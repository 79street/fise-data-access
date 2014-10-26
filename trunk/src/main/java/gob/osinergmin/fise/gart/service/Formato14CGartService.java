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
    
    void insertarDatosFormato14C(Formato14CBean bean) 
    		throws Exception;
    
    

}
