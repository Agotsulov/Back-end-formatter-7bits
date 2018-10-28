package it.sevenbits.core;

import it.sevenbits.exceptions.ContainerException;

public interface Container {

    String get(String key) throws ContainerException;

    void set(String key, String value) throws ContainerException;


    void load() throws ContainerException;

    void load(String file) throws ContainerException;

    void setFile(String file);

    String getFile();
}
