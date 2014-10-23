package gob.osinergmin.fise.dao;

import gob.osinergmin.fise.domain.FiseFormato12AC;
import gob.osinergmin.fise.domain.FiseFormato12AD;
import gob.osinergmin.fise.domain.FiseFormato12ADOb;

import java.util.List;

public interface Formato12ADObDao {
	
	int validarFormato12A(FiseFormato12AC fiseformato12AC, String tipoFormato, String usuario, String terminal);
	List<FiseFormato12ADOb> listarFormato12ADObByFormato12AD(FiseFormato12AD formato12AD);
	void eliminarFormato12ADOb(FiseFormato12ADOb fiseFormato12ADOb);
	
}
