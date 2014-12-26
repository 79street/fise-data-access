package gob.osinergmin.fise.gart.service.impl;

import gob.osinergmin.fise.bean.AutorizarReenvioBean;
import gob.osinergmin.fise.bean.CorreoBean;
import gob.osinergmin.fise.bean.EnvioDefinitivoBean;
import gob.osinergmin.fise.bean.Formato12A12BGeneric;
import gob.osinergmin.fise.bean.Formato12C12D13Generic;
import gob.osinergmin.fise.bean.Formato14Generic;
import gob.osinergmin.fise.bean.NotificacionBean;
import gob.osinergmin.fise.constant.FiseConstants;
import gob.osinergmin.fise.dao.CommonDao;
import gob.osinergmin.fise.dao.FiseControlEnvioDao;
import gob.osinergmin.fise.dao.FiseGrupoInformacionDao;
import gob.osinergmin.fise.dao.Formato12ACDao;
import gob.osinergmin.fise.dao.Formato12BCDao;
import gob.osinergmin.fise.dao.Formato12CCDao;
import gob.osinergmin.fise.dao.Formato12DCDao;
import gob.osinergmin.fise.dao.Formato13ACDao;
import gob.osinergmin.fise.dao.Formato14ACDao;
import gob.osinergmin.fise.dao.Formato14BCDao;
import gob.osinergmin.fise.dao.Formato14CCDao;
import gob.osinergmin.fise.domain.FiseControlEnvioPorGrupo;
import gob.osinergmin.fise.domain.FiseControlEnvioPorGrupoPK;
import gob.osinergmin.fise.domain.FiseFormato12AC;
import gob.osinergmin.fise.domain.FiseFormato12ACPK;
import gob.osinergmin.fise.domain.FiseFormato12BC;
import gob.osinergmin.fise.domain.FiseFormato12BCPK;
import gob.osinergmin.fise.domain.FiseFormato12CC;
import gob.osinergmin.fise.domain.FiseFormato12CCPK;
import gob.osinergmin.fise.domain.FiseFormato12DC;
import gob.osinergmin.fise.domain.FiseFormato12DCPK;
import gob.osinergmin.fise.domain.FiseFormato13AC;
import gob.osinergmin.fise.domain.FiseFormato13ACPK;
import gob.osinergmin.fise.domain.FiseFormato14AC;
import gob.osinergmin.fise.domain.FiseFormato14ACPK;
import gob.osinergmin.fise.domain.FiseFormato14BC;
import gob.osinergmin.fise.domain.FiseFormato14BCPK;
import gob.osinergmin.fise.domain.FiseFormato14CC;
import gob.osinergmin.fise.domain.FiseFormato14CCPK;
import gob.osinergmin.fise.domain.FiseGrupoInformacion;
import gob.osinergmin.fise.gart.service.CommonGartService;
import gob.osinergmin.fise.util.FechaUtil;
import gob.osinergmin.fise.util.FormatoUtil;

import java.math.BigDecimal;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

@Service(value="commonGartServiceImpl")
public class CommonGartServiceImpl implements CommonGartService {
	
	Logger logger=Logger.getLogger(CommonGartServiceImpl.class);
	
	@Autowired
	@Qualifier("commonDaoImpl")
	private CommonDao commonDao;
	
	@Autowired
	@Qualifier("fiseGrupoInformacionDaoImpl")
	private FiseGrupoInformacionDao fiseGrupoInformacionDao;
	
	@Autowired
	@Qualifier("fiseControlEnvioDaoImpl")
	private FiseControlEnvioDao fiseControlEnvioDao;
	
	
	@Autowired
	@Qualifier("formato12ACDaoImpl")
	private Formato12ACDao formato12ACDao;	
	
	@Autowired
	@Qualifier("formato12BCDaoImpl")
	private Formato12BCDao formato12BCDao;
	
	@Autowired
	@Qualifier("formato12CCDaoImpl")
	private Formato12CCDao formato12CCDao;
	
	@Autowired
	@Qualifier("formato12DCDaoImpl")
	private Formato12DCDao formato12DCDao;
	
	@Autowired
	@Qualifier("formato13ACDaoImpl")
	private Formato13ACDao formato13ACDao;	
	
	@Autowired
	@Qualifier("formato14ACDaoImpl")
	private Formato14ACDao formato14ACDao;
	
	@Autowired
	@Qualifier("formato14BCDaoImpl")
	private Formato14BCDao formato14BCDao;	
	
	@Autowired
	@Qualifier("formato14CCDaoImpl")
	private Formato14CCDao formato14CCDao;
	
	
	
	
	
	
	/*******Implementaion de metodos*********/	
	@Override
	@Transactional
	public int obtenerSecuencia() {
		return commonDao.obtenerSecuencia();
	}	
	
	@Override
	@Transactional(propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
	public int validarFormatos_12A12B(Formato12A12BGeneric formato12, 
			String tipoFormato, String usuario, String terminal) {
		return commonDao.validarFormatos_12A12B(formato12, tipoFormato, usuario, terminal);
	}
	
	@Override
	@Transactional(propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
	public int validarFormatos_12C12D13A(Formato12C12D13Generic formato, 
			String tipoFormato, String usuario, String terminal) {
		return commonDao.validarFormatos_12C12D13A(formato, tipoFormato, usuario, terminal);
	}
	
	@Override
	@Transactional(propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
	public int validarFormatos_14(Formato14Generic formato14, String tipoFormato,String usuario, String terminal) {
		return commonDao.validarFormatos_14(formato14, tipoFormato, usuario, terminal);
	}

	@Override
	@Transactional
	public List<CorreoBean> obtenerListaCorreosDestinatarios() {
		return commonDao.obtenerListaCorreosDestinatarios();
	}
	
	@Override
	@Transactional
	public String obtenerEstadoProceso(String codEmpresa, String tipoFormato, 
			long anoPresentacion, long mesPresentacion, String codEtapa) {
		return commonDao.obtenerEstadoProceso(codEmpresa, tipoFormato, 
				anoPresentacion, mesPresentacion, codEtapa);
	}
	
	@Override
	@Transactional
	public FiseGrupoInformacion obtenerFiseGrupoInformacionByPK(long id) {
		FiseGrupoInformacion dto = null;
		try {
			dto = fiseGrupoInformacionDao.obtenerFiseGrupoInformacionByPK(id);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return dto;
	}
	
	@Override
	@Transactional
	public Long obtenerIdGrupoInformacion(Long anioPresentacion, Long mesPresentacion,String frecuencia) {
		return commonDao.obtenerIdGrupoInformacion(anioPresentacion, mesPresentacion, frecuencia);
	}
	
	@Override
	@Transactional
	public boolean fechaEnvioCumplePlazo(String tipoFormato, String codEmpresa,
			Long anoPresentacion, Long mesPresentacion, String etapa, String fechaEnvio) {
		return commonDao.fechaEnvioCumplePlazo(tipoFormato, codEmpresa, 
				anoPresentacion, mesPresentacion, etapa, fechaEnvio);
	}
	
	
	
	@Override
	@Transactional
	public List<AutorizarReenvioBean> buscarFormatoReenvio(String codEmpresa,String anioPres,
			String mesPres,String formato,String etapa) throws Exception{
		
		List<AutorizarReenvioBean> lista = new ArrayList<AutorizarReenvioBean>();
		try {
			if(FiseConstants.NOMBRE_FORMATO_12A.equals(formato)){ 
				
				List<FiseFormato12AC> lista12A = formato12ACDao.buscarFormato12ACReenvio(codEmpresa,
						Long.valueOf(anioPres), Long.valueOf(mesPres), etapa);
				for(FiseFormato12AC f12A: lista12A){
					AutorizarReenvioBean r = new AutorizarReenvioBean();
					r.setCodEmpresa(f12A.getId().getCodEmpresa()); 
					r.setAnioPres(""+f12A.getId().getAnoPresentacion()); 
					r.setMesPres(""+f12A.getId().getMesPresentacion()); 
					r.setAnioEjec(""+f12A.getId().getAnoEjecucionGasto());
					r.setMesEjec(""+f12A.getId().getMesEjecucionGasto());
					r.setEtapa(f12A.getId().getEtapa()); 
					r.setEstado(FiseConstants.ESTADO_FECHAENVIO_ENVIADO);
					r.setFormato(formato); 
					lista.add(r);
				}			
			}else if(FiseConstants.NOMBRE_FORMATO_12B.equals(formato)){
				
				List<FiseFormato12BC> lista12B = formato12BCDao.buscarFormato12BCReenvio(codEmpresa,
						Integer.valueOf(anioPres), Integer.valueOf(mesPres), etapa);
				for(FiseFormato12BC f12B: lista12B){
					AutorizarReenvioBean r = new AutorizarReenvioBean();
					r.setCodEmpresa(f12B.getId().getCodEmpresa()); 
					r.setAnioPres(""+f12B.getId().getAnoPresentacion()); 
					r.setMesPres(""+f12B.getId().getMesPresentacion()); 
					r.setAnioEjec(""+f12B.getId().getAnoEjecucionGasto());
					r.setMesEjec(""+f12B.getId().getMesEjecucionGasto());
					r.setEtapa(f12B.getId().getEtapa()); 
					r.setEstado(FiseConstants.ESTADO_FECHAENVIO_ENVIADO);
					r.setFormato(formato); 
					lista.add(r);
				}			
				
			}else if(FiseConstants.NOMBRE_FORMATO_12C.equals(formato)){ 
				
				List<FiseFormato12CC> lista12C = formato12CCDao.buscarFormato12CCReenvio(codEmpresa,
						Long.valueOf(anioPres), Long.valueOf(mesPres), etapa);
				for(FiseFormato12CC f12C: lista12C){
					AutorizarReenvioBean r = new AutorizarReenvioBean();
					r.setCodEmpresa(f12C.getId().getCodEmpresa()); 
					r.setAnioPres(""+f12C.getId().getAnoPresentacion()); 
					r.setMesPres(""+f12C.getId().getMesPresentacion()); 				
					r.setEtapa(f12C.getId().getEtapa()); 
					r.setEstado(FiseConstants.ESTADO_FECHAENVIO_ENVIADO);
					r.setFormato(formato); 
					lista.add(r);
				}			
				
			}else if(FiseConstants.NOMBRE_FORMATO_12D.equals(formato)){ 
				
				List<FiseFormato12DC> lista12D = formato12DCDao.buscarFormato12DCReenvio(codEmpresa,
						Long.valueOf(anioPres), Long.valueOf(mesPres), etapa);
				for(FiseFormato12DC f12D: lista12D){
					AutorizarReenvioBean r = new AutorizarReenvioBean();
					r.setCodEmpresa(f12D.getId().getCodEmpresa()); 
					r.setAnioPres(""+f12D.getId().getAnoPresentacion()); 
					r.setMesPres(""+f12D.getId().getMesPresentacion()); 				
					r.setEtapa(f12D.getId().getEtapa()); 
					r.setEstado(FiseConstants.ESTADO_FECHAENVIO_ENVIADO);
					r.setFormato(formato); 
					lista.add(r);
				}			
				
			}else if(FiseConstants.NOMBRE_FORMATO_13A.equals(formato)){ 
				
				List<FiseFormato13AC> lista13A = formato13ACDao.buscarFormato13ACReenvio(codEmpresa,
						Long.valueOf(anioPres), Long.valueOf(mesPres), etapa);
				for(FiseFormato13AC f13A: lista13A){
					AutorizarReenvioBean r = new AutorizarReenvioBean();
					r.setCodEmpresa(f13A.getId().getCodEmpresa()); 
					r.setAnioPres(""+f13A.getId().getAnoPresentacion()); 
					r.setMesPres(""+f13A.getId().getMesPresentacion());			
					r.setEtapa(f13A.getId().getEtapa()); 
					r.setEstado(FiseConstants.ESTADO_FECHAENVIO_ENVIADO); 
					r.setFormato(formato); 
					lista.add(r);
				}		
			}else if(FiseConstants.NOMBRE_FORMATO_14A.equals(formato)){
				
				List<FiseFormato14AC> lista14A = formato14ACDao.buscarFormato14ACReenvio(codEmpresa,
						Long.valueOf(anioPres), Long.valueOf(mesPres), etapa);
				for(FiseFormato14AC f14A: lista14A){
					AutorizarReenvioBean r = new AutorizarReenvioBean();
					r.setCodEmpresa(f14A.getId().getCodEmpresa()); 
					r.setAnioPres(""+f14A.getId().getAnoPresentacion()); 
					r.setMesPres(""+f14A.getId().getMesPresentacion()); 
					r.setAnioIniVig(""+f14A.getId().getAnoInicioVigencia());
					r.setAnioFinVig(""+f14A.getId().getAnoFinVigencia());
					r.setEtapa(f14A.getId().getEtapa()); 
					r.setEstado(FiseConstants.ESTADO_FECHAENVIO_ENVIADO); 
					r.setFormato(formato); 
					lista.add(r);
				}
				
			}else if(FiseConstants.NOMBRE_FORMATO_14B.equals(formato)){ 
				
				List<FiseFormato14BC> lista14B = formato14BCDao.buscarFormato14BCReenvio(codEmpresa,
						Long.valueOf(anioPres), Long.valueOf(mesPres), etapa);
				for(FiseFormato14BC f14B: lista14B){
					AutorizarReenvioBean r = new AutorizarReenvioBean();
					r.setCodEmpresa(f14B.getId().getCodEmpresa()); 
					r.setAnioPres(""+f14B.getId().getAnoPresentacion()); 
					r.setMesPres(""+f14B.getId().getMesPresentacion()); 
					r.setAnioIniVig(""+f14B.getId().getAnoInicioVigencia());
					r.setAnioFinVig(""+f14B.getId().getAnoFinVigencia());
					r.setEtapa(f14B.getId().getEtapa()); 
					r.setEstado(FiseConstants.ESTADO_FECHAENVIO_ENVIADO); 
					r.setFormato(formato); 
					lista.add(r);
				}			
			}else if(FiseConstants.NOMBRE_FORMATO_14C.equals(formato)){ 
				
				List<FiseFormato14CC> lista14C = formato14CCDao.buscarFormato14CCReenvio(codEmpresa,
						Long.valueOf(anioPres), Long.valueOf(mesPres), etapa);
				for(FiseFormato14CC f14C: lista14C){
					AutorizarReenvioBean r = new AutorizarReenvioBean();
					r.setCodEmpresa(f14C.getId().getCodEmpresa()); 
					r.setAnioPres(""+f14C.getId().getAnoPresentacion()); 
					r.setMesPres(""+f14C.getId().getMesPresentacion()); 
					r.setAnioIniVig(""+f14C.getId().getAnoInicioVigencia());
					r.setAnioFinVig(""+f14C.getId().getAnoFinVigencia());
					r.setEtapa(f14C.getId().getEtapa()); 
					r.setEstado(FiseConstants.ESTADO_FECHAENVIO_ENVIADO);
					r.setFormato(formato); 
					lista.add(r);
				}			
			}	
		} catch (Exception e) {		
		  logger.error("Error al listar formato reenvio:  "+e);
		}		
		return lista;
	}	
	
	
	
	@Override
	@Transactional(propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
	public String actualizarFormatoReenvio(AutorizarReenvioBean bean) throws Exception{
		String valor ="1";
		try {
			if(FiseConstants.NOMBRE_FORMATO_12A.equals(bean.getFormato())){ 			
				FiseFormato12ACPK id = new FiseFormato12ACPK();
				id.setCodEmpresa(bean.getCodEmpresa());
				id.setAnoPresentacion(Long.valueOf(bean.getAnioPres())); 
				id.setMesPresentacion(Long.valueOf(bean.getMesPres()));
				id.setEtapa(bean.getEtapa());
				id.setAnoEjecucionGasto(Long.valueOf(bean.getAnioEjec())); 
				id.setMesEjecucionGasto(Long.valueOf(bean.getMesEjec())); 
				FiseFormato12AC f = formato12ACDao.obtenerFormato12ACByPK(id);
				f.setFechaEnvioDefinitivo(null);
				f.setUsuarioActualizacion(bean.getUsuario());
				f.setFechaActualizacion(FechaUtil.obtenerFechaActual());
				f.setTerminalActualizacion(bean.getTerminal()); 
				formato12ACDao.modificarFormato12AC(f); 	
				
			}else if(FiseConstants.NOMBRE_FORMATO_12B.equals(bean.getFormato())){	
				 FiseFormato12BCPK id = new FiseFormato12BCPK();
				 id.setCodEmpresa(bean.getCodEmpresa());
				 id.setAnoPresentacion(Integer.valueOf(bean.getAnioPres())); 
				 id.setMesPresentacion(Integer.valueOf(bean.getMesPres()));
				 id.setEtapa(bean.getEtapa());
				 id.setAnoEjecucionGasto(Integer.valueOf(bean.getAnioEjec())); 
				 id.setMesEjecucionGasto(Integer.valueOf(bean.getMesEjec())); 
				 FiseFormato12BC f = formato12BCDao.getFormatoCabeceraById(id);
				 f.setFechaEnvioDefinitivo(null);
				 f.setUsuarioActualizacion(bean.getUsuario());
				 f.setFechaActualizacion(FechaUtil.obtenerFechaActual());
				 f.setTerminalActualizacion(bean.getTerminal()); 
				 formato12BCDao.updateFormatoCabecera(f);
				 
			}else if(FiseConstants.NOMBRE_FORMATO_12C.equals(bean.getFormato())){
				FiseFormato12CCPK id = new FiseFormato12CCPK();	
				id.setCodEmpresa(bean.getCodEmpresa());
				id.setAnoPresentacion(Long.valueOf(bean.getAnioPres())); 
				id.setMesPresentacion(Long.valueOf(bean.getMesPres()));
				id.setEtapa(bean.getEtapa());	
				FiseFormato12CC f =  formato12CCDao.obtenerFormato12CCByPK(id);
				f.setFechaEnvioDefinitivo(null);
				f.setUsuarioActualizacion(bean.getUsuario());
				f.setFechaActualizacion(FechaUtil.obtenerFechaActual());
				f.setTerminalActualizacion(bean.getTerminal()); 
				formato12CCDao.modificarFormato12CC(f); 
				
			}else if(FiseConstants.NOMBRE_FORMATO_12D.equals(bean.getFormato())){ 
				FiseFormato12DCPK id = new FiseFormato12DCPK();	
				id.setCodEmpresa(bean.getCodEmpresa());
				id.setAnoPresentacion(Long.valueOf(bean.getAnioPres())); 
				id.setMesPresentacion(Long.valueOf(bean.getMesPres()));
				id.setEtapa(bean.getEtapa());	
				FiseFormato12DC f =  formato12DCDao.obtenerFormato12DCByPK(id);
				f.setFechaEnvioDefinitivo(null);
				f.setUsuarioActualizacion(bean.getUsuario());
				f.setFechaActualizacion(FechaUtil.obtenerFechaActual());
				f.setTerminalActualizacion(bean.getTerminal()); 
				formato12DCDao.modificarFormato12DC(f); 				
				
			}else if(FiseConstants.NOMBRE_FORMATO_13A.equals(bean.getFormato())){ 
				FiseFormato13ACPK id = new FiseFormato13ACPK();
				id.setCodEmpresa(bean.getCodEmpresa());
				id.setAnoPresentacion(Long.valueOf(bean.getAnioPres())); 
				id.setMesPresentacion(Long.valueOf(bean.getMesPres()));
				id.setEtapa(bean.getEtapa());				
				FiseFormato13AC f = formato13ACDao.obtenerFormato13ACByPK(id);
				f.setFechaEnvioDefinitivo(null); 
				f.setUsuarioActualizacion(bean.getUsuario());
				f.setFechaActualizacion(FechaUtil.obtenerFechaActual());
				f.setTerminalActualizacion(bean.getTerminal()); 
				formato13ACDao.updatecabecera(f);		
				
			}else if(FiseConstants.NOMBRE_FORMATO_14A.equals(bean.getFormato())){ 
				FiseFormato14ACPK id = new FiseFormato14ACPK();
				id.setCodEmpresa(bean.getCodEmpresa());
				id.setAnoPresentacion(Long.valueOf(bean.getAnioPres())); 
				id.setMesPresentacion(Long.valueOf(bean.getMesPres()));
				id.setAnoInicioVigencia(Long.valueOf(bean.getAnioIniVig())); 
				id.setAnoFinVigencia(Long.valueOf(bean.getAnioFinVig()));  
				id.setEtapa(bean.getEtapa());				
				FiseFormato14AC f = formato14ACDao.obtenerFormato14ACByPK(id);
				f.setFechaEnvioDefinitivo(null); 
				f.setUsuarioActualizacion(bean.getUsuario());
				f.setFechaActualizacion(FechaUtil.obtenerFechaActual());
				f.setTerminalActualizacion(bean.getTerminal()); 
				formato14ACDao.modificarFormato14AC(f); 
				
			}else if(FiseConstants.NOMBRE_FORMATO_14B.equals(bean.getFormato())){  
				FiseFormato14BCPK id = new FiseFormato14BCPK();
				id.setCodEmpresa(bean.getCodEmpresa());
				id.setAnoPresentacion(Long.valueOf(bean.getAnioPres())); 
				id.setMesPresentacion(Long.valueOf(bean.getMesPres()));
				id.setAnoInicioVigencia(Long.valueOf(bean.getAnioIniVig())); 
				id.setAnoFinVigencia(Long.valueOf(bean.getAnioFinVig()));  
				id.setEtapa(bean.getEtapa());				
				FiseFormato14BC f = formato14BCDao.obtenerFormato14BCByPK(id);
				f.setFechaEnvioDefinitivo(null); 
				f.setUsuarioActualizacion(bean.getUsuario());
				f.setFechaActualizacion(FechaUtil.obtenerFechaActual());
				f.setTerminalActualizacion(bean.getTerminal()); 
				formato14BCDao.modificarFormato14BC(f); 
				
			}else if(FiseConstants.NOMBRE_FORMATO_14C.equals(bean.getFormato())){ 
				FiseFormato14CCPK id = new FiseFormato14CCPK();
				id.setCodEmpresa(bean.getCodEmpresa());
				id.setAnoPresentacion(Long.valueOf(bean.getAnioPres())); 
				id.setMesPresentacion(Long.valueOf(bean.getMesPres()));
				id.setAnoInicioVigencia(Long.valueOf(bean.getAnioIniVig())); 
				id.setAnoFinVigencia(Long.valueOf(bean.getAnioFinVig()));  
				id.setEtapa(bean.getEtapa());				
				FiseFormato14CC f = formato14CCDao.obtenerFormato14CC(id);
				f.setFechaEnvioDefinitivo(null); 
				f.setUsuarioActualizacion(bean.getUsuario());
				f.setFechaActualizacion(FechaUtil.obtenerFechaActual());
				f.setTerminalActualizacion(bean.getTerminal()); 
				formato14CCDao.actualizarFiseFormato14CC(f); 
			}		
		} catch (Exception e) {
			logger.info("Error al actualizar formato reenvio:  "+e);
			valor = "0";
		}
		return valor;
		
	}
	
	
	
	@Override
	@Transactional
	public List<NotificacionBean> buscarNotificacion(String codEmpresa,
			String flag,String etapa,Long idGrupoInf,String procesar) throws Exception{
		List<NotificacionBean> lista =null;
		NotificacionBean n =null;
		List<Object[]> lista12A =null;
		List<Object[]> lista12B =null;
		List<Object[]> lista12C =null;
		List<Object[]> lista12D =null;
		List<Object[]> lista13A =null;
		List<Object[]> lista14A =null;
		List<Object[]> lista14B =null;
		List<Object[]> lista14C =null;
		try {		
			/**mensual = formatos 12A,12B,12C,12C
			  bienal = formatos 13A,14A,14B,14C******/			
			if(FiseConstants.MENSUAL.equals(flag)){
				lista = new ArrayList<NotificacionBean>();
				/**FORMATO 12A*/				
				if(FiseConstants.PROCESAR_VALIDACION.equals(procesar)){  
					 lista12A = commonDao.listarObsNotificacionProcesar(codEmpresa, 
								etapa, FiseConstants.NOMBRE_FORMATO_12A,idGrupoInf); 
				 }else{
					 lista12A = commonDao.listarObsNotificacion(codEmpresa, 
								etapa, FiseConstants.NOMBRE_FORMATO_12A,idGrupoInf); 
				 }			 
				 logger.info("Tamanio de la lista lista12A:  "+lista12A.size()); 
				 for(int i = 0; i < lista12A.size(); i++){					
					n = new NotificacionBean();
					String codEmpreCompleta = FormatoUtil.rellenaDerecha(codEmpresa, ' ', 4);
					n.setCodEmpresa(codEmpreCompleta); 					
					n.setAnioPres(String.valueOf((BigDecimal)lista12A.get(i)[1])); 
					n.setMesPres(String.valueOf((BigDecimal)lista12A.get(i)[2]));
					n.setEtapa(etapa);
					n.setFormato(FiseConstants.NOMBRE_FORMATO_12A);				
					n.setAnioEjec(String.valueOf(((BigDecimal)lista12A.get(i)[3] == null) ? "---" :lista12A.get(i)[3]));
					n.setMesEjec(String.valueOf(((BigDecimal)lista12A.get(i)[4] == null) ? "00" :lista12A.get(i)[4]));
					n.setAnioIniVig("---");
					n.setAnioFinVig("---");
					lista.add(n);
				 }				
			    /**FORMATO 12B*/				
				 if(FiseConstants.PROCESAR_VALIDACION.equals(procesar)){  
					 lista12B = commonDao.listarObsNotificacionProcesar(codEmpresa, 
							 etapa, FiseConstants.NOMBRE_FORMATO_12B,idGrupoInf); 
				 }else{
					 lista12B = commonDao.listarObsNotificacion(codEmpresa, 
							 etapa, FiseConstants.NOMBRE_FORMATO_12B,idGrupoInf); 
				 }					
				 logger.info("Tamanio de la lista lista12B :  "+lista12B.size()); 
				 for(int i = 0; i < lista12B.size(); i++){					
					n = new NotificacionBean();
					String codEmpreCompleta = FormatoUtil.rellenaDerecha(codEmpresa, ' ', 4);
					n.setCodEmpresa(codEmpreCompleta); 
					n.setAnioPres(String.valueOf((BigDecimal)lista12B.get(i)[1])); 
					n.setMesPres(String.valueOf((BigDecimal)lista12B.get(i)[2]));
					n.setEtapa(etapa);
					n.setFormato(FiseConstants.NOMBRE_FORMATO_12B);				
					n.setAnioEjec(String.valueOf(((BigDecimal)lista12B.get(i)[3] == null) ? "---" :lista12B.get(i)[3]));
					n.setMesEjec(String.valueOf(((BigDecimal)lista12B.get(i)[4] == null) ? "00" :lista12B.get(i)[4]));
					n.setAnioIniVig("---");
					n.setAnioFinVig("---");
					lista.add(n);
				 }		
			    /**FORMATO 12C*/				
				 if(FiseConstants.PROCESAR_VALIDACION.equals(procesar)){  
					 lista12C = commonDao.listarObsNotificacionProcesar(codEmpresa, 
							 etapa, FiseConstants.NOMBRE_FORMATO_12C,idGrupoInf); 
				 }else{
					 lista12C = commonDao.listarObsNotificacion(codEmpresa, 
							 etapa, FiseConstants.NOMBRE_FORMATO_12C,idGrupoInf); 
				 }	
				 logger.info("Tamanio de la lista lista12C:  "+lista12C.size()); 
				 for(int i = 0; i < lista12C.size(); i++){					
					n = new NotificacionBean();
					String codEmpreCompleta = FormatoUtil.rellenaDerecha(codEmpresa, ' ', 4);
					n.setCodEmpresa(codEmpreCompleta); 
					n.setAnioPres(String.valueOf((BigDecimal)lista12C.get(i)[1])); 
					n.setMesPres(String.valueOf((BigDecimal)lista12C.get(i)[2]));
					n.setEtapa(etapa);
					n.setFormato(FiseConstants.NOMBRE_FORMATO_12C);	
					n.setAnioEjec("---");
					n.setMesEjec("00");
					//n.setAnioEjec(String.valueOf(((BigDecimal)lista12C.get(i)[3] == null) ? "---" :lista12C.get(i)[3]));
					//n.setMesEjec(String.valueOf(((BigDecimal)lista12C.get(i)[4] == null) ? "00" :lista12C.get(i)[4]));
					n.setAnioIniVig("---");
					n.setAnioFinVig("---");
					lista.add(n);
				 }		
				
			   /**FORMATO 12D*/				
				 if(FiseConstants.PROCESAR_VALIDACION.equals(procesar)){  
					 lista12D = commonDao.listarObsNotificacionProcesar(codEmpresa, 
							 etapa, FiseConstants.NOMBRE_FORMATO_12D,idGrupoInf); 
				 }else{
					 lista12D = commonDao.listarObsNotificacion(codEmpresa, 
							 etapa, FiseConstants.NOMBRE_FORMATO_12D,idGrupoInf); 
				 }	
				 logger.info("Tamanio de la lista lista12D :  "+lista12D.size()); 
				 for(int i = 0; i < lista12D.size(); i++){					
					n = new NotificacionBean();
					String codEmpreCompleta = FormatoUtil.rellenaDerecha(codEmpresa, ' ', 4);
					n.setCodEmpresa(codEmpreCompleta); 
					n.setAnioPres(String.valueOf((BigDecimal)lista12D.get(i)[1])); 
					n.setMesPres(String.valueOf((BigDecimal)lista12D.get(i)[2]));
					n.setEtapa(etapa);
					n.setFormato(FiseConstants.NOMBRE_FORMATO_12D);	
					n.setAnioEjec("---");
					n.setMesEjec("00");
					//n.setAnioEjec(String.valueOf(((BigDecimal)lista12D.get(i)[3] == null) ? "---" :lista12D.get(i)[3]));
					//n.setMesEjec(String.valueOf(((BigDecimal)lista12D.get(i)[4] == null) ? "00" :lista12D.get(i)[4]));
					n.setAnioIniVig("---");
					n.setAnioFinVig("---");
					lista.add(n);
				 }		
			}else if(FiseConstants.BIENAL.equals(flag)){
				lista = new ArrayList<NotificacionBean>();
				/**FORMATO 13A*/			  
			   if(FiseConstants.PROCESAR_VALIDACION.equals(procesar)){  
				   lista13A = commonDao.listarObsNotificacionProcesar(codEmpresa, 
						   etapa, FiseConstants.NOMBRE_FORMATO_13A,idGrupoInf); 
			   }else{
				   lista13A = commonDao.listarObsNotificacion(codEmpresa, 
						   etapa, FiseConstants.NOMBRE_FORMATO_13A,idGrupoInf); 
			   }
			   logger.info("Tamanio de la lista lista13A:  "+lista13A.size()); 
			   for(int i = 0; i < lista13A.size(); i++){				
					n = new NotificacionBean();
					String codEmpreCompleta = FormatoUtil.rellenaDerecha(codEmpresa, ' ', 4);
					n.setCodEmpresa(codEmpreCompleta); 
					n.setAnioPres(String.valueOf((BigDecimal)lista13A.get(i)[1])); 
					n.setMesPres(String.valueOf((BigDecimal)lista13A.get(i)[2]));
					n.setEtapa(etapa);
					n.setFormato(FiseConstants.NOMBRE_FORMATO_13A); 
					n.setAnioEjec("---");
					n.setMesEjec("00");
					/*n.setAnioIniVig("---");
					n.setAnioFinVig("---");*/
					//add
					n.setAnioIniVig(String.valueOf(((BigDecimal)lista13A.get(i)[3] == null) ? "---" :lista13A.get(i)[3]));
					n.setAnioFinVig(String.valueOf(((BigDecimal)lista13A.get(i)[4] == null) ? "---" :lista13A.get(i)[4]));	
					//
					lista.add(n);
				}				
				/**FORMATO 14A*/			   
			   if(FiseConstants.PROCESAR_VALIDACION.equals(procesar)){  
				   lista14A = commonDao.listarObsNotificacionProcesar(codEmpresa, 
						   etapa, FiseConstants.NOMBRE_FORMATO_14A,idGrupoInf); 
			   }else{
				   lista14A = commonDao.listarObsNotificacion(codEmpresa, 
						   etapa, FiseConstants.NOMBRE_FORMATO_14A,idGrupoInf); 
			   }
			   logger.info("Tamanio de la lista lista14A:  "+lista14A.size()); 
			   for(int i = 0; i < lista14A.size(); i++){
					n = new NotificacionBean();
					String codEmpreCompleta = FormatoUtil.rellenaDerecha(codEmpresa, ' ', 4);
					n.setCodEmpresa(codEmpreCompleta); 
					n.setAnioPres(String.valueOf((BigDecimal)lista14A.get(i)[1])); 
					n.setMesPres(String.valueOf((BigDecimal)lista14A.get(i)[2]));
					n.setEtapa(etapa);
					n.setFormato(FiseConstants.NOMBRE_FORMATO_14A); 	
					n.setAnioEjec("---");
					n.setMesEjec("00");
					n.setAnioIniVig(String.valueOf(((BigDecimal)lista14A.get(i)[3] == null) ? "---" :lista14A.get(i)[3]));
					n.setAnioFinVig(String.valueOf(((BigDecimal)lista14A.get(i)[4] == null) ? "---" :lista14A.get(i)[4]));					
					lista.add(n);
				}
			   /**FORMATO 14B*/		
			   if(FiseConstants.PROCESAR_VALIDACION.equals(procesar)){  
				   lista14B = commonDao.listarObsNotificacionProcesar(codEmpresa, 
						   etapa, FiseConstants.NOMBRE_FORMATO_14B,idGrupoInf); 
			   }else{
				   lista14B = commonDao.listarObsNotificacion(codEmpresa, 
						   etapa, FiseConstants.NOMBRE_FORMATO_14B,idGrupoInf); 
			   }
			   logger.info("Tamanio de la lista lista14B:  "+lista14B.size()); 
			   for(int i = 0; i < lista14B.size(); i++){
					n = new NotificacionBean();
					String codEmpreCompleta = FormatoUtil.rellenaDerecha(codEmpresa, ' ', 4);
					n.setCodEmpresa(codEmpreCompleta); 
					n.setAnioPres(String.valueOf((BigDecimal)lista14B.get(i)[1])); 
					n.setMesPres(String.valueOf((BigDecimal)lista14B.get(i)[2]));
					n.setEtapa(etapa);
					n.setFormato(FiseConstants.NOMBRE_FORMATO_14B); 	
					n.setAnioEjec("---");
					n.setMesEjec("00");
					n.setAnioIniVig(String.valueOf(((BigDecimal)lista14B.get(i)[3] == null) ? "---" :lista14B.get(i)[3]));
					n.setAnioFinVig(String.valueOf(((BigDecimal)lista14B.get(i)[4] == null) ? "---" :lista14B.get(i)[4]));									
					lista.add(n);
				}
				/**FORMATO 14C*/			 
			   if(FiseConstants.PROCESAR_VALIDACION.equals(procesar)){  
				   lista14C = commonDao.listarObsNotificacionProcesar(codEmpresa, 
						   etapa, FiseConstants.NOMBRE_FORMATO_14C,idGrupoInf); 
			   }else{
				   lista14C = commonDao.listarObsNotificacion(codEmpresa, 
						   etapa, FiseConstants.NOMBRE_FORMATO_14C,idGrupoInf); 
			   }
			   logger.info("Tamanio de la lista lista14C:  "+lista14C.size());
			   for(int i = 0; i < lista14C.size(); i++){
					n = new NotificacionBean();
					String codEmpreCompleta = FormatoUtil.rellenaDerecha(codEmpresa, ' ', 4);
					n.setCodEmpresa(codEmpreCompleta); 
					n.setAnioPres(String.valueOf((BigDecimal)lista14C.get(i)[1])); 
					n.setMesPres(String.valueOf((BigDecimal)lista14C.get(i)[2]));
					n.setEtapa(etapa);
					n.setFormato(FiseConstants.NOMBRE_FORMATO_14C); 
					n.setAnioEjec("---");
					n.setMesEjec("00");
					n.setAnioIniVig(String.valueOf(((BigDecimal)lista14C.get(i)[3] == null) ? "---" :lista14C.get(i)[3]));
					n.setAnioFinVig(String.valueOf(((BigDecimal)lista14C.get(i)[4] == null) ? "---" :lista14C.get(i)[4]));					
					lista.add(n);
				}		
			}else{
				lista = new ArrayList<NotificacionBean>();	
			}
		} catch (Exception e) {
			e.printStackTrace();
			logger.info("Ocurrio un error al listar notificacion:  "+e); 
		}finally{
			if(n!=null){
				n=null;
			}
			if(lista12A!=null){
				lista12A=null;
			}
			if(lista12B!=null){
				lista12B=null;
			}
			if(lista12C!=null){
				lista12C=null;
			}
			if(lista12D!=null){
				lista12D=null;
			}
			if(lista13A!=null){
				lista13A=null;
			}
			if(lista14A!=null){
				lista14A=null;
			}
			if(lista14B!=null){
				lista14B=null;
			}
			if(lista14C!=null){
				lista14C=null;
			}
		}
	  return lista;
	}
	
	
	@Override
	@Transactional
	public String notificarValidacionMensual(String codEmpresa, String etapa, 
			long idGrupoInf, String periodicidad, String user,String terminal) throws Exception{
		return commonDao.notificarValidacionMensual(codEmpresa, etapa, idGrupoInf, periodicidad, user, terminal);
	}
	
	
	
	@Override
	@Transactional
	public List<EnvioDefinitivoBean> buscarEnvioDefinitivo(String codEmpresa,
			String flag,String etapa,Long idGrupoInf) throws Exception{
		List<EnvioDefinitivoBean> lista =null;
		EnvioDefinitivoBean e =null;
		List<Object[]> lista12A =null;
		List<Object[]> lista12B =null;
		List<Object[]> lista12C =null;
		List<Object[]> lista12D =null;
		List<Object[]> lista13A =null;
		List<Object[]> lista14A =null;
		List<Object[]> lista14B =null;
		List<Object[]> lista14C =null;
		try {		
			/**mensual = formatos 12A,12B,12C,12C
			  bienal = formatos 13A,14A,14B,14C******/			
			if(FiseConstants.MENSUAL.equals(flag)){
				lista = new ArrayList<EnvioDefinitivoBean>();
				/**FORMATO 12A*/						
				lista12A = commonDao.listarEnvioDefinitivo(codEmpresa, 
						etapa, FiseConstants.NOMBRE_FORMATO_12A,idGrupoInf); 
				
				 logger.info("Tamanio de la lista lista12A:  "+lista12A.size()); 
				 for(int i = 0; i < lista12A.size(); i++){					
					e = new EnvioDefinitivoBean();
					String codEmpreCompleta = FormatoUtil.rellenaDerecha(codEmpresa, ' ', 4);
					e.setCodEmpresa(codEmpreCompleta); 
					e.setAnioPres(String.valueOf((BigDecimal)lista12A.get(i)[1])); 
					e.setMesPres(String.valueOf((BigDecimal)lista12A.get(i)[2]));
					e.setEtapa(etapa);
					e.setFormato(FiseConstants.NOMBRE_FORMATO_12A);				
					e.setAnioEjec(String.valueOf(((BigDecimal)lista12A.get(i)[3] == null) ? "---" :lista12A.get(i)[3]));
					e.setMesEjec(String.valueOf(((BigDecimal)lista12A.get(i)[4] == null) ? "00" :lista12A.get(i)[4]));
					e.setAnioIniVig("---");
					e.setAnioFinVig("---");
					e.setEstado((String)lista12A.get(i)[6] == null ? "---" :lista12A.get(i)[6].toString());
					lista.add(e);
				 }				
			    /**FORMATO 12B*/								
				 lista12B = commonDao.listarEnvioDefinitivo(codEmpresa, 
						 etapa, FiseConstants.NOMBRE_FORMATO_12B,idGrupoInf); 
				 
				 logger.info("Tamanio de la lista lista12B :  "+lista12B.size()); 
				 for(int i = 0; i < lista12B.size(); i++){					
					 e = new EnvioDefinitivoBean();
					 String codEmpreCompleta = FormatoUtil.rellenaDerecha(codEmpresa, ' ', 4);
					 e.setCodEmpresa(codEmpreCompleta);
					 e.setAnioPres(String.valueOf((BigDecimal)lista12B.get(i)[1])); 
					 e.setMesPres(String.valueOf((BigDecimal)lista12B.get(i)[2]));
					 e.setEtapa(etapa);
					 e.setFormato(FiseConstants.NOMBRE_FORMATO_12B);				
					 e.setAnioEjec(String.valueOf(((BigDecimal)lista12B.get(i)[3] == null) ? "---" :lista12B.get(i)[3]));
					 e.setMesEjec(String.valueOf(((BigDecimal)lista12B.get(i)[4] == null) ? "00" :lista12B.get(i)[4]));
					 e.setAnioIniVig("---");
					 e.setAnioFinVig("---");
					 e.setEstado((String)lista12B.get(i)[6] == null ? "---" :lista12B.get(i)[6].toString());
					 lista.add(e);
				 }	
			    /**FORMATO 12C*/						
				 lista12C = commonDao.listarEnvioDefinitivo(codEmpresa, 
						 etapa, FiseConstants.NOMBRE_FORMATO_12C,idGrupoInf); 
				 
				 logger.info("Tamanio de la lista lista12C:  "+lista12C.size()); 
				 for(int i = 0; i < lista12C.size(); i++){					
					e = new EnvioDefinitivoBean();
					String codEmpreCompleta = FormatoUtil.rellenaDerecha(codEmpresa, ' ', 4);
					e.setCodEmpresa(codEmpreCompleta);
					e.setAnioPres(String.valueOf((BigDecimal)lista12C.get(i)[1])); 
					e.setMesPres(String.valueOf((BigDecimal)lista12C.get(i)[2]));
					e.setEtapa(etapa);
					e.setFormato(FiseConstants.NOMBRE_FORMATO_12C);	
					e.setAnioEjec("---");
					e.setMesEjec("00");
					//e.setAnioEjec(String.valueOf(((BigDecimal)lista12C.get(i)[3] == null) ? "---" :lista12C.get(i)[3]));
					//e.setMesEjec(String.valueOf(((BigDecimal)lista12C.get(i)[4] == null) ? "00" :lista12C.get(i)[4]));
					e.setAnioIniVig("---");
					e.setAnioFinVig("---");
					e.setEstado((String)lista12C.get(i)[4] == null ? "---" :lista12C.get(i)[4].toString());
					lista.add(e);
				 }		
				
			   /**FORMATO 12D*/					 
				 lista12D = commonDao.listarEnvioDefinitivo(codEmpresa, 
						 etapa, FiseConstants.NOMBRE_FORMATO_12D,idGrupoInf);  
				
				 logger.info("Tamanio de la lista lista12D :  "+lista12D.size()); 
				 for(int i = 0; i < lista12D.size(); i++){					
					e = new EnvioDefinitivoBean();
					String codEmpreCompleta = FormatoUtil.rellenaDerecha(codEmpresa, ' ', 4);
					e.setCodEmpresa(codEmpreCompleta); 
					e.setAnioPres(String.valueOf((BigDecimal)lista12D.get(i)[1])); 
					e.setMesPres(String.valueOf((BigDecimal)lista12D.get(i)[2]));
					e.setEtapa(etapa);
					e.setFormato(FiseConstants.NOMBRE_FORMATO_12D);	
					e.setAnioEjec("---");
					e.setMesEjec("00");
					//n.setAnioEjec(String.valueOf(((BigDecimal)lista12D.get(i)[3] == null) ? "---" :lista12D.get(i)[3]));
					//n.setMesEjec(String.valueOf(((BigDecimal)lista12D.get(i)[4] == null) ? "00" :lista12D.get(i)[4]));
					e.setAnioIniVig("---");
					e.setAnioFinVig("---");
					e.setEstado((String)lista12D.get(i)[4] == null ? "---" :lista12D.get(i)[4].toString());
					lista.add(e);
				 }
			}else if(FiseConstants.BIENAL.equals(flag)){
				lista = new ArrayList<EnvioDefinitivoBean>();
				/**FORMATO 13A*/			   
				lista13A = commonDao.listarEnvioDefinitivo(codEmpresa, 
						etapa, FiseConstants.NOMBRE_FORMATO_13A,idGrupoInf); 
			   
			   logger.info("Tamanio de la lista lista13A:  "+lista13A.size()); 
			   for(int i = 0; i < lista13A.size(); i++){				
					e = new EnvioDefinitivoBean();
					String codEmpreCompleta = FormatoUtil.rellenaDerecha(codEmpresa, ' ', 4);
					e.setCodEmpresa(codEmpreCompleta);
					e.setAnioPres(String.valueOf((BigDecimal)lista13A.get(i)[1])); 
					e.setMesPres(String.valueOf((BigDecimal)lista13A.get(i)[2]));
					e.setEtapa(etapa);
					e.setFormato(FiseConstants.NOMBRE_FORMATO_13A); 
					e.setAnioEjec("---");
					e.setMesEjec("00");
					e.setAnioIniVig("---");
					e.setAnioFinVig("---");
					e.setEstado((String)lista13A.get(i)[4] == null ? "---" :lista13A.get(i)[4].toString());
					lista.add(e);
				}				
				/**FORMATO 14A*/				  
			   lista14A = commonDao.listarEnvioDefinitivo(codEmpresa, 
					   etapa, FiseConstants.NOMBRE_FORMATO_14A,idGrupoInf); 
			   
			   logger.info("Tamanio de la lista lista14A:  "+lista14A.size()); 
			   for(int i = 0; i < lista14A.size(); i++){
					e = new EnvioDefinitivoBean();
					String codEmpreCompleta = FormatoUtil.rellenaDerecha(codEmpresa, ' ', 4);
					e.setCodEmpresa(codEmpreCompleta);
					e.setAnioPres(String.valueOf((BigDecimal)lista14A.get(i)[1])); 
					e.setMesPres(String.valueOf((BigDecimal)lista14A.get(i)[2]));
					e.setEtapa(etapa);
					e.setFormato(FiseConstants.NOMBRE_FORMATO_14A); 	
					e.setAnioEjec("---");
					e.setMesEjec("00");
					e.setAnioIniVig(String.valueOf(((BigDecimal)lista14A.get(i)[3] == null) ? "---" :lista14A.get(i)[3]));
					e.setAnioFinVig(String.valueOf(((BigDecimal)lista14A.get(i)[4] == null) ? "---" :lista14A.get(i)[4]));
					e.setEstado((String)lista14A.get(i)[6] == null ? "---" :lista14A.get(i)[6].toString());
					lista.add(e);
				}
			   /**FORMATO 14B*/					  
			   lista14B = commonDao.listarEnvioDefinitivo(codEmpresa, 
					   etapa, FiseConstants.NOMBRE_FORMATO_14B,idGrupoInf); 
			   
			   logger.info("Tamanio de la lista lista14B:  "+lista14B.size()); 
			   for(int i = 0; i < lista14B.size(); i++){
					e = new EnvioDefinitivoBean();
					String codEmpreCompleta = FormatoUtil.rellenaDerecha(codEmpresa, ' ', 4);
					e.setCodEmpresa(codEmpreCompleta);
					e.setAnioPres(String.valueOf((BigDecimal)lista14B.get(i)[1])); 
					e.setMesPres(String.valueOf((BigDecimal)lista14B.get(i)[2]));
					e.setEtapa(etapa);
					e.setFormato(FiseConstants.NOMBRE_FORMATO_14B); 	
					e.setAnioEjec("---");
					e.setMesEjec("00");
					e.setAnioIniVig(String.valueOf(((BigDecimal)lista14B.get(i)[3] == null) ? "---" :lista14B.get(i)[3]));
					e.setAnioFinVig(String.valueOf(((BigDecimal)lista14B.get(i)[4] == null) ? "---" :lista14B.get(i)[4]));
					e.setEstado((String)lista14B.get(i)[6] == null ? "---" :lista14B.get(i)[6].toString());
					lista.add(e);
				}
				/**FORMATO 14C*/			  
			   lista14C = commonDao.listarEnvioDefinitivo(codEmpresa, 
					   etapa, FiseConstants.NOMBRE_FORMATO_14C,idGrupoInf); 
			   
			   logger.info("Tamanio de la lista lista14C:  "+lista14C.size());
			   for(int i = 0; i < lista14C.size(); i++){
					e = new EnvioDefinitivoBean();
					String codEmpreCompleta = FormatoUtil.rellenaDerecha(codEmpresa, ' ', 4);
					e.setCodEmpresa(codEmpreCompleta);
					e.setAnioPres(String.valueOf((BigDecimal)lista14C.get(i)[1])); 
					e.setMesPres(String.valueOf((BigDecimal)lista14C.get(i)[2]));
					e.setEtapa(etapa);
					e.setFormato(FiseConstants.NOMBRE_FORMATO_14C); 
					e.setAnioEjec("---");
					e.setMesEjec("00");
					e.setAnioIniVig(String.valueOf(((BigDecimal)lista14C.get(i)[3] == null) ? "---" :lista14C.get(i)[3]));
					e.setAnioFinVig(String.valueOf(((BigDecimal)lista14C.get(i)[4] == null) ? "---" :lista14C.get(i)[4]));
					e.setEstado((String)lista14C.get(i)[6] == null ? "---" :lista14C.get(i)[6].toString());
					lista.add(e);
				}		
			}else{
				lista = new ArrayList<EnvioDefinitivoBean>();	
			}
		} catch (Exception ex) {
			ex.printStackTrace();
			logger.info("Ocurrio un error al listar notificacion:  "+ex); 
		}finally{
			if(e!=null){
				e=null;
			}
			if(lista12A!=null){
				lista12A=null;
			}
			if(lista12B!=null){
				lista12B=null;
			}
			if(lista12C!=null){
				lista12C=null;
			}
			if(lista12D!=null){
				lista12D=null;
			}
			if(lista13A!=null){
				lista13A=null;
			}
			if(lista14A!=null){
				lista14A=null;
			}
			if(lista14B!=null){
				lista14B=null;
			}
			if(lista14C!=null){
				lista14C=null;
			}
		}
	  return lista;
	}
	
	@SuppressWarnings("unchecked")	
	@Override
	@Transactional(propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
	public boolean actualizarFechaEnvioGeneral(Map<String, Object> params) throws Exception{
		boolean valor = false;
		try {
			List<EnvioDefinitivoBean> lista =(List<EnvioDefinitivoBean>)params.get("listaEnvio");
			String f12A = (String)params.get("f12A");
			String f12B = (String)params.get("f12B");
			String f12C = (String)params.get("f12C");
			String f12D = (String)params.get("f12D");
			String f13A = (String)params.get("f13A");
			String f14A = (String)params.get("f14A");
			String f14B = (String)params.get("f14B");
			String f14C = (String)params.get("f14C");	
			String usuario = (String)params.get("usuario");
			String terminal = (String)params.get("terminal");
			
			String codEmpresa = (String)params.get("codEmpresa");	
			String etapa = (String)params.get("etapa");
			String periocidad = (String)params.get("periocidad");
			String idGrupoInf = (String)params.get("idGrupo");
			
			boolean valorFormato = false;
			for(EnvioDefinitivoBean envio :lista){
				if(FiseConstants.NOMBRE_FORMATO_12A.equals(envio.getFormato())&&"1".equals(f12A)){  
  					FiseFormato12ACPK pk = new FiseFormato12ACPK();
  	  				pk.setCodEmpresa(envio.getCodEmpresa());
  	  				pk.setAnoPresentacion(new Long(envio.getAnioPres()));
  	  		        pk.setMesPresentacion(new Long(envio.getMesPres()));
  	  		        pk.setAnoEjecucionGasto(new Long(envio.getAnioEjec()));
  	  		        pk.setMesEjecucionGasto(new Long(envio.getMesEjec()));
  	  		        pk.setEtapa(envio.getEtapa());  	  			        
  	  		        FiseFormato12AC formato12A = formato12ACDao.obtenerFormato12ACByPK(pk);
  	  		        formato12A.setFechaEnvioDefinitivo(FechaUtil.obtenerFechaActual());
  	  		        formato12A.setUsuarioActualizacion(usuario);
  	  	            formato12A.setTerminalActualizacion(terminal);
  	                formato12A.setFechaActualizacion(FechaUtil.obtenerFechaActual());
  	  		        formato12ACDao.modificarFormato12AC(formato12A); 	
  	  		        valorFormato = true;
  				}else if(FiseConstants.NOMBRE_FORMATO_12B.equals(envio.getFormato())&&"1".equals(f12B)){ 
  					FiseFormato12BCPK pk = new FiseFormato12BCPK();
  					pk.setCodEmpresa(envio.getCodEmpresa());
  	  				pk.setAnoPresentacion(new Integer(envio.getAnioPres()));
  	  		        pk.setMesPresentacion(new Integer(envio.getMesPres()));
  	  		        pk.setAnoEjecucionGasto(new Integer(envio.getAnioEjec()));
  	  		        pk.setMesEjecucionGasto(new Integer(envio.getMesEjec()));
  	  		        pk.setEtapa(envio.getEtapa()); 
  					FiseFormato12BC formato12B = formato12BCDao.getFormatoCabeceraById(pk);
  					formato12B.setFechaEnvioDefinitivo(FechaUtil.obtenerFechaActual());
  					formato12B.setUsuarioActualizacion(usuario);
  					formato12B.setTerminalActualizacion(terminal);
  					formato12B.setFechaActualizacion(FechaUtil.obtenerFechaActual());
  					formato12BCDao.updateFormatoCabecera(formato12B);
  					valorFormato = true;
  				}else if(FiseConstants.NOMBRE_FORMATO_12C.equals(envio.getFormato())&&"1".equals(f12C)){ 
  					FiseFormato12CCPK pk = new FiseFormato12CCPK();
  					pk.setCodEmpresa(envio.getCodEmpresa());
  					pk.setAnoPresentacion(new Long(envio.getAnioPres()));
  	  		        pk.setMesPresentacion(new Long(envio.getMesPres()));
  	  		        pk.setEtapa(envio.getEtapa());
  	  		        FiseFormato12CC formato12C = formato12CCDao.obtenerFormato12CCByPK(pk);
  	  		        formato12C.setFechaEnvioDefinitivo(FechaUtil.obtenerFechaActual());
  	  		        formato12C.setUsuarioActualizacion(usuario);
  	  	            formato12C.setTerminalActualizacion(terminal);
  	                formato12C.setFechaActualizacion(FechaUtil.obtenerFechaActual());
  	                formato12CCDao.modificarFormato12CC(formato12C); 
  	                valorFormato = true;
  				}else if(FiseConstants.NOMBRE_FORMATO_12D.equals(envio.getFormato())&&"1".equals(f12D)){ 
  					FiseFormato12DCPK pk = new FiseFormato12DCPK();
  					pk.setCodEmpresa(envio.getCodEmpresa());
  					pk.setAnoPresentacion(new Long(envio.getAnioPres()));
  	  		        pk.setMesPresentacion(new Long(envio.getMesPres()));
  	  		        pk.setEtapa(envio.getEtapa());
  	  		        FiseFormato12DC formato12D= formato12DCDao.obtenerFormato12DCByPK(pk);
  	  		        formato12D.setFechaEnvioDefinitivo(FechaUtil.obtenerFechaActual());
  	  		        formato12D.setUsuarioActualizacion(usuario);
  	  		        formato12D.setTerminalActualizacion(terminal);
  	  	            formato12D.setFechaActualizacion(FechaUtil.obtenerFechaActual());
  	  	            formato12DCDao.modificarFormato12DC(formato12D);
  	  	            valorFormato = true;
  				}else if(FiseConstants.NOMBRE_FORMATO_13A.equals(envio.getFormato())&&"1".equals(f13A)){ 
  					FiseFormato13ACPK pk = new FiseFormato13ACPK();
  					pk.setCodEmpresa(envio.getCodEmpresa());
  	  				pk.setAnoPresentacion(new Long(envio.getAnioPres()));
  	  		        pk.setMesPresentacion(new Long(envio.getMesPres()));
  	  		        pk.setEtapa(envio.getEtapa());  	 
  	  		        FiseFormato13AC formato13A = formato13ACDao.obtenerFormato13ACByPK(pk);	
  	  		        formato13A.setFechaEnvioDefinitivo(FechaUtil.obtenerFechaActual());
  	  		        formato13A.setUsuarioActualizacion(usuario);
  	  	     	    formato13A.setTerminalActualizacion(terminal);
  	          	    formato13A.setFechaActualizacion(FechaUtil.obtenerFechaActual());
  	  		        formato13ACDao.updatecabecera(formato13A);
  	  		        valorFormato = true;
  				}else if(FiseConstants.NOMBRE_FORMATO_14A.equals(envio.getFormato())&&"1".equals(f14A)){ 
  					FiseFormato14ACPK pk = new FiseFormato14ACPK();
  					pk.setCodEmpresa(envio.getCodEmpresa());
  	  				pk.setAnoPresentacion(new Long(envio.getAnioPres()));
  	  		        pk.setMesPresentacion(new Long(envio.getMesPres()));
  			        pk.setAnoInicioVigencia(new Long(envio.getAnioIniVig()));
  			        pk.setAnoFinVigencia(new Long(envio.getAnioFinVig()));
  			        pk.setEtapa(envio.getEtapa());  				        
  			        FiseFormato14AC formato14A = formato14ACDao.obtenerFormato14ACByPK(pk);	
  			        formato14A.setFechaEnvioDefinitivo(FechaUtil.obtenerFechaActual());
  			        formato14A.setUsuarioActualizacion(usuario);
  			        formato14A.setTerminalActualizacion(terminal);
  			        formato14A.setFechaActualizacion(FechaUtil.obtenerFechaActual());
  			        formato14ACDao.modificarFormato14AC(formato14A); 
  			        valorFormato = true;
  				}else if(FiseConstants.NOMBRE_FORMATO_14B.equals(envio.getFormato())&&"1".equals(f14B)){ 
  					FiseFormato14BCPK pk = new FiseFormato14BCPK();
  					pk.setCodEmpresa(envio.getCodEmpresa());
  	  				pk.setAnoPresentacion(new Long(envio.getAnioPres()));
  	  		        pk.setMesPresentacion(new Long(envio.getMesPres()));
  			        pk.setAnoInicioVigencia(new Long(envio.getAnioIniVig()));
  			        pk.setAnoFinVigencia(new Long(envio.getAnioFinVig()));
  			        pk.setEtapa(envio.getEtapa());  						        
  			        FiseFormato14BC formato14B = formato14BCDao.obtenerFormato14BCByPK(pk);	
  			        formato14B.setFechaEnvioDefinitivo(FechaUtil.obtenerFechaActual());
			        formato14B.setUsuarioActualizacion(usuario);
			        formato14B.setTerminalActualizacion(terminal);
			        formato14B.setFechaActualizacion(FechaUtil.obtenerFechaActual());
  			        formato14BCDao.modificarFormato14BC(formato14B); 
  			        valorFormato = true;
  				}else if(FiseConstants.NOMBRE_FORMATO_14C.equals(envio.getFormato())&&"1".equals(f14C)){ 
  					FiseFormato14CCPK pk = new FiseFormato14CCPK();  					
  					pk.setCodEmpresa(envio.getCodEmpresa());
  	  				pk.setAnoPresentacion(new Long(envio.getAnioPres()));
  	  		        pk.setMesPresentacion(new Long(envio.getMesPres()));
  			        pk.setAnoInicioVigencia(new Long(envio.getAnioIniVig()));
  			        pk.setAnoFinVigencia(new Long(envio.getAnioFinVig()));
  			        pk.setEtapa(envio.getEtapa()); 
  					FiseFormato14CC formato14C = formato14CCDao.obtenerFormato14CC(pk);
  					formato14C.setFechaEnvioDefinitivo(FechaUtil.obtenerFechaActual());
			        formato14C.setUsuarioActualizacion(usuario);
			        formato14C.setTerminalActualizacion(terminal);
			        formato14C.setFechaActualizacion(FechaUtil.obtenerFechaActual());
			        formato14CCDao.actualizarFiseFormato14CC(formato14C);
			        valorFormato = true;
  				}		
			}
			/******************Insertamos en la tabla control de envio general********************/
			if(valorFormato){
				FiseControlEnvioPorGrupoPK id = new FiseControlEnvioPorGrupoPK();
				id.setCodEmpresa(codEmpresa);
				id.setEtapa(etapa);
				id.setIdGrupoInformacion(new Long(idGrupoInf));
				id.setPeriodicidad(periocidad); 				
				FiseControlEnvioPorGrupo fiseControl = fiseControlEnvioDao.obtenerFiseControlEnvioByPK(id);
				FiseGrupoInformacion fiseGrupoInf = fiseGrupoInformacionDao.obtenerFiseGrupoInformacionByPK(new Long(idGrupoInf));
				logger.info("objeto control envio:  "+fiseControl); 
				if(fiseControl ==null){					
					FiseControlEnvioPorGrupo grupo = new FiseControlEnvioPorGrupo();
					grupo.setId(id);
					grupo.setFechaCreacion(FechaUtil.obtenerFechaActual());
					grupo.setFechaEnvioDefinitivo(FechaUtil.obtenerFechaActual());
					grupo.setFiseGrupoInformacion(fiseGrupoInf);			
					grupo.setUsuarioCreacion(usuario);
					grupo.setTerminalCreacion(terminal);
					fiseControlEnvioDao.insertarFiseControlEnvio(grupo); 
					valor = true;	
				}else{
					fiseControl.setFechaCreacion(FechaUtil.obtenerFechaActual());
					fiseControl.setFechaEnvioDefinitivo(FechaUtil.obtenerFechaActual());
					fiseControl.setFiseGrupoInformacion(fiseGrupoInf);			
					fiseControl.setUsuarioCreacion(usuario);
					fiseControl.setTerminalCreacion(terminal);
					fiseControlEnvioDao.actualizarFiseControlEnvio(fiseControl); 
					valor = true;		
				}
			}			
		} catch (Exception e) {
			valor = false;
			e.printStackTrace();
			logger.error("Error al actualizar fecha de envio general:  "+e);
		}
		return valor;	
	}
	
	@Override
	@Transactional
	public FiseControlEnvioPorGrupo obtenerFiseControlEnvioByPK(String codEmpresa,String etapa,
			Long idGrupoInf,String periocidad) throws Exception{
		FiseControlEnvioPorGrupoPK id = new FiseControlEnvioPorGrupoPK();
		id.setCodEmpresa(codEmpresa);
		id.setEtapa(etapa);
		id.setIdGrupoInformacion(idGrupoInf);
		id.setPeriodicidad(periocidad); 
		return fiseControlEnvioDao.obtenerFiseControlEnvioByPK(id);
	}
	
	
	
}
