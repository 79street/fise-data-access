package gob.osinergmin.fise.gart.service;

import gob.osinergmin.fise.domain.FiseFormato13AC;
import gob.osinergmin.fise.domain.FiseFormato13AD;

import java.util.List;

public interface Formato13AGartService {

	List<FiseFormato13AC> buscarFormato13AC(String codEmpresa, long anioDesde, long mesDesde, long anioHasta, long mesHasta, String etapa);
	List<FiseFormato13AD> listarFormato13ADByFormato13AC(FiseFormato13AC formato13AC);
}
