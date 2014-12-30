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
	private String desEstado;
	
	private boolean admin;	
	
	
	private String codigoEmpresa;
	private String anioReporte;//ano reporte
	private String mesReporte;//mes reporte
	private String aplicaIgv;
	private String gloza;//gloza
	private String numDoc;//numero de docuemnto del informe de sustento
	private String numDocRecepcion;	//numero de documento recepcion
	private String fechaSustento;//fecha informe sustento	
	private String fechaRecepcion;//fecha de recepcion
	private String igv;	
	private String montoCanje;//monto transferido por canje
	
	private String montoMesR;//monto cargo fijo al mes rural
	private String montoMesP;//monto cargo fijo al mes provincias
	private String montoMesL;//monto cargo fijo al mes lima
	
	private String numAgenteR;//numero de agentes rural
	private String numAgenteP;//numero de agentes provincias
	private String numAgenteL;//numero de agentes lima
	
	private String numUsuBenefR;//numero de usuarios beneficiarios rural
	private String numUsuBenefP;//numero de usuarios beneficiarios provincias
	private String numUsuBenefL;//numero de usuarios beneficiarios lima
	
	private String numUsuEmpR;//numero de usuarios empadronados rural
	private String numUsuEmpP;//numero de usuarios empadronados provincias
	private String numUsuEmpL;//numero de usuarios empadronados lima
	
	private String numValDCanR;//numero de vales digitales cangeados rural
	private String numValDCanP;//numero de vales digitales cangeados provincias
	private String numValDCanL;//numero de vales digitales cangeados lima
	
	private String numValDEmiR;//numero de vales digitales emitidos rural
	private String numValDEmiP;//numero de vales digitales emitidos provincias
	private String numValDEmiL;//numero de vales digitales emitidos lima
	
	private String numValFCanR;//numero de vales fisicos cangeados rural
	private String numValFCanP;//numero de vales fisicos cangeados provincias
	private String numValFCanL;//numero de vales fisicos cangeados lima
	
	private String numValFEmiR;//numero de vales fisicos emitidos rural
	private String numValFEmiP;//numero de vales fisicos emitidos provincias
	private String numValFEmiL;//numero de vales fisicos emitidos lima
	
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
	
	public String getCodigoEmpresa() {
		return codigoEmpresa;
	}
	public void setCodigoEmpresa(String codigoEmpresa) {
		this.codigoEmpresa = codigoEmpresa;
	}
	public String getAnioReporte() {
		return anioReporte;
	}
	public void setAnioReporte(String anioReporte) {
		this.anioReporte = anioReporte;
	}
	public String getMesReporte() {
		return mesReporte;
	}
	public void setMesReporte(String mesReporte) {
		this.mesReporte = mesReporte;
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
	
	public String getMontoCanje() {
		return montoCanje;
	}
	public void setMontoCanje(String montoCanje) {
		this.montoCanje = montoCanje;
	}
	
	public String getMontoMesR() {
		return montoMesR;
	}
	public void setMontoMesR(String montoMesR) {
		this.montoMesR = montoMesR;
	}
	public String getMontoMesP() {
		return montoMesP;
	}
	public void setMontoMesP(String montoMesP) {
		this.montoMesP = montoMesP;
	}
	public String getMontoMesL() {
		return montoMesL;
	}
	public void setMontoMesL(String montoMesL) {
		this.montoMesL = montoMesL;
	}
	public String getNumAgenteR() {
		return numAgenteR;
	}
	public void setNumAgenteR(String numAgenteR) {
		this.numAgenteR = numAgenteR;
	}
	public String getNumAgenteP() {
		return numAgenteP;
	}
	public void setNumAgenteP(String numAgenteP) {
		this.numAgenteP = numAgenteP;
	}
	public String getNumAgenteL() {
		return numAgenteL;
	}
	public void setNumAgenteL(String numAgenteL) {
		this.numAgenteL = numAgenteL;
	}
	public String getNumUsuBenefR() {
		return numUsuBenefR;
	}
	public void setNumUsuBenefR(String numUsuBenefR) {
		this.numUsuBenefR = numUsuBenefR;
	}
	public String getNumUsuBenefP() {
		return numUsuBenefP;
	}
	public void setNumUsuBenefP(String numUsuBenefP) {
		this.numUsuBenefP = numUsuBenefP;
	}
	public String getNumUsuBenefL() {
		return numUsuBenefL;
	}
	public void setNumUsuBenefL(String numUsuBenefL) {
		this.numUsuBenefL = numUsuBenefL;
	}
	public String getNumUsuEmpR() {
		return numUsuEmpR;
	}
	public void setNumUsuEmpR(String numUsuEmpR) {
		this.numUsuEmpR = numUsuEmpR;
	}
	public String getNumUsuEmpP() {
		return numUsuEmpP;
	}
	public void setNumUsuEmpP(String numUsuEmpP) {
		this.numUsuEmpP = numUsuEmpP;
	}
	public String getNumUsuEmpL() {
		return numUsuEmpL;
	}
	public void setNumUsuEmpL(String numUsuEmpL) {
		this.numUsuEmpL = numUsuEmpL;
	}
	public String getNumValDCanR() {
		return numValDCanR;
	}
	public void setNumValDCanR(String numValDCanR) {
		this.numValDCanR = numValDCanR;
	}
	public String getNumValDCanP() {
		return numValDCanP;
	}
	public void setNumValDCanP(String numValDCanP) {
		this.numValDCanP = numValDCanP;
	}
	public String getNumValDCanL() {
		return numValDCanL;
	}
	public void setNumValDCanL(String numValDCanL) {
		this.numValDCanL = numValDCanL;
	}
	public String getNumValDEmiR() {
		return numValDEmiR;
	}
	public void setNumValDEmiR(String numValDEmiR) {
		this.numValDEmiR = numValDEmiR;
	}
	public String getNumValDEmiP() {
		return numValDEmiP;
	}
	public void setNumValDEmiP(String numValDEmiP) {
		this.numValDEmiP = numValDEmiP;
	}
	public String getNumValDEmiL() {
		return numValDEmiL;
	}
	public void setNumValDEmiL(String numValDEmiL) {
		this.numValDEmiL = numValDEmiL;
	}
	public String getNumValFCanR() {
		return numValFCanR;
	}
	public void setNumValFCanR(String numValFCanR) {
		this.numValFCanR = numValFCanR;
	}
	public String getNumValFCanP() {
		return numValFCanP;
	}
	public void setNumValFCanP(String numValFCanP) {
		this.numValFCanP = numValFCanP;
	}
	public String getNumValFCanL() {
		return numValFCanL;
	}
	public void setNumValFCanL(String numValFCanL) {
		this.numValFCanL = numValFCanL;
	}
	public String getNumValFEmiR() {
		return numValFEmiR;
	}
	public void setNumValFEmiR(String numValFEmiR) {
		this.numValFEmiR = numValFEmiR;
	}
	public String getNumValFEmiP() {
		return numValFEmiP;
	}
	public void setNumValFEmiP(String numValFEmiP) {
		this.numValFEmiP = numValFEmiP;
	}
	public String getNumValFEmiL() {
		return numValFEmiL;
	}
	public void setNumValFEmiL(String numValFEmiL) {
		this.numValFEmiL = numValFEmiL;
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
	public String getDesEstado() {
		return desEstado;
	}
	public void setDesEstado(String desEstado) {
		this.desEstado = desEstado;
	}
	
    
    

}
