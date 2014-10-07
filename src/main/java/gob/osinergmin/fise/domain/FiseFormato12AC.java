package gob.osinergmin.fise.domain;

import java.io.Serializable;
import javax.persistence.*;
import java.math.BigDecimal;
import java.util.Date;
import java.util.List;


/**
 * The persistent class for the FISE_FORMATO_12A_C database table.
 * 
 */
@Entity
@Table(name="FISE_FORMATO_12A_C", schema="FISE")
@NamedQuery(name="FiseFormato12AC.findAll", query="SELECT f FROM FiseFormato12AC f")
public class FiseFormato12AC implements Serializable {
	private static final long serialVersionUID = 1L;

	@EmbeddedId
	private FiseFormato12ACPK id;

	@Temporal(TemporalType.TIMESTAMP)
	@Column(name="FECHA_ACTUALIZACION")
	private Date fechaActualizacion;

	@Temporal(TemporalType.TIMESTAMP)
	@Column(name="FECHA_CREACION")
	private Date fechaCreacion;

	@Column(name="TERMINAL_ACTUALIZACION")
	private String terminalActualizacion;

	@Column(name="TERMINAL_CREACION")
	private String terminalCreacion;

	@Column(name="TOTAL_A_RECONOCER")
	private BigDecimal totalAReconocer;

	@Column(name="USUARIO_ACTUALIZACION")
	private String usuarioActualizacion;

	@Column(name="USUARIO_CREACION")
	private String usuarioCreacion;

	//bi-directional many-to-one association to FiseGrupoInformacion
	@ManyToOne(cascade={CascadeType.ALL})
	@JoinColumn(name="ID_GRUPO_INFORMACION")
	private FiseGrupoInformacion fiseGrupoInformacion;

	//bi-directional many-to-one association to FiseFormato12AD
	@Transient
	@OneToMany(mappedBy="fiseFormato12AC", fetch=FetchType.EAGER)
	private List<FiseFormato12AD> fiseFormato12ADs;

	@Transient
	private String descEmpresa;
	@Transient
	private String descEstado;
	@Transient
	private String descMesPresentacion;
	@Transient
	private String descMesEjecucion;
	
	@Column(name="NOMBRE_ARCHIVO_EXCEL")
	private String archivoExcel;
	@Column(name="NOMBRE_ARCHIVO_TEXTO")
	private String archivoTexto;
	
	public FiseFormato12AC() {
	}

	public FiseFormato12ACPK getId() {
		return this.id;
	}

	public void setId(FiseFormato12ACPK id) {
		this.id = id;
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

	public BigDecimal getTotalAReconocer() {
		return this.totalAReconocer;
	}

	public void setTotalAReconocer(BigDecimal totalAReconocer) {
		this.totalAReconocer = totalAReconocer;
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

	public List<FiseFormato12AD> getFiseFormato12ADs() {
		return this.fiseFormato12ADs;
	}

	public void setFiseFormato12ADs(List<FiseFormato12AD> fiseFormato12ADs) {
		this.fiseFormato12ADs = fiseFormato12ADs;
	}

	public FiseFormato12AD addFiseFormato12AD(FiseFormato12AD fiseFormato12AD) {
		getFiseFormato12ADs().add(fiseFormato12AD);
		fiseFormato12AD.setFiseFormato12AC(this);

		return fiseFormato12AD;
	}

	public FiseFormato12AD removeFiseFormato12AD(FiseFormato12AD fiseFormato12AD) {
		getFiseFormato12ADs().remove(fiseFormato12AD);
		fiseFormato12AD.setFiseFormato12AC(null);

		return fiseFormato12AD;
	}

	public String getDescEmpresa() {
		return descEmpresa;
	}

	public void setDescEmpresa(String descEmpresa) {
		this.descEmpresa = descEmpresa;
	}

	public String getDescEstado() {
		return descEstado;
	}

	public void setDescEstado(String descEstado) {
		this.descEstado = descEstado;
	}

	public String getDescMesPresentacion() {
		return descMesPresentacion;
	}

	public void setDescMesPresentacion(String descMesPresentacion) {
		this.descMesPresentacion = descMesPresentacion;
	}

	public String getDescMesEjecucion() {
		return descMesEjecucion;
	}

	public void setDescMesEjecucion(String descMesEjecucion) {
		this.descMesEjecucion = descMesEjecucion;
	}

	public String getArchivoExcel() {
		return archivoExcel;
	}

	public void setArchivoExcel(String archivoExcel) {
		this.archivoExcel = archivoExcel;
	}

	public String getArchivoTexto() {
		return archivoTexto;
	}

	public void setArchivoTexto(String archivoTexto) {
		this.archivoTexto = archivoTexto;
	}



}