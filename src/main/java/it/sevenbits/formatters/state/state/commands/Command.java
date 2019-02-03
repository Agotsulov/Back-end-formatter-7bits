package it.sevenbits.formatters.state.state.commands;

import it.sevenbits.formatters.state.lexers.StateLexer;

/**
 *
 */
public interface Command {

    /**
     * @param lexer Current lexer
     */
    void execute(StateLexer lexer);

}
