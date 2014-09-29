package gob.osinergmin.fise.gart.service.impl;

import gob.osinergmin.fise.domain.FiseFormato12AC;
import gob.osinergmin.fise.domain.FiseFormato12ACPK;
import gob.osinergmin.fise.gart.dao.Formato12AGartDao;
import gob.osinergmin.fise.gart.service.Formato12AGartService;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

@Service(value="formato12AGartServiceImpl")
public class Formato12AGartServiceImpl implements Formato12AGartService {

	@Autowired
	@Qualifier("formato12AGartDaoImpl")
	private Formato12AGartDao formato12AGartDao;
	
	//@Override
	public List<FiseFormato12AC> listarFormato12AC() {
		return formato12AGartDao.listarFormato12AC();
	}
	
	@Override
	public FiseFormato12AC obtenerFormato12ACByPK(FiseFormato12ACPK fiseFormato12ACPK) {
		return formato12AGartDao.obtenerFormato12ACByPK(fiseFormato12ACPK);
	}
	
	@Override
	public void registrarFormato12AC(FiseFormato12AC fiseFormato12AC) {
		formato12AGartDao.registrarFormato12AC(fiseFormato12AC);
	}
	
	@Override
	public void modificarFormato12AC(FiseFormato12AC fiseFormato12AC) {
		formato12AGartDao.modificarFormato12AC(fiseFormato12AC);
	}
	
	@Override
	public void eliminarFormato12AC(FiseFormato12AC fiseFormato12AC) {
		formato12AGartDao.eliminarFormato12AC(fiseFormato12AC);
	}
	
	@Override
	public boolean existeFormato12AC(FiseFormato12AC fiseFormato12AC) {
		return formato12AGartDao.existeFormato12AC(fiseFormato12AC);
	}

	@Override
	public List<FiseFormato12AC> buscarFormato12AC(String codEmpresa, long anioDesde, long mesDesde, long anioHasta, long mesHasta, String etapa){
		return formato12AGartDao.buscarFormato12AC(codEmpresa, anioDesde, mesDesde, anioHasta, mesHasta, etapa);
	}

}
