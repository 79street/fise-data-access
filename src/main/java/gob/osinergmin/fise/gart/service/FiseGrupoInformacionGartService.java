package gob.osinergmin.fise.gart.service;

import gob.osinergmin.fise.domain.FiseGrupoInformacion;

import java.util.List;

public interface FiseGrupoInformacionGartService {
	
	List<FiseGrupoInformacion> listarGrupoInformacion(String tipo) throws Exception;

}
