package gob.osinergmin.fise.domain;

import java.io.Serializable;
import javax.persistence.*;

/**
 * The primary key class for the FISE_FORMATO_12A_D_OBS database table.
 * 
 */
@Embeddable
public class FiseFormato12ADObsPK implements Serializable {
	//default serial version id, required for serializable classes.
	private static final long serialVersionUID = 1L;

	@Column(name="COD_EMPRESA", insertable=false, updatable=false)
	private String codEmpresa;

	@Column(name="ANO_PRESENTACION", insertable=false, updatable=false)
	private long anoPresentacion;

	@Column(name="MES_PRESENTACION", insertable=false, updatable=false)
	private long mesPresentacion;

	@Column(name="ANO_EJECUCION_GASTO", insertable=false, updatable=false)
	private long anoEjecucionGasto;

	@Column(name="MES_EJECUCION_GASTO", insertable=false, updatable=false)
	private long mesEjecucionGasto;

	@Column(insertable=false, updatable=false)
	private String etapa;

	@Column(name="ID_ZONA_BENEF", insertable=false, updatable=false)
	private long idZonaBenef;

	@Column(name="ITEM_OBSERVACION")
	private long itemObservacion;

	public FiseFormato12ADObsPK() {
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
	public long getAnoEjecucionGasto() {
		return this.anoEjecucionGasto;
	}
	public void setAnoEjecucionGasto(long anoEjecucionGasto) {
		this.anoEjecucionGasto = anoEjecucionGasto;
	}
	public long getMesEjecucionGasto() {
		return this.mesEjecucionGasto;
	}
	public void setMesEjecucionGasto(long mesEjecucionGasto) {
		this.mesEjecucionGasto = mesEjecucionGasto;
	}
	public String getEtapa() {
		return this.etapa;
	}
	public void setEtapa(String etapa) {
		this.etapa = etapa;
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
		if (!(other instanceof FiseFormato12ADObsPK)) {
			return false;
		}
		FiseFormato12ADObsPK castOther = (FiseFormato12ADObsPK)other;
		return 
			this.codEmpresa.equals(castOther.codEmpresa)
			&& (this.anoPresentacion == castOther.anoPresentacion)
			&& (this.mesPresentacion == castOther.mesPresentacion)
			&& (this.anoEjecucionGasto == castOther.anoEjecucionGasto)
			&& (this.mesEjecucionGasto == castOther.mesEjecucionGasto)
			&& this.etapa.equals(castOther.etapa)
			&& (this.idZonaBenef == castOther.idZonaBenef)
			&& (this.itemObservacion == castOther.itemObservacion);
	}

	public int hashCode() {
		final int prime = 31;
		int hash = 17;
		hash = hash * prime + this.codEmpresa.hashCode();
		hash = hash * prime + ((int) (this.anoPresentacion ^ (this.anoPresentacion >>> 32)));
		hash = hash * prime + ((int) (this.mesPresentacion ^ (this.mesPresentacion >>> 32)));
		hash = hash * prime + ((int) (this.anoEjecucionGasto ^ (this.anoEjecucionGasto >>> 32)));
		hash = hash * prime + ((int) (this.mesEjecucionGasto ^ (this.mesEjecucionGasto >>> 32)));
		hash = hash * prime + this.etapa.hashCode();
		hash = hash * prime + ((int) (this.idZonaBenef ^ (this.idZonaBenef >>> 32)));
		hash = hash * prime + ((int) (this.itemObservacion ^ (this.itemObservacion >>> 32)));
		
		return hash;
	}
}