package it.sevenbits.handlers;

import it.sevenbits.containers.NewLineFlagContainer;
import it.sevenbits.core.FormatSettings;
import it.sevenbits.exceptions.FormatSettingsException;
import it.sevenbits.exceptions.HandlerException;
import it.sevenbits.other.StringUtils;

/**
 *
 */
public class CloseBrace extends SimpleHandler {

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
        return (symbol == '}');
    }

    @Override
    public String handle() {
        String result = "";

        if (flagContainer.isNeedNewLine()) {
            result += "\n";
        }

        getFormat().setIndentLevel(getFormat().getIndentLevel() - 1);

        if (getFormat().isIndent()) {
            result += StringUtils.repeat(getFormat().getIndentString(), getFormat().getIndentLevel());
        }

        result += "}";

        getFormat().setIndent(true);
        getFormat().setNewLine(false);

        flagContainer.setNeedNewLine(true);

        return result;
    }
}
