package gob.osinergmin.fise.dao;

import gob.osinergmin.fise.bean.CorreoBean;
import gob.osinergmin.fise.bean.CumplimientoReportBean;
import gob.osinergmin.fise.bean.Formato12A12BGeneric;
import gob.osinergmin.fise.bean.Formato12C12D13Generic;
import gob.osinergmin.fise.bean.Formato14Generic;
import gob.osinergmin.fise.bean.HistoricoCostosBean;
import gob.osinergmin.fise.bean.VariacionCostosBean;

import java.sql.SQLException;
import java.util.List;

public interface CommonDao {
	
	int obtenerSecuencia();
	
	int validarFormatos_12A12B(Formato12A12BGeneric formato, String tipoFormato, String usuario, String terminal);
	
	int validarFormatos_12C12D13A(Formato12C12D13Generic formato, String tipoFormato, String usuario, String terminal);
	
	int validarFormatos_14(Formato14Generic formato, String tipoFormato, String usuario, String terminal);
	
	List<CorreoBean> obtenerListaCorreosDestinatarios();

	Long obtenerIdGrupoInformacion(Long anioPresentacion,Long mesPresentacion, String frecuencia);
	
	String obtenerEstadoProceso(String codEmpresa, String tipoFormato, 
			long anoPresentacion, long mesPresentacion, String codEtapa);
	
	List<CumplimientoReportBean> listarFormatolistaDet(long p_ano, long p_mes, String p_solicitud);
	
	boolean fechaEnvioCumplePlazo(String tipoFormato, String codEmpresa, 
			Long anoPresentacion, Long mesPresentacion, String etapa, String fechaEnvio);
	
	List<Object[]> listarObsNotificacion(String codEmpresa,
			String etapa,String formato,Long idGrupoInf) throws SQLException;
	
	String notificarValidacionMensual(String codEmpresa, String etapa, 
			long idGrupoInf, String periodicidad, String user,String terminal) throws SQLException;
	
	List<Object[]> listarObsNotificacionProcesar(String codEmpresa,
			String etapa,String formato,Long idGrupoInf) throws SQLException;
	
	List<Object[]> listarEnvioDefinitivo(String codEmpresa,
			String etapa,String formato,Long idGrupoInf) throws SQLException;
	
	List<CorreoBean> obtenerListaCorreosDestinatariosByEmpresa(String codEmpresa);
	
	boolean esAdministradorFise(String userName);
	
	List<VariacionCostosBean> obtenerVariacionCostosByGrupoinfoFormatoConceptofinal(Long idGrupoInfo, 
			String formato, String conceptoFinal, String etapa);
	
	List<HistoricoCostosBean> obtenerHistoricoCostosByCodempresaFormato(String codEmpresa, String formato);
	
	String obtenerUltimaEtapaFormato(String formato,String codEmpresa, 
			long anioPres, long mesPres, long anioEjec,
			long mesEjec,long anioIniVig,long anioFinVig) throws SQLException;
	
	List<Object[]> listarObsNotificacionEliminar(String codEmpresa,
			String etapa,String formato,Long idGrupoInf) throws SQLException;
	
	String obtenerEstadoNotificacion(String codEmpresa,  
			long idGrupoInf, String etapa) throws SQLException;
			
}
