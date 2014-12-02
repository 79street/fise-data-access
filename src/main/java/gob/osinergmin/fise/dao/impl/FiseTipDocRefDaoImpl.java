package gob.osinergmin.fise.dao.impl;

import gob.osinergmin.base.dao.impl.GenericDaoImpl;
import gob.osinergmin.fise.dao.FiseTipDocRefDao;
import gob.osinergmin.fise.domain.FiseTipDocRef;

import java.util.List;

import javax.persistence.Query;

import org.springframework.stereotype.Repository;

@Repository(value = "fiseTipDocRefDaoImpl")
public class FiseTipDocRefDaoImpl extends GenericDaoImpl implements FiseTipDocRefDao {

	@SuppressWarnings("unchecked")
	//@Override
	//@Transactional
	public List<FiseTipDocRef> listarFiseTipDocRef() {
		List<FiseTipDocRef> lst = null;
		try {
			StringBuffer jql = new StringBuffer();
			jql.append(" SELECT f FROM FiseTipDocRef f ");
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
	public FiseTipDocRef obtenerFiseTipDocRefByPK(String id){
		FiseTipDocRef fiseTipDocRef = null;
		try{
			//em.getTransaction().begin();
			fiseTipDocRef = em.find(FiseTipDocRef.class, id);
			//em.getTransaction().commit();
		}catch (Exception e) {
			e.printStackTrace();
		} finally {
			 em.close();
		 }
		return fiseTipDocRef;
	}
	
}
