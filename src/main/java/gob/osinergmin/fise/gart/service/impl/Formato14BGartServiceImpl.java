package gob.osinergmin.fise.gart.service.impl;

import gob.osinergmin.fise.bean.Formato14BCBean;
import gob.osinergmin.fise.constant.FiseConstants;
import gob.osinergmin.fise.dao.CommonDao;
import gob.osinergmin.fise.dao.FiseGrupoInformacionDao;
import gob.osinergmin.fise.dao.FiseZonaBenefDao;
import gob.osinergmin.fise.dao.Formato14BCDao;
import gob.osinergmin.fise.dao.Formato14BDDao;
import gob.osinergmin.fise.dao.Formato14BDObDao;
import gob.osinergmin.fise.domain.FiseFormato14BC;
import gob.osinergmin.fise.domain.FiseFormato14BCPK;
import gob.osinergmin.fise.domain.FiseFormato14BD;
import gob.osinergmin.fise.domain.FiseFormato14BDOb;
import gob.osinergmin.fise.domain.FiseFormato14BDPK;
import gob.osinergmin.fise.domain.FiseGrupoInformacion;
import gob.osinergmin.fise.domain.FiseZonaBenef;
import gob.osinergmin.fise.gart.service.Formato14BGartService;
import gob.osinergmin.fise.util.FechaUtil;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

@Service(value="formato14BGartServiceImpl")
public class Formato14BGartServiceImpl implements Formato14BGartService {
	
	Logger logger=Logger.getLogger(Formato14BGartServiceImpl.class);
	
	@Autowired
	@Qualifier("formato14BCDaoImpl")
	private Formato14BCDao formato14BCDao;
	
	@Autowired
	@Qualifier("formato14BDDaoImpl")
	private Formato14BDDao formato14BDDao;
	
	@Autowired
	@Qualifier("fiseZonaBenefDaoImpl")
	private FiseZonaBenefDao zonaBenefDao;
	
	@Autowired
	@Qualifier("formato14BDObDaoImpl")
	private Formato14BDObDao formato14BObsDao;
	
	@Autowired
	@Qualifier("commonDaoImpl")
	private CommonDao commonDao;
	
	@Autowired
	@Qualifier("fiseGrupoInformacionDaoImpl")
	private FiseGrupoInformacionDao fiseGrupoInformacionDao;
	
	@Override
	@Transactional
	public FiseFormato14BD obtenerFormato14BDVigente(String codEmpresa, long anioVigencia, long idZonaBenef){
		return formato14BDDao.obtenerFormato14BDVigente(codEmpresa, anioVigencia, idZonaBenef);
	}

	@Override
	@Transactional
	public FiseFormato14BC obtenerFormato14BCByPK(FiseFormato14BCPK fiseFormato14BCPK) {
		FiseFormato14BC formato = null;
		formato = formato14BCDao.obtenerFormato14BCByPK(fiseFormato14BCPK);
		if(formato != null){
			formato.setFiseFormato14BDs(formato14BDDao.listarFormato14BDByFormato14BC(formato));
		}
		return formato;
	}
	
	@Override
	@Transactional
	public List<FiseFormato14BC> buscarFormato14BC(String codEmpresa, long anioDesde, long mesDesde, long anioHasta, long mesHasta, String etapa){
		
		List<FiseFormato14BC> lista = null;
		lista = formato14BCDao.buscarFormato14BC(codEmpresa, anioDesde, mesDesde, anioHasta, mesHasta, etapa);
		for (FiseFormato14BC formato : lista) {
			formato.setFiseFormato14BDs(formato14BDDao.listarFormato14BDByFormato14BC(formato));
		}
		return lista;
	}
	
	@Override
	@Transactional(propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
	public FiseFormato14BC registrarFormato14BC(Formato14BCBean formulario) throws Exception {
		
		FiseFormato14BC dto = null;
		
		try {
			Date hoy = FechaUtil.obtenerFechaActual();
			FiseFormato14BC fiseFormato14BC = new FiseFormato14BC();
			//guardar el pk
			FiseFormato14BCPK id = new FiseFormato14BCPK();
			id.setCodEmpresa(formulario.getCodigoEmpresa());
			id.setAnoPresentacion(formulario.getAnioPresent());
			id.setMesPresentacion(formulario.getMesPresent());
			id.setAnoInicioVigencia(formulario.getAnioInicioVigencia());
			id.setAnoFinVigencia(formulario.getAnioFinVigencia());
			//id.setEtapa(FiseConstants.ETAPA_SOLICITUD);
			id.setEtapa(formulario.getEtapa());
			if( FiseConstants.TIPOARCHIVO_XLS.equals(formulario.getTipoArchivo()) ){
				fiseFormato14BC.setNombreArchivoExcel(formulario.getNombreArchivo());
			}else if( FiseConstants.TIPOARCHIVO_TXT.equals(formulario.getTipoArchivo()) ){
				fiseFormato14BC.setNombreArchivoTexto(formulario.getNombreArchivo());
			}/*else{
				id.setEtapa(formulario.getEtapa());
			}*/
			
			fiseFormato14BC.setId(id);
			
			FiseGrupoInformacion grupoInfo = null;
			long idGrupoInf = commonDao.obtenerIdGrupoInformacion(formulario.getAnioPresent(), formulario.getMesPresent(), FiseConstants.FRECUENCIA_BIENAL_DESCRIPCION); 
			if(idGrupoInf!=0){
				grupoInfo = fiseGrupoInformacionDao.obtenerFiseGrupoInformacionByPK(idGrupoInf);	
			}	
			fiseFormato14BC.setFiseGrupoInformacion(grupoInfo);  
			
			
			List<FiseFormato14BD> lista = new ArrayList<FiseFormato14BD>();
			
			//RURAL
			if( formulario.getNroValesImpR() != 0 ||
					formulario.getNroValesReptR() != 0 ||
					formulario.getNroValesEntrR() != 0 ||
					formulario.getNroValesFisR() != 0 ||
					formulario.getNroTotalAtenR() != 0
					){
				logger.info("entro a RURAL");
				FiseZonaBenef zonaBenef = new FiseZonaBenef();
				zonaBenef = zonaBenefDao.obtenerFiseZonaBenefByPK(FiseConstants.ZONABENEF_RURAL_COD);
				//
				FiseFormato14BD detalleRural = new FiseFormato14BD();
				//pk
				FiseFormato14BDPK pkDetalle = new FiseFormato14BDPK();
				pkDetalle.setCodEmpresa(fiseFormato14BC.getId().getCodEmpresa());
				pkDetalle.setAnoPresentacion(fiseFormato14BC.getId().getAnoPresentacion());
				pkDetalle.setMesPresentacion(fiseFormato14BC.getId().getMesPresentacion());
				pkDetalle.setAnoInicioVigencia(fiseFormato14BC.getId().getAnoInicioVigencia());
				pkDetalle.setAnoFinVigencia(fiseFormato14BC.getId().getAnoFinVigencia());
				pkDetalle.setIdZonaBenef(zonaBenef.getIdZonaBenef());
				pkDetalle.setEtapa(fiseFormato14BC.getId().getEtapa());
				detalleRural.setId(pkDetalle);
				
				//impresion vales
				detalleRural.setImpresionValDsctoCliDisEl(formulario.getImpValDesctoEdeR());
				detalleRural.setImpreValDsctoCliNoDisEl(formulario.getImpValDesctoNoEdeR());
				
				BigDecimal totalImpresion = new BigDecimal(0);
				totalImpresion = totalImpresion.add(detalleRural.getImpresionValDsctoCliDisEl())
						.add(detalleRural.getImpreValDsctoCliNoDisEl());
				detalleRural.setCostoTotalImpresion(totalImpresion);
				detalleRural.setNumeroValesImpreso(formulario.getNroValesImpR());
				if( formulario.getNroValesImpR() !=0 ){
					detalleRural.setCostoUnitarioImpresionVales(detalleRural.getCostoTotalImpresion().divide(new BigDecimal(detalleRural.getNumeroValesImpreso()),2,RoundingMode.HALF_UP));
				}else{
					detalleRural.setCostoUnitarioImpresionVales(BigDecimal.ZERO);
				}
				//reparto vales
				detalleRural.setCostoRepartoValesDescuento(formulario.getCostoTotalValDesctoR());
				detalleRural.setNumeroValesRepartidos(formulario.getNroValesReptR());
				if( formulario.getNroValesReptR() !=0 ){
					detalleRural.setCostoUnitReprtoValeDomici(detalleRural.getCostoRepartoValesDescuento().divide(new BigDecimal(detalleRural.getNumeroValesRepartidos()),2,RoundingMode.HALF_UP));
				}else{
					detalleRural.setCostoUnitReprtoValeDomici(BigDecimal.ZERO);
				}
				//entrega vales
				detalleRural.setCostoTotReprtoValDisEl(formulario.getCostoTotalValOficR());
				detalleRural.setNumeroValesEntregados(formulario.getNroValesEntrR());
				if( formulario.getNroValesEntrR() !=0 ){
					detalleRural.setCostoUnitEntregaValDisEl(detalleRural.getCostoTotReprtoValDisEl().divide(new BigDecimal(detalleRural.getNumeroValesEntregados()),2,RoundingMode.HALF_UP));
				}else{
					detalleRural.setCostoUnitEntregaValDisEl(BigDecimal.ZERO);
				}
				//canje liquidacion vales fisicos
				detalleRural.setCostoEnviarPadronValCanje(formulario.getCostoEnvPadronR());
				detalleRural.setNumeroValesFisicosEmitidos(formulario.getNroValesFisR());
				if( formulario.getNroValesFisR() !=0 ){
					detalleRural.setCostoUnitCanjeLiqValFisi(detalleRural.getCostoEnviarPadronValCanje().divide(new BigDecimal(detalleRural.getNumeroValesFisicosEmitidos()),2,RoundingMode.HALF_UP));
				}else{
					detalleRural.setCostoUnitCanjeLiqValFisi(BigDecimal.ZERO);
				}
				//canje liquidacion vales digitales
				detalleRural.setCostoUnitCanjeValDigital(formulario.getCostoUnitValesDigitR());
				//atencion
				detalleRural.setCostoAtencionSolicitudes(formulario.getCostoAtenSolicR());
				detalleRural.setCostoAtencionConsultaRecla(formulario.getCostoAtenConsR());
				
				BigDecimal totalAtencion = new BigDecimal(0);
				totalAtencion = totalAtencion.add(detalleRural.getCostoAtencionSolicitudes())
						.add(detalleRural.getCostoAtencionConsultaRecla());
				detalleRural.setCostoTotalAtencion(totalAtencion);
				detalleRural.setNumeroTotalAtencion(formulario.getNroTotalAtenR());
				if( formulario.getNroTotalAtenR() !=0 ){
					detalleRural.setCostoUnitarioPorAtencion(detalleRural.getCostoTotalAtencion().divide(new BigDecimal(detalleRural.getNumeroTotalAtencion()),2,RoundingMode.HALF_UP));
				}else{
					detalleRural.setCostoUnitarioPorAtencion(BigDecimal.ZERO);
				}
				//gestion administrativa
				detalleRural.setCostoPersonal(formulario.getCostoPersonalR());
				detalleRural.setCapacitacionAgentesAutGlp(formulario.getCapacAgentR());
				detalleRural.setUtilesMaterialesOficina(formulario.getUtilMatOficR());
				
				BigDecimal totalGestion = new BigDecimal(0);
				totalGestion = totalGestion.add(detalleRural.getCostoPersonal())
						.add(detalleRural.getCapacitacionAgentesAutGlp())
						.add(detalleRural.getUtilesMaterialesOficina());
				detalleRural.setCostoTotalGestionAdministra(totalGestion);
				//
				detalleRural.setFiseFormato14BC(fiseFormato14BC);
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
			if( formulario.getNroValesImpP() != 0 ||
					formulario.getNroValesReptP() != 0 ||
					formulario.getNroValesEntrP() != 0 ||
					formulario.getNroValesFisP() != 0 ||
					formulario.getNroTotalAtenP() != 0
					){
				logger.info("entro a PROVINCIA");
				FiseZonaBenef zonaBenef = new FiseZonaBenef();
				zonaBenef = zonaBenefDao.obtenerFiseZonaBenefByPK(FiseConstants.ZONABENEF_PROVINCIA_COD);
				//
				FiseFormato14BD detalleProvincia = new FiseFormato14BD();
				//pk
				FiseFormato14BDPK pkDetalle = new FiseFormato14BDPK();
				pkDetalle.setCodEmpresa(fiseFormato14BC.getId().getCodEmpresa());
				pkDetalle.setAnoPresentacion(fiseFormato14BC.getId().getAnoPresentacion());
				pkDetalle.setMesPresentacion(fiseFormato14BC.getId().getMesPresentacion());
				pkDetalle.setAnoInicioVigencia(fiseFormato14BC.getId().getAnoInicioVigencia());
				pkDetalle.setAnoFinVigencia(fiseFormato14BC.getId().getAnoFinVigencia());
				pkDetalle.setIdZonaBenef(zonaBenef.getIdZonaBenef());
				pkDetalle.setEtapa(fiseFormato14BC.getId().getEtapa());
				detalleProvincia.setId(pkDetalle);
				
				//impresion vales
				detalleProvincia.setImpresionValDsctoCliDisEl(formulario.getImpValDesctoEdeP());
				detalleProvincia.setImpreValDsctoCliNoDisEl(formulario.getImpValDesctoNoEdeP());
				
				BigDecimal totalImpresion = new BigDecimal(0);
				totalImpresion = totalImpresion.add(detalleProvincia.getImpresionValDsctoCliDisEl())
						.add(detalleProvincia.getImpreValDsctoCliNoDisEl());
				detalleProvincia.setCostoTotalImpresion(totalImpresion);
				detalleProvincia.setNumeroValesImpreso(formulario.getNroValesImpP());
				if( formulario.getNroValesImpP() !=0 ){
					detalleProvincia.setCostoUnitarioImpresionVales(detalleProvincia.getCostoTotalImpresion().divide(new BigDecimal(detalleProvincia.getNumeroValesImpreso()),2,RoundingMode.HALF_UP));
				}else{
					detalleProvincia.setCostoUnitarioImpresionVales(BigDecimal.ZERO);
				}
				//reparto vales
				detalleProvincia.setCostoRepartoValesDescuento(formulario.getCostoTotalValDesctoP());
				detalleProvincia.setNumeroValesRepartidos(formulario.getNroValesReptP());
				if( formulario.getNroValesReptP() !=0 ){
					detalleProvincia.setCostoUnitReprtoValeDomici(detalleProvincia.getCostoRepartoValesDescuento().divide(new BigDecimal(detalleProvincia.getNumeroValesRepartidos()),2,RoundingMode.HALF_UP));
				}else{
					detalleProvincia.setCostoUnitReprtoValeDomici(BigDecimal.ZERO);
				}
				//entrega vales
				detalleProvincia.setCostoTotReprtoValDisEl(formulario.getCostoTotalValOficP());
				detalleProvincia.setNumeroValesEntregados(formulario.getNroValesEntrP());
				if( formulario.getNroValesEntrP() !=0 ){
					detalleProvincia.setCostoUnitEntregaValDisEl(detalleProvincia.getCostoTotReprtoValDisEl().divide(new BigDecimal(detalleProvincia.getNumeroValesEntregados()),2,RoundingMode.HALF_UP));
				}else{
					detalleProvincia.setCostoUnitEntregaValDisEl(BigDecimal.ZERO);
				}
				//canje liquidacion vales fisicos
				detalleProvincia.setCostoEnviarPadronValCanje(formulario.getCostoEnvPadronP());
				detalleProvincia.setNumeroValesFisicosEmitidos(formulario.getNroValesFisP());
				if( formulario.getNroValesFisP() !=0 ){
					detalleProvincia.setCostoUnitCanjeLiqValFisi(detalleProvincia.getCostoEnviarPadronValCanje().divide(new BigDecimal(detalleProvincia.getNumeroValesFisicosEmitidos()),2,RoundingMode.HALF_UP));
				}else{
					detalleProvincia.setCostoUnitCanjeLiqValFisi(BigDecimal.ZERO);
				}
				//canje liquidacion vales digitales
				detalleProvincia.setCostoUnitCanjeValDigital(formulario.getCostoUnitValesDigitP());
				//atencion
				detalleProvincia.setCostoAtencionSolicitudes(formulario.getCostoAtenSolicP());
				detalleProvincia.setCostoAtencionConsultaRecla(formulario.getCostoAtenConsP());
				
				BigDecimal totalAtencion = new BigDecimal(0);
				totalAtencion = totalAtencion.add(detalleProvincia.getCostoAtencionSolicitudes())
						.add(detalleProvincia.getCostoAtencionConsultaRecla());
				detalleProvincia.setCostoTotalAtencion(totalAtencion);
				detalleProvincia.setNumeroTotalAtencion(formulario.getNroTotalAtenP());
				if( formulario.getNroTotalAtenP() !=0 ){
					detalleProvincia.setCostoUnitarioPorAtencion(detalleProvincia.getCostoTotalAtencion().divide(new BigDecimal(detalleProvincia.getNumeroTotalAtencion()),2,RoundingMode.HALF_UP));
				}else{
					detalleProvincia.setCostoUnitarioPorAtencion(BigDecimal.ZERO);
				}
				//gestion administrativa
				detalleProvincia.setCostoPersonal(formulario.getCostoPersonalP());
				detalleProvincia.setCapacitacionAgentesAutGlp(formulario.getCapacAgentP());
				detalleProvincia.setUtilesMaterialesOficina(formulario.getUtilMatOficP());
				
				BigDecimal totalGestion = new BigDecimal(0);
				totalGestion = totalGestion.add(detalleProvincia.getCostoPersonal())
						.add(detalleProvincia.getCapacitacionAgentesAutGlp())
						.add(detalleProvincia.getUtilesMaterialesOficina());
				detalleProvincia.setCostoTotalGestionAdministra(totalGestion);
				//
				detalleProvincia.setFiseFormato14BC(fiseFormato14BC);
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
			if( formulario.getNroValesImpL() != 0 ||
					formulario.getNroValesReptL() != 0 ||
					formulario.getNroValesEntrL() != 0 ||
					formulario.getNroValesFisL() != 0 ||
					formulario.getNroTotalAtenL() != 0
					){
				logger.info("entro a LIMA");
				FiseZonaBenef zonaBenef = new FiseZonaBenef();
				zonaBenef = zonaBenefDao.obtenerFiseZonaBenefByPK(FiseConstants.ZONABENEF_LIMA_COD);
				//
				FiseFormato14BD detalleLima = new FiseFormato14BD();
				//pk
				FiseFormato14BDPK pkDetalle = new FiseFormato14BDPK();
				pkDetalle.setCodEmpresa(fiseFormato14BC.getId().getCodEmpresa());
				pkDetalle.setAnoPresentacion(fiseFormato14BC.getId().getAnoPresentacion());
				pkDetalle.setMesPresentacion(fiseFormato14BC.getId().getMesPresentacion());
				pkDetalle.setAnoInicioVigencia(fiseFormato14BC.getId().getAnoInicioVigencia());
				pkDetalle.setAnoFinVigencia(fiseFormato14BC.getId().getAnoFinVigencia());
				pkDetalle.setIdZonaBenef(zonaBenef.getIdZonaBenef());
				pkDetalle.setEtapa(fiseFormato14BC.getId().getEtapa());
				detalleLima.setId(pkDetalle);
				
				//impresion vales
				detalleLima.setImpresionValDsctoCliDisEl(formulario.getImpValDesctoEdeL());
				detalleLima.setImpreValDsctoCliNoDisEl(formulario.getImpValDesctoNoEdeL());
				
				BigDecimal totalImpresion = new BigDecimal(0);
				totalImpresion = totalImpresion.add(detalleLima.getImpresionValDsctoCliDisEl())
						.add(detalleLima.getImpreValDsctoCliNoDisEl());
				detalleLima.setCostoTotalImpresion(totalImpresion);
				detalleLima.setNumeroValesImpreso(formulario.getNroValesImpL());
				if( formulario.getNroValesImpL() !=0 ){
					detalleLima.setCostoUnitarioImpresionVales(detalleLima.getCostoTotalImpresion().divide(new BigDecimal(detalleLima.getNumeroValesImpreso()),2,RoundingMode.HALF_UP));
				}else{
					detalleLima.setCostoUnitarioImpresionVales(BigDecimal.ZERO);
				}
				//reparto vales
				detalleLima.setCostoRepartoValesDescuento(formulario.getCostoTotalValDesctoL());
				detalleLima.setNumeroValesRepartidos(formulario.getNroValesReptL());
				if( formulario.getNroValesReptL() !=0 ){
					detalleLima.setCostoUnitReprtoValeDomici(detalleLima.getCostoRepartoValesDescuento().divide(new BigDecimal(detalleLima.getNumeroValesRepartidos()),2,RoundingMode.HALF_UP));
				}else{
					detalleLima.setCostoUnitReprtoValeDomici(BigDecimal.ZERO);
				}
				//entrega vales
				detalleLima.setCostoTotReprtoValDisEl(formulario.getCostoTotalValOficL());
				detalleLima.setNumeroValesEntregados(formulario.getNroValesEntrL());
				if( formulario.getNroValesEntrL() !=0 ){
					detalleLima.setCostoUnitEntregaValDisEl(detalleLima.getCostoTotReprtoValDisEl().divide(new BigDecimal(detalleLima.getNumeroValesEntregados()),2,RoundingMode.HALF_UP));
				}else{
					detalleLima.setCostoUnitEntregaValDisEl(BigDecimal.ZERO);
				}
				//canje liquidacion vales fisicos
				detalleLima.setCostoEnviarPadronValCanje(formulario.getCostoEnvPadronL());
				detalleLima.setNumeroValesFisicosEmitidos(formulario.getNroValesFisL());
				if( formulario.getNroValesFisL() !=0 ){
					detalleLima.setCostoUnitCanjeLiqValFisi(detalleLima.getCostoEnviarPadronValCanje().divide(new BigDecimal(detalleLima.getNumeroValesFisicosEmitidos()),2,RoundingMode.HALF_UP));
				}else{
					detalleLima.setCostoUnitCanjeLiqValFisi(BigDecimal.ZERO);
				}
				//canje liquidacion vales digitales
				detalleLima.setCostoUnitCanjeValDigital(formulario.getCostoUnitValesDigitL());
				//atencion
				detalleLima.setCostoAtencionSolicitudes(formulario.getCostoAtenSolicL());
				detalleLima.setCostoAtencionConsultaRecla(formulario.getCostoAtenConsL());
				
				BigDecimal totalAtencion = new BigDecimal(0);
				totalAtencion = totalAtencion.add(detalleLima.getCostoAtencionSolicitudes())
						.add(detalleLima.getCostoAtencionConsultaRecla());
				detalleLima.setCostoTotalAtencion(totalAtencion);
				detalleLima.setNumeroTotalAtencion(formulario.getNroTotalAtenL());
				if( formulario.getNroTotalAtenL() !=0 ){
					detalleLima.setCostoUnitarioPorAtencion(detalleLima.getCostoTotalAtencion().divide(new BigDecimal(detalleLima.getNumeroTotalAtencion()),2,RoundingMode.HALF_UP));
				}else{
					detalleLima.setCostoUnitarioPorAtencion(BigDecimal.ZERO);
				}
				//gestion administrativa
				detalleLima.setCostoPersonal(formulario.getCostoPersonalL());
				detalleLima.setCapacitacionAgentesAutGlp(formulario.getCapacAgentL());
				detalleLima.setUtilesMaterialesOficina(formulario.getUtilMatOficL());
				
				BigDecimal totalGestion = new BigDecimal(0);
				totalGestion = totalGestion.add(detalleLima.getCostoPersonal())
						.add(detalleLima.getCapacitacionAgentesAutGlp())
						.add(detalleLima.getUtilesMaterialesOficina());
				detalleLima.setCostoTotalGestionAdministra(totalGestion);
				//
				detalleLima.setFiseFormato14BC(fiseFormato14BC);
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
			
			fiseFormato14BC.setUsuarioActualizacion(formulario.getUsuario());
			fiseFormato14BC.setTerminalActualizacion(formulario.getTerminal());
			fiseFormato14BC.setFechaActualizacion(hoy);
			fiseFormato14BC.setUsuarioCreacion(formulario.getUsuario());
			fiseFormato14BC.setTerminalCreacion(formulario.getTerminal());
			fiseFormato14BC.setFechaCreacion(hoy);
			
			logger.info("aca se va  a guardar"+fiseFormato14BC);
			boolean existe = false;
			existe = formato14BCDao.existeFormato14BC(fiseFormato14BC);
			if(existe){
				throw new Exception("Ya existe un registro con la misma clave.");
			}else{
				formato14BCDao.registrarFormato14BC(fiseFormato14BC);
			}
			//add
			for (FiseFormato14BD detalle : lista) {
				formato14BDDao.registrarFormato14BD(detalle);
			}
			if( lista != null && lista.size()>0 ){
				fiseFormato14BC.setFiseFormato14BDs(lista);
			}
			dto = fiseFormato14BC;
			
		} 	catch (Exception e) {
			logger.error("--error"+e.getMessage());
			e.printStackTrace();
			throw e;
		}
		return dto;
	}
	
	@Override
	@Transactional(propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
	public FiseFormato14BC modificarFormato14BC(Formato14BCBean formulario, FiseFormato14BC fiseFormato14BC) throws Exception {
		
		FiseFormato14BC dto = null;
		
		try {
			
			Date hoy = FechaUtil.obtenerFechaActual();
	
			List<FiseFormato14BD> lista = new ArrayList<FiseFormato14BD>();
			
			FiseFormato14BD detalleRural = new FiseFormato14BD();
			FiseFormato14BD detalleProvincia = new FiseFormato14BD();
			FiseFormato14BD detalleLima = new FiseFormato14BD();
			if( fiseFormato14BC.getFiseFormato14BDs()!=null ){
				for (FiseFormato14BD detalle : fiseFormato14BC.getFiseFormato14BDs()) {
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
			if( formulario.getNroValesImpR() != 0 ||
					formulario.getNroValesReptR() != 0 ||
					formulario.getNroValesEntrR() != 0 ||
					formulario.getNroValesFisR() != 0 ||
					formulario.getNroTotalAtenR() != 0
					){
				logger.info("se modificara RURAL");
				//impresion vales
				detalleRural.setImpresionValDsctoCliDisEl(formulario.getImpValDesctoEdeR());
				detalleRural.setImpreValDsctoCliNoDisEl(formulario.getImpValDesctoNoEdeR());
				
				BigDecimal totalImpresion = new BigDecimal(0);
				totalImpresion = totalImpresion.add(detalleRural.getImpresionValDsctoCliDisEl())
						.add(detalleRural.getImpreValDsctoCliNoDisEl());
				detalleRural.setCostoTotalImpresion(totalImpresion);
				detalleRural.setNumeroValesImpreso(formulario.getNroValesImpR());
				if( formulario.getNroValesImpR() !=0 ){
					detalleRural.setCostoUnitarioImpresionVales(detalleRural.getCostoTotalImpresion().divide(new BigDecimal(detalleRural.getNumeroValesImpreso()),2,RoundingMode.HALF_UP));
				}else{
					detalleRural.setCostoUnitarioImpresionVales(BigDecimal.ZERO);
				}
				//reparto vales
				detalleRural.setCostoRepartoValesDescuento(formulario.getCostoTotalValDesctoR());
				detalleRural.setNumeroValesRepartidos(formulario.getNroValesReptR());
				if( formulario.getNroValesReptR() !=0 ){
					detalleRural.setCostoUnitReprtoValeDomici(detalleRural.getCostoRepartoValesDescuento().divide(new BigDecimal(detalleRural.getNumeroValesRepartidos()),2,RoundingMode.HALF_UP));
				}else{
					detalleRural.setCostoUnitReprtoValeDomici(BigDecimal.ZERO);
				}
				//entrega vales
				detalleRural.setCostoTotReprtoValDisEl(formulario.getCostoTotalValOficR());
				detalleRural.setNumeroValesEntregados(formulario.getNroValesEntrR());
				if( formulario.getNroValesEntrR() !=0 ){
					detalleRural.setCostoUnitEntregaValDisEl(detalleRural.getCostoTotReprtoValDisEl().divide(new BigDecimal(detalleRural.getNumeroValesEntregados()),2,RoundingMode.HALF_UP));
				}else{
					detalleRural.setCostoUnitEntregaValDisEl(BigDecimal.ZERO);
				}
				//canje liquidacion vales fisicos
				detalleRural.setCostoEnviarPadronValCanje(formulario.getCostoEnvPadronR());
				detalleRural.setNumeroValesFisicosEmitidos(formulario.getNroValesFisR());
				if( formulario.getNroValesFisR() !=0 ){
					detalleRural.setCostoUnitCanjeLiqValFisi(detalleRural.getCostoEnviarPadronValCanje().divide(new BigDecimal(detalleRural.getNumeroValesFisicosEmitidos()),2,RoundingMode.HALF_UP));
				}else{
					detalleRural.setCostoUnitCanjeLiqValFisi(BigDecimal.ZERO);
				}
				//canje liquidacion vales digitales
				detalleRural.setCostoUnitCanjeValDigital(formulario.getCostoUnitValesDigitR());
				//atencion
				detalleRural.setCostoAtencionSolicitudes(formulario.getCostoAtenSolicR());
				detalleRural.setCostoAtencionConsultaRecla(formulario.getCostoAtenConsR());
				
				BigDecimal totalAtencion = new BigDecimal(0);
				totalAtencion = totalAtencion.add(detalleRural.getCostoAtencionSolicitudes())
						.add(detalleRural.getCostoAtencionConsultaRecla());
				detalleRural.setCostoTotalAtencion(totalAtencion);
				detalleRural.setNumeroTotalAtencion(formulario.getNroTotalAtenR());
				if( formulario.getNroTotalAtenR() !=0 ){
					detalleRural.setCostoUnitarioPorAtencion(detalleRural.getCostoTotalAtencion().divide(new BigDecimal(detalleRural.getNumeroTotalAtencion()),2,RoundingMode.HALF_UP));
				}else{
					detalleRural.setCostoUnitarioPorAtencion(BigDecimal.ZERO);
				}
				//gestion administrativa
				detalleRural.setCostoPersonal(formulario.getCostoPersonalR());
				detalleRural.setCapacitacionAgentesAutGlp(formulario.getCapacAgentR());
				detalleRural.setUtilesMaterialesOficina(formulario.getUtilMatOficR());
				
				BigDecimal totalGestion = new BigDecimal(0);
				totalGestion = totalGestion.add(detalleRural.getCostoPersonal())
						.add(detalleRural.getCapacitacionAgentesAutGlp())
						.add(detalleRural.getUtilesMaterialesOficina());
				detalleRural.setCostoTotalGestionAdministra(totalGestion);
				//
				//--detalleRural.setFiseFormato14BC(fiseFormato14BC);
				//
				detalleRural.setUsuarioActualizacion(formulario.getUsuario());
				detalleRural.setTerminalActualizacion(formulario.getTerminal());
				detalleRural.setFechaActualizacion(hoy);
				lista.add(detalleRural);
			}
			//PROVINCIA
			if( formulario.getNroValesImpP() != 0 ||
					formulario.getNroValesReptP() != 0 ||
					formulario.getNroValesEntrP() != 0 ||
					formulario.getNroValesFisP() != 0 ||
					formulario.getNroTotalAtenP() != 0
					){
				logger.info("se modificara PROVINCIA");
				//impresion vales
				detalleProvincia.setImpresionValDsctoCliDisEl(formulario.getImpValDesctoEdeP());
				detalleProvincia.setImpreValDsctoCliNoDisEl(formulario.getImpValDesctoNoEdeP());
				
				BigDecimal totalImpresion = new BigDecimal(0);
				totalImpresion = totalImpresion.add(detalleProvincia.getImpresionValDsctoCliDisEl())
						.add(detalleProvincia.getImpreValDsctoCliNoDisEl());
				detalleProvincia.setCostoTotalImpresion(totalImpresion);
				detalleProvincia.setNumeroValesImpreso(formulario.getNroValesImpP());
				if( formulario.getNroValesImpP() !=0 ){
					detalleProvincia.setCostoUnitarioImpresionVales(detalleProvincia.getCostoTotalImpresion().divide(new BigDecimal(detalleProvincia.getNumeroValesImpreso()),2,RoundingMode.HALF_UP));
				}else{
					detalleProvincia.setCostoUnitarioImpresionVales(BigDecimal.ZERO);
				}
				//reparto vales
				detalleProvincia.setCostoRepartoValesDescuento(formulario.getCostoTotalValDesctoP());
				detalleProvincia.setNumeroValesRepartidos(formulario.getNroValesReptP());
				if( formulario.getNroValesReptP() !=0 ){
					detalleProvincia.setCostoUnitReprtoValeDomici(detalleProvincia.getCostoRepartoValesDescuento().divide(new BigDecimal(detalleProvincia.getNumeroValesRepartidos()),2,RoundingMode.HALF_UP));
				}else{
					detalleProvincia.setCostoUnitReprtoValeDomici(BigDecimal.ZERO);
				}
				//entrega vales
				detalleProvincia.setCostoTotReprtoValDisEl(formulario.getCostoTotalValOficP());
				detalleProvincia.setNumeroValesEntregados(formulario.getNroValesEntrP());
				if( formulario.getNroValesEntrP() !=0 ){
					detalleProvincia.setCostoUnitEntregaValDisEl(detalleProvincia.getCostoTotReprtoValDisEl().divide(new BigDecimal(detalleProvincia.getNumeroValesEntregados()),2,RoundingMode.HALF_UP));
				}else{
					detalleProvincia.setCostoUnitEntregaValDisEl(BigDecimal.ZERO);
				}
				//canje liquidacion vales fisicos
				detalleProvincia.setCostoEnviarPadronValCanje(formulario.getCostoEnvPadronP());
				detalleProvincia.setNumeroValesFisicosEmitidos(formulario.getNroValesFisP());
				if( formulario.getNroValesFisP() !=0 ){
					detalleProvincia.setCostoUnitCanjeLiqValFisi(detalleProvincia.getCostoEnviarPadronValCanje().divide(new BigDecimal(detalleProvincia.getNumeroValesFisicosEmitidos()),2,RoundingMode.HALF_UP));
				}else{
					detalleProvincia.setCostoUnitCanjeLiqValFisi(BigDecimal.ZERO);
				}
				//canje liquidacion vales digitales
				detalleProvincia.setCostoUnitCanjeValDigital(formulario.getCostoUnitValesDigitP());
				//atencion
				detalleProvincia.setCostoAtencionSolicitudes(formulario.getCostoAtenSolicP());
				detalleProvincia.setCostoAtencionConsultaRecla(formulario.getCostoAtenConsP());
				
				BigDecimal totalAtencion = new BigDecimal(0);
				totalAtencion = totalAtencion.add(detalleProvincia.getCostoAtencionSolicitudes())
						.add(detalleProvincia.getCostoAtencionConsultaRecla());
				detalleProvincia.setCostoTotalAtencion(totalAtencion);
				detalleProvincia.setNumeroTotalAtencion(formulario.getNroTotalAtenP());
				if( formulario.getNroTotalAtenP() !=0 ){
					detalleProvincia.setCostoUnitarioPorAtencion(detalleProvincia.getCostoTotalAtencion().divide(new BigDecimal(detalleProvincia.getNumeroTotalAtencion()),2,RoundingMode.HALF_UP));
				}else{
					detalleProvincia.setCostoUnitarioPorAtencion(BigDecimal.ZERO);
				}
				//gestion administrativa
				detalleProvincia.setCostoPersonal(formulario.getCostoPersonalP());
				detalleProvincia.setCapacitacionAgentesAutGlp(formulario.getCapacAgentP());
				detalleProvincia.setUtilesMaterialesOficina(formulario.getUtilMatOficP());
				
				BigDecimal totalGestion = new BigDecimal(0);
				totalGestion = totalGestion.add(detalleProvincia.getCostoPersonal())
						.add(detalleProvincia.getCapacitacionAgentesAutGlp())
						.add(detalleProvincia.getUtilesMaterialesOficina());
				detalleProvincia.setCostoTotalGestionAdministra(totalGestion);
				//
				//--detalleProvincia.setFiseFormato14BC(fiseFormato14BC);
				//
				detalleProvincia.setUsuarioActualizacion(formulario.getUsuario());
				detalleProvincia.setTerminalActualizacion(formulario.getTerminal());
				detalleProvincia.setFechaActualizacion(hoy);
				lista.add(detalleProvincia);
			}
			//LIMA
			if( formulario.getNroValesImpL() != 0 ||
					formulario.getNroValesReptL() != 0 ||
					formulario.getNroValesEntrL() != 0 ||
					formulario.getNroValesFisL() != 0 ||
					formulario.getNroTotalAtenL() != 0
					){
				logger.info("se modificara LIMA");
				//impresion vales
				detalleLima.setImpresionValDsctoCliDisEl(formulario.getImpValDesctoEdeL());
				detalleLima.setImpreValDsctoCliNoDisEl(formulario.getImpValDesctoNoEdeL());
				
				BigDecimal totalImpresion = new BigDecimal(0);
				totalImpresion = totalImpresion.add(detalleLima.getImpresionValDsctoCliDisEl())
						.add(detalleLima.getImpreValDsctoCliNoDisEl());
				detalleLima.setCostoTotalImpresion(totalImpresion);
				detalleLima.setNumeroValesImpreso(formulario.getNroValesImpL());
				if( formulario.getNroValesImpL() !=0 ){
					detalleLima.setCostoUnitarioImpresionVales(detalleLima.getCostoTotalImpresion().divide(new BigDecimal(detalleLima.getNumeroValesImpreso()),2,RoundingMode.HALF_UP));
				}else{
					detalleLima.setCostoUnitarioImpresionVales(BigDecimal.ZERO);
				}
				//reparto vales
				detalleLima.setCostoRepartoValesDescuento(formulario.getCostoTotalValDesctoL());
				detalleLima.setNumeroValesRepartidos(formulario.getNroValesReptL());
				if( formulario.getNroValesReptL() !=0 ){
					detalleLima.setCostoUnitReprtoValeDomici(detalleLima.getCostoRepartoValesDescuento().divide(new BigDecimal(detalleLima.getNumeroValesRepartidos()),2,RoundingMode.HALF_UP));
				}else{
					detalleLima.setCostoUnitReprtoValeDomici(BigDecimal.ZERO);
				}
				//entrega vales
				detalleLima.setCostoTotReprtoValDisEl(formulario.getCostoTotalValOficL());
				detalleLima.setNumeroValesEntregados(formulario.getNroValesEntrL());
				if( formulario.getNroValesEntrL() !=0 ){
					detalleLima.setCostoUnitEntregaValDisEl(detalleLima.getCostoTotReprtoValDisEl().divide(new BigDecimal(detalleLima.getNumeroValesEntregados()),2,RoundingMode.HALF_UP));
				}else{
					detalleLima.setCostoUnitEntregaValDisEl(BigDecimal.ZERO);
				}
				//canje liquidacion vales fisicos
				detalleLima.setCostoEnviarPadronValCanje(formulario.getCostoEnvPadronL());
				detalleLima.setNumeroValesFisicosEmitidos(formulario.getNroValesFisL());
				if( formulario.getNroValesFisL() !=0 ){
					detalleLima.setCostoUnitCanjeLiqValFisi(detalleLima.getCostoEnviarPadronValCanje().divide(new BigDecimal(detalleLima.getNumeroValesFisicosEmitidos()),2,RoundingMode.HALF_UP));
				}else{
					detalleLima.setCostoUnitCanjeLiqValFisi(BigDecimal.ZERO);
				}
				//canje liquidacion vales digitales
				detalleLima.setCostoUnitCanjeValDigital(formulario.getCostoUnitValesDigitL());
				//atencion
				detalleLima.setCostoAtencionSolicitudes(formulario.getCostoAtenSolicL());
				detalleLima.setCostoAtencionConsultaRecla(formulario.getCostoAtenConsL());
				
				BigDecimal totalAtencion = new BigDecimal(0);
				totalAtencion = totalAtencion.add(detalleLima.getCostoAtencionSolicitudes())
						.add(detalleLima.getCostoAtencionConsultaRecla());
				detalleLima.setCostoTotalAtencion(totalAtencion);
				detalleLima.setNumeroTotalAtencion(formulario.getNroTotalAtenL());
				if( formulario.getNroTotalAtenL() !=0 ){
					detalleLima.setCostoUnitarioPorAtencion(detalleLima.getCostoTotalAtencion().divide(new BigDecimal(detalleLima.getNumeroTotalAtencion()),2,RoundingMode.HALF_UP));
				}else{
					detalleLima.setCostoUnitarioPorAtencion(BigDecimal.ZERO);
				}
				//gestion administrativa
				detalleLima.setCostoPersonal(formulario.getCostoPersonalL());
				detalleLima.setCapacitacionAgentesAutGlp(formulario.getCapacAgentL());
				detalleLima.setUtilesMaterialesOficina(formulario.getUtilMatOficL());
				
				BigDecimal totalGestion = new BigDecimal(0);
				totalGestion = totalGestion.add(detalleLima.getCostoPersonal())
						.add(detalleLima.getCapacitacionAgentesAutGlp())
						.add(detalleLima.getUtilesMaterialesOficina());
				detalleLima.setCostoTotalGestionAdministra(totalGestion);
				//
				//--detalleLima.setFiseFormato14BC(fiseFormato14BC);
				//
				detalleLima.setUsuarioActualizacion(formulario.getUsuario());
				detalleLima.setTerminalActualizacion(formulario.getTerminal());
				detalleLima.setFechaActualizacion(hoy);
				lista.add(detalleLima);
			}
		
			fiseFormato14BC.setUsuarioActualizacion(formulario.getUsuario());
			fiseFormato14BC.setTerminalActualizacion(formulario.getTerminal());
			fiseFormato14BC.setFechaActualizacion(hoy);
		
			if( FiseConstants.TIPOARCHIVO_XLS.equals(formulario.getTipoArchivo()) ){
				fiseFormato14BC.setNombreArchivoExcel(formulario.getNombreArchivo());
			}else if( FiseConstants.TIPOARCHIVO_TXT.equals(formulario.getTipoArchivo()) ){
				fiseFormato14BC.setNombreArchivoTexto(formulario.getNombreArchivo());
			}
			
			formato14BCDao.modificarFormato14BC(fiseFormato14BC);
			//add
			for (FiseFormato14BD detalle : lista) {
				formato14BDDao.modificarFormato14BD(detalle);
			}
			dto= fiseFormato14BC;
			
		}	catch (Exception e) {
			logger.error("--error"+e.getMessage());
			e.printStackTrace();
			throw e;
		}
		//
		return dto;

	}
	
	@Override
	@Transactional(propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
	public void eliminarFormato14BC(FiseFormato14BC fiseFormato14BC) {
		//eliminar antes los detalles creados para esa cabecera
		List<FiseFormato14BD> lista = null;
		lista = formato14BDDao.listarFormato14BDByFormato14BC(fiseFormato14BC);
		//por el momento no se esta borrando las dependencias hacias el detalle de observaciones
		//luego aumentar esta dependencia
		for (FiseFormato14BD detalle : lista) {
			List<FiseFormato14BDOb> listaObservacion = formato14BObsDao.listarFormato14BDObByFormato14BD(detalle);
			for (FiseFormato14BDOb observacion : listaObservacion) {
				formato14BObsDao.eliminarFormato14BDOb(observacion);
			}
			formato14BDDao.eliminarFormato14BD(detalle);
		}
		formato14BCDao.eliminarFormato14BC(fiseFormato14BC);
	}
	
	//reporte
	@Override
	public Formato14BCBean estructurarFormato14BBeanByFiseFormato14BC(FiseFormato14BC formato){
		
		Formato14BCBean formato14BBean = new Formato14BCBean();
		
		formato14BBean.setAnioPresent(formato.getId().getAnoPresentacion());
		formato14BBean.setAnioInicioVigencia(formato.getId().getAnoInicioVigencia());
		formato14BBean.setAnioFinVigencia(formato.getId().getAnoFinVigencia());
		
    	for (FiseFormato14BD detalle : formato.getFiseFormato14BDs()) {
			if( FiseConstants.ZONABENEF_RURAL_COD == detalle.getId().getIdZonaBenef() ){
				
				formato14BBean.setImpValDesctoEdeR(detalle.getImpresionValDsctoCliDisEl());
				formato14BBean.setImpValDesctoNoEdeR(detalle.getImpreValDsctoCliNoDisEl());
				formato14BBean.setCostoTotalImpR(detalle.getCostoTotalImpresion());
				formato14BBean.setNroValesImpR(detalle.getNumeroValesImpreso());
				formato14BBean.setCostoUnitImpValesR(detalle.getCostoUnitarioImpresionVales());
				
				formato14BBean.setCostoTotalValDesctoR(detalle.getCostoRepartoValesDescuento());
				formato14BBean.setNroValesReptR(detalle.getNumeroValesRepartidos());
				formato14BBean.setCostoUnitReptValesR(detalle.getCostoUnitReprtoValeDomici());
				
				formato14BBean.setCostoTotalValOficR(detalle.getCostoTotReprtoValDisEl());
				formato14BBean.setNroValesEntrR(detalle.getNumeroValesEntregados());
				formato14BBean.setCostoUnitEntrValesR(detalle.getCostoUnitEntregaValDisEl());
				
				formato14BBean.setCostoEnvPadronR(detalle.getCostoEnviarPadronValCanje());
				formato14BBean.setNroValesFisR(detalle.getNumeroValesFisicosEmitidos());
				formato14BBean.setCostoUnitLiqR(detalle.getCostoUnitCanjeLiqValFisi());
				
				formato14BBean.setCostoUnitValesDigitR(detalle.getCostoUnitCanjeValDigital());
				
				formato14BBean.setCostoAtenSolicR(detalle.getCostoAtencionSolicitudes());
				formato14BBean.setCostoAtenConsR(detalle.getCostoAtencionConsultaRecla());
				formato14BBean.setCostoTotalAtenR(detalle.getCostoTotalAtencion());
				formato14BBean.setNroTotalAtenR(detalle.getNumeroTotalAtencion());
				formato14BBean.setCostoUnitAtenR(detalle.getCostoUnitarioPorAtencion());
				
				formato14BBean.setCostoPersonalR(detalle.getCostoPersonal());
				formato14BBean.setCapacAgentR(detalle.getCapacitacionAgentesAutGlp());
				formato14BBean.setUtilMatOficR(detalle.getUtilesMaterialesOficina());
				formato14BBean.setCostoTotalGestR(detalle.getCostoTotalGestionAdministra());
				
			}else if( FiseConstants.ZONABENEF_PROVINCIA_COD == detalle.getId().getIdZonaBenef() ){
				
				formato14BBean.setImpValDesctoEdeP(detalle.getImpresionValDsctoCliDisEl());
				formato14BBean.setImpValDesctoNoEdeP(detalle.getImpreValDsctoCliNoDisEl());
				formato14BBean.setCostoTotalImpP(detalle.getCostoTotalImpresion());
				formato14BBean.setNroValesImpP(detalle.getNumeroValesImpreso());
				formato14BBean.setCostoUnitImpValesP(detalle.getCostoUnitarioImpresionVales());
				
				formato14BBean.setCostoTotalValDesctoP(detalle.getCostoRepartoValesDescuento());
				formato14BBean.setNroValesReptP(detalle.getNumeroValesRepartidos());
				formato14BBean.setCostoUnitReptValesP(detalle.getCostoUnitReprtoValeDomici());
				
				formato14BBean.setCostoTotalValOficP(detalle.getCostoTotReprtoValDisEl());
				formato14BBean.setNroValesEntrP(detalle.getNumeroValesEntregados());
				formato14BBean.setCostoUnitEntrValesP(detalle.getCostoUnitEntregaValDisEl());
				
				formato14BBean.setCostoEnvPadronP(detalle.getCostoEnviarPadronValCanje());
				formato14BBean.setNroValesFisP(detalle.getNumeroValesFisicosEmitidos());
				formato14BBean.setCostoUnitLiqP(detalle.getCostoUnitCanjeLiqValFisi());
				
				formato14BBean.setCostoUnitValesDigitP(detalle.getCostoUnitCanjeValDigital());
				
				formato14BBean.setCostoAtenSolicP(detalle.getCostoAtencionSolicitudes());
				formato14BBean.setCostoAtenConsP(detalle.getCostoAtencionConsultaRecla());
				formato14BBean.setCostoTotalAtenP(detalle.getCostoTotalAtencion());
				formato14BBean.setNroTotalAtenP(detalle.getNumeroTotalAtencion());
				formato14BBean.setCostoUnitAtenP(detalle.getCostoUnitarioPorAtencion());
				
				formato14BBean.setCostoPersonalP(detalle.getCostoPersonal());
				formato14BBean.setCapacAgentP(detalle.getCapacitacionAgentesAutGlp());
				formato14BBean.setUtilMatOficP(detalle.getUtilesMaterialesOficina());
				formato14BBean.setCostoTotalGestP(detalle.getCostoTotalGestionAdministra());
				
			}else if( FiseConstants.ZONABENEF_LIMA_COD == detalle.getId().getIdZonaBenef() ){
				
				formato14BBean.setImpValDesctoEdeL(detalle.getImpresionValDsctoCliDisEl());
				formato14BBean.setImpValDesctoNoEdeL(detalle.getImpreValDsctoCliNoDisEl());
				formato14BBean.setCostoTotalImpL(detalle.getCostoTotalImpresion());
				formato14BBean.setNroValesImpL(detalle.getNumeroValesImpreso());
				formato14BBean.setCostoUnitImpValesL(detalle.getCostoUnitarioImpresionVales());
				
				formato14BBean.setCostoTotalValDesctoL(detalle.getCostoRepartoValesDescuento());
				formato14BBean.setNroValesReptL(detalle.getNumeroValesRepartidos());
				formato14BBean.setCostoUnitReptValesL(detalle.getCostoUnitReprtoValeDomici());
				
				formato14BBean.setCostoTotalValOficL(detalle.getCostoTotReprtoValDisEl());
				formato14BBean.setNroValesEntrL(detalle.getNumeroValesEntregados());
				formato14BBean.setCostoUnitEntrValesL(detalle.getCostoUnitEntregaValDisEl());
				
				formato14BBean.setCostoEnvPadronL(detalle.getCostoEnviarPadronValCanje());
				formato14BBean.setNroValesFisL(detalle.getNumeroValesFisicosEmitidos());
				formato14BBean.setCostoUnitLiqL(detalle.getCostoUnitCanjeLiqValFisi());
				
				formato14BBean.setCostoUnitValesDigitL(detalle.getCostoUnitCanjeValDigital());
				
				formato14BBean.setCostoAtenSolicL(detalle.getCostoAtencionSolicitudes());
				formato14BBean.setCostoAtenConsL(detalle.getCostoAtencionConsultaRecla());
				formato14BBean.setCostoTotalAtenL(detalle.getCostoTotalAtencion());
				formato14BBean.setNroTotalAtenL(detalle.getNumeroTotalAtencion());
				formato14BBean.setCostoUnitAtenL(detalle.getCostoUnitarioPorAtencion());
				
				formato14BBean.setCostoPersonalL(detalle.getCostoPersonal());
				formato14BBean.setCapacAgentL(detalle.getCapacitacionAgentesAutGlp());
				formato14BBean.setUtilMatOficL(detalle.getUtilesMaterialesOficina());
				formato14BBean.setCostoTotalGestL(detalle.getCostoTotalGestionAdministra());
				
			}
		}

		return formato14BBean;	
	}
 
	@Override
	public HashMap<String, Object> mapearParametrosFormato14B(Formato14BCBean formato14BBean){
		
		HashMap<String, Object> mapJRParams = new HashMap<String, Object>();
		
		mapJRParams.put(FiseConstants.PARAM_DESC_EMPRESA_F14B, formato14BBean.getDescEmpresa());
		mapJRParams.put(FiseConstants.PARAM_ANO_PRES_F14B, formato14BBean.getAnioPresent());
		mapJRParams.put(FiseConstants.PARAM_DESC_MES_PRES_F14B, formato14BBean.getDescMesPresentacion());
		mapJRParams.put(FiseConstants.PARAM_ANO_INICIO_VIGENCIA_F14B, formato14BBean.getAnioInicioVigencia());
		mapJRParams.put(FiseConstants.PARAM_ANO_FIN_VIGENCIA_F14B, formato14BBean.getAnioFinVigencia());
		
		//RURAL
		mapJRParams.put(FiseConstants.PARAM_IMP_VAL_DSCTO_EDE_R_F14B, formato14BBean.getImpValDesctoEdeR());
		mapJRParams.put(FiseConstants.PARAM_IMP_VAL_DSCTO_NOEDE_R_F14B, formato14BBean.getImpValDesctoNoEdeR());
		mapJRParams.put(FiseConstants.PARAM_CSTO_TOTAL_IMP_R_F14B, formato14BBean.getCostoTotalImpR());
		mapJRParams.put(FiseConstants.PARAM_NRO_VALES_R_F14B, formato14BBean.getNroValesImpR());
		mapJRParams.put(FiseConstants.PARAM_CSTO_UNIT_R_F14B, formato14BBean.getCostoUnitImpValesR());
		
		mapJRParams.put(FiseConstants.PARAM_CSTO_TOTAL_VAL_DSCTO_R_F14B, formato14BBean.getCostoTotalValDesctoR());
		mapJRParams.put(FiseConstants.PARAM_NRO_VALES_RPTDOS_R_F14B, formato14BBean.getNroValesReptR());
		mapJRParams.put(FiseConstants.PARAM_CSTOS_UNIT_DOM_R_F14B, formato14BBean.getCostoUnitReptValesR());
		
		mapJRParams.put(FiseConstants.PARAM_CSTO_TOTAL_AUT_R_F14B, formato14BBean.getCostoTotalValOficR());
		mapJRParams.put(FiseConstants.PARAM_NRO_VALES_ENTREG_R_F14B,formato14BBean.getNroValesEntrR());
		mapJRParams.put(FiseConstants.PARAM_CSTOS_UNIT_ENT_R_F14B,formato14BBean.getCostoUnitEntrValesR());
		
		mapJRParams.put(FiseConstants.PARAM_CSTO_VALES_AUTOR_R_F14B,formato14BBean.getCostoEnvPadronR());
		mapJRParams.put(FiseConstants.PARAM_NRO_VALES_EMIT_R_F14B,formato14BBean.getNroValesFisR());
		mapJRParams.put(FiseConstants.PARAM_CSTO_UNIT_FIS_R_F14B,formato14BBean.getCostoUnitLiqR());
		
		mapJRParams.put(FiseConstants.PARAM_CSTO_UNIT_LIQ_R_F14B,formato14BBean.getCostoUnitValesDigitR());
		
		mapJRParams.put(FiseConstants.PARAM_CSTO_ATCION_SOLIC_R_F14B,formato14BBean.getCostoAtenSolicR());
		mapJRParams.put(FiseConstants.PARAM_CSTO_ATCION_CONSUL_R_F14B,formato14BBean.getCostoAtenConsR());
		mapJRParams.put(FiseConstants.PARAM_CSTO_TOTAL_ATCION_R_F14B,formato14BBean.getCostoTotalAtenR());
		mapJRParams.put(FiseConstants.PARAM_NRO_TOTAL_ATCION_R_F14B,formato14BBean.getNroTotalAtenR());
		mapJRParams.put(FiseConstants.PARAM_CSTO_UNIT_ATEN_R_F14B,formato14BBean.getCostoUnitAtenR());
		
		mapJRParams.put(FiseConstants.PARAM_CSTO_PSNAL_R_F14B,formato14BBean.getCostoPersonalR());
		mapJRParams.put(FiseConstants.PARAM_CPTCON_AGE_R_F14B,formato14BBean.getCapacAgentR());
		mapJRParams.put(FiseConstants.PARAM_UTLES_OFIC_R_F14B,formato14BBean.getUtilMatOficR());
		mapJRParams.put(FiseConstants.PARAM_CSTO_TOTAL_GTION_R_F14B,formato14BBean.getCostoTotalGestR());
		
		//PROVINCIA
		mapJRParams.put(FiseConstants.PARAM_IMP_VAL_DSCTO_EDE_P_F14B, formato14BBean.getImpValDesctoEdeP());
		mapJRParams.put(FiseConstants.PARAM_IMP_VAL_DSCTO_NOEDE_P_F14B, formato14BBean.getImpValDesctoNoEdeP());
		mapJRParams.put(FiseConstants.PARAM_CSTO_TOTAL_IMP_P_F14B, formato14BBean.getCostoTotalImpP());
		mapJRParams.put(FiseConstants.PARAM_NRO_VALES_P_F14B, formato14BBean.getNroValesImpP());
		mapJRParams.put(FiseConstants.PARAM_CSTO_UNIT_P_F14B, formato14BBean.getCostoUnitImpValesP());
		
		mapJRParams.put(FiseConstants.PARAM_CSTO_TOTAL_VAL_DSCTO_P_F14B, formato14BBean.getCostoTotalValDesctoP());
		mapJRParams.put(FiseConstants.PARAM_NRO_VALES_RPTDOS_P_F14B, formato14BBean.getNroValesReptP());
		mapJRParams.put(FiseConstants.PARAM_CSTOS_UNIT_DOM_P_F14B, formato14BBean.getCostoUnitReptValesP());
		
		mapJRParams.put(FiseConstants.PARAM_CSTO_TOTAL_AUT_P_F14B, formato14BBean.getCostoTotalValOficP());
		mapJRParams.put(FiseConstants.PARAM_NRO_VALES_ENTREG_P_F14B,formato14BBean.getNroValesEntrP());
		mapJRParams.put(FiseConstants.PARAM_CSTOS_UNIT_ENT_P_F14B,formato14BBean.getCostoUnitEntrValesP());
		
		mapJRParams.put(FiseConstants.PARAM_CSTO_VALES_AUTOR_P_F14B,formato14BBean.getCostoEnvPadronP());
		mapJRParams.put(FiseConstants.PARAM_NRO_VALES_EMIT_P_F14B,formato14BBean.getNroValesFisP());
		mapJRParams.put(FiseConstants.PARAM_CSTO_UNIT_FIS_P_F14B,formato14BBean.getCostoUnitLiqP());
		
		mapJRParams.put(FiseConstants.PARAM_CSTO_UNIT_LIQ_P_F14B,formato14BBean.getCostoUnitValesDigitP());
		
		mapJRParams.put(FiseConstants.PARAM_CSTO_ATCION_SOLIC_P_F14B,formato14BBean.getCostoAtenSolicP());
		mapJRParams.put(FiseConstants.PARAM_CSTO_ATCION_CONSUL_P_F14B,formato14BBean.getCostoAtenConsP());
		mapJRParams.put(FiseConstants.PARAM_CSTO_TOTAL_ATCION_P_F14B,formato14BBean.getCostoTotalAtenP());
		mapJRParams.put(FiseConstants.PARAM_NRO_TOTAL_ATCION_P_F14B,formato14BBean.getNroTotalAtenP());
		mapJRParams.put(FiseConstants.PARAM_CSTO_UNIT_ATEN_P_F14B,formato14BBean.getCostoUnitAtenP());
		
		mapJRParams.put(FiseConstants.PARAM_CSTO_PSNAL_P_F14B,formato14BBean.getCostoPersonalP());
		mapJRParams.put(FiseConstants.PARAM_CPTCON_AGE_P_F14B,formato14BBean.getCapacAgentP());
		mapJRParams.put(FiseConstants.PARAM_UTLES_OFIC_P_F14B,formato14BBean.getUtilMatOficP());
		mapJRParams.put(FiseConstants.PARAM_CSTO_TOTAL_GTION_P_F14B,formato14BBean.getCostoTotalGestP());
		//LIMA
		mapJRParams.put(FiseConstants.PARAM_IMP_VAL_DSCTO_EDE_L_F14B, formato14BBean.getImpValDesctoEdeL());
		mapJRParams.put(FiseConstants.PARAM_IMP_VAL_DSCTO_NOEDE_L_F14B, formato14BBean.getImpValDesctoNoEdeL());
		mapJRParams.put(FiseConstants.PARAM_CSTO_TOTAL_IMP_L_F14B, formato14BBean.getCostoTotalImpL());
		mapJRParams.put(FiseConstants.PARAM_NRO_VALES_L_F14B, formato14BBean.getNroValesImpL());
		mapJRParams.put(FiseConstants.PARAM_CSTO_UNIT_L_F14B, formato14BBean.getCostoUnitImpValesL());
		
		mapJRParams.put(FiseConstants.PARAM_CSTO_TOTAL_VAL_DSCTO_L_F14B, formato14BBean.getCostoTotalValDesctoL());
		mapJRParams.put(FiseConstants.PARAM_NRO_VALES_RPTDOS_L_F14B, formato14BBean.getNroValesReptL());
		mapJRParams.put(FiseConstants.PARAM_CSTOS_UNIT_DOM_L_F14B, formato14BBean.getCostoUnitReptValesL());
		
		mapJRParams.put(FiseConstants.PARAM_CSTO_TOTAL_AUT_L_F14B, formato14BBean.getCostoTotalValOficL());
		mapJRParams.put(FiseConstants.PARAM_NRO_VALES_ENTREG_L_F14B,formato14BBean.getNroValesEntrL());
		mapJRParams.put(FiseConstants.PARAM_CSTOS_UNIT_ENT_L_F14B,formato14BBean.getCostoUnitEntrValesL());
		
		mapJRParams.put(FiseConstants.PARAM_CSTO_VALES_AUTOR_L_F14B,formato14BBean.getCostoEnvPadronL());
		mapJRParams.put(FiseConstants.PARAM_NRO_VALES_EMIT_L_F14B,formato14BBean.getNroValesFisL());
		mapJRParams.put(FiseConstants.PARAM_CSTO_UNIT_FIS_L_F14B,formato14BBean.getCostoUnitLiqL());
		
		mapJRParams.put(FiseConstants.PARAM_CSTO_UNIT_LIQ_L_F14B,formato14BBean.getCostoUnitValesDigitL());
		
		mapJRParams.put(FiseConstants.PARAM_CSTO_ATCION_SOLIC_L_F14B,formato14BBean.getCostoAtenSolicL());
		mapJRParams.put(FiseConstants.PARAM_CSTO_ATCION_CONSUL_L_F14B,formato14BBean.getCostoAtenConsL());
		mapJRParams.put(FiseConstants.PARAM_CSTO_TOTAL_ATCION_L_F14B,formato14BBean.getCostoTotalAtenL());
		mapJRParams.put(FiseConstants.PARAM_NRO_TOTAL_ATCION_L_F14B,formato14BBean.getNroTotalAtenL());
		mapJRParams.put(FiseConstants.PARAM_CSTO_UNIT_ATEN_L_F14B,formato14BBean.getCostoUnitAtenL());
		
		mapJRParams.put(FiseConstants.PARAM_CSTO_PSNAL_L_F14B,formato14BBean.getCostoPersonalL());
		mapJRParams.put(FiseConstants.PARAM_CPTCON_AGE_L_F14B,formato14BBean.getCapacAgentL());
		mapJRParams.put(FiseConstants.PARAM_UTLES_OFIC_L_F14B,formato14BBean.getUtilMatOficL());
		mapJRParams.put(FiseConstants.PARAM_CSTO_TOTAL_GTION_L_F14B,formato14BBean.getCostoTotalGestL());
		
		//logs
		logger.info(FiseConstants.PARAM_IMP_VAL_DSCTO_EDE_R_F14B+":"+ formato14BBean.getImpValDesctoEdeR());
		logger.info(FiseConstants.PARAM_IMP_VAL_DSCTO_NOEDE_R_F14B+":"+ formato14BBean.getImpValDesctoNoEdeR());
		logger.info(FiseConstants.PARAM_CSTO_TOTAL_IMP_R_F14B+":"+ formato14BBean.getCostoTotalImpR());
		logger.info(FiseConstants.PARAM_NRO_VALES_R_F14B+":"+ formato14BBean.getNroValesImpR());
		logger.info(FiseConstants.PARAM_CSTO_UNIT_R_F14B+":"+ formato14BBean.getCostoUnitImpValesR());
		
		logger.info(FiseConstants.PARAM_CSTO_TOTAL_VAL_DSCTO_R_F14B+":"+ formato14BBean.getCostoTotalValDesctoR());
		logger.info(FiseConstants.PARAM_NRO_VALES_RPTDOS_R_F14B+":"+ formato14BBean.getNroValesReptR());
		logger.info(FiseConstants.PARAM_CSTOS_UNIT_DOM_R_F14B+":"+ formato14BBean.getCostoUnitReptValesR());
		
		logger.info(FiseConstants.PARAM_CSTO_TOTAL_AUT_R_F14B+":"+ formato14BBean.getCostoTotalValOficR());
		logger.info(FiseConstants.PARAM_NRO_VALES_ENTREG_R_F14B+":"+formato14BBean.getNroValesEntrR());
		logger.info(FiseConstants.PARAM_CSTOS_UNIT_ENT_R_F14B+":"+formato14BBean.getCostoUnitEntrValesR());
		
		logger.info(FiseConstants.PARAM_CSTO_VALES_AUTOR_R_F14B+":"+formato14BBean.getCostoEnvPadronR());
		logger.info(FiseConstants.PARAM_NRO_VALES_EMIT_R_F14B+":"+formato14BBean.getNroValesFisR());
		logger.info(FiseConstants.PARAM_CSTO_UNIT_FIS_R_F14B+":"+formato14BBean.getCostoUnitLiqR());
		
		logger.info(FiseConstants.PARAM_CSTO_UNIT_LIQ_R_F14B+":"+formato14BBean.getCostoUnitValesDigitR());
		
		logger.info(FiseConstants.PARAM_CSTO_ATCION_SOLIC_R_F14B+":"+formato14BBean.getCostoAtenSolicR());
		logger.info(FiseConstants.PARAM_CSTO_ATCION_CONSUL_R_F14B+":"+formato14BBean.getCostoAtenConsR());
		logger.info(FiseConstants.PARAM_CSTO_TOTAL_ATCION_R_F14B+":"+formato14BBean.getCostoTotalAtenR());
		logger.info(FiseConstants.PARAM_NRO_TOTAL_ATCION_R_F14B+":"+formato14BBean.getNroTotalAtenR());
		logger.info(FiseConstants.PARAM_CSTO_UNIT_ATEN_R_F14B+":"+formato14BBean.getCostoUnitAtenR());
		
		logger.info(FiseConstants.PARAM_CSTO_PSNAL_R_F14B+":"+formato14BBean.getCostoPersonalR());
		logger.info(FiseConstants.PARAM_CPTCON_AGE_R_F14B+":"+formato14BBean.getCapacAgentR());
		logger.info(FiseConstants.PARAM_UTLES_OFIC_R_F14B+":"+formato14BBean.getUtilMatOficR());
		logger.info(FiseConstants.PARAM_CSTO_TOTAL_GTION_R_F14B+":"+formato14BBean.getCostoTotalGestR());
		
		//PROVINCIA
		logger.info(FiseConstants.PARAM_IMP_VAL_DSCTO_EDE_P_F14B+":"+ formato14BBean.getImpValDesctoEdeP());
		logger.info(FiseConstants.PARAM_IMP_VAL_DSCTO_NOEDE_P_F14B+":"+ formato14BBean.getImpValDesctoNoEdeP());
		logger.info(FiseConstants.PARAM_CSTO_TOTAL_IMP_P_F14B+":"+ formato14BBean.getCostoTotalImpP());
		logger.info(FiseConstants.PARAM_NRO_VALES_P_F14B+":"+ formato14BBean.getNroValesImpP());
		logger.info(FiseConstants.PARAM_CSTO_UNIT_P_F14B+":"+ formato14BBean.getCostoUnitImpValesP());
		
		logger.info(FiseConstants.PARAM_CSTO_TOTAL_VAL_DSCTO_P_F14B+":"+ formato14BBean.getCostoTotalValDesctoP());
		logger.info(FiseConstants.PARAM_NRO_VALES_RPTDOS_P_F14B+":"+ formato14BBean.getNroValesReptP());
		logger.info(FiseConstants.PARAM_CSTOS_UNIT_DOM_P_F14B+":"+ formato14BBean.getCostoUnitReptValesP());
		
		logger.info(FiseConstants.PARAM_CSTO_TOTAL_AUT_P_F14B+":"+ formato14BBean.getCostoTotalValOficP());
		logger.info(FiseConstants.PARAM_NRO_VALES_ENTREG_P_F14B+":"+formato14BBean.getNroValesEntrP());
		logger.info(FiseConstants.PARAM_CSTOS_UNIT_ENT_P_F14B+":"+formato14BBean.getCostoUnitEntrValesP());
		
		logger.info(FiseConstants.PARAM_CSTO_VALES_AUTOR_P_F14B+":"+formato14BBean.getCostoEnvPadronP());
		logger.info(FiseConstants.PARAM_NRO_VALES_EMIT_P_F14B+":"+formato14BBean.getNroValesFisP());
		logger.info(FiseConstants.PARAM_CSTO_UNIT_FIS_P_F14B+":"+formato14BBean.getCostoUnitLiqP());
		
		logger.info(FiseConstants.PARAM_CSTO_UNIT_LIQ_P_F14B+":"+formato14BBean.getCostoUnitValesDigitP());
		
		logger.info(FiseConstants.PARAM_CSTO_ATCION_SOLIC_P_F14B+":"+formato14BBean.getCostoAtenSolicP());
		logger.info(FiseConstants.PARAM_CSTO_ATCION_CONSUL_P_F14B+":"+formato14BBean.getCostoAtenConsP());
		logger.info(FiseConstants.PARAM_CSTO_TOTAL_ATCION_P_F14B+":"+formato14BBean.getCostoTotalAtenP());
		logger.info(FiseConstants.PARAM_NRO_TOTAL_ATCION_P_F14B+":"+formato14BBean.getNroTotalAtenP());
		logger.info(FiseConstants.PARAM_CSTO_UNIT_ATEN_P_F14B+":"+formato14BBean.getCostoUnitAtenP());
		
		logger.info(FiseConstants.PARAM_CSTO_PSNAL_P_F14B+":"+formato14BBean.getCostoPersonalP());
		logger.info(FiseConstants.PARAM_CPTCON_AGE_P_F14B+":"+formato14BBean.getCapacAgentP());
		logger.info(FiseConstants.PARAM_UTLES_OFIC_P_F14B+":"+formato14BBean.getUtilMatOficP());
		logger.info(FiseConstants.PARAM_CSTO_TOTAL_GTION_P_F14B+":"+formato14BBean.getCostoTotalGestP());
		//LIMA
		logger.info(FiseConstants.PARAM_IMP_VAL_DSCTO_EDE_L_F14B+":"+ formato14BBean.getImpValDesctoEdeL());
		logger.info(FiseConstants.PARAM_IMP_VAL_DSCTO_NOEDE_L_F14B+":"+ formato14BBean.getImpValDesctoNoEdeL());
		logger.info(FiseConstants.PARAM_CSTO_TOTAL_IMP_L_F14B+":"+ formato14BBean.getCostoTotalImpL());
		logger.info(FiseConstants.PARAM_NRO_VALES_L_F14B+":"+ formato14BBean.getNroValesImpL());
		logger.info(FiseConstants.PARAM_CSTO_UNIT_L_F14B+":"+ formato14BBean.getCostoUnitImpValesL());
		
		logger.info(FiseConstants.PARAM_CSTO_TOTAL_VAL_DSCTO_L_F14B+":"+ formato14BBean.getCostoTotalValDesctoL());
		logger.info(FiseConstants.PARAM_NRO_VALES_RPTDOS_L_F14B+":"+ formato14BBean.getNroValesReptL());
		logger.info(FiseConstants.PARAM_CSTOS_UNIT_DOM_L_F14B+":"+ formato14BBean.getCostoUnitReptValesL());
		
		logger.info(FiseConstants.PARAM_CSTO_TOTAL_AUT_L_F14B+":"+ formato14BBean.getCostoTotalValOficL());
		logger.info(FiseConstants.PARAM_NRO_VALES_ENTREG_L_F14B+":"+formato14BBean.getNroValesEntrL());
		logger.info(FiseConstants.PARAM_CSTOS_UNIT_ENT_L_F14B+":"+formato14BBean.getCostoUnitEntrValesL());
		
		logger.info(FiseConstants.PARAM_CSTO_VALES_AUTOR_L_F14B+":"+formato14BBean.getCostoEnvPadronL());
		logger.info(FiseConstants.PARAM_NRO_VALES_EMIT_L_F14B+":"+formato14BBean.getNroValesFisL());
		logger.info(FiseConstants.PARAM_CSTO_UNIT_FIS_L_F14B+":"+formato14BBean.getCostoUnitLiqL());
		
		logger.info(FiseConstants.PARAM_CSTO_UNIT_LIQ_L_F14B+":"+formato14BBean.getCostoUnitValesDigitL());
		
		logger.info(FiseConstants.PARAM_CSTO_ATCION_SOLIC_L_F14B+":"+formato14BBean.getCostoAtenSolicL());
		logger.info(FiseConstants.PARAM_CSTO_ATCION_CONSUL_L_F14B+":"+formato14BBean.getCostoAtenConsL());
		logger.info(FiseConstants.PARAM_CSTO_TOTAL_ATCION_L_F14B+":"+formato14BBean.getCostoTotalAtenL());
		logger.info(FiseConstants.PARAM_NRO_TOTAL_ATCION_L_F14B+":"+formato14BBean.getNroTotalAtenL());
		logger.info(FiseConstants.PARAM_CSTO_UNIT_ATEN_L_F14B+":"+formato14BBean.getCostoUnitAtenL());
		
		logger.info(FiseConstants.PARAM_CSTO_PSNAL_L_F14B+":"+formato14BBean.getCostoPersonalL());
		logger.info(FiseConstants.PARAM_CPTCON_AGE_L_F14B+":"+formato14BBean.getCapacAgentL());
		logger.info(FiseConstants.PARAM_UTLES_OFIC_L_F14B+":"+formato14BBean.getUtilMatOficL());
		logger.info(FiseConstants.PARAM_CSTO_TOTAL_GTION_L_F14B+":"+formato14BBean.getCostoTotalGestL());
		
		
		/*
		//1.1
		mapJRParams.put(FiseConstants.PARAM_IMP_VAL_DSCTO_EDE_R_F14B, formato14BBean.getImpValDesctoEdeR());
		mapJRParams.put(FiseConstants.PARAM_IMP_VAL_DSCTO_EDE_P_F14B, formato14BBean.getImpValDesctoEdeR());
		mapJRParams.put(FiseConstants.PARAM_IMP_VAL_DSCTO_EDE_L_F14B, formato14BBean.getImpValDesctoEdeR());
		//1.2
		mapJRParams.put(FiseConstants.PARAM_IMP_VAL_DSCTO_NOEDE_R_F14B, formato14BBean.getImpValDesctoNoEdeR());
		mapJRParams.put(FiseConstants.PARAM_IMP_VAL_DSCTO_NOEDE_P_F14B, formato14BBean.getImpValDesctoNoEdeP());
		mapJRParams.put(FiseConstants.PARAM_IMP_VAL_DSCTO_NOEDE_L_F14B, formato14BBean.getImpValDesctoNoEdeL());
		//1.3
		mapJRParams.put(FiseConstants.PARAM_CSTO_TOTAL_IMP_R_F14B, formato14BBean.getCostoTotalImpR());
		mapJRParams.put(FiseConstants.PARAM_CSTO_TOTAL_IMP_P_F14B, formato14BBean.getCostoTotalImpP());
		mapJRParams.put(FiseConstants.PARAM_CSTO_TOTAL_IMP_L_F14B, formato14BBean.getCostoTotalImpL());
		//1.4		
		mapJRParams.put(FiseConstants.PARAM_NRO_VALES_R_F14B, formato14BBean.getNroValesEntrR());
		mapJRParams.put(FiseConstants.PARAM_NRO_VALES_P_F14B, formato14BBean.getNroValesEntrP());
		mapJRParams.put(FiseConstants.PARAM_NRO_VALES_L_F14B, formato14BBean.getNroValesEntrL());
		//1.5
		mapJRParams.put(FiseConstants.PARAM_CSTO_UNIT_R_F14B, formato14BBean.getCostoUnitImpValesR());
		mapJRParams.put(FiseConstants.PARAM_CSTO_UNIT_P_F14B, formato14BBean.getCostoUnitImpValesP());
		mapJRParams.put(FiseConstants.PARAM_CSTO_UNIT_L_F14B, formato14BBean.getCostoUnitImpValesL());
		//2.1
		mapJRParams.put(FiseConstants.PARAM_CSTO_TOTAL_VAL_DSCTO_R_F14B, formato14BBean.getCostoTotalValDesctoR());
		mapJRParams.put(FiseConstants.PARAM_CSTO_TOTAL_VAL_DSCTO_P_F14B, formato14BBean.getCostoTotalValDesctoP());
		mapJRParams.put(FiseConstants.PARAM_CSTO_TOTAL_VAL_DSCTO_L_F14B, formato14BBean.getCostoTotalValDesctoL());
		//2.2
		mapJRParams.put(FiseConstants.PARAM_NRO_VALES_RPTDOS_R_F14B, formato14BBean.getNroValesReptR());
		mapJRParams.put(FiseConstants.PARAM_NRO_VALES_RPTDOS_P_F14B, formato14BBean.getNroValesReptP());
		mapJRParams.put(FiseConstants.PARAM_NRO_VALES_RPTDOS_L_F14B, formato14BBean.getNroValesReptL());
		//2.3
		mapJRParams.put(FiseConstants.PARAM_CSTOS_UNIT_DOM_R_F14B, formato14BBean.getCostoUnitReptValesR());
		mapJRParams.put(FiseConstants.PARAM_CSTOS_UNIT_DOM_P_F14B, formato14BBean.getCostoUnitReptValesP());
		mapJRParams.put(FiseConstants.PARAM_CSTOS_UNIT_DOM_L_F14B, formato14BBean.getCostoUnitReptValesL());
		//3.1
		mapJRParams.put(FiseConstants.PARAM_CSTO_TOTAL_AUT_R_F14B, formato14BBean.getCostoTotalValOficR());
		mapJRParams.put(FiseConstants.PARAM_CSTO_TOTAL_AUT_P_F14B, formato14BBean.getCostoTotalValOficP());
		mapJRParams.put(FiseConstants.PARAM_CSTO_TOTAL_AUT_L_F14B, formato14BBean.getCostoTotalValOficL());
		//3.2
		mapJRParams.put(FiseConstants.PARAM_NRO_VALES_ENTREG_R_F14B,formato14BBean.getNroValesEntrR());
		mapJRParams.put(FiseConstants.PARAM_NRO_VALES_ENTREG_P_F14B,formato14BBean.getNroValesEntrP());
		mapJRParams.put(FiseConstants.PARAM_NRO_VALES_ENTREG_L_F14B,formato14BBean.getNroValesEntrL());
		//3.3
		mapJRParams.put(FiseConstants.PARAM_CSTOS_UNIT_ENT_R_F14B,formato14BBean.getCostoUnitEntrValesR());
		mapJRParams.put(FiseConstants.PARAM_CSTOS_UNIT_ENT_P_F14B,formato14BBean.getCostoUnitEntrValesP());
		mapJRParams.put(FiseConstants.PARAM_CSTOS_UNIT_ENT_L_F14B,formato14BBean.getCostoUnitEntrValesL());
		//4.1
		mapJRParams.put(FiseConstants.PARAM_CSTO_VALES_AUTOR_R_F14B,formato14BBean.getCostoEnvPadronR());
		mapJRParams.put(FiseConstants.PARAM_CSTO_VALES_AUTOR_P_F14B,formato14BBean.getCostoEnvPadronP());
		mapJRParams.put(FiseConstants.PARAM_CSTO_VALES_AUTOR_L_F14B,formato14BBean.getCostoEnvPadronL());
		//4.2
		mapJRParams.put(FiseConstants.PARAM_NRO_VALES_EMIT_R_F14B,formato14BBean.getNroValesFisR());
		mapJRParams.put(FiseConstants.PARAM_NRO_VALES_EMIT_P_F14B,formato14BBean.getNroValesFisP());
		mapJRParams.put(FiseConstants.PARAM_NRO_VALES_EMIT_L_F14B,formato14BBean.getNroValesFisL());
		//4.3
		mapJRParams.put(FiseConstants.PARAM_CSTO_UNIT_FIS_R_F14B,formato14BBean.getCostoUnitLiqR());
		mapJRParams.put(FiseConstants.PARAM_CSTO_UNIT_FIS_P_F14B,formato14BBean.getCostoUnitLiqP());
		mapJRParams.put(FiseConstants.PARAM_CSTO_UNIT_FIS_L_F14B,formato14BBean.getCostoUnitLiqL());
		//5.1
		mapJRParams.put(FiseConstants.PARAM_CSTO_UNIT_LIQ_R_F14B,formato14BBean.getCostoUnitValesDigitR());
		mapJRParams.put(FiseConstants.PARAM_CSTO_UNIT_LIQ_P_F14B,formato14BBean.getCostoUnitValesDigitP());
		mapJRParams.put(FiseConstants.PARAM_CSTO_UNIT_LIQ_L_F14B,formato14BBean.getCostoUnitValesDigitL());
		//6.1
		mapJRParams.put(FiseConstants.PARAM_CSTO_ATCION_SOLIC_R_F14B,formato14BBean.getCostoAtenSolicR());
		mapJRParams.put(FiseConstants.PARAM_CSTO_ATCION_SOLIC_P_F14B,formato14BBean.getCostoAtenSolicP());
		mapJRParams.put(FiseConstants.PARAM_CSTO_ATCION_SOLIC_L_F14B,formato14BBean.getCostoAtenSolicL());
		//6.2
		mapJRParams.put(FiseConstants.PARAM_CSTO_ATCION_CONSUL_R_F14B,formato14BBean.getCostoAtenConsR());
		mapJRParams.put(FiseConstants.PARAM_CSTO_ATCION_CONSUL_P_F14B,formato14BBean.getCostoAtenConsP());
		mapJRParams.put(FiseConstants.PARAM_CSTO_ATCION_CONSUL_L_F14B,formato14BBean.getCostoAtenConsL());
		//6.3
		mapJRParams.put(FiseConstants.PARAM_CSTO_TOTAL_ATCION_R_F14B,formato14BBean.getCostoTotalAtenR());
		mapJRParams.put(FiseConstants.PARAM_CSTO_TOTAL_ATCION_P_F14B,formato14BBean.getCostoTotalAtenP());
		mapJRParams.put(FiseConstants.PARAM_CSTO_TOTAL_ATCION_L_F14B,formato14BBean.getCostoTotalAtenL());
		//6.4
		mapJRParams.put(FiseConstants.PARAM_NRO_TOTAL_ATCION_R_F14B,formato14BBean.getNroTotalAtenR());
		mapJRParams.put(FiseConstants.PARAM_NRO_TOTAL_ATCION_P_F14B,formato14BBean.getNroTotalAtenP());
		mapJRParams.put(FiseConstants.PARAM_NRO_TOTAL_ATCION_L_F14B,formato14BBean.getNroTotalAtenL());
		//6.5
		mapJRParams.put(FiseConstants.PARAM_CSTO_UNIT_ATEN_R_F14B,formato14BBean.getCostoUnitAtenR());
		mapJRParams.put(FiseConstants.PARAM_CSTO_UNIT_ATEN_P_F14B,formato14BBean.getCostoUnitAtenP());
		mapJRParams.put(FiseConstants.PARAM_CSTO_UNIT_ATEN_L_F14B,formato14BBean.getCostoUnitAtenL());
		//7.1
		mapJRParams.put(FiseConstants.PARAM_CSTO_PSNAL_R_F14B,formato14BBean.getCostoPersonalR());
		mapJRParams.put(FiseConstants.PARAM_CSTO_PSNAL_P_F14B,formato14BBean.getCostoPersonalR());
		mapJRParams.put(FiseConstants.PARAM_CSTO_PSNAL_L_F14B,formato14BBean.getCostoPersonalR());
		//7.2
		mapJRParams.put(FiseConstants.PARAM_CPTCON_AGE_R_F14B,formato14BBean.getCapacAgentR());
		mapJRParams.put(FiseConstants.PARAM_CPTCON_AGE_P_F14B,formato14BBean.getCapacAgentP());
		mapJRParams.put(FiseConstants.PARAM_CPTCON_AGE_L_F14B,formato14BBean.getCapacAgentL());
		//7.3
		mapJRParams.put(FiseConstants.PARAM_UTLES_OFIC_R_F14B,formato14BBean.getUtilMatOficR());
		mapJRParams.put(FiseConstants.PARAM_UTLES_OFIC_P_F14B,formato14BBean.getUtilMatOficP());
		mapJRParams.put(FiseConstants.PARAM_UTLES_OFIC_L_F14B,formato14BBean.getUtilMatOficL());
		//7.4
		mapJRParams.put(FiseConstants.PARAM_CSTO_TOTAL_GTION_R_F14B,formato14BBean.getCostoTotalGestR());
		mapJRParams.put(FiseConstants.PARAM_CSTO_TOTAL_GTION_P_F14B,formato14BBean.getCostoTotalGestP());
		mapJRParams.put(FiseConstants.PARAM_CSTO_TOTAL_GTION_L_F14B,formato14BBean.getCostoTotalGestL());
		*/
		
		return mapJRParams;
	}	

	@Override
	@Transactional
	public List<FiseFormato14BDOb> listarFormato14BDObByFormato14BD(FiseFormato14BD formato14BD){
		return formato14BObsDao.listarFormato14BDObByFormato14BD(formato14BD); 
	}
	
	@Override
	@Transactional(propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
	public String modificarEnvioDefinitivoFormato14BC(String user,String terminal, 
			FiseFormato14BC fiseFormato14BC) throws Exception {		
		String valor = "0";
		try{
			fiseFormato14BC.setFechaEnvioDefinitivo(FechaUtil.obtenerFechaActual());
			fiseFormato14BC.setUsuarioActualizacion(user);
			fiseFormato14BC.setTerminalActualizacion(terminal);
			fiseFormato14BC.setFechaActualizacion(FechaUtil.obtenerFechaActual());
			formato14BCDao.modificarFormato14BC(fiseFormato14BC);
			valor = "1";
		} catch (Exception e) {
			valor = "0";
			logger.error("--error"+e.getMessage());
			e.printStackTrace();			
		}
		return valor;
	}

	@Override
	public FiseFormato14BD getCostoUnitarioByEmpAnioZona(String codEmpresa, Integer anio,Integer mes, Integer idZona, String etp) {
		// TODO Auto-generated method stub
		return formato14BDDao.getCostoUnitarioByEmpAnioZona(codEmpresa, anio,mes, idZona, etp);
	}

	@Override
	public List<FiseFormato14BD> getLstCostoUnitarioByEmpAnio(String codEmpresa, Integer anio,Integer mes, Integer idZona, String etp) {
		// TODO Auto-generated method stub
		return formato14BDDao.getLstCostoUnitarioByEmpAnio(codEmpresa, anio,mes, idZona, etp);
	}

	
	@Override
	@Transactional(propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
	public void eliminarObservaciones14B(List<FiseFormato14BDOb> listaObs) throws Exception{	
		for (FiseFormato14BDOb observacion : listaObs) {
			formato14BObsDao.eliminarFormato14BDOb(observacion);
		}	
	}
	

}
