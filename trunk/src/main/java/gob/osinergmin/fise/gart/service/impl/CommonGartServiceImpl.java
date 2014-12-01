package gob.osinergmin.fise.gart.service.impl;

import gob.osinergmin.fise.bean.CorreoBean;
import gob.osinergmin.fise.bean.Formato12A12BGeneric;
import gob.osinergmin.fise.bean.Formato12C12D13Generic;
import gob.osinergmin.fise.bean.Formato14Generic;
import gob.osinergmin.fise.bean.AutorizarReenvioBean;
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
		  logger.info("Error al listar formato reenvio:  "+e);
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
	
	
	
}
