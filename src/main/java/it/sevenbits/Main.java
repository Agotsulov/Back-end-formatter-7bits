package it.sevenbits;

import it.sevenbits.core.Formatter;
import it.sevenbits.core.FormatterException;
import it.sevenbits.formatters.state.StateLexerFormatter;
import it.sevenbits.formatters.state.formatsettings.SimpleSettings;
import it.sevenbits.formatters.state.lexers.factories.StateLexerFactory;
import it.sevenbits.formatters.state.state.factories.FormatterStateEngineFactory;
import it.sevenbits.io.ReaderException;
import it.sevenbits.io.WriterException;
import it.sevenbits.io.file.FileReader;
import it.sevenbits.io.file.FileWriter;

/**
 *
 */
public final class Main {

    private Main() {}

    /**
     * @param args args[0] - in path file, args[1] out path file
     */
    public static void main(final String[] args) {
        try {
            FileWriter fileWriter = new FileWriter(args[0]);
            FileReader fileReader = new FileReader(args[1]);

            Formatter formatter = new StateLexerFormatter(new StateLexerFactory(),
                    new FormatterStateEngineFactory(
                            new SimpleSettings("settings/lexer/containers.json"))
            );
            formatter.format(fileReader, fileWriter);

        } catch (WriterException | ReaderException | FormatterException e) {
            e.printStackTrace();
        }
    }

}
