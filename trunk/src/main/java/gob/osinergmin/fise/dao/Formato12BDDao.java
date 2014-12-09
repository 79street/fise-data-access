package gob.osinergmin.fise.dao;

import java.util.List;

import org.springframework.dao.DataIntegrityViolationException;

import gob.osinergmin.base.dao.GenericDao;
import gob.osinergmin.fise.domain.FiseFormato12BCPK;
import gob.osinergmin.fise.domain.FiseFormato12BD;

public interface Formato12BDDao extends GenericDao{
	
	List<FiseFormato12BD> getLstFormatoDetalle(FiseFormato12BCPK idcabecera);
	FiseFormato12BD getFormatoDetalleById(FiseFormato12BCPK id,Integer idzona);
	FiseFormato12BD saveFormatoDetalle(FiseFormato12BD formato)throws DataIntegrityViolationException,Exception;;
	Integer updateFormatoDetalle(FiseFormato12BD formato)throws DataIntegrityViolationException,Exception;;
	Integer deleteFormatoDetalle(String emp,Integer anio,Integer mes,String etapa,Integer anioEjec,Integer mesEjec,Integer idzona)throws DataIntegrityViolationException,Exception;

}
