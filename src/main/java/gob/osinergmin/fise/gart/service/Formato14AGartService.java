package gob.osinergmin.fise.gart.service;

import gob.osinergmin.fise.bean.Formato14ACBean;
import gob.osinergmin.fise.domain.FiseFormato14AC;
import gob.osinergmin.fise.domain.FiseFormato14ACPK;
import gob.osinergmin.fise.domain.FiseFormato14AD;
import gob.osinergmin.fise.domain.FiseFormato14ADOb;

import java.util.HashMap;
import java.util.List;

public interface Formato14AGartService {
	
	FiseFormato14AD obtenerFormato14ADVigente(String codEmpresa, long anioVigencia, long idZonaBenef);
	
	FiseFormato14AC obtenerFormato14ACByPK(FiseFormato14ACPK fiseFormato14ACPK);
	
	List<FiseFormato14AC> buscarFormato14AC(String codEmpresa, long anioDesde, 
			long mesDesde, long anioHasta, long mesHasta, String etapa);
	
	FiseFormato14AC registrarFormato14AC(Formato14ACBean formato) throws Exception;
	
	FiseFormato14AC modificarFormato14AC(Formato14ACBean formato, FiseFormato14AC fiseFormato14AC) throws Exception;
	
	void eliminarFormato14AC(FiseFormato14AC fiseFormato14AC);
	
	Formato14ACBean estructurarFormato14ABeanByFiseFormato14AC(FiseFormato14AC formato);
	
	HashMap<String, Object> mapearParametrosFormato14A(Formato14ACBean formato14ACBean);
	
	List<FiseFormato14ADOb> listarFormato14ADObByFormato14AD(FiseFormato14AD formato14AD);
	
	String modificarEnvioDefinitivoFormato14AC(String user,String terminal, 
			FiseFormato14AC fiseFormato14AC) throws Exception;
	
	void eliminarObservaciones14A(List<FiseFormato14ADOb> listaObs) throws Exception;
	
	String insertarObservacion14A(String codEmpresa,long anioPres,long mesPres,
			long anioIniVig,long anioFinVig,String etapa,long idZona, 
			String desObservacion,String user,String terminal,
			String idObsExistente,String tipoObservacion) throws Exception;
	
	String eliminarObservacion14A(String codEmpresa,long anioPres,long mesPres,
			long anioIniVig,long anioFinVig,String etapa,long idZona, 
			String idObservacion,long itemObservacion) throws Exception;
	
	List<FiseFormato14AC> buscarFormato14AReporteObs(String codEmpresa,
			long idGrupoInf,String etapa)throws Exception;
	
}
