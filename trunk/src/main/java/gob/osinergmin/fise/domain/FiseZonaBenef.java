package gob.osinergmin.fise.domain;

import java.io.Serializable;
import javax.persistence.*;
import java.util.Date;
import java.util.List;


/**
 * The persistent class for the FISE_ZONA_BENEF database table.
 * 
 */
@Entity
@Table(name="FISE_ZONA_BENEF", schema="FISE")
@NamedQuery(name="FiseZonaBenef.findAll", query="SELECT f FROM FiseZonaBenef f")
public class FiseZonaBenef implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@Column(name="ID_ZONA_BENEF")
	private long idZonaBenef;

	private String descripcion;

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

	//bi-directional many-to-one association to FiseFormato12AD
	/*@OneToMany(mappedBy="fiseZonaBenef")
	private List<FiseFormato12AD> fiseFormato12ADs;*/

	public FiseZonaBenef() {
	}

	public long getIdZonaBenef() {
		return this.idZonaBenef;
	}

	public void setIdZonaBenef(long idZonaBenef) {
		this.idZonaBenef = idZonaBenef;
	}

	public String getDescripcion() {
		return this.descripcion;
	}

	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
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

	/*public List<FiseFormato12AD> getFiseFormato12ADs() {
		return this.fiseFormato12ADs;
	}

	public void setFiseFormato12ADs(List<FiseFormato12AD> fiseFormato12ADs) {
		this.fiseFormato12ADs = fiseFormato12ADs;
	}

	public FiseFormato12AD addFiseFormato12AD(FiseFormato12AD fiseFormato12AD) {
		getFiseFormato12ADs().add(fiseFormato12AD);
		fiseFormato12AD.setFiseZonaBenef(this);

		return fiseFormato12AD;
	}

	public FiseFormato12AD removeFiseFormato12AD(FiseFormato12AD fiseFormato12AD) {
		getFiseFormato12ADs().remove(fiseFormato12AD);
		fiseFormato12AD.setFiseZonaBenef(null);

		return fiseFormato12AD;
	}*/

}