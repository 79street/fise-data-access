package gob.osinergmin.fise.xls;

public class XlsColumnConfig {
	
	private String column_name;
	private String column_header;
	private String column_type;
	private int column_width = 10;
	
	public XlsColumnConfig(String columnHeader, int columnWidth) {
		super();
		column_header = columnHeader;
		column_width = columnWidth;
	}
	
	public XlsColumnConfig(String columnName, String columnHeader,
			String columnType) {
		super();
		column_name = columnName;
		column_header = columnHeader;
		column_type = columnType;
	}
	
	public XlsColumnConfig(String columnName, String columnHeader,
			String columnType, int columnWidth) {
		super();
		column_name = columnName;
		column_header = columnHeader;
		column_type = columnType;
		column_width = columnWidth;
	}

	public String getColumn_name() {
		return column_name;
	}
	public void setColumn_name(String columnName) {
		column_name = columnName;
	}
	public String getColumn_header() {
		return column_header;
	}
	public void setColumn_header(String columnHeader) {
		column_header = columnHeader;
	}
	public String getColumn_type() {
		return column_type;
	}
	public void setColumn_type(String columnType) {
		column_type = columnType;
	}
	public int getColumn_width() {
		return column_width;
	}
	public void setColumn_width(int columnWidth) {
		if (columnWidth > 255) 
			columnWidth = 255;		
		column_width = columnWidth;
	}
	
	
}
