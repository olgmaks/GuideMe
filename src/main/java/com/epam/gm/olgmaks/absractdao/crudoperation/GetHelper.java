package com.epam.gm.olgmaks.absractdao.crudoperation;

import com.epam.gm.olgmaks.absractdao.annotation.Column;
import com.epam.gm.olgmaks.absractdao.general.AbstractDao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

/**
 * Created by OLEG on 07.06.2015.
 */
public class GetHelper<T> extends AbstractHelper<T> {

    // private T t;

    //gryn
    //public GetHelper(Connection connection, Class<T> clazz) {
    public GetHelper(Class<T> clazz) {
        //super(connection, clazz);
        super(clazz);
    }

    //gryn - adding Connection connection to signature
    public PreparedStatement getByFieldName(Connection connection, String fieldName, Object fieldValue)
            throws SQLException {
        String sql = String.format(AbstractDao.SELECT + " where %S=?", tableName,
                fieldName);
//		System.out.println(sql);
        PreparedStatement statement = connection.prepareStatement(sql);
        statement.setObject(1, fieldValue);
//        System.out.println(statement);
        return statement;
    }

    // Query should start with "join ... on ... where ... " statement

    //gryn - adding Connection connection to signature
    public PreparedStatement customQuery(Connection connection, String sqlWithRestrictions)
            throws SQLException {
        String baseSelect = AbstractDao.SELECT;
        String sql = String.format(baseSelect,
                (tableName + " " + sqlWithRestrictions));
        
  //      System.out.println("ABSTRACT***************************************************");
 //       System.out.println(sql);
        PreparedStatement statement = connection.prepareStatement(sql);
        return statement;
    }

    @Deprecated
    public PreparedStatement customDistinctQuery(Connection connection, String sqlWithRestrictions) throws SQLException {
        String baseSelect = "SELECT DISTINCT %s FROM %s";
        StringBuilder tableColumns = new StringBuilder();

        int i = 0;

        String columnAnnotation = null;

        while (i < fields.length - 1) {
            if (!fields[i].isAnnotationPresent(Column.class)) {
                continue;
            }

            columnAnnotation = fields[i].getAnnotation(Column.class).value();
            System.out.println(columnAnnotation);
            tableColumns.append(columnAnnotation).append(",");
            i++;
        }


        tableColumns.append(columnAnnotation);

        String sql = String.format(baseSelect, tableColumns, tableName);

        System.out.println(sql);

        return connection.prepareStatement(sql);
    }

}
