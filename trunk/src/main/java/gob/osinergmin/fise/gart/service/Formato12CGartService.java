package gob.osinergmin.fise.gart.service;

import gob.osinergmin.fise.domain.FiseFormato12CC;
import gob.osinergmin.fise.domain.FiseFormato12CCPK;
import gob.osinergmin.fise.domain.FiseFormato12CD;

import java.util.List;

public interface Formato12CGartService {
	
	FiseFormato12CC obtenerFormato12CCByPK(FiseFormato12CCPK fiseFormato12CCPK);
	List<FiseFormato12CC> buscarFormato12CC(String codEmpresa, long anioDesde, long mesDesde, long anioHasta, long mesHasta, String etapa);
	List<FiseFormato12CD> listarFormato12CDByFormato12CC(FiseFormato12CC formato12CC);
	
}
