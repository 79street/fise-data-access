package gob.osinergmin.fise.dao;


import gob.osinergmin.base.dao.GenericDao;
import gob.osinergmin.fise.domain.FiseFormato14CC;
import gob.osinergmin.fise.domain.FiseFormato14CCPK;

import java.sql.SQLException;
import java.util.List;

public interface Formato14CCDao extends GenericDao {
	

	List<FiseFormato14CC> listarFormato14CC();
	
	void insertarFiseFormato14CC(FiseFormato14CC fiseFormato14CC) 
			throws SQLException;

	void actualizarFiseFormato14CC(FiseFormato14CC fiseFormato14CC) 
			throws SQLException;

	void eliminarFiseFormato14CC(FiseFormato14CC fiseFormato14CC) 
			throws SQLException;
	
	FiseFormato14CC obtenerFormato14CC(FiseFormato14CCPK id) 
			throws SQLException;
	
	public List<FiseFormato14CC> buscarFiseFormato14CC(String codEmpresa, long fechaDesde,
			long fechaHasta, String etapa) throws SQLException;
	
	List<FiseFormato14CC> buscarFormato14CCReenvio(String codEmpresa, long anioPres, 
			long mesPres, String etapa) throws SQLException;
	
	List<FiseFormato14CC> buscarFormato14CCReporteObs(String codEmpresa, long idGrupoInf, 
			String etapa) throws SQLException;
	
	
}
