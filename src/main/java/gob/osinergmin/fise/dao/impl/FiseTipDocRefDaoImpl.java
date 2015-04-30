package gob.osinergmin.fise.dao.impl;

import gob.osinergmin.base.dao.impl.GenericDaoImpl;
import gob.osinergmin.fise.dao.FiseTipDocRefDao;
import gob.osinergmin.fise.domain.FiseTipDocRef;
import gob.osinergmin.fise.util.FormatoUtil;

import java.sql.SQLException;
import java.util.Collections;
import java.util.List;

import javax.persistence.Query;

import org.springframework.stereotype.Repository;

@Repository(value = "fiseTipDocRefDaoImpl")
public class FiseTipDocRefDaoImpl extends GenericDaoImpl implements FiseTipDocRefDao {

	@SuppressWarnings("unchecked")
	@Override
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
		}
		return lst;
	}	
	
	
	@Override
	public void insertarFiseTipDocRef(FiseTipDocRef fiseTipDocRef) 
			throws SQLException{
		em.persist(fiseTipDocRef);
		
	}

	@Override
	public void actualizarFiseTipDocRef(FiseTipDocRef fiseTipDocRef) 
			throws SQLException{
		em.merge(fiseTipDocRef);		
	}

	@Override
	public void eliminarFiseTipDocRef(FiseTipDocRef fiseTipDocRef) 
			throws SQLException{
		em.remove(fiseTipDocRef); 		
	}
	
	@Override
	public FiseTipDocRef obtenerFiseTipDocRefByPK(String id) {
		return em.find(FiseTipDocRef.class, id);		
	}
	
	@SuppressWarnings("unchecked")	
	@Override
	public List<FiseTipDocRef> buscarFiseTipDocRef(String id, String descripcion ) 
			throws SQLException{
		
		String q = "SELECT d FROM " + FiseTipDocRef.class.getName()
				+ " d WHERE 1=1 ";
		if(FormatoUtil.isNotBlank(id)){ 
			q = q.concat(" AND d.idTipDocRef = :id ");
		}			
		if(FormatoUtil.isNotBlank(descripcion)){ 
			q = q.concat(" AND d.descripcion LIKE :descripcion ");
		}
		q = q.concat(" ORDER BY d.descripcion ASC");
		
		Query query = em.createQuery(q); 
		if(FormatoUtil.isNotBlank(id)){ 
			query.setParameter("id", id);
		}			
		if(FormatoUtil.isNotBlank(descripcion)){ 
			String des = "%"+descripcion+"%";
			query.setParameter("descripcion", des);
		}		
		List<FiseTipDocRef> lista= query.getResultList();
		 if(lista==null){
			 return Collections.EMPTY_LIST;
		 }else{
			 return lista;
		 }	
	}
	
	
	
	
	
	
	
	
	
	
	
}
