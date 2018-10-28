package it.sevenbits.handlers;

import it.sevenbits.containers.NewLineFlagContainer;
import it.sevenbits.core.FormatSettings;
import it.sevenbits.exceptions.FormatSettingsException;
import it.sevenbits.exceptions.HandlerException;
import it.sevenbits.other.StringUtils;

public class AnySymbol extends DefaultHandler {

    private NewLineFlagContainer flagContainer;

    @Override
    public void start(FormatSettings settings) throws HandlerException {
        super.start(settings);
        try {
            flagContainer = (NewLineFlagContainer) settings.getContainers().get("NewLineFlagContainer");
        } catch (FormatSettingsException e) {
            throw new HandlerException();
        }
    }


    private char symbol;


    @Override
    public boolean validate(char symbol) {
        if(((format.isNewLine) || (flagContainer.needNewLine)) && (symbol == ' '))
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

        if(flagContainer.needNewLine)
            result += "\n";

        if (format.indent)
            result += StringUtils.repeat(format.indentString, format.indentLevel);

        format.indent = false;
        format.isNewLine = false;
        flagContainer.needNewLine = false;

        return result + symbol;
    }

}
