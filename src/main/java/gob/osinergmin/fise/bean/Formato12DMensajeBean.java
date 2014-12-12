package gob.osinergmin.fise.bean;

import gob.osinergmin.fise.domain.FiseFormato12DC;

import java.io.Serializable;
import java.util.List;

public class Formato12DMensajeBean implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private FiseFormato12DC fiseFormato12DC;
	private String mensajeInformacion;
	private String mensajeError;
	private List<MensajeErrorBean> listaMensajeError;
	private String valor;
	
	public FiseFormato12DC getFiseFormato12DC() {
		return fiseFormato12DC;
	}
	public void setFiseFormato12DC(FiseFormato12DC fiseFormato12DC) {
		this.fiseFormato12DC = fiseFormato12DC;
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
