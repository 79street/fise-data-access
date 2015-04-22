package gob.osinergmin.fise.bean;

import gob.osinergmin.fise.domain.AdmEmpresa;
import gob.osinergmin.fise.domain.FiseGrupoInformacion;

import java.util.List;

public class ResumenObsBean {
	
	/**********Datos de busqueda***************/
	private List<AdmEmpresa> listaEmpresas;
	
	private List<FiseGrupoInformacion> listaGrupoInf;
	private String idGrupoInf;
	
	private String codEmpresaBusq;		
	private String optionFormato;	
	private String grupoInfBusq;
	private String etapaMBusq;//etapa para los formatos mensuales
	private String etapaBBusq;//etapa para los formatos bienales
	private String desMes;	
	private boolean admin;	
	
	
	private String desGrupoInf;//descripcion del grupo de informacion para el reporte
	private String tipoFormato;	
	private String optionBienal;	
	private String optionMensual;
	
	/***para el reporte de observaciones*******/
	private String codEmpresa;
	private String desEmpresa;	
	private String periodo;//anio y mes declarado
	private String formato;//nombre del formato para mostrar
	private String etapa;//la etapa en la se encuentra el formato
	private String usuario;//usuario que reporta la informacion
	private String fecha; //fecha que reporta la informacion el usuario
	
	private String codigo;//codigo de la observacion
	private String descripcion;//descripcion de la observacion
	private String descZonaBenef;//desc de la zona beneficiera
	private String descCodSectorTipico;	//desc del sector tipico
	private String nroItemEtapa; //numero de la etapa
	private String descEtapaEjecucion;//desc de la etapa de ejecucion
	
		
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
	public String getOptionBienal() {
		return optionBienal;
	}
	public void setOptionBienal(String optionBienal) {
		this.optionBienal = optionBienal;
	}	
	public String getOptionMensual() {
		return optionMensual;
	}
	public void setOptionMensual(String optionMensual) {
		this.optionMensual = optionMensual;
	}	
	public String getDesGrupoInf() {
		return desGrupoInf;
	}
	public void setDesGrupoInf(String desGrupoInf) {
		this.desGrupoInf = desGrupoInf;
	}	
	public String getTipoFormato() {
		return tipoFormato;
	}
	public void setTipoFormato(String tipoFormato) {
		this.tipoFormato = tipoFormato;
	}
	public String getEtapaMBusq() {
		return etapaMBusq;
	}
	public void setEtapaMBusq(String etapaMBusq) {
		this.etapaMBusq = etapaMBusq;
	}
	public String getEtapaBBusq() {
		return etapaBBusq;
	}
	public void setEtapaBBusq(String etapaBBusq) {
		this.etapaBBusq = etapaBBusq;
	}
	public String getFormato() {
		return formato;
	}
	public void setFormato(String formato) {
		this.formato = formato;
	}
	public String getEtapa() {
		return etapa;
	}
	public void setEtapa(String etapa) {
		this.etapa = etapa;
	}
	public String getUsuario() {
		return usuario;
	}
	public void setUsuario(String usuario) {
		this.usuario = usuario;
	}
	public String getFecha() {
		return fecha;
	}
	public void setFecha(String fecha) {
		this.fecha = fecha;
	}
	public String getCodigo() {
		return codigo;
	}
	public void setCodigo(String codigo) {
		this.codigo = codigo;
	}
	public String getDescripcion() {
		return descripcion;
	}
	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}
	public String getDescZonaBenef() {
		return descZonaBenef;
	}
	public void setDescZonaBenef(String descZonaBenef) {
		this.descZonaBenef = descZonaBenef;
	}
	public String getDescCodSectorTipico() {
		return descCodSectorTipico;
	}
	public void setDescCodSectorTipico(String descCodSectorTipico) {
		this.descCodSectorTipico = descCodSectorTipico;
	}
	public String getNroItemEtapa() {
		return nroItemEtapa;
	}
	public void setNroItemEtapa(String nroItemEtapa) {
		this.nroItemEtapa = nroItemEtapa;
	}
	public String getDescEtapaEjecucion() {
		return descEtapaEjecucion;
	}
	public void setDescEtapaEjecucion(String descEtapaEjecucion) {
		this.descEtapaEjecucion = descEtapaEjecucion;
	}
	public String getCodEmpresa() {
		return codEmpresa;
	}
	public void setCodEmpresa(String codEmpresa) {
		this.codEmpresa = codEmpresa;
	}	
}
