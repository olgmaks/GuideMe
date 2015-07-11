package com.epam.gm.olgmaks.absractdao.transformer;

import com.epam.gm.olgmaks.absractdao.annotation.Column;
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

    //gryn
    //private Connection connection;

    private Field[] fields;
    private Class<T> clazz;

    private List<Field> referencedFields;

    /**
     * This class helps to create instance of DB objects-tables (DAO model) in
     * Java. Field in bean class related to column in DB has to be annotated
     * with annotation "Column" in com.epam.lab.annotation. Annotation value
     * describe exactly name of current field in DB.
     */

    //gryn
    //public ResultTransformer(Connection connection, Class<T> clazz) {
    public ResultTransformer(Class<T> clazz) {

        this.clazz = clazz;
        fields = clazz.getDeclaredFields();

        //gryn
        //this.connection = connection;

        referencedFields = new ArrayList<>();
        for (Field field : fields) {
            if (field.isAnnotationPresent(OneToMany.class)) {
                referencedFields.add(field);
            }
        }
    }

    //gryn
    //public T getOneInstance(ResultSet rs) {
    public T getOneInstance(Connection connection, ResultSet rs) {
        try {
            rs.next();
        } catch (SQLException e) {
            e.printStackTrace();
        }

        //return toInstance(rs);
        return toInstance(connection, rs);
    }

    //gryn
    //public List<T> getAllInstances(ResultSet rs) {
    public List<T> getAllInstances(Connection connection, ResultSet rs) {

        List<T> result = new ArrayList<T>();
        try {
            while (!rs.isClosed()&&rs.next()) {
                //result.add(toInstance(rs));
                result.add(toInstance(connection, rs));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return result;
    }

    //gryn
    //private T toInstance(ResultSet rs) {
    private T toInstance(Connection connection, ResultSet rs) {

        try {
            t = clazz.newInstance();
//            System.out.println(clazz.getAnnotation(Entity.class));
            for (Field field : fields) {
                field.setAccessible(true);
                String columnLabel = new String();
                if (field.isAnnotationPresent(Column.class) && !field.isAnnotationPresent(OneToMany.class)) {
                    columnLabel = field.getAnnotation(Column.class).value();
//                    System.out.println("col label = "  + columnLabel);
                    try {
                        field.set(t, rs.getObject(columnLabel));
                    } catch (IllegalArgumentException | IllegalAccessException
                            | SQLException e) {
                        e.printStackTrace();
                    }

                }
            }

            for (Field referencedField : referencedFields) {
//                if (referencedField.get(t) == null) {
//                    continue;
//                }
                referencedField.setAccessible(true);

//                String id = referencedField.getAnnotation(Column.class).value();

                String mappedBy = referencedField.getAnnotation(ForeignKey.class).value();
                String foreignKey = referencedField.getAnnotation(OneToMany.class).field();


                Object currentKey = rs.getObject(foreignKey);
//                System.out.println(clazz + "mappedBy " + mappedBy);
//                System.out.println(clazz + "  foreignKey=" + foreignKey);
//                System.out.println(clazz + "  currentKey=" + currentKey);

                Class<?> referencedFieldClass = referencedField.getType();

                //gryn
                //GetHelper<?> getHelper = new GetHelper<>(connection, referencedFieldClass);
                GetHelper<?> getHelper = new GetHelper<>(referencedFieldClass);

                if (mappedBy==null||currentKey==null) {continue;}

                PreparedStatement preparedStatement = getHelper.
                        //getByFieldName(mappedBy, currentKey);
                                getByFieldName(connection, mappedBy, currentKey);
                ResultTransformer<?> resultTransformer =
                        //gryn
                        //new ResultTransformer<>(connection, referencedFieldClass);
                        new ResultTransformer<>(referencedFieldClass);

                referencedField.set(t, resultTransformer.
                        //getOneInstance(preparedStatement.executeQuery()));
                                getOneInstance(connection, preparedStatement.executeQuery()));
            }
        } catch (InstantiationException | IllegalAccessException e1) {
            e1.printStackTrace();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return t;
    }

}
