package gob.osinergmin.fise.domain;

import java.io.Serializable;
import javax.persistence.*;
import java.util.Date;


/**
 * The persistent class for the FISE_LIQUIDACIONES_MOTIVOS_NO database table.
 * 
 */
@Entity
@Table(name="FISE_LIQUIDACIONES_MOTIVOS_NO")
public class FiseLiquidacionesMotivosNo implements Serializable {
	private static final long serialVersionUID = 1L;

	@EmbeddedId
	private FiseLiquidacionesMotivosNoPK id;

	@Column(name="DESCRIPCION_MOTIVO")
	private String descripcionMotivo;

	private String estado;

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

	@Column(name="USUARIO_ACTUALIZACION")
	private String usuarioActualizacion;

	@Column(name="USUARIO_CREACION")
	private String usuarioCreacion;

	//bi-directional many-to-one association to FiseLiquidacione
	@ManyToOne(fetch=FetchType.LAZY)
	@JoinColumn(name="CORRELATIVO",nullable=false, insertable=false, updatable=false)
	private FiseLiquidacione fiseLiquidacione;

	public FiseLiquidacionesMotivosNo() {
	}

	public FiseLiquidacionesMotivosNoPK getId() {
		return this.id;
	}

	public void setId(FiseLiquidacionesMotivosNoPK id) {
		this.id = id;
	}

	public String getDescripcionMotivo() {
		return this.descripcionMotivo;
	}

	public void setDescripcionMotivo(String descripcionMotivo) {
		this.descripcionMotivo = descripcionMotivo;
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

	public FiseLiquidacione getFiseLiquidacione() {
		return this.fiseLiquidacione;
	}

	public void setFiseLiquidacione(FiseLiquidacione fiseLiquidacione) {
		this.fiseLiquidacione = fiseLiquidacione;
	}

}