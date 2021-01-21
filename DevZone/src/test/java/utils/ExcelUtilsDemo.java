package utils;

public class ExcelUtilsDemo {
	public static void main(String[] args) {
		String projectPath= System.getProperty("user.dir");
		ExcelUtils excel= new ExcelUtils(projectPath+"\\excel\\data.xlsx", "Sheet1");
		
		excel.getRowCount();
		excel.getColCount();
		excel.getCellDataString(0, 0);
		excel.getCellDataNumber(1, 1);
		
	}
}
