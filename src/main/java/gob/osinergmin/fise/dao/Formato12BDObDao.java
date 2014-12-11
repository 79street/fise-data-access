package gob.osinergmin.fise.dao;

import java.util.List;

import org.springframework.dao.DataIntegrityViolationException;

import gob.osinergmin.base.dao.GenericDao;
import gob.osinergmin.fise.domain.FiseFormato12BD;
import gob.osinergmin.fise.domain.FiseFormato12BDOb;


public interface Formato12BDObDao extends GenericDao {
	
	List<FiseFormato12BDOb> getLstFormatoObs(FiseFormato12BD idDetalle);
	
	Integer deleteFormatoObs(String emp,Integer anio,Integer mes,String etapa,Integer anioEjec,Integer mesEjec,Integer idzona,Integer item)throws DataIntegrityViolationException,Exception;



}
