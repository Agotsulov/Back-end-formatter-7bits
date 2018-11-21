package it.sevenbits;

import it.sevenbits.core.Formatter;
import it.sevenbits.core.FormatterException;
import it.sevenbits.formatters.lexer.formatters.LexerFormatter;
import it.sevenbits.formatters.lexer.lexerfactories.SimpleLexerFactory;
import it.sevenbits.io.ReaderException;
import it.sevenbits.io.WriterException;
import it.sevenbits.io.file.FileReader;
import it.sevenbits.io.file.FileWriter;

public class Main {

    public static void main(final String[] args) {
        try {
            FileWriter fileWriter = new FileWriter(args[0]);
            FileReader fileReader = new FileReader(args[1]);

            Formatter formatter = new LexerFormatter(new SimpleLexerFactory());

            formatter.format(fileReader, fileWriter);

        } catch (WriterException | ReaderException | FormatterException e) {
            e.printStackTrace();
        }
    }

}
