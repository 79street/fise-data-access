package gob.osinergmin.fise.bean;

import gob.osinergmin.fise.domain.FiseFormato13AC;

public class Formato12C12D13Generic {
	
	private String codigoEmpresa;
	private long anoPresentacion;
	private long mesPresentacion;
	private String codigoEtapa;

	//descomentar para cuando esten mapeado los otros dto
	public Formato12C12D13Generic(Object object){
		/*if(object instanceof FiseFormato12CC){
			this.codigoEmpresa=((FiseFormato12CC) object).getId().getCodEmpresa();
			this.anoPresentacion=((FiseFormato12CC) object).getId().getAnoPresentacion();
			this.mesPresentacion=((FiseFormato12CC) object).getId().getMesPresentacion();
			this.codigoEtapa=((FiseFormato12CC) object).getId().getEtapa();
	    }else if(object instanceof FiseFormato12DC){
			this.codigoEmpresa=((FiseFormato12DC) object).getId().getCodEmpresa();
			this.anoPresentacion=((FiseFormato12DC) object).getId().getAnoPresentacion();
			this.mesPresentacion=((FiseFormato12DC) object).getId().getMesPresentacion();
			this.codigoEtapa=((FiseFormato12DC) object).getId().getEtapa();
	    }else*/ if(object instanceof FiseFormato13AC){
			this.codigoEmpresa=((FiseFormato13AC) object).getId().getCodEmpresa();
			this.anoPresentacion=((FiseFormato13AC) object).getId().getAnoPresentacion();
			this.mesPresentacion=((FiseFormato13AC) object).getId().getMesPresentacion();
			this.codigoEtapa=((FiseFormato13AC) object).getId().getEtapa();
	    }
	}

	public String getCodigoEmpresa() {
		return codigoEmpresa;
	}

	public void setCodigoEmpresa(String codigoEmpresa) {
		this.codigoEmpresa = codigoEmpresa;
	}

	public long getAnoPresentacion() {
		return anoPresentacion;
	}

	public void setAnoPresentacion(long anoPresentacion) {
		this.anoPresentacion = anoPresentacion;
	}

	public long getMesPresentacion() {
		return mesPresentacion;
	}

	public void setMesPresentacion(long mesPresentacion) {
		this.mesPresentacion = mesPresentacion;
	}

	public String getCodigoEtapa() {
		return codigoEtapa;
	}

	public void setCodigoEtapa(String codigoEtapa) {
		this.codigoEtapa = codigoEtapa;
	}

	
	
}
