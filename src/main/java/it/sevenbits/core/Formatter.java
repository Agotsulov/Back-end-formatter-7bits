package it.sevenbits.core;

import it.sevenbits.exceptions.HandlerException;
import it.sevenbits.streams.InStream;
import it.sevenbits.streams.OutStream;

import java.util.List;

public class Formatter {

    /*
        TODO: Разобраться с Exceptions
     */


    private InStream in;
    private OutStream out;

    private FormatSettings settings;

    public Formatter(InStream in, OutStream out, FormatSettings settings) {
        this.in = in;
        this.out = out;
        this.settings = settings;
    }

    public void format(){
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
    
    public InStream getIn() {
        return in;
    }

    public void setIn(InStream in) {
        this.in = in;
    }

    public OutStream getOut() {
        return out;
    }

    public void setOut(OutStream out) {
        this.out = out;
    }

    public FormatSettings getSettings() {
        return settings;
    }

    public void setSettings(FormatSettings settings) {
        this.settings = settings;
    }

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
