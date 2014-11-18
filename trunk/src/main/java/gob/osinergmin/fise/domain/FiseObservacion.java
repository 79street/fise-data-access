package gob.osinergmin.fise.domain;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;


/**
 * The persistent class for the FISE_OBSERVACION database table.
 * 
 */
@Entity
@Table(name="FISE_OBSERVACION", schema="FISE")
@NamedQuery(name="FiseObservacion.findAll", query="SELECT f FROM FiseObservacion f")
public class FiseObservacion implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@Column(name="ID_OBSERVACION")
	private String idObservacion;

	private String descripcion;

	@Temporal(TemporalType.TIMESTAMP)
	@Column(name="FECHA_ACTUALIZACION")
	private Date fechaActualizacion;

	@Temporal(TemporalType.TIMESTAMP)
	@Column(name="FECHA_CREACION")
	private Date fechaCreacion;

	private String fincanaosi;

	private String fincdeslo;

	@Column(name="TERMINAL_ACTUALIZACION")
	private String terminalActualizacion;

	@Column(name="TERMINAL_CREACION")
	private String terminalCreacion;

	@Column(name="USUARIO_ACTUALIZACION")
	private String usuarioActualizacion;

	@Column(name="USUARIO_CREACION")
	private String usuarioCreacion;

	//bi-directional many-to-one association to FiseFormato12ADOb
	@OneToMany(mappedBy="fiseObservacion")
	private List<FiseFormato12ADOb> fiseFormato12ADObs;
	
	//bi-directional many-to-one association to FiseFormato13ADOb
	@OneToMany(mappedBy="fiseObservacion")
	private List<FiseFormato13ADOb> fiseFormato13ADObs;
	
	//bi-directional many-to-one association to FiseFormato12ADOb
	@OneToMany(mappedBy="fiseObservacion")
	private List<FiseFormato14ADOb> fiseFormato14ADObs;
	
	//bi-directional many-to-one association to FiseFormato12ADOb
	@OneToMany(mappedBy="fiseObservacion")
	private List<FiseFormato14ADOb> fiseFormato14BDObs;

	public FiseObservacion() {
	}

	public String getIdObservacion() {
		return this.idObservacion;
	}

	public void setIdObservacion(String idObservacion) {
		this.idObservacion = idObservacion;
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

	public String getFincanaosi() {
		return this.fincanaosi;
	}

	public void setFincanaosi(String fincanaosi) {
		this.fincanaosi = fincanaosi;
	}

	public String getFincdeslo() {
		return this.fincdeslo;
	}

	public void setFincdeslo(String fincdeslo) {
		this.fincdeslo = fincdeslo;
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

	public List<FiseFormato12ADOb> getFiseFormato12ADObs() {
		return this.fiseFormato12ADObs;
	}

	public void setFiseFormato12ADObs(List<FiseFormato12ADOb> fiseFormato12ADObs) {
		this.fiseFormato12ADObs = fiseFormato12ADObs;
	}

	public List<FiseFormato14ADOb> getFiseFormato14ADObs() {
		return fiseFormato14ADObs;
	}

	public void setFiseFormato14ADObs(List<FiseFormato14ADOb> fiseFormato14ADObs) {
		this.fiseFormato14ADObs = fiseFormato14ADObs;
	}

	public List<FiseFormato14ADOb> getFiseFormato14BDObs() {
		return fiseFormato14BDObs;
	}

	public void setFiseFormato14BDObs(List<FiseFormato14ADOb> fiseFormato14BDObs) {
		this.fiseFormato14BDObs = fiseFormato14BDObs;
	}

	public FiseFormato12ADOb addFiseFormato12ADOb(FiseFormato12ADOb fiseFormato12ADOb) {
		getFiseFormato12ADObs().add(fiseFormato12ADOb);
		fiseFormato12ADOb.setFiseObservacion(this);

		return fiseFormato12ADOb;
	}

	public FiseFormato12ADOb removeFiseFormato12ADOb(FiseFormato12ADOb fiseFormato12ADOb) {
		getFiseFormato12ADObs().remove(fiseFormato12ADOb);
		fiseFormato12ADOb.setFiseObservacion(null);

		return fiseFormato12ADOb;
	}

	public List<FiseFormato13ADOb> getFiseFormato13ADObs() {
		return fiseFormato13ADObs;
	}

	public void setFiseFormato13ADObs(List<FiseFormato13ADOb> fiseFormato13ADObs) {
		this.fiseFormato13ADObs = fiseFormato13ADObs;
	}

}