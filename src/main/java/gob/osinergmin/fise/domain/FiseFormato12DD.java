package gob.osinergmin.fise.domain;

import java.io.Serializable;

import javax.persistence.*;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;


/**
 * The persistent class for the FISE_FORMATO_12D_D database table.
 * 
 */
@Entity
@Table(name="FISE_FORMATO_12D_D")
@NamedQuery(name="FiseFormato12DD.findAll", query="SELECT f FROM FiseFormato12DD f")
public class FiseFormato12DD implements Serializable {
	private static final long serialVersionUID = 1L;

	@EmbeddedId
	private FiseFormato12DDPK id;

	private Long cantidad;

	@Column(name="COD_UBIGEO")
	private String codUbigeo;

	@Column(name="CODIGO_CUENTA_CONTA_EDE")
	private String codigoCuentaContaEde;

	@Column(name="COSTO_UNITARIO")
	private BigDecimal costoUnitario;

	@Column(name="DESCRIPCION_GASTO")
	private String descripcionGasto;

	@Column(name="DESCRIPCION_LOCALIDAD")
	private String descripcionLocalidad;

	@Temporal(TemporalType.TIMESTAMP)
	@Column(name="FECHA_ACTUALIZACION")
	private Date fechaActualizacion;

	@Temporal(TemporalType.TIMESTAMP)
	@Column(name="FECHA_AUTORIZACION_GASTO")
	private Date fechaAutorizacionGasto;

	@Temporal(TemporalType.TIMESTAMP)
	@Column(name="FECHA_CREACION")
	private Date fechaCreacion;

	@Column(name="ID_TIP_DOC_REF")
	private String idTipDocRef;

	@Column(name="ID_TIP_GASTO")
	private String idTipGasto;

	@Column(name="ID_ZONA_BENEF")
	private BigDecimal idZonaBenef;

	@Column(name="NUMERO_DOC_AUTORIZA_GASTO")
	private String numeroDocAutorizaGasto;

	@Column(name="NUMERO_DOCUMENTO_REF_GASTO")
	private String numeroDocumentoRefGasto;

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

	@Column(name="TOTAL_GASTO")
	private BigDecimal totalGasto;

	@Column(name="TOTAL_GENERAL")
	private BigDecimal totalGeneral;

	@Column(name="USUARIO_ACTUALIZACION")
	private String usuarioActualizacion;

	@Column(name="USUARIO_CREACION")
	private String usuarioCreacion;

	//bi-directional many-to-one association to FiseFormato12DC
	@Transient
	@ManyToOne
	@JoinColumns({
		@JoinColumn(name="ANO_PRESENTACION", referencedColumnName="ANO_PRESENTACION"),
		@JoinColumn(name="COD_EMPRESA", referencedColumnName="COD_EMPRESA"),
		@JoinColumn(name="ETAPA", referencedColumnName="ETAPA"),
		@JoinColumn(name="MES_PRESENTACION", referencedColumnName="MES_PRESENTACION")
		})
	private FiseFormato12DC fiseFormato12DC;

	//bi-directional many-to-one association to FiseFormato12DDOb
	@Transient
	@OneToMany(mappedBy="fiseFormato12DD")
	private List<FiseFormato12DDOb> fiseFormato12DDObs;

	public FiseFormato12DD() {
	}

	public FiseFormato12DDPK getId() {
		return this.id;
	}

	public void setId(FiseFormato12DDPK id) {
		this.id = id;
	}

	public Long getCantidad() {
		return this.cantidad;
	}

	public void setCantidad(Long cantidad) {
		this.cantidad = cantidad;
	}

	public String getCodUbigeo() {
		return this.codUbigeo;
	}

	public void setCodUbigeo(String codUbigeo) {
		this.codUbigeo = codUbigeo;
	}

	public String getCodigoCuentaContaEde() {
		return this.codigoCuentaContaEde;
	}

	public void setCodigoCuentaContaEde(String codigoCuentaContaEde) {
		this.codigoCuentaContaEde = codigoCuentaContaEde;
	}

	public BigDecimal getCostoUnitario() {
		return this.costoUnitario;
	}

	public void setCostoUnitario(BigDecimal costoUnitario) {
		this.costoUnitario = costoUnitario;
	}

	public String getDescripcionGasto() {
		return this.descripcionGasto;
	}

	public void setDescripcionGasto(String descripcionGasto) {
		this.descripcionGasto = descripcionGasto;
	}

	public String getDescripcionLocalidad() {
		return this.descripcionLocalidad;
	}

	public void setDescripcionLocalidad(String descripcionLocalidad) {
		this.descripcionLocalidad = descripcionLocalidad;
	}

	public Date getFechaActualizacion() {
		return this.fechaActualizacion;
	}

	public void setFechaActualizacion(Date fechaActualizacion) {
		this.fechaActualizacion = fechaActualizacion;
	}

	public Date getFechaAutorizacionGasto() {
		return this.fechaAutorizacionGasto;
	}

	public void setFechaAutorizacionGasto(Date fechaAutorizacionGasto) {
		this.fechaAutorizacionGasto = fechaAutorizacionGasto;
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

	public String getIdTipGasto() {
		return this.idTipGasto;
	}

	public void setIdTipGasto(String idTipGasto) {
		this.idTipGasto = idTipGasto;
	}

	public BigDecimal getIdZonaBenef() {
		return this.idZonaBenef;
	}

	public void setIdZonaBenef(BigDecimal idZonaBenef) {
		this.idZonaBenef = idZonaBenef;
	}

	public String getNumeroDocAutorizaGasto() {
		return this.numeroDocAutorizaGasto;
	}

	public void setNumeroDocAutorizaGasto(String numeroDocAutorizaGasto) {
		this.numeroDocAutorizaGasto = numeroDocAutorizaGasto;
	}

	public String getNumeroDocumentoRefGasto() {
		return this.numeroDocumentoRefGasto;
	}

	public void setNumeroDocumentoRefGasto(String numeroDocumentoRefGasto) {
		this.numeroDocumentoRefGasto = numeroDocumentoRefGasto;
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

	public BigDecimal getTotalGasto() {
		return this.totalGasto;
	}

	public void setTotalGasto(BigDecimal totalGasto) {
		this.totalGasto = totalGasto;
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

	public FiseFormato12DC getFiseFormato12DC() {
		return this.fiseFormato12DC;
	}

	public void setFiseFormato12DC(FiseFormato12DC fiseFormato12DC) {
		this.fiseFormato12DC = fiseFormato12DC;
	}

	public List<FiseFormato12DDOb> getFiseFormato12DDObs() {
		return this.fiseFormato12DDObs;
	}

	public void setFiseFormato12DDObs(List<FiseFormato12DDOb> fiseFormato12DDObs) {
		this.fiseFormato12DDObs = fiseFormato12DDObs;
	}

	public FiseFormato12DDOb addFiseFormato12DDOb(FiseFormato12DDOb fiseFormato12DDOb) {
		getFiseFormato12DDObs().add(fiseFormato12DDOb);
		fiseFormato12DDOb.setFiseFormato12DD(this);

		return fiseFormato12DDOb;
	}

	public FiseFormato12DDOb removeFiseFormato12DDOb(FiseFormato12DDOb fiseFormato12DDOb) {
		getFiseFormato12DDObs().remove(fiseFormato12DDOb);
		fiseFormato12DDOb.setFiseFormato12DD(null);

		return fiseFormato12DDOb;
	}

}