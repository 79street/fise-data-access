package gob.osinergmin.fise.bean;

import java.io.Serializable;
import java.math.BigDecimal;

public class Formato12ACBean implements Serializable {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private String codigoEmpresa;
	private long anioPresent = 0;
	private long mesPresent = 0;
	private long anioEjecuc = 0;
	private long mesEjecuc = 0;
	
	private long idGrupoInfo = 0;
	
	private long nroEmpadR=0;
	private BigDecimal costoUnitEmpadR = new BigDecimal(0);
	private BigDecimal costoTotalEmpadR = new BigDecimal(0);
	private long nroAgentR=0;
	private BigDecimal costoUnitAgentR = new BigDecimal(0);
	private BigDecimal costoTotalAgentR = new BigDecimal(0);
	private BigDecimal desplPersonalR = new BigDecimal(0);
	private BigDecimal activExtraordR = new BigDecimal(0);
	
	private long nroEmpadP=0;
	private BigDecimal costoUnitEmpadP = new BigDecimal(0);
	private BigDecimal costoTotalEmpadP = new BigDecimal(0);
	private long nroAgentP=0;
	private BigDecimal costoUnitAgentP = new BigDecimal(0);
	private BigDecimal costoTotalAgentP = new BigDecimal(0);
	private BigDecimal desplPersonalP = new BigDecimal(0);
	private BigDecimal activExtraordP = new BigDecimal(0);
	
	private long nroEmpadL=0;
	private BigDecimal costoUnitEmpadL = new BigDecimal(0);
	private BigDecimal costoTotalEmpadL = new BigDecimal(0);
	private long nroAgentL=0;
	private BigDecimal costoUnitAgentL = new BigDecimal(0);
	private BigDecimal costoTotalAgentL = new BigDecimal(0);
	private BigDecimal desplPersonalL = new BigDecimal(0);
	private BigDecimal activExtraordL = new BigDecimal(0);
	
	private BigDecimal totalCostoTotalEmpad;
	private BigDecimal totalCostoTotalAgent;
	private BigDecimal totalDesplPersonal;
	private BigDecimal totalActivExtraord;
	
	private BigDecimal totalGeneral = new BigDecimal(0);
	
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
	
	public Formato12ACBean(){
		
	}
	
	/*public FormatoFise12ACCommand(FiseFormato12AC fiseFormato12AC){
		this.codigoEmpresa=fiseFormato12AC.getId().getCodEmpresa();
		this.anioPresent=fiseFormato12AC.getId().getAnoPresentacion();
		this.mesPresent=fiseFormato12AC.getId().getMesPresentacion();
		this.anioEjecuc=fiseFormato12AC.getId().getAnoEjecucionGasto();
		this.mesEjecuc=fiseFormato12AC.getId().getMesEjecucionGasto();
		//
		//---this.idGrupoInfo = fiseFormato12AC.getIdGrupoInformacion();
		this.totalGeneral=fiseFormato12AC.getTotalAReconocer();
	}*/

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

	public long getIdGrupoInfo() {
		return idGrupoInfo;
	}

	public void setIdGrupoInfo(long idGrupoInfo) {
		this.idGrupoInfo = idGrupoInfo;
	}

	public long getNroEmpadR() {
		return nroEmpadR;
	}

	public void setNroEmpadR(long nroEmpadR) {
		this.nroEmpadR = nroEmpadR;
	}

	public BigDecimal getCostoUnitEmpadR() {
		return costoUnitEmpadR;
	}

	public void setCostoUnitEmpadR(BigDecimal costoUnitEmpadR) {
		this.costoUnitEmpadR = costoUnitEmpadR;
	}

	public BigDecimal getCostoTotalEmpadR() {
		return costoTotalEmpadR;
	}

	public void setCostoTotalEmpadR(BigDecimal costoTotalEmpadR) {
		this.costoTotalEmpadR = costoTotalEmpadR;
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

	public BigDecimal getCostoTotalAgentR() {
		return costoTotalAgentR;
	}

	public void setCostoTotalAgentR(BigDecimal costoTotalAgentR) {
		this.costoTotalAgentR = costoTotalAgentR;
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

	public long getNroEmpadP() {
		return nroEmpadP;
	}

	public void setNroEmpadP(long nroEmpadP) {
		this.nroEmpadP = nroEmpadP;
	}

	public BigDecimal getCostoUnitEmpadP() {
		return costoUnitEmpadP;
	}

	public void setCostoUnitEmpadP(BigDecimal costoUnitEmpadP) {
		this.costoUnitEmpadP = costoUnitEmpadP;
	}

	public BigDecimal getCostoTotalEmpadP() {
		return costoTotalEmpadP;
	}

	public void setCostoTotalEmpadP(BigDecimal costoTotalEmpadP) {
		this.costoTotalEmpadP = costoTotalEmpadP;
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

	public BigDecimal getCostoTotalAgentP() {
		return costoTotalAgentP;
	}

	public void setCostoTotalAgentP(BigDecimal costoTotalAgentP) {
		this.costoTotalAgentP = costoTotalAgentP;
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

	public long getNroEmpadL() {
		return nroEmpadL;
	}

	public void setNroEmpadL(long nroEmpadL) {
		this.nroEmpadL = nroEmpadL;
	}

	public BigDecimal getCostoUnitEmpadL() {
		return costoUnitEmpadL;
	}

	public void setCostoUnitEmpadL(BigDecimal costoUnitEmpadL) {
		this.costoUnitEmpadL = costoUnitEmpadL;
	}

	public BigDecimal getCostoTotalEmpadL() {
		return costoTotalEmpadL;
	}

	public void setCostoTotalEmpadL(BigDecimal costoTotalEmpadL) {
		this.costoTotalEmpadL = costoTotalEmpadL;
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

	public BigDecimal getCostoTotalAgentL() {
		return costoTotalAgentL;
	}

	public void setCostoTotalAgentL(BigDecimal costoTotalAgentL) {
		this.costoTotalAgentL = costoTotalAgentL;
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

	public BigDecimal getTotalCostoTotalEmpad() {
		return totalCostoTotalEmpad;
	}

	public void setTotalCostoTotalEmpad(BigDecimal totalCostoTotalEmpad) {
		this.totalCostoTotalEmpad = totalCostoTotalEmpad;
	}

	public BigDecimal getTotalCostoTotalAgent() {
		return totalCostoTotalAgent;
	}

	public void setTotalCostoTotalAgent(BigDecimal totalCostoTotalAgent) {
		this.totalCostoTotalAgent = totalCostoTotalAgent;
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

	public String getTipoArchivo() {
		return tipoArchivo;
	}

	public void setTipoArchivo(String tipoArchivo) {
		this.tipoArchivo = tipoArchivo;
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
