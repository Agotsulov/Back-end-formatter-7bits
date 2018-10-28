package it.sevenbits.handlers;

import it.sevenbits.containers.NewLineFlagContainer;
import it.sevenbits.core.FormatSettings;
import it.sevenbits.exceptions.FormatSettingsException;
import it.sevenbits.exceptions.HandlerException;
import it.sevenbits.other.StringUtils;

public class OpenBrace extends DefaultHandler {

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

    @Override
    public boolean validate(char symbol) {
        return (symbol == '{');
    }

    @Override
    public String handle() {
        String result = "";

        if(flagContainer.needNewLine) {
            result += "\n";
        }

        if (format.indent) {
            result += StringUtils.repeat(format.indentString, format.indentLevel);
        }

        result += "{";

        format.indent = true;
        format.isNewLine = true;

        flagContainer.needNewLine = true;

        format.indentLevel = format.indentLevel + 1;

        return result;
    }
}
