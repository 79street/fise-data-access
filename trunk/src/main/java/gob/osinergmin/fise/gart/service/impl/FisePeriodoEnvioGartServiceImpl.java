package gob.osinergmin.fise.gart.service.impl;

import gob.osinergmin.fise.dao.FisePeriodoEnvioDao;
import gob.osinergmin.fise.domain.FisePeriodoEnvio;
import gob.osinergmin.fise.gart.service.FisePeriodoEnvioGartService;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service(value="fisePeriodoEnvioGartServiceImpl")
public class FisePeriodoEnvioGartServiceImpl implements FisePeriodoEnvioGartService {

	@Autowired
	@Qualifier("fisePeriodoEnvioDaoImpl")
	private FisePeriodoEnvioDao fisePeriodoEnvioDao;
	
	@Transactional
	public List<FisePeriodoEnvio> listarFisePeriodoEnvioMesAnioEtapa(String codEmpresa, String nombreFormato){
		return fisePeriodoEnvioDao.listarFisePeriodoEnvioMesAnioEtapa(codEmpresa, nombreFormato);
	}
	
}
