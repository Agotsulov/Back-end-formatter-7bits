package it.sevenbits.formatters.lexer.lexerfactories;

import it.sevenbits.formatters.lexer.lexers.Lexer;
import it.sevenbits.io.Reader;

public interface LexerFactory {

    Lexer createLexer(Reader reader);

}
