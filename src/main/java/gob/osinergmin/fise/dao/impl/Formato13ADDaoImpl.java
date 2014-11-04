package gob.osinergmin.fise.dao.impl;

import gob.osinergmin.base.dao.impl.GenericDaoImpl;
import gob.osinergmin.fise.dao.Formato13ADDao;
import gob.osinergmin.fise.domain.FiseFormato13AC;
import gob.osinergmin.fise.domain.FiseFormato13AD;
import gob.osinergmin.fise.util.FormatoUtil;

import java.util.List;

import javax.persistence.Query;

import org.springframework.stereotype.Repository;

@Repository(value = "formato13ADDaoImpl")
public class Formato13ADDaoImpl extends GenericDaoImpl implements Formato13ADDao {
	
	
	public List<FiseFormato13AD> listarFormato13ADByFormato13AC(FiseFormato13AC formato13AC) {
		List<FiseFormato13AD> lista = null;
		try{
			String q = "SELECT t FROM FiseFormato13AD t WHERE 1=1 ";
			if(FormatoUtil.isNotBlank(formato13AC.getId().getCodEmpresa())){ 
				q = q  + " AND t.id.codEmpresa = :codEmpresa ";
			}
			if(formato13AC.getId().getAnoPresentacion()!=0){ 
				q = q  + " AND t.id.anoPresentacion = :anioPresentacion ";
			}
			if(formato13AC.getId().getMesPresentacion()!=0){ 
				q = q + " AND t.id.mesPresentacion = :mesPresentacion ";
			}
			if(FormatoUtil.isNotBlank(formato13AC.getId().getEtapa())){ 
				q = q + " AND t.id.etapa = :etapa ";
			}
			Query query = em.createQuery(q); 
			if(FormatoUtil.isNotBlank(formato13AC.getId().getCodEmpresa())){ 
				query.setParameter("codEmpresa", formato13AC.getId().getCodEmpresa());
			}
			if(formato13AC.getId().getAnoPresentacion()!=0){
				query.setParameter("anioPresentacion", formato13AC.getId().getAnoPresentacion());
			}
			if(formato13AC.getId().getMesPresentacion()!=0){ 
				query.setParameter("mesPresentacion", formato13AC.getId().getMesPresentacion());
			}
			if(FormatoUtil.isNotBlank(formato13AC.getId().getEtapa())){ 
				query.setParameter("etapa", formato13AC.getId().getEtapa());
			}
			
			lista= query.getResultList();
			System.out.println("SQL   > " + q);
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

}
