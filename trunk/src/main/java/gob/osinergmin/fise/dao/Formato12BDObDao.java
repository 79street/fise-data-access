package gob.osinergmin.fise.dao;

import java.util.List;

import gob.osinergmin.base.dao.GenericDao;

import gob.osinergmin.fise.domain.FiseFormato12BD;
import gob.osinergmin.fise.domain.FiseFormato12BDOb;


public interface Formato12BDObDao extends GenericDao {
	
	List<FiseFormato12BDOb> getLstFormatoObs(FiseFormato12BD idDetalle);
	
	


}
