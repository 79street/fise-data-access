package gob.osinergmin.fise.domain;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.persistence.Transient;


/**
 * The persistent class for the FISE_PERIODO_ENVIO database table.
 * 
 */
@Entity
@Table(name="FISE_PERIODO_ENVIO")
public class FisePeriodoEnvio implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.SEQUENCE, generator="my_entity_seq_perido_envio")
	@SequenceGenerator(name="my_entity_seq_perido_envio",sequenceName="FISE_PER_ENV_SEQ", allocationSize = 1)
	private long secuencia;

	@Column(name="ANO_FIN_VIGENCIA")
	private Integer anoFinVigencia;

	@Column(name="ANO_INICIO_VIGENCIA")
	private Integer anoInicioVigencia;

	@Column(name="ANO_PRESENTACION")
	private Integer anoPresentacion;

	@Temporal(TemporalType.DATE)
	private Date cegifecdocobs;

	@Temporal(TemporalType.DATE)
	private Date cegifecemanotobs;

	@Temporal(TemporalType.DATE)
	private Date cegifecenv;

	@Temporal(TemporalType.DATE)
	private Date cegifecenvdocobs;

	@Temporal(TemporalType.DATE)
	private Date cegifecrecgart;

	@Temporal(TemporalType.DATE)
	private Date cegifecrecnotobs;

	@Temporal(TemporalType.DATE)
	private Date cegifecrecosi;

	@Temporal(TemporalType.DATE)
	private Date cegifecvenlvtoobs;

	private String ceginrodoc;

	private String ceginrodocobs;

	private String ceginroexp;

	private String ceginrotra;

	@Column(name="COD_EMPRESA")
	private String codEmpresa;

	@Temporal(TemporalType.TIMESTAMP)
	private Date desde;

	@Column(name="DIAS_NOTIFICACION_ANTES_CIERRE")
	private Integer diasNotificacionAntesCierre;

	private String estado;

	private String etapa;
	
	@Temporal(TemporalType.TIMESTAMP)
	@Column(name="FECHA_AMPLIADA")
	private Date fechaAmpliada;

	@Temporal(TemporalType.TIMESTAMP)
	@Column(name="FECHA_ACTUALIZACION")
	private Date fechaActualizacion;

	@Temporal(TemporalType.TIMESTAMP)
	@Column(name="FECHA_CREACION")
	private Date fechaCreacion;

	@Column(name="FLAG_ENVIO_CON_OBSERVACIONES")
	private String flagEnvioConObservaciones;

	@Column(name="FLAG_HABILITA_COSTOS_D_I_F14C")
	private String flagHabilitaCostosDIF14c;

	@Column(name="FLAG_MOSTRAR_ANO_MES_EJEC")
	private String flagMostrarAnoMesEjec;

	private String formato;

	@Temporal(TemporalType.TIMESTAMP)
	private Date hasta;

	@Column(name="MES_PRESENTACION")
	private Integer mesPresentacion;

	@Column(name="TERMINAL_ACTUALIZACION")
	private String terminalActualizacion;

	@Column(name="TERMINAL_CREACION")
	private String terminalCreacion;

	@Column(name="USUARIO_ACTUALIZACION")
	private String usuarioActualizacion;

	@Column(name="USUARIO_CREACION")
	private String usuarioCreacion;
	
	
	// valores de combo
	@Transient
	private String codigoItem;
	@Transient
	private String descripcionItem;
	@Transient
	private String flagPeriodoEjecucion;
	@Transient
	private String anioInicioVig;
	@Transient
	private String anioFinVig;
	@Transient
	private String flagHabilitaCostos;
	
	@Transient
	private String descEmpresa;	
	
	@Transient
	private String descMesPresentacion;	
	
	@Transient
	private String descEstado;	
	

	public FisePeriodoEnvio() {
	}

	public long getSecuencia() {
		return this.secuencia;
	}

	public void setSecuencia(long secuencia) {
		this.secuencia = secuencia;
	}

	public Integer getAnoFinVigencia() {
		return this.anoFinVigencia;
	}

	public void setAnoFinVigencia(Integer anoFinVigencia) {
		this.anoFinVigencia = anoFinVigencia;
	}

	public Integer getAnoInicioVigencia() {
		return this.anoInicioVigencia;
	}

	public void setAnoInicioVigencia(Integer anoInicioVigencia) {
		this.anoInicioVigencia = anoInicioVigencia;
	}

	public Integer getAnoPresentacion() {
		return this.anoPresentacion;
	}

	public void setAnoPresentacion(Integer anoPresentacion) {
		this.anoPresentacion = anoPresentacion;
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

	public String getCodEmpresa() {
		return this.codEmpresa;
	}

	public void setCodEmpresa(String codEmpresa) {
		this.codEmpresa = codEmpresa;
	}

	public Date getDesde() {
		return this.desde;
	}

	public void setDesde(Date desde) {
		this.desde = desde;
	}

	public Integer getDiasNotificacionAntesCierre() {
		return this.diasNotificacionAntesCierre;
	}

	public void setDiasNotificacionAntesCierre(Integer diasNotificacionAntesCierre) {
		this.diasNotificacionAntesCierre = diasNotificacionAntesCierre;
	}

	public String getEstado() {
		return this.estado;
	}

	public void setEstado(String estado) {
		this.estado = estado;
	}

	public String getEtapa() {
		return this.etapa;
	}

	public void setEtapa(String etapa) {
		this.etapa = etapa;
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

	public String getFlagEnvioConObservaciones() {
		return this.flagEnvioConObservaciones;
	}

	public void setFlagEnvioConObservaciones(String flagEnvioConObservaciones) {
		this.flagEnvioConObservaciones = flagEnvioConObservaciones;
	}

	public String getFlagHabilitaCostosDIF14c() {
		return this.flagHabilitaCostosDIF14c;
	}

	public void setFlagHabilitaCostosDIF14c(String flagHabilitaCostosDIF14c) {
		this.flagHabilitaCostosDIF14c = flagHabilitaCostosDIF14c;
	}

	public String getFlagMostrarAnoMesEjec() {
		return this.flagMostrarAnoMesEjec;
	}

	public void setFlagMostrarAnoMesEjec(String flagMostrarAnoMesEjec) {
		this.flagMostrarAnoMesEjec = flagMostrarAnoMesEjec;
	}

	public String getFormato() {
		return this.formato;
	}

	public void setFormato(String formato) {
		this.formato = formato;
	}

	public Date getHasta() {
		return this.hasta;
	}

	public void setHasta(Date hasta) {
		this.hasta = hasta;
	}

	public Integer getMesPresentacion() {
		return this.mesPresentacion;
	}

	public void setMesPresentacion(Integer mesPresentacion) {
		this.mesPresentacion = mesPresentacion;
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

	public String getCodigoItem() {
		return codigoItem;
	}

	public void setCodigoItem(String codigoItem) {
		this.codigoItem = codigoItem;
	}

	public String getDescripcionItem() {
		return descripcionItem;
	}

	public void setDescripcionItem(String descripcionItem) {
		this.descripcionItem = descripcionItem;
	}

	public String getFlagPeriodoEjecucion() {
		return flagPeriodoEjecucion;
	}

	public void setFlagPeriodoEjecucion(String flagPeriodoEjecucion) {
		this.flagPeriodoEjecucion = flagPeriodoEjecucion;
	}

	public String getAnioInicioVig() {
		return anioInicioVig;
	}

	public void setAnioInicioVig(String anioInicioVig) {
		this.anioInicioVig = anioInicioVig;
	}

	public String getAnioFinVig() {
		return anioFinVig;
	}

	public void setAnioFinVig(String anioFinVig) {
		this.anioFinVig = anioFinVig;
	}

	public String getFlagHabilitaCostos() {
		return flagHabilitaCostos;
	}

	public void setFlagHabilitaCostos(String flagHabilitaCostos) {
		this.flagHabilitaCostos = flagHabilitaCostos;
	}

	public String getDescEmpresa() {
		return descEmpresa;
	}

	public void setDescEmpresa(String descEmpresa) {
		this.descEmpresa = descEmpresa;
	}

	public String getDescMesPresentacion() {
		return descMesPresentacion;
	}

	public void setDescMesPresentacion(String descMesPresentacion) {
		this.descMesPresentacion = descMesPresentacion;
	}

	public Date getFechaAmpliada() {
		return fechaAmpliada;
	}

	public void setFechaAmpliada(Date fechaAmpliada) {
		this.fechaAmpliada = fechaAmpliada;
	}

	public String getDescEstado() {
		return descEstado;
	}

	public void setDescEstado(String descEstado) {
		this.descEstado = descEstado;
	}
	
	
	

}