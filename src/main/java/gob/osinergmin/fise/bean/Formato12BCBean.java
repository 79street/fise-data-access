package gob.osinergmin.fise.bean;

import java.io.Serializable;
import java.math.BigDecimal;

public class Formato12BCBean implements Serializable{

private static final long serialVersionUID = 1L;
	
	private String codigoEmpresa;
	private long anioPresent = 0;
	private long mesPresent = 0;
	private long anioEjecuc = 0;
	private long mesEjecuc = 0;
	
	private String etapa;
	
	private long idGrupoInfo = 0;	
	private long idZonaBenef=0;
	
	private long nroValeImpR=0;
	private BigDecimal costoUnitValImpR = new BigDecimal(0);
	private BigDecimal costoTotalValImpR = new BigDecimal(0);
	
	private long nroValReparDomicR=0;
	private BigDecimal costoUnitValReparDomicR = new BigDecimal(0);
	private BigDecimal costoTotalValReparDomicR = new BigDecimal(0);
	
	private long nroValEntDisElR=0;
	private BigDecimal costoUnitValEntDisElR = new BigDecimal(0);
	private BigDecimal costoTotalValEntDisElR = new BigDecimal(0);

	private long nroValFisiCanjR=0;
	private BigDecimal costoUnitValFisiCanjR = new BigDecimal(0);
	private BigDecimal costoTotalValFisiCanjR = new BigDecimal(0);
	
	private long nroValDigitCanjR=0;
	private BigDecimal costoUnitValDigitCanjR = new BigDecimal(0);
	private BigDecimal costoTotalValDigitCanjR = new BigDecimal(0);
	
	private long nroAtencionesR=0;
	
	
	private BigDecimal gestionAdmR = new BigDecimal(0);
	private BigDecimal desplPersonalR = new BigDecimal(0);
	private BigDecimal activExtraordR = new BigDecimal(0);
	
	
	private long nroValeImpP=0;
	private BigDecimal costoUnitValImpP = new BigDecimal(0);
	private BigDecimal costoTotalValImpP = new BigDecimal(0);
	
	private long nroValReparDomicP=0;
	private BigDecimal costoUnitValReparDomicP = new BigDecimal(0);
	private BigDecimal costoTotalValReparDomicP = new BigDecimal(0);
	
	private long nroValEntDisElP=0;
	private BigDecimal costoUnitValEntDisElP = new BigDecimal(0);
	private BigDecimal costoTotalValEntDisElP = new BigDecimal(0);

	private long nroValFisiCanjP=0;
	private BigDecimal costoUnitValFisiCanjP = new BigDecimal(0);
	private BigDecimal costoTotalValFisiCanjP = new BigDecimal(0);
	
	private long nroValDigitCanjP=0;
	private BigDecimal costoUnitValDigitCanjP = new BigDecimal(0);
	private BigDecimal costoTotalValDigitCanjP = new BigDecimal(0);
	
	private long nroAtencionesP=0;
	
	
	
	private BigDecimal gestionAdmP = new BigDecimal(0);
	private BigDecimal desplPersonalP = new BigDecimal(0);
	private BigDecimal activExtraordP = new BigDecimal(0);

	
	private long nroValeImpL=0;
	private BigDecimal costoUnitValImpL = new BigDecimal(0);
	private BigDecimal costoTotalValImpL = new BigDecimal(0);
	
	private long nroValReparDomicL=0;
	private BigDecimal costoUnitValReparDomicL = new BigDecimal(0);
	private BigDecimal costoTotalValReparDomicL = new BigDecimal(0);
	
	private long nroValEntDisElL=0;
	private BigDecimal costoUnitValEntDisElL = new BigDecimal(0);
	private BigDecimal costoTotalValEntDisElL = new BigDecimal(0);

	private long nroValFisiCanjL=0;
	private BigDecimal costoUnitValFisiCanjL = new BigDecimal(0);
	private BigDecimal costoTotalValFisiCanjL = new BigDecimal(0);
	
	private long nroValDigitCanjL=0;
	private BigDecimal costoUnitValDigitCanjL = new BigDecimal(0);
	private BigDecimal costoTotalValDigitCanjL = new BigDecimal(0);
	
	private long nroAtencionesL=0;
	
	
	private BigDecimal gestionAdmL = new BigDecimal(0);
	private BigDecimal desplPersonalL = new BigDecimal(0);
	private BigDecimal activExtraordL = new BigDecimal(0);

		
	private BigDecimal totalImpVal;
	private BigDecimal totalValReparDomic;
	private BigDecimal totalValEntDisEl;
	private BigDecimal totalValFisiCanjLiq;
	private BigDecimal totalValDigitCanjLiq;
	private BigDecimal totalSolictConsultReclam;
	private BigDecimal totalGestionAdm;
	private BigDecimal totalDesplPersonal;
	private BigDecimal totalActivExtraord;
	
	private BigDecimal totalGeneral= new BigDecimal(0);
	
	private String usuario;
	private String terminal;
	private String nombreArchivo;
	private String tipoArchivo;
	
	private String descEmpresa;
	private String descMesPresentacion;
	private String descMesEjecucion;
	
	private BigDecimal costoUnitAtencionesR = new BigDecimal(0);
	private BigDecimal costoTotalAtencionesR = new BigDecimal(0);
	private BigDecimal costoUnitAtencionesP = new BigDecimal(0);
	private BigDecimal costoTotalAtencionesP = new BigDecimal(0);
	private BigDecimal costoUnitAtencionesL = new BigDecimal(0);
	private BigDecimal costoTotalAtencionesL = new BigDecimal(0);

	
	
	
	
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
	public long getAnioEjecuc() {
		return anioEjecuc;
	}
	public void setAnioEjecuc(long anioEjecuc) {
		this.anioEjecuc = anioEjecuc;
	}
	public long getMesEjecuc() {
		return mesEjecuc;
	}
	public void setMesEjecuc(long mesEjecuc) {
		this.mesEjecuc = mesEjecuc;
	}
	public String getEtapa() {
		return etapa;
	}
	public void setEtapa(String etapa) {
		this.etapa = etapa;
	}
	public long getIdGrupoInfo() {
		return idGrupoInfo;
	}
	public void setIdGrupoInfo(long idGrupoInfo) {
		this.idGrupoInfo = idGrupoInfo;
	}
	public long getIdZonaBenef() {
		return idZonaBenef;
	}
	public void setIdZonaBenef(long idZonaBenef) {
		this.idZonaBenef = idZonaBenef;
	}
	public long getNroValeImpR() {
		return nroValeImpR;
	}
	public void setNroValeImpR(long nroValeImpR) {
		this.nroValeImpR = nroValeImpR;
	}
	public BigDecimal getCostoUnitValImpR() {
		return costoUnitValImpR;
	}
	public void setCostoUnitValImpR(BigDecimal costoUnitValImpR) {
		this.costoUnitValImpR = costoUnitValImpR;
	}
	public BigDecimal getCostoTotalValImpR() {
		return costoTotalValImpR;
	}
	public void setCostoTotalValImpR(BigDecimal costoTotalValImpR) {
		this.costoTotalValImpR = costoTotalValImpR;
	}
	public long getNroValReparDomicR() {
		return nroValReparDomicR;
	}
	public void setNroValReparDomicR(long nroValReparDomicR) {
		this.nroValReparDomicR = nroValReparDomicR;
	}
	public BigDecimal getCostoUnitValReparDomicR() {
		return costoUnitValReparDomicR;
	}
	public void setCostoUnitValReparDomicR(BigDecimal costoUnitValReparDomicR) {
		this.costoUnitValReparDomicR = costoUnitValReparDomicR;
	}
	public BigDecimal getCostoTotalValReparDomicR() {
		return costoTotalValReparDomicR;
	}
	public void setCostoTotalValReparDomicR(BigDecimal costoTotalValReparDomicR) {
		this.costoTotalValReparDomicR = costoTotalValReparDomicR;
	}
	public long getNroValEntDisElR() {
		return nroValEntDisElR;
	}
	public void setNroValEntDisElR(long nroValEntDisElR) {
		this.nroValEntDisElR = nroValEntDisElR;
	}
	public BigDecimal getCostoUnitValEntDisElR() {
		return costoUnitValEntDisElR;
	}
	public void setCostoUnitValEntDisElR(BigDecimal costoUnitValEntDisElR) {
		this.costoUnitValEntDisElR = costoUnitValEntDisElR;
	}
	public BigDecimal getCostoTotalValEntDisElR() {
		return costoTotalValEntDisElR;
	}
	public void setCostoTotalValEntDisElR(BigDecimal costoTotalValEntDisElR) {
		this.costoTotalValEntDisElR = costoTotalValEntDisElR;
	}
	public long getNroValFisiCanjR() {
		return nroValFisiCanjR;
	}
	public void setNroValFisiCanjR(long nroValFisiCanjR) {
		this.nroValFisiCanjR = nroValFisiCanjR;
	}
	public BigDecimal getCostoUnitValFisiCanjR() {
		return costoUnitValFisiCanjR;
	}
	public void setCostoUnitValFisiCanjR(BigDecimal costoUnitValFisiCanjR) {
		this.costoUnitValFisiCanjR = costoUnitValFisiCanjR;
	}
	public BigDecimal getCostoTotalValFisiCanjR() {
		return costoTotalValFisiCanjR;
	}
	public void setCostoTotalValFisiCanjR(BigDecimal costoTotalValFisiCanjR) {
		this.costoTotalValFisiCanjR = costoTotalValFisiCanjR;
	}
	public long getNroValDigitCanjR() {
		return nroValDigitCanjR;
	}
	public void setNroValDigitCanjR(long nroValDigitCanjR) {
		this.nroValDigitCanjR = nroValDigitCanjR;
	}
	public BigDecimal getCostoUnitValDigitCanjR() {
		return costoUnitValDigitCanjR;
	}
	public void setCostoUnitValDigitCanjR(BigDecimal costoUnitValDigitCanjR) {
		this.costoUnitValDigitCanjR = costoUnitValDigitCanjR;
	}
	public BigDecimal getCostoTotalValDigitCanjR() {
		return costoTotalValDigitCanjR;
	}
	public void setCostoTotalValDigitCanjR(BigDecimal costoTotalValDigitCanjR) {
		this.costoTotalValDigitCanjR = costoTotalValDigitCanjR;
	}
	public long getNroAtencionesR() {
		return nroAtencionesR;
	}
	public void setNroAtencionesR(long nroAtencionesR) {
		this.nroAtencionesR = nroAtencionesR;
	}
	public BigDecimal getGestionAdmR() {
		return gestionAdmR;
	}
	public void setGestionAdmR(BigDecimal gestionAdmR) {
		this.gestionAdmR = gestionAdmR;
	}
	public BigDecimal getDesplPersonalR() {
		return desplPersonalR;
	}
	public void setDesplPersonalR(BigDecimal desplPersonalR) {
		this.desplPersonalR = desplPersonalR;
	}
	public BigDecimal getActivExtraordR() {
		return activExtraordR;
	}
	public void setActivExtraordR(BigDecimal activExtraordR) {
		this.activExtraordR = activExtraordR;
	}
	public long getNroValeImpP() {
		return nroValeImpP;
	}
	public void setNroValeImpP(long nroValeImpP) {
		this.nroValeImpP = nroValeImpP;
	}
	public BigDecimal getCostoUnitValImpP() {
		return costoUnitValImpP;
	}
	public void setCostoUnitValImpP(BigDecimal costoUnitValImpP) {
		this.costoUnitValImpP = costoUnitValImpP;
	}
	public BigDecimal getCostoTotalValImpP() {
		return costoTotalValImpP;
	}
	public void setCostoTotalValImpP(BigDecimal costoTotalValImpP) {
		this.costoTotalValImpP = costoTotalValImpP;
	}
	public long getNroValReparDomicP() {
		return nroValReparDomicP;
	}
	public void setNroValReparDomicP(long nroValReparDomicP) {
		this.nroValReparDomicP = nroValReparDomicP;
	}
	public BigDecimal getCostoUnitValReparDomicP() {
		return costoUnitValReparDomicP;
	}
	public void setCostoUnitValReparDomicP(BigDecimal costoUnitValReparDomicP) {
		this.costoUnitValReparDomicP = costoUnitValReparDomicP;
	}
	public BigDecimal getCostoTotalValReparDomicP() {
		return costoTotalValReparDomicP;
	}
	public void setCostoTotalValReparDomicP(BigDecimal costoTotalValReparDomicP) {
		this.costoTotalValReparDomicP = costoTotalValReparDomicP;
	}
	public long getNroValEntDisElP() {
		return nroValEntDisElP;
	}
	public void setNroValEntDisElP(long nroValEntDisElP) {
		this.nroValEntDisElP = nroValEntDisElP;
	}
	public BigDecimal getCostoUnitValEntDisElP() {
		return costoUnitValEntDisElP;
	}
	public void setCostoUnitValEntDisElP(BigDecimal costoUnitValEntDisElP) {
		this.costoUnitValEntDisElP = costoUnitValEntDisElP;
	}
	public BigDecimal getCostoTotalValEntDisElP() {
		return costoTotalValEntDisElP;
	}
	public void setCostoTotalValEntDisElP(BigDecimal costoTotalValEntDisElP) {
		this.costoTotalValEntDisElP = costoTotalValEntDisElP;
	}
	public long getNroValFisiCanjP() {
		return nroValFisiCanjP;
	}
	public void setNroValFisiCanjP(long nroValFisiCanjP) {
		this.nroValFisiCanjP = nroValFisiCanjP;
	}
	public BigDecimal getCostoUnitValFisiCanjP() {
		return costoUnitValFisiCanjP;
	}
	public void setCostoUnitValFisiCanjP(BigDecimal costoUnitValFisiCanjP) {
		this.costoUnitValFisiCanjP = costoUnitValFisiCanjP;
	}
	public BigDecimal getCostoTotalValFisiCanjP() {
		return costoTotalValFisiCanjP;
	}
	public void setCostoTotalValFisiCanjP(BigDecimal costoTotalValFisiCanjP) {
		this.costoTotalValFisiCanjP = costoTotalValFisiCanjP;
	}
	public long getNroValDigitCanjP() {
		return nroValDigitCanjP;
	}
	public void setNroValDigitCanjP(long nroValDigitCanjP) {
		this.nroValDigitCanjP = nroValDigitCanjP;
	}
	public BigDecimal getCostoUnitValDigitCanjP() {
		return costoUnitValDigitCanjP;
	}
	public void setCostoUnitValDigitCanjP(BigDecimal costoUnitValDigitCanjP) {
		this.costoUnitValDigitCanjP = costoUnitValDigitCanjP;
	}
	public BigDecimal getCostoTotalValDigitCanjP() {
		return costoTotalValDigitCanjP;
	}
	public void setCostoTotalValDigitCanjP(BigDecimal costoTotalValDigitCanjP) {
		this.costoTotalValDigitCanjP = costoTotalValDigitCanjP;
	}
	public long getNroAtencionesP() {
		return nroAtencionesP;
	}
	public void setNroAtencionesP(long nroAtencionesP) {
		this.nroAtencionesP = nroAtencionesP;
	}
	public BigDecimal getGestionAdmP() {
		return gestionAdmP;
	}
	public void setGestionAdmP(BigDecimal gestionAdmP) {
		this.gestionAdmP = gestionAdmP;
	}
	public BigDecimal getDesplPersonalP() {
		return desplPersonalP;
	}
	public void setDesplPersonalP(BigDecimal desplPersonalP) {
		this.desplPersonalP = desplPersonalP;
	}
	public BigDecimal getActivExtraordP() {
		return activExtraordP;
	}
	public void setActivExtraordP(BigDecimal activExtraordP) {
		this.activExtraordP = activExtraordP;
	}
	public long getNroValeImpL() {
		return nroValeImpL;
	}
	public void setNroValeImpL(long nroValeImpL) {
		this.nroValeImpL = nroValeImpL;
	}
	public BigDecimal getCostoUnitValImpL() {
		return costoUnitValImpL;
	}
	public void setCostoUnitValImpL(BigDecimal costoUnitValImpL) {
		this.costoUnitValImpL = costoUnitValImpL;
	}
	public BigDecimal getCostoTotalValImpL() {
		return costoTotalValImpL;
	}
	public void setCostoTotalValImpL(BigDecimal costoTotalValImpL) {
		this.costoTotalValImpL = costoTotalValImpL;
	}
	public long getNroValReparDomicL() {
		return nroValReparDomicL;
	}
	public void setNroValReparDomicL(long nroValReparDomicL) {
		this.nroValReparDomicL = nroValReparDomicL;
	}
	public BigDecimal getCostoUnitValReparDomicL() {
		return costoUnitValReparDomicL;
	}
	public void setCostoUnitValReparDomicL(BigDecimal costoUnitValReparDomicL) {
		this.costoUnitValReparDomicL = costoUnitValReparDomicL;
	}
	public BigDecimal getCostoTotalValReparDomicL() {
		return costoTotalValReparDomicL;
	}
	public void setCostoTotalValReparDomicL(BigDecimal costoTotalValReparDomicL) {
		this.costoTotalValReparDomicL = costoTotalValReparDomicL;
	}
	public long getNroValEntDisElL() {
		return nroValEntDisElL;
	}
	public void setNroValEntDisElL(long nroValEntDisElL) {
		this.nroValEntDisElL = nroValEntDisElL;
	}
	public BigDecimal getCostoUnitValEntDisElL() {
		return costoUnitValEntDisElL;
	}
	public void setCostoUnitValEntDisElL(BigDecimal costoUnitValEntDisElL) {
		this.costoUnitValEntDisElL = costoUnitValEntDisElL;
	}
	public BigDecimal getCostoTotalValEntDisElL() {
		return costoTotalValEntDisElL;
	}
	public void setCostoTotalValEntDisElL(BigDecimal costoTotalValEntDisElL) {
		this.costoTotalValEntDisElL = costoTotalValEntDisElL;
	}
	public long getNroValFisiCanjL() {
		return nroValFisiCanjL;
	}
	public void setNroValFisiCanjL(long nroValFisiCanjL) {
		this.nroValFisiCanjL = nroValFisiCanjL;
	}
	public BigDecimal getCostoUnitValFisiCanjL() {
		return costoUnitValFisiCanjL;
	}
	public void setCostoUnitValFisiCanjL(BigDecimal costoUnitValFisiCanjL) {
		this.costoUnitValFisiCanjL = costoUnitValFisiCanjL;
	}
	public BigDecimal getCostoTotalValFisiCanjL() {
		return costoTotalValFisiCanjL;
	}
	public void setCostoTotalValFisiCanjL(BigDecimal costoTotalValFisiCanjL) {
		this.costoTotalValFisiCanjL = costoTotalValFisiCanjL;
	}
	public long getNroValDigitCanjL() {
		return nroValDigitCanjL;
	}
	public void setNroValDigitCanjL(long nroValDigitCanjL) {
		this.nroValDigitCanjL = nroValDigitCanjL;
	}
	public BigDecimal getCostoUnitValDigitCanjL() {
		return costoUnitValDigitCanjL;
	}
	public void setCostoUnitValDigitCanjL(BigDecimal costoUnitValDigitCanjL) {
		this.costoUnitValDigitCanjL = costoUnitValDigitCanjL;
	}
	public BigDecimal getCostoTotalValDigitCanjL() {
		return costoTotalValDigitCanjL;
	}
	public void setCostoTotalValDigitCanjL(BigDecimal costoTotalValDigitCanjL) {
		this.costoTotalValDigitCanjL = costoTotalValDigitCanjL;
	}
	public long getNroAtencionesL() {
		return nroAtencionesL;
	}
	public void setNroAtencionesL(long nroAtencionesL) {
		this.nroAtencionesL = nroAtencionesL;
	}
	public BigDecimal getGestionAdmL() {
		return gestionAdmL;
	}
	public void setGestionAdmL(BigDecimal gestionAdmL) {
		this.gestionAdmL = gestionAdmL;
	}
	public BigDecimal getDesplPersonalL() {
		return desplPersonalL;
	}
	public void setDesplPersonalL(BigDecimal desplPersonalL) {
		this.desplPersonalL = desplPersonalL;
	}
	public BigDecimal getActivExtraordL() {
		return activExtraordL;
	}
	public void setActivExtraordL(BigDecimal activExtraordL) {
		this.activExtraordL = activExtraordL;
	}
	public BigDecimal getTotalImpVal() {
		return totalImpVal;
	}
	public void setTotalImpVal(BigDecimal totalImpVal) {
		this.totalImpVal = totalImpVal;
	}
	public BigDecimal getTotalValReparDomic() {
		return totalValReparDomic;
	}
	public void setTotalValReparDomic(BigDecimal totalValReparDomic) {
		this.totalValReparDomic = totalValReparDomic;
	}
	public BigDecimal getTotalValEntDisEl() {
		return totalValEntDisEl;
	}
	public void setTotalValEntDisEl(BigDecimal totalValEntDisEl) {
		this.totalValEntDisEl = totalValEntDisEl;
	}
	public BigDecimal getTotalValFisiCanjLiq() {
		return totalValFisiCanjLiq;
	}
	public void setTotalValFisiCanjLiq(BigDecimal totalValFisiCanjLiq) {
		this.totalValFisiCanjLiq = totalValFisiCanjLiq;
	}
	public BigDecimal getTotalValDigitCanjLiq() {
		return totalValDigitCanjLiq;
	}
	public void setTotalValDigitCanjLiq(BigDecimal totalValDigitCanjLiq) {
		this.totalValDigitCanjLiq = totalValDigitCanjLiq;
	}
	public BigDecimal getTotalSolictConsultReclam() {
		return totalSolictConsultReclam;
	}
	public void setTotalSolictConsultReclam(BigDecimal totalSolictConsultReclam) {
		this.totalSolictConsultReclam = totalSolictConsultReclam;
	}
	public BigDecimal getTotalGestionAdm() {
		return totalGestionAdm;
	}
	public void setTotalGestionAdm(BigDecimal totalGestionAdm) {
		this.totalGestionAdm = totalGestionAdm;
	}
	public BigDecimal getTotalDesplPersonal() {
		return totalDesplPersonal;
	}
	public void setTotalDesplPersonal(BigDecimal totalDesplPersonal) {
		this.totalDesplPersonal = totalDesplPersonal;
	}
	public BigDecimal getTotalActivExtraord() {
		return totalActivExtraord;
	}
	public void setTotalActivExtraord(BigDecimal totalActivExtraord) {
		this.totalActivExtraord = totalActivExtraord;
	}
	public BigDecimal getTotalGeneral() {
		return totalGeneral;
	}
	public void setTotalGeneral(BigDecimal totalGeneral) {
		this.totalGeneral = totalGeneral;
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

	public BigDecimal getCostoUnitAtencionesR() {
		return costoUnitAtencionesR;
	}

	public void setCostoUnitAtencionesR(BigDecimal costoUnitAtencionesR) {
		this.costoUnitAtencionesR = costoUnitAtencionesR;
	}

	public BigDecimal getCostoTotalAtencionesR() {
		return costoTotalAtencionesR;
	}

	public void setCostoTotalAtencionesR(BigDecimal costoTotalAtencionesR) {
		this.costoTotalAtencionesR = costoTotalAtencionesR;
	}

	public BigDecimal getCostoUnitAtencionesP() {
		return costoUnitAtencionesP;
	}

	public void setCostoUnitAtencionesP(BigDecimal costoUnitAtencionesP) {
		this.costoUnitAtencionesP = costoUnitAtencionesP;
	}

	public BigDecimal getCostoTotalAtencionesP() {
		return costoTotalAtencionesP;
	}

	public void setCostoTotalAtencionesP(BigDecimal costoTotalAtencionesP) {
		this.costoTotalAtencionesP = costoTotalAtencionesP;
	}

	public BigDecimal getCostoUnitAtencionesL() {
		return costoUnitAtencionesL;
	}

	public void setCostoUnitAtencionesL(BigDecimal costoUnitAtencionesL) {
		this.costoUnitAtencionesL = costoUnitAtencionesL;
	}

	public BigDecimal getCostoTotalAtencionesL() {
		return costoTotalAtencionesL;
	}

	public void setCostoTotalAtencionesL(BigDecimal costoTotalAtencionesL) {
		this.costoTotalAtencionesL = costoTotalAtencionesL;
	}
	
}
