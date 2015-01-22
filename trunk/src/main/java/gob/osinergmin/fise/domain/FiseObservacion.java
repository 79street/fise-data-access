package gob.osinergmin.fise.domain;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;


/**
 * The persistent class for the FISE_OBSERVACION database table.
 * 
 */
@Entity
@Table(name="FISE_OBSERVACION")
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
	
	private String origen;

	//bi-directional many-to-one association to FiseFormato12aDOb
	@OneToMany(mappedBy="fiseObservacion")
	private List<FiseFormato12ADOb> fiseFormato12aDObs;

	//bi-directional many-to-one association to FiseFormato12bDOb
	@OneToMany(mappedBy="fiseObservacion")
	private List<FiseFormato12BDOb> fiseFormato12bDObs;

	//bi-directional many-to-one association to FiseFormato12cDOb
	@OneToMany(mappedBy="fiseObservacion")
	private List<FiseFormato12CDOb> fiseFormato12cDObs;

	//bi-directional many-to-one association to FiseFormato12dDOb
	@OneToMany(mappedBy="fiseObservacion")
	private List<FiseFormato12DDOb> fiseFormato12dDObs;

	//bi-directional many-to-one association to FiseFormato13aDOb
	@OneToMany(mappedBy="fiseObservacion")
	private List<FiseFormato13ADOb> fiseFormato13aDObs;

	//bi-directional many-to-one association to FiseFormato14aDOb
	@OneToMany(mappedBy="fiseObservacion")
	private List<FiseFormato14ADOb> fiseFormato14aDObs;

	//bi-directional many-to-one association to FiseFormato14bDOb
	@OneToMany(mappedBy="fiseObservacion")
	private List<FiseFormato14BDOb> fiseFormato14bDObs;

	//bi-directional many-to-one association to FiseFormato14cDOb
	@OneToMany(mappedBy="fiseObservacion")
	private List<FiseFormato14CDOb> fiseFormato14cDObs;

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

	public List<FiseFormato12ADOb> getFiseFormato12aDObs() {
		return this.fiseFormato12aDObs;
	}

	public void setFiseFormato12aDObs(List<FiseFormato12ADOb> fiseFormato12aDObs) {
		this.fiseFormato12aDObs = fiseFormato12aDObs;
	}

	public FiseFormato12ADOb addFiseFormato12aDOb(FiseFormato12ADOb fiseFormato12aDOb) {
		getFiseFormato12aDObs().add(fiseFormato12aDOb);
		fiseFormato12aDOb.setFiseObservacion(this);

		return fiseFormato12aDOb;
	}

	public FiseFormato12ADOb removeFiseFormato12aDOb(FiseFormato12ADOb fiseFormato12aDOb) {
		getFiseFormato12aDObs().remove(fiseFormato12aDOb);
		fiseFormato12aDOb.setFiseObservacion(null);

		return fiseFormato12aDOb;
	}

	/*public List<FiseFormato12bDOb> getFiseFormato12bDObs() {
		return this.fiseFormato12bDObs;
	}

	public void setFiseFormato12bDObs(List<FiseFormato12bDOb> fiseFormato12bDObs) {
		this.fiseFormato12bDObs = fiseFormato12bDObs;
	}

	public FiseFormato12bDOb addFiseFormato12bDOb(FiseFormato12bDOb fiseFormato12bDOb) {
		getFiseFormato12bDObs().add(fiseFormato12bDOb);
		fiseFormato12bDOb.setFiseObservacion(this);

		return fiseFormato12bDOb;
	}

	public FiseFormato12bDOb removeFiseFormato12bDOb(FiseFormato12bDOb fiseFormato12bDOb) {
		getFiseFormato12bDObs().remove(fiseFormato12bDOb);
		fiseFormato12bDOb.setFiseObservacion(null);

		return fiseFormato12bDOb;
	}

	public List<FiseFormato12cDOb> getFiseFormato12cDObs() {
		return this.fiseFormato12cDObs;
	}

	public void setFiseFormato12cDObs(List<FiseFormato12cDOb> fiseFormato12cDObs) {
		this.fiseFormato12cDObs = fiseFormato12cDObs;
	}

	public FiseFormato12cDOb addFiseFormato12cDOb(FiseFormato12cDOb fiseFormato12cDOb) {
		getFiseFormato12cDObs().add(fiseFormato12cDOb);
		fiseFormato12cDOb.setFiseObservacion(this);

		return fiseFormato12cDOb;
	}

	public FiseFormato12cDOb removeFiseFormato12cDOb(FiseFormato12cDOb fiseFormato12cDOb) {
		getFiseFormato12cDObs().remove(fiseFormato12cDOb);
		fiseFormato12cDOb.setFiseObservacion(null);

		return fiseFormato12cDOb;
	}

	public List<FiseFormato12dDOb> getFiseFormato12dDObs() {
		return this.fiseFormato12dDObs;
	}

	public void setFiseFormato12dDObs(List<FiseFormato12dDOb> fiseFormato12dDObs) {
		this.fiseFormato12dDObs = fiseFormato12dDObs;
	}

	public FiseFormato12dDOb addFiseFormato12dDOb(FiseFormato12dDOb fiseFormato12dDOb) {
		getFiseFormato12dDObs().add(fiseFormato12dDOb);
		fiseFormato12dDOb.setFiseObservacion(this);

		return fiseFormato12dDOb;
	}

	public FiseFormato12dDOb removeFiseFormato12dDOb(FiseFormato12dDOb fiseFormato12dDOb) {
		getFiseFormato12dDObs().remove(fiseFormato12dDOb);
		fiseFormato12dDOb.setFiseObservacion(null);

		return fiseFormato12dDOb;
	}*/

	public List<FiseFormato13ADOb> getFiseFormato13aDObs() {
		return this.fiseFormato13aDObs;
	}

	public void setFiseFormato13aDObs(List<FiseFormato13ADOb> fiseFormato13aDObs) {
		this.fiseFormato13aDObs = fiseFormato13aDObs;
	}

	public FiseFormato13ADOb addFiseFormato13aDOb(FiseFormato13ADOb fiseFormato13aDOb) {
		getFiseFormato13aDObs().add(fiseFormato13aDOb);
		fiseFormato13aDOb.setFiseObservacion(this);

		return fiseFormato13aDOb;
	}

	public FiseFormato13ADOb removeFiseFormato13aDOb(FiseFormato13ADOb fiseFormato13aDOb) {
		getFiseFormato13aDObs().remove(fiseFormato13aDOb);
		fiseFormato13aDOb.setFiseObservacion(null);

		return fiseFormato13aDOb;
	}

	public List<FiseFormato14ADOb> getFiseFormato14aDObs() {
		return this.fiseFormato14aDObs;
	}

	public void setFiseFormato14aDObs(List<FiseFormato14ADOb> fiseFormato14aDObs) {
		this.fiseFormato14aDObs = fiseFormato14aDObs;
	}

	public FiseFormato14ADOb addFiseFormato14aDOb(FiseFormato14ADOb fiseFormato14aDOb) {
		getFiseFormato14aDObs().add(fiseFormato14aDOb);
		fiseFormato14aDOb.setFiseObservacion(this);

		return fiseFormato14aDOb;
	}

	public FiseFormato14ADOb removeFiseFormato14aDOb(FiseFormato14ADOb fiseFormato14aDOb) {
		getFiseFormato14aDObs().remove(fiseFormato14aDOb);
		fiseFormato14aDOb.setFiseObservacion(null);

		return fiseFormato14aDOb;
	}

	public List<FiseFormato14BDOb> getFiseFormato14bDObs() {
		return this.fiseFormato14bDObs;
	}

	public void setFiseFormato14BDObs(List<FiseFormato14BDOb> fiseFormato14bDObs) {
		this.fiseFormato14bDObs = fiseFormato14bDObs;
	}

	public FiseFormato14BDOb addFiseFormato14bDOb(FiseFormato14BDOb fiseFormato14bDOb) {
		getFiseFormato14bDObs().add(fiseFormato14bDOb);
		fiseFormato14bDOb.setFiseObservacion(this);

		return fiseFormato14bDOb;
	}

	public FiseFormato14BDOb removeFiseFormato14bDOb(FiseFormato14BDOb fiseFormato14bDOb) {
		getFiseFormato14bDObs().remove(fiseFormato14bDOb);
		fiseFormato14bDOb.setFiseObservacion(null);

		return fiseFormato14bDOb;
	}

	public List<FiseFormato14CDOb> getFiseFormato14cDObs() {
		return this.fiseFormato14cDObs;
	}

	public void setFiseFormato14cDObs(List<FiseFormato14CDOb> fiseFormato14cDObs) {
		this.fiseFormato14cDObs = fiseFormato14cDObs;
	}

	public FiseFormato14CDOb addFiseFormato14cDOb(FiseFormato14CDOb fiseFormato14cDOb) {
		getFiseFormato14cDObs().add(fiseFormato14cDOb);
		fiseFormato14cDOb.setFiseObservacion(this);

		return fiseFormato14cDOb;
	}

	public FiseFormato14CDOb removeFiseFormato14cDOb(FiseFormato14CDOb fiseFormato14cDOb) {
		getFiseFormato14cDObs().remove(fiseFormato14cDOb);
		fiseFormato14cDOb.setFiseObservacion(null);

		return fiseFormato14cDOb;
	}

	public String getOrigen() {
		return origen;
	}

	public void setOrigen(String origen) {
		this.origen = origen;
	}
	
	
	

}