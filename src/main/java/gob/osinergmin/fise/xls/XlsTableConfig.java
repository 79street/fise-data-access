package gob.osinergmin.fise.xls;

import java.util.List;

public class XlsTableConfig {

	//private ExportCommandType command_type = ExportCommandType.SQL;
	//private String sql;
	//private List<XlsColumnConfig> columns;
	private List<?> lista;
	private String tipoFormato;
	//private int rowStart = 1;
	//private int colStart = 1;
	//private List<StoredProcedureParameter> parameters;
	
	public XlsTableConfig(List<?> lista, String tipoFormato) {
		super();
		this.lista = lista;
		this.tipoFormato = tipoFormato;
	}
	
	/*public XlsTableConfig(String sql) {
		super();
		this.sql = sql;
		this.columns = new LinkedList<XlsColumnConfig>();
	}*/
	
	/*public XlsTableConfig(String sql, int rowStart, int colStart) {
		super();
		this.sql = sql;
		this.rowStart = rowStart;
		this.colStart = colStart;
	}*/

	/*public XlsTableConfig(String sql, List<XlsColumnConfig> columns) {
		super();
		this.sql = sql;
		this.columns = columns;
	}*/

	/*public XlsTableConfig(String sql, List<XlsColumnConfig> columns,
			int rowStart, int colStart) {
		super();
		this.sql = sql;
		this.columns = columns;
		this.rowStart = rowStart;
		this.colStart = colStart;
	}*/
	
	public String getTipoFormato() {
		return tipoFormato;
	}

	public void setTipoFormato(String tipoFormato) {
		this.tipoFormato = tipoFormato;
	}

	public List<?> getLista() {
		return lista;
	}

	public void setLista(List<?> lista) {
		this.lista = lista;
	}
	
}
