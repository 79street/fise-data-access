package gob.osinergmin.fise.domain;

import java.io.Serializable;

import javax.persistence.*;

import java.util.Date;


/**
 * The persistent class for the FISE_FORMATO_13A_D_OBS database table.
 * 
 */
@Entity
@Table(name="FISE_FORMATO_13A_D_OBS")
@NamedQuery(name="FiseFormato13ADOb.findAll", query="SELECT f FROM FiseFormato13ADOb f")
public class FiseFormato13ADOb implements Serializable {
	private static final long serialVersionUID = 1L;

	@EmbeddedId
	private FiseFormato13ADObPK id;

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

	//bi-directional many-to-one association to FiseFormato13AD
	@Transient
	@ManyToOne(cascade={CascadeType.ALL})
	@JoinColumns({
		@JoinColumn(name="ANO_PRESENTACION", referencedColumnName="ANO_PRESENTACION"),
		@JoinColumn(name="COD_EMPRESA", referencedColumnName="COD_EMPRESA"),
		@JoinColumn(name="COD_SECTOR_TIPICO", referencedColumnName="COD_SECTOR_TIPICO"),
		@JoinColumn(name="COD_UBIGEO", referencedColumnName="COD_UBIGEO"),
		@JoinColumn(name="ETAPA", referencedColumnName="ETAPA"),
		@JoinColumn(name="ID_ZONA_BENEF", referencedColumnName="ID_ZONA_BENEF"),
		@JoinColumn(name="MES_PRESENTACION", referencedColumnName="MES_PRESENTACION")
		})
	private FiseFormato13AD fiseFormato13AD;

	@ManyToOne
	@JoinColumn(name="ID_OBSERVACION")
	private FiseObservacion fiseObservacion;
	
	public FiseFormato13ADOb() {
	}

	public FiseFormato13ADObPK getId() {
		return this.id;
	}

	public void setId(FiseFormato13ADObPK id) {
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

	public FiseFormato13AD getFiseFormato13AD() {
		return this.fiseFormato13AD;
	}

	public void setFiseFormato13AD(FiseFormato13AD fiseFormato13AD) {
		this.fiseFormato13AD = fiseFormato13AD;
	}

	public FiseObservacion getFiseObservacion() {
		return fiseObservacion;
	}

	public void setFiseObservacion(FiseObservacion fiseObservacion) {
		this.fiseObservacion = fiseObservacion;
	}

}