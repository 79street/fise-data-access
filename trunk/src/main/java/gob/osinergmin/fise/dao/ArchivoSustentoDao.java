package gob.osinergmin.fise.dao;

import gob.osinergmin.fise.domain.FiseArchivosCab;
import gob.osinergmin.fise.domain.FiseArchivosDet;

import java.sql.SQLException;
import java.util.List;

public interface ArchivoSustentoDao {
	
	int llenarDatosFiseArchivosSustento(String codEmpresa,long idGrupoInf,String etapa, 
			String usuario, String terminal) throws SQLException;
	
	List<FiseArchivosCab> buscarFiseArchivosCab(String codEmpresa,
			long idGrupoInf,String etapa) throws SQLException;
	
	FiseArchivosCab obtenerFiseArchivosCab(Long id) throws SQLException;
	
	List<FiseArchivosDet> buscarFiseArchivosDet(long correlativo) throws SQLException;

}
