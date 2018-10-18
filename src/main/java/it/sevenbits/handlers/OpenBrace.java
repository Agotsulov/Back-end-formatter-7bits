package it.sevenbits.handlers;

import it.sevenbits.other.StringUtils;

public class OpenBrace extends DefaultHandler {

    @Override
    public boolean validate(char symbol) {
        return (symbol == '{');
    }

    @Override
    public String handle() {
        String result = "";

        if (format.indent)
            result += StringUtils.repeat(format.indentString, format.indentLevel);

        result += "{";
        result += "\n";

        format.indent = true;
        format.isNewLine = true;

        format.indentLevel = format.indentLevel + 1;

        return result;
    }
}
