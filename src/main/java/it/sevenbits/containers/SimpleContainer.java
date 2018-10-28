package it.sevenbits.containers;

import it.sevenbits.exceptions.ContainerException;
import it.sevenbits.other.StringUtils;

import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.Properties;

/**
 *
 */
public class SimpleContainer extends AContainer {

    private boolean indent = false;
    private boolean isNewLine = true;
    private int indentLevel = 0;

    private String indentString = "";

    @Override
    public void load() throws ContainerException {
        load(getFile());
    }

    @Override
    public void load(final String file) throws ContainerException {
        Properties properties = new Properties();
        try {
            properties.load(new FileReader(new File(file)));
        } catch (IOException e) {
            throw new ContainerException();
        }
        int indentLength = Integer.parseInt(properties.getProperty("indentLength"));
        char indentChar = properties.getProperty("indentChar").charAt(1);
        indentString = StringUtils.repeat(indentChar, indentLength);
    }

    @Override
    public String get(final String key) throws ContainerException {
        /*
            Я знаю что это очень плохо.
            Но если нам надо что то новое мы просто пишем новый Container.
            И тут не много кода всегда будет.
         */
        if ("indent".equals(key)) {
            return Boolean.toString(indent);
        }
        if ("isNewLine".equals(key)) {
            return Boolean.toString(isNewLine);
        }
        if ("indentLevel".equals(key)) {
            return Integer.toString(indentLevel);
        }
        if ("indentString ".equals(key)) {
            return indentString;
        }
        throw new ContainerException();
    }

    @Override
    public void set(final String key, final String value) {
        if ("indent".equals(key)) {
            indent = Boolean.valueOf(value);
        }
        if ("isNewLine".equals(key)) {
            isNewLine = Boolean.valueOf(value);
        }
        if ("indentLevel".equals(key)) {
            indentLevel = Integer.valueOf(value);
        }
        if ("indentString".equals(key)) {
            indentString = value;
        }
    }

    public boolean isIndent() {
        return indent;
    }

    public void setIndent(final boolean indent) {
        this.indent = indent;
    }

    public boolean isNewLine() {
        return isNewLine;
    }

    public void setNewLine(final boolean newLine) {
        isNewLine = newLine;
    }

    public int getIndentLevel() {
        return indentLevel;
    }

    public void setIndentLevel(final int indentLevel) {
        this.indentLevel = indentLevel;
    }

    public String getIndentString() {
        return indentString;
    }

    public void setIndentString(final String indentString) {
        this.indentString = indentString;
    }
}
