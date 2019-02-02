package it.sevenbits.formatters.state.sm.loaders;

import it.sevenbits.formatters.state.sm.StateEngine;
import it.sevenbits.formatters.state.sm.StateMap;

public interface StateEngineFactory<T> {

    StateMap getStateMap() throws StateEngineFactoryException;

    StateEngine<T> getStateEngine() throws StateEngineFactoryException;
}
