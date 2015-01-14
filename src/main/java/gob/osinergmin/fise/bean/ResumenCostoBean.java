package gob.osinergmin.fise.bean;

import gob.osinergmin.fise.domain.AdmEmpresa;
import gob.osinergmin.fise.domain.FiseGrupoInformacion;

import java.math.BigDecimal;
import java.util.List;

public class ResumenCostoBean {
	
	/**********Datos de busqueda***************/
	private List<AdmEmpresa> listaEmpresas;
	
	private List<FiseGrupoInformacion> listaGrupoInf;
	private String idGrupoInf;
	
	private String codEmpresaBusq;		
	private String optionFormato;	
	private String grupoInfBusq;	
	private String desMes;	
	private boolean admin;	
	
	
	//varibales para los reportes
	private String desEmpresa;	
	private String periodo;
	
	private String optionBienal;
	private String optionZona;
	
	
	//F14A
	private BigDecimal empSolicitadoR;
	private BigDecimal empSolicitadoP;
	private BigDecimal empSolicitadoL;	
	private BigDecimal empAprobadoR;
	private BigDecimal empAprobadoP;
	private BigDecimal empAprobadoL;
	
	private BigDecimal glpSolicitadoR;
	private BigDecimal glpSolicitadoP;
	private BigDecimal glpSolicitadoL;	
	private BigDecimal glpAprobadoR;
	private BigDecimal glpAprobadoP;
	private BigDecimal glpAprobadoL;	
	
	//F14B
	private BigDecimal cosImpValSoliR;//costo unitario por impresion de vales solicitado rural
	private BigDecimal cosImpValAprobR;//costo unitario por impresion de vales aprobado rural
	private BigDecimal cosImpValSoliP;//costo unitario por impresion de vales solicitado provincia
	private BigDecimal cosImpValAprobP;//costo unitario por impresion de vales aprobado provincia
	private BigDecimal cosImpValSoliL;//costo unitario por impresion de vales solicitado lima
	private BigDecimal cosImpValAprobL;//costo unitario por impresion de vales aprobado lima
	private BigDecimal cosDomValSoliR;//costo unitario reparto a domicilio de vales solicitado rural
	private BigDecimal cosDomValAprobR;//costo unitario reparto a domicilio de vales aprobado rural
	private BigDecimal cosDomValSoliP;//costo unitario reparto a domicilio de vales solicitado provincia
	private BigDecimal cosDomValAprobP;//costo unitario reparto a domicilio de vales aprobado provincia
	private BigDecimal cosDomValSoliL;//costo unitario reparto a domicilio de vales solicitado lima
	private BigDecimal cosDomValAprobL;//costo unitario reparto a domicilio de vales aprobado lima
	private BigDecimal cosEntDisEleSoliR;//costo unitario entrega de vales en dist. elec solicitado rural
	private BigDecimal cosEntDisEleAprobR;//costo unitario entrega de vales en dist. elec solicitado rural
	private BigDecimal cosEntDisEleSoliP;//costo unitario entrega de vales en dist. elec solicitado provincia
	private BigDecimal cosEntDisEleAprobP;//costo unitario entrega de vales en dist. elec aprobado provincia
	private BigDecimal cosEntDisEleSoliL;//costo unitario entrega de vales en dist. elec solicitado lima
	private BigDecimal cosEntDisEleAprobL;//costo unitario entrega de vales en dist. elec aprobado lima	
	private BigDecimal cosValFisSoliR;//costo unitario x canje y liq. de vales fisicos solicitado rural
	private BigDecimal cosValFisAprobR;//costo unitario x canje y liq. de vales fisicos aprobado provincia
	private BigDecimal cosValFisSoliP;//costo unitario x canje y liq. de vales fisicos solicitado provincia
	private BigDecimal cosValFisAprobP;//costo unitario x canje y liq. de vales fisicos aprobado provincia
	private BigDecimal cosValFisSoliL;//costo unitario x canje y liq. de vales fisicos solicitado lima
	private BigDecimal cosValFisAprobL;//costo unitario x canje y liq. de vales fisicos aprobado lima	
	private BigDecimal cosValDigSoliR;//costo unitario x canje  de vales digitales banca celular solicitado rural
	private BigDecimal cosValDigAprobR;//costo unitario x canje  de vales digitales banca celular aprobado rural
	private BigDecimal cosValDigSoliP;//costo unitario x canje  de vales digitales banca celular solicitado provincia
	private BigDecimal cosValDigAprobP;//costo unitario x canje  de vales digitales banca celular aprobado provincia
	private BigDecimal cosValDigSoliL;//costo unitario x canje  de vales digitales banca celular solicitado lima
	private BigDecimal cosValDigAprobL;//costo unitario x canje  de vales digitales banca celular aprobado lima	
	private BigDecimal cosAteSoliR;//costo unitario x atencion solicitado rural
	private BigDecimal cosAteAprobR;//costo unitario x atencion aprobado rural
	private BigDecimal cosAteSoliP;//costo unitario x atencion solicitado provincia
	private BigDecimal cosAteAprobP;//costo unitario x atencion aprobado provincia
	private BigDecimal cosAteSoliL;//costo unitario x atencion solicitado lima
	private BigDecimal cosAteAprobL;//costo unitario x atencion aprobado lima	
	private BigDecimal cosGestAdmSoliR;//costo total gestion administrativa solicitado rural
	private BigDecimal cosGestAdmAprobR;//costo total gestion administrativa aprobado rural
	private BigDecimal cosGestAdmSoliP;//costo total gestion administrativa solicitado provincia
	private BigDecimal cosGestAdmAprobP;//costo total gestion administrativa aprobado provincia
	private BigDecimal cosGestAdmSoliL;//costo total gestion administrativa solicitado lima
	private BigDecimal cosGestAdmAprobL;//costo total gestion administrativa aprobado lima
	
	//F12A  F12B
	private BigDecimal solicitadoR;
	private BigDecimal solicitadoP;
	private BigDecimal solicitadoL;	
	private BigDecimal aprobadoR;
	private BigDecimal aprobadoP;
	private BigDecimal aprobadoL;
	
		
	/*****Metodos get y set****/	
	
	public List<AdmEmpresa> getListaEmpresas() {
		return listaEmpresas;
	}
	public void setListaEmpresas(List<AdmEmpresa> listaEmpresas) {
		this.listaEmpresas = listaEmpresas;
	}
	public List<FiseGrupoInformacion> getListaGrupoInf() {
		return listaGrupoInf;
	}
	public void setListaGrupoInf(List<FiseGrupoInformacion> listaGrupoInf) {
		this.listaGrupoInf = listaGrupoInf;
	}
	public String getIdGrupoInf() {
		return idGrupoInf;
	}
	public void setIdGrupoInf(String idGrupoInf) {
		this.idGrupoInf = idGrupoInf;
	}
	public String getCodEmpresaBusq() {
		return codEmpresaBusq;
	}
	public void setCodEmpresaBusq(String codEmpresaBusq) {
		this.codEmpresaBusq = codEmpresaBusq;
	}
	public String getOptionFormato() {
		return optionFormato;
	}
	public void setOptionFormato(String optionFormato) {
		this.optionFormato = optionFormato;
	}
	public String getGrupoInfBusq() {
		return grupoInfBusq;
	}
	public void setGrupoInfBusq(String grupoInfBusq) {
		this.grupoInfBusq = grupoInfBusq;
	}
	public String getDesEmpresa() {
		return desEmpresa;
	}
	public void setDesEmpresa(String desEmpresa) {
		this.desEmpresa = desEmpresa;
	}
	public String getDesMes() {
		return desMes;
	}
	public void setDesMes(String desMes) {
		this.desMes = desMes;
	}
	public boolean isAdmin() {
		return admin;
	}
	public void setAdmin(boolean admin) {
		this.admin = admin;
	}
	public String getPeriodo() {
		return periodo;
	}
	public void setPeriodo(String periodo) {
		this.periodo = periodo;
	}
	public BigDecimal getEmpSolicitadoR() {
		return empSolicitadoR;
	}
	public void setEmpSolicitadoR(BigDecimal empSolicitadoR) {
		this.empSolicitadoR = empSolicitadoR;
	}
	public BigDecimal getEmpSolicitadoP() {
		return empSolicitadoP;
	}
	public void setEmpSolicitadoP(BigDecimal empSolicitadoP) {
		this.empSolicitadoP = empSolicitadoP;
	}
	public BigDecimal getEmpSolicitadoL() {
		return empSolicitadoL;
	}
	public void setEmpSolicitadoL(BigDecimal empSolicitadoL) {
		this.empSolicitadoL = empSolicitadoL;
	}
	public BigDecimal getEmpAprobadoR() {
		return empAprobadoR;
	}
	public void setEmpAprobadoR(BigDecimal empAprobadoR) {
		this.empAprobadoR = empAprobadoR;
	}
	public BigDecimal getEmpAprobadoP() {
		return empAprobadoP;
	}
	public void setEmpAprobadoP(BigDecimal empAprobadoP) {
		this.empAprobadoP = empAprobadoP;
	}
	public BigDecimal getEmpAprobadoL() {
		return empAprobadoL;
	}
	public void setEmpAprobadoL(BigDecimal empAprobadoL) {
		this.empAprobadoL = empAprobadoL;
	}
	public BigDecimal getGlpSolicitadoR() {
		return glpSolicitadoR;
	}
	public void setGlpSolicitadoR(BigDecimal glpSolicitadoR) {
		this.glpSolicitadoR = glpSolicitadoR;
	}
	public BigDecimal getGlpSolicitadoP() {
		return glpSolicitadoP;
	}
	public void setGlpSolicitadoP(BigDecimal glpSolicitadoP) {
		this.glpSolicitadoP = glpSolicitadoP;
	}
	public BigDecimal getGlpSolicitadoL() {
		return glpSolicitadoL;
	}
	public void setGlpSolicitadoL(BigDecimal glpSolicitadoL) {
		this.glpSolicitadoL = glpSolicitadoL;
	}
	public BigDecimal getGlpAprobadoR() {
		return glpAprobadoR;
	}
	public void setGlpAprobadoR(BigDecimal glpAprobadoR) {
		this.glpAprobadoR = glpAprobadoR;
	}
	public BigDecimal getGlpAprobadoP() {
		return glpAprobadoP;
	}
	public void setGlpAprobadoP(BigDecimal glpAprobadoP) {
		this.glpAprobadoP = glpAprobadoP;
	}
	public BigDecimal getGlpAprobadoL() {
		return glpAprobadoL;
	}
	public void setGlpAprobadoL(BigDecimal glpAprobadoL) {
		this.glpAprobadoL = glpAprobadoL;
	}
	public BigDecimal getSolicitadoR() {
		return solicitadoR;
	}
	public void setSolicitadoR(BigDecimal solicitadoR) {
		this.solicitadoR = solicitadoR;
	}
	public BigDecimal getSolicitadoP() {
		return solicitadoP;
	}
	public void setSolicitadoP(BigDecimal solicitadoP) {
		this.solicitadoP = solicitadoP;
	}
	public BigDecimal getSolicitadoL() {
		return solicitadoL;
	}
	public void setSolicitadoL(BigDecimal solicitadoL) {
		this.solicitadoL = solicitadoL;
	}
	public BigDecimal getAprobadoR() {
		return aprobadoR;
	}
	public void setAprobadoR(BigDecimal aprobadoR) {
		this.aprobadoR = aprobadoR;
	}
	public BigDecimal getAprobadoP() {
		return aprobadoP;
	}
	public void setAprobadoP(BigDecimal aprobadoP) {
		this.aprobadoP = aprobadoP;
	}
	public BigDecimal getAprobadoL() {
		return aprobadoL;
	}
	public void setAprobadoL(BigDecimal aprobadoL) {
		this.aprobadoL = aprobadoL;
	}
	public BigDecimal getCosImpValSoliR() {
		return cosImpValSoliR;
	}
	public void setCosImpValSoliR(BigDecimal cosImpValSoliR) {
		this.cosImpValSoliR = cosImpValSoliR;
	}
	public BigDecimal getCosImpValAprobR() {
		return cosImpValAprobR;
	}
	public void setCosImpValAprobR(BigDecimal cosImpValAprobR) {
		this.cosImpValAprobR = cosImpValAprobR;
	}
	public BigDecimal getCosImpValSoliP() {
		return cosImpValSoliP;
	}
	public void setCosImpValSoliP(BigDecimal cosImpValSoliP) {
		this.cosImpValSoliP = cosImpValSoliP;
	}
	public BigDecimal getCosImpValAprobP() {
		return cosImpValAprobP;
	}
	public void setCosImpValAprobP(BigDecimal cosImpValAprobP) {
		this.cosImpValAprobP = cosImpValAprobP;
	}
	public BigDecimal getCosImpValSoliL() {
		return cosImpValSoliL;
	}
	public void setCosImpValSoliL(BigDecimal cosImpValSoliL) {
		this.cosImpValSoliL = cosImpValSoliL;
	}
	public BigDecimal getCosImpValAprobL() {
		return cosImpValAprobL;
	}
	public void setCosImpValAprobL(BigDecimal cosImpValAprobL) {
		this.cosImpValAprobL = cosImpValAprobL;
	}
	public BigDecimal getCosDomValSoliR() {
		return cosDomValSoliR;
	}
	public void setCosDomValSoliR(BigDecimal cosDomValSoliR) {
		this.cosDomValSoliR = cosDomValSoliR;
	}
	public BigDecimal getCosDomValAprobR() {
		return cosDomValAprobR;
	}
	public void setCosDomValAprobR(BigDecimal cosDomValAprobR) {
		this.cosDomValAprobR = cosDomValAprobR;
	}
	public BigDecimal getCosDomValSoliP() {
		return cosDomValSoliP;
	}
	public void setCosDomValSoliP(BigDecimal cosDomValSoliP) {
		this.cosDomValSoliP = cosDomValSoliP;
	}
	public BigDecimal getCosDomValAprobP() {
		return cosDomValAprobP;
	}
	public void setCosDomValAprobP(BigDecimal cosDomValAprobP) {
		this.cosDomValAprobP = cosDomValAprobP;
	}
	public BigDecimal getCosDomValSoliL() {
		return cosDomValSoliL;
	}
	public void setCosDomValSoliL(BigDecimal cosDomValSoliL) {
		this.cosDomValSoliL = cosDomValSoliL;
	}
	public BigDecimal getCosDomValAprobL() {
		return cosDomValAprobL;
	}
	public void setCosDomValAprobL(BigDecimal cosDomValAprobL) {
		this.cosDomValAprobL = cosDomValAprobL;
	}
	public BigDecimal getCosEntDisEleSoliR() {
		return cosEntDisEleSoliR;
	}
	public void setCosEntDisEleSoliR(BigDecimal cosEntDisEleSoliR) {
		this.cosEntDisEleSoliR = cosEntDisEleSoliR;
	}
	public BigDecimal getCosEntDisEleAprobR() {
		return cosEntDisEleAprobR;
	}
	public void setCosEntDisEleAprobR(BigDecimal cosEntDisEleAprobR) {
		this.cosEntDisEleAprobR = cosEntDisEleAprobR;
	}
	public BigDecimal getCosEntDisEleSoliP() {
		return cosEntDisEleSoliP;
	}
	public void setCosEntDisEleSoliP(BigDecimal cosEntDisEleSoliP) {
		this.cosEntDisEleSoliP = cosEntDisEleSoliP;
	}
	public BigDecimal getCosEntDisEleAprobP() {
		return cosEntDisEleAprobP;
	}
	public void setCosEntDisEleAprobP(BigDecimal cosEntDisEleAprobP) {
		this.cosEntDisEleAprobP = cosEntDisEleAprobP;
	}
	public BigDecimal getCosEntDisEleSoliL() {
		return cosEntDisEleSoliL;
	}
	public void setCosEntDisEleSoliL(BigDecimal cosEntDisEleSoliL) {
		this.cosEntDisEleSoliL = cosEntDisEleSoliL;
	}
	public BigDecimal getCosEntDisEleAprobL() {
		return cosEntDisEleAprobL;
	}
	public void setCosEntDisEleAprobL(BigDecimal cosEntDisEleAprobL) {
		this.cosEntDisEleAprobL = cosEntDisEleAprobL;
	}
	public BigDecimal getCosValFisSoliR() {
		return cosValFisSoliR;
	}
	public void setCosValFisSoliR(BigDecimal cosValFisSoliR) {
		this.cosValFisSoliR = cosValFisSoliR;
	}
	public BigDecimal getCosValFisAprobR() {
		return cosValFisAprobR;
	}
	public void setCosValFisAprobR(BigDecimal cosValFisAprobR) {
		this.cosValFisAprobR = cosValFisAprobR;
	}
	public BigDecimal getCosValFisSoliP() {
		return cosValFisSoliP;
	}
	public void setCosValFisSoliP(BigDecimal cosValFisSoliP) {
		this.cosValFisSoliP = cosValFisSoliP;
	}
	public BigDecimal getCosValFisAprobP() {
		return cosValFisAprobP;
	}
	public void setCosValFisAprobP(BigDecimal cosValFisAprobP) {
		this.cosValFisAprobP = cosValFisAprobP;
	}
	public BigDecimal getCosValFisSoliL() {
		return cosValFisSoliL;
	}
	public void setCosValFisSoliL(BigDecimal cosValFisSoliL) {
		this.cosValFisSoliL = cosValFisSoliL;
	}
	public BigDecimal getCosValFisAprobL() {
		return cosValFisAprobL;
	}
	public void setCosValFisAprobL(BigDecimal cosValFisAprobL) {
		this.cosValFisAprobL = cosValFisAprobL;
	}
	public BigDecimal getCosValDigSoliR() {
		return cosValDigSoliR;
	}
	public void setCosValDigSoliR(BigDecimal cosValDigSoliR) {
		this.cosValDigSoliR = cosValDigSoliR;
	}
	public BigDecimal getCosValDigAprobR() {
		return cosValDigAprobR;
	}
	public void setCosValDigAprobR(BigDecimal cosValDigAprobR) {
		this.cosValDigAprobR = cosValDigAprobR;
	}
	public BigDecimal getCosValDigSoliP() {
		return cosValDigSoliP;
	}
	public void setCosValDigSoliP(BigDecimal cosValDigSoliP) {
		this.cosValDigSoliP = cosValDigSoliP;
	}
	public BigDecimal getCosValDigAprobP() {
		return cosValDigAprobP;
	}
	public void setCosValDigAprobP(BigDecimal cosValDigAprobP) {
		this.cosValDigAprobP = cosValDigAprobP;
	}
	public BigDecimal getCosValDigSoliL() {
		return cosValDigSoliL;
	}
	public void setCosValDigSoliL(BigDecimal cosValDigSoliL) {
		this.cosValDigSoliL = cosValDigSoliL;
	}
	public BigDecimal getCosValDigAprobL() {
		return cosValDigAprobL;
	}
	public void setCosValDigAprobL(BigDecimal cosValDigAprobL) {
		this.cosValDigAprobL = cosValDigAprobL;
	}
	public BigDecimal getCosAteSoliR() {
		return cosAteSoliR;
	}
	public void setCosAteSoliR(BigDecimal cosAteSoliR) {
		this.cosAteSoliR = cosAteSoliR;
	}
	public BigDecimal getCosAteAprobR() {
		return cosAteAprobR;
	}
	public void setCosAteAprobR(BigDecimal cosAteAprobR) {
		this.cosAteAprobR = cosAteAprobR;
	}
	public BigDecimal getCosAteSoliP() {
		return cosAteSoliP;
	}
	public void setCosAteSoliP(BigDecimal cosAteSoliP) {
		this.cosAteSoliP = cosAteSoliP;
	}
	public BigDecimal getCosAteAprobP() {
		return cosAteAprobP;
	}
	public void setCosAteAprobP(BigDecimal cosAteAprobP) {
		this.cosAteAprobP = cosAteAprobP;
	}
	public BigDecimal getCosAteSoliL() {
		return cosAteSoliL;
	}
	public void setCosAteSoliL(BigDecimal cosAteSoliL) {
		this.cosAteSoliL = cosAteSoliL;
	}
	public BigDecimal getCosAteAprobL() {
		return cosAteAprobL;
	}
	public void setCosAteAprobL(BigDecimal cosAteAprobL) {
		this.cosAteAprobL = cosAteAprobL;
	}
	public BigDecimal getCosGestAdmSoliR() {
		return cosGestAdmSoliR;
	}
	public void setCosGestAdmSoliR(BigDecimal cosGestAdmSoliR) {
		this.cosGestAdmSoliR = cosGestAdmSoliR;
	}
	public BigDecimal getCosGestAdmAprobR() {
		return cosGestAdmAprobR;
	}
	public void setCosGestAdmAprobR(BigDecimal cosGestAdmAprobR) {
		this.cosGestAdmAprobR = cosGestAdmAprobR;
	}
	public BigDecimal getCosGestAdmSoliP() {
		return cosGestAdmSoliP;
	}
	public void setCosGestAdmSoliP(BigDecimal cosGestAdmSoliP) {
		this.cosGestAdmSoliP = cosGestAdmSoliP;
	}
	public BigDecimal getCosGestAdmAprobP() {
		return cosGestAdmAprobP;
	}
	public void setCosGestAdmAprobP(BigDecimal cosGestAdmAprobP) {
		this.cosGestAdmAprobP = cosGestAdmAprobP;
	}
	public BigDecimal getCosGestAdmSoliL() {
		return cosGestAdmSoliL;
	}
	public void setCosGestAdmSoliL(BigDecimal cosGestAdmSoliL) {
		this.cosGestAdmSoliL = cosGestAdmSoliL;
	}
	public BigDecimal getCosGestAdmAprobL() {
		return cosGestAdmAprobL;
	}
	public void setCosGestAdmAprobL(BigDecimal cosGestAdmAprobL) {
		this.cosGestAdmAprobL = cosGestAdmAprobL;
	}
	public String getOptionBienal() {
		return optionBienal;
	}
	public void setOptionBienal(String optionBienal) {
		this.optionBienal = optionBienal;
	}
	public String getOptionZona() {
		return optionZona;
	}
	public void setOptionZona(String optionZona) {
		this.optionZona = optionZona;
	}
	
	
	
	
}
