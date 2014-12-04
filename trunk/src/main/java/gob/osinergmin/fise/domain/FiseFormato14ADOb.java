package gob.osinergmin.fise.domain;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.persistence.Transient;


/**
 * The persistent class for the FISE_FORMATO_14A_D_OBS database table.
 * 
 */
@Entity
@Table(name="FISE_FORMATO_14A_D_OBS")
@NamedQuery(name="FiseFormato14ADOb.findAll", query="SELECT f FROM FiseFormato14ADOb f")
public class FiseFormato14ADOb implements Serializable {
	private static final long serialVersionUID = 1L;

	@EmbeddedId
	private FiseFormato14ADObPK id;

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

	//bi-directional many-to-one association to FiseFormato14AD
	@Transient
	@ManyToOne
	/*@JoinColumns({
		@JoinColumn(name="ANO_FIN_VIGENCIA", referencedColumnName="ANO_FIN_VIGENCIA"),
		@JoinColumn(name="ANO_INICIO_VIGENCIA", referencedColumnName="ANO_INICIO_VIGENCIA"),
		@JoinColumn(name="ANO_PRESENTACION", referencedColumnName="ANO_PRESENTACION"),
		@JoinColumn(name="COD_EMPRESA", referencedColumnName="COD_EMPRESA"),
		@JoinColumn(name="ETAPA", referencedColumnName="ETAPA"),
		@JoinColumn(name="ID_ZONA_BENEF", referencedColumnName="ID_ZONA_BENEF"),
		@JoinColumn(name="MES_PRESENTACION", referencedColumnName="MES_PRESENTACION")
		})*/
	private FiseFormato14AD fiseFormato14AD;

	@ManyToOne
	@JoinColumn(name="ID_OBSERVACION")
	private FiseObservacion fiseObservacion;
	
	public FiseFormato14ADOb() {
	}

	public FiseFormato14ADObPK getId() {
		return this.id;
	}

	public void setId(FiseFormato14ADObPK id) {
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

	public FiseFormato14AD getFiseFormato14AD() {
		return this.fiseFormato14AD;
	}

	public void setFiseFormato14AD(FiseFormato14AD fiseFormato14AD) {
		this.fiseFormato14AD = fiseFormato14AD;
	}

	public FiseObservacion getFiseObservacion() {
		return fiseObservacion;
	}

	public void setFiseObservacion(FiseObservacion fiseObservacion) {
		this.fiseObservacion = fiseObservacion;
	}
	

}