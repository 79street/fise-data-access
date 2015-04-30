package gob.osinergmin.fise.gart.service;

import gob.osinergmin.fise.bean.TipoPersonalBean;
import gob.osinergmin.fise.domain.FiseTipPersonal;

import java.util.List;

public interface FiseTipPersonalService {
	
	 List<FiseTipPersonal> listarTipoPersonal() throws Exception;
	 
	 String insertarDatosFiseTipPersonal(TipoPersonalBean bean) throws Exception;
	 
	 String actualizarDatosFiseTipPersonal(TipoPersonalBean bean) throws Exception;
	 
	 String eliminarDatosFiseTipPersonal(String id) throws Exception;
	 
	 TipoPersonalBean buscarFiseTipPersonalEditar(String id) throws Exception;
	 
	 List<FiseTipPersonal> buscarFiseTipPersonal(String id, String descripcion)
				throws Exception;
	 
	 String obtenerIdTipPersonal() throws Exception;

}
