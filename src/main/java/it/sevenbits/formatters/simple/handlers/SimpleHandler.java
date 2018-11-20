package it.sevenbits.formatters.simple.handlers;

import it.sevenbits.formatters.simple.containers.SimpleContainer;
import it.sevenbits.formatters.simple.formatsettings.FormatSettings;
import it.sevenbits.formatters.simple.formatsettings.FormatSettingsException;

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
