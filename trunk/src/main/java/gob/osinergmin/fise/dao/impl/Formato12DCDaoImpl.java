package gob.osinergmin.fise.dao.impl;

import gob.osinergmin.base.dao.impl.GenericDaoImpl;
import gob.osinergmin.fise.dao.Formato12DCDao;
import gob.osinergmin.fise.domain.FiseFormato12DC;
import gob.osinergmin.fise.domain.FiseFormato12DCPK;
import gob.osinergmin.fise.util.FormatoUtil;

import java.sql.SQLException;
import java.util.Collections;
import java.util.List;

import javax.persistence.Query;

import org.springframework.stereotype.Repository;

@Repository(value = "formato12DCDaoImpl")
public class Formato12DCDaoImpl extends GenericDaoImpl implements Formato12DCDao {
	
	@Override
	public FiseFormato12DC obtenerFormato12DCByPK(FiseFormato12DCPK fiseFormato12DCPK){
		FiseFormato12DC formato = null;
		try{
			fiseFormato12DCPK.setCodEmpresa(FormatoUtil.rellenaDerecha(fiseFormato12DCPK.getCodEmpresa(), ' ', 4));
			formato = em.find(FiseFormato12DC.class, fiseFormato12DCPK);
		}catch (Exception e) {
			e.printStackTrace();
		} finally {
			 em.close();
		 }
		return formato;
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<FiseFormato12DC> buscarFormato12DC(String codEmpresa, long anioDesde, long mesDesde, long anioHasta, long mesHasta, String etapa){
		List<FiseFormato12DC> lista = null;
		try{
			String q = "SELECT t FROM FiseFormato12DC t WHERE 1=1 ";
			if(FormatoUtil.isNotBlank(codEmpresa)){ 
				q = q  + " AND t.id.codEmpresa = :codEmpresa ";
			}
			q = q  + " AND t.id.anoPresentacion*100+t.id.mesPresentacion >= :fechaDesde ";
			q = q  + " AND t.id.anoPresentacion*100+t.id.mesPresentacion <= :fechaHasta ";
			if(FormatoUtil.isNotBlank(etapa)){ 
				q = q + " AND t.id.etapa = :etapa ";
			}
			Query query = em.createQuery(q); 
			if(FormatoUtil.isNotBlank(codEmpresa)){ 
				query.setParameter("codEmpresa", codEmpresa);
			}
			long fechaDesde=0;
			if(anioDesde!=0){
				fechaDesde=anioDesde*100;
			}
			if(mesDesde!=0){ 
				fechaDesde=fechaDesde+mesDesde;
			}
			long fechaHasta=0;
			if(anioHasta!=0){
				fechaHasta=anioHasta*100;
			}
			if(mesHasta!=0){ 
				fechaHasta=fechaHasta+mesHasta;
			}
			query.setParameter("fechaDesde", fechaDesde);
			query.setParameter("fechaHasta", fechaHasta);
			if(FormatoUtil.isNotBlank(etapa)){ 
				query.setParameter("etapa", etapa);
			}
			
			lista= query.getResultList();
			System.out.println("SQL   > " + query.toString());
			
		}catch (Exception e) {
			e.printStackTrace();
		} finally {
			 em.close();
		 }
		return lista;
	}
	
	@Override
	public void registrarFormato12DC(FiseFormato12DC fiseFormato12DC){
		try{
			em.persist(fiseFormato12DC);
		}catch (Exception e) {
			e.printStackTrace();
		} finally {
			 em.close();
		 }
	}
	
	@Override
	public void modificarFormato12DC(FiseFormato12DC fiseFormato12DC){
		try{
			em.merge(fiseFormato12DC);
		}catch (Exception e) {
			e.printStackTrace();
		} finally {
			 em.close();
		 }
	}
	
	@Override
	public void eliminarFormato12DC(FiseFormato12DC fiseFormato12DC){
		try{
			em.remove(em.contains(fiseFormato12DC) ? fiseFormato12DC : em.merge(fiseFormato12DC));
		}catch (Exception e) {
			e.printStackTrace();
		} finally {
			 em.close();
		 }
	}
	
	@Override
	public boolean existeFormato12DC(FiseFormato12DC fiseFormato12DC){
		boolean existe = false;
		try{
			fiseFormato12DC.getId().setCodEmpresa(FormatoUtil.rellenaDerecha(fiseFormato12DC.getId().getCodEmpresa(), ' ', 4));
			FiseFormato12DC formato = em.find(FiseFormato12DC.class, fiseFormato12DC.getId());
			if( formato != null ){
		    	existe = true;
		    }
		}catch (Exception e) {
			e.printStackTrace();
		} finally {
			 em.close();
		 }
		return existe;
	}
	
	@SuppressWarnings("unchecked")	
	@Override
	public List<FiseFormato12DC> buscarFormato12DCReenvio(String codEmpresa, long anioPres, 
			long mesPres, String etapa) throws SQLException{
		
		String q = "SELECT f FROM " + FiseFormato12DC.class.getName()
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
			String codEmpreCompleta = FormatoUtil.rellenaDerecha(codEmpresa, ' ', 4);
			query.setParameter("codEmpresa", codEmpreCompleta);
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
		List<FiseFormato12DC> lista= query.getResultList();
		 if(lista==null){
			 return Collections.EMPTY_LIST;
		 }else{
			 return lista;
		 }	
	}
	
}
