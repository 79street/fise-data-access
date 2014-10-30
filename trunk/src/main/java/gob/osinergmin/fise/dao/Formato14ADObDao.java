package gob.osinergmin.fise.dao;

import gob.osinergmin.fise.domain.FiseFormato14AC;
import gob.osinergmin.fise.domain.FiseFormato14AD;
import gob.osinergmin.fise.domain.FiseFormato14ADOb;

import java.util.List;

public interface Formato14ADObDao {
	
	int validarFormato14A(FiseFormato14AC fiseformato14AC, String tipoFormato, String usuario, String terminal);
	List<FiseFormato14ADOb> listarFormato14ADObByFormato14AD(FiseFormato14AD formato14AD);
	void eliminarFormato14ADOb(FiseFormato14ADOb fiseFormato14ADOb);

}
