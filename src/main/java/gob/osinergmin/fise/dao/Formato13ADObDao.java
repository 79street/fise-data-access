package gob.osinergmin.fise.dao;

import gob.osinergmin.fise.domain.FiseFormato13AD;
import gob.osinergmin.fise.domain.FiseFormato13ADOb;

import java.util.List;

public interface Formato13ADObDao {
	
	List<FiseFormato13ADOb> listarFormato13ADObByFormato13AD(FiseFormato13AD formato13AD);
	void eliminarFormato13ADOb(FiseFormato13ADOb fiseFormato13ADOb);

}
