package dao;

import java.util.List;

public interface IHiberDao<T> {
    /**
     * attempts to add the item to the database
     *
     * @param item the item to be added
     * @return whether the item was added
     */
    boolean add(T item);

    /**
     * attempts to delete the item from the database by id
     *
     * @param id the item's id
     * @return whether the item was deleted
     */
    boolean delete(int id);

    /**
     * attempts to update the item
     *
     * @param item the item to be updated
     * @return whether the item was updated
     */
    boolean update(T item);

    /**
     * retrieves all the items from the database
     *
     * @return a list of the items from the database
     */
    List<T> getAllItems();

    /**
     * retrieves an item from the database by id
     * @param itemIDBySpecificFormat the item's id by the needs of the DAO class. This wil leb boxed in an object.
     * @return the item from the database
     */
    T getItemByID(Object itemIDBySpecificFormat);
}
