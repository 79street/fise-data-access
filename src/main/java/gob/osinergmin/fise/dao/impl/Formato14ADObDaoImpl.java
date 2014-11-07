package gob.osinergmin.fise.dao.impl;

import gob.osinergmin.base.dao.impl.GenericDaoImpl;
import gob.osinergmin.fise.dao.Formato14ADObDao;
import gob.osinergmin.fise.domain.FiseFormato14AD;
import gob.osinergmin.fise.domain.FiseFormato14ADOb;
import gob.osinergmin.fise.util.FormatoUtil;

import java.util.List;

import javax.persistence.Query;

import org.springframework.stereotype.Repository;

@Repository(value = "formato14ADObDaoImpl")
public class Formato14ADObDaoImpl extends GenericDaoImpl implements Formato14ADObDao {
	
	@Override
	public void eliminarFormato14ADOb(FiseFormato14ADOb fiseFormato14ADOb){
		try{
			//em.getTransaction().begin();
			//verificar si la entidad pertenece a la transaccion o no
			em.remove(em.contains(fiseFormato14ADOb) ? fiseFormato14ADOb : em.merge(fiseFormato14ADOb));
			//em.remove(fiseFormato14AD);
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
	public List<FiseFormato14ADOb> listarFormato14ADObByFormato14AD(FiseFormato14AD formato14AD) {
		List<FiseFormato14ADOb> lista = null;
		try{
			String q = "SELECT t FROM FiseFormato14ADOb t WHERE 1=1 ";
			if(FormatoUtil.isNotBlank(formato14AD.getId().getCodEmpresa())){ 
				q = q  + " AND t.id.codEmpresa = :codEmpresa ";
			}
			if(formato14AD.getId().getAnoPresentacion()!=0){ 
				q = q  + " AND t.id.anoPresentacion = :anioPresentacion ";
			}
			if(formato14AD.getId().getMesPresentacion()!=0){ 
				q = q + " AND t.id.mesPresentacion = :mesPresentacion ";
			}
			if(formato14AD.getId().getAnoInicioVigencia()!=0){ 
				q = q + " AND t.id.anoInicioVigencia = :anoInicioVigencia ";
			}
			if(formato14AD.getId().getAnoFinVigencia()!=0){ 
				q = q + " AND t.id.anoFinVigencia = :anoFinVigencia ";
			}
			if(FormatoUtil.isNotBlank(formato14AD.getId().getEtapa())){ 
				q = q + " AND t.id.etapa = :etapa ";
			}
			if(formato14AD.getId().getIdZonaBenef()!=0){ 
				q = q + " AND t.id.idZonaBenef = :idZonaBenef ";
			}
			Query query = em.createQuery(q); 
			if(FormatoUtil.isNotBlank(formato14AD.getId().getCodEmpresa())){ 
				query.setParameter("codEmpresa", formato14AD.getId().getCodEmpresa());
			}
			if(formato14AD.getId().getAnoPresentacion()!=0){
				query.setParameter("anioPresentacion", formato14AD.getId().getAnoPresentacion());
			}
			if(formato14AD.getId().getMesPresentacion()!=0){ 
				query.setParameter("mesPresentacion", formato14AD.getId().getMesPresentacion());
			}
			if(formato14AD.getId().getAnoInicioVigencia()!=0){ 
				query.setParameter("anoInicioVigencia", formato14AD.getId().getAnoInicioVigencia());
			}
			if(formato14AD.getId().getAnoFinVigencia()!=0){ 
				query.setParameter("anoFinVigencia", formato14AD.getId().getAnoFinVigencia());
			}
			if(FormatoUtil.isNotBlank(formato14AD.getId().getEtapa())){ 
				query.setParameter("etapa", formato14AD.getId().getEtapa());
			}
			if(formato14AD.getId().getIdZonaBenef()!=0){
				query.setParameter("idZonaBenef", formato14AD.getId().getIdZonaBenef());
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
