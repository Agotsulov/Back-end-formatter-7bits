package it.sevenbits.formatters;

import it.sevenbits.exceptions.FormatterException;
import it.sevenbits.exceptions.WriterException;
import it.sevenbits.formatsettings.SimpleFormatSettings;
import it.sevenbits.io.IWriter;
import it.sevenbits.io.streams.PrintStreamWriter;
import it.sevenbits.io.string.StringBuilderWriter;
import it.sevenbits.io.string.StringReader;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.fail;

public class Homework2 {

    @Test
    void testOverdoneLogic(){
        StringBuilder actual = new StringBuilder();
        try {
            IWriter out = new StringBuilderWriter(actual);
            new SimpleFormatter().format(
                    new StringReader("{{{{}}}}"),
                    out,
                    new SimpleFormatSettings("settings/containers.json", "settings/handles.json")
            );
        } catch (FormatterException e) {
            fail("");
        }
        assertEquals(
                "{\n" +
                "    {\n        {\n            {\n            }\n        }\n    }\n}", actual.toString());
    }

    @Test
    void overdoneLogicConsole(){
        try {
            IWriter out = new PrintStreamWriter(System.out);
            new SimpleFormatter().format(
                    new StringReader("{{{{}}}}"),
                    out,
                    new SimpleFormatSettings("settings/containers.json", "settings/handles.json")
            );
            out.close();
        } catch (FormatterException | WriterException e) {
            fail("");
        }
    }

    @Test
    void testOne() {
        StringBuilder actual = new StringBuilder();
        try {
            IWriter out = new StringBuilderWriter(actual);
            new SimpleFormatter().format(
                    new StringReader("class HelloWorld {" +
                            "public static void main(String[] args) {" +
                            "System.out.println(\"Hello World!\");" +
                            "}" +
                            "}"),
                    out,
                    new SimpleFormatSettings("settings/containers.json", "settings/handles.json")
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
    void testTwo() {
        StringBuilder actual = new StringBuilder();
        try {
            IWriter out = new StringBuilderWriter(actual);
            new SimpleFormatter().format(
                    new StringReader("    Такие       пробелы                    тоже надо       удалять;"
                            + "Это;" + "cлишко;" + "{сложна;}" + "    тут придеться запоминать слова..."),
                    out,
                    new SimpleFormatSettings("settings/containers.json", "settings/handles.json")
            );
        } catch (FormatterException e) {
            fail("");
        }
        assertEquals("Такие       пробелы                    тоже надо       удалять;\n" +
                "Это;\n" +
                "cлишко;\n" +
                "{\n" +
                "    сложна;\n" +
                "}\n" +
                "тут придеться запоминать слова...", actual.toString());
    }

    @Test
    void testThree() {
        StringBuilder actual = new StringBuilder();
        try {
            IWriter out = new StringBuilderWriter(actual);
            new SimpleFormatter().format(
                    new StringReader("\n\n\n\n\n\n" +
                            "\n     \n         \n \n\n         " +
                            "\n adada; \n\n\n \n\na; aa = 5; \n\n\n\n\n\n"),
                    out,
                    new SimpleFormatSettings("settings/containers.json", "settings/handles.json")
            );
        } catch (FormatterException e) {
            fail("");
        }
        assertEquals("adada;\n" +
                "a;\n" +
                "aa = 5;", actual.toString());
    }
}

