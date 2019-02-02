package it.sevenbits.formatters.state.containers;

/**
 *
 */
public class NewLineFlagContainer extends AContainer {

    private boolean needNewLine = false;

    @Override
    public String get(final String key) throws ContainerException {
        return null;
    }

    @Override
    public void set(final String key, final String value) throws ContainerException {

    }

    @Override
    public void load() throws ContainerException {

    }

    @Override
    public void load(final String file) throws ContainerException {

    }

    public boolean isNeedNewLine() {
        return needNewLine;
    }

    public void setNeedNewLine(final boolean needNewLine) {
        this.needNewLine = needNewLine;
    }
}
