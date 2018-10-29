package it.sevenbits.handlers;

import it.sevenbits.containers.SimpleContainer;
import it.sevenbits.core.FormatSettings;
import it.sevenbits.core.Handler;
import it.sevenbits.exceptions.FormatSettingsException;
import it.sevenbits.exceptions.HandlerException;

/**
 *
 */
public abstract class SimpleHandler implements Handler {

    private SimpleContainer format; //А почему нельзя protected? А то не очень удобно

    @Override
    public void start(final FormatSettings settings) throws HandlerException {
        try {
            format = (SimpleContainer) settings.getContainers().get("SimpleContainer");
        } catch (FormatSettingsException e) {
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
