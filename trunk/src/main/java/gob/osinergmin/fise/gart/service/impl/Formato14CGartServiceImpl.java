package gob.osinergmin.fise.gart.service.impl;

import gob.osinergmin.fise.bean.Formato14CBean;
import gob.osinergmin.fise.constant.FiseConstants;
import gob.osinergmin.fise.dao.FiseGrupoInformacionDao;
import gob.osinergmin.fise.dao.FiseTipPersonalDao;
import gob.osinergmin.fise.dao.FiseZonaBenefDao;
import gob.osinergmin.fise.dao.Formato14CCDao;
import gob.osinergmin.fise.dao.Formato14CDDao;
import gob.osinergmin.fise.domain.FiseFormato14CC;
import gob.osinergmin.fise.domain.FiseFormato14CCPK;
import gob.osinergmin.fise.domain.FiseFormato14CD;
import gob.osinergmin.fise.domain.FiseFormato14CDPK;
import gob.osinergmin.fise.domain.FiseGrupoInformacion;
import gob.osinergmin.fise.gart.service.Formato14CGartService;
import gob.osinergmin.fise.util.FechaUtil;

import java.math.BigDecimal;
import java.util.List;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

@Service(value="formato14CGartServiceImpl")
public class Formato14CGartServiceImpl implements Formato14CGartService {
	
    Logger logger=Logger.getLogger(Formato14CGartServiceImpl.class);
	
	@Autowired
	@Qualifier("formato14CCDaoImpl")
	private Formato14CCDao formato14CCDao;	
	
	@Autowired
	@Qualifier("formato14CDDaoImpl")
	private Formato14CDDao formato14CDDao;
	
	@Autowired
	@Qualifier("fiseTipPersonalDaoImpl")
	private FiseTipPersonalDao fiseTipPersonalDao;
	
	@Autowired
	@Qualifier("fiseZonaBenefDaoImpl")
	private FiseZonaBenefDao fiseZonaBenefDao;
	
	@Autowired
	@Qualifier("fiseGrupoInformacionDaoImpl")
	private FiseGrupoInformacionDao fiseGrupoInformacionDao;
	
	/**Metodo para listar el formato 14C*/
	@Override
	public List<FiseFormato14CC> listarFormato14CC() 
			throws Exception{
		return formato14CCDao.listarFormato14CC();
	}
	
	/**Metodo para listar el formato 14C detalle*/
	@Override
	public List<FiseFormato14CD> listarFormato14CD() 
			throws Exception{
		return formato14CDDao.listarFormato14CD();
	}
	
	
	/**Metodo para insertar Cabecera y Detalle del formato 14C*/
	@Transactional(propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
	public void insertarDatosFormato14C(Formato14CBean bean) throws Exception{
		FiseFormato14CC cab = null;
		FiseFormato14CCPK idCab = null;
		FiseFormato14CD det = null;
		FiseFormato14CDPK idDet = null;
		try {
		  /*Grabando en la cabecera*/
			cab = new FiseFormato14CC();
			idCab = new FiseFormato14CCPK();
			//PK
			idCab.setCodEmpresa(bean.getCodEmpresa());
			idCab.setAnoPresentacion(Long.valueOf(bean.getAnioPres())); 
			idCab.setMesPresentacion(Long.valueOf(bean.getMesPres())); 
			idCab.setAnoInicioVigencia(Long.valueOf(bean.getAnioPres())); 
			idCab.setAnoFinVigencia(Long.valueOf(bean.getAnioPres())); 
			idCab.setEtapa(bean.getEtapa()); 
			//FIN PK
			cab.setId(idCab);
			cab.setNombreSede(bean.getNombreSede());
			FiseGrupoInformacion inf = fiseGrupoInformacionDao.obtenerFiseGrupoInformacionByPK(new Long(1)); 
			cab.setFiseGrupoInformacion(inf);  
			cab.setNombreArchivoExcel("");
			cab.setNombreArchivoTexto(""); 
			cab.setFechaEnvioDefinitivo(null);
			cab.setNumBenefEmpPerAntRural(Integer.valueOf(bean.getNumRural())); 
			cab.setNumBenefEmpPerAntUrbProv(Integer.valueOf(bean.getNumUrbProv())); 
			cab.setNumBenefEmpPerAntUrbLima(Integer.valueOf(bean.getNumUrbLima()));  
			cab.setCostoPromMenRural(new BigDecimal(bean.getCostoPromRural()));
			cab.setCostoPromMenUrbProv(new BigDecimal(bean.getCostoPromUrbProv()));
			cab.setCostoPromMenUrbLima(new BigDecimal(bean.getCostoPromUrbLima()));  
			cab.setUsuarioCreacion("USER1");
			cab.setTerminalCreacion("192.168.1.15");
			cab.setFechaCreacion(FechaUtil.obtenerFechaActual());	
			formato14CCDao.insertarFiseFormato14CC(cab); 
		
			/*Grabando en el detalle de la cabecera*/
		    
			/**Grabando el primer detalle Rural-Coordinador**/
			det = new  FiseFormato14CD();
			idDet = new FiseFormato14CDPK();		
			idDet.setCodEmpresa(bean.getCodEmpresa());
			idDet.setAnoPresentacion(Long.valueOf(bean.getAnioPres())); 
			idDet.setMesPresentacion(Long.valueOf(bean.getMesPres())); 
			idDet.setAnoInicioVigencia(Long.valueOf(bean.getAnioPres())); 
			idDet.setAnoFinVigencia(Long.valueOf(bean.getAnioPres())); 
			idDet.setEtapa(bean.getEtapa()); 
			idDet.setIdTipPersonal(FiseConstants.TIPO_PERSONAL_CORDINADOR_COD);
			idDet.setIdZonaBenef(FiseConstants.ZONABENEF_RURAL_COD); 		
			det.setCantidad(Integer.valueOf(bean.getCanRCoord()));  
			det.setCostoDirecto(new BigDecimal(bean.getCostDRCoord())); 
			det.setCostoIndirecto(new BigDecimal(bean.getCostIRCoord()));  
			det.setUsuarioCreacion("USER1");
			det.setTerminalCreacion("192.168.1.15");
			det.setFechaCreacion(FechaUtil.obtenerFechaActual());	
			formato14CDDao.insertarFiseFormato14CD(det); 
			/**Grabando el primer detalle Urbano Provincia-Coordinador**/
			det = new  FiseFormato14CD();
			idDet = new FiseFormato14CDPK();		
			idDet.setCodEmpresa(bean.getCodEmpresa());
			idDet.setAnoPresentacion(Long.valueOf(bean.getAnioPres())); 
			idDet.setMesPresentacion(Long.valueOf(bean.getMesPres())); 
			idDet.setAnoInicioVigencia(Long.valueOf(bean.getAnioPres())); 
			idDet.setAnoFinVigencia(Long.valueOf(bean.getAnioPres())); 
			idDet.setEtapa(bean.getEtapa()); 
			idDet.setIdTipPersonal(FiseConstants.TIPO_PERSONAL_CORDINADOR_COD);
			idDet.setIdZonaBenef(FiseConstants.ZONABENEF_PROVINCIA_COD); 		
			det.setCantidad(Integer.valueOf(bean.getCanPCoord()));  
			det.setCostoDirecto(new BigDecimal(bean.getCostDPCoord())); 
			det.setCostoIndirecto(new BigDecimal(bean.getCostIPCoord()));  
			det.setUsuarioCreacion("USER1");
			det.setTerminalCreacion("192.168.1.15");
			det.setFechaCreacion(FechaUtil.obtenerFechaActual());	
			formato14CDDao.insertarFiseFormato14CD(det); 
			/**Grabando el primer detalle Urbano Lima-Coordinador**/
			det = new  FiseFormato14CD();
			idDet = new FiseFormato14CDPK();		
			idDet.setCodEmpresa(bean.getCodEmpresa());
			idDet.setAnoPresentacion(Long.valueOf(bean.getAnioPres())); 
			idDet.setMesPresentacion(Long.valueOf(bean.getMesPres())); 
			idDet.setAnoInicioVigencia(Long.valueOf(bean.getAnioPres())); 
			idDet.setAnoFinVigencia(Long.valueOf(bean.getAnioPres())); 
			idDet.setEtapa(bean.getEtapa()); 
			idDet.setIdTipPersonal(FiseConstants.TIPO_PERSONAL_CORDINADOR_COD);
			idDet.setIdZonaBenef(FiseConstants.ZONABENEF_LIMA_COD); 		
			det.setCantidad(Integer.valueOf(bean.getCanLCoord()));  
			det.setCostoDirecto(new BigDecimal(bean.getCostDLCoord())); 
			det.setCostoIndirecto(new BigDecimal(bean.getCostILCoord()));  
			det.setUsuarioCreacion("USER1");
			det.setTerminalCreacion("192.168.1.15");
			det.setFechaCreacion(FechaUtil.obtenerFechaActual());	
			formato14CDDao.insertarFiseFormato14CD(det);
			
			/**Grabando el primer detalle Rural-Supervisor**/
			det = new  FiseFormato14CD();
			idDet = new FiseFormato14CDPK();		
			idDet.setCodEmpresa(bean.getCodEmpresa());
			idDet.setAnoPresentacion(Long.valueOf(bean.getAnioPres())); 
			idDet.setMesPresentacion(Long.valueOf(bean.getMesPres())); 
			idDet.setAnoInicioVigencia(Long.valueOf(bean.getAnioPres())); 
			idDet.setAnoFinVigencia(Long.valueOf(bean.getAnioPres())); 
			idDet.setEtapa(bean.getEtapa()); 
			idDet.setIdTipPersonal(FiseConstants.TIPO_PERSONAL_SUPERVISOR_COD);
			idDet.setIdZonaBenef(FiseConstants.ZONABENEF_RURAL_COD); 		
			det.setCantidad(Integer.valueOf(bean.getCanRSupe()));  
			det.setCostoDirecto(new BigDecimal(bean.getCostDRSupe())); 
			det.setCostoIndirecto(new BigDecimal(bean.getCostIRSupe()));  
			det.setUsuarioCreacion("USER1");
			det.setTerminalCreacion("192.168.1.15");
			det.setFechaCreacion(FechaUtil.obtenerFechaActual());	
			formato14CDDao.insertarFiseFormato14CD(det);
			
			/**Grabando el primer detalle Urbano Provincias-Supervisor**/
			det = new  FiseFormato14CD();
			idDet = new FiseFormato14CDPK();		
			idDet.setCodEmpresa(bean.getCodEmpresa());
			idDet.setAnoPresentacion(Long.valueOf(bean.getAnioPres())); 
			idDet.setMesPresentacion(Long.valueOf(bean.getMesPres())); 
			idDet.setAnoInicioVigencia(Long.valueOf(bean.getAnioPres())); 
			idDet.setAnoFinVigencia(Long.valueOf(bean.getAnioPres())); 
			idDet.setEtapa(bean.getEtapa()); 
			idDet.setIdTipPersonal(FiseConstants.TIPO_PERSONAL_SUPERVISOR_COD);
			idDet.setIdZonaBenef(FiseConstants.ZONABENEF_PROVINCIA_COD); 		
			det.setCantidad(Integer.valueOf(bean.getCanPSupe()));  
			det.setCostoDirecto(new BigDecimal(bean.getCostDPSupe())); 
			det.setCostoIndirecto(new BigDecimal(bean.getCostIPSupe()));  
			det.setUsuarioCreacion("USER1");
			det.setTerminalCreacion("192.168.1.15");
			det.setFechaCreacion(FechaUtil.obtenerFechaActual());	
			formato14CDDao.insertarFiseFormato14CD(det);
			
			/**Grabando el primer detalle Urbana Lima -Supervisor**/
			det = new  FiseFormato14CD();
			idDet = new FiseFormato14CDPK();		
			idDet.setCodEmpresa(bean.getCodEmpresa());
			idDet.setAnoPresentacion(Long.valueOf(bean.getAnioPres())); 
			idDet.setMesPresentacion(Long.valueOf(bean.getMesPres())); 
			idDet.setAnoInicioVigencia(Long.valueOf(bean.getAnioPres())); 
			idDet.setAnoFinVigencia(Long.valueOf(bean.getAnioPres())); 
			idDet.setEtapa(bean.getEtapa()); 
			idDet.setIdTipPersonal(FiseConstants.TIPO_PERSONAL_SUPERVISOR_COD);
			idDet.setIdZonaBenef(FiseConstants.ZONABENEF_LIMA_COD); 		
			det.setCantidad(Integer.valueOf(bean.getCanLSupe()));  
			det.setCostoDirecto(new BigDecimal(bean.getCostDLSupe())); 
			det.setCostoIndirecto(new BigDecimal(bean.getCostILSupe()));  
			det.setUsuarioCreacion("USER1");
			det.setTerminalCreacion("192.168.1.15");
			det.setFechaCreacion(FechaUtil.obtenerFechaActual());	
			formato14CDDao.insertarFiseFormato14CD(det);
			
			/**Grabando el primer detalle Rural-Gestor**/
			det = new  FiseFormato14CD();
			idDet = new FiseFormato14CDPK();		
			idDet.setCodEmpresa(bean.getCodEmpresa());
			idDet.setAnoPresentacion(Long.valueOf(bean.getAnioPres())); 
			idDet.setMesPresentacion(Long.valueOf(bean.getMesPres())); 
			idDet.setAnoInicioVigencia(Long.valueOf(bean.getAnioPres())); 
			idDet.setAnoFinVigencia(Long.valueOf(bean.getAnioPres())); 
			idDet.setEtapa(bean.getEtapa()); 
			idDet.setIdTipPersonal(FiseConstants.TIPO_PERSONAL_GESTOR_COD);
			idDet.setIdZonaBenef(FiseConstants.ZONABENEF_RURAL_COD); 		
			det.setCantidad(Integer.valueOf(bean.getCanRGest()));  
			det.setCostoDirecto(new BigDecimal(bean.getCostDRGest())); 
			det.setCostoIndirecto(new BigDecimal(bean.getCostIRGest()));  
			det.setUsuarioCreacion("USER1");
			det.setTerminalCreacion("192.168.1.15");
			det.setFechaCreacion(FechaUtil.obtenerFechaActual());	
			formato14CDDao.insertarFiseFormato14CD(det);
			
			/**Grabando el primer detalle Urbana Provincias-Gestor**/
			det = new  FiseFormato14CD();
			idDet = new FiseFormato14CDPK();		
			idDet.setCodEmpresa(bean.getCodEmpresa());
			idDet.setAnoPresentacion(Long.valueOf(bean.getAnioPres())); 
			idDet.setMesPresentacion(Long.valueOf(bean.getMesPres())); 
			idDet.setAnoInicioVigencia(Long.valueOf(bean.getAnioPres())); 
			idDet.setAnoFinVigencia(Long.valueOf(bean.getAnioPres())); 
			idDet.setEtapa(bean.getEtapa()); 
			idDet.setIdTipPersonal(FiseConstants.TIPO_PERSONAL_GESTOR_COD);
			idDet.setIdZonaBenef(FiseConstants.ZONABENEF_PROVINCIA_COD); 		
			det.setCantidad(Integer.valueOf(bean.getCanPGest()));  
			det.setCostoDirecto(new BigDecimal(bean.getCostDPGest())); 
			det.setCostoIndirecto(new BigDecimal(bean.getCostIPGest()));  
			det.setUsuarioCreacion("USER1");
			det.setTerminalCreacion("192.168.1.15");
			det.setFechaCreacion(FechaUtil.obtenerFechaActual());	
			formato14CDDao.insertarFiseFormato14CD(det);
			
			/**Grabando el primer detalle Urbana Lima -Gestor**/
			det = new  FiseFormato14CD();
			idDet = new FiseFormato14CDPK();		
			idDet.setCodEmpresa(bean.getCodEmpresa());
			idDet.setAnoPresentacion(Long.valueOf(bean.getAnioPres())); 
			idDet.setMesPresentacion(Long.valueOf(bean.getMesPres())); 
			idDet.setAnoInicioVigencia(Long.valueOf(bean.getAnioPres())); 
			idDet.setAnoFinVigencia(Long.valueOf(bean.getAnioPres())); 
			idDet.setEtapa(bean.getEtapa()); 
			idDet.setIdTipPersonal(FiseConstants.TIPO_PERSONAL_GESTOR_COD);
			idDet.setIdZonaBenef(FiseConstants.ZONABENEF_LIMA_COD); 		
			det.setCantidad(Integer.valueOf(bean.getCanLGest()));  
			det.setCostoDirecto(new BigDecimal(bean.getCostDLGest())); 
			det.setCostoIndirecto(new BigDecimal(bean.getCostILGest()));  
			det.setUsuarioCreacion("USER1");
			det.setTerminalCreacion("192.168.1.15");
			det.setFechaCreacion(FechaUtil.obtenerFechaActual());	
			formato14CDDao.insertarFiseFormato14CD(det);
			
			/**Grabando el primer detalle Rural-Asistente/Auxiliar**/
			det = new  FiseFormato14CD();
			idDet = new FiseFormato14CDPK();		
			idDet.setCodEmpresa(bean.getCodEmpresa());
			idDet.setAnoPresentacion(Long.valueOf(bean.getAnioPres())); 
			idDet.setMesPresentacion(Long.valueOf(bean.getMesPres())); 
			idDet.setAnoInicioVigencia(Long.valueOf(bean.getAnioPres())); 
			idDet.setAnoFinVigencia(Long.valueOf(bean.getAnioPres())); 
			idDet.setEtapa(bean.getEtapa()); 
			idDet.setIdTipPersonal(FiseConstants.TIPO_PERSONAL_ASISTENTE_COD);
			idDet.setIdZonaBenef(FiseConstants.ZONABENEF_RURAL_COD); 		
			det.setCantidad(Integer.valueOf(bean.getCanRAsist()));  
			det.setCostoDirecto(new BigDecimal(bean.getCostDRAsist())); 
			det.setCostoIndirecto(new BigDecimal(bean.getCostIRAsist()));  
			det.setUsuarioCreacion("USER1");
			det.setTerminalCreacion("192.168.1.15");
			det.setFechaCreacion(FechaUtil.obtenerFechaActual());	
			formato14CDDao.insertarFiseFormato14CD(det);
			
			/**Grabando el primer detalle Urbano Provincias -Asistente/Auxiliar**/
			det = new  FiseFormato14CD();
			idDet = new FiseFormato14CDPK();		
			idDet.setCodEmpresa(bean.getCodEmpresa());
			idDet.setAnoPresentacion(Long.valueOf(bean.getAnioPres())); 
			idDet.setMesPresentacion(Long.valueOf(bean.getMesPres())); 
			idDet.setAnoInicioVigencia(Long.valueOf(bean.getAnioPres())); 
			idDet.setAnoFinVigencia(Long.valueOf(bean.getAnioPres())); 
			idDet.setEtapa(bean.getEtapa()); 
			idDet.setIdTipPersonal(FiseConstants.TIPO_PERSONAL_ASISTENTE_COD);
			idDet.setIdZonaBenef(FiseConstants.ZONABENEF_PROVINCIA_COD); 		
			det.setCantidad(Integer.valueOf(bean.getCanPAsist()));  
			det.setCostoDirecto(new BigDecimal(bean.getCostDPAsist())); 
			det.setCostoIndirecto(new BigDecimal(bean.getCostIPAsist()));  
			det.setUsuarioCreacion("USER1");
			det.setTerminalCreacion("192.168.1.15");
			det.setFechaCreacion(FechaUtil.obtenerFechaActual());	
			formato14CDDao.insertarFiseFormato14CD(det);
			
			/**Grabando el primer detalle Urbana Lima -Asistente/Auxiliar**/
			det = new  FiseFormato14CD();
			idDet = new FiseFormato14CDPK();		
			idDet.setCodEmpresa(bean.getCodEmpresa());
			idDet.setAnoPresentacion(Long.valueOf(bean.getAnioPres())); 
			idDet.setMesPresentacion(Long.valueOf(bean.getMesPres())); 
			idDet.setAnoInicioVigencia(Long.valueOf(bean.getAnioPres())); 
			idDet.setAnoFinVigencia(Long.valueOf(bean.getAnioPres())); 
			idDet.setEtapa(bean.getEtapa()); 
			idDet.setIdTipPersonal(FiseConstants.TIPO_PERSONAL_ASISTENTE_COD);
			idDet.setIdZonaBenef(FiseConstants.ZONABENEF_LIMA_COD); 		
			det.setCantidad(Integer.valueOf(bean.getCanLAsist()));  
			det.setCostoDirecto(new BigDecimal(bean.getCostDLAsist())); 
			det.setCostoIndirecto(new BigDecimal(bean.getCostILAsist()));  
			det.setUsuarioCreacion("USER1");
			det.setTerminalCreacion("192.168.1.15");
			det.setFechaCreacion(FechaUtil.obtenerFechaActual());	
			formato14CDDao.insertarFiseFormato14CD(det);
			
		} catch (Exception e) { 
		   logger.info("Error al grabar datos del formato 14c:  "+e);
		   e.printStackTrace();
		}finally{
			if(cab!=null){
				cab=null;
			}
			if(idCab!=null){
				idCab=null;
			}
			if(det!=null){
				det=null;
			}
			if(idDet!=null){
				idDet=null;
			}
		}
	}	
	
	/**Metodo para actualizar Cabecera y Detalle del formato 14C*/
	@Transactional(propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
	public void actualizarDatosFormato14C(Formato14CBean bean) throws Exception{
		FiseFormato14CC cab = null;
		FiseFormato14CCPK idCab = null;
		FiseFormato14CD det = null;
		FiseFormato14CDPK idDet = null;
		try {
		  /*Grabando en la cabecera*/		
			idCab = new FiseFormato14CCPK();
			//PK
			idCab.setCodEmpresa(bean.getCodEmpresa());
			idCab.setAnoPresentacion(Long.valueOf(bean.getAnioPres())); 
			idCab.setMesPresentacion(Long.valueOf(bean.getMesPres())); 
			idCab.setAnoInicioVigencia(Long.valueOf(bean.getAnioPres())); 
			idCab.setAnoFinVigencia(Long.valueOf(bean.getAnioPres())); 
			idCab.setEtapa(bean.getEtapa()); 
			//FIN PK
			cab = formato14CCDao.obtenerFormato14CC(idCab);
			cab.setNombreSede(bean.getNombreSede());
			FiseGrupoInformacion inf = fiseGrupoInformacionDao.obtenerFiseGrupoInformacionByPK(new Long(1)); 
			cab.setFiseGrupoInformacion(inf);  
			cab.setNombreArchivoExcel("");
			cab.setNombreArchivoTexto(""); 
			cab.setFechaEnvioDefinitivo(null);
			cab.setNumBenefEmpPerAntRural(Integer.valueOf(bean.getNumRural())); 
			cab.setNumBenefEmpPerAntUrbProv(Integer.valueOf(bean.getNumUrbProv())); 
			cab.setNumBenefEmpPerAntUrbLima(Integer.valueOf(bean.getNumUrbLima()));  
			cab.setCostoPromMenRural(new BigDecimal(bean.getCostoPromRural()));
			cab.setCostoPromMenUrbProv(new BigDecimal(bean.getCostoPromUrbProv()));
			cab.setCostoPromMenUrbLima(new BigDecimal(bean.getCostoPromUrbLima()));  
			cab.setUsuarioActualizacion("USER2"); 
			cab.setTerminalActualizacion("192.168.1.20"); 
			cab.setFechaActualizacion(FechaUtil.obtenerFechaActual()); 
			formato14CCDao.actualizarFiseFormato14CC(cab); 
		
			/*Grabando en el detalle de la cabecera*/
		    
			/**Grabando el primer detalle Rural-Coordinador**/			
			idDet = new FiseFormato14CDPK();		
			idDet.setCodEmpresa(bean.getCodEmpresa());
			idDet.setAnoPresentacion(Long.valueOf(bean.getAnioPres())); 
			idDet.setMesPresentacion(Long.valueOf(bean.getMesPres())); 
			idDet.setAnoInicioVigencia(Long.valueOf(bean.getAnioPres())); 
			idDet.setAnoFinVigencia(Long.valueOf(bean.getAnioPres())); 
			idDet.setEtapa(bean.getEtapa()); 
			idDet.setIdTipPersonal(FiseConstants.TIPO_PERSONAL_CORDINADOR_COD);
			idDet.setIdZonaBenef(FiseConstants.ZONABENEF_RURAL_COD); 		
			det = formato14CDDao.obtenerFiseFormato14CD(idDet);
			det.setCantidad(Integer.valueOf(bean.getCanRCoord()));  
			det.setCostoDirecto(new BigDecimal(bean.getCostDRCoord())); 
			det.setCostoIndirecto(new BigDecimal(bean.getCostIRCoord()));  
			det.setUsuarioActualizacion("USER2"); 
			det.setTerminalActualizacion("192.168.1.20"); 
			det.setFechaActualizacion(FechaUtil.obtenerFechaActual()); 
			formato14CDDao.actualizarFiseFormato14CD(det); 
			
			/**Grabando el primer detalle Urbano Provincia-Coordinador**/			
			idDet = new FiseFormato14CDPK();		
			idDet.setCodEmpresa(bean.getCodEmpresa());
			idDet.setAnoPresentacion(Long.valueOf(bean.getAnioPres())); 
			idDet.setMesPresentacion(Long.valueOf(bean.getMesPres())); 
			idDet.setAnoInicioVigencia(Long.valueOf(bean.getAnioPres())); 
			idDet.setAnoFinVigencia(Long.valueOf(bean.getAnioPres())); 
			idDet.setEtapa(bean.getEtapa()); 
			idDet.setIdTipPersonal(FiseConstants.TIPO_PERSONAL_CORDINADOR_COD);
			idDet.setIdZonaBenef(FiseConstants.ZONABENEF_PROVINCIA_COD); 		
			det = formato14CDDao.obtenerFiseFormato14CD(idDet);
			det.setCantidad(Integer.valueOf(bean.getCanPCoord()));  
			det.setCostoDirecto(new BigDecimal(bean.getCostDPCoord())); 
			det.setCostoIndirecto(new BigDecimal(bean.getCostIPCoord()));  
			det.setUsuarioActualizacion("USER2"); 
			det.setTerminalActualizacion("192.168.1.20"); 
			det.setFechaActualizacion(FechaUtil.obtenerFechaActual()); 	
			formato14CDDao.actualizarFiseFormato14CD(det); 
			
			/**Grabando el primer detalle Urbano Lima-Coordinador**/			
			idDet = new FiseFormato14CDPK();		
			idDet.setCodEmpresa(bean.getCodEmpresa());
			idDet.setAnoPresentacion(Long.valueOf(bean.getAnioPres())); 
			idDet.setMesPresentacion(Long.valueOf(bean.getMesPres())); 
			idDet.setAnoInicioVigencia(Long.valueOf(bean.getAnioPres())); 
			idDet.setAnoFinVigencia(Long.valueOf(bean.getAnioPres())); 
			idDet.setEtapa(bean.getEtapa()); 
			idDet.setIdTipPersonal(FiseConstants.TIPO_PERSONAL_CORDINADOR_COD);
			idDet.setIdZonaBenef(FiseConstants.ZONABENEF_LIMA_COD); 		
			det = formato14CDDao.obtenerFiseFormato14CD(idDet);
			det.setCantidad(Integer.valueOf(bean.getCanLCoord()));  
			det.setCostoDirecto(new BigDecimal(bean.getCostDLCoord())); 
			det.setCostoIndirecto(new BigDecimal(bean.getCostILCoord()));  
			det.setUsuarioActualizacion("USER2"); 
			det.setTerminalActualizacion("192.168.1.20"); 
			det.setFechaActualizacion(FechaUtil.obtenerFechaActual()); 	
			formato14CDDao.actualizarFiseFormato14CD(det);
			
			/**Grabando el primer detalle Rural-Supervisor**/		
			idDet = new FiseFormato14CDPK();		
			idDet.setCodEmpresa(bean.getCodEmpresa());
			idDet.setAnoPresentacion(Long.valueOf(bean.getAnioPres())); 
			idDet.setMesPresentacion(Long.valueOf(bean.getMesPres())); 
			idDet.setAnoInicioVigencia(Long.valueOf(bean.getAnioPres())); 
			idDet.setAnoFinVigencia(Long.valueOf(bean.getAnioPres())); 
			idDet.setEtapa(bean.getEtapa()); 
			idDet.setIdTipPersonal(FiseConstants.TIPO_PERSONAL_SUPERVISOR_COD);
			idDet.setIdZonaBenef(FiseConstants.ZONABENEF_RURAL_COD); 		
			det = formato14CDDao.obtenerFiseFormato14CD(idDet);
			det.setCantidad(Integer.valueOf(bean.getCanRSupe()));  
			det.setCostoDirecto(new BigDecimal(bean.getCostDRSupe())); 
			det.setCostoIndirecto(new BigDecimal(bean.getCostIRSupe()));  
			det.setUsuarioActualizacion("USER2"); 
			det.setTerminalActualizacion("192.168.1.20"); 
			det.setFechaActualizacion(FechaUtil.obtenerFechaActual()); 
			formato14CDDao.actualizarFiseFormato14CD(det);
			
			/**Grabando el primer detalle Urbano Provincias-Supervisor**/			
			idDet = new FiseFormato14CDPK();		
			idDet.setCodEmpresa(bean.getCodEmpresa());
			idDet.setAnoPresentacion(Long.valueOf(bean.getAnioPres())); 
			idDet.setMesPresentacion(Long.valueOf(bean.getMesPres())); 
			idDet.setAnoInicioVigencia(Long.valueOf(bean.getAnioPres())); 
			idDet.setAnoFinVigencia(Long.valueOf(bean.getAnioPres())); 
			idDet.setEtapa(bean.getEtapa()); 
			idDet.setIdTipPersonal(FiseConstants.TIPO_PERSONAL_SUPERVISOR_COD);
			idDet.setIdZonaBenef(FiseConstants.ZONABENEF_PROVINCIA_COD); 		
			det = formato14CDDao.obtenerFiseFormato14CD(idDet);
			det.setCantidad(Integer.valueOf(bean.getCanPSupe()));  
			det.setCostoDirecto(new BigDecimal(bean.getCostDPSupe())); 
			det.setCostoIndirecto(new BigDecimal(bean.getCostIPSupe()));  
			det.setUsuarioActualizacion("USER2"); 
			det.setTerminalActualizacion("192.168.1.20"); 
			det.setFechaActualizacion(FechaUtil.obtenerFechaActual()); 	
			formato14CDDao.actualizarFiseFormato14CD(det);
			
			/**Grabando el primer detalle Urbana Lima -Supervisor**/			
			idDet = new FiseFormato14CDPK();		
			idDet.setCodEmpresa(bean.getCodEmpresa());
			idDet.setAnoPresentacion(Long.valueOf(bean.getAnioPres())); 
			idDet.setMesPresentacion(Long.valueOf(bean.getMesPres())); 
			idDet.setAnoInicioVigencia(Long.valueOf(bean.getAnioPres())); 
			idDet.setAnoFinVigencia(Long.valueOf(bean.getAnioPres())); 
			idDet.setEtapa(bean.getEtapa()); 
			idDet.setIdTipPersonal(FiseConstants.TIPO_PERSONAL_SUPERVISOR_COD);
			idDet.setIdZonaBenef(FiseConstants.ZONABENEF_LIMA_COD); 		
			det = formato14CDDao.obtenerFiseFormato14CD(idDet);
			det.setCantidad(Integer.valueOf(bean.getCanLSupe()));  
			det.setCostoDirecto(new BigDecimal(bean.getCostDLSupe())); 
			det.setCostoIndirecto(new BigDecimal(bean.getCostILSupe()));  
			det.setUsuarioActualizacion("USER2"); 
			det.setTerminalActualizacion("192.168.1.20"); 
			det.setFechaActualizacion(FechaUtil.obtenerFechaActual()); 
			formato14CDDao.actualizarFiseFormato14CD(det);
			
			/**Grabando el primer detalle Rural-Gestor**/			
			idDet = new FiseFormato14CDPK();		
			idDet.setCodEmpresa(bean.getCodEmpresa());
			idDet.setAnoPresentacion(Long.valueOf(bean.getAnioPres())); 
			idDet.setMesPresentacion(Long.valueOf(bean.getMesPres())); 
			idDet.setAnoInicioVigencia(Long.valueOf(bean.getAnioPres())); 
			idDet.setAnoFinVigencia(Long.valueOf(bean.getAnioPres())); 
			idDet.setEtapa(bean.getEtapa()); 
			idDet.setIdTipPersonal(FiseConstants.TIPO_PERSONAL_GESTOR_COD);
			idDet.setIdZonaBenef(FiseConstants.ZONABENEF_RURAL_COD); 		
			det = formato14CDDao.obtenerFiseFormato14CD(idDet);
			det.setCantidad(Integer.valueOf(bean.getCanRGest()));  
			det.setCostoDirecto(new BigDecimal(bean.getCostDRGest())); 
			det.setCostoIndirecto(new BigDecimal(bean.getCostIRGest()));  
			det.setUsuarioActualizacion("USER2"); 
			det.setTerminalActualizacion("192.168.1.20"); 
			det.setFechaActualizacion(FechaUtil.obtenerFechaActual()); 
			formato14CDDao.actualizarFiseFormato14CD(det);
			
			/**Grabando el primer detalle Urbana Provincias-Gestor**/			
			idDet = new FiseFormato14CDPK();		
			idDet.setCodEmpresa(bean.getCodEmpresa());
			idDet.setAnoPresentacion(Long.valueOf(bean.getAnioPres())); 
			idDet.setMesPresentacion(Long.valueOf(bean.getMesPres())); 
			idDet.setAnoInicioVigencia(Long.valueOf(bean.getAnioPres())); 
			idDet.setAnoFinVigencia(Long.valueOf(bean.getAnioPres())); 
			idDet.setEtapa(bean.getEtapa()); 
			idDet.setIdTipPersonal(FiseConstants.TIPO_PERSONAL_GESTOR_COD);
			idDet.setIdZonaBenef(FiseConstants.ZONABENEF_PROVINCIA_COD); 		
			det = formato14CDDao.obtenerFiseFormato14CD(idDet);
			det.setCantidad(Integer.valueOf(bean.getCanPGest()));  
			det.setCostoDirecto(new BigDecimal(bean.getCostDPGest())); 
			det.setCostoIndirecto(new BigDecimal(bean.getCostIPGest()));  
			det.setUsuarioActualizacion("USER2"); 
			det.setTerminalActualizacion("192.168.1.20"); 
			det.setFechaActualizacion(FechaUtil.obtenerFechaActual()); 
			formato14CDDao.actualizarFiseFormato14CD(det);
			
			/**Grabando el primer detalle Urbana Lima -Gestor**/			
			idDet = new FiseFormato14CDPK();		
			idDet.setCodEmpresa(bean.getCodEmpresa());
			idDet.setAnoPresentacion(Long.valueOf(bean.getAnioPres())); 
			idDet.setMesPresentacion(Long.valueOf(bean.getMesPres())); 
			idDet.setAnoInicioVigencia(Long.valueOf(bean.getAnioPres())); 
			idDet.setAnoFinVigencia(Long.valueOf(bean.getAnioPres())); 
			idDet.setEtapa(bean.getEtapa()); 
			idDet.setIdTipPersonal(FiseConstants.TIPO_PERSONAL_GESTOR_COD);
			idDet.setIdZonaBenef(FiseConstants.ZONABENEF_LIMA_COD); 		
			det = formato14CDDao.obtenerFiseFormato14CD(idDet);
			det.setCantidad(Integer.valueOf(bean.getCanLGest()));  
			det.setCostoDirecto(new BigDecimal(bean.getCostDLGest())); 
			det.setCostoIndirecto(new BigDecimal(bean.getCostILGest()));  
			det.setUsuarioActualizacion("USER2"); 
			det.setTerminalActualizacion("192.168.1.20"); 
			det.setFechaActualizacion(FechaUtil.obtenerFechaActual()); 
			formato14CDDao.actualizarFiseFormato14CD(det);
			
			/**Grabando el primer detalle Rural-Asistente/Auxiliar**/			
			idDet = new FiseFormato14CDPK();		
			idDet.setCodEmpresa(bean.getCodEmpresa());
			idDet.setAnoPresentacion(Long.valueOf(bean.getAnioPres())); 
			idDet.setMesPresentacion(Long.valueOf(bean.getMesPres())); 
			idDet.setAnoInicioVigencia(Long.valueOf(bean.getAnioPres())); 
			idDet.setAnoFinVigencia(Long.valueOf(bean.getAnioPres())); 
			idDet.setEtapa(bean.getEtapa()); 
			idDet.setIdTipPersonal(FiseConstants.TIPO_PERSONAL_ASISTENTE_COD);
			idDet.setIdZonaBenef(FiseConstants.ZONABENEF_RURAL_COD); 		
			det = formato14CDDao.obtenerFiseFormato14CD(idDet);
			det.setCantidad(Integer.valueOf(bean.getCanRAsist()));  
			det.setCostoDirecto(new BigDecimal(bean.getCostDRAsist())); 
			det.setCostoIndirecto(new BigDecimal(bean.getCostIRAsist()));  
			det.setUsuarioActualizacion("USER2"); 
			det.setTerminalActualizacion("192.168.1.20"); 
			det.setFechaActualizacion(FechaUtil.obtenerFechaActual()); 
			formato14CDDao.actualizarFiseFormato14CD(det);
			
			/**Grabando el primer detalle Urbano Provincias -Asistente/Auxiliar**/			
			idDet = new FiseFormato14CDPK();		
			idDet.setCodEmpresa(bean.getCodEmpresa());
			idDet.setAnoPresentacion(Long.valueOf(bean.getAnioPres())); 
			idDet.setMesPresentacion(Long.valueOf(bean.getMesPres())); 
			idDet.setAnoInicioVigencia(Long.valueOf(bean.getAnioPres())); 
			idDet.setAnoFinVigencia(Long.valueOf(bean.getAnioPres())); 
			idDet.setEtapa(bean.getEtapa()); 
			idDet.setIdTipPersonal(FiseConstants.TIPO_PERSONAL_ASISTENTE_COD);
			idDet.setIdZonaBenef(FiseConstants.ZONABENEF_PROVINCIA_COD); 		
			det = formato14CDDao.obtenerFiseFormato14CD(idDet);
			det.setCantidad(Integer.valueOf(bean.getCanPAsist()));  
			det.setCostoDirecto(new BigDecimal(bean.getCostDPAsist())); 
			det.setCostoIndirecto(new BigDecimal(bean.getCostIPAsist()));  
			det.setUsuarioActualizacion("USER2"); 
			det.setTerminalActualizacion("192.168.1.20"); 
			det.setFechaActualizacion(FechaUtil.obtenerFechaActual()); 
			formato14CDDao.actualizarFiseFormato14CD(det);
			
			/**Grabando el primer detalle Urbana Lima -Asistente/Auxiliar**/			
			idDet = new FiseFormato14CDPK();		
			idDet.setCodEmpresa(bean.getCodEmpresa());
			idDet.setAnoPresentacion(Long.valueOf(bean.getAnioPres())); 
			idDet.setMesPresentacion(Long.valueOf(bean.getMesPres())); 
			idDet.setAnoInicioVigencia(Long.valueOf(bean.getAnioPres())); 
			idDet.setAnoFinVigencia(Long.valueOf(bean.getAnioPres())); 
			idDet.setEtapa(bean.getEtapa()); 
			idDet.setIdTipPersonal(FiseConstants.TIPO_PERSONAL_ASISTENTE_COD);
			idDet.setIdZonaBenef(FiseConstants.ZONABENEF_LIMA_COD); 		
			det = formato14CDDao.obtenerFiseFormato14CD(idDet);
			det.setCantidad(Integer.valueOf(bean.getCanLAsist()));  
			det.setCostoDirecto(new BigDecimal(bean.getCostDLAsist())); 
			det.setCostoIndirecto(new BigDecimal(bean.getCostILAsist()));  
			det.setUsuarioActualizacion("USER2"); 
			det.setTerminalActualizacion("192.168.1.20"); 
			det.setFechaActualizacion(FechaUtil.obtenerFechaActual()); 	
			formato14CDDao.actualizarFiseFormato14CD(det);
			
		} catch (Exception e) { 
		   logger.info("Error al actualizar datos del formato 14c:  "+e);
		   e.printStackTrace();
		}finally{
			if(cab!=null){
				cab=null;
			}
			if(idCab!=null){
				idCab=null;
			}
			if(det!=null){
				det=null;
			}
			if(idDet!=null){
				idDet=null;
			}
		}
	}
	
	@Transactional
	public void eliminarDatosFormato14C(Formato14CBean bean) throws Exception{
		
	}
	

}
