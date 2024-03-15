package lk.ijse.repository;

public interface CrudRepository<ID , T> extends SuperRepository {
    ID save (T t);
    void update(T t );
    T get (ID id);
    void delete(T t);


}
