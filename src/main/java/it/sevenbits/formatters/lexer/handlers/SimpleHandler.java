package it.sevenbits.formatters.lexer.handlers;

import it.sevenbits.formatters.lexer.formatsettings.LexerSettings;
import it.sevenbits.formatters.lexer.formatsettings.LexerSettingsException;
import it.sevenbits.formatters.simple.containers.SimpleContainer;

/**
 *
 */
public abstract class SimpleHandler implements Handler {

    private SimpleContainer format; //А почему нельзя protected? А то не очень удобно

    @Override
    public void start(final LexerSettings settings) throws HandlerException {
        try {
            format = (SimpleContainer) settings.getContainers().get("SimpleContainer");
        } catch (LexerSettingsException e) {
            throw new HandlerException();
        }
    }

    public SimpleContainer getFormat() {
        return format;
    }

    public void setFormat(final SimpleContainer format) {
        this.format = format;
    }
}
