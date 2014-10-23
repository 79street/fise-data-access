package gob.osinergmin.fise.dao;

import gob.osinergmin.base.dao.GenericDao;
import gob.osinergmin.fise.domain.FiseFormato14CD;

import java.util.List;

public interface Formato14CDDao extends GenericDao {

	List<FiseFormato14CD> listarFormato14CD();
	
}
