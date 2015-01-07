package gob.osinergmin.fise.domain;

import java.io.Serializable;
import javax.persistence.*;

/**
 * The primary key class for the FISE_LIQUIDACIONES_MOTIVOS_NO database table.
 * 
 */
@Embeddable
public class FiseLiquidacionesMotivosNoPK implements Serializable {
	//default serial version id, required for serializable classes.
	private static final long serialVersionUID = 1L;

	private long correlativo;

	private long item;

	public FiseLiquidacionesMotivosNoPK() {
	}
	public long getCorrelativo() {
		return this.correlativo;
	}
	public void setCorrelativo(long correlativo) {
		this.correlativo = correlativo;
	}
	public long getItem() {
		return this.item;
	}
	public void setItem(long item) {
		this.item = item;
	}

	public boolean equals(Object other) {
		if (this == other) {
			return true;
		}
		if (!(other instanceof FiseLiquidacionesMotivosNoPK)) {
			return false;
		}
		FiseLiquidacionesMotivosNoPK castOther = (FiseLiquidacionesMotivosNoPK)other;
		return 
			(this.correlativo == castOther.correlativo)
			&& (this.item == castOther.item);
	}

	public int hashCode() {
		final int prime = 31;
		int hash = 17;
		hash = hash * prime + ((int) (this.correlativo ^ (this.correlativo >>> 32)));
		hash = hash * prime + ((int) (this.item ^ (this.item >>> 32)));
		
		return hash;
	}
}