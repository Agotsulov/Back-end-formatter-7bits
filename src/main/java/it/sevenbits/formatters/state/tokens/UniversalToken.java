package it.sevenbits.formatters.state.tokens;

public class UniversalToken implements Token{

    private String name;
    private String lexeme;

    public UniversalToken(String name, String lexeme) {
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
