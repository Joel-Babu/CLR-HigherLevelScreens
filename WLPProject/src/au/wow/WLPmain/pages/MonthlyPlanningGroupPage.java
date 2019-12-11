package au.wow.WLPmain.pages;

import java.io.File;
import java.io.IOException;
import java.math.BigDecimal;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.apache.logging.log4j.core.util.datetime.Format;
import org.apache.tools.ant.filters.TokenFilter.Trim;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.remote.server.handler.SwitchToFrame;
import org.openqa.selenium.remote.server.handler.interactions.SendKeyToActiveElement;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import au.wow.WLPmain.objects.MonthlyPlanningGroupObjects;
import au.wow.WLPmain.objects.MonthlyPlanningStoreObjects;
import au.wow.WLPmain.objects.WeeklyPlanningStoreObjects;
import au.wow.wlp.utils.BasePage;
import au.wow.wlp.utils.CommonRepository;
import au.wow.wlp.utils.ExtentReportsScreenshotCode;
import au.wow.wlp.utils.SQLWrapper;
import au.wow.wlp.utils.TestBase;

import com.relevantcodes.extentreports.ExtentReports;
import com.relevantcodes.extentreports.ExtentTest;
import com.relevantcodes.extentreports.LogStatus;
import com.thoughtworks.selenium.webdriven.commands.Click;
import com.thoughtworks.selenium.webdriven.commands.GetText;

public class MonthlyPlanningGroupPage extends TestBase
{
	CommonFunctions common = PageFactory.initElements(this.getDriver(),CommonFunctions.class);
	public Logger log = LogManager.getLogger(MonthlyPlanningGroupPage.class);
	SQLWrapper sql = new SQLWrapper(log);
	WeeklyPlanningStoreObjects objWeeklyPlanningStoreObjects;
	MonthlyPlanningStoreObjects objMonthlyPlanningStoreObjects;
	MonthlyPlanningGroupObjects objMonthlyPlanningGroupObjects;
	static ExtentReportsScreenshotCode ScreenShotUtil; 
	static WebDriverWait wait;
	String image;
	public MonthlyPlanningGroupPage()
	{
		// TODO Auto-generated constructor stub
		super();
	}
	
	//Select Group from Dropdown
	public void SelectGroupDropdownToView(MonthlyPlanningGroupObjects objMonthlyPlanningGroupObjects,ExtentTest extentTest,String Group) throws Exception
	{
		try
		{	
			if((isDisplayed(objMonthlyPlanningGroupObjects.HistoryViewTitle)) && (getText(objMonthlyPlanningGroupObjects.HistoryViewTitle).equalsIgnoreCase("Select History View:")))
			{
				selectByVisibleText(objMonthlyPlanningGroupObjects.SelectGroup,Group);
				Report_AddStep("testcase","Group To Display is Selected Successfully from the Dropdown" ,"","", "Pass");
				htmlToExtent(cName,mName,extentTest,driver1, ""+Group+" is Selected Successfully from department drop down ;;;Pass");
			}
			else
			{
				Report_AddStep("testcase","Failed to Select Group Option from DropDown" ,"","", "Fail");
				htmlToExtent(cName,mName,extentTest,driver1, "Failed to select "+Group+" from department drop down ;;;Fail");
			}
		}
		catch(Exception e)
		{
			System.out.println("Exception Occured" +e.getMessage());
			Report_AddStep("testcase","Failed to Select Group Option from DropDown" ,"","", "Fail");
			htmlToExtent(cName,mName,extentTest,driver1, "Exception occured while selecting department ;;;Fail");
		}
	}	
	
	//Get Total
		public List<List<Object>> GetTotal(ExtentTest extentTest,String RowName,String FieldToVerify)
		{
			String Dept,Data=null;
			double CalcValue;
			BigDecimal FinalUIValue=null;
			int columnsize=2;
			int rowcount= WeeklyPlanningStoreTablecount();
			System.out.println("insiderow"+rowcount);
			List<List<Object>> UIValues= new  ArrayList<List<Object>>();	
				List<Object> row = new ArrayList<>(columnsize);
				WebElement RwNm = prepareWebElementWithDynamicXpathWithInt(RowName,rowcount-1);
				Dept = RwNm.getText();
				WebElement Element = prepareWebElementWithDynamicXpathWithInt(FieldToVerify,rowcount-1);
				Data = getText(Element);
				CalcValue=TrimData(Data);
				FinalUIValue =ConvertBigDecimal(CalcValue);
				row.add(Dept);
				row.add(FinalUIValue);	
				UIValues.add(row);
			return UIValues;
		}
		
		//Get Total MP New Screen
				public List<List<Object>> GetTotalMP(ExtentTest extentTest,String RowName,String FieldToVerify)
				{
					String Dept,Data=null;
					double CalcValue;
					BigDecimal FinalUIValue=null;
					int columnsize=2;
					int rowcount= WeeklyPlanningStoreTablecount();
					System.out.println("insiderow"+rowcount);
					List<List<Object>> UIValues= new  ArrayList<List<Object>>();	
						List<Object> row = new ArrayList<>(columnsize);
						WebElement RwNm = prepareWebElementWithDynamicXpathWithInt(RowName,rowcount-8);
						Dept = RwNm.getText();
						WebElement Element = prepareWebElementWithDynamicXpathWithInt(FieldToVerify,rowcount-8);
						Data = getText(Element);
						CalcValue=TrimData(Data);
						FinalUIValue =ConvertBigDecimal(CalcValue);
						row.add(Dept);
						row.add(FinalUIValue);	
						UIValues.add(row);
					return UIValues;
				}
	
		//Get Total
		public List<List<Object>> WPGetTotal(ExtentTest extentTest,String RowName,String FieldToVerify)
		{
				String Dept,Data=null;
				double CalcValue;
				BigDecimal FinalUIValue=null;
				int columnsize=2;
				int rowcount= WeeklyPlanningStoreTablecount();
				System.out.println("insiderow"+rowcount);
				List<List<Object>> UIValues= new  ArrayList<List<Object>>();	
					List<Object> row = new ArrayList<>(columnsize);
					WebElement RwNm = prepareWebElementWithDynamicXpathWithInt(RowName,rowcount-2);
					Dept = RwNm.getText();
					WebElement Element = prepareWebElementWithDynamicXpathWithInt(FieldToVerify,rowcount-2);
					Data = getText(Element);
					CalcValue=TrimData(Data);
					FinalUIValue =ConvertBigDecimal(CalcValue);
					row.add(Dept);
					row.add(FinalUIValue);	
					UIValues.add(row);
				return UIValues;
		}
				
	//Verify Store Total - Wages and Paid Hours
			public BigDecimal GetFinalTotalValue(ExtentTest extentTest,String FieldToVerify) throws Exception
			{
				int i;
				double Data,FinalTotal=0.0,FinalTotalSum=0.0;
				BigDecimal FinalUIValue=null;
				String Value = null;
				try
				{	
					int rowcount= WeeklyPlanningStoreTablecount();
					System.out.println("insiderow"+rowcount);
					for(i=1;i<=rowcount-2;i++)
					{
						WebElement Element = prepareWebElementWithDynamicXpathWithInt(FieldToVerify, i);
						
						Data=TrimData(Value);
						FinalTotal = FinalTotal+Data;
						FinalTotalSum = Math.round(FinalTotal*100)/100D;
						FinalUIValue =ConvertBigDecimal(FinalTotalSum);
						
					}
					Report_AddStep("testcase","Summation of All the Department Value is :"+FinalTotal+" displayed successfully" ,"","", "Pass");
					htmlToExtent(cName,mName,extentTest,driver1, "Summation of All the Department Value is : "+FinalTotal+" displayed successfully ;;;Pass");
					return FinalUIValue;
				}
				catch(Exception e)
				{
					System.out.println("Exception Occured" +e.getMessage());
					Report_AddStep("testcase","Failed to Fetch Department Total Value" ,"","", "Fail");
					htmlToExtent(cName,mName,extentTest,driver1, "Exception Occured while fetching Department value ;;;Fail");
				}
				return FinalUIValue;	
			}
}
