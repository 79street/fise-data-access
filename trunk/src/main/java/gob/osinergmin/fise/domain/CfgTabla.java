package gob.osinergmin.fise.domain;

import java.io.Serializable;
import javax.persistence.*;
import java.util.List;


/**
 * The persistent class for the CFG_TABLA database table.
 * 
 */
@Entity
@Table(name="CFG_TABLA", schema="REMISION")
@NamedQuery(name="CfgTabla.findAll", query="SELECT c FROM CfgTabla c")
public class CfgTabla implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@Column(name="ID_TABLA")
	private long idTabla;

	@Column(name="CAMPO_EMPRESA")
	private String campoEmpresa;

	@Column(name="CAMPO_GRUPO_HIS")
	private String campoGrupoHis;

	@Column(name="CAMPO_PERIODO")
	private String campoPeriodo;

	@Column(name="COD_PROC_SUPERVISION")
	private String codProcSupervision;

	@Column(name="COD_TABLA")
	private String codTabla;

	@Column(name="COD_TABLA_OFICIAL")
	private String codTablaOficial;

	@Column(name="DESCRIPCION_TABLA")
	private String descripcionTabla;

	@Column(name="ESTADO_TABLA")
	private String estadoTabla;

	@Column(name="FLAG_PERIODO_ANTERIOR")
	private String flagPeriodoAnterior;

	private String grupo;

	@Column(name="NOMBRE_ARCHIVO_EXCEL")
	private String nombreArchivoExcel;

	@Column(name="NOMBRE_CORTO")
	private String nombreCorto;

	//bi-directional many-to-one association to CfgCampo
	@OneToMany(mappedBy="cfgTabla", cascade={CascadeType.ALL})
	private List<CfgCampo> cfgCampos;

	public CfgTabla() {
	}

	public long getIdTabla() {
		return this.idTabla;
	}

	public void setIdTabla(long idTabla) {
		this.idTabla = idTabla;
	}

	public String getCampoEmpresa() {
		return this.campoEmpresa;
	}

	public void setCampoEmpresa(String campoEmpresa) {
		this.campoEmpresa = campoEmpresa;
	}

	public String getCampoGrupoHis() {
		return this.campoGrupoHis;
	}

	public void setCampoGrupoHis(String campoGrupoHis) {
		this.campoGrupoHis = campoGrupoHis;
	}

	public String getCampoPeriodo() {
		return this.campoPeriodo;
	}

	public void setCampoPeriodo(String campoPeriodo) {
		this.campoPeriodo = campoPeriodo;
	}

	public String getCodProcSupervision() {
		return this.codProcSupervision;
	}

	public void setCodProcSupervision(String codProcSupervision) {
		this.codProcSupervision = codProcSupervision;
	}

	public String getCodTabla() {
		return this.codTabla;
	}

	public void setCodTabla(String codTabla) {
		this.codTabla = codTabla;
	}

	public String getCodTablaOficial() {
		return this.codTablaOficial;
	}

	public void setCodTablaOficial(String codTablaOficial) {
		this.codTablaOficial = codTablaOficial;
	}

	public String getDescripcionTabla() {
		return this.descripcionTabla;
	}

	public void setDescripcionTabla(String descripcionTabla) {
		this.descripcionTabla = descripcionTabla;
	}

	public String getEstadoTabla() {
		return this.estadoTabla;
	}

	public void setEstadoTabla(String estadoTabla) {
		this.estadoTabla = estadoTabla;
	}

	public String getFlagPeriodoAnterior() {
		return this.flagPeriodoAnterior;
	}

	public void setFlagPeriodoAnterior(String flagPeriodoAnterior) {
		this.flagPeriodoAnterior = flagPeriodoAnterior;
	}

	public String getGrupo() {
		return this.grupo;
	}

	public void setGrupo(String grupo) {
		this.grupo = grupo;
	}

	public String getNombreArchivoExcel() {
		return this.nombreArchivoExcel;
	}

	public void setNombreArchivoExcel(String nombreArchivoExcel) {
		this.nombreArchivoExcel = nombreArchivoExcel;
	}

	public String getNombreCorto() {
		return this.nombreCorto;
	}

	public void setNombreCorto(String nombreCorto) {
		this.nombreCorto = nombreCorto;
	}

	public List<CfgCampo> getCfgCampos() {
		return this.cfgCampos;
	}

	public void setCfgCampos(List<CfgCampo> cfgCampos) {
		this.cfgCampos = cfgCampos;
	}

	public CfgCampo addCfgCampo(CfgCampo cfgCampo) {
		getCfgCampos().add(cfgCampo);
		cfgCampo.setCfgTabla(this);

		return cfgCampo;
	}

	public CfgCampo removeCfgCampo(CfgCampo cfgCampo) {
		getCfgCampos().remove(cfgCampo);
		cfgCampo.setCfgTabla(null);

		return cfgCampo;
	}

}