package gob.osinergmin.fise.dao.impl;

import gob.osinergmin.base.dao.impl.GenericDaoImpl;
import gob.osinergmin.fise.dao.FiseTipPersonalDao;
import gob.osinergmin.fise.domain.FiseTipPersonal;

import java.sql.SQLException;

import org.springframework.stereotype.Repository;

@Repository(value = "fiseTipPersonalDaoImpl")
public class FiseTipPersonalDaoImpl extends GenericDaoImpl implements FiseTipPersonalDao {

	@Override
	public FiseTipPersonal obtenerFiseTipPersonalByPK(long id)
			throws SQLException {
		return em.find(FiseTipPersonal.class, id); 
	}

}
