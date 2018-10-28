package it.sevenbits.formatters;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.google.gson.stream.JsonReader;
import it.sevenbits.core.Container;
import it.sevenbits.core.FormatSettings;
import it.sevenbits.core.Handler;
import it.sevenbits.exceptions.FormatSettingsException;

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
public class DefaultFormatSettings implements FormatSettings {

    private class ThreeString {
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

    DefaultFormatSettings(final String containersFile, final String handlersFile) {
        this.containersFile = containersFile;
        this.handlersFile = handlersFile;
    }

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

    public Map<Handler, Boolean> getHandlers() throws FormatSettingsException {
        if (handlers == null) {
            handlers = loadHandlers(getHandlersFile());
        }
        return handlers;
    }

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
