package gob.osinergmin.fise.bean;

import java.io.Serializable;

public class Formato13ADReportBean implements Serializable {

	private static final long serialVersionUID = 1L;
	
	private String codigoEmpresa;
	private Long anioPresent = 0L;
	private Long mesPresent = 0L;

	private String etapa;
	private String codUbigeo;
	
	private String codSectorTipico;
	private Long idZonaBenef=0L;
	
	private Long anioAlta = 0L;
	private Long mesAlta = 0L;
	
	private Long anioFinVigencia = 0L;
	private Long anioInicioVigencia = 0L;
	
	private String descLocalidad;	
	private String nombreSedeAtiende;
	
	private Long nroBenefPoteSecTipico1 = 0L;
	private Long nroBenefPoteSecTipico2= 0L;
	private Long nroBenefPoteSecTipico3= 0L;
	private Long nroBenefPoteSecTipico4= 0L;
	private Long nroBenefPoteSecTipico5= 0L;
	private Long nroBenefPoteSecTipico6= 0L;
	private Long nroBenefPoteSecTipico7= 0L;
	private Long nroBenefPoteSecTipico8= 0L;
	
	private String usuario;
	private String terminal;
	private String nombreArchivo;
	private String tipoArchivo;
	
	private String descEmpresa;
	private String descMesPresentacion;
	public String getCodigoEmpresa() {
		return codigoEmpresa;
	}
	public void setCodigoEmpresa(String codigoEmpresa) {
		this.codigoEmpresa = codigoEmpresa;
	}
	public Long getAnioPresent() {
		return anioPresent;
	}
	public void setAnioPresent(Long anioPresent) {
		this.anioPresent = anioPresent;
	}
	public Long getMesPresent() {
		return mesPresent;
	}
	public void setMesPresent(Long mesPresent) {
		this.mesPresent = mesPresent;
	}
	public String getEtapa() {
		return etapa;
	}
	public void setEtapa(String etapa) {
		this.etapa = etapa;
	}
	public String getCodUbigeo() {
		return codUbigeo;
	}
	public void setCodUbigeo(String codUbigeo) {
		this.codUbigeo = codUbigeo;
	}
	public String getCodSectorTipico() {
		return codSectorTipico;
	}
	public void setCodSectorTipico(String codSectorTipico) {
		this.codSectorTipico = codSectorTipico;
	}
	public Long getIdZonaBenef() {
		return idZonaBenef;
	}
	public void setIdZonaBenef(Long idZonaBenef) {
		this.idZonaBenef = idZonaBenef;
	}
	public Long getAnioAlta() {
		return anioAlta;
	}
	public void setAnioAlta(Long anioAlta) {
		this.anioAlta = anioAlta;
	}
	public Long getMesAlta() {
		return mesAlta;
	}
	public void setMesAlta(Long mesAlta) {
		this.mesAlta = mesAlta;
	}
	public Long getAnioFinVigencia() {
		return anioFinVigencia;
	}
	public void setAnioFinVigencia(Long anioFinVigencia) {
		this.anioFinVigencia = anioFinVigencia;
	}
	public Long getAnioInicioVigencia() {
		return anioInicioVigencia;
	}
	public void setAnioInicioVigencia(Long anioInicioVigencia) {
		this.anioInicioVigencia = anioInicioVigencia;
	}
	public String getDescLocalidad() {
		return descLocalidad;
	}
	public void setDescLocalidad(String descLocalidad) {
		this.descLocalidad = descLocalidad;
	}
	public String getNombreSedeAtiende() {
		return nombreSedeAtiende;
	}
	public void setNombreSedeAtiende(String nombreSedeAtiende) {
		this.nombreSedeAtiende = nombreSedeAtiende;
	}
	public Long getNroBenefPoteSecTipico1() {
		return nroBenefPoteSecTipico1;
	}
	public void setNroBenefPoteSecTipico1(Long nroBenefPoteSecTipico1) {
		this.nroBenefPoteSecTipico1 = nroBenefPoteSecTipico1;
	}
	public Long getNroBenefPoteSecTipico2() {
		return nroBenefPoteSecTipico2;
	}
	public void setNroBenefPoteSecTipico2(Long nroBenefPoteSecTipico2) {
		this.nroBenefPoteSecTipico2 = nroBenefPoteSecTipico2;
	}
	public Long getNroBenefPoteSecTipico3() {
		return nroBenefPoteSecTipico3;
	}
	public void setNroBenefPoteSecTipico3(Long nroBenefPoteSecTipico3) {
		this.nroBenefPoteSecTipico3 = nroBenefPoteSecTipico3;
	}
	public Long getNroBenefPoteSecTipico4() {
		return nroBenefPoteSecTipico4;
	}
	public void setNroBenefPoteSecTipico4(Long nroBenefPoteSecTipico4) {
		this.nroBenefPoteSecTipico4 = nroBenefPoteSecTipico4;
	}
	public Long getNroBenefPoteSecTipico5() {
		return nroBenefPoteSecTipico5;
	}
	public void setNroBenefPoteSecTipico5(Long nroBenefPoteSecTipico5) {
		this.nroBenefPoteSecTipico5 = nroBenefPoteSecTipico5;
	}
	public Long getNroBenefPoteSecTipico6() {
		return nroBenefPoteSecTipico6;
	}
	public void setNroBenefPoteSecTipico6(Long nroBenefPoteSecTipico6) {
		this.nroBenefPoteSecTipico6 = nroBenefPoteSecTipico6;
	}
	public Long getNroBenefPoteSecTipico7() {
		return nroBenefPoteSecTipico7;
	}
	public void setNroBenefPoteSecTipico7(Long nroBenefPoteSecTipico7) {
		this.nroBenefPoteSecTipico7 = nroBenefPoteSecTipico7;
	}
	public Long getNroBenefPoteSecTipico8() {
		return nroBenefPoteSecTipico8;
	}
	public void setNroBenefPoteSecTipico8(Long nroBenefPoteSecTipico8) {
		this.nroBenefPoteSecTipico8 = nroBenefPoteSecTipico8;
	}
	public String getUsuario() {
		return usuario;
	}
	public void setUsuario(String usuario) {
		this.usuario = usuario;
	}
	public String getTerminal() {
		return terminal;
	}
	public void setTerminal(String terminal) {
		this.terminal = terminal;
	}
	public String getNombreArchivo() {
		return nombreArchivo;
	}
	public void setNombreArchivo(String nombreArchivo) {
		this.nombreArchivo = nombreArchivo;
	}
	public String getTipoArchivo() {
		return tipoArchivo;
	}
	public void setTipoArchivo(String tipoArchivo) {
		this.tipoArchivo = tipoArchivo;
	}
	public String getDescEmpresa() {
		return descEmpresa;
	}
	public void setDescEmpresa(String descEmpresa) {
		this.descEmpresa = descEmpresa;
	}
	public String getDescMesPresentacion() {
		return descMesPresentacion;
	}
	public void setDescMesPresentacion(String descMesPresentacion) {
		this.descMesPresentacion = descMesPresentacion;
	}
	
	
	
}
