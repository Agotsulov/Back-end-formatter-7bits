package it.sevenbits.formatters;

import it.sevenbits.core.FormatterException;
import it.sevenbits.formatters.simple.formatsettings.SimpleFormatSettings;
import it.sevenbits.formatters.simple.SimpleFormatter;
import it.sevenbits.io.Writer;
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
            Writer out = new StringBuilderWriter(actual);
            new SimpleFormatter(new SimpleFormatSettings("settings/containers.json", "settings/handles.json")).format(
                    new StringReader("{{{{}}}}"),
                    out
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
            PrintStreamWriter out = new PrintStreamWriter(System.out);
            new SimpleFormatter(new SimpleFormatSettings("settings/containers.json", "settings/handles.json")).format(
                    new StringReader("{{{{}}}}"),
                    out
            );
            out.close();
        } catch (FormatterException e) {
            fail("");
        }
    }

    @Test
    void testOne() {
        StringBuilder actual = new StringBuilder();
        try {
            Writer out = new StringBuilderWriter(actual);
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
    void testTwo() {
        StringBuilder actual = new StringBuilder();
        try {
            Writer out = new StringBuilderWriter(actual);
            new SimpleFormatter(new SimpleFormatSettings("settings/containers.json", "settings/handles.json")).format(
                    new StringReader("    Такие       пробелы                    тоже надо       удалять;"
                            + "Это;" + "cлишко;" + "{сложна;}" + "    тут придеться запоминать слова..."),
                    out
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
            Writer out = new StringBuilderWriter(actual);
            new SimpleFormatter(new SimpleFormatSettings("settings/containers.json", "settings/handles.json")).format(
                    new StringReader("\n\n\n\n\n\n" +
                            "\n     \n         \n \n\n         " +
                            "\n adada; \n\n\n \n\na; aa = 5; \n\n\n\n\n\n"),
                    out
            );
        } catch (FormatterException e) {
            fail("");
        }
        assertEquals("adada;\n" +
                "a;\n" +
                "aa = 5;", actual.toString());
    }
}

