package gob.osinergmin.fise.gart.service.impl;

import gob.osinergmin.fise.bean.FiseCargoFijoBean;
import gob.osinergmin.fise.dao.FiseCargoFijoDao;
import gob.osinergmin.fise.domain.FiseMcargofijo;
import gob.osinergmin.fise.domain.FiseMcargofijoPK;
import gob.osinergmin.fise.gart.service.FiseCargoFijoService;
import gob.osinergmin.fise.util.FechaUtil;

import java.math.BigDecimal;
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
			pk.setEmpcod(bean.getCodEmpresa());
			pk.setFaniorep(Long.valueOf(bean.getAnioRep())); 
			pk.setFmesrep(Long.valueOf(bean.getMesRep()));	 
			
			cargo = fiseCargoFijoDao.obtenerFiseCargoFijo(pk);
			
			if(cargo==null){
				cargo = new FiseMcargofijo();
				cargo.setId(pk);
				cargo.setCfinumage(Long.valueOf(bean.getNumAgen()));
				cargo.setCfinumusuben(Long.valueOf(bean.getNumUsuBenef()));
				cargo.setCfinumusuemp(Long.valueOf(bean.getNumUsuEmp()));
				cargo.setCfinumvaldcan(Long.valueOf(bean.getNumValDCan()));
				cargo.setCfinumvaldemi(Long.valueOf(bean.getNumValDEmi()));
				cargo.setCfinumvalfcan(Long.valueOf(bean.getNumValFCan()));
				cargo.setCfinumvalfemi(Long.valueOf(bean.getNumValFEmi()));
				cargo.setCfimontracan(new BigDecimal(bean.getMontoCanje()));
				cargo.setCfimon(new BigDecimal(bean.getMontoMes()));
				cargo.setCfiigv(new BigDecimal(bean.getIgv()));
				cargo.setCfiapliigv(Integer.valueOf(bean.getAplicaIgv())); 
				//opcionales
				cargo.setCficom(bean.getGloza());
				cargo.setCfifecinf(bean.getFechaSustento()==null? null:
					FechaUtil.getFechaStringToDate(bean.getFechaSustento())); 
				cargo.setCfidoc(bean.getNumDoc());
				cargo.setScficod(bean.getEstado()==null? 1: Integer.valueOf(bean.getEstado())); 
				cargo.setCfifecrec(bean.getFechaRecepcion()==null? null:
					FechaUtil.getFechaStringToDate(bean.getFechaRecepcion()));
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
			pk.setEmpcod(bean.getCodEmpresa());
			pk.setFaniorep(Long.valueOf(bean.getAnioRep())); 
			pk.setFmesrep(Long.valueOf(bean.getMesRep()));	
			
			cargo = fiseCargoFijoDao.obtenerFiseCargoFijo(pk);	
			
			cargo.setCfinumage(Long.valueOf(bean.getNumAgen()));
			cargo.setCfinumusuben(Long.valueOf(bean.getNumUsuBenef()));
			cargo.setCfinumusuemp(Long.valueOf(bean.getNumUsuEmp()));
			cargo.setCfinumvaldcan(Long.valueOf(bean.getNumValDCan()));
			cargo.setCfinumvaldemi(Long.valueOf(bean.getNumValDEmi()));
			cargo.setCfinumvalfcan(Long.valueOf(bean.getNumValFCan()));
			cargo.setCfinumvalfemi(Long.valueOf(bean.getNumValFEmi()));
			cargo.setCfimontracan(new BigDecimal(bean.getMontoCanje()));
			cargo.setCfimon(new BigDecimal(bean.getMontoMes()));
			cargo.setCfiigv(new BigDecimal(bean.getIgv()));
			cargo.setCfiapliigv(Integer.valueOf(bean.getAplicaIgv())); 
			//opcionales
			cargo.setCficom(bean.getGloza());
			cargo.setCfifecinf(bean.getFechaSustento()==null? null:
				FechaUtil.getFechaStringToDate(bean.getFechaSustento())); 
			cargo.setCfidoc(bean.getNumDoc());
			cargo.setScficod(bean.getEstado()==null? 1: Integer.valueOf(bean.getEstado())); 
			cargo.setCfifecrec(bean.getFechaRecepcion()==null? null:
				FechaUtil.getFechaStringToDate(bean.getFechaRecepcion()));
			cargo.setCfidocrec(bean.getNumDocRecepcion());
			
			//datos de auditoria
			cargo.setUsuarioActualizacion(bean.getUsuario());
			cargo.setTerminalActualizacion(bean.getTerminal()); 
			cargo.setFechaActualizacion(FechaUtil.obtenerFechaActual()); 
			
			fiseCargoFijoDao.actualizarFiseObservacion(cargo); 
		} catch (Exception e) {
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
			
			bean.setCodEmpresa(codEmpresa);
			bean.setAnioRep(""+anio);
			bean.setMesRep(""+mes);
			bean.setNumAgen(""+c.getCfinumage());
			bean.setNumUsuBenef(""+c.getCfinumusuben());
			bean.setNumUsuEmp(""+c.getCfinumusuemp());
			bean.setNumValDCan(""+c.getCfinumvaldcan());
			bean.setNumValDEmi(""+c.getCfinumvaldemi());
			bean.setNumValFCan(""+c.getCfinumvalfcan());
			bean.setNumValFEmi(""+c.getCfinumvalfemi());
			bean.setMontoCanje(""+c.getCfimontracan());
			bean.setMontoMes(""+c.getCfimon());
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
			
		} catch (Exception e) {
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
	public List<FiseMcargofijo> buscarFiseCargoFijo(String codEmpresa, Long anioRep,Long mesRep)
			throws Exception{
		return fiseCargoFijoDao.buscarFiseCargoFijo(codEmpresa, anioRep, mesRep);
	}
	

}
