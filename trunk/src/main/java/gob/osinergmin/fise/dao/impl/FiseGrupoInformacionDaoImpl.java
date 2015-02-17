package gob.osinergmin.fise.dao.impl;

import gob.osinergmin.base.dao.impl.GenericDaoImpl;
import gob.osinergmin.fise.dao.FiseGrupoInformacionDao;
import gob.osinergmin.fise.domain.FiseGrupoInformacion;
import gob.osinergmin.fise.util.FormatoUtil;

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
	public List<FiseGrupoInformacion> listarGrupoInformacion(String tipo,String flag) throws SQLException{
		
		String q = "SELECT g FROM " + FiseGrupoInformacion.class.getName()
				+ " g WHERE g.tipo = :tipo  ";
		if(FormatoUtil.isBlank(flag)){ 
			System.out.println("Entro a estado grupo de informacion estado =1"); 
			q = q.concat(" AND g.estado = 1 ");	
		}
		q = q.concat(" ORDER BY g.anoPresentacion DESC, g.mesPresentacion DESC ");	
		Query query = em.createQuery(q); 
		query.setParameter("tipo", tipo); 
		List<FiseGrupoInformacion> lista= query.getResultList();
		 if(lista==null){
			 return Collections.EMPTY_LIST;
		 }else{
			 return lista;
		 }	
	}
	
	@Override
	public void insertarGrupoInformacion(FiseGrupoInformacion fiseGrupoInformacion) 
			throws SQLException{
		em.persist(fiseGrupoInformacion);
		
	}

	@Override
	public void actualizarGrupoInformacion(FiseGrupoInformacion fiseGrupoInformacion) 
			throws SQLException{
		em.merge(fiseGrupoInformacion);		
	}

	@Override
	public void eliminarGrupoInformacion(FiseGrupoInformacion fiseGrupoInformacion) 
			throws SQLException{
		em.remove(fiseGrupoInformacion); 		
	}	
	
	@SuppressWarnings("unchecked")	
	@Override
	public List<FiseGrupoInformacion> buscarGrupoInformacion(String descripcion,String tipo,Integer estado) 
			throws SQLException{
		
		String q = "SELECT g FROM " + FiseGrupoInformacion.class.getName()
				+ " g WHERE 1=1 ";
		if(FormatoUtil.isNotBlank(tipo)){ 
			q = q.concat(" AND g.tipo = :tipo ");
		}			
		if(FormatoUtil.isNotBlank(descripcion)){ 
			q = q.concat(" AND g.descripcion LIKE :descripcion ");
		}
		if(estado!=null){ 
			q = q.concat(" AND g.estado = :estado ");
		}
		Query query = em.createQuery(q); 
		if(FormatoUtil.isNotBlank(tipo)){ 
			query.setParameter("tipo", tipo);
		}			
		if(FormatoUtil.isNotBlank(descripcion)){ 
			String des = "%"+descripcion+"%";
			query.setParameter("descripcion", des);
		}
		if(estado!=null){ 
			query.setParameter("estado", estado);
		}
		List<FiseGrupoInformacion> lista= query.getResultList();
		 if(lista==null){
			 return Collections.EMPTY_LIST;
		 }else{
			 return lista;
		 }	
	}
	
	@Override
	public boolean verificarGrupoInfBienal(String tipo,Integer estado) throws SQLException{		
		String q = "SELECT COUNT(g.idGrupoInformacion) FROM " + FiseGrupoInformacion.class.getName()
				+ " g WHERE g.tipo = :tipo AND g.estado = :estado";		
		Query query = em.createQuery(q); 
		query.setParameter("tipo", tipo);
		query.setParameter("estado", estado);
		long countGrupoInf = (Long)query.getSingleResult();
		System.out.println("COUNT grupo de informacion bienal :  "+countGrupoInf); 
		if(countGrupoInf !=0){
			return false;
		}else{
			return true;
		}
	}

}
