package gob.osinergmin.fise.dao;

import gob.osinergmin.fise.domain.FiseFormato12DD;
import gob.osinergmin.fise.domain.FiseFormato12DDOb;

import java.util.List;

public interface Formato12DDObDao {
	
	List<FiseFormato12DDOb> listarFormato12DDObByFormato12DD(FiseFormato12DD formato12DD);
	void eliminarFormato12DDOb(FiseFormato12DDOb fiseFormato12DDOb);

}
