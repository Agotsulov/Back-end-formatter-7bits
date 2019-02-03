package it.sevenbits.formatters.state.sm;

import java.util.HashMap;
import java.util.Map;

public class StateMap {
    private final State defaultState;

    private State anyState = new State("");

    private final Map<Pair<State, String>, State> states;

    public StateMap(final Map<Pair<State, String>, State> states, final State defaultState) {
        this.states = states;
        this.defaultState = defaultState;
    }

    public State getStartState() {
        return defaultState;
    }

    public State getNextState(final State state, final String signal) {
        return states.getOrDefault(new Pair<>(state, signal), anyState);
    }
}
