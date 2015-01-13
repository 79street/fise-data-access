package gob.osinergmin.fise.bean;

import gob.osinergmin.fise.domain.AdmEmpresa;
import gob.osinergmin.fise.domain.FisePeriodoEnvio;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.List;
import java.util.Map;

public class Formato14ACBean implements Serializable {

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
	
	private String anioInicioVigenciaHidden;
	private String anioFinVigenciaHidden;
	
	//private long idZonaBnfcio = 0;

	public String getAnioInicioVigenciaHidden() {
		return anioInicioVigenciaHidden;
	}

	public void setAnioInicioVigenciaHidden(String anioInicioVigenciaHidden) {
		this.anioInicioVigenciaHidden = anioInicioVigenciaHidden;
	}

	public String getAnioFinVigenciaHidden() {
		return anioFinVigenciaHidden;
	}

	public void setAnioFinVigenciaHidden(String anioFinVigenciaHidden) {
		this.anioFinVigenciaHidden = anioFinVigenciaHidden;
	}

	private BigDecimal sumEmpadDifusionR = new BigDecimal(0); // campo no existente en la tabla
	
	private BigDecimal imprEsqInvitR = new BigDecimal(0);
	private BigDecimal imprDeclaJuradaR = new BigDecimal(0);
	private BigDecimal imprFichaVerifR = new BigDecimal(0);
	private BigDecimal repartoEsqInvitR = new BigDecimal(0);
	private BigDecimal verifInfoR = new BigDecimal(0);
	private BigDecimal elabArchivoBenefR = new BigDecimal(0);
	private BigDecimal digitFichaBenefR = new BigDecimal(0);
	private BigDecimal totalEmpadR = new BigDecimal(0);// suma de empadronamiento
	private BigDecimal imprVolantesR = new BigDecimal(0);
	private BigDecimal imprAfichesR = new BigDecimal(0);
	private BigDecimal repFolletosR = new BigDecimal(0);
	private BigDecimal spotPublTvR = new BigDecimal(0);
	private BigDecimal spotPublRadioR = new BigDecimal(0);
	private BigDecimal totalDifIniProgR = new BigDecimal(0);
	private Long nroBenefEmpadR = 0L;
	private BigDecimal costoUnitEmpadR = new BigDecimal(0);
	private BigDecimal promConvAgentR = new BigDecimal(0);
	private BigDecimal regConvAgentR = new BigDecimal(0);
	private BigDecimal impEntrBandR = new BigDecimal(0);
	private BigDecimal totalCostoAgentR = new BigDecimal(0);
	private Long nroAgentR = 0L;
	private BigDecimal costoUnitAgentR = new BigDecimal(0);
	
	//-------------------------
	
	private BigDecimal sumEmpadDifusionP = new BigDecimal(0); // campo no existente en la tabla
	
	private BigDecimal imprEsqInvitP = new BigDecimal(0);
	private BigDecimal imprDeclaJuradaP = new BigDecimal(0);
	private BigDecimal imprFichaVerifP = new BigDecimal(0);
	private BigDecimal repartoEsqInvitP = new BigDecimal(0);
	private BigDecimal verifInfoP = new BigDecimal(0);
	private BigDecimal elabArchivoBenefP = new BigDecimal(0);
	private BigDecimal digitFichaBenefP = new BigDecimal(0);
	private BigDecimal totalEmpadP = new BigDecimal(0);// suma de empadronamiento
	private BigDecimal imprVolantesP = new BigDecimal(0);
	private BigDecimal imprAfichesP = new BigDecimal(0);
	private BigDecimal repFolletosP = new BigDecimal(0);
	private BigDecimal spotPublTvP = new BigDecimal(0);
	private BigDecimal spotPublRadioP = new BigDecimal(0);
	private BigDecimal totalDifIniProgP = new BigDecimal(0);
	private Long nroBenefEmpadP = 0L;
	private BigDecimal costoUnitEmpadP = new BigDecimal(0);
	private BigDecimal promConvAgentP = new BigDecimal(0);
	private BigDecimal regConvAgentP = new BigDecimal(0);
	private BigDecimal impEntrBandP = new BigDecimal(0);
	private BigDecimal totalCostoAgentP = new BigDecimal(0);
	private Long nroAgentP = 0L;
	private BigDecimal costoUnitAgentP = new BigDecimal(0);
	
	//--------------------
	private BigDecimal sumEmpadDifusionL = new BigDecimal(0); // campo no existente en la tabla
	
	private BigDecimal imprEsqInvitL = new BigDecimal(0);
	private BigDecimal imprDeclaJuradaL = new BigDecimal(0);
	private BigDecimal imprFichaVerifL = new BigDecimal(0);
	private BigDecimal repartoEsqInvitL = new BigDecimal(0);
	private BigDecimal verifInfoL = new BigDecimal(0);
	private BigDecimal elabArchivoBenefL = new BigDecimal(0);
	private BigDecimal digitFichaBenefL = new BigDecimal(0);
	private BigDecimal totalEmpadL = new BigDecimal(0);// suma de empadronamiento
	private BigDecimal imprVolantesL = new BigDecimal(0);
	private BigDecimal imprAfichesL = new BigDecimal(0);
	private BigDecimal repFolletosL = new BigDecimal(0);
	private BigDecimal spotPublTvL = new BigDecimal(0);
	private BigDecimal spotPublRadioL = new BigDecimal(0);
	private BigDecimal totalDifIniProgL = new BigDecimal(0);
	private Long nroBenefEmpadL = 0L;
	private BigDecimal costoUnitEmpadL = new BigDecimal(0);
	private BigDecimal promConvAgentL = new BigDecimal(0);
	private BigDecimal regConvAgentL = new BigDecimal(0);
	private BigDecimal impEntrBandL = new BigDecimal(0);
	private BigDecimal totalCostoAgentL = new BigDecimal(0);
	private Long nroAgentL = 0L;
	private BigDecimal costoUnitAgentL = new BigDecimal(0);
	
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
	
	public Formato14ACBean(){
		
	}

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

	public long getAnioPresent() {
		return anioPresent;
	}

	public void setAnioPresent(long anioPresent) {
		this.anioPresent = anioPresent;
	}

	public long getMesPresent() {
		return mesPresent;
	}

	public void setMesPresent(long mesPresent) {
		this.mesPresent = mesPresent;
	}

	public long getAnioInicioVigencia() {
		return anioInicioVigencia;
	}

	public void setAnioInicioVigencia(long anioInicioVigencia) {
		this.anioInicioVigencia = anioInicioVigencia;
	}

	public long getAnioFinVigencia() {
		return anioFinVigencia;
	}

	public void setAnioFinVigencia(long anioFinVigencia) {
		this.anioFinVigencia = anioFinVigencia;
	}

	public BigDecimal getSumEmpadDifusionR() {
		return sumEmpadDifusionR;
	}

	public void setSumEmpadDifusionR(BigDecimal sumEmpadDifusionR) {
		this.sumEmpadDifusionR = sumEmpadDifusionR;
	}

	public BigDecimal getImprEsqInvitR() {
		return imprEsqInvitR;
	}

	public void setImprEsqInvitR(BigDecimal imprEsqInvitR) {
		this.imprEsqInvitR = imprEsqInvitR;
	}

	public BigDecimal getImprDeclaJuradaR() {
		return imprDeclaJuradaR;
	}

	public void setImprDeclaJuradaR(BigDecimal imprDeclaJuradaR) {
		this.imprDeclaJuradaR = imprDeclaJuradaR;
	}

	public BigDecimal getImprFichaVerifR() {
		return imprFichaVerifR;
	}

	public void setImprFichaVerifR(BigDecimal imprFichaVerifR) {
		this.imprFichaVerifR = imprFichaVerifR;
	}

	public BigDecimal getRepartoEsqInvitR() {
		return repartoEsqInvitR;
	}

	public void setRepartoEsqInvitR(BigDecimal repartoEsqInvitR) {
		this.repartoEsqInvitR = repartoEsqInvitR;
	}

	public BigDecimal getVerifInfoR() {
		return verifInfoR;
	}

	public void setVerifInfoR(BigDecimal verifInfoR) {
		this.verifInfoR = verifInfoR;
	}

	public BigDecimal getElabArchivoBenefR() {
		return elabArchivoBenefR;
	}

	public void setElabArchivoBenefR(BigDecimal elabArchivoBenefR) {
		this.elabArchivoBenefR = elabArchivoBenefR;
	}

	public BigDecimal getDigitFichaBenefR() {
		return digitFichaBenefR;
	}

	public void setDigitFichaBenefR(BigDecimal digitFichaBenefR) {
		this.digitFichaBenefR = digitFichaBenefR;
	}

	public BigDecimal getTotalEmpadR() {
		return totalEmpadR;
	}

	public void setTotalEmpadR(BigDecimal totalEmpadR) {
		this.totalEmpadR = totalEmpadR;
	}

	public BigDecimal getImprVolantesR() {
		return imprVolantesR;
	}

	public void setImprVolantesR(BigDecimal imprVolantesR) {
		this.imprVolantesR = imprVolantesR;
	}

	public BigDecimal getImprAfichesR() {
		return imprAfichesR;
	}

	public void setImprAfichesR(BigDecimal imprAfichesR) {
		this.imprAfichesR = imprAfichesR;
	}

	public BigDecimal getRepFolletosR() {
		return repFolletosR;
	}

	public void setRepFolletosR(BigDecimal repFolletosR) {
		this.repFolletosR = repFolletosR;
	}

	public BigDecimal getSpotPublTvR() {
		return spotPublTvR;
	}

	public void setSpotPublTvR(BigDecimal spotPublTvR) {
		this.spotPublTvR = spotPublTvR;
	}

	public BigDecimal getSpotPublRadioR() {
		return spotPublRadioR;
	}

	public void setSpotPublRadioR(BigDecimal spotPublRadioR) {
		this.spotPublRadioR = spotPublRadioR;
	}

	public BigDecimal getTotalDifIniProgR() {
		return totalDifIniProgR;
	}

	public void setTotalDifIniProgR(BigDecimal totalDifIniProgR) {
		this.totalDifIniProgR = totalDifIniProgR;
	}

	public long getNroBenefEmpadR() {
		return nroBenefEmpadR;
	}

	public void setNroBenefEmpadR(long nroBenefEmpadR) {
		this.nroBenefEmpadR = nroBenefEmpadR;
	}

	public BigDecimal getCostoUnitEmpadR() {
		return costoUnitEmpadR;
	}

	public void setCostoUnitEmpadR(BigDecimal costoUnitEmpadR) {
		this.costoUnitEmpadR = costoUnitEmpadR;
	}

	public BigDecimal getPromConvAgentR() {
		return promConvAgentR;
	}

	public void setPromConvAgentR(BigDecimal promConvAgentR) {
		this.promConvAgentR = promConvAgentR;
	}

	public BigDecimal getRegConvAgentR() {
		return regConvAgentR;
	}

	public void setRegConvAgentR(BigDecimal regConvAgentR) {
		this.regConvAgentR = regConvAgentR;
	}

	public BigDecimal getImpEntrBandR() {
		return impEntrBandR;
	}

	public void setImpEntrBandR(BigDecimal impEntrBandR) {
		this.impEntrBandR = impEntrBandR;
	}

	public BigDecimal getTotalCostoAgentR() {
		return totalCostoAgentR;
	}

	public void setTotalCostoAgentR(BigDecimal totalCostoAgentR) {
		this.totalCostoAgentR = totalCostoAgentR;
	}

	public long getNroAgentR() {
		return nroAgentR;
	}

	public void setNroAgentR(long nroAgentR) {
		this.nroAgentR = nroAgentR;
	}

	public BigDecimal getCostoUnitAgentR() {
		return costoUnitAgentR;
	}

	public void setCostoUnitAgentR(BigDecimal costoUnitAgentR) {
		this.costoUnitAgentR = costoUnitAgentR;
	}

	public BigDecimal getSumEmpadDifusionP() {
		return sumEmpadDifusionP;
	}

	public void setSumEmpadDifusionP(BigDecimal sumEmpadDifusionP) {
		this.sumEmpadDifusionP = sumEmpadDifusionP;
	}

	public BigDecimal getImprEsqInvitP() {
		return imprEsqInvitP;
	}

	public void setImprEsqInvitP(BigDecimal imprEsqInvitP) {
		this.imprEsqInvitP = imprEsqInvitP;
	}

	public BigDecimal getImprDeclaJuradaP() {
		return imprDeclaJuradaP;
	}

	public void setImprDeclaJuradaP(BigDecimal imprDeclaJuradaP) {
		this.imprDeclaJuradaP = imprDeclaJuradaP;
	}

	public BigDecimal getImprFichaVerifP() {
		return imprFichaVerifP;
	}

	public void setImprFichaVerifP(BigDecimal imprFichaVerifP) {
		this.imprFichaVerifP = imprFichaVerifP;
	}

	public BigDecimal getRepartoEsqInvitP() {
		return repartoEsqInvitP;
	}

	public void setRepartoEsqInvitP(BigDecimal repartoEsqInvitP) {
		this.repartoEsqInvitP = repartoEsqInvitP;
	}

	public BigDecimal getVerifInfoP() {
		return verifInfoP;
	}

	public void setVerifInfoP(BigDecimal verifInfoP) {
		this.verifInfoP = verifInfoP;
	}

	public BigDecimal getElabArchivoBenefP() {
		return elabArchivoBenefP;
	}

	public void setElabArchivoBenefP(BigDecimal elabArchivoBenefP) {
		this.elabArchivoBenefP = elabArchivoBenefP;
	}

	public BigDecimal getDigitFichaBenefP() {
		return digitFichaBenefP;
	}

	public void setDigitFichaBenefP(BigDecimal digitFichaBenefP) {
		this.digitFichaBenefP = digitFichaBenefP;
	}

	public BigDecimal getTotalEmpadP() {
		return totalEmpadP;
	}

	public void setTotalEmpadP(BigDecimal totalEmpadP) {
		this.totalEmpadP = totalEmpadP;
	}

	public BigDecimal getImprVolantesP() {
		return imprVolantesP;
	}

	public void setImprVolantesP(BigDecimal imprVolantesP) {
		this.imprVolantesP = imprVolantesP;
	}

	public BigDecimal getImprAfichesP() {
		return imprAfichesP;
	}

	public void setImprAfichesP(BigDecimal imprAfichesP) {
		this.imprAfichesP = imprAfichesP;
	}

	public BigDecimal getRepFolletosP() {
		return repFolletosP;
	}

	public void setRepFolletosP(BigDecimal repFolletosP) {
		this.repFolletosP = repFolletosP;
	}

	public BigDecimal getSpotPublTvP() {
		return spotPublTvP;
	}

	public void setSpotPublTvP(BigDecimal spotPublTvP) {
		this.spotPublTvP = spotPublTvP;
	}

	public BigDecimal getSpotPublRadioP() {
		return spotPublRadioP;
	}

	public void setSpotPublRadioP(BigDecimal spotPublRadioP) {
		this.spotPublRadioP = spotPublRadioP;
	}

	public BigDecimal getTotalDifIniProgP() {
		return totalDifIniProgP;
	}

	public void setTotalDifIniProgP(BigDecimal totalDifIniProgP) {
		this.totalDifIniProgP = totalDifIniProgP;
	}

	public long getNroBenefEmpadP() {
		return nroBenefEmpadP;
	}

	public void setNroBenefEmpadP(long nroBenefEmpadP) {
		this.nroBenefEmpadP = nroBenefEmpadP;
	}

	public BigDecimal getCostoUnitEmpadP() {
		return costoUnitEmpadP;
	}

	public void setCostoUnitEmpadP(BigDecimal costoUnitEmpadP) {
		this.costoUnitEmpadP = costoUnitEmpadP;
	}

	public BigDecimal getPromConvAgentP() {
		return promConvAgentP;
	}

	public void setPromConvAgentP(BigDecimal promConvAgentP) {
		this.promConvAgentP = promConvAgentP;
	}

	public BigDecimal getRegConvAgentP() {
		return regConvAgentP;
	}

	public void setRegConvAgentP(BigDecimal regConvAgentP) {
		this.regConvAgentP = regConvAgentP;
	}

	public BigDecimal getImpEntrBandP() {
		return impEntrBandP;
	}

	public void setImpEntrBandP(BigDecimal impEntrBandP) {
		this.impEntrBandP = impEntrBandP;
	}

	public BigDecimal getTotalCostoAgentP() {
		return totalCostoAgentP;
	}

	public void setTotalCostoAgentP(BigDecimal totalCostoAgentP) {
		this.totalCostoAgentP = totalCostoAgentP;
	}

	public long getNroAgentP() {
		return nroAgentP;
	}

	public void setNroAgentP(long nroAgentP) {
		this.nroAgentP = nroAgentP;
	}

	public BigDecimal getCostoUnitAgentP() {
		return costoUnitAgentP;
	}

	public void setCostoUnitAgentP(BigDecimal costoUnitAgentP) {
		this.costoUnitAgentP = costoUnitAgentP;
	}

	public BigDecimal getSumEmpadDifusionL() {
		return sumEmpadDifusionL;
	}

	public void setSumEmpadDifusionL(BigDecimal sumEmpadDifusionL) {
		this.sumEmpadDifusionL = sumEmpadDifusionL;
	}

	public BigDecimal getImprEsqInvitL() {
		return imprEsqInvitL;
	}

	public void setImprEsqInvitL(BigDecimal imprEsqInvitL) {
		this.imprEsqInvitL = imprEsqInvitL;
	}

	public BigDecimal getImprDeclaJuradaL() {
		return imprDeclaJuradaL;
	}

	public void setImprDeclaJuradaL(BigDecimal imprDeclaJuradaL) {
		this.imprDeclaJuradaL = imprDeclaJuradaL;
	}

	public BigDecimal getImprFichaVerifL() {
		return imprFichaVerifL;
	}

	public void setImprFichaVerifL(BigDecimal imprFichaVerifL) {
		this.imprFichaVerifL = imprFichaVerifL;
	}

	public BigDecimal getRepartoEsqInvitL() {
		return repartoEsqInvitL;
	}

	public void setRepartoEsqInvitL(BigDecimal repartoEsqInvitL) {
		this.repartoEsqInvitL = repartoEsqInvitL;
	}

	public BigDecimal getVerifInfoL() {
		return verifInfoL;
	}

	public void setVerifInfoL(BigDecimal verifInfoL) {
		this.verifInfoL = verifInfoL;
	}

	public BigDecimal getElabArchivoBenefL() {
		return elabArchivoBenefL;
	}

	public void setElabArchivoBenefL(BigDecimal elabArchivoBenefL) {
		this.elabArchivoBenefL = elabArchivoBenefL;
	}

	public BigDecimal getDigitFichaBenefL() {
		return digitFichaBenefL;
	}

	public void setDigitFichaBenefL(BigDecimal digitFichaBenefL) {
		this.digitFichaBenefL = digitFichaBenefL;
	}

	public BigDecimal getTotalEmpadL() {
		return totalEmpadL;
	}

	public void setTotalEmpadL(BigDecimal totalEmpadL) {
		this.totalEmpadL = totalEmpadL;
	}

	public BigDecimal getImprVolantesL() {
		return imprVolantesL;
	}

	public void setImprVolantesL(BigDecimal imprVolantesL) {
		this.imprVolantesL = imprVolantesL;
	}

	public BigDecimal getImprAfichesL() {
		return imprAfichesL;
	}

	public void setImprAfichesL(BigDecimal imprAfichesL) {
		this.imprAfichesL = imprAfichesL;
	}

	public BigDecimal getRepFolletosL() {
		return repFolletosL;
	}

	public void setRepFolletosL(BigDecimal repFolletosL) {
		this.repFolletosL = repFolletosL;
	}

	public BigDecimal getSpotPublTvL() {
		return spotPublTvL;
	}

	public void setSpotPublTvL(BigDecimal spotPublTvL) {
		this.spotPublTvL = spotPublTvL;
	}

	public BigDecimal getSpotPublRadioL() {
		return spotPublRadioL;
	}

	public void setSpotPublRadioL(BigDecimal spotPublRadioL) {
		this.spotPublRadioL = spotPublRadioL;
	}

	public BigDecimal getTotalDifIniProgL() {
		return totalDifIniProgL;
	}

	public void setTotalDifIniProgL(BigDecimal totalDifIniProgL) {
		this.totalDifIniProgL = totalDifIniProgL;
	}

	public long getNroBenefEmpadL() {
		return nroBenefEmpadL;
	}

	public void setNroBenefEmpadL(long nroBenefEmpadL) {
		this.nroBenefEmpadL = nroBenefEmpadL;
	}

	public BigDecimal getCostoUnitEmpadL() {
		return costoUnitEmpadL;
	}

	public void setCostoUnitEmpadL(BigDecimal costoUnitEmpadL) {
		this.costoUnitEmpadL = costoUnitEmpadL;
	}

	public BigDecimal getPromConvAgentL() {
		return promConvAgentL;
	}

	public void setPromConvAgentL(BigDecimal promConvAgentL) {
		this.promConvAgentL = promConvAgentL;
	}

	public BigDecimal getRegConvAgentL() {
		return regConvAgentL;
	}

	public void setRegConvAgentL(BigDecimal regConvAgentL) {
		this.regConvAgentL = regConvAgentL;
	}

	public BigDecimal getImpEntrBandL() {
		return impEntrBandL;
	}

	public void setImpEntrBandL(BigDecimal impEntrBandL) {
		this.impEntrBandL = impEntrBandL;
	}

	public BigDecimal getTotalCostoAgentL() {
		return totalCostoAgentL;
	}

	public void setTotalCostoAgentL(BigDecimal totalCostoAgentL) {
		this.totalCostoAgentL = totalCostoAgentL;
	}

	public long getNroAgentL() {
		return nroAgentL;
	}

	public void setNroAgentL(long nroAgentL) {
		this.nroAgentL = nroAgentL;
	}

	public BigDecimal getCostoUnitAgentL() {
		return costoUnitAgentL;
	}

	public void setCostoUnitAgentL(BigDecimal costoUnitAgentL) {
		this.costoUnitAgentL = costoUnitAgentL;
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

	public Map<String, String> getListaEtapa() {
		return listaEtapa;
	}

	public void setListaEtapa(Map<String, String> listaEtapa) {
		this.listaEtapa = listaEtapa;
	}

	public String getPeriodoEnvio() {
		return periodoEnvio;
	}

	public void setPeriodoEnvio(String periodoEnvio) {
		this.periodoEnvio = periodoEnvio;
	}

	public List<FisePeriodoEnvio> getListaPeriodoEnvio() {
		return listaPeriodoEnvio;
	}

	public void setListaPeriodoEnvio(List<FisePeriodoEnvio> listaPeriodoEnvio) {
		this.listaPeriodoEnvio = listaPeriodoEnvio;
	}

	public String getFlagPeriodoEjecucion() {
		return flagPeriodoEjecucion;
	}

	public void setFlagPeriodoEjecucion(String flagPeriodoEjecucion) {
		this.flagPeriodoEjecucion = flagPeriodoEjecucion;
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

	public void setAnioPresent(Long anioPresent) {
		this.anioPresent = anioPresent;
	}

	public void setMesPresent(Long mesPresent) {
		this.mesPresent = mesPresent;
	}

	public void setAnioInicioVigencia(Long anioInicioVigencia) {
		this.anioInicioVigencia = anioInicioVigencia;
	}

	public void setAnioFinVigencia(Long anioFinVigencia) {
		this.anioFinVigencia = anioFinVigencia;
	}

	public void setNroBenefEmpadR(Long nroBenefEmpadR) {
		this.nroBenefEmpadR = nroBenefEmpadR;
	}

	public void setNroAgentR(Long nroAgentR) {
		this.nroAgentR = nroAgentR;
	}

	public void setNroBenefEmpadP(Long nroBenefEmpadP) {
		this.nroBenefEmpadP = nroBenefEmpadP;
	}

	public void setNroAgentP(Long nroAgentP) {
		this.nroAgentP = nroAgentP;
	}

	public void setNroBenefEmpadL(Long nroBenefEmpadL) {
		this.nroBenefEmpadL = nroBenefEmpadL;
	}

	public void setNroAgentL(Long nroAgentL) {
		this.nroAgentL = nroAgentL;
	}

	

}

