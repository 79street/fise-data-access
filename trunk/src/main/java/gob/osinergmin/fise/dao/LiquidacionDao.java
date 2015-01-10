package gob.osinergmin.fise.dao;

import gob.osinergmin.fise.domain.FiseLiquidacione;
import gob.osinergmin.fise.domain.FiseLiquidacionesMotivosNo;
import gob.osinergmin.fise.domain.FiseLiquidacionesMotivosNoPK;

import java.sql.SQLException;
import java.util.List;

public interface LiquidacionDao {
	
	int llenarDatosFiseLiquidacion(String codEmpresa,long idGrupoInf, 
			String usuario, String terminal) throws SQLException;
	
	String obtenerUltimaEtapa(String formato,String codEmpresa, 
			long anioPres, long mesPres, long anioEjec,
			long mesEjec,long anioIniVig,long anioFinVig) throws SQLException;
	
	List<FiseLiquidacione> buscarFiseLiquidacion(String codEmpresa,
			long idGrupoInf) throws SQLException;
	
	void eliminarFiseLiquidacion(FiseLiquidacione fiseLiquidacione) 
			throws SQLException;
	
	FiseLiquidacione obtenerFiseLiquidacion(Long id) throws SQLException;
	
	int preparaLiquidacionFormato(long correlativo, 
			String usuario, String terminal) throws SQLException;
	
	int liquidarFormato(long correlativo, 
			String usuario, String terminal) throws SQLException;
	
	List<FiseLiquidacionesMotivosNo> buscarFiseLiquidacionesMotivosNo(long correlativo,
			long item) throws SQLException;
	
	FiseLiquidacionesMotivosNo obtenerFiseLiquidacionesMotivosNo(FiseLiquidacionesMotivosNoPK id) 
			throws SQLException;
	
	void insertarFiseLiquidacionesMotivosNo(FiseLiquidacionesMotivosNo liquidacionMotivosNo) 
			throws SQLException;
	
	void actualizarFiseLiquidacionesMotivosNo(FiseLiquidacionesMotivosNo liquidacionMotivosNo) 
			throws SQLException;
	
	long buscarMaximoMotivo(long correlativo) throws SQLException;
	
	void eliminarFiseLiquidacionesMotivosNo(FiseLiquidacionesMotivosNo liquidacionMotivosNo) 
			throws SQLException;

}
