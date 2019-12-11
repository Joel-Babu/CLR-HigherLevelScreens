package au.wow.WLPmain.pages;

import java.awt.Desktop.Action;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Set;
import java.util.regex.Pattern;
import java.util.regex.Matcher;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.Select;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
//import org.apache.xerces.impl.xs.identity.Selector.Matcher;







import com.relevantcodes.extentreports.ExtentTest;
import com.relevantcodes.extentreports.LogStatus;
import com.thoughtworks.selenium.webdriven.commands.GetAttribute;

import au.wow.WLPmain.objects.DailyPlanningMainObjects;
import au.wow.wlp.utils.BasePage;
import au.wow.wlp.utils.SQLWrapper;
import au.wow.wlp.utils.TestBase;


public class DailyPlanningMainPage extends TestBase{
	CommonFunctions common = PageFactory.initElements(this.getDriver(), CommonFunctions.class);
	private Logger log = LogManager.getLogger(DailyPlanningMainPage.class);
	SQLWrapper sql = new SQLWrapper(log);
	String image;
	public void fnCheckOutItemPriceTotal(DailyPlanningMainObjects objDailyItemPrice,ExtentTest extentTest) throws IOException{
		int tot = objDailyItemPrice.checkouttot.size();
	 	
	 	for (int i = 2; i <= tot; i++) {
	 		String checkout = driver1.findElement(By.xpath("//nobr[contains(text(),'CHECKOUT')]/../../td["+ i +"]")).getText();
	 		double checkut = TrimData(checkout);
	 		String storetotal = driver1.findElement(By.xpath("//span[contains(text(),'STORE TOTAL')]/../../td["+ i +"]")).getText();
	 		double stortotal = TrimData(storetotal);
	 		String day = driver1.findElement(By.xpath("//span[contains(text(),'Departments')]/../../th["+ i +"]/span")).getText();
			
	 		if(checkut==stortotal){
	 			Report_AddStep("testcase", day + ", Checkout: "+checkut+ ", STORE TOTAL: " + stortotal ,"","", "Pass");
	 			htmlToExtent(cName,mName,extentTest,driver1, "Value displayed Correctly : "+day+"; Checkout: "+checkut+"; STORE TOTAL: "+stortotal+";Pass");
	 		}else
	 		{
	 			Report_AddStep("testcase", day + ", Checkout: "+checkut+ ", STORE TOTAL: " + stortotal ,"","", "Fail");
	 			htmlToExtent(cName,mName,extentTest,driver1, "Failed to displayed Value Correctly : "+day+"; Checkout: "+checkut+"; STORE TOTAL: "+stortotal+";Fail");
	 		}
	 		
	 	}
	}

	public void fnlCheckOutCustomersTotal(DailyPlanningMainObjects objDailyCustomers,ExtentTest extentTest) throws IOException{
		int tot = objDailyCustomers.checkouttot.size();
	 	
	 	for (int i = 2; i <= tot; i++) {
	 		String checkout = driver1.findElement(By.xpath("//nobr[contains(text(),'CHECKOUT')]/../../td["+ i +"]")).getText();
	 		String storetotal = driver1.findElement(By.xpath("//span[contains(text(),'STORE TOTAL')]/../../td["+ i +"]")).getText();
	 		String day = driver1.findElement(By.xpath("//span[contains(text(),'Departments')]/../../th["+ i +"]/span")).getText();
			
	 		if(checkout.equalsIgnoreCase(storetotal)){
	 			Report_AddStep("testcase", day + ", Checkout: "+checkout+ ", STORE TOTAL: " + storetotal ,"","", "Pass");
	 			htmlToExtent(cName,mName,extentTest,driver1, "Value displayed Correctly : "+day+"; Checkout: "+checkout+"; STORE TOTAL: "+storetotal+";Pass");
	 		}
	 		else{
	 			Report_AddStep("testcase", day + ", Checkout: "+checkout+ ", STORE TOTAL: " + storetotal ,"","", "Fail");
	 			htmlToExtent(cName,mName,extentTest,driver1, "Failed to displayed Value Correctly : "+day+"; Checkout: "+checkout+"; STORE TOTAL: "+storetotal+";Fail");
	 		}
	 		
	 	}
	}

	public void fnlCheckOutItemsTotal(DailyPlanningMainObjects objDailyItems,ExtentTest extentTest) throws IOException{
		int tot = objDailyItems.checkouttot.size();
	 	
	 	for (int i = 2; i <= tot; i++) {
	 		String checkout = driver1.findElement(By.xpath("//nobr[contains(text(),'CHECKOUT')]/../../td["+ i +"]")).getText();
	 		String storetotal = driver1.findElement(By.xpath("//span[contains(text(),'STORE TOTAL')]/../../td["+ i +"]")).getText();
	 		String day = driver1.findElement(By.xpath("//span[contains(text(),'Departments')]/../../th["+ i +"]/span")).getText();
			
	 		if(checkout.equalsIgnoreCase(storetotal)){
	 			Report_AddStep("testcase", day + ", Checkout: "+checkout+ ", STORE TOTAL: " + storetotal ,"","", "Pass");
	 			htmlToExtent(cName,mName,extentTest,driver1, "Value displayed Correctly : "+day+"; Checkout: "+checkout+"; STORE TOTAL: "+storetotal+";Pass");
	 		}else{
	 			Report_AddStep("testcase", day + ", Checkout: "+checkout+ ", STORE TOTAL: " + storetotal ,"","", "Fail");
	 			htmlToExtent(cName,mName,extentTest,driver1, "Failed to displayed Value Correctly : "+day+"; Checkout: "+checkout+"; STORE TOTAL: "+storetotal+";Fail");
	 		}	
	 	}
	}

	public void fnlPaidHoursTotal(DailyPlanningMainObjects objDailyPaidHours,ExtentTest extentTest) throws IOException{
		int tot = objDailyPaidHours.checkouttot.size();
	
		for (int i =9; i <= tot; i++) {
	 		
	 		  String Merch = driver1.findElement(By.xpath("//nobr[contains(text(),'GM/Merchandising')]/../../td["+ i +"]")).getText();	 		 
	 		  String Reple = driver1.findElement(By.xpath("//nobr[contains(text(),'GR/Replenishment')]/../../td["+ i +"]")).getText(); 
	 		  String Inv = driver1.findElement(By.xpath("//nobr[contains(text(),'PER/Inventory')]/../../td["+ i +"]")).getText();  
	 		  String Deli = driver1.findElement(By.xpath("//nobr[contains(text(),'SERVICED DELICATESSEN')]/../../td["+ i +"]")).getText();  
	 		  String Meat = driver1.findElement(By.xpath("//nobr[contains(text(),'MEAT')]/../../td["+ i +"]")).getText(); 
	 		  String Produce = driver1.findElement(By.xpath("//nobr[contains(text(),'PRODUCE')]/../../td["+ i +"]")).getText();
	 		  String Bakery = driver1.findElement(By.xpath("//nobr[contains(text(),'BAKEHOUSE')]/../../td["+ i +"]")).getText();
	 		  String PropBakery = driver1.findElement(By.xpath("//nobr[contains(text(),'PROPRIETARY BAKERY')]/../../td["+ i +"]")).getText();
	 		  String Seafood = driver1.findElement(By.xpath("//nobr[contains(text(),'SEAFOOD')]/../../td["+ i +"]")).getText();
	 		  String Checkout = driver1.findElement(By.xpath("//nobr[contains(text(),'CHECKOUT')]/../../td["+ i +"]")).getText();
	 		  String NonTrade = driver1.findElement(By.xpath("//nobr[contains(text(),'NON TRADE excl stockroom')]/../../td["+ i +"]")).getText();
	 		  String storetotal = driver1.findElement(By.xpath("//span[contains(text(),'STORE TOTAL')]/../../td["+ i +"]")).getText();
	 		  System.out.println("storetotal " +storetotal);
	 		  
	 		    double Merchvalue = Double.parseDouble(Merch.replaceAll(",",""));
		 		double Replevalue = Double.parseDouble(Reple.replaceAll(",", ""));		 		
		 		double Invvalue = Double.parseDouble(Inv.replaceAll(",", ""));
		 		double Delivalue = Double.parseDouble(Deli.replaceAll(",", ""));
		 		double Meatvalue  = Double.parseDouble(Meat.replaceAll(",", ""));
		 		double Producevalue = Double.parseDouble(Produce.replaceAll(",", ""));
		 		double Bakeryvalue = Double.parseDouble(Bakery.replaceAll(",", ""));
		 		double PropBakeryvalue = Double.parseDouble(PropBakery.replaceAll(",", ""));
				double Seafoodvalue = Double.parseDouble(Seafood.replaceAll(",", ""));
		 		double Checkoutvalue  = Double.parseDouble(Checkout.replaceAll(",", ""));
		 		double Nontradevalue = Double.parseDouble(NonTrade.replaceAll(",", ""));		 		
		 		double storetotalvalue = Double.parseDouble(storetotal.replaceAll(",", ""));
		 		double depttotal = Merchvalue + Replevalue + Invvalue + Delivalue + Meatvalue + Producevalue + Bakeryvalue + 
		 				PropBakeryvalue + Seafoodvalue + Checkoutvalue + Nontradevalue;
		 		
		 		if(depttotal == storetotalvalue){
		 			Report_AddStep("testcase",  "PaidHours DeptTotal: "+depttotal+ ", STORE TOTAL: " + storetotalvalue ,"","", "Pass" );
		 			htmlToExtent(cName,mName,extentTest,driver1, "Value displayed Correctly : ; PaidHours DeptTotal: "+depttotal+"; Store Total: "+storetotalvalue+";Pass");
		 		}else{
		 			Report_AddStep("testcase",  "PaidHours DeptTotal: "+depttotal+ ", STORE TOTAL: " + storetotalvalue ,"","", "Fail");
		 			htmlToExtent(cName,mName,extentTest,driver1, "Failed to displayed Value Correctly :; PaidHours DeptTotal: "+depttotal+"; Store Total: "+storetotalvalue+";Fail");
		 	}			 	
	 	}
	}	 		

	public void fnlWagesTotal(DailyPlanningMainObjects objDailyWages,ExtentTest extentTest) throws IOException{
		int tot = objDailyWages.checkouttot.size();
		
		for (int i =9; i <= tot; i++) {
	 		
	 		  String Merch = driver1.findElement(By.xpath("//nobr[contains(text(),'GM/Merchandising')]/../../td["+ i +"]")).getText();
	 		  System.out.println("Merch is " +Merch);
	 		  String Reple = driver1.findElement(By.xpath("//nobr[contains(text(),'GR/Replenishment')]/../../td["+ i +"]")).getText(); 
	 		  String Inv = driver1.findElement(By.xpath("//nobr[contains(text(),'PER/Inventory')]/../../td["+ i +"]")).getText();  
	 		  String Deli = driver1.findElement(By.xpath("//nobr[contains(text(),'SERVICED DELICATESSEN')]/../../td["+ i +"]")).getText();  
	 		  String Meat = driver1.findElement(By.xpath("//nobr[contains(text(),'MEAT')]/../../td["+ i +"]")).getText(); 
	 		  String Produce = driver1.findElement(By.xpath("//nobr[contains(text(),'PRODUCE')]/../../td["+ i +"]")).getText();
	 		  String Bakery = driver1.findElement(By.xpath("//nobr[contains(text(),'BAKEHOUSE')]/../../td["+ i +"]")).getText();
	 		  String PropBakery = driver1.findElement(By.xpath("//nobr[contains(text(),'PROPRIETARY BAKERY')]/../../td["+ i +"]")).getText();
	 		  String Seafood = driver1.findElement(By.xpath("//nobr[contains(text(),'SEAFOOD')]/../../td["+ i +"]")).getText();
	 		  String Checkout = driver1.findElement(By.xpath("//nobr[contains(text(),'CHECKOUT')]/../../td["+ i +"]")).getText();
	 		  String NonTrade = driver1.findElement(By.xpath("//nobr[contains(text(),'NON TRADE excl stockroom')]/../../td["+ i +"]")).getText();
	 		  String storetotal = driver1.findElement(By.xpath("//span[contains(text(),'STORE TOTAL')]/../../td["+ i +"]")).getText();
	 		 	  
		 		double Merchvalue = Double.parseDouble(Merch.replaceAll(",","")); 		
		 		double Replevalue = Double.parseDouble(Reple.replaceAll(",", ""));
		 		double Invvalue = Double.parseDouble(Inv.replaceAll(",", ""));
		 		double Delivalue = Double.parseDouble(Deli.replaceAll(",", ""));
		 		double Meatvalue  = Double.parseDouble(Meat.replaceAll(",", ""));
		 		double Producevalue = Double.parseDouble(Produce.replaceAll(",", ""));
		 		double Bakeryvalue = Double.parseDouble(Bakery.replaceAll(",", ""));
		 		double PropBakeryvalue = Double.parseDouble(PropBakery.replaceAll(",", ""));
				double Seafoodvalue = Double.parseDouble(Seafood.replaceAll(",", ""));
		 		double Checkoutvalue  = Double.parseDouble(Checkout.replaceAll(",", ""));
		 		double Nontradevalue = Double.parseDouble(NonTrade.replaceAll(",", ""));
		 		double storetotalvalue = Double.parseDouble(storetotal.replaceAll(",", ""));
		 		double depttotal = Merchvalue + Replevalue + Invvalue + Delivalue + Meatvalue + Producevalue + Bakeryvalue + 
		 				PropBakeryvalue + Seafoodvalue + Checkoutvalue + Nontradevalue;
		 		
		 		if(depttotal == storetotalvalue){
		 			Report_AddStep("testcase",  "Wages DeptTotal: "+depttotal+ ", STORE TOTAL: " + storetotalvalue ,"","", "Pass" );
		 			htmlToExtent(cName,mName,extentTest,driver1, "Value displayed Correctly : ; Wages DeptTotal: "+depttotal+"; Store Total: "+storetotalvalue+";Pass");
		 		}else{
		 			Report_AddStep("testcase",  "Wages DeptTotal: "+depttotal+ ", STORE TOTAL: " + storetotalvalue ,"","", "Fail");
		 			htmlToExtent(cName,mName,extentTest,driver1, "Failed to displayed Value Correctly :; Wages DeptTotal: "+depttotal+"; Store Total: "+storetotalvalue+";Fail");
		 		}
		}			
	}
}




