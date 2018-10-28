package it.sevenbits.formatters;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.google.gson.stream.JsonReader;
import it.sevenbits.core.FormatSettings;
import it.sevenbits.core.Handler;
import it.sevenbits.exceptions.FormatSettingsException;
import it.sevenbits.exceptions.HandlerException;
import it.sevenbits.other.StringUtils;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.util.*;

public class DefaultFormatSettings implements FormatSettings {

    /*
        Что то этот класс слишком обьемным получился.
        TODO: Придумать как уменьшить/разделить класс.
     */

    private char indentChar;
    private int indentLength;
    private boolean extraBraces;

    public boolean indent = false;
    public boolean isNewLine = true;
    public int indentLevel = 0;

    public String indentString = "";

    private Map<Handler, Boolean> handlers = null;

    public DefaultFormatSettings(String propertiesFile, String handlersFile) throws FormatSettingsException {
        loadProperties(propertiesFile);
        setSetting();
        handlers = loadHandlers(handlersFile);
    }

    private void loadProperties(String propertiesFile) throws FormatSettingsException {
        Properties properties = new Properties();
        try {
            properties.load(new FileReader(new File(propertiesFile)));
        } catch (IOException e) {
            throw new FormatSettingsException();
        }
        indentLength = Integer.parseInt(properties.getProperty("indentLength"));
        indentChar = properties.getProperty("indentChar").charAt(1);
        extraBraces = Boolean.parseBoolean(properties.getProperty("extraBraces"));
    }

    private void setSetting(){
        indentString = StringUtils.repeat(indentChar, indentLength);
    }

    private Map<Handler, Boolean> loadHandlers(String handlersFile) throws FormatSettingsException {
        Map<Handler, Boolean> result = new LinkedHashMap<>(); //Надо сохранять порядок
        Gson json = new Gson();
        JsonReader jReader = null;
        try {
            jReader = new JsonReader(new FileReader(new File(handlersFile)));
        } catch (FileNotFoundException e) {
            throw new FormatSettingsException();
        }
        Map<String, Boolean> handlers = json.fromJson(jReader,new TypeToken<Map<String, Boolean>>(){}.getType());
        for (String handler : handlers.keySet()) {
            Class<?> c = null;
            try {
                c = Class.forName(handler);
            } catch (ClassNotFoundException e) {
                e.printStackTrace();
            }

            Constructor<?> constructor = null;
            try {
                assert c != null; //Че за assert и зачем IDEA предлагает их ставить?
                constructor = c.getConstructor();
            } catch (NoSuchMethodException e) {
                throw new FormatSettingsException();
            }
            Object instance = null;
            try {
                assert constructor != null;
                instance = constructor.newInstance();
            } catch (InstantiationException | InvocationTargetException | IllegalAccessException e) {
                throw new FormatSettingsException();
            }

            result.put((Handler) instance, handlers.get(handler));
        }

        for(Handler handler: result.keySet()) {
            try {
                handler.start(this);
            } catch (HandlerException e) {
                throw new FormatSettingsException();
            }
        }

        return result;
    }

    public char getIndentChar() {
        return indentChar;
    }

    public int getIndentLength() {
        return indentLength;
    }

    public boolean isExtraBraces() {
        return extraBraces;
    }

    public boolean isIndent() {
        return indent;
    }

    public boolean isNewLine() {
        return isNewLine;
    }

    public int getIndentLevel() {
        return indentLevel;
    }

    public String getIndentString() {
        return indentString;
    }

    public Map<Handler, Boolean> getHandlers() {
        return handlers;
    }
}
