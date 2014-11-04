package gob.osinergmin.fise.gart.service.impl;

import gob.osinergmin.fise.dao.Formato13ACDao;
import gob.osinergmin.fise.dao.Formato13ADDao;
import gob.osinergmin.fise.domain.FiseFormato13AC;
import gob.osinergmin.fise.domain.FiseFormato13AD;
import gob.osinergmin.fise.gart.service.Formato13AGartService;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

@Service(value="formato13AGartServiceImpl")
public class Formato13AGartServiceImpl implements Formato13AGartService {

	@Autowired
	@Qualifier("formato13ACDaoImpl")
	private Formato13ACDao formato13ACDao;
	
	@Autowired
	@Qualifier("formato13ADDaoImpl")
	private Formato13ADDao formato13ADDao;
	
	public List<FiseFormato13AC> buscarFormato13AC(String codEmpresa,
			long anioDesde, long mesDesde, long anioHasta, long mesHasta,
			String etapa) {
		List<FiseFormato13AC> lista = null;
		lista = formato13ACDao.buscarFormato13AC(codEmpresa, anioDesde, mesDesde, anioHasta, mesHasta, etapa);
		/*for (FiseFormato13AC formato : lista) {
			formato.setFiseFormato13ADs(formato12ADDao.listarFormato12ADByFormato12AC(formato));
		}*/
		return lista;
	}
	
	public List<FiseFormato13AD> listarFormato13ADByFormato13AC(FiseFormato13AC formato13AC) {
		List<FiseFormato13AD> lista = null;
		lista = formato13ADDao.listarFormato13ADByFormato13AC(formato13AC);
		/*for (FiseFormato13AC formato : lista) {
			formato.setFiseFormato13ADs(formato12ADDao.listarFormato12ADByFormato12AC(formato));
		}*/
		return lista;
	}

}
