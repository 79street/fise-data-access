package gob.osinergmin.fise.dao.impl;

import gob.osinergmin.base.dao.impl.GenericDaoImpl;
import gob.osinergmin.fise.dao.ResumenCostosDao;
import gob.osinergmin.fise.util.FormatoUtil;

import java.sql.SQLException;
import java.util.Collections;
import java.util.List;

import javax.persistence.Query;

import org.springframework.stereotype.Repository;

@Repository(value = "resumenCostosDaoImpl")
public class ResumenCostosDaoImpl extends GenericDaoImpl implements ResumenCostosDao {

	
	@SuppressWarnings("unchecked")
	@Override
	public List<Object[]> listarResumenCostosF14A(String codEmpresa,
			Long idGrupoInf) throws SQLException{
		StringBuilder sb = new StringBuilder();	
		sb.append(" SELECT");
		sb.append(" EMP.DSC_CORTA_EMPRESA AS empresa, ");//0		
		sb.append(" F14A.ANO_PRESENTACION || '-' || FISE_GEN_PKG.FISE_NOMBRE_MES_FUN(F14A.MES_PRESENTACION) AS periodo ,  ");//1
		sb.append(" FISE_GEN_PKG.FISE_GET_COSTO_F14A_FUN(F14A.COD_EMPRESA,F14A.ANO_PRESENTACION,"
				+ " F14A.MES_PRESENTACION,F14A.ANO_INICIO_VIGENCIA,"
				+ " F14A.ANO_FIN_VIGENCIA,F14A.ETAPA,'EMP_RURAL_SOL') AS emp_Rural_Sol, ");//2		
		sb.append(" FISE_GEN_PKG.FISE_GET_COSTO_F14A_FUN(F14A.COD_EMPRESA,F14A.ANO_PRESENTACION,"
				+ " F14A.MES_PRESENTACION,F14A.ANO_INICIO_VIGENCIA,"
				+ " F14A.ANO_FIN_VIGENCIA,F14A.ETAPA,'EMP_PROV_SOL') AS emp_Prov_Sol, ");//3
		sb.append(" FISE_GEN_PKG.FISE_GET_COSTO_F14A_FUN(F14A.COD_EMPRESA,F14A.ANO_PRESENTACION,"
				+ " F14A.MES_PRESENTACION,F14A.ANO_INICIO_VIGENCIA,"
				+ " F14A.ANO_FIN_VIGENCIA,F14A.ETAPA,'EMP_LIMA_SOL') AS emp_Lima_Sol, ");//4
		sb.append(" FISE_GEN_PKG.FISE_GET_COSTO_F14A_FUN(F14A.COD_EMPRESA,F14A.ANO_PRESENTACION,"
				+ " F14A.MES_PRESENTACION,F14A.ANO_INICIO_VIGENCIA,"
				+ " F14A.ANO_FIN_VIGENCIA,F14A.ETAPA,'EMP_RURAL_APROB') AS emp_Rural_Aprob, ");//5
		sb.append(" FISE_GEN_PKG.FISE_GET_COSTO_F14A_FUN(F14A.COD_EMPRESA,F14A.ANO_PRESENTACION,"
				+ " F14A.MES_PRESENTACION,F14A.ANO_INICIO_VIGENCIA,"
				+ " F14A.ANO_FIN_VIGENCIA,F14A.ETAPA,'EMP_PROV_APROB') AS emp_Prov_Aprob, ");//6
		sb.append(" FISE_GEN_PKG.FISE_GET_COSTO_F14A_FUN(F14A.COD_EMPRESA,F14A.ANO_PRESENTACION,"
				+ " F14A.MES_PRESENTACION,F14A.ANO_INICIO_VIGENCIA,"
				+ " F14A.ANO_FIN_VIGENCIA,F14A.ETAPA,'EMP_LIMA_APROB') AS emp_Lima_Aprob, ");//7
		sb.append(" FISE_GEN_PKG.FISE_GET_COSTO_F14A_FUN(F14A.COD_EMPRESA,F14A.ANO_PRESENTACION,"
				+ " F14A.MES_PRESENTACION,F14A.ANO_INICIO_VIGENCIA,"
				+ " F14A.ANO_FIN_VIGENCIA,F14A.ETAPA,'GLP_RURAL_SOL') AS glp_Rural_Sol, ");//8
		sb.append(" FISE_GEN_PKG.FISE_GET_COSTO_F14A_FUN(F14A.COD_EMPRESA,F14A.ANO_PRESENTACION,"
				+ " F14A.MES_PRESENTACION,F14A.ANO_INICIO_VIGENCIA,"
				+ " F14A.ANO_FIN_VIGENCIA,F14A.ETAPA,'GLP_PROV_SOL') AS glp_Prov_Sol, ");//9
		sb.append(" FISE_GEN_PKG.FISE_GET_COSTO_F14A_FUN(F14A.COD_EMPRESA,F14A.ANO_PRESENTACION,"
				+ " F14A.MES_PRESENTACION,F14A.ANO_INICIO_VIGENCIA,"
				+ " F14A.ANO_FIN_VIGENCIA,F14A.ETAPA,'GLP_LIMA_SOL') AS glp_Lima_Sol, ");//10
		sb.append(" FISE_GEN_PKG.FISE_GET_COSTO_F14A_FUN(F14A.COD_EMPRESA,F14A.ANO_PRESENTACION,"
				+ " F14A.MES_PRESENTACION,F14A.ANO_INICIO_VIGENCIA,"
				+ " F14A.ANO_FIN_VIGENCIA,F14A.ETAPA,'GLP_RURAL_APROB') AS glp_Rural_Aprob, ");//11
		sb.append(" FISE_GEN_PKG.FISE_GET_COSTO_F14A_FUN(F14A.COD_EMPRESA,F14A.ANO_PRESENTACION,"
				+ " F14A.MES_PRESENTACION,F14A.ANO_INICIO_VIGENCIA,"
				+ " F14A.ANO_FIN_VIGENCIA,F14A.ETAPA,'GLP_PROV_APROB') AS glp_Prov_Aprob, ");//12
		sb.append(" FISE_GEN_PKG.FISE_GET_COSTO_F14A_FUN(F14A.COD_EMPRESA,F14A.ANO_PRESENTACION,"
				+ " F14A.MES_PRESENTACION,F14A.ANO_INICIO_VIGENCIA,"
				+ " F14A.ANO_FIN_VIGENCIA,F14A.ETAPA,'GLP_LIMA_APROB') AS glp_Lima_Aprob ");//13		
		sb.append(" FROM ADM_EMPRESA EMP, ADM_PROC_EMPRESA PEMP, FISE_FORMATO_14A_C F14A");		
		sb.append(" WHERE EMP.COD_EMPRESA = PEMP.COD_EMPRESA ");		
		sb.append(" AND PEMP.COD_PROC_SUPERVISION = 'FISE' ");		
		sb.append(" AND PEMP.COD_FUNCION_PROC_SUPERV = 'REMISION' ");	
		sb.append(" AND F14A.COD_EMPRESA = EMP.COD_EMPRESA ");
		sb.append(" AND F14A.ETAPA = 'ESTABLECIDO' ");	
		if(FormatoUtil.isNotBlank(codEmpresa)){
		    sb.append(" AND F14A.COD_EMPRESA = decode('"+codEmpresa+"', 'TODOS', F14A.COD_EMPRESA, '"+codEmpresa+"') ");		
		}		
		if(idGrupoInf!=0){			
			sb.append(" AND F14A.ID_GRUPO_INFORMACION = "+idGrupoInf+" ");			
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
	public List<Object[]> listarResumenCostosF12A(String codEmpresa,
			Long idGrupoInf) throws SQLException{
		StringBuilder sb = new StringBuilder();	
		sb.append(" SELECT");
		sb.append(" EMP.DSC_CORTA_EMPRESA AS empresa, ");//0		
		sb.append(" F12A.ANO_PRESENTACION || '-' || FISE_GEN_PKG.FISE_NOMBRE_MES_FUN(F12A.MES_PRESENTACION) AS periodo ,  ");//1
		
		sb.append(" FISE_GEN_PKG.FISE_GET_COSTO_F12AB_FUN('F12A',F12A.COD_EMPRESA,F12A.ANO_PRESENTACION,"
				+ " F12A.MES_PRESENTACION,F12A.ANO_EJECUCION_GASTO,"
				+ " F12A.MES_EJECUCION_GASTO,F12A.ETAPA,'RURAL_SOL') AS rural_Sol, ");//2
		sb.append(" FISE_GEN_PKG.FISE_GET_COSTO_F12AB_FUN('F12A',F12A.COD_EMPRESA,F12A.ANO_PRESENTACION,"
				+ " F12A.MES_PRESENTACION,F12A.ANO_EJECUCION_GASTO,"
				+ " F12A.MES_EJECUCION_GASTO,F12A.ETAPA,'PROV_SOL') AS prov_Sol, ");//3
		sb.append(" FISE_GEN_PKG.FISE_GET_COSTO_F12AB_FUN('F12A',F12A.COD_EMPRESA,F12A.ANO_PRESENTACION,"
				+ " F12A.MES_PRESENTACION,F12A.ANO_EJECUCION_GASTO,"
				+ " F12A.MES_EJECUCION_GASTO,F12A.ETAPA,'LIMA_SOL') AS lima_Sol, ");//4
		sb.append(" FISE_GEN_PKG.FISE_GET_COSTO_F12AB_FUN('F12A',F12A.COD_EMPRESA,F12A.ANO_PRESENTACION,"
				+ " F12A.MES_PRESENTACION,F12A.ANO_EJECUCION_GASTO,"
				+ " F12A.MES_EJECUCION_GASTO,F12A.ETAPA,'RURAL_APROB') AS rural_Aprob, ");//5
		sb.append(" FISE_GEN_PKG.FISE_GET_COSTO_F12AB_FUN('F12A',F12A.COD_EMPRESA,F12A.ANO_PRESENTACION,"
				+ " F12A.MES_PRESENTACION,F12A.ANO_EJECUCION_GASTO,"
				+ " F12A.MES_EJECUCION_GASTO,F12A.ETAPA,'PROV_APROB') AS prov_Aprob, ");//6
		sb.append(" FISE_GEN_PKG.FISE_GET_COSTO_F12AB_FUN('F12A',F12A.COD_EMPRESA,F12A.ANO_PRESENTACION,"
				+ " F12A.MES_PRESENTACION,F12A.ANO_EJECUCION_GASTO,"
				+ " F12A.MES_EJECUCION_GASTO,F12A.ETAPA,'LIMA_APROB') AS lima_Aprob ");//7		
		sb.append(" FROM ADM_EMPRESA EMP, ADM_PROC_EMPRESA PEMP, FISE_FORMATO_12A_C F12A");		
		sb.append(" WHERE EMP.COD_EMPRESA = PEMP.COD_EMPRESA ");		
		sb.append(" AND PEMP.COD_PROC_SUPERVISION = 'FISE' ");		
		sb.append(" AND PEMP.COD_FUNCION_PROC_SUPERV = 'REMISION' ");	
		sb.append(" AND F12A.COD_EMPRESA = EMP.COD_EMPRESA ");
		sb.append(" AND F12A.ETAPA = 'RECONOCIDO' ");	
		if(FormatoUtil.isNotBlank(codEmpresa)){
		    sb.append(" AND F12A.COD_EMPRESA = decode('"+codEmpresa+"', 'TODOS', F12A.COD_EMPRESA, '"+codEmpresa+"') ");		
		}
		if(idGrupoInf!=0){			
			sb.append(" AND F12A.ID_GRUPO_INFORMACION = "+idGrupoInf+" ");			
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
	public List<Object[]> listarResumenCostosF12B(String codEmpresa,
			Long idGrupoInf) throws SQLException{
		StringBuilder sb = new StringBuilder();	
		sb.append(" SELECT");
		sb.append(" EMP.DSC_CORTA_EMPRESA AS empresa, ");//0		
		sb.append(" F12B.ANO_PRESENTACION || '-' || FISE_GEN_PKG.FISE_NOMBRE_MES_FUN(F12B.MES_PRESENTACION) AS periodo ,  ");//1
		
		sb.append(" FISE_GEN_PKG.FISE_GET_COSTO_F12AB_FUN('F12B',F12B.COD_EMPRESA,F12B.ANO_PRESENTACION,"
				+ " F12B.MES_PRESENTACION,F12B.ANO_EJECUCION_GASTO,"
				+ " F12B.MES_EJECUCION_GASTO,F12B.ETAPA,'RURAL_SOL') AS rural_Sol, ");//2
		sb.append(" FISE_GEN_PKG.FISE_GET_COSTO_F12AB_FUN('F12B',F12B.COD_EMPRESA,F12B.ANO_PRESENTACION,"
				+ " F12B.MES_PRESENTACION,F12B.ANO_EJECUCION_GASTO,"
				+ " F12B.MES_EJECUCION_GASTO,F12B.ETAPA,'PROV_SOL') AS prov_Sol, ");//3
		sb.append(" FISE_GEN_PKG.FISE_GET_COSTO_F12AB_FUN('F12B',F12B.COD_EMPRESA,F12B.ANO_PRESENTACION,"
				+ " F12B.MES_PRESENTACION,F12B.ANO_EJECUCION_GASTO,"
				+ " F12B.MES_EJECUCION_GASTO,F12B.ETAPA,'LIMA_SOL') AS lima_Sol, ");//4
		sb.append(" FISE_GEN_PKG.FISE_GET_COSTO_F12AB_FUN('F12B',F12B.COD_EMPRESA,F12B.ANO_PRESENTACION,"
				+ " F12B.MES_PRESENTACION,F12B.ANO_EJECUCION_GASTO,"
				+ " F12B.MES_EJECUCION_GASTO,F12B.ETAPA,'RURAL_APROB') AS rural_Aprob, ");//5
		sb.append(" FISE_GEN_PKG.FISE_GET_COSTO_F12AB_FUN('F12B',F12B.COD_EMPRESA,F12B.ANO_PRESENTACION,"
				+ " F12B.MES_PRESENTACION,F12B.ANO_EJECUCION_GASTO,"
				+ " F12B.MES_EJECUCION_GASTO,F12B.ETAPA,'PROV_APROB') AS prov_Aprob, ");//6
		sb.append(" FISE_GEN_PKG.FISE_GET_COSTO_F12AB_FUN('F12B',F12B.COD_EMPRESA,F12B.ANO_PRESENTACION,"
				+ " F12B.MES_PRESENTACION,F12B.ANO_EJECUCION_GASTO,"
				+ " F12B.MES_EJECUCION_GASTO,F12B.ETAPA,'LIMA_APROB') AS lima_Aprob ");//7		
		sb.append(" FROM ADM_EMPRESA EMP, ADM_PROC_EMPRESA PEMP, FISE_FORMATO_12B_C F12B");		
		sb.append(" WHERE EMP.COD_EMPRESA = PEMP.COD_EMPRESA ");		
		sb.append(" AND PEMP.COD_PROC_SUPERVISION = 'FISE' ");		
		sb.append(" AND PEMP.COD_FUNCION_PROC_SUPERV = 'REMISION' ");	
		sb.append(" AND F12B.COD_EMPRESA = EMP.COD_EMPRESA ");
		sb.append(" AND F12B.ETAPA = 'RECONOCIDO' ");		
		if(FormatoUtil.isNotBlank(codEmpresa)){
		    sb.append(" AND F12B.COD_EMPRESA = decode('"+codEmpresa+"', 'TODOS', F12B.COD_EMPRESA, '"+codEmpresa+"') ");		
		}
		if(idGrupoInf!=0){			
			sb.append(" AND F12B.ID_GRUPO_INFORMACION = "+idGrupoInf+" ");			
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
	
	
	
	
}
