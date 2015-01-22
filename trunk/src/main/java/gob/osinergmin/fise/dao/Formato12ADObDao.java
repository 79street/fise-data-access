package gob.osinergmin.fise.dao;

import gob.osinergmin.fise.domain.FiseFormato12AC;
import gob.osinergmin.fise.domain.FiseFormato12AD;
import gob.osinergmin.fise.domain.FiseFormato12ADOb;
import gob.osinergmin.fise.domain.FiseFormato12ADObPK;

import java.sql.SQLException;
import java.util.List;

public interface Formato12ADObDao {
	
	int validarFormato12A(FiseFormato12AC fiseformato12AC, String tipoFormato, String usuario, String terminal);
	List<FiseFormato12ADOb> listarFormato12ADObByFormato12AD(FiseFormato12AD formato12AD);
	void eliminarFormato12ADOb(FiseFormato12ADOb fiseFormato12ADOb);
	
	long buscarMaximoItemObs12A(String codEmpresa,long anioPres,long mesPres,
			long anioEjec,long mesEjec,String etapa,long idZona) throws SQLException;
	
	void insertarFiseFormato12AObs(FiseFormato12ADOb fiseFormato12ADOb) 
			throws SQLException;
	
	FiseFormato12ADOb obtenerFiseFormato12ADOb(FiseFormato12ADObPK id) 
			throws SQLException;
	
}
