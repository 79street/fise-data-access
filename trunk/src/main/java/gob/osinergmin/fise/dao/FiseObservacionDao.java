package gob.osinergmin.fise.dao;

import gob.osinergmin.base.dao.GenericDao;
import gob.osinergmin.fise.domain.FiseObservacion;

import java.sql.SQLException;
import java.util.List;

public interface FiseObservacionDao extends GenericDao {
	
	List<FiseObservacion> listarFiseObservacion();
	
	void insertarFiseObservacion(FiseObservacion fiseObservacion) 
			throws SQLException;
	
	void actualizarFiseObservacion(FiseObservacion fiseObservacion) 
			throws SQLException;
	
	void eliminarFiseObservacion(FiseObservacion fiseObservacion) 
			throws SQLException;
	
	FiseObservacion obtenerFiseObservacion(String id) 
			throws SQLException;
	
	List<FiseObservacion> buscarFiseObservacion(String id, String descripcion ) 
			throws SQLException;
	
	String obtenerIdObservacion() throws SQLException;

}
