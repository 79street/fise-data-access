package gob.osinergmin.fise.gart.service.impl;

import gob.osinergmin.fise.dao.Formato14BCDao;
import gob.osinergmin.fise.dao.Formato14BDDao;
import gob.osinergmin.fise.domain.FiseFormato14BC;
import gob.osinergmin.fise.domain.FiseFormato14BD;
import gob.osinergmin.fise.gart.service.Formato14BGartService;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

@Service(value="formato14BGartServiceImpl")
public class Formato14BGartServiceImpl implements Formato14BGartService {
	
	@Autowired
	@Qualifier("formato14BCDaoImpl")
	private Formato14BCDao formato14BCDao;
	
	@Autowired
	@Qualifier("formato14BDDaoImpl")
	private Formato14BDDao formato14BDDao;
	
	@Override
	public List<FiseFormato14BC> listarFormato14BC() {
		return formato14BCDao.listarFormato14BC();
	}
	
	@Override
	public List<FiseFormato14BD> listarFormato14BD() {
		return formato14BDDao.listarFormato14BD();
	}


}
