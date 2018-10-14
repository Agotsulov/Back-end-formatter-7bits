package it.sevenbits.handlers;

import org.apache.commons.lang3.StringUtils;

public class AnySymbol extends DefaultHandler {

    private char symbol;

    @Override
    public boolean validate(char symbol) {
        if((format.isNewLine) && (symbol == ' '))
            return false;
        if((symbol != '\n') && (symbol != '\r')){
            this.symbol = symbol;
            return true;
        }
        return false;
    }

    @Override
    public String handle() {
        String result = "";

        if (format.indent)
            result += StringUtils.repeat(format.indentString, format.indentLevel);

        format.indent = false;
        format.isNewLine = false;

        return result + symbol;
    }

}
