package it.sevenbits.formatters.state.sm.factories;

import it.sevenbits.formatters.state.sm.Pair;
import it.sevenbits.formatters.state.sm.State;
import it.sevenbits.formatters.state.sm.StateEngine;
import it.sevenbits.formatters.state.sm.StateMap;

import java.util.HashMap;
import java.util.Map;

public class DefaultLexerStateEngineFactory implements StateEngineFactory {
    @Override
    public StateMap getStateMap() {

        Map<Pair<State, String>, State> states = new HashMap<>();

        State listenState = new State("LISTEN");
        State stubSuspicion = new State("TRASH_SUSPICION");
        State defaultState = new State("IGNORE");

        states.put(new Pair<>(defaultState, "MESSAGE_START"), listenState);
        states.put(new Pair<>(defaultState, "TRASH"), defaultState);
        states.put(new Pair<>(defaultState, "MESSAGE"), defaultState);
        states.put(new Pair<>(defaultState, "MESSAGE_FINISH"), defaultState);

        states.put(new Pair<>(listenState, "MESSAGE"), listenState);
        states.put(new Pair<>(listenState, "MESSAGE_START"), listenState);
        states.put(new Pair<>(listenState, "MESSAGE_FINISH"), defaultState);
        states.put(new Pair<>(listenState, "TRASH"), stubSuspicion);

        states.put(new Pair<>(stubSuspicion, "MESSAGE"), stubSuspicion);
        states.put(new Pair<>(stubSuspicion, "MESSAGE_START"), stubSuspicion);
        states.put(new Pair<>(stubSuspicion, "MESSAGE_FINISH"), defaultState);
        states.put(new Pair<>(stubSuspicion, "TRASH"), defaultState);

        return null;
    }

    @Override
    public StateEngine getStateEngine() {
        return null;
    }
}
