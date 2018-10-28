package it.sevenbits.core;

import it.sevenbits.exceptions.FormatSettingsException;

import java.util.Map;

public interface FormatSettings  {

    Map<Handler, Boolean> getHandlers() throws FormatSettingsException;

    Map<String, Container> getContainers() throws FormatSettingsException;

}
