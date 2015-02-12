package gob.osinergmin.fise.bean;

import gob.osinergmin.fise.domain.FiseFormato13AC;

import java.io.Serializable;
import java.util.List;

public class Formato13AMensajeBean implements Serializable {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private FiseFormato13AC fiseFormato13AC;
	private String mensajeInformacion;
	private String mensajeError;
	private List<MensajeErrorBean> listaMensajeError;
	private String valor;
	
	public FiseFormato13AC getFiseFormato13AC() {
		return fiseFormato13AC;
	}
	public void setFiseFormato13AC(FiseFormato13AC fiseFormato13AC) {
		this.fiseFormato13AC = fiseFormato13AC;
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
