package it.sevenbits.formatters.state.sm.cmd;

import it.sevenbits.formatters.state.lexers.StateLexer;
import it.sevenbits.formatters.state.tokens.Token;
import it.sevenbits.formatters.state.tokens.UniversalToken;
import it.sevenbits.formatters.state.tokens.Word;
import it.sevenbits.other.ContainerStringBuilder;

public class AppendCharWithToken implements Command{

    private ContainerStringBuilder cs;
    private Token token;

    public AppendCharWithToken(final ContainerStringBuilder cs, final Token token) {
        this.cs = cs;
        this.token = token;
    }

    @Override
    public void execute(StateLexer lexer) {
        cs.append(lexer.getCurrentChar());
        if (cs.getString().length() > 0) {
            lexer.addToken(new UniversalToken("Word",cs.toString()));
            cs.setString(new StringBuilder());
        }
        lexer.addToken(token);
    }
}
