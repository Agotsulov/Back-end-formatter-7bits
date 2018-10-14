package it.sevenbits.core;

import java.util.List;

public interface FormatSettings  {

    /*
        Это нужно ,так как разным Handler'ам нужны разные переменные.
        Но хардкодить их куда нибудь нельзя.
        Так что есть метод start для этого и если там лежит не то должен вылетать exception и тот Handler не обрабатываться.
        Суть такая ,но решение надо получше потом придумаю.
     */

    List<Handler> getHandlers();

}
