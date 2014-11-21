package gob.osinergmin.fise.gart.service;

import gob.osinergmin.fise.bean.FiseObservacionBean;
import gob.osinergmin.fise.domain.FiseObservacion;

import java.util.List;

public interface FiseObservacionGartService {

	List<FiseObservacion> listarFiseObservacion();
	
	String insertarDatosFiseObservacion(FiseObservacionBean bean) throws Exception;
	
	String actualizarDatosFiseObservacion(FiseObservacionBean bean) throws Exception;
	
	String eliminarDatosFiseObservacion(String id) throws Exception;
	
	FiseObservacionBean buscarFiseObsEditar(String id) throws Exception;
	
	FiseObservacion obtenerFiseObservacion(String id) throws Exception;
	
	List<FiseObservacion> buscarFiseObservacion(String id, String descripcion)
			throws Exception;
	
}
