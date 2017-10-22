package com.test.sample.utils;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.hssf.util.HSSFColor;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellStyle;
import org.apache.poi.ss.usermodel.Font;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;

public class ExcelUtil {

	private static int lastColumn = 0;
	/**
	 * Write the status in the last column of the corresponding BVT
	 * @param bvtSheetPath
	 * @param bvtNumber
	 * @param status
	 */
	public static void writeStatus(String bvtSheetPath,int altId, String status) {
		Workbook wb;
		InputStream fis = null;
		FileOutputStream outFile = null;
		try {
			fis = new FileInputStream(new File(bvtSheetPath));
			wb = new HSSFWorkbook(fis);
			Sheet sheet = wb.getSheetAt(0);
			int startRowNum = 1;
			int lastRowOfSheet = sheet.getLastRowNum();
			int altIdRowNumber = getRowNumber(sheet, startRowNum, lastRowOfSheet, altId);
			if(lastColumn == 0)
				lastColumn = getlastColumnNum(sheet, startRowNum, lastRowOfSheet);
			if (altIdRowNumber==0) {
				System.out.println("Alt ID " + altId + " not exists.");
				return;
			}
			Cell cell = sheet.getRow(altIdRowNumber).createCell((lastColumn), HSSFCell.CELL_TYPE_STRING);
			//Set the cell as yellow if status is fail
			if (status.equalsIgnoreCase("f")) {
				CellStyle style = wb.createCellStyle();
				style.setFillForegroundColor(HSSFColor.RED.index);
				style.setFillPattern(CellStyle.SOLID_FOREGROUND);
				cell.setCellStyle(style);
			}else if (status.equalsIgnoreCase("p")){
				CellStyle style = wb.createCellStyle();
				style.setFillForegroundColor(HSSFColor.GREEN.index);
				style.setFillPattern(CellStyle.SOLID_FOREGROUND);
				cell.setCellStyle(style);
			}
			cell.setCellValue(status.toUpperCase());
			fis.close();
			outFile =new FileOutputStream(new File(bvtSheetPath));
			wb.write(outFile);
			outFile.close();
		}catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			if (fis != null) {
				try {
					fis.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
			if (outFile !=null) {
				try {
					outFile.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
			
		}

	}

	/**
	 * Identify last column and Set the build number as the last column header
	 */
	public static void setBuildNumber (String bvtSheetPath, String buildNumber) {
		Workbook wb;
		InputStream fis = null;
		try {
			fis = new FileInputStream(new File(bvtSheetPath));
			wb = new HSSFWorkbook(fis);
			Sheet sheet = wb.getSheetAt(0);
			int startRowNum = 0;
			int timeStampRowNumber = 1;
			int lastRowOfSheet = sheet.getLastRowNum();
			lastColumn = getlastColumnNum(sheet, startRowNum, lastRowOfSheet);
			Row headerRow = sheet.getRow(0);
			//sheet.shiftRows(1,lastRowOfSheet,1); 
			Row dateRow = sheet.getRow(timeStampRowNumber);
			DateFormat dateFormat = new SimpleDateFormat("dd-MM-yyyy HH:mm:ss");
			Date date = new Date();
			//System.out.println(dateFormat.format(date));
			CellStyle cellStyle = wb.createCellStyle();
			Font font = wb.createFont();
			font.setBoldweight(Font.BOLDWEIGHT_BOLD);
			cellStyle.setFont(font);
			cellStyle.setWrapText(true);
			Cell cell = headerRow.createCell((lastColumn), HSSFCell.CELL_TYPE_STRING);
			cell.setCellStyle(cellStyle);
			cell.setCellValue(dateFormat.format(date) + " : build #" + buildNumber + "Status");
			Cell cell2 = dateRow.createCell((lastColumn), HSSFCell.CELL_TYPE_STRING);
			cell2.setCellStyle(cellStyle);
			cell2.setCellValue("Executed on " + dateFormat.format(date));
			fis.close();
			FileOutputStream outFile =new FileOutputStream(new File(bvtSheetPath));
			wb.write(outFile);
			outFile.close();
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	/**
	 * Calculate and return  last column number
	 * @param sheet
	 * @param startRowNum
	 * @param lastRowOfSheet
	 * @return last column number
	 */
	private static int getlastColumnNum(Sheet sheet, int startRowNum, int lastRowOfSheet) {
		int lastCol = 0;
		for (int i = startRowNum; i < lastRowOfSheet; i++) {
				Row row = sheet.getRow(i);
				if (i == startRowNum) {
					lastCol = row.getLastCellNum();// get last column index
				}
			}
		Row row = sheet.getRow(0);
		//lastCol = row.getLastCellNum();

		return lastCol;
	}

	/**
	 * 
	 * @param sheet
	 * @param startRowNum
	 * @param lastRowOfSheet
	 * @param altId
	 * @return row number of given Alt ID
	 */
	private static int getRowNumber(Sheet sheet, int startRowNum, int lastRowOfSheet, int altId) {
		int rowNumber = 0;
		for (int i = startRowNum; i < lastRowOfSheet; i++) {
			Row row = sheet.getRow(i);
			Cell cell = row.getCell(0);
			String cellValue=null;
			if(cell!=null) {

				switch(cell.getCellType()) {
				case Cell.CELL_TYPE_NUMERIC:
					double val = cell.getNumericCellValue();
					cellValue = String.valueOf(val);
					if (cellValue.contains(".")) {
						cellValue = cellValue.substring(0, cellValue.indexOf("."));
					}
					break;
				case Cell.CELL_TYPE_STRING:
					cellValue = cell.getStringCellValue();
					break;
				default:
					cellValue = null;
				}
			}

			if (cellValue!=null && cellValue.equals(String.valueOf(altId))) {
				rowNumber = i;
				break;
			}
		}
		
		return rowNumber;

	}
	public static String getTestcaseId(String bvtSheetPath,int altId) {
		Workbook wb;
		InputStream fis = null;
		String tcId = null;
		try {
			fis = new FileInputStream(new File(bvtSheetPath));
			wb = new HSSFWorkbook(fis);
			Sheet sheet = wb.getSheetAt(0);
			int startRowNum = 2;
			int lastRowOfSheet = sheet.getLastRowNum();
//			tcId = altIdRowNumber(sheet, startRowNum, lastRowOfSheet, altId);
//			System.out.println(tcId);
//			int altIdRowNumber = altIdRowNumber(sheet, startRowNum, lastRowOfSheet, altId);
//			System.out.println(altIdRowNumber);
			if (altId==0) {
				System.out.println("Alt ID " + altId + " not exists.");
				return null;
			}
			tcId = sheet.getRow(altId).getCell(2).toString().replace(".0", "");
			
			fis.close();
			return tcId;
		}catch (FileNotFoundException e) {
			e.printStackTrace();
			return null;
		} catch (IOException e) {
			e.printStackTrace();
			return null;
		} 
	}
	
}
