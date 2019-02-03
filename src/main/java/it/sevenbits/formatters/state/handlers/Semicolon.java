package it.sevenbits.formatters.state.handlers;

import it.sevenbits.formatters.state.containers.NewLineFlagContainer;
import it.sevenbits.formatters.state.formatsettings.Settings;
import it.sevenbits.formatters.state.formatsettings.LexerSettingsException;
import it.sevenbits.other.StringUtils;

/**
 *
 */
public class Semicolon extends SimpleHandler {

    private NewLineFlagContainer flagContainer;

    @Override
    public void start(final Settings settings) throws HandlerException {
        super.start(settings);
        try {
            flagContainer = (NewLineFlagContainer) settings.getContainers().get("NewLineFlagContainer");
        } catch (LexerSettingsException e) {
            throw new HandlerException();
        }
    }

    @Override
    public String handle(final String lexeme) {
        String result = "";

        if (getFormat().isIndent()) {
            result += StringUtils.repeat(getFormat().getIndentString(), getFormat().getIndentLevel());
        }

        getFormat().setIndent(true);

        flagContainer.setNeedNewLine(true);

        return result + lexeme;
    }
}
