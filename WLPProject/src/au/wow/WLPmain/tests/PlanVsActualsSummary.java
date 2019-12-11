package au.wow.WLPmain.tests;

import java.io.File;
import java.io.IOException;
import java.lang.reflect.Method;
import java.sql.SQLException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.support.PageFactory;
import org.testng.ITestContext;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import com.relevantcodes.extentreports.ExtentReports;
import com.relevantcodes.extentreports.LogStatus;

import au.wow.WLPmain.objects.DTS_DepartmentObjects;
import au.wow.WLPmain.objects.DailyTradingStatementObjects;
import au.wow.WLPmain.objects.PlanVsActualsObjects;
import au.wow.WLPmain.objects.SalesHistoryCalendarObjects;
import au.wow.WLPmain.objects.WeeklyPlanningStoreObjects;
import au.wow.WLPmain.pages.CommonFunctions;
import au.wow.WLPmain.pages.DTS_DepartmentPage;
import au.wow.WLPmain.pages.DailyTradingStatementGroupPage;
import au.wow.WLPmain.pages.DailyTradingStatementStorePage;
import au.wow.WLPmain.pages.MonthlyPlanningGroupPage;
import au.wow.WLPmain.pages.MonthlyPlanningStorePage;
import au.wow.WLPmain.pages.WeeklyDepartmentPlanningPage;
import au.wow.WLPmain.pages.WeeklyPlanningStorePage;
import au.wow.WLPmain.pages.WoWLoginPage;
import au.wow.wlp.utils.BasePage;
import au.wow.wlp.utils.CommonRepository;
import au.wow.wlp.utils.CustomExtentReports;
import au.wow.wlp.utils.ExtentReportsScreenshotCode;
import au.wow.wlp.utils.SQLWrapper;
import au.wow.wlp.utils.TestBase;
import au.wow.wlp.utils.TestStatus;
import au.wow.wlp.utils.XMLDataReader;

public class PlanVsActualsSummary extends TestBase {
	protected Logger log = LogManager.getLogger(CR_05_DTS_Departments.class);
	TestStatus status;
	ExtentReportsScreenshotCode ScreenShotUtil;
	SQLWrapper sql = new SQLWrapper(log);
	SQLWrapper sql1 = new SQLWrapper(log);
	WoWLoginPage loginPage;
	CommonRepository CommonFunc;
	WeeklyPlanningStoreObjects objWeeklyPlanningStoreObjects;
	DailyTradingStatementObjects objDailyTradingStatementObjects;
	WeeklyPlanningStorePage pageWeeklyPlanningPO;
	MonthlyPlanningStorePage pageMonthlyPlanningPO;
	DailyTradingStatementStorePage pageDailyTradingStatementStorePO;
	DailyTradingStatementGroupPage pageDailyTradingStatementGroupPO;
	MonthlyPlanningGroupPage pageMonthlyPlanningGroupPO;
	DTS_DepartmentPage pageDTSDepartmentPO;
	PlanVsActualsObjects objPlanVsActualsObjects;
	BasePage BaseObj;
	CommonFunctions common;
	StoreProperties storeprop;
	
	List<List<Object>> UIValues,UITotal,DBPlndWgTtlLL,PlndWgTtlLL,DBPlndWgTtlSFD,PlndWgTtlSFD,DBPlndHrTtlLL,PlndHrTtlLL,DBPlndHrTtlSFD,PlndHrTtlSFD,DBActlWgTtlDyWiseLL,ActlWgTtlDyWiseLL,
	DBActlWgTtlDyWiseSFD,ActlWgTtlDyWiseSFD,DBActlHrTtlDyWiseLL,ActlHrTtlDyWiseLL,DBActlHrTtlDyWiseSFD,ActlHrTtlDyWiseSFD,DBActlWgTtlLL,ActlWgTtlLL,
	DBActlWgTtlSFD,ActlWgTtlSFD,DBActlHrTtlLL,ActlHrTtlLL,DBActlHrTtlSFD,ActlHrTtlSFD;
	List<List<Object>> DBWages,DBHours,DBKronosWages,DBKronosHours,DBPlndWg,PlndWg,DBPlndHr,PlndHr,DBPlndWgTtl,PlndWgTtl,DBPlndHrTtl,PlndHrTtl,
	DBActlWg,ActlWg,DBActlHr,ActlHr,DBActlWgTtl,ActlWgTtl,DBActlHrTtl,ActlHrTtl,DBActlWgDyWise,ActlWgDyWise,DBActlHrDyWise,ActlHrDyWise,DBActlWgTtlDyWise,ActlWgTtlDyWise,
	DBActlHrTtlDyWise,ActlHrTtlDyWise;
	
	public PlanVsActualsSummary()
	{
		super.log=log;
	}

	@Test(priority=0)
	public void LogintoCLRApplication() throws Exception
	{
		loginPage = new WoWLoginPage().createPage(getDriver(), getContext(), log, status, data,report,extentTest, WoWLoginPage.class);
		pageWeeklyPlanningPO = loginPage.navToWeeklyPlanningStorePg(getContext().getStringProperty("store"));
	}
	
	////////////  Plan Vs Actual Daily Summary ///////////////
	@Test(description = "PlanVsActualDailySummary_WagesandHoursValidation",priority=1)
	public void WagesandHoursValidation() throws Exception
	{
		String PlanVsActualDailySummary = "PlanVsActualDailySummary";
		extentTest = report.startTest("Daily Trading Statement - Department, Sales Values");		
		pageDTSDepartmentPO = new DTS_DepartmentPage().createPage(getDriver(), getContext(), log, status, data,getReport(), getReportLogger(), DTS_DepartmentPage.class);
		pageWeeklyPlanningPO = new WeeklyPlanningStorePage().createPage(getDriver(), getContext(), log, status, data,getReport(), getReportLogger(), WeeklyPlanningStorePage.class);
		pageDailyTradingStatementStorePO = new MonthlyPlanningGroupPage().createPage(getDriver(), getContext(), log, status, data,getReport(), getReportLogger(), DailyTradingStatementStorePage.class);
		pageDailyTradingStatementGroupPO = new MonthlyPlanningGroupPage().createPage(getDriver(), getContext(), log, status, data,getReport(), getReportLogger(), DailyTradingStatementGroupPage.class);
		TestStatus status = getTestStatus();
		System.out.println("Welcome "+getContext().getStringProperty("username"));
		pageWeeklyPlanningPO.SelectWeekFromDropdown(objWeeklyPlanningStoreObjects, extentTest,
				getContext().getStringProperty("SelectFiscalWeek"));
		pageWeeklyPlanningPO.selectPage(extentTest, objWeeklyPlanningStoreObjects.menuBar,
				objWeeklyPlanningStoreObjects.planvsactualscreen, "Plan Vs Actual");
		pageWeeklyPlanningPO.selectPage(extentTest, objWeeklyPlanningStoreObjects.menuBar,
				objWeeklyPlanningStoreObjects.PlanvActualDailySummary, "Plan v Actual Daily Summary");
		/*pageWeeklyPlanningPO.selectPage(extentTest, objWeeklyPlanningStoreObjects.menuBar,
				objWeeklyPlanningStoreObjects.DeptPlnscreenToView, "Weekly Department Planning");*/
		
		//Planned Wages
		Report_AddStep("testcase","PLAN VS ACTUALS DAILY SUMMARY - PLANNED WAGES  " ,"","", "");	
		extentTest.log(LogStatus.INFO, "PLAN VS ACTUALS DAILY SUMMARY - PLANNED WAGES");
		
		String PlannedWages = data.getText("PlannedWages");	
		DBPlndWg = sql.CLRexecuteQuery(getContext(), PlannedWages.replace("store",getContext().getStringProperty("store")).replace("Week",getContext().getStringProperty("SelectFiscalWeek"))
				.replace("Division",getContext().getStringProperty("Division")));
		System.out.println("DB Value: "+DBPlndWg); 
		
		String PlannedWagesTotal = data.getText("PlannedWagesTotal");	
		DBPlndWgTtl = sql.CLRexecuteQuery(getContext(), PlannedWagesTotal.replace("store",getContext().getStringProperty("store")).replace("Week",getContext().getStringProperty("SelectFiscalWeek"))
				.replace("Division",getContext().getStringProperty("Division")));
		System.out.println("DB Value: "+DBPlndWgTtl); 
	
		PlndWg =  pageDTSDepartmentPO.PlnVsActlUIResults("Wages",objPlanVsActualsObjects.Departments, objPlanVsActualsObjects.PlannedWagesandHours,objPlanVsActualsObjects.Table);
		PlndWgTtl = pageDTSDepartmentPO.PlnVsActlUITotal("Wages",objPlanVsActualsObjects.StoreTotal, objPlanVsActualsObjects.PlannedWagesandHoursTtl);
		
		pageMonthlyPlanningPO.CompareValues(extentTest,DBPlndWg,PlndWg);	
		pageMonthlyPlanningPO.CompareValues(extentTest,DBPlndWgTtl,PlndWgTtl);
		
		Report_AddStep("testcase","PLAN VS ACTUALS DAILY SUMMARY - PLANNED WAGES LONGLIFE AND SEAFOOD&DELI  " ,"","", "");	
		extentTest.log(LogStatus.INFO, "PLAN VS ACTUALS DAILY SUMMARY - PLANNED WAGES");
		
		String PlannedWagesTotalLL = data.getText("PlannedWagesTotalLL");	
		DBPlndWgTtlLL = sql.CLRexecuteQuery(getContext(), PlannedWagesTotalLL.replace("store",getContext().getStringProperty("store")).replace("Week",getContext().getStringProperty("SelectFiscalWeek"))
				.replace("Division",getContext().getStringProperty("Division")).replace("Department",getContext().getStringProperty("Longlife")));
		System.out.println("DB Value: "+DBPlndWgTtlLL); 
				
		PlndWgTtlLL = pageDTSDepartmentPO.PlnVsActlUITotal("Wages",objPlanVsActualsObjects.RowNameTotalLL, objPlanVsActualsObjects.PlannedWagesandHoursTtlLL);
	
         pageMonthlyPlanningPO.CompareValues(extentTest,DBPlndWgTtlLL,PlndWgTtlLL);
     	String PlannedWagesTotalSFD = data.getText("PlannedWagesTotalSFD");
		
		DBPlndWgTtlSFD = sql.CLRexecuteQuery(getContext(), PlannedWagesTotalSFD.replace("store",getContext().getStringProperty("store")).replace("Week",getContext().getStringProperty("SelectFiscalWeek"))
				.replace("Division",getContext().getStringProperty("Division")).replace("Department",getContext().getStringProperty("SeaFoodDeli")));
		System.out.println("DB Value: "+DBPlndWgTtlSFD);
		PlndWgTtlSFD = pageDTSDepartmentPO.PlnVsActlUITotal("Wages",objPlanVsActualsObjects.RowNameTotalSFD, objPlanVsActualsObjects.PlannedWagesandHoursTtlSFD);
		
		pageMonthlyPlanningPO.CompareValues(extentTest,DBPlndWgTtlSFD,PlndWgTtlSFD);
				
		//Planned Hours
		Report_AddStep("testcase"," PLAN VS ACTUALS DAILY SUMMARY - PLANNED HOURS " ,"","", "");
		extentTest.log(LogStatus.INFO, "PLAN VS ACTUALS DAILY SUMMARY - PLANNED HOURS");
		
		String PlannedHours = data.getText("PlannedHours");	
		DBPlndHr = sql.CLRexecuteQuery(getContext(), PlannedHours.replace("store",getContext().getStringProperty("store")).replace("Week",getContext().getStringProperty("SelectFiscalWeek"))
				.replace("Division",getContext().getStringProperty("Division")));
		System.out.println("DB Value: "+DBPlndHr); 
		
		String PlannedHoursTotal = data.getText("PlannedHoursTotal");	
		DBPlndHrTtl = sql.CLRexecuteQuery(getContext(), PlannedHoursTotal.replace("store",getContext().getStringProperty("store")).replace("Week",getContext().getStringProperty("SelectFiscalWeek"))
				.replace("Division",getContext().getStringProperty("Division")));
		System.out.println("DB Value: "+DBPlndHrTtl); 
	
		PlndHr =  pageDTSDepartmentPO.PlnVsActlUIResults("Hours",objPlanVsActualsObjects.Departments,objPlanVsActualsObjects.PlannedWagesandHours,objPlanVsActualsObjects.Table);
		PlndHrTtl = pageDTSDepartmentPO.PlnVsActlUITotal("Hours",objPlanVsActualsObjects.StoreTotal, objPlanVsActualsObjects.PlannedWagesandHoursTtl);
		
		pageMonthlyPlanningPO.CompareValueDecimalFields(extentTest,DBPlndHr,PlndHr);	
		pageMonthlyPlanningPO.CompareValueDecimalFields(extentTest,DBPlndHrTtl,PlndHrTtl);
		
		Report_AddStep("testcase"," PLAN VS ACTUALS DAILY SUMMARY - PLANNED HOURS LONGLIFE AND SEAFOOD&DELI " ,"","", "");
		extentTest.log(LogStatus.INFO, "PLAN VS ACTUALS DAILY SUMMARY - PLANNED HOURS");
		
		
		String PlannedHoursTotalLL = data.getText("PlannedHoursTotalLL");	
		DBPlndHrTtlLL = sql.CLRexecuteQuery(getContext(), PlannedHoursTotalLL.replace("store",getContext().getStringProperty("store")).replace("Week",getContext().getStringProperty("SelectFiscalWeek"))
				.replace("Division",getContext().getStringProperty("Division")).replace("Department",getContext().getStringProperty("Longlife")));
		System.out.println("DB Value: "+DBPlndHrTtlLL); 
		
		PlndHrTtlLL = pageDTSDepartmentPO.PlnVsActlUITotal("Hours",objPlanVsActualsObjects.RowNameTotalLL, objPlanVsActualsObjects.PlannedWagesandHoursTtlLL);
		pageMonthlyPlanningPO.CompareValueDecimalFields(extentTest,DBPlndHrTtlLL,PlndHrTtlLL);
		
		String PlannedHoursTotalSFD = data.getText("PlannedHoursTotalSFD");
		DBPlndHrTtlSFD = sql.CLRexecuteQuery(getContext(), PlannedHoursTotalSFD.replace("store",getContext().getStringProperty("store")).replace("Week",getContext().getStringProperty("SelectFiscalWeek"))
				.replace("Division",getContext().getStringProperty("Division")).replace("Department",getContext().getStringProperty("SeaFoodDeli")));
		System.out.println("DB Value: "+DBPlndHrTtlSFD); 		
		PlndHrTtlSFD = pageDTSDepartmentPO.PlnVsActlUITotal("Hours",objPlanVsActualsObjects.RowNameTotalSFD, objPlanVsActualsObjects.PlannedWagesandHoursTtlSFD);
			
		pageMonthlyPlanningPO.CompareValueDecimalFields(extentTest,DBPlndHrTtlSFD,PlndHrTtlSFD);
		
		//Actual Wages - Day Wise
		Report_AddStep("testcase","PLAN VS ACTUALS DAILY SUMMARY - ACTUAL WAGES : DAY WISE  " ,"","", "");	
		extentTest.log(LogStatus.INFO, "PLAN VS ACTUALS DAILY SUMMARY - ACTUAL WAGES : DAY WISE");
					
		String ActualWages = data.getText("ActualWages");	
		DBActlWgDyWise = sql.CLRexecuteQuery(getContext(), ActualWages.replace("store",getContext().getStringProperty("store")).replace("Week",getContext().getStringProperty("SelectFiscalWeek"))
				.replace("Division",getContext().getStringProperty("Division")));
		System.out.println("DB Value: "+DBActlWgDyWise); 
						
		String ActualWagesTotal = data.getText("ActualWagesTotal");	
		DBActlWgTtlDyWise = sql.CLRexecuteQuery(getContext(), ActualWagesTotal.replace("store",getContext().getStringProperty("store")).replace("Week",getContext().getStringProperty("SelectFiscalWeek"))
				.replace("Division",getContext().getStringProperty("Division")));
		System.out.println("DB Value: "+DBActlWgTtlDyWise); 
					
		ActlWgDyWise =  pageDTSDepartmentPO.PlnVsActlUIResultsDayWise("Wages",objPlanVsActualsObjects.Departments, objPlanVsActualsObjects.MondayWages, objPlanVsActualsObjects.TuesdayWages, objPlanVsActualsObjects.WednesdayWages,
				 objPlanVsActualsObjects.ThursdayWages, objPlanVsActualsObjects.FridayWages, objPlanVsActualsObjects.SaturdayWages,objPlanVsActualsObjects.SundayWages,objPlanVsActualsObjects.Table);
		ActlWgTtlDyWise = pageDTSDepartmentPO.PlnVsActlUITotalDayWise("Wages",objPlanVsActualsObjects.StoreTotal, objPlanVsActualsObjects.MondayWagesTtl, objPlanVsActualsObjects.TuesdayWagesTtl, objPlanVsActualsObjects.WednesdayWagesTtl,
				 objPlanVsActualsObjects.ThursdayWagesTtl, objPlanVsActualsObjects.FridayWagesTtl, objPlanVsActualsObjects.SaturdayWagesTtl,objPlanVsActualsObjects.SundayWagesTtl,objPlanVsActualsObjects.Table);
						
		pageDailyTradingStatementStorePO.DTSCompareValuesTotal(extentTest,DBActlWgDyWise,ActlWgDyWise);	
		pageDailyTradingStatementStorePO.DTSCompareValuesTotal(extentTest,DBActlWgTtlDyWise,ActlWgTtlDyWise);
		
		Report_AddStep("testcase","PLAN VS ACTUALS DAILY SUMMARY - ACTUAL WAGES : DAY WISE LONGLIFE AND SEAFOOD&DELI " ,"","", "");	
		extentTest.log(LogStatus.INFO, "PLAN VS ACTUALS DAILY SUMMARY - ACTUAL WAGES : DAY WISE");
		
		String ActualWagesTotalLL = data.getText("ActualWagesTotalLL");	
		DBActlWgTtlDyWiseLL = sql.CLRexecuteQuery(getContext(), ActualWagesTotalLL.replace("store",getContext().getStringProperty("store")).replace("Week",getContext().getStringProperty("SelectFiscalWeek"))
				.replace("Division",getContext().getStringProperty("Division")).replace("Department",getContext().getStringProperty("Longlife")));
		System.out.println("DB Value: "+DBActlWgTtlDyWiseLL); 
					
		
		ActlWgTtlDyWiseLL = pageDTSDepartmentPO.PlnVsActlUITotalDayWise("Wages",objPlanVsActualsObjects.RowNameTotalLL, objPlanVsActualsObjects.MondayWagesLL, objPlanVsActualsObjects.TuesdayWagesLL, objPlanVsActualsObjects.WednesdayWagesLL,
				 objPlanVsActualsObjects.ThursdayWagesLL, objPlanVsActualsObjects.FridayWagesLL, objPlanVsActualsObjects.SaturdayWagesLL,objPlanVsActualsObjects.SundayWagesLL,objPlanVsActualsObjects.Table);
	
		pageDailyTradingStatementStorePO.DTSCompareValuesTotal(extentTest,DBActlWgTtlDyWiseLL,ActlWgTtlDyWiseLL);
		
		String ActualWagesTotalSFD = data.getText("ActualWagesTotalSFD");	
		DBActlWgTtlDyWiseSFD = sql.CLRexecuteQuery(getContext(), ActualWagesTotalSFD.replace("store",getContext().getStringProperty("store")).replace("Week",getContext().getStringProperty("SelectFiscalWeek"))
				.replace("Division",getContext().getStringProperty("Division")).replace("Department",getContext().getStringProperty("SeaFoodDeli")));
		System.out.println("DB Value: "+DBActlWgTtlDyWiseSFD); 					
		
		ActlWgTtlDyWiseSFD = pageDTSDepartmentPO.PlnVsActlUITotalDayWise("Wages",objPlanVsActualsObjects.RowNameTotalSFD, objPlanVsActualsObjects.MondayWagesSFD, objPlanVsActualsObjects.TuesdayWagesSFD, objPlanVsActualsObjects.WednesdayWagesSFD,
				 objPlanVsActualsObjects.ThursdayWagesSFD, objPlanVsActualsObjects.FridayWagesSFD, objPlanVsActualsObjects.SaturdayWagesSFD,objPlanVsActualsObjects.SundayWagesSFD,objPlanVsActualsObjects.Table);
			
		pageDailyTradingStatementStorePO.DTSCompareValuesTotal(extentTest,DBActlWgTtlDyWiseSFD,ActlWgTtlDyWiseSFD);	
		
		//Actual Hours - Day Wise
		Report_AddStep("testcase","PLAN VS ACTUALS DAILY SUMMARY - ACTUAL HOURS : DAY WISE  " ,"","", "");	
		extentTest.log(LogStatus.INFO, "PLAN VS ACTUALS DAILY SUMMARY - ACTUAL HOURS : DAY WISE  ");
		
		String ActualHours = data.getText("ActualHours");	
		DBActlHrDyWise = sql.CLRexecuteQuery(getContext(), ActualHours.replace("store",getContext().getStringProperty("store")).replace("Week",getContext().getStringProperty("SelectFiscalWeek"))
				.replace("Division",getContext().getStringProperty("Division")));
		System.out.println("DB Value: "+DBActlHrDyWise); 
								
		String ActualHoursTotal = data.getText("ActualHoursTotal");	
		DBActlHrTtlDyWise = sql.CLRexecuteQuery(getContext(), ActualHoursTotal.replace("store",getContext().getStringProperty("store")).replace("Week",getContext().getStringProperty("SelectFiscalWeek"))
				.replace("Division",getContext().getStringProperty("Division")));
		System.out.println("DB Value: "+DBActlHrTtlDyWise); 
							
		ActlHrDyWise =  pageDTSDepartmentPO.PlnVsActlUIResultsDayWise("Hours",objPlanVsActualsObjects.Departments, objPlanVsActualsObjects.MondayHours, objPlanVsActualsObjects.TuesdayHours, objPlanVsActualsObjects.WednesdayHours,
					 objPlanVsActualsObjects.ThursdayHours, objPlanVsActualsObjects.FridayHours, objPlanVsActualsObjects.SaturdayHours,objPlanVsActualsObjects.SundayHours,objPlanVsActualsObjects.Table);
		ActlHrTtlDyWise = pageDTSDepartmentPO.PlnVsActlUITotalDayWise("Hours",objPlanVsActualsObjects.StoreTotal, objPlanVsActualsObjects.MondayHoursTtl, objPlanVsActualsObjects.TuesdayHoursTtl, objPlanVsActualsObjects.WednesdayHoursTtl,
					 objPlanVsActualsObjects.ThursdayHoursTtl, objPlanVsActualsObjects.FridayHoursTtl, objPlanVsActualsObjects.SaturdayHoursTtl,objPlanVsActualsObjects.SundayHoursTtl,objPlanVsActualsObjects.Table);
								
		pageDailyTradingStatementStorePO.DTSCompareValuesTotal(extentTest,DBActlHrDyWise,ActlHrDyWise);	
		pageDailyTradingStatementStorePO.DTSCompareValuesTotal(extentTest,DBActlHrTtlDyWise,ActlHrTtlDyWise);
		
		Report_AddStep("testcase","PLAN VS ACTUALS DAILY SUMMARY - ACTUAL HOURS : DAY WISE LONGLIFE AND SEAFOOD&DELI " ,"","", "");	
		extentTest.log(LogStatus.INFO, "PLAN VS ACTUALS DAILY SUMMARY - ACTUAL HOURS : DAY WISE  ");
		
		
		String ActualHoursTotalLL = data.getText("ActualHoursTotalLL");	
		DBActlHrTtlDyWiseLL = sql.CLRexecuteQuery(getContext(), ActualHoursTotalLL.replace("store",getContext().getStringProperty("store")).replace("Week",getContext().getStringProperty("SelectFiscalWeek"))
				.replace("Division",getContext().getStringProperty("Division")).replace("Department",getContext().getStringProperty("Longlife")));
		System.out.println("DB Value: "+DBActlHrTtlDyWiseLL); 
			
		ActlHrTtlDyWiseLL = pageDTSDepartmentPO.PlnVsActlUITotalDayWise("Hours",objPlanVsActualsObjects.RowNameTotalLL, objPlanVsActualsObjects.MondayWagesLL, objPlanVsActualsObjects.TuesdayWagesLL, objPlanVsActualsObjects.WednesdayWagesLL,
				 objPlanVsActualsObjects.ThursdayWagesLL, objPlanVsActualsObjects.FridayWagesLL, objPlanVsActualsObjects.SaturdayWagesLL,objPlanVsActualsObjects.SundayWagesLL,objPlanVsActualsObjects.Table);
								
	pageDailyTradingStatementStorePO.DTSCompareValuesTotal(extentTest,DBActlHrTtlDyWiseLL,ActlHrTtlDyWiseLL);	
		String ActualHoursTotalSFD = data.getText("ActualHoursTotalSFD");	
		DBActlHrTtlDyWiseSFD = sql.CLRexecuteQuery(getContext(), ActualHoursTotalSFD.replace("store",getContext().getStringProperty("store")).replace("Week",getContext().getStringProperty("SelectFiscalWeek"))
				.replace("Division",getContext().getStringProperty("Division")).replace("Department",getContext().getStringProperty("SeaFoodDeli")));
		System.out.println("DB Value: "+DBActlHrTtlDyWiseSFD); 
			
		ActlHrTtlDyWiseSFD = pageDTSDepartmentPO.PlnVsActlUITotalDayWise("Hours",objPlanVsActualsObjects.RowNameTotalSFD, objPlanVsActualsObjects.MondayWagesSFD, objPlanVsActualsObjects.TuesdayWagesSFD, objPlanVsActualsObjects.WednesdayWagesSFD,
				 objPlanVsActualsObjects.ThursdayWagesSFD, objPlanVsActualsObjects.FridayWagesSFD, objPlanVsActualsObjects.SaturdayWagesSFD,objPlanVsActualsObjects.SundayWagesSFD,objPlanVsActualsObjects.Table);
								
		pageDailyTradingStatementStorePO.DTSCompareValuesTotal(extentTest,DBActlHrTtlDyWiseSFD,ActlHrTtlDyWiseSFD);	
				
		//Actual Wages - Week to Date
		Report_AddStep("testcase","PLAN VS ACTUALS DAILY SUMMARY - ACTUAL WAGES : WEEK TO DATE  " ,"","", "");	
		extentTest.log(LogStatus.INFO, "PLAN VS ACTUALS DAILY SUMMARY - ACTUAL WAGES : WEEK TO DATE   ");
		
		String ActualWagesWktoDt = data.getText("ActualWagesWktoDt");	
		DBActlWg = sql.CLRexecuteQuery(getContext(), ActualWagesWktoDt.replace("store",getContext().getStringProperty("store")).replace("Week",getContext().getStringProperty("SelectFiscalWeek"))
				.replace("Division",getContext().getStringProperty("Division")));
		System.out.println("DB Value: "+DBActlWg); 
				
		String ActualWagesWktoDtTotal = data.getText("ActualWagesWktoDtTotal");	
		DBActlWgTtl = sql.CLRexecuteQuery(getContext(), ActualWagesWktoDtTotal.replace("store",getContext().getStringProperty("store")).replace("Week",getContext().getStringProperty("SelectFiscalWeek"))
				.replace("Division",getContext().getStringProperty("Division")));
		System.out.println("DB Value: "+DBActlWgTtl); 
			
		ActlWg =  pageDTSDepartmentPO.PlnVsActlUIResults("Wages",objPlanVsActualsObjects.Departments, objPlanVsActualsObjects.WkToDtWagesandHours,objPlanVsActualsObjects.Table);
		ActlWgTtl = pageDTSDepartmentPO.PlnVsActlUITotal("Wages",objPlanVsActualsObjects.StoreTotal, objPlanVsActualsObjects.WkToDtWagesandHoursTtl);
				
		pageMonthlyPlanningPO.CompareValueDecimalFields(extentTest,DBActlWg,ActlWg);	
		pageMonthlyPlanningPO.CompareValueDecimalFields(extentTest,DBActlWgTtl,ActlWgTtl);
		
		Report_AddStep("testcase","PLAN VS ACTUALS DAILY SUMMARY - ACTUAL WAGES : WEEK TO DATE LONGLIFE AND SEAFOOD&DELI  " ,"","", "");	
		extentTest.log(LogStatus.INFO, "PLAN VS ACTUALS DAILY SUMMARY - ACTUAL WAGES : WEEK TO DATE   ");
		
		String ActualWagesWktoDtTotalLL = data.getText("ActualWagesWktoDtTotalLL");	
		DBActlWgTtlLL = sql.CLRexecuteQuery(getContext(), ActualWagesWktoDtTotalLL.replace("store",getContext().getStringProperty("store")).replace("Week",getContext().getStringProperty("SelectFiscalWeek"))
				.replace("Division",getContext().getStringProperty("Division")).replace("Department",getContext().getStringProperty("Longlife")));
		System.out.println("DB Value: "+DBActlWgTtlLL); 			
		
		ActlWgTtlLL = pageDTSDepartmentPO.PlnVsActlUITotal("Wages",objPlanVsActualsObjects.RowNameTotalLL, objPlanVsActualsObjects.WkToDtWagesandHoursLL);
				
		pageMonthlyPlanningPO.CompareValueDecimalFields(extentTest,DBActlWgTtlLL,ActlWgTtlLL);	
		String ActualWagesWktoDtTotalSFD = data.getText("ActualWagesWktoDtTotalSFD");	
		DBActlWgTtlSFD = sql.CLRexecuteQuery(getContext(), ActualWagesWktoDtTotalSFD.replace("store",getContext().getStringProperty("store")).replace("Week",getContext().getStringProperty("SelectFiscalWeek"))
				.replace("Division",getContext().getStringProperty("Division")).replace("Department",getContext().getStringProperty("SeaFoodDeli")));
		System.out.println("DB Value: "+DBActlWgTtlSFD); 			
		
		ActlWgTtlSFD = pageDTSDepartmentPO.PlnVsActlUITotal("Wages",objPlanVsActualsObjects.RowNameTotalSFD, objPlanVsActualsObjects.WkToDtWagesandHoursSFD);
				
		pageMonthlyPlanningPO.CompareValueDecimalFields(extentTest,DBActlWgTtlSFD,ActlWgTtlSFD);	
						
		//Actual Hours - Week to Date
		Report_AddStep("testcase"," PLAN VS ACTUALS DAILY SUMMARY - ACTUAL HOURS : WEEK TO DATE " ,"","", "");
		extentTest.log(LogStatus.INFO, " PLAN VS ACTUALS DAILY SUMMARY - ACTUAL HOURS : WEEK TO DATE ");
		
		String ActualHoursWktoDt = data.getText("ActualHoursWktoDt");	
		DBActlHr = sql.CLRexecuteQuery(getContext(), ActualHoursWktoDt.replace("store",getContext().getStringProperty("store")).replace("Week",getContext().getStringProperty("SelectFiscalWeek"))
				.replace("Division",getContext().getStringProperty("Division")));
		System.out.println("DB Value: "+DBActlHr); 
				
		String ActualHoursWktoDtTotal = data.getText("ActualHoursWktoDtTotal");	
		DBActlHrTtl = sql.CLRexecuteQuery(getContext(), ActualHoursWktoDtTotal.replace("store",getContext().getStringProperty("store")).replace("Week",getContext().getStringProperty("SelectFiscalWeek"))
				.replace("Division",getContext().getStringProperty("Division")));
		System.out.println("DB Value: "+DBActlHrTtl); 
			
		ActlHr =  pageDTSDepartmentPO.PlnVsActlUIResults("Hours",objPlanVsActualsObjects.Departments,objPlanVsActualsObjects.WkToDtWagesandHours,objPlanVsActualsObjects.Table);
		ActlHrTtl = pageDTSDepartmentPO.PlnVsActlUITotal("Hours",objPlanVsActualsObjects.StoreTotal, objPlanVsActualsObjects.WkToDtWagesandHoursTtl);
				
		pageMonthlyPlanningPO.CompareValueDecimalFields(extentTest,DBActlHr,ActlHr);	
		pageMonthlyPlanningPO.CompareValueDecimalFields(extentTest,DBActlHrTtl,ActlHrTtl);
		
		Report_AddStep("testcase"," PLAN VS ACTUALS DAILY SUMMARY - ACTUAL HOURS : WEEK TO DATE LONGLIFE AND SEAFOOD&DELI  " ,"","", "");
		extentTest.log(LogStatus.INFO, " PLAN VS ACTUALS DAILY SUMMARY - ACTUAL HOURS : WEEK TO DATE ");
		
		String ActualHoursWktoDtTotalLL = data.getText("ActualHoursWktoDtTotalLL");	
		DBActlHrTtlLL = sql.CLRexecuteQuery(getContext(), ActualHoursWktoDtTotalLL.replace("store",getContext().getStringProperty("store")).replace("Week",getContext().getStringProperty("SelectFiscalWeek"))
				.replace("Division",getContext().getStringProperty("Division")).replace("Department",getContext().getStringProperty("Longlife")));
		System.out.println("DB Value: "+DBActlHrTtlLL);
			
		
		ActlHrTtlLL = pageDTSDepartmentPO.PlnVsActlUITotal("Hours",objPlanVsActualsObjects.RowNameTotalLL, objPlanVsActualsObjects.WkToDtWagesandHoursLL);
		
		
		pageMonthlyPlanningPO.CompareValueDecimalFields(extentTest,DBActlHrTtlLL,ActlHrTtlLL);
		String ActualHoursWktoDtTotalSFD = data.getText("ActualHoursWktoDtTotalSFD");	
		
		DBActlHrTtlSFD = sql.CLRexecuteQuery(getContext(), ActualHoursWktoDtTotalSFD.replace("store",getContext().getStringProperty("store")).replace("Week",getContext().getStringProperty("SelectFiscalWeek"))
				.replace("Division",getContext().getStringProperty("Division")).replace("Department",getContext().getStringProperty("SeaFoodDeli")));
		System.out.println("DB Value: "+DBActlHrTtlSFD);
			
		
		ActlHrTtlSFD = pageDTSDepartmentPO.PlnVsActlUITotal("Hours",objPlanVsActualsObjects.RowNameTotalSFD, objPlanVsActualsObjects.WkToDtWagesandHoursSFD);
		
		
		pageMonthlyPlanningPO.CompareValueDecimalFields(extentTest,DBActlHrTtlSFD,ActlHrTtlSFD);
	}
	
	@Test(description = "PlanVsActualDailySummary_UnworkedPublicHolidays",priority=2)
	public void UnWorkedPublicDaysValidation() throws Exception
	{
		String PlanVsActualDailySummary = "PlanVsActualDailySummary";
		extentTest = report.startTest("Daily Trading Statement - Department, Sales Values");		
		pageDTSDepartmentPO = new DTS_DepartmentPage().createPage(getDriver(), getContext(), log, status, data,getReport(), getReportLogger(), DTS_DepartmentPage.class);
		pageWeeklyPlanningPO = new WeeklyPlanningStorePage().createPage(getDriver(), getContext(), log, status, data,getReport(), getReportLogger(), WeeklyPlanningStorePage.class);
		pageDailyTradingStatementStorePO = new MonthlyPlanningGroupPage().createPage(getDriver(), getContext(), log, status, data,getReport(), getReportLogger(), DailyTradingStatementStorePage.class);
		TestStatus status = getTestStatus();
		System.out.println("Entering into TestCase : Plan v Actual Daily Summary - Unworked Holidays");
		
		//Unworked Public Holidays - Wages
		Report_AddStep("testcase","PLAN VS ACTUALS DAILY SUMMARY - UNWORKED PUBLIC HOLIDAYS - WAGES  " ,"","", "");	
		extentTest.log(LogStatus.INFO, " PLAN VS ACTUALS DAILY SUMMARY - UNWORKED PUBLIC HOLIDAYS - WAGES  ");
		
		String Wages = data.getText("Wages");	
		DBWages = sql.CLRexecuteQuery(getContext(), Wages.replace("store",getContext().getStringProperty("store")).replace("Week",getContext().getStringProperty("SelectFiscalWeek"))
				.replace("Division",getContext().getStringProperty("Division")));
		System.out.println("DB Value: "+DBWages); 
	
		UITotal = pageDTSDepartmentPO.GetValuefromTextBox(extentTest,objPlanVsActualsObjects.WagesRow,objPlanVsActualsObjects.Wages);
		pageMonthlyPlanningPO.CompareValues(extentTest,DBWages,UITotal);	
				
		//Unworked Public Holidays - Hours
		Report_AddStep("testcase"," PLAN VS ACTUALS DAILY SUMMARY - UNWORKED PUBLIC HOLIDAYS - HOURS " ,"","", "");
		extentTest.log(LogStatus.INFO, "PLAN VS ACTUALS DAILY SUMMARY - UNWORKED PUBLIC HOLIDAYS - HOURS ");
		
		String Hours = data.getText("Hours");	
		DBHours = sql.CLRexecuteQuery(getContext(), Hours.replace("store",getContext().getStringProperty("store")).replace("Week",getContext().getStringProperty("SelectFiscalWeek"))
				.replace("Division",getContext().getStringProperty("Division")));
		System.out.println("DB Value: "+DBHours); 
	
		UITotal = pageDTSDepartmentPO.GetValuefromTextBox(extentTest,objPlanVsActualsObjects.HoursRow,objPlanVsActualsObjects.Hours);
		pageMonthlyPlanningPO.CompareValues(extentTest,DBHours,UITotal);
	}
	
	@Test(priority=3)
	public void LogoutCLRApplication() throws Exception
	{
		pageWeeklyPlanningPO = new WeeklyPlanningStorePage().createPage(getDriver(), getContext(), log, status, data,getReport(), getReportLogger(), WeeklyPlanningStorePage.class);
		pageWeeklyPlanningPO.LogOFF(objWeeklyPlanningStoreObjects);
	}	

	@BeforeTest
	public void InitiateExtentReport() throws Exception
	{
		report = new ExtentReports(System.getProperty("user.dir")+"\\Reports\\PlanVsActualsSummary.html");
	}
	
	@AfterTest
	public void CloseExtentReport() throws Exception
	{
		report.endTest(extentTest);
		report.flush();
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
		/*pageWeeklyDepartmentPlanningLLPO = PageFactory.initElements(this.getDriver(),
				WeeklyDepartmentPlanningPage.class);*/
		pageMonthlyPlanningGroupPO = PageFactory.initElements(this.getDriver(), MonthlyPlanningGroupPage.class);
		pageDTSDepartmentPO = PageFactory.initElements(this.getDriver(), DTS_DepartmentPage.class);
		pageDailyTradingStatementStorePO = PageFactory.initElements(this.getDriver(),
				DailyTradingStatementStorePage.class);
		pageDailyTradingStatementGroupPO = PageFactory.initElements(this.getDriver(),
				DailyTradingStatementGroupPage.class);
		BaseObj = PageFactory.initElements(this.getDriver(), BasePage.class);
		common = PageFactory.initElements(this.getDriver(), CommonFunctions.class);
		storeprop = PageFactory.initElements(this.getDriver(), StoreProperties.class);
		objWeeklyPlanningStoreObjects = PageFactory.initElements(this.getDriver(), WeeklyPlanningStoreObjects.class);
		objDailyTradingStatementObjects = PageFactory.initElements(this.getDriver(),
				DailyTradingStatementObjects.class);
		pageMonthlyPlanningPO = PageFactory.initElements(this.getDriver(), MonthlyPlanningStorePage.class);
	/*	objSalesHistoryCalendarobjects = PageFactory.initElements(this.getDriver(), SalesHistoryCalendarObjects.class);*/
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
			data.loadTestDataXML(System.getProperty("user.dir") + "\\TestData\\WLP\\PlanVsActualDailySummary.xml");
			customreport.createExtentReport();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
}
