package gob.osinergmin.fise.bean;
import gob.osinergmin.fise.domain.AdmEmpresa;
import gob.osinergmin.fise.domain.AdmUbigeo;
import gob.osinergmin.fise.domain.FisePeriodoEnvio;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.List;
import java.util.Map;

public class Formato12CCBean implements Serializable {
	
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
	private String codUbigeoOrigen;
	private String codDepartamentoOrigen;
	private String codProvinciaOrigen;
	private String codDistritoOrigen;
	private String descDepartamentoOrigen;
	private String descProvinciaOrigen;
	private String descDistritoOrigen;
	private String localidadOrigen;
	private String codUbigeoDestino;
	private String codDepartamentoDestino;
	private String codProvinciaDestino;
	private String codDistritoDestino;
	private String descDepartamentoDestino;
	private String descProvinciaDestino;
	private String descDistritoDestino;
	private String localidadDestino;
	private Long zonaBenef;
	private String codCuentaContable;
	private String actividad;
	private String tipoDocumento;
	private String rucEmpresa;
	private String serieDocumento;
	private String nroDocumento;
	private Long nroDias = 0L;
	private BigDecimal montoAlimentacion = new BigDecimal(0);
	private BigDecimal montoAlojamiento = new BigDecimal(0);
	private BigDecimal montoMovilidad = new BigDecimal(0);
	private BigDecimal totalDesplazamiento = new BigDecimal(0);
	private BigDecimal subTotalEtapa = new BigDecimal(0);
	private BigDecimal totalGeneral = new BigDecimal(0);
	
	private String codDepartamentoOrigenHidden;
	private String codProvinciaOrigenHidden;
	private String codDistritoOrigenHidden;
	private String codDepartamentoDestinoHidden;
	private String codProvinciaDestinoHidden;
	private String codDistritoDestinoHidden;
	
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
	public String getCodigoEmpresa() {
		return codigoEmpresa;
	}
	public void setCodigoEmpresa(String codigoEmpresa) {
		this.codigoEmpresa = codigoEmpresa;
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
	public String getFlagPeriodoEjecucion() {
		return flagPeriodoEjecucion;
	}
	public void setFlagPeriodoEjecucion(String flagPeriodoEjecucion) {
		this.flagPeriodoEjecucion = flagPeriodoEjecucion;
	}
	public String getCodUbigeoOrigen() {
		return codUbigeoOrigen;
	}
	public void setCodUbigeoOrigen(String codUbigeoOrigen) {
		this.codUbigeoOrigen = codUbigeoOrigen;
	}
	public String getCodDepartamentoOrigen() {
		return codDepartamentoOrigen;
	}
	public void setCodDepartamentoOrigen(String codDepartamentoOrigen) {
		this.codDepartamentoOrigen = codDepartamentoOrigen;
	}
	public String getCodProvinciaOrigen() {
		return codProvinciaOrigen;
	}
	public void setCodProvinciaOrigen(String codProvinciaOrigen) {
		this.codProvinciaOrigen = codProvinciaOrigen;
	}
	public String getCodDistritoOrigen() {
		return codDistritoOrigen;
	}
	public void setCodDistritoOrigen(String codDistritoOrigen) {
		this.codDistritoOrigen = codDistritoOrigen;
	}
	public String getLocalidadOrigen() {
		return localidadOrigen;
	}
	public void setLocalidadOrigen(String localidadOrigen) {
		this.localidadOrigen = localidadOrigen;
	}
	public String getCodUbigeoDestino() {
		return codUbigeoDestino;
	}
	public void setCodUbigeoDestino(String codUbigeoDestino) {
		this.codUbigeoDestino = codUbigeoDestino;
	}
	public String getCodDepartamentoDestino() {
		return codDepartamentoDestino;
	}
	public void setCodDepartamentoDestino(String codDepartamentoDestino) {
		this.codDepartamentoDestino = codDepartamentoDestino;
	}
	public String getCodProvinciaDestino() {
		return codProvinciaDestino;
	}
	public void setCodProvinciaDestino(String codProvinciaDestino) {
		this.codProvinciaDestino = codProvinciaDestino;
	}
	public String getCodDistritoDestino() {
		return codDistritoDestino;
	}
	public void setCodDistritoDestino(String codDistritoDestino) {
		this.codDistritoDestino = codDistritoDestino;
	}
	public String getLocalidadDestino() {
		return localidadDestino;
	}
	public void setLocalidadDestino(String localidadDestino) {
		this.localidadDestino = localidadDestino;
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
	public String getActividad() {
		return actividad;
	}
	public void setActividad(String actividad) {
		this.actividad = actividad;
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
	public Long getNroDias() {
		return nroDias;
	}
	public void setNroDias(Long nroDias) {
		this.nroDias = nroDias;
	}
	public BigDecimal getMontoAlimentacion() {
		return montoAlimentacion;
	}
	public void setMontoAlimentacion(BigDecimal montoAlimentacion) {
		this.montoAlimentacion = montoAlimentacion;
	}
	public BigDecimal getMontoAlojamiento() {
		return montoAlojamiento;
	}
	public void setMontoAlojamiento(BigDecimal montoAlojamiento) {
		this.montoAlojamiento = montoAlojamiento;
	}
	public BigDecimal getMontoMovilidad() {
		return montoMovilidad;
	}
	public void setMontoMovilidad(BigDecimal montoMovilidad) {
		this.montoMovilidad = montoMovilidad;
	}
	public BigDecimal getTotalDesplazamiento() {
		return totalDesplazamiento;
	}
	public void setTotalDesplazamiento(BigDecimal totalDesplazamiento) {
		this.totalDesplazamiento = totalDesplazamiento;
	}
	public BigDecimal getSubTotalEtapa() {
		return subTotalEtapa;
	}
	public void setSubTotalEtapa(BigDecimal subTotalEtapa) {
		this.subTotalEtapa = subTotalEtapa;
	}
	public BigDecimal getTotalGeneral() {
		return totalGeneral;
	}
	public void setTotalGeneral(BigDecimal totalGeneral) {
		this.totalGeneral = totalGeneral;
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
	public List<FisePeriodoEnvio> getListaPeriodo() {
		return listaPeriodo;
	}
	public void setListaPeriodo(List<FisePeriodoEnvio> listaPeriodo) {
		this.listaPeriodo = listaPeriodo;
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
	public String getCodigoEmpresaHidden() {
		return codigoEmpresaHidden;
	}
	public void setCodigoEmpresaHidden(String codigoEmpresaHidden) {
		this.codigoEmpresaHidden = codigoEmpresaHidden;
	}
	public String getPeriodoEnvioHidden() {
		return periodoEnvioHidden;
	}
	public void setPeriodoEnvioHidden(String periodoEnvioHidden) {
		this.periodoEnvioHidden = periodoEnvioHidden;
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
	public String getDescDepartamentoOrigen() {
		return descDepartamentoOrigen;
	}
	public void setDescDepartamentoOrigen(String descDepartamentoOrigen) {
		this.descDepartamentoOrigen = descDepartamentoOrigen;
	}
	public String getDescProvinciaOrigen() {
		return descProvinciaOrigen;
	}
	public void setDescProvinciaOrigen(String descProvinciaOrigen) {
		this.descProvinciaOrigen = descProvinciaOrigen;
	}
	public String getDescDistritoOrigen() {
		return descDistritoOrigen;
	}
	public void setDescDistritoOrigen(String descDistritoOrigen) {
		this.descDistritoOrigen = descDistritoOrigen;
	}
	public String getDescDepartamentoDestino() {
		return descDepartamentoDestino;
	}
	public void setDescDepartamentoDestino(String descDepartamentoDestino) {
		this.descDepartamentoDestino = descDepartamentoDestino;
	}
	public String getDescProvinciaDestino() {
		return descProvinciaDestino;
	}
	public void setDescProvinciaDestino(String descProvinciaDestino) {
		this.descProvinciaDestino = descProvinciaDestino;
	}
	public String getDescDistritoDestino() {
		return descDistritoDestino;
	}
	public void setDescDistritoDestino(String descDistritoDestino) {
		this.descDistritoDestino = descDistritoDestino;
	}
	public String getCodDepartamentoOrigenHidden() {
		return codDepartamentoOrigenHidden;
	}
	public void setCodDepartamentoOrigenHidden(String codDepartamentoOrigenHidden) {
		this.codDepartamentoOrigenHidden = codDepartamentoOrigenHidden;
	}
	public String getCodProvinciaOrigenHidden() {
		return codProvinciaOrigenHidden;
	}
	public void setCodProvinciaOrigenHidden(String codProvinciaOrigenHidden) {
		this.codProvinciaOrigenHidden = codProvinciaOrigenHidden;
	}
	public String getCodDistritoOrigenHidden() {
		return codDistritoOrigenHidden;
	}
	public void setCodDistritoOrigenHidden(String codDistritoOrigenHidden) {
		this.codDistritoOrigenHidden = codDistritoOrigenHidden;
	}
	public String getCodDepartamentoDestinoHidden() {
		return codDepartamentoDestinoHidden;
	}
	public void setCodDepartamentoDestinoHidden(String codDepartamentoDestinoHidden) {
		this.codDepartamentoDestinoHidden = codDepartamentoDestinoHidden;
	}
	public String getCodProvinciaDestinoHidden() {
		return codProvinciaDestinoHidden;
	}
	public void setCodProvinciaDestinoHidden(String codProvinciaDestinoHidden) {
		this.codProvinciaDestinoHidden = codProvinciaDestinoHidden;
	}
	public String getCodDistritoDestinoHidden() {
		return codDistritoDestinoHidden;
	}
	public void setCodDistritoDestinoHidden(String codDistritoDestinoHidden) {
		this.codDistritoDestinoHidden = codDistritoDestinoHidden;
	}
	
	
}
