package bikestore.repositories.base;

import bikestore.entities.base.ModelEntity;

import java.util.List;

public interface GenericRepository<T extends ModelEntity> {
    List<T> getAll();

    T getById(int id);

    T create(T entity);

    void deleteById(int id);

    void delete(T entity);
}
