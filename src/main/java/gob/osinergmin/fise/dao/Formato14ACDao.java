package gob.osinergmin.fise.dao;

import gob.osinergmin.base.dao.GenericDao;
import gob.osinergmin.fise.domain.FiseFormato14AC;
import gob.osinergmin.fise.domain.FiseFormato14ACPK;

import java.sql.SQLException;
import java.util.List;

public interface Formato14ACDao extends GenericDao {
	
	FiseFormato14AC obtenerFormato14ACByPK(FiseFormato14ACPK fiseFormato14ACPK);
	
	List<FiseFormato14AC> buscarFormato14AC(String codEmpresa, long anioDesde, 
			long mesDesde, long anioHasta, long mesHasta, String etapa);
	
	void registrarFormato14AC(FiseFormato14AC fiseFormato14AC);
	
	void modificarFormato14AC(FiseFormato14AC fiseFormato14AC);
	
	void eliminarFormato14AC(FiseFormato14AC fiseFormato14AC);
	
	boolean existeFormato14AC(FiseFormato14AC fiseFormato14AC);
	
	List<FiseFormato14AC> buscarFormato14ACReenvio(String codEmpresa, long anioPres, 
			long mesPres, String etapa) throws SQLException;
	
}
