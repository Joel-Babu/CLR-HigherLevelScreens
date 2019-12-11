package au.wow.WLPmain.objects;

import java.util.List;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class DailyTradingStatementBusinessUnitsObjects 
{
	@FindBy(xpath= "/html/frameset/frame[2]") 
	public WebElement DataFrame;
	
	@FindBy(xpath= "//*[@id='form1:buttonLogoff']")
	public WebElement Logoff;
	
	@FindBy(xpath= "/html/frameset/frame[1]")
	public WebElement TitleFrame;
	
	@FindBy(xpath= "//span[@id='j0:status.start' and @style='display: none;']")
	public WebElement Spinner;
	
	@FindBy(xpath= "//*[@id='dispForm']/table[3]/tbody/tr/td[2]")
	public WebElement HistoryViewTitle;
	
	@FindBy(xpath= "//*[@id='dispForm']/table[3]/tbody/tr/td[1]")
	public WebElement BUToViewTitle;
	
	@FindBy(xpath= "//*[@id='dispForm']/table[3]/tbody/tr/td[1]/select")
	public WebElement BUToViewDropDown;
	
	@FindBy(xpath= "//*[@id='dispForm']/table[3]/tbody/tr/td[2]/select")
	public WebElement HistoryViewDropdown;
	
	@FindBy(xpath= "//*[@id='dispForm:dtsDivArea:0:j244']")
	public WebElement MondayPlndSMSVal;
	
	public static final String RowNameTotal="//*[@id='frmDetails:ctrStorePlanningDetailsTable:dynamic:colOrgDesc']";
	public static final String Table="//*[@id='dispForm:dtsDiv']/tbody/tr";

	//Sales
	public static final String Department="//td[contains(@id,'dispForm:dtsDiv:dynamic:j')][1]";	//Department Column
	public static final String BUTotal="//td[contains(@id,'dispForm:dtsDiv:dynamic:j') and contains(@class,'dtsDeptColNew')]";	//Store Total Text
	
	//Day Wise Plan Values
	public static final String MondayPlndSales="//td[contains(@id,'dispForm:dtsDiv:dynamic:j')][2]";
	public static final String TuesdayPlndSales="//td[contains(@id,'dispForm:dtsDiv:dynamic:j')][5]";
	public static final String WednesdayPlndSales="//td[contains(@id,'dispForm:dtsDiv:dynamic:j')][8]";
	public static final String ThursdayPlndSales="//td[contains(@id,'dispForm:dtsDiv:dynamic:j')][11]";
	public static final String FridayPlndSales="//td[contains(@id,'dispForm:dtsDiv:dynamic:j')][14]";
	public static final String SaturdayPlndSales="//td[contains(@id,'dispForm:dtsDiv:dynamic:j')][17]";
	public static final String SundayPlndSales="//td[contains(@id,'dispForm:dtsDiv:dynamic:j')][20]";
		
	//Day Wise Actual Values
	public static final String MondayActlSales="//td[contains(@id,'dispForm:dtsDiv:dynamic:j')][3]";
	public static final String TuesdayActlSales="//td[contains(@id,'dispForm:dtsDiv:dynamic:j')][6]";
	public static final String WednesdayActlSales="//td[contains(@id,'dispForm:dtsDiv:dynamic:j')][9]";
	public static final String ThursdayActlSales="//td[contains(@id,'dispForm:dtsDiv:dynamic:j')][12]";
	public static final String FridayActlSales="//td[contains(@id,'dispForm:dtsDiv:dynamic:j')][15]";
	public static final String SaturdayActlSales="//td[contains(@id,'dispForm:dtsDiv:dynamic:j')][18]";
	public static final String SundayActlSales="//td[contains(@id,'dispForm:dtsDiv:dynamic:j')][21]";
	
	
	////Day Wise LY Values
	public static final String MondayLYSales="//td[contains(@id,'dispForm:dtsDiv:dynamic:j')][4]";
	public static final String TuesdayLYSales="//td[contains(@id,'dispForm:dtsDiv:dynamic:j')][7]";
	public static final String WednesdayLYSales="//td[contains(@id,'dispForm:dtsDiv:dynamic:j')][10]";
	public static final String ThursdayLYSales="//td[contains(@id,'dispForm:dtsDiv:dynamic:j')][13]";
	public static final String FridayLYSales="//td[contains(@id,'dispForm:dtsDiv:dynamic:j')][16]";
	public static final String SaturdayLYSales="//td[contains(@id,'dispForm:dtsDiv:dynamic:j')][19]";
	public static final String SundayLYSales="//td[contains(@id,'dispForm:dtsDiv:dynamic:j')][22]";
		
	//Week-to-Date Values
	public static final String WeekToDatePlndSales="//td[contains(@id,'dispForm:dtsDiv:dynamic:j')][23]";	
	public static final String WeekToDateActlSales="//td[contains(@id,'dispForm:dtsDiv:dynamic:j')][24]";
	public static final String WeekToDateLYSales="//td[contains(@id,'dispForm:dtsDiv:dynamic:j')][26]";
	public static final String WeekToDateVarSales="//td[contains(@id,'dispForm:dtsDiv:dynamic:j')][25]";
	
	//Budget
	public static final String BudgetSales="//td[contains(@id,'dispForm:dtsDiv:dynamic:j')][27]";		
	public static final String EstForecastSales="//td[contains(@id,'dispForm:dtsDiv:dynamic:j')][28]";	
	public static final String VarBudgetSales="//td[contains(@id,'dispForm:dtsDiv:dynamic:j')][29]";
	public static final String AllowedWages="//td[contains(@id,'dispForm:dtsDiv:dynamic:j')][30]";
}