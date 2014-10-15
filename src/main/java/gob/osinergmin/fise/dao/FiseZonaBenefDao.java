package gob.osinergmin.fise.dao;

import gob.osinergmin.base.dao.GenericDao;
import gob.osinergmin.fise.domain.FiseZonaBenef;

import java.util.List;

public interface FiseZonaBenefDao extends GenericDao {
	
	List<FiseZonaBenef> listarFiseZonaBenef();
	FiseZonaBenef obtenerFiseZonaBenefByPK(long id);

}
