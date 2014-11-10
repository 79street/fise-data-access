package gob.osinergmin.fise.gart.service.impl;

import gob.osinergmin.fise.bean.Formato14BCBean;
import gob.osinergmin.fise.constant.FiseConstants;
import gob.osinergmin.fise.dao.FiseZonaBenefDao;
import gob.osinergmin.fise.dao.Formato14BCDao;
import gob.osinergmin.fise.dao.Formato14BDDao;
import gob.osinergmin.fise.dao.Formato14BDObDao;
import gob.osinergmin.fise.domain.FiseFormato14BC;
import gob.osinergmin.fise.domain.FiseFormato14BCPK;
import gob.osinergmin.fise.domain.FiseFormato14BD;
import gob.osinergmin.fise.domain.FiseFormato14BDOb;
import gob.osinergmin.fise.domain.FiseFormato14BDPK;
import gob.osinergmin.fise.domain.FiseZonaBenef;
import gob.osinergmin.fise.gart.service.Formato14BGartService;
import gob.osinergmin.fise.util.FechaUtil;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

@Service(value="formato14BGartServiceImpl")
public class Formato14BGartServiceImpl implements Formato14BGartService {
	
	Logger logger=Logger.getLogger(Formato14BGartServiceImpl.class);
	
	@Autowired
	@Qualifier("formato14BCDaoImpl")
	private Formato14BCDao formato14BCDao;
	
	@Autowired
	@Qualifier("formato14BDDaoImpl")
	private Formato14BDDao formato14BDDao;
	
	@Autowired
	@Qualifier("fiseZonaBenefDaoImpl")
	private FiseZonaBenefDao zonaBenefDao;
	
	@Autowired
	@Qualifier("formato14BDObDaoImpl")
	private Formato14BDObDao formato14BObsDao;
	
	@Override
	@Transactional
	public FiseFormato14BD obtenerFormato14BDVigente(String codEmpresa, long anioVigencia, long idZonaBenef){
		return formato14BDDao.obtenerFormato14BDVigente(codEmpresa, anioVigencia, idZonaBenef);
	}

	@Override
	@Transactional
	public FiseFormato14BC obtenerFormato14BCByPK(FiseFormato14BCPK fiseFormato14BCPK) {
		FiseFormato14BC formato = null;
		formato = formato14BCDao.obtenerFormato14BCByPK(fiseFormato14BCPK);
		if(formato != null){
			formato.setFiseFormato14BDs(formato14BDDao.listarFormato14BDByFormato14BC(formato));
		}
		return formato;
	}
	
	@Override
	@Transactional
	public List<FiseFormato14BC> buscarFormato14BC(String codEmpresa, long anioDesde, long mesDesde, long anioHasta, long mesHasta, String etapa){
		
		List<FiseFormato14BC> lista = null;
		lista = formato14BCDao.buscarFormato14BC(codEmpresa, anioDesde, mesDesde, anioHasta, mesHasta, etapa);
		for (FiseFormato14BC formato : lista) {
			formato.setFiseFormato14BDs(formato14BDDao.listarFormato14BDByFormato14BC(formato));
		}
		return lista;
	}
	
	@Override
	@Transactional(propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
	public FiseFormato14BC registrarFormato14BC(Formato14BCBean formulario) throws Exception {
		
		FiseFormato14BC dto = null;
		
		try {
			Date hoy = FechaUtil.obtenerFechaActual();
			FiseFormato14BC fiseFormato14BC = new FiseFormato14BC();
			//guardar el pk
			FiseFormato14BCPK id = new FiseFormato14BCPK();
			id.setCodEmpresa(formulario.getCodigoEmpresa());
			id.setAnoPresentacion(formulario.getAnioPresent());
			id.setMesPresentacion(formulario.getMesPresent());
			id.setAnoInicioVigencia(formulario.getAnioInicioVigencia());
			id.setAnoFinVigencia(formulario.getAnioFinVigencia());
			id.setEtapa(FiseConstants.ETAPA_SOLICITUD);
			/*if( FiseConstants.TIPOARCHIVO_XLS.equals(formulario.getTipoArchivo()) ){
				fiseFormato14BC.setArchivoExcel(formulario.getNombreArchivo());
			}else if( FiseConstants.TIPOARCHIVO_TXT.equals(formulario.getTipoArchivo()) ){
				fiseFormato14BC.setArchivoTexto(formulario.getNombreArchivo());
			}else{
				id.setEtapa(formulario.getEtapa());
			}*/
			
			fiseFormato14BC.setId(id);
			
			List<FiseFormato14BD> lista = new ArrayList<FiseFormato14BD>();
			//RURAL
			/*if( formulario.getNroValesImpR() != 0 &&
					formulario.getNroValesReptR() != 0 &&
					formulario.getNroValesEntrR() != 0 &&
					formulario.getNroValesFisR() != 0 &&
					formulario.getNroTotalAtenR() != 0
					){
				logger.info("entro a RURAL");
				FiseZonaBenef zonaBenef = new FiseZonaBenef();
				zonaBenef = zonaBenefDao.obtenerFiseZonaBenefByPK(FiseConstants.ZONABENEF_RURAL_COD);
				//
				FiseFormato14BD detalleRural = new FiseFormato14BD();
				//pk
				FiseFormato14BDPK pkDetalle = new FiseFormato14BDPK();
				pkDetalle.setCodEmpresa(fiseFormato14BC.getId().getCodEmpresa());
				pkDetalle.setAnoPresentacion(fiseFormato14BC.getId().getAnoPresentacion());
				pkDetalle.setMesPresentacion(fiseFormato14BC.getId().getMesPresentacion());
				pkDetalle.setAnoInicioVigencia(fiseFormato14BC.getId().getAnoInicioVigencia());
				pkDetalle.setAnoFinVigencia(fiseFormato14BC.getId().getAnoFinVigencia());
				pkDetalle.setIdZonaBenef(zonaBenef.getIdZonaBenef());
				pkDetalle.setEtapa(fiseFormato14BC.getId().getEtapa());
				detalleRural.setId(pkDetalle);
				
				detalleRural.setImpresionValDsctoCliDisEl(formulario.getImpValDesctoEdeR());
				detalleRural.setImpreValDsctoCliNoDisEl(formulario.getImpValDesctoNoEdeR());
				detalleRural.setCostoTotalImpresion(formulario.getCostoTotalImpR());
				detalleRural.setNumeroValesImpreso(formulario.getNroValesImpR());
				*//***falta*//*
				
				
				
				//empadronamiento
				detalleRural.setImpresionEsquelaInvitacion(formulario.getImprEsqInvitR());
				detalleRural.setImpresionDeclaracionJurada(formulario.getImprDeclaJuradaR());
				detalleRural.setImpresionFichasVerificacion(formulario.getImprFichaVerifR());
				detalleRural.setRepartoEsquelaInvitacion(formulario.getRepartoEsqInvitR());
				detalleRural.setVerificacionInformacion(formulario.getVerifInfoR());
				detalleRural.setElaboracionArchivoBenef(formulario.getElabArchivoBenefR());
				detalleRural.setDigitacionFichaBenef(formulario.getDigitFichaBenefR());
				
				BigDecimal totalEmpadronamiento = new BigDecimal(0);
				totalEmpadronamiento = totalEmpadronamiento.add(detalleRural.getImpresionEsquelaInvitacion())
						.add(detalleRural.getImpresionDeclaracionJurada())
						.add(detalleRural.getImpresionFichasVerificacion())
						.add(detalleRural.getVerificacionInformacion())
						.add(detalleRural.getRepartoEsquelaInvitacion())
						.add(detalleRural.getElaboracionArchivoBenef())
						.add(detalleRural.getDigitacionFichaBenef());
				detalleRural.setTotalEmpadronamiento(totalEmpadronamiento);
				
				detalleRural.setImpresionVolantes(formulario.getImprVolantesR());
				detalleRural.setImpresionAfiches(formulario.getImprAfichesR());
				detalleRural.setRepartoFolletoPotenciaBenef(formulario.getRepFolletosR());
				detalleRural.setSpotPublicitarioTv(formulario.getSpotPublTvR());
				detalleRural.setSpotPublicitarioRadio(formulario.getSpotPublRadioR());
				
				BigDecimal totalDifusion = new BigDecimal(0);
				totalDifusion = totalDifusion.add(detalleRural.getImpresionVolantes())
						.add(detalleRural.getImpresionAfiches())
						.add(detalleRural.getRepartoFolletoPotenciaBenef())
						.add(detalleRural.getSpotPublicitarioTv())
						.add(detalleRural.getSpotPublicitarioRadio());
				detalleRural.setTotalDifusionInicioPrgFise(totalDifusion);
				
				BigDecimal sumaEmpadDifusion = new BigDecimal(0);
				sumaEmpadDifusion = sumaEmpadDifusion.add(detalleRural.getTotalEmpadronamiento()).add(detalleRural.getTotalDifusionInicioPrgFise());
				
				detalleRural.setNumeroBenefEmpadroMesDic(formulario.getNroBenefEmpadR());
				//costo
				if( formulario.getNroBenefEmpadR() !=0 ){
					detalleRural.setCostoUnitarioEmpadronamiento(sumaEmpadDifusion.divide(new BigDecimal(detalleRural.getNumeroBenefEmpadroMesDic()),4,RoundingMode.HALF_UP));
				}else{
					detalleRural.setCostoUnitarioEmpadronamiento(BigDecimal.ZERO);
				}
				//agentes
				detalleRural.setPromocionConvenioAgAutGlp(formulario.getPromConvAgentR());
				detalleRural.setRegistroFirmaConvAgAutGlp(formulario.getRegConvAgentR());
				detalleRural.setImpresionEntregaBanderola(formulario.getImpEntrBandR());
				
				BigDecimal totalAgentes = new BigDecimal(0);
				totalAgentes = totalAgentes.add(detalleRural.getPromocionConvenioAgAutGlp())
						.add(detalleRural.getRegistroFirmaConvAgAutGlp())
						.add(detalleRural.getImpresionEntregaBanderola());
				detalleRural.setTotalCostoGestionRedAgGlp(totalAgentes);
				
				detalleRural.setNumeroAgentes(formulario.getNroAgentR());
				//costo
				if( formulario.getNroAgentR() !=0 ){
					detalleRural.setCostoUntitarioAgenteGlp(
							detalleRural.getTotalCostoGestionRedAgGlp().divide(new BigDecimal(detalleRural.getNumeroAgentes()),4,RoundingMode.HALF_UP));
				}else{
					detalleRural.setCostoUntitarioAgenteGlp(BigDecimal.ZERO);
				}
				//
				detalleRural.setFiseFormato14BC(fiseFormato14BC);
				//detalleRural.setFiseZonaBenef(zonaBenef);
				//
				detalleRural.setUsuarioCreacion(formulario.getUsuario());
				detalleRural.setTerminalCreacion(formulario.getTerminal());
				detalleRural.setFechaCreacion(hoy);
				detalleRural.setUsuarioActualizacion(formulario.getUsuario());
				detalleRural.setTerminalActualizacion(formulario.getTerminal());
				detalleRural.setFechaActualizacion(hoy);
				lista.add(detalleRural);
			}
			//PROVINCIA
			if( formulario.getNroValesImpP() != 0 &&
					formulario.getNroValesReptP() != 0 &&
					formulario.getNroValesEntrP() != 0 &&
					formulario.getNroValesFisP() != 0 &&
					formulario.getNroTotalAtenP() != 0
					){
				logger.info("entro a PROVINCIA");
				FiseZonaBenef zonaBenef = new FiseZonaBenef();
				zonaBenef = zonaBenefDao.obtenerFiseZonaBenefByPK(FiseConstants.ZONABENEF_PROVINCIA_COD);
				//
				FiseFormato14BD detalleProvincia = new FiseFormato14BD();
				//pk
				FiseFormato14BDPK pkDetalle = new FiseFormato14BDPK();
				pkDetalle.setCodEmpresa(fiseFormato14BC.getId().getCodEmpresa());
				pkDetalle.setAnoPresentacion(fiseFormato14BC.getId().getAnoPresentacion());
				pkDetalle.setMesPresentacion(fiseFormato14BC.getId().getMesPresentacion());
				pkDetalle.setAnoInicioVigencia(fiseFormato14BC.getId().getAnoInicioVigencia());
				pkDetalle.setAnoFinVigencia(fiseFormato14BC.getId().getAnoFinVigencia());
				pkDetalle.setIdZonaBenef(zonaBenef.getIdZonaBenef());
				pkDetalle.setEtapa(fiseFormato14BC.getId().getEtapa());
				detalleProvincia.setId(pkDetalle);
				
				//empadronamiento
				detalleProvincia.setImpresionEsquelaInvitacion(formulario.getImprEsqInvitP());
				detalleProvincia.setImpresionDeclaracionJurada(formulario.getImprDeclaJuradaP());
				detalleProvincia.setImpresionFichasVerificacion(formulario.getImprFichaVerifP());
				detalleProvincia.setRepartoEsquelaInvitacion(formulario.getRepartoEsqInvitP());
				detalleProvincia.setVerificacionInformacion(formulario.getVerifInfoP());
				detalleProvincia.setElaboracionArchivoBenef(formulario.getElabArchivoBenefP());
				detalleProvincia.setDigitacionFichaBenef(formulario.getDigitFichaBenefP());
				
				BigDecimal totalEmpadronamiento = new BigDecimal(0);
				totalEmpadronamiento = totalEmpadronamiento.add(detalleProvincia.getImpresionEsquelaInvitacion())
						.add(detalleProvincia.getImpresionDeclaracionJurada())
						.add(detalleProvincia.getImpresionFichasVerificacion())
						.add(detalleProvincia.getRepartoEsquelaInvitacion())
						.add(detalleProvincia.getVerificacionInformacion())
						.add(detalleProvincia.getElaboracionArchivoBenef())
						.add(detalleProvincia.getDigitacionFichaBenef());
				detalleProvincia.setTotalEmpadronamiento(totalEmpadronamiento);
				
				detalleProvincia.setImpresionVolantes(formulario.getImprVolantesP());
				detalleProvincia.setImpresionAfiches(formulario.getImprAfichesP());
				detalleProvincia.setRepartoFolletoPotenciaBenef(formulario.getRepFolletosP());
				detalleProvincia.setSpotPublicitarioTv(formulario.getSpotPublTvP());
				detalleProvincia.setSpotPublicitarioRadio(formulario.getSpotPublRadioP());
				
				BigDecimal totalDifusion = new BigDecimal(0);
				totalDifusion = totalDifusion.add(detalleProvincia.getImpresionVolantes())
						.add(detalleProvincia.getImpresionAfiches())
						.add(detalleProvincia.getRepartoFolletoPotenciaBenef())
						.add(detalleProvincia.getSpotPublicitarioTv())
						.add(detalleProvincia.getSpotPublicitarioRadio());
				detalleProvincia.setTotalDifusionInicioPrgFise(totalDifusion);
				
				BigDecimal sumaEmpadDifusion = new BigDecimal(0);
				sumaEmpadDifusion = sumaEmpadDifusion.add(detalleProvincia.getTotalEmpadronamiento()).add(detalleProvincia.getTotalDifusionInicioPrgFise());
				
				detalleProvincia.setNumeroBenefEmpadroMesDic(formulario.getNroBenefEmpadP());
				//costo
				if( formulario.getNroBenefEmpadP() !=0 ){
					detalleProvincia.setCostoUnitarioEmpadronamiento(
							sumaEmpadDifusion.divide(new BigDecimal(detalleProvincia.getNumeroBenefEmpadroMesDic()),4,RoundingMode.HALF_UP));
				}else{
					detalleProvincia.setCostoUnitarioEmpadronamiento(BigDecimal.ZERO);
				}
				//agentes
				detalleProvincia.setPromocionConvenioAgAutGlp(formulario.getPromConvAgentP());
				detalleProvincia.setRegistroFirmaConvAgAutGlp(formulario.getRegConvAgentP());
				detalleProvincia.setImpresionEntregaBanderola(formulario.getImpEntrBandP());
				
				BigDecimal totalAgentes = new BigDecimal(0);
				totalAgentes = totalAgentes.add(detalleProvincia.getPromocionConvenioAgAutGlp())
						.add(detalleProvincia.getRegistroFirmaConvAgAutGlp())
						.add(detalleProvincia.getImpresionEntregaBanderola());
				detalleProvincia.setTotalCostoGestionRedAgGlp(totalAgentes);
				
				detalleProvincia.setNumeroAgentes(formulario.getNroAgentP());
				//costo
				if( formulario.getNroAgentP() !=0 ){
					detalleProvincia.setCostoUntitarioAgenteGlp(
							detalleProvincia.getTotalCostoGestionRedAgGlp().divide(new BigDecimal(detalleProvincia.getNumeroAgentes()),4,RoundingMode.HALF_UP));
				}else{
					detalleProvincia.setCostoUntitarioAgenteGlp(BigDecimal.ZERO);
				}
				//
				detalleProvincia.setFiseFormato14BC(fiseFormato14BC);
				//detalleProvincia.setFiseZonaBenef(zonaBenef);
				//
				detalleProvincia.setUsuarioCreacion(formulario.getUsuario());
				detalleProvincia.setTerminalCreacion(formulario.getTerminal());
				detalleProvincia.setFechaCreacion(hoy);
				detalleProvincia.setUsuarioActualizacion(formulario.getUsuario());
				detalleProvincia.setTerminalActualizacion(formulario.getTerminal());
				detalleProvincia.setFechaActualizacion(hoy);
				lista.add(detalleProvincia);
			}
			//LIMA
			if( formulario.getNroValesImpL() != 0 &&
					formulario.getNroValesReptL() != 0 &&
					formulario.getNroValesEntrL() != 0 &&
					formulario.getNroValesFisL() != 0 &&
					formulario.getNroTotalAtenL() != 0
					){
				logger.info("entro a LIMA");
				FiseZonaBenef zonaBenef = new FiseZonaBenef();
				zonaBenef = zonaBenefDao.obtenerFiseZonaBenefByPK(FiseConstants.ZONABENEF_LIMA_COD);
				//
				FiseFormato14BD detalleLima = new FiseFormato14BD();
				//pk
				FiseFormato14BDPK pkDetalle = new FiseFormato14BDPK();
				pkDetalle.setCodEmpresa(fiseFormato14BC.getId().getCodEmpresa());
				pkDetalle.setAnoPresentacion(fiseFormato14BC.getId().getAnoPresentacion());
				pkDetalle.setMesPresentacion(fiseFormato14BC.getId().getMesPresentacion());
				pkDetalle.setAnoInicioVigencia(fiseFormato14BC.getId().getAnoInicioVigencia());
				pkDetalle.setAnoFinVigencia(fiseFormato14BC.getId().getAnoFinVigencia());
				pkDetalle.setIdZonaBenef(zonaBenef.getIdZonaBenef());
				pkDetalle.setEtapa(fiseFormato14BC.getId().getEtapa());
				detalleLima.setId(pkDetalle);
				
				//empadronamiento
				detalleLima.setImpresionEsquelaInvitacion(formulario.getImprEsqInvitL());
				detalleLima.setImpresionDeclaracionJurada(formulario.getImprDeclaJuradaL());
				detalleLima.setImpresionFichasVerificacion(formulario.getImprFichaVerifL());
				detalleLima.setRepartoEsquelaInvitacion(formulario.getRepartoEsqInvitL());
				detalleLima.setVerificacionInformacion(formulario.getVerifInfoL());
				detalleLima.setElaboracionArchivoBenef(formulario.getElabArchivoBenefL());
				detalleLima.setDigitacionFichaBenef(formulario.getDigitFichaBenefL());
				
				BigDecimal totalEmpadronamiento = new BigDecimal(0);
				totalEmpadronamiento = totalEmpadronamiento.add(detalleLima.getImpresionEsquelaInvitacion())
						.add(detalleLima.getImpresionDeclaracionJurada())
						.add(detalleLima.getImpresionFichasVerificacion())
						.add(detalleLima.getRepartoEsquelaInvitacion())
						.add(detalleLima.getVerificacionInformacion())
						.add(detalleLima.getElaboracionArchivoBenef())
						.add(detalleLima.getDigitacionFichaBenef());
				detalleLima.setTotalEmpadronamiento(totalEmpadronamiento);
				
				detalleLima.setImpresionVolantes(formulario.getImprVolantesL());
				detalleLima.setImpresionAfiches(formulario.getImprAfichesL());
				detalleLima.setRepartoFolletoPotenciaBenef(formulario.getRepFolletosL());
				detalleLima.setSpotPublicitarioTv(formulario.getSpotPublTvL());
				detalleLima.setSpotPublicitarioRadio(formulario.getSpotPublRadioL());
				
				BigDecimal totalDifusion = new BigDecimal(0);
				totalDifusion = totalDifusion.add(detalleLima.getImpresionVolantes())
						.add(detalleLima.getImpresionAfiches())
						.add(detalleLima.getRepartoFolletoPotenciaBenef())
						.add(detalleLima.getSpotPublicitarioTv())
						.add(detalleLima.getSpotPublicitarioRadio());
				detalleLima.setTotalDifusionInicioPrgFise(totalDifusion);
				
				BigDecimal sumaEmpadDifusion = new BigDecimal(0);
				sumaEmpadDifusion = sumaEmpadDifusion.add(detalleLima.getTotalEmpadronamiento()).add(detalleLima.getTotalDifusionInicioPrgFise());
				
				detalleLima.setNumeroBenefEmpadroMesDic(formulario.getNroBenefEmpadL());
				//costo
				if( formulario.getNroBenefEmpadL() !=0 ){
					detalleLima.setCostoUnitarioEmpadronamiento(
							sumaEmpadDifusion.divide(new BigDecimal(detalleLima.getNumeroBenefEmpadroMesDic()),4,RoundingMode.HALF_UP));
				}else{
					detalleLima.setCostoUnitarioEmpadronamiento(BigDecimal.ZERO);
				}
				//agentes
				detalleLima.setPromocionConvenioAgAutGlp(formulario.getPromConvAgentL());
				detalleLima.setRegistroFirmaConvAgAutGlp(formulario.getRegConvAgentL());
				detalleLima.setImpresionEntregaBanderola(formulario.getImpEntrBandL());
				
				BigDecimal totalAgentes = new BigDecimal(0);
				totalAgentes = totalAgentes.add(detalleLima.getPromocionConvenioAgAutGlp())
						.add(detalleLima.getRegistroFirmaConvAgAutGlp())
						.add(detalleLima.getImpresionEntregaBanderola());
				detalleLima.setTotalCostoGestionRedAgGlp(totalAgentes);
				
				detalleLima.setNumeroAgentes(formulario.getNroAgentL());
				//costo
				if( formulario.getNroAgentL() !=0 ){
					detalleLima.setCostoUntitarioAgenteGlp(
							detalleLima.getTotalCostoGestionRedAgGlp().divide(new BigDecimal(detalleLima.getNumeroAgentes()),4,RoundingMode.HALF_UP));
				}else{
					detalleLima.setCostoUntitarioAgenteGlp(BigDecimal.ZERO);
				}
				//
				detalleLima.setFiseFormato14BC(fiseFormato14BC);
				//detalleLima.setFiseZonaBenef(zonaBenef);
				//
				detalleLima.setUsuarioCreacion(formulario.getUsuario());
				detalleLima.setTerminalCreacion(formulario.getTerminal());
				detalleLima.setFechaCreacion(hoy);
				detalleLima.setUsuarioActualizacion(formulario.getUsuario());
				detalleLima.setTerminalActualizacion(formulario.getTerminal());
				detalleLima.setFechaActualizacion(hoy);
				lista.add(detalleLima);
			}
			*/
			fiseFormato14BC.setUsuarioActualizacion(formulario.getUsuario());
			fiseFormato14BC.setTerminalActualizacion(formulario.getTerminal());
			fiseFormato14BC.setFechaActualizacion(hoy);
			fiseFormato14BC.setUsuarioCreacion(formulario.getUsuario());
			fiseFormato14BC.setTerminalCreacion(formulario.getTerminal());
			fiseFormato14BC.setFechaCreacion(hoy);
			
			logger.info("aca se va  a guardar"+fiseFormato14BC);
			boolean existe = false;
			existe = formato14BCDao.existeFormato14BC(fiseFormato14BC);
			if(existe){
				throw new Exception("Ya existe un registro con la misma clave.");
			}else{
				formato14BCDao.registrarFormato14BC(fiseFormato14BC);
			}
			//add
			for (FiseFormato14BD detalle : lista) {
				formato14BDDao.registrarFormato14BD(detalle);
			}
			if( lista != null && lista.size()>0 ){
				fiseFormato14BC.setFiseFormato14BDs(lista);
			}
			dto = fiseFormato14BC;
			
		} 	catch (Exception e) {
			logger.error("--error"+e.getMessage());
			e.printStackTrace();
			throw e;
		}
		return dto;
	}
	
	@Override
	@Transactional(propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
	public FiseFormato14BC modificarFormato14BC(Formato14BCBean formulario, FiseFormato14BC fiseFormato14BC) throws Exception {
		
		FiseFormato14BC dto = null;
		
		try {
			
			Date hoy = FechaUtil.obtenerFechaActual();
	
			List<FiseFormato14BD> lista = new ArrayList<FiseFormato14BD>();
			
			FiseFormato14BD detalleRural = new FiseFormato14BD();
			FiseFormato14BD detalleProvincia = new FiseFormato14BD();
			FiseFormato14BD detalleLima = new FiseFormato14BD();
			if( fiseFormato14BC.getFiseFormato14BDs()!=null ){
				for (FiseFormato14BD detalle : fiseFormato14BC.getFiseFormato14BDs()) {
					if( detalle.getId().getIdZonaBenef()==FiseConstants.ZONABENEF_RURAL_COD ){
						detalleRural = detalle;
					}else if( detalle.getId().getIdZonaBenef()==FiseConstants.ZONABENEF_PROVINCIA_COD ){
						detalleProvincia = detalle;
					} else if( detalle.getId().getIdZonaBenef()==FiseConstants.ZONABENEF_LIMA_COD ){
						detalleLima = detalle;
					}
				}
			}
			
			//RURAL
			/*if( formulario.getNroValesImpR() != 0 &&
					formulario.getNroValesReptR() != 0 &&
					formulario.getNroValesEntrR() != 0 &&
					formulario.getNroValesFisR() != 0 &&
					formulario.getNroTotalAtenR() != 0
					){
				logger.info("se modificara RURAL");
				//empadronamiento
				detalleRural.setImpresionEsquelaInvitacion(formulario.getImprEsqInvitR());
				detalleRural.setImpresionDeclaracionJurada(formulario.getImprDeclaJuradaR());
				detalleRural.setImpresionFichasVerificacion(formulario.getImprFichaVerifR());
				detalleRural.setRepartoEsquelaInvitacion(formulario.getRepartoEsqInvitR());
				detalleRural.setVerificacionInformacion(formulario.getVerifInfoR());
				detalleRural.setElaboracionArchivoBenef(formulario.getElabArchivoBenefR());
				detalleRural.setDigitacionFichaBenef(formulario.getDigitFichaBenefR());
				
				BigDecimal totalEmpadronamiento = new BigDecimal(0);
				totalEmpadronamiento = totalEmpadronamiento.add(detalleRural.getImpresionEsquelaInvitacion())
						.add(detalleRural.getImpresionDeclaracionJurada())
						.add(detalleRural.getImpresionFichasVerificacion())
						.add(detalleRural.getRepartoEsquelaInvitacion())
						.add(detalleRural.getVerificacionInformacion())
						.add(detalleRural.getElaboracionArchivoBenef())
						.add(detalleRural.getDigitacionFichaBenef());
				detalleRural.setTotalEmpadronamiento(totalEmpadronamiento);
				
				detalleRural.setImpresionVolantes(formulario.getImprVolantesR());
				detalleRural.setImpresionAfiches(formulario.getImprAfichesR());
				detalleRural.setRepartoFolletoPotenciaBenef(formulario.getRepFolletosR());
				detalleRural.setSpotPublicitarioTv(formulario.getSpotPublTvR());
				detalleRural.setSpotPublicitarioRadio(formulario.getSpotPublRadioR());
				
				BigDecimal totalDifusion = new BigDecimal(0);
				totalDifusion = totalDifusion.add(detalleRural.getImpresionVolantes())
						.add(detalleRural.getImpresionAfiches())
						.add(detalleRural.getRepartoFolletoPotenciaBenef())
						.add(detalleRural.getSpotPublicitarioTv())
						.add(detalleRural.getSpotPublicitarioRadio());
				detalleRural.setTotalDifusionInicioPrgFise(totalDifusion);
				
				BigDecimal sumaEmpadDifusion = new BigDecimal(0);
				sumaEmpadDifusion = sumaEmpadDifusion.add(detalleRural.getTotalEmpadronamiento()).add(detalleRural.getTotalDifusionInicioPrgFise());
				
				detalleRural.setNumeroBenefEmpadroMesDic(formulario.getNroBenefEmpadR());
				//costo
				if( formulario.getNroBenefEmpadR() !=0 ){
					detalleRural.setCostoUnitarioEmpadronamiento(
							sumaEmpadDifusion.divide(new BigDecimal(detalleRural.getNumeroBenefEmpadroMesDic()),4,RoundingMode.HALF_UP));
				}else{
					detalleRural.setCostoUnitarioEmpadronamiento(BigDecimal.ZERO);
				}
				//agentes
				detalleRural.setPromocionConvenioAgAutGlp(formulario.getPromConvAgentR());
				detalleRural.setRegistroFirmaConvAgAutGlp(formulario.getRegConvAgentR());
				detalleRural.setImpresionEntregaBanderola(formulario.getImpEntrBandR());
				
				BigDecimal totalAgentes = new BigDecimal(0);
				totalAgentes = totalAgentes.add(detalleRural.getPromocionConvenioAgAutGlp())
						.add(detalleRural.getRegistroFirmaConvAgAutGlp())
						.add(detalleRural.getImpresionEntregaBanderola());
				detalleRural.setTotalCostoGestionRedAgGlp(totalAgentes);
				
				detalleRural.setNumeroAgentes(formulario.getNroAgentR());
				//costo
				if( formulario.getNroAgentR() !=0 ){
					detalleRural.setCostoUntitarioAgenteGlp(
							detalleRural.getTotalCostoGestionRedAgGlp().divide(new BigDecimal(detalleRural.getNumeroAgentes()),4,RoundingMode.HALF_UP));
				}else{
					detalleRural.setCostoUntitarioAgenteGlp(BigDecimal.ZERO);
				}
				//
				//--detalleRural.setFiseFormato14BC(fiseFormato14BC);
				//
				detalleRural.setUsuarioActualizacion(formulario.getUsuario());
				detalleRural.setTerminalActualizacion(formulario.getTerminal());
				detalleRural.setFechaActualizacion(hoy);
				lista.add(detalleRural);
			}
			//PROVINCIA
			if( formulario.getNroValesImpP() != 0 &&
					formulario.getNroValesReptP() != 0 &&
					formulario.getNroValesEntrP() != 0 &&
					formulario.getNroValesFisP() != 0 &&
					formulario.getNroTotalAtenP() != 0
					){
				logger.info("se modificara PROVINCIA");
				//empadronamiento
				detalleProvincia.setImpresionEsquelaInvitacion(formulario.getImprEsqInvitP());
				detalleProvincia.setImpresionDeclaracionJurada(formulario.getImprDeclaJuradaP());
				detalleProvincia.setImpresionFichasVerificacion(formulario.getImprFichaVerifP());
				detalleProvincia.setRepartoEsquelaInvitacion(formulario.getRepartoEsqInvitP());
				detalleProvincia.setVerificacionInformacion(formulario.getVerifInfoP());
				detalleProvincia.setElaboracionArchivoBenef(formulario.getElabArchivoBenefP());
				detalleProvincia.setDigitacionFichaBenef(formulario.getDigitFichaBenefP());
				
				BigDecimal totalEmpadronamiento = new BigDecimal(0);
				totalEmpadronamiento = totalEmpadronamiento.add(detalleProvincia.getImpresionEsquelaInvitacion())
						.add(detalleProvincia.getImpresionDeclaracionJurada())
						.add(detalleProvincia.getImpresionFichasVerificacion())
						.add(detalleProvincia.getRepartoEsquelaInvitacion())
						.add(detalleProvincia.getVerificacionInformacion())
						.add(detalleProvincia.getElaboracionArchivoBenef())
						.add(detalleProvincia.getDigitacionFichaBenef());
				detalleProvincia.setTotalEmpadronamiento(totalEmpadronamiento);
				
				detalleProvincia.setImpresionVolantes(formulario.getImprVolantesP());
				detalleProvincia.setImpresionAfiches(formulario.getImprAfichesP());
				detalleProvincia.setRepartoFolletoPotenciaBenef(formulario.getRepFolletosP());
				detalleProvincia.setSpotPublicitarioTv(formulario.getSpotPublTvP());
				detalleProvincia.setSpotPublicitarioRadio(formulario.getSpotPublRadioP());
				
				BigDecimal totalDifusion = new BigDecimal(0);
				totalDifusion = totalDifusion.add(detalleProvincia.getImpresionVolantes())
						.add(detalleProvincia.getImpresionAfiches())
						.add(detalleProvincia.getRepartoFolletoPotenciaBenef())
						.add(detalleProvincia.getSpotPublicitarioTv())
						.add(detalleProvincia.getSpotPublicitarioRadio());
				detalleProvincia.setTotalDifusionInicioPrgFise(totalDifusion);
				
				BigDecimal sumaEmpadDifusion = new BigDecimal(0);
				sumaEmpadDifusion = sumaEmpadDifusion.add(detalleRural.getTotalEmpadronamiento()).add(detalleRural.getTotalDifusionInicioPrgFise());
				
				detalleProvincia.setNumeroBenefEmpadroMesDic(formulario.getNroBenefEmpadP());
				//costo
				if( formulario.getNroBenefEmpadP() !=0 ){
					detalleProvincia.setCostoUnitarioEmpadronamiento(
							sumaEmpadDifusion.divide(new BigDecimal(detalleProvincia.getNumeroBenefEmpadroMesDic()),4,RoundingMode.HALF_UP));
				}else{
					detalleProvincia.setCostoUnitarioEmpadronamiento(BigDecimal.ZERO);
				}
				//agentes
				detalleProvincia.setPromocionConvenioAgAutGlp(formulario.getPromConvAgentP());
				detalleProvincia.setRegistroFirmaConvAgAutGlp(formulario.getRegConvAgentP());
				detalleProvincia.setImpresionEntregaBanderola(formulario.getImpEntrBandP());
				
				BigDecimal totalAgentes = new BigDecimal(0);
				totalAgentes = totalAgentes.add(detalleProvincia.getPromocionConvenioAgAutGlp())
						.add(detalleProvincia.getRegistroFirmaConvAgAutGlp())
						.add(detalleProvincia.getImpresionEntregaBanderola());
				detalleProvincia.setTotalCostoGestionRedAgGlp(totalAgentes);
				
				detalleProvincia.setNumeroAgentes(formulario.getNroAgentP());
				//costo
				if( formulario.getNroAgentP() !=0 ){
					detalleProvincia.setCostoUntitarioAgenteGlp(
							detalleProvincia.getTotalCostoGestionRedAgGlp().divide(new BigDecimal(detalleProvincia.getNumeroAgentes()),4,RoundingMode.HALF_UP));
				}else{
					detalleProvincia.setCostoUntitarioAgenteGlp(BigDecimal.ZERO);
				}
				//
				//--detalleProvincia.setFiseFormato14BC(fiseFormato14BC);
				//
				detalleProvincia.setUsuarioActualizacion(formulario.getUsuario());
				detalleProvincia.setTerminalActualizacion(formulario.getTerminal());
				detalleProvincia.setFechaActualizacion(hoy);
				lista.add(detalleProvincia);
			}
			//LIMA
			if( formulario.getNroValesImpL() != 0 &&
					formulario.getNroValesReptL() != 0 &&
					formulario.getNroValesEntrL() != 0 &&
					formulario.getNroValesFisL() != 0 &&
					formulario.getNroTotalAtenL() != 0
					){
				logger.info("se modificara LIMA");
				//empadronamiento
				detalleLima.setImpresionEsquelaInvitacion(formulario.getImprEsqInvitL());
				detalleLima.setImpresionDeclaracionJurada(formulario.getImprDeclaJuradaL());
				detalleLima.setImpresionFichasVerificacion(formulario.getImprFichaVerifL());
				detalleLima.setRepartoEsquelaInvitacion(formulario.getRepartoEsqInvitL());
				detalleLima.setVerificacionInformacion(formulario.getVerifInfoL());
				detalleLima.setElaboracionArchivoBenef(formulario.getElabArchivoBenefL());
				detalleLima.setDigitacionFichaBenef(formulario.getDigitFichaBenefL());
				
				BigDecimal totalEmpadronamiento = new BigDecimal(0);
				totalEmpadronamiento = totalEmpadronamiento.add(detalleLima.getImpresionEsquelaInvitacion())
						.add(detalleLima.getImpresionDeclaracionJurada())
						.add(detalleLima.getImpresionFichasVerificacion())
						.add(detalleLima.getRepartoEsquelaInvitacion())
						.add(detalleLima.getVerificacionInformacion())
						.add(detalleLima.getElaboracionArchivoBenef())
						.add(detalleLima.getDigitacionFichaBenef());
				detalleLima.setTotalEmpadronamiento(totalEmpadronamiento);
				
				detalleLima.setImpresionVolantes(formulario.getImprVolantesL());
				detalleLima.setImpresionAfiches(formulario.getImprAfichesL());
				detalleLima.setRepartoFolletoPotenciaBenef(formulario.getRepFolletosL());
				detalleLima.setSpotPublicitarioTv(formulario.getSpotPublTvL());
				detalleLima.setSpotPublicitarioRadio(formulario.getSpotPublRadioL());
				
				BigDecimal totalDifusion = new BigDecimal(0);
				totalDifusion = totalDifusion.add(detalleLima.getImpresionVolantes())
						.add(detalleLima.getImpresionAfiches())
						.add(detalleLima.getRepartoFolletoPotenciaBenef())
						.add(detalleLima.getSpotPublicitarioTv())
						.add(detalleLima.getSpotPublicitarioRadio());
				detalleLima.setTotalDifusionInicioPrgFise(totalDifusion);
				
				BigDecimal sumaEmpadDifusion = new BigDecimal(0);
				sumaEmpadDifusion = sumaEmpadDifusion.add(detalleRural.getTotalEmpadronamiento()).add(detalleRural.getTotalDifusionInicioPrgFise());
				
				detalleLima.setNumeroBenefEmpadroMesDic(formulario.getNroBenefEmpadL());
				//costo
				if( formulario.getNroBenefEmpadL() !=0 ){
					detalleLima.setCostoUnitarioEmpadronamiento(
							sumaEmpadDifusion.divide(new BigDecimal(detalleLima.getNumeroBenefEmpadroMesDic()),4,RoundingMode.HALF_UP));
				}else{
					detalleLima.setCostoUnitarioEmpadronamiento(BigDecimal.ZERO);
				}
				//agentes
				detalleLima.setPromocionConvenioAgAutGlp(formulario.getPromConvAgentL());
				detalleLima.setRegistroFirmaConvAgAutGlp(formulario.getRegConvAgentL());
				detalleLima.setImpresionEntregaBanderola(formulario.getImpEntrBandL());
				
				BigDecimal totalAgentes = new BigDecimal(0);
				totalAgentes = totalAgentes.add(detalleLima.getPromocionConvenioAgAutGlp())
						.add(detalleLima.getRegistroFirmaConvAgAutGlp())
						.add(detalleLima.getImpresionEntregaBanderola());
				detalleLima.setTotalCostoGestionRedAgGlp(totalAgentes);
				
				detalleLima.setNumeroAgentes(formulario.getNroAgentL());
				//costo
				if( formulario.getNroAgentL() !=0 ){
					detalleLima.setCostoUntitarioAgenteGlp(
							detalleLima.getTotalCostoGestionRedAgGlp().divide(new BigDecimal(detalleLima.getNumeroAgentes()),4,RoundingMode.HALF_UP));
				}else{
					detalleLima.setCostoUntitarioAgenteGlp(BigDecimal.ZERO);
				}
				//
				//--detalleLima.setFiseFormato14BC(fiseFormato14BC);
				//
				detalleLima.setUsuarioActualizacion(formulario.getUsuario());
				detalleLima.setTerminalActualizacion(formulario.getTerminal());
				detalleLima.setFechaActualizacion(hoy);
				lista.add(detalleLima);
			}
		
			fiseFormato14BC.setUsuarioActualizacion(formulario.getUsuario());
			fiseFormato14BC.setTerminalActualizacion(formulario.getTerminal());
			fiseFormato14BC.setFechaActualizacion(hoy);
		
			if( FiseConstants.TIPOARCHIVO_XLS.equals(formulario.getTipoArchivo()) ){
				fiseFormato14BC.setArchivoExcel(formulario.getNombreArchivo());
			}else if( FiseConstants.TIPOARCHIVO_TXT.equals(formulario.getTipoArchivo()) ){
				fiseFormato14BC.setArchivoTexto(formulario.getNombreArchivo());
			}
			
			formato14BCDao.modificarFormato14BC(fiseFormato14BC);
			//add
			for (FiseFormato14BD detalle : lista) {
				formato14BDDao.modificarFormato14BD(detalle);
			}
			dto= fiseFormato14BC;*/
			
		}	catch (Exception e) {
			logger.error("--error"+e.getMessage());
			e.printStackTrace();
			throw e;
		}
		//
		return dto;

	}
	
	@Override
	@Transactional(propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
	public void eliminarFormato14BC(FiseFormato14BC fiseFormato14BC) {
		//eliminar antes los detalles creados para esa cabecera
		List<FiseFormato14BD> lista = null;
		lista = formato14BDDao.listarFormato14BDByFormato14BC(fiseFormato14BC);
		//por el momento no se esta borrando las dependencias hacias el detalle de observaciones
		//luego aumentar esta dependencia
		for (FiseFormato14BD detalle : lista) {
			List<FiseFormato14BDOb> listaObservacion = formato14BObsDao.listarFormato14BDObByFormato14BD(detalle);
			for (FiseFormato14BDOb observacion : listaObservacion) {
				formato14BObsDao.eliminarFormato14BDOb(observacion);
			}
			formato14BDDao.eliminarFormato14BD(detalle);
		}
		formato14BCDao.eliminarFormato14BC(fiseFormato14BC);
	}
	
	//reporte
	@Override
	public Formato14BCBean estructurarFormato14BBeanByFiseFormato14BC(FiseFormato14BC formato){
		
		Formato14BCBean formato14BBean = new Formato14BCBean();
		
		formato14BBean.setAnioPresent(formato.getId().getAnoPresentacion());
		formato14BBean.setAnioInicioVigencia(formato.getId().getAnoInicioVigencia());
		formato14BBean.setAnioFinVigencia(formato.getId().getAnoFinVigencia());
		
    	/*for (FiseFormato14BD detalle : formato.getFiseFormato14BDs()) {
			if( FiseConstants.ZONABENEF_RURAL_COD == detalle.getId().getIdZonaBenef() ){
				formato14BBean.setImprEsqInvitR(detalle.getImpresionEsquelaInvitacion());
				formato14BBean.setImprDeclaJuradaR(detalle.getImpresionDeclaracionJurada());
				formato14BBean.setImprFichaVerifR(detalle.getImpresionFichasVerificacion());
				formato14BBean.setRepartoEsqInvitR(detalle.getRepartoEsquelaInvitacion());
				formato14BBean.setVerifInfoR(detalle.getVerificacionInformacion());
				formato14BBean.setElabArchivoBenefR(detalle.getElaboracionArchivoBenef());
				formato14BBean.setDigitFichaBenefR(detalle.getDigitacionFichaBenef());
				// total  de empadronamiento
				formato14BBean.setTotalEmpadR(detalle.getTotalEmpadronamiento());
				
				formato14BBean.setImprVolantesR(detalle.getImpresionVolantes());
				formato14BBean.setImprAfichesR(detalle.getImpresionAfiches());
				formato14BBean.setRepFolletosR(detalle.getRepartoFolletoPotenciaBenef());
				formato14BBean.setSpotPublTvR(detalle.getSpotPublicitarioTv());
				formato14BBean.setSpotPublRadioR(detalle.getSpotPublicitarioRadio());
				// Suma de total de difusion
				formato14BBean.setTotalDifIniProgR(detalle.getTotalDifusionInicioPrgFise());
				
				formato14BBean.setNroBenefEmpadR(detalle.getNumeroBenefEmpadroMesDic());
				//suma del empadronamiento + difusion
				BigDecimal sumTotalEmpadDifusionR = new BigDecimal(0);
				sumTotalEmpadDifusionR=sumTotalEmpadDifusionR.add(detalle.getTotalEmpadronamiento())
							.add(detalle.getTotalDifusionInicioPrgFise());
				formato14BBean.setSumEmpadDifusionR(sumTotalEmpadDifusionR);
				// Costo unitario por empadronamiento
				formato14BBean.setCostoUnitEmpadR(detalle.getCostoUnitarioEmpadronamiento());
				
				formato14BBean.setPromConvAgentR(detalle.getPromocionConvenioAgAutGlp());
				formato14BBean.setRegConvAgentR(detalle.getRegistroFirmaConvAgAutGlp());
				formato14BBean.setImpEntrBandR(detalle.getImpresionEntregaBanderola());
				formato14BBean.setNroAgentR(detalle.getNumeroAgentes());
				//total agentes
				formato14BBean.setTotalCostoAgentR(detalle.getTotalCostoGestionRedAgGlp());
				// Costo unitario por Agente
				formato14BBean.setCostoUnitAgentR(detalle.getCostoUntitarioAgenteGlp());
				
			}else if( FiseConstants.ZONABENEF_PROVINCIA_COD == detalle.getId().getIdZonaBenef() ){
				formato14BBean.setImprEsqInvitP(detalle.getImpresionEsquelaInvitacion());
				formato14BBean.setImprDeclaJuradaP(detalle.getImpresionDeclaracionJurada());
				formato14BBean.setImprFichaVerifP(detalle.getImpresionFichasVerificacion());
				formato14BBean.setRepartoEsqInvitP(detalle.getRepartoEsquelaInvitacion());
				formato14BBean.setVerifInfoP(detalle.getVerificacionInformacion());
				formato14BBean.setElabArchivoBenefP(detalle.getElaboracionArchivoBenef());
				formato14BBean.setDigitFichaBenefP(detalle.getDigitacionFichaBenef());
				// total  de empadronamiento
				formato14BBean.setTotalEmpadP(detalle.getTotalEmpadronamiento());
				
				formato14BBean.setImprVolantesP(detalle.getImpresionVolantes());
				formato14BBean.setImprAfichesP(detalle.getImpresionAfiches());
				formato14BBean.setRepFolletosP(detalle.getRepartoFolletoPotenciaBenef());
				formato14BBean.setSpotPublTvP(detalle.getSpotPublicitarioTv());
				formato14BBean.setSpotPublRadioP(detalle.getSpotPublicitarioRadio());
				// Suma de total de difusion
				formato14BBean.setTotalDifIniProgP(detalle.getTotalDifusionInicioPrgFise());
				
				formato14BBean.setNroBenefEmpadP(detalle.getNumeroBenefEmpadroMesDic());
				//suma del empadronamiento + difusion
				BigDecimal sumTotalEmpadDifusionP = new BigDecimal(0);
				sumTotalEmpadDifusionP=sumTotalEmpadDifusionP.add(detalle.getTotalEmpadronamiento())
							.add(detalle.getTotalDifusionInicioPrgFise());
				formato14BBean.setSumEmpadDifusionP(sumTotalEmpadDifusionP);
				// Costo unitario por empadronamiento
				formato14BBean.setCostoUnitEmpadP(detalle.getCostoUnitarioEmpadronamiento());
				
				formato14BBean.setPromConvAgentP(detalle.getPromocionConvenioAgAutGlp());
				formato14BBean.setRegConvAgentP(detalle.getRegistroFirmaConvAgAutGlp());
				formato14BBean.setImpEntrBandP(detalle.getImpresionEntregaBanderola());
				formato14BBean.setNroAgentP(detalle.getNumeroAgentes());
				//total agentes
				formato14BBean.setTotalCostoAgentP(detalle.getTotalCostoGestionRedAgGlp());
				// Costo unitario por Agente
				formato14BBean.setCostoUnitAgentP(detalle.getCostoUntitarioAgenteGlp());
				
			}else if( FiseConstants.ZONABENEF_LIMA_COD == detalle.getId().getIdZonaBenef() ){
				formato14BBean.setImprEsqInvitL(detalle.getImpresionEsquelaInvitacion());
				formato14BBean.setImprDeclaJuradaL(detalle.getImpresionDeclaracionJurada());
				formato14BBean.setImprFichaVerifL(detalle.getImpresionFichasVerificacion());
				formato14BBean.setRepartoEsqInvitL(detalle.getRepartoEsquelaInvitacion());
				formato14BBean.setVerifInfoL(detalle.getVerificacionInformacion());
				formato14BBean.setElabArchivoBenefL(detalle.getElaboracionArchivoBenef());
				formato14BBean.setDigitFichaBenefL(detalle.getDigitacionFichaBenef());
				// total  de empadronamiento
				formato14BBean.setTotalEmpadL(detalle.getTotalEmpadronamiento());
				
				formato14BBean.setImprVolantesL(detalle.getImpresionVolantes());
				formato14BBean.setImprAfichesL(detalle.getImpresionAfiches());
				formato14BBean.setRepFolletosL(detalle.getRepartoFolletoPotenciaBenef());
				formato14BBean.setSpotPublTvL(detalle.getSpotPublicitarioTv());
				formato14BBean.setSpotPublRadioL(detalle.getSpotPublicitarioRadio());
				// Suma de total de difusion
				formato14BBean.setTotalDifIniProgL(detalle.getTotalDifusionInicioPrgFise());
				
				formato14BBean.setNroBenefEmpadL(detalle.getNumeroBenefEmpadroMesDic());
				//suma del empadronamiento + difusion
				BigDecimal sumTotalEmpadDifusionL = new BigDecimal(0);
				sumTotalEmpadDifusionL=sumTotalEmpadDifusionL.add(detalle.getTotalEmpadronamiento())
							.add(detalle.getTotalDifusionInicioPrgFise());
				formato14BBean.setSumEmpadDifusionL(sumTotalEmpadDifusionL);
				// Costo unitario por empadronamiento
				formato14BBean.setCostoUnitEmpadL(detalle.getCostoUnitarioEmpadronamiento());
				
				formato14BBean.setPromConvAgentL(detalle.getPromocionConvenioAgAutGlp());
				formato14BBean.setRegConvAgentL(detalle.getRegistroFirmaConvAgAutGlp());
				formato14BBean.setImpEntrBandL(detalle.getImpresionEntregaBanderola());
				formato14BBean.setNroAgentL(detalle.getNumeroAgentes());
				//total agentes
				formato14BBean.setTotalCostoAgentL(detalle.getTotalCostoGestionRedAgGlp());
				// Costo unitario por Agente
				formato14BBean.setCostoUnitAgentL(detalle.getCostoUntitarioAgenteGlp());
			}
		}*/

		return formato14BBean;	
	}
 
 	@Override
	public HashMap<String, Object> mapearParametrosFormato14B(Formato14BCBean formato14BBean){
		
		HashMap<String, Object> mapJRParams = new HashMap<String, Object>();
		
		/*mapJRParams.put(FiseConstants.PARAM_DESC_EMPRESA_F14A, formato14BBean.getDescEmpresa());
		mapJRParams.put(FiseConstants.PARAM_ANO_PRES_F14A, formato14BBean.getAnioPresent());
		mapJRParams.put(FiseConstants.PARAM_DESC_MES_PRES_F14A, formato14BBean.getDescMesPresentacion());
		
		////----RURAL
		mapJRParams.put(FiseConstants.PARAM_SUM_EMP_SUMI_R_F14A,formato14BBean.getSumEmpadDifusionR());
		mapJRParams.put(FiseConstants.PARAM_IMP_ESQ_INV_R_F14A, formato14BBean.getImprEsqInvitR());
		mapJRParams.put(FiseConstants.PARAM_IMP_DECJUR_R_F14A, formato14BBean.getImprDeclaJuradaR());
		mapJRParams.put(FiseConstants.PARAM_IMP_FCH_VRFC_R_F14A, formato14BBean.getImprFichaVerifR());
		mapJRParams.put(FiseConstants.PARAM_RPT_EQL_INV_R_F14A, formato14BBean.getRepartoEsqInvitR());
		mapJRParams.put(FiseConstants.PARAM_VRF_INFOR_R_F14A, formato14BBean.getVerifInfoR());
		mapJRParams.put(FiseConstants.PARAM_ELBRCION_ACHVOS_R_F14A, formato14BBean.getElabArchivoBenefR());
		mapJRParams.put(FiseConstants.PARAM_DGTCION_FCHAS_R_F14A, formato14BBean.getDigitFichaBenefR());
		mapJRParams.put(FiseConstants.PARAM_SUM_EMPADR_R_F14A, formato14BBean.getTotalEmpadR());
		mapJRParams.put(FiseConstants.PARAM_IMP_VOL_R_F14A, formato14BBean.getImprVolantesR());
		mapJRParams.put(FiseConstants.PARAM_IMP_AFCHES_R_F14A, formato14BBean.getImprAfichesR());
		mapJRParams.put(FiseConstants.PARAM_RPTO_FLLETOS_R_F14A, formato14BBean.getRepFolletosR());
		mapJRParams.put(FiseConstants.PARAM_SPORT_TV_R_F14A, formato14BBean.getSpotPublTvR());
		mapJRParams.put(FiseConstants.PARAM_SPORT_RADIO_R_F14A, formato14BBean.getSpotPublRadioR());
		mapJRParams.put(FiseConstants.PARAM_SUMA_DIFUSION_R_F14A, formato14BBean.getTotalDifIniProgR());
		mapJRParams.put(FiseConstants.PARAM_NRO_BNFCIARIOS_R_F14A, formato14BBean.getNroBenefEmpadR());
		mapJRParams.put(FiseConstants.PARAM_CSTO_UNIT_R_F14A, formato14BBean.getCostoUnitEmpadR());
		
		mapJRParams.put(FiseConstants.PARAM_SUMCSTO_GSTION_R_F14A, formato14BBean.getTotalCostoAgentR());
		mapJRParams.put(FiseConstants.PARAM_PROM_AGENTES_R_F14A, formato14BBean.getPromConvAgentR());
		mapJRParams.put(FiseConstants.PARAM_RGTROS_AGENTES_R_F14A, formato14BBean.getRegConvAgentR());
		mapJRParams.put(FiseConstants.PARAM_IMP_BANDER_R_F14A, formato14BBean.getImpEntrBandR());
		mapJRParams.put(FiseConstants.PARAM_NUM_AGENTES_R_F14A, formato14BBean.getNroAgentR());
		mapJRParams.put(FiseConstants.PARAM_CSTOUNIT_AGENTES_R_F14A, formato14BBean.getCostoUnitAgentR());
		
		////----PROVINCIAS
		mapJRParams.put(FiseConstants.PARAM_SUM_EMP_SUMI_P_F14A,formato14BBean.getSumEmpadDifusionP());
		mapJRParams.put(FiseConstants.PARAM_IMP_ESQ_INV_P_F14A, formato14BBean.getImprEsqInvitP());
		mapJRParams.put(FiseConstants.PARAM_IMP_DECJUR_P_F14A, formato14BBean.getImprDeclaJuradaP());
		mapJRParams.put(FiseConstants.PARAM_IMP_FCH_VRFC_P_F14A, formato14BBean.getImprFichaVerifP());
		mapJRParams.put(FiseConstants.PARAM_RPT_EQL_INV_P_F14A, formato14BBean.getRepartoEsqInvitP());
		mapJRParams.put(FiseConstants.PARAM_VRF_INFOR_P_F14A, formato14BBean.getVerifInfoP());
		mapJRParams.put(FiseConstants.PARAM_ELBRCION_ACHVOS_P_F14A, formato14BBean.getElabArchivoBenefP());
		mapJRParams.put(FiseConstants.PARAM_DGTCION_FCHAS_P_F14A, formato14BBean.getDigitFichaBenefP());
		mapJRParams.put(FiseConstants.PARAM_SUM_EMPADR_P_F14A, formato14BBean.getTotalEmpadP());
		mapJRParams.put(FiseConstants.PARAM_IMP_VOL_P_F14A, formato14BBean.getImprVolantesP());
		mapJRParams.put(FiseConstants.PARAM_IMP_AFCHES_P_F14A, formato14BBean.getImprAfichesP());
		mapJRParams.put(FiseConstants.PARAM_RPTO_FLLETOS_P_F14A, formato14BBean.getRepFolletosP());
		mapJRParams.put(FiseConstants.PARAM_SPORT_TV_P_F14A, formato14BBean.getSpotPublTvP());
		mapJRParams.put(FiseConstants.PARAM_SPORT_RADIO_P_F14A, formato14BBean.getSpotPublRadioP());
		mapJRParams.put(FiseConstants.PARAM_SUMA_DIFUSION_P_F14A, formato14BBean.getTotalDifIniProgP());
		mapJRParams.put(FiseConstants.PARAM_NRO_BNFCIARIOS_P_F14A, formato14BBean.getNroBenefEmpadP());
		mapJRParams.put(FiseConstants.PARAM_CSTO_UNIT_P_F14A, formato14BBean.getCostoUnitEmpadP());
		
		mapJRParams.put(FiseConstants.PARAM_SUMCSTO_GSTION_P_F14A, formato14BBean.getTotalCostoAgentP());
		mapJRParams.put(FiseConstants.PARAM_PROM_AGENTES_P_F14A, formato14BBean.getPromConvAgentP());
		mapJRParams.put(FiseConstants.PARAM_RGTROS_AGENTES_P_F14A, formato14BBean.getRegConvAgentP());
		mapJRParams.put(FiseConstants.PARAM_IMP_BANDER_P_F14A, formato14BBean.getImpEntrBandP());
		mapJRParams.put(FiseConstants.PARAM_NUM_AGENTES_P_F14A, formato14BBean.getNroAgentP());
		mapJRParams.put(FiseConstants.PARAM_CSTOUNIT_AGENTES_P_F14A, formato14BBean.getCostoUnitAgentP());
		
		////-----LIMA
		
		mapJRParams.put(FiseConstants.PARAM_SUM_EMP_SUMI_L_F14A,formato14BBean.getSumEmpadDifusionL());
		mapJRParams.put(FiseConstants.PARAM_IMP_ESQ_INV_L_F14A, formato14BBean.getImprEsqInvitL());
		mapJRParams.put(FiseConstants.PARAM_IMP_DECJUR_L_F14A, formato14BBean.getImprDeclaJuradaL());
		mapJRParams.put(FiseConstants.PARAM_IMP_FCH_VRFC_L_F14A, formato14BBean.getImprFichaVerifL());
		mapJRParams.put(FiseConstants.PARAM_RPT_EQL_INV_L_F14A, formato14BBean.getRepartoEsqInvitL());
		mapJRParams.put(FiseConstants.PARAM_VRF_INFOR_L_F14A, formato14BBean.getVerifInfoL());
		mapJRParams.put(FiseConstants.PARAM_ELBRCION_ACHVOS_L_F14A, formato14BBean.getElabArchivoBenefL());
		mapJRParams.put(FiseConstants.PARAM_DGTCION_FCHAS_L_F14A, formato14BBean.getDigitFichaBenefL());
		mapJRParams.put(FiseConstants.PARAM_SUM_EMPADR_L_F14A, formato14BBean.getTotalEmpadL());
		mapJRParams.put(FiseConstants.PARAM_IMP_VOL_L_F14A, formato14BBean.getImprVolantesL());
		mapJRParams.put(FiseConstants.PARAM_IMP_AFCHES_L_F14A, formato14BBean.getImprAfichesL());
		mapJRParams.put(FiseConstants.PARAM_RPTO_FLLETOS_L_F14A, formato14BBean.getRepFolletosL());
		mapJRParams.put(FiseConstants.PARAM_SPORT_TV_L_F14A, formato14BBean.getSpotPublTvL());
		mapJRParams.put(FiseConstants.PARAM_SPORT_RADIO_L_F14A, formato14BBean.getSpotPublRadioL());
		mapJRParams.put(FiseConstants.PARAM_SUMA_DIFUSION_L_F14A, formato14BBean.getTotalDifIniProgL());
		mapJRParams.put(FiseConstants.PARAM_NRO_BNFCIARIOS_L_F14A, formato14BBean.getNroBenefEmpadL());
		mapJRParams.put(FiseConstants.PARAM_CSTO_UNIT_L_F14A, formato14BBean.getCostoUnitEmpadL());
		
		mapJRParams.put(FiseConstants.PARAM_SUMCSTO_GSTION_L_F14A, formato14BBean.getTotalCostoAgentL());
		mapJRParams.put(FiseConstants.PARAM_PROM_AGENTES_L_F14A, formato14BBean.getPromConvAgentL());
		mapJRParams.put(FiseConstants.PARAM_RGTROS_AGENTES_L_F14A, formato14BBean.getRegConvAgentL());
		mapJRParams.put(FiseConstants.PARAM_IMP_BANDER_L_F14A, formato14BBean.getImpEntrBandL());
		mapJRParams.put(FiseConstants.PARAM_NUM_AGENTES_L_F14A, formato14BBean.getNroAgentL());
		mapJRParams.put(FiseConstants.PARAM_CSTOUNIT_AGENTES_L_F14A, formato14BBean.getCostoUnitAgentL());*/
		
		return mapJRParams;
	}

	@Override
	@Transactional
	public List<FiseFormato14BDOb> listarFormato14BDObByFormato14BD(FiseFormato14BD formato14BD){
		return formato14BObsDao.listarFormato14BDObByFormato14BD(formato14BD); 
	}
	
	@Override
	@Transactional(propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
	public FiseFormato14BC modificarEnvioDefinitivoFormato14BC(Formato14BCBean formulario, FiseFormato14BC fiseFormato14BC) throws Exception {
		
		FiseFormato14BC dto = null;
		Date hoy = FechaUtil.obtenerFechaActual();
		try{
			fiseFormato14BC.setFechaEnvioDefinitivo(hoy);
			fiseFormato14BC.setUsuarioActualizacion(formulario.getUsuario());
			fiseFormato14BC.setTerminalActualizacion(formulario.getTerminal());
			fiseFormato14BC.setFechaActualizacion(hoy);
			formato14BCDao.modificarFormato14BC(fiseFormato14BC);
			dto= fiseFormato14BC;
		} catch (Exception e) {
			logger.error("--error"+e.getMessage());
			e.printStackTrace();
			throw e;
		}
		return dto;
	}
	
}
