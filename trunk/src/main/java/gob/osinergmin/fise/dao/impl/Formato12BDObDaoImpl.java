package gob.osinergmin.fise.dao.impl;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.Query;

import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Repository;

import gob.osinergmin.base.dao.impl.GenericDaoImpl;
import gob.osinergmin.fise.dao.Formato12BDObDao;
import gob.osinergmin.fise.domain.FiseFormato12BD;
import gob.osinergmin.fise.domain.FiseFormato12BDOb;

@Repository(value = "formato12BDObDaoImpl")
public class Formato12BDObDaoImpl extends GenericDaoImpl implements Formato12BDObDao{

	@SuppressWarnings("unchecked")
	@Override
	public List<FiseFormato12BDOb> getLstFormatoObs(FiseFormato12BD idDetalle) {
		List<FiseFormato12BDOb> lstReturn=new ArrayList<FiseFormato12BDOb>();
		try {
			StringBuilder sb=new StringBuilder();
			sb.append("SELECT d FROM FiseFormato12BDOb d WHERE 1=1 ");
			
			if(idDetalle.getId().getCodEmpresa() !=null && !idDetalle.getId().getCodEmpresa().isEmpty()){
				sb.append(" AND d.id.codEmpresa =:emp ");
			}
			if(idDetalle.getId().getEtapa()!=null && !idDetalle.getId().getEtapa().isEmpty()){
				sb.append(" AND d.id.etapa =:etp ");
			}
			if(idDetalle.getId().getAnoPresentacion()!=null && idDetalle.getId().getAnoPresentacion()>0){
				sb.append(" AND d.id.anoPresentacion =:aniopres ");
			}
			if(idDetalle.getId().getMesPresentacion()!=null && idDetalle.getId().getMesPresentacion()>0){
				sb.append(" AND d.id.mesPresentacion =:mespres ");
			}
			if(idDetalle.getId().getAnoEjecucionGasto()!=null && idDetalle.getId().getAnoEjecucionGasto()>0){
				sb.append(" AND d.id.anoEjecucionGasto =:anioejec ");
			}
			if(idDetalle.getId().getMesEjecucionGasto()!=null && idDetalle.getId().getMesEjecucionGasto()>0){
				sb.append(" AND d.id.mesEjecucionGasto =:mesejec ");
			}
			if(idDetalle.getId().getIdZonaBenef()!=null && idDetalle.getId().getIdZonaBenef()>0){
				sb.append(" AND d.id.idZonaBenef =:zona ");
			}
			
			
			Query query = em.createQuery(sb.toString());
			
			if(idDetalle.getId().getCodEmpresa() !=null && !idDetalle.getId().getCodEmpresa().isEmpty()){
				query.setParameter("emp", idDetalle.getId().getCodEmpresa().trim());
			}
			if(idDetalle.getId().getEtapa()!=null && !idDetalle.getId().getEtapa().isEmpty()){
				query.setParameter("etp", idDetalle.getId().getEtapa().trim());
			}
			if(idDetalle.getId().getAnoPresentacion()!=null && idDetalle.getId().getAnoPresentacion()>0){
				query.setParameter("aniopres", idDetalle.getId().getAnoPresentacion());
			}
			if(idDetalle.getId().getMesPresentacion()!=null && idDetalle.getId().getMesPresentacion()>0){
				query.setParameter("mespres", idDetalle.getId().getMesPresentacion());
			}
			if(idDetalle.getId().getAnoEjecucionGasto()!=null && idDetalle.getId().getAnoEjecucionGasto()>0){
				query.setParameter("anioejec", idDetalle.getId().getAnoEjecucionGasto());
			}
			if(idDetalle.getId().getMesEjecucionGasto()!=null && idDetalle.getId().getMesEjecucionGasto()>0){
				query.setParameter("mesejec", idDetalle.getId().getMesEjecucionGasto());
			}
			if(idDetalle.getId().getIdZonaBenef()!=null && idDetalle.getId().getIdZonaBenef()>0){
				query.setParameter("zona", idDetalle.getId().getIdZonaBenef());
			}
			
			lstReturn= query.getResultList();
			
			
		}catch(Exception e){
	      e.printStackTrace();
	     
		}finally{
			em.close();
			
		}
		return lstReturn;
	}

	@Override
	public Integer deleteFormatoObs(String emp, Integer anio, Integer mes, String etapa, Integer anioEjec, Integer mesEjec, Integer idzona, Integer item) throws DataIntegrityViolationException, Exception {
		try {
			StringBuilder sb=new StringBuilder();
			sb.append("DELETE FROM FiseFormato12BDOb c WHERE 1=1 ");
			
			if(emp !=null && !emp.isEmpty()){
				sb.append(" AND c.id.codEmpresa = '"+emp.trim()+"'");
			}
			if(etapa!=null && !etapa.isEmpty()){
				sb.append(" AND c.id.etapa =:etp ");
			}
			if(anio!=null && anio>0){
				sb.append(" AND c.id.anoPresentacion =:aniopres ");
			}
			if(mes!=null && mes>0){
				sb.append(" AND c.id.mesPresentacion =:mespres ");
			}
			if(anioEjec!=null && anioEjec>0){
				sb.append(" AND c.id.anoEjecucionGasto =:anioejec ");
			}
			if(mesEjec!=null && mesEjec>0){
				sb.append(" AND c.id.mesEjecucionGasto =:mesejec ");
			}
			if(idzona!=null && idzona>0){
				sb.append(" AND c.id.idZonaBenef =:zona ");
			}
			if(item!=null && item>0){
				sb.append(" AND c.id.itemObservacion =:itm ");
			}
			
			Query query = em.createQuery(sb.toString());
			
			/*if(emp !=null && !emp.isEmpty()){
				query.setParameter("emp", emp.trim());
			}*/
			if(etapa!=null && !etapa.isEmpty()){
				query.setParameter("etp", etapa.trim());
			}
			if(anio!=null && anio>0){
				query.setParameter("aniopres", anio);
			}
			if(mes!=null && mes>0){
				query.setParameter("mespres", mes);
			}
			if(anioEjec!=null && anioEjec>0){
				query.setParameter("anioejec", anioEjec);
			}
			if(mesEjec!=null && mesEjec>0){
				query.setParameter("mesejec", mesEjec);
			}
			if(idzona!=null && idzona>0){
				query.setParameter("zona", idzona);
			}
			if(item!=null && item>0){
				query.setParameter("itm",item);
			}
			int cant=  query.executeUpdate();
			
			return cant;
		}finally{
			em.close();
			
		}
	}

	

}
