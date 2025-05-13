package utils;

import org.apache.poi.ss.usermodel.*;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

public class ExcelWriter {


    public static void writeValueToExcel(String value) {
        String fileName = "src/main/java/utils/RegisteredUserEmail.xlsx";
        String sheetName = "Emails";
        Path filePath = Paths.get("src", "test", "resources", "testdata", fileName);
        Workbook workbook = null;
        Sheet sheet;

        try {
            // Load workbook or create new
            if (Files.exists(filePath)) {
                try (FileInputStream fis = new FileInputStream(filePath.toFile())) {
                    workbook = new XSSFWorkbook(fis);
                }
            } else {
                workbook = new XSSFWorkbook();
            }

            // Load or create sheet
            sheet = workbook.getSheet(sheetName);
            if (sheet == null) {
                sheet = workbook.createSheet(sheetName);
            }

            // Find next empty row
            int lastRowNum = sheet.getLastRowNum();
            int nextRowNum = (sheet.getRow(lastRowNum) == null || isRowEmpty(sheet.getRow(lastRowNum)))
                    ? lastRowNum
                    : lastRowNum + 1;

            Row row = sheet.createRow(nextRowNum);
            Cell cell = row.createCell(0);
            cell.setCellValue(value);

            // Write to file
            try (FileOutputStream fos = new FileOutputStream(filePath.toFile())) {
                workbook.write(fos);
            }

        } catch (IOException e) {
            System.err.println("IOException while writing to Excel file: " + e.getMessage());
            e.printStackTrace();
        } catch (Exception e) {
            System.err.println("Unexpected error while writing Excel data: " + e.getMessage());
            e.printStackTrace();
        } finally {
            try {
                if (workbook != null) workbook.close();
            } catch (IOException e) {
                System.err.println("Error closing workbook: " + e.getMessage());
            }
        }
    }

    /**
     * Helper method to check if a row is empty.
     */
    private static boolean isRowEmpty(Row row) {
        if (row == null) return true;
        for (Cell cell : row) {
            if (cell != null && cell.getCellType() != CellType.BLANK) {
                return false;
            }
        }
        return true;
    }
}
