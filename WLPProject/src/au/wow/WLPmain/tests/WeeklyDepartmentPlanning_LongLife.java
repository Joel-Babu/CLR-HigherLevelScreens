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
import org.openqa.selenium.By;
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
import au.wow.WLPmain.objects.WeeklyDepartmentPlanningObjects;
import au.wow.WLPmain.objects.WeeklyDepartmentPlanning_LonglifeObjects;
import au.wow.WLPmain.objects.WeeklyPlanningStoreObjects;
import au.wow.WLPmain.pages.CommonFunctions;
import au.wow.WLPmain.pages.DTS_DepartmentPage;
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

public class WeeklyDepartmentPlanning_LongLife extends TestBase {

	protected Logger log = LogManager.getLogger(WeeklyDepartmentPlanning_LongLife.class);
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
	MonthlyPlanningGroupPage pageMonthlyPlanningGroupPO;
	DTS_DepartmentPage pageDTSDepartmentPO;
	DTS_DepartmentObjects objDTSDepartmentObjects;
	WeeklyDepartmentPlanningPage pageWeeklyDepartmentPlanningLLPO;
	WeeklyDepartmentPlanning_LonglifeObjects objWeeklyDepartmentPlanningLLObjects;
	WeeklyDepartmentPlanningObjects objWeeklyDepartmentPlanningObjects;

	BasePage BaseObj;
	CommonFunctions common;
	StoreProperties storeprop;

	List<List<Object>> UIValues, UITotal, DBVal, DBTtl;
	List<List<Object>> DBPlndSls, DBBdgtSls, DBPlndWg, DBBdgtWg, DBPlndWgPrcnt, DBBdgtWgPrcnt, DBPlndHr, DBPlndOR,
			DBFirstWk, DBFirstWkTtl, DBSecondWk, DBSecondWkTtl, DBThirdWk, DBThirdWkTtl, DBFourthWk, DBFourthWkTtl,DBLastYearSls, LastyearSls,
			DBAvgMix, DBCPHFirstWk, DBCPHSecondWk, DBCPHThirdWk, DBCPHFourthWk, DBGnrtSls, DBGnrtSlsTtl, DBPlndSlsWk,
			DBPlndSlsWkTtl, DBGnrtItmPr, DBGnrtItmPrTtl, DBPlndItmPr, DBPlndItmPrTtl, DBGnrtItm, DBGnrtItmTtl,DBLastYearSlsTtl, LastyearSlsTtl,
			DBPlndItm, DBPlndItmTtl, DBSMSHr, DBSMSHrTtl, DBPlndHrWk, DBPlndHrWkTtl, DBPlndVar, DBPlndVarTtl, DBORWk,
			DBORWkTtl, DBWgCost, DBWgCostTtl, DBWkType, DBInv, DBInvTtl, DBRepl, DBReplTtl, DBMerch, DBMerchTtl,DBLYSalesSummary2, LastyearSlsSummary2,
			DBPlndSlsSummary2, DBBdgtSlsSummary2, DBBdgtHr, DBFcstHr, DBBdgtOR, DBFcstOR,DBAvgGrowth, Avggrowth,DBPlannedSalesDailyMix, PlannedSalesDailyMixper;
	List<List<Object>> PlndSls, BdgtSls, PlndWg, BdgtWg, PlndWgPrcnt, BdgtWgPrcnt, PlndHr, PlndOR, FirstWk, FirstWkTtl,
			SecondWk, SecondWkTtl, ThirdWk, ThirdWkTtl, FourthWk, FourthWkTtl, AvgMix, CPHFirstWk, CPHSecondWk,DBPlannedSalesVSLYPer, PlannedSalesVSLY,
			CPHThirdWk, CPHFourthWk, GnrtSls, GnrtSlsTtl, PlndSlsWk, PlndSlsWkTtl, GnrtItmPr, GnrtItmPrTtl, PlndItmPr,
			PlndItmPrTtl, GnrtItm, GnrtItmTtl, PlndItm, PlndItmTtl, SMSHr, SMSHrTtl, PlndHrWk, PlndHrWkTtl, PlndVar,DBLYSlsSummary2, LYSlsSummary2,
			PlndVarTtl, ORWk, ORWkTtl, WgCost, WgCostTtl, WkType, Inv, InvTtl, Repl, ReplTtl, Merch, MerchTtl,DBLYHours, LYHours,DBLYOR, LYOR,
			PlndSlsSummary2, BdgtSlsSummary2, BdgtHr, FcstHr, BdgtOR, FcstOR,DBLYSls, LYSls,DBLYwages, LYwages,DBLYwageper, LYwageper;
	String OptionToView;

	public WeeklyDepartmentPlanning_LongLife() {
		super.log = log;
	}

	///////////////////// SUMMARY INFO /////////////////////////
	@Test(description = "Weekly Department Planning , SummaryInfoValidation", priority = 1)
	public void LongLifeValidation() throws Exception {
		loginPage = new WoWLoginPage().createPage(getDriver(), getContext(), log, status, data, report, extentTest,
				WoWLoginPage.class);
		pageWeeklyDepartmentPlanningLLPO = new WeeklyDepartmentPlanning_LongLife().createPage(getDriver(), getContext(),
				log, status, data, getReport(), getReportLogger(), WeeklyDepartmentPlanningPage.class);
		pageMonthlyPlanningGroupPO = new MonthlyPlanningGroupPage().createPage(getDriver(), getContext(), log, status,
				data, getReport(), getReportLogger(), MonthlyPlanningGroupPage.class);
		pageWeeklyPlanningPO = loginPage.navToWeeklyPlanningStorePg(getContext().getStringProperty("store"));
		TestStatus status = getTestStatus();
		System.out.println("Welcome " + getContext().getStringProperty("username"));
		System.out.println("Entering into TestCase : Weekly Planning Department");
		pageWeeklyPlanningPO.SelectWeekFromDropdown(objWeeklyPlanningStoreObjects, extentTest,
				getContext().getStringProperty("SelectFiscalWeek"));

		pageWeeklyPlanningPO.selectPage(extentTest, objWeeklyPlanningStoreObjects.menuBar,
				objWeeklyPlanningStoreObjects.planningScreens, "Planning Screens");
		pageWeeklyPlanningPO.selectPage(extentTest, objWeeklyPlanningStoreObjects.menuBar,
				objWeeklyPlanningStoreObjects.departmentPlanning, "Department Planning");
		pageWeeklyPlanningPO.selectPage(extentTest, objWeeklyPlanningStoreObjects.menuBar,
				objWeeklyPlanningStoreObjects.DeptPlnscreenToView, "Weekly Department Planning-Longlife");
		/*pageWeeklyPlanningPO.verifyPageName(extentTest, objWeeklyPlanningStoreObjects.DataFrame,
				objWeeklyPlanningStoreObjects.pageName,
				getContext().getStringProperty("WeeklyDepartmentPlanningLongLife"));*/

		OptionToView = getContext().getStringProperty("OptionToView");

		////////////////////////////////////// SUMMARY INFO
		////////////////////////////////////// ///////////////////////////////////////////
		
		
		// LastYear Sales
		
					Report_AddStep("testcase", " SUMMARY INFO- LY SALES DATA", "", "", "");
					extentTest = report.startTest("Summary Info - LY  Sales");

					String LastyearSales = data.getText("LastYearSales");
					DBLYSls = sql.CLRexecuteQuery(getContext(),
							LastyearSales.replace("store", getContext().getStringProperty("store"))
									.replace("Week", getContext().getStringProperty("SelectFiscalWeek"))
									.replace("Department", getContext().getStringProperty("Longlife")).replace("LYWk", getContext().getStringProperty("LYWk"))
									.replace("Division", getContext().getStringProperty("Division")));
					System.out.println("DB Value: " + DBLYSls);

					LYSls = pageWeeklyDepartmentPlanningLLPO.UIResultsSummaryInfo(
							objWeeklyDepartmentPlanningLLObjects.SummaryInfoLastyearcolumn, extentTest,
							objWeeklyDepartmentPlanningLLObjects.SummaryInfoLYSales, objWeeklyDepartmentPlanningLLObjects.Tablename);
					pageMonthlyPlanningPO.CompareValues(extentTest, DBLYSls, LYSls);
					// LastYear Wages
					Report_AddStep("testcase", " SUMMARY INFO- LY Wages DATA", "", "", "");
					extentTest = report.startTest("Summary Info - LY  wages");

					String LastyearSWages = data.getText("LastYearWages");
					DBLYwages = sql.CLRexecuteQuery(getContext(),
							LastyearSWages.replace("store", getContext().getStringProperty("store"))
									.replace("Week", getContext().getStringProperty("SelectFiscalWeek"))
									.replace("Department", getContext().getStringProperty("Longlife")).replace("LYWk", getContext().getStringProperty("LYWk"))
									.replace("Division", getContext().getStringProperty("Division")));
					System.out.println("DB Value: " + DBLYwages);

					LYwages = pageWeeklyDepartmentPlanningLLPO.UIResultsSummaryInfo(
							objWeeklyDepartmentPlanningLLObjects.SummaryInfoLastyearcolumn, extentTest,
							objWeeklyDepartmentPlanningLLObjects.SummaryInfoLYWages, objWeeklyDepartmentPlanningLLObjects.Tablename);
					pageMonthlyPlanningPO.CompareValues(extentTest, DBLYwages, LYwages);

					// LastYear Wage%
					Report_AddStep("testcase", " SUMMARY INFO- LY WagesPer DATA", "", "", "");
					extentTest = report.startTest("Summary Info - LYWagesper");

					String LastyearSWageper = data.getText("LastYearWageper");
					DBLYwageper = sql.CLRexecuteQuery(getContext(),
							LastyearSWageper.replace("store", getContext().getStringProperty("store"))
									.replace("Week", getContext().getStringProperty("SelectFiscalWeek"))
									.replace("Department", getContext().getStringProperty("Longlife")).replace("LYWk", getContext().getStringProperty("LYWk"))
									.replace("Division", getContext().getStringProperty("Division")));
					System.out.println("DB Value: " + DBLYwageper);

					LYwageper = pageWeeklyDepartmentPlanningLLPO.UIResultsSummaryInfo(
							objWeeklyDepartmentPlanningLLObjects.SummaryInfoLastyearcolumn, extentTest,
							objWeeklyDepartmentPlanningLLObjects.SummaryInfoLYWageper, objWeeklyDepartmentPlanningLLObjects.Tablename);
					pageMonthlyPlanningPO.CompareValues(extentTest, DBLYwageper, LYwageper);
					//Last Year Hours
					Report_AddStep("testcase", " SUMMARY INFO- LYHours DATA", "", "", "");
					extentTest = report.startTest("Summary Info - LYHours");

					String LastyearHours = data.getText("LastYearHours");
					DBLYHours = sql.CLRexecuteQuery(getContext(),
							LastyearHours.replace("store", getContext().getStringProperty("store"))
									.replace("Week", getContext().getStringProperty("SelectFiscalWeek"))
									.replace("Department", getContext().getStringProperty("Longlife")).replace("LYWk", getContext().getStringProperty("LYWk"))
									.replace("Division", getContext().getStringProperty("Division")));
					System.out.println("DB Value: " + DBLYHours);

					LYHours = pageWeeklyDepartmentPlanningLLPO.UIResultsSummaryInfo(
							objWeeklyDepartmentPlanningLLObjects.SummaryInfoLastyearcolumn, extentTest,
							objWeeklyDepartmentPlanningLLObjects.SummaryInfoLYHours, objWeeklyDepartmentPlanningLLObjects.Tablename);
					pageMonthlyPlanningPO.CompareValues(extentTest, DBLYHours, LYHours);
					
					// Last Year OR%
					Report_AddStep("testcase", " SUMMARY INFO- LY Operating Ratio DATA", "", "", "");
					extentTest = report.startTest("Summary Info - LYOperating Ratio");

					String LastyearOR = data.getText("LastYearORPer");
					DBLYOR = sql.CLRexecuteQuery(getContext(),
							LastyearOR.replace("store", getContext().getStringProperty("store"))
									.replace("Week", getContext().getStringProperty("SelectFiscalWeek"))
									.replace("Department", getContext().getStringProperty("Longlife")).replace("LYWk", getContext().getStringProperty("LYWk")).replace("FinWk", getContext().getStringProperty("FinWk"))
									.replace("Division", getContext().getStringProperty("Division")).replace("LYFinYr", getContext().getStringProperty("LastYr")));
					System.out.println("DB Value: " + DBLYOR);

					LYOR = pageWeeklyDepartmentPlanningLLPO.UIResultsSummaryInfo(
							objWeeklyDepartmentPlanningLLObjects.SummaryInfoLastyearcolumn, extentTest,
							objWeeklyDepartmentPlanningLLObjects.SummaryInfoORPer, objWeeklyDepartmentPlanningLLObjects.Tablename);
					pageMonthlyPlanningPO.CompareValues(extentTest, DBLYOR, LYOR);
		
		
		
		
		// Sales
		
		
		
		Report_AddStep("testcase", " SUMMARY INFO- PLANNED SALES DATA", "", "", "");
		extentTest = report.startTest("Summary Info - Planned Sales");
		
		String PlannedSales = data.getText("PlannedSales");
		DBPlndSls = sql.CLRexecuteQuery(getContext(),
				PlannedSales.replace("store", getContext().getStringProperty("store"))
						.replace("Week", getContext().getStringProperty("SelectFiscalWeek"))
						.replace("Department", getContext().getStringProperty("Longlife"))
						.replace("Division", getContext().getStringProperty("Division")));
		System.out.println("DB Value: " + DBPlndSls);

		PlndSls = pageWeeklyDepartmentPlanningLLPO.UIResultsSummaryInfo(
				objWeeklyDepartmentPlanningLLObjects.SummaryInfocolumn, extentTest,
				objWeeklyDepartmentPlanningLLObjects.SummaryInfoSales, objWeeklyDepartmentPlanningLLObjects.Tablename);
		pageMonthlyPlanningPO.CompareValues(extentTest, DBPlndSls, PlndSls);

		if (OptionToView.contains("Budget")) {
			Report_AddStep("testcase", " SUMMARY INFO- BUDGET SALES DATA", "", "", "");
			extentTest = report.startTest("Summary Info - Budget Sales");
			
			String BudgetSales = data.getText("BudgetSales");
			DBBdgtSls = sql.CLRexecuteQuery(getContext(),
					BudgetSales.replace("store", getContext().getStringProperty("store"))
							.replace("Week", getContext().getStringProperty("SelectFiscalWeek"))
							.replace("Department", getContext().getStringProperty("Longlife"))
							.replace("Division", getContext().getStringProperty("Division")));
			System.out.println("DB Value: " + DBBdgtSls);

			BdgtSls = pageWeeklyDepartmentPlanningLLPO.UIResultsSummaryInfo(
					objWeeklyDepartmentPlanningLLObjects.SummaryInfoBudgetColumn, extentTest,
					objWeeklyDepartmentPlanningLLObjects.SummaryInfoBudgetSales,
					objWeeklyDepartmentPlanningLLObjects.Tablename);
			pageMonthlyPlanningPO.CompareValues(extentTest, DBBdgtSls, BdgtSls);
		}
		if (OptionToView.contains("Forecast")) {
			Report_AddStep("testcase", " SUMMARY INFO- FORECAST SALES DATA", "", "", "");
			extentTest = report.startTest("Summary Info - Forecast Sales");
			
			String ForecastSales = data.getText("ForecastSales");
			DBBdgtSls = sql.CLRexecuteQuery(getContext(),
					ForecastSales.replace("store", getContext().getStringProperty("store"))
							.replace("Week", getContext().getStringProperty("SelectFiscalWeek"))
							.replace("Department", getContext().getStringProperty("Longlife"))
							.replace("Division", getContext().getStringProperty("Division")));
			System.out.println("DB Value: " + DBBdgtSls);

			BdgtSls = pageWeeklyDepartmentPlanningLLPO.UIResultsSummaryInfo(
					objWeeklyDepartmentPlanningLLObjects.SummaryInfoBudgetColumn, extentTest,
					objWeeklyDepartmentPlanningLLObjects.SummaryInfoBudgetSales,
					objWeeklyDepartmentPlanningLLObjects.Tablename);
			pageMonthlyPlanningPO.CompareValues(extentTest, DBBdgtSls, BdgtSls);
		}

		// Wage
		Report_AddStep("testcase", " SUMMARY INFO - PLANNED WAGES DATA", "", "", "");
		extentTest = report.startTest("Summary Info - Planned Wages");
		
		String PlannedWages = data.getText("PlannedWages");
		DBPlndWg = sql.CLRexecuteQuery(getContext(),
				PlannedWages.replace("store", getContext().getStringProperty("store"))
						.replace("Week", getContext().getStringProperty("SelectFiscalWeek"))
						.replace("Department", getContext().getStringProperty("Longlife"))
						.replace("Division", getContext().getStringProperty("Division")));
		System.out.println("DB Value: " + DBPlndWg);

		PlndWg = pageWeeklyDepartmentPlanningLLPO.UIResultsSummaryInfo(
				objWeeklyDepartmentPlanningLLObjects.SummaryInfocolumn, extentTest,
				objWeeklyDepartmentPlanningLLObjects.SummaryInfoWage, objWeeklyDepartmentPlanningLLObjects.Tablename);
		pageMonthlyPlanningPO.CompareValues(extentTest, DBPlndWg, PlndWg);

		if (OptionToView.contains("Budget")) {
			Report_AddStep("testcase", " SUMMARY INFO- BUDGET WAGES DATA", "", "", "");
			extentTest = report.startTest("Summary Info - Budget Wages");
			
			String BudgetWages = data.getText("BudgetWages");
			DBBdgtWg = sql.CLRexecuteQuery(getContext(),
					BudgetWages.replace("store", getContext().getStringProperty("store"))
							.replace("Week", getContext().getStringProperty("SelectFiscalWeek"))
							.replace("Department", getContext().getStringProperty("Longlife"))
							.replace("Division", getContext().getStringProperty("Division")));
			System.out.println("DB Value: " + DBBdgtWg);

			BdgtWg = pageWeeklyDepartmentPlanningLLPO.UIResultsSummaryInfo(
					objWeeklyDepartmentPlanningLLObjects.SummaryInfoBudgetColumn, extentTest,
					objWeeklyDepartmentPlanningLLObjects.SummaryInfoBudgetWage,
					objWeeklyDepartmentPlanningLLObjects.Tablename);
			pageMonthlyPlanningPO.CompareValues(extentTest, DBBdgtWg, BdgtWg);
		}
		if (OptionToView.contains("Forecast")) {
			Report_AddStep("testcase", " SUMMARY INFO- FORECAST WAGES DATA", "", "", "");
			extentTest = report.startTest("Summary Info - Forecast Wages");
			
			String ForecastWages = data.getText("ForecastWages");
			DBBdgtWg = sql.CLRexecuteQuery(getContext(),
					ForecastWages.replace("store", getContext().getStringProperty("store"))
							.replace("Week", getContext().getStringProperty("SelectFiscalWeek"))
							.replace("Department", getContext().getStringProperty("Longlife"))
							.replace("Division", getContext().getStringProperty("Division")));
			System.out.println("DB Value: " + DBBdgtWg);

			BdgtWg = pageWeeklyDepartmentPlanningLLPO.UIResultsSummaryInfo(
					objWeeklyDepartmentPlanningLLObjects.SummaryInfoBudgetColumn, extentTest,
					objWeeklyDepartmentPlanningLLObjects.SummaryInfoBudgetWage,
					objWeeklyDepartmentPlanningLLObjects.Tablename);
			pageMonthlyPlanningPO.CompareValues(extentTest, DBBdgtWg, BdgtWg);
		}

		// Wage%
		Report_AddStep("testcase", " SUMMARY INFO- PLANNED WAGE PERCENT DATA", "", "", "");
		extentTest = report.startTest("Summary Info - Planned Wages Prcnt");
		
		String PlannedWagesPercentage = data.getText("PlannedWagesPercentage");
		DBPlndWgPrcnt = sql.CLRexecuteQuery(getContext(),
				PlannedWagesPercentage.replace("store", getContext().getStringProperty("store"))
						.replace("Week", getContext().getStringProperty("SelectFiscalWeek"))
						.replace("Department", getContext().getStringProperty("Longlife"))
						.replace("Division", getContext().getStringProperty("Division")));
		System.out.println("DB Value: " + DBPlndWgPrcnt);

		PlndWgPrcnt = pageWeeklyDepartmentPlanningLLPO.UIResultsSummaryInfo(
				objWeeklyDepartmentPlanningLLObjects.SummaryInfocolumn, extentTest,
				objWeeklyDepartmentPlanningLLObjects.SummaryInfoWagepercent,
				objWeeklyDepartmentPlanningLLObjects.Tablename);
		pageMonthlyPlanningPO.CompareValues(extentTest, DBPlndWgPrcnt, PlndWgPrcnt);

		if (OptionToView.contains("Budget")) {
			Report_AddStep("testcase", " SUMMARY INFO- BUDGET WAGE PERCENT DATA", "", "", "");
			extentTest = report.startTest("Summary Info - Budget Wages Prcnt");
			
			String BudgetWagesPercentage = data.getText("BudgetWagesPercentage");
			DBBdgtWgPrcnt = sql.CLRexecuteQuery(getContext(),
					BudgetWagesPercentage.replace("store", getContext().getStringProperty("store"))
							.replace("Week", getContext().getStringProperty("SelectFiscalWeek"))
							.replace("Department", getContext().getStringProperty("Longlife"))
							.replace("Division", getContext().getStringProperty("Division")));
			System.out.println("DB Value: " + DBBdgtWgPrcnt);

			BdgtWgPrcnt = pageWeeklyDepartmentPlanningLLPO.UIResultsSummaryInfo(
					objWeeklyDepartmentPlanningLLObjects.SummaryInfoBudgetColumn, extentTest,
					objWeeklyDepartmentPlanningLLObjects.SummaryInfoBudgetWagepercent,
					objWeeklyDepartmentPlanningLLObjects.Tablename);
			pageMonthlyPlanningPO.CompareValueDecimalFields(extentTest, DBBdgtWgPrcnt, BdgtWgPrcnt);
		}
		if (OptionToView.contains("Forecast")) {
			Report_AddStep("testcase", " SUMMARY INFO- FORECAST WAGE PERCENT DATA", "", "", "");
			extentTest = report.startTest("Summary Info - Forecast Wages Prcnt");
			
			String ForecastWagesPercentage = data.getText("ForecastWagesPercentage");
			DBBdgtWgPrcnt = sql.CLRexecuteQuery(getContext(),
					ForecastWagesPercentage.replace("store", getContext().getStringProperty("store"))
							.replace("Week", getContext().getStringProperty("SelectFiscalWeek"))
							.replace("Department", getContext().getStringProperty("Longlife"))
							.replace("Division", getContext().getStringProperty("Division")));
			System.out.println("DB Value: " + DBBdgtWgPrcnt);

			BdgtWgPrcnt = pageWeeklyDepartmentPlanningLLPO.UIResultsSummaryInfo(
					objWeeklyDepartmentPlanningLLObjects.SummaryInfoBudgetColumn, extentTest,
					objWeeklyDepartmentPlanningLLObjects.SummaryInfoBudgetWagepercent,
					objWeeklyDepartmentPlanningLLObjects.Tablename);
			pageMonthlyPlanningPO.CompareValueDecimalFields(extentTest, DBBdgtWgPrcnt, BdgtWgPrcnt);
		}

		// Plan Paid Hours
		Report_AddStep("testcase", " SUMMARY INFO-PLANNED HOURS DATA", "", "", "");
		extentTest = report.startTest("Summary Info - Planned Hours");
		
		String PlannedHours = data.getText("PlannedHours");
		DBPlndHr = sql.CLRexecuteQuery(getContext(),
				PlannedHours.replace("store", getContext().getStringProperty("store"))
						.replace("Week", getContext().getStringProperty("SelectFiscalWeek"))
						.replace("Department", getContext().getStringProperty("Longlife"))
						.replace("Division", getContext().getStringProperty("Division")));
		System.out.println("DB Value: " + DBPlndHr);

		PlndHr = pageWeeklyDepartmentPlanningLLPO.UIResultsSummaryInfo(
				objWeeklyDepartmentPlanningLLObjects.SummaryInfocolumn, extentTest,
				objWeeklyDepartmentPlanningLLObjects.SummaryInfoPlanPaidHours,
				objWeeklyDepartmentPlanningLLObjects.Tablename);
		pageMonthlyPlanningPO.CompareValues(extentTest, DBPlndHr, PlndHr);

		// Budget Hours
		if (OptionToView.contains("Budget")) {
			Report_AddStep("testcase", " SUMMARY INFO-BUDGET HOURS DATA", "", "", "");
			extentTest = report.startTest("Summary Info - Budget Hours");

			String BudgetHours = data.getText("BudgetHours");
			DBBdgtHr = sql.CLRexecuteQuery(getContext(),
					BudgetHours.replace("store", getContext().getStringProperty("store"))
							.replace("Week", getContext().getStringProperty("SelectFiscalWeek"))
							.replace("Department", getContext().getStringProperty("Longlife"))
							.replace("FinWk", getContext().getStringProperty("FinWk"))
							.replace("FinYear", getContext().getStringProperty("FinYr"))
							.replace("Division", getContext().getStringProperty("Division")));
			System.out.println("DB Value: " + DBBdgtHr);

			BdgtHr = pageWeeklyDepartmentPlanningLLPO.UIResultsSummaryInfo(
					objWeeklyDepartmentPlanningLLObjects.SummaryInfoBudgetColumn, extentTest,
					objWeeklyDepartmentPlanningLLObjects.SummaryInfoBudgetPaidHours,
					objWeeklyDepartmentPlanningLLObjects.Tablename);
			pageMonthlyPlanningPO.CompareValueDecimalFields(extentTest, DBBdgtHr, BdgtHr);
		}
		if (OptionToView.contains("Forecast")) {
			Report_AddStep("testcase", " SUMMARY INFO- FORECAST HOURS DATA", "", "", "");
			extentTest = report.startTest("Summary Info - Forecast Hours");

			String ForecastHours = data.getText("ForecastHours");
			DBFcstHr = sql.CLRexecuteQuery(getContext(),
					ForecastHours.replace("store", getContext().getStringProperty("store"))
							.replace("Week", getContext().getStringProperty("SelectFiscalWeek"))
							.replace("Department", getContext().getStringProperty("Longlife"))
							.replace("FinWk", getContext().getStringProperty("FinWk"))
							.replace("FinYear", getContext().getStringProperty("FinYr"))
							.replace("Division", getContext().getStringProperty("Division")));
			System.out.println("DB Value: " + DBFcstHr);

			FcstHr = pageWeeklyDepartmentPlanningLLPO.UIResultsSummaryInfo(
					objWeeklyDepartmentPlanningLLObjects.SummaryInfoBudgetColumn, extentTest,
					objWeeklyDepartmentPlanningLLObjects.SummaryInfoBudgetPaidHours,
					objWeeklyDepartmentPlanningLLObjects.Tablename);
			pageMonthlyPlanningPO.CompareValueDecimalFields(extentTest, DBFcstHr, FcstHr);
		}

		// OR%
		Report_AddStep("testcase", " SUMMARY INFO-PLANNED OR% DATA", "", "", "");
		extentTest = report.startTest("Summary Info - Planned OR");
		
		String PlannedOR = data.getText("PlannedOR");
		DBPlndOR = sql.CLRexecuteQuery(getContext(),
				PlannedOR.replace("store", getContext().getStringProperty("store"))
						.replace("Week", getContext().getStringProperty("SelectFiscalWeek"))
						.replace("Department", getContext().getStringProperty("Longlife"))
						.replace("Division", getContext().getStringProperty("Division")));
		System.out.println("DB Value: " + DBPlndOR);

		PlndOR = pageWeeklyDepartmentPlanningLLPO.UIResultsSummaryInfo(
				objWeeklyDepartmentPlanningLLObjects.SummaryInfocolumn, extentTest,
				objWeeklyDepartmentPlanningLLObjects.SummaryInfoORpercent,
				objWeeklyDepartmentPlanningLLObjects.Tablename);
		pageMonthlyPlanningPO.CompareValueDecimalFields(extentTest, DBPlndOR, PlndOR);

		// Budget OR%
		if (OptionToView.contains("Budget")) {
			Report_AddStep("testcase", " SUMMARY INFO- BUDGET OR PERCENT DATA", "", "", "");
			extentTest = report.startTest("Summary Info - Budget OR");

			String BudgetOR = data.getText("BudgetOR");
			DBBdgtOR = sql.CLRexecuteQuery(getContext(),
					BudgetOR.replace("store", getContext().getStringProperty("store"))
							.replace("Week", getContext().getStringProperty("SelectFiscalWeek"))
							.replace("Department", getContext().getStringProperty("Longlife"))
							.replace("FinWk", getContext().getStringProperty("FinWk"))
							.replace("FinYear", getContext().getStringProperty("FinYr"))
							.replace("Division", getContext().getStringProperty("Division")));
			System.out.println("DB Value: " + DBBdgtOR);

			BdgtOR = pageWeeklyDepartmentPlanningLLPO.UIResultsSummaryInfo(
					objWeeklyDepartmentPlanningLLObjects.SummaryInfoBudgetColumn, extentTest,
					objWeeklyDepartmentPlanningLLObjects.SummaryInfoBudgetORpercent,
					objWeeklyDepartmentPlanningLLObjects.Tablename);
			pageMonthlyPlanningPO.CompareValueDecimalFields(extentTest, DBBdgtOR, BdgtOR);
		}
		if (OptionToView.contains("Forecast")) {
			Report_AddStep("testcase", " SUMMARY INFO- FORECAST OR PERCENT DATA", "", "", "");
			extentTest = report.startTest("Summary Info - Forecast OR");

			String ForecastOR = data.getText("ForecastOR");
			DBFcstOR = sql.CLRexecuteQuery(getContext(),
					ForecastOR.replace("store", getContext().getStringProperty("store"))
							.replace("Week", getContext().getStringProperty("SelectFiscalWeek"))
							.replace("Department", getContext().getStringProperty("Longlife"))
							.replace("FinWk", getContext().getStringProperty("FinWk"))
							.replace("FinYear", getContext().getStringProperty("FinYr"))
							.replace("Division", getContext().getStringProperty("Division")));
			System.out.println("DB Value: " + DBFcstOR);

			FcstOR = pageWeeklyDepartmentPlanningLLPO.UIResultsSummaryInfo(
					objWeeklyDepartmentPlanningLLObjects.SummaryInfoBudgetColumn, extentTest,
					objWeeklyDepartmentPlanningLLObjects.SummaryInfoBudgetORpercent,
					objWeeklyDepartmentPlanningLLObjects.Tablename);
			pageMonthlyPlanningPO.CompareValueDecimalFields(extentTest, DBFcstOR, FcstOR);
			
			
			
			
		}

		///////////////////// SALES HISTORY /////////////////////////
		String WeekID = getContext().getStringProperty("SelectFiscalWeek");
		String LYWeekID = getContext().getStringProperty("LYWk");
		int LYWk1 = Integer.parseInt(LYWeekID) - 1;
		int LYWk4 = Integer.parseInt(LYWeekID) - 4;
		int Wk1 = Integer.parseInt(WeekID) - 1;
		int Wk2 = Integer.parseInt(WeekID) - 2;
		int Wk3 = Integer.parseInt(WeekID) - 3;
		int Wk4 = Integer.parseInt(WeekID) - 4;

		// CPH - Week 1
		Report_AddStep("testcase", " CPH VALIDATION - FIRST WEEK", "", "", "");
		extentTest = report.startTest("CPH - First Week");
		
		String CPH1 = data.getText("CPH");
		DBCPHFirstWk = sql.CLRexecuteQuery(getContext(),
				CPH1.replace("store", getContext().getStringProperty("store"))/*.replace("Week", String.valueOf(Wk4))*/.replace("Week", getContext().getStringProperty("Week1"))
						.replace("Department", getContext().getStringProperty("Longlife"))
						.replace("Division", getContext().getStringProperty("Division")));
		System.out.println("DB Value: " + DBCPHFirstWk);

		CPHFirstWk = pageMonthlyPlanningPO.GetTotal(extentTest, objWeeklyDepartmentPlanningLLObjects.FirstWeekCaption,
				objWeeklyDepartmentPlanningLLObjects.CPHFirstWk);

		pageMonthlyPlanningPO.CompareValueDecimalFields(extentTest, DBCPHFirstWk, CPHFirstWk);

		// CPH - Week 2
		Report_AddStep("testcase", " CPH VALIDATION - SECOND WEEK", "", "", "");
		extentTest = report.startTest("CPH - Second Week");
		
		String CPH2 = data.getText("CPH");
		DBCPHSecondWk = sql.CLRexecuteQuery(getContext(),
				CPH2.replace("store", getContext().getStringProperty("store"))/*.replace("Week", String.valueOf(Wk3))*/.replace("Week", getContext().getStringProperty("Week2"))
						.replace("Department", getContext().getStringProperty("Longlife"))
						.replace("Division", getContext().getStringProperty("Division")));
		System.out.println("DB Value: " + DBCPHSecondWk);

		CPHSecondWk = pageMonthlyPlanningPO.GetTotal(extentTest, objWeeklyDepartmentPlanningLLObjects.SecondWeekCaption,
				objWeeklyDepartmentPlanningLLObjects.CPHSecondWk);

		pageMonthlyPlanningPO.CompareValueDecimalFields(extentTest, DBCPHSecondWk, CPHSecondWk);

		// CPH - Week 3
		Report_AddStep("testcase", " CPH VALIDATION - THIRD WEEK", "", "", "");
		extentTest = report.startTest("CPH - Third Week");
		
		String CPH3 = data.getText("CPH");
		DBCPHThirdWk = sql.CLRexecuteQuery(getContext(),
				CPH3.replace("store", getContext().getStringProperty("store"))/*.replace("Week", String.valueOf(Wk2))*/.replace("Week", getContext().getStringProperty("Week3"))
						.replace("Department", getContext().getStringProperty("Longlife"))
						.replace("Division", getContext().getStringProperty("Division")));
		System.out.println("DB Value: " + DBCPHThirdWk);

		CPHThirdWk = pageMonthlyPlanningPO.GetTotal(extentTest, objWeeklyDepartmentPlanningLLObjects.ThirdWeekCaption,
				objWeeklyDepartmentPlanningLLObjects.CPHThirdWk);

		pageMonthlyPlanningPO.CompareValueDecimalFields(extentTest, DBCPHThirdWk, CPHThirdWk);

		// CPH - Week 4
		Report_AddStep("testcase", " CPH VALIDATION - FOURTH WEEK", "", "", "");
		extentTest = report.startTest("CPH - Fourth Week");
		
		String CPH4 = data.getText("CPH");
		DBCPHFourthWk = sql.CLRexecuteQuery(getContext(),
				CPH4.replace("store", getContext().getStringProperty("store"))/*.replace("Week", String.valueOf(Wk1))*/.replace("Week", getContext().getStringProperty("Week4"))
						.replace("Department", getContext().getStringProperty("Longlife"))
						.replace("Division", getContext().getStringProperty("Division")));
		System.out.println("DB Value: " + DBCPHFourthWk);

		CPHFourthWk = pageMonthlyPlanningPO.GetTotal(extentTest, objWeeklyDepartmentPlanningLLObjects.FourthWeekCaption,
				objWeeklyDepartmentPlanningLLObjects.CPHFourthWk);

		pageMonthlyPlanningPO.CompareValueDecimalFields(extentTest, DBCPHFourthWk, CPHFourthWk);

		// Sales history - Week -1
		Report_AddStep("testcase", " SALES HISTORY - FIRST WEEK", "", "", "");
		extentTest = report.startTest("Sales History - First Week");
		
		String SalesHistory1 = data.getText("SalesHistory");
		DBFirstWk = sql.CLRexecuteQuery(getContext(),
				SalesHistory1.replace("store", getContext().getStringProperty("store"))
						/*.replace("Week", String.valueOf(Wk4))*/.replace("Week", getContext().getStringProperty("Week1"))
						.replace("Department", getContext().getStringProperty("Longlife"))
						.replace("Division", getContext().getStringProperty("Division")));
		System.out.println("DB Value: " + DBFirstWk);

		String SalesHistoryTotal1 = data.getText("SalesHistoryTotal");
		DBFirstWkTtl = sql.CLRexecuteQuery(getContext(),
				SalesHistoryTotal1.replace("store", getContext().getStringProperty("store"))
						/*.replace("Week", String.valueOf(Wk4))*/.replace("Week", getContext().getStringProperty("Week1"))
						.replace("Department", getContext().getStringProperty("Longlife"))
						.replace("Division", getContext().getStringProperty("Division")));
		System.out.println("DB Total: " + DBFirstWkTtl);

		FirstWk = pageWeeklyDepartmentPlanningLLPO.UIResults(objWeeklyDepartmentPlanningObjects, "Sales history",
				objWeeklyDepartmentPlanningLLObjects.FirstWeekMonval,
				objWeeklyDepartmentPlanningLLObjects.FirstWeekTueval,
				objWeeklyDepartmentPlanningLLObjects.FirstWeekWedval,
				objWeeklyDepartmentPlanningLLObjects.FirstWeekThuval,
				objWeeklyDepartmentPlanningLLObjects.FirstWeekFrival,
				objWeeklyDepartmentPlanningLLObjects.FirstWeekSatval,
				objWeeklyDepartmentPlanningLLObjects.FirstWeekSunval,
				objWeeklyDepartmentPlanningLLObjects.FirstWeekCaption);
		FirstWkTtl = pageMonthlyPlanningPO.GetTotal(extentTest, objWeeklyDepartmentPlanningLLObjects.FirstWeekCaption,
				objWeeklyDepartmentPlanningLLObjects.FirstWeekValTotal);

		pageDailyTradingStatementStorePO.DTSCompareValuesTotal(extentTest, DBFirstWk, FirstWk);
		pageMonthlyPlanningPO.CompareValues(extentTest, DBFirstWkTtl, FirstWkTtl);

		// Sales history - Week -2
		Report_AddStep("testcase", " SALES HISTORY - SECOND WEEK", "", "", "");
		extentTest = report.startTest("Sales History - Second Week");
		
		String SalesHistory2 = data.getText("SalesHistory");
		DBSecondWk = sql.CLRexecuteQuery(getContext(),
				SalesHistory2.replace("store", getContext().getStringProperty("store"))
						/*.replace("Week", String.valueOf(Wk3))*/.replace("Week", getContext().getStringProperty("Week2"))
						.replace("Department", getContext().getStringProperty("Longlife"))
						.replace("Division", getContext().getStringProperty("Division")));
		System.out.println("DB Value: " + DBSecondWk);

		String SalesHistoryTotal2 = data.getText("SalesHistoryTotal");
		DBSecondWkTtl = sql.CLRexecuteQuery(getContext(),
				SalesHistoryTotal2.replace("store", getContext().getStringProperty("store"))
						/*.replace("Week", String.valueOf(Wk3))*/.replace("Week", getContext().getStringProperty("Week2"))
						.replace("Department", getContext().getStringProperty("Longlife"))
						.replace("Division", getContext().getStringProperty("Division")));
		System.out.println("DB Total: " + DBSecondWkTtl);

		SecondWk = pageWeeklyDepartmentPlanningLLPO.UIResults(objWeeklyDepartmentPlanningObjects, "Sales history",
				objWeeklyDepartmentPlanningLLObjects.SecondWeekMonval,
				objWeeklyDepartmentPlanningLLObjects.SecondWeekTueval,
				objWeeklyDepartmentPlanningLLObjects.SecondWeekWedval,
				objWeeklyDepartmentPlanningLLObjects.SecondWeekThuval,
				objWeeklyDepartmentPlanningLLObjects.SecondWeekFrival,
				objWeeklyDepartmentPlanningLLObjects.SecondWeekSatval,
				objWeeklyDepartmentPlanningLLObjects.SecondWeekSunval,
				objWeeklyDepartmentPlanningLLObjects.SecondWeekCaption);
		SecondWkTtl = pageMonthlyPlanningPO.GetTotal(extentTest, objWeeklyDepartmentPlanningLLObjects.SecondWeekCaption,
				objWeeklyDepartmentPlanningLLObjects.SecondWeekValTotal);

		pageDailyTradingStatementStorePO.DTSCompareValuesTotal(extentTest, DBSecondWk, SecondWk);
		pageMonthlyPlanningPO.CompareValues(extentTest, DBSecondWkTtl, SecondWkTtl);

		// Sales history - Week -3
		Report_AddStep("testcase", " SALES HISTORY - THIRD WEEK", "", "", "");
		extentTest = report.startTest("Sales History - Third Week");
		
		String SalesHistory3 = data.getText("SalesHistory");
		DBThirdWk = sql.CLRexecuteQuery(getContext(),
				SalesHistory3.replace("store", getContext().getStringProperty("store"))
						/*.replace("Week", String.valueOf(Wk2))*/.replace("Week", getContext().getStringProperty("Week3"))
						.replace("Department", getContext().getStringProperty("Longlife"))
						.replace("Division", getContext().getStringProperty("Division")));
		System.out.println("DB Value: " + DBThirdWk);

		String SalesHistoryTotal3 = data.getText("SalesHistoryTotal");
		DBThirdWkTtl = sql.CLRexecuteQuery(getContext(),
				SalesHistoryTotal3.replace("store", getContext().getStringProperty("store"))
						/*.replace("Week", String.valueOf(Wk2))*/.replace("Week", getContext().getStringProperty("Week3"))
						.replace("Department", getContext().getStringProperty("Longlife"))
						.replace("Division", getContext().getStringProperty("Division")));
		System.out.println("DB Total: " + DBThirdWkTtl);

		ThirdWk = pageWeeklyDepartmentPlanningLLPO.UIResults(objWeeklyDepartmentPlanningObjects, "Sales history",
				objWeeklyDepartmentPlanningLLObjects.ThirdWeekMonval,
				objWeeklyDepartmentPlanningLLObjects.ThirdWeekTueval,
				objWeeklyDepartmentPlanningLLObjects.ThirdWeekWedval,
				objWeeklyDepartmentPlanningLLObjects.ThirdWeekThuval,
				objWeeklyDepartmentPlanningLLObjects.ThirdWeekFrival,
				objWeeklyDepartmentPlanningLLObjects.ThirdWeekSatval,
				objWeeklyDepartmentPlanningLLObjects.ThirdWeekSunval,
				objWeeklyDepartmentPlanningLLObjects.ThirdWeekCaption);
		ThirdWkTtl = pageMonthlyPlanningPO.GetTotal(extentTest, objWeeklyDepartmentPlanningLLObjects.ThirdWeekCaption,
				objWeeklyDepartmentPlanningLLObjects.ThirdWeekValTotal);

		pageDailyTradingStatementStorePO.DTSCompareValuesTotal(extentTest, DBThirdWk, ThirdWk);
		pageMonthlyPlanningPO.CompareValues(extentTest, DBThirdWkTtl, ThirdWkTtl);

		// Sales history - Week -4
		Report_AddStep("testcase", " SALES HISTORY - FOURTH WEEK", "", "", "");
		extentTest = report.startTest("Sales History - Fourth Week");
		
		String SalesHistory4 = data.getText("SalesHistory");
		DBFourthWk = sql.CLRexecuteQuery(getContext(),
				SalesHistory3.replace("store", getContext().getStringProperty("store"))
						/*.replace("Week", String.valueOf(Wk1))*/.replace("Week", getContext().getStringProperty("Week4"))
						.replace("Department", getContext().getStringProperty("Longlife"))
						.replace("Division", getContext().getStringProperty("Division")));
		System.out.println("DB Value: " + DBFourthWk);

		String SalesHistoryTotal4 = data.getText("SalesHistoryTotal");
		DBFourthWkTtl = sql.CLRexecuteQuery(getContext(),
				SalesHistoryTotal3.replace("store", getContext().getStringProperty("store"))
						/*.replace("Week", String.valueOf(Wk1))*/.replace("Week", getContext().getStringProperty("Week4"))
						.replace("Department", getContext().getStringProperty("Longlife"))
						.replace("Division", getContext().getStringProperty("Division")));
		System.out.println("DB Total: " + DBFourthWkTtl);

		FourthWk = pageWeeklyDepartmentPlanningLLPO.UIResults(objWeeklyDepartmentPlanningObjects, "Sales history",
				objWeeklyDepartmentPlanningLLObjects.FourthWeekMonval,
				objWeeklyDepartmentPlanningLLObjects.FourthWeekTueval,
				objWeeklyDepartmentPlanningLLObjects.FourthWeekWedval,
				objWeeklyDepartmentPlanningLLObjects.FourthWeekThuval,
				objWeeklyDepartmentPlanningLLObjects.FourthWeekFrival,
				objWeeklyDepartmentPlanningLLObjects.FourthWeekSatval,
				objWeeklyDepartmentPlanningLLObjects.FourthWeekSunval,
				objWeeklyDepartmentPlanningLLObjects.FourthWeekCaption);
		FourthWkTtl = pageMonthlyPlanningPO.GetTotal(extentTest, objWeeklyDepartmentPlanningLLObjects.FourthWeekCaption,
				objWeeklyDepartmentPlanningLLObjects.FourthWeekValTotal);

		pageDailyTradingStatementStorePO.DTSCompareValuesTotal(extentTest, DBFourthWk, FourthWk);
		pageMonthlyPlanningPO.CompareValues(extentTest, DBFourthWkTtl, FourthWkTtl);

		// Average Daily Mix
		Report_AddStep("testcase", " AVERAG MIX", "", "", "");
		extentTest = report.startTest("Average Mix");
		
		String AverageMix = data.getText("AverageMix");
		DBAvgMix = sql.CLRexecuteQuery(getContext(),
				AverageMix.replace("store", getContext().getStringProperty("store"))
						.replace("Week", getContext().getStringProperty("SelectFiscalWeek"))
						.replace("Department", getContext().getStringProperty("Longlife"))
						.replace("Division", getContext().getStringProperty("Division")));
		System.out.println("DB Value: " + DBAvgMix);

		AvgMix = pageWeeklyDepartmentPlanningLLPO.UIResults(objWeeklyDepartmentPlanningObjects, "Sales history",
				objWeeklyDepartmentPlanningLLObjects.AvgMixMonval, objWeeklyDepartmentPlanningLLObjects.AvgMixTueval,
				objWeeklyDepartmentPlanningLLObjects.AvgMixWedval, objWeeklyDepartmentPlanningLLObjects.AvgMixThuval,
				objWeeklyDepartmentPlanningLLObjects.AvgMixFrival, objWeeklyDepartmentPlanningLLObjects.AvgMixSatval,
				objWeeklyDepartmentPlanningLLObjects.AvgMixSunval,
				objWeeklyDepartmentPlanningLLObjects.AvgDailyMixCaption);

		pageDailyTradingStatementStorePO.DTSCompareValuesTotal(extentTest, DBAvgMix, AvgMix);
		
		
		//Average Growth Vs LY%
				Report_AddStep("testcase", " Average Growth Vs LY Per", "", "", "");
				extentTest = report.startTest("Average Growth Vs LY Per");

				String AverageGrowthVsLYPer = data.getText("AverageGrowthVsLYPerLL");
				DBAvgGrowth = sql.CLRexecuteQuery(getContext(),
						AverageGrowthVsLYPer.replace("store", getContext().getStringProperty("store"))
								.replace("Week", getContext().getStringProperty("SelectFiscalWeek"))
								.replace("Department", getContext().getStringProperty("Longlife")).replace("LYWk", getContext().getStringProperty("LYWk"))
								.replace("Division", getContext().getStringProperty("Division"))
								.replace("Division", getContext().getStringProperty("Division"))/*.replace("WEEK1", getContext().getStringProperty("Week1")).replace("WEEK4", getContext().getStringProperty("Week4"))
								.replace("LYWK1", getContext().getStringProperty("LYWeek1")).replace("LYWK4", getContext().getStringProperty("LYWeek4"))*/.replace("WEEK1", String.valueOf(Wk4)).replace("WEEK4", String.valueOf(Wk1)).
								replace("LYWK1", String.valueOf(LYWk4)).replace("LYWK4", String.valueOf(LYWk1)));
				System.out.println("DB Value: " + DBAvgGrowth);

				Avggrowth = pageWeeklyDepartmentPlanningLLPO.UIResults(objWeeklyDepartmentPlanningObjects, "Sales history",
						objWeeklyDepartmentPlanningLLObjects.AvgGrowthMonval, objWeeklyDepartmentPlanningLLObjects.AvgGrowthTueval,
						objWeeklyDepartmentPlanningLLObjects.AvgGrowthWedval, objWeeklyDepartmentPlanningLLObjects.AvgGrowthThuval,
						objWeeklyDepartmentPlanningLLObjects.AvgGrowthFrival, objWeeklyDepartmentPlanningLLObjects.AvgGrowthSatval,
						objWeeklyDepartmentPlanningLLObjects.AvgGrowthSunval, objWeeklyDepartmentPlanningLLObjects.AvgGrowthCaption);

				pageDailyTradingStatementStorePO.DTSCompareValuesTotal(extentTest, DBAvgGrowth, Avggrowth);
		

		///////////////////// LONG LIFE - PLANNING FOR THE WEEK
		///////////////////// /////////////////////////
				
				
				//Last Year sales 
				
				Report_AddStep("testcase", " LAST YEAR SALES - PLANNING FOR THE WEEK", "", "", "");
				extentTest = report.startTest("Last Year Sales");

				String LastYearSalesind = data.getText("LastYearSalesind");
				DBLastYearSls = sql.CLRexecuteQuery(getContext(),
						LastYearSalesind.replace("store", getContext().getStringProperty("store")).replace("LYWk", getContext().getStringProperty("LYWk"))
								.replace("Week", getContext().getStringProperty("SelectFiscalWeek"))
								.replace("Department", getContext().getStringProperty("Longlife"))
								.replace("Division", getContext().getStringProperty("Division")));
				System.out.println("DB Value: " + DBLastYearSls);

				String LastYearSalesTotal = data.getText("LastYearSalesTotal");
				DBLastYearSlsTtl = sql.CLRexecuteQuery(getContext(),
						LastYearSalesTotal.replace("store", getContext().getStringProperty("store")).replace("LYWk", getContext().getStringProperty("LYWk"))
								.replace("Week", getContext().getStringProperty("SelectFiscalWeek"))
								.replace("Department", getContext().getStringProperty("Longlife"))
								.replace("Division", getContext().getStringProperty("Division")));
				System.out.println("DB Total: " + DBLastYearSlsTtl);

				LastyearSls = pageWeeklyDepartmentPlanningLLPO.UIResults(objWeeklyDepartmentPlanningObjects, "Last Year Sales",
						objWeeklyDepartmentPlanningLLObjects.LastYearSalesMonVal,
						objWeeklyDepartmentPlanningLLObjects.LastYearSalesTueVal,
						objWeeklyDepartmentPlanningLLObjects.LastYearSalesWedVal,
						objWeeklyDepartmentPlanningLLObjects.LastYearSalesThuVal,
						objWeeklyDepartmentPlanningLLObjects.LastYearSalesFriVal,
						objWeeklyDepartmentPlanningLLObjects.LastYearSalesSatVal,
						objWeeklyDepartmentPlanningLLObjects.LastYearSalesSunVal,
						objWeeklyDepartmentPlanningLLObjects.LastYearSalesCaption);
				LastyearSlsTtl = pageMonthlyPlanningPO.GetTotal(extentTest,
						objWeeklyDepartmentPlanningLLObjects.LastYearSalesCaption,
						objWeeklyDepartmentPlanningLLObjects.LastYearSalesTotal);

				pageDailyTradingStatementStorePO.DTSCompareValuesTotal(extentTest, DBLastYearSls, LastyearSls);
				pageMonthlyPlanningPO.CompareValues(extentTest, DBLastYearSlsTtl, LastyearSlsTtl);		
				
		// Generated Sales
		Report_AddStep("testcase", " GENERATED SALES - PLANNING FOR THE WEEK", "", "", "");
		extentTest = report.startTest("Generated Sales");
		
		String GeneratedSales = data.getText("GeneratedSales");
		DBGnrtSls = sql.CLRexecuteQuery(getContext(),
				GeneratedSales.replace("store", getContext().getStringProperty("store"))
						.replace("Week", getContext().getStringProperty("SelectFiscalWeek"))
						.replace("Department", getContext().getStringProperty("Longlife"))
						.replace("Division", getContext().getStringProperty("Division")));
		System.out.println("DB Value: " + DBGnrtSls);

		String GeneratedSalesTotal = data.getText("GeneratedSalesTotal");
		DBGnrtSlsTtl = sql.CLRexecuteQuery(getContext(),
				GeneratedSalesTotal.replace("store", getContext().getStringProperty("store"))
						.replace("Week", getContext().getStringProperty("SelectFiscalWeek"))
						.replace("Department", getContext().getStringProperty("Longlife"))
						.replace("Division", getContext().getStringProperty("Division")));
		System.out.println("DB Total: " + DBGnrtSlsTtl);

		GnrtSls = pageWeeklyDepartmentPlanningLLPO.UIResults(objWeeklyDepartmentPlanningObjects, "Generated Sales",
				objWeeklyDepartmentPlanningLLObjects.GeneratedSalesMonVal,
				objWeeklyDepartmentPlanningLLObjects.GeneratedSalesTueVal,
				objWeeklyDepartmentPlanningLLObjects.GeneratedSalesWedVal,
				objWeeklyDepartmentPlanningLLObjects.GeneratedSalesThuVal,
				objWeeklyDepartmentPlanningLLObjects.GeneratedSalesFriVal,
				objWeeklyDepartmentPlanningLLObjects.GeneratedSalesSatVal,
				objWeeklyDepartmentPlanningLLObjects.GeneratedSalesSunVal,
				objWeeklyDepartmentPlanningLLObjects.GeneratedSalesCaption);
		GnrtSlsTtl = pageMonthlyPlanningPO.GetTotal(extentTest,
				objWeeklyDepartmentPlanningLLObjects.GeneratedSalesCaption,
				objWeeklyDepartmentPlanningLLObjects.GeneratedSalesTotal);

		pageDailyTradingStatementStorePO.DTSCompareValuesTotal(extentTest, DBGnrtSls, GnrtSls);
		pageMonthlyPlanningPO.CompareValues(extentTest, DBGnrtSlsTtl, GnrtSlsTtl);

		// PlannedSales
		Report_AddStep("testcase", " PLANNED SALES - PLANNING FOR THE WEEK", "", "", "");
		extentTest = report.startTest("Planned Sales");
		
		String PlannedSales2 = data.getText("PlannedSalesWk");
		DBPlndSlsWk = sql.CLRexecuteQuery(getContext(),
				PlannedSales2.replace("store", getContext().getStringProperty("store"))
						.replace("Week", getContext().getStringProperty("SelectFiscalWeek"))
						.replace("Department", getContext().getStringProperty("Longlife"))
						.replace("Division", getContext().getStringProperty("Division")));
		System.out.println("DB Value: " + DBPlndSlsWk);

		String PlannedSalesTotal = data.getText("PlannedSalesTotal");
		DBPlndSlsWkTtl = sql.CLRexecuteQuery(getContext(),
				PlannedSalesTotal.replace("store", getContext().getStringProperty("store"))
						.replace("Week", getContext().getStringProperty("SelectFiscalWeek"))
						.replace("Department", getContext().getStringProperty("Longlife"))
						.replace("Division", getContext().getStringProperty("Division")));
		System.out.println("DB Total: " + DBPlndSlsWkTtl);

		PlndSlsWk = pageWeeklyDepartmentPlanningLLPO.UIResults(objWeeklyDepartmentPlanningObjects, "Planned Sales",
				objWeeklyDepartmentPlanningLLObjects.PlannedSalesMonVal,
				objWeeklyDepartmentPlanningLLObjects.PlannedSalesTueVal,
				objWeeklyDepartmentPlanningLLObjects.PlannedSalesWedVal,
				objWeeklyDepartmentPlanningLLObjects.PlannedSalesThuVal,
				objWeeklyDepartmentPlanningLLObjects.PlannedSalesFriVal,
				objWeeklyDepartmentPlanningLLObjects.PlannedSalesSatVal,
				objWeeklyDepartmentPlanningLLObjects.PlannedSalesSunVal,
				objWeeklyDepartmentPlanningLLObjects.PlannedSalesCaption);
		PlndSlsWkTtl = pageWeeklyDepartmentPlanningLLPO.GetTotal(extentTest,
				objWeeklyDepartmentPlanningLLObjects.PlannedSalesCaption,
				objWeeklyDepartmentPlanningLLObjects.PlannedSalesTotal);

		pageDailyTradingStatementStorePO.DTSCompareValuesTotal(extentTest, DBPlndSlsWk, PlndSlsWk);
		pageMonthlyPlanningPO.CompareValues(extentTest, DBPlndSlsWkTtl, PlndSlsWkTtl);
		
		
	//Planned Sales VS LY%
				Report_AddStep("testcase", " Planned Sales VS LYPer - PLANNING FOR THE WEEK", "", "", "");
				extentTest = report.startTest("Planned Sales VS LYPer");

						String PlannedSalesVSLYPer = data.getText("PlannedSalesVSLYPer");
						DBPlannedSalesVSLYPer = sql.CLRexecuteQuery(getContext(),
								PlannedSalesVSLYPer.replace("store", getContext().getStringProperty("store")).replace("LYWk", getContext().getStringProperty("LYWk"))
										.replace("Week", getContext().getStringProperty("SelectFiscalWeek"))
										.replace("Department", getContext().getStringProperty("Longlife"))
										.replace("Division", getContext().getStringProperty("Division")));
						System.out.println("DB Value: " + DBPlannedSalesVSLYPer);

						 
						PlannedSalesVSLY = pageWeeklyDepartmentPlanningLLPO.UIResults(objWeeklyDepartmentPlanningObjects, "Planned Sales VS LYPer",
								objWeeklyDepartmentPlanningLLObjects.PlannedSalesVSLYMonVal,
								objWeeklyDepartmentPlanningLLObjects.PlannedSalesVSLYTueVal,
								objWeeklyDepartmentPlanningLLObjects.PlannedSalesVSLYWedVal,
								objWeeklyDepartmentPlanningLLObjects.PlannedSalesVSLYThuVal,
								objWeeklyDepartmentPlanningLLObjects.PlannedSalesVSLYFriVal,
								objWeeklyDepartmentPlanningLLObjects.PlannedSalesVSLYSatVal,
								objWeeklyDepartmentPlanningLLObjects.PlannedSalesVSLYSunVal,
								objWeeklyDepartmentPlanningLLObjects.PlannedSalesVSLYCaption);
						
						pageDailyTradingStatementStorePO.DTSCompareValuesTotal(extentTest, DBPlannedSalesVSLYPer, PlannedSalesVSLY);
						//PlannedSalesDailyMix%		
						
						Report_AddStep("testcase", " PlannedSalesDailyMix - PLANNING FOR THE WEEK", "", "", "");
						extentTest = report.startTest("PlannedSalesDailyMix");

								String PlannedSalesDailyMix = data.getText("PlannedSalesDailyMix");
								DBPlannedSalesDailyMix = sql.CLRexecuteQuery(getContext(),
										PlannedSalesDailyMix.replace("store", getContext().getStringProperty("store")).replace("LYWk", getContext().getStringProperty("LYWk"))
												.replace("Week", getContext().getStringProperty("SelectFiscalWeek"))
												.replace("Department", getContext().getStringProperty("Longlife"))
												.replace("Division", getContext().getStringProperty("Division")));
								System.out.println("DB Value: " + DBPlannedSalesDailyMix);

								

								PlannedSalesDailyMixper = pageWeeklyDepartmentPlanningLLPO.UIResults(objWeeklyDepartmentPlanningObjects, "PlannedSalesdailymix",
										objWeeklyDepartmentPlanningLLObjects.PlannedSalesdailymixMonVal,
										objWeeklyDepartmentPlanningLLObjects.PlannedSalesdailymixTueVal,
										objWeeklyDepartmentPlanningLLObjects.PlannedSalesdailymixWedVal,
										objWeeklyDepartmentPlanningLLObjects.PlannedSalesdailymixThuVal,
										objWeeklyDepartmentPlanningLLObjects.PlannedSalesdailymixFriVal,
										objWeeklyDepartmentPlanningLLObjects.PlannedSalesdailymixSatVal,
										objWeeklyDepartmentPlanningLLObjects.PlannedSalesdailymixSunVal,
										objWeeklyDepartmentPlanningLLObjects.PlannedSalesVSLYCaption);
								
								pageDailyTradingStatementStorePO.DTSCompareValuesTotal(extentTest, DBPlannedSalesDailyMix, PlannedSalesDailyMixper);
				

		// Generated Item Price
		Report_AddStep("testcase", " GENERATED ITEM PRICE - PLANNING FOR THE WEEK", "", "", "");
		extentTest = report.startTest("Generated Item price");
		
		String GeneratedItemPrice = data.getText("GeneratedItemPrice");
		DBGnrtItmPr = sql.CLRexecuteQuery(getContext(),
				GeneratedItemPrice.replace("store", getContext().getStringProperty("store"))
						.replace("Week", getContext().getStringProperty("SelectFiscalWeek"))
						.replace("Department", getContext().getStringProperty("Longlife"))
						.replace("Division", getContext().getStringProperty("Division")));
		System.out.println("DB Value: " + DBGnrtItmPr);

		String GeneratedItemPriceTotal = data.getText("GeneratedItemPriceTotal");
		DBGnrtItmPrTtl = sql.CLRexecuteQuery(getContext(),
				GeneratedItemPriceTotal.replace("store", getContext().getStringProperty("store"))
						.replace("Week", getContext().getStringProperty("SelectFiscalWeek"))
						.replace("Department", getContext().getStringProperty("Longlife"))
						.replace("Division", getContext().getStringProperty("Division")));
		System.out.println("DB Total: " + DBGnrtItmPrTtl);

		GnrtItmPr = pageWeeklyDepartmentPlanningLLPO.UIResults(objWeeklyDepartmentPlanningObjects,
				"Generated Item Price", objWeeklyDepartmentPlanningLLObjects.GnrtItmPriceMonVal,
				objWeeklyDepartmentPlanningLLObjects.GnrtItmPriceTueVal,
				objWeeklyDepartmentPlanningLLObjects.GnrtItmPriceWedVal,
				objWeeklyDepartmentPlanningLLObjects.GnrtItmPriceThuVal,
				objWeeklyDepartmentPlanningLLObjects.GnrtItmPriceFriVal,
				objWeeklyDepartmentPlanningLLObjects.GnrtItmPriceSatVal,
				objWeeklyDepartmentPlanningLLObjects.GnrtItmPriceSunVal,
				objWeeklyDepartmentPlanningLLObjects.GeneratedItemPriceCaption);
		GnrtItmPrTtl = pageMonthlyPlanningPO.GetTotal(extentTest,
				objWeeklyDepartmentPlanningLLObjects.GeneratedItemPriceCaption,
				objWeeklyDepartmentPlanningLLObjects.GeneratedItemPriceTotal);

		pageDailyTradingStatementStorePO.DTSCompareValuesTotal(extentTest, DBGnrtItmPr, GnrtItmPr);
		pageMonthlyPlanningPO.CompareValueDecimalFields(extentTest, DBGnrtItmPrTtl, GnrtItmPrTtl);

		// Planned Item Price
		Report_AddStep("testcase", " PLANNED ITEM PRICE - PLANNING FOR THE WEEK", "", "", "");
		extentTest = report.startTest("Planned Item price");
		
		String PlannedItemPrice = data.getText("PlannedItemPrice");
		DBPlndItmPr = sql.CLRexecuteQuery(getContext(),
				PlannedItemPrice.replace("store", getContext().getStringProperty("store"))
						.replace("Week", getContext().getStringProperty("SelectFiscalWeek"))
						.replace("Department", getContext().getStringProperty("Longlife"))
						.replace("Division", getContext().getStringProperty("Division")));
		System.out.println("DB Value: " + DBPlndItmPr);

		String PlannedItemPriceTotal = data.getText("PlannedItemPriceTotal");
		DBPlndItmPrTtl = sql.CLRexecuteQuery(getContext(),
				PlannedItemPriceTotal.replace("store", getContext().getStringProperty("store"))
						.replace("Week", getContext().getStringProperty("SelectFiscalWeek"))
						.replace("Department", getContext().getStringProperty("Longlife"))
						.replace("Division", getContext().getStringProperty("Division")));
		System.out.println("DB Total: " + DBPlndItmPrTtl);

		PlndItmPr = pageWeeklyDepartmentPlanningLLPO.UIResults(objWeeklyDepartmentPlanningObjects, "Planned Item Price",
				objWeeklyDepartmentPlanningLLObjects.PlndItmPriceMonValue,
				objWeeklyDepartmentPlanningLLObjects.PlndItmPriceTueValue,
				objWeeklyDepartmentPlanningLLObjects.PlndItmPriceWedValue,
				objWeeklyDepartmentPlanningLLObjects.PlndItmPriceThuValue,
				objWeeklyDepartmentPlanningLLObjects.PlndItmPriceFriValue,
				objWeeklyDepartmentPlanningLLObjects.PlndItmPriceSatValue,
				objWeeklyDepartmentPlanningLLObjects.PlndItmPriceSunValue,
				objWeeklyDepartmentPlanningLLObjects.PlannedItemPriceCaption);
		PlndItmPrTtl = pageWeeklyDepartmentPlanningLLPO.GetTotal(extentTest,
				objWeeklyDepartmentPlanningLLObjects.PlannedItemPriceCaption,
				objWeeklyDepartmentPlanningLLObjects.PlannedItemPriceTotal);

		pageDailyTradingStatementStorePO.CompareValueDecimalTotal(extentTest, DBPlndItmPr, PlndItmPr);
		pageMonthlyPlanningPO.CompareValueDecimalFields(extentTest, DBPlndItmPrTtl, PlndItmPrTtl);

		// Generated Items
		Report_AddStep("testcase", " GENERATED ITEMS - PLANNING FOR THE WEEK", "", "", "");
		extentTest = report.startTest("Generated Items");
		
		String GeneratedItems = data.getText("GeneratedItems");
		DBGnrtItm = sql.CLRexecuteQuery(getContext(),
				GeneratedItems.replace("store", getContext().getStringProperty("store"))
						.replace("Week", getContext().getStringProperty("SelectFiscalWeek"))
						.replace("Department", getContext().getStringProperty("Longlife"))
						.replace("Division", getContext().getStringProperty("Division")));
		System.out.println("DB Value: " + DBGnrtItm);

		String GeneratedItemsTotal = data.getText("GeneratedItemsTotal");
		DBGnrtItmTtl = sql.CLRexecuteQuery(getContext(),
				GeneratedItemsTotal.replace("store", getContext().getStringProperty("store"))
						.replace("Week", getContext().getStringProperty("SelectFiscalWeek"))
						.replace("Department", getContext().getStringProperty("Longlife"))
						.replace("Division", getContext().getStringProperty("Division")));
		System.out.println("DB Total: " + DBGnrtItmTtl);

		GnrtItm = pageWeeklyDepartmentPlanningLLPO.UIResults(objWeeklyDepartmentPlanningObjects, "Generated Items",
				objWeeklyDepartmentPlanningLLObjects.GnrtItmMonVal, objWeeklyDepartmentPlanningLLObjects.GnrtItmTueVal,
				objWeeklyDepartmentPlanningLLObjects.GnrtItmWedVal, objWeeklyDepartmentPlanningLLObjects.GnrtItmThuVal,
				objWeeklyDepartmentPlanningLLObjects.GnrtItmFriVal, objWeeklyDepartmentPlanningLLObjects.GnrtItmSatVal,
				objWeeklyDepartmentPlanningLLObjects.GnrtItmSunVal,
				objWeeklyDepartmentPlanningLLObjects.GeneratedItemCaption);
		GnrtItmTtl = pageMonthlyPlanningPO.GetTotal(extentTest,
				objWeeklyDepartmentPlanningLLObjects.GeneratedItemCaption,
				objWeeklyDepartmentPlanningLLObjects.GeneratedItemTotal);

		pageDailyTradingStatementStorePO.DTSCompareValuesTotal(extentTest, DBGnrtItm, GnrtItm);
		pageMonthlyPlanningPO.CompareValues(extentTest, DBGnrtItmTtl, GnrtItmTtl);

		// Planned Items
		Report_AddStep("testcase", " PLANNED ITEMS - PLANNING FOR THE WEEK", "", "", "");
		extentTest = report.startTest("Planned Items");
		
		String PlannedItems = data.getText("PlannedItems");
		DBPlndItm = sql.CLRexecuteQuery(getContext(),
				PlannedItems.replace("store", getContext().getStringProperty("store"))
						.replace("Week", getContext().getStringProperty("SelectFiscalWeek"))
						.replace("Department", getContext().getStringProperty("Longlife"))
						.replace("Division", getContext().getStringProperty("Division")));
		System.out.println("DB Value: " + DBPlndItm);

		String PlannedItemsTotal = data.getText("PlannedItemsTotal");
		DBPlndItmTtl = sql.CLRexecuteQuery(getContext(),
				PlannedItemsTotal.replace("store", getContext().getStringProperty("store"))
						.replace("Week", getContext().getStringProperty("SelectFiscalWeek"))
						.replace("Department", getContext().getStringProperty("Longlife"))
						.replace("Division", getContext().getStringProperty("Division")));
		System.out.println("DB Total: " + DBPlndItmTtl);

		PlndItm = pageWeeklyDepartmentPlanningLLPO.UIResults(objWeeklyDepartmentPlanningObjects, "Planned Items",
				objWeeklyDepartmentPlanningLLObjects.PlndItmMonVal, objWeeklyDepartmentPlanningLLObjects.PlndItmTueVal,
				objWeeklyDepartmentPlanningLLObjects.PlndItmWedVal, objWeeklyDepartmentPlanningLLObjects.PlndItmThuVal,
				objWeeklyDepartmentPlanningLLObjects.PlndItmFriVal, objWeeklyDepartmentPlanningLLObjects.PlndItmSatVal,
				objWeeklyDepartmentPlanningLLObjects.PlndItmSunVal,
				objWeeklyDepartmentPlanningLLObjects.PlannedItemsCaption);
		PlndItmTtl = pageMonthlyPlanningPO.GetTotal(extentTest,
				objWeeklyDepartmentPlanningLLObjects.PlannedItemsCaption,
				objWeeklyDepartmentPlanningLLObjects.PlannedItemsTotal);

		pageDailyTradingStatementStorePO.DTSCompareValuesTotal(extentTest, DBPlndItm, PlndItm);
		pageMonthlyPlanningPO.CompareValues(extentTest, DBPlndItmTtl, PlndItmTtl);

		// WeekType
		/*
		 * Report_AddStep("testcase"," WEEK TYPE - PLANNING FOR THE WEEK" ,"","", "");
		 * 
		 * String WeekType = data.getText("WeekType"); DBWkType =
		 * sql.CLRexecuteQuery(getContext(),
		 * WeekType.replace("store",getContext().getStringProperty("store")).replace(
		 * "Week",getContext().getStringProperty("SelectFiscalWeek"))
		 * .replace("Department",getContext().getStringProperty("Longlife")).replace(
		 * "Division",getContext().getStringProperty("Division")));
		 * System.out.println("DB Value: "+DBWkType);
		 * 
		 * WkType=pageMonthlyPlanningPO.GetTotal(extentTest,
		 * objWeeklyDepartmentPlanningLLObjects.WeekTypeCaption,
		 * objWeeklyDepartmentPlanningLLObjects.WeekTypeOption);
		 * pageMonthlyPlanningPO.CompareValues(extentTest,DBWkType,WkType);
		 */

		// SMS Hours
		Report_AddStep("testcase", " SMS HOURS - PLANNING FOR THE WEEK", "", "", "");
		extentTest = report.startTest("SMS Hours");
		
		String SMSHours = data.getText("SMSHours");
		DBSMSHr = sql.CLRexecuteQuery(getContext(),
				SMSHours.replace("store", getContext().getStringProperty("store"))
						.replace("Week", getContext().getStringProperty("SelectFiscalWeek"))
						.replace("Department", getContext().getStringProperty("Longlife"))
						.replace("Division", getContext().getStringProperty("Division")));
		System.out.println("DB Value: " + DBSMSHr);

		String SMSHoursTotal = data.getText("SMSHoursTotal");
		DBSMSHrTtl = sql.CLRexecuteQuery(getContext(),
				SMSHoursTotal.replace("store", getContext().getStringProperty("store"))
						.replace("Week", getContext().getStringProperty("SelectFiscalWeek"))
						.replace("Department", getContext().getStringProperty("Longlife"))
						.replace("Division", getContext().getStringProperty("Division")));
		System.out.println("DB Total: " + DBSMSHrTtl);

		SMSHr = pageWeeklyDepartmentPlanningLLPO.UIResults(objWeeklyDepartmentPlanningObjects, "SMS Hours",
				objWeeklyDepartmentPlanningLLObjects.SMSHrsMonVal, objWeeklyDepartmentPlanningLLObjects.SMSHrsTueVal,
				objWeeklyDepartmentPlanningLLObjects.SMSHrsWedVal, objWeeklyDepartmentPlanningLLObjects.SMSHrsThuVal,
				objWeeklyDepartmentPlanningLLObjects.SMSHrsFriVal, objWeeklyDepartmentPlanningLLObjects.SMSHrsSatVal,
				objWeeklyDepartmentPlanningLLObjects.SMSHrsSunVal,
				objWeeklyDepartmentPlanningLLObjects.SMSHoursCaption);
		SMSHrTtl = pageMonthlyPlanningPO.GetTotal(extentTest, objWeeklyDepartmentPlanningLLObjects.SMSHoursCaption,
				objWeeklyDepartmentPlanningLLObjects.SMSHoursTotal);

		pageDailyTradingStatementStorePO.CompareValueDecimalTotal(extentTest, DBSMSHr, SMSHr);
		pageMonthlyPlanningPO.CompareValueDecimalFields(extentTest, DBSMSHrTtl, SMSHrTtl);

		// Inventory
		Report_AddStep("testcase", " INVENTORY - PLANNING FOR THE WEEK", "", "", "");
		extentTest = report.startTest("Inventory Department");
		
		String Inventory = data.getText("Inventory");
		DBInv = sql.CLRexecuteQuery(getContext(),
				Inventory.replace("store", getContext().getStringProperty("store"))
						.replace("Week", getContext().getStringProperty("SelectFiscalWeek")).replace("Department", "10")
						.replace("Division", getContext().getStringProperty("Division")));
		System.out.println("DB Value: " + DBInv);

		String InventoryTotal = data.getText("InventoryTotal");
		DBInvTtl = sql.CLRexecuteQuery(getContext(),
				InventoryTotal.replace("store", getContext().getStringProperty("store"))
						.replace("Week", getContext().getStringProperty("SelectFiscalWeek")).replace("Department", "10")
						.replace("Division", getContext().getStringProperty("Division")));
		System.out.println("DB Total: " + DBInvTtl);

		Inv = pageWeeklyDepartmentPlanningLLPO.UIResults(objWeeklyDepartmentPlanningObjects, "SMS Hours",
				objWeeklyDepartmentPlanningLLObjects.InventoryMonVal,
				objWeeklyDepartmentPlanningLLObjects.InventoryTueVal,
				objWeeklyDepartmentPlanningLLObjects.InventoryWedVal,
				objWeeklyDepartmentPlanningLLObjects.InventoryThuVal,
				objWeeklyDepartmentPlanningLLObjects.InventoryFriVal,
				objWeeklyDepartmentPlanningLLObjects.InventorySatVal,
				objWeeklyDepartmentPlanningLLObjects.InventorySunVal,
				objWeeklyDepartmentPlanningLLObjects.InventoryCaption);
		InvTtl = pageMonthlyPlanningPO.GetTotal(extentTest, objWeeklyDepartmentPlanningLLObjects.InventoryCaption,
				objWeeklyDepartmentPlanningLLObjects.InventoryTotal);

		pageDailyTradingStatementStorePO.CompareValueDecimalTotal(extentTest, DBInv, Inv);
		pageMonthlyPlanningPO.CompareValueDecimalFields(extentTest, DBInvTtl, InvTtl);

		// Replenishment
		Report_AddStep("testcase", " REPLENISHMENT - PLANNING FOR THE WEEK", "", "", "");
		extentTest = report.startTest("Replenishment Department");
		
		String Replenishment = data.getText("Replenishment");
		DBRepl = sql.CLRexecuteQuery(getContext(),
				Replenishment.replace("store", getContext().getStringProperty("store"))
						.replace("Week", getContext().getStringProperty("SelectFiscalWeek")).replace("Department", "5")
						.replace("Division", getContext().getStringProperty("Division")));
		System.out.println("DB Value: " + DBRepl);

		String ReplenishmentTotal = data.getText("ReplenishmentTotal");
		DBReplTtl = sql.CLRexecuteQuery(getContext(),
				ReplenishmentTotal.replace("store", getContext().getStringProperty("store"))
						.replace("Week", getContext().getStringProperty("SelectFiscalWeek")).replace("Department", "5")
						.replace("Division", getContext().getStringProperty("Division")));
		System.out.println("DB Total: " + DBReplTtl);

		Repl = pageWeeklyDepartmentPlanningLLPO.UIResults(objWeeklyDepartmentPlanningObjects, "SMS Hours",
				objWeeklyDepartmentPlanningLLObjects.ReplMonVal, objWeeklyDepartmentPlanningLLObjects.ReplTueVal,
				objWeeklyDepartmentPlanningLLObjects.ReplWedVal, objWeeklyDepartmentPlanningLLObjects.ReplThuVal,
				objWeeklyDepartmentPlanningLLObjects.ReplFriVal, objWeeklyDepartmentPlanningLLObjects.ReplSatVal,
				objWeeklyDepartmentPlanningLLObjects.ReplSunVal, objWeeklyDepartmentPlanningLLObjects.ReplCaption);
		ReplTtl = pageMonthlyPlanningPO.GetTotal(extentTest, objWeeklyDepartmentPlanningLLObjects.ReplCaption,
				objWeeklyDepartmentPlanningLLObjects.ReplTotal);

		pageDailyTradingStatementStorePO.CompareValueDecimalTotal(extentTest, DBRepl, Repl);
		pageMonthlyPlanningPO.CompareValueDecimalFields(extentTest, DBReplTtl, ReplTtl);

		// Merchandising
		Report_AddStep("testcase", " MERCHENDISING - PLANNING FOR THE WEEK", "", "", "");
		extentTest = report.startTest("Merchendising Department");
		
		String Merchendising = data.getText("Merchendising");
		DBMerch = sql.CLRexecuteQuery(getContext(),
				Merchendising.replace("store", getContext().getStringProperty("store"))
						.replace("Week", getContext().getStringProperty("SelectFiscalWeek"))
						.replace("Department", "15").
						replace("Division", getContext().getStringProperty("Division")));
		System.out.println("DB Value: " + DBMerch);

		String MerchendisingTotal = data.getText("MerchendisingTotal");
		DBMerchTtl = sql.CLRexecuteQuery(getContext(),
				MerchendisingTotal.replace("store", getContext().getStringProperty("store"))
						.replace("Week", getContext().getStringProperty("SelectFiscalWeek"))
						.replace("Department", "15").
						replace("Division", getContext().getStringProperty("Division")));
		System.out.println("DB Total: " + DBMerchTtl);

		Merch = pageWeeklyDepartmentPlanningLLPO.UIResults(objWeeklyDepartmentPlanningObjects, "SMS Hours",
				objWeeklyDepartmentPlanningLLObjects.MerchendisingMonVal,
				objWeeklyDepartmentPlanningLLObjects.MerchendisingTueVal,
				objWeeklyDepartmentPlanningLLObjects.MerchendisingWedVal,
				objWeeklyDepartmentPlanningLLObjects.MerchendisingThuVal,
				objWeeklyDepartmentPlanningLLObjects.MerchendisingFriVal,
				objWeeklyDepartmentPlanningLLObjects.MerchendisingSatVal,
				objWeeklyDepartmentPlanningLLObjects.MerchendisingSunVal,
				objWeeklyDepartmentPlanningLLObjects.MerchendisingCaption);
		MerchTtl = pageMonthlyPlanningPO.GetTotal(extentTest, objWeeklyDepartmentPlanningLLObjects.MerchendisingCaption,
				objWeeklyDepartmentPlanningLLObjects.MerchendisingTotal);

		pageDailyTradingStatementStorePO.CompareValueDecimalTotal(extentTest, DBMerch, Merch);
		pageMonthlyPlanningPO.CompareValueDecimalFields(extentTest, DBMerchTtl, MerchTtl);

		// Kronos Hours
		Report_AddStep("testcase", " KRONOS HOURS - PLANNING FOR THE WEEK", "", "", "");
		extentTest = report.startTest("Kronos Hours");
		
		String KronosHours = data.getText("KronosHours");
		DBPlndHrWk = sql.CLRexecuteQuery(getContext(),
				KronosHours.replace("store", getContext().getStringProperty("store"))
						.replace("Week", getContext().getStringProperty("SelectFiscalWeek"))
						.replace("Department", getContext().getStringProperty("Longlife"))
						.replace("Division", getContext().getStringProperty("Division")));
		System.out.println("DB Value: " + DBPlndHrWk);

		String KronosHoursHours = data.getText("KronosHoursHours");
		DBPlndHrWkTtl = sql.CLRexecuteQuery(getContext(),
				KronosHoursHours.replace("store", getContext().getStringProperty("store"))
						.replace("Week", getContext().getStringProperty("SelectFiscalWeek"))
						.replace("Department", getContext().getStringProperty("Longlife"))
						.replace("Division", getContext().getStringProperty("Division")));
		System.out.println("DB Total: " + DBPlndHrWkTtl);

		PlndHrWk = pageWeeklyDepartmentPlanningLLPO.UIResults(objWeeklyDepartmentPlanningObjects, "Kronos Hours",
				objWeeklyDepartmentPlanningLLObjects.PlndHrsMonVal, objWeeklyDepartmentPlanningLLObjects.PlndHrsTueVal,
				objWeeklyDepartmentPlanningLLObjects.PlndHrsWedVal, objWeeklyDepartmentPlanningLLObjects.PlndHrsThuVal,
				objWeeklyDepartmentPlanningLLObjects.PlndHrsFriVal, objWeeklyDepartmentPlanningLLObjects.PlndHrsSatVal,
				objWeeklyDepartmentPlanningLLObjects.PlndHrsSunVal,
				objWeeklyDepartmentPlanningLLObjects.PlndHrsCaption);
		PlndHrWkTtl = pageMonthlyPlanningPO.GetTotal(extentTest, objWeeklyDepartmentPlanningLLObjects.PlndHrsCaption,
				objWeeklyDepartmentPlanningLLObjects.PlndHrsTotal);

		pageDailyTradingStatementStorePO.CompareValueDecimalTotal(extentTest, DBPlndHrWk, PlndHrWk);
		pageMonthlyPlanningPO.CompareValueDecimalFields(extentTest, DBPlndHrWkTtl, PlndHrWkTtl);

		// Planned Variance
		Report_AddStep("testcase", " PLANNED VARIANCE - PLANNING FOR THE WEEK", "", "", "");
		extentTest = report.startTest("Planned Variance");
		
		String PlanVariance = data.getText("PlanVariance");
		DBPlndVar = sql.CLRexecuteQuery(getContext(),
				PlanVariance.replace("store", getContext().getStringProperty("store"))
						.replace("Week", getContext().getStringProperty("SelectFiscalWeek"))
						.replace("Department", getContext().getStringProperty("Longlife"))
						.replace("Division", getContext().getStringProperty("Division")));
		System.out.println("DB Value: " + DBPlndVar);

		String PlanVarianceTotal = data.getText("PlanVarianceTotal");
		DBPlndVarTtl = sql.CLRexecuteQuery(getContext(),
				PlanVarianceTotal.replace("store", getContext().getStringProperty("store"))
						.replace("Week", getContext().getStringProperty("SelectFiscalWeek"))
						.replace("Department", getContext().getStringProperty("Longlife"))
						.replace("Division", getContext().getStringProperty("Division")));
		System.out.println("DB Total: " + DBPlndVarTtl);

		PlndVar = pageWeeklyDepartmentPlanningLLPO.UIResults(objWeeklyDepartmentPlanningObjects, "Planned Variance",
				objWeeklyDepartmentPlanningLLObjects.PlndVarMonVal, objWeeklyDepartmentPlanningLLObjects.PlndVarTueVal,
				objWeeklyDepartmentPlanningLLObjects.PlndVarWedVal, objWeeklyDepartmentPlanningLLObjects.PlndVarThuVal,
				objWeeklyDepartmentPlanningLLObjects.PlndVarFriVal, objWeeklyDepartmentPlanningLLObjects.PlndVarSatVal,
				objWeeklyDepartmentPlanningLLObjects.PlndVarSunVal,
				objWeeklyDepartmentPlanningLLObjects.PlndVarCaption);
		PlndVarTtl = pageMonthlyPlanningPO.GetTotal(extentTest, objWeeklyDepartmentPlanningLLObjects.PlndVarCaption,
				objWeeklyDepartmentPlanningLLObjects.PlndVarTotal);

		pageDailyTradingStatementStorePO.CompareValueDecimalTotal(extentTest, DBPlndVar, PlndVar);
		pageMonthlyPlanningPO.CompareValueDecimalFields(extentTest, DBPlndVarTtl, PlndVarTtl);

		// Planned OR
		Report_AddStep("testcase", " PLANNED OR - PLANNING FOR THE WEEK", "", "", "");
		extentTest = report.startTest("Planned OR");
		
		String OperationalRatio = data.getText("OperationalRatio");
		DBORWk = sql.CLRexecuteQuery(getContext(),
				OperationalRatio.replace("store", getContext().getStringProperty("store"))
						.replace("Week", getContext().getStringProperty("SelectFiscalWeek"))
						.replace("Department", getContext().getStringProperty("Longlife"))
						.replace("Division", getContext().getStringProperty("Division")));
		System.out.println("DB Value: " + DBORWk);

		String OperationalRatioTotal = data.getText("OperationalRatioTotal");
		DBORWkTtl = sql.CLRexecuteQuery(getContext(),
				OperationalRatioTotal.replace("store", getContext().getStringProperty("store"))
						.replace("Week", getContext().getStringProperty("SelectFiscalWeek"))
						.replace("Department", getContext().getStringProperty("Longlife"))
						.replace("Division", getContext().getStringProperty("Division")));
		System.out.println("DB Total: " + DBORWkTtl);

		ORWk = pageWeeklyDepartmentPlanningLLPO.UIResults(objWeeklyDepartmentPlanningObjects, "Operational Ratio",
				objWeeklyDepartmentPlanningLLObjects.PlndORMonVal, objWeeklyDepartmentPlanningLLObjects.PlndORTueVal,
				objWeeklyDepartmentPlanningLLObjects.PlndORWedVal, objWeeklyDepartmentPlanningLLObjects.PlndORThuVal,
				objWeeklyDepartmentPlanningLLObjects.PlndORFriVal, objWeeklyDepartmentPlanningLLObjects.PlndORSatVal,
				objWeeklyDepartmentPlanningLLObjects.PlndORSunVal, objWeeklyDepartmentPlanningLLObjects.PlndORCaption);
		ORWkTtl = pageMonthlyPlanningPO.GetTotal(extentTest, objWeeklyDepartmentPlanningLLObjects.PlndORCaption,
				objWeeklyDepartmentPlanningLLObjects.PlndORTotal);

		pageDailyTradingStatementStorePO.CompareValueDecimalTotal(extentTest, DBORWk, ORWk);
		pageMonthlyPlanningPO.CompareValueDecimalFields(extentTest, DBORWkTtl, ORWkTtl);

		// Wage Cost
		Report_AddStep("testcase", "WAGE COST - PLANNING FOR THE WEEK", "", "", "");
		extentTest = report.startTest("Wage Cost");
		
		String WageCost = data.getText("WageCost");
		DBWgCost = sql.CLRexecuteQuery(getContext(),
				WageCost.replace("store", getContext().getStringProperty("store"))
						.replace("Week", getContext().getStringProperty("SelectFiscalWeek"))
						.replace("Department", getContext().getStringProperty("Longlife"))
						.replace("Division", getContext().getStringProperty("Division")));
		System.out.println("DB Value: " + DBWgCost);

		String WageCostTotal = data.getText("WageCostTotal");
		DBWgCostTtl = sql.CLRexecuteQuery(getContext(),
				WageCostTotal.replace("store", getContext().getStringProperty("store"))
						.replace("Week", getContext().getStringProperty("SelectFiscalWeek"))
						.replace("Department", getContext().getStringProperty("Longlife"))
						.replace("Division", getContext().getStringProperty("Division")));
		System.out.println("DB Total: " + DBWgCostTtl);

		WgCost = pageWeeklyDepartmentPlanningLLPO.UIResults(objWeeklyDepartmentPlanningObjects, "Wage Cost",
				objWeeklyDepartmentPlanningLLObjects.WgCostMonVal, objWeeklyDepartmentPlanningLLObjects.WgCostTueVal,
				objWeeklyDepartmentPlanningLLObjects.WgCostWedVal, objWeeklyDepartmentPlanningLLObjects.WgCostThuVal,
				objWeeklyDepartmentPlanningLLObjects.WgCostFriVal, objWeeklyDepartmentPlanningLLObjects.WgCostSatVal,
				objWeeklyDepartmentPlanningLLObjects.WgCostSunVal, objWeeklyDepartmentPlanningLLObjects.WgCostCaption);
		WgCostTtl = pageMonthlyPlanningPO.GetTotal(extentTest, objWeeklyDepartmentPlanningLLObjects.WgCostCaption,
				objWeeklyDepartmentPlanningLLObjects.WgCostTotal);

		pageDailyTradingStatementStorePO.DTSCompareValuesTotal(extentTest, DBWgCost, WgCost);
		pageMonthlyPlanningPO.CompareValueDecimalFields(extentTest, DBWgCostTtl, WgCostTtl);
	}

	///////////////////// REPL/MERCH/INV - PLANNING FOR THE WEEK
	///////////////////// /////////////////////////
	@Test(description = "Weekly Department Planning - Longlife, REPL/MERCH/INV DEPARTMENT VIEW", priority = 2)
	public void DepartmentWiseSplit_LonglifeScreen() throws Exception {
		pageWeeklyDepartmentPlanningLLPO = new WeeklyDepartmentPlanning_LongLife().createPage(getDriver(), getContext(),
				log, status, data, getReport(), getReportLogger(), WeeklyDepartmentPlanningPage.class);
		pageMonthlyPlanningGroupPO = new MonthlyPlanningGroupPage().createPage(getDriver(), getContext(), log, status,
				data, getReport(), getReportLogger(), MonthlyPlanningGroupPage.class);
		pageMonthlyPlanningPO = new WeeklyDepartmentPlanning_LongLife().createPage(getDriver(), getContext(), log,
				status, data, getReport(), getReportLogger(), MonthlyPlanningStorePage.class);
		TestStatus status = getTestStatus();
		System.out.println("Entering into TestCase : Weekly Department Planning - Longlife , REPL/MERCH/INV");
		String WeekID = getContext().getStringProperty("SelectFiscalWeek");
		OptionToView = getContext().getStringProperty("OptionToView");
		String LYWeekID = getContext().getStringProperty("LYWk");
		int LYWk1 = Integer.parseInt(LYWeekID) - 1;
		int LYWk4 = Integer.parseInt(LYWeekID) - 4;
		int Wk1 = Integer.parseInt(WeekID) - 1;
		int Wk2 = Integer.parseInt(WeekID) - 2;
		int Wk3 = Integer.parseInt(WeekID) - 3;
		int Wk4 = Integer.parseInt(WeekID) - 4;

		if (getContext().getStringProperty("DepartmentToView").contains("5"))
			pageWeeklyDepartmentPlanningLLPO.NavigationButton(objWeeklyDepartmentPlanningLLObjects.DataFrame,
					objWeeklyDepartmentPlanningLLObjects.ReplBtn, objWeeklyDepartmentPlanningLLObjects.Title,
					"GR/Replenishment", extentTest);
		if (getContext().getStringProperty("DepartmentToView").contains("10"))
			pageWeeklyDepartmentPlanningLLPO.NavigationButton(objWeeklyDepartmentPlanningLLObjects.DataFrame,
					objWeeklyDepartmentPlanningLLObjects.InvBtn, objWeeklyDepartmentPlanningLLObjects.Title,
					"PER/Inventory", extentTest);
		if (getContext().getStringProperty("DepartmentToView").contains("15"))
			pageWeeklyDepartmentPlanningLLPO.NavigationButton(objWeeklyDepartmentPlanningLLObjects.DataFrame,
					objWeeklyDepartmentPlanningLLObjects.MerchBtn, objWeeklyDepartmentPlanningLLObjects.Title,
					"GM/Merchandising", extentTest);

		//////////////////////////////////////////////////////// SUMMARY INFO TABLE 2
		//////////////////////////////////////////////////////// ////////////////////////////////////////////////////////////
		//Last Year Sales
		
				Report_AddStep("testcase", "Last Year Sales - SUMMARY INFO FOR SALES - GR/REPLENISHMENT", "", "", "");
				extentTest = report.startTest("Last Year Sales");
				
				String LastYearSalesSummary = data.getText("LastYearSalesSummary");
				DBLYSalesSummary2 = sql.CLRexecuteQuery(getContext(),
						LastYearSalesSummary.replace("store", getContext().getStringProperty("store"))
								.replace("Week", getContext().getStringProperty("SelectFiscalWeek")).replace("LYWk", getContext().getStringProperty("LYWk"))
								.replace("Department", getContext().getStringProperty("DepartmentToView"))
								.replace("Division", getContext().getStringProperty("Division")));
				System.out.println("DB Value: " + DBLYSalesSummary2);

				LastyearSlsSummary2 = pageWeeklyDepartmentPlanningLLPO.UIResultsSummaryInfo(
						objWeeklyDepartmentPlanningLLObjects.LastyearBanner, extentTest,
						objWeeklyDepartmentPlanningLLObjects.LastyearValue,
						objWeeklyDepartmentPlanningLLObjects.SummaryInfoTable2);
				pageMonthlyPlanningPO.CompareValues(extentTest, DBLYSalesSummary2, LastyearSlsSummary2);
		
		// Planned Sales
		Report_AddStep("testcase", "PLANNED SALES - SUMMARY INFO FOR SALES - GR/REPLENISHMENT", "", "", "");
		extentTest = report.startTest("Planned Sales");
		
		String PlannedSalesSummary = data.getText("PlannedSalesSummary");
		DBPlndSlsSummary2 = sql.CLRexecuteQuery(getContext(),
				PlannedSalesSummary.replace("store", getContext().getStringProperty("store"))
						.replace("Week", getContext().getStringProperty("SelectFiscalWeek"))
						.replace("Department", getContext().getStringProperty("DepartmentToView"))
						.replace("Division", getContext().getStringProperty("Division")));
		System.out.println("DB Value: " + DBPlndSlsSummary2);

		PlndSlsSummary2 = pageWeeklyDepartmentPlanningLLPO.UIResultsSummaryInfo(
				objWeeklyDepartmentPlanningLLObjects.PlannedBanner, extentTest,
				objWeeklyDepartmentPlanningLLObjects.PlannedValue,
				objWeeklyDepartmentPlanningLLObjects.SummaryInfoTable2);
		pageMonthlyPlanningPO.CompareValues(extentTest, DBPlndSlsSummary2, PlndSlsSummary2);

		if (OptionToView.contains("Budget")) {
			Report_AddStep("testcase", "BUDGET SALES - SUMMARY INFO FOR SALES - GR/REPLENISHMENT", "", "", "");
			extentTest = report.startTest("Budget Sales");
			
			String BudgetSalesSummary = data.getText("BudgetSalesSummary");
			DBBdgtSlsSummary2 = sql.CLRexecuteQuery(getContext(),
					BudgetSalesSummary.replace("store", getContext().getStringProperty("store"))
							.replace("Week", getContext().getStringProperty("SelectFiscalWeek"))
							.replace("Department", getContext().getStringProperty("DepartmentToView"))
							.replace("Division", getContext().getStringProperty("Division")));
			System.out.println("DB Value: " + DBBdgtSlsSummary2);

			BdgtSlsSummary2 = pageWeeklyDepartmentPlanningLLPO.UIResultsSummaryInfo(
					objWeeklyDepartmentPlanningLLObjects.BudgetBanner, extentTest,
					objWeeklyDepartmentPlanningLLObjects.BudgetValue,
					objWeeklyDepartmentPlanningLLObjects.SummaryInfoTable2);
			pageMonthlyPlanningPO.CompareValues(extentTest, DBBdgtSlsSummary2, BdgtSlsSummary2);
		}
		if (OptionToView.contains("Forecast")) {
			Report_AddStep("testcase", "FORECAST SALES - SUMMARY INFO FOR SALES - GR/REPLENISHMENT", "", "", "");
			extentTest = report.startTest("Forecast Sales");
			
			String ForecastSalesSummary = data.getText("ForecastSalesSummary");
			DBBdgtSlsSummary2 = sql.CLRexecuteQuery(getContext(),
					ForecastSalesSummary.replace("store", getContext().getStringProperty("store"))
							.replace("Week", getContext().getStringProperty("SelectFiscalWeek"))
							.replace("Department", getContext().getStringProperty("DepartmentToView"))
							.replace("Division", getContext().getStringProperty("Division")));
			System.out.println("DB Value: " + DBBdgtSlsSummary2);

			BdgtSlsSummary2 = pageWeeklyDepartmentPlanningLLPO.UIResultsSummaryInfo(
					objWeeklyDepartmentPlanningLLObjects.BudgetBanner, extentTest,
					objWeeklyDepartmentPlanningLLObjects.BudgetValue,
					objWeeklyDepartmentPlanningLLObjects.SummaryInfoTable2);
			pageMonthlyPlanningPO.CompareValues(extentTest, DBBdgtSlsSummary2, BdgtSlsSummary2);
		}
    
		//////////////////////////////////////////////////////// SALES HISTORY
		//////////////////////////////////////////////////////// ////////////////////////////////////////////////////////////
		// CPH - Week 1
		Report_AddStep("testcase", " CPH VALIDATION - FIRST WEEK", "", "", "");
		extentTest = report.startTest("CPH - First Week");
		
		String CPH1 = data.getText("CPH");
		DBCPHFirstWk = sql.CLRexecuteQuery(getContext(),
				CPH1.replace("store", getContext().getStringProperty("store")).replace("Week", String.valueOf(Wk4))
						.replace("Department", getContext().getStringProperty("DepartmentToView"))
						.replace("Division", getContext().getStringProperty("Division")));
		System.out.println("DB Value: " + DBCPHFirstWk);

		CPHFirstWk = pageMonthlyPlanningPO.GetTotal(extentTest, objWeeklyDepartmentPlanningLLObjects.FirstWeekCaption,
				objWeeklyDepartmentPlanningLLObjects.CPHFirstWk);

		pageMonthlyPlanningPO.CompareValueDecimalFields(extentTest, DBCPHFirstWk, CPHFirstWk);

		// CPH - Week 2
		Report_AddStep("testcase", " CPH VALIDATION - SECOND WEEK", "", "", "");
		extentTest = report.startTest("CPH - Second Week");
		
		String CPH2 = data.getText("CPH");
		DBCPHSecondWk = sql.CLRexecuteQuery(getContext(),
				CPH2.replace("store", getContext().getStringProperty("store")).replace("Week", String.valueOf(Wk3))
						.replace("Department", getContext().getStringProperty("DepartmentToView"))
						.replace("Division", getContext().getStringProperty("Division")));
		System.out.println("DB Value: " + DBCPHSecondWk);

		CPHSecondWk = pageMonthlyPlanningPO.GetTotal(extentTest, objWeeklyDepartmentPlanningLLObjects.SecondWeekCaption,
				objWeeklyDepartmentPlanningLLObjects.CPHSecondWk);

		pageMonthlyPlanningPO.CompareValueDecimalFields(extentTest, DBCPHSecondWk, CPHSecondWk);

		// CPH - Week 3
		Report_AddStep("testcase", " CPH VALIDATION - THIRD WEEK", "", "", "");
		extentTest = report.startTest("CPH - Third Week");
		
		String CPH3 = data.getText("CPH");
		DBCPHThirdWk = sql.CLRexecuteQuery(getContext(),
				CPH3.replace("store", getContext().getStringProperty("store")).replace("Week", String.valueOf(Wk2))
						.replace("Department", getContext().getStringProperty("DepartmentToView"))
						.replace("Division", getContext().getStringProperty("Division")));
		System.out.println("DB Value: " + DBCPHThirdWk);

		CPHThirdWk = pageMonthlyPlanningPO.GetTotal(extentTest, objWeeklyDepartmentPlanningLLObjects.ThirdWeekCaption,
				objWeeklyDepartmentPlanningLLObjects.CPHThirdWk);

		pageMonthlyPlanningPO.CompareValueDecimalFields(extentTest, DBCPHThirdWk, CPHThirdWk);

		// CPH - Week 4
		Report_AddStep("testcase", " CPH VALIDATION - FOURTH WEEK", "", "", "");
		extentTest = report.startTest("CPH - Fourth Week");
		
		String CPH4 = data.getText("CPH");
		DBCPHFourthWk = sql.CLRexecuteQuery(getContext(),
				CPH4.replace("store", getContext().getStringProperty("store")).replace("Week", String.valueOf(Wk1))
						.replace("Department", getContext().getStringProperty("DepartmentToView"))
						.replace("Division", getContext().getStringProperty("Division")));
		System.out.println("DB Value: " + DBCPHFourthWk);

		CPHFourthWk = pageMonthlyPlanningPO.GetTotal(extentTest, objWeeklyDepartmentPlanningLLObjects.FourthWeekCaption,
				objWeeklyDepartmentPlanningLLObjects.CPHFourthWk);

		pageMonthlyPlanningPO.CompareValueDecimalFields(extentTest, DBCPHFourthWk, CPHFourthWk);

		// Sales history - Week -1
		Report_AddStep("testcase", " SALES HISTORY - FIRST WEEK", "", "", "");
		extentTest = report.startTest("Sales History - First Week");
		
		String SalesHistory1 = data.getText("SalesHistory");
		DBFirstWk = sql.CLRexecuteQuery(getContext(),
				SalesHistory1.replace("store", getContext().getStringProperty("store"))
						.replace("Week", String.valueOf(Wk4))
						.replace("Department", getContext().getStringProperty("DepartmentToView"))
						.replace("Division", getContext().getStringProperty("Division")));
		System.out.println("DB Value: " + DBFirstWk);

		String SalesHistoryTotal1 = data.getText("SalesHistoryTotal");
		DBFirstWkTtl = sql.CLRexecuteQuery(getContext(),
				SalesHistoryTotal1.replace("store", getContext().getStringProperty("store"))
						.replace("Week", String.valueOf(Wk4))
						.replace("Department", getContext().getStringProperty("DepartmentToView"))
						.replace("Division", getContext().getStringProperty("Division")));
		System.out.println("DB Total: " + DBFirstWkTtl);

		FirstWk = pageWeeklyDepartmentPlanningLLPO.UIResults(objWeeklyDepartmentPlanningObjects, "Sales history",
				objWeeklyDepartmentPlanningLLObjects.FirstWeekMonval,
				objWeeklyDepartmentPlanningLLObjects.FirstWeekTueval,
				objWeeklyDepartmentPlanningLLObjects.FirstWeekWedval,
				objWeeklyDepartmentPlanningLLObjects.FirstWeekThuval,
				objWeeklyDepartmentPlanningLLObjects.FirstWeekFrival,
				objWeeklyDepartmentPlanningLLObjects.FirstWeekSatval,
				objWeeklyDepartmentPlanningLLObjects.FirstWeekSunval,
				objWeeklyDepartmentPlanningLLObjects.FirstWeekCaption);
		FirstWkTtl = pageMonthlyPlanningPO.GetTotal(extentTest, objWeeklyDepartmentPlanningLLObjects.FirstWeekCaption,
				objWeeklyDepartmentPlanningLLObjects.FirstWeekValTotal);

		pageDailyTradingStatementStorePO.DTSCompareValuesTotal(extentTest, DBFirstWk, FirstWk);
		pageMonthlyPlanningPO.CompareValues(extentTest, DBFirstWkTtl, FirstWkTtl);

		// Sales history - Week -2
		Report_AddStep("testcase", " SALES HISTORY - SECOND WEEK", "", "", "");
		extentTest = report.startTest("Sales History - Second Week");
		
		String SalesHistory2 = data.getText("SalesHistory");
		DBSecondWk = sql.CLRexecuteQuery(getContext(),
				SalesHistory2.replace("store", getContext().getStringProperty("store"))
						.replace("Week", String.valueOf(Wk3))
						.replace("Department", getContext().getStringProperty("DepartmentToView"))
						.replace("Division", getContext().getStringProperty("Division")));
		System.out.println("DB Value: " + DBSecondWk);

		String SalesHistoryTotal2 = data.getText("SalesHistoryTotal");
		DBSecondWkTtl = sql.CLRexecuteQuery(getContext(),
				SalesHistoryTotal2.replace("store", getContext().getStringProperty("store"))
						.replace("Week", String.valueOf(Wk3))
						.replace("Department", getContext().getStringProperty("DepartmentToView"))
						.replace("Division", getContext().getStringProperty("Division")));
		System.out.println("DB Total: " + DBSecondWkTtl);

		SecondWk = pageWeeklyDepartmentPlanningLLPO.UIResults(objWeeklyDepartmentPlanningObjects, "Sales history",
				objWeeklyDepartmentPlanningLLObjects.SecondWeekMonval,
				objWeeklyDepartmentPlanningLLObjects.SecondWeekTueval,
				objWeeklyDepartmentPlanningLLObjects.SecondWeekWedval,
				objWeeklyDepartmentPlanningLLObjects.SecondWeekThuval,
				objWeeklyDepartmentPlanningLLObjects.SecondWeekFrival,
				objWeeklyDepartmentPlanningLLObjects.SecondWeekSatval,
				objWeeklyDepartmentPlanningLLObjects.SecondWeekSunval,
				objWeeklyDepartmentPlanningLLObjects.SecondWeekCaption);
		SecondWkTtl = pageMonthlyPlanningPO.GetTotal(extentTest, objWeeklyDepartmentPlanningLLObjects.SecondWeekCaption,
				objWeeklyDepartmentPlanningLLObjects.SecondWeekValTotal);

		pageDailyTradingStatementStorePO.DTSCompareValuesTotal(extentTest, DBSecondWk, SecondWk);
		pageMonthlyPlanningPO.CompareValues(extentTest, DBSecondWkTtl, SecondWkTtl);

		// Sales history - Week -3
		Report_AddStep("testcase", " SALES HISTORY - THIRD WEEK", "", "", "");
		extentTest = report.startTest("Sales History - Third Week");
		
		String SalesHistory3 = data.getText("SalesHistory");
		DBThirdWk = sql.CLRexecuteQuery(getContext(),
				SalesHistory3.replace("store", getContext().getStringProperty("store"))
						.replace("Week", String.valueOf(Wk2))
						.replace("Department", getContext().getStringProperty("DepartmentToView"))
						.replace("Division", getContext().getStringProperty("Division")));
		System.out.println("DB Value: " + DBThirdWk);

		String SalesHistoryTotal3 = data.getText("SalesHistoryTotal");
		DBThirdWkTtl = sql.CLRexecuteQuery(getContext(),
				SalesHistoryTotal3.replace("store", getContext().getStringProperty("store"))
						.replace("Week", String.valueOf(Wk2))
						.replace("Department", getContext().getStringProperty("DepartmentToView"))
						.replace("Division", getContext().getStringProperty("Division")));
		System.out.println("DB Total: " + DBThirdWkTtl);

		ThirdWk = pageWeeklyDepartmentPlanningLLPO.UIResults(objWeeklyDepartmentPlanningObjects, "Sales history",
				objWeeklyDepartmentPlanningLLObjects.ThirdWeekMonval,
				objWeeklyDepartmentPlanningLLObjects.ThirdWeekTueval,
				objWeeklyDepartmentPlanningLLObjects.ThirdWeekWedval,
				objWeeklyDepartmentPlanningLLObjects.ThirdWeekThuval,
				objWeeklyDepartmentPlanningLLObjects.ThirdWeekFrival,
				objWeeklyDepartmentPlanningLLObjects.ThirdWeekSatval,
				objWeeklyDepartmentPlanningLLObjects.ThirdWeekSunval,
				objWeeklyDepartmentPlanningLLObjects.ThirdWeekCaption);
		ThirdWkTtl = pageMonthlyPlanningPO.GetTotal(extentTest, objWeeklyDepartmentPlanningLLObjects.ThirdWeekCaption,
				objWeeklyDepartmentPlanningLLObjects.ThirdWeekValTotal);

		pageDailyTradingStatementStorePO.DTSCompareValuesTotal(extentTest, DBThirdWk, ThirdWk);
		pageMonthlyPlanningPO.CompareValues(extentTest, DBThirdWkTtl, ThirdWkTtl);

		// Sales history - Week -4
		Report_AddStep("testcase", " SALES HISTORY - FOURTH WEEK", "", "", "");
		extentTest = report.startTest("Sales History - Fourth Week");
		
		String SalesHistory4 = data.getText("SalesHistory");
		DBFourthWk = sql.CLRexecuteQuery(getContext(),
				SalesHistory3.replace("store", getContext().getStringProperty("store"))
						.replace("Week", String.valueOf(Wk1))
						.replace("Department", getContext().getStringProperty("DepartmentToView"))
						.replace("Division", getContext().getStringProperty("Division")));
		System.out.println("DB Value: " + DBFourthWk);

		String SalesHistoryTotal4 = data.getText("SalesHistoryTotal");
		DBFourthWkTtl = sql.CLRexecuteQuery(getContext(),
				SalesHistoryTotal3.replace("store", getContext().getStringProperty("store"))
						.replace("Week", String.valueOf(Wk1))
						.replace("Department", getContext().getStringProperty("DepartmentToView"))
						.replace("Division", getContext().getStringProperty("Division")));
		System.out.println("DB Total: " + DBFourthWkTtl);

		FourthWk = pageWeeklyDepartmentPlanningLLPO.UIResults(objWeeklyDepartmentPlanningObjects, "Sales history",
				objWeeklyDepartmentPlanningLLObjects.FourthWeekMonval,
				objWeeklyDepartmentPlanningLLObjects.FourthWeekTueval,
				objWeeklyDepartmentPlanningLLObjects.FourthWeekWedval,
				objWeeklyDepartmentPlanningLLObjects.FourthWeekThuval,
				objWeeklyDepartmentPlanningLLObjects.FourthWeekFrival,
				objWeeklyDepartmentPlanningLLObjects.FourthWeekSatval,
				objWeeklyDepartmentPlanningLLObjects.FourthWeekSunval,
				objWeeklyDepartmentPlanningLLObjects.FourthWeekCaption);
		FourthWkTtl = pageMonthlyPlanningPO.GetTotal(extentTest, objWeeklyDepartmentPlanningLLObjects.FourthWeekCaption,
				objWeeklyDepartmentPlanningLLObjects.FourthWeekValTotal);

		pageDailyTradingStatementStorePO.DTSCompareValuesTotal(extentTest, DBFourthWk, FourthWk);
		pageMonthlyPlanningPO.CompareValues(extentTest, DBFourthWkTtl, FourthWkTtl);

		// Average Daily Mix
		Report_AddStep("testcase", " AVERAG MIX", "", "", "");
		extentTest = report.startTest("Average Mix");
		
		String AverageMix = data.getText("AverageMix");
		DBAvgMix = sql.CLRexecuteQuery(getContext(),
				AverageMix.replace("store", getContext().getStringProperty("store"))
						.replace("Week", getContext().getStringProperty("SelectFiscalWeek"))
						.replace("Department", getContext().getStringProperty("DepartmentToView"))
						.replace("Division", getContext().getStringProperty("Division")));
		System.out.println("DB Value: " + DBAvgMix);

		AvgMix = pageWeeklyDepartmentPlanningLLPO.UIResults(objWeeklyDepartmentPlanningObjects, "Sales history",
				objWeeklyDepartmentPlanningLLObjects.AvgMixMonval, objWeeklyDepartmentPlanningLLObjects.AvgMixTueval,
				objWeeklyDepartmentPlanningLLObjects.AvgMixWedval, objWeeklyDepartmentPlanningLLObjects.AvgMixThuval,
				objWeeklyDepartmentPlanningLLObjects.AvgMixFrival, objWeeklyDepartmentPlanningLLObjects.AvgMixSatval,
				objWeeklyDepartmentPlanningLLObjects.AvgMixSunval,
				objWeeklyDepartmentPlanningLLObjects.AvgDailyMixCaption);

		pageDailyTradingStatementStorePO.DTSCompareValuesTotal(extentTest, DBAvgMix, AvgMix);
		
	
		//Average Growth Vs LY%
		Report_AddStep("testcase", " Average Growth Vs LY Per", "", "", "");
		extentTest = report.startTest("Average Growth Vs LY Per");

		String AverageGrowthVsLYPer = data.getText("AverageGrowthVsLYPer");
		DBAvgGrowth = sql.CLRexecuteQuery(getContext(),
				AverageGrowthVsLYPer.replace("store", getContext().getStringProperty("store"))
						.replace("Week", getContext().getStringProperty("SelectFiscalWeek")).replace("LYWk", getContext().getStringProperty("LYWk"))
						.replace("Department", getContext().getStringProperty("DepartmentToView"))
						.replace("Division", getContext().getStringProperty("Division"))
						.replace("WEEK1", String.valueOf(Wk4)).replace("WEEK4", String.valueOf(Wk1)).replace("LYWK1", String.valueOf(LYWk4)).replace("LYWK4", String.valueOf(LYWk1)));
		System.out.println("DB Value: " + DBAvgGrowth);

		Avggrowth = pageWeeklyDepartmentPlanningLLPO.UIResults(objWeeklyDepartmentPlanningObjects, "Sales history",
				objWeeklyDepartmentPlanningLLObjects.AvgGrowthMonval, objWeeklyDepartmentPlanningLLObjects.AvgGrowthTueval,
				objWeeklyDepartmentPlanningLLObjects.AvgGrowthWedval, objWeeklyDepartmentPlanningLLObjects.AvgGrowthThuval,
				objWeeklyDepartmentPlanningLLObjects.AvgGrowthFrival, objWeeklyDepartmentPlanningLLObjects.AvgGrowthSatval,
				objWeeklyDepartmentPlanningLLObjects.AvgGrowthSunval, objWeeklyDepartmentPlanningLLObjects.AvgGrowthCaption);

		pageDailyTradingStatementStorePO.DTSCompareValuesTotal(extentTest, DBAvgGrowth, Avggrowth);
		

		//////////////////////////////////////////////////////// PLANNING FOR THE WEEK
		//////////////////////////////////////////////////////// ////////////////////////////////////////////////////////////
		
		//Last Year sales 
		
		Report_AddStep("testcase", " LAST YEAR SALES - PLANNING FOR THE WEEK", "", "", "");
		extentTest = report.startTest("Last Year Sales");

		String LastYearSales = data.getText("LastYearSalesind");
		DBLastYearSls = sql.CLRexecuteQuery(getContext(),
				LastYearSales.replace("store", getContext().getStringProperty("store")).replace("LYWk", getContext().getStringProperty("LYWk"))
						.replace("Week", getContext().getStringProperty("SelectFiscalWeek"))
						.replace("Department", getContext().getStringProperty("DepartmentToView"))
						.replace("Division", getContext().getStringProperty("Division")));
		System.out.println("DB Value: " + DBLastYearSls);

		String LastYearSalesTotal = data.getText("LastYearSalesTotal");
		DBLastYearSlsTtl = sql.CLRexecuteQuery(getContext(),
				LastYearSalesTotal.replace("store", getContext().getStringProperty("store")).replace("LYWk", getContext().getStringProperty("LYWk"))
						.replace("Week", getContext().getStringProperty("SelectFiscalWeek"))
						.replace("Department", getContext().getStringProperty("DepartmentToView"))
						.replace("Division", getContext().getStringProperty("Division")));
		System.out.println("DB Total: " + DBLastYearSlsTtl);

		LastyearSls = pageWeeklyDepartmentPlanningLLPO.UIResults(objWeeklyDepartmentPlanningObjects, "Last Year Sales",
				objWeeklyDepartmentPlanningLLObjects.LastYearSalesMonVal,
				objWeeklyDepartmentPlanningLLObjects.LastYearSalesTueVal,
				objWeeklyDepartmentPlanningLLObjects.LastYearSalesWedVal,
				objWeeklyDepartmentPlanningLLObjects.LastYearSalesThuVal,
				objWeeklyDepartmentPlanningLLObjects.LastYearSalesFriVal,
				objWeeklyDepartmentPlanningLLObjects.LastYearSalesSatVal,
				objWeeklyDepartmentPlanningLLObjects.LastYearSalesSunVal,
				objWeeklyDepartmentPlanningLLObjects.LastYearSalesCaption);
		LastyearSlsTtl = pageMonthlyPlanningPO.GetTotal(extentTest,
				objWeeklyDepartmentPlanningLLObjects.LastYearSalesCaption,
				objWeeklyDepartmentPlanningLLObjects.LastYearSalesTotal);

		pageDailyTradingStatementStorePO.DTSCompareValuesTotal(extentTest, DBLastYearSls, LastyearSls);
		pageMonthlyPlanningPO.CompareValues(extentTest, DBLastYearSlsTtl, LastyearSlsTtl);	
		
		// Generated Sales
		Report_AddStep("testcase", " GENERATED SALES - PLANNING FOR THE WEEK", "", "", "");
		extentTest = report.startTest("Generated Sales");
		
		String GeneratedSales = data.getText("GeneratedSales");
		DBGnrtSls = sql.CLRexecuteQuery(getContext(),
				GeneratedSales.replace("store", getContext().getStringProperty("store"))
						.replace("Week", getContext().getStringProperty("SelectFiscalWeek"))
						.replace("Department", getContext().getStringProperty("DepartmentToView"))
						.replace("Division", getContext().getStringProperty("Division")));
		System.out.println("DB Value: " + DBGnrtSls);

		String GeneratedSalesTotal = data.getText("GeneratedSalesTotal");
		DBGnrtSlsTtl = sql.CLRexecuteQuery(getContext(),
				GeneratedSalesTotal.replace("store", getContext().getStringProperty("store"))
						.replace("Week", getContext().getStringProperty("SelectFiscalWeek"))
						.replace("Department", getContext().getStringProperty("DepartmentToView"))
						.replace("Division", getContext().getStringProperty("Division")));
		System.out.println("DB Total: " + DBGnrtSlsTtl);

		GnrtSls = pageWeeklyDepartmentPlanningLLPO.UIResults(objWeeklyDepartmentPlanningObjects, "Generated Sales",
				objWeeklyDepartmentPlanningLLObjects.GeneratedSalesMonVal,
				objWeeklyDepartmentPlanningLLObjects.GeneratedSalesTueVal,
				objWeeklyDepartmentPlanningLLObjects.GeneratedSalesWedVal,
				objWeeklyDepartmentPlanningLLObjects.GeneratedSalesThuVal,
				objWeeklyDepartmentPlanningLLObjects.GeneratedSalesFriVal,
				objWeeklyDepartmentPlanningLLObjects.GeneratedSalesSatVal,
				objWeeklyDepartmentPlanningLLObjects.GeneratedSalesSunVal,
				objWeeklyDepartmentPlanningLLObjects.GeneratedSalesCaption);
		GnrtSlsTtl = pageMonthlyPlanningPO.GetTotal(extentTest,
				objWeeklyDepartmentPlanningLLObjects.GeneratedSalesCaption,
				objWeeklyDepartmentPlanningLLObjects.GeneratedSalesTotal);

		pageDailyTradingStatementStorePO.DTSCompareValuesTotal(extentTest, DBGnrtSls, GnrtSls);
		pageMonthlyPlanningPO.CompareValues(extentTest, DBGnrtSlsTtl, GnrtSlsTtl);

		// PlannedSales
		Report_AddStep("testcase", " PLANNED SALES - PLANNING FOR THE WEEK", "", "", "");
		extentTest = report.startTest("Planned Sales");
		
		String PlannedSales1 = data.getText("PlannedSales");
		DBPlndSlsWk = sql.CLRexecuteQuery(getContext(),
				PlannedSales1.replace("store", getContext().getStringProperty("store"))
						.replace("Week", getContext().getStringProperty("SelectFiscalWeek"))
						.replace("Department", getContext().getStringProperty("DepartmentToView"))
						.replace("Division", getContext().getStringProperty("Division")));
		System.out.println("DB Value: " + DBPlndSlsWk);

		String PlannedSalesTotal = data.getText("PlannedSalesTotal");
		DBPlndSlsWkTtl = sql.CLRexecuteQuery(getContext(),
				PlannedSalesTotal.replace("store", getContext().getStringProperty("store"))
						.replace("Week", getContext().getStringProperty("SelectFiscalWeek"))
						.replace("Department", getContext().getStringProperty("DepartmentToView"))
						.replace("Division", getContext().getStringProperty("Division")));
		System.out.println("DB Total: " + DBPlndSlsWkTtl);

		PlndSlsWk = pageWeeklyDepartmentPlanningLLPO.UIResults(objWeeklyDepartmentPlanningObjects, "Planned Sales",
				objWeeklyDepartmentPlanningLLObjects.PlannedSalesMonVal,
				objWeeklyDepartmentPlanningLLObjects.PlannedSalesTueVal,
				objWeeklyDepartmentPlanningLLObjects.PlannedSalesWedVal,
				objWeeklyDepartmentPlanningLLObjects.PlannedSalesThuVal,
				objWeeklyDepartmentPlanningLLObjects.PlannedSalesFriVal,
				objWeeklyDepartmentPlanningLLObjects.PlannedSalesSatVal,
				objWeeklyDepartmentPlanningLLObjects.PlannedSalesSunVal,
				objWeeklyDepartmentPlanningLLObjects.PlannedSalesCaption);
		PlndSlsWkTtl = pageWeeklyDepartmentPlanningLLPO.GetTotal(extentTest,
				objWeeklyDepartmentPlanningLLObjects.PlannedSalesCaption,
				objWeeklyDepartmentPlanningLLObjects.PlannedSalesTotal);

		pageDailyTradingStatementStorePO.DTSCompareValuesTotal(extentTest, DBPlndSlsWk, PlndSlsWk);
		pageMonthlyPlanningPO.CompareValues(extentTest, DBPlndSlsWkTtl, PlndSlsWkTtl);
		
		//Planned Sales VS LY%
		Report_AddStep("testcase", " Planned Sales VS LYPer - PLANNING FOR THE WEEK", "", "", "");
		extentTest = report.startTest("Planned Sales VS LYPer");

				String PlannedSalesVSLYPer = data.getText("PlannedSalesVSLYPer");
				DBPlannedSalesVSLYPer = sql.CLRexecuteQuery(getContext(),
						PlannedSalesVSLYPer.replace("store", getContext().getStringProperty("store")).replace("LYWk", getContext().getStringProperty("LYWk"))
								.replace("Week", getContext().getStringProperty("SelectFiscalWeek"))
								.replace("Department", getContext().getStringProperty("DepartmentToView"))
								.replace("Division", getContext().getStringProperty("Division")));
				System.out.println("DB Value: " + DBPlannedSalesVSLYPer);

				 
				PlannedSalesVSLY = pageWeeklyDepartmentPlanningLLPO.UIResults(objWeeklyDepartmentPlanningObjects, "Planned Sales VS LYPer",
						objWeeklyDepartmentPlanningLLObjects.PlannedSalesVSLYMonVal,
						objWeeklyDepartmentPlanningLLObjects.PlannedSalesVSLYTueVal,
						objWeeklyDepartmentPlanningLLObjects.PlannedSalesVSLYWedVal,
						objWeeklyDepartmentPlanningLLObjects.PlannedSalesVSLYThuVal,
						objWeeklyDepartmentPlanningLLObjects.PlannedSalesVSLYFriVal,
						objWeeklyDepartmentPlanningLLObjects.PlannedSalesVSLYSatVal,
						objWeeklyDepartmentPlanningLLObjects.PlannedSalesVSLYSunVal,
						objWeeklyDepartmentPlanningLLObjects.PlannedSalesVSLYCaption);
				
				pageDailyTradingStatementStorePO.DTSCompareValuesTotal(extentTest, DBPlannedSalesVSLYPer, PlannedSalesVSLY);
				  //PlannedSalesDailyMix%		
				
				Report_AddStep("testcase", " PlannedSalesDailyMix - PLANNING FOR THE WEEK", "", "", "");
				extentTest = report.startTest("PlannedSalesDailyMix");

						String PlannedSalesDailyMix = data.getText("PlannedSalesDailyMix");
						DBPlannedSalesDailyMix = sql.CLRexecuteQuery(getContext(),
								PlannedSalesDailyMix.replace("store", getContext().getStringProperty("store")).replace("LYWk", getContext().getStringProperty("LYWk"))
										.replace("Week", getContext().getStringProperty("SelectFiscalWeek"))
										.replace("Department", getContext().getStringProperty("DepartmentToView"))
										.replace("Division", getContext().getStringProperty("Division")));
						System.out.println("DB Value: " + DBPlannedSalesDailyMix);

						

						PlannedSalesDailyMixper = pageWeeklyDepartmentPlanningLLPO.UIResults(objWeeklyDepartmentPlanningObjects, "PlannedSalesdailymix",
								objWeeklyDepartmentPlanningLLObjects.PlannedSalesdailymixMonVal,
								objWeeklyDepartmentPlanningLLObjects.PlannedSalesdailymixTueVal,
								objWeeklyDepartmentPlanningLLObjects.PlannedSalesdailymixWedVal,
								objWeeklyDepartmentPlanningLLObjects.PlannedSalesdailymixThuVal,
								objWeeklyDepartmentPlanningLLObjects.PlannedSalesdailymixFriVal,
								objWeeklyDepartmentPlanningLLObjects.PlannedSalesdailymixSatVal,
								objWeeklyDepartmentPlanningLLObjects.PlannedSalesdailymixSunVal,
								objWeeklyDepartmentPlanningLLObjects.PlannedSalesVSLYCaption);
						
						pageDailyTradingStatementStorePO.DTSCompareValuesTotal(extentTest, DBPlannedSalesDailyMix, PlannedSalesDailyMixper);

		// Generated Item Price
		Report_AddStep("testcase", " GENERATED ITEM PRICE - PLANNING FOR THE WEEK", "", "", "");
		extentTest = report.startTest("Generated Item Price");
		
		String GeneratedItemPrice = data.getText("GeneratedItemPrice");
		DBGnrtItmPr = sql.CLRexecuteQuery(getContext(),
				GeneratedItemPrice.replace("store", getContext().getStringProperty("store"))
						.replace("Week", getContext().getStringProperty("SelectFiscalWeek"))
						.replace("Department", getContext().getStringProperty("DepartmentToView"))
						.replace("Division", getContext().getStringProperty("Division")));
		System.out.println("DB Value: " + DBGnrtItmPr);

		String GeneratedItemPriceTotal = data.getText("GeneratedItemPriceTotal");
		DBGnrtItmPrTtl = sql.CLRexecuteQuery(getContext(),
				GeneratedItemPriceTotal.replace("store", getContext().getStringProperty("store"))
						.replace("Week", getContext().getStringProperty("SelectFiscalWeek"))
						.replace("Department", getContext().getStringProperty("DepartmentToView"))
						.replace("Division", getContext().getStringProperty("Division")));
		System.out.println("DB Total: " + DBGnrtItmPrTtl);

		GnrtItmPr = pageWeeklyDepartmentPlanningLLPO.UIResults(objWeeklyDepartmentPlanningObjects,
				"Generated Item Price", objWeeklyDepartmentPlanningLLObjects.GnrtItmPriceMonVal,
				objWeeklyDepartmentPlanningLLObjects.GnrtItmPriceTueVal,
				objWeeklyDepartmentPlanningLLObjects.GnrtItmPriceWedVal,
				objWeeklyDepartmentPlanningLLObjects.GnrtItmPriceThuVal,
				objWeeklyDepartmentPlanningLLObjects.GnrtItmPriceFriVal,
				objWeeklyDepartmentPlanningLLObjects.GnrtItmPriceSatVal,
				objWeeklyDepartmentPlanningLLObjects.GnrtItmPriceSunVal,
				objWeeklyDepartmentPlanningLLObjects.GeneratedItemPriceCaption);
		GnrtItmPrTtl = pageMonthlyPlanningPO.GetTotal(extentTest,
				objWeeklyDepartmentPlanningLLObjects.GeneratedItemPriceCaption,
				objWeeklyDepartmentPlanningLLObjects.GeneratedItemPriceTotal);

		pageDailyTradingStatementStorePO.DTSCompareValuesTotal(extentTest, DBGnrtItmPr, GnrtItmPr);
		pageMonthlyPlanningPO.CompareValueDecimalFields(extentTest, DBGnrtItmPrTtl, GnrtItmPrTtl);

		// Planned Item Price
		Report_AddStep("testcase", " PLANNED ITEM PRICE - PLANNING FOR THE WEEK", "", "", "");
		extentTest = report.startTest("Planned Item Price");
		
		String PlannedItemPrice = data.getText("PlannedItemPrice");
		DBPlndItmPr = sql.CLRexecuteQuery(getContext(),
				PlannedItemPrice.replace("store", getContext().getStringProperty("store"))
						.replace("Week", getContext().getStringProperty("SelectFiscalWeek"))
						.replace("Department", getContext().getStringProperty("DepartmentToView"))
						.replace("Division", getContext().getStringProperty("Division")));
		System.out.println("DB Value: " + DBPlndItmPr);

		String PlannedItemPriceTotal = data.getText("PlannedItemPriceTotal");
		DBPlndItmPrTtl = sql.CLRexecuteQuery(getContext(),
				PlannedItemPriceTotal.replace("store", getContext().getStringProperty("store"))
						.replace("Week", getContext().getStringProperty("SelectFiscalWeek"))
						.replace("Department", getContext().getStringProperty("DepartmentToView"))
						.replace("Division", getContext().getStringProperty("Division")));
		System.out.println("DB Total: " + DBPlndItmPrTtl);

		PlndItmPr = pageWeeklyDepartmentPlanningLLPO.UIResults(objWeeklyDepartmentPlanningObjects, "Planned Item Price",
				objWeeklyDepartmentPlanningLLObjects.PlndItmPriceMonValue,
				objWeeklyDepartmentPlanningLLObjects.PlndItmPriceTueValue,
				objWeeklyDepartmentPlanningLLObjects.PlndItmPriceWedValue,
				objWeeklyDepartmentPlanningLLObjects.PlndItmPriceThuValue,
				objWeeklyDepartmentPlanningLLObjects.PlndItmPriceFriValue,
				objWeeklyDepartmentPlanningLLObjects.PlndItmPriceSatValue,
				objWeeklyDepartmentPlanningLLObjects.PlndItmPriceSunValue,
				objWeeklyDepartmentPlanningLLObjects.PlannedItemPriceCaption);
		PlndItmPrTtl = pageWeeklyDepartmentPlanningLLPO.GetTotal(extentTest,
				objWeeklyDepartmentPlanningLLObjects.PlannedItemPriceCaption,
				objWeeklyDepartmentPlanningLLObjects.PlannedItemPriceTotal);

		pageDailyTradingStatementStorePO.CompareValueDecimalTotal(extentTest, DBPlndItmPr, PlndItmPr);
		pageMonthlyPlanningPO.CompareValueDecimalFields(extentTest, DBPlndItmPrTtl, PlndItmPrTtl);

		// Generated Items
		Report_AddStep("testcase", " GENERATED ITEMS - PLANNING FOR THE WEEK", "", "", "");
		extentTest = report.startTest("Generated Items");
		
		String GeneratedItems = data.getText("GeneratedItems");
		DBGnrtItm = sql.CLRexecuteQuery(getContext(),
				GeneratedItems.replace("store", getContext().getStringProperty("store"))
						.replace("Week", getContext().getStringProperty("SelectFiscalWeek"))
						.replace("Department", getContext().getStringProperty("DepartmentToView"))
						.replace("Division", getContext().getStringProperty("Division")));
		System.out.println("DB Value: " + DBGnrtItm);

		String GeneratedItemsTotal = data.getText("GeneratedItemsTotal");
		DBGnrtItmTtl = sql.CLRexecuteQuery(getContext(),
				GeneratedItemsTotal.replace("store", getContext().getStringProperty("store"))
						.replace("Week", getContext().getStringProperty("SelectFiscalWeek"))
						.replace("Department", getContext().getStringProperty("DepartmentToView"))
						.replace("Division", getContext().getStringProperty("Division")));
		System.out.println("DB Total: " + DBGnrtItmTtl);

		GnrtItm = pageWeeklyDepartmentPlanningLLPO.UIResults(objWeeklyDepartmentPlanningObjects, "Generated Items",
				objWeeklyDepartmentPlanningLLObjects.GnrtItmMonVal, objWeeklyDepartmentPlanningLLObjects.GnrtItmTueVal,
				objWeeklyDepartmentPlanningLLObjects.GnrtItmWedVal, objWeeklyDepartmentPlanningLLObjects.GnrtItmThuVal,
				objWeeklyDepartmentPlanningLLObjects.GnrtItmFriVal, objWeeklyDepartmentPlanningLLObjects.GnrtItmSatVal,
				objWeeklyDepartmentPlanningLLObjects.GnrtItmSunVal,
				objWeeklyDepartmentPlanningLLObjects.GeneratedItemCaption);
		GnrtItmTtl = pageMonthlyPlanningPO.GetTotal(extentTest,
				objWeeklyDepartmentPlanningLLObjects.GeneratedItemCaption,
				objWeeklyDepartmentPlanningLLObjects.GeneratedItemTotal);

		pageDailyTradingStatementStorePO.DTSCompareValuesTotal(extentTest, DBGnrtItm, GnrtItm);
		pageMonthlyPlanningPO.CompareValues(extentTest, DBGnrtItmTtl, GnrtItmTtl);

		// Planned Items
		Report_AddStep("testcase", " PLANNED ITEMS - PLANNING FOR THE WEEK", "", "", "");
		extentTest = report.startTest("Planned Items");
		
		String PlannedItems = data.getText("PlannedItems");
		DBPlndItm = sql.CLRexecuteQuery(getContext(),
				PlannedItems.replace("store", getContext().getStringProperty("store"))
						.replace("Week", getContext().getStringProperty("SelectFiscalWeek"))
						.replace("Department", getContext().getStringProperty("DepartmentToView"))
						.replace("Division", getContext().getStringProperty("Division")));
		System.out.println("DB Value: " + DBPlndItm);

		String PlannedItemsTotal = data.getText("PlannedItemsTotal");
		DBPlndItmTtl = sql.CLRexecuteQuery(getContext(),
				PlannedItemsTotal.replace("store", getContext().getStringProperty("store"))
						.replace("Week", getContext().getStringProperty("SelectFiscalWeek"))
						.replace("Department", getContext().getStringProperty("DepartmentToView"))
						.replace("Division", getContext().getStringProperty("Division")));
		System.out.println("DB Total: " + DBPlndItmTtl);

		PlndItm = pageWeeklyDepartmentPlanningLLPO.UIResults(objWeeklyDepartmentPlanningObjects, "Planned Items",
				objWeeklyDepartmentPlanningLLObjects.PlndItmMonVal, objWeeklyDepartmentPlanningLLObjects.PlndItmTueVal,
				objWeeklyDepartmentPlanningLLObjects.PlndItmWedVal, objWeeklyDepartmentPlanningLLObjects.PlndItmThuVal,
				objWeeklyDepartmentPlanningLLObjects.PlndItmFriVal, objWeeklyDepartmentPlanningLLObjects.PlndItmSatVal,
				objWeeklyDepartmentPlanningLLObjects.PlndItmSunVal,
				objWeeklyDepartmentPlanningLLObjects.PlannedItemsCaption);
		PlndItmTtl = pageMonthlyPlanningPO.GetTotal(extentTest,
				objWeeklyDepartmentPlanningLLObjects.PlannedItemsCaption,
				objWeeklyDepartmentPlanningLLObjects.PlannedItemsTotal);

		pageDailyTradingStatementStorePO.DTSCompareValuesTotal(extentTest, DBPlndItm, PlndItm);
		pageMonthlyPlanningPO.CompareValues(extentTest, DBPlndItmTtl, PlndItmTtl);

		// Wage Cost
		Report_AddStep("testcase", "WAGE COST - PLANNING FOR THE WEEK", "", "", "");
		extentTest = report.startTest("Wage Cost");
		
		String WageCost = data.getText("WageCost");
		DBWgCost = sql.CLRexecuteQuery(getContext(),
				WageCost.replace("store", getContext().getStringProperty("store"))
						.replace("Week", getContext().getStringProperty("SelectFiscalWeek"))
						.replace("Department", getContext().getStringProperty("DepartmentToView"))
						.replace("Division", getContext().getStringProperty("Division")));
		System.out.println("DB Value: " + DBWgCost);

		String WageCostTotal = data.getText("WageCostTotal");
		DBWgCostTtl = sql.CLRexecuteQuery(getContext(),
				WageCostTotal.replace("store", getContext().getStringProperty("store"))
						.replace("Week", getContext().getStringProperty("SelectFiscalWeek"))
						.replace("Department", getContext().getStringProperty("DepartmentToView"))
						.replace("Division", getContext().getStringProperty("Division")));
		System.out.println("DB Total: " + DBWgCostTtl);

		WgCost = pageWeeklyDepartmentPlanningLLPO.UIResults(objWeeklyDepartmentPlanningObjects, "Wage Cost",
				objWeeklyDepartmentPlanningLLObjects.IndViewWgCostMonVal,
				objWeeklyDepartmentPlanningLLObjects.IndViewWgCostTueVal,
				objWeeklyDepartmentPlanningLLObjects.IndViewWgCostWedVal,
				objWeeklyDepartmentPlanningLLObjects.IndViewWgCostThuVal,
				objWeeklyDepartmentPlanningLLObjects.IndViewWgCostFriVal,
				objWeeklyDepartmentPlanningLLObjects.IndViewWgCostSatVal,
				objWeeklyDepartmentPlanningLLObjects.IndViewWgCostSunVal,
				objWeeklyDepartmentPlanningLLObjects.IndViewWgCostCaption);
		WgCostTtl = pageMonthlyPlanningPO.GetTotal(extentTest,
				objWeeklyDepartmentPlanningLLObjects.IndViewWgCostCaption,
				objWeeklyDepartmentPlanningLLObjects.IndViewWgCostTotal);

		pageDailyTradingStatementStorePO.DTSCompareValuesTotal(extentTest, DBWgCost, WgCost);
		pageMonthlyPlanningPO.CompareValueDecimalFields(extentTest, DBWgCostTtl, WgCostTtl);
	}

	// PST 537
	/*
	 * @Test(description =
	 * "Weekly Department Planning , LongLifeValidationForPlannedItemPrice",priority
	 * =3) public void LongLifeValidation_PIP() throws Exception { String
	 * WeeklyDepartmentPlanning =
	 * "Weekly Department Planning , LongLifeValidationForPlannedItemPrice";
	 * extentTest = report.
	 * startTest("Weekly Department Planning , LongLifeValidationForPlannedItemPrice"
	 * ); loginPage = new WoWLoginPage().createPage(getDriver(), getContext(), log,
	 * status, data,report,extentTest, WoWLoginPage.class);
	 * pageWeeklyDepartmentPlanningLLPO = new
	 * WeeklyDepartmentPlanning_LongLife().createPage(getDriver(), getContext(),
	 * log, status, data,getReport(), getReportLogger(),
	 * WeeklyDepartmentPlanningPage.class); pageMonthlyPlanningGroupPO = new
	 * MonthlyPlanningGroupPage().createPage(getDriver(), getContext(), log, status,
	 * data,getReport(), getReportLogger(), MonthlyPlanningGroupPage.class);
	 * pageWeeklyPlanningPO =
	 * loginPage.navToWeeklyPlanningStorePg(getContext().getStringProperty("store"))
	 * ; TestStatus status = getTestStatus();
	 * System.out.println("Welcome "+getContext().getStringProperty("username"));
	 * System.out.println("Entering into TestCase : Weekly Planning Department");
	 * pageWeeklyPlanningPO.VerifyUserName(objWeeklyPlanningStoreObjects,extentTest,
	 * getContext().getStringProperty("username"));
	 * pageWeeklyPlanningPO.SelectWeekFromDropdown(objWeeklyPlanningStoreObjects,
	 * extentTest,getContext().getStringProperty("SelectFiscalWeek"));
	 * pageWeeklyPlanningPO.SelectPage(objWeeklyPlanningStoreObjects,getContext().
	 * getStringProperty("WeeklyDepartmentPlanningLongLife"),extentTest);
	 * pageWeeklyPlanningPO.SwitchToFrame(objWeeklyPlanningStoreObjects.DataFrame);
	 * 
	 * OptionToView = getContext().getStringProperty("OptionToView");
	 * 
	 * Report_AddStep("testcase"," PLANNED ITEM PRICE - PLANNING FOR THE WEEK"
	 * ,"","", "");
	 * 
	 * String PlannedItemPrice = data.getText("PlannedItemPrice"); DBPlndItmPr =
	 * sql.CLRexecuteQuery(getContext(),
	 * PlannedItemPrice.replace("store",getContext().getStringProperty("store")).
	 * replace("Week",getContext().getStringProperty("SelectFiscalWeek"))
	 * .replace("Department",getContext().getStringProperty("Longlife")).replace(
	 * "Division",getContext().getStringProperty("Division")));
	 * System.out.println("DB Value: "+DBPlndItmPr);
	 * 
	 * String PlannedItemPriceTotal = data.getText("PlannedItemPriceTotal");
	 * DBPlndItmPrTtl = sql.CLRexecuteQuery(getContext(),
	 * PlannedItemPriceTotal.replace("store",getContext().getStringProperty("store")
	 * ).replace("Week",getContext().getStringProperty("SelectFiscalWeek"))
	 * .replace("Department",getContext().getStringProperty("Longlife")).replace(
	 * "Division",getContext().getStringProperty("Division")));
	 * System.out.println("DB Total: "+DBPlndItmPrTtl);
	 * 
	 * PlndItmPr=pageWeeklyDepartmentPlanningLLPO.UIResults(
	 * objWeeklyDepartmentPlanningObjects,"Planned Item Price"
	 * ,objWeeklyDepartmentPlanningLLObjects.PlndItmPriceMonValue,
	 * objWeeklyDepartmentPlanningLLObjects.PlndItmPriceTueValue,
	 * objWeeklyDepartmentPlanningLLObjects.PlndItmPriceWedValue,
	 * objWeeklyDepartmentPlanningLLObjects.PlndItmPriceThuValue,
	 * objWeeklyDepartmentPlanningLLObjects.PlndItmPriceFriValue,
	 * objWeeklyDepartmentPlanningLLObjects.PlndItmPriceSatValue,
	 * objWeeklyDepartmentPlanningLLObjects.PlndItmPriceSunValue,
	 * objWeeklyDepartmentPlanningLLObjects.PlannedItemPriceCaption);
	 * PlndItmPrTtl=pageWeeklyDepartmentPlanningLLPO.GetTotal(extentTest,
	 * objWeeklyDepartmentPlanningLLObjects.PlannedItemPriceCaption,
	 * objWeeklyDepartmentPlanningLLObjects.PlannedItemPriceTotal);
	 * 
	 * pageDailyTradingStatementStorePO.CompareValueDecimalTotal(extentTest,
	 * DBPlndItmPr,PlndItmPr);
	 * pageMonthlyPlanningPO.CompareValueDecimalFields(extentTest,DBPlndItmPrTtl,
	 * PlndItmPrTtl);
	 * 
	 * }
	 * 
	 * @Test(description =
	 * "Weekly Department Planning - Longlife, REPL/MERCH/INV DEPARTMENT VIEW"
	 * ,priority=4) public void
	 * DepartmentWiseSplit_LonglifeScreenForPlannedItemPrice() throws Exception {
	 * String WeeklyDepartmentPlanning =
	 * "Weekly Department Planning ,  REPL/MERCH/INV"; extentTest =
	 * report.startTest("Weekly Department Planning ,  REPL/MERCH/INV");
	 * pageWeeklyDepartmentPlanningLLPO = new
	 * WeeklyDepartmentPlanning_LongLife().createPage(getDriver(), getContext(),
	 * log, status, data,getReport(), getReportLogger(),
	 * WeeklyDepartmentPlanningPage.class); pageMonthlyPlanningGroupPO = new
	 * MonthlyPlanningGroupPage().createPage(getDriver(), getContext(), log, status,
	 * data,getReport(), getReportLogger(), MonthlyPlanningGroupPage.class);
	 * pageMonthlyPlanningPO = new
	 * WeeklyDepartmentPlanning_LongLife().createPage(getDriver(), getContext(),
	 * log, status, data,getReport(), getReportLogger(),
	 * MonthlyPlanningStorePage.class); TestStatus status = getTestStatus();
	 * System.out.
	 * println("Entering into TestCase : Weekly Department Planning - Longlife , REPL/MERCH/INV"
	 * );
	 * 
	 * 
	 * if(getContext().getStringProperty("DepartmentToView").contains("5"))
	 * pageWeeklyDepartmentPlanningLLPO.NavigationButton(
	 * objWeeklyDepartmentPlanningLLObjects.DataFrame,
	 * objWeeklyDepartmentPlanningLLObjects.ReplBtn,
	 * objWeeklyDepartmentPlanningLLObjects.Title,"GR/Replenishment", extentTest);
	 * if(getContext().getStringProperty("DepartmentToView").contains("10"))
	 * pageWeeklyDepartmentPlanningLLPO.NavigationButton(
	 * objWeeklyDepartmentPlanningLLObjects.DataFrame,
	 * objWeeklyDepartmentPlanningLLObjects.InvBtn,
	 * objWeeklyDepartmentPlanningLLObjects.Title,"PER/Inventory", extentTest);
	 * if(getContext().getStringProperty("DepartmentToView").contains("15"))
	 * pageWeeklyDepartmentPlanningLLPO.NavigationButton(
	 * objWeeklyDepartmentPlanningLLObjects.DataFrame,
	 * objWeeklyDepartmentPlanningLLObjects.MerchBtn,
	 * objWeeklyDepartmentPlanningLLObjects.Title,"GM/Merchandising", extentTest);
	 * 
	 * driver.switchTo().defaultContent();
	 * driver.switchTo().frame(driver.findElement(By.xpath(
	 * objWeeklyDepartmentPlanningObjects.DataFrameTwo)));
	 * 
	 * if (driver.findElements(By.xpath(objWeeklyDepartmentPlanningObjects.
	 * PlanCompleteSelectedCheckBox)).size() != 0) {
	 * pageWeeklyDepartmentPlanningLLPO.SelectDepartment(
	 * objWeeklyDepartmentPlanningObjects.DataFrame,
	 * objWeeklyDepartmentPlanningObjects.DepartmentHeader,
	 * objWeeklyDepartmentPlanningObjects.DepartmentDropdown,extentTest,getContext()
	 * .getStringProperty("DepartmentToView"));
	 * Report_AddStep("testcase"," PLANNED ITEM PRICE - PLANNING FOR THE WEEK"
	 * ,"","", ""); extentTest.log(LogStatus.INFO,
	 * "PLANNED ITEM PRICE - PLANNING FOR THE WEEK");
	 * 
	 * Report_AddStep("testcase"," PLANNED ITEM PRICE - PLANNING FOR THE WEEK"
	 * ,"","", "");
	 * 
	 * String PlannedItemPrice = data.getText("PlannedItemPrice"); DBPlndItmPr =
	 * sql.CLRexecuteQuery(getContext(),
	 * PlannedItemPrice.replace("store",getContext().getStringProperty("store")).
	 * replace("Week",getContext().getStringProperty("SelectFiscalWeek"))
	 * .replace("Department",getContext().getStringProperty("DepartmentToView")).
	 * replace("Division",getContext().getStringProperty("Division")));
	 * System.out.println("DB Value: "+DBPlndItmPr);
	 * 
	 * String PlannedItemPriceTotal = data.getText("PlannedItemPriceTotal");
	 * DBPlndItmPrTtl = sql.CLRexecuteQuery(getContext(),
	 * PlannedItemPriceTotal.replace("store",getContext().getStringProperty("store")
	 * ).replace("Week",getContext().getStringProperty("SelectFiscalWeek"))
	 * .replace("Department",getContext().getStringProperty("DepartmentToView")).
	 * replace("Division",getContext().getStringProperty("Division")));
	 * System.out.println("DB Total: "+DBPlndItmPrTtl);
	 * 
	 * PlndItmPr=pageWeeklyDepartmentPlanningLLPO.UIResults(
	 * objWeeklyDepartmentPlanningObjects,"Planned Item Price"
	 * ,objWeeklyDepartmentPlanningLLObjects.PlndItmPriceMonValue,
	 * objWeeklyDepartmentPlanningLLObjects.PlndItmPriceTueValue,
	 * objWeeklyDepartmentPlanningLLObjects.PlndItmPriceWedValue,
	 * objWeeklyDepartmentPlanningLLObjects.PlndItmPriceThuValue,
	 * objWeeklyDepartmentPlanningLLObjects.PlndItmPriceFriValue,
	 * objWeeklyDepartmentPlanningLLObjects.PlndItmPriceSatValue,
	 * objWeeklyDepartmentPlanningLLObjects.PlndItmPriceSunValue,
	 * objWeeklyDepartmentPlanningLLObjects.PlannedItemPriceCaption);
	 * PlndItmPrTtl=pageWeeklyDepartmentPlanningLLPO.GetTotal(extentTest,
	 * objWeeklyDepartmentPlanningLLObjects.PlannedItemPriceCaption,
	 * objWeeklyDepartmentPlanningLLObjects.PlannedItemPriceTotal);
	 * 
	 * pageDailyTradingStatementStorePO.CompareValueDecimalTotal(extentTest,
	 * DBPlndItmPr,PlndItmPr);
	 * pageMonthlyPlanningPO.CompareValueDecimalFields(extentTest,DBPlndItmPrTtl,
	 * PlndItmPrTtl);
	 * 
	 * } else {
	 * 
	 * pageWeeklyDepartmentPlanningLLPO.SelectDepartment(
	 * objWeeklyDepartmentPlanningObjects.DataFrame,
	 * objWeeklyDepartmentPlanningObjects.DepartmentHeader,
	 * objWeeklyDepartmentPlanningObjects.DepartmentDropdown,extentTest,getContext()
	 * .getStringProperty("DepartmentToView")); String
	 * Value0=getAttributeWithDalor(objWeeklyDepartmentPlanningObjects.
	 * PlndItmPriceMonValueget); double Monday=Double.parseDouble(Value0) * 2;
	 * String Monday1 = Double.toString(Monday); System.out.println(Monday1);
	 * pageWeeklyDepartmentPlanningLLPO.enterValueTwo(
	 * objWeeklyDepartmentPlanningObjects.PlndItmPriceMonValue, extentTest,
	 * Monday1,"PlndItmPriceMonValue");
	 * 
	 * String Value1=getAttributeWithDalor(objWeeklyDepartmentPlanningObjects.
	 * PlndItmPriceTueValueget); double Tuesday=Double.parseDouble(Value1) * 2;
	 * String Tuesday1 = Double.toString(Tuesday); System.out.println(Tuesday1);
	 * pageWeeklyDepartmentPlanningLLPO.enterValueTwo(
	 * objWeeklyDepartmentPlanningObjects.PlndItmPriceTueValue, extentTest,
	 * Tuesday1,"PlndItmPriceTueValue");
	 * 
	 * String Value2=getAttributeWithDalor(objWeeklyDepartmentPlanningObjects.
	 * PlndItmPriceWedValueget); double Wednesday=Double.parseDouble(Value2) * 2;
	 * String Wednesday1 = Double.toString(Wednesday);
	 * System.out.println(Wednesday1);
	 * 
	 * pageWeeklyDepartmentPlanningLLPO.enterValueTwo(
	 * objWeeklyDepartmentPlanningObjects.PlndItmPriceWedValue, extentTest,
	 * Wednesday1,"PlndItmPriceWedValue");
	 * 
	 * String Value3=getAttributeWithDalor(objWeeklyDepartmentPlanningObjects.
	 * PlndItmPriceThuValueget); double Thursday=Double.parseDouble(Value3) * 2;
	 * String Thursday1 = Double.toString(Thursday); System.out.println(Thursday1);
	 * pageWeeklyDepartmentPlanningLLPO.enterValueTwo(
	 * objWeeklyDepartmentPlanningObjects.PlndItmPriceThuValue, extentTest,
	 * Thursday1,"PlndItmPriceThuValue");
	 * 
	 * String Value4=getAttributeWithDalor(objWeeklyDepartmentPlanningObjects.
	 * PlndItmPriceFriValueget); double Friday=Double.parseDouble(Value4) * 2;
	 * String Friday1 = Double.toString(Friday); System.out.println(Friday1);
	 * pageWeeklyDepartmentPlanningLLPO.enterValueTwo(
	 * objWeeklyDepartmentPlanningObjects.PlndItmPriceFriValue, extentTest,
	 * Friday1,"PlndItmPriceFriValue");
	 * 
	 * String Value5=getAttributeWithDalor(objWeeklyDepartmentPlanningObjects.
	 * PlndItmPriceSatValueget); double Saturday=Double.parseDouble(Value5) * 2;
	 * String Saturday1 = Double.toString(Saturday); System.out.println(Saturday1);
	 * pageWeeklyDepartmentPlanningLLPO.enterValueTwo(
	 * objWeeklyDepartmentPlanningObjects.PlndItmPriceSatValue, extentTest,
	 * Saturday1,"PlndItmPriceSatValue");
	 * 
	 * String Value6=getAttributeWithDalor(objWeeklyDepartmentPlanningObjects.
	 * PlndItmPriceSunValueget); double Sunday=Double.parseDouble(Value6) * 2;
	 * String Sunday1 = Double.toString(Sunday); System.out.println(Sunday1);
	 * pageWeeklyDepartmentPlanningLLPO.enterValueTwo(
	 * objWeeklyDepartmentPlanningObjects.PlndItmPriceSunValue, extentTest,
	 * Sunday1,"PlndItmPriceSunValue");
	 * 
	 * waitFor(5); // WebElement fun =
	 * driver.findElements(By.xpath(objWeeklyDepartmentPlanningObjects.
	 * SaveButtoninPlanComplete));
	 * click(objWeeklyDepartmentPlanningObjects.SaveButtoninPlanCompleteWeb);
	 * //pageWeeklyPlanningPO.clickButton(objWeeklyDepartmentPlanningObjects.
	 * SaveButtoninPlanComplete, extentTest, "Continue");
	 * 
	 * 
	 * 
	 * Report_AddStep("testcase"," PLANNED ITEM PRICE - PLANNING FOR THE WEEK"
	 * ,"","", "");
	 * 
	 * String PlannedItemPrice = data.getText("PlannedItemPrice"); DBPlndItmPr =
	 * sql.CLRexecuteQuery(getContext(),
	 * PlannedItemPrice.replace("store",getContext().getStringProperty("store")).
	 * replace("Week",getContext().getStringProperty("SelectFiscalWeek"))
	 * .replace("Department",getContext().getStringProperty("DepartmentToView")).
	 * replace("Division",getContext().getStringProperty("Division")));
	 * System.out.println("DB Value: "+DBPlndItmPr);
	 * 
	 * String PlannedItemPriceTotal = data.getText("PlannedItemPriceTotal");
	 * DBPlndItmPrTtl = sql.CLRexecuteQuery(getContext(),
	 * PlannedItemPriceTotal.replace("store",getContext().getStringProperty("store")
	 * ).replace("Week",getContext().getStringProperty("SelectFiscalWeek"))
	 * .replace("Department",getContext().getStringProperty("DepartmentToView")).
	 * replace("Division",getContext().getStringProperty("Division")));
	 * System.out.println("DB Total: "+DBPlndItmPrTtl);
	 * 
	 * PlndItmPr=pageWeeklyDepartmentPlanningLLPO.UIResults(
	 * objWeeklyDepartmentPlanningObjects,"Planned Item Price"
	 * ,objWeeklyDepartmentPlanningLLObjects.PlndItmPriceMonValue,
	 * objWeeklyDepartmentPlanningLLObjects.PlndItmPriceTueValue,
	 * objWeeklyDepartmentPlanningLLObjects.PlndItmPriceWedValue,
	 * objWeeklyDepartmentPlanningLLObjects.PlndItmPriceThuValue,
	 * objWeeklyDepartmentPlanningLLObjects.PlndItmPriceFriValue,
	 * objWeeklyDepartmentPlanningLLObjects.PlndItmPriceSatValue,
	 * objWeeklyDepartmentPlanningLLObjects.PlndItmPriceSunValue,
	 * objWeeklyDepartmentPlanningLLObjects.PlannedItemPriceCaption);
	 * PlndItmPrTtl=pageWeeklyDepartmentPlanningLLPO.GetTotal(extentTest,
	 * objWeeklyDepartmentPlanningLLObjects.PlannedItemPriceCaption,
	 * objWeeklyDepartmentPlanningLLObjects.PlannedItemPriceTotal);
	 * 
	 * pageDailyTradingStatementStorePO.CompareValueDecimalTotal(extentTest,
	 * DBPlndItmPr,PlndItmPr);
	 * pageMonthlyPlanningPO.CompareValueDecimalFields(extentTest,DBPlndItmPrTtl,
	 * PlndItmPrTtl);
	 * 
	 * 
	 * } }
	 */

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
		pageMonthlyPlanningGroupPO = PageFactory.initElements(this.getDriver(), MonthlyPlanningGroupPage.class);
		pageWeeklyDepartmentPlanningLLPO = PageFactory.initElements(this.getDriver(),
				WeeklyDepartmentPlanningPage.class);
		objWeeklyDepartmentPlanningLLObjects = PageFactory.initElements(this.getDriver(),
				WeeklyDepartmentPlanning_LonglifeObjects.class);
		objWeeklyDepartmentPlanningObjects = PageFactory.initElements(this.getDriver(),
				WeeklyDepartmentPlanningObjects.class);
		pageDTSDepartmentPO = PageFactory.initElements(this.getDriver(), DTS_DepartmentPage.class);
		pageDailyTradingStatementStorePO = PageFactory.initElements(this.getDriver(),
				DailyTradingStatementStorePage.class);
		BaseObj = PageFactory.initElements(this.getDriver(), BasePage.class);
		common = PageFactory.initElements(this.getDriver(), CommonFunctions.class);
		storeprop = PageFactory.initElements(this.getDriver(), StoreProperties.class);
		objWeeklyPlanningStoreObjects = PageFactory.initElements(this.getDriver(), WeeklyPlanningStoreObjects.class);
		objDailyTradingStatementObjects = PageFactory.initElements(this.getDriver(),
				DailyTradingStatementObjects.class);
		pageMonthlyPlanningPO = PageFactory.initElements(this.getDriver(), MonthlyPlanningStorePage.class);
		objDTSDepartmentObjects = PageFactory.initElements(this.getDriver(), DTS_DepartmentObjects.class);
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
			data.loadTestDataXML(
					System.getProperty("user.dir") + "\\TestData\\WLP\\WeeklyDepartmentPlanningLonglife.xml");
			customreport.createExtentReport();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

}
