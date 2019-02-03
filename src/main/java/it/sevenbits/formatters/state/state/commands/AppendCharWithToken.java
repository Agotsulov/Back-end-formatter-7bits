package it.sevenbits.formatters.state.state.commands;

import it.sevenbits.formatters.state.lexers.StateLexer;
import it.sevenbits.formatters.state.tokens.Token;
import it.sevenbits.formatters.state.tokens.UniversalToken;
import it.sevenbits.other.ContainerStringBuilder;

/**
 *
 */
public class AppendCharWithToken implements Command {

    private ContainerStringBuilder cs;
    private Token token;

    /**
     * @param cs ContainerStringBuilder for word
     * @param token Token which add after append char in ContainerStringBuilder
     */
    public AppendCharWithToken(final ContainerStringBuilder cs, final Token token) {
        this.cs = cs;
        this.token = token;
    }

    @Override
    public void execute(final StateLexer lexer) {
        cs.append(lexer.getCurrentChar());
        if (cs.getString().length() > 0) {
            lexer.addToken(new UniversalToken("Word", cs.toString()));
            cs.setString(new StringBuilder());
        }
        lexer.addToken(token);
    }
}
