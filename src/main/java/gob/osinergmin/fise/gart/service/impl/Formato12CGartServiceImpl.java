package gob.osinergmin.fise.gart.service.impl;

import gob.osinergmin.fise.bean.Formato12CCBean;
import gob.osinergmin.fise.constant.FiseConstants;
import gob.osinergmin.fise.dao.ArchivoSustentoDao;
import gob.osinergmin.fise.dao.CommonDao;
import gob.osinergmin.fise.dao.FiseGrupoInformacionDao;
import gob.osinergmin.fise.dao.FiseObservacionDao;
import gob.osinergmin.fise.dao.FiseZonaBenefDao;
import gob.osinergmin.fise.dao.Formato12CCDao;
import gob.osinergmin.fise.dao.Formato12CDDao;
import gob.osinergmin.fise.dao.Formato12CDObDao;
import gob.osinergmin.fise.domain.FiseArchivosCab;
import gob.osinergmin.fise.domain.FiseArchivosDet;
import gob.osinergmin.fise.domain.FiseFormato12CC;
import gob.osinergmin.fise.domain.FiseFormato12CCPK;
import gob.osinergmin.fise.domain.FiseFormato12CD;
import gob.osinergmin.fise.domain.FiseFormato12CDOb;
import gob.osinergmin.fise.domain.FiseFormato12CDObPK;
import gob.osinergmin.fise.domain.FiseFormato12CDPK;
import gob.osinergmin.fise.domain.FiseGrupoInformacion;
import gob.osinergmin.fise.domain.FiseObservacion;
import gob.osinergmin.fise.domain.FiseZonaBenef;
import gob.osinergmin.fise.gart.service.Formato12CGartService;
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
	private Formato12CDObDao formato12CDObDao;
	
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
	public FiseFormato12CC obtenerFormato12CCByPK(FiseFormato12CCPK fiseFormato12CCPK) {
		FiseFormato12CC formato = null;
		formato = formato12CCDao.obtenerFormato12CCByPK(fiseFormato12CCPK);
		if(formato != null){
			List<FiseFormato12CD> lista = formato12CDDao.listarFormato12CDByFormato12CC(formato);
			long periodoEjecucion=0;
			for (FiseFormato12CD fiseFormato12CD : lista) {
				fiseFormato12CD.setCodEmpresaReport(fiseFormato12CD.getId().getCodEmpresa());
				fiseFormato12CD.setAnoPresentacionReport(fiseFormato12CD.getId().getAnoPresentacion());
				fiseFormato12CD.setMesPresentacionReport(fiseFormato12CD.getId().getMesPresentacion());
				fiseFormato12CD.setEtapaReport(fiseFormato12CD.getId().getEtapa());
				fiseFormato12CD.setAnoEjecucionGastoReport(fiseFormato12CD.getId().getAnoEjecucionGasto());
				fiseFormato12CD.setMesEjecucionGastoReport(fiseFormato12CD.getId().getMesEjecucionGasto());
				fiseFormato12CD.setNumeroItemEtapaReport(fiseFormato12CD.getId().getNumeroItemEtapa());
				fiseFormato12CD.setEtapaEjecucionReport(fiseFormato12CD.getId().getEtapaEjecucion());
				if( periodoEjecucion==0 ){
					periodoEjecucion = fiseFormato12CD.getId().getAnoEjecucionGasto()*100+fiseFormato12CD.getId().getMesEjecucionGasto();
					formato.setAnoEjecucionView(fiseFormato12CD.getId().getAnoEjecucionGasto());
					formato.setMesEjecucionView(fiseFormato12CD.getId().getMesEjecucionGasto());
				}else if( fiseFormato12CD.getId().getAnoEjecucionGasto()*100+fiseFormato12CD.getId().getMesEjecucionGasto() > periodoEjecucion ){
					periodoEjecucion = fiseFormato12CD.getId().getAnoEjecucionGasto()*100+fiseFormato12CD.getId().getMesEjecucionGasto();
					formato.setAnoEjecucionView(fiseFormato12CD.getId().getAnoEjecucionGasto());
					formato.setMesEjecucionView(fiseFormato12CD.getId().getMesEjecucionGasto());
				}
			}
			formato.setFiseFormato12CDs(lista);
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
		//Obtener datos de PK del detalle(se inserto 8 campos de PK en el objeto FiseFormato12DD 
		//para tener todos los campos en un solo objeto y poder leer los campos en iReport)
		List<FiseFormato12CD> lista = null;
		lista = formato12CDDao.listarFormato12CDByFormato12CC(formato12CC);
		for (FiseFormato12CD fiseFormato12CD : lista) {
			fiseFormato12CD.setCodEmpresaReport(fiseFormato12CD.getId().getCodEmpresa());
			fiseFormato12CD.setAnoPresentacionReport(fiseFormato12CD.getId().getAnoPresentacion());
			fiseFormato12CD.setMesPresentacionReport(fiseFormato12CD.getId().getMesPresentacion());
			fiseFormato12CD.setEtapaReport(fiseFormato12CD.getId().getEtapa());
			fiseFormato12CD.setAnoEjecucionGastoReport(fiseFormato12CD.getId().getAnoEjecucionGasto());
			fiseFormato12CD.setMesEjecucionGastoReport(fiseFormato12CD.getId().getMesEjecucionGasto());
			fiseFormato12CD.setNumeroItemEtapaReport(fiseFormato12CD.getId().getNumeroItemEtapa());
			fiseFormato12CD.setEtapaEjecucionReport(fiseFormato12CD.getId().getEtapaEjecucion());
		}
		return lista;
	}
	
	@Override
	@Transactional(propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
	public FiseFormato12CC registrarFormato12CCregistrarFormato12CD(Formato12CCBean formulario) throws Exception {
		
		FiseFormato12CC dto = null;
		
		try {
			Date hoy = FechaUtil.obtenerFechaActual();
			FiseFormato12CC fiseFormato12CC = new FiseFormato12CC();
			//guardar el pk
			FiseFormato12CCPK id = new FiseFormato12CCPK();
			id.setCodEmpresa(formulario.getCodigoEmpresa());
			id.setAnoPresentacion(formulario.getAnioPresentacion());
			id.setMesPresentacion(formulario.getMesPresentacion());
			//id.setEtapa(FiseConstants.ETAPA_SOLICITUD);
			id.setEtapa(formulario.getEtapa());
			if( FiseConstants.TIPOARCHIVO_XLS.equals(formulario.getTipoArchivo()) ){
				fiseFormato12CC.setNombreArchivoExcel(formulario.getNombreArchivo());
			}else if( FiseConstants.TIPOARCHIVO_TXT.equals(formulario.getTipoArchivo()) ){
				fiseFormato12CC.setNombreArchivoTexto(formulario.getNombreArchivo());
			}/*else{
				id.setEtapa(formulario.getEtapa());
			}*/
			
			fiseFormato12CC.setId(id);
			
			FiseGrupoInformacion grupoInfo = null;
			long idGrupoInf = commonDao.obtenerIdGrupoInformacion(formulario.getAnioPresentacion(), formulario.getMesPresentacion(), FiseConstants.FRECUENCIA_MENSUAL_DESCRIPCION); 
			if(idGrupoInf!=0){
				grupoInfo = fiseGrupoInformacionDao.obtenerFiseGrupoInformacionByPK(idGrupoInf);	
			}	
			fiseFormato12CC.setFiseGrupoInformacion(grupoInfo);
			
			List<FiseFormato12CD> lista = new ArrayList<FiseFormato12CD>();
			
			if( FiseConstants.ETAPA_EJECUCION_IMPLEMENTACION_COD == formulario.getEtapaEjecucion() ){
				
				if( formulario.getAnioEjecucion() != 0 ||
						formulario.getMesEjecucion() != 0 ||
						formulario.getNroItemEtapa() != 0 ||
						!formulario.getCodUbigeoOrigen().equals(FiseConstants.BLANCO) ||
						!formulario.getLocalidadOrigen().equals(FiseConstants.BLANCO) ||
						!formulario.getCodUbigeoDestino().equals(FiseConstants.BLANCO) ||
						!formulario.getLocalidadDestino().equals(FiseConstants.BLANCO) ||
						formulario.getZonaBenef() != 0 ||
						!formulario.getCodCuentaContable().equals(FiseConstants.BLANCO) ||
						!formulario.getActividad().equals(FiseConstants.BLANCO) ||
						!formulario.getTipoDocumento().equals(FiseConstants.BLANCO) ||
						!formulario.getRucEmpresa().equals(FiseConstants.BLANCO) ||
						!formulario.getSerieDocumento().equals(FiseConstants.BLANCO) ||
						!formulario.getNroDocumento().equals(FiseConstants.BLANCO) ||
						formulario.getNroDias() != 0 ||
						!formulario.getMontoAlimentacion().equals(BigDecimal.ZERO) ||
						!formulario.getMontoAlojamiento().equals(BigDecimal.ZERO) ||
						!formulario.getMontoMovilidad().equals(BigDecimal.ZERO) 
						){
					
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
					
					detalleImplementacion.setCodUbigeoOrigen(formulario.getCodUbigeoOrigen());
					detalleImplementacion.setDescripcionLocalidadOrigen(formulario.getLocalidadOrigen());
					detalleImplementacion.setCodUbigeoDestino(formulario.getCodUbigeoDestino());
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
				
			}
			
			if( FiseConstants.ETAPA_EJECUCION_OPERATIVA_COD == formulario.getEtapaEjecucion() ){
				
				if( formulario.getAnioEjecucion() != 0 ||
						formulario.getMesEjecucion() != 0 ||
						formulario.getNroItemEtapa() != 0 ||
						!formulario.getCodUbigeoOrigen().equals(FiseConstants.BLANCO) ||
						!formulario.getLocalidadOrigen().equals(FiseConstants.BLANCO) ||
						!formulario.getCodUbigeoDestino().equals(FiseConstants.BLANCO) ||
						!formulario.getLocalidadDestino().equals(FiseConstants.BLANCO) ||
						formulario.getZonaBenef() != 0 ||
						!formulario.getCodCuentaContable().equals(FiseConstants.BLANCO) ||
						!formulario.getActividad().equals(FiseConstants.BLANCO) ||
						!formulario.getTipoDocumento().equals(FiseConstants.BLANCO) ||
						!formulario.getRucEmpresa().equals(FiseConstants.BLANCO) ||
						!formulario.getSerieDocumento().equals(FiseConstants.BLANCO) ||
						!formulario.getNroDocumento().equals(FiseConstants.BLANCO) ||
						formulario.getNroDias() != 0 ||
						!formulario.getMontoAlimentacion().equals(BigDecimal.ZERO) ||
						!formulario.getMontoAlojamiento().equals(BigDecimal.ZERO) ||
						!formulario.getMontoMovilidad().equals(BigDecimal.ZERO) 
						){
					
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
					
					detalleOperativo.setCodUbigeoOrigen(formulario.getCodUbigeoOrigen());
					detalleOperativo.setDescripcionLocalidadOrigen(formulario.getLocalidadOrigen());
					detalleOperativo.setCodUbigeoDestino(formulario.getCodUbigeoDestino());
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
				throw new Exception("El Formato ya existe para la Distribuidora Eléctrica y Periodo a Declarar seleccionado");
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
			throw new Exception("Se produjo un error al guardar los datos del Formato 12C");
		}
		return dto;
	}
	
	@Override
	@Transactional(propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
	public FiseFormato12CC modificarFormato12CCregistrarFormato12CD(Formato12CCBean formulario, FiseFormato12CC fiseFormato12CC) throws Exception {
		
		FiseFormato12CC dto = null;
		
		try {
			
			Date hoy = FechaUtil.obtenerFechaActual();
	
			List<FiseFormato12CD> lista = new ArrayList<FiseFormato12CD>();
			
			if( FiseConstants.ETAPA_EJECUCION_IMPLEMENTACION_COD == formulario.getEtapaEjecucion() ){
				
				if( formulario.getAnioEjecucion() != 0 ||
						formulario.getMesEjecucion() != 0 ||
						formulario.getNroItemEtapa() != 0 ||
						!formulario.getCodUbigeoOrigen().equals(FiseConstants.BLANCO) ||
						!formulario.getLocalidadOrigen().equals(FiseConstants.BLANCO) ||
						!formulario.getCodUbigeoDestino().equals(FiseConstants.BLANCO) ||
						!formulario.getLocalidadDestino().equals(FiseConstants.BLANCO) ||
						formulario.getZonaBenef() != 0 ||
						!formulario.getCodCuentaContable().equals(FiseConstants.BLANCO) ||
						!formulario.getActividad().equals(FiseConstants.BLANCO) ||
						!formulario.getTipoDocumento().equals(FiseConstants.BLANCO) ||
						!formulario.getRucEmpresa().equals(FiseConstants.BLANCO) ||
						!formulario.getSerieDocumento().equals(FiseConstants.BLANCO) ||
						!formulario.getNroDocumento().equals(FiseConstants.BLANCO) ||
						formulario.getNroDias() != 0 ||
						!formulario.getMontoAlimentacion().equals(BigDecimal.ZERO) ||
						!formulario.getMontoAlojamiento().equals(BigDecimal.ZERO) ||
						!formulario.getMontoMovilidad().equals(BigDecimal.ZERO) 
						){
					
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
					
					detalleImplementacion.setCodUbigeoOrigen(formulario.getCodUbigeoOrigen());
					detalleImplementacion.setDescripcionLocalidadOrigen(formulario.getLocalidadOrigen());
					detalleImplementacion.setCodUbigeoDestino(formulario.getCodUbigeoDestino());
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
				
			}
			
			if( FiseConstants.ETAPA_EJECUCION_OPERATIVA_COD == formulario.getEtapaEjecucion() ){
				
				if( formulario.getAnioEjecucion() != 0 ||
						formulario.getMesEjecucion() != 0 ||
						formulario.getNroItemEtapa() != 0 ||
						!formulario.getCodUbigeoOrigen().equals(FiseConstants.BLANCO) ||
						!formulario.getLocalidadOrigen().equals(FiseConstants.BLANCO) ||
						!formulario.getCodUbigeoDestino().equals(FiseConstants.BLANCO) ||
						!formulario.getLocalidadDestino().equals(FiseConstants.BLANCO) ||
						formulario.getZonaBenef() != 0 ||
						!formulario.getCodCuentaContable().equals(FiseConstants.BLANCO) ||
						!formulario.getActividad().equals(FiseConstants.BLANCO) ||
						!formulario.getTipoDocumento().equals(FiseConstants.BLANCO) ||
						!formulario.getRucEmpresa().equals(FiseConstants.BLANCO) ||
						!formulario.getSerieDocumento().equals(FiseConstants.BLANCO) ||
						!formulario.getNroDocumento().equals(FiseConstants.BLANCO) ||
						formulario.getNroDias() != 0 ||
						!formulario.getMontoAlimentacion().equals(BigDecimal.ZERO) ||
						!formulario.getMontoAlojamiento().equals(BigDecimal.ZERO) ||
						!formulario.getMontoMovilidad().equals(BigDecimal.ZERO) 
						){
					
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
					
					detalleOperativo.setCodUbigeoOrigen(formulario.getCodUbigeoOrigen());
					detalleOperativo.setDescripcionLocalidadOrigen(formulario.getLocalidadOrigen());
					detalleOperativo.setCodUbigeoDestino(formulario.getCodUbigeoDestino());
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
				formato12CDDao.registrarFormato12CD(detalle);
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
	
	@Override
	@Transactional(propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
	public FiseFormato12CC modificarFormato12CCmodificarFormato12CD(Formato12CCBean formulario, FiseFormato12CC fiseFormato12CC) throws Exception {
		
		FiseFormato12CC dto = null;
		
		try {
			
			Date hoy = FechaUtil.obtenerFechaActual();
	
			List<FiseFormato12CD> lista = new ArrayList<FiseFormato12CD>();
			
			FiseFormato12CD detalleImplementacion = new FiseFormato12CD();
			FiseFormato12CD detalleOperativo = new FiseFormato12CD();
			if( fiseFormato12CC.getFiseFormato12CDs()!=null ){
				for (FiseFormato12CD detalle : fiseFormato12CC.getFiseFormato12CDs()) {
							
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
						!formulario.getCodUbigeoOrigen().equals(FiseConstants.BLANCO) ||
						!formulario.getLocalidadOrigen().equals(FiseConstants.BLANCO) ||
						!formulario.getCodUbigeoDestino().equals(FiseConstants.BLANCO) ||
						!formulario.getLocalidadDestino().equals(FiseConstants.BLANCO) ||
						formulario.getZonaBenef() != 0 ||
						!formulario.getCodCuentaContable().equals(FiseConstants.BLANCO) ||
						!formulario.getActividad().equals(FiseConstants.BLANCO) ||
						!formulario.getTipoDocumento().equals(FiseConstants.BLANCO) ||
						!formulario.getRucEmpresa().equals(FiseConstants.BLANCO) ||
						!formulario.getSerieDocumento().equals(FiseConstants.BLANCO) ||
						!formulario.getNroDocumento().equals(FiseConstants.BLANCO) ||
						formulario.getNroDias() != 0 ||
						!formulario.getMontoAlimentacion().equals(BigDecimal.ZERO) ||
						!formulario.getMontoAlojamiento().equals(BigDecimal.ZERO) ||
						!formulario.getMontoMovilidad().equals(BigDecimal.ZERO) 
						){
					
					detalleImplementacion.setCodUbigeoOrigen(formulario.getCodUbigeoOrigen());
					detalleImplementacion.setDescripcionLocalidadOrigen(formulario.getLocalidadOrigen());
					detalleImplementacion.setCodUbigeoDestino(formulario.getCodUbigeoDestino());
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
				
				
			}
			
			//OPERATIVA
			if( FiseConstants.ETAPA_EJECUCION_OPERATIVA_COD == formulario.getEtapaEjecucion() ){
				
				if( formulario.getAnioEjecucion() != 0 ||
						formulario.getMesEjecucion() != 0 ||
						formulario.getNroItemEtapa() != 0 ||
						!formulario.getCodUbigeoOrigen().equals(FiseConstants.BLANCO) ||
						!formulario.getLocalidadOrigen().equals(FiseConstants.BLANCO) ||
						!formulario.getCodUbigeoDestino().equals(FiseConstants.BLANCO) ||
						!formulario.getLocalidadDestino().equals(FiseConstants.BLANCO) ||
						formulario.getZonaBenef() != 0 ||
						!formulario.getCodCuentaContable().equals(FiseConstants.BLANCO) ||
						!formulario.getActividad().equals(FiseConstants.BLANCO) ||
						!formulario.getTipoDocumento().equals(FiseConstants.BLANCO) ||
						!formulario.getRucEmpresa().equals(FiseConstants.BLANCO) ||
						!formulario.getSerieDocumento().equals(FiseConstants.BLANCO) ||
						!formulario.getNroDocumento().equals(FiseConstants.BLANCO) ||
						formulario.getNroDias() != 0 ||
						!formulario.getMontoAlimentacion().equals(BigDecimal.ZERO) ||
						!formulario.getMontoAlojamiento().equals(BigDecimal.ZERO) ||
						!formulario.getMontoMovilidad().equals(BigDecimal.ZERO) 
						){
					
					detalleOperativo.setCodUbigeoOrigen(formulario.getCodUbigeoOrigen());
					detalleOperativo.setDescripcionLocalidadOrigen(formulario.getLocalidadOrigen());
					detalleOperativo.setCodUbigeoDestino(formulario.getCodUbigeoDestino());
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
	
	@Override
	@Transactional(propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
	public void eliminarFormato12CC(FiseFormato12CC fiseFormato12CC) {
		List<FiseFormato12CD> lista = null;
		lista = formato12CDDao.listarFormato12CDByFormato12CC(fiseFormato12CC);
		for (FiseFormato12CD detalle : lista) {
			List<FiseFormato12CDOb> listaObservacion = formato12CDObDao.listarFormato12CDObByFormato12CD(detalle);
			for (FiseFormato12CDOb observacion : listaObservacion) {
				formato12CDObDao.eliminarFormato12CDOb(observacion);
			}
			formato12CDDao.eliminarFormato12CD(detalle);
		}
		formato12CCDao.eliminarFormato12CC(fiseFormato12CC);		
		//cambios elozano eliminar fiseArchivoCab y su detalle
		String valor = eliminarArchivoSustentoCab(fiseFormato12CC);
		logger.info("Valor al eliminar archivos de sustento si es 1= OK caso contrario error:  "+valor); 
	}
	
	//@Transactional
	private String eliminarArchivoSustentoCab(FiseFormato12CC f){
		String valor = "0";
		List<FiseArchivosCab> listaCab =null;
		List<FiseArchivosDet> listaDet = null;
		try {
			BigDecimal anioEjec = null;
			BigDecimal mesEjec = null;					
			listaCab = archivoSustentoDao.listaFiseArchivosCabMensual(f.getId().getCodEmpresa(),
					f.getId().getAnoPresentacion(), f.getId().getMesPresentacion(),
					anioEjec, mesEjec, f.getId().getEtapa(),FiseConstants.NOMBRE_FORMATO_12C);
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
	public void eliminarFormato12CD(FiseFormato12CD fiseFormato12CD) {
		List<FiseFormato12CDOb> listaObservacion = formato12CDObDao.listarFormato12CDObByFormato12CD(fiseFormato12CD);
		for (FiseFormato12CDOb observacion : listaObservacion) {
			formato12CDObDao.eliminarFormato12CDOb(observacion);
		}
		formato12CDDao.eliminarFormato12CD(fiseFormato12CD);
	}
	
	@Override
	public Formato12CCBean estructurarFormato12CBeanByFiseFormato12CC(FiseFormato12CC formato){
		Formato12CCBean formato12CBean = new Formato12CCBean();
		formato12CBean.setAnioPresentacion(formato.getId().getAnoPresentacion());
		formato12CBean.setMesPresentacion(formato.getId().getMesPresentacion());
		return formato12CBean;
	}

	@Override
	public HashMap<String, Object> mapearParametrosFormato12C(Formato12CCBean formato12CBean){
		HashMap<String, Object> mapJRParams = new HashMap<String, Object>();
		mapJRParams.put(FiseConstants.PARAM_DESC_EMPRESA, formato12CBean.getDescEmpresa());
		mapJRParams.put(FiseConstants.PARAM_ANO_PRESENTACION, formato12CBean.getAnioPresentacion());
		mapJRParams.put(FiseConstants.PARAM_DESC_MES_PRESENTACION, formato12CBean.getDescMesPresentacion());
		return mapJRParams;
	}
	
	@Override
	@Transactional
	public List<FiseFormato12CDOb> listarFormato12CDObByFormato12CD(FiseFormato12CD formato12CD){
		return formato12CDObDao.listarFormato12CDObByFormato12CD(formato12CD); 
	}
	
	@Override
	@Transactional(propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
	public String modificarEnvioDefinitivoFormato12CC(String user,String terminal, 
			FiseFormato12CC fiseFormato12CC) throws Exception {		
		String valor = "0";		
		try{
			fiseFormato12CC.setFechaEnvioDefinitivo(FechaUtil.obtenerFechaActual());
			fiseFormato12CC.setUsuarioActualizacion(user);
			fiseFormato12CC.setTerminalActualizacion(terminal);
			fiseFormato12CC.setFechaActualizacion(FechaUtil.obtenerFechaActual());
			formato12CCDao.modificarFormato12CC(fiseFormato12CC);
			valor= "1";
		} catch (Exception e) {
			valor = "0";
			logger.error("--error"+e.getMessage());
			e.printStackTrace();			
		}
		return valor;
	}
	
	
	@Override
	@Transactional(propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
	public void eliminarObservaciones12C(List<FiseFormato12CDOb> listaObs) throws Exception {	
		for (FiseFormato12CDOb observacion : listaObs) {
			formato12CDObDao.eliminarFormato12CDOb(observacion); 
		}
	} 
	
	
	@Override
	@Transactional(propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
	public String insertarObservacion12C(String codEmpresa,long anioPres,long mesPres,
			long anioEjec,long mesEjec,String etapa,long etapaEjec,long itemEtapa, 
			String desObservacion,String user,String terminal,
			String idObsExistente,String tipoObservacion) throws Exception{
		String valor = "0";
		FiseObservacion observacion = null;
		FiseFormato12CDObPK pk = null;
		FiseFormato12CDOb obs = null;
		try {
			long maxItemObs = formato12CDObDao.buscarMaximoItemObs12C(codEmpresa, anioPres, mesPres, 
					anioEjec, mesEjec, etapa, etapaEjec, itemEtapa);	
			if(FiseConstants.TIPO_OBSERVACION_MANUAL.equals(tipoObservacion)){
				String idObservacion = fiseObservacionDao.obtenerIdObservacion();			
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
			pk = new FiseFormato12CDObPK();
			pk.setCodEmpresa(codEmpresa);
			pk.setAnoPresentacion(anioPres);
			pk.setMesPresentacion(mesPres);
			pk.setAnoEjecucionGasto(anioEjec);
			pk.setMesEjecucionGasto(mesEjec);
			pk.setEtapa(etapa);
			pk.setEtapaEjecucion(etapaEjec);
			pk.setNumeroItemEtapa(itemEtapa); 
			pk.setItemObservacion(maxItemObs);  	
			obs = new FiseFormato12CDOb();
			obs.setId(pk);
			obs.setFechaCreacion(FechaUtil.obtenerFechaActual());			
			obs.setFiseObservacion(observacion);
			obs.setUsuarioCreacion(user);
			obs.setTerminalCreacion(terminal);		
			formato12CDObDao.insertarFiseFormato12CObs(obs); 
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
	public String eliminarObservacion12C(String codEmpresa,long anioPres,long mesPres,
			long anioEjec,long mesEjec,String etapa,long etapaEjec,long itemEtapa, 
			String idObservacion,long itemObservacion) throws Exception{
		String valor = "0";
		FiseObservacion observacion = null;
		FiseFormato12CDObPK pk = null;
		FiseFormato12CDOb obs = null;
		try {			
			pk = new FiseFormato12CDObPK();
			pk.setCodEmpresa(codEmpresa);
			pk.setAnoPresentacion(anioPres);
			pk.setMesPresentacion(mesPres);
			pk.setAnoEjecucionGasto(anioEjec);
			pk.setMesEjecucionGasto(mesEjec);
			pk.setEtapa(etapa);
			pk.setEtapaEjecucion(etapaEjec);
			pk.setNumeroItemEtapa(itemEtapa); 
			pk.setItemObservacion(itemObservacion);  	
			obs = formato12CDObDao.obtenerFiseFormato12CDOb(pk);			
			formato12CDObDao.eliminarFormato12CDOb(obs); 
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
