package gob.osinergmin.fise.dao;

import gob.osinergmin.base.dao.GenericDao;
import gob.osinergmin.fise.domain.FiseFormato12BC;
import gob.osinergmin.fise.domain.FiseFormato12BCPK;

import java.sql.SQLException;
import java.util.List;

import org.springframework.dao.DataIntegrityViolationException;

public interface Formato12BCDao extends GenericDao{
	
	List<FiseFormato12BC> getLstFormatoCabecera(String codemp,Integer anioDesde ,
			Integer mesDesde,Integer anioHasta,Integer mesHasta,String etapa );
	
	FiseFormato12BC getFormatoCabeceraById(FiseFormato12BCPK id);
	
	FiseFormato12BC saveFormatoCabecera(FiseFormato12BC formato) throws DataIntegrityViolationException,Exception;
	
	Integer updateFormatoCabecera(FiseFormato12BC formato)throws DataIntegrityViolationException,Exception;
	
	Integer deleteFormatoCabecera(FiseFormato12BCPK id)throws DataIntegrityViolationException,Exception;
	
	List<FiseFormato12BC> buscarFormato12BCReenvio(String codEmpresa, Integer anioPres, 
			Integer mesPres, String etapa) throws SQLException;

	boolean existeFormato12BC(FiseFormato12BC fiseFormato12BC);
	
	List<FiseFormato12BC> buscarFiseFormato12BCReporteObs(String codEmpresa, long idGrupoInf, 
			String etapa) throws SQLException;
	
	
}
