package it.sevenbits.formatters.lexer;

import it.sevenbits.core.Formatter;
import it.sevenbits.core.FormatterException;
import it.sevenbits.formatters.lexer.formatsettings.LexerSettings;
import it.sevenbits.formatters.lexer.formatsettings.LexerSettingsException;
import it.sevenbits.formatters.lexer.handlers.Handler;
import it.sevenbits.formatters.lexer.handlers.HandlerException;
import it.sevenbits.formatters.lexer.lexerfactories.LexerFactory;
import it.sevenbits.formatters.lexer.lexerfactories.LexerFactoryException;
import it.sevenbits.formatters.lexer.lexers.Lexer;
import it.sevenbits.formatters.lexer.tokens.Token;
import it.sevenbits.formatters.simple.containers.Container;
import it.sevenbits.formatters.simple.containers.ContainerException;
import it.sevenbits.io.Reader;
import it.sevenbits.io.Writer;
import it.sevenbits.io.WriterException;
import it.sevenbits.other.IteratorException;

import java.util.Map;

/**
 *
 */
public class LexerFormatter implements Formatter {

    private LexerFactory lexerFactory;
    private LexerSettings settings;

    /**
     * @param lexerFactory LexerFactory
     * @param settings LexerSettings
     */
    public LexerFormatter(final LexerFactory lexerFactory, final LexerSettings settings) {
        this.lexerFactory = lexerFactory;
        this.settings = settings;
    }

    /**
     * @param reader Reader
     * @param writer Writer
     * @throws FormatterException Something has gone wrong
     */
    @Override
    public void format(final Reader reader, final Writer writer) throws FormatterException {
        Map<Handler, Boolean> handlers = null;
        Map<String, Container> containers = null;
        try {
            Lexer lexer = lexerFactory.createLexer(reader);
            handlers = settings.getHandlers();
            containers = settings.getContainers();

            if ((handlers == null) || (containers == null)) {
                throw new FormatterException();
            }

            for (Handler handler : handlers.keySet()) {
                handler.start(settings);
            }

            for (Container container : containers.values()) {
                container.load();
            }

            while (lexer.hasNext()) {
                Token token = lexer.next();

                for (Handler h : handlers.keySet()) {
                    if (h.validate(token)) {
                        writer.write(h.handle());
                        if (handlers.get(h)) {
                            break;
                        }
                    }
                }
            }

        } catch (IteratorException
                | LexerSettingsException
                | WriterException
                | HandlerException
                | LexerFactoryException
                | ContainerException e) {
            throw new FormatterException();
        }
    }
}
