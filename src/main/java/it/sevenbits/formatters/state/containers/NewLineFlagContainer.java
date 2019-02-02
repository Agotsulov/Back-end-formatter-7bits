package it.sevenbits.formatters.state.containers;

/**
 *
 */
public class NewLineFlagContainer extends AContainer {

    private boolean needNewLine = false;


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
