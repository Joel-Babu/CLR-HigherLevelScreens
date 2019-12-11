package au.wow.WLPmain.WeeklyTradingStatement;

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
import java.util.concurrent.TimeUnit;

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

import au.wow.WLPmain.objects.DailyPlanningMainObjects;
import au.wow.WLPmain.objects.DailyTradingStatementGroupObjects;
import au.wow.WLPmain.objects.DailyTradingStatementObjects;
import au.wow.WLPmain.objects.MonthlyPlanningGroupObjects;
import au.wow.WLPmain.objects.MonthlyPlanningStoreObjects;
import au.wow.WLPmain.objects.WeeklyPlanningStoreObjects;
import au.wow.WLPmain.objects.WeeklyTradingStatementGroupObjects;
import au.wow.WLPmain.objects.WeeklyTradingStatementStoreObjects;
import au.wow.WLPmain.objects.WeeklyTradingStatementZoneObjects;
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

public class WeeklyTradingStatementZone extends TestBase {
	protected Logger log = LogManager.getLogger(WeeklyTradingStatementZone.class);
	TestStatus status;
	ExtentReportsScreenshotCode ScreenShotUtil;
	SQLWrapper sql = new SQLWrapper(log);
	WoWLoginPage loginPage;
	CommonRepository CommonFunc;
	WeeklyPlanningStoreObjects objWeeklyPlanningStoreObjects;
	WeeklyTradingStatementStoreObjects objWeeklyTradingStatementStoreObjects;
	WeeklyTradingStatementZoneObjects objWeeklyTradingStatementZoneObjects;
	DailyTradingStatementGroupObjects objDailyTradingStatementGroupObjects;
	DailyTradingStatementStorePage pageDailyTradingStatementStorePO;
	WeeklyPlanningStorePage pageWeeklyPlanningPO;
	DailyTradingStatementGroupPage pageDailyTradingStatementGroupPO;
	MonthlyPlanningStorePage pageMonthlyPlanningPO;
	BasePage BaseObj;
	CommonFunctions common;
	StoreProperties storeprop;
	List<List<Object>> DBValues, DBTotal, UIValues, UITotal;
	List<List<Object>> ActlVal, ActlTtl, PlndVal, PlndTtl, VarPlndVal, VarPlndTtl, LYSlsVal, LYSlsTtl, VarLYSlsVal,
			VarLYSlsTtl, BdgtVal, BdgtTtl, VarBdgtTtl, VarBdgtVal, AvgBsktTtl, AvgBsktVal, MixVal, MixTtl, PlndItmVal,
			PlndItmTtl, ActlItmVal, ActlItmTtl, PlndItmPriceVal, PlndItmPriceTtl, ActlItmPriceVal, ActlItmPriceTtl,
			ThisYrCustVal, ThisYrCustTtl, LstYrCustVal, LstYrCustTtl, DataPenTtl, DataPenVal, ActlHrsVal, ActlHrsTtl,
			VarSMSTtl, VarSMSVal, PlndHrsTtl, PlndHrsVal, VarPlndHrsVal, VarPlndHrsTtl, ActlSMSTtl, ActlSMSVal,
			ActlORTtl, ActlORVal, ActlWgTtl, ActlWgVal, ActlWgPrcntTtl, ActlWgPrcntVal, BdgtWgTtl, BdgtWgVal,
			BdgtWgPrcntTtl, BdgtWgPrcntVal, PlndCPHTtl, PlndCPHVal, PlndWgTtl, PlndWgVal, PlndWgPrcntTtl,
			PlndWgPrcntVal, CPHWgTtl, CPHWgVal, VarBdgtWgTtl, VarBdgtWgVal, VarSMSHr, VarSMSHrTtl, ActlSMSHrTtl,
			ActlSMSHr, DBActlSMSHrTtl, DBActlSMSHr, DBVarSMSHrHr, DBVarSMSHrTtl, ORHr, ORHrTtl, DBORHr, DBORHrTtl;
	List<List<Object>> DBActlVal, DBActlTtl, DBPlndVal, DBPlndTtl, DBVarPlndVal, DBVarPlndTtl, DBLYSlsVal, DBLYSlsTtl,
			DBVarLYSlsVal, DBVarLYSlsTtl, DBBdgtVal, DBBdgtTtl, DBVarBdgtTtl, DBVarBdgtVal, DBAvgBsktTtl, DBAvgBsktVal,
			DBMixVal, DBMixTtl, DBPlndItmVal, DBPlndItmTtl, DBActlItmVal, DBActlItmTtl, DBPlndItmPriceVal,
			DBPlndItmPriceTtl, DBActlItmPriceVal, DBActlItmPriceTtl, DBThisYrCustVal, DBThisYrCustTtl, DBLstYrCustVal,
			DBLstYrCustTtl, DBDataPenTtl, DBDataPenVal, DBActlHrsVal, DBActlHrsTtl, DBVarSMSTtl, DBVarSMSVal,
			DBPlndHrsTtl, DBPlndHrsVal, DBVarPlndHrsVal, DBVarPlndHrsTtl, DBActlSMSTtl, DBActlSMSVal, DBActlORTtl,
			DBActlORVal, DBActlWgTtl, DBActlWgVal, DBActlWgPrcntTtl, DBActlWgPrcntVal, DBBdgtWgTtl, DBBdgtWgVal,
			DBBdgtWgPrcntTtl, DBBdgtWgPrcntVal, DBPlndCPHTtl, DBPlndCPHVal, DBPlndWgTtl, DBPlndWgVal, DBPlndWgPrcntTtl,
			DBPlndWgPrcntVal, DBCPHWgTtl, DBCPHWgVal, DBVarBdgtWgTtl, DBVarBdgtWgVal,DBAllowedWgVal, AllowedWgVal,DBAllowedWgTtl, AllowedWgTtl;
	String OptionToView;

	public WeeklyTradingStatementZone() {
		super.log = log;
	}

	@Test(priority = 0)
	public void LogintoCLRApplication() throws Exception {
		loginPage = new WoWLoginPage().createPage(getDriver(), getContext(), log, status, data, report, extentTest,
				WoWLoginPage.class);
		pageWeeklyPlanningPO = loginPage.navToWeeklyPlanningStorePg(getContext().getStringProperty("store"));
	}

	// Sales
	@Test(description = "Weekly Trading Statement - Zone, SalesColumnValidation", priority = 1)
	public void SalesColumnValidation() throws Exception {
		pageWeeklyPlanningPO = new WeeklyPlanningStorePage().createPage(getDriver(), getContext(), log, status, data,
				getReport(), getReportLogger(), WeeklyPlanningStorePage.class);
		pageDailyTradingStatementGroupPO = new MonthlyPlanningGroupPage().createPage(getDriver(), getContext(), log,
				status, data, getReport(), getReportLogger(), DailyTradingStatementGroupPage.class);
		TestStatus status = getTestStatus();
		System.out.println("Entering into TestCase: Sales Column Metrics Validation");
		pageWeeklyPlanningPO.SelectWeekFromDropdown(objWeeklyPlanningStoreObjects, extentTest,
				getContext().getStringProperty("SelectFiscalWeek"));

		pageWeeklyPlanningPO.selectPage(extentTest, objWeeklyPlanningStoreObjects.menuBar,
				objWeeklyPlanningStoreObjects.tradingScreens, "Trading Screens");
		pageWeeklyPlanningPO.selectPage(extentTest, objWeeklyPlanningStoreObjects.menuBar,
				objWeeklyPlanningStoreObjects.weeklyTrading, "Weekly Trading");
		pageWeeklyPlanningPO.selectPage(extentTest, objWeeklyPlanningStoreObjects.menuBar,
				objWeeklyPlanningStoreObjects.screenToViewWeeklyTrading, "Zone");
		//pageWeeklyPlanningPO.verifyPageName(extentTest, objWeeklyPlanningStoreObjects.DataFrame,
			//	objWeeklyPlanningStoreObjects.pageName, getContext().getStringProperty("WTSZone"));

		OptionToView = getContext().getStringProperty("OptionToView");

		// ACTUAL SALES:
		Report_AddStep("testcase", " ACTUAL SALES - WEEK WISE DATA", "", "", "");
		extentTest = report.startTest("Actual Sales");

		String ActualSales = data.getText("ActualSales");
		DBActlVal = sql.CLRexecuteQuery(getContext(),
				ActualSales.replace("Region", getContext().getStringProperty("Region"))
						.replace("Zone", getContext().getStringProperty("Zone"))
						.replace("LYWk", getContext().getStringProperty("LYWk"))
						.replace("Week", getContext().getStringProperty("SelectFiscalWeek"))
						.replace("Division", getContext().getStringProperty("Division")));
		System.out.println("DB Value: " + DBActlVal);

		String ActualSalesTotal = data.getText("ActualSalesTotal");
		DBActlTtl = sql.CLRexecuteQuery(getContext(),
				ActualSalesTotal.replace("Region", getContext().getStringProperty("Region"))
						.replace("Zone", getContext().getStringProperty("Zone"))
						.replace("LYWk", getContext().getStringProperty("LYWk"))
						.replace("Week", getContext().getStringProperty("SelectFiscalWeek"))
						.replace("Division", getContext().getStringProperty("Division")));
		System.out.println("DB Value: " + DBActlTtl);

		// TimeUnit.MINUTES.sleep(30);
		ActlVal = pageDailyTradingStatementGroupPO.RowWiseResult(objWeeklyTradingStatementStoreObjects,
				objWeeklyTradingStatementZoneObjects.Departments, objWeeklyTradingStatementZoneObjects.ActualSales,
				objWeeklyTradingStatementZoneObjects.DataTable);
		ActlTtl = pageDailyTradingStatementGroupPO.WeekToDateUITotal(objWeeklyTradingStatementZoneObjects.TotalTitle,
				objWeeklyTradingStatementZoneObjects.ActualSalesTtl);

		pageMonthlyPlanningPO.CompareValues(extentTest, DBActlVal, ActlVal);
		pageMonthlyPlanningPO.CompareValues(extentTest, DBActlTtl, ActlTtl);

		// PLANNED SALES:
		Report_AddStep("testcase", " PLANNED SALES - WEEK WISE DATA", "", "", "");
		extentTest = report.startTest("Planned Sales");

		String PlannedSales = data.getText("PlannedSales");
		DBPlndVal = sql.CLRexecuteQuery(getContext(),
				PlannedSales.replace("Region", getContext().getStringProperty("Region"))
						.replace("Zone", getContext().getStringProperty("Zone"))
						.replace("LYWk", getContext().getStringProperty("LYWk"))
						.replace("Week", getContext().getStringProperty("SelectFiscalWeek"))
						.replace("Division", getContext().getStringProperty("Division")));
		System.out.println("DB Value: " + DBPlndVal);

		String PlannedSalesTotal = data.getText("PlannedSalesTotal");
		DBPlndTtl = sql.CLRexecuteQuery(getContext(),
				PlannedSalesTotal.replace("Region", getContext().getStringProperty("Region"))
						.replace("Zone", getContext().getStringProperty("Zone"))
						.replace("LYWk", getContext().getStringProperty("LYWk"))
						.replace("Week", getContext().getStringProperty("SelectFiscalWeek"))
						.replace("Division", getContext().getStringProperty("Division")));
		System.out.println("DB Value: " + DBPlndTtl);

		PlndVal = pageDailyTradingStatementGroupPO.RowWiseResult(objWeeklyTradingStatementStoreObjects,
				objWeeklyTradingStatementZoneObjects.Departments, objWeeklyTradingStatementZoneObjects.PlannedSales,
				objWeeklyTradingStatementZoneObjects.DataTable);
		PlndTtl = pageDailyTradingStatementGroupPO.WeekToDateUITotal(objWeeklyTradingStatementZoneObjects.TotalTitle,
				objWeeklyTradingStatementZoneObjects.PlannedSalesTtl);

		pageMonthlyPlanningPO.CompareValues(extentTest, DBPlndVal, PlndVal);
		pageMonthlyPlanningPO.CompareValues(extentTest, DBPlndTtl, PlndTtl);

		// VARIANCE IN PLANNED SALES:
		Report_AddStep("testcase", " VARIANCE IN PLANNED SALES - WEEK WISE DATA", "", "", "");
		extentTest = report.startTest("Variance Sales");

		String VarSales = data.getText("VarSales");
		DBVarPlndVal = sql.CLRexecuteQuery(getContext(),
				VarSales.replace("Region", getContext().getStringProperty("Region"))
						.replace("Zone", getContext().getStringProperty("Zone"))
						.replace("LYWk", getContext().getStringProperty("LYWk"))
						.replace("Week", getContext().getStringProperty("SelectFiscalWeek"))
						.replace("Division", getContext().getStringProperty("Division")));
		System.out.println("DB Value: " + DBVarPlndVal);

		String VarSalesTotal = data.getText("VarSalesTotal");
		DBVarPlndTtl = sql.CLRexecuteQuery(getContext(),
				VarSalesTotal.replace("Region", getContext().getStringProperty("Region"))
						.replace("Zone", getContext().getStringProperty("Zone"))
						.replace("LYWk", getContext().getStringProperty("LYWk"))
						.replace("Week", getContext().getStringProperty("SelectFiscalWeek"))
						.replace("Division", getContext().getStringProperty("Division")));
		System.out.println("DB Value: " + DBVarPlndTtl);

		VarPlndVal = pageDailyTradingStatementGroupPO.RowWiseResult(objWeeklyTradingStatementStoreObjects,
				objWeeklyTradingStatementZoneObjects.Departments,
				objWeeklyTradingStatementZoneObjects.PlannedSalesGrwth, objWeeklyTradingStatementZoneObjects.DataTable);
		VarPlndTtl = pageDailyTradingStatementGroupPO.WeekToDateUITotal(objWeeklyTradingStatementZoneObjects.TotalTitle,
				objWeeklyTradingStatementZoneObjects.PlannedSalesGrwthTtl);

		pageMonthlyPlanningPO.CompareValues(extentTest, DBVarPlndVal, VarPlndVal);
		pageMonthlyPlanningPO.CompareValues(extentTest, DBVarPlndTtl, VarPlndTtl);

		// LAST YEAR SALES:
		Report_AddStep("testcase", " LAST YEAR SALES - WEEK WISE DATA", "", "", "");
		extentTest = report.startTest("LY Sales");

		String LYSales = data.getText("LYSales");
		DBLYSlsVal = sql.CLRexecuteQuery(getContext(),
				LYSales.replace("Region", getContext().getStringProperty("Region"))
						.replace("Zone", getContext().getStringProperty("Zone"))
						.replace("LYWk", getContext().getStringProperty("LYWk"))
						.replace("Week", getContext().getStringProperty("SelectFiscalWeek"))
						.replace("Division", getContext().getStringProperty("Division")));
		System.out.println("DB Value: " + DBLYSlsVal);

		String LYSalesTotal = data.getText("LYSalesTotal");
		DBLYSlsTtl = sql.CLRexecuteQuery(getContext(),
				LYSalesTotal.replace("Region", getContext().getStringProperty("Region"))
						.replace("Zone", getContext().getStringProperty("Zone"))
						.replace("LYWk", getContext().getStringProperty("LYWk"))
						.replace("Week", getContext().getStringProperty("SelectFiscalWeek"))
						.replace("Division", getContext().getStringProperty("Division")));
		System.out.println("DB Value: " + DBLYSlsTtl);

		LYSlsVal = pageDailyTradingStatementGroupPO.RowWiseResult(objWeeklyTradingStatementStoreObjects,
				objWeeklyTradingStatementZoneObjects.Departments, objWeeklyTradingStatementZoneObjects.LYSales,
				objWeeklyTradingStatementZoneObjects.DataTable);
		LYSlsTtl = pageDailyTradingStatementGroupPO.WeekToDateUITotal(objWeeklyTradingStatementZoneObjects.TotalTitle,
				objWeeklyTradingStatementZoneObjects.LYSalesTtl);

		pageMonthlyPlanningPO.CompareValues(extentTest, DBLYSlsVal, LYSlsVal);
		pageMonthlyPlanningPO.CompareValues(extentTest, DBLYSlsTtl, LYSlsTtl);

		// VARIANCE IN LAST YEAR SALES:
		Report_AddStep("testcase", " VARIANCE IN LAST YEAR SALES - WEEK WISE DATA", "", "", "");
		extentTest = report.startTest("Variance LY Sales");

		String LYSalesPct = data.getText("LYSalesPct");
		DBVarLYSlsVal = sql.CLRexecuteQuery(getContext(),
				LYSalesPct.replace("Region", getContext().getStringProperty("Region"))
						.replace("Zone", getContext().getStringProperty("Zone"))
						.replace("LYWk", getContext().getStringProperty("LYWk"))
						.replace("Week", getContext().getStringProperty("SelectFiscalWeek"))
						.replace("Division", getContext().getStringProperty("Division")));
		System.out.println("DB Value: " + DBVarLYSlsVal);

		String LYSalesPctTotal = data.getText("LYSalesPctTotal");
		DBVarLYSlsTtl = sql.CLRexecuteQuery(getContext(),
				LYSalesPctTotal.replace("Region", getContext().getStringProperty("Region"))
						.replace("Zone", getContext().getStringProperty("Zone"))
						.replace("LYWk", getContext().getStringProperty("LYWk"))
						.replace("Week", getContext().getStringProperty("SelectFiscalWeek"))
						.replace("Division", getContext().getStringProperty("Division")));
		System.out.println("DB Value: " + DBVarLYSlsTtl);

		VarLYSlsVal = pageDailyTradingStatementGroupPO.RowWiseResult(objWeeklyTradingStatementStoreObjects,
				objWeeklyTradingStatementZoneObjects.Departments, objWeeklyTradingStatementZoneObjects.LYSalesGrwth,
				objWeeklyTradingStatementZoneObjects.DataTable);
		VarLYSlsTtl = pageDailyTradingStatementGroupPO.WeekToDateUITotal(
				objWeeklyTradingStatementZoneObjects.TotalTitle, objWeeklyTradingStatementZoneObjects.LYSalesGrwthTtl);

		pageMonthlyPlanningPO.CompareValues(extentTest, DBVarLYSlsVal, VarLYSlsVal);
		pageMonthlyPlanningPO.CompareValues(extentTest, DBVarLYSlsTtl, VarLYSlsTtl);

		// BUDGET SALES:
		if (OptionToView.contains("Budget")) {
			Report_AddStep("testcase", "BUDGET SALES - WEEK WISE DATA", "", "", "");
			extentTest = report.startTest("Budget Sales");

			String BudgetSales = data.getText("BudgetSales");
			DBBdgtVal = sql.CLRexecuteQuery(getContext(),
					BudgetSales.replace("Region", getContext().getStringProperty("Region"))
							.replace("Zone", getContext().getStringProperty("Zone"))
							.replace("LYWk", getContext().getStringProperty("LYWk"))
							.replace("Week", getContext().getStringProperty("SelectFiscalWeek"))
							.replace("Division", getContext().getStringProperty("Division")));
			System.out.println("DB Value: " + DBBdgtVal);

			String BudgetSalesTotal = data.getText("BudgetSalesTotal");
			DBBdgtTtl = sql.CLRexecuteQuery(getContext(),
					BudgetSalesTotal.replace("Region", getContext().getStringProperty("Region"))
							.replace("Zone", getContext().getStringProperty("Zone"))
							.replace("LYWk", getContext().getStringProperty("LYWk"))
							.replace("Week", getContext().getStringProperty("SelectFiscalWeek"))
							.replace("Division", getContext().getStringProperty("Division")));
			System.out.println("DB Value: " + DBBdgtTtl);

			BdgtVal = pageDailyTradingStatementGroupPO.RowWiseResult(objWeeklyTradingStatementStoreObjects,
					objWeeklyTradingStatementZoneObjects.Departments, objWeeklyTradingStatementZoneObjects.BudgetSales,
					objWeeklyTradingStatementZoneObjects.DataTable);
			BdgtTtl = pageDailyTradingStatementGroupPO.WeekToDateUITotal(
					objWeeklyTradingStatementZoneObjects.TotalTitle,
					objWeeklyTradingStatementZoneObjects.BudgetSalesTtl);

			pageMonthlyPlanningPO.CompareValues(extentTest, DBBdgtVal, BdgtVal);
			pageMonthlyPlanningPO.CompareValues(extentTest, DBBdgtTtl, BdgtTtl);
		}
		if (OptionToView.contains("Forecast")) {
			Report_AddStep("testcase", "FORECAST SALES - WEEK WISE DATA", "", "", "");
			extentTest = report.startTest("Forecast Sales");

			String ForecastSales = data.getText("ForecastSales");
			DBBdgtVal = sql.CLRexecuteQuery(getContext(),
					ForecastSales.replace("Region", getContext().getStringProperty("Region"))
							.replace("Zone", getContext().getStringProperty("Zone"))
							.replace("LYWk", getContext().getStringProperty("LYWk"))
							.replace("Week", getContext().getStringProperty("SelectFiscalWeek"))
							.replace("Division", getContext().getStringProperty("Division")));
			System.out.println("DB Value: " + DBBdgtVal);

			String ForecastSalesTotal = data.getText("ForecastSalesTotal");
			DBBdgtTtl = sql.CLRexecuteQuery(getContext(),
					ForecastSalesTotal.replace("Region", getContext().getStringProperty("Region"))
							.replace("Zone", getContext().getStringProperty("Zone"))
							.replace("LYWk", getContext().getStringProperty("LYWk"))
							.replace("Week", getContext().getStringProperty("SelectFiscalWeek"))
							.replace("Division", getContext().getStringProperty("Division")));
			System.out.println("DB Value: " + DBBdgtTtl);

			BdgtVal = pageDailyTradingStatementGroupPO.RowWiseResult(objWeeklyTradingStatementStoreObjects,
					objWeeklyTradingStatementZoneObjects.Departments, objWeeklyTradingStatementZoneObjects.BudgetSales,
					objWeeklyTradingStatementZoneObjects.DataTable);
			BdgtTtl = pageDailyTradingStatementGroupPO.WeekToDateUITotal(
					objWeeklyTradingStatementZoneObjects.TotalTitle,
					objWeeklyTradingStatementZoneObjects.BudgetSalesTtl);

			pageMonthlyPlanningPO.CompareValues(extentTest, DBBdgtVal, BdgtVal);
			pageMonthlyPlanningPO.CompareValues(extentTest, DBBdgtTtl, BdgtTtl);
		}

		// VARIANCE IN BUDGET SALES:
		if (OptionToView.contains("Budget")) {
			Report_AddStep("testcase", "VARIANCE IN BUDGET SALES - WEEK WISE DATA", "", "", "");
			extentTest = report.startTest("Variance budget Sales");

			String VarBudgetSales = data.getText("VarBudgetSales");
			DBVarBdgtVal = sql.CLRexecuteQuery(getContext(),
					VarBudgetSales.replace("Region", getContext().getStringProperty("Region"))
							.replace("Zone", getContext().getStringProperty("Zone"))
							.replace("LYWk", getContext().getStringProperty("LYWk"))
							.replace("Week", getContext().getStringProperty("SelectFiscalWeek"))
							.replace("Division", getContext().getStringProperty("Division")));
			System.out.println("DB Value: " + DBVarBdgtVal);

			String VarBudgetSalesTotal = data.getText("VarBudgetSalesTotal");
			DBVarBdgtTtl = sql.CLRexecuteQuery(getContext(),
					VarBudgetSalesTotal.replace("Region", getContext().getStringProperty("Region"))
							.replace("Zone", getContext().getStringProperty("Zone"))
							.replace("LYWk", getContext().getStringProperty("LYWk"))
							.replace("Week", getContext().getStringProperty("SelectFiscalWeek"))
							.replace("Division", getContext().getStringProperty("Division")));
			System.out.println("DB Value: " + DBVarBdgtTtl);

			VarBdgtVal = pageDailyTradingStatementGroupPO.RowWiseResult(objWeeklyTradingStatementStoreObjects,
					objWeeklyTradingStatementZoneObjects.Departments,
					objWeeklyTradingStatementZoneObjects.BudgetSalesGrwth,
					objWeeklyTradingStatementZoneObjects.DataTable);
			VarBdgtTtl = pageDailyTradingStatementGroupPO.WeekToDateUITotal(
					objWeeklyTradingStatementZoneObjects.TotalTitle,
					objWeeklyTradingStatementZoneObjects.BudgetSalesGrwthTtl);

			pageMonthlyPlanningPO.CompareValues(extentTest, DBVarBdgtVal, VarBdgtVal);
			pageMonthlyPlanningPO.CompareValues(extentTest, DBVarBdgtTtl, VarBdgtTtl);
		}
		if (OptionToView.contains("Forecast")) {
			Report_AddStep("testcase", "VARIANCE IN FORECAST SALES - WEEK WISE DATA", "", "", "");
			extentTest = report.startTest("Variance Forecast Sales");

			String VarFcstSales = data.getText("VarFcstSales");
			DBVarBdgtVal = sql.CLRexecuteQuery(getContext(),
					VarFcstSales.replace("Region", getContext().getStringProperty("Region"))
							.replace("Zone", getContext().getStringProperty("Zone"))
							.replace("LYWk", getContext().getStringProperty("LYWk"))
							.replace("Week", getContext().getStringProperty("SelectFiscalWeek"))
							.replace("Division", getContext().getStringProperty("Division")));
			System.out.println("DB Value: " + DBVarBdgtVal);

			String VarFcstSalesTotal = data.getText("VarFcstSalesTotal");
			DBVarBdgtTtl = sql.CLRexecuteQuery(getContext(),
					VarFcstSalesTotal.replace("Region", getContext().getStringProperty("Region"))
							.replace("Zone", getContext().getStringProperty("Zone"))
							.replace("LYWk", getContext().getStringProperty("LYWk"))
							.replace("Week", getContext().getStringProperty("SelectFiscalWeek"))
							.replace("Division", getContext().getStringProperty("Division")));
			System.out.println("DB Value: " + DBVarBdgtTtl);

			VarBdgtVal = pageDailyTradingStatementGroupPO.RowWiseResult(objWeeklyTradingStatementStoreObjects,
					objWeeklyTradingStatementZoneObjects.Departments,
					objWeeklyTradingStatementZoneObjects.BudgetSalesGrwth,
					objWeeklyTradingStatementZoneObjects.DataTable);
			VarBdgtTtl = pageDailyTradingStatementGroupPO.WeekToDateUITotal(
					objWeeklyTradingStatementZoneObjects.TotalTitle,
					objWeeklyTradingStatementZoneObjects.BudgetSalesGrwthTtl);

			pageMonthlyPlanningPO.CompareValues(extentTest, DBVarBdgtVal, VarBdgtVal);
			pageMonthlyPlanningPO.CompareValues(extentTest, DBVarBdgtTtl, VarBdgtTtl);
		}

		// AVERAGE BASKET SALES:
		Report_AddStep("testcase", "AVERAGE BASKET SALES - WEEK WISE DATA", "", "", "");
		extentTest = report.startTest("Average Basket Sales");

		String AvgBasketSales = data.getText("AvgBasketSales");
		DBAvgBsktVal = sql.CLRexecuteQuery(getContext(),
				AvgBasketSales.replace("Region", getContext().getStringProperty("Region"))
						.replace("Zone", getContext().getStringProperty("Zone"))
						.replace("LYWk", getContext().getStringProperty("LYWk"))
						.replace("Week", getContext().getStringProperty("SelectFiscalWeek"))
						.replace("Division", getContext().getStringProperty("Division")));
		System.out.println("DB Value: " + DBAvgBsktVal);

		String AvgBasketSalesTotal = data.getText("AvgBasketSalesTotal");
		DBAvgBsktTtl = sql.CLRexecuteQuery(getContext(),
				AvgBasketSalesTotal.replace("Region", getContext().getStringProperty("Region"))
						.replace("Zone", getContext().getStringProperty("Zone"))
						.replace("LYWk", getContext().getStringProperty("LYWk"))
						.replace("Week", getContext().getStringProperty("SelectFiscalWeek"))
						.replace("Division", getContext().getStringProperty("Division")));
		System.out.println("DB Value: " + DBAvgBsktTtl);

		AvgBsktVal = pageDailyTradingStatementGroupPO.RowWiseResult(objWeeklyTradingStatementStoreObjects,
				objWeeklyTradingStatementZoneObjects.Departments, objWeeklyTradingStatementZoneObjects.AvgBasketSales,
				objWeeklyTradingStatementZoneObjects.DataTable);
		AvgBsktTtl = pageDailyTradingStatementGroupPO.WeekToDateUITotal(objWeeklyTradingStatementZoneObjects.TotalTitle,
				objWeeklyTradingStatementZoneObjects.AvgBasketSalesTtl);

		pageMonthlyPlanningPO.CompareValues(extentTest, DBAvgBsktVal, AvgBsktVal);
		pageMonthlyPlanningPO.CompareValues(extentTest, DBAvgBsktTtl, AvgBsktTtl);

		// MIX SALES:
		Report_AddStep("testcase", "MIX SALES - WEEK WISE DATA", "", "", "");
		extentTest = report.startTest("Mix Sales");

		String WkyMixSales = data.getText("WkyMixSales");
		DBMixVal = sql.CLRexecuteQuery(getContext(),
				WkyMixSales.replace("Region", getContext().getStringProperty("Region"))
						.replace("Zone", getContext().getStringProperty("Zone"))
						.replace("LYWk", getContext().getStringProperty("LYWk"))
						.replace("Week", getContext().getStringProperty("SelectFiscalWeek"))
						.replace("Division", getContext().getStringProperty("Division")));
		System.out.println("DB Value: " + DBMixVal);

		String WkyMixSalesTotal = data.getText("WkyMixSalesTotal");
		DBMixTtl = sql.CLRexecuteQuery(getContext(),
				WkyMixSalesTotal.replace("Region", getContext().getStringProperty("Region"))
						.replace("Zone", getContext().getStringProperty("Zone"))
						.replace("LYWk", getContext().getStringProperty("LYWk"))
						.replace("Week", getContext().getStringProperty("SelectFiscalWeek"))
						.replace("Division", getContext().getStringProperty("Division")));
		System.out.println("DB Value: " + DBMixTtl);

		MixVal = pageDailyTradingStatementGroupPO.RowWiseResult(objWeeklyTradingStatementStoreObjects,
				objWeeklyTradingStatementZoneObjects.Departments, objWeeklyTradingStatementZoneObjects.Mix,
				objWeeklyTradingStatementZoneObjects.DataTable);
		MixTtl = pageDailyTradingStatementGroupPO.WeekToDateUITotal(objWeeklyTradingStatementZoneObjects.TotalTitle,
				objWeeklyTradingStatementZoneObjects.MixTtl);

		pageMonthlyPlanningPO.CompareValues(extentTest, DBMixVal, MixVal);
		pageMonthlyPlanningPO.CompareValues(extentTest, DBMixTtl, MixTtl);
	}

	// Items and Item Price
	@Test(description = "Weekly Trading Statement - Zone, Planned Item", priority = 2)
	public void ItemsColumnValidation() throws Exception {
		pageWeeklyPlanningPO = new WeeklyPlanningStorePage().createPage(getDriver(), getContext(), log, status, data,
				getReport(), getReportLogger(), WeeklyPlanningStorePage.class);
		pageDailyTradingStatementGroupPO = new MonthlyPlanningGroupPage().createPage(getDriver(), getContext(), log,
				status, data, getReport(), getReportLogger(), DailyTradingStatementGroupPage.class);
		TestStatus status = getTestStatus();
		System.out.println("Entering into TestCase : Item View");

		// PLANNED ITEMS:
		Report_AddStep("testcase", "PLANNED ITEMS - WEEK WISE DATA", "", "", "");
		extentTest = report.startTest("Planned Items");

		String PlannedItems = data.getText("PlannedItems");
		DBPlndItmVal = sql.CLRexecuteQuery(getContext(),
				PlannedItems.replace("Region", getContext().getStringProperty("Region"))
						.replace("Zone", getContext().getStringProperty("Zone"))
						.replace("LYWk", getContext().getStringProperty("LYWk"))
						.replace("Week", getContext().getStringProperty("SelectFiscalWeek"))
						.replace("Division", getContext().getStringProperty("Division")));
		System.out.println("DB Value: " + DBPlndItmVal);

		String PlannedItemsTotal = data.getText("PlannedItemsTotal");
		DBPlndItmTtl = sql.CLRexecuteQuery(getContext(),
				PlannedItemsTotal.replace("Region", getContext().getStringProperty("Region"))
						.replace("Zone", getContext().getStringProperty("Zone"))
						.replace("LYWk", getContext().getStringProperty("LYWk"))
						.replace("Week", getContext().getStringProperty("SelectFiscalWeek"))
						.replace("Division", getContext().getStringProperty("Division")));
		System.out.println("DB Value: " + DBPlndItmTtl);

		PlndItmVal = pageDailyTradingStatementGroupPO.RowWiseResult(objWeeklyTradingStatementStoreObjects,
				objWeeklyTradingStatementZoneObjects.Departments, objWeeklyTradingStatementZoneObjects.PlannedItem,
				objWeeklyTradingStatementZoneObjects.DataTable);
		PlndItmTtl = pageDailyTradingStatementGroupPO.WeekToDateUITotal(objWeeklyTradingStatementZoneObjects.TotalTitle,
				objWeeklyTradingStatementZoneObjects.PlannedItemTtl);

		pageMonthlyPlanningPO.CompareValues(extentTest, DBPlndItmVal, PlndItmVal);
		pageMonthlyPlanningPO.CompareValues(extentTest, DBPlndItmTtl, PlndItmTtl);

		// ACTUAL ITEMS:
		Report_AddStep("testcase", "ACTUAL ITEMS - WEEK WISE DATA", "", "", "");
		extentTest = report.startTest("Actual Items");

		String ActualItems = data.getText("ActualItems");
		DBActlItmVal = sql.CLRexecuteQuery(getContext(),
				ActualItems.replace("Region", getContext().getStringProperty("Region"))
						.replace("Zone", getContext().getStringProperty("Zone"))
						.replace("LYWk", getContext().getStringProperty("LYWk"))
						.replace("Week", getContext().getStringProperty("SelectFiscalWeek"))
						.replace("Division", getContext().getStringProperty("Division")));
		System.out.println("DB Value: " + DBActlItmVal);

		String ActualItemsTotal = data.getText("ActualItemsTotal");
		DBActlItmTtl = sql.CLRexecuteQuery(getContext(),
				ActualItemsTotal.replace("Region", getContext().getStringProperty("Region"))
						.replace("Zone", getContext().getStringProperty("Zone"))
						.replace("LYWk", getContext().getStringProperty("LYWk"))
						.replace("Week", getContext().getStringProperty("SelectFiscalWeek"))
						.replace("Division", getContext().getStringProperty("Division")));
		System.out.println("DB Value: " + DBActlItmTtl);

		ActlItmVal = pageDailyTradingStatementGroupPO.RowWiseResult(objWeeklyTradingStatementStoreObjects,
				objWeeklyTradingStatementZoneObjects.Departments, objWeeklyTradingStatementZoneObjects.ActualItem,
				objWeeklyTradingStatementZoneObjects.DataTable);
		ActlItmTtl = pageDailyTradingStatementGroupPO.WeekToDateUITotal(objWeeklyTradingStatementZoneObjects.TotalTitle,
				objWeeklyTradingStatementZoneObjects.ActualItemTtl);

		pageMonthlyPlanningPO.CompareValues(extentTest, DBActlItmVal, ActlItmVal);
		pageMonthlyPlanningPO.CompareValues(extentTest, DBActlItmTtl, ActlItmTtl);

		// PLANNED ITEM PRICE:
		Report_AddStep("testcase", "PLANNED ITEM PRICE - WEEK WISE DATA", "", "", "");
		extentTest = report.startTest("Planned Item Price");

		String PlannedItemPrice = data.getText("PlannedItemPrice");
		DBPlndItmPriceVal = sql.CLRexecuteQuery(getContext(),
				PlannedItemPrice.replace("Region", getContext().getStringProperty("Region"))
						.replace("Zone", getContext().getStringProperty("Zone"))
						.replace("LYWk", getContext().getStringProperty("LYWk"))
						.replace("Week", getContext().getStringProperty("SelectFiscalWeek"))
						.replace("Division", getContext().getStringProperty("Division")));
		System.out.println("DB Value: " + DBPlndItmPriceVal);

		String PlannedItemPriceTotal = data.getText("PlannedItemPriceTotal");
		DBPlndItmPriceTtl = sql.CLRexecuteQuery(getContext(),
				PlannedItemPriceTotal.replace("Region", getContext().getStringProperty("Region"))
						.replace("Zone", getContext().getStringProperty("Zone"))
						.replace("LYWk", getContext().getStringProperty("LYWk"))
						.replace("Week", getContext().getStringProperty("SelectFiscalWeek"))
						.replace("Division", getContext().getStringProperty("Division")));
		System.out.println("DB Value: " + DBPlndItmPriceTtl);

		PlndItmPriceVal = pageDailyTradingStatementGroupPO.RowWiseResult(objWeeklyTradingStatementStoreObjects,
				objWeeklyTradingStatementZoneObjects.Departments, objWeeklyTradingStatementZoneObjects.PlannedItemPrice,
				objWeeklyTradingStatementZoneObjects.DataTable);
		PlndItmPriceTtl = pageDailyTradingStatementGroupPO.WeekToDateUITotal(
				objWeeklyTradingStatementZoneObjects.TotalTitle,
				objWeeklyTradingStatementZoneObjects.PlannedItemPriceTtl);

		pageMonthlyPlanningPO.CompareValueDecimalFields(extentTest, DBPlndItmPriceVal, PlndItmPriceVal);
		pageMonthlyPlanningPO.CompareValueDecimalFields(extentTest, DBPlndItmPriceTtl, PlndItmPriceTtl);

		// ACTUAL ITEM PRICE:
		Report_AddStep("testcase", "ACTUAL ITEM PRICE - WEEK WISE DATA", "", "", "");
		extentTest = report.startTest("Actual Item Price");

		String ActualItemPrice = data.getText("ActualItemPrice");
		DBActlItmPriceVal = sql.CLRexecuteQuery(getContext(),
				ActualItemPrice.replace("Region", getContext().getStringProperty("Region"))
						.replace("Zone", getContext().getStringProperty("Zone"))
						.replace("LYWk", getContext().getStringProperty("LYWk"))
						.replace("Week", getContext().getStringProperty("SelectFiscalWeek"))
						.replace("Division", getContext().getStringProperty("Division")));
		System.out.println("DB Value: " + DBActlItmPriceVal);

		String ActualItemPriceTotal = data.getText("ActualItemPriceTotal");
		DBActlItmPriceTtl = sql.CLRexecuteQuery(getContext(),
				ActualItemPriceTotal.replace("Region", getContext().getStringProperty("Region"))
						.replace("Zone", getContext().getStringProperty("Zone"))
						.replace("LYWk", getContext().getStringProperty("LYWk"))
						.replace("Week", getContext().getStringProperty("SelectFiscalWeek"))
						.replace("Division", getContext().getStringProperty("Division")));
		System.out.println("DB Value: " + DBActlItmPriceTtl);

		ActlItmPriceVal = pageDailyTradingStatementGroupPO.RowWiseResult(objWeeklyTradingStatementStoreObjects,
				objWeeklyTradingStatementZoneObjects.Departments, objWeeklyTradingStatementZoneObjects.ActualItemPrice,
				objWeeklyTradingStatementZoneObjects.DataTable);
		ActlItmPriceTtl = pageDailyTradingStatementGroupPO.WeekToDateUITotal(
				objWeeklyTradingStatementZoneObjects.TotalTitle,
				objWeeklyTradingStatementZoneObjects.ActualItemPriceTtl);

		pageMonthlyPlanningPO.CompareValueDecimalFields(extentTest, DBActlItmPriceVal, ActlItmPriceVal);
		pageMonthlyPlanningPO.CompareValueDecimalFields(extentTest, DBActlItmPriceTtl, ActlItmPriceTtl);
	}

	// Customers
	@Test(description = "Weekly Trading Statement - Zone, This Year Customer", priority = 3)
	public void CustomersColumnValidation() throws Exception {
		pageWeeklyPlanningPO = new WeeklyPlanningStorePage().createPage(getDriver(), getContext(), log, status, data,
				getReport(), getReportLogger(), WeeklyPlanningStorePage.class);
		pageDailyTradingStatementGroupPO = new MonthlyPlanningGroupPage().createPage(getDriver(), getContext(), log,
				status, data, getReport(), getReportLogger(), DailyTradingStatementGroupPage.class);
		TestStatus status = getTestStatus();
		System.out.println("Entering into TestCase - Customer View");

		// THIS YEAR - CUSTOMER:
		Report_AddStep("testcase", "CUSTOMER THIS YEAR VALUE - WEEK WISE DATA", "", "", "");
		extentTest = report.startTest("This Yr Customer");

		String ThisYrActlCustomers = data.getText("ThisYrActlCustomers");
		DBThisYrCustVal = sql.CLRexecuteQuery(getContext(),
				ThisYrActlCustomers.replace("Region", getContext().getStringProperty("Region"))
						.replace("Zone", getContext().getStringProperty("Zone"))
						.replace("LYWk", getContext().getStringProperty("LYWk"))
						.replace("Week", getContext().getStringProperty("SelectFiscalWeek"))
						.replace("Division", getContext().getStringProperty("Division")));
		System.out.println("DB Value: " + DBThisYrCustVal);

		String ThisYrActlCustomersTotal = data.getText("ThisYrActlCustomersTotal");
		DBThisYrCustTtl = sql.CLRexecuteQuery(getContext(),
				ThisYrActlCustomersTotal.replace("Region", getContext().getStringProperty("Region"))
						.replace("Zone", getContext().getStringProperty("Zone"))
						.replace("LYWk", getContext().getStringProperty("LYWk"))
						.replace("Week", getContext().getStringProperty("SelectFiscalWeek"))
						.replace("Division", getContext().getStringProperty("Division")));
		System.out.println("DB Value: " + DBThisYrCustTtl);

		ThisYrCustVal = pageDailyTradingStatementGroupPO.RowWiseResult(objWeeklyTradingStatementStoreObjects,
				objWeeklyTradingStatementZoneObjects.Departments, objWeeklyTradingStatementZoneObjects.ThisYrCustomer,
				objWeeklyTradingStatementZoneObjects.DataTable);
		ThisYrCustTtl = pageDailyTradingStatementGroupPO.WeekToDateUITotal(
				objWeeklyTradingStatementZoneObjects.TotalTitle,
				objWeeklyTradingStatementZoneObjects.ThisYrCustomerTtl);

		pageMonthlyPlanningPO.CompareValues(extentTest, DBThisYrCustVal, ThisYrCustVal);
		pageMonthlyPlanningPO.CompareValues(extentTest, DBThisYrCustTtl, ThisYrCustTtl);

		// % LAST YEAR CUSTOMER:
		Report_AddStep("testcase", "PERCENTAGE LAST YEAR CUSTOMER - WEEK WISE DATA", "", "", "");
		extentTest = report.startTest("LY Customers");

		String PctLastYrCustomers = data.getText("PctLastYrCustomers");
		DBLstYrCustVal = sql.CLRexecuteQuery(getContext(),
				PctLastYrCustomers.replace("Region", getContext().getStringProperty("Region"))
						.replace("Zone", getContext().getStringProperty("Zone"))
						.replace("LYWk", getContext().getStringProperty("LYWk"))
						.replace("Week", getContext().getStringProperty("SelectFiscalWeek"))
						.replace("Division", getContext().getStringProperty("Division")));
		System.out.println("DB Value: " + DBLstYrCustVal);

		String PctLastYrCustomersTotal = data.getText("PctLastYrCustomersTotal");
		DBLstYrCustTtl = sql.CLRexecuteQuery(getContext(),
				PctLastYrCustomersTotal.replace("Region", getContext().getStringProperty("Region"))
						.replace("Zone", getContext().getStringProperty("Zone"))
						.replace("LYWk", getContext().getStringProperty("LYWk"))
						.replace("Week", getContext().getStringProperty("SelectFiscalWeek"))
						.replace("Division", getContext().getStringProperty("Division")));
		System.out.println("DB Value: " + DBLstYrCustTtl);

		LstYrCustVal = pageDailyTradingStatementGroupPO.RowWiseResult(objWeeklyTradingStatementStoreObjects,
				objWeeklyTradingStatementZoneObjects.Departments,
				objWeeklyTradingStatementZoneObjects.PercentageLastCustomer,
				objWeeklyTradingStatementZoneObjects.DataTable);
		LstYrCustTtl = pageDailyTradingStatementGroupPO.WeekToDateUITotal(
				objWeeklyTradingStatementZoneObjects.TotalTitle,
				objWeeklyTradingStatementZoneObjects.PercentageLastCustomerTtl);

		pageMonthlyPlanningPO.CompareValueDecimalFields(extentTest, DBLstYrCustVal, LstYrCustVal);
		pageMonthlyPlanningPO.CompareValueDecimalFields(extentTest, DBLstYrCustTtl, LstYrCustTtl);

		// DATA PENETRATION CUSTOMER:
		Report_AddStep("testcase", "DATA PENETRATION CUSTOMER - WEEK WISE DATA", "", "", "");
		extentTest = report.startTest("Data Penetration Customers");

		String DataPenetrationCustomers = data.getText("DataPenetrationCustomers");
		DBDataPenVal = sql.CLRexecuteQuery(getContext(),
				DataPenetrationCustomers.replace("Region", getContext().getStringProperty("Region"))
						.replace("Zone", getContext().getStringProperty("Zone"))
						.replace("LYWk", getContext().getStringProperty("LYWk"))
						.replace("Week", getContext().getStringProperty("SelectFiscalWeek"))
						.replace("Division", getContext().getStringProperty("Division")));
		System.out.println("DB Value: " + DBDataPenVal);

		String DataPenetrationCustomersTotal = data.getText("DataPenetrationCustomersTotal");
		DBDataPenTtl = sql.CLRexecuteQuery(getContext(),
				DataPenetrationCustomersTotal.replace("Region", getContext().getStringProperty("Region"))
						.replace("Zone", getContext().getStringProperty("Zone"))
						.replace("LYWk", getContext().getStringProperty("LYWk"))
						.replace("Week", getContext().getStringProperty("SelectFiscalWeek"))
						.replace("Division", getContext().getStringProperty("Division")));
		System.out.println("DB Value: " + DBDataPenTtl);

		DataPenVal = pageDailyTradingStatementGroupPO.RowWiseResult(objWeeklyTradingStatementStoreObjects,
				objWeeklyTradingStatementZoneObjects.Departments,
				objWeeklyTradingStatementZoneObjects.DeptPenetrationCustomer,
				objWeeklyTradingStatementZoneObjects.DataTable);
		DataPenTtl = pageDailyTradingStatementGroupPO.WeekToDateUITotal(objWeeklyTradingStatementZoneObjects.TotalTitle,
				objWeeklyTradingStatementZoneObjects.DeptPenetrationCustomerTtl);

		pageMonthlyPlanningPO.CompareValueDecimalFields(extentTest, DBDataPenVal, DataPenVal);
		pageMonthlyPlanningPO.CompareValueDecimalFields(extentTest, DBDataPenTtl, DataPenTtl);
	}

	@Test(description = "Weekly Trading Statement - Zone, Growth in SMS Hours", priority = 4)
	public void HoursColumnValidation() throws Exception {
		pageWeeklyPlanningPO = new WeeklyPlanningStorePage().createPage(getDriver(), getContext(), log, status, data,
				getReport(), getReportLogger(), WeeklyPlanningStorePage.class);
		pageDailyTradingStatementGroupPO = new MonthlyPlanningGroupPage().createPage(getDriver(), getContext(), log,
				status, data, getReport(), getReportLogger(), DailyTradingStatementGroupPage.class);
		TestStatus status = getTestStatus();
		System.out.println("Entering into TestCase - Hours View");

		// ACTUAL HOURS:
		Report_AddStep("testcase", "ACTUAL HOURS - WEEK WISE DATA", "", "", "");
		extentTest = report.startTest("Actual Hours");

		String ActualHours = data.getText("ActualHours");
		DBActlHrsVal = sql.CLRexecuteQuery(getContext(),
				ActualHours.replace("Region", getContext().getStringProperty("Region"))
						.replace("Zone", getContext().getStringProperty("Zone"))
						.replace("LYWk", getContext().getStringProperty("LYWk"))
						.replace("Week", getContext().getStringProperty("SelectFiscalWeek"))
						.replace("Division", getContext().getStringProperty("Division")));
		System.out.println("DB Value: " + DBActlHrsVal);

		String ActualHoursTotal = data.getText("ActualHoursTotal");
		DBActlHrsTtl = sql.CLRexecuteQuery(getContext(),
				ActualHoursTotal.replace("Region", getContext().getStringProperty("Region"))
						.replace("Zone", getContext().getStringProperty("Zone"))
						.replace("LYWk", getContext().getStringProperty("LYWk"))
						.replace("Week", getContext().getStringProperty("SelectFiscalWeek"))
						.replace("Division", getContext().getStringProperty("Division")));
		System.out.println("DB Value: " + DBActlHrsTtl);

		ActlHrsVal = pageDailyTradingStatementGroupPO.RowWiseResult(objWeeklyTradingStatementStoreObjects,
				objWeeklyTradingStatementZoneObjects.Departments, objWeeklyTradingStatementZoneObjects.ActualHours,
				objWeeklyTradingStatementZoneObjects.DataTable);
		ActlHrsTtl = pageDailyTradingStatementGroupPO.WeekToDateUITotal(objWeeklyTradingStatementZoneObjects.TotalTitle,
				objWeeklyTradingStatementZoneObjects.ActualHoursTtl);

		pageMonthlyPlanningPO.CompareValueDecimalFields(extentTest, DBActlHrsVal, ActlHrsVal);
		pageMonthlyPlanningPO.CompareValueDecimalFields(extentTest, DBActlHrsTtl, ActlHrsTtl);

		// Variance SMS:
		Report_AddStep("testcase", "VARIANCE SMS - WEEK WISE DATA", "", "", "");
		extentTest = report.startTest("Variance Hours");

		String VarianceSMS = data.getText("VarianceSMS");
		DBVarSMSHrHr = sql.CLRexecuteQuery(getContext(),
				VarianceSMS.replace("store", getContext().getStringProperty("store"))
						.replace("FinWk", getContext().getStringProperty("FinWk"))
						.replace("Week", getContext().getStringProperty("SelectFiscalWeek"))
						.replace("Year", getContext().getStringProperty("Year"))
						.replace("Region", getContext().getStringProperty("Region"))
						.replace("Area", getContext().getStringProperty("Area"))
						.replace("Division", getContext().getStringProperty("Division"))
						.replace("Zone", getContext().getStringProperty("Zone")));
		System.out.println("DB Value: " + DBVarSMSHrHr);

		String VarianceSMSTotal = data.getText("VarianceSMSTotal");
		DBVarSMSHrTtl = sql.CLRexecuteQuery(getContext(),
				VarianceSMSTotal.replace("store", getContext().getStringProperty("store"))
						.replace("FinWk", getContext().getStringProperty("FinWk"))
						.replace("Week", getContext().getStringProperty("SelectFiscalWeek"))
						.replace("Year", getContext().getStringProperty("Year"))
						.replace("Region", getContext().getStringProperty("Region"))
						.replace("Area", getContext().getStringProperty("Area"))
						.replace("Division", getContext().getStringProperty("Division"))
						.replace("Zone", getContext().getStringProperty("Zone")));
		System.out.println("DB Value: " + DBVarSMSHrTtl);

		VarSMSHr = pageDailyTradingStatementGroupPO.RowWiseResult(objWeeklyTradingStatementStoreObjects,
				objWeeklyTradingStatementZoneObjects.Departments, objWeeklyTradingStatementZoneObjects.VarSMSHours,
				objWeeklyTradingStatementZoneObjects.DataTable);
		VarSMSHrTtl = pageDailyTradingStatementGroupPO.WeekToDateUITotal(
				objWeeklyTradingStatementZoneObjects.TotalTitle, objWeeklyTradingStatementZoneObjects.VarSMSHoursTtl);

		pageMonthlyPlanningPO.CompareValueDecimalFields(extentTest, DBVarSMSHrHr, VarSMSHr);
		pageMonthlyPlanningPO.CompareValueDecimalFields(extentTest, DBVarSMSHrTtl, VarSMSHrTtl);

		// PLANNED HOURS:
		Report_AddStep("testcase", "PLANNED HOURS: - WEEK WISE DATA", "", "", "");
		extentTest = report.startTest("Planned Hours");

		String PlannedHours = data.getText("PlannedHours");
		DBPlndHrsVal = sql.CLRexecuteQuery(getContext(),
				PlannedHours.replace("Region", getContext().getStringProperty("Region"))
						.replace("Zone", getContext().getStringProperty("Zone"))
						.replace("LYWk", getContext().getStringProperty("LYWk"))
						.replace("Week", getContext().getStringProperty("SelectFiscalWeek"))
						.replace("Division", getContext().getStringProperty("Division")));
		System.out.println("DB Value: " + DBPlndHrsVal);

		String PlannedHoursTotal = data.getText("PlannedHoursTotal");
		DBPlndHrsTtl = sql.CLRexecuteQuery(getContext(),
				PlannedHoursTotal.replace("Region", getContext().getStringProperty("Region"))
						.replace("Zone", getContext().getStringProperty("Zone"))
						.replace("LYWk", getContext().getStringProperty("LYWk"))
						.replace("Week", getContext().getStringProperty("SelectFiscalWeek"))
						.replace("Division", getContext().getStringProperty("Division")));
		System.out.println("DB Value: " + DBPlndHrsTtl);

		PlndHrsVal = pageDailyTradingStatementGroupPO.RowWiseResult(objWeeklyTradingStatementStoreObjects,
				objWeeklyTradingStatementZoneObjects.Departments, objWeeklyTradingStatementZoneObjects.PlannedHours,
				objWeeklyTradingStatementZoneObjects.DataTable);
		PlndHrsTtl = pageDailyTradingStatementGroupPO.WeekToDateUITotal(objWeeklyTradingStatementZoneObjects.TotalTitle,
				objWeeklyTradingStatementZoneObjects.PlannedHoursTtl);

		pageMonthlyPlanningPO.CompareValueDecimalFields(extentTest, DBPlndHrsVal, PlndHrsVal);
		pageMonthlyPlanningPO.CompareValueDecimalFields(extentTest, DBPlndHrsTtl, PlndHrsTtl);

		// VARIANCE IN PLANNED HOURS:
		Report_AddStep("testcase", "VARIANCE IN PLANNED HOURS - WEEK WISE DATA", "", "", "");
		extentTest = report.startTest("Variance Planned Hours");

		String VarHours = data.getText("VarHours");
		DBVarPlndHrsVal = sql.CLRexecuteQuery(getContext(),
				VarHours.replace("Region", getContext().getStringProperty("Region"))
						.replace("Zone", getContext().getStringProperty("Zone"))
						.replace("LYWk", getContext().getStringProperty("LYWk"))
						.replace("Week", getContext().getStringProperty("SelectFiscalWeek"))
						.replace("Division", getContext().getStringProperty("Division")));
		System.out.println("DB Value: " + DBVarPlndHrsVal);

		String VarHoursTotal = data.getText("VarHoursTotal");
		DBVarPlndHrsTtl = sql.CLRexecuteQuery(getContext(),
				VarHoursTotal.replace("Region", getContext().getStringProperty("Region"))
						.replace("Zone", getContext().getStringProperty("Zone"))
						.replace("LYWk", getContext().getStringProperty("LYWk"))
						.replace("Week", getContext().getStringProperty("SelectFiscalWeek"))
						.replace("Division", getContext().getStringProperty("Division")));
		System.out.println("DB Value: " + DBVarPlndHrsTtl);

		VarPlndHrsVal = pageDailyTradingStatementGroupPO.RowWiseResult(objWeeklyTradingStatementStoreObjects,
				objWeeklyTradingStatementZoneObjects.Departments, objWeeklyTradingStatementZoneObjects.VarPlannedHours,
				objWeeklyTradingStatementZoneObjects.DataTable);
		VarPlndHrsTtl = pageDailyTradingStatementGroupPO.WeekToDateUITotal(
				objWeeklyTradingStatementZoneObjects.TotalTitle,
				objWeeklyTradingStatementZoneObjects.VarPlannedHoursTtl);

		pageMonthlyPlanningPO.CompareValueDecimalFields(extentTest, DBVarPlndHrsVal, VarPlndHrsVal);
		pageMonthlyPlanningPO.CompareValueDecimalFields(extentTest, DBVarPlndHrsTtl, VarPlndHrsTtl);

		// Actual SMS:
		Report_AddStep("testcase", "ACTUAL SMS - WEEK WISE DATA", "", "", "");
		extentTest = report.startTest("Actual SMS");

		String ActualSMS = data.getText("ActualSMS");
		DBActlSMSHr = sql.CLRexecuteQuery(getContext(),
				ActualSMS.replace("store", getContext().getStringProperty("store"))
						.replace("FinWk", getContext().getStringProperty("FinWk"))
						.replace("Week", getContext().getStringProperty("SelectFiscalWeek"))
						.replace("Year", getContext().getStringProperty("Year"))
						.replace("Region", getContext().getStringProperty("Region"))
						.replace("Area", getContext().getStringProperty("Area"))
						.replace("Division", getContext().getStringProperty("Division"))
						.replace("Zone", getContext().getStringProperty("Zone")));
		System.out.println("DB Value: " + DBActlSMSHr);

		String ActualSMSTotal = data.getText("ActualSMSTotal");
		DBActlSMSHrTtl = sql.CLRexecuteQuery(getContext(),
				ActualSMSTotal.replace("store", getContext().getStringProperty("store"))
						.replace("FinWk", getContext().getStringProperty("FinWk"))
						.replace("Week", getContext().getStringProperty("SelectFiscalWeek"))
						.replace("Year", getContext().getStringProperty("Year"))
						.replace("Region", getContext().getStringProperty("Region"))
						.replace("Area", getContext().getStringProperty("Area"))
						.replace("Division", getContext().getStringProperty("Division"))
						.replace("Zone", getContext().getStringProperty("Zone")));
		System.out.println("DB Value: " + DBActlSMSHrTtl);

		ActlSMSHr = pageDailyTradingStatementGroupPO.RowWiseResult(objWeeklyTradingStatementStoreObjects,
				objWeeklyTradingStatementZoneObjects.Departments, objWeeklyTradingStatementZoneObjects.ActualSMSHours,
				objWeeklyTradingStatementZoneObjects.DataTable);
		ActlSMSHrTtl = pageDailyTradingStatementGroupPO.WeekToDateUITotal(
				objWeeklyTradingStatementZoneObjects.TotalTitle,
				objWeeklyTradingStatementZoneObjects.ActualSMSHoursTtl);

		pageMonthlyPlanningPO.CompareValueDecimalFields(extentTest, DBActlSMSHr, ActlSMSHr);
		pageMonthlyPlanningPO.CompareValueDecimalFields(extentTest, DBActlSMSHrTtl, ActlSMSHrTtl);

		// OR % SMS:
		Report_AddStep("testcase", "OPERATIONAL RATIO - WEEK WISE DATA", "", "", "");
		extentTest = report.startTest("OR - SMS");

		String ORPercentage = data.getText("ORPercentage");
		DBORHr = sql.CLRexecuteQuery(getContext(),
				ORPercentage.replace("store", getContext().getStringProperty("store"))
						.replace("FinWk", getContext().getStringProperty("FinWk"))
						.replace("Week", getContext().getStringProperty("SelectFiscalWeek"))
						.replace("Year", getContext().getStringProperty("Year"))
						.replace("Region", getContext().getStringProperty("Region"))
						.replace("Area", getContext().getStringProperty("Area"))
						.replace("Division", getContext().getStringProperty("Division"))
						.replace("Zone", getContext().getStringProperty("Zone")));
		System.out.println("DB Value: " + DBORHr);

		String ORPercentageTotal = data.getText("ORPercentageTotal");
		DBORHrTtl = sql.CLRexecuteQuery(getContext(),
				ORPercentageTotal.replace("store", getContext().getStringProperty("store"))
						.replace("FinWk", getContext().getStringProperty("FinWk"))
						.replace("Week", getContext().getStringProperty("SelectFiscalWeek"))
						.replace("Year", getContext().getStringProperty("Year"))
						.replace("Region", getContext().getStringProperty("Region"))
						.replace("Area", getContext().getStringProperty("Area"))
						.replace("Division", getContext().getStringProperty("Division"))
						.replace("Zone", getContext().getStringProperty("Zone")));
		System.out.println("DB Value: " + DBORHrTtl);

		ORHr = pageDailyTradingStatementGroupPO.RowWiseResult(objWeeklyTradingStatementStoreObjects,
				objWeeklyTradingStatementZoneObjects.Departments,
				objWeeklyTradingStatementZoneObjects.ORPercentageHours, objWeeklyTradingStatementZoneObjects.DataTable);
		ORHrTtl = pageDailyTradingStatementGroupPO.WeekToDateUITotal(objWeeklyTradingStatementZoneObjects.TotalTitle,
				objWeeklyTradingStatementZoneObjects.ORPercentageHoursTtl);

		pageMonthlyPlanningPO.CompareValueDecimalFields(extentTest, DBORHr, ORHr);
		pageMonthlyPlanningPO.CompareValueDecimalFields(extentTest, DBORHrTtl, ORHrTtl);
	}

	// Wages
	@Test(description = "Weekly Trading Statement - Zone, Actual Wages", priority = 5)
	public void WagesColumnValidation() throws Exception {
		pageWeeklyPlanningPO = new WeeklyPlanningStorePage().createPage(getDriver(), getContext(), log, status, data,
				getReport(), getReportLogger(), WeeklyPlanningStorePage.class);
		pageDailyTradingStatementGroupPO = new MonthlyPlanningGroupPage().createPage(getDriver(), getContext(), log,
				status, data, getReport(), getReportLogger(), DailyTradingStatementGroupPage.class);
		TestStatus status = getTestStatus();
		System.out.println("Entering into TestCase - Wages View");
		OptionToView = getContext().getStringProperty("OptionToView");

		// ACTUAL WAGE:
		Report_AddStep("testcase", "ACTUAL WAGE- WEEK WISE DATA", "", "", "");
		extentTest = report.startTest("Actual Wages");

		String ActualWages = data.getText("ActualWages");
		DBActlWgVal = sql.CLRexecuteQuery(getContext(),
				ActualWages.replace("Region", getContext().getStringProperty("Region"))
						.replace("Zone", getContext().getStringProperty("Zone"))
						.replace("LYWk", getContext().getStringProperty("LYWk"))
						.replace("Week", getContext().getStringProperty("SelectFiscalWeek"))
						.replace("Division", getContext().getStringProperty("Division")));
		System.out.println("DB Value: " + DBActlWgVal);

		String ActualWagesTotal = data.getText("ActualWagesTotal");
		DBActlWgTtl = sql.CLRexecuteQuery(getContext(),
				ActualWagesTotal.replace("Region", getContext().getStringProperty("Region"))
						.replace("Zone", getContext().getStringProperty("Zone"))
						.replace("LYWk", getContext().getStringProperty("LYWk"))
						.replace("Week", getContext().getStringProperty("SelectFiscalWeek"))
						.replace("Division", getContext().getStringProperty("Division")));
		System.out.println("DB Value: " + DBActlWgTtl);

		ActlWgVal = pageDailyTradingStatementGroupPO.RowWiseResult(objWeeklyTradingStatementStoreObjects,
				objWeeklyTradingStatementZoneObjects.Departments, objWeeklyTradingStatementZoneObjects.ActualWageswts,
				objWeeklyTradingStatementZoneObjects.DataTable);
		ActlWgTtl = pageDailyTradingStatementGroupPO.WeekToDateUITotal(objWeeklyTradingStatementZoneObjects.TotalTitle,
				objWeeklyTradingStatementZoneObjects.ActualWagesTtlwts);

		pageMonthlyPlanningPO.CompareValues(extentTest, DBActlWgVal, ActlWgVal);
		pageMonthlyPlanningPO.CompareValues(extentTest, DBActlWgTtl, ActlWgTtl);

		// ACTUAL WAGE PERCENTAGE:
		Report_AddStep("testcase", "ACTUAL WAGE PERCENTAGE- WEEK WISE DATA", "", "", "");
		extentTest = report.startTest("Actual Wages Prcnt");

		String ActualWagePercentage = data.getText("ActualWagePercentage");
		DBActlWgPrcntVal = sql.CLRexecuteQuery(getContext(),
				ActualWagePercentage.replace("Region", getContext().getStringProperty("Region"))
						.replace("Zone", getContext().getStringProperty("Zone"))
						.replace("LYWk", getContext().getStringProperty("LYWk"))
						.replace("Week", getContext().getStringProperty("SelectFiscalWeek"))
						.replace("Division", getContext().getStringProperty("Division")));
		System.out.println("DB Value: " + DBActlWgPrcntVal);

		String ActualWagePercentageTotal = data.getText("ActualWagePercentageTotal");
		DBActlWgPrcntTtl = sql.CLRexecuteQuery(getContext(),
				ActualWagePercentageTotal.replace("Region", getContext().getStringProperty("Region"))
						.replace("Zone", getContext().getStringProperty("Zone"))
						.replace("LYWk", getContext().getStringProperty("LYWk"))
						.replace("Week", getContext().getStringProperty("SelectFiscalWeek"))
						.replace("Division", getContext().getStringProperty("Division")));
		System.out.println("DB Value: " + DBActlWgPrcntTtl);

		ActlWgPrcntVal = pageDailyTradingStatementGroupPO.RowWiseResult(objWeeklyTradingStatementStoreObjects,
				objWeeklyTradingStatementZoneObjects.Departments,
				objWeeklyTradingStatementZoneObjects.ActualWagesPercentagewts,
				objWeeklyTradingStatementZoneObjects.DataTable);
		ActlWgPrcntTtl = pageDailyTradingStatementGroupPO.WeekToDateUITotal(
				objWeeklyTradingStatementZoneObjects.TotalTitle,
				objWeeklyTradingStatementZoneObjects.ActualWagesPercentageTtlwts);

		pageMonthlyPlanningPO.CompareValueDecimalFields(extentTest, DBActlWgPrcntVal, ActlWgPrcntVal);
		pageMonthlyPlanningPO.CompareValueDecimalFields(extentTest, DBActlWgPrcntTtl, ActlWgPrcntTtl);

		// BUDGET WAGE:
		if (OptionToView.contains("Budget")) {
			Report_AddStep("testcase", "ACTUAL BUDGET WAGE - WEEK WISE DATA", "", "", "");
			extentTest = report.startTest("Budget Wages");

			String BudgetWages = data.getText("BudgetWages");
			DBBdgtWgVal = sql.CLRexecuteQuery(getContext(),
					BudgetWages.replace("Region", getContext().getStringProperty("Region"))
							.replace("Zone", getContext().getStringProperty("Zone"))
							.replace("LYWk", getContext().getStringProperty("LYWk"))
							.replace("Week", getContext().getStringProperty("SelectFiscalWeek"))
							.replace("Division", getContext().getStringProperty("Division")));
			System.out.println("DB Value: " + DBBdgtWgVal);

			String BudgetWagesTotal = data.getText("BudgetWagesTotal");
			DBBdgtWgTtl = sql.CLRexecuteQuery(getContext(),
					BudgetWagesTotal.replace("Region", getContext().getStringProperty("Region"))
							.replace("Zone", getContext().getStringProperty("Zone"))
							.replace("LYWk", getContext().getStringProperty("LYWk"))
							.replace("Week", getContext().getStringProperty("SelectFiscalWeek"))
							.replace("Division", getContext().getStringProperty("Division")));
			System.out.println("DB Value: " + DBBdgtWgTtl);

			BdgtWgVal = pageDailyTradingStatementGroupPO.RowWiseResult(objWeeklyTradingStatementStoreObjects,
					objWeeklyTradingStatementZoneObjects.Departments,
					objWeeklyTradingStatementZoneObjects.BudgetWageswts,
					objWeeklyTradingStatementZoneObjects.DataTable);
			BdgtWgTtl = pageDailyTradingStatementGroupPO.WeekToDateUITotal(
					objWeeklyTradingStatementZoneObjects.TotalTitle,
					objWeeklyTradingStatementZoneObjects.BudgetWagesTtlwts);

			pageMonthlyPlanningPO.CompareValues(extentTest, DBBdgtWgVal, BdgtWgVal);
			pageMonthlyPlanningPO.CompareValues(extentTest, DBBdgtWgTtl, BdgtWgTtl);
		}
		if (OptionToView.contains("Forecast")) {
			Report_AddStep("testcase", "ACTUAL FORECAST WAGE - WEEK WISE DATA", "", "", "");
			extentTest = report.startTest("Forecast Wages");

			String ForecastWages = data.getText("ForecastWages");
			DBBdgtWgVal = sql.CLRexecuteQuery(getContext(),
					ForecastWages.replace("Region", getContext().getStringProperty("Region"))
							.replace("Zone", getContext().getStringProperty("Zone"))
							.replace("LYWk", getContext().getStringProperty("LYWk"))
							.replace("Week", getContext().getStringProperty("SelectFiscalWeek"))
							.replace("Division", getContext().getStringProperty("Division")));
			System.out.println("DB Value: " + DBBdgtWgVal);

			String ForecastWagesTotal = data.getText("ForecastWagesTotal");
			DBBdgtWgTtl = sql.CLRexecuteQuery(getContext(),
					ForecastWagesTotal.replace("Region", getContext().getStringProperty("Region"))
							.replace("Zone", getContext().getStringProperty("Zone"))
							.replace("LYWk", getContext().getStringProperty("LYWk"))
							.replace("Week", getContext().getStringProperty("SelectFiscalWeek"))
							.replace("Division", getContext().getStringProperty("Division")));
			System.out.println("DB Value: " + DBBdgtWgTtl);

			BdgtWgVal = pageDailyTradingStatementGroupPO.RowWiseResult(objWeeklyTradingStatementStoreObjects,
					objWeeklyTradingStatementZoneObjects.Departments,
					objWeeklyTradingStatementZoneObjects.BudgetWageswts,
					objWeeklyTradingStatementZoneObjects.DataTable);
			BdgtWgTtl = pageDailyTradingStatementGroupPO.WeekToDateUITotal(
					objWeeklyTradingStatementZoneObjects.TotalTitle,
					objWeeklyTradingStatementZoneObjects.BudgetWagesTtlwts);

			pageMonthlyPlanningPO.CompareValues(extentTest, DBBdgtWgVal, BdgtWgVal);
			pageMonthlyPlanningPO.CompareValues(extentTest, DBBdgtWgTtl, BdgtWgTtl);
		}

		// BUDGET WAGE PERCENTAGE:
		if (OptionToView.contains("Budget")) {
			Report_AddStep("testcase", "BUDGET WAGE PERCENTAGE- WEEK WISE DATA", "", "", "");
			extentTest = report.startTest("Budget Wages Prcnt");

			String BudgetWagePercentage = data.getText("BudgetWagePercentage");
			DBBdgtWgPrcntVal = sql.CLRexecuteQuery(getContext(),
					BudgetWagePercentage.replace("Region", getContext().getStringProperty("Region"))
							.replace("Zone", getContext().getStringProperty("Zone"))
							.replace("LYWk", getContext().getStringProperty("LYWk"))
							.replace("Week", getContext().getStringProperty("SelectFiscalWeek"))
							.replace("Division", getContext().getStringProperty("Division")));
			System.out.println("DB Value: " + DBBdgtWgPrcntVal);

			String BudgetWagePercentageTotal = data.getText("BudgetWagePercentageTotal");
			DBBdgtWgPrcntTtl = sql.CLRexecuteQuery(getContext(),
					BudgetWagePercentageTotal.replace("Region", getContext().getStringProperty("Region"))
							.replace("Zone", getContext().getStringProperty("Zone"))
							.replace("LYWk", getContext().getStringProperty("LYWk"))
							.replace("Week", getContext().getStringProperty("SelectFiscalWeek"))
							.replace("Division", getContext().getStringProperty("Division")));
			System.out.println("DB Value: " + DBBdgtWgPrcntTtl);

			BdgtWgPrcntVal = pageDailyTradingStatementGroupPO.RowWiseResult(objWeeklyTradingStatementStoreObjects,
					objWeeklyTradingStatementZoneObjects.Departments,
					objWeeklyTradingStatementZoneObjects.BudgetWagesPercentagewts,
					objWeeklyTradingStatementZoneObjects.DataTable);
			BdgtWgPrcntTtl = pageDailyTradingStatementGroupPO.WeekToDateUITotal(
					objWeeklyTradingStatementZoneObjects.TotalTitle,
					objWeeklyTradingStatementZoneObjects.BudgetWagesPercentageTtlwts);

			pageMonthlyPlanningPO.CompareValueDecimalFields(extentTest, DBBdgtWgPrcntVal, BdgtWgPrcntVal);
			pageMonthlyPlanningPO.CompareValueDecimalFields(extentTest, DBBdgtWgPrcntTtl, BdgtWgPrcntTtl);
		}
		if (OptionToView.contains("Forecast")) {
			Report_AddStep("testcase", "FORECAST WAGE PERCENTAGE- WEEK WISE DATA", "", "", "");
			extentTest = report.startTest("Forecast Wages Prcnt");

			String ForecastWagePercentage = data.getText("ForecastWagePercentage");
			DBBdgtWgPrcntVal = sql.CLRexecuteQuery(getContext(),
					ForecastWagePercentage.replace("Region", getContext().getStringProperty("Region"))
							.replace("Zone", getContext().getStringProperty("Zone"))
							.replace("LYWk", getContext().getStringProperty("LYWk"))
							.replace("Week", getContext().getStringProperty("SelectFiscalWeek"))
							.replace("Division", getContext().getStringProperty("Division")));
			System.out.println("DB Value: " + DBBdgtWgPrcntVal);

			String ForecastWagePercentageTotal = data.getText("ForecastWagePercentageTotal");
			DBBdgtWgPrcntTtl = sql.CLRexecuteQuery(getContext(),
					ForecastWagePercentageTotal.replace("Region", getContext().getStringProperty("Region"))
							.replace("Zone", getContext().getStringProperty("Zone"))
							.replace("LYWk", getContext().getStringProperty("LYWk"))
							.replace("Week", getContext().getStringProperty("SelectFiscalWeek"))
							.replace("Division", getContext().getStringProperty("Division")));
			System.out.println("DB Value: " + DBBdgtWgPrcntTtl);

			BdgtWgPrcntVal = pageDailyTradingStatementGroupPO.RowWiseResult(objWeeklyTradingStatementStoreObjects,
					objWeeklyTradingStatementZoneObjects.Departments,
					objWeeklyTradingStatementZoneObjects.BudgetWagesPercentagewts,
					objWeeklyTradingStatementZoneObjects.DataTable);
			BdgtWgPrcntTtl = pageDailyTradingStatementGroupPO.WeekToDateUITotal(
					objWeeklyTradingStatementZoneObjects.TotalTitle,
					objWeeklyTradingStatementZoneObjects.BudgetWagesPercentageTtlwts);

			pageMonthlyPlanningPO.CompareValueDecimalFields(extentTest, DBBdgtWgPrcntVal, BdgtWgPrcntVal);
			pageMonthlyPlanningPO.CompareValueDecimalFields(extentTest, DBBdgtWgPrcntTtl, BdgtWgPrcntTtl);
		}

		// PLANNED CPH:
		Report_AddStep("testcase", "PLANNED CPH - WEEK WISE DATA", "", "", "");
		extentTest = report.startTest("Planned CPH");

		String PlannedCPH = data.getText("PlannedCPH");
		DBPlndCPHVal = sql.CLRexecuteQuery(getContext(),
				PlannedCPH.replace("Region", getContext().getStringProperty("Region"))
						.replace("Zone", getContext().getStringProperty("Zone"))
						.replace("LYWk", getContext().getStringProperty("LYWk"))
						.replace("Week", getContext().getStringProperty("SelectFiscalWeek"))
						.replace("Division", getContext().getStringProperty("Division")));
		System.out.println("DB Value: " + DBPlndCPHVal);

		String PlannedCPHTotal = data.getText("PlannedCPHTotal");
		DBPlndCPHTtl = sql.CLRexecuteQuery(getContext(),
				PlannedCPHTotal.replace("Region", getContext().getStringProperty("Region"))
						.replace("Zone", getContext().getStringProperty("Zone"))
						.replace("LYWk", getContext().getStringProperty("LYWk"))
						.replace("Week", getContext().getStringProperty("SelectFiscalWeek"))
						.replace("Division", getContext().getStringProperty("Division")));
		System.out.println("DB Value: " + DBPlndCPHTtl);

		PlndCPHVal = pageDailyTradingStatementGroupPO.RowWiseResult(objWeeklyTradingStatementStoreObjects,
				objWeeklyTradingStatementZoneObjects.Departments,
				objWeeklyTradingStatementZoneObjects.PlannedCPHWageswts,
				objWeeklyTradingStatementZoneObjects.DataTable);
		PlndCPHTtl = pageDailyTradingStatementGroupPO.WeekToDateUITotal(objWeeklyTradingStatementZoneObjects.TotalTitle,
				objWeeklyTradingStatementZoneObjects.PlannedCPHWagesTtlwts);

		pageMonthlyPlanningPO.CompareValueDecimalFields(extentTest, DBPlndCPHVal, PlndCPHVal);
		pageMonthlyPlanningPO.CompareValueDecimalFields(extentTest, DBPlndCPHTtl, PlndCPHTtl);

		// PLANNED WAGE:
		Report_AddStep("testcase", "PLANNED WAGE - WEEK WISE DATA", "", "", "");
		extentTest = report.startTest("Planned Wages");

		String PlannedWages = data.getText("PlannedWages");
		DBPlndWgVal = sql.CLRexecuteQuery(getContext(),
				PlannedWages.replace("Region", getContext().getStringProperty("Region"))
						.replace("Zone", getContext().getStringProperty("Zone"))
						.replace("LYWk", getContext().getStringProperty("LYWk"))
						.replace("Week", getContext().getStringProperty("SelectFiscalWeek"))
						.replace("Division", getContext().getStringProperty("Division")));
		System.out.println("DB Value: " + DBPlndWgVal);

		String PlannedWagesTotal = data.getText("PlannedWagesTotal");
		DBPlndWgTtl = sql.CLRexecuteQuery(getContext(),
				PlannedWagesTotal.replace("Region", getContext().getStringProperty("Region"))
						.replace("Zone", getContext().getStringProperty("Zone"))
						.replace("LYWk", getContext().getStringProperty("LYWk"))
						.replace("Week", getContext().getStringProperty("SelectFiscalWeek"))
						.replace("Division", getContext().getStringProperty("Division")));
		System.out.println("DB Value: " + DBPlndWgTtl);

		PlndWgVal = pageDailyTradingStatementGroupPO.RowWiseResult(objWeeklyTradingStatementStoreObjects,
				objWeeklyTradingStatementZoneObjects.Departments, objWeeklyTradingStatementZoneObjects.PlannedWageswts,
				objWeeklyTradingStatementZoneObjects.DataTable);
		PlndWgTtl = pageDailyTradingStatementGroupPO.WeekToDateUITotal(objWeeklyTradingStatementZoneObjects.TotalTitle,
				objWeeklyTradingStatementZoneObjects.PlannedWagesTtlwts);

		pageMonthlyPlanningPO.CompareValues(extentTest, DBPlndWgVal, PlndWgVal);
		pageMonthlyPlanningPO.CompareValues(extentTest, DBPlndWgTtl, PlndWgTtl);

		// PLANNED WAGE PERCENTAGE:
		Report_AddStep("testcase", "PLANNED WAGE PERCENTAGE - WEEK WISE DATA", "", "", "");
		extentTest = report.startTest("Planned Wages Prcnt");

		String PlannedWagesPercentage = data.getText("PlannedWagesPercentage");
		DBPlndWgPrcntVal = sql.CLRexecuteQuery(getContext(),
				PlannedWagesPercentage.replace("Region", getContext().getStringProperty("Region"))
						.replace("Zone", getContext().getStringProperty("Zone"))
						.replace("LYWk", getContext().getStringProperty("LYWk"))
						.replace("Week", getContext().getStringProperty("SelectFiscalWeek"))
						.replace("Division", getContext().getStringProperty("Division")));
		System.out.println("DB Value: " + DBPlndWgPrcntVal);

		String PlannedWagesPercentageTotal = data.getText("PlannedWagesPercentageTotal");
		DBPlndWgPrcntTtl = sql.CLRexecuteQuery(getContext(),
				PlannedWagesPercentageTotal.replace("Region", getContext().getStringProperty("Region"))
						.replace("Zone", getContext().getStringProperty("Zone"))
						.replace("LYWk", getContext().getStringProperty("LYWk"))
						.replace("Week", getContext().getStringProperty("SelectFiscalWeek"))
						.replace("Division", getContext().getStringProperty("Division")));
		System.out.println("DB Value: " + DBPlndWgPrcntTtl);

		PlndWgPrcntVal = pageDailyTradingStatementGroupPO.RowWiseResult(objWeeklyTradingStatementStoreObjects,
				objWeeklyTradingStatementZoneObjects.Departments,
				objWeeklyTradingStatementZoneObjects.PlannedWagesPercentagewts,
				objWeeklyTradingStatementZoneObjects.DataTable);
		PlndWgPrcntTtl = pageDailyTradingStatementGroupPO.WeekToDateUITotal(
				objWeeklyTradingStatementZoneObjects.TotalTitle,
				objWeeklyTradingStatementZoneObjects.PlannedWagesPercentageTtlwts);

		pageMonthlyPlanningPO.CompareValueDecimalFields(extentTest, DBPlndWgPrcntVal, PlndWgPrcntVal);
		pageMonthlyPlanningPO.CompareValueDecimalFields(extentTest, DBPlndWgPrcntTtl, PlndWgPrcntTtl);

		// CPH Wages:
		Report_AddStep("testcase", "CPH WAGES - WEEK WISE DATA", "", "", "");
		extentTest = report.startTest("CPH Wages");

		String ActualCPH = data.getText("ActualCPH");
		DBCPHWgVal = sql.CLRexecuteQuery(getContext(),
				ActualCPH.replace("Region", getContext().getStringProperty("Region"))
						.replace("Zone", getContext().getStringProperty("Zone"))
						.replace("LYWk", getContext().getStringProperty("LYWk"))
						.replace("Week", getContext().getStringProperty("SelectFiscalWeek"))
						.replace("Division", getContext().getStringProperty("Division")));
		System.out.println("DB Value: " + DBCPHWgVal);

		String ActualCPHTotal = data.getText("ActualCPHTotal");
		DBCPHWgTtl = sql.CLRexecuteQuery(getContext(),
				ActualCPHTotal.replace("Region", getContext().getStringProperty("Region"))
						.replace("Zone", getContext().getStringProperty("Zone"))
						.replace("LYWk", getContext().getStringProperty("LYWk"))
						.replace("Week", getContext().getStringProperty("SelectFiscalWeek"))
						.replace("Division", getContext().getStringProperty("Division")));
		System.out.println("DB Value: " + DBCPHWgTtl);

		CPHWgVal = pageDailyTradingStatementGroupPO.RowWiseResult(objWeeklyTradingStatementStoreObjects,
				objWeeklyTradingStatementZoneObjects.Departments, objWeeklyTradingStatementZoneObjects.CPHWageswts,
				objWeeklyTradingStatementZoneObjects.DataTable);
		CPHWgTtl = pageDailyTradingStatementGroupPO.WeekToDateUITotal(objWeeklyTradingStatementZoneObjects.TotalTitle,
				objWeeklyTradingStatementZoneObjects.CPHWagesTtlwts);

		pageMonthlyPlanningPO.CompareValueDecimalFields(extentTest, DBCPHWgVal, CPHWgVal);
		pageMonthlyPlanningPO.CompareValueDecimalFields(extentTest, DBCPHWgTtl, CPHWgTtl);

		/*
		 * //VARIANCE BUDGET WAGES: if(OptionToView.contains("Budget")) {
		 * Report_AddStep("testcase","VARIANCE BUDGET WAGES - WEEK WISE DATA" ,"","",
		 * ""); extentTest.log(LogStatus.INFO,
		 * "VARIANCE BUDGET WAGES - WEEK WISE DATA");
		 * 
		 * String VarWages = data.getText("VarWages"); DBVarBdgtWgVal =
		 * sql.CLRexecuteQuery(getContext(),
		 * VarWages.replace("Region",getContext().getStringProperty("Region"))
		 * .replace("Zone",getContext().getStringProperty("Zone")).replace("LYWk",
		 * getContext().getStringProperty("LYWk"))
		 * .replace("Week",getContext().getStringProperty("SelectFiscalWeek")).replace(
		 * "Division",getContext().getStringProperty("Division")));
		 * System.out.println("DB Value: "+DBVarBdgtWgVal);
		 * 
		 * String VarWagesTotal = data.getText("VarWagesTotal"); DBVarBdgtWgTtl =
		 * sql.CLRexecuteQuery(getContext(),
		 * VarWagesTotal.replace("Region",getContext().getStringProperty("Region"))
		 * .replace("Zone",getContext().getStringProperty("Zone")).replace("LYWk",
		 * getContext().getStringProperty("LYWk"))
		 * .replace("Week",getContext().getStringProperty("SelectFiscalWeek")).replace(
		 * "Division",getContext().getStringProperty("Division")));
		 * System.out.println("DB Value: "+DBVarBdgtWgTtl);
		 * 
		 * VarBdgtWgVal = pageDailyTradingStatementGroupPO.RowWiseResult(
		 * objWeeklyTradingStatementStoreObjects,objWeeklyTradingStatementZoneObjects.
		 * Departments,objWeeklyTradingStatementZoneObjects.VarBudgetWages,
		 * objWeeklyTradingStatementZoneObjects.DataTable); VarBdgtWgTtl =
		 * pageDailyTradingStatementGroupPO.WeekToDateUITotal(
		 * objWeeklyTradingStatementZoneObjects.TotalTitle,
		 * objWeeklyTradingStatementZoneObjects.VarBudgetWagesTtl);
		 * 
		 * pageMonthlyPlanningPO.CompareValueDecimalFields(extentTest,DBVarBdgtWgVal,
		 * VarBdgtWgVal);
		 * pageMonthlyPlanningPO.CompareValueDecimalFields(extentTest,DBVarBdgtWgTtl,
		 * VarBdgtWgTtl); } if(OptionToView.contains("Forecast")) {
		 * Report_AddStep("testcase","VARIANCE FORECAST WAGES - WEEK WISE DATA" ,"","",
		 * ""); extentTest.log(LogStatus.INFO,
		 * "VARIANCE FORECAST WAGES - WEEK WISE DATA");
		 * 
		 * String VarFcstWages = data.getText("VarFcstWages"); DBVarBdgtWgVal =
		 * sql.CLRexecuteQuery(getContext(),
		 * VarFcstWages.replace("Region",getContext().getStringProperty("Region"))
		 * .replace("Zone",getContext().getStringProperty("Zone")).replace("LYWk",
		 * getContext().getStringProperty("LYWk"))
		 * .replace("Week",getContext().getStringProperty("SelectFiscalWeek")).replace(
		 * "Division",getContext().getStringProperty("Division")));
		 * System.out.println("DB Value: "+DBVarBdgtWgVal);
		 * 
		 * String VarFcstWagesTotal = data.getText("VarFcstWagesTotal"); DBVarBdgtWgTtl
		 * = sql.CLRexecuteQuery(getContext(),
		 * VarFcstWagesTotal.replace("Region",getContext().getStringProperty("Region"))
		 * .replace("Zone",getContext().getStringProperty("Zone")).replace("LYWk",
		 * getContext().getStringProperty("LYWk"))
		 * .replace("Week",getContext().getStringProperty("SelectFiscalWeek")).replace(
		 * "Division",getContext().getStringProperty("Division")));
		 * System.out.println("DB Value: "+DBVarBdgtWgTtl);
		 * 
		 * VarBdgtWgVal = pageDailyTradingStatementGroupPO.RowWiseResult(
		 * objWeeklyTradingStatementStoreObjects,objWeeklyTradingStatementZoneObjects.
		 * Departments,objWeeklyTradingStatementZoneObjects.VarBudgetWages,
		 * objWeeklyTradingStatementZoneObjects.DataTable); VarBdgtWgTtl =
		 * pageDailyTradingStatementGroupPO.WeekToDateUITotal(
		 * objWeeklyTradingStatementZoneObjects.TotalTitle,
		 * objWeeklyTradingStatementZoneObjects.VarBudgetWagesTtl);
		 * 
		 * pageMonthlyPlanningPO.CompareValueDecimalFields(extentTest,DBVarBdgtWgVal,
		 * VarBdgtWgVal);
		 * pageMonthlyPlanningPO.CompareValueDecimalFields(extentTest,DBVarBdgtWgTtl,
		 * VarBdgtWgTtl); }
		 */
		
		if (OptionToView.contains("Budget")) {
			Report_AddStep("testcase", "Allowed Wages Validation", "", "", "");
			extentTest = report.startTest("Allowed Wages");

			String AllowedWages = data.getText("AllowedWages");
			DBAllowedWgVal = sql.CLRexecuteQuery(getContext(),
					AllowedWages.replace("Region", getContext().getStringProperty("Region"))
							.replace("Zone", getContext().getStringProperty("Zone"))
							.replace("LYWk", getContext().getStringProperty("LYWk"))
							.replace("Week", getContext().getStringProperty("SelectFiscalWeek"))
							.replace("Division", getContext().getStringProperty("Division")));
			System.out.println("DB Value: " + DBAllowedWgVal);

			String AllowedWagesTotal = data.getText("AllowedWagesTotal");
			DBAllowedWgTtl = sql.CLRexecuteQuery(getContext(),
					AllowedWagesTotal.replace("Region", getContext().getStringProperty("Region"))
							.replace("Zone", getContext().getStringProperty("Zone"))
							.replace("LYWk", getContext().getStringProperty("LYWk"))
							.replace("Week", getContext().getStringProperty("SelectFiscalWeek"))
							.replace("Division", getContext().getStringProperty("Division")));
			System.out.println("DB Value: " + DBAllowedWgTtl);

			AllowedWgVal = pageDailyTradingStatementGroupPO.RowWiseResult(objWeeklyTradingStatementStoreObjects,
					objWeeklyTradingStatementZoneObjects.Departments,
					objWeeklyTradingStatementZoneObjects.AllowedWagesswts,
					objWeeklyTradingStatementZoneObjects.DataTable);
			AllowedWgTtl = pageDailyTradingStatementGroupPO.WeekToDateUITotal(
					objWeeklyTradingStatementZoneObjects.TotalTitle,
					objWeeklyTradingStatementZoneObjects.AllowedWagesTtlwts);

			pageMonthlyPlanningPO.CompareValues(extentTest, DBAllowedWgVal, AllowedWgVal);
			pageMonthlyPlanningPO.CompareValues(extentTest, DBAllowedWgTtl, AllowedWgTtl);
		}
	}

	//////////////////////////////////////////////// CLR 1
	//////////////////////////////////////////////// /////////////////////////////////////////////////////////
	// Sales
	@Test(description = "Weekly Trading Statement - Zone, SalesColumnValidationNewTable", priority = 6)
	public void SalesColumnValidationNewTable() throws Exception {
		pageWeeklyPlanningPO = new WeeklyPlanningStorePage().createPage(getDriver(), getContext(), log, status, data,
				getReport(), getReportLogger(), WeeklyPlanningStorePage.class);
		pageDailyTradingStatementGroupPO = new MonthlyPlanningGroupPage().createPage(getDriver(), getContext(), log,
				status, data, getReport(), getReportLogger(), DailyTradingStatementGroupPage.class);
		TestStatus status = getTestStatus();
		System.out.println("Entering into TestCase: Sales Column Metrics Validation");
		
		OptionToView = getContext().getStringProperty("OptionToView");

		// ACTUAL SALES:
		Report_AddStep("testcase", " ACTUAL SALES COMBINED TABLE- WEEK WISE DATA", "", "", "");
		extentTest = report.startTest("Actual Sales");

		String ActualSalesNew = data.getText("ActualSales");

		DBActlVal = sql.CLRexecuteQuery(getContext(),
				ActualSalesNew.replace("Region", getContext().getStringProperty("Region"))
						.replace("Month", getContext().getStringProperty("MonthToTest"))
						.replace("Division", getContext().getStringProperty("Division"))
						.replace("Zone", getContext().getStringProperty("Zone"))
						.replace("Area", getContext().getStringProperty("Area")));
		System.out.println("DB Value: " + DBActlVal);

		String ActualSalesTotalNew = data.getText("ActualSalesTotal");
		DBActlTtl = sql.CLRexecuteQuery(getContext(),
				ActualSalesTotalNew.replace("Region", getContext().getStringProperty("Region"))
						.replace("Month", getContext().getStringProperty("MonthToTest"))
						.replace("Division", getContext().getStringProperty("Division"))
						.replace("Zone", getContext().getStringProperty("Zone"))
						.replace("Area", getContext().getStringProperty("Area")));
		System.out.println("DB Value: " + DBActlTtl);

		ActlVal = pageDailyTradingStatementGroupPO.RowWiseResult(objWeeklyTradingStatementStoreObjects,
				objWeeklyTradingStatementZoneObjects.Weeks, objWeeklyTradingStatementZoneObjects.ActualSalesNew,
				objWeeklyTradingStatementZoneObjects.Table);
		ActlTtl = pageDailyTradingStatementGroupPO.WeekToDateUITotal(objWeeklyTradingStatementZoneObjects.MonthTotal,
				objWeeklyTradingStatementZoneObjects.ActualSalesTtlNew);

		pageMonthlyPlanningPO.CompareValues(extentTest, DBActlVal, ActlVal);
		pageMonthlyPlanningPO.CompareValues(extentTest, DBActlTtl, ActlTtl);

		// WEEKLY PLANNED SALES:
		Report_AddStep("testcase", " WEEKLY PLANNED SALES COMBINED TABLE- WEEK WISE DATA", "", "", "");
		extentTest = report.startTest("Planned Sales - Week Data");

		String WeeklyPlannedSales = data.getText("WeeklyPlannedSales");
		DBPlndVal = sql.CLRexecuteQuery(getContext(),
				WeeklyPlannedSales.replace("Region", getContext().getStringProperty("Region"))
						.replace("Month", getContext().getStringProperty("MonthToTest"))
						.replace("Division", getContext().getStringProperty("Division"))
						.replace("Zone", getContext().getStringProperty("Zone"))
						.replace("Area", getContext().getStringProperty("Area")));
		System.out.println("DB Value: " + DBPlndVal);

		String WeeklyPlannedSalesTotal = data.getText("WeeklyPlannedSalesTotal");
		DBPlndTtl = sql.CLRexecuteQuery(getContext(),
				WeeklyPlannedSalesTotal.replace("Region", getContext().getStringProperty("Region"))
						.replace("Month", getContext().getStringProperty("MonthToTest"))
						.replace("Division", getContext().getStringProperty("Division"))
						.replace("Zone", getContext().getStringProperty("Zone"))
						.replace("Area", getContext().getStringProperty("Area")));
		System.out.println("DB Value: " + DBPlndTtl);

		PlndVal = pageDailyTradingStatementGroupPO.RowWiseResult(objWeeklyTradingStatementStoreObjects,
				objWeeklyTradingStatementZoneObjects.Weeks, objWeeklyTradingStatementZoneObjects.WeeklyPlannedSales,
				objWeeklyTradingStatementZoneObjects.Table);
		PlndTtl = pageDailyTradingStatementGroupPO.WeekToDateUITotal(objWeeklyTradingStatementZoneObjects.MonthTotal,
				objWeeklyTradingStatementZoneObjects.WeeklyPlannedSalesTtl);

		pageMonthlyPlanningPO.CompareValues(extentTest, DBPlndVal, PlndVal);
		pageMonthlyPlanningPO.CompareValues(extentTest, DBPlndTtl, PlndTtl);

		// MONTHLY PLANNED SALES:
		Report_AddStep("testcase", "MONTHLY PLANNED SALES COMBINED TABLE - WEEK WISE DATA", "", "", "");
		extentTest = report.startTest("Planned Sales - Month Data");

		String MonthlyPlannedSales = data.getText("MonthlyPlannedSales");
		DBPlndVal = sql.CLRexecuteQuery(getContext(),
				MonthlyPlannedSales.replace("Region", getContext().getStringProperty("Region"))
						.replace("Month", getContext().getStringProperty("MonthToTest"))
						.replace("Division", getContext().getStringProperty("Division"))
						.replace("Zone", getContext().getStringProperty("Zone"))
						.replace("Area", getContext().getStringProperty("Area")));
		System.out.println("DB Value: " + DBPlndVal);

		String MonthlyPlannedSalesTotal = data.getText("MonthlyPlannedSalesTotal");
		DBPlndTtl = sql.CLRexecuteQuery(getContext(),
				MonthlyPlannedSalesTotal.replace("Region", getContext().getStringProperty("Region"))
						.replace("Month", getContext().getStringProperty("MonthToTest"))
						.replace("Division", getContext().getStringProperty("Division"))
						.replace("Zone", getContext().getStringProperty("Zone"))
						.replace("Area", getContext().getStringProperty("Area")));
		System.out.println("DB Value: " + DBPlndTtl);

		PlndVal = pageDailyTradingStatementGroupPO.RowWiseResult(objWeeklyTradingStatementStoreObjects,
				objWeeklyTradingStatementZoneObjects.Weeks, objWeeklyTradingStatementZoneObjects.MonthlyPlannedSales,
				objWeeklyTradingStatementZoneObjects.Table);
		PlndTtl = pageDailyTradingStatementGroupPO.WeekToDateUITotal(objWeeklyTradingStatementZoneObjects.MonthTotal,
				objWeeklyTradingStatementZoneObjects.MonthlyPlannedSalesTtl);

		pageMonthlyPlanningPO.CompareValues(extentTest, DBPlndVal, PlndVal);
		pageMonthlyPlanningPO.CompareValues(extentTest, DBPlndTtl, PlndTtl);

		// BUDGET SALES:
		if (OptionToView.contains("Budget")) {
			Report_AddStep("testcase", "BUDGET SALES COMBINED TABLE- WEEK WISE DATA", "", "", "");
			extentTest = report.startTest("Budget Sales");

			String BudgetSales = data.getText("BudgetSales");
			DBBdgtVal = sql.CLRexecuteQuery(getContext(),
					BudgetSales.replace("Region", getContext().getStringProperty("Region"))
							.replace("Month", getContext().getStringProperty("MonthToTest"))
							.replace("Division", getContext().getStringProperty("Division"))
							.replace("Zone", getContext().getStringProperty("Zone"))
							.replace("Area", getContext().getStringProperty("Area")));
			System.out.println("DB Value: " + DBBdgtVal);

			String BudgetSalesTotal = data.getText("BudgetSalesTotal");
			DBBdgtTtl = sql.CLRexecuteQuery(getContext(),
					BudgetSalesTotal.replace("Region", getContext().getStringProperty("Region"))
							.replace("Month", getContext().getStringProperty("MonthToTest"))
							.replace("Division", getContext().getStringProperty("Division"))
							.replace("Zone", getContext().getStringProperty("Zone"))
							.replace("Area", getContext().getStringProperty("Area")));
			System.out.println("DB Value: " + DBBdgtTtl);

			BdgtVal = pageDailyTradingStatementGroupPO.RowWiseResult(objWeeklyTradingStatementStoreObjects,
					objWeeklyTradingStatementZoneObjects.Weeks, objWeeklyTradingStatementZoneObjects.BudgetSalesNew,
					objWeeklyTradingStatementZoneObjects.Table);
			BdgtTtl = pageDailyTradingStatementGroupPO.WeekToDateUITotal(
					objWeeklyTradingStatementZoneObjects.MonthTotal,
					objWeeklyTradingStatementZoneObjects.BudgetSalesTtlNew);

			pageMonthlyPlanningPO.CompareValues(extentTest, DBBdgtVal, BdgtVal);
			pageMonthlyPlanningPO.CompareValues(extentTest, DBBdgtTtl, BdgtTtl);
		}
		if (OptionToView.contains("Forecast")) {
			Report_AddStep("testcase", "FORECAST SALES COMBINED TABLE - WEEK WISE DATA", "", "", "");
			extentTest = report.startTest("Forecast Sales");

			String ForecastSales = data.getText("ForecastSales");
			DBBdgtVal = sql.CLRexecuteQuery(getContext(),
					ForecastSales.replace("Region", getContext().getStringProperty("Region"))
							.replace("Month", getContext().getStringProperty("MonthToTest"))
							.replace("Division", getContext().getStringProperty("Division"))
							.replace("Zone", getContext().getStringProperty("Zone"))
							.replace("Area", getContext().getStringProperty("Area")));
			System.out.println("DB Value: " + DBBdgtVal);

			String ForecastSalesTotal = data.getText("ForecastSalesTotal");
			DBBdgtTtl = sql.CLRexecuteQuery(getContext(),
					ForecastSalesTotal.replace("Region", getContext().getStringProperty("Region"))
							.replace("Month", getContext().getStringProperty("MonthToTest"))
							.replace("Division", getContext().getStringProperty("Division"))
							.replace("Zone", getContext().getStringProperty("Zone"))
							.replace("Area", getContext().getStringProperty("Area")));
			System.out.println("DB Value: " + DBBdgtTtl);

			BdgtVal = pageDailyTradingStatementGroupPO.RowWiseResult(objWeeklyTradingStatementStoreObjects,
					objWeeklyTradingStatementZoneObjects.Weeks, objWeeklyTradingStatementZoneObjects.ForecastSalesNew,
					objWeeklyTradingStatementZoneObjects.Table);
			BdgtTtl = pageDailyTradingStatementGroupPO.WeekToDateUITotal(
					objWeeklyTradingStatementZoneObjects.MonthTotal,
					objWeeklyTradingStatementZoneObjects.ForecastSalesTtlNew);

			pageMonthlyPlanningPO.CompareValues(extentTest, DBBdgtVal, BdgtVal);
			pageMonthlyPlanningPO.CompareValues(extentTest, DBBdgtTtl, BdgtTtl);
		}
	}

	///// WAGES///
	@Test(description = "Weekly Trading Statement - Zone, SalesColumnValidationNewTable", priority = 7)
	public void WagesColumnValidationNewTable() throws Exception {
		pageWeeklyPlanningPO = new WeeklyPlanningStorePage().createPage(getDriver(), getContext(), log, status, data,
				getReport(), getReportLogger(), WeeklyPlanningStorePage.class);
		pageDailyTradingStatementGroupPO = new MonthlyPlanningGroupPage().createPage(getDriver(), getContext(), log,
				status, data, getReport(), getReportLogger(), DailyTradingStatementGroupPage.class);
		TestStatus status = getTestStatus();
		System.out.println("Entering into TestCase: Sales Column Metrics Validation");
		OptionToView = getContext().getStringProperty("OptionToView");

		// ACTUAL WAGES:
		Report_AddStep("testcase", " ACTUAL WAGES COMBINED TABLE- WEEK WISE DATA", "", "", "");
		extentTest = report.startTest("Actual Wages");

		String ActualWagesNew = data.getText("ActualWages");
		DBActlVal = sql.CLRexecuteQuery(getContext(),
				ActualWagesNew.replace("Region", getContext().getStringProperty("Region"))
						.replace("Month", getContext().getStringProperty("MonthToTest"))
						.replace("Division", getContext().getStringProperty("Division"))
						.replace("Zone", getContext().getStringProperty("Zone"))
						.replace("Area", getContext().getStringProperty("Area")));
		System.out.println("DB Value: " + DBActlVal);

		String ActualWagesTotalNew = data.getText("ActualWagesTotal");
		DBActlTtl = sql.CLRexecuteQuery(getContext(),
				ActualWagesTotalNew.replace("Region", getContext().getStringProperty("Region"))
						.replace("Month", getContext().getStringProperty("MonthToTest"))
						.replace("Division", getContext().getStringProperty("Division"))
						.replace("Zone", getContext().getStringProperty("Zone"))
						.replace("Area", getContext().getStringProperty("Area")));
		System.out.println("DB Value: " + DBActlTtl);

		ActlVal = pageDailyTradingStatementGroupPO.RowWiseResult(objWeeklyTradingStatementStoreObjects,
				objWeeklyTradingStatementZoneObjects.Weeks, objWeeklyTradingStatementZoneObjects.ActualWagesNew,
				objWeeklyTradingStatementZoneObjects.Table);
		ActlTtl = pageDailyTradingStatementGroupPO.WeekToDateUITotal(objWeeklyTradingStatementZoneObjects.MonthTotal,
				objWeeklyTradingStatementZoneObjects.ActualWagesTtlNew);

		pageMonthlyPlanningPO.CompareValues(extentTest, DBActlVal, ActlVal);
		pageMonthlyPlanningPO.CompareValues(extentTest, DBActlTtl, ActlTtl);

		// WEEKLY PLANNED WAGES:
		Report_AddStep("testcase", " WEEKLY PLANNED WAGES COMBINED TABLE- WEEK WISE DATA", "", "", "");
		extentTest = report.startTest("Planned Wages - Week Data");

		String WeeklyPlannedWages = data.getText("WeeklyPlannedWages");
		DBPlndVal = sql.CLRexecuteQuery(getContext(),
				WeeklyPlannedWages.replace("Region", getContext().getStringProperty("Region"))
						.replace("Month", getContext().getStringProperty("MonthToTest"))
						.replace("Division", getContext().getStringProperty("Division"))
						.replace("Zone", getContext().getStringProperty("Zone"))
						.replace("Area", getContext().getStringProperty("Area")));
		System.out.println("DB Value: " + DBPlndVal);

		String WeeklyPlannedWagesTotal = data.getText("WeeklyPlannedWagesTotal");
		DBPlndTtl = sql.CLRexecuteQuery(getContext(),
				WeeklyPlannedWagesTotal.replace("Region", getContext().getStringProperty("Region"))
						.replace("Month", getContext().getStringProperty("MonthToTest"))
						.replace("Division", getContext().getStringProperty("Division"))
						.replace("Zone", getContext().getStringProperty("Zone"))
						.replace("Area", getContext().getStringProperty("Area")));
		System.out.println("DB Value: " + DBPlndTtl);

		PlndVal = pageDailyTradingStatementGroupPO.RowWiseResult(objWeeklyTradingStatementStoreObjects,
				objWeeklyTradingStatementZoneObjects.Weeks, objWeeklyTradingStatementZoneObjects.WeeklyPlannedWages,
				objWeeklyTradingStatementZoneObjects.Table);
		PlndTtl = pageDailyTradingStatementGroupPO.WeekToDateUITotal(objWeeklyTradingStatementZoneObjects.MonthTotal,
				objWeeklyTradingStatementZoneObjects.WeeklyPlannedWagesTtl);

		pageMonthlyPlanningPO.CompareValues(extentTest, DBPlndVal, PlndVal);
		pageMonthlyPlanningPO.CompareValues(extentTest, DBPlndTtl, PlndTtl);

		// MONTHLY PLANNED WAGES:
		Report_AddStep("testcase", "MONTHLY PLANNED WAGES COMBINED TABLE - WEEK WISE DATA", "", "", "");
		extentTest = report.startTest("Planned Wages - Month Data");

		String MonthlyPlannedWages = data.getText("MonthlyPlannedWages");
		DBPlndVal = sql.CLRexecuteQuery(getContext(),
				MonthlyPlannedWages.replace("Region", getContext().getStringProperty("Region"))
						.replace("Month", getContext().getStringProperty("MonthToTest"))
						.replace("Division", getContext().getStringProperty("Division"))
						.replace("Zone", getContext().getStringProperty("Zone"))
						.replace("Area", getContext().getStringProperty("Area")));
		System.out.println("DB Value: " + DBPlndVal);

		String MonthlyPlannedWagesTotal = data.getText("MonthlyPlannedWagesTotal");
		DBPlndTtl = sql.CLRexecuteQuery(getContext(),
				MonthlyPlannedWagesTotal.replace("Region", getContext().getStringProperty("Region"))
						.replace("Month", getContext().getStringProperty("MonthToTest"))
						.replace("Division", getContext().getStringProperty("Division"))
						.replace("Zone", getContext().getStringProperty("Zone"))
						.replace("Area", getContext().getStringProperty("Area")));
		System.out.println("DB Value: " + DBPlndTtl);

		PlndVal = pageDailyTradingStatementGroupPO.RowWiseResult(objWeeklyTradingStatementStoreObjects,
				objWeeklyTradingStatementZoneObjects.Weeks, objWeeklyTradingStatementZoneObjects.MonthlyPlannedWages,
				objWeeklyTradingStatementZoneObjects.Table);
		PlndTtl = pageDailyTradingStatementGroupPO.WeekToDateUITotal(objWeeklyTradingStatementZoneObjects.MonthTotal,
				objWeeklyTradingStatementZoneObjects.MonthlyPlannedWagesTtl);

		pageMonthlyPlanningPO.CompareValues(extentTest, DBPlndVal, PlndVal);
		pageMonthlyPlanningPO.CompareValues(extentTest, DBPlndTtl, PlndTtl);

		// BUDGET WAGES:
		if (OptionToView.contains("Budget")) {
			Report_AddStep("testcase", "BUDGET WAGES COMBINED TABLE- WEEK WISE DATA", "", "", "");
			extentTest = report.startTest("Budget Wages");

			String BudgetWages = data.getText("BudgetWages");
			DBBdgtVal = sql.CLRexecuteQuery(getContext(),
					BudgetWages.replace("Region", getContext().getStringProperty("Region"))
							.replace("Month", getContext().getStringProperty("MonthToTest"))
							.replace("Division", getContext().getStringProperty("Division"))
							.replace("Zone", getContext().getStringProperty("Zone"))
							.replace("Area", getContext().getStringProperty("Area")));
			System.out.println("DB Value: " + DBBdgtVal);

			String BudgetWagesTotal = data.getText("BudgetWagesTotal");
			DBBdgtTtl = sql.CLRexecuteQuery(getContext(),
					BudgetWagesTotal.replace("Region", getContext().getStringProperty("Region"))
							.replace("Month", getContext().getStringProperty("MonthToTest"))
							.replace("Division", getContext().getStringProperty("Division"))
							.replace("Zone", getContext().getStringProperty("Zone"))
							.replace("Area", getContext().getStringProperty("Area")));
			System.out.println("DB Value: " + DBBdgtTtl);

			BdgtVal = pageDailyTradingStatementGroupPO.RowWiseResult(objWeeklyTradingStatementStoreObjects,
					objWeeklyTradingStatementZoneObjects.Weeks, objWeeklyTradingStatementZoneObjects.BudgetWagesNew,
					objWeeklyTradingStatementZoneObjects.Table);
			BdgtTtl = pageDailyTradingStatementGroupPO.WeekToDateUITotal(
					objWeeklyTradingStatementZoneObjects.MonthTotal,
					objWeeklyTradingStatementZoneObjects.BudgetWagesTtlNew);

			pageMonthlyPlanningPO.CompareValues(extentTest, DBBdgtVal, BdgtVal);
			pageMonthlyPlanningPO.CompareValues(extentTest, DBBdgtTtl, BdgtTtl);
		}
		if (OptionToView.contains("Forecast")) {
			Report_AddStep("testcase", "FORECAST WAGES COMBINED TABLE - WEEK WISE DATA", "", "", "");
			extentTest = report.startTest("Forecast Wages");

			String ForecastWages = data.getText("ForecastWages");
			DBBdgtVal = sql.CLRexecuteQuery(getContext(),
					ForecastWages.replace("Region", getContext().getStringProperty("Region"))
							.replace("Month", getContext().getStringProperty("MonthToTest"))
							.replace("Division", getContext().getStringProperty("Division"))
							.replace("Zone", getContext().getStringProperty("Zone"))
							.replace("Area", getContext().getStringProperty("Area")));
			System.out.println("DB Value: " + DBBdgtVal);

			String ForecastWagesTotal = data.getText("ForecastWagesTotal");
			DBBdgtTtl = sql.CLRexecuteQuery(getContext(),
					ForecastWagesTotal.replace("Region", getContext().getStringProperty("Region"))
							.replace("Month", getContext().getStringProperty("MonthToTest"))
							.replace("Division", getContext().getStringProperty("Division"))
							.replace("Zone", getContext().getStringProperty("Zone"))
							.replace("Area", getContext().getStringProperty("Area")));
			System.out.println("DB Value: " + DBBdgtTtl);

			BdgtVal = pageDailyTradingStatementGroupPO.RowWiseResult(objWeeklyTradingStatementStoreObjects,
					objWeeklyTradingStatementZoneObjects.Weeks, objWeeklyTradingStatementZoneObjects.ForecastWagesNew,
					objWeeklyTradingStatementZoneObjects.Table);
			BdgtTtl = pageDailyTradingStatementGroupPO.WeekToDateUITotal(
					objWeeklyTradingStatementZoneObjects.MonthTotal,
					objWeeklyTradingStatementZoneObjects.ForecastWagesTtlNew);

			pageMonthlyPlanningPO.CompareValues(extentTest, DBBdgtVal, BdgtVal);
			pageMonthlyPlanningPO.CompareValues(extentTest, DBBdgtTtl, BdgtTtl);
		}
	}

	///// WAGE%///
	@Test(description = "Weekly Trading Statement - Zone, WagePercentageColumnValidationNewTable", priority = 8)
	public void WagePercentageColumnValidationNewTable() throws Exception {
		pageWeeklyPlanningPO = new WeeklyPlanningStorePage().createPage(getDriver(), getContext(), log, status, data,
				getReport(), getReportLogger(), WeeklyPlanningStorePage.class);
		pageDailyTradingStatementGroupPO = new MonthlyPlanningGroupPage().createPage(getDriver(), getContext(), log,
				status, data, getReport(), getReportLogger(), DailyTradingStatementGroupPage.class);
		TestStatus status = getTestStatus();
		System.out.println("Entering into TestCase: Sales Column Metrics Validation");
		OptionToView = getContext().getStringProperty("OptionToView");

		// ACTUAL WAGE%:
		Report_AddStep("testcase", " ACTUAL WAGE PERCENTAGE COMBINED TABLE- WEEK WISE DATA", "", "", "");
		extentTest = report.startTest("Actual Wages Prcnt");

		String ActualWagePercentageNew = data.getText("ActualWagePercentage");
		DBActlVal = sql.CLRexecuteQuery(getContext(),
				ActualWagePercentageNew.replace("Region", getContext().getStringProperty("Region"))
						.replace("Month", getContext().getStringProperty("MonthToTest"))
						.replace("Division", getContext().getStringProperty("Division"))
						.replace("Zone", getContext().getStringProperty("Zone"))
						.replace("Area", getContext().getStringProperty("Area")));
		System.out.println("DB Value: " + DBActlVal);

		String ActualWagePercentageTotal = data.getText("ActualWagePercentageTotal");
		DBActlTtl = sql.CLRexecuteQuery(getContext(),
				ActualWagePercentageTotal.replace("Region", getContext().getStringProperty("Region"))
						.replace("Month", getContext().getStringProperty("MonthToTest"))
						.replace("Division", getContext().getStringProperty("Division"))
						.replace("Zone", getContext().getStringProperty("Zone"))
						.replace("Area", getContext().getStringProperty("Area")));
		System.out.println("DB Value: " + DBActlTtl);

		ActlVal = pageDailyTradingStatementGroupPO.RowWiseResult(objWeeklyTradingStatementStoreObjects,
				objWeeklyTradingStatementZoneObjects.Weeks, objWeeklyTradingStatementZoneObjects.ActualWagePerNew,
				objWeeklyTradingStatementZoneObjects.Table);
		ActlTtl = pageDailyTradingStatementGroupPO.WeekToDateUITotal(objWeeklyTradingStatementZoneObjects.MonthTotal,
				objWeeklyTradingStatementZoneObjects.ActualWagePerTtlNew);

		pageMonthlyPlanningPO.CompareValueDecimalFields(extentTest, DBActlVal, ActlVal);
		pageMonthlyPlanningPO.CompareValueDecimalFields(extentTest, DBActlTtl, ActlTtl);

		// WEEKLY PLANNED WAGE%:
		Report_AddStep("testcase", " WEEKLY PLANNED WAGE PERCENTAGE COMBINED TABLE- WEEK WISE DATA", "", "", "");
		extentTest = report.startTest("Planned Wages Prcnt - Week Data");

		String WeeklyPlannedWagePercentage = data.getText("WeeklyPlannedWagePercentage");
		DBPlndVal = sql.CLRexecuteQuery(getContext(),
				WeeklyPlannedWagePercentage.replace("Region", getContext().getStringProperty("Region"))
						.replace("Month", getContext().getStringProperty("MonthToTest"))
						.replace("Division", getContext().getStringProperty("Division"))
						.replace("Zone", getContext().getStringProperty("Zone"))
						.replace("Area", getContext().getStringProperty("Area")));
		System.out.println("DB Value: " + DBPlndVal);

		String WeeklyPlannedWagePercentageTotal = data.getText("WeeklyPlannedWagePercentageTotal");
		DBPlndTtl = sql.CLRexecuteQuery(getContext(),
				WeeklyPlannedWagePercentageTotal.replace("Region", getContext().getStringProperty("Region"))
						.replace("Month", getContext().getStringProperty("MonthToTest"))
						.replace("Division", getContext().getStringProperty("Division"))
						.replace("Zone", getContext().getStringProperty("Zone"))
						.replace("Area", getContext().getStringProperty("Area")));
		System.out.println("DB Value: " + DBPlndTtl);

		PlndVal = pageDailyTradingStatementGroupPO.RowWiseResult(objWeeklyTradingStatementStoreObjects,
				objWeeklyTradingStatementZoneObjects.Weeks, objWeeklyTradingStatementZoneObjects.WeeklyPlannedWagePer,
				objWeeklyTradingStatementZoneObjects.Table);
		PlndTtl = pageDailyTradingStatementGroupPO.WeekToDateUITotal(objWeeklyTradingStatementZoneObjects.MonthTotal,
				objWeeklyTradingStatementZoneObjects.WeeklyPlannedWagePerTtl);

		pageMonthlyPlanningPO.CompareValueDecimalFields(extentTest, DBPlndVal, PlndVal);
		pageMonthlyPlanningPO.CompareValueDecimalFields(extentTest, DBPlndTtl, PlndTtl);

		// MONTHLY PLANNED WAGE%:
		Report_AddStep("testcase", "MONTHLY PLANNED WAGE PERCENTAGE COMBINED TABLE - WEEK WISE DATA", "", "", "");
		extentTest = report.startTest("Planned Wages Prcnt - Month Data");

		String MonthlyPlannedWagePercentage = data.getText("MonthlyPlannedWagePercentage");
		DBPlndVal = sql.CLRexecuteQuery(getContext(),
				MonthlyPlannedWagePercentage.replace("Region", getContext().getStringProperty("Region"))
						.replace("Month", getContext().getStringProperty("MonthToTest"))
						.replace("Division", getContext().getStringProperty("Division"))
						.replace("Zone", getContext().getStringProperty("Zone"))
						.replace("Area", getContext().getStringProperty("Area")));
		System.out.println("DB Value: " + DBPlndVal);

		String MonthlyPlannedWagePercentageTotal = data.getText("MonthlyPlannedWagePercentageTotal");
		DBPlndTtl = sql.CLRexecuteQuery(getContext(),
				MonthlyPlannedWagePercentageTotal.replace("Region", getContext().getStringProperty("Region"))
						.replace("Month", getContext().getStringProperty("MonthToTest"))
						.replace("Division", getContext().getStringProperty("Division"))
						.replace("Zone", getContext().getStringProperty("Zone"))
						.replace("Area", getContext().getStringProperty("Area")));
		System.out.println("DB Value: " + DBPlndTtl);

		PlndVal = pageDailyTradingStatementGroupPO.RowWiseResult(objWeeklyTradingStatementStoreObjects,
				objWeeklyTradingStatementZoneObjects.Weeks, objWeeklyTradingStatementZoneObjects.MonthlyPlannedWagePer,
				objWeeklyTradingStatementZoneObjects.Table);
		PlndTtl = pageDailyTradingStatementGroupPO.WeekToDateUITotal(objWeeklyTradingStatementZoneObjects.MonthTotal,
				objWeeklyTradingStatementZoneObjects.MonthlyPlannedWagePerTtl);

		pageMonthlyPlanningPO.CompareValueDecimalFields(extentTest, DBPlndVal, PlndVal);
		pageMonthlyPlanningPO.CompareValueDecimalFields(extentTest, DBPlndTtl, PlndTtl);

		// BUDGET WAGE%:
		if (OptionToView.contains("Budget")) {
			Report_AddStep("testcase", "BUDGET WAGE PERCENTAGE COMBINED TABLE- WEEK WISE DATA", "", "", "");
			extentTest = report.startTest("Budget Wages Prcnt");

			String BudgetWagePercentage = data.getText("BudgetWagePercentage");
			DBBdgtVal = sql.CLRexecuteQuery(getContext(),
					BudgetWagePercentage.replace("Region", getContext().getStringProperty("Region"))
							.replace("Month", getContext().getStringProperty("MonthToTest"))
							.replace("Division", getContext().getStringProperty("Division"))
							.replace("Zone", getContext().getStringProperty("Zone"))
							.replace("Area", getContext().getStringProperty("Area")));
			System.out.println("DB Value: " + DBBdgtVal);

			String BudgetWagePercentageTotal = data.getText("BudgetWagePercentageTotal");
			DBBdgtTtl = sql.CLRexecuteQuery(getContext(),
					BudgetWagePercentageTotal.replace("Region", getContext().getStringProperty("Region"))
							.replace("Month", getContext().getStringProperty("MonthToTest"))
							.replace("Division", getContext().getStringProperty("Division"))
							.replace("Zone", getContext().getStringProperty("Zone"))
							.replace("Area", getContext().getStringProperty("Area")));
			System.out.println("DB Value: " + DBBdgtTtl);

			BdgtVal = pageDailyTradingStatementGroupPO.RowWiseResult(objWeeklyTradingStatementStoreObjects,
					objWeeklyTradingStatementZoneObjects.Weeks, objWeeklyTradingStatementZoneObjects.BudgetWagePerNew,
					objWeeklyTradingStatementZoneObjects.Table);
			BdgtTtl = pageDailyTradingStatementGroupPO.WeekToDateUITotal(
					objWeeklyTradingStatementZoneObjects.MonthTotal,
					objWeeklyTradingStatementZoneObjects.BudgetWagePerTtlNew);

			pageMonthlyPlanningPO.CompareValueDecimalFields(extentTest, DBBdgtVal, BdgtVal);
			pageMonthlyPlanningPO.CompareValueDecimalFields(extentTest, DBBdgtTtl, BdgtTtl);
		}
		if (OptionToView.contains("Forecast")) {
			Report_AddStep("testcase", "FORECAST WAGE PERCENTAGE COMBINED TABLE - WEEK WISE DATA", "", "", "");
			extentTest = report.startTest("Forecast Wages Prcnt");

			String ForecastWagePercentage = data.getText("ForecastWagePercentage");
			DBBdgtVal = sql.CLRexecuteQuery(getContext(),
					ForecastWagePercentage.replace("Region", getContext().getStringProperty("Region"))
							.replace("Month", getContext().getStringProperty("MonthToTest"))
							.replace("Division", getContext().getStringProperty("Division"))
							.replace("Zone", getContext().getStringProperty("Zone"))
							.replace("Area", getContext().getStringProperty("Area")));
			System.out.println("DB Value: " + DBBdgtVal);

			String ForecastWagePercentageTotal = data.getText("ForecastWagePercentageTotal");
			DBBdgtTtl = sql.CLRexecuteQuery(getContext(),
					ForecastWagePercentageTotal.replace("Region", getContext().getStringProperty("Region"))
							.replace("Month", getContext().getStringProperty("MonthToTest"))
							.replace("Division", getContext().getStringProperty("Division"))
							.replace("Zone", getContext().getStringProperty("Zone"))
							.replace("Area", getContext().getStringProperty("Area")));
			System.out.println("DB Value: " + DBBdgtTtl);

			BdgtVal = pageDailyTradingStatementGroupPO.RowWiseResult(objWeeklyTradingStatementStoreObjects,
					objWeeklyTradingStatementZoneObjects.Weeks, objWeeklyTradingStatementZoneObjects.ForecastWagePerNew,
					objWeeklyTradingStatementZoneObjects.Table);
			BdgtTtl = pageDailyTradingStatementGroupPO.WeekToDateUITotal(
					objWeeklyTradingStatementZoneObjects.MonthTotal,
					objWeeklyTradingStatementZoneObjects.ForecastWagePerTtlNew);

			pageMonthlyPlanningPO.CompareValueDecimalFields(extentTest, DBBdgtVal, BdgtVal);
			pageMonthlyPlanningPO.CompareValueDecimalFields(extentTest, DBBdgtTtl, BdgtTtl);
		}
	}

	///// OR///
	@Test(description = "Weekly Trading Statement - Zone, ORColumnValidationNewTable", priority = 9)
	public void ORColumnValidationNewTable() throws Exception {
		pageWeeklyPlanningPO = new WeeklyPlanningStorePage().createPage(getDriver(), getContext(), log, status, data,
				getReport(), getReportLogger(), WeeklyPlanningStorePage.class);
		pageDailyTradingStatementGroupPO = new MonthlyPlanningGroupPage().createPage(getDriver(), getContext(), log,
				status, data, getReport(), getReportLogger(), DailyTradingStatementGroupPage.class);
		TestStatus status = getTestStatus();
		System.out.println("Entering into TestCase: Sales Column Metrics Validation");
		OptionToView = getContext().getStringProperty("OptionToView");

		// ACTUAL OR:
		Report_AddStep("testcase", " ACTUAL OR COMBINED TABLE- WEEK WISE DATA", "", "", "");
		extentTest = report.startTest("Actual OR");

		String ActualOR = data.getText("ActualOR");
		DBActlVal = sql.CLRexecuteQuery(getContext(),
				ActualOR.replace("Region", getContext().getStringProperty("Region"))
						.replace("Month", getContext().getStringProperty("MonthToTest"))
						.replace("Division", getContext().getStringProperty("Division"))
						.replace("Zone", getContext().getStringProperty("Zone"))
						.replace("Area", getContext().getStringProperty("Area")));
		System.out.println("DB Value: " + DBActlVal);

		String ActualORTotal = data.getText("ActualORTotal");
		DBActlTtl = sql.CLRexecuteQuery(getContext(),
				ActualORTotal.replace("Region", getContext().getStringProperty("Region"))
						.replace("Month", getContext().getStringProperty("MonthToTest"))
						.replace("Division", getContext().getStringProperty("Division"))
						.replace("Zone", getContext().getStringProperty("Zone"))
						.replace("Area", getContext().getStringProperty("Area")));
		System.out.println("DB Value: " + DBActlTtl);

		ActlVal = pageDailyTradingStatementGroupPO.RowWiseResult(objWeeklyTradingStatementStoreObjects,
				objWeeklyTradingStatementZoneObjects.Weeks, objWeeklyTradingStatementZoneObjects.ActualORNew,
				objWeeklyTradingStatementZoneObjects.Table);
		ActlTtl = pageDailyTradingStatementGroupPO.WeekToDateUITotal(objWeeklyTradingStatementZoneObjects.MonthTotal,
				objWeeklyTradingStatementZoneObjects.ActualORTtlNew);

		pageMonthlyPlanningPO.CompareValueDecimalFields(extentTest, DBActlVal, ActlVal);
		pageMonthlyPlanningPO.CompareValueDecimalFields(extentTest, DBActlTtl, ActlTtl);

		// WEEKLY PLANNED OR:
		Report_AddStep("testcase", " WEEKLY PLANNED OR  COMBINED TABLE- WEEK WISE DATA", "", "", "");
		extentTest = report.startTest("Planned OR - Week data");

		String WeeklyPlannedOR = data.getText("WeeklyPlannedOR");
		DBPlndVal = sql.CLRexecuteQuery(getContext(),
				WeeklyPlannedOR.replace("Region", getContext().getStringProperty("Region"))
						.replace("Month", getContext().getStringProperty("MonthToTest"))
						.replace("Division", getContext().getStringProperty("Division"))
						.replace("Zone", getContext().getStringProperty("Zone"))
						.replace("Area", getContext().getStringProperty("Area")));
		System.out.println("DB Value: " + DBPlndVal);

		String WeeklyPlannedORTotal = data.getText("WeeklyPlannedORTotal");
		DBPlndTtl = sql.CLRexecuteQuery(getContext(),
				WeeklyPlannedORTotal.replace("Region", getContext().getStringProperty("Region"))
						.replace("Month", getContext().getStringProperty("MonthToTest"))
						.replace("Division", getContext().getStringProperty("Division"))
						.replace("Zone", getContext().getStringProperty("Zone"))
						.replace("Area", getContext().getStringProperty("Area")));
		System.out.println("DB Value: " + DBPlndTtl);

		PlndVal = pageDailyTradingStatementGroupPO.RowWiseResult(objWeeklyTradingStatementStoreObjects,
				objWeeklyTradingStatementZoneObjects.Weeks, objWeeklyTradingStatementZoneObjects.WeeklyPlannedOR,
				objWeeklyTradingStatementZoneObjects.Table);
		PlndTtl = pageDailyTradingStatementGroupPO.WeekToDateUITotal(objWeeklyTradingStatementZoneObjects.MonthTotal,
				objWeeklyTradingStatementZoneObjects.WeeklyPlannedORTtl);

		pageMonthlyPlanningPO.CompareValueDecimalFields(extentTest, DBPlndVal, PlndVal);
		pageMonthlyPlanningPO.CompareValueDecimalFields(extentTest, DBPlndTtl, PlndTtl);

		// MONTHLY PLANNED OR:
		Report_AddStep("testcase", "MONTHLY PLANNED OR  COMBINED TABLE - WEEK WISE DATA", "", "", "");
		extentTest = report.startTest("Planned OR - Month Data");

		String MonthlyPlannedOR = data.getText("MonthlyPlannedOR");
		DBPlndVal = sql.CLRexecuteQuery(getContext(),
				MonthlyPlannedOR.replace("Region", getContext().getStringProperty("Region"))
						.replace("Month", getContext().getStringProperty("MonthToTest"))
						.replace("Division", getContext().getStringProperty("Division"))
						.replace("Zone", getContext().getStringProperty("Zone"))
						.replace("Area", getContext().getStringProperty("Area")));
		System.out.println("DB Value: " + DBPlndVal);

		String MonthlyPlannedORTotal = data.getText("MonthlyPlannedORTotal");
		DBPlndTtl = sql.CLRexecuteQuery(getContext(),
				MonthlyPlannedORTotal.replace("Region", getContext().getStringProperty("Region"))
						.replace("Month", getContext().getStringProperty("MonthToTest"))
						.replace("Division", getContext().getStringProperty("Division"))
						.replace("Zone", getContext().getStringProperty("Zone"))
						.replace("Area", getContext().getStringProperty("Area")));
		System.out.println("DB Value: " + DBPlndTtl);

		PlndVal = pageDailyTradingStatementGroupPO.RowWiseResult(objWeeklyTradingStatementStoreObjects,
				objWeeklyTradingStatementZoneObjects.Weeks, objWeeklyTradingStatementZoneObjects.MonthlyPlannedOR,
				objWeeklyTradingStatementZoneObjects.Table);
		PlndTtl = pageDailyTradingStatementGroupPO.WeekToDateUITotal(objWeeklyTradingStatementZoneObjects.MonthTotal,
				objWeeklyTradingStatementZoneObjects.MonthlyPlannedORTtl);

		pageMonthlyPlanningPO.CompareValueDecimalFields(extentTest, DBPlndVal, PlndVal);
		pageMonthlyPlanningPO.CompareValueDecimalFields(extentTest, DBPlndTtl, PlndTtl);
	}

	///// CPH///
	@Test(description = "Weekly Trading Statement - Zone, CPHColumnValidationNewTable", priority = 10)
	public void CPHColumnValidationNewTable() throws Exception {
		pageWeeklyPlanningPO = new WeeklyPlanningStorePage().createPage(getDriver(), getContext(), log, status, data,
				getReport(), getReportLogger(), WeeklyPlanningStorePage.class);
		pageDailyTradingStatementGroupPO = new MonthlyPlanningGroupPage().createPage(getDriver(), getContext(), log,
				status, data, getReport(), getReportLogger(), DailyTradingStatementGroupPage.class);
		TestStatus status = getTestStatus();
		System.out.println("Entering into TestCase: CPH Column Metrics Validation");
		OptionToView = getContext().getStringProperty("OptionToView");

		// ACTUAL CPH:
		Report_AddStep("testcase", " ACTUAL CPH COMBINED TABLE- WEEK WISE DATA", "", "", "");
		extentTest = report.startTest("Actual CPH");

		String ActualCPH = data.getText("ActualCPH");
		DBActlVal = sql.CLRexecuteQuery(getContext(),
				ActualCPH.replace("Region", getContext().getStringProperty("Region"))
						.replace("Month", getContext().getStringProperty("MonthToTest"))
						.replace("Division", getContext().getStringProperty("Division"))
						.replace("Zone", getContext().getStringProperty("Zone"))
						.replace("Area", getContext().getStringProperty("Area")));
		System.out.println("DB Value: " + DBActlVal);

		String ActualCPHTotal = data.getText("ActualCPHTotal");
		DBActlTtl = sql.CLRexecuteQuery(getContext(),
				ActualCPHTotal.replace("Region", getContext().getStringProperty("Region"))
						.replace("Month", getContext().getStringProperty("MonthToTest"))
						.replace("Division", getContext().getStringProperty("Division"))
						.replace("Zone", getContext().getStringProperty("Zone"))
						.replace("Area", getContext().getStringProperty("Area")));
		System.out.println("DB Value: " + DBActlTtl);

		ActlVal = pageDailyTradingStatementGroupPO.RowWiseResult(objWeeklyTradingStatementStoreObjects,
				objWeeklyTradingStatementZoneObjects.Weeks, objWeeklyTradingStatementZoneObjects.ActualCPHNew,
				objWeeklyTradingStatementZoneObjects.Table);
		ActlTtl = pageDailyTradingStatementGroupPO.WeekToDateUITotal(objWeeklyTradingStatementZoneObjects.MonthTotal,
				objWeeklyTradingStatementZoneObjects.ActualCPHTtlNew);

		pageMonthlyPlanningPO.CompareValueDecimalFields(extentTest, DBActlVal, ActlVal);
		pageMonthlyPlanningPO.CompareValueDecimalFields(extentTest, DBActlTtl, ActlTtl);

		// WEEKLY PLANNED CPH:
		Report_AddStep("testcase", " WEEKLY PLANNED CPH  COMBINED TABLE- WEEK WISE DATA", "", "", "");
		extentTest = report.startTest("Planned CPH - Week Data");

		String WeeklyPlannedCPH = data.getText("WeeklyPlannedCPH");
		DBPlndVal = sql.CLRexecuteQuery(getContext(),
				WeeklyPlannedCPH.replace("Region", getContext().getStringProperty("Region"))
						.replace("Month", getContext().getStringProperty("MonthToTest"))
						.replace("Division", getContext().getStringProperty("Division"))
						.replace("Zone", getContext().getStringProperty("Zone"))
						.replace("Area", getContext().getStringProperty("Area")));
		System.out.println("DB Value: " + DBPlndVal);

		String WeeklyPlannedCPHTotal = data.getText("WeeklyPlannedCPHTotal");
		DBPlndTtl = sql.CLRexecuteQuery(getContext(),
				WeeklyPlannedCPHTotal.replace("Region", getContext().getStringProperty("Region"))
						.replace("Month", getContext().getStringProperty("MonthToTest"))
						.replace("Division", getContext().getStringProperty("Division"))
						.replace("Zone", getContext().getStringProperty("Zone"))
						.replace("Area", getContext().getStringProperty("Area")));
		System.out.println("DB Value: " + DBPlndTtl);

		PlndVal = pageDailyTradingStatementGroupPO.RowWiseResult(objWeeklyTradingStatementStoreObjects,
				objWeeklyTradingStatementZoneObjects.Weeks, objWeeklyTradingStatementZoneObjects.WeeklyPlannedCPH,
				objWeeklyTradingStatementZoneObjects.Table);
		PlndTtl = pageDailyTradingStatementGroupPO.WeekToDateUITotal(objWeeklyTradingStatementZoneObjects.MonthTotal,
				objWeeklyTradingStatementZoneObjects.WeeklyPlannedCPHTtl);

		pageMonthlyPlanningPO.CompareValueDecimalFields(extentTest, DBPlndVal, PlndVal);
		pageMonthlyPlanningPO.CompareValueDecimalFields(extentTest, DBPlndTtl, PlndTtl);

		// MONTHLY PLANNED CPH:
		Report_AddStep("testcase", "MONTHLY PLANNED CPH  COMBINED TABLE - WEEK WISE DATA", "", "", "");
		extentTest = report.startTest("Planned CPH - Month Data");

		String MonthlyPlannedCPH = data.getText("MonthlyPlannedCPH");
		DBPlndVal = sql.CLRexecuteQuery(getContext(),
				MonthlyPlannedCPH.replace("Region", getContext().getStringProperty("Region"))
						.replace("Month", getContext().getStringProperty("MonthToTest"))
						.replace("Division", getContext().getStringProperty("Division"))
						.replace("Zone", getContext().getStringProperty("Zone"))
						.replace("Area", getContext().getStringProperty("Area")));
		System.out.println("DB Value: " + DBPlndVal);

		String MonthlyPlannedCPHTotal = data.getText("MonthlyPlannedCPHTotal");
		DBPlndTtl = sql.CLRexecuteQuery(getContext(),
				MonthlyPlannedCPHTotal.replace("Region", getContext().getStringProperty("Region"))
						.replace("Month", getContext().getStringProperty("MonthToTest"))
						.replace("Division", getContext().getStringProperty("Division"))
						.replace("Zone", getContext().getStringProperty("Zone"))
						.replace("Area", getContext().getStringProperty("Area")));
		System.out.println("DB Value: " + DBPlndTtl);

		PlndVal = pageDailyTradingStatementGroupPO.RowWiseResult(objWeeklyTradingStatementStoreObjects,
				objWeeklyTradingStatementZoneObjects.Weeks, objWeeklyTradingStatementZoneObjects.MonthlyPlannedCPH,
				objWeeklyTradingStatementZoneObjects.Table);
		PlndTtl = pageDailyTradingStatementGroupPO.WeekToDateUITotal(objWeeklyTradingStatementZoneObjects.MonthTotal,
				objWeeklyTradingStatementZoneObjects.MonthlyPlannedCPHTtl);

		pageMonthlyPlanningPO.CompareValueDecimalFields(extentTest, DBPlndVal, PlndVal);
		pageMonthlyPlanningPO.CompareValueDecimalFields(extentTest, DBPlndTtl, PlndTtl);
	}

	@Test(priority = 20)
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
		pageDailyTradingStatementGroupPO = PageFactory.initElements(this.getDriver(),
				DailyTradingStatementGroupPage.class);
		pageDailyTradingStatementStorePO = PageFactory.initElements(this.getDriver(),
				DailyTradingStatementStorePage.class);
		BaseObj = PageFactory.initElements(this.getDriver(), BasePage.class);
		common = PageFactory.initElements(this.getDriver(), CommonFunctions.class);
		storeprop = PageFactory.initElements(this.getDriver(), StoreProperties.class);
		objWeeklyPlanningStoreObjects = PageFactory.initElements(this.getDriver(), WeeklyPlanningStoreObjects.class);
		objWeeklyTradingStatementStoreObjects = PageFactory.initElements(this.getDriver(),
				WeeklyTradingStatementStoreObjects.class);
		objWeeklyTradingStatementZoneObjects = PageFactory.initElements(this.getDriver(),
				WeeklyTradingStatementZoneObjects.class);
		objDailyTradingStatementGroupObjects = PageFactory.initElements(this.getDriver(),
				DailyTradingStatementGroupObjects.class);
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
			data.loadTestDataXML(System.getProperty("user.dir") + "\\TestData\\WLP\\WeeklyTradingStatementZone.xml");
			customreport.createExtentReport();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

}