package gob.osinergmin.fise.bean;

import gob.osinergmin.fise.domain.AdmEmpresa;

import java.io.Serializable;
import java.util.List;

public class HistoricoCostosBean implements Serializable {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	/**********Datos de busqueda***************/
	private List<AdmEmpresa> listaEmpresas;
	
	private String codEmpresaBusq;		
	private String formatoBusq;
	
	private boolean admin;	
	
	private String periodo;
	private String valor;
	private String nroBeneficiarios;
	
	private String cadenaValorVariacion;
	//datos de auditoria
	private String usuario;
	private String terminal;
	
	private String titulo1;
	private String titulo2;
	private String titulo3;
	private String formato;
	
	public List<AdmEmpresa> getListaEmpresas() {
		return listaEmpresas;
	}
	public void setListaEmpresas(List<AdmEmpresa> listaEmpresas) {
		this.listaEmpresas = listaEmpresas;
	}
	public String getCodEmpresaBusq() {
		return codEmpresaBusq;
	}
	public void setCodEmpresaBusq(String codEmpresaBusq) {
		this.codEmpresaBusq = codEmpresaBusq;
	}
	public String getFormatoBusq() {
		return formatoBusq;
	}
	public void setFormatoBusq(String formatoBusq) {
		this.formatoBusq = formatoBusq;
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
	public String getValor() {
		return valor;
	}
	public void setValor(String valor) {
		this.valor = valor;
	}
	public String getNroBeneficiarios() {
		return nroBeneficiarios;
	}
	public void setNroBeneficiarios(String nroBeneficiarios) {
		this.nroBeneficiarios = nroBeneficiarios;
	}
	public String getCadenaValorVariacion() {
		return cadenaValorVariacion;
	}
	public void setCadenaValorVariacion(String cadenaValorVariacion) {
		this.cadenaValorVariacion = cadenaValorVariacion;
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
	public String getTitulo1() {
		return titulo1;
	}
	public void setTitulo1(String titulo1) {
		this.titulo1 = titulo1;
	}
	public String getTitulo2() {
		return titulo2;
	}
	public void setTitulo2(String titulo2) {
		this.titulo2 = titulo2;
	}
	public String getFormato() {
		return formato;
	}
	public void setFormato(String formato) {
		this.formato = formato;
	}
	public String getTitulo3() {
		return titulo3;
	}
	public void setTitulo3(String titulo3) {
		this.titulo3 = titulo3;
	}
	
	
}
