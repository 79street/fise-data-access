package gob.osinergmin.fise.dao.impl;

import gob.osinergmin.base.dao.impl.GenericDaoImpl;
import gob.osinergmin.fise.dao.ArchivoSustentoDao;
import gob.osinergmin.fise.domain.FiseArchivosCab;
import gob.osinergmin.fise.domain.FiseArchivosDet;
import gob.osinergmin.fise.util.FormatoUtil;

import java.sql.SQLException;
import java.util.Collections;
import java.util.List;

import javax.persistence.Query;

import org.springframework.stereotype.Repository;

@Repository(value = "archivoSustentoDaoImpl")
public class ArchivoSustentoDaoImpl extends GenericDaoImpl implements ArchivoSustentoDao {

	
	@Override
	public int llenarDatosFiseArchivosSustento(String codEmpresa,long idGrupoInf,String etapa, 
			String usuario, String terminal) throws SQLException{
		int result = -1;
		try {		
			StringBuffer jql = new StringBuffer();
			jql.append("CALL FISE_GEN_PKG.FISE_PUEBLA_CAB_ARCH_PRC(?,?,?,?,?)");
			Query query = em.createNativeQuery(jql.toString());
			query.setParameter(1, codEmpresa);
			query.setParameter(2, idGrupoInf);	
			query.setParameter(3, etapa);	
			query.setParameter(4, usuario);
			query.setParameter(5, terminal);			
			result = query.executeUpdate();		
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			em.close();
		}
		return result;
	}
	
	
	
	@SuppressWarnings("unchecked")	
	@Override
	public List<FiseArchivosCab> buscarFiseArchivosCab(String codEmpresa,
			long idGrupoInf,String etapa) throws SQLException{
		
		String q = "SELECT a FROM " + FiseArchivosCab.class.getName()
				+ " a WHERE 1=1 ";
		if(FormatoUtil.isNotBlank(codEmpresa) && !"TODO".equals(codEmpresa)){  
			q = q.concat(" AND a.codEmpresa = :codEmpresa ");
		}
		if(idGrupoInf!=0){ 		
			q = q.concat(" AND a.fiseGrupoInformacion.idGrupoInformacion =:idGrupoInf ");	
		}
		if(FormatoUtil.isNotBlank(etapa)){  
			q = q.concat(" AND a.etapa = :etapa ");
		}
		Query query = em.createQuery(q); 
		if(FormatoUtil.isNotBlank(codEmpresa) && !"TODO".equals(codEmpresa)){ 
			String codEmpreCompleta = FormatoUtil.rellenaDerecha(codEmpresa, ' ', 4);
			query.setParameter("codEmpresa", codEmpreCompleta);
		}
		if(idGrupoInf!=0){
			query.setParameter("idGrupoInf", idGrupoInf);			
		}
		if(FormatoUtil.isNotBlank(etapa)){  
			query.setParameter("etapa", etapa);			
		}
		List<FiseArchivosCab> lista= query.getResultList();
		if(lista==null){
			return Collections.EMPTY_LIST;
		}else{
			return lista;
		}	
	}
	
	
	@SuppressWarnings("unchecked")	
	@Override
	public List<FiseArchivosDet> buscarFiseArchivosDet(long correlativo) throws SQLException{
		
		String q = "SELECT d FROM " + FiseArchivosDet.class.getName()
				+ " d WHERE 1=1 ";		
		if(correlativo!=0){ 		
			q = q.concat(" AND d.id.correlativo =:correlativo ");	
		}		
		Query query = em.createQuery(q); 
		
		if(correlativo!=0){
			query.setParameter("correlativo", correlativo);			
		}		
		List<FiseArchivosDet> lista= query.getResultList();
		if(lista==null){
			return Collections.EMPTY_LIST;
		}else{
			return lista;
		}	
	}	
	
	
	@Override
	public FiseArchivosCab obtenerFiseArchivosCab(Long id) throws SQLException{
		return em.find(FiseArchivosCab.class, id);		
	}
	
	
}
