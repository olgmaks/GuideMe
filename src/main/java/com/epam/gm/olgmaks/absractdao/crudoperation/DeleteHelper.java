package com.epam.gm.olgmaks.absractdao.crudoperation;

import java.lang.reflect.Field;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import com.epam.gm.olgmaks.absractdao.annotation.Column;
import com.epam.gm.olgmaks.absractdao.general.IDao;

/**
 * Created by OLEG on 07.06.2015.
 */
public class DeleteHelper<T> extends AbstractHelper<T> {

    private String whereCondition;
//    private List<String> conditionFields;

    public DeleteHelper(Connection connection, Class<T> clazz) {
        super(connection, clazz);
        whereCondition = new String();
//        conditionFields = new ArrayList<>();
    }

    public PreparedStatement delete(T t) throws SQLException,
            IllegalAccessException {
        init(t);
        String sql = String.format(IDao.DELETE, tableName, whereCondition);
        System.out.println(sql);
        PreparedStatement statement = connection.prepareStatement(sql);

        int index = 1;
        for (Field field : fields) {
            field.setAccessible(true);
            statement.setObject(index++, field.get(t));
        }

        return statement;
    }

    public PreparedStatement delete(String fieldName, Object fieldValue)
            throws SQLException, IllegalAccessException {

        whereCondition += fieldName + "=?";
        String sql = String.format(IDao.DELETE, tableName, whereCondition);
        System.out.println(sql);
        PreparedStatement statement = connection.prepareStatement(sql);
        statement.setObject(1, fieldValue);
        return statement;
    }

    public void init(T t) {
        for (Field field : fields) {
            whereCondition += field.getAnnotation(Column.class).value() + "=? AND ";
        }
        whereCondition = whereCondition.substring(0, whereCondition.length() - 4);
        System.out.println("whereCondition : " + whereCondition);
    }
}
