package gob.osinergmin.fise.domain;

import java.io.Serializable;
import javax.persistence.*;

/**
 * The primary key class for the FISE_MCARGOFIJO database table.
 * 
 */
@Embeddable
public class FiseMcargofijoPK implements Serializable {
	//default serial version id, required for serializable classes.
	private static final long serialVersionUID = 1L;

	private String empcod;

	private long faniorep;

	private long fmesrep;

	public FiseMcargofijoPK() {
	}
	public String getEmpcod() {
		return this.empcod;
	}
	public void setEmpcod(String empcod) {
		this.empcod = empcod;
	}
	public long getFaniorep() {
		return this.faniorep;
	}
	public void setFaniorep(long faniorep) {
		this.faniorep = faniorep;
	}
	public long getFmesrep() {
		return this.fmesrep;
	}
	public void setFmesrep(long fmesrep) {
		this.fmesrep = fmesrep;
	}

	public boolean equals(Object other) {
		if (this == other) {
			return true;
		}
		if (!(other instanceof FiseMcargofijoPK)) {
			return false;
		}
		FiseMcargofijoPK castOther = (FiseMcargofijoPK)other;
		return 
			this.empcod.equals(castOther.empcod)
			&& (this.faniorep == castOther.faniorep)
			&& (this.fmesrep == castOther.fmesrep);
	}

	public int hashCode() {
		final int prime = 31;
		int hash = 17;
		hash = hash * prime + this.empcod.hashCode();
		hash = hash * prime + ((int) (this.faniorep ^ (this.faniorep >>> 32)));
		hash = hash * prime + ((int) (this.fmesrep ^ (this.fmesrep >>> 32)));
		
		return hash;
	}
}