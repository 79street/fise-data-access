package gob.osinergmin.fise.dao.impl;

import gob.osinergmin.base.dao.impl.GenericDaoImpl;
import gob.osinergmin.fise.dao.Formato12CCDao;
import gob.osinergmin.fise.domain.FiseFormato12CC;
import gob.osinergmin.fise.domain.FiseFormato12CCPK;
import gob.osinergmin.fise.util.FormatoUtil;

import java.sql.SQLException;
import java.util.Collections;
import java.util.List;

import javax.persistence.Query;

import org.springframework.stereotype.Repository;

@Repository(value = "formato12CCDaoImpl")
public class Formato12CCDaoImpl extends GenericDaoImpl implements Formato12CCDao {
	
	@Override
	public FiseFormato12CC obtenerFormato12CCByPK(FiseFormato12CCPK fiseFormato12CCPK){
		FiseFormato12CC formato = null;
		try{
			fiseFormato12CCPK.setCodEmpresa(FormatoUtil.rellenaDerecha(fiseFormato12CCPK.getCodEmpresa(), ' ', 4));
			formato = em.find(FiseFormato12CC.class, fiseFormato12CCPK);
		}catch (Exception e) {
			e.printStackTrace();
		} finally {
			 em.close();
		 }
		return formato;
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<FiseFormato12CC> buscarFormato12CC(String codEmpresa, long anioDesde, long mesDesde, long anioHasta, long mesHasta, String etapa){
		List<FiseFormato12CC> lista = null;
		try{
			String q = "SELECT t FROM FiseFormato12CC t WHERE 1=1 ";
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
	public void registrarFormato12CC(FiseFormato12CC fiseFormato12CC){
		try{
			em.persist(fiseFormato12CC);
		}catch (Exception e) {
			e.printStackTrace();
		} finally {
			 em.close();
		 }
	}
	
	@Override
	public void modificarFormato12CC(FiseFormato12CC fiseFormato12CC){
		try{
			em.merge(fiseFormato12CC);
		}catch (Exception e) {
			e.printStackTrace();
		} finally {
			 em.close();
		 }
	}
	
	@Override
	public void eliminarFormato12CC(FiseFormato12CC fiseFormato12CC){
		try{
			em.remove(em.contains(fiseFormato12CC) ? fiseFormato12CC : em.merge(fiseFormato12CC));
		}catch (Exception e) {
			e.printStackTrace();
		} finally {
			 em.close();
		 }
	}
	
	@Override
	public boolean existeFormato12CC(FiseFormato12CC fiseFormato12CC){
		boolean existe = false;
		try{
			fiseFormato12CC.getId().setCodEmpresa(FormatoUtil.rellenaDerecha(fiseFormato12CC.getId().getCodEmpresa(), ' ', 4));
			FiseFormato12CC formato = em.find(FiseFormato12CC.class, fiseFormato12CC.getId());
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
	public List<FiseFormato12CC> buscarFormato12CCReenvio(String codEmpresa, long anioPres, 
			long mesPres, String etapa) throws SQLException{
		
		String q = "SELECT f FROM " + FiseFormato12CC.class.getName()
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
		List<FiseFormato12CC> lista= query.getResultList();
		 if(lista==null){
			 return Collections.EMPTY_LIST;
		 }else{
			 return lista;
		 }	
	}
	
}
