package it.sevenbits.formatters.lexer.formatters;

import it.sevenbits.core.Formatter;
import it.sevenbits.core.FormatterException;
import it.sevenbits.formatters.lexer.lexerfactories.LexerFactory;
import it.sevenbits.formatters.lexer.lexers.Lexer;
import it.sevenbits.formatters.lexer.tokens.Token;
import it.sevenbits.io.Reader;
import it.sevenbits.io.Writer;
import it.sevenbits.io.WriterException;
import it.sevenbits.other.IteratorException;

public class LexerFormatter implements Formatter {

    private LexerFactory lexerFactory;

    public LexerFormatter(LexerFactory lexerFactory) {
        this.lexerFactory = lexerFactory;
    }

    @Override
    public void format(Reader reader, Writer writer) throws FormatterException {
        try {
            Lexer lexer = lexerFactory.createLexer(reader);
            while (lexer.hasNext()) {
                Token token = lexer.next();
                writer.write(token.getLexeme());
            }
        } catch (IteratorException | WriterException e) {
            throw new FormatterException();
        }
    }
}
