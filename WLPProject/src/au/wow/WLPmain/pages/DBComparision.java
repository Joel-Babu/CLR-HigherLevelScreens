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
import org.jboss.netty.util.internal.SystemPropertyUtil;
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

public class DBComparision extends TestBase
{
	CommonFunctions common = PageFactory.initElements(this.getDriver(),CommonFunctions.class);
	public Logger log = LogManager.getLogger(DBComparision.class);
	SQLWrapper sql = new SQLWrapper(log);
	WeeklyPlanningStoreObjects objWeeklyPlanningStoreObjects;
	MonthlyPlanningStoreObjects objMonthlyPlanningStoreObjects;
	static ExtentReportsScreenshotCode ScreenShotUtil; 
	static WebDriverWait wait;
	String image;
	public DBComparision()
	{
		// TODO Auto-generated constructor stub
		super();
	}
	
	//Compare UI and DB values
	public void CompareValues(ExtentTest extentTest,List<List<Object>> TotalValue,List<List<Object>> IndividualValue,String Column) throws Exception
	{
		try
		{	
			int TotalRowCount = TotalValue.size();
			
			String Testing[] = TotalValue.toString().replace('[', ' ').replace(']', ' ').split(",");
			int size = Testing.length;
			int IndividualRowCount = IndividualValue.size();
			BigDecimal FinalDBValue = null,FinalUIValue=null;
			double CalcuatedUIValue = 0,CalcuatedDBValue=0,RoundOffValue = 0,RoundOffValue1=0;
			boolean result = false;
		
			for(int i=0;i<=TotalRowCount-1;i++)
			{
				for(int j=0;j<=IndividualRowCount-1;j++)
				{
					if((TotalValue.get(i).get(0)).equals(IndividualValue.get(j).get(0)))
					{
						for (int k=1;k<size;k++)
						{
						CalcuatedDBValue =new Double(TotalValue.get(i).get(k).toString());
						RoundOffValue = Math.round(CalcuatedDBValue);	
						FinalDBValue =ConvertBigDecimal(CalcuatedDBValue);
						System.out.println(FinalDBValue);
						
						CalcuatedUIValue =new Double(IndividualValue.get(j).get(k).toString());
						RoundOffValue1 = Math.round(CalcuatedUIValue);
						FinalUIValue =ConvertBigDecimal(CalcuatedUIValue);
						System.out.println(FinalUIValue);
						BigDecimal biggy =FinalUIValue.subtract(FinalDBValue);
						int Variance = biggy.intValue();
						if(Variance <2 || Variance>-2)
							result=true;
						else
							result = false;
						if(result==true)
						{
							Report_AddStep("testcase","System displays the "+Column+" column Total Value and Individual Field Value Correctly :" ,""+CalcuatedUIValue+"",""+CalcuatedDBValue+"", "Pass");
							htmlToExtent(cName,mName,extentTest,driver1, "Value displayed Correctly for : "+Column+"; Actual Value = "+CalcuatedUIValue+"; Excepted Value = "+CalcuatedDBValue+";Pass");
						}
						else
						{
							Report_AddStep("testcase","System Fails to displays the "+Column+" column Total Value and Individual Field Value Correctly :" ,""+CalcuatedUIValue+"",""+CalcuatedDBValue+"", "Fail");
							htmlToExtent(cName,mName,extentTest,driver1, "Failed to display data Correctly for : "+Column+"; Actual Value = "+CalcuatedUIValue+"; Excepted Value = "+CalcuatedDBValue+";Fail");
						}
					}
					}
					else
						continue;				
				}
			}
			
		}
		catch(Exception e)
		{
			System.out.println("Exception Occured" +e.getMessage());
			Report_AddStep("testcase","System Fails to displays the Total Value and Individual Field Value Correctly" ,"","", "Fail");
			htmlToExtent(cName,mName,extentTest,driver1, "Exception Occured while comparing data;;;Fail");
		}
	}
	
	//Compare UI and DB values
	public void CompareValueDecimalFields(ExtentTest extentTest,List<List<Object>> TotalValue,List<List<Object>> IndividualValue,String Column) throws Exception
	{
		try
		{	
			int TotalRowCount = TotalValue.size();
			int IndividualRowCount = IndividualValue.size();
			BigDecimal Ttl = null,IndVal= null,FinalVal= null;
			double Variance;
			boolean result = false;
		
			for(int i=0;i<=TotalRowCount-1;i++)
			{
				for(int j=0;j<=IndividualRowCount-1;j++)
				{
					if((TotalValue.get(i).get(0)).equals(IndividualValue.get(j).get(0)))
					{
						Ttl = (BigDecimal) TotalValue.get(i).get(1);
						IndVal = (BigDecimal) IndividualValue.get(j).get(1);
						FinalVal = Ttl.subtract(IndVal);
						Variance = FinalVal.doubleValue();
						if(Variance <=0.05 && Variance >=-0.05)
					
						{
							result=true;
							break;
						}
						else
							result = false;
							break;
					}
					else
						continue;
				}
			}
			if(result==true)
			{
				Report_AddStep("testcase","System displays the "+Column+" column Total Value and Individual Field Value Correctly :" ,""+Ttl+"",""+IndVal+"", "Pass");
				htmlToExtent(cName,mName,extentTest,driver1, "Value displayed Correctly for : "+Column+"; Actual Value = "+Ttl+"; Excepted Value = "+IndVal+";Pass");
			}
			else
			{
				Report_AddStep("testcase","System Fails to displays the "+Column+" column Total Value and Individual Field Value Correctly :" ,""+Ttl+"",""+IndVal+"", "Fail");
				htmlToExtent(cName,mName,extentTest,driver1, "Failed to display data Correctly for : "+Column+"; Actual Value = "+Ttl+"; Excepted Value = "+IndVal+";Fail");
			}
		}
		catch(Exception e)
		{
			System.out.println("Exception Occured" +e.getMessage());
			Report_AddStep("testcase","System Fails to displays the Total Value and Individual Field Value Correctly" ,"","", "Fail");
			htmlToExtent(cName,mName,extentTest,driver1, "Exception Occured while comparing data;;;Fail");
		}
	}
}
