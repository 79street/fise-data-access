package gob.osinergmin.fise.gart.service;

import gob.osinergmin.fise.domain.FiseTipDocRef;

import java.util.List;

public interface FiseTipDocRefGartService {
	
	List<FiseTipDocRef> listarFiseTipDocRef();
	FiseTipDocRef obtenerFiseTipDocRefByPK(String id);

}
