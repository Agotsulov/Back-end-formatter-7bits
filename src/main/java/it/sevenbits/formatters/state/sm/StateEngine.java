package it.sevenbits.formatters.state.sm;


import java.util.Map;

//Название временное
public class StateEngine<T> {

    private Map<Pair<State, String>, T> commands;

    private State current;

    private StateMap stateMap;

    public StateEngine(final StateMap stateMap, final Map<Pair<State, String>, T> commands) {
        this.commands = commands;
        this.stateMap = stateMap;
        current = stateMap.getStartState();
    }

    public T get(final String signal) {
        T t = commands.get(new Pair<>(current, signal));
        current = stateMap.getNextState(current, signal);
        return t;
    }
}
