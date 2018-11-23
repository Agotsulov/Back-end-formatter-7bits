package it.sevenbits.formatters.lexer.lexerfactories;

import it.sevenbits.formatters.lexer.lexers.LexerException;
import it.sevenbits.formatters.lexer.lexers.SimpleLexer;
import it.sevenbits.formatters.lexer.lexers.Lexer;
import it.sevenbits.io.Reader;

/**
 *
 */
public class SimpleLexerFactory implements LexerFactory {

    /**
     * @param reader reader
     * @return Lexer
     * @throws LexerFactoryException Something has gone wrong
     */
    @Override
    public Lexer createLexer(final Reader reader) throws LexerFactoryException {
        try {
            return new SimpleLexer(reader);
        } catch (LexerException e) {
            throw new LexerFactoryException();
        }
    }
}
