package it.sevenbits.formatters.state.state.commands;

import it.sevenbits.formatters.state.lexers.StateLexer;
import it.sevenbits.formatters.state.tokens.Word;
import it.sevenbits.other.ContainerStringBuilder;

/**
 *
 */
public class AppendChar implements Command {

    private ContainerStringBuilder cs;

    /**
     * @param cs ContainerStringBuilder for word token
     */
    public AppendChar(final ContainerStringBuilder cs) {
        this.cs = cs;
    }

    @Override
    public void execute(final StateLexer lexer) {
        cs.append(lexer.getCurrentChar());
        if (!lexer.hasNextChar()) {
            lexer.addToken(new Word(cs.toString()));
        }
    }
}
