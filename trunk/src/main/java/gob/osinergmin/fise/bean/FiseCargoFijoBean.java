package gob.osinergmin.fise.bean;

import gob.osinergmin.fise.domain.AdmEmpresa;

import java.util.List;
import java.util.Map;


public class FiseCargoFijoBean {
	
	/**********Datos de busqueda***************/
	private List<AdmEmpresa> listaEmpresas;
	private Map<Long,String> listaMes;
	
	private String codEmpresaBusq;
	private String anioRepBusq;
	private String mesRepBusq;	
	
	private String desEmpresa;	
	private String desMesRep;
	
	private boolean admin;	
	private String flagEditar;//para saber si esta editando o visualizando
	
	
	private String codEmpresa;
	private String anioRep;//ano reporte
	private String mesRep;//mes reporte
	private String aplicaIgv;
	private String gloza;//gloza
	private String numDoc;//numero de docuemnto del informe de sustento
	private String numDocRecepcion;	//numero de documento recepcion
	private String fechaSustento;//fecha informe sustento	
	private String fechaRecepcion;//fecha de recepcion
	private String igv;
	private String montoMes;//monto cargo fijo al mes
	private String montoCanje;//monto transferido por canje
	private String numAgen;//numero de agentes
	private String numUsuBenef;//numero de usuarios beneficiarios
	private String numUsuEmp;//numero de usuarios empadronados
	private String numValDCan;//numero de vales digitales cangeados
	private String numValDEmi;//numero de vales digitales emitidos
	private String numValFCan;//numero de vales fisicos cangeados
	private String numValFEmi;//numero de vales fisicos emitidos
	private String estado;//estado de registro
	private String usuario;
    private String terminal;
    
        
	public List<AdmEmpresa> getListaEmpresas() {
		return listaEmpresas;
	}
	public void setListaEmpresas(List<AdmEmpresa> listaEmpresas) {
		this.listaEmpresas = listaEmpresas;
	}
	public Map<Long, String> getListaMes() {
		return listaMes;
	}
	public void setListaMes(Map<Long, String> listaMes) {
		this.listaMes = listaMes;
	}	
	public String getCodEmpresaBusq() {
		return codEmpresaBusq;
	}
	public void setCodEmpresaBusq(String codEmpresaBusq) {
		this.codEmpresaBusq = codEmpresaBusq;
	}
	public String getAnioRepBusq() {
		return anioRepBusq;
	}
	public void setAnioRepBusq(String anioRepBusq) {
		this.anioRepBusq = anioRepBusq;
	}
	public String getMesRepBusq() {
		return mesRepBusq;
	}
	public void setMesRepBusq(String mesRepBusq) {
		this.mesRepBusq = mesRepBusq;
	}
	public String getDesEmpresa() {
		return desEmpresa;
	}
	public void setDesEmpresa(String desEmpresa) {
		this.desEmpresa = desEmpresa;
	}
	public String getDesMesRep() {
		return desMesRep;
	}
	public void setDesMesRep(String desMesRep) {
		this.desMesRep = desMesRep;
	}
	public String getCodEmpresa() {
		return codEmpresa;
	}
	public void setCodEmpresa(String codEmpresa) {
		this.codEmpresa = codEmpresa;
	}
	public String getAnioRep() {
		return anioRep;
	}
	public void setAnioRep(String anioRep) {
		this.anioRep = anioRep;
	}
	public String getMesRep() {
		return mesRep;
	}
	public void setMesRep(String mesRep) {
		this.mesRep = mesRep;
	}
	public String getAplicaIgv() {
		return aplicaIgv;
	}
	public void setAplicaIgv(String aplicaIgv) {
		this.aplicaIgv = aplicaIgv;
	}
	public String getGloza() {
		return gloza;
	}
	public void setGloza(String gloza) {
		this.gloza = gloza;
	}
	public String getNumDoc() {
		return numDoc;
	}
	public void setNumDoc(String numDoc) {
		this.numDoc = numDoc;
	}
	public String getNumDocRecepcion() {
		return numDocRecepcion;
	}
	public void setNumDocRecepcion(String numDocRecepcion) {
		this.numDocRecepcion = numDocRecepcion;
	}
	public String getFechaSustento() {
		return fechaSustento;
	}
	public void setFechaSustento(String fechaSustento) {
		this.fechaSustento = fechaSustento;
	}
	public String getFechaRecepcion() {
		return fechaRecepcion;
	}
	public void setFechaRecepcion(String fechaRecepcion) {
		this.fechaRecepcion = fechaRecepcion;
	}
	public String getIgv() {
		return igv;
	}
	public void setIgv(String igv) {
		this.igv = igv;
	}
	public String getMontoMes() {
		return montoMes;
	}
	public void setMontoMes(String montoMes) {
		this.montoMes = montoMes;
	}
	public String getMontoCanje() {
		return montoCanje;
	}
	public void setMontoCanje(String montoCanje) {
		this.montoCanje = montoCanje;
	}
	public String getNumAgen() {
		return numAgen;
	}
	public void setNumAgen(String numAgen) {
		this.numAgen = numAgen;
	}
	public String getNumUsuBenef() {
		return numUsuBenef;
	}
	public void setNumUsuBenef(String numUsuBenef) {
		this.numUsuBenef = numUsuBenef;
	}
	public String getNumUsuEmp() {
		return numUsuEmp;
	}
	public void setNumUsuEmp(String numUsuEmp) {
		this.numUsuEmp = numUsuEmp;
	}
	public String getNumValDCan() {
		return numValDCan;
	}
	public void setNumValDCan(String numValDCan) {
		this.numValDCan = numValDCan;
	}
	public String getNumValDEmi() {
		return numValDEmi;
	}
	public void setNumValDEmi(String numValDEmi) {
		this.numValDEmi = numValDEmi;
	}
	public String getNumValFCan() {
		return numValFCan;
	}
	public void setNumValFCan(String numValFCan) {
		this.numValFCan = numValFCan;
	}
	public String getNumValFEmi() {
		return numValFEmi;
	}
	public void setNumValFEmi(String numValFEmi) {
		this.numValFEmi = numValFEmi;
	}
	public String getEstado() {
		return estado;
	}
	public void setEstado(String estado) {
		this.estado = estado;
	}
	public String getUsuario() {
		return usuario;
	}
	public void setUsuario(String usuario) {
		this.usuario = usuario;
	}
	public String getTerminal() {
		return terminal;
	}
	public void setTerminal(String terminal) {
		this.terminal = terminal;
	}
	public boolean isAdmin() {
		return admin;
	}
	public void setAdmin(boolean admin) {
		this.admin = admin;
	}
	public String getFlagEditar() {
		return flagEditar;
	}
	public void setFlagEditar(String flagEditar) {
		this.flagEditar = flagEditar;
	}  
    
    

}
