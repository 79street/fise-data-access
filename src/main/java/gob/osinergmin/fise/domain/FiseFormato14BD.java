package gob.osinergmin.fise.domain;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.JoinColumns;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.persistence.Transient;


/**
 * The persistent class for the FISE_FORMATO_14B_D database table.
 * 
 */
@Entity
@Table(name="FISE_FORMATO_14B_D", schema="FISE")
@NamedQuery(name="FiseFormato14BD.findAll", query="SELECT f FROM FiseFormato14BD f")
public class FiseFormato14BD implements Serializable {
	private static final long serialVersionUID = 1L;

	@EmbeddedId
	private FiseFormato14BDPK id;

	@Column(name="CAPACITACION_AGENTES_AUT_GLP")
	private BigDecimal capacitacionAgentesAutGlp;

	@Column(name="COSTO_ATENCION_CONSULTA_RECLA")
	private BigDecimal costoAtencionConsultaRecla;

	@Column(name="COSTO_ATENCION_SOLICITUDES")
	private BigDecimal costoAtencionSolicitudes;

	@Column(name="COSTO_ENVIAR_PADRON_VAL_CANJE")
	private BigDecimal costoEnviarPadronValCanje;

	@Column(name="COSTO_PERSONAL")
	private BigDecimal costoPersonal;

	@Column(name="COSTO_REPARTO_VALES_DESCUENTO")
	private BigDecimal costoRepartoValesDescuento;

	@Column(name="COSTO_TOT_REPRTO_VAL_DIS_EL")
	private BigDecimal costoTotReprtoValDisEl;

	@Column(name="COSTO_TOTAL_ATENCION")
	private BigDecimal costoTotalAtencion;

	@Column(name="COSTO_TOTAL_GESTION_ADMINISTRA")
	private BigDecimal costoTotalGestionAdministra;

	@Column(name="COSTO_TOTAL_IMPRESION")
	private BigDecimal costoTotalImpresion;

	@Column(name="COSTO_UNIT_CANJE_LIQ_VAL_FISI")
	private BigDecimal costoUnitCanjeLiqValFisi;

	@Column(name="COSTO_UNIT_CANJE_VAL_DIGITAL")
	private BigDecimal costoUnitCanjeValDigital;

	@Column(name="COSTO_UNIT_ENTREGA_VAL_DIS_EL")
	private BigDecimal costoUnitEntregaValDisEl;

	@Column(name="COSTO_UNIT_REPRTO_VALE_DOMICI")
	private BigDecimal costoUnitReprtoValeDomici;

	@Column(name="COSTO_UNITARIO_IMPRESION_VALES")
	private BigDecimal costoUnitarioImpresionVales;

	@Column(name="COSTO_UNITARIO_POR_ATENCION")
	private BigDecimal costoUnitarioPorAtencion;

	@Temporal(TemporalType.TIMESTAMP)
	@Column(name="FECHA_ACTUALIZACION")
	private Date fechaActualizacion;

	@Temporal(TemporalType.TIMESTAMP)
	@Column(name="FECHA_CREACION")
	private Date fechaCreacion;

	@Column(name="IMPRE_VAL_DSCTO_CLI_NO_DIS_EL")
	private BigDecimal impreValDsctoCliNoDisEl;

	@Column(name="IMPRESION_VAL_DSCTO_CLI_DIS_EL")
	private BigDecimal impresionValDsctoCliDisEl;

	@Column(name="NUMERO_TOTAL_ATENCION")
	private Long numeroTotalAtencion;

	@Column(name="NUMERO_VALES_ENTREGADOS")
	private Long numeroValesEntregados;

	@Column(name="NUMERO_VALES_FISICOS_EMITIDOS")
	private Long numeroValesFisicosEmitidos;

	@Column(name="NUMERO_VALES_IMPRESO")
	private Long numeroValesImpreso;

	@Column(name="NUMERO_VALES_REPARTIDOS")
	private Long numeroValesRepartidos;

	@Column(name="TERMINAL_ACTUALIZACION")
	private String terminalActualizacion;

	@Column(name="TERMINAL_CREACION")
	private String terminalCreacion;

	@Column(name="USUARIO_ACTUALIZACION")
	private String usuarioActualizacion;

	@Column(name="USUARIO_CREACION")
	private String usuarioCreacion;

	@Column(name="UTILES_MATERIALES_OFICINA")
	private BigDecimal utilesMaterialesOficina;

	//bi-directional many-to-one association to FiseFormato14BC
	@Transient
	@ManyToOne(cascade={CascadeType.ALL})
	@JoinColumns({
		@JoinColumn(name="ANO_FIN_VIGENCIA", referencedColumnName="ANO_FIN_VIGENCIA"),
		@JoinColumn(name="ANO_INICIO_VIGENCIA", referencedColumnName="ANO_INICIO_VIGENCIA"),
		@JoinColumn(name="ANO_PRESENTACION", referencedColumnName="ANO_PRESENTACION"),
		@JoinColumn(name="COD_EMPRESA", referencedColumnName="COD_EMPRESA"),
		@JoinColumn(name="ETAPA", referencedColumnName="ETAPA"),
		@JoinColumn(name="MES_PRESENTACION", referencedColumnName="MES_PRESENTACION")
		})
	private FiseFormato14BC fiseFormato14BC;

	//bi-directional many-to-one association to FiseFormato14BDOb
	@Transient
	@OneToMany(mappedBy="fiseFormato14BD")
	private List<FiseFormato14BDOb> fiseFormato14BDObs;

	public FiseFormato14BD() {
	}

	public FiseFormato14BDPK getId() {
		return this.id;
	}

	public void setId(FiseFormato14BDPK id) {
		this.id = id;
	}

	public BigDecimal getCapacitacionAgentesAutGlp() {
		return this.capacitacionAgentesAutGlp;
	}

	public void setCapacitacionAgentesAutGlp(BigDecimal capacitacionAgentesAutGlp) {
		this.capacitacionAgentesAutGlp = capacitacionAgentesAutGlp;
	}

	public BigDecimal getCostoAtencionConsultaRecla() {
		return this.costoAtencionConsultaRecla;
	}

	public void setCostoAtencionConsultaRecla(BigDecimal costoAtencionConsultaRecla) {
		this.costoAtencionConsultaRecla = costoAtencionConsultaRecla;
	}

	public BigDecimal getCostoAtencionSolicitudes() {
		return this.costoAtencionSolicitudes;
	}

	public void setCostoAtencionSolicitudes(BigDecimal costoAtencionSolicitudes) {
		this.costoAtencionSolicitudes = costoAtencionSolicitudes;
	}

	public BigDecimal getCostoEnviarPadronValCanje() {
		return this.costoEnviarPadronValCanje;
	}

	public void setCostoEnviarPadronValCanje(BigDecimal costoEnviarPadronValCanje) {
		this.costoEnviarPadronValCanje = costoEnviarPadronValCanje;
	}

	public BigDecimal getCostoPersonal() {
		return this.costoPersonal;
	}

	public void setCostoPersonal(BigDecimal costoPersonal) {
		this.costoPersonal = costoPersonal;
	}

	public BigDecimal getCostoRepartoValesDescuento() {
		return this.costoRepartoValesDescuento;
	}

	public void setCostoRepartoValesDescuento(BigDecimal costoRepartoValesDescuento) {
		this.costoRepartoValesDescuento = costoRepartoValesDescuento;
	}

	public BigDecimal getCostoTotReprtoValDisEl() {
		return this.costoTotReprtoValDisEl;
	}

	public void setCostoTotReprtoValDisEl(BigDecimal costoTotReprtoValDisEl) {
		this.costoTotReprtoValDisEl = costoTotReprtoValDisEl;
	}

	public BigDecimal getCostoTotalAtencion() {
		return this.costoTotalAtencion;
	}

	public void setCostoTotalAtencion(BigDecimal costoTotalAtencion) {
		this.costoTotalAtencion = costoTotalAtencion;
	}

	public BigDecimal getCostoTotalGestionAdministra() {
		return this.costoTotalGestionAdministra;
	}

	public void setCostoTotalGestionAdministra(BigDecimal costoTotalGestionAdministra) {
		this.costoTotalGestionAdministra = costoTotalGestionAdministra;
	}

	public BigDecimal getCostoTotalImpresion() {
		return this.costoTotalImpresion;
	}

	public void setCostoTotalImpresion(BigDecimal costoTotalImpresion) {
		this.costoTotalImpresion = costoTotalImpresion;
	}

	public BigDecimal getCostoUnitCanjeLiqValFisi() {
		return this.costoUnitCanjeLiqValFisi;
	}

	public void setCostoUnitCanjeLiqValFisi(BigDecimal costoUnitCanjeLiqValFisi) {
		this.costoUnitCanjeLiqValFisi = costoUnitCanjeLiqValFisi;
	}

	public BigDecimal getCostoUnitCanjeValDigital() {
		return this.costoUnitCanjeValDigital;
	}

	public void setCostoUnitCanjeValDigital(BigDecimal costoUnitCanjeValDigital) {
		this.costoUnitCanjeValDigital = costoUnitCanjeValDigital;
	}

	public BigDecimal getCostoUnitEntregaValDisEl() {
		return this.costoUnitEntregaValDisEl;
	}

	public void setCostoUnitEntregaValDisEl(BigDecimal costoUnitEntregaValDisEl) {
		this.costoUnitEntregaValDisEl = costoUnitEntregaValDisEl;
	}

	public BigDecimal getCostoUnitReprtoValeDomici() {
		return this.costoUnitReprtoValeDomici;
	}

	public void setCostoUnitReprtoValeDomici(BigDecimal costoUnitReprtoValeDomici) {
		this.costoUnitReprtoValeDomici = costoUnitReprtoValeDomici;
	}

	public BigDecimal getCostoUnitarioImpresionVales() {
		return this.costoUnitarioImpresionVales;
	}

	public void setCostoUnitarioImpresionVales(BigDecimal costoUnitarioImpresionVales) {
		this.costoUnitarioImpresionVales = costoUnitarioImpresionVales;
	}

	public BigDecimal getCostoUnitarioPorAtencion() {
		return this.costoUnitarioPorAtencion;
	}

	public void setCostoUnitarioPorAtencion(BigDecimal costoUnitarioPorAtencion) {
		this.costoUnitarioPorAtencion = costoUnitarioPorAtencion;
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

	public BigDecimal getImpreValDsctoCliNoDisEl() {
		return this.impreValDsctoCliNoDisEl;
	}

	public void setImpreValDsctoCliNoDisEl(BigDecimal impreValDsctoCliNoDisEl) {
		this.impreValDsctoCliNoDisEl = impreValDsctoCliNoDisEl;
	}

	public BigDecimal getImpresionValDsctoCliDisEl() {
		return this.impresionValDsctoCliDisEl;
	}

	public void setImpresionValDsctoCliDisEl(BigDecimal impresionValDsctoCliDisEl) {
		this.impresionValDsctoCliDisEl = impresionValDsctoCliDisEl;
	}

	public Long getNumeroTotalAtencion() {
		return this.numeroTotalAtencion;
	}

	public void setNumeroTotalAtencion(Long numeroTotalAtencion) {
		this.numeroTotalAtencion = numeroTotalAtencion;
	}

	public Long getNumeroValesEntregados() {
		return this.numeroValesEntregados;
	}

	public void setNumeroValesEntregados(Long numeroValesEntregados) {
		this.numeroValesEntregados = numeroValesEntregados;
	}

	public Long getNumeroValesFisicosEmitidos() {
		return this.numeroValesFisicosEmitidos;
	}

	public void setNumeroValesFisicosEmitidos(Long numeroValesFisicosEmitidos) {
		this.numeroValesFisicosEmitidos = numeroValesFisicosEmitidos;
	}

	public Long getNumeroValesImpreso() {
		return this.numeroValesImpreso;
	}

	public void setNumeroValesImpreso(Long numeroValesImpreso) {
		this.numeroValesImpreso = numeroValesImpreso;
	}

	public Long getNumeroValesRepartidos() {
		return this.numeroValesRepartidos;
	}

	public void setNumeroValesRepartidos(Long numeroValesRepartidos) {
		this.numeroValesRepartidos = numeroValesRepartidos;
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

	public BigDecimal getUtilesMaterialesOficina() {
		return this.utilesMaterialesOficina;
	}

	public void setUtilesMaterialesOficina(BigDecimal utilesMaterialesOficina) {
		this.utilesMaterialesOficina = utilesMaterialesOficina;
	}

	public FiseFormato14BC getFiseFormato14BC() {
		return this.fiseFormato14BC;
	}

	public void setFiseFormato14BC(FiseFormato14BC fiseFormato14BC) {
		this.fiseFormato14BC = fiseFormato14BC;
	}

	public List<FiseFormato14BDOb> getFiseFormato14BDObs() {
		return this.fiseFormato14BDObs;
	}

	public void setFiseFormato14BDObs(List<FiseFormato14BDOb> fiseFormato14BDObs) {
		this.fiseFormato14BDObs = fiseFormato14BDObs;
	}

	public FiseFormato14BDOb addFiseFormato14BDOb(FiseFormato14BDOb fiseFormato14BDOb) {
		getFiseFormato14BDObs().add(fiseFormato14BDOb);
		fiseFormato14BDOb.setFiseFormato14BD(this);

		return fiseFormato14BDOb;
	}

	public FiseFormato14BDOb removeFiseFormato14BDOb(FiseFormato14BDOb fiseFormato14BDOb) {
		getFiseFormato14BDObs().remove(fiseFormato14BDOb);
		fiseFormato14BDOb.setFiseFormato14BD(null);

		return fiseFormato14BDOb;
	}

}