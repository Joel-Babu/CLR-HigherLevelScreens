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

public class MonthlyPlanningStorePage extends TestBase
{
	CommonFunctions common = PageFactory.initElements(this.getDriver(),CommonFunctions.class);
	public Logger log = LogManager.getLogger(MonthlyPlanningStorePage.class);
	SQLWrapper sql = new SQLWrapper(log);
	WeeklyPlanningStoreObjects objWeeklyPlanningStoreObjects;
	MonthlyPlanningStoreObjects objMonthlyPlanningStoreObjects;
	MonthlyPlanningGroupObjects objMonthlyPlanningGroupObjects;
	static ExtentReportsScreenshotCode ScreenShotUtil; 
	static WebDriverWait wait;
	String image;
	public MonthlyPlanningStorePage()
	{
		// TODO Auto-generated constructor stub
		super();
	}
	
	//Get UI Value to Compare with DB Value
			public List<List<Object>> UIData(WebElement Week,ExtentTest extentTest,String FieldToVerify)
			{
				int RowCount;
				String Dept,Values=null;
				double CalcValue;
				int columnsize=2;
				List<List<Object>> UIValues= new  ArrayList<List<Object>>();	
				String WeekValue = getText(Week);
				if(WeekValue.trim().isEmpty() || WeekValue.contains("null"))
					RowCount=5;
				else
					RowCount=6;
				for(int i=1;i<=RowCount-1;i++)
				{
					List<Object> row = new ArrayList<>(columnsize);
					WebElement Department = prepareWebElementWithDynamicXpathWithInt(objWeeklyPlanningStoreObjects.DepartmentNames, i);
					WebElement Data = prepareWebElementWithDynamicXpathWithInt(FieldToVerify, i);	
					Values = Data.getAttribute("value");
					Dept = getText(Department);
					CalcValue=TrimData(Values);
					row.add(Dept);
					row.add(CalcValue);
					UIValues.add(row);
				}
				return UIValues;
			}
			
			
		//Verify Store Total - Wages and Paid Hours
		public double GetFinalTotalValue(WebElement Week,ExtentTest extentTest,String FieldToVerify) throws Exception
		{
			int i,RowCount;
			double Data,FinalTotal=0.0,FinalTotalSum=0.0;
			String Value = null;
			try
			{	
				String WeekValue = getText(Week);
				if(WeekValue.trim().isEmpty() || WeekValue.contains(" ")|| WeekValue.contains("null"))
					RowCount=5;
				else
					RowCount=6;
				for(i=1;i<=RowCount-1;i++)
				{
					WebElement Element = prepareWebElementWithDynamicXpathWithInt(FieldToVerify, i);
					Value = Element.getAttribute("value");
					Data=TrimData(Value);
					FinalTotal = FinalTotal+Data;
					FinalTotalSum = Math.round(FinalTotal*100)/100D;
				}
				Report_AddStep("testcase","Summation of All the Month Value is :"+FinalTotal+" displayed successfully" ,"","", "Pass");
				htmlToExtent(cName,mName,extentTest,driver1, "Summation of All the Month Value is :"+FinalTotal+" displayed successfully ;;;Pass");
				return FinalTotalSum;
			}
			catch(Exception e)
			{
				System.out.println("Exception Occured" +e.getMessage());
				Report_AddStep("testcase","Failed to Fetch Month Total Value" ,"","", "Fail");
				htmlToExtent(cName,mName,extentTest,driver1, "Failed to Fetch Month Total Value ;;;Fail");
			}
			return FinalTotalSum;	
		}
	
	//Compare UI and DB values
	public void CompareValues(ExtentTest extentTest,List<List<Object>> DBValues,List<List<Object>> UIValues) throws Exception
	{
		try
		{	
			int RowCount = DBValues.size();
			String WeekName=null;
			double CalcuatedUIValue = 0,CalcuatedDBValue=0,RoundOffValue = 0,RoundOffValue1=0;
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
						System.out.println("FinalDBValue"+FinalDBValue);
						
						CalcuatedUIValue =new Double(UIValues.get(i).get(j).toString());
						RoundOffValue1 = Math.round(CalcuatedUIValue);
						FinalUIValue =ConvertBigDecimal(CalcuatedUIValue);
						System.out.println("FinalUIValue"+FinalUIValue);
						BigDecimal biggy =FinalUIValue.subtract(FinalDBValue);
						int Variance = biggy.intValue();
						if(Variance <=2 && Variance >=-2)
						{
							Report_AddStep("testcase","Calculated DB values are displayed Correctly in UI for: "+WeekName+"" ,""+FinalUIValue+"",""+FinalDBValue+"", "Pass");
							htmlToExtent(cName,mName,extentTest,driver1, "Value displayed Correctly for : "+WeekName+";"+FinalUIValue+";"+FinalDBValue+";Pass");
						}
						else
						{
							Report_AddStep("testcase","Calculated DB values are Not Matching for: "+WeekName+"" ,""+FinalUIValue+"",""+FinalDBValue+"", "Fail");
							htmlToExtent(cName,mName,extentTest,driver1, "Failed to display data Correctly for : "+WeekName+";"+FinalUIValue+";"+FinalDBValue+";Fail");
						}
					}
					else
					{
						WeekName =new String(UIValues.get(i).get(j).toString());
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

		//Forecast Calendar 
		public void CompareValuesFC(ExtentTest extentTest,List<List<Object>> DBValues,List<List<Object>> UIValues) throws Exception
		{
			try
			{	
				int RowCount = DBValues.size();
				boolean result=false;
				String Screen = "Zone",Name = null,WeekName="Data",DBName=null;
				double CalcuatedUIValue = 0,CalcuatedDBValue=0,RoundOffValue = 0,RoundOffValue1=0;
				BigDecimal FinalDBValue = null,FinalUIValue=null;
				System.out.println(UIValues);
				System.out.println(DBValues);
			
				for(int i=0;i<=RowCount-1;i++)
				{
					for(int j=0;j<2;j++)
					{
						if(j==0)
						{		
							CalcuatedDBValue =new Double(DBValues.get(i).get(j).toString());
							RoundOffValue = Math.round(CalcuatedDBValue);	
							FinalDBValue =ConvertBigDecimal(CalcuatedDBValue);
							System.out.println("FinalDBValue"+FinalDBValue);
							
							CalcuatedUIValue =new Double(UIValues.get(i).get(j).toString());
							RoundOffValue1 = Math.round(CalcuatedUIValue);
							FinalUIValue =ConvertBigDecimal(CalcuatedUIValue);
							System.out.println("FinalUIValue"+FinalUIValue);
							BigDecimal biggy =FinalUIValue.subtract(FinalDBValue);
							int Variance = biggy.intValue();
							if(Variance <=2 && Variance >=-2)
							{
								Report_AddStep("testcase","Calculated DB and UI values are displayed Correctly" ,""+FinalUIValue+"",""+FinalDBValue+"", "Pass");
								htmlToExtent(cName,mName,extentTest,driver1, "Value displayed Correctly for : "+WeekName+";"+CalcuatedUIValue+";"+CalcuatedDBValue+";Pass");
							}
							else
							{
								Report_AddStep("testcase","Calculated DB values are Not Matching for: "+WeekName+"" ,""+FinalUIValue+"",""+FinalDBValue+"", "Fail");
								htmlToExtent(cName,mName,extentTest,driver1, "Failed to display data Correctly for : "+WeekName+";"+CalcuatedUIValue+";"+CalcuatedDBValue+";Fail");
							}
						}
						else
						{
							WeekName =new String(UIValues.get(j).toString());
							
							DBName =new String(DBValues.get(j).toString());
							String[] Nm =DBName.split("");	
							if(Screen.contains("Zone"))
								Name="G0".concat(Nm[1]).concat(Nm[2]);
							if(Screen.contains("State"))
								Name="Z0".concat(Nm[1]).concat(Nm[2]);
							if(Screen.contains("BU"))
								Name="Region ".concat(Nm[1]).concat(Nm[2]);
							else
								Name=DBName;
							if((WeekName.trim()).contains(Name.trim()))
								result=true;
							else
							{
								result=false;
								System.out.println(Name+" "+WeekName);
								break;
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
			
	
	//Planned Sales to BDGT Rate Percentage Calculations
		public double PlannedSalestoBDGTRate(String Input,MonthlyPlanningStoreObjects objMonthlyPlanningStoreObjects,ExtentTest extentTest,String WeekName,String FieldToVerify,String Bdgtwgprcnt,String Plndsls,String Bdgtsls,String PlndWgs) throws Exception
		{
			int i,RowCount;
			double Bdgtwgprcntge,Plannedsales,Budgetsales,PlannedWages,BDGTRate=0.0,CalculatedValue=0.0,FinalTotalSum=0.0;
			String Value,Value1,Value2,Value3,Value4,CalcValue,UIValue,WeekName1,Result = null;
			try
			{	
				String WeekValue = getText(objMonthlyPlanningStoreObjects.FifthWeek);
				if(Input.equalsIgnoreCase("FinalTotal"))
				{
					RowCount=2;
				}
				else
				{
					if(WeekValue.trim().isEmpty() || WeekValue.contains(" ")|| WeekValue.contains("null"))
						RowCount=5;
					else
						RowCount=6;
				}
				for(i=1;i<=RowCount-1;i++)
				{
					WebElement Week = prepareWebElementWithDynamicXpathWithInt(WeekName, i);
					WebElement xpath1 = prepareWebElementWithDynamicXpathWithInt(Bdgtwgprcnt, i);
					WebElement xpath2 = prepareWebElementWithDynamicXpathWithInt(Plndsls, i);
					WebElement xpath3 = prepareWebElementWithDynamicXpathWithInt(Bdgtsls, i);
					WebElement xpath4 = prepareWebElementWithDynamicXpathWithInt(PlndWgs, i);
					WebElement CalculatedXpath = prepareWebElementWithDynamicXpathWithInt(FieldToVerify, i);
					WeekName1 = getText(Week);
					Value1 = getText(xpath1);
					if(Input.equalsIgnoreCase("FinalTotal"))
					{
						Value2 = getText(xpath2);
						Value4 = getText(xpath4);
					}
					else
					{
						Value2 = xpath2.getAttribute("value");
						Value4 = xpath4.getAttribute("value");
					}
					Value3 = getText(xpath3);
					Value = getText(CalculatedXpath);
					Bdgtwgprcntge=TrimData(Value1);
					Plannedsales=TrimData(Value2);
					Budgetsales=TrimData(Value3);
					PlannedWages=TrimData(Value4);
					BDGTRate=TrimData(Value);
					System.out.println(BDGTRate+" "+Value);
					BigDecimal Val =ConvertBigDecimalNbr(BDGTRate);
					if(Plannedsales>Budgetsales)
					{
						CalculatedValue = PlannedWages - ((((0.5 * Bdgtwgprcntge) * (Plannedsales - Budgetsales))+(Bdgtwgprcntge * Budgetsales))/100) ;
						BigDecimal Val1 =ConvertBigDecimalNbr(CalculatedValue);
						Result = (Val.subtract(Val1).toString());
						boolean Final1 =Result.equals("-1");
						boolean Final = Result.equals("1");
						if(Val.equals(Val1) || Final==true || Final1==true )
						{
							System.out.println(Val+"  "+Val1);
							Report_AddStep("testcase","Calculated Value  is displayed correctly for "+WeekName1+" as" ,""+Val+"",""+Val1+"", "Pass");
							htmlToExtent(cName,mName,extentTest,driver1, "Value displayed Correctly for : "+WeekName1+";"+Val+";"+Val1+";Pass");
						}
						else
						{
							System.out.println(Val+"  "+Val1);
							Report_AddStep("testcase","Failed to Display Value Correctly for "+WeekName1+" :" ,""+Val+"",""+Val1+"", "Fail");
							htmlToExtent(cName,mName,extentTest,driver1, "Failed to display data Correctly for : "+WeekName1+";"+Val+";"+Val1+";Fail");
						}
					}else if(Plannedsales<=Budgetsales)
					{
						CalculatedValue = PlannedWages - (Bdgtwgprcntge * Plannedsales / 100);
						BigDecimal Val1 =ConvertBigDecimalNbr(CalculatedValue);
						Result = (Val.subtract(Val1).toString());
						boolean Final1 =Result.equals("-1");
						boolean Final = Result.equals("1");
						if(Val.equals(Val1) || Final==true || Final1==true )
							{
								System.out.println(Val+"  "+Val1);
								Report_AddStep("testcase","Calculated Value  is displayed correctly for "+WeekName1+" as" ,""+Val+"",""+Val1+"", "Pass");
								htmlToExtent(cName,mName,extentTest,driver1, "Value displayed Correctly for : "+WeekName1+";"+Val+";"+Val1+";Pass");
							}
							else
							{
								System.out.println(Val+"  "+Val1);
								Report_AddStep("testcase","Failed to Display Value Correctly for "+WeekName1+" :" ,""+Val+"",""+Val1+"", "Fail");
								htmlToExtent(cName,mName,extentTest,driver1, "Failed to display data Correctly for : "+WeekName1+";"+Val+";"+Val1+";Fail");
							}
						}	
					}	
				}
				catch(Exception e)
				{
					System.out.println("Exception Occured" +e.getMessage());
					Report_AddStep("testcase","Failed to Fetch Week Total Value" ,"","", "Fail");
					htmlToExtent(cName,mName,extentTest,driver1, "Exception Occured while comparing data;;;Fail");
				}
				return FinalTotalSum;	
			}	

	//For PST 916
	public double PlannedSalestoBDGTRateforStore(String Input,MonthlyPlanningStoreObjects objMonthlyPlanningStoreObjects,ExtentTest extentTest,String WeekName,String FieldToVerify,String Bdgtwgprcnt,
			String Plndsls,String Bdgtsls,String PlndWgs,String Bdgtwg,List<List<Object>> PositiveFactor,List<List<Object>> NegativeFactor,List<List<Object>> PositiveFactorTtl,List<List<Object>> NegativeFactorTtl) throws Exception
	{
		int i,RowCount;
		double Bdgtwgprcntge,finalBdgtWgPrcnt,bgdtwgprcnt,BudgetWage,Plannedsales,Budgetsales,PlannedWages,NegativeRateFctr,PositiveRateFctr,BDGTRate=0.0,CalculatedValue=0.0,FinalTotalSum=0.0;
		String Value,Value1,Value2,Value3,Value4,Value5,CalcValue,CalVal,UIValue,WeekName1,Result = null;
		try
		{	
			String WeekValue = getText(objMonthlyPlanningStoreObjects.FifthWeek);
			if(Input.equalsIgnoreCase("FinalTotal"))
			{
				RowCount=2;
			}
			else
			{
				if(WeekValue.trim().isEmpty() || WeekValue.contains(" ")|| WeekValue.contains("null"))
					RowCount=5;
				else
					RowCount=6;
			}
			for(i=1;i<=RowCount-1;i++)
			{
				
				WebElement Week = prepareWebElementWithDynamicXpathWithInt(WeekName, i);
				WebElement xpath1 = prepareWebElementWithDynamicXpathWithInt(Bdgtwgprcnt, i);
				WebElement xpath2 = prepareWebElementWithDynamicXpathWithInt(Plndsls, i);
				WebElement xpath3 = prepareWebElementWithDynamicXpathWithInt(Bdgtsls, i);
				WebElement xpath4 = prepareWebElementWithDynamicXpathWithInt(PlndWgs, i);
				WebElement xpath5 = prepareWebElementWithDynamicXpathWithInt(Bdgtwg, i);
				WebElement CalculatedXpath = prepareWebElementWithDynamicXpathWithInt(FieldToVerify, i);
				WeekName1 = getText(Week);
				Value1 = getText(xpath1);
				if(Input.equalsIgnoreCase("FinalTotal"))
				{
					Value2 = getText(xpath2);
					Value4 = getText(xpath4);
					PositiveRateFctr =new Double(PositiveFactorTtl.get(0).get(0).toString());
					NegativeRateFctr =new Double(NegativeFactorTtl.get(0).get(0).toString());
					
				}
				else
				{
					Value2 = xpath2.getAttribute("value");
					Value4 = xpath4.getAttribute("value");
					PositiveRateFctr =new Double(PositiveFactor.get(i-1).get(0).toString());
					NegativeRateFctr =new Double(NegativeFactor.get(i-1).get(0).toString());
				}
				Value3 = getText(xpath3);
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
				BigDecimal Val =ConvertBigDecimalNbr(BDGTRate);
				
						
				if(Plannedsales>Budgetsales)
				{
					CalculatedValue = (PlannedWages - ((((PositiveRateFctr * finalBdgtWgPrcnt) * (Plannedsales - Budgetsales)) + BudgetWage)));
					System.out.println(CalculatedValue);
					BigDecimal Val1 =ConvertBigDecimalNbr(CalculatedValue);
					Result = (Val.subtract(Val1).toString());
					boolean Final1 =Result.equals("-1");
					boolean Final = Result.equals("1");
					if(Val.equals(Val1) || Final==true || Final1==true )
					{
						System.out.println(Val+"  "+Val1);
						Report_AddStep("testcase","Calculated Value  is displayed correctly for "+WeekName1+" as" ,""+Val+"",""+Val1+"", "Pass");
						htmlToExtent(cName,mName,extentTest,driver1, "Value displayed Correctly for : "+WeekName1+";"+Val+";"+Val1+";Pass");
					}
					else
					{
						System.out.println(Val+"  "+Val1);
						Report_AddStep("testcase","Failed to Display Value Correctly for "+WeekName1+" :" ,""+Val+"",""+Val1+"", "Fail");
						htmlToExtent(cName,mName,extentTest,driver1, "Failed to display data Correctly for : "+WeekName1+";"+Val+";"+Val1+";Fail");
					}
				}
				else if(Plannedsales<Budgetsales)
				{
					CalculatedValue = (PlannedWages - ((((NegativeRateFctr * finalBdgtWgPrcnt) * (Plannedsales - Budgetsales)) + BudgetWage)));
					BigDecimal Val1 =ConvertBigDecimalNbr(CalculatedValue);
					Result = (Val.subtract(Val1).toString());
					boolean Final1 =Result.equals("-1");
					boolean Final = Result.equals("1"); 
					if(Val.equals(Val1) || Final==true || Final1==true )
						{
							System.out.println(Val+"  "+Val1);
							Report_AddStep("testcase","Calculated Value  is displayed correctly for "+WeekName1+" as" ,""+Val+"",""+Val1+"", "Pass");
							htmlToExtent(cName,mName,extentTest,driver1, "Value displayed Correctly for : "+WeekName1+";"+Val+";"+Val1+";Pass");
						}
						else
						{
							System.out.println(Val+"  "+Val1);
							Report_AddStep("testcase","Failed to Display Value Correctly for "+WeekName1+" :" ,""+Val+"",""+Val1+"", "Fail");
							htmlToExtent(cName,mName,extentTest,driver1, "Failed to display data Correctly for : "+WeekName1+";"+Val+";"+Val1+";Fail");
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
							Report_AddStep("testcase","Calculated Value  is displayed correctly for "+WeekName1+" as" ,""+Val+"",""+Val1+"", "Pass");
							htmlToExtent(cName,mName,extentTest,driver1, "Value displayed Correctly for : "+WeekName1+";"+Val+";"+Val1+";Pass");
						}
						else
						{
							System.out.println(Val+"  "+Val1);
							Report_AddStep("testcase","Failed to Display Value Correctly for "+WeekName1+" :" ,""+Val+"",""+Val1+"", "Fail");
							htmlToExtent(cName,mName,extentTest,driver1, "Failed to display data Correctly for : "+WeekName1+";"+Val+";"+Val1+";Fail");
						}
					}	
				}	
			}
			catch(Exception e)
			{
				System.out.println("Exception Occured" +e.getMessage());
				Report_AddStep("testcase","Failed to Fetch Week Total Value" ,"","", "Fail");
				htmlToExtent(cName,mName,extentTest,driver1, "Exception Occured while comparing data;;;Fail");
			}
			return FinalTotalSum;	
		}
	public double PlannedSalestoBDGTRateforWeektable(MonthlyPlanningGroupObjects objMonthlyPlanningGroupObjects,ExtentTest extentTest,String Table,String FieldToVerify,String Bdgtwgprcnt,
			String Plndsls,String Bdgtsls,String PlndWgs,String Bdgtwg,List<List<Object>> PositiveFactor,List<List<Object>> NegativeFactor) throws Exception
	{
		
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
			int RowCount =TradingStatementTableCount(Table);
			int j=RowCount-6;
			for( int i=j;i<=RowCount-2;i++)
			{			{
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
				CalVal = String.format("%.4g%n", bgdtwgprcnt);
				finalBdgtWgPrcnt = Double.parseDouble(CalVal);
				System.out.println(finalBdgtWgPrcnt);
				
				//UI Value which needs to be compared
				BDGTRate=TrimData(Value);
				System.out.println(BDGTRate+" "+Value);
				BigDecimal ActlVal =ConvertBigDecimalNbr(BDGTRate);
				
				//Rate Factor Conversion
				
				PositiveRateFctr =new Double(PositiveFactor.get(i-j).get(0).toString());
				NegativeRateFctr =new Double(NegativeFactor.get(i-j).get(0).toString());
						
				if(Plannedsales>Budgetsales)
				{	
					
					CalculatedValue = (PlannedWages - ((((PositiveRateFctr * finalBdgtWgPrcnt) * (Plannedsales - Budgetsales)) + BudgetWage)));
					System.out.println(CalculatedValue);
					
					BigDecimal CalcVal =ConvertBigDecimalNbr(CalculatedValue);
					Result = (ActlVal.subtract(CalcVal).toString());
              
					if(ActlVal.equals(CalcVal))
					{
						System.out.println(ActlVal+"  "+CalcVal);
						Report_AddStep("testcase","Calculated Value for Department "+Dept+" is displayed successfully" ,""+ActlVal+"",""+CalcVal+"", "Pass");
						htmlToExtent(cName,mName,extentTest,driver1, "Value displayed Correctly for : "+Dept+";"+ActlVal+";"+CalcVal+";Pass");
					}
					else
					{
						System.out.println(ActlVal+"  "+CalcVal);
						Report_AddStep("testcase","Failed to Display Value Correctly for Department "+Dept+"" ,""+ActlVal+"",""+CalcVal+"", "Fail");
						htmlToExtent(cName,mName,extentTest,driver1, "Failed to display data Correctly for : "+Dept+";"+ActlVal+";"+CalcVal+";Fail");
					}
				}else if(Plannedsales<Budgetsales)		
				{
					CalculatedValue = (PlannedWages - ((((NegativeRateFctr * finalBdgtWgPrcnt) * (Plannedsales - Budgetsales)) + BudgetWage)));
					System.out.println(CalculatedValue);
					
					BigDecimal CalcVal =ConvertBigDecimalNbr(CalculatedValue);
					Result = (ActlVal.subtract(CalcVal).toString());

					if(ActlVal.equals(CalcVal))
					{
						System.out.println(ActlVal+"  "+CalcVal);
						Report_AddStep("testcase","Calculated Value for Department "+Dept+" is displayed successfully" ,""+ActlVal+"",""+CalcVal+"", "Pass");
						htmlToExtent(cName,mName,extentTest,driver1, "Value displayed Correctly for : "+Dept+";"+ActlVal+";"+CalcVal+";Pass");
					}
					else
					{
						System.out.println(ActlVal+"  "+CalcVal);
						Report_AddStep("testcase","Failed to Display Value Correctly for Department "+Dept+"" ,""+ActlVal+"",""+CalcVal+"", "Fail");
						htmlToExtent(cName,mName,extentTest,driver1, "Failed to display data Correctly for : "+Dept+";"+ActlVal+";"+CalcVal+";Fail");
					}
				}	
				else if(Plannedsales==Budgetsales)
				{
					CalculatedValue = (PlannedWages - BudgetWage);
					System.out.println(CalculatedValue);
					
					BigDecimal CalcVal =ConvertBigDecimalNbr(CalculatedValue);
					Result = (ActlVal.subtract(CalcVal).toString());
					/*boolean Final1 =Result.equals("-1");
						boolean Final = Result.equals("1");*/
					if(ActlVal.equals(CalcVal)/* || Final==true || Final1==true */)
					{
						System.out.println(ActlVal+"  "+CalcVal);
						Report_AddStep("testcase","Calculated Value for Department "+Dept+" is displayed successfully" ,""+ActlVal+"",""+CalcVal+"", "Pass");
						htmlToExtent(cName,mName,extentTest,driver1, "Value displayed Correctly for : "+Dept+";"+ActlVal+";"+CalcVal+";Pass");
					}
					else
					{
						System.out.println(ActlVal+"  "+CalcVal);
						Report_AddStep("testcase","Failed to Display Value Correctly for Department "+Dept+"" ,""+ActlVal+"",""+CalcVal+"", "Fail");
						htmlToExtent(cName,mName,extentTest,driver1, "Failed to display data Correctly for : "+Dept+";"+ActlVal+";"+CalcVal+";Fail");
					}
				}	
				
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

	public void PlannedSalestoBDGTRateforGroup(MonthlyPlanningGroupObjects objMonthlyPlanningGroupObjects,ExtentTest extentTest,String Table,String FieldToVerify,String Bdgtwgprcnt,
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
			int RowCount =TradingStatementTableCount(Table);
			for(i=1;i<=RowCount-8;i++)
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
					if(CalcVal.equals(ActlVal) || Final1==true || Final==true )
					{
						System.out.println(ActlVal+"  "+CalcVal);
						Report_AddStep("testcase","Calculated Value for Department "+Dept+" is displayed successfully" ,""+ActlVal+"",""+CalcVal+"", "Pass");
						htmlToExtent(cName,mName,extentTest,driver1, "Value displayed Correctly for : "+Dept+";"+ActlVal+";"+CalcVal+";Pass");
					}
					else
					{
						System.out.println(ActlVal+"  "+CalcVal);
						Report_AddStep("testcase","Failed to Display Value Correctly for Department "+Dept+"" ,""+ActlVal+"",""+CalcVal+"", "Fail");
						htmlToExtent(cName,mName,extentTest,driver1, "Failed to display data Correctly for : "+Dept+";"+ActlVal+";"+CalcVal+";Fail");
					}
				}else if(Plannedsales<Budgetsales)
				{
					CalculatedValue = (PlannedWages - ((((NegativeRateFctr * finalBdgtWgPrcnt) * (Plannedsales - Budgetsales)) + BudgetWage)));
					System.out.println(CalculatedValue);
					
					BigDecimal CalcVal =ConvertBigDecimalNbr(CalculatedValue);
					Result = (ActlVal.subtract(CalcVal).toString());
					boolean Final1 =Result.equals("-1");
					boolean Final = Result.equals("1"); 
					if(CalcVal.equals(ActlVal) || Final1==true || Final==true )
					{
						System.out.println(ActlVal+"  "+CalcVal);
						Report_AddStep("testcase","Calculated Value for Department "+Dept+" is displayed successfully" ,""+ActlVal+"",""+CalcVal+"", "Pass");
						htmlToExtent(cName,mName,extentTest,driver1, "Value displayed Correctly for : "+Dept+";"+ActlVal+";"+CalcVal+";Pass");
					}
					else
					{
						System.out.println(ActlVal+"  "+CalcVal);
						Report_AddStep("testcase","Failed to Display Value Correctly for Department "+Dept+"" ,""+ActlVal+"",""+CalcVal+"", "Fail");
						htmlToExtent(cName,mName,extentTest,driver1, "Failed to display data Correctly for : "+Dept+";"+ActlVal+";"+CalcVal+";Fail");
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
					if(CalcVal.equals(ActlVal) || Final1==true || Final==true )
					{
						System.out.println(ActlVal+"  "+CalcVal);
						Report_AddStep("testcase","Calculated Value for Department "+Dept+" is displayed successfully" ,""+ActlVal+"",""+CalcVal+"", "Pass");
						htmlToExtent(cName,mName,extentTest,driver1, "Value displayed Correctly for : "+Dept+";"+ActlVal+";"+CalcVal+";Pass");
					}
					else
					{
						System.out.println(ActlVal+"  "+CalcVal);
						Report_AddStep("testcase","Failed to Display Value Correctly for Department "+Dept+"" ,""+ActlVal+"",""+CalcVal+"", "Fail");
						htmlToExtent(cName,mName,extentTest,driver1, "Failed to display data Correctly for : "+Dept+";"+ActlVal+";"+CalcVal+";Fail");
					}
				}	
				
			}	
		}
		catch(Exception e)
		{
			System.out.println("Exception Occured" +e.getMessage());
			Report_AddStep("testcase","Failed to Fetch Department Total Value" ,"","", "Fail");
			htmlToExtent(cName,mName,extentTest,driver1, "Exception Occured while comparing data;;;Fail");
		}
		return ;	
	}

	//Get GetStoreTotal
	public List<List<Object>> GetTotal(ExtentTest extentTest,WebElement RowName,WebElement FieldToVerify)
	{
		String Dept,Data=null;
		double CalcValue;
		int columnsize=2;
		List<List<Object>> UIValues= new  ArrayList<List<Object>>();	
			List<Object> row = new ArrayList<>(columnsize);
			Dept = RowName.getText();
			Data = FieldToVerify.getText();
			CalcValue=TrimData(Data);
			row.add(Dept);
			row.add(CalcValue);	
			UIValues.add(row);
		return UIValues;
	}
	
		//Compare UI and DB values
		public void CompareValuesforOR(ExtentTest extentTest,List<List<Object>> DBValues,List<List<Object>> UIValues) throws Exception
		{
			try
			{	
				int RowCount = DBValues.size();
				String Screen = "Zone",Name = null,WeekName=null,DBName=null;
				double CalcuatedUIValue = 0,CalcuatedDBValue=0,RoundOffValue = 0,RoundOffValue1=0;
				boolean result = false;
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
							System.out.println("FinalDBValue"+FinalDBValue);
							
							CalcuatedUIValue =new Double(UIValues.get(i).get(j).toString());
							RoundOffValue1 = Math.round(CalcuatedUIValue);
							FinalUIValue =ConvertBigDecimalSingleDigit(CalcuatedUIValue);
							System.out.println("FinalUIValue"+FinalUIValue);
							BigDecimal biggy =FinalUIValue.subtract(FinalDBValue);
							double Variance = biggy.doubleValue();
							if(Variance <=0.2 && Variance >=-0.2)
								result=true;
							else
								result=false;
						}
						else
						{
							WeekName =new String(UIValues.get(i).get(j).toString());
						}
						
					}
					if(result==true)
					{
						Report_AddStep("testcase","Calculated DB values are displayed Correctly in UI for: "+WeekName+"" ,""+FinalUIValue+"",""+FinalDBValue+"", "Pass");
						htmlToExtent(cName,mName,extentTest,driver1, "Value displayed Correctly for : "+WeekName+";"+CalcuatedUIValue+";"+CalcuatedDBValue+";Pass");
					}
					else
					{
						Report_AddStep("testcase","Calculated DB values are Not Matching for: "+WeekName+"" ,""+FinalUIValue+"",""+FinalDBValue+"", "Fail");
						htmlToExtent(cName,mName,extentTest,driver1, "Failed to display data Correctly for : "+WeekName+";"+CalcuatedUIValue+";"+CalcuatedDBValue+";Fail");
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
		public void CompareValueDecimalFields(ExtentTest extentTest,List<List<Object>> DBValues,List<List<Object>> UIValues) throws Exception
		{
			try
			{	
				int RowCount = DBValues.size();
				String WeekName=null;
				double CalcuatedUIValue = 0,CalcuatedDBValue=0;
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
							FinalDBValue =ConvertBigDecimal(CalcuatedDBValue);
							System.out.println("FinalDBValue"+FinalDBValue);
							
							CalcuatedUIValue =new Double(UIValues.get(i).get(j).toString());
							FinalUIValue =ConvertBigDecimal(CalcuatedUIValue);
							System.out.println("FinalUIValue"+FinalUIValue);
							BigDecimal biggy =FinalUIValue.subtract(FinalDBValue);
							double Variance = biggy.doubleValue();
							if(Variance <=0.2 && Variance >=-0.1)
							{
								Report_AddStep("testcase","Calculated DB values are displayed Correctly in UI for: "+WeekName+"" ,""+FinalUIValue+"",""+FinalDBValue+"", "Pass");
								htmlToExtent(cName,mName,extentTest,driver1, "Value displayed Correctly for : "+WeekName+";"+CalcuatedUIValue+";"+CalcuatedDBValue+";Pass");
							}
							else
							{
								Report_AddStep("testcase","Calculated DB values are Not Matching for: "+WeekName+"" ,""+FinalUIValue+"",""+FinalDBValue+"", "Fail");
								htmlToExtent(cName,mName,extentTest,driver1, "Failed to display data Correctly for : "+WeekName+";"+CalcuatedUIValue+";"+CalcuatedDBValue+";Fail");
							}
						}
						else
						{
							WeekName =new String(UIValues.get(i).get(j).toString());
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
		// For SMS Alone
		public void CompareValueDecimalFieldsSMS(ExtentTest extentTest,List<List<Object>> DBValues,List<List<Object>> UIValues) throws Exception
		{
			try
			{	
				int RowCount = DBValues.size();
				String WeekName=null;
				double CalcuatedUIValue = 0,CalcuatedDBValue=0;
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
							FinalDBValue =ConvertBigDecimal(CalcuatedDBValue);
							System.out.println("FinalDBValue"+FinalDBValue);
							
							CalcuatedUIValue =new Double(UIValues.get(i).get(j).toString());
							FinalUIValue =ConvertBigDecimal(CalcuatedUIValue);
							System.out.println("FinalUIValue"+FinalUIValue);
							BigDecimal biggy =FinalUIValue.subtract(FinalDBValue);
							double Variance = biggy.doubleValue();
							if(Variance <=0.5 && Variance >=-0.5)
							{
								Report_AddStep("testcase","Calculated DB values are displayed Correctly in UI for: "+WeekName+"" ,""+FinalUIValue+"",""+FinalDBValue+"", "Pass");
								htmlToExtent(cName,mName,extentTest,driver1, "Value displayed Correctly for : "+WeekName+";"+CalcuatedUIValue+";"+CalcuatedDBValue+";Pass");
							}
							else
							{
								Report_AddStep("testcase","Calculated DB values are Not Matching for: "+WeekName+"" ,""+FinalUIValue+"",""+FinalDBValue+"", "Fail");
								htmlToExtent(cName,mName,extentTest,driver1, "Failed to display data Correctly for : "+WeekName+";"+CalcuatedUIValue+";"+CalcuatedDBValue+";Fail");
							}
						}
						else
						{
							WeekName =new String(UIValues.get(i).get(j).toString());
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
		//MP Screen New enhancement Methods
		public void CompareValueDecimalFieldsMP(ExtentTest extentTest,List<List<Object>> DBValues,List<List<Object>> UIValues) throws Exception
		{
			try
			{	
				int RowCount = DBValues.size();
				String WeekName=null;
				double CalcuatedUIValue = 0,CalcuatedDBValue=0;
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
							FinalDBValue =ConvertBigDecimal(CalcuatedDBValue);
							System.out.println("FinalDBValue"+FinalDBValue);
							
							CalcuatedUIValue =new Double(UIValues.get(i).get(j).toString());
							FinalUIValue =ConvertBigDecimal(CalcuatedUIValue);
							System.out.println("FinalUIValue"+FinalUIValue);
							BigDecimal biggy =FinalUIValue.subtract(FinalDBValue);
							double Variance = biggy.doubleValue();
							if(Variance <=0.05 && Variance >=-0.05)
							{
								Report_AddStep("testcase","Calculated DB values are displayed Correctly in UI for: "+WeekName+"" ,""+FinalUIValue+"",""+FinalDBValue+"", "Pass");
								htmlToExtent(cName,mName,extentTest,driver1, "Value displayed Correctly for : "+WeekName+";"+CalcuatedUIValue+";"+CalcuatedDBValue+";Pass");
							}
							else
							{
								Report_AddStep("testcase","Calculated DB values are Not Matching for: "+WeekName+"" ,""+FinalUIValue+"",""+FinalDBValue+"", "Fail");
								htmlToExtent(cName,mName,extentTest,driver1, "Failed to display data Correctly for : "+WeekName+";"+CalcuatedUIValue+";"+CalcuatedDBValue+";Fail");
							}
						}
						else
						{
							WeekName =new String(UIValues.get(i).get(j).toString());
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
		public void CompareValuesForLYHours(ExtentTest extentTest,List<List<Object>> DBValues,List<List<Object>> UIValues) throws Exception
		{
			try
			{	
				int RowCount = DBValues.size();
				String WeekName=null,DBName=null;
				double CalcuatedUIValue = 0,CalcuatedDBValue=0,RoundOffValue = 0,RoundOffValue1=0;
				boolean result = false;
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
							System.out.println("FinalDBValue"+FinalDBValue);
							
							CalcuatedUIValue =new Double(UIValues.get(i).get(j).toString());
							RoundOffValue1 = Math.round(CalcuatedUIValue);
							FinalUIValue =ConvertBigDecimalNbr(CalcuatedUIValue);
							System.out.println("FinalUIValue"+FinalUIValue);
							BigDecimal biggy =FinalUIValue.subtract(FinalDBValue);
							int Variance = biggy.intValue();
							if(Variance <=1 && Variance >=-1)
								result=true;
							else
								result=false;
						}
						else
						{
							WeekName =new String(UIValues.get(i).get(j).toString());
						}
						
					}
					if(result==true)
					{
						Report_AddStep("testcase","Calculated DB values are displayed Correctly in UI for: "+WeekName+"" ,""+FinalUIValue+"",""+FinalDBValue+"", "Pass");
						htmlToExtent(cName,mName,extentTest,driver1, "Value displayed Correctly for : "+WeekName+";"+CalcuatedUIValue+";"+CalcuatedDBValue+";Pass");
					}
					else
					{
						Report_AddStep("testcase","Calculated DB values are Not Matching for: "+WeekName+"" ,""+FinalUIValue+"",""+FinalDBValue+"", "Fail");
						htmlToExtent(cName,mName,extentTest,driver1, "Failed to display data Correctly for : "+WeekName+";"+CalcuatedUIValue+";"+CalcuatedDBValue+";Fail");
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
		
		//Sales History Screen
		public void CompareValuesSH(ExtentTest extentTest,List<List<Object>> DBValues,List<List<Object>> UIValues) throws Exception
		{
			try
			{	
				int RowCount = DBValues.size();
				String WeekName=null,DBName=null;
				double CalcuatedUIValue = 0,CalcuatedDBValue=0,RoundOffValue = 0,RoundOffValue1=0;
				BigDecimal FinalDBValue = null,FinalUIValue=null;
				System.out.println(UIValues);
				System.out.println(DBValues);
			
				for(int i=0;i<=RowCount-1;i++)
				{
					for(int j=0;j<=7;j++)
					{
						if(j!=0)
						{		
							CalcuatedDBValue =new Double(DBValues.get(i).get(j).toString());
							RoundOffValue = Math.round(CalcuatedDBValue);	
							FinalDBValue =ConvertBigDecimal(CalcuatedDBValue);
							System.out.println("FinalDBValue"+FinalDBValue);
							
							CalcuatedUIValue =new Double(UIValues.get(i).get(j).toString());
							RoundOffValue1 = Math.round(CalcuatedUIValue);
							FinalUIValue =ConvertBigDecimal(CalcuatedUIValue);
							System.out.println("FinalUIValue"+FinalUIValue);
							BigDecimal biggy =FinalUIValue.subtract(FinalDBValue);
							int Variance = biggy.intValue();
							if(Variance <=2 && Variance >=-2)
							{
								Report_AddStep("testcase","Calculated DB values are displayed Correctly in UI for: "+WeekName+"" ,""+FinalUIValue+"",""+FinalDBValue+"", "Pass");
								htmlToExtent(cName,mName,extentTest,driver1, "Value displayed Correctly for : "+WeekName+";"+CalcuatedUIValue+";"+CalcuatedDBValue+";Pass");
							}
							else
							{
								Report_AddStep("testcase","Calculated DB values are Not Matching for: "+WeekName+"" ,""+FinalUIValue+"",""+FinalDBValue+"", "Fail");
								htmlToExtent(cName,mName,extentTest,driver1, "Failed to display data Correctly for : "+WeekName+";"+CalcuatedUIValue+";"+CalcuatedDBValue+";Fail");}
						}
						else
						{
							WeekName =new String(UIValues.get(i).get(j).toString());
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
}
