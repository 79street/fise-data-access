package gob.osinergmin.fise.dao;

import gob.osinergmin.fise.domain.FiseFormato14AD;
import gob.osinergmin.fise.domain.FiseFormato14ADOb;
import gob.osinergmin.fise.domain.FiseFormato14ADObPK;

import java.sql.SQLException;
import java.util.List;

public interface Formato14ADObDao {
	
	List<FiseFormato14ADOb> listarFormato14ADObByFormato14AD(FiseFormato14AD formato14AD);
	void eliminarFormato14ADOb(FiseFormato14ADOb fiseFormato14ADOb);
	
	long buscarMaximoItemObs14A(String codEmpresa,long anioPres,long mesPres,
			long anioIniVig,long anioFinVig,String etapa,long idZona) throws SQLException;
	
	void insertarFiseFormato14AObs(FiseFormato14ADOb fiseFormato14ADOb) 
			throws SQLException;
	
	FiseFormato14ADOb obtenerFiseFormato14ADOb(FiseFormato14ADObPK id) 
			throws SQLException;

}
