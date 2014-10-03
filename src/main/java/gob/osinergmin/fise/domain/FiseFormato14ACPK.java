package gob.osinergmin.fise.domain;

import java.io.Serializable;
import javax.persistence.*;

/**
 * The primary key class for the FISE_FORMATO_14A_C database table.
 * 
 */
@Embeddable
public class FiseFormato14ACPK implements Serializable {
	//default serial version id, required for serializable classes.
	private static final long serialVersionUID = 1L;

	@Column(name="COD_EMPRESA")
	private String codEmpresa;

	@Column(name="ANO_PRESENTACION")
	private long anoPresentacion;

	@Column(name="MES_PRESENTACION")
	private long mesPresentacion;

	@Column(name="ANO_INICIO_VIGENCIA")
	private long anoInicioVigencia;

	@Column(name="ANO_FIN_VIGENCIA")
	private long anoFinVigencia;

	private String etapa;

	public FiseFormato14ACPK() {
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

	public boolean equals(Object other) {
		if (this == other) {
			return true;
		}
		if (!(other instanceof FiseFormato14ACPK)) {
			return false;
		}
		FiseFormato14ACPK castOther = (FiseFormato14ACPK)other;
		return 
			this.codEmpresa.equals(castOther.codEmpresa)
			&& (this.anoPresentacion == castOther.anoPresentacion)
			&& (this.mesPresentacion == castOther.mesPresentacion)
			&& (this.anoInicioVigencia == castOther.anoInicioVigencia)
			&& (this.anoFinVigencia == castOther.anoFinVigencia)
			&& this.etapa.equals(castOther.etapa);
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
		
		return hash;
	}
}