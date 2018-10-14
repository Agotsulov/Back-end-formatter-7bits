package it.sevenbits.handlers;

import it.sevenbits.core.FormatSettings;
import it.sevenbits.core.Handler;
import it.sevenbits.formatters.DefaultFormatSettings;

public abstract class DefaultHandler implements Handler {

    protected DefaultFormatSettings format;

    @Override
    public void start(FormatSettings format) {
        this.format = (DefaultFormatSettings) format;
    }

}
