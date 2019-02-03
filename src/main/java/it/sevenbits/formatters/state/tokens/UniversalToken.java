package it.sevenbits.formatters.state.tokens;

import java.util.Objects;

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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        UniversalToken that = (UniversalToken) o;
        return Objects.equals(name, that.name) &&
                Objects.equals(lexeme, that.lexeme);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name, lexeme);
    }
}
