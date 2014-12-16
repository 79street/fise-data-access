package gob.osinergmin.fise.gart.service;

import gob.osinergmin.fise.domain.AdmUbigeo;

import java.util.List;

public interface AdmUbigeoGartService {
	
	List<AdmUbigeo> listarAdmUbigeo();
	
	List<AdmUbigeo> listarDepartamentos();
	List<AdmUbigeo> listarProvincias(String codDepartamento);
	List<AdmUbigeo> listarDistritos(String codProvincia);
}
