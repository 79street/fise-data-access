package gob.osinergmin.fise.dao.impl;

import gob.osinergmin.base.dao.impl.GenericDaoImpl;
import gob.osinergmin.fise.dao.FiseTipGastoDao;
import gob.osinergmin.fise.domain.FiseTipGasto;

import java.util.List;

import javax.persistence.Query;

import org.springframework.stereotype.Repository;

@Repository(value = "fiseTipGastoDaoImpl")
public class FiseTipGastoDaoImpl extends GenericDaoImpl implements FiseTipGastoDao {

	@SuppressWarnings("unchecked")
	//@Override
	//@Transactional
	public List<FiseTipGasto> listarFiseTipGasto() {
		List<FiseTipGasto> lst = null;
		try {
			StringBuffer jql = new StringBuffer();
			jql.append(" SELECT f FROM FiseTipGasto f ");
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
	public FiseTipGasto obtenerFiseTipGastoByPK(String id){
		FiseTipGasto fiseTipGasto = null;
		try{
			//em.getTransaction().begin();
			fiseTipGasto = em.find(FiseTipGasto.class, id);
			//em.getTransaction().commit();
		}catch (Exception e) {
			e.printStackTrace();
		} finally {
			 em.close();
		 }
		return fiseTipGasto;
	}
	
}
