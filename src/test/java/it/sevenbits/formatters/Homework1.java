package it.sevenbits.formatters;

import it.sevenbits.core.Formatter;
import it.sevenbits.exceptions.FormatterException;
import it.sevenbits.exceptions.WriterException;
import it.sevenbits.streams.IWriter;
import it.sevenbits.streams.streams.PrintStreamWriter;
import it.sevenbits.streams.string.StringBuilderWriter;
import it.sevenbits.streams.string.StringReader;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.fail;

public class Homework1 {

    @Test
    void first(){
        StringBuilder actual = new StringBuilder();
        try {
            IWriter out = new StringBuilderWriter(actual);
            Formatter.format(
                    new StringReader("aaa { bbbb; ccc;}"),
                    out,
                    new DefaultFormatSettings("settings/containers.json", "settings/handles.json"));
        } catch (FormatterException e) {
            fail("");
        }
        assertEquals("aaa {\n    bbbb;\n    ccc;\n}", actual.toString());
    }

    @Test
    void firstInConsole(){
        try {
            IWriter out = new PrintStreamWriter(System.out);
            Formatter.format(
                    new StringReader("aaa { bbbb; ccc;}"),
                    out,
                    new DefaultFormatSettings("settings/containers.json", "settings/handles.json"));
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
            Formatter.format(
                    new StringReader("class HelloWorld {" +
                            "public static void main(String[] args) {" +
                            "System.out.println(\"Hello World!\");" +
                            "}" +
                            "}"),
                    out,
                    new DefaultFormatSettings("settings/containers.json", "settings/handles.json"));
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
            Formatter.format(
                    new StringReader("class HelloWorld {" +
                            "public static void main(String[] args) {" +
                            "System.out.println(\"Hello World!\");" +
                            "}" +
                            "}"),
                    out,
                    new DefaultFormatSettings("settings/containers.json", "settings/handles.json"));
            out.close();
        } catch (FormatterException | WriterException e) {
            fail("");
        }
    }
}
