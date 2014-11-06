package gob.osinergmin.fise.gart.service;

import gob.osinergmin.fise.bean.Formato14CBean;
import gob.osinergmin.fise.domain.FiseFormato14CC;
import gob.osinergmin.fise.domain.FiseFormato14CD;

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
    
    List<FiseFormato14CC> buscarFiseFormato14CC(String codEmpresa, long anioDesde, 
			long anioHasta, long mesDesde, long mesHasta, String etapa) 
					throws Exception;
    
    
    public Formato14CBean buscarFormato14CEditar(String codEmpresa,String anioPres,String mesPres,
			String anioIniVig,String anioFinVig,String etapa) 
					throws Exception;

}
