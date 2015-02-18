package gob.osinergmin.fise.bean;

import gob.osinergmin.fise.domain.AdmEmpresa;
import gob.osinergmin.fise.domain.FiseGrupoInformacion;

import java.math.BigDecimal;
import java.util.List;

public class ResumenCostoActividadBean {
	
	/**********Datos de busqueda***************/
	private List<AdmEmpresa> listaEmpresas;
	
	private List<FiseGrupoInformacion> listaGrupoInf;
	private String idGrupoInf;
	
	private String codEmpresaBusq;		
	private String optionFormato;	
	private String grupoInfBusq;		
	private boolean admin;		
	
	//varibales para los reportes
	private String desEmpresa;	
	private String periodo;
	private String tipoFormato;
	
	//F14A
	private String itemA;
	private String desCostoA;//descripcion de la columna de costos
	private String desCostoUnitA;//descripcion de la columna de costos unitarios		
	private BigDecimal costUniAR;//costo unitario tanto para emp con glp formato 14A rural 
	private BigDecimal costUniAP;//costo unitario tanto para emp con glp formato 14A provincia
	private BigDecimal costUniAL;//costo unitario tanto para emp con glp formato 14A lima
	
	//F14B
	private String itemB;
	private String desCostoB;//descripcion de la columna de costos
	private String desCostoUnitB;//descripcion de la columna de costos unitarios	
	private BigDecimal costUniBR;//costo unitario tanto para los 7 items formato 14A rural 
	private BigDecimal costUniBP;//costo unitario tanto para 7 items formato 14A provincia
	private BigDecimal costUniBL;//costo unitario tanto para 7 items formato 14A lima
	
	
	
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
	public boolean isAdmin() {
		return admin;
	}
	public void setAdmin(boolean admin) {
		this.admin = admin;
	}
	public String getDesEmpresa() {
		return desEmpresa;
	}
	public void setDesEmpresa(String desEmpresa) {
		this.desEmpresa = desEmpresa;
	}
	public String getPeriodo() {
		return periodo;
	}
	public void setPeriodo(String periodo) {
		this.periodo = periodo;
	}
	public String getItemA() {
		return itemA;
	}
	public void setItemA(String itemA) {
		this.itemA = itemA;
	}
	public String getDesCostoA() {
		return desCostoA;
	}
	public void setDesCostoA(String desCostoA) {
		this.desCostoA = desCostoA;
	}
	public String getDesCostoUnitA() {
		return desCostoUnitA;
	}
	public void setDesCostoUnitA(String desCostoUnitA) {
		this.desCostoUnitA = desCostoUnitA;
	}	
	public String getItemB() {
		return itemB;
	}
	public void setItemB(String itemB) {
		this.itemB = itemB;
	}
	public String getDesCostoB() {
		return desCostoB;
	}
	public void setDesCostoB(String desCostoB) {
		this.desCostoB = desCostoB;
	}
	public String getDesCostoUnitB() {
		return desCostoUnitB;
	}
	public void setDesCostoUnitB(String desCostoUnitB) {
		this.desCostoUnitB = desCostoUnitB;
	}
	public BigDecimal getCostUniAR() {
		return costUniAR;
	}
	public void setCostUniAR(BigDecimal costUniAR) {
		this.costUniAR = costUniAR;
	}
	public BigDecimal getCostUniAP() {
		return costUniAP;
	}
	public void setCostUniAP(BigDecimal costUniAP) {
		this.costUniAP = costUniAP;
	}
	public BigDecimal getCostUniAL() {
		return costUniAL;
	}
	public void setCostUniAL(BigDecimal costUniAL) {
		this.costUniAL = costUniAL;
	}
	public BigDecimal getCostUniBR() {
		return costUniBR;
	}
	public void setCostUniBR(BigDecimal costUniBR) {
		this.costUniBR = costUniBR;
	}
	public BigDecimal getCostUniBP() {
		return costUniBP;
	}
	public void setCostUniBP(BigDecimal costUniBP) {
		this.costUniBP = costUniBP;
	}
	public BigDecimal getCostUniBL() {
		return costUniBL;
	}
	public void setCostUniBL(BigDecimal costUniBL) {
		this.costUniBL = costUniBL;
	}
	public String getTipoFormato() {
		return tipoFormato;
	}
	public void setTipoFormato(String tipoFormato) {
		this.tipoFormato = tipoFormato;
	}
	
}
