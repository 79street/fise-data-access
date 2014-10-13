package gob.osinergmin.fise.xls;

import java.util.List;

public class XlsWorkbookConfig {
	
	private String name;
	private List<XlsWorksheetConfig> sheets;
	
	
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public List<XlsWorksheetConfig> getSheets() {
		return sheets;
	}
	public void setSheets(List<XlsWorksheetConfig> sheets) {
		this.sheets = sheets;
	}	
}
