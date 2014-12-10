package gob.osinergmin.fise.domain;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Embeddable;

/**
 * The primary key class for the FISE_CONTROL_ENVIO_POR_GRUPO database table.
 * 
 */
@Embeddable
public class FiseControlEnvioPorGrupoPK implements Serializable {
	//default serial version id, required for serializable classes.
	private static final long serialVersionUID = 1L;

	@Column(name="COD_EMPRESA",unique=true, nullable=false)
	private String codEmpresa;

	@Column(name="ETAPA",unique=true, nullable=false)
	private String etapa;

	@Column(name="PERIODICIDAD",unique=true, nullable=false)
	private String periodicidad;

	@Column(name="ID_GRUPO_INFORMACION",unique=true, nullable=false)
	private long idGrupoInformacion;

	public FiseControlEnvioPorGrupoPK() {
	}
	public String getCodEmpresa() {
		return this.codEmpresa;
	}
	public void setCodEmpresa(String codEmpresa) {
		this.codEmpresa = codEmpresa;
	}
	public String getEtapa() {
		return this.etapa;
	}
	public void setEtapa(String etapa) {
		this.etapa = etapa;
	}
	public String getPeriodicidad() {
		return this.periodicidad;
	}
	public void setPeriodicidad(String periodicidad) {
		this.periodicidad = periodicidad;
	}
	public long getIdGrupoInformacion() {
		return this.idGrupoInformacion;
	}
	public void setIdGrupoInformacion(long idGrupoInformacion) {
		this.idGrupoInformacion = idGrupoInformacion;
	}

	public boolean equals(Object other) {
		if (this == other) {
			return true;
		}
		if (!(other instanceof FiseControlEnvioPorGrupoPK)) {
			return false;
		}
		FiseControlEnvioPorGrupoPK castOther = (FiseControlEnvioPorGrupoPK)other;
		return 
			this.codEmpresa.equals(castOther.codEmpresa)
			&& this.etapa.equals(castOther.etapa)
			&& this.periodicidad.equals(castOther.periodicidad)
			&& (this.idGrupoInformacion == castOther.idGrupoInformacion);
	}

	public int hashCode() {
		final int prime = 31;
		int hash = 17;
		hash = hash * prime + this.codEmpresa.hashCode();
		hash = hash * prime + this.etapa.hashCode();
		hash = hash * prime + this.periodicidad.hashCode();
		hash = hash * prime + ((int) (this.idGrupoInformacion ^ (this.idGrupoInformacion >>> 32)));
		
		return hash;
	}
}