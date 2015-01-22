package gob.osinergmin.fise.dao.impl;

import gob.osinergmin.base.dao.impl.GenericDaoImpl;
import gob.osinergmin.fise.dao.Formato12CDObDao;
import gob.osinergmin.fise.domain.FiseFormato12CD;
import gob.osinergmin.fise.domain.FiseFormato12CDOb;
import gob.osinergmin.fise.domain.FiseFormato12CDObPK;
import gob.osinergmin.fise.util.FormatoUtil;

import java.sql.SQLException;
import java.util.List;

import javax.persistence.Query;

import org.springframework.stereotype.Repository;

@Repository(value = "formato12CDObDaoImpl")
public class Formato12CDObDaoImpl extends GenericDaoImpl implements Formato12CDObDao {
	
	@Override
	public void eliminarFormato12CDOb(FiseFormato12CDOb fiseFormato12CDOb){
		try{
			em.remove(em.contains(fiseFormato12CDOb) ? fiseFormato12CDOb : em.merge(fiseFormato12CDOb));
		}catch (Exception e) {
			e.printStackTrace();
		} finally {
			 em.close();
		 }
	}
	
	@SuppressWarnings("unchecked")
	@Override
	public List<FiseFormato12CDOb> listarFormato12CDObByFormato12CD(FiseFormato12CD formato12CD) {
		List<FiseFormato12CDOb> lista = null;
		try{
			String q = "SELECT t FROM FiseFormato12CDOb t WHERE 1=1 ";
			if(FormatoUtil.isNotBlank(formato12CD.getId().getCodEmpresa())){ 
				q = q  + " AND t.id.codEmpresa = :codEmpresa ";
			}
			if(formato12CD.getId().getAnoPresentacion()!=0){ 
				q = q  + " AND t.id.anoPresentacion = :anioPresentacion ";
			}
			if(formato12CD.getId().getMesPresentacion()!=0){ 
				q = q + " AND t.id.mesPresentacion = :mesPresentacion ";
			}
			if(FormatoUtil.isNotBlank(formato12CD.getId().getEtapa())){ 
				q = q + " AND t.id.etapa = :etapa ";
			}
			if(formato12CD.getId().getAnoEjecucionGasto()!=0){ 
				q = q  + " AND t.id.anoEjecucionGasto = :anioEjecucionGasto ";
			}
			if(formato12CD.getId().getMesEjecucionGasto()!=0){ 
				q = q + " AND t.id.mesEjecucionGasto = :mesEjecucionGasto ";
			}
			if(formato12CD.getId().getEtapaEjecucion()!=0){ 
				q = q + " AND t.id.etapaEjecucion = :etapaEjecucion ";
			}
			if(formato12CD.getId().getNumeroItemEtapa()!=0){ 
				q = q + " AND t.id.numeroItemEtapa = :numeroItemEtapa ";
			}
			Query query = em.createQuery(q); 
			if(FormatoUtil.isNotBlank(formato12CD.getId().getCodEmpresa())){ 
				query.setParameter("codEmpresa", formato12CD.getId().getCodEmpresa());
			}
			if(formato12CD.getId().getAnoPresentacion()!=0){
				query.setParameter("anioPresentacion", formato12CD.getId().getAnoPresentacion());
			}
			if(formato12CD.getId().getMesPresentacion()!=0){ 
				query.setParameter("mesPresentacion", formato12CD.getId().getMesPresentacion());
			}
			if(FormatoUtil.isNotBlank(formato12CD.getId().getEtapa())){ 
				query.setParameter("etapa", formato12CD.getId().getEtapa());
			}
			if(formato12CD.getId().getAnoEjecucionGasto()!=0){
				query.setParameter("anioEjecucionGasto", formato12CD.getId().getAnoEjecucionGasto());
			}
			if(formato12CD.getId().getMesEjecucionGasto()!=0){ 
				query.setParameter("mesEjecucionGasto", formato12CD.getId().getMesEjecucionGasto());
			}
			if(formato12CD.getId().getEtapaEjecucion()!=0){ 
				query.setParameter("etapaEjecucion", formato12CD.getId().getEtapaEjecucion());
			}
			if(formato12CD.getId().getNumeroItemEtapa()!=0){
				query.setParameter("numeroItemEtapa", formato12CD.getId().getNumeroItemEtapa());
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
	public long buscarMaximoItemObs12C(String codEmpresa,long anioPres,long mesPres,
			long anioEjec,long mesEjec,String etapa,long etapaEjec,long itemEtapa) throws SQLException{		
		long maxId = 1;		
		String q = "SELECT MAX(f.id.itemObservacion) FROM " + FiseFormato12CDOb.class.getName()
				+ " f WHERE f.id.codEmpresa= :empresa AND"
				+ " f.id.anoPresentacion= :anioPres AND f.id.mesPresentacion= :mesPres AND"
				+ " f.id.anoEjecucionGasto= :anioEjec  AND f.id.mesEjecucionGasto= :mesEjec AND"
				+ " f.id.etapa= :etapa AND  f.id.etapaEjecucion= :etapaEjec AND f.id.numeroItemEtapa= :itemEtapa";
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
	public void insertarFiseFormato12CObs(FiseFormato12CDOb fiseFormato12CDOb) 
			throws SQLException{
		em.persist(fiseFormato12CDOb);	 		
	}
	
	
	@Override
	public FiseFormato12CDOb obtenerFiseFormato12CDOb(FiseFormato12CDObPK id) 
			throws SQLException{
		return em.find(FiseFormato12CDOb.class, id);		
	}	
	

}
