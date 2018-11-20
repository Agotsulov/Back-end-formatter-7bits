package it.sevenbits.formatters;

import it.sevenbits.core.FormatterException;
import it.sevenbits.io.WriterException;
import it.sevenbits.formatters.simple.formatsettings.SimpleFormatSettings;
import it.sevenbits.formatters.simple.formatters.SimpleFormatter;
import it.sevenbits.io.IWriter;
import it.sevenbits.io.streams.PrintStreamWriter;
import it.sevenbits.io.string.StringBuilderWriter;
import it.sevenbits.io.string.StringReader;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.fail;

public class Homework1 {

    @Test
    void first(){
        StringBuilder actual = new StringBuilder();
        try {
            IWriter out = new StringBuilderWriter(actual);
            new SimpleFormatter(new SimpleFormatSettings("settings/containers.json", "settings/handles.json")).format(
                    new StringReader("aaa { bbbb; ccc;}"),
                    out
            );
        } catch (FormatterException e) {
            fail("");
        }
        assertEquals("aaa {\n    bbbb;\n    ccc;\n}", actual.toString());
    }

    @Test
    void firstInConsole(){
        try {
            IWriter out = new PrintStreamWriter(System.out);
            new SimpleFormatter(new SimpleFormatSettings("settings/containers.json", "settings/handles.json")).format(
                    new StringReader("aaa { bbbb; ccc;}"),
                    out
            );
            out.close();
        } catch (FormatterException | WriterException e) {
            fail("");
        }
    }

    @Test
    void firstHelloWorld(){
        StringBuilder actual = new StringBuilder();
        try {
            IWriter out = new StringBuilderWriter(actual);
            new SimpleFormatter(new SimpleFormatSettings("settings/containers.json", "settings/handles.json")).format(
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
    void firstHelloWorldConsole(){
        try {
            IWriter out = new PrintStreamWriter(System.out);
            new SimpleFormatter(new SimpleFormatSettings("settings/containers.json", "settings/handles.json")).format(
                    new StringReader("class HelloWorld {" +
                            "public static void main(String[] args) {" +
                            "System.out.println(\"Hello World!\");" +
                            "}" +
                            "}"),
                    out
            );
            out.close();
        } catch (FormatterException | WriterException e) {
            fail("");
        }
    }
}
