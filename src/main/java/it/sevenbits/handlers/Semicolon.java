package it.sevenbits.handlers;

import it.sevenbits.core.FormatSettings;
import it.sevenbits.core.Handler;
import it.sevenbits.formatters.DefaultFormatSettings;
import org.apache.commons.lang3.StringUtils;

public class Semicolon implements Handler {

    DefaultFormatSettings format;

    @Override
    public void start(FormatSettings format) {
        this.format = (DefaultFormatSettings) format;
    }

    @Override
    public boolean validate(char symbol) {
        return symbol == ';';
    }

    @Override
    public String handle() {
        String result = "";

        if (format.indent){
            result += StringUtils.repeat(format.indentString, format.indentLevel - 1);
        }

        format.indent = true;
        format.isNewLine = true;

        return result + ";\n";
    }
}
