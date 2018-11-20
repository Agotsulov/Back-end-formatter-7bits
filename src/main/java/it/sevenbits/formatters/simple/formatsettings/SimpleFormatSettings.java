package it.sevenbits.formatters.simple.formatsettings;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.google.gson.stream.JsonReader;
import it.sevenbits.formatters.simple.containers.Container;
import it.sevenbits.formatters.simple.handlers.Handler;

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
public class SimpleFormatSettings implements FormatSettings {

    /**
     * Class helper for loading containers from json
     */
    private class ThreeString { //TODO: rename
        private String name;
        private String path;
        private String file;

        ThreeString(final String name, final String path, final String file) {
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
    public SimpleFormatSettings(final String containersFile, final String handlersFile) {
        this.containersFile = containersFile;
        this.handlersFile = handlersFile;
    }

    /**
     * @param cFile path to json file
     * @return Map key - name value - container
     * @throws FormatSettingsException Something has gone wrong
     */
    private Map<String, Container> loadContainers(final String cFile) throws FormatSettingsException {
        Map<String, Container> result = new HashMap<>();

        Gson json = new Gson();
        JsonReader jReader = null;

        try {
            jReader = new JsonReader(new FileReader(new File(cFile)));
        } catch (FileNotFoundException e) {
            throw new FormatSettingsException();
        }

        List<ThreeString> data = json.fromJson(jReader, new TypeToken<List<ThreeString>>() { } .getType());

        for (ThreeString d : data) {
            Class<?> c = null;
            try {
                c = Class.forName(d.path);
            } catch (ClassNotFoundException e) {
                throw new FormatSettingsException();
            }

            Constructor<?> constructor = null;
            try {
                assert c != null;
                constructor = c.getConstructor();
            } catch (NoSuchMethodException e) {
                throw new FormatSettingsException();
            }

            Object instance = null;
            try {
                assert constructor != null;
                instance = constructor.newInstance();
            } catch (InstantiationException | InvocationTargetException | IllegalAccessException e) {
                throw new FormatSettingsException();
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
     * @throws FormatSettingsException Something has gone wrong
     */
    private Map<Handler, Boolean> loadHandlers(final String hFile) throws FormatSettingsException {
        Map<Handler, Boolean> result = new LinkedHashMap<>(); //Надо сохранять порядок
        Gson json = new Gson();
        JsonReader jReader = null;
        try {
            jReader = new JsonReader(new FileReader(new File(hFile)));
        } catch (FileNotFoundException e) {
            throw new FormatSettingsException();
        }
        Map<String, Boolean> handlers = json.fromJson(jReader, new TypeToken<Map<String, Boolean>>() { } .getType());
        for (String handler : handlers.keySet()) {
            Class<?> c = null;
            try {
                c = Class.forName(handler);
            } catch (ClassNotFoundException e) {
                throw new FormatSettingsException();
            }

            Constructor<?> constructor = null;
            try {
                assert c != null; //Че за assert и зачем IDEA предлагает их ставить?
                constructor = c.getConstructor();
            } catch (NoSuchMethodException e) {
                throw new FormatSettingsException();
            }

            Object instance = null;
            try {
                assert constructor != null;
                instance = constructor.newInstance();
            } catch (InstantiationException | InvocationTargetException | IllegalAccessException e) {
                throw new FormatSettingsException();
            }

            result.put((Handler) instance, handlers.get(handler));
        }



        return result;
    }


    //JavaDoc же должно наследоваться и быть не обязательным
    /**
     * @return Map key - handler value - boolean if true it terminal handler
     * @throws FormatSettingsException Something has gone wrong
     */
    public Map<Handler, Boolean> getHandlers() throws FormatSettingsException {
        if (handlers == null) {
            handlers = loadHandlers(getHandlersFile());
        }
        return handlers;
    }

    /**
     * @return Map key - name value - container
     * @throws FormatSettingsException Something has gone wrong
     */
    public Map<String, Container> getContainers() throws FormatSettingsException {
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
