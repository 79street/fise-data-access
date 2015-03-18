package gob.osinergmin.fise.gart.service.impl;

import gob.osinergmin.fise.bean.ArchivoSustentoBean;
import gob.osinergmin.fise.dao.ArchivoSustentoDao;
import gob.osinergmin.fise.domain.FiseArchivosCab;
import gob.osinergmin.fise.domain.FiseArchivosDet;
import gob.osinergmin.fise.domain.FiseArchivosDetPK;
import gob.osinergmin.fise.gart.service.ArchivoSustentoService;
import gob.osinergmin.fise.util.FechaUtil;

import java.util.ArrayList;
import java.util.List;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service(value="archivoSustentoServiceImpl")
public class ArchivoSustentoServiceImpl implements ArchivoSustentoService {

	Logger logger=Logger.getLogger(ArchivoSustentoServiceImpl.class);
	
	
	@Autowired
	@Qualifier("archivoSustentoDaoImpl")
	private ArchivoSustentoDao archivoSustentoDao;
	
	
	
/*****Implementacion de metodos********/
	
	
	@Override
	@Transactional
	public List<ArchivoSustentoBean> listarFiseArchivosCab(String codEmpresa, 
			long idGrupoInf,String etapa,String usuario,String terminal,String flagBusq)
			throws Exception{
		List<ArchivoSustentoBean> lista = null;	
		try {	
			int resultadoProceso = 1;
			int resultadoBusq = 1;
			/** P = proceso llamando a un procedimiento para poblar la entidad fisearchivocab y despues busco.**/
			if("P".equals(flagBusq)){ 
				resultadoProceso = archivoSustentoDao.llenarDatosFiseArchivosSustento(codEmpresa,
						idGrupoInf, etapa, usuario, terminal);
				logger.info("resultado de la operacion de poblar archivos sustentos: "+resultadoProceso);	
			}else{
				resultadoBusq = 1;
				resultadoProceso =0;
			}
			
			if(resultadoProceso==0 && resultadoBusq==1){		
				
				List<FiseArchivosCab> listaFormatos = archivoSustentoDao.buscarFiseArchivosCab(codEmpresa,
						idGrupoInf, etapa);	
				
				lista = new ArrayList<ArchivoSustentoBean>();
				
				for(FiseArchivosCab a : listaFormatos){
					ArchivoSustentoBean objeto = new ArchivoSustentoBean();
					objeto.setCorrelativo(""+a.getCorrelativo()); 
					objeto.setCodEmpresa(a.getCodEmpresa());
					objeto.setAnioPres(""+a.getAnoPresentacion());
					objeto.setMesPres(""+a.getMesPresentacion());
					objeto.setAnioEjec(a.getAnoEjecucionGasto()==null? "---": ""+a.getAnoEjecucionGasto());
					objeto.setMesEjec(a.getMesEjecucionGasto()==null? "00": ""+a.getMesEjecucionGasto());
					objeto.setAnioIniVig(a.getAnoInicioVigencia()==null? "---": ""+a.getAnoInicioVigencia()); 
					objeto.setAnioFinVig(a.getAnoFinVigencia()==null? "---" : ""+a.getAnoFinVigencia()); 
					objeto.setEtapa(a.getEtapa()==null? "---":a.getEtapa()); 					
					objeto.setFormato(a.getFormato()); 
					lista.add(objeto);
				}		
			}				
		} catch (Exception e) {
			logger.info("Ocurrio un error al listar formatos pra agregar archivos de sustento:  "+e);
			lista = new ArrayList<ArchivoSustentoBean>();
			e.printStackTrace();
		}	
		return lista;		
	}


	@Override
	@Transactional
	public List<ArchivoSustentoBean> listarArchivosSustentoFormato(long correlativo)
			throws Exception {
		List<ArchivoSustentoBean> lista = new ArrayList<ArchivoSustentoBean>();	
		try {
			List<FiseArchivosDet> listaArchivos = archivoSustentoDao.buscarFiseArchivosDet(correlativo);
			for(FiseArchivosDet d : listaArchivos){
				ArchivoSustentoBean objeto = new ArchivoSustentoBean();
				objeto.setItemArchivo(""+d.getId().getItem());
				objeto.setCorrArchivo(""+d.getId().getCorrelativo());
				objeto.setNombreArchivo(d.getNombreArchivoFisico()==null? "---": ""+d.getNombreArchivoFisico());
				objeto.setEstadoArchivo(d.getEstado().equals("A")? "Activo": "Inactivo");
				objeto.setIdFileEntry(""+d.getIdFileLiferay()); 
				lista.add(objeto);
			}		
		} catch (Exception e) {
			logger.info("Ocurrio un error al listar archivos de sustentos:  "+e);			
			e.printStackTrace();	
		}
		return lista;
	}
		
	
	@Override
	@Transactional
	public String guardarArchivoSustento(String correlativoF,String nombreArchivo,long idFileEntry,
			String user,String terminal) throws Exception{
		String valor = "0";
		FiseArchivosDet archivoDet = null;
		FiseArchivosDetPK pk = null;			
		try {			
			archivoDet = new FiseArchivosDet();
			pk = new FiseArchivosDetPK();
			long itemArchivo = archivoSustentoDao.buscarMaximoItemArchivo(Long.valueOf(correlativoF));
			logger.info("Item archivo:   "+itemArchivo); 
			pk.setItem(itemArchivo);
			pk.setCorrelativo(Long.valueOf(correlativoF));		
			archivoDet.setId(pk);			 
			archivoDet.setNombreArchivoFisico(nombreArchivo);
			archivoDet.setEstado("A");
			archivoDet.setIdFileLiferay(idFileEntry); 
			archivoDet.setUsuarioCreacion(user);
			archivoDet.setTerminalCreacion(terminal);
			archivoDet.setFechaCreacion(FechaUtil.obtenerFechaActual());
			archivoSustentoDao.insertarFiseArchivosDet(archivoDet); 
			valor = "1";
		} catch (Exception e) {
			e.printStackTrace();
			valor = "0";
		}finally{
			if(archivoDet!=null){
				archivoDet=null;
			}
			if(pk!=null){
				pk=null;
			}			
		}
		return valor;
	}
	
	
	@Override
	@Transactional
	public String actualizarArchivoSustento(String itemArchivo,String correlativoArchivo,String nombreArchivo,
			long idFileEntry,String user,String terminal) throws Exception{
		String valor = "0";
		FiseArchivosDet archivoDet = null;
		FiseArchivosDetPK pk = null;		
		try {
			pk = new FiseArchivosDetPK();
			pk.setItem(Long.valueOf(itemArchivo)); 	
			pk.setCorrelativo(Long.valueOf(correlativoArchivo));		
			archivoDet = archivoSustentoDao.obtenerFiseArchivosDet(pk);	
			archivoDet.setNombreArchivoFisico(nombreArchivo);
			archivoDet.setIdFileLiferay(idFileEntry); 
			archivoDet.setUsuarioActualizacion(user);
			archivoDet.setTerminalActualizacion(terminal);
			archivoDet.setFechaActualizacion(FechaUtil.obtenerFechaActual());
			archivoSustentoDao.actualizarFiseArchivosDet(archivoDet); 
			valor = "1";
		} catch (Exception e) {
			e.printStackTrace();
			valor = "0";
		}finally{
			if(archivoDet!=null){
				archivoDet=null;
			}
			if(pk!=null){
				pk=null;
			}			
		}
		return valor;
	}
	
	
	@Override
	@Transactional
	public String eliminarArchivoSustento(String itemArchivo,String correlativoArchivo) throws Exception{
		String valor = "0";
		FiseArchivosDet archivoDet = null;
		FiseArchivosDetPK pk = null;		
		try {
			pk = new FiseArchivosDetPK();
			pk.setItem(Long.valueOf(itemArchivo)); 	
			pk.setCorrelativo(Long.valueOf(correlativoArchivo));			
			archivoDet = archivoSustentoDao.obtenerFiseArchivosDet(pk);				
			archivoSustentoDao.eliminarFiseArchivosDet(archivoDet); 
			valor = "1";
		} catch (Exception e) {
			e.printStackTrace();
			valor = "0";
		}finally{
			if(archivoDet!=null){
				archivoDet=null;
			}
			if(pk!=null){
				pk=null;
			}			
		}
		return valor;
	}
	
	
}
