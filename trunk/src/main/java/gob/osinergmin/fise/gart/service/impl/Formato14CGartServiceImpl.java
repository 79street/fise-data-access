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
	@Transactional
	public List<FiseFormato14CC> listarFormato14CC() 
			throws Exception{
		return formato14CCDao.listarFormato14CC();
	}
	
	/**Metodo para listar el formato 14C detalle*/
	@Override
	@Transactional
	public List<FiseFormato14CD> listarFormato14CD() 
			throws Exception{
		return formato14CDDao.listarFormato14CD();
	}
	
	
	/**Metodo para insertar Cabecera y Detalle del formato 14C*/
	@Override
	@Transactional(propagation = Propagation.REQUIRED, rollbackFor = Exception.class)	
	public String insertarDatosFormato14C(Formato14CBean bean) throws Exception{
		FiseFormato14CC cab = null;
		FiseFormato14CCPK idCab = null;
		FiseFormato14CD det = null;
		FiseFormato14CDPK idDet = null;
		String valor = "1";
		try {
		  /*Grabando en la cabecera*/
			
			idCab = new FiseFormato14CCPK();
			//PK
			idCab.setCodEmpresa(bean.getCodEmpresa());
			idCab.setAnoPresentacion(Long.valueOf(bean.getAnioPres())); 
			idCab.setMesPresentacion(Long.valueOf(bean.getMesPres())); 
			idCab.setAnoInicioVigencia(Long.valueOf(bean.getAnioInicioVig())); 
			idCab.setAnoFinVigencia(Long.valueOf(bean.getAnioFinVig())); 
			idCab.setEtapa(bean.getEtapa()); 
			//FIN PK
			/**verificamos que el registro no este en la base de datos caso contrario no insertamos**/
			cab = formato14CCDao.obtenerFormato14CC(idCab); 
			if(cab==null){
				cab = new FiseFormato14CC();
				cab.setId(idCab);
				cab.setNombreSede(bean.getNombreSede());
				logger.info("Obtneniendo el obk informacion: "); 
				FiseGrupoInformacion inf = fiseGrupoInformacionDao.obtenerFiseGrupoInformacionByPK(new Long(1000));	//falta		
				logger.info("Obtneniendo el obk informacion: "+inf.getDescripcion()); 
				cab.setFiseGrupoInformacion(inf);  
				cab.setNombreArchivoExcel("");
				cab.setNombreArchivoTexto(""); 
				cab.setFechaEnvioDefinitivo(null);
				cab.setNumBenefEmpPerAntRural(Long.valueOf(bean.getNumRural())); 
				cab.setNumBenefEmpPerAntUrbProv(Long.valueOf(bean.getNumUrbProv())); 
				cab.setNumBenefEmpPerAntUrbLima(Long.valueOf(bean.getNumUrbLima()));  
				cab.setCostoPromMenRural(new BigDecimal(bean.getCostoPromRural()));
				cab.setCostoPromMenUrbProv(new BigDecimal(bean.getCostoPromUrbProv()));
				cab.setCostoPromMenUrbLima(new BigDecimal(bean.getCostoPromUrbLima()));  
				cab.setUsuarioCreacion(bean.getUsuario());
				cab.setTerminalCreacion(bean.getTerminal());
				cab.setFechaCreacion(FechaUtil.obtenerFechaActual());	
				formato14CCDao.insertarFiseFormato14CC(cab); 
				logger.info("GRABO EN LA CABECERA OK");		
				/*Grabando en el detalle de la cabecera*/
			    
				/**Grabando el primer detalle Rural-Coordinador**/
				det = new  FiseFormato14CD();
				idDet = new FiseFormato14CDPK();		
				idDet.setCodEmpresa(bean.getCodEmpresa());
				idDet.setAnoPresentacion(Long.valueOf(bean.getAnioPres())); 
				idDet.setMesPresentacion(Long.valueOf(bean.getMesPres())); 
				idDet.setAnoInicioVigencia(Long.valueOf(bean.getAnioInicioVig())); 
				idDet.setAnoFinVigencia(Long.valueOf(bean.getAnioFinVig())); 
				idDet.setEtapa(bean.getEtapa()); 
				idDet.setIdTipPersonal(FiseConstants.TIPO_PERSONAL_CORDINADOR_COD);
				idDet.setIdZonaBenef(FiseConstants.ZONABENEF_RURAL_COD);
	            det.setId(idDet); 		
				det.setCantCostDirecto(Long.valueOf(bean.getCanDRCoord()));
				det.setCantCostIndirecto(Long.valueOf(bean.getCanIRCoord())); 
				det.setCostoDirecto(new BigDecimal(bean.getCostDRCoord())); 
				det.setCostoIndirecto(new BigDecimal(bean.getCostIRCoord()));  
				det.setUsuarioCreacion(bean.getUsuario());
				det.setTerminalCreacion(bean.getTerminal());
				det.setFechaCreacion(FechaUtil.obtenerFechaActual());	
				formato14CDDao.insertarFiseFormato14CD(det); 
				/**Grabando el primer detalle Urbano Provincia-Coordinador**/
				det = new  FiseFormato14CD();
				idDet = new FiseFormato14CDPK();		
				idDet.setCodEmpresa(bean.getCodEmpresa());
				idDet.setAnoPresentacion(Long.valueOf(bean.getAnioPres())); 
				idDet.setMesPresentacion(Long.valueOf(bean.getMesPres()));
				idDet.setAnoInicioVigencia(Long.valueOf(bean.getAnioInicioVig())); 
				idDet.setAnoFinVigencia(Long.valueOf(bean.getAnioFinVig())); 		
				idDet.setEtapa(bean.getEtapa()); 
				idDet.setIdTipPersonal(FiseConstants.TIPO_PERSONAL_CORDINADOR_COD);
				idDet.setIdZonaBenef(FiseConstants.ZONABENEF_PROVINCIA_COD); 
				det.setId(idDet); 	
				det.setCantCostDirecto(Long.valueOf(bean.getCanDPCoord()));
				det.setCantCostIndirecto(Long.valueOf(bean.getCanIPCoord())); 			
				det.setCostoDirecto(new BigDecimal(bean.getCostDPCoord())); 
				det.setCostoIndirecto(new BigDecimal(bean.getCostIPCoord()));  
				det.setUsuarioCreacion(bean.getUsuario());
				det.setTerminalCreacion(bean.getTerminal());
				det.setFechaCreacion(FechaUtil.obtenerFechaActual());	
				formato14CDDao.insertarFiseFormato14CD(det); 
				/**Grabando el primer detalle Urbano Lima-Coordinador**/
				det = new  FiseFormato14CD();
				idDet = new FiseFormato14CDPK();		
				idDet.setCodEmpresa(bean.getCodEmpresa());
				idDet.setAnoPresentacion(Long.valueOf(bean.getAnioPres())); 
				idDet.setMesPresentacion(Long.valueOf(bean.getMesPres())); 
				idDet.setAnoInicioVigencia(Long.valueOf(bean.getAnioInicioVig())); 
				idDet.setAnoFinVigencia(Long.valueOf(bean.getAnioFinVig())); 	
				idDet.setEtapa(bean.getEtapa()); 
				idDet.setIdTipPersonal(FiseConstants.TIPO_PERSONAL_CORDINADOR_COD);
				idDet.setIdZonaBenef(FiseConstants.ZONABENEF_LIMA_COD); 
				det.setId(idDet); 	
				det.setCantCostDirecto(Long.valueOf(bean.getCanDLCoord()));
				det.setCantCostIndirecto(Long.valueOf(bean.getCanILCoord()));			
				det.setCostoDirecto(new BigDecimal(bean.getCostDLCoord())); 
				det.setCostoIndirecto(new BigDecimal(bean.getCostILCoord()));  
				det.setUsuarioCreacion(bean.getUsuario());
				det.setTerminalCreacion(bean.getTerminal());
				det.setFechaCreacion(FechaUtil.obtenerFechaActual());	
				formato14CDDao.insertarFiseFormato14CD(det);
				
				/**Grabando el primer detalle Rural-Supervisor**/
				det = new  FiseFormato14CD();
				idDet = new FiseFormato14CDPK();		
				idDet.setCodEmpresa(bean.getCodEmpresa());
				idDet.setAnoPresentacion(Long.valueOf(bean.getAnioPres())); 
				idDet.setMesPresentacion(Long.valueOf(bean.getMesPres())); 
				idDet.setAnoInicioVigencia(Long.valueOf(bean.getAnioInicioVig())); 
				idDet.setAnoFinVigencia(Long.valueOf(bean.getAnioFinVig())); 	
				idDet.setEtapa(bean.getEtapa()); 
				idDet.setIdTipPersonal(FiseConstants.TIPO_PERSONAL_SUPERVISOR_COD);
				idDet.setIdZonaBenef(FiseConstants.ZONABENEF_RURAL_COD); 
				det.setId(idDet); 	
				det.setCantCostDirecto(Long.valueOf(bean.getCanDRSupe()));
				det.setCantCostIndirecto(Long.valueOf(bean.getCanIRSupe())); 			
				det.setCostoDirecto(new BigDecimal(bean.getCostDRSupe())); 
				det.setCostoIndirecto(new BigDecimal(bean.getCostIRSupe()));  
				det.setUsuarioCreacion(bean.getUsuario());
				det.setTerminalCreacion(bean.getTerminal());
				det.setFechaCreacion(FechaUtil.obtenerFechaActual());	
				formato14CDDao.insertarFiseFormato14CD(det);
				
				/**Grabando el primer detalle Urbano Provincias-Supervisor**/
				det = new  FiseFormato14CD();
				idDet = new FiseFormato14CDPK();		
				idDet.setCodEmpresa(bean.getCodEmpresa());
				idDet.setAnoPresentacion(Long.valueOf(bean.getAnioPres())); 
				idDet.setMesPresentacion(Long.valueOf(bean.getMesPres())); 
				idDet.setAnoInicioVigencia(Long.valueOf(bean.getAnioInicioVig())); 
				idDet.setAnoFinVigencia(Long.valueOf(bean.getAnioFinVig())); 	
				idDet.setEtapa(bean.getEtapa()); 
				idDet.setIdTipPersonal(FiseConstants.TIPO_PERSONAL_SUPERVISOR_COD);
				idDet.setIdZonaBenef(FiseConstants.ZONABENEF_PROVINCIA_COD); 
				det.setId(idDet); 	
				det.setCantCostDirecto(Long.valueOf(bean.getCanDPSupe()));
				det.setCantCostIndirecto(Long.valueOf(bean.getCanIPSupe())); 			
				det.setCostoDirecto(new BigDecimal(bean.getCostDPSupe())); 
				det.setCostoIndirecto(new BigDecimal(bean.getCostIPSupe()));  
				det.setUsuarioCreacion(bean.getUsuario());
				det.setTerminalCreacion(bean.getTerminal());
				det.setFechaCreacion(FechaUtil.obtenerFechaActual());	
				formato14CDDao.insertarFiseFormato14CD(det);
				
				/**Grabando el primer detalle Urbana Lima -Supervisor**/
				det = new  FiseFormato14CD();
				idDet = new FiseFormato14CDPK();		
				idDet.setCodEmpresa(bean.getCodEmpresa());
				idDet.setAnoPresentacion(Long.valueOf(bean.getAnioPres())); 
				idDet.setMesPresentacion(Long.valueOf(bean.getMesPres())); 
				idDet.setAnoInicioVigencia(Long.valueOf(bean.getAnioInicioVig())); 
				idDet.setAnoFinVigencia(Long.valueOf(bean.getAnioFinVig())); 	
				idDet.setEtapa(bean.getEtapa()); 
				idDet.setIdTipPersonal(FiseConstants.TIPO_PERSONAL_SUPERVISOR_COD);
				idDet.setIdZonaBenef(FiseConstants.ZONABENEF_LIMA_COD);
				det.setId(idDet); 	
				det.setCantCostDirecto(Long.valueOf(bean.getCanDLSupe()));
				det.setCantCostIndirecto(Long.valueOf(bean.getCanILSupe())); 	
				det.setCostoDirecto(new BigDecimal(bean.getCostDLSupe())); 
				det.setCostoIndirecto(new BigDecimal(bean.getCostILSupe()));  
				det.setUsuarioCreacion(bean.getUsuario());
				det.setTerminalCreacion(bean.getTerminal());
				det.setFechaCreacion(FechaUtil.obtenerFechaActual());	
				formato14CDDao.insertarFiseFormato14CD(det);
				
				/**Grabando el primer detalle Rural-Gestor**/
				det = new  FiseFormato14CD();
				idDet = new FiseFormato14CDPK();		
				idDet.setCodEmpresa(bean.getCodEmpresa());
				idDet.setAnoPresentacion(Long.valueOf(bean.getAnioPres())); 
				idDet.setMesPresentacion(Long.valueOf(bean.getMesPres())); 
				idDet.setAnoInicioVigencia(Long.valueOf(bean.getAnioInicioVig())); 
				idDet.setAnoFinVigencia(Long.valueOf(bean.getAnioFinVig())); 	
				idDet.setEtapa(bean.getEtapa()); 
				idDet.setIdTipPersonal(FiseConstants.TIPO_PERSONAL_GESTOR_COD);
				idDet.setIdZonaBenef(FiseConstants.ZONABENEF_RURAL_COD);
				det.setId(idDet); 	
				det.setCantCostDirecto(Long.valueOf(bean.getCanDRGest()));
				det.setCantCostIndirecto(Long.valueOf(bean.getCanIRGest())); 			
				det.setCostoDirecto(new BigDecimal(bean.getCostDRGest())); 
				det.setCostoIndirecto(new BigDecimal(bean.getCostIRGest()));  
				det.setUsuarioCreacion(bean.getUsuario());
				det.setTerminalCreacion(bean.getTerminal());
				det.setFechaCreacion(FechaUtil.obtenerFechaActual());	
				formato14CDDao.insertarFiseFormato14CD(det);
				
				/**Grabando el primer detalle Urbana Provincias-Gestor**/
				det = new  FiseFormato14CD();
				idDet = new FiseFormato14CDPK();		
				idDet.setCodEmpresa(bean.getCodEmpresa());
				idDet.setAnoPresentacion(Long.valueOf(bean.getAnioPres())); 
				idDet.setMesPresentacion(Long.valueOf(bean.getMesPres())); 
				idDet.setAnoInicioVigencia(Long.valueOf(bean.getAnioInicioVig())); 
				idDet.setAnoFinVigencia(Long.valueOf(bean.getAnioFinVig())); 	
				idDet.setEtapa(bean.getEtapa()); 
				idDet.setIdTipPersonal(FiseConstants.TIPO_PERSONAL_GESTOR_COD);
				idDet.setIdZonaBenef(FiseConstants.ZONABENEF_PROVINCIA_COD); 
				det.setId(idDet); 	
				det.setCantCostDirecto(Long.valueOf(bean.getCanDPGest()));
				det.setCantCostIndirecto(Long.valueOf(bean.getCanIPGest())); 		
				det.setCostoDirecto(new BigDecimal(bean.getCostDPGest())); 
				det.setCostoIndirecto(new BigDecimal(bean.getCostIPGest()));  
				det.setUsuarioCreacion(bean.getUsuario());
				det.setTerminalCreacion(bean.getTerminal());
				det.setFechaCreacion(FechaUtil.obtenerFechaActual());	
				formato14CDDao.insertarFiseFormato14CD(det);
				
				/**Grabando el primer detalle Urbana Lima -Gestor**/
				det = new  FiseFormato14CD();
				idDet = new FiseFormato14CDPK();		
				idDet.setCodEmpresa(bean.getCodEmpresa());
				idDet.setAnoPresentacion(Long.valueOf(bean.getAnioPres())); 
				idDet.setMesPresentacion(Long.valueOf(bean.getMesPres())); 
				idDet.setAnoInicioVigencia(Long.valueOf(bean.getAnioInicioVig())); 
				idDet.setAnoFinVigencia(Long.valueOf(bean.getAnioFinVig())); 	
				idDet.setEtapa(bean.getEtapa()); 
				idDet.setIdTipPersonal(FiseConstants.TIPO_PERSONAL_GESTOR_COD);
				idDet.setIdZonaBenef(FiseConstants.ZONABENEF_LIMA_COD); 
				det.setId(idDet); 	
				det.setCantCostDirecto(Long.valueOf(bean.getCanDLGest()));
				det.setCantCostIndirecto(Long.valueOf(bean.getCanDLGest())); 				
				det.setCostoDirecto(new BigDecimal(bean.getCostDLGest())); 
				det.setCostoIndirecto(new BigDecimal(bean.getCostILGest()));  
				det.setUsuarioCreacion(bean.getUsuario());
				det.setTerminalCreacion(bean.getTerminal());
				det.setFechaCreacion(FechaUtil.obtenerFechaActual());	
				formato14CDDao.insertarFiseFormato14CD(det);
				
				/**Grabando el primer detalle Rural-Asistente/Auxiliar**/
				det = new  FiseFormato14CD();
				idDet = new FiseFormato14CDPK();		
				idDet.setCodEmpresa(bean.getCodEmpresa());
				idDet.setAnoPresentacion(Long.valueOf(bean.getAnioPres())); 
				idDet.setMesPresentacion(Long.valueOf(bean.getMesPres())); 
				idDet.setAnoInicioVigencia(Long.valueOf(bean.getAnioInicioVig())); 
				idDet.setAnoFinVigencia(Long.valueOf(bean.getAnioFinVig())); 	
				idDet.setEtapa(bean.getEtapa()); 
				idDet.setIdTipPersonal(FiseConstants.TIPO_PERSONAL_ASISTENTE_COD);
				idDet.setIdZonaBenef(FiseConstants.ZONABENEF_RURAL_COD); 
				det.setId(idDet); 	
				det.setCantCostDirecto(Long.valueOf(bean.getCanDRAsist()));
				det.setCantCostIndirecto(Long.valueOf(bean.getCanIRAsist())); 		
				det.setCostoDirecto(new BigDecimal(bean.getCostDRAsist())); 
				det.setCostoIndirecto(new BigDecimal(bean.getCostIRAsist()));  
				det.setUsuarioCreacion(bean.getUsuario());
				det.setTerminalCreacion(bean.getTerminal());
				det.setFechaCreacion(FechaUtil.obtenerFechaActual());	
				formato14CDDao.insertarFiseFormato14CD(det);
				
				/**Grabando el primer detalle Urbano Provincias -Asistente/Auxiliar**/
				det = new  FiseFormato14CD();
				idDet = new FiseFormato14CDPK();		
				idDet.setCodEmpresa(bean.getCodEmpresa());
				idDet.setAnoPresentacion(Long.valueOf(bean.getAnioPres())); 
				idDet.setMesPresentacion(Long.valueOf(bean.getMesPres())); 
				idDet.setAnoInicioVigencia(Long.valueOf(bean.getAnioInicioVig())); 
				idDet.setAnoFinVigencia(Long.valueOf(bean.getAnioFinVig())); 	
				idDet.setEtapa(bean.getEtapa()); 
				idDet.setIdTipPersonal(FiseConstants.TIPO_PERSONAL_ASISTENTE_COD);
				idDet.setIdZonaBenef(FiseConstants.ZONABENEF_PROVINCIA_COD);
				det.setId(idDet); 	
				det.setCantCostDirecto(Long.valueOf(bean.getCanDPAsist()));
				det.setCantCostIndirecto(Long.valueOf(bean.getCanIPAsist()));			
				det.setCostoDirecto(new BigDecimal(bean.getCostDPAsist())); 
				det.setCostoIndirecto(new BigDecimal(bean.getCostIPAsist()));  
				det.setUsuarioCreacion(bean.getUsuario());
				det.setTerminalCreacion(bean.getTerminal());
				det.setFechaCreacion(FechaUtil.obtenerFechaActual());	
				formato14CDDao.insertarFiseFormato14CD(det);
				
				/**Grabando el primer detalle Urbana Lima -Asistente/Auxiliar**/
				det = new  FiseFormato14CD();
				idDet = new FiseFormato14CDPK();		
				idDet.setCodEmpresa(bean.getCodEmpresa());
				idDet.setAnoPresentacion(Long.valueOf(bean.getAnioPres())); 
				idDet.setMesPresentacion(Long.valueOf(bean.getMesPres())); 
				idDet.setAnoInicioVigencia(Long.valueOf(bean.getAnioInicioVig())); 
				idDet.setAnoFinVigencia(Long.valueOf(bean.getAnioFinVig())); 	
				idDet.setEtapa(bean.getEtapa()); 
				idDet.setIdTipPersonal(FiseConstants.TIPO_PERSONAL_ASISTENTE_COD);
				idDet.setIdZonaBenef(FiseConstants.ZONABENEF_LIMA_COD);
				det.setId(idDet); 	
				det.setCantCostDirecto(Long.valueOf(bean.getCanDLAsist()));
				det.setCantCostIndirecto(Long.valueOf(bean.getCanILAsist()));	
				det.setCostoDirecto(new BigDecimal(bean.getCostDLAsist())); 
				det.setCostoIndirecto(new BigDecimal(bean.getCostILAsist()));  
				det.setUsuarioCreacion(bean.getUsuario());
				det.setTerminalCreacion(bean.getTerminal());
				det.setFechaCreacion(FechaUtil.obtenerFechaActual());	
				formato14CDDao.insertarFiseFormato14CD(det);
			}else{
			  valor ="2";	
			}				
		} catch (Exception e) { 
			valor ="0";
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
		return valor;
	}	
	
	/**Metodo para actualizar Cabecera y Detalle del formato 14C*/
	@Override
	@Transactional(propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
	public String actualizarDatosFormato14C(Formato14CBean bean) throws Exception{
		FiseFormato14CC cab = null;
		FiseFormato14CCPK idCab = null;
		FiseFormato14CD det = null;
		FiseFormato14CDPK idDet = null;
		String valor ="1";
		try {
		  /*Grabando en la cabecera*/		
			idCab = new FiseFormato14CCPK();
			//PK
			idCab.setCodEmpresa(bean.getCodEmpresa());
			idCab.setAnoPresentacion(Long.valueOf(bean.getAnioPres())); 
			idCab.setMesPresentacion(Long.valueOf(bean.getMesPres())); 
			idCab.setAnoInicioVigencia(Long.valueOf(bean.getAnioInicioVig())); 
			idCab.setAnoFinVigencia(Long.valueOf(bean.getAnioFinVig())); 
			idCab.setEtapa(bean.getEtapa()); 
			//FIN PK
			cab = formato14CCDao.obtenerFormato14CC(idCab); 
			logger.info("objeto en sesion : "+cab);
			logger.info("Nombre sede: "+bean.getNombreSede());
			cab.setNombreSede(bean.getNombreSede());
			FiseGrupoInformacion inf = fiseGrupoInformacionDao.obtenerFiseGrupoInformacionByPK(new Long(1000)); 
			cab.setFiseGrupoInformacion(inf);  
			cab.setNombreArchivoExcel("");
			cab.setNombreArchivoTexto(""); 
			cab.setFechaEnvioDefinitivo(null);
			cab.setNumBenefEmpPerAntRural(Long.valueOf(bean.getNumRural())); 
			cab.setNumBenefEmpPerAntUrbProv(Long.valueOf(bean.getNumUrbProv())); 
			cab.setNumBenefEmpPerAntUrbLima(Long.valueOf(bean.getNumUrbLima()));  
			cab.setCostoPromMenRural(new BigDecimal(bean.getCostoPromRural()));
			cab.setCostoPromMenUrbProv(new BigDecimal(bean.getCostoPromUrbProv()));
			cab.setCostoPromMenUrbLima(new BigDecimal(bean.getCostoPromUrbLima()));  
			cab.setUsuarioActualizacion(bean.getUsuario()); 
			cab.setTerminalActualizacion(bean.getTerminal()); 
			cab.setFechaActualizacion(FechaUtil.obtenerFechaActual()); 
			formato14CCDao.actualizarFiseFormato14CC(cab); 
		
			/*Grabando en el detalle de la cabecera*/
		    
			/**Grabando el primer detalle Rural-Coordinador**/			
			idDet = new FiseFormato14CDPK();		
			idDet.setCodEmpresa(bean.getCodEmpresa());
			idDet.setAnoPresentacion(Long.valueOf(bean.getAnioPres())); 
			idDet.setMesPresentacion(Long.valueOf(bean.getMesPres())); 
			idDet.setAnoInicioVigencia(Long.valueOf(bean.getAnioInicioVig())); 
			idDet.setAnoFinVigencia(Long.valueOf(bean.getAnioFinVig())); 	
			idDet.setEtapa(bean.getEtapa()); 
			idDet.setIdTipPersonal(FiseConstants.TIPO_PERSONAL_CORDINADOR_COD);
			idDet.setIdZonaBenef(FiseConstants.ZONABENEF_RURAL_COD); 		
			det = formato14CDDao.obtenerFiseFormato14CD(idDet);
			det.setCantCostDirecto(Long.valueOf(bean.getCanDRCoord()));
			det.setCantCostIndirecto(Long.valueOf(bean.getCanIRCoord())); 		
			det.setCostoDirecto(new BigDecimal(bean.getCostDRCoord())); 
			det.setCostoIndirecto(new BigDecimal(bean.getCostIRCoord()));  
			det.setUsuarioActualizacion(bean.getUsuario()); 
			det.setTerminalActualizacion(bean.getTerminal()); 
			det.setFechaActualizacion(FechaUtil.obtenerFechaActual()); 
			formato14CDDao.actualizarFiseFormato14CD(det); 
			
			/**Grabando el primer detalle Urbano Provincia-Coordinador**/			
			idDet = new FiseFormato14CDPK();		
			idDet.setCodEmpresa(bean.getCodEmpresa());
			idDet.setAnoPresentacion(Long.valueOf(bean.getAnioPres())); 
			idDet.setMesPresentacion(Long.valueOf(bean.getMesPres())); 
			idDet.setAnoInicioVigencia(Long.valueOf(bean.getAnioInicioVig())); 
			idDet.setAnoFinVigencia(Long.valueOf(bean.getAnioFinVig())); 	
			idDet.setEtapa(bean.getEtapa()); 
			idDet.setIdTipPersonal(FiseConstants.TIPO_PERSONAL_CORDINADOR_COD);
			idDet.setIdZonaBenef(FiseConstants.ZONABENEF_PROVINCIA_COD); 		
			det = formato14CDDao.obtenerFiseFormato14CD(idDet);
			det.setCantCostDirecto(Long.valueOf(bean.getCanDPCoord()));
			det.setCantCostIndirecto(Long.valueOf(bean.getCanIPCoord())); 		
			det.setCostoDirecto(new BigDecimal(bean.getCostDPCoord())); 
			det.setCostoIndirecto(new BigDecimal(bean.getCostIPCoord()));  
			det.setUsuarioActualizacion(bean.getUsuario()); 
			det.setTerminalActualizacion(bean.getTerminal()); 
			det.setFechaActualizacion(FechaUtil.obtenerFechaActual()); 	
			formato14CDDao.actualizarFiseFormato14CD(det); 
			
			/**Grabando el primer detalle Urbano Lima-Coordinador**/			
			idDet = new FiseFormato14CDPK();		
			idDet.setCodEmpresa(bean.getCodEmpresa());
			idDet.setAnoPresentacion(Long.valueOf(bean.getAnioPres())); 
			idDet.setMesPresentacion(Long.valueOf(bean.getMesPres())); 
			idDet.setAnoInicioVigencia(Long.valueOf(bean.getAnioInicioVig())); 
			idDet.setAnoFinVigencia(Long.valueOf(bean.getAnioFinVig())); 	
			idDet.setEtapa(bean.getEtapa()); 
			idDet.setIdTipPersonal(FiseConstants.TIPO_PERSONAL_CORDINADOR_COD);
			idDet.setIdZonaBenef(FiseConstants.ZONABENEF_LIMA_COD); 		
			det = formato14CDDao.obtenerFiseFormato14CD(idDet);
			det.setCantCostDirecto(Long.valueOf(bean.getCanDLCoord()));
			det.setCantCostIndirecto(Long.valueOf(bean.getCanILCoord()));			
			det.setCostoDirecto(new BigDecimal(bean.getCostDLCoord())); 
			det.setCostoIndirecto(new BigDecimal(bean.getCostILCoord()));  
			det.setUsuarioActualizacion(bean.getUsuario()); 
			det.setTerminalActualizacion(bean.getTerminal()); 
			det.setFechaActualizacion(FechaUtil.obtenerFechaActual()); 	
			formato14CDDao.actualizarFiseFormato14CD(det);
			
			/**Grabando el primer detalle Rural-Supervisor**/		
			idDet = new FiseFormato14CDPK();		
			idDet.setCodEmpresa(bean.getCodEmpresa());
			idDet.setAnoPresentacion(Long.valueOf(bean.getAnioPres())); 
			idDet.setMesPresentacion(Long.valueOf(bean.getMesPres())); 
			idDet.setAnoInicioVigencia(Long.valueOf(bean.getAnioInicioVig())); 
			idDet.setAnoFinVigencia(Long.valueOf(bean.getAnioFinVig())); 	
			idDet.setEtapa(bean.getEtapa()); 
			idDet.setIdTipPersonal(FiseConstants.TIPO_PERSONAL_SUPERVISOR_COD);
			idDet.setIdZonaBenef(FiseConstants.ZONABENEF_RURAL_COD); 		
			det = formato14CDDao.obtenerFiseFormato14CD(idDet);
			det.setCantCostDirecto(Long.valueOf(bean.getCanDRSupe()));
			det.setCantCostIndirecto(Long.valueOf(bean.getCanIRSupe()));		
			det.setCostoDirecto(new BigDecimal(bean.getCostDRSupe())); 
			det.setCostoIndirecto(new BigDecimal(bean.getCostIRSupe()));  
			det.setUsuarioActualizacion(bean.getUsuario()); 
			det.setTerminalActualizacion(bean.getTerminal()); 
			det.setFechaActualizacion(FechaUtil.obtenerFechaActual()); 
			formato14CDDao.actualizarFiseFormato14CD(det);
			
			/**Grabando el primer detalle Urbano Provincias-Supervisor**/			
			idDet = new FiseFormato14CDPK();		
			idDet.setCodEmpresa(bean.getCodEmpresa());
			idDet.setAnoPresentacion(Long.valueOf(bean.getAnioPres())); 
			idDet.setMesPresentacion(Long.valueOf(bean.getMesPres())); 
			idDet.setAnoInicioVigencia(Long.valueOf(bean.getAnioInicioVig())); 
			idDet.setAnoFinVigencia(Long.valueOf(bean.getAnioFinVig())); 	
			idDet.setEtapa(bean.getEtapa()); 
			idDet.setIdTipPersonal(FiseConstants.TIPO_PERSONAL_SUPERVISOR_COD);
			idDet.setIdZonaBenef(FiseConstants.ZONABENEF_PROVINCIA_COD); 		
			det = formato14CDDao.obtenerFiseFormato14CD(idDet);
			det.setCantCostDirecto(Long.valueOf(bean.getCanDPSupe()));
			det.setCantCostIndirecto(Long.valueOf(bean.getCanIPSupe()));		
			det.setCostoDirecto(new BigDecimal(bean.getCostDPSupe())); 
			det.setCostoIndirecto(new BigDecimal(bean.getCostIPSupe()));  
			det.setUsuarioActualizacion(bean.getUsuario()); 
			det.setTerminalActualizacion(bean.getTerminal()); 
			det.setFechaActualizacion(FechaUtil.obtenerFechaActual()); 	
			formato14CDDao.actualizarFiseFormato14CD(det);
			
			/**Grabando el primer detalle Urbana Lima -Supervisor**/			
			idDet = new FiseFormato14CDPK();		
			idDet.setCodEmpresa(bean.getCodEmpresa());
			idDet.setAnoPresentacion(Long.valueOf(bean.getAnioPres())); 
			idDet.setMesPresentacion(Long.valueOf(bean.getMesPres())); 
			idDet.setAnoInicioVigencia(Long.valueOf(bean.getAnioInicioVig())); 
			idDet.setAnoFinVigencia(Long.valueOf(bean.getAnioFinVig())); 	
			idDet.setEtapa(bean.getEtapa()); 
			idDet.setIdTipPersonal(FiseConstants.TIPO_PERSONAL_SUPERVISOR_COD);
			idDet.setIdZonaBenef(FiseConstants.ZONABENEF_LIMA_COD); 		
			det = formato14CDDao.obtenerFiseFormato14CD(idDet);
			det.setCantCostDirecto(Long.valueOf(bean.getCanDLSupe()));
			det.setCantCostIndirecto(Long.valueOf(bean.getCanILSupe())); 			
			det.setCostoDirecto(new BigDecimal(bean.getCostDLSupe())); 
			det.setCostoIndirecto(new BigDecimal(bean.getCostILSupe()));  
			det.setUsuarioActualizacion(bean.getUsuario()); 
			det.setTerminalActualizacion(bean.getTerminal()); 
			det.setFechaActualizacion(FechaUtil.obtenerFechaActual()); 
			formato14CDDao.actualizarFiseFormato14CD(det);
			
			/**Grabando el primer detalle Rural-Gestor**/			
			idDet = new FiseFormato14CDPK();		
			idDet.setCodEmpresa(bean.getCodEmpresa());
			idDet.setAnoPresentacion(Long.valueOf(bean.getAnioPres())); 
			idDet.setMesPresentacion(Long.valueOf(bean.getMesPres())); 
			idDet.setAnoInicioVigencia(Long.valueOf(bean.getAnioInicioVig())); 
			idDet.setAnoFinVigencia(Long.valueOf(bean.getAnioFinVig())); 	
			idDet.setEtapa(bean.getEtapa()); 
			idDet.setIdTipPersonal(FiseConstants.TIPO_PERSONAL_GESTOR_COD);
			idDet.setIdZonaBenef(FiseConstants.ZONABENEF_RURAL_COD); 		
			det = formato14CDDao.obtenerFiseFormato14CD(idDet);
			det.setCantCostDirecto(Long.valueOf(bean.getCanDRGest()));
			det.setCantCostIndirecto(Long.valueOf(bean.getCanIRGest())); 			
			det.setCostoDirecto(new BigDecimal(bean.getCostDRGest())); 
			det.setCostoIndirecto(new BigDecimal(bean.getCostIRGest()));  
			det.setUsuarioActualizacion(bean.getUsuario()); 
			det.setTerminalActualizacion(bean.getTerminal()); 
			det.setFechaActualizacion(FechaUtil.obtenerFechaActual()); 
			formato14CDDao.actualizarFiseFormato14CD(det);
			
			/**Grabando el primer detalle Urbana Provincias-Gestor**/			
			idDet = new FiseFormato14CDPK();		
			idDet.setCodEmpresa(bean.getCodEmpresa());
			idDet.setAnoPresentacion(Long.valueOf(bean.getAnioPres())); 
			idDet.setMesPresentacion(Long.valueOf(bean.getMesPres())); 
			idDet.setAnoInicioVigencia(Long.valueOf(bean.getAnioInicioVig())); 
			idDet.setAnoFinVigencia(Long.valueOf(bean.getAnioFinVig())); 	
			idDet.setEtapa(bean.getEtapa()); 
			idDet.setIdTipPersonal(FiseConstants.TIPO_PERSONAL_GESTOR_COD);
			idDet.setIdZonaBenef(FiseConstants.ZONABENEF_PROVINCIA_COD); 		
			det = formato14CDDao.obtenerFiseFormato14CD(idDet);
			det.setCantCostDirecto(Long.valueOf(bean.getCanDPGest()));
			det.setCantCostIndirecto(Long.valueOf(bean.getCanIPGest())); 			
			det.setCostoDirecto(new BigDecimal(bean.getCostDPGest())); 
			det.setCostoIndirecto(new BigDecimal(bean.getCostIPGest()));  
			det.setUsuarioActualizacion(bean.getUsuario()); 
			det.setTerminalActualizacion(bean.getTerminal()); 
			det.setFechaActualizacion(FechaUtil.obtenerFechaActual()); 
			formato14CDDao.actualizarFiseFormato14CD(det);
			
			/**Grabando el primer detalle Urbana Lima -Gestor**/			
			idDet = new FiseFormato14CDPK();		
			idDet.setCodEmpresa(bean.getCodEmpresa());
			idDet.setAnoPresentacion(Long.valueOf(bean.getAnioPres())); 
			idDet.setMesPresentacion(Long.valueOf(bean.getMesPres())); 
			idDet.setAnoInicioVigencia(Long.valueOf(bean.getAnioInicioVig())); 
			idDet.setAnoFinVigencia(Long.valueOf(bean.getAnioFinVig())); 	
			idDet.setEtapa(bean.getEtapa()); 
			idDet.setIdTipPersonal(FiseConstants.TIPO_PERSONAL_GESTOR_COD);
			idDet.setIdZonaBenef(FiseConstants.ZONABENEF_LIMA_COD); 		
			det = formato14CDDao.obtenerFiseFormato14CD(idDet);
			det.setCantCostDirecto(Long.valueOf(bean.getCanDLGest()));
			det.setCantCostIndirecto(Long.valueOf(bean.getCanILGest()));			
			det.setCostoDirecto(new BigDecimal(bean.getCostDLGest())); 
			det.setCostoIndirecto(new BigDecimal(bean.getCostILGest()));  
			det.setUsuarioActualizacion(bean.getUsuario()); 
			det.setTerminalActualizacion(bean.getTerminal()); 
			det.setFechaActualizacion(FechaUtil.obtenerFechaActual()); 
			formato14CDDao.actualizarFiseFormato14CD(det);
			
			/**Grabando el primer detalle Rural-Asistente/Auxiliar**/			
			idDet = new FiseFormato14CDPK();		
			idDet.setCodEmpresa(bean.getCodEmpresa());
			idDet.setAnoPresentacion(Long.valueOf(bean.getAnioPres())); 
			idDet.setMesPresentacion(Long.valueOf(bean.getMesPres())); 
			idDet.setAnoInicioVigencia(Long.valueOf(bean.getAnioInicioVig())); 
			idDet.setAnoFinVigencia(Long.valueOf(bean.getAnioFinVig())); 	
			idDet.setEtapa(bean.getEtapa()); 
			idDet.setIdTipPersonal(FiseConstants.TIPO_PERSONAL_ASISTENTE_COD);
			idDet.setIdZonaBenef(FiseConstants.ZONABENEF_RURAL_COD); 		
			det = formato14CDDao.obtenerFiseFormato14CD(idDet);
			det.setCantCostDirecto(Long.valueOf(bean.getCanDRAsist()));
			det.setCantCostIndirecto(Long.valueOf(bean.getCanIRAsist())); 			
			det.setCostoDirecto(new BigDecimal(bean.getCostDRAsist())); 
			det.setCostoIndirecto(new BigDecimal(bean.getCostIRAsist()));  
			det.setUsuarioActualizacion(bean.getUsuario()); 
			det.setTerminalActualizacion(bean.getTerminal()); 
			det.setFechaActualizacion(FechaUtil.obtenerFechaActual()); 
			formato14CDDao.actualizarFiseFormato14CD(det);
			
			/**Grabando el primer detalle Urbano Provincias -Asistente/Auxiliar**/			
			idDet = new FiseFormato14CDPK();		
			idDet.setCodEmpresa(bean.getCodEmpresa());
			idDet.setAnoPresentacion(Long.valueOf(bean.getAnioPres())); 
			idDet.setMesPresentacion(Long.valueOf(bean.getMesPres())); 
			idDet.setAnoInicioVigencia(Long.valueOf(bean.getAnioInicioVig())); 
			idDet.setAnoFinVigencia(Long.valueOf(bean.getAnioFinVig())); 	
			idDet.setEtapa(bean.getEtapa()); 
			idDet.setIdTipPersonal(FiseConstants.TIPO_PERSONAL_ASISTENTE_COD);
			idDet.setIdZonaBenef(FiseConstants.ZONABENEF_PROVINCIA_COD); 		
			det = formato14CDDao.obtenerFiseFormato14CD(idDet);
			det.setCantCostDirecto(Long.valueOf(bean.getCanDPAsist()));
			det.setCantCostIndirecto(Long.valueOf(bean.getCanIPAsist())); 			
			det.setCostoDirecto(new BigDecimal(bean.getCostDPAsist())); 
			det.setCostoIndirecto(new BigDecimal(bean.getCostIPAsist()));  
			det.setUsuarioActualizacion(bean.getUsuario()); 
			det.setTerminalActualizacion(bean.getTerminal()); 
			det.setFechaActualizacion(FechaUtil.obtenerFechaActual()); 
			formato14CDDao.actualizarFiseFormato14CD(det);
			
			/**Grabando el primer detalle Urbana Lima -Asistente/Auxiliar**/			
			idDet = new FiseFormato14CDPK();		
			idDet.setCodEmpresa(bean.getCodEmpresa());
			idDet.setAnoPresentacion(Long.valueOf(bean.getAnioPres())); 
			idDet.setMesPresentacion(Long.valueOf(bean.getMesPres())); 
			idDet.setAnoInicioVigencia(Long.valueOf(bean.getAnioInicioVig())); 
			idDet.setAnoFinVigencia(Long.valueOf(bean.getAnioFinVig())); 	
			idDet.setEtapa(bean.getEtapa()); 
			idDet.setIdTipPersonal(FiseConstants.TIPO_PERSONAL_ASISTENTE_COD);
			idDet.setIdZonaBenef(FiseConstants.ZONABENEF_LIMA_COD); 		
			det = formato14CDDao.obtenerFiseFormato14CD(idDet);
			det.setCantCostDirecto(Long.valueOf(bean.getCanDLAsist()));
			det.setCantCostIndirecto(Long.valueOf(bean.getCanILAsist())); 			 
			det.setCostoDirecto(new BigDecimal(bean.getCostDLAsist())); 
			det.setCostoIndirecto(new BigDecimal(bean.getCostILAsist()));  
			det.setUsuarioActualizacion(bean.getUsuario()); 
			det.setTerminalActualizacion(bean.getTerminal()); 
			det.setFechaActualizacion(FechaUtil.obtenerFechaActual()); 	
			formato14CDDao.actualizarFiseFormato14CD(det);
			
		} catch (Exception e) { 
			valor ="0";
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
		return valor;
	}
	
	@Transactional
	public void eliminarDatosFormato14C(Formato14CBean bean) throws Exception{
		
	}

	@Override
	@Transactional
	public List<FiseFormato14CC> buscarFiseFormato14CC(String codEmpresa,
			long anioDesde, long anioHasta, long mesDesde, long mesHasta,
			String etapa) throws Exception {		
		return formato14CCDao.buscarFiseFormato14CC(codEmpresa, anioDesde, anioHasta, mesDesde, mesHasta, etapa); 
	}
	
	@Override
	@Transactional
	public Formato14CBean buscarFormato14CEditar(String codEmpresa,String anioPres,String mesPres,
			String anioIniVig,String anioFinVig,String etapa) throws Exception{
		
		Formato14CBean bean = new Formato14CBean();
		
		FiseFormato14CCPK id = new FiseFormato14CCPK();
		id.setCodEmpresa(codEmpresa);
		id.setAnoPresentacion(Long.valueOf(anioPres)); 
		id.setMesPresentacion(Long.valueOf(mesPres));
		id.setAnoInicioVigencia(Long.valueOf(anioIniVig)); 
		id.setAnoFinVigencia(Long.valueOf(anioFinVig)); 
		id.setEtapa(etapa); 
		
		FiseFormato14CC f = formato14CCDao.obtenerFormato14CC(id);		
		logger.info("Tamaño de la lista de detalle:  "+f.getFiseFormato14cDs().size()); 
		/**cabecera y pk**/
		bean.setCodEmpresa(f.getId().getCodEmpresa()); 
		bean.setAnioPres(""+f.getId().getAnoPresentacion()); 
		bean.setMesPres(""+f.getId().getMesPresentacion()); 
		bean.setAnioInicioVig(""+f.getId().getAnoInicioVigencia()); 
		bean.setAnioFinVig(""+f.getId().getAnoFinVigencia()); 
		bean.setEtapa(f.getId().getEtapa());  
		///////////////////////////////////////
		bean.setNombreSede(f.getNombreSede()); 
		bean.setNumRural(""+f.getNumBenefEmpPerAntRural());
		bean.setNumUrbProv(""+f.getNumBenefEmpPerAntUrbProv());
		bean.setNumUrbLima(""+f.getNumBenefEmpPerAntUrbLima()); 
		bean.setCostoPromRural(""+f.getCostoPromMenRural());
		bean.setCostoPromUrbProv(""+f.getCostoPromMenUrbProv()); 
		bean.setCostoPromUrbLima(""+f.getCostoPromMenUrbLima()); 
		/**detalle**/
		List<FiseFormato14CD> listDetalle = f.getFiseFormato14cDs();
		for(FiseFormato14CD d:listDetalle ){
		  /*Tipo de personal Coordinador*/
		  if(FiseConstants.ZONABENEF_RURAL_COD==d.getId().getIdZonaBenef() &&
				  FiseConstants.TIPO_PERSONAL_CORDINADOR_COD==d.getId().getIdTipPersonal()){
			  bean.setCanDRCoord(""+d.getCantCostDirecto());
			  bean.setCostDRCoord(""+d.getCostoDirecto());
			  bean.setCanIRCoord(""+d.getCantCostIndirecto());
			  bean.setCostIRCoord(""+d.getCostoIndirecto()); 
		  }
		  if(FiseConstants.ZONABENEF_PROVINCIA_COD==d.getId().getIdZonaBenef() &&
				  FiseConstants.TIPO_PERSONAL_CORDINADOR_COD==d.getId().getIdTipPersonal()){
			  bean.setCanDPCoord(""+d.getCantCostDirecto());
			  bean.setCostDPCoord(""+d.getCostoDirecto());
			  bean.setCanIPCoord(""+d.getCantCostIndirecto());
			  bean.setCostIPCoord(""+d.getCostoIndirecto()); 
		  }
		  if(FiseConstants.ZONABENEF_LIMA_COD==d.getId().getIdZonaBenef() &&
				  FiseConstants.TIPO_PERSONAL_CORDINADOR_COD==d.getId().getIdTipPersonal()){
			  bean.setCanDLCoord(""+d.getCantCostDirecto());
			  bean.setCostDLCoord(""+d.getCostoDirecto());
			  bean.setCanILCoord(""+d.getCantCostIndirecto());
			  bean.setCostILCoord(""+d.getCostoIndirecto()); 
		  }
		  /*Tipo de personal Supervisor*/
		  if(FiseConstants.ZONABENEF_RURAL_COD==d.getId().getIdZonaBenef() &&
				  FiseConstants.TIPO_PERSONAL_SUPERVISOR_COD==d.getId().getIdTipPersonal()){			
			  bean.setCanDRSupe(""+d.getCantCostDirecto());
			  bean.setCostDRSupe(""+d.getCostoDirecto());
			  bean.setCanIRSupe(""+d.getCantCostIndirecto());
			  bean.setCostIRSupe(""+d.getCostoIndirecto()); 
		  }
		  if(FiseConstants.ZONABENEF_PROVINCIA_COD==d.getId().getIdZonaBenef() &&
				  FiseConstants.TIPO_PERSONAL_SUPERVISOR_COD==d.getId().getIdTipPersonal()){
			  bean.setCanDPSupe(""+d.getCantCostDirecto());
			  bean.setCostDPSupe(""+d.getCostoDirecto());
			  bean.setCanIPSupe(""+d.getCantCostIndirecto());
			  bean.setCostIPSupe(""+d.getCostoIndirecto()); 
		  }
		  if(FiseConstants.ZONABENEF_LIMA_COD==d.getId().getIdZonaBenef() &&
				  FiseConstants.TIPO_PERSONAL_SUPERVISOR_COD==d.getId().getIdTipPersonal()){
			  bean.setCanDLSupe(""+d.getCantCostDirecto());
			  bean.setCostDLSupe(""+d.getCostoDirecto());
			  bean.setCanILSupe(""+d.getCantCostIndirecto());
			  bean.setCostILSupe(""+d.getCostoIndirecto()); 
		  }
		  /*Tipo de personal Gestor*/
		  if(FiseConstants.ZONABENEF_RURAL_COD==d.getId().getIdZonaBenef() &&
				  FiseConstants.TIPO_PERSONAL_GESTOR_COD==d.getId().getIdTipPersonal()){			
			  bean.setCanDRGest(""+d.getCantCostDirecto());
			  bean.setCostDRGest(""+d.getCostoDirecto());
			  bean.setCanIRGest(""+d.getCantCostIndirecto());
			  bean.setCostIRGest(""+d.getCostoIndirecto()); 
		  }
		  if(FiseConstants.ZONABENEF_PROVINCIA_COD==d.getId().getIdZonaBenef() &&
				  FiseConstants.TIPO_PERSONAL_GESTOR_COD==d.getId().getIdTipPersonal()){
			  bean.setCanDPGest(""+d.getCantCostDirecto());
			  bean.setCostDPGest(""+d.getCostoDirecto());
			  bean.setCanIPGest(""+d.getCantCostIndirecto());
			  bean.setCostIPGest(""+d.getCostoIndirecto()); 
		  }
		  if(FiseConstants.ZONABENEF_LIMA_COD==d.getId().getIdZonaBenef() &&
				  FiseConstants.TIPO_PERSONAL_GESTOR_COD==d.getId().getIdTipPersonal()){
			  bean.setCanDLGest(""+d.getCantCostDirecto());
			  bean.setCostDLGest(""+d.getCostoDirecto());
			  bean.setCanILGest(""+d.getCantCostIndirecto());
			  bean.setCostILGest(""+d.getCostoIndirecto()); 
		  }	
		  /*Tipo de personal Asistente*/
		  if(FiseConstants.ZONABENEF_RURAL_COD==d.getId().getIdZonaBenef() &&
				  FiseConstants.TIPO_PERSONAL_ASISTENTE_COD==d.getId().getIdTipPersonal()){			
			  bean.setCanDRAsist(""+d.getCantCostDirecto());
			  bean.setCostDRAsist(""+d.getCostoDirecto());
			  bean.setCanIRAsist(""+d.getCantCostIndirecto());
			  bean.setCostIRAsist(""+d.getCostoIndirecto()); 
		  }
		  if(FiseConstants.ZONABENEF_PROVINCIA_COD==d.getId().getIdZonaBenef() &&
				  FiseConstants.TIPO_PERSONAL_ASISTENTE_COD==d.getId().getIdTipPersonal()){
			  bean.setCanDPAsist(""+d.getCantCostDirecto());
			  bean.setCostDPAsist(""+d.getCostoDirecto());
			  bean.setCanIPAsist(""+d.getCantCostIndirecto());
			  bean.setCostIPAsist(""+d.getCostoIndirecto()); 
		  }
		  if(FiseConstants.ZONABENEF_LIMA_COD==d.getId().getIdZonaBenef() &&
				  FiseConstants.TIPO_PERSONAL_ASISTENTE_COD==d.getId().getIdTipPersonal()){
			  bean.setCanDLAsist(""+d.getCantCostDirecto());
			  bean.setCostDLAsist(""+d.getCostoDirecto());
			  bean.setCanILAsist(""+d.getCantCostIndirecto());
			  bean.setCostILAsist(""+d.getCostoIndirecto()); 
		  }				
		}//fin del for	
		return bean;
	}
	

}
