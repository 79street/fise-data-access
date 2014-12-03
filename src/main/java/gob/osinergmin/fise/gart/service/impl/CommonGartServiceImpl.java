package gob.osinergmin.fise.gart.service.impl;

import gob.osinergmin.fise.bean.AutorizarReenvioBean;
import gob.osinergmin.fise.bean.CorreoBean;
import gob.osinergmin.fise.bean.Formato12A12BGeneric;
import gob.osinergmin.fise.bean.Formato12C12D13Generic;
import gob.osinergmin.fise.bean.Formato14Generic;
import gob.osinergmin.fise.bean.NotificacionBean;
import gob.osinergmin.fise.constant.FiseConstants;
import gob.osinergmin.fise.dao.CommonDao;
import gob.osinergmin.fise.dao.FiseGrupoInformacionDao;
import gob.osinergmin.fise.dao.Formato12ACDao;
import gob.osinergmin.fise.dao.Formato13ACDao;
import gob.osinergmin.fise.dao.Formato14ACDao;
import gob.osinergmin.fise.dao.Formato14BCDao;
import gob.osinergmin.fise.dao.Formato14CCDao;
import gob.osinergmin.fise.domain.FiseFormato12AC;
import gob.osinergmin.fise.domain.FiseFormato12ACPK;
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

import java.math.BigDecimal;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

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
	@Qualifier("formato12ACDaoImpl")
	private Formato12ACDao formato12ACDao;
	
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
	public int validarFormatos_14(Formato14Generic formato14, String tipoFormato, 
			String usuario, String terminal) {
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
	
	
	@Transactional
	@Override
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
				
			}else if(FiseConstants.NOMBRE_FORMATO_12C.equals(formato)){ 
				
			}else if(FiseConstants.NOMBRE_FORMATO_12D.equals(formato)){ 
				
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
	
	
	@Transactional
	@Override
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
				
			}else if(FiseConstants.NOMBRE_FORMATO_12C.equals(bean.getFormato())){ 
				
			}else if(FiseConstants.NOMBRE_FORMATO_12D.equals(bean.getFormato())){ 
				
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
	
	
	@Transactional
	@Override
	public List<NotificacionBean> buscarNotificacion(String codEmpresa,
			String flag,String etapa,Long idGrupoInf) throws Exception{
		List<NotificacionBean> lista =null;
		try {		
			/**mensual = formatos 12A,12B,12C,12C
			  bienal = formatos 13A,14A,14B,14C******/			
			if(FiseConstants.MENSUAL.equals(flag)){
				lista = new ArrayList<NotificacionBean>();
				/**FORMATO 12A*/
				 List<Object[]> lista12A = commonDao.listarObsNotificacion(codEmpresa, 
							etapa, FiseConstants.NOMBRE_FORMATO_12A,idGrupoInf);			
				 logger.info("Tamanio de la lista lista12A:  "+lista12A.size()); 
				 for(int i = 0; i < lista12A.size(); i++){					
					NotificacionBean n = new NotificacionBean();
					n.setCodEmpresa(codEmpresa.length()==3 ? codEmpresa+" ":codEmpresa); 
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
				 List<Object[]> lista12B = commonDao.listarObsNotificacion(codEmpresa, 
							etapa, FiseConstants.NOMBRE_FORMATO_12B,idGrupoInf);			
				 logger.info("Tamanio de la lista lista12B :  "+lista12B.size()); 
				 for(int i = 0; i < lista12B.size(); i++){					
					NotificacionBean n = new NotificacionBean();
					n.setCodEmpresa(codEmpresa.length()==3 ? codEmpresa+" ":codEmpresa); 
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
				 List<Object[]> lista12C = commonDao.listarObsNotificacion(codEmpresa, 
							etapa, FiseConstants.NOMBRE_FORMATO_12A,idGrupoInf);			
				 logger.info("Tamanio de la lista lista12C:  "+lista12C.size()); 
				 for(int i = 0; i < lista12C.size(); i++){					
					NotificacionBean n = new NotificacionBean();
					n.setCodEmpresa(codEmpresa.length()==3 ? codEmpresa+" ":codEmpresa); 
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
				 List<Object[]> lista12D = commonDao.listarObsNotificacion(codEmpresa, 
							etapa, FiseConstants.NOMBRE_FORMATO_12A,idGrupoInf);			
				 logger.info("Tamanio de la lista lista12D :  "+lista12D.size()); 
				 for(int i = 0; i < lista12D.size(); i++){					
					NotificacionBean n = new NotificacionBean();
					n.setCodEmpresa(codEmpresa.length()==3 ? codEmpresa+" ":codEmpresa); 
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
			   List<Object[]> lista13A = commonDao.listarObsNotificacion(codEmpresa, 
						etapa, FiseConstants.NOMBRE_FORMATO_13A,idGrupoInf);			
			   logger.info("Tamanio de la lista lista13A:  "+lista13A.size()); 
			   for(int i = 0; i < lista13A.size(); i++){				
					NotificacionBean n = new NotificacionBean();
					n.setCodEmpresa(codEmpresa.length()==3 ? codEmpresa+" ":codEmpresa); 
					n.setAnioPres(String.valueOf((BigDecimal)lista13A.get(i)[1])); 
					n.setMesPres(String.valueOf((BigDecimal)lista13A.get(i)[2]));
					n.setEtapa(etapa);
					n.setFormato(FiseConstants.NOMBRE_FORMATO_13A); 
					n.setAnioEjec("---");
					n.setMesEjec("00");
					n.setAnioIniVig("---");
					n.setAnioFinVig("---");
					lista.add(n);
				}				
				/**FORMATO 14A*/
			   List<Object[]> lista14A = commonDao.listarObsNotificacion(codEmpresa, 
						etapa, FiseConstants.NOMBRE_FORMATO_14A,idGrupoInf);			
			   logger.info("Tamanio de la lista lista14A:  "+lista14A.size()); 
			   for(int i = 0; i < lista14A.size(); i++){
					NotificacionBean n = new NotificacionBean();
					n.setCodEmpresa(codEmpresa.length()==3 ? codEmpresa+" ":codEmpresa); 
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
			   List<Object[]> lista14B = commonDao.listarObsNotificacion(codEmpresa, 
						etapa, FiseConstants.NOMBRE_FORMATO_14B,idGrupoInf);			
			   logger.info("Tamanio de la lista lista14B:  "+lista14B.size()); 
			   for(int i = 0; i < lista14B.size(); i++){
					NotificacionBean n = new NotificacionBean();
					n.setCodEmpresa(codEmpresa.length()==3 ? codEmpresa+" ":codEmpresa); 
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
			   List<Object[]> lista14C = commonDao.listarObsNotificacion(codEmpresa, 
						etapa, FiseConstants.NOMBRE_FORMATO_14C,idGrupoInf);			
			   logger.info("Tamanio de la lista lista14C:  "+lista14C.size());
			   for(int i = 0; i < lista14C.size(); i++){
					NotificacionBean n = new NotificacionBean();
					n.setCodEmpresa(codEmpresa.length()==3 ? codEmpresa+" ":codEmpresa); 
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
		}
	  return lista;
	}
	
	@Transactional
	@Override
	public String notificarValidacionMensual(String codEmpresa, String etapa, 
			long idGrupoInf, String periodicidad, String user,String terminal) throws Exception{
		return commonDao.notificarValidacionMensual(codEmpresa, etapa, idGrupoInf, periodicidad, user, terminal);
	}
	
	
	
}
