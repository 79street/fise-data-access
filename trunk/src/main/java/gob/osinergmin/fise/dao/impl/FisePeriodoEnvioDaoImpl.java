package gob.osinergmin.fise.dao.impl;

import gob.osinergmin.base.dao.impl.GenericDaoImpl;
import gob.osinergmin.fise.constant.FiseConstants;
import gob.osinergmin.fise.dao.FisePeriodoEnvioDao;
import gob.osinergmin.fise.domain.FisePeriodoEnvio;
import gob.osinergmin.fise.util.FechaUtil;
import gob.osinergmin.fise.util.FormatoUtil;

import java.math.BigDecimal;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
import java.util.Iterator;
import java.util.List;

import javax.persistence.Query;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

@Repository(value = "fisePeriodoEnvioDaoImpl")
public class FisePeriodoEnvioDaoImpl extends GenericDaoImpl implements FisePeriodoEnvioDao {
	
	public List<FisePeriodoEnvio> listarFisePeriodoEnvioMesAnioEtapa(String codEmpresa, String nombreFormato) {
		
		List<FisePeriodoEnvio> lst = new ArrayList<FisePeriodoEnvio>();
		try {
			StringBuilder sql = new StringBuilder();
			
			sql.append(" SELECT DISTINCT ANO_PRESENTACION || LPAD(MES_PRESENTACION, 2, '0') || ETAPA CODIGO,");
			//sql.append(" DECODE(MES_PRESENTACION,1,'Ene',2,'Feb',3,'Mar',4,'Abr',5,'May',6,'Jun',7,'Jul',8,'Ago',9,'Set',10,'Oct',11,'Nov',12,'Dic')");
			sql.append(" SUBSTR(FISE_GEN_PKG.FISE_NOMBRE_MES_FUN(MES_PRESENTACION),1,3)");
			sql.append(" || '-' || ANO_PRESENTACION || ' / ' || ETAPA DESCRIPCION, ");
			
			/*sql.append(" || '-' || ANO_PRESENTACION || ' / ' || (CASE ETAPA "
					+ "WHEN 'SOLICITUD' THEN 'SOLICITUD' "
					+ "WHEN 'LEV.OBS' THEN 'LEVANTAMIENTO OBSERVACIONES' "
					+ "WHEN 'RECONSIDERACION' THEN 'RECONSIDERACION' "
					+ "WHEN 'HISTORICO' THEN 'HISTORICO' "
					+ "WHEN 'RECONOCIDO' THEN 'RECONOCIDO' "
					+ "WHEN 'ESTABLECIDO' THEN 'ESTABLECIDO' "
					+ "ELSE '' "
					+ "END) DESCRIPCION, ");*/

			sql.append(" FLAG_MOSTRAR_ANO_MES_EJEC FLAG_PERIODO_EJEC, ");
			sql.append(" ANO_INICIO_VIGENCIA ANO_INICIO_VIGENCIA, ");
			sql.append(" ANO_FIN_VIGENCIA ANO_FIN_VIGENCIA, ");
			sql.append(" FLAG_HABILITA_COSTOS_D_I_F14C FLAG_HABILITA_COSTOS,");
			sql.append(" FLAG_ENVIO_CON_OBSERVACIONES FLAG_ENVIO_OBS, ");
			sql.append(" FLAG_PERMITIR_EDITAR_COSTO_EST FLAG_EDITAR_COSTO_EST ");
			sql.append(" FROM FISE_PERIODO_ENVIO t");
			sql.append(" WHERE 1=1 ");
			
			if(FormatoUtil.isNotBlank(codEmpresa))
				sql.append(" AND COD_EMPRESA = '").append(codEmpresa.trim()).append("' ");
			if(FormatoUtil.isNotBlank(nombreFormato))
				sql.append(" AND FORMATO = '").append(nombreFormato.trim()).append("' ");
			System.out.println("FechaUtil.fechaHoyYYYYMMDD().trim()"+FechaUtil.fechaHoyYYYYMMDD().trim());
			sql.append(" AND '").append(FechaUtil.fechaHoyYYYYMMDD().trim()).append("' BETWEEN TO_CHAR(DESDE, 'YYYYMMDD') AND TO_CHAR(NVL(FECHA_AMPLIADA, HASTA), 'YYYYMMDD') ");
			sql.append(" AND ESTADO='V' ");
			sql.append(" ORDER BY CODIGO DESC");
			//System.out.println("QUERY:"+sql.toString());
			
			Query query = em.createNativeQuery(sql.toString());
			
			List resultado = query.getResultList();
			Iterator it=resultado.iterator();
			while(it.hasNext()){
				Object[] valor=(Object[] )it.next();
				FisePeriodoEnvio periodoEnvio=new FisePeriodoEnvio();
				periodoEnvio.setCodigoItem((String)valor[0]);
				periodoEnvio.setDescripcionItem((String)valor[1]);
				periodoEnvio.setFlagPeriodoEjecucion((String)valor[2]);
				periodoEnvio.setAnioInicioVig(((BigDecimal)valor[3])!=null?((BigDecimal)valor[3]).toString():FiseConstants.BLANCO);
				periodoEnvio.setAnioFinVig(((BigDecimal)valor[4])!=null?((BigDecimal)valor[4]).toString():FiseConstants.BLANCO);
				periodoEnvio.setFlagHabilitaCostos((String)valor[5]);
				periodoEnvio.setFlagEnvioConObservaciones((String)valor[6]);
				periodoEnvio.setFlagEditarCostosEst((String)valor[7]);
				lst.add(periodoEnvio);
			}
		} catch (Exception e) {			
			e.printStackTrace();
		} finally {
			 em.close();
		 }
		return lst;
	}
	
	@Override
	public void insertarFisePeriodoEnvio(FisePeriodoEnvio fisePeriodoEnvio) 
			throws SQLException{
		em.persist(fisePeriodoEnvio);
		
	}

	@Override
	public void actualizarFisePeriodoEnvio(FisePeriodoEnvio fisePeriodoEnvio) 
			throws SQLException{
		em.merge(fisePeriodoEnvio);		
	}

	@Override
	public void eliminarFisePeriodoEnvio(FisePeriodoEnvio fisePeriodoEnvio) 
			throws SQLException{
		em.remove(fisePeriodoEnvio); 		
	}
	
	@Override
	public FisePeriodoEnvio obtenerFisePeriodoEnvio(Long id) 
			throws SQLException{
		return em.find(FisePeriodoEnvio.class, id);		
	}
	
	@SuppressWarnings("unchecked")	
	@Override
	public List<FisePeriodoEnvio> buscarFisePeriodoEnvio(String codEmpresa, Integer anioPres, 
			Integer mesPres, String formato,String etapa,
			String flagEnvio,String estado,Date fechaActual) throws SQLException{
		System.out.println("Ingresando a buscar periodo envio"); 
		String q = "SELECT p FROM " + FisePeriodoEnvio.class.getName()
				+ " p WHERE 1=1 ";
		if(FormatoUtil.isNotBlank(codEmpresa)){ 
			q = q.concat(" AND p.codEmpresa = :codEmpresa ");
		}
		if(anioPres!=0){ 		
			q = q.concat(" AND p.anoPresentacion =:anioPres");	
		}
		if(mesPres!=0){ 
			q = q.concat(" AND p.mesPresentacion =:mesPres");				
		}		
		if(FormatoUtil.isNotBlank(etapa)){ 
			q = q.concat(" AND p.etapa = :etapa ");
		}
		if(FormatoUtil.isNotBlank(formato)){ 
			q = q.concat(" AND p.formato = :formato ");
		}
		if(FormatoUtil.isNotBlank(estado)){ 
			q = q.concat(" AND p.estado = :estado ");
		}
		if(flagEnvio.equals("1")){ //SI
			q = q.concat(" AND  p.desde <=:fechaActual AND p.hasta>=:fechaActual");
		}else if(flagEnvio.equals("2")){//NO
			q = q.concat(" AND  p.desde >=:fechaActual AND p.hasta<=:fechaActual ");
		}
		Query query = em.createQuery(q); 
		if(FormatoUtil.isNotBlank(codEmpresa)){ 
			String codEmpreCompleta = FormatoUtil.rellenaDerecha(codEmpresa, ' ', 4);
			query.setParameter("codEmpresa", codEmpreCompleta);
		}
		if(anioPres!=0){
			query.setParameter("anioPres", anioPres);			
		}
		if(mesPres!=0){ 
			query.setParameter("mesPres", mesPres);			
		}		
		if(FormatoUtil.isNotBlank(etapa)){ 
			query.setParameter("etapa", etapa);
		}
		if(FormatoUtil.isNotBlank(formato)){ 
			query.setParameter("formato", formato);
		}
		if(FormatoUtil.isNotBlank(estado)){ 
			query.setParameter("estado", estado);
		}
		if(flagEnvio.equals("1")){ //SI
			query.setParameter("fechaActual", fechaActual);
		}else if(flagEnvio.equals("2")){//NO
			query.setParameter("fechaActual", fechaActual);
		}
		List<FisePeriodoEnvio> lista= query.getResultList();
		 if(lista==null){
			 return Collections.EMPTY_LIST;
		 }else{
			 return lista;
		 }	
	}
	
	//MENSUAL
	public List<FisePeriodoEnvio> listarFisePeriodoEnvioMesAnioEtapaCumplimiento(String frecuenciaFormato){
		
		List<FisePeriodoEnvio> lst = new ArrayList<FisePeriodoEnvio>();
		try {
			StringBuilder sql = new StringBuilder();
		
			sql.append(" select distinct ano_presentacion || LPAD(mes_presentacion, 2, '0') || ETAPA CODIGO,");
			sql.append(" DECODE(MES_PRESENTACION,1,'Ene',2,'Feb',3,'Mar',4,'Abr',5,'May',6,'Jun',7,'Jul',8,'Ago',9,'Set',10,'Oct',11,'Nov',12,'Dic')");
			sql.append(" || '-' || ANO_PRESENTACION || ' / ' || ETAPA DESCRIPCION");
			sql.append(" FROM FISE_PERIODO_ENVIO t");
			sql.append(" WHERE 1=1 ");
			
			if( FiseConstants.FRECUENCIA_MENSUAL_DESCRIPCION.equals(frecuenciaFormato) ){
				sql.append(" AND FORMATO IN ('F12A', 'F12B','F12C','F12D') ");
			}else if( FiseConstants.FRECUENCIA_BIENAL_DESCRIPCION.equals(frecuenciaFormato) ){
				sql.append(" AND FORMATO IN ('F13A', 'F14A','F14B','F14C') ");
			}

			sql.append(" order by 1 desc");
			Query query = em.createNativeQuery(sql.toString());
			
			List resultado = query.getResultList();
			Iterator it=resultado.iterator();
			while(it.hasNext()){
				Object[] valor=(Object[] )it.next();
				FisePeriodoEnvio periodoEnvio=new FisePeriodoEnvio();
				periodoEnvio.setCodigoItem((String)valor[0]);
				periodoEnvio.setDescripcionItem((String)valor[1]);
				lst.add(periodoEnvio);
			}
		} catch (Exception e) {			
			e.printStackTrace();
		}

		return lst;
	}
	
	/***Metodo verficar si existe un registro existente con estado vigente para una empresa*/
	@SuppressWarnings("unchecked")	
	@Override
	public boolean verificarPeridoEnvioEmpresa(String codEmpresa, Integer anioPres, 
			Integer mesPres, String formato,String etapa,String estado) throws SQLException{
		
		String q = "SELECT p FROM " + FisePeriodoEnvio.class.getName()
				+ " p WHERE 1=1 ";
		if(FormatoUtil.isNotBlank(codEmpresa)){ 
			q = q.concat(" AND p.codEmpresa = :codEmpresa ");
		}
		if(anioPres!=0){ 		
			q = q.concat(" AND p.anoPresentacion =:anioPres");	
		}
		if(mesPres!=0){ 
			q = q.concat(" AND p.mesPresentacion =:mesPres");				
		}		
		if(FormatoUtil.isNotBlank(etapa)){ 
			q = q.concat(" AND p.etapa = :etapa ");
		}
		if(FormatoUtil.isNotBlank(formato)){ 
			q = q.concat(" AND p.formato = :formato ");
		}
		if(FormatoUtil.isNotBlank(estado)){ 
			q = q.concat(" AND p.estado = :estado ");
		}		
		Query query = em.createQuery(q); 
		if(FormatoUtil.isNotBlank(codEmpresa)){ 
			String codEmpreCompleta = FormatoUtil.rellenaDerecha(codEmpresa, ' ', 4);
			query.setParameter("codEmpresa", codEmpreCompleta);
		}
		if(anioPres!=0){
			query.setParameter("anioPres", anioPres);			
		}
		if(mesPres!=0){ 
			query.setParameter("mesPres", mesPres);			
		}		
		if(FormatoUtil.isNotBlank(etapa)){ 
			query.setParameter("etapa", etapa);
		}
		if(FormatoUtil.isNotBlank(formato)){ 
			query.setParameter("formato", formato);
		}
		if(FormatoUtil.isNotBlank(estado)){ 
			query.setParameter("estado", estado);
		}		
		 List<FisePeriodoEnvio> lista= query.getResultList();
		 if(lista!=null && lista.size()>0){
			 return true;
		 }else{
			 return false;
		 }	
	}
	
	/***Metodo para obtener el flag envio con observaciones de un periodo para una determinada empresa*/
	@SuppressWarnings("unchecked")	
	@Override
	public String obtenerFlagEnvioConObs(String codEmpresa, Integer anioPres, 
			Integer mesPres, String formato,String etapa,String estado) throws SQLException{
		
		String q = "SELECT p FROM " + FisePeriodoEnvio.class.getName()
				+ " p WHERE 1=1 ";
		if(FormatoUtil.isNotBlank(codEmpresa)){ 
			q = q.concat(" AND p.codEmpresa = :codEmpresa ");
		}
		if(anioPres!=0){ 		
			q = q.concat(" AND p.anoPresentacion =:anioPres");	
		}
		if(mesPres!=0){ 
			q = q.concat(" AND p.mesPresentacion =:mesPres");				
		}		
		if(FormatoUtil.isNotBlank(etapa)){ 
			q = q.concat(" AND p.etapa = :etapa ");
		}
		if(FormatoUtil.isNotBlank(formato)){ 
			q = q.concat(" AND p.formato = :formato ");
		}
		if(FormatoUtil.isNotBlank(estado)){ 
			q = q.concat(" AND p.estado = :estado ");
		}		
		Query query = em.createQuery(q); 
		if(FormatoUtil.isNotBlank(codEmpresa)){ 
			String codEmpreCompleta = FormatoUtil.rellenaDerecha(codEmpresa, ' ', 4);
			query.setParameter("codEmpresa", codEmpreCompleta);
		}
		if(anioPres!=0){
			query.setParameter("anioPres", anioPres);			
		}
		if(mesPres!=0){ 
			query.setParameter("mesPres", mesPres);			
		}		
		if(FormatoUtil.isNotBlank(etapa)){ 
			query.setParameter("etapa", etapa);
		}
		if(FormatoUtil.isNotBlank(formato)){ 
			query.setParameter("formato", formato);
		}
		if(FormatoUtil.isNotBlank(estado)){ 
			query.setParameter("estado", estado);
		}		
		 List<FisePeriodoEnvio> lista= query.getResultList();
		 if(lista==null){
			 return "";
		 }else{
			 return lista.get(0).getFlagEnvioConObservaciones();
		 }	
	}
	
	
	/***Metodo para obtener el plazo maximo para el levantamiento de observaciones***/
	@Override
	public String listarPlazoMaximoEnvioObs(String codEmpresa,long anioPres,
    		long mesPres,String etapa,String formato) throws SQLException{
    	
    	StringBuilder sql = new StringBuilder();
    	String plazo = "";    	
    	
    	sql.append("SELECT ");
    	sql.append(" TO_CHAR(HASTA, 'DD/MM/YYYY') AS FECHA, ");
    	sql.append(" NVL((SELECT VALOR ");
    	sql.append(" FROM FISE_PARAMETROS ");
    	sql.append(" WHERE CODIGO = 'HORA_PLAZO_ENVIO_FORMATO'),'18:00:00') AS HORA ");
    	sql.append(" FROM FISE_PERIODO_ENVIO t ");
    	sql.append(" WHERE t.COD_EMPRESA = '").append(codEmpresa.trim()).append("' ");
    	sql.append(" AND t.ANO_PRESENTACION = ").append(anioPres).append(" ");
    	sql.append(" AND t.MES_PRESENTACION = ").append(mesPres).append(" ");
    	sql.append(" AND t.FORMATO = '").append(formato).append("' ");
    	sql.append(" AND t.ETAPA = '").append(etapa).append("' ");
    	sql.append(" ORDER BY SECUENCIA DESC ");
    	
    	String jql = sql.toString();
    	Query query = em.createNativeQuery(jql);
    	
    	List resultado = query.getResultList();
		Iterator it=resultado.iterator();
		while(it.hasNext()){
			Object[] valor=(Object[] )it.next();			
			plazo = ((String)valor[0])+" "+((String)valor[1]);
			break;
		}
		return plazo;
	}

}
