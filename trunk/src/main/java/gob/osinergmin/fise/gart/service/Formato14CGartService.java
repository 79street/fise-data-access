package gob.osinergmin.fise.gart.service;

import gob.osinergmin.fise.bean.Formato14CBean;
import gob.osinergmin.fise.bean.Formato14CReportBean;
import gob.osinergmin.fise.domain.FiseFormato14CC;
import gob.osinergmin.fise.domain.FiseFormato14CD;
import gob.osinergmin.fise.domain.FiseFormato14CDOb;

import java.util.HashMap;
import java.util.List;

public interface Formato14CGartService {
	
    List<FiseFormato14CC> listarFormato14CC() 
			throws Exception;
    
    List<FiseFormato14CD> listarFormato14CD() 
			throws Exception;
    
    String insertarDatosFormato14C(Formato14CBean bean) 
    		throws Exception;
    
    String actualizarDatosFormato14C(Formato14CBean bean) 
    		throws Exception;
    
    String actualizarDatosEnvioFormato14C(Formato14CBean bean) 
    		throws Exception;
    
    String eliminarDatosFormato14C(Formato14CBean bean) 
    		throws Exception;
    
    public List<FiseFormato14CC> buscarFiseFormato14CC(String codEmpresa,
			long fechaDesde, long fechaHasta,String etapa) throws Exception;
    
    Formato14CBean buscarFormato14CEditar(String codEmpresa,String anioPres,String mesPres,
			String anioIniVig,String anioFinVig,String etapa) 
					throws Exception;
    
    FiseFormato14CC obtenerFiseFormato14CC(Formato14CBean bean) 
    		throws Exception;
    
    List<FiseFormato14CDOb> listaObservacionesF14C(FiseFormato14CD d) 
    		throws Exception;
    
    Formato14CReportBean estructurarFormato14CBeanByFiseFormato14C(
			FiseFormato14CC formato) throws Exception;
    
    HashMap<String, Object> mapearParametrosFormato14C(
			Formato14CReportBean f) throws Exception;
    
    void eliminarObservaciones14C(List<FiseFormato14CDOb> listObs) throws Exception;
    
    String insertarObservacion14C(String codEmpresa,long anioPres,long mesPres,
			long anioIniVig,long anioFinVig,String etapa,long idZona,long idPersonal, 
			String desObservacion,String user,String terminal) throws Exception;
    
    String eliminarObservacion14C(String codEmpresa,long anioPres,long mesPres,
			long anioIniVig,long anioFinVig,String etapa,long idZona,long idPersonal, 
			String idObservacion,long itemObservacion) throws Exception;
    

}
