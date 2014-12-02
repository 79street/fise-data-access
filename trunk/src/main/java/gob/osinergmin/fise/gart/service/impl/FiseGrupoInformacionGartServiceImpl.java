package gob.osinergmin.fise.gart.service.impl;

import java.util.List;

import gob.osinergmin.fise.dao.FiseGrupoInformacionDao;
import gob.osinergmin.fise.domain.FiseGrupoInformacion;
import gob.osinergmin.fise.gart.service.FiseGrupoInformacionGartService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service(value="fiseGrupoInformacionGartServiceImpl")
public class FiseGrupoInformacionGartServiceImpl implements
		FiseGrupoInformacionGartService {
	
	
	@Autowired
	@Qualifier("fiseGrupoInformacionDaoImpl")
	private FiseGrupoInformacionDao fiseGrupoInformacionDao;
	
	@Transactional
	@Override
	public List<FiseGrupoInformacion> listarGrupoInformacion(String tipo) throws Exception{
		return fiseGrupoInformacionDao.listarGrupoInformacion(tipo);
	}

}
