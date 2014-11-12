package gob.osinergmin.fise.dao.impl;

import gob.osinergmin.base.dao.impl.GenericDaoImpl;
import gob.osinergmin.fise.bean.CorreoBean;
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
	
}
