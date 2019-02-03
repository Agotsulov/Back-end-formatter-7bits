package it.sevenbits.formatters.state.lexers;

import it.sevenbits.formatters.state.sm.StateEngine;
import it.sevenbits.formatters.state.sm.cmd.Command;
import it.sevenbits.formatters.state.tokens.*;
import it.sevenbits.io.Reader;
import it.sevenbits.io.ReaderException;

import java.util.ArrayList;
import java.util.List;

/**
 *
 */
public class StateLexer implements Lexer {

    private int i;
    private List<Token> tokens;

    private char currentChar;
    private Reader reader;

    /**
     * @param reader reader
     * @throws LexerException Something gone wrong
     */
    public StateLexer(final Reader reader, final StateEngine<Command> stateEngine) throws LexerException {
        try {
            this.reader = reader;
            i = 0;
            tokens = new ArrayList<>();
            while (reader.hasNext()) {
                currentChar = reader.next();
                Command c = stateEngine.get(String.valueOf(currentChar));
                if (c != null) {
                    c.execute(this);
                }
            }
        } catch (ReaderException e) {
            throw new LexerException();
        }
    }


    @Override
    public void addToken(final Token token) {
        tokens.add(token);
    }

    public char getCurrentChar() {
        return currentChar;
    }

    public boolean hasNextChar() { return reader.hasNext();}

    @Override
    public Token next() {
        Token token = tokens.get(i);
        i++;
        return token;
    }

    @Override
    public boolean hasNext() {
        return i < tokens.size();
    }

}
