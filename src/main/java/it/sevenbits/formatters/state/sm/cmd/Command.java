package it.sevenbits.formatters.state.sm.cmd;

import it.sevenbits.formatters.state.lexers.StateLexer;

public interface Command {

    void execute(StateLexer lexer);

}
