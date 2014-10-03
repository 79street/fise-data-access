package gob.osinergmin.fise.gart.dao.impl;

import gob.osinergmin.base.dao.impl.GenericDaoImpl;
import gob.osinergmin.fise.domain.FiseZonaBenef;
import gob.osinergmin.fise.gart.dao.FiseZonaBenefGartDao;

import java.util.List;

import javax.persistence.Query;

import org.springframework.stereotype.Repository;

@Repository(value = "fiseZonaBenefGartDaoImpl")
public class FiseZonaBenefGartDaoImpl extends GenericDaoImpl implements FiseZonaBenefGartDao {

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
		}
		return fiseZonaBenef;
	}
	
}
