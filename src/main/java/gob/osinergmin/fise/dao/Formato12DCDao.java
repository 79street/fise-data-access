package gob.osinergmin.fise.dao;

import gob.osinergmin.base.dao.GenericDao;
import gob.osinergmin.fise.domain.FiseFormato12DC;
import gob.osinergmin.fise.domain.FiseFormato12DCPK;

import java.sql.SQLException;
import java.util.List;

public interface Formato12DCDao extends GenericDao {
	
	FiseFormato12DC obtenerFormato12DCByPK(FiseFormato12DCPK fiseFormato12DCPK);
	List<FiseFormato12DC> buscarFormato12DC(String codEmpresa, long anioDesde, long mesDesde, long anioHasta, long mesHasta, String etapa);
	void registrarFormato12DC(FiseFormato12DC fiseFormato12DC);
	void modificarFormato12DC(FiseFormato12DC fiseFormato12DC);
	void eliminarFormato12DC(FiseFormato12DC fiseFormato12DC);
	boolean existeFormato12DC(FiseFormato12DC fiseFormato12DC);
	List<FiseFormato12DC> buscarFormato12DCReenvio(String codEmpresa, long anioPres, long mesPres, String etapa) throws SQLException;
	
}
