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

public class AlocationofFixHoursPage extends TestBase {

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
	public AlocationofFixHoursPage()
	{
		// TODO Auto-generated constructor stub
		super();
	}
	
	//Get UI Value to Compare with DB Value
	public List<List<Object>> UIResults(String Table,String ColumnName,String Monday,String Tuesday,String Wednesday,String Thursday,String Friday,String Saturday,String Sunday
			,String WeekDay)
	{
		try 
		{
			int RowCount,count,i = 0,initial = 0,columnsize=3;
			WebElement MondayElmt,TuesdayElmt,WednesdayElmt,ThursdayElmt,FridayElmt,SaturdayElmt,SundayElmt,WeekDayElmt;
			String Title,Mon,Tue,Wed,Thu,Fri,Sat,Sun;
			double MonVal,TueVal,WedVal,ThuVal,FriVal,SatVal,SunVal;
			List<List<Object>> UIValues= new  ArrayList<List<Object>>();
			if(ColumnName.equalsIgnoreCase("Total Daily Hours"))
			{
				RowCount =TradingStatementTableCount(Table);
				count=RowCount-1;
				List<Object> row = new ArrayList<>(columnsize);
				
				WeekDayElmt = prepareWebElementWithDynamicXpathWithInt(WeekDay, count);
				MondayElmt = prepareWebElementWithDynamicXpathWithInt(Monday, count);
				TuesdayElmt = prepareWebElementWithDynamicXpathWithInt(Tuesday, count);
				WednesdayElmt = prepareWebElementWithDynamicXpathWithInt(Wednesday, count);
				ThursdayElmt = prepareWebElementWithDynamicXpathWithInt(Thursday, count);
				FridayElmt = prepareWebElementWithDynamicXpathWithInt(Friday, count);
				SaturdayElmt = prepareWebElementWithDynamicXpathWithInt(Saturday, count);
				SundayElmt = prepareWebElementWithDynamicXpathWithInt(Sunday, count);
				
					Title = getText(WeekDayElmt);
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
				
				row.add(Title);
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
			else	
				RowCount =TradingStatementTableCount(Table);
			
			for(i=initial;i<=RowCount-2;i=i+1)
			{
				List<Object> row = new ArrayList<>(columnsize);
			
				WeekDayElmt = prepareWebElementWithDynamicXpathWithInt(WeekDay, i);
				MondayElmt = prepareWebElementWithDynamicXpathWithInt(Monday, i);
				TuesdayElmt = prepareWebElementWithDynamicXpathWithInt(Tuesday, i);
				WednesdayElmt = prepareWebElementWithDynamicXpathWithInt(Wednesday, i);
				ThursdayElmt = prepareWebElementWithDynamicXpathWithInt(Thursday, i);
				FridayElmt = prepareWebElementWithDynamicXpathWithInt(Friday, i);
				SaturdayElmt = prepareWebElementWithDynamicXpathWithInt(Saturday, i);
				SundayElmt = prepareWebElementWithDynamicXpathWithInt(Sunday, i);
				if(ColumnName.equalsIgnoreCase("Planned Sales") || ColumnName.equalsIgnoreCase("Planned Item Price") )
				{
					Title = getText(WeekDayElmt);
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
					Title = getText(WeekDayElmt);
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
			}
			System.out.println(UIValues);
			return UIValues;
		}
		catch (Exception e) 
		{
			System.out.println("Exception occured while fetching data from UI");
			e.printStackTrace();
		}
		return null;
	}
	
	
	
	//Get GetStoreTotal
	public List<List<Object>> GetTotal(ExtentTest extentTest,String RowName,String FieldToVerify,String Table,String Column)
	{
		int i,RowCount,count;
		String Dept,Data=null;
		WebElement RowElmt,FieldElmt;
		double CalcValue;
		if(Column.equalsIgnoreCase("Total Daily Hours"))
		{
			RowCount =TradingStatementTableCount(Table);
			count=RowCount-1;
			List<List<Object>> UIValues= new  ArrayList<List<Object>>();	
			int columnsize=2;
			
				List<Object> row = new ArrayList<>(columnsize);
				RowElmt = prepareWebElementWithDynamicXpathWithInt(RowName, count);
				FieldElmt = prepareWebElementWithDynamicXpathWithInt(FieldToVerify, count);
				Dept = RowElmt.getText();
				if(Column.equalsIgnoreCase("Fixed Percentage"))
					Data = getText(FieldElmt);
				else
					Data = getText(FieldElmt);
				
					CalcValue=TrimData(Data);
					row.add(Dept);
					row.add(CalcValue);	
					UIValues.add(row);
				
			
			System.out.println(UIValues);
			return UIValues;
		}
		else	
			RowCount =TradingStatementTableCount(Table);
		List<List<Object>> UIValues= new  ArrayList<List<Object>>();	
		int columnsize=2;
		
		for(i=0;i<=RowCount-2;i=i+1)
		{		

			List<Object> row = new ArrayList<>(columnsize);
			RowElmt = prepareWebElementWithDynamicXpathWithInt(RowName, i);
			FieldElmt = prepareWebElementWithDynamicXpathWithInt(FieldToVerify, i);
			Dept = RowElmt.getText();
			if(Column.equalsIgnoreCase("Fixed Percentage"))
				Data = getText(FieldElmt);
			else
				Data = getText(FieldElmt);
			if(Column.equalsIgnoreCase("Task Type") || Column.equalsIgnoreCase("Variable Standard") || Column.equalsIgnoreCase("Variable Type") || Column.equalsIgnoreCase("Priority"))
			{
				row.add(Dept);
				row.add(Data);	
				UIValues.add(row);
			}
			else
			{
				CalcValue=TrimData(Data);
				row.add(Dept);
				row.add(CalcValue);	
				UIValues.add(row);
			}
		}
		System.out.println(UIValues);
		return UIValues;
	}
	
	//Compare UI and DB values
	public void CompareValues(ExtentTest extentTest,List<List<Object>> DBValues,List<List<Object>> UIValues) throws Exception
	{
		try
		{	
			int RowCount = DBValues.size();
			String WeekName=null;
			String CalcuatedUIValue = null;
			String CalcuatedDBValue=null;
			
			System.out.println(UIValues);
			System.out.println(DBValues);
		
			for(int i=0;i<=RowCount-1;i++)
			{
				for(int j=0;j<2;j++)
				{
					if(j!=0)
					{		
						CalcuatedDBValue =DBValues.get(i).get(j).toString();
						System.out.println("FinalDBValue"+CalcuatedDBValue);
						
						CalcuatedUIValue =UIValues.get(i).get(j).toString();
						System.out.println("FinalUIValue"+CalcuatedUIValue);
						boolean Final = CalcuatedDBValue.equalsIgnoreCase(CalcuatedUIValue);
						if(Final==true)
						{
							Report_AddStep("testcase","Calculated DB values are displayed Correctly in UI for: "+WeekName+"" ,""+CalcuatedUIValue+"",""+CalcuatedDBValue+"", "Pass");
							htmlToExtent(cName,mName,extentTest,driver1, "Value displayed Correctly for : "+WeekName+"; Actual Value = "+CalcuatedUIValue+"; Excepted Value = "+CalcuatedDBValue+";Pass");
						}
						else
						{
							Report_AddStep("testcase","Calculated DB values are Not Matching for: "+WeekName+"" ,""+CalcuatedUIValue+"",""+CalcuatedDBValue+"", "Fail");
							htmlToExtent(cName,mName,extentTest,driver1, "Failed to Display Values Correctly for : "+WeekName+"; Actual Value = "+CalcuatedUIValue+"; Excepted Value = "+CalcuatedDBValue+";Fail");
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
			htmlToExtent(cName,mName,extentTest,driver1, "Exception occured while comparing data;;;Fail");
		}
	}
}
