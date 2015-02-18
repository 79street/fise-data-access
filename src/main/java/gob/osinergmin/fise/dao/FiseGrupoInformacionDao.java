package gob.osinergmin.fise.dao;

import gob.osinergmin.fise.domain.FiseGrupoInformacion;

import java.sql.SQLException;
import java.util.List;

public interface FiseGrupoInformacionDao {
	
	FiseGrupoInformacion obtenerFiseGrupoInformacionByPK(long id) throws SQLException;
	
	List<FiseGrupoInformacion> listarGrupoInformacion(String tipo,String flag) throws SQLException;
	
	void insertarGrupoInformacion(FiseGrupoInformacion fiseGrupoInformacion) 
			throws SQLException;
	
	void actualizarGrupoInformacion(FiseGrupoInformacion fiseGrupoInformacion) 
			throws SQLException;
	
	void eliminarGrupoInformacion(FiseGrupoInformacion fiseGrupoInformacion) 
			throws SQLException;
	
	List<FiseGrupoInformacion> buscarGrupoInformacion(String descripcion,String tipo,Integer estado ) 
			throws SQLException;
	
	boolean verificarGrupoInfBienal(String tipo,Integer estado) throws SQLException;
	
	boolean verificarGrupoInfMensual(String tipo,Long anio,Long mes,Integer estado) throws SQLException;
	
	

}
