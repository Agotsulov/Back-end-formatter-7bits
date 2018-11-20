package it.sevenbits.formatters.simple.handlers;

import it.sevenbits.formatters.simple.containers.NewLineFlagContainer;
import it.sevenbits.formatters.simple.formatsettings.FormatSettings;
import it.sevenbits.formatters.simple.formatsettings.FormatSettingsException;
import it.sevenbits.other.StringUtils;

/**
 *
 */
public class AnySymbol extends SimpleHandler {

    private NewLineFlagContainer flagContainer;

    @Override
    public void start(final FormatSettings settings) throws HandlerException {
        super.start(settings);
        try {
            flagContainer = (NewLineFlagContainer) settings.getContainers().get("NewLineFlagContainer");
        } catch (FormatSettingsException e) {
            throw new HandlerException();
        }
    }


    private char aChar; //Из-за checkstyle переименовывать


    @Override
    public boolean validate(final char symbol) {
        if (((getFormat().isNewLine()) || (flagContainer.isNeedNewLine())) && (symbol == ' ')) {
            return false;
        }
        if ((symbol != '\n') && (symbol != '\r')) {
            this.aChar = symbol;
            return true;
        }
        return false;
    }

    @Override
    public String handle() {
        String result = "";

        if (flagContainer.isNeedNewLine()) {
            result += "\n";
        }

        if (getFormat().isIndent()) {
            result += StringUtils.repeat(getFormat().getIndentString(), getFormat().getIndentLevel());
        }

        getFormat().setIndent(false);
        getFormat().setNewLine(false);

        flagContainer.setNeedNewLine(false);

        return result + aChar;
    }

}
