package it.sevenbits.formatters.state.formatsettings;

import it.sevenbits.formatters.state.containers.Container;
import it.sevenbits.formatters.state.handlers.Handler;

import java.util.Map;

/**
 *
 */
public interface LexerSettings {

    /**
     * @return Map key - name value - container
     * @throws LexerSettingsException Something has gone wrong
     */
    Map<String, Container> getContainers() throws LexerSettingsException;

}
