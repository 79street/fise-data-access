package gob.osinergmin.fise.domain;

import java.io.Serializable;
import javax.persistence.*;

/**
 * The primary key class for the FISE_FORMATO_14A_D database table.
 * 
 */
@Embeddable
public class FiseFormato14ADPK implements Serializable {
	//default serial version id, required for serializable classes.
	private static final long serialVersionUID = 1L;

	@Column(name="COD_EMPRESA", insertable=false, updatable=false)
	private String codEmpresa;

	@Column(name="ANO_PRESENTACION", insertable=false, updatable=false)
	private long anoPresentacion;

	@Column(name="MES_PRESENTACION", insertable=false, updatable=false)
	private long mesPresentacion;

	@Column(name="ANO_INICIO_VIGENCIA", insertable=false, updatable=false)
	private long anoInicioVigencia;

	@Column(name="ANO_FIN_VIGENCIA", insertable=false, updatable=false)
	private long anoFinVigencia;

	@Column(insertable=false, updatable=false)
	private String etapa;

	@Column(name="ID_ZONA_BENEF", insertable=false, updatable=false)
	private long idZonaBenef;

	public FiseFormato14ADPK() {
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
	public long getAnoInicioVigencia() {
		return this.anoInicioVigencia;
	}
	public void setAnoInicioVigencia(long anoInicioVigencia) {
		this.anoInicioVigencia = anoInicioVigencia;
	}
	public long getAnoFinVigencia() {
		return this.anoFinVigencia;
	}
	public void setAnoFinVigencia(long anoFinVigencia) {
		this.anoFinVigencia = anoFinVigencia;
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

	public boolean equals(Object other) {
		if (this == other) {
			return true;
		}
		if (!(other instanceof FiseFormato14ADPK)) {
			return false;
		}
		FiseFormato14ADPK castOther = (FiseFormato14ADPK)other;
		return 
			this.codEmpresa.equals(castOther.codEmpresa)
			&& (this.anoPresentacion == castOther.anoPresentacion)
			&& (this.mesPresentacion == castOther.mesPresentacion)
			&& (this.anoInicioVigencia == castOther.anoInicioVigencia)
			&& (this.anoFinVigencia == castOther.anoFinVigencia)
			&& this.etapa.equals(castOther.etapa)
			&& (this.idZonaBenef == castOther.idZonaBenef);
	}

	public int hashCode() {
		final int prime = 31;
		int hash = 17;
		hash = hash * prime + this.codEmpresa.hashCode();
		hash = hash * prime + ((int) (this.anoPresentacion ^ (this.anoPresentacion >>> 32)));
		hash = hash * prime + ((int) (this.mesPresentacion ^ (this.mesPresentacion >>> 32)));
		hash = hash * prime + ((int) (this.anoInicioVigencia ^ (this.anoInicioVigencia >>> 32)));
		hash = hash * prime + ((int) (this.anoFinVigencia ^ (this.anoFinVigencia >>> 32)));
		hash = hash * prime + this.etapa.hashCode();
		hash = hash * prime + ((int) (this.idZonaBenef ^ (this.idZonaBenef >>> 32)));
		
		return hash;
	}
}