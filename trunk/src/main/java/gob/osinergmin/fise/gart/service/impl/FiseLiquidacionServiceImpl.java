package gob.osinergmin.fise.gart.service.impl;

import gob.osinergmin.fise.bean.LiquidacionBean;
import gob.osinergmin.fise.dao.LiquidacionDao;
import gob.osinergmin.fise.domain.FiseLiquidacione;
import gob.osinergmin.fise.gart.service.FiseLiquidacionService;

import java.util.ArrayList;
import java.util.List;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;


@Service(value="fiseLiquidacionServiceImpl")
public class FiseLiquidacionServiceImpl implements FiseLiquidacionService {
	
	Logger logger=Logger.getLogger(FiseLiquidacionServiceImpl.class);
	
	
	@Autowired
	@Qualifier("liquidacionDaoImpl")
	private LiquidacionDao liquidacionDao;
	
	
	/*****Implementacion de metodos********/
	
	@Transactional
	@Override
	public List<LiquidacionBean> listarLiquidaciones(String codEmpresa, 
			long idGrupoInf,String usuario,String terminal,String flagBusq)
			throws Exception{
		List<LiquidacionBean> lista = null;	
		try {	
			int resultadoProceso = 1;
			int resultadoBusq = 1;
			
			if("P".equals(flagBusq)){ 
				resultadoProceso = liquidacionDao.llenarDatosFiseLiquidacion(codEmpresa,
						idGrupoInf, usuario, terminal);
				logger.info("resultado de la operacion de poblar liquidacion: "+resultadoProceso);	
			}else{
				resultadoBusq = 1;
				resultadoProceso =0;
			}
			
			if(resultadoProceso==0 && resultadoBusq==1){
				
				String ultimaEtapa = "---";
				
				List<FiseLiquidacione> listaLiq = liquidacionDao.buscarFiseLiquidacion(codEmpresa, idGrupoInf);	
				lista = new ArrayList<LiquidacionBean>();
				
				for(FiseLiquidacione l : listaLiq){
					LiquidacionBean liq = new LiquidacionBean();
					liq.setCorrelativo(""+l.getCorrelativo()); 
					liq.setCodEmpresa(l.getCodEmpresa());
					liq.setAnioPres(""+l.getAnoPresentacion());
					liq.setMesPres(""+l.getMesPresentacion());
					liq.setAnioEjec(l.getAnoEjecucionGasto()==null? "---": ""+l.getAnoEjecucionGasto());
					liq.setMesEjec(l.getMesEjecucionGasto()==null? "00": ""+l.getMesEjecucionGasto());
					liq.setAnioIniVig(l.getAnoInicioVigencia()==null? "---": ""+l.getAnoInicioVigencia()); 
					liq.setAnioFinVig(l.getAnoFinVigencia()==null? "---" : ""+l.getAnoFinVigencia()); 
					liq.setEtapa(l.getEtapa()==null? "---":l.getEtapa()); 
					liq.setLiquidado(l.getFechaEnvioDefinitivo()==null? "NO" : "SI");
					ultimaEtapa = liquidacionDao.obtenerUltimaEtapa(l.getFormato(), l.getCodEmpresa(), 
							l.getAnoPresentacion(), l.getMesPresentacion(), 
							l.getAnoEjecucionGasto()==null? 0:l.getAnoEjecucionGasto(), 
							l.getMesEjecucionGasto()==null? 0:l.getMesEjecucionGasto(),
							l.getAnoInicioVigencia()==null? 0:l.getAnoInicioVigencia(), 
							l.getAnoFinVigencia()==null? 0:l.getAnoFinVigencia());
					logger.info("ultima etapa de liquidacion:  "+ultimaEtapa); 
					liq.setEtapaReconocido(ultimaEtapa); 
					liq.setFormato(l.getFormato()); 
					lista.add(liq);
				}		
			}				
		} catch (Exception e) {
			logger.info("Ocurrio un error al listar liquidaciones:  "+e);
			lista = new ArrayList<LiquidacionBean>();
			e.printStackTrace();
		}	
		return lista;		
	}
	
	
	@Override
	@Transactional
	public String eliminarDatosLiquidacion(Long id) throws Exception{
		FiseLiquidacione liq =null;
		String valor ="1";
		try {			
			liq = liquidacionDao.obtenerFiseLiquidacion(id);			
			liquidacionDao.eliminarFiseLiquidacion(liq); 			
		} catch (Exception e) {
			logger.info("Error al eliminar  liquidacion: "+e); 
			valor = "0";
		}finally{
			if(liq !=null){
				liq =null;
			}
		}
		return valor;
	}
	
	@Override
	@Transactional(propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
	public String prepararLiquidacion(List<LiquidacionBean> lista, 
			String usuario,String terminal) throws Exception{
		String valor ="1";
		int valorPrepara;
		try {			
			for(LiquidacionBean l : lista){				
				valorPrepara = liquidacionDao.preparaLiquidacionFormato(new Long(l.getCorrelativo()),
						usuario, terminal);	
				logger.info("valor al preparar la liquidacion:  "+valorPrepara); 
				if(valorPrepara!=0){
					valor ="0";
					break;			
				}
			}		
		} catch (Exception e) {
			logger.info("Ocurrio un error al preparar la liquidacion:  "+e); 
			valor ="0";
		}
		return valor;
	}
	
	@Override
	@Transactional(propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
	public String liquidarFormatos(List<LiquidacionBean> lista, 
			String usuario,String terminal) throws Exception{
		String valor ="1";
		int valorLiquiq;
		try {			
			for(LiquidacionBean l : lista){				
				valorLiquiq = liquidacionDao.liquidarFormato(new Long(l.getCorrelativo()),
						usuario, terminal);	
				logger.info("valor al  liquidar:  "+valorLiquiq); 
				if(valorLiquiq!=0){
					valor ="0";
					break;			
				}
			}		
		} catch (Exception e) {
			logger.info("Ocurrio un error al liquidar formatos:  "+e); 
			valor ="0";
		}
		return valor;
	}
	
	
	

}
