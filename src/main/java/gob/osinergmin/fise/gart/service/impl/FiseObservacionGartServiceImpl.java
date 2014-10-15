package gob.osinergmin.fise.gart.service.impl;

import gob.osinergmin.fise.dao.FiseObservacionDao;
import gob.osinergmin.fise.domain.FiseObservacion;
import gob.osinergmin.fise.gart.service.FiseObservacionGartService;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

@Service(value="fiseObservacionGartServiceImpl")
public class FiseObservacionGartServiceImpl implements FiseObservacionGartService {
	
	@Autowired
	@Qualifier("fiseObservacionDaoImpl")
	private FiseObservacionDao fiseObservacionDao;
	
	@Override
	public List<FiseObservacion> listarFiseObservacion() {
		return fiseObservacionDao.listarFiseObservacion();
	}

}
