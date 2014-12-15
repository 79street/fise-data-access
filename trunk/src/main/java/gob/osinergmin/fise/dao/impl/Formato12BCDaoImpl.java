package gob.osinergmin.fise.dao.impl;

import gob.osinergmin.base.dao.impl.GenericDaoImpl;
import gob.osinergmin.fise.dao.Formato12BCDao;
import gob.osinergmin.fise.domain.FiseFormato12BC;
import gob.osinergmin.fise.domain.FiseFormato12BCPK;
import gob.osinergmin.fise.util.FormatoUtil;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import javax.persistence.Query;

import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;


@Repository(value = "formato12BCDaoImpl")
public class Formato12BCDaoImpl extends GenericDaoImpl implements Formato12BCDao {

	
	@SuppressWarnings("unchecked")
	@Override
	public List<FiseFormato12BC> getLstFormatoCabecera(String codemp, Integer anioDesde, Integer mesDesde, Integer anioHasta, Integer mesHasta, String etapa) {
		List<FiseFormato12BC> lstReturn=new ArrayList<FiseFormato12BC>();
		try {
			StringBuilder sb=new StringBuilder();
			sb.append("SELECT c FROM FiseFormato12BC c WHERE 1=1 ");
			
			if(codemp!=null && !codemp.isEmpty()){
				sb.append(" AND c.id.codEmpresa =:emp ");
			}
			if(etapa!=null && !etapa.isEmpty()){
				sb.append(" AND c.id.etapa =:etp ");
			}
			if(anioDesde!=null && anioHasta!=null){
				sb.append(" AND c.id.anoPresentacion between :inianio AND :finanio ");
			}else if(anioDesde!=null && anioHasta==null){
				sb.append(" AND c.id.anoPresentacion >= :inianio ");
			}else if(anioDesde==null && anioHasta!=null){
				sb.append(" AND c.id.anoPresentacion <= :finanio ");
			}
			if(mesDesde!=null && mesHasta!=null){
				sb.append(" AND c.id.mesPresentacion between :inimes AND :finmes ");
			}else if(mesDesde!=null && mesHasta==null){
				sb.append(" AND c.id.mesPresentacion >= :inimes ");
			}else if(mesDesde==null && mesHasta!=null){
				sb.append(" AND c.id.mesPresentacion <= :finmes ");
			}
			
			Query query = em.createQuery(sb.toString());
			 if(codemp!=null && !codemp.isEmpty()){
            	query.setParameter("emp", codemp.trim());
			}
			if(etapa!=null && !etapa.isEmpty()){
				query.setParameter("etp", etapa.trim());
			}
			if(anioDesde!=null && anioHasta!=null){
				
				query.setParameter("inianio", anioDesde);
				query.setParameter("finanio", anioHasta);
			}else if(anioDesde!=null && anioHasta==null){
				query.setParameter("inianio", anioDesde);
			}else if(anioDesde==null && anioHasta!=null){
				query.setParameter("finanio", anioHasta);
			}
			
			if(mesDesde!=null && mesHasta!=null){
				query.setParameter("inimes", mesDesde);
				query.setParameter("finmes", mesHasta);
			}else if(mesDesde!=null && mesHasta==null){
				query.setParameter("inimes", mesDesde);
			}else if(mesDesde==null && mesHasta!=null){
				query.setParameter("finmes", mesHasta);
			}
			
			lstReturn= query.getResultList();
			
			System.out.println("ANIO SELECCION"+anioDesde);
			System.out.println("ANIO HASTA"+anioDesde);
			System.out.println("cant::"+lstReturn.size());
		}catch(Exception e){
	      e.printStackTrace();
	     
		}finally{
			em.close();
			
		}
		return lstReturn;
		
	}

	@Override
	public FiseFormato12BC getFormatoCabeceraById(FiseFormato12BCPK id) {		
		try {
			StringBuilder sb=new StringBuilder();
			System.out.println(id.getCodEmpresa().trim());
			System.out.println(id.getEtapa().trim());
			System.out.println(id.getAnoPresentacion());
			System.out.println(id.getMesPresentacion());
			System.out.println(id.getAnoEjecucionGasto());
			System.out.println(id.getMesEjecucionGasto());
			sb.append("SELECT c FROM FiseFormato12BC c WHERE 1=1 ");
			
			if(id.getCodEmpresa() !=null && !id.getCodEmpresa().isEmpty()){
				sb.append(" AND c.id.codEmpresa =:emp ");
			}
			if(id.getEtapa()!=null && !id.getEtapa().isEmpty()){
				sb.append(" AND c.id.etapa =:etp ");
			}
			if(id.getAnoPresentacion()!=null && id.getAnoPresentacion()>0){
				sb.append(" AND c.id.anoPresentacion =:aniopres ");
			}
			if(id.getMesPresentacion()!=null && id.getMesPresentacion()>0){
				sb.append(" AND c.id.mesPresentacion =:mespres ");
			}
			if(id.getAnoEjecucionGasto()!=null && id.getAnoEjecucionGasto()>0){
				sb.append(" AND c.id.anoEjecucionGasto =:anioejec ");
			}
			if(id.getMesEjecucionGasto()!=null && id.getMesEjecucionGasto()>0){
				sb.append(" AND c.id.mesEjecucionGasto =:mesejec ");
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
			FiseFormato12BC bean= (FiseFormato12BC) query.getSingleResult();			
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
	public FiseFormato12BC saveFormatoCabecera(FiseFormato12BC formato) throws DataIntegrityViolationException,Exception{
		FiseFormato12BC result = null;
		try {
				em.persist(formato);
			result = formato;
			System.out.println("cabecera persis::=>" + result);
		} finally {
			em.close();
		}
		return result;
	}

	@Override
	@Transactional
	public Integer updateFormatoCabecera(FiseFormato12BC formato)throws DataIntegrityViolationException,Exception {
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
	
	public Integer deleteFormatoCabecera(FiseFormato12BCPK id) throws DataIntegrityViolationException,Exception{

		try {
				StringBuilder sb=new StringBuilder();
			
			//sb.append("DELETE FROM FiseFormato12BC c WHERE 1=1 ");
			sb.append("DELETE FROM FISE_FORMATO_12B_C  WHERE 1=1 ");
			
			if(id.getCodEmpresa() !=null && !id.getCodEmpresa().isEmpty()){
				//sb.append(" AND c.id.codEmpresa =:emp ");
				sb.append(" AND COD_EMPRESA = ? ");
			}
			if(id.getEtapa()!=null && !id.getEtapa().isEmpty()){
				//sb.append(" AND c.id.etapa =:etp ");
				sb.append(" AND ETAPA = ? ");
			}
			if(id.getAnoPresentacion()!=null && id.getAnoPresentacion()>0){
				//sb.append(" AND c.id.anoPresentacion =:aniopres ");
				sb.append(" AND ANO_PRESENTACION = ? ");
			}
			if(id.getMesPresentacion()!=null && id.getMesPresentacion()>0){
				//sb.append(" AND c.id.mesPresentacion =:mespres ");
				sb.append(" AND MES_PRESENTACION = ? ");
			}
			if(id.getAnoEjecucionGasto()!=null && id.getAnoEjecucionGasto()>0){
				//sb.append(" AND c.id.anoEjecucionGasto =:anioejec ");
				sb.append(" AND ANO_EJECUCION_GASTO = ? ");
			}
			if(id.getMesEjecucionGasto()!=null && id.getMesEjecucionGasto()>0){
				//sb.append(" AND c.id.mesEjecucionGasto =:mesejec ");
				sb.append(" AND MES_EJECUCION_GASTO = ? ");
			}
			
			
			Query query = em.createNativeQuery(sb.toString());
			int param=1;
			if(id.getCodEmpresa() !=null && !id.getCodEmpresa().isEmpty()){
				//query.setParameter("emp", id.getCodEmpresa().trim());
				query.setParameter(param, id.getCodEmpresa().trim());
				param++;
			}
			if(id.getEtapa()!=null && !id.getEtapa().isEmpty()){
				//query.setParameter("etp", id.getEtapa().trim());
				query.setParameter(param, id.getEtapa().trim());
				param++;
			}
			if(id.getAnoPresentacion()!=null && id.getAnoPresentacion()>0){
				//query.setParameter("aniopres", id.getAnoPresentacion());
				query.setParameter(param, id.getAnoPresentacion());
				param++;
			}
			if(id.getMesPresentacion()!=null && id.getMesPresentacion()>0){
				//query.setParameter("mespres", id.getMesPresentacion());
				query.setParameter(param, id.getMesPresentacion());
				param++;
			}
			if(id.getAnoEjecucionGasto()!=null && id.getAnoEjecucionGasto()>0){
				//query.setParameter("anioejec", id.getAnoEjecucionGasto());
				query.setParameter(param, id.getAnoEjecucionGasto());
				param++;
			}
			if(id.getMesEjecucionGasto()!=null && id.getMesEjecucionGasto()>0){
				//query.setParameter("mesejec", id.getMesEjecucionGasto());
				query.setParameter(param, id.getMesEjecucionGasto());
				param++;
			}
			
          System.out.println("EMPRESA ELIMINANDO CABECERA::"+id.getCodEmpresa().trim());
          System.out.println("ANO ELIMINANDO CABECERA::"+id.getAnoPresentacion());
          System.out.println("MES ELIMINANDO CABECERA::"+id.getMesPresentacion());
          System.out.println("ANIO EJEC ELIMINANDO CABECERA::"+id.getAnoEjecucionGasto());
          System.out.println("MES EJEC ELIMINANDO CABECERA::"+id.getAnoEjecucionGasto());
          System.out.println("ETAPA ELIMINANDO CABECERA::"+id.getEtapa().trim());
			
			int cant=  query.executeUpdate();
			
			return cant;
		}finally{
			em.close();
			
		}
	}
	
	
	@SuppressWarnings("unchecked")	
	@Override
	public List<FiseFormato12BC> buscarFormato12BCReenvio(String codEmpresa, Integer anioPres, 
			Integer mesPres, String etapa) throws SQLException{
		
		String q = "SELECT f FROM " + FiseFormato12BC.class.getName()
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
			query.setParameter("codEmpresa", codEmpresa);
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
		List<FiseFormato12BC> lista= query.getResultList();
		 if(lista==null){
			 return Collections.EMPTY_LIST;
		 }else{
			 return lista;
		 }	
	}

	
}
