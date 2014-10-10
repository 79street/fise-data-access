package gob.osinergmin.fise.gart.dao.impl;

import gob.osinergmin.base.dao.impl.GenericDaoImpl;
import gob.osinergmin.fise.domain.FiseFormato12AC;
import gob.osinergmin.fise.domain.FiseFormato12ACPK;
import gob.osinergmin.fise.gart.dao.Formato12ACGartDao;
import gob.osinergmin.fise.util.FormatoUtil;

import java.util.List;

import javax.persistence.Query;

import org.springframework.stereotype.Repository;

@Repository(value = "formato12ACGartDaoImpl")
public class Formato12ACGartDaoImpl extends GenericDaoImpl implements Formato12ACGartDao {

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
		}
		return lst;
	}
	
	@Override
	public FiseFormato12AC obtenerFormato12ACByPK(FiseFormato12ACPK fiseFormato12ACPK){
		FiseFormato12AC formato = null;
		try{
			//em.getTransaction().begin();
			formato = em.find(FiseFormato12AC.class, fiseFormato12ACPK);
			//em.getTransaction().commit();
			//return formato;
		}catch (Exception e) {
			e.printStackTrace();
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
		}
	}
	
	@Override
	public boolean existeFormato12AC(FiseFormato12AC fiseFormato12AC){
		boolean existe = false;
		try{
			//em.getTransaction().begin();
			FiseFormato12AC formato = em.find(FiseFormato12AC.class, fiseFormato12AC.getId());
			if( formato != null ){
		    	existe = true;
		    }
			//em.persist(fiseFormato12A);
			//em.getTransaction().commit();
		}catch (Exception e) {
			e.printStackTrace();
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
		}
		return lista;
	}
	
}
