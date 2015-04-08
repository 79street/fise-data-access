package gob.osinergmin.fise.dao.impl;

import gob.osinergmin.base.dao.impl.GenericDaoImpl;
import gob.osinergmin.fise.dao.AdmUbigeoDao;
import gob.osinergmin.fise.domain.AdmUbigeo;

import java.util.List;

import javax.persistence.Query;

import org.springframework.stereotype.Repository;

@Repository(value = "admUbigeoDaoImpl")
public class AdmUbigeoDaoImpl extends GenericDaoImpl implements AdmUbigeoDao {

	@SuppressWarnings("unchecked")
	public List<AdmUbigeo> listarAdmUbigeo() {
		List<AdmUbigeo> lst = null;
		try {
			StringBuffer jql = new StringBuffer();
			jql.append(" SELECT f FROM AdmUbigeo f ");
			Query query = em.createQuery(jql.toString());
			System.out.println(query.toString());
			lst = query.getResultList();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			 em.close();
		 }
		return lst;
	}
	
	@SuppressWarnings("unchecked")
	public List<AdmUbigeo> listarDepartamentos() {
		List<AdmUbigeo> lst = null;
		try {
			StringBuffer jql = new StringBuffer();
			jql.append(" SELECT a FROM AdmUbigeo a WHERE a.codUbigeo LIKE '%0000' and a.estado=1 ORDER BY a.nomUbigeo");
			Query query = em.createQuery(jql.toString());
			lst = query.getResultList();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			 em.close();
		 }
		return lst;
	}

	
	@SuppressWarnings("unchecked")
	public List<AdmUbigeo> listarProvincias(String codDepartamento) {
		List<AdmUbigeo> lst = null;
		try {
			StringBuffer jql = new StringBuffer();
			jql.append(" SELECT a FROM AdmUbigeo a WHERE a.codUbigeo LIKE :codDepartamento and a.estado=1 AND a.codUbigeo NOT LIKE '%0000' ORDER BY a.nomUbigeo");
			Query query = em.createQuery(jql.toString());
			query.setParameter("codDepartamento", codDepartamento.concat("%00"));
			lst = query.getResultList();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			 em.close();
		 }
		return lst;
	}

	
	@SuppressWarnings("unchecked")
	public List<AdmUbigeo> listarDistritos(String codProvincia) {
		List<AdmUbigeo> lst = null;
		try {
			StringBuffer jql = new StringBuffer();
			jql.append(" SELECT a FROM AdmUbigeo a WHERE a.codUbigeo LIKE :codProvincia and a.estado=1 AND a.codUbigeo NOT LIKE '%00' ORDER BY a.nomUbigeo");
			Query query = em.createQuery(jql.toString());
			query.setParameter("codProvincia", codProvincia.concat("%"));
			lst = query.getResultList();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			 em.close();
		 }
		return lst;
	}

}
