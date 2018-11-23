package it.sevenbits.formatters.lexer.handlers;

import it.sevenbits.formatters.lexer.formatsettings.LexerSettings;
import it.sevenbits.formatters.lexer.formatsettings.LexerSettingsException;
import it.sevenbits.formatters.lexer.tokens.Token;
import it.sevenbits.formatters.simple.containers.NewLineFlagContainer;
import it.sevenbits.other.StringUtils;

/**
 *
 */
public class AnySymbol extends SimpleHandler {

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


    private String string;


    @Override
    public boolean validate(final Token token) {
        if ("Spaces".equals(token.getName())) {
            if (((getFormat().isNewLine()) || (flagContainer.isNeedNewLine()))) {
                return false;
            } else {
                string = token.getLexeme();
                return true;
            }
        }
        if ("Word".equals(token.getName())) {
            string = token.getLexeme();
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

        return result + string;
    }

}
