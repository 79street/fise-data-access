package gob.osinergmin.fise.dao.impl;

import gob.osinergmin.base.dao.impl.GenericDaoImpl;
import gob.osinergmin.fise.dao.CfgTablaDao;
import gob.osinergmin.fise.domain.CfgTabla;

import org.springframework.stereotype.Repository;

@Repository(value = "cfgTablaDaoImpl")
public class CfgTablaDaoImpl extends GenericDaoImpl implements CfgTablaDao {
	
	@Override
	public CfgTabla obtenerCfgTablaByPK(long idTabla){
		CfgTabla tabla = null;
		try{
			tabla = em.find(CfgTabla.class, idTabla);
		}catch (Exception e) {
			e.printStackTrace();
		}
		return tabla;
	}

}
