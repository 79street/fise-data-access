package gob.osinergmin.fise.domain;

import java.io.Serializable;
import java.math.BigDecimal;

import javax.persistence.*;

import java.util.Date;
import java.util.List;


/**
 * The persistent class for the FISE_FORMATO_12B_C database table.
 * 
 */
@Entity
@Table(name="FISE_FORMATO_12B_C")
@NamedQuery(name="FiseFormato12BC.findAll", query="SELECT f FROM FiseFormato12BC f")
public class FiseFormato12BC implements Serializable {
	private static final long serialVersionUID = 1L;

	@EmbeddedId
	private FiseFormato12BCPK id;

	@Temporal(TemporalType.DATE)
	@Column(name="FECHA_ACTUALIZACION")
	private Date fechaActualizacion;

	@Temporal(TemporalType.DATE)
	@Column(name="FECHA_CREACION")
	private Date fechaCreacion;

	@Temporal(TemporalType.DATE)
	@Column(name="FECHA_ENVIO_DEFINITIVO")
	private Date fechaEnvioDefinitivo;

	

	@Column(name="NOMBRE_ARCHIVO_EXCEL")
	private String nombreArchivoExcel;

	@Column(name="NOMBRE_ARCHIVO_TEXTO")
	private String nombreArchivoTexto;

	@Column(name="TERMINAL_ACTUALIZACION")
	private String terminalActualizacion;

	@Column(name="TERMINAL_CREACION")
	private String terminalCreacion;

	@Column(name="TOTAL_RECONOCER")
	private BigDecimal totalReconocer;

	@Column(name="USUARIO_ACTUALIZACION")
	private String usuarioActualizacion;

	@Column(name="USUARIO_CREACION")
	private String usuarioCreacion;
	
	@ManyToOne
	@JoinColumn(name="ID_GRUPO_INFORMACION")
	private FiseGrupoInformacion fiseGrupoInformacion;
	
	@ManyToOne
	@JoinColumn(name="COD_EMPRESA", insertable=false, updatable=false)
	private AdmEmpresa admEmpresa;

	
	//bi-directional many-to-one association to FiseFormato12bD
	@Transient
	@OneToMany(mappedBy="fiseFormato12BC")
	
	private List<FiseFormato12BD> fiseFormato12BDs;
	
	
	//bi-directional many-to-one association to FiseGrupoInformacion
	
	
	public FiseFormato12BC() {
	}

	public FiseFormato12BCPK getId() {
		return this.id;
	}

	public void setId(FiseFormato12BCPK id) {
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

	public Date getFechaEnvioDefinitivo() {
		return this.fechaEnvioDefinitivo;
	}

	public void setFechaEnvioDefinitivo(Date fechaEnvioDefinitivo) {
		this.fechaEnvioDefinitivo = fechaEnvioDefinitivo;
	}

	
	public String getNombreArchivoExcel() {
		return this.nombreArchivoExcel;
	}

	public void setNombreArchivoExcel(String nombreArchivoExcel) {
		this.nombreArchivoExcel = nombreArchivoExcel;
	}

	public String getNombreArchivoTexto() {
		return this.nombreArchivoTexto;
	}

	public void setNombreArchivoTexto(String nombreArchivoTexto) {
		this.nombreArchivoTexto = nombreArchivoTexto;
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

	public List<FiseFormato12BD> getFiseFormato12BDs() {
		return this.fiseFormato12BDs;
	}

	public void setFiseFormato12BDs(List<FiseFormato12BD> fiseFormato12BDs) {
		this.fiseFormato12BDs = fiseFormato12BDs;
	}

	public FiseFormato12BD addFiseFormato12BD(FiseFormato12BD fiseFormato12BD) {
		getFiseFormato12BDs().add(fiseFormato12BD);
		fiseFormato12BD.setFiseFormato12BC(this);

		return fiseFormato12BD;
	}

	public FiseFormato12BD removeFiseFormato12BD(FiseFormato12BD fiseFormato12BD) {
		getFiseFormato12BDs().remove(fiseFormato12BD);
		fiseFormato12BD.setFiseFormato12BC(null);

		return fiseFormato12BD;
	}

	public FiseGrupoInformacion getFiseGrupoInformacion() {
		return fiseGrupoInformacion;
	}

	public void setFiseGrupoInformacion(FiseGrupoInformacion fiseGrupoInformacion) {
		this.fiseGrupoInformacion = fiseGrupoInformacion;
	}

	public AdmEmpresa getAdmEmpresa() {
		return admEmpresa;
	}

	public void setAdmEmpresa(AdmEmpresa admEmpresa) {
		this.admEmpresa = admEmpresa;
	}
	
	

}