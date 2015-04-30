package gob.osinergmin.fise.gart.service;

import gob.osinergmin.fise.bean.TipDocReferenciaBean;
import gob.osinergmin.fise.domain.FiseTipDocRef;

import java.util.List;

public interface FiseTipDocRefGartService {
	
	List<FiseTipDocRef> listarFiseTipDocRef();
	
	FiseTipDocRef obtenerFiseTipDocRefByPK(String id);
	
	String insertarDatosFiseTipDocRef(TipDocReferenciaBean bean) throws Exception;
	
	String actualizarDatosFiseTipDocRef(TipDocReferenciaBean bean) throws Exception;
	
	String eliminarDatosFiseObservacion(String id) throws Exception;
	
	TipDocReferenciaBean buscarFiseTipDocRefEditar(String id) throws Exception;
	
	List<FiseTipDocRef> buscarFiseTipDocRef(String id, String descripcion)
			throws Exception;

}
