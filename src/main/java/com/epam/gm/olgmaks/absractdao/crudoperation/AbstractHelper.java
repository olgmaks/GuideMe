package com.epam.gm.olgmaks.absractdao.crudoperation;

import com.epam.gm.olgmaks.absractdao.annotation.OneToMany;
import com.epam.gm.olgmaks.absractdao.annotation.Entity;

import java.lang.reflect.Field;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by OLEG on 07.06.2015.
 */
public abstract class AbstractHelper<T> {

    protected Field[] fields;
    protected List<Field> referencedFields;
    protected Connection connection;
    protected String sql;
    protected String tableName;
    protected PreparedStatement preparedStatement;
    protected List<String> fieldNames;
    protected List<Object> fieldValues;
    // "?,?,?,?,?"
    protected String unknownValues;
    // "id,first_name,last_name,price"
    protected String fieldNameSequence;


    public AbstractHelper(Connection connection, Class<T> clazz) {
        this.connection = connection;
        fieldNames = new ArrayList<String>();
        fieldValues = new ArrayList<Object>();
        tableName = clazz.getAnnotation(Entity.class).value();
        fields = clazz.getDeclaredFields();

        referencedFields = new ArrayList<>();
        for (Field field : fields) {
            if (field.isAnnotationPresent(OneToMany.class)) {
               referencedFields.add(field);
            }
        }
    }

}

