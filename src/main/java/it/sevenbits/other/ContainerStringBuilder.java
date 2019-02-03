package it.sevenbits.other;

import java.util.Objects;

/**
 *
 */
public class ContainerStringBuilder {

    private StringBuilder string;

    /**
     *
     */
    public ContainerStringBuilder() {
        this.string = new StringBuilder();
    }

    public StringBuilder getString() {
        return string;
    }

    public void setString(final StringBuilder string) {
        this.string = string;
    }

    @Override
    public boolean equals(final Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        ContainerStringBuilder that = (ContainerStringBuilder) o;
        return Objects.equals(string, that.string);
    }

    /**
     * @param c append char to StringBuilder
     */
    public void append(final char c) {
        string.append(c);
    }

    @Override
    public int hashCode() {
        return Objects.hash(string);
    }

    @Override
    public String toString() {
        return string.toString();
    }
}
