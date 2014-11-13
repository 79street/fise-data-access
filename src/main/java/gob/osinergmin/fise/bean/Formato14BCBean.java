package gob.osinergmin.fise.bean;

import gob.osinergmin.fise.domain.AdmEmpresa;
import gob.osinergmin.fise.domain.FisePeriodoEnvio;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.List;
import java.util.Map;

public class Formato14BCBean  implements Serializable {

	private static final long serialVersionUID = 1L;
	
	private Map<Long,String> listaMes;
	private List<AdmEmpresa> listaEmpresas;
	private Map<String,String> listaEtapa;
	private List<FisePeriodoEnvio> listaPeriodoEnvio;
	
	private String codEmpresaB;
	private String mesDesde;
	private String anioDesde;
	private String mesHasta;
	private String anioHasta;
	private String etapaB;
	
	private boolean admin;

	private String codigoEmpresa;
	private Long anioPresent = 0L;
	private Long mesPresent = 0L;
	private Long anioInicioVigencia = 0L;
	private Long anioFinVigencia = 0L;
	
	private String periodoEnvio;
	private String flagPeriodoEjecucion;
	
	//RURAL
	private BigDecimal impValDesctoEdeR = new BigDecimal(0);
	private BigDecimal impValDesctoNoEdeR = new BigDecimal(0);
	private BigDecimal costoTotalImpR = new BigDecimal(0);
	private Long nroValesImpR = 0L;
	private BigDecimal costoUnitImpValesR = new BigDecimal(0);
	
	private BigDecimal costoTotalValDesctoR = new BigDecimal(0);
	private Long nroValesReptR = 0L;
	private BigDecimal costoUnitReptValesR = new BigDecimal(0);
	
	private BigDecimal costoTotalValOficR = new BigDecimal(0);
	private Long nroValesEntrR = 0L;
	private BigDecimal costoUnitEntrValesR = new BigDecimal(0);
	
	private BigDecimal costoEnvPadronR = new BigDecimal(0);
	private Long nroValesFisR = 0L;
	private BigDecimal costoUnitLiqR = new BigDecimal(0);
	
	private BigDecimal costoUnitValesDigitR = new BigDecimal(0);
	
	private BigDecimal costoAtenSolicR = new BigDecimal(0);
	private BigDecimal costoAtenConsR = new BigDecimal(0);
	private BigDecimal costoTotalAtenR = new BigDecimal(0);
	private Long nroTotalAtenR = 0L;
	private BigDecimal costoUnitAtenR = new BigDecimal(0);
	
	private BigDecimal costoPersonalR = new BigDecimal(0);
	private BigDecimal capacAgentR = new BigDecimal(0);
	private BigDecimal utilMatOficR = new BigDecimal(0);
	private BigDecimal costoTotalGestR = new BigDecimal(0);
	//PROVINCIA
	private BigDecimal impValDesctoEdeP = new BigDecimal(0);
	private BigDecimal impValDesctoNoEdeP = new BigDecimal(0);
	private BigDecimal costoTotalImpP = new BigDecimal(0);
	private Long nroValesImpP = 0L;
	private BigDecimal costoUnitImpValesP = new BigDecimal(0);
	
	private BigDecimal costoTotalValDesctoP = new BigDecimal(0);
	private Long nroValesReptP = 0L;
	private BigDecimal costoUnitReptValesP = new BigDecimal(0);
	
	private BigDecimal costoTotalValOficP = new BigDecimal(0);
	private Long nroValesEntrP = 0L;
	private BigDecimal costoUnitEntrValesP = new BigDecimal(0);
	
	private BigDecimal costoEnvPadronP = new BigDecimal(0);
	private Long nroValesFisP = 0L;
	private BigDecimal costoUnitLiqP = new BigDecimal(0);
	
	private BigDecimal costoUnitValesDigitP = new BigDecimal(0);
	
	private BigDecimal costoAtenSolicP = new BigDecimal(0);
	private BigDecimal costoAtenConsP = new BigDecimal(0);
	private BigDecimal costoTotalAtenP = new BigDecimal(0);
	private Long nroTotalAtenP = 0L;
	private BigDecimal costoUnitAtenP = new BigDecimal(0);
	
	private BigDecimal costoPersonalP = new BigDecimal(0);
	private BigDecimal capacAgentP = new BigDecimal(0);
	private BigDecimal utilMatOficP = new BigDecimal(0);
	private BigDecimal costoTotalGestP = new BigDecimal(0);
	//LIMA
	private BigDecimal impValDesctoEdeL = new BigDecimal(0);
	private BigDecimal impValDesctoNoEdeL = new BigDecimal(0);
	private BigDecimal costoTotalImpL = new BigDecimal(0);
	private Long nroValesImpL = 0L;
	private BigDecimal costoUnitImpValesL = new BigDecimal(0);
	
	private BigDecimal costoTotalValDesctoL = new BigDecimal(0);
	private Long nroValesReptL = 0L;
	private BigDecimal costoUnitReptValesL = new BigDecimal(0);
	
	private BigDecimal costoTotalValOficL = new BigDecimal(0);
	private Long nroValesEntrL = 0L;
	private BigDecimal costoUnitEntrValesL = new BigDecimal(0);
	
	private BigDecimal costoEnvPadronL = new BigDecimal(0);
	private Long nroValesFisL = 0L;
	private BigDecimal costoUnitLiqL = new BigDecimal(0);
	
	private BigDecimal costoUnitValesDigitL = new BigDecimal(0);
	
	private BigDecimal costoAtenSolicL = new BigDecimal(0);
	private BigDecimal costoAtenConsL = new BigDecimal(0);
	private BigDecimal costoTotalAtenL = new BigDecimal(0);
	private Long nroTotalAtenL = 0L;
	private BigDecimal costoUnitAtenL = new BigDecimal(0);
	
	private BigDecimal costoPersonalL = new BigDecimal(0);
	private BigDecimal capacAgentL = new BigDecimal(0);
	private BigDecimal utilMatOficL = new BigDecimal(0);
	private BigDecimal costoTotalGestL = new BigDecimal(0);
	//
	
	private String usuario;
	private String terminal;
	private String nombreArchivo;
	private String tipoArchivo;
	
	private String descEmpresa;
	private String descMesPresentacion;
	private String descMesEjecucion;
	
	private String etapa;

	private String descGrupoInformacion;
	private String descEstado;
	
	private String  codEdelnor;
	private String  codLuzSur;
	
	public Map<Long, String> getListaMes() {
		return listaMes;
	}

	public void setListaMes(Map<Long, String> listaMes) {
		this.listaMes = listaMes;
	}

	public List<AdmEmpresa> getListaEmpresas() {
		return listaEmpresas;
	}

	public void setListaEmpresas(List<AdmEmpresa> listaEmpresas) {
		this.listaEmpresas = listaEmpresas;
	}

	public Map<String, String> getListaEtapa() {
		return listaEtapa;
	}

	public void setListaEtapa(Map<String, String> listaEtapa) {
		this.listaEtapa = listaEtapa;
	}

	public List<FisePeriodoEnvio> getListaPeriodoEnvio() {
		return listaPeriodoEnvio;
	}

	public void setListaPeriodoEnvio(List<FisePeriodoEnvio> listaPeriodoEnvio) {
		this.listaPeriodoEnvio = listaPeriodoEnvio;
	}

	public String getCodEmpresaB() {
		return codEmpresaB;
	}

	public void setCodEmpresaB(String codEmpresaB) {
		this.codEmpresaB = codEmpresaB;
	}

	public String getMesDesde() {
		return mesDesde;
	}

	public void setMesDesde(String mesDesde) {
		this.mesDesde = mesDesde;
	}

	public String getAnioDesde() {
		return anioDesde;
	}

	public void setAnioDesde(String anioDesde) {
		this.anioDesde = anioDesde;
	}

	public String getMesHasta() {
		return mesHasta;
	}

	public void setMesHasta(String mesHasta) {
		this.mesHasta = mesHasta;
	}

	public String getAnioHasta() {
		return anioHasta;
	}

	public void setAnioHasta(String anioHasta) {
		this.anioHasta = anioHasta;
	}

	public String getEtapaB() {
		return etapaB;
	}

	public void setEtapaB(String etapaB) {
		this.etapaB = etapaB;
	}

	public boolean isAdmin() {
		return admin;
	}

	public void setAdmin(boolean admin) {
		this.admin = admin;
	}

	public String getCodigoEmpresa() {
		return codigoEmpresa;
	}

	public void setCodigoEmpresa(String codigoEmpresa) {
		this.codigoEmpresa = codigoEmpresa;
	}

	public Long getAnioPresent() {
		return anioPresent;
	}

	public void setAnioPresent(Long anioPresent) {
		this.anioPresent = anioPresent;
	}

	public Long getMesPresent() {
		return mesPresent;
	}

	public void setMesPresent(Long mesPresent) {
		this.mesPresent = mesPresent;
	}

	public Long getAnioInicioVigencia() {
		return anioInicioVigencia;
	}

	public void setAnioInicioVigencia(Long anioInicioVigencia) {
		this.anioInicioVigencia = anioInicioVigencia;
	}

	public Long getAnioFinVigencia() {
		return anioFinVigencia;
	}

	public void setAnioFinVigencia(Long anioFinVigencia) {
		this.anioFinVigencia = anioFinVigencia;
	}

	public String getPeriodoEnvio() {
		return periodoEnvio;
	}

	public void setPeriodoEnvio(String periodoEnvio) {
		this.periodoEnvio = periodoEnvio;
	}

	public String getFlagPeriodoEjecucion() {
		return flagPeriodoEjecucion;
	}

	public void setFlagPeriodoEjecucion(String flagPeriodoEjecucion) {
		this.flagPeriodoEjecucion = flagPeriodoEjecucion;
	}

	public BigDecimal getImpValDesctoEdeR() {
		return impValDesctoEdeR;
	}

	public void setImpValDesctoEdeR(BigDecimal impValDesctoEdeR) {
		this.impValDesctoEdeR = impValDesctoEdeR;
	}

	public BigDecimal getImpValDesctoNoEdeR() {
		return impValDesctoNoEdeR;
	}

	public void setImpValDesctoNoEdeR(BigDecimal impValDesctoNoEdeR) {
		this.impValDesctoNoEdeR = impValDesctoNoEdeR;
	}

	public BigDecimal getCostoTotalImpR() {
		return costoTotalImpR;
	}

	public void setCostoTotalImpR(BigDecimal costoTotalImpR) {
		this.costoTotalImpR = costoTotalImpR;
	}

	public Long getNroValesImpR() {
		return nroValesImpR;
	}

	public void setNroValesImpR(Long nroValesImpR) {
		this.nroValesImpR = nroValesImpR;
	}

	public BigDecimal getCostoUnitImpValesR() {
		return costoUnitImpValesR;
	}

	public void setCostoUnitImpValesR(BigDecimal costoUnitImpValesR) {
		this.costoUnitImpValesR = costoUnitImpValesR;
	}

	public BigDecimal getCostoTotalValDesctoR() {
		return costoTotalValDesctoR;
	}

	public void setCostoTotalValDesctoR(BigDecimal costoTotalValDesctoR) {
		this.costoTotalValDesctoR = costoTotalValDesctoR;
	}

	public Long getNroValesReptR() {
		return nroValesReptR;
	}

	public void setNroValesReptR(Long nroValesReptR) {
		this.nroValesReptR = nroValesReptR;
	}

	public BigDecimal getCostoUnitReptValesR() {
		return costoUnitReptValesR;
	}

	public void setCostoUnitReptValesR(BigDecimal costoUnitReptValesR) {
		this.costoUnitReptValesR = costoUnitReptValesR;
	}

	public BigDecimal getCostoTotalValOficR() {
		return costoTotalValOficR;
	}

	public void setCostoTotalValOficR(BigDecimal costoTotalValOficR) {
		this.costoTotalValOficR = costoTotalValOficR;
	}

	public Long getNroValesEntrR() {
		return nroValesEntrR;
	}

	public void setNroValesEntrR(Long nroValesEntrR) {
		this.nroValesEntrR = nroValesEntrR;
	}

	public BigDecimal getCostoUnitEntrValesR() {
		return costoUnitEntrValesR;
	}

	public void setCostoUnitEntrValesR(BigDecimal costoUnitEntrValesR) {
		this.costoUnitEntrValesR = costoUnitEntrValesR;
	}

	public BigDecimal getCostoEnvPadronR() {
		return costoEnvPadronR;
	}

	public void setCostoEnvPadronR(BigDecimal costoEnvPadronR) {
		this.costoEnvPadronR = costoEnvPadronR;
	}

	public Long getNroValesFisR() {
		return nroValesFisR;
	}

	public void setNroValesFisR(Long nroValesFisR) {
		this.nroValesFisR = nroValesFisR;
	}

	public BigDecimal getCostoUnitLiqR() {
		return costoUnitLiqR;
	}

	public void setCostoUnitLiqR(BigDecimal costoUnitLiqR) {
		this.costoUnitLiqR = costoUnitLiqR;
	}

	public BigDecimal getCostoUnitValesDigitR() {
		return costoUnitValesDigitR;
	}

	public void setCostoUnitValesDigitR(BigDecimal costoUnitValesDigitR) {
		this.costoUnitValesDigitR = costoUnitValesDigitR;
	}

	public BigDecimal getCostoAtenSolicR() {
		return costoAtenSolicR;
	}

	public void setCostoAtenSolicR(BigDecimal costoAtenSolicR) {
		this.costoAtenSolicR = costoAtenSolicR;
	}

	public BigDecimal getCostoAtenConsR() {
		return costoAtenConsR;
	}

	public void setCostoAtenConsR(BigDecimal costoAtenConsR) {
		this.costoAtenConsR = costoAtenConsR;
	}

	public BigDecimal getCostoTotalAtenR() {
		return costoTotalAtenR;
	}

	public void setCostoTotalAtenR(BigDecimal costoTotalAtenR) {
		this.costoTotalAtenR = costoTotalAtenR;
	}

	public Long getNroTotalAtenR() {
		return nroTotalAtenR;
	}

	public void setNroTotalAtenR(Long nroTotalAtenR) {
		this.nroTotalAtenR = nroTotalAtenR;
	}

	public BigDecimal getCostoUnitAtenR() {
		return costoUnitAtenR;
	}

	public void setCostoUnitAtenR(BigDecimal costoUnitAtenR) {
		this.costoUnitAtenR = costoUnitAtenR;
	}

	public BigDecimal getCostoPersonalR() {
		return costoPersonalR;
	}

	public void setCostoPersonalR(BigDecimal costoPersonalR) {
		this.costoPersonalR = costoPersonalR;
	}

	public BigDecimal getCapacAgentR() {
		return capacAgentR;
	}

	public void setCapacAgentR(BigDecimal capacAgentR) {
		this.capacAgentR = capacAgentR;
	}

	public BigDecimal getUtilMatOficR() {
		return utilMatOficR;
	}

	public void setUtilMatOficR(BigDecimal utilMatOficR) {
		this.utilMatOficR = utilMatOficR;
	}

	public BigDecimal getCostoTotalGestR() {
		return costoTotalGestR;
	}

	public void setCostoTotalGestR(BigDecimal costoTotalGestR) {
		this.costoTotalGestR = costoTotalGestR;
	}

	public BigDecimal getImpValDesctoEdeP() {
		return impValDesctoEdeP;
	}

	public void setImpValDesctoEdeP(BigDecimal impValDesctoEdeP) {
		this.impValDesctoEdeP = impValDesctoEdeP;
	}

	public BigDecimal getImpValDesctoNoEdeP() {
		return impValDesctoNoEdeP;
	}

	public void setImpValDesctoNoEdeP(BigDecimal impValDesctoNoEdeP) {
		this.impValDesctoNoEdeP = impValDesctoNoEdeP;
	}

	public BigDecimal getCostoTotalImpP() {
		return costoTotalImpP;
	}

	public void setCostoTotalImpP(BigDecimal costoTotalImpP) {
		this.costoTotalImpP = costoTotalImpP;
	}

	public Long getNroValesImpP() {
		return nroValesImpP;
	}

	public void setNroValesImpP(Long nroValesImpP) {
		this.nroValesImpP = nroValesImpP;
	}

	public BigDecimal getCostoUnitImpValesP() {
		return costoUnitImpValesP;
	}

	public void setCostoUnitImpValesP(BigDecimal costoUnitImpValesP) {
		this.costoUnitImpValesP = costoUnitImpValesP;
	}

	public BigDecimal getCostoTotalValDesctoP() {
		return costoTotalValDesctoP;
	}

	public void setCostoTotalValDesctoP(BigDecimal costoTotalValDesctoP) {
		this.costoTotalValDesctoP = costoTotalValDesctoP;
	}

	public Long getNroValesReptP() {
		return nroValesReptP;
	}

	public void setNroValesReptP(Long nroValesReptP) {
		this.nroValesReptP = nroValesReptP;
	}

	public BigDecimal getCostoUnitReptValesP() {
		return costoUnitReptValesP;
	}

	public void setCostoUnitReptValesP(BigDecimal costoUnitReptValesP) {
		this.costoUnitReptValesP = costoUnitReptValesP;
	}

	public BigDecimal getCostoTotalValOficP() {
		return costoTotalValOficP;
	}

	public void setCostoTotalValOficP(BigDecimal costoTotalValOficP) {
		this.costoTotalValOficP = costoTotalValOficP;
	}

	public Long getNroValesEntrP() {
		return nroValesEntrP;
	}

	public void setNroValesEntrP(Long nroValesEntrP) {
		this.nroValesEntrP = nroValesEntrP;
	}

	public BigDecimal getCostoUnitEntrValesP() {
		return costoUnitEntrValesP;
	}

	public void setCostoUnitEntrValesP(BigDecimal costoUnitEntrValesP) {
		this.costoUnitEntrValesP = costoUnitEntrValesP;
	}

	public BigDecimal getCostoEnvPadronP() {
		return costoEnvPadronP;
	}

	public void setCostoEnvPadronP(BigDecimal costoEnvPadronP) {
		this.costoEnvPadronP = costoEnvPadronP;
	}

	public Long getNroValesFisP() {
		return nroValesFisP;
	}

	public void setNroValesFisP(Long nroValesFisP) {
		this.nroValesFisP = nroValesFisP;
	}

	public BigDecimal getCostoUnitLiqP() {
		return costoUnitLiqP;
	}

	public void setCostoUnitLiqP(BigDecimal costoUnitLiqP) {
		this.costoUnitLiqP = costoUnitLiqP;
	}

	public BigDecimal getCostoUnitValesDigitP() {
		return costoUnitValesDigitP;
	}

	public void setCostoUnitValesDigitP(BigDecimal costoUnitValesDigitP) {
		this.costoUnitValesDigitP = costoUnitValesDigitP;
	}

	public BigDecimal getCostoAtenSolicP() {
		return costoAtenSolicP;
	}

	public void setCostoAtenSolicP(BigDecimal costoAtenSolicP) {
		this.costoAtenSolicP = costoAtenSolicP;
	}

	public BigDecimal getCostoAtenConsP() {
		return costoAtenConsP;
	}

	public void setCostoAtenConsP(BigDecimal costoAtenConsP) {
		this.costoAtenConsP = costoAtenConsP;
	}

	public BigDecimal getCostoTotalAtenP() {
		return costoTotalAtenP;
	}

	public void setCostoTotalAtenP(BigDecimal costoTotalAtenP) {
		this.costoTotalAtenP = costoTotalAtenP;
	}

	public Long getNroTotalAtenP() {
		return nroTotalAtenP;
	}

	public void setNroTotalAtenP(Long nroTotalAtenP) {
		this.nroTotalAtenP = nroTotalAtenP;
	}

	public BigDecimal getCostoUnitAtenP() {
		return costoUnitAtenP;
	}

	public void setCostoUnitAtenP(BigDecimal costoUnitAtenP) {
		this.costoUnitAtenP = costoUnitAtenP;
	}

	public BigDecimal getCostoPersonalP() {
		return costoPersonalP;
	}

	public void setCostoPersonalP(BigDecimal costoPersonalP) {
		this.costoPersonalP = costoPersonalP;
	}

	public BigDecimal getCapacAgentP() {
		return capacAgentP;
	}

	public void setCapacAgentP(BigDecimal capacAgentP) {
		this.capacAgentP = capacAgentP;
	}

	public BigDecimal getUtilMatOficP() {
		return utilMatOficP;
	}

	public void setUtilMatOficP(BigDecimal utilMatOficP) {
		this.utilMatOficP = utilMatOficP;
	}

	public BigDecimal getCostoTotalGestP() {
		return costoTotalGestP;
	}

	public void setCostoTotalGestP(BigDecimal costoTotalGestP) {
		this.costoTotalGestP = costoTotalGestP;
	}

	public BigDecimal getImpValDesctoEdeL() {
		return impValDesctoEdeL;
	}

	public void setImpValDesctoEdeL(BigDecimal impValDesctoEdeL) {
		this.impValDesctoEdeL = impValDesctoEdeL;
	}

	public BigDecimal getImpValDesctoNoEdeL() {
		return impValDesctoNoEdeL;
	}

	public void setImpValDesctoNoEdeL(BigDecimal impValDesctoNoEdeL) {
		this.impValDesctoNoEdeL = impValDesctoNoEdeL;
	}

	public BigDecimal getCostoTotalImpL() {
		return costoTotalImpL;
	}

	public void setCostoTotalImpL(BigDecimal costoTotalImpL) {
		this.costoTotalImpL = costoTotalImpL;
	}

	public Long getNroValesImpL() {
		return nroValesImpL;
	}

	public void setNroValesImpL(Long nroValesImpL) {
		this.nroValesImpL = nroValesImpL;
	}

	public BigDecimal getCostoUnitImpValesL() {
		return costoUnitImpValesL;
	}

	public void setCostoUnitImpValesL(BigDecimal costoUnitImpValesL) {
		this.costoUnitImpValesL = costoUnitImpValesL;
	}

	public BigDecimal getCostoTotalValDesctoL() {
		return costoTotalValDesctoL;
	}

	public void setCostoTotalValDesctoL(BigDecimal costoTotalValDesctoL) {
		this.costoTotalValDesctoL = costoTotalValDesctoL;
	}

	public Long getNroValesReptL() {
		return nroValesReptL;
	}

	public void setNroValesReptL(Long nroValesReptL) {
		this.nroValesReptL = nroValesReptL;
	}

	public BigDecimal getCostoUnitReptValesL() {
		return costoUnitReptValesL;
	}

	public void setCostoUnitReptValesL(BigDecimal costoUnitReptValesL) {
		this.costoUnitReptValesL = costoUnitReptValesL;
	}

	public BigDecimal getCostoTotalValOficL() {
		return costoTotalValOficL;
	}

	public void setCostoTotalValOficL(BigDecimal costoTotalValOficL) {
		this.costoTotalValOficL = costoTotalValOficL;
	}

	public Long getNroValesEntrL() {
		return nroValesEntrL;
	}

	public void setNroValesEntrL(Long nroValesEntrL) {
		this.nroValesEntrL = nroValesEntrL;
	}

	public BigDecimal getCostoUnitEntrValesL() {
		return costoUnitEntrValesL;
	}

	public void setCostoUnitEntrValesL(BigDecimal costoUnitEntrValesL) {
		this.costoUnitEntrValesL = costoUnitEntrValesL;
	}

	public BigDecimal getCostoEnvPadronL() {
		return costoEnvPadronL;
	}

	public void setCostoEnvPadronL(BigDecimal costoEnvPadronL) {
		this.costoEnvPadronL = costoEnvPadronL;
	}

	public Long getNroValesFisL() {
		return nroValesFisL;
	}

	public void setNroValesFisL(Long nroValesFisL) {
		this.nroValesFisL = nroValesFisL;
	}

	public BigDecimal getCostoUnitLiqL() {
		return costoUnitLiqL;
	}

	public void setCostoUnitLiqL(BigDecimal costoUnitLiqL) {
		this.costoUnitLiqL = costoUnitLiqL;
	}

	public BigDecimal getCostoUnitValesDigitL() {
		return costoUnitValesDigitL;
	}

	public void setCostoUnitValesDigitL(BigDecimal costoUnitValesDigitL) {
		this.costoUnitValesDigitL = costoUnitValesDigitL;
	}

	public BigDecimal getCostoAtenSolicL() {
		return costoAtenSolicL;
	}

	public void setCostoAtenSolicL(BigDecimal costoAtenSolicL) {
		this.costoAtenSolicL = costoAtenSolicL;
	}

	public BigDecimal getCostoAtenConsL() {
		return costoAtenConsL;
	}

	public void setCostoAtenConsL(BigDecimal costoAtenConsL) {
		this.costoAtenConsL = costoAtenConsL;
	}

	public BigDecimal getCostoTotalAtenL() {
		return costoTotalAtenL;
	}

	public void setCostoTotalAtenL(BigDecimal costoTotalAtenL) {
		this.costoTotalAtenL = costoTotalAtenL;
	}

	public Long getNroTotalAtenL() {
		return nroTotalAtenL;
	}

	public void setNroTotalAtenL(Long nroTotalAtenL) {
		this.nroTotalAtenL = nroTotalAtenL;
	}

	public BigDecimal getCostoUnitAtenL() {
		return costoUnitAtenL;
	}

	public void setCostoUnitAtenL(BigDecimal costoUnitAtenL) {
		this.costoUnitAtenL = costoUnitAtenL;
	}

	public BigDecimal getCostoPersonalL() {
		return costoPersonalL;
	}

	public void setCostoPersonalL(BigDecimal costoPersonalL) {
		this.costoPersonalL = costoPersonalL;
	}

	public BigDecimal getCapacAgentL() {
		return capacAgentL;
	}

	public void setCapacAgentL(BigDecimal capacAgentL) {
		this.capacAgentL = capacAgentL;
	}

	public BigDecimal getUtilMatOficL() {
		return utilMatOficL;
	}

	public void setUtilMatOficL(BigDecimal utilMatOficL) {
		this.utilMatOficL = utilMatOficL;
	}

	public BigDecimal getCostoTotalGestL() {
		return costoTotalGestL;
	}

	public void setCostoTotalGestL(BigDecimal costoTotalGestL) {
		this.costoTotalGestL = costoTotalGestL;
	}

	public String getUsuario() {
		return usuario;
	}

	public void setUsuario(String usuario) {
		this.usuario = usuario;
	}

	public String getTerminal() {
		return terminal;
	}

	public void setTerminal(String terminal) {
		this.terminal = terminal;
	}

	public String getNombreArchivo() {
		return nombreArchivo;
	}

	public void setNombreArchivo(String nombreArchivo) {
		this.nombreArchivo = nombreArchivo;
	}

	public String getTipoArchivo() {
		return tipoArchivo;
	}

	public void setTipoArchivo(String tipoArchivo) {
		this.tipoArchivo = tipoArchivo;
	}

	public String getDescEmpresa() {
		return descEmpresa;
	}

	public void setDescEmpresa(String descEmpresa) {
		this.descEmpresa = descEmpresa;
	}

	public String getDescMesPresentacion() {
		return descMesPresentacion;
	}

	public void setDescMesPresentacion(String descMesPresentacion) {
		this.descMesPresentacion = descMesPresentacion;
	}

	public String getDescMesEjecucion() {
		return descMesEjecucion;
	}

	public void setDescMesEjecucion(String descMesEjecucion) {
		this.descMesEjecucion = descMesEjecucion;
	}

	public String getEtapa() {
		return etapa;
	}

	public void setEtapa(String etapa) {
		this.etapa = etapa;
	}

	public String getDescGrupoInformacion() {
		return descGrupoInformacion;
	}

	public void setDescGrupoInformacion(String descGrupoInformacion) {
		this.descGrupoInformacion = descGrupoInformacion;
	}

	public String getDescEstado() {
		return descEstado;
	}

	public void setDescEstado(String descEstado) {
		this.descEstado = descEstado;
	}

	public String getCodEdelnor() {
		return codEdelnor;
	}

	public void setCodEdelnor(String codEdelnor) {
		this.codEdelnor = codEdelnor;
	}

	public String getCodLuzSur() {
		return codLuzSur;
	}

	public void setCodLuzSur(String codLuzSur) {
		this.codLuzSur = codLuzSur;
	}

	
	
}