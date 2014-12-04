package gob.osinergmin.fise.domain;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.persistence.Transient;


/**
 * The persistent class for the FISE_FORMATO_14A_C database table.
 * 
 */
@Entity
@Table(name="FISE_FORMATO_14A_C")
@NamedQuery(name="FiseFormato14AC.findAll", query="SELECT f FROM FiseFormato14AC f")
public class FiseFormato14AC implements Serializable {
	private static final long serialVersionUID = 1L;

	@EmbeddedId
	private FiseFormato14ACPK id;

	@Temporal(TemporalType.TIMESTAMP)
	@Column(name="FECHA_ACTUALIZACION")
	private Date fechaActualizacion;

	@Temporal(TemporalType.TIMESTAMP)
	@Column(name="FECHA_CREACION")
	private Date fechaCreacion;

	//@Column(name="ID_GRUPO_INFORMACION")
	//private java.math.BigDecimal idGrupoInformacion;
	
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
	@OneToMany(mappedBy="fiseFormato14AC", cascade={CascadeType.ALL})
	private List<FiseFormato14AD> fiseFormato14ADs;
	
	//bi-directional many-to-one association to FiseGrupoInformacion
	@ManyToOne
	@JoinColumn(name="ID_GRUPO_INFORMACION")
	private FiseGrupoInformacion fiseGrupoInformacion;
	
	@Transient
	private String descEmpresa;
	@Transient
	private String descEstado;
	@Transient
	private String descMesPresentacion;
	@Transient
	private String descGrupoInformacion;
	
	@Column(name="NOMBRE_ARCHIVO_EXCEL")
	private String archivoExcel;
	@Column(name="NOMBRE_ARCHIVO_TEXTO")
	private String archivoTexto;
	
	@Temporal(TemporalType.TIMESTAMP)
	@Column(name="FECHA_ENVIO_DEFINITIVO")
	private Date fechaEnvioDefinitivo;

	public FiseFormato14AC() {
	}

	public FiseFormato14ACPK getId() {
		return this.id;
	}

	public void setId(FiseFormato14ACPK id) {
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

	/*public java.math.BigDecimal getIdGrupoInformacion() {
		return this.idGrupoInformacion;
	}

	public void setIdGrupoInformacion(java.math.BigDecimal idGrupoInformacion) {
		this.idGrupoInformacion = idGrupoInformacion;
	}*/

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

	public List<FiseFormato14AD> getFiseFormato14ADs() {
		return this.fiseFormato14ADs;
	}

	public void setFiseFormato14ADs(List<FiseFormato14AD> fiseFormato14ADs) {
		this.fiseFormato14ADs = fiseFormato14ADs;
	}

	public FiseFormato14AD addFiseFormato14AD(FiseFormato14AD fiseFormato14AD) {
		getFiseFormato14ADs().add(fiseFormato14AD);
		fiseFormato14AD.setFiseFormato14AC(this);

		return fiseFormato14AD;
	}

	public FiseFormato14AD removeFiseFormato14AD(FiseFormato14AD fiseFormato14AD) {
		getFiseFormato14ADs().remove(fiseFormato14AD);
		fiseFormato14AD.setFiseFormato14AC(null);

		return fiseFormato14AD;
	}

	public String getDescEmpresa() {
		return descEmpresa;
	}

	public void setDescEmpresa(String descEmpresa) {
		this.descEmpresa = descEmpresa;
	}

	public String getDescEstado() {
		return descEstado;
	}

	public void setDescEstado(String descEstado) {
		this.descEstado = descEstado;
	}

	public String getDescMesPresentacion() {
		return descMesPresentacion;
	}

	public void setDescMesPresentacion(String descMesPresentacion) {
		this.descMesPresentacion = descMesPresentacion;
	}

	public String getDescGrupoInformacion() {
		return descGrupoInformacion;
	}

	public void setDescGrupoInformacion(String descGrupoInformacion) {
		this.descGrupoInformacion = descGrupoInformacion;
	}

	public String getArchivoExcel() {
		return archivoExcel;
	}

	public void setArchivoExcel(String archivoExcel) {
		this.archivoExcel = archivoExcel;
	}

	public String getArchivoTexto() {
		return archivoTexto;
	}

	public void setArchivoTexto(String archivoTexto) {
		this.archivoTexto = archivoTexto;
	}

	public Date getFechaEnvioDefinitivo() {
		return fechaEnvioDefinitivo;
	}

	public void setFechaEnvioDefinitivo(Date fechaEnvioDefinitivo) {
		this.fechaEnvioDefinitivo = fechaEnvioDefinitivo;
	}

	public FiseGrupoInformacion getFiseGrupoInformacion() {
		return fiseGrupoInformacion;
	}

	public void setFiseGrupoInformacion(FiseGrupoInformacion fiseGrupoInformacion) {
		this.fiseGrupoInformacion = fiseGrupoInformacion;
	}
	

}