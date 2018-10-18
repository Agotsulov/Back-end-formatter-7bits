package it.sevenbits.other;

public class StringUtils {

    public static String repeat(String s, int count){
        StringBuilder sb = new StringBuilder();

        for (int i = 0;i < count;i++)
            sb.append(s);

        return sb.toString();
    }

    public static String repeat(char c, int count){
        StringBuilder sb = new StringBuilder();

        for (int i = 0;i < count;i++)
            sb.append(c);

        return sb.toString();
    }
}
