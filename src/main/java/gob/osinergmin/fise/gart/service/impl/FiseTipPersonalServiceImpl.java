package gob.osinergmin.fise.gart.service.impl;

import gob.osinergmin.fise.dao.FiseTipPersonalDao;
import gob.osinergmin.fise.domain.FiseTipPersonal;
import gob.osinergmin.fise.gart.service.FiseTipPersonalService;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service(value="fiseTipPersonalServiceImpl")
public class FiseTipPersonalServiceImpl implements FiseTipPersonalService {

	@Autowired
	@Qualifier("fiseTipPersonalDaoImpl")
	private FiseTipPersonalDao fiseTipPersonalDao;
	
	@Override
	@Transactional
	public List<FiseTipPersonal> listarTipoPersonal() throws Exception{
		return fiseTipPersonalDao.listarFiseTipPersonal();
	}
	
}
