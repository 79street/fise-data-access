package gob.osinergmin.fise.gart.dao;

import gob.osinergmin.base.dao.GenericDao;
import gob.osinergmin.fise.domain.FiseFormato14AC;
import gob.osinergmin.fise.domain.FiseFormato14AD;
import gob.osinergmin.fise.domain.FiseFormato14ADPK;

import java.util.List;

public interface Formato14ADGartDao extends GenericDao {
	
	FiseFormato14AD obtenerFormato14ADByPK(FiseFormato14ADPK fiseFormato14ADPK);
	List<FiseFormato14AD> listarFormato14ADByFormato14AC(FiseFormato14AC formato14AC);
	FiseFormato14AD obtenerFormato14ADVigente(String codEmpresa, long anioVigencia, long idZonaBenef);

}
