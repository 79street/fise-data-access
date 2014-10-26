package gob.osinergmin.fise.dao.impl;

import java.sql.SQLException;

import org.springframework.stereotype.Repository;

import gob.osinergmin.base.dao.impl.GenericDaoImpl;
import gob.osinergmin.fise.dao.FiseGrupoInformacionDao;
import gob.osinergmin.fise.domain.FiseGrupoInformacion;

@Repository(value = "fiseGrupoInformacionDaoImpl")
public class FiseGrupoInformacionDaoImpl extends GenericDaoImpl implements FiseGrupoInformacionDao {

	@Override
	public FiseGrupoInformacion obtenerFiseGrupoInformacionByPK(long id)
			throws SQLException {		
		return em.find(FiseGrupoInformacion.class, id);
	}

}
