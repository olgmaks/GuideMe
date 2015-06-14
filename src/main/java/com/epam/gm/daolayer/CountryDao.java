package com.epam.gm.daolayer;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.epam.gm.model.Country;
import com.epam.gm.model.User;
import com.epam.gm.olgmaks.absractdao.dbcontrol.ConnectionManager;
import com.epam.gm.olgmaks.absractdao.general.AbstractDao;

public class CountryDao extends AbstractDao<Country> {

    public CountryDao() {
    	super(ConnectionManager.getConnection(), Country.class);
    }
    
    //gryn
    public Country getCountryById(Integer id) throws SQLException {
        List<Country> list =  getByField("id", id);
        
        if(list.isEmpty()) 
        	return null;
        
        return list.get(0);
    }
    
    //gryn
    //Получити країни аналоги в різних мовах:
    //Ukraine - Україна - Украина
    public List<Country> getCountriesByPureId(Integer pureId) throws SQLException {
    	return getByField("pure_id", pureId);
    }	
    

}
