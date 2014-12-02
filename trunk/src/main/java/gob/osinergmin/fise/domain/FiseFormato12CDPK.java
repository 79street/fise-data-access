package gob.osinergmin.fise.domain;

import java.io.Serializable;
import javax.persistence.*;

/**
 * The primary key class for the FISE_FORMATO_12C_D database table.
 * 
 */
@Embeddable
public class FiseFormato12CDPK implements Serializable {
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

	@Column(name="ANO_EJECUCION_GASTO")
	private long anoEjecucionGasto;

	@Column(name="MES_EJECUCION_GASTO")
	private long mesEjecucionGasto;

	@Column(name="ETAPA_EJECUCION")
	private long etapaEjecucion;

	@Column(name="NUMERO_ITEM_ETAPA")
	private long numeroItemEtapa;

	public FiseFormato12CDPK() {
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
	public long getEtapaEjecucion() {
		return this.etapaEjecucion;
	}
	public void setEtapaEjecucion(long etapaEjecucion) {
		this.etapaEjecucion = etapaEjecucion;
	}
	public long getNumeroItemEtapa() {
		return this.numeroItemEtapa;
	}
	public void setNumeroItemEtapa(long numeroItemEtapa) {
		this.numeroItemEtapa = numeroItemEtapa;
	}

	public boolean equals(Object other) {
		if (this == other) {
			return true;
		}
		if (!(other instanceof FiseFormato12CDPK)) {
			return false;
		}
		FiseFormato12CDPK castOther = (FiseFormato12CDPK)other;
		return 
			this.codEmpresa.equals(castOther.codEmpresa)
			&& (this.anoPresentacion == castOther.anoPresentacion)
			&& (this.mesPresentacion == castOther.mesPresentacion)
			&& this.etapa.equals(castOther.etapa)
			&& (this.anoEjecucionGasto == castOther.anoEjecucionGasto)
			&& (this.mesEjecucionGasto == castOther.mesEjecucionGasto)
			&& (this.etapaEjecucion == castOther.etapaEjecucion)
			&& (this.numeroItemEtapa == castOther.numeroItemEtapa);
	}

	public int hashCode() {
		final int prime = 31;
		int hash = 17;
		hash = hash * prime + this.codEmpresa.hashCode();
		hash = hash * prime + ((int) (this.anoPresentacion ^ (this.anoPresentacion >>> 32)));
		hash = hash * prime + ((int) (this.mesPresentacion ^ (this.mesPresentacion >>> 32)));
		hash = hash * prime + this.etapa.hashCode();
		hash = hash * prime + ((int) (this.anoEjecucionGasto ^ (this.anoEjecucionGasto >>> 32)));
		hash = hash * prime + ((int) (this.mesEjecucionGasto ^ (this.mesEjecucionGasto >>> 32)));
		hash = hash * prime + ((int) (this.etapaEjecucion ^ (this.etapaEjecucion >>> 32)));
		hash = hash * prime + ((int) (this.numeroItemEtapa ^ (this.numeroItemEtapa >>> 32)));
		
		return hash;
	}
}