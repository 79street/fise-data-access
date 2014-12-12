package gob.osinergmin.fise.domain;

import java.io.Serializable;

import javax.persistence.*;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;


/**
 * The persistent class for the FISE_FORMATO_12D_C database table.
 * 
 */
@Entity
@Table(name="FISE_FORMATO_12D_C")
@NamedQuery(name="FiseFormato12DC.findAll", query="SELECT f FROM FiseFormato12DC f")
public class FiseFormato12DC implements Serializable {
	private static final long serialVersionUID = 1L;

	@EmbeddedId
	private FiseFormato12DCPK id;

	@Temporal(TemporalType.TIMESTAMP)
	@Column(name="FECHA_ACTUALIZACION")
	private Date fechaActualizacion;

	@Temporal(TemporalType.TIMESTAMP)
	@Column(name="FECHA_CREACION")
	private Date fechaCreacion;

	@Temporal(TemporalType.TIMESTAMP)
	@Column(name="FECHA_ENVIO_DEFINITIVO")
	private Date fechaEnvioDefinitivo;

	@Column(name="NOMBRE_ARCHIVO_EXCEL")
	private String nombreArchivoExcel;

	@Column(name="NOMBRE_ARCHIVO_TEXTO")
	private String nombreArchivoTexto;

	@Column(name="TERMINAL_ACTUALIZACION")
	private String terminalActualizacion;

	@Column(name="TERMINAL_CREACION")
	private String terminalCreacion;

	@Column(name="TOTAL_GENERAL")
	private BigDecimal totalGeneral;

	@Column(name="USUARIO_ACTUALIZACION")
	private String usuarioActualizacion;

	@Column(name="USUARIO_CREACION")
	private String usuarioCreacion;

	//bi-directional many-to-one association to FiseFormato12DD
	@Transient
	@OneToMany(mappedBy="fiseFormato12DC")
	private List<FiseFormato12DD> fiseFormato12DDs;

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
	
	//guardamos la cabecerta del ultimo detalle registrado
	@Transient
	private Long anoEjecucionDetalle;
	@Transient
	private Long mesEjecucionDetalle;
	@Transient
	private Long etapaEjecucionDetalle;
	@Transient
	private Long numeroItemEtapaDetalle;
	
	public FiseFormato12DC() {
	}

	public FiseFormato12DCPK getId() {
		return this.id;
	}

	public void setId(FiseFormato12DCPK id) {
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

	public BigDecimal getTotalGeneral() {
		return this.totalGeneral;
	}

	public void setTotalGeneral(BigDecimal totalGeneral) {
		this.totalGeneral = totalGeneral;
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

	public List<FiseFormato12DD> getFiseFormato12DDs() {
		return this.fiseFormato12DDs;
	}

	public void setFiseFormato12DDs(List<FiseFormato12DD> fiseFormato12DDs) {
		this.fiseFormato12DDs = fiseFormato12DDs;
	}

	public FiseFormato12DD addFiseFormato12DD(FiseFormato12DD fiseFormato12DD) {
		getFiseFormato12DDs().add(fiseFormato12DD);
		fiseFormato12DD.setFiseFormato12DC(this);

		return fiseFormato12DD;
	}

	public FiseFormato12DD removeFiseFormato12DD(FiseFormato12DD fiseFormato12DD) {
		getFiseFormato12DDs().remove(fiseFormato12DD);
		fiseFormato12DD.setFiseFormato12DC(null);

		return fiseFormato12DD;
	}

	public FiseGrupoInformacion getFiseGrupoInformacion() {
		return fiseGrupoInformacion;
	}

	public void setFiseGrupoInformacion(FiseGrupoInformacion fiseGrupoInformacion) {
		this.fiseGrupoInformacion = fiseGrupoInformacion;
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

	public Long getAnoEjecucionDetalle() {
		return anoEjecucionDetalle;
	}

	public void setAnoEjecucionDetalle(Long anoEjecucionDetalle) {
		this.anoEjecucionDetalle = anoEjecucionDetalle;
	}

	public Long getMesEjecucionDetalle() {
		return mesEjecucionDetalle;
	}

	public void setMesEjecucionDetalle(Long mesEjecucionDetalle) {
		this.mesEjecucionDetalle = mesEjecucionDetalle;
	}

	public Long getEtapaEjecucionDetalle() {
		return etapaEjecucionDetalle;
	}

	public void setEtapaEjecucionDetalle(Long etapaEjecucionDetalle) {
		this.etapaEjecucionDetalle = etapaEjecucionDetalle;
	}

	public Long getNumeroItemEtapaDetalle() {
		return numeroItemEtapaDetalle;
	}

	public void setNumeroItemEtapaDetalle(Long numeroItemEtapaDetalle) {
		this.numeroItemEtapaDetalle = numeroItemEtapaDetalle;
	}

}