package gob.osinergmin.fise.dao;

import gob.osinergmin.base.dao.GenericDao;
import gob.osinergmin.fise.domain.FiseObservacion;

import java.util.List;

public interface FiseObservacionDao extends GenericDao {
	
	List<FiseObservacion> listarFiseObservacion();

}
