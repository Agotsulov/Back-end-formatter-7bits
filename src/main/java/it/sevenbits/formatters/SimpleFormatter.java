package it.sevenbits.formatters;

import it.sevenbits.core.Container;
import it.sevenbits.core.FormatSettings;
import it.sevenbits.core.Formatter;
import it.sevenbits.core.Handler;
import it.sevenbits.exceptions.ContainerException;
import it.sevenbits.exceptions.FormatterException;
import it.sevenbits.exceptions.FormatSettingsException;
import it.sevenbits.exceptions.HandlerException;
import it.sevenbits.exceptions.ReaderException;
import it.sevenbits.exceptions.WriterException;
import it.sevenbits.io.IReader;
import it.sevenbits.io.IWriter;

import java.util.Map;

/**
 *
 */
public class SimpleFormatter implements Formatter {

    @Override
    public void format(final IReader reader, final IWriter writer, FormatSettings settings) throws FormatterException {
        Map<Handler, Boolean> handlers = null;
        Map<String, Container> containers = null;
        try {
            handlers = settings.getHandlers();
            containers = settings.getContainers();
        } catch (FormatSettingsException e) {
            throw new FormatterException();
        }

        if ((handlers == null) || (containers == null)) {
            throw new FormatterException();
        }

        for (Handler handler: handlers.keySet()) {
            try {
                handler.start(settings);
            } catch (HandlerException e) {
                throw new FormatterException();
            }
        }

        for (Container container: containers.values()) {
            try {
                container.load();
            } catch (ContainerException e) {
                throw new FormatterException();
            }
        }

        try {
            while (reader.hasNext()) {
                char symbol = reader.next();
                for (Handler h : handlers.keySet()) {
                    if (h.validate(symbol)) {
                        try {
                            writer.write(h.handle());
                        } catch (HandlerException | WriterException e) {
                            throw new FormatterException();
                        }
                        if (handlers.get(h)) {
                            break;
                        }
                    }
                }
            }
        } catch (ReaderException e) {
            throw new FormatterException();
        }
    }

}
