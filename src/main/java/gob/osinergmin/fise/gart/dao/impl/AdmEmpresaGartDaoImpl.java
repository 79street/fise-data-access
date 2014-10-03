package gob.osinergmin.fise.gart.dao.impl;

import gob.osinergmin.base.dao.impl.GenericDaoImpl;
import gob.osinergmin.fise.domain.AdmEmpresa;
import gob.osinergmin.fise.gart.dao.AdmEmpresaGartDao;

import java.util.List;

import javax.persistence.Query;

import org.springframework.stereotype.Repository;

@Repository(value = "admEmpresaGartDaoImpl")
public class AdmEmpresaGartDaoImpl extends GenericDaoImpl implements AdmEmpresaGartDao {

	@SuppressWarnings("unchecked")
	//@Override
	//@Transactional
	public List<AdmEmpresa> listarAdmEmpresa() {
		List<AdmEmpresa> lst = null;
		try {
			StringBuffer jql = new StringBuffer();
			jql.append(" SELECT f FROM AdmEmpresa f ");
			Query query = em.createQuery(jql.toString());
			System.out.println(query.toString());
			lst = query.getResultList();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return lst;
	}
	
}
