package it.sevenbits.other;

/**
 *
 */
public final class StringUtils {

    private StringUtils() {

    }

    public static String repeat(final String s, final int count) {
        StringBuilder sb = new StringBuilder();

        for (int i = 0; i < count; i++) {
            sb.append(s);
        }

        return sb.toString();
    }

    public static String repeat(final char c, final int count) {
        StringBuilder sb = new StringBuilder();

        for (int i = 0; i < count; i++) {
            sb.append(c);
        }

        return sb.toString();
    }
}
