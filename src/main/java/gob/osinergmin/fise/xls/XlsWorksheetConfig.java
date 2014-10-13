package gob.osinergmin.fise.xls;

import java.util.List;

public class XlsWorksheetConfig {
	
	private String name;
	private List<XlsTableConfig> tables;
	
	
	public XlsWorksheetConfig() {
		super();
	}
	public XlsWorksheetConfig(String name, List<XlsTableConfig> tables) {
		super();
		this.name = name;
		this.tables = tables;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public List<XlsTableConfig> getTables() {
		return tables;
	}
	public void setTables(List<XlsTableConfig> tables) {
		this.tables = tables;
	}
	
	
	
}
