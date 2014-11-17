package gob.osinergmin.fise.bean;

import java.io.Serializable;

public class MensajeErrorBean implements Serializable {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private int id;
	private String codigo;
	private String descripcion;
	private String descZonaBenef;
	private String descCodSectorTipico;
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getCodigo() {
		return codigo;
	}
	public void setCodigo(String codigo) {
		this.codigo = codigo;
	}
	public String getDescripcion() {
		return descripcion;
	}
	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}
	public String getDescZonaBenef() {
		return descZonaBenef;
	}
	public void setDescZonaBenef(String descZonaBenef) {
		this.descZonaBenef = descZonaBenef;
	}
	public String getDescCodSectorTipico() {
		return descCodSectorTipico;
	}
	public void setDescCodSectorTipico(String descCodSectorTipico) {
		this.descCodSectorTipico = descCodSectorTipico;
	}

}
