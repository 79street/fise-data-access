package gob.osinergmin.fise.domain;

import java.io.Serializable;

import javax.persistence.*;

import java.util.Date;
import java.util.List;


/**
 * The persistent class for the FISE_FORMATO_13A_D database table.
 * 
 */
@Entity
@Table(name="FISE_FORMATO_13A_D", schema="FISE")
@NamedQuery(name="FiseFormato13AD.findAll", query="SELECT f FROM FiseFormato13AD f")
public class FiseFormato13AD implements Serializable {
	private static final long serialVersionUID = 1L;

	@EmbeddedId
	private FiseFormato13ADPK id;

	@Column(name="ANO_ALTA")
	private Long anoAlta;

	@Column(name="ANO_FIN_VIGENCIA")
	private Long anoFinVigencia;

	@Column(name="ANO_INICIO_VIGENCIA")
	private Long anoInicioVigencia;

	@Column(name="DESCRIPCION_LOCALIDAD")
	private String descripcionLocalidad;

	@Temporal(TemporalType.DATE)
	@Column(name="FECHA_ACTUALIZACION")
	private Date fechaActualizacion;

	@Temporal(TemporalType.DATE)
	@Column(name="FECHA_CREACION")
	private Date fechaCreacion;

	@Column(name="MES_ALTA")
	private Long mesAlta;

	@Column(name="NOMBRE_SEDE_ATIENDE")
	private String nombreSedeAtiende;

	@Column(name="NUMERO_BENEFI_POTE_SECT_TIPICO")
	private Long numeroBenefiPoteSectTipico;

	@Column(name="TERMINAL_ACTUALIZACION")
	private String terminalActualizacion;

	@Column(name="TERMINAL_CREACION")
	private String terminalCreacion;

	@Column(name="USUARIO_ACTUALIZACION")
	private String usuarioActualizacion;

	@Column(name="USUARIO_CREACION")
	private String usuarioCreacion;

	//bi-directional many-to-one association to FiseFormato13AC
	@Transient
	@ManyToOne(cascade={CascadeType.ALL})
	@JoinColumns({
		@JoinColumn(name="ANO_PRESENTACION", referencedColumnName="ANO_PRESENTACION"),
		@JoinColumn(name="COD_EMPRESA", referencedColumnName="COD_EMPRESA"),
		@JoinColumn(name="ETAPA", referencedColumnName="ETAPA"),
		@JoinColumn(name="MES_PRESENTACION", referencedColumnName="MES_PRESENTACION")
		})
	private FiseFormato13AC fiseFormato13AC;

	//bi-directional many-to-one association to FiseFormato13ADOb
	@Transient
	@OneToMany(mappedBy="fiseFormato13AD", cascade={CascadeType.ALL})
	private List<FiseFormato13ADOb> fiseFormato13ADObs;

	public FiseFormato13AD() {
	}

	public FiseFormato13ADPK getId() {
		return this.id;
	}

	public void setId(FiseFormato13ADPK id) {
		this.id = id;
	}

	public Long getAnoAlta() {
		return this.anoAlta;
	}

	public void setAnoAlta(Long anoAlta) {
		this.anoAlta = anoAlta;
	}

	public Long getAnoFinVigencia() {
		return this.anoFinVigencia;
	}

	public void setAnoFinVigencia(Long anoFinVigencia) {
		this.anoFinVigencia = anoFinVigencia;
	}

	public Long getAnoInicioVigencia() {
		return this.anoInicioVigencia;
	}

	public void setAnoInicioVigencia(Long anoInicioVigencia) {
		this.anoInicioVigencia = anoInicioVigencia;
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

	public Date getFechaCreacion() {
		return this.fechaCreacion;
	}

	public void setFechaCreacion(Date fechaCreacion) {
		this.fechaCreacion = fechaCreacion;
	}

	public Long getMesAlta() {
		return this.mesAlta;
	}

	public void setMesAlta(Long mesAlta) {
		this.mesAlta = mesAlta;
	}

	public String getNombreSedeAtiende() {
		return this.nombreSedeAtiende;
	}

	public void setNombreSedeAtiende(String nombreSedeAtiende) {
		this.nombreSedeAtiende = nombreSedeAtiende;
	}

	public Long getNumeroBenefiPoteSectTipico() {
		return this.numeroBenefiPoteSectTipico;
	}

	public void setNumeroBenefiPoteSectTipico(Long numeroBenefiPoteSectTipico) {
		this.numeroBenefiPoteSectTipico = numeroBenefiPoteSectTipico;
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

	public FiseFormato13AC getFiseFormato13AC() {
		return this.fiseFormato13AC;
	}

	public void setFiseFormato13AC(FiseFormato13AC fiseFormato13AC) {
		this.fiseFormato13AC = fiseFormato13AC;
	}

	public List<FiseFormato13ADOb> getFiseFormato13ADObs() {
		return this.fiseFormato13ADObs;
	}

	public void setFiseFormato13ADObs(List<FiseFormato13ADOb> fiseFormato13ADObs) {
		this.fiseFormato13ADObs = fiseFormato13ADObs;
	}

	public FiseFormato13ADOb addFiseFormato13ADOb(FiseFormato13ADOb fiseFormato13ADOb) {
		getFiseFormato13ADObs().add(fiseFormato13ADOb);
		fiseFormato13ADOb.setFiseFormato13AD(this);

		return fiseFormato13ADOb;
	}

	public FiseFormato13ADOb removeFiseFormato13ADOb(FiseFormato13ADOb fiseFormato13ADOb) {
		getFiseFormato13ADObs().remove(fiseFormato13ADOb);
		fiseFormato13ADOb.setFiseFormato13AD(null);

		return fiseFormato13ADOb;
	}

}