package DAO;

import java.util.List;

public interface DAO<T>{

    public List<T> getAll();

    public T getById(Long id);

    public Long save(T obj);

    public boolean update(T obj);

    public boolean remove(Long id);



}
