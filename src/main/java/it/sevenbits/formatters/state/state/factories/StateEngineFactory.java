package it.sevenbits.formatters.state.state.factories;

import it.sevenbits.formatters.state.state.engines.StateEngine;

/**
 * @param <T> type of command what return StateEngine
 */
public interface StateEngineFactory<T> {

    /**
     * @return StateEngine<T>
     * @throws StateEngineFactoryException Something has gone wrong
     */
    StateEngine<T> getStateEngine() throws StateEngineFactoryException;
}
