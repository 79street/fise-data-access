package gob.osinergmin.fise.gart.service;

import gob.osinergmin.fise.bean.ResumenCostoActividadBean;
import gob.osinergmin.fise.bean.ResumenCostoBean;
import gob.osinergmin.fise.domain.AdmEmpresa;

import java.util.List;

public interface ResumenCostosService {
	
	List<ResumenCostoBean> buscarResumenCostoF14A(String codEmpresa,
			Long idGrupoInf) throws Exception;
	
	List<ResumenCostoBean> buscarResumenCostoF12AB(String codEmpresa,
			Long idGrupoInf,String formato) throws Exception;
	
	List<ResumenCostoBean> buscarResumenCostoF14B(String codEmpresa,
			Long idGrupoInf) throws Exception;
	
	List<ResumenCostoBean> buscarResumenCostoCompF14AB(String codEmpresa,
			Long idGrupoInf,Long idZona, String formato) throws Exception;	
		
	List<ResumenCostoActividadBean> buscarResumenCostoActividadF14AB(String codEmpresa,
			Long idGrupoInf,List<AdmEmpresa> listaEmpresas) throws Exception;

}
