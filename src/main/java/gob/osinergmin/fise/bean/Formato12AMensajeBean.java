package gob.osinergmin.fise.bean;

import gob.osinergmin.fise.domain.FiseFormato12AC;

import java.io.Serializable;
import java.util.List;

public class Formato12AMensajeBean implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private FiseFormato12AC fiseFormato12AC;
	private String mensajeInformacion;
	private String mensajeError;
	private List<MensajeErrorBean> listaMensajeError;
	
	public FiseFormato12AC getFiseFormato12AC() {
		return fiseFormato12AC;
	}
	public void setFiseFormato12AC(FiseFormato12AC fiseFormato12AC) {
		this.fiseFormato12AC = fiseFormato12AC;
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
