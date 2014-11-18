package gob.osinergmin.fise.gart.service.impl;

import gob.osinergmin.fise.bean.Formato13ACBean;
import gob.osinergmin.fise.bean.Formato13ADReportBean;
import gob.osinergmin.fise.constant.FiseConstants;
import gob.osinergmin.fise.dao.Formato13ACDao;
import gob.osinergmin.fise.dao.Formato13ADDao;
import gob.osinergmin.fise.dao.Formato13ADObDao;
import gob.osinergmin.fise.domain.FiseFormato13AC;
import gob.osinergmin.fise.domain.FiseFormato13ACPK;
import gob.osinergmin.fise.domain.FiseFormato13AD;
import gob.osinergmin.fise.domain.FiseFormato13ADOb;
import gob.osinergmin.fise.domain.FiseFormato14AD;
import gob.osinergmin.fise.domain.FiseFormato14ADOb;
import gob.osinergmin.fise.gart.service.Formato13AGartService;

import java.util.HashMap;
import java.util.List;

import org.hibernate.exception.ConstraintViolationException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

@Service(value="formato13AGartServiceImpl")
public class Formato13AGartServiceImpl implements Formato13AGartService {

	@Autowired
	@Qualifier("formato13ACDaoImpl")
	private Formato13ACDao formato13ACDao;
	
	@Autowired
	@Qualifier("formato13ADDaoImpl")
	private Formato13ADDao formato13ADDao;
	
	@Autowired
	@Qualifier("formato13ADObDaoImpl")
	private Formato13ADObDao formato13ADObDao;
	
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
	
	public Formato13ACBean estructurarFormato13ABeanByFiseFormato13AC(FiseFormato13AC formato) {
		
		//setting the values of Bean
		Formato13ACBean formato13ABean = new Formato13ACBean();
		formato13ABean.setAnioPresent(formato.getId().getAnoPresentacion());
		formato13ABean.setMesPresent(formato.getId().getMesPresentacion());
		return formato13ABean;
     }

	
	public HashMap<String, Object> mapearParametrosFormato13A(Formato13ACBean formato13ABean) {
				
		HashMap<String, Object> mapJRParams = new HashMap<String, Object>();
		mapJRParams.put(FiseConstants.PARAM_DESC_EMPRESA_F13A, formato13ABean.getDescEmpresa());
		mapJRParams.put(FiseConstants.PARAM_ANO_PRES_F13A, formato13ABean.getAnioPresent());
		mapJRParams.put(FiseConstants.PARAM_DESC_MES_PRES_F13A, formato13ABean.getDescMesPresentacion());
		
		return mapJRParams;
	}	
	
	@Transactional
	public  List<Formato13ADReportBean> listarLocalidadesPorZonasBenefFormato13ADByFormato13AC(FiseFormato13AC formato13AC){
		return formato13ADDao.listarLocalidadesPorZonasBenefFormato13ADByFormato13AC(formato13AC);
	}

	@Transactional
	public FiseFormato13AC savecabecera(FiseFormato13AC fiseC) throws ConstraintViolationException {
		return formato13ACDao.savecabecera(fiseC);
	}

	@Transactional
	public FiseFormato13AD savedetalle(FiseFormato13AD fiseD) {
		return formato13ADDao.savedetalle(fiseD);
	}

	public FiseFormato13AC obtenerFormato13ACByPK(FiseFormato13ACPK fiseFormato13ACPK){
		FiseFormato13AC formato = null;
		formato = formato13ACDao.obtenerFormato13ACByPK(fiseFormato13ACPK);
		if(formato != null){
			List<FiseFormato13AD> listaDetalle = formato13ADDao.listarFormato13ADByFormato13AC(formato);
			//mostramos la fecha inicio fin de vigencia de uno de los detalles
			if( listaDetalle!=null && !listaDetalle.isEmpty() ){
				formato.setFiseFormato13ADs(listaDetalle);
				for (FiseFormato13AD detalle : listaDetalle) {
					if( (detalle.getAnoInicioVigencia()!=null && detalle.getAnoInicioVigencia()!=0) && 
							(detalle.getAnoFinVigencia()!=null && detalle.getAnoFinVigencia()!=0) ){
						formato.setAnoInicioVigenciaDetalle(detalle.getAnoInicioVigencia());
						formato.setAnoFinVigenciaDetalle(detalle.getAnoFinVigencia());
						break;
					}
				}
			}
		}
		return formato;
	}
	
	@Transactional
	public FiseFormato13AC updatecabecera(FiseFormato13AC fiseC) {
		return formato13ACDao.updatecabecera(fiseC);
	}

	@Transactional
	public FiseFormato13AD updatedetalle(FiseFormato13AD fiseD) {
		return formato13ADDao.updatedetalle(fiseD);
	}
	
	@Override
	@Transactional
	public List<FiseFormato13ADOb> listarFormato13ADObByFormato13AD(FiseFormato13AD formato13AD){
		return formato13ADObDao.listarFormato13ADObByFormato13AD(formato13AD); 
	}

	@Override
	@Transactional
	public Integer deletedetalle(String emp, Integer anio, Integer mes, String etapa) {
		// TODO Auto-generated method stub
		return formato13ADDao.deletedetalle(emp, anio, mes, etapa);
	}
	
	//eliminar cabecera con detalle y observaciones
	@Override
	@Transactional(propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
	public void eliminarCabecera(FiseFormato13AC fiseFormato13AC) {
		List<FiseFormato13AD> lista = null;
		lista = formato13ADDao.listarFormato13ADByFormato13AC(fiseFormato13AC);
		for (FiseFormato13AD detalle : lista) {
			List<FiseFormato13ADOb> listaObservacion = formato13ADObDao.listarFormato13ADObByFormato13AD(detalle);
			for (FiseFormato13ADOb observacion : listaObservacion) {
				formato13ADObDao.eliminarFormato13ADOb(observacion);
			}
			formato13ADDao.eliminarFormato13AD(detalle);
		}
		formato13ACDao.eliminarFormato13AC(fiseFormato13AC);
	}
	
	@Override
	@Transactional(propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
	public void eliminarDetalle(FiseFormato13AD fiseFormato13AD) {
		List<FiseFormato13ADOb> listaObservacion = formato13ADObDao.listarFormato13ADObByFormato13AD(fiseFormato13AD);
		for (FiseFormato13ADOb observacion : listaObservacion) {
			formato13ADObDao.eliminarFormato13ADOb(observacion);
		}
		formato13ADDao.eliminarFormato13AD(fiseFormato13AD);
	}
	
}
