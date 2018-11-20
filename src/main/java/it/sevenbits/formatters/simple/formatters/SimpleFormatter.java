package it.sevenbits.formatters.simple.formatters;

import it.sevenbits.formatters.simple.containers.Container;
import it.sevenbits.core.Formatter;
import it.sevenbits.formatters.simple.handlers.Handler;
import it.sevenbits.formatters.simple.containers.ContainerException;
import it.sevenbits.core.FormatterException;
import it.sevenbits.formatters.simple.formatsettings.FormatSettingsException;
import it.sevenbits.formatters.simple.handlers.HandlerException;
import it.sevenbits.io.ReaderException;
import it.sevenbits.io.Writer;
import it.sevenbits.io.WriterException;
import it.sevenbits.formatters.simple.formatsettings.FormatSettings;
import it.sevenbits.io.IReader;

import java.util.Map;

/**
 *
 */
public class SimpleFormatter implements Formatter {

    private FormatSettings settings;

    public SimpleFormatter(FormatSettings formatSettings) {
        settings = formatSettings;
    }

    @Override
    public void format(final IReader reader, final Writer writer) throws FormatterException {
        Map<Handler, Boolean> handlers = null;
        Map<String, Container> containers = null;
        try {
            handlers = settings.getHandlers();
            containers = settings.getContainers();

            if ((handlers == null) || (containers == null)) {
                throw new FormatterException();
            }

            for (Handler handler: handlers.keySet()) {
                handler.start(settings);
            }

            for (Container container: containers.values()) {
                container.load();
            }

            while (reader.hasNext()) {
                char symbol = reader.next();
                for (Handler h : handlers.keySet()) {
                    if (h.validate(symbol)) {
                        writer.write(h.handle());
                        if (handlers.get(h)) {
                            break;
                        }
                    }
                }
            }
        } catch (ReaderException |FormatSettingsException | HandlerException | WriterException | ContainerException e) {
            throw new FormatterException();
        }
    }

}
