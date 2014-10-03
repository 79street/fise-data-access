package gob.osinergmin.fise.gart.service.impl;

import gob.osinergmin.fise.domain.AdmEmpresa;
import gob.osinergmin.fise.gart.dao.AdmEmpresaGartDao;
import gob.osinergmin.fise.gart.service.AdmEmpresaGartService;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

@Service(value="admEmpresaGartServiceImpl")
public class AdmEmpresaGartServiceImpl implements AdmEmpresaGartService {
	
	@Autowired
	@Qualifier("admEmpresaGartDaoImpl")
	private AdmEmpresaGartDao admEmpresaGartDao;
	
	@Override
	public List<AdmEmpresa> listarAdmEmpresa() {
		return admEmpresaGartDao.listarAdmEmpresa();
	}

}
