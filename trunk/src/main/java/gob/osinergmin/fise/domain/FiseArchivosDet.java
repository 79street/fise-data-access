package gob.osinergmin.fise.domain;

import java.io.Serializable;
import javax.persistence.*;
import java.util.Date;


/**
 * The persistent class for the FISE_ARCHIVOS_DET database table.
 * 
 */
@Entity
@Table(name="FISE_ARCHIVOS_DET")
public class FiseArchivosDet implements Serializable {
	private static final long serialVersionUID = 1L;

	@EmbeddedId
	private FiseArchivosDetPK id;

	private String estado;

	@Temporal(TemporalType.TIMESTAMP)
	@Column(name="FECHA_ACTUALIZACION")
	private Date fechaActualizacion;

	@Temporal(TemporalType.TIMESTAMP)
	@Column(name="FECHA_CREACION")
	private Date fechaCreacion;

	@Column(name="NOMBRE_ARCHIVO_FISICO")
	private String nombreArchivoFisico;

	@Column(name="TERMINAL_ACTUALIZACION")
	private String terminalActualizacion;

	@Column(name="TERMINAL_CREACION")
	private String terminalCreacion;

	@Column(name="USUARIO_ACTUALIZACION")
	private String usuarioActualizacion;

	@Column(name="USUARIO_CREACION")
	private String usuarioCreacion;

	//bi-directional many-to-one association to FiseArchivosCab
	@ManyToOne(fetch=FetchType.LAZY)
	@JoinColumn(name="CORRELATIVO")
	private FiseArchivosCab fiseArchivosCab;

	public FiseArchivosDet() {
	}

	public FiseArchivosDetPK getId() {
		return this.id;
	}

	public void setId(FiseArchivosDetPK id) {
		this.id = id;
	}

	public String getEstado() {
		return this.estado;
	}

	public void setEstado(String estado) {
		this.estado = estado;
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

	public String getNombreArchivoFisico() {
		return this.nombreArchivoFisico;
	}

	public void setNombreArchivoFisico(String nombreArchivoFisico) {
		this.nombreArchivoFisico = nombreArchivoFisico;
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

	public FiseArchivosCab getFiseArchivosCab() {
		return this.fiseArchivosCab;
	}

	public void setFiseArchivosCab(FiseArchivosCab fiseArchivosCab) {
		this.fiseArchivosCab = fiseArchivosCab;
	}

}