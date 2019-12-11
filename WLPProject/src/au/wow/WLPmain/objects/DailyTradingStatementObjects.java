//Xpath
package au.wow.WLPmain.objects;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class DailyTradingStatementObjects 
{
	@FindBy(xpath= "/html/frameset/frame[2]") 
	public WebElement DataFrame;
	
	@FindBy(xpath= "//*[@id='form1:buttonLogoff']")
	public WebElement Logoff;
	
	@FindBy(xpath= "/html/frameset/frame[1]")
	public WebElement TitleFrame;
	
	public static final String RowNameTotal="//*[@id='frmDetails:ctrStorePlanningDetailsTable:dynamic:colOrgDesc']";
	public static final String Table="//*[@id='dispForm:dts']/tbody/tr";
	public static final String ShowButton="//input[@value='Hide/Show All']";  
	public static final String ShowButtonGroup="//*[@id='dispForm']/table[2]/tbody/tr/td[3]/input"; 
	//Sales
	public static final String Department="//td[contains(@id,'dispForm:dts:dynamic:j')][1]/span[2]";	//Department Column
	public static final String StoreTotal="//td[contains(@id,'dispForm:dts:dynamic:j') and contains(@class,'dtsDeptColBold')]";	//Store Total Text
	
	public static final String MondayPlndSales="//td[contains(@id,'dispForm:dts:dynamic:j')][2]";
	public static final String TuesdayPlndSales="//td[contains(@id,'dispForm:dts:dynamic:j')][5]";
	public static final String WednesdayPlndSales="//td[contains(@id,'dispForm:dts:dynamic:j')][8]";
	public static final String ThursdayPlndSales="//td[contains(@id,'dispForm:dts:dynamic:j')][11]";
	public static final String ThursdayPlndSalesWhite="//td[contains(@id,'dispForm:dts:dynamic:j')][11]";	//Duplicate - Not Required
	public static final String FridayPlndSales="//td[contains(@id,'dispForm:dts:dynamic:j')][14]";
	public static final String FridayPlndSalesWhite="//td[contains(@id,'dispForm:dts:dynamic:j')][14]";	//Duplicate - Not Required
	public static final String SaturdayPlndSales="//td[contains(@id,'dispForm:dts:dynamic:j')][17]";
	public static final String SaturdayPlndSalesWhite="//td[contains(@id,'dispForm:dts:dynamic:j')][17]";	//Duplicate - Not Required
	public static final String SundayPlndSales="//td[contains(@id,'dispForm:dts:dynamic:j')][20]";
	public static final String SundayPlndSalesWhite="//td[contains(@id,'dispForm:dts:dynamic:j')][20]";	//Duplicate - Not Required
	public static final String WeekToDatePlndSales="//td[contains(@id,'dispForm:dts:dynamic:j')][23]";	
	
	public static final String MondayActlSales="//td[contains(@id,'dispForm:dts:dynamic:j')][3]";
	public static final String MondayActlSalesYellow="//td[contains(@id,'dispForm:dts:dynamic:j')][3]";	//Duplicate - Not Required
	public static final String TuesdayActlSales="//td[contains(@id,'dispForm:dts:dynamic:j')][6]";
	public static final String TuesdayActlSalesYellow="//td[contains(@id,'dispForm:dts:dynamic:j')][6]";	//Duplicate - Not Required
	public static final String WednesdayActlSales="//td[contains(@id,'dispForm:dts:dynamic:j')][9]";
	public static final String WednesdayActlSalesYellow="//td[contains(@id,'dispForm:dts:dynamic:j')][9]";	//Duplicate - Not Required
	public static final String ThursdayActlSales="//td[contains(@id,'dispForm:dts:dynamic:j')][12]";
	public static final String ThursdayActlSalesYellow="//td[contains(@id,'dispForm:dts:dynamic:j')][12]";	//Duplicate - Not Required
	public static final String FridayActlSales="//td[contains(@id,'dispForm:dts:dynamic:j')][15]";
	public static final String FridayActlSalesYellow="//td[contains(@id,'dispForm:dts:dynamic:j')][15]";	//Duplicate - Not Required
	public static final String SaturdayActlSales="//td[contains(@id,'dispForm:dts:dynamic:j')][18]";
	public static final String SaturdayActlSalesYellow="//td[contains(@id,'dispForm:dts:dynamic:j')][18]";	//Duplicate - Not Required
	public static final String SundayActlSales="//td[contains(@id,'dispForm:dts:dynamic:j')][21]";
	public static final String SundayActlSalesYellow="//td[contains(@id,'dispForm:dts:dynamic:j')][21]";	//Duplicate - Not Required
	public static final String WeekToDateActlSales="//td[contains(@id,'dispForm:dts:dynamic:j')][24]";
	public static final String WeekToDateActlSalesYellow="//td[contains(@id,'dispForm:dts:dynamic:j')][24]";	//Duplicate - Not Required
	
	public static final String MondayLYSales="//td[contains(@id,'dispForm:dts:dynamic:j')][4]";
	public static final String TuesdayLYSales="//td[contains(@id,'dispForm:dts:dynamic:j')][7]";
	public static final String WednesdayLYSales="//td[contains(@id,'dispForm:dts:dynamic:j')][10]";
	public static final String ThursdayLYSales="//td[contains(@id,'dispForm:dts:dynamic:j')][13]";
	public static final String FridayLYSales="//td[contains(@id,'dispForm:dts:dynamic:j')][16]";
	public static final String SaturdayLYSales="//td[contains(@id,'dispForm:dts:dynamic:j')][19]";
	public static final String SundayLYSales="//td[contains(@id,'dispForm:dts:dynamic:j')][22]";
	public static final String WeekToDateLYSales="//td[contains(@id,'dispForm:dts:dynamic:j')][26]";
	
	public static final String WeekToDateVarSales="//td[contains(@id,'dispForm:dts:dynamic:j')][25]";
	public static final String MondayPlndValue="//td[contains(@id,'dispForm:dts:dynamic:j')][]";	//Duplicate - Not Required
	public static final String TuesdayPlndValue="//td[contains(@id,'dispForm:dts:dynamic:j')][]";	//Duplicate - Not Required
	public static final String WednesdayPlndValue="//td[contains(@id,'dispForm:dts:dynamic:j')][]";	//Duplicate - Not Required
	public static final String ThursdayPlndValue="//td[contains(@id,'dispForm:dts:dynamic:j')][]";	//Duplicate - Not Required
	public static final String FridayPlndValue="//td[contains(@id,'dispForm:dts:dynamic:j')][]";	//Duplicate - Not Required
	public static final String SaturdayPlndValue="//td[contains(@id,'dispForm:dts:dynamic:j')][]";	//Duplicate - Not Required
	public static final String SundayPlndValue="//td[contains(@id,'dispForm:dts:dynamic:j')][]";	//Duplicate - Not Required
	public static final String WeekToDatePlndValue="//td[contains(@id,'dispForm:dts:dynamic:j')][23]";	//Duplicate - Not Required
	
	//OR
	public static final String MondayPlndValueOR="//td[contains(@id,'dispForm:dts:dynamic:j')][]";	//Duplicate - Not Required
	public static final String TuesdayPlndValueOR="//td[contains(@id,'dispForm:dts:dynamic:j')][]";	//Duplicate - Not Required
	public static final String WednesdayPlndValueOR="//td[contains(@id,'dispForm:dts:dynamic:j')][]";	//Duplicate - Not Required
	public static final String ThursdayPlndValueOR="//td[contains(@id,'dispForm:dts:dynamic:j')][]";	//Duplicate - Not Required
	public static final String FridayPlndValueOR="//td[contains(@id,'dispForm:dts:dynamic:j')][]";	//Duplicate - Not Required
	public static final String SaturdayPlndValueOR="//td[contains(@id,'dispForm:dts:dynamic:j')][]";	//Duplicate - Not Required
	public static final String SundayPlndValueOR="//td[contains(@id,'dispForm:dts:dynamic:j')][]";	//Duplicate - Not Required
	public static final String WeekToDatePlndValueOR="//td[contains(@id,'dispForm:dts:dynamic:j')][23]";	//Duplicate - Not Required
	
	public static final String MondayActlValueOR="//td[contains(@id,'dispForm:dts:dynamic:j')][]";	//Duplicate - Not Required
	public static final String TuesdayActlValueOR="//td[contains(@id,'dispForm:dts:dynamic:j')][]";	//Duplicate - Not Required
	public static final String WednesdayActlValueOR="//td[contains(@id,'dispForm:dts:dynamic:j')][]";	//Duplicate - Not Required
	public static final String ThursdayActlValueOR="//td[contains(@id,'dispForm:dts:dynamic:j')][]";	//Duplicate - Not Required
	public static final String FridayActlValueOR="//td[contains(@id,'dispForm:dts:dynamic:j')][]";	//Duplicate - Not Required
	public static final String SaturdayActlValueOR="//td[contains(@id,'dispForm:dts:dynamic:j')][]";	//Duplicate - Not Required
	public static final String SundayActlValueOR="//td[contains(@id,'dispForm:dts:dynamic:j')][]";	//Duplicate - Not Required
	public static final String WeekToDateActlValueOR="//td[contains(@id,'dispForm:dts:dynamic:j')][24]";	//Duplicate - Not Required
	
	public static final String MondayActlValue="//td[contains(@id,'dispForm:dts:dynamic:j')][]";	//Duplicate - Not Required
	public static final String MondayActlValueYellow="//td[contains(@id,'dispForm:dts:dynamic:j')][]";	//Duplicate - Not Required
	public static final String TuesdayActlValue="//td[contains(@id,'dispForm:dts:dynamic:j')][]";	//Duplicate - Not Required
	public static final String TuesdayActlValueYellow="//td[contains(@id,'dispForm:dts:dynamic:j')][]";	//Duplicate - Not Required
	public static final String WednesdayActlValue="//td[contains(@id,'dispForm:dts:dynamic:j')][]";	//Duplicate - Not Required
	public static final String WednesdayActlValueYellow="//td[contains(@id,'dispForm:dts:dynamic:j')][]";	//Duplicate - Not Required
	public static final String ThursdayActlValue="//td[contains(@id,'dispForm:dts:dynamic:j')][]";	//Duplicate - Not Required
	public static final String ThursdayActlValueYellow="//td[contains(@id,'dispForm:dts:dynamic:j')][]";	//Duplicate - Not Required
	public static final String FridayActlValue="//td[contains(@id,'dispForm:dts:dynamic:j')][]";	//Duplicate - Not Required
	public static final String FridayActlValueYellow="//td[contains(@id,'dispForm:dts:dynamic:j')][]";	//Duplicate - Not Required
	public static final String SaturdayActlValue="//td[contains(@id,'dispForm:dts:dynamic:j')][]";	//Duplicate - Not Required
	public static final String SaturdayActlValueYellow="//td[contains(@id,'dispForm:dts:dynamic:j')][]";	//Duplicate - Not Required
	public static final String SundayActlValue="//td[contains(@id,'dispForm:dts:dynamic:j')][]";	//Duplicate - Not Required
	public static final String SundayActlValueYellow="//td[contains(@id,'dispForm:dts:dynamic:j')][]";	//Duplicate - Not Required
	public static final String WeekToDateActlValue="//td[contains(@id,'dispForm:dts:dynamic:j')][24]";	//Duplicate - Not Required
	public static final String WeekToDateActlValueYellow="//td[contains(@id,'dispForm:dts:dynamic:j')][]";
	
	public static final String MondayLYValue="//td[contains(@id,'dispForm:dts:dynamic:j')][]";	//Duplicate - Not Required
	public static final String TuesdayLYValue="//td[contains(@id,'dispForm:dts:dynamic:j')][]";	//Duplicate - Not Required
	public static final String WednesdayLYValue="//td[contains(@id,'dispForm:dts:dynamic:j')][]";	//Duplicate - Not Required
	public static final String ThursdayLYValue="//td[contains(@id,'dispForm:dts:dynamic:j')][]";	//Duplicate - Not Required
	public static final String FridayLYValue="//td[contains(@id,'dispForm:dts:dynamic:j')][]";	//Duplicate - Not Required
	public static final String SaturdayLYValue="//td[contains(@id,'dispForm:dts:dynamic:j')][]";	//Duplicate - Not Required
	public static final String SundayLYValue="//td[contains(@id,'dispForm:dts:dynamic:j')][]";	//Duplicate - Not Required
	public static final String WeekToDateLYValue="//td[contains(@id,'dispForm:dts:dynamic:j')][26]";	//Duplicate - Not Required
	
	public static final String WeekToDateVarValue="//td[contains(@id,'dispForm:dts:dynamic:j')][25]";	//Duplicate - Not Required
	
	//Budget
	public static final String BudgetSales="//td[contains(@id,'dispForm:dts:dynamic:j')][27]";
	public static final String BudgetWgPrcnt="//td[contains(@id,'dispForm:dts:dynamic:j')][27]";	//Duplicate - Not Required
	
	public static final String AllowedWages="//td[contains(@id,'dispForm:dts:dynamic:j')][30]";
	public static final String AllowedWagesValue="//td[contains(@id,'dispForm:dts:dynamic:j')][30]";	//Duplicate - Not Required
	
	public static final String EstForecastSales="//td[contains(@id,'dispForm:dts:dynamic:j')][28]";	
	public static final String EstForecastValue="//td[contains(@id,'dispForm:dts:dynamic:j')][28]";	//Duplicate - Not Required
	
	public static final String VarBudgetSales="//td[contains(@id,'dispForm:dts:dynamic:j')][29]";
	public static final String VarBudgetValue="//td[contains(@id,'dispForm:dts:dynamic:j')][29]";	//Duplicate - Not Required
	
	//DaysMix and WTD Mix
	public static final String Mix="//*[@id='dispForm:dts:dynamic:j99']";
	
	// Before Pay Role Adjustment //
	public static final String PRDataTable="//*[@id=\"dispForm:Adjusted:tb\"]/tr";
	public static final String PRPlanCompletedStr="//td[contains(@id,'dispForm:Adjusted:dynamic:j')][1]";
	public static final String PRPlanNotCompletedStr="//*[@id=\"dispForm:Adjusted:dynamic:j464\"]";	//Duplicate - Not Required
	public static final String PRStoreTotal="//td[contains(@id,\"dispForm:Adjusted:13:j\")][1]";	//Store Total Text  
	
	// SMS
	public static final String PRMondayActlSMSWhite="//td[contains(@id,'dispForm:Adjusted:dynamic:j')][2]";
	public static final String PRTuesdayActlSMSWhite="//td[contains(@id,'dispForm:Adjusted:dynamic:j')][5]";
	public static final String PRWednesdayActlSMSWhite="//td[contains(@id,'dispForm:Adjusted:dynamic:j')][8]";
	public static final String PRThursdayActlSMSWhite="//td[contains(@id,'dispForm:Adjusted:dynamic:j')][11]";
	public static final String PRFridayActlSMSWhite="//td[contains(@id,'dispForm:Adjusted:dynamic:j')][14]";
	public static final String PRSaturdayActlSMSWhite="//td[contains(@id,'dispForm:Adjusted:dynamic:j')][17]";
	public static final String PRSundayActlSMSWhite="//td[contains(@id,'dispForm:Adjusted:dynamic:j')][20]";
	public static final String PRWeekToDateActlSMSWhite="//td[contains(@id,'dispForm:Adjusted:dynamic:j')][23]";
	
	public static final String PRMondayActlSMSYellow="//*[@id=\"dispForm:Adjusted:dynamic:j470\"]";	//Duplicate - Not Required
	public static final String PRTuesdayActlSMSYellow="//*[@id=\"dispForm:Adjusted:dynamic:j482\"]";	//Duplicate - Not Required
	public static final String PRWednesdayActlSMSYellow="//*[@id=\"dispForm:Adjusted:dynamic:j494\"]";	//Duplicate - Not Required
	public static final String PRThursdayActlSMSYellow="//*[@id=\"dispForm:Adjusted:dynamic:j506\"]";	//Duplicate - Not Required
	public static final String PRFridayActlSMSYellow="//*[@id=\"dispForm:Adjusted:dynamic:j518\"]";	//Duplicate - Not Required
	public static final String PRSaturdayActlSMSYellow="//*[@id=\"dispForm:Adjusted:dynamic:j530\"]";	//Duplicate - Not Required
	public static final String PRSundayActlSMSYellow="//*[@id=\"dispForm:Adjusted:dynamic:j542\"]";	//Duplicate - Not Required
	public static final String PRWeekToDateActlSMSYellow="//*[@id=\"dispForm:Adjusted:dynamic:j554\"]";	//Duplicate - Not Required
	
	public static final String PRMondayGroupTotalActlSMSWhite="//td[contains(@id,'dispForm:Adjusted:13:j')][2]";
	public static final String PRTuesdayGroupTotalActlSMSWhite="//td[contains(@id,'dispForm:Adjusted:13:j')][5]";
	public static final String PRWednesdaydayGroupTotalActlSMSWhite="//td[contains(@id,'dispForm:Adjusted:13:j')][8]";
	public static final String PRThursdayGroupTotalActlSMSWhite="//td[contains(@id,'dispForm:Adjusted:13:j')][11]";
	public static final String PRFridayGroupTotalActlSMSWhite="//td[contains(@id,'dispForm:Adjusted:13:j')][14]";
	public static final String PRSaturdayGroupTotalActlSMSWhite="//td[contains(@id,'dispForm:Adjusted:13:j')][17]";
	public static final String PRSundayGroupTotalActlSMSWhite="//td[contains(@id,'dispForm:Adjusted:13:j')][20]";
	public static final String PRStoreTtlWeekToDateActlSMSWhite="//td[contains(@id,'dispForm:Adjusted:13:j')][23]";
	
	public static final String PRMondayGroupTotalActlSMSYellow="//*[@id=\"dispForm:Adjusted:13:j470\"]";	//Duplicate - Not Required
	public static final String PRTuesdayGroupTotalActlSMSYellow="//*[@id=\"dispForm:Adjusted:13:j482\"]";	//Duplicate - Not Required
	public static final String PRWednesdaydayGroupTotalActlSMSYellow="//*[@id=\"dispForm:Adjusted:13:j494\"]";	//Duplicate - Not Required
	public static final String PRThursdayGroupTotalActlSMSYellow="//*[@id=\"dispForm:Adjusted:13:j506\"]";	//Duplicate - Not Required
	public static final String PRFridayGroupTotalActlSMSYellow="//*[@id=\"dispForm:Adjusted:13:j518\"]";	//Duplicate - Not Required
	public static final String PRSaturdayGroupTotalActlSMSYellow="//*[@id=\"dispForm:Adjusted:13:j530\"]";	//Duplicate - Not Required
	public static final String PRSundayGroupTotalActlSMSYellow="//*[@id=\"dispForm:Adjusted:13:j542\"]";	//Duplicate - Not Required
	public static final String PRStoreTtlWeekToDateActlSMSYellow="//*[@id=\"dispForm:Adjusted:13:j554\"]";	//Duplicate - Not Required
	//OR
	public static final String PRMondayActlORWhite="//td[contains(@id,'dispForm:Adjusted:dynamic:j')][4]";
	public static final String PRTuesdayActlORWhite="//td[contains(@id,'dispForm:Adjusted:dynamic:j')][7]";
	public static final String PRWednesdayActlORWhite="//td[contains(@id,'dispForm:Adjusted:dynamic:j')][10]";
	public static final String PRThursdayActlORWhite="//td[contains(@id,'dispForm:Adjusted:dynamic:j')][13]";
	public static final String PRFridayActlORWhite="//td[contains(@id,'dispForm:Adjusted:dynamic:j')][16]";
	public static final String PRSaturdayActlORWhite="//td[contains(@id,'dispForm:Adjusted:dynamic:j')][19]";
	public static final String PRSundayActlORWhite="//td[contains(@id,'dispForm:Adjusted:dynamic:j')][22]";
	public static final String PRWeekToDateActlORWhite="//td[contains(@id,'dispForm:Adjusted:dynamic:j')][25]";
	
	public static final String PRMondayActlORYellow="//*[@id=\"dispForm:Adjusted:dynamic:j478\"]";	//Duplicate - Not Required
	public static final String PRTuesdayActlORYellow="//*[@id=\"dispForm:Adjusted:dynamic:j490\"]";	//Duplicate - Not Required	
	public static final String PRWednesdayActlORYellow="//*[@id=\"dispForm:Adjusted:dynamic:j502\"]";	//Duplicate - Not Required
	public static final String PRThursdayActlORYellow="//*[@id=\"dispForm:Adjusted:dynamic:j514\"]";	//Duplicate - Not Required
	public static final String PRFridayActlORYellow="//*[@id=\"dispForm:Adjusted:dynamic:j526\"]";	//Duplicate - Not Required
	public static final String PRSaturdayActlORYellow="//*[@id=\"dispForm:Adjusted:dynamic:j538\"]";	//Duplicate - Not Required
	public static final String PRSundayActlORYellow="//*[@id=\"dispForm:Adjusted:dynamic:j550\"]";	//Duplicate - Not Required
	public static final String PRWeekToDateActlORYellow="//*[@id=\"dispForm:Adjusted:dynamic:j562\"]";	//Duplicate - Not Required
	
	public static final String PRMondayGroupTotalActlORWhite="//td[contains(@id,'dispForm:Adjusted:13:j')][4]";
	public static final String PRTuesdayGroupTotalActlORWhite="//td[contains(@id,'dispForm:Adjusted:13:j')][7]";
	public static final String PRWednesdaydayGroupTotalActlORWhite="//td[contains(@id,'dispForm:Adjusted:13:j')][10]";
	public static final String PRThursdayGroupTotalActlORWhite="//td[contains(@id,'dispForm:Adjusted:13:j')][13]";
	public static final String PRFridayGroupTotalActlORWhite="//td[contains(@id,'dispForm:Adjusted:13:j')][16]";
	public static final String PRSaturdayGroupTotalActlORWhite="//td[contains(@id,'dispForm:Adjusted:13:j')][19]";
	public static final String PRSundayGroupTotalActlORWhite="//td[contains(@id,'dispForm:Adjusted:13:j')][22]";
	public static final String PRStoreTtlWeekToDateActlORWhite="//td[contains(@id,'dispForm:Adjusted:13:j')][25]";
	
	public static final String PRMondayGroupTotalActlORYellow="//*[@id=\"dispForm:Adjusted:13:j478\"]";	//Duplicate - Not Required
	public static final String PRTuesdayGroupTotalActlORYellow="//*[@id=\"dispForm:Adjusted:13:j490\"]";	//Duplicate - Not Required
	public static final String PRWednesdaydayGroupTotalActlORYellow="//*[@id=\"dispForm:Adjusted:13:j502\"]";	//Duplicate - Not Required
	public static final String PRThursdayGroupTotalActlORYellow="//*[@id=\"dispForm:Adjusted:13:j514\"]";	//Duplicate - Not Required
	public static final String PRFridayGroupTotalActlORYellow="//*[@id=\"dispForm:Adjusted:13:j526\"]";	//Duplicate - Not Required
	public static final String PRSaturdayGroupTotalActlORYellow="//*[@id=\"dispForm:Adjusted:13:j538\"]";	//Duplicate - Not Required
	public static final String PRSundayGroupTotalActlORYellow="//*[@id=\"dispForm:Adjusted:13:j550\"]";	//Duplicate - Not Required
	public static final String PRStoreTtlWeekToDateActlORYellow="//*[@id=\"dispForm:Adjusted:13:j562\"]";	//Duplicate - Not Required
	
	//Hours
	public static final String PRMondayActlHrWhite="//td[contains(@id,'dispForm:Adjusted:dynamic:j')][3]";
	public static final String PRTuesdayActlHrWhite="//td[contains(@id,'dispForm:Adjusted:dynamic:j')][6]";
	public static final String PRWednesdayActlHrWhite="//td[contains(@id,'dispForm:Adjusted:dynamic:j')][9]";
	public static final String PRThursdayActlHrWhite="//td[contains(@id,'dispForm:Adjusted:dynamic:j')][12]";
	public static final String PRFridayActlHrWhite="//td[contains(@id,'dispForm:Adjusted:dynamic:j')][15]";
	public static final String PRSaturdayActlHrWhite="//td[contains(@id,'dispForm:Adjusted:dynamic:j')][18]";
	public static final String PRSundayActlHrWhite="//td[contains(@id,'dispForm:Adjusted:dynamic:j')][21]";
	public static final String PRWeekToDateActlHrWhite="//td[contains(@id,'dispForm:Adjusted:dynamic:j')][24]";
	
	public static final String PRMondayActlHrYellow="//*[@id=\"dispForm:Adjusted:dynamic:j474\"]";	//Duplicate - Not Required
	public static final String PRTuesdayActlHrYellow="//*[@id=\"dispForm:Adjusted:dynamic:j486\"]";	//Duplicate - Not Required
	public static final String PRWednesdayActlHrYellow="//*[@id=\"dispForm:Adjusted:dynamic:j498\"]";	//Duplicate - Not Required
	public static final String PRThursdayActlHrYellow="//*[@id=\"dispForm:Adjusted:dynamic:j510\"]";	//Duplicate - Not Required
	public static final String PRFridayActlHrYellow="//*[@id=\"dispForm:Adjusted:dynamic:j522\"]";	//Duplicate - Not Required
	public static final String PRSaturdayActlHrYellow="//*[@id=\"dispForm:Adjusted:dynamic:j534\"]";	//Duplicate - Not Required
	public static final String PRSundayActlHrYellow="//*[@id=\"dispForm:Adjusted:dynamic:j545\"]";	//Duplicate - Not Required
	public static final String PRWeekToDateActlHrYellow="//*[@id=\"dispForm:Adjusted:dynamic:j558\"]";	//Duplicate - Not Required
	
	public static final String PRMondayGroupTotalActlHrWhite="//td[contains(@id,'dispForm:Adjusted:13:j')][3]";
	public static final String PRTuesdayGroupTotalActlHrWhite="//td[contains(@id,'dispForm:Adjusted:13:j')][6]";
	public static final String PRWednesdaydayGroupTotalActlHrWhite="//td[contains(@id,'dispForm:Adjusted:13:j')][9]";
	public static final String PRThursdayGroupTotalActlHrWhite="//td[contains(@id,'dispForm:Adjusted:13:j')][12]";
	public static final String PRFridayGroupTotalActlHrWhite="//td[contains(@id,'dispForm:Adjusted:13:j')][15]";
	public static final String PRSaturdayGroupTotalActlHrWhite="//td[contains(@id,'dispForm:Adjusted:13:j')][18]";
	public static final String PRSundayGroupTotalActlHrWhite="//td[contains(@id,'dispForm:Adjusted:13:j')][21]";
	public static final String PRStoreTtlWeekToDateActlHrWhite="//td[contains(@id,'dispForm:Adjusted:13:j')][24]";
	
	public static final String PRMondayGroupTotalActlHrYellow="//*[@id=\"dispForm:Adjusted:13:j474\"]";	//Duplicate - Not Required
	public static final String PRTuesdayGroupTotalActlHrYellow="//*[@id=\"dispForm:Adjusted:13:j486\"]";	//Duplicate - Not Required
	public static final String PRWednesdaydayGroupTotalActlHrYellow="//*[@id=\"dispForm:Adjusted:13:j498\"]";	//Duplicate - Not Required
	public static final String PRThursdayGroupTotalActlHrYellow="//*[@id=\"dispForm:Adjusted:13:j510\"]";	//Duplicate - Not Required
	public static final String PRFridayGroupTotalActlHrYellow="//*[@id=\"dispForm:Adjusted:13:j522\"]";	//Duplicate - Not Required
	public static final String PRSaturdayGroupTotalActlHrYellow="//*[@id=\"dispForm:Adjusted:13:j534\"]";	//Duplicate - Not Required
	public static final String PRSundayGroupTotalActlHrYellow="//*[@id=\"dispForm:Adjusted:13:j546\"]";	//Duplicate - Not Required
	public static final String PRStoreTtlWeekToDateActlHrYellow="//*[@id=\"dispForm:Adjusted:13:j558\"]";	//Duplicate - Not Required
	
	//Longlife
	public static final String PRMondayActlSMSLL="//td[contains(@id,'dispForm:Adjusted:14:j')][2]";
	public static final String PRTuesdayActlSMSLL="//td[contains(@id,'dispForm:Adjusted:14:j')][5]";
	public static final String PRWednesdayActlSMSLL="//td[contains(@id,'dispForm:Adjusted:14:j')][8]";
	public static final String PRThursdayActlSMSLL="//td[contains(@id,'dispForm:Adjusted:14:j')][11]";
	public static final String PRFridayActlSMSLL="//td[contains(@id,'dispForm:Adjusted:14:j')][14]";
	public static final String PRSaturdayActlSMSLL="//td[contains(@id,'dispForm:Adjusted:14:j')][17]";
	public static final String PRSundayActlSMSLL="//td[contains(@id,'dispForm:Adjusted:14:j')][20]";
	public static final String PRStoreTtlActlSMSLL="//td[contains(@id,'dispForm:Adjusted:14:j')][23]";
	
	public static final String PRMondayActlHrLL="//td[contains(@id,'dispForm:Adjusted:14:j')][3]";
	public static final String PRTuesdayActlHrLL="//td[contains(@id,'dispForm:Adjusted:14:j')][6]";
	public static final String PRWednesdayActlHrLL="//td[contains(@id,'dispForm:Adjusted:14:j')][9]";
	public static final String PRThursdayActlHrLL="//td[contains(@id,'dispForm:Adjusted:14:j')][12]";
	public static final String PRFridayActlHrLL="//td[contains(@id,'dispForm:Adjusted:14:j')][15]";
	public static final String PRSaturdayActlHrLL="//td[contains(@id,'dispForm:Adjusted:14:j')][18]";
	public static final String PRSundayActlHrLL="//td[contains(@id,'dispForm:Adjusted:14:j')][21]";
	public static final String PRStoreTtlActlHrLL="//td[contains(@id,'dispForm:Adjusted:14:j')][24]";
	
	public static final String PRMondayActlORLL="//td[contains(@id,'dispForm:Adjusted:14:j')][4]";
	public static final String PRTuesdayActlORLL="//td[contains(@id,'dispForm:Adjusted:14:j')][7]";
	public static final String PRWednesdayActlORLL="//td[contains(@id,'dispForm:Adjusted:14:j')][10]";
	public static final String PRThursdayActlORLL="//td[contains(@id,'dispForm:Adjusted:14:j')][13]";
	public static final String PRFridayActlORLL="//td[contains(@id,'dispForm:Adjusted:14:j')][16]";
	public static final String PRSaturdayActlORLL="//td[contains(@id,'dispForm:Adjusted:14:j')][19]";
	public static final String PRSundayActlORLL="//td[contains(@id,'dispForm:Adjusted:14:j')][22]";
	public static final String PRStoreTtlActlORLL="//td[contains(@id,'dispForm:Adjusted:14:j')][25]";
	
	public static final String PRMondayActlSMSLLYellow="//*[@id=\"dispForm:Adjusted:14:j470\"]";	//Duplicate - Not Required
	public static final String PRTuesdayActlSMSLLYellow="//*[@id=\"dispForm:Adjusted:14:j482\"]";	//Duplicate - Not Required
	public static final String PRWednesdayActlSMSLLYellow="//*[@id=\"dispForm:Adjusted:14:j494\"]";	//Duplicate - Not Required
	public static final String PRThursdayActlSMSLLYellow="//*[@id=\"dispForm:Adjusted:14:j506\"]";	//Duplicate - Not Required
	public static final String PRFridayActlSMSLLYellow="//*[@id=\"dispForm:Adjusted:14:j518\"]";	//Duplicate - Not Required
	public static final String PRSaturdayActlSMSLLYellow="//*[@id=\"dispForm:Adjusted:14:j530\"]";	//Duplicate - Not Required
	public static final String PRSundayActlSMSLLYellow="//*[@id=\"dispForm:Adjusted:14:j542\"]";	//Duplicate - Not Required
	public static final String PRStoreTtlActlSMSLLYellow="//*[@id=\"dispForm:Adjusted:14:j554\"]";	//Duplicate - Not Required
	
	public static final String PRMondayActlHrLLYellow="//*[@id=\"dispForm:Adjusted:14:j474\"]";	//Duplicate - Not Required
	public static final String PRTuesdayActlHrLLYellow="//*[@id=\"dispForm:Adjusted:14:j486\"]";	//Duplicate - Not Required
	public static final String PRWednesdayActlHrLLYellow="//*[@id=\"dispForm:Adjusted:14:j498\"]";	//Duplicate - Not Required
	public static final String PRThursdayActlHrLLYellow="//*[@id=\"dispForm:Adjusted:14:j510\"]";	//Duplicate - Not Required
	public static final String PRFridayActlHrLLYellow="//*[@id=\"dispForm:Adjusted:14:j522\"]";	//Duplicate - Not Required
	public static final String PRSaturdayActlHrLLYellow="//*[@id=\"dispForm:Adjusted:14:j534\"]";	//Duplicate - Not Required
	public static final String PRSundayActlHrLLYellow="//*[@id=\"dispForm:Adjusted:14:j546\"]";	//Duplicate - Not Required
	public static final String PRStoreTtlActlHrLLYellow="//*[@id=\"dispForm:Adjusted:14:j558\"]";	//Duplicate - Not Required
	
	public static final String PRMondayActlORLLYellow="//*[@id=\"dispForm:Adjusted:14:j478\"]";	//Duplicate - Not Required
	public static final String PRTuesdayActlORLLYellow="//*[@id=\"dispForm:Adjusted:14:j490\"]";	//Duplicate - Not Required
	public static final String PRWednesdayActlORLLYellow="//*[@id=\"dispForm:Adjusted:14:j502\"]";	//Duplicate - Not Required
	public static final String PRThursdayActlORLLYellow="//*[@id=\"dispForm:Adjusted:14:j514\"]";	//Duplicate - Not Required
	public static final String PRFridayActlORLLYellow="//*[@id=\"dispForm:Adjusted:14:j526\"]";	//Duplicate - Not Required
	public static final String PRSaturdayActlORLLYellow="//*[@id=\"dispForm:Adjusted:14:j538\"]";	//Duplicate - Not Required
	public static final String PRSundayActlORLLYellow="//*[@id=\"dispForm:Adjusted:14:j550\"]";	//Duplicate - Not Required
	public static final String PRStoreTtlActlORLLYellow="//*[@id=\"dispForm:Adjusted:14:j562\"]";	//Duplicate - Not Required

	//Seafood and Deli
	public static final String PRMondayActlSMSSD="//td[contains(@id,'dispForm:Adjusted:15:j')][2]";
	public static final String PRTuesdayActlSMSSD="//td[contains(@id,'dispForm:Adjusted:15:j')][5]";
	public static final String PRWednesdayActlSMSSD="//td[contains(@id,'dispForm:Adjusted:15:j')][8]";
	public static final String PRThursdayActlSMSSD="//td[contains(@id,'dispForm:Adjusted:15:j')][11]";
	public static final String PRFridayActlSMSSD="//td[contains(@id,'dispForm:Adjusted:15:j')][14]";
	public static final String PRSaturdayActlSMSSD="//td[contains(@id,'dispForm:Adjusted:15:j')][17]";
	public static final String PRSundayActlSMSSD="//td[contains(@id,'dispForm:Adjusted:15:j')][20]";
	public static final String PRStoreTtlActlSMSSD="//td[contains(@id,'dispForm:Adjusted:15:j')][23]";
	
	public static final String PRMondayActlHrSD="//td[contains(@id,'dispForm:Adjusted:15:j')][3]";
	public static final String PRTuesdayActlHrSD="//td[contains(@id,'dispForm:Adjusted:15:j')][6]";
	public static final String PRWednesdayActlHrSD="//td[contains(@id,'dispForm:Adjusted:15:j')][9]";
	public static final String PRThursdayActlHrSD="//td[contains(@id,'dispForm:Adjusted:15:j')][12]";
	public static final String PRFridayActlHrSD="//td[contains(@id,'dispForm:Adjusted:15:j')][15]";
	public static final String PRSaturdayActlHrSD="//td[contains(@id,'dispForm:Adjusted:15:j')][18]";
	public static final String PRSundayActlHrSD="//td[contains(@id,'dispForm:Adjusted:15:j')][21]";
	public static final String PRStoreTtlActlHrSD="//td[contains(@id,'dispForm:Adjusted:15:j')][24]";
	
	public static final String PRMondayActlORSD="//td[contains(@id,'dispForm:Adjusted:15:j')][4]";
	public static final String PRTuesdayActlORSD="//td[contains(@id,'dispForm:Adjusted:15:j')][7]";
	public static final String PRWednesdayActlORSD="//td[contains(@id,'dispForm:Adjusted:15:j')][10]";
	public static final String PRThursdayActlORSD="//td[contains(@id,'dispForm:Adjusted:15:j')][13]";
	public static final String PRFridayActlORSD="//td[contains(@id,'dispForm:Adjusted:15:j')][16]";
	public static final String PRSaturdayActlORSD="//td[contains(@id,'dispForm:Adjusted:15:j')][19]";
	public static final String PRSundayActlORSD="//td[contains(@id,'dispForm:Adjusted:15:j')][22]";
	public static final String PRStoreTtlActlORSD="//td[contains(@id,'dispForm:Adjusted:15:j')][25]";
	
	public static final String PRMondayActlSMSSDYellow="//*[@id=\"dispForm:Adjusted:15:j470\"]";	//Duplicate - Not Required
	public static final String PRTuesdayActlSMSSDYellow="//*[@id=\"dispForm:Adjusted:15:j482\"]";	//Duplicate - Not Required
	public static final String PRWednesdayActlSMSSDYellow="//*[@id=\"dispForm:Adjusted:15:j494\"]";	//Duplicate - Not Required
	public static final String PRThursdayActlSMSSDYellow="//*[@id=\"dispForm:Adjusted:15:j506\"]";	//Duplicate - Not Required
	public static final String PRFridayActlSMSSDYellow="//*[@id=\"dispForm:Adjusted:15:j518\"]";	//Duplicate - Not Required
	public static final String PRSaturdayActlSMSSDYellow="//*[@id=\"dispForm:Adjusted:15:j530\"]";	//Duplicate - Not Required
	public static final String PRSundayActlSMSSDYellow="//*[@id=\"dispForm:Adjusted:15:j542\"]";	//Duplicate - Not Required
	public static final String PRStoreTtlActlSMSSDYellow="//*[@id=\"dispForm:Adjusted:15:j554\"]";	//Duplicate - Not Required
	
	public static final String PRMondayActlHrSDYellow="//*[@id=\"dispForm:Adjusted:15:j474\"]";	//Duplicate - Not Required
	public static final String PRTuesdayActlHrSDYellow="//*[@id=\"dispForm:Adjusted:15:j486\"]";	//Duplicate - Not Required
	public static final String PRWednesdayActlHrSDYellow="//*[@id=\"dispForm:Adjusted:15:j498\"]";	//Duplicate - Not Required
	public static final String PRThursdayActlHrSDYellow="//*[@id=\"dispForm:Adjusted:15:j510\"]";	//Duplicate - Not Required
	public static final String PRFridayActlHrSDYellow="//*[@id=\"dispForm:Adjusted:15:j522\"]";	//Duplicate - Not Required
	public static final String PRSaturdayActlHrSDYellow="//*[@id=\"dispForm:Adjusted:15:j534\"]";	//Duplicate - Not Required
	public static final String PRSundayActlHrSDYellow="//*[@id=\"dispForm:Adjusted:15:j546\"]";	//Duplicate - Not Required
	public static final String PRStoreTtlActlHrSDYellow="//*[@id=\"dispForm:Adjusted:15:j558\"]";	//Duplicate - Not Required
	
	public static final String PRMondayActlORSDYellow="//*[@id=\"dispForm:Adjusted:15:j478\"]";	//Duplicate - Not Required
	public static final String PRTuesdayActlORSDYellow="//*[@id=\"dispForm:Adjusted:15:j490\"]";	//Duplicate - Not Required
	public static final String PRWednesdayActlORSDYellow="//*[@id=\"dispForm:Adjusted:15:j502\"]";	//Duplicate - Not Required
	public static final String PRThursdayActlORSDYellow="//*[@id=\"dispForm:Adjusted:15:j514\"]";	//Duplicate - Not Required
	public static final String PRFridayActlORSDYellow="//*[@id=\"dispForm:Adjusted:15:j526\"]";	//Duplicate - Not Required
	public static final String PRSaturdayActlORSDYellow="//*[@id=\"dispForm:Adjusted:15:j538\"]";	//Duplicate - Not Required
	public static final String PRSundayActlORSDYellow="//*[@id=\"dispForm:Adjusted:15:j550\"]";	//Duplicate - Not Required
	public static final String PRStoreTtlActlORSDYellow="//*[@id=\"dispForm:Adjusted:15:j562\"]";	//Duplicate - Not Required
	
	public static final String PRLLTitle="//td[contains(@id,'dispForm:Adjusted:14:j')][1]";	//Longlife Text
	public static final String PRSDTitle="//td[contains(@id,'dispForm:Adjusted:15:j')][1]";	//Seafood and Deli Text
}