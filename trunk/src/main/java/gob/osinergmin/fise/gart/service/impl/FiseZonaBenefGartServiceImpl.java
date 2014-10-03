package gob.osinergmin.fise.gart.service.impl;

import gob.osinergmin.fise.domain.FiseZonaBenef;
import gob.osinergmin.fise.gart.dao.FiseZonaBenefGartDao;
import gob.osinergmin.fise.gart.service.FiseZonaBenefGartService;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

@Service(value="fiseZonaBenefGartServiceImpl")
public class FiseZonaBenefGartServiceImpl implements FiseZonaBenefGartService {
	
	@Autowired
	@Qualifier("fiseZonaBenefGartDaoImpl")
	private FiseZonaBenefGartDao fiseZonaBenefGartDao;
	
	@Override
	public List<FiseZonaBenef> listarFiseZonaBenef() {
		return fiseZonaBenefGartDao.listarFiseZonaBenef();
	}
	
	@Override
	public FiseZonaBenef obtenerFiseZonaBenefByPK(long id) {
		return fiseZonaBenefGartDao.obtenerFiseZonaBenefByPK(id);
	}

}
