package gob.osinergmin.fise.gart.service.impl;

import gob.osinergmin.fise.bean.PeriodoEnvioBean;
import gob.osinergmin.fise.constant.FiseConstants;
import gob.osinergmin.fise.dao.FisePeriodoEnvioDao;
import gob.osinergmin.fise.domain.FisePeriodoEnvio;
import gob.osinergmin.fise.gart.service.FisePeriodoEnvioGartService;
import gob.osinergmin.fise.util.FechaUtil;
import gob.osinergmin.fise.util.FormatoUtil;

import java.util.Date;
import java.util.List;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service(value="fisePeriodoEnvioGartServiceImpl")
public class FisePeriodoEnvioGartServiceImpl implements FisePeriodoEnvioGartService {

	 Logger logger=Logger.getLogger(FisePeriodoEnvioGartServiceImpl.class);
	
	@Autowired
	@Qualifier("fisePeriodoEnvioDaoImpl")
	private FisePeriodoEnvioDao fisePeriodoEnvioDao;
	
	
	@Override
	@Transactional
	public String insertarDatosFisePeriodoEnvio(PeriodoEnvioBean bean) throws Exception{
		FisePeriodoEnvio periodo =null;
		String valor;
		try {	
			boolean verificar= false;
			periodo = new FisePeriodoEnvio();
			periodo.setCodEmpresa(bean.getCodEmpresa());
			periodo.setAnoPresentacion(Integer.valueOf(bean.getAnioPres())); 
			periodo.setMesPresentacion(Integer.valueOf(bean.getMesPres())); 
			periodo.setFormato(bean.getFormato());
			periodo.setEtapa(bean.getEtapa()); 
			if(FiseConstants.PERIODO_ENVIO_ESTADO_VIGENTE.equals(bean.getEstado())){ 
			    verificar = fisePeriodoEnvioDao.verificarPeridoEnvioEmpresa(periodo.getCodEmpresa(),
							periodo.getAnoPresentacion(), periodo.getMesPresentacion(),
							periodo.getFormato(), periodo.getEtapa(), FiseConstants.PERIODO_ENVIO_ESTADO_VIGENTE);	
			}			
			if(!verificar){
				logger.info("Fecha desde:  "+FechaUtil.getFechaStringToDate(bean.getDesde())); 
				logger.info("Fecha hasta:  "+FechaUtil.getFechaStringToDate(bean.getHasta())); 
				periodo.setDesde(FechaUtil.getFechaStringToDate(bean.getDesde()));
				periodo.setHasta(FechaUtil.getFechaStringToDate(bean.getHasta()));
				if(FormatoUtil.isNotBlank(bean.getFechaAmpl())){ 
					periodo.setFechaAmpliada(FechaUtil.getFechaStringToDate(bean.getFechaAmpl())); 
				}			
				periodo.setDiasNotificacionAntesCierre(Integer.valueOf(bean.getDiasNotifCierre())); 
				periodo.setFlagEnvioConObservaciones(bean.getFlagEnvioObs());			
				periodo.setFlagMostrarAnoMesEjec(bean.getFlagAnioMesEjec());
				if(FormatoUtil.isNotBlank(bean.getAnoIniVigencia())){ 
					periodo.setAnoInicioVigencia(Integer.valueOf(bean.getAnoIniVigencia())); 	
				}
				if(FormatoUtil.isNotBlank(bean.getAnoFinVigencia())){ 
					periodo.setAnoFinVigencia(Integer.valueOf(bean.getAnoFinVigencia())); 	
				}			
				periodo.setFlagHabilitaCostosDIF14c(bean.getFlagHabCostos()); 
				periodo.setEstado(bean.getEstado()); 
				periodo.setFlagEditarCostosEst(bean.getFlagEditarCosto()); 
				//auditoria
				periodo.setUsuarioCreacion(bean.getUsuario());
				periodo.setTerminalCreacion(bean.getTerminal()); 
				periodo.setFechaCreacion(FechaUtil.obtenerFechaActual()); 
				fisePeriodoEnvioDao.insertarFisePeriodoEnvio(periodo);	
				logger.info("Secuencial obtenido del insert:  "+periodo.getSecuencia());
				valor = ""+periodo.getSecuencia();
			}else{
				valor = "D";//D = duplicado
			}		
		} catch (Exception e) {
			logger.info("Error al grabar en periodo envio: "+e); 
			valor = "0";
		}finally{
			if(periodo !=null){
				periodo =null;
			}
		}
		return valor;
		
	}
	
	@Override
	@Transactional
	public String actualizarDatosFisePeriodoEnvio(PeriodoEnvioBean bean) throws Exception{
		FisePeriodoEnvio periodo =null;
		String valor ="0";		
		try {	
			boolean verificar= false;
			periodo = fisePeriodoEnvioDao.obtenerFisePeriodoEnvio(Long.valueOf(bean.getSecuencial())); 
			
			if(periodo!=null && FiseConstants.PERIODO_ENVIO_ESTADO_ANULADO.equals(periodo.getEstado())){				
				  if(FiseConstants.PERIODO_ENVIO_ESTADO_VIGENTE.equals(bean.getEstado())){
					  verificar = fisePeriodoEnvioDao.verificarPeridoEnvioEmpresa(bean.getCodEmpresa(),
								Integer.valueOf(bean.getAnioPres()), Integer.valueOf(bean.getMesPres()),
								bean.getFormato(), bean.getEtapa(), FiseConstants.PERIODO_ENVIO_ESTADO_VIGENTE);
				  }		
			}			
			if(!verificar){
				//periodo = fisePeriodoEnvioDao.obtenerFisePeriodoEnvio(Long.valueOf(bean.getSecuencial())); 			
				logger.info("Fecha desde:  "+FechaUtil.getFechaStringToDate(bean.getDesde())); 
				logger.info("Fecha hasta:  "+FechaUtil.getFechaStringToDate(bean.getHasta())); 
				periodo.setDesde(FechaUtil.getFechaStringToDate(bean.getDesde()));
				periodo.setHasta(FechaUtil.getFechaStringToDate(bean.getHasta()));
				
				if(FormatoUtil.isNotBlank(bean.getFechaAmpl())){ 
					periodo.setFechaAmpliada(FechaUtil.getFechaStringToDate(bean.getFechaAmpl())); 
				}else{
					periodo.setFechaAmpliada(null); 
				}				
				periodo.setDiasNotificacionAntesCierre(Integer.valueOf(bean.getDiasNotifCierre())); 
				periodo.setFlagEnvioConObservaciones(bean.getFlagEnvioObs());			
				periodo.setFlagMostrarAnoMesEjec(bean.getFlagAnioMesEjec()); 
				if(FormatoUtil.isNotBlank(bean.getAnoIniVigencia())){ 
					periodo.setAnoInicioVigencia(Integer.valueOf(bean.getAnoIniVigencia())); 	
				}
				if(FormatoUtil.isNotBlank(bean.getAnoFinVigencia())){ 
					periodo.setAnoFinVigencia(Integer.valueOf(bean.getAnoFinVigencia())); 	
				}			
				periodo.setFlagHabilitaCostosDIF14c(bean.getFlagHabCostos()); 
				periodo.setEstado(bean.getEstado()); 
				periodo.setFlagEditarCostosEst(bean.getFlagEditarCosto());
				//auditoria
				periodo.setUsuarioActualizacion(bean.getUsuario());
				periodo.setTerminalActualizacion(bean.getTerminal()); 
				periodo.setFechaActualizacion(FechaUtil.obtenerFechaActual()); 
				fisePeriodoEnvioDao.actualizarFisePeriodoEnvio(periodo);
				valor = "1";
			}else{
				valor ="D";//D= duplicado
			}			
		} catch (Exception e) {
			logger.info("Error al actualizar en periodo envio: "+e); 
			valor = "0";
		}finally{
			if(periodo !=null){
				periodo =null;
			}
		}
		return valor;		
	}
	
	@Override
	@Transactional
	public String eliminarDatosFisePeriodoEnvio(String id,String user,String terminal) throws Exception{
		FisePeriodoEnvio periodo =null;
		String valor ="1";
		try {			
			periodo = fisePeriodoEnvioDao.obtenerFisePeriodoEnvio(Long.valueOf(id));  
			periodo.setEstado("A"); 
			//auditoria
			periodo.setUsuarioActualizacion(user);
			periodo.setTerminalActualizacion(terminal); 
			periodo.setFechaActualizacion(FechaUtil.obtenerFechaActual()); 
			fisePeriodoEnvioDao.actualizarFisePeriodoEnvio(periodo);				
		} catch (Exception e) {
			logger.info("Error al eliminar  periodo envio: "+e); 
			valor = "0";
		}finally{
			if(periodo !=null){
				periodo =null;
			}
		}
		return valor;
	}
	
	@Override
	@Transactional
	public FisePeriodoEnvio obtenerFisePeriodoEnvio(String id) throws Exception{
		return fisePeriodoEnvioDao.obtenerFisePeriodoEnvio(Long.valueOf(id)); 
		
	}
	
	@Override
	@Transactional
	public PeriodoEnvioBean buscarFisePeriodoEnvioEditar(String id) throws Exception{
		FisePeriodoEnvio p =null;
		PeriodoEnvioBean bean =new PeriodoEnvioBean();
		try {
			p = fisePeriodoEnvioDao.obtenerFisePeriodoEnvio(Long.valueOf(id));
			bean.setSecuencial(""+p.getSecuencia());
			bean.setCodEmpresa(p.getCodEmpresa()); 
			bean.setAnioPres(""+p.getAnoPresentacion());
			bean.setMesPres(""+p.getMesPresentacion()); 
			bean.setFormato(p.getFormato()); 			
			bean.setEtapa(p.getEtapa().equals("LEV.OBS")?FiseConstants.ETAPA_LEVOBS_DESC:p.getEtapa());
			bean.setDesde(FechaUtil.getFechaDateToString(p.getDesde()));  
			bean.setHasta(FechaUtil.getFechaDateToString(p.getHasta())); 
			
			if(p.getFechaAmpliada()!=null){ 
				bean.setFechaAmpl(FechaUtil.getFechaDateToString(p.getFechaAmpliada())); 
			}else{
				bean.setFechaAmpl("");	
			}			
			bean.setDiasNotifCierre(""+p.getDiasNotificacionAntesCierre()); 
			bean.setFlagAnioMesEjec(p.getFlagMostrarAnoMesEjec());
			bean.setFlagEnvioObs(p.getFlagEnvioConObservaciones());
			bean.setEstado(p.getEstado());
			bean.setAnoIniVigencia(""+p.getAnoInicioVigencia());
			bean.setAnoFinVigencia(""+p.getAnoFinVigencia()); 
			bean.setFlagHabCostos(p.getFlagHabilitaCostosDIF14c());	
			bean.setFlagEditarCosto(p.getFlagEditarCostosEst()); 
		} catch (Exception e) {
			logger.info("Error al buscar datos para editar:  "+e); 
		}finally{
			if(p!=null)
				p=null;
		}
		return bean;
	}
	
	
	
	@Transactional
	public List<FisePeriodoEnvio> listarFisePeriodoEnvioMesAnioEtapa(String codEmpresa, String nombreFormato){
		return fisePeriodoEnvioDao.listarFisePeriodoEnvioMesAnioEtapa(codEmpresa, nombreFormato);
	}
	
	@Transactional
	@Override
	public List<FisePeriodoEnvio> buscarFisePeriodoEnvio(String codEmpresa, Integer anioPres, 
			Integer mesPres, String formato,String etapa,
			String flagEnvio,String estado,Date fechaActual) throws Exception{
		return fisePeriodoEnvioDao.buscarFisePeriodoEnvio(codEmpresa, anioPres,
				mesPres, formato, etapa, flagEnvio, estado, fechaActual);
	}
	
	@Transactional
	public List<FisePeriodoEnvio> listarFisePeriodoEnvioMesAnioEtapaCumplimiento(String frecuenciaFormato){
		return fisePeriodoEnvioDao.listarFisePeriodoEnvioMesAnioEtapaCumplimiento(frecuenciaFormato);
	}
	
	@Transactional
	@Override
	public String obtenerFlagEnvioConObs(String codEmpresa, Integer anioPres, 
			Integer mesPres, String formato,String etapa,String estado)  throws Exception{
		return fisePeriodoEnvioDao.obtenerFlagEnvioConObs(codEmpresa, anioPres, mesPres, formato, etapa, estado);
	}
	
	/***metodo para buscar plazo maximo de envio de observaciones****/
	@Transactional
	@Override
	public String listarPlazoMaximoEnvioObs(String codEmpresa,long anioPres,
    		long mesPres,String etapa,String formato) throws Exception{
		String plazo = "";
		try {
			plazo = fisePeriodoEnvioDao.listarPlazoMaximoEnvioObs(codEmpresa,
					anioPres, mesPres, etapa, formato);				
		} catch (Exception e) {
			//e.printStackTrace();
			plazo = "";
		}
		return plazo;
	}
	
	
}
