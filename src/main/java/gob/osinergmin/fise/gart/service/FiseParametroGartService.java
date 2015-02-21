package gob.osinergmin.fise.gart.service;

import gob.osinergmin.fise.bean.FiseParametroBean;
import gob.osinergmin.fise.domain.FiseParametro;

import java.util.List;

public interface FiseParametroGartService {

	List<FiseParametro> listarFiseParametro();
	
	String insertarDatosFiseParametro(FiseParametroBean bean) throws Exception;
	
	String actualizarDatosFiseParametro(FiseParametroBean bean) throws Exception;
	
	String eliminarDatosFiseParametro(String codigo) throws Exception;
	
	FiseParametroBean buscarFiseObsEditar(String codigo) throws Exception;
	
	FiseParametro obtenerFiseParametro(String codigo) throws Exception;
	
	List<FiseParametro> buscarFiseParametro(String codigo, String nombre) throws Exception;
	
}
