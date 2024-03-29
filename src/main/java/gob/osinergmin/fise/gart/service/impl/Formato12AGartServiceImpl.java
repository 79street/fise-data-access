package gob.osinergmin.fise.gart.service.impl;

import gob.osinergmin.fise.bean.Formato12ACBean;
import gob.osinergmin.fise.constant.FiseConstants;
import gob.osinergmin.fise.dao.ArchivoSustentoDao;
import gob.osinergmin.fise.dao.CommonDao;
import gob.osinergmin.fise.dao.FiseGrupoInformacionDao;
import gob.osinergmin.fise.dao.FiseObservacionDao;
import gob.osinergmin.fise.dao.FiseZonaBenefDao;
import gob.osinergmin.fise.dao.Formato12ACDao;
import gob.osinergmin.fise.dao.Formato12ADDao;
import gob.osinergmin.fise.dao.Formato12ADObDao;
import gob.osinergmin.fise.domain.FiseArchivosCab;
import gob.osinergmin.fise.domain.FiseArchivosDet;
import gob.osinergmin.fise.domain.FiseFormato12AC;
import gob.osinergmin.fise.domain.FiseFormato12ACPK;
import gob.osinergmin.fise.domain.FiseFormato12AD;
import gob.osinergmin.fise.domain.FiseFormato12ADOb;
import gob.osinergmin.fise.domain.FiseFormato12ADObPK;
import gob.osinergmin.fise.domain.FiseFormato12ADPK;
import gob.osinergmin.fise.domain.FiseGrupoInformacion;
import gob.osinergmin.fise.domain.FiseObservacion;
import gob.osinergmin.fise.domain.FiseZonaBenef;
import gob.osinergmin.fise.gart.service.Formato12AGartService;
import gob.osinergmin.fise.util.FechaUtil;

import java.math.BigDecimal;
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



@Service(value="formato12AGartServiceImpl")
public class Formato12AGartServiceImpl implements Formato12AGartService {

	Logger logger=Logger.getLogger(Formato12AGartServiceImpl.class);
	
	@Autowired
	@Qualifier("formato12ACDaoImpl")
	private Formato12ACDao formato12ACDao;
	
	@Autowired
	@Qualifier("formato12ADDaoImpl")
	private Formato12ADDao formato12ADDao;
	
	@Autowired
	@Qualifier("fiseZonaBenefDaoImpl")
	private FiseZonaBenefDao zonaBenefDao;
	
	@Autowired
	@Qualifier("formato12ADObDaoImpl")
	private Formato12ADObDao formato12AObsDao;
	
	@Autowired
	@Qualifier("commonDaoImpl")
	private CommonDao commonDao;
	
	@Autowired
	@Qualifier("fiseGrupoInformacionDaoImpl")
	private FiseGrupoInformacionDao fiseGrupoInformacionDao;
	
	@Autowired
	@Qualifier("fiseObservacionDaoImpl")
	private FiseObservacionDao fiseObservacionDao;
	
	@Autowired
	@Qualifier("archivoSustentoDaoImpl")
	private ArchivoSustentoDao archivoSustentoDao;
	
	
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
	
	//cambios elozano para reporte de obs.	
    @Override
	@Transactional
	public List<FiseFormato12AC> buscarFormato12AReporteObs(String codEmpresa,
			long idGrupoInf,String etapa)throws Exception {		
		List<FiseFormato12AC> lista = formato12ACDao.buscarFormato12ACReporteObs(codEmpresa, idGrupoInf, etapa);
		List<FiseFormato12AC> listaF12A = new ArrayList<FiseFormato12AC>();
		for(FiseFormato12AC f :lista){
			f.setFiseFormato12ADs(formato12ADDao.listarFormato12ADByFormato12AC(f));
			listaF12A.add(f);
		}		
		return listaF12A;
	}	
    
	
	@Override
	@Transactional(propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
	public FiseFormato12AC registrarFormato12AC(Formato12ACBean formulario) throws Exception {
		
		FiseFormato12AC dto = null;
		
		try {
			Date hoy = FechaUtil.obtenerFechaActual();
			FiseFormato12AC fiseFormato12AC = new FiseFormato12AC();
			//guardar el pk
			FiseFormato12ACPK id = new FiseFormato12ACPK();
			id.setCodEmpresa(formulario.getCodigoEmpresa());
			id.setAnoPresentacion(formulario.getAnioPresent());
			id.setMesPresentacion(formulario.getMesPresent());		
			id.setAnoEjecucionGasto(formulario.getAnioEjecuc());
			id.setMesEjecucionGasto(formulario.getMesEjecuc());			
			id.setEtapa(formulario.getEtapa());			
			
			if( FiseConstants.TIPOARCHIVO_XLS.equals(formulario.getTipoArchivo()) ){
				fiseFormato12AC.setArchivoExcel(formulario.getNombreArchivo());
			}else if( FiseConstants.TIPOARCHIVO_TXT.equals(formulario.getTipoArchivo()) ){
				fiseFormato12AC.setArchivoTexto(formulario.getNombreArchivo());
			}
			
			fiseFormato12AC.setId(id);
			
			FiseGrupoInformacion grupoInfo = null;
			long idGrupoInf = commonDao.obtenerIdGrupoInformacion(formulario.getAnioPresent(), formulario.getMesPresent(), FiseConstants.FRECUENCIA_MENSUAL_DESCRIPCION); 
			if(idGrupoInf!=0){
				grupoInfo = fiseGrupoInformacionDao.obtenerFiseGrupoInformacionByPK(idGrupoInf);	
			}	
			fiseFormato12AC.setFiseGrupoInformacion(grupoInfo);
			
		    //total de las 3 detalles segun sea el caso
			BigDecimal total = new BigDecimal(0);
			List<FiseFormato12AD> lista = new ArrayList<FiseFormato12AD>();
			
			//RURAL
			
			boolean cambio = true; //cambio elozano
			
			/*if( formulario.getNroEmpadR() != 0 ||
					//!formulario.getCostoUnitEmpadR().equals(BigDecimal.ZERO) &&
					formulario.getNroAgentR() != 0 //&&
					//!formulario.getCostoUnitAgentR().equals(BigDecimal.ZERO) &&
					//!formulario.getDesplPersonalR().equals(BigDecimal.ZERO) &&
					//!formulario.getActivExtraordR().equals(BigDecimal.ZERO) 
					){*/
			  if(cambio){
				logger.info("entro a RURAL");
				FiseZonaBenef zonaBenef = new FiseZonaBenef();
				zonaBenef = zonaBenefDao.obtenerFiseZonaBenefByPK(FiseConstants.ZONABENEF_RURAL_COD);				
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
				
				detalleRural.setFiseFormato12AC(fiseFormato12AC);
				
				detalleRural.setUsuarioCreacion(formulario.getUsuario());
				detalleRural.setTerminalCreacion(formulario.getTerminal());
				detalleRural.setFechaCreacion(hoy);
				detalleRural.setUsuarioActualizacion(formulario.getUsuario());
				detalleRural.setTerminalActualizacion(formulario.getTerminal());
				detalleRural.setFechaActualizacion(hoy);
				
				//grabar detalle
				logger.info("totalrural"+totalRural);
				total = total.add(totalRural);				
				logger.info("total hasta rural"+total);				
				lista.add(detalleRural);
			}
			//PROVINCIA
			/*if( formulario.getNroEmpadP() != 0 ||
					//!formulario.getCostoUnitEmpadP().equals(BigDecimal.ZERO) &&
					formulario.getNroAgentP() != 0 //&&
					//!formulario.getCostoUnitAgentP().equals(BigDecimal.ZERO) &&
					//!formulario.getDesplPersonalP().equals(BigDecimal.ZERO) &&
					//!formulario.getActivExtraordP().equals(BigDecimal.ZERO) 
					){*/
			  if(cambio){
				logger.info("entro a PROVINCIA");
				FiseZonaBenef zonaBenef = new FiseZonaBenef();
				zonaBenef = zonaBenefDao.obtenerFiseZonaBenefByPK(FiseConstants.ZONABENEF_PROVINCIA_COD);
				
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
				
				detalleProvincia.setFiseFormato12AC(fiseFormato12AC);
				
				detalleProvincia.setUsuarioCreacion(formulario.getUsuario());
				detalleProvincia.setTerminalCreacion(formulario.getTerminal());
				detalleProvincia.setFechaCreacion(hoy);
				detalleProvincia.setUsuarioActualizacion(formulario.getUsuario());
				detalleProvincia.setTerminalActualizacion(formulario.getTerminal());
				detalleProvincia.setFechaActualizacion(hoy);
				
				//grabar detalle
				logger.info("totalprovincia"+totalProvincia);
				total = total.add(totalProvincia);				
				logger.info("total hasta provincia"+total);				
				lista.add(detalleProvincia);
			}
			//LIMA
			/*if( formulario.getNroEmpadL() != 0 ||
					//!formulario.getCostoUnitEmpadL().equals(BigDecimal.ZERO) &&
					formulario.getNroAgentL() != 0 //&&
					//!formulario.getCostoUnitAgentL().equals(BigDecimal.ZERO) &&
					//!formulario.getDesplPersonalL().equals(BigDecimal.ZERO) &&
					//!formulario.getActivExtraordL().equals(BigDecimal.ZERO) 
					){*/
			  if(FiseConstants.COD_EMPRESA_EDELNOR.equals(formulario.getCodigoEmpresa()) || 
						FiseConstants.COD_EMPRESA_LUZ_SUR.equals(formulario.getCodigoEmpresa().trim()))
				{
				logger.info("entro a LIMA");
				FiseZonaBenef zonaBenef = new FiseZonaBenef();
				zonaBenef = zonaBenefDao.obtenerFiseZonaBenefByPK(FiseConstants.ZONABENEF_LIMA_COD);
				
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
				
				detalleLima.setUsuarioCreacion(formulario.getUsuario());
				detalleLima.setTerminalCreacion(formulario.getTerminal());
				detalleLima.setFechaCreacion(hoy);
				detalleLima.setUsuarioActualizacion(formulario.getUsuario());
				detalleLima.setTerminalActualizacion(formulario.getTerminal());
				detalleLima.setFechaActualizacion(hoy);
				
				//grabar detalle
				logger.info("totallima"+totalLima);
				total = total.add(totalLima);				
				logger.info("total hasta lima"+total);			
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
			
			boolean existe = false;
			existe = formato12ACDao.existeFormato12AC(fiseFormato12AC);
			if(existe){
				throw new Exception("El Formato ya existe para la Distribuidora Eléctrica y Periodo a Declarar seleccionado");
			}else{
				try{
					formato12ACDao.registrarFormato12AC(fiseFormato12AC);
				}catch(Exception e){
					throw new Exception("Se produjo un error al guardar los datos del Formato 12A");
				}				
			}			
			//add
			for (FiseFormato12AD detalle : lista) {
				try{
					formato12ADDao.registrarFormato12AD(detalle);
				}catch(Exception e){
					throw new Exception("Se produjo un error al guardar los datos del Formato 12A");
				}
			}			
			if( lista != null && lista.size()>0 ){
				fiseFormato12AC.setFiseFormato12ADs(lista);
			}
			dto = fiseFormato12AC;
			
		} 	catch (Exception e) {
			logger.error("--error"+e.getMessage());
			e.printStackTrace();
			throw new Exception(e.getMessage());
		}
		return dto;
	}
	
	
	@Override
	@Transactional(propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
	public FiseFormato12AC modificarFormato12AC(Formato12ACBean formulario, FiseFormato12AC fiseFormato12AC) throws Exception {
		
		/***tomar en cuenta que para la carga de excel hacer otro metodo***/
		
		FiseFormato12AC dto = null;
		
		try {
			
			Date hoy = FechaUtil.obtenerFechaActual();
	
			BigDecimal total = new BigDecimal(0);
			List<FiseFormato12AD> lista = new ArrayList<FiseFormato12AD>();	
			FiseFormato12AD detalleRural = null;
			FiseFormato12AD detalleProvincia = null;
			FiseFormato12AD detalleLima = null;			
			
			if( fiseFormato12AC.getFiseFormato12ADs()!=null ){
				for (FiseFormato12AD detalle : fiseFormato12AC.getFiseFormato12ADs()) {
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
			if( detalleRural != null ){
			/*if( detalleRural != null &&
					formulario.getNroEmpadR() != 0 &&
					//!formulario.getCostoUnitEmpadR().equals(BigDecimal.ZERO) &&
					formulario.getNroAgentR() != 0 //&&
					//!formulario.getCostoUnitAgentR().equals(BigDecimal.ZERO) &&
					//!formulario.getDesplPersonalR().equals(BigDecimal.ZERO) &&
					//!formulario.getActivExtraordR().equals(BigDecimal.ZERO) 
					){*/
				logger.info("se modificara RURAL");
				detalleRural.setNumeroEmpadronados(formulario.getNroEmpadR());
				detalleRural.setCostoEstandarUnitarioEmpad(formulario.getCostoUnitEmpadR());
				BigDecimal costoTotalEmpadR = detalleRural.getCostoEstandarUnitarioEmpad().multiply(new BigDecimal(detalleRural.getNumeroEmpadronados()));
				detalleRural.setCostoTotalEmpadronamiento(costoTotalEmpadR);
				detalleRural.setNumeroAgentesAutorizGlp(formulario.getNroAgentR());
				detalleRural.setCostoEstandarUnitAgAutGlp(formulario.getCostoUnitAgentR());
				BigDecimal costoTotalAgentR = detalleRural.getCostoEstandarUnitAgAutGlp().multiply(new BigDecimal(detalleRural.getNumeroAgentesAutorizGlp()));
				detalleRural.setCostoTotalGestRedAgGlp(costoTotalAgentR);
				BigDecimal totalDesplR = formulario.getDesplPersonalR();
				detalleRural.setTotalDesplazamientoPersonal(totalDesplR);
				BigDecimal totalActivR = formulario.getActivExtraordR();
				detalleRural.setTotalActividadesExtraord(totalActivR);
				BigDecimal totalRural = detalleRural.getCostoTotalEmpadronamiento()
						.add(detalleRural.getCostoTotalGestRedAgGlp())
						.add(detalleRural.getTotalDesplazamientoPersonal())
						.add(detalleRural.getTotalActividadesExtraord());
				
				detalleRural.setUsuarioActualizacion(formulario.getUsuario());
				detalleRural.setTerminalActualizacion(formulario.getTerminal());
				detalleRural.setFechaActualizacion(hoy);
				
				//grabar detalle
				logger.info("totalrural modifica"+totalRural);
				total = total.add(totalRural);				
				logger.info("total hasta rural modifica"+total);				
				lista.add(detalleRural);
			}
			//PROVINCIA
			if( detalleProvincia != null ){
			/*if( detalleProvincia != null &&
					formulario.getNroEmpadP() != 0 &&
					//!formulario.getCostoUnitEmpadP().equals(BigDecimal.ZERO) &&
					formulario.getNroAgentP() != 0 //&&
					//!formulario.getCostoUnitAgentP().equals(BigDecimal.ZERO) &&
					//!formulario.getDesplPersonalP().equals(BigDecimal.ZERO) &&
					//!formulario.getActivExtraordP().equals(BigDecimal.ZERO) 
					){*/
				logger.info("se modificara PROVINCIA");
				detalleProvincia.setNumeroEmpadronados(formulario.getNroEmpadP());
				detalleProvincia.setCostoEstandarUnitarioEmpad(formulario.getCostoUnitEmpadP());
				BigDecimal costoTotalEmpadP = detalleProvincia.getCostoEstandarUnitarioEmpad().multiply(new BigDecimal(detalleProvincia.getNumeroEmpadronados()));
				detalleProvincia.setCostoTotalEmpadronamiento(costoTotalEmpadP);
				detalleProvincia.setNumeroAgentesAutorizGlp(formulario.getNroAgentP());
				detalleProvincia.setCostoEstandarUnitAgAutGlp(formulario.getCostoUnitAgentP());
				BigDecimal costoTotalAgentP = detalleProvincia.getCostoEstandarUnitAgAutGlp().multiply(new BigDecimal(detalleProvincia.getNumeroAgentesAutorizGlp()));
				detalleProvincia.setCostoTotalGestRedAgGlp(costoTotalAgentP);
				BigDecimal totalDesplP = formulario.getDesplPersonalP();
				detalleProvincia.setTotalDesplazamientoPersonal(totalDesplP);
				BigDecimal totalActivP = formulario.getActivExtraordP();
				detalleProvincia.setTotalActividadesExtraord(totalActivP);
				BigDecimal totalProvincia = detalleProvincia.getCostoTotalEmpadronamiento()
						.add(detalleProvincia.getCostoTotalGestRedAgGlp())
						.add(detalleProvincia.getTotalDesplazamientoPersonal())
						.add(detalleProvincia.getTotalActividadesExtraord());
			
				detalleProvincia.setUsuarioActualizacion(formulario.getUsuario());
				detalleProvincia.setTerminalActualizacion(formulario.getTerminal());
				detalleProvincia.setFechaActualizacion(hoy);
				
				//grabar detalle
				logger.info("totalprovincia modificar"+totalProvincia);
				total = total.add(totalProvincia);				
				logger.info("total hasta provincia modificar"+total);				
				lista.add(detalleProvincia);
			}
			//LIMA
			if( detalleLima != null ){
			/*if( detalleLima != null &&
					formulario.getNroEmpadL() != 0 &&
					//!formulario.getCostoUnitEmpadL().equals(BigDecimal.ZERO) &&
					formulario.getNroAgentL() != 0 //&&
					//!formulario.getCostoUnitAgentL().equals(BigDecimal.ZERO) &&
					//!formulario.getDesplPersonalL().equals(BigDecimal.ZERO) &&
					//!formulario.getActivExtraordL().equals(BigDecimal.ZERO) 
					){*/
				logger.info("se modificara LIMA");
				detalleLima.setNumeroEmpadronados(formulario.getNroEmpadL());
				detalleLima.setCostoEstandarUnitarioEmpad(formulario.getCostoUnitEmpadL());
				BigDecimal costoTotalEmpadL = detalleLima.getCostoEstandarUnitarioEmpad().multiply(new BigDecimal(detalleLima.getNumeroEmpadronados()));
				detalleLima.setCostoTotalEmpadronamiento(costoTotalEmpadL);
				detalleLima.setNumeroAgentesAutorizGlp(formulario.getNroAgentL());
				detalleLima.setCostoEstandarUnitAgAutGlp(formulario.getCostoUnitAgentL());
				BigDecimal costoTotalAgentL = detalleLima.getCostoEstandarUnitAgAutGlp().multiply(new BigDecimal(detalleLima.getNumeroAgentesAutorizGlp()));
				detalleLima.setCostoTotalGestRedAgGlp(costoTotalAgentL);
				BigDecimal totalDesplL = formulario.getDesplPersonalL();
				detalleLima.setTotalDesplazamientoPersonal(totalDesplL);
				BigDecimal totalActivL = formulario.getActivExtraordL();
				detalleLima.setTotalActividadesExtraord(totalActivL);
				BigDecimal totalLima = detalleLima.getCostoTotalEmpadronamiento()
						.add(detalleLima.getCostoTotalGestRedAgGlp())
						.add(detalleLima.getTotalDesplazamientoPersonal())
						.add(detalleLima.getTotalActividadesExtraord());				
				detalleLima.setUsuarioActualizacion(formulario.getUsuario());
				detalleLima.setTerminalActualizacion(formulario.getTerminal());
				detalleLima.setFechaActualizacion(hoy);
				
				//grabar detalle
				logger.info("totallima modificar"+totalLima);
				total = total.add(totalLima);				
				logger.info("total hasta lima modificar"+total);				
				lista.add(detalleLima);
			}
			logger.info("total de total"+total);
			fiseFormato12AC.setTotalAReconocer(total);	
		
			fiseFormato12AC.setUsuarioActualizacion(formulario.getUsuario());
			fiseFormato12AC.setTerminalActualizacion(formulario.getTerminal());
			fiseFormato12AC.setFechaActualizacion(hoy);
		
			if( FiseConstants.TIPOARCHIVO_XLS.equals(formulario.getTipoArchivo()) ){
				fiseFormato12AC.setArchivoExcel(formulario.getNombreArchivo());
			}else if( FiseConstants.TIPOARCHIVO_TXT.equals(formulario.getTipoArchivo()) ){
				fiseFormato12AC.setArchivoTexto(formulario.getNombreArchivo());
			}
			
			formato12ACDao.modificarFormato12AC(fiseFormato12AC);
			
			for (FiseFormato12AD detalle : lista) {
				formato12ADDao.modificarFormato12AD(detalle);
			}
			dto= fiseFormato12AC;
			
		}	catch (Exception e) {
			logger.error("--error"+e.getMessage());
			e.printStackTrace();
			throw new Exception("Se produjo un error al actualizar los datos del Formato 12A");
		}		
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
			List<FiseFormato12ADOb> listaObservacion = formato12AObsDao.listarFormato12ADObByFormato12AD(detalle);
			for (FiseFormato12ADOb observacion : listaObservacion) {
				formato12AObsDao.eliminarFormato12ADOb(observacion);
			}
			formato12ADDao.eliminarFormato12AD(detalle);
		}
		formato12ACDao.eliminarFormato12AC(fiseFormato12AC);
		//cambios elozano eliminar fiseArchivoCab y su detalle
		String valor = eliminarArchivoSustentoCab(fiseFormato12AC);
		logger.info("Valor al eliminar archivos de sustento si es 1= OK caso contrario error:  "+valor); 
	}
	
	//@Transactional
	private String eliminarArchivoSustentoCab(FiseFormato12AC f){
		String valor = "0";
		List<FiseArchivosCab> listaCab =null;
		List<FiseArchivosDet> listaDet = null;
		try {
			BigDecimal anioEjec = null;
			BigDecimal mesEjec = null;
			if(f.getId().getAnoEjecucionGasto()!=0){
				anioEjec = new 	BigDecimal(f.getId().getAnoEjecucionGasto());
			}
            if(f.getId().getMesEjecucionGasto()!=0){
            	mesEjec = new BigDecimal(f.getId().getMesEjecucionGasto());
			}			
			listaCab = archivoSustentoDao.listaFiseArchivosCabMensual(f.getId().getCodEmpresa(),
					f.getId().getAnoPresentacion(), f.getId().getMesPresentacion(),
					anioEjec, mesEjec, f.getId().getEtapa(),FiseConstants.NOMBRE_FORMATO_12A);
			logger.info("tamaño de lista cabecera por formato a borrar:  "+listaCab.size());
			if(listaCab!=null && listaCab.size()>0){				
				listaDet = archivoSustentoDao.buscarFiseArchivosDet(listaCab.get(0).getCorrelativo());
				for(FiseArchivosDet d: listaDet){					
					archivoSustentoDao.eliminarFiseArchivosDet(d);
				}
				archivoSustentoDao.eliminarFiseArchivosCab(listaCab.get(0)); 
				valor = "1";
			}		
		} catch (Exception e) {
			//e.printStackTrace();
			valor = "0";
		}finally{
			if(listaCab!=null){
				listaCab = null;	
			}
			if(listaDet!=null){
				listaDet = null;	
			}
		}
		return valor;
	}
	
	
	
	
	@Override
	@Transactional
	public boolean existeFormato12AC(FiseFormato12AC fiseFormato12AC) {
		return formato12ACDao.existeFormato12AC(fiseFormato12AC);
	}

	@Override
	@Transactional
	public List<FiseFormato12AC> buscarFormato12AC(String codEmpresa, long anioDesde, long mesDesde, long anioHasta, long mesHasta, String etapa){
		
		List<FiseFormato12AC> lista = null;
		lista = formato12ACDao.buscarFormato12AC(codEmpresa, anioDesde, mesDesde, anioHasta, mesHasta, etapa);
		for (FiseFormato12AC formato : lista) {
			formato.setFiseFormato12ADs(formato12ADDao.listarFormato12ADByFormato12AC(formato));
		}
		return lista;
	}
	
	@Override
	public Formato12ACBean estructurarFormato12ABeanByFiseFormato12AC(FiseFormato12AC formato){
		
		Formato12ACBean formato12ABean = new Formato12ACBean();
		
		//setamos los valores en el bean
		//formato12ABean.setDescEmpresa(mapaEmpresa.get(formato.getId().getCodEmpresa()));
		formato12ABean.setAnioPresent(formato.getId().getAnoPresentacion());
		//formato12ABean.setDescMesPresentacion(listaMes.get(formato.getId().getMesPresentacion()));
		formato12ABean.setAnioEjecuc(formato.getId().getAnoEjecucionGasto());
		//formato12ABean.setDescMesEjecucion(listaMes.get(formato.getId().getMesEjecucionGasto()));
    	//
		BigDecimal totalEmpadronamiento = new BigDecimal(0);
		BigDecimal totalAgentes = new BigDecimal(0);
		BigDecimal totalDesplazamiento = new BigDecimal(0);
		BigDecimal totalActividades = new BigDecimal(0);
		
    	for (FiseFormato12AD detalle : formato.getFiseFormato12ADs()) {
			if( FiseConstants.ZONABENEF_RURAL_COD == detalle.getId().getIdZonaBenef() ){
				formato12ABean.setNroEmpadR(detalle.getNumeroEmpadronados());
				formato12ABean.setCostoUnitEmpadR(detalle.getCostoEstandarUnitarioEmpad());
				BigDecimal costoTotalEmpad = detalle.getCostoEstandarUnitarioEmpad().multiply(new BigDecimal(detalle.getNumeroEmpadronados()));
				formato12ABean.setCostoTotalEmpadR(costoTotalEmpad);
			
				formato12ABean.setNroAgentR(detalle.getNumeroAgentesAutorizGlp());
				formato12ABean.setCostoUnitAgentR(detalle.getCostoEstandarUnitAgAutGlp());
				BigDecimal costoTotalAgent = detalle.getCostoEstandarUnitAgAutGlp().multiply(new BigDecimal(detalle.getNumeroAgentesAutorizGlp()));
				formato12ABean.setCostoTotalAgentR(costoTotalAgent);
				
				formato12ABean.setDesplPersonalR(detalle.getTotalDesplazamientoPersonal());
				formato12ABean.setActivExtraordR(detalle.getTotalActividadesExtraord());
				//
				totalEmpadronamiento = totalEmpadronamiento.add(formato12ABean.getCostoTotalEmpadR());
				totalAgentes = totalAgentes.add(formato12ABean.getCostoTotalAgentR());
				totalDesplazamiento = totalDesplazamiento.add(formato12ABean.getDesplPersonalR());
				totalActividades = totalActividades.add(formato12ABean.getActivExtraordR());
			}else if( FiseConstants.ZONABENEF_PROVINCIA_COD == detalle.getId().getIdZonaBenef() ){
				formato12ABean.setNroEmpadP(detalle.getNumeroEmpadronados());
				formato12ABean.setCostoUnitEmpadP(detalle.getCostoEstandarUnitarioEmpad());
				BigDecimal costoTotalEmpad = detalle.getCostoEstandarUnitarioEmpad().multiply(new BigDecimal(detalle.getNumeroEmpadronados()));
				formato12ABean.setCostoTotalEmpadP(costoTotalEmpad);
			
				formato12ABean.setNroAgentP(detalle.getNumeroAgentesAutorizGlp());
				formato12ABean.setCostoUnitAgentP(detalle.getCostoEstandarUnitAgAutGlp());
				BigDecimal costoTotalAgent = detalle.getCostoEstandarUnitAgAutGlp().multiply(new BigDecimal(detalle.getNumeroAgentesAutorizGlp()));
				formato12ABean.setCostoTotalAgentP(costoTotalAgent);
				
				formato12ABean.setDesplPersonalP(detalle.getTotalDesplazamientoPersonal());
				formato12ABean.setActivExtraordP(detalle.getTotalActividadesExtraord());
				//
				totalEmpadronamiento = totalEmpadronamiento.add(formato12ABean.getCostoTotalEmpadP());
				totalAgentes = totalAgentes.add(formato12ABean.getCostoTotalAgentP());
				totalDesplazamiento = totalDesplazamiento.add(formato12ABean.getDesplPersonalP());
				totalActividades = totalActividades.add(formato12ABean.getActivExtraordP());
			}else if( FiseConstants.ZONABENEF_LIMA_COD == detalle.getId().getIdZonaBenef() ){
				formato12ABean.setNroEmpadL(detalle.getNumeroEmpadronados());
				formato12ABean.setCostoUnitEmpadL(detalle.getCostoEstandarUnitarioEmpad());
				BigDecimal costoTotalEmpad = detalle.getCostoEstandarUnitarioEmpad().multiply(new BigDecimal(detalle.getNumeroEmpadronados()));
				formato12ABean.setCostoTotalEmpadL(costoTotalEmpad);
			
				formato12ABean.setNroAgentL(detalle.getNumeroAgentesAutorizGlp());
				formato12ABean.setCostoUnitAgentL(detalle.getCostoEstandarUnitAgAutGlp());
				BigDecimal costoTotalAgent = detalle.getCostoEstandarUnitAgAutGlp().multiply(new BigDecimal(detalle.getNumeroAgentesAutorizGlp()));
				formato12ABean.setCostoTotalAgentL(costoTotalAgent);
				
				formato12ABean.setDesplPersonalL(detalle.getTotalDesplazamientoPersonal());
				formato12ABean.setActivExtraordL(detalle.getTotalActividadesExtraord());
				//
				totalEmpadronamiento = totalEmpadronamiento.add(formato12ABean.getCostoTotalEmpadL());
				totalAgentes = totalAgentes.add(formato12ABean.getCostoTotalAgentL());
				totalDesplazamiento = totalDesplazamiento.add(formato12ABean.getDesplPersonalL());
				totalActividades = totalActividades.add(formato12ABean.getActivExtraordL());
			}
		}
    	formato12ABean.setTotalCostoTotalEmpad(totalEmpadronamiento);
    	formato12ABean.setTotalCostoTotalAgent(totalAgentes);
    	formato12ABean.setTotalDesplPersonal(totalDesplazamiento);
    	formato12ABean.setTotalActivExtraord(totalActividades);
    	//
    	formato12ABean.setTotalGeneral(formato.getTotalAReconocer());
		
		return formato12ABean;
	}
	
	@Override
	public HashMap<String, Object> mapearParametrosFormato12A(Formato12ACBean formato12ABean){
		
		HashMap<String, Object> mapJRParams = new HashMap<String, Object>();
		
		mapJRParams.put(FiseConstants.PARAM_DESC_EMPRESA_F12A, formato12ABean.getDescEmpresa());
		mapJRParams.put(FiseConstants.PARAM_ANO_PRES_F12A, formato12ABean.getAnioPresent());
		mapJRParams.put(FiseConstants.PARAM_DESC_MES_PRES_F12A, formato12ABean.getDescMesPresentacion());
		mapJRParams.put(FiseConstants.PARAM_ANO_EJEC_F12A, formato12ABean.getAnioEjecuc());
		mapJRParams.put(FiseConstants.PARAM_DESC_MES_EJEC_F12A, formato12ABean.getDescMesEjecucion());
		//
		mapJRParams.put(FiseConstants.PARAM_NRO_EMPAD_R_F12A, formato12ABean.getNroEmpadR());
		mapJRParams.put(FiseConstants.PARAM_CU_EMPAD_R_F12A, formato12ABean.getCostoUnitEmpadR());
		mapJRParams.put(FiseConstants.PARAM_CT_EMPAD_R_F12A, formato12ABean.getCostoTotalEmpadR());
		mapJRParams.put(FiseConstants.PARAM_NRO_AGENT_R_F12A, formato12ABean.getNroAgentR());
		mapJRParams.put(FiseConstants.PARAM_CU_AGENT_R_F12A, formato12ABean.getCostoUnitAgentR());
		mapJRParams.put(FiseConstants.PARAM_CT_AGENT_R_F12A, formato12ABean.getCostoTotalAgentR());
		mapJRParams.put(FiseConstants.PARAM_DESPL_PERS_R_F12A, formato12ABean.getDesplPersonalR());
		mapJRParams.put(FiseConstants.PARAM_ACTIV_EXTR_R_F12A, formato12ABean.getActivExtraordR());
		//
		mapJRParams.put(FiseConstants.PARAM_NRO_EMPAD_P_F12A, formato12ABean.getNroEmpadP());
		mapJRParams.put(FiseConstants.PARAM_CU_EMPAD_P_F12A, formato12ABean.getCostoUnitEmpadP());
		mapJRParams.put(FiseConstants.PARAM_CT_EMPAD_P_F12A, formato12ABean.getCostoTotalEmpadP());
		mapJRParams.put(FiseConstants.PARAM_NRO_AGENT_P_F12A, formato12ABean.getNroAgentP());
		mapJRParams.put(FiseConstants.PARAM_CU_AGENT_P_F12A, formato12ABean.getCostoUnitAgentP());
		mapJRParams.put(FiseConstants.PARAM_CT_AGENT_P_F12A, formato12ABean.getCostoTotalAgentP());
		mapJRParams.put(FiseConstants.PARAM_DESPL_PERS_P_F12A, formato12ABean.getDesplPersonalP());
		mapJRParams.put(FiseConstants.PARAM_ACTIV_EXTR_P_F12A, formato12ABean.getActivExtraordP());
		//
		mapJRParams.put(FiseConstants.PARAM_NRO_EMPAD_L_F12A, formato12ABean.getNroEmpadL());
		mapJRParams.put(FiseConstants.PARAM_CU_EMPAD_L_F12A, formato12ABean.getCostoUnitEmpadL());
		mapJRParams.put(FiseConstants.PARAM_CT_EMPAD_L_F12A, formato12ABean.getCostoTotalEmpadL());
		mapJRParams.put(FiseConstants.PARAM_NRO_AGENT_L_F12A, formato12ABean.getNroAgentL());
		mapJRParams.put(FiseConstants.PARAM_CU_AGENT_L_F12A, formato12ABean.getCostoUnitAgentL());
		mapJRParams.put(FiseConstants.PARAM_CT_AGENT_L_F12A, formato12ABean.getCostoTotalAgentL());
		mapJRParams.put(FiseConstants.PARAM_DESPL_PERS_L_F12A, formato12ABean.getDesplPersonalL());
		mapJRParams.put(FiseConstants.PARAM_ACTIV_EXTR_L_F12A, formato12ABean.getActivExtraordL());
		//
		mapJRParams.put(FiseConstants.PARAM_TOTAL_EMPAD_F12A,formato12ABean.getTotalCostoTotalEmpad());
		mapJRParams.put(FiseConstants.PARAM_TOTAL_AGENT_F12A, formato12ABean.getTotalCostoTotalAgent());
		mapJRParams.put(FiseConstants.PARAM_TOTAL_DESPLAZ_F12A, formato12ABean.getTotalDesplPersonal());
		mapJRParams.put(FiseConstants.PARAM_TOTAL_ACTIV_F12A, formato12ABean.getTotalActivExtraord());
		//
		mapJRParams.put(FiseConstants.PARAM_TOTAL_GENERAL_F12A, formato12ABean.getTotalGeneral());
		
		return mapJRParams;
	}
	
	@Override
	@Transactional
	public int obtenerSecuencia() {
		return formato12ACDao.obtenerSecuencia();
	}
	
	@Override
	@Transactional(propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
	public int validarFormato12A(FiseFormato12AC fiseFormato12AC, String tipoFormato, String usuario, String terminal) {
		return formato12AObsDao.validarFormato12A(fiseFormato12AC, tipoFormato, usuario, terminal);
	}

	@Override
	@Transactional
	public List<FiseFormato12ADOb> listarFormato12ADObByFormato12AD(FiseFormato12AD formato12AD){
		return formato12AObsDao.listarFormato12ADObByFormato12AD(formato12AD); 
	}
	
	@Override
	@Transactional(propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
	public String modificarEnvioDefinitivoFormato12AC(String user,String terminal, 
			FiseFormato12AC fiseFormato12AC) throws Exception {		
		String  valor = "0";		
		try{
			fiseFormato12AC.setFechaEnvioDefinitivo(FechaUtil.obtenerFechaActual());
			fiseFormato12AC.setUsuarioActualizacion(user);
			fiseFormato12AC.setTerminalActualizacion(terminal);
			fiseFormato12AC.setFechaActualizacion(FechaUtil.obtenerFechaActual());
			formato12ACDao.modificarFormato12AC(fiseFormato12AC);
			valor = "1";//valor exitoso
		} catch (Exception e) {
			logger.error("--error"+e.getMessage());
			valor = "0";
			e.printStackTrace();			
		}
		return valor;
	}
	
	
	@Override
	@Transactional(propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
	public void eliminarObservaciones12A(List<FiseFormato12ADOb> listaObs) throws Exception {	
		for (FiseFormato12ADOb observacion : listaObs) {			
			formato12AObsDao.eliminarFormato12ADOb(observacion);
		}
	} 
	
		
	@Override
	@Transactional(propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
	public String insertarObservacion12A(String codEmpresa,long anioPres,long mesPres,
			long anioEjec,long mesEjec,String etapa,long idZona, 
			String desObservacion,String user,String terminal,
			String idObsExistente,String tipoObservacion) throws Exception{
		String valor = "0";
		FiseObservacion observacion = null;
		FiseFormato12ADObPK pk = null;
		FiseFormato12ADOb obs = null;
		try {
			long maxItemObs = formato12AObsDao.buscarMaximoItemObs12A(codEmpresa, anioPres, 
					mesPres, anioEjec, mesEjec, etapa, idZona);
			logger.info("maximo item observacion:  "+maxItemObs); 
			if(FiseConstants.TIPO_OBSERVACION_MANUAL.equals(tipoObservacion)){
				String idObservacion = fiseObservacionDao.obtenerIdObservacion();
				logger.info("id observacion:  "+idObservacion); 
				observacion = new FiseObservacion();
				observacion.setIdObservacion(idObservacion); 
				observacion.setDescripcion(desObservacion);
				observacion.setOrigen(FiseConstants.TIPO_OBSERVACION_MANUAL); 
				observacion.setUsuarioCreacion(user);
				observacion.setTerminalCreacion(terminal); 
				observacion.setFechaCreacion(FechaUtil.obtenerFechaActual()); 
				fiseObservacionDao.insertarFiseObservacion(observacion);	
			}else{
				observacion = fiseObservacionDao.obtenerFiseObservacion(idObsExistente);
			}
			pk = new FiseFormato12ADObPK();
			pk.setCodEmpresa(codEmpresa);
			pk.setAnoPresentacion(anioPres);
			pk.setMesPresentacion(mesPres);
			pk.setAnoEjecucionGasto(anioEjec);
			pk.setMesEjecucionGasto(mesEjec);
			pk.setEtapa(etapa);
			pk.setIdZonaBenef(idZona);		
			pk.setItemObservacion(maxItemObs); 	
			obs = new FiseFormato12ADOb();
			obs.setId(pk);
			obs.setFechaCreacion(FechaUtil.obtenerFechaActual());			
			obs.setFiseObservacion(observacion);
			obs.setUsuarioCreacion(user);
			obs.setTerminalCreacion(terminal);		
			formato12AObsDao.insertarFiseFormato12AObs(obs); 
			valor = "1";
		} catch (Exception e) {
			valor = "0";
			e.printStackTrace();
		}finally{
			if(observacion!=null){
				observacion=null;
			}
			if(pk!=null){
				pk=null;
			}
			if(obs!=null){
				obs=null;
			}
		}
		return valor;
	}
	
	
	@Override
	@Transactional(propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
	public String eliminarObservacion12A(String codEmpresa,long anioPres,long mesPres,
			long anioEjec,long mesEjec,String etapa,long idZona, 
			String idObservacion,long itemObservacion) throws Exception{
		String valor = "0";
		FiseObservacion observacion = null;
		FiseFormato12ADObPK pk = null;	
		FiseFormato12ADOb obs = null;
		try {						
			pk = new FiseFormato12ADObPK();
			pk.setCodEmpresa(codEmpresa);
			pk.setAnoPresentacion(anioPres);
			pk.setMesPresentacion(mesPres);
			pk.setAnoEjecucionGasto(anioEjec);
			pk.setMesEjecucionGasto(mesEjec);
			pk.setEtapa(etapa);
			pk.setIdZonaBenef(idZona);		
			pk.setItemObservacion(itemObservacion); 	
			obs = formato12AObsDao.obtenerFiseFormato12ADOb(pk);					
			formato12AObsDao.eliminarFormato12ADOb(obs); 
			//observacion = fiseObservacionDao.obtenerFiseObservacion(idObservacion);			
			//fiseObservacionDao.eliminarFiseObservacion(observacion);
			valor = "1";
		} catch (Exception e) {
			valor = "0";
			e.printStackTrace();
		}finally{
			if(observacion!=null){
				observacion=null;
			}
			if(pk!=null){
				pk=null;
			}
			if(obs!=null){
				obs=null;
			}
		}
		return valor;
	}
	
	
	
	
}
