package it.sevenbits.formatters.state.handlers;

import it.sevenbits.formatters.state.containers.SimpleContainer;
import it.sevenbits.formatters.state.formatsettings.LexerSettings;
import it.sevenbits.formatters.state.formatsettings.LexerSettingsException;

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

}
