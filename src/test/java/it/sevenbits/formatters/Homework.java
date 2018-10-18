package it.sevenbits.formatters;

import it.sevenbits.core.Formatter;
import it.sevenbits.exceptions.FormatSettingsException;
import it.sevenbits.streams.OutStream;
import it.sevenbits.streams.streams.PrintStreamOutStream;
import it.sevenbits.streams.string.StringInStream;
import it.sevenbits.streams.string.StringBuilderOutStream;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class Homework {

    @Test
    void first(){
        StringBuilder actual = new StringBuilder();
        try {
            OutStream out = new StringBuilderOutStream(actual);
            Formatter.format(
                    new StringInStream("aaa { bbbb; ccc;}"),
                    out,
                    new DefaultFormatSettings("default.properties", "default.json"));
        } catch (FormatSettingsException e) {
            fail("");
        }
        assertEquals("aaa {\n    bbbb;\n    ccc;\n}", actual.toString());
    }

    @Test
    void firstInConsole(){
        try {
            OutStream out = new PrintStreamOutStream(System.out);
            Formatter.format(
                    new StringInStream("aaa { bbbb; ccc;}"),
                    out,
                    new DefaultFormatSettings("default.properties", "default.json"));
            out.close();
        } catch (FormatSettingsException e) {
            fail("");
        }
    }

    @Test
    void firstHelloWorld(){
        StringBuilder actual = new StringBuilder();
        try {
            OutStream out = new StringBuilderOutStream(actual);
            Formatter.format(
                    new StringInStream("class HelloWorld {" +
                            "public static void main(String[] args) {" +
                            "System.out.println(\"Hello World!\");" +
                            "}" +
                            "}"),
                    out,
                    new DefaultFormatSettings("default.properties", "default.json"));
        } catch (FormatSettingsException e) {
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
            OutStream out = new PrintStreamOutStream(System.out);
            Formatter.format(
                    new StringInStream("class HelloWorld {" +
                            "public static void main(String[] args) {" +
                            "System.out.println(\"Hello World!\");" +
                            "}" +
                            "}"),
                    out,
                    new DefaultFormatSettings("default.properties", "default.json"));
            out.close();
        } catch (FormatSettingsException e) {
            fail("");
        }
    }

}
