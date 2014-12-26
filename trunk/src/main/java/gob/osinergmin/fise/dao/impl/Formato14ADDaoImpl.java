package gob.osinergmin.fise.dao.impl;

import gob.osinergmin.base.dao.impl.GenericDaoImpl;
import gob.osinergmin.fise.constant.FiseConstants;
import gob.osinergmin.fise.dao.Formato14ADDao;
import gob.osinergmin.fise.domain.FiseFormato14AC;
import gob.osinergmin.fise.domain.FiseFormato14AD;
import gob.osinergmin.fise.domain.FiseFormato14ADPK;
import gob.osinergmin.fise.util.FormatoUtil;

import java.util.List;

import javax.persistence.Query;

import org.springframework.stereotype.Repository;

@Repository(value = "formato14ADDaoImpl")
public class Formato14ADDaoImpl extends GenericDaoImpl implements Formato14ADDao {
	
	@Override
	public FiseFormato14AD obtenerFormato14ADByPK(FiseFormato14ADPK fiseFormato14ADPK){
		FiseFormato14AD formato = null;
		try{
			//em.getTransaction().begin();
			fiseFormato14ADPK.setCodEmpresa(FormatoUtil.rellenaDerecha(fiseFormato14ADPK.getCodEmpresa(), ' ', 4));
			formato = em.find(FiseFormato14AD.class, fiseFormato14ADPK);
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
	public List<FiseFormato14AD> listarFormato14ADByFormato14AC(FiseFormato14AC formato14AC) {
		List<FiseFormato14AD> lista = null;
		try{
			String q = "SELECT t FROM FiseFormato14AD t WHERE 1=1 ";
			if(FormatoUtil.isNotBlank(formato14AC.getId().getCodEmpresa())){ 
				q = q  + " AND t.id.codEmpresa = :codEmpresa ";
			}
			if(formato14AC.getId().getAnoPresentacion()!=0){ 
				q = q  + " AND t.id.anoPresentacion = :anioPresentacion ";
			}
			if(formato14AC.getId().getMesPresentacion()!=0){ 
				q = q + " AND t.id.mesPresentacion = :mesPresentacion ";
			}
			if(formato14AC.getId().getAnoInicioVigencia()!=0){ 
				q = q + " AND t.id.anoInicioVigencia = :anioInicio ";
			}
			if(formato14AC.getId().getAnoFinVigencia()!=0){ 
				q = q + " AND t.id.anoFinVigencia = :anioFin ";
			}
			if(FormatoUtil.isNotBlank(formato14AC.getId().getEtapa())){ 
				q = q + " AND t.id.etapa = :etapa ";
			}
			Query query = em.createQuery(q); 
			if(FormatoUtil.isNotBlank(formato14AC.getId().getCodEmpresa())){ 
				query.setParameter("codEmpresa", formato14AC.getId().getCodEmpresa());
			}
			if(formato14AC.getId().getAnoPresentacion()!=0){
				query.setParameter("anioPresentacion", formato14AC.getId().getAnoPresentacion());
			}
			if(formato14AC.getId().getMesPresentacion()!=0){ 
				query.setParameter("mesPresentacion", formato14AC.getId().getMesPresentacion());
			}
			if(formato14AC.getId().getAnoInicioVigencia()!=0){ 
				query.setParameter("anioInicio", formato14AC.getId().getAnoInicioVigencia());
			}
			if(formato14AC.getId().getAnoFinVigencia()!=0){ 
				query.setParameter("anioFin", formato14AC.getId().getAnoFinVigencia());
			}
			if(FormatoUtil.isNotBlank(formato14AC.getId().getEtapa())){ 
				query.setParameter("etapa", formato14AC.getId().getEtapa());
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
	public FiseFormato14AD obtenerFormato14ADVigente(String codEmpresa, long anioVigencia, long idZonaBenef) {
		List<FiseFormato14AD> lista = null;
		FiseFormato14AD objeto = null;
		try{
			String q = "SELECT t FROM FiseFormato14AD t WHERE 1=1 ";
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
				query.setParameter("codEmpresa", FormatoUtil.rellenaDerecha(codEmpresa, ' ', 4));
			}
			//if(anioVigencia!=0){ 
				query.setParameter("anioVigencia", anioVigencia);
			//}
			if(idZonaBenef!=0){ 
				query.setParameter("idZonaBenef", idZonaBenef);
			}
			query.setParameter("etapa", FiseConstants.ETAPA_ESTABLECIDO);
			
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
	public void registrarFormato14AD(FiseFormato14AD fiseFormato14AD){
		try{
			//em.getTransaction().begin();
			em.persist(fiseFormato14AD);
			//em.flush();
			//em.getTransaction().commit();
		}catch (Exception e) {
			e.printStackTrace();
		} finally {
			 em.close();
		 }
	}
	
	@Override
	public void modificarFormato14AD(FiseFormato14AD fiseFormato14AD){
		try{
			//em.getTransaction().begin();
			em.merge(fiseFormato14AD);
			//em.flush();
			//em.getTransaction().commit();
		}catch (Exception e) {
			e.printStackTrace();
		} finally {
			 em.close();
		 }
	}
	
	@Override
	public void eliminarFormato14AD(FiseFormato14AD fiseFormato14AD){
		try{
			//em.getTransaction().begin();
			//verificar si la entidad pertenece a la transaccion o no
			em.remove(em.contains(fiseFormato14AD) ? fiseFormato14AD : em.merge(fiseFormato14AD));
			//em.remove(fiseFormato14AD);
			//em.flush();
			//em.getTransaction().commit();
		}catch (Exception e) {
			e.printStackTrace();
		} finally {
			 em.close();
		 }
	}

}
