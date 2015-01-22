package gob.osinergmin.fise.dao;

import gob.osinergmin.fise.domain.FiseFormato13AD;
import gob.osinergmin.fise.domain.FiseFormato13ADOb;
import gob.osinergmin.fise.domain.FiseFormato13ADObPK;

import java.sql.SQLException;
import java.util.List;

public interface Formato13ADObDao {
	
	List<FiseFormato13ADOb> listarFormato13ADObByFormato13AD(FiseFormato13AD formato13AD);
	void eliminarFormato13ADOb(FiseFormato13ADOb fiseFormato13ADOb);
	
	
	long buscarMaximoItemObs13A(String codEmpresa,long anioPres,long mesPres,
			String ubigeo,String sector,String etapa,long idZona) throws SQLException;
	
	void insertarFiseFormato13AObs(FiseFormato13ADOb fiseFormato13ADOb) 
			throws SQLException;
	
	FiseFormato13ADOb obtenerFiseFormato13ADOb(FiseFormato13ADObPK id) 
			throws SQLException;
	

}
