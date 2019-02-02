package it.sevenbits.formatters.state.containers;

/**
 *
 */
public interface Container {



    /*
    Я понимаю что методы load, setFile и getFile не нужны и их бы разделить на разные интерфейсы.
    Но я не знаю как тогда загружать это из json. TODO: Разделить load и file методы на разные интерфейсы.
     */

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
