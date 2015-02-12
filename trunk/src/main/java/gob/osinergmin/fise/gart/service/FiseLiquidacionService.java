package gob.osinergmin.fise.gart.service;

import gob.osinergmin.fise.bean.LiquidacionBean;
import gob.osinergmin.fise.domain.FiseDescripcionActividade;

import java.util.List;

public interface FiseLiquidacionService {
	
	List<LiquidacionBean> listarLiquidaciones(String codEmpresa, 
			long idGrupoInf,String usuario,String terminal,String flagBusq)
			throws Exception;
	
	String eliminarDatosLiquidacion(Long id) throws Exception;
	
	String prepararLiquidacion(List<LiquidacionBean> lista, 
			String usuario,String terminal) throws Exception;
	
	String liquidarFormatos(List<LiquidacionBean> lista, 
			String usuario,String terminal) throws Exception;
	
	String insertarDatosLiquidacionesMotivosNo(LiquidacionBean bean) 
			throws Exception;
	
	String actualizarDatosLiquidacionesMotivosNo(LiquidacionBean bean) 
			throws Exception;
	
	String eliminarDatosLiquidacionesMotivosNo(LiquidacionBean bean) 
			throws Exception;
	
	List<LiquidacionBean> buscarDatosLiquidacionesMotivosNo(long correlativo, long item) 
			throws Exception;
	
	LiquidacionBean obtenerDatosLiquidacionesMotivosNo(long correlativo, long item) 
			throws Exception;
	
	String  obtenerUltimaEtapa(String formato,String codEmpresa, 
			long anioPres, long mesPres, long anioEjec,
			long mesEjec,long anioIniVig,long anioFinVig)throws Exception;
	
	List<FiseDescripcionActividade> listarDescripcionActividades(String formato) 
			throws Exception;

}
