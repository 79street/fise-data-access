package gob.osinergmin.fise.dao;

import gob.osinergmin.fise.domain.FiseGrupoInformacion;

import java.sql.SQLException;
import java.util.List;

public interface FiseGrupoInformacionDao {
	
	FiseGrupoInformacion obtenerFiseGrupoInformacionByPK(long id) throws SQLException;
	
	List<FiseGrupoInformacion> listarGrupoInformacion(String tipo) throws SQLException;

}
