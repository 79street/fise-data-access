package gob.osinergmin.fise.dao;


import gob.osinergmin.base.dao.GenericDao;
import gob.osinergmin.fise.domain.FiseFormato14BC;

import java.util.List;

public interface Formato14BCDao extends GenericDao {

	List<FiseFormato14BC> listarFormato14BC();
	
}
