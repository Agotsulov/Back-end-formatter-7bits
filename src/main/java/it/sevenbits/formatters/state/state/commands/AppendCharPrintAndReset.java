package it.sevenbits.formatters.state.state.commands;

import it.sevenbits.formatters.state.lexers.StateLexer;
import it.sevenbits.formatters.state.tokens.UniversalToken;
import it.sevenbits.formatters.state.tokens.Word;
import it.sevenbits.other.ContainerStringBuilder;

/**
 *
 */
public class AppendCharPrintAndReset implements Command {

    private ContainerStringBuilder cs;
    private ContainerStringBuilder forSpaces;

    /**
     * @param cs ContainerStringBuilder for word
     * @param forSpaces ContainerStringBuilder for spaces
     */
    public AppendCharPrintAndReset(final ContainerStringBuilder cs, final ContainerStringBuilder forSpaces) {
        this.cs = cs;
        this.forSpaces = forSpaces;
    }

    @Override
    public void execute(final StateLexer lexer) {
        if (cs.getString().length() > 0) {
            lexer.addToken(new UniversalToken("Word", cs.toString()));
            cs.setString(new StringBuilder());
        }
        if (forSpaces.getString().length() > 0) {
            lexer.addToken(new UniversalToken("Spaces", forSpaces.toString()));
            forSpaces.setString(new StringBuilder());
        }
        cs.append(lexer.getCurrentChar());
        if (!lexer.hasNextChar()) {
            lexer.addToken(new Word(cs.toString()));
        }
    }
}
