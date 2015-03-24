package gob.osinergmin.fise.dao;

import gob.osinergmin.fise.domain.FiseArchivosCab;
import gob.osinergmin.fise.domain.FiseArchivosDet;
import gob.osinergmin.fise.domain.FiseArchivosDetPK;

import java.math.BigDecimal;
import java.sql.SQLException;
import java.util.List;

public interface ArchivoSustentoDao {
	
	int llenarDatosFiseArchivosSustento(String codEmpresa,long idGrupoInf,String etapa, 
			String usuario, String terminal) throws SQLException;
	
	List<FiseArchivosCab> buscarFiseArchivosCab(String codEmpresa,
			long idGrupoInf,String etapa) throws SQLException;
	
	FiseArchivosCab obtenerFiseArchivosCab(Long id) throws SQLException;
	
	List<FiseArchivosDet> buscarFiseArchivosDet(long correlativo) throws SQLException;
	
	void insertarFiseArchivosDet(FiseArchivosDet fiseArchivosDet) 
			throws SQLException;
	
	void actualizarFiseArchivosDet(FiseArchivosDet fiseArchivosDet) 
			throws SQLException;
	
	FiseArchivosDet obtenerFiseArchivosDet(FiseArchivosDetPK id) 
			throws SQLException;
	
	void eliminarFiseArchivosDet(FiseArchivosDet fiseArchivosDet) 
			throws SQLException;
	
	long buscarMaximoItemArchivo(long correlativo) throws SQLException;
	
	void eliminarFiseArchivosCab(FiseArchivosCab id) throws SQLException;
	
	List<FiseArchivosCab> listaFiseArchivosCabMensual(String codEmpresa,
			long anioPres,long mesPres,BigDecimal anioEjec,BigDecimal mesEjec,String etapa,String formato) 
					throws SQLException;
	
	List<FiseArchivosCab> listaFiseArchivosCabBienal(String codEmpresa,
			long anioPres,long mesPres,BigDecimal anioInioVig,BigDecimal anioFinVig,String etapa,String formato) 
					throws SQLException;

}
