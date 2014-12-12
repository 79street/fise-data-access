package gob.osinergmin.fise.gart.service.impl;

import gob.osinergmin.fise.dao.FiseTipGastoDao;
import gob.osinergmin.fise.domain.FiseTipGasto;
import gob.osinergmin.fise.gart.service.FiseTipGastoGartService;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service(value="fiseTipGastoGartServiceImpl")
public class FiseTipGastoGartServiceImpl implements FiseTipGastoGartService {
	
	@Autowired
	@Qualifier("fiseTipGastoDaoImpl")
	private FiseTipGastoDao fiseTipGastoDao;
	
	@Override
	@Transactional
	public List<FiseTipGasto> listarFiseTipGasto() {
		return fiseTipGastoDao.listarFiseTipGasto();
	}
	
	@Override
	@Transactional
	public FiseTipGasto obtenerFiseTipGastoByPK(String id) {
		return fiseTipGastoDao.obtenerFiseTipGastoByPK(id);
	}

}
