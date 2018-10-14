package it.sevenbits.handlers;

import it.sevenbits.core.FormatSettings;
import it.sevenbits.core.Handler;
import it.sevenbits.formatters.DefaultFormatSettings;
import org.apache.commons.lang3.StringUtils;

public class CloseBrace implements Handler {

    private DefaultFormatSettings format;

    @Override
    public void start(FormatSettings format) {
        this.format = (DefaultFormatSettings) format;
    }

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
        result += "\n";

        format.indent = true;
        format.isNewLine = true;

        return result;
    }
}
