package it.sevenbits.containers;

import it.sevenbits.exceptions.ContainerException;

public class NewLineFlagContainer extends AContainer {

    public boolean needNewLine = false;

    @Override
    public String get(String key) throws ContainerException {
        return null;
    }

    @Override
    public void set(String key, String value) throws ContainerException {

    }

    @Override
    public void load() throws ContainerException {

    }

    @Override
    public void load(String file) throws ContainerException {

    }
}
