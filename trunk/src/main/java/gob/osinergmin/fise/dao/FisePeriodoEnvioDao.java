package gob.osinergmin.fise.dao;

import gob.osinergmin.fise.domain.FisePeriodoEnvio;

import java.sql.SQLException;
import java.util.Date;
import java.util.List;

public interface FisePeriodoEnvioDao {
	
	
	void insertarFisePeriodoEnvio(FisePeriodoEnvio FisePeriodoEnvio) 
			throws SQLException;

	void actualizarFisePeriodoEnvio(FisePeriodoEnvio FisePeriodoEnvio) 
			throws SQLException;

	void eliminarFisePeriodoEnvio(FisePeriodoEnvio FisePeriodoEnvio) 
			throws SQLException;
	
	FisePeriodoEnvio obtenerFisePeriodoEnvio(Long id) 
			throws SQLException;
	
	List<FisePeriodoEnvio> listarFisePeriodoEnvioMesAnioEtapa(String codEmpresa, String nombreFormato);
	
	List<FisePeriodoEnvio> buscarFisePeriodoEnvio(String codEmpresa, Integer anioPres, 
			Integer mesPres, String formato,String etapa,
			String flagEnvio,String estado,Date fechaActual) throws SQLException;

}
