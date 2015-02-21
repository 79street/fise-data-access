package gob.osinergmin.fise.dao.impl;

import gob.osinergmin.base.dao.impl.GenericDaoImpl;
import gob.osinergmin.fise.dao.FiseParametroDao;
import gob.osinergmin.fise.domain.FiseParametro;
import gob.osinergmin.fise.util.FormatoUtil;

import java.sql.SQLException;
import java.util.Collections;
import java.util.List;

import javax.persistence.Query;

import org.springframework.stereotype.Repository;

@Repository(value = "fiseParametroDaoImpl")
public class FiseParametroDaoImpl extends GenericDaoImpl implements FiseParametroDao {

	@SuppressWarnings("unchecked")
	public List<FiseParametro> listarFiseParametro() {
		List<FiseParametro> lst = null;
		try {
			StringBuffer jql = new StringBuffer();
			jql.append(" SELECT f FROM FiseParametro f ");
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
	public void insertarFiseParametro(FiseParametro fiseParametro) throws SQLException{
		em.persist(fiseParametro);
	}

	@Override
	public void actualizarFiseParametro(FiseParametro fiseParametro) throws SQLException{
		em.merge(fiseParametro);		
	}

	@Override
	public void eliminarFiseParametro(FiseParametro fiseParametro) throws SQLException{
		em.remove(fiseParametro); 		
	}
	
	@Override
	public FiseParametro obtenerFiseParametro(String codigo)	throws SQLException{
		return em.find(FiseParametro.class, codigo);		
	}
	
	@SuppressWarnings("unchecked")	
	@Override
	public List<FiseParametro> buscarFiseParametro(String codigo, String nombre) throws SQLException{
		
		String q = "SELECT o FROM " + FiseParametro.class.getName()
				+ " o WHERE 1=1 ";
		if(FormatoUtil.isNotBlank(codigo)){ 
			q = q.concat(" AND o.codigo = :codigo ");
		}			
		if(FormatoUtil.isNotBlank(nombre)){ 
			q = q.concat(" AND o.nombre LIKE :nombre ");
		}		
		Query query = em.createQuery(q); 
		if(FormatoUtil.isNotBlank(codigo)){ 
			query.setParameter("codigo", codigo);
		}			
		if(FormatoUtil.isNotBlank(nombre)){ 
			String nom = "%"+nombre+"%";
			query.setParameter("nombre", nom);
		}		
		List<FiseParametro> lista= query.getResultList();
		 if(lista==null){
			 return Collections.EMPTY_LIST;
		 }else{
			 return lista;
		 }	
	}
	
	
}
