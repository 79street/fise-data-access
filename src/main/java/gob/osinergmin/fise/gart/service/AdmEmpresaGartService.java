package gob.osinergmin.fise.gart.service;

import gob.osinergmin.fise.domain.AdmEmpresa;

import java.util.List;

public interface AdmEmpresaGartService {
	
	List<AdmEmpresa> listarAdmEmpresa();
	List<AdmEmpresa> getEmpresaFise(String codProceso, String codFuncion,String cadenaEmpresas);
	
}
