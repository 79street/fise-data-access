package gob.osinergmin.fise.dao.impl;

import gob.osinergmin.base.dao.impl.GenericDaoImpl;
import gob.osinergmin.fise.dao.FiseObservacionDao;
import gob.osinergmin.fise.domain.FiseObservacion;
import gob.osinergmin.fise.util.FormatoUtil;

import java.sql.SQLException;
import java.util.Collections;
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
		} finally {
			 em.close();
		 }
		return lst;
	}	
	
	@Override
	public void insertarFiseObservacion(FiseObservacion fiseObservacion) 
			throws SQLException{
		em.persist(fiseObservacion);
		
	}

	@Override
	public void actualizarFiseObservacion(FiseObservacion fiseObservacion) 
			throws SQLException{
		em.merge(fiseObservacion);		
	}

	@Override
	public void eliminarFiseObservacion(FiseObservacion fiseObservacion) 
			throws SQLException{
		em.remove(fiseObservacion); 		
	}
	
	@Override
	public FiseObservacion obtenerFiseObservacion(String id) 
			throws SQLException{
		return em.find(FiseObservacion.class, id);		
	}
	
	@SuppressWarnings("unchecked")	
	@Override
	public List<FiseObservacion> buscarFiseObservacion(String id, String descripcion ) 
			throws SQLException{
		
		String q = "SELECT o FROM " + FiseObservacion.class.getName()
				+ " o WHERE 1=1 ";
		if(FormatoUtil.isNotBlank(id)){ 
			q = q.concat(" AND o.idObservacion = :id ");
		}			
		if(FormatoUtil.isNotBlank(descripcion)){ 
			q = q.concat(" AND o.descripcion LIKE :descripcion ");
		}		
		Query query = em.createQuery(q); 
		if(FormatoUtil.isNotBlank(id)){ 
			query.setParameter("id", id);
		}			
		if(FormatoUtil.isNotBlank(descripcion)){ 
			String des = "%"+descripcion+"%";
			query.setParameter("descripcion", des);
		}		
		List<FiseObservacion> lista= query.getResultList();
		 if(lista==null){
			 return Collections.EMPTY_LIST;
		 }else{
			 return lista;
		 }	
	}
	
	
}
