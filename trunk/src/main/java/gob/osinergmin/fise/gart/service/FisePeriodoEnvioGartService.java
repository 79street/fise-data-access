package gob.osinergmin.fise.gart.service;

import gob.osinergmin.fise.domain.FisePeriodoEnvio;

import java.util.List;

public interface FisePeriodoEnvioGartService {
	
	List<FisePeriodoEnvio> listarFisePeriodoEnvioMesAnioEtapa(String codEmpresa, String nombreFormato);

}
