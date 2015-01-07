package gob.osinergmin.fise.bean;

import gob.osinergmin.fise.domain.AdmEmpresa;
import gob.osinergmin.fise.domain.FiseGrupoInformacion;

import java.util.List;

public class LiquidacionBean {
	
	/**********Datos de busqueda***************/
	private List<AdmEmpresa> listaEmpresas;
	
	private List<FiseGrupoInformacion> listaGrupoInf;
	private String idGrupoInf;
	
	private String codEmpresaBusq;		
	private String optionFormato;	
	private String grupoInfBusq;
	
	private String desEmpresa;	
	private String desMes;
	private String desMesEje;
	private String formato;
	private String liquidado;	
	private String etapaReconocido;
	
	private boolean admin;	
	
	//datos de auditoria
	private String usuario;
	private String terminal;
	
	/*******Datos de liquidacion*************/
	private String correlativo;
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
	
	private String flagBusq;//para refrescar la grilla de la busqueda luedo de eliminar, liquiday y generar etapa
	
	/***para motivos de la liquidacion**/
	private String itemMotivo;//item del motivo 
	private String coMotivo;//correlativo del motivo
	private String descMotivo;//descripcion del motivo
	private String estadoMotivo;//estado del motivo
	private String itemMotivoEdit;
	private String correlativoEdit;
	
	
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
	public String getDesMesEje() {
		return desMesEje;
	}
	public void setDesMesEje(String desMesEje) {
		this.desMesEje = desMesEje;
	}
	public String getFormato() {
		return formato;
	}
	public void setFormato(String formato) {
		this.formato = formato;
	}
	public String getLiquidado() {
		return liquidado;
	}
	public void setLiquidado(String liquidado) {
		this.liquidado = liquidado;
	}
	public String getEtapaReconocido() {
		return etapaReconocido;
	}
	public void setEtapaReconocido(String etapaReconocido) {
		this.etapaReconocido = etapaReconocido;
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
	public String getCorrelativo() {
		return correlativo;
	}
	public void setCorrelativo(String correlativo) {
		this.correlativo = correlativo;
	}
	public String getFlagBusq() {
		return flagBusq;
	}
	public void setFlagBusq(String flagBusq) {
		this.flagBusq = flagBusq;
	}
	public String getItemMotivo() {
		return itemMotivo;
	}
	public void setItemMotivo(String itemMotivo) {
		this.itemMotivo = itemMotivo;
	}
	public String getCoMotivo() {
		return coMotivo;
	}
	public void setCoMotivo(String coMotivo) {
		this.coMotivo = coMotivo;
	}
	public String getDescMotivo() {
		return descMotivo;
	}
	public void setDescMotivo(String descMotivo) {
		this.descMotivo = descMotivo;
	}
	public String getEstadoMotivo() {
		return estadoMotivo;
	}
	public void setEstadoMotivo(String estadoMotivo) {
		this.estadoMotivo = estadoMotivo;
	}
	public String getItemMotivoEdit() {
		return itemMotivoEdit;
	}
	public void setItemMotivoEdit(String itemMotivoEdit) {
		this.itemMotivoEdit = itemMotivoEdit;
	}
	public String getCorrelativoEdit() {
		return correlativoEdit;
	}
	public void setCorrelativoEdit(String correlativoEdit) {
		this.correlativoEdit = correlativoEdit;
	}
	
	
	
}
