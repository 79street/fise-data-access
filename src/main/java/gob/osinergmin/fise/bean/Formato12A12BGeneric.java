package gob.osinergmin.fise.bean;

import gob.osinergmin.fise.domain.FiseFormato12AC;

public class Formato12A12BGeneric {
	
	private String codigoEmpresa;
	private long anoPresentacion;
	private long mesPresentacion;
	private long anoEjecucion;
	private long mesEjecucion;
	private String codigoEtapa;

	public Formato12A12BGeneric(Object object){
		if(object instanceof FiseFormato12AC){
			this.codigoEmpresa=((FiseFormato12AC) object).getId().getCodEmpresa();
			this.anoPresentacion=((FiseFormato12AC) object).getId().getAnoPresentacion();
			this.mesPresentacion=((FiseFormato12AC) object).getId().getMesPresentacion();
			this.anoEjecucion=((FiseFormato12AC) object).getId().getAnoEjecucionGasto();
			this.mesEjecucion=((FiseFormato12AC) object).getId().getMesEjecucionGasto();
			this.codigoEtapa=((FiseFormato12AC) object).getId().getEtapa();
	    }
		/*else if(object instanceof FiseFormato12BC){
			this.codigoEmpresa=((FiseFormato12AC) object).getId().getCodEmpresa();
			this.anoPresentacion=((FiseFormato12AC) object).getId().getAnoPresentacion();
			this.mesPresentacion=((FiseFormato12AC) object).getId().getMesPresentacion();
			this.anoEjecucion=((FiseFormato12AC) object).getId().getAnoEjecucionGasto();
			this.mesEjecucion=((FiseFormato12AC) object).getId().getMesEjecucionGasto();
			this.codigoEtapa=((FiseFormato12AC) object).getId().getEtapa();
	    }*/
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

	public long getAnoEjecucion() {
		return anoEjecucion;
	}

	public void setAnoEjecucion(long anoEjecucion) {
		this.anoEjecucion = anoEjecucion;
	}

	public long getMesEjecucion() {
		return mesEjecucion;
	}

	public void setMesEjecucion(long mesEjecucion) {
		this.mesEjecucion = mesEjecucion;
	}

	public String getCodigoEtapa() {
		return codigoEtapa;
	}

	public void setCodigoEtapa(String codigoEtapa) {
		this.codigoEtapa = codigoEtapa;
	}

	
	
}
