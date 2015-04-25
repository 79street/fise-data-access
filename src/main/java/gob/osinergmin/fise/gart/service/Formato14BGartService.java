package gob.osinergmin.fise.gart.service;

import gob.osinergmin.fise.bean.Formato14BCBean;
import gob.osinergmin.fise.domain.FiseFormato14BC;
import gob.osinergmin.fise.domain.FiseFormato14BCPK;
import gob.osinergmin.fise.domain.FiseFormato14BD;
import gob.osinergmin.fise.domain.FiseFormato14BDOb;

import java.util.HashMap;
import java.util.List;

public interface Formato14BGartService {
	
	FiseFormato14BD obtenerFormato14BDVigente(String codEmpresa, long anioVigencia, long idZonaBenef);
	
	FiseFormato14BD getCostoUnitarioByEmpAnioZona(String codEmpresa, Integer anio, 
			Integer mes,Integer idZona,String etp);
	
	List<FiseFormato14BD> getLstCostoUnitarioByEmpAnio(String codEmpresa, Integer anio,
			Integer mes, Integer idZona,String etp);
	
	FiseFormato14BC obtenerFormato14BCByPK(FiseFormato14BCPK fiseFormato14BCPK);
	
	List<FiseFormato14BC> buscarFormato14BC(String codEmpresa, long anioDesde, long mesDesde, 
			long anioHasta, long mesHasta, String etapa);
	
	FiseFormato14BC registrarFormato14BC(Formato14BCBean formato) throws Exception;
	
	FiseFormato14BC modificarFormato14BC(Formato14BCBean formato, FiseFormato14BC fiseFormato14BC) throws Exception;
	
	void eliminarFormato14BC(FiseFormato14BC fiseFormato14BC);
	
	Formato14BCBean estructurarFormato14BBeanByFiseFormato14BC(FiseFormato14BC formato);
	
	HashMap<String, Object> mapearParametrosFormato14B(Formato14BCBean formato14BCBean);
	
	List<FiseFormato14BDOb> listarFormato14BDObByFormato14BD(FiseFormato14BD formato14BD);
	
	String modificarEnvioDefinitivoFormato14BC(String user,String terminal, 
			FiseFormato14BC fiseFormato14BC) throws Exception;

	void eliminarObservaciones14B(List<FiseFormato14BDOb> listaObs) throws Exception;
	
	String insertarObservacion14B(String codEmpresa,long anioPres,long mesPres,
			long anioIniVig,long anioFinVig,String etapa,long idZona, 
			String desObservacion,String user,String terminal,
			String idObsExistente,String tipoObservacion) throws Exception;
	
	String eliminarObservacion14B(String codEmpresa,long anioPres,long mesPres,
			long anioIniVig,long anioFinVig,String etapa,long idZona, 
			String idObservacion,long itemObservacion) throws Exception;
	
	List<FiseFormato14BC> buscarFormato14BReporteObs(String codEmpresa,
			long idGrupoInf,String etapa)throws Exception;
}
