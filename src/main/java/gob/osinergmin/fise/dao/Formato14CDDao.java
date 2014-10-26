package gob.osinergmin.fise.dao;

import gob.osinergmin.base.dao.GenericDao;
import gob.osinergmin.fise.domain.FiseFormato14CD;
import gob.osinergmin.fise.domain.FiseFormato14CDPK;

import java.sql.SQLException;
import java.util.List;

public interface Formato14CDDao extends GenericDao {

	List<FiseFormato14CD> listarFormato14CD();	
	
	void insertarFiseFormato14CD(FiseFormato14CD fiseFormato14CD) 
			throws SQLException;

	void actualizarFiseFormato14CD(FiseFormato14CD fiseFormato14CD) 
			throws SQLException;

	void eliminarFiseFormato14CD(FiseFormato14CD fiseFormato14CD) 
			throws SQLException;
	
	FiseFormato14CD obtenerFiseFormato14CD(FiseFormato14CDPK id) 
			throws SQLException;
	
	
}
