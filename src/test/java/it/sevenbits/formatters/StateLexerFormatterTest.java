package it.sevenbits.formatters;

import it.sevenbits.core.FormatterException;
import it.sevenbits.formatters.state.formatsettings.SimpleLexerSettings;
import it.sevenbits.formatters.state.StateLexerFormatter;
import it.sevenbits.formatters.state.lexers.factories.StateLexerFactory;
import it.sevenbits.formatters.state.sm.factories.FormatterStateEngineFactory;
import it.sevenbits.io.Writer;
import it.sevenbits.io.streams.PrintStreamWriter;
import it.sevenbits.io.string.StringBuilderWriter;
import it.sevenbits.io.string.StringReader;
import org.junit.Test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.fail;

public class StateLexerFormatterTest {

    @Test
    public void testSimpleLexer() {
        StringBuilder actual = new StringBuilder();
        try {
            Writer out = new StringBuilderWriter(actual);
            new StateLexerFormatter(new StateLexerFactory(),
                    new FormatterStateEngineFactory(new SimpleLexerSettings("settings/lexer/containers.json"))
            ).format(new StringReader("aaaaa;;;babaf;b;faba;f"), out);
        } catch (FormatterException e) {
            fail("");
        }
        assertEquals("aaaaa;;;" + "\n" +
                "babaf;" + "\n" +
                "b;" + "\n" +
                "faba;" + "\n" +
                "f", actual.toString());
    }



    @Test
    public void testSimpleLexer2() {
        StringBuilder actual = new StringBuilder();
        try {
            Writer out = new StringBuilderWriter(actual);
            new StateLexerFormatter(new StateLexerFactory(),
                    new FormatterStateEngineFactory(new SimpleLexerSettings("settings/lexer/containers.json"))
            ).format(new StringReader("{{{{a;}}}}"), out);
        } catch (FormatterException e) {
            fail("");
        }
        assertEquals("{\n" +
                "    {\n" +
                "        {\n" +
                "            {\n" +
                "                a;\n" +
                "            }\n" +
                "        }\n" +
                "    }\n" +
                "}", actual.toString());
    }

    @Test
    public void first(){
        StringBuilder actual = new StringBuilder();
        try {
            Writer out = new StringBuilderWriter(actual);
            new StateLexerFormatter(new StateLexerFactory(),
                    new FormatterStateEngineFactory(new SimpleLexerSettings("settings/lexer/containers.json"))
            ).format(
                    new StringReader("aaa { bbbb; ccc;}"),
                    out
            );
        } catch (FormatterException e) {
            fail("");
        }
        assertEquals("aaa {\n    bbbb;\n    ccc;\n}", actual.toString());
    }

    @Test
    public void firstInConsole(){
        try {
            PrintStreamWriter out = new PrintStreamWriter(System.out);
            new StateLexerFormatter(new StateLexerFactory(),
                    new FormatterStateEngineFactory(new SimpleLexerSettings("settings/lexer/containers.json"))
            ).format(
                    new StringReader("aaa { bbbb; ccc;}"),
                    out
            );
            out.close();
        } catch (FormatterException e) {
            fail("");
        }
    }

    @Test
    public void firstHelloWorld(){
        StringBuilder actual = new StringBuilder();
        try {
            Writer out = new StringBuilderWriter(actual);
            new StateLexerFormatter(new StateLexerFactory(),
                    new FormatterStateEngineFactory(new SimpleLexerSettings("settings/lexer/containers.json"))
            ).format(
                    new StringReader("class HelloWorld {" +
                            "public static void main(String[] args) {" +
                            "System.out.println(\"Hello World!\");" +
                            "}" +
                            "}"),
                    out
            );
        } catch (FormatterException e) {
            fail("");
        }
        assertEquals("class HelloWorld {\n" +
                "    public static void main(String[] args) {\n" +
                "        System.out.println(\"Hello World!\");\n" +
                "    }\n" +
                "}", actual.toString());
    }

    @Test
    public void firstHelloWorldConsole(){
        try {
            PrintStreamWriter out = new PrintStreamWriter(System.out);
            new StateLexerFormatter(new StateLexerFactory(),
                    new FormatterStateEngineFactory(new SimpleLexerSettings("settings/lexer/containers.json"))
            ).format(
                    new StringReader("class HelloWorld {\n" +
                            "public static void main(String[] args) {" +
                            "System.out.println(\"Hello World!\");" +
                            "}" +
                            "}"),
                    out
            );
            out.close();
        } catch (FormatterException e) {
            fail("");
        }
    }

    @Test
    public void firstHelloWorldConsol2e(){
        try {
            PrintStreamWriter out = new PrintStreamWriter(System.out);
            new StateLexerFormatter(new StateLexerFactory(),
                    new FormatterStateEngineFactory(new SimpleLexerSettings("settings/lexer/containers.json"))
            ).format(
                    new StringReader("class HelloWorld {\n" +
                            "public static void main(String[] args) {" +
                            "System.out.println(\"{Hello {World!}}\");" +
                            "}" +
                            "}"),
                    out
            );
            out.close();
        } catch (FormatterException e) {
            fail("");
        }
    }
}