package gob.osinergmin.fise.dao.impl;

import gob.osinergmin.base.dao.impl.GenericDaoImpl;
import gob.osinergmin.fise.dao.FiseCargoFijoDao;
import gob.osinergmin.fise.domain.FiseMcargofijo;
import gob.osinergmin.fise.domain.FiseMcargofijoPK;
import gob.osinergmin.fise.util.FormatoUtil;

import java.sql.SQLException;
import java.util.Collections;
import java.util.List;

import javax.persistence.Query;

import org.springframework.stereotype.Repository;

@Repository(value = "fiseCargoFijoDaoImpl")
public class FiseCargoFijoDaoImpl extends GenericDaoImpl implements FiseCargoFijoDao {

	
	@Override
	public void insertarFiseCargoFijo(FiseMcargofijo fiseCargoFijo) 
			throws SQLException{
		em.persist(fiseCargoFijo);
		
	}

	@Override
	public void actualizarFiseObservacion(FiseMcargofijo fiseCargoFijo) 
			throws SQLException{
		em.merge(fiseCargoFijo);		
	}
	
	
	@Override
	public FiseMcargofijo obtenerFiseCargoFijo(FiseMcargofijoPK id) 
			throws SQLException{
		return em.find(FiseMcargofijo.class, id);		
	}
	
	@SuppressWarnings("unchecked")	
	@Override
	public List<FiseMcargofijo> buscarFiseCargoFijo(String codEmpresa, Long anioRep,Long mesRep) 
			throws SQLException{
		
		String q = "SELECT m FROM " + FiseMcargofijo.class.getName()
				+ " m WHERE 1=1 ";
		if(FormatoUtil.isNotBlank(codEmpresa)){ 
			q = q.concat(" AND m.id.empcod =:codEmpresa ");
		}			
		if(anioRep !=0){ 
			q = q.concat(" AND m.id.faniorep =:anio ");
		}
		if(mesRep !=0){ 
			q = q.concat(" AND m.id.fmesrep =:mes ");
		}	
		Query query = em.createQuery(q); 
		if(FormatoUtil.isNotBlank(codEmpresa)){
			String codEmpreCompleta = FormatoUtil.rellenaDerecha(codEmpresa, ' ', 4);
			query.setParameter("codEmpresa", codEmpreCompleta);
		}			
		if(anioRep !=0){ 			
			query.setParameter("anio", anioRep);
		}
		if(mesRep !=0){ 			
			query.setParameter("mes", mesRep);
		}	
		List<FiseMcargofijo> lista= query.getResultList();
		 if(lista==null){
			 return Collections.EMPTY_LIST;
		 }else{
			 return lista;
		 }	
	}
	
	
	
}
