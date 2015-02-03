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
	private String anoIniVigencia;
	private String anoFinVigencia; 
	
	private String anioInicioVigenciaHidden;
	private String anioFinVigenciaHidden;
	
	private String etapa;	
	private String nombreSede;
	private String nombreExel;
	private String nombreText;
	private String numRural="0";//numero de beneficiarios del periodo anterior rural
	private String numUrbProv="0";//numero de beneficiarios del periodo anterior urbano provincias
	private String numUrbLima="0"; //numero de beneficiarios del periodo anterior lima
	private String numTotal;//no editable
	private String costoPromRural="0.0";//costo promedio mensual utiles rural
	private String costoPromUrbProv="0.0";//costo promedio mensual utiles urbano provincias
	private String costoPromUrbLima="0.0";//costo promedio mensual utiles urbano lima
	//datos de auditoria
	private String usuario;
	private String terminal;
	
	
	private List<FisePeriodoEnvio> listaPeriodoEnvio;
	//private String codigoPeriodo;
	private String periodoEnvio;
	private String desperiodoEnvio;//para el proceso editar
	private String flagPeriodoEjecucion;
	//para costos directo e indirecto
	private String flagCosto;
	
	
	
	
	 /**datos del detalle de la cabecera*/
	private String idZonaBenef;
	private String idTipoPersonal;
	
	//***ZONA RURAL
	private String canDRCoord ="0";//cantidad costo directo rural persona coordinador
	private String costDRCoord="0.0";//costo directo rural persona coordinador
	private String canIRCoord="0";//cantidad costo indirecto rural persona coordinador
	private String costIRCoord="0.0";//costo indirecto rural persona coordinador
	private String costTotalRCoord;//no editable
	
	
	private String canDRSupe="0";//cantidad costo directo rural persona supervisor
	private String costDRSupe="0.0";//costo directo rural persona supervisor
	private String canIRSupe="0";//cantidad costo indirecto rural persona supervisor
	private String costIRSupe="0.0";//costo indirecto rural persona supervisor
	private String costTotalRSupe;//no editable
	
	private String canDRGest="0";//cantidad costo directo rural persona gestor
	private String costDRGest="0.0";//costo directo rural persona gestor
	private String canIRGest="0";//cantidad costo indirecto rural persona gestor
	private String costIRGest="0.0";//costo indirecto rural persona gestor
	private String costTotalRGest;//no editable
	
	private String canDRAsist="0";//cantidad costo directo rural persona asistente /auxiliar	
	private String costDRAsist="0.0";//costo directo rural persona asistente /auxiliar
	private String canIRAsist="0";//cantidad costo indirecto rural persona asistente /auxiliar	
	private String costIRAsist="0.0";//costo indirecto rural persona asistente /auxiliar
	private String costTotalRAsist;//no editable
	
	//**ZONA URBANA PROVINCIAS
	private String canDPCoord="0";//cantidad costo directo urbano provincias persona coordinador
	private String costDPCoord="0.0";//costo directo urbano provincias persona coordinador
	private String canIPCoord="0";//cantidad costo indirecto urbano provincias persona coordinador
	private String costIPCoord="0.0";//costo indirecto urbano lima persona coordinador	
	private String costTotalPCoord;//no editable
	
	private String canDPSupe="0";//cantidad costo directo urbano provincias persona supervisor
	private String costDPSupe="0.0";//costo directo urbano provincias persona supervisor
	private String canIPSupe="0";//cantidad costo indirecto urbano provincias persona supervisor
	private String costIPSupe="0.0";//costo indirecto urbano lima persona supervisor	
	private String costTotalPSupe;//no editable
	
	private String canDPGest="0";//cantidad costo directo urbano provincias persona gestor
	private String costDPGest="0.0";//costo directo urbano provincias persona gestor
	private String canIPGest="0";//cantidad costo indirecto urbano provincias persona gestor
	private String costIPGest="0.0";//costo indirecto urbano lima persona gestor	
	private String costTotalPGest;//no editable
	
	private String canDPAsist="0";//cantidad costo directo urbano provincias persona asistente /auxiliar
	private String costDPAsist="0.0";//costo directo urbano provincias persona asistente /auxiliar
	private String canIPAsist="0";//cantidad costo indirecto urbano provincias persona asistente /auxiliar
	private String costIPAsist="0.0";//costo indirecto urbano lima persona asistente /auxiliar
	private String costTotalPAsist;//no editable
		
	//**ZONA URBANA LIMA
	private String canDLCoord="0";//cantidad costo directo urbano lima persona coordinador
	private String costDLCoord="0.0";//costo directo urbano lima persona coordinador
	private String canILCoord="0";//cantidad costo indirecto urbano lima persona coordinador
	private String costILCoord="0.0";//costo indirecto urbano lima persona coordinador
	private String costTotalLCoord;//no editable
	
	private String canDLSupe="0";//cantidad costo directo urbano lima persona supervisor
	private String costDLSupe="0.0";//costo directo urbano lima persona supervisor
	private String canILSupe="0";//cantidad costo indirecto urbano lima persona supervisor
	private String costILSupe="0.0";//costo indirecto urbano lima persona supervisor
	private String costTotalLSupe;//no editable
	
	private String canDLGest="0";//cantidad costo directo urbano lima persona gestor
	private String costDLGest="0.0";//costo directo urbano lima persona gestor
	private String canILGest="0";//cantidad costo indirecto urbano lima persona gestor
	private String costILGest="0.0";//costo indirecto urbano lima persona gestor
	private String costTotalLGest;//no editable
	
	private String canDLAsist="0";//cantidad costo directo urbano lima persona asistente /auxiliar
	private String costDLAsist="0.0";//costo directo urbano lima persona asistente /auxiliar
	private String canILAsist="0";//cantidad costo indirecto urbano lima persona asistente /auxiliar
	private String costILAsist="0.0";//costo indirecto urbano lima persona asistente /auxiliar
	private String costTotalLAsist;//no editable
	
	//Panel totales
	private String canTotalCoord;//no editable
	private String costTotalCoord;//no editable
	
	private String canTotalSupe;//no editable
	private String costTotalSupe;//no editable
	
	private String canTotalGest;//no editable
	private String costTotalGest;//no editable
	
	private String canTotalAsist;//no editable	
	private String costTotalAsist;//no editable
	
	//panel detalle
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
	
	/***PARA CARGA DE EXEL Y TEXT***/
	private String mensajeInfo;
	private String mensajeError;
	//
	private String flag;//flag para controlar mostrar el formulario de ingreso cuando hay un error en carga de formulario excel o texto
	
	//private String anioInicioVig;
	//private String anioFinVig;
	
	private String  grupoInformacion;//descripcion del grupo de informacion
	private String  estado;//estado del formato
	
	private String  codEdelnor;//cod empresa edelnor constante
	private String  codLuzSur;//cod empresa luz del sur constante
	
	private String idGrupoInfo;
	
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
	public String getCodEmpresaBusq() {
		return codEmpresaBusq;
	}
	public void setCodEmpresaBusq(String codEmpresaBusq) {
		this.codEmpresaBusq = codEmpresaBusq;
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
	public String getCodEmpresa() {
		return codEmpresa;
	}
	public void setCodEmpresa(String codEmpresa) {
		this.codEmpresa = codEmpresa;
	}
	public String getAnioPres() {
		return anioPres;
	}
	public void setAnioPres(String anioPres) {
		this.anioPres = anioPres;
	}
	public String getMesPres() {
		return mesPres;
	}
	public void setMesPres(String mesPres) {
		this.mesPres = mesPres;
	}
	public String getEtapa() {
		return etapa;
	}
	public void setEtapa(String etapa) {
		this.etapa = etapa;
	}
	public String getNombreSede() {
		return nombreSede;
	}
	public void setNombreSede(String nombreSede) {
		this.nombreSede = nombreSede;
	}
	public String getNombreExel() {
		return nombreExel;
	}
	public void setNombreExel(String nombreExel) {
		this.nombreExel = nombreExel;
	}
	public String getNombreText() {
		return nombreText;
	}
	public void setNombreText(String nombreText) {
		this.nombreText = nombreText;
	}
	public String getNumRural() {
		return numRural;
	}
	public void setNumRural(String numRural) {
		this.numRural = numRural;
	}
	public String getNumUrbProv() {
		return numUrbProv;
	}
	public void setNumUrbProv(String numUrbProv) {
		this.numUrbProv = numUrbProv;
	}
	public String getNumUrbLima() {
		return numUrbLima;
	}
	public void setNumUrbLima(String numUrbLima) {
		this.numUrbLima = numUrbLima;
	}
	public String getNumTotal() {
		return numTotal;
	}
	public void setNumTotal(String numTotal) {
		this.numTotal = numTotal;
	}
	public String getCostoPromRural() {
		return costoPromRural;
	}
	public void setCostoPromRural(String costoPromRural) {
		this.costoPromRural = costoPromRural;
	}
	public String getCostoPromUrbProv() {
		return costoPromUrbProv;
	}
	public void setCostoPromUrbProv(String costoPromUrbProv) {
		this.costoPromUrbProv = costoPromUrbProv;
	}
	public String getCostoPromUrbLima() {
		return costoPromUrbLima;
	}
	public void setCostoPromUrbLima(String costoPromUrbLima) {
		this.costoPromUrbLima = costoPromUrbLima;
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
	public String getDesperiodoEnvio() {
		return desperiodoEnvio;
	}
	public void setDesperiodoEnvio(String desperiodoEnvio) {
		this.desperiodoEnvio = desperiodoEnvio;
	}
	public String getFlagPeriodoEjecucion() {
		return flagPeriodoEjecucion;
	}
	public void setFlagPeriodoEjecucion(String flagPeriodoEjecucion) {
		this.flagPeriodoEjecucion = flagPeriodoEjecucion;
	}
	public String getIdZonaBenef() {
		return idZonaBenef;
	}
	public void setIdZonaBenef(String idZonaBenef) {
		this.idZonaBenef = idZonaBenef;
	}
	public String getIdTipoPersonal() {
		return idTipoPersonal;
	}
	public void setIdTipoPersonal(String idTipoPersonal) {
		this.idTipoPersonal = idTipoPersonal;
	}
	public String getCanDRCoord() {
		return canDRCoord;
	}
	public void setCanDRCoord(String canDRCoord) {
		this.canDRCoord = canDRCoord;
	}
	public String getCostDRCoord() {
		return costDRCoord;
	}
	public void setCostDRCoord(String costDRCoord) {
		this.costDRCoord = costDRCoord;
	}
	public String getCanIRCoord() {
		return canIRCoord;
	}
	public void setCanIRCoord(String canIRCoord) {
		this.canIRCoord = canIRCoord;
	}
	public String getCostIRCoord() {
		return costIRCoord;
	}
	public void setCostIRCoord(String costIRCoord) {
		this.costIRCoord = costIRCoord;
	}
	public String getCostTotalRCoord() {
		return costTotalRCoord;
	}
	public void setCostTotalRCoord(String costTotalRCoord) {
		this.costTotalRCoord = costTotalRCoord;
	}
	public String getCanDRSupe() {
		return canDRSupe;
	}
	public void setCanDRSupe(String canDRSupe) {
		this.canDRSupe = canDRSupe;
	}
	public String getCostDRSupe() {
		return costDRSupe;
	}
	public void setCostDRSupe(String costDRSupe) {
		this.costDRSupe = costDRSupe;
	}
	public String getCanIRSupe() {
		return canIRSupe;
	}
	public void setCanIRSupe(String canIRSupe) {
		this.canIRSupe = canIRSupe;
	}
	public String getCostIRSupe() {
		return costIRSupe;
	}
	public void setCostIRSupe(String costIRSupe) {
		this.costIRSupe = costIRSupe;
	}
	public String getCostTotalRSupe() {
		return costTotalRSupe;
	}
	public void setCostTotalRSupe(String costTotalRSupe) {
		this.costTotalRSupe = costTotalRSupe;
	}
	public String getCanDRGest() {
		return canDRGest;
	}
	public void setCanDRGest(String canDRGest) {
		this.canDRGest = canDRGest;
	}
	public String getCostDRGest() {
		return costDRGest;
	}
	public void setCostDRGest(String costDRGest) {
		this.costDRGest = costDRGest;
	}
	public String getCanIRGest() {
		return canIRGest;
	}
	public void setCanIRGest(String canIRGest) {
		this.canIRGest = canIRGest;
	}
	public String getCostIRGest() {
		return costIRGest;
	}
	public void setCostIRGest(String costIRGest) {
		this.costIRGest = costIRGest;
	}
	public String getCostTotalRGest() {
		return costTotalRGest;
	}
	public void setCostTotalRGest(String costTotalRGest) {
		this.costTotalRGest = costTotalRGest;
	}
	public String getCanDRAsist() {
		return canDRAsist;
	}
	public void setCanDRAsist(String canDRAsist) {
		this.canDRAsist = canDRAsist;
	}
	public String getCostDRAsist() {
		return costDRAsist;
	}
	public void setCostDRAsist(String costDRAsist) {
		this.costDRAsist = costDRAsist;
	}
	public String getCanIRAsist() {
		return canIRAsist;
	}
	public void setCanIRAsist(String canIRAsist) {
		this.canIRAsist = canIRAsist;
	}
	public String getCostIRAsist() {
		return costIRAsist;
	}
	public void setCostIRAsist(String costIRAsist) {
		this.costIRAsist = costIRAsist;
	}
	public String getCostTotalRAsist() {
		return costTotalRAsist;
	}
	public void setCostTotalRAsist(String costTotalRAsist) {
		this.costTotalRAsist = costTotalRAsist;
	}
	public String getCanDPCoord() {
		return canDPCoord;
	}
	public void setCanDPCoord(String canDPCoord) {
		this.canDPCoord = canDPCoord;
	}
	public String getCostDPCoord() {
		return costDPCoord;
	}
	public void setCostDPCoord(String costDPCoord) {
		this.costDPCoord = costDPCoord;
	}
	public String getCanIPCoord() {
		return canIPCoord;
	}
	public void setCanIPCoord(String canIPCoord) {
		this.canIPCoord = canIPCoord;
	}
	public String getCostIPCoord() {
		return costIPCoord;
	}
	public void setCostIPCoord(String costIPCoord) {
		this.costIPCoord = costIPCoord;
	}
	public String getCostTotalPCoord() {
		return costTotalPCoord;
	}
	public void setCostTotalPCoord(String costTotalPCoord) {
		this.costTotalPCoord = costTotalPCoord;
	}
	public String getCanDPSupe() {
		return canDPSupe;
	}
	public void setCanDPSupe(String canDPSupe) {
		this.canDPSupe = canDPSupe;
	}
	public String getCostDPSupe() {
		return costDPSupe;
	}
	public void setCostDPSupe(String costDPSupe) {
		this.costDPSupe = costDPSupe;
	}
	public String getCanIPSupe() {
		return canIPSupe;
	}
	public void setCanIPSupe(String canIPSupe) {
		this.canIPSupe = canIPSupe;
	}
	public String getCostIPSupe() {
		return costIPSupe;
	}
	public void setCostIPSupe(String costIPSupe) {
		this.costIPSupe = costIPSupe;
	}
	public String getCostTotalPSupe() {
		return costTotalPSupe;
	}
	public void setCostTotalPSupe(String costTotalPSupe) {
		this.costTotalPSupe = costTotalPSupe;
	}
	public String getCanDPGest() {
		return canDPGest;
	}
	public void setCanDPGest(String canDPGest) {
		this.canDPGest = canDPGest;
	}
	public String getCostDPGest() {
		return costDPGest;
	}
	public void setCostDPGest(String costDPGest) {
		this.costDPGest = costDPGest;
	}
	public String getCanIPGest() {
		return canIPGest;
	}
	public void setCanIPGest(String canIPGest) {
		this.canIPGest = canIPGest;
	}
	public String getCostIPGest() {
		return costIPGest;
	}
	public void setCostIPGest(String costIPGest) {
		this.costIPGest = costIPGest;
	}
	public String getCostTotalPGest() {
		return costTotalPGest;
	}
	public void setCostTotalPGest(String costTotalPGest) {
		this.costTotalPGest = costTotalPGest;
	}
	public String getCanDPAsist() {
		return canDPAsist;
	}
	public void setCanDPAsist(String canDPAsist) {
		this.canDPAsist = canDPAsist;
	}
	public String getCostDPAsist() {
		return costDPAsist;
	}
	public void setCostDPAsist(String costDPAsist) {
		this.costDPAsist = costDPAsist;
	}
	public String getCanIPAsist() {
		return canIPAsist;
	}
	public void setCanIPAsist(String canIPAsist) {
		this.canIPAsist = canIPAsist;
	}
	public String getCostIPAsist() {
		return costIPAsist;
	}
	public void setCostIPAsist(String costIPAsist) {
		this.costIPAsist = costIPAsist;
	}
	public String getCostTotalPAsist() {
		return costTotalPAsist;
	}
	public void setCostTotalPAsist(String costTotalPAsist) {
		this.costTotalPAsist = costTotalPAsist;
	}
	public String getCanDLCoord() {
		return canDLCoord;
	}
	public void setCanDLCoord(String canDLCoord) {
		this.canDLCoord = canDLCoord;
	}
	public String getCostDLCoord() {
		return costDLCoord;
	}
	public void setCostDLCoord(String costDLCoord) {
		this.costDLCoord = costDLCoord;
	}
	public String getCanILCoord() {
		return canILCoord;
	}
	public void setCanILCoord(String canILCoord) {
		this.canILCoord = canILCoord;
	}
	public String getCostILCoord() {
		return costILCoord;
	}
	public void setCostILCoord(String costILCoord) {
		this.costILCoord = costILCoord;
	}
	public String getCostTotalLCoord() {
		return costTotalLCoord;
	}
	public void setCostTotalLCoord(String costTotalLCoord) {
		this.costTotalLCoord = costTotalLCoord;
	}
	public String getCanDLSupe() {
		return canDLSupe;
	}
	public void setCanDLSupe(String canDLSupe) {
		this.canDLSupe = canDLSupe;
	}
	public String getCostDLSupe() {
		return costDLSupe;
	}
	public void setCostDLSupe(String costDLSupe) {
		this.costDLSupe = costDLSupe;
	}
	public String getCanILSupe() {
		return canILSupe;
	}
	public void setCanILSupe(String canILSupe) {
		this.canILSupe = canILSupe;
	}
	public String getCostILSupe() {
		return costILSupe;
	}
	public void setCostILSupe(String costILSupe) {
		this.costILSupe = costILSupe;
	}
	public String getCostTotalLSupe() {
		return costTotalLSupe;
	}
	public void setCostTotalLSupe(String costTotalLSupe) {
		this.costTotalLSupe = costTotalLSupe;
	}
	public String getCanDLGest() {
		return canDLGest;
	}
	public void setCanDLGest(String canDLGest) {
		this.canDLGest = canDLGest;
	}
	public String getCostDLGest() {
		return costDLGest;
	}
	public void setCostDLGest(String costDLGest) {
		this.costDLGest = costDLGest;
	}
	public String getCanILGest() {
		return canILGest;
	}
	public void setCanILGest(String canILGest) {
		this.canILGest = canILGest;
	}
	public String getCostILGest() {
		return costILGest;
	}
	public void setCostILGest(String costILGest) {
		this.costILGest = costILGest;
	}
	public String getCostTotalLGest() {
		return costTotalLGest;
	}
	public void setCostTotalLGest(String costTotalLGest) {
		this.costTotalLGest = costTotalLGest;
	}
	public String getCanDLAsist() {
		return canDLAsist;
	}
	public void setCanDLAsist(String canDLAsist) {
		this.canDLAsist = canDLAsist;
	}
	public String getCostDLAsist() {
		return costDLAsist;
	}
	public void setCostDLAsist(String costDLAsist) {
		this.costDLAsist = costDLAsist;
	}
	public String getCanILAsist() {
		return canILAsist;
	}
	public void setCanILAsist(String canILAsist) {
		this.canILAsist = canILAsist;
	}
	public String getCostILAsist() {
		return costILAsist;
	}
	public void setCostILAsist(String costILAsist) {
		this.costILAsist = costILAsist;
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
	public String getMensajeInfo() {
		return mensajeInfo;
	}
	public void setMensajeInfo(String mensajeInfo) {
		this.mensajeInfo = mensajeInfo;
	}
	public String getMensajeError() {
		return mensajeError;
	}
	public void setMensajeError(String mensajeError) {
		this.mensajeError = mensajeError;
	}
	public String getFlag() {
		return flag;
	}
	public void setFlag(String flag) {
		this.flag = flag;
	}
	public String getAnoIniVigencia() {
		return anoIniVigencia;
	}
	public void setAnoIniVigencia(String anoIniVigencia) {
		this.anoIniVigencia = anoIniVigencia;
	}
	public String getAnoFinVigencia() {
		return anoFinVigencia;
	}
	public void setAnoFinVigencia(String anoFinVigencia) {
		this.anoFinVigencia = anoFinVigencia;
	}
	public String getGrupoInformacion() {
		return grupoInformacion;
	}
	public void setGrupoInformacion(String grupoInformacion) {
		this.grupoInformacion = grupoInformacion;
	}
	public String getEstado() {
		return estado;
	}
	public void setEstado(String estado) {
		this.estado = estado;
	}
	public String getFlagCosto() {
		return flagCosto;
	}
	public void setFlagCosto(String flagCosto) {
		this.flagCosto = flagCosto;
	}
	public String getCodEdelnor() {
		return codEdelnor;
	}
	public void setCodEdelnor(String codEdelnor) {
		this.codEdelnor = codEdelnor;
	}
	public String getCodLuzSur() {
		return codLuzSur;
	}
	public void setCodLuzSur(String codLuzSur) {
		this.codLuzSur = codLuzSur;
	}
	public String getAnioInicioVigenciaHidden() {
		return anioInicioVigenciaHidden;
	}
	public void setAnioInicioVigenciaHidden(String anioInicioVigenciaHidden) {
		this.anioInicioVigenciaHidden = anioInicioVigenciaHidden;
	}
	public String getAnioFinVigenciaHidden() {
		return anioFinVigenciaHidden;
	}
	public void setAnioFinVigenciaHidden(String anioFinVigenciaHidden) {
		this.anioFinVigenciaHidden = anioFinVigenciaHidden;
	}
	public String getIdGrupoInfo() {
		return idGrupoInfo;
	}
	public void setIdGrupoInfo(String idGrupoInfo) {
		this.idGrupoInfo = idGrupoInfo;
	}
	
	
	
	
}
