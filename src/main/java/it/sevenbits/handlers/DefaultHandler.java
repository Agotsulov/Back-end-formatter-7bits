package it.sevenbits.handlers;

import it.sevenbits.containers.SimpleContainer;
import it.sevenbits.core.FormatSettings;
import it.sevenbits.core.Handler;
import it.sevenbits.exceptions.FormatSettingsException;
import it.sevenbits.exceptions.HandlerException;
import it.sevenbits.formatters.DefaultFormatSettings;

public abstract class DefaultHandler implements Handler {

    protected SimpleContainer format;

    @Override
    public void start(FormatSettings settings) throws HandlerException {
        try {
            format = (SimpleContainer) settings.getContainers().get("SimpleContainer");
        } catch (FormatSettingsException e) {
            throw new HandlerException();
        }
    }

}
