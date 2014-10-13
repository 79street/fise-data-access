package gob.osinergmin.fise.gart.service;

import gob.osinergmin.fise.domain.CfgCampo;
import gob.osinergmin.fise.domain.CfgTabla;

import java.util.List;

public interface CfgCampoGartService {

	List<CfgCampo> listarCamposByTabla(CfgTabla cfgTabla);
	int obtenerPosicionFinalCampo(List<CfgCampo> listaCampo, String nombreCampo);
	//int obtenerPosicionFinalCampo(List<CfgCampo> listaCampo, String nombreCampo);
	int longitudMaximaRegistro(List<CfgCampo> listaCampo);
	
}
