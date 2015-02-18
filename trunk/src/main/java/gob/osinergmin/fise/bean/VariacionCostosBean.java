package gob.osinergmin.fise.bean;

import gob.osinergmin.fise.domain.FiseGrupoInformacion;

import java.io.Serializable;
import java.util.List;
import java.util.Map;

public class VariacionCostosBean implements Serializable {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	/**********Datos de busqueda***************/
	private List<FiseGrupoInformacion> listaGrupoInfo;
	private Map<String, String> listaConceptos;
	private String idGrupoInfo;
	
	private String grupoInfoBusq;		
	private String formatoBusq;
	private String zonaBusq;
	private String conceptoBusq;
	private String etapaBusq;
	
	private boolean admin;	
	
	private String codEmpresa;
	private String descEmpresa;
	private String valor;
	
	private String cadenaValorVariacion;
	//datos de auditoria
	private String usuario;
	private String terminal;
	
	private String titulo1;
	private String titulo2;
	private String titulo3;
	
	public List<FiseGrupoInformacion> getListaGrupoInfo() {
		return listaGrupoInfo;
	}
	public void setListaGrupoInfo(List<FiseGrupoInformacion> listaGrupoInfo) {
		this.listaGrupoInfo = listaGrupoInfo;
	}
	public String getIdGrupoInfo() {
		return idGrupoInfo;
	}
	public void setIdGrupoInfo(String idGrupoInfo) {
		this.idGrupoInfo = idGrupoInfo;
	}
	public String getGrupoInfoBusq() {
		return grupoInfoBusq;
	}
	public void setGrupoInfoBusq(String grupoInfoBusq) {
		this.grupoInfoBusq = grupoInfoBusq;
	}
	public String getFormatoBusq() {
		return formatoBusq;
	}
	public void setFormatoBusq(String formatoBusq) {
		this.formatoBusq = formatoBusq;
	}
	public String getZonaBusq() {
		return zonaBusq;
	}
	public void setZonaBusq(String zonaBusq) {
		this.zonaBusq = zonaBusq;
	}
	public String getConceptoBusq() {
		return conceptoBusq;
	}
	public void setConceptoBusq(String conceptoBusq) {
		this.conceptoBusq = conceptoBusq;
	}
	public boolean isAdmin() {
		return admin;
	}
	public void setAdmin(boolean admin) {
		this.admin = admin;
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
	public Map<String, String> getListaConceptos() {
		return listaConceptos;
	}
	public void setListaConceptos(Map<String, String> listaConceptos) {
		this.listaConceptos = listaConceptos;
	}
	public String getCodEmpresa() {
		return codEmpresa;
	}
	public void setCodEmpresa(String codEmpresa) {
		this.codEmpresa = codEmpresa;
	}
	public String getDescEmpresa() {
		return descEmpresa;
	}
	public void setDescEmpresa(String descEmpresa) {
		this.descEmpresa = descEmpresa;
	}
	public String getValor() {
		return valor;
	}
	public void setValor(String valor) {
		this.valor = valor;
	}
	public String getCadenaValorVariacion() {
		return cadenaValorVariacion;
	}
	public void setCadenaValorVariacion(String cadenaValorVariacion) {
		this.cadenaValorVariacion = cadenaValorVariacion;
	}
	public String getEtapaBusq() {
		return etapaBusq;
	}
	public void setEtapaBusq(String etapaBusq) {
		this.etapaBusq = etapaBusq;
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
	public String getTitulo3() {
		return titulo3;
	}
	public void setTitulo3(String titulo3) {
		this.titulo3 = titulo3;
	}
	
	
}
