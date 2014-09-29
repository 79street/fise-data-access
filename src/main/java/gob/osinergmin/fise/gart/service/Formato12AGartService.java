package gob.osinergmin.fise.gart.service;

import gob.osinergmin.fise.domain.FiseFormato12AC;
import gob.osinergmin.fise.domain.FiseFormato12ACPK;

import java.util.List;

public interface Formato12AGartService {
	
	List<FiseFormato12AC> listarFormato12AC();
	FiseFormato12AC obtenerFormato12ACByPK(FiseFormato12ACPK fiseFormato12ACPK);
	void registrarFormato12AC(FiseFormato12AC fiseFormato12AC);
	void modificarFormato12AC(FiseFormato12AC fiseFormato12AC);
	void eliminarFormato12AC(FiseFormato12AC fiseFormato12AC);
	boolean existeFormato12AC(FiseFormato12AC fiseFormato12AC);
	List<FiseFormato12AC> buscarFormato12AC(String codEmpresa, long anioDesde, long mesDesde, long anioHasta, long mesHasta, String etapa);

}
