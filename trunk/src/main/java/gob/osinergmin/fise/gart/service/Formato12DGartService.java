package gob.osinergmin.fise.gart.service;

import gob.osinergmin.fise.bean.Formato12DCBean;
import gob.osinergmin.fise.domain.FiseFormato12DC;
import gob.osinergmin.fise.domain.FiseFormato12DCPK;
import gob.osinergmin.fise.domain.FiseFormato12DD;
import gob.osinergmin.fise.domain.FiseFormato12DDOb;

import java.util.HashMap;
import java.util.List;

public interface Formato12DGartService {
	
	FiseFormato12DC obtenerFormato12DCByPK(FiseFormato12DCPK fiseFormato12DCPK);
	List<FiseFormato12DC> buscarFormato12DC(String codEmpresa, long anioDesde, long mesDesde, long anioHasta, long mesHasta, String etapa);
	List<FiseFormato12DD> listarFormato12DDByFormato12DC(FiseFormato12DC formato12DC);
	FiseFormato12DC registrarFormato12DCregistrarFormato12DD(Formato12DCBean formato) throws Exception;
	FiseFormato12DC modificarFormato12DCregistrarFormato12DD(Formato12DCBean formato, FiseFormato12DC fiseFormato12DC) throws Exception;
	FiseFormato12DC modificarFormato12DCmodificarFormato12DD(Formato12DCBean formato, FiseFormato12DC fiseFormato12DC) throws Exception;
	void eliminarFormato12DC(FiseFormato12DC fiseFormato12DC);
	void eliminarFormato12DD(FiseFormato12DD fiseFormato12DD);
	Formato12DCBean estructurarFormato12DBeanByFiseFormato12DC(FiseFormato12DC formato);
	HashMap<String, Object> mapearParametrosFormato12D(Formato12DCBean formato12DBean);
	List<FiseFormato12DDOb> listarFormato12DDObByFormato12DD(FiseFormato12DD formato12DD);
	String modificarEnvioDefinitivoFormato12DC(String user,String terminal, FiseFormato12DC fiseFormato12DC) throws Exception;
	
	void eliminarObservaciones12D(List<FiseFormato12DDOb> listaObs) throws Exception;
}
