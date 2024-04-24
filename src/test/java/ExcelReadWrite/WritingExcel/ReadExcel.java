package ExcelReadWrite.WritingExcel;
import org.apache.poi.ss.usermodel.*;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import java.io.FileInputStream;
import java.io.IOException;

public class ReadExcel {

	public static void main(String[] args) {
		 // Path to the Excel file
        String filePath = "E:\\\\GUVI\\\\eclipse-workspace\\\\WritingExcel\\\\Book2.xlsx";

        try (FileInputStream fis = new FileInputStream("E:\\\\GUVI\\\\eclipse-workspace\\\\WritingExcel\\\\Book2.xlsx");
             Workbook workbook = new XSSFWorkbook(fis)) {

            // Get the first sheet
            Sheet sheet = workbook.getSheetAt(0);

            // Iterate through each row in the sheet
            for (Row row : sheet) {
                // Iterate through each cell in the row
                for (Cell cell : row) {
                    // Print the cell value
                    System.out.print(cell.toString() + "\t");
                }
                // Move to the next line after printing all cells of a row
                System.out.println();
            }

        } catch (IOException e) {
            e.printStackTrace();
        }
		

	}

}
