package it.sevenbits.formatters.state.containers;

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
    private int indentLevel = 0;

    private String indentString = "";

    private String currentIndentString = "";

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

    public boolean isIndent() {
        return indent;
    }

    public void setIndent(final boolean indent) {
        this.indent = indent;
    }


    public int getIndentLevel() {
        return indentLevel;
    }

    public void setIndentLevel(final int indentLevel) {
        this.indentLevel = indentLevel;
        currentIndentString += StringUtils.repeat(getIndentString(), getIndentLevel());
    }

    public String getIndentString() {
        return indentString;
    }

    public void setIndentString(final String indentString) {
        this.indentString = indentString;
    }

    public String getCurrentIndentString() {
        return currentIndentString;
    }
}
