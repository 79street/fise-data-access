package gob.osinergmin.fise.gart.service.impl;

import gob.osinergmin.fise.bean.Formato12BCBean;
import gob.osinergmin.fise.constant.FiseConstants;
import gob.osinergmin.fise.dao.ArchivoSustentoDao;
import gob.osinergmin.fise.dao.CommonDao;
import gob.osinergmin.fise.dao.FiseGrupoInformacionDao;
import gob.osinergmin.fise.dao.FiseObservacionDao;
import gob.osinergmin.fise.dao.FiseZonaBenefDao;
import gob.osinergmin.fise.dao.Formato12BCDao;
import gob.osinergmin.fise.dao.Formato12BDDao;
import gob.osinergmin.fise.dao.Formato12BDObDao;
import gob.osinergmin.fise.domain.FiseArchivosCab;
import gob.osinergmin.fise.domain.FiseArchivosDet;
import gob.osinergmin.fise.domain.FiseFormato12BC;
import gob.osinergmin.fise.domain.FiseFormato12BCPK;
import gob.osinergmin.fise.domain.FiseFormato12BD;
import gob.osinergmin.fise.domain.FiseFormato12BDOb;
import gob.osinergmin.fise.domain.FiseFormato12BDObPK;
import gob.osinergmin.fise.domain.FiseFormato12BDPK;
import gob.osinergmin.fise.domain.FiseGrupoInformacion;
import gob.osinergmin.fise.domain.FiseObservacion;
import gob.osinergmin.fise.domain.FiseZonaBenef;
import gob.osinergmin.fise.gart.service.Formato12BGartService;
import gob.osinergmin.fise.util.FechaUtil;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;


@Service(value="formato12BGartServiceImpl")
public class Formato12BGartServiceImpl implements Formato12BGartService {

	Logger logger=Logger.getLogger(Formato12BGartServiceImpl.class);
	
	@Autowired
	@Qualifier("formato12BCDaoImpl")
	private Formato12BCDao formato12BCDao;
	
	@Autowired
	@Qualifier("formato12BDDaoImpl")
	private Formato12BDDao formato12BDDao;
	
	@Autowired
	@Qualifier("formato12BDObDaoImpl")
	private Formato12BDObDao formato12BDObDao;
	
	@Autowired
	@Qualifier("fiseObservacionDaoImpl")
	private FiseObservacionDao fiseObservacionDao;
	
	@Autowired
	@Qualifier("commonDaoImpl")
	private CommonDao commonDao;
	
	@Autowired
	@Qualifier("fiseGrupoInformacionDaoImpl")
	private FiseGrupoInformacionDao fiseGrupoInformacionDao;
	
	@Autowired
	@Qualifier("fiseZonaBenefDaoImpl")
	private FiseZonaBenefDao zonaBenefDao;
	
	@Autowired
	@Qualifier("archivoSustentoDaoImpl")
	private ArchivoSustentoDao archivoSustentoDao;
	
	
	
	@Override
	public List<FiseFormato12BC> getLstFormatoCabecera(String codemp, Integer anioDesde, Integer mesDesde, Integer anioHasta, Integer mesHasta, String etapa) {
		return formato12BCDao.getLstFormatoCabecera(codemp, anioDesde, mesDesde, anioHasta, mesHasta, etapa);
	}

	@Override
	@Transactional
	public FiseFormato12BC getFormatoCabeceraById(FiseFormato12BCPK id) {
		FiseFormato12BC formato = formato12BCDao.getFormatoCabeceraById(id);		
	    formato.setListaDetalle12BDs(formato!=null?formato.getFiseFormato12BDs():new ArrayList<FiseFormato12BD>()); 
	    System.out.println("detalle observacion 12B  :"+formato.getListaDetalle12BDs()); 
		return formato;
	}

	@Override
	@Transactional
	public FiseFormato12BC saveFormatoCabecera(FiseFormato12BC formato) throws DataIntegrityViolationException,Exception{
		return formato12BCDao.saveFormatoCabecera(formato);
	}

	@Override
	@Transactional
	public Integer updateFormatoCabecera(FiseFormato12BC formato) throws DataIntegrityViolationException,Exception{
		return formato12BCDao.updateFormatoCabecera(formato);
	}

	@Override
	@Transactional
	public Integer deleteFormatoCabecera(FiseFormato12BCPK id) throws DataIntegrityViolationException,Exception{
		return formato12BCDao.deleteFormatoCabecera(id);
	}
	
	//cambios elozano
	@Transactional
	public String eliminarArchivoSustentoCab(FiseFormato12BCPK f) throws Exception{
		String valor = "0";
		List<FiseArchivosCab> listaCab =null;
		List<FiseArchivosDet> listaDet = null;
		try {
			BigDecimal anioEjec = null;
			BigDecimal mesEjec = null;
			if(f.getAnoEjecucionGasto()!=0){
				anioEjec = new 	BigDecimal(f.getAnoEjecucionGasto());
			}
			if(f.getMesEjecucionGasto()!=0){
				mesEjec = new BigDecimal(f.getMesEjecucionGasto());
			}			
			listaCab = archivoSustentoDao.listaFiseArchivosCabMensual(f.getCodEmpresa(),
					Long.valueOf(f.getAnoPresentacion()), Long.valueOf(f.getMesPresentacion()),
					anioEjec, mesEjec, f.getEtapa(),FiseConstants.NOMBRE_FORMATO_12B);
			logger.error("tamaño de lista cabecera por formato a borrar:  "+listaCab.size());
			if(listaCab!=null && listaCab.size()>0){				
				listaDet = archivoSustentoDao.buscarFiseArchivosDet(listaCab.get(0).getCorrelativo());
				for(FiseArchivosDet d: listaDet){					
					archivoSustentoDao.eliminarFiseArchivosDet(d);
				}
				archivoSustentoDao.eliminarFiseArchivosCab(listaCab.get(0)); 
				valor = "1";
			}		
		} catch (Exception e) {
			//e.printStackTrace();
			valor = "0";
		}finally{
			if(listaCab!=null){
				listaCab = null;	
			}
			if(listaDet!=null){
				listaDet = null;	
			}
		}
		return valor;
	}

	@Override
	public List<FiseFormato12BD> getLstFormatoDetalle(FiseFormato12BCPK idcabecera) {
		return formato12BDDao.getLstFormatoDetalle(idcabecera);
	}

	@Override
	public FiseFormato12BD getFormatoDetalleById(FiseFormato12BCPK id, Integer idzona) {
		return formato12BDDao.getFormatoDetalleById(id, idzona);
	}

	@Override
	@Transactional
	public FiseFormato12BD saveFormatoDetalle(FiseFormato12BD formato) throws DataIntegrityViolationException,Exception{
		return formato12BDDao.saveFormatoDetalle(formato);
	}

	@Override
	@Transactional
	public Integer updateFormatoDetalle(FiseFormato12BD formato) throws DataIntegrityViolationException,Exception{
		return formato12BDDao.updateFormatoDetalle(formato);
	}

	@Override
	@Transactional
	public  Integer deleteFormatoDetalle(String emp,Integer anio,Integer mes,String etapa,Integer anioEjec,Integer mesEjec,Integer idzona) throws DataIntegrityViolationException,Exception{
		return formato12BDDao.deleteFormatoDetalle( emp, anio, mes, etapa, anioEjec, mesEjec,idzona);
	}

	@Override
	@Transactional
	public List<FiseFormato12BDOb> getLstFormatoObs(FiseFormato12BD idDetalle) {
		return formato12BDObDao.getLstFormatoObs(idDetalle);
	}
	
	public Formato12BCBean estructurarFormato12BBeanByFiseFormato12BC(FiseFormato12BC formato) {
		
		//setting the values of Bean
		Formato12BCBean formato12BBean = new Formato12BCBean();
		
		formato12BBean.setAnioPresent(formato.getId().getAnoPresentacion());
		formato12BBean.setAnioEjecuc(formato.getId().getAnoEjecucionGasto());
		
		 BigDecimal totalImpresionesVal = new BigDecimal(0);
		 BigDecimal totalRepartValDomic = new BigDecimal(0);
		 BigDecimal totalEntregaValDisEl = new BigDecimal(0);
		 BigDecimal totalCanjeLiqValFisic = new BigDecimal(0);
		 BigDecimal totalCanjeLiqValDigit = new BigDecimal(0);
		 BigDecimal totalAtencionSolic = new BigDecimal(0);
		 BigDecimal totalGestionAdm = new BigDecimal(0);
		 BigDecimal totalDesplPersonal = new BigDecimal(0);
		 BigDecimal totalActiviExtraord = new BigDecimal(0);
		 
		 try{
		 if(formato.getFiseFormato12BDs()!=null){
			 for (FiseFormato12BD detalle : formato.getFiseFormato12BDs()) {			
					if( FiseConstants.ZONABENEF_RURAL == detalle.getId().getIdZonaBenef() ){
						
					    formato12BBean.setNroValeImpR(detalle.getNumeroValesImpreso()!=null ?detalle.getNumeroValesImpreso():0);
					    formato12BBean.setCostoUnitValImpR(detalle.getCostoEstandarUnitValeImpre()!=null?detalle.getCostoEstandarUnitValeImpre():new BigDecimal(0));
						BigDecimal costoTotalValImpR = detalle.getCostoEstandarUnitValeImpre().multiply(new BigDecimal(detalle.getNumeroValesImpreso()));
						formato12BBean.setCostoTotalValImpR(costoTotalValImpR);
					
						formato12BBean.setNroValReparDomicR(detalle.getNumeroValesRepartidosDomi()!=null ?detalle.getNumeroValesRepartidosDomi():0);
						formato12BBean.setCostoUnitValReparDomicR(detalle.getCostoEstandarUnitValeRepar()!=null?detalle.getCostoEstandarUnitValeRepar():new BigDecimal(0));
						BigDecimal costoTotalValReparDomicR = detalle.getCostoEstandarUnitValeRepar().multiply(new BigDecimal(detalle.getNumeroValesRepartidosDomi()));
						formato12BBean.setCostoTotalValReparDomicR(costoTotalValReparDomicR);
						
						formato12BBean.setNroValEntDisElR(detalle.getNumeroValesEntregadoDisEl()!=null ?detalle.getNumeroValesEntregadoDisEl():0);
						formato12BBean.setCostoUnitValEntDisElR(detalle.getCostoEstandarUnitValDisEl()!=null?detalle.getCostoEstandarUnitValDisEl():new BigDecimal(0));
						BigDecimal costoTotalValEntDisElR=detalle.getCostoEstandarUnitValDisEl().multiply(new BigDecimal(detalle.getNumeroValesEntregadoDisEl()));
						formato12BBean.setCostoTotalValEntDisElR(costoTotalValEntDisElR);
						
						formato12BBean.setNroValFisiCanjR(detalle.getNumeroValesFisicosCanjeados()!=null ?detalle.getNumeroValesFisicosCanjeados():0);
						formato12BBean.setCostoUnitValFisiCanjR(detalle.getCostoEstandarUnitValFiCan()!=null?detalle.getCostoEstandarUnitValFiCan():new BigDecimal(0));
						BigDecimal costoTotalValFisiCanjR = detalle.getCostoEstandarUnitValFiCan().multiply(new BigDecimal(detalle.getNumeroValesFisicosCanjeados()));
						formato12BBean.setCostoTotalValFisiCanjR(costoTotalValFisiCanjR);
						

						formato12BBean.setNroValDigitCanjR(detalle.getNumeroValesDigitalCanjeados()!=null ?detalle.getNumeroValesDigitalCanjeados():0);
						formato12BBean.setCostoUnitValDigitCanjR(detalle.getCostoEstandarUnitValDgCan()!=null?detalle.getCostoEstandarUnitValDgCan():new BigDecimal(0));
						BigDecimal costoTotalValDigitCanjR = detalle.getCostoEstandarUnitValDgCan().multiply(new BigDecimal(detalle.getNumeroValesDigitalCanjeados()));
						formato12BBean.setCostoTotalValDigitCanjR(costoTotalValDigitCanjR);
						
						formato12BBean.setNroAtencionesR(detalle.getNumeroAtenciones()!=null ?detalle.getNumeroAtenciones():0);
						formato12BBean.setCostoUnitAtencionesR(detalle.getCostoEstandarUnitAtencion()!=null?detalle.getCostoEstandarUnitAtencion():new BigDecimal(0));
						BigDecimal costoTotalAtencionesR=detalle.getCostoEstandarUnitAtencion().multiply(new BigDecimal(detalle.getNumeroAtenciones()));
						formato12BBean.setCostoTotalAtencionesR(costoTotalAtencionesR);
						
						formato12BBean.setGestionAdmR(detalle.getTotalGestionAdministrativa()!=null?detalle.getTotalGestionAdministrativa():new BigDecimal(0));
						formato12BBean.setDesplPersonalR(detalle.getTotalDesplazamientoPersonal()!=null?detalle.getTotalDesplazamientoPersonal():new BigDecimal(0));
						formato12BBean.setActivExtraordR(detalle.getTotalActividadesExtraord()!=null?detalle.getTotalActividadesExtraord():new BigDecimal(0));
						
						//
						totalImpresionesVal = totalImpresionesVal.add(formato12BBean.getCostoTotalValImpR());
						totalRepartValDomic = totalRepartValDomic.add(formato12BBean.getCostoTotalValReparDomicR());
						totalEntregaValDisEl =totalEntregaValDisEl.add(formato12BBean.getCostoTotalValEntDisElR());
						totalCanjeLiqValFisic =totalCanjeLiqValFisic.add(formato12BBean.getCostoTotalValFisiCanjR());
						totalCanjeLiqValDigit =totalCanjeLiqValDigit.add(formato12BBean.getCostoTotalValDigitCanjR());
						totalAtencionSolic = totalAtencionSolic.add(formato12BBean.getCostoTotalAtencionesR());
						totalGestionAdm =totalGestionAdm.add(formato12BBean.getGestionAdmR());
						totalDesplPersonal =totalDesplPersonal.add(formato12BBean.getDesplPersonalR());
						totalActiviExtraord =totalActiviExtraord.add(formato12BBean.getActivExtraordR());
						
						}else if( FiseConstants.ZONABENEF_PROVINCIA == detalle.getId().getIdZonaBenef() ){
						    formato12BBean.setNroValeImpP(detalle.getNumeroValesImpreso()!=null ?detalle.getNumeroValesImpreso():0);
						    formato12BBean.setCostoUnitValImpP(detalle.getCostoEstandarUnitValeImpre());
							BigDecimal costoTotalValImpP = detalle.getCostoEstandarUnitValeImpre().multiply(new BigDecimal(detalle.getNumeroValesImpreso()));
							formato12BBean.setCostoTotalValImpP(costoTotalValImpP);
						
							formato12BBean.setNroValReparDomicP(detalle.getNumeroValesRepartidosDomi()!=null ?detalle.getNumeroValesRepartidosDomi():0);
							formato12BBean.setCostoUnitValReparDomicP(detalle.getCostoEstandarUnitValeRepar());
							BigDecimal costoTotalValReparDomicP = detalle.getCostoEstandarUnitValeRepar().multiply(new BigDecimal(detalle.getNumeroValesRepartidosDomi()));
							formato12BBean.setCostoTotalValReparDomicP(costoTotalValReparDomicP);
							
							formato12BBean.setNroValEntDisElP(detalle.getNumeroValesEntregadoDisEl()!=null ?detalle.getNumeroValesEntregadoDisEl():0);
							formato12BBean.setCostoUnitValEntDisElP(detalle.getCostoEstandarUnitValDisEl());
							BigDecimal costoTotalValEntDisElP=detalle.getCostoEstandarUnitValDisEl().multiply(new BigDecimal(detalle.getNumeroValesEntregadoDisEl()));
							formato12BBean.setCostoTotalValEntDisElP(costoTotalValEntDisElP);
							
							formato12BBean.setNroValFisiCanjP(detalle.getNumeroValesFisicosCanjeados()!=null ?detalle.getNumeroValesFisicosCanjeados():0);
							formato12BBean.setCostoUnitValFisiCanjP(detalle.getCostoEstandarUnitValFiCan());
							BigDecimal costoTotalValFisiCanjP = detalle.getCostoEstandarUnitValFiCan().multiply(new BigDecimal(detalle.getNumeroValesFisicosCanjeados()));
							formato12BBean.setCostoTotalValFisiCanjP(costoTotalValFisiCanjP);
							

							formato12BBean.setNroValDigitCanjP(detalle.getNumeroValesDigitalCanjeados()!=null ?detalle.getNumeroValesDigitalCanjeados():0);
							formato12BBean.setCostoUnitValDigitCanjP(detalle.getCostoEstandarUnitValDgCan());
							BigDecimal costoTotalValDigitCanjP = detalle.getCostoEstandarUnitValDgCan().multiply(new BigDecimal(detalle.getNumeroValesDigitalCanjeados()));
							formato12BBean.setCostoTotalValDigitCanjP(costoTotalValDigitCanjP);
							
							formato12BBean.setNroAtencionesP(detalle.getNumeroAtenciones()!=null ?detalle.getNumeroAtenciones():0);
							formato12BBean.setCostoUnitAtencionesP(detalle.getCostoEstandarUnitAtencion());
							BigDecimal costoTotalAtencionesP=detalle.getCostoEstandarUnitAtencion().multiply(new BigDecimal(detalle.getNumeroAtenciones()));
							formato12BBean.setCostoTotalAtencionesP(costoTotalAtencionesP);
							
							formato12BBean.setGestionAdmP(detalle.getTotalGestionAdministrativa());
							formato12BBean.setDesplPersonalP(detalle.getTotalDesplazamientoPersonal());
							formato12BBean.setActivExtraordP(detalle.getTotalActividadesExtraord());
							
							//
							totalImpresionesVal = totalImpresionesVal.add(formato12BBean.getCostoTotalValImpP());
							totalRepartValDomic = totalRepartValDomic.add(formato12BBean.getCostoTotalValReparDomicP());
							totalEntregaValDisEl =totalEntregaValDisEl.add(formato12BBean.getCostoTotalValEntDisElP());
							totalCanjeLiqValFisic =totalCanjeLiqValFisic.add(formato12BBean.getCostoTotalValFisiCanjP());
							totalCanjeLiqValDigit =totalCanjeLiqValDigit.add(formato12BBean.getCostoTotalValDigitCanjP());
							totalAtencionSolic = totalAtencionSolic.add(formato12BBean.getCostoTotalAtencionesP());
							totalGestionAdm =totalGestionAdm.add(formato12BBean.getGestionAdmP());
							totalDesplPersonal =totalDesplPersonal.add(formato12BBean.getDesplPersonalP());
							totalActiviExtraord =totalActiviExtraord.add(formato12BBean.getActivExtraordP());
						
					}else if( FiseConstants.ZONABENEF_LIMA == detalle.getId().getIdZonaBenef() ){
						 	formato12BBean.setNroValeImpL(detalle.getNumeroValesImpreso()!=null ?detalle.getNumeroValesImpreso():0);
						    formato12BBean.setCostoUnitValImpL(detalle.getCostoEstandarUnitValeImpre());
							BigDecimal costoTotalValImpL = detalle.getCostoEstandarUnitValeImpre().multiply(new BigDecimal(detalle.getNumeroValesImpreso()));
							formato12BBean.setCostoTotalValImpL(costoTotalValImpL);
						
							formato12BBean.setNroValReparDomicL(detalle.getNumeroValesRepartidosDomi()!=null ?detalle.getNumeroValesRepartidosDomi():0);
							formato12BBean.setCostoUnitValReparDomicL(detalle.getCostoEstandarUnitValeRepar());
							BigDecimal costoTotalValReparDomicL = detalle.getCostoEstandarUnitValeRepar().multiply(new BigDecimal(detalle.getNumeroValesRepartidosDomi()));
							formato12BBean.setCostoTotalValReparDomicL(costoTotalValReparDomicL);
							
							formato12BBean.setNroValEntDisElL(detalle.getNumeroValesEntregadoDisEl()!=null ?detalle.getNumeroValesEntregadoDisEl():0);
							formato12BBean.setCostoUnitValEntDisElL(detalle.getCostoEstandarUnitValDisEl());
							BigDecimal costoTotalValEntDisElL=detalle.getCostoEstandarUnitValDisEl().multiply(new BigDecimal(detalle.getNumeroValesEntregadoDisEl()));
							formato12BBean.setCostoTotalValEntDisElL(costoTotalValEntDisElL);
							
							formato12BBean.setNroValFisiCanjL(detalle.getNumeroValesFisicosCanjeados()!=null ?detalle.getNumeroValesFisicosCanjeados():0);
							formato12BBean.setCostoUnitValFisiCanjL(detalle.getCostoEstandarUnitValFiCan());
							BigDecimal costoTotalValFisiCanjL = detalle.getCostoEstandarUnitValFiCan().multiply(new BigDecimal(detalle.getNumeroValesFisicosCanjeados()));
							formato12BBean.setCostoTotalValFisiCanjL(costoTotalValFisiCanjL);
							

							formato12BBean.setNroValDigitCanjL(detalle.getNumeroValesDigitalCanjeados()!=null ?detalle.getNumeroValesDigitalCanjeados():0);
							formato12BBean.setCostoUnitValDigitCanjL(detalle.getCostoEstandarUnitValDgCan());
							BigDecimal costoTotalValDigitCanjL = detalle.getCostoEstandarUnitValDgCan().multiply(new BigDecimal(detalle.getNumeroValesDigitalCanjeados()));
							formato12BBean.setCostoTotalValDigitCanjL(costoTotalValDigitCanjL);
							
							formato12BBean.setNroAtencionesL(detalle.getNumeroAtenciones()!=null ?detalle.getNumeroAtenciones():0);
							formato12BBean.setCostoUnitAtencionesL(detalle.getCostoEstandarUnitAtencion());
							BigDecimal costoTotalAtencionesL=detalle.getCostoEstandarUnitAtencion().multiply(new BigDecimal(detalle.getNumeroAtenciones()));
							formato12BBean.setCostoTotalAtencionesL(costoTotalAtencionesL);
							
							formato12BBean.setGestionAdmL(detalle.getTotalGestionAdministrativa());
							formato12BBean.setDesplPersonalL(detalle.getTotalDesplazamientoPersonal());
							formato12BBean.setActivExtraordL(detalle.getTotalActividadesExtraord());
							
							//
							totalImpresionesVal = totalImpresionesVal.add(formato12BBean.getCostoTotalValImpL());
							totalRepartValDomic = totalRepartValDomic.add(formato12BBean.getCostoTotalValReparDomicL());
							totalEntregaValDisEl =totalEntregaValDisEl.add(formato12BBean.getCostoTotalValEntDisElL());
							totalCanjeLiqValFisic =totalCanjeLiqValFisic.add(formato12BBean.getCostoTotalValFisiCanjL());
							totalCanjeLiqValDigit =totalCanjeLiqValDigit.add(formato12BBean.getCostoTotalValDigitCanjL());
							totalAtencionSolic = totalAtencionSolic.add(formato12BBean.getCostoTotalAtencionesL());
							totalGestionAdm =totalGestionAdm.add(formato12BBean.getGestionAdmL());
							totalDesplPersonal =totalDesplPersonal.add(formato12BBean.getDesplPersonalL());
							totalActiviExtraord =totalActiviExtraord.add(formato12BBean.getActivExtraordL());
					}
				}
		 }
		 
		
		 formato12BBean.setTotalImpVal(totalImpresionesVal);
		 formato12BBean.setTotalValReparDomic(totalRepartValDomic);		
		 formato12BBean.setTotalValEntDisEl(totalEntregaValDisEl);
		 formato12BBean.setTotalValFisiCanjLiq(totalCanjeLiqValFisic);
		 formato12BBean.setTotalValDigitCanjLiq(totalCanjeLiqValDigit);
		 formato12BBean.setTotalSolictConsultReclam(totalAtencionSolic);
		 formato12BBean.setTotalGestionAdm(totalGestionAdm);
		 formato12BBean.setTotalDesplPersonal(totalDesplPersonal);
		 formato12BBean.setTotalActivExtraord(totalActiviExtraord);
		 //
		 Double total=formato12BBean.getTotalImpVal().doubleValue()+formato12BBean.getTotalValReparDomic().doubleValue()+
				 formato12BBean.getTotalValEntDisEl().doubleValue()+formato12BBean.getTotalValFisiCanjLiq().doubleValue()+
				 formato12BBean.getTotalValDigitCanjLiq().doubleValue()+formato12BBean.getTotalSolictConsultReclam().doubleValue()+
				 formato12BBean.getTotalGestionAdm().doubleValue()+formato12BBean.getTotalDesplPersonal().doubleValue()+
				 formato12BBean.getTotalActivExtraord().doubleValue();			 
		 formato12BBean.setTotalGeneral(new BigDecimal(total));
	
	
		 }catch(Exception e){
			 e.printStackTrace();
		 }
	
	return formato12BBean;
}


@Override
public HashMap<String, Object> mapearParametrosFormato12B(Formato12BCBean formato12BBean){
	
	HashMap<String, Object> mapJRParams = new HashMap<String, Object>();
	
	mapJRParams.put(FiseConstants.PARAM_DESC_EMPRESA_F12B, formato12BBean.getDescEmpresa());
	mapJRParams.put(FiseConstants.PARAM_ANO_PRES_F12B, formato12BBean.getAnioPresent());
	mapJRParams.put(FiseConstants.PARAM_DESC_MES_PRES_F12B, formato12BBean.getDescMesPresentacion());
	mapJRParams.put(FiseConstants.PARAM_ANO_EJEC_F12B, formato12BBean.getAnioEjecuc());
	mapJRParams.put(FiseConstants.PARAM_DESC_MES_EJEC_F12B, formato12BBean.getDescMesEjecucion());
	mapJRParams.put(FiseConstants.ETAPA_SOLICITUD, formato12BBean.getEtapa());
	//
	
	
	mapJRParams.put(FiseConstants.PARAM_NRO_VAL_IMP_R_F12B, formato12BBean.getNroValeImpR());
	mapJRParams.put(FiseConstants.PARAM_NRO_VAL_IMP_P_F12B, formato12BBean.getNroValeImpP());
	mapJRParams.put(FiseConstants.PARAM_NRO_VAL_IMP_L_F12B, formato12BBean.getNroValeImpL());
	
	mapJRParams.put(FiseConstants.PARAM_COSTO_UNIT_VAL_IMP_R_F12B, formato12BBean.getCostoUnitValImpR());
	mapJRParams.put(FiseConstants.PARAM_COSTO_UNIT_VAL_IMP_P_F12B, formato12BBean.getCostoUnitValImpP());
	mapJRParams.put(FiseConstants.PARAM_COSTO_UNIT_VAL_IMP_L_F12B, formato12BBean.getCostoUnitValImpL());

	
	
	mapJRParams.put(FiseConstants.PARAM_COSTO_TOTAL_VAL_IMP_R_F12B, formato12BBean.getCostoTotalValImpR());
	mapJRParams.put(FiseConstants.PARAM_COSTO_TOTAL_VAL_IMP_P_F12B, formato12BBean.getCostoTotalValImpP());
	mapJRParams.put(FiseConstants.PARAM_COSTO_TOTAL_VAL_IMP_L_F12B, formato12BBean.getCostoTotalValImpL());
	
	mapJRParams.put(FiseConstants.PARAM_NUM_VAL_REPAR_DOMIC_R_F12B, formato12BBean.getNroValReparDomicR());
	mapJRParams.put(FiseConstants.PARAM_NUM_VAL_REPAR_DOMIC_P_F12B, formato12BBean.getNroValReparDomicP());
	mapJRParams.put(FiseConstants.PARAM_NUM_VAL_REPAR_DOMIC_L_F12B, formato12BBean.getNroValReparDomicL());
	
	mapJRParams.put(FiseConstants.PARAM_COSTO_UNIT_VAL_REPAR_DOMIC_R_F12B, formato12BBean.getCostoUnitValReparDomicR());
	mapJRParams.put(FiseConstants.PARAM_COSTO_UNIT_VAL_REPAR_DOMIC_P_F12B, formato12BBean.getCostoUnitValReparDomicP());
	mapJRParams.put(FiseConstants.PARAM_COSTO_UNIT_VAL_REPAR_DOMIC_L_F12B, formato12BBean.getCostoUnitValReparDomicL());
	
	mapJRParams.put(FiseConstants.PARAM_COSTO_TOTAL_VAL_REPAR_DOMIC_R_F12B, formato12BBean.getCostoTotalValReparDomicR());
	mapJRParams.put(FiseConstants.PARAM_COSTO_TOTAL_VAL_REPAR_DOMIC_P_F12B, formato12BBean.getCostoTotalValReparDomicP());
	mapJRParams.put(FiseConstants.PARAM_COSTO_TOTAL_VAL_REPAR_DOMIC_L_F12B, formato12BBean.getCostoTotalValReparDomicL());
	
	
	mapJRParams.put(FiseConstants.PARAM_NUM_VAL_ENTREG_EDE_R_F12B, formato12BBean.getNroValEntDisElR());
	mapJRParams.put(FiseConstants.PARAM_NUM_VAL_ENTREG_EDE_P_F12B, formato12BBean.getNroValEntDisElP());
	mapJRParams.put(FiseConstants.PARAM_NUM_VAL_ENTREG_EDE_L_F12B, formato12BBean.getNroValEntDisElL());
	
	mapJRParams.put(FiseConstants.PARAM_COSTO_UNIT_VAL_ENTREG_EDE_R_F12B, formato12BBean.getCostoUnitValEntDisElR());
	mapJRParams.put(FiseConstants.PARAM_COSTO_UNIT_VAL_ENTREG_EDE_P_F12B, formato12BBean.getCostoUnitValEntDisElP());
	mapJRParams.put(FiseConstants.PARAM_COSTO_UNIT_VAL_ENTREG_EDE_L_F12B, formato12BBean.getCostoUnitValEntDisElL());
	
	mapJRParams.put(FiseConstants.PARAM_COSTO_TOTAL_VAL_ENTREG_EDE_R_F12B, formato12BBean.getCostoTotalValEntDisElR());
	mapJRParams.put(FiseConstants.PARAM_COSTO_TOTAL_VAL_ENTREG_EDE_P_F12B, formato12BBean.getCostoTotalValEntDisElP());
	mapJRParams.put(FiseConstants.PARAM_COSTO_TOTAL_VAL_ENTREG_EDE_L_F12B, formato12BBean.getCostoTotalValEntDisElL());
	
	mapJRParams.put(FiseConstants.PARAM_NUM_VAL_FISIC_CANJ_R_F12B, formato12BBean.getNroValFisiCanjR());
	mapJRParams.put(FiseConstants.PARAM_NUM_VAL_FISIC_CANJ_P_F12B, formato12BBean.getNroValFisiCanjP());
	mapJRParams.put(FiseConstants.PARAM_NUM_VAL_FISIC_CANJ_L_F12B, formato12BBean.getNroValFisiCanjL());
	
	mapJRParams.put(FiseConstants.PARAM_COSTO_UNIT_VAL_FISIC_CANJ_R_F12B, formato12BBean.getCostoUnitValFisiCanjR());
	mapJRParams.put(FiseConstants.PARAM_COSTO_UNIT_VAL_FISIC_CANJ_P_F12B, formato12BBean.getCostoUnitValFisiCanjP());
	mapJRParams.put(FiseConstants.PARAM_COSTO_UNIT_VAL_FISIC_CANJ_L_F12B, formato12BBean.getCostoUnitValFisiCanjL());
	
	mapJRParams.put(FiseConstants.PARAM_COSTO_TOTAL_VAL_FISIC_CANJ_R_F12B, formato12BBean.getCostoTotalValFisiCanjR());
	mapJRParams.put(FiseConstants.PARAM_COSTO_TOTAL_VAL_FISIC_CANJ_P_F12B, formato12BBean.getCostoTotalValFisiCanjP());
	mapJRParams.put(FiseConstants.PARAM_COSTO_TOTAL_VAL_FISIC_CANJ_L_F12B, formato12BBean.getCostoTotalValFisiCanjL());
	
	mapJRParams.put(FiseConstants.PARAM_NUM_VAL_DIGIT_CANJ_R_F12B, formato12BBean.getNroValDigitCanjR());
	mapJRParams.put(FiseConstants.PARAM_NUM_VAL_DIGIT_CANJ_P_F12B, formato12BBean.getNroValDigitCanjP());
	mapJRParams.put(FiseConstants.PARAM_NUM_VAL_DIGIT_CANJ_L_F12B, formato12BBean.getNroValDigitCanjL());
	
	mapJRParams.put(FiseConstants.PARAM_COSTO_UNIT_VAL_DIGIT_CANJ_R_F12B, formato12BBean.getCostoUnitValDigitCanjR());
	mapJRParams.put(FiseConstants.PARAM_COSTO_UNIT_VAL_DIGIT_CANJ_P_F12B, formato12BBean.getCostoUnitValDigitCanjP());
	mapJRParams.put(FiseConstants.PARAM_COSTO_UNIT_VAL_DIGIT_CANJ_L_F12B, formato12BBean.getCostoUnitValDigitCanjL());
	
	mapJRParams.put(FiseConstants.PARAM_COSTO_TOTAL_VAL_DIGIT_CANJ_R_F12B, formato12BBean.getCostoTotalValDigitCanjR());
	mapJRParams.put(FiseConstants.PARAM_COSTO_TOTAL_VAL_DIGIT_CANJ_P_F12B, formato12BBean.getCostoTotalValDigitCanjP());
	mapJRParams.put(FiseConstants.PARAM_COSTO_TOTAL_VAL_DIGIT_CANJ_L_F12B, formato12BBean.getCostoTotalValDigitCanjL());
	
	mapJRParams.put(FiseConstants.PARAM_NUM_ATENCIONES_R_F12B, formato12BBean.getNroAtencionesR());
	mapJRParams.put(FiseConstants.PARAM_NUM_ATENCIONES_P_F12B, formato12BBean.getNroAtencionesP());
	mapJRParams.put(FiseConstants.PARAM_NUM_ATENCIONES_L_F12B, formato12BBean.getNroAtencionesL());
	
	mapJRParams.put(FiseConstants.PARAM_COSTO_UNIT_ATENCIONES_R_F12B, formato12BBean.getCostoUnitAtencionesR());
	mapJRParams.put(FiseConstants.PARAM_COSTO_UNIT_ATENCIONES_P_F12B, formato12BBean.getCostoUnitAtencionesP());
	mapJRParams.put(FiseConstants.PARAM_COSTO_UNIT_ATENCIONES_L_F12B, formato12BBean.getCostoUnitAtencionesL());
	
	mapJRParams.put(FiseConstants.PARAM_COSTO_TOTAL_ATENCIONES_R_F12B, formato12BBean.getCostoTotalAtencionesR());
	mapJRParams.put(FiseConstants.PARAM_COSTO_TOTAL_ATENCIONES_P_F12B, formato12BBean.getCostoTotalAtencionesP());
	mapJRParams.put(FiseConstants.PARAM_COSTO_TOTAL_ATENCIONES_L_F12B, formato12BBean.getCostoTotalAtencionesL());
	
	System.out.println("costos unitarios ::"+formato12BBean.getCostoUnitAtencionesL());
	System.out.println("des mes presentacion::"+formato12BBean.getCostoUnitAtencionesP());
	
	mapJRParams.put(FiseConstants.PARAM_GESTION_ADM_R_F12B, formato12BBean.getGestionAdmR());
	mapJRParams.put(FiseConstants.PARAM_GESTION_ADM_P_F12B, formato12BBean.getGestionAdmP());
	mapJRParams.put(FiseConstants.PARAM_GESTION_ADM_L_F12B, formato12BBean.getGestionAdmL());

	mapJRParams.put(FiseConstants.PARAM_DESPL_PERSONAL_R_F12B, formato12BBean.getDesplPersonalR());
	mapJRParams.put(FiseConstants.PARAM_DESPL_PERSONAL_P_F12B, formato12BBean.getDesplPersonalP());
	mapJRParams.put(FiseConstants.PARAM_DESPL_PERSONAL_L_F12B, formato12BBean.getDesplPersonalL());
	
	mapJRParams.put(FiseConstants.PARAM_ACTIV_EXTRAORD_R_F12B, formato12BBean.getActivExtraordR());
	mapJRParams.put(FiseConstants.PARAM_ACTIV_EXTRAORD_P_F12B, formato12BBean.getActivExtraordP());
	mapJRParams.put(FiseConstants.PARAM_ACTIV_EXTRAORD_L_F12B, formato12BBean.getActivExtraordL());
	
	mapJRParams.put(FiseConstants.PARAM_TOTAL_VAL_IMP_F12B, formato12BBean.getTotalImpVal());
	mapJRParams.put(FiseConstants.PARAM_TOTAL_VAL_REPAR_DOMIC_F12B, formato12BBean.getTotalValReparDomic());
	mapJRParams.put(FiseConstants.PARAM_TOTAL_VAL_ENTREG_EDE_F12B, formato12BBean.getTotalValEntDisEl());
	mapJRParams.put(FiseConstants.PARAM_TOTAL_VAL_FISIC_CANJ_LIQ_F12B, formato12BBean.getTotalValFisiCanjLiq());
	mapJRParams.put(FiseConstants.PARAM_TOTAL_VAL_DIGIT_CANJ_LIQ_F12B, formato12BBean.getTotalValDigitCanjLiq());
	mapJRParams.put(FiseConstants.PARAM_TOTAL_SOLICIT_CONSULT_RECLAM_F12B, formato12BBean.getTotalSolictConsultReclam());
	mapJRParams.put(FiseConstants.PARAM_TOTAL_GESTION_ADM_F12B, formato12BBean.getTotalGestionAdm());
	mapJRParams.put(FiseConstants.PARAM_TOTAL_DESPL_PERSONAL_F12B, formato12BBean.getTotalDesplPersonal());
	mapJRParams.put(FiseConstants.PARAM_TOTAL_ACTIV_EXTRAORD_F12B, formato12BBean.getTotalActivExtraord());
	
	mapJRParams.put(FiseConstants.PARAM_TOTAL_GENERAL_F12B, formato12BBean.getTotalGeneral());
	
	return mapJRParams;
}

@Override
@Transactional(propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
public String modificarEnvioDefinitivoFormato12BC(String user,String terminal, 
		FiseFormato12BC fiseFormato12BC) throws Exception {	
	String valor = "0";	
	try{
		fiseFormato12BC.setFechaEnvioDefinitivo(FechaUtil.obtenerFechaActual());
		fiseFormato12BC.setUsuarioActualizacion(user);
		fiseFormato12BC.setTerminalActualizacion(terminal);
		fiseFormato12BC.setFechaActualizacion(FechaUtil.obtenerFechaActual());
		formato12BCDao.updateFormatoCabecera(fiseFormato12BC);
		valor = "1";
	} catch (Exception e) {
		valor = "0";
		e.printStackTrace();		
	}
	return valor;
}

	@Override
	@Transactional
	public Integer deleteFormatoObs(String emp, Integer anio, Integer mes, String etapa, Integer anioEjec, Integer mesEjec, Integer idzona, Integer item) throws DataIntegrityViolationException, Exception {
		
		return formato12BDObDao.deleteFormatoObs(emp, anio, mes, etapa, anioEjec, mesEjec,idzona,item);
	}


	@Override
	@Transactional(propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
	public void eliminarObservaciones12B(List<FiseFormato12BDOb> listaObs) throws Exception {	
		for (FiseFormato12BDOb o : listaObs) {
			formato12BDObDao.deleteFormatoObs(o.getId().getCodEmpresa(),o.getId().getAnoPresentacion(),
					o.getId().getMesPresentacion(),o.getId().getEtapa(),o.getId().getAnoEjecucionGasto(),
					o.getId().getMesEjecucionGasto(),o.getId().getIdZonaBenef()
					,o.getId().getItemObservacion());
		}
	} 
	
	
	@Override
	@Transactional(propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
	public String insertarObservacion12B(String codEmpresa,Integer anioPres,Integer mesPres,
			Integer anioEjec,Integer mesEjec,String etapa,Integer idZona, 
			String desObservacion,String user,String terminal) throws Exception{
		String valor = "0";
		FiseObservacion observacion = null;
		FiseFormato12BDObPK pk = null;
		FiseFormato12BDOb obs = null;
		try {
			long maxItemObs = formato12BDObDao.buscarMaximoItemObs12B(codEmpresa, anioPres, 
					mesPres, anioEjec, mesEjec, etapa, idZona);			
			String idObservacion = fiseObservacionDao.obtenerIdObservacion();			
			observacion = new FiseObservacion();
			observacion.setIdObservacion(idObservacion); 
			observacion.setDescripcion(desObservacion);
			observacion.setOrigen(FiseConstants.OBSERVACION_MANUAL); 
			observacion.setUsuarioCreacion(user);
			observacion.setTerminalCreacion(terminal); 
			observacion.setFechaCreacion(FechaUtil.obtenerFechaActual()); 
			fiseObservacionDao.insertarFiseObservacion(observacion);
			pk = new FiseFormato12BDObPK();
			pk.setCodEmpresa(codEmpresa);
			pk.setAnoPresentacion(anioPres);
			pk.setMesPresentacion(mesPres);
			pk.setAnoEjecucionGasto(anioEjec);
			pk.setMesEjecucionGasto(mesEjec);
			pk.setEtapa(etapa);
			pk.setIdZonaBenef(idZona);		
			pk.setItemObservacion((int)maxItemObs);  	
			obs = new FiseFormato12BDOb();
			obs.setId(pk);
			obs.setFechaCreacion(FechaUtil.obtenerFechaActual());			
			obs.setFiseObservacion(observacion);
			obs.setUsuarioCreacion(user);
			obs.setTerminalCreacion(terminal);		
			formato12BDObDao.insertarFiseFormato12BObs(obs); 
			valor = "1";
		} catch (Exception e) {
			valor = "0";
			e.printStackTrace();
		}finally{
			if(observacion!=null){
				observacion=null;
			}
			if(pk!=null){
				pk=null;
			}
			if(obs!=null){
				obs=null;
			}
		}
		return valor;
	}
	
	
	@Override
	@Transactional(propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
	public String eliminarObservacion12B(String codEmpresa,Integer anioPres,Integer mesPres,
			Integer anioEjec,Integer mesEjec,String etapa,Integer idZona, 
			String idObservacion,Integer itemObservacion) throws Exception{
		String valor = "0";
		FiseObservacion observacion = null;
		FiseFormato12BDObPK pk = null;
		FiseFormato12BDOb obs = null;
		try {			
			pk = new FiseFormato12BDObPK();
			pk.setCodEmpresa(codEmpresa);
			pk.setAnoPresentacion(anioPres);
			pk.setMesPresentacion(mesPres);
			pk.setAnoEjecucionGasto(anioEjec);
			pk.setMesEjecucionGasto(mesEjec);
			pk.setEtapa(etapa);
			pk.setIdZonaBenef(idZona);		
			pk.setItemObservacion(itemObservacion);  	
			obs = formato12BDObDao.obtenerFiseFormato12BDOb(pk);				
			formato12BDObDao.eliminarFiseFormato12BDOb(obs);  
			observacion = fiseObservacionDao.obtenerFiseObservacion(idObservacion);			
			fiseObservacionDao.eliminarFiseObservacion(observacion);	
			valor = "1";
		} catch (Exception e) {
			valor = "0";
			e.printStackTrace();
		}finally{
			if(observacion!=null){
				observacion=null;
			}
			if(pk!=null){
				pk=null;
			}
			if(obs!=null){
				obs=null;
			}
		}
		return valor;
	}
	
	
	/***************************/
	
	@Override
	@Transactional(propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
	public FiseFormato12BC registrarFormato12BC(Formato12BCBean formulario) throws Exception {
		
		FiseFormato12BC dto = null;
		
		try {
			Date hoy = FechaUtil.obtenerFechaActual();
			FiseFormato12BC fiseFormato12BC = new FiseFormato12BC();
			//guardar el pk
			FiseFormato12BCPK id = new FiseFormato12BCPK();
			id.setCodEmpresa(formulario.getCodigoEmpresa());
			id.setAnoPresentacion((int)formulario.getAnioPresent());
			id.setMesPresentacion((int)formulario.getMesPresent());
			id.setAnoEjecucionGasto(id.getAnoPresentacion());
			id.setMesEjecucionGasto(id.getMesPresentacion());			
			id.setEtapa(formulario.getEtapa());
			if( FiseConstants.TIPOARCHIVO_XLS.equals(formulario.getTipoArchivo()) ){
				fiseFormato12BC.setNombreArchivoExcel(formulario.getNombreArchivo());
			}else if( FiseConstants.TIPOARCHIVO_TXT.equals(formulario.getTipoArchivo()) ){
				fiseFormato12BC.setNombreArchivoTexto(formulario.getNombreArchivo());
			}
			
			fiseFormato12BC.setId(id);
			
			FiseGrupoInformacion grupoInfo = null;
			long idGrupoInf = commonDao.obtenerIdGrupoInformacion(formulario.getAnioPresent(), formulario.getMesPresent(), FiseConstants.FRECUENCIA_MENSUAL_DESCRIPCION); 
			if(idGrupoInf!=0){
				grupoInfo = fiseGrupoInformacionDao.obtenerFiseGrupoInformacionByPK(idGrupoInf);	
			}	
			fiseFormato12BC.setFiseGrupoInformacion(grupoInfo);  
			
			
			List<FiseFormato12BD> lista = new ArrayList<FiseFormato12BD>();
			
			//RURAL
			boolean cambio = true; //cambio elozano
			
			/*if( formulario.getNroValeImpR() != 0 ||
					formulario.getNroValReparDomicR() != 0 ||
					formulario.getNroValEntDisElR() != 0 ||
					formulario.getNroValFisiCanjR() != 0 ||
					formulario.getNroValDigitCanjR() != 0 ||
					formulario.getNroAtencionesR() != 0 ){*/
			 if(cambio){ 	
				logger.info("entro a RURAL");
				FiseZonaBenef zonaBenef = new FiseZonaBenef();
				zonaBenef = zonaBenefDao.obtenerFiseZonaBenefByPK(FiseConstants.ZONABENEF_RURAL_COD);
				//
				FiseFormato12BD detalleRural = new FiseFormato12BD();
				//pk
				FiseFormato12BDPK pkDetalle = new FiseFormato12BDPK();
				pkDetalle.setCodEmpresa(fiseFormato12BC.getId().getCodEmpresa());
				pkDetalle.setAnoPresentacion(fiseFormato12BC.getId().getAnoPresentacion());
				pkDetalle.setMesPresentacion(fiseFormato12BC.getId().getMesPresentacion());
				pkDetalle.setAnoEjecucionGasto(fiseFormato12BC.getId().getAnoEjecucionGasto());
				pkDetalle.setMesEjecucionGasto(fiseFormato12BC.getId().getMesEjecucionGasto());
				pkDetalle.setIdZonaBenef((int)zonaBenef.getIdZonaBenef());
				pkDetalle.setEtapa(fiseFormato12BC.getId().getEtapa());
				detalleRural.setId(pkDetalle);
				
				//impresion vales
				detalleRural.setNumeroValesImpreso((int)formulario.getNroValeImpR());
				detalleRural.setCostoEstandarUnitValeImpre(formulario.getCostoUnitValImpR());
				BigDecimal totalImpresion = detalleRural.getCostoEstandarUnitValeImpre().multiply(new BigDecimal(detalleRural.getNumeroValesImpreso()));
				detalleRural.setCostoTotalImpresionVale(totalImpresion);
				//reparto vales
				detalleRural.setNumeroValesRepartidosDomi((int)formulario.getNroValReparDomicR());
				detalleRural.setCostoEstandarUnitValeRepar(formulario.getCostoUnitValReparDomicR());
				BigDecimal totalReparto = detalleRural.getCostoEstandarUnitValeRepar().multiply(new BigDecimal(detalleRural.getNumeroValesRepartidosDomi()));
				detalleRural.setCostoTotalRepartoValesDomi(totalReparto);
				//entrega vales
				detalleRural.setNumeroValesEntregadoDisEl((int)formulario.getNroValEntDisElR());
				detalleRural.setCostoEstandarUnitValDisEl(formulario.getCostoUnitValEntDisElR());
				BigDecimal totalEntregados = detalleRural.getCostoEstandarUnitValDisEl().multiply(new BigDecimal(detalleRural.getNumeroValesEntregadoDisEl()));
				detalleRural.setCostoTotalEntregaValDisEl(totalEntregados);
				//vales fisicos
				detalleRural.setNumeroValesFisicosCanjeados((int)formulario.getNroValFisiCanjR());
				detalleRural.setCostoEstandarUnitValFiCan(formulario.getCostoUnitValFisiCanjR());
				BigDecimal totalFisicos = detalleRural.getCostoEstandarUnitValFiCan().multiply(new BigDecimal(detalleRural.getNumeroValesFisicosCanjeados()));
				detalleRural.setCostoTotalCanjeLiqValeFis(totalFisicos);
				//vales digitales
				detalleRural.setNumeroValesDigitalCanjeados((int)formulario.getNroValDigitCanjR());
				detalleRural.setCostoEstandarUnitValDgCan(formulario.getCostoUnitValDigitCanjR());
				BigDecimal totalDigitales = detalleRural.getCostoEstandarUnitValDgCan().multiply(new BigDecimal(detalleRural.getNumeroValesDigitalCanjeados()));
				detalleRural.setCostoTotalCanjeLiqValeDig(totalDigitales);
				//atencion
				detalleRural.setNumeroAtenciones((int)formulario.getNroAtencionesR());
				detalleRural.setCostoEstandarUnitAtencion(formulario.getCostoUnitAtencionesR());
				BigDecimal totalAtenciones = detalleRural.getCostoEstandarUnitAtencion().multiply(new BigDecimal(detalleRural.getNumeroAtenciones()));
				detalleRural.setCostoTotalAtencionConsRecl(totalAtenciones);
				//gestion administrativa
				detalleRural.setTotalGestionAdministrativa(formulario.getGestionAdmR());
				detalleRural.setTotalDesplazamientoPersonal(formulario.getDesplPersonalR());
				detalleRural.setTotalActividadesExtraord(formulario.getActivExtraordR());
				
				BigDecimal totalRural = new BigDecimal(0);
				totalRural = totalRural.add(detalleRural.getCostoTotalImpresionVale())
						.add(detalleRural.getCostoTotalRepartoValesDomi())
						.add(detalleRural.getCostoTotalEntregaValDisEl())
						.add(detalleRural.getCostoTotalCanjeLiqValeFis())
						.add(detalleRural.getCostoTotalCanjeLiqValeDig())
						.add(detalleRural.getCostoTotalAtencionConsRecl())
						.add(detalleRural.getTotalGestionAdministrativa())
						.add(detalleRural.getTotalDesplazamientoPersonal())
						.add(detalleRural.getTotalActividadesExtraord());
				detalleRural.setTotalReconocer(totalRural);
				//
				detalleRural.setFiseFormato12BC(fiseFormato12BC);
				//detalleRural.setFiseZonaBenef(zonaBenef);
				//
				detalleRural.setUsuarioCreacion(formulario.getUsuario());
				detalleRural.setTerminalCreacion(formulario.getTerminal());
				detalleRural.setFechaCreacion(hoy);
				detalleRural.setUsuarioActualizacion(formulario.getUsuario());
				detalleRural.setTerminalActualizacion(formulario.getTerminal());
				detalleRural.setFechaActualizacion(hoy);
				lista.add(detalleRural);
			}
			//PROVINCIA
			/*if( formulario.getNroValeImpP() != 0 ||
					formulario.getNroValReparDomicP() != 0 ||
					formulario.getNroValEntDisElP() != 0 ||
					formulario.getNroValFisiCanjP() != 0 ||
					formulario.getNroValDigitCanjP() != 0 ||
					formulario.getNroAtencionesP() != 0 ){*/
			 if(cambio){
				logger.info("entro a PROVINCIA");
				FiseZonaBenef zonaBenef = new FiseZonaBenef();
				zonaBenef = zonaBenefDao.obtenerFiseZonaBenefByPK(FiseConstants.ZONABENEF_PROVINCIA_COD);
				//
				FiseFormato12BD detalleProvincia = new FiseFormato12BD();
				//pk
				FiseFormato12BDPK pkDetalle = new FiseFormato12BDPK();
				pkDetalle.setCodEmpresa(fiseFormato12BC.getId().getCodEmpresa());
				pkDetalle.setAnoPresentacion(fiseFormato12BC.getId().getAnoPresentacion());
				pkDetalle.setMesPresentacion(fiseFormato12BC.getId().getMesPresentacion());
				pkDetalle.setAnoEjecucionGasto(fiseFormato12BC.getId().getAnoEjecucionGasto());
				pkDetalle.setMesEjecucionGasto(fiseFormato12BC.getId().getMesEjecucionGasto());
				pkDetalle.setIdZonaBenef((int)zonaBenef.getIdZonaBenef());
				pkDetalle.setEtapa(fiseFormato12BC.getId().getEtapa());
				detalleProvincia.setId(pkDetalle);
				
				//impresion vales
				detalleProvincia.setNumeroValesImpreso((int)formulario.getNroValeImpP());
				detalleProvincia.setCostoEstandarUnitValeImpre(formulario.getCostoUnitValImpP());
				BigDecimal totalImpresion = detalleProvincia.getCostoEstandarUnitValeImpre().multiply(new BigDecimal(detalleProvincia.getNumeroValesImpreso()));
				detalleProvincia.setCostoTotalImpresionVale(totalImpresion);
				//reparto vales
				detalleProvincia.setNumeroValesRepartidosDomi((int)formulario.getNroValReparDomicP());
				detalleProvincia.setCostoEstandarUnitValeRepar(formulario.getCostoUnitValReparDomicP());
				BigDecimal totalReparto = detalleProvincia.getCostoEstandarUnitValeRepar().multiply(new BigDecimal(detalleProvincia.getNumeroValesRepartidosDomi()));
				detalleProvincia.setCostoTotalRepartoValesDomi(totalReparto);
				//entrega vales
				detalleProvincia.setNumeroValesEntregadoDisEl((int)formulario.getNroValEntDisElP());
				detalleProvincia.setCostoEstandarUnitValDisEl(formulario.getCostoUnitValEntDisElP());
				BigDecimal totalEntregados = detalleProvincia.getCostoEstandarUnitValDisEl().multiply(new BigDecimal(detalleProvincia.getNumeroValesEntregadoDisEl()));
				detalleProvincia.setCostoTotalEntregaValDisEl(totalEntregados);
				//vales fisicos
				detalleProvincia.setNumeroValesFisicosCanjeados((int)formulario.getNroValFisiCanjP());
				detalleProvincia.setCostoEstandarUnitValFiCan(formulario.getCostoUnitValFisiCanjP());
				BigDecimal totalFisicos = detalleProvincia.getCostoEstandarUnitValFiCan().multiply(new BigDecimal(detalleProvincia.getNumeroValesFisicosCanjeados()));
				detalleProvincia.setCostoTotalCanjeLiqValeFis(totalFisicos);
				//vales digitales
				detalleProvincia.setNumeroValesDigitalCanjeados((int)formulario.getNroValDigitCanjP());
				detalleProvincia.setCostoEstandarUnitValDgCan(formulario.getCostoUnitValDigitCanjP());
				BigDecimal totalDigitales = detalleProvincia.getCostoEstandarUnitValDgCan().multiply(new BigDecimal(detalleProvincia.getNumeroValesDigitalCanjeados()));
				detalleProvincia.setCostoTotalCanjeLiqValeDig(totalDigitales);
				//atencion
				detalleProvincia.setNumeroAtenciones((int)formulario.getNroAtencionesP());
				detalleProvincia.setCostoEstandarUnitAtencion(formulario.getCostoUnitAtencionesP());
				BigDecimal totalAtenciones = detalleProvincia.getCostoEstandarUnitAtencion().multiply(new BigDecimal(detalleProvincia.getNumeroAtenciones()));
				detalleProvincia.setCostoTotalAtencionConsRecl(totalAtenciones);
				//gestion administrativa
				detalleProvincia.setTotalGestionAdministrativa(formulario.getGestionAdmP());
				detalleProvincia.setTotalDesplazamientoPersonal(formulario.getDesplPersonalP());
				detalleProvincia.setTotalActividadesExtraord(formulario.getActivExtraordP());
				
				BigDecimal totalProvincia = new BigDecimal(0);
				totalProvincia = totalProvincia.add(detalleProvincia.getCostoTotalImpresionVale())
						.add(detalleProvincia.getCostoTotalRepartoValesDomi())
						.add(detalleProvincia.getCostoTotalEntregaValDisEl())
						.add(detalleProvincia.getCostoTotalCanjeLiqValeFis())
						.add(detalleProvincia.getCostoTotalCanjeLiqValeDig())
						.add(detalleProvincia.getCostoTotalAtencionConsRecl())
						.add(detalleProvincia.getTotalGestionAdministrativa())
						.add(detalleProvincia.getTotalDesplazamientoPersonal())
						.add(detalleProvincia.getTotalActividadesExtraord());
				detalleProvincia.setTotalReconocer(totalProvincia);
				//
				detalleProvincia.setFiseFormato12BC(fiseFormato12BC);
				//detalleProvincia.setFiseZonaBenef(zonaBenef);
				//
				detalleProvincia.setUsuarioCreacion(formulario.getUsuario());
				detalleProvincia.setTerminalCreacion(formulario.getTerminal());
				detalleProvincia.setFechaCreacion(hoy);
				detalleProvincia.setUsuarioActualizacion(formulario.getUsuario());
				detalleProvincia.setTerminalActualizacion(formulario.getTerminal());
				detalleProvincia.setFechaActualizacion(hoy);
				lista.add(detalleProvincia);
			}
			//LIMA
			/*if( formulario.getNroValeImpL() != 0 ||
					formulario.getNroValReparDomicL() != 0 ||
					formulario.getNroValEntDisElL() != 0 ||
					formulario.getNroValFisiCanjL() != 0 ||
					formulario.getNroValDigitCanjL() != 0 ||
					formulario.getNroAtencionesL() != 0){*/
			 if(FiseConstants.COD_EMPRESA_EDELNOR.equals(formulario.getCodigoEmpresa()) || 
						FiseConstants.COD_EMPRESA_LUZ_SUR.equals(formulario.getCodigoEmpresa().trim()))
				{
				logger.info("entro a LIMA");
				FiseZonaBenef zonaBenef = new FiseZonaBenef();
				zonaBenef = zonaBenefDao.obtenerFiseZonaBenefByPK(FiseConstants.ZONABENEF_LIMA_COD);
				//
				FiseFormato12BD detalleLima = new FiseFormato12BD();
				//pk
				FiseFormato12BDPK pkDetalle = new FiseFormato12BDPK();
				pkDetalle.setCodEmpresa(fiseFormato12BC.getId().getCodEmpresa());
				pkDetalle.setAnoPresentacion(fiseFormato12BC.getId().getAnoPresentacion());
				pkDetalle.setMesPresentacion(fiseFormato12BC.getId().getMesPresentacion());
				pkDetalle.setAnoEjecucionGasto(fiseFormato12BC.getId().getAnoEjecucionGasto());
				pkDetalle.setMesEjecucionGasto(fiseFormato12BC.getId().getMesEjecucionGasto());
				pkDetalle.setIdZonaBenef((int)zonaBenef.getIdZonaBenef());
				pkDetalle.setEtapa(fiseFormato12BC.getId().getEtapa());
				detalleLima.setId(pkDetalle);
				
				//impresion vales
				detalleLima.setNumeroValesImpreso((int)formulario.getNroValeImpL());
				detalleLima.setCostoEstandarUnitValeImpre(formulario.getCostoUnitValImpL());
				BigDecimal totalImpresion = detalleLima.getCostoEstandarUnitValeImpre().multiply(new BigDecimal(detalleLima.getNumeroValesImpreso()));
				detalleLima.setCostoTotalImpresionVale(totalImpresion);
				//reparto vales
				detalleLima.setNumeroValesRepartidosDomi((int)formulario.getNroValReparDomicL());
				detalleLima.setCostoEstandarUnitValeRepar(formulario.getCostoUnitValReparDomicL());
				BigDecimal totalReparto = detalleLima.getCostoEstandarUnitValeRepar().multiply(new BigDecimal(detalleLima.getNumeroValesRepartidosDomi()));
				detalleLima.setCostoTotalRepartoValesDomi(totalReparto);
				//entrega vales
				detalleLima.setNumeroValesEntregadoDisEl((int)formulario.getNroValEntDisElL());
				detalleLima.setCostoEstandarUnitValDisEl(formulario.getCostoUnitValEntDisElL());
				BigDecimal totalEntregados = detalleLima.getCostoEstandarUnitValDisEl().multiply(new BigDecimal(detalleLima.getNumeroValesEntregadoDisEl()));
				detalleLima.setCostoTotalEntregaValDisEl(totalEntregados);
				//vales fisicos
				detalleLima.setNumeroValesFisicosCanjeados((int)formulario.getNroValFisiCanjL());
				detalleLima.setCostoEstandarUnitValFiCan(formulario.getCostoUnitValFisiCanjL());
				BigDecimal totalFisicos = detalleLima.getCostoEstandarUnitValFiCan().multiply(new BigDecimal(detalleLima.getNumeroValesFisicosCanjeados()));
				detalleLima.setCostoTotalCanjeLiqValeFis(totalFisicos);
				//vales digitales
				detalleLima.setNumeroValesDigitalCanjeados((int)formulario.getNroValDigitCanjL());
				detalleLima.setCostoEstandarUnitValDgCan(formulario.getCostoUnitValDigitCanjL());
				BigDecimal totalDigitales = detalleLima.getCostoEstandarUnitValDgCan().multiply(new BigDecimal(detalleLima.getNumeroValesDigitalCanjeados()));
				detalleLima.setCostoTotalCanjeLiqValeDig(totalDigitales);
				//atencion
				detalleLima.setNumeroAtenciones((int)formulario.getNroAtencionesL());
				detalleLima.setCostoEstandarUnitAtencion(formulario.getCostoUnitAtencionesL());
				BigDecimal totalAtenciones = detalleLima.getCostoEstandarUnitAtencion().multiply(new BigDecimal(detalleLima.getNumeroAtenciones()));
				detalleLima.setCostoTotalAtencionConsRecl(totalAtenciones);
				//gestion administrativa
				detalleLima.setTotalGestionAdministrativa(formulario.getGestionAdmL());
				detalleLima.setTotalDesplazamientoPersonal(formulario.getDesplPersonalL());
				detalleLima.setTotalActividadesExtraord(formulario.getActivExtraordL());
				
				BigDecimal totalLima = new BigDecimal(0);
				totalLima = totalLima.add(detalleLima.getCostoTotalImpresionVale())
						.add(detalleLima.getCostoTotalRepartoValesDomi())
						.add(detalleLima.getCostoTotalEntregaValDisEl())
						.add(detalleLima.getCostoTotalCanjeLiqValeFis())
						.add(detalleLima.getCostoTotalCanjeLiqValeDig())
						.add(detalleLima.getCostoTotalAtencionConsRecl())
						.add(detalleLima.getTotalGestionAdministrativa())
						.add(detalleLima.getTotalDesplazamientoPersonal())
						.add(detalleLima.getTotalActividadesExtraord());
				detalleLima.setTotalReconocer(totalLima);
				//
				detalleLima.setFiseFormato12BC(fiseFormato12BC);
				//detalleLima.setFiseZonaBenef(zonaBenef);
				//
				detalleLima.setUsuarioCreacion(formulario.getUsuario());
				detalleLima.setTerminalCreacion(formulario.getTerminal());
				detalleLima.setFechaCreacion(hoy);
				detalleLima.setUsuarioActualizacion(formulario.getUsuario());
				detalleLima.setTerminalActualizacion(formulario.getTerminal());
				detalleLima.setFechaActualizacion(hoy);
				lista.add(detalleLima);
			}
			
			fiseFormato12BC.setUsuarioActualizacion(formulario.getUsuario());
			fiseFormato12BC.setTerminalActualizacion(formulario.getTerminal());
			fiseFormato12BC.setFechaActualizacion(hoy);
			fiseFormato12BC.setUsuarioCreacion(formulario.getUsuario());
			fiseFormato12BC.setTerminalCreacion(formulario.getTerminal());
			fiseFormato12BC.setFechaCreacion(hoy);
			
			logger.info("aca se va  a guardar"+fiseFormato12BC);
			boolean existe = false;
			existe = formato12BCDao.existeFormato12BC(fiseFormato12BC);
			if(existe){
				throw new Exception("El Formato ya existe para la Distribuidora Eléctrica y Periodo a Declarar seleccionado");
			}else{
				try{
					formato12BCDao.saveFormatoCabecera(fiseFormato12BC);
				}catch(Exception e){
					throw new Exception("Se produjo un error al guardar los datos del Formato 12B");
				}
				
			}
			//add
			for (FiseFormato12BD detalle : lista) {
				try{
					formato12BDDao.saveFormatoDetalle(detalle);
				}catch(Exception e){
					throw new Exception("Se produjo un error al guardar los datos del Formato 12B");
				}
				
			}
			if( lista != null && lista.size()>0 ){
				fiseFormato12BC.setFiseFormato12BDs(lista);
			}
			dto = fiseFormato12BC;
			
		} 	catch (Exception e) {
			logger.error("--error"+e.getMessage());
			e.printStackTrace();
			throw new Exception(e.getMessage());
		}
		return dto;
	}
	
	@Override
	@Transactional(propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
	public FiseFormato12BC modificarFormato12BC(Formato12BCBean formulario, FiseFormato12BC fiseFormato12BC) throws Exception {
		
		FiseFormato12BC dto = null;
		
		try {
			
			Date hoy = FechaUtil.obtenerFechaActual();
	
			List<FiseFormato12BD> lista = new ArrayList<FiseFormato12BD>();
			
			FiseFormato12BD detalleRural = new FiseFormato12BD();
			FiseFormato12BD detalleProvincia = new FiseFormato12BD();
			FiseFormato12BD detalleLima = new FiseFormato12BD();
			if( fiseFormato12BC.getFiseFormato12BDs()!=null ){
				for (FiseFormato12BD detalle : fiseFormato12BC.getFiseFormato12BDs()) {
					if( detalle.getId().getIdZonaBenef()==FiseConstants.ZONABENEF_RURAL_COD ){
						detalleRural = detalle;
					}else if( detalle.getId().getIdZonaBenef()==FiseConstants.ZONABENEF_PROVINCIA_COD ){
						detalleProvincia = detalle;
					} else if( detalle.getId().getIdZonaBenef()==FiseConstants.ZONABENEF_LIMA_COD ){
						detalleLima = detalle;
					}
				}
			}
			
			//RURAL
			if( detalleRural != null ){
			/*if( formulario.getNroValeImpR() != 0 ||
					formulario.getNroValReparDomicR() != 0 ||
					formulario.getNroValEntDisElR() != 0 ||
					formulario.getNroValFisiCanjR() != 0 ||
					formulario.getNroValDigitCanjR() != 0 ||
					formulario.getNroAtencionesR() != 0
					){*/
				logger.info("entro a RURAL");
				//
				
				//impresion vales
				detalleRural.setNumeroValesImpreso((int)formulario.getNroValeImpR());
				detalleRural.setCostoEstandarUnitValeImpre(formulario.getCostoUnitValImpR());
				BigDecimal totalImpresion = detalleRural.getCostoEstandarUnitValeImpre().multiply(new BigDecimal(detalleRural.getNumeroValesImpreso()));
				detalleRural.setCostoTotalImpresionVale(totalImpresion);
				//reparto vales
				detalleRural.setNumeroValesRepartidosDomi((int)formulario.getNroValReparDomicR());
				detalleRural.setCostoEstandarUnitValeRepar(formulario.getCostoUnitValReparDomicR());
				BigDecimal totalReparto = detalleRural.getCostoEstandarUnitValeRepar().multiply(new BigDecimal(detalleRural.getNumeroValesRepartidosDomi()));
				detalleRural.setCostoTotalRepartoValesDomi(totalReparto);
				//entrega vales
				detalleRural.setNumeroValesEntregadoDisEl((int)formulario.getNroValEntDisElR());
				detalleRural.setCostoEstandarUnitValDisEl(formulario.getCostoUnitValEntDisElR());
				BigDecimal totalEntregados = detalleRural.getCostoEstandarUnitValDisEl().multiply(new BigDecimal(detalleRural.getNumeroValesEntregadoDisEl()));
				detalleRural.setCostoTotalEntregaValDisEl(totalEntregados);
				//vales fisicos
				detalleRural.setNumeroValesFisicosCanjeados((int)formulario.getNroValFisiCanjR());
				detalleRural.setCostoEstandarUnitValFiCan(formulario.getCostoUnitValFisiCanjR());
				BigDecimal totalFisicos = detalleRural.getCostoEstandarUnitValFiCan().multiply(new BigDecimal(detalleRural.getNumeroValesFisicosCanjeados()));
				detalleRural.setCostoTotalCanjeLiqValeFis(totalFisicos);
				//vales digitales
				detalleRural.setNumeroValesDigitalCanjeados((int)formulario.getNroValDigitCanjR());
				detalleRural.setCostoEstandarUnitValDgCan(formulario.getCostoUnitValDigitCanjR());
				BigDecimal totalDigitales = detalleRural.getCostoEstandarUnitValDgCan().multiply(new BigDecimal(detalleRural.getNumeroValesDigitalCanjeados()));
				detalleRural.setCostoTotalCanjeLiqValeDig(totalDigitales);
				//atencion
				detalleRural.setNumeroAtenciones((int)formulario.getNroAtencionesR());
				detalleRural.setCostoEstandarUnitAtencion(formulario.getCostoUnitAtencionesR());
				BigDecimal totalAtenciones = detalleRural.getCostoEstandarUnitAtencion().multiply(new BigDecimal(detalleRural.getNumeroAtenciones()));
				detalleRural.setCostoTotalAtencionConsRecl(totalAtenciones);
				//gestion administrativa
				detalleRural.setTotalGestionAdministrativa(formulario.getGestionAdmR());
				detalleRural.setTotalDesplazamientoPersonal(formulario.getDesplPersonalR());
				detalleRural.setTotalActividadesExtraord(formulario.getActivExtraordR());
				
				BigDecimal totalRural = new BigDecimal(0);
				totalRural = totalRural.add(detalleRural.getCostoTotalImpresionVale())
						.add(detalleRural.getCostoTotalRepartoValesDomi())
						.add(detalleRural.getCostoTotalEntregaValDisEl())
						.add(detalleRural.getCostoTotalCanjeLiqValeFis())
						.add(detalleRural.getCostoTotalCanjeLiqValeDig())
						.add(detalleRural.getCostoTotalAtencionConsRecl())
						.add(detalleRural.getTotalGestionAdministrativa())
						.add(detalleRural.getTotalDesplazamientoPersonal())
						.add(detalleRural.getTotalActividadesExtraord());
				detalleRural.setTotalReconocer(totalRural);
				//
				detalleRural.setFiseFormato12BC(fiseFormato12BC);
				//detalleRural.setFiseZonaBenef(zonaBenef);
				//
				detalleRural.setUsuarioActualizacion(formulario.getUsuario());
				detalleRural.setTerminalActualizacion(formulario.getTerminal());
				detalleRural.setFechaActualizacion(hoy);
				lista.add(detalleRural);
			}
			//PROVINCIA
			if( detalleProvincia != null ){
			/*if( formulario.getNroValeImpP() != 0 ||
					formulario.getNroValReparDomicP() != 0 ||
					formulario.getNroValEntDisElP() != 0 ||
					formulario.getNroValFisiCanjP() != 0 ||
					formulario.getNroValDigitCanjP() != 0 ||
					formulario.getNroAtencionesP() != 0
					){*/
				logger.info("entro a PROVINCIA");
				//
				
				//impresion vales
				detalleProvincia.setNumeroValesImpreso((int)formulario.getNroValeImpP());
				detalleProvincia.setCostoEstandarUnitValeImpre(formulario.getCostoUnitValImpP());
				BigDecimal totalImpresion = detalleProvincia.getCostoEstandarUnitValeImpre().multiply(new BigDecimal(detalleProvincia.getNumeroValesImpreso()));
				detalleProvincia.setCostoTotalImpresionVale(totalImpresion);
				//reparto vales
				detalleProvincia.setNumeroValesRepartidosDomi((int)formulario.getNroValReparDomicP());
				detalleProvincia.setCostoEstandarUnitValeRepar(formulario.getCostoUnitValReparDomicP());
				BigDecimal totalReparto = detalleProvincia.getCostoEstandarUnitValeRepar().multiply(new BigDecimal(detalleProvincia.getNumeroValesRepartidosDomi()));
				detalleProvincia.setCostoTotalRepartoValesDomi(totalReparto);
				//entrega vales
				detalleProvincia.setNumeroValesEntregadoDisEl((int)formulario.getNroValEntDisElP());
				detalleProvincia.setCostoEstandarUnitValDisEl(formulario.getCostoUnitValEntDisElP());
				BigDecimal totalEntregados = detalleProvincia.getCostoEstandarUnitValDisEl().multiply(new BigDecimal(detalleProvincia.getNumeroValesEntregadoDisEl()));
				detalleProvincia.setCostoTotalEntregaValDisEl(totalEntregados);
				//vales fisicos
				detalleProvincia.setNumeroValesFisicosCanjeados((int)formulario.getNroValFisiCanjP());
				detalleProvincia.setCostoEstandarUnitValFiCan(formulario.getCostoUnitValFisiCanjP());
				BigDecimal totalFisicos = detalleProvincia.getCostoEstandarUnitValFiCan().multiply(new BigDecimal(detalleProvincia.getNumeroValesFisicosCanjeados()));
				detalleProvincia.setCostoTotalCanjeLiqValeFis(totalFisicos);
				//vales digitales
				detalleProvincia.setNumeroValesDigitalCanjeados((int)formulario.getNroValDigitCanjP());
				detalleProvincia.setCostoEstandarUnitValDgCan(formulario.getCostoUnitValDigitCanjP());
				BigDecimal totalDigitales = detalleProvincia.getCostoEstandarUnitValDgCan().multiply(new BigDecimal(detalleProvincia.getNumeroValesDigitalCanjeados()));
				detalleProvincia.setCostoTotalCanjeLiqValeDig(totalDigitales);
				//atencion
				detalleProvincia.setNumeroAtenciones((int)formulario.getNroAtencionesP());
				detalleProvincia.setCostoEstandarUnitAtencion(formulario.getCostoUnitAtencionesP());
				BigDecimal totalAtenciones = detalleProvincia.getCostoEstandarUnitAtencion().multiply(new BigDecimal(detalleProvincia.getNumeroAtenciones()));
				detalleProvincia.setCostoTotalAtencionConsRecl(totalAtenciones);
				//gestion administrativa
				detalleProvincia.setTotalGestionAdministrativa(formulario.getGestionAdmP());
				detalleProvincia.setTotalDesplazamientoPersonal(formulario.getDesplPersonalP());
				detalleProvincia.setTotalActividadesExtraord(formulario.getActivExtraordP());
				
				BigDecimal totalProvincia = new BigDecimal(0);
				totalProvincia = totalProvincia.add(detalleProvincia.getCostoTotalImpresionVale())
						.add(detalleProvincia.getCostoTotalRepartoValesDomi())
						.add(detalleProvincia.getCostoTotalEntregaValDisEl())
						.add(detalleProvincia.getCostoTotalCanjeLiqValeFis())
						.add(detalleProvincia.getCostoTotalCanjeLiqValeDig())
						.add(detalleProvincia.getCostoTotalAtencionConsRecl())
						.add(detalleProvincia.getTotalGestionAdministrativa())
						.add(detalleProvincia.getTotalDesplazamientoPersonal())
						.add(detalleProvincia.getTotalActividadesExtraord());
				detalleProvincia.setTotalReconocer(totalProvincia);
				//
				detalleProvincia.setFiseFormato12BC(fiseFormato12BC);
				//detalleProvincia.setFiseZonaBenef(zonaBenef);
				//
				detalleProvincia.setUsuarioActualizacion(formulario.getUsuario());
				detalleProvincia.setTerminalActualizacion(formulario.getTerminal());
				detalleProvincia.setFechaActualizacion(hoy);
				lista.add(detalleProvincia);
			}
			//LIMA
			if( detalleLima != null ){
			/*if( formulario.getNroValeImpL() != 0 ||
					formulario.getNroValReparDomicL() != 0 ||
					formulario.getNroValEntDisElL() != 0 ||
					formulario.getNroValFisiCanjL() != 0 ||
					formulario.getNroValDigitCanjL() != 0 ||
					formulario.getNroAtencionesL() != 0
					){*/
				logger.info("entro a LIMA");
				//
				
				//impresion vales
				detalleLima.setNumeroValesImpreso((int)formulario.getNroValeImpL());
				detalleLima.setCostoEstandarUnitValeImpre(formulario.getCostoUnitValImpL());
				BigDecimal totalImpresion = detalleLima.getCostoEstandarUnitValeImpre().multiply(new BigDecimal(detalleLima.getNumeroValesImpreso()));
				detalleLima.setCostoTotalImpresionVale(totalImpresion);
				//reparto vales
				detalleLima.setNumeroValesRepartidosDomi((int)formulario.getNroValReparDomicL());
				detalleLima.setCostoEstandarUnitValeRepar(formulario.getCostoUnitValReparDomicL());
				BigDecimal totalReparto = detalleLima.getCostoEstandarUnitValeRepar().multiply(new BigDecimal(detalleLima.getNumeroValesRepartidosDomi()));
				detalleLima.setCostoTotalRepartoValesDomi(totalReparto);
				//entrega vales
				detalleLima.setNumeroValesEntregadoDisEl((int)formulario.getNroValEntDisElL());
				detalleLima.setCostoEstandarUnitValDisEl(formulario.getCostoUnitValEntDisElL());
				BigDecimal totalEntregados = detalleLima.getCostoEstandarUnitValDisEl().multiply(new BigDecimal(detalleLima.getNumeroValesEntregadoDisEl()));
				detalleLima.setCostoTotalEntregaValDisEl(totalEntregados);
				//vales fisicos
				detalleLima.setNumeroValesFisicosCanjeados((int)formulario.getNroValFisiCanjL());
				detalleLima.setCostoEstandarUnitValFiCan(formulario.getCostoUnitValFisiCanjL());
				BigDecimal totalFisicos = detalleLima.getCostoEstandarUnitValFiCan().multiply(new BigDecimal(detalleLima.getNumeroValesFisicosCanjeados()));
				detalleLima.setCostoTotalCanjeLiqValeFis(totalFisicos);
				//vales digitales
				detalleLima.setNumeroValesDigitalCanjeados((int)formulario.getNroValDigitCanjL());
				detalleLima.setCostoEstandarUnitValDgCan(formulario.getCostoUnitValDigitCanjL());
				BigDecimal totalDigitales = detalleLima.getCostoEstandarUnitValDgCan().multiply(new BigDecimal(detalleLima.getNumeroValesDigitalCanjeados()));
				detalleLima.setCostoTotalCanjeLiqValeDig(totalDigitales);
				//atencion
				detalleLima.setNumeroAtenciones((int)formulario.getNroAtencionesL());
				detalleLima.setCostoEstandarUnitAtencion(formulario.getCostoUnitAtencionesL());
				BigDecimal totalAtenciones = detalleLima.getCostoEstandarUnitAtencion().multiply(new BigDecimal(detalleLima.getNumeroAtenciones()));
				detalleLima.setCostoTotalAtencionConsRecl(totalAtenciones);
				//gestion administrativa
				detalleLima.setTotalGestionAdministrativa(formulario.getGestionAdmL());
				detalleLima.setTotalDesplazamientoPersonal(formulario.getDesplPersonalL());
				detalleLima.setTotalActividadesExtraord(formulario.getActivExtraordL());
				
				BigDecimal totalLima = new BigDecimal(0);
				totalLima = totalLima.add(detalleLima.getCostoTotalImpresionVale())
						.add(detalleLima.getCostoTotalRepartoValesDomi())
						.add(detalleLima.getCostoTotalEntregaValDisEl())
						.add(detalleLima.getCostoTotalCanjeLiqValeFis())
						.add(detalleLima.getCostoTotalCanjeLiqValeDig())
						.add(detalleLima.getCostoTotalAtencionConsRecl())
						.add(detalleLima.getTotalGestionAdministrativa())
						.add(detalleLima.getTotalDesplazamientoPersonal())
						.add(detalleLima.getTotalActividadesExtraord());
				detalleLima.setTotalReconocer(totalLima);
				//
				detalleLima.setFiseFormato12BC(fiseFormato12BC);
				//detalleLima.setFiseZonaBenef(zonaBenef);
				//
				detalleLima.setUsuarioActualizacion(formulario.getUsuario());
				detalleLima.setTerminalActualizacion(formulario.getTerminal());
				detalleLima.setFechaActualizacion(hoy);
				lista.add(detalleLima);
			}
		
			fiseFormato12BC.setUsuarioActualizacion(formulario.getUsuario());
			fiseFormato12BC.setTerminalActualizacion(formulario.getTerminal());
			fiseFormato12BC.setFechaActualizacion(hoy);
		
			if( FiseConstants.TIPOARCHIVO_XLS.equals(formulario.getTipoArchivo()) ){
				fiseFormato12BC.setNombreArchivoExcel(formulario.getNombreArchivo());
			}else if( FiseConstants.TIPOARCHIVO_TXT.equals(formulario.getTipoArchivo()) ){
				fiseFormato12BC.setNombreArchivoTexto(formulario.getNombreArchivo());
			}
			
			formato12BCDao.updateFormatoCabecera(fiseFormato12BC);
			//add
			for (FiseFormato12BD detalle : lista) {
				formato12BDDao.updateFormatoDetalle(detalle);
			}
			dto= fiseFormato12BC;
			
		}	catch (Exception e) {
			logger.error("--error"+e.getMessage());
			e.printStackTrace();
			throw new Exception("Se produjo un error al actualizar los datos del Formato 12B");
		}
		//
		return dto;

	}

}
