package it.sevenbits.formatters.state.state.engines;

import it.sevenbits.formatters.state.state.Pair;
import it.sevenbits.formatters.state.state.State;
import it.sevenbits.formatters.state.state.StateMap;

import java.util.Map;

/**
 * @param <T> type
 */
public class SimpleStateEngine<T> implements StateEngine<T> {

    private Map<Pair<State, String>, T> commands;

    private State current;

    private StateMap stateMap;

    /**
     * @param stateMap stateMap
     * @param commands commands
     */
    public SimpleStateEngine(final StateMap stateMap, final Map<Pair<State, String>, T> commands) {
        this.commands = commands;
        this.stateMap = stateMap;
        current = stateMap.getStartState();
    }


    @Override
    public T get(final String signal) {
        T t = commands.get(new Pair<>(current, signal));
        if (t != null) {
            current = stateMap.getNextState(current, signal);
        } else {
            t = commands.get(new Pair<>(current, ""));
            current = stateMap.getNextState(current, "");
        }

        return t;
    }
}
