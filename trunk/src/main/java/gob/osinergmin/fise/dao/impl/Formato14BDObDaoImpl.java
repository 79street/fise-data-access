package gob.osinergmin.fise.dao.impl;

import gob.osinergmin.base.dao.impl.GenericDaoImpl;
import gob.osinergmin.fise.dao.Formato14BDObDao;
import gob.osinergmin.fise.domain.FiseFormato14BD;
import gob.osinergmin.fise.domain.FiseFormato14BDOb;
import gob.osinergmin.fise.domain.FiseFormato14BDObPK;
import gob.osinergmin.fise.util.FormatoUtil;

import java.sql.SQLException;
import java.util.List;

import javax.persistence.Query;

import org.springframework.stereotype.Repository;

@Repository(value = "formato14BDObDaoImpl")
public class Formato14BDObDaoImpl extends GenericDaoImpl implements Formato14BDObDao {
	
	@Override
	public void eliminarFormato14BDOb(FiseFormato14BDOb fiseFormato14BDOb){
		try{
			//em.getTransaction().begin();
			//verificar si la entidad pertenece a la transaccion o no
			em.remove(em.contains(fiseFormato14BDOb) ? fiseFormato14BDOb : em.merge(fiseFormato14BDOb));
			//em.remove(fiseFormato14BD);
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
	public List<FiseFormato14BDOb> listarFormato14BDObByFormato14BD(FiseFormato14BD formato14BD) {
		List<FiseFormato14BDOb> lista = null;
		try{
			String q = "SELECT t FROM FiseFormato14BDOb t WHERE 1=1 ";
			if(FormatoUtil.isNotBlank(formato14BD.getId().getCodEmpresa())){ 
				q = q  + " AND t.id.codEmpresa = :codEmpresa ";
			}
			if(formato14BD.getId().getAnoPresentacion()!=0){ 
				q = q  + " AND t.id.anoPresentacion = :anioPresentacion ";
			}
			if(formato14BD.getId().getMesPresentacion()!=0){ 
				q = q + " AND t.id.mesPresentacion = :mesPresentacion ";
			}
			if(formato14BD.getId().getAnoInicioVigencia()!=0){ 
				q = q + " AND t.id.anoInicioVigencia = :anoInicioVigencia ";
			}
			if(formato14BD.getId().getAnoFinVigencia()!=0){ 
				q = q + " AND t.id.anoFinVigencia = :anoFinVigencia ";
			}
			if(FormatoUtil.isNotBlank(formato14BD.getId().getEtapa())){ 
				q = q + " AND t.id.etapa = :etapa ";
			}
			if(formato14BD.getId().getIdZonaBenef()!=0){ 
				q = q + " AND t.id.idZonaBenef = :idZonaBenef ";
			}
			Query query = em.createQuery(q); 
			if(FormatoUtil.isNotBlank(formato14BD.getId().getCodEmpresa())){ 
				query.setParameter("codEmpresa", formato14BD.getId().getCodEmpresa());
			}
			if(formato14BD.getId().getAnoPresentacion()!=0){
				query.setParameter("anioPresentacion", formato14BD.getId().getAnoPresentacion());
			}
			if(formato14BD.getId().getMesPresentacion()!=0){ 
				query.setParameter("mesPresentacion", formato14BD.getId().getMesPresentacion());
			}
			if(formato14BD.getId().getAnoInicioVigencia()!=0){ 
				query.setParameter("anoInicioVigencia", formato14BD.getId().getAnoInicioVigencia());
			}
			if(formato14BD.getId().getAnoFinVigencia()!=0){ 
				query.setParameter("anoFinVigencia", formato14BD.getId().getAnoFinVigencia());
			}
			if(FormatoUtil.isNotBlank(formato14BD.getId().getEtapa())){ 
				query.setParameter("etapa", formato14BD.getId().getEtapa());
			}
			if(formato14BD.getId().getIdZonaBenef()!=0){
				query.setParameter("idZonaBenef", formato14BD.getId().getIdZonaBenef());
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
	public long buscarMaximoItemObs14B(String codEmpresa,long anioPres,long mesPres,
			long anioIniVig,long anioFinVig,String etapa,long idZona) throws SQLException{		
		long maxId = 1;		
		String q = "SELECT MAX(f.id.itemObservacion) FROM " + FiseFormato14BDOb.class.getName()
				+ " f WHERE f.id.codEmpresa= :empresa AND"
				+ " f.id.anoPresentacion= :anioPres AND f.id.mesPresentacion= :mesPres AND"
				+ " f.id.anoInicioVigencia= :anioIniVig  AND f.id.anoFinVigencia= :anioFinVig AND"
				+ " f.id.etapa= :etapa AND f.id.idZonaBenef= :zona";
		Query query = em.createQuery(q); 	
		query.setParameter("empresa", codEmpresa);
		query.setParameter("anioPres", anioPres);
		query.setParameter("mesPres", mesPres);
		query.setParameter("anioIniVig", anioIniVig);
		query.setParameter("anioFinVig", anioFinVig);
		query.setParameter("etapa", etapa);
		query.setParameter("zona", idZona);
		
		Long verifica = (Long)query.getSingleResult();
		if(verifica!=null){
			maxId = verifica +1;
		}
		return maxId;
	}
	
	@Override
	public void insertarFiseFormato14BObs(FiseFormato14BDOb fiseFormato14BDOb) 
			throws SQLException{
		em.persist(fiseFormato14BDOb);	 		
	}
	
	
	@Override
	public FiseFormato14BDOb obtenerFiseFormato14BDOb(FiseFormato14BDObPK id) 
			throws SQLException{
		return em.find(FiseFormato14BDOb.class, id);		
	}	
	
	

}
