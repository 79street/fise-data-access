package gob.osinergmin.fise.dao.impl;

import gob.osinergmin.base.dao.impl.GenericDaoImpl;
import gob.osinergmin.fise.dao.Formato12CDDao;
import gob.osinergmin.fise.domain.FiseFormato12CC;
import gob.osinergmin.fise.domain.FiseFormato12CD;
import gob.osinergmin.fise.domain.FiseFormato12CDPK;
import gob.osinergmin.fise.util.FormatoUtil;

import java.util.List;

import javax.persistence.Query;

import org.springframework.stereotype.Repository;

@Repository(value = "formato12CDDaoImpl")
public class Formato12CDDaoImpl extends GenericDaoImpl implements Formato12CDDao {
	
	@Override
	public FiseFormato12CD obtenerFormato12CDByPK(FiseFormato12CDPK fiseFormato12CDPK){
		FiseFormato12CD formato = null;
		try{
			fiseFormato12CDPK.setCodEmpresa(FormatoUtil.rellenaDerecha(fiseFormato12CDPK.getCodEmpresa(), ' ', 4));
			formato = em.find(FiseFormato12CD.class, fiseFormato12CDPK);
		}catch (Exception e) {
			e.printStackTrace();
		} finally {
			 em.close();
		 }
		return formato;
	}
	
	@SuppressWarnings("unchecked")
	@Override
	public List<FiseFormato12CD> listarFormato12CDByFormato12CC(FiseFormato12CC formato12CC) {
		List<FiseFormato12CD> lista = null;
		try{
			String q = "SELECT t FROM FiseFormato12CD t WHERE 1=1 ";
			if(FormatoUtil.isNotBlank(formato12CC.getId().getCodEmpresa())){ 
				q = q  + " AND t.id.codEmpresa = :codEmpresa ";
			}
			if(formato12CC.getId().getAnoPresentacion()!=0){ 
				q = q  + " AND t.id.anoPresentacion = :anioPresentacion ";
			}
			if(formato12CC.getId().getMesPresentacion()!=0){ 
				q = q + " AND t.id.mesPresentacion = :mesPresentacion ";
			}
			if(FormatoUtil.isNotBlank(formato12CC.getId().getEtapa())){ 
				q = q + " AND t.id.etapa = :etapa ";
			}
			Query query = em.createQuery(q); 
			if(FormatoUtil.isNotBlank(formato12CC.getId().getCodEmpresa())){ 
				query.setParameter("codEmpresa", formato12CC.getId().getCodEmpresa());
			}
			if(formato12CC.getId().getAnoPresentacion()!=0){
				query.setParameter("anioPresentacion", formato12CC.getId().getAnoPresentacion());
			}
			if(formato12CC.getId().getMesPresentacion()!=0){ 
				query.setParameter("mesPresentacion", formato12CC.getId().getMesPresentacion());
			}
			if(FormatoUtil.isNotBlank(formato12CC.getId().getEtapa())){ 
				query.setParameter("etapa", formato12CC.getId().getEtapa());
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
	public void registrarFormato12CD(FiseFormato12CD fiseFormato12CD){
		try{
			em.persist(fiseFormato12CD);
		}catch (Exception e) {
			e.printStackTrace();
		} finally {
			 em.close();
		 }
	}
	
	@Override
	public void modificarFormato12CD(FiseFormato12CD fiseFormato12CD){
		try{
			em.merge(fiseFormato12CD);
		}catch (Exception e) {
			e.printStackTrace();
		} finally {
			 em.close();
		 }
	}
	
	@Override
	public void eliminarFormato12CD(FiseFormato12CD fiseFormato12CD){
		try{
			em.remove(em.contains(fiseFormato12CD) ? fiseFormato12CD : em.merge(fiseFormato12CD));
		}catch (Exception e) {
			e.printStackTrace();
		} finally {
			 em.close();
		 }
	}
	
	@Override
	public Long obtenerMaximoItemEtapa(FiseFormato12CDPK formato12CDPK) {
		Long valor = new Long(0);
		try{
			String q = "SELECT MAX(t.id.numeroItemEtapa) FROM FiseFormato12CD t WHERE 1=1 ";
			if(FormatoUtil.isNotBlank(formato12CDPK.getCodEmpresa())){ 
				q = q  + " AND t.id.codEmpresa = :codEmpresa ";
			}
			if(formato12CDPK.getAnoPresentacion()!=0){ 
				q = q  + " AND t.id.anoPresentacion = :anioPresentacion ";
			}
			if(formato12CDPK.getMesPresentacion()!=0){ 
				q = q + " AND t.id.mesPresentacion = :mesPresentacion ";
			}
			if(FormatoUtil.isNotBlank(formato12CDPK.getEtapa())){ 
				q = q + " AND t.id.etapa = :etapa ";
			}
			if(formato12CDPK.getAnoEjecucionGasto()!=0){ 
				q = q  + " AND t.id.anoEjecucionGasto = :anoEjecucionGasto ";
			}
			if(formato12CDPK.getMesEjecucionGasto()!=0){ 
				q = q + " AND t.id.mesEjecucionGasto = :mesEjecucionGasto ";
			}
			if(formato12CDPK.getEtapaEjecucion()!=0){ 
				q = q + " AND t.id.etapaEjecucion = :etapaEjecucion ";
			}
			Query query = em.createQuery(q); 
			if(FormatoUtil.isNotBlank(formato12CDPK.getCodEmpresa())){ 
				query.setParameter("codEmpresa", formato12CDPK.getCodEmpresa());
			}
			if(formato12CDPK.getAnoPresentacion()!=0){
				query.setParameter("anioPresentacion", formato12CDPK.getAnoPresentacion());
			}
			if(formato12CDPK.getMesPresentacion()!=0){ 
				query.setParameter("mesPresentacion", formato12CDPK.getMesPresentacion());
			}
			if(FormatoUtil.isNotBlank(formato12CDPK.getEtapa())){ 
				query.setParameter("etapa", formato12CDPK.getEtapa());
			}
			if(formato12CDPK.getAnoEjecucionGasto()!=0){
				query.setParameter("anoEjecucionGasto", formato12CDPK.getAnoEjecucionGasto());
			}
			if(formato12CDPK.getMesEjecucionGasto()!=0){ 
				query.setParameter("mesEjecucionGasto", formato12CDPK.getMesEjecucionGasto());
			}
			if(formato12CDPK.getEtapaEjecucion()!=0){
				query.setParameter("etapaEjecucion", formato12CDPK.getEtapaEjecucion());
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
