package gob.osinergmin.fise.bean;

import gob.osinergmin.fise.domain.AdmEmpresa;

import java.util.List;
import java.util.Map;

public class AutorizarReenvioBean {
	
	/**********Datos de actualizacion***************/
	private Map<Long,String> listaMes;
	private List<AdmEmpresa> listaEmpresas;
	private String codEmpresaBusq;
	private String anioPresBusq;
	private String mesPresBusq;
	private String formatoBusq;
	private String etapaBusq;
	
	private String desEmpresa;
	private String desMes;
	private String estado;
	private String formato;
	
	private boolean admin;	
	
	//datos de auditoria
	private String usuario;
	private String terminal;
	
	/*******Datos de actualizacion*******************/
	private String codEmpresa;
	private String anioPres;
	private String mesPres;
	private String etapa;
	//formato 12A, 12B
	private String anioEjec;
	private String mesEjec;
	//formato 14A,14B, 14C
	private String anioIniVig;
	private String anioFinVig;
	
	/*****Metodos get y set****/
	
	public String getCodEmpresaBusq() {
		return codEmpresaBusq;
	}
	public void setCodEmpresaBusq(String codEmpresaBusq) {
		this.codEmpresaBusq = codEmpresaBusq;
	}
	public String getAnioPresBusq() {
		return anioPresBusq;
	}
	public void setAnioPresBusq(String anioPresBusq) {
		this.anioPresBusq = anioPresBusq;
	}
	public String getMesPresBusq() {
		return mesPresBusq;
	}
	public void setMesPresBusq(String mesPresBusq) {
		this.mesPresBusq = mesPresBusq;
	}
	public String getFormatoBusq() {
		return formatoBusq;
	}
	public void setFormatoBusq(String formatoBusq) {
		this.formatoBusq = formatoBusq;
	}
	public String getEtapaBusq() {
		return etapaBusq;
	}
	public void setEtapaBusq(String etapaBusq) {
		this.etapaBusq = etapaBusq;
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
	public String getEstado() {
		return estado;
	}
	public void setEstado(String estado) {
		this.estado = estado;
	}
	public String getCodEmpresa() {
		return codEmpresa;
	}
	public void setCodEmpresa(String codEmpresa) {
		this.codEmpresa = codEmpresa;
	}
	public String getAnioPres() {
		return anioPres;
	}
	public void setAnioPres(String anioPres) {
		this.anioPres = anioPres;
	}
	public String getMesPres() {
		return mesPres;
	}
	public void setMesPres(String mesPres) {
		this.mesPres = mesPres;
	}
	public String getEtapa() {
		return etapa;
	}
	public void setEtapa(String etapa) {
		this.etapa = etapa;
	}
	public String getAnioEjec() {
		return anioEjec;
	}
	public void setAnioEjec(String anioEjec) {
		this.anioEjec = anioEjec;
	}
	public String getMesEjec() {
		return mesEjec;
	}
	public void setMesEjec(String mesEjec) {
		this.mesEjec = mesEjec;
	}
	public String getAnioIniVig() {
		return anioIniVig;
	}
	public void setAnioIniVig(String anioIniVig) {
		this.anioIniVig = anioIniVig;
	}
	public String getAnioFinVig() {
		return anioFinVig;
	}
	public void setAnioFinVig(String anioFinVig) {
		this.anioFinVig = anioFinVig;
	}
	public String getFormato() {
		return formato;
	}
	public void setFormato(String formato) {
		this.formato = formato;
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
	
	

}
