package gob.osinergmin.fise.dao;

import gob.osinergmin.fise.domain.FiseFormato12CD;
import gob.osinergmin.fise.domain.FiseFormato12CDOb;

import java.util.List;

public interface Formato12CDObDao {
	
	List<FiseFormato12CDOb> listarFormato12CDObByFormato12CD(FiseFormato12CD formato12CD);
	void eliminarFormato12CDOb(FiseFormato12CDOb fiseFormato12CDOb);

}
