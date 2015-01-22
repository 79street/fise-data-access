package gob.osinergmin.fise.dao;

import gob.osinergmin.fise.domain.FiseFormato12CD;
import gob.osinergmin.fise.domain.FiseFormato12CDOb;
import gob.osinergmin.fise.domain.FiseFormato12CDObPK;

import java.sql.SQLException;
import java.util.List;

public interface Formato12CDObDao {
	
	List<FiseFormato12CDOb> listarFormato12CDObByFormato12CD(FiseFormato12CD formato12CD);
	void eliminarFormato12CDOb(FiseFormato12CDOb fiseFormato12CDOb);
	
	long buscarMaximoItemObs12C(String codEmpresa,long anioPres,long mesPres,
			long anioEjec,long mesEjec,String etapa,
			long etapaEjec,long itemEtapa) throws SQLException;
	
	void insertarFiseFormato12CObs(FiseFormato12CDOb fiseFormato12CDOb) 
			throws SQLException;
	
	FiseFormato12CDOb obtenerFiseFormato12CDOb(FiseFormato12CDObPK id) 
			throws SQLException;

}
