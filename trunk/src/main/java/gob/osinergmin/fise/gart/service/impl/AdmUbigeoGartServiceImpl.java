package gob.osinergmin.fise.gart.service.impl;

import gob.osinergmin.fise.dao.AdmUbigeoDao;
import gob.osinergmin.fise.domain.AdmUbigeo;
import gob.osinergmin.fise.gart.service.AdmUbigeoGartService;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

@Service(value="admUbigeoGartServiceImpl")
public class AdmUbigeoGartServiceImpl implements AdmUbigeoGartService{
	
	@Autowired
	@Qualifier("admUbigeoDaoImpl")
	private AdmUbigeoDao admUbigeoDao;

	public List<AdmUbigeo> listarDepartamentos() {
		return admUbigeoDao.listarDepartamentos();
	}


	public List<AdmUbigeo> listarProvincias(String codDepartamento) {
		return admUbigeoDao.listarProvincias(codDepartamento);
	}


	public List<AdmUbigeo> listarDistritos(String codProvincia) {
		return admUbigeoDao.listarDistritos(codProvincia);
	}
	
}
