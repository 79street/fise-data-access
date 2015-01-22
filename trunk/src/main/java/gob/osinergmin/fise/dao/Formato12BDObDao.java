package gob.osinergmin.fise.dao;

import java.sql.SQLException;
import java.util.List;

import org.springframework.dao.DataIntegrityViolationException;

import gob.osinergmin.base.dao.GenericDao;
import gob.osinergmin.fise.domain.FiseFormato12BD;
import gob.osinergmin.fise.domain.FiseFormato12BDOb;
import gob.osinergmin.fise.domain.FiseFormato12BDObPK;


public interface Formato12BDObDao extends GenericDao {
	
	List<FiseFormato12BDOb> getLstFormatoObs(FiseFormato12BD idDetalle);
	
	Integer deleteFormatoObs(String emp,Integer anio,Integer mes,String etapa,Integer anioEjec,
			Integer mesEjec,Integer idzona,Integer item)throws DataIntegrityViolationException,Exception;
	
	long buscarMaximoItemObs12B(String codEmpresa,long anioPres,long mesPres,
			long anioEjec,long mesEjec,String etapa,long idZona) throws SQLException;
	
	void insertarFiseFormato12BObs(FiseFormato12BDOb fiseFormato12BDOb) 
			throws SQLException;
	
	FiseFormato12BDOb obtenerFiseFormato12BDOb(FiseFormato12BDObPK id) 
			throws SQLException;
	
	void eliminarFiseFormato12BDOb(FiseFormato12BDOb fiseFormato12BDOb) 
			throws SQLException;



}
