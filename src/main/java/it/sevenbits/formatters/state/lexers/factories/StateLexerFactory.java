package it.sevenbits.formatters.state.lexers.factories;

import it.sevenbits.formatters.state.lexers.Lexer;
import it.sevenbits.formatters.state.lexers.LexerException;
import it.sevenbits.formatters.state.lexers.StateLexer;
import it.sevenbits.formatters.state.state.factories.LexerStateEngineFactory;
import it.sevenbits.io.Reader;

/**
 *
 */
public class StateLexerFactory implements LexerFactory {

    @Override
    public Lexer createLexer(final Reader reader) throws LexerFactoryException {
        try {
            return new StateLexer(reader, new LexerStateEngineFactory().getStateEngine());
        } catch (LexerException e) {
            throw new LexerFactoryException();
        }
    }
}
