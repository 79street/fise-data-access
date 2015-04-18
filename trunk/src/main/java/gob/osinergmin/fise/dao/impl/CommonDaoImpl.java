package gob.osinergmin.fise.dao.impl;

import gob.osinergmin.base.dao.impl.GenericDaoImpl;
import gob.osinergmin.fise.bean.CorreoBean;
import gob.osinergmin.fise.bean.CumplimientoReportBean;
import gob.osinergmin.fise.bean.Formato12A12BGeneric;
import gob.osinergmin.fise.bean.Formato12C12D13Generic;
import gob.osinergmin.fise.bean.Formato14Generic;
import gob.osinergmin.fise.bean.HistoricoCostosBean;
import gob.osinergmin.fise.bean.VariacionCostosBean;
import gob.osinergmin.fise.constant.FiseConstants;
import gob.osinergmin.fise.dao.CommonDao;
import gob.osinergmin.fise.util.FormatoUtil;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;

import javax.persistence.Query;

import org.springframework.stereotype.Repository;

@Repository(value = "commonDaoImpl")
public class CommonDaoImpl extends GenericDaoImpl implements CommonDao {
	
	@Override
	public int obtenerSecuencia(){
		int i=0;
		try {
			StringBuffer jql = new StringBuffer();
			jql.append("SELECT FISE_GEN_PKG.FISE_CORR_ARCH_FUN FROM DUAL");
			Query query = em.createNativeQuery(jql.toString());
			BigDecimal secuencia = (BigDecimal)query.getSingleResult();
			i = secuencia.intValue();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			 em.close();
		 }
		return i;
	}
	
	@Override
	public int validarFormatos_12A12B(Formato12A12BGeneric formato, String tipoFormato, String usuario, String terminal){
		int result = -1;
		try {
			StringBuffer jql = new StringBuffer();
			jql.append("CALL FISE_GEN_PKG.FISE_VAL_FORM_PRC (?,?,?,?,?,?,?,?,?)");
			Query query = em.createNativeQuery(jql.toString());
			query.setParameter(1, tipoFormato);
			query.setParameter(2, formato.getCodigoEmpresa());
			query.setParameter(3, formato.getAnoPresentacion());
			query.setParameter(4, formato.getMesPresentacion());
			query.setParameter(5, formato.getAnoEjecucion());
			query.setParameter(6, formato.getMesEjecucion());
			query.setParameter(7, formato.getCodigoEtapa());
			query.setParameter(8, usuario);
			query.setParameter(9, terminal);
			/*int v = query.executeUpdate();
			if (v == 0) {
				return new MensajeTransaccion();
			}*/
			//podemos validar posteriormente la transaccion
			result = query.executeUpdate();
			
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			em.close();
		 }
		return result;
	}
	
	@Override
	public int validarFormatos_12C12D13A(Formato12C12D13Generic formato, String tipoFormato, String usuario, String terminal){
		int result = -1;
		try {
			StringBuffer jql = new StringBuffer();
			jql.append("CALL FISE_GEN_PKG.FISE_VAL_FORM_PRC3 (?,?,?,?,?,?,?)");
			Query query = em.createNativeQuery(jql.toString());
			query.setParameter(1, tipoFormato);
			query.setParameter(2, formato.getCodigoEmpresa());
			query.setParameter(3, formato.getAnoPresentacion());
			query.setParameter(4, formato.getMesPresentacion());
			query.setParameter(5, formato.getCodigoEtapa());
			query.setParameter(6, usuario);
			query.setParameter(7, terminal);
			/*int v = query.executeUpdate();
			if (v == 0) {
				return new MensajeTransaccion();
			}*/
			//podemos validar posteriormente la transaccion
			result = query.executeUpdate();
			
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			em.close();
		 }
		return result;
	}
	
	@Override
	public int validarFormatos_14(Formato14Generic formato14, String tipoFormato, String usuario, String terminal){
		int result = -1;
		try {
			 
			StringBuffer jql = new StringBuffer();
			jql.append("CALL FISE_GEN_PKG.FISE_VAL_FORM_PRC2 (?,?,?,?,?,?,?,?,?)");
			Query query = em.createNativeQuery(jql.toString());
			query.setParameter(1, tipoFormato);
			query.setParameter(2, formato14.getCodigoEmpresa());
			query.setParameter(3, formato14.getAnoPresentacion());
			query.setParameter(4, formato14.getMesPresentacion());
			query.setParameter(5, formato14.getAnoInicioVigencia());
			query.setParameter(6, formato14.getAnoFinVigencia());
			query.setParameter(7, formato14.getCodigoEtapa());
			query.setParameter(8, usuario);
			query.setParameter(9, terminal);
			/*int v = query.executeUpdate();
			if (v == 0) {
				return new MensajeTransaccion();
			}*/
			//podemos validar posteriormente la transaccion
			result = query.executeUpdate();
			
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			em.close();
		 }
		return result;
	}

	@Override
	public List<CorreoBean> obtenerListaCorreosDestinatarios(){
		
		List<CorreoBean> listaCorreo = new ArrayList<CorreoBean>();
		try {
			StringBuilder sql = new StringBuilder();
			
			sql.append(" SELECT EMAILADDRESS CORREO, FIRSTNAME || ' ' || LASTNAME NOMBRE ");
			sql.append(" FROM USER_ T ");
			sql.append(" WHERE USERID IN (SELECT USERID FROM USERS_GROUPS WHERE GROUPID IN ");
			sql.append(" (SELECT GROUPID FROM GROUP_ WHERE ACTIVE_= 1 AND SITE = 1 AND UPPER(NAME) LIKE '%FISE%')) ");
			sql.append(" AND LTRIM(EMAILADDRESS) IS NOT NULL ");
			sql.append("  AND USERID IN (SELECT USERID FROM USERS_ORGS T WHERE ORGANIZATIONID IN ");
			sql.append(" (SELECT CLASSPK FROM EXPANDOVALUE T WHERE UPPER(RTRIM(DATA_)) = UPPER(RTRIM('OSI')))) ");
			
			Query query = em.createNativeQuery(sql.toString());
			
			List resultado = query.getResultList();
			Iterator it=resultado.iterator();
			while(it.hasNext()){
				Object[] valor=(Object[] )it.next();
				CorreoBean correo=new CorreoBean();
				correo.setDireccionCorreo((String)valor[0]);
				correo.setUsuarioCorreo((String)valor[1]);
				listaCorreo.add(correo);
			}
		} catch (Exception e) {			
			e.printStackTrace();
		} finally {
			 em.close();
		 }

		return listaCorreo;

	}
	
	@Override
	public List<CorreoBean> obtenerListaCorreosDestinatariosByEmpresa(String codEmpresa){
		
		List<CorreoBean> listaCorreo = new ArrayList<CorreoBean>();
		try {
			StringBuilder sql = new StringBuilder();
			
			sql.append(" SELECT EMAILADDRESS CORREO, FIRSTNAME || ' ' || LASTNAME NOMBRE ");
			sql.append(" FROM USER_ T ");
			sql.append(" WHERE USERID IN (SELECT USERID FROM USERS_GROUPS WHERE GROUPID IN ");
			sql.append(" (SELECT GROUPID FROM GROUP_ WHERE ACTIVE_= 1 AND SITE = 1 AND UPPER(NAME) LIKE '%FISE%')) ");
			sql.append(" AND LTRIM(EMAILADDRESS) IS NOT NULL ");
			sql.append("  AND USERID IN (SELECT USERID FROM USERS_ORGS T WHERE ORGANIZATIONID IN ");
			sql.append(" (SELECT CLASSPK FROM EXPANDOVALUE T WHERE UPPER(RTRIM(DATA_)) = UPPER(RTRIM('"+codEmpresa+"')))) ");
			
			Query query = em.createNativeQuery(sql.toString());
			
			List resultado = query.getResultList();
			Iterator it=resultado.iterator();
			while(it.hasNext()){
				Object[] valor=(Object[] )it.next();
				CorreoBean correo=new CorreoBean();
				correo.setDireccionCorreo((String)valor[0]);
				correo.setUsuarioCorreo((String)valor[1]);
				listaCorreo.add(correo);
			}
		} catch (Exception e) {			
			e.printStackTrace();
		} finally {
			 em.close();
		 }
		return listaCorreo;

	}
	
	/**Para obtener el id del grupo de informacion**/
	
	@Override
	public Long obtenerIdGrupoInformacion(Long anioPresentacion,Long mesPresentacion, String frecuencia){
     	Long valor = new Long(0); 
		try {
			StringBuffer jql = new StringBuffer();
			jql.append("SELECT FISE_GEN_PKG.FISE_GET_GRUPO_FUN (?,?,?) FROM DUAL");
			Query query = em.createNativeQuery(jql.toString());
			query.setParameter(1, anioPresentacion);
			query.setParameter(2, mesPresentacion);
			query.setParameter(3, frecuencia);
			if(query.getSingleResult()!=null){
			 BigDecimal codigo = (BigDecimal)query.getSingleResult();
			 valor = codigo.longValue();
			}			
		} catch (Exception e) {
			valor =new Long(0);
			e.printStackTrace();
		} finally {
			 em.close();
		 }
		return valor;
	}	
	
	/**para validar el flujo de los procesos de EDITAR, ANULAR o ENVIO DEFINITIVO*/
	
	@Override
	public String obtenerEstadoProceso(String codEmpresa, String tipoFormato, 
			long anoPresentacion, long mesPresentacion, String codEtapa){
		String estado="";
		try {
			StringBuffer jql = new StringBuffer();
			jql.append("SELECT FISE_GEN_PKG.FISE_ESTADO_FORMATO_FUN(?,?,?,?,?) FROM DUAL");
			Query query = em.createNativeQuery(jql.toString());
			query.setParameter(1, codEmpresa);
			query.setParameter(2, tipoFormato);
			query.setParameter(3, anoPresentacion);
			query.setParameter(4, mesPresentacion);
			query.setParameter(5, codEtapa);
			estado = (String)query.getSingleResult();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			 em.close();
		 }
		return estado;
	}

//	@SuppressWarnings("unchecked")
//	@Override
	public List<CumplimientoReportBean> listarFormatolistaDet(long p_ano, long p_mes, String p_etapa ) {
	
		List<CumplimientoReportBean> lista = new ArrayList<CumplimientoReportBean>();
		try{
			StringBuilder sql = new StringBuilder();
			sql.append("SELECT trim(EMP.COD_EMPRESA) AS COD_EMPRESA, ");//0
			sql.append(" trim(EMP.DSC_CORTA_EMPRESA) AS DSC_CORTA_EMPRESA,");//1
			sql.append("FISE_GEN_PKG.FISE_ENVIADO_FUN(EMP.COD_EMPRESA,'F12A',");
			sql.append(p_ano).append(",").append(p_mes).append(",'").append(p_etapa).append("') F12A,");//2
			sql.append("FISE_GEN_PKG.FISE_ENVIADO_FUN(EMP.COD_EMPRESA,'F12B',");
			sql.append(p_ano).append(",").append(p_mes).append(",'").append(p_etapa).append("') F12B,");//3
			sql.append("FISE_GEN_PKG.FISE_ENVIADO_FUN(EMP.COD_EMPRESA,'F12C',");
			sql.append(p_ano).append(",").append(p_mes).append(",'").append(p_etapa).append("') F12C,");//4
			sql.append("FISE_GEN_PKG.FISE_ENVIADO_FUN(EMP.COD_EMPRESA,'F12D',");
			sql.append(p_ano).append(",").append(p_mes).append(",'").append(p_etapa).append("') F12D,");//5
			sql.append("FISE_GEN_PKG.FISE_ENVIADO_FUN(EMP.COD_EMPRESA,'F13A',");
			sql.append(p_ano).append(",").append(p_mes).append(",'").append(p_etapa).append("') F13A,");//6
			sql.append("FISE_GEN_PKG.FISE_ENVIADO_FUN(EMP.COD_EMPRESA,'F14A',");
			sql.append(p_ano).append(",").append(p_mes).append(",'").append(p_etapa).append("') F14A,");//7
			sql.append("FISE_GEN_PKG.FISE_ENVIADO_FUN(EMP.COD_EMPRESA,'F14B',");
			sql.append(p_ano).append(",").append(p_mes).append(",'").append(p_etapa).append("') F14B,");//8
			sql.append("FISE_GEN_PKG.FISE_ENVIADO_FUN(EMP.COD_EMPRESA,'F14C',");
			sql.append(p_ano).append(",").append(p_mes).append(",'").append(p_etapa).append("') F14C");//9
			sql.append(" FROM ADMIN_GART.ADM_EMPRESA EMP");
			sql.append(" JOIN ADMIN_GART.ADM_PROC_EMPRESA PEMP");
			sql.append(" ON EMP.COD_EMPRESA = PEMP.COD_EMPRESA");
			sql.append(" AND PEMP.COD_PROC_SUPERVISION = 'FISE'");
			sql.append(" AND PEMP.COD_FUNCION_PROC_SUPERV = 'REMISION'");
			sql.append(" ORDER BY EMP.DSC_CORTA_EMPRESA");
			
			Query query = em.createNativeQuery(sql.toString());
			
			List resultado = query.getResultList();
			Iterator it=resultado.iterator();
			while(it.hasNext()){
				Object[] valor=(Object[] )it.next();
				CumplimientoReportBean cumplimiento=new CumplimientoReportBean();
				cumplimiento.setCodigoEmpresa((String)valor[0]);
				cumplimiento.setDescipcionCortaEmpresa((String)valor[1]);
				cumplimiento.setFormat12A(((String)valor[2]==null)? " ": (String)valor[2]);
				cumplimiento.setFormat12B(((String)valor[3]==null)? " ": (String)valor[3]);
				cumplimiento.setFormat12C(((String)valor[4]==null)? " ": (String)valor[4]);
				cumplimiento.setFormat12D(((String)valor[5]==null)? " ": (String)valor[5]);
				cumplimiento.setFormat13A(((String)valor[6]==null)? " ": (String)valor[6]);
				cumplimiento.setFormat14A(((String)valor[7]==null)? " ": (String)valor[7]);
				cumplimiento.setFormat14B(((String)valor[8]==null)? " ": (String)valor[8]);
				cumplimiento.setFormat14C(((String)valor[9]==null)? " ": (String)valor[9]);
				lista.add(cumplimiento);
			}
		} catch (Exception e) {			
			e.printStackTrace();
		}

		return lista;
	}
	
	@Override
	public boolean fechaEnvioCumplePlazo(String tipoFormato, String codEmpresa, Long anoPresentacion, Long mesPresentacion, String etapa, String fechaEnvio){
		 boolean result=false;
		 String resultado = "";
		try {
			StringBuffer jql = new StringBuffer();
			jql.append("SELECT FISE_GEN_PKG.FISE_EN_EL_PLAZO_FUN(?,?,?,?,?,?) FROM DUAL");
			Query query = em.createNativeQuery(jql.toString());
			query.setParameter(1, codEmpresa);
			query.setParameter(2, tipoFormato);
			query.setParameter(3, anoPresentacion);
			query.setParameter(4, mesPresentacion);
			query.setParameter(5, etapa);
			query.setParameter(6, fechaEnvio);
			resultado = (String)query.getSingleResult();
			
			if( resultado != null ){
				if( "S".equals(resultado.trim()) ){
					result = true;
				}else{
					result = false;
				}
			}else{
				result = false;
			}
			
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			 em.close();
		 }
		return result;
	}
	
	/*@SuppressWarnings("unchecked")
	@Override
	public List<Object[]> listarObsNotificacion(String codEmpresa,
			String etapa,String formato,Long idGrupoInf) throws SQLException{
		StringBuilder sb = new StringBuilder();	
		sb.append(" SELECT DISTINCT ");
		sb.append(" c.COD_EMPRESA, ");//0		
		sb.append(" c.ANO_PRESENTACION, ");//1
		sb.append(" c.MES_PRESENTACION, ");//2		
		if(FiseConstants.NOMBRE_FORMATO_12A.equals(formato)){ 			
			sb.append(" c.ANO_EJECUCION_GASTO, ");//3
			sb.append(" c.MES_EJECUCION_GASTO, ");//4
			sb.append(" c.ETAPA ");//5				
			sb.append(" FROM FISE_FORMATO_12A_C c , FISE_FORMATO_12A_D_OBS o ");		
			sb.append(" WHERE o.COD_EMPRESA = c.COD_EMPRESA");
			sb.append(" AND o.ANO_PRESENTACION = c.ANO_PRESENTACION ");
			sb.append(" AND o.MES_PRESENTACION = c.MES_PRESENTACION ");
			sb.append(" AND o.ETAPA = c.ETAPA ");			
		}else if(FiseConstants.NOMBRE_FORMATO_12B.equals(formato)){ 
			sb.append(" c.ANO_EJECUCION_GASTO, ");//3
			sb.append(" c.MES_EJECUCION_GASTO, ");//4
			sb.append(" c.ETAPA ");//5				
			sb.append(" FROM FISE_FORMATO_12B_C c , FISE_FORMATO_12B_D_OBS o ");		
			sb.append(" WHERE o.COD_EMPRESA = c.COD_EMPRESA");
			sb.append(" AND o.ANO_PRESENTACION = c.ANO_PRESENTACION ");
			sb.append(" AND o.MES_PRESENTACION = c.MES_PRESENTACION ");
			sb.append(" AND o.ETAPA = c.ETAPA ");			
		}else if(FiseConstants.NOMBRE_FORMATO_12C.equals(formato)){ 
			//sb.append(" c.ANO_EJECUCION_GASTO, ");//3
			//sb.append(" c.MES_EJECUCION_GASTO, ");//4
			sb.append(" c.ETAPA ");//3			
			sb.append(" FROM FISE_FORMATO_12C_C c , FISE_FORMATO_12C_D_OBS o ");		
			sb.append(" WHERE o.COD_EMPRESA = c.COD_EMPRESA");
			sb.append(" AND o.ANO_PRESENTACION = c.ANO_PRESENTACION ");
			sb.append(" AND o.MES_PRESENTACION = c.MES_PRESENTACION ");
			sb.append(" AND o.ETAPA = c.ETAPA ");	
		}else if(FiseConstants.NOMBRE_FORMATO_12D.equals(formato)){ 
			//sb.append(" c.ANO_EJECUCION_GASTO, ");//3
			//sb.append(" c.MES_EJECUCION_GASTO, ");//4
			sb.append(" c.ETAPA ");//3			
			sb.append(" FROM FISE_FORMATO_12D_C c , FISE_FORMATO_12D_D_OBS o ");		
			sb.append(" WHERE o.COD_EMPRESA = c.COD_EMPRESA");
			sb.append(" AND o.ANO_PRESENTACION = c.ANO_PRESENTACION ");
			sb.append(" AND o.MES_PRESENTACION = c.MES_PRESENTACION ");
			sb.append(" AND o.ETAPA = c.ETAPA ");
		}else if(FiseConstants.NOMBRE_FORMATO_13A.equals(formato)){ 				
			
			sb.append(" FISE_GEN_PKG.FISE_GET_ANO_INI_FIN_FUN(c.COD_EMPRESA,c.ANO_PRESENTACION,c.MES_PRESENTACION,c.ETAPA,'INICIO'),  ");//3
			sb.append(" FISE_GEN_PKG.FISE_GET_ANO_INI_FIN_FUN(c.COD_EMPRESA,c.ANO_PRESENTACION,c.MES_PRESENTACION,c.ETAPA,'FIN'),  ");//4
			
			sb.append(" c.ETAPA ");//5				
			sb.append(" FROM FISE_FORMATO_13A_C c , FISE_FORMATO_13A_D_OBS o ");		
			sb.append(" WHERE o.COD_EMPRESA = c.COD_EMPRESA");
			sb.append(" AND o.ANO_PRESENTACION = c.ANO_PRESENTACION ");
			sb.append(" AND o.MES_PRESENTACION = c.MES_PRESENTACION ");
			sb.append(" AND o.ETAPA = c.ETAPA ");
		}else if(FiseConstants.NOMBRE_FORMATO_14A.equals(formato)){			
			sb.append(" c.ANO_INICIO_VIGENCIA, ");//3
			sb.append(" c.ANO_FIN_VIGENCIA, ");//4
			sb.append(" c.ETAPA ");//5				
			sb.append(" FROM FISE_FORMATO_14A_C c, FISE_FORMATO_14A_D_OBS o ");		
			sb.append(" WHERE o.COD_EMPRESA = c.COD_EMPRESA");
			sb.append(" AND o.ANO_PRESENTACION = c.ANO_PRESENTACION ");
			sb.append(" AND o.MES_PRESENTACION = c.MES_PRESENTACION ");
			sb.append(" AND o.ETAPA = c.ETAPA ");	
		}else if(FiseConstants.NOMBRE_FORMATO_14B.equals(formato)){ 
			sb.append(" c.ANO_INICIO_VIGENCIA, ");//3
			sb.append(" c.ANO_FIN_VIGENCIA, ");//4
			sb.append(" c.ETAPA ");//5				
			sb.append(" FROM FISE_FORMATO_14B_C c , FISE_FORMATO_14B_D_OBS o ");		
			sb.append(" WHERE o.COD_EMPRESA = c.COD_EMPRESA");
			sb.append(" AND o.ANO_PRESENTACION = c.ANO_PRESENTACION ");
			sb.append(" AND o.MES_PRESENTACION = c.MES_PRESENTACION ");
			sb.append(" AND o.ETAPA = c.ETAPA ");	
		}else if(FiseConstants.NOMBRE_FORMATO_14C.equals(formato)){ 
			sb.append(" c.ANO_INICIO_VIGENCIA, ");//3
			sb.append(" c.ANO_FIN_VIGENCIA, ");//4
			sb.append(" c.ETAPA ");//5				
			sb.append(" FROM FISE_FORMATO_14C_C c, FISE_FORMATO_14C_D_OBS o ");		
			sb.append(" WHERE o.COD_EMPRESA = c.COD_EMPRESA");
			sb.append(" AND o.ANO_PRESENTACION = c.ANO_PRESENTACION ");
			sb.append(" AND o.MES_PRESENTACION = c.MES_PRESENTACION ");
			sb.append(" AND o.ETAPA = c.ETAPA ");	
		}		
		if(FormatoUtil.isNotBlank(codEmpresa)){			
			sb.append(" AND o.COD_EMPRESA = '"+codEmpresa+"' ");					
		}		
		if(idGrupoInf!=0){			
			sb.append(" AND c.ID_GRUPO_INFORMACION = "+idGrupoInf+" ");			
		}		
		if(FormatoUtil.isNotBlank(etapa)){
			sb.append(" AND o.ETAPA = '"+etapa+"' ");
		}		
		String jql = sb.toString();
		Query query = em.createNativeQuery(jql);	
		
		List<Object[]> lista = query.getResultList();		
		if (lista == null) {
			return Collections.EMPTY_LIST;
		} else {
			return lista;
		}		
	}*/
	
	
	
	@SuppressWarnings("unchecked")
	@Override
	public List<Object[]> listarObsNotificacion(String codEmpresa,
			String etapa,String formato,Long idGrupoInf) throws SQLException{
		StringBuilder sb = new StringBuilder();	
		sb.append(" SELECT DISTINCT ");
		sb.append(" c.COD_EMPRESA, ");//0		
		sb.append(" c.ANO_PRESENTACION, ");//1
		sb.append(" c.MES_PRESENTACION, ");//2		
		if(FiseConstants.NOMBRE_FORMATO_12A.equals(formato)){ 			
			sb.append(" c.ANO_EJECUCION_GASTO, ");//3
			sb.append(" c.MES_EJECUCION_GASTO, ");//4
			sb.append(" c.ETAPA ");//5				
			sb.append(" FROM FISE_FORMATO_12A_C c  ");		
						
		}else if(FiseConstants.NOMBRE_FORMATO_12B.equals(formato)){ 
			sb.append(" c.ANO_EJECUCION_GASTO, ");//3
			sb.append(" c.MES_EJECUCION_GASTO, ");//4
			sb.append(" c.ETAPA ");//5				
			sb.append(" FROM FISE_FORMATO_12B_C c  ");		
						
		}else if(FiseConstants.NOMBRE_FORMATO_12C.equals(formato)){ 
			//sb.append(" c.ANO_EJECUCION_GASTO, ");//3
			//sb.append(" c.MES_EJECUCION_GASTO, ");//4
			sb.append(" c.ETAPA ");//3			
			sb.append(" FROM FISE_FORMATO_12C_C c ");		
			
		}else if(FiseConstants.NOMBRE_FORMATO_12D.equals(formato)){ 
			//sb.append(" c.ANO_EJECUCION_GASTO, ");//3
			//sb.append(" c.MES_EJECUCION_GASTO, ");//4
			sb.append(" c.ETAPA ");//3			
			sb.append(" FROM FISE_FORMATO_12D_C c ");		
			
		}else if(FiseConstants.NOMBRE_FORMATO_13A.equals(formato)){ 				
		
			sb.append(" FISE_GEN_PKG.FISE_GET_ANO_INI_FIN_FUN(c.COD_EMPRESA,c.ANO_PRESENTACION,c.MES_PRESENTACION,c.ETAPA,'INICIO'),  ");//3
			sb.append(" FISE_GEN_PKG.FISE_GET_ANO_INI_FIN_FUN(c.COD_EMPRESA,c.ANO_PRESENTACION,c.MES_PRESENTACION,c.ETAPA,'FIN'),  ");//4
			
			sb.append(" c.ETAPA ");//5				
			sb.append(" FROM FISE_FORMATO_13A_C c  ");		
			
		}else if(FiseConstants.NOMBRE_FORMATO_14A.equals(formato)){			
			sb.append(" c.ANO_INICIO_VIGENCIA, ");//3
			sb.append(" c.ANO_FIN_VIGENCIA, ");//4
			sb.append(" c.ETAPA ");//5				
			sb.append(" FROM FISE_FORMATO_14A_C c ");		
				
		}else if(FiseConstants.NOMBRE_FORMATO_14B.equals(formato)){ 
			sb.append(" c.ANO_INICIO_VIGENCIA, ");//3
			sb.append(" c.ANO_FIN_VIGENCIA, ");//4
			sb.append(" c.ETAPA ");//5				
			sb.append(" FROM FISE_FORMATO_14B_C c  ");		
				
		}else if(FiseConstants.NOMBRE_FORMATO_14C.equals(formato)){ 
			sb.append(" c.ANO_INICIO_VIGENCIA, ");//3
			sb.append(" c.ANO_FIN_VIGENCIA, ");//4
			sb.append(" c.ETAPA ");//5				
			sb.append(" FROM FISE_FORMATO_14C_C c");			
		}		
		sb.append(" WHERE 1=1 ");	
		
		if(FormatoUtil.isNotBlank(codEmpresa)){			
			sb.append(" AND c.COD_EMPRESA = '"+codEmpresa+"' ");					
		}		
		if(idGrupoInf!=0){			
			sb.append(" AND c.ID_GRUPO_INFORMACION = "+idGrupoInf+" ");			
		}		
		if(FormatoUtil.isNotBlank(etapa)){
			sb.append(" AND c.ETAPA = '"+etapa+"' ");
		}		
		String jql = sb.toString();
		Query query = em.createNativeQuery(jql);	
		
		List<Object[]> lista = query.getResultList();		
		if (lista == null) {
			return Collections.EMPTY_LIST;
		} else {
			return lista;
		}		
	}
	
	
	@SuppressWarnings("unchecked")
	@Override
	public List<Object[]> listarObsNotificacionProcesar(String codEmpresa,
			String etapa,String formato,Long idGrupoInf) throws SQLException{
		StringBuilder sb = new StringBuilder();	
		sb.append(" SELECT DISTINCT ");
		sb.append(" c.COD_EMPRESA, ");//0		
		sb.append(" c.ANO_PRESENTACION, ");//1
		sb.append(" c.MES_PRESENTACION, ");//2		
		if(FiseConstants.NOMBRE_FORMATO_12A.equals(formato)){ 			
			sb.append(" c.ANO_EJECUCION_GASTO, ");//3
			sb.append(" c.MES_EJECUCION_GASTO, ");//4
			sb.append(" c.ETAPA ");//5			
			sb.append(" FROM FISE_FORMATO_12A_C c");		
			sb.append(" WHERE 1=1 ");					
		}else if(FiseConstants.NOMBRE_FORMATO_12B.equals(formato)){ 
			sb.append(" c.ANO_EJECUCION_GASTO, ");//3
			sb.append(" c.MES_EJECUCION_GASTO, ");//4
			sb.append(" c.ETAPA ");//5				
			sb.append(" FROM FISE_FORMATO_12B_C c ");		
			sb.append(" WHERE 1=1 ");						
		}else if(FiseConstants.NOMBRE_FORMATO_12C.equals(formato)){ 
			//sb.append(" c.ANO_EJECUCION_GASTO, ");//3
			//sb.append(" c.MES_EJECUCION_GASTO, ");//4
			sb.append(" c.ETAPA ");//3			
			sb.append(" FROM FISE_FORMATO_12C_C c ");		
			sb.append(" WHERE 1=1 ");			
		}else if(FiseConstants.NOMBRE_FORMATO_12D.equals(formato)){ 
			//sb.append(" c.ANO_EJECUCION_GASTO, ");//3
			//sb.append(" c.MES_EJECUCION_GASTO, ");//4
			sb.append(" c.ETAPA ");//3			
			sb.append(" FROM FISE_FORMATO_12D_C c ");		
			sb.append(" WHERE 1=1 ");			
		}else if(FiseConstants.NOMBRE_FORMATO_13A.equals(formato)){ 				
			/***/
			sb.append(" FISE_GEN_PKG.FISE_GET_ANO_INI_FIN_FUN(c.COD_EMPRESA,c.ANO_PRESENTACION,c.MES_PRESENTACION,c.ETAPA,'INICIO'),  ");//3
			sb.append(" FISE_GEN_PKG.FISE_GET_ANO_INI_FIN_FUN(c.COD_EMPRESA,c.ANO_PRESENTACION,c.MES_PRESENTACION,c.ETAPA,'FIN'), ");//4
			/***/
			sb.append(" c.ETAPA ");//5				
			sb.append(" FROM FISE_FORMATO_13A_C c ");		
			sb.append(" WHERE 1=1 ");					
		}else if(FiseConstants.NOMBRE_FORMATO_14A.equals(formato)){			
			sb.append(" c.ANO_INICIO_VIGENCIA, ");//3
			sb.append(" c.ANO_FIN_VIGENCIA, ");//4
			sb.append(" c.ETAPA ");//5				
			sb.append(" FROM FISE_FORMATO_14A_C c");		
			sb.append(" WHERE 1=1 ");				
		}else if(FiseConstants.NOMBRE_FORMATO_14B.equals(formato)){ 
			sb.append(" c.ANO_INICIO_VIGENCIA, ");//3
			sb.append(" c.ANO_FIN_VIGENCIA, ");//4
			sb.append(" c.ETAPA ");//5				
			sb.append(" FROM FISE_FORMATO_14B_C c");		
			sb.append(" WHERE 1=1 ");			
		}else if(FiseConstants.NOMBRE_FORMATO_14C.equals(formato)){ 
			sb.append(" c.ANO_INICIO_VIGENCIA, ");//3
			sb.append(" c.ANO_FIN_VIGENCIA, ");//4
			sb.append(" c.ETAPA ");//5				
			sb.append(" FROM FISE_FORMATO_14C_C c");		
			sb.append(" WHERE 1=1 ");			}		
		if(FormatoUtil.isNotBlank(codEmpresa)){			
			sb.append(" AND c.COD_EMPRESA = '"+codEmpresa+"' ");					
		}		
		if(idGrupoInf!=0){			
			sb.append(" AND c.ID_GRUPO_INFORMACION = "+idGrupoInf+" ");			
		}		
		if(FormatoUtil.isNotBlank(etapa)){
			sb.append(" AND c.ETAPA = '"+etapa+"' ");
		}		
		String jql = sb.toString();
		Query query = em.createNativeQuery(jql);	
		
		List<Object[]> lista = query.getResultList();		
		if (lista == null) {
			return Collections.EMPTY_LIST;
		} else {
			return lista;
		}		
	}
	
	/*****Funcion para realizar la notificacion del consolidado de observaciones****/
	@Override
	public String notificarValidacionMensual(String codEmpresa, String etapa, 
			long idGrupoInf, String periodicidad, String user,String terminal) throws SQLException{
		String mensaje="";
		try {
			StringBuffer jql = new StringBuffer();
			jql.append("CALL FISE_GEN_PKG.FISE_CREA_ETAPA_PRC(?,?,?,?,?,?)");	
			Query query = em.createNativeQuery(jql.toString());
			query.setParameter(1, codEmpresa);
			query.setParameter(2, etapa);
			query.setParameter(3, periodicidad);//MENSUAL,BIENAL
			query.setParameter(4, idGrupoInf);
			query.setParameter(5, user);
			query.setParameter(6, terminal);
			int valor = query.executeUpdate();	
			System.out.println("Valor del mensaje al ejecutar el procedimiento :  "+valor); 
			mensaje = String.valueOf(valor);
		} catch (Exception e) {
			e.printStackTrace();
			mensaje = "1";
		} finally {
			 em.close();
		 }
		return mensaje;
	}
	
	
	@SuppressWarnings("unchecked")
	@Override
	public List<Object[]> listarEnvioDefinitivo(String codEmpresa,
			String etapa,String formato,Long idGrupoInf) throws SQLException{
		StringBuilder sb = new StringBuilder();	
		sb.append(" SELECT DISTINCT ");
		sb.append(" c.COD_EMPRESA, ");//0		
		sb.append(" c.ANO_PRESENTACION, ");//1
		sb.append(" c.MES_PRESENTACION, ");//2		
		if(FiseConstants.NOMBRE_FORMATO_12A.equals(formato)){ 			
			sb.append(" c.ANO_EJECUCION_GASTO, ");//3
			sb.append(" c.MES_EJECUCION_GASTO, ");//4
			sb.append(" c.ETAPA, ");//5
			sb.append(" FISE_GEN_PKG.FISE_ESTADO_FORMATO_FUN(c.COD_EMPRESA,'"+formato+"',c.ANO_PRESENTACION,c.MES_PRESENTACION,c.ETAPA,c.ANO_EJECUCION_GASTO,c.MES_EJECUCION_GASTO) AS ESTADO ");//6	
			sb.append(" FROM FISE_FORMATO_12A_C c");		
			sb.append(" WHERE 1=1 ");					
		}else if(FiseConstants.NOMBRE_FORMATO_12B.equals(formato)){ 
			sb.append(" c.ANO_EJECUCION_GASTO, ");//3
			sb.append(" c.MES_EJECUCION_GASTO, ");//4
			sb.append(" c.ETAPA, ");//5	
			sb.append(" FISE_GEN_PKG.FISE_ESTADO_FORMATO_FUN(c.COD_EMPRESA,'"+formato+"',c.ANO_PRESENTACION,c.MES_PRESENTACION,c.ETAPA,c.ANO_EJECUCION_GASTO,c.MES_EJECUCION_GASTO) AS ESTADO ");//6
			sb.append(" FROM FISE_FORMATO_12B_C c ");		
			sb.append(" WHERE 1=1 ");						
		}else if(FiseConstants.NOMBRE_FORMATO_12C.equals(formato)){ 
			//sb.append(" c.ANO_EJECUCION_GASTO, ");//3
			//sb.append(" c.MES_EJECUCION_GASTO, ");//4
			sb.append(" c.ETAPA, ");//3
			sb.append(" FISE_GEN_PKG.FISE_ESTADO_FORMATO_FUN(c.COD_EMPRESA,'"+formato+"',c.ANO_PRESENTACION,c.MES_PRESENTACION,c.ETAPA) AS ESTADO ");//6
			sb.append(" FROM FISE_FORMATO_12C_C c ");		
			sb.append(" WHERE 1=1 ");			
		}else if(FiseConstants.NOMBRE_FORMATO_12D.equals(formato)){ 
			//sb.append(" c.ANO_EJECUCION_GASTO, ");//3
			//sb.append(" c.MES_EJECUCION_GASTO, ");//4
			sb.append(" c.ETAPA, ");//3
			sb.append(" FISE_GEN_PKG.FISE_ESTADO_FORMATO_FUN(c.COD_EMPRESA,'"+formato+"',c.ANO_PRESENTACION,c.MES_PRESENTACION,c.ETAPA) AS ESTADO ");//6
			sb.append(" FROM FISE_FORMATO_12D_C c ");		
			sb.append(" WHERE 1=1 ");			
		}else if(FiseConstants.NOMBRE_FORMATO_13A.equals(formato)){ 	
			/***/
			sb.append(" FISE_GEN_PKG.FISE_GET_ANO_INI_FIN_FUN(c.COD_EMPRESA,c.ANO_PRESENTACION,c.MES_PRESENTACION,c.ETAPA,'INICIO'),  ");//3
			sb.append(" FISE_GEN_PKG.FISE_GET_ANO_INI_FIN_FUN(c.COD_EMPRESA,c.ANO_PRESENTACION,c.MES_PRESENTACION,c.ETAPA,'FIN'),  ");//4
			/***/
			sb.append(" c.ETAPA, ");//5
			sb.append(" FISE_GEN_PKG.FISE_ESTADO_FORMATO_FUN(c.COD_EMPRESA,'"+formato+"',c.ANO_PRESENTACION,c.MES_PRESENTACION,c.ETAPA) AS ESTADO ");//6
			sb.append(" FROM FISE_FORMATO_13A_C c ");		
			sb.append(" WHERE 1=1 ");					
		}else if(FiseConstants.NOMBRE_FORMATO_14A.equals(formato)){			
			sb.append(" c.ANO_INICIO_VIGENCIA, ");//3
			sb.append(" c.ANO_FIN_VIGENCIA, ");//4
			sb.append(" c.ETAPA, ");//5	
			sb.append(" FISE_GEN_PKG.FISE_ESTADO_FORMATO_FUN(c.COD_EMPRESA,'"+formato+"',c.ANO_PRESENTACION,c.MES_PRESENTACION,c.ETAPA,"+null+","+null+",c.ANO_INICIO_VIGENCIA,c.ANO_FIN_VIGENCIA) AS ESTADO ");//6
			sb.append(" FROM FISE_FORMATO_14A_C c");		
			sb.append(" WHERE 1=1 ");				
		}else if(FiseConstants.NOMBRE_FORMATO_14B.equals(formato)){ 
			sb.append(" c.ANO_INICIO_VIGENCIA, ");//3
			sb.append(" c.ANO_FIN_VIGENCIA, ");//4
			sb.append(" c.ETAPA, ");//5	
			sb.append(" FISE_GEN_PKG.FISE_ESTADO_FORMATO_FUN(c.COD_EMPRESA,'"+formato+"',c.ANO_PRESENTACION,c.MES_PRESENTACION,c.ETAPA,"+null+","+null+",c.ANO_INICIO_VIGENCIA,c.ANO_FIN_VIGENCIA) AS ESTADO ");//6
			sb.append(" FROM FISE_FORMATO_14B_C c");		
			sb.append(" WHERE 1=1 ");			
		}else if(FiseConstants.NOMBRE_FORMATO_14C.equals(formato)){ 
			sb.append(" c.ANO_INICIO_VIGENCIA, ");//3
			sb.append(" c.ANO_FIN_VIGENCIA, ");//4
			sb.append(" c.ETAPA, ");//5		
			sb.append(" FISE_GEN_PKG.FISE_ESTADO_FORMATO_FUN(c.COD_EMPRESA,'"+formato+"',c.ANO_PRESENTACION,c.MES_PRESENTACION,c.ETAPA,"+null+","+null+",c.ANO_INICIO_VIGENCIA,c.ANO_FIN_VIGENCIA) AS ESTADO ");//6
			sb.append(" FROM FISE_FORMATO_14C_C c");		
			sb.append(" WHERE 1=1 ");			}		
		if(FormatoUtil.isNotBlank(codEmpresa)){
			if(codEmpresa.length()==3){
				sb.append(" AND c.COD_EMPRESA = '"+codEmpresa+" "+"' ");	
			}else if(codEmpresa.length()==2){
				sb.append(" AND c.COD_EMPRESA = '"+codEmpresa+"  "+"' ");	
			}else{
				sb.append(" AND c.COD_EMPRESA = '"+codEmpresa+"' ");		
			}			
		}		
		if(idGrupoInf!=0){			
			sb.append(" AND c.ID_GRUPO_INFORMACION = "+idGrupoInf+" ");			
		}		
		if(FormatoUtil.isNotBlank(etapa)){
			sb.append(" AND c.ETAPA = '"+etapa+"' ");
		}		
		String jql = sb.toString();
		Query query = em.createNativeQuery(jql);	
		
		List<Object[]> lista = query.getResultList();		
		if (lista == null) {
			return Collections.EMPTY_LIST;
		} else {
			return lista;
		}		
	}
	
	/***Metodo para obtener si un usuario es administrador */	
	@Override
	public boolean esAdministradorFise(String userName){
     	boolean admin = false; 
		try {
			StringBuffer jql = new StringBuffer();
			jql.append("SELECT FISE_GEN_PKG.FISE_ES_ADM_FISE_FUN (?) FROM DUAL");
			Query query = em.createNativeQuery(jql.toString());
			query.setParameter(1,userName);			
			if(query.getSingleResult()!=null){
			 String respuesta = (String)query.getSingleResult();
			 if("SI".equals(respuesta)){
				 admin = true;  
			 }
			}			
		} catch (Exception e) {			
			e.printStackTrace();
		} finally {
			 em.close();
		 }
		return admin;
	}	
	
	//OBTENER VARIACION DE COSTOS
	@Override
	public List<VariacionCostosBean> obtenerVariacionCostosByGrupoinfoFormatoConceptofinal(Long idGrupoInfo, String formato, String conceptoFinal, String etapa){
		
		List<VariacionCostosBean> listaCostos = new ArrayList<VariacionCostosBean>();
		try {
			StringBuilder sql = new StringBuilder();
			
			sql.append(" SELECT EMP.COD_EMPRESA, EMP.DSC_CORTA_EMPRESA, ");
			sql.append("  FISE_GEN_PKG.FISE_GET_COSTO_F14AB_FUN('" + formato
					+ "',F14AB.COD_EMPRESA,F14AB.ANO_PRESENTACION,F14AB.MES_PRESENTACION,F14AB.ANO_INICIO_VIGENCIA,F14AB.ANO_FIN_VIGENCIA,F14AB.ETAPA,'" + conceptoFinal
					+ "') VALOR ");
			sql.append(" FROM ADM_EMPRESA EMP, ADM_PROC_EMPRESA PEMP, ");
			if(FiseConstants.TIPO_FORMATO_14A.equals(formato)){
				sql.append(" FISE_FORMATO_14A_C F14AB ");
			}else if(FiseConstants.TIPO_FORMATO_14B.equals(formato)){
				sql.append(" FISE_FORMATO_14B_C F14AB ");
			}
			sql.append(" WHERE EMP.COD_EMPRESA = PEMP.COD_EMPRESA ");
			sql.append(" AND PEMP.COD_PROC_SUPERVISION = 'FISE' AND PEMP.COD_FUNCION_PROC_SUPERV = 'REMISION'  ");
			//--sql.append(" AND F14AB.COD_EMPRESA = EMP.COD_EMPRESA AND F14AB.ETAPA = 'ESTABLECIDO' ");
			sql.append(" AND F14AB.COD_EMPRESA = EMP.COD_EMPRESA AND F14AB.ETAPA = '"+etapa+"' ");
			sql.append(" AND F14AB.ID_GRUPO_INFORMACION = " + idGrupoInfo );
			
			sql.append(" ORDER BY 1" );
			
			Query query = em.createNativeQuery(sql.toString());
			
			List resultado = query.getResultList();
			Iterator it=resultado.iterator();
			while(it.hasNext()){
				Object[] valor=(Object[] )it.next();
				VariacionCostosBean variacion=new VariacionCostosBean();
				variacion.setCodEmpresa((String)valor[0]);
				variacion.setDescEmpresa((String)valor[1]);
				variacion.setValor(((BigDecimal)valor[2]).setScale(2, RoundingMode.HALF_UP).toString());
				listaCostos.add(variacion);
			}
		} catch (Exception e) {			
			e.printStackTrace();
		} finally {
			 em.close();
		 }
		return listaCostos;

	}
	
	//OBTENER VARIACION DE COSTOS
	@Override
	public List<HistoricoCostosBean> obtenerHistoricoCostosByCodempresaFormato(String codEmpresa, String formato){
		
		List<HistoricoCostosBean> listaCostos = new ArrayList<HistoricoCostosBean>();
		try {
			StringBuilder sql = new StringBuilder();
			
			sql.append(" SELECT ANO_PRESENTACION || '-' || LPAD(MES_PRESENTACION, 2, '0') PERIODO, ");
			
			sql.append(" " + ("NAC".equals(codEmpresa)?"SUM(":"")
					+ "FISE_GEN_PKG.FISE_GET_COSTO_F12AB_FUN('"
					+ formato
					+ "',F12AB.COD_EMPRESA,F12AB.ANO_PRESENTACION,F12AB.MES_PRESENTACION,F12AB.ANO_EJECUCION_GASTO,F12AB.MES_EJECUCION_GASTO,F12AB.ETAPA,'RURAL_APROB') + "
					+ "FISE_GEN_PKG.FISE_GET_COSTO_F12AB_FUN('"
					+ formato
					+ "',F12AB.COD_EMPRESA,F12AB.ANO_PRESENTACION,F12AB.MES_PRESENTACION,F12AB.ANO_EJECUCION_GASTO,F12AB.MES_EJECUCION_GASTO,F12AB.ETAPA,'PROV_APROB') + "
					+ "FISE_GEN_PKG.FISE_GET_COSTO_F12AB_FUN('"
					+ formato
					+ "',F12AB.COD_EMPRESA,F12AB.ANO_PRESENTACION,F12AB.MES_PRESENTACION,F12AB.ANO_EJECUCION_GASTO,F12AB.MES_EJECUCION_GASTO,F12AB.ETAPA,'LIMA_APROB')"
					+ ("NAC".equals(codEmpresa)?")":"")+" COSTO,  ");
			
			sql.append(" " + ("NAC".equals(codEmpresa)?"SUM(":"")
					+ "FISE_GEN_PKG.FISE_GET_COSTO_F12AB_FUN('"
					+ formato
					+ "',F12AB.COD_EMPRESA,F12AB.ANO_PRESENTACION,	F12AB.MES_PRESENTACION,F12AB.ANO_EJECUCION_GASTO,F12AB.MES_EJECUCION_GASTO,F12AB.ETAPA,'RURAL_APROB_NB') + "
					+ "FISE_GEN_PKG.FISE_GET_COSTO_F12AB_FUN('"
					+ formato
					+ "',F12AB.COD_EMPRESA,F12AB.ANO_PRESENTACION,F12AB.MES_PRESENTACION,F12AB.ANO_EJECUCION_GASTO,F12AB.MES_EJECUCION_GASTO,F12AB.ETAPA,'PROV_APROB_NB') + "
					+ "FISE_GEN_PKG.FISE_GET_COSTO_F12AB_FUN('"
					+ formato
					+ "',F12AB.COD_EMPRESA,F12AB.ANO_PRESENTACION,F12AB.MES_PRESENTACION,F12AB.ANO_EJECUCION_GASTO,F12AB.MES_EJECUCION_GASTO,F12AB.ETAPA,'LIMA_APROB_NB')"
					+ ("NAC".equals(codEmpresa)?")":"") +" NUM_BENEFICIARIOS  ");
			
			sql.append(" FROM ADM_EMPRESA EMP, ADM_PROC_EMPRESA PEMP, ");
			if(FiseConstants.TIPO_FORMATO_12A.equals(formato)){
				sql.append(" FISE_FORMATO_12A_C F12AB ");
			}else if(FiseConstants.TIPO_FORMATO_12B.equals(formato)){
				sql.append(" FISE_FORMATO_12B_C F12AB ");
			}
					   
			sql.append(" WHERE EMP.COD_EMPRESA = PEMP.COD_EMPRESA ");
			sql.append(" AND PEMP.COD_PROC_SUPERVISION = 'FISE' AND PEMP.COD_FUNCION_PROC_SUPERV = 'REMISION'  ");
			sql.append(" AND F12AB.COD_EMPRESA = EMP.COD_EMPRESA AND F12AB.ETAPA = 'RECONOCIDO'  ");
			if( "NAC".equals(codEmpresa) ){
				sql.append(" GROUP BY ANO_PRESENTACION || '-' || LPAD(mes_PRESENTACION, 2, '0') " );
			}else{
				sql.append(" AND F12AB.COD_EMPRESA = '" + FormatoUtil.rellenaDerecha(codEmpresa, ' ', 4) + "' " );
			}
			sql.append(" ORDER BY 1" );
			
			
			Query query = em.createNativeQuery(sql.toString());
			
			List resultado = query.getResultList();
			Iterator it=resultado.iterator();
			while(it.hasNext()){
				Object[] valor=(Object[] )it.next();
				HistoricoCostosBean historico=new HistoricoCostosBean();
				//por ahora ya no obtiene el cod y descripcion de la empresa
				historico.setPeriodo((String)valor[0]);
				historico.setValor(((BigDecimal)valor[1]).setScale(2, RoundingMode.HALF_UP).toString());
				historico.setNroBeneficiarios(String.valueOf(((BigDecimal)valor[2]).longValue()));
				listaCostos.add(historico);
			}
		} catch (Exception e) {			
			e.printStackTrace();
		} finally {
			 em.close();
		 }
		return listaCostos;

	}	
	
	/****Metodo para verificar si realizamos la carga de información de un formato 
	 * para eso es necesario obtenerla ultima etapa y que esta etapa diferente a RECONOCIDO o ESTABLECIDO******/
	@Override
	public String obtenerUltimaEtapaFormato(String formato,String codEmpresa, 
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
	
	
}
