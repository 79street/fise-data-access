package gob.osinergmin.fise.dao;

import gob.osinergmin.base.dao.GenericDao;
import gob.osinergmin.fise.domain.AdmEmpresa;

import java.util.List;

public interface AdmEmpresaDao extends GenericDao {

	List<AdmEmpresa> listarAdmEmpresa();
	List<AdmEmpresa> getEmpresaFise(String codProceso, String codFuncion,String cadenaEmpresas);
	
}
