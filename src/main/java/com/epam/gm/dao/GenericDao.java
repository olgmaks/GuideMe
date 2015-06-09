package com.epam.gm.dao;

import java.util.List;
import java.util.Map;

public interface GenericDao<T>  {
	
	
	
	public void save(T t) throws Exception;
	
	public void delete(Object id,Class<T> clazz) throws Exception;
	
	public void update(T t) throws Exception;
	
	public T get(Object id,Class<T> clazz) throws Exception;
	
	public List<T> findAllByConditions(Map<String,Object> sqlWhereMap,Class<T> clazz) throws Exception;
	
	//tarasg
	public List<T> findAllByStatement(String stmt, List<Object> values,   Class<T> clazz) throws Exception;
}