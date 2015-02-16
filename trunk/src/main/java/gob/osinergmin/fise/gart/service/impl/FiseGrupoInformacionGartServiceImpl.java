package gob.osinergmin.fise.gart.service.impl;

import gob.osinergmin.fise.bean.GrupoInformacionBean;
import gob.osinergmin.fise.constant.FiseConstants;
import gob.osinergmin.fise.dao.FiseGrupoInformacionDao;
import gob.osinergmin.fise.domain.FiseGrupoInformacion;
import gob.osinergmin.fise.gart.service.FiseGrupoInformacionGartService;
import gob.osinergmin.fise.util.FechaUtil;

import java.util.List;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service(value="fiseGrupoInformacionGartServiceImpl")
public class FiseGrupoInformacionGartServiceImpl implements
		FiseGrupoInformacionGartService {
	
	Logger logger=Logger.getLogger(FiseGrupoInformacionGartServiceImpl.class);
	
	
	@Autowired
	@Qualifier("fiseGrupoInformacionDaoImpl")
	private FiseGrupoInformacionDao fiseGrupoInformacionDao;
	
	
	/**Implementacion de metodos**/
	
	
	@Transactional
	@Override
	public List<FiseGrupoInformacion> listarGrupoInformacion(String tipo,String flag) throws Exception{
		return fiseGrupoInformacionDao.listarGrupoInformacion(tipo,flag);
	}
	
	
	@Override
	@Transactional
	public String insertarDatosGrupoInf(GrupoInformacionBean bean) throws Exception{
		FiseGrupoInformacion grupo =null;
		String valor;
		boolean verificar = true;
		try {			
			grupo = new FiseGrupoInformacion();	
			if(FiseConstants.BIENAL.equals(bean.getTipo()) && 
					FiseConstants.ESTADO_ACTIVO_GRUPO_INF==Integer.valueOf(bean.getEstado())){ 
				verificar = fiseGrupoInformacionDao.verificarGrupoInfBienal(bean.getTipo(),
						FiseConstants.ESTADO_ACTIVO_GRUPO_INF);		 		
			}
			if(verificar){
				grupo.setDescripcion(bean.getDescripcion());
				grupo.setTipo(bean.getTipo());
				grupo.setEstado(Integer.valueOf(bean.getEstado()));  
				grupo.setAnoPresentacion(Long.valueOf(bean.getAnioPres()));
				grupo.setMesPresentacion(Long.valueOf(bean.getMesPres()));
				//auditoria
				grupo.setUsuarioCreacion(bean.getUsuario());
				grupo.setTerminalCreacion(bean.getTerminal()); 
				grupo.setFechaCreacion(FechaUtil.obtenerFechaActual()); 
				fiseGrupoInformacionDao.insertarGrupoInformacion(grupo);
				logger.info("Id de Grupo inf. al grabar:  "+grupo.getIdGrupoInformacion()); 
				valor = ""+grupo.getIdGrupoInformacion();
			}else{
				valor = "-1";//grupo de informacion bienal ya existe con estado activado
			}
		} catch (Exception e) {
			logger.info("Error al grabar en grupo informacion: "+e); 
			valor = "0";
		}finally{
			if(grupo !=null){
				grupo =null;
			}
		}
		return valor;		
	}
	
	@Override
	@Transactional
	public String actualizarDatosGrupoInf(GrupoInformacionBean bean) throws Exception{
		FiseGrupoInformacion grupo =null;
		String valor ="0";
		boolean verificar = true;
		try {
			grupo = fiseGrupoInformacionDao.obtenerFiseGrupoInformacionByPK(Long.valueOf(bean.getIdGrupoInf())); 			
			if(FiseConstants.BIENAL.equals(bean.getTipo()) && 
					FiseConstants.ESTADO_ACTIVO_GRUPO_INF==Integer.valueOf(bean.getEstado()) &&
					FiseConstants.ESTADO_INACTIVO_GRUPO_INF == grupo.getEstado()){ 
				verificar = fiseGrupoInformacionDao.verificarGrupoInfBienal(bean.getTipo(),
						FiseConstants.ESTADO_ACTIVO_GRUPO_INF);		 		
			}
			if(verificar && grupo!=null){						
				grupo.setDescripcion(bean.getDescripcion());
				grupo.setTipo(bean.getTipo());
				grupo.setEstado(Integer.valueOf(bean.getEstado()));
				grupo.setAnoPresentacion(Long.valueOf(bean.getAnioPres()));
				grupo.setMesPresentacion(Long.valueOf(bean.getMesPres()));
				//auditoria
				grupo.setUsuarioActualizacion(bean.getUsuario());
				grupo.setTerminalActualizacion(bean.getTerminal()); 
				grupo.setFechaActualizacion(FechaUtil.obtenerFechaActual()); 
				fiseGrupoInformacionDao.actualizarGrupoInformacion(grupo);
				valor = "1";
			}else if(!verificar){
				valor = "-1";//grupo de informacion bienal ya existe con estado activado
			}else{
				valor = "0";
			}
		} catch (Exception e) {
			logger.info("Error al actualizar en grupo informacion: "+e); 
			valor = "0";
		}finally{
			if(grupo !=null){
				grupo =null;
			}
		}
		return valor;
		
	}
	
	@Override
	@Transactional
	public String eliminarDatosGrupoInf(Long id,String user,String terminal) throws Exception{
		FiseGrupoInformacion grupo =null;
		String valor ="1";
		try {			
			grupo = fiseGrupoInformacionDao.obtenerFiseGrupoInformacionByPK(id); 			
			grupo.setEstado(0); 
			grupo.setUsuarioActualizacion(user);
			grupo.setTerminalActualizacion(terminal); 
			grupo.setFechaActualizacion(FechaUtil.obtenerFechaActual());
			fiseGrupoInformacionDao.actualizarGrupoInformacion(grupo); 			
		} catch (Exception e) {
			logger.info("Error al eliminar  grupo informacion: "+e); 
			valor = "0";
		}finally{
			if(grupo !=null){
				grupo =null;
			}
		}
		return valor;
	}
	
	@Override
	@Transactional
	public FiseGrupoInformacion obtenerGrupoInf(Long id) throws Exception{
		return fiseGrupoInformacionDao.obtenerFiseGrupoInformacionByPK(id);
		
	}
	
	@Override
	@Transactional
	public GrupoInformacionBean buscarGrupoInfEditar(Long id) throws Exception{
		FiseGrupoInformacion g =null;
		GrupoInformacionBean bean =new GrupoInformacionBean();
		try {
			g = fiseGrupoInformacionDao.obtenerFiseGrupoInformacionByPK(id); 	
			bean.setIdGrupoInf(""+g.getIdGrupoInformacion()); 
			bean.setDescripcion(g.getDescripcion()); 
			bean.setEstado(""+g.getEstado()); 
			bean.setAnioPres(""+g.getAnoPresentacion());
			bean.setMesPres(""+g.getMesPresentacion());
			bean.setTipo(g.getTipo()); 
		} catch (Exception e) {
			logger.info("Error al buscar datos para editar:  "+e); 
		}finally{
			if(g!=null)
				g=null;
		}
		return bean;
	}
	
	@Override
	@Transactional
	public List<FiseGrupoInformacion> buscarGrupoInformacion(String descripcion,String tipo,Integer estado)
			throws Exception{
		return fiseGrupoInformacionDao.buscarGrupoInformacion(descripcion, tipo, estado);
	}
	

}
