package it.sevenbits.other;

/**
 *
 */
public final class StringUtils {

    private StringUtils() {

    }

    /**
     * Повторяет s строку count раз
     * @param s строка которую повторить
     * @param count кол-во раз
     * @return результат
     */
    public static String repeat(final String s, final int count) {
        StringBuilder sb = new StringBuilder();

        for (int i = 0; i < count; i++) {
            sb.append(s);
        }

        return sb.toString();
    }

    /**
     * Повторяет c символ count раз
     * @param c символ который повторить
     * @param count кол-во раз
     * @return результат
     */
    public static String repeat(final char c, final int count) {
        StringBuilder sb = new StringBuilder();

        for (int i = 0; i < count; i++) {
            sb.append(c);
        }

        return sb.toString();
    }
}
