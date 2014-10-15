package gob.osinergmin.fise.dao;

import gob.osinergmin.fise.domain.FisePeriodoEnvio;

import java.util.List;

public interface FisePeriodoEnvioDao {
	
	List<FisePeriodoEnvio> listarFisePeriodoEnvioMesAnioEtapa(String codEmpresa, String nombreFormato);

}
