package it.sevenbits.formatters.state.sm.factories;

import it.sevenbits.formatters.state.sm.StateEngine;
import it.sevenbits.formatters.state.sm.StateMap;

public interface StateEngineFactory<T> {

    StateEngine<T> getStateEngine() throws StateEngineFactoryException;
}
