package it.sevenbits.formatters.lexer.formatsettings;

import it.sevenbits.formatters.lexer.handlers.Handler;
import it.sevenbits.formatters.simple.containers.Container;

import java.util.Map;

/**
 *
 */
public interface LexerSettings {


    /**
     * @return Map key - handler value - boolean if true it terminal handler
     * @throws LexerSettingsException Something has gone wrong
     */
    Map<Handler, Boolean> getHandlers() throws LexerSettingsException;

    /**
     * @return Map key - name value - container
     * @throws LexerSettingsException Something has gone wrong
     */
    Map<String, Container> getContainers() throws LexerSettingsException;

}
