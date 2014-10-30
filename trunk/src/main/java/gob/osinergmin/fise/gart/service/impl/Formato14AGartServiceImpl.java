package gob.osinergmin.fise.gart.service.impl;

import gob.osinergmin.fise.bean.Formato14ACBean;
import gob.osinergmin.fise.constant.FiseConstants;
import gob.osinergmin.fise.dao.FiseZonaBenefDao;
import gob.osinergmin.fise.dao.Formato14ACDao;
import gob.osinergmin.fise.dao.Formato14ADDao;
import gob.osinergmin.fise.dao.Formato14ADObDao;
import gob.osinergmin.fise.domain.FiseFormato14AC;
import gob.osinergmin.fise.domain.FiseFormato14ACPK;
import gob.osinergmin.fise.domain.FiseFormato14AD;
import gob.osinergmin.fise.domain.FiseFormato14ADOb;
import gob.osinergmin.fise.domain.FiseFormato14ADPK;
import gob.osinergmin.fise.domain.FiseZonaBenef;
import gob.osinergmin.fise.gart.service.Formato14AGartService;
import gob.osinergmin.fise.util.FechaUtil;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

@Service(value="formato14AGartServiceImpl")
public class Formato14AGartServiceImpl implements Formato14AGartService {
	
	Logger logger=Logger.getLogger(Formato14AGartServiceImpl.class);
	
	@Autowired
	@Qualifier("formato14ACDaoImpl")
	private Formato14ACDao formato14ACDao;
	
	@Autowired
	@Qualifier("formato14ADDaoImpl")
	private Formato14ADDao formato14ADDao;
	
	@Autowired
	@Qualifier("fiseZonaBenefDaoImpl")
	private FiseZonaBenefDao zonaBenefDao;
	
	@Autowired
	@Qualifier("formato14ADObDaoImpl")
	private Formato14ADObDao formato14AObsDao;
	
	@Override
	public FiseFormato14AD obtenerFormato14ADVigente(String codEmpresa, long anioVigencia, long idZonaBenef){
		return formato14ADDao.obtenerFormato14ADVigente(codEmpresa, anioVigencia, idZonaBenef);
	}

	@Override
	public FiseFormato14AC obtenerFormato14ACByPK(FiseFormato14ACPK fiseFormato14ACPK) {
		FiseFormato14AC formato = null;
		formato = formato14ACDao.obtenerFormato14ACByPK(fiseFormato14ACPK);
		if(formato != null){
			formato.setFiseFormato14ADs(formato14ADDao.listarFormato14ADByFormato14AC(formato));
		}
		return formato;
	}
	
	@Override
	public List<FiseFormato14AC> buscarFormato14AC(String codEmpresa, long anioDesde, long mesDesde, long anioHasta, long mesHasta, String etapa){
		
		List<FiseFormato14AC> lista = null;
		lista = formato14ACDao.buscarFormato14AC(codEmpresa, anioDesde, mesDesde, anioHasta, mesHasta, etapa);
		for (FiseFormato14AC formato : lista) {
			formato.setFiseFormato14ADs(formato14ADDao.listarFormato14ADByFormato14AC(formato));
		}
		return lista;
	}
	
	@Override
	@Transactional(propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
	public FiseFormato14AC registrarFormato14AC(Formato14ACBean formulario) throws Exception {
		
		FiseFormato14AC dto = null;
		
		try {
			Date hoy = FechaUtil.obtenerFechaActual();
			FiseFormato14AC fiseFormato14AC = new FiseFormato14AC();
			//guardar el pk
			FiseFormato14ACPK id = new FiseFormato14ACPK();
			id.setCodEmpresa(formulario.getCodigoEmpresa());
			id.setAnoPresentacion(formulario.getAnioPresent());
			id.setMesPresentacion(formulario.getMesPresent());
			id.setAnoInicioVigencia(formulario.getAnioInicioVigencia());
			id.setAnoFinVigencia(formulario.getAnioFinVigencia());
			id.setEtapa(FiseConstants.ETAPA_SOLICITUD);
			if( FiseConstants.TIPOARCHIVO_XLS.equals(formulario.getTipoArchivo()) ){
				fiseFormato14AC.setArchivoExcel(formulario.getNombreArchivo());
			}else if( FiseConstants.TIPOARCHIVO_TXT.equals(formulario.getTipoArchivo()) ){
				fiseFormato14AC.setArchivoTexto(formulario.getNombreArchivo());
			}else{
				id.setEtapa(formulario.getEtapa());
			}
			
			fiseFormato14AC.setId(id);
			
			List<FiseFormato14AD> lista = new ArrayList<FiseFormato14AD>();
			//RURAL
			if( formulario.getNroBenefEmpadR() != 0 &&
					formulario.getNroAgentR() != 0 
					){
				logger.info("entro a RURAL");
				FiseZonaBenef zonaBenef = new FiseZonaBenef();
				zonaBenef = zonaBenefDao.obtenerFiseZonaBenefByPK(FiseConstants.ZONABENEF_RURAL_COD);
				//
				FiseFormato14AD detalleRural = new FiseFormato14AD();
				//pk
				FiseFormato14ADPK pkDetalle = new FiseFormato14ADPK();
				pkDetalle.setCodEmpresa(fiseFormato14AC.getId().getCodEmpresa());
				pkDetalle.setAnoPresentacion(fiseFormato14AC.getId().getAnoPresentacion());
				pkDetalle.setMesPresentacion(fiseFormato14AC.getId().getMesPresentacion());
				pkDetalle.setAnoInicioVigencia(fiseFormato14AC.getId().getAnoInicioVigencia());
				pkDetalle.setAnoFinVigencia(fiseFormato14AC.getId().getAnoFinVigencia());
				pkDetalle.setIdZonaBenef(zonaBenef.getIdZonaBenef());
				pkDetalle.setEtapa(fiseFormato14AC.getId().getEtapa());
				detalleRural.setId(pkDetalle);
				
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
				
				detalleRural.setNumeroBenefEmpadroMesDic(formulario.getNroBenefEmpadR());
				//costo
				if( formulario.getNroBenefEmpadR() !=0 ){
					detalleRural.setCostoUnitarioEmpadronamiento(detalleRural.getTotalEmpadronamiento().divide(new BigDecimal(detalleRural.getNumeroBenefEmpadroMesDic())));
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
					detalleRural.setCostoUntitarioAgenteGlp(detalleRural.getTotalCostoGestionRedAgGlp().divide(new BigDecimal(detalleRural.getNumeroAgentes())));
				}else{
					detalleRural.setCostoUntitarioAgenteGlp(BigDecimal.ZERO);
				}
				//
				detalleRural.setFiseFormato14AC(fiseFormato14AC);
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
			if( formulario.getNroBenefEmpadP() != 0 &&
					formulario.getNroAgentP() != 0 
					){
				logger.info("entro a PROVINCIA");
				FiseZonaBenef zonaBenef = new FiseZonaBenef();
				zonaBenef = zonaBenefDao.obtenerFiseZonaBenefByPK(FiseConstants.ZONABENEF_PROVINCIA_COD);
				//
				FiseFormato14AD detalleProvincia = new FiseFormato14AD();
				//pk
				FiseFormato14ADPK pkDetalle = new FiseFormato14ADPK();
				pkDetalle.setCodEmpresa(fiseFormato14AC.getId().getCodEmpresa());
				pkDetalle.setAnoPresentacion(fiseFormato14AC.getId().getAnoPresentacion());
				pkDetalle.setMesPresentacion(fiseFormato14AC.getId().getMesPresentacion());
				pkDetalle.setAnoInicioVigencia(fiseFormato14AC.getId().getAnoInicioVigencia());
				pkDetalle.setAnoFinVigencia(fiseFormato14AC.getId().getAnoFinVigencia());
				pkDetalle.setIdZonaBenef(zonaBenef.getIdZonaBenef());
				pkDetalle.setEtapa(fiseFormato14AC.getId().getEtapa());
				detalleProvincia.setId(pkDetalle);
				
				//empadronamiento
				detalleProvincia.setImpresionEsquelaInvitacion(formulario.getImprEsqInvitP());
				detalleProvincia.setImpresionDeclaracionJurada(formulario.getImprDeclaJuradaP());
				detalleProvincia.setImpresionFichasVerificacion(formulario.getImprFichaVerifP());
				detalleProvincia.setRepartoEsquelaInvitacion(formulario.getRepartoEsqInvitP());
				detalleProvincia.setVerificacionInformacion(formulario.getVerifInfoR());
				detalleProvincia.setElaboracionArchivoBenef(formulario.getElabArchivoBenefP());
				detalleProvincia.setDigitacionFichaBenef(formulario.getDigitFichaBenefP());
				
				BigDecimal totalEmpadronamiento = new BigDecimal(0);
				totalEmpadronamiento = totalEmpadronamiento.add(detalleProvincia.getImpresionEsquelaInvitacion())
						.add(detalleProvincia.getImpresionDeclaracionJurada())
						.add(detalleProvincia.getImpresionFichasVerificacion())
						.add(detalleProvincia.getRepartoEsquelaInvitacion())
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
				
				detalleProvincia.setNumeroBenefEmpadroMesDic(formulario.getNroBenefEmpadP());
				//costo
				if( formulario.getNroBenefEmpadP() !=0 ){
					detalleProvincia.setCostoUnitarioEmpadronamiento(
							detalleProvincia.getTotalEmpadronamiento().divide(new BigDecimal(detalleProvincia.getNumeroBenefEmpadroMesDic()),4,RoundingMode.HALF_UP));
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
				detalleProvincia.setFiseFormato14AC(fiseFormato14AC);
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
			if( formulario.getNroBenefEmpadL() != 0 &&
					formulario.getNroAgentL() != 0 
					){
				logger.info("entro a LIMA");
				FiseZonaBenef zonaBenef = new FiseZonaBenef();
				zonaBenef = zonaBenefDao.obtenerFiseZonaBenefByPK(FiseConstants.ZONABENEF_LIMA_COD);
				//
				FiseFormato14AD detalleLima = new FiseFormato14AD();
				//pk
				FiseFormato14ADPK pkDetalle = new FiseFormato14ADPK();
				pkDetalle.setCodEmpresa(fiseFormato14AC.getId().getCodEmpresa());
				pkDetalle.setAnoPresentacion(fiseFormato14AC.getId().getAnoPresentacion());
				pkDetalle.setMesPresentacion(fiseFormato14AC.getId().getMesPresentacion());
				pkDetalle.setAnoInicioVigencia(fiseFormato14AC.getId().getAnoInicioVigencia());
				pkDetalle.setAnoFinVigencia(fiseFormato14AC.getId().getAnoFinVigencia());
				pkDetalle.setIdZonaBenef(zonaBenef.getIdZonaBenef());
				pkDetalle.setEtapa(fiseFormato14AC.getId().getEtapa());
				detalleLima.setId(pkDetalle);
				
				//empadronamiento
				detalleLima.setImpresionEsquelaInvitacion(formulario.getImprEsqInvitL());
				detalleLima.setImpresionDeclaracionJurada(formulario.getImprDeclaJuradaL());
				detalleLima.setImpresionFichasVerificacion(formulario.getImprFichaVerifL());
				detalleLima.setRepartoEsquelaInvitacion(formulario.getRepartoEsqInvitL());
				detalleLima.setVerificacionInformacion(formulario.getVerifInfoR());
				detalleLima.setElaboracionArchivoBenef(formulario.getElabArchivoBenefL());
				detalleLima.setDigitacionFichaBenef(formulario.getDigitFichaBenefL());
				
				BigDecimal totalEmpadronamiento = new BigDecimal(0);
				totalEmpadronamiento = totalEmpadronamiento.add(detalleLima.getImpresionEsquelaInvitacion())
						.add(detalleLima.getImpresionDeclaracionJurada())
						.add(detalleLima.getImpresionFichasVerificacion())
						.add(detalleLima.getRepartoEsquelaInvitacion())
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
				
				detalleLima.setNumeroBenefEmpadroMesDic(formulario.getNroBenefEmpadL());
				//costo
				if( formulario.getNroBenefEmpadL() !=0 ){
					detalleLima.setCostoUnitarioEmpadronamiento(
							detalleLima.getTotalEmpadronamiento().divide(new BigDecimal(detalleLima.getNumeroBenefEmpadroMesDic()),4,RoundingMode.HALF_UP));
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
				detalleLima.setFiseFormato14AC(fiseFormato14AC);
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
			
			fiseFormato14AC.setUsuarioActualizacion(formulario.getUsuario());
			fiseFormato14AC.setTerminalActualizacion(formulario.getTerminal());
			fiseFormato14AC.setFechaActualizacion(hoy);
			fiseFormato14AC.setUsuarioCreacion(formulario.getUsuario());
			fiseFormato14AC.setTerminalCreacion(formulario.getTerminal());
			fiseFormato14AC.setFechaCreacion(hoy);
			
			logger.info("aca se va  a guardar"+fiseFormato14AC);
			boolean existe = false;
			existe = formato14ACDao.existeFormato14AC(fiseFormato14AC);
			if(existe){
				throw new Exception("Ya existe un registro con la misma clave.");
			}else{
				formato14ACDao.registrarFormato14AC(fiseFormato14AC);
			}
			//add
			for (FiseFormato14AD detalle : lista) {
				formato14ADDao.registrarFormato14AD(detalle);
			}
			if( lista != null && lista.size()>0 ){
				fiseFormato14AC.setFiseFormato14ADs(lista);
			}
			dto = fiseFormato14AC;
			
		} 	catch (Exception e) {
			logger.error("--error"+e.getMessage());
			e.printStackTrace();
			throw e;
		}
		return dto;
	}
	
	@Override
	@Transactional(propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
	public FiseFormato14AC modificarFormato14AC(Formato14ACBean formulario, FiseFormato14AC fiseFormato14AC) throws Exception {
		
		FiseFormato14AC dto = null;
		
		try {
			
			Date hoy = FechaUtil.obtenerFechaActual();
	
			List<FiseFormato14AD> lista = new ArrayList<FiseFormato14AD>();
			
			FiseFormato14AD detalleRural = new FiseFormato14AD();
			FiseFormato14AD detalleProvincia = new FiseFormato14AD();
			FiseFormato14AD detalleLima = new FiseFormato14AD();
			if( fiseFormato14AC.getFiseFormato14ADs()!=null ){
				for (FiseFormato14AD detalle : fiseFormato14AC.getFiseFormato14ADs()) {
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
			if( formulario.getNroBenefEmpadR() != 0 &&
					formulario.getNroAgentR() != 0 
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
				
				detalleRural.setNumeroBenefEmpadroMesDic(formulario.getNroBenefEmpadR());
				//costo
				if( formulario.getNroBenefEmpadR() !=0 ){
					detalleRural.setCostoUnitarioEmpadronamiento(
							detalleRural.getTotalEmpadronamiento().divide(new BigDecimal(detalleRural.getNumeroBenefEmpadroMesDic()),4,RoundingMode.HALF_UP));
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
				//--detalleRural.setFiseFormato14AC(fiseFormato14AC);
				//
				detalleRural.setUsuarioActualizacion(formulario.getUsuario());
				detalleRural.setTerminalActualizacion(formulario.getTerminal());
				detalleRural.setFechaActualizacion(hoy);
				lista.add(detalleRural);
			}
			//PROVINCIA
			if( formulario.getNroBenefEmpadP() != 0 &&
					formulario.getNroAgentP() != 0 
					){
				logger.info("se modificara PROVINCIA");
				//empadronamiento
				detalleProvincia.setImpresionEsquelaInvitacion(formulario.getImprEsqInvitP());
				detalleProvincia.setImpresionDeclaracionJurada(formulario.getImprDeclaJuradaP());
				detalleProvincia.setImpresionFichasVerificacion(formulario.getImprFichaVerifP());
				detalleProvincia.setRepartoEsquelaInvitacion(formulario.getRepartoEsqInvitP());
				detalleProvincia.setVerificacionInformacion(formulario.getVerifInfoR());
				detalleProvincia.setElaboracionArchivoBenef(formulario.getElabArchivoBenefP());
				detalleProvincia.setDigitacionFichaBenef(formulario.getDigitFichaBenefP());
				
				BigDecimal totalEmpadronamiento = new BigDecimal(0);
				totalEmpadronamiento = totalEmpadronamiento.add(detalleProvincia.getImpresionEsquelaInvitacion())
						.add(detalleProvincia.getImpresionDeclaracionJurada())
						.add(detalleProvincia.getImpresionFichasVerificacion())
						.add(detalleProvincia.getRepartoEsquelaInvitacion())
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
				
				detalleProvincia.setNumeroBenefEmpadroMesDic(formulario.getNroBenefEmpadP());
				//costo
				if( formulario.getNroBenefEmpadP() !=0 ){
					detalleProvincia.setCostoUnitarioEmpadronamiento(
							detalleProvincia.getTotalEmpadronamiento().divide(new BigDecimal(detalleProvincia.getNumeroBenefEmpadroMesDic()),4,RoundingMode.HALF_UP));
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
				//--detalleProvincia.setFiseFormato14AC(fiseFormato14AC);
				//
				detalleProvincia.setUsuarioActualizacion(formulario.getUsuario());
				detalleProvincia.setTerminalActualizacion(formulario.getTerminal());
				detalleProvincia.setFechaActualizacion(hoy);
				lista.add(detalleProvincia);
			}
			//LIMA
			if( formulario.getNroBenefEmpadL() != 0 &&
					formulario.getNroAgentL() != 0 
					){
				logger.info("se modificara LIMA");
				//empadronamiento
				detalleLima.setImpresionEsquelaInvitacion(formulario.getImprEsqInvitL());
				detalleLima.setImpresionDeclaracionJurada(formulario.getImprDeclaJuradaL());
				detalleLima.setImpresionFichasVerificacion(formulario.getImprFichaVerifL());
				detalleLima.setRepartoEsquelaInvitacion(formulario.getRepartoEsqInvitL());
				detalleLima.setVerificacionInformacion(formulario.getVerifInfoR());
				detalleLima.setElaboracionArchivoBenef(formulario.getElabArchivoBenefL());
				detalleLima.setDigitacionFichaBenef(formulario.getDigitFichaBenefL());
				
				BigDecimal totalEmpadronamiento = new BigDecimal(0);
				totalEmpadronamiento = totalEmpadronamiento.add(detalleLima.getImpresionEsquelaInvitacion())
						.add(detalleLima.getImpresionDeclaracionJurada())
						.add(detalleLima.getImpresionFichasVerificacion())
						.add(detalleLima.getRepartoEsquelaInvitacion())
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
				
				detalleLima.setNumeroBenefEmpadroMesDic(formulario.getNroBenefEmpadL());
				//costo
				if( formulario.getNroBenefEmpadL() !=0 ){
					detalleLima.setCostoUnitarioEmpadronamiento(
							detalleLima.getTotalEmpadronamiento().divide(new BigDecimal(detalleLima.getNumeroBenefEmpadroMesDic()),4,RoundingMode.HALF_UP));
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
				//--detalleLima.setFiseFormato14AC(fiseFormato14AC);
				//
				detalleLima.setUsuarioActualizacion(formulario.getUsuario());
				detalleLima.setTerminalActualizacion(formulario.getTerminal());
				detalleLima.setFechaActualizacion(hoy);
				lista.add(detalleLima);
			}
		
			fiseFormato14AC.setUsuarioActualizacion(formulario.getUsuario());
			fiseFormato14AC.setTerminalActualizacion(formulario.getTerminal());
			fiseFormato14AC.setFechaActualizacion(hoy);
		
			if( FiseConstants.TIPOARCHIVO_XLS.equals(formulario.getTipoArchivo()) ){
				fiseFormato14AC.setArchivoExcel(formulario.getNombreArchivo());
			}else if( FiseConstants.TIPOARCHIVO_TXT.equals(formulario.getTipoArchivo()) ){
				fiseFormato14AC.setArchivoTexto(formulario.getNombreArchivo());
			}
			
			formato14ACDao.modificarFormato14AC(fiseFormato14AC);
			//add
			for (FiseFormato14AD detalle : lista) {
				formato14ADDao.modificarFormato14AD(detalle);
			}
			dto= fiseFormato14AC;
			
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
	public void eliminarFormato14AC(FiseFormato14AC fiseFormato14AC) {
		//eliminar antes los detalles creados para esa cabecera
		List<FiseFormato14AD> lista = null;
		lista = formato14ADDao.listarFormato14ADByFormato14AC(fiseFormato14AC);
		//por el momento no se esta borrando las dependencias hacias el detalle de observaciones
		//luego aumentar esta dependencia
		for (FiseFormato14AD detalle : lista) {
			List<FiseFormato14ADOb> listaObservacion = formato14AObsDao.listarFormato14ADObByFormato14AD(detalle);
			for (FiseFormato14ADOb observacion : listaObservacion) {
				formato14AObsDao.eliminarFormato14ADOb(observacion);
			}
			formato14ADDao.eliminarFormato14AD(detalle);
		}
		formato14ACDao.eliminarFormato14AC(fiseFormato14AC);
	}
	
}
