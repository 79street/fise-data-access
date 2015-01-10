package gob.osinergmin.fise.gart.service.impl;

import gob.osinergmin.fise.bean.ResumenCostoBean;
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

}
