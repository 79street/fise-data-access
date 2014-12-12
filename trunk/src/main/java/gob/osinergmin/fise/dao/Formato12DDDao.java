package gob.osinergmin.fise.dao;

import gob.osinergmin.base.dao.GenericDao;
import gob.osinergmin.fise.domain.FiseFormato12DC;
import gob.osinergmin.fise.domain.FiseFormato12DD;
import gob.osinergmin.fise.domain.FiseFormato12DDPK;

import java.util.List;

public interface Formato12DDDao extends GenericDao {
	
	FiseFormato12DD obtenerFormato12DDByPK(FiseFormato12DDPK fiseFormato12DDPK);
	List<FiseFormato12DD> listarFormato12DDByFormato12DC(FiseFormato12DC formato12DC);
	void registrarFormato12DD(FiseFormato12DD fiseFormato12DD);
	void modificarFormato12DD(FiseFormato12DD fiseFormato12DD);
	void eliminarFormato12DD(FiseFormato12DD fiseFormato12DD);
	Long obtenerMaximoItemEtapa(FiseFormato12DDPK formato12DDPK);
	
}
