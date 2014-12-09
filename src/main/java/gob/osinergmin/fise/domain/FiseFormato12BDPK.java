package gob.osinergmin.fise.domain;

import java.io.Serializable;
import javax.persistence.*;

/**
 * The primary key class for the FISE_FORMATO_12B_D database table.
 * 
 */
@Embeddable
public class FiseFormato12BDPK implements Serializable {
	//default serial version id, required for serializable classes.
	private static final long serialVersionUID = 1L;

	@Column(name="COD_EMPRESA", insertable=false, updatable=false)
	private String codEmpresa;

	@Column(name="ANO_PRESENTACION", insertable=false, updatable=false)
	private Integer anoPresentacion;

	@Column(name="MES_PRESENTACION", insertable=false, updatable=false)
	private Integer mesPresentacion;

	@Column(name="ANO_EJECUCION_GASTO", insertable=false, updatable=false)
	private Integer anoEjecucionGasto;

	@Column(name="MES_EJECUCION_GASTO", insertable=false, updatable=false)
	private Integer mesEjecucionGasto;

	@Column(insertable=false, updatable=false)
	private String etapa;

	@Column(name="ID_ZONA_BENEF", insertable=false, updatable=false)
	private Integer idZonaBenef;

	public FiseFormato12BDPK() {
	}
	public String getCodEmpresa() {
		return this.codEmpresa;
	}
	public void setCodEmpresa(String codEmpresa) {
		this.codEmpresa = codEmpresa;
	}
	public Integer getAnoPresentacion() {
		return this.anoPresentacion;
	}
	public void setAnoPresentacion(Integer anoPresentacion) {
		this.anoPresentacion = anoPresentacion;
	}
	public Integer getMesPresentacion() {
		return this.mesPresentacion;
	}
	public void setMesPresentacion(Integer mesPresentacion) {
		this.mesPresentacion = mesPresentacion;
	}
	public Integer getAnoEjecucionGasto() {
		return this.anoEjecucionGasto;
	}
	public void setAnoEjecucionGasto(Integer anoEjecucionGasto) {
		this.anoEjecucionGasto = anoEjecucionGasto;
	}
	public Integer getMesEjecucionGasto() {
		return this.mesEjecucionGasto;
	}
	public void setMesEjecucionGasto(Integer mesEjecucionGasto) {
		this.mesEjecucionGasto = mesEjecucionGasto;
	}
	public String getEtapa() {
		return this.etapa;
	}
	public void setEtapa(String etapa) {
		this.etapa = etapa;
	}
	public Integer getIdZonaBenef() {
		return this.idZonaBenef;
	}
	public void setIdZonaBenef(Integer idZonaBenef) {
		this.idZonaBenef = idZonaBenef;
	}

	public boolean equals(Object other) {
		if (this == other) {
			return true;
		}
		if (!(other instanceof FiseFormato12BDPK)) {
			return false;
		}
		FiseFormato12BDPK castOther = (FiseFormato12BDPK)other;
		return 
			this.codEmpresa.equals(castOther.codEmpresa)
			&& (this.anoPresentacion == castOther.anoPresentacion)
			&& (this.mesPresentacion == castOther.mesPresentacion)
			&& (this.anoEjecucionGasto == castOther.anoEjecucionGasto)
			&& (this.mesEjecucionGasto == castOther.mesEjecucionGasto)
			&& this.etapa.equals(castOther.etapa)
			&& (this.idZonaBenef == castOther.idZonaBenef);
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
		
		return hash;
	}
}