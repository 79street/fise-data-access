package gob.osinergmin.fise.bean;

public class FiseParametroBean {
	
	private String codigoBusq;
	private String nombreBusq;
	
	
    private String codigo;
    private String codParametro;//valor solo para setear al momento de enviar del js al controller en editar, visualiar y eliminar
    private String nombre;
    private String valor;
    private String orden;
    
    private String usuario;
    private String terminal;
    
    
    
    
    
	public String getCodigoBusq() {
		return codigoBusq;
	}
	public void setCodigoBusq(String codigoBusq) {
		this.codigoBusq = codigoBusq;
	}
	public String getNombreBusq() {
		return nombreBusq;
	}
	public void setNombreBusq(String nombreBusq) {
		this.nombreBusq = nombreBusq;
	}
	public String getCodigo() {
		return codigo;
	}
	public void setCodigo(String codigo) {
		this.codigo = codigo;
	}
	public String getCodParametro() {
		return codParametro;
	}
	public void setCodParametro(String codParametro) {
		this.codParametro = codParametro;
	}
	public String getNombre() {
		return nombre;
	}
	public void setNombre(String nombre) {
		this.nombre = nombre;
	}
	public String getValor() {
		return valor;
	}
	public void setValor(String valor) {
		this.valor = valor;
	}
	public String getOrden() {
		return orden;
	}
	public void setOrden(String orden) {
		this.orden = orden;
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
    

    
    
    
	
	
    
    
}
