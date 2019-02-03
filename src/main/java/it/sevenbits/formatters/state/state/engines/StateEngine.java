package it.sevenbits.formatters.state.state.engines;


/**
 * @param <T> type object
 */
//Название временное
public interface StateEngine<T> {

    /**
     * @param signal String
     * @return object on current state
     */
    T get(final String signal);
}
