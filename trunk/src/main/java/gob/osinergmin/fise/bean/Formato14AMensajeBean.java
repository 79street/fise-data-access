package gob.osinergmin.fise.bean;

import gob.osinergmin.fise.domain.FiseFormato14AC;

import java.io.Serializable;
import java.util.List;

public class Formato14AMensajeBean implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private FiseFormato14AC fiseFormato14AC;
	private String mensajeInformacion;
	private String mensajeError;
	private List<MensajeErrorBean> listaMensajeError;
	private String valor;
	//para el formato 14c
	private Formato14CBean formato14CBean;
	
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
	public Formato14CBean getFormato14CBean() {
		return formato14CBean;
	}
	public void setFormato14CBean(Formato14CBean formato14cBean) {
		formato14CBean = formato14cBean;
	}
	public String getValor() {
		return valor;
	}
	public void setValor(String valor) {
		this.valor = valor;
	}
	
	
	

}
