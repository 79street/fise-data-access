package gob.osinergmin.fise.dao.impl;

import gob.osinergmin.base.dao.impl.GenericDaoImpl;
import gob.osinergmin.fise.dao.ArchivoSustentoDao;
import gob.osinergmin.fise.domain.FiseArchivosCab;
import gob.osinergmin.fise.domain.FiseArchivosDet;
import gob.osinergmin.fise.domain.FiseArchivosDetPK;
import gob.osinergmin.fise.util.FormatoUtil;

import java.math.BigDecimal;
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
	
	
	@Override
	public void insertarFiseArchivosDet(FiseArchivosDet fiseArchivosDet) 
			throws SQLException{
		em.persist(fiseArchivosDet);
	}
	
	@Override
	public void actualizarFiseArchivosDet(FiseArchivosDet fiseArchivosDet) 
			throws SQLException{
		em.merge(fiseArchivosDet);
	}
	
	
	@Override
	public FiseArchivosDet obtenerFiseArchivosDet(FiseArchivosDetPK id) 
			throws SQLException{
		return em.find(FiseArchivosDet.class, id);
	}
	
	@Override
	public void eliminarFiseArchivosCab(FiseArchivosCab id) throws SQLException{
		em.remove(id);
	}
	
	@Override
	public void eliminarFiseArchivosDet(FiseArchivosDet fiseArchivosDet) 
			throws SQLException{
		em.remove(fiseArchivosDet);
	}
	
	
	@Override
	public long buscarMaximoItemArchivo(long correlativo) throws SQLException{		
		long maxId = 1;		
		String q = "SELECT MAX(d.id.item) FROM " + FiseArchivosDet.class.getName()
				+ " d WHERE d.id.correlativo = :correlativo ";
		Query query = em.createQuery(q); 	
		query.setParameter("correlativo", correlativo);
		Long verifica = (Long)query.getSingleResult();
		if(verifica!=null){
			maxId = verifica +1;
		}
		return maxId;
	}
	
	
	/******metodos para eliminar datos de la tabla fiseArchivoCab*****/
	
	
	@SuppressWarnings("unchecked")	
	@Override
	public List<FiseArchivosCab> listaFiseArchivosCabMensual(String codEmpresa,
			long anioPres,long mesPres,BigDecimal anioEjec,BigDecimal mesEjec,String etapa,String formato) 
					throws SQLException{
		
		String q = "SELECT a FROM " + FiseArchivosCab.class.getName()
				+ " a WHERE  a.codEmpresa = :codEmpresa ";				
		q = q.concat(" AND a.anoPresentacion =:anioPres ");	
		q = q.concat(" AND a.mesPresentacion =:mesPres ");
		q = q.concat(" AND a.etapa = :etapa ");	
		q = q.concat(" AND a.formato = :formato ");	
		if(anioEjec!=null){ 		
			q = q.concat(" AND a.anoEjecucionGasto =:anioEjec ");	
		}
		if(mesEjec!=null){ 		
			q = q.concat(" AND a.mesEjecucionGasto =:mesEjec ");	
		}			
		Query query = em.createQuery(q); 
		
		String codEmpreCompleta = FormatoUtil.rellenaDerecha(codEmpresa, ' ', 4);
		
		query.setParameter("codEmpresa", codEmpreCompleta);
		query.setParameter("anioPres", anioPres);			
		query.setParameter("mesPres", mesPres);			
		query.setParameter("etapa", etapa);			
		query.setParameter("formato", formato);		
		if(anioEjec!=null){
			query.setParameter("anioEjec", anioEjec);			
		}
		if(mesEjec!=null){
			query.setParameter("mesEjec", mesEjec);			
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
	public List<FiseArchivosCab> listaFiseArchivosCabBienal(String codEmpresa,
			long anioPres,long mesPres,BigDecimal anioInioVig,BigDecimal anioFinVig,String etapa,String formato) 
					throws SQLException{
		
		String q = "SELECT a FROM " + FiseArchivosCab.class.getName()
				+ " a WHERE  a.codEmpresa = :codEmpresa ";				
		q = q.concat(" AND a.anoPresentacion =:anioPres ");	
		q = q.concat(" AND a.mesPresentacion =:mesPres ");
		q = q.concat(" AND a.etapa = :etapa ");		
		q = q.concat(" AND a.formato = :formato ");	
		
		if(anioInioVig!=null){ 		
			q = q.concat(" AND a.anoInicioVigencia =:anioInioVig ");	
		}
		if(anioFinVig!=null){ 		
			q = q.concat(" AND a.anoFinVigencia =:anioFinVig ");	
		}			
		Query query = em.createQuery(q); 
		
		String codEmpreCompleta = FormatoUtil.rellenaDerecha(codEmpresa, ' ', 4);
		
		query.setParameter("codEmpresa", codEmpreCompleta);
		query.setParameter("anioPres", anioPres);			
		query.setParameter("mesPres", mesPres);			
		query.setParameter("etapa", etapa);			
		query.setParameter("formato", formato);		
		
		if(anioInioVig!=null){
			query.setParameter("anioInioVig", anioInioVig);			
		}
		if(anioFinVig!=null){
			query.setParameter("anioFinVig", anioFinVig);			
		}
		
		List<FiseArchivosCab> lista= query.getResultList();
		if(lista==null){
			return Collections.EMPTY_LIST;
		}else{
			return lista;
		}	
	}
	
	
	
	
}
