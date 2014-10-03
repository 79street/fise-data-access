package gob.osinergmin.fise.gart.service;

import gob.osinergmin.fise.domain.FiseZonaBenef;

import java.util.List;

public interface FiseZonaBenefGartService {
	
	List<FiseZonaBenef> listarFiseZonaBenef();
	FiseZonaBenef obtenerFiseZonaBenefByPK(long id);

}
