package gob.osinergmin.fise.dao;

import gob.osinergmin.fise.domain.FiseFormato14BD;
import gob.osinergmin.fise.domain.FiseFormato14BDOb;
import gob.osinergmin.fise.domain.FiseFormato14BDObPK;

import java.sql.SQLException;
import java.util.List;

public interface Formato14BDObDao {
	
	List<FiseFormato14BDOb> listarFormato14BDObByFormato14BD(FiseFormato14BD formato14BD);
	void eliminarFormato14BDOb(FiseFormato14BDOb fiseFormato14BDOb);
	
	long buscarMaximoItemObs14B(String codEmpresa,long anioPres,long mesPres,
			long anioIniVig,long anioFinVig,String etapa,long idZona) throws SQLException;
	
	void insertarFiseFormato14BObs(FiseFormato14BDOb fiseFormato14BDOb) 
			throws SQLException;
	
	FiseFormato14BDOb obtenerFiseFormato14BDOb(FiseFormato14BDObPK id) 
			throws SQLException;

}
