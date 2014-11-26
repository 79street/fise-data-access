package gob.osinergmin.fise.dao.impl;

import gob.osinergmin.base.dao.impl.GenericDaoImpl;
import gob.osinergmin.fise.dao.Formato14CCDao;
import gob.osinergmin.fise.domain.FiseFormato14CC;
import gob.osinergmin.fise.domain.FiseFormato14CCPK;
import gob.osinergmin.fise.util.FormatoUtil;

import java.sql.SQLException;
import java.util.Collections;
import java.util.List;

import javax.persistence.Query;

import org.springframework.stereotype.Repository;

@Repository(value = "formato14CCDaoImpl")
public class Formato14CCDaoImpl extends GenericDaoImpl implements Formato14CCDao {
	
	@SuppressWarnings("unchecked")
	public List<FiseFormato14CC> listarFormato14CC() {
		List<FiseFormato14CC> lst = null;
		try {
			StringBuffer jql = new StringBuffer();
			jql.append(" SELECT f FROM FiseFormato14CC f ");
			Query query = em.createQuery(jql.toString());
			System.out.println(query.toString());
			lst = query.getResultList();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return lst;
	}
	
	@Override
	public void insertarFiseFormato14CC(FiseFormato14CC fiseFormato14CC) 
			throws SQLException{
		em.persist(fiseFormato14CC);	 		
	}

	@Override
	public void actualizarFiseFormato14CC(FiseFormato14CC fiseFormato14CC) 
			throws SQLException{
		em.merge(fiseFormato14CC);		
	}

	@Override
	public void eliminarFiseFormato14CC(FiseFormato14CC fiseFormato14CC) 
			throws SQLException{
		em.remove(fiseFormato14CC); 		
	}
	
	@Override
	public FiseFormato14CC obtenerFormato14CC(FiseFormato14CCPK id) 
			throws SQLException{
		id.setCodEmpresa(FormatoUtil.rellenaDerecha(id.getCodEmpresa(), ' ', 4));
		return em.find(FiseFormato14CC.class, id);		
	}
	
	
	@SuppressWarnings("unchecked")	
	@Override
	public List<FiseFormato14CC> buscarFiseFormato14CC(String codEmpresa, long anioDesde, 
			long anioHasta, long mesDesde, long mesHasta, String etapa) throws SQLException{
		
		String q = "SELECT f FROM " + FiseFormato14CC.class.getName()
				+ " f WHERE 1=1 ";
		if(FormatoUtil.isNotBlank(codEmpresa)){ 
			q = q.concat(" AND f.id.codEmpresa = :codEmpresa ");
		}
		if(anioDesde!=0 && anioHasta!=0){ 		
			q = q.concat(" AND f.id.anoPresentacion BETWEEN :anioDesde AND :anioHasta ");	
		}
		if(mesDesde!=0 && mesHasta!=0 ){ 
			q = q.concat(" AND f.id.mesPresentacion BETWEEN :mesDesde AND :mesHasta ");				
		}		
		if(FormatoUtil.isNotBlank(etapa)){ 
			q = q.concat(" AND f.id.etapa = :etapa ");
		}
		Query query = em.createQuery(q); 
		if(FormatoUtil.isNotBlank(codEmpresa)){ 
			query.setParameter("codEmpresa", codEmpresa);
		}
		if(anioDesde!=0 && anioHasta!=0){
			query.setParameter("anioDesde", anioDesde);
			query.setParameter("anioHasta", anioHasta);
		}
		if(mesDesde!=0 && mesHasta!=0){ 
			query.setParameter("mesDesde", mesDesde);
			query.setParameter("mesHasta", mesHasta);
		}		
		if(FormatoUtil.isNotBlank(etapa)){ 
			query.setParameter("etapa", etapa);
		}
		List<FiseFormato14CC> lista= query.getResultList();
		 if(lista==null){
			 return Collections.EMPTY_LIST;
		 }else{
			 return lista;
		 }	
	}
	
	@SuppressWarnings("unchecked")	
	@Override
	public List<FiseFormato14CC> buscarFormato14CCReenvio(String codEmpresa, long anioPres, 
			long mesPres, String etapa) throws SQLException{
		
		String q = "SELECT f FROM " + FiseFormato14CC.class.getName()
				+ " f WHERE 1=1 ";
		if(FormatoUtil.isNotBlank(codEmpresa)){ 
			q = q.concat(" AND f.id.codEmpresa = :codEmpresa ");
		}
		if(anioPres!=0){ 		
			q = q.concat(" AND f.id.anoPresentacion =:anioPres ");	
		}
		if(mesPres!=0){ 
			q = q.concat(" AND f.id.mesPresentacion = :mesPres ");				
		}		
		if(FormatoUtil.isNotBlank(etapa)){ 
			q = q.concat(" AND f.id.etapa = :etapa ");
		}
		q = q.concat(" AND f.fechaEnvioDefinitivo IS NOT NULL ");
		Query query = em.createQuery(q); 
		if(FormatoUtil.isNotBlank(codEmpresa)){ 
			query.setParameter("codEmpresa", codEmpresa);
		}
		if(anioPres!=0){
			query.setParameter("anioPres", anioPres);			
		}
		if(mesPres!=0){ 
			query.setParameter("mesPres", mesPres);	
		}		
		if(FormatoUtil.isNotBlank(etapa)){ 
			query.setParameter("etapa", etapa);
		}
		List<FiseFormato14CC> lista= query.getResultList();
		 if(lista==null){
			 return Collections.EMPTY_LIST;
		 }else{
			 return lista;
		 }	
	}
	
	
	

}
