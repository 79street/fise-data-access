package gob.osinergmin.fise.gart.service.impl;

import gob.osinergmin.fise.dao.CommonDao;
import gob.osinergmin.fise.dao.FiseGrupoInformacionDao;
import gob.osinergmin.fise.dao.FiseZonaBenefDao;
import gob.osinergmin.fise.dao.Formato12CCDao;
import gob.osinergmin.fise.dao.Formato12CDDao;
import gob.osinergmin.fise.dao.Formato12CDObDao;
import gob.osinergmin.fise.domain.FiseFormato12CC;
import gob.osinergmin.fise.domain.FiseFormato12CCPK;
import gob.osinergmin.fise.domain.FiseFormato12CD;
import gob.osinergmin.fise.gart.service.Formato12CGartService;

import java.util.List;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service(value="formato12CGartServiceImpl")
public class Formato12CGartServiceImpl implements Formato12CGartService {
	
	Logger logger=Logger.getLogger(Formato12CGartServiceImpl.class);
	
	@Autowired
	@Qualifier("formato12CCDaoImpl")
	private Formato12CCDao formato12CCDao;
	
	@Autowired
	@Qualifier("formato12CDDaoImpl")
	private Formato12CDDao formato12CDDao;
	
	@Autowired
	@Qualifier("fiseZonaBenefDaoImpl")
	private FiseZonaBenefDao zonaBenefDao;
	
	@Autowired
	@Qualifier("formato12CDObDaoImpl")
	private Formato12CDObDao formato12CObsDao;
	
	@Autowired
	@Qualifier("commonDaoImpl")
	private CommonDao commonDao;
	
	@Autowired
	@Qualifier("fiseGrupoInformacionDaoImpl")
	private FiseGrupoInformacionDao fiseGrupoInformacionDao;
	
	@Override
	@Transactional
	public FiseFormato12CC obtenerFormato12CCByPK(FiseFormato12CCPK fiseFormato12CCPK) {
		FiseFormato12CC formato = null;
		formato = formato12CCDao.obtenerFormato12CCByPK(fiseFormato12CCPK);
		if(formato != null){
			formato.setFiseFormato12CDs(formato12CDDao.listarFormato12CDByFormato12CC(formato));
		}
		return formato;
	}
	
	@Override
	@Transactional
	public List<FiseFormato12CC> buscarFormato12CC(String codEmpresa, long anioDesde, long mesDesde, long anioHasta, long mesHasta, String etapa){
		
		List<FiseFormato12CC> lista = null;
		lista = formato12CCDao.buscarFormato12CC(codEmpresa, anioDesde, mesDesde, anioHasta, mesHasta, etapa);
		for (FiseFormato12CC formato : lista) {
			formato.setFiseFormato12CDs(formato12CDDao.listarFormato12CDByFormato12CC(formato));
		}
		return lista;
	}
	
	public List<FiseFormato12CD> listarFormato12CDByFormato12CC(FiseFormato12CC formato12CC) {
		List<FiseFormato12CD> lista = null;
		lista = formato12CDDao.listarFormato12CDByFormato12CC(formato12CC);
		return lista;
	}
	
}
