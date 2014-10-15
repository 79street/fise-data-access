package gob.osinergmin.fise.gart.service.impl;

import gob.osinergmin.fise.dao.FiseZonaBenefDao;
import gob.osinergmin.fise.domain.FiseZonaBenef;
import gob.osinergmin.fise.gart.service.FiseZonaBenefGartService;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

@Service(value="fiseZonaBenefGartServiceImpl")
public class FiseZonaBenefGartServiceImpl implements FiseZonaBenefGartService {
	
	@Autowired
	@Qualifier("fiseZonaBenefDaoImpl")
	private FiseZonaBenefDao fiseZonaBenefDao;
	
	@Override
	public List<FiseZonaBenef> listarFiseZonaBenef() {
		return fiseZonaBenefDao.listarFiseZonaBenef();
	}
	
	@Override
	public FiseZonaBenef obtenerFiseZonaBenefByPK(long id) {
		return fiseZonaBenefDao.obtenerFiseZonaBenefByPK(id);
	}

}
