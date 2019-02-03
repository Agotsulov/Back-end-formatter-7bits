package it.sevenbits.formatters.state.sm.cmd;

import it.sevenbits.formatters.state.lexers.StateLexer;
import it.sevenbits.formatters.state.tokens.Word;
import it.sevenbits.other.ContainerStringBuilder;

public class AppendChar implements Command{

    private ContainerStringBuilder cs;

    public AppendChar(final ContainerStringBuilder cs) {
        this.cs = cs;
    }

    @Override
    public void execute(StateLexer lexer) {
        cs.append(lexer.getCurrentChar());
        if (!lexer.hasNextChar()) { //(не)много костылей
            lexer.addToken(new Word(cs.toString()));
        }
    }
}
