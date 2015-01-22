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
		q = q.concat(" AND f.id.etapa =:etapa ");
		if(anioInicioVige!=0){
			q = q.concat(" AND f.id.anoInicioVigencia =:anioInicio ");		
		}
		if(anioFinVige!=0){
			q = q.concat(" AND f.id.anoFinVigencia =:anioFin ");	
		}
		if(idZonaBenef!=0){
			q = q.concat(" AND f.id.idZonaBenef =:idZona ");
		}
		if(idTipoPersonal!=0){
			q = q.concat(" AND f.id.idTipPersonal =:idTipoPer ");
		}		
		Query query = em.createQuery(q); 		
		query.setParameter("codEmpresa", codEmpresa);
		query.setParameter("anioPres", anioPresentaion);
		query.setParameter("mesPres", mesPresentacion);
		query.setParameter("etapa", etapa);
		if(anioInicioVige!=0){
			query.setParameter("anioInicio", anioInicioVige);
		}
		if(anioFinVige!=0){
			query.setParameter("anioFin", anioFinVige);
		}
		if(idZonaBenef!=0){
			query.setParameter("idZona", idZonaBenef);	
		}
		if(idTipoPersonal!=0){
			query.setParameter("idTipoPer", idTipoPersonal);	
		}		
		List<FiseFormato14CDOb> lista= query.getResultList();
		 if(lista==null){
			 return Collections.EMPTY_LIST;
		 }else{
			 return lista;
		 }	
	}
	
	
	@Override
	public long buscarMaximoItemObs14C(String codEmpresa,long anioPres,long mesPres,
			long anioIniVig,long anioFinVig,String etapa,long idZona,long idPersonal) throws SQLException{		
		long maxId = 1;		
		String q = "SELECT MAX(f.id.itemObservacion) FROM " + FiseFormato14CDOb.class.getName()
				+ " f WHERE f.id.codEmpresa= :empresa AND"
				+ " f.id.anoPresentacion= :anioPres AND f.id.mesPresentacion= :mesPres AND"
				+ " f.id.anoInicioVigencia= :anioIniVig  AND f.id.anoFinVigencia= :anioFinVig AND"
				+ " f.id.etapa= :etapa AND f.id.idZonaBenef= :zona AND f.id.idTipPersonal= :personal";
		Query query = em.createQuery(q); 	
		query.setParameter("empresa", codEmpresa);
		query.setParameter("anioPres", anioPres);
		query.setParameter("mesPres", mesPres);
		query.setParameter("anioIniVig", anioIniVig);
		query.setParameter("anioFinVig", anioFinVig);
		query.setParameter("etapa", etapa);
		query.setParameter("zona", idZona);
		query.setParameter("personal", idPersonal);
		
		Long verifica = (Long)query.getSingleResult();
		if(verifica!=null){
			maxId = verifica +1;
		}
		return maxId;
	}
	

}
