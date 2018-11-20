package it.sevenbits.formatters.lexer.lexers;

import it.sevenbits.other.IteratorException;
import it.sevenbits.formatters.lexer.tokens.Token;
import it.sevenbits.io.IReader;

public class SimpleLexer implements Lexer {

    private IReader reader;

    public SimpleLexer(IReader reader) {
        this.reader = reader;
    }

    @Override
    public Token next() throws IteratorException {
        return null;
    }

    @Override
    public boolean hasNext() {
        return reader.hasNext();
    }

}
