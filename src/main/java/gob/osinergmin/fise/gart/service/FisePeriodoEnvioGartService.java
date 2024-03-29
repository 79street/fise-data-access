package gob.osinergmin.fise.gart.service;

import gob.osinergmin.fise.bean.PeriodoEnvioBean;
import gob.osinergmin.fise.domain.FisePeriodoEnvio;

import java.util.Date;
import java.util.List;

public interface FisePeriodoEnvioGartService {
	
	String insertarDatosFisePeriodoEnvio(PeriodoEnvioBean bean) throws Exception;
	
	String actualizarDatosFisePeriodoEnvio(PeriodoEnvioBean bean) throws Exception;

    String eliminarDatosFisePeriodoEnvio(String id,String user,String terminal) throws Exception;
    
    FisePeriodoEnvio obtenerFisePeriodoEnvio(String id) throws Exception;
	
	List<FisePeriodoEnvio> listarFisePeriodoEnvioMesAnioEtapa(String codEmpresa, String nombreFormato);
	
	List<FisePeriodoEnvio> buscarFisePeriodoEnvio(String codEmpresa, Integer anioPres, 
			Integer mesPres, String formato,String etapa,
			String flagEnvio,String estado,Date fechaActual)throws Exception;
	
	PeriodoEnvioBean buscarFisePeriodoEnvioEditar(String id) throws Exception;
	
	List<FisePeriodoEnvio> listarFisePeriodoEnvioMesAnioEtapaCumplimiento(String frecuenciaFormato);
	
	String obtenerFlagEnvioConObs(String codEmpresa, Integer anioPres, 
			Integer mesPres, String formato,String etapa,String estado)  throws Exception;
	
	String listarPlazoMaximoEnvioObs(String codEmpresa,long anioPres,
    		long mesPres,String etapa,String formato) throws Exception;

}
