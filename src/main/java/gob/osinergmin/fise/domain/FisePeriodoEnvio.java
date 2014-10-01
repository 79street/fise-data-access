package gob.osinergmin.fise.domain;

import java.io.Serializable;

import javax.persistence.*;

import java.math.BigDecimal;
import java.util.Date;


/**
 * The persistent class for the FISE_PERIODO_ENVIO database table.
 * 
 */
@Entity
@Table(name="FISE_PERIODO_ENVIO", schema="FISE")
@NamedQuery(name="FisePeriodoEnvio.findAll", query="SELECT f FROM FisePeriodoEnvio f")
public class FisePeriodoEnvio implements Serializable {
	private static final long serialVersionUID = 1L;

	@EmbeddedId
	private FisePeriodoEnvioPK id;

	@Temporal(TemporalType.TIMESTAMP)
	private Date cegifecdocobs;

	@Temporal(TemporalType.TIMESTAMP)
	private Date cegifecemanotobs;

	@Temporal(TemporalType.TIMESTAMP)
	private Date cegifecenv;

	@Temporal(TemporalType.TIMESTAMP)
	private Date cegifecenvdocobs;

	@Temporal(TemporalType.TIMESTAMP)
	private Date cegifecrecgart;

	@Temporal(TemporalType.TIMESTAMP)
	private Date cegifecrecnotobs;

	@Temporal(TemporalType.TIMESTAMP)
	private Date cegifecrecosi;

	@Temporal(TemporalType.TIMESTAMP)
	private Date cegifecvenlvtoobs;

	private String ceginrodoc;

	private String ceginrodocobs;

	private String ceginroexp;

	private String ceginrotra;

	@Temporal(TemporalType.TIMESTAMP)
	private Date desde;

	@Column(name="DIAS_NOTIFICACION_ANTES_CIERRE")
	private BigDecimal diasNotificacionAntesCierre;

	@Temporal(TemporalType.TIMESTAMP)
	@Column(name="FECHA_ACTUALIZACION")
	private Date fechaActualizacion;

	@Temporal(TemporalType.TIMESTAMP)
	@Column(name="FECHA_CREACION")
	private Date fechaCreacion;

	@Temporal(TemporalType.TIMESTAMP)
	private Date hasta;

	@Column(name="TERMINAL_ACTUALIZACION")
	private String terminalActualizacion;

	@Column(name="TERMINAL_CREACION")
	private String terminalCreacion;

	@Column(name="USUARIO_ACTUALIZACION")
	private String usuarioActualizacion;

	@Column(name="USUARIO_CREACION")
	private String usuarioCreacion;

	public FisePeriodoEnvio() {
	}

	public FisePeriodoEnvioPK getId() {
		return this.id;
	}

	public void setId(FisePeriodoEnvioPK id) {
		this.id = id;
	}

	public Date getCegifecdocobs() {
		return this.cegifecdocobs;
	}

	public void setCegifecdocobs(Date cegifecdocobs) {
		this.cegifecdocobs = cegifecdocobs;
	}

	public Date getCegifecemanotobs() {
		return this.cegifecemanotobs;
	}

	public void setCegifecemanotobs(Date cegifecemanotobs) {
		this.cegifecemanotobs = cegifecemanotobs;
	}

	public Date getCegifecenv() {
		return this.cegifecenv;
	}

	public void setCegifecenv(Date cegifecenv) {
		this.cegifecenv = cegifecenv;
	}

	public Date getCegifecenvdocobs() {
		return this.cegifecenvdocobs;
	}

	public void setCegifecenvdocobs(Date cegifecenvdocobs) {
		this.cegifecenvdocobs = cegifecenvdocobs;
	}

	public Date getCegifecrecgart() {
		return this.cegifecrecgart;
	}

	public void setCegifecrecgart(Date cegifecrecgart) {
		this.cegifecrecgart = cegifecrecgart;
	}

	public Date getCegifecrecnotobs() {
		return this.cegifecrecnotobs;
	}

	public void setCegifecrecnotobs(Date cegifecrecnotobs) {
		this.cegifecrecnotobs = cegifecrecnotobs;
	}

	public Date getCegifecrecosi() {
		return this.cegifecrecosi;
	}

	public void setCegifecrecosi(Date cegifecrecosi) {
		this.cegifecrecosi = cegifecrecosi;
	}

	public Date getCegifecvenlvtoobs() {
		return this.cegifecvenlvtoobs;
	}

	public void setCegifecvenlvtoobs(Date cegifecvenlvtoobs) {
		this.cegifecvenlvtoobs = cegifecvenlvtoobs;
	}

	public String getCeginrodoc() {
		return this.ceginrodoc;
	}

	public void setCeginrodoc(String ceginrodoc) {
		this.ceginrodoc = ceginrodoc;
	}

	public String getCeginrodocobs() {
		return this.ceginrodocobs;
	}

	public void setCeginrodocobs(String ceginrodocobs) {
		this.ceginrodocobs = ceginrodocobs;
	}

	public String getCeginroexp() {
		return this.ceginroexp;
	}

	public void setCeginroexp(String ceginroexp) {
		this.ceginroexp = ceginroexp;
	}

	public String getCeginrotra() {
		return this.ceginrotra;
	}

	public void setCeginrotra(String ceginrotra) {
		this.ceginrotra = ceginrotra;
	}

	public Date getDesde() {
		return this.desde;
	}

	public void setDesde(Date desde) {
		this.desde = desde;
	}

	public BigDecimal getDiasNotificacionAntesCierre() {
		return this.diasNotificacionAntesCierre;
	}

	public void setDiasNotificacionAntesCierre(BigDecimal diasNotificacionAntesCierre) {
		this.diasNotificacionAntesCierre = diasNotificacionAntesCierre;
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

	public Date getHasta() {
		return this.hasta;
	}

	public void setHasta(Date hasta) {
		this.hasta = hasta;
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

}