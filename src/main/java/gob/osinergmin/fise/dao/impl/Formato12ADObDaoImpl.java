package gob.osinergmin.fise.dao.impl;

import gob.osinergmin.base.dao.impl.GenericDaoImpl;
import gob.osinergmin.fise.dao.Formato12ADObDao;
import gob.osinergmin.fise.domain.FiseFormato12AC;

import javax.persistence.Query;

import org.springframework.stereotype.Repository;

@Repository(value = "formato12ADObDaoImpl")
public class Formato12ADObDaoImpl extends GenericDaoImpl implements Formato12ADObDao {
	
	@Override
	public int validarFormato12A(FiseFormato12AC fiseformato12AC, String tipoFormato){
		int result = -1;
		try {
			 
			StringBuffer jql = new StringBuffer();
			jql.append("CALL FISE.FISE_GEN_PKG.FISE_VAL_FORM_PRC (?,?,?,?,?,?,?,?,?)");
			Query query = em.createNativeQuery(jql.toString());
			query.setParameter(1, tipoFormato);
			query.setParameter(2, fiseformato12AC.getId().getCodEmpresa());
			query.setParameter(3, fiseformato12AC.getId().getAnoPresentacion());
			query.setParameter(4, fiseformato12AC.getId().getMesPresentacion());
			query.setParameter(5, fiseformato12AC.getId().getAnoEjecucionGasto());
			query.setParameter(6, fiseformato12AC.getId().getMesEjecucionGasto());
			query.setParameter(7, fiseformato12AC.getId().getEtapa());
			query.setParameter(8, fiseformato12AC.getUsuarioActualizacion());
			query.setParameter(9, fiseformato12AC.getTerminalActualizacion());
			/*int v = query.executeUpdate();
			if (v == 0) {
				return new MensajeTransaccion();
			}*/
			//podemos validar posteriormente la transaccion
			result = query.executeUpdate();
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		return result;
	}
	

}
