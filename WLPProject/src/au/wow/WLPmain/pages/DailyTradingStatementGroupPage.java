package au.wow.WLPmain.pages;

import java.io.File;
import java.io.IOException;
import java.math.BigDecimal;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.Arrays;
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

import au.wow.WLPmain.objects.DailyTradingStatementGroupObjects;
import au.wow.WLPmain.objects.DailyTradingStatementObjects;
import au.wow.WLPmain.objects.DailyTradingStatementZoneObjects;
import au.wow.WLPmain.objects.MonthlyPlanningGroupObjects;
import au.wow.WLPmain.objects.WeeklyPlanningStoreObjects;
import au.wow.WLPmain.objects.WeeklyTradingStatementStoreObjects;
import au.wow.wlp.utils.BasePage;
import au.wow.wlp.utils.CommonRepository;
import au.wow.wlp.utils.ExtentReportsScreenshotCode;
import au.wow.wlp.utils.SQLWrapper;
import au.wow.wlp.utils.TestBase;

import com.relevantcodes.extentreports.ExtentReports;
import com.relevantcodes.extentreports.ExtentTest;
import com.relevantcodes.extentreports.LogStatus;
import com.thoughtworks.selenium.webdriven.commands.Click;
import com.thoughtworks.selenium.webdriven.commands.GetAttribute;
import com.thoughtworks.selenium.webdriven.commands.GetText;

public class DailyTradingStatementGroupPage extends TestBase
{
	CommonFunctions common = PageFactory.initElements(this.getDriver(),CommonFunctions.class);
	public Logger log = LogManager.getLogger(DailyTradingStatementGroupPage.class);
	SQLWrapper sql = new SQLWrapper(log);
	DailyTradingStatementObjects objDailyTradingStatementObjects;
	DailyTradingStatementGroupObjects objDailyTradingStatementGroupObjects;
	static ExtentReportsScreenshotCode ScreenShotUtil; 
	static WebDriverWait wait;
	String image;
	DailyTradingStatementZoneObjects objDailyTradingStatementZoneObjects;
	
	public DailyTradingStatementGroupPage()
	{
		super();
	}
	
	//History Selection
	public void SelectHistoryDropdownToView(DailyTradingStatementGroupObjects objDailyTradingStatementGroupObjects,ExtentTest extentTest,String history) throws Exception
	{
		try
		{
			if((isDisplayed(objDailyTradingStatementGroupObjects.HistoryViewTitle)) && (getText(objDailyTradingStatementGroupObjects.HistoryViewTitle).contains("View")))
			{
				selectByVisibleText(objDailyTradingStatementGroupObjects.HistoryViewDropdown,history );
				Report_AddStep("testcase","History To Display is Selected Successfully from the Dropdown" ,"","", "Pass");
				htmlToExtent(cName,mName,extentTest,driver1, ""+history+" is Selected Successfully from history Dropdown ;;;Pass");
			}
			else
			{
				Report_AddStep("testcase","Failed to Select History Option from DropDown" ,"","", "Fail");
				htmlToExtent(cName,mName,extentTest,driver1, "Failed to Select "+history+" from history Dropdown ;;;Fail");
			}
		}
		catch(Exception e)
		{
			System.out.println("Exception Occured" +e.getMessage());		
			Report_AddStep("testcase","Failed to Select History Option from DropDown" ,"","", "Fail");
			htmlToExtent(cName,mName,extentTest,driver1, "Exception occured while selecting history Dropdown ;;;Fail");
		}
	}
	
	//Verify Page HyperLink 
	public void VerifyHyperLink(WeeklyTradingStatementStoreObjects objWeeklyTradingStatementStoreObjects,ExtentTest extentTest) throws Exception
	{
		try
		{
			if(getText(objWeeklyTradingStatementStoreObjects.ZoneHyperLink).equalsIgnoreCase("Zone") && getText(objWeeklyTradingStatementStoreObjects.GroupHyperLink).equalsIgnoreCase("Group"))
			{		
				Report_AddStep("testcase","Group and Zone HyperLink are enabled for Store Manager Credentials" ,"","","Pass");
				htmlToExtent(cName,mName,extentTest,driver1, "Group and Zone HyperLink are enabled for Store Manager Credentials ;;;Pass");
			}
			else
			{		
				Report_AddStep("testcase","Group and Zone HyperLink are not enabled for Store Manager Credentials" ,"","","Fail");
				htmlToExtent(cName,mName,extentTest,driver1, "Group and Zone HyperLink are not enabled for Store Manager Credentials ;;;Fail");
			}
		}
		catch(Exception e)
		{
			System.out.println("Exception Occured" +e.getMessage());		
			Report_AddStep("testcase","Group and Zone HyperLink are not enabled for Store Manager Credentials" ,"","","Fail");
			htmlToExtent(cName,mName,extentTest,driver1, "Group and Zone HyperLink are not enabled for Store Manager Credentials ;;;Fail");
		}
	}
	
	//Select Group from Dropdown
	public void SelectGroupDropdownToView(DailyTradingStatementGroupObjects objDailyTradingStatementGroupObjects,ExtentTest extentTest,String Group) throws Exception
	{
		try
		{	
			if((isDisplayed(objDailyTradingStatementGroupObjects.HistoryViewTitle)) && (getText(objDailyTradingStatementGroupObjects.GroupToViewTitle).contains("Group:")) ||
					(isDisplayed(objDailyTradingStatementGroupObjects.HistoryViewTitle)) && (getText(objDailyTradingStatementGroupObjects.GroupToViewTitle).contains("Zone:")))
			{
				selectByVisibleText(objDailyTradingStatementGroupObjects.GroupToViewDropDown,Group);
				Report_AddStep("testcase","Group To Display is Selected Successfully from the Dropdown" ,"","", "Pass");
				htmlToExtent(cName,mName,extentTest,driver1, ""+Group+" is Selected Successfully from Group Dropdown ;;;Pass");
			}
			else
			{
				Report_AddStep("testcase","Failed to Select Group Option from DropDown" ,"","", "Fail");
				htmlToExtent(cName,mName,extentTest,driver1, "Failed to Select "+Group+" from Group DropDown ;;;Fail");
			}
		}
			catch(Exception e)
			{
				System.out.println("Exception Occured" +e.getMessage());
				Report_AddStep("testcase","Failed to Select Group Option from DropDown" ,"","", "Fail");
				htmlToExtent(cName,mName,extentTest,driver1, "Exception occured while selecting Group ;;;Fail");
			}
		}	
			
			//Get UI Value Single column
			public List<List<Object>> WeekToDateData(String Value,DailyTradingStatementObjects objDailyTradingStatementObjects,String WeekToDate)
			{
				try 
				{
					int i,initial = 0,columnsize=1;
					WebElement Department = null,WeektoDateElmt;
					String Dept,FullWeek=null;
					double FullWeekVal;
					SwitchFrame(objDailyTradingStatementObjects.DataFrame);
					List<List<Object>> UIValues= new  ArrayList<List<Object>>();	
					int RowCount =TradingStatementTableCount();	
					
					//Initialize Value for Loops
					if(Value.equalsIgnoreCase("Sales"))
						initial=0;
					if(Value.equalsIgnoreCase("SMS"))
						initial=1;
					if(Value.equalsIgnoreCase("Hours"))
						initial=2;
					if(Value.equalsIgnoreCase("WagesPercentage"))
						initial=3;
					if(Value.equalsIgnoreCase("Items"))
						initial=4;
					if(Value.equalsIgnoreCase("Customers"))
						initial=56;
					
					for(i=initial;i<=RowCount-7;i=i+5)
					{
						List<Object> row = new ArrayList<>(columnsize);
						//i = loopValue(i,Value);
						System.out.println(i);
						if(i>57 && Value.equalsIgnoreCase("Sales"))
							break;
						if(i>58 && Value.equalsIgnoreCase("SMS"))
							break;
						if(i>59 && Value.equalsIgnoreCase("Hours"))
							break;
						if(i>60 && Value.equalsIgnoreCase("WagesPercentage"))
							break;
						if(i>61 && Value.equalsIgnoreCase("Items"))
							break;
						if(i>62 && Value.equalsIgnoreCase("Customers"))
							break;
						
				// To Get Department and Store Total Column Names	
						if(Value.equalsIgnoreCase("Sales"))
							Department = prepareWebElementWithDynamicXpathWithInt(objDailyTradingStatementObjects.Department, i); 
						if(Value.equalsIgnoreCase("SMS"))
							Department = prepareWebElementWithDynamicXpathWithInt(objDailyTradingStatementObjects.Department, i-1);
						if(Value.equalsIgnoreCase("Hours"))
							Department = prepareWebElementWithDynamicXpathWithInt(objDailyTradingStatementObjects.Department, i-2);
						if(Value.equalsIgnoreCase("Customers"))
							Department = prepareWebElementWithDynamicXpathWithInt(objDailyTradingStatementObjects.Department, i-5);
						if(Value.equalsIgnoreCase("WagesPercentage"))
						{
							if(i==46 || i==49)
								Department = prepareWebElementWithDynamicXpathWithInt(objDailyTradingStatementObjects.Department, i-1);
							else
								Department = prepareWebElementWithDynamicXpathWithInt(objDailyTradingStatementObjects.Department, i-3);	
						}
						if(Value.equalsIgnoreCase("Items"))
						{
							if(i==47 || i==50)
								Department = prepareWebElementWithDynamicXpathWithInt(objDailyTradingStatementObjects.Department, i-2);
							else
								Department = prepareWebElementWithDynamicXpathWithInt(objDailyTradingStatementObjects.Department, i-4);
						}
						WeektoDateElmt = prepareWebElementWithDynamicXpathWithInt(WeekToDate, i);
						
						Dept = getText(Department);
						FullWeek = getText(WeektoDateElmt);
					
						FullWeekVal= TrimData(FullWeek);
						
						row.add(Dept);	
						row.add(FullWeekVal);
						
						UIValues.add(row);
						System.out.println(UIValues);
					}
					return UIValues;
				}
				catch (Exception e) 
				{
					System.out.println("Exception occured while getting DTS Screen Data");
					e.printStackTrace();
				}
				return null;
				}
			
			//Get UI Total to Compare with DB Value
			public List<List<Object>> WeekToDateTotal(String Value,DailyTradingStatementObjects objDailyTradingStatementObjects,String WeekToDate)
			{
				try
				{
					int i=0,columnsize=2;
					WebElement Department,WeektoDateElmt;
					String Dept,FullWeek=null;
					double FullWeekVal;
					List<List<Object>> UIValues= new  ArrayList<List<Object>>();	
					List<Object> row = new ArrayList<>(columnsize);
					
					//Initialize Value for Loops
					if(Value.equalsIgnoreCase("Sales"))
						i=63;
					if(Value.equalsIgnoreCase("SMS"))
						i=64;
					if(Value.equalsIgnoreCase("Hours"))
						i=65;
					if(Value.equalsIgnoreCase("Wages"))
						i=66;
					if(Value.equalsIgnoreCase("WagesPercentage"))
						i=67;
					if(Value.equalsIgnoreCase("Items"))
						i=68;
					if(Value.equalsIgnoreCase("Customers"))
						i=69;
					
				// To Get Department and Store Total Column Names	
						
						Department = prepareWebElementWithDynamicXpathWithInt(objDailyTradingStatementObjects.StoreTotal,63); 
						WeektoDateElmt = prepareWebElementWithDynamicXpathWithInt(WeekToDate, i);
						
						Dept = getText(Department);
						FullWeek = getText(WeektoDateElmt);

						FullWeekVal= TrimData(FullWeek);
						
						row.add(Dept);	
						row.add(FullWeekVal);
						
						UIValues.add(row);
						System.out.println(UIValues);
						return UIValues;
				}
				catch (Exception e) 
				{
					System.out.println("Exception occured while Switching the Frame");
					e.printStackTrace();
				}
				return null;
			}
		
	//Compare UI and DB values
	public void CompareValues(ExtentTest extentTest,List<List<Object>> DBValues,List<List<Object>> UIValues) throws Exception
	{
		try
		{	
			int RowCount = DBValues.size();
			double CalcuatedUIValue = 0,CalcuatedDBValue=0,RoundOffValue = 0,RoundOffValue1=0;
			boolean result = false;
			String DBName=null,UIName=null;
			BigDecimal FinalDBValue = null,FinalUIValue=null;
			System.out.println(UIValues);
			System.out.println(DBValues);
		
			for(int i=0;i<=RowCount-1;i++)
			{
				for(int j=0;j<2;j++)
				{
					if(j!=0)
					{
						
						CalcuatedDBValue =new Double(DBValues.get(i).get(j).toString());
						RoundOffValue = Math.round(CalcuatedDBValue);	
						FinalDBValue =ConvertBigDecimal(CalcuatedDBValue);
						System.out.println(FinalDBValue);
						
						CalcuatedUIValue =new Double(UIValues.get(i).get(j).toString());
						RoundOffValue1 = Math.round(CalcuatedUIValue);
						FinalUIValue =ConvertBigDecimal(CalcuatedUIValue);
						System.out.println(FinalUIValue);
						BigDecimal biggy =FinalUIValue.subtract(FinalDBValue);
						int Variance = biggy.intValue();
						if(Variance <=1 && Variance >=-1)
							result=true;
						else
							result=false;
					}
					else
					{
						DBName =new String(DBValues.get(i).get(j).toString());
						UIName =new String(UIValues.get(i).get(j).toString());
						System.out.println(UIName+" "+DBName);
						if((DBName.trim()).equalsIgnoreCase(UIName.trim()))
							result=true;
						else
						{
							result=false;
							System.out.println(UIName+" "+DBName);
							break;
							
						}
					}
				}
				if(result==true)
				{
					Report_AddStep("testcase","System Fetch the Values from DB for : "+DBName+" and Displayed Correctly" ,""+CalcuatedDBValue+"",""+CalcuatedUIValue+"", "Pass");
					htmlToExtent(cName,mName,extentTest,driver1, "Value displayed Correctly for : "+DBName+"; Actual Value = "+CalcuatedUIValue+"; Excepted Value = "+CalcuatedDBValue+";Pass");
				}
				else
				{
					Report_AddStep("testcase","Failed to Display the UI and DB Values Correctly "+DBName+" and value is "+CalcuatedDBValue+"",""+CalcuatedUIValue+"" ,"", "Fail");
					htmlToExtent(cName,mName,extentTest,driver1, "Failed to display data Correctly for : "+DBName+"; Actual Value = "+CalcuatedUIValue+"; Excepted Value = "+CalcuatedDBValue+";Fail");
				}
			}
		}
		catch(Exception e)
		{
			System.out.println("Exception Occured" +e.getMessage());
			Report_AddStep("testcase","Failed to Verify UI and DB Values" ,"","", "Fail");
			htmlToExtent(cName,mName,extentTest,driver1, "Exception Occured while comparing data;;;Fail");
		}
	}
	
	//Get UI Value to Compare with DB Value
		public List<List<Object>> DayWiseUIData(String Value,String Column,DailyTradingStatementObjects objDailyTradingStatementObjects)
		{
			try 
			{
				int i,initial = 0,columnsize=2;
				WebElement Department = null,DayElmt;
				String Dept,Day=null;
				double DayVal;
				List<List<Object>> UIValues= new  ArrayList<List<Object>>();	
				int RowCount =TradingStatementTableCount();	
				List<Object> row;
				
				//Initialize Value for Loops
				if(Value.equalsIgnoreCase("Sales"))
					initial=0;
				if(Value.equalsIgnoreCase("SMS"))
					initial=1;
				if(Value.equalsIgnoreCase("Hours"))
					initial=2;
				if(Value.equalsIgnoreCase("WagesPercentage"))
					initial=3;
				if(Value.equalsIgnoreCase("Items"))
					initial=4;
				if(Value.equalsIgnoreCase("Customers"))
					initial=56;
				
				for(i=initial;i<=RowCount-7;i=i+5)
				{
						row = new ArrayList<>(columnsize);
						
						
						//To Break the loop after fetching all Department Values
						if(i>57 && Value.equalsIgnoreCase("Sales"))
							break;
						if(i>58 && Value.equalsIgnoreCase("SMS"))
							break;
						if(i>59 && Value.equalsIgnoreCase("Hours"))
							break;
						if(i>60 && Value.equalsIgnoreCase("WagesPercentage"))
							break;
						if(i>61 && Value.equalsIgnoreCase("Items"))
							break;
						if(i>62 && Value.equalsIgnoreCase("Customers"))
							break;	
						
						// To Get Department and Store Total Column Names	
						if(Value.equalsIgnoreCase("Sales"))
							Department = prepareWebElementWithDynamicXpathWithInt(objDailyTradingStatementObjects.Department, i); 
						if(Value.equalsIgnoreCase("SMS"))
							Department = prepareWebElementWithDynamicXpathWithInt(objDailyTradingStatementObjects.Department, i-1);
						if(Value.equalsIgnoreCase("Hours"))
							Department = prepareWebElementWithDynamicXpathWithInt(objDailyTradingStatementObjects.Department, i-2);
						if(Value.equalsIgnoreCase("Customers"))
							Department = prepareWebElementWithDynamicXpathWithInt(objDailyTradingStatementObjects.Department, i-5);
						if(Value.equalsIgnoreCase("WagesPercentage"))
						{
							if(i==46 || i==49)
								Department = prepareWebElementWithDynamicXpathWithInt(objDailyTradingStatementObjects.Department, i-1);
							else
								Department = prepareWebElementWithDynamicXpathWithInt(objDailyTradingStatementObjects.Department, i-3);	
						}
						if(Value.equalsIgnoreCase("Items"))
						{
							if(i==47 || i==50)
								Department = prepareWebElementWithDynamicXpathWithInt(objDailyTradingStatementObjects.Department, i-2);
							else
								Department = prepareWebElementWithDynamicXpathWithInt(objDailyTradingStatementObjects.Department, i-4);
						}
					
							for(int k=0;k<7;k++)
							{
								row = new ArrayList<>(columnsize);
								String Datej = ("Datek".replace("k", ""+k));
								DayElmt = prepareWebElementWithDynamicXpathforDays(objDailyTradingStatementObjects,Datej,Column,i);
				
								Dept = getText(Department);
								Day = getText(DayElmt);
					
								DayVal= TrimData(Day);
						
								row.add(Dept);
								row.add(DayVal);
								UIValues.add(row);
								System.out.println(UIValues);
						}
				}
				return UIValues;
			}
			catch (Exception e) 
			{
				System.out.println("Exception occured while fetching DTS Data");
				e.printStackTrace();
			}
			return null;
			}
		
		//Get UI Total to Compare with DB Value
		public List<List<Object>> DayWiseUITotal(String Value,String Column,DailyTradingStatementObjects objDailyTradingStatementObjects)
		{
			try
			{
				int i=0,columnsize=2;
				WebElement Department,DayElmt;
				String Dept,Day=null;
				double DayVal;
				List<List<Object>> UIValues= new  ArrayList<List<Object>>();	
				List<Object> row = new ArrayList<>(columnsize);
				
				//Initialize Value for Loops
				if(Value.equalsIgnoreCase("Sales"))
					i=63;
				if(Value.equalsIgnoreCase("SMS"))
					i=64;
				if(Value.equalsIgnoreCase("Hours"))
					i=65;
				if(Value.equalsIgnoreCase("Wages"))
					i=66;
				if(Value.equalsIgnoreCase("WagesPercentage"))
					i=67;
				if(Value.equalsIgnoreCase("Items"))
					i=68;
				if(Value.equalsIgnoreCase("Customers"))
					i=69;
				
				// To Get Department and Store Total Column Names						
				Department = prepareWebElementWithDynamicXpathWithInt(objDailyTradingStatementObjects.StoreTotal,63); 
					
					for(int k=0;k<7;k++)
					{
						row = new ArrayList<>(columnsize);
						String Datej = ("Datek".replace("k", ""+k));
						DayElmt = prepareWebElementWithDynamicXpathforDays(objDailyTradingStatementObjects,Datej,Column,i);
		
						Dept = getText(Department);
						Day = getText(DayElmt);
			
						DayVal= TrimData(Day);
				
						row.add(Dept);
						row.add(DayVal);
						UIValues.add(row);
						System.out.println(UIValues);
				}
				return UIValues;
			}
			catch (Exception e) 
			{
				System.out.println("Exception occured while Switching the Frame");
				e.printStackTrace();
			}
			return null;
		}
		
/////////////////////////////////////////////// Alternate for Getting UI Data for Week days //////////////////////////////////////////////////
		
		//Get UI Value - Planned Sales
		public List<List<Object>> PlannedUIResults(String PlanCompletedStr,String PlanNotCompletedStr,String Monday,String Tuesday,String Wednesday,String ThursdayG,String ThursdayW,
				String FridayG,String FridayW,String SaturdayG,String SaturdayW,String SundayG,String SundayW,String Table)
		{
			try 
			{
				int i,columnsize=3;
				String Element,ThursdayElement,FridayElement,SaturdayElement,SundayElement;
				WebElement Department = null,MondayElmt,TuesdayElmt,WednesdayElmt,ThursdayElmt,FridayElmt,SaturdayElmt,SundayElmt;
				BigDecimal MondayVal,TuesdayVal,WednesdayVal,ThursdayVal,FridayVal,SaturdayVal,SundayVal;
				String Dept,Mon,Tue,Wed,Thu,Fri,Sat,Sun;
				double MonVal,TueVal,WedVal,ThuVal,FriVal,SatVal,SunVal;
				List<List<Object>> UIValues= new  ArrayList<List<Object>>();
				List<Object> row = new ArrayList<>(columnsize);
				int RowCount =TradingStatementTableCount(Table);	
					
				for(i=0;i<RowCount;i++)
				{
					row = new ArrayList<>(columnsize);
					Element = PlanCompletedStr.replace("dynamic",""+i);
					ThursdayElement = ThursdayG.replace("dynamic",""+i);
					FridayElement = FridayG.replace("dynamic",""+i);
					SaturdayElement = SaturdayG.replace("dynamic",""+i);
					SundayElement = SundayG.replace("dynamic",""+i);
					
					if(isElementPresent(Element))
					{
						Department = prepareWebElementWithDynamicXpathWithInt(PlanCompletedStr, i); 
						Dept = getText(Department);
					}else
					{
						Department = prepareWebElementWithDynamicXpathWithInt(PlanNotCompletedStr, i); 
						Dept = getText(Department);
					}
					
					MondayElmt = prepareWebElementWithDynamicXpathWithInt(Monday, i);
					TuesdayElmt = prepareWebElementWithDynamicXpathWithInt(Tuesday, i);
					WednesdayElmt = prepareWebElementWithDynamicXpathWithInt(Wednesday, i);
					
					if(isElementPresent(ThursdayElement))
						ThursdayElmt = prepareWebElementWithDynamicXpathWithInt(ThursdayG, i);
					else
						ThursdayElmt = prepareWebElementWithDynamicXpathWithInt(ThursdayW, i);
					
					if(isElementPresent(FridayElement))
						FridayElmt = prepareWebElementWithDynamicXpathWithInt(FridayG, i);
					else
						FridayElmt = prepareWebElementWithDynamicXpathWithInt(FridayW, i);
					
					if(isElementPresent(SaturdayElement))
						SaturdayElmt = prepareWebElementWithDynamicXpathWithInt(SaturdayG, i);
					else
						SaturdayElmt = prepareWebElementWithDynamicXpathWithInt(SaturdayW, i);
					
					if(isElementPresent(SundayElement))
						SundayElmt = prepareWebElementWithDynamicXpathWithInt(SundayG, i);
					else
						SundayElmt = prepareWebElementWithDynamicXpathWithInt(SundayW, i);
					
					Dept = getText(Department);
					Mon = getText(MondayElmt);
					Tue = getText(TuesdayElmt);
					Wed = getText(WednesdayElmt);
					Thu = getText(ThursdayElmt);
					Fri = getText(FridayElmt);
					Sat = getText(SaturdayElmt);
					Sun = getText(SundayElmt);
					
					MonVal= TrimData(Mon);
					MondayVal = ConvertBigDecimal(MonVal);
					TueVal= TrimData(Tue);
					TuesdayVal = ConvertBigDecimal(TueVal);
					WedVal= TrimData(Wed);
					WednesdayVal = ConvertBigDecimal(WedVal);
					ThuVal= TrimData(Thu);
					ThursdayVal = ConvertBigDecimal(ThuVal);
					FriVal= TrimData(Fri);
					FridayVal = ConvertBigDecimal(FriVal);
					SatVal= TrimData(Sat);
					SaturdayVal = ConvertBigDecimal(SatVal);
					SunVal= TrimData(Sun);
					SundayVal = ConvertBigDecimal(SunVal);
					
					row.add(Dept);
					row.add(MondayVal);	
					row.add(TuesdayVal);	
					row.add(WednesdayVal);	
					row.add(ThursdayVal);	
					row.add(FridayVal);	
					row.add(SaturdayVal);	
					row.add(SundayVal);	
					
					UIValues.add(row);
					System.out.println(UIValues);
			}
				return UIValues;
			}
			catch (Exception e) 
			{
				System.out.println("Exception occured while Fetching Data");
				e.printStackTrace();
			}
			return null;
			}
			
			//Get UI Total Planned
				public List<List<Object>> PlannedUITotal(String GroupTotal,String Monday,String Tuesday,String Wednesday,String ThursdayG,String ThursdayW,
						String FridayG,String FridayW,String SaturdayG,String SaturdayW,String SundayG,String SundayW,String Table) 
				{
					try
					{
						int columnsize=3;
						WebElement Department,MondayElement,TuesdayElement,WednesdayElement,ThursdayElement,FridayElement,SaturdayElement,SundayElement;
						String Dept,Mon,Tue,Wed,Thu,Fri,Sat,Sun;
						double MonVal,TueVal,WedVal,ThuVal,FriVal,SatVal,SunVal;
						List<List<Object>> UIValues= new  ArrayList<List<Object>>();	
						int RowCount =TradingStatementTableCount(Table);	
						List<Object> row = new ArrayList<>(columnsize);
						BigDecimal MondayVal,TuesdayVal,WednesdayVal,ThursdayVal,FridayVal,SaturdayVal,SundayVal;
					// To Get Department and Store Total Column Names	
							
							Department = prepareWebElementWithDynamicXpathWithInt(GroupTotal,RowCount); 
							
							MondayElement = prepareWebElementWithDynamicXpathWithInt(Monday);
							TuesdayElement = prepareWebElementWithDynamicXpathWithInt(Tuesday);
							WednesdayElement = prepareWebElementWithDynamicXpathWithInt(Wednesday);

							if(isElementPresent(ThursdayG))
								ThursdayElement = prepareWebElementWithDynamicXpathWithInt(ThursdayG);
							else
								ThursdayElement = prepareWebElementWithDynamicXpathWithInt(ThursdayW);
							
							if(isElementPresent(FridayG))
								FridayElement = prepareWebElementWithDynamicXpathWithInt(FridayG);
							else
								FridayElement = prepareWebElementWithDynamicXpathWithInt(FridayW);
							
							if(isElementPresent(SaturdayG))
								SaturdayElement = prepareWebElementWithDynamicXpathWithInt(SaturdayG);
							else
								SaturdayElement = prepareWebElementWithDynamicXpathWithInt(SaturdayW);
							
							if(isElementPresent(SundayG))
								SundayElement = prepareWebElementWithDynamicXpathWithInt(SundayG);
							else
								SundayElement = prepareWebElementWithDynamicXpathWithInt(SundayW);
							
							Dept = getText(Department);
							Mon = getText(MondayElement);
							Tue = getText(TuesdayElement);
							Wed = getText(WednesdayElement);
							Thu = getText(ThursdayElement);
							Fri = getText(FridayElement);
							Sat = getText(SaturdayElement);
							Sun = getText(SundayElement);
							
							MonVal= TrimData(Mon);
							MondayVal = ConvertBigDecimal(MonVal);
							TueVal= TrimData(Tue);
							TuesdayVal = ConvertBigDecimal(TueVal);
							WedVal= TrimData(Wed);
							WednesdayVal = ConvertBigDecimal(WedVal);
							ThuVal= TrimData(Thu);
							ThursdayVal = ConvertBigDecimal(ThuVal);
							FriVal= TrimData(Fri);
							FridayVal = ConvertBigDecimal(FriVal);
							SatVal= TrimData(Sat);
							SaturdayVal = ConvertBigDecimal(SatVal);
							SunVal= TrimData(Sun);
							SundayVal = ConvertBigDecimal(SunVal);
							
							row.add(Dept);
							row.add(MondayVal);	
							row.add(TuesdayVal);	
							row.add(WednesdayVal);	
							row.add(ThursdayVal);	
							row.add(FridayVal);	
							row.add(SaturdayVal);	
							row.add(SundayVal);	
							
							UIValues.add(row);
							System.out.println(UIValues);
							return UIValues;
					}
					catch (Exception e) 
					{
						System.out.println("Exception occured while Switching the Frame");
						e.printStackTrace();
					}
					return null;
				}		

				//Get UI Value Actual
				public List<List<Object>> ActualsUIResult(String PlanCompletedStr,String PlanNotCompletedStr,String MondayW,String MondayY,String TuesdayW,String TuesdayY,
						String WednesdayW,String WednesdayY,String ThursdayW,String ThursdayY,String FridayW,String FridayY,String SaturdayW,String SaturdayY,
						String SundayW,String  SundayY,String Table) 
				{
					try 
					{
						int i,columnsize=3;
						String Element,MondayElement,TuesdayElement,WednesdayElement,ThursdayElement,FridayElement,SaturdayElement,SundayElement;
						WebElement Department = null,MondayElmt,TuesdayElmt,WednesdayElmt,ThursdayElmt,FridayElmt,SaturdayElmt,SundayElmt;
						String Dept,Mon,Tue,Wed,Thu,Fri,Sat,Sun;
						double MonVal,TueVal,WedVal,ThuVal,FriVal,SatVal,SunVal;
						List<List<Object>> UIValues= new  ArrayList<List<Object>>();
						List<Object> row = new ArrayList<>(columnsize);
						BigDecimal MondayVal,TuesdayVal,WednesdayVal,ThursdayVal,FridayVal,SaturdayVal,SundayVal;
						int RowCount =TradingStatementTableCount(Table);
							
						for(i=0;i<RowCount;i++)
						{
							row = new ArrayList<>(columnsize);
							Element = PlanCompletedStr.replace("dynamic",""+i);
							MondayElement = MondayW.replace("dynamic",""+i);
							TuesdayElement = TuesdayW.replace("dynamic",""+i);
							WednesdayElement = WednesdayW.replace("dynamic",""+i);
							ThursdayElement = ThursdayW.replace("dynamic",""+i);
							FridayElement = FridayW.replace("dynamic",""+i);
							SaturdayElement = SaturdayW.replace("dynamic",""+i);
							SundayElement = SundayW.replace("dynamic",""+i);
							
							if(isElementPresent(Element))
								Department = prepareWebElementWithDynamicXpathWithInt(PlanCompletedStr, i); 
							else
								Department = prepareWebElementWithDynamicXpathWithInt(PlanNotCompletedStr, i); 					
							
							if(isElementPresent(MondayElement))
								MondayElmt = prepareWebElementWithDynamicXpathWithInt(MondayW, i);
							else
								MondayElmt = prepareWebElementWithDynamicXpathWithInt(MondayY, i); 
							
							if(isElementPresent(TuesdayElement))
								TuesdayElmt = prepareWebElementWithDynamicXpathWithInt(TuesdayW, i);
							else
								TuesdayElmt = prepareWebElementWithDynamicXpathWithInt(TuesdayY, i);
							
							if(isElementPresent(WednesdayElement))
								WednesdayElmt = prepareWebElementWithDynamicXpathWithInt(WednesdayW, i);
							else
								WednesdayElmt = prepareWebElementWithDynamicXpathWithInt(WednesdayY, i);
							
							if(isElementPresent(ThursdayElement))
								ThursdayElmt = prepareWebElementWithDynamicXpathWithInt(ThursdayW, i);
							else
								ThursdayElmt = prepareWebElementWithDynamicXpathWithInt(ThursdayY, i);
							
							if(isElementPresent(FridayElement))
								FridayElmt = prepareWebElementWithDynamicXpathWithInt(FridayW, i);
							else
								FridayElmt = prepareWebElementWithDynamicXpathWithInt(FridayY, i);
							
							if(isElementPresent(SaturdayElement))
								SaturdayElmt = prepareWebElementWithDynamicXpathWithInt(SaturdayW, i);
							else
								SaturdayElmt = prepareWebElementWithDynamicXpathWithInt(SaturdayY, i);
							
							if(isElementPresent(SundayElement))
								SundayElmt = prepareWebElementWithDynamicXpathWithInt(SundayW, i);
							else
								SundayElmt = prepareWebElementWithDynamicXpathWithInt(SundayY, i);
							
							Dept = getText(Department);
							Mon = getText(MondayElmt);
							Tue = getText(TuesdayElmt);
							Wed = getText(WednesdayElmt);
							Thu = getText(ThursdayElmt);
							Fri = getText(FridayElmt);
							Sat = getText(SaturdayElmt);
							Sun = getText(SundayElmt);
							
							MonVal= TrimData(Mon);
							MondayVal = ConvertBigDecimal(MonVal);
							TueVal= TrimData(Tue);
							TuesdayVal = ConvertBigDecimal(TueVal);
							WedVal= TrimData(Wed);
							WednesdayVal = ConvertBigDecimal(WedVal);
							ThuVal= TrimData(Thu);
							ThursdayVal = ConvertBigDecimal(ThuVal);
							FriVal= TrimData(Fri);
							FridayVal = ConvertBigDecimal(FriVal);
							SatVal= TrimData(Sat);
							SaturdayVal = ConvertBigDecimal(SatVal);
							SunVal= TrimData(Sun);
							SundayVal = ConvertBigDecimal(SunVal);

							row.add(Dept);
							row.add(MondayVal);	
							row.add(TuesdayVal);	
							row.add(WednesdayVal);	
							row.add(ThursdayVal);	
							row.add(FridayVal);	
							row.add(SaturdayVal);	
							row.add(SundayVal);		
							
							UIValues.add(row);
							System.out.println(UIValues);
					}
						return UIValues;
					}
					catch (Exception e) 
					{
						System.out.println("Exception occured while Switching the Frame");
						e.printStackTrace();
					}
					return null;
			}	
				
				//Get UI Total Actuals
				public List<List<Object>> ActualsUITotal(String GroupTotal,String MondayW,String MondayY,String TuesdayW,String TuesdayY,
						String WednesdayW,String WednesdayY,String ThursdayW,String ThursdayY,String FridayW,String FridayY,String SaturdayW,String SaturdayY,
						String SundayW,String  SundayY) 
				{
					try 
					{
						int columnsize=3;
						WebElement Department = null,MondayElmt,TuesdayElmt,WednesdayElmt,ThursdayElmt,FridayElmt,SaturdayElmt,SundayElmt;
						String Dept,Mon,Tue,Wed,Thu,Fri,Sat,Sun;
						BigDecimal MondayVal,TuesdayVal,WednesdayVal,ThursdayVal,FridayVal,SaturdayVal,SundayVal;
						double MonVal,TueVal,WedVal,ThuVal,FriVal,SatVal,SunVal;
						List<List<Object>> UIValues= new  ArrayList<List<Object>>();
						List<Object> row = new ArrayList<>(columnsize);	
						row = new ArrayList<>(columnsize);
						
								Department = prepareWebElementWithDynamicXpathWithInt(GroupTotal); 
							
							if(isElementPresent(MondayW))
								MondayElmt = prepareWebElementWithDynamicXpathWithInt(MondayW);
							else
								MondayElmt = prepareWebElementWithDynamicXpathWithInt(MondayY); 
							
							if(isElementPresent(TuesdayW))
								TuesdayElmt = prepareWebElementWithDynamicXpathWithInt(TuesdayW);
							else
								TuesdayElmt = prepareWebElementWithDynamicXpathWithInt(TuesdayY);
							
							if(isElementPresent(WednesdayW))
								WednesdayElmt = prepareWebElementWithDynamicXpathWithInt(WednesdayW);
							else
								WednesdayElmt = prepareWebElementWithDynamicXpathWithInt(WednesdayY);
							
							if(isElementPresent(ThursdayW))
								ThursdayElmt = prepareWebElementWithDynamicXpathWithInt(ThursdayW);
							else
								ThursdayElmt = prepareWebElementWithDynamicXpathWithInt(ThursdayY);
							
							if(isElementPresent(FridayW))
								FridayElmt = prepareWebElementWithDynamicXpathWithInt(FridayW);
							else
								FridayElmt = prepareWebElementWithDynamicXpathWithInt(FridayY);
							
							if(isElementPresent(SaturdayW))
								SaturdayElmt = prepareWebElementWithDynamicXpathWithInt(SaturdayW);
							else
								SaturdayElmt = prepareWebElementWithDynamicXpathWithInt(SaturdayY);
							
							if(isElementPresent(SundayW))
								SundayElmt = prepareWebElementWithDynamicXpathWithInt(SundayW);
							else
								SundayElmt = prepareWebElementWithDynamicXpathWithInt(SundayY);
							
							Dept = getText(Department);
							Mon = getText(MondayElmt);
							Tue = getText(TuesdayElmt);
							Wed = getText(WednesdayElmt);
							Thu = getText(ThursdayElmt);
							Fri = getText(FridayElmt);
							Sat = getText(SaturdayElmt);
							Sun = getText(SundayElmt);
							
							MonVal= TrimData(Mon);
							MondayVal = ConvertBigDecimal(MonVal);
							TueVal= TrimData(Tue);
							TuesdayVal = ConvertBigDecimal(TueVal);
							WedVal= TrimData(Wed);
							WednesdayVal = ConvertBigDecimal(WedVal);
							ThuVal= TrimData(Thu);
							ThursdayVal = ConvertBigDecimal(ThuVal);
							FriVal= TrimData(Fri);
							FridayVal = ConvertBigDecimal(FriVal);
							SatVal= TrimData(Sat);
							SaturdayVal = ConvertBigDecimal(SatVal);
							SunVal= TrimData(Sun);
							SundayVal = ConvertBigDecimal(SunVal);

							row.add(Dept);
							row.add(MondayVal);	
							row.add(TuesdayVal);	
							row.add(WednesdayVal);	
							row.add(ThursdayVal);	
							row.add(FridayVal);	
							row.add(SaturdayVal);	
							row.add(SundayVal);		
							
							UIValues.add(row);
							System.out.println(UIValues);
						return UIValues;
					}
					catch (Exception e) 
					{
						System.out.println("Exception occured while Switching the Frame");
						e.printStackTrace();
					}
					return null;
			}	
				
				//Get UI Value - LY
				public List<List<Object>> LYUIResults(String PlanCompletedStr,String PlanNotCompletedStr,String Monday,String Tuesday,String Wednesday,String Thursday,
						String Friday,String Saturday,String Sunday,String Table) //,String WeekToDate
				{
					try 
					{
						int i,columnsize=3;
						String Element;
						WebElement Department = null,MondayElmt,TuesdayElmt,WednesdayElmt,ThursdayElmt,FridayElmt,SaturdayElmt,SundayElmt;
						BigDecimal MondayVal,TuesdayVal,WednesdayVal,ThursdayVal,FridayVal,SaturdayVal,SundayVal;
						String Dept,Mon,Tue,Wed,Thu,Fri,Sat,Sun;
						double MonVal,TueVal,WedVal,ThuVal,FriVal,SatVal,SunVal;
						List<List<Object>> UIValues= new  ArrayList<List<Object>>();
						List<Object> row = new ArrayList<>(columnsize);
						int RowCount =TradingStatementTableCount(Table);
							
						for(i=0;i<RowCount;i++)
						{
							row = new ArrayList<>(columnsize);
							Element = PlanCompletedStr.replace("dynamic",""+i);					
							if(isElementPresent(Element))
							{
								Department = prepareWebElementWithDynamicXpathWithInt(PlanCompletedStr, i); 
								Dept = getText(Department);
							}else
							{
								Department = prepareWebElementWithDynamicXpathWithInt(PlanNotCompletedStr, i); 
								Dept = getText(Department);
							}
							
							MondayElmt = prepareWebElementWithDynamicXpathWithInt(Monday, i);
							TuesdayElmt = prepareWebElementWithDynamicXpathWithInt(Tuesday, i);
							WednesdayElmt = prepareWebElementWithDynamicXpathWithInt(Wednesday, i);
							ThursdayElmt = prepareWebElementWithDynamicXpathWithInt(Thursday, i);
							FridayElmt = prepareWebElementWithDynamicXpathWithInt(Friday, i);
							SaturdayElmt = prepareWebElementWithDynamicXpathWithInt(Saturday, i);
							SundayElmt = prepareWebElementWithDynamicXpathWithInt(Sunday, i);
							
							Dept = getText(Department);
							Mon = getText(MondayElmt);
							Tue = getText(TuesdayElmt);
							Wed = getText(WednesdayElmt);
							Thu = getText(ThursdayElmt);
							Fri = getText(FridayElmt);
							Sat = getText(SaturdayElmt);
							Sun = getText(SundayElmt);
							
							MonVal= TrimData(Mon);
							MondayVal = ConvertBigDecimal(MonVal);
							TueVal= TrimData(Tue);
							TuesdayVal = ConvertBigDecimal(TueVal);
							WedVal= TrimData(Wed);
							WednesdayVal = ConvertBigDecimal(WedVal);
							ThuVal= TrimData(Thu);
							ThursdayVal = ConvertBigDecimal(ThuVal);
							FriVal= TrimData(Fri);
							FridayVal = ConvertBigDecimal(FriVal);
							SatVal= TrimData(Sat);
							SaturdayVal = ConvertBigDecimal(SatVal);
							SunVal= TrimData(Sun);
							SundayVal = ConvertBigDecimal(SunVal);

							row.add(Dept);
							row.add(MondayVal);	
							row.add(TuesdayVal);	
							row.add(WednesdayVal);	
							row.add(ThursdayVal);	
							row.add(FridayVal);	
							row.add(SaturdayVal);	
							row.add(SundayVal);	
							
							UIValues.add(row);
							System.out.println(UIValues);
					}
						return UIValues;
					}
					catch (Exception e) 
					{
						System.out.println("Exception occured while Fetching LY data");
						e.printStackTrace();
					}
					return null;
					}
					
					//Get UI Total LY
						public List<List<Object>> LYUITotal(String GroupTotal,String Monday,String Tuesday,String Wednesday,String Thursday,
								String Friday,String Saturday,String Sunday,String Table)  //,String WeekToDate
						{
							try
							{
								int columnsize=3;
								WebElement Department,MondayElement,TuesdayElement,WednesdayElement,ThursdayElement,FridayElement,SaturdayElement,SundayElement;
								String Dept,Mon,Tue,Wed,Thu,Fri,Sat,Sun;
								double MonVal,TueVal,WedVal,ThuVal,FriVal,SatVal,SunVal;
								List<List<Object>> UIValues= new  ArrayList<List<Object>>();	
								int RowCount =TradingStatementTableCount(Table);	
								List<Object> row = new ArrayList<>(columnsize);
								BigDecimal MondayVal,TuesdayVal,WednesdayVal,ThursdayVal,FridayVal,SaturdayVal,SundayVal;
								
							// To Get Department and Store Total Column Names	
									
									Department = prepareWebElementWithDynamicXpathWithInt(GroupTotal,RowCount); 
									
									MondayElement = prepareWebElementWithDynamicXpathWithInt(Monday);
									TuesdayElement = prepareWebElementWithDynamicXpathWithInt(Tuesday);
									WednesdayElement = prepareWebElementWithDynamicXpathWithInt(Wednesday);
									ThursdayElement = prepareWebElementWithDynamicXpathWithInt(Thursday);
									FridayElement = prepareWebElementWithDynamicXpathWithInt(Friday);
									SaturdayElement = prepareWebElementWithDynamicXpathWithInt(Saturday);
									SundayElement = prepareWebElementWithDynamicXpathWithInt(Sunday);
									
									Dept = getText(Department);
									Mon = getText(MondayElement);
									Tue = getText(TuesdayElement);
									Wed = getText(WednesdayElement);
									Thu = getText(ThursdayElement);
									Fri = getText(FridayElement);
									Sat = getText(SaturdayElement);
									Sun = getText(SundayElement);
									
									MonVal= TrimData(Mon);
									MondayVal = ConvertBigDecimal(MonVal);
									TueVal= TrimData(Tue);
									TuesdayVal = ConvertBigDecimal(TueVal);
									WedVal= TrimData(Wed);
									WednesdayVal = ConvertBigDecimal(WedVal);
									ThuVal= TrimData(Thu);
									ThursdayVal = ConvertBigDecimal(ThuVal);
									FriVal= TrimData(Fri);
									FridayVal = ConvertBigDecimal(FriVal);
									SatVal= TrimData(Sat);
									SaturdayVal = ConvertBigDecimal(SatVal);
									SunVal= TrimData(Sun);
									SundayVal = ConvertBigDecimal(SunVal);

									row.add(Dept);
									row.add(MondayVal);	
									row.add(TuesdayVal);	
									row.add(WednesdayVal);	
									row.add(ThursdayVal);	
									row.add(FridayVal);	
									row.add(SaturdayVal);	
									row.add(SundayVal);	
									
									UIValues.add(row);
									System.out.println(UIValues);
									return UIValues;
							}
							catch (Exception e) 
							{
								System.out.println("Exception occured while Fetching LY data");
								e.printStackTrace();
							}
							return null;
						}	
						
						//Get UI Value WeektoDate 
						public List<List<Object>> WeekToDateUIResult(String PlanCompletedStr,String PlanNotCompletedStr,String WeekToDateElement,String Table) 
						{
							try 
							{
								int i,columnsize=3;
								String Element;
								WebElement Department = null,WeektoDateElmt;
								String Dept,WkToDate;
								double WkToDateVal;
								List<List<Object>> UIValues= new  ArrayList<List<Object>>();
								List<Object> row;
								int RowCount =TradingStatementTableCount(Table);
								BigDecimal FinalVal;
								for(i=0;i<RowCount;i++)
								{
									row = new ArrayList<>(columnsize);
									Element = PlanCompletedStr.replace("dynamic",""+i);
									WeektoDateElmt = prepareWebElementWithDynamicXpathWithInt(WeekToDateElement, i); 
									
									if(isElementPresent(Element))
										Department = prepareWebElementWithDynamicXpathWithInt(PlanCompletedStr, i); 
									else
										Department = prepareWebElementWithDynamicXpathWithInt(PlanNotCompletedStr, i); 					
									
									Dept = getText(Department);
									WkToDate = getText(WeektoDateElmt);		
									WkToDateVal= TrimData(WkToDate);
									FinalVal = ConvertBigDecimal(WkToDateVal);
									row.add(Dept);	
									row.add(FinalVal);
									
									UIValues.add(row);
									System.out.println(UIValues);
							}
								return UIValues;
							}
							catch (Exception e) 
							{
								System.out.println("Exception occured while Fetching Value");
								e.printStackTrace();
							}
							return null;
					}
						
						
						public List<List<Object>> WeekToDateUITotalWTS(String GroupTotal,String WeekToDateTotal,String Table) 
						{
							try 
							{
								int columnsize=3;
								WebElement Department = null,WeekToDateElmt;
								String Dept,WeekToDate;
								double WeekToDateVal;
								BigDecimal FinalVal;
								int RowCount =TradingStatementTableCount(Table);
								List<List<Object>> UIValues= new  ArrayList<List<Object>>();
								List<Object> row = new ArrayList<>(columnsize);	
								row = new ArrayList<>(columnsize);
								Department = prepareWebElementWithDynamicXpathWithInt(GroupTotal, RowCount-5); 	
								WeekToDateElmt = prepareWebElementWithDynamicXpathWithInt(WeekToDateTotal, RowCount-5); 
									
									Dept = getText(Department);
									WeekToDate = getText(WeekToDateElmt);			
									WeekToDateVal= TrimData(WeekToDate);
									FinalVal = ConvertBigDecimal(WeekToDateVal);
									row.add(Dept);
									row.add(FinalVal);										
									UIValues.add(row);
									System.out.println(UIValues);
								return UIValues;
							}
							catch (Exception e) 
							{
								System.out.println("Exception occured while Fetching Data");
								e.printStackTrace();
							}
							return null;
					}			
						
						//Get UI Total Actuals
						public List<List<Object>> WeekToDateUITotal(String GroupTotal,String WeekToDateTotal) 
						{
							try 
							{
								int columnsize=3;
								WebElement Department = null,WeekToDateElmt;
								String Dept,WeekToDate;
								double WeekToDateVal;
								BigDecimal FinalVal;
							
								List<List<Object>> UIValues= new  ArrayList<List<Object>>();
								List<Object> row = new ArrayList<>(columnsize);	
								row = new ArrayList<>(columnsize);
								Department = prepareWebElementWithDynamicXpathWithInt(GroupTotal); 	
								WeekToDateElmt = prepareWebElementWithDynamicXpathWithInt(WeekToDateTotal); 
									
									Dept = getText(Department);
									WeekToDate = getText(WeekToDateElmt);			
									WeekToDateVal= TrimData(WeekToDate);
									FinalVal = ConvertBigDecimal(WeekToDateVal);
									row.add(Dept);
									row.add(FinalVal);										
									UIValues.add(row);
									System.out.println(UIValues);
								return UIValues;
							}
							catch (Exception e) 
							{
								System.out.println("Exception occured while Fetching Data");
								e.printStackTrace();
							}
							return null;
					}
						
						//Get UI Value WeektoDate 
						public List<List<Object>> WeekToDateActualsUIResult(String PlanCompletedStr,String PlanNotCompletedStr,String WeekToDateElementW,String WeekToDateElementY
								,String Table) 
						{
							try 
							{
								int i,columnsize=3;
								String Element,WkToDateElement;
								WebElement Department = null,WeektoDateElmt;
								String Dept,WkToDate;
								double WkToDateVal;
								List<List<Object>> UIValues= new  ArrayList<List<Object>>();
								List<Object> row;
								int RowCount =TradingStatementTableCount(Table);
								BigDecimal FinalVal;	
								for(i=0;i<RowCount;i++)
								{
									row = new ArrayList<>(columnsize);
									Element = PlanCompletedStr.replace("dynamic",""+i);
									WkToDateElement = WeekToDateElementW.replace("dynamic",""+i);
									
									if(isElementPresent(Element))
										Department = prepareWebElementWithDynamicXpathWithInt(PlanCompletedStr, i); 
									else
										Department = prepareWebElementWithDynamicXpathWithInt(PlanNotCompletedStr, i); 
									
									if(isElementPresent(WkToDateElement))
										WeektoDateElmt = prepareWebElementWithDynamicXpathWithInt(WeekToDateElementW, i);
									else
										WeektoDateElmt = prepareWebElementWithDynamicXpathWithInt(WeekToDateElementY, i); 
									
									Dept = getText(Department);
									WkToDate = getText(WeektoDateElmt);		
									WkToDateVal= TrimData(WkToDate);
									FinalVal = ConvertBigDecimal(WkToDateVal);
									row.add(Dept);	
									row.add(FinalVal);	
									UIValues.add(row);
									System.out.println(UIValues);
							}
								return UIValues;
							}
							catch (Exception e) 
							{
								System.out.println("Exception occured while Fetching Value");
								e.printStackTrace();
							}
							return null;
					}
						
				//Get UI Total Actuals
				public List<List<Object>> WeekToDateActualsUITotal(String GroupTotal,String WeekToDateTotalW,String WeekToDateTotalY) 
				{
						try 
						{
							int columnsize=3;
							WebElement Department = null,WeekToDateElmt;
							String Dept,WeekToDate;
							double WeekToDateVal;
							List<List<Object>> UIValues= new  ArrayList<List<Object>>();
							List<Object> row = new ArrayList<>(columnsize);	
							row = new ArrayList<>(columnsize);
							Department = prepareWebElementWithDynamicXpathWithInt(GroupTotal); 
							BigDecimal FinalVal;
							if(isElementPresent(WeekToDateTotalW))
								WeekToDateElmt = prepareWebElementWithDynamicXpathWithInt(WeekToDateTotalW);
							else
								WeekToDateElmt = prepareWebElementWithDynamicXpathWithInt(WeekToDateTotalY);
									
							Dept = getText(Department);
							WeekToDate = getText(WeekToDateElmt);			
							WeekToDateVal= TrimData(WeekToDate);
							FinalVal = ConvertBigDecimal(WeekToDateVal);
							row.add(Dept);
							row.add(FinalVal);										
							UIValues.add(row);
							System.out.println(UIValues);
							return UIValues;
						}
						catch (Exception e) 
						{
							System.out.println("Exception occured while Fetching Data");
							e.printStackTrace();
						}
						return null;
				}
				
////////////////////////////////////////////  WTS and MTS  ///////////////////////////////////////
				//Get UI Value WeektoDate 
				public List<List<Object>> GetRowWiseResult(WeeklyTradingStatementStoreObjects objWeeklyTradingStatementStoreObjects,String Description,String ElementToVerify,String Table) 
				{
					try 
					{
						int i,columnsize=3;
						WebElement Department = null,WeektoDateElmt;
						String Dept,WkToDate;
						double WkToDateVal;
						List<List<Object>> UIValues= new  ArrayList<List<Object>>();
						List<Object> row;
						int RowCount =TradingStatementTableCount(Table);
						BigDecimal FinalVal;
						for(i=0;i<RowCount-5;i++)
						{
							//if(i==6)
								//i++;
							row = new ArrayList<>(columnsize);
							Department = prepareWebElementWithDynamicXpathWithInt(Description, i); 	
							WeektoDateElmt = prepareWebElementWithDynamicXpathWithInt(ElementToVerify, i); 
							
							Dept = getText(Department);
							WkToDate = getText(WeektoDateElmt);		
							WkToDateVal= TrimData(WkToDate);
							FinalVal = ConvertBigDecimal(WkToDateVal);
							row.add(Dept);	
							row.add(FinalVal);
							
							UIValues.add(row);
							System.out.println(UIValues);
					}
						return UIValues;
					}
					catch (Exception e) 
					{
						System.out.println("Exception occured while Fetching Value");
						e.printStackTrace();
					}
					return null;
			}
				
				
				
				//CopyDataToNewStore
				
				public List<List<Object>> GetValue(WeeklyPlanningStoreObjects objWeeklyPlanningStoreObjects,String Description,String ElementToVerify,String Table,String Neg) 
				{
					try 
					{
						WebElement State = null,FinWeek,PosValue,NegValue;
						String val1,val2,val3,val4;
						List<List<Object>> UIValues= new  ArrayList<List<Object>>();
						List<Object> row;
							row = new ArrayList<>();
							State = prepareWebElementWithDynamicXpathWithInt(Description); 	
							FinWeek = prepareWebElementWithDynamicXpathWithInt(ElementToVerify); 
							PosValue = prepareWebElementWithDynamicXpathWithInt(Description); 	
							NegValue = prepareWebElementWithDynamicXpathWithInt(ElementToVerify); 
							
							val1 = getText(State);
							val2 = getText(FinWeek);		
							val3 = getText(PosValue);
							val4 = getText(NegValue);		
							
							row.add(val1);	
							row.add(val2);
							row.add(val3);
							row.add(val4);
							
							UIValues.add(row);
							System.out.println(UIValues);
					
						return UIValues;
					}
					catch (Exception e) 
					{
						System.out.println("Exception occured while Fetching Value");
						e.printStackTrace();
					}
					return null;
			}	
				// Copy Data To NEw Store Dynamic
				public List<List<Object>> UIResults(WeeklyPlanningStoreObjects objWeeklyPlanningStoreObjects,String Description,String ElementToVerify,String Table,String Neg)
				{
					WebElement State = null,FinWeek = null,PosValue = null,NegValue = null;
					String val1,val2,val3,val4;
					int columnsize=6;
					List<List<Object>> UIValues= new  ArrayList<List<Object>>();
					List<Object> row = null ; 
					int RowCount =CopyDataToNewsToTableCount();	
					for(int i=0;i<=RowCount-1;i++)
					{
						row =  new ArrayList<>(columnsize);
						State = prepareWebElementWithDynamicXpathWithInt(Description,i); 	
						FinWeek = prepareWebElementWithDynamicXpathWithInt(ElementToVerify,i); 
						PosValue = prepareWebElementWithDynamicXpathWithInt(Table,i); 	
						NegValue = prepareWebElementWithDynamicXpathWithInt(Neg,i); 
						
						val1 = getText(State);
						val2 = getText(FinWeek).replace("01","JAN").replace("02","FEB").replace("03","MAR").replace("04","APR").replace("05","MAY").replace("06","JUN").replace("07","JUL").replace("08","AUG").replace("09","SEP").replace("10","OCT").replace("11","NOV").replace("12","DEC").replace("2018","18");		
						val3 = getAttribute(PosValue);
						val4 = getAttribute(NegValue);		
						
						row.add(val1);	
						row.add(val2);
						row.add(val3);
						row.add(val4);
					 
						UIValues.add(row);
					}
					System.out.println(UIValues);
					return UIValues;
				}
				
//////////////////// WTS and MTS - Group to WOW ////////////////////////////
				
		//Get UI Value WeektoDate 
		public List<List<Object>> RowWiseResult(WeeklyTradingStatementStoreObjects objWeeklyTradingStatementStoreObjects,String Description,String ElementToVerify,String Table) 
		{
			try 
			{
					int i,columnsize=3;
					WebElement Department = null,WeektoDateElmt;
					String Dept,WkToDate;
					double WkToDateVal;
					List<List<Object>> UIValues= new  ArrayList<List<Object>>();
					List<Object> row;
					int RowCount =TradingStatementTableCount(Table);
					BigDecimal FinalVal;
					for(i=0;i<RowCount-1;i++)
					{
						row = new ArrayList<>(columnsize);
						Department = prepareWebElementWithDynamicXpathWithInt(Description, i); 	
						WeektoDateElmt = prepareWebElementWithDynamicXpathWithInt(ElementToVerify, i); 
							
						Dept = getText(Department);
						WkToDate = getText(WeektoDateElmt);		
						WkToDateVal= TrimData(WkToDate);
						FinalVal = ConvertBigDecimal(WkToDateVal);
						row.add(Dept);	
						row.add(FinalVal);
							
						UIValues.add(row);
						System.out.println(UIValues);
				}
					return UIValues;
				}
				catch (Exception e) 
				{
					System.out.println("Exception occured while Fetching Value");
					e.printStackTrace();
				}
				return null;
		}
				
				public String[] VerifyRegionDropdownList(WeeklyPlanningStoreObjects objWeeklyPlanningStoreObjects,ExtentTest extentTest,String screen) throws Exception
				{		
					try
					{	
						List<String> RegionNm=new ArrayList<String>();
						if((isDisplayed(objWeeklyPlanningStoreObjects.PageTitle)) && (getText(objWeeklyPlanningStoreObjects.PageTitle).equalsIgnoreCase("Please Select Page...")))
							{
							int RowCount =TradingStatementTableCount(objWeeklyPlanningStoreObjects.TSRegionDropdown);
							String[] Regions = null;
							for(int i=1;i<=RowCount-1;i++)
							{
								WebElement Data = prepareWebElementWithDynamicXpathWithInt(objWeeklyPlanningStoreObjects.TSRegionDropdownValue, i);
								RegionNm.add(getText(Data));
								Object[] objRegions=RegionNm.toArray();
								Regions=Arrays.copyOf(objRegions, objRegions.length,String[].class);
								System.out.println(Regions[i-1]);
							}
							return Regions;
						}	
						else
							Report_AddStep("testcase","Failed to get the Values from the Dropdown" ,"","", "Fail");
							htmlToExtent(cName,mName,extentTest,driver1, "Failed to get the Values from the Dropdown;;;Fail");
					}
					catch(Exception e)
					{
						System.out.println("Exception Occured" +e.getMessage()); 
						Report_AddStep("testcase","Failed to get the Page names from the Dropdown" ,"","", "Fail");
						htmlToExtent(cName,mName,extentTest,driver1, "Exception occured while fetching page name from dropdon;;;Fail");
					}
					return null;
				}
				
		public void SwitchDataFrame(WebElement element) throws Exception
		{
			try 
			{
				SwitchFrame(element);
			}
			catch (Exception e) 
			{
				System.out.println("Generic Exception occured");
			}
		}

		///////////////////////////////// PST 71 ///////////////////////////////////////
		//Get UI Value - Planned Sales
		public List<List<Object>> PlannedUIResults_PST(String PlanCompletedStr,String PlanNotCompletedStr,String MondayG,String MondayW,String TuesdayG,String TuesdayW,String WednesdayG,String WednesdayW,
				String ThursdayG,String ThursdayW,
				String FridayG,String FridayW,String SaturdayG,String SaturdayW,String SundayG,String SundayW,String Table) //,String WeekToDate
		{
			try 
			{
				int i,columnsize=3;
				String Element,ThursdayElement,FridayElement,SaturdayElement,SundayElement,MondayElement,TuesdayElement,WednesdayElement;
				WebElement Department = null,MondayElmt,TuesdayElmt,WednesdayElmt,ThursdayElmt,FridayElmt,SaturdayElmt,SundayElmt;
				BigDecimal MondayVal,TuesdayVal,WednesdayVal,ThursdayVal,FridayVal,SaturdayVal,SundayVal;
				String Dept,Mon,Tue,Wed,Thu,Fri,Sat,Sun;
				double MonVal,TueVal,WedVal,ThuVal,FriVal,SatVal,SunVal;
				List<List<Object>> UIValues= new  ArrayList<List<Object>>();
				List<Object> row = new ArrayList<>(columnsize);
				int RowCount =TradingStatementTableCount(Table);	
					
				for(i=0;i<RowCount-3;i++)
				{
					row = new ArrayList<>(columnsize);
					MondayElement = MondayG.replace("dynamic",""+i);
					TuesdayElement = TuesdayG.replace("dynamic",""+i);
					WednesdayElement = WednesdayG.replace("dynamic",""+i);
					Element = PlanCompletedStr.replace("dynamic",""+i);
					ThursdayElement = ThursdayG.replace("dynamic",""+i);
					FridayElement = FridayG.replace("dynamic",""+i);
					SaturdayElement = SaturdayG.replace("dynamic",""+i);
					SundayElement = SundayG.replace("dynamic",""+i);
					
					if(isElementPresent(Element))
					{
						Department = prepareWebElementWithDynamicXpathWithInt(PlanCompletedStr, i); 
						Dept = getText(Department);
					}else
					{
						Department = prepareWebElementWithDynamicXpathWithInt(PlanNotCompletedStr, i); 
						Dept = getText(Department);
					}
					
					if(isElementPresent(MondayElement))
						MondayElmt = prepareWebElementWithDynamicXpathWithInt(MondayG, i);
					else
						MondayElmt = prepareWebElementWithDynamicXpathWithInt(MondayW, i); 
					
					if(isElementPresent(TuesdayElement))
						TuesdayElmt = prepareWebElementWithDynamicXpathWithInt(TuesdayG, i);
					else
						TuesdayElmt = prepareWebElementWithDynamicXpathWithInt(TuesdayW, i);
					
					if(isElementPresent(WednesdayElement))
						WednesdayElmt = prepareWebElementWithDynamicXpathWithInt(WednesdayG, i);
					else
						WednesdayElmt = prepareWebElementWithDynamicXpathWithInt(WednesdayW, i);
					if(isElementPresent(ThursdayElement))
						ThursdayElmt = prepareWebElementWithDynamicXpathWithInt(ThursdayG, i);
					else
						ThursdayElmt = prepareWebElementWithDynamicXpathWithInt(ThursdayW, i);
					
					if(isElementPresent(FridayElement))
						FridayElmt = prepareWebElementWithDynamicXpathWithInt(FridayG, i);
					else
						FridayElmt = prepareWebElementWithDynamicXpathWithInt(FridayW, i);
					
					if(isElementPresent(SaturdayElement))
						SaturdayElmt = prepareWebElementWithDynamicXpathWithInt(SaturdayG, i);
					else
						SaturdayElmt = prepareWebElementWithDynamicXpathWithInt(SaturdayW, i);
					
					if(isElementPresent(SundayElement))
						SundayElmt = prepareWebElementWithDynamicXpathWithInt(SundayG, i);
					else
						SundayElmt = prepareWebElementWithDynamicXpathWithInt(SundayW, i);
					
					Dept = getText(Department);
					Mon = getText(MondayElmt);
					Tue = getText(TuesdayElmt);
					Wed = getText(WednesdayElmt);
					Thu = getText(ThursdayElmt);
					Fri = getText(FridayElmt);
					Sat = getText(SaturdayElmt);
					Sun = getText(SundayElmt);
					
					MonVal= TrimData(Mon);
					MondayVal = ConvertBigDecimal(MonVal);
					TueVal= TrimData(Tue);
					TuesdayVal = ConvertBigDecimal(TueVal);
					WedVal= TrimData(Wed);
					WednesdayVal = ConvertBigDecimal(WedVal);
					ThuVal= TrimData(Thu);
					ThursdayVal = ConvertBigDecimal(ThuVal);
					FriVal= TrimData(Fri);
					FridayVal = ConvertBigDecimal(FriVal);
					SatVal= TrimData(Sat);
					SaturdayVal = ConvertBigDecimal(SatVal);
					SunVal= TrimData(Sun);
					SundayVal = ConvertBigDecimal(SunVal);
					
					row.add(Dept);
					row.add(MondayVal);	
					row.add(TuesdayVal);	
					row.add(WednesdayVal);	
					row.add(ThursdayVal);	
					row.add(FridayVal);	
					row.add(SaturdayVal);	
					row.add(SundayVal);	
					
					UIValues.add(row);
					System.out.println(UIValues);
			}
				return UIValues;
			}
			catch (Exception e) 
			{
				System.out.println("Exception occured while Fetching Data");
				e.printStackTrace();
			}
			return null;
			}
		
		//Get UI Value - Planned Sales
				public List<List<Object>> PlannedUIResults_PayRole(String PlanCompletedStr,String PlanNotCompletedStr,String MondayG,String MondayW,String TuesdayG,String TuesdayW,String WednesdayG,String WednesdayW,
						String ThursdayG,String ThursdayW,
						String FridayG,String FridayW,String SaturdayG,String SaturdayW,String SundayG,String SundayW,String Table) 
				{
					try 
					{
						int i,columnsize=3;
						String Element,ThursdayElement,FridayElement,SaturdayElement,SundayElement,MondayElement,TuesdayElement,WednesdayElement;
						WebElement Department = null,MondayElmt,TuesdayElmt,WednesdayElmt,ThursdayElmt,FridayElmt,SaturdayElmt,SundayElmt;
						BigDecimal MondayVal,TuesdayVal,WednesdayVal,ThursdayVal,FridayVal,SaturdayVal,SundayVal;
						String Dept,Mon,Tue,Wed,Thu,Fri,Sat,Sun;
						double MonVal,TueVal,WedVal,ThuVal,FriVal,SatVal,SunVal;
						List<List<Object>> UIValues= new  ArrayList<List<Object>>();
						List<Object> row = new ArrayList<>(columnsize);
						int RowCount =TradingStatementTableCount(Table);	
							
						for(i=0;i<RowCount;i++)
						{
							row = new ArrayList<>(columnsize);
							MondayElement = MondayG.replace("dynamic",""+i);
							TuesdayElement = TuesdayG.replace("dynamic",""+i);
							WednesdayElement = WednesdayG.replace("dynamic",""+i);
							Element = PlanCompletedStr.replace("dynamic",""+i);
							ThursdayElement = ThursdayG.replace("dynamic",""+i);
							FridayElement = FridayG.replace("dynamic",""+i);
							SaturdayElement = SaturdayG.replace("dynamic",""+i);
							SundayElement = SundayG.replace("dynamic",""+i);
							
							if(isElementPresent(Element))
							{
								Department = prepareWebElementWithDynamicXpathWithInt(PlanCompletedStr, i); 
								Dept = getText(Department);
							}else
							{
								Department = prepareWebElementWithDynamicXpathWithInt(PlanNotCompletedStr, i); 
								Dept = getText(Department);
							}
							
							if(isElementPresent(MondayElement))
								MondayElmt = prepareWebElementWithDynamicXpathWithInt(MondayG, i);
							else
								MondayElmt = prepareWebElementWithDynamicXpathWithInt(MondayW, i); 
							
							if(isElementPresent(TuesdayElement))
								TuesdayElmt = prepareWebElementWithDynamicXpathWithInt(TuesdayG, i);
							else
								TuesdayElmt = prepareWebElementWithDynamicXpathWithInt(TuesdayW, i);
							
							if(isElementPresent(WednesdayElement))
								WednesdayElmt = prepareWebElementWithDynamicXpathWithInt(WednesdayG, i);
							else
								WednesdayElmt = prepareWebElementWithDynamicXpathWithInt(WednesdayW, i);
							if(isElementPresent(ThursdayElement))
								ThursdayElmt = prepareWebElementWithDynamicXpathWithInt(ThursdayG, i);
							else
								ThursdayElmt = prepareWebElementWithDynamicXpathWithInt(ThursdayW, i);
							
							if(isElementPresent(FridayElement))
								FridayElmt = prepareWebElementWithDynamicXpathWithInt(FridayG, i);
							else
								FridayElmt = prepareWebElementWithDynamicXpathWithInt(FridayW, i);
							
							if(isElementPresent(SaturdayElement))
								SaturdayElmt = prepareWebElementWithDynamicXpathWithInt(SaturdayG, i);
							else
								SaturdayElmt = prepareWebElementWithDynamicXpathWithInt(SaturdayW, i);
							
							if(isElementPresent(SundayElement))
								SundayElmt = prepareWebElementWithDynamicXpathWithInt(SundayG, i);
							else
								SundayElmt = prepareWebElementWithDynamicXpathWithInt(SundayW, i);
							
							Dept = getText(Department);
							Mon = getText(MondayElmt);
							Tue = getText(TuesdayElmt);
							Wed = getText(WednesdayElmt);
							Thu = getText(ThursdayElmt);
							Fri = getText(FridayElmt);
							Sat = getText(SaturdayElmt);
							Sun = getText(SundayElmt);
							
							MonVal= TrimData(Mon);
							MondayVal = ConvertBigDecimal(MonVal);
							TueVal= TrimData(Tue);
							TuesdayVal = ConvertBigDecimal(TueVal);
							WedVal= TrimData(Wed);
							WednesdayVal = ConvertBigDecimal(WedVal);
							ThuVal= TrimData(Thu);
							ThursdayVal = ConvertBigDecimal(ThuVal);
							FriVal= TrimData(Fri);
							FridayVal = ConvertBigDecimal(FriVal);
							SatVal= TrimData(Sat);
							SaturdayVal = ConvertBigDecimal(SatVal);
							SunVal= TrimData(Sun);
							SundayVal = ConvertBigDecimal(SunVal);
							
							row.add(Dept);
							row.add(MondayVal);	
							row.add(TuesdayVal);	
							row.add(WednesdayVal);	
							row.add(ThursdayVal);	
							row.add(FridayVal);	
							row.add(SaturdayVal);	
							row.add(SundayVal);	
							
							UIValues.add(row);
							System.out.println(UIValues);
					}
						return UIValues;
					}
					catch (Exception e) 
					{
						System.out.println("Exception occured while Fetching Data");
						e.printStackTrace();
					}
					return null;
				}
				
				
				// DTS Group to WOW New Changes in UI				
				public List<List<Object>> DayWiseValue(String Value,WebElement DataFrame,String Monday,String Tuesday,String Wednesday,String Thursday,
						String Friday,String Saturday,String Sunday,String Table,String DepartmentName)
				{
					try 
					{
						int i,j = 0,initial = 0,columnsize=3;
						WebElement Department = null,MondayElmt,TuesdayElmt,WednesdayElmt,ThursdayElmt,FridayElmt,SaturdayElmt,SundayElmt,WeektoDateElmt;
						String Dept,Mon,Tue,Wed,Thu,Fri,Sat,Sun;
						double MonVal,TueVal,WedVal,ThuVal,FriVal,SatVal,SunVal;
						List<List<Object>> UIValues= new  ArrayList<List<Object>>();	
						int RowCount =TradingStatementTableCount(Table);	
					
						//Initialize Value for Loops
						if(Value.equalsIgnoreCase("Sales"))
							initial=9;
						if(Value.equalsIgnoreCase("SMS"))
							initial=10; 
						if(Value.equalsIgnoreCase("Hours"))
							initial=11;
						if(Value.equalsIgnoreCase("OR"))
							initial=12;
						if(Value.equalsIgnoreCase("Wages"))
							initial=13;
						if(Value.equalsIgnoreCase("WagesPercentage"))
							initial=14;
						if(Value.equalsIgnoreCase("Items"))
							initial=15;
						if(Value.equalsIgnoreCase("Customers"))
							initial=16;
						if(Value.equalsIgnoreCase("CPH"))
							initial=17;
						
						for(i=initial;i<=RowCount-8;i=i+9)
						{
							List<Object> row = new ArrayList<>(columnsize);
							System.out.println(i);
							
							if(i<9)
								j=j;
							else
								j=j+9;
							if(i>RowCount-16 && Value.equalsIgnoreCase("Sales"))
								break;
							if(i>RowCount-15 && Value.equalsIgnoreCase("SMS"))
								break;
							if(i>RowCount-14 && Value.equalsIgnoreCase("Hours"))
								break;
							if(i>RowCount-13 && Value.equalsIgnoreCase("OR"))
								break;
							if(i>RowCount-12 && Value.equalsIgnoreCase("Wages"))
								break;
							if(i>RowCount-11 && Value.equalsIgnoreCase("WagesPercentage"))
								break;
							if(i>RowCount-10 && Value.equalsIgnoreCase("Items"))
								break;
							if(i>RowCount-9 && Value.equalsIgnoreCase("Customers"))
								break;
							if(i>RowCount-8 && Value.equalsIgnoreCase("CPH"))
								break;
					// To Get Department and Store Total Column Names
							if(j>RowCount-8)
								break;
							else
								Department = prepareWebElementWithDynamicXpathWithInt(DepartmentName, j); 
							
							MondayElmt = prepareWebElementWithDynamicXpathWithInt(Monday, i);
							TuesdayElmt = prepareWebElementWithDynamicXpathWithInt(Tuesday, i);
							WednesdayElmt = prepareWebElementWithDynamicXpathWithInt(Wednesday, i);
							ThursdayElmt = prepareWebElementWithDynamicXpathWithInt(Thursday, i);
							FridayElmt = prepareWebElementWithDynamicXpathWithInt(Friday, i);
							SaturdayElmt = prepareWebElementWithDynamicXpathWithInt(Saturday, i);
							SundayElmt = prepareWebElementWithDynamicXpathWithInt(Sunday, i);
							
							Dept = getText(Department);
							Mon = getText(MondayElmt);
							Tue = getText(TuesdayElmt);
							Wed = getText(WednesdayElmt);
							Thu = getText(ThursdayElmt);
							Fri = getText(FridayElmt);
							Sat = getText(SaturdayElmt);
							Sun = getText(SundayElmt);
							
							MonVal= TrimData(Mon);
							TueVal= TrimData(Tue);
							WedVal= TrimData(Wed);
							ThuVal= TrimData(Thu);
							FriVal= TrimData(Fri);
							SatVal= TrimData(Sat);
							SunVal= TrimData(Sun);

							row.add(Dept);
							row.add(MonVal);	
							row.add(TueVal);	
							row.add(WedVal);	
							row.add(ThuVal);	
							row.add(FriVal);	
							row.add(SatVal);	
							row.add(SunVal);	
							
							UIValues.add(row);
						}
						System.out.println(UIValues);
						return UIValues;
					}
					catch (Exception e) 
					{
						System.out.println("Exception occured while Fetching Day Wise Individual Values");
						e.printStackTrace();
					}
					return null;
					}
		
				public List<List<Object>> DayWiseValueZone(String Value,WebElement DataFrame,String Monday,String Tuesday,String Wednesday,String Thursday,
						String Friday,String Saturday,String Sunday,String Table,String DepartmentName)
				{
					try 
					{
						int i,j = 0,initial = 0,columnsize=3;
						WebElement Department = null,MondayElmt,TuesdayElmt,WednesdayElmt,ThursdayElmt,FridayElmt,SaturdayElmt,SundayElmt,WeektoDateElmt;
						String Dept,Mon,Tue,Wed,Thu,Fri,Sat,Sun;
						double MonVal,TueVal,WedVal,ThuVal,FriVal,SatVal,SunVal;
						List<List<Object>> UIValues= new  ArrayList<List<Object>>();	
						int RowCount =TradingStatementTableCount(Table);	
					
						//Initialize Value for Loops
						if(Value.equalsIgnoreCase("Sales"))
							initial=9;
						if(Value.equalsIgnoreCase("SMS"))
							initial=10; 
						if(Value.equalsIgnoreCase("Hours"))
							initial=11;
						if(Value.equalsIgnoreCase("OR"))
							initial=12;
						if(Value.equalsIgnoreCase("Wages"))
							initial=13;
						if(Value.equalsIgnoreCase("WagesPercentage"))
							initial=14;
						if(Value.equalsIgnoreCase("Items"))
							initial=15;
						if(Value.equalsIgnoreCase("Customers"))
							initial=16;
						if(Value.equalsIgnoreCase("CPH"))
							initial=17;
						
						for(i=initial;i<=RowCount-4;i=i+9)
						{
							List<Object> row = new ArrayList<>(columnsize);
							System.out.println(i);
							
							if(i<9)
								j=j;
							else
								j=j+9;
							if(i>RowCount-13 && Value.equalsIgnoreCase("Sales"))
								break;
							if(i>RowCount-12 && Value.equalsIgnoreCase("SMS"))
								break;
							if(i>RowCount-11 && Value.equalsIgnoreCase("Hours"))
								break;
							if(i>RowCount-10 && Value.equalsIgnoreCase("OR"))
								break;
							if(i>RowCount-9 && Value.equalsIgnoreCase("Wages"))
								break;
							if(i>RowCount-8 && Value.equalsIgnoreCase("WagesPercentage"))
								break;
							if(i>RowCount-7 && Value.equalsIgnoreCase("Items"))
								break;
							if(i>RowCount-6 && Value.equalsIgnoreCase("Customers"))
								break;
							if(i>RowCount-4 && Value.equalsIgnoreCase("CPH"))
								break;
					// To Get Department and Store Total Column Names
							if(j>RowCount-5)
								break;
							else
								Department = prepareWebElementWithDynamicXpathWithInt(DepartmentName, j); 
							
							MondayElmt = prepareWebElementWithDynamicXpathWithInt(Monday, i);
							TuesdayElmt = prepareWebElementWithDynamicXpathWithInt(Tuesday, i);
							WednesdayElmt = prepareWebElementWithDynamicXpathWithInt(Wednesday, i);
							ThursdayElmt = prepareWebElementWithDynamicXpathWithInt(Thursday, i);
							FridayElmt = prepareWebElementWithDynamicXpathWithInt(Friday, i);
							SaturdayElmt = prepareWebElementWithDynamicXpathWithInt(Saturday, i);
							SundayElmt = prepareWebElementWithDynamicXpathWithInt(Sunday, i);
							
							Dept = getText(Department);
							Mon = getText(MondayElmt);
							Tue = getText(TuesdayElmt);
							Wed = getText(WednesdayElmt);
							Thu = getText(ThursdayElmt);
							Fri = getText(FridayElmt);
							Sat = getText(SaturdayElmt);
							Sun = getText(SundayElmt);
							
							MonVal= TrimData(Mon);
							TueVal= TrimData(Tue);
							WedVal= TrimData(Wed);
							ThuVal= TrimData(Thu);
							FriVal= TrimData(Fri);
							SatVal= TrimData(Sat);
							SunVal= TrimData(Sun);

							row.add(Dept);
							row.add(MonVal);	
							row.add(TueVal);	
							row.add(WedVal);	
							row.add(ThuVal);	
							row.add(FriVal);	
							row.add(SatVal);	
							row.add(SunVal);	
							
							UIValues.add(row);
						}
						System.out.println(UIValues);
						return UIValues;
					}
					catch (Exception e) 
					{
						System.out.println("Exception occured while Fetching Day Wise Individual Values");
						e.printStackTrace();
					}
					return null;
					}
				//FOR Getting  Week to date Values
				public List<List<Object>> WeekToDateValue(String Value,WebElement DataFrame,String WeekToDate,String DepartmentName,String Table)
				{
					try 
					{
						int i,j=0,initial = 0,columnsize=1;
						WebElement Department = null,WeektoDateElmt;
						String Dept,FullWeek=null;
						double FullWeekVal;
						List<List<Object>> UIValues= new  ArrayList<List<Object>>();	
						int RowCount =TradingStatementTableCount(Table);	
					
						//Initialize Value for Loops
						if(Value.equalsIgnoreCase("Sales"))
							initial=9;
						if(Value.equalsIgnoreCase("SMS"))
							initial=10;
						if(Value.equalsIgnoreCase("Hours"))
							initial=11;
						if(Value.equalsIgnoreCase("OR"))
							initial=12;
						if(Value.equalsIgnoreCase("Wages"))
							initial=13;
						if(Value.equalsIgnoreCase("WagesPercentage"))
							initial=14;
						if(Value.equalsIgnoreCase("Items"))
							initial=15;
						if(Value.equalsIgnoreCase("Customers"))
							initial=16;
						if(Value.equalsIgnoreCase("CPH"))
							initial=17;
						
						for(i=initial;i<=RowCount-8;i=i+9)
						{
							List<Object> row = new ArrayList<>(columnsize);
	
							System.out.println(i);
							
							if(i<9)
								j=j;
							else
								j=j+9;
								
							if(i>RowCount-16 && Value.equalsIgnoreCase("Sales"))
								break;
							if(i>RowCount-15 && Value.equalsIgnoreCase("SMS"))
								break;
							if(i>RowCount-14 && Value.equalsIgnoreCase("Hours"))
								break;
							if(i>RowCount-13 && Value.equalsIgnoreCase("OR"))
								break;
							if(i>RowCount-12 && Value.equalsIgnoreCase("Wages"))
								break;
							if(i>RowCount-11 && Value.equalsIgnoreCase("WagesPercentage"))
								break;
							if(i>RowCount-10 && Value.equalsIgnoreCase("Items"))
								break;
							if(i>RowCount-9 && Value.equalsIgnoreCase("Customers"))
								break;
							if(i>RowCount-7 && Value.equalsIgnoreCase("CPH"))
								break;
							
					// To Get Department and Store Total Column Names	
							if(i>RowCount-8)
								break;
							else
								Department = prepareWebElementWithDynamicXpathWithInt(DepartmentName, j);
							
							WeektoDateElmt = prepareWebElementWithDynamicXpathWithInt(WeekToDate, i);
								
							Dept = getText(Department);
							FullWeek = getText(WeektoDateElmt);
						
							FullWeekVal= TrimData(FullWeek);
							
							row.add(Dept);	
							row.add(FullWeekVal);
							
							UIValues.add(row);				
						}
						System.out.println(UIValues);
						return UIValues;
					}
					catch (Exception e) 
					{
						System.out.println("Exception occured while getting DTS Screen Week to Date Value");
						e.printStackTrace();
					}
					return null;
					}
				
				public List<List<Object>> WeekToDateValueZone(String Value,WebElement DataFrame,String WeekToDate,String DepartmentName,String Table)
				{
					try 
					{
						
						int i,j=0,initial = 0,columnsize=1;
						WebElement Department = null,WeektoDateElmt;
						String Dept,FullWeek=null;
						double FullWeekVal;
						List<List<Object>> UIValues= new  ArrayList<List<Object>>();	
						int RowCount =TradingStatementTableCount(Table);	

						//Initialize Value for Loops
						if(Value.equalsIgnoreCase("Sales"))
							initial=9;
						if(Value.equalsIgnoreCase("SMS"))
							initial=10;
						if(Value.equalsIgnoreCase("Hours"))
							initial=11;
						if(Value.equalsIgnoreCase("OR"))
							initial=12;
						if(Value.equalsIgnoreCase("Wages"))
							initial=13;
						if(Value.equalsIgnoreCase("WagesPercentage"))
							initial=14;
						if(Value.equalsIgnoreCase("Items"))
							initial=15;
						if(Value.equalsIgnoreCase("Customers"))
							initial=16;
						if(Value.equalsIgnoreCase("CPH"))
							initial=17;
						
						for(i=initial;i<=RowCount-4;i=i+9)
						{
							List<Object> row = new ArrayList<>(columnsize);
	
							System.out.println(i);
							
							if(i<9)
								j=j;
							else
								j=j+9;
								
							if(i>RowCount-13 && Value.equalsIgnoreCase("Sales"))
								break;
							if(i>RowCount-12 && Value.equalsIgnoreCase("SMS"))
								break;
							if(i>RowCount-11 && Value.equalsIgnoreCase("Hours"))
								break;
							if(i>RowCount-10 && Value.equalsIgnoreCase("OR"))
								break;
							if(i>RowCount-9 && Value.equalsIgnoreCase("Wages"))
								break;
							if(i>RowCount-8 && Value.equalsIgnoreCase("WagesPercentage"))
								break;
							if(i>RowCount-7 && Value.equalsIgnoreCase("Items"))
								break;
							if(i>RowCount-6 && Value.equalsIgnoreCase("Customers"))
								break;
							if(i>RowCount-5 && Value.equalsIgnoreCase("CPH"))
								break;
							
					// To Get Department and Store Total Column Names	
							
							if(i>RowCount-4)
								break;
							else
								Department = prepareWebElementWithDynamicXpathWithInt(DepartmentName, j);
							
							WeektoDateElmt = prepareWebElementWithDynamicXpathWithInt(WeekToDate, i);
								
							Dept = getText(Department);
							FullWeek = getText(WeektoDateElmt);
						
							FullWeekVal= TrimData(FullWeek);
							
							
							row.add(Dept);	
							row.add(FullWeekVal);
							
							UIValues.add(row);				
						}
						System.out.println(UIValues);
						return UIValues;
					}
					catch (Exception e) 
					{
						System.out.println("Exception occured while getting DTS Screen Week to Date Value");
						e.printStackTrace();
					}
					return null;
					}
				public List<List<Object>> DayWiseWowScreen(String Value,WebElement DataFrame,String Monday,String Tuesday,String Wednesday,String Thursday,
						String Friday,String Saturday,String Sunday,String Table,String StoreTotal) 
				{
					try
					{
						int i=0,columnsize=3;
						WebElement Department,MondayElmt,TuesdayElmt,WednesdayElmt,ThursdayElmt,FridayElmt,SaturdayElmt,SundayElmt;
						String Dept,Mon,Tue,Wed,Thu,Fri,Sat,Sun;
						double MonVal,TueVal,WedVal,ThuVal,FriVal,SatVal,SunVal;
						List<List<Object>> UIValues= new  ArrayList<List<Object>>();		
						List<Object> row = new ArrayList<>(columnsize);
						
						//Initialize Value for Loops
						
						if(Value.equalsIgnoreCase("Sales"))
							i=9;
						if(Value.equalsIgnoreCase("SMS"))
							i=10;
						if(Value.equalsIgnoreCase("Hours"))
							i=11;
						if(Value.equalsIgnoreCase("OR"))
							i=12;
						if(Value.equalsIgnoreCase("Wages"))
							i=13;
						if(Value.equalsIgnoreCase("WagesPercentage"))
							i=14;
						if(Value.equalsIgnoreCase("Items"))
							i=15;
						if(Value.equalsIgnoreCase("Customers"))
							i=16;
						if(Value.equalsIgnoreCase("CPH"))
							i=17;
						
					// To Get Department and Store Total Column Names	
							
							Department = prepareWebElementWithDynamicXpathWithInt(StoreTotal,0); 
							MondayElmt = prepareWebElementWithDynamicXpathWithInt(Monday, i);
							TuesdayElmt = prepareWebElementWithDynamicXpathWithInt(Tuesday, i);
							WednesdayElmt = prepareWebElementWithDynamicXpathWithInt(Wednesday, i);
							ThursdayElmt = prepareWebElementWithDynamicXpathWithInt(Thursday, i);
							FridayElmt = prepareWebElementWithDynamicXpathWithInt(Friday, i);
							SaturdayElmt = prepareWebElementWithDynamicXpathWithInt(Saturday, i);
							SundayElmt = prepareWebElementWithDynamicXpathWithInt(Sunday, i);

							Dept = getText(Department);
							Mon = getText(MondayElmt);
							Tue = getText(TuesdayElmt);
							Wed = getText(WednesdayElmt);
							Thu = getText(ThursdayElmt);
							Fri = getText(FridayElmt);
							Sat = getText(SaturdayElmt);
							Sun = getText(SundayElmt);
							
							MonVal= TrimData(Mon);
							TueVal= TrimData(Tue);
							WedVal= TrimData(Wed);
							ThuVal= TrimData(Thu);
							FriVal= TrimData(Fri);
							SatVal= TrimData(Sat);
							SunVal= TrimData(Sun);
							
							row.add(Dept);
							row.add(MonVal);	
							row.add(TueVal);	
							row.add(WedVal);	
							row.add(ThuVal);	
							row.add(FriVal);	
							row.add(SatVal);	
							row.add(SunVal);	
							
							UIValues.add(row);
							System.out.println(UIValues);
							return UIValues;
					}
					catch (Exception e) 
					{
						System.out.println("Exception occured while Fetching the Day Wise Total Value");
						e.printStackTrace();
					}
					return null;
				}
				
				//Day Wise Plan Total
				public List<List<Object>> DayWiseTotal(String Value,WebElement DataFrame,String Monday,String Tuesday,String Wednesday,String Thursday,
						String Friday,String Saturday,String Sunday,String Table,String StoreTotal) 
				{
					try
					{
						int i=0,columnsize=3;
						WebElement Department,MondayElmt,TuesdayElmt,WednesdayElmt,ThursdayElmt,FridayElmt,SaturdayElmt,SundayElmt;
						String Dept,Mon,Tue,Wed,Thu,Fri,Sat,Sun;
						double MonVal,TueVal,WedVal,ThuVal,FriVal,SatVal,SunVal;
						List<List<Object>> UIValues= new  ArrayList<List<Object>>();		
						List<Object> row = new ArrayList<>(columnsize);
						
						//Initialize Value for Loops
						
						if(Value.equalsIgnoreCase("Sales"))
							i=0;
						if(Value.equalsIgnoreCase("SMS"))
							i=1;
						if(Value.equalsIgnoreCase("Hours"))
							i=2;
						if(Value.equalsIgnoreCase("OR"))
							i=3;
						if(Value.equalsIgnoreCase("Wages"))
							i=4;
						if(Value.equalsIgnoreCase("WagesPercentage"))
							i=5;
						if(Value.equalsIgnoreCase("Items"))
							i=6;
						if(Value.equalsIgnoreCase("Customers"))
							i=7;
						if(Value.equalsIgnoreCase("CPH"))
							i=8;
						
					// To Get Department and Store Total Column Names	
							
							Department = prepareWebElementWithDynamicXpathWithInt(StoreTotal,0); 
							MondayElmt = prepareWebElementWithDynamicXpathWithInt(Monday, i);
							TuesdayElmt = prepareWebElementWithDynamicXpathWithInt(Tuesday, i);
							WednesdayElmt = prepareWebElementWithDynamicXpathWithInt(Wednesday, i);
							ThursdayElmt = prepareWebElementWithDynamicXpathWithInt(Thursday, i);
							FridayElmt = prepareWebElementWithDynamicXpathWithInt(Friday, i);
							SaturdayElmt = prepareWebElementWithDynamicXpathWithInt(Saturday, i);
							SundayElmt = prepareWebElementWithDynamicXpathWithInt(Sunday, i);

							Dept = getText(Department);
							Mon = getText(MondayElmt);
							Tue = getText(TuesdayElmt);
							Wed = getText(WednesdayElmt);
							Thu = getText(ThursdayElmt);
							Fri = getText(FridayElmt);
							Sat = getText(SaturdayElmt);
							Sun = getText(SundayElmt);
							
							MonVal= TrimData(Mon);
							TueVal= TrimData(Tue);
							WedVal= TrimData(Wed);
							ThuVal= TrimData(Thu);
							FriVal= TrimData(Fri);
							SatVal= TrimData(Sat);
							SunVal= TrimData(Sun);
							
							row.add(Dept);
							row.add(MonVal);	
							row.add(TueVal);	
							row.add(WedVal);	
							row.add(ThuVal);	
							row.add(FriVal);	
							row.add(SatVal);	
							row.add(SunVal);	
							
							UIValues.add(row);
							System.out.println(UIValues);
							return UIValues;
					}
					catch (Exception e) 
					{
						System.out.println("Exception occured while Fetching the Day Wise Total Value");
						e.printStackTrace();
					}
					return null;
				}
				
				
				public List<List<Object>> DayWiseValueState(String Value,WebElement DataFrame,String Monday,String Tuesday,String Wednesday,String Thursday,
						String Friday,String Saturday,String Sunday,String Table,String DepartmentName) 
				{
					try
					{
						int i=0,columnsize=3;
						WebElement Department,MondayElmt,TuesdayElmt,WednesdayElmt,ThursdayElmt,FridayElmt,SaturdayElmt,SundayElmt;
						String Dept,Mon,Tue,Wed,Thu,Fri,Sat,Sun;
						double MonVal,TueVal,WedVal,ThuVal,FriVal,SatVal,SunVal;
						List<List<Object>> UIValues= new  ArrayList<List<Object>>();		
						List<Object> row = new ArrayList<>(columnsize);
						
						//Initialize Value for Loops
						
						if(Value.equalsIgnoreCase("Sales"))
							i=9;
						if(Value.equalsIgnoreCase("SMS"))
							i=10;
						if(Value.equalsIgnoreCase("Hours"))
							i=11;
						if(Value.equalsIgnoreCase("OR"))
							i=12;
						if(Value.equalsIgnoreCase("Wages"))
							i=13;
						if(Value.equalsIgnoreCase("WagesPercentage"))
							i=14;
						if(Value.equalsIgnoreCase("Items"))
							i=15;
						if(Value.equalsIgnoreCase("Customers"))
							i=16;
						if(Value.equalsIgnoreCase("CPH"))
							i=17;
						
					// To Get Department and Store Total Column Names	
							
							Department = prepareWebElementWithDynamicXpathWithInt(DepartmentName,0); 
							MondayElmt = prepareWebElementWithDynamicXpathWithInt(Monday, i);
							TuesdayElmt = prepareWebElementWithDynamicXpathWithInt(Tuesday, i);
							WednesdayElmt = prepareWebElementWithDynamicXpathWithInt(Wednesday, i);
							ThursdayElmt = prepareWebElementWithDynamicXpathWithInt(Thursday, i);
							FridayElmt = prepareWebElementWithDynamicXpathWithInt(Friday, i);
							SaturdayElmt = prepareWebElementWithDynamicXpathWithInt(Saturday, i);
							SundayElmt = prepareWebElementWithDynamicXpathWithInt(Sunday, i);

							Dept = getText(Department);
							Mon = getText(MondayElmt);
							Tue = getText(TuesdayElmt);
							Wed = getText(WednesdayElmt);
							Thu = getText(ThursdayElmt);
							Fri = getText(FridayElmt);
							Sat = getText(SaturdayElmt);
							Sun = getText(SundayElmt);
							
							MonVal= TrimData(Mon);
							TueVal= TrimData(Tue);
							WedVal= TrimData(Wed);
							ThuVal= TrimData(Thu);
							FriVal= TrimData(Fri);
							SatVal= TrimData(Sat);
							SunVal= TrimData(Sun);
							
							row.add(Dept);
							row.add(MonVal);	
							row.add(TueVal);	
							row.add(WedVal);	
							row.add(ThuVal);	
							row.add(FriVal);	
							row.add(SatVal);	
							row.add(SunVal);	
							
							UIValues.add(row);
							System.out.println(UIValues);
							return UIValues;
					}
					catch (Exception e) 
					{
						System.out.println("Exception occured while Fetching the Day Wise  Value");
						e.printStackTrace();
					}
					return null;
				}
				
				//WeektodateTotal
				public List<List<Object>> WeekToDateTotal(String Value,WebElement DataFrame,String WeekToDate,String Table,
						String StoreTotal)
				{
					try
					{
						int i=0,columnsize=2;
						WebElement Department,WeektoDateElmt;
						String Dept,FullWeek=null;
						double FullWeekVal;
						List<List<Object>> UIValues= new  ArrayList<List<Object>>();	
						List<Object> row = new ArrayList<>(columnsize);
						
						//Initialize Value for Loops
						if(Value.equalsIgnoreCase("Sales"))
							i=0;
						if(Value.equalsIgnoreCase("SMS"))
							i=1;
						if(Value.equalsIgnoreCase("Hours"))
							i=2;
						if(Value.equalsIgnoreCase("OR"))
							i=3;
						if(Value.equalsIgnoreCase("Wages"))
							i=4;
						if(Value.equalsIgnoreCase("WagesPercentage"))
							i=5;
						if(Value.equalsIgnoreCase("Items"))
							i=6;
						if(Value.equalsIgnoreCase("Customers"))
							i=7;
						if(Value.equalsIgnoreCase("CPH"))
							i=8;
						
					// To Get Department and Store Total Column Names	
							
							Department = prepareWebElementWithDynamicXpathWithInt(StoreTotal,0); 							
							WeektoDateElmt = prepareWebElementWithDynamicXpathWithInt(WeekToDate, i);
							
							Dept = getText(Department);
							FullWeek = getText(WeektoDateElmt);

							FullWeekVal= TrimData(FullWeek);
							
							row.add(Dept);	
							row.add(FullWeekVal);
							
							UIValues.add(row);
							System.out.println(UIValues);
							return UIValues;
					}
					catch (Exception e) 
					{
						System.out.println("Exception occured while Fetching Week to Date Total");
						e.printStackTrace();
					}
					return null;
				}		
				//Wow screen Week to date total
				public List<List<Object>> WeekToDateTotalforWowscreen(String Value,WebElement DataFrame,String WeekToDate,String Table,
						String StoreTotal)
				{
					try
					{
						int i=0,columnsize=2;
						WebElement Department,WeektoDateElmt;
						String Dept,FullWeek=null;
						double FullWeekVal;
						List<List<Object>> UIValues= new  ArrayList<List<Object>>();	
						List<Object> row = new ArrayList<>(columnsize);
						
						//Initialize Value for Loops
						if(Value.equalsIgnoreCase("Sales"))
							i=9;
						if(Value.equalsIgnoreCase("SMS"))
							i=10;
						if(Value.equalsIgnoreCase("Hours"))
							i=11;
						if(Value.equalsIgnoreCase("OR"))
							i=12;
						if(Value.equalsIgnoreCase("Wages"))
							i=13;
						if(Value.equalsIgnoreCase("WagesPercentage"))
							i=14;
						if(Value.equalsIgnoreCase("Items"))
							i=15;
						if(Value.equalsIgnoreCase("Customers"))
							i=16;
						if(Value.equalsIgnoreCase("CPH"))
							i=17;
						
					// To Get Department and Store Total Column Names	
							
							Department = prepareWebElementWithDynamicXpathWithInt(StoreTotal,0); 							
							WeektoDateElmt = prepareWebElementWithDynamicXpathWithInt(WeekToDate, i);
							
							Dept = getText(Department);
							FullWeek = getText(WeektoDateElmt);

							FullWeekVal= TrimData(FullWeek);
							
							row.add(Dept);	
							row.add(FullWeekVal);
							
							UIValues.add(row);
							System.out.println(UIValues);
							return UIValues;
					}
					catch (Exception e) 
					{
						System.out.println("Exception occured while Fetching Week to Date Total");
						e.printStackTrace();
					}
					return null;
				}
				public List<List<Object>> DayWiseValueZoneCPH(String Value,WebElement DataFrame,String Monday,String Tuesday,String Wednesday,String Thursday,
						String Friday,String Saturday,String Sunday,String Table,String DepartmentName)
				{
					try 
					{
						int i,j = 0,initial = 0,columnsize=3;
						WebElement Department = null,MondayElmt,TuesdayElmt,WednesdayElmt,ThursdayElmt,FridayElmt,SaturdayElmt,SundayElmt,WeektoDateElmt;
						String Dept,Mon,Tue,Wed,Thu,Fri,Sat,Sun;
						double MonVal,TueVal,WedVal,ThuVal,FriVal,SatVal,SunVal;
						List<List<Object>> UIValues= new  ArrayList<List<Object>>();	
						int RowCount =TradingStatementTableCount(Table);	
					
						//Initialize Value for Loops
						if(Value.equalsIgnoreCase("Sales"))
							initial=9;
						if(Value.equalsIgnoreCase("SMS"))
							initial=10; 
						if(Value.equalsIgnoreCase("Hours"))
							initial=11;
						if(Value.equalsIgnoreCase("OR"))
							initial=12;
						if(Value.equalsIgnoreCase("Wages"))
							initial=13;
						if(Value.equalsIgnoreCase("WagesPercentage"))
							initial=14;
						if(Value.equalsIgnoreCase("Items"))
							initial=15;
						if(Value.equalsIgnoreCase("Customers"))
							initial=16;
						if(Value.equalsIgnoreCase("CPH"))
							initial=17;
						
						for(i=initial;i<=RowCount-4;i=i+9)
						{
							List<Object> row = new ArrayList<>(columnsize);
							System.out.println(i);
							
							if(i<9)
								j=j;
							else
								j=j+9;
							if(i>RowCount-16 && Value.equalsIgnoreCase("Sales"))
								break;
							if(i>RowCount-15 && Value.equalsIgnoreCase("SMS"))
								break;
							if(i>RowCount-14 && Value.equalsIgnoreCase("Hours"))
								break;
							if(i>RowCount-13 && Value.equalsIgnoreCase("OR"))
								break;
							if(i>RowCount-12 && Value.equalsIgnoreCase("Wages"))
								break;
							if(i>RowCount-11 && Value.equalsIgnoreCase("WagesPercentage"))
								break;
							if(i>RowCount-10 && Value.equalsIgnoreCase("Items"))
								break;
							if(i>RowCount-9 && Value.equalsIgnoreCase("Customers"))
								break;
							if(i>RowCount-5 && Value.equalsIgnoreCase("CPH"))
								break;
					// To Get Department and Store Total Column Names
							if(j>RowCount-5)
								break;
							else
								Department = prepareWebElementWithDynamicXpathWithInt(DepartmentName, j); 
							
							MondayElmt = prepareWebElementWithDynamicXpathWithInt(Monday, i);
							TuesdayElmt = prepareWebElementWithDynamicXpathWithInt(Tuesday, i);
							WednesdayElmt = prepareWebElementWithDynamicXpathWithInt(Wednesday, i);
							ThursdayElmt = prepareWebElementWithDynamicXpathWithInt(Thursday, i);
							FridayElmt = prepareWebElementWithDynamicXpathWithInt(Friday, i);
							SaturdayElmt = prepareWebElementWithDynamicXpathWithInt(Saturday, i);
							SundayElmt = prepareWebElementWithDynamicXpathWithInt(Sunday, i);
							
							Dept = getText(Department);
							Mon = getText(MondayElmt);
							Tue = getText(TuesdayElmt);
							Wed = getText(WednesdayElmt);
							Thu = getText(ThursdayElmt);
							Fri = getText(FridayElmt);
							Sat = getText(SaturdayElmt);
							Sun = getText(SundayElmt);
							
							MonVal= TrimData(Mon);
							TueVal= TrimData(Tue);
							WedVal= TrimData(Wed);
							ThuVal= TrimData(Thu);
							FriVal= TrimData(Fri);
							SatVal= TrimData(Sat);
							SunVal= TrimData(Sun);

							row.add(Dept);
							row.add(MonVal);	
							row.add(TueVal);	
							row.add(WedVal);	
							row.add(ThuVal);	
							row.add(FriVal);	
							row.add(SatVal);	
							row.add(SunVal);	
							
							UIValues.add(row);
						}
						System.out.println(UIValues);
						return UIValues;
					}
					catch (Exception e) 
					{
						System.out.println("Exception occured while Fetching Day Wise Individual Values");
						e.printStackTrace();
					}
					return null;
					}
				public List<List<Object>> WeekToDateValueZoneCPH(String Value,WebElement DataFrame,String WeekToDate,String DepartmentName,String Table)
				{
					try 
					{
						int i,j=0,initial = 0,columnsize=1;
						WebElement Department = null,WeektoDateElmt;
						String Dept,FullWeek=null;
						double FullWeekVal;
						List<List<Object>> UIValues= new  ArrayList<List<Object>>();	
						int RowCount =TradingStatementTableCount(Table);	
					
						//Initialize Value for Loops
						if(Value.equalsIgnoreCase("Sales"))
							initial=9;
						if(Value.equalsIgnoreCase("SMS"))
							initial=10;
						if(Value.equalsIgnoreCase("Hours"))
							initial=11;
						if(Value.equalsIgnoreCase("OR"))
							initial=12;
						if(Value.equalsIgnoreCase("Wages"))
							initial=13;
						if(Value.equalsIgnoreCase("WagesPercentage"))
							initial=14;
						if(Value.equalsIgnoreCase("Items"))
							initial=15;
						if(Value.equalsIgnoreCase("Customers"))
							initial=16;
						if(Value.equalsIgnoreCase("CPH"))
							initial=17;
						
						for(i=initial;i<=RowCount-4;i=i+9)
						{
							List<Object> row = new ArrayList<>(columnsize);
	
							System.out.println(i);
							
							if(i<9)
								j=j;
							else
								j=j+9;
								
							if(i>RowCount-15 && Value.equalsIgnoreCase("Sales"))
								break;
							if(i>RowCount-14 && Value.equalsIgnoreCase("SMS"))
								break;
							if(i>RowCount-13 && Value.equalsIgnoreCase("Hours"))
								break;
							if(i>RowCount-12 && Value.equalsIgnoreCase("OR"))
								break;
							if(i>RowCount-11 && Value.equalsIgnoreCase("Wages"))
								break;
							if(i>RowCount-10 && Value.equalsIgnoreCase("WagesPercentage"))
								break;
							if(i>RowCount-9 && Value.equalsIgnoreCase("Items"))
								break;
							if(i>RowCount-8 && Value.equalsIgnoreCase("Customers"))
								break;
							if(i>RowCount-5 && Value.equalsIgnoreCase("CPH"))
								break;
							
					// To Get Department and Store Total Column Names	
							if(i>RowCount-5)
								break;
							else
								Department = prepareWebElementWithDynamicXpathWithInt(DepartmentName, j);
							
							WeektoDateElmt = prepareWebElementWithDynamicXpathWithInt(WeekToDate, i);
								
							Dept = getText(Department);
							FullWeek = getText(WeektoDateElmt);
						
							FullWeekVal= TrimData(FullWeek);
							
							row.add(Dept);	
							row.add(FullWeekVal);
							
							UIValues.add(row);				
						}
						System.out.println(UIValues);
						return UIValues;
					}
					catch (Exception e) 
					{
						System.out.println("Exception occured while getting DTS Screen Week to Date Value");
						e.printStackTrace();
					}
					return null;
					}
}
