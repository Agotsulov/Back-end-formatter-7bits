package it.sevenbits.formatters.lexer.lexerfactories;

import it.sevenbits.formatters.lexer.lexers.SimpleLexer;
import it.sevenbits.formatters.lexer.lexers.Lexer;
import it.sevenbits.io.IReader;

public class SimpleLexerFactory implements LexerFactory {

    @Override
    public Lexer createLexer(IReader reader) {
        return new SimpleLexer(reader);
    }
}
