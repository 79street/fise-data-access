package gob.osinergmin.fise.dao.impl;

import gob.osinergmin.base.dao.impl.GenericDaoImpl;
import gob.osinergmin.fise.dao.FiseTipPersonalDao;
import gob.osinergmin.fise.domain.FiseTipPersonal;
import gob.osinergmin.fise.util.FormatoUtil;

import java.sql.SQLException;
import java.util.Collections;
import java.util.List;

import javax.persistence.Query;

import org.springframework.stereotype.Repository;

@Repository(value = "fiseTipPersonalDaoImpl")
public class FiseTipPersonalDaoImpl extends GenericDaoImpl implements FiseTipPersonalDao {

	
	@SuppressWarnings("unchecked")
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
		}
		return lst;
	}
	
	
	@Override
	public void insertarFiseTipPersonal(FiseTipPersonal fiseTipPersonal) 
			throws SQLException{
		em.persist(fiseTipPersonal);
		
	}

	@Override
	public void actualizarFiseTipPersonal(FiseTipPersonal fiseTipPersonal) 
			throws SQLException{
		em.merge(fiseTipPersonal);		
	}

	@Override
	public void eliminarFiseTipPersonal(FiseTipPersonal fiseTipPersonal) 
			throws SQLException{
		em.remove(fiseTipPersonal); 		
	}
	
	@Override
	public FiseTipPersonal obtenerFiseTipPersonalByPK(long id)
			throws SQLException {
		return em.find(FiseTipPersonal.class, id); 
	}	
	
	@SuppressWarnings("unchecked")	
	@Override
	public List<FiseTipPersonal> buscarFiseTipPersonal(String id, String descripcion ) 
			throws SQLException{
		
		String q = "SELECT t FROM " + FiseTipPersonal.class.getName()
				+ " t WHERE 1=1 ";
		if(FormatoUtil.isNotBlank(id)){ 
			q = q.concat(" AND t.idTipPersonal = :id ");
		}			
		if(FormatoUtil.isNotBlank(descripcion)){ 
			q = q.concat(" AND t.descripcion LIKE :descripcion ");
		}		
		Query query = em.createQuery(q); 
		if(FormatoUtil.isNotBlank(id)){ 
			query.setParameter("id", Long.valueOf(id));
		}			
		if(FormatoUtil.isNotBlank(descripcion)){ 
			String des = "%"+descripcion+"%";
			query.setParameter("descripcion", des);
		}		
		List<FiseTipPersonal> lista= query.getResultList();
		 if(lista==null){
			 return Collections.EMPTY_LIST;
		 }else{
			 return lista;
		 }	
	}	
	
	@Override
	public long buscarMaximoIdTipPersonal() throws SQLException{		
		long maxId = 1;		
		String q = "SELECT MAX(t.idTipPersonal) FROM " + FiseTipPersonal.class.getName()
				+ " t ";
		Query query = em.createQuery(q); 			
		Long verifica = (Long)query.getSingleResult();
		if(verifica!=null){
			maxId = verifica +1;
		}
		return maxId;
	}
	
	

}
