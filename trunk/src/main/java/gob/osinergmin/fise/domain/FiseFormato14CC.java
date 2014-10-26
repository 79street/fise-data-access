package gob.osinergmin.fise.domain;

import java.io.Serializable;
import javax.persistence.*;
import java.math.BigDecimal;
import java.util.Date;
import java.util.List;


/**
 * The persistent class for the FISE_FORMATO_14C_C database table.
 * 
 */
@Entity
@Table(name="FISE_FORMATO_14C_C")
public class FiseFormato14CC implements Serializable {
	private static final long serialVersionUID = 1L;

	@EmbeddedId
	private FiseFormato14CCPK id;

	@Column(name="COSTO_PROM_MEN_RURAL")
	private BigDecimal costoPromMenRural;

	@Column(name="COSTO_PROM_MEN_URB_LIMA")
	private BigDecimal costoPromMenUrbLima;

	@Column(name="COSTO_PROM_MEN_URB_PROV")
	private BigDecimal costoPromMenUrbProv;

	@Temporal(TemporalType.DATE)
	@Column(name="FECHA_ACTUALIZACION")
	private Date fechaActualizacion;

	@Temporal(TemporalType.DATE)
	@Column(name="FECHA_CREACION")
	private Date fechaCreacion;

	@Temporal(TemporalType.DATE)
	@Column(name="FECHA_ENVIO_DEFINITIVO")
	private Date fechaEnvioDefinitivo;

	@Column(name="NOMBRE_ARCHIVO_EXCEL")
	private String nombreArchivoExcel;

	@Column(name="NOMBRE_ARCHIVO_TEXTO")
	private String nombreArchivoTexto;

	@Column(name="NOMBRE_SEDE")
	private String nombreSede;

	@Column(name="NUM_BENEF_EMP_PER_ANT_RURAL")
	private Integer numBenefEmpPerAntRural;

	@Column(name="NUM_BENEF_EMP_PER_ANT_URB_LIMA")
	private Integer numBenefEmpPerAntUrbLima;

	@Column(name="NUM_BENEF_EMP_PER_ANT_URB_PROV")
	private Integer numBenefEmpPerAntUrbProv;

	@Column(name="TERMINAL_ACTUALIZACION")
	private String terminalActualizacion;

	@Column(name="TERMINAL_CREACION")
	private String terminalCreacion;

	@Column(name="USUARIO_ACTUALIZACION")
	private String usuarioActualizacion;

	@Column(name="USUARIO_CREACION")
	private String usuarioCreacion;

	//bi-directional many-to-one association to FiseGrupoInformacion
	@ManyToOne(fetch=FetchType.LAZY)
	@JoinColumn(name="ID_GRUPO_INFORMACION",nullable=false, insertable=false, updatable=false)
	private FiseGrupoInformacion fiseGrupoInformacion;

	//bi-directional many-to-one association to FiseFormato14cD
	@OneToMany(mappedBy="fiseFormato14cC")
	private List<FiseFormato14CD> fiseFormato14cDs;

	public FiseFormato14CC() {
	}

	public FiseFormato14CCPK getId() {
		return this.id;
	}

	public void setId(FiseFormato14CCPK id) {
		this.id = id;
	}

	public BigDecimal getCostoPromMenRural() {
		return this.costoPromMenRural;
	}

	public void setCostoPromMenRural(BigDecimal costoPromMenRural) {
		this.costoPromMenRural = costoPromMenRural;
	}

	public BigDecimal getCostoPromMenUrbLima() {
		return this.costoPromMenUrbLima;
	}

	public void setCostoPromMenUrbLima(BigDecimal costoPromMenUrbLima) {
		this.costoPromMenUrbLima = costoPromMenUrbLima;
	}

	public BigDecimal getCostoPromMenUrbProv() {
		return this.costoPromMenUrbProv;
	}

	public void setCostoPromMenUrbProv(BigDecimal costoPromMenUrbProv) {
		this.costoPromMenUrbProv = costoPromMenUrbProv;
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

	public Date getFechaEnvioDefinitivo() {
		return this.fechaEnvioDefinitivo;
	}

	public void setFechaEnvioDefinitivo(Date fechaEnvioDefinitivo) {
		this.fechaEnvioDefinitivo = fechaEnvioDefinitivo;
	}

	public String getNombreArchivoExcel() {
		return this.nombreArchivoExcel;
	}

	public void setNombreArchivoExcel(String nombreArchivoExcel) {
		this.nombreArchivoExcel = nombreArchivoExcel;
	}

	public String getNombreArchivoTexto() {
		return this.nombreArchivoTexto;
	}

	public void setNombreArchivoTexto(String nombreArchivoTexto) {
		this.nombreArchivoTexto = nombreArchivoTexto;
	}

	public String getNombreSede() {
		return this.nombreSede;
	}

	public void setNombreSede(String nombreSede) {
		this.nombreSede = nombreSede;
	}

	public Integer getNumBenefEmpPerAntRural() {
		return this.numBenefEmpPerAntRural;
	}

	public void setNumBenefEmpPerAntRural(Integer numBenefEmpPerAntRural) {
		this.numBenefEmpPerAntRural = numBenefEmpPerAntRural;
	}

	public Integer getNumBenefEmpPerAntUrbLima() {
		return this.numBenefEmpPerAntUrbLima;
	}

	public void setNumBenefEmpPerAntUrbLima(Integer numBenefEmpPerAntUrbLima) {
		this.numBenefEmpPerAntUrbLima = numBenefEmpPerAntUrbLima;
	}

	public Integer getNumBenefEmpPerAntUrbProv() {
		return this.numBenefEmpPerAntUrbProv;
	}

	public void setNumBenefEmpPerAntUrbProv(Integer numBenefEmpPerAntUrbProv) {
		this.numBenefEmpPerAntUrbProv = numBenefEmpPerAntUrbProv;
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

	public FiseGrupoInformacion getFiseGrupoInformacion() {
		return this.fiseGrupoInformacion;
	}

	public void setFiseGrupoInformacion(FiseGrupoInformacion fiseGrupoInformacion) {
		this.fiseGrupoInformacion = fiseGrupoInformacion;
	}

	public List<FiseFormato14CD> getFiseFormato14cDs() {
		return this.fiseFormato14cDs;
	}

	public void setFiseFormato14cDs(List<FiseFormato14CD> fiseFormato14cDs) {
		this.fiseFormato14cDs = fiseFormato14cDs;
	}

	public FiseFormato14CD addFiseFormato14cD(FiseFormato14CD fiseFormato14cD) {
		getFiseFormato14cDs().add(fiseFormato14cD);
		fiseFormato14cD.setFiseFormato14cC(this);

		return fiseFormato14cD;
	}

	public FiseFormato14CD removeFiseFormato14cD(FiseFormato14CD fiseFormato14cD) {
		getFiseFormato14cDs().remove(fiseFormato14cD);
		fiseFormato14cD.setFiseFormato14cC(null);

		return fiseFormato14cD;
	}

}