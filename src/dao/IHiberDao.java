package dao;

import java.util.List;

public interface IHiberDao<T> {
    void add(T item);
    void delete(int id);
    void update(T item);
    List<T> getAllItems();
    T getItemByID(int itemID);
}
