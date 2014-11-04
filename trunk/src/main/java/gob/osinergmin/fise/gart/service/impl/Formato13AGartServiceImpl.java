package gob.osinergmin.fise.gart.service.impl;

import gob.osinergmin.fise.dao.Formato12ACDao;
import gob.osinergmin.fise.dao.Formato13ACDao;
import gob.osinergmin.fise.domain.FiseFormato12AC;
import gob.osinergmin.fise.domain.FiseFormato13AC;
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

}
