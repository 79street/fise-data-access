package gob.osinergmin.fise.gart.service;

import gob.osinergmin.fise.bean.GrupoInformacionBean;
import gob.osinergmin.fise.domain.FiseGrupoInformacion;

import java.util.List;

public interface FiseGrupoInformacionGartService {
	
	List<FiseGrupoInformacion> listarGrupoInformacion(String tipo,String flag) throws Exception;
	
	String insertarDatosGrupoInf(GrupoInformacionBean bean) throws Exception;
	
	String actualizarDatosGrupoInf(GrupoInformacionBean bean) throws Exception;
	
	String eliminarDatosGrupoInf(Long id,String user,String terminal) throws Exception;
	
	FiseGrupoInformacion obtenerGrupoInf(Long id) throws Exception;
	
	GrupoInformacionBean buscarGrupoInfEditar(Long id) throws Exception;
	
	List<FiseGrupoInformacion> buscarGrupoInformacion(String descripcion,String tipo,Integer estado)
			throws Exception;

}
