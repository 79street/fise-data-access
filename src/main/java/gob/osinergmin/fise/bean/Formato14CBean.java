package gob.osinergmin.fise.bean;


public class Formato14CBean {

	/**datos de la cabecera*/
	private String codEmpresa;
	private String anioPres;
	private String mesPres;	
	private String etapa;
	private String nombreSede;
	private String nombreExel;
	private String nombreText;
	private String numRural;//numero de beneficiarios del periodo anterior rural
	private String numUrbProv;//numero de beneficiarios del periodo anterior urbano provincias
	private String numUrbLima; //numero de beneficiarios del periodo anterior lima
	private String costoPromRural;//costo promedio mensual utiles rural
	private String costoPromUrbProv;//costo promedio mensual utiles urbano provincias
	private String costoPromUrbLima;//costo promedio mensual utiles urbano lima	
	
	 /**datos del detalle de la cabecera*/
	private String idZonaBenef;
	private String idTipoPersonal;
	
	//***ZONA RURAL
	private String canRCoord;//cantidad rural persona coordinador
	private String canRSupe;//cantidad rural persona supervisor
	private String canRGest;//cantidad rural persona gestor
	private String canRAsist;//cantidad rural persona asistente /auxiliar
	
	private String costDRCoord;//costo directo rural persona coordinador
	private String costDRSupe;//costo directo rural persona supervisor
	private String costDRGest;//costo directo rural persona gestor
	private String costDRAsist;//costo directo rural persona asistente /auxiliar
	
	private String costIRCoord;//costo indirecto rural persona coordinador
	private String costIRSupe;//costo indirecto rural persona supervisor
	private String costIRGest;//costo indirecto rural persona gestor
	private String costIRAsist;//costo indirecto rural persona asistente /auxiliar
	
	//**ZONA URBANA PROVINCIAS
	private String canPCoord;//cantidad urbano provincias persona coordinador
	private String canPSupe;//cantidad urbano provincias persona supervisor
	private String canPGest;//cantidad urbano provincias persona gestor
	private String canPAsist;//cantidad urbano provincias persona asistente /auxiliar
	
	private String costDPCoord;//costo directo urbano provincias persona coordinador
	private String costDPSupe;//costo directo urbano provincias persona supervisor
	private String costDPGest;//costo directo urbano provincias persona gestor
	private String costDPAsist;//costo directo urbano provincias persona asistente /auxiliar
	
	private String costIPCoord;//costo indirecto urbano lima persona coordinador
	private String costIPSupe;//costo indirecto urbano lima persona supervisor
	private String costIPGest;//costo indirecto urbano lima persona gestor
	private String costIPAsist;//costo indirecto urbano lima persona asistente /auxiliar
	
	//**ZONA URBANA LIMA
	private String canLCoord;//cantidad urbano lima persona coordinador
	private String canLSupe;//cantidad urbano lima persona supervisor
	private String canLGest;//cantidad urbano lima persona gestor
	private String canLAsist;//cantidad urbano lima persona asistente /auxiliar

	private String costDLCoord;//costo directo urbano lima persona coordinador
	private String costDLSupe;//costo directo urbano lima persona supervisor
	private String costDLGest;//costo directo urbano lima persona gestor
	private String costDLAsist;//costo directo urbano lima persona asistente /auxiliar

	private String costILCoord;//costo indirecto urbano lima persona coordinador
	private String costILSupe;//costo indirecto urbano lima persona supervisor
	private String costILGest;//costo indirecto urbano lima persona gestor
	private String costILAsist;//costo indirecto urbano lima persona asistente /auxiliar
	
	
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
	 * @return the canRCoord
	 */
	public String getCanRCoord() {
		return canRCoord;
	}

	/**
	 * @param canRCoord the canRCoord to set
	 */
	public void setCanRCoord(String canRCoord) {
		this.canRCoord = canRCoord;
	}

	/**
	 * @return the canRSupe
	 */
	public String getCanRSupe() {
		return canRSupe;
	}

	/**
	 * @param canRSupe the canRSupe to set
	 */
	public void setCanRSupe(String canRSupe) {
		this.canRSupe = canRSupe;
	}

	/**
	 * @return the canRGest
	 */
	public String getCanRGest() {
		return canRGest;
	}

	/**
	 * @param canRGest the canRGest to set
	 */
	public void setCanRGest(String canRGest) {
		this.canRGest = canRGest;
	}

	/**
	 * @return the canRAsist
	 */
	public String getCanRAsist() {
		return canRAsist;
	}

	/**
	 * @param canRAsist the canRAsist to set
	 */
	public void setCanRAsist(String canRAsist) {
		this.canRAsist = canRAsist;
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
	 * @return the canPCoord
	 */
	public String getCanPCoord() {
		return canPCoord;
	}

	/**
	 * @param canPCoord the canPCoord to set
	 */
	public void setCanPCoord(String canPCoord) {
		this.canPCoord = canPCoord;
	}

	/**
	 * @return the canPSupe
	 */
	public String getCanPSupe() {
		return canPSupe;
	}

	/**
	 * @param canPSupe the canPSupe to set
	 */
	public void setCanPSupe(String canPSupe) {
		this.canPSupe = canPSupe;
	}

	/**
	 * @return the canPGest
	 */
	public String getCanPGest() {
		return canPGest;
	}

	/**
	 * @param canPGest the canPGest to set
	 */
	public void setCanPGest(String canPGest) {
		this.canPGest = canPGest;
	}

	/**
	 * @return the canPAsist
	 */
	public String getCanPAsist() {
		return canPAsist;
	}

	/**
	 * @param canPAsist the canPAsist to set
	 */
	public void setCanPAsist(String canPAsist) {
		this.canPAsist = canPAsist;
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
	 * @return the canLCoord
	 */
	public String getCanLCoord() {
		return canLCoord;
	}

	/**
	 * @param canLCoord the canLCoord to set
	 */
	public void setCanLCoord(String canLCoord) {
		this.canLCoord = canLCoord;
	}

	/**
	 * @return the canLSupe
	 */
	public String getCanLSupe() {
		return canLSupe;
	}

	/**
	 * @param canLSupe the canLSupe to set
	 */
	public void setCanLSupe(String canLSupe) {
		this.canLSupe = canLSupe;
	}

	/**
	 * @return the canLGest
	 */
	public String getCanLGest() {
		return canLGest;
	}

	/**
	 * @param canLGest the canLGest to set
	 */
	public void setCanLGest(String canLGest) {
		this.canLGest = canLGest;
	}

	/**
	 * @return the canLAsist
	 */
	public String getCanLAsist() {
		return canLAsist;
	}

	/**
	 * @param canLAsist the canLAsist to set
	 */
	public void setCanLAsist(String canLAsist) {
		this.canLAsist = canLAsist;
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
	
	
	
	
	
}
