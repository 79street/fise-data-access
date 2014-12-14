package gob.osinergmin.fise.gart.service.impl;

import gob.osinergmin.fise.bean.Formato12BCBean;
import gob.osinergmin.fise.constant.FiseConstants;
import gob.osinergmin.fise.dao.Formato12BCDao;
import gob.osinergmin.fise.dao.Formato12BDDao;
import gob.osinergmin.fise.dao.Formato12BDObDao;
import gob.osinergmin.fise.domain.FiseFormato12BC;
import gob.osinergmin.fise.domain.FiseFormato12BCPK;
import gob.osinergmin.fise.domain.FiseFormato12BD;
import gob.osinergmin.fise.domain.FiseFormato12BDOb;
import gob.osinergmin.fise.gart.service.Formato12BGartService;
import gob.osinergmin.fise.util.FechaUtil;

import java.math.BigDecimal;
import java.util.Date;
import java.util.HashMap;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;


@Service(value="formato12BGartServiceImpl")
public class Formato12BGartServiceImpl implements Formato12BGartService {

	@Autowired
	@Qualifier("formato12BCDaoImpl")
	private Formato12BCDao formato12BCDao;
	
	@Autowired
	@Qualifier("formato12BDDaoImpl")
	private Formato12BDDao formato12BDDao;
	
	@Autowired
	@Qualifier("formato12BDObDaoImpl")
	private Formato12BDObDao formato12BDObDao;
	
	@Override
	public List<FiseFormato12BC> getLstFormatoCabecera(String codemp, Integer anioDesde, Integer mesDesde, Integer anioHasta, Integer mesHasta, String etapa) {
		return formato12BCDao.getLstFormatoCabecera(codemp, anioDesde, mesDesde, anioHasta, mesHasta, etapa);
	}

	@Override
	public FiseFormato12BC getFormatoCabeceraById(FiseFormato12BCPK id) {
		FiseFormato12BC formato = formato12BCDao.getFormatoCabeceraById(id);		
	    formato.setListaDetalle12BDs(formato.getFiseFormato12BDs()); 	   
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
	System.out.println("des mes ejecucion::"+formato12BBean.getDescMesEjecucion());
	System.out.println("des mes presentacion::"+formato12BBean.getDescMesPresentacion());
	
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
public FiseFormato12BC modificarEnvioDefinitivoFormato12BC(Formato12BCBean formulario, FiseFormato12BC fiseFormato12BC) throws Exception {
	
	FiseFormato12BC dto = null;
	Date hoy = FechaUtil.obtenerFechaActual();
	try{
		fiseFormato12BC.setFechaEnvioDefinitivo(hoy);
		fiseFormato12BC.setUsuarioActualizacion(formulario.getUsuario());
		fiseFormato12BC.setTerminalActualizacion(formulario.getTerminal());
		fiseFormato12BC.setFechaActualizacion(hoy);
		formato12BCDao.updateFormatoCabecera(fiseFormato12BC);
		dto= fiseFormato12BC;
	} catch (Exception e) {
		
		e.printStackTrace();
		throw e;
	}
	return dto;
}

	@Override
	@Transactional
	public Integer deleteFormatoObs(String emp, Integer anio, Integer mes, String etapa, Integer anioEjec, Integer mesEjec, Integer idzona, Integer item) throws DataIntegrityViolationException, Exception {
		// TODO Auto-generated method stub
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

}
