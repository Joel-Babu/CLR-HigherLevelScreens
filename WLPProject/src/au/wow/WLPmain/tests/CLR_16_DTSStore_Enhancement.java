package au.wow.WLPmain.tests;

import java.awt.AWTException;

import org.testng.ITestContext;
import org.testng.annotations.*;

import java.io.File;
import java.io.IOException;
import java.lang.reflect.Method;
import java.math.BigDecimal;
import java.sql.SQLException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Collections;
import java.util.Date;
import java.util.Iterator;
import java.util.List;
import java.util.Set;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.Select;

import com.relevantcodes.extentreports.ExtentReports;
import com.relevantcodes.extentreports.ExtentTest;
import com.relevantcodes.extentreports.LogStatus;

import au.wow.WLPmain.DailyTradingStatement.DTS_Store;
import au.wow.WLPmain.objects.DailyPlanningMainObjects;
import au.wow.WLPmain.objects.DailyTradingStatementObjects;
import au.wow.WLPmain.objects.MonthlyPlanningGroupObjects;
import au.wow.WLPmain.objects.MonthlyPlanningStoreObjects;
import au.wow.WLPmain.objects.WeeklyPlanningStoreObjects;
import au.wow.WLPmain.pages.CommonFunctions;
import au.wow.WLPmain.pages.DailyPlanningMainPage;
import au.wow.WLPmain.pages.DailyTradingStatementGroupPage;
import au.wow.WLPmain.pages.DailyTradingStatementStorePage;
import au.wow.WLPmain.pages.MonthlyPlanningGroupPage;
import au.wow.WLPmain.pages.MonthlyPlanningStorePage;
import au.wow.WLPmain.pages.WeeklyPlanningStorePage;
import au.wow.WLPmain.pages.WoWLoginPage;
import au.wow.WLPmain.tests.StoreProperties;
import au.wow.wlp.utils.BasePage;
import au.wow.wlp.utils.CommonRepository;
import au.wow.wlp.utils.CustomExtentReports;
import au.wow.wlp.utils.ExtentReportsScreenshotCode;
import au.wow.wlp.utils.SQLWrapper;
import au.wow.wlp.utils.TestBase;
import au.wow.wlp.utils.TestStatus;
import au.wow.wlp.utils.XMLDataReader;

public class CLR_16_DTSStore_Enhancement extends TestBase
{
	protected Logger log = LogManager.getLogger(DTS_Store.class);
	TestStatus status;
	ExtentReportsScreenshotCode ScreenShotUtil;
	SQLWrapper sql = new SQLWrapper(log);
	WoWLoginPage loginPage;
	CommonRepository CommonFunc;
	WeeklyPlanningStoreObjects objWeeklyPlanningStoreObjects;
	DailyTradingStatementObjects objDailyTradingStatementObjects;
	WeeklyPlanningStorePage pageWeeklyPlanningPO;
	MonthlyPlanningStorePage pageMonthlyPlanningPO;
	DailyTradingStatementStorePage pageDailyTradingStatementStorePO;
	DailyTradingStatementGroupPage pageDailyTradingStatementGroupPO;
	BasePage BaseObj;
	CommonFunctions common;
	StoreProperties storeprop;
	List<List<Object>> UIValues,UITotal;	
	List<List<Object>> PlndVal,ActlVal,LYVal,PlndValTtl,ActlValTtl,LYValTtl,BdgtSls,BdgtSlsTtl,EstFcst,EstFcstTtl,AllowedWgs,AllowedWgsTtl,Var,VarTtl,BdgtWg,BdgtWgTtl;
	List<List<Object>> PlndValWkToDt,ActlValWkToDt,LYValWkToDt,PlndValTtlWkToDt,ActlValTtlWkToDt,LYValTtlWkToDt,VarWkToDt,ORWkToDt,VarTtlWkToDt,ORTtlWkToDt;
	List<List<Object>> DBPlndVal,DBActlVal,DBLYVal,DBPlndValTtl,DBActlTtl,DBLYValTtl,DBBdgtSls,DBBdgtSlsTtl,DBEstFcst,DBEstFcstTtl,DBAllowedWgs,DBAllowedWgsTtl,DBVar,
	DBVarTtl,DBBdgtWg,DBBdgtWgTtl,DBAlwdWgs,DBAlwdWgsTtl,AlwdWgs,AlwdWgsTtl,DBEstFcstSls,DBEstFcstSlsTtl,EstFcstSls,EstFcstSlsTtl,DBEstFcstWgs,DBEstFcstWgsTtl,EstFcstWgs,EstFcstWgsTtl,
	DBEstFcstWgsPrcnt,DBEstFcstWgsPrcntTtl,EstFcstWgsPrcnt,EstFcstWgsPrcntTtl,DBVarSales,DBVarSalesTtl,VarSls,VarSalesTtl,DBVarWages,DBVarWagesTtl,VarWages,VarWagesTtl,
	DBVarWagesPrcnt,DBVarWagesPrcntTtl,VarWagesPrcnt,VarWagesPrcntTtl,DBActlValTtl,DBActlValTtlWkToDt,DBActlORVal,DBActlORValTtl,ActlValOR,ActlValORTtl,DBActlValWkToDtOR,
	DBActlValTtlWkToDtOR,ActlValTtlWkToDtOR,ActlValWkToDtOR,DBActlValTtlSF,DBActlValTtlWkToDtSF;
	List<List<Object>> DBPlndValWkToDt,DBActlValWkToDt,DBLYValWkToDt,DBPlndValTtlWkToDt,DBActlTtlWkToDt,DBLYValTtlWkToDt,DBVarWkToDt,DBORWkToDt,DBVarWkToDtTtl,DBORWkToDtTtl;
	List<List<Object>> PlndValSF,ActlValSF,LYValSF,PlndValTtlSF,ActlValTtlSF,LYValTtlSF,BdgtSlsSF,BdgtSlsTtlSF,EstFcstSF,EstFcstTtlSF,AllowedWgsSF,AllowedWgsTtlSF,VarSF,VarTtlSF,BdgtWgSF,BdgtWgTtlSF;
	List<List<Object>> PlndValWkToDtSF,ActlValWkToDtSF,LYValWkToDtSF,PlndValTtlWkToDtSF,ActlValTtlWkToDtSF,LYValTtlWkToDtSF,VarWkToDtSF,ORWkToDtSF,VarTtlWkToDtSF,ORTtlWkToDtSF;
	List<List<Object>> DBPlndValSF,DBActlValSF,DBLYValSF,DBPlndValTtlSF,DBActlTtlSF,DBLYValTtlSF,DBBdgtSlsSF,DBBdgtSlsTtlSF,DBEstFcstSF,DBEstFcstTtlSF,DBAllowedWgsSF,DBAllowedWgsTtlSF,DBVarSF,
	DBVarTtlSF,DBBdgtWgSF,DBBdgtWgTtlSF,DBAlwdWgsSF,DBAlwdWgsTtlSF,AlwdWgsSF,AlwdWgsTtlSF,DBEstFcstSlsSF,DBEstFcstSlsTtlSF,EstFcstSlsSF,EstFcstSlsTtlSF,DBEstFcstWgsSF,DBEstFcstWgsTtlSF,EstFcstWgsSF,EstFcstWgsTtlSF,
	DBEstFcstWgsPrcntSF,DBEstFcstWgsPrcntTtlSF,EstFcstWgsPrcntSF,EstFcstWgsPrcntTtlSF,DBVarSalesSF,DBVarSalesTtlSF,VarSlsSF,VarSalesTtlSF,DBVarWagesSF,DBVarWagesTtlSF,VarWagesSF,VarWagesTtlSF,
	DBVarWagesPrcntSF,DBVarWagesPrcntTtlSF,VarWagesPrcntSF,VarWagesPrcntTtlSF;
	List<List<Object>> DBPlndValWkToDtSF,DBActlValWkToDtSF,DBLYValWkToDtSF,DBPlndValTtlWkToDtSF,DBActlTtlWkToDtSF,DBLYValTtlWkToDtSF,DBVarWkToDtSF,DBORWkToDtSF,DBVarWkToDtTtlSF,DBORWkToDtTtlSF;
	String WeekToTest, OptionToView, WeekValue, QueryValue = null;
	public CLR_16_DTSStore_Enhancement()
	{
		super.log=log;
	}

	@Test(priority=0)
	public void LogintoCLRApplication() throws Exception
	{
		loginPage = new WoWLoginPage().createPage(getDriver(), getContext(), log, status, data,report,extentTest, WoWLoginPage.class);
		pageWeeklyPlanningPO = loginPage.navToWeeklyPlanningStorePg(getContext().getStringProperty("store"));
	}
	
//////////// Sales ///////////////
	@Test(description = "Daily Trading Statement - Store, Sales Values", priority = 1)
	public void SalesColumnValidation() throws Exception {
		pageWeeklyPlanningPO = new WeeklyPlanningStorePage().createPage(getDriver(), getContext(), log, status, data,
				getReport(), getReportLogger(), WeeklyPlanningStorePage.class);
		pageDailyTradingStatementStorePO = new MonthlyPlanningGroupPage().createPage(getDriver(), getContext(), log,
				status, data, getReport(), getReportLogger(), DailyTradingStatementStorePage.class);
		TestStatus status = getTestStatus();
		System.out.println("Welcome " + getContext().getStringProperty("username"));
		System.out.println("Entering into TestCase : DTS Store - Sales View");
		pageWeeklyPlanningPO.SelectWeekFromDropdown(objWeeklyPlanningStoreObjects, extentTest,
				getContext().getStringProperty("SelectFiscalWeek"));

		pageWeeklyPlanningPO.selectPage(extentTest, objWeeklyPlanningStoreObjects.menuBar,
				objWeeklyPlanningStoreObjects.tradingScreens, "Trading Screens");
		pageWeeklyPlanningPO.selectPage(extentTest, objWeeklyPlanningStoreObjects.menuBar,
				objWeeklyPlanningStoreObjects.dailyTrading, "Daily Trading");
		pageWeeklyPlanningPO.selectPage(extentTest, objWeeklyPlanningStoreObjects.menuBar,
				objWeeklyPlanningStoreObjects.screenToViewDailyTrading, "Store");
		/*pageWeeklyPlanningPO.clickButton(objDailyTradingStatementObjects.ShowButton, extentTest, "Store");*/
		pageWeeklyPlanningPO.verifyPageName(extentTest, objWeeklyPlanningStoreObjects.DataFrame,
				objWeeklyPlanningStoreObjects.pageName, getContext().getStringProperty("DTSStore"));

		WeekValue = getContext().getStringProperty("WeekToTest");
		OptionToView = getContext().getStringProperty("OptionToView");
		
	
  //Planned Sales
		Report_AddStep("testcase", " PLANNED SALES - WEEK WISE DATA", "", "", "");
		extentTest = report.startTest("Planned Sales - Day Wise");
		
 		String PlannedSalesTotal = data.getText("PlannedSalesTotal");	
		DBPlndValTtl = sql.CLRexecuteQuery(getContext(), PlannedSalesTotal.replace("store",getContext().getStringProperty("store")).replace("Division", getContext().getStringProperty("Division"))
				.replace("Week",getContext().getStringProperty("SelectFiscalWeek")).replace("Department",getContext().getStringProperty("Longlife")));
		System.out.println("DB Value: "+DBPlndValTtl); 
		
		String PlannedSalesTotalSF = data.getText("PlannedSalesTotal");	
		DBPlndValTtlSF = sql.CLRexecuteQuery(getContext(), PlannedSalesTotalSF.replace("store",getContext().getStringProperty("store")).replace("Division", getContext().getStringProperty("Division"))
				.replace("Week",getContext().getStringProperty("SelectFiscalWeek")).replace("Department",getContext().getStringProperty("SeaFoodDeli")));
		System.out.println("DB Value: "+DBPlndValTtlSF); 
		
		PlndValTtl = pageDailyTradingStatementStorePO.CombinedPastUITotal("LLSales",objDailyTradingStatementObjects,objDailyTradingStatementObjects.MondayPlndSales,objDailyTradingStatementObjects.TuesdayPlndSales,
					objDailyTradingStatementObjects.WednesdayPlndSales,objDailyTradingStatementObjects.ThursdayPlndSales,objDailyTradingStatementObjects.ThursdayPlndSalesWhite,
					objDailyTradingStatementObjects.FridayPlndSales,objDailyTradingStatementObjects.FridayPlndSalesWhite,
					objDailyTradingStatementObjects.SaturdayPlndSales,objDailyTradingStatementObjects.SaturdayPlndSalesWhite,
					objDailyTradingStatementObjects.SundayPlndSales,objDailyTradingStatementObjects.SundayPlndSalesWhite,objDailyTradingStatementObjects.Table);
				
				PlndValTtlSF = pageDailyTradingStatementStorePO.CombinedPastUITotal("SFSales",objDailyTradingStatementObjects,objDailyTradingStatementObjects.MondayPlndSales,objDailyTradingStatementObjects.TuesdayPlndSales,
						objDailyTradingStatementObjects.WednesdayPlndSales,objDailyTradingStatementObjects.ThursdayPlndSales,objDailyTradingStatementObjects.ThursdayPlndSalesWhite,
						objDailyTradingStatementObjects.FridayPlndSales,objDailyTradingStatementObjects.FridayPlndSalesWhite,
						objDailyTradingStatementObjects.SaturdayPlndSales,objDailyTradingStatementObjects.SaturdayPlndSalesWhite,
						objDailyTradingStatementObjects.SundayPlndSales,objDailyTradingStatementObjects.SundayPlndSalesWhite,objDailyTradingStatementObjects.Table);
		
		
		pageDailyTradingStatementStorePO.DTSCompareValuesTotal(extentTest,DBPlndValTtl,PlndValTtl); 
		pageDailyTradingStatementStorePO.DTSCompareValuesTotal(extentTest,DBPlndValTtlSF,PlndValTtlSF); 
		
	
		//Actual Sales
		
		
		
		//LY Sales
		Report_AddStep("testcase", " LY SALES - WEEK WISE DATA", "", "", "");
		extentTest = report.startTest("LY Sales - Day Wise");
		
		if (WeekValue.contains("Past") ) {
		String LYSalesTotal = data.getText("PastWeekLYSalesTotal");	
		DBLYValTtl = sql.CLRexecuteQuery(getContext(), LYSalesTotal.replace("store",getContext().getStringProperty("store")).replace("Division", getContext().getStringProperty("Division")).replace("LYWk",getContext().getStringProperty("LYWk"))
				.replace("Week",getContext().getStringProperty("SelectFiscalWeek")).replace("Department",getContext().getStringProperty("Longlife")));
		System.out.println("DB Value: "+DBLYValTtl);
		
		String LYSalesTotalSF = data.getText("PastWeekLYSalesTotal");	
		DBLYValTtlSF = sql.CLRexecuteQuery(getContext(), LYSalesTotalSF.replace("store",getContext().getStringProperty("store")).replace("Division", getContext().getStringProperty("Division")).replace("LYWk",getContext().getStringProperty("LYWk"))
				.replace("Week",getContext().getStringProperty("SelectFiscalWeek")).replace("Department",getContext().getStringProperty("SeaFoodDeli")));
		System.out.println("DB Value: "+DBLYValTtlSF);
		}
		if (WeekValue.contains("Current") ) {
			String LYSalesTotal = data.getText("CurrentWeekLYSalesTotal");	
			DBLYValTtl = sql.CLRexecuteQuery(getContext(), LYSalesTotal.replace("store",getContext().getStringProperty("store")).replace("Division", getContext().getStringProperty("Division")).replace("LYWk",getContext().getStringProperty("LYWk"))
					.replace("Week",getContext().getStringProperty("SelectFiscalWeek")).replace("Department",getContext().getStringProperty("Longlife")));
			System.out.println("DB Value: "+DBLYValTtl);
			
			String LYSalesTotalSF = data.getText("CurrentWeekLYSalesTotal");	
			DBLYValTtlSF = sql.CLRexecuteQuery(getContext(), LYSalesTotalSF.replace("store",getContext().getStringProperty("store")).replace("Division", getContext().getStringProperty("Division")).replace("LYWk",getContext().getStringProperty("LYWk"))
					.replace("Week",getContext().getStringProperty("SelectFiscalWeek")).replace("Department",getContext().getStringProperty("SeaFoodDeli")));
			System.out.println("DB Value: "+DBLYValTtlSF);
			}
			LYValTtl = pageDailyTradingStatementStorePO.CombinedDayWiseUITotal("LLSales","LYSales",objDailyTradingStatementObjects);		
			LYValTtlSF = pageDailyTradingStatementStorePO.CombinedDayWiseUITotal("SFSales","LYSales",objDailyTradingStatementObjects);		
			
			
				pageDailyTradingStatementStorePO.CompareValueDecimalTotal(extentTest,DBLYValTtl,LYValTtl); 	
		pageDailyTradingStatementStorePO.CompareValueDecimalTotal(extentTest,DBLYValTtlSF,LYValTtlSF); 	
		
	
		//Week to Date - Planned Sales
		Report_AddStep("testcase", " PLANNED SALES - WEEK WISE DATA", "", "", "");
		extentTest = report.startTest("Planned Sales - Week Wise");
		
		String PlannedSalesWkToDtTotal = data.getText("PlannedSalesWkToDtTotal");	
		DBPlndValTtlWkToDt = sql.CLRexecuteQuery(getContext(), PlannedSalesWkToDtTotal.replace("store",getContext().getStringProperty("store")).replace("Division", getContext().getStringProperty("Division")).replace("Week",getContext().getStringProperty("SelectFiscalWeek"))
				.replace("Department",getContext().getStringProperty("Longlife")));
		System.out.println("DB Value: "+DBPlndValTtlWkToDt); 
		
		String PlannedSalesWkToDtTotalSF = data.getText("PlannedSalesWkToDtTotal");	
		DBPlndValTtlWkToDtSF = sql.CLRexecuteQuery(getContext(), PlannedSalesWkToDtTotalSF.replace("store",getContext().getStringProperty("store")).replace("Division", getContext().getStringProperty("Division")).replace("Week",getContext().getStringProperty("SelectFiscalWeek"))
				.replace("Department",getContext().getStringProperty("SeaFoodDeli")));
		System.out.println("DB Value: "+DBPlndValTtlWkToDtSF); 
		
		
			PlndValTtlWkToDt = pageDailyTradingStatementStorePO.CombinedWeekToDateTotal("LLSales",objDailyTradingStatementObjects, objDailyTradingStatementObjects.WeekToDatePlndSales);	
			PlndValTtlWkToDtSF = pageDailyTradingStatementStorePO.CombinedWeekToDateTotal("SFSales",objDailyTradingStatementObjects, objDailyTradingStatementObjects.WeekToDatePlndSales);
				
		
		pageMonthlyPlanningPO.CompareValues(extentTest,DBPlndValTtlWkToDt,PlndValTtlWkToDt); 
		pageMonthlyPlanningPO.CompareValues(extentTest,DBPlndValTtlWkToDtSF,PlndValTtlWkToDtSF); 
		
		
		//Week to Date - Actual Sales
		Report_AddStep("testcase", " ACTUAL SALES - WEEK TO DATE DATA", "", "", "");
		extentTest = report.startTest("Actual Sales - Week to Date");
		
		if (WeekValue.contains("Past") ) {
		String ActualSalesWkToDtTotal = data.getText("PastWeekActualSalesWkToDtTotal");	
		DBActlTtlWkToDt = sql.CLRexecuteQuery(getContext(), ActualSalesWkToDtTotal.replace("store",getContext().getStringProperty("store")).replace("Division", getContext().getStringProperty("Division")).replace("Week",getContext().getStringProperty("SelectFiscalWeek"))
				.replace("Department",getContext().getStringProperty("Longlife")));
		System.out.println("DB Value: "+DBActlTtlWkToDt);
		
		String ActualSalesWkToDtTotalSF = data.getText("PastWeekActualSalesWkToDtTotal");	
		DBActlTtlWkToDtSF = sql.CLRexecuteQuery(getContext(), ActualSalesWkToDtTotalSF.replace("store",getContext().getStringProperty("store")).replace("Division", getContext().getStringProperty("Division")).replace("Week",getContext().getStringProperty("SelectFiscalWeek"))
				.replace("Department",getContext().getStringProperty("SeaFoodDeli")));
		System.out.println("DB Value: "+DBActlTtlWkToDtSF);
		}
		if (WeekValue.contains("Current") ) {
			String ActualSalesWkToDtTotal = data.getText("CurrentWeekActualSalesWkToDtTotal");	
			DBActlTtlWkToDt = sql.CLRexecuteQuery(getContext(), ActualSalesWkToDtTotal.replace("store",getContext().getStringProperty("store")).replace("Division", getContext().getStringProperty("Division")).replace("Week",getContext().getStringProperty("SelectFiscalWeek"))
					.replace("Department",getContext().getStringProperty("Longlife")));
			System.out.println("DB Value: "+DBActlTtlWkToDt);
			
			String ActualSalesWkToDtTotalSF = data.getText("CurrentWeekActualSalesWkToDtTotal");	
			DBActlTtlWkToDtSF = sql.CLRexecuteQuery(getContext(), ActualSalesWkToDtTotalSF.replace("store",getContext().getStringProperty("store")).replace("Division", getContext().getStringProperty("Division")).replace("Week",getContext().getStringProperty("SelectFiscalWeek"))
					.replace("Department",getContext().getStringProperty("SeaFoodDeli")));
			System.out.println("DB Value: "+DBActlTtlWkToDtSF);
			}
		
			ActlValTtlWkToDt = pageDailyTradingStatementStorePO.CombinedWeekToDateTotal("LLSales",objDailyTradingStatementObjects, objDailyTradingStatementObjects.WeekToDateActlSales);	
			ActlValTtlWkToDtSF = pageDailyTradingStatementStorePO.CombinedWeekToDateTotal("SFSales",objDailyTradingStatementObjects, objDailyTradingStatementObjects.WeekToDateActlSales);
			
				
		pageMonthlyPlanningPO.CompareValues(extentTest,DBActlTtlWkToDt,ActlValTtlWkToDt); 
		pageMonthlyPlanningPO.CompareValues(extentTest,DBActlTtlWkToDtSF,ActlValTtlWkToDtSF); 
		
	
		//Week to Date - LY Sales
		Report_AddStep("testcase", " LY SALES - WEEK WISE DATA", "", "", "");
		extentTest = report.startTest("LY Sales - Week to Date Wise");
		if (WeekValue.contains("Past") ) {
		String LYSalesWkToDtTotal = data.getText("PastWeekLYSalesWkToDtTotal");	
		DBLYValTtlWkToDt = sql.CLRexecuteQuery(getContext(), LYSalesWkToDtTotal.replace("store",getContext().getStringProperty("store")).replace("Division", getContext().getStringProperty("Division")).replace("LYWk",getContext().getStringProperty("LYWk"))
				.replace("Week",getContext().getStringProperty("SelectFiscalWeek")).replace("Department",getContext().getStringProperty("Longlife")));
		System.out.println("DB Value: "+DBLYValTtlWkToDt);
		
		String LYSalesWkToDtTotalSF = data.getText("PastWeekLYSalesWkToDtTotal");	
		DBLYValTtlWkToDtSF = sql.CLRexecuteQuery(getContext(), LYSalesWkToDtTotalSF.replace("store",getContext().getStringProperty("store")).replace("Division", getContext().getStringProperty("Division")).replace("LYWk",getContext().getStringProperty("LYWk"))
				.replace("Week",getContext().getStringProperty("SelectFiscalWeek")).replace("Department",getContext().getStringProperty("SeaFoodDeli")));
		System.out.println("DB Value: "+DBLYValTtlWkToDtSF);
		}
		
		if (WeekValue.contains("Current") ) {
			String LYSalesWkToDtTotal = data.getText("CurrentWeekLYSalesWkToDtTotal");	
			DBLYValTtlWkToDt = sql.CLRexecuteQuery(getContext(), LYSalesWkToDtTotal.replace("store",getContext().getStringProperty("store")).replace("Division", getContext().getStringProperty("Division")).replace("LYWk",getContext().getStringProperty("LYWk"))
					.replace("Week",getContext().getStringProperty("SelectFiscalWeek")).replace("Department",getContext().getStringProperty("Longlife")));
			System.out.println("DB Value: "+DBLYValTtlWkToDt);
			
			String LYSalesWkToDtTotalSF = data.getText("CurrentWeekLYSalesWkToDtTotal");	
			DBLYValTtlWkToDtSF = sql.CLRexecuteQuery(getContext(), LYSalesWkToDtTotalSF.replace("store",getContext().getStringProperty("store")).replace("Division", getContext().getStringProperty("Division")).replace("LYWk",getContext().getStringProperty("LYWk"))
					.replace("Week",getContext().getStringProperty("SelectFiscalWeek")).replace("Department",getContext().getStringProperty("SeaFoodDeli")));
			System.out.println("DB Value: "+DBLYValTtlWkToDtSF);
			}
		
			LYValTtlWkToDt = pageDailyTradingStatementStorePO.CombinedWeekToDateTotal("LLSales",objDailyTradingStatementObjects, objDailyTradingStatementObjects.WeekToDateLYSales);
			LYValTtlWkToDtSF = pageDailyTradingStatementStorePO.CombinedWeekToDateTotal("SFSales",objDailyTradingStatementObjects, objDailyTradingStatementObjects.WeekToDateLYSales);
				
		
		pageMonthlyPlanningPO.CompareValueDecimalFields(extentTest,DBLYValTtlWkToDt,LYValTtlWkToDt);	
		pageMonthlyPlanningPO.CompareValueDecimalFields(extentTest,DBLYValTtlWkToDtSF,LYValTtlWkToDtSF);
	
		
		//Week to Date - Variance Sales
		Report_AddStep("testcase", " VARIANCE SALES - WEEK TO DATE DATA", "", "", "");
		extentTest = report.startTest("Variance Sales - Week to Date");
		if (WeekValue.contains("Past") ) {
		String VarSalesWkToDtTotal = data.getText("PastWeekVarSalesWkToDtTotal");	
		DBVarWkToDtTtl = sql.CLRexecuteQuery(getContext(), VarSalesWkToDtTotal.replace("store",getContext().getStringProperty("store")).replace("Division", getContext().getStringProperty("Division")).replace("Week",getContext().getStringProperty("SelectFiscalWeek"))
				.replace("Department",getContext().getStringProperty("Longlife")));
		System.out.println("DB Value: "+DBVarWkToDtTtl);
		
		String VarSalesWkToDtTotalSF = data.getText("PastWeekVarSalesWkToDtTotal");	
		DBVarWkToDtTtlSF = sql.CLRexecuteQuery(getContext(), VarSalesWkToDtTotalSF.replace("store",getContext().getStringProperty("store")).replace("Division", getContext().getStringProperty("Division")).replace("Week",getContext().getStringProperty("SelectFiscalWeek"))
				.replace("Department",getContext().getStringProperty("SeaFoodDeli")));
		System.out.println("DB Value: "+DBVarWkToDtTtlSF);
		}
		
		if (WeekValue.contains("Current") ) {
			String VarSalesWkToDtTotal = data.getText("CurrentWeekVarSalesWkToDtTotal");	
			DBVarWkToDtTtl = sql.CLRexecuteQuery(getContext(), VarSalesWkToDtTotal.replace("store",getContext().getStringProperty("store")).replace("Division", getContext().getStringProperty("Division")).replace("Week",getContext().getStringProperty("SelectFiscalWeek"))
					.replace("Department",getContext().getStringProperty("Longlife")));
			System.out.println("DB Value: "+DBVarWkToDtTtl);
			
			String VarSalesWkToDtTotalSF = data.getText("CurrentWeekVarSalesWkToDtTotal");	
			DBVarWkToDtTtlSF = sql.CLRexecuteQuery(getContext(), VarSalesWkToDtTotalSF.replace("store",getContext().getStringProperty("store")).replace("Division", getContext().getStringProperty("Division")).replace("Week",getContext().getStringProperty("SelectFiscalWeek"))
					.replace("Department",getContext().getStringProperty("SeaFoodDeli")));
			System.out.println("DB Value: "+DBVarWkToDtTtlSF);
			}
			
			VarTtlWkToDt = pageDailyTradingStatementStorePO.CombinedWeekToDateTotal("LLSales",objDailyTradingStatementObjects, objDailyTradingStatementObjects.WeekToDateVarSales);	
			VarTtlWkToDtSF = pageDailyTradingStatementStorePO.CombinedWeekToDateTotal("SFSales",objDailyTradingStatementObjects, objDailyTradingStatementObjects.WeekToDateVarSales);
		
		
		pageMonthlyPlanningPO.CompareValues(extentTest,DBVarWkToDtTtl,VarTtlWkToDt);
		pageMonthlyPlanningPO.CompareValues(extentTest,DBVarWkToDtTtlSF,VarTtlWkToDtSF);	
			
	}	
 
 
//////////// SMS ///////////////
@Test(description = "Daily Trading Statement - Store, SMS Values",priority=2)
public void SMSColumnValidation() throws Exception
{
		String DTSStore = "Daily Trading Statement - Store, SMS Values";
		extentTest = report.startTest("Daily Trading Statement - Store, SMS Values");		
		pageWeeklyPlanningPO = new WeeklyPlanningStorePage().createPage(getDriver(), getContext(), log, status, data,getReport(), getReportLogger(), WeeklyPlanningStorePage.class);
		pageDailyTradingStatementStorePO = new MonthlyPlanningGroupPage().createPage(getDriver(), getContext(), log, status, data,getReport(), getReportLogger(), DailyTradingStatementStorePage.class);
		TestStatus status = getTestStatus();
		System.out.println("Entering into TestCase : DTS Store - SMS View");
		WeekValue = getContext().getStringProperty("WeekToTest");
		
		//Planned SMS
		Report_AddStep("testcase", " PLANNED SMS - WEEK WISE DATA", "", "", "");
		extentTest = report.startTest("Planned SMS - Day Wise");
		
		String PlannedSMSTotal = data.getText("PlannedSMSTotal");	
		DBPlndValTtl = sql.CLRexecuteQuery(getContext(), PlannedSMSTotal.replace("store",getContext().getStringProperty("store")).replace("Division", getContext().getStringProperty("Division")).replace("Week",getContext().getStringProperty("SelectFiscalWeek"))
				.replace("Department",getContext().getStringProperty("Longlife")));
		System.out.println("DB Value: "+DBPlndValTtl);
		
		String PlannedSMSTotalSF = data.getText("PlannedSMSTotal");	
		DBPlndValTtlSF = sql.CLRexecuteQuery(getContext(), PlannedSMSTotalSF.replace("store",getContext().getStringProperty("store")).replace("Division", getContext().getStringProperty("Division")).replace("Week",getContext().getStringProperty("SelectFiscalWeek"))
				.replace("Department",getContext().getStringProperty("SeaFoodDeli")));
		System.out.println("DB Value: "+DBPlndValTtlSF);
		
	
		
		PlndValTtl = pageDailyTradingStatementStorePO.CombinedDayWiseUITotal("LLSMS","PlannedValue",objDailyTradingStatementObjects);
		PlndValTtlSF = pageDailyTradingStatementStorePO.CombinedDayWiseUITotal("SFSMS","PlannedValue",objDailyTradingStatementObjects);	
	
			
			
		pageDailyTradingStatementStorePO.CompareValueDecimalTotal(extentTest,DBPlndValTtl,PlndValTtl);
		pageDailyTradingStatementStorePO.CompareValueDecimalTotal(extentTest,DBPlndValTtlSF,PlndValTtlSF);
		
		//Actual SMS
		Report_AddStep("testcase", " ACTUAL SMS- WEEK WISE DATA", "", "", "");
		extentTest = report.startTest("Actual SMS - Day Wise");
		if (WeekValue.contains("Past") ) {
		String ActualSMSTotal = data.getText("PastWeekActualSMSTotal");	
		DBActlValTtl = sql.CLRexecuteQuery(getContext(), ActualSMSTotal.replace("store",getContext().getStringProperty("store")).replace("Division", getContext().getStringProperty("Division")).replace("FinWk",getContext().getStringProperty("FinWk"))
				.replace("Year",getContext().getStringProperty("FinYr")).replace("Department",getContext().getStringProperty("Longlife")));
		System.out.println("DB Value: "+DBActlValTtl);
		
		String ActualSMSTotalSF = data.getText("PastWeekActualSMSTotalSF");	
		DBActlValTtlSF = sql.CLRexecuteQuery(getContext(), ActualSMSTotalSF.replace("store",getContext().getStringProperty("store")).replace("Division", getContext().getStringProperty("Division")).replace("FinWk",getContext().getStringProperty("FinWk"))
				.replace("Year",getContext().getStringProperty("FinYr")).replace("Department",getContext().getStringProperty("SeaFoodDeli")));
		System.out.println("DB Value: "+DBActlValTtlSF);
		}
		if (WeekValue.contains("Current") ) {
			String ActualSMSTotal = data.getText("CurrentWeekActualSMSTotal");	
			DBActlValTtl = sql.CLRexecuteQuery(getContext(), ActualSMSTotal.replace("store",getContext().getStringProperty("store")).replace("Division", getContext().getStringProperty("Division")).replace("FinWk",getContext().getStringProperty("FinWk"))
					.replace("Year",getContext().getStringProperty("FinYr")).replace("Department",getContext().getStringProperty("Longlife")));
			System.out.println("DB Value: "+DBActlValTtl);
			
			String ActualSMSTotalSF = data.getText("CurrentWeekActualSMSTotalSF");	
			DBActlValTtlSF = sql.CLRexecuteQuery(getContext(), ActualSMSTotalSF.replace("store",getContext().getStringProperty("store")).replace("Division", getContext().getStringProperty("Division")).replace("FinWk",getContext().getStringProperty("FinWk"))
					.replace("Year",getContext().getStringProperty("FinYr")).replace("Department",getContext().getStringProperty("SeaFoodDeli")));
			System.out.println("DB Value: "+DBActlValTtlSF);
			}
		
			ActlValTtl = pageDailyTradingStatementStorePO.CombinedDayWiseUITotal("LLSMS","ActualValue",objDailyTradingStatementObjects);
			ActlValTtlSF = pageDailyTradingStatementStorePO.CombinedDayWiseUITotal("SFSMS","ActualValue",objDailyTradingStatementObjects);	
			
			
		
		pageDailyTradingStatementStorePO.CompareValueDecimalTotal(extentTest,DBActlValTtl,ActlValTtl);
		pageDailyTradingStatementStorePO.CompareValueDecimalTotal(extentTest,DBActlValTtlSF,ActlValTtlSF);
		
		
		//Week to Date - Planned SMS
		Report_AddStep("testcase", " PLANNED SMS - WEEK TO DATE  DATA", "", "", "");
		extentTest = report.startTest("Planned SMS - Week to Date");
		
		String PlannedSMSWkToDtTotal = data.getText("PlannedSMSWkToDtTotal");	
		DBPlndValTtlWkToDt = sql.CLRexecuteQuery(getContext(), PlannedSMSWkToDtTotal.replace("store",getContext().getStringProperty("store")).replace("Division", getContext().getStringProperty("Division")).replace("Week",getContext().getStringProperty("SelectFiscalWeek"))
				.replace("Department",getContext().getStringProperty("Longlife")));
		System.out.println("DB Value: "+DBPlndValTtlWkToDt); 
		
		String PlannedSMSWkToDtTotalSF = data.getText("PlannedSMSWkToDtTotal");	
		DBPlndValTtlWkToDtSF = sql.CLRexecuteQuery(getContext(), PlannedSMSWkToDtTotalSF.replace("store",getContext().getStringProperty("store")).replace("Division", getContext().getStringProperty("Division")).replace("Week",getContext().getStringProperty("SelectFiscalWeek"))
				.replace("Department",getContext().getStringProperty("SeaFoodDeli")));
		System.out.println("DB Value: "+DBPlndValTtlWkToDtSF); 
		
		
			PlndValTtlWkToDt = pageDailyTradingStatementStorePO.CombinedWeekToDateTotal("LLSMS",objDailyTradingStatementObjects, objDailyTradingStatementObjects.WeekToDatePlndValue);
			PlndValTtlWkToDtSF = pageDailyTradingStatementStorePO.CombinedWeekToDateTotal("SFSMS",objDailyTradingStatementObjects, objDailyTradingStatementObjects.WeekToDatePlndValue);
		
			
		
		pageMonthlyPlanningPO.CompareValueDecimalFields(extentTest,DBPlndValTtlWkToDt,PlndValTtlWkToDt); 	
		pageMonthlyPlanningPO.CompareValueDecimalFields(extentTest,DBPlndValTtlWkToDtSF,PlndValTtlWkToDtSF); 	
		
		//Week to Date - Actual SMS
		Report_AddStep("testcase", " ACTUAL SMS - WEEK TO DATE DATA", "", "", "");
		extentTest = report.startTest("Actual SMS - Week to Date");
		
		if (WeekValue.contains("Past") ) {
		String ActualSMSWkToDtTotal = data.getText("PastWeekActualSMSWkToDtTotal");	
		DBActlValTtlWkToDt = sql.CLRexecuteQuery(getContext(), ActualSMSWkToDtTotal.replace("store",getContext().getStringProperty("store")).replace("Division", getContext().getStringProperty("Division")).replace("FinWk",getContext().getStringProperty("FinWk"))
				.replace("Year",getContext().getStringProperty("FinYr")).replace("Department",getContext().getStringProperty("Longlife")));
		System.out.println("DB Value: "+DBActlValTtlWkToDt); 
		
		String ActualSMSWkToDtTotalSF = data.getText("PastWeekActualSMSWkToDtTotalSF");	
		DBActlValTtlWkToDtSF = sql.CLRexecuteQuery(getContext(), ActualSMSWkToDtTotalSF.replace("store",getContext().getStringProperty("store")).replace("Division", getContext().getStringProperty("Division")).replace("FinWk",getContext().getStringProperty("FinWk"))
				.replace("Year",getContext().getStringProperty("FinYr")).replace("Department",getContext().getStringProperty("SeaFoodDeli")));
		System.out.println("DB Value: "+DBActlValTtlWkToDtSF); 
		}
		if (WeekValue.contains("Current") ) {
			String ActualSMSWkToDtTotal = data.getText("CurrentWeekActualSMSWkToDtTotal");	
			DBActlValTtlWkToDt = sql.CLRexecuteQuery(getContext(), ActualSMSWkToDtTotal.replace("store",getContext().getStringProperty("store")).replace("Division", getContext().getStringProperty("Division")).replace("FinWk",getContext().getStringProperty("FinWk"))
					.replace("Year",getContext().getStringProperty("FinYr")).replace("Department",getContext().getStringProperty("Longlife")));
			System.out.println("DB Value: "+DBActlValTtlWkToDt); 
			
			String ActualSMSWkToDtTotalSF = data.getText("CurrentWeekActualSMSWkToDtTotalSF");	
			DBActlValTtlWkToDtSF = sql.CLRexecuteQuery(getContext(), ActualSMSWkToDtTotalSF.replace("store",getContext().getStringProperty("store")).replace("Division", getContext().getStringProperty("Division")).replace("FinWk",getContext().getStringProperty("FinWk"))
					.replace("Year",getContext().getStringProperty("FinYr")).replace("Department",getContext().getStringProperty("SeaFoodDeli")));
			System.out.println("DB Value: "+DBActlValTtlWkToDtSF); 
			}
		
			ActlValTtlWkToDt = pageDailyTradingStatementStorePO.CombinedWeekToDateTotal("LLSMS",objDailyTradingStatementObjects, objDailyTradingStatementObjects.WeekToDateActlValue);
			ActlValTtlWkToDtSF = pageDailyTradingStatementStorePO.CombinedWeekToDateTotal("SFSMS",objDailyTradingStatementObjects, objDailyTradingStatementObjects.WeekToDateActlValue);
		
		
		pageMonthlyPlanningPO.CompareValueDecimalFields(extentTest,DBActlValTtlWkToDt,ActlValTtlWkToDt); 	
		pageMonthlyPlanningPO.CompareValueDecimalFields(extentTest,DBActlValTtlWkToDtSF,ActlValTtlWkToDtSF); 	
	
}  
 
//////////// Hours ///////////////
@Test(description = "Daily Trading Statement - Store, Hours Values",priority=3)
public void HoursColumnValidation() throws Exception
{
		String DTSStore = "Daily Trading Statement - Store, Hours Values";
		extentTest = report.startTest("Daily Trading Statement - Store, Hours Values");		
		pageWeeklyPlanningPO = new WeeklyPlanningStorePage().createPage(getDriver(), getContext(), log, status, data,getReport(), getReportLogger(), WeeklyPlanningStorePage.class);
		pageDailyTradingStatementStorePO = new MonthlyPlanningGroupPage().createPage(getDriver(), getContext(), log, status, data,getReport(), getReportLogger(), DailyTradingStatementStorePage.class);
		TestStatus status = getTestStatus();
		System.out.println("Entering into TestCase : DTS Store - SMS View");
		WeekValue = getContext().getStringProperty("WeekToTest");
		
		//Planned Hours
		Report_AddStep("testcase", " PLANNED hOURS - WEEK WISE DATA", "", "", "");
		extentTest = report.startTest("Planned Hours - Day Wise");
		
		String PlannedHoursTotal = data.getText("PlannedHoursTotal");	
		DBPlndValTtl = sql.CLRexecuteQuery(getContext(), PlannedHoursTotal.replace("store",getContext().getStringProperty("store")).replace("Division", getContext().getStringProperty("Division")).replace("Week",getContext().getStringProperty("SelectFiscalWeek"))
				.replace("Department",getContext().getStringProperty("Longlife")));
		System.out.println("DB Value: "+DBPlndValTtl); 
		
		String PlannedHoursTotalSF = data.getText("PlannedHoursTotal");	
		DBPlndValTtlSF = sql.CLRexecuteQuery(getContext(), PlannedHoursTotalSF.replace("store",getContext().getStringProperty("store")).replace("Division", getContext().getStringProperty("Division")).replace("Week",getContext().getStringProperty("SelectFiscalWeek"))
				.replace("Department",getContext().getStringProperty("SeaFoodDeli")));
		System.out.println("DB Value: "+DBPlndValTtlSF); 
		
		
			PlndValTtl = pageDailyTradingStatementStorePO.CombinedDayWiseUITotal("LLHours","PlannedValue",objDailyTradingStatementObjects);		
			PlndValTtlSF = pageDailyTradingStatementStorePO.CombinedDayWiseUITotal("SFHours","PlannedValue",objDailyTradingStatementObjects);
		
		pageDailyTradingStatementStorePO.CompareValueDecimalTotal(extentTest,DBPlndValTtl,PlndValTtl);	
		pageDailyTradingStatementStorePO.CompareValueDecimalTotal(extentTest,DBPlndValTtlSF,PlndValTtlSF);	
		
		
		//Actual Hours
		Report_AddStep("testcase", " ACTUAL HOURS - WEEK WISE DATA", "", "", "");
		extentTest = report.startTest("Actual Hours - Day Wise");
		if (WeekValue.contains("Past") ) {
		String ActualHoursTotal = data.getText("PastWeekActualHoursTotal");	
		DBActlTtl = sql.CLRexecuteQuery(getContext(), ActualHoursTotal.replace("store",getContext().getStringProperty("store")).replace("Division", getContext().getStringProperty("Division")).replace("Week",getContext().getStringProperty("SelectFiscalWeek"))
				.replace("Department",getContext().getStringProperty("Longlife")));
		System.out.println("DB Value: "+DBActlTtl);
		
		String ActualHoursTotalSF = data.getText("PastWeekActualHoursTotal");	
		DBActlTtlSF = sql.CLRexecuteQuery(getContext(), ActualHoursTotalSF.replace("store",getContext().getStringProperty("store")).replace("Division", getContext().getStringProperty("Division")).replace("Week",getContext().getStringProperty("SelectFiscalWeek"))
				.replace("Department",getContext().getStringProperty("SeaFoodDeli")));
		System.out.println("DB Value: "+DBActlTtlSF);
		}
		if (WeekValue.contains("Current") ) {
			String ActualHoursTotal = data.getText("CurrentWeekActualHoursTotal");	
			DBActlTtl = sql.CLRexecuteQuery(getContext(), ActualHoursTotal.replace("store",getContext().getStringProperty("store")).replace("Division", getContext().getStringProperty("Division")).replace("Week",getContext().getStringProperty("SelectFiscalWeek"))
					.replace("Department",getContext().getStringProperty("Longlife")));
			System.out.println("DB Value: "+DBActlTtl);
			
			String ActualHoursTotalSF = data.getText("CurrentWeekActualHoursTotal");	
			DBActlTtlSF = sql.CLRexecuteQuery(getContext(), ActualHoursTotalSF.replace("store",getContext().getStringProperty("store")).replace("Division", getContext().getStringProperty("Division")).replace("Week",getContext().getStringProperty("SelectFiscalWeek"))
					.replace("Department",getContext().getStringProperty("SeaFoodDeli")));
			System.out.println("DB Value: "+DBActlTtlSF);
			}
			
			ActlValTtl = pageDailyTradingStatementStorePO.CombinedDayWiseUITotal("LLHours","ActualValue",objDailyTradingStatementObjects);
			ActlValTtlSF = pageDailyTradingStatementStorePO.CombinedDayWiseUITotal("SFHours","ActualValue",objDailyTradingStatementObjects);
		
		pageDailyTradingStatementStorePO.CompareValueDecimalTotal(extentTest,DBActlTtl,ActlValTtl);	
		pageDailyTradingStatementStorePO.CompareValueDecimalTotal(extentTest,DBActlTtlSF,ActlValTtlSF);
		
		
		//Week to Date - Planned Hours
		Report_AddStep("testcase", " PLANNED hOURS - WEEK TO DATE DATA", "", "", "");
		extentTest = report.startTest("Planned Hours - Week to Date");
		
		String PlannedHoursWkToDtTotal = data.getText("PlannedHoursWkToDtTotal");	
		DBPlndValTtlWkToDt = sql.CLRexecuteQuery(getContext(), PlannedHoursWkToDtTotal.replace("store",getContext().getStringProperty("store")).replace("Division", getContext().getStringProperty("Division")).replace("Week",getContext().getStringProperty("SelectFiscalWeek"))
				.replace("Department",getContext().getStringProperty("Longlife")));
		System.out.println("DB Value: "+DBPlndValTtlWkToDt); 
		
		String PlannedHoursWkToDtTotalSF = data.getText("PlannedHoursWkToDtTotal");	
		DBPlndValTtlWkToDtSF = sql.CLRexecuteQuery(getContext(), PlannedHoursWkToDtTotalSF.replace("store",getContext().getStringProperty("store")).replace("Division", getContext().getStringProperty("Division")).replace("Week",getContext().getStringProperty("SelectFiscalWeek"))
				.replace("Department",getContext().getStringProperty("SeaFoodDeli")));
		System.out.println("DB Value: "+DBPlndValTtlWkToDtSF); 
		
		
			PlndValTtlWkToDt = pageDailyTradingStatementStorePO.CombinedWeekToDateTotal("LLHours",objDailyTradingStatementObjects, objDailyTradingStatementObjects.WeekToDatePlndValue);
			PlndValTtlWkToDtSF = pageDailyTradingStatementStorePO.CombinedWeekToDateTotal("SFHours",objDailyTradingStatementObjects, objDailyTradingStatementObjects.WeekToDatePlndValue);
		
		pageMonthlyPlanningPO.CompareValueDecimalFields(extentTest,DBPlndValTtlWkToDt,PlndValTtlWkToDt);	
		pageMonthlyPlanningPO.CompareValueDecimalFields(extentTest,DBPlndValTtlWkToDtSF,PlndValTtlWkToDtSF);
		
		
		//Week to Date - Actual Hours
		Report_AddStep("testcase", " Actual HOURS - WEEK TO DATE DATA", "", "", "");
		extentTest = report.startTest("Actual Hours - Week to Date");
		
		if(WeekValue.contains("Past") )
		{
			String ActualHoursWkToDtTotal = data.getText("PastWeekActualHoursWkToDtTotal");	
			DBActlTtlWkToDt = sql.CLRexecuteQuery(getContext(), ActualHoursWkToDtTotal.replace("store",getContext().getStringProperty("store")).replace("Division", getContext().getStringProperty("Division")).replace("Week",getContext().getStringProperty("SelectFiscalWeek"))
					.replace("Department",getContext().getStringProperty("Longlife")));
			System.out.println("DB Value: "+DBActlTtlWkToDt);
			
			String ActualHoursWkToDtTotalSF = data.getText("PastWeekActualHoursWkToDtTotal");	
			DBActlTtlWkToDtSF = sql.CLRexecuteQuery(getContext(), ActualHoursWkToDtTotalSF.replace("store",getContext().getStringProperty("store")).replace("Division", getContext().getStringProperty("Division")).replace("Week",getContext().getStringProperty("SelectFiscalWeek"))
					.replace("Department",getContext().getStringProperty("SeaFoodDeli")));
			System.out.println("DB Value: "+DBActlTtlWkToDtSF);
		}
		if(WeekValue.contains("Current"))
		{
			String CurrentActualHoursWkToDtTotal = data.getText("CurrentWeekActualHoursWkToDtTotal");	
			DBActlTtlWkToDt = sql.CLRexecuteQuery(getContext(), CurrentActualHoursWkToDtTotal.replace("store",getContext().getStringProperty("store")).replace("Division", getContext().getStringProperty("Division")).replace("Week",getContext().getStringProperty("SelectFiscalWeek"))
					.replace("Department",getContext().getStringProperty("Longlife")));
			System.out.println("DB Value: "+DBActlTtlWkToDt);
			
			String CurrentActualHoursWkToDtTotalSF = data.getText("CurrentWeekActualHoursWkToDtTotal");	
			DBActlTtlWkToDtSF = sql.CLRexecuteQuery(getContext(), CurrentActualHoursWkToDtTotalSF.replace("store",getContext().getStringProperty("store")).replace("Division", getContext().getStringProperty("Division")).replace("Week",getContext().getStringProperty("SelectFiscalWeek"))
					.replace("Department",getContext().getStringProperty("SeaFoodDeli")));
			System.out.println("DB Value: "+DBActlTtlWkToDtSF);
		}
		
		
			ActlValTtlWkToDt = pageDailyTradingStatementStorePO.CombinedWeekToDateTotal("LLHours",objDailyTradingStatementObjects, objDailyTradingStatementObjects.WeekToDateActlValue);
			ActlValTtlWkToDtSF = pageDailyTradingStatementStorePO.CombinedWeekToDateTotal("SFHours",objDailyTradingStatementObjects, objDailyTradingStatementObjects.WeekToDateActlValue);
		
		
		pageMonthlyPlanningPO.CompareValueDecimalFields(extentTest,DBActlTtlWkToDt,ActlValTtlWkToDt);
		pageMonthlyPlanningPO.CompareValueDecimalFields(extentTest,DBActlTtlWkToDtSF,ActlValTtlWkToDtSF);
		
		
		//Week to Date - Variance Hours
		Report_AddStep("testcase", " VARIANCE HOURS - WEEK TO DATE DATA", "", "", "");
		extentTest = report.startTest("Variance Hours - Week to Date");
		
		if(WeekValue.contains("Past") )
		{
			String VarHoursWkToDtTotal = data.getText("PastWeekVarHoursWkToDtTotal");	
			DBVarWkToDtTtl = sql.CLRexecuteQuery(getContext(), VarHoursWkToDtTotal.replace("store",getContext().getStringProperty("store")).replace("Division", getContext().getStringProperty("Division")).replace("Week",getContext().getStringProperty("SelectFiscalWeek"))
					.replace("Department",getContext().getStringProperty("Longlife")));
			System.out.println("DB Value: "+DBVarWkToDtTtl);
			
			String VarHoursWkToDtTotalSF = data.getText("PastWeekVarHoursWkToDtTotal");	
			DBVarWkToDtTtlSF = sql.CLRexecuteQuery(getContext(), VarHoursWkToDtTotalSF.replace("store",getContext().getStringProperty("store")).replace("Division", getContext().getStringProperty("Division")).replace("Week",getContext().getStringProperty("SelectFiscalWeek"))
					.replace("Department",getContext().getStringProperty("SeaFoodDeli")));
			System.out.println("DB Value: "+DBVarWkToDtTtlSF);
		}
		if(WeekValue.contains("Current"))
		{
			String CurrentVarHoursWkToDtTotal = data.getText("CurrentWeekVarHoursWkToDtTotal");	
			DBVarWkToDtTtl = sql.CLRexecuteQuery(getContext(), CurrentVarHoursWkToDtTotal.replace("store",getContext().getStringProperty("store")).replace("Division", getContext().getStringProperty("Division")).replace("Week",getContext().getStringProperty("SelectFiscalWeek"))
					.replace("Department",getContext().getStringProperty("Longlife")));
			System.out.println("DB Value: "+DBVarWkToDtTtl);
			
			String CurrentVarHoursWkToDtTotalSF = data.getText("CurrentWeekVarHoursWkToDtTotal");	
			DBVarWkToDtTtlSF = sql.CLRexecuteQuery(getContext(), CurrentVarHoursWkToDtTotalSF.replace("store",getContext().getStringProperty("store")).replace("Division", getContext().getStringProperty("Division")).replace("Week",getContext().getStringProperty("SelectFiscalWeek"))
					.replace("Department",getContext().getStringProperty("SeaFoodDeli")));
			System.out.println("DB Value: "+DBVarWkToDtTtlSF);
		}
		
		
			VarTtlWkToDt = pageDailyTradingStatementStorePO.CombinedWeekToDateTotal("LLHours",objDailyTradingStatementObjects, objDailyTradingStatementObjects.WeekToDateVarValue);
			VarTtlWkToDtSF = pageDailyTradingStatementStorePO.CombinedWeekToDateTotal("SFHours",objDailyTradingStatementObjects, objDailyTradingStatementObjects.WeekToDateVarValue);
		
		
		pageMonthlyPlanningPO.CompareValueDecimalFields(extentTest,DBVarWkToDtTtl,VarTtlWkToDt);	
		pageMonthlyPlanningPO.CompareValueDecimalFields(extentTest,DBVarWkToDtTtlSF,VarTtlWkToDtSF);	
		
} 

//////////// OR Percentage ///////////////
	@Test(description = "Daily Trading Statement - Store, OR Percentage Values",priority=4)
	public void ORColumnValidation() throws Exception
	{
		String DTSStore = "Daily Trading Statement - Store, OR Percentage Values";
		extentTest = report.startTest("Daily Trading Statement - Store, OR Percentage Values");		
		pageWeeklyPlanningPO = new WeeklyPlanningStorePage().createPage(getDriver(), getContext(), log, status, data,getReport(), getReportLogger(), WeeklyPlanningStorePage.class);
		pageDailyTradingStatementStorePO = new MonthlyPlanningGroupPage().createPage(getDriver(), getContext(), log, status, data,getReport(), getReportLogger(), DailyTradingStatementStorePage.class);
		TestStatus status = getTestStatus();
		System.out.println("Entering into TestCase : DTS Store - OR  View");
		WeekValue = getContext().getStringProperty("WeekToTest");
		
		//Planned OR
		Report_AddStep("testcase", " PLANNED OR - WEEK WISE DATA", "", "", "");
		extentTest = report.startTest("Planned OR - Day wise");
		
		String PlannedORTotal = data.getText("PlannedORTotal");	
		DBPlndValTtl = sql.CLRexecuteQuery(getContext(), PlannedORTotal.replace("store",getContext().getStringProperty("store")).replace("Division", getContext().getStringProperty("Division")).replace("Week",getContext().getStringProperty("SelectFiscalWeek"))
				.replace("Department",getContext().getStringProperty("Longlife")));
		System.out.println("DB Value: "+DBPlndValTtl); 
		
		String PlannedORTotalSF = data.getText("PlannedORTotal");	
		DBPlndValTtlSF = sql.CLRexecuteQuery(getContext(), PlannedORTotalSF.replace("store",getContext().getStringProperty("store")).replace("Division", getContext().getStringProperty("Division")).replace("Week",getContext().getStringProperty("SelectFiscalWeek"))
				.replace("Department",getContext().getStringProperty("SeaFoodDeli")));
		System.out.println("DB Value: "+DBPlndValTtlSF); 
		
		
			PlndValTtl = pageDailyTradingStatementStorePO.CombinedDayWiseUITotal("LLOR","PlannedValueOR",objDailyTradingStatementObjects);
			PlndValTtlSF = pageDailyTradingStatementStorePO.CombinedDayWiseUITotal("SFOR","PlannedValueOR",objDailyTradingStatementObjects);
		
		
		pageDailyTradingStatementStorePO.CompareValueDecimalTotal(extentTest,DBPlndValTtl,PlndValTtl);	
		pageDailyTradingStatementStorePO.CompareValueDecimalTotal(extentTest,DBPlndValTtlSF,PlndValTtlSF);	
		
		//Actual OR
		Report_AddStep("testcase", " ACTUAL OR - WEEK WISE DATA", "", "", "");
		extentTest = report.startTest("Actual OR - Day wise");
				if (WeekValue.contains("Past") ) {
				String ActualORTotal = data.getText("PastWeekActualORTotal");	
				DBActlValTtl = sql.CLRexecuteQuery(getContext(), ActualORTotal.replace("store",getContext().getStringProperty("store")).replace("Division", getContext().getStringProperty("Division")).replace("FinWk",getContext().getStringProperty("FinWk"))
						.replace("Year",getContext().getStringProperty("FinYr")).replace("Department",getContext().getStringProperty("Longlife")).replace("Week",getContext().getStringProperty("SelectFiscalWeek")));
				System.out.println("DB Value: "+DBActlValTtl); 
				
				String ActualORTotalSF = data.getText("PastWeekActualORTotalSF");	
				DBActlValTtlSF = sql.CLRexecuteQuery(getContext(), ActualORTotalSF.replace("store",getContext().getStringProperty("store")).replace("Division", getContext().getStringProperty("Division")).replace("FinWk",getContext().getStringProperty("FinWk"))
						.replace("Year",getContext().getStringProperty("FinYr")).replace("Department",getContext().getStringProperty("SeaFoodDeli")).replace("Week",getContext().getStringProperty("SelectFiscalWeek")));
				System.out.println("DB Value: "+DBActlValTtlSF); 
				}
				
				
				if (WeekValue.contains("Current") ) {
					String ActualORTotal = data.getText("CurrentWeekActualORTotal");	
					DBActlValTtl = sql.CLRexecuteQuery(getContext(), ActualORTotal.replace("store",getContext().getStringProperty("store")).replace("Division", getContext().getStringProperty("Division")).replace("FinWk",getContext().getStringProperty("FinWk"))
							.replace("Year",getContext().getStringProperty("FinYr")).replace("Department",getContext().getStringProperty("Longlife")).replace("Week",getContext().getStringProperty("SelectFiscalWeek")));
					System.out.println("DB Value: "+DBActlValTtl); 
					
					String ActualORTotalSF = data.getText("CurrentWeekActualORTotalSF");	
					DBActlValTtlSF = sql.CLRexecuteQuery(getContext(), ActualORTotalSF.replace("store",getContext().getStringProperty("store")).replace("Division", getContext().getStringProperty("Division")).replace("FinWk",getContext().getStringProperty("FinWk"))
							.replace("Year",getContext().getStringProperty("FinYr")).replace("Department",getContext().getStringProperty("SeaFoodDeli")).replace("Week",getContext().getStringProperty("SelectFiscalWeek")));
					System.out.println("DB Value: "+DBActlValTtlSF); 
					}
					
					ActlValTtl = pageDailyTradingStatementStorePO.CombinedDayWiseUITotal("LLOR","ActualValueOR",objDailyTradingStatementObjects);
					ActlValTtlSF = pageDailyTradingStatementStorePO.CombinedDayWiseUITotal("SFOR","ActualValueOR",objDailyTradingStatementObjects);
			
				
				pageDailyTradingStatementStorePO.CompareValueDecimalTotal(extentTest,DBActlValTtl,ActlValTtl);	
				pageDailyTradingStatementStorePO.CompareValueDecimalTotal(extentTest,DBActlValTtlSF,ActlValTtlSF);	
				
		//Week to Date - Planned OR
				Report_AddStep("testcase", " PLANNED OR - WEEK TO DATE DATA", "", "", "");
				extentTest = report.startTest("Planned OR - Week to Date");
		
		String PlannedORWkToDtTotal = data.getText("PlannedORWkToDtTotal");	
		DBPlndValTtlWkToDt = sql.CLRexecuteQuery(getContext(), PlannedORWkToDtTotal.replace("store",getContext().getStringProperty("store")).replace("Division", getContext().getStringProperty("Division")).replace("Week",getContext().getStringProperty("SelectFiscalWeek"))
				.replace("Department",getContext().getStringProperty("Longlife")));
		System.out.println("DB Value: "+DBPlndValTtlWkToDt); 
		
		String PlannedORWkToDtTotalSF = data.getText("PlannedORWkToDtTotal");	
		DBPlndValTtlWkToDtSF = sql.CLRexecuteQuery(getContext(), PlannedORWkToDtTotalSF.replace("store",getContext().getStringProperty("store")).replace("Division", getContext().getStringProperty("Division")).replace("Week",getContext().getStringProperty("SelectFiscalWeek"))
				.replace("Department",getContext().getStringProperty("SeaFoodDeli")));
		System.out.println("DB Value: "+DBPlndValTtlWkToDtSF); 
		
			PlndValTtlWkToDt = pageDailyTradingStatementStorePO.CombinedWeekToDateTotal("LLOR",objDailyTradingStatementObjects, objDailyTradingStatementObjects.WeekToDatePlndValueOR);
			PlndValTtlWkToDtSF = pageDailyTradingStatementStorePO.CombinedWeekToDateTotal("SFOR",objDailyTradingStatementObjects, objDailyTradingStatementObjects.WeekToDatePlndValueOR);
		
		pageMonthlyPlanningPO.CompareValueDecimalFields(extentTest,DBPlndValTtlWkToDt,PlndValTtlWkToDt); 
		pageMonthlyPlanningPO.CompareValueDecimalFields(extentTest,DBPlndValTtlWkToDtSF,PlndValTtlWkToDtSF); 
		
		//Week to Date - Actual OR
		Report_AddStep("testcase", " ACTUAL OR - WEEK TO DATE DATA", "", "", "");
		extentTest = report.startTest("Actual OR - Week to Date");
		
				if (WeekValue.contains("Past") ) {
				String ActualORWkToDtTotal = data.getText("PastWeekActualORWkToDtTotal");	
				DBActlValTtlWkToDt = sql.CLRexecuteQuery(getContext(), ActualORWkToDtTotal.replace("store",getContext().getStringProperty("store")).replace("Division", getContext().getStringProperty("Division")).replace("FinWk",getContext().getStringProperty("FinWk"))
						.replace("Year",getContext().getStringProperty("FinYr")).replace("Department",getContext().getStringProperty("Longlife")).replace("Week",getContext().getStringProperty("SelectFiscalWeek")));
				System.out.println("DB Value: "+DBActlValTtlWkToDt); 
				
				String ActualORWkToDtTotalSF = data.getText("PastWeekActualORWkToDtTotalSF");	
				DBActlValTtlWkToDtSF = sql.CLRexecuteQuery(getContext(), ActualORWkToDtTotalSF.replace("store",getContext().getStringProperty("store")).replace("Division", getContext().getStringProperty("Division")).replace("FinWk",getContext().getStringProperty("FinWk"))
						.replace("Year",getContext().getStringProperty("FinYr")).replace("Department",getContext().getStringProperty("SeaFoodDeli")).replace("Week",getContext().getStringProperty("SelectFiscalWeek")));
				System.out.println("DB Value: "+DBActlValTtlWkToDtSF); 
				
				}
				if (WeekValue.contains("Current") ) {
					String ActualORWkToDtTotal = data.getText("CurrentWeekActualORWkToDtTotal");	
					DBActlValTtlWkToDt = sql.CLRexecuteQuery(getContext(), ActualORWkToDtTotal.replace("store",getContext().getStringProperty("store")).replace("Division", getContext().getStringProperty("Division")).replace("FinWk",getContext().getStringProperty("FinWk"))
							.replace("Year",getContext().getStringProperty("FinYr")).replace("Department",getContext().getStringProperty("Longlife")).replace("Week",getContext().getStringProperty("SelectFiscalWeek")));
					System.out.println("DB Value: "+DBActlValTtlWkToDt); 
					
					String ActualORWkToDtTotalSF = data.getText("CurrentWeekActualORWkToDtTotalSF");	
					DBActlValTtlWkToDtSF = sql.CLRexecuteQuery(getContext(), ActualORWkToDtTotalSF.replace("store",getContext().getStringProperty("store")).replace("Division", getContext().getStringProperty("Division")).replace("FinWk",getContext().getStringProperty("FinWk"))
							.replace("Year",getContext().getStringProperty("FinYr")).replace("Department",getContext().getStringProperty("SeaFoodDeli")).replace("Week",getContext().getStringProperty("SelectFiscalWeek")));
					System.out.println("DB Value: "+DBActlValTtlWkToDtSF); 
					
					}
					ActlValTtlWkToDt = pageDailyTradingStatementStorePO.CombinedWeekToDateTotal("LLOR",objDailyTradingStatementObjects, objDailyTradingStatementObjects.WeekToDateActlValueOR);
					ActlValTtlWkToDtSF = pageDailyTradingStatementStorePO.CombinedWeekToDateTotal("SFOR",objDailyTradingStatementObjects, objDailyTradingStatementObjects.WeekToDateActlValueOR);
				
				pageMonthlyPlanningPO.CompareValueDecimalFields(extentTest,DBActlValTtlWkToDt,ActlValTtlWkToDt); 
				pageMonthlyPlanningPO.CompareValueDecimalFields(extentTest,DBActlValTtlWkToDtSF,ActlValTtlWkToDtSF); 
		
	 }	
	
//////////// ITEMS ///////////////
@Test(description = "Daily Trading Statement - Store, Items Values",priority=5)
public void ItemsColumnValidation() throws Exception
{
		String DTSStore = "Daily Trading Statement - Store, Items Values";
		extentTest = report.startTest("Daily Trading Statement - Store, Items Values");		
		pageWeeklyPlanningPO = new WeeklyPlanningStorePage().createPage(getDriver(), getContext(), log, status, data,getReport(), getReportLogger(), WeeklyPlanningStorePage.class);
		pageDailyTradingStatementStorePO = new MonthlyPlanningGroupPage().createPage(getDriver(), getContext(), log, status, data,getReport(), getReportLogger(), DailyTradingStatementStorePage.class);
		TestStatus status = getTestStatus();
		System.out.println("Entering into TestCase : DTS Store - Items View");
		WeekValue = getContext().getStringProperty("WeekToTest");
		
		//Planned Items
		Report_AddStep("testcase", " PLANNED ITEMS- WEEK WISE DATA", "", "", "");
		extentTest = report.startTest("Planned Items - Day wise");
		
		String PlannedItemsTotal = data.getText("PlannedItemsTotal");	
		DBPlndValTtl = sql.CLRexecuteQuery(getContext(), PlannedItemsTotal.replace("store",getContext().getStringProperty("store")).replace("Division", getContext().getStringProperty("Division")).replace("Week",getContext().getStringProperty("SelectFiscalWeek"))
				.replace("Department",getContext().getStringProperty("Longlife")));
		System.out.println("DB Value: "+DBPlndValTtl);
		
		String PlannedItemsTotalSF = data.getText("PlannedItemsTotal");	
		DBPlndValTtlSF = sql.CLRexecuteQuery(getContext(), PlannedItemsTotalSF.replace("store",getContext().getStringProperty("store")).replace("Division", getContext().getStringProperty("Division")).replace("Week",getContext().getStringProperty("SelectFiscalWeek"))
				.replace("Department",getContext().getStringProperty("SeaFoodDeli")));
		System.out.println("DB Value: "+DBPlndValTtlSF);
		
			
			PlndValTtl = pageDailyTradingStatementStorePO.CombinedDayWiseUITotal("LLItems","PlannedValue",objDailyTradingStatementObjects);
			PlndValTtlSF = pageDailyTradingStatementStorePO.CombinedDayWiseUITotal("SFItems","PlannedValue",objDailyTradingStatementObjects);
		
		pageDailyTradingStatementStorePO.DTSCompareValuesTotal(extentTest,DBPlndValTtl,PlndValTtl); 
		pageDailyTradingStatementStorePO.DTSCompareValuesTotal(extentTest,DBPlndValTtlSF,PlndValTtlSF);
		
		
		//Actual Items
		Report_AddStep("testcase", " ACTUAL ITEMS- WEEK WISE DATA", "", "", "");
		extentTest = report.startTest("Actual Items - Day wise");
		if (WeekValue.contains("Past") ) {
		String ActualItemsTotal = data.getText("PastWeekActualItemsTotal");	
		DBActlTtl = sql.CLRexecuteQuery(getContext(), ActualItemsTotal.replace("store",getContext().getStringProperty("store")).replace("Division", getContext().getStringProperty("Division")).replace("Week",getContext().getStringProperty("SelectFiscalWeek"))
				.replace("Department",getContext().getStringProperty("Longlife")));
		System.out.println("DB Value: "+DBActlTtl); 
		
		String ActualItemsTotalSF = data.getText("PastWeekActualItemsTotal");	
		DBActlTtlSF = sql.CLRexecuteQuery(getContext(), ActualItemsTotalSF.replace("store",getContext().getStringProperty("store")).replace("Division", getContext().getStringProperty("Division")).replace("Week",getContext().getStringProperty("SelectFiscalWeek"))
				.replace("Department",getContext().getStringProperty("SeaFoodDeli")));
		System.out.println("DB Value: "+DBActlTtlSF); 
		}
		if (WeekValue.contains("Current") ) {
			String ActualItemsTotal = data.getText("CurrentWeekActualItemsTotal");	
			DBActlTtl = sql.CLRexecuteQuery(getContext(), ActualItemsTotal.replace("store",getContext().getStringProperty("store")).replace("Division", getContext().getStringProperty("Division")).replace("Week",getContext().getStringProperty("SelectFiscalWeek"))
					.replace("Department",getContext().getStringProperty("Longlife")));
			System.out.println("DB Value: "+DBActlTtl); 
			
			String ActualItemsTotalSF = data.getText("CurrentWeekActualItemsTotal");	
			DBActlTtlSF = sql.CLRexecuteQuery(getContext(), ActualItemsTotalSF.replace("store",getContext().getStringProperty("store")).replace("Division", getContext().getStringProperty("Division")).replace("Week",getContext().getStringProperty("SelectFiscalWeek"))
					.replace("Department",getContext().getStringProperty("SeaFoodDeli")));
			System.out.println("DB Value: "+DBActlTtlSF); 
			}
			
			ActlValTtl = pageDailyTradingStatementStorePO.CombinedDayWiseUITotal("LLItems","ActualValue",objDailyTradingStatementObjects);
			ActlValTtlSF = pageDailyTradingStatementStorePO.CombinedDayWiseUITotal("SFItems","ActualValue",objDailyTradingStatementObjects);
		
		
		pageDailyTradingStatementStorePO.DTSCompareValuesTotal(extentTest,DBActlTtl,ActlValTtl);
		pageDailyTradingStatementStorePO.DTSCompareValuesTotal(extentTest,DBActlTtlSF,ActlValTtlSF);
		 
		
		//LY Items
		Report_AddStep("testcase", " LY ITEMS- WEEK WISE DATA", "", "", "");
		extentTest = report.startTest("LY Items - Day wise");
		if (WeekValue.contains("Past") ) {
		String LYItemsTotal = data.getText("PastWeekLYItemsTotal");	
		DBLYValTtl = sql.CLRexecuteQuery(getContext(), LYItemsTotal.replace("store",getContext().getStringProperty("store")).replace("Division", getContext().getStringProperty("Division")).replace("LYWk",getContext().getStringProperty("LYWk"))
				.replace("Week",getContext().getStringProperty("SelectFiscalWeek")).replace("Department",getContext().getStringProperty("Longlife")));
		System.out.println("DB Value: "+DBLYValTtl);
		
		String LYItemsTotalSF = data.getText("PastWeekLYItemsTotal");	
		DBLYValTtlSF = sql.CLRexecuteQuery(getContext(), LYItemsTotalSF.replace("store",getContext().getStringProperty("store")).replace("Division", getContext().getStringProperty("Division")).replace("LYWk",getContext().getStringProperty("LYWk"))
				.replace("Week",getContext().getStringProperty("SelectFiscalWeek")).replace("Department",getContext().getStringProperty("SeaFoodDeli")));
		System.out.println("DB Value: "+DBLYValTtlSF);
		
		}
		if (WeekValue.contains("Current") ) {
			String LYItemsTotal = data.getText("CurrentWeekLYItemsTotal");	
			DBLYValTtl = sql.CLRexecuteQuery(getContext(), LYItemsTotal.replace("store",getContext().getStringProperty("store")).replace("Division", getContext().getStringProperty("Division")).replace("LYWk",getContext().getStringProperty("LYWk"))
					.replace("Week",getContext().getStringProperty("SelectFiscalWeek")).replace("Department",getContext().getStringProperty("Longlife")));
			System.out.println("DB Value: "+DBLYValTtl);
			
			String LYItemsTotalSF = data.getText("CurrentWeekLYItemsTotal");	
			DBLYValTtlSF = sql.CLRexecuteQuery(getContext(), LYItemsTotalSF.replace("store",getContext().getStringProperty("store")).replace("Division", getContext().getStringProperty("Division")).replace("LYWk",getContext().getStringProperty("LYWk"))
					.replace("Week",getContext().getStringProperty("SelectFiscalWeek")).replace("Department",getContext().getStringProperty("SeaFoodDeli")));
			System.out.println("DB Value: "+DBLYValTtlSF);
			
			}
			LYValTtl = pageDailyTradingStatementStorePO.CombinedDayWiseUITotal("LLItems","LYValue",objDailyTradingStatementObjects);	
			LYValTtlSF = pageDailyTradingStatementStorePO.CombinedDayWiseUITotal("SFItems","LYValue",objDailyTradingStatementObjects);	
		
		
		pageDailyTradingStatementStorePO.CompareValueDecimalTotal(extentTest,DBLYValTtl,LYValTtl); 	
		pageDailyTradingStatementStorePO.CompareValueDecimalTotal(extentTest,DBLYValTtlSF,LYValTtlSF); 	
		
		
		//Week to Date - Planned Items
		Report_AddStep("testcase", " PLANNED ITEMS- WEEK TO DATE DATA", "", "", "");
		extentTest = report.startTest("Planned Items - Week to Date");
		
		String PlannedItemsWkToDtTotal = data.getText("PlannedItemsWkToDtTotal");	
		DBPlndValTtlWkToDt = sql.CLRexecuteQuery(getContext(), PlannedItemsWkToDtTotal.replace("store",getContext().getStringProperty("store")).replace("Division", getContext().getStringProperty("Division")).replace("Week",getContext().getStringProperty("SelectFiscalWeek"))
				.replace("Department",getContext().getStringProperty("Longlife")));
		System.out.println("DB Value: "+DBPlndValTtlWkToDt); 
		
		String PlannedItemsWkToDtTotalSF = data.getText("PlannedItemsWkToDtTotal");	
		DBPlndValTtlWkToDtSF = sql.CLRexecuteQuery(getContext(), PlannedItemsWkToDtTotalSF.replace("store",getContext().getStringProperty("store")).replace("Division", getContext().getStringProperty("Division")).replace("Week",getContext().getStringProperty("SelectFiscalWeek"))
				.replace("Department",getContext().getStringProperty("SeaFoodDeli")));
		System.out.println("DB Value: "+DBPlndValTtlWkToDtSF); 
		
		
			PlndValTtlWkToDt = pageDailyTradingStatementStorePO.CombinedWeekToDateTotal("LLItems",objDailyTradingStatementObjects, objDailyTradingStatementObjects.WeekToDatePlndValue);		
			PlndValTtlWkToDtSF = pageDailyTradingStatementStorePO.CombinedWeekToDateTotal("SFItems",objDailyTradingStatementObjects, objDailyTradingStatementObjects.WeekToDatePlndValue);
		
		
		pageMonthlyPlanningPO.CompareValues(extentTest,DBPlndValTtlWkToDt,PlndValTtlWkToDt); 
		pageMonthlyPlanningPO.CompareValues(extentTest,DBPlndValTtlWkToDtSF,PlndValTtlWkToDtSF); 
		
		
		//Week to Date - Actual Items
		Report_AddStep("testcase", " ACTUAL ITEMS- WEEK TO DATE DATA", "", "", "");
		extentTest = report.startTest("Actual Items - Week to Date");
		
		if (WeekValue.contains("Past") ) {
		String ActualItemsWkToDtTotal = data.getText("PastWeekActualItemsWkToDtTotal");	
		DBActlTtlWkToDt = sql.CLRexecuteQuery(getContext(), ActualItemsWkToDtTotal.replace("store",getContext().getStringProperty("store")).replace("Division", getContext().getStringProperty("Division")).replace("Week",getContext().getStringProperty("SelectFiscalWeek"))
				.replace("Department",getContext().getStringProperty("Longlife")));
		System.out.println("DB Value: "+DBActlTtlWkToDt);
		
		String ActualItemsWkToDtTotalSF = data.getText("PastWeekActualItemsWkToDtTotal");	
		DBActlTtlWkToDtSF = sql.CLRexecuteQuery(getContext(), ActualItemsWkToDtTotalSF.replace("store",getContext().getStringProperty("store")).replace("Division", getContext().getStringProperty("Division")).replace("Week",getContext().getStringProperty("SelectFiscalWeek"))
				.replace("Department",getContext().getStringProperty("SeaFoodDeli")));
		System.out.println("DB Value: "+DBActlTtlWkToDtSF);
		}
		
		if (WeekValue.contains("Current") ) {
			String ActualItemsWkToDtTotal = data.getText("CurrentWeekActualItemsWkToDtTotal");	
			DBActlTtlWkToDt = sql.CLRexecuteQuery(getContext(), ActualItemsWkToDtTotal.replace("store",getContext().getStringProperty("store")).replace("Division", getContext().getStringProperty("Division")).replace("Week",getContext().getStringProperty("SelectFiscalWeek"))
					.replace("Department",getContext().getStringProperty("Longlife")));
			System.out.println("DB Value: "+DBActlTtlWkToDt);
			
			String ActualItemsWkToDtTotalSF = data.getText("CurrentWeekActualItemsWkToDtTotal");	
			DBActlTtlWkToDtSF = sql.CLRexecuteQuery(getContext(), ActualItemsWkToDtTotalSF.replace("store",getContext().getStringProperty("store")).replace("Division", getContext().getStringProperty("Division")).replace("Week",getContext().getStringProperty("SelectFiscalWeek"))
					.replace("Department",getContext().getStringProperty("SeaFoodDeli")));
			System.out.println("DB Value: "+DBActlTtlWkToDtSF);
			}
			ActlValTtlWkToDt = pageDailyTradingStatementStorePO.CombinedWeekToDateTotal("LLItems",objDailyTradingStatementObjects, objDailyTradingStatementObjects.WeekToDateActlValue);
			ActlValTtlWkToDtSF = pageDailyTradingStatementStorePO.CombinedWeekToDateTotal("SFItems",objDailyTradingStatementObjects, objDailyTradingStatementObjects.WeekToDateActlValue);
		
		pageMonthlyPlanningPO.CompareValues(extentTest,DBActlTtlWkToDt,ActlValTtlWkToDt);
		pageMonthlyPlanningPO.CompareValues(extentTest,DBActlTtlWkToDtSF,ActlValTtlWkToDtSF); 
		 
		
		//Week to Date - LY Items
		Report_AddStep("testcase", " LY ITEMS- WEEK TO DATE DATA", "", "", "");
		extentTest = report.startTest("LY Items - Week to Date");
		
		if (WeekValue.contains("Past") ) {
		String LYItemsWkToDtTotal = data.getText("PastWeekLYItemsWkToDtTotal");	
		DBLYValTtlWkToDt = sql.CLRexecuteQuery(getContext(), LYItemsWkToDtTotal.replace("store",getContext().getStringProperty("store")).replace("Division", getContext().getStringProperty("Division")).replace("LYWk",getContext().getStringProperty("LYWk"))
				.replace("Week",getContext().getStringProperty("SelectFiscalWeek")).replace("Department",getContext().getStringProperty("Longlife")));
		System.out.println("DB Value: "+DBLYValTtlWkToDt);
		
		String LYItemsWkToDtTotalSF = data.getText("PastWeekLYItemsWkToDtTotal");	
		DBLYValTtlWkToDtSF = sql.CLRexecuteQuery(getContext(), LYItemsWkToDtTotalSF.replace("store",getContext().getStringProperty("store")).replace("Division", getContext().getStringProperty("Division")).replace("LYWk",getContext().getStringProperty("LYWk"))
				.replace("Week",getContext().getStringProperty("SelectFiscalWeek")).replace("Department",getContext().getStringProperty("SeaFoodDeli")));
		System.out.println("DB Value: "+DBLYValTtlWkToDtSF);
		
		}
		if (WeekValue.contains("Current") ) {
			String LYItemsWkToDtTotal = data.getText("CurrentWeekLYItemsWkToDtTotal");	
			DBLYValTtlWkToDt = sql.CLRexecuteQuery(getContext(), LYItemsWkToDtTotal.replace("store",getContext().getStringProperty("store")).replace("Division", getContext().getStringProperty("Division")).replace("LYWk",getContext().getStringProperty("LYWk"))
					.replace("Week",getContext().getStringProperty("SelectFiscalWeek")).replace("Department",getContext().getStringProperty("Longlife")));
			System.out.println("DB Value: "+DBLYValTtlWkToDt);
			
			String LYItemsWkToDtTotalSF = data.getText("CurrentWeekLYItemsWkToDtTotal");	
			DBLYValTtlWkToDtSF = sql.CLRexecuteQuery(getContext(), LYItemsWkToDtTotalSF.replace("store",getContext().getStringProperty("store")).replace("Division", getContext().getStringProperty("Division")).replace("LYWk",getContext().getStringProperty("LYWk"))
					.replace("Week",getContext().getStringProperty("SelectFiscalWeek")).replace("Department",getContext().getStringProperty("SeaFoodDeli")));
			System.out.println("DB Value: "+DBLYValTtlWkToDtSF);
			
			}
			LYValTtlWkToDt =  pageDailyTradingStatementStorePO.CombinedWeekToDateTotal("LLItems",objDailyTradingStatementObjects, objDailyTradingStatementObjects.WeekToDateLYValue);
			LYValTtlWkToDtSF =  pageDailyTradingStatementStorePO.CombinedWeekToDateTotal("SFItems",objDailyTradingStatementObjects, objDailyTradingStatementObjects.WeekToDateLYValue);
		
		pageMonthlyPlanningPO.CompareValueDecimalFields(extentTest,DBLYValTtlWkToDt,LYValTtlWkToDt);
		pageMonthlyPlanningPO.CompareValueDecimalFields(extentTest,DBLYValTtlWkToDtSF,LYValTtlWkToDtSF);
		
		
		//Week to Date - Variance Items
		Report_AddStep("testcase", " VARIANCE ITEMS- WEEK TO DATE DATA", "", "", "");
		extentTest = report.startTest("Variance Items - Week to Date"); 
		
		if (WeekValue.contains("Past") ) {
		String VarItemsWkToDtTotal = data.getText("PastWeekVarItemsWkToDtTotal");	
		DBVarWkToDtTtl = sql.CLRexecuteQuery(getContext(), VarItemsWkToDtTotal.replace("store",getContext().getStringProperty("store")).replace("Division", getContext().getStringProperty("Division")).replace("Week",getContext().getStringProperty("SelectFiscalWeek"))
				.replace("Department",getContext().getStringProperty("Longlife")));
		System.out.println("DB Value: "+DBVarWkToDtTtl);
		
		String VarItemsWkToDtTotalSF = data.getText("PastWeekVarItemsWkToDtTotal");	
		DBVarWkToDtTtlSF = sql.CLRexecuteQuery(getContext(), VarItemsWkToDtTotalSF.replace("store",getContext().getStringProperty("store")).replace("Division", getContext().getStringProperty("Division")).replace("Week",getContext().getStringProperty("SelectFiscalWeek"))
				.replace("Department",getContext().getStringProperty("SeaFoodDeli")));
		System.out.println("DB Value: "+DBVarWkToDtTtlSF);
		
		}
		if (WeekValue.contains("Current") ) {
			String VarItemsWkToDtTotal = data.getText("CurrentWeekVarItemsWkToDtTotal");	
			DBVarWkToDtTtl = sql.CLRexecuteQuery(getContext(), VarItemsWkToDtTotal.replace("store",getContext().getStringProperty("store")).replace("Division", getContext().getStringProperty("Division")).replace("Week",getContext().getStringProperty("SelectFiscalWeek"))
					.replace("Department",getContext().getStringProperty("Longlife")));
			System.out.println("DB Value: "+DBVarWkToDtTtl);
			
			String VarItemsWkToDtTotalSF = data.getText("CurrentWeekVarItemsWkToDtTotal");	
			DBVarWkToDtTtlSF = sql.CLRexecuteQuery(getContext(), VarItemsWkToDtTotalSF.replace("store",getContext().getStringProperty("store")).replace("Division", getContext().getStringProperty("Division")).replace("Week",getContext().getStringProperty("SelectFiscalWeek"))
					.replace("Department",getContext().getStringProperty("SeaFoodDeli")));
			System.out.println("DB Value: "+DBVarWkToDtTtlSF);
			
			}
			VarTtlWkToDt = pageDailyTradingStatementStorePO.CombinedWeekToDateTotal("LLItems",objDailyTradingStatementObjects, objDailyTradingStatementObjects.WeekToDateVarValue);	
			VarTtlWkToDtSF = pageDailyTradingStatementStorePO.CombinedWeekToDateTotal("SFItems",objDailyTradingStatementObjects, objDailyTradingStatementObjects.WeekToDateVarValue);
		
			
		
		pageMonthlyPlanningPO.CompareValues(extentTest,DBVarWkToDtTtl,VarTtlWkToDt); 
		pageMonthlyPlanningPO.CompareValues(extentTest,DBVarWkToDtTtlSF,VarTtlWkToDtSF); 
		
}	

//////////// BUDGET ///////////////
@Test(description = "Daily Trading Statement - Store, Customers Values",priority=7)
public void BudgetColumnValidation() throws Exception
{
		String DTSStore = "Daily Trading Statement - Store, Customers Values";
		extentTest = report.startTest("Daily Trading Statement - Store, Customers Values");		
		pageWeeklyPlanningPO = new WeeklyPlanningStorePage().createPage(getDriver(), getContext(), log, status, data,getReport(), getReportLogger(), WeeklyPlanningStorePage.class);
		pageDailyTradingStatementStorePO = new MonthlyPlanningGroupPage().createPage(getDriver(), getContext(), log, status, data,getReport(), getReportLogger(), DailyTradingStatementStorePage.class);
		TestStatus status = getTestStatus();
		System.out.println("Entering into TestCase : DTS Store - Budget View");
		WeekValue = getContext().getStringProperty("WeekToTest");
		OptionToView = getContext().getStringProperty("OptionToView");
		
		//Budget Sales
		
		if(OptionToView.contains("Budget"))
		{
			Report_AddStep("testcase", "BUDGET SALES-UNDER BUDGET VIEW", "", "", "");
			extentTest = report.startTest("Budget Sales - UNDER BUDGET VIEW");
			
			String BudgetSalesTotal = data.getText("BudgetSalesTotal");	
			DBBdgtSlsTtl = sql.CLRexecuteQuery(getContext(), BudgetSalesTotal.replace("store",getContext().getStringProperty("store")).replace("Division", getContext().getStringProperty("Division")).replace("Week",getContext().getStringProperty("SelectFiscalWeek"))
					.replace("Department",getContext().getStringProperty("Longlife")));
			System.out.println("DB Value: "+DBBdgtSlsTtl); 
			
			String BudgetSalesTotalSF = data.getText("BudgetSalesTotal");	
			DBBdgtSlsTtlSF = sql.CLRexecuteQuery(getContext(), BudgetSalesTotalSF.replace("store",getContext().getStringProperty("store")).replace("Division", getContext().getStringProperty("Division")).replace("Week",getContext().getStringProperty("SelectFiscalWeek"))
					.replace("Department",getContext().getStringProperty("SeaFoodDeli")));
			System.out.println("DB Value: "+DBBdgtSlsTtlSF); 
			
					
				BdgtSlsTtl = pageDailyTradingStatementStorePO.CombinedWeekToDateTotal("LLSales",objDailyTradingStatementObjects, objDailyTradingStatementObjects.BudgetSales);
				BdgtSlsTtlSF = pageDailyTradingStatementStorePO.CombinedWeekToDateTotal("SFSales",objDailyTradingStatementObjects, objDailyTradingStatementObjects.BudgetSales);
			
			pageMonthlyPlanningPO.CompareValues(extentTest,DBBdgtSlsTtl,BdgtSlsTtl);
			pageMonthlyPlanningPO.CompareValues(extentTest,DBBdgtSlsTtlSF,BdgtSlsTtlSF);
			
		}
		if(OptionToView.contains("Forecast"))
		{
			Report_AddStep("testcase", "FORECAST SALES-UNDER BUDGET VIEW", "", "", "");
			extentTest = report.startTest("Forecast Sales - UNDER BUDGET VIEW");
			
			String ForecastSalesTotal = data.getText("ForecastSalesTotal");	
			DBBdgtSlsTtl = sql.CLRexecuteQuery(getContext(), ForecastSalesTotal.replace("store",getContext().getStringProperty("store")).replace("Division", getContext().getStringProperty("Division")).replace("Week",getContext().getStringProperty("SelectFiscalWeek"))
					.replace("Department",getContext().getStringProperty("Longlife")));
			System.out.println("DB Value: "+DBBdgtSlsTtl); 
			
			String ForecastSalesTotalSF = data.getText("ForecastSalesTotal");	
			DBBdgtSlsTtlSF = sql.CLRexecuteQuery(getContext(), ForecastSalesTotalSF.replace("store",getContext().getStringProperty("store")).replace("Division", getContext().getStringProperty("Division")).replace("Week",getContext().getStringProperty("SelectFiscalWeek"))
					.replace("Department",getContext().getStringProperty("SeaFoodDeli")));
			System.out.println("DB Value: "+DBBdgtSlsTtlSF);
			
					
				BdgtSlsTtl = pageDailyTradingStatementStorePO.CombinedWeekToDateTotal("LLSales",objDailyTradingStatementObjects, objDailyTradingStatementObjects.BudgetSales);
				BdgtSlsTtlSF = pageDailyTradingStatementStorePO.CombinedWeekToDateTotal("SFSales",objDailyTradingStatementObjects, objDailyTradingStatementObjects.BudgetSales);
			
			
			pageMonthlyPlanningPO.CompareValues(extentTest,DBBdgtSlsTtl,BdgtSlsTtl);
			pageMonthlyPlanningPO.CompareValues(extentTest,DBBdgtSlsTtlSF,BdgtSlsTtlSF);
			
		}
		//Budget Wages
		
		if(OptionToView.contains("Budget"))
		{
			Report_AddStep("testcase", "BUDGET WAGES-UNDER BUDGET VIEW", "", "", "");
			extentTest = report.startTest("Budget Wages - UNDER BUDGET VIEW");
			
			String BudgetWagePercentageTotal = data.getText("BudgetWagePercentageTotal");	
			DBBdgtWgTtl = sql.CLRexecuteQuery(getContext(), BudgetWagePercentageTotal.replace("store",getContext().getStringProperty("store")).replace("Division", getContext().getStringProperty("Division")).replace("Week",getContext().getStringProperty("SelectFiscalWeek"))
					.replace("Department",getContext().getStringProperty("Longlife")));
			System.out.println("DB Value: "+DBBdgtWgTtl); 
			
			String BudgetWagePercentageTotalSF = data.getText("BudgetWagePercentageTotal");	
			DBBdgtWgTtlSF = sql.CLRexecuteQuery(getContext(), BudgetWagePercentageTotalSF.replace("store",getContext().getStringProperty("store")).replace("Division", getContext().getStringProperty("Division")).replace("Week",getContext().getStringProperty("SelectFiscalWeek"))
					.replace("Department",getContext().getStringProperty("SeaFoodDeli")));
			System.out.println("DB Value: "+DBBdgtWgTtlSF);
			
					
				BdgtWgTtl = pageDailyTradingStatementStorePO.CombinedWeekToDateTotal("LLWagesPercentage",objDailyTradingStatementObjects, objDailyTradingStatementObjects.BudgetSales);
				BdgtWgTtlSF = pageDailyTradingStatementStorePO.CombinedWeekToDateTotal("SFWagesPercentage",objDailyTradingStatementObjects, objDailyTradingStatementObjects.BudgetSales);
			
			
			pageMonthlyPlanningPO.CompareValueDecimalFields(extentTest,DBBdgtWgTtl,BdgtWgTtl); 
			pageMonthlyPlanningPO.CompareValueDecimalFields(extentTest,DBBdgtWgTtlSF,BdgtWgTtlSF);
			
		}
		if(OptionToView.contains("Forecast"))
		{
			Report_AddStep("testcase", "FORECAST WAGES-UNDER BUDGET VIEW", "", "", "");
			extentTest = report.startTest("FORECAST Wages - UNDER BUDGET VIEW");
			
			String ForecastWagePercentageTotal = data.getText("ForecastWagePercentageTotal");	
			DBBdgtWgTtl = sql.CLRexecuteQuery(getContext(), ForecastWagePercentageTotal.replace("store",getContext().getStringProperty("store")).replace("Division", getContext().getStringProperty("Division")).replace("Week",getContext().getStringProperty("SelectFiscalWeek"))
					.replace("Department",getContext().getStringProperty("Longlife")));
			System.out.println("DB Value: "+DBBdgtWgTtl); 
			
			String ForecastWagePercentageTotalSF = data.getText("ForecastWagePercentageTotal");	
			DBBdgtWgTtlSF = sql.CLRexecuteQuery(getContext(), ForecastWagePercentageTotalSF.replace("store",getContext().getStringProperty("store")).replace("Division", getContext().getStringProperty("Division")).replace("Week",getContext().getStringProperty("SelectFiscalWeek"))
					.replace("Department",getContext().getStringProperty("SeaFoodDeli")));
			System.out.println("DB Value: "+DBBdgtWgTtlSF); 
			
					
				BdgtWgTtl = pageDailyTradingStatementStorePO.CombinedWeekToDateTotal("LLWagesPercentage",objDailyTradingStatementObjects, objDailyTradingStatementObjects.BudgetSales);
				BdgtWgTtlSF = pageDailyTradingStatementStorePO.CombinedWeekToDateTotal("SFWagesPercentage",objDailyTradingStatementObjects, objDailyTradingStatementObjects.BudgetSales);
			
			
			pageMonthlyPlanningPO.CompareValueDecimalFields(extentTest,DBBdgtWgTtl,BdgtWgTtl); 
			pageMonthlyPlanningPO.CompareValueDecimalFields(extentTest,DBBdgtWgTtlSF,BdgtWgTtlSF); 
			
		}
		
		//Budget Wage %	
		if(OptionToView.contains("Budget"))
		{
			Report_AddStep("testcase", "BUDGET WAGE%-UNDER BUDGET VIEW", "", "", "");
			extentTest = report.startTest("Budget Wage% - UNDER BUDGET VIEW");
			
			String BudgetWagePercentageTotal = data.getText("BudgetWagePercentageTotal");	
			DBBdgtWgTtl = sql.CLRexecuteQuery(getContext(), BudgetWagePercentageTotal.replace("store",getContext().getStringProperty("store")).replace("Division", getContext().getStringProperty("Division")).replace("Week",getContext().getStringProperty("SelectFiscalWeek"))
					.replace("Department",getContext().getStringProperty("Longlife")));
			System.out.println("DB Value: "+DBBdgtWgTtl); 
			
			String BudgetWagePercentageTotalSF = data.getText("BudgetWagePercentageTotal");	
			DBBdgtWgTtlSF = sql.CLRexecuteQuery(getContext(), BudgetWagePercentageTotalSF.replace("store",getContext().getStringProperty("store")).replace("Division", getContext().getStringProperty("Division")).replace("Week",getContext().getStringProperty("SelectFiscalWeek"))
					.replace("Department",getContext().getStringProperty("SeaFoodDeli")));
			System.out.println("DB Value: "+DBBdgtWgTtlSF);
			
					
				BdgtWgTtl = pageDailyTradingStatementStorePO.CombinedWeekToDateTotal("LLWagesPercentage",objDailyTradingStatementObjects, objDailyTradingStatementObjects.BudgetWgPrcnt);
				BdgtWgTtlSF = pageDailyTradingStatementStorePO.CombinedWeekToDateTotal("SFWagesPercentage",objDailyTradingStatementObjects, objDailyTradingStatementObjects.BudgetWgPrcnt);
			
			
			pageMonthlyPlanningPO.CompareValueDecimalFields(extentTest,DBBdgtWgTtl,BdgtWgTtl); 
			pageMonthlyPlanningPO.CompareValueDecimalFields(extentTest,DBBdgtWgTtlSF,BdgtWgTtlSF);
			
		}
		if(OptionToView.contains("Forecast"))
		{
			Report_AddStep("testcase", "FORECAST WAGE%-UNDER BUDGET VIEW", "", "", "");
			extentTest = report.startTest("FORECAST Wage% - UNDER BUDGET VIEW");
			
			String ForecastWagePercentageTotal = data.getText("ForecastWagePercentageTotal");	
			DBBdgtWgTtl = sql.CLRexecuteQuery(getContext(), ForecastWagePercentageTotal.replace("store",getContext().getStringProperty("store")).replace("Division", getContext().getStringProperty("Division")).replace("Week",getContext().getStringProperty("SelectFiscalWeek"))
					.replace("Department",getContext().getStringProperty("Longlife")));
			System.out.println("DB Value: "+DBBdgtWgTtl); 
			
			String ForecastWagePercentageTotalSF = data.getText("ForecastWagePercentageTotal");	
			DBBdgtWgTtlSF = sql.CLRexecuteQuery(getContext(), ForecastWagePercentageTotalSF.replace("store",getContext().getStringProperty("store")).replace("Division", getContext().getStringProperty("Division")).replace("Week",getContext().getStringProperty("SelectFiscalWeek"))
					.replace("Department",getContext().getStringProperty("SeaFoodDeli")));
			System.out.println("DB Value: "+DBBdgtWgTtlSF); 
			
					
				BdgtWgTtl = pageDailyTradingStatementStorePO.CombinedWeekToDateTotal("LLWagesPercentage",objDailyTradingStatementObjects, objDailyTradingStatementObjects.BudgetWgPrcnt);
				BdgtWgTtlSF = pageDailyTradingStatementStorePO.CombinedWeekToDateTotal("SFWagesPercentage",objDailyTradingStatementObjects, objDailyTradingStatementObjects.BudgetWgPrcnt);
			
			
			pageMonthlyPlanningPO.CompareValueDecimalFields(extentTest,DBBdgtWgTtl,BdgtWgTtl); 
			pageMonthlyPlanningPO.CompareValueDecimalFields(extentTest,DBBdgtWgTtlSF,BdgtWgTtlSF); 
			
		}
		
		//Allowed Wages
		if(OptionToView.contains("Budget"))
		{
			Report_AddStep("testcase", "ALLOWED WAGES-UNDER BUDGET VIEW", "", "", "");
			extentTest = report.startTest("Allowed Wages - UNDER BUDGET VIEW");
			
			if (WeekValue.contains("Past") ) {
			String AllowedWagesTotal = data.getText("PastWeekAllowedWagesTotal");	
			DBAlwdWgsTtl = sql.CLRexecuteQuery(getContext(), AllowedWagesTotal.replace("store",getContext().getStringProperty("store")).replace("Division", getContext().getStringProperty("Division")).replace("Week",getContext().getStringProperty("SelectFiscalWeek"))
					.replace("Department",getContext().getStringProperty("Longlife")));
			System.out.println("DB Value: "+DBAlwdWgsTtl); 
			
			String AllowedWagesTotalSF = data.getText("PastWeekAllowedWagesTotal");	
			DBAlwdWgsTtlSF = sql.CLRexecuteQuery(getContext(), AllowedWagesTotalSF.replace("store",getContext().getStringProperty("store")).replace("Division", getContext().getStringProperty("Division")).replace("Week",getContext().getStringProperty("SelectFiscalWeek"))
					.replace("Department",getContext().getStringProperty("SeaFoodDeli")));
			System.out.println("DB Value: "+DBAlwdWgsTtlSF); 
			}
			if (WeekValue.contains("Current") ) {
				String AllowedWagesTotal = data.getText("CurrentWeekAllowedWagesTotal");	
				DBAlwdWgsTtl = sql.CLRexecuteQuery(getContext(), AllowedWagesTotal.replace("store",getContext().getStringProperty("store")).replace("Division", getContext().getStringProperty("Division")).replace("Week",getContext().getStringProperty("SelectFiscalWeek"))
						.replace("Department",getContext().getStringProperty("Longlife")));
				System.out.println("DB Value: "+DBAlwdWgsTtl); 
				
				String AllowedWagesTotalSF = data.getText("CurrentWeekAllowedWagesTotal");	
				DBAlwdWgsTtlSF = sql.CLRexecuteQuery(getContext(), AllowedWagesTotalSF.replace("store",getContext().getStringProperty("store")).replace("Division", getContext().getStringProperty("Division")).replace("Week",getContext().getStringProperty("SelectFiscalWeek"))
						.replace("Department",getContext().getStringProperty("SeaFoodDeli")));
				System.out.println("DB Value: "+DBAlwdWgsTtlSF); 
				}
			
				AlwdWgsTtl = pageDailyTradingStatementStorePO.CombinedWeekToDateTotal("LLWages",objDailyTradingStatementObjects, objDailyTradingStatementObjects.AllowedWagesValue);	
				AlwdWgsTtlSF = pageDailyTradingStatementStorePO.CombinedWeekToDateTotal("SFWages",objDailyTradingStatementObjects, objDailyTradingStatementObjects.AllowedWagesValue);
			
			
			pageMonthlyPlanningPO.CompareValues(extentTest,DBAlwdWgsTtl,AlwdWgsTtl); 
			pageMonthlyPlanningPO.CompareValues(extentTest,DBAlwdWgsTtlSF,AlwdWgsTtlSF);
			
		}
		
			
		//Estimated Forecast Sales
		Report_AddStep("testcase", "ESTIMATED FORECAST SALES-UNDER BUDGET VIEW", "", "", "");
		extentTest = report.startTest("Estimated Forecast Sales  - UNDER BUDGET VIEW"); 
		
		if (WeekValue.contains("Past") ) {
		String EstimatedFcstSalesTotal = data.getText("PastWeekEstimatedFcstSalesTotal");	
		DBEstFcstSlsTtl = sql.CLRexecuteQuery(getContext(), EstimatedFcstSalesTotal.replace("store",getContext().getStringProperty("store")).replace("Division", getContext().getStringProperty("Division")).replace("Week",getContext().getStringProperty("SelectFiscalWeek"))
				.replace("Department",getContext().getStringProperty("Longlife")));
		System.out.println("DB Value: "+DBEstFcstSlsTtl); 
		
		String EstimatedFcstSalesTotalSF = data.getText("PastWeekEstimatedFcstSalesTotal");	
		DBEstFcstSlsTtlSF = sql.CLRexecuteQuery(getContext(), EstimatedFcstSalesTotalSF.replace("store",getContext().getStringProperty("store")).replace("Division", getContext().getStringProperty("Division")).replace("Week",getContext().getStringProperty("SelectFiscalWeek"))
				.replace("Department",getContext().getStringProperty("SeaFoodDeli")));
		System.out.println("DB Value: "+DBEstFcstSlsTtlSF); 
		
		}
		if (WeekValue.contains("Current") ) {
			String EstimatedFcstSalesTotal = data.getText("CurrentWeekEstimatedFcstSalesTotal");	
			DBEstFcstSlsTtl = sql.CLRexecuteQuery(getContext(), EstimatedFcstSalesTotal.replace("store",getContext().getStringProperty("store")).replace("Division", getContext().getStringProperty("Division")).replace("Week",getContext().getStringProperty("SelectFiscalWeek"))
					.replace("Department",getContext().getStringProperty("Longlife")));
			System.out.println("DB Value: "+DBEstFcstSlsTtl); 
			
			String EstimatedFcstSalesTotalSF = data.getText("CurrentWeekEstimatedFcstSalesTotal");	
			DBEstFcstSlsTtlSF = sql.CLRexecuteQuery(getContext(), EstimatedFcstSalesTotalSF.replace("store",getContext().getStringProperty("store")).replace("Division", getContext().getStringProperty("Division")).replace("Week",getContext().getStringProperty("SelectFiscalWeek"))
					.replace("Department",getContext().getStringProperty("SeaFoodDeli")));
			System.out.println("DB Value: "+DBEstFcstSlsTtlSF); 
			
			}
			EstFcstSlsTtl = pageDailyTradingStatementStorePO.CombinedWeekToDateTotal("LLSales",objDailyTradingStatementObjects, objDailyTradingStatementObjects.EstForecastSales);
			EstFcstSlsTtlSF = pageDailyTradingStatementStorePO.CombinedWeekToDateTotal("SFSales",objDailyTradingStatementObjects, objDailyTradingStatementObjects.EstForecastSales);
		
		
		pageMonthlyPlanningPO.CompareValues(extentTest,DBEstFcstSlsTtl,EstFcstSlsTtl);	
		pageMonthlyPlanningPO.CompareValues(extentTest,DBEstFcstSlsTtlSF,EstFcstSlsTtlSF);
		
			
		//Estimated Forecast Wages
		Report_AddStep("testcase", "ESTIMATED FORECAST WAGES-UNDER BUDGET VIEW", "", "", "");
		extentTest = report.startTest("Estimated Forecast Wages - UNDER BUDGET VIEW"); 
		
		if(WeekValue.contains("Past") )
		{									
			String EstimatedFcstWageTotal = data.getText("PastWeekEstimatedFcstWageTotal");	
			DBEstFcstWgsTtl = sql.CLRexecuteQuery(getContext(), EstimatedFcstWageTotal.replace("store",getContext().getStringProperty("store")).replace("Division", getContext().getStringProperty("Division")).replace("Week",getContext().getStringProperty("SelectFiscalWeek"))
					.replace("Department",getContext().getStringProperty("Longlife")));
			System.out.println("DB Value: "+DBEstFcstWgsTtl); 
			
			String EstimatedFcstWageTotalSF = data.getText("PastWeekEstimatedFcstWageTotal");	
			DBEstFcstWgsTtlSF = sql.CLRexecuteQuery(getContext(), EstimatedFcstWageTotalSF.replace("store",getContext().getStringProperty("store")).replace("Division", getContext().getStringProperty("Division")).replace("Week",getContext().getStringProperty("SelectFiscalWeek"))
					.replace("Department",getContext().getStringProperty("SeaFoodDeli")));
			System.out.println("DB Value: "+DBEstFcstWgsTtlSF); 
		}
		if(WeekValue.contains("Current"))
		{									
			String CurrentEstimatedFcstWageTotal = data.getText("CurrentWeekEstimatedFcstWageTotal");	
			DBEstFcstWgsTtl = sql.CLRexecuteQuery(getContext(), CurrentEstimatedFcstWageTotal.replace("store",getContext().getStringProperty("store")).replace("Division", getContext().getStringProperty("Division")).replace("Week",getContext().getStringProperty("SelectFiscalWeek"))
					.replace("Department",getContext().getStringProperty("Longlife")));
			System.out.println("DB Value: "+DBEstFcstWgsTtl); 
			
			String CurrentEstimatedFcstWageTotalSF = data.getText("CurrentWeekEstimatedFcstWageTotal");	
			DBEstFcstWgsTtlSF = sql.CLRexecuteQuery(getContext(), CurrentEstimatedFcstWageTotalSF.replace("store",getContext().getStringProperty("store")).replace("Division", getContext().getStringProperty("Division")).replace("Week",getContext().getStringProperty("SelectFiscalWeek"))
					.replace("Department",getContext().getStringProperty("SeaFoodDeli")));
			System.out.println("DB Value: "+DBEstFcstWgsTtlSF); 
		}	
		
				
			EstFcstWgsTtl = pageDailyTradingStatementStorePO.CombinedWeekToDateTotal("LLWages",objDailyTradingStatementObjects, objDailyTradingStatementObjects.EstForecastValue);
			EstFcstWgsTtlSF = pageDailyTradingStatementStorePO.CombinedWeekToDateTotal("SFWages",objDailyTradingStatementObjects, objDailyTradingStatementObjects.EstForecastValue);
		
			pageMonthlyPlanningPO.CompareValues(extentTest,DBEstFcstWgsTtl,EstFcstWgsTtl);	
			pageMonthlyPlanningPO.CompareValues(extentTest,DBEstFcstWgsTtlSF,EstFcstWgsTtlSF);	
			
	
		//Estimated Forecast Wages %
			Report_AddStep("testcase", "ESTIMATED FORECAST WAGE PERCENTAGE-UNDER BUDGET VIEW", "", "", "");
			extentTest = report.startTest("Estimated Forecast Wages % - UNDER BUDGET VIEW"); 
		
		if(WeekValue.contains("Past") )
		{										
			String EstimatedFcstWagePrcntTotal = data.getText("PastWeekEstimatedFcstWagePrcntTotal");	
			DBEstFcstWgsPrcntTtl = sql.CLRexecuteQuery(getContext(), EstimatedFcstWagePrcntTotal.replace("store",getContext().getStringProperty("store")).replace("Division", getContext().getStringProperty("Division")).replace("Week",getContext().getStringProperty("SelectFiscalWeek"))
					.replace("Department",getContext().getStringProperty("Longlife")));
			System.out.println("DB Value: "+DBEstFcstWgsPrcntTtl); 
			
			String EstimatedFcstWagePrcntTotalSF = data.getText("PastWeekEstimatedFcstWagePrcntTotal");	
			DBEstFcstWgsPrcntTtlSF = sql.CLRexecuteQuery(getContext(), EstimatedFcstWagePrcntTotalSF.replace("store",getContext().getStringProperty("store")).replace("Division", getContext().getStringProperty("Division")).replace("Week",getContext().getStringProperty("SelectFiscalWeek"))
					.replace("Department",getContext().getStringProperty("SeaFoodDeli")));
			System.out.println("DB Value: "+DBEstFcstWgsPrcntTtlSF); 
		}
		if(WeekValue.contains("Current"))
		{										
			String CurrentEstimatedFcstWagePrcntTotal = data.getText("CurrentWeekEstimatedFcstWagePrcntTotal");	
			DBEstFcstWgsPrcntTtl = sql.CLRexecuteQuery(getContext(), CurrentEstimatedFcstWagePrcntTotal.replace("store",getContext().getStringProperty("store")).replace("Division", getContext().getStringProperty("Division")).replace("Week",getContext().getStringProperty("SelectFiscalWeek"))
					.replace("Department",getContext().getStringProperty("Longlife")));
			System.out.println("DB Value: "+DBEstFcstWgsPrcntTtl); 
			
			String CurrentEstimatedFcstWagePrcntTotalSF = data.getText("CurrentWeekEstimatedFcstWagePrcntTotal");	
			DBEstFcstWgsPrcntTtlSF = sql.CLRexecuteQuery(getContext(), CurrentEstimatedFcstWagePrcntTotalSF.replace("store",getContext().getStringProperty("store")).replace("Division", getContext().getStringProperty("Division")).replace("Week",getContext().getStringProperty("SelectFiscalWeek"))
					.replace("Department",getContext().getStringProperty("SeaFoodDeli")));
			System.out.println("DB Value: "+DBEstFcstWgsPrcntTtlSF); 
		}	
			
				
			EstFcstWgsPrcntTtl = pageDailyTradingStatementStorePO.CombinedWeekToDateTotal("LLWagesPercentage",objDailyTradingStatementObjects, objDailyTradingStatementObjects.EstForecastValue);
			EstFcstWgsPrcntTtlSF = pageDailyTradingStatementStorePO.CombinedWeekToDateTotal("SFWagesPercentage",objDailyTradingStatementObjects, objDailyTradingStatementObjects.EstForecastValue);
	
			pageMonthlyPlanningPO.CompareValueDecimalFields(extentTest,DBEstFcstWgsPrcntTtl,EstFcstWgsPrcntTtl);
			pageMonthlyPlanningPO.CompareValueDecimalFields(extentTest,DBEstFcstWgsPrcntTtlSF,EstFcstWgsPrcntTtlSF);
				
			
			//Variance Sales
			if(OptionToView.contains("Budget"))
			{
				Report_AddStep("testcase", "VARIANCE SALES-UNDER BUDGET VIEW", "", "", "");
				extentTest = report.startTest("Variance Sales - UNDER BUDGET VIEW"); 
				
				if (WeekValue.contains("Past") ) {
				String VarSalesTotal = data.getText("PastWeekVarSalesTotal");	
				DBVarSalesTtl = sql.CLRexecuteQuery(getContext(), VarSalesTotal.replace("store",getContext().getStringProperty("store")).replace("Division", getContext().getStringProperty("Division")).replace("Week",getContext().getStringProperty("SelectFiscalWeek"))
						.replace("Department",getContext().getStringProperty("Longlife")));
				System.out.println("DB Value: "+DBVarSalesTtl); 
				
				String VarSalesTotalSF = data.getText("PastWeekVarSalesTotal");	
				DBVarSalesTtlSF = sql.CLRexecuteQuery(getContext(), VarSalesTotalSF.replace("store",getContext().getStringProperty("store")).replace("Division", getContext().getStringProperty("Division")).replace("Week",getContext().getStringProperty("SelectFiscalWeek"))
						.replace("Department",getContext().getStringProperty("SeaFoodDeli")));
				System.out.println("DB Value: "+DBVarSalesTtlSF); 
				}
				if (WeekValue.contains("Current") ) {
					String VarSalesTotal = data.getText("CurrentWeekVarSalesTotal");	
					DBVarSalesTtl = sql.CLRexecuteQuery(getContext(), VarSalesTotal.replace("store",getContext().getStringProperty("store")).replace("Division", getContext().getStringProperty("Division")).replace("Week",getContext().getStringProperty("SelectFiscalWeek"))
							.replace("Department",getContext().getStringProperty("Longlife")));
					System.out.println("DB Value: "+DBVarSalesTtl); 
					
					String VarSalesTotalSF = data.getText("CurrentWeekVarSalesTotal");	
					DBVarSalesTtlSF = sql.CLRexecuteQuery(getContext(), VarSalesTotalSF.replace("store",getContext().getStringProperty("store")).replace("Division", getContext().getStringProperty("Division")).replace("Week",getContext().getStringProperty("SelectFiscalWeek"))
							.replace("Department",getContext().getStringProperty("SeaFoodDeli")));
					System.out.println("DB Value: "+DBVarSalesTtlSF); 
					}
					
					VarSalesTtl = pageDailyTradingStatementStorePO.CombinedWeekToDateTotal("LLSales",objDailyTradingStatementObjects, objDailyTradingStatementObjects.VarBudgetSales);
					VarSalesTtlSF = pageDailyTradingStatementStorePO.CombinedWeekToDateTotal("SFSales",objDailyTradingStatementObjects, objDailyTradingStatementObjects.VarBudgetSales);
				
				pageMonthlyPlanningPO.CompareValues(extentTest,DBVarSalesTtl,VarSalesTtl);	
				pageMonthlyPlanningPO.CompareValues(extentTest,DBVarSalesTtlSF,VarSalesTtlSF);
						
			}
			if(OptionToView.contains("Forecast"))
			{
				Report_AddStep("testcase", "VARIANCE SALES-UNDER BUDGET VIEW", "", "", "");
				extentTest = report.startTest("Variance Sales - UNDER BUDGET VIEW"); 
				
				if (WeekValue.contains("Past") ) {
				String ForecastVarSalesTotal = data.getText("PastWeekForecastVarSalesTotal");	
				DBVarSalesTtl = sql.CLRexecuteQuery(getContext(), ForecastVarSalesTotal.replace("store",getContext().getStringProperty("store")).replace("Division", getContext().getStringProperty("Division")).replace("Week",getContext().getStringProperty("SelectFiscalWeek"))
						.replace("Department",getContext().getStringProperty("Longlife")));
				System.out.println("DB Value: "+DBVarSalesTtl); 
				
				String ForecastVarSalesTotalSF = data.getText("PastWeekForecastVarSalesTotal");	
				DBVarSalesTtlSF = sql.CLRexecuteQuery(getContext(), ForecastVarSalesTotalSF.replace("store",getContext().getStringProperty("store")).replace("Division", getContext().getStringProperty("Division")).replace("Week",getContext().getStringProperty("SelectFiscalWeek"))
						.replace("Department",getContext().getStringProperty("SeaFoodDeli")));
				System.out.println("DB Value: "+DBVarSalesTtlSF); 
				}
				if (WeekValue.contains("Current") ) {
					String ForecastVarSalesTotal = data.getText("CurrentWeekForecastVarSalesTotal");	
					DBVarSalesTtl = sql.CLRexecuteQuery(getContext(), ForecastVarSalesTotal.replace("store",getContext().getStringProperty("store")).replace("Division", getContext().getStringProperty("Division")).replace("Week",getContext().getStringProperty("SelectFiscalWeek"))
							.replace("Department",getContext().getStringProperty("Longlife")));
					System.out.println("DB Value: "+DBVarSalesTtl); 
					
					String ForecastVarSalesTotalSF = data.getText("CurrentWeekForecastVarSalesTotal");	
					DBVarSalesTtlSF = sql.CLRexecuteQuery(getContext(), ForecastVarSalesTotalSF.replace("store",getContext().getStringProperty("store")).replace("Division", getContext().getStringProperty("Division")).replace("Week",getContext().getStringProperty("SelectFiscalWeek"))
							.replace("Department",getContext().getStringProperty("SeaFoodDeli")));
					System.out.println("DB Value: "+DBVarSalesTtlSF); 
					}
					
					VarSalesTtl = pageDailyTradingStatementStorePO.CombinedWeekToDateTotal("LLSales",objDailyTradingStatementObjects, objDailyTradingStatementObjects.VarBudgetSales);
					VarSalesTtlSF = pageDailyTradingStatementStorePO.CombinedWeekToDateTotal("SFSales",objDailyTradingStatementObjects, objDailyTradingStatementObjects.VarBudgetSales);
				
				
				pageMonthlyPlanningPO.CompareValues(extentTest,DBVarSalesTtl,VarSalesTtl);
				pageMonthlyPlanningPO.CompareValues(extentTest,DBVarSalesTtlSF,VarSalesTtlSF);	
							
			}
			
		//Variance Wages
		if(OptionToView.contains("Budget"))
		{
			Report_AddStep("testcase", "VARIANCE WAGES-UNDER BUDGET VIEW", "", "", "");
			extentTest = report.startTest("Variance Wages - UNDER BUDGET VIEW"); 
			
			if(WeekValue.contains("Past") )
			{												
					String VarWagesTotal = data.getText("PastWeekVarWagesTotal");	
					DBVarWagesTtl = sql.CLRexecuteQuery(getContext(), VarWagesTotal.replace("store",getContext().getStringProperty("store")).replace("Division", getContext().getStringProperty("Division")).replace("Week",getContext().getStringProperty("SelectFiscalWeek"))
							.replace("Department",getContext().getStringProperty("Longlife")));
					System.out.println("DB Value: "+DBVarWagesTtl);
					
					String VarWagesTotalSF = data.getText("PastWeekVarWagesTotal");	
					DBVarWagesTtlSF = sql.CLRexecuteQuery(getContext(), VarWagesTotalSF.replace("store",getContext().getStringProperty("store")).replace("Division", getContext().getStringProperty("Division")).replace("Week",getContext().getStringProperty("SelectFiscalWeek"))
							.replace("Department",getContext().getStringProperty("SeaFoodDeli")));
					System.out.println("DB Value: "+DBVarWagesTtlSF);
			}
			if(WeekValue.contains("Current"))
			{													
				String CurrentVarWagesTotal = data.getText("CurrentWeekVarWagesTotal");	
				DBVarWagesTtl = sql.CLRexecuteQuery(getContext(), CurrentVarWagesTotal.replace("store",getContext().getStringProperty("store")).replace("Division", getContext().getStringProperty("Division")).replace("Week",getContext().getStringProperty("SelectFiscalWeek"))
						.replace("Department",getContext().getStringProperty("Longlife")));
				System.out.println("DB Value: "+DBVarWagesTtl); 
				
				String CurrentVarWagesTotalSF = data.getText("CurrentWeekVarWagesTotal");	
				DBVarWagesTtlSF = sql.CLRexecuteQuery(getContext(), CurrentVarWagesTotalSF.replace("store",getContext().getStringProperty("store")).replace("Division", getContext().getStringProperty("Division")).replace("Week",getContext().getStringProperty("SelectFiscalWeek"))
						.replace("Department",getContext().getStringProperty("SeaFoodDeli")));
				System.out.println("DB Value: "+DBVarWagesTtlSF);
			}	
			
				
				VarWagesTtl = pageDailyTradingStatementStorePO.CombinedWeekToDateTotal("LLWages",objDailyTradingStatementObjects, objDailyTradingStatementObjects.VarBudgetValue);	
				VarWagesTtlSF = pageDailyTradingStatementStorePO.CombinedWeekToDateTotal("SFWages",objDailyTradingStatementObjects, objDailyTradingStatementObjects.VarBudgetValue);
			
			
				pageMonthlyPlanningPO.CompareValues(extentTest,DBVarWagesTtl,VarWagesTtl);
				pageMonthlyPlanningPO.CompareValues(extentTest,DBVarWagesTtlSF,VarWagesTtlSF);	
					
		}
		if(OptionToView.contains("Forecast"))
		{
			Report_AddStep("testcase"," VARIANCE WAGES - FORECAST COLUMN" ,"","", "");
			extentTest.log(LogStatus.INFO, "VARIANCE WAGES - FORECAST COLUMN");
			
			if(WeekValue.contains("Past"))
			{												
					String ForecastVarWagesTotal = data.getText("PastWeekForecastVarWagesTotal");	
					DBVarWagesTtl = sql.CLRexecuteQuery(getContext(), ForecastVarWagesTotal.replace("store",getContext().getStringProperty("store")).replace("Division", getContext().getStringProperty("Division")).replace("Week",getContext().getStringProperty("SelectFiscalWeek"))
							.replace("Department",getContext().getStringProperty("Longlife")));
					System.out.println("DB Value: "+DBVarWagesTtl); 
					
					String ForecastVarWagesTotalSF = data.getText("PastWeekForecastVarWagesTotal");	
					DBVarWagesTtlSF = sql.CLRexecuteQuery(getContext(), ForecastVarWagesTotalSF.replace("store",getContext().getStringProperty("store")).replace("Division", getContext().getStringProperty("Division")).replace("Week",getContext().getStringProperty("SelectFiscalWeek"))
							.replace("Department",getContext().getStringProperty("SeaFoodDeli")));
					System.out.println("DB Value: "+DBVarWagesTtlSF);
			}
			if(WeekValue.contains("Current"))
			{														
				String CurrentForecastVarWagesTotal = data.getText("CurrentWeekForecastVarWagesTotal");	
				DBVarWagesTtl = sql.CLRexecuteQuery(getContext(), CurrentForecastVarWagesTotal.replace("store",getContext().getStringProperty("store")).replace("Division", getContext().getStringProperty("Division")).replace("Week",getContext().getStringProperty("SelectFiscalWeek"))
						.replace("Department",getContext().getStringProperty("Longlife")));
				System.out.println("DB Value: "+DBVarWagesTtl); 
				
				String CurrentForecastVarWagesTotalSF = data.getText("CurrentWeekForecastVarWagesTotal");	
				DBVarWagesTtlSF = sql.CLRexecuteQuery(getContext(), CurrentForecastVarWagesTotalSF.replace("store",getContext().getStringProperty("store")).replace("Division", getContext().getStringProperty("Division")).replace("Week",getContext().getStringProperty("SelectFiscalWeek"))
						.replace("Department",getContext().getStringProperty("SeaFoodDeli")));
				System.out.println("DB Value: "+DBVarWagesTtlSF);
			}
			
				
				VarWagesTtl = pageDailyTradingStatementStorePO.CombinedWeekToDateTotal("LLWages",objDailyTradingStatementObjects, objDailyTradingStatementObjects.VarBudgetValue);	
				VarWagesTtlSF = pageDailyTradingStatementStorePO.CombinedWeekToDateTotal("SFWages",objDailyTradingStatementObjects, objDailyTradingStatementObjects.VarBudgetValue);
			
			

				pageMonthlyPlanningPO.CompareValues(extentTest,DBVarWagesTtl,VarWagesTtl);
				pageMonthlyPlanningPO.CompareValues(extentTest,DBVarWagesTtlSF,VarWagesTtlSF);	
					
		}
			
		//Variance Wages %
		if(OptionToView.contains("Budget"))
		{
			Report_AddStep("testcase"," VARIANCE WAGES PERCENTAGE - BUDGET COLUMN" ,"","", "");
			extentTest.log(LogStatus.INFO, "VARIANCE WAGES PERCENTAGE - BUDGET COLUMN");
			
			if(WeekValue.contains("Past") )
			{											
				String VarWagesPrecentageTotal = data.getText("PastWeekVarWagesPrecentageTotal");					
				DBVarWagesPrcntTtl = sql.CLRexecuteQuery(getContext(), VarWagesPrecentageTotal.replace("store",getContext().getStringProperty("store")).replace("Division", getContext().getStringProperty("Division")).replace("Week",getContext().getStringProperty("SelectFiscalWeek"))
						.replace("Department",getContext().getStringProperty("Longlife")));
				System.out.println("DB Value: "+DBVarWagesPrcntTtl);
				
				String VarWagesPrecentageTotalSF = data.getText("PastWeekVarWagesPrecentageTotal");					
				DBVarWagesPrcntTtlSF = sql.CLRexecuteQuery(getContext(), VarWagesPrecentageTotalSF.replace("store",getContext().getStringProperty("store")).replace("Division", getContext().getStringProperty("Division")).replace("Week",getContext().getStringProperty("SelectFiscalWeek"))
						.replace("Department",getContext().getStringProperty("SeaFoodDeli")));
				System.out.println("DB Value: "+DBVarWagesPrcntTtlSF);
			}
			if(WeekValue.contains("Current"))
			{													
				String CurrentVarWagesPrecentageTotal = data.getText("CurrentWeekVarWagesPrecentageTotal");	
				DBVarWagesPrcntTtl = sql.CLRexecuteQuery(getContext(), CurrentVarWagesPrecentageTotal.replace("store",getContext().getStringProperty("store")).replace("Division", getContext().getStringProperty("Division")).replace("Week",getContext().getStringProperty("SelectFiscalWeek"))
						.replace("Department",getContext().getStringProperty("Longlife")));
				System.out.println("DB Value: "+DBVarWagesPrcntTtl); 
				
				String CurrentVarWagesPrecentageTotalSF = data.getText("CurrentWeekVarWagesPrecentageTotal");	
				DBVarWagesPrcntTtlSF = sql.CLRexecuteQuery(getContext(), CurrentVarWagesPrecentageTotalSF.replace("store",getContext().getStringProperty("store")).replace("Division", getContext().getStringProperty("Division")).replace("Week",getContext().getStringProperty("SelectFiscalWeek"))
						.replace("Department",getContext().getStringProperty("SeaFoodDeli")));
				System.out.println("DB Value: "+DBVarWagesPrcntTtlSF);
			}	
			
			
				VarWagesPrcntTtl = pageDailyTradingStatementStorePO.CombinedWeekToDateTotal("LLWagesPercentage",objDailyTradingStatementObjects, objDailyTradingStatementObjects.VarBudgetValue);	
				VarWagesPrcntTtlSF = pageDailyTradingStatementStorePO.CombinedWeekToDateTotal("SFWagesPercentage",objDailyTradingStatementObjects, objDailyTradingStatementObjects.VarBudgetValue);
			
				pageMonthlyPlanningPO.CompareValueDecimalFields(extentTest,DBVarWagesPrcntTtl,VarWagesPrcntTtl);
				pageMonthlyPlanningPO.CompareValueDecimalFields(extentTest,DBVarWagesPrcntTtlSF,VarWagesPrcntTtlSF);
						
		}
		if(OptionToView.contains("Forecast"))
		{
			Report_AddStep("testcase", "VARIANCE WAGES-UNDER BUDGET VIEW", "", "", "");
			extentTest = report.startTest("Variance Wages - UNDER BUDGET VIEW"); 
			
			if(WeekValue.contains("Past") )
			{														
				String ForecastVarWagesPrecentageTotal = data.getText("PastWeekForecastVarWagesPrecentageTotal");					
				DBVarWagesPrcntTtl = sql.CLRexecuteQuery(getContext(), ForecastVarWagesPrecentageTotal.replace("store",getContext().getStringProperty("store")).replace("Division", getContext().getStringProperty("Division")).replace("Week",getContext().getStringProperty("SelectFiscalWeek"))
						.replace("Department",getContext().getStringProperty("Longlife")));
				System.out.println("DB Value: "+DBVarWagesPrcntTtl);
				
				String ForecastVarWagesPrecentageTotalSF = data.getText("PastWeekForecastVarWagesPrecentageTotal");					
				DBVarWagesPrcntTtlSF = sql.CLRexecuteQuery(getContext(), ForecastVarWagesPrecentageTotalSF.replace("store",getContext().getStringProperty("store")).replace("Division", getContext().getStringProperty("Division")).replace("Week",getContext().getStringProperty("SelectFiscalWeek"))
						.replace("Department",getContext().getStringProperty("SeaFoodDeli")));
				System.out.println("DB Value: "+DBVarWagesPrcntTtlSF);
			}
			if(WeekValue.contains("Current"))
			{													
				String CurrentForecastVarWagesPrecentageTotal = data.getText("CurrentWeekForecastVarWagesPrecentageTotal");	
				DBVarWagesPrcntTtl = sql.CLRexecuteQuery(getContext(), CurrentForecastVarWagesPrecentageTotal.replace("store",getContext().getStringProperty("store")).replace("Division", getContext().getStringProperty("Division")).replace("Week",getContext().getStringProperty("SelectFiscalWeek"))
						.replace("Department",getContext().getStringProperty("Longlife")));
				System.out.println("DB Value: "+DBVarWagesPrcntTtl); 
				
				String CurrentForecastVarWagesPrecentageTotalSF = data.getText("CurrentWeekForecastVarWagesPrecentageTotal");	
				DBVarWagesPrcntTtlSF = sql.CLRexecuteQuery(getContext(), CurrentForecastVarWagesPrecentageTotalSF.replace("store",getContext().getStringProperty("store")).replace("Division", getContext().getStringProperty("Division")).replace("Week",getContext().getStringProperty("SelectFiscalWeek"))
						.replace("Department",getContext().getStringProperty("SeaFoodDeli")));
				System.out.println("DB Value: "+DBVarWagesPrcntTtlSF); 
			}	
			
				
				VarWagesPrcntTtl = pageDailyTradingStatementStorePO.CombinedWeekToDateTotal("LLWagesPercentage",objDailyTradingStatementObjects, objDailyTradingStatementObjects.VarBudgetValue);	
				VarWagesPrcntTtlSF = pageDailyTradingStatementStorePO.CombinedWeekToDateTotal("SFWagesPercentage",objDailyTradingStatementObjects, objDailyTradingStatementObjects.VarBudgetValue);
			
				pageMonthlyPlanningPO.CompareValueDecimalFields(extentTest,DBVarWagesPrcntTtl,VarWagesPrcntTtl);
				pageMonthlyPlanningPO.CompareValueDecimalFields(extentTest,DBVarWagesPrcntTtlSF,VarWagesPrcntTtlSF);	
						
		}	
}	

//////////// WAGES PERCENTAGE ///////////////
@Test(description = "Daily Trading Statement - Store, Wages Values",priority=8)
public void WagesPercentageColumnValidation() throws Exception
{
		String DTSStore = "Daily Trading Statement - Store, WagesPercentage Values";
		extentTest = report.startTest("Daily Trading Statement - Store, WagesPercentage Values");		
		pageWeeklyPlanningPO = new WeeklyPlanningStorePage().createPage(getDriver(), getContext(), log, status, data,getReport(), getReportLogger(), WeeklyPlanningStorePage.class);
		pageDailyTradingStatementStorePO = new MonthlyPlanningGroupPage().createPage(getDriver(), getContext(), log, status, data,getReport(), getReportLogger(), DailyTradingStatementStorePage.class);
		TestStatus status = getTestStatus();
		System.out.println("Entering into TestCase : DTS Store - Items View");
		WeekValue = getContext().getStringProperty("WeekToTest");
		
		//Day wise - Planned WagesPercentage
		Report_AddStep("testcase", " PLANNED WAGE% - WEEK WISE DATA", "", "", "");
		extentTest = report.startTest(" Planned WagesPercentage - Day Wise");
		
		String PlannedWagesPercentageTotal = data.getText("PlannedWagesPercentageTotal");	
		DBPlndValTtl = sql.CLRexecuteQuery(getContext(), PlannedWagesPercentageTotal.replace("store",getContext().getStringProperty("store")).replace("Division", getContext().getStringProperty("Division")).replace("Week",getContext().getStringProperty("SelectFiscalWeek"))
				.replace("Department",getContext().getStringProperty("Longlife")));
		System.out.println("DB Value: "+DBPlndValTtl); 
		
		String PlannedWagesPercentageTotalSF = data.getText("PlannedWagesPercentageTotal");	
		DBPlndValTtlSF = sql.CLRexecuteQuery(getContext(), PlannedWagesPercentageTotalSF.replace("store",getContext().getStringProperty("store")).replace("Division", getContext().getStringProperty("Division")).replace("Week",getContext().getStringProperty("SelectFiscalWeek"))
				.replace("Department",getContext().getStringProperty("SeaFoodDeli")));
		System.out.println("DB Value: "+DBPlndValTtlSF);
	
			
			PlndValTtl = pageDailyTradingStatementStorePO.CombinedDayWiseUITotal("LLWagesPercentage","PlannedValue",objDailyTradingStatementObjects);
			PlndValTtlSF = pageDailyTradingStatementStorePO.CombinedDayWiseUITotal("SFWagesPercentage","PlannedValue",objDailyTradingStatementObjects);
		
		pageDailyTradingStatementStorePO.CompareValueDecimalTotal(extentTest,DBPlndValTtl,PlndValTtl); 
		pageDailyTradingStatementStorePO.CompareValueDecimalTotal(extentTest,DBPlndValTtlSF,PlndValTtlSF);
		
				
		//Day wise - Actual WagesPercentage
		Report_AddStep("testcase"," ACTUAL WAGES PERCENTAGE - WEEK TO DATE DATA" ,"","", "");
		extentTest.log(LogStatus.INFO, "ACTUAL WAGES PERCENTAGE - WEEK TO DATE DATA");
		if (WeekValue.contains("Past") ) {
		String ActualWagesPercentageTotal = data.getText("PastWeekActualWagesPercentageTotal");	
		DBActlTtl = sql.CLRexecuteQuery(getContext(), ActualWagesPercentageTotal.replace("store",getContext().getStringProperty("store")).replace("Division", getContext().getStringProperty("Division")).replace("Week",getContext().getStringProperty("SelectFiscalWeek"))
				.replace("Department",getContext().getStringProperty("Longlife")));
		System.out.println("DB Value: "+DBActlTtl);
		
		String ActualWagesPercentageTotalSF = data.getText("PastWeekActualWagesPercentageTotal");	
		DBActlTtlSF = sql.CLRexecuteQuery(getContext(), ActualWagesPercentageTotalSF.replace("store",getContext().getStringProperty("store")).replace("Division", getContext().getStringProperty("Division")).replace("Week",getContext().getStringProperty("SelectFiscalWeek"))
				.replace("Department",getContext().getStringProperty("SeaFoodDeli")));
		System.out.println("DB Value: "+DBActlTtlSF);
		}
		if (WeekValue.contains("Current") ) {
			String ActualWagesPercentageTotal = data.getText("CurrentWeekActualWagesPercentageTotal");	
			DBActlTtl = sql.CLRexecuteQuery(getContext(), ActualWagesPercentageTotal.replace("store",getContext().getStringProperty("store")).replace("Division", getContext().getStringProperty("Division")).replace("Week",getContext().getStringProperty("SelectFiscalWeek"))
					.replace("Department",getContext().getStringProperty("Longlife")));
			System.out.println("DB Value: "+DBActlTtl);
			
			String ActualWagesPercentageTotalSF = data.getText("CurrentWeekActualWagesPercentageTotal");	
			DBActlTtlSF = sql.CLRexecuteQuery(getContext(), ActualWagesPercentageTotalSF.replace("store",getContext().getStringProperty("store")).replace("Division", getContext().getStringProperty("Division")).replace("Week",getContext().getStringProperty("SelectFiscalWeek"))
					.replace("Department",getContext().getStringProperty("SeaFoodDeli")));
			System.out.println("DB Value: "+DBActlTtlSF);
			}
			ActlValTtl =  pageDailyTradingStatementStorePO.CombinedDayWiseUITotal("LLWagesPercentage","ActualValue",objDailyTradingStatementObjects);
			ActlValTtlSF =  pageDailyTradingStatementStorePO.CombinedDayWiseUITotal("SFWagesPercentage","ActualValue",objDailyTradingStatementObjects);
		
		
		pageDailyTradingStatementStorePO.CompareValueDecimalTotal(extentTest,DBActlTtl,ActlValTtl); 
		pageDailyTradingStatementStorePO.CompareValueDecimalTotal(extentTest,DBActlTtlSF,ActlValTtlSF); 
		
		
		//Week to Date - Planned WagesPercentage
		Report_AddStep("testcase"," PLANNED WAGES PERCENTAGE - WEEK TO DATE DATA" ,"","", "");
		extentTest.log(LogStatus.INFO, "PLANNED WAGES PERCENTAGE - WEEK TO DATE DATA");
		
		String PlannedWagesPercentageWkToDtTotal = data.getText("PlannedWagesPercentageWkToDtTotal");	
		DBPlndValTtlWkToDt = sql.CLRexecuteQuery(getContext(), PlannedWagesPercentageWkToDtTotal.replace("store",getContext().getStringProperty("store")).replace("Division", getContext().getStringProperty("Division")).replace("Week",getContext().getStringProperty("SelectFiscalWeek"))
				.replace("Department",getContext().getStringProperty("Longlife")));
		System.out.println("DB Value: "+DBPlndValTtlWkToDt); 
		
		String PlannedWagesPercentageWkToDtTotalSF = data.getText("PlannedWagesPercentageWkToDtTotal");	
		DBPlndValTtlWkToDtSF = sql.CLRexecuteQuery(getContext(), PlannedWagesPercentageWkToDtTotalSF.replace("store",getContext().getStringProperty("store")).replace("Division", getContext().getStringProperty("Division")).replace("Week",getContext().getStringProperty("SelectFiscalWeek"))
				.replace("Department",getContext().getStringProperty("SeaFoodDeli")));
		System.out.println("DB Value: "+DBPlndValTtlWkToDtSF); 
		
		
			PlndValTtlWkToDt = pageDailyTradingStatementStorePO.CombinedWeekToDateTotal("LLWagesPercentage",objDailyTradingStatementObjects, objDailyTradingStatementObjects.WeekToDatePlndValue);
			PlndValTtlWkToDtSF = pageDailyTradingStatementStorePO.CombinedWeekToDateTotal("SFWagesPercentage",objDailyTradingStatementObjects, objDailyTradingStatementObjects.WeekToDatePlndValue);
		
			pageMonthlyPlanningPO.CompareValueDecimalFields(extentTest,DBPlndValTtlWkToDt,PlndValTtlWkToDt); 
		pageMonthlyPlanningPO.CompareValueDecimalFields(extentTest,DBPlndValTtlWkToDtSF,PlndValTtlWkToDtSF); 
		
						
		//Week to Date - Actual WagesPercentage
		Report_AddStep("testcase", " ACTUAL WAGE% - WEEK WISE DATA", "", "", "");
		extentTest = report.startTest(" Actual WagesPercentage - Day Wise");
		
		if(WeekValue.contains("Past") )
		{
			String ActualWagesPercentageWkToDtTotal = data.getText("PastWeekActualWagesPercentageWkToDtTotal");	
			DBActlTtlWkToDt = sql.CLRexecuteQuery(getContext(), ActualWagesPercentageWkToDtTotal.replace("store",getContext().getStringProperty("store")).replace("Division", getContext().getStringProperty("Division")).replace("Week",getContext().getStringProperty("SelectFiscalWeek"))
					.replace("Department",getContext().getStringProperty("Longlife")));
			System.out.println("DB Value: "+DBActlTtlWkToDt);
			
			String ActualWagesPercentageWkToDtTotalSF = data.getText("PastWeekActualWagesPercentageWkToDtTotal");	
			DBActlTtlWkToDtSF = sql.CLRexecuteQuery(getContext(), ActualWagesPercentageWkToDtTotalSF.replace("store",getContext().getStringProperty("store")).replace("Division", getContext().getStringProperty("Division")).replace("Week",getContext().getStringProperty("SelectFiscalWeek"))
					.replace("Department",getContext().getStringProperty("SeaFoodDeli")));
			System.out.println("DB Value: "+DBActlTtlWkToDtSF);
		}
		if(WeekValue.contains("Current"))
		{
			String CurrentActualWagesPercentageWkToDtTotal = data.getText("CurrentWeekActualWagesPercentageWkToDtTotal");	
			DBActlTtlWkToDt = sql.CLRexecuteQuery(getContext(), CurrentActualWagesPercentageWkToDtTotal.replace("store",getContext().getStringProperty("store")).replace("Division", getContext().getStringProperty("Division")).replace("Week",getContext().getStringProperty("SelectFiscalWeek"))
					.replace("Department",getContext().getStringProperty("Longlife")));
			System.out.println("DB Value: "+DBActlTtlWkToDt);
			
			String CurrentActualWagesPercentageWkToDtTotalSF = data.getText("CurrentWeekActualWagesPercentageWkToDtTotal");	
			DBActlTtlWkToDtSF = sql.CLRexecuteQuery(getContext(), CurrentActualWagesPercentageWkToDtTotalSF.replace("store",getContext().getStringProperty("store")).replace("Division", getContext().getStringProperty("Division")).replace("Week",getContext().getStringProperty("SelectFiscalWeek"))
					.replace("Department",getContext().getStringProperty("SeaFoodDeli")));
			System.out.println("DB Value: "+DBActlTtlWkToDtSF);
		}
		
	
			ActlValTtlWkToDt = pageDailyTradingStatementStorePO.CombinedWeekToDateTotal("LLWagesPercentage",objDailyTradingStatementObjects, objDailyTradingStatementObjects.WeekToDateActlValue);
			ActlValTtlWkToDtSF = pageDailyTradingStatementStorePO.CombinedWeekToDateTotal("SFWagesPercentage",objDailyTradingStatementObjects, objDailyTradingStatementObjects.WeekToDateActlValue);
		
		pageMonthlyPlanningPO.CompareValueDecimalFields(extentTest,DBActlTtlWkToDt,ActlValTtlWkToDt); 
		pageMonthlyPlanningPO.CompareValueDecimalFields(extentTest,DBActlTtlWkToDtSF,ActlValTtlWkToDtSF); 
		
	
		//Week to Date - Variance WagesPercentage
		Report_AddStep("testcase", " VARIANCE WAGE% - WEEK WISE DATA", "", "", "");
		extentTest = report.startTest(" Variance WagesPercentage - Day Wise");
		
		if(WeekValue.contains("Past") )
		{
			String VarWagesPercentageWkToDtTotal = data.getText("PastWeekVarWagesPercentageWkToDtTotal");	
			DBVarWkToDtTtl = sql.CLRexecuteQuery(getContext(), VarWagesPercentageWkToDtTotal.replace("store",getContext().getStringProperty("store")).replace("Division", getContext().getStringProperty("Division")).replace("Week",getContext().getStringProperty("SelectFiscalWeek"))
					.replace("Department",getContext().getStringProperty("Longlife")));
			System.out.println("DB Value: "+DBVarWkToDtTtl);
			
			String VarWagesPercentageWkToDtTotalSF = data.getText("PastWeekVarWagesPercentageWkToDtTotal");	
			DBVarWkToDtTtlSF = sql.CLRexecuteQuery(getContext(), VarWagesPercentageWkToDtTotalSF.replace("store",getContext().getStringProperty("store")).replace("Division", getContext().getStringProperty("Division")).replace("Week",getContext().getStringProperty("SelectFiscalWeek"))
					.replace("Department",getContext().getStringProperty("SeaFoodDeli")));
			System.out.println("DB Value: "+DBVarWkToDtTtlSF);
		}
		if(WeekValue.contains("Current"))
		{
			String CurrentVarWagesPercentageWkToDtTotal = data.getText("CurrentWeekVarWagesPercentageWkToDtTotal");	
			DBVarWkToDtTtl = sql.CLRexecuteQuery(getContext(), CurrentVarWagesPercentageWkToDtTotal.replace("store",getContext().getStringProperty("store")).replace("Division", getContext().getStringProperty("Division")).replace("Week",getContext().getStringProperty("SelectFiscalWeek"))
					.replace("Department",getContext().getStringProperty("Longlife")));
			System.out.println("DB Value: "+DBVarWkToDtTtl);
			
			String CurrentVarWagesPercentageWkToDtTotalSF = data.getText("CurrentWeekVarWagesPercentageWkToDtTotal");	
			DBVarWkToDtTtlSF = sql.CLRexecuteQuery(getContext(), CurrentVarWagesPercentageWkToDtTotalSF.replace("store",getContext().getStringProperty("store")).replace("Division", getContext().getStringProperty("Division")).replace("Week",getContext().getStringProperty("SelectFiscalWeek"))
					.replace("Department",getContext().getStringProperty("SeaFoodDeli")));
			System.out.println("DB Value: "+DBVarWkToDtTtlSF);
		}
		
		
			VarTtlWkToDt = pageDailyTradingStatementStorePO.CombinedWeekToDateTotal("LLWagesPercentage",objDailyTradingStatementObjects, objDailyTradingStatementObjects.WeekToDateVarValue);
			VarTtlWkToDtSF = pageDailyTradingStatementStorePO.CombinedWeekToDateTotal("SFWagesPercentage",objDailyTradingStatementObjects, objDailyTradingStatementObjects.WeekToDateVarValue);
		
		pageMonthlyPlanningPO.CompareValueDecimalFields(extentTest,DBVarWkToDtTtl,VarTtlWkToDt); 
		pageMonthlyPlanningPO.CompareValueDecimalFields(extentTest,DBVarWkToDtTtlSF,VarTtlWkToDtSF); 
		
}	

////////////WAGES ///////////////
@Test(description = "Daily Trading Statement - Store, Wages Values",priority=9)
public void WagesColumnValidation() throws Exception
{
		String DTSStore = "Daily Trading Statement - Store, WagesPercentage Values";
		extentTest = report.startTest("Daily Trading Statement - Store, WagesPercentage Values");		
		pageWeeklyPlanningPO = new WeeklyPlanningStorePage().createPage(getDriver(), getContext(), log, status, data,getReport(), getReportLogger(), WeeklyPlanningStorePage.class);
		pageDailyTradingStatementStorePO = new MonthlyPlanningGroupPage().createPage(getDriver(), getContext(), log, status, data,getReport(), getReportLogger(), DailyTradingStatementStorePage.class);
		TestStatus status = getTestStatus();
		System.out.println("Entering into TestCase : DTS Store - Wages View");
		WeekValue = getContext().getStringProperty("WeekToTest");
				
		//Day Wise - Planned Wages
		Report_AddStep("testcase", " PLANNED WAGES - WEEK WISE DATA", "", "", "");
		extentTest = report.startTest(" Planned Wages - Day Wise");
		
		String PlannedWagesTotal = data.getText("PlannedWagesTotal");	
		DBPlndValTtl = sql.CLRexecuteQuery(getContext(), PlannedWagesTotal.replace("store",getContext().getStringProperty("store")).replace("Division", getContext().getStringProperty("Division")).replace("Week",getContext().getStringProperty("SelectFiscalWeek"))
				.replace("Department",getContext().getStringProperty("Longlife")));
		System.out.println("DB Value: "+DBPlndValTtl); 
		
		String PlannedWagesTotalSF = data.getText("PlannedWagesTotal");	
		DBPlndValTtlSF = sql.CLRexecuteQuery(getContext(), PlannedWagesTotalSF.replace("store",getContext().getStringProperty("store")).replace("Division", getContext().getStringProperty("Division")).replace("Week",getContext().getStringProperty("SelectFiscalWeek"))
				.replace("Department",getContext().getStringProperty("SeaFoodDeli")));
		System.out.println("DB Value: "+DBPlndValTtlSF); 
		
		
			PlndValTtl = pageDailyTradingStatementStorePO.CombinedDayWiseUITotal("LLWages","PlannedValue",objDailyTradingStatementObjects);
			PlndValTtlSF = pageDailyTradingStatementStorePO.CombinedDayWiseUITotal("SFWages","PlannedValue",objDailyTradingStatementObjects);	
		
		pageDailyTradingStatementStorePO.DTSCompareValuesTotal(extentTest,DBPlndValTtl,PlndValTtl); 
		pageDailyTradingStatementStorePO.DTSCompareValuesTotal(extentTest,DBPlndValTtlSF,PlndValTtlSF); 
		
				
		//Day Wise - Actual Wages
		Report_AddStep("testcase", " ACTUAL WAGES - WEEK WISE DATA", "", "", "");
		extentTest = report.startTest(" Actual  Wages - Day Wise");
		
		if (WeekValue.contains("Past") ) {
		String ActualWagesTotal = data.getText("PastWeekActualWagesTotal");	
		DBActlTtl = sql.CLRexecuteQuery(getContext(), ActualWagesTotal.replace("store",getContext().getStringProperty("store")).replace("Division", getContext().getStringProperty("Division")).replace("Week",getContext().getStringProperty("SelectFiscalWeek"))
				.replace("Department",getContext().getStringProperty("Longlife")));
		System.out.println("DB Value: "+DBActlTtl);
		
		String ActualWagesTotalSF = data.getText("PastWeekActualWagesTotal");	
		DBActlTtlSF = sql.CLRexecuteQuery(getContext(), ActualWagesTotalSF.replace("store",getContext().getStringProperty("store")).replace("Division", getContext().getStringProperty("Division")).replace("Week",getContext().getStringProperty("SelectFiscalWeek"))
				.replace("Department",getContext().getStringProperty("SeaFoodDeli")));
		System.out.println("DB Value: "+DBActlTtlSF);
		}
		if (WeekValue.contains("Current") ) {
			String ActualWagesTotal = data.getText("CurrentWeekActualWagesTotal");	
			DBActlTtl = sql.CLRexecuteQuery(getContext(), ActualWagesTotal.replace("store",getContext().getStringProperty("store")).replace("Division", getContext().getStringProperty("Division")).replace("Week",getContext().getStringProperty("SelectFiscalWeek"))
					.replace("Department",getContext().getStringProperty("Longlife")));
			System.out.println("DB Value: "+DBActlTtl);
			
			String ActualWagesTotalSF = data.getText("CurrentWeekActualWagesTotal");	
			DBActlTtlSF = sql.CLRexecuteQuery(getContext(), ActualWagesTotalSF.replace("store",getContext().getStringProperty("store")).replace("Division", getContext().getStringProperty("Division")).replace("Week",getContext().getStringProperty("SelectFiscalWeek"))
					.replace("Department",getContext().getStringProperty("SeaFoodDeli")));
			System.out.println("DB Value: "+DBActlTtlSF);
			}
			
			ActlValTtl = pageDailyTradingStatementStorePO.CombinedDayWiseUITotal("LLWages","ActualValue",objDailyTradingStatementObjects);
			ActlValTtlSF = pageDailyTradingStatementStorePO.CombinedDayWiseUITotal("SFWages","ActualValue",objDailyTradingStatementObjects);
				
		pageDailyTradingStatementStorePO.DTSCompareValuesTotal(extentTest,DBActlTtl,ActlValTtl); 
		pageDailyTradingStatementStorePO.DTSCompareValuesTotal(extentTest,DBActlTtlSF,ActlValTtlSF); 
		
				
		//LY Wages
		Report_AddStep("testcase", " LY WAGES - WEEK WISE DATA", "", "", "");
		extentTest = report.startTest(" LY Wages - Day Wise");
		
		if (WeekValue.contains("Past") ) {
		String LYWagesTotal = data.getText("PastWeekLYWagesTotal");	
		DBLYValTtl = sql.CLRexecuteQuery(getContext(), LYWagesTotal.replace("store",getContext().getStringProperty("store")).replace("Division", getContext().getStringProperty("Division")).replace("LYWk",getContext().getStringProperty("LYWk"))
				.replace("Week",getContext().getStringProperty("SelectFiscalWeek")).replace("Department",getContext().getStringProperty("Longlife")));
		System.out.println("DB Value: "+DBLYValTtl);
		
		String LYWagesTotalSF = data.getText("PastWeekLYWagesTotal");	
		DBLYValTtlSF = sql.CLRexecuteQuery(getContext(), LYWagesTotalSF.replace("store",getContext().getStringProperty("store")).replace("Division", getContext().getStringProperty("Division")).replace("LYWk",getContext().getStringProperty("LYWk"))
				.replace("Week",getContext().getStringProperty("SelectFiscalWeek")).replace("Department",getContext().getStringProperty("SeaFoodDeli")));
		System.out.println("DB Value: "+DBLYValTtlSF);
		}
		if (WeekValue.contains("Current") ) {
			String LYWagesTotal = data.getText("CurrentWeekLYWagesTotal");	
			DBLYValTtl = sql.CLRexecuteQuery(getContext(), LYWagesTotal.replace("store",getContext().getStringProperty("store")).replace("Division", getContext().getStringProperty("Division")).replace("LYWk",getContext().getStringProperty("LYWk"))
					.replace("Week",getContext().getStringProperty("SelectFiscalWeek")).replace("Department",getContext().getStringProperty("Longlife")));
			System.out.println("DB Value: "+DBLYValTtl);
			
			String LYWagesTotalSF = data.getText("CurrentWeekLYWagesTotal");	
			DBLYValTtlSF = sql.CLRexecuteQuery(getContext(), LYWagesTotalSF.replace("store",getContext().getStringProperty("store")).replace("Division", getContext().getStringProperty("Division")).replace("LYWk",getContext().getStringProperty("LYWk"))
					.replace("Week",getContext().getStringProperty("SelectFiscalWeek")).replace("Department",getContext().getStringProperty("SeaFoodDeli")));
			System.out.println("DB Value: "+DBLYValTtlSF);
			}
		
			LYValTtl = pageDailyTradingStatementStorePO.CombinedDayWiseUITotal("LLWages","LYValue",objDailyTradingStatementObjects);	
			LYValTtlSF = pageDailyTradingStatementStorePO.CombinedDayWiseUITotal("SFWages","LYValue",objDailyTradingStatementObjects);	
		
		
		pageDailyTradingStatementStorePO.CompareValueDecimalTotal(extentTest,DBLYValTtl,LYValTtl); 	
		pageDailyTradingStatementStorePO.CompareValueDecimalTotal(extentTest,DBLYValTtlSF,LYValTtlSF); 	
		
			
		//Week to Date - Planned Wages
		Report_AddStep("testcase", " PLANNED WAGES - WEEK TO DATE  DATA", "", "", "");
		extentTest = report.startTest(" Planned Wages - Week to Date");
		
		
		String PlannedWagesWkToDtTotal = data.getText("PlannedWagesWkToDtTotal");	
		DBPlndValTtlWkToDt = sql.CLRexecuteQuery(getContext(), PlannedWagesWkToDtTotal.replace("store",getContext().getStringProperty("store")).replace("Division", getContext().getStringProperty("Division")).replace("Week",getContext().getStringProperty("SelectFiscalWeek"))
				.replace("Department",getContext().getStringProperty("Longlife")));
		System.out.println("DB Value: "+DBPlndValTtlWkToDt); 
		
		String PlannedWagesWkToDtTotalSF = data.getText("PlannedWagesWkToDtTotal");	
		DBPlndValTtlWkToDtSF = sql.CLRexecuteQuery(getContext(), PlannedWagesWkToDtTotalSF.replace("store",getContext().getStringProperty("store")).replace("Division", getContext().getStringProperty("Division")).replace("Week",getContext().getStringProperty("SelectFiscalWeek"))
				.replace("Department",getContext().getStringProperty("SeaFoodDeli")));
		System.out.println("DB Value: "+DBPlndValTtlWkToDtSF); 
		
		
			PlndValTtlWkToDt = pageDailyTradingStatementStorePO.CombinedWeekToDateTotal("LLWages",objDailyTradingStatementObjects, objDailyTradingStatementObjects.WeekToDatePlndValue);
			PlndValTtlWkToDtSF = pageDailyTradingStatementStorePO.CombinedWeekToDateTotal("SFWages",objDailyTradingStatementObjects, objDailyTradingStatementObjects.WeekToDatePlndValue);
		
		pageMonthlyPlanningPO.CompareValues(extentTest,DBPlndValTtlWkToDt,PlndValTtlWkToDt); 
		pageMonthlyPlanningPO.CompareValues(extentTest,DBPlndValTtlWkToDtSF,PlndValTtlWkToDtSF);
		
		
		//Week to Date - Actual Wages
		Report_AddStep("testcase", " ACTUAL WAGES - WEEK TO DATE  DATA", "", "", "");
		extentTest = report.startTest(" ACTUAL Wages - Week to Date");
		
		if(WeekValue.contains("Past") )
		{
			String ActualWagesWkToDtTotal = data.getText("PastWeekActualWagesWkToDtTotal");	
			DBActlTtlWkToDt = sql.CLRexecuteQuery(getContext(), ActualWagesWkToDtTotal.replace("store",getContext().getStringProperty("store")).replace("Division", getContext().getStringProperty("Division")).replace("Week",getContext().getStringProperty("SelectFiscalWeek"))
					.replace("Department",getContext().getStringProperty("Longlife")));
			System.out.println("DB Value: "+DBActlTtlWkToDt);
			
			String ActualWagesWkToDtTotalSF = data.getText("PastWeekActualWagesWkToDtTotal");	
			DBActlTtlWkToDtSF = sql.CLRexecuteQuery(getContext(), ActualWagesWkToDtTotalSF.replace("store",getContext().getStringProperty("store")).replace("Division", getContext().getStringProperty("Division")).replace("Week",getContext().getStringProperty("SelectFiscalWeek"))
					.replace("Department",getContext().getStringProperty("SeaFoodDeli")));
			System.out.println("DB Value: "+DBActlTtlWkToDtSF);
		}
		if(WeekValue.contains("Current"))
		{
			String CurrentActualWagesWkToDtTotal = data.getText("CurrentWeekActualWagesWkToDtTotal");	
			DBActlTtlWkToDt = sql.CLRexecuteQuery(getContext(), CurrentActualWagesWkToDtTotal.replace("store",getContext().getStringProperty("store")).replace("Division", getContext().getStringProperty("Division")).replace("Week",getContext().getStringProperty("SelectFiscalWeek"))
					.replace("Department",getContext().getStringProperty("Longlife")));
			System.out.println("DB Value: "+DBActlTtlWkToDt);
			
			String CurrentActualWagesWkToDtTotalSF = data.getText("CurrentWeekActualWagesWkToDtTotal");	
			DBActlTtlWkToDtSF = sql.CLRexecuteQuery(getContext(), CurrentActualWagesWkToDtTotalSF.replace("store",getContext().getStringProperty("store")).replace("Division", getContext().getStringProperty("Division")).replace("Week",getContext().getStringProperty("SelectFiscalWeek"))
					.replace("Department",getContext().getStringProperty("SeaFoodDeli")));
			System.out.println("DB Value: "+DBActlTtlWkToDtSF);
		}
		
		
			ActlValTtlWkToDt = pageDailyTradingStatementStorePO.CombinedWeekToDateTotal("LLWages",objDailyTradingStatementObjects, objDailyTradingStatementObjects.WeekToDateActlValue);
			ActlValTtlWkToDtSF = pageDailyTradingStatementStorePO.CombinedWeekToDateTotal("SFWages",objDailyTradingStatementObjects, objDailyTradingStatementObjects.WeekToDateActlValue);
		
		pageMonthlyPlanningPO.CompareValues(extentTest,DBActlTtlWkToDt,ActlValTtlWkToDt); 
		pageMonthlyPlanningPO.CompareValues(extentTest,DBActlTtlWkToDtSF,ActlValTtlWkToDtSF); 
		
		
		//Week to Date - LY Wages
		Report_AddStep("testcase", " LY WAGES - WEEK TO DATE  DATA", "", "", "");
		extentTest = report.startTest(" LY Wages - Week to Date");
		
		if(WeekValue.contains("Past"))
		{	
				String LYWagesWkToDtTotal = data.getText("PastWeekLYWagesWkToDtTotal");	
				DBLYValTtlWkToDt = sql.CLRexecuteQuery(getContext(), LYWagesWkToDtTotal.replace("store",getContext().getStringProperty("store")).replace("Division", getContext().getStringProperty("Division")).replace("LYWk",getContext().getStringProperty("LYWk"))
						.replace("Week",getContext().getStringProperty("SelectFiscalWeek")).replace("Department",getContext().getStringProperty("Longlife")));
				System.out.println("DB Value: "+DBLYValTtlWkToDt);
				
				String LYWagesWkToDtTotalSF = data.getText("PastWeekLYWagesWkToDtTotal");	
				DBLYValTtlWkToDtSF = sql.CLRexecuteQuery(getContext(), LYWagesWkToDtTotalSF.replace("store",getContext().getStringProperty("store")).replace("Division", getContext().getStringProperty("Division")).replace("LYWk",getContext().getStringProperty("LYWk"))
						.replace("Week",getContext().getStringProperty("SelectFiscalWeek")).replace("Department",getContext().getStringProperty("SeaFoodDeli")));
				System.out.println("DB Value: "+DBLYValTtlWkToDtSF);
		}
		if(WeekValue.contains("Current"))
		{
				String CurrentLYWagesWkToDtTotal = data.getText("CurrentWeekLYWagesWkToDtTotal");	
				DBLYValTtlWkToDt = sql.CLRexecuteQuery(getContext(), CurrentLYWagesWkToDtTotal.replace("store",getContext().getStringProperty("store")).replace("Division", getContext().getStringProperty("Division")).replace("LYWk",getContext().getStringProperty("LYWk"))
						.replace("Week",getContext().getStringProperty("SelectFiscalWeek")).replace("Department",getContext().getStringProperty("Longlife")));
				System.out.println("DB Value: "+DBLYValTtlWkToDt);
				
				String CurrentLYWagesWkToDtTotalSF = data.getText("CurrentWeekLYWagesWkToDtTotal");	
				DBLYValTtlWkToDtSF = sql.CLRexecuteQuery(getContext(), CurrentLYWagesWkToDtTotalSF.replace("store",getContext().getStringProperty("store")).replace("Division", getContext().getStringProperty("Division")).replace("LYWk",getContext().getStringProperty("LYWk"))
						.replace("Week",getContext().getStringProperty("SelectFiscalWeek")).replace("Department",getContext().getStringProperty("SeaFoodDeli")));
				System.out.println("DB Value: "+DBLYValTtlWkToDtSF);
		}
		
		
			LYValTtlWkToDt =  pageDailyTradingStatementStorePO.CombinedWeekToDateTotal("LLWages",objDailyTradingStatementObjects, objDailyTradingStatementObjects.WeekToDateLYValue);
			LYValTtlWkToDtSF =  pageDailyTradingStatementStorePO.CombinedWeekToDateTotal("SFWages",objDailyTradingStatementObjects, objDailyTradingStatementObjects.WeekToDateLYValue);
		
		pageMonthlyPlanningPO.CompareValueDecimalFields(extentTest,DBLYValTtlWkToDt,LYValTtlWkToDt);
		pageMonthlyPlanningPO.CompareValueDecimalFields(extentTest,DBLYValTtlWkToDtSF,LYValTtlWkToDtSF);
				
		
		//Week to Date - Variance Wages
		Report_AddStep("testcase", " VARIANCE WAGES - WEEK TO DATE  DATA", "", "", "");
		extentTest = report.startTest(" Variance Wages - Week to Date");
		
		if(WeekValue.contains("Past") )
		{
			String VarWagesWkToDtTotal = data.getText("PastWeekVarWagesWkToDtTotal");	
			DBVarWkToDtTtl = sql.CLRexecuteQuery(getContext(), VarWagesWkToDtTotal.replace("store",getContext().getStringProperty("store")).replace("Division", getContext().getStringProperty("Division")).replace("Week",getContext().getStringProperty("SelectFiscalWeek"))
					.replace("Department",getContext().getStringProperty("Longlife")));
			System.out.println("DB Value: "+DBVarWkToDtTtl);
			
			String VarWagesWkToDtTotalSF = data.getText("PastWeekVarWagesWkToDtTotal");	
			DBVarWkToDtTtlSF = sql.CLRexecuteQuery(getContext(), VarWagesWkToDtTotalSF.replace("store",getContext().getStringProperty("store")).replace("Division", getContext().getStringProperty("Division")).replace("Week",getContext().getStringProperty("SelectFiscalWeek"))
					.replace("Department",getContext().getStringProperty("SeaFoodDeli")));
			System.out.println("DB Value: "+DBVarWkToDtTtlSF);
		}
		if(WeekValue.contains("Current"))
		{
			String CurrentVarWagesWkToDtTotal = data.getText("CurrentWeekVarWagesWkToDtTotal");	
			DBVarWkToDtTtl = sql.CLRexecuteQuery(getContext(), CurrentVarWagesWkToDtTotal.replace("store",getContext().getStringProperty("store")).replace("Division", getContext().getStringProperty("Division")).replace("Week",getContext().getStringProperty("SelectFiscalWeek"))
					.replace("Department",getContext().getStringProperty("Longlife")));
			System.out.println("DB Value: "+DBVarWkToDtTtl);
			
			String CurrentVarWagesWkToDtTotalSF = data.getText("CurrentWeekVarWagesWkToDtTotal");	
			DBVarWkToDtTtlSF = sql.CLRexecuteQuery(getContext(), CurrentVarWagesWkToDtTotalSF.replace("store",getContext().getStringProperty("store")).replace("Division", getContext().getStringProperty("Division")).replace("Week",getContext().getStringProperty("SelectFiscalWeek"))
					.replace("Department",getContext().getStringProperty("SeaFoodDeli")));
			System.out.println("DB Value: "+DBVarWkToDtTtlSF);
		}
		
		
			VarTtlWkToDt = pageDailyTradingStatementStorePO.CombinedWeekToDateTotal("LLWages",objDailyTradingStatementObjects, objDailyTradingStatementObjects.WeekToDateVarValue);
			VarTtlWkToDtSF = pageDailyTradingStatementStorePO.CombinedWeekToDateTotal("SFWages",objDailyTradingStatementObjects, objDailyTradingStatementObjects.WeekToDateVarValue);
		
		
		pageMonthlyPlanningPO.CompareValues(extentTest,DBVarWkToDtTtl,VarTtlWkToDt);
		pageMonthlyPlanningPO.CompareValues(extentTest,DBVarWkToDtTtlSF,VarTtlWkToDtSF); 		 
}	

	

@Test(priority = 10)
public void LogoutCLRApplication() throws Exception {
	pageWeeklyPlanningPO = new WeeklyPlanningStorePage().createPage(getDriver(), getContext(), log, status, data,
			getReport(), getReportLogger(), WeeklyPlanningStorePage.class);
	pageWeeklyPlanningPO.LogOFF(objWeeklyPlanningStoreObjects);
}

@BeforeMethod
public void setUpItemPrice(Method m, ITestContext testcontext) throws IOException {
	System.out.println("test1 - beforemethod");
	mName = m.getName().toString().trim();
	System.out.println(m.getName().toString().trim());
	if (!methodList.contains(m.getName().toString().trim())) {
		methodList.add(m.getName().toString().trim());
		extentTest = report.startTest(m.getName().toString().trim());
	}
	System.out.println(methodList.size());
	// initializeTestcontext();

	System.out.println("test1 - beforemethod");
	log.info("Loading Testdata for the test case: " + m.getName().toString());
	data.getTCNameFromChildren(m.getName().toString().trim());
	testcontext.setAttribute("WebDriver", getDriver());
	testcontext.setAttribute("Customreports", this.customreport);
	customreport.startTest(m.getName().toString());
	initializeTestStatus();
	reportPath = new File(getContext().getStringProperty("reportPath"));
	moduledesc = m.getName().toString().trim();
	dtmoduledesc = m.getAnnotation(Test.class).description();
	testcasesttime = new Date();
	pageWeeklyPlanningPO = PageFactory.initElements(this.getDriver(), WeeklyPlanningStorePage.class);
	pageDailyTradingStatementStorePO = PageFactory.initElements(this.getDriver(),
			DailyTradingStatementStorePage.class);
	BaseObj = PageFactory.initElements(this.getDriver(), BasePage.class);
	common = PageFactory.initElements(this.getDriver(), CommonFunctions.class);
	storeprop = PageFactory.initElements(this.getDriver(), StoreProperties.class);
	objWeeklyPlanningStoreObjects = PageFactory.initElements(this.getDriver(), WeeklyPlanningStoreObjects.class);
	objDailyTradingStatementObjects = PageFactory.initElements(this.getDriver(),
			DailyTradingStatementObjects.class);
	pageDailyTradingStatementGroupPO = PageFactory.initElements(this.getDriver(),
			DailyTradingStatementGroupPage.class);
	pageMonthlyPlanningPO = PageFactory.initElements(this.getDriver(), MonthlyPlanningStorePage.class);
	ScreenShotUtil = PageFactory.initElements(this.getDriver(), ExtentReportsScreenshotCode.class);
	System.out.println(System.getProperty("user.dir"));
	Date date = new Date();
	SimpleDateFormat sdf = new SimpleDateFormat("MMddyyyy_hmmss");
	String formattedDate = sdf.format(date);
	System.out.println(formattedDate); // 12/01/2011 4:48:16 PM
	DateFormat dateFormat = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
	String formattedDate1 = dateFormat.format(date);
	System.out.println(formattedDate1); // 12/01/2011 4:48:16 PM
	String dat = formattedDate1;
	Report_Header("testcase", reportPath, moduledesc, dat);

}

@BeforeClass
public void loadXMLFile() {
	try {
		cName = this.getClass().getSimpleName();
		reportPath = new File(getContext().getStringProperty("reportPath"));
		report = new ExtentReports(
				getContext().getStringProperty("reportPath") + "\\" + this.getClass().getSimpleName() + ".html");
		classList.add(this.getClass().getSimpleName());
		initializeTestcontext();
		sql.CLRConnectDB(context);
		System.out.println("test1 - Beforeclass");
		data = new XMLDataReader(log);
		customreport = new CustomExtentReports();
		createDriver(getContext().getStringProperty("WLPUrl"), getContext().getStringProperty("browser"), cName,
				mName, extentTest, driver1);
		log.info("Loading data for the class: ");
			data.loadTestDataXML(System.getProperty("user.dir") + "\\TestData\\WLP\\CombinedDailyTradingStatementStore.xml");
			customreport.createExtentReport();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

}