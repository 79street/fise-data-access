package gob.osinergmin.fise.bean;

import java.util.Map;

public class GrupoInformacionBean {
	
	private Map<Long,String> listaMes;
	
	private String estadoBusq;
	private String tipoBusq;
	private String descripcionBusq;	
	
	private String idGrupoInf;
    private String descripcion;
    private String estado;
	private String tipo;
	private String anioPres;
	private String mesPres;
    
    private String usuario;
    private String terminal;   
    
    
    
	public String getEstadoBusq() {
		return estadoBusq;
	}
	public void setEstadoBusq(String estadoBusq) {
		this.estadoBusq = estadoBusq;
	}
	public String getTipoBusq() {
		return tipoBusq;
	}
	public void setTipoBusq(String tipoBusq) {
		this.tipoBusq = tipoBusq;
	}
	public String getDescripcionBusq() {
		return descripcionBusq;
	}
	public void setDescripcionBusq(String descripcionBusq) {
		this.descripcionBusq = descripcionBusq;
	}
	public String getIdGrupoInf() {
		return idGrupoInf;
	}
	public void setIdGrupoInf(String idGrupoInf) {
		this.idGrupoInf = idGrupoInf;
	}
	public String getDescripcion() {
		return descripcion;
	}
	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}
	public String getEstado() {
		return estado;
	}
	public void setEstado(String estado) {
		this.estado = estado;
	}
	public String getTipo() {
		return tipo;
	}
	public void setTipo(String tipo) {
		this.tipo = tipo;
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
	public Map<Long, String> getListaMes() {
		return listaMes;
	}
	public void setListaMes(Map<Long, String> listaMes) {
		this.listaMes = listaMes;
	}
	public String getAnioPres() {
		return anioPres;
	}
	public void setAnioPres(String anioPres) {
		this.anioPres = anioPres;
	}
	public String getMesPres() {
		return mesPres;
	}
	public void setMesPres(String mesPres) {
		this.mesPres = mesPres;
	}
	
	

}
