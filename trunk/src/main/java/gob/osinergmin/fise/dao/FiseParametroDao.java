package gob.osinergmin.fise.dao;

import gob.osinergmin.base.dao.GenericDao;
import gob.osinergmin.fise.domain.FiseParametro;

import java.sql.SQLException;
import java.util.List;

public interface FiseParametroDao extends GenericDao {
	
	List<FiseParametro> listarFiseParametro();
	
	void insertarFiseParametro(FiseParametro fiseParametro) throws SQLException;
	
	void actualizarFiseParametro(FiseParametro fiseParametro) throws SQLException;
	
	void eliminarFiseParametro(FiseParametro fiseParametro) throws SQLException;
	
	FiseParametro obtenerFiseParametro(String codigo) throws SQLException;
	
	List<FiseParametro> buscarFiseParametro(String codigo, String nombre ) throws SQLException;


}
