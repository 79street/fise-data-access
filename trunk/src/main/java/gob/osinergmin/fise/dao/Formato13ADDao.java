package gob.osinergmin.fise.dao;

import gob.osinergmin.base.dao.GenericDao;
import gob.osinergmin.fise.bean.Formato13ADReportBean;
import gob.osinergmin.fise.domain.FiseFormato13AC;
import gob.osinergmin.fise.domain.FiseFormato13AD;

import java.util.List;

import org.springframework.dao.DataIntegrityViolationException;

public interface Formato13ADDao extends GenericDao {
	
	List<FiseFormato13AD> listarFormato13ADByFormato13AC(FiseFormato13AC formato13AC);
	List<Formato13ADReportBean> listarLocalidadesPorZonasBenefFormato13ADByFormato13AC(FiseFormato13AC formato13AC);
	FiseFormato13AD savedetalle(FiseFormato13AD fiseD)throws DataIntegrityViolationException;
	FiseFormato13AD updatedetalle(FiseFormato13AD fiseD);
	Integer deletedetalle(String emp,Long anio,Long mes,String etapa);
	void eliminarFormato13AD(FiseFormato13AD fiseFormato13AD);
	
	boolean existeFormatoDetalleSectorTipico(FiseFormato13AC fiseFormato13AC, String codUbigeo, Long idZonaBenef);
}
