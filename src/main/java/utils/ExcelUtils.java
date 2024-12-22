package utils;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Calendar;

import org.apache.poi.ss.usermodel.DateUtil;
import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFCellStyle;
import org.apache.poi.xssf.usermodel.XSSFCreationHelper;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import base.BaseTest;

public class ExcelUtils {
	public String path;
	public FileInputStream fis = null;
	public FileOutputStream fileOut = null;
	public XSSFWorkbook workbook = null;
	private XSSFSheet sheet = null;
	private XSSFRow row = null;
	private XSSFCell cell = null;
			
	public ExcelUtils(String path) {
		this.path = path;
		try {
			fis = new FileInputStream(path);
			workbook = new XSSFWorkbook(fis);
			System.out.println("2----------");
			sheet = workbook.getSheetAt(0);
			fis.close();
			} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
	   
	   /**
		 * 
		 * @author SwarnaliLahiri
		 * @param sheetName
		 * @param colNum
		 * @param rowNum
		 * @return
		 */
	
	/**
	 * Retrieves the data from a specific cell in an Excel sheet.
	 * 
	 * This method takes the sheet name, row number, and column number as parameters and returns the value in the specified cell.
	 * It handles various types of data in the cell, including strings, numbers, formulas, and dates. If the cell is blank,
	 * an empty string is returned. In case of an error (e.g., invalid row/column numbers), an error message is returned.
	 * The method uses the Apache POI library to interact with Excel files.
	 * 
	 * @param sheetName The name of the sheet from which the cell data will be retrieved.
	 * @param rowNum The row number (1-based index) of the cell.
	 * @param colNum The column number (0-based index) of the cell.
	 * @return The data in the specified cell, formatted as a string. If the cell contains a number or date,
	 *         it will be converted to a string representation. If the cell is blank, an empty string is returned.
	 *         If there is an error (e.g., invalid row/column), an error message is returned indicating the issue.
	 * @throws IOException If there is an issue with reading the Excel file or accessing the sheet.
	 */
		// returns the data from a cell
		public String getCellData(String sheetName, int rowNum, int colNum) throws IOException{
			try {
				if (rowNum <= 0)
					return "";
				int index = workbook .getSheetIndex(sheetName);
							if (index == -1)
					return "";

				sheet = workbook.getSheetAt(index);
				row = sheet.getRow(rowNum );
				if (row == null)
					return "";
				cell = row.getCell(colNum);
				if (cell == null)
					return "";

				//
				if (cell.getCellType().name().equals("STRING"))
					return cell.getStringCellValue();

				//
				// if (cell.getCellType().STRING != null)
				// return cell.getStringCellValue();
				else if ((cell.getCellType().name().equals("NUMERIC")) || (cell.getCellType().name().equals("FORMULA"))) {

					String cellText = String.valueOf(cell.getNumericCellValue());
					if (DateUtil.isCellDateFormatted(cell)) {
						// format in form of M/D/YY
						double d = cell.getNumericCellValue();

						Calendar cal = Calendar.getInstance();
						cal.setTime(DateUtil.getJavaDate(d));
						cellText = (String.valueOf(cal.get(Calendar.YEAR))).substring(2);
						cellText = cal.get(Calendar.MONTH) + 1 + "/" + cal.get(Calendar.DAY_OF_MONTH) + "/" + cellText;

						// System.out.println(cellText);

					}

					return cellText;
				} else if (cell.getCellType().BLANK != null)
					return "";
				else
					return String.valueOf(cell.getBooleanCellValue());
			} catch (Exception e) {

				e.printStackTrace();
				return "row " + rowNum + " or column " + colNum + " does not exist  in xls";
			}
		}

		
		
		/**
		 * Retrieves the value of a cell from a specified sheet, row, and column name in an Excel workbook.
		 * 
		 * This method searches for the column with the specified name in the first row (assumed to be the header row).
		 * Once the column is found, it retrieves the data from the specified row and column.
		 * If the sheet, row, or column doesn't exist, it returns an empty string or an appropriate error message.
		 * 
		 * @param sheetName The name of the sheet containing the data.
		 * @param rowNum The row number (1-based index) where the data is located.
		 * @param colName The name of the column from which the data should be retrieved.
		 * @return The value of the cell as a string. Returns an empty string if the sheet, row, or column is not found.
		 * @throws IOException If there is an issue reading the Excel file or accessing the sheet.
		 */
		/**
		 * 
		 * @author SwarnaliLahiri
		 * @param sheetName
		 * @param colNum
		 * @param rowNum
		 * @return
		 */
		// returns the data from a cell
		public String getCellDataByColName(String sheetName, int rowNum, String colName) throws IOException {
			try {
				if (rowNum <= 0)
					return "";
				 // Check if the sheet exists
				int index = workbook.getSheetIndex(sheetName);
				int col_Num = -1;
				if (index == -1)
					return "";
				// Access the sheet and the first row (header row)
				sheet = workbook.getSheetAt(index);
				row = sheet.getRow(0); // The first row is assumed to be the header
				
				// Find the column index for the given column name
				for (int i = 0; i < row.getLastCellNum(); i++) {
					// System.out.println(row.getCell(i).getStringCellValue().trim());
					if (row.getCell(i).getStringCellValue().trim().equals(colName.trim()))
						col_Num = i;
				}
				
				// If the column is not found, return an empty string
				if (col_Num == -1)
					return "";

				sheet = workbook.getSheetAt(index);
				row = sheet.getRow(rowNum);
				if (row == null)
					return "";
				cell = row.getCell(col_Num);

				if (cell == null)
					return "";

				 // Check the cell type and return the appropriate value
				if (cell.getCellType().name().equals("STRING"))
					return cell.getStringCellValue();

				// if (cell.getCellType().STRING != null)

				// if(cell.getCellType()==Xls_Reader.CELL_TYPE_STRING)
				// return cell.getStringCellValue();
				else if ((cell.getCellType().name().equals("NUMERIC")) || (cell.getCellType().name().equals("FORMULA"))) {

					String cellText = String.valueOf(cell.getNumericCellValue());
					 // If the cell contains a date, format it
					if (DateUtil.isCellDateFormatted(cell)) {
						// format in form of M/D/YY
						double d = cell.getNumericCellValue();

						Calendar cal = Calendar.getInstance();
						cal.setTime(DateUtil.getJavaDate(d));
						cellText = (String.valueOf(cal.get(Calendar.YEAR))).substring(2);
						cellText = cal.get(Calendar.DAY_OF_MONTH) + "/" + cal.get(Calendar.MONTH) + 1 + "/" + cellText;

						// System.out.println(cellText);

					}

					return cellText;
				} else if (cell.getCellType().BLANK != null)
					return "";
				else
					return String.valueOf(cell.getBooleanCellValue());
			} catch (Exception e) {
				e.printStackTrace();
				return "row " + rowNum + " or column " + colName + " does not exist in xls";
			}
		}

		
		/**
		 * Retrieves the number of rows in the specified sheet of the Excel workbook.
		 * 
		 * This method checks if the given sheet exists in the workbook. If the sheet exists, it calculates the number
		 * of rows in the sheet by using the `getLastRowNum()` method, which gives the index of the last row (starting from 0).
		 * The result is adjusted by adding 1 to get the actual count of rows.
		 * 
		 * @param sheetName The name of the sheet whose row count is to be determined.
		 * @return The total number of rows in the specified sheet. If the sheet does not exist, it returns 0.
		 * @throws IOException If there is an issue reading the Excel file or accessing the sheet.
		 */
		
		// returns the row count in a sheet

		public int getRowCount(String sheetName) throws IOException {
			int index = workbook.getSheetIndex(sheetName);
			if (index == -1)
				return 0;
			else {
				sheet = workbook.getSheetAt(index);
				int number = sheet.getLastRowNum() + 1;
				return number;
			}

		}
		
		
		/**
		 * Checks if a sheet with the given name exists in the Excel workbook.
		 * 
		 * This method checks if the specified sheet exists in the workbook by looking for an exact match of the sheet name.
		 * If the sheet is not found with the exact case, it checks for a match with the sheet name converted to uppercase.
		 * 
		 * @param sheetName The name of the sheet to be checked.
		 * @return {@code true} if a sheet with the specified name exists (case-insensitive), {@code false} otherwise.
		 * @throws IOException If there is an issue reading the Excel file or accessing the sheet.
		 */
		
		// find whether sheets exists
		public boolean isSheetExist(String sheetName) throws IOException{
			int index = workbook.getSheetIndex(sheetName);
			if (index == -1) {
				index = workbook.getSheetIndex(sheetName.toUpperCase());
				if (index == -1)
					return false;
				else
					return true;
			} else
				return true;
		}
		
		
		/**
		 * Retrieves the number of columns in the specified sheet of the Excel workbook.
		 * 
		 * This method checks if the given sheet exists. If the sheet does not exist, it returns -1.
		 * If the sheet exists, it retrieves the first row of the sheet and calculates the total number of columns
		 * by using the `getLastCellNum()` method. If the first row is null, it returns -1, indicating an empty sheet.
		 * 
		 * @param sheetName The name of the sheet for which the number of columns is to be determined.
		 * @return The number of columns in the specified sheet. If the sheet does not exist or is empty,
		 *         it returns -1.
		 * @throws IOException If there is an issue reading the Excel file or accessing the sheet.
		 */
		
		// returns number of columns in a sheet
		public int getColumnCount(String sheetName) throws IOException {
			// check if sheet exists
			if (!isSheetExist(sheetName))
				return -1;

			sheet = workbook.getSheet(sheetName);
			row = sheet.getRow(0);

			if (row == null)
				return -1;

			return row.getLastCellNum();

		}
}
