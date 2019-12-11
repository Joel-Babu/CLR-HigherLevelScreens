package au.wow.WLPmain.pages;

import java.io.File;
import java.io.IOException;
import java.math.BigDecimal;
import java.math.BigInteger;
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

import au.wow.WLPmain.objects.SalesHistoryCalendarObjects;
import au.wow.WLPmain.objects.WeeklyPlanningStoreObjects;
import au.wow.wlp.utils.BasePage;
import au.wow.wlp.utils.CommonRepository;
import au.wow.wlp.utils.CustomException;
import au.wow.wlp.utils.ExtentReportsScreenshotCode;
import au.wow.wlp.utils.SQLWrapper;
import au.wow.wlp.utils.TestBase;

import com.relevantcodes.extentreports.ExtentReports;
import com.relevantcodes.extentreports.ExtentTest;
import com.relevantcodes.extentreports.LogStatus;
import com.thoughtworks.selenium.webdriven.commands.Click;
import com.thoughtworks.selenium.webdriven.commands.GetAttribute;
import com.thoughtworks.selenium.webdriven.commands.GetText;

public class WeeklyPlanningStorePage extends TestBase
{
	CommonFunctions common = PageFactory.initElements(this.getDriver(),CommonFunctions.class);
	public Logger log = LogManager.getLogger(WeeklyPlanningStorePage.class);
	SQLWrapper sql = new SQLWrapper(log);
	WeeklyPlanningStoreObjects objWeeklyPlanningStoreObjects;
	static ExtentReportsScreenshotCode ScreenShotUtil; 
	static WebDriverWait wait;
	String image;
	public WeeklyPlanningStorePage()
	{
		// TODO Auto-generated constructor stub
		super();
	}
		
	public void SwitchingFrame(WebElement Frame) throws Exception
	{
		SwitchFrame(Frame);
	}
	
	//Select Week from Drop down
	public void VerifyUserName(WeeklyPlanningStoreObjects objWeeklyPlanningStoreObjects,ExtentTest extentTest,String UserName) throws Exception
	{
		try
		{
			if(getText(objWeeklyPlanningStoreObjects.LoggedUserName).equalsIgnoreCase("Welcome "+UserName+""))
			{
				System.out.println("User Name is Displayed Correctly");	
				Report_AddStep("testcase","Login User Name is displayed Correctly in HomePage" ,"","Welcome "+UserName+"", "Pass");
				htmlToExtent(cName,mName,extentTest,driver1, ""+UserName+" is displayed as User Name in Home Page ;;;Pass");
			}
			else
			{
				Report_AddStep("testcase","Failed to Display Login User Name Correctly in Home Page" ,"","Welcome "+UserName+"", "Fail");
				htmlToExtent(cName,mName,extentTest,driver1, "Failed to Display Login User Name Correctly in Home Page;;;Fail");
			}
		}
		catch(Exception e)
		{
			System.out.println("Exception Occured in Header Frame" +e.getMessage());
			Report_AddStep("testcase","Failed to Display Login User Name" ,"","Welcome "+getContext().getStringProperty("username"), "Fail");
			htmlToExtent(cName,mName,extentTest,driver1, "Exception occured while verifying User Name ;;;Fail");
		}
	}
	
	//Enter Store Number
	public void EnterStrNbr(WeeklyPlanningStoreObjects objWeeklyPlanningStoreObjects,ExtentTest extentTest) throws Exception
	{
		try
		{
			if((isDisplayed(objWeeklyPlanningStoreObjects.StoreNbrTitle)) && (getText(objWeeklyPlanningStoreObjects.StoreNbrTitle).equalsIgnoreCase("Please Select Store...")))
			{
				enterText(objWeeklyPlanningStoreObjects.StoreNbrTxtBox, "1412");
				Report_AddStep("testcase","Store Number is Entered Successfully into Text Box" ,"","", "Pass");
				htmlToExtent(cName,mName,extentTest,driver1, "Store Number is Entered Successfully into Text Box ;;;Pass");
			}
			else
			{
				Report_AddStep("testcase","Failed to Enter Store Number" ,"","", "Fail");
				htmlToExtent(cName,mName,extentTest,driver1, "Failed to Enter Store Number ;;;Fail");
			}
		}
		catch(Exception e)
		{
			System.out.println("Exception Occured" +e.getMessage()); 
			Report_AddStep("testcase","Exception Occured :Failed to Enter Store Number" ,"","", "Fail");
			htmlToExtent(cName,mName,extentTest,driver1, "Exception occured while entering Store Number ;;;Fail");
		}
	}
	
	//Select Week from Dropdown
	public void SelectWeekFromDropdown(WeeklyPlanningStoreObjects objWeeklyPlanningStoreObjects,ExtentTest extentTest,String Week) throws Exception
	{  
		try
		{
			if((isDisplayed(objWeeklyPlanningStoreObjects.WeekTitle)) && (getText(objWeeklyPlanningStoreObjects.WeekTitle).equalsIgnoreCase("Please Select Financial Week...")))
			{
				selectByValue(objWeeklyPlanningStoreObjects.WeekSelectionDropDown,Week);
				Report_AddStep("testcase","Fiscal Week is Selected Successfully from the Dropdown" ,"","", "Pass");
				htmlToExtent(cName,mName,extentTest,driver1, ""+Week+" is selected as Fiscal Week from Week Drop down;;;Pass");
			}
			else
			{
				Report_AddStep("testcase","Failed to Select Fiscal Week from DropDown" ,"","", "Fail");
				htmlToExtent(cName,mName,extentTest,driver1, "Failed to Select Fiscal Week from DropDown;;;Fail");
			}
		}
		catch(Exception e)
		{			
			System.out.println("Exception Occured" +e.getMessage()); 
			Report_AddStep("testcase","Failed to Select Fiscal Week from DropDown" ,"","", "Fail");
			htmlToExtent(cName,mName,extentTest,driver1, "Exception occured while selecting Week;;;Fail");
		}
	}
	
	//Select Week from Dropdown
	public void SelectPage(WeeklyPlanningStoreObjects objWeeklyPlanningStoreObjects,String Page,ExtentTest extentTest) throws Exception
	{
		try
		{
			if((isDisplayed(objWeeklyPlanningStoreObjects.PageTitle)) && (getText(objWeeklyPlanningStoreObjects.PageTitle).equalsIgnoreCase("Please Select Page...")))
			{
				selectByVisibleText(objWeeklyPlanningStoreObjects.PageSelectionDropDown,Page);
				click(objWeeklyPlanningStoreObjects.GoButton);
				Report_AddStep("testcase","Page to Display is Selected Successfully from the Dropdown" ,"","", "Pass");
				htmlToExtent(cName,mName,extentTest,driver1, ""+Page+" is selected as Page to display from Page Dropdown;;;Pass");
			}
			else
			{
				Report_AddStep("testcase","Failed to Select Page from DropDown" ,"","", "Fail");
				htmlToExtent(cName,mName,extentTest,driver1, "Failed to Select Page from DropDown;;;Fail");
			}
		}
		catch(Exception e)
		{
			System.out.println("Exception Occured" +e.getMessage()); 
			Report_AddStep("testcase","Failed to Select Page from DropDown" ,"","", "Fail");
			htmlToExtent(cName,mName,extentTest,driver1, "Exception occured while selecting Page from DropDown;;;Fail");
		}
	}
	
	//Select Week from Dropdown
	public void SelectHistoryDropdownToView(WeeklyPlanningStoreObjects objWeeklyPlanningStoreObjects,ExtentTest extentTest,String history) throws Exception
	{
		try
		{
			if((isDisplayed(objWeeklyPlanningStoreObjects.HistoryViewTitle)) && (getText(objWeeklyPlanningStoreObjects.HistoryViewTitle).equalsIgnoreCase("Select History View:")))
			{
				selectByVisibleText(objWeeklyPlanningStoreObjects.HistoryViewDropdown,history );
				Report_AddStep("testcase","History To Display is Selected Successfully from the Dropdown" ,"","", "Pass");
				htmlToExtent(cName,mName,extentTest,driver1, ""+history+" is selected as Option to View in History Drop down;;;Pass");
			}
			else
			{
				Report_AddStep("testcase","Failed to Select History Option from DropDown" ,"","", "Fail");
				htmlToExtent(cName,mName,extentTest,driver1, "Failed to Select option from history Drop down;;;Fail");
			}
		}
		catch(Exception e)
		{
			System.out.println("Exception Occured" +e.getMessage());
			Report_AddStep("testcase","Failed to Select History Option from DropDown" ,"","", "Fail");
			htmlToExtent(cName,mName,extentTest,driver1, "Exception occured while selecting option from history DropDown;;;Fail");
		}
	}
	public void SelectRegionDropdownToView(WeeklyPlanningStoreObjects objWeeklyPlanningStoreObjects,ExtentTest extentTest,String history) throws Exception
	{
		try
		{
			//if((isDisplayed(objWeeklyPlanningStoreObjects.HistoryViewTitle)) && (getText(objWeeklyPlanningStoreObjects.HistoryViewTitle).equalsIgnoreCase("Select History View:")))
			//{
				selectByVisibleText(objWeeklyPlanningStoreObjects.RegionViewDropdown,history );
				Report_AddStep("testcase","History To Display is Selected Successfully from the Dropdown" ,"","", "Pass");
				htmlToExtent(cName,mName,extentTest,driver1, ""+history+" is selected as Option to View in History Drop down;;;Pass");
			//}
			//else
			//{
			//	Report_AddStep("testcase","Failed to Select History Option from DropDown" ,"","", "Fail");
			//	htmlToExtent(cName,mName,extentTest,driver1, "Failed to Select option from history Drop down;;;Fail");
			//}
		}
		catch(Exception e)
		{
			System.out.println("Exception Occured" +e.getMessage());
			Report_AddStep("testcase","Failed to Select History Option from DropDown" ,"","", "Fail");
			htmlToExtent(cName,mName,extentTest,driver1, "Exception occured while selecting option from history DropDown;;;Fail");
		}
	}
		
	//Planned Sales to BDGT Rate
		public void PlannedSalestoBDGTRate(WeeklyPlanningStoreObjects objWeeklyPlanningStoreObjects,ExtentTest extentTest,String FieldToVerify,String Bdgtwgprcnt,
				String Plndsls,String Bdgtsls,String PlndWgs,String Bdgtwg,List<List<Object>> PositiveFactor,List<List<Object>> NegativeFactor) throws Exception
		{
			int i;
			String Result;
			double RoundAcceptableDiff,Bdgtwgprcntge,Plannedsales,Budgetsales,bgdtwgprcnt,BudgetWage,PlannedWages,FinalBDGT,finalBdgtWgPrcnt,
			BDGTRate=0.0,CalculatedValue=0.0,FinalTotalSum=0.0,PositiveRateFctr,NegativeRateFctr;
			String CalVal;
			String Value,Value1,Value2,Value3,Value4,Value5,CalcValue,UIValue,Dept = null;
			BigDecimal ValFinal = null;
			RoundAcceptableDiff = 0.01;
			ValFinal =ConvertBigDecimal(RoundAcceptableDiff);
			
			try
			{	
				int rowcount= WeeklyPlanningStoreTablecount();
				for(i=1;i<=rowcount-1;i++)
				{
					WebElement Department = prepareWebElementWithDynamicXpathWithInt(objWeeklyPlanningStoreObjects.DepartmentNames, i);
					WebElement xpath1 = prepareWebElementWithDynamicXpathWithInt(Bdgtwgprcnt, i);
					WebElement xpath2 = prepareWebElementWithDynamicXpathWithInt(Plndsls, i);
					WebElement xpath3 = prepareWebElementWithDynamicXpathWithInt(Bdgtsls, i);
					WebElement xpath4 = prepareWebElementWithDynamicXpathWithInt(PlndWgs, i);
					WebElement xpath5 = prepareWebElementWithDynamicXpathWithInt(Bdgtwg, i);
					WebElement CalculatedXpath = prepareWebElementWithDynamicXpathWithInt(FieldToVerify, i);
					
					Dept = getText(Department);
					Value1 = getText(xpath1);
					Value2 = getText(xpath2);
					Value3 = getText(xpath3);
					Value4 = getText(xpath4);
					Value5 = getText(xpath5);
					
					Value = getText(CalculatedXpath);
					Bdgtwgprcntge=TrimData(Value1);
					Plannedsales=TrimData(Value2);
					Budgetsales=TrimData(Value3);
					PlannedWages=TrimData(Value4);
					BudgetWage=TrimData(Value5);
					
					//conversion of 5 decimal for Budget wage Percentage
					bgdtwgprcnt = (BudgetWage/Budgetsales);
					CalVal = String.format("%.5f", bgdtwgprcnt);
					finalBdgtWgPrcnt = Double.parseDouble(CalVal);
					System.out.println(finalBdgtWgPrcnt);
					
					//UI Value which needs to be compared
					BDGTRate=TrimData(Value);
					System.out.println(BDGTRate+" "+Value);
					BigDecimal ActlVal =ConvertBigDecimalNbr(BDGTRate);
					
					//Rate Factor Conversion
					PositiveRateFctr =new Double(PositiveFactor.get(0).get(0).toString());
					NegativeRateFctr =new Double(NegativeFactor.get(0).get(0).toString());
							
					if(Plannedsales>Budgetsales)
					{		
						CalculatedValue = (PlannedWages - ((((PositiveRateFctr * finalBdgtWgPrcnt) * (Plannedsales - Budgetsales)) + BudgetWage)));
						System.out.println(CalculatedValue);
						
						BigDecimal CalcVal =ConvertBigDecimalNbr(CalculatedValue);
						Result = (ActlVal.subtract(CalcVal).toString());
						boolean Final1 =Result.equals("-1");
 						boolean Final = Result.equals("1");
						if(ActlVal.equals(CalcVal) || Final==true || Final1==true )
						{
							System.out.println(ActlVal+"  "+CalcVal);
							Report_AddStep("testcase","Calculated Value for Department "+Dept+" is displayed successfully" ,""+ActlVal+"",""+CalcVal+"", "Pass");
							htmlToExtent(cName,mName,extentTest,driver1, "Value displayed Correctly for : "+Dept+"; "+ActlVal+";  "+CalcVal+";Pass");
						}
						else
						{
							System.out.println(ActlVal+"  "+CalcVal);
							Report_AddStep("testcase","Failed to Display Value Correctly for Department "+Dept+"" ,""+ActlVal+"",""+CalcVal+"", "Fail");
							htmlToExtent(cName,mName,extentTest,driver1, "Failed to display data Correctly for : "+Dept+"; "+ActlVal+";  "+CalcVal+";Fail");
						}
					}else if(Plannedsales<Budgetsales)
					{
						CalculatedValue = (PlannedWages - ((((NegativeRateFctr * finalBdgtWgPrcnt) * (Plannedsales - Budgetsales)) + BudgetWage)));
						System.out.println(CalculatedValue);
						
						BigDecimal CalcVal =ConvertBigDecimalNbr(CalculatedValue);
						Result = (ActlVal.subtract(CalcVal).toString());
						boolean Final1 =Result.equals("-1");
 						boolean Final = Result.equals("1");
						if(ActlVal.equals(CalcVal) || Final==true || Final1==true )
						{
							System.out.println(ActlVal+"  "+CalcVal);
							Report_AddStep("testcase","Calculated Value for Department "+Dept+" is displayed successfully" ,""+ActlVal+"",""+CalcVal+"", "Pass");
							htmlToExtent(cName,mName,extentTest,driver1, "Value displayed Correctly for : "+Dept+"; "+ActlVal+";  "+CalcVal+";Pass");
						}
						else
						{
							System.out.println(ActlVal+"  "+CalcVal);
							Report_AddStep("testcase","Failed to Display Value Correctly for Department "+Dept+"" ,""+ActlVal+"",""+CalcVal+"", "Fail");
							htmlToExtent(cName,mName,extentTest,driver1, "Failed to display data Correctly for : "+Dept+"; "+ActlVal+";  "+CalcVal+";Fail");
						}
					}	
					else if(Plannedsales==Budgetsales)
					{
						CalculatedValue = (PlannedWages - BudgetWage);
						System.out.println(CalculatedValue);
						
						BigDecimal CalcVal =ConvertBigDecimalNbr(CalculatedValue);
						Result = (ActlVal.subtract(CalcVal).toString());
						boolean Final1 =Result.equals("-1");
 						boolean Final = Result.equals("1");
						if(ActlVal.equals(CalcVal) || Final==true || Final1==true )
						{
							System.out.println(ActlVal+"  "+CalcVal);
							Report_AddStep("testcase","Calculated Value for Department "+Dept+" is displayed successfully" ,""+ActlVal+"",""+CalcVal+"", "Pass");
							htmlToExtent(cName,mName,extentTest,driver1, "Value displayed Correctly for : "+Dept+"; "+ActlVal+";  "+CalcVal+";Pass");
						}
						else
						{
							System.out.println(ActlVal+"  "+CalcVal);
							Report_AddStep("testcase","Failed to Display Value Correctly for Department "+Dept+"" ,""+ActlVal+"",""+CalcVal+"", "Fail");
							htmlToExtent(cName,mName,extentTest,driver1, "Failed to display data Correctly for : "+Dept+"; "+ActlVal+";  "+CalcVal+";Fail");
						}
					}	
					if(Dept.contains("TOTAL"))
						break;
					else
						continue;
				}	
			}
			catch(Exception e)
			{
				System.out.println("Exception Occured" +e.getMessage());
				Report_AddStep("testcase","Failed to Fetch Department Total Value" ,"","", "Fail");
				htmlToExtent(cName,mName,extentTest,driver1, "Failed to Fetch Department Total Value ;;;Fail");
			}
			return;	
		}
	
		

	//Get UI Value to Compare with DB Value
		public List<List<Object>> UIResults(String Table,ExtentTest extentTest,String DepName,String FieldToVerify)
		{

			String Dept,Values=null;
			double CalcValue;
			int columnsize=2;
			List<List<Object>> UIValues= new  ArrayList<List<Object>>();
			
			int RowCount =TradingStatementTableCount(Table);	
			for(int i=1;i<=RowCount-2;i++) //6
			{
				List<Object> row = new ArrayList<>(columnsize);
				WebElement Department = prepareWebElementWithDynamicXpathWithInt(DepName, i);
				WebElement Data = prepareWebElementWithDynamicXpathWithInt(FieldToVerify, i);	
				Dept = getText(Department);
				Values = getText(Data);
				CalcValue=TrimData(Values);
				row.add(Dept);
				row.add(CalcValue);	
				UIValues.add(row);
			}
			return UIValues;
		}
		
		//Get UI Value to Compare with DB Value for MP screen
				public List<List<Object>> UIResultsMP(String Table,ExtentTest extentTest,String DepName,String FieldToVerify)
				{

					String Dept,Values=null;
					double CalcValue;
					int columnsize=2;
					List<List<Object>> UIValues= new  ArrayList<List<Object>>();
					
					int RowCount =TradingStatementTableCount(Table);	
					for(int i=1;i<=RowCount-7;i++)
					{
						List<Object> row = new ArrayList<>(columnsize);
						WebElement Department = prepareWebElementWithDynamicXpathWithInt(DepName, i);
						WebElement Data = prepareWebElementWithDynamicXpathWithInt(FieldToVerify, i);	
						Dept = getText(Department);
						Values = getText(Data);
						CalcValue=TrimData(Values);
						row.add(Dept);
						row.add(CalcValue);	
						UIValues.add(row);
					}
					return UIValues;
				}
				
		//PST 720 Week Table Get Text Method
		public List<List<Object>> UIResultsGroup(String Table,ExtentTest extentTest,String DepName,String FieldToVerify)
		{

			String Dept,Values=null;
			double CalcValue;
			int columnsize=2;
			List<List<Object>> UIValues= new  ArrayList<List<Object>>();
			
			int RowCount =TradingStatementTableCount(Table);	
			int j=RowCount-6;
			for( int i=j;i<=RowCount-2;i++)
			{
				List<Object> row = new ArrayList<>(columnsize);
				WebElement Department = prepareWebElementWithDynamicXpathWithInt(DepName, i);
				WebElement Data = prepareWebElementWithDynamicXpathWithInt(FieldToVerify, i);	
				Dept = getText(Department);
				Values = getText(Data);
				CalcValue=TrimData(Values);
				row.add(Dept);
				row.add(CalcValue);	
				UIValues.add(row);
			}
			return UIValues;
		}
		
		//PST 760 WEEkTABLE COMPARISON METHOD
		public List<List<Object>> UIResultsWeekData(WeeklyPlanningStoreObjects objWeeklyPlanningStoreObjects,ExtentTest extentTest,String FieldToVerify)
		{
			String Dept,Values=null;
			double CalcValue;
			int columnsize=2;
			List<List<Object>> UIValues= new  ArrayList<List<Object>>();
			
			int RowCount =WeeklyPlanningStoreTablecount();	
			for(int i=1;i<=RowCount-1;i++)
			{
				List<Object> row = new ArrayList<>(columnsize);
				WebElement Department = prepareWebElementWithDynamicXpathWithInt(objWeeklyPlanningStoreObjects.DepartmentNamesMPWeekTable, i);
				WebElement Data = prepareWebElementWithDynamicXpathWithInt(FieldToVerify, i);	
				Dept = getText(Department);
				Values = getText(Data);
				CalcValue=TrimData(Values);
				row.add(Dept);
				row.add(CalcValue);	
				UIValues.add(row);
			}
			return UIValues;
		}
		
		//Compare UI and DB values
		public void CompareValues(ExtentTest extentTest,List<List<Object>> DBValues,List<List<Object>> UIValues) throws Exception
		{
			try
			{	
				int RowCount = DBValues.size();
				double CalcuatedUIValue = 0,CalcuatedDBValue=0,RoundOffValue = 0,RoundOffValue1=0;
				String UIName=null;
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
							if(Variance <=2 && Variance >=-2)
							{
								Report_AddStep("testcase","System Fetch the Values from DB for : "+UIName+" and Displayed Correctly" ,""+CalcuatedUIValue+"",""+CalcuatedDBValue+"", "Pass");
								htmlToExtent(cName,mName,extentTest,driver1, "Value displayed Correctly for : "+UIName+"; "+CalcuatedUIValue+";  "+CalcuatedDBValue+";Pass");
							}
							else
							{
								Report_AddStep("testcase","Failed to Display the UI and DB Values Correctly "+UIName+"",""+CalcuatedUIValue+"" ,""+CalcuatedDBValue+"", "Fail");
								htmlToExtent(cName,mName,extentTest,driver1, "Failed to display data Correctly for : "+UIName+"; "+CalcuatedUIValue+";  "+CalcuatedDBValue+";Fail");
							}
						}
						else
						{
							UIName =new String(UIValues.get(i).get(j).toString());
						}
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
		
	//PST 720
	
	public void CompareValuesWeekTableMP(ExtentTest extentTest,List<List<Object>> DBValues,List<List<Object>> UIValues) throws Exception
	{
		try
		{	
			int RowCount = DBValues.size();
			double CalcuatedUIValue = 0,CalcuatedDBValue=0,RoundOffValue = 0,RoundOffValue1=0;
			String DBName=null,UIName=null;
			BigDecimal FinalDBValue = null,FinalUIValue=null;
			System.out.println(UIValues);
			System.out.println(DBValues);
		
			for(int i=0;i<=RowCount-1;i++)
			{
				for(int j=1;j<2;j++)
				{
					if(j!=10)
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
						if(Variance <=2 && Variance >=-2)
						{
							Report_AddStep("testcase","System Fetch the Values from DB  and Displayed Correctly" ,""+CalcuatedDBValue+"",""+CalcuatedUIValue+"", "Pass");
							htmlToExtent(cName,mName,extentTest,driver1, "Value displayed Correctly : ; "+CalcuatedUIValue+";  "+CalcuatedDBValue+";Pass");
						}
						else
						{
							System.out.println(UIName+" "+DBName);
							Report_AddStep("testcase","Failed to Display the UI and DB Values Correctly ",""+CalcuatedUIValue+"" ," "+CalcuatedDBValue+"", "Fail");					
							htmlToExtent(cName,mName,extentTest,driver1, "Failed to display data Correctly : ; "+CalcuatedUIValue+";  "+CalcuatedDBValue+";Fail");
						}
					}
					else
					{
						DBName =new String(DBValues.get(i).get(j).toString());
						UIName =new String(UIValues.get(i).get(j).toString());
						System.out.println(UIName+" "+DBName);
						if((DBName.trim()).equalsIgnoreCase(UIName.trim()))
						{
							Report_AddStep("testcase","Column Name are matching with UI and DB : "+DBName+" Correctly" ,"","", "Pass");
							htmlToExtent(cName,mName,extentTest,driver1, "Column Name are matching with UI and DB : "+DBName+" Correctly ;;;Pass");
						}
						else
						{
							Report_AddStep("testcase","Failed to Display Column Name in UI and DB Values Correctly "+DBName+"","" ,"", "Fail");	
							htmlToExtent(cName,mName,extentTest,driver1, "Failed to Display Column Name in UI and DB Values Correctly "+DBName+" ;;;Fail");
						}
					}
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
	
	public void CompareValuesTwo(ExtentTest extentTest,List<List<Object>> DBValues,List<List<Object>> UIValues) throws Exception
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
				for(int j=0;j<4;j++)
				{
					if(j==3 || j==2)
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
						if(Variance <=2 && Variance >=-2)
						{
							Report_AddStep("testcase","System Fetch the Values from DB for : "+DBName+" and Displayed Correctly" ,""+CalcuatedDBValue+"",""+CalcuatedUIValue+"", "Pass");
							htmlToExtent(cName,mName,extentTest,driver1, "Value displayed Correctly for : "+DBName+"; "+CalcuatedUIValue+";  "+CalcuatedDBValue+";Pass");
						}
						else
						{
							System.out.println(UIName+" "+DBName);
							Report_AddStep("testcase","Failed to Display the UI and DB Values Correctly "+DBName+"",""+CalcuatedUIValue+"" ," "+CalcuatedDBValue+"", "Fail");
							htmlToExtent(cName,mName,extentTest,driver1, "Failed to display data Correctly for : "+DBName+"; "+CalcuatedUIValue+";  "+CalcuatedDBValue+";Fail");
						}
					}
					else
					{
						DBName =new String(DBValues.get(i).get(j).toString());
						UIName =new String(UIValues.get(i).get(j).toString());
						System.out.println(UIName+" "+DBName);
						if((DBName.trim()).equalsIgnoreCase(UIName.trim()))
						{
							Report_AddStep("testcase","Column Name are matching with UI and DB : "+DBName+" Correctly" ,"","", "Pass");
							htmlToExtent(cName,mName,extentTest,driver1, "Column Name are matching with UI and DB : "+DBName+" Correctly ;;;Pass");
						}
						else
						{
							Report_AddStep("testcase","Failed to Display Column Name in UI and DB Values Correctly "+DBName+"","" ,"", "Fail");	
							htmlToExtent(cName,mName,extentTest,driver1, "Failed to Display Column Name in UI and DB Values Correctly "+DBName+" ;;;Fail");
						}
					}
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
	
	//Get MonthName
	public String MonthName(WeeklyPlanningStoreObjects objWeeklyPlanningStoreObjects,ExtentTest extentTest,String FieldToVerify)
	{
		String Values=null;	
		WebElement Data = prepareWebElementWithDynamicXpathWithInt(FieldToVerify, 0);
		Values = getText(Data);
		return Values;
	}
	
	//Verify Navigated Page Name
		public void VerifyPageName(WebElement Button) throws Exception
		{
			try
			{
				if(isDisplayed(Button))
				{
					click(Button);	
					Report_AddStep("testcase","Daily Planning Button is clicked Successfully" ,"","", "Pass");
					htmlToExtent(cName,mName,extentTest,driver1, "Daily Planning Button is clicked Successfully;;;Pass");
				}
				else
				{
					Report_AddStep("testcase","Failed to Click on Daily Planning Button" ,"","", "Fail");
					htmlToExtent(cName,mName,extentTest,driver1, "Failed to Click on Daily Planning Button ;;;Fail");
				}
			}
			catch(Exception e)
			{
				System.out.println("Exception Occured" +e.getMessage());
				Report_AddStep("testcase","Failed to Click on Daily Planning Button" ,"","", "Fail");
				htmlToExtent(cName,mName,extentTest,driver1, "Failed to Click on Daily Planning Button ;;;Fail");
			}
		}	

	//Compare UI and DB values
		public void CompareValueDecimalFields(ExtentTest extentTest,List<List<Object>> DBValues,List<List<Object>> UIValues) throws Exception
		{
			try
			{	
				int RowCount = DBValues.size();
				double CalcuatedUIValue = 0,CalcuatedDBValue=0,RoundOffValue = 0,RoundOffValue1=0;
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
							double Variance = biggy.doubleValue();
							if(Variance <=0.1 && Variance >=-0.1)
							{
								Report_AddStep("testcase","System Fetch the Values from DB for : "+DBName+" Correctly" ,""+CalcuatedUIValue+"",""+CalcuatedDBValue+"", "Pass");
								htmlToExtent(cName,mName,extentTest,driver1, "Value displayed Correctly for : "+DBName+"; "+CalcuatedUIValue+";  "+CalcuatedDBValue+";Pass");
							}
							else
							{
								Report_AddStep("testcase","Failed to Display the UI and DB Values Correctly "+DBName+"" ,""+CalcuatedUIValue+"" ,""+CalcuatedDBValue+"", "Fail");
								htmlToExtent(cName,mName,extentTest,driver1, "Failed to display data Correctly for : "+DBName+"; "+CalcuatedUIValue+";  "+CalcuatedDBValue+";Fail");
							}
						}
						else
						{
							DBName =new String(DBValues.get(i).get(j).toString());
						}
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
		
		
		public void CompareValueDecimalFieldsWeekTableMP(ExtentTest extentTest,List<List<Object>> DBValues,List<List<Object>> UIValues) throws Exception
		{
			try
			{	
				int RowCount = DBValues.size();
				double CalcuatedUIValue = 0,CalcuatedDBValue=0,RoundOffValue = 0,RoundOffValue1=0;
				String DBName=null,UIName=null;
				BigDecimal FinalDBValue = null,FinalUIValue=null;
				System.out.println(UIValues);
				System.out.println(DBValues);
			
				for(int i=0;i<=RowCount-1;i++)
				{
					for(int j=1;j<2;j++)
					{
						if(j!=10)
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
							double Variance = biggy.doubleValue();
							if(Variance <=0.10 && Variance >=-0.10)
							{
								Report_AddStep("testcase","System Fetch the Values from DB  Correctly" ,""+CalcuatedUIValue+"",""+CalcuatedDBValue+"", "Pass");
								htmlToExtent(cName,mName,extentTest,driver1, "Value displayed Correctly ; "+CalcuatedUIValue+";  "+CalcuatedDBValue+";Pass");
							}
							else
							{
								System.out.println(UIName+" "+DBName);
								Report_AddStep("testcase","Failed to Display the UI and DB Values Correctly " ,""+CalcuatedUIValue+"" ,""+CalcuatedDBValue+"", "Fail");
								htmlToExtent(cName,mName,extentTest,driver1, "Failed to display data Correctly ; "+CalcuatedUIValue+";  "+CalcuatedDBValue+";Fail");
							}
						}
						else
						{
							DBName =new String(DBValues.get(i).get(j).toString());
							UIName =new String(UIValues.get(i).get(j).toString());
							System.out.println(UIName+" "+DBName);
							if((DBName.trim()).equalsIgnoreCase(UIName.trim()))
							{
								Report_AddStep("testcase","Column Name are matching with UI and DB : "+DBName+" and Displayed Correctly" ,"","", "Pass");
								htmlToExtent(cName,mName,extentTest,driver1, "Column Name are matching with UI and DB : "+DBName+" and Displayed Correctly ;;;Pass");
							}
							else
							{
								Report_AddStep("testcase","Failed to Display Column Name in UI and DB Values Correctly "+DBName+"","" ,"", "Fail");	
								htmlToExtent(cName,mName,extentTest,driver1, "Failed to Display Column Name in UI and DB Values Correctly "+DBName+";;;Fail");							
							}
						}
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
		
		//Compare UI and DB values
		public void CompareValuesforLYHours(ExtentTest extentTest,List<List<Object>> DBValues,List<List<Object>> UIValues) throws Exception
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
							FinalDBValue =ConvertBigDecimalNbr(CalcuatedDBValue);
							System.out.println(FinalDBValue);
							
							CalcuatedUIValue =new Double(UIValues.get(i).get(j).toString());
							RoundOffValue1 = Math.round(CalcuatedUIValue);
							FinalUIValue =ConvertBigDecimalNbr(CalcuatedUIValue);
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
								System.out.println(UIName+" "+DBName);
								result=false;	
								break;
								
							}
						}
					}
					if(result==true)
					{
						Report_AddStep("testcase","System Fetch the Values from DB for : "+DBName+" and Displayed Correctly" ,""+CalcuatedDBValue+"",""+CalcuatedUIValue+"", "Pass");
						htmlToExtent(cName,mName,extentTest,driver1, "Value displayed Correctly for : "+DBName+"; "+CalcuatedUIValue+";  "+CalcuatedDBValue+";Pass");
					}
					else
					{
						Report_AddStep("testcase","Failed to Display the UI and DB Values Correctly "+DBName+" and value is "+CalcuatedDBValue+"",""+CalcuatedUIValue+"" ,"", "Fail");
						htmlToExtent(cName,mName,extentTest,driver1, "Failed to display data Correctly for : "+DBName+"; "+CalcuatedUIValue+";  "+CalcuatedDBValue+";Fail");
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
		
		public String[] VerifyRegionDropdownList(WeeklyPlanningStoreObjects objWeeklyPlanningStoreObjects,ExtentTest extentTest,String screen) throws Exception
		{
		
			try
			{
				
				List<String> RegionNm=new ArrayList<String>();
				if((isDisplayed(objWeeklyPlanningStoreObjects.PageTitle)) && (getText(objWeeklyPlanningStoreObjects.PageTitle).equalsIgnoreCase("Please Select Page...")))
					{
					int RowCount =TradingStatementTableCount(WeeklyPlanningStoreObjects.RegionDropdown);
					String[] Regions = null;
					for(int i=1;i<=RowCount-1;i++)
					{
						WebElement Data = prepareWebElementWithDynamicXpathWithInt(WeeklyPlanningStoreObjects.RegionDropdownValue, i);
						RegionNm.add(getText(Data));
						Object[] objRegions=RegionNm.toArray();
						Regions=Arrays.copyOf(objRegions, objRegions.length,String[].class);
					}
					return Regions;
				}	
				else
				{
					Report_AddStep("testcase","Failed to get the Values from the Dropdown" ,"","", "Fail");
					htmlToExtent(cName,mName,extentTest,driver1, "Failed to get the Values from the Dropdown ;;;Fail");
				}
			}
			catch(Exception e)
			{
				System.out.println("Exception Occured" +e.getMessage()); 
				Report_AddStep("testcase","Failed to get the Page names from the Dropdown" ,"","", "Fail");
				htmlToExtent(cName,mName,extentTest,driver1, "Exception occured while verifying Page name ;;;Fail");
			}
			return null;
		}
		
		//Sorting Region dropdown
		public void OrderVerification(String[] regions,String screen,ExtentTest extentTest) throws IOException 
		{
			int i=0;
			System.out.println(regions.length);
			for(String as=regions[i];i<regions.length-1;i++)
			{
				System.out.println(regions[i]);
				System.out.println(regions[i+1]);
				int var1=regions[i].compareTo(regions[i+1]);
				if(var1<=0)
				{
					System.out.println("Value is in ascending order: "+regions[i]+"");	
					extentTest.log(LogStatus.PASS, "Region name: "+regions[i]+" dropdown for "+screen+" screen were displayed in ascending order for "+screen+" Manager user");
					Report_AddStep("testcase","Region name: "+regions[i]+" dropdown for "+screen+" screen were displayed in ascending order for "+screen+" Manager user",regions[i],regions[i+1],"Pass");
				}
				else
				{
					System.out.println("The values are not in ascending order");
					extentTest.log(LogStatus.FAIL,"Region name: "+regions[i]+" dropdown for "+screen+" screen were NOT displayed in ascending order for "+screen+" Manager user");
					Report_AddStep("testcase","Region name: "+regions[i]+" dropdown for "+screen+" screen were displayed in ascending order for "+screen+" Manager user",regions[i],regions[i+1], "Fail");
					break;
				}	
			} 	
		}
		
//CLR-7, Daily Planning button availability
		
	//Verify page
	public void VerifyPageName(WeeklyPlanningStoreObjects objWeeklyPlanningStoreObjects,ExtentTest extentTest,String Page) throws Exception
	{
		SwitchFrame(objWeeklyPlanningStoreObjects.DataFrame);
		try
		{
			if(getInnerText(objWeeklyPlanningStoreObjects.ScreenName).equalsIgnoreCase(""+Page+": "))
			{	
				Report_AddStep("testcase","Navigated Successfully to "+Page+" Page" ,"",Page, "Pass");
				htmlToExtent(cName,mName,extentTest,driver1, "Navigated Successfully to "+Page+" Page ;;;Pass");
			}
			else
			{
				Report_AddStep("testcase","Failed to Navigate to "+Page+" Page" ,Page,"", "Fail");
				htmlToExtent(cName,mName,extentTest,driver1, "Failed to Navigate to "+Page+" Page ;;;Fail");
			}
		}
		catch(Exception e)
		{
			System.out.println("Exception Occured" +e.getMessage());
			Report_AddStep("testcase","Failed to Navigate to "+Page+" Page" ,"","", "Fail");
			htmlToExtent(cName,mName,extentTest,driver1, "Failed to Navigate to "+Page+" Page ;;;Fail");
		}
	}
				
	public void DailyPlanningButton(WebElement Frame,WebElement DailyPlanningButton,WebElement WeeklyPlanningButton,String DPDBtnName,String Page,String WPSBtnName, 
			ExtentTest extentTest) throws Exception
	{
		SwitchFrame(Frame);
		if(isDisplayed(DailyPlanningButton))
		{
			click(DailyPlanningButton);
			waitFor(2);
			getAttribute(WeeklyPlanningButton);
			if(getAttribute(WeeklyPlanningButton).equalsIgnoreCase(DPDBtnName))
			{
				Report_AddStep("testcase",""+WPSBtnName+" button is clicked Successfully","","", "Pass");
				htmlToExtent(cName,mName,extentTest,driver1, ""+WPSBtnName+" button is clicked Successfully ;;;Pass");
			}
			else
			{
				System.out.println("Page did not navigate");
				Report_AddStep("testcase","Failed to Click "+WPSBtnName+" button" ,"","", "Fail");
				htmlToExtent(cName,mName,extentTest,driver1, "Failed to Click "+WPSBtnName+" button ;;;Fail");
			}			
		}
		else
				System.out.println("Daily Planning button is unavailable");
	}

	private boolean isElementPresent(WebElement dailyPlanningButton) 
	{
		return false;
	}
	
	public void GoButton(WebElement GoButton,WebElement Frame) throws Exception 
	{
		click(GoButton);
	}
	
	
	//Compare UI and DB values for OR
			public void CompareValueDecimalORFields(ExtentTest extentTest,List<List<Object>> DBValues,List<List<Object>> UIValues) throws Exception
			{
				try
				{	
					int RowCount = DBValues.size();
					double CalcuatedUIValue = 0,CalcuatedDBValue=0,RoundOffValue = 0,RoundOffValue1=0;
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
								FinalDBValue =ConvertBigDecimalSingleDigit(CalcuatedDBValue);
								System.out.println(FinalDBValue);
								
								CalcuatedUIValue =new Double(UIValues.get(i).get(j).toString());
								RoundOffValue1 = Math.round(CalcuatedUIValue);
								FinalUIValue =ConvertBigDecimalSingleDigit(CalcuatedUIValue);
								System.out.println(FinalUIValue);
								BigDecimal biggy =FinalUIValue.subtract(FinalDBValue);
								double Variance = biggy.doubleValue();
								if(Variance <=0.1 && Variance >=-0.1)
								{
									Report_AddStep("testcase","System Fetch the Values from DB for : "+DBName+" Correctly" ,""+CalcuatedUIValue+"",""+CalcuatedDBValue+"", "Pass");
									htmlToExtent(cName,mName,extentTest,driver1, "Value displayed Correctly for : "+DBName+"; "+CalcuatedUIValue+";  "+CalcuatedDBValue+";Pass");
								}
								else
								{
									System.out.println(UIName+" "+DBName);
									Report_AddStep("testcase","Failed to Display the UI and DB Values Correctly "+DBName+" and value is "+CalcuatedUIValue+"",""+CalcuatedDBValue+"" ,"", "Fail");
									htmlToExtent(cName,mName,extentTest,driver1, "Failed to display data Correctly for : "+DBName+"; "+CalcuatedUIValue+";  "+CalcuatedDBValue+";Fail");
								}
							}
							else
							{
								DBName =new String(DBValues.get(i).get(j).toString());
								UIName =new String(UIValues.get(i).get(j).toString());
								System.out.println(UIName+" "+DBName);
								if((DBName.trim()).equalsIgnoreCase(UIName.trim()))
								{
									Report_AddStep("testcase","Column Name are matching with UI and DB : "+DBName+" and Displayed Correctly" ,"","", "Pass");
									htmlToExtent(cName,mName,extentTest,driver1, "Column Name are matching with UI and DB : "+DBName+" and Displayed Correctly ;;;Pass");
								}
								else
								{
									Report_AddStep("testcase","Failed to Display Column Name in UI and DB Values Correctly "+DBName+"","" ,"", "Fail");	
									htmlToExtent(cName,mName,extentTest,driver1, "Failed to Display Column Name in UI and DB Values Correctly "+DBName+" ;;;Fail");							
								}
							}
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
			
		public void SwitchToFrame(WebElement Frame) throws Exception
		{
			SwitchFrame(Frame);		
		}
		
		//Percentage Calculations
				public double PlannedSalestoBDGTRateCombined(String DepartmentNames,ExtentTest extentTest,String FieldToVerify,String Bdgtwgprcnt,String Plndsls,String Bdgtsls,String PlndWgs,
						String Bdgtwg,List<List<Object>> PositiveFactor,List<List<Object>> NegativeFactor) throws Exception
				{
					int i = 0;
					String Result;
					double RoundAcceptableDiff,Bdgtwgprcntge,Plannedsales,Budgetsales,PlannedWages,FinalBDGT,BDGTRate=0.0,CalculatedValue=0.0,
							FinalTotalSum=0.0,PositiveRateFctr,NegativeRateFctr,finalBdgtWgPrcnt,bgdtwgprcnt,BudgetWage;
					String Value,Value1,Value2,Value3,Value4,CalcValue,UIValue,Dept = null,CalVal,Value5;
					BigDecimal ValFinal = null;
					RoundAcceptableDiff = 0.01;
					ValFinal =ConvertBigDecimal(RoundAcceptableDiff);
					
					try
					{	
							WebElement Department = prepareWebElementWithDynamicXpathWithInt(DepartmentNames, i);
							WebElement xpath1 = prepareWebElementWithDynamicXpathWithInt(Bdgtwgprcnt, i);
							WebElement xpath2 = prepareWebElementWithDynamicXpathWithInt(Plndsls, i);
							WebElement xpath3 = prepareWebElementWithDynamicXpathWithInt(Bdgtsls, i);
							WebElement xpath4 = prepareWebElementWithDynamicXpathWithInt(PlndWgs, i);
							WebElement xpath5 = prepareWebElementWithDynamicXpathWithInt(Bdgtwg, i);
							WebElement CalculatedXpath = prepareWebElementWithDynamicXpathWithInt(FieldToVerify, i);
							Dept = getText(Department);
							Value1 = getText(xpath1);
							Value2 = getText(xpath2);
							Value3 = getText(xpath3);
							Value4 = getText(xpath4);
							Value5 = getText(xpath5);
							Value = getText(CalculatedXpath);
							Bdgtwgprcntge=TrimData(Value1);
							Plannedsales=TrimData(Value2);
							Budgetsales=TrimData(Value3);
							PlannedWages=TrimData(Value4);
							BudgetWage=TrimData(Value5);
							//conversion of 5 decimal for Budget wage Percentage
							bgdtwgprcnt = (BudgetWage/Budgetsales);
							CalVal = String.format("%.5g%n", bgdtwgprcnt);
							finalBdgtWgPrcnt = Double.parseDouble(CalVal);
							System.out.println(finalBdgtWgPrcnt);
							
							//Rate Factor Conversion
							PositiveRateFctr =new Double(PositiveFactor.get(0).get(0).toString());
							NegativeRateFctr =new Double(NegativeFactor.get(0).get(0).toString());
							
							BDGTRate=TrimData(Value);
							System.out.println(BDGTRate+" "+Value);
							BigDecimal Val =ConvertBigDecimalNbr(BDGTRate);
							if(Plannedsales>Budgetsales)
							{
								CalculatedValue = (PlannedWages - ((((PositiveRateFctr * finalBdgtWgPrcnt) * (Plannedsales - Budgetsales)) + BudgetWage)));
								BigDecimal Val1 =ConvertBigDecimalNbr(CalculatedValue);
								Result = (Val.subtract(Val1).toString());
								boolean Final1 =Result.equals("-1");
		 						boolean Final = Result.equals("1");
								if(Val.equals(Val1) || Final==true || Final1==true )
								{
									System.out.println(Val+"  "+Val1);
									Report_AddStep("testcase","Calculated Value for Department "+Dept+" is displayed successfully" ,""+Val+"",""+Val1+"", "Pass");
									htmlToExtent(cName,mName,extentTest,driver1, "Value displayed Correctly for : "+Dept+"; "+Val+";  "+Val1+";Pass");
								}
								else
								{
									System.out.println(Val+"  "+Val1);
									Report_AddStep("testcase","Failed to Display Value Correctly for Department "+Dept+" :"+Val1+"" ,"","", "Fail");
									htmlToExtent(cName,mName,extentTest,driver1, "Failed to display data Correctly for : "+Dept+"; "+Val+";  "+Val1+";Fail");
								}
							}else if(Plannedsales<=Budgetsales)
							{
								CalculatedValue = (PlannedWages - ((((NegativeRateFctr * finalBdgtWgPrcnt) * (Plannedsales - Budgetsales)) + BudgetWage)));
								BigDecimal Val1 =ConvertBigDecimalNbr(CalculatedValue);
								Result = (Val.subtract(Val1).toString());
								boolean Final1 =Result.equals("-1");
		 						boolean Final = Result.equals("1");
								if(Val.equals(Val1) || Final==true || Final1==true )
								{
									System.out.println(Val+"  "+Val1);
									Report_AddStep("testcase","Calculated Value for Department "+Dept+" is displayed successfully" ,""+Val+"",""+Val1+"", "Pass");
									htmlToExtent(cName,mName,extentTest,driver1, "Value displayed Correctly for : "+Dept+"; "+Val+";  "+Val1+";Pass");
								}
								else
								{
									System.out.println(Val+"  "+Val1);
									Report_AddStep("testcase","Failed to Display Value Correctly for Department "+Dept+" :"+Val1+"" ,"","", "Fail");
									htmlToExtent(cName,mName,extentTest,driver1, "Failed to display data Correctly for : "+Dept+"; "+Val+";  "+Val1+";Fail");
								}
							}
							else if(Plannedsales==Budgetsales)
							{
								CalculatedValue = (PlannedWages - BudgetWage);
								BigDecimal Val1 =ConvertBigDecimalNbr(CalculatedValue);
								Result = (Val.subtract(Val1).toString());
								boolean Final1 =Result.equals("-1");
		 						boolean Final = Result.equals("1");
								if(Val.equals(Val1) || Final==true || Final1==true )
								{
									System.out.println(Val+"  "+Val1);
									Report_AddStep("testcase","Calculated Value for Department "+Dept+" is displayed successfully" ,""+Val+"",""+Val1+"", "Pass");
									htmlToExtent(cName,mName,extentTest,driver1, "Value displayed Correctly for : "+Dept+"; "+Val+";  "+Val1+";Pass");
								}
								else
								{
									System.out.println(Val+"  "+Val1);
									Report_AddStep("testcase","Failed to Display Value Correctly for Department "+Dept+" :"+Val1+"" ,"","", "Fail");
									htmlToExtent(cName,mName,extentTest,driver1, "Failed to display data Correctly for : "+Dept+"; "+Val+";  "+Val1+";Fail");
								}
							}	
					}
					catch(Exception e)
					{
						System.out.println("Exception Occured" +e.getMessage());
						Report_AddStep("testcase","Failed to Fetch Department Total Value" ,"","", "Fail");
						htmlToExtent(cName,mName,extentTest,driver1, "Exception Occured while comparing data;;;Fail");
					}
					return FinalTotalSum;	
				}
				
// Get total value in WP Store screen				
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
					WebElement RwNm = prepareWebElementWithDynamicXpathWithInt(RowName,rowcount-5);
					Dept = RwNm.getText();
					WebElement Element = prepareWebElementWithDynamicXpathWithInt(FieldToVerify,rowcount-5);
					Data = getText(Element);
					CalcValue=TrimData(Data);
					FinalUIValue =ConvertBigDecimal(CalcValue);
					row.add(Dept);
					row.add(FinalUIValue);	
					UIValues.add(row);
				return UIValues;
		}
		
		//Select Department Filter for Sales History Calendar screen
		public void SelectDepartmentFilter(SalesHistoryCalendarObjects objSalesHistoryCalendarobjects,ExtentTest extentTest,String Department) throws Exception
		{
			try
			{	 			
				if((getText(objSalesHistoryCalendarobjects.DepartmentHeader).contains("Department")))
				{
					selectByValue(objSalesHistoryCalendarobjects.DepartmentDropdown,Department);
					Report_AddStep("testcase","Department To Display is Selected Successfully from the Dropdown" ,"","", "Pass");
					htmlToExtent(cName,mName,extentTest,driver1, ""+Department+" is Selected Successfully from department drop down ;;;Pass");
				}
				else
				{
					Report_AddStep("testcase","Failed to Select Department Option from DropDown" ,"","", "Fail");
					htmlToExtent(cName,mName,extentTest,driver1, "Failed to select "+Department+" from department drop down ;;;Fail");
				}
			}
			catch(Exception e)
			{
				System.out.println("Exception Occured" +e.getMessage());
				Report_AddStep("testcase","Failed to Select History Option from DropDown" ,"","", "Fail");
				htmlToExtent(cName,mName,extentTest,driver1, "Exception occured while selecting department ;;;Fail");
			}
		}
		
		//New Table UI Value
		public List<List<Object>> WPNewTable_GetUIValue(ExtentTest extentTest,String FieldToVerify,String Week,String Table)
		{

				String Dept,Values=null;
				double CalcValue;
				BigDecimal FinalUIValue = null;
				int columnsize=2;
				List<List<Object>> UIValues= new  ArrayList<List<Object>>();
					
				int RowCount =TradingStatementTableCount(Table);	
				for(int i=0;i<=RowCount-2;i++)
				{
						List<Object> row = new ArrayList<>(columnsize);
						WebElement Department = prepareWebElementWithDynamicXpathWithInt(Week, i);
						WebElement Data = prepareWebElementWithDynamicXpathWithInt(FieldToVerify, i);	
						Dept = getText(Department);
						Values = getText(Data);
						CalcValue=TrimData(Values);
						row.add(Dept);
						row.add(CalcValue);	
						UIValues.add(row);
				}
				return UIValues;
		}
		
		//New Table Total
		public List<List<Object>> WPNewTable_GetTotal(ExtentTest extentTest,String RowName,String FieldToVerify,String Table)
		{
			String Dept,Data=null;
			double CalcValue;
			BigDecimal FinalUIValue=null;
			int columnsize=2;
			int rowcount= TradingStatementTableCount(Table);
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
		
		//
		public void verifyDisplayedMessage(String Title, ExtentTest extentTest, String Message, String ReportName)
				throws IOException {
			boolean Result = false;
			try {
				WebElement PgName = prepareWebElementWithDynamicXpathWithInt(Title);
				if (isDisplayed(PgName)) {
					System.out.println(getText(PgName));
					Result = getText(PgName).trim().equalsIgnoreCase(Message);
					if (Result == true) {
						htmlToExtent(cName,mName,extentTest,driver1, ""+Message+" message is displayed successfully ;;;Pass");
					}
				} else {
					htmlToExtent(cName,mName,extentTest,driver1, "Failed to display "+Message+" message ;;;Fail");
				}
			} catch (Exception e) {
				System.out.println("Exception occured:Failed to Verify Page Title " + ReportName + "" + e.getMessage());
				htmlToExtent(cName,mName,extentTest,driver1, "Exception Occured - Failed to display "+Message+" message ;;;Fail");
			}
		}
		public void verifyDisplayedMessageForTimeandDate(String Title, ExtentTest extentTest, String Message, String ReportName)
				throws IOException {
			boolean Result = false;
			try {
				WebElement PgName = prepareWebElementWithDynamicXpathWithInt(Title);
				if (isDisplayed(PgName)) {
					String value=getText(PgName);
					System.out.println(value);
					
					Result = getText(PgName).trim().equalsIgnoreCase(Message);
					if (Result == false) {
						htmlToExtent(cName,mName,extentTest,driver1, ""+Message+" message is displayed successfully ;;;Pass");
					}
				} else {
					htmlToExtent(cName,mName,extentTest,driver1, "Failed to display "+Message+" message ;;;Fail");
				}
			} catch (Exception e) {
				System.out.println("Exception occured:Failed to Verify Page Title " + ReportName + "" + e.getMessage());
				htmlToExtent(cName,mName,extentTest,driver1, "Exception Occured - Failed to display "+Message+" message ;;;Fail");
			}
		}
		public void enterValue(String textBoxXpath, ExtentTest extentTest, String textBoxValue, String reportName) {
			try {
				
				WebElement textBoxElement = prepareWebElementWithDynamicXpathWithInt(textBoxXpath);
				if (isDisplayed(textBoxElement)) {
					click(textBoxElement);
					enterText(textBoxElement, textBoxValue);
					boolean result = getAttribute(textBoxElement).contains(textBoxValue);
					if (result == true) {
						htmlToExtent(cName,mName,extentTest,driver1, "Successfully Entered "+textBoxValue+" into "+reportName+" text Box ;;;Pass");
					}
					else {
						htmlToExtent(cName,mName,extentTest,driver1, "Failed to Enter "+textBoxValue+" into "+reportName+" text Box ;;;Fail");
					}

				} else {
					htmlToExtent(cName,mName,extentTest,driver1, "Failed to Enter "+textBoxValue+" into "+reportName+" text Box ;;;Fail");
				}
			} catch (Exception e) {
				System.out.println("Exception occured while entering value into " + reportName + "" + e.getMessage());
				htmlToExtent(cName,mName,extentTest,driver1, "Exception Occured While Entering value in Text box ;;;Fail");
			}
		}	
		
		public void clickButton(String buttonXpath, ExtentTest extentTest, String ReportName) {
			String buttonName = null;
			try {
				
				WebElement buttonElement = prepareWebElementWithDynamicXpathWithInt(buttonXpath);
				if (isDisplayed(buttonElement)) {
					buttonName = getText(buttonElement);
					if (ReportName.equalsIgnoreCase("Button")
							|| ReportName.equalsIgnoreCase(" Button")
							|| ReportName.equalsIgnoreCase("Continue") || ReportName.equalsIgnoreCase("Cancel")
							|| ReportName.equalsIgnoreCase("Next ")
							|| ReportName.equalsIgnoreCase(" Button")
							|| ReportName.equalsIgnoreCase("Add"))
						scrolltoElement(buttonElement);
					click(buttonElement);
					htmlToExtent(cName,mName,extentTest,driver1, "Successfully clicked the button: "+buttonName+" ;;;Pass");
				} else {
					htmlToExtent(cName,mName,extentTest,driver1, "Button is not displayed in UI: " + buttonName + " ;;;Fail");
				}
			} catch (Exception e) {
				System.out.println("Exception Occured while clicking the Button:" + e.getMessage());
				htmlToExtent(cName,mName,extentTest,driver1, "Exception Occured while clicking the Button ;;;Fail");
			}
		}
		
		
		public void clickButtonTwo(String buttonXpath, ExtentTest extentTest, String ReportName) {
			String buttonName = null;
			try {
				
				WebElement buttonElement = prepareWebElementWithDynamicXpathWithInt(buttonXpath);
				if (isDisplayed(buttonElement)) {
					
					click(buttonElement);
					htmlToExtent(cName,mName,extentTest,driver1, "Successfully clicked the button: "+buttonName+" ;;;Pass");
				} else {
					htmlToExtent(cName,mName,extentTest,driver1, "Button is not displayed in UI: " + buttonName + " ;;;Fail");
				}
			} catch (Exception e) {
				htmlToExtent(cName,mName,extentTest,driver1, "Exception Occured while clicking the Button ;;;Fail");
			}
		}
		
		/**
		 * To Select Page from Menu Bar
		 * @param extentTest
		 * @param PageTitle
		 * @param menuBtn
		 * @param reportName
		 * @throws Exception
		 */
		public void selectPage(ExtentTest extentTest,WebElement PageTitle,String menuBtn,String reportName  ) throws Exception
		{
			String buttonName;
			try
			{
				if((isDisplayed(PageTitle)))
				{
					WebElement elemen =prepareWebElementWithDynamicXpathWithString(menuBtn,reportName);
					buttonName = getText(elemen);
					//if (buttonName.equalsIgnoreCase(reportName))
					//{
						click(elemen);
						//htmlToExtent(cName,mName,extentTest,driver, "Successfully clicked the button: "+reportName+" ;;;Pass");
					//}
				}
			}
			catch(Exception e)
			{
				System.out.println("Exception Occured" +e.getMessage()); 
				//htmlToExtent(cName,mName,extentTest,driver, "Exception Occured - Failed to Select Page from Menu Bar ;;;Fail");
			}
		}
		
		/**
		 * Verify Page Name
		 * @param extentTest
		 * @param titleFrame
		 * @param page
		 * @param ReportName
		 * @throws Exception
		 */
		public void verifyPageName(ExtentTest extentTest,WebElement titleFrame,WebElement page,String ReportName) throws Exception
		{
			try
			{  
				String name=getText(page);
				String[] arr = name.split(":");
				if(arr[0].equalsIgnoreCase(ReportName))
				{
					htmlToExtent(cName,mName,extentTest,driver1, "Page: "+ReportName+" is displayed Successfully in screen ;;;Pass");
				}
				else
				{
					htmlToExtent(cName,mName,extentTest,driver1, "Failed to Display Page Name Correctly ;;;Fail");
				}
			}
			catch(Exception e)
			{
				System.out.println("Exception Occured while Verifying Page Name" +e.getMessage());
				htmlToExtent(cName,mName,extentTest,driver1, "Exception Occured - Failed to Display Page Name Correctly ;;;Fail");
			}
		}
		
		public boolean iselementDisplayed(WebElement element) {

			boolean isElementDisplayed = false;
			String elementname = getElementLocator(element);
			try {
				isElementDisplayed = element.isDisplayed();
				if (isElementDisplayed) {
					String name=getText(element);
					String[] arr = name.split(":");
					
					System.out.println("Element: " + elementname + " is visible");
					
				} else
					getLogger().warn("Element: " + elementname + " is not visible");
			} catch (Exception e) {
				try {
					throw new CustomException(e, getLogger(), elementname);
				} catch (CustomException e1) {
					getLogger().error("Error in checking the visibility of the element: " + elementname);
				}
			}
			return isElementDisplayed;
		}

}
