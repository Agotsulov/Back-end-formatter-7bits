package it.sevenbits.formatters.lexer.lexerfactories;

import it.sevenbits.formatters.lexer.lexers.Lexer;
import it.sevenbits.io.IReader;

public interface LexerFactory {

    Lexer createLexer(IReader reader);

}
