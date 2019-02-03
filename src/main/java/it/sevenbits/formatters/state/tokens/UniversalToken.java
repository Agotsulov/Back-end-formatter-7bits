package it.sevenbits.formatters.state.tokens;

/**
 *
 */
public class UniversalToken implements Token {

    private String name;
    private String lexeme;

    /**
     * @param name String name
     * @param lexeme String lexem
     */
    public UniversalToken(final String name, final String lexeme) {
        this.name = name;
        this.lexeme = lexeme;
    }

    @Override
    public String getName() {
        return name;
    }

    @Override
    public String getLexeme() {
        return lexeme;
    }


}
