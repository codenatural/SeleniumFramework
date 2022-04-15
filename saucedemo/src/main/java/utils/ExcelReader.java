package utils;

import java.io.FileInputStream;
import java.io.FileOutputStream;

import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

public class ExcelReader {
	
	public String readData(String inputFile, String sheetName, int rowNumber, int columnNumber) throws Exception {
		// create file input stream object for the excel sheet
		FileInputStream fis = new FileInputStream(inputFile);
		XSSFWorkbook wb = new XSSFWorkbook(fis);
		XSSFSheet sheet = wb.getSheet(sheetName);
		XSSFRow row = sheet.getRow(rowNumber);
		XSSFCell cell = row.getCell(columnNumber);
		wb.close();
		return cell.getStringCellValue();
	}

	public void writeData(String outputFile, String sheetName, int rowNumber, int columnNumber, String dataToWrite) throws Exception {
		FileInputStream fis = new FileInputStream(outputFile);
		XSSFWorkbook wb = new XSSFWorkbook(fis);
		XSSFSheet sheet = wb.getSheet(sheetName);
		XSSFRow row = sheet.getRow(rowNumber);
		//create object for cell present in row using Row object 'row'
		XSSFCell cell = row.createCell(columnNumber);
		cell.setCellValue(dataToWrite);
		FileOutputStream fos = new FileOutputStream(outputFile);
		wb.write(fos);
		wb.close();
	}

}
