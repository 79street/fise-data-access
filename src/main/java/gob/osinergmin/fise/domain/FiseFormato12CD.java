package gob.osinergmin.fise.domain;

import java.io.Serializable;

import javax.persistence.*;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;


/**
 * The persistent class for the FISE_FORMATO_12C_D database table.
 * 
 */
@Entity
@Table(name="FISE_FORMATO_12C_D")
@NamedQuery(name="FiseFormato12CD.findAll", query="SELECT f FROM FiseFormato12CD f")
public class FiseFormato12CD implements Serializable {
	private static final long serialVersionUID = 1L;

	@EmbeddedId
	private FiseFormato12CDPK id;

	@Column(name="COD_UBIGEO_DESTINO")
	private String codUbigeoDestino;

	@Column(name="COD_UBIGEO_ORIGEN")
	private String codUbigeoOrigen;

	@Column(name="CODIGO_CUENTA_CONTA_EDE")
	private String codigoCuentaContaEde;

	@Column(name="DESCRIPCION_ACTIVIDAD")
	private String descripcionActividad;

	@Column(name="DESCRIPCION_LOCALIDAD_DESTINO")
	private String descripcionLocalidadDestino;

	@Column(name="DESCRIPCION_LOCALIDAD_ORIGEN")
	private String descripcionLocalidadOrigen;

	@Temporal(TemporalType.TIMESTAMP)
	@Column(name="FECHA_ACTUALIZACION")
	private Date fechaActualizacion;

	@Temporal(TemporalType.TIMESTAMP)
	@Column(name="FECHA_CREACION")
	private Date fechaCreacion;

	@Column(name="ID_TIP_DOC_REF")
	private String idTipDocRef;

	@Column(name="ID_ZONA_BENEF")
	private Long idZonaBenef;

	@Column(name="MONTO_ALIMENTACION")
	private BigDecimal montoAlimentacion;

	@Column(name="MONTO_ALOJAMIENTO")
	private BigDecimal montoAlojamiento;

	@Column(name="MONTO_MOVILIDAD")
	private BigDecimal montoMovilidad;

	@Column(name="NUMERO_DIAS")
	private Long numeroDias;

	@Column(name="NUMERO_DOCUMENTO_REFERENCIA")
	private String numeroDocumentoReferencia;

	@Column(name="RUC_EMPRESA_EMITE_DOC_REF")
	private String rucEmpresaEmiteDocRef;

	@Column(name="SERIE_DOCUMENTO_REFERENCIA")
	private String serieDocumentoReferencia;

	@Column(name="SUB_TOTAL_ETAPA")
	private BigDecimal subTotalEtapa;

	@Column(name="TERMINAL_ACTUALIZACION")
	private String terminalActualizacion;

	@Column(name="TERMINAL_CREACION")
	private String terminalCreacion;

	@Column(name="TOTAL_DESPLAZAMIENTO_REALIZADO")
	private BigDecimal totalDesplazamientoRealizado;

	@Column(name="TOTAL_GENERAL")
	private BigDecimal totalGeneral;

	@Column(name="USUARIO_ACTUALIZACION")
	private String usuarioActualizacion;

	@Column(name="USUARIO_CREACION")
	private String usuarioCreacion;

	//bi-directional many-to-one association to FiseFormato12CC
	@Transient
	@ManyToOne(cascade={CascadeType.ALL})
	@JoinColumns({
		@JoinColumn(name="ANO_PRESENTACION", referencedColumnName="ANO_PRESENTACION"),
		@JoinColumn(name="COD_EMPRESA", referencedColumnName="COD_EMPRESA"),
		@JoinColumn(name="ETAPA", referencedColumnName="ETAPA"),
		@JoinColumn(name="MES_PRESENTACION", referencedColumnName="MES_PRESENTACION")
		})
	private FiseFormato12CC fiseFormato12CC;

	//bi-directional many-to-one association to FiseFormato12CDOb
	@Transient
	@OneToMany(mappedBy="fiseFormato12CD")
	private List<FiseFormato12CDOb> fiseFormato12CDObs;

	@Transient
	private String descMesEjecucion;
	@Transient
	private String descEtapaEjecucion;
	@Transient
	private String descZonaBenef;
	
	@Transient
	private String codEmpresaReport;
	@Transient
	private long anoPresentacionReport;
	@Transient
	private long mesPresentacionReport;
	@Transient
	private String etapaReport;
	@Transient
	private long anoEjecucionGastoReport;
	@Transient
	private long mesEjecucionGastoReport;
	@Transient
	private long etapaEjecucionReport;
	@Transient
	private long numeroItemEtapaReport;
	
	public FiseFormato12CD() {
	}

	public FiseFormato12CDPK getId() {
		return this.id;
	}

	public void setId(FiseFormato12CDPK id) {
		this.id = id;
	}

	public String getCodUbigeoDestino() {
		return this.codUbigeoDestino;
	}

	public void setCodUbigeoDestino(String codUbigeoDestino) {
		this.codUbigeoDestino = codUbigeoDestino;
	}

	public String getCodUbigeoOrigen() {
		return this.codUbigeoOrigen;
	}

	public void setCodUbigeoOrigen(String codUbigeoOrigen) {
		this.codUbigeoOrigen = codUbigeoOrigen;
	}

	public String getCodigoCuentaContaEde() {
		return this.codigoCuentaContaEde;
	}

	public void setCodigoCuentaContaEde(String codigoCuentaContaEde) {
		this.codigoCuentaContaEde = codigoCuentaContaEde;
	}

	public String getDescripcionActividad() {
		return this.descripcionActividad;
	}

	public void setDescripcionActividad(String descripcionActividad) {
		this.descripcionActividad = descripcionActividad;
	}

	public String getDescripcionLocalidadDestino() {
		return this.descripcionLocalidadDestino;
	}

	public void setDescripcionLocalidadDestino(String descripcionLocalidadDestino) {
		this.descripcionLocalidadDestino = descripcionLocalidadDestino;
	}

	public String getDescripcionLocalidadOrigen() {
		return this.descripcionLocalidadOrigen;
	}

	public void setDescripcionLocalidadOrigen(String descripcionLocalidadOrigen) {
		this.descripcionLocalidadOrigen = descripcionLocalidadOrigen;
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

	public String getIdTipDocRef() {
		return this.idTipDocRef;
	}

	public void setIdTipDocRef(String idTipDocRef) {
		this.idTipDocRef = idTipDocRef;
	}

	public Long getIdZonaBenef() {
		return this.idZonaBenef;
	}

	public void setIdZonaBenef(Long idZonaBenef) {
		this.idZonaBenef = idZonaBenef;
	}

	public BigDecimal getMontoAlimentacion() {
		return this.montoAlimentacion;
	}

	public void setMontoAlimentacion(BigDecimal montoAlimentacion) {
		this.montoAlimentacion = montoAlimentacion;
	}

	public BigDecimal getMontoAlojamiento() {
		return this.montoAlojamiento;
	}

	public void setMontoAlojamiento(BigDecimal montoAlojamiento) {
		this.montoAlojamiento = montoAlojamiento;
	}

	public BigDecimal getMontoMovilidad() {
		return this.montoMovilidad;
	}

	public void setMontoMovilidad(BigDecimal montoMovilidad) {
		this.montoMovilidad = montoMovilidad;
	}

	public Long getNumeroDias() {
		return this.numeroDias;
	}

	public void setNumeroDias(Long numeroDias) {
		this.numeroDias = numeroDias;
	}

	public String getNumeroDocumentoReferencia() {
		return this.numeroDocumentoReferencia;
	}

	public void setNumeroDocumentoReferencia(String numeroDocumentoReferencia) {
		this.numeroDocumentoReferencia = numeroDocumentoReferencia;
	}

	public String getRucEmpresaEmiteDocRef() {
		return this.rucEmpresaEmiteDocRef;
	}

	public void setRucEmpresaEmiteDocRef(String rucEmpresaEmiteDocRef) {
		this.rucEmpresaEmiteDocRef = rucEmpresaEmiteDocRef;
	}

	public String getSerieDocumentoReferencia() {
		return this.serieDocumentoReferencia;
	}

	public void setSerieDocumentoReferencia(String serieDocumentoReferencia) {
		this.serieDocumentoReferencia = serieDocumentoReferencia;
	}

	public BigDecimal getSubTotalEtapa() {
		return this.subTotalEtapa;
	}

	public void setSubTotalEtapa(BigDecimal subTotalEtapa) {
		this.subTotalEtapa = subTotalEtapa;
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

	public BigDecimal getTotalDesplazamientoRealizado() {
		return this.totalDesplazamientoRealizado;
	}

	public void setTotalDesplazamientoRealizado(BigDecimal totalDesplazamientoRealizado) {
		this.totalDesplazamientoRealizado = totalDesplazamientoRealizado;
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

	public FiseFormato12CC getFiseFormato12CC() {
		return this.fiseFormato12CC;
	}

	public void setFiseFormato12CC(FiseFormato12CC fiseFormato12CC) {
		this.fiseFormato12CC = fiseFormato12CC;
	}

	public List<FiseFormato12CDOb> getFiseFormato12CDObs() {
		return this.fiseFormato12CDObs;
	}

	public void setFiseFormato12CDObs(List<FiseFormato12CDOb> fiseFormato12CDObs) {
		this.fiseFormato12CDObs = fiseFormato12CDObs;
	}

	public FiseFormato12CDOb addFiseFormato12CDOb(FiseFormato12CDOb fiseFormato12CDOb) {
		getFiseFormato12CDObs().add(fiseFormato12CDOb);
		fiseFormato12CDOb.setFiseFormato12CD(this);

		return fiseFormato12CDOb;
	}

	public FiseFormato12CDOb removeFiseFormato12CDOb(FiseFormato12CDOb fiseFormato12CDOb) {
		getFiseFormato12CDObs().remove(fiseFormato12CDOb);
		fiseFormato12CDOb.setFiseFormato12CD(null);

		return fiseFormato12CDOb;
	}

	public String getDescMesEjecucion() {
		return descMesEjecucion;
	}

	public void setDescMesEjecucion(String descMesEjecucion) {
		this.descMesEjecucion = descMesEjecucion;
	}

	public String getDescEtapaEjecucion() {
		return descEtapaEjecucion;
	}

	public void setDescEtapaEjecucion(String descEtapaEjecucion) {
		this.descEtapaEjecucion = descEtapaEjecucion;
	}

	public String getDescZonaBenef() {
		return descZonaBenef;
	}

	public void setDescZonaBenef(String descZonaBenef) {
		this.descZonaBenef = descZonaBenef;
	}

	public String getCodEmpresaReport() {
		return codEmpresaReport;
	}

	public void setCodEmpresaReport(String codEmpresaReport) {
		this.codEmpresaReport = codEmpresaReport;
	}

	public long getAnoPresentacionReport() {
		return anoPresentacionReport;
	}

	public void setAnoPresentacionReport(long anoPresentacionReport) {
		this.anoPresentacionReport = anoPresentacionReport;
	}

	public long getMesPresentacionReport() {
		return mesPresentacionReport;
	}

	public void setMesPresentacionReport(long mesPresentacionReport) {
		this.mesPresentacionReport = mesPresentacionReport;
	}

	public String getEtapaReport() {
		return etapaReport;
	}

	public void setEtapaReport(String etapaReport) {
		this.etapaReport = etapaReport;
	}

	public long getAnoEjecucionGastoReport() {
		return anoEjecucionGastoReport;
	}

	public void setAnoEjecucionGastoReport(long anoEjecucionGastoReport) {
		this.anoEjecucionGastoReport = anoEjecucionGastoReport;
	}

	public long getMesEjecucionGastoReport() {
		return mesEjecucionGastoReport;
	}

	public void setMesEjecucionGastoReport(long mesEjecucionGastoReport) {
		this.mesEjecucionGastoReport = mesEjecucionGastoReport;
	}

	public long getEtapaEjecucionReport() {
		return etapaEjecucionReport;
	}

	public void setEtapaEjecucionReport(long etapaEjecucionReport) {
		this.etapaEjecucionReport = etapaEjecucionReport;
	}

	public long getNumeroItemEtapaReport() {
		return numeroItemEtapaReport;
	}

	public void setNumeroItemEtapaReport(long numeroItemEtapaReport) {
		this.numeroItemEtapaReport = numeroItemEtapaReport;
	}

}