package gob.osinergmin.fise.gart.service;

import gob.osinergmin.fise.bean.CumplimientoReportBean;

import java.util.HashMap;
import java.util.List;

public interface FormatoCumplimientoService {
	
	HashMap<String, Object> mapearParametrosCumplimientoReport(CumplimientoReportBean cumplimientoReportBean);
	List<CumplimientoReportBean> listarFormatoCumplimientoReportBean(long p_ano, long p_mes, String p_solicitud);

}
