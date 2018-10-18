package it.sevenbits.handlers;

import it.sevenbits.other.StringUtils;

public class Semicolon extends DefaultHandler {

    @Override
    public boolean validate(char symbol) {
        return symbol == ';';
    }

    @Override
    public String handle() {
        String result = "";

        if (format.indent)
            result += StringUtils.repeat(format.indentString, format.indentLevel - 1);

        format.indent = true;
        format.isNewLine = true;

        return result + ";\n";
    }
}
