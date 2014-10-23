package gob.osinergmin.fise.dao;

import gob.osinergmin.base.dao.GenericDao;
import gob.osinergmin.fise.domain.FiseFormato14BD;

import java.util.List;

public interface Formato14BDDao extends GenericDao {
	
	List<FiseFormato14BD> listarFormato14BD();

}
