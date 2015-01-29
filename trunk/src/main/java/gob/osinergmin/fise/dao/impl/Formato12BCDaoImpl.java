package gob.osinergmin.fise.dao.impl;

import gob.osinergmin.base.dao.impl.GenericDaoImpl;
import gob.osinergmin.fise.dao.Formato12BCDao;
import gob.osinergmin.fise.domain.AdmEmpresa;
import gob.osinergmin.fise.domain.FiseFormato12BC;
import gob.osinergmin.fise.domain.FiseFormato12BCPK;
import gob.osinergmin.fise.domain.FiseGrupoInformacion;
import gob.osinergmin.fise.util.FormatoUtil;

import java.math.BigDecimal;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import javax.persistence.Query;


import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;


@Repository(value = "formato12BCDaoImpl")
public class Formato12BCDaoImpl extends GenericDaoImpl implements Formato12BCDao {

	
	@SuppressWarnings("unchecked")
	@Override
	public List<FiseFormato12BC> getLstFormatoCabecera(String codemp, Integer anioDesde, Integer mesDesde, Integer anioHasta, Integer mesHasta, String etapa) {
		List<FiseFormato12BC> lstReturn=new ArrayList<FiseFormato12BC>();
		try {
			StringBuilder sb=new StringBuilder();
			sb.append("SELECT bc.COD_EMPRESA ,");
			sb.append("bc.ANO_PRESENTACION,");
			sb.append("bc.MES_PRESENTACION,");
			sb.append("bc.ANO_EJECUCION_GASTO,");
			sb.append("bc.MES_EJECUCION_GASTO,");
			sb.append("bc.ETAPA,");
			sb.append("bc.ID_GRUPO_INFORMACION,");
			sb.append("bc.TOTAL_RECONOCER,");
			sb.append("bc.NOMBRE_ARCHIVO_EXCEL,");
			sb.append("bc.NOMBRE_ARCHIVO_TEXTO,");
			sb.append("bc.FECHA_ENVIO_DEFINITIVO,");
			sb.append("bc.USUARIO_CREACION,");
			sb.append("bc.TERMINAL_CREACION,");
			sb.append("bc.FECHA_CREACION,");
			sb.append("bc.USUARIO_ACTUALIZACION,");
			sb.append("bc.TERMINAL_ACTUALIZACION,");
			sb.append("bc.FECHA_ACTUALIZACION,");
			sb.append("(SELECT E.DSC_CORTA_EMPRESA FROM ADM_EMPRESA E WHERE E.COD_EMPRESA = bc.COD_EMPRESA ) AS NAME_EMP, ");
			sb.append("(SELECT G.DESCRIPCION FROM FISE_GRUPO_INFORMACION G WHERE G.ID_GRUPO_INFORMACION=bc.ID_GRUPO_INFORMACION ) AS NAME_GRUPO ");
			sb.append(" FROM FISE_FORMATO_12B_C bc where 1=1 ");
			
			if(codemp!=null && !codemp.isEmpty()){
				sb.append(" AND bc.COD_EMPRESA = '"+codemp.trim()+"'");
			}if(etapa !=null && !etapa.isEmpty()){
				sb.append(" AND bc.ETAPA = '"+etapa.trim()+"'");
			}
			
			String finicio="";
			String ffin="";
			String where=" AND TO_NUMBER(  "
					+ "(select "
					+ " case  when LENGTH( cd.MES_PRESENTACION ) > 1   then  cd.ANO_PRESENTACION || cd.MES_PRESENTACION   else cd.ANO_PRESENTACION ||'0'|| cd.MES_PRESENTACION  end "
					+ " from FISE_FORMATO_12B_C cd  "
					+ " where cd.COD_EMPRESA = bc.COD_EMPRESA"
					+ " and cd.ANO_PRESENTACION = bc.ANO_PRESENTACION "
					+ " and cd.MES_PRESENTACION = bc.MES_PRESENTACION "
					+ " and cd.ANO_EJECUCION_GASTO = bc.ANO_EJECUCION_GASTO "
					+ " and cd.MES_EJECUCION_GASTO = bc.MES_EJECUCION_GASTO "
					+ " and cd.ETAPA = bc.ETAPA)"
					+ " ) ";
			boolean onlyMes=false;
			if(anioDesde != null && anioHasta!=null){
				finicio=anioDesde+"";
				ffin=anioHasta+"";
			}else if(anioDesde != null && anioHasta==null){
				finicio=anioDesde+"";
			}else if(anioDesde == null && anioHasta!=null){
				ffin=anioHasta+"";
			}else if(anioDesde == null && anioHasta==null){
				onlyMes=true;
			}
				
			if(mesDesde != null && mesHasta!=null){
				finicio=finicio+(mesDesde>9?mesDesde:"0"+mesDesde);
				ffin=ffin+(mesHasta>9?mesHasta:"0"+mesHasta);
			}else if(mesDesde != null && mesHasta==null){
				finicio=finicio+(mesDesde>9?mesDesde:"0"+mesDesde);
				ffin=ffin+"12";
			}else if(mesDesde == null && mesHasta!=null){
				finicio=finicio+"01";
				ffin=ffin+(mesHasta>9?mesHasta:"0"+mesHasta);
			}else if(mesDesde == null && mesHasta==null){
				finicio=finicio+"01";
				ffin=ffin+"12";
			}
			
			if(onlyMes){
				if(finicio.length()>0 && ffin.length()>0){
					sb.append(" AND bc.MES_PRESENTACION between " + finicio + " and " +ffin);
				}else if(finicio.length()>0 && ffin.length() == 0){
					sb.append(" AND bc.MES_PRESENTACION >= " + finicio );
				}else if(finicio.length() == 0 && ffin.length()>0){
					sb.append(" AND bc.MES_PRESENTACION<= " +ffin);
				}
			}else {
				if(finicio.length()>0 && ffin.length()>0){
					sb.append(where +" between " + finicio + " and " +ffin);
				}else if(finicio.length()>0 && ffin.length() == 0){
					sb.append(where +" >= " + finicio );
				}else if(finicio.length() == 0 && ffin.length()>0){
					sb.append(where +" <= " +ffin);
				}
			}
			
			
			System.out.println("********sql****");
			System.out.println(sb.toString());
			Query query = em.createNativeQuery(sb.toString());
			
			List<Object[]> lstObj=query.getResultList();
			
			if (lstObj.size() > 0) {
				for (Object[] obj : lstObj) {
					FiseFormato12BC cb = new FiseFormato12BC();
					FiseFormato12BCPK pk=new FiseFormato12BCPK();
					
					pk.setCodEmpresa(obj[0] == null ? "" : (obj[0].toString()));
					pk.setAnoPresentacion(obj[1] == null ? null : Integer.valueOf((obj[1].toString())));
					pk.setMesPresentacion(obj[2] == null ? null : Integer.valueOf((obj[2].toString())));
					pk.setAnoEjecucionGasto(obj[3] == null ? null : Integer.valueOf((obj[3].toString())));
					pk.setMesEjecucionGasto(obj[4] == null ? null : Integer.valueOf((obj[4].toString())));
					pk.setEtapa(obj[5] == null ? "" : (obj[5].toString()));
					cb.setId(pk);
				    
					FiseGrupoInformacion gi=new FiseGrupoInformacion();
				    gi.setIdGrupoInformacion(obj[6] == null ? null : Long.valueOf((obj[6].toString())));
				    gi.setDescripcion(obj[18] == null ? "" : (obj[18].toString()));
				    cb.setFiseGrupoInformacion(gi);
				    
				    cb.setTotalReconocer(obj[7] == null ? new BigDecimal(0) : new BigDecimal((obj[7].toString())));
				    cb.setNombreArchivoExcel(obj[8] == null ? "" : (obj[8].toString()));
					cb.setNombreArchivoTexto(obj[9] == null ? "" : (obj[9].toString()));
					cb.setFechaEnvioDefinitivo(obj[10] == null  ? null :(java.util.Date) (obj[10]));
					cb.setUsuarioCreacion(obj[11] == null ? "" : (obj[11].toString()));
					cb.setTerminalCreacion(obj[12] == null ? "" : (obj[12].toString()));
					cb.setFechaCreacion(obj[13] == null  ? null :(java.util.Date) (obj[13]));
					cb.setUsuarioActualizacion(obj[14] == null ? "" : (obj[14].toString()));
					cb.setTerminalActualizacion(obj[15] == null ? "" : (obj[15].toString()));
					cb.setFechaActualizacion(obj[16] == null  ? null :(java.util.Date) (obj[16]));
					
					AdmEmpresa emp=new AdmEmpresa();
					emp.setCodEmpresa(obj[0] == null ? "" : (obj[0].toString()));
					emp.setDscCortaEmpresa(obj[17] == null ? "" : (obj[17].toString()));
					
					cb.setAdmEmpresa(emp);
					
					lstReturn.add(cb);
				}
			}
			
		}catch(Exception e){
	      e.printStackTrace();
	     
		}finally{
			em.close();
			
		}
		return lstReturn;
		
	}

	@Override
	public FiseFormato12BC getFormatoCabeceraById(FiseFormato12BCPK id) {	
		FiseFormato12BC bean=null;
		try {
			StringBuilder sb=new StringBuilder();
			System.out.println("CABCERA EMP::"+id.getCodEmpresa().trim());
			System.out.println("CABCERA ETA::"+id.getEtapa().trim());
			System.out.println("CABCERA PRES::"+id.getAnoPresentacion());
			System.out.println("CABCERA MES PRES::"+id.getMesPresentacion());
			System.out.println("CABCERA ANIO EJEC::"+id.getAnoEjecucionGasto());
			System.out.println("CABCERA MES EJEC::"+id.getMesEjecucionGasto());
			
			sb.append("SELECT c FROM FiseFormato12BC c WHERE 1=1 ");
			
			if(id.getCodEmpresa() !=null && !id.getCodEmpresa().isEmpty()){
				sb.append(" AND c.id.codEmpresa ='"+id.getCodEmpresa().trim()+"'");
			}
			if(id.getEtapa()!=null && !id.getEtapa().isEmpty()){
				sb.append(" AND c.id.etapa =:etp ");
			}
			if(id.getAnoPresentacion()!=null && id.getAnoPresentacion()>0){
				sb.append(" AND c.id.anoPresentacion =:aniopres ");
			}
			if(id.getMesPresentacion()!=null && id.getMesPresentacion()>0){
				sb.append(" AND c.id.mesPresentacion =:mespres ");
			}
			if(id.getAnoEjecucionGasto()!=null && id.getAnoEjecucionGasto()>0){
				sb.append(" AND c.id.anoEjecucionGasto =:anioejec ");
			}
			if(id.getMesEjecucionGasto()!=null && id.getMesEjecucionGasto()>0){
				sb.append(" AND c.id.mesEjecucionGasto =:mesejec ");
			}
			
			Query query = em.createQuery(sb.toString());
			
			/*if(id.getCodEmpresa() !=null && !id.getCodEmpresa().isEmpty()){
				query.setParameter("emp", id.getCodEmpresa().trim());
			}*/
			if(id.getEtapa()!=null && !id.getEtapa().isEmpty()){
				query.setParameter("etp", id.getEtapa().trim());
			}
			if(id.getAnoPresentacion()!=null && id.getAnoPresentacion()>0){
				query.setParameter("aniopres", id.getAnoPresentacion());
			}
			if(id.getMesPresentacion()!=null && id.getMesPresentacion()>0){
				query.setParameter("mespres", id.getMesPresentacion());
			}
			if(id.getAnoEjecucionGasto()!=null && id.getAnoEjecucionGasto()>0){
				query.setParameter("anioejec", id.getAnoEjecucionGasto());
			}
			if(id.getMesEjecucionGasto()!=null && id.getMesEjecucionGasto()>0){
				query.setParameter("mesejec", id.getMesEjecucionGasto());
			}		
			 bean= (FiseFormato12BC) query.getSingleResult();	
			 
			
			//return bean;
		}catch(Exception e){
	      e.printStackTrace();
	     
		}finally{
			em.close();		
		}
		
		 System.out.println("RETORNAMDO BEAN==>"+bean);
		
return bean;
	}

	@Override
	@Transactional
	public FiseFormato12BC saveFormatoCabecera(FiseFormato12BC formato) throws DataIntegrityViolationException,Exception{
		FiseFormato12BC result = null;
		try {
				em.persist(formato);
			result = formato;
			System.out.println("cabecera persis::=>" + result);
		} finally {
			em.close();
		}
		return result;
	}

	@Override
	@Transactional
	public Integer updateFormatoCabecera(FiseFormato12BC formato)throws DataIntegrityViolationException,Exception {
		Integer result = null;
		try {
			em.merge(formato);
			System.out.println("cabecera merge::=>" + formato);
			result = 1;
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			em.close();
		}
		return result;
	}

	@Override
	
	public Integer deleteFormatoCabecera(FiseFormato12BCPK id) throws DataIntegrityViolationException,Exception{

		try {
				StringBuilder sb=new StringBuilder();
			
			//sb.append("DELETE FROM FiseFormato12BC c WHERE 1=1 ");
			sb.append("DELETE FROM FISE_FORMATO_12B_C  WHERE 1=1 ");
			
			if(id.getCodEmpresa() !=null && !id.getCodEmpresa().isEmpty()){
				//sb.append(" AND c.id.codEmpresa =:emp ");
				sb.append(" AND COD_EMPRESA = '"+id.getCodEmpresa()+"'");
			}
			if(id.getEtapa()!=null && !id.getEtapa().isEmpty()){
				//sb.append(" AND c.id.etapa =:etp ");
				sb.append(" AND ETAPA = ? ");
			}
			if(id.getAnoPresentacion()!=null && id.getAnoPresentacion()>0){
				//sb.append(" AND c.id.anoPresentacion =:aniopres ");
				sb.append(" AND ANO_PRESENTACION = ? ");
			}
			if(id.getMesPresentacion()!=null && id.getMesPresentacion()>0){
				//sb.append(" AND c.id.mesPresentacion =:mespres ");
				sb.append(" AND MES_PRESENTACION = ? ");
			}
			if(id.getAnoEjecucionGasto()!=null && id.getAnoEjecucionGasto()>0){
				//sb.append(" AND c.id.anoEjecucionGasto =:anioejec ");
				sb.append(" AND ANO_EJECUCION_GASTO = ? ");
			}
			if(id.getMesEjecucionGasto()!=null && id.getMesEjecucionGasto()>0){
				//sb.append(" AND c.id.mesEjecucionGasto =:mesejec ");
				sb.append(" AND MES_EJECUCION_GASTO = ? ");
			}
			
			
			Query query = em.createNativeQuery(sb.toString());
			int param=1;
			/*if(id.getCodEmpresa() !=null && !id.getCodEmpresa().isEmpty()){
				//query.setParameter("emp", id.getCodEmpresa().trim());
				query.setParameter(param, id.getCodEmpresa().trim());
				param++;
			}*/
			if(id.getEtapa()!=null && !id.getEtapa().isEmpty()){
				//query.setParameter("etp", id.getEtapa().trim());
				query.setParameter(param, id.getEtapa().trim());
				param++;
			}
			if(id.getAnoPresentacion()!=null && id.getAnoPresentacion()>0){
				//query.setParameter("aniopres", id.getAnoPresentacion());
				query.setParameter(param, id.getAnoPresentacion());
				param++;
			}
			if(id.getMesPresentacion()!=null && id.getMesPresentacion()>0){
				//query.setParameter("mespres", id.getMesPresentacion());
				query.setParameter(param, id.getMesPresentacion());
				param++;
			}
			if(id.getAnoEjecucionGasto()!=null && id.getAnoEjecucionGasto()>0){
				//query.setParameter("anioejec", id.getAnoEjecucionGasto());
				query.setParameter(param, id.getAnoEjecucionGasto());
				param++;
			}
			if(id.getMesEjecucionGasto()!=null && id.getMesEjecucionGasto()>0){
				//query.setParameter("mesejec", id.getMesEjecucionGasto());
				query.setParameter(param, id.getMesEjecucionGasto());
				param++;
			}
			
          System.out.println("EMPRESA ELIMINANDO CABECERA::"+id.getCodEmpresa().trim());
          System.out.println("ANO ELIMINANDO CABECERA::"+id.getAnoPresentacion());
          System.out.println("MES ELIMINANDO CABECERA::"+id.getMesPresentacion());
          System.out.println("ANIO EJEC ELIMINANDO CABECERA::"+id.getAnoEjecucionGasto());
          System.out.println("MES EJEC ELIMINANDO CABECERA::"+id.getAnoEjecucionGasto());
          System.out.println("ETAPA ELIMINANDO CABECERA::"+id.getEtapa().trim());
			
			int cant=  query.executeUpdate();
			
			return cant;
		}finally{
			em.close();
			
		}
	}
	
	
	@SuppressWarnings("unchecked")	
	@Override
	public List<FiseFormato12BC> buscarFormato12BCReenvio(String codEmpresa, Integer anioPres, 
			Integer mesPres, String etapa) throws SQLException{
		
		String q = "SELECT f FROM " + FiseFormato12BC.class.getName()
				+ " f WHERE 1=1 ";
		if(FormatoUtil.isNotBlank(codEmpresa)){ 
			q = q.concat(" AND f.id.codEmpresa = :codEmpresa ");
		}
		if(anioPres!=0){ 		
			q = q.concat(" AND f.id.anoPresentacion =:anioPres ");	
		}
		if(mesPres!=0){ 
			q = q.concat(" AND f.id.mesPresentacion = :mesPres ");				
		}		
		if(FormatoUtil.isNotBlank(etapa)){ 
			q = q.concat(" AND f.id.etapa = :etapa ");
		}
		q = q.concat(" AND f.fechaEnvioDefinitivo IS NOT NULL ");		
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
		List<FiseFormato12BC> lista= query.getResultList();
		 if(lista==null){
			 return Collections.EMPTY_LIST;
		 }else{
			 return lista;
		 }	
	}

	
}
