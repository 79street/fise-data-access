package gob.osinergmin.fise.domain;

import java.io.Serializable;
import javax.persistence.*;

/**
 * The primary key class for the FISE_PERIODO_ENVIO database table.
 * 
 */
@Embeddable
public class FisePeriodoEnvioPK implements Serializable {
	//default serial version id, required for serializable classes.
	private static final long serialVersionUID = 1L;

	@Column(name="COD_EMPRESA")
	private String codEmpresa;

	@Column(name="ANO_PRESENTACION")
	private long anoPresentacion;

	@Column(name="MES_PRESENTACION")
	private long mesPresentacion;

	private String formato;

	private String etapa;

	private long secuencia;

	public FisePeriodoEnvioPK() {
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
	public String getFormato() {
		return this.formato;
	}
	public void setFormato(String formato) {
		this.formato = formato;
	}
	public String getEtapa() {
		return this.etapa;
	}
	public void setEtapa(String etapa) {
		this.etapa = etapa;
	}
	public long getSecuencia() {
		return this.secuencia;
	}
	public void setSecuencia(long secuencia) {
		this.secuencia = secuencia;
	}

	public boolean equals(Object other) {
		if (this == other) {
			return true;
		}
		if (!(other instanceof FisePeriodoEnvioPK)) {
			return false;
		}
		FisePeriodoEnvioPK castOther = (FisePeriodoEnvioPK)other;
		return 
			this.codEmpresa.equals(castOther.codEmpresa)
			&& (this.anoPresentacion == castOther.anoPresentacion)
			&& (this.mesPresentacion == castOther.mesPresentacion)
			&& this.formato.equals(castOther.formato)
			&& this.etapa.equals(castOther.etapa)
			&& (this.secuencia == castOther.secuencia);
	}

	public int hashCode() {
		final int prime = 31;
		int hash = 17;
		hash = hash * prime + this.codEmpresa.hashCode();
		hash = hash * prime + ((int) (this.anoPresentacion ^ (this.anoPresentacion >>> 32)));
		hash = hash * prime + ((int) (this.mesPresentacion ^ (this.mesPresentacion >>> 32)));
		hash = hash * prime + this.formato.hashCode();
		hash = hash * prime + this.etapa.hashCode();
		hash = hash * prime + ((int) (this.secuencia ^ (this.secuencia >>> 32)));
		
		return hash;
	}
}