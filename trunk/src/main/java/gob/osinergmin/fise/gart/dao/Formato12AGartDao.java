package gob.osinergmin.fise.gart.dao;

import gob.osinergmin.base.dao.GenericDao;
import gob.osinergmin.fise.domain.FiseFormato12AC;

import java.util.List;

public interface Formato12AGartDao extends GenericDao {
	
	List<FiseFormato12AC> listarFormato12AC();

}
