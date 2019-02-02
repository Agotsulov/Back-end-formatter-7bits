package it.sevenbits.formatters.state.sm;


import java.util.Map;

//Название временное
public interface StateEngine<T> {

    T get(final String signal);
}
