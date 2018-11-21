package it.sevenbits.formatters.lexer.lexers;

import it.sevenbits.formatters.lexer.tokens.AnySymbol;
import it.sevenbits.io.Reader;
import it.sevenbits.other.IteratorException;
import it.sevenbits.formatters.lexer.tokens.Token;

public class SimpleLexer implements Lexer {

    private Reader reader;

    public SimpleLexer(Reader reader) {
        this.reader = reader;
    }

    @Override
    public Token next() throws IteratorException {
        return new AnySymbol("" + reader.next());
    }

    @Override
    public boolean hasNext() {
        return reader.hasNext();
    }

}
