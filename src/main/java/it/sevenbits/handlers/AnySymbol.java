package it.sevenbits.handlers;

import it.sevenbits.core.Handler;
import it.sevenbits.core.FormatSettings;
import it.sevenbits.formatters.DefaultFormatSettings;
import org.apache.commons.lang3.StringUtils;


public class AnySymbol implements Handler {

    private char symbol;
    private DefaultFormatSettings format;

    @Override
    public void start(FormatSettings format) {
        this.format = (DefaultFormatSettings) format;
    }

    @Override
    public boolean validate(char symbol) {
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
