
package au.wow.WLPmain.pages;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.WebDriverWait;

import com.relevantcodes.extentreports.ExtentReports;
import com.relevantcodes.extentreports.ExtentTest;
import com.relevantcodes.extentreports.LogStatus;
import com.thoughtworks.selenium.webdriven.commands.GetAttribute;

import au.wow.WLPmain.objects.DTS_DepartmentObjects;
import au.wow.WLPmain.objects.DailyPlanningStoreObjects;
import au.wow.WLPmain.objects.DailyTradingStatementObjects;
import au.wow.WLPmain.objects.WeeklyDepartmentPlanningObjects;
import au.wow.WLPmain.objects.WeeklyPlanningStoreObjects;
import au.wow.wlp.utils.ExtentReportsScreenshotCode;
import au.wow.wlp.utils.SQLWrapper;
import au.wow.wlp.utils.TestBase;

public class WeeklyDepartmentPlanningPage extends TestBase {

	private static final CharSequence New = null;
	ExtentReports report;
	ExtentTest extentTest;
	WebDriver driver;
	ExtentReportsScreenshotCode Util;
	CommonFunctions common = PageFactory.initElements(this.getDriver(),CommonFunctions.class);
	public Logger log = LogManager.getLogger(WeeklyPlanningStorePage.class);
	SQLWrapper sql = new SQLWrapper(log);
	WeeklyPlanningStoreObjects objWeeklyPlanningStoreObjects;
	DTS_DepartmentObjects objDTS_DepartmentObjects;
	WeeklyDepartmentPlanningObjects objWeeklyDepartmentPlanningObjects;
	static ExtentReportsScreenshotCode ScreenShotUtil; 
	static WebDriverWait wait;
	String image;
	public WeeklyDepartmentPlanningPage()
	{
		// TODO Auto-generated constructor stub
		super();
	}
	
	//Select Department
	public void SelectDepartment(WebElement DataFrame,WebElement DeptHdr,WebElement DeptDrpdwn,ExtentTest extentTest,String Department) throws Exception
	{
		try
		{	
			System.out.println(getText(DeptHdr)); 			
			if((getText(DeptHdr).contains("Department")))
			{
				selectByValue(DeptDrpdwn,Department);
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
			
	//Get UI Value to Compare with DB Value
	public List<List<Object>> UIResultsSummaryInfo(String Header,ExtentTest extentTest,String FieldToVerify,String Table) throws Exception
	{
		String Day,Values=null;
		double CalcValue;
		int columnsize=2;
		WebElement Data;
		List<List<Object>> UIValues= new  ArrayList<List<Object>>();
		
		int RowCount =TradingStatementTableCount(Table);	
		for(int i=0;i<=RowCount-1;i++)
		{
			List<Object> row = new ArrayList<>(columnsize);
			WebElement Department = prepareWebElementWithDynamicXpathWithInt(Header, i);
			String Element = FieldToVerify.replace("dynamic",""+i);
			Data = prepareWebElementWithDynamicXpathWithInt(FieldToVerify, i);		
			Day = getText(Department);
			Values = getText(Data);
			CalcValue=TrimData(Values);
			row.add(Day);
			row.add(CalcValue);	
			UIValues.add(row);
		}
		return UIValues;
	}
	
	

	//New try
	public List<List<Object>> UIResults(WeeklyDepartmentPlanningObjects objWeeklyDepartmentPlanningObjects,String Table,String WeekDay,String ColumnName,String DayValue)
	{
		try
		{
			int columnsize=3,j;		
			WebElement MondayElmt,DeptElmt;
			String Dept,Day = null,Mon,Tue,Wed,Thu,Fri,Sat,Sun;
			double MonVal = 0;		
			List<List<Object>> UIValues= new  ArrayList<List<Object>>();		

			if(ColumnName.equalsIgnoreCase("Sales History"))
			{
				j=15;
				for (int i=22;i<=27;i++)
				{
					List<Object> row = new ArrayList<>(columnsize);
					DeptElmt=prepareWebElementWithDynamicXpathWithInt(WeekDay,j);
					MondayElmt= prepareWebElementWithDynamicXpathWithInt(DayValue,i);
					if(ColumnName.equalsIgnoreCase("Planned Sales") || ColumnName.equalsIgnoreCase("Planned Item Price") || ColumnName.equalsIgnoreCase("Wage Cost"))
					{
							Mon=getValueAttribute(MondayElmt);
					}
					else
					{
						Mon=getText(MondayElmt);
					}
					MonVal=TrimData(Mon);
					Day=getText(DeptElmt);	
					row.add(Day);
					row.add(MonVal);
					UIValues.add(row);	
					j--;
				}
			}
			
			if(ColumnName.equalsIgnoreCase("Generated Sales") || ColumnName.equalsIgnoreCase("Generated Item Price") || ColumnName.equalsIgnoreCase("Generated Items") || ColumnName.equalsIgnoreCase("Planned Items") || ColumnName.equalsIgnoreCase("SMS Hours") || ColumnName.equalsIgnoreCase("Inventory") || ColumnName.equalsIgnoreCase("Inventory") || ColumnName.equalsIgnoreCase("Merchandising") || ColumnName.equalsIgnoreCase("Replenishment") || ColumnName.equalsIgnoreCase("Kronos Scheduled Hours") || ColumnName.equalsIgnoreCase("Plan Variance") || ColumnName.equalsIgnoreCase("Operating Ratio") || ColumnName.equalsIgnoreCase("Wage Cost"))
			{
				j=4;
			for(int i=43;i<=49;i++)
			{
					List<Object> row = new ArrayList<>(columnsize);
					DeptElmt=prepareWebElementWithDynamicXpathWithInt(WeekDay,j);
					if(ColumnName.equalsIgnoreCase("Operating Ratio"))
					{
					MondayElmt= prepareWebElementWithDynamicXpathWithInt(DayValue,j);

					}
					else
					{
					MondayElmt= prepareWebElementWithDynamicXpathWithInt(DayValue,i);
					}
					if(ColumnName.equalsIgnoreCase("Wage Cost"))
					{
						Mon=getValueAttribute(MondayElmt);
					}
					else
					{
					Mon=getText(MondayElmt);
					}
					MonVal=TrimData(Mon);
					Day=getText(DeptElmt);	
					row.add(Day);
					row.add(MonVal);
					UIValues.add(row);	
					j++;
			}
			}	
	
			if(ColumnName.equalsIgnoreCase("Planned Sales") || ColumnName.equalsIgnoreCase("Planned Item Price"))
			{
				j=4;
					for(int i=1;i<=7;i++)
					{
					List<Object> row = new ArrayList<>(columnsize);
					DeptElmt=prepareWebElementWithDynamicXpathWithInt(WeekDay,j);
					MondayElmt= prepareWebElementWithDynamicXpathWithInt(DayValue,i);
					Mon=getValueAttribute(MondayElmt);
					System.out.println(Mon);
					MonVal=TrimData(Mon);
					Day=getText(DeptElmt);	
					row.add(Day);
					row.add(MonVal);
					UIValues.add(row);	
					j++;
					}
				}		
			
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
	
	//Get UI Value to Compare with DB Value
	public List<List<Object>> UIResults(WeeklyDepartmentPlanningObjects objWeeklyDepartmentPlanningObjects,String ColumnName,String Monday,String Tuesday,String Wednesday,String Thursday,String Friday,String Saturday,String Sunday
			,WebElement WeekDay)
	{
		try 
		{
			int i = 0,columnsize=3;
			WebElement MondayElmt,TuesdayElmt,WednesdayElmt,ThursdayElmt,FridayElmt,SaturdayElmt,SundayElmt;
			String Title,Mon,Tue,Wed,Thu,Fri,Sat,Sun;
			double MonVal,TueVal,WedVal,ThuVal,FriVal,SatVal,SunVal;
			List<List<Object>> UIValues= new  ArrayList<List<Object>>();	
			List<Object> row = new ArrayList<>(columnsize);
			
				MondayElmt = prepareWebElementWithDynamicXpathWithInt(Monday, i);
				TuesdayElmt = prepareWebElementWithDynamicXpathWithInt(Tuesday, i);
				WednesdayElmt = prepareWebElementWithDynamicXpathWithInt(Wednesday, i);
				ThursdayElmt = prepareWebElementWithDynamicXpathWithInt(Thursday, i);
				FridayElmt = prepareWebElementWithDynamicXpathWithInt(Friday, i);
				SaturdayElmt = prepareWebElementWithDynamicXpathWithInt(Saturday, i);
				SundayElmt = prepareWebElementWithDynamicXpathWithInt(Sunday, i);
				if(ColumnName.equalsIgnoreCase("Planned Sales") || ColumnName.equalsIgnoreCase("Planned Item Price"))
				{
					Title = getText(WeekDay);
					Mon = getValueAttribute(MondayElmt);
					Tue = getValueAttribute(TuesdayElmt);
					Wed = getValueAttribute(WednesdayElmt);
					Thu = getValueAttribute(ThursdayElmt);
					Fri = getValueAttribute(FridayElmt);
					Sat = getValueAttribute(SaturdayElmt);
					Sun = getValueAttribute(SundayElmt);
				}
				else
				{
					Title = getText(WeekDay);
					Mon = getText(MondayElmt);
					Tue = getText(TuesdayElmt);
					Wed = getText(WednesdayElmt);
					Thu = getText(ThursdayElmt);
					Fri = getText(FridayElmt);
					Sat = getText(SaturdayElmt);
					Sun = getText(SundayElmt);
				}
				
				MonVal= TrimData(Mon);
				TueVal= TrimData(Tue);
				WedVal= TrimData(Wed);
				ThuVal= TrimData(Thu);
				FriVal= TrimData(Fri);
				SatVal= TrimData(Sat);
				SunVal= TrimData(Sun);
				
				row.add(Title);
				row.add(MonVal);	
				row.add(TueVal);	
				row.add(WedVal);	
				row.add(ThuVal);	
				row.add(FriVal);	
				row.add(SatVal);	
				row.add(SunVal);	
				
				UIValues.add(row);
				return UIValues;
		}
		catch (Exception e) 
		{
			System.out.println("Exception occured while Switching the Frame");
			e.printStackTrace();
		}
		return null;
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
			Data = getValueAttribute(FieldToVerify);
			CalcValue=TrimData(Data);
			row.add(Dept);
			row.add(CalcValue);	
			UIValues.add(row);
		return UIValues;
	}

	public void NavigationButton(WebElement Frame,WebElement Button,WebElement Title,String PageName,ExtentTest extentTest) throws Exception
	{
		if(isDisplayed(Button))
		{
			click(Button);
			waitFor(2);
			if(getText(Title).contains(PageName))
			{
				Report_AddStep("testcase",""+Button+" button is clicked Successfully","","", "Pass");
				htmlToExtent(cName,mName,extentTest,driver1, ""+Button+" button is clicked Successfully ;;;Pass");
			}
			else
			{
				Report_AddStep("testcase","Failed to Click "+Button+" button" ,"","", "Fail");
				htmlToExtent(cName,mName,extentTest,driver1, "Failed to Click "+Button+" button ;;;Fail");
			}			
		}
		else
				System.out.println("Daily Planning button is unavailable");
	}

}