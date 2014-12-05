package gob.osinergmin.fise.bean;

import gob.osinergmin.fise.domain.FiseFormato12CC;

import java.io.Serializable;
import java.util.List;

public class Formato12CMensajeBean implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private FiseFormato12CC fiseFormato12CC;
	private String mensajeInformacion;
	private String mensajeError;
	private List<MensajeErrorBean> listaMensajeError;
	private String valor;
	
	public FiseFormato12CC getFiseFormato12CC() {
		return fiseFormato12CC;
	}
	public void setFiseFormato12CC(FiseFormato12CC fiseFormato12CC) {
		this.fiseFormato12CC = fiseFormato12CC;
	}
	public String getMensajeInformacion() {
		return mensajeInformacion;
	}
	public void setMensajeInformacion(String mensajeInformacion) {
		this.mensajeInformacion = mensajeInformacion;
	}
	public List<MensajeErrorBean> getListaMensajeError() {
		return listaMensajeError;
	}
	public void setListaMensajeError(List<MensajeErrorBean> listaMensajeError) {
		this.listaMensajeError = listaMensajeError;
	}
	public String getMensajeError() {
		return mensajeError;
	}
	public void setMensajeError(String mensajeError) {
		this.mensajeError = mensajeError;
	}
	public String getValor() {
		return valor;
	}
	public void setValor(String valor) {
		this.valor = valor;
	}
	
	
	

}
