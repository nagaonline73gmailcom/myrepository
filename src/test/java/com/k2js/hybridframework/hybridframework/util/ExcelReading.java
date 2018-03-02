package com.k2js.hybridframework.hybridframework.util;

public class ExcelReading {
	public static void main(String[] args) {
		Xls_Reader xls=new Xls_Reader("C:\\Users\\admin\\Desktop\\testcases.xlsx");
		String sheetname="sheet2";
		String testcasename="testb";
		int testStartRowNum=1;
		boolean t;
		while(!xls.getCellData(sheetname, 0, testStartRowNum).equalsIgnoreCase(testcasename))
		{
			
			testStartRowNum++;
		}
		System.out.println(testStartRowNum);
		int colStartRowNum=testStartRowNum+1;
		int dataStartRowNum=testStartRowNum+2;
		int rows=0;
		while(!xls.getCellData(sheetname, 0,dataStartRowNum+rows).equalsIgnoreCase(""))
		{
			rows++;
		}
		System.out.println(rows);
		int cols=0;
		while(!xls.getCellData(sheetname, cols, colStartRowNum).equalsIgnoreCase(""))
		{
			cols++;
		}
		System.out.println(cols);
		Object[][] data=new Object[rows][cols];
		int dataRow=0;
		for(int rNum=dataStartRowNum;rNum<dataStartRowNum+rows;rNum++)
		{
			for(int cNum=0;cNum<cols;cNum++)
			{
				System.out.println(xls.getCellData(sheetname, cNum, rNum));
			}
		}
		
	}

}
