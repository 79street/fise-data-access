package gob.osinergmin.fise.bean;

import gob.osinergmin.fise.domain.FiseFormato14AC;

import java.io.Serializable;
import java.util.List;

public class Formato14BMensajeBean implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private FiseFormato14AC fiseFormato14AC;
	private String mensajeInformacion;
	private String mensajeError;
	private List<MensajeErrorBean> listaMensajeError;
	
	public FiseFormato14AC getFiseFormato14AC() {
		return fiseFormato14AC;
	}
	public void setFiseFormato14AC(FiseFormato14AC fiseFormato14AC) {
		this.fiseFormato14AC = fiseFormato14AC;
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
