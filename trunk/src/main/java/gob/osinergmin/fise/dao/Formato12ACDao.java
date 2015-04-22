package gob.osinergmin.fise.dao;

import gob.osinergmin.base.dao.GenericDao;
import gob.osinergmin.fise.domain.FiseFormato12AC;
import gob.osinergmin.fise.domain.FiseFormato12ACPK;

import java.sql.SQLException;
import java.util.List;

public interface Formato12ACDao extends GenericDao {
	
	List<FiseFormato12AC> listarFormato12AC();
	
	FiseFormato12AC obtenerFormato12ACByPK(FiseFormato12ACPK fiseFormato12ACPK);
	
	void registrarFormato12AC(FiseFormato12AC fiseFormato12AC);
	
	void modificarFormato12AC(FiseFormato12AC fiseFormato12AC);
	
	void eliminarFormato12AC(FiseFormato12AC fiseFormato12AC);
	
	boolean existeFormato12AC(FiseFormato12AC fiseFormato12AC);
	
	List<FiseFormato12AC> buscarFormato12AC(String codEmpresa, long anioDesde, 
			long mesDesde, long anioHasta, long mesHasta, String etapa);
	
	int obtenerSecuencia();
	
	List<FiseFormato12AC> buscarFormato12ACReenvio(String codEmpresa, long anioPres, 
			long mesPres, String etapa) throws SQLException;
	
	List<FiseFormato12AC> buscarFormato12ACReporteObs(String codEmpresa, long idGrupoInf, 
			String etapa) throws SQLException;
	
}
