package gob.osinergmin.fise.dao.impl;

import gob.osinergmin.base.dao.impl.GenericDaoImpl;
import gob.osinergmin.fise.dao.Formato12DDDao;
import gob.osinergmin.fise.domain.FiseFormato12DC;
import gob.osinergmin.fise.domain.FiseFormato12DD;
import gob.osinergmin.fise.domain.FiseFormato12DDPK;
import gob.osinergmin.fise.util.FormatoUtil;

import java.util.List;

import javax.persistence.Query;

import org.springframework.stereotype.Repository;

@Repository(value = "formato12DDDaoImpl")
public class Formato12DDDaoImpl extends GenericDaoImpl implements Formato12DDDao {
	
	@Override
	public FiseFormato12DD obtenerFormato12DDByPK(FiseFormato12DDPK fiseFormato12DDPK){
		FiseFormato12DD formato = null;
		try{
			fiseFormato12DDPK.setCodEmpresa(FormatoUtil.rellenaDerecha(fiseFormato12DDPK.getCodEmpresa(), ' ', 4));
			formato = em.find(FiseFormato12DD.class, fiseFormato12DDPK);
		}catch (Exception e) {
			e.printStackTrace();
		} finally {
			 em.close();
		 }
		return formato;
	}
	
	@SuppressWarnings("unchecked")
	@Override
	public List<FiseFormato12DD> listarFormato12DDByFormato12DC(FiseFormato12DC formato12DC) {
		List<FiseFormato12DD> lista = null;
		try{
			String q = "SELECT t FROM FiseFormato12DD t WHERE 1=1 ";
			if(FormatoUtil.isNotBlank(formato12DC.getId().getCodEmpresa())){ 
				q = q  + " AND t.id.codEmpresa = :codEmpresa ";
			}
			if(formato12DC.getId().getAnoPresentacion()!=0){ 
				q = q  + " AND t.id.anoPresentacion = :anioPresentacion ";
			}
			if(formato12DC.getId().getMesPresentacion()!=0){ 
				q = q + " AND t.id.mesPresentacion = :mesPresentacion ";
			}
			if(FormatoUtil.isNotBlank(formato12DC.getId().getEtapa())){ 
				q = q + " AND t.id.etapa = :etapa ";
			}
			Query query = em.createQuery(q); 
			if(FormatoUtil.isNotBlank(formato12DC.getId().getCodEmpresa())){ 
				query.setParameter("codEmpresa", formato12DC.getId().getCodEmpresa());
			}
			if(formato12DC.getId().getAnoPresentacion()!=0){
				query.setParameter("anioPresentacion", formato12DC.getId().getAnoPresentacion());
			}
			if(formato12DC.getId().getMesPresentacion()!=0){ 
				query.setParameter("mesPresentacion", formato12DC.getId().getMesPresentacion());
			}
			if(FormatoUtil.isNotBlank(formato12DC.getId().getEtapa())){ 
				query.setParameter("etapa", formato12DC.getId().getEtapa());
			}
			
			q = q + " AND order by t.id.etapaEjecucion ";
			
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
	public void registrarFormato12DD(FiseFormato12DD fiseFormato12DD){
		try{
			em.persist(fiseFormato12DD);
		}catch (Exception e) {
			e.printStackTrace();
		} finally {
			 em.close();
		 }
	}
	
	@Override
	public void modificarFormato12DD(FiseFormato12DD fiseFormato12DD){
		try{
			em.merge(fiseFormato12DD);
		}catch (Exception e) {
			e.printStackTrace();
		} finally {
			 em.close();
		 }
	}
	
	@Override
	public void eliminarFormato12DD(FiseFormato12DD fiseFormato12DD){
		try{
			em.remove(em.contains(fiseFormato12DD) ? fiseFormato12DD : em.merge(fiseFormato12DD));
		}catch (Exception e) {
			e.printStackTrace();
		} finally {
			 em.close();
		 }
	}
	
	@Override
	public Long obtenerMaximoItemEtapa(FiseFormato12DDPK formato12DDPK) {
		Long valor = new Long(0);
		try{
			String q = "SELECT MAX(t.id.numeroItemEtapa) FROM FiseFormato12DD t WHERE 1=1 ";
			if(FormatoUtil.isNotBlank(formato12DDPK.getCodEmpresa())){ 
				q = q  + " AND t.id.codEmpresa = :codEmpresa ";
			}
			if(formato12DDPK.getAnoPresentacion()!=0){ 
				q = q  + " AND t.id.anoPresentacion = :anioPresentacion ";
			}
			if(formato12DDPK.getMesPresentacion()!=0){ 
				q = q + " AND t.id.mesPresentacion = :mesPresentacion ";
			}
			if(FormatoUtil.isNotBlank(formato12DDPK.getEtapa())){ 
				q = q + " AND t.id.etapa = :etapa ";
			}
			if(formato12DDPK.getAnoEjecucionGasto()!=0){ 
				q = q  + " AND t.id.anoEjecucionGasto = :anoEjecucionGasto ";
			}
			if(formato12DDPK.getMesEjecucionGasto()!=0){ 
				q = q + " AND t.id.mesEjecucionGasto = :mesEjecucionGasto ";
			}
			if(formato12DDPK.getEtapaEjecucion()!=0){ 
				q = q + " AND t.id.etapaEjecucion = :etapaEjecucion ";
			}
			Query query = em.createQuery(q); 
			if(FormatoUtil.isNotBlank(formato12DDPK.getCodEmpresa())){ 
				query.setParameter("codEmpresa", formato12DDPK.getCodEmpresa());
			}
			if(formato12DDPK.getAnoPresentacion()!=0){
				query.setParameter("anioPresentacion", formato12DDPK.getAnoPresentacion());
			}
			if(formato12DDPK.getMesPresentacion()!=0){ 
				query.setParameter("mesPresentacion", formato12DDPK.getMesPresentacion());
			}
			if(FormatoUtil.isNotBlank(formato12DDPK.getEtapa())){ 
				query.setParameter("etapa", formato12DDPK.getEtapa());
			}
			if(formato12DDPK.getAnoEjecucionGasto()!=0){
				query.setParameter("anoEjecucionGasto", formato12DDPK.getAnoEjecucionGasto());
			}
			if(formato12DDPK.getMesEjecucionGasto()!=0){ 
				query.setParameter("mesEjecucionGasto", formato12DDPK.getMesEjecucionGasto());
			}
			if(formato12DDPK.getEtapaEjecucion()!=0){
				query.setParameter("etapaEjecucion", formato12DDPK.getEtapaEjecucion());
			}
			
			System.out.println("SQL   > " + query.toString());
			if(query.getSingleResult()!=null){
				valor = (Long)query.getSingleResult();
				//BigDecimal codigo = (BigDecimal)query.getSingleResult();
				//valor = codigo.longValue();
			}	
			
			
		}catch (Exception e) {
			valor =new Long(0);
			e.printStackTrace();
		} finally {
			 em.close();
		 }
		return valor;
	}

}
