//package com.epam.gm.dao;
//
//import java.beans.IntrospectionException;
//import java.beans.PropertyDescriptor;
//import java.lang.reflect.Field;
//import java.lang.reflect.InvocationTargetException;
//import java.sql.PreparedStatement;
//import java.sql.ResultSet;
//import java.sql.SQLException;
//import java.sql.Timestamp;
//import java.sql.Types;
//import java.util.ArrayList;
//import java.util.Date;
//import java.util.HashMap;
//import java.util.Iterator;
//import java.util.List;
//import java.util.Map;
//import java.util.Map.Entry;
//import java.util.Set;
//
//import com.epam.gm.model.annotation.Column;
//import com.epam.gm.model.annotation.Entity;
//import com.epam.gm.model.annotation.ID;
//import com.epam.gm.model.annotation.Reference;
//import com.epam.gm.persistant.ConnectionManager;
//
///**
// * DAOJDBC
// * @author 
// * @version 1.0
// */
//public class GenericDaoImpl<T> implements GenericDao<T> {
//	
//	private static final String TABLE_ALIAS = "";
//
//	@Override
//	public void save(T t) throws Exception {
//		Class<?> clazz = t.getClass();
//		//
//		String tableName = getTableName(clazz);
//		//
//		StringBuilder fieldNames = new StringBuilder();		//
//		List<Object> fieldValues = new ArrayList<Object>();	//
//		StringBuilder placeholders = new StringBuilder();	//
//		Field[] fields = clazz.getDeclaredFields();
//		for (Field field : fields) {
//			PropertyDescriptor pd = new PropertyDescriptor(field.getName(),t.getClass());
//			//if (field.isAnnotationPresent(ID.class)) {
//			//	fieldNames.append(field.getAnnotation(ID.class).value()).append(",");
//			//	fieldValues.add(pd.getReadMethod().invoke(t));
//			//} else
//			if(field.isAnnotationPresent(Column.class)) {
//				fieldNames.append(field.getAnnotation(Column.class).value()).append(",");
//				fieldValues.add(pd.getReadMethod().invoke(t));
//			}
//			if (!field.isAnnotationPresent(ID.class)) 
//				placeholders.append("?").append(",");
//		}
//		//
//		fieldNames.deleteCharAt(fieldNames.length()-1);
//		placeholders.deleteCharAt(placeholders.length()-1);
//		
//		//sql
//		StringBuilder sql = new StringBuilder("");
//		sql.append("insert into ").append(tableName)
//		   .append(" (").append(fieldNames.toString())
//		   .append(") values (").append(placeholders).append(")") ;
//		PreparedStatement ps = ConnectionManager.getInstanse().getConnection().prepareStatement(sql.toString());
//		//SQL
//		setParameter(fieldValues, ps, false);
//		//SQL
//		ps.execute();
//		ConnectionManager.release(ps, null);
//	}
//
//
//	@Override
//	public void delete(Object id,Class<T> clazz) throws Exception {
//		String tableName = getTableName(clazz);
//		//ID
//		String idFieldName = "";
//		boolean flag = false;
//		Field[] fields = clazz.getDeclaredFields();
//		for (Field field : fields) {
//			if(field.isAnnotationPresent(ID.class)) {
//				idFieldName = field.getAnnotation(ID.class).value();
//				flag = true;
//				break;
//			}
//		}
//		if (!flag) {
//			throw new Exception(clazz.getName() + " object not found id property.");
//		}
//		
//		//sql
//		String sql = "delete from " + tableName + " where " + idFieldName + "=?";
//		PreparedStatement ps = ConnectionManager.getInstanse().getConnection().prepareStatement(sql);
//		ps.setObject(1, id);
//		//SQL
//		ps.execute();
//		ConnectionManager.release(ps,null);
//	}
//
//	@Override
//	public void update(T t) throws Exception {
//		Class<?> clazz = t.getClass();
//		//
//		String tableName = getTableName(clazz);
//		//
//		List<Object> fieldNames = new ArrayList<Object>();	//
//		List<Object> fieldValues = new ArrayList<Object>();	//
//		List<String> placeholders = new ArrayList<String>();//
//		String idFieldName = "";
//		Object idFieldValue = "";
//		Field[] fields = clazz.getDeclaredFields();
//		for (Field field : fields) {
//			PropertyDescriptor pd = new PropertyDescriptor(field.getName(),t.getClass());
//			if (field.isAnnotationPresent(ID.class)) {
//				idFieldName = field.getAnnotation(ID.class).value();
//				idFieldValue = pd.getReadMethod().invoke(t);
//			} else if(field.isAnnotationPresent(Column.class)) {
//				fieldNames.add(field.getAnnotation(Column.class).value());
//				fieldValues.add(pd.getReadMethod().invoke(t));
//				placeholders.add("?");
//			}
//		}
//		//ID?
//		fieldNames.add(idFieldName);
//		fieldValues.add(idFieldValue);
//		placeholders.add("?");
//		
//		//sql
//		StringBuilder sql = new StringBuilder("");
//		sql.append("update ").append(tableName).append(" set ");
//		int index = fieldNames.size() - 1;
//		for (int i = 0; i < index; i++) {
//			sql.append(fieldNames.get(i)).append("=").append(placeholders.get(i)).append(",");
//		}
//		sql.deleteCharAt(sql.length()-1).append(" where ").append(fieldNames.get(index)).append("=").append("?");
//		
//		//SQL
//		PreparedStatement ps = ConnectionManager.getInstanse().getConnection().prepareStatement(sql.toString());
//		setParameter(fieldValues, ps, false);
//		
//		//SQL
//		ps.execute();
//		ConnectionManager.release(ps, null);
//		
//		System.out.println(sql + "\n" + clazz.getSimpleName() + ".");
//	}
//
//	@Override
//	public T get(Object id,Class<T> clazz) throws Exception {
//		String idFieldName = "";
//		Field[] fields = clazz.getDeclaredFields();
//		boolean flag = false;
//		for (Field field : fields) {
//			if (field.isAnnotationPresent(ID.class)) {
//				idFieldName = field.getAnnotation(ID.class).value();
//				flag = true;
//				break;
//			} 
//		}
//		
//		if (!flag) {
//			throw new Exception(clazz.getName() + " object not found id property.");
//		}
//		
//		//SQL
//		Map<String,Object> sqlWhereMap = new HashMap<String, Object>();
//		sqlWhereMap.put(TABLE_ALIAS + "." + idFieldName, id);
//		
//		List<T> list = findAllByConditions(sqlWhereMap, clazz);
//		return list.size() > 0 ? list.get(0) : null;
//	}
//
//	@Override
//	public List<T> findAllByConditions(Map<String,Object> sqlWhereMap,Class<T> clazz) throws Exception {
//		List<T> list = new ArrayList<T>();
//		String tableName = getTableName(clazz);
//		String idFieldName = "";
//		StringBuffer fieldNames = new StringBuffer();
//		Field[] fields = clazz.getDeclaredFields();
//		for (Field field : fields) {
//			String propertyName = field.getName();
//			if (field.isAnnotationPresent(ID.class)) {
//				idFieldName = field.getAnnotation(ID.class).value();
//				fieldNames.append(idFieldName)
//						  .append(" as ").append(propertyName).append(",");
//			} else if (field.isAnnotationPresent(Column.class)) {
//				fieldNames.append(field.getAnnotation(Column.class).value())
//						  .append(" as ").append(propertyName).append(",");
//			}
//		}
//		fieldNames.deleteCharAt(fieldNames.length()-1);
//		
//		//SQL
//		String sql = "select " + fieldNames + " from " + tableName;
//		PreparedStatement ps = null;
//		List<Object> values = null;
//		if (sqlWhereMap != null) {
//			List<Object> sqlWhereWithValues = getSqlWhereWithValues(sqlWhereMap);
//			if (sqlWhereWithValues != null) {
//				//SQL
//				String sqlWhere = (String)sqlWhereWithValues.get(0);
//				sql += sqlWhere;
//				//SQL
//				values = (List<Object>) sqlWhereWithValues.get(1);
//			}
//		} 
//		
//		//
//		if (values != null) {
//			ps = ConnectionManager.getInstanse().getConnection().prepareStatement(sql);
//			setParameter(values, ps, true);
//		} else {
//			ps = ConnectionManager.getInstanse().getConnection().prepareStatement(sql);
//		}
//		
//		
//		//SQL
//		ResultSet rs = ps.executeQuery();
//		while(rs.next()) {
//			T t = clazz.newInstance();
//			initObject(t, fields, rs);
//			list.add(t);
//		}
//		
//		ConnectionManager.release(ps, rs);
//		return list;
//	}
//	
//	
//
//	
//	
//	private void initObject(T t, Field[] fields, ResultSet rs)
//			throws SQLException, IntrospectionException,
//			IllegalAccessException, InvocationTargetException {
//		
//		
//		for (Field field : fields) {
//			String propertyName = field.getName();
//			Object paramVal = null;
//			
//			
//			
//			
//			System.out.println("propertyName = " + propertyName);
//			
//			Class<?> clazzField = field.getType();
//			if (clazzField == String.class) {
//				paramVal = rs.getString(propertyName);
//			} else if (clazzField == short.class || clazzField == Short.class) {
//				paramVal = rs.getShort(propertyName);
//			} else if (clazzField == int.class || clazzField == Integer.class) {
//				paramVal = rs.getInt(propertyName);
//			} else if (clazzField == long.class || clazzField == Long.class) {
//				paramVal = rs.getLong(propertyName);
//			} else if (clazzField == float.class || clazzField == Float.class) {
//				paramVal = rs.getFloat(propertyName);
//			} else if (clazzField == double.class || clazzField == Double.class) {
//				paramVal = rs.getDouble(propertyName);
//			} else if (clazzField == boolean.class || clazzField == Boolean.class) {
//				paramVal = rs.getBoolean(propertyName);
//			} else if (clazzField == byte.class || clazzField == Byte.class) {
//				paramVal = rs.getByte(propertyName);
//			} else if (clazzField == char.class || clazzField == Character.class) {
//				paramVal = rs.getCharacterStream(propertyName);
//			} else if (clazzField == Date.class) {
//				paramVal = rs.getTimestamp(propertyName);
//			} else if (clazzField.isArray()) {
//				paramVal = rs.getString(propertyName).split(",");	//
//			} 
//			PropertyDescriptor pd = new PropertyDescriptor(propertyName,t.getClass());
//			pd.getWriteMethod().invoke(t, paramVal);
//		}
//	}
//	
//	
//	private List<Object> getSqlWhereWithValues(Map<String,Object> sqlWhereMap) {
//		if (sqlWhereMap.size() <1 ) return null;
//		List<Object> list = new ArrayList<Object>();
//		List<Object> fieldValues = new ArrayList<Object>();
//		StringBuffer sqlWhere = new StringBuffer(" where ");
//		Set<Entry<String, Object>> entrySets = sqlWhereMap.entrySet();
//		for (Iterator<Entry<String, Object>> iteraotr = entrySets.iterator();iteraotr.hasNext();) {
//			Entry<String, Object> entrySet = iteraotr.next();
//			fieldValues.add(entrySet.getValue());
//			Object value = entrySet.getValue();
//			if (value.getClass() == String.class) {
//				sqlWhere.append(entrySet.getKey()).append(" like ").append("?").append(" and ");
//			} else {
//				sqlWhere.append(entrySet.getKey()).append("=").append("?").append(" and ");
//			}
//		}
//		sqlWhere.delete(sqlWhere.lastIndexOf("and"), sqlWhere.length());
//		list.add(sqlWhere.toString());
//		list.add(fieldValues);
//		return list;
//	}
//	
//	private String getTableName(Class<?> clazz) throws Exception {
//		if (clazz.isAnnotationPresent(Entity.class)) {
//			Entity entity = clazz.getAnnotation(Entity.class);
//			return entity.value();
//		} else {
//			throw new Exception(clazz.getName() + " is not Entity Annotation.");
//		}
//	}
//	
//	private void setParameter(List<Object> values, PreparedStatement ps, boolean isSearch)
//			throws SQLException {
//		for (int i = 1; i <= values.size(); i++) {
//			Object fieldValue = values.get(i-1);
//			Class<?> clazzValue = fieldValue.getClass();
//			if (clazzValue == String.class) {
//				if (isSearch) 
//					ps.setString(i, "%" + (String)fieldValue + "%");
//				else
//					ps.setString(i,(String)fieldValue);
//					
//			} else if (clazzValue == boolean.class || clazzValue == Boolean.class) {
//				ps.setBoolean(i, (Boolean)fieldValue);
//			} else if (clazzValue == byte.class || clazzValue == Byte.class) {
//				ps.setByte(i, (Byte)fieldValue);
//			} else if (clazzValue == char.class || clazzValue == Character.class) {
//				ps.setObject(i, fieldValue,Types.CHAR);
//			} else if (clazzValue == Date.class) {
//				ps.setTimestamp(i, new Timestamp(((Date) fieldValue).getTime()));
//			} else if (clazzValue.isArray()) {
//				Object[] arrayValue = (Object[]) fieldValue;
//				StringBuffer sb = new StringBuffer();
//				for (int j = 0; j < arrayValue.length; j++) {
//					sb.append(arrayValue[j]).append("?");
//				}
//				ps.setString(i, sb.deleteCharAt(sb.length()-1).toString());
//			} else {
//				ps.setObject(i, fieldValue, Types.NUMERIC);
//			}
//		}
//	}
//
//	
//	//tarasg
//	private void initObjectByMap(Object t, Map<String, Field> fieldHolder, ResultSet rs)
//			throws SQLException, IntrospectionException,
//			IllegalAccessException, InvocationTargetException {
//		
//
//		int colNum = rs.getMetaData().getColumnCount();
//		for(int i = 1; i <= colNum; i++) {
//			String columnLabel = rs.getMetaData().getColumnLabel(i);
//			
//			Field field = fieldHolder.getOrDefault(columnLabel, null);
//			if(field == null) 
//				continue;
//			
//			String propertyName = field.getName();
//			Object paramVal = null;
//			
//			Class<?> clazzField = field.getType();
//			if (clazzField == String.class) {
//				paramVal = rs.getString(columnLabel);
//			} else if (clazzField == short.class || clazzField == Short.class) {
//				paramVal = rs.getShort(columnLabel);
//			} else if (clazzField == int.class || clazzField == Integer.class) {
//				paramVal = rs.getInt(columnLabel);
//			} else if (clazzField == long.class || clazzField == Long.class) {
//				paramVal = rs.getLong(columnLabel);
//			} else if (clazzField == float.class || clazzField == Float.class) {
//				paramVal = rs.getFloat(columnLabel);
//			} else if (clazzField == double.class || clazzField == Double.class) {
//				paramVal = rs.getDouble(columnLabel);
//			} else if (clazzField == boolean.class || clazzField == Boolean.class) {
//				paramVal = rs.getBoolean(columnLabel);
//			} else if (clazzField == byte.class || clazzField == Byte.class) {
//				paramVal = rs.getByte(columnLabel);
//			} else if (clazzField == char.class || clazzField == Character.class) {
//				paramVal = rs.getCharacterStream(columnLabel);
//			} else if (clazzField == Date.class) {
//				paramVal = rs.getTimestamp(columnLabel);
//			} else if (clazzField.isArray()) {
//				paramVal = rs.getString(columnLabel).split(",");	//
//			} 
//			PropertyDescriptor pd = new PropertyDescriptor(propertyName,t.getClass());
//			pd.getWriteMethod().invoke(t, paramVal);
//			
//		}
//		
//
//	}
//	
//	
//	//tarasg
//	private Map<String, Field> getFieldsMap(Class<?> clazz, String tablePrefix) {
//		Map<String, Field> fieldHolder = new HashMap<>();
//		
//		Field[] fields = clazz.getDeclaredFields();
//		for (Field field : fields) {
//			String columnName = null;
//			
//			if (field.isAnnotationPresent(ID.class)) {
//				columnName = field.getAnnotation(ID.class).value();
//			} else if (field.isAnnotationPresent(Column.class)) {
//				columnName = field.getAnnotation(Column.class).value();
//			} else
//				continue;
//				
//			fieldHolder.put(tablePrefix + columnName, field);
//		}
//		
//		return fieldHolder;
//	}
//	
//	
//	//taras
//	private Map<String, Field> getFieldsMap(Class<?> clazz) {
//		return getFieldsMap(clazz, "");
//	}
//
//	//tarasg
//	@Override
//	public List<T> findAllByStatement(String sql, List<Object> values, Class<T> clazz)
//			throws Exception {
//		
//		//tarasg
//		Map<String, Field> fieldHolder = getFieldsMap(clazz);
//		
//		
//		List<T> list = new ArrayList<T>();
//		
//		PreparedStatement ps = null;
//		if (values != null) {
//			ps = ConnectionManager.getInstanse().getConnection().prepareStatement(sql);
//			setParameter(values, ps, true);
//		} else {
//			ps = ConnectionManager.getInstanse().getConnection().prepareStatement(sql);
//		}
//		
//		//SQL
//		ResultSet rs = ps.executeQuery();
//		while(rs.next()) {
//			T t = clazz.newInstance();
//			initObjectByMap(t, fieldHolder, rs);
//			list.add(t);
//			
//
//			
//			//tarasg - get @Reference fields
//			Field[] fields = clazz.getDeclaredFields();
//			for (Field f : fields) {
//				if(!f.isAnnotationPresent(Reference.class)) 
//					continue;
//
//				//String propertyName = f.getName();
//				String refName = f.getAnnotation(Reference.class).value();
//				Class<?> refClass = f.getAnnotation(Reference.class).type();
//				Map<String, Field> refFields = getFieldsMap(refClass, refName + "_");
//				
//				Object ref = refClass.newInstance();
//				initObjectByMap(ref, refFields, rs);
//				PropertyDescriptor pd = new PropertyDescriptor(f.getName(), t.getClass());
//				pd.getWriteMethod().invoke(t, ref);				
//				
//			}	
//			
//		}
//		
//		
//	
//		
//		ConnectionManager.release(ps, rs);
//		return list;
//	}
//}