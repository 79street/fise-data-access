package gob.osinergmin.fise.dao.impl;

import gob.osinergmin.base.dao.impl.GenericDaoImpl;
import gob.osinergmin.fise.dao.Formato14BCDao;
import gob.osinergmin.fise.domain.FiseFormato14BC;

import java.util.List;

import javax.persistence.Query;

import org.springframework.stereotype.Repository;

@Repository(value = "formato14BCDaoImpl")
public class Formato14BCDaoImpl extends GenericDaoImpl implements Formato14BCDao {
	
	@SuppressWarnings("unchecked")
	public List<FiseFormato14BC> listarFormato14BC() {
		List<FiseFormato14BC> lst = null;
		try {
			StringBuffer jql = new StringBuffer();
			jql.append(" SELECT f FROM FiseFormato14BC f ");
			Query query = em.createQuery(jql.toString());
			System.out.println(query.toString());
			lst = query.getResultList();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return lst;
	}

}
