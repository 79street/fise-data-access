package gob.osinergmin.fise.gart.service;

import gob.osinergmin.fise.bean.ResumenCostoBean;

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

}
