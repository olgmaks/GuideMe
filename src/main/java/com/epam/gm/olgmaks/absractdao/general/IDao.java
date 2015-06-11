package com.epam.gm.olgmaks.absractdao.general;

import java.sql.SQLException;
import java.util.List;

public interface IDao<T> {

    public static final String SELECT = "SELECT * FROM %S";
    public static final String INSERT = "INSERT INTO %S (%S) VALUES (%S)";
    public static final String UPDATE = "UPDATE %S SET %S WHERE %S";
    public static final String DELETE = "DELETE FROM %S WHERE %S";

    /**
     * Saving operations
     */
    public void save(T t);

    /**
     * Update operations
     */
    public void update(T t, String ... updateConditions);

    /**
     * Delete operations
     */
    public void delete(T t);

    public void deleteByField(String fieldName, Object fieldValue);

    /**
     * Get operations
     */

    public List<T> getAll();

    public List<T>  getByField(String fieldName, Object fieldValue) throws SQLException;

    public List<T> customQuery (String sqlWithRestrictions) throws SQLException;

}
