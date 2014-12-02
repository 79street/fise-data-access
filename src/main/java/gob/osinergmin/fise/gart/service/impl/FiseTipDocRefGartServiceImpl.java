package gob.osinergmin.fise.gart.service.impl;

import gob.osinergmin.fise.dao.FiseTipDocRefDao;
import gob.osinergmin.fise.domain.FiseTipDocRef;
import gob.osinergmin.fise.gart.service.FiseTipDocRefGartService;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service(value="fiseTipDocRefGartServiceImpl")
public class FiseTipDocRefGartServiceImpl implements FiseTipDocRefGartService {
	
	@Autowired
	@Qualifier("fiseTipDocRefDaoImpl")
	private FiseTipDocRefDao fiseTipDocRefDao;
	
	@Override
	@Transactional
	public List<FiseTipDocRef> listarFiseTipDocRef() {
		return fiseTipDocRefDao.listarFiseTipDocRef();
	}
	
	@Override
	@Transactional
	public FiseTipDocRef obtenerFiseTipDocRefByPK(String id) {
		return fiseTipDocRefDao.obtenerFiseTipDocRefByPK(id);
	}

}
