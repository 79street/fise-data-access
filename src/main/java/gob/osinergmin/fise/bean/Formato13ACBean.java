package gob.osinergmin.fise.bean;

import java.io.Serializable;

public class Formato13ACBean implements Serializable {

	private static final long serialVersionUID = 1L;
	
	private String codigoEmpresa;
	private long anioPresent = 0;
	private long mesPresent = 0;

	private String etapa;

	private long idGrupoInfo = 0;
		
	private String usuario;
	private String terminal;
	private String nombreArchivo;
	private String tipoArchivo;
	
	private String descEmpresa;
	private String descMesPresentacion;
	
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
	public String getEtapa() {
		return etapa;
	}
	public void setEtapa(String etapa) {
		this.etapa = etapa;
	}
	public long getIdGrupoInfo() {
		return idGrupoInfo;
	}
	public void setIdGrupoInfo(long idGrupoInfo) {
		this.idGrupoInfo = idGrupoInfo;
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
	public String getTipoArchivo() {
		return tipoArchivo;
	}
	public void setTipoArchivo(String tipoArchivo) {
		this.tipoArchivo = tipoArchivo;
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
	
	
}
