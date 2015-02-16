package gob.osinergmin.fise.gart.service;

import gob.osinergmin.fise.bean.Formato13ACBean;
import gob.osinergmin.fise.bean.Formato13ADReportBean;
import gob.osinergmin.fise.domain.FiseFormato13AC;
import gob.osinergmin.fise.domain.FiseFormato13ACPK;
import gob.osinergmin.fise.domain.FiseFormato13AD;
import gob.osinergmin.fise.domain.FiseFormato13ADOb;

import java.util.HashMap;
import java.util.List;

import org.springframework.dao.DataIntegrityViolationException;

public interface Formato13AGartService {

	List<FiseFormato13AC> buscarFormato13AC(String codEmpresa, long anioDesde, long mesDesde, long anioHasta, long mesHasta, String etapa);
	List<FiseFormato13AD> listarFormato13ADByFormato13AC(FiseFormato13AC formato13AC);
	Formato13ACBean estructurarFormato13ABeanByFiseFormato13AC(FiseFormato13AC formato);
	HashMap<String, Object> mapearParametrosFormato13A(Formato13ACBean formato13ABean);
	List<Formato13ADReportBean> listarLocalidadesPorZonasBenefFormato13ADByFormato13AC(FiseFormato13AC formato13AC);

	FiseFormato13AC savecabecera(FiseFormato13AC fiseC)throws DataIntegrityViolationException;
	FiseFormato13AC getCabecera(FiseFormato13ACPK fiseFormato13ACPK);
	FiseFormato13AC updatecabecera(FiseFormato13AC fiseC)throws Exception;
	
	
	FiseFormato13AD savedetalle(FiseFormato13AD fiseD)throws Exception;
	FiseFormato13AC obtenerFormato13ACByPK(FiseFormato13ACPK fiseFormato13ACPK);
	FiseFormato13AD updatedetalle(FiseFormato13AD fiseD);
	
	Integer deletedetalle(String emp,Long anio,Long mes,String etapa);
	
	List<FiseFormato13ADOb> listarFormato13ADObByFormato13AD(FiseFormato13AD formato13AD);
	void eliminarCabecera(FiseFormato13AC fiseFormato13AC);
	void eliminarDetalle(FiseFormato13AD fiseFormato13AD);
	
	void eliminarObservaciones13A(List<FiseFormato13ADOb> listaObs) throws Exception;
	
	boolean existeFormato13AC(FiseFormato13AC fiseFormato13AC);
	boolean existeFormatoDetalleSectorTipico(FiseFormato13AC fiseFormato13AC, String codUbigeo, Long idZonaBenef);
	
	
	String insertarObservacion13A(String codEmpresa,long anioPres,long mesPres,
			String ubigeo,String sector,String etapa,long idZona,
			String desObservacion,String user,String terminal) throws Exception;
	
	String eliminarObservacion13A(String codEmpresa,long anioPres,long mesPres,
			String ubigeo,String sector,String etapa,long idZona,
			String idObservacion,long itemObservacion) throws Exception;
	
	FiseFormato13AC registrarFormato13ACregistrarFormato13AD(Formato13ACBean formato,List<Formato13ACBean> listaDetalleCarga) throws Exception;
	FiseFormato13AC modificarFormato13ACregistrarFormato13AD(Formato13ACBean formato, FiseFormato13AC fiseFormato13AC,List<Formato13ACBean> listaDetalleCarga) throws Exception;
	
}
