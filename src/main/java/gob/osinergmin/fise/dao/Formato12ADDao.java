package gob.osinergmin.fise.dao;

import gob.osinergmin.base.dao.GenericDao;
import gob.osinergmin.fise.domain.FiseFormato12AC;
import gob.osinergmin.fise.domain.FiseFormato12AD;
import gob.osinergmin.fise.domain.FiseFormato12ADPK;

import java.util.List;

public interface Formato12ADDao extends GenericDao {
	
	FiseFormato12AD obtenerFormato12ADByPK(FiseFormato12ADPK fiseFormato12ADPK);
	void registrarFormato12AD(FiseFormato12AD fiseFormato12AD);
	void modificarFormato12AD(FiseFormato12AD fiseFormato12AD);
	void eliminarFormato12AD(FiseFormato12AD fiseFormato12AD);
	List<FiseFormato12AD> listarFormato12ADByFormato12AC(FiseFormato12AC formato12AC);

}
