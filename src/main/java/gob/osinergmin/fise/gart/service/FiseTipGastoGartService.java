package gob.osinergmin.fise.gart.service;

import gob.osinergmin.fise.domain.FiseTipGasto;

import java.util.List;

public interface FiseTipGastoGartService {
	
	List<FiseTipGasto> listarFiseTipGasto();
	FiseTipGasto obtenerFiseTipGastoByPK(String id);

}
