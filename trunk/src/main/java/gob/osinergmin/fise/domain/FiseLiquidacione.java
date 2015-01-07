package gob.osinergmin.fise.domain;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;


/**
 * The persistent class for the FISE_LIQUIDACIONES database table.
 * 
 */
@Entity
@Table(name="FISE_LIQUIDACIONES")
public class FiseLiquidacione implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	private Long correlativo;

	@Column(name="ANO_EJECUCION_GASTO")
	private Long anoEjecucionGasto;

	@Column(name="ANO_FIN_VIGENCIA")
	private Long anoFinVigencia;

	@Column(name="ANO_INICIO_VIGENCIA")
	private Long anoInicioVigencia;

	@Column(name="ANO_PRESENTACION")
	private Long anoPresentacion;

	@Column(name="COD_EMPRESA")
	private String codEmpresa;

	private String etapa;

	@Temporal(TemporalType.TIMESTAMP)
	@Column(name="FECHA_ACTUALIZACION")
	private Date fechaActualizacion;

	@Temporal(TemporalType.TIMESTAMP)
	@Column(name="FECHA_CREACION")
	private Date fechaCreacion;

	@Temporal(TemporalType.TIMESTAMP)
	@Column(name="FECHA_ENVIO_DEFINITIVO")
	private Date fechaEnvioDefinitivo;

	private String formato;

	@Column(name="MES_EJECUCION_GASTO")
	private Long mesEjecucionGasto;

	@Column(name="MES_PRESENTACION")
	private Long mesPresentacion;

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
	
	//bi-directional many-to-one association to FiseLiquidacionesMotivosNo
	@OneToMany(mappedBy="fiseLiquidacione")
	private List<FiseLiquidacionesMotivosNo> fiseLiquidacionesMotivosNos;

	public FiseLiquidacione() {
	}

	public long getCorrelativo() {
		return this.correlativo;
	}

	public void setCorrelativo(long correlativo) {
		this.correlativo = correlativo;
	}

	public Long getAnoEjecucionGasto() {
		return this.anoEjecucionGasto;
	}

	public void setAnoEjecucionGasto(Long anoEjecucionGasto) {
		this.anoEjecucionGasto = anoEjecucionGasto;
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

	public Long getAnoPresentacion() {
		return this.anoPresentacion;
	}

	public void setAnoPresentacion(Long anoPresentacion) {
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

	public Date getFechaEnvioDefinitivo() {
		return this.fechaEnvioDefinitivo;
	}

	public void setFechaEnvioDefinitivo(Date fechaEnvioDefinitivo) {
		this.fechaEnvioDefinitivo = fechaEnvioDefinitivo;
	}

	public String getFormato() {
		return this.formato;
	}

	public void setFormato(String formato) {
		this.formato = formato;
	}

	public Long getMesEjecucionGasto() {
		return this.mesEjecucionGasto;
	}

	public void setMesEjecucionGasto(Long mesEjecucionGasto) {
		this.mesEjecucionGasto = mesEjecucionGasto;
	}

	public Long getMesPresentacion() {
		return this.mesPresentacion;
	}

	public void setMesPresentacion(Long mesPresentacion) {
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
	
	public List<FiseLiquidacionesMotivosNo> getFiseLiquidacionesMotivosNos() {
		return this.fiseLiquidacionesMotivosNos;
	}

	public void setFiseLiquidacionesMotivosNos(List<FiseLiquidacionesMotivosNo> fiseLiquidacionesMotivosNos) {
		this.fiseLiquidacionesMotivosNos = fiseLiquidacionesMotivosNos;
	}

	public FiseLiquidacionesMotivosNo addFiseLiquidacionesMotivosNo(FiseLiquidacionesMotivosNo fiseLiquidacionesMotivosNo) {
		getFiseLiquidacionesMotivosNos().add(fiseLiquidacionesMotivosNo);
		fiseLiquidacionesMotivosNo.setFiseLiquidacione(this);

		return fiseLiquidacionesMotivosNo;
	}

	public FiseLiquidacionesMotivosNo removeFiseLiquidacionesMotivosNo(FiseLiquidacionesMotivosNo fiseLiquidacionesMotivosNo) {
		getFiseLiquidacionesMotivosNos().remove(fiseLiquidacionesMotivosNo);
		fiseLiquidacionesMotivosNo.setFiseLiquidacione(null);

		return fiseLiquidacionesMotivosNo;
	}

}