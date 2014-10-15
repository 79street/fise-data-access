package gob.osinergmin.fise.dao.impl;

import gob.osinergmin.base.dao.impl.GenericDaoImpl;
import gob.osinergmin.fise.dao.FiseObservacionDao;
import gob.osinergmin.fise.domain.FiseObservacion;

import java.util.List;

import javax.persistence.Query;

import org.springframework.stereotype.Repository;

@Repository(value = "fiseObservacionDaoImpl")
public class FiseObservacionDaoImpl extends GenericDaoImpl implements FiseObservacionDao {

	@SuppressWarnings("unchecked")
	public List<FiseObservacion> listarFiseObservacion() {
		List<FiseObservacion> lst = null;
		try {
			StringBuffer jql = new StringBuffer();
			jql.append(" SELECT f FROM FiseObservacion f ");
			Query query = em.createQuery(jql.toString());
			System.out.println(query.toString());
			lst = query.getResultList();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return lst;
	}
	
}
