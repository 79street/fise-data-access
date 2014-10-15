package gob.osinergmin.fise.dao.impl;

import gob.osinergmin.base.dao.impl.GenericDaoImpl;
import gob.osinergmin.fise.dao.Formato14ACDao;
import gob.osinergmin.fise.domain.FiseFormato14AC;
import gob.osinergmin.fise.domain.FiseFormato14ACPK;

import org.springframework.stereotype.Repository;

@Repository(value = "formato14ACDaoImpl")
public class Formato14ACDaoImpl extends GenericDaoImpl implements Formato14ACDao {
	
	@Override
	public FiseFormato14AC obtenerFormato14ACByPK(FiseFormato14ACPK fiseFormato14ACPK){
		FiseFormato14AC formato = null;
		try{
			//em.getTransaction().begin();
			formato = em.find(FiseFormato14AC.class, fiseFormato14ACPK);
			//em.getTransaction().commit();
			//return formato;
		}catch (Exception e) {
			e.printStackTrace();
		}
		return formato;
	}

}
