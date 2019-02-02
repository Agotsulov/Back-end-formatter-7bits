package it.sevenbits.formatters.state.formatsettings;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.google.gson.stream.JsonReader;
import it.sevenbits.formatters.state.containers.Container;
import it.sevenbits.formatters.state.handlers.Handler;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

/**
 *
 */
public class SimpleLexerSettings implements LexerSettings {

    /**
     * Class helper for loading containers from json
     */
    private class ContainerJSON { //TODO: rename
        private String name;
        private String path;
        private String file;

        ContainerJSON(final String name, final String path, final String file) {
            this.name = name;
            this.path = path;
            this.file = file;
        }

        public String getName() {
            return name;
        }

        public void setName(final String name) {
            this.name = name;
        }

        public String getPath() {
            return path;
        }

        public void setPath(final String path) {
            this.path = path;
        }

        public String getFile() {
            return file;
        }

        public void setFile(final String file) {
            this.file = file;
        }
    }

    private Map<Handler, Boolean> handlers = null;
    private Map<String, Container> containers = null;

    private String containersFile;
    private String handlersFile;

    /**
     * @param containersFile path to json file
     * @param handlersFile path to json file
     */
    public SimpleLexerSettings(final String containersFile, final String handlersFile) {
        this.containersFile = containersFile;
        this.handlersFile = handlersFile;
    }

    /**
     * @param cFile path to json file
     * @return Map key - name value - container
     * @throws LexerSettingsException Something has gone wrong
     */
    private Map<String, Container> loadContainers(final String cFile) throws LexerSettingsException {
        Map<String, Container> result = new HashMap<>();

        Gson json = new Gson();
        JsonReader jReader = null;

        try {
            jReader = new JsonReader(new FileReader(new File(cFile)));
        } catch (FileNotFoundException e) {
            throw new LexerSettingsException();
        }

        List<ContainerJSON> data = json.fromJson(jReader, new TypeToken<List<ContainerJSON>>() { } .getType());

        for (ContainerJSON d : data) {
            Class<?> c = null;
            try {
                c = Class.forName(d.path);
            } catch (ClassNotFoundException e) {
                throw new LexerSettingsException();
            }

            Constructor<?> constructor = null;
            try {
                constructor = c.getConstructor();
            } catch (NoSuchMethodException e) {
                throw new LexerSettingsException();
            }

            Object instance = null;
            try {
                assert constructor != null;
                instance = constructor.newInstance();
            } catch (InstantiationException | InvocationTargetException | IllegalAccessException e) {
                throw new LexerSettingsException();
            }

            Container container = (Container) instance;
            container.setFile(d.file);
            result.put(d.name, container);
        }

        return result;
    }

    /**
     * @param hFile path to json file
     * @return Map key - handler value - boolean if true it terminal handler
     * @throws LexerSettingsException Something has gone wrong
     */
    private Map<Handler, Boolean> loadHandlers(final String hFile) throws LexerSettingsException {
        Map<Handler, Boolean> result = new LinkedHashMap<>(); //Надо сохранять порядок
        Gson json = new Gson();
        JsonReader jReader = null;
        try {
            jReader = new JsonReader(new FileReader(new File(hFile)));
        } catch (FileNotFoundException e) {
            throw new LexerSettingsException();
        }
        Map<String, Boolean> handlers = json.fromJson(jReader, new TypeToken<Map<String, Boolean>>() { } .getType());
        for (String handler : handlers.keySet()) {
            Class<?> c = null;
            try {
                c = Class.forName(handler);
            } catch (ClassNotFoundException e) {
                throw new LexerSettingsException();
            }

            Constructor<?> constructor = null;
            try {
                constructor = c.getConstructor();
            } catch (NoSuchMethodException e) {
                throw new LexerSettingsException();
            }

            Object instance = null;
            try {
                assert constructor != null;
                instance = constructor.newInstance();
            } catch (InstantiationException | InvocationTargetException | IllegalAccessException e) {
                throw new LexerSettingsException();
            }

            result.put((Handler) instance, handlers.get(handler));
        }



        return result;
    }


    //JavaDoc же должно наследоваться и быть не обязательным
    /**
     * @return Map key - handler value - boolean if true it terminal handler
     * @throws LexerSettingsException Something has gone wrong
     */
    public Map<Handler, Boolean> getHandlers() throws LexerSettingsException {
        if (handlers == null) {
            handlers = loadHandlers(getHandlersFile());
        }
        return handlers;
    }

    /**
     * @return Map key - name value - container
     * @throws LexerSettingsException Something has gone wrong
     */
    public Map<String, Container> getContainers() throws LexerSettingsException {
        if (containers == null) {
            containers = loadContainers(getContainersFile());
        }
        return containers;
    }

    public String getContainersFile() {
        return containersFile;
    }

    public void setContainersFile(final String containersFile) {
        this.containersFile = containersFile;
    }

    public String getHandlersFile() {
        return handlersFile;
    }

    public void setHandlersFile(final String handlersFile) {
        this.handlersFile = handlersFile;
    }
}