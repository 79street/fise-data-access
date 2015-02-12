package gob.osinergmin.fise.bean;

import java.io.Serializable;

public class Formato13ACBean implements Serializable {

	private static final long serialVersionUID = 1L;
	
	private String codigoEmpresa;
	private long anioPresent = 0;
	private long mesPresent = 0;

	private String etapa;

	private long idGrupoInfo = 0;
		
	private String usuario;
	private String terminal;
	private String nombreArchivo;
	private String tipoArchivo;
	
	private String descEmpresa;
	private String descMesPresentacion;
	
	//detalle
	private String codUbigeo;
	private String codSectorTipico;
	private Long idZonaBenef= 0L;
	private Long anioAlta= 0L;
	private Long mesAlta= 0L;
	private Long anioInicioVigencia= 0L;
	private Long anioFinVigencia= 0L;
	private String localidad;
	private String Sede;
	private Long st1= 0L;
	private Long st2= 0L;
	private Long st3= 0L;
	private Long st4= 0L;
	private Long st5= 0L;
	private Long st6= 0L;
	private Long stSer= 0L;
	private Long stEsp= 0L;
	
	
	public String getCodigoEmpresa() {
		return codigoEmpresa;
	}
	public void setCodigoEmpresa(String codigoEmpresa) {
		this.codigoEmpresa = codigoEmpresa;
	}
	public long getAnioPresent() {
		return anioPresent;
	}
	public void setAnioPresent(long anioPresent) {
		this.anioPresent = anioPresent;
	}
	public long getMesPresent() {
		return mesPresent;
	}
	public void setMesPresent(long mesPresent) {
		this.mesPresent = mesPresent;
	}
	public String getEtapa() {
		return etapa;
	}
	public void setEtapa(String etapa) {
		this.etapa = etapa;
	}
	public long getIdGrupoInfo() {
		return idGrupoInfo;
	}
	public void setIdGrupoInfo(long idGrupoInfo) {
		this.idGrupoInfo = idGrupoInfo;
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
	public Long getAnioInicioVigencia() {
		return anioInicioVigencia;
	}
	public void setAnioInicioVigencia(Long anioInicioVigencia) {
		this.anioInicioVigencia = anioInicioVigencia;
	}
	public Long getAnioFinVigencia() {
		return anioFinVigencia;
	}
	public void setAnioFinVigencia(Long anioFinVigencia) {
		this.anioFinVigencia = anioFinVigencia;
	}
	public String getLocalidad() {
		return localidad;
	}
	public void setLocalidad(String localidad) {
		this.localidad = localidad;
	}
	public String getSede() {
		return Sede;
	}
	public void setSede(String sede) {
		Sede = sede;
	}
	public Long getSt1() {
		return st1;
	}
	public void setSt1(Long st1) {
		this.st1 = st1;
	}
	public Long getSt2() {
		return st2;
	}
	public void setSt2(Long st2) {
		this.st2 = st2;
	}
	public Long getSt3() {
		return st3;
	}
	public void setSt3(Long st3) {
		this.st3 = st3;
	}
	public Long getSt4() {
		return st4;
	}
	public void setSt4(Long st4) {
		this.st4 = st4;
	}
	public Long getSt5() {
		return st5;
	}
	public void setSt5(Long st5) {
		this.st5 = st5;
	}
	public Long getSt6() {
		return st6;
	}
	public void setSt6(Long st6) {
		this.st6 = st6;
	}
	public Long getStSer() {
		return stSer;
	}
	public void setStSer(Long stSer) {
		this.stSer = stSer;
	}
	public Long getStEsp() {
		return stEsp;
	}
	public void setStEsp(Long stEsp) {
		this.stEsp = stEsp;
	}
	
	
}
