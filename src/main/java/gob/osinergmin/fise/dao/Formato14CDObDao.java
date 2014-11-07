package gob.osinergmin.fise.dao;

import gob.osinergmin.fise.domain.FiseFormato14CDOb;
import gob.osinergmin.fise.domain.FiseFormato14CDObPK;

import java.sql.SQLException;
import java.util.List;

public interface Formato14CDObDao {
	
    List<FiseFormato14CDOb> listarFiseFormato14CDOb();	
	
	void insertarFiseFormato14CDOb(FiseFormato14CDOb fiseFormato14CDOb) 
			throws SQLException;

	void actualizarFiseFormato14CDOb(FiseFormato14CDOb fiseFormato14CDOb) 
			throws SQLException;

	void eliminarFiseFormato14CDOb(FiseFormato14CDOb fiseFormato14CDOb) 
			throws SQLException;
	
	FiseFormato14CDOb obtenerFiseFormato14CDOb(FiseFormato14CDObPK id) 
			throws SQLException;
	
	List<FiseFormato14CDOb> buscarFiseFormato14CDOb(String codEmpresa, long anioPresentaion, 
			long mesPresentacion, long anioInicioVige, long anioFinVige, 
			String etapa,long idZonaBenef,long idTipoPersonal) throws SQLException;

}
