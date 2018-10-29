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
import it.sevenbits.streams.IReader;
import it.sevenbits.streams.IWriter;

import java.util.Map;

/**
 *
 */
public class SimpleFormatter implements Formatter {

    private IReader in;
    private IWriter out;

    private FormatSettings settings;

    /**
     * @param in хз
     * @param out че
     * @param settings писать
     */
    public SimpleFormatter(final IReader in, final IWriter out, final FormatSettings settings) {
        this.in = in;
        this.out = out;
        this.settings = settings;
    }

    /**
     * @throws FormatterException Something has gone wrong
     */
    public void format() throws FormatterException {
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
            while (in.hasNext()) {
                char symbol = in.next();
                for (Handler h : handlers.keySet()) {
                    if (h.validate(symbol)) {
                        try {
                            out.write(h.handle());
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

    @Override
    public void format(final IReader reader, final IWriter writer) throws FormatterException {
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

    @Override
    public IReader getReader() {
        return in;
    }

    @Override
    public void setReader(final IReader reader) {
        in = reader;
    }

    @Override
    public IWriter getWriter() {
        return out;
    }

    @Override
    public void setWriter(final IWriter writer) {
        out = writer;
    }


    public FormatSettings getSettings() {
        return settings;
    }

    public void setSettings(final FormatSettings settings) {
        this.settings = settings;
    }

}
