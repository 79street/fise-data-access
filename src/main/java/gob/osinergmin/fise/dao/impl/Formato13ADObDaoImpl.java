package gob.osinergmin.fise.dao.impl;

import gob.osinergmin.base.dao.impl.GenericDaoImpl;
import gob.osinergmin.fise.dao.Formato13ADObDao;
import gob.osinergmin.fise.domain.FiseFormato13AD;
import gob.osinergmin.fise.domain.FiseFormato13ADOb;
import gob.osinergmin.fise.domain.FiseFormato13ADObPK;
import gob.osinergmin.fise.util.FormatoUtil;

import java.sql.SQLException;
import java.util.List;

import javax.persistence.Query;

import org.springframework.stereotype.Repository;

@Repository(value = "formato13ADObDaoImpl")
public class Formato13ADObDaoImpl extends GenericDaoImpl implements Formato13ADObDao {
	
	@Override
	public void eliminarFormato13ADOb(FiseFormato13ADOb fiseFormato13ADOb){
		try{
			//verificar si la entidad pertenece a la transaccion o no
			em.remove(em.contains(fiseFormato13ADOb) ? fiseFormato13ADOb : em.merge(fiseFormato13ADOb));
		}catch (Exception e) {
			e.printStackTrace();
		} finally {
			 em.close();
		 }
	}
	
	@SuppressWarnings("unchecked")
	@Override
	public List<FiseFormato13ADOb> listarFormato13ADObByFormato13AD(FiseFormato13AD formato13AD) {
		List<FiseFormato13ADOb> lista = null;
		try{
			String q = "SELECT t FROM FiseFormato13ADOb t WHERE 1=1 ";
			if(FormatoUtil.isNotBlank(formato13AD.getId().getCodEmpresa())){ 
				q = q  + " AND t.id.codEmpresa = :codEmpresa ";
			}
			if(formato13AD.getId().getAnoPresentacion()!=0){ 
				q = q  + " AND t.id.anoPresentacion = :anioPresentacion ";
			}
			if(formato13AD.getId().getMesPresentacion()!=0){ 
				q = q + " AND t.id.mesPresentacion = :mesPresentacion ";
			}
			if(FormatoUtil.isNotBlank(formato13AD.getId().getEtapa())){ 
				q = q + " AND t.id.etapa = :etapa ";
			}
			if(FormatoUtil.isNotBlank(formato13AD.getId().getCodUbigeo())){ 
				q = q + " AND t.id.codUbigeo = :codUbigeo ";
			}
			if(FormatoUtil.isNotBlank(formato13AD.getId().getCodSectorTipico())){ 
				q = q + " AND t.id.codSectorTipico = :codSectorTipico ";
			}
			if(formato13AD.getId().getIdZonaBenef()!=0){ 
				q = q + " AND t.id.idZonaBenef = :idZonaBenef ";
			}
			Query query = em.createQuery(q); 
			if(FormatoUtil.isNotBlank(formato13AD.getId().getCodEmpresa())){ 
				query.setParameter("codEmpresa", formato13AD.getId().getCodEmpresa());
			}
			if(formato13AD.getId().getAnoPresentacion()!=0){
				query.setParameter("anioPresentacion", formato13AD.getId().getAnoPresentacion());
			}
			if(formato13AD.getId().getMesPresentacion()!=0){ 
				query.setParameter("mesPresentacion", formato13AD.getId().getMesPresentacion());
			}
			if(FormatoUtil.isNotBlank(formato13AD.getId().getEtapa())){ 
				query.setParameter("etapa", formato13AD.getId().getEtapa());
			}
			if(FormatoUtil.isNotBlank(formato13AD.getId().getCodUbigeo())){ 
				query.setParameter("codUbigeo", formato13AD.getId().getCodUbigeo());
			}
			if(FormatoUtil.isNotBlank(formato13AD.getId().getCodSectorTipico())){ 
				query.setParameter("codSectorTipico", formato13AD.getId().getCodSectorTipico());
			}
			if(formato13AD.getId().getIdZonaBenef()!=0){
				query.setParameter("idZonaBenef", formato13AD.getId().getIdZonaBenef());
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
	public long buscarMaximoItemObs13A(String codEmpresa,long anioPres,long mesPres,
			String ubigeo,String sector,String etapa,long idZona) throws SQLException{		
		long maxId = 1;		
		String q = "SELECT MAX(f.id.itemObservacion) FROM " + FiseFormato13ADOb.class.getName()
				+ " f WHERE f.id.codEmpresa= :empresa AND"
				+ " f.id.anoPresentacion= :anioPres AND f.id.mesPresentacion= :mesPres AND"
				+ " f.id.codUbigeo= :ubigeo  AND f.id.codSectorTipico= :sector AND"
				+ " f.id.etapa= :etapa AND f.id.idZonaBenef= :zona";
		Query query = em.createQuery(q); 	
		query.setParameter("empresa", codEmpresa);
		query.setParameter("anioPres", anioPres);
		query.setParameter("mesPres", mesPres);
		query.setParameter("ubigeo", ubigeo);
		query.setParameter("sector", sector);
		query.setParameter("etapa", etapa);
		query.setParameter("zona", idZona);
		
		Long verifica = (Long)query.getSingleResult();
		if(verifica!=null){
			maxId = verifica +1;
		}
		return maxId;
	}
	
	@Override
	public void insertarFiseFormato13AObs(FiseFormato13ADOb fiseFormato13ADOb) 
			throws SQLException{
		em.persist(fiseFormato13ADOb);	 		
	}
	
	
	@Override
	public FiseFormato13ADOb obtenerFiseFormato13ADOb(FiseFormato13ADObPK id) 
			throws SQLException{
		return em.find(FiseFormato13ADOb.class, id);		
	}	
	

}
