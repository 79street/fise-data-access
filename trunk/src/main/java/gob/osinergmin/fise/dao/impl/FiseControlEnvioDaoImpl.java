package gob.osinergmin.fise.dao.impl;

import gob.osinergmin.base.dao.impl.GenericDaoImpl;
import gob.osinergmin.fise.dao.FiseControlEnvioDao;
import gob.osinergmin.fise.domain.FiseControlEnvioPorGrupo;
import gob.osinergmin.fise.domain.FiseControlEnvioPorGrupoPK;
import gob.osinergmin.fise.util.FormatoUtil;

import java.sql.SQLException;

import org.springframework.stereotype.Repository;

@Repository(value = "fiseControlEnvioDaoImpl")
public class FiseControlEnvioDaoImpl extends GenericDaoImpl implements FiseControlEnvioDao {
	
	
	@Override
	public void insertarFiseControlEnvio(FiseControlEnvioPorGrupo fiseControlEnvioPorGrupo) 
			throws SQLException{
		em.persist(fiseControlEnvioPorGrupo);

	}
	
	@Override
	public void actualizarFiseControlEnvio(FiseControlEnvioPorGrupo fiseControlEnvioPorGrupo) 
			throws SQLException{
		em.merge(fiseControlEnvioPorGrupo);		
	}
	
	@Override
	public FiseControlEnvioPorGrupo obtenerFiseControlEnvioByPK(FiseControlEnvioPorGrupoPK id)
			throws SQLException {	
		id.setCodEmpresa(FormatoUtil.rellenaDerecha(id.getCodEmpresa(), ' ', 4));
		return em.find(FiseControlEnvioPorGrupo.class, id);
	}

}
