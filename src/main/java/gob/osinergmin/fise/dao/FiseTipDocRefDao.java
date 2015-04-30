package gob.osinergmin.fise.dao;

import gob.osinergmin.base.dao.GenericDao;
import gob.osinergmin.fise.domain.FiseTipDocRef;

import java.sql.SQLException;
import java.util.List;

public interface FiseTipDocRefDao extends GenericDao {
	
	List<FiseTipDocRef> listarFiseTipDocRef();
	
	FiseTipDocRef obtenerFiseTipDocRefByPK(String id);
	
	void insertarFiseTipDocRef(FiseTipDocRef fiseTipDocRef) 
			throws SQLException;
	
	void actualizarFiseTipDocRef(FiseTipDocRef fiseTipDocRef) 
			throws SQLException;
	
	void eliminarFiseTipDocRef(FiseTipDocRef fiseTipDocRef) 
			throws SQLException;
	
	List<FiseTipDocRef> buscarFiseTipDocRef(String id, String descripcion ) 
			throws SQLException;

}
