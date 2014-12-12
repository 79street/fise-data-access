package gob.osinergmin.fise.bean;
import gob.osinergmin.fise.domain.AdmEmpresa;
import gob.osinergmin.fise.domain.AdmUbigeo;
import gob.osinergmin.fise.domain.FisePeriodoEnvio;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;
import java.util.List;
import java.util.Map;

public class Formato12DCBean implements Serializable {
	
	private static final long serialVersionUID = 1L;
	
	private Map<Long,String> listaMes;
	private List<AdmEmpresa> listaEmpresas;
	private Map<String,String> listaEtapa;
	private List<FisePeriodoEnvio> listaPeriodoEnvio;
	
	private Map<Long, String> listaZonaBenef;
	private List<AdmUbigeo> listaDepartamentos;
	private Map<Long, String> listaEtapaEjecucion;
	//private List<FiseTipDocRef> listaTipoDocumento;
	private Map<String, String> listaTipoDocumento;
	private Map<String, String> listaTipoGasto;
	
	private String codEmpresaB;
	private String mesDesde;
	private String anioDesde;
	private String mesHasta;
	private String anioHasta;
	private String etapaB;

	private boolean admin;

	private Long anoEjecucionHidden;
	private Long mesEjecucionHidden;
	private Long etapaEjecucionHidden;
	
	private String codigoEmpresa;
	private String codigoEmpresaHidden;
	
	private Long anioPresentacion = 0L;
	private Long mesPresentacion = 0L;
	private Long anioEjecucion = 0L;
	private Long mesEjecucion = 0L;
	private Long etapaEjecucion = 0L;
	private Long nroItemEtapa = 0L;

	private String periodoEnvio;
	private String periodoEnvioHidden;
	
	private String flagPeriodoEjecucion;

	//detalles
	private String codUbigeo;
	private String codDepartamento;
	private String codProvincia;
	private String codDistrito;
	private String descDepartamento;
	private String descProvincia;
	private String descDistrito;
	private String localidad;
	private Long zonaBenef;
	private String codCuentaContable;
	private String gasto;
	private String tipoGasto;
	private String tipoDocumento;
	private String rucEmpresa;
	private String serieDocumento;
	private String nroDocumento;
	private Date fechaAutorizacion;
	private String fechaAutorizacionString;
	private String nroDocAutorizacion;
	
	
	private Long cantidad = 0L;
	private BigDecimal costoUnitario = new BigDecimal(0);
	private BigDecimal totalGeneral = new BigDecimal(0);
	
	private String codDepartamentoHidden;
	private String codProvinciaHidden;
	private String codDistritoHidden;
	
	private String tipoOperacion;
	
	private String usuario;
	private String terminal;
	private String nombreArchivo;
	private String tipoArchivo;
	
	private String descEmpresa;
	private String descMesPresentacion;
	private String descMesEjecucion;
	
	private String etapa;
	
	private String descGrupoInformacion;
	private String descEstado;
	
	private String  codEdelnor;
	private String  codLuzSur;
	
	private List<FisePeriodoEnvio> listaPeriodo;
	private boolean readOnly;
	private boolean readonlyFlagPeriodo;
	private boolean readonlyEdit;
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
	public Map<String, String> getListaEtapa() {
		return listaEtapa;
	}
	public void setListaEtapa(Map<String, String> listaEtapa) {
		this.listaEtapa = listaEtapa;
	}
	public List<FisePeriodoEnvio> getListaPeriodoEnvio() {
		return listaPeriodoEnvio;
	}
	public void setListaPeriodoEnvio(List<FisePeriodoEnvio> listaPeriodoEnvio) {
		this.listaPeriodoEnvio = listaPeriodoEnvio;
	}
	public Map<Long, String> getListaZonaBenef() {
		return listaZonaBenef;
	}
	public void setListaZonaBenef(Map<Long, String> listaZonaBenef) {
		this.listaZonaBenef = listaZonaBenef;
	}
	public List<AdmUbigeo> getListaDepartamentos() {
		return listaDepartamentos;
	}
	public void setListaDepartamentos(List<AdmUbigeo> listaDepartamentos) {
		this.listaDepartamentos = listaDepartamentos;
	}
	public Map<Long, String> getListaEtapaEjecucion() {
		return listaEtapaEjecucion;
	}
	public void setListaEtapaEjecucion(Map<Long, String> listaEtapaEjecucion) {
		this.listaEtapaEjecucion = listaEtapaEjecucion;
	}
	public Map<String, String> getListaTipoDocumento() {
		return listaTipoDocumento;
	}
	public void setListaTipoDocumento(Map<String, String> listaTipoDocumento) {
		this.listaTipoDocumento = listaTipoDocumento;
	}
	public String getCodEmpresaB() {
		return codEmpresaB;
	}
	public void setCodEmpresaB(String codEmpresaB) {
		this.codEmpresaB = codEmpresaB;
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
	public String getEtapaB() {
		return etapaB;
	}
	public void setEtapaB(String etapaB) {
		this.etapaB = etapaB;
	}
	public boolean isAdmin() {
		return admin;
	}
	public void setAdmin(boolean admin) {
		this.admin = admin;
	}
	public Long getAnoEjecucionHidden() {
		return anoEjecucionHidden;
	}
	public void setAnoEjecucionHidden(Long anoEjecucionHidden) {
		this.anoEjecucionHidden = anoEjecucionHidden;
	}
	public Long getMesEjecucionHidden() {
		return mesEjecucionHidden;
	}
	public void setMesEjecucionHidden(Long mesEjecucionHidden) {
		this.mesEjecucionHidden = mesEjecucionHidden;
	}
	public Long getEtapaEjecucionHidden() {
		return etapaEjecucionHidden;
	}
	public void setEtapaEjecucionHidden(Long etapaEjecucionHidden) {
		this.etapaEjecucionHidden = etapaEjecucionHidden;
	}
	public String getCodigoEmpresa() {
		return codigoEmpresa;
	}
	public void setCodigoEmpresa(String codigoEmpresa) {
		this.codigoEmpresa = codigoEmpresa;
	}
	public String getCodigoEmpresaHidden() {
		return codigoEmpresaHidden;
	}
	public void setCodigoEmpresaHidden(String codigoEmpresaHidden) {
		this.codigoEmpresaHidden = codigoEmpresaHidden;
	}
	public Long getAnioPresentacion() {
		return anioPresentacion;
	}
	public void setAnioPresentacion(Long anioPresentacion) {
		this.anioPresentacion = anioPresentacion;
	}
	public Long getMesPresentacion() {
		return mesPresentacion;
	}
	public void setMesPresentacion(Long mesPresentacion) {
		this.mesPresentacion = mesPresentacion;
	}
	public Long getAnioEjecucion() {
		return anioEjecucion;
	}
	public void setAnioEjecucion(Long anioEjecucion) {
		this.anioEjecucion = anioEjecucion;
	}
	public Long getMesEjecucion() {
		return mesEjecucion;
	}
	public void setMesEjecucion(Long mesEjecucion) {
		this.mesEjecucion = mesEjecucion;
	}
	public Long getEtapaEjecucion() {
		return etapaEjecucion;
	}
	public void setEtapaEjecucion(Long etapaEjecucion) {
		this.etapaEjecucion = etapaEjecucion;
	}
	public Long getNroItemEtapa() {
		return nroItemEtapa;
	}
	public void setNroItemEtapa(Long nroItemEtapa) {
		this.nroItemEtapa = nroItemEtapa;
	}
	public String getPeriodoEnvio() {
		return periodoEnvio;
	}
	public void setPeriodoEnvio(String periodoEnvio) {
		this.periodoEnvio = periodoEnvio;
	}
	public String getPeriodoEnvioHidden() {
		return periodoEnvioHidden;
	}
	public void setPeriodoEnvioHidden(String periodoEnvioHidden) {
		this.periodoEnvioHidden = periodoEnvioHidden;
	}
	public String getFlagPeriodoEjecucion() {
		return flagPeriodoEjecucion;
	}
	public void setFlagPeriodoEjecucion(String flagPeriodoEjecucion) {
		this.flagPeriodoEjecucion = flagPeriodoEjecucion;
	}
	public String getCodUbigeo() {
		return codUbigeo;
	}
	public void setCodUbigeo(String codUbigeo) {
		this.codUbigeo = codUbigeo;
	}
	public String getCodDepartamento() {
		return codDepartamento;
	}
	public void setCodDepartamento(String codDepartamento) {
		this.codDepartamento = codDepartamento;
	}
	public String getCodProvincia() {
		return codProvincia;
	}
	public void setCodProvincia(String codProvincia) {
		this.codProvincia = codProvincia;
	}
	public String getCodDistrito() {
		return codDistrito;
	}
	public void setCodDistrito(String codDistrito) {
		this.codDistrito = codDistrito;
	}
	public String getDescDepartamento() {
		return descDepartamento;
	}
	public void setDescDepartamento(String descDepartamento) {
		this.descDepartamento = descDepartamento;
	}
	public String getDescProvincia() {
		return descProvincia;
	}
	public void setDescProvincia(String descProvincia) {
		this.descProvincia = descProvincia;
	}
	public String getDescDistrito() {
		return descDistrito;
	}
	public void setDescDistrito(String descDistrito) {
		this.descDistrito = descDistrito;
	}
	public String getLocalidad() {
		return localidad;
	}
	public void setLocalidad(String localidad) {
		this.localidad = localidad;
	}
	public Long getZonaBenef() {
		return zonaBenef;
	}
	public void setZonaBenef(Long zonaBenef) {
		this.zonaBenef = zonaBenef;
	}
	public String getCodCuentaContable() {
		return codCuentaContable;
	}
	public void setCodCuentaContable(String codCuentaContable) {
		this.codCuentaContable = codCuentaContable;
	}
	public String getGasto() {
		return gasto;
	}
	public void setGasto(String gasto) {
		this.gasto = gasto;
	}
	public String getTipoDocumento() {
		return tipoDocumento;
	}
	public void setTipoDocumento(String tipoDocumento) {
		this.tipoDocumento = tipoDocumento;
	}
	public String getRucEmpresa() {
		return rucEmpresa;
	}
	public void setRucEmpresa(String rucEmpresa) {
		this.rucEmpresa = rucEmpresa;
	}
	public String getSerieDocumento() {
		return serieDocumento;
	}
	public void setSerieDocumento(String serieDocumento) {
		this.serieDocumento = serieDocumento;
	}
	public String getNroDocumento() {
		return nroDocumento;
	}
	public void setNroDocumento(String nroDocumento) {
		this.nroDocumento = nroDocumento;
	}
	public Date getFechaAutorizacion() {
		return fechaAutorizacion;
	}
	public void setFechaAutorizacion(Date fechaAutorizacion) {
		this.fechaAutorizacion = fechaAutorizacion;
	}
	public String getNroDocAutorizacion() {
		return nroDocAutorizacion;
	}
	public void setNroDocAutorizacion(String nroDocAutorizacion) {
		this.nroDocAutorizacion = nroDocAutorizacion;
	}
	public Long getCantidad() {
		return cantidad;
	}
	public void setCantidad(Long cantidad) {
		this.cantidad = cantidad;
	}
	public BigDecimal getCostoUnitario() {
		return costoUnitario;
	}
	public void setCostoUnitario(BigDecimal costoUnitario) {
		this.costoUnitario = costoUnitario;
	}
	public BigDecimal getTotalGeneral() {
		return totalGeneral;
	}
	public void setTotalGeneral(BigDecimal totalGeneral) {
		this.totalGeneral = totalGeneral;
	}
	public String getCodDepartamentoHidden() {
		return codDepartamentoHidden;
	}
	public void setCodDepartamentoHidden(String codDepartamentoHidden) {
		this.codDepartamentoHidden = codDepartamentoHidden;
	}
	public String getCodProvinciaHidden() {
		return codProvinciaHidden;
	}
	public void setCodProvinciaHidden(String codProvinciaHidden) {
		this.codProvinciaHidden = codProvinciaHidden;
	}
	public String getCodDistritoHidden() {
		return codDistritoHidden;
	}
	public void setCodDistritoHidden(String codDistritoHidden) {
		this.codDistritoHidden = codDistritoHidden;
	}
	public String getTipoOperacion() {
		return tipoOperacion;
	}
	public void setTipoOperacion(String tipoOperacion) {
		this.tipoOperacion = tipoOperacion;
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
	public String getDescMesEjecucion() {
		return descMesEjecucion;
	}
	public void setDescMesEjecucion(String descMesEjecucion) {
		this.descMesEjecucion = descMesEjecucion;
	}
	public String getEtapa() {
		return etapa;
	}
	public void setEtapa(String etapa) {
		this.etapa = etapa;
	}
	public String getDescGrupoInformacion() {
		return descGrupoInformacion;
	}
	public void setDescGrupoInformacion(String descGrupoInformacion) {
		this.descGrupoInformacion = descGrupoInformacion;
	}
	public String getDescEstado() {
		return descEstado;
	}
	public void setDescEstado(String descEstado) {
		this.descEstado = descEstado;
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
	public List<FisePeriodoEnvio> getListaPeriodo() {
		return listaPeriodo;
	}
	public void setListaPeriodo(List<FisePeriodoEnvio> listaPeriodo) {
		this.listaPeriodo = listaPeriodo;
	}
	public boolean isReadOnly() {
		return readOnly;
	}
	public void setReadOnly(boolean readOnly) {
		this.readOnly = readOnly;
	}
	public boolean isReadonlyFlagPeriodo() {
		return readonlyFlagPeriodo;
	}
	public void setReadonlyFlagPeriodo(boolean readonlyFlagPeriodo) {
		this.readonlyFlagPeriodo = readonlyFlagPeriodo;
	}
	public boolean isReadonlyEdit() {
		return readonlyEdit;
	}
	public void setReadonlyEdit(boolean readonlyEdit) {
		this.readonlyEdit = readonlyEdit;
	}
	public String getTipoGasto() {
		return tipoGasto;
	}
	public void setTipoGasto(String tipoGasto) {
		this.tipoGasto = tipoGasto;
	}
	public String getFechaAutorizacionString() {
		return fechaAutorizacionString;
	}
	public void setFechaAutorizacionString(String fechaAutorizacionString) {
		this.fechaAutorizacionString = fechaAutorizacionString;
	}
	public Map<String, String> getListaTipoGasto() {
		return listaTipoGasto;
	}
	public void setListaTipoGasto(Map<String, String> listaTipoGasto) {
		this.listaTipoGasto = listaTipoGasto;
	}
	
		
}
