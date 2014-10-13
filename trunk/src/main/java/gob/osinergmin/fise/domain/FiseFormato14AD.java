package gob.osinergmin.fise.domain;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.JoinColumns;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.persistence.Transient;


/**
 * The persistent class for the FISE_FORMATO_14A_D database table.
 * 
 */
@Entity
@Table(name="FISE_FORMATO_14A_D", schema="FISE")
@NamedQuery(name="FiseFormato14AD.findAll", query="SELECT f FROM FiseFormato14AD f")
public class FiseFormato14AD implements Serializable {
	private static final long serialVersionUID = 1L;

	@EmbeddedId
	private FiseFormato14ADPK id;

	@Column(name="COSTO_UNITARIO_EMPADRONAMIENTO")
	private BigDecimal costoUnitarioEmpadronamiento;

	@Column(name="COSTO_UNTITARIO_AGENTE_GLP")
	private BigDecimal costoUntitarioAgenteGlp;

	@Column(name="DIGITACION_FICHA_BENEF")
	private BigDecimal digitacionFichaBenef;

	@Column(name="ELABORACION_ARCHIVO_BENEF")
	private BigDecimal elaboracionArchivoBenef;

	@Temporal(TemporalType.TIMESTAMP)
	@Column(name="FECHA_ACTUALIZACION")
	private Date fechaActualizacion;

	@Temporal(TemporalType.TIMESTAMP)
	@Column(name="FECHA_CREACION")
	private Date fechaCreacion;

	@Column(name="IMPRESION_AFICHES")
	private BigDecimal impresionAfiches;

	@Column(name="IMPRESION_DECLARACION_JURADA")
	private BigDecimal impresionDeclaracionJurada;

	@Column(name="IMPRESION_ENTREGA_BANDEROLA")
	private BigDecimal impresionEntregaBanderola;

	@Column(name="IMPRESION_ESQUELA_INVITACION")
	private BigDecimal impresionEsquelaInvitacion;

	@Column(name="IMPRESION_FICHAS_VERIFICACION")
	private BigDecimal impresionFichasVerificacion;

	@Column(name="IMPRESION_VOLANTES")
	private BigDecimal impresionVolantes;

	@Column(name="NUMERO_AGENTES")
	private Long numeroAgentes;

	@Column(name="NUMERO_BENEF_EMPADRO_MES_DIC")
	private Long numeroBenefEmpadroMesDic;

	@Column(name="PROMOCION_CONVENIO_AG_AUT_GLP")
	private BigDecimal promocionConvenioAgAutGlp;

	@Column(name="REGISTRO_FIRMA_CONV_AG_AUT_GLP")
	private BigDecimal registroFirmaConvAgAutGlp;

	@Column(name="REPARTO_ESQUELA_INVITACION")
	private BigDecimal repartoEsquelaInvitacion;

	@Column(name="REPARTO_FOLLETO_POTENCIA_BENEF")
	private BigDecimal repartoFolletoPotenciaBenef;

	@Column(name="SPOT_PUBLICITARIO_RADIO")
	private BigDecimal spotPublicitarioRadio;

	@Column(name="SPOT_PUBLICITARIO_TV")
	private BigDecimal spotPublicitarioTv;

	@Column(name="TERMINAL_ACTUALIZACION")
	private String terminalActualizacion;

	@Column(name="TERMINAL_CREACION")
	private String terminalCreacion;

	@Column(name="TOTAL_COSTO_GESTION_RED_AG_GLP")
	private BigDecimal totalCostoGestionRedAgGlp;

	@Column(name="TOTAL_DIFUSION_INICIO_PRG_FISE")
	private BigDecimal totalDifusionInicioPrgFise;

	@Column(name="TOTAL_EMPADRONAMIENTO")
	private BigDecimal totalEmpadronamiento;

	@Column(name="USUARIO_ACTUALIZACION")
	private String usuarioActualizacion;

	@Column(name="USUARIO_CREACION")
	private String usuarioCreacion;

	@Column(name="VERIFICACION_INFORMACION")
	private BigDecimal verificacionInformacion;

	//bi-directional many-to-one association to FiseFormato14AC
	@Transient
	@ManyToOne(cascade={CascadeType.ALL})
	@JoinColumns({
		/*@JoinColumn(name="ANO_FIN_VIGENCIA", referencedColumnName="ANO_FIN_VIGENCIA"),
		@JoinColumn(name="ANO_INICIO_VIGENCIA", referencedColumnName="ANO_INICIO_VIGENCIA"),
		@JoinColumn(name="ANO_PRESENTACION", referencedColumnName="ANO_PRESENTACION"),
		@JoinColumn(name="COD_EMPRESA", referencedColumnName="COD_EMPRESA"),
		@JoinColumn(name="ETAPA", referencedColumnName="ETAPA"),
		@JoinColumn(name="MES_PRESENTACION", referencedColumnName="MES_PRESENTACION")*/
		})
	private FiseFormato14AC fiseFormato14AC;

	//bi-directional many-to-one association to FiseFormato14ADOb
	@OneToMany(mappedBy="fiseFormato14AD")
	private List<FiseFormato14ADOb> fiseFormato14ADObs;

	public FiseFormato14AD() {
	}

	public FiseFormato14ADPK getId() {
		return this.id;
	}

	public void setId(FiseFormato14ADPK id) {
		this.id = id;
	}

	public BigDecimal getCostoUnitarioEmpadronamiento() {
		return this.costoUnitarioEmpadronamiento;
	}

	public void setCostoUnitarioEmpadronamiento(BigDecimal costoUnitarioEmpadronamiento) {
		this.costoUnitarioEmpadronamiento = costoUnitarioEmpadronamiento;
	}

	public BigDecimal getCostoUntitarioAgenteGlp() {
		return this.costoUntitarioAgenteGlp;
	}

	public void setCostoUntitarioAgenteGlp(BigDecimal costoUntitarioAgenteGlp) {
		this.costoUntitarioAgenteGlp = costoUntitarioAgenteGlp;
	}

	public BigDecimal getDigitacionFichaBenef() {
		return this.digitacionFichaBenef;
	}

	public void setDigitacionFichaBenef(BigDecimal digitacionFichaBenef) {
		this.digitacionFichaBenef = digitacionFichaBenef;
	}

	public BigDecimal getElaboracionArchivoBenef() {
		return this.elaboracionArchivoBenef;
	}

	public void setElaboracionArchivoBenef(BigDecimal elaboracionArchivoBenef) {
		this.elaboracionArchivoBenef = elaboracionArchivoBenef;
	}

	public Date getFechaActualizacion() {
		return this.fechaActualizacion;
	}

	public void setFechaActualizacion(Date fechaActualizacion) {
		this.fechaActualizacion = fechaActualizacion;
	}

	public Date getFechaCreacion() {
		return this.fechaCreacion;
	}

	public void setFechaCreacion(Date fechaCreacion) {
		this.fechaCreacion = fechaCreacion;
	}

	public BigDecimal getImpresionAfiches() {
		return this.impresionAfiches;
	}

	public void setImpresionAfiches(BigDecimal impresionAfiches) {
		this.impresionAfiches = impresionAfiches;
	}

	public BigDecimal getImpresionDeclaracionJurada() {
		return this.impresionDeclaracionJurada;
	}

	public void setImpresionDeclaracionJurada(BigDecimal impresionDeclaracionJurada) {
		this.impresionDeclaracionJurada = impresionDeclaracionJurada;
	}

	public BigDecimal getImpresionEntregaBanderola() {
		return this.impresionEntregaBanderola;
	}

	public void setImpresionEntregaBanderola(BigDecimal impresionEntregaBanderola) {
		this.impresionEntregaBanderola = impresionEntregaBanderola;
	}

	public BigDecimal getImpresionEsquelaInvitacion() {
		return this.impresionEsquelaInvitacion;
	}

	public void setImpresionEsquelaInvitacion(BigDecimal impresionEsquelaInvitacion) {
		this.impresionEsquelaInvitacion = impresionEsquelaInvitacion;
	}

	public BigDecimal getImpresionFichasVerificacion() {
		return this.impresionFichasVerificacion;
	}

	public void setImpresionFichasVerificacion(BigDecimal impresionFichasVerificacion) {
		this.impresionFichasVerificacion = impresionFichasVerificacion;
	}

	public BigDecimal getImpresionVolantes() {
		return this.impresionVolantes;
	}

	public void setImpresionVolantes(BigDecimal impresionVolantes) {
		this.impresionVolantes = impresionVolantes;
	}

	public Long getNumeroAgentes() {
		return this.numeroAgentes;
	}

	public void setNumeroAgentes(Long numeroAgentes) {
		this.numeroAgentes = numeroAgentes;
	}

	public Long getNumeroBenefEmpadroMesDic() {
		return this.numeroBenefEmpadroMesDic;
	}

	public void setNumeroBenefEmpadroMesDic(Long numeroBenefEmpadroMesDic) {
		this.numeroBenefEmpadroMesDic = numeroBenefEmpadroMesDic;
	}

	public BigDecimal getPromocionConvenioAgAutGlp() {
		return this.promocionConvenioAgAutGlp;
	}

	public void setPromocionConvenioAgAutGlp(BigDecimal promocionConvenioAgAutGlp) {
		this.promocionConvenioAgAutGlp = promocionConvenioAgAutGlp;
	}

	public BigDecimal getRegistroFirmaConvAgAutGlp() {
		return this.registroFirmaConvAgAutGlp;
	}

	public void setRegistroFirmaConvAgAutGlp(BigDecimal registroFirmaConvAgAutGlp) {
		this.registroFirmaConvAgAutGlp = registroFirmaConvAgAutGlp;
	}

	public BigDecimal getRepartoEsquelaInvitacion() {
		return this.repartoEsquelaInvitacion;
	}

	public void setRepartoEsquelaInvitacion(BigDecimal repartoEsquelaInvitacion) {
		this.repartoEsquelaInvitacion = repartoEsquelaInvitacion;
	}

	public BigDecimal getRepartoFolletoPotenciaBenef() {
		return this.repartoFolletoPotenciaBenef;
	}

	public void setRepartoFolletoPotenciaBenef(BigDecimal repartoFolletoPotenciaBenef) {
		this.repartoFolletoPotenciaBenef = repartoFolletoPotenciaBenef;
	}

	public BigDecimal getSpotPublicitarioRadio() {
		return this.spotPublicitarioRadio;
	}

	public void setSpotPublicitarioRadio(BigDecimal spotPublicitarioRadio) {
		this.spotPublicitarioRadio = spotPublicitarioRadio;
	}

	public BigDecimal getSpotPublicitarioTv() {
		return this.spotPublicitarioTv;
	}

	public void setSpotPublicitarioTv(BigDecimal spotPublicitarioTv) {
		this.spotPublicitarioTv = spotPublicitarioTv;
	}

	public String getTerminalActualizacion() {
		return this.terminalActualizacion;
	}

	public void setTerminalActualizacion(String terminalActualizacion) {
		this.terminalActualizacion = terminalActualizacion;
	}

	public String getTerminalCreacion() {
		return this.terminalCreacion;
	}

	public void setTerminalCreacion(String terminalCreacion) {
		this.terminalCreacion = terminalCreacion;
	}

	public BigDecimal getTotalCostoGestionRedAgGlp() {
		return this.totalCostoGestionRedAgGlp;
	}

	public void setTotalCostoGestionRedAgGlp(BigDecimal totalCostoGestionRedAgGlp) {
		this.totalCostoGestionRedAgGlp = totalCostoGestionRedAgGlp;
	}

	public BigDecimal getTotalDifusionInicioPrgFise() {
		return this.totalDifusionInicioPrgFise;
	}

	public void setTotalDifusionInicioPrgFise(BigDecimal totalDifusionInicioPrgFise) {
		this.totalDifusionInicioPrgFise = totalDifusionInicioPrgFise;
	}

	public BigDecimal getTotalEmpadronamiento() {
		return this.totalEmpadronamiento;
	}

	public void setTotalEmpadronamiento(BigDecimal totalEmpadronamiento) {
		this.totalEmpadronamiento = totalEmpadronamiento;
	}

	public String getUsuarioActualizacion() {
		return this.usuarioActualizacion;
	}

	public void setUsuarioActualizacion(String usuarioActualizacion) {
		this.usuarioActualizacion = usuarioActualizacion;
	}

	public String getUsuarioCreacion() {
		return this.usuarioCreacion;
	}

	public void setUsuarioCreacion(String usuarioCreacion) {
		this.usuarioCreacion = usuarioCreacion;
	}

	public BigDecimal getVerificacionInformacion() {
		return this.verificacionInformacion;
	}

	public void setVerificacionInformacion(BigDecimal verificacionInformacion) {
		this.verificacionInformacion = verificacionInformacion;
	}

	public FiseFormato14AC getFiseFormato14AC() {
		return this.fiseFormato14AC;
	}

	public void setFiseFormato14AC(FiseFormato14AC fiseFormato14AC) {
		this.fiseFormato14AC = fiseFormato14AC;
	}

	public List<FiseFormato14ADOb> getFiseFormato14ADObs() {
		return this.fiseFormato14ADObs;
	}

	public void setFiseFormato14ADObs(List<FiseFormato14ADOb> fiseFormato14ADObs) {
		this.fiseFormato14ADObs = fiseFormato14ADObs;
	}

	public FiseFormato14ADOb addFiseFormato14ADOb(FiseFormato14ADOb fiseFormato14ADOb) {
		getFiseFormato14ADObs().add(fiseFormato14ADOb);
		fiseFormato14ADOb.setFiseFormato14AD(this);

		return fiseFormato14ADOb;
	}

	public FiseFormato14ADOb removeFiseFormato14ADOb(FiseFormato14ADOb fiseFormato14ADOb) {
		getFiseFormato14ADObs().remove(fiseFormato14ADOb);
		fiseFormato14ADOb.setFiseFormato14AD(null);

		return fiseFormato14ADOb;
	}

}