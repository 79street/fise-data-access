package gob.osinergmin.fise.gart.dao;

import gob.osinergmin.base.dao.GenericDao;
import gob.osinergmin.fise.domain.AdmEmpresa;

import java.util.List;

public interface AdmEmpresaGartDao extends GenericDao {

	List<AdmEmpresa> listarAdmEmpresa();
	List<AdmEmpresa> getEmpresaFise(String codProceso, String codFuncion,String cadenaEmpresas);
	
}
