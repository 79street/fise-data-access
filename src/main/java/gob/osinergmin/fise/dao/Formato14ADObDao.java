package gob.osinergmin.fise.dao;

import gob.osinergmin.fise.domain.FiseFormato14AD;
import gob.osinergmin.fise.domain.FiseFormato14ADOb;

import java.util.List;

public interface Formato14ADObDao {
	
	List<FiseFormato14ADOb> listarFormato14ADObByFormato14AD(FiseFormato14AD formato14AD);
	void eliminarFormato14ADOb(FiseFormato14ADOb fiseFormato14ADOb);

}
