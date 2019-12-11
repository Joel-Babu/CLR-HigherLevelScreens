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

public class WeeklyDepartmentPlanning extends TestBase {

	protected Logger log = LogManager.getLogger(WeeklyDepartmentPlanning.class);
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
	WeeklyDepartmentPlanningObjects objWeeklyDepartmentPlanningObjects;

	BasePage BaseObj;
	CommonFunctions common;
	StoreProperties storeprop;

	List<List<Object>> UIValues, UITotal, DBVal, DBTtl;
	List<List<Object>> DBPlndSls, DBBdgtSls, DBPlndWg, DBBdgtWg, DBPlndWgPrcnt, DBBdgtWgPrcnt, DBPlndHr, DBPlndOR,
			DBFirstWk, DBFirstWkTtl, DBSecondWk, DBSecondWkTtl, DBThirdWk, DBThirdWkTtl, DBFourthWk, DBFourthWkTtl,
			DBAvgMix, DBCPHFirstWk, DBCPHSecondWk, DBCPHThirdWk, DBCPHFourthWk, DBGnrtSls, DBGnrtSlsTtl, DBPlndSlsWk,
			DBPlndSlsWkTtl, DBGnrtItmPr, DBGnrtItmPrTtl, DBPlndItmPr, DBPlndItmPrTtl, DBGnrtItm, DBGnrtItmTtl,DBPlannedSalesVSLYPer, PlannedSalesVSLY,
			DBPlndItm, DBPlndItmTtl, DBSMSHr, DBSMSHrTtl, DBPlndHrWk, DBPlndHrWkTtl, DBPlndVar, DBPlndVarTtl, DBORWk,
			DBORWkTtl, DBWgCost, DBWgCostTtl, DBWkType,DBAvgGrowth, Avggrowth,DBLastYearSls, LastyearSls,DBLastYearSlsTtl, LastyearSlsTtl;
	List<List<Object>> PlndSls, BdgtSls, PlndWg, BdgtWg, PlndWgPrcnt, BdgtWgPrcnt, PlndHr, PlndOR, FirstWk, FirstWkTtl,
			SecondWk, SecondWkTtl, ThirdWk, ThirdWkTtl, FourthWk, FourthWkTtl, AvgMix, CPHFirstWk, CPHSecondWk,
			CPHThirdWk, CPHFourthWk, GnrtSls, GnrtSlsTtl, PlndSlsWk, PlndSlsWkTtl, GnrtItmPr, GnrtItmPrTtl, PlndItmPr,DBLYOR, LYOR,
			PlndItmPrTtl, GnrtItm, DBFcstHr, FcstHr, DBBdgtHr, BdgtHr, GnrtItmTtl, PlndItm, PlndItmTtl, SMSHr, SMSHrTtl,
			PlndHrWk, PlndHrWkTtl, PlndVar, PlndVarTtl, ORWk, ORWkTtl, WgCost, WgCostTtl, WkType, DBBdgtOR, DBFcstOR,DBLYHours, LYHours,
			BdgtOR, FcstOR, PlndCust, PlndCustTtl, DBPlndCust, DBPlndCustTtl,DBLYwages, LYwages,DBLYSls, LYSls, DBLYwageper, LYwageper,DBPlannedSalesDailyMix, PlannedSalesDailyMixper;
	String OptionToView;

	public WeeklyDepartmentPlanning() {
		super.log = log;
	}

	///////////////////// SUMMARY INFO /////////////////////////
	@Test(description = "Weekly Department Planning , SummaryInfoValidation", priority = 1)
	public void SummaryInfoValidation() throws Exception {
		loginPage = new WoWLoginPage().createPage(getDriver(), getContext(), log, status, data, report, extentTest,
				WoWLoginPage.class);
		pageWeeklyDepartmentPlanningLLPO = new WeeklyDepartmentPlanning().createPage(getDriver(), getContext(), log,
				status, data, getReport(), getReportLogger(), WeeklyDepartmentPlanningPage.class);
		pageMonthlyPlanningGroupPO = new MonthlyPlanningGroupPage().createPage(getDriver(), getContext(), log, status,
				data, getReport(), getReportLogger(), MonthlyPlanningGroupPage.class);
		OptionToView = getContext().getStringProperty("OptionToView");
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
				objWeeklyPlanningStoreObjects.DeptPlnscreenToView, "Weekly Department Planning");
		
		pageWeeklyDepartmentPlanningLLPO.SelectDepartment(objWeeklyDepartmentPlanningObjects.DataFrame,
				objWeeklyDepartmentPlanningObjects.DepartmentHeader,
				objWeeklyDepartmentPlanningObjects.DepartmentDropdown, extentTest,
				getContext().getStringProperty("DepartmentToView"));
		OptionToView = getContext().getStringProperty("OptionToView");	
		
		
		// LastYear Sales
		
				Report_AddStep("testcase", " SUMMARY INFO- LY SALES DATA", "", "", "");
				extentTest = report.startTest("Summary Info - LY  Sales");

				String LastyearSales = data.getText("LastYearSales");
				DBLYSls = sql.CLRexecuteQuery(getContext(),
						LastyearSales.replace("store", getContext().getStringProperty("store"))
								.replace("Week", getContext().getStringProperty("SelectFiscalWeek")).replace("LYWk", getContext().getStringProperty("LYWk")).
								replace("Department", getContext().getStringProperty("DepartmentToView"))
								.replace("Division", getContext().getStringProperty("Division")));
				System.out.println("DB Value: " + DBLYSls);

				LYSls = pageWeeklyDepartmentPlanningLLPO.UIResultsSummaryInfo(
						objWeeklyDepartmentPlanningObjects.SummaryInfoLastyearcolumn, extentTest,
						objWeeklyDepartmentPlanningObjects.SummaryInfoLYSales, objWeeklyDepartmentPlanningObjects.Tablename);
				pageMonthlyPlanningPO.CompareValues(extentTest, DBLYSls, LYSls);
				// LastYear Wages
				Report_AddStep("testcase", " SUMMARY INFO- LY Wages DATA", "", "", "");
				extentTest = report.startTest("Summary Info - LY  wages");

				String LastyearSWages = data.getText("LastYearWages");
				DBLYwages = sql.CLRexecuteQuery(getContext(),
						LastyearSWages.replace("store", getContext().getStringProperty("store"))
								.replace("Week", getContext().getStringProperty("SelectFiscalWeek"))
								.replace("Department", getContext().getStringProperty("DepartmentToView")).replace("LYWk", getContext().getStringProperty("LYWk"))
								.replace("Division", getContext().getStringProperty("Division")));
				System.out.println("DB Value: " + DBLYwages);

				LYwages = pageWeeklyDepartmentPlanningLLPO.UIResultsSummaryInfo(
						objWeeklyDepartmentPlanningObjects.SummaryInfoLastyearcolumn, extentTest,
						objWeeklyDepartmentPlanningObjects.SummaryInfoLYWages, objWeeklyDepartmentPlanningObjects.Tablename);
				pageMonthlyPlanningPO.CompareValues(extentTest, DBLYwages, LYwages);

				// LastYear Wage%
				Report_AddStep("testcase", " SUMMARY INFO- LY WagesPer DATA", "", "", "");
				extentTest = report.startTest("Summary Info - LYWagesper");

				String LastyearSWageper = data.getText("LastYearWageper");
				DBLYwageper = sql.CLRexecuteQuery(getContext(),
						LastyearSWageper.replace("store", getContext().getStringProperty("store"))
								.replace("Week", getContext().getStringProperty("SelectFiscalWeek"))
								.replace("Department", getContext().getStringProperty("DepartmentToView")).replace("LYWk", getContext().getStringProperty("LYWk"))
								.replace("Division", getContext().getStringProperty("Division")));
				System.out.println("DB Value: " + DBLYwageper);

				LYwageper = pageWeeklyDepartmentPlanningLLPO.UIResultsSummaryInfo(
						objWeeklyDepartmentPlanningObjects.SummaryInfoLastyearcolumn, extentTest,
						objWeeklyDepartmentPlanningObjects.SummaryInfoLYWageper, objWeeklyDepartmentPlanningObjects.Tablename);
				pageMonthlyPlanningPO.CompareValues(extentTest, DBLYwageper, LYwageper);
				//Last Year Hours
				Report_AddStep("testcase", " SUMMARY INFO- LYHours DATA", "", "", "");
				extentTest = report.startTest("Summary Info - LYHours");

				String LastyearHours = data.getText("LastYearHours");
				DBLYHours = sql.CLRexecuteQuery(getContext(),
						LastyearHours.replace("store", getContext().getStringProperty("store"))
								.replace("Week", getContext().getStringProperty("SelectFiscalWeek"))
								.replace("Department", getContext().getStringProperty("DepartmentToView")).replace("LYWk", getContext().getStringProperty("LYWk"))
								.replace("Division", getContext().getStringProperty("Division")));
				System.out.println("DB Value: " + DBLYHours);

				LYHours = pageWeeklyDepartmentPlanningLLPO.UIResultsSummaryInfo(
						objWeeklyDepartmentPlanningObjects.SummaryInfoLastyearcolumn, extentTest,
						objWeeklyDepartmentPlanningObjects.SummaryInfoLYHours, objWeeklyDepartmentPlanningObjects.Tablename);
				pageMonthlyPlanningPO.CompareValues(extentTest, DBLYHours, LYHours);
				
				// Last Year OR%
				Report_AddStep("testcase", " SUMMARY INFO- LY Operating Ratio DATA", "", "", "");
				extentTest = report.startTest("Summary Info - LYOperating Ratio");

				String LastyearOR = data.getText("LastYearORPer");
				DBLYOR = sql.CLRexecuteQuery(getContext(),
						LastyearOR.replace("store", getContext().getStringProperty("store"))
								.replace("Week", getContext().getStringProperty("SelectFiscalWeek")).replace("FinWk", getContext().getStringProperty("FinWk"))
								.replace("Department", getContext().getStringProperty("DepartmentToView")).replace("LYWk", getContext().getStringProperty("LYWk")).replace("LYFinYr", getContext().getStringProperty("LastYr"))
								.replace("Division", getContext().getStringProperty("Division")));
				System.out.println("DB Value: " + DBLYOR);

				LYOR = pageWeeklyDepartmentPlanningLLPO.UIResultsSummaryInfo(
						objWeeklyDepartmentPlanningObjects.SummaryInfoLastyearcolumn, extentTest,
						objWeeklyDepartmentPlanningObjects.SummaryInfoORPer, objWeeklyDepartmentPlanningObjects.Tablename);
				pageMonthlyPlanningPO.CompareValues(extentTest, DBLYOR, LYOR);

		// Sales
		Report_AddStep("testcase", " SUMMARY INFO- PLANNED SALES DATA", "", "", "");
		extentTest = report.startTest("Summary Info - Planned Sales");

		String PlannedSales = data.getText("PlannedSales");
		DBPlndSls = sql.CLRexecuteQuery(getContext(),
				PlannedSales.replace("store", getContext().getStringProperty("store"))
						.replace("Week", getContext().getStringProperty("SelectFiscalWeek"))
						.replace("Department", getContext().getStringProperty("DepartmentToView"))
						.replace("Division", getContext().getStringProperty("Division")));
		System.out.println("DB Value: " + DBPlndSls);

		PlndSls = pageWeeklyDepartmentPlanningLLPO.UIResultsSummaryInfo(
				objWeeklyDepartmentPlanningObjects.SummaryInfocolumn, extentTest,
				objWeeklyDepartmentPlanningObjects.SummaryInfoSales, objWeeklyDepartmentPlanningObjects.Tablename);
		pageMonthlyPlanningPO.CompareValues(extentTest, DBPlndSls, DBPlndSls);

		if (OptionToView.contains("Budget")) {
			Report_AddStep("testcase", " SUMMARY INFO- BUDGET SALES DATA", "", "", "");
			extentTest = report.startTest("Summary Info - Budget Sales");

			String BudgetSales = data.getText("BudgetSales");
			DBBdgtSls = sql.CLRexecuteQuery(getContext(),
					BudgetSales.replace("store", getContext().getStringProperty("store"))
							.replace("Week", getContext().getStringProperty("SelectFiscalWeek"))
							.replace("Department", getContext().getStringProperty("DepartmentToView"))
							.replace("Division", getContext().getStringProperty("Division")));
			System.out.println("DB Value: " + DBBdgtSls);

			BdgtSls = pageWeeklyDepartmentPlanningLLPO.UIResultsSummaryInfo(
					objWeeklyDepartmentPlanningObjects.SummaryInfoBudgetcolumn, extentTest,
					objWeeklyDepartmentPlanningObjects.SummaryInfoBudgetSales,
					objWeeklyDepartmentPlanningObjects.Tablename);
			pageMonthlyPlanningPO.CompareValues(extentTest, DBBdgtSls, DBBdgtSls);
		}
		if (OptionToView.contains("Forecast")) {
			Report_AddStep("testcase", " SUMMARY INFO- FORECAST SALES DATA", "", "", "");
			extentTest.log(LogStatus.INFO, "SUMMARY INFO- FORECAST SALES DATA");
			extentTest = report.startTest("Summary Info - Forecast Sales");
			
			String ForecastSales = data.getText("ForecastSales");
			DBBdgtSls = sql.CLRexecuteQuery(getContext(),
					ForecastSales.replace("store", getContext().getStringProperty("store"))
							.replace("Week", getContext().getStringProperty("SelectFiscalWeek"))
							.replace("Department", getContext().getStringProperty("DepartmentToView"))
							.replace("Division", getContext().getStringProperty("Division")));
			System.out.println("DB Value: " + DBBdgtSls);

			BdgtSls = pageWeeklyDepartmentPlanningLLPO.UIResultsSummaryInfo(
					objWeeklyDepartmentPlanningObjects.SummaryInfoBudgetcolumn, extentTest,
					objWeeklyDepartmentPlanningObjects.SummaryInfoBudgetSales,
					objWeeklyDepartmentPlanningObjects.Tablename);
			pageMonthlyPlanningPO.CompareValues(extentTest, DBBdgtSls, BdgtSls);
		}
	

		// Wage
		Report_AddStep("testcase", " SUMMARY INFO - PLANNED WAGES DATA", "", "", "");
		extentTest = report.startTest("Summary Info - Planned Wages");

		String PlannedWages = data.getText("PlannedWages");
		DBPlndWg = sql.CLRexecuteQuery(getContext(),
				PlannedWages.replace("store", getContext().getStringProperty("store"))
						.replace("Week", getContext().getStringProperty("SelectFiscalWeek"))
						.replace("Department", getContext().getStringProperty("DepartmentToView"))
						.replace("Division", getContext().getStringProperty("Division")));
		System.out.println("DB Value: " + DBPlndWg);

		PlndWg = pageWeeklyDepartmentPlanningLLPO.UIResultsSummaryInfo(
				objWeeklyDepartmentPlanningObjects.SummaryInfocolumn, extentTest,
				objWeeklyDepartmentPlanningObjects.SummaryInfoWage, objWeeklyDepartmentPlanningObjects.Tablename);
		pageMonthlyPlanningPO.CompareValues(extentTest, DBPlndWg, PlndWg);

		if (OptionToView.contains("Budget")) {
			Report_AddStep("testcase", " SUMMARY INFO- BUDGET WAGES DATA", "", "", "");
			extentTest = report.startTest("Summary Info - Budget Wages");
			
			String BudgetWages = data.getText("BudgetWages");
			DBBdgtWg = sql.CLRexecuteQuery(getContext(),
					BudgetWages.replace("store", getContext().getStringProperty("store"))
							.replace("Week", getContext().getStringProperty("SelectFiscalWeek"))
							.replace("Department", getContext().getStringProperty("DepartmentToView"))
							.replace("Division", getContext().getStringProperty("Division")));
			System.out.println("DB Value: " + DBBdgtWg);

			BdgtWg = pageWeeklyDepartmentPlanningLLPO.UIResultsSummaryInfo(
					objWeeklyDepartmentPlanningObjects.SummaryInfoBudgetcolumn, extentTest,
					objWeeklyDepartmentPlanningObjects.SummaryInfoBudgetWage,
					objWeeklyDepartmentPlanningObjects.Tablename);
			pageMonthlyPlanningPO.CompareValues(extentTest, DBBdgtWg, BdgtWg);
		}
		if (OptionToView.contains("Forecast")) {
			Report_AddStep("testcase", " SUMMARY INFO- FORECAST WAGES DATA", "", "", "");
			extentTest = report.startTest("Summary Info - Forecast Wages");

			String ForecastWages = data.getText("ForecastWages");
			DBBdgtWg = sql.CLRexecuteQuery(getContext(),
					ForecastWages.replace("store", getContext().getStringProperty("store"))
							.replace("Week", getContext().getStringProperty("SelectFiscalWeek"))
							.replace("Department", getContext().getStringProperty("DepartmentToView"))
							.replace("Division", getContext().getStringProperty("Division")));
			System.out.println("DB Value: " + DBBdgtWg);

			BdgtWg = pageWeeklyDepartmentPlanningLLPO.UIResultsSummaryInfo(
					objWeeklyDepartmentPlanningObjects.SummaryInfoBudgetcolumn, extentTest,
					objWeeklyDepartmentPlanningObjects.SummaryInfoBudgetWage,
					objWeeklyDepartmentPlanningObjects.Tablename);
			pageMonthlyPlanningPO.CompareValues(extentTest, DBBdgtWg, BdgtWg);
		}

		// Wage%
		Report_AddStep("testcase", " SUMMARY INFO- PLANNED WAGE PERCENT DATA", "", "", "");
		extentTest = report.startTest("Summary Info - Planned Wage Prcnt");

		String PlannedWagesPercentage = data.getText("PlannedWagesPercentage");
		DBPlndWgPrcnt = sql.CLRexecuteQuery(getContext(),
				PlannedWagesPercentage.replace("store", getContext().getStringProperty("store"))
						.replace("Week", getContext().getStringProperty("SelectFiscalWeek"))
						.replace("Department", getContext().getStringProperty("DepartmentToView"))
						.replace("Division", getContext().getStringProperty("Division")));
		System.out.println("DB Value: " + DBPlndWgPrcnt);

		PlndWgPrcnt = pageWeeklyDepartmentPlanningLLPO.UIResultsSummaryInfo(
				objWeeklyDepartmentPlanningObjects.SummaryInfocolumn, extentTest,
				objWeeklyDepartmentPlanningObjects.SummaryInfoWagepercent,
				objWeeklyDepartmentPlanningObjects.Tablename);
		pageMonthlyPlanningPO.CompareValues(extentTest, DBPlndWgPrcnt, PlndWgPrcnt);

		// Wage%
		if (OptionToView.contains("Budget")) {
			Report_AddStep("testcase", " SUMMARY INFO- BUDGET WAGE PERCENT DATA", "", "", "");
			extentTest = report.startTest("Summary Info - Budget Wage Prcnt");

			String BudgetWagesPercentage = data.getText("BudgetWagesPercentage");
			DBBdgtWgPrcnt = sql.CLRexecuteQuery(getContext(),
					BudgetWagesPercentage.replace("store", getContext().getStringProperty("store"))
							.replace("Week", getContext().getStringProperty("SelectFiscalWeek"))
							.replace("Department", getContext().getStringProperty("DepartmentToView"))
							.replace("Division", getContext().getStringProperty("Division")));
			System.out.println("DB Value: " + DBBdgtWgPrcnt);

			BdgtWgPrcnt = pageWeeklyDepartmentPlanningLLPO.UIResultsSummaryInfo(
					objWeeklyDepartmentPlanningObjects.SummaryInfoBudgetcolumn, extentTest,
					objWeeklyDepartmentPlanningObjects.SummaryInfoBudgetWagepercent,
					objWeeklyDepartmentPlanningObjects.Tablename);
			pageMonthlyPlanningPO.CompareValueDecimalFields(extentTest, DBBdgtWgPrcnt, BdgtWgPrcnt);
		}
		if (OptionToView.contains("Forecast")) {
			Report_AddStep("testcase", " SUMMARY INFO- FORECAST WAGE PERCENT DATA", "", "", "");
			extentTest = report.startTest("Summary Info - Forecast Wage Prcnt");

			String ForecastWagesPercentage = data.getText("ForecastWagesPercentage");
			DBBdgtWgPrcnt = sql.CLRexecuteQuery(getContext(),
					ForecastWagesPercentage.replace("store", getContext().getStringProperty("store"))
							.replace("Week", getContext().getStringProperty("SelectFiscalWeek"))
							.replace("Department", getContext().getStringProperty("DepartmentToView"))
							.replace("Division", getContext().getStringProperty("Division")));
			System.out.println("DB Value: " + DBBdgtWgPrcnt);

			BdgtWgPrcnt = pageWeeklyDepartmentPlanningLLPO.UIResultsSummaryInfo(
					objWeeklyDepartmentPlanningObjects.SummaryInfoBudgetcolumn, extentTest,
					objWeeklyDepartmentPlanningObjects.SummaryInfoBudgetWagepercent,
					objWeeklyDepartmentPlanningObjects.Tablename);
			pageMonthlyPlanningPO.CompareValueDecimalFields(extentTest, DBBdgtWgPrcnt, BdgtWgPrcnt);
		}

		// Plan Paid Hours
		Report_AddStep("testcase", " SUMMARY INFO-PLANNED HOURS DATA", "", "", "");
		extentTest = report.startTest("Summary Info - Planned Hours");

		String PlannedHours = data.getText("PlannedHours");
		DBPlndHr = sql.CLRexecuteQuery(getContext(),
				PlannedHours.replace("store", getContext().getStringProperty("store"))
						.replace("Week", getContext().getStringProperty("SelectFiscalWeek"))
						.replace("Department", getContext().getStringProperty("DepartmentToView"))
						.replace("Division", getContext().getStringProperty("Division")));
		System.out.println("DB Value: " + DBPlndHr);

		PlndHr = pageWeeklyDepartmentPlanningLLPO.UIResultsSummaryInfo(
				objWeeklyDepartmentPlanningObjects.SummaryInfocolumn, extentTest,
				objWeeklyDepartmentPlanningObjects.SummaryInfoPlanPaidHours,
				objWeeklyDepartmentPlanningObjects.Tablename);
		pageMonthlyPlanningPO.CompareValues(extentTest, DBPlndHr, PlndHr);

		// Budget Hours
		if (OptionToView.contains("Budget")) {
			Report_AddStep("testcase", " SUMMARY INFO- BUDGET HOURS DATA", "", "", "");
			extentTest = report.startTest("Summary Info - Budget Hours");

			String BudgetHours = data.getText("BudgetHours");
			DBBdgtHr = sql.CLRexecuteQuery(getContext(),
					BudgetHours.replace("store", getContext().getStringProperty("store"))
							.replace("Week", getContext().getStringProperty("SelectFiscalWeek"))
							.replace("Department", getContext().getStringProperty("DepartmentToView"))
							.replace("FinWk", getContext().getStringProperty("FinWk"))
							.replace("FinYear", getContext().getStringProperty("FinYr"))
							.replace("Division", getContext().getStringProperty("Division")));
			System.out.println("DB Value: " + DBBdgtHr);

			BdgtHr = pageWeeklyDepartmentPlanningLLPO.UIResultsSummaryInfo(
					objWeeklyDepartmentPlanningObjects.SummaryInfoBudgetcolumn, extentTest,
					objWeeklyDepartmentPlanningObjects.SummaryInfoBudgetPaidHours,
					objWeeklyDepartmentPlanningObjects.Tablename);
			pageMonthlyPlanningPO.CompareValueDecimalFields(extentTest, DBBdgtHr, BdgtHr);
		}
		if (OptionToView.contains("Forecast")) {
			Report_AddStep("testcase", " SUMMARY INFO- FORECAST HOURS DATA", "", "", "");
			extentTest = report.startTest("Summary Info - Forecast Hours");

			String ForecastHours = data.getText("ForecastHours");
			DBFcstHr = sql.CLRexecuteQuery(getContext(),
					ForecastHours.replace("store", getContext().getStringProperty("store"))
							.replace("Week", getContext().getStringProperty("SelectFiscalWeek"))
							.replace("Department", getContext().getStringProperty("DepartmentToView"))
							.replace("FinWk", getContext().getStringProperty("FinWk"))
							.replace("FinYear", getContext().getStringProperty("FinYr"))
							.replace("Division", getContext().getStringProperty("Division")));
			System.out.println("DB Value: " + DBFcstHr);

			FcstHr = pageWeeklyDepartmentPlanningLLPO.UIResultsSummaryInfo(
					objWeeklyDepartmentPlanningObjects.SummaryInfoBudgetcolumn, extentTest,
					objWeeklyDepartmentPlanningObjects.SummaryInfoBudgetPaidHours,
					objWeeklyDepartmentPlanningObjects.Tablename);
			pageMonthlyPlanningPO.CompareValueDecimalFields(extentTest, DBFcstHr, FcstHr);
		}

		// OR%
		Report_AddStep("testcase", " SUMMARY INFO-PLANNED OR% DATA", "", "", "");
		extentTest = report.startTest("Summary Info - Planned OR");

		String PlannedOR = data.getText("PlannedOR");
		DBPlndOR = sql.CLRexecuteQuery(getContext(),
				PlannedOR.replace("store", getContext().getStringProperty("store"))
						.replace("Week", getContext().getStringProperty("SelectFiscalWeek"))
						.replace("Department", getContext().getStringProperty("DepartmentToView"))
						.replace("Division", getContext().getStringProperty("Division")));
		System.out.println("DB Value: " + DBPlndOR);

		PlndOR = pageWeeklyDepartmentPlanningLLPO.UIResultsSummaryInfo(
				objWeeklyDepartmentPlanningObjects.SummaryInfocolumn, extentTest,
				objWeeklyDepartmentPlanningObjects.SummaryInfoORpercent, objWeeklyDepartmentPlanningObjects.Tablename);
		pageMonthlyPlanningPO.CompareValueDecimalFields(extentTest, DBPlndOR, PlndOR);

		// Budget OR%
		if (OptionToView.contains("Budget")) {
			Report_AddStep("testcase", " SUMMARY INFO- BUDGET OR PERCENT DATA", "", "", "");
			extentTest = report.startTest("Summary Info - Budget OR");

			String BudgetOR = data.getText("BudgetOR");
			DBBdgtOR = sql.CLRexecuteQuery(getContext(),
					BudgetOR.replace("store", getContext().getStringProperty("store"))
							.replace("Week", getContext().getStringProperty("SelectFiscalWeek"))
							.replace("Department", getContext().getStringProperty("DepartmentToView"))
							.replace("FinWk", getContext().getStringProperty("FinWk"))
							.replace("FinYear", getContext().getStringProperty("FinYr"))
							.replace("Division", getContext().getStringProperty("Division")));
			System.out.println("DB Value: " + DBBdgtOR);

			BdgtOR = pageWeeklyDepartmentPlanningLLPO.UIResultsSummaryInfo(
					objWeeklyDepartmentPlanningObjects.SummaryInfoBudgetcolumn, extentTest,
					objWeeklyDepartmentPlanningObjects.SummaryInfoBudgetORpercent,
					objWeeklyDepartmentPlanningObjects.Tablename);
			pageMonthlyPlanningPO.CompareValueDecimalFields(extentTest, DBBdgtOR, BdgtOR);
		}
		if (OptionToView.contains("Forecast")) {
			Report_AddStep("testcase", " SUMMARY INFO- FORECAST OR PERCENT DATA", "", "", "");
			extentTest = report.startTest("Summary Info - Forecast OR");

			String ForecastOR = data.getText("ForecastOR");
			DBFcstOR = sql.CLRexecuteQuery(getContext(),
					ForecastOR.replace("store", getContext().getStringProperty("store"))
							.replace("Week", getContext().getStringProperty("SelectFiscalWeek"))
							.replace("Department", getContext().getStringProperty("DepartmentToView"))
							.replace("FinWk", getContext().getStringProperty("FinWk"))
							.replace("FinYear", getContext().getStringProperty("FinYr"))
							.replace("Division", getContext().getStringProperty("Division")));
			System.out.println("DB Value: " + DBFcstOR);

			FcstOR = pageWeeklyDepartmentPlanningLLPO.UIResultsSummaryInfo(
					objWeeklyDepartmentPlanningObjects.SummaryInfoBudgetcolumn, extentTest,
					objWeeklyDepartmentPlanningObjects.SummaryInfoBudgetORpercent,
					objWeeklyDepartmentPlanningObjects.Tablename);
			pageMonthlyPlanningPO.CompareValueDecimalFields(extentTest, DBFcstOR, FcstOR);
		}
		
	}

	///////////////////// SALES HISTORY /////////////////////////
	@Test(description = "Weekly Department Planning , SalesHistoryValidation", priority = 2)
	public void SalesHistoryValidation() throws Exception {
		pageWeeklyDepartmentPlanningLLPO = new WeeklyDepartmentPlanning().createPage(getDriver(), getContext(), log,
				status, data, getReport(), getReportLogger(), WeeklyDepartmentPlanningPage.class);
		pageMonthlyPlanningGroupPO = new MonthlyPlanningGroupPage().createPage(getDriver(), getContext(), log, status,
				data, getReport(), getReportLogger(), MonthlyPlanningGroupPage.class);
		pageMonthlyPlanningPO = new WeeklyDepartmentPlanning().createPage(getDriver(), getContext(), log, status, data,
				getReport(), getReportLogger(), MonthlyPlanningStorePage.class);
		TestStatus status = getTestStatus();
		System.out.println("Entering into TestCase : Weekly Planning Department");
		String WeekID = getContext().getStringProperty("SelectFiscalWeek");
		String LYWeekID = getContext().getStringProperty("LYWk");
		int LYWk1 = Integer.parseInt(LYWeekID) - 2;
		int LYWk4 = Integer.parseInt(LYWeekID) - 5;
		int Wk1 = Integer.parseInt(WeekID) - 2;
		int Wk2 = Integer.parseInt(WeekID) - 3;
		int Wk3 = Integer.parseInt(WeekID) - 4;
		int Wk4 = Integer.parseInt(WeekID) - 5;

		// CPH - Week 1
		Report_AddStep("testcase", " CPH VALIDATION - FIRST WEEK", "", "", "");
		extentTest = report.startTest("CPH - First Week");

		String CPH1 = data.getText("CPH");
		DBCPHFirstWk = sql.CLRexecuteQuery(getContext(),
				CPH1.replace("store", getContext().getStringProperty("store"))./*replace("Week", String.valueOf(Wk4))*/replace("Week", getContext().getStringProperty("Week1"))
						.replace("Department", getContext().getStringProperty("DepartmentToView"))
						.replace("Division", getContext().getStringProperty("Division")));
		System.out.println("DB Value: " + DBCPHFirstWk);

		CPHFirstWk = pageMonthlyPlanningPO.GetTotal(extentTest, objWeeklyDepartmentPlanningObjects.FirstWeekCaption,
				objWeeklyDepartmentPlanningObjects.CPHFirstWk);

		pageMonthlyPlanningPO.CompareValueDecimalFields(extentTest, DBCPHFirstWk, CPHFirstWk);

		// CPH - Week 2
		Report_AddStep("testcase", " CPH VALIDATION - SECOND WEEK", "", "", "");
		extentTest = report.startTest("CPH - Second Week");

		String CPH2 = data.getText("CPH");
		DBCPHSecondWk = sql.CLRexecuteQuery(getContext(),
				CPH2.replace("store", getContext().getStringProperty("store"))./*replace("Week", String.valueOf(Wk3))*/
						replace("Department", getContext().getStringProperty("DepartmentToView")).replace("Week", getContext().getStringProperty("Week2"))
						.replace("Division", getContext().getStringProperty("Division")));
		System.out.println("DB Value: " + DBCPHSecondWk);

		CPHSecondWk = pageMonthlyPlanningPO.GetTotal(extentTest, objWeeklyDepartmentPlanningObjects.SecondWeekCaption,
				objWeeklyDepartmentPlanningObjects.CPHSecondWk);

		pageMonthlyPlanningPO.CompareValueDecimalFields(extentTest, DBCPHSecondWk, CPHSecondWk);

		// CPH - Week 3
		Report_AddStep("testcase", " CPH VALIDATION - THIRD WEEK", "", "", "");
		extentTest = report.startTest("CPH - Third Week");

		String CPH3 = data.getText("CPH");
		DBCPHThirdWk = sql.CLRexecuteQuery(getContext(),
				CPH3.replace("store", getContext().getStringProperty("store"))/*.replace("Week", String.valueOf(Wk2))*/
						.replace("Department", getContext().getStringProperty("DepartmentToView")).replace("Week", getContext().getStringProperty("Week3"))
						.replace("Division", getContext().getStringProperty("Division")));
		System.out.println("DB Value: " + DBCPHThirdWk);

		CPHThirdWk = pageMonthlyPlanningPO.GetTotal(extentTest, objWeeklyDepartmentPlanningObjects.ThirdWeekCaption,
				objWeeklyDepartmentPlanningObjects.CPHThirdWk);

		pageMonthlyPlanningPO.CompareValueDecimalFields(extentTest, DBCPHThirdWk, CPHThirdWk);

		// CPH - Week 4
		Report_AddStep("testcase", " CPH VALIDATION - FOURTH WEEK", "", "", "");
		extentTest = report.startTest("CPH - Fourth Week");

		String CPH4 = data.getText("CPH");
		DBCPHFourthWk = sql.CLRexecuteQuery(getContext(),
				CPH4.replace("store", getContext().getStringProperty("store"))/*.replace("Week", String.valueOf(Wk1))*/
						.replace("Department", getContext().getStringProperty("DepartmentToView")).replace("Week", getContext().getStringProperty("Week4"))
						.replace("Division", getContext().getStringProperty("Division")));
		System.out.println("DB Value: " + DBCPHFourthWk);

		CPHFourthWk = pageMonthlyPlanningPO.GetTotal(extentTest, objWeeklyDepartmentPlanningObjects.FourthWeekCaption,
				objWeeklyDepartmentPlanningObjects.CPHFourthWk);

		pageMonthlyPlanningPO.CompareValueDecimalFields(extentTest, DBCPHFourthWk, CPHFourthWk);

		// Sales history - Week -1
		Report_AddStep("testcase", " SALES HISTORY - FIRST WEEK", "", "", "");
		extentTest = report.startTest("Sales History - First Week");

		String SalesHistory1 = data.getText("SalesHistory");
		DBFirstWk = sql.CLRexecuteQuery(getContext(),
				SalesHistory1.replace("store", getContext().getStringProperty("store"))
						/*.replace("Week", String.valueOf(Wk4))*/.replace("Week", getContext().getStringProperty("Week1"))
						.replace("Department", getContext().getStringProperty("DepartmentToView"))
						.replace("Division", getContext().getStringProperty("Division")));
		System.out.println("DB Value: " + DBFirstWk);

		String SalesHistoryTotal1 = data.getText("SalesHistoryTotal");
		DBFirstWkTtl = sql.CLRexecuteQuery(getContext(),
				SalesHistoryTotal1.replace("store", getContext().getStringProperty("store"))
						/*.replace("Week", String.valueOf(Wk4))*/.replace("Week", getContext().getStringProperty("Week1"))
						.replace("Department", getContext().getStringProperty("DepartmentToView"))
						.replace("Division", getContext().getStringProperty("Division")));
		System.out.println("DB Total: " + DBFirstWkTtl);

		FirstWk = pageWeeklyDepartmentPlanningLLPO.UIResults(objWeeklyDepartmentPlanningObjects, "Sales history",
				objWeeklyDepartmentPlanningObjects.FirstWeekMonval, objWeeklyDepartmentPlanningObjects.FirstWeekTueval,
				objWeeklyDepartmentPlanningObjects.FirstWeekWedval, objWeeklyDepartmentPlanningObjects.FirstWeekThuval,
				objWeeklyDepartmentPlanningObjects.FirstWeekFrival, objWeeklyDepartmentPlanningObjects.FirstWeekSatval,
				objWeeklyDepartmentPlanningObjects.FirstWeekSunval,
				objWeeklyDepartmentPlanningObjects.FirstWeekCaption);
		FirstWkTtl = pageMonthlyPlanningPO.GetTotal(extentTest, objWeeklyDepartmentPlanningObjects.FirstWeekCaption,
				objWeeklyDepartmentPlanningObjects.FirstWeekValTotal);

		pageDailyTradingStatementStorePO.DTSCompareValuesTotal(extentTest, DBFirstWk, FirstWk);
		pageMonthlyPlanningPO.CompareValues(extentTest, DBFirstWkTtl, FirstWkTtl);

		// Sales history - Week -2
		Report_AddStep("testcase", " SALES HISTORY - SECOND WEEK", "", "", "");
		extentTest = report.startTest("Sales History - Second Week");

		String SalesHistory2 = data.getText("SalesHistory");
		DBSecondWk = sql.CLRexecuteQuery(getContext(),
				SalesHistory2.replace("store", getContext().getStringProperty("store"))
						/*.replace("Week", String.valueOf(Wk3))*/.replace("Week", getContext().getStringProperty("Week2"))
						.replace("Department", getContext().getStringProperty("DepartmentToView"))
						.replace("Division", getContext().getStringProperty("Division")));
		System.out.println("DB Value: " + DBSecondWk);

		String SalesHistoryTotal2 = data.getText("SalesHistoryTotal");
		DBSecondWkTtl = sql.CLRexecuteQuery(getContext(),
				SalesHistoryTotal2.replace("store", getContext().getStringProperty("store"))
						/*.replace("Week", String.valueOf(Wk3))*/.replace("Week", getContext().getStringProperty("Week2"))
						.replace("Department", getContext().getStringProperty("DepartmentToView"))
						.replace("Division", getContext().getStringProperty("Division")));
		System.out.println("DB Total: " + DBSecondWkTtl);

		SecondWk = pageWeeklyDepartmentPlanningLLPO.UIResults(objWeeklyDepartmentPlanningObjects, "Sales history",
				objWeeklyDepartmentPlanningObjects.SecondWeekMonval,
				objWeeklyDepartmentPlanningObjects.SecondWeekTueval,
				objWeeklyDepartmentPlanningObjects.SecondWeekWedval,
				objWeeklyDepartmentPlanningObjects.SecondWeekThuval,
				objWeeklyDepartmentPlanningObjects.SecondWeekFrival,
				objWeeklyDepartmentPlanningObjects.SecondWeekSatval,
				objWeeklyDepartmentPlanningObjects.SecondWeekSunval,
				objWeeklyDepartmentPlanningObjects.SecondWeekCaption);
		SecondWkTtl = pageMonthlyPlanningPO.GetTotal(extentTest, objWeeklyDepartmentPlanningObjects.SecondWeekCaption,
				objWeeklyDepartmentPlanningObjects.SecondWeekValTotal);

		pageDailyTradingStatementStorePO.DTSCompareValuesTotal(extentTest, DBSecondWk, SecondWk);
		pageMonthlyPlanningPO.CompareValues(extentTest, DBSecondWkTtl, SecondWkTtl);

		// Sales history - Week -3
		Report_AddStep("testcase", " SALES HISTORY - THIRD WEEK", "", "", "");
		extentTest = report.startTest("Sales History - Third Week");

		String SalesHistory3 = data.getText("SalesHistory");
		DBThirdWk = sql.CLRexecuteQuery(getContext(),
				SalesHistory3.replace("store", getContext().getStringProperty("store"))
						/*.replace("Week", String.valueOf(Wk2))*/.replace("Week", getContext().getStringProperty("Week3"))
						.replace("Department", getContext().getStringProperty("DepartmentToView"))
						.replace("Division", getContext().getStringProperty("Division")));
		System.out.println("DB Value: " + DBThirdWk);

		String SalesHistoryTotal3 = data.getText("SalesHistoryTotal");
		DBThirdWkTtl = sql.CLRexecuteQuery(getContext(),
				SalesHistoryTotal3.replace("store", getContext().getStringProperty("store"))
						/*.replace("Week", String.valueOf(Wk2))*/.replace("Week", getContext().getStringProperty("Week3"))
						.replace("Department", getContext().getStringProperty("DepartmentToView"))
						.replace("Division", getContext().getStringProperty("Division")));
		System.out.println("DB Total: " + DBThirdWkTtl);

		ThirdWk = pageWeeklyDepartmentPlanningLLPO.UIResults(objWeeklyDepartmentPlanningObjects, "Sales history",
				objWeeklyDepartmentPlanningObjects.ThirdWeekMonval, objWeeklyDepartmentPlanningObjects.ThirdWeekTueval,
				objWeeklyDepartmentPlanningObjects.ThirdWeekWedval, objWeeklyDepartmentPlanningObjects.ThirdWeekThuval,
				objWeeklyDepartmentPlanningObjects.ThirdWeekFrival, objWeeklyDepartmentPlanningObjects.ThirdWeekSatval,
				objWeeklyDepartmentPlanningObjects.ThirdWeekSunval,
				objWeeklyDepartmentPlanningObjects.ThirdWeekCaption);
		ThirdWkTtl = pageMonthlyPlanningPO.GetTotal(extentTest, objWeeklyDepartmentPlanningObjects.ThirdWeekCaption,
				objWeeklyDepartmentPlanningObjects.ThirdWeekValTotal);

		pageDailyTradingStatementStorePO.DTSCompareValuesTotal(extentTest, DBThirdWk, ThirdWk);
		pageMonthlyPlanningPO.CompareValues(extentTest, DBThirdWkTtl, ThirdWkTtl);

		// Sales history - Week -4
		Report_AddStep("testcase", " SALES HISTORY - FOURTH WEEK", "", "", "");
		extentTest = report.startTest("Sales History - Fourth Week");

		String SalesHistory4 = data.getText("SalesHistory");
		DBFourthWk = sql.CLRexecuteQuery(getContext(),
				SalesHistory3.replace("store", getContext().getStringProperty("store"))
						/*.replace("Week", String.valueOf(Wk1))*/.replace("Week", getContext().getStringProperty("Week4"))
						.replace("Department", getContext().getStringProperty("DepartmentToView"))
						.replace("Division", getContext().getStringProperty("Division")));
		System.out.println("DB Value: " + DBFourthWk);

		String SalesHistoryTotal4 = data.getText("SalesHistoryTotal");
		DBFourthWkTtl = sql.CLRexecuteQuery(getContext(),
				SalesHistoryTotal3.replace("store", getContext().getStringProperty("store"))
						/*.replace("Week", String.valueOf(Wk1))*/.replace("Week", getContext().getStringProperty("Week4"))
						.replace("Department", getContext().getStringProperty("DepartmentToView"))
						.replace("Division", getContext().getStringProperty("Division")));
		System.out.println("DB Total: " + DBFourthWkTtl);

		FourthWk = pageWeeklyDepartmentPlanningLLPO.UIResults(objWeeklyDepartmentPlanningObjects, "Sales history",
				objWeeklyDepartmentPlanningObjects.FourthWeekMonval,
				objWeeklyDepartmentPlanningObjects.FourthWeekTueval,
				objWeeklyDepartmentPlanningObjects.FourthWeekWedval,
				objWeeklyDepartmentPlanningObjects.FourthWeekThuval,
				objWeeklyDepartmentPlanningObjects.FourthWeekFrival,
				objWeeklyDepartmentPlanningObjects.FourthWeekSatval,
				objWeeklyDepartmentPlanningObjects.FourthWeekSunval,
				objWeeklyDepartmentPlanningObjects.FourthWeekCaption);
		FourthWkTtl = pageMonthlyPlanningPO.GetTotal(extentTest, objWeeklyDepartmentPlanningObjects.FourthWeekCaption,
				objWeeklyDepartmentPlanningObjects.FourthWeekValTotal);

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
						.replace("Division", getContext().getStringProperty("Division"))
						.replace("Division", getContext().getStringProperty("Division")));
		System.out.println("DB Value: " + DBFourthWk);

		AvgMix = pageWeeklyDepartmentPlanningLLPO.UIResults(objWeeklyDepartmentPlanningObjects, "Sales history",
				objWeeklyDepartmentPlanningObjects.AvgMixMonval, objWeeklyDepartmentPlanningObjects.AvgMixTueval,
				objWeeklyDepartmentPlanningObjects.AvgMixWedval, objWeeklyDepartmentPlanningObjects.AvgMixThuval,
				objWeeklyDepartmentPlanningObjects.AvgMixFrival, objWeeklyDepartmentPlanningObjects.AvgMixSatval,
				objWeeklyDepartmentPlanningObjects.AvgMixSunval, objWeeklyDepartmentPlanningObjects.AvgDailyMixCaption);

		pageDailyTradingStatementStorePO.DTSCompareValuesTotal(extentTest, DBAvgMix, AvgMix);
		//Average Growth Vs LY%
		Report_AddStep("testcase", " Average Growth Vs LY Per", "", "", "");
		extentTest = report.startTest("Average Growth Vs LY Per");

		String AverageGrowthVsLYPer = data.getText("AverageGrowthVsLYPer");
		DBAvgGrowth = sql.CLRexecuteQuery(getContext(),
				AverageGrowthVsLYPer.replace("store", getContext().getStringProperty("store"))
					
						.replace("Department", getContext().getStringProperty("DepartmentToView"))
						.replace("Division", getContext().getStringProperty("Division"))
						/*.replace("WEEK1", getContext().getStringProperty("Week1")).replace("WEEK4", getContext().getStringProperty("Week4"))
						.replace("LYWK1", getContext().getStringProperty("LYWeek1")).replace("LYWK4", getContext().getStringProperty("LYWeek4"))*/
						.replace("WEEK1", String.valueOf(Wk4)).replace("WEEK4", String.valueOf(Wk1)).replace("LYWK1", String.valueOf(LYWk4)).replace("LYWK4", String.valueOf(LYWk1)));
		System.out.println("DB Value: " + DBAvgGrowth);
		

		Avggrowth = pageWeeklyDepartmentPlanningLLPO.UIResults(objWeeklyDepartmentPlanningObjects, "Sales history",
				objWeeklyDepartmentPlanningObjects.AvgGrowthMonval, objWeeklyDepartmentPlanningObjects.AvgGrowthTueval,
				objWeeklyDepartmentPlanningObjects.AvgGrowthWedval, objWeeklyDepartmentPlanningObjects.AvgGrowthThuval,
				objWeeklyDepartmentPlanningObjects.AvgGrowthFrival, objWeeklyDepartmentPlanningObjects.AvgGrowthSatval,
				objWeeklyDepartmentPlanningObjects.AvgGrowthSunval, objWeeklyDepartmentPlanningObjects.AvgGrowthCaption);

		pageDailyTradingStatementStorePO.DTSCompareValuesTotal(extentTest, DBAvgGrowth, Avggrowth);
	}

	///////////////////// PLANNING FOR THE WEEK /////////////////////////
	@Test(description = "Weekly Department Planning , Planning for the Week", priority = 3)
	public void PlanningfortheWeekValidation() throws Exception {
		pageWeeklyDepartmentPlanningLLPO = new WeeklyDepartmentPlanning().createPage(getDriver(), getContext(), log,
				status, data, getReport(), getReportLogger(), WeeklyDepartmentPlanningPage.class);
		pageMonthlyPlanningGroupPO = new MonthlyPlanningGroupPage().createPage(getDriver(), getContext(), log, status,
				data, getReport(), getReportLogger(), MonthlyPlanningGroupPage.class);
		pageMonthlyPlanningPO = new WeeklyDepartmentPlanning().createPage(getDriver(), getContext(), log, status, data,
				getReport(), getReportLogger(), MonthlyPlanningStorePage.class);
		TestStatus status = getTestStatus();
		System.out.println("Entering into TestCase : Weekly Planning Department");
		String DepartmentToView = getContext().getStringProperty("DepartmentToView");
       //Last Year sales 
		
		Report_AddStep("testcase", " LAST YEAR SALES - PLANNING FOR THE WEEK", "", "", "");
		extentTest = report.startTest("Last Year Sales");

		String LastYearSales = data.getText("LastYearSales");
		DBLastYearSls = sql.CLRexecuteQuery(getContext(),
				LastYearSales.replace("store", getContext().getStringProperty("store"))
						.replace("Week", getContext().getStringProperty("SelectFiscalWeek"))
						.replace("Department", getContext().getStringProperty("DepartmentToView"))
						.replace("Division", getContext().getStringProperty("Division")).replace("LYWk", getContext().getStringProperty("LYWk")));
		System.out.println("DB Value: " + DBLastYearSls);

		String LastYearSalesTotal = data.getText("LastYearSalesTotal");
		DBLastYearSlsTtl = sql.CLRexecuteQuery(getContext(),
				LastYearSalesTotal.replace("store", getContext().getStringProperty("store"))
						.replace("Week", getContext().getStringProperty("SelectFiscalWeek"))
						.replace("Department", getContext().getStringProperty("DepartmentToView")).replace("LYWk", getContext().getStringProperty("LYWk"))
						.replace("Division", getContext().getStringProperty("Division")));
		System.out.println("DB Total: " + DBLastYearSlsTtl);

		LastyearSls = pageWeeklyDepartmentPlanningLLPO.UIResults(objWeeklyDepartmentPlanningObjects, "Last Year Sales",
				objWeeklyDepartmentPlanningObjects.LastYearSalesMonVal,
				objWeeklyDepartmentPlanningObjects.LastYearSalesTueVal,
				objWeeklyDepartmentPlanningObjects.LastYearSalesWedVal,
				objWeeklyDepartmentPlanningObjects.LastYearSalesThuVal,
				objWeeklyDepartmentPlanningObjects.LastYearSalesFriVal,
				objWeeklyDepartmentPlanningObjects.LastYearSalesSatVal,
				objWeeklyDepartmentPlanningObjects.LastYearSalesSunVal,
				objWeeklyDepartmentPlanningObjects.LastYearSalesCaption);
		LastyearSlsTtl = pageMonthlyPlanningPO.GetTotal(extentTest,
				objWeeklyDepartmentPlanningObjects.LastYearSalesCaption,
				objWeeklyDepartmentPlanningObjects.LastYearSalesTotal);

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
				objWeeklyDepartmentPlanningObjects.GeneratedSalesMonVal,
				objWeeklyDepartmentPlanningObjects.GeneratedSalesTueVal,
				objWeeklyDepartmentPlanningObjects.GeneratedSalesWedVal,
				objWeeklyDepartmentPlanningObjects.GeneratedSalesThuVal,
				objWeeklyDepartmentPlanningObjects.GeneratedSalesFriVal,
				objWeeklyDepartmentPlanningObjects.GeneratedSalesSatVal,
				objWeeklyDepartmentPlanningObjects.GeneratedSalesSunVal,
				objWeeklyDepartmentPlanningObjects.GeneratedSalesCaption);
		GnrtSlsTtl = pageMonthlyPlanningPO.GetTotal(extentTest,
				objWeeklyDepartmentPlanningObjects.GeneratedSalesCaption,
				objWeeklyDepartmentPlanningObjects.GeneratedSalesTotal);

		pageDailyTradingStatementStorePO.DTSCompareValuesTotal(extentTest, DBGnrtSls, GnrtSls);
		pageMonthlyPlanningPO.CompareValues(extentTest, DBGnrtSlsTtl, GnrtSlsTtl);

		// PlannedSales
		Report_AddStep("testcase", " PLANNED SALES - PLANNING FOR THE WEEK", "", "", "");
		extentTest = report.startTest("Planned Sales");

		String PlannedSales = data.getText("PlannedSales");
		DBPlndSlsWk = sql.CLRexecuteQuery(getContext(),
				PlannedSales.replace("store", getContext().getStringProperty("store"))
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
				objWeeklyDepartmentPlanningObjects.PlannedSalesMonVal,
				objWeeklyDepartmentPlanningObjects.PlannedSalesTueVal,
				objWeeklyDepartmentPlanningObjects.PlannedSalesWedVal,
				objWeeklyDepartmentPlanningObjects.PlannedSalesThuVal,
				objWeeklyDepartmentPlanningObjects.PlannedSalesFriVal,
				objWeeklyDepartmentPlanningObjects.PlannedSalesSatVal,
				objWeeklyDepartmentPlanningObjects.PlannedSalesSunVal,
				objWeeklyDepartmentPlanningObjects.PlannedSalesCaption);
		PlndSlsWkTtl = pageWeeklyDepartmentPlanningLLPO.GetTotal(extentTest,
				objWeeklyDepartmentPlanningObjects.PlannedSalesCaption,
				objWeeklyDepartmentPlanningObjects.PlannedSalesTotal);

		pageDailyTradingStatementStorePO.DTSCompareValuesTotal(extentTest, DBPlndSlsWk, PlndSlsWk);
		pageMonthlyPlanningPO.CompareValues(extentTest, DBPlndSlsWkTtl, PlndSlsWkTtl);
        //Planned Sales VS LY%
		Report_AddStep("testcase", " Planned Sales VS LYPer - PLANNING FOR THE WEEK", "", "", "");
		extentTest = report.startTest("Planned Sales VS LYPer");

				String PlannedSalesVSLYPer = data.getText("PlannedSalesVSLYPer");
				DBPlannedSalesVSLYPer = sql.CLRexecuteQuery(getContext(),
						PlannedSalesVSLYPer.replace("store", getContext().getStringProperty("store"))
								.replace("Week", getContext().getStringProperty("SelectFiscalWeek"))
								.replace("Department", getContext().getStringProperty("DepartmentToView")).replace("LYWk", getContext().getStringProperty("LYWk"))
								.replace("Division", getContext().getStringProperty("Division")));
				System.out.println("DB Value: " + DBPlannedSalesVSLYPer);

				 
				PlannedSalesVSLY = pageWeeklyDepartmentPlanningLLPO.UIResults(objWeeklyDepartmentPlanningObjects, "Last Year Sales",
						objWeeklyDepartmentPlanningObjects.PlannedSalesVSLYMonVal,
						objWeeklyDepartmentPlanningObjects.PlannedSalesVSLYTueVal,
						objWeeklyDepartmentPlanningObjects.PlannedSalesVSLYWedVal,
						objWeeklyDepartmentPlanningObjects.PlannedSalesVSLYThuVal,
						objWeeklyDepartmentPlanningObjects.PlannedSalesVSLYFriVal,
						objWeeklyDepartmentPlanningObjects.PlannedSalesVSLYSatVal,
						objWeeklyDepartmentPlanningObjects.PlannedSalesVSLYSunVal,
						objWeeklyDepartmentPlanningObjects.PlannedSalesVSLYCaption);
				
				pageDailyTradingStatementStorePO.DTSCompareValuesTotal(extentTest, DBPlannedSalesVSLYPer, PlannedSalesVSLY);
				  //PlannedSalesDailyMix%		
				
				Report_AddStep("testcase", " PlannedSalesDailyMix - PLANNING FOR THE WEEK", "", "", "");
				extentTest = report.startTest("PlannedSalesDailyMix");

						String PlannedSalesDailyMix = data.getText("PlannedSalesDailyMix");
						DBPlannedSalesDailyMix = sql.CLRexecuteQuery(getContext(),
								PlannedSalesDailyMix.replace("store", getContext().getStringProperty("store"))
										.replace("Week", getContext().getStringProperty("SelectFiscalWeek"))
										.replace("Department", getContext().getStringProperty("DepartmentToView")).replace("LYWk", getContext().getStringProperty("LYWk"))
										.replace("Division", getContext().getStringProperty("Division")));
						System.out.println("DB Value: " + DBPlannedSalesDailyMix);

						

						PlannedSalesDailyMixper = pageWeeklyDepartmentPlanningLLPO.UIResults(objWeeklyDepartmentPlanningObjects, "PlannedSalesDailyMix",
								objWeeklyDepartmentPlanningObjects.PlannedSalesdailymixMonVal,
								objWeeklyDepartmentPlanningObjects.PlannedSalesdailymixTueVal,
								objWeeklyDepartmentPlanningObjects.PlannedSalesdailymixWedVal,
								objWeeklyDepartmentPlanningObjects.PlannedSalesdailymixThuVal,
								objWeeklyDepartmentPlanningObjects.PlannedSalesdailymixFriVal,
								objWeeklyDepartmentPlanningObjects.PlannedSalesdailymixSatVal,
								objWeeklyDepartmentPlanningObjects.PlannedSalesdailymixSunVal,
								objWeeklyDepartmentPlanningObjects.PlannedSalesVSLYCaption);
						
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
				"Generated Item Price", objWeeklyDepartmentPlanningObjects.GnrtItmPriceMonVal,
				objWeeklyDepartmentPlanningObjects.GnrtItmPriceTueVal,
				objWeeklyDepartmentPlanningObjects.GnrtItmPriceWedVal,
				objWeeklyDepartmentPlanningObjects.GnrtItmPriceThuVal,
				objWeeklyDepartmentPlanningObjects.GnrtItmPriceFriVal,
				objWeeklyDepartmentPlanningObjects.GnrtItmPriceSatVal,
				objWeeklyDepartmentPlanningObjects.GnrtItmPriceSunVal,
				objWeeklyDepartmentPlanningObjects.GeneratedItemPriceCaption);
		GnrtItmPrTtl = pageMonthlyPlanningPO.GetTotal(extentTest,
				objWeeklyDepartmentPlanningObjects.GeneratedItemPriceCaption,
				objWeeklyDepartmentPlanningObjects.GeneratedItemPriceTotal);

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
				objWeeklyDepartmentPlanningObjects.PlndItmPriceMonValue,
				objWeeklyDepartmentPlanningObjects.PlndItmPriceTueValue,
				objWeeklyDepartmentPlanningObjects.PlndItmPriceWedValue,
				objWeeklyDepartmentPlanningObjects.PlndItmPriceThuValue,
				objWeeklyDepartmentPlanningObjects.PlndItmPriceFriValue,
				objWeeklyDepartmentPlanningObjects.PlndItmPriceSatValue,
				objWeeklyDepartmentPlanningObjects.PlndItmPriceSunValue,
				objWeeklyDepartmentPlanningObjects.PlannedItemPriceCaption);
		PlndItmPrTtl = pageWeeklyDepartmentPlanningLLPO.GetTotal(extentTest,
				objWeeklyDepartmentPlanningObjects.PlannedItemPriceCaption,
				objWeeklyDepartmentPlanningObjects.PlannedItemPriceTotal);

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
				objWeeklyDepartmentPlanningObjects.GnrtItmMonVal, objWeeklyDepartmentPlanningObjects.GnrtItmTueVal,
				objWeeklyDepartmentPlanningObjects.GnrtItmWedVal, objWeeklyDepartmentPlanningObjects.GnrtItmThuVal,
				objWeeklyDepartmentPlanningObjects.GnrtItmFriVal, objWeeklyDepartmentPlanningObjects.GnrtItmSatVal,
				objWeeklyDepartmentPlanningObjects.GnrtItmSunVal,
				objWeeklyDepartmentPlanningObjects.GeneratedItemCaption);
		GnrtItmTtl = pageMonthlyPlanningPO.GetTotal(extentTest, objWeeklyDepartmentPlanningObjects.GeneratedItemCaption,
				objWeeklyDepartmentPlanningObjects.GeneratedItemTotal);

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
				objWeeklyDepartmentPlanningObjects.PlndItmMonVal, objWeeklyDepartmentPlanningObjects.PlndItmTueVal,
				objWeeklyDepartmentPlanningObjects.PlndItmWedVal, objWeeklyDepartmentPlanningObjects.PlndItmThuVal,
				objWeeklyDepartmentPlanningObjects.PlndItmFriVal, objWeeklyDepartmentPlanningObjects.PlndItmSatVal,
				objWeeklyDepartmentPlanningObjects.PlndItmSunVal,
				objWeeklyDepartmentPlanningObjects.PlannedItemsCaption);
		PlndItmTtl = pageMonthlyPlanningPO.GetTotal(extentTest, objWeeklyDepartmentPlanningObjects.PlannedItemsCaption,
				objWeeklyDepartmentPlanningObjects.PlannedItemsTotal);

		pageDailyTradingStatementStorePO.DTSCompareValuesTotal(extentTest, DBPlndItm, PlndItm);
		pageMonthlyPlanningPO.CompareValues(extentTest, DBPlndItmTtl, PlndItmTtl);

		// Planned Customers
		if (DepartmentToView.contains("90")) {
			Report_AddStep("testcase", " PLANNED CUSTOMERS - PLANNING FOR THE WEEK", "", "", "");
			extentTest = report.startTest("Planned Customers");

			String PlannedCustomer = data.getText("PlannedCustomer");
			DBPlndCust = sql.CLRexecuteQuery(getContext(),
					PlannedCustomer.replace("store", getContext().getStringProperty("store"))
							.replace("Week", getContext().getStringProperty("SelectFiscalWeek"))
							.replace("Department", getContext().getStringProperty("DepartmentToView"))
							.replace("Division", getContext().getStringProperty("Division")));
			System.out.println("DB Value: " + DBPlndCust);

			String PlannedCustomerTotal = data.getText("PlannedCustomerTotal");
			DBPlndCustTtl = sql.CLRexecuteQuery(getContext(),
					PlannedCustomerTotal.replace("store", getContext().getStringProperty("store"))
							.replace("Week", getContext().getStringProperty("SelectFiscalWeek"))
							.replace("Department", getContext().getStringProperty("DepartmentToView"))
							.replace("Division", getContext().getStringProperty("Division")));
			System.out.println("DB Total: " + DBPlndCustTtl);

			PlndCust = pageWeeklyDepartmentPlanningLLPO.UIResults(objWeeklyDepartmentPlanningObjects, "Planned Items",
					objWeeklyDepartmentPlanningObjects.PlndCustMonVal,
					objWeeklyDepartmentPlanningObjects.PlndCustTueVal,
					objWeeklyDepartmentPlanningObjects.PlndCustWedVal,
					objWeeklyDepartmentPlanningObjects.PlndCustThuVal,
					objWeeklyDepartmentPlanningObjects.PlndCustFriVal,
					objWeeklyDepartmentPlanningObjects.PlndCustSatVal,
					objWeeklyDepartmentPlanningObjects.PlndCustSunVal,
					objWeeklyDepartmentPlanningObjects.PlannedCustCaption);
			PlndCustTtl = pageMonthlyPlanningPO.GetTotal(extentTest,
					objWeeklyDepartmentPlanningObjects.PlannedCustCaption,
					objWeeklyDepartmentPlanningObjects.PlannedCustTotal);

			pageDailyTradingStatementStorePO.DTSCompareValuesTotal(extentTest, DBPlndCust, PlndCust);
			pageMonthlyPlanningPO.CompareValues(extentTest, DBPlndCustTtl, PlndCustTtl);
		} else {
			Report_AddStep("testcase", " NO PLANNED CUSTOMERS VIEW AVIALBLE FOR THIS DEPARTMENT", "", "", "");
			extentTest.log(LogStatus.INFO, "NO PLANNED CUSTOMERS VIEW AVIALBLE FOR THIS DEPARTMENT");
		} 

		// WeekType
		/*
		 * Report_AddStep("testcase"," WEEK TYPE - PLANNING FOR THE WEEK" ,"","", "");
		 * 
		 * String WeekType = data.getText("WeekType"); DBWkType =
		 * sql.CLRexecuteQuery(getContext(),
		 * WeekType.replace("store",getContext().getStringProperty("store")).replace(
		 * "Week",getContext().getStringProperty("SelectFiscalWeek"))
		 * .replace("Department",getContext().getStringProperty("DepartmentToView")).
		 * replace("Division",getContext().getStringProperty("Division")));
		 * System.out.println("DB Value: "+DBWkType);
		 * 
		 * WkType=pageMonthlyPlanningPO.GetTotal(extentTest,
		 * objWeeklyDepartmentPlanningObjects.WeekTypeCaption,
		 * objWeeklyDepartmentPlanningObjects.WeekTypeOption);
		 * pageMonthlyPlanningPO.CompareValues(extentTest,DBWkType,WkType);
		 */

		// SMS Hours
		Report_AddStep("testcase", " SMS HOURS - PLANNING FOR THE WEEK", "", "", "");
		extentTest = report.startTest("SMS Hours");

		String SMSHours = data.getText("SMSHours");
		DBSMSHr = sql.CLRexecuteQuery(getContext(),
				SMSHours.replace("store", getContext().getStringProperty("store"))
						.replace("Week", getContext().getStringProperty("SelectFiscalWeek"))
						.replace("Department", getContext().getStringProperty("DepartmentToView"))
						.replace("Division", getContext().getStringProperty("Division")));
		System.out.println("DB Value: " + DBSMSHr);

		String SMSHoursTotal = data.getText("SMSHoursTotal");
		DBSMSHrTtl = sql.CLRexecuteQuery(getContext(),
				SMSHoursTotal.replace("store", getContext().getStringProperty("store"))
						.replace("Week", getContext().getStringProperty("SelectFiscalWeek"))
						.replace("Department", getContext().getStringProperty("DepartmentToView"))
						.replace("Division", getContext().getStringProperty("Division")));
		System.out.println("DB Total: " + DBSMSHrTtl);

		if (DepartmentToView.contains("90")) {
			SMSHr = pageWeeklyDepartmentPlanningLLPO.UIResults(objWeeklyDepartmentPlanningObjects, "SMS Hours",
					objWeeklyDepartmentPlanningObjects.SMSHrsMonVal, objWeeklyDepartmentPlanningObjects.SMSHrsTueVal,
					objWeeklyDepartmentPlanningObjects.SMSHrsWedVal, objWeeklyDepartmentPlanningObjects.SMSHrsThuVal,
					objWeeklyDepartmentPlanningObjects.SMSHrsFriVal, objWeeklyDepartmentPlanningObjects.SMSHrsSatVal,
					objWeeklyDepartmentPlanningObjects.SMSHrsSunVal,
					objWeeklyDepartmentPlanningObjects.SMSHoursCaption);
			SMSHrTtl = pageMonthlyPlanningPO.GetTotal(extentTest, objWeeklyDepartmentPlanningObjects.SMSHoursCaption,
					objWeeklyDepartmentPlanningObjects.SMSHoursTotal);
		} else {
			SMSHr = pageWeeklyDepartmentPlanningLLPO.UIResults(objWeeklyDepartmentPlanningObjects, "SMS Hours",
					objWeeklyDepartmentPlanningObjects.NonCHeckoutSMSHrsMonVal,
					objWeeklyDepartmentPlanningObjects.NonCHeckoutSMSHrsTueVal,
					objWeeklyDepartmentPlanningObjects.NonCHeckoutSMSHrsWedVal,
					objWeeklyDepartmentPlanningObjects.NonCHeckoutSMSHrsThuVal,
					objWeeklyDepartmentPlanningObjects.NonCHeckoutSMSHrsFriVal,
					objWeeklyDepartmentPlanningObjects.NonCHeckoutSMSHrsSatVal,
					objWeeklyDepartmentPlanningObjects.NonCHeckoutSMSHrsSunVal,
					objWeeklyDepartmentPlanningObjects.NonCHeckoutSMSHoursCaption);
			SMSHrTtl = pageMonthlyPlanningPO.GetTotal(extentTest,
					objWeeklyDepartmentPlanningObjects.NonCHeckoutSMSHoursCaption,
					objWeeklyDepartmentPlanningObjects.NonCHeckoutSMSHoursTotal);
		}

		pageDailyTradingStatementStorePO.CompareValueDecimalTotal(extentTest, DBSMSHr, SMSHr);
		pageMonthlyPlanningPO.CompareValueDecimalFields(extentTest, DBSMSHrTtl, SMSHrTtl);

		// Kronos Hours
		Report_AddStep("testcase", " KRONOS HOURS - PLANNING FOR THE WEEK", "", "", "");
		extentTest = report.startTest("Kronos Hours");

		String KronosHours = data.getText("KronosHours");
		DBPlndHrWk = sql.CLRexecuteQuery(getContext(),
				KronosHours.replace("store", getContext().getStringProperty("store"))
						.replace("Week", getContext().getStringProperty("SelectFiscalWeek"))
						.replace("Department", getContext().getStringProperty("DepartmentToView"))
						.replace("Division", getContext().getStringProperty("Division")));
		System.out.println("DB Value: " + DBPlndHrWk);

		String KronosHoursHours = data.getText("KronosHoursHours");
		DBPlndHrWkTtl = sql.CLRexecuteQuery(getContext(),
				KronosHoursHours.replace("store", getContext().getStringProperty("store"))
						.replace("Week", getContext().getStringProperty("SelectFiscalWeek"))
						.replace("Department", getContext().getStringProperty("DepartmentToView"))
						.replace("Division", getContext().getStringProperty("Division")));
		System.out.println("DB Total: " + DBPlndHrWkTtl);

		if (DepartmentToView.contains("90")) {
			PlndHrWk = pageWeeklyDepartmentPlanningLLPO.UIResults(objWeeklyDepartmentPlanningObjects, "Kronos Hours",
					objWeeklyDepartmentPlanningObjects.PlndHrsMonVal, objWeeklyDepartmentPlanningObjects.PlndHrsTueVal,
					objWeeklyDepartmentPlanningObjects.PlndHrsWedVal, objWeeklyDepartmentPlanningObjects.PlndHrsThuVal,
					objWeeklyDepartmentPlanningObjects.PlndHrsFriVal, objWeeklyDepartmentPlanningObjects.PlndHrsSatVal,
					objWeeklyDepartmentPlanningObjects.PlndHrsSunVal,
					objWeeklyDepartmentPlanningObjects.PlndHrsCaption);
			PlndHrWkTtl = pageMonthlyPlanningPO.GetTotal(extentTest, objWeeklyDepartmentPlanningObjects.PlndHrsCaption,
					objWeeklyDepartmentPlanningObjects.PlndHrsTotal);
		} else {
			PlndHrWk = pageWeeklyDepartmentPlanningLLPO.UIResults(objWeeklyDepartmentPlanningObjects, "Kronos Hours",
					objWeeklyDepartmentPlanningObjects.NonCHeckoutPlndHrsMonVal,
					objWeeklyDepartmentPlanningObjects.NonCHeckoutPlndHrsTueVal,
					objWeeklyDepartmentPlanningObjects.NonCHeckoutPlndHrsWedVal,
					objWeeklyDepartmentPlanningObjects.NonCHeckoutPlndHrsThuVal,
					objWeeklyDepartmentPlanningObjects.NonCHeckoutPlndHrsFriVal,
					objWeeklyDepartmentPlanningObjects.NonCHeckoutPlndHrsSatVal,
					objWeeklyDepartmentPlanningObjects.NonCHeckoutPlndHrsSunVal,
					objWeeklyDepartmentPlanningObjects.NonCHeckoutPlndHrsCaption);
			PlndHrWkTtl = pageMonthlyPlanningPO.GetTotal(extentTest,
					objWeeklyDepartmentPlanningObjects.NonCHeckoutPlndHrsCaption,
					objWeeklyDepartmentPlanningObjects.NonCHeckoutPlndHrsTotal);
		}

		pageDailyTradingStatementStorePO.CompareValueDecimalTotal(extentTest, DBPlndHrWk, PlndHrWk);
		pageMonthlyPlanningPO.CompareValueDecimalFields(extentTest, DBPlndHrWkTtl, PlndHrWkTtl);

		// Planned Variance
		Report_AddStep("testcase", " PLANNED VARIANCE - PLANNING FOR THE WEEK", "", "", "");
		extentTest = report.startTest("Planned Variance");

		String PlanVariance = data.getText("PlanVariance");
		DBPlndVar = sql.CLRexecuteQuery(getContext(),
				PlanVariance.replace("store", getContext().getStringProperty("store"))
						.replace("Week", getContext().getStringProperty("SelectFiscalWeek"))
						.replace("Department", getContext().getStringProperty("DepartmentToView"))
						.replace("Division", getContext().getStringProperty("Division")));
		System.out.println("DB Value: " + DBPlndVar);

		String PlanVarianceTotal = data.getText("PlanVarianceTotal");
		DBPlndVarTtl = sql.CLRexecuteQuery(getContext(),
				PlanVarianceTotal.replace("store", getContext().getStringProperty("store"))
						.replace("Week", getContext().getStringProperty("SelectFiscalWeek"))
						.replace("Department", getContext().getStringProperty("DepartmentToView"))
						.replace("Division", getContext().getStringProperty("Division")));
		System.out.println("DB Total: " + DBPlndVarTtl);

		if (DepartmentToView.contains("90")) {
			PlndVar = pageWeeklyDepartmentPlanningLLPO.UIResults(objWeeklyDepartmentPlanningObjects, "Planned Variance",
					objWeeklyDepartmentPlanningObjects.PlndVarMonVal, objWeeklyDepartmentPlanningObjects.PlndVarTueVal,
					objWeeklyDepartmentPlanningObjects.PlndVarWedVal, objWeeklyDepartmentPlanningObjects.PlndVarThuVal,
					objWeeklyDepartmentPlanningObjects.PlndVarFriVal, objWeeklyDepartmentPlanningObjects.PlndVarSatVal,
					objWeeklyDepartmentPlanningObjects.PlndVarSunVal,
					objWeeklyDepartmentPlanningObjects.PlndVarCaption);
			PlndVarTtl = pageMonthlyPlanningPO.GetTotal(extentTest, objWeeklyDepartmentPlanningObjects.PlndVarCaption,
					objWeeklyDepartmentPlanningObjects.PlndVarTotal);
		} else {
			PlndVar = pageWeeklyDepartmentPlanningLLPO.UIResults(objWeeklyDepartmentPlanningObjects, "Planned Variance",
					objWeeklyDepartmentPlanningObjects.NonCHeckoutPlndVarMonVal,
					objWeeklyDepartmentPlanningObjects.NonCHeckoutPlndVarTueVal,
					objWeeklyDepartmentPlanningObjects.NonCHeckoutPlndVarWedVal,
					objWeeklyDepartmentPlanningObjects.NonCHeckoutPlndVarThuVal,
					objWeeklyDepartmentPlanningObjects.NonCHeckoutPlndVarFriVal,
					objWeeklyDepartmentPlanningObjects.NonCHeckoutPlndVarSatVal,
					objWeeklyDepartmentPlanningObjects.NonCHeckoutPlndVarSunVal,
					objWeeklyDepartmentPlanningObjects.NonCHeckoutPlndVarCaption);
			PlndVarTtl = pageMonthlyPlanningPO.GetTotal(extentTest,
					objWeeklyDepartmentPlanningObjects.NonCHeckoutPlndVarCaption,
					objWeeklyDepartmentPlanningObjects.NonCHeckoutPlndVarTotal);
		}

		pageDailyTradingStatementStorePO.CompareValueDecimalTotal(extentTest, DBPlndVar, PlndVar);
		pageMonthlyPlanningPO.CompareValueDecimalFields(extentTest, DBPlndVarTtl, PlndVarTtl);

		// Planned OR
		Report_AddStep("testcase", " PLANNED OR - PLANNING FOR THE WEEK", "", "", "");
		extentTest = report.startTest("Planned OR");

		String OperationalRatio = data.getText("OperationalRatio");
		DBORWk = sql.CLRexecuteQuery(getContext(),
				OperationalRatio.replace("store", getContext().getStringProperty("store"))
						.replace("Week", getContext().getStringProperty("SelectFiscalWeek"))
						.replace("Department", getContext().getStringProperty("DepartmentToView"))
						.replace("Division", getContext().getStringProperty("Division")));
		System.out.println("DB Value: " + DBORWk);

		String OperationalRatioTotal = data.getText("OperationalRatioTotal");
		DBORWkTtl = sql.CLRexecuteQuery(getContext(),
				OperationalRatioTotal.replace("store", getContext().getStringProperty("store"))
						.replace("Week", getContext().getStringProperty("SelectFiscalWeek"))
						.replace("Department", getContext().getStringProperty("DepartmentToView"))
						.replace("Division", getContext().getStringProperty("Division")));
		System.out.println("DB Total: " + DBORWkTtl);

		if (DepartmentToView.contains("90")) {
			ORWk = pageWeeklyDepartmentPlanningLLPO.UIResults(objWeeklyDepartmentPlanningObjects, "Operational Ratio",
					objWeeklyDepartmentPlanningObjects.PlndORMonVal, objWeeklyDepartmentPlanningObjects.PlndORTueVal,
					objWeeklyDepartmentPlanningObjects.PlndORWedVal, objWeeklyDepartmentPlanningObjects.PlndORThuVal,
					objWeeklyDepartmentPlanningObjects.PlndORFriVal, objWeeklyDepartmentPlanningObjects.PlndORSatVal,
					objWeeklyDepartmentPlanningObjects.PlndORSunVal, objWeeklyDepartmentPlanningObjects.PlndORCaption);
			ORWkTtl = pageMonthlyPlanningPO.GetTotal(extentTest, objWeeklyDepartmentPlanningObjects.PlndORCaption,
					objWeeklyDepartmentPlanningObjects.PlndORTotal);
		} else {
			ORWk = pageWeeklyDepartmentPlanningLLPO.UIResults(objWeeklyDepartmentPlanningObjects, "Operational Ratio",
					objWeeklyDepartmentPlanningObjects.NonCHeckoutPlndORMonVal,
					objWeeklyDepartmentPlanningObjects.NonCHeckoutPlndORTueVal,
					objWeeklyDepartmentPlanningObjects.NonCHeckoutPlndORWedVal,
					objWeeklyDepartmentPlanningObjects.NonCHeckoutPlndORThuVal,
					objWeeklyDepartmentPlanningObjects.NonCHeckoutPlndORFriVal,
					objWeeklyDepartmentPlanningObjects.NonCHeckoutPlndORSatVal,
					objWeeklyDepartmentPlanningObjects.NonCHeckoutPlndORSunVal,
					objWeeklyDepartmentPlanningObjects.NonCHeckoutPlndORCaption);
			ORWkTtl = pageMonthlyPlanningPO.GetTotal(extentTest,
					objWeeklyDepartmentPlanningObjects.NonCHeckoutPlndORCaption,
					objWeeklyDepartmentPlanningObjects.NonCHeckoutPlndORTotal);
		}

		pageDailyTradingStatementStorePO.CompareValueDecimalTotal(extentTest, DBORWk, ORWk);
		pageMonthlyPlanningPO.CompareValueDecimalFields(extentTest, DBORWkTtl, ORWkTtl);

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

		if (DepartmentToView.contains("90")) {
			WgCost = pageWeeklyDepartmentPlanningLLPO.UIResults(objWeeklyDepartmentPlanningObjects, "Wage Cost",
					objWeeklyDepartmentPlanningObjects.WgCostMonVal, objWeeklyDepartmentPlanningObjects.WgCostTueVal,
					objWeeklyDepartmentPlanningObjects.WgCostWedVal, objWeeklyDepartmentPlanningObjects.WgCostThuVal,
					objWeeklyDepartmentPlanningObjects.WgCostFriVal, objWeeklyDepartmentPlanningObjects.WgCostSatVal,
					objWeeklyDepartmentPlanningObjects.WgCostSunVal, objWeeklyDepartmentPlanningObjects.WgCostCaption);
			WgCostTtl = pageMonthlyPlanningPO.GetTotal(extentTest, objWeeklyDepartmentPlanningObjects.WgCostCaption,
					objWeeklyDepartmentPlanningObjects.WgCostTotal);
		} else {
			WgCost = pageWeeklyDepartmentPlanningLLPO.UIResults(objWeeklyDepartmentPlanningObjects, "Wage Cost",
					objWeeklyDepartmentPlanningObjects.NonCHeckoutWgCostMonVal,
					objWeeklyDepartmentPlanningObjects.NonCHeckoutWgCostTueVal,
					objWeeklyDepartmentPlanningObjects.NonCHeckoutWgCostWedVal,
					objWeeklyDepartmentPlanningObjects.NonCHeckoutWgCostThuVal,
					objWeeklyDepartmentPlanningObjects.NonCHeckoutWgCostFriVal,
					objWeeklyDepartmentPlanningObjects.NonCHeckoutWgCostSatVal,
					objWeeklyDepartmentPlanningObjects.NonCHeckoutWgCostSunVal,
					objWeeklyDepartmentPlanningObjects.NonCHeckoutWgCostCaption);
			WgCostTtl = pageMonthlyPlanningPO.GetTotal(extentTest,
					objWeeklyDepartmentPlanningObjects.NonCHeckoutWgCostCaption,
					objWeeklyDepartmentPlanningObjects.NonCHeckoutWgCostTotal);
		}

		pageDailyTradingStatementStorePO.DTSCompareValuesTotal(extentTest, DBWgCost, WgCost);
		pageMonthlyPlanningPO.CompareValueDecimalFields(extentTest, DBWgCostTtl, WgCostTtl);

	}

	// PST537
	/*
	 * @Test(description =
	 * "PlannedItemPrice , PlannedItemPriceValidation",priority=4) public void
	 * PlannedItemPriceValidation() throws Exception { String
	 * WeeklyDepartmentPlanning =
	 * "Weekly Department Planning , PlannedItemPrice Info"; extentTest =
	 * report.startTest("Weekly Department Planning , PlannedItemPrice Info");
	 * loginPage = new WoWLoginPage().createPage(getDriver(), getContext(), log,
	 * status, data,report,extentTest, WoWLoginPage.class);
	 * pageWeeklyDepartmentPlanningLLPO = new
	 * WeeklyDepartmentPlanning().createPage(getDriver(), getContext(), log, status,
	 * data,getReport(), getReportLogger(), WeeklyDepartmentPlanningPage.class);
	 * pageMonthlyPlanningGroupPO = new
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
	 * getStringProperty("WeeklyDepartmentPlanning"),extentTest);
	 * 
	 * //boolean isElementDisplayed = false;
	 * //SwitchFrame(objWeeklyDepartmentPlanningObjects.DataFrame);
	 * driver.switchTo().defaultContent();
	 * driver.switchTo().frame(driver.findElement(By.xpath(
	 * objWeeklyDepartmentPlanningObjects.DataFrameTwo)).replace("Division",
	 * getContext().getStringProperty("Division"))); //WebElement
	 * isElementDisplayed=driver.findElement(By.xpath(
	 * objWeeklyDepartmentPlanningObjects.PlanCompleteSelectedCheckBox));
	 * //WebElement textBoxElemnt =
	 * prepareWebElementWithDynamicXpathWithInt(DeptHdr);
	 * if(getContext().getStringProperty("DepartmentToView").contains("90") ||
	 * getContext().getStringProperty("DepartmentToView").contains("0") ) {
	 * pageWeeklyDepartmentPlanningLLPO.SelectDepartment(
	 * objWeeklyDepartmentPlanningObjects.DataFrame,
	 * objWeeklyDepartmentPlanningObjects.DepartmentHeader,
	 * objWeeklyDepartmentPlanningObjects.DepartmentDropdown,extentTest,getContext()
	 * .getStringProperty("DepartmentToView"));
	 * Report_AddStep("testcase"," PLANNED ITEM PRICE - PLANNING FOR THE WEEK"
	 * ,"","", ""); extentTest.log(LogStatus.INFO,
	 * "PLANNED ITEM PRICE - PLANNING FOR THE WEEK");
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
	 * ,objWeeklyDepartmentPlanningObjects.PlndItmPriceMonValue,
	 * objWeeklyDepartmentPlanningObjects.PlndItmPriceTueValue,
	 * objWeeklyDepartmentPlanningObjects.PlndItmPriceWedValue,
	 * objWeeklyDepartmentPlanningObjects.PlndItmPriceThuValue,
	 * objWeeklyDepartmentPlanningObjects.PlndItmPriceFriValue,
	 * objWeeklyDepartmentPlanningObjects.PlndItmPriceSatValue,
	 * objWeeklyDepartmentPlanningObjects.PlndItmPriceSunValue,
	 * objWeeklyDepartmentPlanningObjects.PlannedItemPriceCaption);
	 * PlndItmPrTtl=pageWeeklyDepartmentPlanningLLPO.GetTotal(extentTest,
	 * objWeeklyDepartmentPlanningObjects.PlannedItemPriceCaption,
	 * objWeeklyDepartmentPlanningObjects.PlannedItemPriceTotal);
	 * 
	 * pageDailyTradingStatementStorePO.CompareValueDecimalTotal(extentTest,
	 * DBPlndItmPr,PlndItmPr);
	 * pageMonthlyPlanningPO.CompareValueDecimalFields(extentTest,DBPlndItmPrTtl,
	 * PlndItmPrTtl);
	 * 
	 * } else if (driver.findElements(By.xpath(objWeeklyDepartmentPlanningObjects.
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
	 * ,objWeeklyDepartmentPlanningObjects.PlndItmPriceMonValue,
	 * objWeeklyDepartmentPlanningObjects.PlndItmPriceTueValue,
	 * objWeeklyDepartmentPlanningObjects.PlndItmPriceWedValue,
	 * objWeeklyDepartmentPlanningObjects.PlndItmPriceThuValue,
	 * objWeeklyDepartmentPlanningObjects.PlndItmPriceFriValue,
	 * objWeeklyDepartmentPlanningObjects.PlndItmPriceSatValue,
	 * objWeeklyDepartmentPlanningObjects.PlndItmPriceSunValue,
	 * objWeeklyDepartmentPlanningObjects.PlannedItemPriceCaption);
	 * PlndItmPrTtl=pageWeeklyDepartmentPlanningLLPO.GetTotal(extentTest,
	 * objWeeklyDepartmentPlanningObjects.PlannedItemPriceCaption,
	 * objWeeklyDepartmentPlanningObjects.PlannedItemPriceTotal);
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
	 * 
	 * click(objWeeklyDepartmentPlanningObjects.SaveButtoninPlanCompleteWeb);
	 * 
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
	 * ,objWeeklyDepartmentPlanningObjects.PlndItmPriceMonValue,
	 * objWeeklyDepartmentPlanningObjects.PlndItmPriceTueValue,
	 * objWeeklyDepartmentPlanningObjects.PlndItmPriceWedValue,
	 * objWeeklyDepartmentPlanningObjects.PlndItmPriceThuValue,
	 * objWeeklyDepartmentPlanningObjects.PlndItmPriceFriValue,
	 * objWeeklyDepartmentPlanningObjects.PlndItmPriceSatValue,
	 * objWeeklyDepartmentPlanningObjects.PlndItmPriceSunValue,
	 * objWeeklyDepartmentPlanningObjects.PlannedItemPriceCaption);
	 * PlndItmPrTtl=pageWeeklyDepartmentPlanningLLPO.GetTotal(extentTest,
	 * objWeeklyDepartmentPlanningObjects.PlannedItemPriceCaption,
	 * objWeeklyDepartmentPlanningObjects.PlannedItemPriceTotal);
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
			data.loadTestDataXML(System.getProperty("user.dir") + "\\TestData\\WLP\\WeeklyDepartmentPlanning.xml");
			customreport.createExtentReport();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

}
