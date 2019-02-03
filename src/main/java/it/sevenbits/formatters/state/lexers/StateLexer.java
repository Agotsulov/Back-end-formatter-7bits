package it.sevenbits.formatters.state.lexers;

import it.sevenbits.formatters.state.state.engines.StateEngine;
import it.sevenbits.formatters.state.state.commands.Command;
import it.sevenbits.formatters.state.tokens.Token;
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
     * @param reader Reader
     * @param stateEngine StateEngine<Command>
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

    /**
     * @return true if reader has next char
     */
    public boolean hasNextChar() {
        return reader.hasNext();
    }

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
