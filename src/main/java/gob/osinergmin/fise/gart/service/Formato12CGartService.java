package gob.osinergmin.fise.gart.service;

import gob.osinergmin.fise.bean.Formato12CCBean;
import gob.osinergmin.fise.domain.FiseFormato12CC;
import gob.osinergmin.fise.domain.FiseFormato12CCPK;
import gob.osinergmin.fise.domain.FiseFormato12CD;
import gob.osinergmin.fise.domain.FiseFormato12CDOb;

import java.util.HashMap;
import java.util.List;

public interface Formato12CGartService {
	
	FiseFormato12CC obtenerFormato12CCByPK(FiseFormato12CCPK fiseFormato12CCPK);
	List<FiseFormato12CC> buscarFormato12CC(String codEmpresa, long anioDesde, long mesDesde, long anioHasta, long mesHasta, String etapa);
	List<FiseFormato12CD> listarFormato12CDByFormato12CC(FiseFormato12CC formato12CC);
	FiseFormato12CC registrarFormato12CCregistrarFormato12CD(Formato12CCBean formato) throws Exception;
	FiseFormato12CC modificarFormato12CCregistrarFormato12CD(Formato12CCBean formato, FiseFormato12CC fiseFormato12CC) throws Exception;
	FiseFormato12CC modificarFormato12CCmodificarFormato12CD(Formato12CCBean formato, FiseFormato12CC fiseFormato12CC) throws Exception;
	void eliminarFormato12CC(FiseFormato12CC fiseFormato12CC);
	void eliminarFormato12CD(FiseFormato12CD fiseFormato12CD);
	Formato12CCBean estructurarFormato12CBeanByFiseFormato12CC(FiseFormato12CC formato);
	HashMap<String, Object> mapearParametrosFormato12C(Formato12CCBean formato12CBean);
	List<FiseFormato12CDOb> listarFormato12CDObByFormato12CD(FiseFormato12CD formato12CD);
	String modificarEnvioDefinitivoFormato12CC(String user,String terminal, FiseFormato12CC fiseFormato12CC) throws Exception;
	
	void eliminarObservaciones12C(List<FiseFormato12CDOb> listaObs) throws Exception;
}
