package gob.osinergmin.fise.dao.impl;

import gob.osinergmin.base.dao.impl.GenericDaoImpl;
import gob.osinergmin.fise.dao.FiseActividadesDao;
import gob.osinergmin.fise.domain.FiseDescripcionActividade;
import gob.osinergmin.fise.domain.FiseDescripcionActividadePK;

import java.sql.SQLException;
import java.util.Collections;
import java.util.List;

import javax.persistence.Query;

import org.springframework.stereotype.Repository;

@Repository(value = "fiseActividadesDaoImpl")
public class FiseActividadesDaoImpl extends GenericDaoImpl implements FiseActividadesDao {

	
	@Override
	public FiseDescripcionActividade obtenerFiseDescripcionActividadeByPK(FiseDescripcionActividadePK id)
			throws SQLException {		
		return em.find(FiseDescripcionActividade.class, id);
	}
		
	@SuppressWarnings("unchecked")
	@Override
	public List<FiseDescripcionActividade> listarDescripcionActividade(String formato) 
			throws SQLException{
		
		String q = "SELECT a FROM " + FiseDescripcionActividade.class.getName()
				+ " a WHERE a.id.formato = :formato  ";			
		Query query = em.createQuery(q); 
		query.setParameter("formato", formato);
		List<FiseDescripcionActividade> lista= query.getResultList();
		 if(lista==null){
			 return Collections.EMPTY_LIST;
		 }else{
			 return lista;
		 }	
	}	

}
