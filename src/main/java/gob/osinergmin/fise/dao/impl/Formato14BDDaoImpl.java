package gob.osinergmin.fise.dao.impl;

import gob.osinergmin.base.dao.impl.GenericDaoImpl;
import gob.osinergmin.fise.constant.FiseConstants;
import gob.osinergmin.fise.dao.Formato14BDDao;
import gob.osinergmin.fise.domain.FiseFormato14BC;
import gob.osinergmin.fise.domain.FiseFormato14BD;
import gob.osinergmin.fise.domain.FiseFormato14BDPK;
import gob.osinergmin.fise.util.FormatoUtil;

import java.util.List;

import javax.persistence.Query;

import org.springframework.stereotype.Repository;

@Repository(value = "formato14BDDaoImpl")
public class Formato14BDDaoImpl extends GenericDaoImpl implements Formato14BDDao {

	@Override
	public FiseFormato14BD obtenerFormato14BDByPK(FiseFormato14BDPK fiseFormato14BDPK){
		FiseFormato14BD formato = null;
		try{
			//em.getTransaction().begin();
			fiseFormato14BDPK.setCodEmpresa(FormatoUtil.rellenaDerecha(fiseFormato14BDPK.getCodEmpresa(), ' ', 4));
			formato = em.find(FiseFormato14BD.class, fiseFormato14BDPK);
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
	public List<FiseFormato14BD> listarFormato14BDByFormato14BC(FiseFormato14BC formato14BC) {
		List<FiseFormato14BD> lista = null;
		try{
			String q = "SELECT t FROM FiseFormato14BD t WHERE 1=1 ";
			if(FormatoUtil.isNotBlank(formato14BC.getId().getCodEmpresa())){ 
				q = q  + " AND t.id.codEmpresa = :codEmpresa ";
			}
			if(formato14BC.getId().getAnoPresentacion()!=0){ 
				q = q  + " AND t.id.anoPresentacion = :anioPresentacion ";
			}
			if(formato14BC.getId().getMesPresentacion()!=0){ 
				q = q + " AND t.id.mesPresentacion = :mesPresentacion ";
			}
			if(formato14BC.getId().getAnoInicioVigencia()!=0){ 
				q = q + " AND t.id.anoInicioVigencia = :anioInicio ";
			}
			if(formato14BC.getId().getAnoFinVigencia()!=0){ 
				q = q + " AND t.id.anoFinVigencia = :anioFin ";
			}
			if(FormatoUtil.isNotBlank(formato14BC.getId().getEtapa())){ 
				q = q + " AND t.id.etapa = :etapa ";
			}
			Query query = em.createQuery(q); 
			if(FormatoUtil.isNotBlank(formato14BC.getId().getCodEmpresa())){ 
				query.setParameter("codEmpresa", formato14BC.getId().getCodEmpresa());
			}
			if(formato14BC.getId().getAnoPresentacion()!=0){
				query.setParameter("anioPresentacion", formato14BC.getId().getAnoPresentacion());
			}
			if(formato14BC.getId().getMesPresentacion()!=0){ 
				query.setParameter("mesPresentacion", formato14BC.getId().getMesPresentacion());
			}
			if(formato14BC.getId().getAnoInicioVigencia()!=0){ 
				query.setParameter("anioInicio", formato14BC.getId().getAnoInicioVigencia());
			}
			if(formato14BC.getId().getAnoFinVigencia()!=0){ 
				query.setParameter("anioFin", formato14BC.getId().getAnoFinVigencia());
			}
			if(FormatoUtil.isNotBlank(formato14BC.getId().getEtapa())){ 
				query.setParameter("etapa", formato14BC.getId().getEtapa());
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
		} finally {
			 em.close();
		 }
		return lista;
	}
	
	@SuppressWarnings("unchecked")
	//@Override
	public FiseFormato14BD obtenerFormato14BDVigente(String codEmpresa, long anioVigencia, long idZonaBenef) {
		List<FiseFormato14BD> lista = null;
		FiseFormato14BD objeto = null;
		try{
			String q = "SELECT t FROM FiseFormato14BD t WHERE 1=1 ";
			if(FormatoUtil.isNotBlank(codEmpresa)){ 
				q = q  + " AND t.id.codEmpresa = :codEmpresa ";
			}
			//if(anioVigencia!=0){ 
				q = q + " AND t.id.anoInicioVigencia <= :anioVigencia ";
				q = q + " AND t.id.anoFinVigencia >= :anioVigencia ";
			//}
			if(idZonaBenef!=0){ 
				q = q + " AND t.id.idZonaBenef = :idZonaBenef ";
			}
			q = q + " AND t.id.etapa = :etapa ";
			Query query = em.createQuery(q); 
			if(FormatoUtil.isNotBlank(codEmpresa)){ 
				query.setParameter("codEmpresa", codEmpresa);
			}
			//if(anioVigencia!=0){ 
				query.setParameter("anioVigencia", anioVigencia);
			//}
			if(idZonaBenef!=0){ 
				query.setParameter("idZonaBenef", idZonaBenef);
			}
			query.setParameter("etapa", FiseConstants.ETAPA_RECONOCIDO);
			
			lista= query.getResultList();
			System.out.println("SQL   > " + query.toString());
			if(lista!=null && lista.size()>0){
				objeto = lista.get(0);
			}
			
		}catch (Exception e) {
			e.printStackTrace();
		} finally {
			 em.close();
		 }
		return objeto;
	}
	
	@Override
	public void registrarFormato14BD(FiseFormato14BD fiseFormato14BD){
		try{
			//em.getTransaction().begin();
			em.persist(fiseFormato14BD);
			//em.flush();
			//em.getTransaction().commit();
		}catch (Exception e) {
			e.printStackTrace();
		} finally {
			 em.close();
		 }
	}
	
	@Override
	public void modificarFormato14BD(FiseFormato14BD fiseFormato14BD){
		try{
			//em.getTransaction().begin();
			em.merge(fiseFormato14BD);
			//em.flush();
			//em.getTransaction().commit();
		}catch (Exception e) {
			e.printStackTrace();
		} finally {
			 em.close();
		 }
	}
	
	@Override
	public void eliminarFormato14BD(FiseFormato14BD fiseFormato14BD){
		try{
			//em.getTransaction().begin();
			//verificar si la entidad pertenece a la transaccion o no
			em.remove(em.contains(fiseFormato14BD) ? fiseFormato14BD : em.merge(fiseFormato14BD));
			//em.remove(fiseFormato14BD);
			//em.flush();
			//em.getTransaction().commit();
		}catch (Exception e) {
			e.printStackTrace();
		} finally {
			 em.close();
		 }
	}

	@Override
	public FiseFormato14BD getCostoUnitarioByEmpAnioZona(String codEmpresa, Integer anio, Integer mes,Integer idZona, String etp) {
		try {
			StringBuilder sb=new StringBuilder();
			sb.append("SELECT c FROM FiseFormato14BD c WHERE 1=1 ");
			
			if(codEmpresa !=null && !codEmpresa.isEmpty()){
				sb.append(" AND c.id.codEmpresa =:emp ");
			}
			if(anio!=null &&  anio>0){
				sb.append(" AND c.id.anoPresentacion =:aniopres ");
				
			}
			if(mes!=null &&  mes>0){
				sb.append(" AND c.id.mesPresentacion =:mespres ");
				
			}
			if(idZona!=null && idZona>0){
				sb.append(" AND c.id.idZonaBenef =:zna ");
			}
			
			if(etp !=null && !etp.isEmpty()){
				sb.append(" AND c.id.etapa =:etpa ");
				
			}
			
			Query query = em.createQuery(sb.toString());
			System.out.println("codEmpresa::"+codEmpresa);
			System.out.println("anio::"+anio);
			System.out.println("mes::"+mes);
			System.out.println("idZona::"+idZona);
			System.out.println("etp::"+etp);
			
			
			if(codEmpresa !=null && !codEmpresa.isEmpty()){
				query.setParameter("emp", codEmpresa.trim());
			}
			if(anio!=null &&  anio>0){
				query.setParameter("aniopres", anio.longValue());
			}
			if(mes!=null &&  mes>0){
				query.setParameter("mespres", mes.longValue());
				
			}
			if(idZona!=null && idZona>0){
				query.setParameter("zna", idZona.longValue());
			}
			if(etp !=null && !etp.isEmpty()){
				query.setParameter("etpa", etp.trim());
			}
			
			FiseFormato14BD bean= (FiseFormato14BD) query.getSingleResult();
			
			return bean;
		}catch(Exception e){
	      e.printStackTrace();
	      return null;
		}finally{
			em.close();
			
		}
	}

	@Override
	public List<FiseFormato14BD> getLstCostoUnitarioByEmpAnio(String codEmpresa, Integer anio,Integer mes, Integer idZona, String etp) {
		try {
			StringBuilder sb=new StringBuilder();
			sb.append("SELECT c FROM FiseFormato14BD c WHERE 1=1 ");
			
			if(codEmpresa !=null && !codEmpresa.isEmpty()){
				sb.append(" AND c.id.codEmpresa =:emp ");
			}
			if(anio!=null &&  anio>0){
				sb.append(" AND c.id.anoPresentacion =:aniopres ");
				
			}
			if(mes!=null &&  mes>0){
				sb.append(" AND c.id.mesPresentacion =:mespres ");
				
			}
			if(idZona!=null && idZona>0){
				sb.append(" AND c.id.idZonaBenef =:zna ");
			}
			
			if(etp !=null && !etp.isEmpty()){
				sb.append(" AND c.id.etapa =:etpa ");
				
			}
			
			Query query = em.createQuery(sb.toString());
			System.out.println("CONSULTA SQL::"+sb.toString());
			System.out.println("codEmpresa::"+codEmpresa);
			System.out.println("anio::"+anio);
			System.out.println("mes::"+mes);
			System.out.println("idZona::"+idZona);
			System.out.println("etp::"+etp);
			
			
			if(codEmpresa !=null && !codEmpresa.isEmpty()){
				query.setParameter("emp", codEmpresa.trim());
			}
			if(anio!=null &&  anio>0){
				query.setParameter("aniopres", anio.longValue());
			}
			if(mes!=null &&  mes>0){
				query.setParameter("mespres", mes.longValue());
			}
			if(idZona!=null && idZona>0){
				query.setParameter("zna", idZona.longValue());
			}
			if(etp !=null && !etp.isEmpty()){
				query.setParameter("etpa", etp.trim());
			}
			
			List<FiseFormato14BD> lst=  query.getResultList();
			
			return lst;
		}catch(Exception e){
	      e.printStackTrace();
	      return null;
		}finally{
			em.close();
			
		}
	}
	
}
