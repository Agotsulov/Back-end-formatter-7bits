package it.sevenbits.formatters.state.sm.cmd;

import it.sevenbits.formatters.state.lexers.StateLexer;
import it.sevenbits.formatters.state.tokens.UniversalToken;
import it.sevenbits.formatters.state.tokens.Word;
import it.sevenbits.other.ContainerStringBuilder;

public class AppendCharWithSpaces implements Command{

    private ContainerStringBuilder cs;
    private ContainerStringBuilder forSpaces;

    public AppendCharWithSpaces(final ContainerStringBuilder cs, final ContainerStringBuilder forSpaces) {
        this.cs = cs;
        this.forSpaces = forSpaces;
    }

    @Override
    public void execute(StateLexer lexer) {
        if (cs.getString().length() > 0) {
            lexer.addToken(new UniversalToken("Word",cs.toString()));
            cs.setString(new StringBuilder());
        }
        if (forSpaces.getString().length() > 0) {
            lexer.addToken(new UniversalToken("Spaces",forSpaces.toString()));
            forSpaces.setString(new StringBuilder());
        }
        cs.append(lexer.getCurrentChar());
        if (!lexer.hasNextChar()) { //(не)много костылей
            lexer.addToken(new Word(cs.toString()));
        }
    }
}
