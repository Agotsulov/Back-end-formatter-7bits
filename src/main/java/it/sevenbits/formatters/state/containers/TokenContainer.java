package it.sevenbits.formatters.state.containers;

import it.sevenbits.formatters.state.tokens.Token;

public class TokenContainer extends AContainer {

    private Token current;

    public Token getCurrent() {
        return current;
    }

    public void setCurrent(Token current) {
        this.current = current;
    }

    @Override
    public String get(String key) throws ContainerException {
        return null;
    }

    @Override
    public void set(String key, String value) throws ContainerException {

    }

    @Override
    public void load() throws ContainerException {

    }

    @Override
    public void load(String file) throws ContainerException {

    }
}
