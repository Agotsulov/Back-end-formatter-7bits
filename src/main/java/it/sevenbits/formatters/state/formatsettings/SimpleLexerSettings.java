package it.sevenbits.formatters.state.formatsettings;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.google.gson.stream.JsonReader;
import it.sevenbits.formatters.state.containers.Container;
import it.sevenbits.formatters.state.handlers.Handler;
import it.sevenbits.other.ContainerJSON;

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

    private Map<String, Container> containers = null;

    private String containersFile;

    /**
     * @param containersFile path to json file
     */
    public SimpleLexerSettings(final String containersFile) {
        this.containersFile = containersFile;
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

            List<ContainerJSON> data = json.fromJson(jReader, new TypeToken<List<ContainerJSON>>() {
            }.getType());

            for (ContainerJSON d : data) {
                Class<?> c = null;

                c = Class.forName(d.getPath());

                Constructor<?> constructor = null;

                constructor = c.getConstructor();

                Object instance = null;
                instance = constructor.newInstance();

                Container container = (Container) instance;
                container.setFile(d.getFile());
                result.put(d.getName(), container);
            }
        }catch (FileNotFoundException
                | InstantiationException
                | InvocationTargetException
                | IllegalAccessException
                | ClassNotFoundException
                | NoSuchMethodException e) {
            throw new LexerSettingsException();
        }

        return result;
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


}
