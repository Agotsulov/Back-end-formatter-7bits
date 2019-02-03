package it.sevenbits.formatters.state.state.factories;

import it.sevenbits.formatters.state.containers.Container;
import it.sevenbits.formatters.state.containers.ContainerException;
import it.sevenbits.formatters.state.formatsettings.Settings;
import it.sevenbits.formatters.state.formatsettings.LexerSettingsException;
import it.sevenbits.formatters.state.handlers.*;
import it.sevenbits.formatters.state.state.*;
import it.sevenbits.formatters.state.state.engines.SimpleStateEngine;
import it.sevenbits.formatters.state.state.engines.StateEngine;

import java.util.HashMap;
import java.util.Map;

/**
 *
 */
public class FormatterStateEngineFactory implements StateEngineFactory<Handler> {

    private Settings settings;

    /**
     * @param settings Settings
     */
    public FormatterStateEngineFactory(final Settings settings) {
        this.settings = settings;
    }

    /**
     * @return StateMap
     */
    public StateMap getStateMap() {
        Map<Pair<State, String>, State> states = new HashMap<>();

        State defaultState = new State("DEFAULT");
        State needNewLineState = new State("NEED_NEW_LINE");

        states.put(new Pair<>(defaultState, "Word"), defaultState);
        states.put(new Pair<>(defaultState, "Spaces"), defaultState);

        states.put(new Pair<>(defaultState, "NewLine"), defaultState);
        states.put(new Pair<>(needNewLineState, "NewLine"), defaultState);

        states.put(new Pair<>(defaultState, "Comment"), defaultState);

        states.put(new Pair<>(needNewLineState, "Word"), defaultState);
        states.put(new Pair<>(needNewLineState, "Spaces"), defaultState);

        states.put(new Pair<>(needNewLineState, "Comment"), defaultState);

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

        commands.put(new Pair<>(defaultState, "Word"), new Any());
        commands.put(new Pair<>(defaultState, "Comment"), new Comment());
        commands.put(new Pair<>(defaultState, "Spaces"), new Any());

        commands.put(new Pair<>(defaultState, "NewLine"), new Skip());
        commands.put(new Pair<>(needNewLineState, "NewLine"), new Skip());

        commands.put(new Pair<>(needNewLineState, "Word"), new Any());
        commands.put(new Pair<>(needNewLineState, "Spaces"), new Skip());

        commands.put(new Pair<>(defaultState, "CloseBrace"), new CloseBrace());
        commands.put(new Pair<>(defaultState, "OpenBrace"), new OpenBrace());
        commands.put(new Pair<>(defaultState, "Semicolon"), new Semicolon());

        commands.put(new Pair<>(needNewLineState, "CloseBrace"), new CloseBrace());
        commands.put(new Pair<>(needNewLineState, "OpenBrace"), new OpenBrace());
        commands.put(new Pair<>(needNewLineState, "Semicolon"), new Semicolon());

        for (Handler h: commands.values()) {
            try {
                h.start(settings);
            } catch (HandlerException e) {
                throw new StateEngineFactoryException();
            }
        }

        try {
            for (Container container : settings.getContainers().values()) {
                container.load();
            }
        } catch (LexerSettingsException |
            ContainerException e) {
            throw new StateEngineFactoryException();
        }

        return new SimpleStateEngine(getStateMap(), commands);
    }


}
