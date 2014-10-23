package gob.osinergmin.fise.dao.impl;

import gob.osinergmin.base.dao.impl.GenericDaoImpl;
import gob.osinergmin.fise.dao.Formato12ADObDao;
import gob.osinergmin.fise.domain.FiseFormato12AC;
import gob.osinergmin.fise.domain.FiseFormato12AD;
import gob.osinergmin.fise.domain.FiseFormato12ADOb;
import gob.osinergmin.fise.util.FormatoUtil;

import java.util.List;

import javax.persistence.Query;

import org.springframework.stereotype.Repository;

@Repository(value = "formato12ADObDaoImpl")
public class Formato12ADObDaoImpl extends GenericDaoImpl implements Formato12ADObDao {
	
	@Override
	public int validarFormato12A(FiseFormato12AC fiseformato12AC, String tipoFormato, String usuario, String terminal){
		int result = -1;
		try {
			 
			StringBuffer jql = new StringBuffer();
			jql.append("CALL FISE.FISE_GEN_PKG.FISE_VAL_FORM_PRC (?,?,?,?,?,?,?,?,?)");
			Query query = em.createNativeQuery(jql.toString());
			query.setParameter(1, tipoFormato);
			query.setParameter(2, fiseformato12AC.getId().getCodEmpresa());
			query.setParameter(3, fiseformato12AC.getId().getAnoPresentacion());
			query.setParameter(4, fiseformato12AC.getId().getMesPresentacion());
			query.setParameter(5, fiseformato12AC.getId().getAnoEjecucionGasto());
			query.setParameter(6, fiseformato12AC.getId().getMesEjecucionGasto());
			query.setParameter(7, fiseformato12AC.getId().getEtapa());
			query.setParameter(8, usuario);
			query.setParameter(9, terminal);
			/*int v = query.executeUpdate();
			if (v == 0) {
				return new MensajeTransaccion();
			}*/
			//podemos validar posteriormente la transaccion
			result = query.executeUpdate();
			
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			em.close();
		 }
		return result;
	}
	
	@Override
	public void eliminarFormato12ADOb(FiseFormato12ADOb fiseFormato12ADOb){
		try{
			//em.getTransaction().begin();
			//verificar si la entidad pertenece a la transaccion o no
			em.remove(em.contains(fiseFormato12ADOb) ? fiseFormato12ADOb : em.merge(fiseFormato12ADOb));
			//em.remove(fiseFormato12AD);
			//em.flush();
			//em.getTransaction().commit();
		}catch (Exception e) {
			e.printStackTrace();
		} finally {
			 em.close();
		 }
	}
	
	@SuppressWarnings("unchecked")
	@Override
	public List<FiseFormato12ADOb> listarFormato12ADObByFormato12AD(FiseFormato12AD formato12AD) {
		List<FiseFormato12ADOb> lista = null;
		try{
			String q = "SELECT t FROM FiseFormato12ADOb t WHERE 1=1 ";
			if(FormatoUtil.isNotBlank(formato12AD.getId().getCodEmpresa())){ 
				q = q  + " AND t.id.codEmpresa = :codEmpresa ";
			}
			if(formato12AD.getId().getAnoPresentacion()!=0){ 
				q = q  + " AND t.id.anoPresentacion = :anioPresentacion ";
			}
			if(formato12AD.getId().getMesPresentacion()!=0){ 
				q = q + " AND t.id.mesPresentacion = :mesPresentacion ";
			}
			if(formato12AD.getId().getAnoEjecucionGasto()!=0){ 
				q = q + " AND t.id.anoEjecucionGasto = :anioEjecucion ";
			}
			if(formato12AD.getId().getMesEjecucionGasto()!=0){ 
				q = q + " AND t.id.mesEjecucionGasto = :mesEjecucion ";
			}
			if(FormatoUtil.isNotBlank(formato12AD.getId().getEtapa())){ 
				q = q + " AND t.id.etapa = :etapa ";
			}
			if(formato12AD.getId().getIdZonaBenef()!=0){ 
				q = q + " AND t.id.idZonaBenef = :idZonaBenef ";
			}
			Query query = em.createQuery(q); 
			if(FormatoUtil.isNotBlank(formato12AD.getId().getCodEmpresa())){ 
				query.setParameter("codEmpresa", formato12AD.getId().getCodEmpresa());
			}
			if(formato12AD.getId().getAnoPresentacion()!=0){
				query.setParameter("anioPresentacion", formato12AD.getId().getAnoPresentacion());
			}
			if(formato12AD.getId().getMesPresentacion()!=0){ 
				query.setParameter("mesPresentacion", formato12AD.getId().getMesPresentacion());
			}
			if(formato12AD.getId().getAnoEjecucionGasto()!=0){ 
				query.setParameter("anioEjecucion", formato12AD.getId().getAnoEjecucionGasto());
			}
			if(formato12AD.getId().getMesEjecucionGasto()!=0){ 
				query.setParameter("mesEjecucion", formato12AD.getId().getMesEjecucionGasto());
			}
			if(FormatoUtil.isNotBlank(formato12AD.getId().getEtapa())){ 
				query.setParameter("etapa", formato12AD.getId().getEtapa());
			}
			if(formato12AD.getId().getIdZonaBenef()!=0){
				query.setParameter("idZonaBenef", formato12AD.getId().getIdZonaBenef());
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
	

}
