package gob.osinergmin.fise.dao;

import java.sql.SQLException;
import java.util.List;

import gob.osinergmin.fise.domain.FiseTipPersonal;

public interface FiseTipPersonalDao {
	
	FiseTipPersonal obtenerFiseTipPersonalByPK(long id) throws SQLException;
	
	List<FiseTipPersonal> listarFiseTipPersonal() throws SQLException;
	
	void insertarFiseTipPersonal(FiseTipPersonal fiseTipPersonal) 
			throws SQLException;
	
	void actualizarFiseTipPersonal(FiseTipPersonal fiseTipPersonal) 
			throws SQLException;
	
	void eliminarFiseTipPersonal(FiseTipPersonal fiseTipPersonal) 
			throws SQLException;
	
	List<FiseTipPersonal> buscarFiseTipPersonal(String id, String descripcion ) 
			throws SQLException;
	
	long buscarMaximoIdTipPersonal() throws SQLException;

}
