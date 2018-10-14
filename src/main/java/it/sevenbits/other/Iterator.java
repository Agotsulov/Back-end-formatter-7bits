package it.sevenbits.other;

public interface Iterator<T>{
    //Вот что лучше: использовать стандарнтый Java'овский или свой?

    T next();

    boolean hasNext();

}
