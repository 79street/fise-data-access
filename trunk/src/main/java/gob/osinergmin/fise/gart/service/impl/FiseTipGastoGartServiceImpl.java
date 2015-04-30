package gob.osinergmin.fise.gart.service.impl;

import gob.osinergmin.fise.bean.TipoGastoBean;
import gob.osinergmin.fise.dao.FiseTipGastoDao;
import gob.osinergmin.fise.domain.FiseTipGasto;
import gob.osinergmin.fise.gart.service.FiseTipGastoGartService;
import gob.osinergmin.fise.util.FechaUtil;

import java.util.List;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service(value="fiseTipGastoGartServiceImpl")
public class FiseTipGastoGartServiceImpl implements FiseTipGastoGartService {
	
	Logger logger=Logger.getLogger(FiseTipGastoGartServiceImpl.class);
	
	
	@Autowired
	@Qualifier("fiseTipGastoDaoImpl")
	private FiseTipGastoDao fiseTipGastoDao;
	
	/**Implementacion de metodos*/	
	
	@Override
	@Transactional
	public List<FiseTipGasto> listarFiseTipGasto() {
		return fiseTipGastoDao.listarFiseTipGasto();
	}
	
	@Override
	@Transactional
	public FiseTipGasto obtenerFiseTipGastoByPK(String id) {
		return fiseTipGastoDao.obtenerFiseTipGastoByPK(id);
	}
	
	
	@Override
	@Transactional
	public String insertarDatosFiseTipGasto(TipoGastoBean bean) throws Exception{
		FiseTipGasto gasto =null;
		String valor="1";
		try {
			gasto = fiseTipGastoDao.obtenerFiseTipGastoByPK(bean.getId());	
			if(gasto==null){
				gasto = new FiseTipGasto();
				gasto.setIdTipGasto(bean.getId()); 
				gasto.setDescripcion(bean.getDescripcion());					
				//auditoria
				gasto.setUsuarioCreacion(bean.getUsuario());
				gasto.setTerminalCreacion(bean.getTerminal()); 
				gasto.setFechaCreacion(FechaUtil.obtenerFechaActual()); 
				fiseTipGastoDao.insertarFiseTipGasto(gasto); 			
			}else{
				valor = "2";		
			}			
		} catch (Exception e) {
			logger.info("Error al grabar en tipo de gasto: "+e); 
			valor = "0";
		}finally{
			if(gasto !=null){
				gasto =null;
			}
		}
		return valor;
		
	}
	
	@Override
	@Transactional
	public String actualizarDatosFiseTipGasto(TipoGastoBean bean) throws Exception{
		FiseTipGasto gasto =null;
		String valor ="1";
		try {			
			gasto = fiseTipGastoDao.obtenerFiseTipGastoByPK(bean.getId());			
			gasto.setDescripcion(bean.getDescripcion());			
			//auditoria
			gasto.setUsuarioActualizacion(bean.getUsuario());
			gasto.setTerminalActualizacion(bean.getTerminal()); 
			gasto.setFechaActualizacion(FechaUtil.obtenerFechaActual()); 
			fiseTipGastoDao.actualizarFiseTipGasto(gasto); 
		} catch (Exception e) {
			logger.info("Error al actualizar en tipo de gasto: "+e); 
			valor = "0";
		}finally{
			if(gasto !=null){
				gasto =null;
			}
		}
		return valor;
		
	}
	
	@Override
	@Transactional
	public String eliminarDatosFiseTipGasto(String id) throws Exception{
		FiseTipGasto gasto =null;
		String valor ="1";
		try {			
			gasto = fiseTipGastoDao.obtenerFiseTipGastoByPK(id);			
			fiseTipGastoDao.eliminarFiseTipGasto(gasto); 			
		} catch (Exception e) {
			logger.info("Error al eliminar  tipo de gasto: "+e); 
			valor = "0";
		}finally{
			if(gasto !=null){
				gasto =null;
			}
		}
		return valor;
	}	

	
	@Override
	@Transactional
	public TipoGastoBean buscarFiseTipGastoEditar(String id) throws Exception{
		FiseTipGasto g =null;
		TipoGastoBean bean =new TipoGastoBean();
		try {
			g = fiseTipGastoDao.obtenerFiseTipGastoByPK(id);
			bean.setId(g.getIdTipGasto());
			bean.setDescripcion(g.getDescripcion()); 					
		} catch (Exception e) {
			logger.info("Error al buscar datos de tipo de gasto para editar:  "+e); 
		}finally{
			if(g!=null)
				g=null;
		}
		return bean;
	}
	
	@Override
	@Transactional
	public List<FiseTipGasto> buscarFiseTipGasto(String id, String descripcion)
			throws Exception{
		return fiseTipGastoDao.buscarFiseTipGasto(id, descripcion);
	}

}
