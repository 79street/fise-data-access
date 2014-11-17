package gob.osinergmin.fise.gart.service;

import gob.osinergmin.fise.bean.Formato13ACBean;
import gob.osinergmin.fise.bean.Formato13ADReportBean;
import gob.osinergmin.fise.domain.FiseFormato13AC;
import gob.osinergmin.fise.domain.FiseFormato13ACPK;
import gob.osinergmin.fise.domain.FiseFormato13AD;
import gob.osinergmin.fise.domain.FiseFormato13ADOb;

import java.util.HashMap;
import java.util.List;

public interface Formato13AGartService {

	List<FiseFormato13AC> buscarFormato13AC(String codEmpresa, long anioDesde, long mesDesde, long anioHasta, long mesHasta, String etapa);
	List<FiseFormato13AD> listarFormato13ADByFormato13AC(FiseFormato13AC formato13AC);
	Formato13ACBean estructurarFormato13ABeanByFiseFormato13AC(FiseFormato13AC formato);
	HashMap<String, Object> mapearParametrosFormato13A(Formato13ACBean formato13ABean);
	List<Formato13ADReportBean> listarLocalidadesPorZonasBenefFormato13ADByFormato13AC(FiseFormato13AC formato13AC);

	FiseFormato13AC savecabecera(FiseFormato13AC fiseC)throws Exception;
	FiseFormato13AD savedetalle(FiseFormato13AD fiseD)throws Exception;
	FiseFormato13AC obtenerFormato13ACByPK(FiseFormato13ACPK fiseFormato13ACPK);
	FiseFormato13AC updatecabecera(FiseFormato13AC fiseC);
	FiseFormato13AD updatedetalle(FiseFormato13AD fiseD);
	
	List<FiseFormato13ADOb> listarFormato13ADObByFormato13AD(FiseFormato13AD formato13AD);
}
