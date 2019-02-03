package it.sevenbits.formatters.state.state;

import java.util.Objects;

/**
 *
 */
public class State {

    private final String name;

    /**
     * @param name State name
     */
    public State(final String name) {
        this.name = name;
    }

    @Override
    public boolean equals(final Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        State state = (State) o;
        return Objects.equals(name, state.name);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name);
    }
}