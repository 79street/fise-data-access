package gob.osinergmin.fise.dao.impl;

import gob.osinergmin.base.dao.impl.GenericDaoImpl;
import gob.osinergmin.fise.bean.Formato12A12BGeneric;
import gob.osinergmin.fise.bean.Formato12C12D13Generic;
import gob.osinergmin.fise.bean.Formato14Generic;
import gob.osinergmin.fise.dao.CommonDao;

import java.math.BigDecimal;

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

}
