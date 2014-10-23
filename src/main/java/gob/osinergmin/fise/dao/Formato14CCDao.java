package gob.osinergmin.fise.dao;


import gob.osinergmin.base.dao.GenericDao;
import gob.osinergmin.fise.domain.FiseFormato14CC;


import java.util.List;

public interface Formato14CCDao extends GenericDao {
	

	List<FiseFormato14CC> listarFormato14CC();
	
	
}
