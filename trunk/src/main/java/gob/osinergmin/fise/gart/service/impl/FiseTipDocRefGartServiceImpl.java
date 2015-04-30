package gob.osinergmin.fise.gart.service.impl;

import gob.osinergmin.fise.bean.TipDocReferenciaBean;
import gob.osinergmin.fise.dao.FiseTipDocRefDao;
import gob.osinergmin.fise.domain.FiseTipDocRef;
import gob.osinergmin.fise.gart.service.FiseTipDocRefGartService;
import gob.osinergmin.fise.util.FechaUtil;

import java.util.List;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service(value="fiseTipDocRefGartServiceImpl")
public class FiseTipDocRefGartServiceImpl implements FiseTipDocRefGartService {
	
	Logger logger=Logger.getLogger(FiseTipDocRefGartServiceImpl.class);
	
	@Autowired
	@Qualifier("fiseTipDocRefDaoImpl")
	private FiseTipDocRefDao fiseTipDocRefDao;
	
	@Override
	@Transactional
	public List<FiseTipDocRef> listarFiseTipDocRef() {
		return fiseTipDocRefDao.listarFiseTipDocRef();
	}
	
	@Override
	@Transactional
	public FiseTipDocRef obtenerFiseTipDocRefByPK(String id) {
		return fiseTipDocRefDao.obtenerFiseTipDocRefByPK(id);
	}
	
	
	@Override
	@Transactional
	public String insertarDatosFiseTipDocRef(TipDocReferenciaBean bean) throws Exception{
		FiseTipDocRef doc =null;
		String valor="1";
		try {
			doc = fiseTipDocRefDao.obtenerFiseTipDocRefByPK(bean.getId());	
			if(doc==null){
				doc = new FiseTipDocRef();
				doc.setIdTipDocRef(bean.getId()); 
				doc.setDescripcion(bean.getDescripcion());					
				//auditoria
				doc.setUsuarioCreacion(bean.getUsuario());
				doc.setTerminalCreacion(bean.getTerminal()); 
				doc.setFechaCreacion(FechaUtil.obtenerFechaActual()); 
				fiseTipDocRefDao.insertarFiseTipDocRef(doc); 			
			}else{
				valor = "2";		
			}			
		} catch (Exception e) {
			logger.info("Error al grabar en fise documento referencia: "+e); 
			valor = "0";
		}finally{
			if(doc !=null){
				doc =null;
			}
		}
		return valor;
		
	}
	
	@Override
	@Transactional
	public String actualizarDatosFiseTipDocRef(TipDocReferenciaBean bean) throws Exception{
		FiseTipDocRef doc =null;
		String valor ="1";
		try {			
			doc = fiseTipDocRefDao.obtenerFiseTipDocRefByPK(bean.getId());			
			doc.setDescripcion(bean.getDescripcion());			
			//auditoria
			doc.setUsuarioActualizacion(bean.getUsuario());
			doc.setTerminalActualizacion(bean.getTerminal()); 
			doc.setFechaActualizacion(FechaUtil.obtenerFechaActual()); 
			fiseTipDocRefDao.actualizarFiseTipDocRef(doc); 
		} catch (Exception e) {
			logger.info("Error al actualizar en fise documento de referencia: "+e); 
			valor = "0";
		}finally{
			if(doc !=null){
				doc =null;
			}
		}
		return valor;
		
	}
	
	@Override
	@Transactional
	public String eliminarDatosFiseObservacion(String id) throws Exception{
		FiseTipDocRef doc =null;
		String valor ="1";
		try {			
			doc = fiseTipDocRefDao.obtenerFiseTipDocRefByPK(id);			
			fiseTipDocRefDao.eliminarFiseTipDocRef(doc); 			
		} catch (Exception e) {
			logger.info("Error al eliminar  fise documento de referencia: "+e); 
			valor = "0";
		}finally{
			if(doc !=null){
				doc =null;
			}
		}
		return valor;
	}	
	
	
	@Override
	@Transactional
	public TipDocReferenciaBean buscarFiseTipDocRefEditar(String id) throws Exception{
		FiseTipDocRef d =null;
		TipDocReferenciaBean bean =new TipDocReferenciaBean();
		try {
			d = fiseTipDocRefDao.obtenerFiseTipDocRefByPK(id);
			bean.setId(d.getIdTipDocRef());
			bean.setDescripcion(d.getDescripcion()); 					
		} catch (Exception e) {
			logger.info("Error al buscar datos de fise documento de referencia para editar:  "+e); 
		}finally{
			if(d!=null)
				d=null;
		}
		return bean;
	}
	
	@Override
	@Transactional
	public List<FiseTipDocRef> buscarFiseTipDocRef(String id, String descripcion)
			throws Exception{
		return fiseTipDocRefDao.buscarFiseTipDocRef(id, descripcion);
	}
	
	

}
