package gob.osinergmin.fise.gart.service.impl;

import gob.osinergmin.fise.bean.Formato14ACBean;
import gob.osinergmin.fise.constant.FiseConstants;
import gob.osinergmin.fise.dao.CommonDao;
import gob.osinergmin.fise.dao.FiseGrupoInformacionDao;
import gob.osinergmin.fise.dao.FiseZonaBenefDao;
import gob.osinergmin.fise.dao.Formato14ACDao;
import gob.osinergmin.fise.dao.Formato14ADDao;
import gob.osinergmin.fise.dao.Formato14ADObDao;
import gob.osinergmin.fise.domain.FiseFormato14AC;
import gob.osinergmin.fise.domain.FiseFormato14ACPK;
import gob.osinergmin.fise.domain.FiseFormato14AD;
import gob.osinergmin.fise.domain.FiseFormato14ADOb;
import gob.osinergmin.fise.domain.FiseFormato14ADPK;
import gob.osinergmin.fise.domain.FiseGrupoInformacion;
import gob.osinergmin.fise.domain.FiseZonaBenef;
import gob.osinergmin.fise.gart.service.Formato14AGartService;
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
	
	@Autowired
	@Qualifier("commonDaoImpl")
	private CommonDao commonDao;
	
	@Autowired
	@Qualifier("fiseGrupoInformacionDaoImpl")
	private FiseGrupoInformacionDao fiseGrupoInformacionDao;
	
	@Override
	@Transactional
	public FiseFormato14AD obtenerFormato14ADVigente(String codEmpresa, long anioVigencia, long idZonaBenef){
		return formato14ADDao.obtenerFormato14ADVigente(codEmpresa, anioVigencia, idZonaBenef);
	}

	@Override
	@Transactional
	public FiseFormato14AC obtenerFormato14ACByPK(FiseFormato14ACPK fiseFormato14ACPK) {
		FiseFormato14AC formato = null;
		formato = formato14ACDao.obtenerFormato14ACByPK(fiseFormato14ACPK);
		if(formato != null){
			formato.setFiseFormato14ADs(formato14ADDao.listarFormato14ADByFormato14AC(formato));
		}
		return formato;
	}
	
	@Override
	@Transactional
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
			//id.setEtapa(FiseConstants.ETAPA_SOLICITUD);
			id.setEtapa(formulario.getEtapa());
			if( FiseConstants.TIPOARCHIVO_XLS.equals(formulario.getTipoArchivo()) ){
				fiseFormato14AC.setArchivoExcel(formulario.getNombreArchivo());
			}else if( FiseConstants.TIPOARCHIVO_TXT.equals(formulario.getTipoArchivo()) ){
				fiseFormato14AC.setArchivoTexto(formulario.getNombreArchivo());
			}/*else{
				id.setEtapa(formulario.getEtapa());
			}*/
			
			fiseFormato14AC.setId(id);
			
			FiseGrupoInformacion grupoInfo = null;
			long idGrupoInf = commonDao.obtenerIdGrupoInformacion(formulario.getAnioPresent(), formulario.getMesPresent(), FiseConstants.FRECUENCIA_BIENAL_DESCRIPCION); 
			if(idGrupoInf!=0){
				grupoInfo = fiseGrupoInformacionDao.obtenerFiseGrupoInformacionByPK(idGrupoInf);	
			}	
			fiseFormato14AC.setFiseGrupoInformacion(grupoInfo);
			
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
	
	//reporte
	@Override
	public Formato14ACBean estructurarFormato14ABeanByFiseFormato14AC(FiseFormato14AC formato){
		
		Formato14ACBean formato14ABean = new Formato14ACBean();
		
		formato14ABean.setAnioPresent(formato.getId().getAnoPresentacion());
		formato14ABean.setAnioInicioVigencia(formato.getId().getAnoInicioVigencia());
		formato14ABean.setAnioFinVigencia(formato.getId().getAnoFinVigencia());
		
    	for (FiseFormato14AD detalle : formato.getFiseFormato14ADs()) {
			if( FiseConstants.ZONABENEF_RURAL_COD == detalle.getId().getIdZonaBenef() ){
				formato14ABean.setImprEsqInvitR(detalle.getImpresionEsquelaInvitacion());
				formato14ABean.setImprDeclaJuradaR(detalle.getImpresionDeclaracionJurada());
				formato14ABean.setImprFichaVerifR(detalle.getImpresionFichasVerificacion());
				formato14ABean.setRepartoEsqInvitR(detalle.getRepartoEsquelaInvitacion());
				formato14ABean.setVerifInfoR(detalle.getVerificacionInformacion());
				formato14ABean.setElabArchivoBenefR(detalle.getElaboracionArchivoBenef());
				formato14ABean.setDigitFichaBenefR(detalle.getDigitacionFichaBenef());
				// total  de empadronamiento
				formato14ABean.setTotalEmpadR(detalle.getTotalEmpadronamiento());
				
				formato14ABean.setImprVolantesR(detalle.getImpresionVolantes());
				formato14ABean.setImprAfichesR(detalle.getImpresionAfiches());
				formato14ABean.setRepFolletosR(detalle.getRepartoFolletoPotenciaBenef());
				formato14ABean.setSpotPublTvR(detalle.getSpotPublicitarioTv());
				formato14ABean.setSpotPublRadioR(detalle.getSpotPublicitarioRadio());
				// Suma de total de difusion
				formato14ABean.setTotalDifIniProgR(detalle.getTotalDifusionInicioPrgFise());
				
				formato14ABean.setNroBenefEmpadR(detalle.getNumeroBenefEmpadroMesDic());
				//suma del empadronamiento + difusion
				BigDecimal sumTotalEmpadDifusionR = new BigDecimal(0);
				sumTotalEmpadDifusionR=sumTotalEmpadDifusionR.add(detalle.getTotalEmpadronamiento())
							.add(detalle.getTotalDifusionInicioPrgFise());
				formato14ABean.setSumEmpadDifusionR(sumTotalEmpadDifusionR);
				// Costo unitario por empadronamiento
				formato14ABean.setCostoUnitEmpadR(detalle.getCostoUnitarioEmpadronamiento());
				
				formato14ABean.setPromConvAgentR(detalle.getPromocionConvenioAgAutGlp());
				formato14ABean.setRegConvAgentR(detalle.getRegistroFirmaConvAgAutGlp());
				formato14ABean.setImpEntrBandR(detalle.getImpresionEntregaBanderola());
				formato14ABean.setNroAgentR(detalle.getNumeroAgentes());
				//total agentes
				formato14ABean.setTotalCostoAgentR(detalle.getTotalCostoGestionRedAgGlp());
				// Costo unitario por Agente
				formato14ABean.setCostoUnitAgentR(detalle.getCostoUntitarioAgenteGlp());
				
			}else if( FiseConstants.ZONABENEF_PROVINCIA_COD == detalle.getId().getIdZonaBenef() ){
				formato14ABean.setImprEsqInvitP(detalle.getImpresionEsquelaInvitacion());
				formato14ABean.setImprDeclaJuradaP(detalle.getImpresionDeclaracionJurada());
				formato14ABean.setImprFichaVerifP(detalle.getImpresionFichasVerificacion());
				formato14ABean.setRepartoEsqInvitP(detalle.getRepartoEsquelaInvitacion());
				formato14ABean.setVerifInfoP(detalle.getVerificacionInformacion());
				formato14ABean.setElabArchivoBenefP(detalle.getElaboracionArchivoBenef());
				formato14ABean.setDigitFichaBenefP(detalle.getDigitacionFichaBenef());
				// total  de empadronamiento
				formato14ABean.setTotalEmpadP(detalle.getTotalEmpadronamiento());
				
				formato14ABean.setImprVolantesP(detalle.getImpresionVolantes());
				formato14ABean.setImprAfichesP(detalle.getImpresionAfiches());
				formato14ABean.setRepFolletosP(detalle.getRepartoFolletoPotenciaBenef());
				formato14ABean.setSpotPublTvP(detalle.getSpotPublicitarioTv());
				formato14ABean.setSpotPublRadioP(detalle.getSpotPublicitarioRadio());
				// Suma de total de difusion
				formato14ABean.setTotalDifIniProgP(detalle.getTotalDifusionInicioPrgFise());
				
				formato14ABean.setNroBenefEmpadP(detalle.getNumeroBenefEmpadroMesDic());
				//suma del empadronamiento + difusion
				BigDecimal sumTotalEmpadDifusionP = new BigDecimal(0);
				sumTotalEmpadDifusionP=sumTotalEmpadDifusionP.add(detalle.getTotalEmpadronamiento())
							.add(detalle.getTotalDifusionInicioPrgFise());
				formato14ABean.setSumEmpadDifusionP(sumTotalEmpadDifusionP);
				// Costo unitario por empadronamiento
				formato14ABean.setCostoUnitEmpadP(detalle.getCostoUnitarioEmpadronamiento());
				
				formato14ABean.setPromConvAgentP(detalle.getPromocionConvenioAgAutGlp());
				formato14ABean.setRegConvAgentP(detalle.getRegistroFirmaConvAgAutGlp());
				formato14ABean.setImpEntrBandP(detalle.getImpresionEntregaBanderola());
				formato14ABean.setNroAgentP(detalle.getNumeroAgentes());
				//total agentes
				formato14ABean.setTotalCostoAgentP(detalle.getTotalCostoGestionRedAgGlp());
				// Costo unitario por Agente
				formato14ABean.setCostoUnitAgentP(detalle.getCostoUntitarioAgenteGlp());
				
			}else if( FiseConstants.ZONABENEF_LIMA_COD == detalle.getId().getIdZonaBenef() ){
				formato14ABean.setImprEsqInvitL(detalle.getImpresionEsquelaInvitacion());
				formato14ABean.setImprDeclaJuradaL(detalle.getImpresionDeclaracionJurada());
				formato14ABean.setImprFichaVerifL(detalle.getImpresionFichasVerificacion());
				formato14ABean.setRepartoEsqInvitL(detalle.getRepartoEsquelaInvitacion());
				formato14ABean.setVerifInfoL(detalle.getVerificacionInformacion());
				formato14ABean.setElabArchivoBenefL(detalle.getElaboracionArchivoBenef());
				formato14ABean.setDigitFichaBenefL(detalle.getDigitacionFichaBenef());
				// total  de empadronamiento
				formato14ABean.setTotalEmpadL(detalle.getTotalEmpadronamiento());
				
				formato14ABean.setImprVolantesL(detalle.getImpresionVolantes());
				formato14ABean.setImprAfichesL(detalle.getImpresionAfiches());
				formato14ABean.setRepFolletosL(detalle.getRepartoFolletoPotenciaBenef());
				formato14ABean.setSpotPublTvL(detalle.getSpotPublicitarioTv());
				formato14ABean.setSpotPublRadioL(detalle.getSpotPublicitarioRadio());
				// Suma de total de difusion
				formato14ABean.setTotalDifIniProgL(detalle.getTotalDifusionInicioPrgFise());
				
				formato14ABean.setNroBenefEmpadL(detalle.getNumeroBenefEmpadroMesDic());
				//suma del empadronamiento + difusion
				BigDecimal sumTotalEmpadDifusionL = new BigDecimal(0);
				sumTotalEmpadDifusionL=sumTotalEmpadDifusionL.add(detalle.getTotalEmpadronamiento())
							.add(detalle.getTotalDifusionInicioPrgFise());
				formato14ABean.setSumEmpadDifusionL(sumTotalEmpadDifusionL);
				// Costo unitario por empadronamiento
				formato14ABean.setCostoUnitEmpadL(detalle.getCostoUnitarioEmpadronamiento());
				
				formato14ABean.setPromConvAgentL(detalle.getPromocionConvenioAgAutGlp());
				formato14ABean.setRegConvAgentL(detalle.getRegistroFirmaConvAgAutGlp());
				formato14ABean.setImpEntrBandL(detalle.getImpresionEntregaBanderola());
				formato14ABean.setNroAgentL(detalle.getNumeroAgentes());
				//total agentes
				formato14ABean.setTotalCostoAgentL(detalle.getTotalCostoGestionRedAgGlp());
				// Costo unitario por Agente
				formato14ABean.setCostoUnitAgentL(detalle.getCostoUntitarioAgenteGlp());
			}
		}

		return formato14ABean;	
	}
 
 	@Override
	public HashMap<String, Object> mapearParametrosFormato14A(Formato14ACBean formato14ABean){
		
		HashMap<String, Object> mapJRParams = new HashMap<String, Object>();
		
		mapJRParams.put(FiseConstants.PARAM_DESC_EMPRESA_F14A, formato14ABean.getDescEmpresa());
		mapJRParams.put(FiseConstants.PARAM_ANO_PRES_F14A, formato14ABean.getAnioPresent());
		mapJRParams.put(FiseConstants.PARAM_DESC_MES_PRES_F14A, formato14ABean.getDescMesPresentacion());
		mapJRParams.put(FiseConstants.PARAM_ANO_INICIO_VIGENCIA, formato14ABean.getAnioInicioVigencia());
		mapJRParams.put(FiseConstants.PARAM_ANO_FIN_VIGENCIA, formato14ABean.getAnioFinVigencia());
		
		////----RURAL
		mapJRParams.put(FiseConstants.PARAM_SUM_EMP_SUMI_R_F14A,formato14ABean.getSumEmpadDifusionR());
		mapJRParams.put(FiseConstants.PARAM_IMP_ESQ_INV_R_F14A, formato14ABean.getImprEsqInvitR());
		mapJRParams.put(FiseConstants.PARAM_IMP_DECJUR_R_F14A, formato14ABean.getImprDeclaJuradaR());
		mapJRParams.put(FiseConstants.PARAM_IMP_FCH_VRFC_R_F14A, formato14ABean.getImprFichaVerifR());
		mapJRParams.put(FiseConstants.PARAM_RPT_EQL_INV_R_F14A, formato14ABean.getRepartoEsqInvitR());
		mapJRParams.put(FiseConstants.PARAM_VRF_INFOR_R_F14A, formato14ABean.getVerifInfoR());
		mapJRParams.put(FiseConstants.PARAM_ELBRCION_ACHVOS_R_F14A, formato14ABean.getElabArchivoBenefR());
		mapJRParams.put(FiseConstants.PARAM_DGTCION_FCHAS_R_F14A, formato14ABean.getDigitFichaBenefR());
		mapJRParams.put(FiseConstants.PARAM_SUM_EMPADR_R_F14A, formato14ABean.getTotalEmpadR());
		mapJRParams.put(FiseConstants.PARAM_IMP_VOL_R_F14A, formato14ABean.getImprVolantesR());
		mapJRParams.put(FiseConstants.PARAM_IMP_AFCHES_R_F14A, formato14ABean.getImprAfichesR());
		mapJRParams.put(FiseConstants.PARAM_RPTO_FLLETOS_R_F14A, formato14ABean.getRepFolletosR());
		mapJRParams.put(FiseConstants.PARAM_SPORT_TV_R_F14A, formato14ABean.getSpotPublTvR());
		mapJRParams.put(FiseConstants.PARAM_SPORT_RADIO_R_F14A, formato14ABean.getSpotPublRadioR());
		mapJRParams.put(FiseConstants.PARAM_SUMA_DIFUSION_R_F14A, formato14ABean.getTotalDifIniProgR());
		mapJRParams.put(FiseConstants.PARAM_NRO_BNFCIARIOS_R_F14A, formato14ABean.getNroBenefEmpadR());
		mapJRParams.put(FiseConstants.PARAM_CSTO_UNIT_R_F14A, formato14ABean.getCostoUnitEmpadR());
		
		mapJRParams.put(FiseConstants.PARAM_SUMCSTO_GSTION_R_F14A, formato14ABean.getTotalCostoAgentR());
		mapJRParams.put(FiseConstants.PARAM_PROM_AGENTES_R_F14A, formato14ABean.getPromConvAgentR());
		mapJRParams.put(FiseConstants.PARAM_RGTROS_AGENTES_R_F14A, formato14ABean.getRegConvAgentR());
		mapJRParams.put(FiseConstants.PARAM_IMP_BANDER_R_F14A, formato14ABean.getImpEntrBandR());
		mapJRParams.put(FiseConstants.PARAM_NUM_AGENTES_R_F14A, formato14ABean.getNroAgentR());
		mapJRParams.put(FiseConstants.PARAM_CSTOUNIT_AGENTES_R_F14A, formato14ABean.getCostoUnitAgentR());
		
		////----PROVINCIAS
		mapJRParams.put(FiseConstants.PARAM_SUM_EMP_SUMI_P_F14A,formato14ABean.getSumEmpadDifusionP());
		mapJRParams.put(FiseConstants.PARAM_IMP_ESQ_INV_P_F14A, formato14ABean.getImprEsqInvitP());
		mapJRParams.put(FiseConstants.PARAM_IMP_DECJUR_P_F14A, formato14ABean.getImprDeclaJuradaP());
		mapJRParams.put(FiseConstants.PARAM_IMP_FCH_VRFC_P_F14A, formato14ABean.getImprFichaVerifP());
		mapJRParams.put(FiseConstants.PARAM_RPT_EQL_INV_P_F14A, formato14ABean.getRepartoEsqInvitP());
		mapJRParams.put(FiseConstants.PARAM_VRF_INFOR_P_F14A, formato14ABean.getVerifInfoP());
		mapJRParams.put(FiseConstants.PARAM_ELBRCION_ACHVOS_P_F14A, formato14ABean.getElabArchivoBenefP());
		mapJRParams.put(FiseConstants.PARAM_DGTCION_FCHAS_P_F14A, formato14ABean.getDigitFichaBenefP());
		mapJRParams.put(FiseConstants.PARAM_SUM_EMPADR_P_F14A, formato14ABean.getTotalEmpadP());
		mapJRParams.put(FiseConstants.PARAM_IMP_VOL_P_F14A, formato14ABean.getImprVolantesP());
		mapJRParams.put(FiseConstants.PARAM_IMP_AFCHES_P_F14A, formato14ABean.getImprAfichesP());
		mapJRParams.put(FiseConstants.PARAM_RPTO_FLLETOS_P_F14A, formato14ABean.getRepFolletosP());
		mapJRParams.put(FiseConstants.PARAM_SPORT_TV_P_F14A, formato14ABean.getSpotPublTvP());
		mapJRParams.put(FiseConstants.PARAM_SPORT_RADIO_P_F14A, formato14ABean.getSpotPublRadioP());
		mapJRParams.put(FiseConstants.PARAM_SUMA_DIFUSION_P_F14A, formato14ABean.getTotalDifIniProgP());
		mapJRParams.put(FiseConstants.PARAM_NRO_BNFCIARIOS_P_F14A, formato14ABean.getNroBenefEmpadP());
		mapJRParams.put(FiseConstants.PARAM_CSTO_UNIT_P_F14A, formato14ABean.getCostoUnitEmpadP());
		
		mapJRParams.put(FiseConstants.PARAM_SUMCSTO_GSTION_P_F14A, formato14ABean.getTotalCostoAgentP());
		mapJRParams.put(FiseConstants.PARAM_PROM_AGENTES_P_F14A, formato14ABean.getPromConvAgentP());
		mapJRParams.put(FiseConstants.PARAM_RGTROS_AGENTES_P_F14A, formato14ABean.getRegConvAgentP());
		mapJRParams.put(FiseConstants.PARAM_IMP_BANDER_P_F14A, formato14ABean.getImpEntrBandP());
		mapJRParams.put(FiseConstants.PARAM_NUM_AGENTES_P_F14A, formato14ABean.getNroAgentP());
		mapJRParams.put(FiseConstants.PARAM_CSTOUNIT_AGENTES_P_F14A, formato14ABean.getCostoUnitAgentP());
		
		////-----LIMA
		
		mapJRParams.put(FiseConstants.PARAM_SUM_EMP_SUMI_L_F14A,formato14ABean.getSumEmpadDifusionL());
		mapJRParams.put(FiseConstants.PARAM_IMP_ESQ_INV_L_F14A, formato14ABean.getImprEsqInvitL());
		mapJRParams.put(FiseConstants.PARAM_IMP_DECJUR_L_F14A, formato14ABean.getImprDeclaJuradaL());
		mapJRParams.put(FiseConstants.PARAM_IMP_FCH_VRFC_L_F14A, formato14ABean.getImprFichaVerifL());
		mapJRParams.put(FiseConstants.PARAM_RPT_EQL_INV_L_F14A, formato14ABean.getRepartoEsqInvitL());
		mapJRParams.put(FiseConstants.PARAM_VRF_INFOR_L_F14A, formato14ABean.getVerifInfoL());
		mapJRParams.put(FiseConstants.PARAM_ELBRCION_ACHVOS_L_F14A, formato14ABean.getElabArchivoBenefL());
		mapJRParams.put(FiseConstants.PARAM_DGTCION_FCHAS_L_F14A, formato14ABean.getDigitFichaBenefL());
		mapJRParams.put(FiseConstants.PARAM_SUM_EMPADR_L_F14A, formato14ABean.getTotalEmpadL());
		mapJRParams.put(FiseConstants.PARAM_IMP_VOL_L_F14A, formato14ABean.getImprVolantesL());
		mapJRParams.put(FiseConstants.PARAM_IMP_AFCHES_L_F14A, formato14ABean.getImprAfichesL());
		mapJRParams.put(FiseConstants.PARAM_RPTO_FLLETOS_L_F14A, formato14ABean.getRepFolletosL());
		mapJRParams.put(FiseConstants.PARAM_SPORT_TV_L_F14A, formato14ABean.getSpotPublTvL());
		mapJRParams.put(FiseConstants.PARAM_SPORT_RADIO_L_F14A, formato14ABean.getSpotPublRadioL());
		mapJRParams.put(FiseConstants.PARAM_SUMA_DIFUSION_L_F14A, formato14ABean.getTotalDifIniProgL());
		mapJRParams.put(FiseConstants.PARAM_NRO_BNFCIARIOS_L_F14A, formato14ABean.getNroBenefEmpadL());
		mapJRParams.put(FiseConstants.PARAM_CSTO_UNIT_L_F14A, formato14ABean.getCostoUnitEmpadL());
		
		mapJRParams.put(FiseConstants.PARAM_SUMCSTO_GSTION_L_F14A, formato14ABean.getTotalCostoAgentL());
		mapJRParams.put(FiseConstants.PARAM_PROM_AGENTES_L_F14A, formato14ABean.getPromConvAgentL());
		mapJRParams.put(FiseConstants.PARAM_RGTROS_AGENTES_L_F14A, formato14ABean.getRegConvAgentL());
		mapJRParams.put(FiseConstants.PARAM_IMP_BANDER_L_F14A, formato14ABean.getImpEntrBandL());
		mapJRParams.put(FiseConstants.PARAM_NUM_AGENTES_L_F14A, formato14ABean.getNroAgentL());
		mapJRParams.put(FiseConstants.PARAM_CSTOUNIT_AGENTES_L_F14A, formato14ABean.getCostoUnitAgentL());
		
		return mapJRParams;
	}

	@Override
	@Transactional
	public List<FiseFormato14ADOb> listarFormato14ADObByFormato14AD(FiseFormato14AD formato14AD){
		return formato14AObsDao.listarFormato14ADObByFormato14AD(formato14AD); 
	}
	
	@Override
	@Transactional(propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
	public FiseFormato14AC modificarEnvioDefinitivoFormato14AC(Formato14ACBean formulario, FiseFormato14AC fiseFormato14AC) throws Exception {
		
		FiseFormato14AC dto = null;
		Date hoy = FechaUtil.obtenerFechaActual();
		try{
			fiseFormato14AC.setFechaEnvioDefinitivo(hoy);
			fiseFormato14AC.setUsuarioActualizacion(formulario.getUsuario());
			fiseFormato14AC.setTerminalActualizacion(formulario.getTerminal());
			fiseFormato14AC.setFechaActualizacion(hoy);
			formato14ACDao.modificarFormato14AC(fiseFormato14AC);
			dto= fiseFormato14AC;
		} catch (Exception e) {
			logger.error("--error"+e.getMessage());
			e.printStackTrace();
			throw e;
		}
		return dto;
	}
	
	
	@Override
	@Transactional(propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
	public void eliminarObservaciones14A(List<FiseFormato14ADOb> listaObs) throws Exception{	
		for (FiseFormato14ADOb observacion : listaObs) {
			formato14AObsDao.eliminarFormato14ADOb(observacion);
		}		
	}
	
	
}
