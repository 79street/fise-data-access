package gob.osinergmin.fise.gart.service.impl;

import gob.osinergmin.fise.bean.FiseParametroBean;
import gob.osinergmin.fise.dao.FiseParametroDao;
import gob.osinergmin.fise.domain.FiseParametro;
import gob.osinergmin.fise.gart.service.FiseParametroGartService;
import gob.osinergmin.fise.util.FechaUtil;

import java.util.List;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service(value="fiseParametroGartServiceImpl")
public class FiseParametroGartServiceImpl implements FiseParametroGartService {
	
	Logger logger=Logger.getLogger(FiseParametroGartServiceImpl.class);
	
	@Autowired
	@Qualifier("fiseParametroDaoImpl")
	private FiseParametroDao fiseParametroDao;
	
	@Override
	@Transactional
	public List<FiseParametro> listarFiseParametro() {
		return fiseParametroDao.listarFiseParametro();
	}
	
	
	@Override
	@Transactional
	public String insertarDatosFiseParametro(FiseParametroBean bean) throws Exception{
		FiseParametro param =null;
		String valor="1";
		try {
			param = fiseParametroDao.obtenerFiseParametro(bean.getCodigo());	
			if(param==null){
				param = new FiseParametro();
				param.setCodigo(bean.getCodigo()); 
				param.setNombre(bean.getNombre());	
				param.setValor(bean.getValor()); 
				param.setOrden(Long.parseLong(bean.getOrden()));
				//auditoria
				param.setUsuarioCreacion(bean.getUsuario());
				param.setTerminalCreacion(bean.getTerminal()); 
				param.setFechaCreacion(FechaUtil.obtenerFechaActual()); 
				param.setUsuarioActualizacion(bean.getUsuario());
				param.setTerminalActualizacion(bean.getTerminal()); 
				param.setFechaActualizacion(FechaUtil.obtenerFechaActual()); 
				
				fiseParametroDao.insertarFiseParametro(param); 			
			}else{
				valor = "2";		
			}			
		} catch (Exception e) {
			logger.info("Error al grabar en Parametros: "+e); 
			valor = "0";
		}finally{
			if(param !=null){
				param =null;
			}
		}
		return valor;
		
	}
	
	@Override
	@Transactional
	public String actualizarDatosFiseParametro(FiseParametroBean bean) throws Exception{
		FiseParametro param =null;
		String valor ="1";
		try {			
			param = fiseParametroDao.obtenerFiseParametro(bean.getCodigo());			
			param.setNombre(bean.getNombre());		
			param.setValor(bean.getValor());	
			param.setOrden(Long.parseLong(bean.getOrden()));
			//auditoria
			param.setUsuarioActualizacion(bean.getUsuario());
			param.setTerminalActualizacion(bean.getTerminal()); 
			param.setFechaActualizacion(FechaUtil.obtenerFechaActual()); 
			fiseParametroDao.actualizarFiseParametro(param); 
		} catch (Exception e) {
			logger.info("Error al actualizar en Parametroes: "+e); 
			valor = "0";
		}finally{
			if(param !=null){
				param =null;
			}
		}
		return valor;
		
	}
	
	@Override
	@Transactional
	public String eliminarDatosFiseParametro(String codigo) throws Exception{
		FiseParametro param =null;
		String valor ="1";
		try {			
			param = fiseParametroDao.obtenerFiseParametro(codigo);			
			fiseParametroDao.eliminarFiseParametro(param); 			
		} catch (Exception e) {
			logger.info("Error al eliminar  Parametro: "+e); 
			valor = "0";
		}finally{
			if(param !=null){
				param =null;
			}
		}
		return valor;
	}
	
	@Override
	@Transactional
	public FiseParametro obtenerFiseParametro(String codigo) throws Exception{
		return fiseParametroDao.obtenerFiseParametro(codigo);
		
	}
	
	@Override
	@Transactional
	public FiseParametroBean buscarFiseObsEditar(String codigo) throws Exception{
		FiseParametro p =null;
		FiseParametroBean bean =new FiseParametroBean();
		try {
			p = fiseParametroDao.obtenerFiseParametro(codigo);
			bean.setCodigo(p.getCodigo());
			bean.setNombre(p.getNombre()); 
			bean.setValor(p.getValor());
			bean.setOrden(p.getOrden().toString());
			
		} catch (Exception e) {
			logger.info("Error al buscar datos para editar:  "+e); 
		}finally{
			if(p!=null)
				p=null;
		}
		return bean;
	}
	
	@Override
	@Transactional
	public List<FiseParametro> buscarFiseParametro(String codigo, String nombre) throws Exception{
		return fiseParametroDao.buscarFiseParametro(codigo, nombre);
	}
	


}
