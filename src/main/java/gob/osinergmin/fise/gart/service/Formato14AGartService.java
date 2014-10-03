package gob.osinergmin.fise.gart.service;

import gob.osinergmin.fise.domain.FiseFormato14AD;

public interface Formato14AGartService {
	
	FiseFormato14AD obtenerFormato14ADVigente(String codEmpresa, long anioVigencia, long idZonaBenef);

}
