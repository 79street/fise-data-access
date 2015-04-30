package gob.osinergmin.fise.dao.impl;

import gob.osinergmin.base.dao.impl.GenericDaoImpl;
import gob.osinergmin.fise.dao.FiseTipGastoDao;
import gob.osinergmin.fise.domain.FiseTipGasto;
import gob.osinergmin.fise.util.FormatoUtil;

import java.sql.SQLException;
import java.util.Collections;
import java.util.List;

import javax.persistence.Query;

import org.springframework.stereotype.Repository;

@Repository(value = "fiseTipGastoDaoImpl")
public class FiseTipGastoDaoImpl extends GenericDaoImpl implements FiseTipGastoDao {

	@SuppressWarnings("unchecked")
	@Override	
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
		}
		return lst;
	}	
	
	
	@Override
	public void insertarFiseTipGasto(FiseTipGasto fiseTipGasto) 
			throws SQLException{
		em.persist(fiseTipGasto);
		
	}

	@Override
	public void actualizarFiseTipGasto(FiseTipGasto fiseTipGasto) 
			throws SQLException{
		em.merge(fiseTipGasto);		
	}

	@Override
	public void eliminarFiseTipGasto(FiseTipGasto fiseTipGasto) 
			throws SQLException{
		em.remove(fiseTipGasto); 		
	}
	
	@Override
	public FiseTipGasto obtenerFiseTipGastoByPK(String id) {
		return em.find(FiseTipGasto.class, id);		
	}
	
	@SuppressWarnings("unchecked")	
	@Override
	public List<FiseTipGasto> buscarFiseTipGasto(String id, String descripcion ) 
			throws SQLException{
		
		String q = "SELECT g FROM " + FiseTipGasto.class.getName()
				+ " g WHERE 1=1 ";
		if(FormatoUtil.isNotBlank(id)){ 
			q = q.concat(" AND g.idTipGasto = :id ");
		}			
		if(FormatoUtil.isNotBlank(descripcion)){ 
			q = q.concat(" AND g.descripcion LIKE :descripcion ");
		}		
		Query query = em.createQuery(q); 
		if(FormatoUtil.isNotBlank(id)){ 
			query.setParameter("id", id);
		}			
		if(FormatoUtil.isNotBlank(descripcion)){ 
			String des = "%"+descripcion+"%";
			query.setParameter("descripcion", des);
		}		
		List<FiseTipGasto> lista= query.getResultList();
		 if(lista==null){
			 return Collections.EMPTY_LIST;
		 }else{
			 return lista;
		 }	
	}
	
	
}
