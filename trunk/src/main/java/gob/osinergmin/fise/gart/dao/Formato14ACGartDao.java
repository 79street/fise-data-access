package gob.osinergmin.fise.gart.dao;

import gob.osinergmin.base.dao.GenericDao;
import gob.osinergmin.fise.domain.FiseFormato14AC;
import gob.osinergmin.fise.domain.FiseFormato14ACPK;

public interface Formato14ACGartDao extends GenericDao {
	
	FiseFormato14AC obtenerFormato14ACByPK(FiseFormato14ACPK fiseFormato14ACPK);
	
}
