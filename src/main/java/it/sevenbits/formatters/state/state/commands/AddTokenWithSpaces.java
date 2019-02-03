package it.sevenbits.formatters.state.state.commands;

import it.sevenbits.formatters.state.lexers.StateLexer;
import it.sevenbits.formatters.state.tokens.Token;
import it.sevenbits.formatters.state.tokens.UniversalToken;
import it.sevenbits.other.ContainerStringBuilder;

/**
 *
 */
public class AddTokenWithSpaces implements Command {

    private Token token;
    private ContainerStringBuilder cs;
    private ContainerStringBuilder forSpaces;
    private String tokenName;


    public AddTokenWithSpaces(final Token token,
                              final ContainerStringBuilder cs,
                              final String tokenName,
                              final ContainerStringBuilder forSpaces) {
        this.token = token;
        this.cs = cs;
        this.forSpaces = forSpaces;
        this.tokenName = tokenName;
    }

    @Override
    public void execute(final StateLexer lexer) {
        if (cs.getString().length() > 0) {
            lexer.addToken(new UniversalToken(tokenName, cs.toString()));
            cs.setString(new StringBuilder());
        }
        if (forSpaces.getString().length() > 0) {
            lexer.addToken(new UniversalToken("Spaces", forSpaces.toString()));
            forSpaces.setString(new StringBuilder());
        }
        lexer.addToken(token);

    }

}