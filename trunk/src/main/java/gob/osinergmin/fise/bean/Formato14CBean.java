package gob.osinergmin.fise.bean;

import gob.osinergmin.fise.domain.AdmEmpresa;
import gob.osinergmin.fise.domain.FisePeriodoEnvio;

import java.util.List;
import java.util.Map;


public class Formato14CBean {

	/*******DATOS PARA BUSQUEDA**************/
	private Map<Long,String> listaMes;
	private List<AdmEmpresa> listaEmpresas;
	private String mesDesde;
	private String anioDesde;
	private String mesHasta;
	private String anioHasta;	
	private String codEmpresaBusq;
	private String etapaBusq;	
	private boolean admin;	
	
	/**DATOS PARA NUEVO REGISTRO*****/
	/**datos de la cabecera*/
	private String codEmpresa;
	private String anioPres;
	private String mesPres;	
	private String anioInicioVig;
	private String anioFinVig;
	private String etapa;	
	private String nombreSede;
	private String nombreExel;
	private String nombreText;
	private String numRural;//numero de beneficiarios del periodo anterior rural
	private String numUrbProv;//numero de beneficiarios del periodo anterior urbano provincias
	private String numUrbLima; //numero de beneficiarios del periodo anterior lima
	private String numTotal;//no editable
	private String costoPromRural;//costo promedio mensual utiles rural
	private String costoPromUrbProv;//costo promedio mensual utiles urbano provincias
	private String costoPromUrbLima;//costo promedio mensual utiles urbano lima
	//datos de auditoria
	private String usuario;
	private String terminal;
	
	
	private List<FisePeriodoEnvio> listaPeriodoEnvio;
	//private String codigoPeriodo;
	private String periodoEnvio;
	
	 /**datos del detalle de la cabecera*/
	private String idZonaBenef;
	private String idTipoPersonal;
	
	//***ZONA RURAL
	private String canDRCoord;//cantidad costo directo rural persona coordinador
	private String costDRCoord;//costo directo rural persona coordinador
	private String canIRCoord;//cantidad costo indirecto rural persona coordinador
	private String costIRCoord;//costo indirecto rural persona coordinador
	private String costTotalRCoord;//no editable
	
	
	private String canDRSupe;//cantidad costo directo rural persona supervisor
	private String costDRSupe;//costo directo rural persona supervisor
	private String canIRSupe;//cantidad costo indirecto rural persona supervisor
	private String costIRSupe;//costo indirecto rural persona supervisor
	private String costTotalRSupe;//no editable
	
	private String canDRGest;//cantidad costo directo rural persona gestor
	private String costDRGest;//costo directo rural persona gestor
	private String canIRGest;//cantidad costo indirecto rural persona gestor
	private String costIRGest;//costo indirecto rural persona gestor
	private String costTotalRGest;//no editable
	
	private String canDRAsist;//cantidad costo directo rural persona asistente /auxiliar	
	private String costDRAsist;//costo directo rural persona asistente /auxiliar
	private String canIRAsist;//cantidad costo indirecto rural persona asistente /auxiliar	
	private String costIRAsist;//costo indirecto rural persona asistente /auxiliar
	private String costTotalRAsist;//no editable
	
	//**ZONA URBANA PROVINCIAS
	private String canDPCoord;//cantidad costo directo urbano provincias persona coordinador
	private String costDPCoord;//costo directo urbano provincias persona coordinador
	private String canIPCoord;//cantidad costo indirecto urbano provincias persona coordinador
	private String costIPCoord;//costo indirecto urbano lima persona coordinador	
	private String costTotalPCoord;//no editable
	
	private String canDPSupe;//cantidad costo directo urbano provincias persona supervisor
	private String costDPSupe;//costo directo urbano provincias persona supervisor
	private String canIPSupe;//cantidad costo indirecto urbano provincias persona supervisor
	private String costIPSupe;//costo indirecto urbano lima persona supervisor	
	private String costTotalPSupe;//no editable
	
	private String canDPGest;//cantidad costo directo urbano provincias persona gestor
	private String costDPGest;//costo directo urbano provincias persona gestor
	private String canIPGest;//cantidad costo indirecto urbano provincias persona gestor
	private String costIPGest;//costo indirecto urbano lima persona gestor	
	private String costTotalPGest;//no editable
	
	private String canDPAsist;//cantidad costo directo urbano provincias persona asistente /auxiliar
	private String costDPAsist;//costo directo urbano provincias persona asistente /auxiliar
	private String canIPAsist;//cantidad costo indirecto urbano provincias persona asistente /auxiliar
	private String costIPAsist;//costo indirecto urbano lima persona asistente /auxiliar
	private String costTotalPAsist;//no editable
		
	//**ZONA URBANA LIMA
	private String canDLCoord;//cantidad costo directo urbano lima persona coordinador
	private String costDLCoord;//costo directo urbano lima persona coordinador
	private String canILCoord;//cantidad costo indirecto urbano lima persona coordinador
	private String costILCoord;//costo indirecto urbano lima persona coordinador
	private String costTotalLCoord;//no editable
	
	private String canDLSupe;//cantidad costo directo urbano lima persona supervisor
	private String costDLSupe;//costo directo urbano lima persona supervisor
	private String canILSupe;//cantidad costo indirecto urbano lima persona supervisor
	private String costILSupe;//costo indirecto urbano lima persona supervisor
	private String costTotalLSupe;//no editable
	
	private String canDLGest;//cantidad costo directo urbano lima persona gestor
	private String costDLGest;//costo directo urbano lima persona gestor
	private String canILGest;//cantidad costo indirecto urbano lima persona gestor
	private String costILGest;//costo indirecto urbano lima persona gestor
	private String costTotalLGest;//no editable
	
	private String canDLAsist;//cantidad costo directo urbano lima persona asistente /auxiliar
	private String costDLAsist;//costo directo urbano lima persona asistente /auxiliar
	private String canILAsist;//cantidad costo indirecto urbano lima persona asistente /auxiliar
	private String costILAsist;//costo indirecto urbano lima persona asistente /auxiliar
	private String costTotalLAsist;//no editable
	
	
	private String canTotalCoord;//no editable
	private String costTotalCoord;//no editable
	
	private String canTotalSupe;//no editable
	private String costTotalSupe;//no editable
	
	private String canTotalGest;//no editable
	private String costTotalGest;//no editable
	
	private String canTotalAsist;//no editable	
	private String costTotalAsist;//no editable
	
	private String canDRGP;//no editable GP gestion de personal
	private String costDRGP;//no editable GP gestion de personal
	private String canIRGP;//no editable GP gestion de personal
	private String costIRGP;//no editable GP gestion de personal
	private String costTotalRGP;//no editable GP gestion de personal
	
	private String canDPGP;//no editable GP gestion de personal
	private String costDPGP;//no editable GP gestion de personal
	private String canIPGP;//no editable GP gestion de personal
	private String costIPGP;//no editable GP gestion de personal
	private String costTotalPGP;//no editable GP gestion de personal
	
	private String canDLGP;//no editable GP gestion de personal
	private String costDLGP;//no editable GP gestion de personal
	private String canILGP;//no editable GP gestion de personal
	private String costILGP;//no editable GP gestion de personal
	private String costTotalLGP;//no editable GP gestion de personal
	
	private String canTotalGP;//no editable GP gestion de personal
	private String costTotalGP;//no editable GP gestion de personal
	
	private String costTotalPromedio;//no editable GP gestion de personal
	
	/**
	 * @return the codEmpresa
	 */
	public String getCodEmpresa() {
		return codEmpresa;
	}
	/**
	 * @param codEmpresa the codEmpresa to set
	 */
	public void setCodEmpresa(String codEmpresa) {
		this.codEmpresa = codEmpresa;
	}
	/**
	 * @return the anioPres
	 */
	public String getAnioPres() {
		return anioPres;
	}
	/**
	 * @param anioPres the anioPres to set
	 */
	public void setAnioPres(String anioPres) {
		this.anioPres = anioPres;
	}
	/**
	 * @return the mesPres
	 */
	public String getMesPres() {
		return mesPres;
	}
	/**
	 * @param mesPres the mesPres to set
	 */
	public void setMesPres(String mesPres) {
		this.mesPres = mesPres;
	}
	/**
	 * @return the etapa
	 */
	public String getEtapa() {
		return etapa;
	}
	/**
	 * @param etapa the etapa to set
	 */
	public void setEtapa(String etapa) {
		this.etapa = etapa;
	}
	/**
	 * @return the nombreSede
	 */
	public String getNombreSede() {
		return nombreSede;
	}
	/**
	 * @param nombreSede the nombreSede to set
	 */
	public void setNombreSede(String nombreSede) {
		this.nombreSede = nombreSede;
	}
	/**
	 * @return the nombreExel
	 */
	public String getNombreExel() {
		return nombreExel;
	}
	/**
	 * @param nombreExel the nombreExel to set
	 */
	public void setNombreExel(String nombreExel) {
		this.nombreExel = nombreExel;
	}
	/**
	 * @return the nombreText
	 */
	public String getNombreText() {
		return nombreText;
	}
	/**
	 * @param nombreText the nombreText to set
	 */
	public void setNombreText(String nombreText) {
		this.nombreText = nombreText;
	}
	/**
	 * @return the numRural
	 */
	public String getNumRural() {
		return numRural;
	}
	/**
	 * @param numRural the numRural to set
	 */
	public void setNumRural(String numRural) {
		this.numRural = numRural;
	}
	/**
	 * @return the numUrbProv
	 */
	public String getNumUrbProv() {
		return numUrbProv;
	}
	/**
	 * @param numUrbProv the numUrbProv to set
	 */
	public void setNumUrbProv(String numUrbProv) {
		this.numUrbProv = numUrbProv;
	}
	/**
	 * @return the numUrbLima
	 */
	public String getNumUrbLima() {
		return numUrbLima;
	}
	/**
	 * @param numUrbLima the numUrbLima to set
	 */
	public void setNumUrbLima(String numUrbLima) {
		this.numUrbLima = numUrbLima;
	}
	/**
	 * @return the costoPromRural
	 */
	public String getCostoPromRural() {
		return costoPromRural;
	}
	/**
	 * @param costoPromRural the costoPromRural to set
	 */
	public void setCostoPromRural(String costoPromRural) {
		this.costoPromRural = costoPromRural;
	}
	/**
	 * @return the costoPromUrbProv
	 */
	public String getCostoPromUrbProv() {
		return costoPromUrbProv;
	}
	/**
	 * @param costoPromUrbProv the costoPromUrbProv to set
	 */
	public void setCostoPromUrbProv(String costoPromUrbProv) {
		this.costoPromUrbProv = costoPromUrbProv;
	}
	/**
	 * @return the costoPromUrbLima
	 */
	public String getCostoPromUrbLima() {
		return costoPromUrbLima;
	}
	/**
	 * @param costoPromUrbLima the costoPromUrbLima to set
	 */
	public void setCostoPromUrbLima(String costoPromUrbLima) {
		this.costoPromUrbLima = costoPromUrbLima;
	}
	/**
	 * @return the idZonaBenef
	 */
	public String getIdZonaBenef() {
		return idZonaBenef;
	}
	/**
	 * @param idZonaBenef the idZonaBenef to set
	 */
	public void setIdZonaBenef(String idZonaBenef) {
		this.idZonaBenef = idZonaBenef;
	}
	/**
	 * @return the idTipoPersonal
	 */
	public String getIdTipoPersonal() {
		return idTipoPersonal;
	}
	/**
	 * @param idTipoPersonal the idTipoPersonal to set
	 */
	public void setIdTipoPersonal(String idTipoPersonal) {
		this.idTipoPersonal = idTipoPersonal;
	}
	/**
	 * @return the canDRCoord
	 */
	public String getCanDRCoord() {
		return canDRCoord;
	}
	/**
	 * @param canDRCoord the canDRCoord to set
	 */
	public void setCanDRCoord(String canDRCoord) {
		this.canDRCoord = canDRCoord;
	}
	/**
	 * @return the costDRCoord
	 */
	public String getCostDRCoord() {
		return costDRCoord;
	}
	/**
	 * @param costDRCoord the costDRCoord to set
	 */
	public void setCostDRCoord(String costDRCoord) {
		this.costDRCoord = costDRCoord;
	}
	/**
	 * @return the canIRCoord
	 */
	public String getCanIRCoord() {
		return canIRCoord;
	}
	/**
	 * @param canIRCoord the canIRCoord to set
	 */
	public void setCanIRCoord(String canIRCoord) {
		this.canIRCoord = canIRCoord;
	}
	/**
	 * @return the costIRCoord
	 */
	public String getCostIRCoord() {
		return costIRCoord;
	}
	/**
	 * @param costIRCoord the costIRCoord to set
	 */
	public void setCostIRCoord(String costIRCoord) {
		this.costIRCoord = costIRCoord;
	}
	/**
	 * @return the canDRSupe
	 */
	public String getCanDRSupe() {
		return canDRSupe;
	}
	/**
	 * @param canDRSupe the canDRSupe to set
	 */
	public void setCanDRSupe(String canDRSupe) {
		this.canDRSupe = canDRSupe;
	}
	/**
	 * @return the costDRSupe
	 */
	public String getCostDRSupe() {
		return costDRSupe;
	}
	/**
	 * @param costDRSupe the costDRSupe to set
	 */
	public void setCostDRSupe(String costDRSupe) {
		this.costDRSupe = costDRSupe;
	}
	/**
	 * @return the canIRSupe
	 */
	public String getCanIRSupe() {
		return canIRSupe;
	}
	/**
	 * @param canIRSupe the canIRSupe to set
	 */
	public void setCanIRSupe(String canIRSupe) {
		this.canIRSupe = canIRSupe;
	}
	/**
	 * @return the costIRSupe
	 */
	public String getCostIRSupe() {
		return costIRSupe;
	}
	/**
	 * @param costIRSupe the costIRSupe to set
	 */
	public void setCostIRSupe(String costIRSupe) {
		this.costIRSupe = costIRSupe;
	}
	/**
	 * @return the canDRGest
	 */
	public String getCanDRGest() {
		return canDRGest;
	}
	/**
	 * @param canDRGest the canDRGest to set
	 */
	public void setCanDRGest(String canDRGest) {
		this.canDRGest = canDRGest;
	}
	/**
	 * @return the costDRGest
	 */
	public String getCostDRGest() {
		return costDRGest;
	}
	/**
	 * @param costDRGest the costDRGest to set
	 */
	public void setCostDRGest(String costDRGest) {
		this.costDRGest = costDRGest;
	}
	/**
	 * @return the canIRGest
	 */
	public String getCanIRGest() {
		return canIRGest;
	}
	/**
	 * @param canIRGest the canIRGest to set
	 */
	public void setCanIRGest(String canIRGest) {
		this.canIRGest = canIRGest;
	}
	/**
	 * @return the costIRGest
	 */
	public String getCostIRGest() {
		return costIRGest;
	}
	/**
	 * @param costIRGest the costIRGest to set
	 */
	public void setCostIRGest(String costIRGest) {
		this.costIRGest = costIRGest;
	}
	/**
	 * @return the canDRAsist
	 */
	public String getCanDRAsist() {
		return canDRAsist;
	}
	/**
	 * @param canDRAsist the canDRAsist to set
	 */
	public void setCanDRAsist(String canDRAsist) {
		this.canDRAsist = canDRAsist;
	}
	/**
	 * @return the costDRAsist
	 */
	public String getCostDRAsist() {
		return costDRAsist;
	}
	/**
	 * @param costDRAsist the costDRAsist to set
	 */
	public void setCostDRAsist(String costDRAsist) {
		this.costDRAsist = costDRAsist;
	}
	/**
	 * @return the canIRAsist
	 */
	public String getCanIRAsist() {
		return canIRAsist;
	}
	/**
	 * @param canIRAsist the canIRAsist to set
	 */
	public void setCanIRAsist(String canIRAsist) {
		this.canIRAsist = canIRAsist;
	}
	/**
	 * @return the costIRAsist
	 */
	public String getCostIRAsist() {
		return costIRAsist;
	}
	/**
	 * @param costIRAsist the costIRAsist to set
	 */
	public void setCostIRAsist(String costIRAsist) {
		this.costIRAsist = costIRAsist;
	}
	/**
	 * @return the canDPCoord
	 */
	public String getCanDPCoord() {
		return canDPCoord;
	}
	/**
	 * @param canDPCoord the canDPCoord to set
	 */
	public void setCanDPCoord(String canDPCoord) {
		this.canDPCoord = canDPCoord;
	}
	/**
	 * @return the costDPCoord
	 */
	public String getCostDPCoord() {
		return costDPCoord;
	}
	/**
	 * @param costDPCoord the costDPCoord to set
	 */
	public void setCostDPCoord(String costDPCoord) {
		this.costDPCoord = costDPCoord;
	}
	/**
	 * @return the canIPCoord
	 */
	public String getCanIPCoord() {
		return canIPCoord;
	}
	/**
	 * @param canIPCoord the canIPCoord to set
	 */
	public void setCanIPCoord(String canIPCoord) {
		this.canIPCoord = canIPCoord;
	}
	/**
	 * @return the costIPCoord
	 */
	public String getCostIPCoord() {
		return costIPCoord;
	}
	/**
	 * @param costIPCoord the costIPCoord to set
	 */
	public void setCostIPCoord(String costIPCoord) {
		this.costIPCoord = costIPCoord;
	}
	/**
	 * @return the canDPSupe
	 */
	public String getCanDPSupe() {
		return canDPSupe;
	}
	/**
	 * @param canDPSupe the canDPSupe to set
	 */
	public void setCanDPSupe(String canDPSupe) {
		this.canDPSupe = canDPSupe;
	}
	/**
	 * @return the costDPSupe
	 */
	public String getCostDPSupe() {
		return costDPSupe;
	}
	/**
	 * @param costDPSupe the costDPSupe to set
	 */
	public void setCostDPSupe(String costDPSupe) {
		this.costDPSupe = costDPSupe;
	}
	/**
	 * @return the canIPSupe
	 */
	public String getCanIPSupe() {
		return canIPSupe;
	}
	/**
	 * @param canIPSupe the canIPSupe to set
	 */
	public void setCanIPSupe(String canIPSupe) {
		this.canIPSupe = canIPSupe;
	}
	/**
	 * @return the costIPSupe
	 */
	public String getCostIPSupe() {
		return costIPSupe;
	}
	/**
	 * @param costIPSupe the costIPSupe to set
	 */
	public void setCostIPSupe(String costIPSupe) {
		this.costIPSupe = costIPSupe;
	}
	/**
	 * @return the canDPGest
	 */
	public String getCanDPGest() {
		return canDPGest;
	}
	/**
	 * @param canDPGest the canDPGest to set
	 */
	public void setCanDPGest(String canDPGest) {
		this.canDPGest = canDPGest;
	}
	/**
	 * @return the costDPGest
	 */
	public String getCostDPGest() {
		return costDPGest;
	}
	/**
	 * @param costDPGest the costDPGest to set
	 */
	public void setCostDPGest(String costDPGest) {
		this.costDPGest = costDPGest;
	}
	/**
	 * @return the canIPGest
	 */
	public String getCanIPGest() {
		return canIPGest;
	}
	/**
	 * @param canIPGest the canIPGest to set
	 */
	public void setCanIPGest(String canIPGest) {
		this.canIPGest = canIPGest;
	}
	/**
	 * @return the costIPGest
	 */
	public String getCostIPGest() {
		return costIPGest;
	}
	/**
	 * @param costIPGest the costIPGest to set
	 */
	public void setCostIPGest(String costIPGest) {
		this.costIPGest = costIPGest;
	}
	/**
	 * @return the canDPAsist
	 */
	public String getCanDPAsist() {
		return canDPAsist;
	}
	/**
	 * @param canDPAsist the canDPAsist to set
	 */
	public void setCanDPAsist(String canDPAsist) {
		this.canDPAsist = canDPAsist;
	}
	/**
	 * @return the costDPAsist
	 */
	public String getCostDPAsist() {
		return costDPAsist;
	}
	/**
	 * @param costDPAsist the costDPAsist to set
	 */
	public void setCostDPAsist(String costDPAsist) {
		this.costDPAsist = costDPAsist;
	}
	/**
	 * @return the canIPAsist
	 */
	public String getCanIPAsist() {
		return canIPAsist;
	}
	/**
	 * @param canIPAsist the canIPAsist to set
	 */
	public void setCanIPAsist(String canIPAsist) {
		this.canIPAsist = canIPAsist;
	}
	/**
	 * @return the costIPAsist
	 */
	public String getCostIPAsist() {
		return costIPAsist;
	}
	/**
	 * @param costIPAsist the costIPAsist to set
	 */
	public void setCostIPAsist(String costIPAsist) {
		this.costIPAsist = costIPAsist;
	}
	/**
	 * @return the canDLCoord
	 */
	public String getCanDLCoord() {
		return canDLCoord;
	}
	/**
	 * @param canDLCoord the canDLCoord to set
	 */
	public void setCanDLCoord(String canDLCoord) {
		this.canDLCoord = canDLCoord;
	}
	/**
	 * @return the costDLCoord
	 */
	public String getCostDLCoord() {
		return costDLCoord;
	}
	/**
	 * @param costDLCoord the costDLCoord to set
	 */
	public void setCostDLCoord(String costDLCoord) {
		this.costDLCoord = costDLCoord;
	}
	/**
	 * @return the canILCoord
	 */
	public String getCanILCoord() {
		return canILCoord;
	}
	/**
	 * @param canILCoord the canILCoord to set
	 */
	public void setCanILCoord(String canILCoord) {
		this.canILCoord = canILCoord;
	}
	/**
	 * @return the costILCoord
	 */
	public String getCostILCoord() {
		return costILCoord;
	}
	/**
	 * @param costILCoord the costILCoord to set
	 */
	public void setCostILCoord(String costILCoord) {
		this.costILCoord = costILCoord;
	}
	/**
	 * @return the canDLSupe
	 */
	public String getCanDLSupe() {
		return canDLSupe;
	}
	/**
	 * @param canDLSupe the canDLSupe to set
	 */
	public void setCanDLSupe(String canDLSupe) {
		this.canDLSupe = canDLSupe;
	}
	/**
	 * @return the costDLSupe
	 */
	public String getCostDLSupe() {
		return costDLSupe;
	}
	/**
	 * @param costDLSupe the costDLSupe to set
	 */
	public void setCostDLSupe(String costDLSupe) {
		this.costDLSupe = costDLSupe;
	}
	/**
	 * @return the canILSupe
	 */
	public String getCanILSupe() {
		return canILSupe;
	}
	/**
	 * @param canILSupe the canILSupe to set
	 */
	public void setCanILSupe(String canILSupe) {
		this.canILSupe = canILSupe;
	}
	/**
	 * @return the costILSupe
	 */
	public String getCostILSupe() {
		return costILSupe;
	}
	/**
	 * @param costILSupe the costILSupe to set
	 */
	public void setCostILSupe(String costILSupe) {
		this.costILSupe = costILSupe;
	}
	/**
	 * @return the canDLGest
	 */
	public String getCanDLGest() {
		return canDLGest;
	}
	/**
	 * @param canDLGest the canDLGest to set
	 */
	public void setCanDLGest(String canDLGest) {
		this.canDLGest = canDLGest;
	}
	/**
	 * @return the costDLGest
	 */
	public String getCostDLGest() {
		return costDLGest;
	}
	/**
	 * @param costDLGest the costDLGest to set
	 */
	public void setCostDLGest(String costDLGest) {
		this.costDLGest = costDLGest;
	}
	/**
	 * @return the canILGest
	 */
	public String getCanILGest() {
		return canILGest;
	}
	/**
	 * @param canILGest the canILGest to set
	 */
	public void setCanILGest(String canILGest) {
		this.canILGest = canILGest;
	}
	/**
	 * @return the costILGest
	 */
	public String getCostILGest() {
		return costILGest;
	}
	/**
	 * @param costILGest the costILGest to set
	 */
	public void setCostILGest(String costILGest) {
		this.costILGest = costILGest;
	}
	/**
	 * @return the canDLAsist
	 */
	public String getCanDLAsist() {
		return canDLAsist;
	}
	/**
	 * @param canDLAsist the canDLAsist to set
	 */
	public void setCanDLAsist(String canDLAsist) {
		this.canDLAsist = canDLAsist;
	}
	/**
	 * @return the costDLAsist
	 */
	public String getCostDLAsist() {
		return costDLAsist;
	}
	/**
	 * @param costDLAsist the costDLAsist to set
	 */
	public void setCostDLAsist(String costDLAsist) {
		this.costDLAsist = costDLAsist;
	}
	/**
	 * @return the canILAsist
	 */
	public String getCanILAsist() {
		return canILAsist;
	}
	/**
	 * @param canILAsist the canILAsist to set
	 */
	public void setCanILAsist(String canILAsist) {
		this.canILAsist = canILAsist;
	}
	/**
	 * @return the costILAsist
	 */
	public String getCostILAsist() {
		return costILAsist;
	}
	/**
	 * @param costILAsist the costILAsist to set
	 */
	public void setCostILAsist(String costILAsist) {
		this.costILAsist = costILAsist;
	}
	public Map<Long, String> getListaMes() {
		return listaMes;
	}
	public void setListaMes(Map<Long, String> listaMes) {
		this.listaMes = listaMes;
	}
	public List<AdmEmpresa> getListaEmpresas() {
		return listaEmpresas;
	}
	public void setListaEmpresas(List<AdmEmpresa> listaEmpresas) {
		this.listaEmpresas = listaEmpresas;
	}
	public String getMesDesde() {
		return mesDesde;
	}
	public void setMesDesde(String mesDesde) {
		this.mesDesde = mesDesde;
	}
	public String getAnioDesde() {
		return anioDesde;
	}
	public void setAnioDesde(String anioDesde) {
		this.anioDesde = anioDesde;
	}
	public String getMesHasta() {
		return mesHasta;
	}
	public void setMesHasta(String mesHasta) {
		this.mesHasta = mesHasta;
	}
	public String getAnioHasta() {
		return anioHasta;
	}
	public void setAnioHasta(String anioHasta) {
		this.anioHasta = anioHasta;
	}
	public String getEtapaBusq() {
		return etapaBusq;
	}
	public void setEtapaBusq(String etapaBusq) {
		this.etapaBusq = etapaBusq;
	}
	public boolean isAdmin() {
		return admin;
	}
	public void setAdmin(boolean admin) {
		this.admin = admin;
	}
	public String getCodEmpresaBusq() {
		return codEmpresaBusq;
	}
	public void setCodEmpresaBusq(String codEmpresaBusq) {
		this.codEmpresaBusq = codEmpresaBusq;
	}
	public List<FisePeriodoEnvio> getListaPeriodoEnvio() {
		return listaPeriodoEnvio;
	}
	public void setListaPeriodoEnvio(List<FisePeriodoEnvio> listaPeriodoEnvio) {
		this.listaPeriodoEnvio = listaPeriodoEnvio;
	}
	public String getPeriodoEnvio() {
		return periodoEnvio;
	}
	public void setPeriodoEnvio(String periodoEnvio) {
		this.periodoEnvio = periodoEnvio;
	}
	public String getAnioInicioVig() {
		return anioInicioVig;
	}
	public void setAnioInicioVig(String anioInicioVig) {
		this.anioInicioVig = anioInicioVig;
	}
	public String getAnioFinVig() {
		return anioFinVig;
	}
	public void setAnioFinVig(String anioFinVig) {
		this.anioFinVig = anioFinVig;
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
	public String getNumTotal() {
		return numTotal;
	}
	public void setNumTotal(String numTotal) {
		this.numTotal = numTotal;
	}
	public String getCostTotalRCoord() {
		return costTotalRCoord;
	}
	public void setCostTotalRCoord(String costTotalRCoord) {
		this.costTotalRCoord = costTotalRCoord;
	}
	public String getCostTotalRSupe() {
		return costTotalRSupe;
	}
	public void setCostTotalRSupe(String costTotalRSupe) {
		this.costTotalRSupe = costTotalRSupe;
	}
	public String getCostTotalRGest() {
		return costTotalRGest;
	}
	public void setCostTotalRGest(String costTotalRGest) {
		this.costTotalRGest = costTotalRGest;
	}
	public String getCostTotalRAsist() {
		return costTotalRAsist;
	}
	public void setCostTotalRAsist(String costTotalRAsist) {
		this.costTotalRAsist = costTotalRAsist;
	}
	public String getCostTotalPCoord() {
		return costTotalPCoord;
	}
	public void setCostTotalPCoord(String costTotalPCoord) {
		this.costTotalPCoord = costTotalPCoord;
	}
	public String getCostTotalPSupe() {
		return costTotalPSupe;
	}
	public void setCostTotalPSupe(String costTotalPSupe) {
		this.costTotalPSupe = costTotalPSupe;
	}
	public String getCostTotalPGest() {
		return costTotalPGest;
	}
	public void setCostTotalPGest(String costTotalPGest) {
		this.costTotalPGest = costTotalPGest;
	}
	public String getCostTotalPAsist() {
		return costTotalPAsist;
	}
	public void setCostTotalPAsist(String costTotalPAsist) {
		this.costTotalPAsist = costTotalPAsist;
	}
	public String getCostTotalLCoord() {
		return costTotalLCoord;
	}
	public void setCostTotalLCoord(String costTotalLCoord) {
		this.costTotalLCoord = costTotalLCoord;
	}
	public String getCostTotalLSupe() {
		return costTotalLSupe;
	}
	public void setCostTotalLSupe(String costTotalLSupe) {
		this.costTotalLSupe = costTotalLSupe;
	}
	public String getCostTotalLGest() {
		return costTotalLGest;
	}
	public void setCostTotalLGest(String costTotalLGest) {
		this.costTotalLGest = costTotalLGest;
	}
	public String getCostTotalLAsist() {
		return costTotalLAsist;
	}
	public void setCostTotalLAsist(String costTotalLAsist) {
		this.costTotalLAsist = costTotalLAsist;
	}
	public String getCanTotalCoord() {
		return canTotalCoord;
	}
	public void setCanTotalCoord(String canTotalCoord) {
		this.canTotalCoord = canTotalCoord;
	}
	public String getCostTotalCoord() {
		return costTotalCoord;
	}
	public void setCostTotalCoord(String costTotalCoord) {
		this.costTotalCoord = costTotalCoord;
	}
	public String getCanTotalSupe() {
		return canTotalSupe;
	}
	public void setCanTotalSupe(String canTotalSupe) {
		this.canTotalSupe = canTotalSupe;
	}
	public String getCostTotalSupe() {
		return costTotalSupe;
	}
	public void setCostTotalSupe(String costTotalSupe) {
		this.costTotalSupe = costTotalSupe;
	}
	public String getCanTotalGest() {
		return canTotalGest;
	}
	public void setCanTotalGest(String canTotalGest) {
		this.canTotalGest = canTotalGest;
	}
	public String getCostTotalGest() {
		return costTotalGest;
	}
	public void setCostTotalGest(String costTotalGest) {
		this.costTotalGest = costTotalGest;
	}
	public String getCanTotalAsist() {
		return canTotalAsist;
	}
	public void setCanTotalAsist(String canTotalAsist) {
		this.canTotalAsist = canTotalAsist;
	}
	public String getCostTotalAsist() {
		return costTotalAsist;
	}
	public void setCostTotalAsist(String costTotalAsist) {
		this.costTotalAsist = costTotalAsist;
	}
	public String getCanDRGP() {
		return canDRGP;
	}
	public void setCanDRGP(String canDRGP) {
		this.canDRGP = canDRGP;
	}
	public String getCostDRGP() {
		return costDRGP;
	}
	public void setCostDRGP(String costDRGP) {
		this.costDRGP = costDRGP;
	}
	public String getCanIRGP() {
		return canIRGP;
	}
	public void setCanIRGP(String canIRGP) {
		this.canIRGP = canIRGP;
	}
	public String getCostIRGP() {
		return costIRGP;
	}
	public void setCostIRGP(String costIRGP) {
		this.costIRGP = costIRGP;
	}
	public String getCostTotalRGP() {
		return costTotalRGP;
	}
	public void setCostTotalRGP(String costTotalRGP) {
		this.costTotalRGP = costTotalRGP;
	}
	public String getCanDPGP() {
		return canDPGP;
	}
	public void setCanDPGP(String canDPGP) {
		this.canDPGP = canDPGP;
	}
	public String getCostDPGP() {
		return costDPGP;
	}
	public void setCostDPGP(String costDPGP) {
		this.costDPGP = costDPGP;
	}
	public String getCanIPGP() {
		return canIPGP;
	}
	public void setCanIPGP(String canIPGP) {
		this.canIPGP = canIPGP;
	}
	public String getCostIPGP() {
		return costIPGP;
	}
	public void setCostIPGP(String costIPGP) {
		this.costIPGP = costIPGP;
	}
	public String getCostTotalPGP() {
		return costTotalPGP;
	}
	public void setCostTotalPGP(String costTotalPGP) {
		this.costTotalPGP = costTotalPGP;
	}
	public String getCanDLGP() {
		return canDLGP;
	}
	public void setCanDLGP(String canDLGP) {
		this.canDLGP = canDLGP;
	}
	public String getCostDLGP() {
		return costDLGP;
	}
	public void setCostDLGP(String costDLGP) {
		this.costDLGP = costDLGP;
	}
	public String getCanILGP() {
		return canILGP;
	}
	public void setCanILGP(String canILGP) {
		this.canILGP = canILGP;
	}
	public String getCostILGP() {
		return costILGP;
	}
	public void setCostILGP(String costILGP) {
		this.costILGP = costILGP;
	}
	public String getCostTotalLGP() {
		return costTotalLGP;
	}
	public void setCostTotalLGP(String costTotalLGP) {
		this.costTotalLGP = costTotalLGP;
	}
	public String getCanTotalGP() {
		return canTotalGP;
	}
	public void setCanTotalGP(String canTotalGP) {
		this.canTotalGP = canTotalGP;
	}
	public String getCostTotalGP() {
		return costTotalGP;
	}
	public void setCostTotalGP(String costTotalGP) {
		this.costTotalGP = costTotalGP;
	}
	public String getCostTotalPromedio() {
		return costTotalPromedio;
	}
	public void setCostTotalPromedio(String costTotalPromedio) {
		this.costTotalPromedio = costTotalPromedio;
	}	
	
	
	
}
