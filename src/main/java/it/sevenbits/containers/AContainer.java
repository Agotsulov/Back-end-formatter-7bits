package it.sevenbits.containers;

import it.sevenbits.core.Container;


/**
 *
 */
public abstract class AContainer implements Container {

    private String file;

    public String getFile() {
        return file;
    }

    public void setFile(final String file) {
        this.file = file;
    }
}
