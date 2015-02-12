package gob.osinergmin.fise.domain;

import java.io.Serializable;
import javax.persistence.*;
import java.util.Date;
import java.util.List;


/**
 * The persistent class for the FISE_DESCRIPCION_ACTIVIDADES database table.
 * 
 */
@Entity
@Table(name="FISE_DESCRIPCION_ACTIVIDADES")
public class FiseDescripcionActividade implements Serializable {
	private static final long serialVersionUID = 1L;

	@EmbeddedId
	private FiseDescripcionActividadePK id;

	private String descripcion;	

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

	//bi-directional many-to-one association to FiseLiquidacionesMotivosNo
	@OneToMany(mappedBy="fiseDescripcionActividade")
	private List<FiseLiquidacionesMotivosNo> fiseLiquidacionesMotivosNos;

	public FiseDescripcionActividade() {
	}

	public FiseDescripcionActividadePK getId() {
		return this.id;
	}

	public void setId(FiseDescripcionActividadePK id) {
		this.id = id;
	}

	public String getDescripcion() {
		return this.descripcion;
	}

	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
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

	public List<FiseLiquidacionesMotivosNo> getFiseLiquidacionesMotivosNos() {
		return this.fiseLiquidacionesMotivosNos;
	}

	public void setFiseLiquidacionesMotivosNos(List<FiseLiquidacionesMotivosNo> fiseLiquidacionesMotivosNos) {
		this.fiseLiquidacionesMotivosNos = fiseLiquidacionesMotivosNos;
	}

	public FiseLiquidacionesMotivosNo addFiseLiquidacionesMotivosNo(FiseLiquidacionesMotivosNo fiseLiquidacionesMotivosNo) {
		getFiseLiquidacionesMotivosNos().add(fiseLiquidacionesMotivosNo);
		fiseLiquidacionesMotivosNo.setFiseDescripcionActividade(this);

		return fiseLiquidacionesMotivosNo;
	}

	public FiseLiquidacionesMotivosNo removeFiseLiquidacionesMotivosNo(FiseLiquidacionesMotivosNo fiseLiquidacionesMotivosNo) {
		getFiseLiquidacionesMotivosNos().remove(fiseLiquidacionesMotivosNo);
		fiseLiquidacionesMotivosNo.setFiseDescripcionActividade(null);

		return fiseLiquidacionesMotivosNo;
	}	

}