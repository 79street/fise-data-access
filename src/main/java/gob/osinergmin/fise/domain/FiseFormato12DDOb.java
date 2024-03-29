package gob.osinergmin.fise.domain;

import java.io.Serializable;

import javax.persistence.*;

import java.util.Date;


/**
 * The persistent class for the FISE_FORMATO_12D_D_OBS database table.
 * 
 */
@Entity
@Table(name="FISE_FORMATO_12D_D_OBS")
@NamedQuery(name="FiseFormato12DDOb.findAll", query="SELECT f FROM FiseFormato12DDOb f")
public class FiseFormato12DDOb implements Serializable {
	private static final long serialVersionUID = 1L;

	@EmbeddedId
	private FiseFormato12DDObPK id;

	@Temporal(TemporalType.TIMESTAMP)
	@Column(name="FECHA_ACTUALIZACION")
	private Date fechaActualizacion;

	@Temporal(TemporalType.TIMESTAMP)
	@Column(name="FECHA_CREACION")
	private Date fechaCreacion;

	@Column(name="TERMINAL_ACTUALIZACION")
	private String terminalActualizacion;

	@Column(name="TERMINAL_CREACION")
	private String terminalCreacion;

	@Column(name="USUARIO_ACTUALIZACION")
	private String usuarioActualizacion;

	@Column(name="USUARIO_CREACION")
	private String usuarioCreacion;

	//bi-directional many-to-one association to FiseFormato12DD
	@Transient
	@ManyToOne
	@JoinColumns({
		@JoinColumn(name="ANO_EJECUCION_GASTO", referencedColumnName="ANO_EJECUCION_GASTO"),
		@JoinColumn(name="ANO_PRESENTACION", referencedColumnName="ANO_PRESENTACION"),
		@JoinColumn(name="COD_EMPRESA", referencedColumnName="COD_EMPRESA"),
		@JoinColumn(name="ETAPA", referencedColumnName="ETAPA"),
		@JoinColumn(name="ETAPA_EJECUCION", referencedColumnName="ETAPA_EJECUCION"),
		@JoinColumn(name="MES_EJECUCION_GASTO", referencedColumnName="MES_EJECUCION_GASTO"),
		@JoinColumn(name="MES_PRESENTACION", referencedColumnName="MES_PRESENTACION"),
		@JoinColumn(name="NUMERO_ITEM_ETAPA", referencedColumnName="NUMERO_ITEM_ETAPA")
		})
	private FiseFormato12DD fiseFormato12DD;

	@ManyToOne
	@JoinColumn(name="ID_OBSERVACION")
	private FiseObservacion fiseObservacion;
	
	public FiseFormato12DDOb() {
	}

	public FiseFormato12DDObPK getId() {
		return this.id;
	}

	public void setId(FiseFormato12DDObPK id) {
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

	public FiseFormato12DD getFiseFormato12DD() {
		return this.fiseFormato12DD;
	}

	public void setFiseFormato12DD(FiseFormato12DD fiseFormato12DD) {
		this.fiseFormato12DD = fiseFormato12DD;
	}

	public FiseObservacion getFiseObservacion() {
		return fiseObservacion;
	}

	public void setFiseObservacion(FiseObservacion fiseObservacion) {
		this.fiseObservacion = fiseObservacion;
	}

}