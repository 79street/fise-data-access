package gob.osinergmin.fise.gart.service.impl;

import gob.osinergmin.fise.bean.Formato12ACBean;
import gob.osinergmin.fise.constant.FiseConstants;
import gob.osinergmin.fise.domain.FiseFormato12AC;
import gob.osinergmin.fise.domain.FiseFormato12ACPK;
import gob.osinergmin.fise.domain.FiseFormato12AD;
import gob.osinergmin.fise.domain.FiseFormato12ADPK;
import gob.osinergmin.fise.domain.FiseZonaBenef;
import gob.osinergmin.fise.gart.dao.FiseZonaBenefGartDao;
import gob.osinergmin.fise.gart.dao.Formato12ACGartDao;
import gob.osinergmin.fise.gart.dao.Formato12ADGartDao;
import gob.osinergmin.fise.gart.service.Formato12AGartService;
import gob.osinergmin.fise.util.FechaUtil;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;

@Service(value="formato12AGartServiceImpl")
public class Formato12AGartServiceImpl implements Formato12AGartService {

	Log logger=LogFactoryUtil.getLog(Formato12AGartServiceImpl.class);
	
	@Autowired
	@Qualifier("formato12ACGartDaoImpl")
	private Formato12ACGartDao formato12ACDao;
	
	@Autowired
	@Qualifier("formato12ADGartDaoImpl")
	private Formato12ADGartDao formato12ADDao;
	
	@Autowired
	@Qualifier("fiseZonaBenefGartDaoImpl")
	private FiseZonaBenefGartDao zonaBenefDao;
	
	//@Override
	public List<FiseFormato12AC> listarFormato12AC() {
		return formato12ACDao.listarFormato12AC();
	}
	
	@Override
	public FiseFormato12AC obtenerFormato12ACByPK(FiseFormato12ACPK fiseFormato12ACPK) {
		FiseFormato12AC formato = null;
		formato = formato12ACDao.obtenerFormato12ACByPK(fiseFormato12ACPK);
		if(formato != null){
			formato.setFiseFormato12ADs(formato12ADDao.listarFormato12ADByFormato12AC(formato));
		}
		return formato;
	}
	
	@Override
	@Transactional(propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
	public FiseFormato12AC registrarFormato12AC(Formato12ACBean formulario) {
		
		FiseFormato12AC dto = null;
		
		try {
			Date hoy = FechaUtil.obtenerFechaActual();
			FiseFormato12AC fiseFormato12AC = new FiseFormato12AC();
			//guardar el pk
			FiseFormato12ACPK id = new FiseFormato12ACPK();
			id.setCodEmpresa(formulario.getCodigoEmpresa());
			id.setAnoPresentacion(formulario.getAnioPresent());
			id.setMesPresentacion(formulario.getMesPresent());
			//id.setAnoEjecucionGasto(Long.parseLong(FechaUtil.obtenerNroAnioFechaActual()));
			//id.setMesEjecucionGasto(Long.parseLong(FechaUtil.obtenerNroMesFechaActual()));
			id.setAnoEjecucionGasto(formulario.getAnioEjecuc());
			id.setMesEjecucionGasto(formulario.getMesEjecuc());
			id.setEtapa(FiseConstants.ETAPA_SOLICITUD);
			fiseFormato12AC.setId(id);
			
			logger.info(formulario.getNroEmpadP()!=0);
			//total de las 3 detalles segun sea el caso
			BigDecimal total = new BigDecimal(0);
			List<FiseFormato12AD> lista = new ArrayList<FiseFormato12AD>();
			//RURAL
			if( formulario.getNroEmpadR() != 0 &&
					!formulario.getCostoUnitEmpadR().equals(BigDecimal.ZERO) &&
					formulario.getNroAgentR() != 0 &&
					!formulario.getCostoUnitAgentR().equals(BigDecimal.ZERO) &&
					!formulario.getDesplPersonalR().equals(BigDecimal.ZERO) &&
					!formulario.getActivExtraordR().equals(BigDecimal.ZERO) 
					){
				logger.info("entro a RURAL");
				FiseZonaBenef zonaBenef = new FiseZonaBenef();
				zonaBenef = zonaBenefDao.obtenerFiseZonaBenefByPK(FiseConstants.ZONABENEF_RURAL);
				//
				FiseFormato12AD detalleRural = new FiseFormato12AD();
				//pk
				FiseFormato12ADPK pkDetalle = new FiseFormato12ADPK();
				pkDetalle.setCodEmpresa(fiseFormato12AC.getId().getCodEmpresa());
				pkDetalle.setAnoPresentacion(fiseFormato12AC.getId().getAnoPresentacion());
				pkDetalle.setMesPresentacion(fiseFormato12AC.getId().getMesPresentacion());
				pkDetalle.setAnoEjecucionGasto(fiseFormato12AC.getId().getAnoEjecucionGasto());
				pkDetalle.setMesEjecucionGasto(fiseFormato12AC.getId().getMesEjecucionGasto());
				pkDetalle.setIdZonaBenef(zonaBenef.getIdZonaBenef());
				pkDetalle.setEtapa(fiseFormato12AC.getId().getEtapa());
				detalleRural.setId(pkDetalle);
				
				detalleRural.setNumeroEmpadronados(formulario.getNroEmpadR());
				detalleRural.setCostoEstandarUnitarioEmpad(formulario.getCostoUnitEmpadR());
				BigDecimal costoTotalEmpad = detalleRural.getCostoEstandarUnitarioEmpad().multiply(new BigDecimal(detalleRural.getNumeroEmpadronados()));
				detalleRural.setCostoTotalEmpadronamiento(costoTotalEmpad);
				detalleRural.setNumeroAgentesAutorizGlp(formulario.getNroAgentR());
				detalleRural.setCostoEstandarUnitAgAutGlp(formulario.getCostoUnitAgentR());
				BigDecimal costoTotalAgent = detalleRural.getCostoEstandarUnitAgAutGlp().multiply(new BigDecimal(detalleRural.getNumeroAgentesAutorizGlp()));
				detalleRural.setCostoTotalGestRedAgGlp(costoTotalAgent);
				BigDecimal totalDespl = formulario.getDesplPersonalR();
				detalleRural.setTotalDesplazamientoPersonal(totalDespl);
				BigDecimal totalActiv = formulario.getActivExtraordR();
				detalleRural.setTotalActividadesExtraord(totalActiv);
				BigDecimal totalRural = detalleRural.getCostoTotalEmpadronamiento()
						.add(detalleRural.getCostoTotalGestRedAgGlp())
						.add(detalleRural.getTotalDesplazamientoPersonal())
						.add(detalleRural.getTotalActividadesExtraord());
				//
				detalleRural.setFiseFormato12AC(fiseFormato12AC);
				//ªªdetalleRural.setFiseZonaBenef(zonaBenef);
				//
				detalleRural.setUsuarioCreacion(formulario.getUsuario());
				detalleRural.setTerminalCreacion(formulario.getTerminal());
				detalleRural.setFechaCreacion(hoy);
				detalleRural.setUsuarioActualizacion(formulario.getUsuario());
				detalleRural.setTerminalActualizacion(formulario.getTerminal());
				detalleRural.setFechaActualizacion(hoy);
				//grabar detalle
				logger.info("totalrural"+totalRural);
				total = total.add(totalRural);
				//total.add(totalRural);
				logger.info("total hasta rural"+total);
				//TrimUtil.trimAll(detalleRural);
				lista.add(detalleRural);
			}
			//PROVINCIA
			if( formulario.getNroEmpadP() != 0 &&
					!formulario.getCostoUnitEmpadP().equals(BigDecimal.ZERO) &&
					formulario.getNroAgentP() != 0 &&
					!formulario.getCostoUnitAgentP().equals(BigDecimal.ZERO) &&
					!formulario.getDesplPersonalP().equals(BigDecimal.ZERO) &&
					!formulario.getActivExtraordP().equals(BigDecimal.ZERO) 
					){
				logger.info("entro a PROVINCIA");
				FiseZonaBenef zonaBenef = new FiseZonaBenef();
				zonaBenef = zonaBenefDao.obtenerFiseZonaBenefByPK(FiseConstants.ZONABENEF_PROVINCIA);
				//
				FiseFormato12AD detalleProvincia = new FiseFormato12AD();
				//pk
				FiseFormato12ADPK pkDetalle = new FiseFormato12ADPK();
				pkDetalle.setCodEmpresa(fiseFormato12AC.getId().getCodEmpresa());
				pkDetalle.setAnoPresentacion(fiseFormato12AC.getId().getAnoPresentacion());
				pkDetalle.setMesPresentacion(fiseFormato12AC.getId().getMesPresentacion());
				pkDetalle.setAnoEjecucionGasto(fiseFormato12AC.getId().getAnoEjecucionGasto());
				pkDetalle.setMesEjecucionGasto(fiseFormato12AC.getId().getMesEjecucionGasto());
				pkDetalle.setIdZonaBenef(zonaBenef.getIdZonaBenef());
				pkDetalle.setEtapa(fiseFormato12AC.getId().getEtapa());
				detalleProvincia.setId(pkDetalle);
				
				detalleProvincia.setNumeroEmpadronados(formulario.getNroEmpadP());
				detalleProvincia.setCostoEstandarUnitarioEmpad(formulario.getCostoUnitEmpadP());
				BigDecimal costoTotalEmpad = detalleProvincia.getCostoEstandarUnitarioEmpad().multiply(new BigDecimal(detalleProvincia.getNumeroEmpadronados()));
				detalleProvincia.setCostoTotalEmpadronamiento(costoTotalEmpad);
				detalleProvincia.setNumeroAgentesAutorizGlp(formulario.getNroAgentP());
				detalleProvincia.setCostoEstandarUnitAgAutGlp(formulario.getCostoUnitAgentP());
				BigDecimal costoTotalAgent = detalleProvincia.getCostoEstandarUnitAgAutGlp().multiply(new BigDecimal(detalleProvincia.getNumeroAgentesAutorizGlp()));
				detalleProvincia.setCostoTotalGestRedAgGlp(costoTotalAgent);
				BigDecimal totalDespl = formulario.getDesplPersonalP();
				detalleProvincia.setTotalDesplazamientoPersonal(totalDespl);
				BigDecimal totalActiv = formulario.getActivExtraordP();
				detalleProvincia.setTotalActividadesExtraord(totalActiv);
				BigDecimal totalProvincia = detalleProvincia.getCostoTotalEmpadronamiento()
						.add(detalleProvincia.getCostoTotalGestRedAgGlp())
						.add(detalleProvincia.getTotalDesplazamientoPersonal())
						.add(detalleProvincia.getTotalActividadesExtraord());
				//
				detalleProvincia.setFiseFormato12AC(fiseFormato12AC);
				//ªªªdetalleProvincia.setFiseZonaBenef(zonaBenef);
				//
				detalleProvincia.setUsuarioCreacion(formulario.getUsuario());
				detalleProvincia.setTerminalCreacion(formulario.getTerminal());
				detalleProvincia.setFechaCreacion(hoy);
				detalleProvincia.setUsuarioActualizacion(formulario.getUsuario());
				detalleProvincia.setTerminalActualizacion(formulario.getTerminal());
				detalleProvincia.setFechaActualizacion(hoy);
				//grabar detalle
				logger.info("totalprovincia"+totalProvincia);
				total = total.add(totalProvincia);
				//total.add(totalProvincia);
				logger.info("total hasta provincia"+total);
				//TrimUtil.trimAll(detalleProvincia);
				lista.add(detalleProvincia);
			}
			//LIMA
			if( formulario.getNroEmpadL() != 0 &&
					!formulario.getCostoUnitEmpadL().equals(BigDecimal.ZERO) &&
					formulario.getNroAgentL() != 0 &&
					!formulario.getCostoUnitAgentL().equals(BigDecimal.ZERO) &&
					!formulario.getDesplPersonalL().equals(BigDecimal.ZERO) &&
					!formulario.getActivExtraordL().equals(BigDecimal.ZERO) 
					){
				logger.info("entro a LIMA");
				FiseZonaBenef zonaBenef = new FiseZonaBenef();
				zonaBenef = zonaBenefDao.obtenerFiseZonaBenefByPK(FiseConstants.ZONABENEF_LIMA);
				//
				FiseFormato12AD detalleLima = new FiseFormato12AD();
				//pk
				FiseFormato12ADPK pkDetalle = new FiseFormato12ADPK();
				pkDetalle.setCodEmpresa(fiseFormato12AC.getId().getCodEmpresa());
				pkDetalle.setAnoPresentacion(fiseFormato12AC.getId().getAnoPresentacion());
				pkDetalle.setMesPresentacion(fiseFormato12AC.getId().getMesPresentacion());
				pkDetalle.setAnoEjecucionGasto(fiseFormato12AC.getId().getAnoEjecucionGasto());
				pkDetalle.setMesEjecucionGasto(fiseFormato12AC.getId().getMesEjecucionGasto());
				pkDetalle.setIdZonaBenef(zonaBenef.getIdZonaBenef());
				pkDetalle.setEtapa(fiseFormato12AC.getId().getEtapa());
				detalleLima.setId(pkDetalle);
				
				detalleLima.setNumeroEmpadronados(formulario.getNroEmpadL());
				detalleLima.setCostoEstandarUnitarioEmpad(formulario.getCostoUnitEmpadL());
				BigDecimal costoTotalEmpad = detalleLima.getCostoEstandarUnitarioEmpad().multiply(new BigDecimal(detalleLima.getNumeroEmpadronados()));
				detalleLima.setCostoTotalEmpadronamiento(costoTotalEmpad);
				detalleLima.setNumeroAgentesAutorizGlp(formulario.getNroAgentL());
				detalleLima.setCostoEstandarUnitAgAutGlp(formulario.getCostoUnitAgentL());
				BigDecimal costoTotalAgent = detalleLima.getCostoEstandarUnitAgAutGlp().multiply(new BigDecimal(detalleLima.getNumeroAgentesAutorizGlp()));
				detalleLima.setCostoTotalGestRedAgGlp(costoTotalAgent);
				BigDecimal totalDespl = formulario.getDesplPersonalL();
				detalleLima.setTotalDesplazamientoPersonal(totalDespl);
				BigDecimal totalActiv = formulario.getActivExtraordL();
				detalleLima.setTotalActividadesExtraord(totalActiv);
				BigDecimal totalLima = detalleLima.getCostoTotalEmpadronamiento()
						.add(detalleLima.getCostoTotalGestRedAgGlp())
						.add(detalleLima.getTotalDesplazamientoPersonal())
						.add(detalleLima.getTotalActividadesExtraord());
				detalleLima.setFiseFormato12AC(fiseFormato12AC);
				//ªªªªdetalleLima.setFiseZonaBenef(zonaBenef);
				//
				detalleLima.setUsuarioCreacion(formulario.getUsuario());
				detalleLima.setTerminalCreacion(formulario.getTerminal());
				detalleLima.setFechaCreacion(hoy);
				detalleLima.setUsuarioActualizacion(formulario.getUsuario());
				detalleLima.setTerminalActualizacion(formulario.getTerminal());
				detalleLima.setFechaActualizacion(hoy);
				//grabar detalle
				logger.info("totallima"+totalLima);
				total = total.add(totalLima);
				//total.add(totalLima);
				logger.info("total hasta lima"+total);
				//TrimUtil.trimAll(detalleLima);
				lista.add(detalleLima);
			}
			logger.info("total de total"+total);
			fiseFormato12AC.setTotalAReconocer(total);
			fiseFormato12AC.setUsuarioActualizacion(formulario.getUsuario());
			fiseFormato12AC.setTerminalActualizacion(formulario.getTerminal());
			fiseFormato12AC.setFechaActualizacion(hoy);
			fiseFormato12AC.setUsuarioCreacion(formulario.getUsuario());
			fiseFormato12AC.setTerminalCreacion(formulario.getTerminal());
			fiseFormato12AC.setFechaCreacion(hoy);
			
			logger.info("aca se va  a guardar"+fiseFormato12AC);
			//fiseFormato12AC = (FiseFormato12AC) TrimUtil.trimReflective(fiseFormato12AC);
			formato12ACDao.registrarFormato12AC(fiseFormato12AC);
			
			//add
			for (FiseFormato12AD detalle : lista) {
				formato12ADDao.registrarFormato12AD(detalle);
			}
			
			if( lista != null && lista.size()>0 ){
				fiseFormato12AC.setFiseFormato12ADs(lista);
			}
			dto = fiseFormato12AC;
			
			
			/*boolean existe = false;
			existe = formatoService.existeFormato(fiseFormato12AC);
			
			if(existe){
				logger.info("--formato existe");
				formatoService.modificarFormato(fiseFormato12AC);
			}else{
				logger.info("--formato no existe");
				fiseFormato12AC.setUsuarioCreacion(user.getLogin());
				fiseFormato12AC.setTerminalCreacion(user.getLoginIP());
				fiseFormato12AC.setFechaCreacion(hoy);
				formatoService.registrarFormato(fiseFormato12AC);
			}*/
			
		} 	catch (Exception e) {
			logger.error("--error"+e.getMessage());
			e.printStackTrace();
		}
		return dto;
	}
	
	@Override
	@Transactional(propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
	public FiseFormato12AC modificarFormato12AC(Formato12ACBean formulario, FiseFormato12AC fiseFormato12AC) {
		
		FiseFormato12AC dto = null;
		
		try {
			
			Date hoy = FechaUtil.obtenerFechaActual();
	
			BigDecimal total = new BigDecimal(0);
			List<FiseFormato12AD> lista = new ArrayList<FiseFormato12AD>();
			
			FiseFormato12AD detalleRural = new FiseFormato12AD();
			FiseFormato12AD detalleProvincia = new FiseFormato12AD();
			FiseFormato12AD detalleLima = new FiseFormato12AD();
			if( fiseFormato12AC.getFiseFormato12ADs()!=null ){
				for (FiseFormato12AD detalle : fiseFormato12AC.getFiseFormato12ADs()) {
					if( detalle.getId().getIdZonaBenef()==FiseConstants.ZONABENEF_RURAL ){
						detalleRural = detalle;
					}else if( detalle.getId().getIdZonaBenef()==FiseConstants.ZONABENEF_PROVINCIA ){
						detalleProvincia = detalle;
					} else if( detalle.getId().getIdZonaBenef()==FiseConstants.ZONABENEF_LIMA ){
						detalleLima = detalle;
					}
				}
			}
			
			//RURAL
			if( detalleRural != null &&
					formulario.getNroEmpadR() != 0 &&
					!formulario.getCostoUnitEmpadR().equals(BigDecimal.ZERO) &&
					formulario.getNroAgentR() != 0 &&
					!formulario.getCostoUnitAgentR().equals(BigDecimal.ZERO) &&
					!formulario.getDesplPersonalR().equals(BigDecimal.ZERO) &&
					!formulario.getActivExtraordR().equals(BigDecimal.ZERO) 
					){
				logger.info("se modificara RURAL");
				detalleRural.setNumeroEmpadronados(formulario.getNroEmpadR());
				detalleRural.setCostoEstandarUnitarioEmpad(formulario.getCostoUnitEmpadR());
				BigDecimal costoTotalEmpad = detalleRural.getCostoEstandarUnitarioEmpad().multiply(new BigDecimal(detalleRural.getNumeroEmpadronados()));
				detalleRural.setCostoTotalEmpadronamiento(costoTotalEmpad);
				detalleRural.setNumeroAgentesAutorizGlp(formulario.getNroAgentR());
				detalleRural.setCostoEstandarUnitAgAutGlp(formulario.getCostoUnitAgentR());
				BigDecimal costoTotalAgent = detalleRural.getCostoEstandarUnitAgAutGlp().multiply(new BigDecimal(detalleRural.getNumeroAgentesAutorizGlp()));
				detalleRural.setCostoTotalGestRedAgGlp(costoTotalAgent);
				BigDecimal totalDespl = formulario.getDesplPersonalR();
				detalleRural.setTotalDesplazamientoPersonal(totalDespl);
				BigDecimal totalActiv = formulario.getActivExtraordR();
				detalleRural.setTotalActividadesExtraord(totalActiv);
				BigDecimal totalRural = detalleRural.getCostoTotalEmpadronamiento()
						.add(detalleRural.getCostoTotalGestRedAgGlp())
						.add(detalleRural.getTotalDesplazamientoPersonal())
						.add(detalleRural.getTotalActividadesExtraord());
				//
				//--detalleRural.setFiseFormato12AC(fiseFormato12AC);
				//
				detalleRural.setUsuarioActualizacion(formulario.getUsuario());
				detalleRural.setTerminalActualizacion(formulario.getTerminal());
				detalleRural.setFechaActualizacion(hoy);
				//grabar detalle
				logger.info("totalrural modifica"+totalRural);
				total = total.add(totalRural);
				//total.add(totalRural);
				logger.info("total hasta rural modifica"+total);
				//TrimUtil.trimAll(detalleRural);
				lista.add(detalleRural);
			}
			//PROVINCIA
			if( detalleProvincia != null &&
					formulario.getNroEmpadP() != 0 &&
					!formulario.getCostoUnitEmpadP().equals(BigDecimal.ZERO) &&
					formulario.getNroAgentP() != 0 &&
					!formulario.getCostoUnitAgentP().equals(BigDecimal.ZERO) &&
					!formulario.getDesplPersonalP().equals(BigDecimal.ZERO) &&
					!formulario.getActivExtraordP().equals(BigDecimal.ZERO) 
					){
				logger.info("se modificara PROVINCIA");
				detalleProvincia.setNumeroEmpadronados(formulario.getNroEmpadP());
				detalleProvincia.setCostoEstandarUnitarioEmpad(formulario.getCostoUnitEmpadP());
				BigDecimal costoTotalEmpad = detalleProvincia.getCostoEstandarUnitarioEmpad().multiply(new BigDecimal(detalleProvincia.getNumeroEmpadronados()));
				detalleProvincia.setCostoTotalEmpadronamiento(costoTotalEmpad);
				detalleProvincia.setNumeroAgentesAutorizGlp(formulario.getNroAgentP());
				detalleProvincia.setCostoEstandarUnitAgAutGlp(formulario.getCostoUnitAgentP());
				BigDecimal costoTotalAgent = detalleProvincia.getCostoEstandarUnitAgAutGlp().multiply(new BigDecimal(detalleProvincia.getNumeroAgentesAutorizGlp()));
				detalleProvincia.setCostoTotalGestRedAgGlp(costoTotalAgent);
				BigDecimal totalDespl = formulario.getDesplPersonalP();
				detalleProvincia.setTotalDesplazamientoPersonal(totalDespl);
				BigDecimal totalActiv = formulario.getActivExtraordP();
				detalleProvincia.setTotalActividadesExtraord(totalActiv);
				BigDecimal totalProvincia = detalleProvincia.getCostoTotalEmpadronamiento()
						.add(detalleProvincia.getCostoTotalGestRedAgGlp())
						.add(detalleProvincia.getTotalDesplazamientoPersonal())
						.add(detalleProvincia.getTotalActividadesExtraord());
				//
				//-detalleProvincia.setFiseFormato12AC(fiseFormato12AC);
				//
				detalleProvincia.setUsuarioActualizacion(formulario.getUsuario());
				detalleProvincia.setTerminalActualizacion(formulario.getTerminal());
				detalleProvincia.setFechaActualizacion(hoy);
				//grabar detalle
				logger.info("totalprovincia modificar"+totalProvincia);
				total = total.add(totalProvincia);
				//total.add(totalProvincia);
				logger.info("total hasta provincia modificar"+total);
				//TrimUtil.trimAll(detalleProvincia);
				lista.add(detalleProvincia);
			}
			//LIMA
			if( detalleLima != null &&
					formulario.getNroEmpadL() != 0 &&
					!formulario.getCostoUnitEmpadL().equals(BigDecimal.ZERO) &&
					formulario.getNroAgentL() != 0 &&
					!formulario.getCostoUnitAgentL().equals(BigDecimal.ZERO) &&
					!formulario.getDesplPersonalL().equals(BigDecimal.ZERO) &&
					!formulario.getActivExtraordL().equals(BigDecimal.ZERO) 
					){
				logger.info("se modificara LIMA");
				detalleLima.setNumeroEmpadronados(formulario.getNroEmpadL());
				detalleLima.setCostoEstandarUnitarioEmpad(formulario.getCostoUnitEmpadL());
				BigDecimal costoTotalEmpad = detalleLima.getCostoEstandarUnitarioEmpad().multiply(new BigDecimal(detalleLima.getNumeroEmpadronados()));
				detalleLima.setCostoTotalEmpadronamiento(costoTotalEmpad);
				detalleLima.setNumeroAgentesAutorizGlp(formulario.getNroAgentL());
				detalleLima.setCostoEstandarUnitAgAutGlp(formulario.getCostoUnitAgentL());
				BigDecimal costoTotalAgent = detalleLima.getCostoEstandarUnitAgAutGlp().multiply(new BigDecimal(detalleLima.getNumeroAgentesAutorizGlp()));
				detalleLima.setCostoTotalGestRedAgGlp(costoTotalAgent);
				BigDecimal totalDespl = formulario.getDesplPersonalL();
				detalleLima.setTotalDesplazamientoPersonal(totalDespl);
				BigDecimal totalActiv = formulario.getActivExtraordL();
				detalleLima.setTotalActividadesExtraord(totalActiv);
				BigDecimal totalLima = detalleLima.getCostoTotalEmpadronamiento()
						.add(detalleLima.getCostoTotalGestRedAgGlp())
						.add(detalleLima.getTotalDesplazamientoPersonal())
						.add(detalleLima.getTotalActividadesExtraord());
				//--detalleLima.setFiseFormato12AC(fiseFormato12AC);
				//
				detalleLima.setUsuarioActualizacion(formulario.getUsuario());
				detalleLima.setTerminalActualizacion(formulario.getTerminal());
				detalleLima.setFechaActualizacion(hoy);
				//grabar detalle
				logger.info("totallima modificar"+totalLima);
				total = total.add(totalLima);
				//total.add(totalLima);
				logger.info("total hasta lima modificar"+total);
				//TrimUtil.trimAll(detalleLima);
				lista.add(detalleLima);
			}
			logger.info("total de total"+total);
			fiseFormato12AC.setTotalAReconocer(total);
		
		
			fiseFormato12AC.setUsuarioActualizacion(formulario.getUsuario());
			fiseFormato12AC.setTerminalActualizacion(formulario.getTerminal());
			fiseFormato12AC.setFechaActualizacion(hoy);
		
			formato12ACDao.modificarFormato12AC(fiseFormato12AC);
			//add
			for (FiseFormato12AD detalle : lista) {
				formato12ADDao.modificarFormato12AD(detalle);
			}
			dto= fiseFormato12AC;
			
		}	catch (Exception e) {
			logger.error("--error"+e.getMessage());
			e.printStackTrace();
		}
		//
		return dto;

	}
	
	@Override
	@Transactional(propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
	public void eliminarFormato12AC(FiseFormato12AC fiseFormato12AC) {
		//eliminar antes los detalles creados para esa cabecera
		List<FiseFormato12AD> lista = null;
		lista = formato12ADDao.listarFormato12ADByFormato12AC(fiseFormato12AC);
		//por el momento no se esta borrando las dependencias hacias el detalle de observaciones
		//luego aumentar esta dependencia
		for (FiseFormato12AD detalle : lista) {
			formato12ADDao.eliminarFormato12AD(detalle);
		}
		formato12ACDao.eliminarFormato12AC(fiseFormato12AC);
	} 
	
	@Override
	public boolean existeFormato12AC(FiseFormato12AC fiseFormato12AC) {
		return formato12ACDao.existeFormato12AC(fiseFormato12AC);
	}

	@Override
	public List<FiseFormato12AC> buscarFormato12AC(String codEmpresa, long anioDesde, long mesDesde, long anioHasta, long mesHasta, String etapa){
		
		List<FiseFormato12AC> lista = null;
		lista = formato12ACDao.buscarFormato12AC(codEmpresa, anioDesde, mesDesde, anioHasta, mesHasta, etapa);
		for (FiseFormato12AC formato : lista) {
			formato.setFiseFormato12ADs(formato12ADDao.listarFormato12ADByFormato12AC(formato));
		}
		return lista;
	}

}
