package gob.osinergmin.fise.domain;

import java.io.Serializable;
import javax.persistence.*;
import java.sql.Timestamp;


/**
 * The persistent class for the ADM_UBIGEO database table.
 * 
 */
@Entity
@Table(name="ADM_UBIGEO", schema="ADMIN_GART")
@NamedQuery(name="AdmUbigeo.findAll", query="SELECT a FROM AdmUbigeo a")
public class AdmUbigeo implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@Column(name="COD_UBIGEO")
	private String codUbigeo;

	@Column(name="AD_COD_USUARIO")
	private String adCodUsuario;

	@Column(name="AD_FECHA")
	private Timestamp adFecha;

	private String estado;

	@Column(name="NOM_UBIGEO")
	private String nomUbigeo;

	public AdmUbigeo() {
	}

	public String getCodUbigeo() {
		return this.codUbigeo;
	}

	public void setCodUbigeo(String codUbigeo) {
		this.codUbigeo = codUbigeo;
	}

	public String getAdCodUsuario() {
		return this.adCodUsuario;
	}

	public void setAdCodUsuario(String adCodUsuario) {
		this.adCodUsuario = adCodUsuario;
	}

	public Timestamp getAdFecha() {
		return this.adFecha;
	}

	public void setAdFecha(Timestamp adFecha) {
		this.adFecha = adFecha;
	}

	public String getEstado() {
		return this.estado;
	}

	public void setEstado(String estado) {
		this.estado = estado;
	}

	public String getNomUbigeo() {
		return this.nomUbigeo;
	}

	public void setNomUbigeo(String nomUbigeo) {
		this.nomUbigeo = nomUbigeo;
	}

}