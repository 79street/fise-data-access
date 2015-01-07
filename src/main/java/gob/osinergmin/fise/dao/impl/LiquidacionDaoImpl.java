package gob.osinergmin.fise.dao.impl;

import gob.osinergmin.base.dao.impl.GenericDaoImpl;
import gob.osinergmin.fise.dao.LiquidacionDao;
import gob.osinergmin.fise.domain.FiseLiquidacione;
import gob.osinergmin.fise.domain.FiseLiquidacionesMotivosNo;
import gob.osinergmin.fise.domain.FiseLiquidacionesMotivosNoPK;
import gob.osinergmin.fise.util.FormatoUtil;

import java.sql.SQLException;
import java.util.Collections;
import java.util.List;

import javax.persistence.Query;

import org.springframework.stereotype.Repository;


@Repository(value = "liquidacionDaoImpl")
public class LiquidacionDaoImpl extends GenericDaoImpl implements LiquidacionDao {
	
	
	@Override
	public int llenarDatosFiseLiquidacion(String codEmpresa,long idGrupoInf, 
			String usuario, String terminal) throws SQLException{
		int result = -1;
		try {		
			StringBuffer jql = new StringBuffer();
			jql.append("CALL FISE_GEN_PKG.FISE_PUEBLA_LIQUIDAC_PRC(?,?,?,?)");
			Query query = em.createNativeQuery(jql.toString());
			query.setParameter(1, codEmpresa);
			query.setParameter(2, idGrupoInf);					
			query.setParameter(3, usuario);
			query.setParameter(4, terminal);			
			result = query.executeUpdate();		
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			em.close();
		}
		return result;
	}
	
	
	@Override
	public String obtenerUltimaEtapa(String formato,String codEmpresa, 
			long anioPres, long mesPres, long anioEjec,
			long mesEjec,long anioIniVig,long anioFinVig) throws SQLException{
		String etapa="";
		try {			
			StringBuffer jql = new StringBuffer();
			jql.append("SELECT FISE_GEN_PKG.FISE_GET_ULT_ETAPA_LIQUID_FUN(?,?,?,?,?,?,?,?) FROM DUAL");
			Query query = em.createNativeQuery(jql.toString());
			query.setParameter(1, formato);
			query.setParameter(2, codEmpresa);
			query.setParameter(3, anioPres);
			query.setParameter(4, mesPres);
			query.setParameter(5, anioEjec);
			query.setParameter(6, mesEjec);
			query.setParameter(7, anioIniVig);
			query.setParameter(8, anioFinVig);
			etapa = (String)query.getSingleResult();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			 em.close();
		}
		return etapa;
	}
	
	@SuppressWarnings("unchecked")	
	@Override
	public List<FiseLiquidacione> buscarFiseLiquidacion(String codEmpresa,
			long idGrupoInf) throws SQLException{
		
		String q = "SELECT f FROM " + FiseLiquidacione.class.getName()
				+ " f WHERE 1=1 ";
		if(FormatoUtil.isNotBlank(codEmpresa) && !"TODO".equals(codEmpresa)){  
			q = q.concat(" AND f.codEmpresa = :codEmpresa ");
		}
		if(idGrupoInf!=0){ 		
			q = q.concat(" AND f.fiseGrupoInformacion.idGrupoInformacion =:idGrupoInf ");	
		}		
		Query query = em.createQuery(q); 
		if(FormatoUtil.isNotBlank(codEmpresa) && !"TODO".equals(codEmpresa)){ 
			String codEmpreCompleta = FormatoUtil.rellenaDerecha(codEmpresa, ' ', 4);
			query.setParameter("codEmpresa", codEmpreCompleta);
		}
		if(idGrupoInf!=0){
			query.setParameter("idGrupoInf", idGrupoInf);			
		}		
		List<FiseLiquidacione> lista= query.getResultList();
		if(lista==null){
			return Collections.EMPTY_LIST;
		}else{
			return lista;
		}	
	}
	
	
	@Override
	public void eliminarFiseLiquidacion(FiseLiquidacione fiseLiquidacione) 
			throws SQLException{
		em.remove(fiseLiquidacione); 		
	}
	
	@Override
	public FiseLiquidacione obtenerFiseLiquidacion(Long id) throws SQLException{
		return em.find(FiseLiquidacione.class, id);		
	}
	
	
	@Override
	public int preparaLiquidacionFormato(long correlativo, 
			String usuario, String terminal) throws SQLException{
		int result = -1;
		try {	
			StringBuffer jql = new StringBuffer();
			jql.append("CALL FISE_GEN_PKG.FISE_PREPARA_LIQ_FORMATO_PRC(?,?,?)");
			Query query = em.createNativeQuery(jql.toString());
			query.setParameter(1, correlativo);								
			query.setParameter(2, usuario);
			query.setParameter(3, terminal);			
			result = query.executeUpdate();		
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			em.close();
		}
		return result;
	}
	
	
	@Override
	public int liquidarFormato(long correlativo, 
			String usuario, String terminal) throws SQLException{
		int result = -1;
		try {				
			StringBuffer jql = new StringBuffer();
			jql.append("CALL FISE_GEN_PKG.FISE_LIQUIDA_FORMATO_PRC(?,?,?)");
			Query query = em.createNativeQuery(jql.toString());
			query.setParameter(1, correlativo);								
			query.setParameter(2, usuario);
			query.setParameter(3, terminal);			
			result = query.executeUpdate();		
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			em.close();
		}
		return result;
	}
	
	/*****Motivos de la liquidacion****/
	
	@SuppressWarnings("unchecked")	
	@Override
	public List<FiseLiquidacionesMotivosNo> buscarFiseLiquidacionesMotivosNo(long correlativo,
			long item) throws SQLException{
		
		String q = "SELECT m FROM " + FiseLiquidacionesMotivosNo.class.getName()
				+ " m WHERE 1=1 ";		
		if(correlativo!=0){ 		
			q = q.concat(" AND m.id.correlativo =:correlativo ");	
		}
		if(item!=0){ 		
			q = q.concat(" AND m.id.item =:item ");	
		}
		Query query = em.createQuery(q); 
		if(correlativo!=0){
			query.setParameter("correlativo", correlativo);			
		}
		if(item!=0){
			query.setParameter("item", item);			
		}		
		List<FiseLiquidacionesMotivosNo> lista= query.getResultList();
		if(lista==null){
			return Collections.EMPTY_LIST;
		}else{
			return lista;
		}	
	}
	
	@Override
	public FiseLiquidacionesMotivosNo obtenerFiseLiquidacionesMotivosNo(FiseLiquidacionesMotivosNoPK id) 
			throws SQLException{
		return em.find(FiseLiquidacionesMotivosNo.class, id);		
	}
	
	@Override
	public void insertarFiseLiquidacionesMotivosNo(FiseLiquidacionesMotivosNo liquidacionMotivosNo) 
			throws SQLException{
		em.persist(liquidacionMotivosNo);
		
	}

	@Override
	public void actualizarFiseLiquidacionesMotivosNo(FiseLiquidacionesMotivosNo liquidacionMotivosNo) 
			throws SQLException{
		em.merge(liquidacionMotivosNo);		
	}
	
	
	@Override
	public long buscarMaximoMotivo() throws SQLException{		
		long maxId = 1;		
		String q = "SELECT MAX(m.id.item) FROM " + FiseLiquidacionesMotivosNo.class.getName()
				+ " m  ";
		Query query = em.createQuery(q); 		
		Long verifica = (Long)query.getSingleResult();
		if(verifica!=null){
			maxId = verifica +1;
		}
		return maxId;
	}
	

}
