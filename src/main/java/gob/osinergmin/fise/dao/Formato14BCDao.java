package gob.osinergmin.fise.dao;


import gob.osinergmin.base.dao.GenericDao;
import gob.osinergmin.fise.domain.FiseFormato14BC;
import gob.osinergmin.fise.domain.FiseFormato14BCPK;

import java.sql.SQLException;
import java.util.List;

public interface Formato14BCDao extends GenericDao {

	FiseFormato14BC obtenerFormato14BCByPK(FiseFormato14BCPK fiseFormato14BCPK);
	
	List<FiseFormato14BC> buscarFormato14BC(String codEmpresa, long anioDesde, 
			long mesDesde, long anioHasta, long mesHasta, String etapa);
	
	void registrarFormato14BC(FiseFormato14BC fiseFormato14BC);
	
	void modificarFormato14BC(FiseFormato14BC fiseFormato14BC);
	
	void eliminarFormato14BC(FiseFormato14BC fiseFormato14BC);
	
	boolean existeFormato14BC(FiseFormato14BC fiseFormato14BC);
	
	List<FiseFormato14BC> buscarFormato14BCReenvio(String codEmpresa, long anioPres, 
			long mesPres, String etapa) throws SQLException;
	
}
