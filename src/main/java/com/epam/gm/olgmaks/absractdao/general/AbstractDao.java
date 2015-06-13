package com.epam.gm.olgmaks.absractdao.general;

import com.epam.gm.olgmaks.absractdao.annotation.Entity;
import com.epam.gm.olgmaks.absractdao.crudoperation.DeleteHelper;
import com.epam.gm.olgmaks.absractdao.crudoperation.GetHelper;
import com.epam.gm.olgmaks.absractdao.crudoperation.SaveHelper;
import com.epam.gm.olgmaks.absractdao.crudoperation.UpdateHelper;
import com.epam.gm.olgmaks.absractdao.transformer.ResultTransformer;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class AbstractDao<T> {

    public static final String SELECT = "SELECT * FROM %S";
    public static final String INSERT = "INSERT INTO %S (%S) VALUES (%S)";
    public static final String UPDATE = "UPDATE %S SET %S WHERE %S";
    public static final String DELETE = "DELETE FROM %S WHERE %S";

    private String dataBaseTableName;
    private ResultTransformer<T> transformer;
    private Connection connection;
    private Class<T> clazz;
    private DeleteHelper<T> deleteHelper;
    private GetHelper<T> getHelper;
    private UpdateHelper<T> updateHelper;


    public AbstractDao(Connection connection, Class<T> clazz) {
        this.connection = connection;
        this.clazz = clazz;
        transformer = new ResultTransformer<T>(connection, clazz);
        dataBaseTableName = clazz.getAnnotation(Entity.class).value();
        deleteHelper = new DeleteHelper<T>(connection, clazz);
        getHelper = new GetHelper<>(connection, clazz);
        updateHelper = new UpdateHelper<>(connection, clazz);
    }


    public void save(T t) throws IllegalArgumentException, IllegalAccessException, SQLException {
        new SaveHelper<T>(connection, clazz).prepareSave(t).executeUpdate();
    }

    public void update(T t, String... updateConditions) throws IllegalAccessException, SQLException {
        System.out.println("abstract dao update");
        updateHelper.update(t, updateConditions).executeUpdate();
    }


    public void updateById(Integer id, Map<String, Object> updates) throws SQLException {
        updateHelper.update(id, updates).executeUpdate();
    }


    public void updateWithCustomQuery(Map<String, Object> updates, String joined, String where) throws SQLException {
        System.out.println("update with custom query");
        updateHelper.update(updates, joined, where).executeUpdate();
    }


    public void delete(T t) throws IllegalAccessException, SQLException {
        deleteHelper.delete(t).executeUpdate();
    }


    public void deleteByField(String fieldName, Object fieldValue) throws IllegalAccessException, SQLException {
        deleteHelper.delete(fieldName, fieldValue).executeUpdate();
    }


    public List<T> getAll() throws SQLException {
        List<T> result = new ArrayList<T>();
        String baseSelect = SELECT;
        String concreteTableSelect = String.format(baseSelect, dataBaseTableName);
        ResultSet rs = connection.prepareStatement(concreteTableSelect).executeQuery();
        result = transformer.getAllInstances(rs);
        return result;
    }

    //Query should start with "join"

    public List<T> getWithCustomQuery(String sqlWithRestrictions) throws SQLException {
        List<T> result = new ArrayList<T>();
        ResultSet resultSet = getHelper.customQuery(sqlWithRestrictions).executeQuery();
        ResultTransformer<T> transformer = new ResultTransformer<>(connection, clazz);
        result = transformer.getAllInstances(resultSet);
        return result;
    }


    public List<T> getByField(String fieldName, Object fieldValue) throws SQLException {
        ResultSet resultSet = getHelper.getByFieldName(fieldName, fieldValue).executeQuery();
        ResultTransformer<T> transformer = new ResultTransformer<>(connection, clazz);
        return transformer.getAllInstances(resultSet);
    }

    public void callStoredProcedure(String procedureName, String... parameters) throws SQLException {
        CallableStatement cs = connection.prepareCall(procedureName);
        cs.setInt(1, Integer.parseInt(parameters[0]));
        cs.execute();
    }
}
