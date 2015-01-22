package gob.osinergmin.fise.domain;

import java.io.Serializable;
import javax.persistence.*;
import java.util.Date;


/**
 * The persistent class for the FISE_FORMATO_14C_D_OBS database table.
 * 
 */
@Entity
@Table(name="FISE_FORMATO_14C_D_OBS")
public class FiseFormato14CDOb implements Serializable {
	private static final long serialVersionUID = 1L;

	@EmbeddedId
	private FiseFormato14CDObPK id;

	@Temporal(TemporalType.DATE)
	@Column(name="FECHA_ACTUALIZACION")
	private Date fechaActualizacion;

	@Temporal(TemporalType.DATE)
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

	//bi-directional many-to-one association to FiseFormato14cD
	@ManyToOne(fetch=FetchType.LAZY)
	@JoinColumns({
		@JoinColumn(name="ANO_FIN_VIGENCIA", referencedColumnName="ANO_FIN_VIGENCIA",nullable=false, insertable=false, updatable=false),
		@JoinColumn(name="ANO_INICIO_VIGENCIA", referencedColumnName="ANO_INICIO_VIGENCIA",nullable=false, insertable=false, updatable=false),
		@JoinColumn(name="ANO_PRESENTACION", referencedColumnName="ANO_PRESENTACION",nullable=false, insertable=false, updatable=false),
		@JoinColumn(name="COD_EMPRESA", referencedColumnName="COD_EMPRESA",nullable=false, insertable=false, updatable=false),
		@JoinColumn(name="ETAPA", referencedColumnName="ETAPA",nullable=false, insertable=false, updatable=false),
		@JoinColumn(name="ID_TIP_PERSONAL", referencedColumnName="ID_TIP_PERSONAL",nullable=false, insertable=false, updatable=false),
		@JoinColumn(name="ID_ZONA_BENEF", referencedColumnName="ID_ZONA_BENEF",nullable=false, insertable=false, updatable=false),
		@JoinColumn(name="MES_PRESENTACION", referencedColumnName="MES_PRESENTACION",nullable=false, insertable=false, updatable=false)
		})
	private FiseFormato14CD fiseFormato14cD;

	//bi-directional many-to-one association to FiseObservacion
	@ManyToOne(fetch=FetchType.EAGER)
	//@ManyToOne
	//@JoinColumn(name="ID_OBSERVACION",nullable=false, insertable=false, updatable=false)
	@JoinColumn(name="ID_OBSERVACION")
	private FiseObservacion fiseObservacion;

	public FiseFormato14CDOb() {
	}

	public FiseFormato14CDObPK getId() {
		return this.id;
	}

	public void setId(FiseFormato14CDObPK id) {
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

	public FiseFormato14CD getFiseFormato14cD() {
		return this.fiseFormato14cD;
	}

	public void setFiseFormato14cD(FiseFormato14CD fiseFormato14cD) {
		this.fiseFormato14cD = fiseFormato14cD;
	}

	public FiseObservacion getFiseObservacion() {
		return this.fiseObservacion;
	}

	public void setFiseObservacion(FiseObservacion fiseObservacion) {
		this.fiseObservacion = fiseObservacion;
	}

}