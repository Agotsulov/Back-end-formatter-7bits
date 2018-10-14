package it.sevenbits.handlers;

import org.apache.commons.lang3.StringUtils;

public class CloseBrace extends DefaultHandler {

    @Override
    public boolean validate(char symbol) {
        return (symbol == '}');
    }

    @Override
    public String handle() {
        String result = "";

        if(!format.isNewLine)
            result += "\n";

        format.indentLevel = format.indentLevel - 1;

        if (format.indent)
            result += StringUtils.repeat(format.indentString, format.indentLevel);

        result += "}";

        format.indent = true;
        format.isNewLine = false;

        return result;
    }
}
