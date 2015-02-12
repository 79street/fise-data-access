package gob.osinergmin.fise.domain;

import java.io.Serializable;
import javax.persistence.*;

/**
 * The primary key class for the FISE_DESCRIPCION_ACTIVIDADES database table.
 * 
 */
@Embeddable
public class FiseDescripcionActividadePK implements Serializable {
	//default serial version id, required for serializable classes.
	private static final long serialVersionUID = 1L;

	private String formato;

	private String item;

	public FiseDescripcionActividadePK() {
	}
	public String getFormato() {
		return this.formato;
	}
	public void setFormato(String formato) {
		this.formato = formato;
	}
	public String getItem() {
		return this.item;
	}
	public void setItem(String item) {
		this.item = item;
	}

	public boolean equals(Object other) {
		if (this == other) {
			return true;
		}
		if (!(other instanceof FiseDescripcionActividadePK)) {
			return false;
		}
		FiseDescripcionActividadePK castOther = (FiseDescripcionActividadePK)other;
		return 
			this.formato.equals(castOther.formato)
			&& this.item.equals(castOther.item);
	}

	public int hashCode() {
		final int prime = 31;
		int hash = 17;
		hash = hash * prime + this.formato.hashCode();
		hash = hash * prime + this.item.hashCode();
		
		return hash;
	}
}