package gob.osinergmin.fise.dao;

import java.sql.SQLException;

import gob.osinergmin.fise.domain.FiseTipPersonal;

public interface FiseTipPersonalDao {
	
	FiseTipPersonal obtenerFiseTipPersonalByPK(long id) throws SQLException;

}
