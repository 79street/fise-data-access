package gob.osinergmin.fise.gart.service.impl;

import gob.osinergmin.fise.bean.FiseCargoFijoBean;
import gob.osinergmin.fise.dao.FiseCargoFijoDao;
import gob.osinergmin.fise.domain.FiseMcargofijo;
import gob.osinergmin.fise.domain.FiseMcargofijoPK;
import gob.osinergmin.fise.gart.service.FiseCargoFijoService;
import gob.osinergmin.fise.util.FechaUtil;
import gob.osinergmin.fise.util.FormatoUtil;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;


@Service(value="fiseCargoFijoServiceImpl")
public class FiseCargoFijoServiceImpl implements FiseCargoFijoService {
	
	
	Logger logger=Logger.getLogger(FiseCargoFijoServiceImpl.class);
	
	@Autowired
	@Qualifier("fiseCargoFijoDaoImpl")
	private FiseCargoFijoDao fiseCargoFijoDao;
	
	
	
	@Override
	@Transactional
	public String insertarDatosFiseCargoFijo(FiseCargoFijoBean bean) throws Exception{
		FiseMcargofijo cargo =null;
		FiseMcargofijoPK pk = null;
		String valor="1";
		try {
			pk = new FiseMcargofijoPK();
			pk.setEmpcod(bean.getCodigoEmpresa());
			pk.setFaniorep(Long.valueOf(bean.getAnioReporte())); 
			pk.setFmesrep(Long.valueOf(bean.getMesReporte()));	 
			
			cargo = fiseCargoFijoDao.obtenerFiseCargoFijo(pk);
			
			if(cargo==null){
				cargo = new FiseMcargofijo();
				cargo.setId(pk);
				
				cargo.setCfinumageRural(Long.valueOf(bean.getNumAgente()));
				cargo.setCfinumageUrbProv(Long.valueOf(bean.getNumAgente()));
				cargo.setCfinumageUrbLima(Long.valueOf(bean.getNumAgente()));
				
				cargo.setCfinumusubenRural(Long.valueOf(bean.getNumUsuBenef()));
				cargo.setCfinumusubenUrbProv(Long.valueOf(bean.getNumUsuBenef()));
				cargo.setCfinumusubenUrbLima(Long.valueOf(bean.getNumUsuBenef()));
				
				cargo.setCfinumusuempRural(Long.valueOf(bean.getNumUsuEmp()));
				cargo.setCfinumusuempUrbProv(Long.valueOf(bean.getNumUsuEmp()));
				cargo.setCfinumusuempUrbLima(Long.valueOf(bean.getNumUsuEmp()));
				
				cargo.setCfinumvaldcanRural(Long.valueOf(bean.getNumValDCan()));
				cargo.setCfinumvaldcanUrbProv(Long.valueOf(bean.getNumValDCan()));
				cargo.setCfinumvaldcanUrbLima(Long.valueOf(bean.getNumValDCan()));
				
				cargo.setCfinumvaldemiRural(Long.valueOf(bean.getNumValDEmi()));
				cargo.setCfinumvaldemiUrbProv(Long.valueOf(bean.getNumValDEmi()));
				cargo.setCfinumvaldemiUrbLima(Long.valueOf(bean.getNumValDEmi()));
				
				cargo.setCfinumvalfcanRural(Long.valueOf(bean.getNumValFCan()));
				cargo.setCfinumvalfcanUrbProv(Long.valueOf(bean.getNumValFCan()));
				cargo.setCfinumvalfcanUrbLima(Long.valueOf(bean.getNumValFCan()));
				
				cargo.setCfinumvalfemiRural(Long.valueOf(bean.getNumValFEmi()));
				cargo.setCfinumvalfemiUrbProv(Long.valueOf(bean.getNumValFEmi()));
				cargo.setCfinumvalfemiUrbLima(Long.valueOf(bean.getNumValFEmi()));
				
				cargo.setCfimontracan(new BigDecimal(bean.getMontoCanje()));
			
				cargo.setCfimonRural(new BigDecimal(bean.getMontoMes()));
				cargo.setCfimonUrbProv(new BigDecimal(bean.getMontoMes()));
				cargo.setCfimonUrbLima(new BigDecimal(bean.getMontoMes()));			
				
				
				cargo.setCfiigv(new BigDecimal(bean.getIgv()));
				cargo.setCfiapliigv(Integer.valueOf(bean.getAplicaIgv())); 
				//opcionales
				cargo.setCficom(bean.getGloza());
				
				cargo.setCfifecinf(bean.getFechaSustento()==null? null:
					FechaUtil.getFechaStringToDateDDMMYYY(bean.getFechaSustento()));
				
				cargo.setCfidoc(bean.getNumDoc());
				
				cargo.setScficod(bean.getEstado()==null? 1: Integer.valueOf(bean.getEstado())); 
				
				cargo.setCfifecrec(bean.getFechaRecepcion()==null? null:
					FechaUtil.getFechaStringToDateDDMMYYY(bean.getFechaRecepcion()));
				
				cargo.setCfidocrec(bean.getNumDocRecepcion());
				//auditoria
				cargo.setUsuarioCreacion(bean.getUsuario());
				cargo.setTerminalCreacion(bean.getTerminal()); 
				cargo.setFechaCreacion(FechaUtil.obtenerFechaActual()); 
				
				fiseCargoFijoDao.insertarFiseCargoFijo(cargo); 			
			}else{
				valor = "2";		
			}			
		} catch (Exception e) {
			logger.info("Error al grabar en fise cargo fijo: "+e); 
			valor = "0";
		}finally{
			if(cargo !=null){
				cargo =null;
			}
			if(pk !=null){
				pk =null;
			}
		}
		return valor;
		
	}
	
	@Override
	@Transactional
	public String actualizarDatosFiseCargoFijo(FiseCargoFijoBean bean) throws Exception{
		FiseMcargofijo cargo =null;
		FiseMcargofijoPK pk = null;
		String valor ="1";
		try {			
			pk = new FiseMcargofijoPK();
			pk.setEmpcod(bean.getCodigoEmpresa());
			pk.setFaniorep(Long.valueOf(bean.getAnioReporte())); 
			pk.setFmesrep(Long.valueOf(bean.getMesReporte()));	
			
			cargo = fiseCargoFijoDao.obtenerFiseCargoFijo(pk);			
			
			cargo.setCfinumageRural(Long.valueOf(bean.getNumAgente()));
			cargo.setCfinumageUrbProv(Long.valueOf(bean.getNumAgente()));
			cargo.setCfinumageUrbLima(Long.valueOf(bean.getNumAgente()));
			
			cargo.setCfinumusubenRural(Long.valueOf(bean.getNumUsuBenef()));
			cargo.setCfinumusubenUrbProv(Long.valueOf(bean.getNumUsuBenef()));
			cargo.setCfinumusubenUrbLima(Long.valueOf(bean.getNumUsuBenef()));
			
			cargo.setCfinumusuempRural(Long.valueOf(bean.getNumUsuEmp()));
			cargo.setCfinumusuempUrbProv(Long.valueOf(bean.getNumUsuEmp()));
			cargo.setCfinumusuempUrbLima(Long.valueOf(bean.getNumUsuEmp()));
			
			cargo.setCfinumvaldcanRural(Long.valueOf(bean.getNumValDCan()));
			cargo.setCfinumvaldcanUrbProv(Long.valueOf(bean.getNumValDCan()));
			cargo.setCfinumvaldcanUrbLima(Long.valueOf(bean.getNumValDCan()));
			
			cargo.setCfinumvaldemiRural(Long.valueOf(bean.getNumValDEmi()));
			cargo.setCfinumvaldemiUrbProv(Long.valueOf(bean.getNumValDEmi()));
			cargo.setCfinumvaldemiUrbLima(Long.valueOf(bean.getNumValDEmi()));
			
			cargo.setCfinumvalfcanRural(Long.valueOf(bean.getNumValFCan()));
			cargo.setCfinumvalfcanUrbProv(Long.valueOf(bean.getNumValFCan()));
			cargo.setCfinumvalfcanUrbLima(Long.valueOf(bean.getNumValFCan()));
			
			cargo.setCfinumvalfemiRural(Long.valueOf(bean.getNumValFEmi()));
			cargo.setCfinumvalfemiUrbProv(Long.valueOf(bean.getNumValFEmi()));
			cargo.setCfinumvalfemiUrbLima(Long.valueOf(bean.getNumValFEmi()));
			
			cargo.setCfimontracan(new BigDecimal(bean.getMontoCanje()));
		
			cargo.setCfimonRural(new BigDecimal(bean.getMontoMes()));
			cargo.setCfimonUrbProv(new BigDecimal(bean.getMontoMes()));
			cargo.setCfimonUrbLima(new BigDecimal(bean.getMontoMes()));	
			
			
			cargo.setCfiigv(new BigDecimal(bean.getIgv()));
			cargo.setCfiapliigv(Integer.valueOf(bean.getAplicaIgv())); 
			//opcionales
			cargo.setCficom(bean.getGloza());
			
			cargo.setCfifecinf(bean.getFechaSustento()==null? null:
				FechaUtil.getFechaStringToDateDDMMYYY(bean.getFechaSustento())); 
			
			cargo.setCfidoc(bean.getNumDoc());
			
			cargo.setScficod(bean.getEstado()==null? 1: Integer.valueOf(bean.getEstado())); 
			
			cargo.setCfifecrec(bean.getFechaRecepcion()==null? null:
				FechaUtil.getFechaStringToDateDDMMYYY(bean.getFechaRecepcion()));
			
			cargo.setCfidocrec(bean.getNumDocRecepcion());
			
			//datos de auditoria
			cargo.setUsuarioActualizacion(bean.getUsuario());
			cargo.setTerminalActualizacion(bean.getTerminal()); 
			cargo.setFechaActualizacion(FechaUtil.obtenerFechaActual()); 
			
			fiseCargoFijoDao.actualizarFiseObservacion(cargo); 
		} catch (Exception e) {
			e.printStackTrace();
			logger.info("Error al actualizar en fise cargo fijo: "+e); 
			valor = "0";
		}finally{
			if(cargo !=null){
				cargo =null;
			}
			if(pk !=null){
				pk =null;
			}
		}
		return valor;
		
	}
	
	@Override
	@Transactional
	public String eliminarDatosFiseCargoFijo(String codEmpresa,
			String anio,String mes,String usuario,String terminal) throws Exception{		
		FiseMcargofijo cargo =null;
		FiseMcargofijoPK pk = null;
		String valor ="1";
		try {			
			pk = new FiseMcargofijoPK();
			pk.setEmpcod(codEmpresa);
			pk.setFaniorep(Long.valueOf(anio)); 
			pk.setFmesrep(Long.valueOf(mes));	
			
			cargo = fiseCargoFijoDao.obtenerFiseCargoFijo(pk);	
			cargo.setScficod(0); //cambio de estado a inactivo
			//datos de auditoria
			cargo.setUsuarioActualizacion(usuario);
			cargo.setTerminalActualizacion(terminal); 
			cargo.setFechaActualizacion(FechaUtil.obtenerFechaActual()); 
			
			fiseCargoFijoDao.actualizarFiseObservacion(cargo); 
			
		} catch (Exception e) {
			logger.info("Error al eliminar  observacion: "+e); 
			valor = "0";
		}finally{
			if(cargo !=null){
				cargo =null;
			}
			if(pk !=null){
				pk =null;
			}
		}
		return valor;
	}
	
	@Override
	@Transactional
	public FiseMcargofijo obtenerFiseCargofijo(String codEmpresa,
			String anio,String mes) throws Exception{
		
		FiseMcargofijoPK pk = new FiseMcargofijoPK();
		pk.setEmpcod(codEmpresa);
		pk.setFaniorep(Long.valueOf(anio)); 
		pk.setFmesrep(Long.valueOf(mes));		
		
		return fiseCargoFijoDao.obtenerFiseCargoFijo(pk);
		
	}
	
	@Override
	@Transactional
	public FiseCargoFijoBean buscarFiseCargoFijoEditar(String codEmpresa,
			String anio,String mes) throws Exception{		
		FiseMcargofijo c =null;
		FiseMcargofijoPK pk = null;
		FiseCargoFijoBean bean =new FiseCargoFijoBean();
		try {
			pk = new FiseMcargofijoPK();
			pk.setEmpcod(codEmpresa);
			pk.setFaniorep(Long.valueOf(anio)); 
			pk.setFmesrep(Long.valueOf(mes));
			
			c= fiseCargoFijoDao.obtenerFiseCargoFijo(pk);	
			if(c!=null){
				String codEmpreCompleta = FormatoUtil.rellenaDerecha(c.getId().getEmpcod(), ' ', 4);
				bean.setCodigoEmpresa(codEmpreCompleta);
				bean.setAnioReporte(""+c.getId().getFaniorep());
				bean.setMesReporte(""+c.getId().getFmesrep());
				bean.setNumAgente(""+c.getCfinumageRural());
				bean.setNumUsuBenef(""+c.getCfinumusubenRural());
				bean.setNumUsuEmp(""+c.getCfinumusuempRural());
				bean.setNumValDCan(""+c.getCfinumvaldcanRural());
				bean.setNumValDEmi(""+c.getCfinumvaldemiRural());
				bean.setNumValFCan(""+c.getCfinumvalfcanRural());
				bean.setNumValFEmi(""+c.getCfinumvalfemiRural());
				bean.setMontoCanje(""+c.getCfimontracan());
				bean.setMontoMes(""+c.getCfimonRural());
				bean.setIgv(""+c.getCfiigv());
				bean.setAplicaIgv(""+c.getCfiapliigv());		
				//opcionales
				bean.setGloza(c.getCficom()==null? "":c.getCficom());
				bean.setFechaSustento(c.getCfifecinf()==null? "":
					FechaUtil.getFechaDateToString(c.getCfifecinf())); 
				bean.setNumDoc(c.getCfidoc()==null ? "":c.getCfidoc());
				bean.setEstado(c.getScficod()==null?"": ""+c.getScficod());
				bean.setFechaRecepcion(c.getCfifecrec()==null? "" :
					FechaUtil.getFechaDateToString(c.getCfifecrec()) );
				bean.setNumDocRecepcion(c.getCfidocrec()==null ? "" : c.getCfidocrec());
			}		
		} catch (Exception e) {
			e.printStackTrace();
			logger.info("Error al buscar datos para editar:  "+e); 
		}finally{
			if(c !=null){
				c =null;
			}
			if(pk !=null){
				pk =null;
			}
		}
		return bean;
	}
	
	@Override
	@Transactional
	public List<FiseCargoFijoBean> buscarFiseCargoFijo(String codEmpresa, Long anioRep,Long mesRep)
			throws Exception{
		
		List<FiseCargoFijoBean> listaCargosFijos= new ArrayList<FiseCargoFijoBean>();
		FiseCargoFijoBean bean=null;
		try {
			List<FiseMcargofijo> lista = fiseCargoFijoDao.buscarFiseCargoFijo(codEmpresa, anioRep, mesRep);
			
			for(FiseMcargofijo cargo : lista){ 
					bean = new FiseCargoFijoBean();
					bean.setCodigoEmpresa(cargo.getId().getEmpcod()); 
	  				bean.setAnioReporte(""+cargo.getId().getFaniorep());
	  				bean.setMesReporte(""+cargo.getId().getFmesrep());   
					bean.setNumAgente(""+cargo.getCfinumageRural());
					bean.setNumUsuBenef(""+cargo.getCfinumusubenRural());
					bean.setNumUsuEmp(""+cargo.getCfinumusuempRural());
					bean.setNumValDCan(""+cargo.getCfinumvaldcanRural());
					bean.setNumValDEmi(""+cargo.getCfinumvaldemiRural());
					bean.setNumValFCan(""+cargo.getCfinumvalfcanRural());
					bean.setNumValFEmi(""+cargo.getCfinumvalfemiRural());
					bean.setMontoCanje(""+cargo.getCfimontracan());
					bean.setMontoMes(""+cargo.getCfimonRural());
					bean.setIgv(""+cargo.getCfiigv());
					//bean.setAplicaIgv(""+cargo.getCfiapliigv());		
					//opcionales
					bean.setGloza(cargo.getCficom()==null? "":cargo.getCficom());
					bean.setFechaSustento(cargo.getCfifecinf()==null? "":
						FechaUtil.getFechaDateToString(cargo.getCfifecinf())); 
					bean.setNumDoc(cargo.getCfidoc()==null ? "":cargo.getCfidoc());					
					bean.setFechaRecepcion(cargo.getCfifecrec()==null? "" :
						FechaUtil.getFechaDateToString(cargo.getCfifecrec()) );
					bean.setNumDocRecepcion(cargo.getCfidocrec()==null ? "" : cargo.getCfidocrec());
					
					if(1==cargo.getScficod()){ 
						bean.setDesEstado("Activo");			
					}else{
						bean.setDesEstado("Inactivo");			
					}

					if(1==cargo.getCfiapliigv()){ 
						bean.setAplicaIgv("SI");			
					}else{
						bean.setAplicaIgv("NO");			
					}
					
					listaCargosFijos.add(bean);
				}   			
		} catch (Exception e) {
			logger.info("Error al listar cargos fijos:  "+e); 
		}finally{
			if(bean!=null){
				bean =null;
			}
		}
		return listaCargosFijos;
	}
	

}
