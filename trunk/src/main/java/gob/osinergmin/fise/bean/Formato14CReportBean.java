package gob.osinergmin.fise.bean;

import java.io.Serializable;
import java.math.BigDecimal;

public class Formato14CReportBean implements Serializable {
	
	private static final long serialVersionUID = 1L;
	
	private String codigoEmpresa;
	private long anioPresent = 0;
	private long mesPresent = 0;
	private long anioInicioVigencia = 0;
	private long anioFinVigencia = 0;
	
	private long idZonaBnfcio = 0;
	
	//-------------- GENERAL--------------------
		private String nombreSede="IQUITOS";
					
		private long nroBenefCantT = 0;
	
		//------------------------- RURAL--------------------------	
	
		private long nroBenefCantR = 0;
		
		//------------Coordinador------------------
		private long costoCoordCantCDR = 0;
		private BigDecimal costoCoordCDR = new BigDecimal(0);
		private long costoCoordCantCIR = 0;
		private BigDecimal costoCoordCIR = new BigDecimal(0);
		private BigDecimal costoCoordCostoTotR = new BigDecimal(0);
	
		//------------Supervisor------------------
		private long costoSuperCantCDR = 0;
		private BigDecimal costoSuperCDR = new BigDecimal(0);
		private long costoSuperCantCIR = 0;
		private BigDecimal costoSuperCIR = new BigDecimal(0);
		private BigDecimal costoSuperCostoTotR = new BigDecimal(0);

		//------------Gestor------------------
		private long costoGestCantCDR = 0;
		private BigDecimal costoGestCDR = new BigDecimal(0);
		private long costoGestCantCIR = 0;
		private BigDecimal costoGestCIR = new BigDecimal(0);
		private BigDecimal costoGestCostoTotR = new BigDecimal(0);
	
		//------------Asistente / Auxiliar------------------
		private long costoAsistCantCDR = 0;
		private BigDecimal costoAsistCDR = new BigDecimal(0);
		private long costoAsistCantCIR = 0;
		private BigDecimal costoAsistCIR = new BigDecimal(0);
		private BigDecimal costoAsistCostoTotR = new BigDecimal(0);
	
		//------------Suma total Gestion Personal------------------
		private long costoTotalGestCantCDR = 0;
		private BigDecimal costoTotalGestCDR = new BigDecimal(0);
		private long costoTotalGestCantCIR = 0;
		private BigDecimal costoTotGestCIR = new BigDecimal(0);
		private BigDecimal costoTotalGestoTotR = new BigDecimal(0);
	
		//------------Costo Promedio Mensual------------------
		private BigDecimal costoPromMensCDR = new BigDecimal(0);
		private BigDecimal costoPromMensCTR = new BigDecimal(0);
	
	
		//------------------------- PROVINCIA--------------------------	
		
		private long nroBenefCantP = 0;
		
		//------------Coordinador------------------
		private long costoCoordCantCDP = 0;
		private BigDecimal costoCoordCDP = new BigDecimal(0);
		private long costoCoordCantCIP = 0;
		private BigDecimal costoCoordCIP = new BigDecimal(0);
		private BigDecimal costoCoordCstoTotP = new BigDecimal(0);
	
		//------------Supervisor------------------
		private long costoSuperCantCDP = 0;
		private BigDecimal costoSuperCDP = new BigDecimal(0);
		private long costoSuperCantCIP = 0;
		private BigDecimal costoSuperCIP = new BigDecimal(0);
		private BigDecimal costoSuperCstoTotP = new BigDecimal(0);

		//------------Gestor------------------
		private long costoGestCantCDP = 0;
		private BigDecimal costoGestCDP = new BigDecimal(0);
		private long costoGestCantCIP = 0;
		private BigDecimal costoGestCIP = new BigDecimal(0);
		private BigDecimal costoGestCstoTotP = new BigDecimal(0);
	
		//------------Asistente / Auxiliar------------------
		private long costoAsistCantCDP = 0;
		private BigDecimal costoAsistCDP = new BigDecimal(0);
		private long costoAsistCantCIP = 0;
		private BigDecimal costoAsistCIP = new BigDecimal(0);
		private BigDecimal costoAsistCstoTotP = new BigDecimal(0);
	
		//------------Suma total Gestion Personal------------------
		private long costoTotGestCantCDP = 0;
		private BigDecimal costoTotGestCDP = new BigDecimal(0);
		private long costoTotGestCantCIP = 0;
		private BigDecimal costoTotGestCIP = new BigDecimal(0);
		private BigDecimal costoTotGestoTotP = new BigDecimal(0);
	
		//------------Costo Promedio Mensual------------------
		private BigDecimal costoPromMensCDP = new BigDecimal(0);
		private BigDecimal costoPromMensCTP = new BigDecimal(0);

		
		//------------------------- LIMA--------------------------	
		
		private long nroBenefCantL = 0;
		
		//------------Coordinador------------------
		private long costoCoordCantCDL = 0;
		private BigDecimal costoCoordCDL = new BigDecimal(0);
		private long costoCoordCantCIL = 0;
		private BigDecimal costoCoordCIL = new BigDecimal(0);
		private BigDecimal costoCoordCstoTotL = new BigDecimal(0);
	
		//------------Supervisor------------------
		private long costoSuperCantCDL = 0;
		private BigDecimal costoSuperCDL = new BigDecimal(0);
		private long costoSuperCantCIL = 0;
		private BigDecimal costoSuperCIL = new BigDecimal(0);
		private BigDecimal costoSuperCstoTotL = new BigDecimal(0);

		//------------Gestor------------------
		private long costoGestCantCDL = 0;
		private BigDecimal costoGestCDL = new BigDecimal(0);
		private long costoGestCantCIL = 0;
		private BigDecimal costoGestorCIL = new BigDecimal(0);
		private BigDecimal costoGestCstoTotL = new BigDecimal(0);
	
		//------------Asistente / Auxiliar------------------
		private long costoAsistCantCDL = 0;
		private BigDecimal costoAsistCDL = new BigDecimal(0);
		private long costoAsistCantCIL = 0;
		private BigDecimal costoAsistCIL = new BigDecimal(0);
		private BigDecimal costoAsistCstoTotL = new BigDecimal(0);
	
		//------------Suma total Gestion Personal------------------
		private long costoTotGestCantCDL = 0;
		private BigDecimal costoTotGestCDL = new BigDecimal(0);
		private long costoTotGestCantCIL = 0;
		private BigDecimal costoTotGestCIL = new BigDecimal(0);
		private BigDecimal costoTotGestoTotL = new BigDecimal(0);
	
		//------------Costo Promedio Mensual------------------
		private BigDecimal costoPromMensCDL = new BigDecimal(0);
		private BigDecimal costoPromMensCTL = new BigDecimal(0);
		
	
		//----------------------------------------------------------------------------------------
		//------------Suma totales 3 zonas cantidad------------------
		private long SumaCantNroBenefT = 0;
		private long SumaCantCoordT = 0;
		private long SumaCantSupervT = 0;
		private long SumaCantGestorT = 0;
		private long SumaCantAsistT = 0;
		private long SumaCantCstoTotalT = 0;
		
		
				//------------Suma totales 3costo------------------
		private BigDecimal SumaCostCoordT = new BigDecimal(0);
		private BigDecimal SumaCostSupervT = new BigDecimal(0);
		private BigDecimal SumaCostGestorT = new BigDecimal(0);
		private BigDecimal SumaCostAsistT = new BigDecimal(0);
		private BigDecimal SumaCostCstoTotalT = new BigDecimal(0);
		private BigDecimal SumaCostPromiTotalT = new BigDecimal(0);
		
		
		private String usuario;
		private String terminal;
		private String nombreArchivo;
		private String tipoArchivo;
		
		private String descEmpresa;
		private String descMesPresentacion;
		public String getCodigoEmpresa() {
			return codigoEmpresa;
		}
		public void setCodigoEmpresa(String codigoEmpresa) {
			this.codigoEmpresa = codigoEmpresa;
		}
		public long getAnioPresent() {
			return anioPresent;
		}
		public void setAnioPresent(long anioPresent) {
			this.anioPresent = anioPresent;
		}
		public long getMesPresent() {
			return mesPresent;
		}
		public void setMesPresent(long mesPresent) {
			this.mesPresent = mesPresent;
		}
		public long getAnioInicioVigencia() {
			return anioInicioVigencia;
		}
		public void setAnioInicioVigencia(long anioInicioVigencia) {
			this.anioInicioVigencia = anioInicioVigencia;
		}
		public long getAnioFinVigencia() {
			return anioFinVigencia;
		}
		public void setAnioFinVigencia(long anioFinVigencia) {
			this.anioFinVigencia = anioFinVigencia;
		}
		public long getIdZonaBnfcio() {
			return idZonaBnfcio;
		}
		public void setIdZonaBnfcio(long idZonaBnfcio) {
			this.idZonaBnfcio = idZonaBnfcio;
		}
		public String getNombreSede() {
			return nombreSede;
		}
		public void setNombreSede(String nombreSede) {
			this.nombreSede = nombreSede;
		}
		public long getNroBenefCantT() {
			return nroBenefCantT;
		}
		public void setNroBenefCantT(long nroBenefCantT) {
			this.nroBenefCantT = nroBenefCantT;
		}
		public long getNroBenefCantR() {
			return nroBenefCantR;
		}
		public void setNroBenefCantR(long nroBenefCantR) {
			this.nroBenefCantR = nroBenefCantR;
		}
		public long getCostoCoordCantCDR() {
			return costoCoordCantCDR;
		}
		public void setCostoCoordCantCDR(long costoCoordCantCDR) {
			this.costoCoordCantCDR = costoCoordCantCDR;
		}
		public BigDecimal getCostoCoordCDR() {
			return costoCoordCDR;
		}
		public void setCostoCoordCDR(BigDecimal costoCoordCDR) {
			this.costoCoordCDR = costoCoordCDR;
		}
		public long getCostoCoordCantCIR() {
			return costoCoordCantCIR;
		}
		public void setCostoCoordCantCIR(long costoCoordCantCIR) {
			this.costoCoordCantCIR = costoCoordCantCIR;
		}
		public BigDecimal getCostoCoordCIR() {
			return costoCoordCIR;
		}
		public void setCostoCoordCIR(BigDecimal costoCoordCIR) {
			this.costoCoordCIR = costoCoordCIR;
		}
		public BigDecimal getCostoCoordCostoTotR() {
			return costoCoordCostoTotR;
		}
		public void setCostoCoordCostoTotR(BigDecimal costoCoordCostoTotR) {
			this.costoCoordCostoTotR = costoCoordCostoTotR;
		}
		public long getCostoSuperCantCDR() {
			return costoSuperCantCDR;
		}
		public void setCostoSuperCantCDR(long costoSuperCantCDR) {
			this.costoSuperCantCDR = costoSuperCantCDR;
		}
		public BigDecimal getCostoSuperCDR() {
			return costoSuperCDR;
		}
		public void setCostoSuperCDR(BigDecimal costoSuperCDR) {
			this.costoSuperCDR = costoSuperCDR;
		}
		public long getCostoSuperCantCIR() {
			return costoSuperCantCIR;
		}
		public void setCostoSuperCantCIR(long costoSuperCantCIR) {
			this.costoSuperCantCIR = costoSuperCantCIR;
		}
		public BigDecimal getCostoSuperCIR() {
			return costoSuperCIR;
		}
		public void setCostoSuperCIR(BigDecimal costoSuperCIR) {
			this.costoSuperCIR = costoSuperCIR;
		}
		public BigDecimal getCostoSuperCostoTotR() {
			return costoSuperCostoTotR;
		}
		public void setCostoSuperCostoTotR(BigDecimal costoSuperCostoTotR) {
			this.costoSuperCostoTotR = costoSuperCostoTotR;
		}
		public long getCostoGestCantCDR() {
			return costoGestCantCDR;
		}
		public void setCostoGestCantCDR(long costoGestCantCDR) {
			this.costoGestCantCDR = costoGestCantCDR;
		}
		public BigDecimal getCostoGestCDR() {
			return costoGestCDR;
		}
		public void setCostoGestCDR(BigDecimal costoGestCDR) {
			this.costoGestCDR = costoGestCDR;
		}
		public long getCostoGestCantCIR() {
			return costoGestCantCIR;
		}
		public void setCostoGestCantCIR(long costoGestCantCIR) {
			this.costoGestCantCIR = costoGestCantCIR;
		}
		public BigDecimal getCostoGestCIR() {
			return costoGestCIR;
		}
		public void setCostoGestCIR(BigDecimal costoGestCIR) {
			this.costoGestCIR = costoGestCIR;
		}
		public BigDecimal getCostoGestCostoTotR() {
			return costoGestCostoTotR;
		}
		public void setCostoGestCostoTotR(BigDecimal costoGestCostoTotR) {
			this.costoGestCostoTotR = costoGestCostoTotR;
		}
		public long getCostoAsistCantCDR() {
			return costoAsistCantCDR;
		}
		public void setCostoAsistCantCDR(long costoAsistCantCDR) {
			this.costoAsistCantCDR = costoAsistCantCDR;
		}
		public BigDecimal getCostoAsistCDR() {
			return costoAsistCDR;
		}
		public void setCostoAsistCDR(BigDecimal costoAsistCDR) {
			this.costoAsistCDR = costoAsistCDR;
		}
		public long getCostoAsistCantCIR() {
			return costoAsistCantCIR;
		}
		public void setCostoAsistCantCIR(long costoAsistCantCIR) {
			this.costoAsistCantCIR = costoAsistCantCIR;
		}
		public BigDecimal getCostoAsistCIR() {
			return costoAsistCIR;
		}
		public void setCostoAsistCIR(BigDecimal costoAsistCIR) {
			this.costoAsistCIR = costoAsistCIR;
		}
		public BigDecimal getCostoAsistCostoTotR() {
			return costoAsistCostoTotR;
		}
		public void setCostoAsistCostoTotR(BigDecimal costoAsistCostoTotR) {
			this.costoAsistCostoTotR = costoAsistCostoTotR;
		}
		public long getCostoTotalGestCantCDR() {
			return costoTotalGestCantCDR;
		}
		public void setCostoTotalGestCantCDR(long costoTotalGestCantCDR) {
			this.costoTotalGestCantCDR = costoTotalGestCantCDR;
		}
		public BigDecimal getCostoTotalGestCDR() {
			return costoTotalGestCDR;
		}
		public void setCostoTotalGestCDR(BigDecimal costoTotalGestCDR) {
			this.costoTotalGestCDR = costoTotalGestCDR;
		}
		public long getCostoTotalGestCantCIR() {
			return costoTotalGestCantCIR;
		}
		public void setCostoTotalGestCantCIR(long costoTotalGestCantCIR) {
			this.costoTotalGestCantCIR = costoTotalGestCantCIR;
		}
		public BigDecimal getCostoTotGestCIR() {
			return costoTotGestCIR;
		}
		public void setCostoTotGestCIR(BigDecimal costoTotGestCIR) {
			this.costoTotGestCIR = costoTotGestCIR;
		}
		public BigDecimal getCostoTotalGestoTotR() {
			return costoTotalGestoTotR;
		}
		public void setCostoTotalGestoTotR(BigDecimal costoTotalGestoTotR) {
			this.costoTotalGestoTotR = costoTotalGestoTotR;
		}
		public BigDecimal getCostoPromMensCDR() {
			return costoPromMensCDR;
		}
		public void setCostoPromMensCDR(BigDecimal costoPromMensCDR) {
			this.costoPromMensCDR = costoPromMensCDR;
		}
		public BigDecimal getCostoPromMensCTR() {
			return costoPromMensCTR;
		}
		public void setCostoPromMensCTR(BigDecimal costoPromMensCTR) {
			this.costoPromMensCTR = costoPromMensCTR;
		}
		public long getNroBenefCantP() {
			return nroBenefCantP;
		}
		public void setNroBenefCantP(long nroBenefCantP) {
			this.nroBenefCantP = nroBenefCantP;
		}
		public long getCostoCoordCantCDP() {
			return costoCoordCantCDP;
		}
		public void setCostoCoordCantCDP(long costoCoordCantCDP) {
			this.costoCoordCantCDP = costoCoordCantCDP;
		}
		public BigDecimal getCostoCoordCDP() {
			return costoCoordCDP;
		}
		public void setCostoCoordCDP(BigDecimal costoCoordCDP) {
			this.costoCoordCDP = costoCoordCDP;
		}
		public long getCostoCoordCantCIP() {
			return costoCoordCantCIP;
		}
		public void setCostoCoordCantCIP(long costoCoordCantCIP) {
			this.costoCoordCantCIP = costoCoordCantCIP;
		}
		public BigDecimal getCostoCoordCIP() {
			return costoCoordCIP;
		}
		public void setCostoCoordCIP(BigDecimal costoCoordCIP) {
			this.costoCoordCIP = costoCoordCIP;
		}
		public BigDecimal getCostoCoordCstoTotP() {
			return costoCoordCstoTotP;
		}
		public void setCostoCoordCstoTotP(BigDecimal costoCoordCstoTotP) {
			this.costoCoordCstoTotP = costoCoordCstoTotP;
		}
		public long getCostoSuperCantCDP() {
			return costoSuperCantCDP;
		}
		public void setCostoSuperCantCDP(long costoSuperCantCDP) {
			this.costoSuperCantCDP = costoSuperCantCDP;
		}
		public BigDecimal getCostoSuperCDP() {
			return costoSuperCDP;
		}
		public void setCostoSuperCDP(BigDecimal costoSuperCDP) {
			this.costoSuperCDP = costoSuperCDP;
		}
		public long getCostoSuperCantCIP() {
			return costoSuperCantCIP;
		}
		public void setCostoSuperCantCIP(long costoSuperCantCIP) {
			this.costoSuperCantCIP = costoSuperCantCIP;
		}
		public BigDecimal getCostoSuperCIP() {
			return costoSuperCIP;
		}
		public void setCostoSuperCIP(BigDecimal costoSuperCIP) {
			this.costoSuperCIP = costoSuperCIP;
		}
		public BigDecimal getCostoSuperCstoTotP() {
			return costoSuperCstoTotP;
		}
		public void setCostoSuperCstoTotP(BigDecimal costoSuperCstoTotP) {
			this.costoSuperCstoTotP = costoSuperCstoTotP;
		}
		public long getCostoGestCantCDP() {
			return costoGestCantCDP;
		}
		public void setCostoGestCantCDP(long costoGestCantCDP) {
			this.costoGestCantCDP = costoGestCantCDP;
		}
		public BigDecimal getCostoGestCDP() {
			return costoGestCDP;
		}
		public void setCostoGestCDP(BigDecimal costoGestCDP) {
			this.costoGestCDP = costoGestCDP;
		}
		public long getCostoGestCantCIP() {
			return costoGestCantCIP;
		}
		public void setCostoGestCantCIP(long costoGestCantCIP) {
			this.costoGestCantCIP = costoGestCantCIP;
		}
		public BigDecimal getCostoGestCIP() {
			return costoGestCIP;
		}
		public void setCostoGestCIP(BigDecimal costoGestCIP) {
			this.costoGestCIP = costoGestCIP;
		}
		public BigDecimal getCostoGestCstoTotP() {
			return costoGestCstoTotP;
		}
		public void setCostoGestCstoTotP(BigDecimal costoGestCstoTotP) {
			this.costoGestCstoTotP = costoGestCstoTotP;
		}
		public long getCostoAsistCantCDP() {
			return costoAsistCantCDP;
		}
		public void setCostoAsistCantCDP(long costoAsistCantCDP) {
			this.costoAsistCantCDP = costoAsistCantCDP;
		}
		public BigDecimal getCostoAsistCDP() {
			return costoAsistCDP;
		}
		public void setCostoAsistCDP(BigDecimal costoAsistCDP) {
			this.costoAsistCDP = costoAsistCDP;
		}
		public long getCostoAsistCantCIP() {
			return costoAsistCantCIP;
		}
		public void setCostoAsistCantCIP(long costoAsistCantCIP) {
			this.costoAsistCantCIP = costoAsistCantCIP;
		}
		public BigDecimal getCostoAsistCIP() {
			return costoAsistCIP;
		}
		public void setCostoAsistCIP(BigDecimal costoAsistCIP) {
			this.costoAsistCIP = costoAsistCIP;
		}
		public BigDecimal getCostoAsistCstoTotP() {
			return costoAsistCstoTotP;
		}
		public void setCostoAsistCstoTotP(BigDecimal costoAsistCstoTotP) {
			this.costoAsistCstoTotP = costoAsistCstoTotP;
		}
		public long getCostoTotGestCantCDP() {
			return costoTotGestCantCDP;
		}
		public void setCostoTotGestCantCDP(long costoTotGestCantCDP) {
			this.costoTotGestCantCDP = costoTotGestCantCDP;
		}
		public BigDecimal getCostoTotGestCDP() {
			return costoTotGestCDP;
		}
		public void setCostoTotGestCDP(BigDecimal costoTotGestCDP) {
			this.costoTotGestCDP = costoTotGestCDP;
		}
		public long getCostoTotGestCantCIP() {
			return costoTotGestCantCIP;
		}
		public void setCostoTotGestCantCIP(long costoTotGestCantCIP) {
			this.costoTotGestCantCIP = costoTotGestCantCIP;
		}
		public BigDecimal getCostoTotGestCIP() {
			return costoTotGestCIP;
		}
		public void setCostoTotGestCIP(BigDecimal costoTotGestCIP) {
			this.costoTotGestCIP = costoTotGestCIP;
		}
		public BigDecimal getCostoTotGestoTotP() {
			return costoTotGestoTotP;
		}
		public void setCostoTotGestoTotP(BigDecimal costoTotGestoTotP) {
			this.costoTotGestoTotP = costoTotGestoTotP;
		}
		public BigDecimal getCostoPromMensCDP() {
			return costoPromMensCDP;
		}
		public void setCostoPromMensCDP(BigDecimal costoPromMensCDP) {
			this.costoPromMensCDP = costoPromMensCDP;
		}
		public BigDecimal getCostoPromMensCTP() {
			return costoPromMensCTP;
		}
		public void setCostoPromMensCTP(BigDecimal costoPromMensCTP) {
			this.costoPromMensCTP = costoPromMensCTP;
		}
		public long getNroBenefCantL() {
			return nroBenefCantL;
		}
		public void setNroBenefCantL(long nroBenefCantL) {
			this.nroBenefCantL = nroBenefCantL;
		}
		public long getCostoCoordCantCDL() {
			return costoCoordCantCDL;
		}
		public void setCostoCoordCantCDL(long costoCoordCantCDL) {
			this.costoCoordCantCDL = costoCoordCantCDL;
		}
		public BigDecimal getCostoCoordCDL() {
			return costoCoordCDL;
		}
		public void setCostoCoordCDL(BigDecimal costoCoordCDL) {
			this.costoCoordCDL = costoCoordCDL;
		}
		public long getCostoCoordCantCIL() {
			return costoCoordCantCIL;
		}
		public void setCostoCoordCantCIL(long costoCoordCantCIL) {
			this.costoCoordCantCIL = costoCoordCantCIL;
		}
		public BigDecimal getCostoCoordCIL() {
			return costoCoordCIL;
		}
		public void setCostoCoordCIL(BigDecimal costoCoordCIL) {
			this.costoCoordCIL = costoCoordCIL;
		}
		public BigDecimal getCostoCoordCstoTotL() {
			return costoCoordCstoTotL;
		}
		public void setCostoCoordCstoTotL(BigDecimal costoCoordCstoTotL) {
			this.costoCoordCstoTotL = costoCoordCstoTotL;
		}
		public long getCostoSuperCantCDL() {
			return costoSuperCantCDL;
		}
		public void setCostoSuperCantCDL(long costoSuperCantCDL) {
			this.costoSuperCantCDL = costoSuperCantCDL;
		}
		public BigDecimal getCostoSuperCDL() {
			return costoSuperCDL;
		}
		public void setCostoSuperCDL(BigDecimal costoSuperCDL) {
			this.costoSuperCDL = costoSuperCDL;
		}
		public long getCostoSuperCantCIL() {
			return costoSuperCantCIL;
		}
		public void setCostoSuperCantCIL(long costoSuperCantCIL) {
			this.costoSuperCantCIL = costoSuperCantCIL;
		}
		public BigDecimal getCostoSuperCIL() {
			return costoSuperCIL;
		}
		public void setCostoSuperCIL(BigDecimal costoSuperCIL) {
			this.costoSuperCIL = costoSuperCIL;
		}
		public BigDecimal getCostoSuperCstoTotL() {
			return costoSuperCstoTotL;
		}
		public void setCostoSuperCstoTotL(BigDecimal costoSuperCstoTotL) {
			this.costoSuperCstoTotL = costoSuperCstoTotL;
		}
		public long getCostoGestCantCDL() {
			return costoGestCantCDL;
		}
		public void setCostoGestCantCDL(long costoGestCantCDL) {
			this.costoGestCantCDL = costoGestCantCDL;
		}
		public BigDecimal getCostoGestCDL() {
			return costoGestCDL;
		}
		public void setCostoGestCDL(BigDecimal costoGestCDL) {
			this.costoGestCDL = costoGestCDL;
		}
		public long getCostoGestCantCIL() {
			return costoGestCantCIL;
		}
		public void setCostoGestCantCIL(long costoGestCantCIL) {
			this.costoGestCantCIL = costoGestCantCIL;
		}
		public BigDecimal getCostoGestorCIL() {
			return costoGestorCIL;
		}
		public void setCostoGestorCIL(BigDecimal costoGestorCIL) {
			this.costoGestorCIL = costoGestorCIL;
		}
		public BigDecimal getCostoGestCstoTotL() {
			return costoGestCstoTotL;
		}
		public void setCostoGestCstoTotL(BigDecimal costoGestCstoTotL) {
			this.costoGestCstoTotL = costoGestCstoTotL;
		}
		public long getCostoAsistCantCDL() {
			return costoAsistCantCDL;
		}
		public void setCostoAsistCantCDL(long costoAsistCantCDL) {
			this.costoAsistCantCDL = costoAsistCantCDL;
		}
		public BigDecimal getCostoAsistCDL() {
			return costoAsistCDL;
		}
		public void setCostoAsistCDL(BigDecimal costoAsistCDL) {
			this.costoAsistCDL = costoAsistCDL;
		}
		public long getCostoAsistCantCIL() {
			return costoAsistCantCIL;
		}
		public void setCostoAsistCantCIL(long costoAsistCantCIL) {
			this.costoAsistCantCIL = costoAsistCantCIL;
		}
		public BigDecimal getCostoAsistCIL() {
			return costoAsistCIL;
		}
		public void setCostoAsistCIL(BigDecimal costoAsistCIL) {
			this.costoAsistCIL = costoAsistCIL;
		}
		public BigDecimal getCostoAsistCstoTotL() {
			return costoAsistCstoTotL;
		}
		public void setCostoAsistCstoTotL(BigDecimal costoAsistCstoTotL) {
			this.costoAsistCstoTotL = costoAsistCstoTotL;
		}
		public long getCostoTotGestCantCDL() {
			return costoTotGestCantCDL;
		}
		public void setCostoTotGestCantCDL(long costoTotGestCantCDL) {
			this.costoTotGestCantCDL = costoTotGestCantCDL;
		}
		public BigDecimal getCostoTotGestCDL() {
			return costoTotGestCDL;
		}
		public void setCostoTotGestCDL(BigDecimal costoTotGestCDL) {
			this.costoTotGestCDL = costoTotGestCDL;
		}
		public long getCostoTotGestCantCIL() {
			return costoTotGestCantCIL;
		}
		public void setCostoTotGestCantCIL(long costoTotGestCantCIL) {
			this.costoTotGestCantCIL = costoTotGestCantCIL;
		}
		public BigDecimal getCostoTotGestCIL() {
			return costoTotGestCIL;
		}
		public void setCostoTotGestCIL(BigDecimal costoTotGestCIL) {
			this.costoTotGestCIL = costoTotGestCIL;
		}
		public BigDecimal getCostoTotGestoTotL() {
			return costoTotGestoTotL;
		}
		public void setCostoTotGestoTotL(BigDecimal costoTotGestoTotL) {
			this.costoTotGestoTotL = costoTotGestoTotL;
		}
		public BigDecimal getCostoPromMensCDL() {
			return costoPromMensCDL;
		}
		public void setCostoPromMensCDL(BigDecimal costoPromMensCDL) {
			this.costoPromMensCDL = costoPromMensCDL;
		}
		public BigDecimal getCostoPromMensCTL() {
			return costoPromMensCTL;
		}
		public void setCostoPromMensCTL(BigDecimal costoPromMensCTL) {
			this.costoPromMensCTL = costoPromMensCTL;
		}
		public long getSumaCantNroBenefT() {
			return SumaCantNroBenefT;
		}
		public void setSumaCantNroBenefT(long sumaCantNroBenefT) {
			SumaCantNroBenefT = sumaCantNroBenefT;
		}
		public long getSumaCantCoordT() {
			return SumaCantCoordT;
		}
		public void setSumaCantCoordT(long sumaCantCoordT) {
			SumaCantCoordT = sumaCantCoordT;
		}
		public long getSumaCantSupervT() {
			return SumaCantSupervT;
		}
		public void setSumaCantSupervT(long sumaCantSupervT) {
			SumaCantSupervT = sumaCantSupervT;
		}
		public long getSumaCantGestorT() {
			return SumaCantGestorT;
		}
		public void setSumaCantGestorT(long sumaCantGestorT) {
			SumaCantGestorT = sumaCantGestorT;
		}
		public long getSumaCantAsistT() {
			return SumaCantAsistT;
		}
		public void setSumaCantAsistT(long sumaCantAsistT) {
			SumaCantAsistT = sumaCantAsistT;
		}
		public long getSumaCantCstoTotalT() {
			return SumaCantCstoTotalT;
		}
		public void setSumaCantCstoTotalT(long sumaCantCstoTotalT) {
			SumaCantCstoTotalT = sumaCantCstoTotalT;
		}
		public BigDecimal getSumaCostCoordT() {
			return SumaCostCoordT;
		}
		public void setSumaCostCoordT(BigDecimal sumaCostCoordT) {
			SumaCostCoordT = sumaCostCoordT;
		}
		public BigDecimal getSumaCostSupervT() {
			return SumaCostSupervT;
		}
		public void setSumaCostSupervT(BigDecimal sumaCostSupervT) {
			SumaCostSupervT = sumaCostSupervT;
		}
		public BigDecimal getSumaCostGestorT() {
			return SumaCostGestorT;
		}
		public void setSumaCostGestorT(BigDecimal sumaCostGestorT) {
			SumaCostGestorT = sumaCostGestorT;
		}
		public BigDecimal getSumaCostAsistT() {
			return SumaCostAsistT;
		}
		public void setSumaCostAsistT(BigDecimal sumaCostAsistT) {
			SumaCostAsistT = sumaCostAsistT;
		}
		public BigDecimal getSumaCostCstoTotalT() {
			return SumaCostCstoTotalT;
		}
		public void setSumaCostCstoTotalT(BigDecimal sumaCostCstoTotalT) {
			SumaCostCstoTotalT = sumaCostCstoTotalT;
		}
		public BigDecimal getSumaCostPromiTotalT() {
			return SumaCostPromiTotalT;
		}
		public void setSumaCostPromiTotalT(BigDecimal sumaCostPromiTotalT) {
			SumaCostPromiTotalT = sumaCostPromiTotalT;
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
		public String getNombreArchivo() {
			return nombreArchivo;
		}
		public void setNombreArchivo(String nombreArchivo) {
			this.nombreArchivo = nombreArchivo;
		}
		public String getTipoArchivo() {
			return tipoArchivo;
		}
		public void setTipoArchivo(String tipoArchivo) {
			this.tipoArchivo = tipoArchivo;
		}
		public String getDescEmpresa() {
			return descEmpresa;
		}
		public void setDescEmpresa(String descEmpresa) {
			this.descEmpresa = descEmpresa;
		}
		public String getDescMesPresentacion() {
			return descMesPresentacion;
		}
		public void setDescMesPresentacion(String descMesPresentacion) {
			this.descMesPresentacion = descMesPresentacion;
		}
		
		
		
		
		
		
		
	
}
