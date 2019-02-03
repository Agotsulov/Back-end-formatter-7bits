package it.sevenbits.formatters.state.state.commands;

import it.sevenbits.formatters.state.lexers.StateLexer;
import it.sevenbits.formatters.state.tokens.Token;
import it.sevenbits.formatters.state.tokens.UniversalToken;
import it.sevenbits.other.ContainerStringBuilder;

/**
 *
 */
public class AddTokenWith implements Command {

    private Token token;
    private ContainerStringBuilder cs;
    private String tokenName;

    /**
     * @param token Token which add after add UniversalToken
     * @param cs ContainerStringBuilder for UniversalToken lexeme
     * @param tokenName for UniversalToken name
     */
    public AddTokenWith(final Token token, final ContainerStringBuilder cs, final String tokenName) {
        this.token = token;
        this.cs = cs;
        this.tokenName = tokenName;
    }

    @Override
    public void execute(final StateLexer lexer) {
        if (cs.getString().length() > 0) {
            lexer.addToken(new UniversalToken(tokenName, cs.toString()));
            cs.setString(new StringBuilder());
        }
        lexer.addToken(token);

    }

}
