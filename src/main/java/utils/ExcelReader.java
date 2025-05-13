package utils;

import org.apache.poi.ss.usermodel.*;
import java.io.FileInputStream;
import java.io.IOException;
import java.nio.file.Paths;
import java.util.HashMap;
import java.util.Map;

public class ExcelReader {



    public static Map<String, String> getRowData() {
        String filePath = "src/test/resources/testdata/RegisterUser.xlsx";
        String sheetName = "Sheet1";
        int rowIndex = 1;
        Map<String, String> dataMap = new HashMap<>();
        try (FileInputStream fis = new FileInputStream(filePath)) {
            Workbook wb = WorkbookFactory.create(fis);
            Sheet sheet = wb.getSheet(sheetName);

            Row headerRow = sheet.getRow(0);
            Row dataRow = sheet.getRow(rowIndex);

            if (headerRow == null || dataRow == null) return null;

            for (int col = 0; col < headerRow.getLastCellNum(); col++) {
                Cell headerCell = headerRow.getCell(col);
                Cell dataCell = dataRow.getCell(col);

                if (headerCell != null && dataCell != null) {
                    dataMap.put(headerCell.getStringCellValue(), dataCell.toString());
                }
            }

        } catch (IOException e) {
            System.err.println("IOException while reading Excel file: " + e.getMessage());
            e.printStackTrace();
        } catch (Exception e) {
            System.err.println("Error reading Excel data: " + e.getMessage());
            e.printStackTrace();
        }
        return dataMap;
    }


    public void prepareExcel(){

    }
}
