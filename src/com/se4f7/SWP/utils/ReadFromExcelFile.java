package com.se4f7.SWP.utils;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.poifs.filesystem.POIFSFileSystem;
import org.apache.poi.ss.usermodel.DataFormatter;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import com.se4f7.SWP.service.CouponService;
import com.se4f7.SWP.service.impl.CouponServiceImpl;

public class ReadFromExcelFile {

	public static void importExcel(HttpServletRequest request, HttpServletResponse response, String filePath)
			throws Exception {
		try (FileInputStream inp = new FileInputStream(filePath)) {
			Workbook wb;

			if (filePath.endsWith(".xls")) {
				// For Excel 97-2003 (.xls) format
				wb = new HSSFWorkbook(new POIFSFileSystem(inp));
			} else if (filePath.endsWith(".xlsx")) {
				// For Excel 2007 onwards (.xlsx) format
				wb = new XSSFWorkbook(inp);
			} else {
				System.err.println("Invalid Excel file format: " + filePath);
				throw new IllegalArgumentException("Invalid Excel file format");
			}

			Sheet sheet = wb.getSheetAt(0);
			DataFormatter dataFormatter = new DataFormatter();

			for (int i = 1; i <= sheet.getLastRowNum(); i++) {
				Row row = sheet.getRow(i);
				String name = dataFormatter.formatCellValue(row.getCell(1));
				String description = dataFormatter.formatCellValue(row.getCell(2));
				int status = (int) row.getCell(3).getNumericCellValue();
				if (status < 0 || status > 3) {
					System.err.println("Invalid status value in Excel file at row " + (i + 1));
					continue;
				}
				String createdBy = dataFormatter.formatCellValue(row.getCell(4));
				String updatedBy = dataFormatter.formatCellValue(row.getCell(5));
				String createdDate = dataFormatter.formatCellValue(row.getCell(6));
				String updatedDate = dataFormatter.formatCellValue(row.getCell(7));
				int Amount = (int) row.getCell(8).getNumericCellValue();

				String due = dataFormatter.formatCellValue(row.getCell(9));

				CouponService couponService = new CouponServiceImpl();
				couponService.create(name, description, status, createdBy, updatedBy, createdDate, updatedDate, Amount,
						due);
			}

			wb.close();

		} catch (FileNotFoundException e) {
			e.printStackTrace();
			throw new Exception(e.getMessage());
		} catch (IOException e) {
			e.printStackTrace();
			throw new Exception(e.getMessage());
		}
	}

}
