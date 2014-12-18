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

	private BigDecimal cfimon;

	private BigDecimal cfimontracan;

	private Long cfinumage;

	private Long cfinumusuben;

	private Long cfinumusuemp;

	private Long cfinumvaldcan;

	private Long cfinumvaldemi;

	private Long cfinumvalfcan;

	private Long cfinumvalfemi;

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
	
	@Transient
	private String descEmpresa;	
	
	@Transient
	private String descMesPresentacion;	
	
	@Transient
	private String descEstado;	
	

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

	public BigDecimal getCfimon() {
		return this.cfimon;
	}

	public void setCfimon(BigDecimal cfimon) {
		this.cfimon = cfimon;
	}

	public BigDecimal getCfimontracan() {
		return this.cfimontracan;
	}

	public void setCfimontracan(BigDecimal cfimontracan) {
		this.cfimontracan = cfimontracan;
	}

	public Long getCfinumage() {
		return this.cfinumage;
	}

	public void setCfinumage(Long cfinumage) {
		this.cfinumage = cfinumage;
	}

	public Long getCfinumusuben() {
		return this.cfinumusuben;
	}

	public void setCfinumusuben(Long cfinumusuben) {
		this.cfinumusuben = cfinumusuben;
	}

	public Long getCfinumusuemp() {
		return this.cfinumusuemp;
	}

	public void setCfinumusuemp(Long cfinumusuemp) {
		this.cfinumusuemp = cfinumusuemp;
	}

	public Long getCfinumvaldcan() {
		return this.cfinumvaldcan;
	}

	public void setCfinumvaldcan(Long cfinumvaldcan) {
		this.cfinumvaldcan = cfinumvaldcan;
	}

	public Long getCfinumvaldemi() {
		return this.cfinumvaldemi;
	}

	public void setCfinumvaldemi(Long cfinumvaldemi) {
		this.cfinumvaldemi = cfinumvaldemi;
	}

	public Long getCfinumvalfcan() {
		return this.cfinumvalfcan;
	}

	public void setCfinumvalfcan(Long cfinumvalfcan) {
		this.cfinumvalfcan = cfinumvalfcan;
	}

	public Long getCfinumvalfemi() {
		return this.cfinumvalfemi;
	}

	public void setCfinumvalfemi(Long cfinumvalfemi) {
		this.cfinumvalfemi = cfinumvalfemi;
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

	public String getDescEstado() {
		return descEstado;
	}

	public void setDescEstado(String descEstado) {
		this.descEstado = descEstado;
	}
	
	
	

}