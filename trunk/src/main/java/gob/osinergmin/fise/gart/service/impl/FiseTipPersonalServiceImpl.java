package gob.osinergmin.fise.gart.service.impl;

import gob.osinergmin.fise.bean.TipoPersonalBean;
import gob.osinergmin.fise.dao.FiseTipPersonalDao;
import gob.osinergmin.fise.domain.FiseTipPersonal;
import gob.osinergmin.fise.gart.service.FiseTipPersonalService;
import gob.osinergmin.fise.util.FechaUtil;

import java.util.List;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service(value="fiseTipPersonalServiceImpl")
public class FiseTipPersonalServiceImpl implements FiseTipPersonalService {

	Logger logger=Logger.getLogger(FiseTipPersonalServiceImpl.class);
	
	@Autowired
	@Qualifier("fiseTipPersonalDaoImpl")
	private FiseTipPersonalDao fiseTipPersonalDao;
	
	/***Implementacion de metodos*/
	
	@Override
	@Transactional
	public List<FiseTipPersonal> listarTipoPersonal() throws Exception{
		return fiseTipPersonalDao.listarFiseTipPersonal();
	}
	
	
	@Override
	@Transactional
	public String insertarDatosFiseTipPersonal(TipoPersonalBean bean) throws Exception{
		FiseTipPersonal personal =null;
		String valor="1";
		try {
			personal = fiseTipPersonalDao.obtenerFiseTipPersonalByPK(bean.getId()!=null?Long.parseLong(bean.getId()):0);	
			if(personal==null){
				personal = new FiseTipPersonal();
				
				long idMax = fiseTipPersonalDao.buscarMaximoIdTipPersonal();
				
				personal.setIdTipPersonal(idMax); 
				personal.setDescripcion(bean.getDescripcion());					
				//auditoria
				personal.setUsuarioCreacion(bean.getUsuario());
				personal.setTerminalCreacion(bean.getTerminal()); 
				personal.setFechaCreacion(FechaUtil.obtenerFechaActual()); 
				fiseTipPersonalDao.insertarFiseTipPersonal(personal); 			
			}else{
				valor = "2";		
			}			
		} catch (Exception e) {
			logger.info("Error al grabar en tipo de personal: "+e); 
			valor = "0";
		}finally{
			if(personal !=null){
				personal =null;
			}
		}
		return valor;
		
	}
	
	@Override
	@Transactional
	public String actualizarDatosFiseTipPersonal(TipoPersonalBean bean) throws Exception{
		FiseTipPersonal personal =null;
		String valor ="1";
		try {			
			personal = fiseTipPersonalDao.obtenerFiseTipPersonalByPK(bean.getId()!=null?Long.parseLong(bean.getId()):0);			
			personal.setDescripcion(bean.getDescripcion());			
			//auditoria
			personal.setUsuarioActualizacion(bean.getUsuario());
			personal.setTerminalActualizacion(bean.getTerminal()); 
			personal.setFechaActualizacion(FechaUtil.obtenerFechaActual()); 
			fiseTipPersonalDao.actualizarFiseTipPersonal(personal); 
		} catch (Exception e) {
			logger.info("Error al actualizar en tipo de personal: "+e); 
			valor = "0";
		}finally{
			if(personal !=null){
				personal =null;
			}
		}
		return valor;
		
	}
	
	@Override
	@Transactional
	public String eliminarDatosFiseTipPersonal(String id) throws Exception{
		FiseTipPersonal personal =null;
		String valor ="1";
		try {			
			personal = fiseTipPersonalDao.obtenerFiseTipPersonalByPK(id!=null?Long.parseLong(id):0);			
			fiseTipPersonalDao.eliminarFiseTipPersonal(personal); 			
		} catch (Exception e) {
			logger.info("Error al eliminar  tipo de personal: "+e); 
			valor = "0";
		}finally{
			if(personal !=null){
				personal =null;
			}
		}
		return valor;
	}	
	
	@Override
	@Transactional
	public TipoPersonalBean buscarFiseTipPersonalEditar(String id) throws Exception{
		FiseTipPersonal p =null;
		TipoPersonalBean bean =new TipoPersonalBean();
		try {
			p = fiseTipPersonalDao.obtenerFiseTipPersonalByPK(id!=null?Long.parseLong(id):0);
			bean.setId(""+p.getIdTipPersonal());
			bean.setDescripcion(p.getDescripcion()); 					
		} catch (Exception e) {
			logger.info("Error al buscar datos tipo de personal para editar:  "+e); 
		}finally{
			if(p!=null)
				p=null;
		}
		return bean;
	}
	
	@Override
	@Transactional
	public List<FiseTipPersonal> buscarFiseTipPersonal(String id, String descripcion)
			throws Exception{
		return fiseTipPersonalDao.buscarFiseTipPersonal(id, descripcion);
	}
	
	
	@Override
	@Transactional
	public String obtenerIdTipPersonal() throws Exception{	
		String id = "0";
		long valor =0;
		try{
			valor = fiseTipPersonalDao.buscarMaximoIdTipPersonal();	
			id = ""+valor;
		} catch (Exception e) {
			logger.info("Error al obtener el max id de tipo de doc: "+e); 
			id = "0";
		}
		return id;
	}	
	
	
}
