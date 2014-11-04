package gob.osinergmin.fise.domain;

import java.io.Serializable;

import javax.persistence.*;

import java.util.Date;
import java.util.List;


/**
 * The persistent class for the FISE_FORMATO_13A_C database table.
 * 
 */
@Entity
@Table(name="FISE_FORMATO_13A_C")
@NamedQuery(name="FiseFormato13AC.findAll", query="SELECT f FROM FiseFormato13AC f")
public class FiseFormato13AC implements Serializable {
	private static final long serialVersionUID = 1L;

	@EmbeddedId
	private FiseFormato13ACPK id;

	@Temporal(TemporalType.DATE)
	@Column(name="FECHA_ACTUALIZACION")
	private Date fechaActualizacion;

	@Temporal(TemporalType.DATE)
	@Column(name="FECHA_CREACION")
	private Date fechaCreacion;

	@Temporal(TemporalType.DATE)
	@Column(name="FECHA_ENVIO_DEFINITIVO")
	private Date fechaEnvioDefinitivo;

	@Column(name="ID_GRUPO_INFORMACION")
	private Long idGrupoInformacion;

	@Column(name="NOMBRE_ARCHIVO_EXCEL")
	private String nombreArchivoExcel;

	@Column(name="NOMBRE_ARCHIVO_TEXTO")
	private String nombreArchivoTexto;

	@Column(name="TERMINAL_ACTUALIZACION")
	private String terminalActualizacion;

	@Column(name="TERMINAL_CREACION")
	private String terminalCreacion;

	@Column(name="USUARIO_ACTUALIZACION")
	private String usuarioActualizacion;

	@Column(name="USUARIO_CREACION")
	private String usuarioCreacion;

	@Transient
	private String descEmpresa;
	
	@Transient
	private String descMesPresentacion;
	
	@Transient
	private String descGrupoInformacion;
	
	//bi-directional many-to-one association to FiseFormato13AD
	@Transient
	@OneToMany(mappedBy="fiseFormato13AC", cascade={CascadeType.ALL})
	private List<FiseFormato13AD> fiseFormato13ADs;

	public FiseFormato13AC() {
	}

	public FiseFormato13ACPK getId() {
		return this.id;
	}

	public void setId(FiseFormato13ACPK id) {
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

	public Date getFechaEnvioDefinitivo() {
		return this.fechaEnvioDefinitivo;
	}

	public void setFechaEnvioDefinitivo(Date fechaEnvioDefinitivo) {
		this.fechaEnvioDefinitivo = fechaEnvioDefinitivo;
	}

	public Long getIdGrupoInformacion() {
		return this.idGrupoInformacion;
	}

	public void setIdGrupoInformacion(Long idGrupoInformacion) {
		this.idGrupoInformacion = idGrupoInformacion;
	}

	public String getNombreArchivoExcel() {
		return this.nombreArchivoExcel;
	}

	public void setNombreArchivoExcel(String nombreArchivoExcel) {
		this.nombreArchivoExcel = nombreArchivoExcel;
	}

	public String getNombreArchivoTexto() {
		return this.nombreArchivoTexto;
	}

	public void setNombreArchivoTexto(String nombreArchivoTexto) {
		this.nombreArchivoTexto = nombreArchivoTexto;
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

	public List<FiseFormato13AD> getFiseFormato13ADs() {
		return this.fiseFormato13ADs;
	}

	public void setFiseFormato13ADs(List<FiseFormato13AD> fiseFormato13ADs) {
		this.fiseFormato13ADs = fiseFormato13ADs;
	}

	public FiseFormato13AD addFiseFormato13AD(FiseFormato13AD fiseFormato13AD) {
		getFiseFormato13ADs().add(fiseFormato13AD);
		fiseFormato13AD.setFiseFormato13AC(this);

		return fiseFormato13AD;
	}

	public FiseFormato13AD removeFiseFormato13AD(FiseFormato13AD fiseFormato13AD) {
		getFiseFormato13ADs().remove(fiseFormato13AD);
		fiseFormato13AD.setFiseFormato13AC(null);

		return fiseFormato13AD;
	}

	public String getDescEmpresa() {
		return descEmpresa;
	}

	public void setDescEmpresa(String descEmpresa) {
		this.descEmpresa = descEmpresa;
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

}