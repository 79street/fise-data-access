package gob.osinergmin.fise.dao.impl;

import gob.osinergmin.base.dao.impl.GenericDaoImpl;
import gob.osinergmin.fise.dao.FiseGrupoInformacionDao;
import gob.osinergmin.fise.domain.FiseGrupoInformacion;

import java.sql.SQLException;
import java.util.Collections;
import java.util.List;

import javax.persistence.Query;

import org.springframework.stereotype.Repository;

@Repository(value = "fiseGrupoInformacionDaoImpl")
public class FiseGrupoInformacionDaoImpl extends GenericDaoImpl implements FiseGrupoInformacionDao {

	@Override
	public FiseGrupoInformacion obtenerFiseGrupoInformacionByPK(long id)
			throws SQLException {		
		return em.find(FiseGrupoInformacion.class, id);
	}
	
	@SuppressWarnings("unchecked")
	@Override
	public List<FiseGrupoInformacion> listarGrupoInformacion(String tipo) throws SQLException{
		
		String q = "SELECT g FROM " + FiseGrupoInformacion.class.getName()
				+ " g WHERE g.tipo = :tipo AND g.estado = 1 ";			
		Query query = em.createQuery(q); 
		query.setParameter("tipo", tipo);
		List<FiseGrupoInformacion> lista= query.getResultList();
		 if(lista==null){
			 return Collections.EMPTY_LIST;
		 }else{
			 return lista;
		 }	
	}
	
	

}
