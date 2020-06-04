package com.excel.utility;

import java.util.ArrayList;

import org.testng.annotations.Test;


public class TestUtil {

	static Xls_Reader reader;
	
	public static ArrayList<Object[]> getDatafromExcel()
	{
		ArrayList<Object[]> mydata = new ArrayList<Object[]>();
		
		try {
			reader=new Xls_Reader("C:\\Users\\Abhi\\git\\Shopping_website\\eshopping\\src\\test\\java\\com\\testdata\\Eshopping.xlsx");
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
	for(int rowNum=2;rowNum<=reader.getRowCount("SignIn");rowNum++) 
	{
		 String EmailAddress=reader.getCellData("SignIn", "EmailAddress", rowNum);
		 String FirstName=reader.getCellData("SignIn", "FirstName", rowNum);
		 String LastName=reader.getCellData("SignIn", "LastName", rowNum);
		 String Password=reader.getCellData("SignIn", "Password", rowNum);
		 String Day=reader.getCellData("SignIn", "Day", rowNum);
		 String Month=reader.getCellData("SignIn", "Month", rowNum);
		 String Year=reader.getCellData("SignIn", "Year", rowNum);
		 String Company=reader.getCellData("SignIn", "Company", rowNum);
		 String AddressLine1=reader.getCellData("SignIn", "AddressLine1", rowNum);
		 String City=reader.getCellData("SignIn", "City", rowNum);
		 String State=reader.getCellData("SignIn", "State", rowNum);
		 String Zip=reader.getCellData("SignIn", "Zip", rowNum);
		 String Country=reader.getCellData("SignIn", "Country", rowNum);
		 String MobilePhone=reader.getCellData("SignIn", "MobilePhone", rowNum);
		 
		 Object ob[]= {EmailAddress,FirstName,LastName,Password,Day,Month,Year,Company,AddressLine1,
				 City,State,Zip,Country,MobilePhone};
		 mydata.add(ob);
				 
	}
		
		return mydata;
	}
	
}
