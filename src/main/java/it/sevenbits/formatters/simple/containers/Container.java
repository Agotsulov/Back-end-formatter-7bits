package it.sevenbits.formatters.simple.containers;

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
     * Load from "file". Init vars.
     * @throws ContainerException Something has gone wrong
     */
    void load() throws ContainerException;

    /**
     * Load from "file". Init vars.
     * @param file custom filename
     * @throws ContainerException Something has gone wrong
     */
    void load(String file) throws ContainerException;

    /**
     * @param file set current filename
     */
    void setFile(String file);

    /**
     * @return file current filename
     */
    String getFile();
}
