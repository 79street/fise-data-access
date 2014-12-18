package gob.osinergmin.fise.dao.impl;

import gob.osinergmin.base.dao.impl.GenericDaoImpl;
import gob.osinergmin.fise.dao.Formato14ACDao;
import gob.osinergmin.fise.domain.FiseFormato12AC;
import gob.osinergmin.fise.domain.FiseFormato14AC;
import gob.osinergmin.fise.domain.FiseFormato14ACPK;
import gob.osinergmin.fise.util.FormatoUtil;

import java.sql.SQLException;
import java.util.Collections;
import java.util.List;

import javax.persistence.Query;

import org.springframework.stereotype.Repository;

@Repository(value = "formato14ACDaoImpl")
public class Formato14ACDaoImpl extends GenericDaoImpl implements Formato14ACDao {
	
	@Override
	public FiseFormato14AC obtenerFormato14ACByPK(FiseFormato14ACPK fiseFormato14ACPK){
		FiseFormato14AC formato = null;
		try{
			//em.getTransaction().begin();
			fiseFormato14ACPK.setCodEmpresa(FormatoUtil.rellenaDerecha(fiseFormato14ACPK.getCodEmpresa(), ' ', 4));
			formato = em.find(FiseFormato14AC.class, fiseFormato14ACPK);
			//em.getTransaction().commit();
			//return formato;
		}catch (Exception e) {
			e.printStackTrace();
		} finally {
			 em.close();
		 }
		return formato;
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<FiseFormato14AC> buscarFormato14AC(String codEmpresa, long anioDesde, long mesDesde, long anioHasta, long mesHasta, String etapa){
		List<FiseFormato14AC> lista = null;
		try{
			String q = "SELECT t FROM FiseFormato14AC t WHERE 1=1 ";
			if(FormatoUtil.isNotBlank(codEmpresa)){ 
				q = q  + " AND t.id.codEmpresa = :codEmpresa ";
			}
			if(anioDesde!=0){ 
				q = q  + " AND t.id.anoPresentacion >= :anioDesde ";
			}
			if(mesDesde!=0){ 
				q = q + " AND t.id.mesPresentacion >= :mesDesde ";
			}
			if(anioHasta!=0){ 
				q = q + " AND t.id.anoPresentacion <= :anioHasta ";
			}
			if(mesHasta!=0){ 
				q = q + " AND t.id.mesPresentacion <= :mesHasta ";
			}
			if(FormatoUtil.isNotBlank(etapa)){ 
				q = q + " AND t.id.etapa = :etapa ";
			}
			Query query = em.createQuery(q); 
			if(FormatoUtil.isNotBlank(codEmpresa)){ 
				query.setParameter("codEmpresa", codEmpresa);
			}
			if(anioDesde!=0){
				query.setParameter("anioDesde", anioDesde);
			}
			if(mesDesde!=0){ 
				query.setParameter("mesDesde", mesDesde);
			}
			if(anioHasta!=0){ 
				query.setParameter("anioHasta", anioHasta);
			}
			if(mesHasta!=0){ 
				query.setParameter("mesHasta", mesHasta);
			}
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
	public void registrarFormato14AC(FiseFormato14AC fiseFormato14AC){
		try{
			//em.getTransaction().begin();
			em.persist(fiseFormato14AC);
			//em.flush();
			//em.getTransaction().commit();
		}catch (Exception e) {
			e.printStackTrace();
		} finally {
			 em.close();
		 }
	}
	
	@Override
	public void modificarFormato14AC(FiseFormato14AC fiseFormato14AC){
		try{
			//em.getTransaction().begin();
			em.merge(fiseFormato14AC);
			//em.flush();
			//em.getTransaction().commit();
		}catch (Exception e) {
			e.printStackTrace();
		} finally {
			 em.close();
		 }
	}
	
	@Override
	public void eliminarFormato14AC(FiseFormato14AC fiseFormato14AC){
		try{
			//em.getTransaction().begin();
			em.remove(em.contains(fiseFormato14AC) ? fiseFormato14AC : em.merge(fiseFormato14AC));
			//em.remove(fiseFormato14AC);
			//em.flush();
			//em.getTransaction().commit();
		}catch (Exception e) {
			e.printStackTrace();
		} finally {
			 em.close();
		 }
	}
	
	@Override
	public boolean existeFormato14AC(FiseFormato14AC fiseFormato14AC){
		boolean existe = false;
		try{
			//em.getTransaction().begin();
			fiseFormato14AC.getId().setCodEmpresa(FormatoUtil.rellenaDerecha(fiseFormato14AC.getId().getCodEmpresa(), ' ', 4));
			FiseFormato14AC formato = em.find(FiseFormato14AC.class, fiseFormato14AC.getId());
			if( formato != null ){
		    	existe = true;
		    }
			//em.persist(fiseFormato14A);
			//em.getTransaction().commit();
		}catch (Exception e) {
			e.printStackTrace();
		} finally {
			 em.close();
		 }
		return existe;
	}
	
	
	@SuppressWarnings("unchecked")	
	@Override
	public List<FiseFormato14AC> buscarFormato14ACReenvio(String codEmpresa, long anioPres, 
			long mesPres, String etapa) throws SQLException{
		
		String q = "SELECT f FROM " + FiseFormato14AC.class.getName()
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
		List<FiseFormato14AC> lista= query.getResultList();
		 if(lista==null){
			 return Collections.EMPTY_LIST;
		 }else{
			 return lista;
		 }	
	}
	
	
	
}
