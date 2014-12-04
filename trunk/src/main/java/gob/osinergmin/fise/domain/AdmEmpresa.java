package gob.osinergmin.fise.domain;

import java.io.Serializable;
import javax.persistence.*;
import java.sql.Timestamp;


/**
 * The persistent class for the ADM_EMPRESA database table.
 * 
 */
@Entity
@Table(name="ADM_EMPRESA")
@NamedQuery(name="AdmEmpresa.findAll", query="SELECT a FROM AdmEmpresa a")
public class AdmEmpresa implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@Column(name="COD_EMPRESA")
	private String codEmpresa;

	@Column(name="AD_COD_USUARIO")
	private String adCodUsuario;

	@Column(name="AD_FECHA")
	private Timestamp adFecha;

	@Column(name="COD_UBIGEO")
	private String codUbigeo;

	private String direccion;

	@Column(name="DSC_CORTA_EMPRESA")
	private String dscCortaEmpresa;

	@Column(name="DSC_EMPRESA")
	private String dscEmpresa;

	private String email;

	private String estado;

	@Column(name="REPRESENTANTE_LEGAL")
	private String representanteLegal;

	private String ruc;

	private String telefonos;

	public AdmEmpresa() {
	}

	public String getCodEmpresa() {
		return this.codEmpresa;
	}

	public void setCodEmpresa(String codEmpresa) {
		this.codEmpresa = codEmpresa;
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

	public String getCodUbigeo() {
		return this.codUbigeo;
	}

	public void setCodUbigeo(String codUbigeo) {
		this.codUbigeo = codUbigeo;
	}

	public String getDireccion() {
		return this.direccion;
	}

	public void setDireccion(String direccion) {
		this.direccion = direccion;
	}

	public String getDscCortaEmpresa() {
		return this.dscCortaEmpresa;
	}

	public void setDscCortaEmpresa(String dscCortaEmpresa) {
		this.dscCortaEmpresa = dscCortaEmpresa;
	}

	public String getDscEmpresa() {
		return this.dscEmpresa;
	}

	public void setDscEmpresa(String dscEmpresa) {
		this.dscEmpresa = dscEmpresa;
	}

	public String getEmail() {
		return this.email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getEstado() {
		return this.estado;
	}

	public void setEstado(String estado) {
		this.estado = estado;
	}

	public String getRepresentanteLegal() {
		return this.representanteLegal;
	}

	public void setRepresentanteLegal(String representanteLegal) {
		this.representanteLegal = representanteLegal;
	}

	public String getRuc() {
		return this.ruc;
	}

	public void setRuc(String ruc) {
		this.ruc = ruc;
	}

	public String getTelefonos() {
		return this.telefonos;
	}

	public void setTelefonos(String telefonos) {
		this.telefonos = telefonos;
	}

}