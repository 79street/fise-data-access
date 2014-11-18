package gob.osinergmin.fise.dao.impl;

import gob.osinergmin.base.dao.impl.GenericDaoImpl;
import gob.osinergmin.fise.dao.Formato14CDDao;
import gob.osinergmin.fise.domain.FiseFormato14CD;
import gob.osinergmin.fise.domain.FiseFormato14CDPK;
import gob.osinergmin.fise.util.FormatoUtil;

import java.sql.SQLException;
import java.util.Collections;
import java.util.List;

import javax.persistence.Query;

import org.springframework.stereotype.Repository;

@Repository(value = "formato14CDDaoImpl")
public class Formato14CDDaoImpl extends GenericDaoImpl implements Formato14CDDao {
	
	@SuppressWarnings("unchecked")
	public List<FiseFormato14CD> listarFormato14CD() {
		List<FiseFormato14CD> lst = null;
		try {
			StringBuffer jql = new StringBuffer();
			jql.append(" SELECT f FROM FiseFormato14CD f ");
			Query query = em.createQuery(jql.toString());
			System.out.println(query.toString());
			lst = query.getResultList();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return lst;
	}

	@Override
	public void insertarFiseFormato14CD(FiseFormato14CD fiseFormato14CD)
			throws SQLException {
		em.persist(fiseFormato14CD);
		
	}

	@Override
	public void actualizarFiseFormato14CD(FiseFormato14CD fiseFormato14CD)
			throws SQLException {
		em.merge(fiseFormato14CD);
		
	}

	@Override
	public void eliminarFiseFormato14CD(FiseFormato14CD fiseFormato14CD)
			throws SQLException {
		em.remove(fiseFormato14CD); 
		
	}

	@Override
	public FiseFormato14CD obtenerFiseFormato14CD(FiseFormato14CDPK id)
			throws SQLException {		
		id.setCodEmpresa(FormatoUtil.rellenaDerecha(id.getCodEmpresa(), ' ', 4));
		return em.find(FiseFormato14CD.class, id);
	} 
	
	
	@SuppressWarnings("unchecked")	
	@Override
	public List<FiseFormato14CD> buscarFiseFormato14CD(String codEmpresa, long anioPresentaion, 
			long mesPresentacion, long anioInicioVige, long anioFinVige, String etapa) throws SQLException{
		
		String q = "SELECT f FROM " + FiseFormato14CD.class.getName()
				+ " f WHERE 1=1 ";		
		q = q.concat(" AND f.id.codEmpresa = :codEmpresa ");
		q = q.concat(" AND f.id.anoPresentacion =:anioPres ");	
		q = q.concat(" AND f.id.mesPresentacion =:mesPres ");
		q = q.concat(" AND f.id.anoInicioVigencia =:anioInicio ");	
		q = q.concat(" AND f.id.anoFinVigencia =:anioFin ");	
		q = q.concat(" AND f.id.etapa = :etapa ");
		
		Query query = em.createQuery(q); 		
		query.setParameter("codEmpresa", codEmpresa);
		query.setParameter("anioPres", anioPresentaion);
		query.setParameter("mesPres", mesPresentacion);
		query.setParameter("anioInicio", anioInicioVige);
		query.setParameter("anioFin", anioFinVige);
		query.setParameter("etapa", etapa);		
		List<FiseFormato14CD> lista= query.getResultList();
		 if(lista==null){
			 return Collections.EMPTY_LIST;
		 }else{
			 return lista;
		 }	
	}
	
	

}
