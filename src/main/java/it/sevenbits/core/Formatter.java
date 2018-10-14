package it.sevenbits.core;

import it.sevenbits.streams.InStream;
import it.sevenbits.streams.OutStream;

import java.util.List;

public class Formatter {

    public static void format(InStream in, OutStream out, FormatSettings settings){
        List<Handler> handlers = settings.getHandlers();
        while (in.hasNext()) {
            char symbol = in.next();
            for (Handler h: handlers)
                if(h.validate(symbol)) {
                    out.write(h.handle());
                    break; //Можно ли он его тут избавиться?
                }
        }
    }

}
