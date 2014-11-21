package gob.osinergmin.fise.dao.impl;

import gob.osinergmin.base.dao.impl.GenericDaoImpl;
import gob.osinergmin.fise.bean.CorreoBean;
import gob.osinergmin.fise.bean.CumplimientoReportBean;
import gob.osinergmin.fise.bean.Formato12A12BGeneric;
import gob.osinergmin.fise.bean.Formato12C12D13Generic;
import gob.osinergmin.fise.bean.Formato14Generic;
import gob.osinergmin.fise.dao.CommonDao;

import java.math.BigDecimal;
import java.util.ArrayList;
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
			jql.append("SELECT FISE.FISE_GEN_PKG.FISE_CORR_ARCH_FUN FROM DUAL");
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
			jql.append("CALL FISE.FISE_GEN_PKG.FISE_VAL_FORM_PRC (?,?,?,?,?,?,?,?,?)");
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
			jql.append("CALL FISE.FISE_GEN_PKG.FISE_VAL_FORM_PRC3 (?,?,?,?,?,?,?)");
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
			jql.append("CALL FISE.FISE_GEN_PKG.FISE_VAL_FORM_PRC2 (?,?,?,?,?,?,?,?,?)");
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
			// TODO: handle exception
			e.printStackTrace();
		} finally {
			 em.close();
		 }

		return listaCorreo;

	}
	
	/**Para obtener el id del grupo de informacion**/
	
	@Override
	public Long obtenerIdGrupoInformacion(Long anioPresentacion,Long mesPresentacion){
     	Long valor = new Long(0); 
		try {
			StringBuffer jql = new StringBuffer();
			jql.append("SELECT FISE_GEN_PKG.FISE_GET_GRUPO_FUN (?,?) FROM DUAL");
			Query query = em.createNativeQuery(jql.toString());
			query.setParameter(1, anioPresentacion);
			query.setParameter(2, mesPresentacion);
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
			jql.append("SELECT FISE.FISE_GEN_PKG.FISE_ESTADO_FORMATO_FUN(?,?,?,?,?) FROM DUAL");
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
			sql.append("SELECT trim(EMP.COD_EMPRESA) AS COD_EMPRESA,trim(EMP.DSC_CORTA_EMPRESA) AS DSC_CORTA_EMPRESA,");
			sql.append("fise_gen_pkg.fise_enviado_fun(EMP.COD_EMPRESA,'F12A',");
			sql.append(p_ano).append(",").append(p_mes).append(",'").append(p_etapa).append("') F12A,");
			sql.append("fise_gen_pkg.fise_enviado_fun(EMP.COD_EMPRESA,'F12B',");
			sql.append(p_ano).append(",").append(p_mes).append(",'").append(p_etapa).append("') F12B,");
			sql.append("fise_gen_pkg.fise_enviado_fun(EMP.COD_EMPRESA,'F12C',");
			sql.append(p_ano).append(",").append(p_mes).append(",'").append(p_etapa).append("') F12C,");
			sql.append("fise_gen_pkg.fise_enviado_fun(EMP.COD_EMPRESA,'F12F',");
			sql.append(p_ano).append(",").append(p_mes).append(",'").append(p_etapa).append("') F12D,");
			sql.append("fise_gen_pkg.fise_enviado_fun(EMP.COD_EMPRESA,'F13A',");
			sql.append(p_ano).append(",").append(p_mes).append(",'").append(p_etapa).append("') F13A,");
			sql.append("fise_gen_pkg.fise_enviado_fun(EMP.COD_EMPRESA,'F14A',");
			sql.append(p_ano).append(",").append(p_mes).append(",'").append(p_etapa).append("') F14A,");
			sql.append("fise_gen_pkg.fise_enviado_fun(EMP.COD_EMPRESA,'F14B',");
			sql.append(p_ano).append(",").append(p_mes).append(",'").append(p_etapa).append("') F14B,");
			sql.append("fise_gen_pkg.fise_enviado_fun(EMP.COD_EMPRESA,'F14C',");
			sql.append(p_ano).append(",").append(p_mes).append(",'").append(p_etapa).append("') F14C");
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
				cumplimiento.setFormat12A((String)valor[2]);
				cumplimiento.setFormat12B((String)valor[3]);
				cumplimiento.setFormat12C((String)valor[4]);
				cumplimiento.setFormat12D((String)valor[5]);
				cumplimiento.setFormat13A((String)valor[6]);
				cumplimiento.setFormat14A((String)valor[7]);
				cumplimiento.setFormat14B((String)valor[8]);
				cumplimiento.setFormat14C((String)valor[9]);
				lista.add(cumplimiento);
			}
		} catch (Exception e) {
			// TODO: handle exception
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
			jql.append("SELECT FISE.FISE_GEN_PKG.FISE_EN_EL_PLAZO_FUN(?,?,?,?,?,?) FROM DUAL");
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
	
}
