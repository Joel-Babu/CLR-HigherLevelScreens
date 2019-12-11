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
import org.testng.annotations.Test;

import com.relevantcodes.extentreports.ExtentReports;
import com.relevantcodes.extentreports.ExtentTest;
import com.relevantcodes.extentreports.LogStatus;

import au.wow.WLPmain.objects.DTS_DepartmentObjects;
import au.wow.WLPmain.objects.DailyPlanningMainObjects;
import au.wow.WLPmain.objects.DailyTradingStatementObjects;
import au.wow.WLPmain.objects.MonthlyPlanningGroupObjects;
import au.wow.WLPmain.objects.MonthlyPlanningStoreObjects;
import au.wow.WLPmain.objects.WeeklyPlanningStoreObjects;
import au.wow.WLPmain.pages.CommonFunctions;
import au.wow.WLPmain.pages.DTS_DepartmentPage;
import au.wow.WLPmain.pages.DailyPlanningMainPage;
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

public class CR_05_DTS_Departments extends TestBase {
	protected Logger log = LogManager.getLogger(CR_05_DTS_Departments.class);
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
	BasePage BaseObj;
	CommonFunctions common;
	StoreProperties storeprop;
	String OptionToView, WeekToTest, DepartmentToView;
	List<List<Object>> UIValues, UITotal;
	List<List<Object>> DBPlndValTtl, DBActlTtl, DBVARValTtl, DBLYValTtl, DBDailyMixValTtl, DBPlanSMSValTtl,
			DBKronosScheduledHoursValTtl, DBVARHoursValTtl, DBActlSMSHoursValTtl, DBPlanItemsValTtl,
			DBActualItemsValTtl, DBVARItemsValTtl, DBKronosActlSMSHoursValTtl, DBVARActualHoursValTtl, DBORHoursValTtl,
			DBPlanCustomersTtl, DBActlCustomersValTtl, DBVARCustomersValTtl, DBPlnWagesValTtl, DBActlWagesValTtl,
			DBVARPlanVsActualWageValTtl, DBWagesPlanpercentValTtl, DBWagesactualpercentValTtl;
	List<List<Object>> DBPlndVal, DBActlVal, DBVARVal, DBLYVal, DBDailyMixVal, DBPlanSMSVal, DBKronosScheduledHoursVal,
			DBVARHoursVal, DBActlSMSHoursVal, DBPlanItemsVal, DBActualItemsVal, DBVARItemsVal, DBKronosActlSMSHoursVal,
			KronosActlSMSHours, DBVARActualHoursVal, DBORHoursVal, DBPlnCustomersVal, DBActlCustomersVal,
			DBVARCustomersVal, DBPlnWagesVal, DBActlWagesVal, DBVARPlanVsActualWageVal, DBWagesPlanpercentVal,
			DBWagesactualpercentVal;
	List<List<Object>> DBBudSalesValTtl, DBVartoBudPerSalesTotalValTtl, DBPertoBudSalesValTtl, DBBudWageValTtl,
			DBBudPerValTtl, DBAllowBudWagesValTtl, DBActlORTtl, DBActlORVal;

	public CR_05_DTS_Departments() {
		super.log = log;
	}

	@Test(priority = 0)
	public void LogintoCLRApplication() throws Exception {
		loginPage = new WoWLoginPage().createPage(getDriver(), getContext(), log, status, data, report, extentTest,
				WoWLoginPage.class);
		pageWeeklyPlanningPO = loginPage.navToWeeklyPlanningStorePg(getContext().getStringProperty("store"));
	}

	//////////// Sales ///////////////
	@Test(description = "Daily Trading Statement - Department, Sales Values", priority = 1)
	public void SalesColumnValidation_DTSDepartment() throws Exception {
		pageDTSDepartmentPO = new DTS_DepartmentPage().createPage(getDriver(), getContext(), log, status, data,
				getReport(), getReportLogger(), DTS_DepartmentPage.class);
		pageWeeklyPlanningPO = new WeeklyPlanningStorePage().createPage(getDriver(), getContext(), log, status, data,
				getReport(), getReportLogger(), WeeklyPlanningStorePage.class);
		pageDailyTradingStatementStorePO = new MonthlyPlanningGroupPage().createPage(getDriver(), getContext(), log,
				status, data, getReport(), getReportLogger(), DailyTradingStatementStorePage.class);
		TestStatus status = getTestStatus();
		System.out.println("Welcome " + getContext().getStringProperty("username"));
		System.out.println("Entering into TestCase : DTS Department sales");
		pageWeeklyPlanningPO.SelectWeekFromDropdown(objWeeklyPlanningStoreObjects, extentTest,
				getContext().getStringProperty("SelectFiscalWeek"));

		pageWeeklyPlanningPO.selectPage(extentTest, objWeeklyPlanningStoreObjects.menuBar,
				objWeeklyPlanningStoreObjects.tradingScreens, "Trading Screens");
		pageWeeklyPlanningPO.selectPage(extentTest, objWeeklyPlanningStoreObjects.menuBar,
				objWeeklyPlanningStoreObjects.dailyTrading, "Daily Trading");
		pageWeeklyPlanningPO.selectPage(extentTest, objWeeklyPlanningStoreObjects.menuBar,
				objWeeklyPlanningStoreObjects.screenToViewDailyTrading, "Department");
		/*pageWeeklyPlanningPO.verifyPageName(extentTest, objWeeklyPlanningStoreObjects.DataFrame,
				objWeeklyPlanningStoreObjects.pageName, getContext().getStringProperty("DTS_Department"));*/

		pageDTSDepartmentPO.SelectDepartment(objDTSDepartmentObjects, extentTest,
				getContext().getStringProperty("DepartmentToView"));
		OptionToView = getContext().getStringProperty("OptionToView");

		// Planned Sales
		Report_AddStep("testcase", " PLANNED SALES - DAY WISE DATA", "", "", "");
		extentTest = report.startTest("Planned Sales");
		
		String PlannedSales = data.getText("PlannedSales");
		DBPlndVal = sql.CLRexecuteQuery(getContext(),
				PlannedSales.replace("store", getContext().getStringProperty("store"))
						.replace("Week", getContext().getStringProperty("SelectFiscalWeek"))
						.replace("DepartmentToView", getContext().getStringProperty("DepartmentToView")).replace("Division", getContext().getStringProperty("Division")).replace("Division", getContext().getStringProperty("Division")).
						replace("FinalResult", getContext().getStringProperty("FinalResult")));
		System.out.println("DB Value: " + DBPlndVal);

		String PlannedSalesTotal = data.getText("PlannedSalesTotal");
		DBPlndValTtl = sql.CLRexecuteQuery(getContext(),
				PlannedSalesTotal.replace("store", getContext().getStringProperty("store"))
						.replace("Week", getContext().getStringProperty("SelectFiscalWeek")).replace("Division", getContext().getStringProperty("Division")).
						replace("DepartmentToView", getContext().getStringProperty("DepartmentToView"))
						.replace("FinalResult", getContext().getStringProperty("FinalResult")));
		System.out.println("DB Value: " + DBPlndValTtl);

		UIValues = pageDTSDepartmentPO.UIResults(objDTSDepartmentObjects, extentTest,
				objDTSDepartmentObjects.PlannedSales, objDTSDepartmentObjects.PlannedSalesGreen,
				objDTSDepartmentObjects.TableRowsCount);
		pageWeeklyPlanningPO.CompareValues(extentTest, DBPlndVal, UIValues);

		UITotal = pageDTSDepartmentPO.GetTotal(extentTest, objDTSDepartmentObjects.RowNameTotal,
				objDTSDepartmentObjects.PlannedSalesTot);
		pageMonthlyPlanningPO.CompareValues(extentTest, DBPlndValTtl, UITotal);

		// Actual Sales
		Report_AddStep("testcase", " ACTUAL SALES - DAY WISE DATA", "", "", "");
		extentTest = report.startTest("Actual Sales");
		
		String ActualSales = data.getText("ActualSales");
		DBActlVal = sql.CLRexecuteQuery(getContext(),
				ActualSales.replace("store", getContext().getStringProperty("store"))
						.replace("Week", getContext().getStringProperty("SelectFiscalWeek"))
						.replace("DepartmentToView", getContext().getStringProperty("DepartmentToView")).replace("Division", getContext().getStringProperty("Division")).replace("Division", getContext().getStringProperty("Division"))
						.replace("FinalResult", getContext().getStringProperty("FinalResult")));
		System.out.println("DB Value: " + DBActlVal);

		String ActualSalesTotal = data.getText("ActualSalesTotal");
		DBActlTtl = sql.CLRexecuteQuery(getContext(),
				ActualSalesTotal.replace("store", getContext().getStringProperty("store"))
						.replace("Week", getContext().getStringProperty("SelectFiscalWeek"))
						.replace("DepartmentToView", getContext().getStringProperty("DepartmentToView")).replace("Division", getContext().getStringProperty("Division")).replace("Division", getContext().getStringProperty("Division"))
						.replace("FinalResult", getContext().getStringProperty("FinalResult")));
		System.out.println("DB Value: " + DBActlTtl);

		UIValues = pageDTSDepartmentPO.UIResults(objDTSDepartmentObjects, extentTest,
				objDTSDepartmentObjects.ActualSales, objDTSDepartmentObjects.PlannedSalesGreen,
				objDTSDepartmentObjects.TableRowsCount);
		pageWeeklyPlanningPO.CompareValues(extentTest, DBActlVal, UIValues);

		UITotal = pageDTSDepartmentPO.GetTotal(extentTest, objDTSDepartmentObjects.RowNameTotal,
				objDTSDepartmentObjects.ActualSalesTot);
		pageMonthlyPlanningPO.CompareValues(extentTest, DBActlTtl, UITotal);

		// Variance Sales
		Report_AddStep("testcase", " VARIANCE SALES - DAY WISE DATA", "", "", "");
		extentTest = report.startTest("Variance Sales");
		
		String VARSales = data.getText("VARSales");
		DBVARVal = sql.CLRexecuteQuery(getContext(),
				VARSales.replace("store", getContext().getStringProperty("store"))
						.replace("Week", getContext().getStringProperty("SelectFiscalWeek"))
						.replace("DepartmentToView", getContext().getStringProperty("DepartmentToView")).replace("Division", getContext().getStringProperty("Division")).replace("Division", getContext().getStringProperty("Division"))
						.replace("FinalResult", getContext().getStringProperty("FinalResult")));
		System.out.println("DB Value: " + DBVARVal);

		String VARSalesTotal = data.getText("VARSalesTotal");
		DBVARValTtl = sql.CLRexecuteQuery(getContext(),
				VARSalesTotal.replace("store", getContext().getStringProperty("store"))
						.replace("Week", getContext().getStringProperty("SelectFiscalWeek"))
						.replace("DepartmentToView", getContext().getStringProperty("DepartmentToView")).replace("Division", getContext().getStringProperty("Division"))
						.replace("FinalResult", getContext().getStringProperty("FinalResult")));
		System.out.println("DB Value: " + DBVARValTtl);

		UIValues = pageDTSDepartmentPO.UIResults(objDTSDepartmentObjects, extentTest, objDTSDepartmentObjects.VarSales,
				objDTSDepartmentObjects.PlannedSalesGreen, objDTSDepartmentObjects.TableRowsCount);
		pageWeeklyPlanningPO.CompareValues(extentTest, DBVARVal, UIValues);

		UITotal = pageDTSDepartmentPO.GetTotal(extentTest, objDTSDepartmentObjects.RowNameTotal,
				objDTSDepartmentObjects.VARSalesTot);
		pageMonthlyPlanningPO.CompareValues(extentTest, DBVARValTtl, UITotal);

		// LY Sales
		Report_AddStep("testcase", " LY SALES - DAY WISE DATA", "", "", "");
		extentTest = report.startTest("LY Sales");
		
		String LYSales = data.getText("LYSales");
		DBLYVal = sql.CLRexecuteQuery(getContext(),
				LYSales.replace("store", getContext().getStringProperty("store"))
						.replace("Week", getContext().getStringProperty("SelectFiscalWeek"))
						.replace("DepartmentToView", getContext().getStringProperty("DepartmentToView")).replace("Division", getContext().getStringProperty("Division"))
						.replace("FinalResult", getContext().getStringProperty("FinalResult"))
						.replace("LYWk", getContext().getStringProperty("LYWk")));

		System.out.println("DB Value: " + DBLYVal);

		String LYSalesTotal = data.getText("LYSalesTotal");
		DBLYValTtl = sql.CLRexecuteQuery(getContext(),
				LYSalesTotal.replace("store", getContext().getStringProperty("store"))
						.replace("Week", getContext().getStringProperty("SelectFiscalWeek"))
						.replace("DepartmentToView", getContext().getStringProperty("DepartmentToView")).replace("Division", getContext().getStringProperty("Division"))
						.replace("FinalResult", getContext().getStringProperty("FinalResult"))
						.replace("LYWk", getContext().getStringProperty("LYWk")));

		System.out.println("DB Value: " + DBLYValTtl);

		UIValues = pageDTSDepartmentPO.UIResults(objDTSDepartmentObjects, extentTest,
				objDTSDepartmentObjects.pertoLYSales, objDTSDepartmentObjects.pertoLYSales,
				objDTSDepartmentObjects.TableRowsCount);
		pageWeeklyPlanningPO.CompareValueDecimalFields(extentTest, DBLYVal, UIValues);

		UITotal = pageDTSDepartmentPO.GetTotal(extentTest, objDTSDepartmentObjects.RowNameTotal,
				objDTSDepartmentObjects.PerToLYSalesTot);
		pageMonthlyPlanningPO.CompareValueDecimalFields(extentTest, DBLYValTtl, UITotal);

		// Daily Mix_Sales
		Report_AddStep("testcase", " Daily Mix SALES - DAY WISE DATA", "", "", "");
		extentTest = report.startTest("Daily Mix Sales");
		
		String DailyMixSales = data.getText("DailyMixSales");
		DBDailyMixVal = sql.CLRexecuteQuery(getContext(),
				DailyMixSales.replace("store", getContext().getStringProperty("store"))
						.replace("Week", getContext().getStringProperty("SelectFiscalWeek"))
						.replace("DepartmentToView", getContext().getStringProperty("DepartmentToView")).replace("Division", getContext().getStringProperty("Division"))
						.replace("FinalResult", getContext().getStringProperty("FinalResult"))
						.replace("LYWk", getContext().getStringProperty("LYWk")));

		System.out.println("DB Value: " + DBDailyMixVal);

		String DailyMixSalesTotal = data.getText("DailyMixSalesTotal");
		DBDailyMixValTtl = sql.CLRexecuteQuery(getContext(),
				DailyMixSalesTotal.replace("store", getContext().getStringProperty("store"))
						.replace("Week", getContext().getStringProperty("SelectFiscalWeek"))
						.replace("DepartmentToView", getContext().getStringProperty("DepartmentToView")).replace("Division", getContext().getStringProperty("Division"))
						.replace("FinalResult", getContext().getStringProperty("FinalResult"))
						.replace("LYWk", getContext().getStringProperty("LYWk")));

		System.out.println("DB Value: " + DBDailyMixValTtl);

		UIValues = pageDTSDepartmentPO.UIResults(objDTSDepartmentObjects, extentTest,
				objDTSDepartmentObjects.DailyMixSales, objDTSDepartmentObjects.DailyMixSales,
				objDTSDepartmentObjects.TableRowsCount);
		pageWeeklyPlanningPO.CompareValues(extentTest, DBDailyMixVal, UIValues);

		UITotal = pageDTSDepartmentPO.GetTotal(extentTest, objDTSDepartmentObjects.RowNameTotal,
				objDTSDepartmentObjects.DailyMixTot);
		pageMonthlyPlanningPO.CompareValues(extentTest, DBDailyMixValTtl, UITotal);

		// Budget Sales Total
		if (OptionToView.contains("Budget")) {
			Report_AddStep("testcase", " BUDGET SALES - DAY WISE DATA", "", "", "");
			extentTest = report.startTest("Budget Sales");
			
			String BudgetSalesTotal = data.getText("BudgetSalesTotal");
			DBBudSalesValTtl = sql.CLRexecuteQuery(getContext(),
					BudgetSalesTotal.replace("store", getContext().getStringProperty("store"))
							.replace("Week", getContext().getStringProperty("SelectFiscalWeek"))
							.replace("DepartmentToView", getContext().getStringProperty("DepartmentToView")).replace("Division", getContext().getStringProperty("Division"))
							.replace("FinalResult", getContext().getStringProperty("FinalResult"))
							.replace("LYWk", getContext().getStringProperty("LYWk")));
			System.out.println("DB Value: " + DBBudSalesValTtl);

			UITotal = pageDTSDepartmentPO.GetTotal(extentTest, objDTSDepartmentObjects.RowNameTotal,
					objDTSDepartmentObjects.BudSalesTot);
			pageMonthlyPlanningPO.CompareValues(extentTest, DBBudSalesValTtl, UITotal);
		}
		if (OptionToView.contains("Forecast")) {
			Report_AddStep("testcase", " FORECAST SALES - DAY WISE DATA", "", "", "");
			extentTest = report.startTest("Forecast Sales");
			
			String ForecastSalesTotal = data.getText("ForecastSalesTotal");
			DBBudSalesValTtl = sql.CLRexecuteQuery(getContext(),
					ForecastSalesTotal.replace("store", getContext().getStringProperty("store"))
							.replace("Week", getContext().getStringProperty("SelectFiscalWeek"))
							.replace("DepartmentToView", getContext().getStringProperty("DepartmentToView")).replace("Division", getContext().getStringProperty("Division"))
							.replace("FinalResult", getContext().getStringProperty("FinalResult"))
							.replace("LYWk", getContext().getStringProperty("LYWk")));
			System.out.println("DB Value: " + DBBudSalesValTtl);

			UITotal = pageDTSDepartmentPO.GetTotal(extentTest, objDTSDepartmentObjects.RowNameTotal,
					objDTSDepartmentObjects.FcstSalesTot);
			pageMonthlyPlanningPO.CompareValues(extentTest, DBBudSalesValTtl, UITotal);
		}

		// VAR to Bud % Sales
		if (OptionToView.contains("Budget")) {
			Report_AddStep("testcase", " VARIANCE TO BUDGET SALES - DAY WISE DATA", "", "", "");
			extentTest = report.startTest("Variance to Budget Sales");
			
			String VartoBudPerSalesTotal = data.getText("VartoBudPerSalesTotal");
			DBVartoBudPerSalesTotalValTtl = sql.CLRexecuteQuery(getContext(),
					VartoBudPerSalesTotal.replace("store", getContext().getStringProperty("store"))
							.replace("Week", getContext().getStringProperty("SelectFiscalWeek"))
							.replace("DepartmentToView", getContext().getStringProperty("DepartmentToView")).replace("Division", getContext().getStringProperty("Division"))
							.replace("FinalResult", getContext().getStringProperty("FinalResult"))
							.replace("LYWk", getContext().getStringProperty("LYWk")));
			System.out.println("DB Value: " + DBVartoBudPerSalesTotalValTtl);

			UITotal = pageDTSDepartmentPO.GetTotal(extentTest, objDTSDepartmentObjects.RowNameTotal,
					objDTSDepartmentObjects.VarToBudSalesTot);
			pageMonthlyPlanningPO.CompareValues(extentTest, DBVartoBudPerSalesTotalValTtl, UITotal);
		}
		if (OptionToView.contains("Forecast")) {
			Report_AddStep("testcase", " VARIANCE TO FORECAST SALES - DAY WISE DATA", "", "", "");
			extentTest = report.startTest("Variance to Forecast Sales");
			
			String VartoFcstPerSalesTotal = data.getText("VartoFcstPerSalesTotal");
			DBVartoBudPerSalesTotalValTtl = sql.CLRexecuteQuery(getContext(),
					VartoFcstPerSalesTotal.replace("store", getContext().getStringProperty("store"))
							.replace("Week", getContext().getStringProperty("SelectFiscalWeek"))
							.replace("DepartmentToView", getContext().getStringProperty("DepartmentToView")).replace("Division", getContext().getStringProperty("Division"))
							.replace("FinalResult", getContext().getStringProperty("FinalResult"))
							.replace("LYWk", getContext().getStringProperty("LYWk")));
			System.out.println("DB Value: " + DBVartoBudPerSalesTotalValTtl);

			UITotal = pageDTSDepartmentPO.GetTotal(extentTest, objDTSDepartmentObjects.RowNameTotal,
					objDTSDepartmentObjects.VarToFcstSalesTot);
			pageMonthlyPlanningPO.CompareValues(extentTest, DBVartoBudPerSalesTotalValTtl, UITotal);
		}

		// % to Budget
		if (OptionToView.contains("Budget")) {
			Report_AddStep("testcase", " BUDGET PERCENTAGE SALES - DAY WISE DATA", "", "", "");
			extentTest = report.startTest("Budget Percentage Sales");
			
			String PertoBudSalesTotal = data.getText("PertoBudSalesTotal");
			DBPertoBudSalesValTtl = sql.CLRexecuteQuery(getContext(),
					PertoBudSalesTotal.replace("store", getContext().getStringProperty("store"))
							.replace("Week", getContext().getStringProperty("SelectFiscalWeek"))
							.replace("DepartmentToView", getContext().getStringProperty("DepartmentToView")).replace("Division", getContext().getStringProperty("Division"))
							.replace("FinalResult", getContext().getStringProperty("FinalResult"))
							.replace("LYWk", getContext().getStringProperty("LYWk")));

			System.out.println("DB Value: " + DBPertoBudSalesValTtl);

			UITotal = pageDTSDepartmentPO.GetTotal(extentTest, objDTSDepartmentObjects.RowNameTotal,
					objDTSDepartmentObjects.PerToBudSalesTot);
			pageMonthlyPlanningPO.CompareValues(extentTest, DBPertoBudSalesValTtl, UITotal);
		}
		if (OptionToView.contains("Forecast")) {
			Report_AddStep("testcase", " FORECAST PERCENTAGE SALES - DAY WISE DATA", "", "", "");
			extentTest = report.startTest("Forecast Percentage Sales");
			
			String PertoFcstSalesTotal = data.getText("PertoFcstSalesTotal");
			DBPertoBudSalesValTtl = sql.CLRexecuteQuery(getContext(),
					PertoFcstSalesTotal.replace("store", getContext().getStringProperty("store"))
							.replace("Week", getContext().getStringProperty("SelectFiscalWeek"))
							.replace("DepartmentToView", getContext().getStringProperty("DepartmentToView")).replace("Division", getContext().getStringProperty("Division"))
							.replace("FinalResult", getContext().getStringProperty("FinalResult"))
							.replace("LYWk", getContext().getStringProperty("LYWk")));

			System.out.println("DB Value: " + DBPertoBudSalesValTtl);

			UITotal = pageDTSDepartmentPO.GetTotal(extentTest, objDTSDepartmentObjects.RowNameTotal,
					objDTSDepartmentObjects.PerToFcstSalesTot);
			pageMonthlyPlanningPO.CompareValues(extentTest, DBPertoBudSalesValTtl, UITotal);
		}

	}

	//////////// HOURS ///////////////
	@Test(description = "Daily Trading Statement - Department, Hours Values", priority = 2)
	public void HoursColumnValidation_DTSDepartment() throws Exception {
		pageDTSDepartmentPO = new DTS_DepartmentPage().createPage(getDriver(), getContext(), log, status, data,
				getReport(), getReportLogger(), DTS_DepartmentPage.class);
		pageWeeklyPlanningPO = new WeeklyPlanningStorePage().createPage(getDriver(), getContext(), log, status, data,
				getReport(), getReportLogger(), WeeklyPlanningStorePage.class);
		pageDailyTradingStatementStorePO = new MonthlyPlanningGroupPage().createPage(getDriver(), getContext(), log,
				status, data, getReport(), getReportLogger(), DailyTradingStatementStorePage.class);
		TestStatus status = getTestStatus();
		System.out.println("Welcome " + getContext().getStringProperty("username"));
		System.out.println("Entering into TestCase : DTS Department");

		// Plan SMS Hours
		Report_AddStep("testcase", " PLAN SMS HOURS - DAY WISE DATA", "", "", "");
		extentTest = report.startTest("Plan SMS Hours");
		
		String PlanSMSHours = data.getText("PlanSMSHours");
		DBPlanSMSVal = sql.CLRexecuteQuery(getContext(),
				PlanSMSHours.replace("store", getContext().getStringProperty("store"))
						.replace("Week", getContext().getStringProperty("SelectFiscalWeek"))
						.replace("DepartmentToView", getContext().getStringProperty("DepartmentToView")).replace("Division", getContext().getStringProperty("Division"))
						.replace("FinalResult", getContext().getStringProperty("FinalResult"))
						.replace("LYWk", getContext().getStringProperty("LYWk")));

		System.out.println("DB Value: " + DBPlanSMSVal);

		String PlanSMSTotal = data.getText("PlanSMSTotal");
		DBPlanSMSValTtl = sql.CLRexecuteQuery(getContext(),
				PlanSMSTotal.replace("store", getContext().getStringProperty("store"))
						.replace("Week", getContext().getStringProperty("SelectFiscalWeek"))
						.replace("DepartmentToView", getContext().getStringProperty("DepartmentToView")).replace("Division", getContext().getStringProperty("Division"))
						.replace("FinalResult", getContext().getStringProperty("FinalResult"))
						.replace("LYWk", getContext().getStringProperty("LYWk")));

		System.out.println("DB Value: " + DBPlanSMSValTtl);

		UIValues = pageDTSDepartmentPO.UIResults(objDTSDepartmentObjects, extentTest, objDTSDepartmentObjects.PlanSMS,
				objDTSDepartmentObjects.PlannedSalesGreen, objDTSDepartmentObjects.TableRowsCount);
		pageWeeklyPlanningPO.CompareValues(extentTest, DBPlanSMSVal, UIValues);

		UITotal = pageDTSDepartmentPO.GetTotal(extentTest, objDTSDepartmentObjects.RowNameTotal,
				objDTSDepartmentObjects.PlanSMSHoursTot);
		pageMonthlyPlanningPO.CompareValues(extentTest, DBPlanSMSValTtl, UITotal);

		// Kronos Scheduled Hours
		Report_AddStep("testcase", " KRONOS SCHEDULED HOURS - DAY WISE DATA", "", "", "");
		extentTest = report.startTest("Kronos Scheduled Hours");
		
		String KronosScheduledHours = data.getText("KronosScheduledHours");
		DBKronosScheduledHoursVal = sql.CLRexecuteQuery(getContext(),
				KronosScheduledHours.replace("store", getContext().getStringProperty("store"))
						.replace("Week", getContext().getStringProperty("SelectFiscalWeek"))
						.replace("DepartmentToView", getContext().getStringProperty("DepartmentToView")).replace("Division", getContext().getStringProperty("Division"))
						.replace("FinalResult", getContext().getStringProperty("FinalResult"))
						.replace("LYWk", getContext().getStringProperty("LYWk")));

		System.out.println("DB Value: " + DBKronosScheduledHoursVal);

		String KronosScheduledHoursTotal = data.getText("KronosScheduledHoursTotal");
		DBKronosScheduledHoursValTtl = sql.CLRexecuteQuery(getContext(),
				KronosScheduledHoursTotal.replace("store", getContext().getStringProperty("store"))
						.replace("Week", getContext().getStringProperty("SelectFiscalWeek"))
						.replace("DepartmentToView", getContext().getStringProperty("DepartmentToView")).replace("Division", getContext().getStringProperty("Division"))
						.replace("FinalResult", getContext().getStringProperty("FinalResult"))
						.replace("LYWk", getContext().getStringProperty("LYWk")));

		System.out.println("DB Value: " + DBKronosScheduledHoursValTtl);

		UIValues = pageDTSDepartmentPO.UIResults(objDTSDepartmentObjects, extentTest,
				objDTSDepartmentObjects.KronosScheduledHours, objDTSDepartmentObjects.PlannedSalesGreen,
				objDTSDepartmentObjects.TableRowsCount);
		pageWeeklyPlanningPO.CompareValues(extentTest, DBKronosScheduledHoursVal, UIValues);

		UITotal = pageDTSDepartmentPO.GetTotal(extentTest, objDTSDepartmentObjects.RowNameTotal,
				objDTSDepartmentObjects.KronosSchedHoursTot);
		pageMonthlyPlanningPO.CompareValues(extentTest, DBKronosScheduledHoursValTtl, UITotal);

		// VAR Hours
		Report_AddStep("testcase", " VARIANCE HOURS - DAY WISE DATA", "", "", "");
		extentTest = report.startTest("Variance Hours");
		
		String VARHours = data.getText("VARHours");
		DBVARHoursVal = sql.CLRexecuteQuery(getContext(),
				VARHours.replace("store", getContext().getStringProperty("store"))
						.replace("Week", getContext().getStringProperty("SelectFiscalWeek"))
						.replace("DepartmentToView", getContext().getStringProperty("DepartmentToView")).replace("Division", getContext().getStringProperty("Division"))
						.replace("FinalResult", getContext().getStringProperty("FinalResult"))
						.replace("LYWk", getContext().getStringProperty("LYWk")));

		System.out.println("DB Value: " + DBVARHoursVal);

		String VARHoursValTotal = data.getText("VARHoursValTotal");
		DBVARHoursValTtl = sql.CLRexecuteQuery(getContext(),
				VARHoursValTotal.replace("store", getContext().getStringProperty("store"))
						.replace("Week", getContext().getStringProperty("SelectFiscalWeek"))
						.replace("DepartmentToView", getContext().getStringProperty("DepartmentToView")).replace("Division", getContext().getStringProperty("Division"))
						.replace("FinalResult", getContext().getStringProperty("FinalResult"))
						.replace("LYWk", getContext().getStringProperty("LYWk")));

		System.out.println("DB Value: " + DBVARHoursValTtl);

		UIValues = pageDTSDepartmentPO.UIResults(objDTSDepartmentObjects, extentTest, objDTSDepartmentObjects.VARHours,
				objDTSDepartmentObjects.PlannedSalesGreen, objDTSDepartmentObjects.TableRowsCount);
		pageWeeklyPlanningPO.CompareValues(extentTest, DBVARHoursVal, UIValues);

		UITotal = pageDTSDepartmentPO.GetTotal(extentTest, objDTSDepartmentObjects.RowNameTotal,
				objDTSDepartmentObjects.VARHoursTot);
		pageMonthlyPlanningPO.CompareValues(extentTest, DBVARHoursValTtl, UITotal);

		// OR Hours
		Report_AddStep("testcase", " OR % UNDER PLANNED HOURS - DAY WISE DATA", "", "", "");
		extentTest = report.startTest("OR Hours");
		
		String OperatingRatioPlannedHours = data.getText("OperatingRatioPlannedHours");
		DBVARHoursVal = sql.CLRexecuteQuery(getContext(),
				OperatingRatioPlannedHours.replace("store", getContext().getStringProperty("store"))
						.replace("Week", getContext().getStringProperty("SelectFiscalWeek"))
						.replace("DepartmentToView", getContext().getStringProperty("DepartmentToView")).replace("Division", getContext().getStringProperty("Division"))
						.replace("FinalResult", getContext().getStringProperty("FinalResult"))
						.replace("LYWk", getContext().getStringProperty("LYWk")));
		System.out.println("DB Value: " + DBVARHoursVal);

		String OperatingRatioPlannedHoursTotal = data.getText("OperatingRatioPlannedHoursTotal");
		DBVARHoursValTtl = sql.CLRexecuteQuery(getContext(),
				OperatingRatioPlannedHoursTotal.replace("store", getContext().getStringProperty("store"))
						.replace("Week", getContext().getStringProperty("SelectFiscalWeek"))
						.replace("DepartmentToView", getContext().getStringProperty("DepartmentToView")).replace("Division", getContext().getStringProperty("Division"))
						.replace("FinalResult", getContext().getStringProperty("FinalResult"))
						.replace("LYWk", getContext().getStringProperty("LYWk")));
		System.out.println("DB Value: " + DBVARHoursValTtl);

		UIValues = pageDTSDepartmentPO.UIResults(objDTSDepartmentObjects, extentTest,
				objDTSDepartmentObjects.OperatingRatioPlannedHours, objDTSDepartmentObjects.OperatingRatioPlannedHours,
				objDTSDepartmentObjects.TableRowsCount);
		pageWeeklyPlanningPO.CompareValues(extentTest, DBVARHoursVal, UIValues);

		UITotal = pageDTSDepartmentPO.GetTotal(extentTest, objDTSDepartmentObjects.RowNameTotal,
				objDTSDepartmentObjects.OperatingRatioPlannedHoursTot);
		pageMonthlyPlanningPO.CompareValues(extentTest, DBVARHoursValTtl, UITotal);
	}

	//////////// ACTUAL HOURS ///////////////
	@Test(description = "Daily Trading Statement - Department, Actual Hours Values", priority = 3)
	public void ActualHoursColumnValidation_DTSDepartment() throws Exception {
		pageDTSDepartmentPO = new DTS_DepartmentPage().createPage(getDriver(), getContext(), log, status, data,
				getReport(), getReportLogger(), DTS_DepartmentPage.class);
		pageWeeklyPlanningPO = new WeeklyPlanningStorePage().createPage(getDriver(), getContext(), log, status, data,
				getReport(), getReportLogger(), WeeklyPlanningStorePage.class);
		pageDailyTradingStatementStorePO = new MonthlyPlanningGroupPage().createPage(getDriver(), getContext(), log,
				status, data, getReport(), getReportLogger(), DailyTradingStatementStorePage.class);
		TestStatus status = getTestStatus();
		System.out.println("Welcome " + getContext().getStringProperty("username"));
		System.out.println("Entering into TestCase : DTS Department");
		DepartmentToView = getContext().getStringProperty("DepartmentToView");
		WeekToTest = getContext().getStringProperty("WeekToTest");

		// Actual SMS Hours
		Report_AddStep("testcase", " ACTUAL SMS HOURS - DAY WISE DATA", "", "", "");
		extentTest = report.startTest("Actual SMS Hours");
		
		if (DepartmentToView.equals("5") || DepartmentToView.equals("10") || DepartmentToView.equals("15")) {
			String ActlSMSHoursLL = data.getText("ActlSMSHoursLL");
			DBActlSMSHoursVal = sql.CLRexecuteQuery(getContext(),
					ActlSMSHoursLL.replace("store", getContext().getStringProperty("store"))
							.replace("FinWk", getContext().getStringProperty("FinWk"))
							.replace("DepartmentToView", getContext().getStringProperty("DepartmentToView")).replace("Division", getContext().getStringProperty("Division"))
							.replace("FinalResult", getContext().getStringProperty("FinalResult"))
							.replace("Year", getContext().getStringProperty("FinYr")));
			System.out.println("DB Value: " + DBActlSMSHoursVal);

			String ActlSMSHoursValTotalLL = data.getText("ActlSMSHoursValTotalLL");
			DBActlSMSHoursValTtl = sql.CLRexecuteQuery(getContext(),
					ActlSMSHoursValTotalLL.replace("store", getContext().getStringProperty("store"))
							.replace("FinWk", getContext().getStringProperty("FinWk"))
							.replace("DepartmentToView", getContext().getStringProperty("DepartmentToView")).replace("Division", getContext().getStringProperty("Division"))
							.replace("FinalResult", getContext().getStringProperty("FinalResult"))
							.replace("Year", getContext().getStringProperty("FinYr")));

			System.out.println("DB Value: " + DBActlSMSHoursValTtl);
		} else {
			String ActlSMSHoursNonLL = data.getText("ActlSMSHoursNonLL");
			DBActlSMSHoursVal = sql.CLRexecuteQuery(getContext(),
					ActlSMSHoursNonLL.replace("store", getContext().getStringProperty("store"))
							.replace("FinWk", getContext().getStringProperty("FinWk"))
							.replace("DepartmentToView", getContext().getStringProperty("DepartmentToView")).replace("Division", getContext().getStringProperty("Division"))
							.replace("FinalResult", getContext().getStringProperty("FinalResult"))
							.replace("Year", getContext().getStringProperty("FinYr")));
			System.out.println("DB Value: " + DBActlSMSHoursVal);

			String ActlSMSHoursValTotalNonLL = data.getText("ActlSMSHoursValTotalNonLL");
			DBActlSMSHoursValTtl = sql.CLRexecuteQuery(getContext(),
					ActlSMSHoursValTotalNonLL.replace("store", getContext().getStringProperty("store"))
							.replace("FinWk", getContext().getStringProperty("FinWk"))
							.replace("DepartmentToView", getContext().getStringProperty("DepartmentToView")).replace("Division", getContext().getStringProperty("Division"))
							.replace("FinalResult", getContext().getStringProperty("FinalResult"))
							.replace("Year", getContext().getStringProperty("FinYr")));

			System.out.println("DB Value: " + DBActlSMSHoursValTtl);
		}

		UIValues = pageDTSDepartmentPO.UIResults(objDTSDepartmentObjects, extentTest, objDTSDepartmentObjects.ActualSMS,
				objDTSDepartmentObjects.ActualSMS, objDTSDepartmentObjects.TableRowsCount);
		pageWeeklyPlanningPO.CompareValues(extentTest, DBActlSMSHoursVal, UIValues);

		UITotal = pageDTSDepartmentPO.GetTotal(extentTest, objDTSDepartmentObjects.RowNameTotal,
				objDTSDepartmentObjects.ActualSMSHoursTot);
		pageMonthlyPlanningPO.CompareValues(extentTest, DBActlSMSHoursValTtl, UITotal);

		// Kronos Actual Hours
		Report_AddStep("testcase", " ACUTAL KRONOS HOURS - DAY WISE DATA", "", "", "");
		extentTest = report.startTest("Kronos Actual Hours");
		
		String KronosActlSMSHours = data.getText("KronosActlSMSHours");
		DBKronosActlSMSHoursVal = sql.CLRexecuteQuery(getContext(),
				KronosActlSMSHours.replace("store", getContext().getStringProperty("store"))
						.replace("Week", getContext().getStringProperty("SelectFiscalWeek"))
						.replace("DepartmentToView", getContext().getStringProperty("DepartmentToView")).replace("Division", getContext().getStringProperty("Division"))
						.replace("FinalResult", getContext().getStringProperty("FinalResult"))
						.replace("LYWk", getContext().getStringProperty("LYWk")));

		System.out.println("DB Value: " + DBKronosActlSMSHoursVal);

		String KronosActlSMSHoursTotal = data.getText("CurrentKronosActlSMSHoursTotal");
		DBKronosActlSMSHoursValTtl = sql.CLRexecuteQuery(getContext(),
				KronosActlSMSHoursTotal.replace("store", getContext().getStringProperty("store"))
						.replace("Week", getContext().getStringProperty("SelectFiscalWeek"))
						.replace("DepartmentToView", getContext().getStringProperty("DepartmentToView")).replace("Division", getContext().getStringProperty("Division"))
						.replace("FinalResult", getContext().getStringProperty("FinalResult"))
						.replace("LYWk", getContext().getStringProperty("LYWk"))
						.replace("Year", getContext().getStringProperty("FinYr"))
						.replace("FinWk", getContext().getStringProperty("FinWk"))
						.replace("Division", getContext().getStringProperty("Division")));

		System.out.println("DB Value: " + DBKronosActlSMSHoursValTtl);

		UIValues = pageDTSDepartmentPO.UIResults(objDTSDepartmentObjects, extentTest,
				objDTSDepartmentObjects.KronsActualHours, objDTSDepartmentObjects.KronsActualHours,
				objDTSDepartmentObjects.TableRowsCount);
		pageWeeklyPlanningPO.CompareValues(extentTest, DBKronosActlSMSHoursVal, UIValues);

		UITotal = pageDTSDepartmentPO.GetTotal(extentTest, objDTSDepartmentObjects.RowNameTotal,
				objDTSDepartmentObjects.KronosActlHoursTot);
		pageMonthlyPlanningPO.CompareValues(extentTest, DBKronosActlSMSHoursValTtl, UITotal);

		// VAR Hours
		Report_AddStep("testcase", " VARIANCE UNDER ACTUAL HOURS - DAY WISE DATA", "", "", "");
		extentTest = report.startTest("Variance Hours");
		
		pageDTSDepartmentPO.ActualHours_Variance("Variance", objDTSDepartmentObjects, extentTest,
				objDTSDepartmentObjects.Days, objDTSDepartmentObjects.ActualSMS,
				objDTSDepartmentObjects.KronsActualHours, objDTSDepartmentObjects.VARActualHours,
				objDTSDepartmentObjects.TableRowsCount, objDTSDepartmentObjects.PlanSMS);

		pageDTSDepartmentPO.Actual_Total("Variance", objDTSDepartmentObjects, extentTest, objDTSDepartmentObjects.Total,
				objDTSDepartmentObjects.ActualSMSHoursTot, objDTSDepartmentObjects.KronosActlHoursTot,
				objDTSDepartmentObjects.VARActlHoursTot, objDTSDepartmentObjects.PlanSMSHoursTot);

		// Operating Ratio
		Report_AddStep("testcase", " OPERATING RATIO HOURS - DAY WISE DATA", "", "", "");
		extentTest = report.startTest("Operating Ratio");
		/*
		 * pageDTSDepartmentPO.ActualHours_Variance("OR",objDTSDepartmentObjects,
		 * extentTest, objDTSDepartmentObjects.Days,objDTSDepartmentObjects.ActualSMS,
		 * objDTSDepartmentObjects.KronsActualHours,
		 * objDTSDepartmentObjects.ORHours,objDTSDepartmentObjects.TableRowsCount,
		 * objDTSDepartmentObjects.PlanSMS);
		 * 
		 * pageDTSDepartmentPO.Actual_Total("OR",objDTSDepartmentObjects, extentTest,
		 * objDTSDepartmentObjects.Total,objDTSDepartmentObjects.ActualSMSHoursTot,
		 * objDTSDepartmentObjects.KronosActlHoursTot,
		 * objDTSDepartmentObjects.ORActlHoursTot,objDTSDepartmentObjects.
		 * PlanSMSHoursTot);
		 */

		if (DepartmentToView.equals("5") || DepartmentToView.equals("10") || DepartmentToView.equals("15")) {
			String ORHoursLL = data.getText("ORHoursLL");
			DBActlORVal = sql.CLRexecuteQuery(getContext(),
					ORHoursLL.replace("store", getContext().getStringProperty("store"))
							.replace("FinWk", getContext().getStringProperty("FinWk"))
							.replace("Week", getContext().getStringProperty("SelectFiscalWeek"))
							.replace("DepartmentToView", getContext().getStringProperty("DepartmentToView")).replace("Division", getContext().getStringProperty("Division"))
							.replace("FinalResult", getContext().getStringProperty("FinalResult"))
							.replace("Year", getContext().getStringProperty("FinYr")));
			System.out.println("DB Value: " + DBActlORVal);

			String ORHoursTotalLL = data.getText("CurrentWeekORHoursTotalLL");
			DBActlORTtl = sql.CLRexecuteQuery(getContext(),
					ORHoursTotalLL.replace("store", getContext().getStringProperty("store"))
							.replace("FinWk", getContext().getStringProperty("FinWk"))
							.replace("Week", getContext().getStringProperty("SelectFiscalWeek"))
							.replace("DepartmentToView", getContext().getStringProperty("DepartmentToView")).replace("Division", getContext().getStringProperty("Division"))
							.replace("FinalResult", getContext().getStringProperty("FinalResult"))
							.replace("Year", getContext().getStringProperty("FinYr")));

			System.out.println("DB Value: " + DBActlORTtl);
		} else {
			String ORHoursNonLL = data.getText("ORHoursNonLL");
			DBActlORVal = sql.CLRexecuteQuery(getContext(),
					ORHoursNonLL.replace("store", getContext().getStringProperty("store"))
							.replace("FinWk", getContext().getStringProperty("FinWk"))
							.replace("Week", getContext().getStringProperty("SelectFiscalWeek"))
							.replace("DepartmentToView", getContext().getStringProperty("DepartmentToView")).replace("Division", getContext().getStringProperty("Division"))
							.replace("FinalResult", getContext().getStringProperty("FinalResult"))
							.replace("Year", getContext().getStringProperty("FinYr")));
			System.out.println("DB Value: " + DBActlORVal);

			if (WeekToTest.contains("Past")) {
				String ORHoursTotalNonLL = data.getText("ORHoursTotalNonLL");
				DBActlORTtl = sql.CLRexecuteQuery(getContext(),
						ORHoursTotalNonLL.replace("store", getContext().getStringProperty("store"))
								.replace("FinWk", getContext().getStringProperty("FinWk"))
								.replace("Week", getContext().getStringProperty("SelectFiscalWeek"))
								.replace("DepartmentToView", getContext().getStringProperty("DepartmentToView")).replace("Division", getContext().getStringProperty("Division"))
								.replace("FinalResult", getContext().getStringProperty("FinalResult"))
								.replace("Year", getContext().getStringProperty("FinYr")));

				System.out.println("DB Value: " + DBActlORTtl);
			}
			if (WeekToTest.contains("Current")) {
				String CurrentORHoursTotalNonLL = data.getText("CurrentORHoursTotalNonLL");
				DBActlORTtl = sql.CLRexecuteQuery(getContext(),
						CurrentORHoursTotalNonLL.replace("store", getContext().getStringProperty("store"))
								.replace("FinWk", getContext().getStringProperty("FinWk"))
								.replace("Week", getContext().getStringProperty("SelectFiscalWeek"))
								.replace("DepartmentToView", getContext().getStringProperty("DepartmentToView")).replace("Division", getContext().getStringProperty("Division"))
								.replace("FinalResult", getContext().getStringProperty("FinalResult"))
								.replace("Year", getContext().getStringProperty("FinYr")));

				System.out.println("DB Value: " + DBActlORTtl);
			}
		}

		UIValues = pageDTSDepartmentPO.UIResults(objDTSDepartmentObjects, extentTest, objDTSDepartmentObjects.ORHours,
				objDTSDepartmentObjects.ORHours, objDTSDepartmentObjects.TableRowsCount);
		pageWeeklyPlanningPO.CompareValues(extentTest, DBActlORVal, UIValues);

		UITotal = pageDTSDepartmentPO.GetTotal(extentTest, objDTSDepartmentObjects.RowNameTotal,
				objDTSDepartmentObjects.ORActlHoursTot);
		pageMonthlyPlanningPO.CompareValues(extentTest, DBActlORTtl, UITotal);
	}

	//////////// ITEMS ///////////////
	@Test(description = "Daily Trading Statement - Department, Actual Hours Values", priority = 4)
	public void ItemsColumnValidation_DTSDepartment() throws Exception {
		pageDTSDepartmentPO = new DTS_DepartmentPage().createPage(getDriver(), getContext(), log, status, data,
				getReport(), getReportLogger(), DTS_DepartmentPage.class);
		pageDailyTradingStatementStorePO = new MonthlyPlanningGroupPage().createPage(getDriver(), getContext(), log,
				status, data, getReport(), getReportLogger(), DailyTradingStatementStorePage.class);
		pageWeeklyPlanningPO = new WeeklyPlanningStorePage().createPage(getDriver(), getContext(), log, status, data,
				getReport(), getReportLogger(), WeeklyPlanningStorePage.class);
		TestStatus status = getTestStatus();
		System.out.println("Welcome " + getContext().getStringProperty("username"));
		System.out.println("Entering into TestCase : DTS Department");

		// Plan Items
		Report_AddStep("testcase", " PLANNED ITEMS - DAY WISE DATA", "", "", "");
		extentTest = report.startTest("Planned Items");
		
		String PlannedItems = data.getText("PlannedItems");
		DBPlanItemsVal = sql.CLRexecuteQuery(getContext(),
				PlannedItems.replace("store", getContext().getStringProperty("store"))
						.replace("Week", getContext().getStringProperty("SelectFiscalWeek"))
						.replace("DepartmentToView", getContext().getStringProperty("DepartmentToView")).replace("Division", getContext().getStringProperty("Division"))
						.replace("FinalResult", getContext().getStringProperty("FinalResult"))
						.replace("LYWk", getContext().getStringProperty("LYWk")));

		System.out.println("DB Value: " + DBPlanItemsVal);

		String PlannedItemsTotal = data.getText("PlannedItemsTotal");
		DBPlanItemsValTtl = sql.CLRexecuteQuery(getContext(),
				PlannedItemsTotal.replace("store", getContext().getStringProperty("store"))
						.replace("Week", getContext().getStringProperty("SelectFiscalWeek"))
						.replace("DepartmentToView", getContext().getStringProperty("DepartmentToView")).replace("Division", getContext().getStringProperty("Division"))
						.replace("FinalResult", getContext().getStringProperty("FinalResult"))
						.replace("LYWk", getContext().getStringProperty("LYWk")));

		System.out.println("DB Value: " + DBPlanItemsValTtl);

		UIValues = pageDTSDepartmentPO.UIResults(objDTSDepartmentObjects, extentTest, objDTSDepartmentObjects.PlanItems,
				objDTSDepartmentObjects.PlanItems, objDTSDepartmentObjects.TableRowsCount);
		pageWeeklyPlanningPO.CompareValues(extentTest, DBPlanItemsVal, UIValues);

		UITotal = pageDTSDepartmentPO.GetTotal(extentTest, objDTSDepartmentObjects.RowNameTotal,
				objDTSDepartmentObjects.PlanItemsTot);
		pageMonthlyPlanningPO.CompareValues(extentTest, DBPlanItemsValTtl, UITotal);

		// Actual Items
		Report_AddStep("testcase", " ACTUAL ITEMS - DAY WISE DATA", "", "", "");
		extentTest = report.startTest("Actual Items");
		
		String ActualItems = data.getText("ActualItems");
		DBActualItemsVal = sql.CLRexecuteQuery(getContext(),
				ActualItems.replace("store", getContext().getStringProperty("store"))
						.replace("Week", getContext().getStringProperty("SelectFiscalWeek"))
						.replace("DepartmentToView", getContext().getStringProperty("DepartmentToView")).replace("Division", getContext().getStringProperty("Division"))
						.replace("FinalResult", getContext().getStringProperty("FinalResult"))
						.replace("LYWk", getContext().getStringProperty("LYWk")));

		System.out.println("DB Value: " + DBActualItemsVal);

		String ActualItemsTotal = data.getText("ActualItemsTotal");
		DBActualItemsValTtl = sql.CLRexecuteQuery(getContext(),
				ActualItemsTotal.replace("store", getContext().getStringProperty("store"))
						.replace("Week", getContext().getStringProperty("SelectFiscalWeek"))
						.replace("DepartmentToView", getContext().getStringProperty("DepartmentToView")).replace("Division", getContext().getStringProperty("Division"))
						.replace("FinalResult", getContext().getStringProperty("FinalResult"))
						.replace("LYWk", getContext().getStringProperty("LYWk")));

		System.out.println("DB Value: " + DBActualItemsValTtl);

		UIValues = pageDTSDepartmentPO.UIResults(objDTSDepartmentObjects, extentTest,
				objDTSDepartmentObjects.ActualItems, objDTSDepartmentObjects.PlannedSalesGreen,
				objDTSDepartmentObjects.TableRowsCount);
		pageWeeklyPlanningPO.CompareValues(extentTest, DBActualItemsVal, UIValues);

		UITotal = pageDTSDepartmentPO.GetTotal(extentTest, objDTSDepartmentObjects.RowNameTotal,
				objDTSDepartmentObjects.ActlItemsTot);
		pageMonthlyPlanningPO.CompareValues(extentTest, DBActualItemsValTtl, UITotal);

		// VAR Items
		Report_AddStep("testcase", " VARIANCE ITEMS - DAY WISE DATA", "", "", "");
		extentTest = report.startTest("Variance Items");
		
		String VARItems = data.getText("VARItems");
		DBVARItemsVal = sql.CLRexecuteQuery(getContext(),
				VARItems.replace("store", getContext().getStringProperty("store"))
						.replace("Week", getContext().getStringProperty("SelectFiscalWeek"))
						.replace("DepartmentToView", getContext().getStringProperty("DepartmentToView")).replace("Division", getContext().getStringProperty("Division"))
						.replace("FinalResult", getContext().getStringProperty("FinalResult"))
						.replace("LYWk", getContext().getStringProperty("LYWk")));

		System.out.println("DB Value: " + DBVARItemsVal);

		String VARItemsTotal = data.getText("VARItemsTotal");
		DBVARItemsValTtl = sql.CLRexecuteQuery(getContext(),
				VARItemsTotal.replace("store", getContext().getStringProperty("store"))
						.replace("Week", getContext().getStringProperty("SelectFiscalWeek"))
						.replace("DepartmentToView", getContext().getStringProperty("DepartmentToView")).replace("Division", getContext().getStringProperty("Division"))
						.replace("FinalResult", getContext().getStringProperty("FinalResult"))
						.replace("LYWk", getContext().getStringProperty("LYWk")));

		System.out.println("DB Value: " + DBVARItemsValTtl);

		UIValues = pageDTSDepartmentPO.UIResults(objDTSDepartmentObjects, extentTest, objDTSDepartmentObjects.VARItems,
				objDTSDepartmentObjects.PlannedSalesGreen, objDTSDepartmentObjects.TableRowsCount);
		pageWeeklyPlanningPO.CompareValues(extentTest, DBVARItemsVal, UIValues);

		UITotal = pageDTSDepartmentPO.GetTotal(extentTest, objDTSDepartmentObjects.RowNameTotal,
				objDTSDepartmentObjects.VARItemsTot);
		pageMonthlyPlanningPO.CompareValues(extentTest, DBVARItemsValTtl, UITotal);
	}

	//////////// CUSTOMERS ///////////////
	@Test(description = "Daily Trading Statement - Department, Customer Values", priority = 5)
	public void CustomerColumnValidation_DTSDepartment() throws Exception {
		pageDTSDepartmentPO = new DTS_DepartmentPage().createPage(getDriver(), getContext(), log, status, data,
				getReport(), getReportLogger(), DTS_DepartmentPage.class);
		pageWeeklyPlanningPO = new WeeklyPlanningStorePage().createPage(getDriver(), getContext(), log, status, data,
				getReport(), getReportLogger(), WeeklyPlanningStorePage.class);
		pageDailyTradingStatementStorePO = new MonthlyPlanningGroupPage().createPage(getDriver(), getContext(), log,
				status, data, getReport(), getReportLogger(), DailyTradingStatementStorePage.class);
		TestStatus status = getTestStatus();
		System.out.println("Welcome " + getContext().getStringProperty("username"));
		OptionToView = getContext().getStringProperty("DepartmentToView");

		if (OptionToView.equalsIgnoreCase("90") || OptionToView.equalsIgnoreCase("-1")) {
			// Customer Planned
			Report_AddStep("testcase", " PLANNED CUSTOMERS - DAY WISE DATA", "", "", "");
			extentTest = report.startTest("Planned Customers");
			
			String PlannedCustomers = data.getText("PlannedCustomers");
			DBPlnCustomersVal = sql.CLRexecuteQuery(getContext(),
					PlannedCustomers.replace("store", getContext().getStringProperty("store"))
							.replace("Week", getContext().getStringProperty("SelectFiscalWeek"))
							.replace("DepartmentToView", getContext().getStringProperty("DepartmentToView")).replace("Division", getContext().getStringProperty("Division"))
							.replace("FinalResult", getContext().getStringProperty("FinalResult"))
							.replace("LYWk", getContext().getStringProperty("LYWk")));

			System.out.println("DB Value: " + DBPlnCustomersVal);

			String PlannedCustomersTotal = data.getText("PlannedCustomersTotal");
			DBPlanCustomersTtl = sql.CLRexecuteQuery(getContext(),
					PlannedCustomersTotal.replace("store", getContext().getStringProperty("store"))
							.replace("Week", getContext().getStringProperty("SelectFiscalWeek"))
							.replace("DepartmentToView", getContext().getStringProperty("DepartmentToView")).replace("Division", getContext().getStringProperty("Division"))
							.replace("FinalResult", getContext().getStringProperty("FinalResult"))
							.replace("LYWk", getContext().getStringProperty("LYWk")));

			System.out.println("DB Value: " + DBPlanCustomersTtl);

			UIValues = pageDTSDepartmentPO.UIResults(objDTSDepartmentObjects, extentTest,
					objDTSDepartmentObjects.PlanCustomers, objDTSDepartmentObjects.PlanCustomersEmpty,
					objDTSDepartmentObjects.TableRowsCount);
			pageWeeklyPlanningPO.CompareValues(extentTest, DBPlnCustomersVal, UIValues);

			if (OptionToView.contains("90") || OptionToView.contains("0"))
				UITotal = pageDTSDepartmentPO.GetTotal(extentTest, objDTSDepartmentObjects.RowNameTotal,
						objDTSDepartmentObjects.PlanCustTot);
			else
				UITotal = pageDTSDepartmentPO.GetTotal(extentTest, objDTSDepartmentObjects.RowNameTotal,
						objDTSDepartmentObjects.PlanCustTotEmpty);
			pageMonthlyPlanningPO.CompareValues(extentTest, DBPlanCustomersTtl, UITotal);

			// Customer Actual
			Report_AddStep("testcase", " ACTUAL CUSTOMERS - DAY WISE DATA", "", "", "");
			extentTest = report.startTest("Actual Customers");
			
			String ActualCustomers = data.getText("ActualCustomers");
			DBActlCustomersVal = sql.CLRexecuteQuery(getContext(),
					ActualCustomers.replace("store", getContext().getStringProperty("store"))
							.replace("Week", getContext().getStringProperty("SelectFiscalWeek"))
							.replace("DepartmentToView", getContext().getStringProperty("DepartmentToView")).replace("Division", getContext().getStringProperty("Division"))
							.replace("FinalResult", getContext().getStringProperty("FinalResult"))
							.replace("LYWk", getContext().getStringProperty("LYWk")));

			System.out.println("DB Value: " + DBActlCustomersVal);

			String ActualCustomersTotal = data.getText("ActualCustomersTotal");
			DBActlCustomersValTtl = sql.CLRexecuteQuery(getContext(),
					ActualCustomersTotal.replace("store", getContext().getStringProperty("store"))
							.replace("Week", getContext().getStringProperty("SelectFiscalWeek"))
							.replace("DepartmentToView", getContext().getStringProperty("DepartmentToView")).replace("Division", getContext().getStringProperty("Division"))
							.replace("FinalResult", getContext().getStringProperty("FinalResult"))
							.replace("LYWk", getContext().getStringProperty("LYWk")));

			System.out.println("DB Value: " + DBActlCustomersValTtl);

			UIValues = pageDTSDepartmentPO.UIResults(objDTSDepartmentObjects, extentTest,
					objDTSDepartmentObjects.ActualCustomers, objDTSDepartmentObjects.ActualCustomersEmpty,
					objDTSDepartmentObjects.TableRowsCount);
			pageWeeklyPlanningPO.CompareValues(extentTest, DBActlCustomersVal, UIValues);

			if (OptionToView.contains("90") || OptionToView.contains("0"))
				UITotal = pageDTSDepartmentPO.GetTotal(extentTest, objDTSDepartmentObjects.RowNameTotal,
						objDTSDepartmentObjects.ActlCustTot);
			else
				UITotal = pageDTSDepartmentPO.GetTotal(extentTest, objDTSDepartmentObjects.RowNameTotal,
						objDTSDepartmentObjects.ActlCustTotEmpty);
			pageMonthlyPlanningPO.CompareValues(extentTest, DBActlCustomersValTtl, UITotal);

			// VAR Customer
			Report_AddStep("testcase", " VARIANCE CUSTOMERS- DAY WISE DATA", "", "", "");
			extentTest = report.startTest("Variance Customers");
			
			String VARCustomers = data.getText("VARCustomers");
			DBVARCustomersVal = sql.CLRexecuteQuery(getContext(),
					VARCustomers.replace("store", getContext().getStringProperty("store"))
							.replace("Week", getContext().getStringProperty("SelectFiscalWeek"))
							.replace("DepartmentToView", getContext().getStringProperty("DepartmentToView")).replace("Division", getContext().getStringProperty("Division"))
							.replace("FinalResult", getContext().getStringProperty("FinalResult"))
							.replace("LYWk", getContext().getStringProperty("LYWk")));

			System.out.println("DB Value: " + DBVARCustomersVal);

			String VARCustomersTotal = data.getText("VARCustomersTotal");
			DBVARCustomersValTtl = sql.CLRexecuteQuery(getContext(),
					VARCustomersTotal.replace("store", getContext().getStringProperty("store"))
							.replace("Week", getContext().getStringProperty("SelectFiscalWeek"))
							.replace("DepartmentToView", getContext().getStringProperty("DepartmentToView")).replace("Division", getContext().getStringProperty("Division"))
							.replace("FinalResult", getContext().getStringProperty("FinalResult"))
							.replace("LYWk", getContext().getStringProperty("LYWk")));

			System.out.println("DB Value: " + DBVARCustomersValTtl);

			UIValues = pageDTSDepartmentPO.UIResults(objDTSDepartmentObjects, extentTest,
					objDTSDepartmentObjects.VARCustomers, objDTSDepartmentObjects.VARCustomersEmpty,
					objDTSDepartmentObjects.TableRowsCount);
			pageWeeklyPlanningPO.CompareValues(extentTest, DBVARCustomersVal, UIValues);

			if (OptionToView.contains("90") || OptionToView.contains("0"))
				UITotal = pageDTSDepartmentPO.GetTotal(extentTest, objDTSDepartmentObjects.RowNameTotal,
						objDTSDepartmentObjects.VARCustTot);
			else
				UITotal = pageDTSDepartmentPO.GetTotal(extentTest, objDTSDepartmentObjects.RowNameTotal,
						objDTSDepartmentObjects.VARCustTotEmpty);
			pageMonthlyPlanningPO.CompareValues(extentTest, DBVARCustomersValTtl, UITotal);
		} else
			Report_AddStep("testcase", " No Customer Data will be populated for Department " + OptionToView + " ", "",
					"", "Pass");

	}

	//////////// WAGES ///////////////
	@Test(description = "Daily Trading Statement - Department, Wages Values", priority = 6)
	public void WagesColumnValidation_DTSDepartment() throws Exception {
		pageDTSDepartmentPO = new DTS_DepartmentPage().createPage(getDriver(), getContext(), log, status, data,
				getReport(), getReportLogger(), DTS_DepartmentPage.class);
		pageWeeklyPlanningPO = new WeeklyPlanningStorePage().createPage(getDriver(), getContext(), log, status, data,
				getReport(), getReportLogger(), WeeklyPlanningStorePage.class);
		pageDailyTradingStatementStorePO = new MonthlyPlanningGroupPage().createPage(getDriver(), getContext(), log,
				status, data, getReport(), getReportLogger(), DailyTradingStatementStorePage.class);
		TestStatus status = getTestStatus();
		System.out.println("Welcome " + getContext().getStringProperty("username"));
		System.out.println("Entering into TestCase : DTS Department");
		OptionToView = getContext().getStringProperty("OptionToView");
		WeekToTest = getContext().getStringProperty("WeekToTest");

		// Plan Wages
		Report_AddStep("testcase", " PLANNED WAGES - DAY WISE DATA", "", "", "");
		extentTest = report.startTest("Planned Wages");
		
		String PlanWages = data.getText("PlanWages");
		DBPlnWagesVal = sql.CLRexecuteQuery(getContext(),
				PlanWages.replace("store", getContext().getStringProperty("store"))
						.replace("Week", getContext().getStringProperty("SelectFiscalWeek"))
						.replace("DepartmentToView", getContext().getStringProperty("DepartmentToView")).replace("Division", getContext().getStringProperty("Division"))
						.replace("FinalResult", getContext().getStringProperty("FinalResult"))
						.replace("LYWk", getContext().getStringProperty("LYWk")));

		System.out.println("DB Value: " + DBPlnWagesVal);

		String PlanWagesTotal = data.getText("PlanWagesTotal");
		DBPlnWagesValTtl = sql.CLRexecuteQuery(getContext(),
				PlanWagesTotal.replace("store", getContext().getStringProperty("store"))
						.replace("Week", getContext().getStringProperty("SelectFiscalWeek"))
						.replace("DepartmentToView", getContext().getStringProperty("DepartmentToView")).replace("Division", getContext().getStringProperty("Division"))
						.replace("FinalResult", getContext().getStringProperty("FinalResult"))
						.replace("LYWk", getContext().getStringProperty("LYWk")));

		System.out.println("DB Value: " + DBPlnWagesValTtl);

		UIValues = pageDTSDepartmentPO.UIResults(objDTSDepartmentObjects, extentTest, objDTSDepartmentObjects.PlanWage,
				objDTSDepartmentObjects.PlannedSalesGreen, objDTSDepartmentObjects.TableRowsCount);
		pageWeeklyPlanningPO.CompareValues(extentTest, DBPlnWagesVal, UIValues);

		UITotal = pageDTSDepartmentPO.GetTotal(extentTest, objDTSDepartmentObjects.RowNameTotal,
				objDTSDepartmentObjects.PlanWagesTot);
		pageMonthlyPlanningPO.CompareValues(extentTest, DBPlnWagesValTtl, UITotal);

		// Actual Wages
		Report_AddStep("testcase", " ACTUAL WAGES - DAY WISE DATA", "", "", "");
		extentTest = report.startTest("Actual Wages");
		
		String ActualWages = data.getText("ActualWages");
		DBActlWagesVal = sql.CLRexecuteQuery(getContext(),
				ActualWages.replace("store", getContext().getStringProperty("store"))
						.replace("Week", getContext().getStringProperty("SelectFiscalWeek"))
						.replace("DepartmentToView", getContext().getStringProperty("DepartmentToView")).replace("Division", getContext().getStringProperty("Division"))
						.replace("FinalResult", getContext().getStringProperty("FinalResult"))
						.replace("LYWk", getContext().getStringProperty("LYWk")));

		System.out.println("DB Value: " + DBActlWagesVal);

		if (WeekToTest.contains("Past") || OptionToView.contains("Future")) {
			String ActualWagesTotal = data.getText("ActualWagesTotal");
			DBActlWagesValTtl = sql.CLRexecuteQuery(getContext(),
					ActualWagesTotal.replace("store", getContext().getStringProperty("store"))
							.replace("Week", getContext().getStringProperty("SelectFiscalWeek"))
							.replace("DepartmentToView", getContext().getStringProperty("DepartmentToView")).replace("Division", getContext().getStringProperty("Division"))
							.replace("FinalResult", getContext().getStringProperty("FinalResult"))
							.replace("LYWk", getContext().getStringProperty("LYWk"))
							.replace("Year", getContext().getStringProperty("FinYr"))
							.replace("FinWk", getContext().getStringProperty("FinWk"))
							.replace("Division", getContext().getStringProperty("Division")));
			System.out.println("DB Value: " + DBActlWagesValTtl);
		}
		if (WeekToTest.contains("Current")) {
			String CurrentActualWagesTotal = data.getText("currentActualWagesTotal");
			DBActlWagesValTtl = sql.CLRexecuteQuery(getContext(),
					CurrentActualWagesTotal.replace("store", getContext().getStringProperty("store"))
							.replace("Week", getContext().getStringProperty("SelectFiscalWeek"))
							.replace("DepartmentToView", getContext().getStringProperty("DepartmentToView")).replace("Division", getContext().getStringProperty("Division"))
							.replace("FinalResult", getContext().getStringProperty("FinalResult"))
							.replace("LYWk", getContext().getStringProperty("LYWk"))
							.replace("Year", getContext().getStringProperty("FinYr"))
							.replace("FinWk", getContext().getStringProperty("FinWk"))
							.replace("Division", getContext().getStringProperty("Division")));
			System.out.println("DB Value: " + DBActlWagesValTtl);
		}

		UIValues = pageDTSDepartmentPO.UIResults(objDTSDepartmentObjects, extentTest,
				objDTSDepartmentObjects.ActualWage, objDTSDepartmentObjects.PlannedSalesGreen,
				objDTSDepartmentObjects.TableRowsCount);
		pageWeeklyPlanningPO.CompareValues(extentTest, DBActlWagesVal, UIValues);

		UITotal = pageDTSDepartmentPO.GetTotal(extentTest, objDTSDepartmentObjects.RowNameTotal,
				objDTSDepartmentObjects.ActualWagesTot);
		pageMonthlyPlanningPO.CompareValues(extentTest, DBActlWagesValTtl, UITotal);

		// VAR Plan Vs Actual Wage
		Report_AddStep("testcase", " VARIANCE PLANNED VS ACTUALS - DAY WISE DATA", "", "", "");
		extentTest = report.startTest("Plan vs Actual - Variance");
		
		String VARPlanVsActualWages = data.getText("VARPlanVsActualWages");
		DBVARPlanVsActualWageVal = sql.CLRexecuteQuery(getContext(),
				VARPlanVsActualWages.replace("store", getContext().getStringProperty("store"))
						.replace("Week", getContext().getStringProperty("SelectFiscalWeek"))
						.replace("DepartmentToView", getContext().getStringProperty("DepartmentToView")).replace("Division", getContext().getStringProperty("Division"))
						.replace("FinalResult", getContext().getStringProperty("FinalResult"))
						.replace("LYWk", getContext().getStringProperty("LYWk")));

		System.out.println("DB Value: " + DBVARPlanVsActualWageVal);

		if (WeekToTest.contains("Past") || WeekToTest.contains("Future")) {
			String VARPlanVsActualWagesTotal = data.getText("VARPlanVsActualWagesTotal");
			DBVARPlanVsActualWageValTtl = sql.CLRexecuteQuery(getContext(),
					VARPlanVsActualWagesTotal.replace("store", getContext().getStringProperty("store"))
							.replace("Week", getContext().getStringProperty("SelectFiscalWeek"))
							.replace("DepartmentToView", getContext().getStringProperty("DepartmentToView")).replace("Division", getContext().getStringProperty("Division"))
							.replace("FinalResult", getContext().getStringProperty("FinalResult"))
							.replace("LYWk", getContext().getStringProperty("LYWk"))
							.replace("Year", getContext().getStringProperty("FinYr"))
							.replace("FinWk", getContext().getStringProperty("FinWk"))
							.replace("Division", getContext().getStringProperty("Division")));
			System.out.println("DB Value: " + DBVARPlanVsActualWageValTtl);
		}
		if (WeekToTest.contains("Current")) {
			String CurrentVARPlanVsActualWageTotal = data.getText("currentVARPlanVsActualWagesTotal");
			DBVARPlanVsActualWageValTtl = sql.CLRexecuteQuery(getContext(),
					CurrentVARPlanVsActualWageTotal.replace("store", getContext().getStringProperty("store"))
							.replace("Week", getContext().getStringProperty("SelectFiscalWeek"))
							.replace("DepartmentToView", getContext().getStringProperty("DepartmentToView")).replace("Division", getContext().getStringProperty("Division"))
							.replace("FinalResult", getContext().getStringProperty("FinalResult"))
							.replace("LYWk", getContext().getStringProperty("LYWk"))
							.replace("Year", getContext().getStringProperty("FinYr"))
							.replace("FinWk", getContext().getStringProperty("FinWk"))
							.replace("Division", getContext().getStringProperty("Division")));
			System.out.println("DB Value: " + DBVARPlanVsActualWageValTtl);
		}

		UIValues = pageDTSDepartmentPO.UIResults(objDTSDepartmentObjects, extentTest,
				objDTSDepartmentObjects.VARPlanVsActualWage, objDTSDepartmentObjects.PlannedSalesGreen,
				objDTSDepartmentObjects.TableRowsCount);
		pageWeeklyPlanningPO.CompareValues(extentTest, DBVARPlanVsActualWageVal, UIValues);

		UITotal = pageDTSDepartmentPO.GetTotal(extentTest, objDTSDepartmentObjects.RowNameTotal,
				objDTSDepartmentObjects.VARPlanVsActualWageTot);
		pageMonthlyPlanningPO.CompareValues(extentTest, DBVARPlanVsActualWageValTtl, UITotal);

		// Plan %
		Report_AddStep("testcase", " PLANNED PERCENTAGE - DAY WISE DATA", "", "", "");
		extentTest = report.startTest("Planned Wages Prcnt");
		
		String WagesPlanPercentage = data.getText("WagesPlanPercentage");
		DBWagesPlanpercentVal = sql.CLRexecuteQuery(getContext(),
				WagesPlanPercentage.replace("store", getContext().getStringProperty("store"))
						.replace("Week", getContext().getStringProperty("SelectFiscalWeek"))
						.replace("DepartmentToView", getContext().getStringProperty("DepartmentToView")).replace("Division", getContext().getStringProperty("Division"))
						.replace("FinalResult", getContext().getStringProperty("FinalResult"))
						.replace("LYWk", getContext().getStringProperty("LYWk")));

		System.out.println("DB Value: " + DBWagesPlanpercentVal);

		String WagesPlanPercentageTotal = data.getText("WagesPlanPercentageTotal");
		DBWagesPlanpercentValTtl = sql.CLRexecuteQuery(getContext(),
				WagesPlanPercentageTotal.replace("store", getContext().getStringProperty("store"))
						.replace("Week", getContext().getStringProperty("SelectFiscalWeek"))
						.replace("DepartmentToView", getContext().getStringProperty("DepartmentToView")).replace("Division", getContext().getStringProperty("Division"))
						.replace("FinalResult", getContext().getStringProperty("FinalResult"))
						.replace("LYWk", getContext().getStringProperty("LYWk")));

		System.out.println("DB Value: " + DBWagesPlanpercentValTtl);

		UIValues = pageDTSDepartmentPO.UIResults(objDTSDepartmentObjects, extentTest,
				objDTSDepartmentObjects.PlanpercentWages, objDTSDepartmentObjects.VARPlanVsActualWage,
				objDTSDepartmentObjects.TableRowsCount);
		pageWeeklyPlanningPO.CompareValueDecimalFields(extentTest, DBWagesPlanpercentVal, UIValues);

		UITotal = pageDTSDepartmentPO.GetTotal(extentTest, objDTSDepartmentObjects.RowNameTotal,
				objDTSDepartmentObjects.PlanPerWagesTot);
		pageMonthlyPlanningPO.CompareValueDecimalFields(extentTest, DBWagesPlanpercentValTtl, UITotal);

		// Actual %
		Report_AddStep("testcase", " ACTUAL PERCENTAGE - DAY WISE DATA", "", "", "");
		extentTest = report.startTest("Actual Wages Prcnt");
		
		String WagesActualPercentage = data.getText("WagesActualPercentage");
		DBWagesactualpercentVal = sql.CLRexecuteQuery(getContext(),
				WagesActualPercentage.replace("store", getContext().getStringProperty("store"))
						.replace("Week", getContext().getStringProperty("SelectFiscalWeek"))
						.replace("DepartmentToView", getContext().getStringProperty("DepartmentToView")).replace("Division", getContext().getStringProperty("Division"))
						.replace("FinalResult", getContext().getStringProperty("FinalResult"))
						.replace("LYWk", getContext().getStringProperty("LYWk")));

		System.out.println("DB Value: " + DBWagesactualpercentVal);

		if (WeekToTest.contains("Past") || WeekToTest.contains("Future")) {
			String WagesActualPercentageTotal = data.getText("WagesActualPercentageTotal");
			DBWagesactualpercentValTtl = sql.CLRexecuteQuery(getContext(),
					WagesActualPercentageTotal.replace("store", getContext().getStringProperty("store"))
							.replace("Week", getContext().getStringProperty("SelectFiscalWeek"))
							.replace("DepartmentToView", getContext().getStringProperty("DepartmentToView")).replace("Division", getContext().getStringProperty("Division"))
							.replace("FinalResult", getContext().getStringProperty("FinalResult"))
							.replace("LYWk", getContext().getStringProperty("LYWk"))
							.replace("Year", getContext().getStringProperty("FinYr"))
							.replace("FinWk", getContext().getStringProperty("FinWk"))
							.replace("Division", getContext().getStringProperty("Division")));

			System.out.println("DB Value: " + DBWagesactualpercentValTtl);
		}
		if (WeekToTest.contains("Current")) {
			String CurrentWagesActualPercentageTotal = data.getText("currentWagesActualPercentageTotal");
			DBWagesactualpercentValTtl = sql.CLRexecuteQuery(getContext(),
					CurrentWagesActualPercentageTotal.replace("store", getContext().getStringProperty("store"))
							.replace("Week", getContext().getStringProperty("SelectFiscalWeek"))
							.replace("DepartmentToView", getContext().getStringProperty("DepartmentToView")).replace("Division", getContext().getStringProperty("Division"))
							.replace("FinalResult", getContext().getStringProperty("FinalResult"))
							.replace("LYWk", getContext().getStringProperty("LYWk"))
							.replace("Year", getContext().getStringProperty("FinYr"))
							.replace("FinWk", getContext().getStringProperty("FinWk"))
							.replace("Division", getContext().getStringProperty("Division")));

			System.out.println("DB Value: " + DBWagesactualpercentValTtl);
		}

		UIValues = pageDTSDepartmentPO.UIResults(objDTSDepartmentObjects, extentTest,
				objDTSDepartmentObjects.ActualpercentWages, objDTSDepartmentObjects.PlannedSalesGreen,
				objDTSDepartmentObjects.TableRowsCount);
		pageWeeklyPlanningPO.CompareValueDecimalFields(extentTest, DBWagesactualpercentVal, UIValues);

		UITotal = pageDTSDepartmentPO.GetTotal(extentTest, objDTSDepartmentObjects.RowNameTotal,
				objDTSDepartmentObjects.ActualPerWagesTot);
		pageMonthlyPlanningPO.CompareValueDecimalFields(extentTest, DBWagesactualpercentValTtl, UITotal);

		// Budget Wage
		if (OptionToView.contains("Budget")) {
			Report_AddStep("testcase", " BUDGET WAGES- DAY WISE DATA", "", "", "");
			extentTest = report.startTest("Budget Wages");
			
			String BudgetWagesTotal = data.getText("BudgetWagesTotal");
			DBBudWageValTtl = sql.CLRexecuteQuery(getContext(),
					BudgetWagesTotal.replace("store", getContext().getStringProperty("store"))
							.replace("Week", getContext().getStringProperty("SelectFiscalWeek"))
							.replace("DepartmentToView", getContext().getStringProperty("DepartmentToView")).replace("Division", getContext().getStringProperty("Division"))
							.replace("FinalResult", getContext().getStringProperty("FinalResult"))
							.replace("LYWk", getContext().getStringProperty("LYWk")));

			System.out.println("DB Value: " + DBBudWageValTtl);

			UITotal = pageDTSDepartmentPO.GetTotal(extentTest, objDTSDepartmentObjects.RowNameTotal,
					objDTSDepartmentObjects.BudWagesTot);
			pageMonthlyPlanningPO.CompareValues(extentTest, DBBudWageValTtl, UITotal);
		}
		if (OptionToView.contains("Forecast")) {
			Report_AddStep("testcase", " FORECAST WAGES- DAY WISE DATA", "", "", "");
			extentTest = report.startTest("Forecast Wages");
			
			String ForecastWagesTotal = data.getText("ForecastWagesTotal");
			DBBudWageValTtl = sql.CLRexecuteQuery(getContext(),
					ForecastWagesTotal.replace("store", getContext().getStringProperty("store"))
							.replace("Week", getContext().getStringProperty("SelectFiscalWeek"))
							.replace("DepartmentToView", getContext().getStringProperty("DepartmentToView")).replace("Division", getContext().getStringProperty("Division"))
							.replace("FinalResult", getContext().getStringProperty("FinalResult"))
							.replace("LYWk", getContext().getStringProperty("LYWk")));

			System.out.println("DB Value: " + DBBudWageValTtl);

			UITotal = pageDTSDepartmentPO.GetTotal(extentTest, objDTSDepartmentObjects.RowNameTotal,
					objDTSDepartmentObjects.FcstWagesTot);
			pageMonthlyPlanningPO.CompareValues(extentTest, DBBudWageValTtl, UITotal);
		}
		// Budget Percentage
		if (OptionToView.contains("Budget")) {
			Report_AddStep("testcase", " BUDGET PERCENTAGE WAGES- DAY WISE DATA", "", "", "");
			extentTest = report.startTest("Budget Wages Prcnt");
			
			String BudgetPercentageTotal = data.getText("BudgetPercentageTotal");
			DBBudPerValTtl = sql.CLRexecuteQuery(getContext(),
					BudgetPercentageTotal.replace("store", getContext().getStringProperty("store"))
							.replace("Week", getContext().getStringProperty("SelectFiscalWeek"))
							.replace("DepartmentToView", getContext().getStringProperty("DepartmentToView")).replace("Division", getContext().getStringProperty("Division"))
							.replace("FinalResult", getContext().getStringProperty("FinalResult"))
							.replace("LYWk", getContext().getStringProperty("LYWk")));

			System.out.println("DB Value: " + DBBudPerValTtl);

			UITotal = pageDTSDepartmentPO.GetTotal(extentTest, objDTSDepartmentObjects.RowNameTotal,
					objDTSDepartmentObjects.BudPerWagesTot);
			pageMonthlyPlanningPO.CompareValueDecimalFields(extentTest, DBBudPerValTtl, UITotal);
		}
		if (OptionToView.contains("Forecast")) {
			Report_AddStep("testcase", " FORECAST PERCENTAGE WAGES- DAY WISE DATA", "", "", "");
			extentTest = report.startTest("Forecast Wages Prcnt");
			
			String ForecastPercentageTotal = data.getText("ForecastPercentageTotal");
			DBBudPerValTtl = sql.CLRexecuteQuery(getContext(),
					ForecastPercentageTotal.replace("store", getContext().getStringProperty("store"))
							.replace("Week", getContext().getStringProperty("SelectFiscalWeek"))
							.replace("DepartmentToView", getContext().getStringProperty("DepartmentToView")).replace("Division", getContext().getStringProperty("Division"))
							.replace("FinalResult", getContext().getStringProperty("FinalResult"))
							.replace("LYWk", getContext().getStringProperty("LYWk")));

			System.out.println("DB Value: " + DBBudPerValTtl);

			UITotal = pageDTSDepartmentPO.GetTotal(extentTest, objDTSDepartmentObjects.RowNameTotal,
					objDTSDepartmentObjects.FcstPerWagesTot);
			pageMonthlyPlanningPO.CompareValueDecimalFields(extentTest, DBBudPerValTtl, UITotal);
		}

		// Allowed Budget Wages
		if (OptionToView.contains("Budget")) {
			Report_AddStep("testcase", " ALLOWED BUDGET WAGES- DAY WISE DATA", "", "", "");
			extentTest = report.startTest("Allowed Budget Wages");
			
			int Result = pageDTSDepartmentPO.AllowedWagesValidation(extentTest, objDTSDepartmentObjects.BudSalesTot,
					objDTSDepartmentObjects.PlannedSalesTot, objDTSDepartmentObjects.ActualSalesTot);
			if (Result == 2) {
				String AllowedWagesBdgtOne = data.getText("AllowedWagesBdgtOne");
				DBAllowBudWagesValTtl = sql.CLRexecuteQuery(getContext(),
						AllowedWagesBdgtOne.replace("store", getContext().getStringProperty("store"))
								.replace("Week", getContext().getStringProperty("SelectFiscalWeek"))
								.replace("DepartmentToView", getContext().getStringProperty("DepartmentToView")).replace("Division", getContext().getStringProperty("Division"))
								.replace("FinalResult", getContext().getStringProperty("FinalResult"))
								.replace("LYWk", getContext().getStringProperty("LYWk")));
				System.out.println("DB Value: " + DBAllowBudWagesValTtl);
			} else {
				String AllowedWagesBdgtTwo = data.getText("AllowedWagesBdgtTwo");
				DBAllowBudWagesValTtl = sql.CLRexecuteQuery(getContext(),
						AllowedWagesBdgtTwo.replace("store", getContext().getStringProperty("store"))
								.replace("Week", getContext().getStringProperty("SelectFiscalWeek"))
								.replace("DepartmentToView", getContext().getStringProperty("DepartmentToView")).replace("Division", getContext().getStringProperty("Division"))
								.replace("FinalResult", getContext().getStringProperty("FinalResult"))
								.replace("LYWk", getContext().getStringProperty("LYWk")));
				System.out.println("DB Value: " + DBAllowBudWagesValTtl);
			}

			UITotal = pageDTSDepartmentPO.GetTotal(extentTest, objDTSDepartmentObjects.RowNameTotal,
					objDTSDepartmentObjects.AllowedBudWagesTot);
			pageMonthlyPlanningPO.CompareValues(extentTest, DBAllowBudWagesValTtl, UITotal);
		}
		if (OptionToView.contains("Forecast")) {
			Report_AddStep("testcase", " ALLOWED FORECAST WAGES- DAY WISE DATA", "", "", "");
			extentTest = report.startTest("Allowed Forecast Wages");
			
			int Result = pageDTSDepartmentPO.AllowedWagesValidation(extentTest, objDTSDepartmentObjects.FcstSalesTot,
					objDTSDepartmentObjects.PlannedSalesTot, objDTSDepartmentObjects.ActualSalesTot);
			if (Result == 2) {
				String AllowedWagesFcstOne = data.getText("AllowedWagesFcstOne");
				DBAllowBudWagesValTtl = sql.CLRexecuteQuery(getContext(),
						AllowedWagesFcstOne.replace("store", getContext().getStringProperty("store"))
								.replace("Week", getContext().getStringProperty("SelectFiscalWeek"))
								.replace("DepartmentToView", getContext().getStringProperty("DepartmentToView")).replace("Division", getContext().getStringProperty("Division"))
								.replace("FinalResult", getContext().getStringProperty("FinalResult"))
								.replace("LYWk", getContext().getStringProperty("LYWk")));
				System.out.println("DB Value: " + DBAllowBudWagesValTtl);
			} else {
				String AllowedWagesFcstTwo = data.getText("AllowedWagesFcstTwo");
				DBAllowBudWagesValTtl = sql.CLRexecuteQuery(getContext(),
						AllowedWagesFcstTwo.replace("store", getContext().getStringProperty("store"))
								.replace("Week", getContext().getStringProperty("SelectFiscalWeek"))
								.replace("DepartmentToView", getContext().getStringProperty("DepartmentToView")).replace("Division", getContext().getStringProperty("Division"))
								.replace("FinalResult", getContext().getStringProperty("FinalResult"))
								.replace("LYWk", getContext().getStringProperty("LYWk")));
				System.out.println("DB Value: " + DBAllowBudWagesValTtl);
			}

			UITotal = pageDTSDepartmentPO.GetTotal(extentTest, objDTSDepartmentObjects.RowNameTotal,
					objDTSDepartmentObjects.AllowedFcstWagesTot);
			pageMonthlyPlanningPO.CompareValues(extentTest, DBAllowBudWagesValTtl, UITotal);
		}
	}

	@Test(priority = 8)
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
		pageMonthlyPlanningGroupPO = PageFactory.initElements(this.getDriver(), MonthlyPlanningGroupPage.class);
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
					System.getProperty("user.dir") + "\\TestData\\WLP\\DailyTradingStatementDepartment.xml");
			customreport.createExtentReport();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

}