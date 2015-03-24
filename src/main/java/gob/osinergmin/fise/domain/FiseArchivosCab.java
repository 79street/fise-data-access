package gob.osinergmin.fise.domain;

import java.io.Serializable;
import javax.persistence.*;
import java.math.BigDecimal;
import java.util.Date;
import java.util.List;


/**
 * The persistent class for the FISE_ARCHIVOS_CAB database table.
 * 
 */
@Entity
@Table(name="FISE_ARCHIVOS_CAB")
public class FiseArchivosCab implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	private long correlativo;

	@Column(name="ANO_EJECUCION_GASTO")
	private BigDecimal anoEjecucionGasto;

	@Column(name="ANO_FIN_VIGENCIA")
	private BigDecimal anoFinVigencia;

	@Column(name="ANO_INICIO_VIGENCIA")
	private BigDecimal anoInicioVigencia;

	@Column(name="ANO_PRESENTACION")
	private long anoPresentacion;

	@Column(name="COD_EMPRESA")
	private String codEmpresa;

	private String etapa;

	@Temporal(TemporalType.TIMESTAMP)
	@Column(name="FECHA_ACTUALIZACION")
	private Date fechaActualizacion;

	@Temporal(TemporalType.TIMESTAMP)
	@Column(name="FECHA_CREACION")
	private Date fechaCreacion;

	private String formato;

	@Column(name="MES_EJECUCION_GASTO")
	private BigDecimal mesEjecucionGasto;

	@Column(name="MES_PRESENTACION")
	private long mesPresentacion;

	@Column(name="TERMINAL_ACTUALIZACION")
	private String terminalActualizacion;

	@Column(name="TERMINAL_CREACION")
	private String terminalCreacion;

	@Column(name="USUARIO_ACTUALIZACION")
	private String usuarioActualizacion;

	@Column(name="USUARIO_CREACION")
	private String usuarioCreacion;

	//bi-directional many-to-one association to FiseGrupoInformacion
	@ManyToOne(fetch=FetchType.LAZY)
	@JoinColumn(name="ID_GRUPO_INFORMACION")
	private FiseGrupoInformacion fiseGrupoInformacion;

	//bi-directional many-to-one association to FiseArchivosDet
	@OneToMany(mappedBy="fiseArchivosCab")
	private List<FiseArchivosDet> fiseArchivosDets;

	public FiseArchivosCab() {
	}

	public long getCorrelativo() {
		return this.correlativo;
	}

	public void setCorrelativo(long correlativo) {
		this.correlativo = correlativo;
	}

	public BigDecimal getAnoEjecucionGasto() {
		return this.anoEjecucionGasto;
	}

	public void setAnoEjecucionGasto(BigDecimal anoEjecucionGasto) {
		this.anoEjecucionGasto = anoEjecucionGasto;
	}

	public BigDecimal getAnoFinVigencia() {
		return this.anoFinVigencia;
	}

	public void setAnoFinVigencia(BigDecimal anoFinVigencia) {
		this.anoFinVigencia = anoFinVigencia;
	}

	public BigDecimal getAnoInicioVigencia() {
		return this.anoInicioVigencia;
	}

	public void setAnoInicioVigencia(BigDecimal anoInicioVigencia) {
		this.anoInicioVigencia = anoInicioVigencia;
	}

	public long getAnoPresentacion() {
		return this.anoPresentacion;
	}

	public void setAnoPresentacion(long anoPresentacion) {
		this.anoPresentacion = anoPresentacion;
	}

	public String getCodEmpresa() {
		return this.codEmpresa;
	}

	public void setCodEmpresa(String codEmpresa) {
		this.codEmpresa = codEmpresa;
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

	public String getFormato() {
		return this.formato;
	}

	public void setFormato(String formato) {
		this.formato = formato;
	}

	public BigDecimal getMesEjecucionGasto() {
		return this.mesEjecucionGasto;
	}

	public void setMesEjecucionGasto(BigDecimal mesEjecucionGasto) {
		this.mesEjecucionGasto = mesEjecucionGasto;
	}

	public long getMesPresentacion() {
		return this.mesPresentacion;
	}

	public void setMesPresentacion(long mesPresentacion) {
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

	public FiseGrupoInformacion getFiseGrupoInformacion() {
		return this.fiseGrupoInformacion;
	}

	public void setFiseGrupoInformacion(FiseGrupoInformacion fiseGrupoInformacion) {
		this.fiseGrupoInformacion = fiseGrupoInformacion;
	}

	public List<FiseArchivosDet> getFiseArchivosDets() {
		return this.fiseArchivosDets;
	}

	public void setFiseArchivosDets(List<FiseArchivosDet> fiseArchivosDets) {
		this.fiseArchivosDets = fiseArchivosDets;
	}

	public FiseArchivosDet addFiseArchivosDet(FiseArchivosDet fiseArchivosDet) {
		getFiseArchivosDets().add(fiseArchivosDet);
		fiseArchivosDet.setFiseArchivosCab(this);

		return fiseArchivosDet;
	}

	public FiseArchivosDet removeFiseArchivosDet(FiseArchivosDet fiseArchivosDet) {
		getFiseArchivosDets().remove(fiseArchivosDet);
		fiseArchivosDet.setFiseArchivosCab(null);

		return fiseArchivosDet;
	}

}