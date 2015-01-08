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
	
}
