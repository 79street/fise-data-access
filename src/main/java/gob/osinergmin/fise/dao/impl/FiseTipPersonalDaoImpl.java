package gob.osinergmin.fise.dao.impl;

import gob.osinergmin.base.dao.impl.GenericDaoImpl;
import gob.osinergmin.fise.dao.FiseTipPersonalDao;
import gob.osinergmin.fise.domain.FiseTipPersonal;

import java.sql.SQLException;
import java.util.List;

import javax.persistence.Query;

import org.springframework.stereotype.Repository;

@Repository(value = "fiseTipPersonalDaoImpl")
public class FiseTipPersonalDaoImpl extends GenericDaoImpl implements FiseTipPersonalDao {

	@Override
	public FiseTipPersonal obtenerFiseTipPersonalByPK(long id)
			throws SQLException {
		return em.find(FiseTipPersonal.class, id); 
	}
	
	@Override
	public List<FiseTipPersonal> listarFiseTipPersonal() throws SQLException{
		List<FiseTipPersonal> lst = null;
		try {
			StringBuffer jql = new StringBuffer();
			jql.append(" SELECT p FROM FiseTipPersonal p ");
			Query query = em.createQuery(jql.toString());
			System.out.println(query.toString());
			lst = query.getResultList();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			 em.close();
		 }
		return lst;
	}

}
