package gob.osinergmin.fise.gart.service.impl;

import gob.osinergmin.fise.bean.Formato13ACBean;
import gob.osinergmin.fise.bean.Formato13ADReportBean;
import gob.osinergmin.fise.constant.FiseConstants;
import gob.osinergmin.fise.dao.ArchivoSustentoDao;
import gob.osinergmin.fise.dao.CommonDao;
import gob.osinergmin.fise.dao.FiseGrupoInformacionDao;
import gob.osinergmin.fise.dao.FiseObservacionDao;
import gob.osinergmin.fise.dao.FiseZonaBenefDao;
import gob.osinergmin.fise.dao.Formato13ACDao;
import gob.osinergmin.fise.dao.Formato13ADDao;
import gob.osinergmin.fise.dao.Formato13ADObDao;
import gob.osinergmin.fise.domain.FiseArchivosCab;
import gob.osinergmin.fise.domain.FiseArchivosDet;
import gob.osinergmin.fise.domain.FiseFormato13AC;
import gob.osinergmin.fise.domain.FiseFormato13ACPK;
import gob.osinergmin.fise.domain.FiseFormato13AD;
import gob.osinergmin.fise.domain.FiseFormato13ADOb;
import gob.osinergmin.fise.domain.FiseFormato13ADObPK;
import gob.osinergmin.fise.domain.FiseFormato13ADPK;
import gob.osinergmin.fise.domain.FiseGrupoInformacion;
import gob.osinergmin.fise.domain.FiseObservacion;
import gob.osinergmin.fise.gart.service.Formato13AGartService;
import gob.osinergmin.fise.util.FechaUtil;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

@Service(value="formato13AGartServiceImpl")
public class Formato13AGartServiceImpl implements Formato13AGartService {
	
	Logger logger=Logger.getLogger(Formato13AGartServiceImpl.class);

	@Autowired
	@Qualifier("formato13ACDaoImpl")
	private Formato13ACDao formato13ACDao;
	
	@Autowired
	@Qualifier("formato13ADDaoImpl")
	private Formato13ADDao formato13ADDao;
	
	@Autowired
	@Qualifier("formato13ADObDaoImpl")
	private Formato13ADObDao formato13ADObDao;
	
	@Autowired
	@Qualifier("fiseObservacionDaoImpl")
	private FiseObservacionDao fiseObservacionDao;
	
	@Autowired
	@Qualifier("commonDaoImpl")
	private CommonDao commonDao;
	
	@Autowired
	@Qualifier("fiseGrupoInformacionDaoImpl")
	private FiseGrupoInformacionDao fiseGrupoInformacionDao;
	
	@Autowired
	@Qualifier("fiseZonaBenefDaoImpl")
	private FiseZonaBenefDao zonaBenefDao;
	
	@Autowired
	@Qualifier("archivoSustentoDaoImpl")
	private ArchivoSustentoDao archivoSustentoDao;
	
	public List<FiseFormato13AC> buscarFormato13AC(String codEmpresa,
			long anioDesde, long mesDesde, long anioHasta, long mesHasta,
			String etapa) {
		List<FiseFormato13AC> lista = null;
		lista = formato13ACDao.buscarFormato13AC(codEmpresa, anioDesde, mesDesde, anioHasta, mesHasta, etapa);
		/*for (FiseFormato13AC formato : lista) {
			formato.setFiseFormato13ADs(formato12ADDao.listarFormato12ADByFormato12AC(formato));
		}*/
		return lista;
	}
	
	public List<FiseFormato13AD> listarFormato13ADByFormato13AC(FiseFormato13AC formato13AC) {
		List<FiseFormato13AD> lista = null;
		lista = formato13ADDao.listarFormato13ADByFormato13AC(formato13AC);
		/*for (FiseFormato13AC formato : lista) {
			formato.setFiseFormato13ADs(formato12ADDao.listarFormato12ADByFormato12AC(formato));
		}*/
		return lista;
	}
	
	public Formato13ACBean estructurarFormato13ABeanByFiseFormato13AC(FiseFormato13AC formato) {
		
		//setting the values of Bean
		Formato13ACBean formato13ABean = new Formato13ACBean();
		formato13ABean.setAnioPresent(formato.getId().getAnoPresentacion());
		formato13ABean.setMesPresent(formato.getId().getMesPresentacion());
		return formato13ABean;
     }

	
	public HashMap<String, Object> mapearParametrosFormato13A(Formato13ACBean formato13ABean) {
				
		HashMap<String, Object> mapJRParams = new HashMap<String, Object>();
		mapJRParams.put(FiseConstants.PARAM_DESC_EMPRESA_F13A, formato13ABean.getDescEmpresa());
		mapJRParams.put(FiseConstants.PARAM_ANO_PRES_F13A, formato13ABean.getAnioPresent());
		mapJRParams.put(FiseConstants.PARAM_DESC_MES_PRES_F13A, formato13ABean.getDescMesPresentacion());
		
		return mapJRParams;
	}	
	
	@Transactional
	public  List<Formato13ADReportBean> listarLocalidadesPorZonasBenefFormato13ADByFormato13AC(FiseFormato13AC formato13AC){
		return formato13ADDao.listarLocalidadesPorZonasBenefFormato13ADByFormato13AC(formato13AC);
	}

	@Transactional
	public FiseFormato13AC savecabecera(FiseFormato13AC fiseC) throws DataIntegrityViolationException {
		FiseGrupoInformacion grupoInfo = null;
		try {
			long idGrupoInf = commonDao.obtenerIdGrupoInformacion(fiseC.getId().getAnoPresentacion(), fiseC.getId().getMesPresentacion(), FiseConstants.FRECUENCIA_BIENAL_DESCRIPCION); 
			if(idGrupoInf!=0){
				grupoInfo = fiseGrupoInformacionDao.obtenerFiseGrupoInformacionByPK(idGrupoInf);	
			}	
			logger.info("Grupo de irfomacion:  "+grupoInfo); 
			fiseC.setFiseGrupoInformacion(grupoInfo);	
		} catch (Exception e) {
			e.printStackTrace();
		}		
		return formato13ACDao.savecabecera(fiseC);
	}

	@Transactional
	public FiseFormato13AD savedetalle(FiseFormato13AD fiseD) throws DataIntegrityViolationException{
		return formato13ADDao.savedetalle(fiseD);
	}

	public FiseFormato13AC obtenerFormato13ACByPK(FiseFormato13ACPK fiseFormato13ACPK){
		FiseFormato13AC formato = null;
		formato = formato13ACDao.obtenerFormato13ACByPK(fiseFormato13ACPK);
		if(formato != null){
			List<FiseFormato13AD> listaDetalle = formato13ADDao.listarFormato13ADByFormato13AC(formato);
			//mostramos la fecha inicio fin de vigencia de uno de los detalles
			if( listaDetalle!=null && !listaDetalle.isEmpty() ){
				formato.setFiseFormato13ADs(listaDetalle);
				for (FiseFormato13AD detalle : listaDetalle) {
					if( (detalle.getAnoInicioVigencia()!=null && detalle.getAnoInicioVigencia()!=0) && 
							(detalle.getAnoFinVigencia()!=null && detalle.getAnoFinVigencia()!=0) ){
						formato.setAnoInicioVigenciaDetalle(detalle.getAnoInicioVigencia());
						formato.setAnoFinVigenciaDetalle(detalle.getAnoFinVigencia());
						break;
					}
				}
			}
		}
		return formato;
	}
	
	@Transactional
	public FiseFormato13AC updatecabecera(FiseFormato13AC fiseC) {
		return formato13ACDao.updatecabecera(fiseC);
	}

	@Transactional
	public FiseFormato13AD updatedetalle(FiseFormato13AD fiseD) {
		return formato13ADDao.updatedetalle(fiseD);
	}
	
	@Override
	@Transactional
	public List<FiseFormato13ADOb> listarFormato13ADObByFormato13AD(FiseFormato13AD formato13AD){
		return formato13ADObDao.listarFormato13ADObByFormato13AD(formato13AD); 
	}

	@Override
	@Transactional
	public Integer deletedetalle(String emp, Long anio, Long mes, String etapa) {
		// TODO Auto-generated method stub
		return formato13ADDao.deletedetalle(emp, anio, mes, etapa);
	}
	
	//eliminar cabecera con detalle y observaciones
	@Override
	@Transactional(propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
	public void eliminarCabecera(FiseFormato13AC fiseFormato13AC) {
		List<FiseFormato13AD> lista = null;
		lista = formato13ADDao.listarFormato13ADByFormato13AC(fiseFormato13AC);
		for (FiseFormato13AD detalle : lista) {
			List<FiseFormato13ADOb> listaObservacion = formato13ADObDao.listarFormato13ADObByFormato13AD(detalle);
			for (FiseFormato13ADOb observacion : listaObservacion) {
				formato13ADObDao.eliminarFormato13ADOb(observacion);
			}
			formato13ADDao.eliminarFormato13AD(detalle);
		}
		formato13ACDao.eliminarFormato13AC(fiseFormato13AC);		
		//cambios elozano eliminar fiseArchivoCab y su detalle
		String valor = eliminarArchivoSustentoCab(fiseFormato13AC);
		logger.info("Valor al eliminar archivos de sustento si es 1= OK caso contrario error:  "+valor); 
	}

	//@Transactional
	private String eliminarArchivoSustentoCab(FiseFormato13AC f){
		String valor = "0";
		List<FiseArchivosCab> listaCab =null;
		List<FiseArchivosDet> listaDet = null;
		try {
			BigDecimal anioInioVig = null;
			BigDecimal anioFinVig = null;					
			listaCab = archivoSustentoDao.listaFiseArchivosCabBienal(f.getId().getCodEmpresa(),
					f.getId().getAnoPresentacion(), f.getId().getMesPresentacion(),
					anioInioVig, anioFinVig, f.getId().getEtapa(),FiseConstants.NOMBRE_FORMATO_13A);
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
	public void eliminarDetalle(FiseFormato13AD fiseFormato13AD) {
		List<FiseFormato13ADOb> listaObservacion = formato13ADObDao.listarFormato13ADObByFormato13AD(fiseFormato13AD);
		for (FiseFormato13ADOb observacion : listaObservacion) {
			formato13ADObDao.eliminarFormato13ADOb(observacion);
		}
		formato13ADDao.eliminarFormato13AD(fiseFormato13AD);
	}

	@Override
	public FiseFormato13AC getCabecera(FiseFormato13ACPK fiseFormato13ACPK) {
		return formato13ACDao.getCabecera(fiseFormato13ACPK);
	}
	
	
	@Override
	@Transactional(propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
	public void eliminarObservaciones13A(List<FiseFormato13ADOb> listaObs) throws Exception{		
		for (FiseFormato13ADOb observacion : listaObs) {
			formato13ADObDao.eliminarFormato13ADOb(observacion);
		}			
	}
	
	@Override
	@Transactional
	public boolean existeFormato13AC(FiseFormato13AC fiseFormato13AC){
		return formato13ACDao.existeFormato13AC(fiseFormato13AC);
	}
	
	@Override
	@Transactional
	public boolean existeFormatoDetalleSectorTipico(FiseFormato13AC fiseFormato13AC, String codUbigeo, Long idZonaBenef){
		return formato13ADDao.existeFormatoDetalleSectorTipico(fiseFormato13AC, codUbigeo, idZonaBenef);
	}
	
	
	
	@Override
	@Transactional(propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
	public String insertarObservacion13A(String codEmpresa,long anioPres,long mesPres,
			String ubigeo,String sector,String etapa,long idZona,
			String desObservacion,String user,String terminal) throws Exception{
		String valor = "0";
		FiseObservacion observacion = null;
		FiseFormato13ADObPK pk = null;
		FiseFormato13ADOb obs = null;
		try {
			long maxItemObs = formato13ADObDao.buscarMaximoItemObs13A(codEmpresa, anioPres,
					mesPres, ubigeo, sector, etapa, idZona);
			 
			String idObservacion = fiseObservacionDao.obtenerIdObservacion();
			
			observacion = new FiseObservacion();
			observacion.setIdObservacion(idObservacion); 
			observacion.setDescripcion(desObservacion);
			observacion.setOrigen(FiseConstants.OBSERVACION_MANUAL); 
			observacion.setUsuarioCreacion(user);
			observacion.setTerminalCreacion(terminal); 
			observacion.setFechaCreacion(FechaUtil.obtenerFechaActual()); 
			fiseObservacionDao.insertarFiseObservacion(observacion);
			pk = new FiseFormato13ADObPK();
			pk.setCodEmpresa(codEmpresa);
			pk.setAnoPresentacion(anioPres);
			pk.setMesPresentacion(mesPres);
			pk.setCodUbigeo(ubigeo);
			pk.setCodSectorTipico(sector);			
			pk.setEtapa(etapa);
			pk.setIdZonaBenef(idZona);		
			pk.setItemObservacion(maxItemObs); 	
			obs = new FiseFormato13ADOb();
			obs.setId(pk);
			obs.setFechaCreacion(FechaUtil.obtenerFechaActual());			
			obs.setFiseObservacion(observacion);
			obs.setUsuarioCreacion(user);
			obs.setTerminalCreacion(terminal);		
			formato13ADObDao.insertarFiseFormato13AObs(obs); 
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
	public String eliminarObservacion13A(String codEmpresa,long anioPres,long mesPres,
			String ubigeo,String sector,String etapa,long idZona,
			String idObservacion,long itemObservacion) throws Exception{
		String valor = "0";
		FiseObservacion observacion = null;
		FiseFormato13ADObPK pk = null;
		FiseFormato13ADOb obs = null;
		try {			
			pk = new FiseFormato13ADObPK();
			pk.setCodEmpresa(codEmpresa);
			pk.setAnoPresentacion(anioPres);
			pk.setMesPresentacion(mesPres);
			pk.setCodUbigeo(ubigeo);
			pk.setCodSectorTipico(sector);			
			pk.setEtapa(etapa);
			pk.setIdZonaBenef(idZona);		
			pk.setItemObservacion(itemObservacion); 	
			obs = formato13ADObDao.obtenerFiseFormato13ADOb(pk);		
			formato13ADObDao.eliminarFormato13ADOb(obs); 
			observacion = fiseObservacionDao.obtenerFiseObservacion(idObservacion);			
			fiseObservacionDao.eliminarFiseObservacion(observacion);
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
	
	
	/*******************************************/
	
	@Override
	@Transactional(propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
	public FiseFormato13AC registrarFormato13ACregistrarFormato13AD(Formato13ACBean formulario, List<Formato13ACBean> listaDetalleCarga) throws Exception {
		
		FiseFormato13AC dto = null;
		
		try {
			Date hoy = FechaUtil.obtenerFechaActual();
			FiseFormato13AC fiseFormato13AC = new FiseFormato13AC();
			//guardar el pk
			FiseFormato13ACPK id = new FiseFormato13ACPK();
			id.setCodEmpresa(formulario.getCodigoEmpresa());
			id.setAnoPresentacion(formulario.getAnioPresent());
			id.setMesPresentacion(formulario.getMesPresent());
			id.setEtapa(formulario.getEtapa());
			if( FiseConstants.TIPOARCHIVO_XLS.equals(formulario.getTipoArchivo()) ){
				fiseFormato13AC.setNombreArchivoExcel(formulario.getNombreArchivo());
			}else if( FiseConstants.TIPOARCHIVO_TXT.equals(formulario.getTipoArchivo()) ){
				fiseFormato13AC.setNombreArchivoTexto(formulario.getNombreArchivo());
			}
			
			fiseFormato13AC.setId(id);
			
			FiseGrupoInformacion grupoInfo = null;
			long idGrupoInf = commonDao.obtenerIdGrupoInformacion(formulario.getAnioPresent(), formulario.getMesPresent(), FiseConstants.FRECUENCIA_BIENAL_DESCRIPCION); 
			if(idGrupoInf!=0){
				grupoInfo = fiseGrupoInformacionDao.obtenerFiseGrupoInformacionByPK(idGrupoInf);	
			}	
			logger.info("Grupo de irfomacion:  "+grupoInfo); 
			fiseFormato13AC.setFiseGrupoInformacion(grupoInfo);
			
			List<FiseFormato13AD> lista = new ArrayList<FiseFormato13AD>();
			
			if( listaDetalleCarga==null ){
				if( formulario.getAnioAlta() != 0 ||
						formulario.getMesAlta() != 0 ||
						!formulario.getCodUbigeo().equals(FiseConstants.BLANCO) ||
						!formulario.getLocalidad().equals(FiseConstants.BLANCO) ||
						formulario.getIdZonaBenef() != 0 ||
						formulario.getSt1() != 0 ||
						formulario.getSt2() != 0 ||
						formulario.getSt3() != 0 ||
						formulario.getSt4() != 0 ||
						formulario.getSt5() != 0 ||
						formulario.getSt6() != 0 ||
						formulario.getStSer() != 0 ||
						formulario.getStEsp() != 0 ||
						!formulario.getLocalidad().equals(FiseConstants.BLANCO) 
						){
					
					agregarSectorTipico(FiseConstants.SECTOR_TIPICO_1_COD, formulario, lista);
					agregarSectorTipico(FiseConstants.SECTOR_TIPICO_2_COD, formulario, lista);
					agregarSectorTipico(FiseConstants.SECTOR_TIPICO_3_COD, formulario, lista);
					agregarSectorTipico(FiseConstants.SECTOR_TIPICO_4_COD, formulario, lista);
					agregarSectorTipico(FiseConstants.SECTOR_TIPICO_5_COD, formulario, lista);
					agregarSectorTipico(FiseConstants.SECTOR_TIPICO_6_COD, formulario, lista);
					agregarSectorTipico(FiseConstants.SECTOR_TIPICO_SER_COD, formulario, lista);
					agregarSectorTipico(FiseConstants.SECTOR_TIPICO_ESP_COD, formulario, lista);
					
				}
			}else{
				lista = convertirBeanADtoFormato13AD(listaDetalleCarga);
			}
				
			

			
			fiseFormato13AC.setUsuarioActualizacion(formulario.getUsuario());
			fiseFormato13AC.setTerminalActualizacion(formulario.getTerminal());
			fiseFormato13AC.setFechaActualizacion(hoy);
			fiseFormato13AC.setUsuarioCreacion(formulario.getUsuario());
			fiseFormato13AC.setTerminalCreacion(formulario.getTerminal());
			fiseFormato13AC.setFechaCreacion(hoy);
			
			logger.info("aca se va  a guardar"+fiseFormato13AC);
			boolean existe = false;
			existe = formato13ACDao.existeFormato13AC(fiseFormato13AC);
			if(existe){
				throw new Exception("El Formato ya existe para la Distribuidora Eléctrica y Periodo a Declarar seleccionado");
			}else{
				formato13ACDao.savecabecera(fiseFormato13AC);
			}
			//add
			for (FiseFormato13AD detalle : lista) {
				formato13ADDao.savedetalle(detalle);
			}
			if( lista != null && lista.size()>0 ){
				fiseFormato13AC.setFiseFormato13ADs(lista);
				/*if( lista.size()==1 ){
					//enviamos la cabecera de informacion
					fiseFormato13AC.setAnoEjecucionDetalle(lista.get(0).getId().getAnoEjecucionGasto());
					fiseFormato13AC.setMesEjecucionDetalle(lista.get(0).getId().getMesEjecucionGasto());
					fiseFormato13AC.setEtapaEjecucionDetalle(lista.get(0).getId().getEtapaEjecucion());
					fiseFormato13AC.setNumeroItemEtapaDetalle(lista.get(0).getId().getNumeroItemEtapa());
				}*/
			}
			dto = fiseFormato13AC;
			
		} 	catch (Exception e) {
			logger.error("--error"+e.getMessage());
			e.printStackTrace();
			throw new Exception("Se produjo un error al guardar los datos del Formato 12C");
		}
		return dto;
	}
	
	@Override
	@Transactional(propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
	public FiseFormato13AC modificarFormato13ACregistrarFormato13AD(Formato13ACBean formulario, FiseFormato13AC fiseFormato13AC, List<Formato13ACBean> listaDetalleCarga) throws Exception {
		
		FiseFormato13AC dto = null;
		
		try {
			
			Date hoy = FechaUtil.obtenerFechaActual();
	
			List<FiseFormato13AD> lista = new ArrayList<FiseFormato13AD>();
			
			if( listaDetalleCarga==null ){
				if( formulario.getAnioAlta() != 0 ||
						formulario.getMesAlta() != 0 ||
						!formulario.getCodUbigeo().equals(FiseConstants.BLANCO) ||
						!formulario.getLocalidad().equals(FiseConstants.BLANCO) ||
						formulario.getIdZonaBenef() != 0 ||
						formulario.getSt1() != 0 ||
						formulario.getSt2() != 0 ||
						formulario.getSt3() != 0 ||
						formulario.getSt4() != 0 ||
						formulario.getSt5() != 0 ||
						formulario.getSt6() != 0 ||
						formulario.getStSer() != 0 ||
						formulario.getStEsp() != 0 ||
						!formulario.getLocalidad().equals(FiseConstants.BLANCO) 
						){
					
					agregarSectorTipico(FiseConstants.SECTOR_TIPICO_1_COD, formulario, lista);
					agregarSectorTipico(FiseConstants.SECTOR_TIPICO_2_COD, formulario, lista);
					agregarSectorTipico(FiseConstants.SECTOR_TIPICO_3_COD, formulario, lista);
					agregarSectorTipico(FiseConstants.SECTOR_TIPICO_4_COD, formulario, lista);
					agregarSectorTipico(FiseConstants.SECTOR_TIPICO_5_COD, formulario, lista);
					agregarSectorTipico(FiseConstants.SECTOR_TIPICO_6_COD, formulario, lista);
					agregarSectorTipico(FiseConstants.SECTOR_TIPICO_SER_COD, formulario, lista);
					agregarSectorTipico(FiseConstants.SECTOR_TIPICO_ESP_COD, formulario, lista);
					
				}
			}else{
				lista=convertirBeanADtoFormato13AD(listaDetalleCarga);
			}
			
			
				

		
			fiseFormato13AC.setUsuarioActualizacion(formulario.getUsuario());
			fiseFormato13AC.setTerminalActualizacion(formulario.getTerminal());
			fiseFormato13AC.setFechaActualizacion(hoy);
		
			if( FiseConstants.TIPOARCHIVO_XLS.equals(formulario.getTipoArchivo()) ){
				fiseFormato13AC.setNombreArchivoExcel(formulario.getNombreArchivo());
			}else if( FiseConstants.TIPOARCHIVO_TXT.equals(formulario.getTipoArchivo()) ){
				fiseFormato13AC.setNombreArchivoTexto(formulario.getNombreArchivo());
			}
			
			formato13ACDao.updatecabecera(fiseFormato13AC);
			//add
			for (FiseFormato13AD detalle : lista) {
				formato13ADDao.savedetalle(detalle);
			}
			if( lista != null && lista.size()>0 ){
				/*if( lista.size()==1 ){
					//enviamos la cabecera de informacion
					fiseFormato13AC.setAnoEjecucionDetalle(lista.get(0).getId().getAnoEjecucionGasto());
					fiseFormato13AC.setMesEjecucionDetalle(lista.get(0).getId().getMesEjecucionGasto());
					fiseFormato13AC.setEtapaEjecucionDetalle(lista.get(0).getId().getEtapaEjecucion());
					fiseFormato13AC.setNumeroItemEtapaDetalle(lista.get(0).getId().getNumeroItemEtapa());
				}*/
			}
			dto= fiseFormato13AC;
			
		}	catch (Exception e) {
			logger.error("--error"+e.getMessage());
			e.printStackTrace();
			throw e;
		}
		//
		return dto;

	}
	
	
	
	//------------------------------------------//
	public void agregarSectorTipico(String sectorTipico, Formato13ACBean bean, List<FiseFormato13AD> listaDetalle) {

		Date hoy = FechaUtil.obtenerFechaActual();

		try {
			
			/*if( bean.getAnioInicioVigencia()==null || bean.getAnioInicioVigencia().equals(new Long(0)) ){
				bean.setAnioInicioVigencia(bean.getAnoInicioVigenciaHidden());
			}
			if( bean.getAnioFinVigencia()==null || bean.getAnioFinVigencia().equals(new Long(0)) ){
				bean.setAnioFinVigencia(bean.getAnoFinVigenciaHidden());
			}*/

			FiseFormato13AD detalle = new FiseFormato13AD();
			FiseFormato13ADPK pk = new FiseFormato13ADPK();
			pk.setCodEmpresa(bean.getCodigoEmpresa());
			pk.setAnoPresentacion(bean.getAnioPresent());
			pk.setMesPresentacion(bean.getMesPresent());
			pk.setEtapa(bean.getEtapa());
			pk.setCodUbigeo(bean.getCodUbigeo());
			pk.setCodSectorTipico(sectorTipico);
			pk.setIdZonaBenef(bean.getIdZonaBenef());
			detalle.setId(pk);
			detalle.setAnoAlta(bean.getAnioAlta());
			detalle.setMesAlta(bean.getMesAlta());
			// luego verificar de donde se obtendra los valores de ano e inicio
			// de vigencia
			detalle.setAnoInicioVigencia(bean.getAnioInicioVigencia());
			detalle.setAnoFinVigencia(bean.getAnioFinVigencia());
			//
			detalle.setDescripcionLocalidad(bean.getLocalidad());
			detalle.setNombreSedeAtiende(bean.getSede());
			if (FiseConstants.SECTOR_TIPICO_1_COD.equals(sectorTipico.trim())) {
				detalle.setNumeroBenefiPoteSectTipico(bean.getSt1());
			} else if (FiseConstants.SECTOR_TIPICO_2_COD.equals(sectorTipico.trim())) {
				detalle.setNumeroBenefiPoteSectTipico(bean.getSt2());
			} else if (FiseConstants.SECTOR_TIPICO_3_COD.equals(sectorTipico.trim())) {
				detalle.setNumeroBenefiPoteSectTipico(bean.getSt3());
			} else if (FiseConstants.SECTOR_TIPICO_4_COD.equals(sectorTipico.trim())) {
				detalle.setNumeroBenefiPoteSectTipico(bean.getSt4());
			} else if (FiseConstants.SECTOR_TIPICO_5_COD.equals(sectorTipico.trim())) {
				detalle.setNumeroBenefiPoteSectTipico(bean.getSt5());
			} else if (FiseConstants.SECTOR_TIPICO_6_COD.equals(sectorTipico.trim())) {
				detalle.setNumeroBenefiPoteSectTipico(bean.getSt6());
			} else if (FiseConstants.SECTOR_TIPICO_SER_COD.equals(sectorTipico.trim())) {
				detalle.setNumeroBenefiPoteSectTipico(bean.getStSer());
			} else if (FiseConstants.SECTOR_TIPICO_ESP_COD.equals(sectorTipico.trim())) {
				detalle.setNumeroBenefiPoteSectTipico(bean.getStEsp());
			}
			detalle.setUsuarioCreacion(bean.getUsuario());
			detalle.setTerminalCreacion(bean.getTerminal());
			detalle.setFechaCreacion(hoy);
			detalle.setUsuarioActualizacion(bean.getUsuario());
			detalle.setTerminalActualizacion(bean.getTerminal());
			detalle.setFechaActualizacion(hoy);
			listaDetalle.add(detalle);

		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}

	}
	
	public List<FiseFormato13AD> convertirBeanADtoFormato13AD(List<Formato13ACBean> listaCarga){
		List<FiseFormato13AD> listaFinal = new ArrayList<FiseFormato13AD>();
		
		Date hoy = FechaUtil.obtenerFechaActual();
		
		for (Formato13ACBean bean : listaCarga) {
			FiseFormato13AD detalle = new FiseFormato13AD();
			FiseFormato13ADPK pk = new FiseFormato13ADPK();
			pk.setCodEmpresa(bean.getCodigoEmpresa());
			pk.setAnoPresentacion(bean.getAnioPresent());
			pk.setMesPresentacion(bean.getMesPresent());
			pk.setEtapa(bean.getEtapa());
			pk.setCodUbigeo(bean.getCodUbigeo());
			pk.setCodSectorTipico(bean.getCodSectorTipico());
			pk.setIdZonaBenef(bean.getIdZonaBenef());
			detalle.setId(pk);
			detalle.setAnoAlta(bean.getAnioAlta());
			detalle.setMesAlta(bean.getMesAlta());

			detalle.setAnoInicioVigencia(bean.getAnioInicioVigencia());
			detalle.setAnoFinVigencia(bean.getAnioFinVigencia());
			//
			detalle.setDescripcionLocalidad(bean.getLocalidad());
			detalle.setNombreSedeAtiende(bean.getSede());
			if (FiseConstants.SECTOR_TIPICO_1_COD.equals(bean.getCodSectorTipico().trim())) {
				detalle.setNumeroBenefiPoteSectTipico(bean.getSt1());
			} else if (FiseConstants.SECTOR_TIPICO_2_COD.equals(bean.getCodSectorTipico().trim())) {
				detalle.setNumeroBenefiPoteSectTipico(bean.getSt2());
			} else if (FiseConstants.SECTOR_TIPICO_3_COD.equals(bean.getCodSectorTipico().trim())) {
				detalle.setNumeroBenefiPoteSectTipico(bean.getSt3());
			} else if (FiseConstants.SECTOR_TIPICO_4_COD.equals(bean.getCodSectorTipico().trim())) {
				detalle.setNumeroBenefiPoteSectTipico(bean.getSt4());
			} else if (FiseConstants.SECTOR_TIPICO_5_COD.equals(bean.getCodSectorTipico().trim())) {
				detalle.setNumeroBenefiPoteSectTipico(bean.getSt5());
			} else if (FiseConstants.SECTOR_TIPICO_6_COD.equals(bean.getCodSectorTipico().trim())) {
				detalle.setNumeroBenefiPoteSectTipico(bean.getSt6());
			} else if (FiseConstants.SECTOR_TIPICO_SER_COD.equals(bean.getCodSectorTipico().trim())) {
				detalle.setNumeroBenefiPoteSectTipico(bean.getStSer());
			} else if (FiseConstants.SECTOR_TIPICO_ESP_COD.equals(bean.getCodSectorTipico().trim())) {
				detalle.setNumeroBenefiPoteSectTipico(bean.getStEsp());
			}
			detalle.setUsuarioCreacion(bean.getUsuario());
			detalle.setTerminalCreacion(bean.getTerminal());
			detalle.setFechaCreacion(hoy);
			detalle.setUsuarioActualizacion(bean.getUsuario());
			detalle.setTerminalActualizacion(bean.getTerminal());
			detalle.setFechaActualizacion(hoy);
			listaFinal.add(detalle);
		}
		
		return listaFinal;
	}
	
}
