package gob.osinergmin.fise.dao;

import java.sql.SQLException;
import java.util.List;

import gob.osinergmin.fise.domain.FiseTipPersonal;

public interface FiseTipPersonalDao {
	
	FiseTipPersonal obtenerFiseTipPersonalByPK(long id) throws SQLException;
	
	List<FiseTipPersonal> listarFiseTipPersonal() throws SQLException;

}
