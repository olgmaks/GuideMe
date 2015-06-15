package com.epam.gm.daolayer;

import java.sql.SQLException;
import java.util.List;

import com.epam.gm.model.Address;
import com.epam.gm.model.City;
import com.epam.gm.olgmaks.absractdao.dbcontrol.ConnectionManager;
import com.epam.gm.olgmaks.absractdao.general.AbstractDao;

public class AddressDao extends AbstractDao<Address>{

    public AddressDao() {
	super(ConnectionManager.getConnection(), Address.class);
    }

    //gryn
    //must be optimized later
    public Integer getLastPureId() throws SQLException {
    	
    	List<Address> list = getAll();
    	
    	Integer max = 0;
    	for(Address c: list) {
    		if(c.getPureId() > max)
    			max = c.getPureId();
    	}
    	
    	return max;
    }    
    
    //gryn
    //Получити адреси аналоги в різних мовах:
    public List<Address> getAddressByPureId(Integer pureId) throws SQLException {
    	return getByField("pure_id", pureId);
    }	    
}
