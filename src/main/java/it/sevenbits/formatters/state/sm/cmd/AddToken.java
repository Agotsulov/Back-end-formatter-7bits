package it.sevenbits.formatters.state.sm.cmd;

import it.sevenbits.formatters.state.lexers.StateLexer;
import it.sevenbits.formatters.state.tokens.Token;
import it.sevenbits.formatters.state.tokens.UniversalToken;
import it.sevenbits.other.ContainerStringBuilder;

public class AddToken implements Command{

    private ContainerStringBuilder cs;
    private String tokenName;

    public AddToken( final String tokenName, final ContainerStringBuilder cs) {
        this.cs = cs;
        this.tokenName = tokenName;
    }

    @Override
    public void execute(StateLexer lexer) {
        if (cs.getString().length() > 0) {
            lexer.addToken(new UniversalToken(tokenName,cs.toString()));
            cs.setString(new StringBuilder());
        }

    }

}
