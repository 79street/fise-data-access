package gob.osinergmin.fise.gart.service.impl;

import gob.osinergmin.fise.dao.Formato14ACDao;
import gob.osinergmin.fise.dao.Formato14ADDao;
import gob.osinergmin.fise.domain.FiseFormato14AC;
import gob.osinergmin.fise.domain.FiseFormato14ACPK;
import gob.osinergmin.fise.domain.FiseFormato14AD;
import gob.osinergmin.fise.gart.service.Formato14AGartService;

import java.util.List;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

@Service(value="formato14AGartServiceImpl")
public class Formato14AGartServiceImpl implements Formato14AGartService {
	
	Logger logger=Logger.getLogger(Formato14AGartServiceImpl.class);
	
	@Autowired
	@Qualifier("formato14ACDaoImpl")
	private Formato14ACDao formato14ACDao;
	
	@Autowired
	@Qualifier("formato14ADDaoImpl")
	private Formato14ADDao formato14ADDao;
	
	@Override
	public FiseFormato14AD obtenerFormato14ADVigente(String codEmpresa, long anioVigencia, long idZonaBenef){
		return formato14ADDao.obtenerFormato14ADVigente(codEmpresa, anioVigencia, idZonaBenef);
	}

	@Override
	public FiseFormato14AC obtenerFormato14ACByPK(FiseFormato14ACPK fiseFormato14ACPK) {
		FiseFormato14AC formato = null;
		formato = formato14ACDao.obtenerFormato14ACByPK(fiseFormato14ACPK);
		if(formato != null){
			formato.setFiseFormato14ADs(formato14ADDao.listarFormato14ADByFormato14AC(formato));
		}
		return formato;
	}
	
	@Override
	public List<FiseFormato14AC> buscarFormato14AC(String codEmpresa, long anioDesde, long mesDesde, long anioHasta, long mesHasta, String etapa){
		
		List<FiseFormato14AC> lista = null;
		lista = formato14ACDao.buscarFormato14AC(codEmpresa, anioDesde, mesDesde, anioHasta, mesHasta, etapa);
		for (FiseFormato14AC formato : lista) {
			formato.setFiseFormato14ADs(formato14ADDao.listarFormato14ADByFormato14AC(formato));
		}
		return lista;
	}
	
}
