package gob.osinergmin.fise.dao;

import gob.osinergmin.base.dao.GenericDao;
import gob.osinergmin.fise.domain.FiseTipGasto;

import java.sql.SQLException;
import java.util.List;

public interface FiseTipGastoDao extends GenericDao {
	
	List<FiseTipGasto> listarFiseTipGasto();
	
	FiseTipGasto obtenerFiseTipGastoByPK(String id) ;
	
	void insertarFiseTipGasto(FiseTipGasto fiseTipGasto) 
			throws SQLException;
	
	void actualizarFiseTipGasto(FiseTipGasto fiseTipGasto) 
			throws SQLException;
	
	void eliminarFiseTipGasto(FiseTipGasto fiseTipGasto) 
			throws SQLException;
	
	List<FiseTipGasto> buscarFiseTipGasto(String id, String descripcion ) 
			throws SQLException;

}
