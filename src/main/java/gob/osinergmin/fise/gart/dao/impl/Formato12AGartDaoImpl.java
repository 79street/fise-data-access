package gob.osinergmin.fise.gart.dao.impl;

import gob.osinergmin.base.dao.impl.GenericDaoImpl;
import gob.osinergmin.fise.domain.FiseFormato12AC;
import gob.osinergmin.fise.gart.dao.Formato12AGartDao;

import java.util.List;

import javax.persistence.Query;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

@Repository(value = "formato12AGartDaoImpl")
public class Formato12AGartDaoImpl extends GenericDaoImpl implements Formato12AGartDao {

	@SuppressWarnings("unchecked")
	@Override
	@Transactional
	public List<FiseFormato12AC> listarFormato12AC() {
		List<FiseFormato12AC> lst = null;
		try {
			StringBuffer jql = new StringBuffer();
			jql.append(" SELECT f FROM FiseFormato12AC f ");
			Query query = em.createQuery(jql.toString());
			lst = query.getResultList();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return lst;
	}
	
}
