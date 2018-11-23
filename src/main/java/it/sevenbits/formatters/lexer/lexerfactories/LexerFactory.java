package it.sevenbits.formatters.lexer.lexerfactories;

import it.sevenbits.formatters.lexer.lexers.Lexer;
import it.sevenbits.io.Reader;

/**
 *
 */
public interface LexerFactory {

    /**
     * @param reader reader
     * @return lexer
     * @throws LexerFactoryException  Something has gone wrong
     */
    Lexer createLexer(final Reader reader) throws LexerFactoryException;

}
