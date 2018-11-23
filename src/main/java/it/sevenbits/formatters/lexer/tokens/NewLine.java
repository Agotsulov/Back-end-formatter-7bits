package it.sevenbits.formatters.lexer.tokens;
/**
 *
 */
public class NewLine implements Token {

    @Override
    public String getName() {
        return "Newline";
    }

    @Override
    public String getLexeme() {
        return "\n";
    }
}
