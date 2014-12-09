package gob.osinergmin.fise.domain;

import java.io.Serializable;
import java.math.BigDecimal;

import javax.persistence.*;

import java.util.Date;
import java.util.List;


/**
 * The persistent class for the FISE_FORMATO_12B_D database table.
 * 
 */
@Entity
@Table(name="FISE_FORMATO_12B_D")
@NamedQuery(name="FiseFormato12BD.findAll", query="SELECT f FROM FiseFormato12BD f")
public class FiseFormato12BD implements Serializable {
	private static final long serialVersionUID = 1L;

	@EmbeddedId
	private FiseFormato12BDPK id;

	@Column(name="COSTO_ESTANDAR_UNIT_ATENCION")
	private BigDecimal costoEstandarUnitAtencion;

	@Column(name="COSTO_ESTANDAR_UNIT_VAL_DG_CAN")
	private BigDecimal costoEstandarUnitValDgCan;

	@Column(name="COSTO_ESTANDAR_UNIT_VAL_DIS_EL")
	private BigDecimal costoEstandarUnitValDisEl;

	@Column(name="COSTO_ESTANDAR_UNIT_VAL_FI_CAN")
	private BigDecimal costoEstandarUnitValFiCan;

	@Column(name="COSTO_ESTANDAR_UNIT_VALE_IMPRE")
	private BigDecimal costoEstandarUnitValeImpre;

	@Column(name="COSTO_ESTANDAR_UNIT_VALE_REPAR")
	private BigDecimal costoEstandarUnitValeRepar;

	@Column(name="COSTO_TOTAL_ATENCION_CONS_RECL")
	private BigDecimal costoTotalAtencionConsRecl;

	@Column(name="COSTO_TOTAL_CANJE_LIQ_VALE_DIG")
	private BigDecimal costoTotalCanjeLiqValeDig;

	@Column(name="COSTO_TOTAL_CANJE_LIQ_VALE_FIS")
	private BigDecimal costoTotalCanjeLiqValeFis;

	@Column(name="COSTO_TOTAL_ENTREGA_VAL_DIS_EL")
	private BigDecimal costoTotalEntregaValDisEl;

	@Column(name="COSTO_TOTAL_IMPRESION_VALE")
	private BigDecimal costoTotalImpresionVale;

	@Column(name="COSTO_TOTAL_REPARTO_VALES_DOMI")
	private BigDecimal costoTotalRepartoValesDomi;

	@Temporal(TemporalType.DATE)
	@Column(name="FECHA_ACTUALIZACION")
	private Date fechaActualizacion;

	@Temporal(TemporalType.DATE)
	@Column(name="FECHA_CREACION")
	private Date fechaCreacion;

	@Column(name="NUMERO_ATENCIONES")
	private Integer numeroAtenciones;

	@Column(name="NUMERO_VALES_DIGITAL_CANJEADOS")
	private Integer numeroValesDigitalCanjeados;

	@Column(name="NUMERO_VALES_ENTREGADO_DIS_EL")
	private Integer numeroValesEntregadoDisEl;

	@Column(name="NUMERO_VALES_FISICOS_CANJEADOS")
	private Integer numeroValesFisicosCanjeados;

	@Column(name="NUMERO_VALES_IMPRESO")
	private Integer numeroValesImpreso;

	@Column(name="NUMERO_VALES_REPARTIDOS_DOMI")
	private Integer numeroValesRepartidosDomi;

	@Column(name="TERMINAL_ACTUALIZACION")
	private String terminalActualizacion;

	@Column(name="TERMINAL_CREACION")
	private String terminalCreacion;

	@Column(name="TOTAL_ACTIVIDADES_EXTRAORD")
	private BigDecimal totalActividadesExtraord;

	@Column(name="TOTAL_DESPLAZAMIENTO_PERSONAL")
	private BigDecimal totalDesplazamientoPersonal;

	@Column(name="TOTAL_GESTION_ADMINISTRATIVA")
	private BigDecimal totalGestionAdministrativa;

	@Column(name="TOTAL_RECONOCER")
	private BigDecimal totalReconocer;

	@Column(name="USUARIO_ACTUALIZACION")
	private String usuarioActualizacion;

	@Column(name="USUARIO_CREACION")
	private String usuarioCreacion;
	
	
	
	//bi-directional many-to-one association to FiseFormato12bDOb
	@Transient
		@OneToMany(mappedBy="fiseFormato12BD")
		private List<FiseFormato12BDOb> fiseFormato12BDObs;

	//bi-directional many-to-one association to FiseFormato12bC
	@Transient
	@ManyToOne
	@JoinColumns({
		@JoinColumn(name="ANO_EJECUCION_GASTO", referencedColumnName="ANO_EJECUCION_GASTO"),
		@JoinColumn(name="ANO_PRESENTACION", referencedColumnName="ANO_PRESENTACION"),
		@JoinColumn(name="COD_EMPRESA", referencedColumnName="COD_EMPRESA"),
		@JoinColumn(name="ETAPA", referencedColumnName="ETAPA"),
		@JoinColumn(name="MES_EJECUCION_GASTO", referencedColumnName="MES_EJECUCION_GASTO"),
		@JoinColumn(name="MES_PRESENTACION", referencedColumnName="MES_PRESENTACION")
		})
	private FiseFormato12BC fiseFormato12BC;

	
    
	public FiseFormato12BD() {
	}

	public FiseFormato12BDPK getId() {
		return this.id;
	}

	public void setId(FiseFormato12BDPK id) {
		this.id = id;
	}

	public BigDecimal getCostoEstandarUnitAtencion() {
		return this.costoEstandarUnitAtencion;
	}

	public void setCostoEstandarUnitAtencion(BigDecimal costoEstandarUnitAtencion) {
		this.costoEstandarUnitAtencion = costoEstandarUnitAtencion;
	}

	public BigDecimal getCostoEstandarUnitValDgCan() {
		return this.costoEstandarUnitValDgCan;
	}

	public void setCostoEstandarUnitValDgCan(BigDecimal costoEstandarUnitValDgCan) {
		this.costoEstandarUnitValDgCan = costoEstandarUnitValDgCan;
	}

	public BigDecimal getCostoEstandarUnitValDisEl() {
		return this.costoEstandarUnitValDisEl;
	}

	public void setCostoEstandarUnitValDisEl(BigDecimal costoEstandarUnitValDisEl) {
		this.costoEstandarUnitValDisEl = costoEstandarUnitValDisEl;
	}

	public BigDecimal getCostoEstandarUnitValFiCan() {
		return this.costoEstandarUnitValFiCan;
	}

	public void setCostoEstandarUnitValFiCan(BigDecimal costoEstandarUnitValFiCan) {
		this.costoEstandarUnitValFiCan = costoEstandarUnitValFiCan;
	}

	public BigDecimal getCostoEstandarUnitValeImpre() {
		return this.costoEstandarUnitValeImpre;
	}

	public void setCostoEstandarUnitValeImpre(BigDecimal costoEstandarUnitValeImpre) {
		this.costoEstandarUnitValeImpre = costoEstandarUnitValeImpre;
	}

	public BigDecimal getCostoEstandarUnitValeRepar() {
		return this.costoEstandarUnitValeRepar;
	}

	public void setCostoEstandarUnitValeRepar(BigDecimal costoEstandarUnitValeRepar) {
		this.costoEstandarUnitValeRepar = costoEstandarUnitValeRepar;
	}

	public BigDecimal getCostoTotalAtencionConsRecl() {
		return this.costoTotalAtencionConsRecl;
	}

	public void setCostoTotalAtencionConsRecl(BigDecimal costoTotalAtencionConsRecl) {
		this.costoTotalAtencionConsRecl = costoTotalAtencionConsRecl;
	}

	public BigDecimal getCostoTotalCanjeLiqValeDig() {
		return this.costoTotalCanjeLiqValeDig;
	}

	public void setCostoTotalCanjeLiqValeDig(BigDecimal costoTotalCanjeLiqValeDig) {
		this.costoTotalCanjeLiqValeDig = costoTotalCanjeLiqValeDig;
	}

	public BigDecimal getCostoTotalCanjeLiqValeFis() {
		return this.costoTotalCanjeLiqValeFis;
	}

	public void setCostoTotalCanjeLiqValeFis(BigDecimal costoTotalCanjeLiqValeFis) {
		this.costoTotalCanjeLiqValeFis = costoTotalCanjeLiqValeFis;
	}

	public BigDecimal getCostoTotalEntregaValDisEl() {
		return this.costoTotalEntregaValDisEl;
	}

	public void setCostoTotalEntregaValDisEl(BigDecimal costoTotalEntregaValDisEl) {
		this.costoTotalEntregaValDisEl = costoTotalEntregaValDisEl;
	}

	public BigDecimal getCostoTotalImpresionVale() {
		return this.costoTotalImpresionVale;
	}

	public void setCostoTotalImpresionVale(BigDecimal costoTotalImpresionVale) {
		this.costoTotalImpresionVale = costoTotalImpresionVale;
	}

	public BigDecimal getCostoTotalRepartoValesDomi() {
		return this.costoTotalRepartoValesDomi;
	}

	public void setCostoTotalRepartoValesDomi(BigDecimal costoTotalRepartoValesDomi) {
		this.costoTotalRepartoValesDomi = costoTotalRepartoValesDomi;
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

	public Integer getNumeroAtenciones() {
		return this.numeroAtenciones;
	}

	public void setNumeroAtenciones(Integer numeroAtenciones) {
		this.numeroAtenciones = numeroAtenciones;
	}

	public Integer getNumeroValesDigitalCanjeados() {
		return this.numeroValesDigitalCanjeados;
	}

	public void setNumeroValesDigitalCanjeados(Integer numeroValesDigitalCanjeados) {
		this.numeroValesDigitalCanjeados = numeroValesDigitalCanjeados;
	}

	public Integer getNumeroValesEntregadoDisEl() {
		return this.numeroValesEntregadoDisEl;
	}

	public void setNumeroValesEntregadoDisEl(Integer numeroValesEntregadoDisEl) {
		this.numeroValesEntregadoDisEl = numeroValesEntregadoDisEl;
	}

	public Integer getNumeroValesFisicosCanjeados() {
		return this.numeroValesFisicosCanjeados;
	}

	public void setNumeroValesFisicosCanjeados(Integer numeroValesFisicosCanjeados) {
		this.numeroValesFisicosCanjeados = numeroValesFisicosCanjeados;
	}

	public Integer getNumeroValesImpreso() {
		return this.numeroValesImpreso;
	}

	public void setNumeroValesImpreso(Integer numeroValesImpreso) {
		this.numeroValesImpreso = numeroValesImpreso;
	}

	public Integer getNumeroValesRepartidosDomi() {
		return this.numeroValesRepartidosDomi;
	}

	public void setNumeroValesRepartidosDomi(Integer numeroValesRepartidosDomi) {
		this.numeroValesRepartidosDomi = numeroValesRepartidosDomi;
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

	public BigDecimal getTotalActividadesExtraord() {
		return this.totalActividadesExtraord;
	}

	public void setTotalActividadesExtraord(BigDecimal totalActividadesExtraord) {
		this.totalActividadesExtraord = totalActividadesExtraord;
	}

	public BigDecimal getTotalDesplazamientoPersonal() {
		return this.totalDesplazamientoPersonal;
	}

	public void setTotalDesplazamientoPersonal(BigDecimal totalDesplazamientoPersonal) {
		this.totalDesplazamientoPersonal = totalDesplazamientoPersonal;
	}

	public BigDecimal getTotalGestionAdministrativa() {
		return this.totalGestionAdministrativa;
	}

	public void setTotalGestionAdministrativa(BigDecimal totalGestionAdministrativa) {
		this.totalGestionAdministrativa = totalGestionAdministrativa;
	}

	public BigDecimal getTotalReconocer() {
		return this.totalReconocer;
	}

	public void setTotalReconocer(BigDecimal totalReconocer) {
		this.totalReconocer = totalReconocer;
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

	public FiseFormato12BC getFiseFormato12BC() {
		return this.fiseFormato12BC;
	}

	public void setFiseFormato12BC(FiseFormato12BC fiseFormato12BC) {
		this.fiseFormato12BC = fiseFormato12BC;
	}

	public List<FiseFormato12BDOb> getFiseFormato12BDObs() {
		return this.fiseFormato12BDObs;
	}

	public void setFiseFormato12BDObs(List<FiseFormato12BDOb> fiseFormato12BDObs) {
		this.fiseFormato12BDObs = fiseFormato12BDObs;
	}

	public FiseFormato12BDOb addFiseFormato12bDOb(FiseFormato12BDOb fiseFormato12BDOb) {
		getFiseFormato12BDObs().add(fiseFormato12BDOb);
		fiseFormato12BDOb.setFiseFormato12BD(this);

		return fiseFormato12BDOb;
	}

	public FiseFormato12BDOb removeFiseFormato12BDOb(FiseFormato12BDOb fiseFormato12BDOb) {
		getFiseFormato12BDObs().remove(fiseFormato12BDOb);
		fiseFormato12BDOb.setFiseFormato12BD(null);

		return fiseFormato12BDOb;
	}

}