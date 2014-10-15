package gob.osinergmin.fise.gart.service.impl;

import gob.osinergmin.fise.dao.AdmEmpresaDao;
import gob.osinergmin.fise.domain.AdmEmpresa;
import gob.osinergmin.fise.gart.service.AdmEmpresaGartService;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

@Service(value="admEmpresaGartServiceImpl")
public class AdmEmpresaGartServiceImpl implements AdmEmpresaGartService {
	
	@Autowired
	@Qualifier("admEmpresaDaoImpl")
	private AdmEmpresaDao admEmpresaDao;
	
	@Override
	public List<AdmEmpresa> listarAdmEmpresa() {
		return admEmpresaDao.listarAdmEmpresa();
	}
	
	public List<AdmEmpresa> getEmpresaFise(String codProceso, String codFuncion,String cadenaEmpresas){
		return admEmpresaDao.getEmpresaFise( codProceso,  codFuncion, cadenaEmpresas);
	}

}
