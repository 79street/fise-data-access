package gob.osinergmin.fise.domain;

import java.io.Serializable;

import javax.persistence.*;

import java.util.Date;


/**
 * The persistent class for the FISE_FORMATO_12B_D_OBS database table.
 * 
 */
@Entity
@Table(name="FISE_FORMATO_12B_D_OBS")
@NamedQuery(name="FiseFormato12BDOb.findAll", query="SELECT f FROM FiseFormato12BDOb f")
public class FiseFormato12BDOb implements Serializable {
	private static final long serialVersionUID = 1L;

	@EmbeddedId
	private FiseFormato12BDObPK id;

	@Temporal(TemporalType.TIMESTAMP)
	@Column(name="FECHA_ACTUALIZACION")
	private Date fechaActualizacion;

	@Temporal(TemporalType.TIMESTAMP)
	@Column(name="FECHA_CREACION")
	private Date fechaCreacion;

	/*@Column(name="ID_OBSERVACION")
	private String idObservacion;*/

	@Column(name="TERMINAL_ACTUALIZACION")
	private String terminalActualizacion;

	@Column(name="TERMINAL_CREACION")
	private String terminalCreacion;

	@Column(name="USUARIO_ACTUALIZACION")
	private String usuarioActualizacion;

	@Column(name="USUARIO_CREACION")
	private String usuarioCreacion;

	//bi-directional many-to-one association to FiseFormato12bD
	//@Transient
	@ManyToOne
	@JoinColumns({
		@JoinColumn(name="ANO_EJECUCION_GASTO", referencedColumnName="ANO_EJECUCION_GASTO",nullable=false, insertable=false, updatable=false),
		@JoinColumn(name="ANO_PRESENTACION", referencedColumnName="ANO_PRESENTACION",nullable=false, insertable=false, updatable=false),
		@JoinColumn(name="COD_EMPRESA", referencedColumnName="COD_EMPRESA",nullable=false, insertable=false, updatable=false),
		@JoinColumn(name="ETAPA", referencedColumnName="ETAPA",nullable=false, insertable=false, updatable=false),
		@JoinColumn(name="ID_ZONA_BENEF", referencedColumnName="ID_ZONA_BENEF",nullable=false, insertable=false, updatable=false),
		@JoinColumn(name="MES_EJECUCION_GASTO", referencedColumnName="MES_EJECUCION_GASTO",nullable=false, insertable=false, updatable=false),
		@JoinColumn(name="MES_PRESENTACION", referencedColumnName="MES_PRESENTACION",nullable=false, insertable=false, updatable=false)
		})
	private FiseFormato12BD fiseFormato12BD;
	
	//bi-directional many-to-one association to FiseObservacion
	@ManyToOne
	@JoinColumn(name="ID_OBSERVACION")//insertable=false, updatable=false
	private FiseObservacion fiseObservacion; 

	public FiseFormato12BDOb() {
	}

	public FiseFormato12BDObPK getId() {
		return this.id;
	}

	public void setId(FiseFormato12BDObPK id) {
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

	public FiseFormato12BD getFiseFormato12BD() {
		return this.fiseFormato12BD;
	}

	public void setFiseFormato12BD(FiseFormato12BD fiseFormato12BD) {
		this.fiseFormato12BD = fiseFormato12BD;
	}

	public FiseObservacion getFiseObservacion() {
		return fiseObservacion;
	}

	public void setFiseObservacion(FiseObservacion fiseObservacion) {
		this.fiseObservacion = fiseObservacion;
	}
	
	

}