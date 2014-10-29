package gob.osinergmin.fise.domain;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.JoinColumns;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;


/**
 * The persistent class for the FISE_FORMATO_14C_D database table.
 * 
 */
@Entity
@Table(name="FISE_FORMATO_14C_D")
public class FiseFormato14CD implements Serializable {
	private static final long serialVersionUID = 1L;

	@EmbeddedId
	private FiseFormato14CDPK id;

	@Column(name="CANTIDAD_COSTO_DIRECTO")
	private Long cantCostDirecto;
	
	@Column(name="CANTIDAD_COSTO_INDIRECTO")
	private Long cantCostIndirecto;

	@Column(name="COSTO_DIRECTO")
	private BigDecimal costoDirecto;

	@Column(name="COSTO_INDIRECTO")
	private BigDecimal costoIndirecto;

	@Temporal(TemporalType.DATE)
	@Column(name="FECHA_ACTUALIZACION")
	private Date fechaActualizacion;

	@Temporal(TemporalType.DATE)
	@Column(name="FECHA_CREACION")
	private Date fechaCreacion;

	@Column(name="TERMINAL_ACTUALIZACION")
	private String terminalActualizacion;

	@Column(name="TERMINAL_CREACION")
	private String terminalCreacion;

	@Column(name="USUARIO_ACTUALIZACION")
	private String usuarioActualizacion;

	@Column(name="USUARIO_CREACION")
	private String usuarioCreacion;

	//bi-directional many-to-one association to FiseFormato14cC
	@ManyToOne(fetch=FetchType.LAZY)
	@JoinColumns({
		@JoinColumn(name="ANO_FIN_VIGENCIA", referencedColumnName="ANO_FIN_VIGENCIA",nullable=false, insertable=false, updatable=false),
		@JoinColumn(name="ANO_INICIO_VIGENCIA", referencedColumnName="ANO_INICIO_VIGENCIA",nullable=false, insertable=false, updatable=false),
		@JoinColumn(name="ANO_PRESENTACION", referencedColumnName="ANO_PRESENTACION",nullable=false, insertable=false, updatable=false),
		@JoinColumn(name="COD_EMPRESA", referencedColumnName="COD_EMPRESA",nullable=false, insertable=false, updatable=false),
		@JoinColumn(name="ETAPA", referencedColumnName="ETAPA",nullable=false, insertable=false, updatable=false),
		@JoinColumn(name="MES_PRESENTACION", referencedColumnName="MES_PRESENTACION",nullable=false, insertable=false, updatable=false)
		})
	private FiseFormato14CC fiseFormato14cC;

	//bi-directional many-to-one association to FiseTipPersonal
	@ManyToOne(fetch=FetchType.LAZY)
	@JoinColumn(name="ID_TIP_PERSONAL",nullable=false, insertable=false, updatable=false)
	private FiseTipPersonal fiseTipPersonal;

	//bi-directional many-to-one association to FiseZonaBenef
	@ManyToOne(fetch=FetchType.LAZY)
	@JoinColumn(name="ID_ZONA_BENEF",nullable=false, insertable=false, updatable=false)
	private FiseZonaBenef fiseZonaBenef;

	//bi-directional many-to-one association to FiseFormato14cDOb
	@OneToMany(mappedBy="fiseFormato14cD")
	private List<FiseFormato14CDOb> fiseFormato14cDObs;

	public FiseFormato14CD() {
	}

	public FiseFormato14CDPK getId() {
		return this.id;
	}

	public void setId(FiseFormato14CDPK id) {
		this.id = id;
	}	

	public Long getCantCostDirecto() {
		return cantCostDirecto;
	}

	public void setCantCostDirecto(Long cantCostDirecto) {
		this.cantCostDirecto = cantCostDirecto;
	}

	public Long getCantCostIndirecto() {
		return cantCostIndirecto;
	}

	public void setCantCostIndirecto(Long cantCostIndirecto) {
		this.cantCostIndirecto = cantCostIndirecto;
	}

	public BigDecimal getCostoDirecto() {
		return this.costoDirecto;
	}

	public void setCostoDirecto(BigDecimal costoDirecto) {
		this.costoDirecto = costoDirecto;
	}

	public BigDecimal getCostoIndirecto() {
		return this.costoIndirecto;
	}

	public void setCostoIndirecto(BigDecimal costoIndirecto) {
		this.costoIndirecto = costoIndirecto;
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

	public FiseFormato14CC getFiseFormato14cC() {
		return this.fiseFormato14cC;
	}

	public void setFiseFormato14cC(FiseFormato14CC fiseFormato14cC) {
		this.fiseFormato14cC = fiseFormato14cC;
	}

	public FiseTipPersonal getFiseTipPersonal() {
		return this.fiseTipPersonal;
	}

	public void setFiseTipPersonal(FiseTipPersonal fiseTipPersonal) {
		this.fiseTipPersonal = fiseTipPersonal;
	}

	public FiseZonaBenef getFiseZonaBenef() {
		return this.fiseZonaBenef;
	}

	public void setFiseZonaBenef(FiseZonaBenef fiseZonaBenef) {
		this.fiseZonaBenef = fiseZonaBenef;
	}

	public List<FiseFormato14CDOb> getFiseFormato14cDObs() {
		return this.fiseFormato14cDObs;
	}

	public void setFiseFormato14cDObs(List<FiseFormato14CDOb> fiseFormato14cDObs) {
		this.fiseFormato14cDObs = fiseFormato14cDObs;
	}

	public FiseFormato14CDOb addFiseFormato14cDOb(FiseFormato14CDOb fiseFormato14cDOb) {
		getFiseFormato14cDObs().add(fiseFormato14cDOb);
		fiseFormato14cDOb.setFiseFormato14cD(this);

		return fiseFormato14cDOb;
	}

	public FiseFormato14CDOb removeFiseFormato14cDOb(FiseFormato14CDOb fiseFormato14cDOb) {
		getFiseFormato14cDObs().remove(fiseFormato14cDOb);
		fiseFormato14cDOb.setFiseFormato14cD(null);

		return fiseFormato14cDOb;
	}

}