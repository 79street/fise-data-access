package gob.osinergmin.fise.domain;

import java.io.Serializable;

import javax.persistence.*;

import java.util.Date;
import java.util.List;


/**
 * The persistent class for the FISE_FORMATO_14C_C database table.
 * 
 */
@Entity
@Table(name="FISE_FORMATO_14C_C", schema="FISE")
@NamedQuery(name="FiseFormato14CC.findAll", query="SELECT f FROM FiseFormato14CC f")
public class FiseFormato14CC implements Serializable {
	private static final long serialVersionUID = 1L;

	@EmbeddedId
	private FiseFormato14CCPK id;

	@Temporal(TemporalType.TIMESTAMP)
	@Column(name="FECHA_ACTUALIZACION")
	private Date fechaActualizacion;

	@Temporal(TemporalType.TIMESTAMP)
	@Column(name="FECHA_CREACION")
	private Date fechaCreacion;

	@Temporal(TemporalType.TIMESTAMP)
	@Column(name="FECHA_ENVIO_DEFINITIVO")
	private Date fechaEnvioDefinitivo;

	@Column(name="ID_GRUPO_INFORMACION")
	private java.math.BigDecimal idGrupoInformacion;

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

	//bi-directional many-to-one association to FiseFormato14CD
	@Transient
	@OneToMany(mappedBy="fiseFormato14CC", cascade={CascadeType.ALL})
	private List<FiseFormato14CD> fiseFormato14CDs;

	public FiseFormato14CC() {
	}

	public FiseFormato14CCPK getId() {
		return this.id;
	}

	public void setId(FiseFormato14CCPK id) {
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

	public java.math.BigDecimal getIdGrupoInformacion() {
		return this.idGrupoInformacion;
	}

	public void setIdGrupoInformacion(java.math.BigDecimal idGrupoInformacion) {
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

	public List<FiseFormato14CD> getFiseFormato14CDs() {
		return this.fiseFormato14CDs;
	}

	public void setFiseFormato14CDs(List<FiseFormato14CD> fiseFormato14CDs) {
		this.fiseFormato14CDs = fiseFormato14CDs;
	}

	public FiseFormato14CD addFiseFormato14CD(FiseFormato14CD fiseFormato14CD) {
		getFiseFormato14CDs().add(fiseFormato14CD);
		fiseFormato14CD.setFiseFormato14CC(this);

		return fiseFormato14CD;
	}

	public FiseFormato14CD removeFiseFormato14CD(FiseFormato14CD fiseFormato14CD) {
		getFiseFormato14CDs().remove(fiseFormato14CD);
		fiseFormato14CD.setFiseFormato14CC(null);

		return fiseFormato14CD;
	}

}