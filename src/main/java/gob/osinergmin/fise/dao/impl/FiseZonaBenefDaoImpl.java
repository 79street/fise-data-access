package gob.osinergmin.fise.dao.impl;

import gob.osinergmin.base.dao.impl.GenericDaoImpl;
import gob.osinergmin.fise.dao.FiseZonaBenefDao;
import gob.osinergmin.fise.domain.FiseZonaBenef;

import java.util.List;

import javax.persistence.Query;

import org.springframework.stereotype.Repository;

@Repository(value = "fiseZonaBenefDaoImpl")
public class FiseZonaBenefDaoImpl extends GenericDaoImpl implements FiseZonaBenefDao {

	@SuppressWarnings("unchecked")
	//@Override
	//@Transactional
	public List<FiseZonaBenef> listarFiseZonaBenef() {
		List<FiseZonaBenef> lst = null;
		try {
			StringBuffer jql = new StringBuffer();
			jql.append(" SELECT f FROM FiseZonaBenef f ");
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
	
	@Override
	public FiseZonaBenef obtenerFiseZonaBenefByPK(long id){
		FiseZonaBenef fiseZonaBenef = null;
		try{
			//em.getTransaction().begin();
			fiseZonaBenef = em.find(FiseZonaBenef.class, id);
			//em.getTransaction().commit();
		}catch (Exception e) {
			e.printStackTrace();
		} finally {
			 em.close();
		 }
		return fiseZonaBenef;
	}
	
}
