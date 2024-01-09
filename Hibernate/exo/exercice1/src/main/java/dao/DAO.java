package dao;


import java.util.List;

public interface DAO<T> {

    public void create(T obj);

    public T get(Long id);

    public List<T> getAll();

    public void update(T obj);

    public void remove(Long id);


}
