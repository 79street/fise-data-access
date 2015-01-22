package gob.osinergmin.fise.gart.service;

import gob.osinergmin.fise.bean.Formato12BCBean;
import gob.osinergmin.fise.domain.FiseFormato12BC;
import gob.osinergmin.fise.domain.FiseFormato12BCPK;
import gob.osinergmin.fise.domain.FiseFormato12BD;
import gob.osinergmin.fise.domain.FiseFormato12BDOb;





import java.util.HashMap;
import java.util.List;

import org.springframework.dao.DataIntegrityViolationException;

public interface Formato12BGartService {

	//CABECERA
	List<FiseFormato12BC> getLstFormatoCabecera(String codemp,Integer anioDesde ,Integer mesDesde,Integer anioHasta,Integer mesHasta,String etapa );
	FiseFormato12BC getFormatoCabeceraById(FiseFormato12BCPK id);
	FiseFormato12BC saveFormatoCabecera(FiseFormato12BC formato)throws DataIntegrityViolationException,Exception;
	Integer updateFormatoCabecera(FiseFormato12BC formato)throws DataIntegrityViolationException,Exception;
	Integer deleteFormatoCabecera(FiseFormato12BCPK id)throws DataIntegrityViolationException,Exception;
	
	//DETALLE
	List<FiseFormato12BD> getLstFormatoDetalle(FiseFormato12BCPK idcabecera);
	FiseFormato12BD getFormatoDetalleById(FiseFormato12BCPK id,Integer idzona);
	FiseFormato12BD saveFormatoDetalle(FiseFormato12BD formato)throws DataIntegrityViolationException,Exception;
	Integer updateFormatoDetalle(FiseFormato12BD formato)throws DataIntegrityViolationException,Exception;
	Integer deleteFormatoDetalle(String emp,Integer anio,Integer mes,String etapa,Integer anioEjec,Integer mesEjec,Integer idzona)throws DataIntegrityViolationException,Exception;

    //OBS
	List<FiseFormato12BDOb> getLstFormatoObs(FiseFormato12BD idDetalle);
	Integer deleteFormatoObs(String emp,Integer anio,Integer mes,String etapa,Integer anioEjec,Integer mesEjec,Integer idzona,Integer item)throws DataIntegrityViolationException,Exception;

	 HashMap<String, Object> mapearParametrosFormato12B(Formato12BCBean formato12BBean);
	 Formato12BCBean estructurarFormato12BBeanByFiseFormato12BC(FiseFormato12BC formato);

	 String modificarEnvioDefinitivoFormato12BC(String user,String terminal, FiseFormato12BC fiseFormato12BC) throws Exception;
	 
	 void eliminarObservaciones12B(List<FiseFormato12BDOb> listaObs) throws Exception ;
	 
	 String insertarObservacion12B(String codEmpresa,Integer anioPres,Integer mesPres,
				Integer anioEjec,Integer mesEjec,String etapa,Integer idZona, 
				String desObservacion,String user,String terminal) throws Exception;
	 
	 String eliminarObservacion12B(String codEmpresa,Integer anioPres,Integer mesPres,
				Integer anioEjec,Integer mesEjec,String etapa,Integer idZona, 
				String idObservacion,Integer itemObservacion) throws Exception;


}
