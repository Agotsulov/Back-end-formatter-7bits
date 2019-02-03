package it.sevenbits.formatters.state.handlers;

import it.sevenbits.formatters.state.formatsettings.Settings;

/**
 *
 */
public interface Handler {

    /**
     * In this method you can get containers and other handles
     * @param settings Current Settings
     * @throws HandlerException Something has gone wrong
     */
    void start(Settings settings) throws HandlerException;

    /**
     * Call if validate true
     * @param lexeme String
     * @return character formatting result
     * @throws HandlerException Something has gone wrong
     */
    String handle(String lexeme) throws HandlerException;

}
