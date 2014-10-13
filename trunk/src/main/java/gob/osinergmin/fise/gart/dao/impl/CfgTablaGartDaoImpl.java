package gob.osinergmin.fise.gart.dao.impl;

import gob.osinergmin.base.dao.impl.GenericDaoImpl;
import gob.osinergmin.fise.domain.CfgTabla;
import gob.osinergmin.fise.gart.dao.CfgTablaGartDao;

import org.springframework.stereotype.Repository;

@Repository(value = "cfgTablaGartDaoImpl")
public class CfgTablaGartDaoImpl extends GenericDaoImpl implements CfgTablaGartDao {
	
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
