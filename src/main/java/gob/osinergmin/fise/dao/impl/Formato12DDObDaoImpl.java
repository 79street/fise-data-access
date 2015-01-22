package gob.osinergmin.fise.dao.impl;

import gob.osinergmin.base.dao.impl.GenericDaoImpl;
import gob.osinergmin.fise.dao.Formato12DDObDao;
import gob.osinergmin.fise.domain.FiseFormato12DD;
import gob.osinergmin.fise.domain.FiseFormato12DDOb;
import gob.osinergmin.fise.domain.FiseFormato12DDObPK;
import gob.osinergmin.fise.util.FormatoUtil;

import java.sql.SQLException;
import java.util.List;

import javax.persistence.Query;

import org.springframework.stereotype.Repository;

@Repository(value = "formato12DDObDaoImpl")
public class Formato12DDObDaoImpl extends GenericDaoImpl implements Formato12DDObDao {
	
	@Override
	public void eliminarFormato12DDOb(FiseFormato12DDOb fiseFormato12DDOb){
		try{
			em.remove(em.contains(fiseFormato12DDOb) ? fiseFormato12DDOb : em.merge(fiseFormato12DDOb));
		}catch (Exception e) {
			e.printStackTrace();
		} finally {
			 em.close();
		 }
	}
	
	@SuppressWarnings("unchecked")
	@Override
	public List<FiseFormato12DDOb> listarFormato12DDObByFormato12DD(FiseFormato12DD formato12DD) {
		List<FiseFormato12DDOb> lista = null;
		try{
			String q = "SELECT t FROM FiseFormato12DDOb t WHERE 1=1 ";
			if(FormatoUtil.isNotBlank(formato12DD.getId().getCodEmpresa())){ 
				q = q  + " AND t.id.codEmpresa = :codEmpresa ";
			}
			if(formato12DD.getId().getAnoPresentacion()!=0){ 
				q = q  + " AND t.id.anoPresentacion = :anioPresentacion ";
			}
			if(formato12DD.getId().getMesPresentacion()!=0){ 
				q = q + " AND t.id.mesPresentacion = :mesPresentacion ";
			}
			if(FormatoUtil.isNotBlank(formato12DD.getId().getEtapa())){ 
				q = q + " AND t.id.etapa = :etapa ";
			}
			if(formato12DD.getId().getAnoEjecucionGasto()!=0){ 
				q = q  + " AND t.id.anoEjecucionGasto = :anioEjecucionGasto ";
			}
			if(formato12DD.getId().getMesEjecucionGasto()!=0){ 
				q = q + " AND t.id.mesEjecucionGasto = :mesEjecucionGasto ";
			}
			if(formato12DD.getId().getEtapaEjecucion()!=0){ 
				q = q + " AND t.id.etapaEjecucion = :etapaEjecucion ";
			}
			if(formato12DD.getId().getNumeroItemEtapa()!=0){ 
				q = q + " AND t.id.numeroItemEtapa = :numeroItemEtapa ";
			}
			Query query = em.createQuery(q); 
			if(FormatoUtil.isNotBlank(formato12DD.getId().getCodEmpresa())){ 
				query.setParameter("codEmpresa", formato12DD.getId().getCodEmpresa());
			}
			if(formato12DD.getId().getAnoPresentacion()!=0){
				query.setParameter("anioPresentacion", formato12DD.getId().getAnoPresentacion());
			}
			if(formato12DD.getId().getMesPresentacion()!=0){ 
				query.setParameter("mesPresentacion", formato12DD.getId().getMesPresentacion());
			}
			if(FormatoUtil.isNotBlank(formato12DD.getId().getEtapa())){ 
				query.setParameter("etapa", formato12DD.getId().getEtapa());
			}
			if(formato12DD.getId().getAnoEjecucionGasto()!=0){
				query.setParameter("anioEjecucionGasto", formato12DD.getId().getAnoEjecucionGasto());
			}
			if(formato12DD.getId().getMesEjecucionGasto()!=0){ 
				query.setParameter("mesEjecucionGasto", formato12DD.getId().getMesEjecucionGasto());
			}
			if(formato12DD.getId().getEtapaEjecucion()!=0){ 
				query.setParameter("etapaEjecucion", formato12DD.getId().getEtapaEjecucion());
			}
			if(formato12DD.getId().getNumeroItemEtapa()!=0){
				query.setParameter("numeroItemEtapa", formato12DD.getId().getNumeroItemEtapa());
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
	public long buscarMaximoItemObs12D(String codEmpresa,long anioPres,long mesPres,
			long anioEjec,long mesEjec,String etapa,long etapaEjec,long itemEtapa) throws SQLException{		
		long maxId = 1;		
		String q = "SELECT MAX(f.id.itemObservacion) FROM " + FiseFormato12DDOb.class.getName()
				+ " f WHERE f.id.codEmpresa=:empresa AND"
				+ " f.id.anoPresentacion=:anioPres AND f.id.mesPresentacion=:mesPres AND"
				+ " f.id.anoEjecucionGasto=: anioEjec  AND f.id.mesEjecucionGasto=:mesEjec AND"
				+ " f.id.etapa=:etapa AND  f.id.etapaEjecucion=:etapaEjec AND f.id.numeroItemEtapa=:itemEtapa";
		Query query = em.createQuery(q); 	
		query.setParameter("empresa", codEmpresa);
		query.setParameter("anioPres", anioPres);
		query.setParameter("mesPres", mesPres);
		query.setParameter("anioEjec", anioEjec);
		query.setParameter("mesEjec", mesEjec);
		query.setParameter("etapa", etapa);
		query.setParameter("etapaEjec", etapaEjec);
		query.setParameter("itemEtapa", itemEtapa);
		
		Long verifica = (Long)query.getSingleResult();
		if(verifica!=null){
			maxId = verifica +1;
		}
		return maxId;
	}
	
	@Override
	public void insertarFiseFormato12DObs(FiseFormato12DDOb fiseFormato12DDOb) 
			throws SQLException{
		em.persist(fiseFormato12DDOb);	 		
	}
	
	@Override
	public FiseFormato12DDOb obtenerFiseFormato12DDOb(FiseFormato12DDObPK id) 
			throws SQLException{
		return em.find(FiseFormato12DDOb.class, id);		
	}	
	

}
