package gob.osinergmin.fise.dao.impl;

import gob.osinergmin.base.dao.impl.GenericDaoImpl;
import gob.osinergmin.fise.dao.Formato14CDObDao;
import gob.osinergmin.fise.domain.FiseFormato14CD;
import gob.osinergmin.fise.domain.FiseFormato14CDOb;
import gob.osinergmin.fise.domain.FiseFormato14CDObPK;

import java.sql.SQLException;
import java.util.Collections;
import java.util.List;

import javax.persistence.Query;

import org.springframework.stereotype.Repository;

@Repository(value = "formato14CDObDaoImpl")
public class Formato14CDObDaoImpl extends GenericDaoImpl implements Formato14CDObDao {

	@SuppressWarnings("unchecked")
	@Override
	public List<FiseFormato14CDOb> listarFiseFormato14CDOb() {
		String jql = "SELECT o FROM "+ FiseFormato14CDOb.class.getName()+ " o";
		Query query = em.createQuery(jql); 
		List<FiseFormato14CDOb> lista = query.getResultList();
		if(lista==null){
			return Collections.EMPTY_LIST;
		}else{
			return lista;
		}
	}

	@Override
	public void insertarFiseFormato14CDOb(FiseFormato14CDOb fiseFormato14CDOb)
			throws SQLException {
		em.persist(fiseFormato14CDOb);
		
	}

	@Override
	public void actualizarFiseFormato14CDOb(FiseFormato14CDOb fiseFormato14CDOb)
			throws SQLException {
		em.merge(fiseFormato14CDOb);
		
	}

	@Override
	public void eliminarFiseFormato14CDOb(FiseFormato14CDOb fiseFormato14CDOb)
			throws SQLException {
		em.remove(fiseFormato14CDOb); 
		
	}

	@Override
	public FiseFormato14CD obtenerFiseFormato14CDOb(FiseFormato14CDObPK id)
			throws SQLException {		
		return em.find(FiseFormato14CD.class, id); 
	}

}
