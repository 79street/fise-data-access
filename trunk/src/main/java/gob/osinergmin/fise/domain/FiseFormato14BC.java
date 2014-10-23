package gob.osinergmin.fise.domain;

import java.io.Serializable;

import javax.persistence.*;

import java.util.Date;
import java.util.List;


/**
 * The persistent class for the FISE_FORMATO_14B_C database table.
 * 
 */
@Entity
@Table(name="FISE_FORMATO_14B_C", schema="FISE")
@NamedQuery(name="FiseFormato14BC.findAll", query="SELECT f FROM FiseFormato14BC f")
public class FiseFormato14BC implements Serializable {
	private static final long serialVersionUID = 1L;

	@EmbeddedId
	private FiseFormato14BCPK id;

	@Temporal(TemporalType.TIMESTAMP)
	@Column(name="FECHA_ACTUALIZACION")
	private Date fechaActualizacion;

	@Temporal(TemporalType.TIMESTAMP)
	@Column(name="FECHA_CREACION")
	private Date fechaCreacion;

	@Temporal(TemporalType.TIMESTAMP)
	@Column(name="FECHA_ENVIO_DEFINITIVO")
	private Date fechaEnvioDefinitivo;

	@Column(name="ID_GRUPO_INFORMACION")
	private java.math.BigDecimal idGrupoInformacion;

	@Column(name="NOMBRE_ARCHIVO_EXCEL")
	private String nombreArchivoExcel;

	@Column(name="NOMBRE_ARCHIVO_TEXTO")
	private String nombreArchivoTexto;

	@Column(name="TERMINAL_ACTUALIZACION")
	private String terminalActualizacion;

	@Column(name="TERMINAL_CREACION")
	private String terminalCreacion;

	@Column(name="USUARIO_ACTUALIZACION")
	private String usuarioActualizacion;

	@Column(name="USUARIO_CREACION")
	private String usuarioCreacion;

	//bi-directional many-to-one association to FiseFormato14BD
	@Transient
	@OneToMany(mappedBy="fiseFormato14BC", cascade={CascadeType.ALL})
	private List<FiseFormato14BD> fiseFormato14BDs;

	public FiseFormato14BC() {
	}

	public FiseFormato14BCPK getId() {
		return this.id;
	}

	public void setId(FiseFormato14BCPK id) {
		this.id = id;
	}

	public Date getFechaActualizacion() {
		return this.fechaActualizacion;
	}

	public void setFechaActualizacion(Date fechaActualizacion) {
		this.fechaActualizacion = fechaActualizacion;
	}

	public Date getFechaCreacion() {
		return this.fechaCreacion;
	}

	public void setFechaCreacion(Date fechaCreacion) {
		this.fechaCreacion = fechaCreacion;
	}

	public Date getFechaEnvioDefinitivo() {
		return this.fechaEnvioDefinitivo;
	}

	public void setFechaEnvioDefinitivo(Date fechaEnvioDefinitivo) {
		this.fechaEnvioDefinitivo = fechaEnvioDefinitivo;
	}

	public java.math.BigDecimal getIdGrupoInformacion() {
		return this.idGrupoInformacion;
	}

	public void setIdGrupoInformacion(java.math.BigDecimal idGrupoInformacion) {
		this.idGrupoInformacion = idGrupoInformacion;
	}

	public String getNombreArchivoExcel() {
		return this.nombreArchivoExcel;
	}

	public void setNombreArchivoExcel(String nombreArchivoExcel) {
		this.nombreArchivoExcel = nombreArchivoExcel;
	}

	public String getNombreArchivoTexto() {
		return this.nombreArchivoTexto;
	}

	public void setNombreArchivoTexto(String nombreArchivoTexto) {
		this.nombreArchivoTexto = nombreArchivoTexto;
	}

	public String getTerminalActualizacion() {
		return this.terminalActualizacion;
	}

	public void setTerminalActualizacion(String terminalActualizacion) {
		this.terminalActualizacion = terminalActualizacion;
	}

	public String getTerminalCreacion() {
		return this.terminalCreacion;
	}

	public void setTerminalCreacion(String terminalCreacion) {
		this.terminalCreacion = terminalCreacion;
	}

	public String getUsuarioActualizacion() {
		return this.usuarioActualizacion;
	}

	public void setUsuarioActualizacion(String usuarioActualizacion) {
		this.usuarioActualizacion = usuarioActualizacion;
	}

	public String getUsuarioCreacion() {
		return this.usuarioCreacion;
	}

	public void setUsuarioCreacion(String usuarioCreacion) {
		this.usuarioCreacion = usuarioCreacion;
	}

	public List<FiseFormato14BD> getFiseFormato14BDs() {
		return this.fiseFormato14BDs;
	}

	public void setFiseFormato14BDs(List<FiseFormato14BD> fiseFormato14BDs) {
		this.fiseFormato14BDs = fiseFormato14BDs;
	}

	public FiseFormato14BD addFiseFormato14BD(FiseFormato14BD fiseFormato14BD) {
		getFiseFormato14BDs().add(fiseFormato14BD);
		fiseFormato14BD.setFiseFormato14BC(this);

		return fiseFormato14BD;
	}

	public FiseFormato14BD removeFiseFormato14BD(FiseFormato14BD fiseFormato14BD) {
		getFiseFormato14BDs().remove(fiseFormato14BD);
		fiseFormato14BD.setFiseFormato14BC(null);

		return fiseFormato14BD;
	}

}