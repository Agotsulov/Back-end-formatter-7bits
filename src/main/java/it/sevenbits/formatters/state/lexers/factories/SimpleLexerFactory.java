package it.sevenbits.formatters.state.lexers.factories;

import it.sevenbits.formatters.state.lexers.Lexer;
import it.sevenbits.formatters.state.lexers.LexerException;
import it.sevenbits.formatters.state.lexers.SimpleLexer;
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
