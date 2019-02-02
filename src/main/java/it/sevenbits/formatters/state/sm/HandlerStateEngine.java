package it.sevenbits.formatters.state.sm;

import it.sevenbits.formatters.state.handlers.Handler;

import java.util.Map;

public class HandlerStateEngine implements StateEngine<Handler> {

    private Map<Pair<State, String>, Handler> commands;

    private State current;

    private StateMap stateMap;

    public HandlerStateEngine(final StateMap stateMap, final Map<Pair<State, String>, Handler> commands) {
        this.commands = commands;
        this.stateMap = stateMap;
        current = stateMap.getStartState();
    }


    @Override
    public Handler get(String signal) {
        Handler h = commands.get(new Pair<>(current, signal));
        current = stateMap.getNextState(current, signal);
        return h;
    }
}
