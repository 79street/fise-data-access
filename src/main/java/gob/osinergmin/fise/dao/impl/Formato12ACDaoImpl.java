package gob.osinergmin.fise.dao.impl;

import gob.osinergmin.base.dao.impl.GenericDaoImpl;
import gob.osinergmin.fise.dao.Formato12ACDao;
import gob.osinergmin.fise.domain.FiseFormato12AC;
import gob.osinergmin.fise.domain.FiseFormato12ACPK;
import gob.osinergmin.fise.util.FormatoUtil;

import java.math.BigDecimal;
import java.sql.SQLException;
import java.util.Collections;
import java.util.List;

import javax.persistence.Query;

import org.springframework.stereotype.Repository;

@Repository(value = "formato12ACDaoImpl")
public class Formato12ACDaoImpl extends GenericDaoImpl implements Formato12ACDao {

	@SuppressWarnings("unchecked")
	//@Override
	//@Transactional
	public List<FiseFormato12AC> listarFormato12AC() {
		List<FiseFormato12AC> lst = null;
		try {
			StringBuffer jql = new StringBuffer();
			jql.append(" SELECT f FROM FiseFormato12AC f ");
			Query query = em.createQuery(jql.toString());
			System.out.println(query.toString());
			lst = query.getResultList();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			 em.close();
		 }
		return lst;
	}
	
	@Override
	public FiseFormato12AC obtenerFormato12ACByPK(FiseFormato12ACPK fiseFormato12ACPK){
		FiseFormato12AC formato = null;
		try{
			//em.getTransaction().begin();
			fiseFormato12ACPK.setCodEmpresa(FormatoUtil.rellenaDerecha(fiseFormato12ACPK.getCodEmpresa(), ' ', 4));
			formato = em.find(FiseFormato12AC.class, fiseFormato12ACPK);
			//em.getTransaction().commit();
			//return formato;
		}catch (Exception e) {
			e.printStackTrace();
		} finally {
			 em.close();
		 }
		return formato;
	}
	
	@Override
	public void registrarFormato12AC(FiseFormato12AC fiseFormato12AC){
		try{
			//em.getTransaction().begin();
			em.persist(fiseFormato12AC);
			//em.flush();
			//em.getTransaction().commit();
		}catch (Exception e) {
			e.printStackTrace();
		} finally {
			 em.close();
		 }
	}
	
	@Override
	public void modificarFormato12AC(FiseFormato12AC fiseFormato12AC){
		try{
			//em.getTransaction().begin();
			em.merge(fiseFormato12AC);
			//em.flush();
			//em.getTransaction().commit();
		}catch (Exception e) {
			e.printStackTrace();
		} finally {
			 em.close();
		 }
	}
	
	@Override
	public void eliminarFormato12AC(FiseFormato12AC fiseFormato12AC){
		try{
			//em.getTransaction().begin();
			em.remove(em.contains(fiseFormato12AC) ? fiseFormato12AC : em.merge(fiseFormato12AC));
			//em.remove(fiseFormato12AC);
			//em.flush();
			//em.getTransaction().commit();
		}catch (Exception e) {
			e.printStackTrace();
		} finally {
			 em.close();
		 }
	}
	
	@Override
	public boolean existeFormato12AC(FiseFormato12AC fiseFormato12AC){
		boolean existe = false;
		try{
			//em.getTransaction().begin();
			fiseFormato12AC.getId().setCodEmpresa(FormatoUtil.rellenaDerecha(fiseFormato12AC.getId().getCodEmpresa(), ' ', 4));
			FiseFormato12AC formato = em.find(FiseFormato12AC.class, fiseFormato12AC.getId());
			if( formato != null ){
		    	existe = true;
		    }
			//em.persist(fiseFormato12A);
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
	public List<FiseFormato12AC> buscarFormato12AC(String codEmpresa, long anioDesde, long mesDesde, long anioHasta, long mesHasta, String etapa){
		List<FiseFormato12AC> lista = null;
		try{
			String q = "SELECT t FROM FiseFormato12AC t WHERE 1=1 ";
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
			/*if(lista==null){
				return Collections.EMPTY_LIST;
			}else{
				 return lista;
			}*/
			
		}catch (Exception e) {
			e.printStackTrace();
		} finally {
			 em.close();
		 }
		return lista;
	}
	
	@Override
	public int obtenerSecuencia(){
		int i=0;
		try {
			StringBuffer jql = new StringBuffer();
			jql.append("SELECT FISE_GEN_PKG.FISE_CORR_ARCH_FUN FROM DUAL");
			Query query = em.createNativeQuery(jql.toString());
			BigDecimal secuencia = (BigDecimal)query.getSingleResult();
			i = secuencia.intValue();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			 em.close();
		 }
		return i;
	}
	
	
	@SuppressWarnings("unchecked")	
	@Override
	public List<FiseFormato12AC> buscarFormato12ACReenvio(String codEmpresa, long anioPres, 
			long mesPres, String etapa) throws SQLException{
		
		String q = "SELECT f FROM " + FiseFormato12AC.class.getName()
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
		List<FiseFormato12AC> lista= query.getResultList();
		 if(lista==null){
			 return Collections.EMPTY_LIST;
		 }else{
			 return lista;
		 }	
	}
	
}
