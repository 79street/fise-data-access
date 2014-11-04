package gob.osinergmin.fise.dao;

import gob.osinergmin.base.dao.GenericDao;
import gob.osinergmin.fise.domain.FiseFormato13AC;
import gob.osinergmin.fise.domain.FiseFormato13AD;

import java.util.List;

public interface Formato13ADDao extends GenericDao {
	
	List<FiseFormato13AD> listarFormato13ADByFormato13AC(FiseFormato13AC formato13AC);

}
