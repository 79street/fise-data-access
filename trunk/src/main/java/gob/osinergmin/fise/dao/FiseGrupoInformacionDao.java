package gob.osinergmin.fise.dao;

import gob.osinergmin.fise.domain.FiseGrupoInformacion;

import java.sql.SQLException;

public interface FiseGrupoInformacionDao {
	
	FiseGrupoInformacion obtenerFiseGrupoInformacionByPK(long id) throws SQLException;

}
