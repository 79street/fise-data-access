package gob.osinergmin.fise.domain;

import java.io.Serializable;
import javax.persistence.*;

/**
 * The primary key class for the FISE_FORMATO_13A_D_OBS database table.
 * 
 */
@Embeddable
public class FiseFormato13ADObPK implements Serializable {
	//default serial version id, required for serializable classes.
	private static final long serialVersionUID = 1L;

	@Column(name="COD_EMPRESA", insertable=false, updatable=false)
	private String codEmpresa;

	@Column(name="ANO_PRESENTACION", insertable=false, updatable=false)
	private long anoPresentacion;

	@Column(name="MES_PRESENTACION", insertable=false, updatable=false)
	private long mesPresentacion;

	@Column(insertable=false, updatable=false)
	private String etapa;

	@Column(name="COD_UBIGEO", insertable=false, updatable=false)
	private String codUbigeo;

	@Column(name="COD_SECTOR_TIPICO", insertable=false, updatable=false)
	private String codSectorTipico;

	@Column(name="ID_ZONA_BENEF", insertable=false, updatable=false)
	private long idZonaBenef;

	@Column(name="ITEM_OBSERVACION")
	private long itemObservacion;

	public FiseFormato13ADObPK() {
	}
	public String getCodEmpresa() {
		return this.codEmpresa;
	}
	public void setCodEmpresa(String codEmpresa) {
		this.codEmpresa = codEmpresa;
	}
	public long getAnoPresentacion() {
		return this.anoPresentacion;
	}
	public void setAnoPresentacion(long anoPresentacion) {
		this.anoPresentacion = anoPresentacion;
	}
	public long getMesPresentacion() {
		return this.mesPresentacion;
	}
	public void setMesPresentacion(long mesPresentacion) {
		this.mesPresentacion = mesPresentacion;
	}
	public String getEtapa() {
		return this.etapa;
	}
	public void setEtapa(String etapa) {
		this.etapa = etapa;
	}
	public String getCodUbigeo() {
		return this.codUbigeo;
	}
	public void setCodUbigeo(String codUbigeo) {
		this.codUbigeo = codUbigeo;
	}
	public String getCodSectorTipico() {
		return this.codSectorTipico;
	}
	public void setCodSectorTipico(String codSectorTipico) {
		this.codSectorTipico = codSectorTipico;
	}
	public long getIdZonaBenef() {
		return this.idZonaBenef;
	}
	public void setIdZonaBenef(long idZonaBenef) {
		this.idZonaBenef = idZonaBenef;
	}
	public long getItemObservacion() {
		return this.itemObservacion;
	}
	public void setItemObservacion(long itemObservacion) {
		this.itemObservacion = itemObservacion;
	}

	public boolean equals(Object other) {
		if (this == other) {
			return true;
		}
		if (!(other instanceof FiseFormato13ADObPK)) {
			return false;
		}
		FiseFormato13ADObPK castOther = (FiseFormato13ADObPK)other;
		return 
			this.codEmpresa.equals(castOther.codEmpresa)
			&& (this.anoPresentacion == castOther.anoPresentacion)
			&& (this.mesPresentacion == castOther.mesPresentacion)
			&& this.etapa.equals(castOther.etapa)
			&& this.codUbigeo.equals(castOther.codUbigeo)
			&& this.codSectorTipico.equals(castOther.codSectorTipico)
			&& (this.idZonaBenef == castOther.idZonaBenef)
			&& (this.itemObservacion == castOther.itemObservacion);
	}

	public int hashCode() {
		final int prime = 31;
		int hash = 17;
		hash = hash * prime + this.codEmpresa.hashCode();
		hash = hash * prime + ((int) (this.anoPresentacion ^ (this.anoPresentacion >>> 32)));
		hash = hash * prime + ((int) (this.mesPresentacion ^ (this.mesPresentacion >>> 32)));
		hash = hash * prime + this.etapa.hashCode();
		hash = hash * prime + this.codUbigeo.hashCode();
		hash = hash * prime + this.codSectorTipico.hashCode();
		hash = hash * prime + ((int) (this.idZonaBenef ^ (this.idZonaBenef >>> 32)));
		hash = hash * prime + ((int) (this.itemObservacion ^ (this.itemObservacion >>> 32)));
		
		return hash;
	}
}