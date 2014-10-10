package gob.osinergmin.fise.gart.service.impl;

import gob.osinergmin.fise.domain.FiseFormato14AD;
import gob.osinergmin.fise.gart.dao.Formato14ADGartDao;
import gob.osinergmin.fise.gart.service.Formato14AGartService;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

@Service(value="formato14AGartServiceImpl")
public class Formato14AGartServiceImpl implements Formato14AGartService {
	
	Logger logger=LoggerFactory.getLogger(Formato14AGartServiceImpl.class);
	
	@Autowired
	@Qualifier("formato14ADGartDaoImpl")
	private Formato14ADGartDao formato14ADDao;
	
	@Override
	public FiseFormato14AD obtenerFormato14ADVigente(String codEmpresa, long anioVigencia, long idZonaBenef){
		
		return formato14ADDao.obtenerFormato14ADVigente(codEmpresa, anioVigencia, idZonaBenef);

	}

}
