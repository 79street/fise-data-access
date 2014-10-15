package gob.osinergmin.fise.dao.impl;

import gob.osinergmin.base.dao.impl.GenericDaoImpl;
import gob.osinergmin.fise.dao.AdmEmpresaDao;
import gob.osinergmin.fise.domain.AdmEmpresa;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import javax.persistence.Query;

import org.springframework.stereotype.Repository;

@Repository(value = "admEmpresaDaoImpl")
public class AdmEmpresaDaoImpl extends GenericDaoImpl implements AdmEmpresaDao {

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
	
public List<AdmEmpresa> getEmpresaFise(String codProceso, String codFuncion,String cadenaEmpresas) {
		
		List<AdmEmpresa> lst = new ArrayList<AdmEmpresa>();
		try {
			StringBuilder sql = new StringBuilder();
			
			sql.append("SELECT trim(EMP.COD_EMPRESA) AS COD_EMPRESA, trim(EMP.DSC_CORTA_EMPRESA) AS DSC_CORTA_EMPRESA");
			sql.append(" FROM ADMIN_GART.ADM_EMPRESA EMP");
			sql.append(" JOIN ADMIN_GART.ADM_PROC_EMPRESA PEMP");
			sql.append(" ON EMP.COD_EMPRESA = PEMP.COD_EMPRESA");
			sql.append(" AND PEMP.COD_PROC_SUPERVISION = '"+codProceso+"'");
			sql.append(" AND PEMP.COD_FUNCION_PROC_SUPERV = '"+codFuncion+"'");		
			sql.append(" WHERE 1=1 ");
			
			if(!cadenaEmpresas.trim().equals(""))
				sql.append(" AND (TRIM(EMP.COD_EMPRESA) IN (").append(cadenaEmpresas.trim()).append(") OR 'OSI' IN (").append(cadenaEmpresas.trim()).append("))");
			
			sql.append(" ORDER BY EMP.DSC_CORTA_EMPRESA");
		
			//System.out.println("QUERY:"+sql.toString());
			
			Query query = em.createNativeQuery(sql.toString());
			
			List resultado = query.getResultList();
			Iterator it=resultado.iterator();
			while(it.hasNext()){
				Object[] valor=(Object[] )it.next();
				
				AdmEmpresa empresa=new AdmEmpresa();
				empresa.setCodEmpresa((String)valor[0]);
				empresa.setDscEmpresa((String)valor[1]);
				lst.add(empresa);
			}
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}

		return lst;
	}
	
}
