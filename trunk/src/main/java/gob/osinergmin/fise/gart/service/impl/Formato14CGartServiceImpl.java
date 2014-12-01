package gob.osinergmin.fise.gart.service.impl;

import gob.osinergmin.fise.bean.Formato14CBean;
import gob.osinergmin.fise.bean.Formato14CReportBean;
import gob.osinergmin.fise.constant.FiseConstants;
import gob.osinergmin.fise.dao.CommonDao;
import gob.osinergmin.fise.dao.FiseGrupoInformacionDao;
import gob.osinergmin.fise.dao.FiseTipPersonalDao;
import gob.osinergmin.fise.dao.FiseZonaBenefDao;
import gob.osinergmin.fise.dao.Formato14CCDao;
import gob.osinergmin.fise.dao.Formato14CDDao;
import gob.osinergmin.fise.dao.Formato14CDObDao;
import gob.osinergmin.fise.domain.FiseFormato14CC;
import gob.osinergmin.fise.domain.FiseFormato14CCPK;
import gob.osinergmin.fise.domain.FiseFormato14CD;
import gob.osinergmin.fise.domain.FiseFormato14CDOb;
import gob.osinergmin.fise.domain.FiseFormato14CDObPK;
import gob.osinergmin.fise.domain.FiseFormato14CDPK;
import gob.osinergmin.fise.domain.FiseGrupoInformacion;
import gob.osinergmin.fise.gart.service.Formato14CGartService;
import gob.osinergmin.fise.util.FechaUtil;
import gob.osinergmin.fise.util.FormatoUtil;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.HashMap;
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
	
	
	@Autowired
	@Qualifier("formato14CDObDaoImpl")
	private Formato14CDObDao formato14CDObDao;
	
	@Autowired
	@Qualifier("commonDaoImpl")
	private CommonDao commonDao;
	
	
	
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
	
	private boolean validarDato(String cadena){
		if(FormatoUtil.isNotBlank(cadena)){
			return true;
		}else{
			return false;
		}
	}
	/**Metodo para insertar Cabecera y Detalle del formato 14C*/
	@Override
	@Transactional(propagation = Propagation.REQUIRED, rollbackFor = Exception.class)	
	public String insertarDatosFormato14C(Formato14CBean bean) throws Exception{
		FiseFormato14CC cab = null;
		FiseFormato14CCPK idCab = null;
		FiseFormato14CD det = null;
		FiseFormato14CDPK idDet = null;
		FiseGrupoInformacion inf =null;
		String valor = "1";
		try {
		  /***Grabando en la cabecera*/			
			idCab = new FiseFormato14CCPK();
			//PK
			idCab.setCodEmpresa(bean.getCodEmpresa());
			idCab.setAnoPresentacion(Long.valueOf(bean.getAnioPres())); 
			idCab.setMesPresentacion(Long.valueOf(bean.getMesPres())); 
			idCab.setAnoInicioVigencia(Long.valueOf(bean.getAnoIniVigencia())); 
			idCab.setAnoFinVigencia(Long.valueOf(bean.getAnoFinVigencia())); 
			idCab.setEtapa(bean.getEtapa()); 
			//FIN PK
			/**verificamos que el registro no este en la base de datos caso contrario no insertamos**/
			cab = formato14CCDao.obtenerFormato14CC(idCab); 
			if(cab==null){				
				cab = new FiseFormato14CC();
				cab.setId(idCab);
				cab.setNombreSede(bean.getNombreSede());
				logger.info("Obtneniendo el id Grupo informacion: "); 
				long idGrupoInf = commonDao.obtenerIdGrupoInformacion(Long.valueOf(bean.getAnioPres()),
						Long.valueOf(bean.getMesPres()), FiseConstants.FRECUENCIA_BIENAL_DESCRIPCION); 
				logger.info("El id Grupo informacion es  : " +idGrupoInf); 
				if(idGrupoInf!=0){
					inf = fiseGrupoInformacionDao.obtenerFiseGrupoInformacionByPK(idGrupoInf);	
					logger.info("Grupo de infomacion:  "+inf); 					
				}	
				cab.setFiseGrupoInformacion(inf);  				
				cab.setNombreArchivoExcel(bean.getNombreExel());
				cab.setNombreArchivoTexto(bean.getNombreText());								
                if(validarDato(bean.getNumRural())){
                	cab.setNumBenefEmpPerAntRural(Long.valueOf(bean.getNumRural()));	
				}			
				if(validarDato(bean.getNumUrbProv())){
					cab.setNumBenefEmpPerAntUrbProv(Long.valueOf(bean.getNumUrbProv()));	
				}				
				if(validarDato(bean.getNumUrbLima())){
					cab.setNumBenefEmpPerAntUrbLima(Long.valueOf(bean.getNumUrbLima()));	
				}				
				if(validarDato(bean.getCostoPromUrbLima())){
					cab.setCostoPromMenUrbLima(new BigDecimal(bean.getCostoPromUrbLima())); 	
				}				
				if(validarDato(bean.getCostoPromRural())){
					cab.setCostoPromMenRural(new BigDecimal(bean.getCostoPromRural()));	
				}				
				if(validarDato(bean.getCostoPromUrbProv())){
					cab.setCostoPromMenUrbProv(new BigDecimal(bean.getCostoPromUrbProv()));	
				}				
				cab.setUsuarioCreacion(bean.getUsuario());
				cab.setTerminalCreacion(bean.getTerminal());
				cab.setFechaCreacion(FechaUtil.obtenerFechaActual());	
				formato14CCDao.insertarFiseFormato14CC(cab);				
				logger.info("GRABO EN LA CABECERA OK");		
				
				/***Grabando en el detalle de la cabecera*/				
	            
	            if(FormatoUtil.isNotBlank(bean.getFlagCosto()) && 
	            		FiseConstants.COSTO_DIRECTO_F14C.equals(bean.getFlagCosto())){
	            	/****COSTO DIRECTO****/
	            	/**Grabando el primer detalle Rural-Coordinador**/
	            	det = new  FiseFormato14CD();				
					idDet = new FiseFormato14CDPK();		
					idDet.setCodEmpresa(bean.getCodEmpresa());
					idDet.setAnoPresentacion(Long.valueOf(bean.getAnioPres())); 
					idDet.setMesPresentacion(Long.valueOf(bean.getMesPres())); 
					idDet.setAnoInicioVigencia(Long.valueOf(bean.getAnoIniVigencia())); 
					idDet.setAnoFinVigencia(Long.valueOf(bean.getAnoFinVigencia())); 
					idDet.setEtapa(bean.getEtapa()); 
					idDet.setIdTipPersonal(FiseConstants.TIPO_PERSONAL_CORDINADOR_COD);
					idDet.setIdZonaBenef(FiseConstants.ZONABENEF_RURAL_COD);
		            det.setId(idDet);
		            if(validarDato(bean.getCanDRCoord())){
		            	det.setCantCostDirecto(Long.valueOf(valorCantidad(bean.getCanDRCoord())));		
					}	            	
	            	if(validarDato(bean.getCostDRCoord())){
	            		det.setCostoDirecto(new BigDecimal(bean.getCostDRCoord())); 		
					}									
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
					idDet.setAnoInicioVigencia(Long.valueOf(bean.getAnoIniVigencia())); 
					idDet.setAnoFinVigencia(Long.valueOf(bean.getAnoFinVigencia())); 	
					idDet.setEtapa(bean.getEtapa()); 
					idDet.setIdTipPersonal(FiseConstants.TIPO_PERSONAL_SUPERVISOR_COD);
					idDet.setIdZonaBenef(FiseConstants.ZONABENEF_RURAL_COD); 
					det.setId(idDet); 	
					if(validarDato(bean.getCanDRSupe())){
						det.setCantCostDirecto(Long.valueOf(valorCantidad(bean.getCanDRSupe())));	
					}					
					if(validarDato(bean.getCostDRSupe())){
						det.setCostoDirecto(new BigDecimal(bean.getCostDRSupe())); 	
					}									
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
					idDet.setAnoInicioVigencia(Long.valueOf(bean.getAnoIniVigencia())); 
					idDet.setAnoFinVigencia(Long.valueOf(bean.getAnoFinVigencia())); 	
					idDet.setEtapa(bean.getEtapa()); 
					idDet.setIdTipPersonal(FiseConstants.TIPO_PERSONAL_GESTOR_COD);
					idDet.setIdZonaBenef(FiseConstants.ZONABENEF_RURAL_COD);
					det.setId(idDet); 
					if(validarDato(bean.getCanDRGest())){
						det.setCantCostDirecto(Long.valueOf(valorCantidad(bean.getCanDRGest())));
					}					
					if(validarDato(bean.getCostDRGest())){
						det.setCostoDirecto(new BigDecimal(bean.getCostDRGest()));			
					}							
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
					idDet.setAnoInicioVigencia(Long.valueOf(bean.getAnoIniVigencia())); 
					idDet.setAnoFinVigencia(Long.valueOf(bean.getAnoFinVigencia())); 	
					idDet.setEtapa(bean.getEtapa()); 
					idDet.setIdTipPersonal(FiseConstants.TIPO_PERSONAL_ASISTENTE_COD);
					idDet.setIdZonaBenef(FiseConstants.ZONABENEF_RURAL_COD); 
					det.setId(idDet); 
					if(validarDato(bean.getCanDRAsist())){
						det.setCantCostDirecto(Long.valueOf(valorCantidad(bean.getCanDRAsist())));	
					}					
					if(validarDato(bean.getCostDRAsist())){
						det.setCostoDirecto(new BigDecimal(bean.getCostDRAsist())); 				
					}							
					det.setUsuarioCreacion(bean.getUsuario());
					det.setTerminalCreacion(bean.getTerminal());
					det.setFechaCreacion(FechaUtil.obtenerFechaActual());	
					formato14CDDao.insertarFiseFormato14CD(det);
					/****PROVINCIA****/				
					/**Grabando el primer detalle Urbano Provincia-Coordinador**/
					det = new  FiseFormato14CD();
					idDet = new FiseFormato14CDPK();		
					idDet.setCodEmpresa(bean.getCodEmpresa());
					idDet.setAnoPresentacion(Long.valueOf(bean.getAnioPres())); 
					idDet.setMesPresentacion(Long.valueOf(bean.getMesPres()));
					idDet.setAnoInicioVigencia(Long.valueOf(bean.getAnoIniVigencia())); 
					idDet.setAnoFinVigencia(Long.valueOf(bean.getAnoFinVigencia())); 		
					idDet.setEtapa(bean.getEtapa()); 
					idDet.setIdTipPersonal(FiseConstants.TIPO_PERSONAL_CORDINADOR_COD);
					idDet.setIdZonaBenef(FiseConstants.ZONABENEF_PROVINCIA_COD); 
					det.setId(idDet);	
					if(validarDato(bean.getCanDPCoord())){
						det.setCantCostDirecto(Long.valueOf(valorCantidad(bean.getCanDPCoord())));		
					}					
					if(validarDato(bean.getCostDPCoord())){
						det.setCostoDirecto(new BigDecimal(bean.getCostDPCoord())); 	
					}										
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
					idDet.setAnoInicioVigencia(Long.valueOf(bean.getAnoIniVigencia())); 
					idDet.setAnoFinVigencia(Long.valueOf(bean.getAnoFinVigencia())); 	
					idDet.setEtapa(bean.getEtapa()); 
					idDet.setIdTipPersonal(FiseConstants.TIPO_PERSONAL_SUPERVISOR_COD);
					idDet.setIdZonaBenef(FiseConstants.ZONABENEF_PROVINCIA_COD); 
					det.setId(idDet);
					if(validarDato(bean.getCanDPSupe())){
						det.setCantCostDirecto(Long.valueOf(valorCantidad(bean.getCanDPSupe())));	
					}					
					if(validarDato(bean.getCostDPSupe())){
						det.setCostoDirecto(new BigDecimal(bean.getCostDPSupe())); 	
					}									
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
					idDet.setAnoInicioVigencia(Long.valueOf(bean.getAnoIniVigencia())); 
					idDet.setAnoFinVigencia(Long.valueOf(bean.getAnoFinVigencia())); 	
					idDet.setEtapa(bean.getEtapa()); 
					idDet.setIdTipPersonal(FiseConstants.TIPO_PERSONAL_GESTOR_COD);
					idDet.setIdZonaBenef(FiseConstants.ZONABENEF_PROVINCIA_COD); 
					det.setId(idDet); 	
					if(validarDato(bean.getCanDPGest())){
						det.setCantCostDirecto(Long.valueOf(valorCantidad(bean.getCanDPGest())));		
					}					
					if(validarDato(bean.getCostDPGest())){
						det.setCostoDirecto(new BigDecimal(bean.getCostDPGest())); 		
					}								
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
					idDet.setAnoInicioVigencia(Long.valueOf(bean.getAnoIniVigencia())); 
					idDet.setAnoFinVigencia(Long.valueOf(bean.getAnoFinVigencia())); 	
					idDet.setEtapa(bean.getEtapa()); 
					idDet.setIdTipPersonal(FiseConstants.TIPO_PERSONAL_ASISTENTE_COD);
					idDet.setIdZonaBenef(FiseConstants.ZONABENEF_PROVINCIA_COD);
					det.setId(idDet); 
					if(validarDato(bean.getCanDPAsist())){
						det.setCantCostDirecto(Long.valueOf(valorCantidad(bean.getCanDPAsist())));		
					}					
					if(validarDato(bean.getCostDPAsist())){
						det.setCostoDirecto(new BigDecimal(bean.getCostDPAsist())); 	
					}									
					det.setUsuarioCreacion(bean.getUsuario());
					det.setTerminalCreacion(bean.getTerminal());
					det.setFechaCreacion(FechaUtil.obtenerFechaActual());	
					formato14CDDao.insertarFiseFormato14CD(det);
					
					/****LIMA****/
					if(FiseConstants.COD_EMPRESA_EDELNOR.equals(bean.getCodEmpresa()) || 
							FiseConstants.COD_EMPRESA_LUZ_SUR.equals(bean.getCodEmpresa()))
					{
						/**Grabando el primer detalle Urbano Lima-Coordinador**/
						det = new  FiseFormato14CD();
						idDet = new FiseFormato14CDPK();		
						idDet.setCodEmpresa(bean.getCodEmpresa());
						idDet.setAnoPresentacion(Long.valueOf(bean.getAnioPres())); 
						idDet.setMesPresentacion(Long.valueOf(bean.getMesPres())); 
						idDet.setAnoInicioVigencia(Long.valueOf(bean.getAnoIniVigencia())); 
						idDet.setAnoFinVigencia(Long.valueOf(bean.getAnoFinVigencia())); 	
						idDet.setEtapa(bean.getEtapa()); 
						idDet.setIdTipPersonal(FiseConstants.TIPO_PERSONAL_CORDINADOR_COD);
						idDet.setIdZonaBenef(FiseConstants.ZONABENEF_LIMA_COD); 
						det.setId(idDet); 	
						if(validarDato(bean.getCanDLCoord())){
							det.setCantCostDirecto(Long.valueOf(valorCantidad(bean.getCanDLCoord())));	
						}						
						if(validarDato(bean.getCostDLCoord())){
							det.setCostoDirecto(new BigDecimal(bean.getCostDLCoord())); 		
						}										
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
						idDet.setAnoInicioVigencia(Long.valueOf(bean.getAnoIniVigencia())); 
						idDet.setAnoFinVigencia(Long.valueOf(bean.getAnoFinVigencia())); 	
						idDet.setEtapa(bean.getEtapa()); 
						idDet.setIdTipPersonal(FiseConstants.TIPO_PERSONAL_SUPERVISOR_COD);
						idDet.setIdZonaBenef(FiseConstants.ZONABENEF_LIMA_COD);
						det.setId(idDet); 	
						if(validarDato(bean.getCanDLSupe())){
							det.setCantCostDirecto(Long.valueOf(valorCantidad(bean.getCanDLSupe())));	
						}						
						if(validarDato(bean.getCostDLSupe())){
							det.setCostoDirecto(new BigDecimal(bean.getCostDLSupe())); 		
						}										
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
						idDet.setAnoInicioVigencia(Long.valueOf(bean.getAnoIniVigencia())); 
						idDet.setAnoFinVigencia(Long.valueOf(bean.getAnoFinVigencia())); 	
						idDet.setEtapa(bean.getEtapa()); 
						idDet.setIdTipPersonal(FiseConstants.TIPO_PERSONAL_GESTOR_COD);
						idDet.setIdZonaBenef(FiseConstants.ZONABENEF_LIMA_COD); 
						det.setId(idDet); 	
						if(validarDato(bean.getCanDLGest())){
							det.setCantCostDirecto(Long.valueOf(valorCantidad(bean.getCanDLGest())));	
						}						
						if(validarDato(bean.getCostDLGest())){
							det.setCostoDirecto(new BigDecimal(bean.getCostDLGest()));				
						}								
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
						idDet.setAnoInicioVigencia(Long.valueOf(bean.getAnoIniVigencia())); 
						idDet.setAnoFinVigencia(Long.valueOf(bean.getAnoFinVigencia())); 	
						idDet.setEtapa(bean.getEtapa()); 
						idDet.setIdTipPersonal(FiseConstants.TIPO_PERSONAL_ASISTENTE_COD);
						idDet.setIdZonaBenef(FiseConstants.ZONABENEF_LIMA_COD);
						det.setId(idDet); 
						if(validarDato(bean.getCanDLAsist())){
							det.setCantCostDirecto(Long.valueOf(valorCantidad(bean.getCanDLAsist())));	
						}						
						if(validarDato(bean.getCostDLAsist())){
							det.setCostoDirecto(new BigDecimal(bean.getCostDLAsist())); 			
						}									
						det.setUsuarioCreacion(bean.getUsuario());
						det.setTerminalCreacion(bean.getTerminal());
						det.setFechaCreacion(FechaUtil.obtenerFechaActual());	
						formato14CDDao.insertarFiseFormato14CD(det);     
						
					}//	fin de  empresa							       	
	            	
	            }else if(FormatoUtil.isNotBlank(bean.getFlagCosto()) && 
	            		FiseConstants.COSTO_INDIRECTO_F14C.equals(bean.getFlagCosto())){
	            	/*****COSTO INDIRECTO*****/
	            	/**Grabando el primer detalle Rural-Coordinador**/
	            	det = new  FiseFormato14CD();				
					idDet = new FiseFormato14CDPK();		
					idDet.setCodEmpresa(bean.getCodEmpresa());
					idDet.setAnoPresentacion(Long.valueOf(bean.getAnioPres())); 
					idDet.setMesPresentacion(Long.valueOf(bean.getMesPres())); 
					idDet.setAnoInicioVigencia(Long.valueOf(bean.getAnoIniVigencia())); 
					idDet.setAnoFinVigencia(Long.valueOf(bean.getAnoFinVigencia())); 
					idDet.setEtapa(bean.getEtapa()); 
					idDet.setIdTipPersonal(FiseConstants.TIPO_PERSONAL_CORDINADOR_COD);
					idDet.setIdZonaBenef(FiseConstants.ZONABENEF_RURAL_COD);
		            det.setId(idDet); 	
		            if(validarDato(bean.getCanIRCoord())){
		            	det.setCantCostIndirecto(Long.valueOf(valorCantidad(bean.getCanIRCoord()))); 	
					}					
					if(validarDato(bean.getCostIRCoord())){
						det.setCostoIndirecto(new BigDecimal(bean.getCostIRCoord()));  	
					}					
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
					idDet.setAnoInicioVigencia(Long.valueOf(bean.getAnoIniVigencia())); 
					idDet.setAnoFinVigencia(Long.valueOf(bean.getAnoFinVigencia())); 	
					idDet.setEtapa(bean.getEtapa()); 
					idDet.setIdTipPersonal(FiseConstants.TIPO_PERSONAL_SUPERVISOR_COD);
					idDet.setIdZonaBenef(FiseConstants.ZONABENEF_RURAL_COD); 
					det.setId(idDet); 	
					if(validarDato(bean.getCanIRSupe())){
						det.setCantCostIndirecto(Long.valueOf(valorCantidad(bean.getCanIRSupe()))); 	
					}					
					if(validarDato(bean.getCostIRSupe())){
						det.setCostoIndirecto(new BigDecimal(bean.getCostIRSupe()));  	
					}					
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
					idDet.setAnoInicioVigencia(Long.valueOf(bean.getAnoIniVigencia())); 
					idDet.setAnoFinVigencia(Long.valueOf(bean.getAnoFinVigencia())); 	
					idDet.setEtapa(bean.getEtapa()); 
					idDet.setIdTipPersonal(FiseConstants.TIPO_PERSONAL_GESTOR_COD);
					idDet.setIdZonaBenef(FiseConstants.ZONABENEF_RURAL_COD);
					det.setId(idDet);
					if(validarDato(bean.getCanIRGest())){
						det.setCantCostIndirecto(Long.valueOf(valorCantidad(bean.getCanIRGest()))); 	
					}					
					if(validarDato(bean.getCostIRGest())){
						det.setCostoIndirecto(new BigDecimal(bean.getCostIRGest()));  	
					}					
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
					idDet.setAnoInicioVigencia(Long.valueOf(bean.getAnoIniVigencia())); 
					idDet.setAnoFinVigencia(Long.valueOf(bean.getAnoFinVigencia())); 	
					idDet.setEtapa(bean.getEtapa()); 
					idDet.setIdTipPersonal(FiseConstants.TIPO_PERSONAL_ASISTENTE_COD);
					idDet.setIdZonaBenef(FiseConstants.ZONABENEF_RURAL_COD); 
					det.setId(idDet); 
					if(validarDato(bean.getCanIRAsist())){
						det.setCantCostIndirecto(Long.valueOf(valorCantidad(bean.getCanIRAsist()))); 
					}					
					if(validarDato(bean.getCostIRAsist())){
						det.setCostoIndirecto(new BigDecimal(bean.getCostIRAsist()));  	
					}					
					det.setUsuarioCreacion(bean.getUsuario());
					det.setTerminalCreacion(bean.getTerminal());
					det.setFechaCreacion(FechaUtil.obtenerFechaActual());	
					formato14CDDao.insertarFiseFormato14CD(det);
					/*****PROVINCIA*****/				
					/**Grabando el primer detalle Urbano Provincia-Coordinador**/
					det = new  FiseFormato14CD();
					idDet = new FiseFormato14CDPK();		
					idDet.setCodEmpresa(bean.getCodEmpresa());
					idDet.setAnoPresentacion(Long.valueOf(bean.getAnioPres())); 
					idDet.setMesPresentacion(Long.valueOf(bean.getMesPres()));
					idDet.setAnoInicioVigencia(Long.valueOf(bean.getAnoIniVigencia())); 
					idDet.setAnoFinVigencia(Long.valueOf(bean.getAnoFinVigencia())); 		
					idDet.setEtapa(bean.getEtapa()); 
					idDet.setIdTipPersonal(FiseConstants.TIPO_PERSONAL_CORDINADOR_COD);
					idDet.setIdZonaBenef(FiseConstants.ZONABENEF_PROVINCIA_COD); 
					det.setId(idDet); 
					if(validarDato(bean.getCanIPCoord())){
						det.setCantCostIndirecto(Long.valueOf(valorCantidad(bean.getCanIPCoord())));	
					}					
					if(validarDato(bean.getCostIPCoord())){
						det.setCostoIndirecto(new BigDecimal(bean.getCostIPCoord()));  	
					}					
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
					idDet.setAnoInicioVigencia(Long.valueOf(bean.getAnoIniVigencia())); 
					idDet.setAnoFinVigencia(Long.valueOf(bean.getAnoFinVigencia())); 	
					idDet.setEtapa(bean.getEtapa()); 
					idDet.setIdTipPersonal(FiseConstants.TIPO_PERSONAL_SUPERVISOR_COD);
					idDet.setIdZonaBenef(FiseConstants.ZONABENEF_PROVINCIA_COD); 
					det.setId(idDet); 
					if(validarDato(bean.getCanIPSupe())){
						det.setCantCostIndirecto(Long.valueOf(valorCantidad(bean.getCanIPSupe())));	
					}					
					if(validarDato(bean.getCostIPSupe())){
						det.setCostoIndirecto(new BigDecimal(bean.getCostIPSupe()));  
					}					
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
					idDet.setAnoInicioVigencia(Long.valueOf(bean.getAnoIniVigencia())); 
					idDet.setAnoFinVigencia(Long.valueOf(bean.getAnoFinVigencia())); 	
					idDet.setEtapa(bean.getEtapa()); 
					idDet.setIdTipPersonal(FiseConstants.TIPO_PERSONAL_GESTOR_COD);
					idDet.setIdZonaBenef(FiseConstants.ZONABENEF_PROVINCIA_COD); 
					det.setId(idDet); 	
					if(validarDato(bean.getCanIPGest())){
						det.setCantCostIndirecto(Long.valueOf(valorCantidad(bean.getCanIPGest())));
					}					
					if(validarDato(bean.getCostIPGest())){
						det.setCostoIndirecto(new BigDecimal(bean.getCostIPGest()));  
					}					
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
					idDet.setAnoInicioVigencia(Long.valueOf(bean.getAnoIniVigencia())); 
					idDet.setAnoFinVigencia(Long.valueOf(bean.getAnoFinVigencia())); 	
					idDet.setEtapa(bean.getEtapa()); 
					idDet.setIdTipPersonal(FiseConstants.TIPO_PERSONAL_ASISTENTE_COD);
					idDet.setIdZonaBenef(FiseConstants.ZONABENEF_PROVINCIA_COD);
					det.setId(idDet); 
					if(validarDato(bean.getCanIPAsist())){
						det.setCantCostIndirecto(Long.valueOf(valorCantidad(bean.getCanIPAsist())));
					}					
					if(validarDato(bean.getCostIPAsist())){
						det.setCostoIndirecto(new BigDecimal(bean.getCostIPAsist()));  	
					}					
					det.setUsuarioCreacion(bean.getUsuario());
					det.setTerminalCreacion(bean.getTerminal());
					det.setFechaCreacion(FechaUtil.obtenerFechaActual());	
					formato14CDDao.insertarFiseFormato14CD(det);
					
					/********LIMA********/					
					if(FiseConstants.COD_EMPRESA_EDELNOR.equals(bean.getCodEmpresa()) || 
							FiseConstants.COD_EMPRESA_LUZ_SUR.equals(bean.getCodEmpresa()))
					{
						/**Grabando el primer detalle Urbano Lima-Coordinador**/
						det = new  FiseFormato14CD();
						idDet = new FiseFormato14CDPK();		
						idDet.setCodEmpresa(bean.getCodEmpresa());
						idDet.setAnoPresentacion(Long.valueOf(bean.getAnioPres())); 
						idDet.setMesPresentacion(Long.valueOf(bean.getMesPres())); 
						idDet.setAnoInicioVigencia(Long.valueOf(bean.getAnoIniVigencia())); 
						idDet.setAnoFinVigencia(Long.valueOf(bean.getAnoFinVigencia())); 	
						idDet.setEtapa(bean.getEtapa()); 
						idDet.setIdTipPersonal(FiseConstants.TIPO_PERSONAL_CORDINADOR_COD);
						idDet.setIdZonaBenef(FiseConstants.ZONABENEF_LIMA_COD); 
						det.setId(idDet); 
						if(validarDato(bean.getCanILCoord())){
							det.setCantCostIndirecto(Long.valueOf(valorCantidad(bean.getCanILCoord())));	
						}						
						if(validarDato(bean.getCostILCoord())){
							det.setCostoIndirecto(new BigDecimal(bean.getCostILCoord()));  	
						}						
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
						idDet.setAnoInicioVigencia(Long.valueOf(bean.getAnoIniVigencia())); 
						idDet.setAnoFinVigencia(Long.valueOf(bean.getAnoFinVigencia())); 	
						idDet.setEtapa(bean.getEtapa()); 
						idDet.setIdTipPersonal(FiseConstants.TIPO_PERSONAL_SUPERVISOR_COD);
						idDet.setIdZonaBenef(FiseConstants.ZONABENEF_LIMA_COD);
						det.setId(idDet); 
						if(validarDato(bean.getCanILSupe())){
							det.setCantCostIndirecto(Long.valueOf(valorCantidad(bean.getCanILSupe())));	
						}						
						if(validarDato(bean.getCostILSupe())){
							det.setCostoIndirecto(new BigDecimal(bean.getCostILSupe()));  	
						}						
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
						idDet.setAnoInicioVigencia(Long.valueOf(bean.getAnoIniVigencia())); 
						idDet.setAnoFinVigencia(Long.valueOf(bean.getAnoFinVigencia())); 	
						idDet.setEtapa(bean.getEtapa()); 
						idDet.setIdTipPersonal(FiseConstants.TIPO_PERSONAL_GESTOR_COD);
						idDet.setIdZonaBenef(FiseConstants.ZONABENEF_LIMA_COD); 
						det.setId(idDet); 
						if(validarDato(bean.getCanILGest())){
							det.setCantCostIndirecto(Long.valueOf(valorCantidad(bean.getCanILGest()))); 	
						}						
						if(validarDato(bean.getCostILGest())){
							det.setCostoIndirecto(new BigDecimal(bean.getCostILGest()));  	
						}						
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
						idDet.setAnoInicioVigencia(Long.valueOf(bean.getAnoIniVigencia())); 
						idDet.setAnoFinVigencia(Long.valueOf(bean.getAnoFinVigencia())); 	
						idDet.setEtapa(bean.getEtapa()); 
						idDet.setIdTipPersonal(FiseConstants.TIPO_PERSONAL_ASISTENTE_COD);
						idDet.setIdZonaBenef(FiseConstants.ZONABENEF_LIMA_COD);
						det.setId(idDet); 	
						if(validarDato(bean.getCanILAsist())){
							det.setCantCostIndirecto(Long.valueOf(valorCantidad(bean.getCanILAsist())));	
						}						
						if(validarDato(bean.getCostILAsist())){
							det.setCostoIndirecto(new BigDecimal(bean.getCostILAsist()));  	
						}						
						det.setUsuarioCreacion(bean.getUsuario());
						det.setTerminalCreacion(bean.getTerminal());
						det.setFechaCreacion(FechaUtil.obtenerFechaActual());	
						formato14CDDao.insertarFiseFormato14CD(det); 
					}//fin de empresa															      	
	            	
	            }else if(FormatoUtil.isNotBlank(bean.getFlagCosto()) && 
	            		FiseConstants.COSTO_D_I_F14C.equals(bean.getFlagCosto())){
	            	 /*******COSTO DIRECTO E INDIRECTO*****/
	            	/**Grabando el primer detalle Rural-Coordinador**/
	            	det = new  FiseFormato14CD();				
					idDet = new FiseFormato14CDPK();		
					idDet.setCodEmpresa(bean.getCodEmpresa());
					idDet.setAnoPresentacion(Long.valueOf(bean.getAnioPres())); 
					idDet.setMesPresentacion(Long.valueOf(bean.getMesPres())); 
					idDet.setAnoInicioVigencia(Long.valueOf(bean.getAnoIniVigencia())); 
					idDet.setAnoFinVigencia(Long.valueOf(bean.getAnoFinVigencia())); 
					idDet.setEtapa(bean.getEtapa()); 
					idDet.setIdTipPersonal(FiseConstants.TIPO_PERSONAL_CORDINADOR_COD);
					idDet.setIdZonaBenef(FiseConstants.ZONABENEF_RURAL_COD);
		            det.setId(idDet); 
		            if(validarDato(bean.getCanDRCoord())){
		            	det.setCantCostDirecto(Long.valueOf(valorCantidad(bean.getCanDRCoord())));	
					}	            	
	            	if(validarDato(bean.getCanIRCoord())){
	            		det.setCantCostIndirecto(Long.valueOf(valorCantidad(bean.getCanIRCoord()))); 	
					}					
					if(validarDato(bean.getCostDRCoord())){ 
						det.setCostoDirecto(new BigDecimal(bean.getCostDRCoord()));	
					}				
					if(validarDato(bean.getCostIRCoord())){
						det.setCostoIndirecto(new BigDecimal(bean.getCostIRCoord()));  	
					}					
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
					idDet.setAnoInicioVigencia(Long.valueOf(bean.getAnoIniVigencia())); 
					idDet.setAnoFinVigencia(Long.valueOf(bean.getAnoFinVigencia())); 	
					idDet.setEtapa(bean.getEtapa()); 
					idDet.setIdTipPersonal(FiseConstants.TIPO_PERSONAL_SUPERVISOR_COD);
					idDet.setIdZonaBenef(FiseConstants.ZONABENEF_RURAL_COD); 
					det.setId(idDet); 	
					if(validarDato(bean.getCanDRSupe())){
						det.setCantCostDirecto(Long.valueOf(valorCantidad(bean.getCanDRSupe())));
					}					
					if(validarDato(bean.getCanIRSupe())){
						det.setCantCostIndirecto(Long.valueOf(valorCantidad(bean.getCanIRSupe()))); 	
					}					
					if(validarDato(bean.getCostDRSupe())){
						det.setCostoDirecto(new BigDecimal(bean.getCostDRSupe())); 	
					}					
					if(validarDato(bean.getCostIRSupe())){
						det.setCostoIndirecto(new BigDecimal(bean.getCostIRSupe()));  	
					}					
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
					idDet.setAnoInicioVigencia(Long.valueOf(bean.getAnoIniVigencia())); 
					idDet.setAnoFinVigencia(Long.valueOf(bean.getAnoFinVigencia())); 	
					idDet.setEtapa(bean.getEtapa()); 
					idDet.setIdTipPersonal(FiseConstants.TIPO_PERSONAL_GESTOR_COD);
					idDet.setIdZonaBenef(FiseConstants.ZONABENEF_RURAL_COD);
					det.setId(idDet); 
					if(validarDato(bean.getCanDRGest())){
						det.setCantCostDirecto(Long.valueOf(valorCantidad(bean.getCanDRGest())));	
					}					
					if(validarDato(bean.getCanIRGest())){
						det.setCantCostIndirecto(Long.valueOf(valorCantidad(bean.getCanIRGest()))); 
					}					
					if(validarDato(bean.getCostDRGest())){
						det.setCostoDirecto(new BigDecimal(bean.getCostDRGest()));	
					}					
					if(validarDato(bean.getCostIRGest())){
						det.setCostoIndirecto(new BigDecimal(bean.getCostIRGest()));  		
					}					
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
					idDet.setAnoInicioVigencia(Long.valueOf(bean.getAnoIniVigencia())); 
					idDet.setAnoFinVigencia(Long.valueOf(bean.getAnoFinVigencia())); 	
					idDet.setEtapa(bean.getEtapa()); 
					idDet.setIdTipPersonal(FiseConstants.TIPO_PERSONAL_ASISTENTE_COD);
					idDet.setIdZonaBenef(FiseConstants.ZONABENEF_RURAL_COD); 
					det.setId(idDet); 
					if(validarDato(bean.getCanDRAsist())){
						det.setCantCostDirecto(Long.valueOf(valorCantidad(bean.getCanDRAsist())));	
					}					
					if(validarDato(bean.getCanIRAsist())){
						det.setCantCostIndirecto(Long.valueOf(valorCantidad(bean.getCanIRAsist())));	
					}					
					if(validarDato(bean.getCostDRAsist())){ 
						det.setCostoDirecto(new BigDecimal(bean.getCostDRAsist())); 	
					}					
					if(validarDato(bean.getCostIRAsist())){
						det.setCostoIndirecto(new BigDecimal(bean.getCostIRAsist()));  
					}					
					det.setUsuarioCreacion(bean.getUsuario());
					det.setTerminalCreacion(bean.getTerminal());
					det.setFechaCreacion(FechaUtil.obtenerFechaActual());	
					formato14CDDao.insertarFiseFormato14CD(det);
					/****PROVINCIA*****/				
					/**Grabando el primer detalle Urbano Provincia-Coordinador**/
					det = new  FiseFormato14CD();
					idDet = new FiseFormato14CDPK();		
					idDet.setCodEmpresa(bean.getCodEmpresa());
					idDet.setAnoPresentacion(Long.valueOf(bean.getAnioPres())); 
					idDet.setMesPresentacion(Long.valueOf(bean.getMesPres()));
					idDet.setAnoInicioVigencia(Long.valueOf(bean.getAnoIniVigencia())); 
					idDet.setAnoFinVigencia(Long.valueOf(bean.getAnoFinVigencia())); 		
					idDet.setEtapa(bean.getEtapa()); 
					idDet.setIdTipPersonal(FiseConstants.TIPO_PERSONAL_CORDINADOR_COD);
					idDet.setIdZonaBenef(FiseConstants.ZONABENEF_PROVINCIA_COD); 
					det.setId(idDet); 
					if(validarDato(bean.getCanDPCoord())){
						det.setCantCostDirecto(Long.valueOf(valorCantidad(bean.getCanDPCoord())));	
					}					
					if(validarDato(bean.getCanIPCoord())){
						det.setCantCostIndirecto(Long.valueOf(valorCantidad(bean.getCanIPCoord())));	
					}					
					if(validarDato(bean.getCostDPCoord())){
						det.setCostoDirecto(new BigDecimal(bean.getCostDPCoord())); 	
					}					
					if(validarDato(bean.getCostIPCoord())){
						det.setCostoIndirecto(new BigDecimal(bean.getCostIPCoord()));  	
					}					
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
					idDet.setAnoInicioVigencia(Long.valueOf(bean.getAnoIniVigencia())); 
					idDet.setAnoFinVigencia(Long.valueOf(bean.getAnoFinVigencia())); 	
					idDet.setEtapa(bean.getEtapa()); 
					idDet.setIdTipPersonal(FiseConstants.TIPO_PERSONAL_SUPERVISOR_COD);
					idDet.setIdZonaBenef(FiseConstants.ZONABENEF_PROVINCIA_COD); 
					det.setId(idDet); 	
					if(validarDato(bean.getCanDPSupe())){
						det.setCantCostDirecto(Long.valueOf(valorCantidad(bean.getCanDPSupe())));
					}					
					if(validarDato(bean.getCanIPSupe())){
						det.setCantCostIndirecto(Long.valueOf(valorCantidad(bean.getCanIPSupe()))); 	
					}					
					if(validarDato(bean.getCostDPSupe())){
						det.setCostoDirecto(new BigDecimal(bean.getCostDPSupe())); 	
					}					
					if(validarDato(bean.getCostIPSupe())){
						det.setCostoIndirecto(new BigDecimal(bean.getCostIPSupe()));  	
					}					
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
					idDet.setAnoInicioVigencia(Long.valueOf(bean.getAnoIniVigencia())); 
					idDet.setAnoFinVigencia(Long.valueOf(bean.getAnoFinVigencia())); 	
					idDet.setEtapa(bean.getEtapa()); 
					idDet.setIdTipPersonal(FiseConstants.TIPO_PERSONAL_GESTOR_COD);
					idDet.setIdZonaBenef(FiseConstants.ZONABENEF_PROVINCIA_COD); 
					det.setId(idDet); 	
					if(validarDato(bean.getCanDPGest())){
						det.setCantCostDirecto(Long.valueOf(valorCantidad(bean.getCanDPGest())));	
					}					
					if(validarDato(bean.getCanIPGest())){
						det.setCantCostIndirecto(Long.valueOf(valorCantidad(bean.getCanIPGest()))); 	
					}					
					if(validarDato(bean.getCostDPGest())){
						det.setCostoDirecto(new BigDecimal(bean.getCostDPGest())); 	
					}					
					if(validarDato(bean.getCostIPGest())){
						det.setCostoIndirecto(new BigDecimal(bean.getCostIPGest()));  
					}					
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
					idDet.setAnoInicioVigencia(Long.valueOf(bean.getAnoIniVigencia())); 
					idDet.setAnoFinVigencia(Long.valueOf(bean.getAnoFinVigencia())); 	
					idDet.setEtapa(bean.getEtapa()); 
					idDet.setIdTipPersonal(FiseConstants.TIPO_PERSONAL_ASISTENTE_COD);
					idDet.setIdZonaBenef(FiseConstants.ZONABENEF_PROVINCIA_COD);
					det.setId(idDet); 	
					if(validarDato(bean.getCanDPAsist())){
						det.setCantCostDirecto(Long.valueOf(valorCantidad(bean.getCanDPAsist())));	
					}					
					if(validarDato(bean.getCanIPAsist())){
						det.setCantCostIndirecto(Long.valueOf(valorCantidad(bean.getCanIPAsist())));	
					}					
					if(validarDato(bean.getCostDPAsist())){
						det.setCostoDirecto(new BigDecimal(bean.getCostDPAsist())); 	
					}					
					if(validarDato(bean.getCostIPAsist())){
						det.setCostoIndirecto(new BigDecimal(bean.getCostIPAsist()));	
					}					  
					det.setUsuarioCreacion(bean.getUsuario());
					det.setTerminalCreacion(bean.getTerminal());
					det.setFechaCreacion(FechaUtil.obtenerFechaActual());	
					formato14CDDao.insertarFiseFormato14CD(det);						
					/*****LIMA******/
					if(FiseConstants.COD_EMPRESA_EDELNOR.equals(bean.getCodEmpresa()) || 
							FiseConstants.COD_EMPRESA_LUZ_SUR.equals(bean.getCodEmpresa()))
					{
						/**Grabando el primer detalle Urbano Lima-Coordinador**/
						det = new  FiseFormato14CD();
						idDet = new FiseFormato14CDPK();		
						idDet.setCodEmpresa(bean.getCodEmpresa());
						idDet.setAnoPresentacion(Long.valueOf(bean.getAnioPres())); 
						idDet.setMesPresentacion(Long.valueOf(bean.getMesPres())); 
						idDet.setAnoInicioVigencia(Long.valueOf(bean.getAnoIniVigencia())); 
						idDet.setAnoFinVigencia(Long.valueOf(bean.getAnoFinVigencia())); 	
						idDet.setEtapa(bean.getEtapa()); 
						idDet.setIdTipPersonal(FiseConstants.TIPO_PERSONAL_CORDINADOR_COD);
						idDet.setIdZonaBenef(FiseConstants.ZONABENEF_LIMA_COD); 
						det.setId(idDet); 
						if(validarDato(bean.getCanDLCoord())){
							det.setCantCostDirecto(Long.valueOf(valorCantidad(bean.getCanDLCoord())));		
						}						
						if(validarDato(bean.getCanILCoord())){
							det.setCantCostIndirecto(Long.valueOf(valorCantidad(bean.getCanILCoord())));	
						}						
						if(validarDato(bean.getCostDLCoord())){
							det.setCostoDirecto(new BigDecimal(bean.getCostDLCoord())); 	
						}						
						if(validarDato(bean.getCostILCoord())){
							det.setCostoIndirecto(new BigDecimal(bean.getCostILCoord()));  	
						}						
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
						idDet.setAnoInicioVigencia(Long.valueOf(bean.getAnoIniVigencia())); 
						idDet.setAnoFinVigencia(Long.valueOf(bean.getAnoFinVigencia())); 	
						idDet.setEtapa(bean.getEtapa()); 
						idDet.setIdTipPersonal(FiseConstants.TIPO_PERSONAL_SUPERVISOR_COD);
						idDet.setIdZonaBenef(FiseConstants.ZONABENEF_LIMA_COD);
						det.setId(idDet);
						if(validarDato(bean.getCanDLSupe())){
							det.setCantCostDirecto(Long.valueOf(valorCantidad(bean.getCanDLSupe())));	
						}						
						if(validarDato(bean.getCanILSupe())){
							det.setCantCostIndirecto(Long.valueOf(valorCantidad(bean.getCanILSupe()))); 
						}						
						if(validarDato(bean.getCostDLSupe())){
							det.setCostoDirecto(new BigDecimal(bean.getCostDLSupe())); 	
						}						
						if(validarDato(bean.getCostILSupe())){
							det.setCostoIndirecto(new BigDecimal(bean.getCostILSupe()));  	
						}						
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
						idDet.setAnoInicioVigencia(Long.valueOf(bean.getAnoIniVigencia())); 
						idDet.setAnoFinVigencia(Long.valueOf(bean.getAnoFinVigencia())); 	
						idDet.setEtapa(bean.getEtapa()); 
						idDet.setIdTipPersonal(FiseConstants.TIPO_PERSONAL_GESTOR_COD);
						idDet.setIdZonaBenef(FiseConstants.ZONABENEF_LIMA_COD); 
						det.setId(idDet); 	
						if(validarDato(bean.getCanDLGest())){
							det.setCantCostDirecto(Long.valueOf(valorCantidad(bean.getCanDLGest())));	
						}						
						if(validarDato(bean.getCanILGest())){
							det.setCantCostIndirecto(Long.valueOf(valorCantidad(bean.getCanILGest()))); 	
						}						
						if(validarDato(bean.getCostDLGest())){
							det.setCostoDirecto(new BigDecimal(bean.getCostDLGest())); 	
						}						
						if(validarDato(bean.getCostILGest())){
							det.setCostoIndirecto(new BigDecimal(bean.getCostILGest())); 	
						}						 
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
						idDet.setAnoInicioVigencia(Long.valueOf(bean.getAnoIniVigencia())); 
						idDet.setAnoFinVigencia(Long.valueOf(bean.getAnoFinVigencia())); 	
						idDet.setEtapa(bean.getEtapa()); 
						idDet.setIdTipPersonal(FiseConstants.TIPO_PERSONAL_ASISTENTE_COD);
						idDet.setIdZonaBenef(FiseConstants.ZONABENEF_LIMA_COD);
						det.setId(idDet); 
						if(validarDato(bean.getCanDLAsist())){
							det.setCantCostDirecto(Long.valueOf(valorCantidad(bean.getCanDLAsist())));	
						}						
						if(validarDato(bean.getCanILAsist())){
							det.setCantCostIndirecto(Long.valueOf(valorCantidad(bean.getCanILAsist())));	
						}						
						if(validarDato(bean.getCostDLAsist())){
							det.setCostoDirecto(new BigDecimal(bean.getCostDLAsist())); 	
						}						
						if(validarDato(bean.getCostILAsist())){
							det.setCostoIndirecto(new BigDecimal(bean.getCostILAsist()));  	
						}						
						det.setUsuarioCreacion(bean.getUsuario());
						det.setTerminalCreacion(bean.getTerminal());
						det.setFechaCreacion(FechaUtil.obtenerFechaActual());	
						formato14CDDao.insertarFiseFormato14CD(det);
					}//Fin de empresa 								
	            }				
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
			if(inf!=null){
				inf=null;
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
		  /*Actualizando en la cabecera*/		
			idCab = new FiseFormato14CCPK();
			//PK
			idCab.setCodEmpresa(bean.getCodEmpresa());
			idCab.setAnoPresentacion(Long.valueOf(bean.getAnioPres())); 
			idCab.setMesPresentacion(Long.valueOf(bean.getMesPres())); 
			idCab.setAnoInicioVigencia(Long.valueOf(bean.getAnoIniVigencia())); 
			idCab.setAnoFinVigencia(Long.valueOf(bean.getAnoFinVigencia())); 
			idCab.setEtapa(bean.getEtapa()); 
			//FIN PK
			cab = formato14CCDao.obtenerFormato14CC(idCab); 
			logger.info("objeto en sesion : "+cab);			
			cab.setNombreSede(bean.getNombreSede());			
			cab.setNombreArchivoExcel(bean.getNombreExel());
			cab.setNombreArchivoTexto(bean.getNombreText()); 
			if(validarDato(bean.getNumRural())){
            	cab.setNumBenefEmpPerAntRural(Long.valueOf(bean.getNumRural()));	
			}			
			if(validarDato(bean.getNumUrbProv())){
				cab.setNumBenefEmpPerAntUrbProv(Long.valueOf(bean.getNumUrbProv()));	
			}				
			if(validarDato(bean.getNumUrbLima())){
				cab.setNumBenefEmpPerAntUrbLima(Long.valueOf(bean.getNumUrbLima()));	
			}				
			if(validarDato(bean.getCostoPromUrbLima())){
				cab.setCostoPromMenUrbLima(new BigDecimal(bean.getCostoPromUrbLima())); 	
			}				
			if(validarDato(bean.getCostoPromRural())){
				cab.setCostoPromMenRural(new BigDecimal(bean.getCostoPromRural()));	
			}				
			if(validarDato(bean.getCostoPromUrbProv())){
				cab.setCostoPromMenUrbProv(new BigDecimal(bean.getCostoPromUrbProv()));	
			}			
			cab.setUsuarioActualizacion(bean.getUsuario()); 
			cab.setTerminalActualizacion(bean.getTerminal()); 
			cab.setFechaActualizacion(FechaUtil.obtenerFechaActual()); 
			formato14CCDao.actualizarFiseFormato14CC(cab); 
		
			/***Actualizando en el detalle de la cabecera*/
			if(FormatoUtil.isNotBlank(bean.getFlagCosto()) && 
            		FiseConstants.COSTO_DIRECTO_F14C.equals(bean.getFlagCosto())){
				/******COSTO DIRECTO*******/
				/**Actualizando el primer detalle Rural-Coordinador**/			
				idDet = new FiseFormato14CDPK();		
				idDet.setCodEmpresa(bean.getCodEmpresa());
				idDet.setAnoPresentacion(Long.valueOf(bean.getAnioPres())); 
				idDet.setMesPresentacion(Long.valueOf(bean.getMesPres())); 
				idDet.setAnoInicioVigencia(Long.valueOf(bean.getAnoIniVigencia())); 
				idDet.setAnoFinVigencia(Long.valueOf(bean.getAnoFinVigencia())); 	
				idDet.setEtapa(bean.getEtapa()); 
				idDet.setIdTipPersonal(FiseConstants.TIPO_PERSONAL_CORDINADOR_COD);
				idDet.setIdZonaBenef(FiseConstants.ZONABENEF_RURAL_COD); 		
				det = formato14CDDao.obtenerFiseFormato14CD(idDet);
				if(validarDato(bean.getCanDRCoord())){
		            	det.setCantCostDirecto(Long.valueOf(valorCantidad(bean.getCanDRCoord())));		
				}	            	
	            if(validarDato(bean.getCostDRCoord())){
	            		det.setCostoDirecto(new BigDecimal(bean.getCostDRCoord())); 		
				}								
				det.setUsuarioActualizacion(bean.getUsuario()); 
				det.setTerminalActualizacion(bean.getTerminal()); 
				det.setFechaActualizacion(FechaUtil.obtenerFechaActual()); 
				formato14CDDao.actualizarFiseFormato14CD(det); 
				/**Actualizando el primer detalle Rural-Supervisor**/				
				idDet = new FiseFormato14CDPK();		
				idDet.setCodEmpresa(bean.getCodEmpresa());
				idDet.setAnoPresentacion(Long.valueOf(bean.getAnioPres())); 
				idDet.setMesPresentacion(Long.valueOf(bean.getMesPres())); 
				idDet.setAnoInicioVigencia(Long.valueOf(bean.getAnoIniVigencia())); 
				idDet.setAnoFinVigencia(Long.valueOf(bean.getAnoFinVigencia())); 	
				idDet.setEtapa(bean.getEtapa()); 
				idDet.setIdTipPersonal(FiseConstants.TIPO_PERSONAL_SUPERVISOR_COD);
				idDet.setIdZonaBenef(FiseConstants.ZONABENEF_RURAL_COD); 
				det = formato14CDDao.obtenerFiseFormato14CD(idDet);		
				if(validarDato(bean.getCanDRSupe())){
					det.setCantCostDirecto(Long.valueOf(valorCantidad(bean.getCanDRSupe())));	
				}					
				if(validarDato(bean.getCostDRSupe())){
					det.setCostoDirecto(new BigDecimal(bean.getCostDRSupe())); 	
				}				
				det.setUsuarioActualizacion(bean.getUsuario()); 
				det.setTerminalActualizacion(bean.getTerminal()); 
				det.setFechaActualizacion(FechaUtil.obtenerFechaActual()); 
				formato14CDDao.actualizarFiseFormato14CD(det); 	
				/**Actualizando el primer detalle Rural-Gestor**/				
				idDet = new FiseFormato14CDPK();		
				idDet.setCodEmpresa(bean.getCodEmpresa());
				idDet.setAnoPresentacion(Long.valueOf(bean.getAnioPres())); 
				idDet.setMesPresentacion(Long.valueOf(bean.getMesPres())); 
				idDet.setAnoInicioVigencia(Long.valueOf(bean.getAnoIniVigencia())); 
				idDet.setAnoFinVigencia(Long.valueOf(bean.getAnoFinVigencia())); 	
				idDet.setEtapa(bean.getEtapa()); 
				idDet.setIdTipPersonal(FiseConstants.TIPO_PERSONAL_GESTOR_COD);
				idDet.setIdZonaBenef(FiseConstants.ZONABENEF_RURAL_COD);
				det = formato14CDDao.obtenerFiseFormato14CD(idDet);		
				if(validarDato(bean.getCanDRGest())){
					det.setCantCostDirecto(Long.valueOf(valorCantidad(bean.getCanDRGest())));
				}					
				if(validarDato(bean.getCostDRGest())){
					det.setCostoDirecto(new BigDecimal(bean.getCostDRGest()));			
				}				
				det.setUsuarioActualizacion(bean.getUsuario()); 
				det.setTerminalActualizacion(bean.getTerminal()); 
				det.setFechaActualizacion(FechaUtil.obtenerFechaActual()); 
				formato14CDDao.actualizarFiseFormato14CD(det); 	
				/**Actualizando el primer detalle Rural-Asistente/Auxiliar**/				
				idDet = new FiseFormato14CDPK();		
				idDet.setCodEmpresa(bean.getCodEmpresa());
				idDet.setAnoPresentacion(Long.valueOf(bean.getAnioPres())); 
				idDet.setMesPresentacion(Long.valueOf(bean.getMesPres())); 
				idDet.setAnoInicioVigencia(Long.valueOf(bean.getAnoIniVigencia())); 
				idDet.setAnoFinVigencia(Long.valueOf(bean.getAnoFinVigencia())); 	
				idDet.setEtapa(bean.getEtapa()); 
				idDet.setIdTipPersonal(FiseConstants.TIPO_PERSONAL_ASISTENTE_COD);
				idDet.setIdZonaBenef(FiseConstants.ZONABENEF_RURAL_COD); 
				det = formato14CDDao.obtenerFiseFormato14CD(idDet);		
				if(validarDato(bean.getCanDRAsist())){
					det.setCantCostDirecto(Long.valueOf(valorCantidad(bean.getCanDRAsist())));	
				}					
				if(validarDato(bean.getCostDRAsist())){
					det.setCostoDirecto(new BigDecimal(bean.getCostDRAsist())); 				
				}	 					
				det.setUsuarioActualizacion(bean.getUsuario()); 
				det.setTerminalActualizacion(bean.getTerminal()); 
				det.setFechaActualizacion(FechaUtil.obtenerFechaActual()); 
				formato14CDDao.actualizarFiseFormato14CD(det); 	
				/******PROVINCIA******/		
				/**Actualizando el primer detalle Urbano Provincia-Coordinador**/				
				idDet = new FiseFormato14CDPK();		
				idDet.setCodEmpresa(bean.getCodEmpresa());
				idDet.setAnoPresentacion(Long.valueOf(bean.getAnioPres())); 
				idDet.setMesPresentacion(Long.valueOf(bean.getMesPres()));
				idDet.setAnoInicioVigencia(Long.valueOf(bean.getAnoIniVigencia())); 
				idDet.setAnoFinVigencia(Long.valueOf(bean.getAnoFinVigencia())); 		
				idDet.setEtapa(bean.getEtapa()); 
				idDet.setIdTipPersonal(FiseConstants.TIPO_PERSONAL_CORDINADOR_COD);
				idDet.setIdZonaBenef(FiseConstants.ZONABENEF_PROVINCIA_COD); 
				det = formato14CDDao.obtenerFiseFormato14CD(idDet);		
				if(validarDato(bean.getCanDPCoord())){
					det.setCantCostDirecto(Long.valueOf(valorCantidad(bean.getCanDPCoord())));		
				}					
				if(validarDato(bean.getCostDPCoord())){
					det.setCostoDirecto(new BigDecimal(bean.getCostDPCoord())); 	
				}				
				det.setUsuarioActualizacion(bean.getUsuario()); 
				det.setTerminalActualizacion(bean.getTerminal()); 
				det.setFechaActualizacion(FechaUtil.obtenerFechaActual()); 
				formato14CDDao.actualizarFiseFormato14CD(det); 				
				/**Actualizando el primer detalle Urbano Provincias-Supervisor**/			
				idDet = new FiseFormato14CDPK();		
				idDet.setCodEmpresa(bean.getCodEmpresa());
				idDet.setAnoPresentacion(Long.valueOf(bean.getAnioPres())); 
				idDet.setMesPresentacion(Long.valueOf(bean.getMesPres())); 
				idDet.setAnoInicioVigencia(Long.valueOf(bean.getAnoIniVigencia())); 
				idDet.setAnoFinVigencia(Long.valueOf(bean.getAnoFinVigencia())); 	
				idDet.setEtapa(bean.getEtapa()); 
				idDet.setIdTipPersonal(FiseConstants.TIPO_PERSONAL_SUPERVISOR_COD);
				idDet.setIdZonaBenef(FiseConstants.ZONABENEF_PROVINCIA_COD); 
				det = formato14CDDao.obtenerFiseFormato14CD(idDet);		
				if(validarDato(bean.getCanDPSupe())){
					det.setCantCostDirecto(Long.valueOf(valorCantidad(bean.getCanDPSupe())));	
				}					
				if(validarDato(bean.getCostDPSupe())){
					det.setCostoDirecto(new BigDecimal(bean.getCostDPSupe())); 	
				}				
				det.setUsuarioActualizacion(bean.getUsuario()); 
				det.setTerminalActualizacion(bean.getTerminal()); 
				det.setFechaActualizacion(FechaUtil.obtenerFechaActual()); 
				formato14CDDao.actualizarFiseFormato14CD(det); 				
				/**Actualizando el primer detalle Urbana Provincias-Gestor**/				
				idDet = new FiseFormato14CDPK();		
				idDet.setCodEmpresa(bean.getCodEmpresa());
				idDet.setAnoPresentacion(Long.valueOf(bean.getAnioPres())); 
				idDet.setMesPresentacion(Long.valueOf(bean.getMesPres())); 
				idDet.setAnoInicioVigencia(Long.valueOf(bean.getAnoIniVigencia())); 
				idDet.setAnoFinVigencia(Long.valueOf(bean.getAnoFinVigencia())); 	
				idDet.setEtapa(bean.getEtapa()); 
				idDet.setIdTipPersonal(FiseConstants.TIPO_PERSONAL_GESTOR_COD);
				idDet.setIdZonaBenef(FiseConstants.ZONABENEF_PROVINCIA_COD); 
				det = formato14CDDao.obtenerFiseFormato14CD(idDet);		
				if(validarDato(bean.getCanDPGest())){
					det.setCantCostDirecto(Long.valueOf(valorCantidad(bean.getCanDPGest())));		
				}					
				if(validarDato(bean.getCostDPGest())){
					det.setCostoDirecto(new BigDecimal(bean.getCostDPGest())); 		
				}			
				det.setUsuarioActualizacion(bean.getUsuario()); 
				det.setTerminalActualizacion(bean.getTerminal()); 
				det.setFechaActualizacion(FechaUtil.obtenerFechaActual()); 
				formato14CDDao.actualizarFiseFormato14CD(det); 				
				/**Actualizando el primer detalle Urbano Provincias -Asistente/Auxiliar**/			
				idDet = new FiseFormato14CDPK();		
				idDet.setCodEmpresa(bean.getCodEmpresa());
				idDet.setAnoPresentacion(Long.valueOf(bean.getAnioPres())); 
				idDet.setMesPresentacion(Long.valueOf(bean.getMesPres())); 
				idDet.setAnoInicioVigencia(Long.valueOf(bean.getAnoIniVigencia())); 
				idDet.setAnoFinVigencia(Long.valueOf(bean.getAnoFinVigencia())); 	
				idDet.setEtapa(bean.getEtapa()); 
				idDet.setIdTipPersonal(FiseConstants.TIPO_PERSONAL_ASISTENTE_COD);
				idDet.setIdZonaBenef(FiseConstants.ZONABENEF_PROVINCIA_COD);
				det = formato14CDDao.obtenerFiseFormato14CD(idDet);		
				if(validarDato(bean.getCanDPAsist())){
					det.setCantCostDirecto(Long.valueOf(valorCantidad(bean.getCanDPAsist())));		
				}					
				if(validarDato(bean.getCostDPAsist())){
					det.setCostoDirecto(new BigDecimal(bean.getCostDPAsist())); 	
				}					
				det.setUsuarioActualizacion(bean.getUsuario()); 
				det.setTerminalActualizacion(bean.getTerminal()); 
				det.setFechaActualizacion(FechaUtil.obtenerFechaActual()); 
				formato14CDDao.actualizarFiseFormato14CD(det); 
				/****LIMA****/
				if(FiseConstants.COD_EMPRESA_EDELNOR.equals(bean.getCodEmpresa()) || 
						FiseConstants.COD_EMPRESA_LUZ_SUR.equals(bean.getCodEmpresa()))
				{
					/**actualizando el primer detalle Urbano Lima-Coordinador**/					
					idDet = new FiseFormato14CDPK();		
					idDet.setCodEmpresa(bean.getCodEmpresa());
					idDet.setAnoPresentacion(Long.valueOf(bean.getAnioPres())); 
					idDet.setMesPresentacion(Long.valueOf(bean.getMesPres())); 
					idDet.setAnoInicioVigencia(Long.valueOf(bean.getAnoIniVigencia())); 
					idDet.setAnoFinVigencia(Long.valueOf(bean.getAnoFinVigencia())); 	
					idDet.setEtapa(bean.getEtapa()); 
					idDet.setIdTipPersonal(FiseConstants.TIPO_PERSONAL_CORDINADOR_COD);
					idDet.setIdZonaBenef(FiseConstants.ZONABENEF_LIMA_COD); 
					det = formato14CDDao.obtenerFiseFormato14CD(idDet);		
					if(validarDato(bean.getCanDLCoord())){
						det.setCantCostDirecto(Long.valueOf(valorCantidad(bean.getCanDLCoord())));	
					}						
					if(validarDato(bean.getCostDLCoord())){
						det.setCostoDirecto(new BigDecimal(bean.getCostDLCoord())); 		
					}				
					det.setUsuarioActualizacion(bean.getUsuario()); 
					det.setTerminalActualizacion(bean.getTerminal()); 
					det.setFechaActualizacion(FechaUtil.obtenerFechaActual()); 
					formato14CDDao.actualizarFiseFormato14CD(det); 
					/**Actualizando el primer detalle Urbana Lima -Supervisor**/					
					idDet = new FiseFormato14CDPK();		
					idDet.setCodEmpresa(bean.getCodEmpresa());
					idDet.setAnoPresentacion(Long.valueOf(bean.getAnioPres())); 
					idDet.setMesPresentacion(Long.valueOf(bean.getMesPres())); 
					idDet.setAnoInicioVigencia(Long.valueOf(bean.getAnoIniVigencia())); 
					idDet.setAnoFinVigencia(Long.valueOf(bean.getAnoFinVigencia())); 	
					idDet.setEtapa(bean.getEtapa()); 
					idDet.setIdTipPersonal(FiseConstants.TIPO_PERSONAL_SUPERVISOR_COD);
					idDet.setIdZonaBenef(FiseConstants.ZONABENEF_LIMA_COD);
					det = formato14CDDao.obtenerFiseFormato14CD(idDet);
					if(validarDato(bean.getCanDLSupe())){
						det.setCantCostDirecto(Long.valueOf(valorCantidad(bean.getCanDLSupe())));	
					}						
					if(validarDato(bean.getCostDLSupe())){
						det.setCostoDirecto(new BigDecimal(bean.getCostDLSupe())); 		
					}										
					det.setUsuarioActualizacion(bean.getUsuario()); 
					det.setTerminalActualizacion(bean.getTerminal()); 
					det.setFechaActualizacion(FechaUtil.obtenerFechaActual()); 
					formato14CDDao.actualizarFiseFormato14CD(det); 
					/**Actualizando el primer detalle Urbana Lima -Gestor**/					
					idDet = new FiseFormato14CDPK();		
					idDet.setCodEmpresa(bean.getCodEmpresa());
					idDet.setAnoPresentacion(Long.valueOf(bean.getAnioPres())); 
					idDet.setMesPresentacion(Long.valueOf(bean.getMesPres())); 
					idDet.setAnoInicioVigencia(Long.valueOf(bean.getAnoIniVigencia())); 
					idDet.setAnoFinVigencia(Long.valueOf(bean.getAnoFinVigencia())); 	
					idDet.setEtapa(bean.getEtapa()); 
					idDet.setIdTipPersonal(FiseConstants.TIPO_PERSONAL_GESTOR_COD);
					idDet.setIdZonaBenef(FiseConstants.ZONABENEF_LIMA_COD); 
					det = formato14CDDao.obtenerFiseFormato14CD(idDet);		
					if(validarDato(bean.getCanDLGest())){
						det.setCantCostDirecto(Long.valueOf(valorCantidad(bean.getCanDLGest())));	
					}						
					if(validarDato(bean.getCostDLGest())){
						det.setCostoDirecto(new BigDecimal(bean.getCostDLGest()));				
					}					
					det.setUsuarioActualizacion(bean.getUsuario()); 
					det.setTerminalActualizacion(bean.getTerminal()); 
					det.setFechaActualizacion(FechaUtil.obtenerFechaActual()); 
					formato14CDDao.actualizarFiseFormato14CD(det); 
					/**Actualizando el primer detalle Urbana Lima -Asistente/Auxiliar**/					
					idDet = new FiseFormato14CDPK();		
					idDet.setCodEmpresa(bean.getCodEmpresa());
					idDet.setAnoPresentacion(Long.valueOf(bean.getAnioPres())); 
					idDet.setMesPresentacion(Long.valueOf(bean.getMesPres())); 
					idDet.setAnoInicioVigencia(Long.valueOf(bean.getAnoIniVigencia())); 
					idDet.setAnoFinVigencia(Long.valueOf(bean.getAnoFinVigencia())); 	
					idDet.setEtapa(bean.getEtapa()); 
					idDet.setIdTipPersonal(FiseConstants.TIPO_PERSONAL_ASISTENTE_COD);
					idDet.setIdZonaBenef(FiseConstants.ZONABENEF_LIMA_COD);
					det = formato14CDDao.obtenerFiseFormato14CD(idDet);		
					if(validarDato(bean.getCanDLAsist())){
						det.setCantCostDirecto(Long.valueOf(valorCantidad(bean.getCanDLAsist())));	
					}						
					if(validarDato(bean.getCostDLAsist())){
						det.setCostoDirecto(new BigDecimal(bean.getCostDLAsist())); 			
					}					
					det.setUsuarioActualizacion(bean.getUsuario()); 
					det.setTerminalActualizacion(bean.getTerminal()); 
					det.setFechaActualizacion(FechaUtil.obtenerFechaActual()); 
					formato14CDDao.actualizarFiseFormato14CD(det); 		
				}//	fin de validacion de empresa							
				
			}else if(FormatoUtil.isNotBlank(bean.getFlagCosto()) && 
            		FiseConstants.COSTO_INDIRECTO_F14C.equals(bean.getFlagCosto())){
				/**COSTO INDIRECTO**/
				/**Actualizando el primer detalle Rural-Coordinador**/		
				idDet = new FiseFormato14CDPK();		
				idDet.setCodEmpresa(bean.getCodEmpresa());
				idDet.setAnoPresentacion(Long.valueOf(bean.getAnioPres())); 
				idDet.setMesPresentacion(Long.valueOf(bean.getMesPres())); 
				idDet.setAnoInicioVigencia(Long.valueOf(bean.getAnoIniVigencia())); 
				idDet.setAnoFinVigencia(Long.valueOf(bean.getAnoFinVigencia())); 
				idDet.setEtapa(bean.getEtapa()); 
				idDet.setIdTipPersonal(FiseConstants.TIPO_PERSONAL_CORDINADOR_COD);
				idDet.setIdZonaBenef(FiseConstants.ZONABENEF_RURAL_COD);
				det = formato14CDDao.obtenerFiseFormato14CD(idDet);       	
				if(validarDato(bean.getCanIRCoord())){
	            	det.setCantCostIndirecto(Long.valueOf(valorCantidad(bean.getCanIRCoord()))); 	
				}					
				if(validarDato(bean.getCostIRCoord())){
					det.setCostoIndirecto(new BigDecimal(bean.getCostIRCoord()));  	
				}
				det.setUsuarioActualizacion(bean.getUsuario()); 
				det.setTerminalActualizacion(bean.getTerminal()); 
				det.setFechaActualizacion(FechaUtil.obtenerFechaActual()); 
				formato14CDDao.actualizarFiseFormato14CD(det); 	
				/**Actualizando el primer detalle Rural-Supervisor**/				
				idDet = new FiseFormato14CDPK();		
				idDet.setCodEmpresa(bean.getCodEmpresa());
				idDet.setAnoPresentacion(Long.valueOf(bean.getAnioPres())); 
				idDet.setMesPresentacion(Long.valueOf(bean.getMesPres())); 
				idDet.setAnoInicioVigencia(Long.valueOf(bean.getAnoIniVigencia())); 
				idDet.setAnoFinVigencia(Long.valueOf(bean.getAnoFinVigencia())); 	
				idDet.setEtapa(bean.getEtapa()); 
				idDet.setIdTipPersonal(FiseConstants.TIPO_PERSONAL_SUPERVISOR_COD);
				idDet.setIdZonaBenef(FiseConstants.ZONABENEF_RURAL_COD); 
				det = formato14CDDao.obtenerFiseFormato14CD(idDet);		
				if(validarDato(bean.getCanIRSupe())){
					det.setCantCostIndirecto(Long.valueOf(valorCantidad(bean.getCanIRSupe()))); 	
				}					
				if(validarDato(bean.getCostIRSupe())){
					det.setCostoIndirecto(new BigDecimal(bean.getCostIRSupe()));  	
				}
				det.setUsuarioActualizacion(bean.getUsuario()); 
				det.setTerminalActualizacion(bean.getTerminal()); 
				det.setFechaActualizacion(FechaUtil.obtenerFechaActual()); 
				formato14CDDao.actualizarFiseFormato14CD(det); 	
				/**Actualizando el primer detalle Rural-Gestor**/				
				idDet = new FiseFormato14CDPK();		
				idDet.setCodEmpresa(bean.getCodEmpresa());
				idDet.setAnoPresentacion(Long.valueOf(bean.getAnioPres())); 
				idDet.setMesPresentacion(Long.valueOf(bean.getMesPres())); 
				idDet.setAnoInicioVigencia(Long.valueOf(bean.getAnoIniVigencia())); 
				idDet.setAnoFinVigencia(Long.valueOf(bean.getAnoFinVigencia())); 	
				idDet.setEtapa(bean.getEtapa()); 
				idDet.setIdTipPersonal(FiseConstants.TIPO_PERSONAL_GESTOR_COD);
				idDet.setIdZonaBenef(FiseConstants.ZONABENEF_RURAL_COD);
				det = formato14CDDao.obtenerFiseFormato14CD(idDet);	
				if(validarDato(bean.getCanIRGest())){
					det.setCantCostIndirecto(Long.valueOf(valorCantidad(bean.getCanIRGest()))); 	
				}					
				if(validarDato(bean.getCostIRGest())){
					det.setCostoIndirecto(new BigDecimal(bean.getCostIRGest()));  	
				}
				det.setUsuarioActualizacion(bean.getUsuario()); 
				det.setTerminalActualizacion(bean.getTerminal()); 
				det.setFechaActualizacion(FechaUtil.obtenerFechaActual()); 
				formato14CDDao.actualizarFiseFormato14CD(det); 		
				/**Actualizando el primer detalle Rural-Asistente/Auxiliar**/			
				idDet = new FiseFormato14CDPK();		
				idDet.setCodEmpresa(bean.getCodEmpresa());
				idDet.setAnoPresentacion(Long.valueOf(bean.getAnioPres())); 
				idDet.setMesPresentacion(Long.valueOf(bean.getMesPres())); 
				idDet.setAnoInicioVigencia(Long.valueOf(bean.getAnoIniVigencia())); 
				idDet.setAnoFinVigencia(Long.valueOf(bean.getAnoFinVigencia())); 	
				idDet.setEtapa(bean.getEtapa()); 
				idDet.setIdTipPersonal(FiseConstants.TIPO_PERSONAL_ASISTENTE_COD);
				idDet.setIdZonaBenef(FiseConstants.ZONABENEF_RURAL_COD); 
				det = formato14CDDao.obtenerFiseFormato14CD(idDet);	
				if(validarDato(bean.getCanIRAsist())){
					det.setCantCostIndirecto(Long.valueOf(valorCantidad(bean.getCanIRAsist()))); 
				}					
				if(validarDato(bean.getCostIRAsist())){
					det.setCostoIndirecto(new BigDecimal(bean.getCostIRAsist()));  	
				}
				det.setUsuarioActualizacion(bean.getUsuario()); 
				det.setTerminalActualizacion(bean.getTerminal()); 
				det.setFechaActualizacion(FechaUtil.obtenerFechaActual()); 
				formato14CDDao.actualizarFiseFormato14CD(det); 	
				/***PROVINCIA**/				
				/**Actualizando el primer detalle Urbano Provincia-Coordinador**/				
				idDet = new FiseFormato14CDPK();		
				idDet.setCodEmpresa(bean.getCodEmpresa());
				idDet.setAnoPresentacion(Long.valueOf(bean.getAnioPres())); 
				idDet.setMesPresentacion(Long.valueOf(bean.getMesPres()));
				idDet.setAnoInicioVigencia(Long.valueOf(bean.getAnoIniVigencia())); 
				idDet.setAnoFinVigencia(Long.valueOf(bean.getAnoFinVigencia())); 		
				idDet.setEtapa(bean.getEtapa()); 
				idDet.setIdTipPersonal(FiseConstants.TIPO_PERSONAL_CORDINADOR_COD);
				idDet.setIdZonaBenef(FiseConstants.ZONABENEF_PROVINCIA_COD); 
				det = formato14CDDao.obtenerFiseFormato14CD(idDet);				
				if(validarDato(bean.getCanIPCoord())){
					det.setCantCostIndirecto(Long.valueOf(valorCantidad(bean.getCanIPCoord())));	
				}					
				if(validarDato(bean.getCostIPCoord())){
					det.setCostoIndirecto(new BigDecimal(bean.getCostIPCoord()));  	
				}	
				det.setUsuarioActualizacion(bean.getUsuario()); 
				det.setTerminalActualizacion(bean.getTerminal()); 
				det.setFechaActualizacion(FechaUtil.obtenerFechaActual()); 
				formato14CDDao.actualizarFiseFormato14CD(det);			
				/**Actualizando el primer detalle Urbano Provincias-Supervisor**/				
				idDet = new FiseFormato14CDPK();		
				idDet.setCodEmpresa(bean.getCodEmpresa());
				idDet.setAnoPresentacion(Long.valueOf(bean.getAnioPres())); 
				idDet.setMesPresentacion(Long.valueOf(bean.getMesPres())); 
				idDet.setAnoInicioVigencia(Long.valueOf(bean.getAnoIniVigencia())); 
				idDet.setAnoFinVigencia(Long.valueOf(bean.getAnoFinVigencia())); 	
				idDet.setEtapa(bean.getEtapa()); 
				idDet.setIdTipPersonal(FiseConstants.TIPO_PERSONAL_SUPERVISOR_COD);
				idDet.setIdZonaBenef(FiseConstants.ZONABENEF_PROVINCIA_COD); 
				det = formato14CDDao.obtenerFiseFormato14CD(idDet);			
				if(validarDato(bean.getCanIPSupe())){
					det.setCantCostIndirecto(Long.valueOf(valorCantidad(bean.getCanIPSupe())));	
				}					
				if(validarDato(bean.getCostIPSupe())){
					det.setCostoIndirecto(new BigDecimal(bean.getCostIPSupe()));  
				}	
				det.setUsuarioActualizacion(bean.getUsuario()); 
				det.setTerminalActualizacion(bean.getTerminal()); 
				det.setFechaActualizacion(FechaUtil.obtenerFechaActual()); 
				formato14CDDao.actualizarFiseFormato14CD(det); 				
				/**Actualizando el primer detalle Urbana Provincias-Gestor**/			
				idDet = new FiseFormato14CDPK();		
				idDet.setCodEmpresa(bean.getCodEmpresa());
				idDet.setAnoPresentacion(Long.valueOf(bean.getAnioPres())); 
				idDet.setMesPresentacion(Long.valueOf(bean.getMesPres())); 
				idDet.setAnoInicioVigencia(Long.valueOf(bean.getAnoIniVigencia())); 
				idDet.setAnoFinVigencia(Long.valueOf(bean.getAnoFinVigencia())); 	
				idDet.setEtapa(bean.getEtapa()); 
				idDet.setIdTipPersonal(FiseConstants.TIPO_PERSONAL_GESTOR_COD);
				idDet.setIdZonaBenef(FiseConstants.ZONABENEF_PROVINCIA_COD); 
				det = formato14CDDao.obtenerFiseFormato14CD(idDet);			
				if(validarDato(bean.getCanIPGest())){
					det.setCantCostIndirecto(Long.valueOf(valorCantidad(bean.getCanIPGest())));
				}					
				if(validarDato(bean.getCostIPGest())){
					det.setCostoIndirecto(new BigDecimal(bean.getCostIPGest()));  
				}	
				det.setUsuarioActualizacion(bean.getUsuario()); 
				det.setTerminalActualizacion(bean.getTerminal()); 
				det.setFechaActualizacion(FechaUtil.obtenerFechaActual()); 
				formato14CDDao.actualizarFiseFormato14CD(det); 			
				/**Actualizando el primer detalle Urbano Provincias -Asistente/Auxiliar**/				
				idDet = new FiseFormato14CDPK();		
				idDet.setCodEmpresa(bean.getCodEmpresa());
				idDet.setAnoPresentacion(Long.valueOf(bean.getAnioPres())); 
				idDet.setMesPresentacion(Long.valueOf(bean.getMesPres())); 
				idDet.setAnoInicioVigencia(Long.valueOf(bean.getAnoIniVigencia())); 
				idDet.setAnoFinVigencia(Long.valueOf(bean.getAnoFinVigencia())); 	
				idDet.setEtapa(bean.getEtapa()); 
				idDet.setIdTipPersonal(FiseConstants.TIPO_PERSONAL_ASISTENTE_COD);
				idDet.setIdZonaBenef(FiseConstants.ZONABENEF_PROVINCIA_COD);
				det = formato14CDDao.obtenerFiseFormato14CD(idDet);			
				if(validarDato(bean.getCanIPAsist())){
					det.setCantCostIndirecto(Long.valueOf(valorCantidad(bean.getCanIPAsist())));
				}					
				if(validarDato(bean.getCostIPAsist())){
					det.setCostoIndirecto(new BigDecimal(bean.getCostIPAsist()));  	
				} 
				det.setUsuarioActualizacion(bean.getUsuario()); 
				det.setTerminalActualizacion(bean.getTerminal()); 
				det.setFechaActualizacion(FechaUtil.obtenerFechaActual()); 
				formato14CDDao.actualizarFiseFormato14CD(det); 	
				/*****LIMA****/
				if(FiseConstants.COD_EMPRESA_EDELNOR.equals(bean.getCodEmpresa()) || 
						FiseConstants.COD_EMPRESA_LUZ_SUR.equals(bean.getCodEmpresa()))
				{
					/**Actualizando el primer detalle Urbano Lima-Coordinador**/					
					idDet = new FiseFormato14CDPK();		
					idDet.setCodEmpresa(bean.getCodEmpresa());
					idDet.setAnoPresentacion(Long.valueOf(bean.getAnioPres())); 
					idDet.setMesPresentacion(Long.valueOf(bean.getMesPres())); 
					idDet.setAnoInicioVigencia(Long.valueOf(bean.getAnoIniVigencia())); 
					idDet.setAnoFinVigencia(Long.valueOf(bean.getAnoFinVigencia())); 	
					idDet.setEtapa(bean.getEtapa()); 
					idDet.setIdTipPersonal(FiseConstants.TIPO_PERSONAL_CORDINADOR_COD);
					idDet.setIdZonaBenef(FiseConstants.ZONABENEF_LIMA_COD); 
					det = formato14CDDao.obtenerFiseFormato14CD(idDet);				
					if(validarDato(bean.getCanILCoord())){
						det.setCantCostIndirecto(Long.valueOf(valorCantidad(bean.getCanILCoord())));	
					}						
					if(validarDato(bean.getCostILCoord())){
						det.setCostoIndirecto(new BigDecimal(bean.getCostILCoord()));  	
					}	
					det.setUsuarioActualizacion(bean.getUsuario()); 
					det.setTerminalActualizacion(bean.getTerminal()); 
					det.setFechaActualizacion(FechaUtil.obtenerFechaActual()); 
					formato14CDDao.actualizarFiseFormato14CD(det); 		
					/**Actualizando el primer detalle Urbana Lima -Supervisor**/					
					idDet = new FiseFormato14CDPK();		
					idDet.setCodEmpresa(bean.getCodEmpresa());
					idDet.setAnoPresentacion(Long.valueOf(bean.getAnioPres())); 
					idDet.setMesPresentacion(Long.valueOf(bean.getMesPres())); 
					idDet.setAnoInicioVigencia(Long.valueOf(bean.getAnoIniVigencia())); 
					idDet.setAnoFinVigencia(Long.valueOf(bean.getAnoFinVigencia())); 	
					idDet.setEtapa(bean.getEtapa()); 
					idDet.setIdTipPersonal(FiseConstants.TIPO_PERSONAL_SUPERVISOR_COD);
					idDet.setIdZonaBenef(FiseConstants.ZONABENEF_LIMA_COD);
					det = formato14CDDao.obtenerFiseFormato14CD(idDet);					
					if(validarDato(bean.getCanILSupe())){
						det.setCantCostIndirecto(Long.valueOf(valorCantidad(bean.getCanILSupe())));	
					}						
					if(validarDato(bean.getCostILSupe())){
						det.setCostoIndirecto(new BigDecimal(bean.getCostILSupe()));  	
					}		
					det.setUsuarioActualizacion(bean.getUsuario()); 
					det.setTerminalActualizacion(bean.getTerminal()); 
					det.setFechaActualizacion(FechaUtil.obtenerFechaActual()); 
					formato14CDDao.actualizarFiseFormato14CD(det); 		
					/**Actualizando el primer detalle Urbana Lima -Gestor**/					
					idDet = new FiseFormato14CDPK();		
					idDet.setCodEmpresa(bean.getCodEmpresa());
					idDet.setAnoPresentacion(Long.valueOf(bean.getAnioPres())); 
					idDet.setMesPresentacion(Long.valueOf(bean.getMesPres())); 
					idDet.setAnoInicioVigencia(Long.valueOf(bean.getAnoIniVigencia())); 
					idDet.setAnoFinVigencia(Long.valueOf(bean.getAnoFinVigencia())); 	
					idDet.setEtapa(bean.getEtapa()); 
					idDet.setIdTipPersonal(FiseConstants.TIPO_PERSONAL_GESTOR_COD);
					idDet.setIdZonaBenef(FiseConstants.ZONABENEF_LIMA_COD); 
					det = formato14CDDao.obtenerFiseFormato14CD(idDet);			
					if(validarDato(bean.getCanILGest())){
						det.setCantCostIndirecto(Long.valueOf(valorCantidad(bean.getCanILGest()))); 	
					}						
					if(validarDato(bean.getCostILGest())){
						det.setCostoIndirecto(new BigDecimal(bean.getCostILGest()));  	
					}	
					det.setUsuarioActualizacion(bean.getUsuario()); 
					det.setTerminalActualizacion(bean.getTerminal()); 
					det.setFechaActualizacion(FechaUtil.obtenerFechaActual()); 
					formato14CDDao.actualizarFiseFormato14CD(det); 		
					/**Actualizando el primer detalle Urbana Lima -Asistente/Auxiliar**/					
					idDet = new FiseFormato14CDPK();		
					idDet.setCodEmpresa(bean.getCodEmpresa());
					idDet.setAnoPresentacion(Long.valueOf(bean.getAnioPres())); 
					idDet.setMesPresentacion(Long.valueOf(bean.getMesPres())); 
					idDet.setAnoInicioVigencia(Long.valueOf(bean.getAnoIniVigencia())); 
					idDet.setAnoFinVigencia(Long.valueOf(bean.getAnoFinVigencia())); 	
					idDet.setEtapa(bean.getEtapa()); 
					idDet.setIdTipPersonal(FiseConstants.TIPO_PERSONAL_ASISTENTE_COD);
					idDet.setIdZonaBenef(FiseConstants.ZONABENEF_LIMA_COD);
					det = formato14CDDao.obtenerFiseFormato14CD(idDet);		
					if(validarDato(bean.getCanILAsist())){
						det.setCantCostIndirecto(Long.valueOf(valorCantidad(bean.getCanILAsist())));	
					}						
					if(validarDato(bean.getCostILAsist())){
						det.setCostoIndirecto(new BigDecimal(bean.getCostILAsist()));  	
					}	 
					det.setUsuarioActualizacion(bean.getUsuario()); 
					det.setTerminalActualizacion(bean.getTerminal()); 
					det.setFechaActualizacion(FechaUtil.obtenerFechaActual()); 
					formato14CDDao.actualizarFiseFormato14CD(det); 		
				}//fin de validacion empresa								
				
			}else if(FormatoUtil.isNotBlank(bean.getFlagCosto()) && 
            		FiseConstants.COSTO_D_I_F14C.equals(bean.getFlagCosto())){
				/**AMBOS DIRECTO E INDIRECTO RURAL*/
				/**Actualizando el primer detalle Rural-Coordinador**/			
				idDet = new FiseFormato14CDPK();		
				idDet.setCodEmpresa(bean.getCodEmpresa());
				idDet.setAnoPresentacion(Long.valueOf(bean.getAnioPres())); 
				idDet.setMesPresentacion(Long.valueOf(bean.getMesPres())); 
				idDet.setAnoInicioVigencia(Long.valueOf(bean.getAnoIniVigencia())); 
				idDet.setAnoFinVigencia(Long.valueOf(bean.getAnoFinVigencia())); 	
				idDet.setEtapa(bean.getEtapa()); 
				idDet.setIdTipPersonal(FiseConstants.TIPO_PERSONAL_CORDINADOR_COD);
				idDet.setIdZonaBenef(FiseConstants.ZONABENEF_RURAL_COD); 		
				det = formato14CDDao.obtenerFiseFormato14CD(idDet);
				if(validarDato(bean.getCanDRCoord())){
	            	det.setCantCostDirecto(Long.valueOf(valorCantidad(bean.getCanDRCoord())));	
				}	            	
            	if(validarDato(bean.getCanIRCoord())){
            		det.setCantCostIndirecto(Long.valueOf(valorCantidad(bean.getCanIRCoord()))); 	
				}					
				if(validarDato(bean.getCostDRCoord())){ 
					det.setCostoDirecto(new BigDecimal(bean.getCostDRCoord()));	
				}				
				if(validarDato(bean.getCostIRCoord())){
					det.setCostoIndirecto(new BigDecimal(bean.getCostIRCoord()));  	
				}				
				det.setUsuarioActualizacion(bean.getUsuario()); 
				det.setTerminalActualizacion(bean.getTerminal()); 
				det.setFechaActualizacion(FechaUtil.obtenerFechaActual()); 
				formato14CDDao.actualizarFiseFormato14CD(det); 				
				/**Actualizando el primer detalle Rural-Supervisor**/		
				idDet = new FiseFormato14CDPK();		
				idDet.setCodEmpresa(bean.getCodEmpresa());
				idDet.setAnoPresentacion(Long.valueOf(bean.getAnioPres())); 
				idDet.setMesPresentacion(Long.valueOf(bean.getMesPres())); 
				idDet.setAnoInicioVigencia(Long.valueOf(bean.getAnoIniVigencia())); 
				idDet.setAnoFinVigencia(Long.valueOf(bean.getAnoFinVigencia())); 	
				idDet.setEtapa(bean.getEtapa()); 
				idDet.setIdTipPersonal(FiseConstants.TIPO_PERSONAL_SUPERVISOR_COD);
				idDet.setIdZonaBenef(FiseConstants.ZONABENEF_RURAL_COD); 		
				det = formato14CDDao.obtenerFiseFormato14CD(idDet);
				if(validarDato(bean.getCanDRSupe())){
					det.setCantCostDirecto(Long.valueOf(valorCantidad(bean.getCanDRSupe())));
				}					
				if(validarDato(bean.getCanIRSupe())){
					det.setCantCostIndirecto(Long.valueOf(valorCantidad(bean.getCanIRSupe()))); 	
				}					
				if(validarDato(bean.getCostDRSupe())){
					det.setCostoDirecto(new BigDecimal(bean.getCostDRSupe())); 	
				}					
				if(validarDato(bean.getCostIRSupe())){
					det.setCostoIndirecto(new BigDecimal(bean.getCostIRSupe()));  	
				}		
				det.setUsuarioActualizacion(bean.getUsuario()); 
				det.setTerminalActualizacion(bean.getTerminal()); 
				det.setFechaActualizacion(FechaUtil.obtenerFechaActual()); 
				formato14CDDao.actualizarFiseFormato14CD(det);
				/**Actualizando el primer detalle Rural-Gestor**/			
				idDet = new FiseFormato14CDPK();		
				idDet.setCodEmpresa(bean.getCodEmpresa());
				idDet.setAnoPresentacion(Long.valueOf(bean.getAnioPres())); 
				idDet.setMesPresentacion(Long.valueOf(bean.getMesPres())); 
				idDet.setAnoInicioVigencia(Long.valueOf(bean.getAnoIniVigencia())); 
				idDet.setAnoFinVigencia(Long.valueOf(bean.getAnoFinVigencia())); 	
				idDet.setEtapa(bean.getEtapa()); 
				idDet.setIdTipPersonal(FiseConstants.TIPO_PERSONAL_GESTOR_COD);
				idDet.setIdZonaBenef(FiseConstants.ZONABENEF_RURAL_COD); 		
				det = formato14CDDao.obtenerFiseFormato14CD(idDet);
				if(validarDato(bean.getCanDRGest())){
					det.setCantCostDirecto(Long.valueOf(valorCantidad(bean.getCanDRGest())));	
				}					
				if(validarDato(bean.getCanIRGest())){
					det.setCantCostIndirecto(Long.valueOf(valorCantidad(bean.getCanIRGest()))); 
				}					
				if(validarDato(bean.getCostDRGest())){
					det.setCostoDirecto(new BigDecimal(bean.getCostDRGest()));	
				}					
				if(validarDato(bean.getCostIRGest())){
					det.setCostoIndirecto(new BigDecimal(bean.getCostIRGest()));  		
				}	
				det.setUsuarioActualizacion(bean.getUsuario()); 
				det.setTerminalActualizacion(bean.getTerminal()); 
				det.setFechaActualizacion(FechaUtil.obtenerFechaActual()); 
				formato14CDDao.actualizarFiseFormato14CD(det);
				/**Actualizando  el primer detalle Rural-Asistente/Auxiliar**/			
				idDet = new FiseFormato14CDPK();		
				idDet.setCodEmpresa(bean.getCodEmpresa());
				idDet.setAnoPresentacion(Long.valueOf(bean.getAnioPres())); 
				idDet.setMesPresentacion(Long.valueOf(bean.getMesPres())); 
				idDet.setAnoInicioVigencia(Long.valueOf(bean.getAnoIniVigencia())); 
				idDet.setAnoFinVigencia(Long.valueOf(bean.getAnoFinVigencia())); 	
				idDet.setEtapa(bean.getEtapa()); 
				idDet.setIdTipPersonal(FiseConstants.TIPO_PERSONAL_ASISTENTE_COD);
				idDet.setIdZonaBenef(FiseConstants.ZONABENEF_RURAL_COD); 		
				det = formato14CDDao.obtenerFiseFormato14CD(idDet);
				if(validarDato(bean.getCanDRAsist())){
					det.setCantCostDirecto(Long.valueOf(valorCantidad(bean.getCanDRAsist())));	
				}					
				if(validarDato(bean.getCanIRAsist())){
					det.setCantCostIndirecto(Long.valueOf(valorCantidad(bean.getCanIRAsist())));	
				}					
				if(validarDato(bean.getCostDRAsist())){ 
					det.setCostoDirecto(new BigDecimal(bean.getCostDRAsist())); 	
				}					
				if(validarDato(bean.getCostIRAsist())){
					det.setCostoIndirecto(new BigDecimal(bean.getCostIRAsist()));  
				}	
				det.setUsuarioActualizacion(bean.getUsuario()); 
				det.setTerminalActualizacion(bean.getTerminal()); 
				det.setFechaActualizacion(FechaUtil.obtenerFechaActual()); 
				formato14CDDao.actualizarFiseFormato14CD(det);
				/***PROVINCIA***/
				/**Actualizando el primer detalle Urbano Provincia-Coordinador**/			
				idDet = new FiseFormato14CDPK();		
				idDet.setCodEmpresa(bean.getCodEmpresa());
				idDet.setAnoPresentacion(Long.valueOf(bean.getAnioPres())); 
				idDet.setMesPresentacion(Long.valueOf(bean.getMesPres())); 
				idDet.setAnoInicioVigencia(Long.valueOf(bean.getAnoIniVigencia())); 
				idDet.setAnoFinVigencia(Long.valueOf(bean.getAnoFinVigencia())); 	
				idDet.setEtapa(bean.getEtapa()); 
				idDet.setIdTipPersonal(FiseConstants.TIPO_PERSONAL_CORDINADOR_COD);
				idDet.setIdZonaBenef(FiseConstants.ZONABENEF_PROVINCIA_COD); 		
				det = formato14CDDao.obtenerFiseFormato14CD(idDet);
				if(validarDato(bean.getCanDPCoord())){
					det.setCantCostDirecto(Long.valueOf(valorCantidad(bean.getCanDPCoord())));	
				}					
				if(validarDato(bean.getCanIPCoord())){
					det.setCantCostIndirecto(Long.valueOf(valorCantidad(bean.getCanIPCoord())));	
				}					
				if(validarDato(bean.getCostDPCoord())){
					det.setCostoDirecto(new BigDecimal(bean.getCostDPCoord())); 	
				}					
				if(validarDato(bean.getCostIPCoord())){
					det.setCostoIndirecto(new BigDecimal(bean.getCostIPCoord()));  	
				}
				det.setUsuarioActualizacion(bean.getUsuario()); 
				det.setTerminalActualizacion(bean.getTerminal()); 
				det.setFechaActualizacion(FechaUtil.obtenerFechaActual()); 	
				formato14CDDao.actualizarFiseFormato14CD(det); 
				/**Actualizando el primer detalle Urbano Provincias-Supervisor**/			
				idDet = new FiseFormato14CDPK();		
				idDet.setCodEmpresa(bean.getCodEmpresa());
				idDet.setAnoPresentacion(Long.valueOf(bean.getAnioPres())); 
				idDet.setMesPresentacion(Long.valueOf(bean.getMesPres())); 
				idDet.setAnoInicioVigencia(Long.valueOf(bean.getAnoIniVigencia())); 
				idDet.setAnoFinVigencia(Long.valueOf(bean.getAnoFinVigencia())); 	
				idDet.setEtapa(bean.getEtapa()); 
				idDet.setIdTipPersonal(FiseConstants.TIPO_PERSONAL_SUPERVISOR_COD);
				idDet.setIdZonaBenef(FiseConstants.ZONABENEF_PROVINCIA_COD); 		
				det = formato14CDDao.obtenerFiseFormato14CD(idDet);
				if(validarDato(bean.getCanDPSupe())){
					det.setCantCostDirecto(Long.valueOf(valorCantidad(bean.getCanDPSupe())));
				}					
				if(validarDato(bean.getCanIPSupe())){
					det.setCantCostIndirecto(Long.valueOf(valorCantidad(bean.getCanIPSupe()))); 	
				}					
				if(validarDato(bean.getCostDPSupe())){
					det.setCostoDirecto(new BigDecimal(bean.getCostDPSupe())); 	
				}					
				if(validarDato(bean.getCostIPSupe())){
					det.setCostoIndirecto(new BigDecimal(bean.getCostIPSupe()));  	
				}	
				det.setUsuarioActualizacion(bean.getUsuario()); 
				det.setTerminalActualizacion(bean.getTerminal()); 
				det.setFechaActualizacion(FechaUtil.obtenerFechaActual()); 	
				formato14CDDao.actualizarFiseFormato14CD(det);			
				/**Actualizando el primer detalle Urbana Provincias-Gestor**/			
				idDet = new FiseFormato14CDPK();		
				idDet.setCodEmpresa(bean.getCodEmpresa());
				idDet.setAnoPresentacion(Long.valueOf(bean.getAnioPres())); 
				idDet.setMesPresentacion(Long.valueOf(bean.getMesPres())); 
				idDet.setAnoInicioVigencia(Long.valueOf(bean.getAnoIniVigencia())); 
				idDet.setAnoFinVigencia(Long.valueOf(bean.getAnoFinVigencia())); 	
				idDet.setEtapa(bean.getEtapa()); 
				idDet.setIdTipPersonal(FiseConstants.TIPO_PERSONAL_GESTOR_COD);
				idDet.setIdZonaBenef(FiseConstants.ZONABENEF_PROVINCIA_COD); 		
				det = formato14CDDao.obtenerFiseFormato14CD(idDet);
				if(validarDato(bean.getCanDPGest())){
					det.setCantCostDirecto(Long.valueOf(valorCantidad(bean.getCanDPGest())));	
				}					
				if(validarDato(bean.getCanIPGest())){
					det.setCantCostIndirecto(Long.valueOf(valorCantidad(bean.getCanIPGest()))); 	
				}					
				if(validarDato(bean.getCostDPGest())){
					det.setCostoDirecto(new BigDecimal(bean.getCostDPGest())); 	
				}					
				if(validarDato(bean.getCostIPGest())){
					det.setCostoIndirecto(new BigDecimal(bean.getCostIPGest()));  
				}	 
				det.setUsuarioActualizacion(bean.getUsuario()); 
				det.setTerminalActualizacion(bean.getTerminal()); 
				det.setFechaActualizacion(FechaUtil.obtenerFechaActual()); 
				formato14CDDao.actualizarFiseFormato14CD(det);			
				/**Actualizando el primer detalle Urbano Provincias -Asistente/Auxiliar**/			
				idDet = new FiseFormato14CDPK();		
				idDet.setCodEmpresa(bean.getCodEmpresa());
				idDet.setAnoPresentacion(Long.valueOf(bean.getAnioPres())); 
				idDet.setMesPresentacion(Long.valueOf(bean.getMesPres())); 
				idDet.setAnoInicioVigencia(Long.valueOf(bean.getAnoIniVigencia())); 
				idDet.setAnoFinVigencia(Long.valueOf(bean.getAnoFinVigencia())); 	
				idDet.setEtapa(bean.getEtapa()); 
				idDet.setIdTipPersonal(FiseConstants.TIPO_PERSONAL_ASISTENTE_COD);
				idDet.setIdZonaBenef(FiseConstants.ZONABENEF_PROVINCIA_COD); 		
				det = formato14CDDao.obtenerFiseFormato14CD(idDet);
				if(validarDato(bean.getCanDPAsist())){
					det.setCantCostDirecto(Long.valueOf(valorCantidad(bean.getCanDPAsist())));	
				}					
				if(validarDato(bean.getCanIPAsist())){
					det.setCantCostIndirecto(Long.valueOf(valorCantidad(bean.getCanIPAsist())));	
				}					
				if(validarDato(bean.getCostDPAsist())){
					det.setCostoDirecto(new BigDecimal(bean.getCostDPAsist())); 	
				}					
				if(validarDato(bean.getCostIPAsist())){
					det.setCostoIndirecto(new BigDecimal(bean.getCostIPAsist()));	
				}	
				det.setUsuarioActualizacion(bean.getUsuario()); 
				det.setTerminalActualizacion(bean.getTerminal()); 
				det.setFechaActualizacion(FechaUtil.obtenerFechaActual()); 
				formato14CDDao.actualizarFiseFormato14CD(det);				
				/**LIMA**/
				if(FiseConstants.COD_EMPRESA_EDELNOR.equals(bean.getCodEmpresa()) || 
						FiseConstants.COD_EMPRESA_LUZ_SUR.equals(bean.getCodEmpresa()))
				{
					/**Actualizando el primer detalle Urbano Lima-Coordinador**/			
					idDet = new FiseFormato14CDPK();		
					idDet.setCodEmpresa(bean.getCodEmpresa());
					idDet.setAnoPresentacion(Long.valueOf(bean.getAnioPres())); 
					idDet.setMesPresentacion(Long.valueOf(bean.getMesPres())); 
					idDet.setAnoInicioVigencia(Long.valueOf(bean.getAnoIniVigencia())); 
					idDet.setAnoFinVigencia(Long.valueOf(bean.getAnoFinVigencia())); 	
					idDet.setEtapa(bean.getEtapa()); 
					idDet.setIdTipPersonal(FiseConstants.TIPO_PERSONAL_CORDINADOR_COD);
					idDet.setIdZonaBenef(FiseConstants.ZONABENEF_LIMA_COD); 		
					det = formato14CDDao.obtenerFiseFormato14CD(idDet);
					if(validarDato(bean.getCanDLCoord())){
						det.setCantCostDirecto(Long.valueOf(valorCantidad(bean.getCanDLCoord())));		
					}						
					if(validarDato(bean.getCanILCoord())){
						det.setCantCostIndirecto(Long.valueOf(valorCantidad(bean.getCanILCoord())));	
					}						
					if(validarDato(bean.getCostDLCoord())){
						det.setCostoDirecto(new BigDecimal(bean.getCostDLCoord())); 	
					}						
					if(validarDato(bean.getCostILCoord())){
						det.setCostoIndirecto(new BigDecimal(bean.getCostILCoord()));  	
					}		
					det.setUsuarioActualizacion(bean.getUsuario()); 
					det.setTerminalActualizacion(bean.getTerminal()); 
					det.setFechaActualizacion(FechaUtil.obtenerFechaActual()); 	
					formato14CDDao.actualizarFiseFormato14CD(det);	
					/**Actualizando el primer detalle Urbana Lima -Supervisor**/			
					idDet = new FiseFormato14CDPK();		
					idDet.setCodEmpresa(bean.getCodEmpresa());
					idDet.setAnoPresentacion(Long.valueOf(bean.getAnioPres())); 
					idDet.setMesPresentacion(Long.valueOf(bean.getMesPres())); 
					idDet.setAnoInicioVigencia(Long.valueOf(bean.getAnoIniVigencia())); 
					idDet.setAnoFinVigencia(Long.valueOf(bean.getAnoFinVigencia())); 	
					idDet.setEtapa(bean.getEtapa()); 
					idDet.setIdTipPersonal(FiseConstants.TIPO_PERSONAL_SUPERVISOR_COD);
					idDet.setIdZonaBenef(FiseConstants.ZONABENEF_LIMA_COD); 		
					det = formato14CDDao.obtenerFiseFormato14CD(idDet);
					if(validarDato(bean.getCanDLSupe())){
						det.setCantCostDirecto(Long.valueOf(valorCantidad(bean.getCanDLSupe())));	
					}						
					if(validarDato(bean.getCanILSupe())){
						det.setCantCostIndirecto(Long.valueOf(valorCantidad(bean.getCanILSupe()))); 
					}						
					if(validarDato(bean.getCostDLSupe())){
						det.setCostoDirecto(new BigDecimal(bean.getCostDLSupe())); 	
					}						
					if(validarDato(bean.getCostILSupe())){
						det.setCostoIndirecto(new BigDecimal(bean.getCostILSupe()));  	
					}		
					det.setUsuarioActualizacion(bean.getUsuario()); 
					det.setTerminalActualizacion(bean.getTerminal()); 
					det.setFechaActualizacion(FechaUtil.obtenerFechaActual()); 
					formato14CDDao.actualizarFiseFormato14CD(det);
					/**Actualizando el primer detalle Urbana Lima -Gestor**/			
					idDet = new FiseFormato14CDPK();		
					idDet.setCodEmpresa(bean.getCodEmpresa());
					idDet.setAnoPresentacion(Long.valueOf(bean.getAnioPres())); 
					idDet.setMesPresentacion(Long.valueOf(bean.getMesPres())); 
					idDet.setAnoInicioVigencia(Long.valueOf(bean.getAnoIniVigencia())); 
					idDet.setAnoFinVigencia(Long.valueOf(bean.getAnoFinVigencia())); 	
					idDet.setEtapa(bean.getEtapa()); 
					idDet.setIdTipPersonal(FiseConstants.TIPO_PERSONAL_GESTOR_COD);
					idDet.setIdZonaBenef(FiseConstants.ZONABENEF_LIMA_COD); 		
					det = formato14CDDao.obtenerFiseFormato14CD(idDet);
					if(validarDato(bean.getCanDLGest())){
						det.setCantCostDirecto(Long.valueOf(valorCantidad(bean.getCanDLGest())));	
					}						
					if(validarDato(bean.getCanILGest())){
						det.setCantCostIndirecto(Long.valueOf(valorCantidad(bean.getCanILGest()))); 	
					}						
					if(validarDato(bean.getCostDLGest())){
						det.setCostoDirecto(new BigDecimal(bean.getCostDLGest())); 	
					}						
					if(validarDato(bean.getCostILGest())){
						det.setCostoIndirecto(new BigDecimal(bean.getCostILGest())); 	
					}			
					det.setUsuarioActualizacion(bean.getUsuario()); 
					det.setTerminalActualizacion(bean.getTerminal()); 
					det.setFechaActualizacion(FechaUtil.obtenerFechaActual()); 
					formato14CDDao.actualizarFiseFormato14CD(det);
					/**Actualizando el primer detalle Urbana Lima -Asistente/Auxiliar**/			
					idDet = new FiseFormato14CDPK();		
					idDet.setCodEmpresa(bean.getCodEmpresa());
					idDet.setAnoPresentacion(Long.valueOf(bean.getAnioPres())); 
					idDet.setMesPresentacion(Long.valueOf(bean.getMesPres())); 
					idDet.setAnoInicioVigencia(Long.valueOf(bean.getAnoIniVigencia())); 
					idDet.setAnoFinVigencia(Long.valueOf(bean.getAnoFinVigencia())); 	
					idDet.setEtapa(bean.getEtapa()); 
					idDet.setIdTipPersonal(FiseConstants.TIPO_PERSONAL_ASISTENTE_COD);
					idDet.setIdZonaBenef(FiseConstants.ZONABENEF_LIMA_COD); 		
					det = formato14CDDao.obtenerFiseFormato14CD(idDet);
					if(validarDato(bean.getCanDLAsist())){
						det.setCantCostDirecto(Long.valueOf(valorCantidad(bean.getCanDLAsist())));	
					}						
					if(validarDato(bean.getCanILAsist())){
						det.setCantCostIndirecto(Long.valueOf(valorCantidad(bean.getCanILAsist())));	
					}						
					if(validarDato(bean.getCostDLAsist())){
						det.setCostoDirecto(new BigDecimal(bean.getCostDLAsist())); 	
					}						
					if(validarDato(bean.getCostILAsist())){
						det.setCostoIndirecto(new BigDecimal(bean.getCostILAsist()));  	
					}		
					det.setUsuarioActualizacion(bean.getUsuario()); 
					det.setTerminalActualizacion(bean.getTerminal()); 
					det.setFechaActualizacion(FechaUtil.obtenerFechaActual()); 	
					formato14CDDao.actualizarFiseFormato14CD(det);
				}			
			}		
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
	
	private int valorCantidad(String valor){
		return new BigDecimal(valor).byteValue();
	}
	
	@Override
	@Transactional(propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
	public String actualizarDatosEnvioFormato14C(Formato14CBean bean) throws Exception{
		FiseFormato14CC cab = null;
		FiseFormato14CCPK idCab = null;		
		String valor ="1";
		try {
			 /*Actualizando en la cabecera*/		
			idCab = new FiseFormato14CCPK();
			//PK
			idCab.setCodEmpresa(bean.getCodEmpresa());
			idCab.setAnoPresentacion(Long.valueOf(bean.getAnioPres())); 
			idCab.setMesPresentacion(Long.valueOf(bean.getMesPres())); 
			idCab.setAnoInicioVigencia(Long.valueOf(bean.getAnoIniVigencia())); 
			idCab.setAnoFinVigencia(Long.valueOf(bean.getAnoFinVigencia())); 
			idCab.setEtapa(bean.getEtapa()); 
			//FIN PK
			cab = formato14CCDao.obtenerFormato14CC(idCab); 
			cab.setFechaEnvioDefinitivo(FechaUtil.obtenerFechaActual());
			cab.setUsuarioActualizacion(bean.getUsuario());
			cab.setTerminalActualizacion(bean.getTerminal());
			cab.setFechaActualizacion(FechaUtil.obtenerFechaActual());
			formato14CCDao.actualizarFiseFormato14CC(cab); 
		} catch (Exception e) {
			valor = "0";
			logger.info("Error al actualizar los datos de envio: "+e); 
		}finally{
			if(cab!=null){
				cab=null;
			}
			if(idCab!=null){
				idCab=null;
			}	
		}
		return valor;
	}
	
	@Override
	@Transactional(propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
	public String eliminarDatosFormato14C(Formato14CBean bean) throws Exception{
		String valor = "1";
		FiseFormato14CC cab = null;
		FiseFormato14CCPK idCab = null;
		FiseFormato14CD det = null;
		FiseFormato14CDPK idDet = null;
		FiseFormato14CDObPK idObs =null;
		FiseFormato14CDOb obs = null;
		try {
			/**Cabecera del formato*/
			idCab = new FiseFormato14CCPK();			
			idCab.setCodEmpresa(bean.getCodEmpresa());
			idCab.setAnoPresentacion(Long.valueOf(bean.getAnioPres())); 
			idCab.setMesPresentacion(Long.valueOf(bean.getMesPres())); 
			idCab.setAnoInicioVigencia(Long.valueOf(bean.getAnoIniVigencia())); 
			idCab.setAnoFinVigencia(Long.valueOf(bean.getAnoFinVigencia())); 
			idCab.setEtapa(bean.getEtapa()); 
			/*Obtengo el objeto en session*/
			cab = formato14CCDao.obtenerFormato14CC(idCab); 
		    /***PREPARANDO PARA ELIMINAR EL DETALLE***/					
			List<FiseFormato14CD> listDetalle = formato14CDDao.buscarFiseFormato14CD(bean.getCodEmpresa(), 
					Long.valueOf(bean.getAnioPres()), Long.valueOf(bean.getMesPres()),
					Long.valueOf(bean.getAnoIniVigencia()), Long.valueOf(bean.getAnoFinVigencia()),
					bean.getEtapa());	
			for(FiseFormato14CD d : listDetalle){			
				idDet = new FiseFormato14CDPK();		
				idDet.setCodEmpresa(d.getId().getCodEmpresa());
				idDet.setAnoPresentacion(d.getId().getAnoPresentacion()); 
				idDet.setMesPresentacion(d.getId().getMesPresentacion()); 
				idDet.setAnoInicioVigencia(d.getId().getAnoInicioVigencia()); 
				idDet.setAnoFinVigencia(d.getId().getAnoFinVigencia()); 	
				idDet.setEtapa(d.getId().getEtapa()); 
				idDet.setIdTipPersonal(d.getId().getIdTipPersonal());
				idDet.setIdZonaBenef(d.getId().getIdZonaBenef()); 
				/*Obtengo el objeto en session*/
				det = formato14CDDao.obtenerFiseFormato14CD(idDet);				
			     /***PREPARANDO PARA ELIMINAR LAS OBSERVACIONES***/					
				List<FiseFormato14CDOb>	listObs = formato14CDObDao.buscarFiseFormato14CDOb(d.getId().getCodEmpresa(),
						d.getId().getAnoPresentacion(), d.getId().getMesPresentacion(),
						d.getId().getAnoInicioVigencia(), d.getId().getAnoFinVigencia(), 
						d.getId().getEtapa(), d.getId().getIdZonaBenef(),d.getId().getIdTipPersonal()); 		
				logger.info("Tamaño de la lista de observaciones:  "+listObs.size()); 
				for(FiseFormato14CDOb o:listObs){
					idObs = new FiseFormato14CDObPK();	
					idObs.setCodEmpresa(o.getId().getCodEmpresa()); 
					idObs.setAnoPresentacion(o.getId().getAnoPresentacion());
					idObs.setMesPresentacion(o.getId().getMesPresentacion()); 
					idObs.setAnoInicioVigencia(o.getId().getAnoInicioVigencia()); 
					idObs.setAnoFinVigencia(o.getId().getAnoFinVigencia()); 	
					idObs.setEtapa(o.getId().getEtapa()); 				
					idObs.setIdZonaBenef(o.getId().getIdZonaBenef()); 
					idObs.setIdTipPersonal(o.getId().getIdTipPersonal()); 
					idObs.setItemObservacion(o.getId().getItemObservacion()); 
					if(idObs!=null){
						/*Obtengo el objeto en session*/
						obs = formato14CDObDao.obtenerFiseFormato14CDOb(idObs);
						logger.info("objeto de observaciones en session"+obs); 	
					}					
					/****Primero elimino las observaciones*****/
					if(obs!=null){
					   formato14CDObDao.eliminarFiseFormato14CDOb(obs); 	
					}					
				}//fin del for de observaciones
				/****Segundo  elimino los detalles*****/
				formato14CDDao.eliminarFiseFormato14CD(det); 
			}//fin del for de detalle
			/****Tercero  elimino los detalles*****/
			formato14CCDao.eliminarFiseFormato14CC(cab); 
		} catch (Exception e) {
			logger.info("Error al eliminar el formato 14C:  "+e);
			e.printStackTrace();
			valor = "0";
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
			if(obs!=null){
				obs=null;
			}
			if(idObs!=null){
				idObs=null;
			}
		}
		return valor;
	}
	
	@Override
	@Transactional
	public FiseFormato14CC obtenerFiseFormato14CC(Formato14CBean bean) throws Exception{
		FiseFormato14CC formato = null;
		FiseFormato14CCPK idCab = null;
		try {
			idCab = new FiseFormato14CCPK();			
			idCab.setCodEmpresa(bean.getCodEmpresa());
			idCab.setAnoPresentacion(Long.valueOf(bean.getAnioPres())); 
			idCab.setMesPresentacion(Long.valueOf(bean.getMesPres())); 
			idCab.setAnoInicioVigencia(Long.valueOf(bean.getAnoIniVigencia())); 
			idCab.setAnoFinVigencia(Long.valueOf(bean.getAnoFinVigencia())); 
			idCab.setEtapa(bean.getEtapa()); 
			/*Obtengo el objeto en session*/			
			formato = formato14CCDao.obtenerFormato14CC(idCab);		
			formato.setListaDetalle14cDs(formato.getFiseFormato14cDs()); 
			logger.info("Detalle de la lista "+formato.getListaDetalle14cDs().size()); 
		} catch (Exception e) {
			logger.info("Error al obtener el formato 14C:  "+e); 
		}finally{
			if(idCab!=null){
				idCab=null;
			}	
		}
		return formato;
	}	
	
	@Override
	@Transactional
	public List<FiseFormato14CDOb> listaObservacionesF14C(FiseFormato14CD d) throws Exception{	
	  logger.info("tamaño del codigo de empresa al validacion:  "+d.getId().getCodEmpresa().length()); 	
	  return formato14CDObDao.buscarFiseFormato14CDOb(d.getId().getCodEmpresa(),
				d.getId().getAnoPresentacion(), d.getId().getMesPresentacion(),
				d.getId().getAnoInicioVigencia(), d.getId().getAnoFinVigencia(), 
				d.getId().getEtapa(), d.getId().getIdZonaBenef(),d.getId().getIdTipPersonal());
	}
	
	@Override
	@Transactional
	public List<FiseFormato14CC> buscarFiseFormato14CC(String codEmpresa,
			long anioDesde, long anioHasta, long mesDesde, long mesHasta,
			String etapa) throws Exception {
		List<FiseFormato14CC> lista = new ArrayList<FiseFormato14CC>();
		List<FiseFormato14CC> listDatos = formato14CCDao.buscarFiseFormato14CC(codEmpresa, 
				anioDesde, anioHasta, mesDesde, mesHasta, etapa); 
		for(FiseFormato14CC f :listDatos){
			FiseFormato14CC f14c = new	FiseFormato14CC();
			if(f.getFiseGrupoInformacion()!=null){
				f.getFiseGrupoInformacion().getDescripcion();	
			}			
			f14c =f;
			lista.add(f14c);
		}
		return lista;
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
		//logger.info("Tamaño de la lista de detalle:  "+f.getFiseFormato14cDs().size()); 
		/**cabecera y pk**/
		bean.setCodEmpresa(f.getId().getCodEmpresa()); 
		bean.setAnioPres(""+f.getId().getAnoPresentacion()); 
		bean.setMesPres(""+f.getId().getMesPresentacion()); 
		bean.setAnoIniVigencia(""+f.getId().getAnoInicioVigencia()); 
		bean.setAnoFinVigencia(""+f.getId().getAnoFinVigencia()); 
		bean.setEtapa(f.getId().getEtapa());  
		if(f.getFiseGrupoInformacion()!=null && 
				f.getFiseGrupoInformacion().getDescripcion()!=null){
			bean.setGrupoInformacion(f.getFiseGrupoInformacion().getDescripcion());	
		}else{
			bean.setGrupoInformacion("---");	
		}
		if(f.getFechaEnvioDefinitivo()!=null){
			bean.setEstado(FiseConstants.ESTADO_ENVIADO_F14C);
		}else{
			bean.setEstado(FiseConstants.ESTADO_POR_ENVIAR_F14C);
		}		
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
	
	
	/*****************REPORTES*****************/
	@Override
	public Formato14CReportBean estructurarFormato14CBeanByFiseFormato14C(
			FiseFormato14CC formato) throws Exception {	
		
		Formato14CReportBean bean = new Formato14CReportBean();

		bean.setAnioPresent(formato.getId().getAnoPresentacion());
		bean.setAnioInicioVigencia(formato.getId().getAnoInicioVigencia());
		bean.setAnioFinVigencia(formato.getId().getAnoFinVigencia());
		if(formato.getNombreSede()!=null){
			bean.setNombreSede(formato.getNombreSede());	
		}else{
			bean.setNombreSede("---");	
		}
		
		// ------- sumatoria por total de las 3 zonas-------------
		long totalCantBeneficiarios = (0);
		long totalCantCoordinador = (0);
		long totalCantSupervisor = (0);
		long totalCantGestor = (0);
		long totalCantAsist = (0);

		BigDecimal totalSumaCoordinador = new BigDecimal(0);
		BigDecimal totalSumaSupervisor = new BigDecimal(0);
		BigDecimal totalSumaGestor = new BigDecimal(0);
		BigDecimal totalSumaAsist = new BigDecimal(0);

		// ------- sumatoria por Costo Total por Gestion-------------
		long totalCantCDTotalR = (0);
		BigDecimal totalSumaCDTotalR = new BigDecimal(0);
		long totalCantCITotalR = (0);
		BigDecimal totalSumaCITotalR = new BigDecimal(0);
		BigDecimal totalSumaCostoTotalR = new BigDecimal(0);

		long totalCantCDTotalP = (0);
		BigDecimal totalSumaCDTotalP = new BigDecimal(0);
		long totalCantCITotalP = (0);
		BigDecimal totalSumaCITotalP = new BigDecimal(0);
		BigDecimal totalSumaCostoTotalP = new BigDecimal(0);

		long totalCantCDTotalL = (0);
		BigDecimal totalSumaCDTotalL = new BigDecimal(0);
		long totalCantCITotalL = (0);
		BigDecimal totalSumaCITotalL = new BigDecimal(0);
		BigDecimal totalSumaCostoTotalL = new BigDecimal(0);

		// BigDecimal totalSumaCostoTotalT= new BigDecimal(0);
		BigDecimal SumaCostoPromedioMensual = new BigDecimal(0);

		for (FiseFormato14CD d : formato.getListaDetalle14cDs()) {
			if (FiseConstants.ZONABENEF_RURAL_COD == d.getId().getIdZonaBenef()) {
				bean.setNroBenefCantR(formato
						.getNumBenefEmpPerAntRural() != null ? formato
						.getNumBenefEmpPerAntRural() : new Long(0));
				bean.setCostoPromMensCDR(formato
						.getCostoPromMenRural() != null ? formato
						.getCostoPromMenRural() : new BigDecimal(0));
				bean.setCostoPromMensCTR(formato
						.getCostoPromMenRural() != null ? formato
						.getCostoPromMenRural() : new BigDecimal(0));
				if (FiseConstants.TIPO_PERSONAL_CORDINADOR_COD == d.getId().getIdTipPersonal()) {
					bean.setCostoCoordCantCDR(d.getCantCostDirecto() != null ? d.getCantCostDirecto() : new Long(0));
					bean.setCostoCoordCDR(d.getCostoDirecto() != null ? d.getCostoDirecto() : new BigDecimal(0));
					bean.setCostoCoordCantCIR(d.getCantCostIndirecto() != null ? d.getCantCostIndirecto() : new Long(0));
					bean.setCostoCoordCIR(d.getCostoIndirecto() != null ? d.getCostoIndirecto() : new BigDecimal(0));
					BigDecimal costoTotalCoordR = ((bean.getCostoCoordCDR()	.multiply(new BigDecimal(bean.getCostoCoordCantCDR())))).add(
							(bean.getCostoCoordCIR().multiply(new BigDecimal(
													bean.getCostoCoordCantCIR()))));
					bean.setCostoCoordCostoTotR(costoTotalCoordR);

					totalCantCoordinador = totalCantCoordinador
							+ bean.getCostoCoordCantCDR()
							+ bean.getCostoCoordCantCIR();
					totalSumaCoordinador = totalSumaCoordinador
							.add(costoTotalCoordR);

					totalCantCDTotalR = totalCantCDTotalR
							+ bean.getCostoCoordCantCDR();
					totalSumaCDTotalR = totalSumaCDTotalR.add(bean.getCostoCoordCDR());
					totalCantCITotalR = totalCantCITotalR
							+ bean.getCostoCoordCantCIR();
					totalSumaCITotalR = totalSumaCITotalR.add(bean.getCostoCoordCIR());
					totalSumaCostoTotalR = totalSumaCostoTotalR
							.add(costoTotalCoordR);

				} else if (FiseConstants.TIPO_PERSONAL_SUPERVISOR_COD == d.getId()
						.getIdTipPersonal()) {

					bean.setCostoSuperCantCDR(d
							.getCantCostDirecto() != null ? d
							.getCantCostDirecto() : new Long(0));
					bean.setCostoSuperCDR(d.getCostoDirecto() != null ? d
									.getCostoDirecto() : new BigDecimal(0));
					bean.setCostoSuperCantCIR(d
							.getCantCostIndirecto() != null ? d
							.getCantCostIndirecto() : new Long(0));
					bean.setCostoSuperCIR(d.getCostoIndirecto() != null ? d
									.getCostoIndirecto() : new BigDecimal(0));

					BigDecimal costoTotalSupeR = ((bean.getCostoSuperCDR()
							.multiply(new BigDecimal(bean.getCostoSuperCantCDR())))).add(
									(bean.getCostoSuperCIR().multiply(new BigDecimal(
							bean.getCostoSuperCantCIR()))));
					bean.setCostoSuperCostoTotR(costoTotalSupeR);

					totalCantSupervisor = totalCantSupervisor
							+ bean.getCostoSuperCantCDR()
							+ bean.getCostoSuperCantCIR();
					totalSumaSupervisor = totalSumaSupervisor
							.add(costoTotalSupeR);

					totalCantCDTotalR = totalCantCDTotalR
							+ bean.getCostoSuperCantCDR();
					totalSumaCDTotalR = totalSumaCDTotalR.add(bean.getCostoSuperCDR());
					totalCantCITotalR = totalCantCITotalR
							+ bean.getCostoSuperCantCIR();
					totalSumaCITotalR = totalSumaCITotalR.add(bean.getCostoSuperCIR());
					totalSumaCostoTotalR = totalSumaCostoTotalR
							.add(costoTotalSupeR);

				} else if (FiseConstants.TIPO_PERSONAL_GESTOR_COD == d.getId()
						.getIdTipPersonal()) {

					bean.setCostoGestCantCDR(d
							.getCantCostDirecto() != null ? d
							.getCantCostDirecto() : new Long(0));
					bean.setCostoGestCDR(d.getCostoDirecto() != null ? d
									.getCostoDirecto() : new BigDecimal(0));
					bean.setCostoGestCantCIR(d
							.getCantCostIndirecto() != null ? d
							.getCantCostIndirecto() : new Long(0));
					bean.setCostoGestCIR(d.getCostoIndirecto() != null ? d
									.getCostoIndirecto() : new BigDecimal(0));

					BigDecimal costoTotalGestR = ((bean.getCostoGestCDR()
							.multiply(new BigDecimal(bean.getCostoGestCantCDR())))).add((bean.getCostoGestCIR().multiply(new BigDecimal(
									bean.getCostoGestCantCIR()))));
					bean.setCostoGestCostoTotR(costoTotalGestR);

					totalCantGestor = totalCantGestor
							+ bean.getCostoGestCantCDR()
							+ bean.getCostoGestCantCIR();
					totalSumaGestor = totalSumaGestor.add(costoTotalGestR);

					totalCantCDTotalR = totalCantCDTotalR
							+ bean.getCostoGestCantCDR();
					totalSumaCDTotalR = totalSumaCDTotalR.add(bean.getCostoGestCDR());
					totalCantCITotalR = totalCantCITotalR
							+ bean.getCostoGestCantCIR();
					totalSumaCITotalR = totalSumaCITotalR.add(bean.getCostoGestCIR());
					totalSumaCostoTotalR = totalSumaCostoTotalR
							.add(costoTotalGestR);

				} else if (FiseConstants.TIPO_PERSONAL_ASISTENTE_COD == d.getId()
						.getIdTipPersonal()) {

					bean.setCostoAsistCantCDR(d
							.getCantCostDirecto() != null ? d
							.getCantCostDirecto() : new Long(0));
					bean.setCostoAsistCDR(d.getCostoDirecto() != null ? d
									.getCostoDirecto() : new BigDecimal(0));
					bean.setCostoAsistCantCIR(d
							.getCantCostIndirecto() != null ? d
							.getCantCostIndirecto() : new Long(0));
					bean.setCostoAsistCIR(d.getCostoIndirecto() != null ? d
									.getCostoIndirecto() : new BigDecimal(0));

					BigDecimal costoTotalAsistR = ((bean.getCostoAsistCDR()
							.multiply(new BigDecimal(bean.getCostoAsistCantCDR())))).add((bean.getCostoAsistCIR().multiply(new BigDecimal(
									bean.getCostoAsistCantCIR()))));
					bean.setCostoAsistCostoTotR(costoTotalAsistR);

					totalCantAsist = totalCantAsist
							+ bean.getCostoAsistCantCDR()
							+ bean.getCostoAsistCantCIR();
					totalSumaAsist = totalSumaAsist.add(costoTotalAsistR);

					totalCantCDTotalR = totalCantCDTotalR
							+ bean.getCostoAsistCantCDR();
					totalSumaCDTotalR = totalSumaCDTotalR.add(bean.getCostoAsistCDR());
					totalCantCITotalR = totalCantCITotalR
							+ bean.getCostoAsistCantCIR();
					totalSumaCITotalR = totalSumaCITotalR.add(bean.getCostoAsistCIR());
					totalSumaCostoTotalR = totalSumaCostoTotalR
							.add(costoTotalAsistR);
				}

			} else if (FiseConstants.ZONABENEF_PROVINCIA_COD == d.getId()
					.getIdZonaBenef()) {

				bean.setNroBenefCantP(formato
						.getNumBenefEmpPerAntUrbProv() != null ? formato
						.getNumBenefEmpPerAntUrbProv() : new Long(0));
				bean.setCostoPromMensCDP(formato
						.getCostoPromMenUrbProv() != null ? formato
						.getCostoPromMenUrbProv() : new BigDecimal(0));
				bean.setCostoPromMensCTP(formato
						.getCostoPromMenUrbProv() != null ? formato
						.getCostoPromMenUrbProv() : new BigDecimal(0));

				if (FiseConstants.TIPO_PERSONAL_CORDINADOR_COD == d.getId()
						.getIdTipPersonal()) {

					bean.setCostoCoordCantCDP(d
							.getCantCostDirecto() != null ? d
							.getCantCostDirecto() : new Long(0));
					bean.setCostoCoordCDP(d.getCostoDirecto() != null ? d
									.getCostoDirecto() : new BigDecimal(0));
					bean.setCostoCoordCantCIP(d
							.getCantCostIndirecto() != null ? d
							.getCantCostIndirecto() : new Long(0));
					bean.setCostoCoordCIP(d.getCostoIndirecto() != null ? d
									.getCostoIndirecto() : new BigDecimal(0));

					BigDecimal costoTotalCoordP = ((bean.getCostoCoordCDP()
							.multiply(new BigDecimal(bean.getCostoCoordCantCDP())))).add((bean.getCostoCoordCIP().multiply(new BigDecimal(
									bean.getCostoCoordCantCIP()))));
					
					bean.setCostoCoordCstoTotP(costoTotalCoordP);

					totalCantCoordinador = totalCantCoordinador
							+ bean.getCostoCoordCantCDP()
							+ bean.getCostoCoordCantCIP();
					totalSumaCoordinador = totalSumaCoordinador
							.add(costoTotalCoordP);

					totalCantCDTotalP = totalCantCDTotalP
							+ bean.getCostoCoordCantCDP();
					totalSumaCDTotalP = totalSumaCDTotalP.add(bean.getCostoCoordCDP());
					totalCantCITotalP = totalCantCITotalP
							+ bean.getCostoCoordCantCIP();
					totalSumaCITotalP = totalSumaCITotalP.add(bean.getCostoCoordCIP());
					totalSumaCostoTotalP = totalSumaCostoTotalP
							.add(costoTotalCoordP);

				} else if (FiseConstants.TIPO_PERSONAL_SUPERVISOR_COD == d.getId()
						.getIdTipPersonal()) {

					bean.setCostoSuperCantCDP(d
							.getCantCostDirecto() != null ? d
							.getCantCostDirecto() : new Long(0));
					bean.setCostoSuperCDP(d.getCostoDirecto() != null ? d
									.getCostoDirecto() : new BigDecimal(0));
					bean.setCostoSuperCantCIP(d
							.getCantCostIndirecto() != null ? d
							.getCantCostIndirecto() : new Long(0));
					bean.setCostoSuperCIP(d.getCostoIndirecto() != null ? d
									.getCostoIndirecto() : new BigDecimal(0));

					BigDecimal costoTotalSupeP = ((bean.getCostoSuperCDP()
							.multiply(new BigDecimal(bean.getCostoSuperCantCDP())))).add((bean.getCostoSuperCIP().multiply(new BigDecimal(
									bean.getCostoSuperCantCIP()))));
					bean.setCostoSuperCstoTotP(costoTotalSupeP);

					totalCantSupervisor = totalCantSupervisor
							+ bean.getCostoSuperCantCDP()
							+ bean.getCostoSuperCantCIP();
					totalSumaSupervisor = totalSumaSupervisor
							.add(costoTotalSupeP);

					totalCantCDTotalP = totalCantCDTotalP
							+ bean.getCostoSuperCantCDP();
					totalSumaCDTotalP = totalSumaCDTotalP.add(bean.getCostoSuperCDP());
					totalCantCITotalP = totalCantCITotalP
							+ bean.getCostoSuperCantCIP();
					totalSumaCITotalP = totalSumaCITotalP.add(bean.getCostoSuperCIP());
					totalSumaCostoTotalP = totalSumaCostoTotalP
							.add(costoTotalSupeP);

				} else if (FiseConstants.TIPO_PERSONAL_GESTOR_COD == d.getId()
						.getIdTipPersonal()) {

					bean.setCostoGestCantCDP(d
							.getCantCostDirecto() != null ? d
							.getCantCostDirecto() : new Long(0));
					bean.setCostoGestCDP(d.getCostoDirecto() != null ? d
									.getCostoDirecto() : new BigDecimal(0));
					bean.setCostoGestCantCIP(d
							.getCantCostIndirecto() != null ? d
							.getCantCostIndirecto() : new Long(0));
					bean.setCostoGestCIP(d.getCostoIndirecto() != null ? d
									.getCostoIndirecto() : new BigDecimal(0));

					BigDecimal costoTotalGestP = ((bean.getCostoGestCDP()
							.multiply(new BigDecimal(bean.getCostoGestCantCDP())))).add((bean.getCostoGestCIP().multiply(new BigDecimal(
									bean.getCostoGestCantCIP()))));
					bean.setCostoGestCstoTotP(costoTotalGestP);

					totalCantGestor = totalCantGestor
							+ bean.getCostoGestCantCDP()
							+ bean.getCostoGestCantCIP();
					totalSumaGestor = totalSumaGestor.add(costoTotalGestP);

					totalCantCDTotalP = totalCantCDTotalP
							+ bean.getCostoGestCantCDP();
					totalSumaCDTotalP = totalSumaCDTotalP.add(bean.getCostoGestCDP());
					totalCantCITotalP = totalCantCITotalP
							+ bean.getCostoGestCantCIP();
					totalSumaCITotalP = totalSumaCITotalP.add(bean.getCostoGestCIP());
					totalSumaCostoTotalP = totalSumaCostoTotalP
							.add(costoTotalGestP);

				} else if (FiseConstants.TIPO_PERSONAL_ASISTENTE_COD == d.getId()
						.getIdTipPersonal()) {

					bean.setCostoAsistCantCDP(d
							.getCantCostDirecto() != null ? d
							.getCantCostDirecto() : new Long(0));
					bean.setCostoAsistCDP(d.getCostoDirecto() != null ? d
									.getCostoDirecto() : new BigDecimal(0));
					bean.setCostoAsistCantCIP(d
							.getCantCostIndirecto() != null ? d
							.getCantCostIndirecto() : new Long(0));
					bean.setCostoAsistCIP(d.getCostoIndirecto() != null ? d
									.getCostoIndirecto() : new BigDecimal(0));

					BigDecimal costoTotalAsistP = ((bean.getCostoAsistCDP()
							.multiply(new BigDecimal(bean.getCostoAsistCantCDP())))).add((bean.getCostoAsistCIP().multiply(new BigDecimal(
									bean.getCostoAsistCantCIP()))));
					bean.setCostoAsistCstoTotP(costoTotalAsistP);

					totalCantAsist = totalCantAsist
							+ bean.getCostoAsistCantCDP()
							+ bean.getCostoAsistCantCIP();
					totalSumaAsist = totalSumaAsist.add(costoTotalAsistP);

					totalCantCDTotalP = totalCantCDTotalP
							+ bean.getCostoAsistCantCDP();
					totalSumaCDTotalP = totalSumaCDTotalP.add(bean.getCostoAsistCDP());
					totalCantCITotalP = totalCantCITotalP
							+ bean.getCostoAsistCantCIP();
					totalSumaCITotalP = totalSumaCITotalP.add(bean.getCostoAsistCIP());
					totalSumaCostoTotalP = totalSumaCostoTotalP
							.add(costoTotalAsistP);
				}

			} else if (FiseConstants.ZONABENEF_LIMA_COD == d.getId()
					.getIdZonaBenef()) {

				bean.setNroBenefCantL(formato
						.getNumBenefEmpPerAntUrbLima() != null ? formato
						.getNumBenefEmpPerAntUrbLima() : new Long(0));
				bean.setCostoPromMensCDL(formato
						.getCostoPromMenUrbLima() != null ? formato
						.getCostoPromMenUrbLima() : new BigDecimal(0));
				bean.setCostoPromMensCTL(formato
						.getCostoPromMenUrbLima() != null ? formato
						.getCostoPromMenUrbLima() : new BigDecimal(0));

				if (FiseConstants.TIPO_PERSONAL_CORDINADOR_COD == d.getId()
						.getIdTipPersonal()) {

					bean.setCostoCoordCantCDL(d
							.getCantCostDirecto() != null ? d
							.getCantCostDirecto() : new Long(0));
					bean.setCostoCoordCDL(d.getCostoDirecto() != null ? d
									.getCostoDirecto() : new BigDecimal(0));
					bean.setCostoCoordCantCIL(d
							.getCantCostIndirecto() != null ? d
							.getCantCostIndirecto() : new Long(0));
					bean.setCostoCoordCIL(d.getCostoIndirecto() != null ? d
									.getCostoIndirecto() : new BigDecimal(0));

					BigDecimal costoTotalCoordL = ((bean.getCostoCoordCDL()
							.multiply(new BigDecimal(bean.getCostoCoordCantCDL())))).add((bean.getCostoCoordCIL().multiply(new BigDecimal(
									bean.getCostoCoordCantCIL()))));
					bean.setCostoCoordCstoTotL(costoTotalCoordL);

					totalCantCoordinador = totalCantCoordinador
							+ bean.getCostoCoordCantCDL()
							+ bean.getCostoCoordCantCIL();
					totalSumaCoordinador = totalSumaCoordinador
							.add(costoTotalCoordL);

					totalCantCDTotalL = totalCantCDTotalL
							+ bean.getCostoCoordCantCDL();
					totalSumaCDTotalL = totalSumaCDTotalL.add(bean.getCostoCoordCDL());
					totalCantCITotalL = totalCantCITotalL
							+ bean.getCostoCoordCantCIL();
					totalSumaCITotalL = totalSumaCITotalL.add(bean.getCostoCoordCIL());
					totalSumaCostoTotalL = totalSumaCostoTotalL
							.add(costoTotalCoordL);

				} else if (FiseConstants.TIPO_PERSONAL_SUPERVISOR_COD == d.getId()
						.getIdTipPersonal()) {

					bean.setCostoSuperCantCDL(d
							.getCantCostDirecto() != null ? d
							.getCantCostDirecto() : new Long(0));
					bean.setCostoSuperCDL(d.getCostoDirecto() != null ? d
									.getCostoDirecto() : new BigDecimal(0));
					bean.setCostoSuperCantCIL(d
							.getCantCostIndirecto() != null ? d
							.getCantCostIndirecto() : new Long(0));
					bean.setCostoSuperCIL(d.getCostoIndirecto() != null ? d
									.getCostoIndirecto() : new BigDecimal(0));

					BigDecimal costoTotalSupeL = ((bean.getCostoSuperCDL()
							.multiply(new BigDecimal(bean.getCostoSuperCantCDL())))).add((bean.getCostoSuperCIL().multiply(new BigDecimal(
									bean.getCostoSuperCantCIL()))));
					bean.setCostoSuperCstoTotL(costoTotalSupeL);

					totalCantSupervisor = totalCantSupervisor
							+ bean.getCostoSuperCantCDL()
							+ bean.getCostoSuperCantCIL();
					totalSumaSupervisor = totalSumaSupervisor
							.add(costoTotalSupeL);

					totalCantCDTotalL = totalCantCDTotalL
							+ bean.getCostoSuperCantCDL();
					totalSumaCDTotalL = totalSumaCDTotalL.add(bean.getCostoSuperCDL());
					totalCantCITotalL = totalCantCITotalL
							+ bean.getCostoSuperCantCIL();
					totalSumaCITotalL = totalSumaCITotalL.add(bean.getCostoSuperCIL());
					totalSumaCostoTotalL = totalSumaCostoTotalL
							.add(costoTotalSupeL);

				} else if (FiseConstants.TIPO_PERSONAL_GESTOR_COD == d.getId()
						.getIdTipPersonal()) {

					bean.setCostoGestCantCDL(d
							.getCantCostDirecto() != null ? d
							.getCantCostDirecto() : new Long(0));
					bean.setCostoGestCDL(d.getCostoDirecto() != null ? d
									.getCostoDirecto() : new BigDecimal(0));
					bean.setCostoGestCantCIL(d
							.getCantCostIndirecto() != null ? d
							.getCantCostIndirecto() : new Long(0));
					bean.setCostoGestorCIL(d
							.getCostoIndirecto() != null ? d
							.getCostoIndirecto() : new BigDecimal(0));

					BigDecimal costoTotalGestL = ((bean.getCostoGestCDL()
							.multiply(new BigDecimal(bean.getCostoGestCantCDL())))).add((bean.getCostoGestorCIL().multiply(new BigDecimal(
									bean.getCostoGestCantCIL()))));
					bean.setCostoGestCstoTotL(costoTotalGestL);

					totalCantGestor = totalCantGestor
							+ bean.getCostoGestCantCDL()
							+ bean.getCostoGestCantCIL();
					totalSumaGestor = totalSumaGestor.add(costoTotalGestL);

					totalCantCDTotalL = totalCantCDTotalL
							+ bean.getCostoGestCantCDL();
					totalSumaCDTotalL = totalSumaCDTotalL.add(bean.getCostoGestCDL());
					totalCantCITotalL = totalCantCITotalL
							+ bean.getCostoGestCantCIL();
					totalSumaCITotalL = totalSumaCITotalL.add(bean.getCostoGestorCIL());
					totalSumaCostoTotalL = totalSumaCostoTotalL
							.add(costoTotalGestL);

				} else if (FiseConstants.TIPO_PERSONAL_ASISTENTE_COD == d.getId()
						.getIdTipPersonal()) {

					bean.setCostoAsistCantCDL(d
							.getCantCostDirecto() != null ? d
							.getCantCostDirecto() : new Long(0));
					bean.setCostoAsistCDL(d.getCostoDirecto() != null ? d
									.getCostoDirecto() : new BigDecimal(0));
					bean.setCostoAsistCantCIL(d
							.getCantCostIndirecto() != null ? d
							.getCantCostIndirecto() : new Long(0));
					bean.setCostoAsistCIL(d.getCostoIndirecto() != null ? d
									.getCostoIndirecto() : new BigDecimal(0));

					BigDecimal costoTotalAsistL = ((bean.getCostoAsistCDL()
							.multiply(new BigDecimal(bean.getCostoAsistCantCDL())))).add((bean.getCostoAsistCIL().multiply(new BigDecimal(
									bean.getCostoAsistCantCIL()))));
					bean.setCostoAsistCstoTotL(costoTotalAsistL);

					totalCantAsist = totalCantAsist
							+ bean.getCostoAsistCantCDL()
							+ bean.getCostoAsistCantCIL();
					totalSumaAsist = totalSumaAsist.add(costoTotalAsistL);

					totalCantCDTotalL = totalCantCDTotalL
							+ bean.getCostoAsistCantCDL();
					totalSumaCDTotalL = totalSumaCDTotalL.add(bean.getCostoAsistCDL());
					totalCantCITotalL = totalCantCITotalL
							+ bean.getCostoAsistCantCIL();
					totalSumaCITotalL = totalSumaCITotalL.add(bean.getCostoAsistCIL());
					totalSumaCostoTotalL = totalSumaCostoTotalL
							.add(costoTotalAsistL);
				}

			}

			totalCantBeneficiarios = bean.getNroBenefCantR()
					+ bean.getNroBenefCantP()
					+ bean.getNroBenefCantL();
		
			SumaCostoPromedioMensual = bean.getCostoPromMensCDR()
					.add(bean.getCostoPromMensCDL())
					.add(bean.getCostoPromMensCDP());
		}		
		// ------------ SUMA TOTAL DE LAS 3 regiones---------
		bean.setSumaCantNroBenefT(totalCantBeneficiarios);
		logger.info("totalCantBeneficiarios" + totalCantBeneficiarios);
		
		long totalSumaCantidad = 0;
		bean.setSumaCantCoordT(totalCantCoordinador);
		bean.setSumaCantSupervT(totalCantSupervisor);
		bean.setSumaCantGestorT(totalCantGestor);
		bean.setSumaCantAsistT(totalCantAsist);

		totalSumaCantidad = totalCantCoordinador + totalCantSupervisor
				+ totalCantGestor + totalCantAsist;
		bean.setSumaCantCstoTotalT(totalSumaCantidad);
		logger.info("totalSumaCantidad" + totalSumaCantidad);

		BigDecimal totalSumaCosto = new BigDecimal(0);
		bean.setSumaCostCoordT(totalSumaCoordinador);
		bean.setSumaCostSupervT(totalSumaSupervisor);
		bean.setSumaCostGestorT(totalSumaGestor);
		bean.setSumaCostAsistT(totalSumaAsist);

		totalSumaCosto = totalSumaCoordinador.add(totalSumaSupervisor)
				.add(totalSumaGestor).add(totalSumaAsist);
		bean.setSumaCostCstoTotalT(totalSumaCosto);
		logger.info("totalSumaCosto" + totalSumaCosto);

		bean.setSumaCostPromiTotalT(SumaCostoPromedioMensual);
		logger.info("SumaCostoPromedioMensual"
				+ SumaCostoPromedioMensual);

		// ------suma costo gestion POR ZONA----------------------

		bean.setCostoTotalGestCantCDR(totalCantCDTotalR);
		logger.info("totalCantCDTotalR" + totalCantCDTotalR);
		bean.setCostoTotalGestCDR(totalSumaCDTotalR);
		bean.setCostoTotalGestCantCIR(totalCantCITotalR);
		logger.info("totalCantCITotalR" + totalCantCITotalR);
		bean.setCostoTotGestCIR(totalSumaCITotalR);
		bean.setCostoTotalGestoTotR(totalSumaCostoTotalR);
		// logger.info("totalSumaCostoTotalR"+totalSumaCostoTotalR);

		bean.setCostoTotGestCantCDP(totalCantCDTotalP);
		logger.info("totalCantCDTotalP" + totalCantCDTotalP);
		bean.setCostoTotGestCDP(totalSumaCDTotalP);
		bean.setCostoTotGestCantCIP(totalCantCITotalP);
		logger.info("totalCantCITotalP" + totalCantCITotalP);
		bean.setCostoTotGestCIP(totalSumaCITotalP);
		bean.setCostoTotGestoTotP(totalSumaCostoTotalP);
		// logger.info("totalSumaCostoTotalP"+totalSumaCostoTotalP);

		bean.setCostoTotGestCantCDL(totalCantCDTotalL);
		logger.info("totalCantCDTotalL" + totalCantCDTotalL);
		bean.setCostoTotGestCDL(totalSumaCDTotalL);
		bean.setCostoTotGestCantCIL(totalCantCITotalL);
		logger.info("totalCantCITotalL" + totalCantCITotalL);
		bean.setCostoTotGestCIL(totalSumaCITotalL);
		bean.setCostoTotGestoTotL(totalSumaCostoTotalL);
		logger.info("totalSumaCostoTotalL" + totalSumaCostoTotalL);

		return bean;
	}
	
	@Override
	public HashMap<String, Object> mapearParametrosFormato14C(
			Formato14CReportBean f) throws Exception {

		HashMap<String, Object> mapJRParams = new HashMap<String, Object>();

		mapJRParams.put(FiseConstants.PARAM_NOMBRE_SEDE_F14C,
				f.getNombreSede());

		mapJRParams.put(FiseConstants.PARAM_DESC_EMPRESA_F12A,
				f.getDescEmpresa());
		mapJRParams.put(FiseConstants.PARAM_ANO_PRES_F12A,
				f.getAnioPresent());
		mapJRParams.put(FiseConstants.PARAM_DESC_MES_PRES_F12A,
				f.getDescMesPresentacion());
		
		mapJRParams.put(FiseConstants.PARAM_ANO_INI_VIG_F14C,
				f.getAnioInicioVigencia());		
		
		mapJRParams.put(FiseConstants.PARAM_ANO_FIn_VIG_F14C,
				f.getAnioFinVigencia());

		// ---------------RURAL-------------------------------------//
		mapJRParams.put(FiseConstants.PARAM_NUMBENEF_EMPAD_CANT_R_F14C,
				f.getNroBenefCantR());

		mapJRParams.put(FiseConstants.PARAM_COSTO_COORD_CANTCD_R_F14C,
				f.getCostoCoordCantCDR());
		mapJRParams.put(FiseConstants.PARAM_COSTO_SUPER_CANTCD_R_F14C,
				f.getCostoSuperCantCDR());
		mapJRParams.put(FiseConstants.PARAM_COSTO_GEST_CANTCD_R_F14C,
				f.getCostoGestCantCDR());
		mapJRParams.put(FiseConstants.PARAM_COSTO_ASIST_CANTCD_R_F14C,
				f.getCostoAsistCantCDR());
		mapJRParams.put(FiseConstants.PARAM_COSTO_TOTGESTPERS_CANTCD_R_F14C,
				f.getCostoTotalGestCantCDR());

		mapJRParams.put(FiseConstants.PARAM_COSTO_COORD_CD_R_F14C,
				f.getCostoCoordCDR());
		mapJRParams.put(FiseConstants.PARAM_COSTO_SUP_CD_R_F14C,
				f.getCostoSuperCDR());
		mapJRParams.put(FiseConstants.PARAM_COSTO_GEST_CD_R_F14C,
				f.getCostoGestCDR());
		mapJRParams.put(FiseConstants.PARAM_COSTO_ASIST_CD_R_F14C,
				f.getCostoAsistCDR());
		mapJRParams.put(FiseConstants.PARAM_COSTO_TOTALGESTPERS_CD_R_F14C,
				f.getCostoTotalGestCDR());

		mapJRParams.put(FiseConstants.PARAM_COSTO_COORD_CANTCI_R_F14C,
				f.getCostoCoordCantCIR());
		mapJRParams.put(FiseConstants.PARAM_COSTO_SUPER_CANTCI_R_F14C,
				f.getCostoSuperCantCIR());
		mapJRParams.put(FiseConstants.PARAM_COSTO_GEST_CANTCI_R_F14C,
				f.getCostoGestCantCIR());
		mapJRParams.put(FiseConstants.PARAM_COSTO_ASIST_CANTCI_R_F14C,
				f.getCostoAsistCantCIR());
		mapJRParams.put(FiseConstants.PARAM_COSTO_TOTALGESTPERS_CANTCI_R_F14C,
				f.getCostoTotalGestCantCIR());

		mapJRParams.put(FiseConstants.PARAM_COSTO_COORD_CI_R_F14C,
				f.getCostoCoordCIR());
		mapJRParams.put(FiseConstants.PARAM_COSTO_SUP_CI_R_F14C,
				f.getCostoSuperCIR());
		mapJRParams.put(FiseConstants.PARAM_COSTO_GEST_CI_R_F14C,
				f.getCostoGestCIR());
		mapJRParams.put(FiseConstants.PARAM_COSTO_ASIST_CI_R_F14C,
				f.getCostoAsistCIR());
		mapJRParams.put(FiseConstants.PARAM_COSTO_TOTALGESTPERS_CI_R_F14C,
				f.getCostoTotGestCIR());

		// ------------ Suma Costo Total ------
		mapJRParams.put(FiseConstants.PARAM_COSTO_TOTAL_COORD_R_F14C,
				f.getCostoCoordCostoTotR());
		mapJRParams.put(FiseConstants.PARAM_COSTO_TOTAL_SUPERV_R_F14C,
				f.getCostoSuperCostoTotR());
		mapJRParams.put(FiseConstants.PARAM_COSTO_TOTAL_GESTOR_R_F14C,
				f.getCostoGestCostoTotR());
		mapJRParams.put(FiseConstants.PARAM_COSTO_TOTAL_ASIST_R_F14C,
				f.getCostoAsistCostoTotR());
		mapJRParams.put(FiseConstants.PARAM_COSTO_TOTAL_GEST_R_F14C,
				f.getCostoTotalGestoTotR());

		mapJRParams.put(FiseConstants.PARAM_COSTO_PROM_CD_R_F14C,
				f.getCostoPromMensCDR());
		mapJRParams.put(FiseConstants.PARAM_COSTO_PROM_TOTAL_R_F14C,
				f.getCostoPromMensCDR());

		// ---------------PROVINCIA-------------------------------------//
		mapJRParams.put(FiseConstants.PARAM_NUMBENEF_EMPAD_CANT_P_F14C,
				f.getNroBenefCantP());

		mapJRParams.put(FiseConstants.PARAM_COSTO_COORD_CANTCD_P_F14C,
				f.getCostoCoordCantCDP());
		mapJRParams.put(FiseConstants.PARAM_COSTO_SUPER_CANTCD_P_F14C,
				f.getCostoSuperCantCDP());
		mapJRParams.put(FiseConstants.PARAM_COSTO_GEST_CANTCD_P_F14C,
				f.getCostoGestCantCDP());
		mapJRParams.put(FiseConstants.PARAM_COSTO_ASIST_CANTCD_P_F14C,
				f.getCostoAsistCantCDP());
		mapJRParams.put(FiseConstants.PARAM_COSTO_TOTALGESTPERS_CANTCD_P_F14C,
				f.getCostoTotGestCantCDP());

		mapJRParams.put(FiseConstants.PARAM_COSTO_COORD_CD_P_F14C,
				f.getCostoCoordCDP());
		mapJRParams.put(FiseConstants.PARAM_COSTO_SUP_CD_P_F14C,
				f.getCostoSuperCDP());
		mapJRParams.put(FiseConstants.PARAM_COSTO_GEST_CD_P_F14C,
				f.getCostoGestCDP());
		mapJRParams.put(FiseConstants.PARAM_COSTO_ASIST_CD_P_F14C,
				f.getCostoAsistCDP());
		mapJRParams.put(FiseConstants.PARAM_COSTO_TOTALGESTPERS_CD_P_F14C,
				f.getCostoTotGestCDP());

		mapJRParams.put(FiseConstants.PARAM_COSTO_COORD_CANTCI_P_F14C,
				f.getCostoCoordCantCIP());
		mapJRParams.put(FiseConstants.PARAM_COSTO_SUPER_CANTCI_P_F14C,
				f.getCostoSuperCantCIP());
		mapJRParams.put(FiseConstants.PARAM_COSTO_GEST_CANTCI_P_F14C,
				f.getCostoGestCantCIP());
		mapJRParams.put(FiseConstants.PARAM_COSTO_ASIST_CANTCI_P_F14C,
				f.getCostoAsistCantCIP());
		mapJRParams.put(FiseConstants.PARAM_COSTO_TOTGESTPERS_CANTCI_P_F14C,
				f.getCostoTotGestCantCIP());

		mapJRParams.put(FiseConstants.PARAM_COSTO_COORD_CI_P_F14C,
				f.getCostoCoordCIP());
		mapJRParams.put(FiseConstants.PARAM_COSTO_SUP_CI_P_F14C,
				f.getCostoSuperCIP());
		mapJRParams.put(FiseConstants.PARAM_COSTO_GEST_CI_P_F14C,
				f.getCostoGestCIP());
		mapJRParams.put(FiseConstants.PARAM_COSTO_ASIST_CI_P_F14C,
				f.getCostoAsistCIP());
		mapJRParams.put(FiseConstants.PARAM_COSTO_TOTGESTPERS_CI_P_F14C,
				f.getCostoTotGestCIP());

		// ------------ Suma Costo Total ------
		mapJRParams.put(FiseConstants.PARAM_COSTO_TOTAL_COORD_P_F14C,
				f.getCostoCoordCstoTotP());
		mapJRParams.put(FiseConstants.PARAM_COSTO_TOTAL_SUPERV_P_F14C,
				f.getCostoSuperCstoTotP());
		mapJRParams.put(FiseConstants.PARAM_COSTO_TOTAL_GESTOR_P_F14C,
				f.getCostoGestCstoTotP());
		mapJRParams.put(FiseConstants.PARAM_COSTO_TOTAL_ASIST_P_F14C,
				f.getCostoAsistCstoTotP());
		mapJRParams.put(FiseConstants.PARAM_COSTO_TOTAL_GEST_P_F14C,
				f.getCostoTotGestoTotP());

		mapJRParams.put(FiseConstants.PARAM_COSTO_PROM_CD_P_F14C,
				f.getCostoPromMensCDP());
		mapJRParams.put(FiseConstants.PARAM_COSTO_PROM_TOT_P_F14C,
				f.getCostoPromMensCDP());

		// --------------LIMA-------------------------------------//
		mapJRParams.put(FiseConstants.PARAM_NUMBENEF_EMPAD_CANT_L_F14C,
				f.getNroBenefCantL());

		mapJRParams.put(FiseConstants.PARAM_COSTO_COORD_CANTCD_L_F14C,
				f.getCostoCoordCantCDL());
		mapJRParams.put(FiseConstants.PARAM_COSTO_SUPER_CANTCD_L_F14C,
				f.getCostoSuperCantCDL());
		mapJRParams.put(FiseConstants.PARAM_COSTO_GEST_CANTCD_L_F14C,
				f.getCostoGestCantCDL());
		mapJRParams.put(FiseConstants.PARAM_COSTO_ASIST_CANTCD_L_F14C,
				f.getCostoAsistCantCDL());
		mapJRParams.put(FiseConstants.PARAM_COSTO_TOTGESTPERS_CANTCD_L_F14C,
				f.getCostoTotGestCantCDL());

		mapJRParams.put(FiseConstants.PARAM_COSTO_COORD_CD_L_F14C,
				f.getCostoCoordCDL());
		mapJRParams.put(FiseConstants.PARAM_COSTO_SUP_CD_L_F14C,
				f.getCostoSuperCDL());
		mapJRParams.put(FiseConstants.PARAM_COSTO_GEST_CD_L_F14C,
				f.getCostoGestCDL());
		mapJRParams.put(FiseConstants.PARAM_COSTO_ASIST_CD_L_F14C,
				f.getCostoAsistCDL());
		mapJRParams.put(FiseConstants.PARAM_COSTO_TOTGESTPERS_CD_L_F14C,
				f.getCostoTotGestCDL());

		mapJRParams.put(FiseConstants.PARAM_COSTO_COORD_CANTCI_L_F14C,
				f.getCostoCoordCantCIL());
		mapJRParams.put(FiseConstants.PARAM_COSTO_SUPER_CANTCI_L_F14C,
				f.getCostoSuperCantCIL());
		mapJRParams.put(FiseConstants.PARAM_COSTO_GEST_CANTCI_L_F14C,
				f.getCostoGestCantCIL());
		mapJRParams.put(FiseConstants.PARAM_COSTO_ASIST_CANTCI_L_F14C,
				f.getCostoAsistCantCIL());
		mapJRParams.put(FiseConstants.PARAM_COSTO_TOTGESTPERS_CANTCI_L_F14C,
				f.getCostoTotGestCantCIL());

		mapJRParams.put(FiseConstants.PARAM_COSTO_COORD_CI_L_F14C,
				f.getCostoCoordCIL());
		mapJRParams.put(FiseConstants.PARAM_COSTO_SUP_CI_L_F14C,
				f.getCostoSuperCIL());
		mapJRParams.put(FiseConstants.PARAM_COSTO_GEST_CI_L_F14C,
				f.getCostoGestorCIL());
		mapJRParams.put(FiseConstants.PARAM_COSTO_ASIST_CI_L_F14C,
				f.getCostoAsistCIL());
		mapJRParams.put(FiseConstants.PARAM_COSTO_TOTGESTPERS_CI_L_F14C,
				f.getCostoTotGestCIL());

		// ------------ Suma Costo Total ------
		mapJRParams.put(FiseConstants.PARAM_COSTO_TOTAL_COORD_L_F14C,
				f.getCostoCoordCstoTotL());
		mapJRParams.put(FiseConstants.PARAM_COSTO_TOTAL_SUPERV_L_F14C,
				f.getCostoSuperCstoTotL());
		mapJRParams.put(FiseConstants.PARAM_COSTO_TOTAL_GESTOR_L_F14C,
				f.getCostoGestCstoTotL());
		mapJRParams.put(FiseConstants.PARAM_COSTO_TOTAL_ASIST_L_F14C,
				f.getCostoAsistCstoTotL());
		mapJRParams.put(FiseConstants.PARAM_COSTO_TOTAL_GEST_L_F14C,
				f.getCostoTotGestoTotL());

		mapJRParams.put(FiseConstants.PARAM_COSTO_PROM_CD_L_F14C,
				f.getCostoPromMensCDL());
		mapJRParams.put(FiseConstants.PARAM_COSTO_PROM_TOT_L_F14C,
				f.getCostoPromMensCDL());

		// --------------SUMA 3 ZONA TOTAL SUMA DE
		// CANTIDAD-------------------------------------//

		mapJRParams.put(FiseConstants.PARAM_SUMANEBEF_CANT_T_F14C,
				f.getSumaCantNroBenefT());

		mapJRParams.put(FiseConstants.PARAM_SUMACOORD_CANT_T_F14C,
				f.getSumaCantCoordT());
		mapJRParams.put(FiseConstants.PARAM_SUMASUP_CANT_T_F14C,
				f.getSumaCantSupervT());
		mapJRParams.put(FiseConstants.PARAM_SUMAGEST_CANT_T_F14C,
				f.getSumaCantGestorT());
		mapJRParams.put(FiseConstants.PARAM_SUMASIST_CANT_T_F14C,
				f.getSumaCantAsistT());
		mapJRParams.put(FiseConstants.PARAM_SUMACSTO_GESPER_CANT_T_F14C,
				f.getSumaCantCstoTotalT());

		// --------------ZONA TOTAL SUMA DE
		// COSTO-------------------------------------//

		mapJRParams.put(FiseConstants.PARAM_SUMACSTO_COORD_COSTO_T_F14C,
				f.getSumaCostCoordT());
		mapJRParams.put(FiseConstants.PARAM_SUMACSTO_SUP_COSTO_T_F14C,
				f.getSumaCostSupervT());
		mapJRParams.put(FiseConstants.PARAM_SUMACSTO_GEST_COSTO_T_F14C,
				f.getSumaCostGestorT());
		mapJRParams.put(FiseConstants.PARAM_SUMACSTO_ASIST_COSTO_T_F14C,
				f.getSumaCostAsistT());
		mapJRParams.put(FiseConstants.PARAM_SUMACSTO_GESPER_COSTO_T_F14C,
				f.getSumaCostCstoTotalT());
		mapJRParams.put(FiseConstants.PARAM_CSPOPROM_MENS_TOTCOS_T_F14C,
				f.getSumaCostPromiTotalT());
		
		return mapJRParams;
	}
	

}
