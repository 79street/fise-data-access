package gob.osinergmin.fise.dao;

import gob.osinergmin.base.dao.GenericDao;
import gob.osinergmin.fise.domain.FiseFormato12CC;
import gob.osinergmin.fise.domain.FiseFormato12CCPK;

import java.sql.SQLException;
import java.util.List;

public interface Formato12CCDao extends GenericDao {
	
	FiseFormato12CC obtenerFormato12CCByPK(FiseFormato12CCPK fiseFormato12CCPK);
	
	List<FiseFormato12CC> buscarFormato12CC(String codEmpresa, long anioDesde, 
			long mesDesde, long anioHasta, long mesHasta, String etapa);
	
	void registrarFormato12CC(FiseFormato12CC fiseFormato12CC);
	
	void modificarFormato12CC(FiseFormato12CC fiseFormato12CC);
	
	void eliminarFormato12CC(FiseFormato12CC fiseFormato12CC);
	
	boolean existeFormato12CC(FiseFormato12CC fiseFormato12CC);
	
	List<FiseFormato12CC> buscarFormato12CCReenvio(String codEmpresa, long anioPres, 
			long mesPres, String etapa) throws SQLException;
	
	List<FiseFormato12CC> buscarFormato12CCReporteObs(String codEmpresa, long idGrupoInf, 
			String etapa) throws SQLException;
	
}
