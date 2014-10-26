package gob.osinergmin.fise.dao.impl;

import gob.osinergmin.base.dao.impl.GenericDaoImpl;
import gob.osinergmin.fise.dao.Formato14CCDao;
import gob.osinergmin.fise.domain.FiseFormato14CC;
import gob.osinergmin.fise.domain.FiseFormato14CCPK;

import java.sql.SQLException;
import java.util.List;

import javax.persistence.Query;

import org.springframework.stereotype.Repository;

@Repository(value = "formato14CCDaoImpl")
public class Formato14CCDaoImpl extends GenericDaoImpl implements Formato14CCDao {
	
	@SuppressWarnings("unchecked")
	public List<FiseFormato14CC> listarFormato14CC() {
		List<FiseFormato14CC> lst = null;
		try {
			StringBuffer jql = new StringBuffer();
			jql.append(" SELECT f FROM FiseFormato14CC f ");
			Query query = em.createQuery(jql.toString());
			System.out.println(query.toString());
			lst = query.getResultList();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return lst;
	}
	
	@Override
	public void insertarFiseFormato14CC(FiseFormato14CC fiseFormato14CC) 
			throws SQLException{
		em.persist(fiseFormato14CC);	 		
	}

	@Override
	public void actualizarFiseFormato14CC(FiseFormato14CC fiseFormato14CC) 
			throws SQLException{
		em.merge(fiseFormato14CC);		
	}

	@Override
	public void eliminarFiseFormato14CC(FiseFormato14CC fiseFormato14CC) 
			throws SQLException{
		em.remove(fiseFormato14CC); 		
	}
	
	@Override
	public FiseFormato14CC obtenerFormato14CC(FiseFormato14CCPK id) 
			throws SQLException{
		return em.find(FiseFormato14CC.class, id);		
	}
	
	
	
	

}
