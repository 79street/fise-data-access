package gob.osinergmin.fise.gart.service;

import gob.osinergmin.fise.bean.AutorizarReenvioBean;
import gob.osinergmin.fise.bean.CorreoBean;
import gob.osinergmin.fise.bean.EnvioDefinitivoBean;
import gob.osinergmin.fise.bean.Formato12A12BGeneric;
import gob.osinergmin.fise.bean.Formato12C12D13Generic;
import gob.osinergmin.fise.bean.Formato14Generic;
import gob.osinergmin.fise.bean.NotificacionBean;
import gob.osinergmin.fise.bean.VariacionCostosBean;
import gob.osinergmin.fise.domain.FiseControlEnvioPorGrupo;
import gob.osinergmin.fise.domain.FiseGrupoInformacion;

import java.util.List;
import java.util.Map;

public interface CommonGartService {
	
	int obtenerSecuencia();
	
	int validarFormatos_12A12B(Formato12A12BGeneric formato, String tipoFormato, 
			String usuario, String terminal);
	
	int validarFormatos_12C12D13A(Formato12C12D13Generic formato, String tipoFormato, 
			String usuario, String terminal);
	
	int validarFormatos_14(Formato14Generic formato14, String tipoFormato, String usuario, String terminal);
	
	List<CorreoBean> obtenerListaCorreosDestinatarios();
	
	List<CorreoBean> obtenerListaCorreosDestinatariosByEmpresa(String codEmpresa) 
			throws Exception;
	
	String obtenerEstadoProceso(String codEmpresa, String tipoFormato,
			long anoPresentacion, long mesPresentacion, String codEtapa);
	
	FiseGrupoInformacion obtenerFiseGrupoInformacionByPK(long id);
	
	Long obtenerIdGrupoInformacion(Long anioPresentacion,Long mesPresentacion,String frecuencia);
	
	boolean fechaEnvioCumplePlazo(String tipoFormato, String codEmpresa, 
			Long anoPresentacion, Long mesPresentacion, String etapa, String fechaEnvio);
	
	List<AutorizarReenvioBean> buscarFormatoReenvio(String codEmpresa,String anioPres,
			String mesPres,String formato,String etapa) throws Exception;
	
	String actualizarFormatoReenvio(AutorizarReenvioBean bean) throws Exception;
	
	List<NotificacionBean> buscarNotificacion(String codEmpresa,
			String flag,String etapa,Long idGrupoInf,String procesar) throws Exception;
	
	String notificarValidacionMensual(String codEmpresa, String etapa, 
			long idGrupoInf, String periodicidad, String user,String terminal) throws Exception;
	
	List<EnvioDefinitivoBean> buscarEnvioDefinitivo(String codEmpresa,
			String flag,String etapa,Long idGrupoInf) throws Exception;
	
	boolean actualizarFechaEnvioGeneral(Map<String, Object> params) throws Exception;
	
	FiseControlEnvioPorGrupo obtenerFiseControlEnvioByPK(String codEmpresa,String etapa,
			Long idGrupoInf,String periocidad) throws Exception;
	
	boolean esAdministradorFise(String userName) throws Exception;
	
	List<VariacionCostosBean> obtenerVariacionCostosByGrupoinfoFormatoConceptofinal(Long idGrupoInfo, String formato, String conceptoFinal) throws Exception;
    

}
