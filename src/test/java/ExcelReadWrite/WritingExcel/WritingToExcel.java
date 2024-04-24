package ExcelReadWrite.WritingExcel;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;

import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

public class WritingToExcel {

	public static void main(String[] args) {
		WritingToExcel excel = new WritingToExcel();
		excel.writeToExcel();
		}
	public void writeToExcel() {
		// set the stream to connect to excel file - To open the file
	
	
	
	
	try {
		FileInputStream fis = new FileInputStream("E:\\GUVI\\eclipse-workspace\\WritingExcel\\Book1.xlsx");
		
		
		
		
		// To open the Workbook
		XSSFWorkbook workbook = new XSSFWorkbook(fis);
		
		// to Create the Sheet
		XSSFSheet sheet = workbook.createSheet("Sheet 1");
		
		// To open the sheet
		//XSSFSheet sheet = workbook.getSheetAt(0);
		
		// Get the holds of the Rows in the particular
		XSSFRow row = sheet.createRow(0);
		// Now use the cells and write the data in to your cell
		XSSFCell cell = row.createCell(0);
		cell.setCellValue("Name");
		cell =  row.createCell(1);
		cell.setCellValue("Age");
		cell = row.createCell(2);
		cell.setCellValue("Email");
		
		// Row num 2 is created and adding the values
		row = sheet.createRow(1);
		cell = row.createCell(0);
		cell.setCellValue("John Doe");
		cell = row.createCell(1);
		cell.setCellValue(30);
		cell = row.createCell(2);
		cell.setCellValue("John@test.com");
		
		// Row num 3 is created and adding the values
		row = sheet.createRow(2);
		cell = row.createCell(0);
		cell.setCellValue("Jane Doe");
		cell = row.createCell(1);
		cell.setCellValue(28);
		cell = row.createCell(2);
		cell.setCellValue("John@test.com");
		
		// Row number 4 is created and adding the values
		row = sheet.createRow(3);
		cell = row.createCell(0);
		cell.setCellValue("Bob Smith");
		cell = row.createCell(1);
		cell.setCellValue(35);
		cell = row.createCell(2);
		cell.setCellValue("Jacky@example.com");
		
		// Row num 4 is created and adding the values
		
		row = sheet.createRow(4);
		cell = row.createCell(0);
		cell.setCellValue("Swapnil");
		cell = row.createCell(1);
		cell.setCellValue(35);
		cell = row.createCell(2);
		cell.setCellValue("swapnil@example.com");
		
		// Output Stream to write the values to the destination file
		FileOutputStream fos = new FileOutputStream("E:\\GUVI\\eclipse-workspace\\WritingExcel\\Book1.xlsx");
		workbook.write(fos);
		fis.close();
		fos.close();
		workbook.close();
		} 
	catch (FileNotFoundException e) 
	{
		e.printStackTrace();
	} catch (IOException e) {
		
		e.printStackTrace();
	}
	
	



}
	

}
