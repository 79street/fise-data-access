package gob.osinergmin.fise.gart.service.impl;

import gob.osinergmin.fise.domain.FiseFormato12AC;
import gob.osinergmin.fise.gart.dao.Formato12AGartDao;
import gob.osinergmin.fise.gart.service.Formato12AGartService;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

@Service(value="Formato12AGartServiceImpl")
public class Formato12AGartServiceImpl implements Formato12AGartService {

	@Autowired
	@Qualifier("formato12AGartDaoImpl")
	private Formato12AGartDao formato12AGartDao;
	
	@Override
	public List<FiseFormato12AC> listarFormato12AC() {
		return formato12AGartDao.listarFormato12AC();
	}

}
