package it.sevenbits.other;

/**
 *
 */
public class ContainerJSON {

    private String name;
    private String path;
    private String file;

    /**
     * @param name String
     * @param path String
     * @param file String
     */
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