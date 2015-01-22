package gob.osinergmin.fise.gart.service.impl;

import gob.osinergmin.fise.bean.FiseObservacionBean;
import gob.osinergmin.fise.constant.FiseConstants;
import gob.osinergmin.fise.dao.FiseObservacionDao;
import gob.osinergmin.fise.domain.FiseObservacion;
import gob.osinergmin.fise.gart.service.FiseObservacionGartService;
import gob.osinergmin.fise.util.FechaUtil;

import java.util.List;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service(value="fiseObservacionGartServiceImpl")
public class FiseObservacionGartServiceImpl implements FiseObservacionGartService {
	
	Logger logger=Logger.getLogger(FiseObservacionGartServiceImpl.class);
	
	@Autowired
	@Qualifier("fiseObservacionDaoImpl")
	private FiseObservacionDao fiseObservacionDao;
	
	@Override
	@Transactional
	public List<FiseObservacion> listarFiseObservacion() {
		return fiseObservacionDao.listarFiseObservacion();
	}
	
	
	@Override
	@Transactional
	public String insertarDatosFiseObservacion(FiseObservacionBean bean) throws Exception{
		FiseObservacion obs =null;
		String valor="1";
		try {
			obs = fiseObservacionDao.obtenerFiseObservacion(bean.getId());	
			if(obs==null){
				obs = new FiseObservacion();
				obs.setIdObservacion(bean.getId()); 
				obs.setDescripcion(bean.getDescripcion());	
				obs.setOrigen(FiseConstants.OBSERVACION_AUTOMATICO); 
				//auditoria
				obs.setUsuarioCreacion(bean.getUsuario());
				obs.setTerminalCreacion(bean.getTerminal()); 
				obs.setFechaCreacion(FechaUtil.obtenerFechaActual()); 
				fiseObservacionDao.insertarFiseObservacion(obs); 			
			}else{
				valor = "2";		
			}			
		} catch (Exception e) {
			logger.info("Error al grabar en observaciones: "+e); 
			valor = "0";
		}finally{
			if(obs !=null){
				obs =null;
			}
		}
		return valor;
		
	}
	
	@Override
	@Transactional
	public String actualizarDatosFiseObservacion(FiseObservacionBean bean) throws Exception{
		FiseObservacion obs =null;
		String valor ="1";
		try {			
			obs = fiseObservacionDao.obtenerFiseObservacion(bean.getId());			
			obs.setDescripcion(bean.getDescripcion());			
			//auditoria
			obs.setUsuarioActualizacion(bean.getUsuario());
			obs.setTerminalActualizacion(bean.getTerminal()); 
			obs.setFechaActualizacion(FechaUtil.obtenerFechaActual()); 
			fiseObservacionDao.actualizarFiseObservacion(obs); 
		} catch (Exception e) {
			logger.info("Error al actualizar en observaciones: "+e); 
			valor = "0";
		}finally{
			if(obs !=null){
				obs =null;
			}
		}
		return valor;
		
	}
	
	@Override
	@Transactional
	public String eliminarDatosFiseObservacion(String id) throws Exception{
		FiseObservacion obs =null;
		String valor ="1";
		try {			
			obs = fiseObservacionDao.obtenerFiseObservacion(id);			
			fiseObservacionDao.eliminarFiseObservacion(obs); 			
		} catch (Exception e) {
			logger.info("Error al eliminar  observacion: "+e); 
			valor = "0";
		}finally{
			if(obs !=null){
				obs =null;
			}
		}
		return valor;
	}
	
	@Override
	@Transactional
	public FiseObservacion obtenerFiseObservacion(String id) throws Exception{
		return fiseObservacionDao.obtenerFiseObservacion(id);
		
	}
	
	@Override
	@Transactional
	public FiseObservacionBean buscarFiseObsEditar(String id) throws Exception{
		FiseObservacion o =null;
		FiseObservacionBean bean =new FiseObservacionBean();
		try {
			o = fiseObservacionDao.obtenerFiseObservacion(id);
			bean.setId(o.getIdObservacion());
			bean.setDescripcion(o.getDescripcion()); 					
		} catch (Exception e) {
			logger.info("Error al buscar datos para editar:  "+e); 
		}finally{
			if(o!=null)
				o=null;
		}
		return bean;
	}
	
	@Override
	@Transactional
	public List<FiseObservacion> buscarFiseObservacion(String id, String descripcion)
			throws Exception{
		return fiseObservacionDao.buscarFiseObservacion(id, descripcion);
	}
	
	
	@Override
	@Transactional
	public String obtenerIdObservacion() throws Exception{
		return fiseObservacionDao.obtenerIdObservacion();
	}

}
