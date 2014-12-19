package gob.osinergmin.fise.dao;

import gob.osinergmin.fise.domain.FiseControlEnvioPorGrupo;
import gob.osinergmin.fise.domain.FiseControlEnvioPorGrupoPK;

import java.sql.SQLException;

public interface FiseControlEnvioDao {
	
	void insertarFiseControlEnvio(FiseControlEnvioPorGrupo fiseControlEnvioPorGrupo) 
			throws SQLException;
	
	FiseControlEnvioPorGrupo obtenerFiseControlEnvioByPK(FiseControlEnvioPorGrupoPK id)
			throws SQLException;
	
	void actualizarFiseControlEnvio(FiseControlEnvioPorGrupo FiseControlEnvioPorGrupo) 
			throws SQLException;

}
