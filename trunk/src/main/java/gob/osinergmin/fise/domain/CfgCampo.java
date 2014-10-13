package gob.osinergmin.fise.domain;

import java.io.Serializable;
import javax.persistence.*;
import java.math.BigDecimal;


/**
 * The persistent class for the CFG_CAMPO database table.
 * 
 */
@Entity
@Table(name="CFG_CAMPO", schema="REMISION")
@NamedQuery(name="CfgCampo.findAll", query="SELECT c FROM CfgCampo c")
public class CfgCampo implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@Column(name="ID_CAMPO")
	private long idCampo;

	private String clasificacion;

	@Column(name="COD_CAMPO")
	private String codCampo;

	@Column(name="COD_CAMPO_OFICIAL")
	private String codCampoOficial;

	@Column(name="COD_LEYENDA")
	private String codLeyenda;

	@Column(name="DATO_EJEMPLO")
	private String datoEjemplo;

	@Column(name="DESCRIPCION_CAMPO")
	private String descripcionCampo;

	@Column(name="EXCEL_WIDTH")
	private Long excelWidth;

	private BigDecimal longitud;

	@Column(name="NRO_CAMPO")
	private Long nroCampo;

	private String obligatorio;

	private String tipo;

	@Column(name="TIPO_DATO")
	private String tipoDato;

	@Column(name="TRANSF_OFIC")
	private String transfOfic;

	@Column(name="TRANSF_ORIG")
	private String transfOrig;

	@Column(name="TRANSF_VALID")
	private String transfValid;

	@Column(name="VALIDAR_PK")
	private String validarPk;

	//bi-directional many-to-one association to CfgTabla
	@ManyToOne(cascade={CascadeType.ALL})
	@JoinColumn(name="ID_TABLA")
	private CfgTabla cfgTabla;

	public CfgCampo() {
	}

	public long getIdCampo() {
		return this.idCampo;
	}

	public void setIdCampo(long idCampo) {
		this.idCampo = idCampo;
	}

	public String getClasificacion() {
		return this.clasificacion;
	}

	public void setClasificacion(String clasificacion) {
		this.clasificacion = clasificacion;
	}

	public String getCodCampo() {
		return this.codCampo;
	}

	public void setCodCampo(String codCampo) {
		this.codCampo = codCampo;
	}

	public String getCodCampoOficial() {
		return this.codCampoOficial;
	}

	public void setCodCampoOficial(String codCampoOficial) {
		this.codCampoOficial = codCampoOficial;
	}

	public String getCodLeyenda() {
		return this.codLeyenda;
	}

	public void setCodLeyenda(String codLeyenda) {
		this.codLeyenda = codLeyenda;
	}

	public String getDatoEjemplo() {
		return this.datoEjemplo;
	}

	public void setDatoEjemplo(String datoEjemplo) {
		this.datoEjemplo = datoEjemplo;
	}

	public String getDescripcionCampo() {
		return this.descripcionCampo;
	}

	public void setDescripcionCampo(String descripcionCampo) {
		this.descripcionCampo = descripcionCampo;
	}

	public Long getExcelWidth() {
		return this.excelWidth;
	}

	public void setExcelWidth(Long excelWidth) {
		this.excelWidth = excelWidth;
	}

	public BigDecimal getLongitud() {
		return this.longitud;
	}

	public void setLongitud(BigDecimal longitud) {
		this.longitud = longitud;
	}

	public Long getNroCampo() {
		return this.nroCampo;
	}

	public void setNroCampo(Long nroCampo) {
		this.nroCampo = nroCampo;
	}

	public String getObligatorio() {
		return this.obligatorio;
	}

	public void setObligatorio(String obligatorio) {
		this.obligatorio = obligatorio;
	}

	public String getTipo() {
		return this.tipo;
	}

	public void setTipo(String tipo) {
		this.tipo = tipo;
	}

	public String getTipoDato() {
		return this.tipoDato;
	}

	public void setTipoDato(String tipoDato) {
		this.tipoDato = tipoDato;
	}

	public String getTransfOfic() {
		return this.transfOfic;
	}

	public void setTransfOfic(String transfOfic) {
		this.transfOfic = transfOfic;
	}

	public String getTransfOrig() {
		return this.transfOrig;
	}

	public void setTransfOrig(String transfOrig) {
		this.transfOrig = transfOrig;
	}

	public String getTransfValid() {
		return this.transfValid;
	}

	public void setTransfValid(String transfValid) {
		this.transfValid = transfValid;
	}

	public String getValidarPk() {
		return this.validarPk;
	}

	public void setValidarPk(String validarPk) {
		this.validarPk = validarPk;
	}

	public CfgTabla getCfgTabla() {
		return this.cfgTabla;
	}

	public void setCfgTabla(CfgTabla cfgTabla) {
		this.cfgTabla = cfgTabla;
	}

}