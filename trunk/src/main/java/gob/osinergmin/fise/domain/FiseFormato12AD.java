package gob.osinergmin.fise.domain;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

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


/**
 * The persistent class for the FISE_FORMATO_12A_D database table.
 * 
 */
@Entity
@Table(name="FISE_FORMATO_12A_D")
@NamedQuery(name="FiseFormato12AD.findAll", query="SELECT f FROM FiseFormato12AD f")
public class FiseFormato12AD implements Serializable {
	private static final long serialVersionUID = 1L;

	@EmbeddedId
	private FiseFormato12ADPK id;

	@Column(name="COSTO_ESTANDAR_UNIT_AG_AUT_GLP")
	private BigDecimal costoEstandarUnitAgAutGlp;

	@Column(name="COSTO_ESTANDAR_UNITARIO_EMPAD")
	private BigDecimal costoEstandarUnitarioEmpad;

	@Column(name="COSTO_TOTAL_EMPADRONAMIENTO")
	private BigDecimal costoTotalEmpadronamiento;

	@Column(name="COSTO_TOTAL_GEST_RED_AG_GLP")
	private BigDecimal costoTotalGestRedAgGlp;

	@Temporal(TemporalType.TIMESTAMP)
	@Column(name="FECHA_ACTUALIZACION")
	private Date fechaActualizacion;

	@Temporal(TemporalType.TIMESTAMP)
	@Column(name="FECHA_CREACION")
	private Date fechaCreacion;

	@Column(name="NUMERO_AGENTES_AUTORIZ_GLP")
	private long numeroAgentesAutorizGlp;

	@Column(name="NUMERO_EMPADRONADOS")
	private long numeroEmpadronados;

	@Column(name="TERMINAL_ACTUALIZACION")
	private String terminalActualizacion;

	@Column(name="TERMINAL_CREACION")
	private String terminalCreacion;

	@Column(name="TOTAL_ACTIVIDADES_EXTRAORD")
	private BigDecimal totalActividadesExtraord;

	@Column(name="TOTAL_DESPLAZAMIENTO_PERSONAL")
	private BigDecimal totalDesplazamientoPersonal;

	@Column(name="USUARIO_ACTUALIZACION")
	private String usuarioActualizacion;

	@Column(name="USUARIO_CREACION")
	private String usuarioCreacion;

	//bi-directional many-to-one association to FiseFormato12AC
	@ManyToOne
	@JoinColumns({
		@JoinColumn(name="ANO_EJECUCION_GASTO", referencedColumnName="ANO_EJECUCION_GASTO"),
		@JoinColumn(name="ANO_PRESENTACION", referencedColumnName="ANO_PRESENTACION"),
		@JoinColumn(name="COD_EMPRESA", referencedColumnName="COD_EMPRESA"),
		@JoinColumn(name="MES_EJECUCION_GASTO", referencedColumnName="MES_EJECUCION_GASTO"),
		@JoinColumn(name="MES_PRESENTACION", referencedColumnName="MES_PRESENTACION"),
		@JoinColumn(name="ETAPA", referencedColumnName="ETAPA")
		})
	private FiseFormato12AC fiseFormato12AC;

	//bi-directional many-to-one association to FiseZonaBenef
	@ManyToOne
	@JoinColumn(name="ID_ZONA_BENEF")
	private FiseZonaBenef fiseZonaBenef;

	//bi-directional many-to-one association to FiseFormato12ADObs
	@OneToMany(mappedBy="fiseFormato12AD")
	private List<FiseFormato12ADObs> fiseFormato12ADObs;

	public FiseFormato12AD() {
	}

	public FiseFormato12ADPK getId() {
		return this.id;
	}

	public void setId(FiseFormato12ADPK id) {
		this.id = id;
	}

	public BigDecimal getCostoEstandarUnitAgAutGlp() {
		return this.costoEstandarUnitAgAutGlp;
	}

	public void setCostoEstandarUnitAgAutGlp(BigDecimal costoEstandarUnitAgAutGlp) {
		this.costoEstandarUnitAgAutGlp = costoEstandarUnitAgAutGlp;
	}

	public BigDecimal getCostoEstandarUnitarioEmpad() {
		return this.costoEstandarUnitarioEmpad;
	}

	public void setCostoEstandarUnitarioEmpad(BigDecimal costoEstandarUnitarioEmpad) {
		this.costoEstandarUnitarioEmpad = costoEstandarUnitarioEmpad;
	}

	public BigDecimal getCostoTotalEmpadronamiento() {
		return this.costoTotalEmpadronamiento;
	}

	public void setCostoTotalEmpadronamiento(BigDecimal costoTotalEmpadronamiento) {
		this.costoTotalEmpadronamiento = costoTotalEmpadronamiento;
	}

	public BigDecimal getCostoTotalGestRedAgGlp() {
		return this.costoTotalGestRedAgGlp;
	}

	public void setCostoTotalGestRedAgGlp(BigDecimal costoTotalGestRedAgGlp) {
		this.costoTotalGestRedAgGlp = costoTotalGestRedAgGlp;
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

	public long getNumeroAgentesAutorizGlp() {
		return this.numeroAgentesAutorizGlp;
	}

	public void setNumeroAgentesAutorizGlp(long numeroAgentesAutorizGlp) {
		this.numeroAgentesAutorizGlp = numeroAgentesAutorizGlp;
	}

	public long getNumeroEmpadronados() {
		return this.numeroEmpadronados;
	}

	public void setNumeroEmpadronados(long numeroEmpadronados) {
		this.numeroEmpadronados = numeroEmpadronados;
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

	public FiseFormato12AC getFiseFormato12AC() {
		return this.fiseFormato12AC;
	}

	public void setFiseFormato12AC(FiseFormato12AC fiseFormato12AC) {
		this.fiseFormato12AC = fiseFormato12AC;
	}

	public FiseZonaBenef getFiseZonaBenef() {
		return this.fiseZonaBenef;
	}

	public void setFiseZonaBenef(FiseZonaBenef fiseZonaBenef) {
		this.fiseZonaBenef = fiseZonaBenef;
	}

	public List<FiseFormato12ADObs> getFiseFormato12ADObs() {
		return this.fiseFormato12ADObs;
	}

	public void setFiseFormato12ADObs(List<FiseFormato12ADObs> fiseFormato12ADObs) {
		this.fiseFormato12ADObs = fiseFormato12ADObs;
	}

	public FiseFormato12ADObs addFiseFormato12ADOb(FiseFormato12ADObs fiseFormato12ADOb) {
		getFiseFormato12ADObs().add(fiseFormato12ADOb);
		fiseFormato12ADOb.setFiseFormato12AD(this);

		return fiseFormato12ADOb;
	}

	public FiseFormato12ADObs removeFiseFormato12ADOb(FiseFormato12ADObs fiseFormato12ADOb) {
		getFiseFormato12ADObs().remove(fiseFormato12ADOb);
		fiseFormato12ADOb.setFiseFormato12AD(null);

		return fiseFormato12ADOb;
	}

}