package it.sevenbits.core;

import it.sevenbits.exceptions.HandlerException;
import it.sevenbits.streams.InStream;
import it.sevenbits.streams.OutStream;

import java.util.List;

public class Formatter {

    /*
        TODO: Разобраться с Exceptions
     */

    public static void format(InStream in, OutStream out, FormatSettings settings){
        List<Handler> handlers = settings.getHandlers();
        while (in.hasNext()) {
            char symbol = in.next();
            for (Handler h: handlers)
                if(h.validate(symbol)) {
                    try {
                        out.write(h.handle());
                    } catch (HandlerException e) {
                        e.printStackTrace();
                    }
                    break; //Можно ли он его тут избавиться?
                }
        }
    }

}
