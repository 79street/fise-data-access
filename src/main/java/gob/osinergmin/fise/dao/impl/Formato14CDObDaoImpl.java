package gob.osinergmin.fise.dao.impl;

import gob.osinergmin.base.dao.impl.GenericDaoImpl;
import gob.osinergmin.fise.dao.Formato14CDObDao;
import gob.osinergmin.fise.domain.FiseFormato14CDOb;
import gob.osinergmin.fise.domain.FiseFormato14CDObPK;
import gob.osinergmin.fise.util.FormatoUtil;

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
	public FiseFormato14CDOb obtenerFiseFormato14CDOb(FiseFormato14CDObPK id)
			throws SQLException {		
		id.setCodEmpresa(FormatoUtil.rellenaDerecha(id.getCodEmpresa(), ' ', 4));
		return em.find(FiseFormato14CDOb.class, id); 
	}
	
	@SuppressWarnings("unchecked")	
	@Override
	public List<FiseFormato14CDOb> buscarFiseFormato14CDOb(String codEmpresa, long anioPresentaion, 
			long mesPresentacion, long anioInicioVige, long anioFinVige, 
			String etapa,long idZonaBenef,long idTipoPersonal) throws SQLException{
		
		String q = "SELECT f FROM " + FiseFormato14CDOb.class.getName()
				+ " f WHERE 1=1 ";		
		q = q.concat(" AND f.id.codEmpresa = :codEmpresa ");
		q = q.concat(" AND f.id.anoPresentacion =:anioPres ");	
		q = q.concat(" AND f.id.mesPresentacion =:mesPres ");
		q = q.concat(" AND f.id.anoInicioVigencia =:anioInicio ");	
		q = q.concat(" AND f.id.anoFinVigencia =:anioFin ");	
		q = q.concat(" AND f.id.etapa =:etapa ");
		q = q.concat(" AND f.id.idZonaBenef =:idZona ");
		q = q.concat(" AND f.id.idTipPersonal =:idTipoPer ");
		
		Query query = em.createQuery(q); 		
		query.setParameter("codEmpresa", codEmpresa);
		query.setParameter("anioPres", anioPresentaion);
		query.setParameter("mesPres", mesPresentacion);
		query.setParameter("anioInicio", anioInicioVige);
		query.setParameter("anioFin", anioFinVige);
		query.setParameter("etapa", etapa);
		query.setParameter("idZona", idZonaBenef);	
		query.setParameter("idTipoPer", idTipoPersonal);		
		List<FiseFormato14CDOb> lista= query.getResultList();
		 if(lista==null){
			 return Collections.EMPTY_LIST;
		 }else{
			 return lista;
		 }	
	}

}
