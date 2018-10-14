package it.sevenbits.formatters;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.google.gson.stream.JsonReader;
import it.sevenbits.core.FormatSettings;
import it.sevenbits.core.Handler;
import it.sevenbits.exceptions.FormatSettingsException;
import it.sevenbits.exceptions.HandlerException;
import org.apache.commons.lang3.StringUtils;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

public class DefaultFormatSettings implements FormatSettings {

    private static final char INDENT_CHAR_DEFAULT = ' ';
    private static final int INDENT_LENGTH_DEFAULT = 4;

    private char indentChar;
    private int indentLength;
    private boolean extraBraces;

    public boolean indent = false;
    public boolean isNewLine = true;
    public int indentLevel = 0;

    public String indentString = "";

    private List<Handler> handlers = null;

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

    private List<Handler> loadHandlers(String handlersFile) throws FormatSettingsException {
        List<Handler> result = new ArrayList<>();
        Gson json = new Gson();
        JsonReader jReader = null;
        try {
            jReader = new JsonReader(new FileReader(new File(handlersFile)));
        } catch (FileNotFoundException e) {
            throw new FormatSettingsException();
        }
        List<String> handlers = json.fromJson(jReader,new TypeToken<List<String>>(){}.getType());
        for (String handler : handlers) {
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

            result.add((Handler) instance);
        }

        for(Handler handler: result) {
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

    public List<Handler> getHandlers() {
        return handlers;
    }
}
