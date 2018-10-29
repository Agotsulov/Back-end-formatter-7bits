package it.sevenbits.core;

import it.sevenbits.exceptions.FormatSettingsException;

import java.util.Map;

/**
 *
 */
public interface FormatSettings  {


    /**
     * @return Map key - handler value - boolean if true it terminal handler
     * @throws FormatSettingsException Something has gone wrong
     */
    Map<Handler, Boolean> getHandlers() throws FormatSettingsException;

    /**
     * @return Map key - name value - container
     * @throws FormatSettingsException Something has gone wrong
     */
    Map<String, Container> getContainers() throws FormatSettingsException;

}
