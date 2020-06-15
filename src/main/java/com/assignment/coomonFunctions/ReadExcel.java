package com.assignment.coomonFunctions;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellType;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

public class ReadExcel {
	
	public static void main(String[] args) throws IOException {
		ReadExcel r = new ReadExcel();
		//r.getData();
		
		ReadExcelDataFile rr = new ReadExcelDataFile("C:\\Users\\chavan_v\\Desktop\\BookMeetingRoom.xlsx");
		
		rr.getCellData("Sheet1", 0, 3);
	}
	
	XSSFWorkbook workbook;
	
	public Object[][] getData() throws IOException {
	FileInputStream file = new FileInputStream("C:\\Users\\chavan_v\\Desktop\\BookMeetingRoom.xlsx");
	
	workbook = new XSSFWorkbook(file);
	
	XSSFSheet sheet = workbook.getSheet("Sheet1");
	
	Row row = sheet.getRow(0);
	int rowC = sheet.getLastRowNum();//getPhysicalNumberOfCells();
	int rowCount = rowC+1;
	System.out.println("Rows count is : "+rowCount);
	int cellCount = row.getLastCellNum();
	System.out.println("Coll count is : "+cellCount);
	
	Object[][] input = new Object[rowCount][cellCount];
	Object data = null;
	
	for(int i=0 ; i<rowCount ; i++) {
		row = sheet.getRow(i);
		for(int j=0 ; j<cellCount ; j++) {
			Cell cell = row.getCell(j);
			if(cell!=null) {
			if(cell.getCellType()==CellType.STRING) {
				data = cell.getStringCellValue();
			}
			else if(cell.getCellType()==CellType.NUMERIC) {
				data = cell.getNumericCellValue();
			}	
		    else if(cell.getCellType()==CellType.BLANK) {
			    data = " ";
		    	System.out.println("cell is blank");
		    }
			}
			input[i][j]=data;
			System.out.println("data is  : "+input[i][j]);
		}
	}
	return input;
	
	}
	
	
}
