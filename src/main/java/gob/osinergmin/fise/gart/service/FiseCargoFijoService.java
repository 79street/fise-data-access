package gob.osinergmin.fise.gart.service;

import java.util.HashMap;
import java.util.List;

import gob.osinergmin.fise.bean.FiseCargoFijoBean;
import gob.osinergmin.fise.domain.FiseMcargofijo;

public interface FiseCargoFijoService {
	
	String insertarDatosFiseCargoFijo(FiseCargoFijoBean bean) throws Exception;
	
	String actualizarDatosFiseCargoFijo(FiseCargoFijoBean bean) throws Exception;
	
	String eliminarDatosFiseCargoFijo(String codEmpresa,
			String anio,String mes,String usuario,String terminal) throws Exception;
	
	FiseMcargofijo obtenerFiseCargofijo(String codEmpresa,
			String anio,String mes) throws Exception;
	
	FiseCargoFijoBean buscarFiseCargoFijoEditar(String codEmpresa,
			String anio,String mes) throws Exception;
	
	List<FiseCargoFijoBean> buscarFiseCargoFijo(String codEmpresa, Long anioRep,Long mesRep)
			throws Exception;
	
	HashMap<String, Object> mapParametrosDatosProyectoFise(FiseCargoFijoBean bean) 
			throws Exception;

}
