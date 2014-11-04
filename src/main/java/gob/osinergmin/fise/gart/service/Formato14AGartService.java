package gob.osinergmin.fise.gart.service;

import gob.osinergmin.fise.bean.Formato14ACBean;
import gob.osinergmin.fise.domain.FiseFormato14AC;
import gob.osinergmin.fise.domain.FiseFormato14ACPK;
import gob.osinergmin.fise.domain.FiseFormato14AD;

import java.util.HashMap;
import java.util.List;

public interface Formato14AGartService {
	
	FiseFormato14AD obtenerFormato14ADVigente(String codEmpresa, long anioVigencia, long idZonaBenef);
	FiseFormato14AC obtenerFormato14ACByPK(FiseFormato14ACPK fiseFormato14ACPK);
	List<FiseFormato14AC> buscarFormato14AC(String codEmpresa, long anioDesde, long mesDesde, long anioHasta, long mesHasta, String etapa);
	FiseFormato14AC registrarFormato14AC(Formato14ACBean formato) throws Exception;
	FiseFormato14AC modificarFormato14AC(Formato14ACBean formato, FiseFormato14AC fiseFormato14AC) throws Exception;
	void eliminarFormato14AC(FiseFormato14AC fiseFormato14AC);
	Formato14ACBean estructurarFormato14ABeanByFiseFormato14AC(FiseFormato14AC formato);
	HashMap<String, Object> mapearParametrosFormato14A(Formato14ACBean formato14ACBean);
	
}