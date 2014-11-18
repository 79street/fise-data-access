package gob.osinergmin.fise.dao.impl;

import gob.osinergmin.base.dao.impl.GenericDaoImpl;
import gob.osinergmin.fise.dao.Formato14BCDao;
import gob.osinergmin.fise.domain.FiseFormato14BC;
import gob.osinergmin.fise.domain.FiseFormato14BCPK;
import gob.osinergmin.fise.util.FormatoUtil;

import java.util.List;

import javax.persistence.Query;

import org.springframework.stereotype.Repository;

@Repository(value = "formato14BCDaoImpl")
public class Formato14BCDaoImpl extends GenericDaoImpl implements Formato14BCDao {
	
	@Override
	public FiseFormato14BC obtenerFormato14BCByPK(FiseFormato14BCPK fiseFormato14BCPK){
		FiseFormato14BC formato = null;
		try{
			//em.getTransaction().begin();
			fiseFormato14BCPK.setCodEmpresa(FormatoUtil.rellenaDerecha(fiseFormato14BCPK.getCodEmpresa(), ' ', 4));
			formato = em.find(FiseFormato14BC.class, fiseFormato14BCPK);
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
	public List<FiseFormato14BC> buscarFormato14BC(String codEmpresa, long anioDesde, long mesDesde, long anioHasta, long mesHasta, String etapa){
		List<FiseFormato14BC> lista = null;
		try{
			String q = "SELECT t FROM FiseFormato14BC t WHERE 1=1 ";
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
	public void registrarFormato14BC(FiseFormato14BC fiseFormato14BC){
		try{
			//em.getTransaction().begin();
			em.persist(fiseFormato14BC);
			//em.flush();
			//em.getTransaction().commit();
		}catch (Exception e) {
			e.printStackTrace();
		} finally {
			 em.close();
		 }
	}
	
	@Override
	public void modificarFormato14BC(FiseFormato14BC fiseFormato14BC){
		try{
			//em.getTransaction().begin();
			em.merge(fiseFormato14BC);
			//em.flush();
			//em.getTransaction().commit();
		}catch (Exception e) {
			e.printStackTrace();
		} finally {
			 em.close();
		 }
	}
	
	@Override
	public void eliminarFormato14BC(FiseFormato14BC fiseFormato14BC){
		try{
			//em.getTransaction().begin();
			em.remove(em.contains(fiseFormato14BC) ? fiseFormato14BC : em.merge(fiseFormato14BC));
			//em.remove(fiseFormato14BC);
			//em.flush();
			//em.getTransaction().commit();
		}catch (Exception e) {
			e.printStackTrace();
		} finally {
			 em.close();
		 }
	}
	
	@Override
	public boolean existeFormato14BC(FiseFormato14BC fiseFormato14BC){
		boolean existe = false;
		try{
			//em.getTransaction().begin();
			FiseFormato14BC formato = em.find(FiseFormato14BC.class, fiseFormato14BC.getId());
			if( formato != null ){
		    	existe = true;
		    }
			//em.persist(fiseFormato14B);
			//em.getTransaction().commit();
		}catch (Exception e) {
			e.printStackTrace();
		} finally {
			 em.close();
		 }
		return existe;
	}

}
