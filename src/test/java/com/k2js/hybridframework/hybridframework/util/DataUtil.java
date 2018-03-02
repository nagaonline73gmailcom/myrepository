package com.k2js.hybridframework.hybridframework.util;

import java.util.Hashtable;

public class DataUtil {
	
	
	public static Object[][] getdata(Xls_Reader xls,String testname)
	{

		String sheetname = "data";

		int testStartRowNum = 1;
		boolean t;
		while (!xls.getCellData(sheetname, 0, testStartRowNum).equalsIgnoreCase(testname)) {

			testStartRowNum++;
		}
		System.out.println(testStartRowNum);
		int colStartRowNum = testStartRowNum + 1;
		int dataStartRowNum = testStartRowNum + 2;
		int rows = 0;
		while (!xls.getCellData(sheetname, 0, dataStartRowNum + rows).equalsIgnoreCase("")) {
			rows++;
		}
		System.out.println(rows);
		int cols = 0;
		while (!xls.getCellData(sheetname, cols, colStartRowNum).equalsIgnoreCase("")) {
			cols++;
		}
		System.out.println(cols);
		Object[][] data = new Object[rows][1];
		int dataRow = 0;
		Hashtable<String, String> table = null;
		for (int rNum = dataStartRowNum; rNum < dataStartRowNum + rows; rNum++) {
			table = new Hashtable<String, String>();
			for (int cNum = 0; cNum < cols; cNum++) {
				String key = xls.getCellData(sheetname, cNum, colStartRowNum);
				String value = xls.getCellData(sheetname, cNum, rNum);
				table.put(key, value);
			}
			System.out.println(table);
			data[dataRow][0] = table;
			dataRow++;
		}
		return data;

	}
	
	

		
	}


