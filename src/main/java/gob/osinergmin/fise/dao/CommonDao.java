package gob.osinergmin.fise.dao;

import java.util.List;

import gob.osinergmin.fise.bean.CorreoBean;
import gob.osinergmin.fise.bean.Formato12A12BGeneric;
import gob.osinergmin.fise.bean.Formato12C12D13Generic;
import gob.osinergmin.fise.bean.Formato14Generic;

public interface CommonDao {
	
	int obtenerSecuencia();
	int validarFormatos_12A12B(Formato12A12BGeneric formato, String tipoFormato, String usuario, String terminal);
	int validarFormatos_12C12D13A(Formato12C12D13Generic formato, String tipoFormato, String usuario, String terminal);
	int validarFormatos_14(Formato14Generic formato, String tipoFormato, String usuario, String terminal);
	List<CorreoBean> obtenerListaCorreosDestinatarios();
	Long obtenerIdGrupoInformacion(Long anioPresentacion,Long mesPresentacion);
	String obtenerEstadoProceso(String codEmpresa, String tipoFormato, long anoPresentacion, long mesPresentacion, String codEtapa);
	
}
