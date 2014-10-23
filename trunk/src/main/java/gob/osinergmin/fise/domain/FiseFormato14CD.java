package gob.osinergmin.fise.domain;

import java.io.Serializable;

import javax.persistence.*;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;


/**
 * The persistent class for the FISE_FORMATO_14C_D database table.
 * 
 */
@Entity
@Table(name="FISE_FORMATO_14C_D", schema="FISE")
@NamedQuery(name="FiseFormato14CD.findAll", query="SELECT f FROM FiseFormato14CD f")
public class FiseFormato14CD implements Serializable {
	private static final long serialVersionUID = 1L;

	@EmbeddedId
	private FiseFormato14CDPK id;

	private Long cantidad;

	@Column(name="COSTO_TOT_TIP_PERSONA_UTL_ESCR")
	private BigDecimal costoTotTipPersonaUtlEscr;

	@Column(name="COSTO_TOTAL_ZONA")
	private BigDecimal costoTotalZona;

	@Column(name="COSTO_UNITARIO")
	private BigDecimal costoUnitario;

	@Temporal(TemporalType.TIMESTAMP)
	@Column(name="FECHA_ACTUALIZACION")
	private Date fechaActualizacion;

	@Temporal(TemporalType.TIMESTAMP)
	@Column(name="FECHA_CREACION")
	private Date fechaCreacion;

	@Column(name="ID_TIP_COSTO_GESTION")
	private BigDecimal idTipCostoGestion;

	@Column(name="ID_TIP_PERSONAL")
	private BigDecimal idTipPersonal;

	@Column(name="NOMBRE_SEDE")
	private String nombreSede;

	@Column(name="NUMERO_ITEM_TIPO_COSTO_GESTION")
	private Long numeroItemTipoCostoGestion;

	@Column(name="NUMRO_TOT_BENE_EMP_DIC_PER_ANT")
	private Long numroTotBeneEmpDicPerAnt;

	@Column(name="TERMINAL_ACTUALIZACION")
	private String terminalActualizacion;

	@Column(name="TERMINAL_CREACION")
	private String terminalCreacion;

	@Column(name="USUARIO_ACTUALIZACION")
	private String usuarioActualizacion;

	@Column(name="USUARIO_CREACION")
	private String usuarioCreacion;

	//bi-directional many-to-one association to FiseFormato14CC
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
	private FiseFormato14CC fiseFormato14CC;

	//bi-directional many-to-one association to FiseFormato14CDOb
	@Transient
	@OneToMany(mappedBy="fiseFormato14CD")
	private List<FiseFormato14CDOb> fiseFormato14CDObs;

	public FiseFormato14CD() {
	}

	public FiseFormato14CDPK getId() {
		return this.id;
	}

	public void setId(FiseFormato14CDPK id) {
		this.id = id;
	}

	public Long getCantidad() {
		return this.cantidad;
	}

	public void setCantidad(Long cantidad) {
		this.cantidad = cantidad;
	}

	public BigDecimal getCostoTotTipPersonaUtlEscr() {
		return this.costoTotTipPersonaUtlEscr;
	}

	public void setCostoTotTipPersonaUtlEscr(BigDecimal costoTotTipPersonaUtlEscr) {
		this.costoTotTipPersonaUtlEscr = costoTotTipPersonaUtlEscr;
	}

	public BigDecimal getCostoTotalZona() {
		return this.costoTotalZona;
	}

	public void setCostoTotalZona(BigDecimal costoTotalZona) {
		this.costoTotalZona = costoTotalZona;
	}

	public BigDecimal getCostoUnitario() {
		return this.costoUnitario;
	}

	public void setCostoUnitario(BigDecimal costoUnitario) {
		this.costoUnitario = costoUnitario;
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

	public BigDecimal getIdTipCostoGestion() {
		return this.idTipCostoGestion;
	}

	public void setIdTipCostoGestion(BigDecimal idTipCostoGestion) {
		this.idTipCostoGestion = idTipCostoGestion;
	}

	public BigDecimal getIdTipPersonal() {
		return this.idTipPersonal;
	}

	public void setIdTipPersonal(BigDecimal idTipPersonal) {
		this.idTipPersonal = idTipPersonal;
	}

	public String getNombreSede() {
		return this.nombreSede;
	}

	public void setNombreSede(String nombreSede) {
		this.nombreSede = nombreSede;
	}

	public Long getNumeroItemTipoCostoGestion() {
		return this.numeroItemTipoCostoGestion;
	}

	public void setNumeroItemTipoCostoGestion(Long numeroItemTipoCostoGestion) {
		this.numeroItemTipoCostoGestion = numeroItemTipoCostoGestion;
	}

	public Long getNumroTotBeneEmpDicPerAnt() {
		return this.numroTotBeneEmpDicPerAnt;
	}

	public void setNumroTotBeneEmpDicPerAnt(Long numroTotBeneEmpDicPerAnt) {
		this.numroTotBeneEmpDicPerAnt = numroTotBeneEmpDicPerAnt;
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

	public FiseFormato14CC getFiseFormato14CC() {
		return this.fiseFormato14CC;
	}

	public void setFiseFormato14CC(FiseFormato14CC fiseFormato14CC) {
		this.fiseFormato14CC = fiseFormato14CC;
	}

	public List<FiseFormato14CDOb> getFiseFormato14CDObs() {
		return this.fiseFormato14CDObs;
	}

	public void setFiseFormato14CDObs(List<FiseFormato14CDOb> fiseFormato14CDObs) {
		this.fiseFormato14CDObs = fiseFormato14CDObs;
	}

	public FiseFormato14CDOb addFiseFormato14CDOb(FiseFormato14CDOb fiseFormato14CDOb) {
		getFiseFormato14CDObs().add(fiseFormato14CDOb);
		fiseFormato14CDOb.setFiseFormato14CD(this);

		return fiseFormato14CDOb;
	}

	public FiseFormato14CDOb removeFiseFormato14CDOb(FiseFormato14CDOb fiseFormato14CDOb) {
		getFiseFormato14CDObs().remove(fiseFormato14CDOb);
		fiseFormato14CDOb.setFiseFormato14CD(null);

		return fiseFormato14CDOb;
	}

}