package gob.osinergmin.fise.domain;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.persistence.Transient;


/**
 * The persistent class for the FISE_GRUPO_INFORMACION database table.
 * 
 */
@Entity
@Table(name="FISE_GRUPO_INFORMACION")
@NamedQuery(name="FiseGrupoInformacion.findAll", query="SELECT f FROM FiseGrupoInformacion f")
public class FiseGrupoInformacion implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.SEQUENCE, generator="my_entity_seq_grupo_inf")
	@SequenceGenerator(name="my_entity_seq_grupo_inf",sequenceName="FISE_GRUPO_INF_SEQ", allocationSize = 1)
	@Column(name="ID_GRUPO_INFORMACION")
	private long idGrupoInformacion;

	@Column(name="ANO_PRESENTACION")
	private Long anoPresentacion;

	private String descripcion;

	private Integer estado;
	
	@Transient
	private String descEstado;

	@Temporal(TemporalType.TIMESTAMP)
	@Column(name="FECHA_ACTUALIZACION")
	private Date fechaActualizacion;

	@Temporal(TemporalType.TIMESTAMP)
	@Column(name="FECHA_CREACION")
	private Date fechaCreacion;

	@Column(name="MES_PRESENTACION")
	private Long mesPresentacion;

	@Column(name="TERMINAL_ACTUALIZACION")
	private String terminalActualizacion;

	@Column(name="TERMINAL_CREACION")
	private String terminalCreacion;

	@Column(name="USUARIO_ACTUALIZACION")
	private String usuarioActualizacion;

	@Column(name="USUARIO_CREACION")
	private String usuarioCreacion;
	
	private String tipo;

	//bi-directional many-to-one association to FiseFormato12AC
	@OneToMany(mappedBy="fiseGrupoInformacion")
	private List<FiseFormato12AC> fiseFormato12ACs;
	
	//bi-directional many-to-one association to FiseFormato12AC
	@OneToMany(mappedBy="fiseGrupoInformacion")
	private List<FiseFormato12CC> fiseFormato12CCs;
	
	//bi-directional many-to-one association to FiseFormato13AC
	@OneToMany(mappedBy="fiseGrupoInformacion")
	private List<FiseFormato13AC> fiseFormato13ACs;
	
	//bi-directional many-to-one association to FiseFormato14AC
	@OneToMany(mappedBy="fiseGrupoInformacion")
	private List<FiseFormato14AC> fiseFormato14ACs;
		
	//bi-directional many-to-one association to FiseFormato14BC
	@OneToMany(mappedBy="fiseGrupoInformacion")
	private List<FiseFormato14BC> fiseFormato14BCs;
	
	//bi-directional many-to-one association to FiseFormato14CC
	@OneToMany(mappedBy="fiseGrupoInformacion")
	private List<FiseFormato14CC> fiseFormato14CCs;

	public FiseGrupoInformacion() {
	}

	public Long getIdGrupoInformacion() {
		return this.idGrupoInformacion;
	}

	public void setIdGrupoInformacion(Long idGrupoInformacion) {
		this.idGrupoInformacion = idGrupoInformacion;
	}

	public Long getAnoPresentacion() {
		return this.anoPresentacion;
	}

	public void setAnoPresentacion(Long anoPresentacion) {
		this.anoPresentacion = anoPresentacion;
	}

	public String getDescripcion() {
		return this.descripcion;
	}

	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}

	public Integer getEstado() {
		return this.estado;
	}

	public void setEstado(Integer estado) {
		this.estado = estado;
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

	public Long getMesPresentacion() {
		return this.mesPresentacion;
	}

	public void setMesPresentacion(Long mesPresentacion) {
		this.mesPresentacion = mesPresentacion;
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

	public List<FiseFormato12AC> getFiseFormato12ACs() {
		return this.fiseFormato12ACs;
	}

	public void setFiseFormato12ACs(List<FiseFormato12AC> fiseFormato12ACs) {
		this.fiseFormato12ACs = fiseFormato12ACs;
	}

	public FiseFormato12AC addFiseFormato12AC(FiseFormato12AC fiseFormato12AC) {
		getFiseFormato12ACs().add(fiseFormato12AC);
		fiseFormato12AC.setFiseGrupoInformacion(this);

		return fiseFormato12AC;
	}

	public FiseFormato12AC removeFiseFormato12AC(FiseFormato12AC fiseFormato12AC) {
		getFiseFormato12ACs().remove(fiseFormato12AC);
		fiseFormato12AC.setFiseGrupoInformacion(null);

		return fiseFormato12AC;
	}

	public List<FiseFormato14CC> getFiseFormato14CCs() {
		return fiseFormato14CCs;
	}

	public void setFiseFormato14CCs(List<FiseFormato14CC> fiseFormato14CCs) {
		this.fiseFormato14CCs = fiseFormato14CCs;
	}
	
	
	public FiseFormato14CC addFiseFormato14cC(FiseFormato14CC fiseFormato14CC) {
		getFiseFormato14CCs().add(fiseFormato14CC);
		fiseFormato14CC.setFiseGrupoInformacion(this);

		return fiseFormato14CC;
	}

	public FiseFormato14CC removeFiseFormato14cC(FiseFormato14CC fiseFormato14CC) {
		getFiseFormato14CCs().remove(fiseFormato14CC);
		fiseFormato14CC.setFiseGrupoInformacion(null);

		return fiseFormato14CC;
	}

	public String getTipo() {
		return tipo;
	}

	public void setTipo(String tipo) {
		this.tipo = tipo;
	}

	public String getDescEstado() {
		String desEstado;
		if(estado!=null && estado==1){
			desEstado ="Activo";	
		}else if(estado!=null && estado==0){
			desEstado ="Inactivo";	
		}else{
			desEstado ="";	
		}
		return desEstado;
	}

	public void setDescEstado(String descEstado) {
		this.descEstado = descEstado;
	}
	
	

}