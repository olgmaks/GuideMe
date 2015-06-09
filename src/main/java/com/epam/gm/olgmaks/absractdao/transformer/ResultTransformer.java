package com.epam.gm.olgmaks.absractdao.transformer;

import com.epam.gm.olgmaks.absractdao.annotation.Column;
import com.epam.gm.olgmaks.absractdao.annotation.Entity;
import com.epam.gm.olgmaks.absractdao.annotation.ForeignKey;
import com.epam.gm.olgmaks.absractdao.annotation.OneToMany;
import com.epam.gm.olgmaks.absractdao.crudoperation.GetHelper;

import java.lang.reflect.Field;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class ResultTransformer<T> {

    private T t;
    private Connection connection;
    private Field[] fields;
    private Class<T> clazz;

    private List<Field> referencedFields;

    /**
     * This class helps to create instance of DB objects-tables (DAO model) in
     * Java. Field in bean class related to column in DB has to be annotated
     * with annotation "Column" in com.epam.lab.annotation. Annotation value
     * describe exactly name of current field in DB.
     */
    public ResultTransformer(Connection connection, Class<T> clazz) {

        this.clazz = clazz;
        fields = clazz.getDeclaredFields();
        this.connection = connection;
        referencedFields = new ArrayList<>();
        for (Field field : fields) {
            if (field.isAnnotationPresent(OneToMany.class)) {
                referencedFields.add(field);
            }
        }
    }

    public T getOneInstance(ResultSet rs) {
        try {
            rs.next();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return toInstance(rs);
    }

    public List<T> getAllInstances(ResultSet rs) {

        List<T> result = new ArrayList<T>();
        try {
            while (rs.next()) {
                result.add(toInstance(rs));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return result;
    }

    private T toInstance(ResultSet rs) {

        try {
            t = clazz.newInstance();
            System.out.println(clazz.getAnnotation(Entity.class));
            for (Field field : fields) {
                field.setAccessible(true);
                String columnLabel = new String();
                if (field.isAnnotationPresent(Column.class) && !field.isAnnotationPresent(OneToMany.class)) {
                    columnLabel = field.getAnnotation(Column.class).value();
                    try {
                        field.set(t, rs.getObject(columnLabel));
                    } catch (IllegalArgumentException | IllegalAccessException
                            | SQLException e) {
                        e.printStackTrace();
                    }

                }
            }

            for (Field referencedField : referencedFields) {
                referencedField.setAccessible(true);

//                String id = referencedField.getAnnotation(Column.class).value();

                String mappedBy = null;
                String foreignKey = null;
                for (Field field : referencedField.getType().getDeclaredFields()) {
                    if (field.isAnnotationPresent(ForeignKey.class)) {
//                        for (Field field1 : field.getAnnotation(OneToMany.class).value().getDeclaredFields()) {
//                            if (field.isAnnotationPresent(ForeignKey.class)) {
//                        foreignKey = referencedField.getAnnotation(Column.class).value();
                        mappedBy = field.getAnnotation(ForeignKey.class).value();
                        foreignKey =field.getAnnotation(Column.class).value();
//                            }
//                        }
                    }
                }

                Object currentKey = rs.getObject(mappedBy);
//                System.out.println(clazz + "mapped by " + mappedBy);
//                System.out.println(clazz + "  foreignKey=" + foreignKey);
//                System.out.println(clazz + "  currentKey=" + currentKey);

                Class<?> referencedFieldClass = referencedField.getType();
                GetHelper<?> getHelper = new GetHelper<>(connection, referencedFieldClass);
                PreparedStatement preparedStatement = getHelper.
                        getByFieldName(foreignKey, currentKey);
                ResultTransformer<?> resultTransformer =
                        new ResultTransformer<>(connection, referencedFieldClass);
                referencedField.set(t, resultTransformer.
                        getOneInstance(preparedStatement.executeQuery()));
            }
        } catch (InstantiationException | IllegalAccessException e1) {
            e1.printStackTrace();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return t;
    }

}
