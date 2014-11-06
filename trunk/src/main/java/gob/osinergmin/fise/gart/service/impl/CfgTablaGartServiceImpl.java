package gob.osinergmin.fise.gart.service.impl;

import gob.osinergmin.fise.dao.CfgTablaDao;
import gob.osinergmin.fise.domain.CfgTabla;
import gob.osinergmin.fise.gart.service.CfgTablaGartService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service(value="cfgTablaGartServiceImpl")
public class CfgTablaGartServiceImpl implements CfgTablaGartService {
	
	@Autowired
	@Qualifier("cfgTablaDaoImpl")
	private CfgTablaDao cfgTablaDao;
	
	@Override
	@Transactional
	public CfgTabla obtenerCfgTablaByPK(long idTabla) {
		return cfgTablaDao.obtenerCfgTablaByPK(idTabla);
	}

}
