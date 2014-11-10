package gob.osinergmin.fise.dao.impl;

import gob.osinergmin.base.dao.impl.GenericDaoImpl;
import gob.osinergmin.fise.dao.CfgCampoDao;
import gob.osinergmin.fise.domain.CfgCampo;
import gob.osinergmin.fise.domain.CfgTabla;

import java.util.List;

import javax.persistence.Query;

import org.springframework.stereotype.Repository;

@Repository(value = "cfgCampoDaoImpl")
public class CfgCampoDaoImpl extends GenericDaoImpl implements CfgCampoDao {
	
	@SuppressWarnings("unchecked")
	@Override
	public List<CfgCampo> listarCamposByTabla(CfgTabla cfgTabla) {
		List<CfgCampo> lista = null;
		try{
			String q = "SELECT t FROM CfgCampo t WHERE 1=1 ";
			if(cfgTabla.getIdTabla()!=0){ 
				q = q  + " AND t.cfgTabla.idTabla = :idTabla ";
			}
			q = q  + "  ORDER BY t.nroCampo ";
			Query query = em.createQuery(q); 
			if(cfgTabla.getIdTabla()!=0){
				query.setParameter("idTabla", cfgTabla.getIdTabla());
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
