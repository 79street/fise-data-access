package gob.osinergmin.fise.gart.service.impl;

import gob.osinergmin.fise.bean.Formato12CCBean;
import gob.osinergmin.fise.constant.FiseConstants;
import gob.osinergmin.fise.dao.CommonDao;
import gob.osinergmin.fise.dao.FiseGrupoInformacionDao;
import gob.osinergmin.fise.dao.FiseZonaBenefDao;
import gob.osinergmin.fise.dao.Formato12CCDao;
import gob.osinergmin.fise.dao.Formato12CDDao;
import gob.osinergmin.fise.dao.Formato12CDObDao;
import gob.osinergmin.fise.domain.FiseFormato12CC;
import gob.osinergmin.fise.domain.FiseFormato12CCPK;
import gob.osinergmin.fise.domain.FiseFormato12CD;
import gob.osinergmin.fise.domain.FiseFormato12CDPK;
import gob.osinergmin.fise.domain.FiseGrupoInformacion;
import gob.osinergmin.fise.domain.FiseZonaBenef;
import gob.osinergmin.fise.gart.service.Formato12CGartService;
import gob.osinergmin.fise.util.FechaUtil;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

@Service(value="formato12CGartServiceImpl")
public class Formato12CGartServiceImpl implements Formato12CGartService {
	
	Logger logger=Logger.getLogger(Formato12CGartServiceImpl.class);
	
	@Autowired
	@Qualifier("formato12CCDaoImpl")
	private Formato12CCDao formato12CCDao;
	
	@Autowired
	@Qualifier("formato12CDDaoImpl")
	private Formato12CDDao formato12CDDao;
	
	@Autowired
	@Qualifier("fiseZonaBenefDaoImpl")
	private FiseZonaBenefDao zonaBenefDao;
	
	@Autowired
	@Qualifier("formato12CDObDaoImpl")
	private Formato12CDObDao formato12CObsDao;
	
	@Autowired
	@Qualifier("commonDaoImpl")
	private CommonDao commonDao;
	
	@Autowired
	@Qualifier("fiseGrupoInformacionDaoImpl")
	private FiseGrupoInformacionDao fiseGrupoInformacionDao;
	
	@Override
	@Transactional
	public FiseFormato12CC obtenerFormato12CCByPK(FiseFormato12CCPK fiseFormato12CCPK) {
		FiseFormato12CC formato = null;
		formato = formato12CCDao.obtenerFormato12CCByPK(fiseFormato12CCPK);
		if(formato != null){
			formato.setFiseFormato12CDs(formato12CDDao.listarFormato12CDByFormato12CC(formato));
		}
		return formato;
	}
	
	@Override
	@Transactional
	public List<FiseFormato12CC> buscarFormato12CC(String codEmpresa, long anioDesde, long mesDesde, long anioHasta, long mesHasta, String etapa){
		
		List<FiseFormato12CC> lista = null;
		lista = formato12CCDao.buscarFormato12CC(codEmpresa, anioDesde, mesDesde, anioHasta, mesHasta, etapa);
		for (FiseFormato12CC formato : lista) {
			formato.setFiseFormato12CDs(formato12CDDao.listarFormato12CDByFormato12CC(formato));
		}
		return lista;
	}
	
	public List<FiseFormato12CD> listarFormato12CDByFormato12CC(FiseFormato12CC formato12CC) {
		List<FiseFormato12CD> lista = null;
		lista = formato12CDDao.listarFormato12CDByFormato12CC(formato12CC);
		return lista;
	}
	
	@Override
	@Transactional(propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
	public FiseFormato12CC registrarFormato12CC(Formato12CCBean formulario) throws Exception {
		
		FiseFormato12CC dto = null;
		
		try {
			Date hoy = FechaUtil.obtenerFechaActual();
			FiseFormato12CC fiseFormato12CC = new FiseFormato12CC();
			//guardar el pk
			FiseFormato12CCPK id = new FiseFormato12CCPK();
			id.setCodEmpresa(formulario.getCodigoEmpresa());
			id.setAnoPresentacion(formulario.getAnioPresentacion());
			id.setMesPresentacion(formulario.getMesPresentacion());
			id.setEtapa(FiseConstants.ETAPA_SOLICITUD);
			if( FiseConstants.TIPOARCHIVO_XLS.equals(formulario.getTipoArchivo()) ){
				fiseFormato12CC.setNombreArchivoExcel(formulario.getNombreArchivo());
			}else if( FiseConstants.TIPOARCHIVO_TXT.equals(formulario.getTipoArchivo()) ){
				fiseFormato12CC.setNombreArchivoTexto(formulario.getNombreArchivo());
			}else{
				id.setEtapa(formulario.getEtapa());
			}
			
			fiseFormato12CC.setId(id);
			
			FiseGrupoInformacion grupoInfo = null;
			long idGrupoInf = commonDao.obtenerIdGrupoInformacion(formulario.getAnioPresentacion(), formulario.getMesPresentacion(), FiseConstants.FRECUENCIA_MENSUAL_DESCRIPCION); 
			if(idGrupoInf!=0){
				grupoInfo = fiseGrupoInformacionDao.obtenerFiseGrupoInformacionByPK(idGrupoInf);	
			}	
			fiseFormato12CC.setFiseGrupoInformacion(grupoInfo);
			
			List<FiseFormato12CD> lista = new ArrayList<FiseFormato12CD>();
			
			if( FiseConstants.ETAPA_EJECUCION_IMPLEMENTACION_COD == formulario.getEtapaEjecucion() ){
				FiseZonaBenef zonaBeneficiario = zonaBenefDao.obtenerFiseZonaBenefByPK(formulario.getZonaBenef());
				//
				FiseFormato12CD detalleImplementacion = new FiseFormato12CD();
				//pk
				FiseFormato12CDPK pkDetalle = new FiseFormato12CDPK();
				pkDetalle.setCodEmpresa(fiseFormato12CC.getId().getCodEmpresa());
				pkDetalle.setAnoPresentacion(fiseFormato12CC.getId().getAnoPresentacion());
				pkDetalle.setMesPresentacion(fiseFormato12CC.getId().getMesPresentacion());
				pkDetalle.setEtapa(fiseFormato12CC.getId().getEtapa());
				pkDetalle.setAnoEjecucionGasto(formulario.getAnioEjecucion());
				pkDetalle.setMesEjecucionGasto(formulario.getMesEjecucion());
				pkDetalle.setEtapaEjecucion(formulario.getEtapaEjecucion());
				//obtenemos el ultimo valor del itemetapa del detalle
				Long nroItemEtapa = formato12CDDao.obtenerMaximoItemEtapa(pkDetalle);
				pkDetalle.setNumeroItemEtapa(nroItemEtapa+1);
				detalleImplementacion.setId(pkDetalle);
				
				detalleImplementacion.setCodUbigeoOrigen(formulario.getCodDistritoOrigen());
				detalleImplementacion.setDescripcionLocalidadOrigen(formulario.getLocalidadOrigen());
				detalleImplementacion.setCodUbigeoDestino(formulario.getCodDistritoDestino());
				detalleImplementacion.setDescripcionLocalidadDestino(formulario.getLocalidadDestino());
				detalleImplementacion.setIdZonaBenef(zonaBeneficiario.getIdZonaBenef());
				detalleImplementacion.setCodigoCuentaContaEde(formulario.getCodCuentaContable());
				detalleImplementacion.setDescripcionActividad(formulario.getActividad());
				if( !FiseConstants.BLANCO.equals(formulario.getTipoDocumento()) ){
					detalleImplementacion.setIdTipDocRef(formulario.getTipoDocumento());
					detalleImplementacion.setRucEmpresaEmiteDocRef(formulario.getRucEmpresa());
					detalleImplementacion.setSerieDocumentoReferencia(formulario.getSerieDocumento());
					detalleImplementacion.setNumeroDocumentoReferencia(formulario.getNroDocumento());
				}
				detalleImplementacion.setNumeroDias(formulario.getNroDias());
				detalleImplementacion.setMontoAlimentacion(formulario.getMontoAlimentacion());
				detalleImplementacion.setMontoAlojamiento(formulario.getMontoAlojamiento());
				detalleImplementacion.setMontoMovilidad(formulario.getMontoMovilidad());
				
				BigDecimal total = new BigDecimal(0);
				total = total.add(detalleImplementacion.getMontoAlimentacion())
						.add(detalleImplementacion.getMontoAlojamiento())
						.add(detalleImplementacion.getMontoMovilidad());
				detalleImplementacion.setTotalGeneral(total);
				
				//
				detalleImplementacion.setFiseFormato12CC(fiseFormato12CC);
				//
				detalleImplementacion.setUsuarioCreacion(formulario.getUsuario());
				detalleImplementacion.setTerminalCreacion(formulario.getTerminal());
				detalleImplementacion.setFechaCreacion(hoy);
				detalleImplementacion.setUsuarioActualizacion(formulario.getUsuario());
				detalleImplementacion.setTerminalActualizacion(formulario.getTerminal());
				detalleImplementacion.setFechaActualizacion(hoy);
				lista.add(detalleImplementacion);
				
			}
			
			if( FiseConstants.ETAPA_EJECUCION_OPERATIVA_COD == formulario.getEtapaEjecucion() ){
				FiseZonaBenef zonaBeneficiario = zonaBenefDao.obtenerFiseZonaBenefByPK(formulario.getZonaBenef());
				//
				FiseFormato12CD detalleOperativo = new FiseFormato12CD();
				//pk
				FiseFormato12CDPK pkDetalle = new FiseFormato12CDPK();
				pkDetalle.setCodEmpresa(fiseFormato12CC.getId().getCodEmpresa());
				pkDetalle.setAnoPresentacion(fiseFormato12CC.getId().getAnoPresentacion());
				pkDetalle.setMesPresentacion(fiseFormato12CC.getId().getMesPresentacion());
				pkDetalle.setEtapa(fiseFormato12CC.getId().getEtapa());
				pkDetalle.setAnoEjecucionGasto(formulario.getAnioEjecucion());
				pkDetalle.setMesEjecucionGasto(formulario.getMesEjecucion());
				pkDetalle.setEtapaEjecucion(formulario.getEtapaEjecucion());
				//obtenemos el ultimo valor del itemetapa del detalle
				Long nroItemEtapa = formato12CDDao.obtenerMaximoItemEtapa(pkDetalle);
				pkDetalle.setNumeroItemEtapa(nroItemEtapa+1);
				detalleOperativo.setId(pkDetalle);
				
				detalleOperativo.setCodUbigeoOrigen(formulario.getCodDistritoOrigen());
				detalleOperativo.setDescripcionLocalidadOrigen(formulario.getLocalidadOrigen());
				detalleOperativo.setCodUbigeoDestino(formulario.getCodDistritoDestino());
				detalleOperativo.setDescripcionLocalidadDestino(formulario.getLocalidadDestino());
				detalleOperativo.setIdZonaBenef(zonaBeneficiario.getIdZonaBenef());
				detalleOperativo.setCodigoCuentaContaEde(formulario.getCodCuentaContable());
				detalleOperativo.setDescripcionActividad(formulario.getActividad());
				if( !FiseConstants.BLANCO.equals(formulario.getTipoDocumento()) ){
					detalleOperativo.setIdTipDocRef(formulario.getTipoDocumento());
					detalleOperativo.setRucEmpresaEmiteDocRef(formulario.getRucEmpresa());
					detalleOperativo.setSerieDocumentoReferencia(formulario.getSerieDocumento());
					detalleOperativo.setNumeroDocumentoReferencia(formulario.getNroDocumento());
				}
				detalleOperativo.setNumeroDias(formulario.getNroDias());
				detalleOperativo.setMontoAlimentacion(formulario.getMontoAlimentacion());
				detalleOperativo.setMontoAlojamiento(formulario.getMontoAlojamiento());
				detalleOperativo.setMontoMovilidad(formulario.getMontoMovilidad());
				
				BigDecimal total = new BigDecimal(0);
				total = total.add(detalleOperativo.getMontoAlimentacion())
						.add(detalleOperativo.getMontoAlojamiento())
						.add(detalleOperativo.getMontoMovilidad());
				detalleOperativo.setTotalGeneral(total);
				
				//
				detalleOperativo.setFiseFormato12CC(fiseFormato12CC);
				//
				detalleOperativo.setUsuarioCreacion(formulario.getUsuario());
				detalleOperativo.setTerminalCreacion(formulario.getTerminal());
				detalleOperativo.setFechaCreacion(hoy);
				detalleOperativo.setUsuarioActualizacion(formulario.getUsuario());
				detalleOperativo.setTerminalActualizacion(formulario.getTerminal());
				detalleOperativo.setFechaActualizacion(hoy);
				lista.add(detalleOperativo);
				
			}
			
			fiseFormato12CC.setUsuarioActualizacion(formulario.getUsuario());
			fiseFormato12CC.setTerminalActualizacion(formulario.getTerminal());
			fiseFormato12CC.setFechaActualizacion(hoy);
			fiseFormato12CC.setUsuarioCreacion(formulario.getUsuario());
			fiseFormato12CC.setTerminalCreacion(formulario.getTerminal());
			fiseFormato12CC.setFechaCreacion(hoy);
			
			logger.info("aca se va  a guardar"+fiseFormato12CC);
			boolean existe = false;
			existe = formato12CCDao.existeFormato12CC(fiseFormato12CC);
			if(existe){
				throw new Exception("Ya existe un registro con la misma clave.");
			}else{
				formato12CCDao.registrarFormato12CC(fiseFormato12CC);
			}
			//add
			for (FiseFormato12CD detalle : lista) {
				formato12CDDao.registrarFormato12CD(detalle);
			}
			if( lista != null && lista.size()>0 ){
				fiseFormato12CC.setFiseFormato12CDs(lista);
				if( lista.size()==1 ){
					//enviamos la cabecera de informacion
					fiseFormato12CC.setAnoEjecucionDetalle(lista.get(0).getId().getAnoEjecucionGasto());
					fiseFormato12CC.setMesEjecucionDetalle(lista.get(0).getId().getMesEjecucionGasto());
					fiseFormato12CC.setEtapaEjecucionDetalle(lista.get(0).getId().getEtapaEjecucion());
					fiseFormato12CC.setNumeroItemEtapaDetalle(lista.get(0).getId().getNumeroItemEtapa());
				}
			}
			dto = fiseFormato12CC;
			
		} 	catch (Exception e) {
			logger.error("--error"+e.getMessage());
			e.printStackTrace();
			throw e;
		}
		return dto;
	}
	
	@Override
	@Transactional(propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
	public FiseFormato12CC modificarFormato12CC(Formato12CCBean formulario, FiseFormato12CC fiseFormato12CC) throws Exception {
		
		FiseFormato12CC dto = null;
		
		try {
			
			Date hoy = FechaUtil.obtenerFechaActual();
	
			List<FiseFormato12CD> lista = new ArrayList<FiseFormato12CD>();
			
			FiseFormato12CD detalleImplementacion = new FiseFormato12CD();
			FiseFormato12CD detalleOperativo = new FiseFormato12CD();
			if( fiseFormato12CC.getFiseFormato12CDs()!=null ){
				for (FiseFormato12CD detalle : fiseFormato12CC.getFiseFormato12CDs()) {
					if( FiseConstants.ETAPA_EJECUCION_IMPLEMENTACION_COD == detalle.getId().getEtapaEjecucion() && formulario.getNroItemEtapa() == detalle.getId().getNumeroItemEtapa() ){
						detalleImplementacion = detalle;
						break;
					}else if( FiseConstants.ETAPA_EJECUCION_OPERATIVA_COD == detalle.getId().getEtapaEjecucion() && formulario.getNroItemEtapa() == detalle.getId().getNumeroItemEtapa() ){
						detalleOperativo = detalle;
						break;
					} 
				}
			}
			
			//IMPLEMENTACION
			if( FiseConstants.ETAPA_EJECUCION_IMPLEMENTACION_COD == formulario.getEtapaEjecucion() ){
				detalleImplementacion.setCodUbigeoOrigen(formulario.getCodDistritoOrigen());
				detalleImplementacion.setDescripcionLocalidadOrigen(formulario.getLocalidadOrigen());
				detalleImplementacion.setCodUbigeoDestino(formulario.getCodDistritoDestino());
				detalleImplementacion.setDescripcionLocalidadDestino(formulario.getLocalidadDestino());
				detalleImplementacion.setIdZonaBenef(formulario.getZonaBenef());
				detalleImplementacion.setCodigoCuentaContaEde(formulario.getCodCuentaContable());
				detalleImplementacion.setDescripcionActividad(formulario.getActividad());
				if( !FiseConstants.BLANCO.equals(formulario.getTipoDocumento()) ){
					detalleImplementacion.setIdTipDocRef(formulario.getTipoDocumento());
					detalleImplementacion.setRucEmpresaEmiteDocRef(formulario.getRucEmpresa());
					detalleImplementacion.setSerieDocumentoReferencia(formulario.getSerieDocumento());
					detalleImplementacion.setNumeroDocumentoReferencia(formulario.getNroDocumento());
				}
				detalleImplementacion.setNumeroDias(formulario.getNroDias());
				detalleImplementacion.setMontoAlimentacion(formulario.getMontoAlimentacion());
				detalleImplementacion.setMontoAlojamiento(formulario.getMontoAlojamiento());
				detalleImplementacion.setMontoMovilidad(formulario.getMontoMovilidad());
				
				BigDecimal total = new BigDecimal(0);
				total = total.add(detalleImplementacion.getMontoAlimentacion())
						.add(detalleImplementacion.getMontoAlojamiento())
						.add(detalleImplementacion.getMontoMovilidad());
				detalleImplementacion.setTotalGeneral(total);
				
				detalleImplementacion.setUsuarioActualizacion(formulario.getUsuario());
				detalleImplementacion.setTerminalActualizacion(formulario.getTerminal());
				detalleImplementacion.setFechaActualizacion(hoy);
				lista.add(detalleImplementacion);
			}
			
			//OPERATIVA
			if( FiseConstants.ETAPA_EJECUCION_OPERATIVA_COD == formulario.getEtapaEjecucion() ){
				detalleOperativo.setCodUbigeoOrigen(formulario.getCodDistritoOrigen());
				detalleOperativo.setDescripcionLocalidadOrigen(formulario.getLocalidadOrigen());
				detalleOperativo.setCodUbigeoDestino(formulario.getCodDistritoDestino());
				detalleOperativo.setDescripcionLocalidadDestino(formulario.getLocalidadDestino());
				detalleOperativo.setIdZonaBenef(formulario.getZonaBenef());
				detalleOperativo.setCodigoCuentaContaEde(formulario.getCodCuentaContable());
				detalleOperativo.setDescripcionActividad(formulario.getActividad());
				if( !FiseConstants.BLANCO.equals(formulario.getTipoDocumento()) ){
					detalleOperativo.setIdTipDocRef(formulario.getTipoDocumento());
					detalleOperativo.setRucEmpresaEmiteDocRef(formulario.getRucEmpresa());
					detalleOperativo.setSerieDocumentoReferencia(formulario.getSerieDocumento());
					detalleOperativo.setNumeroDocumentoReferencia(formulario.getNroDocumento());
				}
				detalleOperativo.setNumeroDias(formulario.getNroDias());
				detalleOperativo.setMontoAlimentacion(formulario.getMontoAlimentacion());
				detalleOperativo.setMontoAlojamiento(formulario.getMontoAlojamiento());
				detalleOperativo.setMontoMovilidad(formulario.getMontoMovilidad());
				
				BigDecimal total = new BigDecimal(0);
				total = total.add(detalleOperativo.getMontoAlimentacion())
						.add(detalleOperativo.getMontoAlojamiento())
						.add(detalleOperativo.getMontoMovilidad());
				detalleOperativo.setTotalGeneral(total);
				
				detalleOperativo.setUsuarioActualizacion(formulario.getUsuario());
				detalleOperativo.setTerminalActualizacion(formulario.getTerminal());
				detalleOperativo.setFechaActualizacion(hoy);
				lista.add(detalleOperativo);
			}
		
			fiseFormato12CC.setUsuarioActualizacion(formulario.getUsuario());
			fiseFormato12CC.setTerminalActualizacion(formulario.getTerminal());
			fiseFormato12CC.setFechaActualizacion(hoy);
		
			if( FiseConstants.TIPOARCHIVO_XLS.equals(formulario.getTipoArchivo()) ){
				fiseFormato12CC.setNombreArchivoExcel(formulario.getNombreArchivo());
			}else if( FiseConstants.TIPOARCHIVO_TXT.equals(formulario.getTipoArchivo()) ){
				fiseFormato12CC.setNombreArchivoTexto(formulario.getNombreArchivo());
			}
			
			formato12CCDao.modificarFormato12CC(fiseFormato12CC);
			//add
			for (FiseFormato12CD detalle : lista) {
				formato12CDDao.modificarFormato12CD(detalle);
			}
			if( lista != null && lista.size()>0 ){
				if( lista.size()==1 ){
					//enviamos la cabecera de informacion
					fiseFormato12CC.setAnoEjecucionDetalle(lista.get(0).getId().getAnoEjecucionGasto());
					fiseFormato12CC.setMesEjecucionDetalle(lista.get(0).getId().getMesEjecucionGasto());
					fiseFormato12CC.setEtapaEjecucionDetalle(lista.get(0).getId().getEtapaEjecucion());
					fiseFormato12CC.setNumeroItemEtapaDetalle(lista.get(0).getId().getNumeroItemEtapa());
				}
			}
			dto= fiseFormato12CC;
			
		}	catch (Exception e) {
			logger.error("--error"+e.getMessage());
			e.printStackTrace();
			throw e;
		}
		//
		return dto;

	}
	
}
