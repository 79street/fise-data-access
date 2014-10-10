package gob.osinergmin.fise.gart.dao.impl;

import gob.osinergmin.base.dao.impl.GenericDaoImpl;
import gob.osinergmin.fise.domain.FiseFormato12AC;
import gob.osinergmin.fise.domain.FiseFormato12AD;
import gob.osinergmin.fise.domain.FiseFormato12ADPK;
import gob.osinergmin.fise.gart.dao.Formato12ADGartDao;
import gob.osinergmin.fise.util.FormatoUtil;

import java.util.List;

import javax.persistence.Query;

import org.springframework.stereotype.Repository;

@Repository(value = "formato12ADGartDaoImpl")
public class Formato12ADGartDaoImpl extends GenericDaoImpl implements Formato12ADGartDao {
	
	@Override
	public FiseFormato12AD obtenerFormato12ADByPK(FiseFormato12ADPK fiseFormato12ADPK){
		FiseFormato12AD formato = null;
		try{
			//em.getTransaction().begin();
			formato = em.find(FiseFormato12AD.class, fiseFormato12ADPK);
			//em.getTransaction().commit();
			//return formato;
		}catch (Exception e) {
			e.printStackTrace();
		}
		return formato;
	}
	
	@Override
	public void registrarFormato12AD(FiseFormato12AD fiseFormato12AD){
		try{
			//em.getTransaction().begin();
			em.persist(fiseFormato12AD);
			//em.flush();
			//em.getTransaction().commit();
		}catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	@Override
	public void modificarFormato12AD(FiseFormato12AD fiseFormato12AD){
		try{
			//em.getTransaction().begin();
			em.merge(fiseFormato12AD);
			//em.flush();
			//em.getTransaction().commit();
		}catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	@Override
	public void eliminarFormato12AD(FiseFormato12AD fiseFormato12AD){
		try{
			//em.getTransaction().begin();
			//verificar si la entidad pertenece a la transaccion o no
			em.remove(em.contains(fiseFormato12AD) ? fiseFormato12AD : em.merge(fiseFormato12AD));
			//em.remove(fiseFormato12AD);
			//em.flush();
			//em.getTransaction().commit();
		}catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	@SuppressWarnings("unchecked")
	@Override
	public List<FiseFormato12AD> listarFormato12ADByFormato12AC(FiseFormato12AC formato12AC) {
		List<FiseFormato12AD> lista = null;
		try{
			String q = "SELECT t FROM FiseFormato12AD t WHERE 1=1 ";
			if(FormatoUtil.isNotBlank(formato12AC.getId().getCodEmpresa())){ 
				q = q  + " AND t.id.codEmpresa = :codEmpresa ";
			}
			if(formato12AC.getId().getAnoPresentacion()!=0){ 
				q = q  + " AND t.id.anoPresentacion = :anioPresentacion ";
			}
			if(formato12AC.getId().getMesPresentacion()!=0){ 
				q = q + " AND t.id.mesPresentacion = :mesPresentacion ";
			}
			if(formato12AC.getId().getAnoEjecucionGasto()!=0){ 
				q = q + " AND t.id.anoEjecucionGasto = :anioEjecucion ";
			}
			if(formato12AC.getId().getMesEjecucionGasto()!=0){ 
				q = q + " AND t.id.mesEjecucionGasto = :mesEjecucion ";
			}
			if(FormatoUtil.isNotBlank(formato12AC.getId().getEtapa())){ 
				q = q + " AND t.id.etapa = :etapa ";
			}
			Query query = em.createQuery(q); 
			if(FormatoUtil.isNotBlank(formato12AC.getId().getCodEmpresa())){ 
				query.setParameter("codEmpresa", formato12AC.getId().getCodEmpresa());
			}
			if(formato12AC.getId().getAnoPresentacion()!=0){
				query.setParameter("anioPresentacion", formato12AC.getId().getAnoPresentacion());
			}
			if(formato12AC.getId().getMesPresentacion()!=0){ 
				query.setParameter("mesPresentacion", formato12AC.getId().getMesPresentacion());
			}
			if(formato12AC.getId().getAnoEjecucionGasto()!=0){ 
				query.setParameter("anioEjecucion", formato12AC.getId().getAnoEjecucionGasto());
			}
			if(formato12AC.getId().getMesEjecucionGasto()!=0){ 
				query.setParameter("mesEjecucion", formato12AC.getId().getMesEjecucionGasto());
			}
			if(FormatoUtil.isNotBlank(formato12AC.getId().getEtapa())){ 
				query.setParameter("etapa", formato12AC.getId().getEtapa());
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
