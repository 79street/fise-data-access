package gob.osinergmin.fise.dao;

import gob.osinergmin.base.dao.GenericDao;
import gob.osinergmin.fise.domain.FiseFormato12CC;
import gob.osinergmin.fise.domain.FiseFormato12CD;
import gob.osinergmin.fise.domain.FiseFormato12CDPK;

import java.util.List;

public interface Formato12CDDao extends GenericDao {
	
	FiseFormato12CD obtenerFormato12CDByPK(FiseFormato12CDPK fiseFormato12CDPK);
	List<FiseFormato12CD> listarFormato12CDByFormato12CC(FiseFormato12CC formato12CC);
	void registrarFormato12CD(FiseFormato12CD fiseFormato12CD);
	void modificarFormato12CD(FiseFormato12CD fiseFormato12CD);
	void eliminarFormato12CD(FiseFormato12CD fiseFormato12CD);
	Long obtenerMaximoItemEtapa(FiseFormato12CDPK formato12CDPK);
	
}
