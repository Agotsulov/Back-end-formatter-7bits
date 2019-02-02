package it.sevenbits.formatters.state.tokens;

/**
 *
 */
public class NewLine implements Token {

    @Override
    public String getName() {
        return "NewLine";
    }

    @Override
    public String getLexeme() {
        return "\n";
    }
}
