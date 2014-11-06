package gob.osinergmin.fise.bean;

import gob.osinergmin.fise.domain.FiseFormato14AC;
import gob.osinergmin.fise.domain.FiseFormato14BC;
import gob.osinergmin.fise.domain.FiseFormato14CC;

public class Formato14Generic {
	
	private String codigoEmpresa;
	private long anoPresentacion;
	private long mesPresentacion;
	private long anoInicioVigencia;
	private long anoFinVigencia;
	private String codigoEtapa;

	public Formato14Generic(Object object){
		if(object instanceof FiseFormato14AC){
			this.codigoEmpresa=((FiseFormato14AC) object).getId().getCodEmpresa();
			this.anoPresentacion=((FiseFormato14AC) object).getId().getAnoPresentacion();
			this.mesPresentacion=((FiseFormato14AC) object).getId().getMesPresentacion();
			this.anoInicioVigencia=((FiseFormato14AC) object).getId().getAnoInicioVigencia();
			this.anoFinVigencia=((FiseFormato14AC) object).getId().getAnoFinVigencia();
			this.codigoEtapa=((FiseFormato14AC) object).getId().getEtapa();
	    }else if(object instanceof FiseFormato14BC){
			this.codigoEmpresa=((FiseFormato14BC) object).getId().getCodEmpresa();
			this.anoPresentacion=((FiseFormato14BC) object).getId().getAnoPresentacion();
			this.mesPresentacion=((FiseFormato14BC) object).getId().getMesPresentacion();
			this.anoInicioVigencia=((FiseFormato14BC) object).getId().getAnoInicioVigencia();
			this.anoFinVigencia=((FiseFormato14BC) object).getId().getAnoFinVigencia();
			this.codigoEtapa=((FiseFormato14BC) object).getId().getEtapa();
	    }else if(object instanceof FiseFormato14CC){
			this.codigoEmpresa=((FiseFormato14CC) object).getId().getCodEmpresa();
			this.anoPresentacion=((FiseFormato14CC) object).getId().getAnoPresentacion();
			this.mesPresentacion=((FiseFormato14CC) object).getId().getMesPresentacion();
			this.anoInicioVigencia=((FiseFormato14CC) object).getId().getAnoInicioVigencia();
			this.anoFinVigencia=((FiseFormato14CC) object).getId().getAnoFinVigencia();
			this.codigoEtapa=((FiseFormato14CC) object).getId().getEtapa();
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

	public long getAnoInicioVigencia() {
		return anoInicioVigencia;
	}

	public void setAnoInicioVigencia(long anoInicioVigencia) {
		this.anoInicioVigencia = anoInicioVigencia;
	}

	public long getAnoFinVigencia() {
		return anoFinVigencia;
	}

	public void setAnoFinVigencia(long anoFinVigencia) {
		this.anoFinVigencia = anoFinVigencia;
	}

	public String getCodigoEtapa() {
		return codigoEtapa;
	}

	public void setCodigoEtapa(String codigoEtapa) {
		this.codigoEtapa = codigoEtapa;
	}
	
	
	
}
