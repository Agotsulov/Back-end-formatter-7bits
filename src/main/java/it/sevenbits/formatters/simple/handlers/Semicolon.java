package it.sevenbits.formatters.simple.handlers;

import it.sevenbits.formatters.simple.containers.NewLineFlagContainer;
import it.sevenbits.formatters.simple.formatsettings.FormatSettings;
import it.sevenbits.formatters.simple.formatsettings.FormatSettingsException;
import it.sevenbits.other.StringUtils;

/**
 *
 */
public class Semicolon extends SimpleHandler {

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

    @Override
    public boolean validate(final char symbol) {
        return symbol == ';';
    }

    @Override
    public String handle() {
        String result = "";

        if (getFormat().isIndent()) {
            result += StringUtils.repeat(getFormat().getIndentString(), getFormat().getIndentLevel());
        }

        getFormat().setIndent(true);
        getFormat().setNewLine(true);

        flagContainer.setNeedNewLine(true);

        return result + ";";
    }
}
