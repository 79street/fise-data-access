package gob.osinergmin.fise.dao;

import gob.osinergmin.base.dao.GenericDao;
import gob.osinergmin.fise.domain.FiseTipGasto;

import java.util.List;

public interface FiseTipGastoDao extends GenericDao {
	
	List<FiseTipGasto> listarFiseTipGasto();
	FiseTipGasto obtenerFiseTipGastoByPK(String id);

}
