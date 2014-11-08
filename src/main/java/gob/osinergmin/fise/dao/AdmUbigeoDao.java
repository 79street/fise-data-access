package gob.osinergmin.fise.dao;

import gob.osinergmin.base.dao.GenericDao;
import gob.osinergmin.fise.domain.AdmUbigeo;

import java.util.List;

public interface AdmUbigeoDao extends GenericDao {

	List<AdmUbigeo> listarDepartamentos();
	List<AdmUbigeo> listarProvincias(String codDepartamento);
	List<AdmUbigeo> listarDistritos(String codProvincia);
	
}
