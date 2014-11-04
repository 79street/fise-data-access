package gob.osinergmin.fise.gart.service;

import gob.osinergmin.fise.domain.FiseFormato13AC;

import java.util.List;

public interface Formato13AGartService {

	List<FiseFormato13AC> buscarFormato13AC(String codEmpresa, long anioDesde, long mesDesde, long anioHasta, long mesHasta, String etapa);
}
