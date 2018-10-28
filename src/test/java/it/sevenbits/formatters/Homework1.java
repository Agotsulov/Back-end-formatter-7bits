package it.sevenbits.formatters;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.google.gson.stream.JsonReader;
import it.sevenbits.core.Formatter;
import it.sevenbits.exceptions.FormatterException;
import it.sevenbits.streams.OutStream;
import it.sevenbits.streams.streams.PrintStreamOutStream;
import it.sevenbits.streams.string.StringInStream;
import it.sevenbits.streams.string.StringBuilderOutStream;
import org.junit.jupiter.api.Test;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

public class Homework1 {

    @Test
    void first(){
        StringBuilder actual = new StringBuilder();
        try {
            OutStream out = new StringBuilderOutStream(actual);
            Formatter.format(
                    new StringInStream("aaa { bbbb; ccc;}"),
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
            OutStream out = new PrintStreamOutStream(System.out);
            Formatter.format(
                    new StringInStream("aaa { bbbb; ccc;}"),
                    out,
                    new DefaultFormatSettings("settings/containers.json", "settings/handles.json"));
            out.close();
        } catch (FormatterException e) {
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
            OutStream out = new PrintStreamOutStream(System.out);
            Formatter.format(
                    new StringInStream("class HelloWorld {" +
                            "public static void main(String[] args) {" +
                            "System.out.println(\"Hello World!\");" +
                            "}" +
                            "}"),
                    out,
                    new DefaultFormatSettings("settings/containers.json", "settings/handles.json"));
            out.close();
        } catch (FormatterException  e) {
            fail("");
        }
    }
}
