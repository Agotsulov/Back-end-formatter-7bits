package it.sevenbits.core;

import it.sevenbits.exceptions.ContainerException;
import it.sevenbits.exceptions.ReaderException;
import it.sevenbits.exceptions.FormatSettingsException;
import it.sevenbits.exceptions.FormatterException;
import it.sevenbits.exceptions.HandlerException;
import it.sevenbits.exceptions.WriterException;
import it.sevenbits.streams.IReader;
import it.sevenbits.streams.IWriter;

import java.util.Map;

/**
 *
 */
public class Formatter {

    /*
        TODO: Разобраться с Exceptions
     */

    private IReader in;
    private IWriter out;

    private FormatSettings settings;

    public Formatter(final IReader in, final IWriter out, final FormatSettings settings) {
        this.in = in;
        this.out = out;
        this.settings = settings;
    }

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

    public static void format(final IReader in, final IWriter out, final FormatSettings settings) throws FormatterException {
        new Formatter(in, out, settings).format();
    }
    
    public IReader getIn() {
        return in;
    }

    public void setIn(final IReader in) {
        this.in = in;
    }

    public IWriter getOut() {
        return out;
    }

    public void setOut(final IWriter out) {
        this.out = out;
    }

    public FormatSettings getSettings() {
        return settings;
    }

    public void setSettings(final FormatSettings settings) {
        this.settings = settings;
    }



}
