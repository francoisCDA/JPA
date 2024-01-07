package dao;

import java.util.List;

public interface InterfaceDAO<T> {

    public List<T> getAll();

    public boolean save(T obj);

    public boolean rm(Long id);

}
