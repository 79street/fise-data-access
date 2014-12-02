package gob.osinergmin.fise.dao;

import gob.osinergmin.base.dao.GenericDao;
import gob.osinergmin.fise.domain.FiseTipDocRef;

import java.util.List;

public interface FiseTipDocRefDao extends GenericDao {
	
	List<FiseTipDocRef> listarFiseTipDocRef();
	FiseTipDocRef obtenerFiseTipDocRefByPK(String id);

}
