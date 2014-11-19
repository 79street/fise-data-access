package gob.osinergmin.fise.gart.service.impl;

import gob.osinergmin.fise.bean.CumplimientoReportBean;
import gob.osinergmin.fise.constant.FiseConstants;
import gob.osinergmin.fise.dao.CommonDao;
import gob.osinergmin.fise.gart.service.FormatoCumplimientoService;

import java.util.HashMap;
import java.util.List;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;


@Service(value="formatoCumplimientoServiceImpl")
public class FormatoCumplimientoServiceImpl implements FormatoCumplimientoService  {
	
	Logger logger=Logger.getLogger(FormatoCumplimientoServiceImpl.class);

	
	@Autowired
	@Qualifier("commonDaoImpl")
	private CommonDao commonDao ;
	
	@Override
		public HashMap<String, Object> mapearParametrosCumplimientoReport(CumplimientoReportBean cumplimientoReportBean){
			
			HashMap<String, Object> mapJRParams = new HashMap<String, Object>();
			mapJRParams.put(FiseConstants.PARAM_ANO_CUMPLI, cumplimientoReportBean.getAnioPresent());
			mapJRParams.put(FiseConstants.PARAM_MES_CUMPLI, cumplimientoReportBean.getMesPresent());
			mapJRParams.put(FiseConstants.PARAM_ETAPA_CUMPLI, cumplimientoReportBean.getEtapa());
			return mapJRParams;
	
	 }
	
	@Transactional
	 public List<CumplimientoReportBean> listarFormatoCumplimientoReportBean(long p_ano, long p_mes, String p_solicitud){
		List<CumplimientoReportBean> lista = commonDao.listarFormatolistaDet(p_ano, p_mes, p_solicitud);
		return lista; 
	}	 
	 
}

