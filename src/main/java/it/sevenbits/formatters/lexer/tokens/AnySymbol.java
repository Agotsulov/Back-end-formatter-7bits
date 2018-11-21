package it.sevenbits.formatters.lexer.tokens;

public class AnySymbol implements Token {

    private String s;

    public AnySymbol(String s){
        this.s = s;
    }

    @Override
    public String getName() {
        return "SYMBOL";
    }

    @Override
    public String getLexeme() {
        return s;
    }
}
