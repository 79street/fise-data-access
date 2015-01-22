package gob.osinergmin.fise.bean;

import gob.osinergmin.fise.domain.AdmEmpresa;
import gob.osinergmin.fise.domain.FiseGrupoInformacion;

import java.util.List;

public class NotificacionBean {
	
	/**********Datos de busqueda***************/
	private List<AdmEmpresa> listaEmpresas;
	
	private List<FiseGrupoInformacion> listaGrupoInf;
	private String idGrupoInf;
	
	private String codEmpresaBusq;		
	private String optionFormato;
	private String etapaBusq;
	private String grupoInfBusq;
	
	private String descGrupoInf;//para envial email
	
	private String desEmpresa;	
	private String desMes;
	private String desMesEje;
	private String formato;
	
	private boolean admin;	
	
	//datos de auditoria
	private String usuario;
	private String terminal;
	
	/*******Datos de notificacion*************/
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
	
	/**para las pk de los detalles de cada formato*/
	private String idZona;
	private String desZona;
	private String etapaEjec;
	private String desEstapaEjec;
	private String itemEtapa;
	private String codUbigeo;
	private String codSector;
	private String desSector;
	private String idPersonal;
	private String desPersonal;
	
	/***para las pk de las observaciones de los formatos***/
	private String itemObs;
	private String idObservacion; //fk de la tabla observaciones
	private String desObservacion;//descripcion de la observacion
	private String origenObs;//si es manual o es automatico
	
	/*****Metodos get y set****/
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
	public String getOptionFormato() {
		return optionFormato;
	}
	public void setOptionFormato(String optionFormato) {
		this.optionFormato = optionFormato;
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
	public String getEtapaBusq() {
		return etapaBusq;
	}
	public void setEtapaBusq(String etapaBusq) {
		this.etapaBusq = etapaBusq;
	}
	public String getGrupoInfBusq() {
		return grupoInfBusq;
	}
	public void setGrupoInfBusq(String grupoInfBusq) {
		this.grupoInfBusq = grupoInfBusq;
	}
	public String getDescGrupoInf() {
		return descGrupoInf;
	}
	public void setDescGrupoInf(String descGrupoInf) {
		this.descGrupoInf = descGrupoInf;
	}
	public String getIdZona() {
		return idZona;
	}
	public void setIdZona(String idZona) {
		this.idZona = idZona;
	}
	public String getEtapaEjec() {
		return etapaEjec;
	}
	public void setEtapaEjec(String etapaEjec) {
		this.etapaEjec = etapaEjec;
	}
	public String getItemEtapa() {
		return itemEtapa;
	}
	public void setItemEtapa(String itemEtapa) {
		this.itemEtapa = itemEtapa;
	}
	public String getCodUbigeo() {
		return codUbigeo;
	}
	public void setCodUbigeo(String codUbigeo) {
		this.codUbigeo = codUbigeo;
	}
	public String getCodSector() {
		return codSector;
	}
	public void setCodSector(String codSector) {
		this.codSector = codSector;
	}
	public String getIdPersonal() {
		return idPersonal;
	}
	public void setIdPersonal(String idPersonal) {
		this.idPersonal = idPersonal;
	}
	public String getDesZona() {
		return desZona;
	}
	public void setDesZona(String desZona) {
		this.desZona = desZona;
	}
	public String getDesSector() {
		return desSector;
	}
	public void setDesSector(String desSector) {
		this.desSector = desSector;
	}
	public String getDesPersonal() {
		return desPersonal;
	}
	public void setDesPersonal(String desPersonal) {
		this.desPersonal = desPersonal;
	}
	public String getDesEstapaEjec() {
		return desEstapaEjec;
	}
	public void setDesEstapaEjec(String desEstapaEjec) {
		this.desEstapaEjec = desEstapaEjec;
	}
	public String getItemObs() {
		return itemObs;
	}
	public void setItemObs(String itemObs) {
		this.itemObs = itemObs;
	}
	public String getIdObservacion() {
		return idObservacion;
	}
	public void setIdObservacion(String idObservacion) {
		this.idObservacion = idObservacion;
	}
	public String getDesObservacion() {
		return desObservacion;
	}
	public void setDesObservacion(String desObservacion) {
		this.desObservacion = desObservacion;
	}
	public String getOrigenObs() {
		return origenObs;
	}
	public void setOrigenObs(String origenObs) {
		this.origenObs = origenObs;
	}	
	
}
