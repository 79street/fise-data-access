package gob.osinergmin.fise.gart.service.impl;

import gob.osinergmin.fise.bean.FiseCargoFijoBean;
import gob.osinergmin.fise.dao.FiseCargoFijoDao;
import gob.osinergmin.fise.domain.FiseMcargofijo;
import gob.osinergmin.fise.domain.FiseMcargofijoPK;
import gob.osinergmin.fise.gart.service.FiseCargoFijoService;
import gob.osinergmin.fise.util.FechaUtil;
import gob.osinergmin.fise.util.FormatoUtil;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;


@Service(value="fiseCargoFijoServiceImpl")
public class FiseCargoFijoServiceImpl implements FiseCargoFijoService {
	
	
	Logger logger=Logger.getLogger(FiseCargoFijoServiceImpl.class);
	
	@Autowired
	@Qualifier("fiseCargoFijoDaoImpl")
	private FiseCargoFijoDao fiseCargoFijoDao;
	
	
	
	@Override
	@Transactional
	public String insertarDatosFiseCargoFijo(FiseCargoFijoBean bean) throws Exception{
		FiseMcargofijo cargo =null;
		FiseMcargofijoPK pk = null;
		String valor="1";
		try {
			pk = new FiseMcargofijoPK();
			pk.setEmpcod(bean.getCodigoEmpresa());
			pk.setFaniorep(Long.valueOf(bean.getAnioReporte())); 
			pk.setFmesrep(Long.valueOf(bean.getMesReporte()));	 
			
			cargo = fiseCargoFijoDao.obtenerFiseCargoFijo(pk);
			
			if(cargo==null){
				cargo = new FiseMcargofijo();
				cargo.setId(pk);
				
				cargo.setCfinumageRural(Long.valueOf(bean.getNumAgenteR()));
				cargo.setCfinumageUrbProv(Long.valueOf(bean.getNumAgenteP()));
				cargo.setCfinumageUrbLima(Long.valueOf(bean.getNumAgenteL()));
				
				cargo.setCfinumusubenRural(Long.valueOf(bean.getNumUsuBenefR()));
				cargo.setCfinumusubenUrbProv(Long.valueOf(bean.getNumUsuBenefP()));
				cargo.setCfinumusubenUrbLima(Long.valueOf(bean.getNumUsuBenefL()));
				
				cargo.setCfinumusuempRural(Long.valueOf(bean.getNumUsuEmpR()));
				cargo.setCfinumusuempUrbProv(Long.valueOf(bean.getNumUsuEmpP()));
				cargo.setCfinumusuempUrbLima(Long.valueOf(bean.getNumUsuEmpL()));
				
				cargo.setCfinumvaldcanRural(Long.valueOf(bean.getNumValDCanR()));
				cargo.setCfinumvaldcanUrbProv(Long.valueOf(bean.getNumValDCanP()));
				cargo.setCfinumvaldcanUrbLima(Long.valueOf(bean.getNumValDCanL()));
				
				cargo.setCfinumvaldemiRural(Long.valueOf(bean.getNumValDEmiR()));
				cargo.setCfinumvaldemiUrbProv(Long.valueOf(bean.getNumValDEmiP()));
				cargo.setCfinumvaldemiUrbLima(Long.valueOf(bean.getNumValDEmiL()));
				
				cargo.setCfinumvalfcanRural(Long.valueOf(bean.getNumValFCanR()));
				cargo.setCfinumvalfcanUrbProv(Long.valueOf(bean.getNumValFCanP()));
				cargo.setCfinumvalfcanUrbLima(Long.valueOf(bean.getNumValFCanL()));
				
				cargo.setCfinumvalfemiRural(Long.valueOf(bean.getNumValFEmiR()));
				cargo.setCfinumvalfemiUrbProv(Long.valueOf(bean.getNumValFEmiP()));
				cargo.setCfinumvalfemiUrbLima(Long.valueOf(bean.getNumValFEmiL()));
				
				cargo.setCfimontracan(new BigDecimal(bean.getMontoCanje()));
			
				cargo.setCfimonRural(new BigDecimal(bean.getMontoMesR()));
				cargo.setCfimonUrbProv(new BigDecimal(bean.getMontoMesP()));
				cargo.setCfimonUrbLima(new BigDecimal(bean.getMontoMesL()));			
				
				
				cargo.setCfiigv(new BigDecimal(bean.getIgv()));
				cargo.setCfiapliigv(Integer.valueOf(bean.getAplicaIgv())); 
				//opcionales
				cargo.setCficom(bean.getGloza());
				
				cargo.setCfifecinf(bean.getFechaSustento()==null? null:
					FechaUtil.getFechaStringToDateDDMMYYY(bean.getFechaSustento()));
				
				cargo.setCfidoc(bean.getNumDoc());
				
				cargo.setScficod(bean.getEstado()==null? 1: Integer.valueOf(bean.getEstado())); 
				
				cargo.setCfifecrec(bean.getFechaRecepcion()==null? null:
					FechaUtil.getFechaStringToDateDDMMYYY(bean.getFechaRecepcion()));
				
				cargo.setCfidocrec(bean.getNumDocRecepcion());
				//auditoria
				cargo.setUsuarioCreacion(bean.getUsuario());
				cargo.setTerminalCreacion(bean.getTerminal()); 
				cargo.setFechaCreacion(FechaUtil.obtenerFechaActual()); 
				
				fiseCargoFijoDao.insertarFiseCargoFijo(cargo); 			
			}else{
				valor = "2";		
			}			
		} catch (Exception e) {
			logger.info("Error al grabar en fise cargo fijo: "+e); 
			valor = "0";
		}finally{
			if(cargo !=null){
				cargo =null;
			}
			if(pk !=null){
				pk =null;
			}
		}
		return valor;
		
	}
	
	@Override
	@Transactional
	public String actualizarDatosFiseCargoFijo(FiseCargoFijoBean bean) throws Exception{
		FiseMcargofijo cargo =null;
		FiseMcargofijoPK pk = null;
		String valor ="1";
		try {			
			pk = new FiseMcargofijoPK();
			pk.setEmpcod(bean.getCodigoEmpresa());
			pk.setFaniorep(Long.valueOf(bean.getAnioReporte())); 
			pk.setFmesrep(Long.valueOf(bean.getMesReporte()));	
			
			cargo = fiseCargoFijoDao.obtenerFiseCargoFijo(pk);			
			
			cargo.setCfinumageRural(Long.valueOf(bean.getNumAgenteR()));
			cargo.setCfinumageUrbProv(Long.valueOf(bean.getNumAgenteP()));
			cargo.setCfinumageUrbLima(Long.valueOf(bean.getNumAgenteL()));
			
			cargo.setCfinumusubenRural(Long.valueOf(bean.getNumUsuBenefR()));
			cargo.setCfinumusubenUrbProv(Long.valueOf(bean.getNumUsuBenefP()));
			cargo.setCfinumusubenUrbLima(Long.valueOf(bean.getNumUsuBenefL()));
			
			cargo.setCfinumusuempRural(Long.valueOf(bean.getNumUsuEmpR()));
			cargo.setCfinumusuempUrbProv(Long.valueOf(bean.getNumUsuEmpP()));
			cargo.setCfinumusuempUrbLima(Long.valueOf(bean.getNumUsuEmpL()));
			
			cargo.setCfinumvaldcanRural(Long.valueOf(bean.getNumValDCanR()));
			cargo.setCfinumvaldcanUrbProv(Long.valueOf(bean.getNumValDCanP()));
			cargo.setCfinumvaldcanUrbLima(Long.valueOf(bean.getNumValDCanL()));
			
			cargo.setCfinumvaldemiRural(Long.valueOf(bean.getNumValDEmiR()));
			cargo.setCfinumvaldemiUrbProv(Long.valueOf(bean.getNumValDEmiP()));
			cargo.setCfinumvaldemiUrbLima(Long.valueOf(bean.getNumValDEmiL()));
			
			cargo.setCfinumvalfcanRural(Long.valueOf(bean.getNumValFCanR()));
			cargo.setCfinumvalfcanUrbProv(Long.valueOf(bean.getNumValFCanP()));
			cargo.setCfinumvalfcanUrbLima(Long.valueOf(bean.getNumValFCanL()));
			
			cargo.setCfinumvalfemiRural(Long.valueOf(bean.getNumValFEmiR()));
			cargo.setCfinumvalfemiUrbProv(Long.valueOf(bean.getNumValFEmiP()));
			cargo.setCfinumvalfemiUrbLima(Long.valueOf(bean.getNumValFEmiL()));
			
			cargo.setCfimontracan(new BigDecimal(bean.getMontoCanje()));
		
			cargo.setCfimonRural(new BigDecimal(bean.getMontoMesR()));
			cargo.setCfimonUrbProv(new BigDecimal(bean.getMontoMesP()));
			cargo.setCfimonUrbLima(new BigDecimal(bean.getMontoMesL()));		
			
			
			cargo.setCfiigv(new BigDecimal(bean.getIgv()));
			cargo.setCfiapliigv(Integer.valueOf(bean.getAplicaIgv())); 
			//opcionales
			cargo.setCficom(bean.getGloza());
			
			cargo.setCfifecinf(bean.getFechaSustento()==null? null:
				FechaUtil.getFechaStringToDateDDMMYYY(bean.getFechaSustento())); 
			
			cargo.setCfidoc(bean.getNumDoc());
			
			cargo.setScficod(bean.getEstado()==null? 1: Integer.valueOf(bean.getEstado())); 
			
			cargo.setCfifecrec(bean.getFechaRecepcion()==null? null:
				FechaUtil.getFechaStringToDateDDMMYYY(bean.getFechaRecepcion()));
			
			cargo.setCfidocrec(bean.getNumDocRecepcion());
			
			//datos de auditoria
			cargo.setUsuarioActualizacion(bean.getUsuario());
			cargo.setTerminalActualizacion(bean.getTerminal()); 
			cargo.setFechaActualizacion(FechaUtil.obtenerFechaActual()); 
			
			fiseCargoFijoDao.actualizarFiseObservacion(cargo); 
		} catch (Exception e) {
			e.printStackTrace();
			logger.info("Error al actualizar en fise cargo fijo: "+e); 
			valor = "0";
		}finally{
			if(cargo !=null){
				cargo =null;
			}
			if(pk !=null){
				pk =null;
			}
		}
		return valor;
		
	}
	
	@Override
	@Transactional
	public String eliminarDatosFiseCargoFijo(String codEmpresa,
			String anio,String mes,String usuario,String terminal) throws Exception{		
		FiseMcargofijo cargo =null;
		FiseMcargofijoPK pk = null;
		String valor ="1";
		try {			
			pk = new FiseMcargofijoPK();
			pk.setEmpcod(codEmpresa);
			pk.setFaniorep(Long.valueOf(anio)); 
			pk.setFmesrep(Long.valueOf(mes));	
			
			cargo = fiseCargoFijoDao.obtenerFiseCargoFijo(pk);	
			cargo.setScficod(0); //cambio de estado a inactivo
			//datos de auditoria
			cargo.setUsuarioActualizacion(usuario);
			cargo.setTerminalActualizacion(terminal); 
			cargo.setFechaActualizacion(FechaUtil.obtenerFechaActual()); 
			
			fiseCargoFijoDao.actualizarFiseObservacion(cargo); 
			
		} catch (Exception e) {
			logger.info("Error al eliminar  observacion: "+e); 
			valor = "0";
		}finally{
			if(cargo !=null){
				cargo =null;
			}
			if(pk !=null){
				pk =null;
			}
		}
		return valor;
	}
	
	@Override
	@Transactional
	public FiseMcargofijo obtenerFiseCargofijo(String codEmpresa,
			String anio,String mes) throws Exception{
		
		FiseMcargofijoPK pk = new FiseMcargofijoPK();
		pk.setEmpcod(codEmpresa);
		pk.setFaniorep(Long.valueOf(anio)); 
		pk.setFmesrep(Long.valueOf(mes));		
		
		return fiseCargoFijoDao.obtenerFiseCargoFijo(pk);
		
	}
	
	@Override
	@Transactional
	public FiseCargoFijoBean buscarFiseCargoFijoEditar(String codEmpresa,
			String anio,String mes) throws Exception{		
		FiseMcargofijo c =null;
		FiseMcargofijoPK pk = null;
		FiseCargoFijoBean bean =new FiseCargoFijoBean();
		try {
			pk = new FiseMcargofijoPK();
			pk.setEmpcod(codEmpresa);
			pk.setFaniorep(Long.valueOf(anio)); 
			pk.setFmesrep(Long.valueOf(mes));
			
			c= fiseCargoFijoDao.obtenerFiseCargoFijo(pk);	
			if(c!=null){
				String codEmpreCompleta = FormatoUtil.rellenaDerecha(c.getId().getEmpcod(), ' ', 4);
				bean.setCodigoEmpresa(codEmpreCompleta);
				bean.setAnioReporte(""+c.getId().getFaniorep());
				bean.setMesReporte(""+c.getId().getFmesrep());
				
				bean.setNumAgenteR(""+c.getCfinumageRural());
				bean.setNumAgenteP(""+c.getCfinumageUrbProv());
				bean.setNumAgenteL(""+c.getCfinumageUrbLima());
				
				bean.setNumUsuBenefR(""+c.getCfinumusubenRural());
				bean.setNumUsuBenefP(""+c.getCfinumusubenUrbProv());
				bean.setNumUsuBenefL(""+c.getCfinumusubenUrbLima());
				
				bean.setNumUsuEmpR(""+c.getCfinumusuempRural());
				bean.setNumUsuEmpP(""+c.getCfinumusuempUrbProv());
				bean.setNumUsuEmpL(""+c.getCfinumusuempUrbLima());
				
				bean.setNumValDCanR(""+c.getCfinumvaldcanRural());
				bean.setNumValDCanP(""+c.getCfinumvaldcanUrbProv());
				bean.setNumValDCanL(""+c.getCfinumvaldcanUrbLima());
				
				bean.setNumValDEmiR(""+c.getCfinumvaldemiRural());
				bean.setNumValDEmiP(""+c.getCfinumvaldemiUrbProv());
				bean.setNumValDEmiL(""+c.getCfinumvaldemiUrbLima());
				
				bean.setNumValFCanR(""+c.getCfinumvalfcanRural());
				bean.setNumValFCanP(""+c.getCfinumvalfcanUrbProv());
				bean.setNumValFCanL(""+c.getCfinumvalfcanUrbLima());
				
				bean.setNumValFEmiR(""+c.getCfinumvalfemiRural());
				bean.setNumValFEmiP(""+c.getCfinumvalfemiUrbProv());
				bean.setNumValFEmiL(""+c.getCfinumvalfemiUrbLima());
				
				bean.setMontoCanje(""+c.getCfimontracan());
				
				bean.setMontoMesR(""+c.getCfimonRural());
				bean.setMontoMesP(""+c.getCfimonUrbProv());
				bean.setMontoMesL(""+c.getCfimonUrbLima());
				
				bean.setIgv(""+c.getCfiigv());
				bean.setAplicaIgv(""+c.getCfiapliigv());		
				//opcionales
				bean.setGloza(c.getCficom()==null? "":c.getCficom());
				bean.setFechaSustento(c.getCfifecinf()==null? "":
					FechaUtil.getFechaDateToString(c.getCfifecinf())); 
				bean.setNumDoc(c.getCfidoc()==null ? "":c.getCfidoc());
				bean.setEstado(c.getScficod()==null?"": ""+c.getScficod());
				bean.setFechaRecepcion(c.getCfifecrec()==null? "" :
					FechaUtil.getFechaDateToString(c.getCfifecrec()) );
				bean.setNumDocRecepcion(c.getCfidocrec()==null ? "" : c.getCfidocrec());
			}		
		} catch (Exception e) {
			e.printStackTrace();
			logger.info("Error al buscar datos para editar:  "+e); 
		}finally{
			if(c !=null){
				c =null;
			}
			if(pk !=null){
				pk =null;
			}
		}
		return bean;
	}
	
	@Override
	@Transactional
	public List<FiseCargoFijoBean> buscarFiseCargoFijo(String codEmpresa, Long anioRep,Long mesRep)
			throws Exception{
		
		List<FiseCargoFijoBean> listaCargosFijos= new ArrayList<FiseCargoFijoBean>();
		FiseCargoFijoBean bean=null;
		try {
			List<FiseMcargofijo> lista = fiseCargoFijoDao.buscarFiseCargoFijo(codEmpresa, anioRep, mesRep);
			
			for(FiseMcargofijo cargo : lista){ 
					bean = new FiseCargoFijoBean();
					bean.setCodigoEmpresa(cargo.getId().getEmpcod()); 
	  				bean.setAnioReporte(""+cargo.getId().getFaniorep());
	  				bean.setMesReporte(""+cargo.getId().getFmesrep());   
	  				bean.setNumAgenteR(""+cargo.getCfinumageRural());
					bean.setNumAgenteP(""+cargo.getCfinumageUrbProv());
					bean.setNumAgenteL(""+cargo.getCfinumageUrbLima());
					
					bean.setNumUsuBenefR(""+cargo.getCfinumusubenRural());
					bean.setNumUsuBenefP(""+cargo.getCfinumusubenUrbProv());
					bean.setNumUsuBenefL(""+cargo.getCfinumusubenUrbLima());
					
					bean.setNumUsuEmpR(""+cargo.getCfinumusuempRural());
					bean.setNumUsuEmpP(""+cargo.getCfinumusuempUrbProv());
					bean.setNumUsuEmpL(""+cargo.getCfinumusuempUrbLima());
					
					bean.setNumValDCanR(""+cargo.getCfinumvaldcanRural());
					bean.setNumValDCanP(""+cargo.getCfinumvaldcanUrbProv());
					bean.setNumValDCanL(""+cargo.getCfinumvaldcanUrbLima());
					
					bean.setNumValDEmiR(""+cargo.getCfinumvaldemiRural());
					bean.setNumValDEmiP(""+cargo.getCfinumvaldemiUrbProv());
					bean.setNumValDEmiL(""+cargo.getCfinumvaldemiUrbLima());
					
					bean.setNumValFCanR(""+cargo.getCfinumvalfcanRural());
					bean.setNumValFCanP(""+cargo.getCfinumvalfcanUrbProv());
					bean.setNumValFCanL(""+cargo.getCfinumvalfcanUrbLima());
					
					bean.setNumValFEmiR(""+cargo.getCfinumvalfemiRural());
					bean.setNumValFEmiP(""+cargo.getCfinumvalfemiUrbProv());
					bean.setNumValFEmiL(""+cargo.getCfinumvalfemiUrbLima());
					
					bean.setMontoCanje(""+cargo.getCfimontracan());
					
					bean.setMontoMesR(""+cargo.getCfimonRural());
					bean.setMontoMesP(""+cargo.getCfimonUrbProv());
					bean.setMontoMesL(""+cargo.getCfimonUrbLima());
					
					bean.setIgv(""+cargo.getCfiigv());
					//bean.setAplicaIgv(""+cargo.getCfiapliigv());		
					//opcionales
					bean.setGloza(cargo.getCficom()==null? "":cargo.getCficom());
					bean.setFechaSustento(cargo.getCfifecinf()==null? "":
						FechaUtil.getFechaDateToString(cargo.getCfifecinf())); 
					bean.setNumDoc(cargo.getCfidoc()==null ? "":cargo.getCfidoc());					
					bean.setFechaRecepcion(cargo.getCfifecrec()==null? "" :
						FechaUtil.getFechaDateToString(cargo.getCfifecrec()) );
					bean.setNumDocRecepcion(cargo.getCfidocrec()==null ? "" : cargo.getCfidocrec());
					
					if(1==cargo.getScficod()){ 
						bean.setDesEstado("Activo");			
					}else{
						bean.setDesEstado("Inactivo");			
					}

					if(1==cargo.getCfiapliigv()){ 
						bean.setAplicaIgv("SI");			
					}else{
						bean.setAplicaIgv("NO");			
					}
					
					listaCargosFijos.add(bean);
				}   			
		} catch (Exception e) {
			logger.info("Error al listar cargos fijos:  "+e); 
		}finally{
			if(bean!=null){
				bean =null;
			}
		}
		return listaCargosFijos;
	}
	
	
	@Override
	public HashMap<String, Object> mapParametrosDatosProyectoFise(FiseCargoFijoBean bean) 
			throws Exception {
		
		HashMap<String, Object> map = new HashMap<String, Object>();	
				
		map.put("DESC_EMPRESA",bean.getDesEmpresa());		
		map.put("ANIO_REPORTADO",bean.getAnioReporte());	
		map.put("MES_REPORTADO",bean.getDesMesRep());		
		
		map.put("NRO_AGENTES_R",bean.getNumAgenteR()!=null?Long.parseLong(bean.getNumAgenteR()):0);		
		map.put("NRO_AGENTES_P",bean.getNumAgenteP()!=null?Long.parseLong(bean.getNumAgenteP()):0);		
		map.put("NRO_AGENTES_L",bean.getNumAgenteL()!=null?Long.parseLong(bean.getNumAgenteL()):0);		
		
		map.put("NRO_USU_BENEF_R",bean.getNumUsuBenefR()!=null?Long.parseLong(bean.getNumUsuBenefR()):0);	
		map.put("NRO_USU_BENEF_P",bean.getNumUsuBenefP()!=null?Long.parseLong(bean.getNumUsuBenefP()):0);
		map.put("NRO_USU_BENEF_L",bean.getNumUsuBenefL()!=null?Long.parseLong(bean.getNumUsuBenefL()):0);
		
		map.put("NRO_USU_EMPADRONADO_BENEF_R",bean.getNumUsuEmpR()!=null?Long.parseLong(bean.getNumUsuEmpR()):0);	
		map.put("NRO_USU_EMPADRONADO_BENEF_P",bean.getNumUsuEmpP()!=null?Long.parseLong(bean.getNumUsuEmpP()):0);		
		map.put("NRO_USU_EMPADRONADO_BENEF_L",bean.getNumUsuEmpL()!=null?Long.parseLong(bean.getNumUsuEmpL()):0);
		
		map.put("NRO_VAL_DIGITAL_CANJ_R",bean.getNumValDCanR()!=null?Long.parseLong(bean.getNumValDCanR()):0);		
		map.put("NRO_VAL_DIGITAL_CANJ_P",bean.getNumValDCanP()!=null?Long.parseLong(bean.getNumValDCanP()):0);		
		map.put("NRO_VAL_DIGITAL_CANJ_L",bean.getNumValDCanL()!=null?Long.parseLong(bean.getNumValDCanL()):0);	
		
		map.put("NRO_VAL_DIGITAL_EMIT_R",bean.getNumValDEmiR()!=null?Long.parseLong(bean.getNumValDEmiR()):0);		
		map.put("NRO_VAL_DIGITAL_EMIT_P",bean.getNumValDEmiP()!=null?Long.parseLong(bean.getNumValDEmiP()):0);	
		map.put("NRO_VAL_DIGITAL_EMIT_L",bean.getNumValDEmiL()!=null?Long.parseLong(bean.getNumValDEmiL()):0);		
		
		map.put("NRO_VAL_FISICOS_CANJ_R",bean.getNumValFCanR()!=null?Long.parseLong(bean.getNumValFCanR()):0);	
		map.put("NRO_VAL_FISICOS_CANJ_P",bean.getNumValFCanP()!=null?Long.parseLong(bean.getNumValFCanP()):0);				
		map.put("NRO_VAL_FISICOS_CANJ_L",bean.getNumValFCanL()!=null?Long.parseLong(bean.getNumValFCanL()):0);	
		
		map.put("NRO_VAL_FISICOS_EMIT_R",bean.getNumValFEmiR()!=null?Long.parseLong(bean.getNumValFEmiR()):0);	
		map.put("NRO_VAL_FISICOS_EMIT_P",bean.getNumValFEmiP()!=null?Long.parseLong(bean.getNumValFEmiP()):0);		
		map.put("NRO_VAL_FISICOS_EMIT_L",bean.getNumValFEmiL()!=null?Long.parseLong(bean.getNumValFEmiL()):0);
				
		map.put("CARGO_FIJO_MES_R",bean.getMontoMesR()!=null?new BigDecimal(bean.getMontoMesR()):new BigDecimal(0.00));		
		map.put("CARGO_FIJO_MES_P",bean.getMontoMesP()!=null?new BigDecimal(bean.getMontoMesP()):new BigDecimal(0.00));		
		map.put("CARGO_FIJO_MES_L",bean.getMontoMesL()!=null?new BigDecimal(bean.getMontoMesL()):new BigDecimal(0.00));
		
		map.put("MONTO_TRANSF_CANJE",bean.getMontoCanje()!=null?new BigDecimal(bean.getMontoCanje()):new BigDecimal(0.00));			
				
		map.put("IGV",bean.getIgv()!=null?new BigDecimal(bean.getIgv()):new BigDecimal(0.00));
		logger.info("Aplica igv service:  "+bean.getAplicaIgv()); 
		map.put("APLICA_IGV",bean.getAplicaIgv().trim().equals("1")?"SI":"NO");			
		map.put("GLOSA",bean.getGloza());		
		map.put("FECHA_INFORME_SUST",bean.getFechaSustento());		
		map.put("NRO_DOC_INFORME_SUST",bean.getNumDoc());			
		map.put("ESTADO",bean.getEstado().trim().equals("1")?"Activo":"Inactivo");		
		map.put("FECHA_RECEP_INF",bean.getFechaRecepcion());
		map.put("NRO_DOC_RECEP_INF",bean.getNumDocRecepcion());	
		
		return map;
	}	

}
