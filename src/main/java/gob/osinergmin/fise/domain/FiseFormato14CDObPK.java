package gob.osinergmin.fise.domain;

import java.io.Serializable;
import javax.persistence.*;

/**
 * The primary key class for the FISE_FORMATO_14C_D_OBS database table.
 * 
 */
@Embeddable
public class FiseFormato14CDObPK implements Serializable {
	//default serial version id, required for serializable classes.
	private static final long serialVersionUID = 1L;

	@Column(name="COD_EMPRESA",unique=true, nullable=false)
	private String codEmpresa;

	@Column(name="ANO_PRESENTACION",unique=true, nullable=false)
	private long anoPresentacion;

	@Column(name="MES_PRESENTACION",unique=true, nullable=false)
	private long mesPresentacion;

	@Column(name="ANO_INICIO_VIGENCIA",unique=true, nullable=false)
	private long anoInicioVigencia;

	@Column(name="ANO_FIN_VIGENCIA",unique=true, nullable=false)
	private long anoFinVigencia;
	
	@Column(name="ETAPA",unique=true, nullable=false)
	private String etapa;

	@Column(name="ID_ZONA_BENEF",unique=true, nullable=false)
	private long idZonaBenef;

	@Column(name="ITEM_OBSERVACION",unique=true, nullable=false)
	private long itemObservacion;

	public FiseFormato14CDObPK() {
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
		if (!(other instanceof FiseFormato14CDObPK)) {
			return false;
		}
		FiseFormato14CDObPK castOther = (FiseFormato14CDObPK)other;
		return 
			this.codEmpresa.equals(castOther.codEmpresa)
			&& (this.anoPresentacion == castOther.anoPresentacion)
			&& (this.mesPresentacion == castOther.mesPresentacion)
			&& (this.anoInicioVigencia == castOther.anoInicioVigencia)
			&& (this.anoFinVigencia == castOther.anoFinVigencia)
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
		hash = hash * prime + ((int) (this.anoInicioVigencia ^ (this.anoInicioVigencia >>> 32)));
		hash = hash * prime + ((int) (this.anoFinVigencia ^ (this.anoFinVigencia >>> 32)));
		hash = hash * prime + this.etapa.hashCode();
		hash = hash * prime + ((int) (this.idZonaBenef ^ (this.idZonaBenef >>> 32)));
		hash = hash * prime + ((int) (this.itemObservacion ^ (this.itemObservacion >>> 32)));
		
		return hash;
	}
}