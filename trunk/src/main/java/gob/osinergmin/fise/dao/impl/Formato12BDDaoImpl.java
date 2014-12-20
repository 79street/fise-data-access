package gob.osinergmin.fise.dao.impl;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.Query;

import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import gob.osinergmin.base.dao.impl.GenericDaoImpl;
import gob.osinergmin.fise.dao.Formato12BDDao;
import gob.osinergmin.fise.domain.FiseFormato12BCPK;
import gob.osinergmin.fise.domain.FiseFormato12BD;


@Repository(value = "formato12BDDaoImpl")
public class Formato12BDDaoImpl extends GenericDaoImpl implements Formato12BDDao{

	

	@SuppressWarnings("unchecked")
	@Override
	public List<FiseFormato12BD> getLstFormatoDetalle(FiseFormato12BCPK idcabecera) {
		System.out.println("listando detalle formato");
		List<FiseFormato12BD> lstReturn=new ArrayList<FiseFormato12BD>();
		try {
			StringBuilder sb=new StringBuilder();
			sb.append("SELECT d FROM FiseFormato12BD d WHERE 1=1 ");
			
			if(idcabecera.getCodEmpresa() !=null && !idcabecera.getCodEmpresa().isEmpty()){
				sb.append(" AND d.id.codEmpresa ='"+idcabecera.getCodEmpresa().trim()+"'");
			}
			if(idcabecera.getEtapa()!=null && !idcabecera.getEtapa().isEmpty()){
				sb.append(" AND d.id.etapa =:etp ");
			}
			if(idcabecera.getAnoPresentacion()!=null && idcabecera.getAnoPresentacion()>0){
				sb.append(" AND d.id.anoPresentacion =:aniopres ");
			}
			if(idcabecera.getMesPresentacion()!=null && idcabecera.getMesPresentacion()>0){
				sb.append(" AND d.id.mesPresentacion =:mespres ");
			}
			if(idcabecera.getAnoEjecucionGasto()!=null && idcabecera.getAnoEjecucionGasto()>0){
				sb.append(" AND d.id.anoEjecucionGasto =:anioejec ");
			}
			if(idcabecera.getMesEjecucionGasto()!=null && idcabecera.getMesEjecucionGasto()>0){
				sb.append(" AND d.id.mesEjecucionGasto =:mesejec ");
			}
			
			
			Query query = em.createQuery(sb.toString());
			
			
			if(idcabecera.getEtapa()!=null && !idcabecera.getEtapa().isEmpty()){
				query.setParameter("etp", idcabecera.getEtapa().trim());
			}
			if(idcabecera.getAnoPresentacion()!=null && idcabecera.getAnoPresentacion()>0){
				query.setParameter("aniopres", idcabecera.getAnoPresentacion());
			}
			if(idcabecera.getMesPresentacion()!=null && idcabecera.getMesPresentacion()>0){
				query.setParameter("mespres", idcabecera.getMesPresentacion());
			}
			if(idcabecera.getAnoEjecucionGasto()!=null && idcabecera.getAnoEjecucionGasto()>0){
				query.setParameter("anioejec", idcabecera.getAnoEjecucionGasto());
			}
			if(idcabecera.getMesEjecucionGasto()!=null && idcabecera.getMesEjecucionGasto()>0){
				query.setParameter("mesejec", idcabecera.getMesEjecucionGasto());
			}
			
			lstReturn= query.getResultList();
			
			System.out.println("lstReturn::"+lstReturn.size());
		}catch(Exception e){
	      e.printStackTrace();
	     
		}finally{
			em.close();
			
		}
		return lstReturn;
	}

	@Override
	public FiseFormato12BD getFormatoDetalleById(FiseFormato12BCPK id, Integer idzona) {
		
		try {
			
			StringBuilder sb=new StringBuilder();
			sb.append("SELECT d FROM FiseFormato12BD d WHERE 1=1 ");
			
			if(id.getCodEmpresa() !=null && !id.getCodEmpresa().isEmpty()){
				sb.append(" AND d.id.codEmpresa =:emp ");
			}
			if(id.getEtapa()!=null && !id.getEtapa().isEmpty()){
				sb.append(" AND d.id.etapa =:etp ");
			}
			if(id.getAnoPresentacion()!=null && id.getAnoPresentacion()>0){
				sb.append(" AND d.id.anoPresentacion =:aniopres ");
			}
			if(id.getMesPresentacion()!=null && id.getMesPresentacion()>0){
				sb.append(" AND d.id.mesPresentacion =:mespres ");
			}
			if(id.getAnoEjecucionGasto()!=null && id.getAnoEjecucionGasto()>0){
				sb.append(" AND d.id.anoEjecucionGasto =:anioejec ");
			}
			if(id.getMesEjecucionGasto()!=null && id.getMesEjecucionGasto()>0){
				sb.append(" AND d.id.mesEjecucionGasto =:mesejec ");
			}
			
			
			Query query = em.createQuery(sb.toString());
			
			if(id.getCodEmpresa() !=null && !id.getCodEmpresa().isEmpty()){
				query.setParameter("emp", id.getCodEmpresa().trim());
			}
			if(id.getEtapa()!=null && !id.getEtapa().isEmpty()){
				query.setParameter("etp", id.getEtapa().trim());
			}
			if(id.getAnoPresentacion()!=null && id.getAnoPresentacion()>0){
				query.setParameter("aniopres", id.getAnoPresentacion());
			}
			if(id.getMesPresentacion()!=null && id.getMesPresentacion()>0){
				query.setParameter("mespres", id.getMesPresentacion());
			}
			if(id.getAnoEjecucionGasto()!=null && id.getAnoEjecucionGasto()>0){
				query.setParameter("anioejec", id.getAnoEjecucionGasto());
			}
			if(id.getMesEjecucionGasto()!=null && id.getMesEjecucionGasto()>0){
				query.setParameter("mesejec", id.getMesEjecucionGasto());
			}
			
			FiseFormato12BD bean= (FiseFormato12BD) query.getSingleResult();
			return bean;
			
			
		}catch(Exception e){
	      e.printStackTrace();
	     return null;
		}finally{
			em.close();
			
		}
		
	}

	@Override
	@Transactional
	public FiseFormato12BD saveFormatoDetalle(FiseFormato12BD formato) throws DataIntegrityViolationException, Exception {
		FiseFormato12BD result = null;
		try {
			em.persist(formato);
			System.out.println("cabecera persis::=>" + formato);
			result = formato;
		} finally {
			em.close();
		}
		return result;
	}

	@Override
	@Transactional
	public Integer updateFormatoDetalle(FiseFormato12BD formato) throws DataIntegrityViolationException, Exception {
		Integer result = null;
		try {
			em.merge(formato);
			System.out.println("cabecera merge::=>" + formato);
			result = 1;
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			em.close();
		}
		return result;
	}

	@Override
	@Transactional
	public Integer deleteFormatoDetalle(String emp,Integer anio,Integer mes,String etapa,Integer anioEjec,Integer mesEjec,Integer idzona)throws DataIntegrityViolationException, Exception {
		try {
			StringBuilder sb=new StringBuilder();
			sb.append("DELETE FROM FiseFormato12BD c WHERE 1=1 ");
			
			if(emp !=null && !emp.isEmpty()){
				sb.append(" AND c.id.codEmpresa =:emp ");
			}
			if(etapa!=null && !etapa.isEmpty()){
				sb.append(" AND c.id.etapa =:etp ");
			}
			if(anio!=null && anio>0){
				sb.append(" AND c.id.anoPresentacion =:aniopres ");
			}
			if(mes!=null && mes>0){
				sb.append(" AND c.id.mesPresentacion =:mespres ");
			}
			if(anioEjec!=null && anioEjec>0){
				sb.append(" AND c.id.anoEjecucionGasto =:anioejec ");
			}
			if(mesEjec!=null && mesEjec>0){
				sb.append(" AND c.id.mesEjecucionGasto =:mesejec ");
			}
			if(idzona!=null && idzona>0){
				sb.append(" AND c.id.idZonaBenef =:zona ");
			}
			
			
			Query query = em.createQuery(sb.toString());
			
			if(emp !=null && !emp.isEmpty()){
				query.setParameter("emp", emp.trim());
			}
			if(etapa!=null && !etapa.isEmpty()){
				query.setParameter("etp", etapa.trim());
			}
			if(anio!=null && anio>0){
				query.setParameter("aniopres", anio);
			}
			if(mes!=null && mes>0){
				query.setParameter("mespres", mes);
			}
			if(anioEjec!=null && anioEjec>0){
				query.setParameter("anioejec", anioEjec);
			}
			if(mesEjec!=null && mesEjec>0){
				query.setParameter("mesejec", mesEjec);
			}
			if(idzona!=null && idzona>0){
				query.setParameter("zona", idzona);
			}
			
			int cant=  query.executeUpdate();
			
			return cant;
		}finally{
			em.close();
			
		}
	}

}
