package gob.osinergmin.fise.gart.service.impl;

import gob.osinergmin.fise.bean.Formato13ACBean;
import gob.osinergmin.fise.bean.Formato13ADReportBean;
import gob.osinergmin.fise.constant.FiseConstants;
import gob.osinergmin.fise.dao.Formato13ACDao;
import gob.osinergmin.fise.dao.Formato13ADDao;
import gob.osinergmin.fise.domain.FiseFormato13AC;
import gob.osinergmin.fise.domain.FiseFormato13AD;
import gob.osinergmin.fise.gart.service.Formato13AGartService;

import java.util.HashMap;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

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

}
