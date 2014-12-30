package gob.osinergmin.fise.domain;

import java.io.Serializable;

import javax.persistence.*;

import java.math.BigDecimal;
import java.util.Date;


/**
 * The persistent class for the FISE_MCARGOFIJO database table.
 * 
 */
@Entity
@Table(name="FISE_MCARGOFIJO")
public class FiseMcargofijo implements Serializable {
	private static final long serialVersionUID = 1L;

	@EmbeddedId
	private FiseMcargofijoPK id;

	private Integer cfiapliigv;

	private String cficom;

	private String cfidoc;

	private String cfidocrec;

	@Temporal(TemporalType.DATE)
	private Date cfifecinf;

	@Temporal(TemporalType.DATE)
	private Date cfifecrec;

	private BigDecimal cfiigv;
	
	@Column(name="CFIMON_RURAL")
	private BigDecimal cfimonRural;	

	@Column(name="CFIMON_URB_PROV")
	private BigDecimal cfimonUrbProv;
	
	@Column(name="CFIMON_URB_LIMA")
	private BigDecimal cfimonUrbLima;
	

	private BigDecimal cfimontracan;

	@Column(name="CFINUMAGE_RURAL")
	private Long cfinumageRural;
	
	@Column(name="CFINUMAGE_URB_PROV")
	private Long cfinumageUrbProv;
	
	@Column(name="CFINUMAGE_URB_LIMA")
	private Long cfinumageUrbLima;

	
	@Column(name="CFINUMUSUBEN_RURAL")
	private Long cfinumusubenRural;
	
	@Column(name="CFINUMUSUBEN_URB_PROV")
	private Long cfinumusubenUrbProv;
	
	@Column(name="CFINUMUSUBEN_URB_LIMA")
	private Long cfinumusubenUrbLima;
	
	
	@Column(name="CFINUMUSUEMP_RURAL")
	private Long cfinumusuempRural;
	
	@Column(name="CFINUMUSUEMP_URB_PROV")
	private Long cfinumusuempUrbProv;
	
	@Column(name="CFINUMUSUEMP_URB_LIMA")
	private Long cfinumusuempUrbLima;
	

	@Column(name="CFINUMVALDCAN_RURAL")
	private Long cfinumvaldcanRural;
	
	@Column(name="CFINUMVALDCAN_URB_PROV")
	private Long cfinumvaldcanUrbProv;
	
	@Column(name="CFINUMVALDCAN_URB_LIMA")
	private Long cfinumvaldcanUrbLima;

	
	@Column(name="CFINUMVALDEMI_RURAL")
	private Long cfinumvaldemiRural;
	
	@Column(name="CFINUMVALDEMI_URB_PROV")
	private Long cfinumvaldemiUrbProv;
	
	@Column(name="CFINUMVALDEMI_URB_LIMA")
	private Long cfinumvaldemiUrbLima;

	
	@Column(name="CFINUMVALFCAN_RURAL")
	private Long cfinumvalfcanRural;
	
	@Column(name="CFINUMVALFCAN_URB_PROV")
	private Long cfinumvalfcanUrbProv;
	
	@Column(name="CFINUMVALFCAN_URB_LIMA")
	private Long cfinumvalfcanUrbLima;
	
	
	@Column(name="CFINUMVALFEMI_RURAL")
	private Long cfinumvalfemiRural;
	
	@Column(name="CFINUMVALFEMI_URB_PROV")
	private Long cfinumvalfemiUrbProv;
	
	@Column(name="CFINUMVALFEMI_URB_LIMA")
	private Long cfinumvalfemiUrbLima;
	

	@Temporal(TemporalType.TIMESTAMP)
	@Column(name="FECHA_ACTUALIZACION")
	private Date fechaActualizacion;

	@Temporal(TemporalType.TIMESTAMP)
	@Column(name="FECHA_CREACION")
	private Date fechaCreacion;

	private Integer scficod;

	@Column(name="TERMINAL_ACTUALIZACION")
	private String terminalActualizacion;

	@Column(name="TERMINAL_CREACION")
	private String terminalCreacion;

	@Column(name="USUARIO_ACTUALIZACION")
	private String usuarioActualizacion;

	@Column(name="USUARIO_CREACION")
	private String usuarioCreacion;
	
	
	public FiseMcargofijo() {
	}

	public FiseMcargofijoPK getId() {
		return this.id;
	}

	public void setId(FiseMcargofijoPK id) {
		this.id = id;
	}

	public Integer getCfiapliigv() {
		return this.cfiapliigv;
	}

	public void setCfiapliigv(Integer cfiapliigv) {
		this.cfiapliigv = cfiapliigv;
	}

	public String getCficom() {
		return this.cficom;
	}

	public void setCficom(String cficom) {
		this.cficom = cficom;
	}

	public String getCfidoc() {
		return this.cfidoc;
	}

	public void setCfidoc(String cfidoc) {
		this.cfidoc = cfidoc;
	}

	public String getCfidocrec() {
		return this.cfidocrec;
	}

	public void setCfidocrec(String cfidocrec) {
		this.cfidocrec = cfidocrec;
	}

	public Date getCfifecinf() {
		return this.cfifecinf;
	}

	public void setCfifecinf(Date cfifecinf) {
		this.cfifecinf = cfifecinf;
	}

	public Date getCfifecrec() {
		return this.cfifecrec;
	}

	public void setCfifecrec(Date cfifecrec) {
		this.cfifecrec = cfifecrec;
	}

	public BigDecimal getCfiigv() {
		return this.cfiigv;
	}

	public void setCfiigv(BigDecimal cfiigv) {
		this.cfiigv = cfiigv;
	}

	public BigDecimal getCfimontracan() {
		return this.cfimontracan;
	}

	public void setCfimontracan(BigDecimal cfimontracan) {
		this.cfimontracan = cfimontracan;
	}

	public Long getCfinumusubenRural() {
		return cfinumusubenRural;
	}

	public void setCfinumusubenRural(Long cfinumusubenRural) {
		this.cfinumusubenRural = cfinumusubenRural;
	}	

	public BigDecimal getCfimonRural() {
		return cfimonRural;
	}

	public void setCfimonRural(BigDecimal cfimonRural) {
		this.cfimonRural = cfimonRural;
	}

	public Long getCfinumageRural() {
		return cfinumageRural;
	}

	public void setCfinumageRural(Long cfinumageRural) {
		this.cfinumageRural = cfinumageRural;
	}

	public Long getCfinumusuempRural() {
		return cfinumusuempRural;
	}

	public void setCfinumusuempRural(Long cfinumusuempRural) {
		this.cfinumusuempRural = cfinumusuempRural;
	}

	public Long getCfinumvaldcanRural() {
		return cfinumvaldcanRural;
	}

	public void setCfinumvaldcanRural(Long cfinumvaldcanRural) {
		this.cfinumvaldcanRural = cfinumvaldcanRural;
	}

	public Long getCfinumvaldemiRural() {
		return cfinumvaldemiRural;
	}

	public void setCfinumvaldemiRural(Long cfinumvaldemiRural) {
		this.cfinumvaldemiRural = cfinumvaldemiRural;
	}

	public Long getCfinumvalfcanRural() {
		return cfinumvalfcanRural;
	}

	public void setCfinumvalfcanRural(Long cfinumvalfcanRural) {
		this.cfinumvalfcanRural = cfinumvalfcanRural;
	}

	public Long getCfinumvalfemiRural() {
		return cfinumvalfemiRural;
	}

	public void setCfinumvalfemiRural(Long cfinumvalfemiRural) {
		this.cfinumvalfemiRural = cfinumvalfemiRural;
	}	

	public BigDecimal getCfimonUrbProv() {
		return cfimonUrbProv;
	}

	public void setCfimonUrbProv(BigDecimal cfimonUrbProv) {
		this.cfimonUrbProv = cfimonUrbProv;
	}

	public BigDecimal getCfimonUrbLima() {
		return cfimonUrbLima;
	}

	public void setCfimonUrbLima(BigDecimal cfimonUrbLima) {
		this.cfimonUrbLima = cfimonUrbLima;
	}

	public Long getCfinumageUrbProv() {
		return cfinumageUrbProv;
	}

	public void setCfinumageUrbProv(Long cfinumageUrbProv) {
		this.cfinumageUrbProv = cfinumageUrbProv;
	}

	public Long getCfinumageUrbLima() {
		return cfinumageUrbLima;
	}

	public void setCfinumageUrbLima(Long cfinumageUrbLima) {
		this.cfinumageUrbLima = cfinumageUrbLima;
	}

	public Long getCfinumusubenUrbProv() {
		return cfinumusubenUrbProv;
	}

	public void setCfinumusubenUrbProv(Long cfinumusubenUrbProv) {
		this.cfinumusubenUrbProv = cfinumusubenUrbProv;
	}

	public Long getCfinumusubenUrbLima() {
		return cfinumusubenUrbLima;
	}

	public void setCfinumusubenUrbLima(Long cfinumusubenUrbLima) {
		this.cfinumusubenUrbLima = cfinumusubenUrbLima;
	}

	public Long getCfinumusuempUrbProv() {
		return cfinumusuempUrbProv;
	}

	public void setCfinumusuempUrbProv(Long cfinumusuempUrbProv) {
		this.cfinumusuempUrbProv = cfinumusuempUrbProv;
	}

	public Long getCfinumusuempUrbLima() {
		return cfinumusuempUrbLima;
	}

	public void setCfinumusuempUrbLima(Long cfinumusuempUrbLima) {
		this.cfinumusuempUrbLima = cfinumusuempUrbLima;
	}

	public Long getCfinumvaldcanUrbProv() {
		return cfinumvaldcanUrbProv;
	}

	public void setCfinumvaldcanUrbProv(Long cfinumvaldcanUrbProv) {
		this.cfinumvaldcanUrbProv = cfinumvaldcanUrbProv;
	}

	public Long getCfinumvaldcanUrbLima() {
		return cfinumvaldcanUrbLima;
	}

	public void setCfinumvaldcanUrbLima(Long cfinumvaldcanUrbLima) {
		this.cfinumvaldcanUrbLima = cfinumvaldcanUrbLima;
	}

	public Long getCfinumvaldemiUrbProv() {
		return cfinumvaldemiUrbProv;
	}

	public void setCfinumvaldemiUrbProv(Long cfinumvaldemiUrbProv) {
		this.cfinumvaldemiUrbProv = cfinumvaldemiUrbProv;
	}

	public Long getCfinumvaldemiUrbLima() {
		return cfinumvaldemiUrbLima;
	}

	public void setCfinumvaldemiUrbLima(Long cfinumvaldemiUrbLima) {
		this.cfinumvaldemiUrbLima = cfinumvaldemiUrbLima;
	}

	public Long getCfinumvalfcanUrbProv() {
		return cfinumvalfcanUrbProv;
	}

	public void setCfinumvalfcanUrbProv(Long cfinumvalfcanUrbProv) {
		this.cfinumvalfcanUrbProv = cfinumvalfcanUrbProv;
	}

	public Long getCfinumvalfcanUrbLima() {
		return cfinumvalfcanUrbLima;
	}

	public void setCfinumvalfcanUrbLima(Long cfinumvalfcanUrbLima) {
		this.cfinumvalfcanUrbLima = cfinumvalfcanUrbLima;
	}

	public Long getCfinumvalfemiUrbProv() {
		return cfinumvalfemiUrbProv;
	}

	public void setCfinumvalfemiUrbProv(Long cfinumvalfemiUrbProv) {
		this.cfinumvalfemiUrbProv = cfinumvalfemiUrbProv;
	}

	public Long getCfinumvalfemiUrbLima() {
		return cfinumvalfemiUrbLima;
	}

	public void setCfinumvalfemiUrbLima(Long cfinumvalfemiUrbLima) {
		this.cfinumvalfemiUrbLima = cfinumvalfemiUrbLima;
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

	public Integer getScficod() {
		return this.scficod;
	}

	public void setScficod(Integer scficod) {
		this.scficod = scficod;
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

}