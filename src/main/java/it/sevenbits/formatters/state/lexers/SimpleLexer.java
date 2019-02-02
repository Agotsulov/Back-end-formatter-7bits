package it.sevenbits.formatters.state.lexers;

import it.sevenbits.formatters.state.tokens.*;
import it.sevenbits.io.Reader;
import it.sevenbits.io.ReaderException;

import java.util.ArrayList;
import java.util.List;

/**
 *
 */
public class SimpleLexer implements Lexer {

    private int i;
    private List<Token> tokens;

    /**
     * Я понимаю что это ужас.
     * Но если остальное правильно ,то можно пострадать и TODO: сделать динамическую загрузку
     * @param reader reader
     * @throws LexerException Something gone wrong
     */
    public SimpleLexer(final Reader reader) throws LexerException {
        try {
            i = 0;
            tokens = new ArrayList<>();
            StringBuilder string = new StringBuilder();
            while (reader.hasNext()) {
                while (reader.hasNext()) {
                    char current = reader.next();

                    if (' ' == current) {
                        if (string.length() > 0) {
                            tokens.add(new Word(string.toString()));
                            string = new StringBuilder();
                        }
                        int s = 1;
                        current = reader.next();
                        while (current == ' ') {
                            current = reader.next();
                            s++;
                        }
                        tokens.add(new Spaces(s));
                    }

                    if (('\n' == current) || ('\r' == current)) {
                        if (string.length() > 0) {
                            tokens.add(new Word(string.toString()));
                            string = new StringBuilder();
                        }
                        tokens.add(new NewLine());
                        break;
                    }
                    if (';' == current) {
                        if (string.length() > 0) {
                            tokens.add(new Word(string.toString()));
                            string = new StringBuilder();
                        }
                        tokens.add(new Semicolon());
                        break;
                    }
                    if ('{' == current) {
                        if (string.length() > 0) {
                            tokens.add(new Word(string.toString()));
                            string = new StringBuilder();
                        }
                        tokens.add(new OpenBrace());
                        break;
                    }
                    if ('}' == current) {
                        if (string.length() > 0) {
                            tokens.add(new Word(string.toString()));
                            string = new StringBuilder();
                        }
                        tokens.add(new CloseBrace());
                        break;
                    }

                    string.append(current);
                }
            }
            if (string.length() > 0) {
                tokens.add(new Word(string.toString()));
            }

        } catch (ReaderException e) {
            throw new LexerException();
        }
    }

    @Override
    public Token getCurrent(){
        return tokens.get(i);
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
