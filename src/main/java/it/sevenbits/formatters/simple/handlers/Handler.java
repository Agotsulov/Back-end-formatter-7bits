package it.sevenbits.formatters.simple.handlers;

import it.sevenbits.formatters.simple.formatsettings.FormatSettings;

/**
 *
 */
public interface Handler {

    /**
     * In this method you can get containers and other handles
     * @param settings Current FormatSettings
     * @throws HandlerException Something has gone wrong
     */
    void start(FormatSettings settings) throws HandlerException;

    /**
     * Check validate symbol to this handler
     * @param symbol character
     * @return true if it valid
     */
    boolean validate(char symbol);

    /**
     * Call if validate true
     * @return character formatting result
     * @throws HandlerException Something has gone wrong
     */
    String handle() throws HandlerException;

}
