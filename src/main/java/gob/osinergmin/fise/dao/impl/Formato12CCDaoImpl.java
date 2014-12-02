package gob.osinergmin.fise.dao.impl;

import gob.osinergmin.base.dao.impl.GenericDaoImpl;
import gob.osinergmin.fise.dao.Formato12CCDao;
import gob.osinergmin.fise.domain.FiseFormato12CC;
import gob.osinergmin.fise.domain.FiseFormato12CCPK;
import gob.osinergmin.fise.util.FormatoUtil;

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
	
}