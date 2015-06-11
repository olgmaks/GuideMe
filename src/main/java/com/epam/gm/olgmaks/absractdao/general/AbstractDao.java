package com.epam.gm.olgmaks.absractdao.general;

import com.epam.gm.olgmaks.absractdao.crudoperation.DeleteHelper;
import com.epam.gm.olgmaks.absractdao.transformer.ResultTransformer;
import com.epam.gm.olgmaks.absractdao.annotation.Entity;
import com.epam.gm.olgmaks.absractdao.crudoperation.GetHelper;
import com.epam.gm.olgmaks.absractdao.crudoperation.SaveHelper;
import com.epam.gm.olgmaks.absractdao.crudoperation.UpdateHelper;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class AbstractDao<T> implements IDao<T> {

    private String dataBaseTableName;
    private ResultTransformer<T> transformer;
    private Connection connection;
    private Class<T> clazz;
    private DeleteHelper<T> deleteHelper;
    private GetHelper<T> getHelper;


    public AbstractDao(Connection connection, Class<T> clazz) {
        this.connection = connection;
        this.clazz = clazz;
        transformer = new ResultTransformer<T>(connection, clazz);
		dataBaseTableName = clazz.getAnnotation(Entity.class).value();
        deleteHelper = new DeleteHelper<T>(connection, clazz);
        getHelper = new GetHelper<>(connection, clazz);

    }

    @Override
    public void save(T t) {
        try {
            new SaveHelper<T>(connection, clazz).prepareSave(t).
                    executeUpdate();
        } catch (IllegalArgumentException | IllegalAccessException
                | SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void update(T t, String... updateConditions) {
        System.out.println("abstract dao update");
        try {
            new UpdateHelper<T>(connection, clazz, updateConditions).
                    update(t).executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        }

    }

    @Override
    public void delete(T t) {
        try {
            deleteHelper.delete(t).executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        }

    }

    @Override
    public void deleteByField(String fieldName, Object fieldValue) {
        try {
            deleteHelper.delete(fieldName, fieldValue).executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        }
    }

    @Override
    public List<T> getAll() {
        List<T> result = new ArrayList<T>();
        String baseSelect = SELECT;
        String concreteTableSelect = String.format(baseSelect, dataBaseTableName);
        try {
            ResultSet rs = connection.prepareStatement(concreteTableSelect).executeQuery();
            result = transformer.getAllInstances(rs);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return result;
    }

    //Query should start with "join"
    @Override
    public List<T> customQuery (String sqlWithRestrictions) throws SQLException {
        List<T> result = new ArrayList<T>();
            ResultSet resultSet = getHelper.customQuery(sqlWithRestrictions).executeQuery();
            ResultTransformer<T> transformer = new ResultTransformer<>(connection, clazz);
            result = transformer.getAllInstances(resultSet);
        return result;
    }

    @Override
    public List<T> getByField(String fieldName, Object fieldValue) throws SQLException {
        ResultSet resultSet = getHelper.getByFieldName(fieldName, fieldValue).executeQuery();
        ResultTransformer<T> transformer = new ResultTransformer<>(connection, clazz);
        return transformer.getAllInstances(resultSet);
    }


}
