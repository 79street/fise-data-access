package gob.osinergmin.fise.bean;

import gob.osinergmin.fise.domain.AdmEmpresa;

import java.util.List;
import java.util.Map;


public class PeriodoEnvioBean {

	/*******DATOS PARA BUSQUEDA**************/
	private Map<Long,String> listaMes;
	private List<AdmEmpresa> listaEmpresas;
	private String anioDesde;	
	private String mesDesde;	
	private String codEmpresaBusq;
	private String formatoBusq;	
	private String etapaBusq;	
	private String flagEnvioBusq;
	private String estadoBusq;
	
	private boolean admin;	
	
	
	/**DATOS PARA NUEVO REGISTRO*****/	
	private String secuencial;
	private String codEmpresa;
	private String anioPres;
	private String mesPres;
	private String formato;
	private String etapa;	
	private String desde;//Date
	private String hasta;//Date
	private String fechaAmpl;//fecha ampliada
	private String diasNotifCierre; //dias de notificacion antes de cierre
	private String flagEnvioObs;//flag envio con observaciones
	private String flagAnioMesEjec; //flag mostrar anio y mes de ejecucion
	private String flagEditarCosto;//flag para saber si editar o no costos estandaras o unitarios
	////////////////////////////////////////////////////
	private String ceginroexp;
    private String ceginrotra;
    private String ceginrodoc;
    private String cegifecenv;//Date
    private String cegifecrecosi;//Date
    private String cegifecrecgart;//Date
    private String ceginrodocobs;
    private String cegifecdocobs;//Date
    private String cegifecenvdocobs;//Date
    private String cegifecrecnotobs;//Date
    private String cegifecemanotobs;//Date
    private String cegifecvenlvtoobs;//Date
    //////////////////////////////////////////////////////
    private String estado; 
    private String anoIniVigencia;
	private String anoFinVigencia;  
	private String flagHabCostos;//flag habilita costos directos e indirectos o ambos 
	//datos de auditoria
	private String usuario;
	private String terminal;
	
	
	
	
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
	public String getCodEmpresaBusq() {
		return codEmpresaBusq;
	}
	public void setCodEmpresaBusq(String codEmpresaBusq) {
		this.codEmpresaBusq = codEmpresaBusq;
	}
	public String getFormatoBusq() {
		return formatoBusq;
	}
	public void setFormatoBusq(String formatoBusq) {
		this.formatoBusq = formatoBusq;
	}
	public String getEtapaBusq() {
		return etapaBusq;
	}
	public void setEtapaBusq(String etapaBusq) {
		this.etapaBusq = etapaBusq;
	}
	public String getFlagEnvioBusq() {
		return flagEnvioBusq;
	}
	public void setFlagEnvioBusq(String flagEnvioBusq) {
		this.flagEnvioBusq = flagEnvioBusq;
	}
	public String getEstadoBusq() {
		return estadoBusq;
	}
	public void setEstadoBusq(String estadoBusq) {
		this.estadoBusq = estadoBusq;
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
	public String getFormato() {
		return formato;
	}
	public void setFormato(String formato) {
		this.formato = formato;
	}
	public String getEtapa() {
		return etapa;
	}
	public void setEtapa(String etapa) {
		this.etapa = etapa;
	}
	public String getDesde() {
		return desde;
	}
	public void setDesde(String desde) {
		this.desde = desde;
	}
	public String getHasta() {
		return hasta;
	}
	public void setHasta(String hasta) {
		this.hasta = hasta;
	}
	public String getDiasNotifCierre() {
		return diasNotifCierre;
	}
	public void setDiasNotifCierre(String diasNotifCierre) {
		this.diasNotifCierre = diasNotifCierre;
	}
	public String getFlagEnvioObs() {
		return flagEnvioObs;
	}
	public void setFlagEnvioObs(String flagEnvioObs) {
		this.flagEnvioObs = flagEnvioObs;
	}
	public String getFlagAnioMesEjec() {
		return flagAnioMesEjec;
	}
	public void setFlagAnioMesEjec(String flagAnioMesEjec) {
		this.flagAnioMesEjec = flagAnioMesEjec;
	}
	public String getCeginroexp() {
		return ceginroexp;
	}
	public void setCeginroexp(String ceginroexp) {
		this.ceginroexp = ceginroexp;
	}
	public String getCeginrotra() {
		return ceginrotra;
	}
	public void setCeginrotra(String ceginrotra) {
		this.ceginrotra = ceginrotra;
	}
	public String getCeginrodoc() {
		return ceginrodoc;
	}
	public void setCeginrodoc(String ceginrodoc) {
		this.ceginrodoc = ceginrodoc;
	}
	public String getCegifecenv() {
		return cegifecenv;
	}
	public void setCegifecenv(String cegifecenv) {
		this.cegifecenv = cegifecenv;
	}
	public String getCegifecrecosi() {
		return cegifecrecosi;
	}
	public void setCegifecrecosi(String cegifecrecosi) {
		this.cegifecrecosi = cegifecrecosi;
	}
	public String getCegifecrecgart() {
		return cegifecrecgart;
	}
	public void setCegifecrecgart(String cegifecrecgart) {
		this.cegifecrecgart = cegifecrecgart;
	}
	public String getCeginrodocobs() {
		return ceginrodocobs;
	}
	public void setCeginrodocobs(String ceginrodocobs) {
		this.ceginrodocobs = ceginrodocobs;
	}
	public String getCegifecdocobs() {
		return cegifecdocobs;
	}
	public void setCegifecdocobs(String cegifecdocobs) {
		this.cegifecdocobs = cegifecdocobs;
	}
	public String getCegifecenvdocobs() {
		return cegifecenvdocobs;
	}
	public void setCegifecenvdocobs(String cegifecenvdocobs) {
		this.cegifecenvdocobs = cegifecenvdocobs;
	}
	public String getCegifecrecnotobs() {
		return cegifecrecnotobs;
	}
	public void setCegifecrecnotobs(String cegifecrecnotobs) {
		this.cegifecrecnotobs = cegifecrecnotobs;
	}
	public String getCegifecemanotobs() {
		return cegifecemanotobs;
	}
	public void setCegifecemanotobs(String cegifecemanotobs) {
		this.cegifecemanotobs = cegifecemanotobs;
	}
	public String getCegifecvenlvtoobs() {
		return cegifecvenlvtoobs;
	}
	public void setCegifecvenlvtoobs(String cegifecvenlvtoobs) {
		this.cegifecvenlvtoobs = cegifecvenlvtoobs;
	}
	public String getEstado() {
		return estado;
	}
	public void setEstado(String estado) {
		this.estado = estado;
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
	public String getFlagHabCostos() {
		return flagHabCostos;
	}
	public void setFlagHabCostos(String flagHabCostos) {
		this.flagHabCostos = flagHabCostos;
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
	public String getSecuencial() {
		return secuencial;
	}
	public void setSecuencial(String secuencial) {
		this.secuencial = secuencial;
	}
	public String getFechaAmpl() {
		return fechaAmpl;
	}
	public void setFechaAmpl(String fechaAmpl) {
		this.fechaAmpl = fechaAmpl;
	}
	public String getFlagEditarCosto() {
		return flagEditarCosto;
	}
	public void setFlagEditarCosto(String flagEditarCosto) {
		this.flagEditarCosto = flagEditarCosto;
	}
	
	
}
