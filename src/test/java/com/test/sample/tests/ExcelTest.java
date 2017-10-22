package com.test.sample.tests;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.concurrent.TimeUnit;

import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxProfile;
import org.openqa.selenium.firefox.internal.ProfilesIni;
import org.openqa.selenium.remote.DesiredCapabilities;

import com.test.sample.Constants;

public class ExcelTest {

	public static void main(String[] args) throws IOException {
		FileInputStream fis = new FileInputStream("src/test/resources/testdata.xls");
		HSSFWorkbook wb = new HSSFWorkbook(fis);
		Sheet sheet = wb.getSheetAt(0);
		
		
		for(int i = 1; i<3 ; i++) {
			Row row = sheet.getRow(i);
//			Cell c =row.getCell(0);
			System.out.println(row.getCell(0).getNumericCellValue());
			System.out.println(row.getCell(1).getStringCellValue());
		}
		Cell cell = sheet.getRow(3).createCell((2), HSSFCell.CELL_TYPE_STRING);
		cell.setCellValue("Pass");
		
		FileOutputStream outFile = new FileOutputStream(new File("src/test/resources/testdata.xls"));
		wb.write(outFile);
		outFile.close();
		fis.close();
	}

}
