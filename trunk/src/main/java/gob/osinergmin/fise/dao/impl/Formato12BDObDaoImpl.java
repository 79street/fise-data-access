package gob.osinergmin.fise.dao.impl;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.Query;

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

	

}
