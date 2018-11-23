package it.sevenbits.formatters.lexer.handlers;

import it.sevenbits.formatters.lexer.formatsettings.LexerSettings;
import it.sevenbits.formatters.lexer.formatsettings.LexerSettingsException;
import it.sevenbits.formatters.lexer.tokens.Token;
import it.sevenbits.formatters.simple.containers.NewLineFlagContainer;
import it.sevenbits.other.StringUtils;

/**
 *
 */
public class CloseBrace extends SimpleHandler {

    private NewLineFlagContainer flagContainer;

    @Override
    public void start(final LexerSettings settings) throws HandlerException {
        super.start(settings);
        try {
            flagContainer = (NewLineFlagContainer) settings.getContainers().get("NewLineFlagContainer");
        } catch (LexerSettingsException e) {
            throw new HandlerException();
        }
    }

    @Override
    public boolean validate(final Token token) {
        return "CloseBrace".equals(token.getName());
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
