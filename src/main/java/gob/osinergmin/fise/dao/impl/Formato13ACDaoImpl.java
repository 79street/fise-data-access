package gob.osinergmin.fise.dao.impl;

import gob.osinergmin.base.dao.impl.GenericDaoImpl;
import gob.osinergmin.fise.dao.Formato13ACDao;
import gob.osinergmin.fise.domain.FiseFormato13AC;
import gob.osinergmin.fise.domain.FiseFormato13ACPK;
import gob.osinergmin.fise.util.FormatoUtil;

import java.util.List;

import javax.persistence.Query;

import org.hibernate.exception.ConstraintViolationException;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

@Repository(value = "formato13ACDaoImpl")
public class Formato13ACDaoImpl extends GenericDaoImpl implements Formato13ACDao {

	public List<FiseFormato13AC> buscarFormato13AC(String codEmpresa, long anioDesde, long mesDesde, long anioHasta, long mesHasta, String etapa) {

		List<FiseFormato13AC> lista = null;
		try {
			String q = "SELECT t FROM FiseFormato13AC t WHERE 1=1 ";
			if (FormatoUtil.isNotBlank(codEmpresa)) {
				q = q + " AND t.id.codEmpresa = :codEmpresa ";
			}
			if (anioDesde != 0) {
				q = q + " AND t.id.anoPresentacion >= :anioDesde ";
			}
			if (mesDesde != 0) {
				q = q + " AND t.id.mesPresentacion >= :mesDesde ";
			}
			if (anioHasta != 0) {
				q = q + " AND t.id.anoPresentacion <= :anioHasta ";
			}
			if (mesHasta != 0) {
				q = q + " AND t.id.mesPresentacion <= :mesHasta ";
			}
			if (FormatoUtil.isNotBlank(etapa)) {
				q = q + " AND t.id.etapa = :etapa ";
			}
			Query query = em.createQuery(q);
			if (FormatoUtil.isNotBlank(codEmpresa)) {
				query.setParameter("codEmpresa", codEmpresa);
			}
			if (anioDesde != 0) {
				query.setParameter("anioDesde", anioDesde);
			}
			if (mesDesde != 0) {
				query.setParameter("mesDesde", mesDesde);
			}
			if (anioHasta != 0) {
				query.setParameter("anioHasta", anioHasta);
			}
			if (mesHasta != 0) {
				query.setParameter("mesHasta", mesHasta);
			}
			if (FormatoUtil.isNotBlank(etapa)) {
				query.setParameter("etapa", etapa);
			}

			lista = query.getResultList();
			System.out.println("SQL   > " + q);
			/*
			 * if(lista==null){ return Collections.EMPTY_LIST; }else{ return
			 * lista; }
			 */

		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			em.close();
		}
		return lista;
	}

	@Override
	@Transactional
	public FiseFormato13AC savecabecera(FiseFormato13AC fiseC) throws ConstraintViolationException {
		FiseFormato13AC result = null;
		try {
			em.persist(fiseC);
			System.out.println("cabecera persis::=>" + fiseC);
			result = fiseC;
		} finally {
			em.close();
		}
		return result;
	}
	
	public FiseFormato13AC obtenerFormato13ACByPK(FiseFormato13ACPK fiseFormato13ACPK){
		FiseFormato13AC formato = null;
		try{
			//em.getTransaction().begin();
			formato = em.find(FiseFormato13AC.class, fiseFormato13ACPK);
			//em.getTransaction().commit();
			//return formato;
		}catch (Exception e) {
			e.printStackTrace();
		} finally {
			 em.close();
		 }
		return formato;
	}
	
	@Override
	@Transactional
	public FiseFormato13AC updatecabecera(FiseFormato13AC fiseC) {
		FiseFormato13AC result = null;
		try {
			em.merge(fiseC);
			System.out.println("cabecera persis::=>" + fiseC);
			result = fiseC;
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			em.close();
		}
		return result;
	}
	
}
