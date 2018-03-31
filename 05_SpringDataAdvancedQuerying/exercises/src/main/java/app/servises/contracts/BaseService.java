package app.servises.contracts;

import java.util.List;

public interface BaseService<E, K> {

    E findById(K id);

    void remove(E object);

    List<E> findAll();

    void save (E object);
}
