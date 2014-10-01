package gob.osinergmin.fise.domain;

import java.io.Serializable;

import javax.persistence.*;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;


/**
 * The persistent class for the FISE_GRUPO_INFORMACION database table.
 * 
 */
@Entity
@Table(name="FISE_GRUPO_INFORMACION", schema="FISE")
@NamedQuery(name="FiseGrupoInformacion.findAll", query="SELECT f FROM FiseGrupoInformacion f")
public class FiseGrupoInformacion implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@Column(name="ID_GRUPO_INFORMACION")
	private long idGrupoInformacion;

	@Column(name="ANO_PRESENTACION")
	private long anoPresentacion;

	private String descripcion;

	private BigDecimal estado;

	@Temporal(TemporalType.TIMESTAMP)
	@Column(name="FECHA_ACTUALIZACION")
	private Date fechaActualizacion;

	@Temporal(TemporalType.TIMESTAMP)
	@Column(name="FECHA_CREACION")
	private Date fechaCreacion;

	@Column(name="MES_PRESENTACION")
	private long mesPresentacion;

	@Column(name="TERMINAL_ACTUALIZACION")
	private String terminalActualizacion;

	@Column(name="TERMINAL_CREACION")
	private String terminalCreacion;

	@Column(name="USUARIO_ACTUALIZACION")
	private String usuarioActualizacion;

	@Column(name="USUARIO_CREACION")
	private String usuarioCreacion;

	//bi-directional many-to-one association to FiseFormato12AC
	@OneToMany(mappedBy="fiseGrupoInformacion")
	private List<FiseFormato12AC> fiseFormato12ACs;

	public FiseGrupoInformacion() {
	}

	public long getIdGrupoInformacion() {
		return this.idGrupoInformacion;
	}

	public void setIdGrupoInformacion(long idGrupoInformacion) {
		this.idGrupoInformacion = idGrupoInformacion;
	}

	public long getAnoPresentacion() {
		return this.anoPresentacion;
	}

	public void setAnoPresentacion(long anoPresentacion) {
		this.anoPresentacion = anoPresentacion;
	}

	public String getDescripcion() {
		return this.descripcion;
	}

	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}

	public BigDecimal getEstado() {
		return this.estado;
	}

	public void setEstado(BigDecimal estado) {
		this.estado = estado;
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

	public long getMesPresentacion() {
		return this.mesPresentacion;
	}

	public void setMesPresentacion(long mesPresentacion) {
		this.mesPresentacion = mesPresentacion;
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

	public List<FiseFormato12AC> getFiseFormato12ACs() {
		return this.fiseFormato12ACs;
	}

	public void setFiseFormato12ACs(List<FiseFormato12AC> fiseFormato12ACs) {
		this.fiseFormato12ACs = fiseFormato12ACs;
	}

	public FiseFormato12AC addFiseFormato12AC(FiseFormato12AC fiseFormato12AC) {
		getFiseFormato12ACs().add(fiseFormato12AC);
		fiseFormato12AC.setFiseGrupoInformacion(this);

		return fiseFormato12AC;
	}

	public FiseFormato12AC removeFiseFormato12AC(FiseFormato12AC fiseFormato12AC) {
		getFiseFormato12ACs().remove(fiseFormato12AC);
		fiseFormato12AC.setFiseGrupoInformacion(null);

		return fiseFormato12AC;
	}

}