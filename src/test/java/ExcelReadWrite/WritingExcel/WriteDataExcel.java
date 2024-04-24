package ExcelReadWrite.WritingExcel;
import org.apache.poi.ss.usermodel.*;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import java.io.FileOutputStream;
import java.io.IOException;

public class WriteDataExcel {

	public static void main(String[] args) {
		// Create a new workbook
        Workbook workbook = new XSSFWorkbook();
        
        // Create a new sheet
        Sheet sheet = workbook.createSheet("Sheet1");

        // Create the header row
        Row headerRow = sheet.createRow(0);
        String[] headers = {"Name", "Age", "Email"};
        for (int i = 0; i < headers.length; i++) {
            Cell cell = headerRow.createCell(i);
            cell.setCellValue(headers[i]);
        }

        // Data rows
        String[][] data = {
                {"Karthikaa", "30", "karthi123@test.com"},
                {"Vilva", "28", "Vilva@test.com"},
                {"Dass", "35", "Dass@example.com"},
                {"Ravi", "37", "Ravi@example.com"}
        };

        // Write data to rows
        for (int i = 0; i < data.length; i++) {
            Row row = sheet.createRow(i + 1);
            for (int j = 0; j < data[i].length; j++) {
                row.createCell(j).setCellValue(data[i][j]);
            }
        }

        // Write workbook to file
        try (FileOutputStream fos = new FileOutputStream("E:\\GUVI\\eclipse-workspace\\WritingExcel\\Book2.xlsx")) {
            workbook.write(fos);
            System.out.println("Data has been written to data.xlsx successfully!");
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                workbook.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
}
		
		

	


