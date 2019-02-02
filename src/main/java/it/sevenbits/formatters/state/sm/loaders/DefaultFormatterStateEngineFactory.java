package it.sevenbits.formatters.state.sm.loaders;

import it.sevenbits.formatters.state.containers.Container;
import it.sevenbits.formatters.state.containers.ContainerException;
import it.sevenbits.formatters.state.formatsettings.LexerSettings;
import it.sevenbits.formatters.state.formatsettings.LexerSettingsException;
import it.sevenbits.formatters.state.handlers.*;
import it.sevenbits.formatters.state.sm.Pair;
import it.sevenbits.formatters.state.sm.State;
import it.sevenbits.formatters.state.sm.StateEngine;
import it.sevenbits.formatters.state.sm.StateMap;

import java.util.HashMap;
import java.util.Map;

public class DefaultFormatterStateEngineFactory implements StateEngineFactory<Handler> {

    private LexerSettings lexerSettings;

    public DefaultFormatterStateEngineFactory(LexerSettings lexerSettings) {
        this.lexerSettings = lexerSettings;
    }

    @Override
    public StateMap getStateMap() {
        Map<Pair<State, String>, State> states = new HashMap<>();

        State defaultState = new State("DEFAULT");
        State needNewLineState = new State("NEED_NEW_LINE");

        states.put(new Pair<>(defaultState, "Word"), defaultState);
        states.put(new Pair<>(defaultState, "Spaces"), defaultState);

        states.put(new Pair<>(needNewLineState, "Word"), defaultState);
        states.put(new Pair<>(needNewLineState, "Spaces"), defaultState);

        states.put(new Pair<>(defaultState, "CloseBrace"), needNewLineState);
        states.put(new Pair<>(defaultState, "OpenBrace"), needNewLineState);
        states.put(new Pair<>(defaultState, "Semicolon"), needNewLineState);

        states.put(new Pair<>(needNewLineState, "CloseBrace"), needNewLineState);
        states.put(new Pair<>(needNewLineState, "OpenBrace"), needNewLineState);
        states.put(new Pair<>(needNewLineState, "Semicolon"), needNewLineState);


        return new StateMap(states, defaultState);
    }

    @Override
    public StateEngine<Handler> getStateEngine() throws StateEngineFactoryException {
        Map<Pair<State, String>, Handler> commands = new HashMap<>();

        State defaultState = new State("DEFAULT");
        State needNewLineState = new State("NEED_NEW_LINE");

        commands.put(new Pair<>(defaultState, "Word"), new AnySymbol());
        commands.put(new Pair<>(defaultState, "Spaces"), new AnySymbol());

        commands.put(new Pair<>(needNewLineState, "Word"), new AnySymbol());
        commands.put(new Pair<>(needNewLineState, "Spaces"), new Skip());

        commands.put(new Pair<>(defaultState, "CloseBrace"), new CloseBrace());
        commands.put(new Pair<>(defaultState, "OpenBrace"), new OpenBrace());
        commands.put(new Pair<>(defaultState, "Semicolon"), new Semicolon());

        commands.put(new Pair<>(needNewLineState, "CloseBrace"), new CloseBrace());
        commands.put(new Pair<>(needNewLineState, "OpenBrace"), new OpenBrace());
        commands.put(new Pair<>(needNewLineState, "Semicolon"), new Semicolon());

        for (Handler h: commands.values()) {
            try {
                h.start(lexerSettings);
            } catch (HandlerException e) {
                throw new StateEngineFactoryException();
            }
        }

        try {
            for (Container container : lexerSettings.getContainers().values()) {
                container.load();
            }
        } catch (LexerSettingsException |
            ContainerException e) {
            throw new StateEngineFactoryException();
        }

        return new StateEngine<>(getStateMap(), commands);
    }


}
