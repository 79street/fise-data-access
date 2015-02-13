package gob.osinergmin.fise.gart.service.impl;

import gob.osinergmin.fise.bean.ResumenCostoBean;
import gob.osinergmin.fise.constant.FiseConstants;
import gob.osinergmin.fise.dao.ResumenCostosDao;
import gob.osinergmin.fise.gart.service.ResumenCostosService;
import gob.osinergmin.fise.util.FormatoUtil;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service(value="resumenCostosServiceImpl")
public class ResumenCostosServiceImpl implements ResumenCostosService {
	
	Logger logger=Logger.getLogger(ResumenCostosServiceImpl.class);
	
	
	@Autowired
	@Qualifier("resumenCostosDaoImpl")
	private ResumenCostosDao resumenCostosDao;
	
	
	
	@Override
	@Transactional
	public List<ResumenCostoBean> buscarResumenCostoF14A(String codEmpresa,
			Long idGrupoInf) throws Exception{
		List<ResumenCostoBean> lista = new ArrayList<ResumenCostoBean>();
		ResumenCostoBean r =null;
		List<Object[]> listaF14A =null;
		try {
			String codEmpreCompleta = FormatoUtil.rellenaDerecha(codEmpresa, ' ', 4);
			listaF14A = resumenCostosDao.listarResumenCostosF14A(codEmpreCompleta, idGrupoInf);
			for(int i = 0; i < listaF14A.size(); i++){					
				r = new ResumenCostoBean();					
				r.setDesEmpresa(String.valueOf(((String)listaF14A.get(i)[0] == null) ? "--" :listaF14A.get(i)[0]));
				r.setPeriodo(String.valueOf(((String)listaF14A.get(i)[1] == null) ? "--" :listaF14A.get(i)[1]));	
				r.setEmpSolicitadoR(((BigDecimal)listaF14A.get(i)[2] == null) ? new BigDecimal(0.0) :(BigDecimal)listaF14A.get(i)[2]);			   
				r.setEmpSolicitadoP(((BigDecimal)listaF14A.get(i)[3] == null) ? new BigDecimal(0.0) :(BigDecimal)listaF14A.get(i)[3]);		   
				r.setEmpSolicitadoL(((BigDecimal)listaF14A.get(i)[4] == null) ? new BigDecimal(0.0) :(BigDecimal)listaF14A.get(i)[4]);	
				r.setEmpAprobadoR(((BigDecimal)listaF14A.get(i)[5] == null) ? new BigDecimal(0.0) :(BigDecimal)listaF14A.get(i)[5]);
				r.setEmpAprobadoP(((BigDecimal)listaF14A.get(i)[6] == null) ? new BigDecimal(0.0) :(BigDecimal)listaF14A.get(i)[6]);		  
			    r.setEmpAprobadoL(((BigDecimal)listaF14A.get(i)[7] == null) ? new BigDecimal(0.0) :(BigDecimal)listaF14A.get(i)[7]);		   
			    r.setGlpSolicitadoR(((BigDecimal)listaF14A.get(i)[8] == null) ? new BigDecimal(0.0) :(BigDecimal)listaF14A.get(i)[8]);    
			    r.setGlpSolicitadoP(((BigDecimal)listaF14A.get(i)[9] == null) ? new BigDecimal(0.0) :(BigDecimal)listaF14A.get(i)[9]);		    
			    r.setGlpSolicitadoL(((BigDecimal)listaF14A.get(i)[10] == null) ? new BigDecimal(0.0) :(BigDecimal)listaF14A.get(i)[10]);	   
			    r.setGlpAprobadoR(((BigDecimal)listaF14A.get(i)[11] == null) ? new BigDecimal(0.0) :(BigDecimal)listaF14A.get(i)[11]);			   
			    r.setGlpAprobadoP(((BigDecimal)listaF14A.get(i)[12] == null) ? new BigDecimal(0.0) :(BigDecimal)listaF14A.get(i)[12]);		   
			    r.setGlpAprobadoL(((BigDecimal)listaF14A.get(i)[13] == null) ? new BigDecimal(0.0) :(BigDecimal)listaF14A.get(i)[13]);
				lista.add(r);
			 }			
		} catch (Exception e) { 
			e.printStackTrace();
			logger.info("Error al listar resumen de costos de F14A:  "+e); 
		}finally{
			if(r!=null){
				r=null;
			}
			if(listaF14A!=null){
				listaF14A=null;
			}	
		}
		return lista;
	}
	
	@Override
	@Transactional
	public List<ResumenCostoBean> buscarResumenCostoF14B(String codEmpresa,
			Long idGrupoInf) throws Exception{
		List<ResumenCostoBean> lista = new ArrayList<ResumenCostoBean>();
		ResumenCostoBean r =null;
		List<Object[]> listaF14B =null;
		try {
			String codEmpreCompleta = FormatoUtil.rellenaDerecha(codEmpresa, ' ', 4);
			listaF14B = resumenCostosDao.listarResumenCostosF14B(codEmpreCompleta, idGrupoInf);
			for(int i = 0; i < listaF14B.size(); i++){					
				r = new ResumenCostoBean();					
				r.setDesEmpresa(String.valueOf(((String)listaF14B.get(i)[0] == null) ? "--" :listaF14B.get(i)[0]));
				r.setPeriodo(String.valueOf(((String)listaF14B.get(i)[1] == null) ? "--" :listaF14B.get(i)[1]));			
				
				r.setCosImpValSoliR(((BigDecimal)listaF14B.get(i)[2] == null) ? new BigDecimal(0.0) :(BigDecimal)listaF14B.get(i)[2]);			   
				r.setCosImpValAprobR(((BigDecimal)listaF14B.get(i)[3] == null) ? new BigDecimal(0.0) :(BigDecimal)listaF14B.get(i)[3]);		   
				r.setCosImpValSoliP(((BigDecimal)listaF14B.get(i)[4] == null) ? new BigDecimal(0.0) :(BigDecimal)listaF14B.get(i)[4]);	
				r.setCosImpValAprobP(((BigDecimal)listaF14B.get(i)[5] == null) ? new BigDecimal(0.0) :(BigDecimal)listaF14B.get(i)[5]);
				r.setCosImpValSoliL(((BigDecimal)listaF14B.get(i)[6] == null) ? new BigDecimal(0.0) :(BigDecimal)listaF14B.get(i)[6]);		  
			    r.setCosImpValAprobL(((BigDecimal)listaF14B.get(i)[7] == null) ? new BigDecimal(0.0) :(BigDecimal)listaF14B.get(i)[7]);		   
			    
			    r.setCosDomValSoliR(((BigDecimal)listaF14B.get(i)[8] == null) ? new BigDecimal(0.0) :(BigDecimal)listaF14B.get(i)[8]);    
			    r.setCosDomValAprobR(((BigDecimal)listaF14B.get(i)[9] == null) ? new BigDecimal(0.0) :(BigDecimal)listaF14B.get(i)[9]);		    
			    r.setCosDomValSoliP(((BigDecimal)listaF14B.get(i)[10] == null) ? new BigDecimal(0.0) :(BigDecimal)listaF14B.get(i)[10]);	   
			    r.setCosDomValAprobP(((BigDecimal)listaF14B.get(i)[11] == null) ? new BigDecimal(0.0) :(BigDecimal)listaF14B.get(i)[11]);			   
			    r.setCosDomValSoliL(((BigDecimal)listaF14B.get(i)[12] == null) ? new BigDecimal(0.0) :(BigDecimal)listaF14B.get(i)[12]);		   
			    r.setCosDomValAprobL(((BigDecimal)listaF14B.get(i)[13] == null) ? new BigDecimal(0.0) :(BigDecimal)listaF14B.get(i)[13]);		    
			    
			    r.setCosEntDisEleSoliR(((BigDecimal)listaF14B.get(i)[4] == null) ? new BigDecimal(0.0) :(BigDecimal)listaF14B.get(i)[14]);	
				r.setCosEntDisEleAprobR(((BigDecimal)listaF14B.get(i)[5] == null) ? new BigDecimal(0.0) :(BigDecimal)listaF14B.get(i)[15]);
				r.setCosEntDisEleSoliP(((BigDecimal)listaF14B.get(i)[6] == null) ? new BigDecimal(0.0) :(BigDecimal)listaF14B.get(i)[16]);		  
			    r.setCosEntDisEleAprobP(((BigDecimal)listaF14B.get(i)[7] == null) ? new BigDecimal(0.0) :(BigDecimal)listaF14B.get(i)[17]);		   
			    r.setCosEntDisEleSoliL(((BigDecimal)listaF14B.get(i)[8] == null) ? new BigDecimal(0.0) :(BigDecimal)listaF14B.get(i)[18]);    
			    r.setCosEntDisEleAprobL(((BigDecimal)listaF14B.get(i)[9] == null) ? new BigDecimal(0.0) :(BigDecimal)listaF14B.get(i)[19]);		    
			    
			    r.setCosValFisSoliR(((BigDecimal)listaF14B.get(i)[10] == null) ? new BigDecimal(0.0) :(BigDecimal)listaF14B.get(i)[20]);	   
			    r.setCosValFisAprobR(((BigDecimal)listaF14B.get(i)[11] == null) ? new BigDecimal(0.0) :(BigDecimal)listaF14B.get(i)[21]);			   
			    r.setCosValFisSoliP(((BigDecimal)listaF14B.get(i)[12] == null) ? new BigDecimal(0.0) :(BigDecimal)listaF14B.get(i)[22]);		   
			    r.setCosValFisAprobP(((BigDecimal)listaF14B.get(i)[13] == null) ? new BigDecimal(0.0) :(BigDecimal)listaF14B.get(i)[23]);		    
			    r.setCosValFisSoliL(((BigDecimal)listaF14B.get(i)[4] == null) ? new BigDecimal(0.0) :(BigDecimal)listaF14B.get(i)[24]);	
				r.setCosValFisAprobL(((BigDecimal)listaF14B.get(i)[5] == null) ? new BigDecimal(0.0) :(BigDecimal)listaF14B.get(i)[25]);
				
				r.setCosValDigSoliR(((BigDecimal)listaF14B.get(i)[6] == null) ? new BigDecimal(0.0) :(BigDecimal)listaF14B.get(i)[26]);		  
			    r.setCosValDigAprobR(((BigDecimal)listaF14B.get(i)[7] == null) ? new BigDecimal(0.0) :(BigDecimal)listaF14B.get(i)[27]);		   
			    r.setCosValDigSoliP(((BigDecimal)listaF14B.get(i)[8] == null) ? new BigDecimal(0.0) :(BigDecimal)listaF14B.get(i)[28]);    
			    r.setCosValDigAprobP(((BigDecimal)listaF14B.get(i)[9] == null) ? new BigDecimal(0.0) :(BigDecimal)listaF14B.get(i)[29]);		    
			    r.setCosValDigSoliL(((BigDecimal)listaF14B.get(i)[10] == null) ? new BigDecimal(0.0) :(BigDecimal)listaF14B.get(i)[30]);	   
			    r.setCosValDigAprobL(((BigDecimal)listaF14B.get(i)[11] == null) ? new BigDecimal(0.0) :(BigDecimal)listaF14B.get(i)[31]);			   
			   
			    r.setCosAteSoliR(((BigDecimal)listaF14B.get(i)[12] == null) ? new BigDecimal(0.0) :(BigDecimal)listaF14B.get(i)[32]);		   
			    r.setCosAteAprobR(((BigDecimal)listaF14B.get(i)[13] == null) ? new BigDecimal(0.0) :(BigDecimal)listaF14B.get(i)[33]);		    
			    r.setCosAteSoliP(((BigDecimal)listaF14B.get(i)[4] == null) ? new BigDecimal(0.0) :(BigDecimal)listaF14B.get(i)[34]);	
				r.setCosAteAprobP(((BigDecimal)listaF14B.get(i)[5] == null) ? new BigDecimal(0.0) :(BigDecimal)listaF14B.get(i)[35]);
				r.setCosAteSoliL(((BigDecimal)listaF14B.get(i)[12] == null) ? new BigDecimal(0.0) :(BigDecimal)listaF14B.get(i)[36]);	
				r.setCosAteAprobL(((BigDecimal)listaF14B.get(i)[6] == null) ? new BigDecimal(0.0) :(BigDecimal)listaF14B.get(i)[37]);		  
			    
				r.setCosGestAdmSoliR(((BigDecimal)listaF14B.get(i)[7] == null) ? new BigDecimal(0.0) :(BigDecimal)listaF14B.get(i)[38]);		   
			    r.setCosGestAdmAprobR(((BigDecimal)listaF14B.get(i)[8] == null) ? new BigDecimal(0.0) :(BigDecimal)listaF14B.get(i)[39]);    
			    r.setCosGestAdmSoliP(((BigDecimal)listaF14B.get(i)[9] == null) ? new BigDecimal(0.0) :(BigDecimal)listaF14B.get(i)[40]);		    
			    r.setCosGestAdmAprobP(((BigDecimal)listaF14B.get(i)[10] == null) ? new BigDecimal(0.0) :(BigDecimal)listaF14B.get(i)[41]);	   
			    r.setCosGestAdmSoliL(((BigDecimal)listaF14B.get(i)[11] == null) ? new BigDecimal(0.0) :(BigDecimal)listaF14B.get(i)[42]);			   
			    r.setCosGestAdmAprobL(((BigDecimal)listaF14B.get(i)[12] == null) ? new BigDecimal(0.0) :(BigDecimal)listaF14B.get(i)[43]); 
			    
				lista.add(r);
			 }			
		} catch (Exception e) { 
			e.printStackTrace();
			logger.info("Error al listar resumen de costos de F14B:  "+e); 
		}finally{
			if(r!=null){
				r=null;
			}
			if(listaF14B!=null){
				listaF14B=null;
			}	
		}
		return lista;
	}	
	
	
	@Override
	@Transactional
	public List<ResumenCostoBean> buscarResumenCostoF12AB(String codEmpresa,
			Long idGrupoInf,String formato) throws Exception{
		List<ResumenCostoBean> lista = new ArrayList<ResumenCostoBean>();
		ResumenCostoBean r =null;
		List<Object[]> listaF12 =null;		
		try {
			String codEmpreCompleta = FormatoUtil.rellenaDerecha(codEmpresa, ' ', 4);
			if(formato.equals("F12A")){ 
				listaF12 = resumenCostosDao.listarResumenCostosF12A(codEmpreCompleta, idGrupoInf);
			}else{
				listaF12 = resumenCostosDao.listarResumenCostosF12B(codEmpreCompleta, idGrupoInf);	
			}			
			for(int i = 0; i < listaF12.size(); i++){					
				r = new ResumenCostoBean();					
				r.setDesEmpresa(String.valueOf(((String)listaF12.get(i)[0] == null) ? "--" :listaF12.get(i)[0]));
				r.setPeriodo(String.valueOf(((String)listaF12.get(i)[1] == null) ? "--" :listaF12.get(i)[1]));	
				r.setSolicitadoR(((BigDecimal)listaF12.get(i)[2] == null) ? new BigDecimal(0.0) :(BigDecimal)listaF12.get(i)[2]);			   
				r.setSolicitadoP(((BigDecimal)listaF12.get(i)[3] == null) ? new BigDecimal(0.0) :(BigDecimal)listaF12.get(i)[3]);		   
				r.setSolicitadoL(((BigDecimal)listaF12.get(i)[4] == null) ? new BigDecimal(0.0) :(BigDecimal)listaF12.get(i)[4]);	
				r.setAprobadoR(((BigDecimal)listaF12.get(i)[5] == null) ? new BigDecimal(0.0) :(BigDecimal)listaF12.get(i)[5]);
				r.setAprobadoP(((BigDecimal)listaF12.get(i)[6] == null) ? new BigDecimal(0.0) :(BigDecimal)listaF12.get(i)[6]);		  
			    r.setAprobadoL(((BigDecimal)listaF12.get(i)[7] == null) ? new BigDecimal(0.0) :(BigDecimal)listaF12.get(i)[7]);			   
				lista.add(r);		
			 }			
		} catch (Exception e) { 
			e.printStackTrace();
			logger.info("Error al listar resumen de costos de F12A o F12B:  "+e); 
		}finally{
			if(r!=null){
				r=null;
			}
			if(listaF12!=null){
				listaF12=null;
			}	
		}
		return lista;
	}
	
	
	/**metodo para implementar reportes de 14A y 14B comparativos de costos*/
	
	@Override
	@Transactional
	public List<ResumenCostoBean> buscarResumenCostoCompF14AB(String codEmpresa,
			Long idGrupoInf,Long idZona, String formato) throws Exception{
		List<ResumenCostoBean> lista = new ArrayList<ResumenCostoBean>();
		ResumenCostoBean r =null;
		List<Object[]> listaComp =null;
		try {
			String codEmpreCompleta = FormatoUtil.rellenaDerecha(codEmpresa, ' ', 4);
			if(FiseConstants.NOMBRE_FORMATO_14A.equals(formato)){ 
				listaComp = resumenCostosDao.listarResumenCostosF14AComp(codEmpreCompleta, idGrupoInf, idZona);	
			}else if(FiseConstants.NOMBRE_FORMATO_14B.equals(formato)){
				listaComp = resumenCostosDao.listarResumenCostosF14BComp(codEmpreCompleta, idGrupoInf, idZona);		
			}			
			for(int i = 0; i < listaComp.size(); i++){					
				r = new ResumenCostoBean();	
				r.setCodEmpresa(String.valueOf(((String)listaComp.get(i)[0] == null) ? "--" :listaComp.get(i)[0])); 
				r.setDesEmpresa(String.valueOf(((String)listaComp.get(i)[1] == null) ? "--" :listaComp.get(i)[1]));				
				r.setPeriodo(String.valueOf(((String)listaComp.get(i)[2] == null) ? "--" :listaComp.get(i)[2]));
				r.setActividad(String.valueOf(((String)listaComp.get(i)[3] == null) ? "--" :listaComp.get(i)[3]));
				r.setDesZona(String.valueOf(((String)listaComp.get(i)[4] == null) ? "--" :listaComp.get(i)[4]));
				r.setMontoSolicitud(((BigDecimal)listaComp.get(i)[5] == null) ? new BigDecimal(0.0) :(BigDecimal)listaComp.get(i)[5]);
				r.setMontoLevObs(((BigDecimal)listaComp.get(i)[6] == null) ? new BigDecimal(0.0) :(BigDecimal)listaComp.get(i)[6]);
				r.setMontoHistorico(((BigDecimal)listaComp.get(i)[7] == null) ? new BigDecimal(0.0) :(BigDecimal)listaComp.get(i)[7]);
				r.setMontoEstablecido(((BigDecimal)listaComp.get(i)[8] == null) ? new BigDecimal(0.0) :(BigDecimal)listaComp.get(i)[8]);
				r.setItem(String.valueOf(((String)listaComp.get(i)[9] == null) ? "--" :listaComp.get(i)[9]));
				r.setDesMotivo(String.valueOf(((String)listaComp.get(i)[10] == null) ? " " :listaComp.get(i)[10]));				
				lista.add(r);
			 }			
		} catch (Exception e) { 
			e.printStackTrace();
			logger.info("Error al listar resumen de costos comparativos formatos 14A y 14B:  "+e); 
		}finally{
			if(r!=null){
				r=null;
			}
			if(listaComp!=null){
				listaComp=null;
			}	
		}
		return lista;
	}
	

}
