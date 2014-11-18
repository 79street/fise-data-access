package gob.osinergmin.fise.gart.service.impl;

import gob.osinergmin.fise.bean.CorreoBean;
import gob.osinergmin.fise.bean.Formato12A12BGeneric;
import gob.osinergmin.fise.bean.Formato12C12D13Generic;
import gob.osinergmin.fise.bean.Formato14Generic;
import gob.osinergmin.fise.dao.CommonDao;
import gob.osinergmin.fise.dao.FiseGrupoInformacionDao;
import gob.osinergmin.fise.domain.CfgTabla;
import gob.osinergmin.fise.domain.FiseGrupoInformacion;
import gob.osinergmin.fise.gart.service.CommonGartService;

import java.sql.SQLException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

@Service(value="commonGartServiceImpl")
public class CommonGartServiceImpl implements CommonGartService {
	
	@Autowired
	@Qualifier("commonDaoImpl")
	private CommonDao commonDao;
	
	@Autowired
	@Qualifier("fiseGrupoInformacionDaoImpl")
	private FiseGrupoInformacionDao fiseGrupoInformacionDao;
	
	@Override
	@Transactional
	public int obtenerSecuencia() {
		return commonDao.obtenerSecuencia();
	}
	
	@Override
	@Transactional(propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
	public int validarFormatos_12A12B(Formato12A12BGeneric formato12, String tipoFormato, String usuario, String terminal) {
		return commonDao.validarFormatos_12A12B(formato12, tipoFormato, usuario, terminal);
	}
	
	@Override
	@Transactional(propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
	public int validarFormatos_12C12D13A(Formato12C12D13Generic formato, String tipoFormato, String usuario, String terminal) {
		return commonDao.validarFormatos_12C12D13A(formato, tipoFormato, usuario, terminal);
	}
	
	@Override
	@Transactional(propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
	public int validarFormatos_14(Formato14Generic formato14, String tipoFormato, String usuario, String terminal) {
		return commonDao.validarFormatos_14(formato14, tipoFormato, usuario, terminal);
	}

	@Override
	@Transactional
	public List<CorreoBean> obtenerListaCorreosDestinatarios() {
		return commonDao.obtenerListaCorreosDestinatarios();
	}
	
	@Override
	@Transactional
	public String obtenerEstadoProceso(String codEmpresa, String tipoFormato, long anoPresentacion, long mesPresentacion, String codEtapa) {
		return commonDao.obtenerEstadoProceso(codEmpresa, tipoFormato, anoPresentacion, mesPresentacion, codEtapa);
	}
	
	@Override
	@Transactional
	public FiseGrupoInformacion obtenerFiseGrupoInformacionByPK(long id) {
		FiseGrupoInformacion dto = null;
		try {
			dto = fiseGrupoInformacionDao.obtenerFiseGrupoInformacionByPK(id);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return dto;
	}
	
	@Override
	@Transactional
	public Long obtenerIdGrupoInformacion(Long anioPresentacion, Long mesPresentacion) {
		return commonDao.obtenerIdGrupoInformacion(anioPresentacion, mesPresentacion);
	}
	
}
