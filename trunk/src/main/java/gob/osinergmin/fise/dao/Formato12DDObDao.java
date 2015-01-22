package gob.osinergmin.fise.dao;

import gob.osinergmin.fise.domain.FiseFormato12DD;
import gob.osinergmin.fise.domain.FiseFormato12DDOb;
import gob.osinergmin.fise.domain.FiseFormato12DDObPK;

import java.sql.SQLException;
import java.util.List;

public interface Formato12DDObDao {
	
	List<FiseFormato12DDOb> listarFormato12DDObByFormato12DD(FiseFormato12DD formato12DD);
	void eliminarFormato12DDOb(FiseFormato12DDOb fiseFormato12DDOb);
	
	long buscarMaximoItemObs12D(String codEmpresa,long anioPres,long mesPres,
			long anioEjec,long mesEjec,String etapa,long etapaEjec,long itemEtapa) throws SQLException;
	
	void insertarFiseFormato12DObs(FiseFormato12DDOb fiseFormato12DDOb) 
			throws SQLException;
	
	FiseFormato12DDOb obtenerFiseFormato12DDOb(FiseFormato12DDObPK id) 
			throws SQLException;

}
