package gob.osinergmin.fise.gart.service;

import gob.osinergmin.fise.bean.TipoGastoBean;
import gob.osinergmin.fise.domain.FiseTipGasto;

import java.util.List;

public interface FiseTipGastoGartService {
	
	List<FiseTipGasto> listarFiseTipGasto();
	
	FiseTipGasto obtenerFiseTipGastoByPK(String id);
	
	String insertarDatosFiseTipGasto(TipoGastoBean bean) throws Exception;
	
	String actualizarDatosFiseTipGasto(TipoGastoBean bean) throws Exception;
	
	String eliminarDatosFiseTipGasto(String id) throws Exception;
	
	TipoGastoBean buscarFiseTipGastoEditar(String id) throws Exception;
	
	List<FiseTipGasto> buscarFiseTipGasto(String id, String descripcion)
			throws Exception;

}
