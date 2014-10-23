package gob.osinergmin.fise.dao.impl;

import gob.osinergmin.base.dao.impl.GenericDaoImpl;
import gob.osinergmin.fise.dao.Formato14BDDao;
import gob.osinergmin.fise.domain.FiseFormato14BD;

import java.util.List;

import javax.persistence.Query;

import org.springframework.stereotype.Repository;

@Repository(value = "formato14BDDaoImpl")
public class Formato14BDDaoImpl extends GenericDaoImpl implements Formato14BDDao {

	@SuppressWarnings("unchecked")
	public List<FiseFormato14BD> listarFormato14BD() {
		List<FiseFormato14BD> lst = null;
		try {
			StringBuffer jql = new StringBuffer();
			jql.append(" SELECT f FROM FiseFormato14BD f ");
			Query query = em.createQuery(jql.toString());
			System.out.println(query.toString());
			lst = query.getResultList();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return lst;
	}
	
}
