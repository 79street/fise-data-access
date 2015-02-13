package gob.osinergmin.fise.bean;

import gob.osinergmin.fise.domain.FiseFormato12BC;

import java.io.Serializable;
import java.util.List;

public class Formato12BMensajeBean implements Serializable {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private FiseFormato12BC fiseFormato12BC;
	private String mensajeInformacion;
	private String mensajeError;
	private List<MensajeErrorBean> listaMensajeError;
	private String valor;
	
	public FiseFormato12BC getFiseFormato12BC() {
		return fiseFormato12BC;
	}
	public void setFiseFormato12BC(FiseFormato12BC fiseFormato12BC) {
		this.fiseFormato12BC = fiseFormato12BC;
	}
	public String getMensajeInformacion() {
		return mensajeInformacion;
	}
	public void setMensajeInformacion(String mensajeInformacion) {
		this.mensajeInformacion = mensajeInformacion;
	}
	public String getMensajeError() {
		return mensajeError;
	}
	public void setMensajeError(String mensajeError) {
		this.mensajeError = mensajeError;
	}
	public List<MensajeErrorBean> getListaMensajeError() {
		return listaMensajeError;
	}
	public void setListaMensajeError(List<MensajeErrorBean> listaMensajeError) {
		this.listaMensajeError = listaMensajeError;
	}
	public String getValor() {
		return valor;
	}
	public void setValor(String valor) {
		this.valor = valor;
	}

	
	
}
