package it.sevenbits.formatters.state.state;

import java.util.Map;

/**
 *
 */
public class StateMap {
    private State defaultState;

    private Map<Pair<State, String>, State> states;

    /**
     * @param states Map<Pair<State, String>, State>
     * @param defaultState State
     */
    public StateMap(final Map<Pair<State, String>, State> states, final State defaultState) {
        this.states = states;
        this.defaultState = defaultState;
    }

    public State getStartState() {
        return defaultState;
    }

    /**
     * @param state current state
     * @param signal signal
     * @return next state
     */
    public State getNextState(final State state, final String signal) {
        return states.getOrDefault(new Pair<>(state, signal), defaultState);
    }
}
