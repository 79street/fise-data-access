package gob.osinergmin.fise.dao;

import gob.osinergmin.fise.domain.FiseDescripcionActividade;
import gob.osinergmin.fise.domain.FiseDescripcionActividadePK;

import java.sql.SQLException;
import java.util.List;

public interface FiseActividadesDao {
	
	FiseDescripcionActividade obtenerFiseDescripcionActividadeByPK(FiseDescripcionActividadePK id)
			throws SQLException;
	
	List<FiseDescripcionActividade> listarDescripcionActividade(String formato) 
			throws SQLException;
	
	

}
