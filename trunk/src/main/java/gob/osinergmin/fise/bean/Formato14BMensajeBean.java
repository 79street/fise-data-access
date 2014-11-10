package gob.osinergmin.fise.bean;

import gob.osinergmin.fise.domain.FiseFormato14BC;

import java.io.Serializable;
import java.util.List;

public class Formato14BMensajeBean implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private FiseFormato14BC fiseFormato14BC;
	private String mensajeInformacion;
	private String mensajeError;
	private List<MensajeErrorBean> listaMensajeError;
	
	public FiseFormato14BC getFiseFormato14BC() {
		return fiseFormato14BC;
	}
	public void setFiseFormato14BC(FiseFormato14BC fiseFormato14BC) {
		this.fiseFormato14BC = fiseFormato14BC;
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


}
