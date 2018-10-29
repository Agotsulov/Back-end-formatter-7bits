package it.sevenbits.core;

import it.sevenbits.exceptions.ContainerException;

/**
 *
 */
public interface Container {

    /**
     * @param key название переменной
     * @return значение переменной
     * @throws ContainerException что то пошло не так
     */
    String get(String key) throws ContainerException;

    /**
     * @param key название переменной
     * @param value значение
     * @throws ContainerException что то пошло не так
     */
    void set(String key, String value) throws ContainerException;


    /**
     * @throws ContainerException
     */
    void load() throws ContainerException;

    /**
     * @param file
     * @throws ContainerException
     */
    void load(String file) throws ContainerException;

    /**
     * @param file
     */
    void setFile(String file);

    /**
     * @return file
     */
    String getFile();
}
