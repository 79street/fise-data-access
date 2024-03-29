package gob.osinergmin.fise.gart.service;

import gob.osinergmin.fise.bean.ArchivoSustentoBean;
import gob.osinergmin.fise.domain.FiseDescripcionActividade;

import java.util.List;

public interface ArchivoSustentoService {
	
	 List<ArchivoSustentoBean> listarFiseArchivosCab(String codEmpresa, 
				long idGrupoInf,String etapa,String usuario,String terminal,String flagBusq)
				throws Exception;
	 
	 List<ArchivoSustentoBean> listarArchivosSustentoFormato(long correlativo)
			 throws Exception;
	 
	 String guardarArchivoSustento(String correlativoF,String nombreArchivo,long idFileEntry,
			 String formatoActiv,String itemActiv,String user,String terminal) throws Exception;
	 
	 String actualizarArchivoSustento(String itemArchivo,String correlativoArchivo,String nombreArchivo,
				long idFileEntry,String formatoActiv,String itemActiv,String user,String terminal) throws Exception;
	 
	 String eliminarArchivoSustento(String itemArchivo,String correlativoArchivo) throws Exception;
	 
	 List<FiseDescripcionActividade> listarDescripcionActividades(String formato) 
				throws Exception;

}
