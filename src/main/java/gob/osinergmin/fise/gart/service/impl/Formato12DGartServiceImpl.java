package gob.osinergmin.fise.gart.service.impl;

import gob.osinergmin.fise.bean.Formato12DCBean;
import gob.osinergmin.fise.constant.FiseConstants;
import gob.osinergmin.fise.dao.ArchivoSustentoDao;
import gob.osinergmin.fise.dao.CommonDao;
import gob.osinergmin.fise.dao.FiseGrupoInformacionDao;
import gob.osinergmin.fise.dao.FiseObservacionDao;
import gob.osinergmin.fise.dao.FiseZonaBenefDao;
import gob.osinergmin.fise.dao.Formato12DCDao;
import gob.osinergmin.fise.dao.Formato12DDDao;
import gob.osinergmin.fise.dao.Formato12DDObDao;
import gob.osinergmin.fise.domain.FiseArchivosCab;
import gob.osinergmin.fise.domain.FiseArchivosDet;
import gob.osinergmin.fise.domain.FiseFormato12DC;
import gob.osinergmin.fise.domain.FiseFormato12DCPK;
import gob.osinergmin.fise.domain.FiseFormato12DD;
import gob.osinergmin.fise.domain.FiseFormato12DDOb;
import gob.osinergmin.fise.domain.FiseFormato12DDObPK;
import gob.osinergmin.fise.domain.FiseFormato12DDPK;
import gob.osinergmin.fise.domain.FiseGrupoInformacion;
import gob.osinergmin.fise.domain.FiseObservacion;
import gob.osinergmin.fise.domain.FiseZonaBenef;
import gob.osinergmin.fise.gart.service.Formato12DGartService;
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

@Service(value="formato12DGartServiceImpl")
public class Formato12DGartServiceImpl implements Formato12DGartService {
	
	Logger logger=Logger.getLogger(Formato12DGartServiceImpl.class);
	
	@Autowired
	@Qualifier("formato12DCDaoImpl")
	private Formato12DCDao formato12DCDao;
	
	@Autowired
	@Qualifier("formato12DDDaoImpl")
	private Formato12DDDao formato12DDDao;
	
	@Autowired
	@Qualifier("fiseZonaBenefDaoImpl")
	private FiseZonaBenefDao zonaBenefDao;
	
	@Autowired
	@Qualifier("formato12DDObDaoImpl")
	private Formato12DDObDao formato12DDObDao;
	
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
	
	@Override
	@Transactional
	public FiseFormato12DC obtenerFormato12DCByPK(FiseFormato12DCPK fiseFormato12DCPK) {
		FiseFormato12DC formato = null;
		formato = formato12DCDao.obtenerFormato12DCByPK(fiseFormato12DCPK);
		if(formato != null){
			List<FiseFormato12DD> lista = formato12DDDao.listarFormato12DDByFormato12DC(formato);
			long periodoEjecucion=0;
			for (FiseFormato12DD fiseFormato12DD : lista) {
				fiseFormato12DD.setCodEmpresaReport(fiseFormato12DD.getId().getCodEmpresa());
				fiseFormato12DD.setAnoPresentacionReport(fiseFormato12DD.getId().getAnoPresentacion());
				fiseFormato12DD.setMesPresentacionReport(fiseFormato12DD.getId().getMesPresentacion());
				fiseFormato12DD.setEtapaReport(fiseFormato12DD.getId().getEtapa());
				fiseFormato12DD.setAnoEjecucionGastoReport(fiseFormato12DD.getId().getAnoEjecucionGasto());
				fiseFormato12DD.setMesEjecucionGastoReport(fiseFormato12DD.getId().getMesEjecucionGasto());
				fiseFormato12DD.setNumeroItemEtapaReport(fiseFormato12DD.getId().getNumeroItemEtapa());
				fiseFormato12DD.setEtapaEjecucionReport(fiseFormato12DD.getId().getEtapaEjecucion());
				if( periodoEjecucion==0 ){
					periodoEjecucion = fiseFormato12DD.getId().getAnoEjecucionGasto()*100+fiseFormato12DD.getId().getMesEjecucionGasto();
					formato.setAnoEjecucionView(fiseFormato12DD.getId().getAnoEjecucionGasto());
					formato.setMesEjecucionView(fiseFormato12DD.getId().getMesEjecucionGasto());
				}else if( fiseFormato12DD.getId().getAnoEjecucionGasto()*100+fiseFormato12DD.getId().getMesEjecucionGasto() > periodoEjecucion ){
					periodoEjecucion = fiseFormato12DD.getId().getAnoEjecucionGasto()*100+fiseFormato12DD.getId().getMesEjecucionGasto();
					formato.setAnoEjecucionView(fiseFormato12DD.getId().getAnoEjecucionGasto());
					formato.setMesEjecucionView(fiseFormato12DD.getId().getMesEjecucionGasto());
				}
			}
			formato.setFiseFormato12DDs(lista);
		}
		return formato;
	}
	
	@Override
	@Transactional
	public List<FiseFormato12DC> buscarFormato12DC(String codEmpresa, long anioDesde, long mesDesde, long anioHasta, long mesHasta, String etapa){
		
		List<FiseFormato12DC> lista = null;
		lista = formato12DCDao.buscarFormato12DC(codEmpresa, anioDesde, mesDesde, anioHasta, mesHasta, etapa);
		for (FiseFormato12DC formato : lista) {
			formato.setFiseFormato12DDs(formato12DDDao.listarFormato12DDByFormato12DC(formato));
		}
		return lista;
	}
	
	public List<FiseFormato12DD> listarFormato12DDByFormato12DC(FiseFormato12DC formato12DC) {
		//Obtener datos de PK del detalle(se inserto 8 campos de PK en el objeto FiseFormato12DD 
		//para tener todos los campos en un solo objeto y poder leer los campos en iReport)
		List<FiseFormato12DD> lista = null;
		lista = formato12DDDao.listarFormato12DDByFormato12DC(formato12DC);
		for (FiseFormato12DD fiseFormato12DD : lista) {
			fiseFormato12DD.setCodEmpresaReport(fiseFormato12DD.getId().getCodEmpresa());
			fiseFormato12DD.setAnoPresentacionReport(fiseFormato12DD.getId().getAnoPresentacion());
			fiseFormato12DD.setMesPresentacionReport(fiseFormato12DD.getId().getMesPresentacion());
			fiseFormato12DD.setEtapaReport(fiseFormato12DD.getId().getEtapa());
			fiseFormato12DD.setAnoEjecucionGastoReport(fiseFormato12DD.getId().getAnoEjecucionGasto());
			fiseFormato12DD.setMesEjecucionGastoReport(fiseFormato12DD.getId().getMesEjecucionGasto());
			fiseFormato12DD.setNumeroItemEtapaReport(fiseFormato12DD.getId().getNumeroItemEtapa());
			fiseFormato12DD.setEtapaEjecucionReport(fiseFormato12DD.getId().getEtapaEjecucion());
		}
		return lista;
	}
	
	@Override
	@Transactional(propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
	public FiseFormato12DC registrarFormato12DCregistrarFormato12DD(Formato12DCBean formulario) throws Exception {
		
		FiseFormato12DC dto = null;
		
		try {
			Date hoy = FechaUtil.obtenerFechaActual();
			String horaActual = FechaUtil.getHoraActual();
			
			FiseFormato12DC fiseFormato12DC = new FiseFormato12DC();
			//guardar el pk
			FiseFormato12DCPK id = new FiseFormato12DCPK();
			id.setCodEmpresa(formulario.getCodigoEmpresa());
			id.setAnoPresentacion(formulario.getAnioPresentacion());
			id.setMesPresentacion(formulario.getMesPresentacion());
			//id.setEtapa(FiseConstants.ETAPA_SOLICITUD);
			id.setEtapa(formulario.getEtapa());
			if( FiseConstants.TIPOARCHIVO_XLS.equals(formulario.getTipoArchivo()) ){
				fiseFormato12DC.setNombreArchivoExcel(formulario.getNombreArchivo());
			}else if( FiseConstants.TIPOARCHIVO_TXT.equals(formulario.getTipoArchivo()) ){
				fiseFormato12DC.setNombreArchivoTexto(formulario.getNombreArchivo());
			}/*else{
				id.setEtapa(formulario.getEtapa());
			}*/
			
			fiseFormato12DC.setId(id);
			
			FiseGrupoInformacion grupoInfo = null;
			long idGrupoInf = commonDao.obtenerIdGrupoInformacion(formulario.getAnioPresentacion(), formulario.getMesPresentacion(), FiseConstants.FRECUENCIA_MENSUAL_DESCRIPCION); 
			if(idGrupoInf!=0){
				grupoInfo = fiseGrupoInformacionDao.obtenerFiseGrupoInformacionByPK(idGrupoInf);	
			}	
			fiseFormato12DC.setFiseGrupoInformacion(grupoInfo);
			
			List<FiseFormato12DD> lista = new ArrayList<FiseFormato12DD>();
			
			if( FiseConstants.ETAPA_EJECUCION_IMPLEMENTACION_COD == formulario.getEtapaEjecucion() ){
				
				if( formulario.getAnioEjecucion() != 0 ||
						formulario.getMesEjecucion() != 0 ||
						formulario.getNroItemEtapa() != 0 ||
						!formulario.getCodUbigeo().equals(FiseConstants.BLANCO) ||
						!formulario.getLocalidad().equals(FiseConstants.BLANCO) ||
						formulario.getZonaBenef() != 0 ||
						!formulario.getCodCuentaContable().equals(FiseConstants.BLANCO) ||
						!formulario.getGasto().equals(FiseConstants.BLANCO) ||
						!formulario.getTipoGasto().equals(FiseConstants.BLANCO) ||
						!formulario.getTipoDocumento().equals(FiseConstants.BLANCO) ||
						!formulario.getRucEmpresa().equals(FiseConstants.BLANCO) ||
						!formulario.getSerieDocumento().equals(FiseConstants.BLANCO) ||
						!formulario.getNroDocumento().equals(FiseConstants.BLANCO) ||
						!formulario.getFechaAutorizacionString().equals(FiseConstants.BLANCO) ||
						!formulario.getNroDocAutorizacion().equals(FiseConstants.BLANCO) ||
						formulario.getCantidad() != 0 ||
						!formulario.getCostoUnitario().equals(BigDecimal.ZERO) 
						){
					
					FiseZonaBenef zonaBeneficiario = zonaBenefDao.obtenerFiseZonaBenefByPK(formulario.getZonaBenef());
					//
					FiseFormato12DD detalleImplementacion = new FiseFormato12DD();
					//pk
					FiseFormato12DDPK pkDetalle = new FiseFormato12DDPK();
					pkDetalle.setCodEmpresa(fiseFormato12DC.getId().getCodEmpresa());
					pkDetalle.setAnoPresentacion(fiseFormato12DC.getId().getAnoPresentacion());
					pkDetalle.setMesPresentacion(fiseFormato12DC.getId().getMesPresentacion());
					pkDetalle.setEtapa(fiseFormato12DC.getId().getEtapa());
					pkDetalle.setAnoEjecucionGasto(formulario.getAnioEjecucion());
					pkDetalle.setMesEjecucionGasto(formulario.getMesEjecucion());
					pkDetalle.setEtapaEjecucion(formulario.getEtapaEjecucion());
					//obtenemos el ultimo valor del itemetapa del detalle
					Long nroItemEtapa = formato12DDDao.obtenerMaximoItemEtapa(pkDetalle);
					pkDetalle.setNumeroItemEtapa(nroItemEtapa+1);
					detalleImplementacion.setId(pkDetalle);
					
					detalleImplementacion.setCodUbigeo(formulario.getCodUbigeo());
					detalleImplementacion.setDescripcionLocalidad(formulario.getLocalidad());
					detalleImplementacion.setIdZonaBenef(zonaBeneficiario.getIdZonaBenef());
					detalleImplementacion.setCodigoCuentaContaEde(formulario.getCodCuentaContable());
					detalleImplementacion.setDescripcionGasto(formulario.getGasto());
					detalleImplementacion.setIdTipGasto(formulario.getTipoGasto());
					if( !FiseConstants.BLANCO.equals(formulario.getTipoDocumento()) ){
						detalleImplementacion.setIdTipDocRef(formulario.getTipoDocumento());
						detalleImplementacion.setRucEmpresaEmiteDocRef(formulario.getRucEmpresa());
						detalleImplementacion.setSerieDocumentoReferencia(formulario.getSerieDocumento());
						detalleImplementacion.setNumeroDocumentoRefGasto(formulario.getNroDocumento());
					}
					detalleImplementacion.setFechaAutorizacionGasto(FechaUtil.fechaDate_DD_MM_YYYY_HHMMSS(formulario.getFechaAutorizacionString()+" "+horaActual));
					detalleImplementacion.setNumeroDocAutorizaGasto(formulario.getNroDocAutorizacion());
					detalleImplementacion.setCantidad(formulario.getCantidad());
					detalleImplementacion.setCostoUnitario(formulario.getCostoUnitario());
					
					
					BigDecimal total = new BigDecimal(0);
					total = detalleImplementacion.getCostoUnitario().multiply(new BigDecimal(detalleImplementacion.getCantidad()));
					detalleImplementacion.setTotalGeneral(total);
					
					//
					detalleImplementacion.setFiseFormato12DC(fiseFormato12DC);
					//
					detalleImplementacion.setUsuarioCreacion(formulario.getUsuario());
					detalleImplementacion.setTerminalCreacion(formulario.getTerminal());
					detalleImplementacion.setFechaCreacion(hoy);
					detalleImplementacion.setUsuarioActualizacion(formulario.getUsuario());
					detalleImplementacion.setTerminalActualizacion(formulario.getTerminal());
					detalleImplementacion.setFechaActualizacion(hoy);
					lista.add(detalleImplementacion);
					
				}
				
			}
			
			if( FiseConstants.ETAPA_EJECUCION_OPERATIVA_COD == formulario.getEtapaEjecucion() ){
				
				if( formulario.getAnioEjecucion() != 0 ||
						formulario.getMesEjecucion() != 0 ||
						formulario.getNroItemEtapa() != 0 ||
						!formulario.getCodUbigeo().equals(FiseConstants.BLANCO) ||
						!formulario.getLocalidad().equals(FiseConstants.BLANCO) ||
						formulario.getZonaBenef() != 0 ||
						!formulario.getCodCuentaContable().equals(FiseConstants.BLANCO) ||
						!formulario.getGasto().equals(FiseConstants.BLANCO) ||
						!formulario.getTipoGasto().equals(FiseConstants.BLANCO) ||
						!formulario.getTipoDocumento().equals(FiseConstants.BLANCO) ||
						!formulario.getRucEmpresa().equals(FiseConstants.BLANCO) ||
						!formulario.getSerieDocumento().equals(FiseConstants.BLANCO) ||
						!formulario.getNroDocumento().equals(FiseConstants.BLANCO) ||
						!formulario.getFechaAutorizacionString().equals(FiseConstants.BLANCO) ||
						!formulario.getNroDocAutorizacion().equals(FiseConstants.BLANCO) ||
						formulario.getCantidad() != 0 ||
						!formulario.getCostoUnitario().equals(BigDecimal.ZERO) 
						){
					
					FiseZonaBenef zonaBeneficiario = zonaBenefDao.obtenerFiseZonaBenefByPK(formulario.getZonaBenef());
					//
					FiseFormato12DD detalleOperativo = new FiseFormato12DD();
					//pk
					FiseFormato12DDPK pkDetalle = new FiseFormato12DDPK();
					pkDetalle.setCodEmpresa(fiseFormato12DC.getId().getCodEmpresa());
					pkDetalle.setAnoPresentacion(fiseFormato12DC.getId().getAnoPresentacion());
					pkDetalle.setMesPresentacion(fiseFormato12DC.getId().getMesPresentacion());
					pkDetalle.setEtapa(fiseFormato12DC.getId().getEtapa());
					pkDetalle.setAnoEjecucionGasto(formulario.getAnioEjecucion());
					pkDetalle.setMesEjecucionGasto(formulario.getMesEjecucion());
					pkDetalle.setEtapaEjecucion(formulario.getEtapaEjecucion());
					//obtenemos el ultimo valor del itemetapa del detalle
					Long nroItemEtapa = formato12DDDao.obtenerMaximoItemEtapa(pkDetalle);
					pkDetalle.setNumeroItemEtapa(nroItemEtapa+1);
					detalleOperativo.setId(pkDetalle);
					
					detalleOperativo.setCodUbigeo(formulario.getCodUbigeo());
					detalleOperativo.setDescripcionLocalidad(formulario.getLocalidad());
					detalleOperativo.setIdZonaBenef(zonaBeneficiario.getIdZonaBenef());
					detalleOperativo.setCodigoCuentaContaEde(formulario.getCodCuentaContable());
					detalleOperativo.setDescripcionGasto(formulario.getGasto());
					detalleOperativo.setIdTipGasto(formulario.getTipoGasto());
					if( !FiseConstants.BLANCO.equals(formulario.getTipoDocumento()) ){
						detalleOperativo.setIdTipDocRef(formulario.getTipoDocumento());
						detalleOperativo.setRucEmpresaEmiteDocRef(formulario.getRucEmpresa());
						detalleOperativo.setSerieDocumentoReferencia(formulario.getSerieDocumento());
						detalleOperativo.setNumeroDocumentoRefGasto(formulario.getNroDocumento());
					}
					detalleOperativo.setFechaAutorizacionGasto(FechaUtil.fechaDate_DD_MM_YYYY_HHMMSS(formulario.getFechaAutorizacionString()+" "+horaActual));
					detalleOperativo.setNumeroDocAutorizaGasto(formulario.getNroDocAutorizacion());
					detalleOperativo.setCantidad(formulario.getCantidad());
					detalleOperativo.setCostoUnitario(formulario.getCostoUnitario());
					
					
					BigDecimal total = new BigDecimal(0);
					total = detalleOperativo.getCostoUnitario().multiply(new BigDecimal(detalleOperativo.getCantidad()));
					detalleOperativo.setTotalGeneral(total);
					
					//
					detalleOperativo.setFiseFormato12DC(fiseFormato12DC);
					//
					detalleOperativo.setUsuarioCreacion(formulario.getUsuario());
					detalleOperativo.setTerminalCreacion(formulario.getTerminal());
					detalleOperativo.setFechaCreacion(hoy);
					detalleOperativo.setUsuarioActualizacion(formulario.getUsuario());
					detalleOperativo.setTerminalActualizacion(formulario.getTerminal());
					detalleOperativo.setFechaActualizacion(hoy);
					lista.add(detalleOperativo);
					
				}
	
			}
			
			fiseFormato12DC.setUsuarioActualizacion(formulario.getUsuario());
			fiseFormato12DC.setTerminalActualizacion(formulario.getTerminal());
			fiseFormato12DC.setFechaActualizacion(hoy);
			fiseFormato12DC.setUsuarioCreacion(formulario.getUsuario());
			fiseFormato12DC.setTerminalCreacion(formulario.getTerminal());
			fiseFormato12DC.setFechaCreacion(hoy);
			
			logger.info("aca se va  a guardar"+fiseFormato12DC);
			boolean existe = false;
			existe = formato12DCDao.existeFormato12DC(fiseFormato12DC);
			if(existe){
				throw new Exception("El Formato ya existe para la Distribuidora Eléctrica y Periodo a Declarar seleccionado");
			}else{
				formato12DCDao.registrarFormato12DC(fiseFormato12DC);
			}
			//add
			for (FiseFormato12DD detalle : lista) {
				formato12DDDao.registrarFormato12DD(detalle);
			}
			if( lista != null && lista.size()>0 ){
				fiseFormato12DC.setFiseFormato12DDs(lista);
				if( lista.size()==1 ){
					//enviamos la cabecera de informacion
					fiseFormato12DC.setAnoEjecucionDetalle(lista.get(0).getId().getAnoEjecucionGasto());
					fiseFormato12DC.setMesEjecucionDetalle(lista.get(0).getId().getMesEjecucionGasto());
					fiseFormato12DC.setEtapaEjecucionDetalle(lista.get(0).getId().getEtapaEjecucion());
					fiseFormato12DC.setNumeroItemEtapaDetalle(lista.get(0).getId().getNumeroItemEtapa());
				}
			}
			dto = fiseFormato12DC;
			
		} 	catch (Exception e) {
			logger.error("--error"+e.getMessage());
			e.printStackTrace();
			throw new Exception("Se produjo un error al guardar los datos del Formato 12D");
		}
		return dto;
	}
	
	@Override
	@Transactional(propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
	public FiseFormato12DC modificarFormato12DCregistrarFormato12DD(Formato12DCBean formulario, FiseFormato12DC fiseFormato12DC) throws Exception {
		
		FiseFormato12DC dto = null;
		
		try {
			
			Date hoy = FechaUtil.obtenerFechaActual();
			String horaActual = FechaUtil.getHoraActual();
	
			List<FiseFormato12DD> lista = new ArrayList<FiseFormato12DD>();
			
			if( FiseConstants.ETAPA_EJECUCION_IMPLEMENTACION_COD == formulario.getEtapaEjecucion() ){
				
				if( formulario.getAnioEjecucion() != 0 ||
						formulario.getMesEjecucion() != 0 ||
						formulario.getNroItemEtapa() != 0 ||
						!formulario.getCodUbigeo().equals(FiseConstants.BLANCO) ||
						!formulario.getLocalidad().equals(FiseConstants.BLANCO) ||
						formulario.getZonaBenef() != 0 ||
						!formulario.getCodCuentaContable().equals(FiseConstants.BLANCO) ||
						!formulario.getGasto().equals(FiseConstants.BLANCO) ||
						!formulario.getTipoGasto().equals(FiseConstants.BLANCO) ||
						!formulario.getTipoDocumento().equals(FiseConstants.BLANCO) ||
						!formulario.getRucEmpresa().equals(FiseConstants.BLANCO) ||
						!formulario.getSerieDocumento().equals(FiseConstants.BLANCO) ||
						!formulario.getNroDocumento().equals(FiseConstants.BLANCO) ||
						!formulario.getFechaAutorizacionString().equals(FiseConstants.BLANCO) ||
						!formulario.getNroDocAutorizacion().equals(FiseConstants.BLANCO) ||
						formulario.getCantidad() != 0 ||
						!formulario.getCostoUnitario().equals(BigDecimal.ZERO) 
						){
					
					FiseZonaBenef zonaBeneficiario = zonaBenefDao.obtenerFiseZonaBenefByPK(formulario.getZonaBenef());
					//
					FiseFormato12DD detalleImplementacion = new FiseFormato12DD();
					//pk
					FiseFormato12DDPK pkDetalle = new FiseFormato12DDPK();
					pkDetalle.setCodEmpresa(fiseFormato12DC.getId().getCodEmpresa());
					pkDetalle.setAnoPresentacion(fiseFormato12DC.getId().getAnoPresentacion());
					pkDetalle.setMesPresentacion(fiseFormato12DC.getId().getMesPresentacion());
					pkDetalle.setEtapa(fiseFormato12DC.getId().getEtapa());
					pkDetalle.setAnoEjecucionGasto(formulario.getAnioEjecucion());
					pkDetalle.setMesEjecucionGasto(formulario.getMesEjecucion());
					pkDetalle.setEtapaEjecucion(formulario.getEtapaEjecucion());
					//obtenemos el ultimo valor del itemetapa del detalle
					Long nroItemEtapa = formato12DDDao.obtenerMaximoItemEtapa(pkDetalle);
					pkDetalle.setNumeroItemEtapa(nroItemEtapa+1);
					detalleImplementacion.setId(pkDetalle);
					
					detalleImplementacion.setCodUbigeo(formulario.getCodUbigeo());
					detalleImplementacion.setDescripcionLocalidad(formulario.getLocalidad());
					detalleImplementacion.setIdZonaBenef(zonaBeneficiario.getIdZonaBenef());
					detalleImplementacion.setCodigoCuentaContaEde(formulario.getCodCuentaContable());
					detalleImplementacion.setDescripcionGasto(formulario.getGasto());
					detalleImplementacion.setIdTipGasto(formulario.getTipoGasto());
					if( !FiseConstants.BLANCO.equals(formulario.getTipoDocumento()) ){
						detalleImplementacion.setIdTipDocRef(formulario.getTipoDocumento());
						detalleImplementacion.setRucEmpresaEmiteDocRef(formulario.getRucEmpresa());
						detalleImplementacion.setSerieDocumentoReferencia(formulario.getSerieDocumento());
						detalleImplementacion.setNumeroDocumentoRefGasto(formulario.getNroDocumento());
					}
					detalleImplementacion.setFechaAutorizacionGasto(FechaUtil.fechaDate_DD_MM_YYYY_HHMMSS(formulario.getFechaAutorizacionString()+" "+horaActual));
					detalleImplementacion.setNumeroDocAutorizaGasto(formulario.getNroDocAutorizacion());
					detalleImplementacion.setCantidad(formulario.getCantidad());
					detalleImplementacion.setCostoUnitario(formulario.getCostoUnitario());
					
					
					BigDecimal total = new BigDecimal(0);
					total = detalleImplementacion.getCostoUnitario().multiply(new BigDecimal(detalleImplementacion.getCantidad()));
					detalleImplementacion.setTotalGeneral(total);
					
					//
					detalleImplementacion.setFiseFormato12DC(fiseFormato12DC);
					//
					detalleImplementacion.setUsuarioCreacion(formulario.getUsuario());
					detalleImplementacion.setTerminalCreacion(formulario.getTerminal());
					detalleImplementacion.setFechaCreacion(hoy);
					detalleImplementacion.setUsuarioActualizacion(formulario.getUsuario());
					detalleImplementacion.setTerminalActualizacion(formulario.getTerminal());
					detalleImplementacion.setFechaActualizacion(hoy);
					lista.add(detalleImplementacion);
					
				}
				
			}
			
			if( FiseConstants.ETAPA_EJECUCION_OPERATIVA_COD == formulario.getEtapaEjecucion() ){
				
				if( formulario.getAnioEjecucion() != 0 ||
						formulario.getMesEjecucion() != 0 ||
						formulario.getNroItemEtapa() != 0 ||
						!formulario.getCodUbigeo().equals(FiseConstants.BLANCO) ||
						!formulario.getLocalidad().equals(FiseConstants.BLANCO) ||
						formulario.getZonaBenef() != 0 ||
						!formulario.getCodCuentaContable().equals(FiseConstants.BLANCO) ||
						!formulario.getGasto().equals(FiseConstants.BLANCO) ||
						!formulario.getTipoGasto().equals(FiseConstants.BLANCO) ||
						!formulario.getTipoDocumento().equals(FiseConstants.BLANCO) ||
						!formulario.getRucEmpresa().equals(FiseConstants.BLANCO) ||
						!formulario.getSerieDocumento().equals(FiseConstants.BLANCO) ||
						!formulario.getNroDocumento().equals(FiseConstants.BLANCO) ||
						!formulario.getFechaAutorizacionString().equals(FiseConstants.BLANCO) ||
						!formulario.getNroDocAutorizacion().equals(FiseConstants.BLANCO) ||
						formulario.getCantidad() != 0 ||
						!formulario.getCostoUnitario().equals(BigDecimal.ZERO) 
						){
					
					FiseZonaBenef zonaBeneficiario = zonaBenefDao.obtenerFiseZonaBenefByPK(formulario.getZonaBenef());
					//
					FiseFormato12DD detalleOperativo = new FiseFormato12DD();
					//pk
					FiseFormato12DDPK pkDetalle = new FiseFormato12DDPK();
					pkDetalle.setCodEmpresa(fiseFormato12DC.getId().getCodEmpresa());
					pkDetalle.setAnoPresentacion(fiseFormato12DC.getId().getAnoPresentacion());
					pkDetalle.setMesPresentacion(fiseFormato12DC.getId().getMesPresentacion());
					pkDetalle.setEtapa(fiseFormato12DC.getId().getEtapa());
					pkDetalle.setAnoEjecucionGasto(formulario.getAnioEjecucion());
					pkDetalle.setMesEjecucionGasto(formulario.getMesEjecucion());
					pkDetalle.setEtapaEjecucion(formulario.getEtapaEjecucion());
					//obtenemos el ultimo valor del itemetapa del detalle
					Long nroItemEtapa = formato12DDDao.obtenerMaximoItemEtapa(pkDetalle);
					pkDetalle.setNumeroItemEtapa(nroItemEtapa+1);
					detalleOperativo.setId(pkDetalle);
					
					detalleOperativo.setCodUbigeo(formulario.getCodUbigeo());
					detalleOperativo.setDescripcionLocalidad(formulario.getLocalidad());
					detalleOperativo.setIdZonaBenef(zonaBeneficiario.getIdZonaBenef());
					detalleOperativo.setCodigoCuentaContaEde(formulario.getCodCuentaContable());
					detalleOperativo.setDescripcionGasto(formulario.getGasto());
					detalleOperativo.setIdTipGasto(formulario.getTipoGasto());
					if( !FiseConstants.BLANCO.equals(formulario.getTipoDocumento()) ){
						detalleOperativo.setIdTipDocRef(formulario.getTipoDocumento());
						detalleOperativo.setRucEmpresaEmiteDocRef(formulario.getRucEmpresa());
						detalleOperativo.setSerieDocumentoReferencia(formulario.getSerieDocumento());
						detalleOperativo.setNumeroDocumentoRefGasto(formulario.getNroDocumento());
					}
					detalleOperativo.setFechaAutorizacionGasto(FechaUtil.fechaDate_DD_MM_YYYY_HHMMSS(formulario.getFechaAutorizacionString()+" "+horaActual));
					detalleOperativo.setNumeroDocAutorizaGasto(formulario.getNroDocAutorizacion());
					detalleOperativo.setCantidad(formulario.getCantidad());
					detalleOperativo.setCostoUnitario(formulario.getCostoUnitario());
					
					
					BigDecimal total = new BigDecimal(0);
					total = detalleOperativo.getCostoUnitario().multiply(new BigDecimal(detalleOperativo.getCantidad()));
					detalleOperativo.setTotalGeneral(total);
					
					//
					detalleOperativo.setFiseFormato12DC(fiseFormato12DC);
					//
					detalleOperativo.setUsuarioCreacion(formulario.getUsuario());
					detalleOperativo.setTerminalCreacion(formulario.getTerminal());
					detalleOperativo.setFechaCreacion(hoy);
					detalleOperativo.setUsuarioActualizacion(formulario.getUsuario());
					detalleOperativo.setTerminalActualizacion(formulario.getTerminal());
					detalleOperativo.setFechaActualizacion(hoy);
					lista.add(detalleOperativo);
					
				}
				
			}
		
			fiseFormato12DC.setUsuarioActualizacion(formulario.getUsuario());
			fiseFormato12DC.setTerminalActualizacion(formulario.getTerminal());
			fiseFormato12DC.setFechaActualizacion(hoy);
		
			if( FiseConstants.TIPOARCHIVO_XLS.equals(formulario.getTipoArchivo()) ){
				fiseFormato12DC.setNombreArchivoExcel(formulario.getNombreArchivo());
			}else if( FiseConstants.TIPOARCHIVO_TXT.equals(formulario.getTipoArchivo()) ){
				fiseFormato12DC.setNombreArchivoTexto(formulario.getNombreArchivo());
			}
			
			formato12DCDao.modificarFormato12DC(fiseFormato12DC);
			//add
			for (FiseFormato12DD detalle : lista) {
				formato12DDDao.registrarFormato12DD(detalle);
			}
			if( lista != null && lista.size()>0 ){
				if( lista.size()==1 ){
					//enviamos la cabecera de informacion
					fiseFormato12DC.setAnoEjecucionDetalle(lista.get(0).getId().getAnoEjecucionGasto());
					fiseFormato12DC.setMesEjecucionDetalle(lista.get(0).getId().getMesEjecucionGasto());
					fiseFormato12DC.setEtapaEjecucionDetalle(lista.get(0).getId().getEtapaEjecucion());
					fiseFormato12DC.setNumeroItemEtapaDetalle(lista.get(0).getId().getNumeroItemEtapa());
				}
			}
			dto= fiseFormato12DC;
			
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
	public FiseFormato12DC modificarFormato12DCmodificarFormato12DD(Formato12DCBean formulario, FiseFormato12DC fiseFormato12DC) throws Exception {
		
		FiseFormato12DC dto = null;
		
		try {
			
			Date hoy = FechaUtil.obtenerFechaActual();
			String horaActual = FechaUtil.getHoraActual();
	
			List<FiseFormato12DD> lista = new ArrayList<FiseFormato12DD>();
			
			FiseFormato12DD detalleImplementacion = new FiseFormato12DD();
			FiseFormato12DD detalleOperativo = new FiseFormato12DD();
			if( fiseFormato12DC.getFiseFormato12DDs()!=null ){
				for (FiseFormato12DD detalle : fiseFormato12DC.getFiseFormato12DDs()) {
					
					if( formulario.getCodigoEmpresa().trim().equals(detalle.getId().getCodEmpresa().trim()) &&
							formulario.getAnioPresentacion() == detalle.getId().getAnoPresentacion() &&
							formulario.getMesPresentacion() == detalle.getId().getMesPresentacion() &&
							formulario.getEtapa().equals(detalle.getId().getEtapa()) &&
							formulario.getAnioEjecucion() == detalle.getId().getAnoEjecucionGasto() &&
							formulario.getMesEjecucion() == detalle.getId().getMesEjecucionGasto() &&
							formulario.getEtapaEjecucion() == detalle.getId().getEtapaEjecucion() &&
							formulario.getNroItemEtapa() == detalle.getId().getNumeroItemEtapa()
						){
					
						logger.info("entro");
						
						if( FiseConstants.ETAPA_EJECUCION_IMPLEMENTACION_COD == formulario.getEtapaEjecucion() ){
							detalleImplementacion = detalle;
							break;
						}else if( FiseConstants.ETAPA_EJECUCION_OPERATIVA_COD == formulario.getEtapaEjecucion() ){
							detalleOperativo = detalle;
							break;
						}
						
					}else{
						logger.info("no entro");
					}

				}
			}
			
			//IMPLEMENTACION
			if( FiseConstants.ETAPA_EJECUCION_IMPLEMENTACION_COD == formulario.getEtapaEjecucion() ){
				
				if( formulario.getAnioEjecucion() != 0 ||
						formulario.getMesEjecucion() != 0 ||
						formulario.getNroItemEtapa() != 0 ||
						!formulario.getCodUbigeo().equals(FiseConstants.BLANCO) ||
						!formulario.getLocalidad().equals(FiseConstants.BLANCO) ||
						formulario.getZonaBenef() != 0 ||
						!formulario.getCodCuentaContable().equals(FiseConstants.BLANCO) ||
						!formulario.getGasto().equals(FiseConstants.BLANCO) ||
						!formulario.getTipoGasto().equals(FiseConstants.BLANCO) ||
						!formulario.getTipoDocumento().equals(FiseConstants.BLANCO) ||
						!formulario.getRucEmpresa().equals(FiseConstants.BLANCO) ||
						!formulario.getSerieDocumento().equals(FiseConstants.BLANCO) ||
						!formulario.getNroDocumento().equals(FiseConstants.BLANCO) ||
						!formulario.getFechaAutorizacionString().equals(FiseConstants.BLANCO) ||
						!formulario.getNroDocAutorizacion().equals(FiseConstants.BLANCO) ||
						formulario.getCantidad() != 0 ||
						!formulario.getCostoUnitario().equals(BigDecimal.ZERO) 
						){
					
					detalleImplementacion.setCodUbigeo(formulario.getCodUbigeo());
					detalleImplementacion.setDescripcionLocalidad(formulario.getLocalidad());
					detalleImplementacion.setIdZonaBenef(formulario.getZonaBenef());
					detalleImplementacion.setCodigoCuentaContaEde(formulario.getCodCuentaContable());
					detalleImplementacion.setDescripcionGasto(formulario.getGasto());
					detalleImplementacion.setIdTipGasto(formulario.getTipoGasto());
					if( !FiseConstants.BLANCO.equals(formulario.getTipoDocumento()) ){
						detalleImplementacion.setIdTipDocRef(formulario.getTipoDocumento());
						detalleImplementacion.setRucEmpresaEmiteDocRef(formulario.getRucEmpresa());
						detalleImplementacion.setSerieDocumentoReferencia(formulario.getSerieDocumento());
						detalleImplementacion.setNumeroDocumentoRefGasto(formulario.getNroDocumento());
					}
					detalleImplementacion.setFechaAutorizacionGasto(FechaUtil.fechaDate_DD_MM_YYYY_HHMMSS(formulario.getFechaAutorizacionString()+" "+horaActual));
					detalleImplementacion.setNumeroDocAutorizaGasto(formulario.getNroDocAutorizacion());
					detalleImplementacion.setCantidad(formulario.getCantidad());
					detalleImplementacion.setCostoUnitario(formulario.getCostoUnitario());
					
					
					BigDecimal total = new BigDecimal(0);
					total = detalleImplementacion.getCostoUnitario().multiply(new BigDecimal(detalleImplementacion.getCantidad()));
					detalleImplementacion.setTotalGeneral(total);
					
					detalleImplementacion.setUsuarioActualizacion(formulario.getUsuario());
					detalleImplementacion.setTerminalActualizacion(formulario.getTerminal());
					detalleImplementacion.setFechaActualizacion(hoy);
					lista.add(detalleImplementacion);
					
				}
				
				
			}
			
			//OPERATIVA
			if( FiseConstants.ETAPA_EJECUCION_OPERATIVA_COD == formulario.getEtapaEjecucion() ){
				
				if( formulario.getAnioEjecucion() != 0 ||
						formulario.getMesEjecucion() != 0 ||
						formulario.getNroItemEtapa() != 0 ||
						!formulario.getCodUbigeo().equals(FiseConstants.BLANCO) ||
						!formulario.getLocalidad().equals(FiseConstants.BLANCO) ||
						formulario.getZonaBenef() != 0 ||
						!formulario.getCodCuentaContable().equals(FiseConstants.BLANCO) ||
						!formulario.getGasto().equals(FiseConstants.BLANCO) ||
						!formulario.getTipoGasto().equals(FiseConstants.BLANCO) ||
						!formulario.getTipoDocumento().equals(FiseConstants.BLANCO) ||
						!formulario.getRucEmpresa().equals(FiseConstants.BLANCO) ||
						!formulario.getSerieDocumento().equals(FiseConstants.BLANCO) ||
						!formulario.getNroDocumento().equals(FiseConstants.BLANCO) ||
						!formulario.getFechaAutorizacionString().equals(FiseConstants.BLANCO) ||
						!formulario.getNroDocAutorizacion().equals(FiseConstants.BLANCO) ||
						formulario.getCantidad() != 0 ||
						!formulario.getCostoUnitario().equals(BigDecimal.ZERO) 
						){
					
					detalleOperativo.setCodUbigeo(formulario.getCodUbigeo());
					detalleOperativo.setDescripcionLocalidad(formulario.getLocalidad());
					detalleOperativo.setIdZonaBenef(formulario.getZonaBenef());
					detalleOperativo.setCodigoCuentaContaEde(formulario.getCodCuentaContable());
					detalleOperativo.setDescripcionGasto(formulario.getGasto());
					detalleImplementacion.setIdTipGasto(formulario.getTipoGasto());
					if( !FiseConstants.BLANCO.equals(formulario.getTipoDocumento()) ){
						detalleOperativo.setIdTipDocRef(formulario.getTipoDocumento());
						detalleOperativo.setRucEmpresaEmiteDocRef(formulario.getRucEmpresa());
						detalleOperativo.setSerieDocumentoReferencia(formulario.getSerieDocumento());
						detalleOperativo.setNumeroDocumentoRefGasto(formulario.getNroDocumento());
					}
					detalleImplementacion.setFechaAutorizacionGasto(FechaUtil.fechaDate_DD_MM_YYYY_HHMMSS(formulario.getFechaAutorizacionString()+" "+horaActual));
					detalleOperativo.setNumeroDocAutorizaGasto(formulario.getNroDocAutorizacion());
					detalleOperativo.setCantidad(formulario.getCantidad());
					detalleOperativo.setCostoUnitario(formulario.getCostoUnitario());
					
					
					BigDecimal total = new BigDecimal(0);
					total = detalleOperativo.getCostoUnitario().multiply(new BigDecimal(detalleOperativo.getCantidad()));
					detalleOperativo.setTotalGeneral(total);
					
					detalleOperativo.setUsuarioActualizacion(formulario.getUsuario());
					detalleOperativo.setTerminalActualizacion(formulario.getTerminal());
					detalleOperativo.setFechaActualizacion(hoy);
					lista.add(detalleOperativo);
					
				}
				
				
			}
		
			fiseFormato12DC.setUsuarioActualizacion(formulario.getUsuario());
			fiseFormato12DC.setTerminalActualizacion(formulario.getTerminal());
			fiseFormato12DC.setFechaActualizacion(hoy);
		
			if( FiseConstants.TIPOARCHIVO_XLS.equals(formulario.getTipoArchivo()) ){
				fiseFormato12DC.setNombreArchivoExcel(formulario.getNombreArchivo());
			}else if( FiseConstants.TIPOARCHIVO_TXT.equals(formulario.getTipoArchivo()) ){
				fiseFormato12DC.setNombreArchivoTexto(formulario.getNombreArchivo());
			}
			
			formato12DCDao.modificarFormato12DC(fiseFormato12DC);
			//add
			for (FiseFormato12DD detalle : lista) {
				formato12DDDao.modificarFormato12DD(detalle);
			}
			if( lista != null && lista.size()>0 ){
				if( lista.size()==1 ){
					//enviamos la cabecera de informacion
					fiseFormato12DC.setAnoEjecucionDetalle(lista.get(0).getId().getAnoEjecucionGasto());
					fiseFormato12DC.setMesEjecucionDetalle(lista.get(0).getId().getMesEjecucionGasto());
					fiseFormato12DC.setEtapaEjecucionDetalle(lista.get(0).getId().getEtapaEjecucion());
					fiseFormato12DC.setNumeroItemEtapaDetalle(lista.get(0).getId().getNumeroItemEtapa());
				}
			}
			dto= fiseFormato12DC;
			
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
	public void eliminarFormato12DC(FiseFormato12DC fiseFormato12DC) {
		List<FiseFormato12DD> lista = null;
		lista = formato12DDDao.listarFormato12DDByFormato12DC(fiseFormato12DC);
		for (FiseFormato12DD detalle : lista) {
			List<FiseFormato12DDOb> listaObservacion = formato12DDObDao.listarFormato12DDObByFormato12DD(detalle);
			for (FiseFormato12DDOb observacion : listaObservacion) {
				formato12DDObDao.eliminarFormato12DDOb(observacion);
			}
			formato12DDDao.eliminarFormato12DD(detalle);
		}
		formato12DCDao.eliminarFormato12DC(fiseFormato12DC);
		//cambios elozano eliminar fiseArchivoCab y su detalle
		String valor = eliminarArchivoSustentoCab(fiseFormato12DC);
		logger.info("Valor al eliminar archivos de sustento si es 1= OK caso contrario error:  "+valor); 
	}
	
	//@Transactional
	private String eliminarArchivoSustentoCab(FiseFormato12DC f){
		String valor = "0";
		List<FiseArchivosCab> listaCab =null;
		List<FiseArchivosDet> listaDet = null;
		try {
			BigDecimal anioEjec = null;
			BigDecimal mesEjec = null;					
			listaCab = archivoSustentoDao.listaFiseArchivosCabMensual(f.getId().getCodEmpresa(),
					f.getId().getAnoPresentacion(), f.getId().getMesPresentacion(),
					anioEjec, mesEjec, f.getId().getEtapa(),FiseConstants.NOMBRE_FORMATO_12D);
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
	@Transactional(propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
	public void eliminarFormato12DD(FiseFormato12DD fiseFormato12DD) {
		List<FiseFormato12DDOb> listaObservacion = formato12DDObDao.listarFormato12DDObByFormato12DD(fiseFormato12DD);
		for (FiseFormato12DDOb observacion : listaObservacion) {
			formato12DDObDao.eliminarFormato12DDOb(observacion);
		}
		formato12DDDao.eliminarFormato12DD(fiseFormato12DD);
	}
	
	@Override
	public Formato12DCBean estructurarFormato12DBeanByFiseFormato12DC(FiseFormato12DC formato){
		Formato12DCBean formato12DBean = new Formato12DCBean();
		formato12DBean.setAnioPresentacion(formato.getId().getAnoPresentacion());
		formato12DBean.setMesPresentacion(formato.getId().getMesPresentacion());
		return formato12DBean;
	}

	@Override
	public HashMap<String, Object> mapearParametrosFormato12D(Formato12DCBean formato12DBean){
		HashMap<String, Object> mapJRParams = new HashMap<String, Object>();
		mapJRParams.put(FiseConstants.PARAM_DESC_EMPRESA, formato12DBean.getDescEmpresa());
		mapJRParams.put(FiseConstants.PARAM_ANO_PRESENTACION, formato12DBean.getAnioPresentacion());
		mapJRParams.put(FiseConstants.PARAM_DESC_MES_PRESENTACION, formato12DBean.getDescMesPresentacion());
		return mapJRParams;
	}
	
	@Override
	@Transactional
	public List<FiseFormato12DDOb> listarFormato12DDObByFormato12DD(FiseFormato12DD formato12DD){
		return formato12DDObDao.listarFormato12DDObByFormato12DD(formato12DD); 
	}
	
	@Override
	@Transactional(propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
	public String modificarEnvioDefinitivoFormato12DC(String user,String terminal,
			FiseFormato12DC fiseFormato12DC) throws Exception {
		
		String valor ="0";		
		try{
			fiseFormato12DC.setFechaEnvioDefinitivo(FechaUtil.obtenerFechaActual());
			fiseFormato12DC.setUsuarioActualizacion(user);
			fiseFormato12DC.setTerminalActualizacion(terminal);
			fiseFormato12DC.setFechaActualizacion(FechaUtil.obtenerFechaActual());
			formato12DCDao.modificarFormato12DC(fiseFormato12DC);
			valor = "1";
		} catch (Exception e) {
			valor = "0";
			logger.error("--error"+e.getMessage());
			e.printStackTrace();			
		}
		return valor;
	}
	
	
	
	@Override
	@Transactional(propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
	public void eliminarObservaciones12D(List<FiseFormato12DDOb> listaObs) throws Exception {	
		for (FiseFormato12DDOb observacion : listaObs) {
			formato12DDObDao.eliminarFormato12DDOb(observacion); 
		}
	}
	
	
	
	@Override
	@Transactional(propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
	public String insertarObservacion12D(String codEmpresa,long anioPres,long mesPres,
			long anioEjec,long mesEjec,String etapa,long etapaEjec,long itemEtapa, 
			String desObservacion,String user,String terminal) throws Exception{
		String valor = "0";
		FiseObservacion observacion = null;
		FiseFormato12DDObPK pk = null;
		FiseFormato12DDOb obs = null;
		try {
			long maxItemObs = formato12DDObDao.buscarMaximoItemObs12D(codEmpresa, anioPres, mesPres, 
					anioEjec, mesEjec, etapa, etapaEjec, itemEtapa);			
			String idObservacion = fiseObservacionDao.obtenerIdObservacion();			
			observacion = new FiseObservacion();
			observacion.setIdObservacion(idObservacion); 
			observacion.setDescripcion(desObservacion);
			observacion.setOrigen(FiseConstants.OBSERVACION_MANUAL); 
			observacion.setUsuarioCreacion(user);
			observacion.setTerminalCreacion(terminal); 
			observacion.setFechaCreacion(FechaUtil.obtenerFechaActual()); 
			fiseObservacionDao.insertarFiseObservacion(observacion);
			pk = new FiseFormato12DDObPK();
			pk.setCodEmpresa(codEmpresa);
			pk.setAnoPresentacion(anioPres);
			pk.setMesPresentacion(mesPres);
			pk.setAnoEjecucionGasto(anioEjec);
			pk.setMesEjecucionGasto(mesEjec);
			pk.setEtapa(etapa);
			pk.setEtapaEjecucion(etapaEjec);
			pk.setNumeroItemEtapa(itemEtapa); 
			pk.setItemObservacion(maxItemObs);  	
			obs = new FiseFormato12DDOb();
			obs.setId(pk);
			obs.setFechaCreacion(FechaUtil.obtenerFechaActual());			
			obs.setFiseObservacion(observacion);
			obs.setUsuarioCreacion(user);
			obs.setTerminalCreacion(terminal);		
			formato12DDObDao.insertarFiseFormato12DObs(obs); 
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
	public String eliminarObservacion12D(String codEmpresa,long anioPres,long mesPres,
			long anioEjec,long mesEjec,String etapa,long etapaEjec,long itemEtapa, 
			String idObservacion,long itemObservacion) throws Exception{
		String valor = "0";
		FiseObservacion observacion = null;
		FiseFormato12DDObPK pk = null;
		FiseFormato12DDOb obs = null;
		try {			
			pk = new FiseFormato12DDObPK();
			pk.setCodEmpresa(codEmpresa);
			pk.setAnoPresentacion(anioPres);
			pk.setMesPresentacion(mesPres);
			pk.setAnoEjecucionGasto(anioEjec);
			pk.setMesEjecucionGasto(mesEjec);
			pk.setEtapa(etapa);
			pk.setEtapaEjecucion(etapaEjec);
			pk.setNumeroItemEtapa(itemEtapa); 
			pk.setItemObservacion(itemObservacion);  	
			obs = formato12DDObDao.obtenerFiseFormato12DDOb(pk);			
			formato12DDObDao.eliminarFormato12DDOb(obs); 
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
