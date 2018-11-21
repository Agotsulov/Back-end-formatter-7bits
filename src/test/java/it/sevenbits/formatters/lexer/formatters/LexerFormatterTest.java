package it.sevenbits.formatters.lexer.formatters;

import it.sevenbits.core.FormatterException;
import it.sevenbits.formatters.lexer.lexerfactories.SimpleLexerFactory;
import it.sevenbits.io.Writer;
import it.sevenbits.io.string.StringBuilderWriter;
import it.sevenbits.io.string.StringReader;
import org.junit.Test;

import static org.junit.Assert.*;

public class LexerFormatterTest {

    @Test
    public void testSimpleLexer() {
        StringBuilder actual = new StringBuilder();
        try {
            Writer out = new StringBuilderWriter(actual);
            new LexerFormatter(new SimpleLexerFactory()).format(
                    new StringReader("aaaaa;;;babaf;b;faba;f"),
                    out
            );
        } catch (FormatterException e) {
            fail("");
        }
        assertEquals("aaaaa;;;babaf;b;faba;f", actual.toString());
    }

}