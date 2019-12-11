package au.wow.WLPmain.MonthlyTradingStatement;

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

import au.wow.WLPmain.objects.DailyPlanningMainObjects;
import au.wow.WLPmain.objects.DailyTradingStatementGroupObjects;
import au.wow.WLPmain.objects.DailyTradingStatementObjects;
import au.wow.WLPmain.objects.MonthlyPlanningGroupObjects;
import au.wow.WLPmain.objects.MonthlyPlanningStoreObjects;
import au.wow.WLPmain.objects.WeeklyPlanningStoreObjects;
import au.wow.WLPmain.objects.WeeklyTradingStatementBusinessUnitObjects;
import au.wow.WLPmain.objects.WeeklyTradingStatementGroupObjects;
import au.wow.WLPmain.objects.WeeklyTradingStatementStateObjects;
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

public class MonthlyTradingStatementBU extends TestBase {
	protected Logger log = LogManager.getLogger(MonthlyTradingStatementBU.class);
	TestStatus status;
	ExtentReportsScreenshotCode ScreenShotUtil;
	SQLWrapper sql = new SQLWrapper(log);
	WoWLoginPage loginPage;
	CommonRepository CommonFunc;
	WeeklyPlanningStoreObjects objWeeklyPlanningStoreObjects;
	WeeklyTradingStatementStoreObjects objWeeklyTradingStatementStoreObjects;
	WeeklyTradingStatementBusinessUnitObjects objWeeklyTradingStatementBUObjects;
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
			PlndWgPrcntVal, CPHWgTtl, CPHWgVal, VarBdgtWgTtl, VarBdgtWgVal;
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

	public MonthlyTradingStatementBU() {
		super.log = log;
	}

	@Test(priority = 0)
	public void LogintoCLRApplication() throws Exception {
		loginPage = new WoWLoginPage().createPage(getDriver(), getContext(), log, status, data, report, extentTest,
				WoWLoginPage.class);
		pageWeeklyPlanningPO = loginPage.navToStatePage(getContext().getStringProperty("store"));
	}

	// Sales
	@Test(description = "Monthly Trading Statement - BU, SalesColumnValidation", priority = 1)
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
				objWeeklyPlanningStoreObjects.monthlyTrading, "Monthly Trading");
		pageWeeklyPlanningPO.selectPage(extentTest, objWeeklyPlanningStoreObjects.menuBar,
				objWeeklyPlanningStoreObjects.screenToViewMonthlyTrading, "Business Unit");
		//pageWeeklyPlanningPO.verifyPageName(extentTest, objWeeklyPlanningStoreObjects.DataFrame,
			//	objWeeklyPlanningStoreObjects.pageName, getContext().getStringProperty("MTSBU"));

		OptionToView = getContext().getStringProperty("OptionToView");

		// ACTUAL SALES:
		Report_AddStep("testcase", " ACTUAL SALES - WEEK WISE DATA", "", "", "");
		extentTest = report.startTest("Actual Sales");

		String ActualSales = data.getText("ActualSales");
		DBActlVal = sql.CLRexecuteQuery(getContext(),
				ActualSales.replace("LYWk", getContext().getStringProperty("LYWk"))
						.replace("WeekEnd", getContext().getStringProperty("SelectFiscalWeek"))
						.replace("WeekStart", getContext().getStringProperty("WeekStart"))
						.replace("LYWeekStrt", getContext().getStringProperty("LYWeekStrt"))
						.replace("Division", getContext().getStringProperty("Division")));
		System.out.println("DB Value: " + DBActlVal);

		String ActualSalesTotal = data.getText("ActualSalesTotal");
		DBActlTtl = sql.CLRexecuteQuery(getContext(),
				ActualSalesTotal.replace("LYWk", getContext().getStringProperty("LYWk"))
						.replace("WeekEnd", getContext().getStringProperty("SelectFiscalWeek"))
						.replace("WeekStart", getContext().getStringProperty("WeekStart"))
						.replace("LYWeekStrt", getContext().getStringProperty("LYWeekStrt"))
						.replace("Division", getContext().getStringProperty("Division")));
		System.out.println("DB Value: " + DBActlTtl);

		ActlVal = pageDailyTradingStatementGroupPO.RowWiseResult(objWeeklyTradingStatementStoreObjects,
				objWeeklyTradingStatementBUObjects.Departments, objWeeklyTradingStatementBUObjects.ActualSales,
				objWeeklyTradingStatementBUObjects.DataTable);
		ActlTtl = pageDailyTradingStatementGroupPO.WeekToDateUITotal(objWeeklyTradingStatementBUObjects.TotalTitle,
				objWeeklyTradingStatementBUObjects.ActualSalesTtl);

		pageMonthlyPlanningPO.CompareValueDecimalFields(extentTest, DBActlVal, ActlVal);
		pageMonthlyPlanningPO.CompareValueDecimalFields(extentTest, DBActlTtl, ActlTtl);

		// PLANNED SALES:
		Report_AddStep("testcase", " PLANNED SALES - WEEK WISE DATA", "", "", "");
		extentTest = report.startTest("Planned Sales");

		String PlannedSales = data.getText("PlannedSales");
		DBPlndVal = sql.CLRexecuteQuery(getContext(),
				PlannedSales.replace("LYWk", getContext().getStringProperty("LYWk"))
						.replace("WeekEnd", getContext().getStringProperty("SelectFiscalWeek"))
						.replace("WeekStart", getContext().getStringProperty("WeekStart"))
						.replace("LYWeekStrt", getContext().getStringProperty("LYWeekStrt"))
						.replace("Division", getContext().getStringProperty("Division")));
		System.out.println("DB Value: " + DBPlndVal);

		String PlannedSalesTotal = data.getText("PlannedSalesTotal");
		DBPlndTtl = sql.CLRexecuteQuery(getContext(),
				PlannedSalesTotal.replace("LYWk", getContext().getStringProperty("LYWk"))
						.replace("WeekEnd", getContext().getStringProperty("SelectFiscalWeek"))
						.replace("WeekStart", getContext().getStringProperty("WeekStart"))
						.replace("LYWeekStrt", getContext().getStringProperty("LYWeekStrt"))
						.replace("Division", getContext().getStringProperty("Division")));
		System.out.println("DB Value: " + DBPlndTtl);

		PlndVal = pageDailyTradingStatementGroupPO.RowWiseResult(objWeeklyTradingStatementStoreObjects,
				objWeeklyTradingStatementBUObjects.Departments, objWeeklyTradingStatementBUObjects.PlannedSales,
				objWeeklyTradingStatementBUObjects.DataTable);
		PlndTtl = pageDailyTradingStatementGroupPO.WeekToDateUITotal(objWeeklyTradingStatementBUObjects.TotalTitle,
				objWeeklyTradingStatementBUObjects.PlannedSalesTtl);

		pageMonthlyPlanningPO.CompareValues(extentTest, DBPlndVal, PlndVal);
		pageMonthlyPlanningPO.CompareValues(extentTest, DBPlndTtl, PlndTtl);

		// VARIANCE IN PLANNED SALES:
		Report_AddStep("testcase", " VARIANCE IN PLANNED SALES - WEEK WISE DATA", "", "", "");
		extentTest = report.startTest("Variance Sales");

		String VarSales = data.getText("VarSales");
	DBVarPlndVal = sql.CLRexecuteQuery(getContext(),
				VarSales.replace("LYWk", getContext().getStringProperty("LYWk"))
						.replace("WeekEnd", getContext().getStringProperty("SelectFiscalWeek"))
						.replace("WeekStart", getContext().getStringProperty("WeekStart"))
						.replace("LYWeekStrt", getContext().getStringProperty("LYWeekStrt"))
						.replace("Division", getContext().getStringProperty("Division")));		System.out.println("DB Value: " + DBVarPlndVal);

		String VarSalesTotal = data.getText("VarSalesTotal");
		DBVarPlndTtl = sql.CLRexecuteQuery(getContext(),
				VarSalesTotal.replace("LYWk", getContext().getStringProperty("LYWk"))
						.replace("WeekEnd", getContext().getStringProperty("SelectFiscalWeek"))
						.replace("WeekStart", getContext().getStringProperty("WeekStart"))
						.replace("LYWeekStrt", getContext().getStringProperty("LYWeekStrt"))
						.replace("Division", getContext().getStringProperty("Division")));
		System.out.println("DB Value: " + DBVarPlndTtl);

		VarPlndVal = pageDailyTradingStatementGroupPO.RowWiseResult(objWeeklyTradingStatementStoreObjects,
				objWeeklyTradingStatementBUObjects.Departments, objWeeklyTradingStatementBUObjects.PlannedSalesGrwth,
				objWeeklyTradingStatementBUObjects.DataTable);
		VarPlndTtl = pageDailyTradingStatementGroupPO.WeekToDateUITotal(objWeeklyTradingStatementBUObjects.TotalTitle,
				objWeeklyTradingStatementBUObjects.PlannedSalesGrwthTtl);

		pageMonthlyPlanningPO.CompareValues(extentTest, DBVarPlndVal, VarPlndVal);
		pageMonthlyPlanningPO.CompareValues(extentTest, DBVarPlndTtl, VarPlndTtl);

		// LAST YEAR SALES:
		Report_AddStep("testcase", " LAST YEAR SALES - WEEK WISE DATA", "", "", "");
		extentTest = report.startTest("LY Sales");

		String LYSales = data.getText("LYSales");
		DBLYSlsVal = sql.CLRexecuteQuery(getContext(),
				LYSales.replace("LYWk", getContext().getStringProperty("LYWk"))
						.replace("WeekEnd", getContext().getStringProperty("SelectFiscalWeek"))
						.replace("WeekStart", getContext().getStringProperty("WeekStart"))
						.replace("LYWeekStrt", getContext().getStringProperty("LYWeekStrt"))
						.replace("Division", getContext().getStringProperty("Division")));
		System.out.println("DB Value: " + DBLYSlsVal);

		String LYSalesTotal = data.getText("LYSalesTotal");
		DBLYSlsTtl = sql.CLRexecuteQuery(getContext(),
				LYSalesTotal.replace("LYWk", getContext().getStringProperty("LYWk"))
						.replace("WeekEnd", getContext().getStringProperty("SelectFiscalWeek"))
						.replace("WeekStart", getContext().getStringProperty("WeekStart"))
						.replace("LYWeekStrt", getContext().getStringProperty("LYWeekStrt"))
						.replace("Division", getContext().getStringProperty("Division")));
		System.out.println("DB Value: " + DBLYSlsTtl);

		LYSlsVal = pageDailyTradingStatementGroupPO.RowWiseResult(objWeeklyTradingStatementStoreObjects,
				objWeeklyTradingStatementBUObjects.Departments, objWeeklyTradingStatementBUObjects.LYSales,
				objWeeklyTradingStatementBUObjects.DataTable);
		LYSlsTtl = pageDailyTradingStatementGroupPO.WeekToDateUITotal(objWeeklyTradingStatementBUObjects.TotalTitle,
				objWeeklyTradingStatementBUObjects.LYSalesTtl);

		pageMonthlyPlanningPO.CompareValues(extentTest, DBLYSlsVal, LYSlsVal);
		pageMonthlyPlanningPO.CompareValues(extentTest, DBLYSlsTtl, LYSlsTtl);

		// VARIANCE IN LAST YEAR SALES:
		Report_AddStep("testcase", " VARIANCE IN LAST YEAR SALES - WEEK WISE DATA", "", "", "");
		extentTest = report.startTest("Variance LY Sales");

		String LYSalesPct = data.getText("LYSalesPct");
		DBVarLYSlsVal = sql.CLRexecuteQuery(getContext(),
				LYSalesPct.replace("LYWk", getContext().getStringProperty("LYWk"))
						.replace("WeekEnd", getContext().getStringProperty("SelectFiscalWeek"))
						.replace("WeekStart", getContext().getStringProperty("WeekStart"))
						.replace("LYWeekStrt", getContext().getStringProperty("LYWeekStrt"))
						.replace("Division", getContext().getStringProperty("Division")));
		System.out.println("DB Value: " + DBVarLYSlsVal);

		String LYSalesPctTotal = data.getText("LYSalesPctTotal");
		DBVarLYSlsTtl = sql.CLRexecuteQuery(getContext(),
				LYSalesPctTotal.replace("LYWk", getContext().getStringProperty("LYWk"))
						.replace("WeekEnd", getContext().getStringProperty("SelectFiscalWeek"))
						.replace("WeekStart", getContext().getStringProperty("WeekStart"))
						.replace("LYWeekStrt", getContext().getStringProperty("LYWeekStrt"))
						.replace("Division", getContext().getStringProperty("Division")));
		System.out.println("DB Value: " + DBVarLYSlsTtl);

		VarLYSlsVal = pageDailyTradingStatementGroupPO.RowWiseResult(objWeeklyTradingStatementStoreObjects,
				objWeeklyTradingStatementBUObjects.Departments, objWeeklyTradingStatementBUObjects.LYSalesGrwth,
				objWeeklyTradingStatementBUObjects.DataTable);
		VarLYSlsTtl = pageDailyTradingStatementGroupPO.WeekToDateUITotal(objWeeklyTradingStatementBUObjects.TotalTitle,
				objWeeklyTradingStatementBUObjects.LYSalesGrwthTtl);

		pageMonthlyPlanningPO.CompareValueDecimalFields(extentTest, DBVarLYSlsVal, VarLYSlsVal);
		pageMonthlyPlanningPO.CompareValueDecimalFields(extentTest, DBVarLYSlsTtl, VarLYSlsTtl);

		// BUDGET SALES:
		if (OptionToView.contains("Budget")) {
			Report_AddStep("testcase", "BUDGET SALES - WEEK WISE DATA", "", "", "");
			extentTest = report.startTest("Budget Sales");

			String BudgetSales = data.getText("BudgetSales");
			DBBdgtVal = sql.CLRexecuteQuery(getContext(),
					BudgetSales.replace("LYWk", getContext().getStringProperty("LYWk"))
							.replace("WeekEnd", getContext().getStringProperty("SelectFiscalWeek"))
							.replace("WeekStart", getContext().getStringProperty("WeekStart"))
							.replace("LYWeekStrt", getContext().getStringProperty("LYWeekStrt"))
							.replace("Division", getContext().getStringProperty("Division")));
			System.out.println("DB Value: " + DBBdgtVal);

			String BudgetSalesTotal = data.getText("BudgetSalesTotal");
			DBBdgtTtl = sql.CLRexecuteQuery(getContext(),
					BudgetSalesTotal.replace("LYWk", getContext().getStringProperty("LYWk"))
							.replace("WeekEnd", getContext().getStringProperty("SelectFiscalWeek"))
							.replace("WeekStart", getContext().getStringProperty("WeekStart"))
							.replace("LYWeekStrt", getContext().getStringProperty("LYWeekStrt"))
							.replace("Division", getContext().getStringProperty("Division")));
			System.out.println("DB Value: " + DBBdgtTtl);

			BdgtVal = pageDailyTradingStatementGroupPO.RowWiseResult(objWeeklyTradingStatementStoreObjects,
					objWeeklyTradingStatementBUObjects.Departments, objWeeklyTradingStatementBUObjects.BudgetSales,
					objWeeklyTradingStatementBUObjects.DataTable);
			BdgtTtl = pageDailyTradingStatementGroupPO.WeekToDateUITotal(objWeeklyTradingStatementBUObjects.TotalTitle,
					objWeeklyTradingStatementBUObjects.BudgetSalesTtl);

			pageMonthlyPlanningPO.CompareValueDecimalFields(extentTest, DBBdgtVal, BdgtVal);
			pageMonthlyPlanningPO.CompareValueDecimalFields(extentTest, DBBdgtTtl, BdgtTtl);
		}
		if (OptionToView.contains("Forecast")) {
			Report_AddStep("testcase", "FORECAST SALES - WEEK WISE DATA", "", "", "");
			extentTest = report.startTest("Forecast Sales");

			String ForecastSales = data.getText("ForecastSales");
			DBBdgtVal = sql.CLRexecuteQuery(getContext(),
					ForecastSales.replace("LYWk", getContext().getStringProperty("LYWk"))
							.replace("WeekEnd", getContext().getStringProperty("SelectFiscalWeek"))
							.replace("WeekStart", getContext().getStringProperty("WeekStart"))
							.replace("LYWeekStrt", getContext().getStringProperty("LYWeekStrt"))
							.replace("Division", getContext().getStringProperty("Division")));
			System.out.println("DB Value: " + DBBdgtVal);

			String ForecastSalesTotal = data.getText("ForecastSalesTotal");
			DBBdgtTtl = sql.CLRexecuteQuery(getContext(),
					ForecastSalesTotal.replace("LYWk", getContext().getStringProperty("LYWk"))
							.replace("WeekEnd", getContext().getStringProperty("SelectFiscalWeek"))
							.replace("WeekStart", getContext().getStringProperty("WeekStart"))
							.replace("LYWeekStrt", getContext().getStringProperty("LYWeekStrt"))
							.replace("Division", getContext().getStringProperty("Division")));
			System.out.println("DB Value: " + DBBdgtTtl);

			BdgtVal = pageDailyTradingStatementGroupPO.RowWiseResult(objWeeklyTradingStatementStoreObjects,
					objWeeklyTradingStatementBUObjects.Departments, objWeeklyTradingStatementBUObjects.BudgetSales,
					objWeeklyTradingStatementBUObjects.DataTable);
			BdgtTtl = pageDailyTradingStatementGroupPO.WeekToDateUITotal(objWeeklyTradingStatementBUObjects.TotalTitle,
					objWeeklyTradingStatementBUObjects.BudgetSalesTtl);

			pageMonthlyPlanningPO.CompareValueDecimalFields(extentTest, DBBdgtVal, BdgtVal);
			pageMonthlyPlanningPO.CompareValueDecimalFields(extentTest, DBBdgtTtl, BdgtTtl);
		}

		// VARIANCE IN BUDGET SALES:
		if (OptionToView.contains("Budget")) {
			Report_AddStep("testcase", "VARIANCE IN BUDGET SALES - WEEK WISE DATA", "", "", "");
			extentTest = report.startTest("Variance Budget Sales");

			String VarBudgetSales = data.getText("VarBudgetSales");
			DBVarBdgtVal = sql.CLRexecuteQuery(getContext(),
					VarBudgetSales.replace("LYWk", getContext().getStringProperty("LYWk"))
							.replace("WeekEnd", getContext().getStringProperty("SelectFiscalWeek"))
							.replace("WeekStart", getContext().getStringProperty("WeekStart"))
							.replace("LYWeekStrt", getContext().getStringProperty("LYWeekStrt"))
							.replace("Division", getContext().getStringProperty("Division")));
			System.out.println("DB Value: " + DBVarBdgtVal);

			String VarBudgetSalesTotal = data.getText("VarBudgetSalesTotal");
			DBVarBdgtTtl = sql.CLRexecuteQuery(getContext(),
					VarBudgetSalesTotal.replace("LYWk", getContext().getStringProperty("LYWk"))
							.replace("WeekEnd", getContext().getStringProperty("SelectFiscalWeek"))
							.replace("WeekStart", getContext().getStringProperty("WeekStart"))
							.replace("LYWeekStrt", getContext().getStringProperty("LYWeekStrt"))
							.replace("Division", getContext().getStringProperty("Division")));
			System.out.println("DB Value: " + DBVarBdgtTtl);

			VarBdgtVal = pageDailyTradingStatementGroupPO.RowWiseResult(objWeeklyTradingStatementStoreObjects,
					objWeeklyTradingStatementBUObjects.Departments, objWeeklyTradingStatementBUObjects.BudgetSalesGrwth,
					objWeeklyTradingStatementBUObjects.DataTable);
			VarBdgtTtl = pageDailyTradingStatementGroupPO.WeekToDateUITotal(
					objWeeklyTradingStatementBUObjects.TotalTitle,
					objWeeklyTradingStatementBUObjects.BudgetSalesGrwthTtl);

			pageMonthlyPlanningPO.CompareValues(extentTest, DBVarBdgtVal, VarBdgtVal);
			pageMonthlyPlanningPO.CompareValues(extentTest, DBVarBdgtTtl, VarBdgtTtl);
		}
		if (OptionToView.contains("Forecast")) {
			Report_AddStep("testcase", "VARIANCE IN FORECAST SALES - WEEK WISE DATA", "", "", "");
			extentTest = report.startTest("Variance Forecast Sales");

			String VarFcstSales = data.getText("VarFcstSales");
			DBVarBdgtVal = sql.CLRexecuteQuery(getContext(),
					VarFcstSales.replace("LYWk", getContext().getStringProperty("LYWk"))
							.replace("WeekEnd", getContext().getStringProperty("SelectFiscalWeek"))
							.replace("WeekStart", getContext().getStringProperty("WeekStart"))
							.replace("LYWeekStrt", getContext().getStringProperty("LYWeekStrt"))
							.replace("Division", getContext().getStringProperty("Division")));
			System.out.println("DB Value: " + DBVarBdgtVal);

			String VarFcstSalesTotal = data.getText("VarFcstSalesTotal");
			DBVarBdgtTtl = sql.CLRexecuteQuery(getContext(),
					VarFcstSalesTotal.replace("LYWk", getContext().getStringProperty("LYWk"))
							.replace("WeekEnd", getContext().getStringProperty("SelectFiscalWeek"))
							.replace("WeekStart", getContext().getStringProperty("WeekStart"))
							.replace("LYWeekStrt", getContext().getStringProperty("LYWeekStrt"))
							.replace("Division", getContext().getStringProperty("Division")));
			System.out.println("DB Value: " + DBVarBdgtTtl);

			VarBdgtVal = pageDailyTradingStatementGroupPO.RowWiseResult(objWeeklyTradingStatementStoreObjects,
					objWeeklyTradingStatementBUObjects.Departments, objWeeklyTradingStatementBUObjects.BudgetSalesGrwth,
					objWeeklyTradingStatementBUObjects.DataTable);
			VarBdgtTtl = pageDailyTradingStatementGroupPO.WeekToDateUITotal(
					objWeeklyTradingStatementBUObjects.TotalTitle,
					objWeeklyTradingStatementBUObjects.BudgetSalesGrwthTtl);

			pageMonthlyPlanningPO.CompareValueDecimalFields(extentTest, DBVarBdgtVal, VarBdgtVal);
			pageMonthlyPlanningPO.CompareValueDecimalFields(extentTest, DBVarBdgtTtl, VarBdgtTtl);
		}

		// AVERAGE BASKET SALES:
		Report_AddStep("testcase", "AVERAGE BASKET SALES - WEEK WISE DATA", "", "", "");
		extentTest = report.startTest("Average Basket Sales");

		String AvgBasketSales = data.getText("AvgBasketSales");
		DBAvgBsktVal = sql.CLRexecuteQuery(getContext(),
				AvgBasketSales.replace("LYWk", getContext().getStringProperty("LYWk"))
						.replace("WeekEnd", getContext().getStringProperty("SelectFiscalWeek"))
						.replace("WeekStart", getContext().getStringProperty("WeekStart"))
						.replace("LYWeekStrt", getContext().getStringProperty("LYWeekStrt"))
						.replace("Division", getContext().getStringProperty("Division")));
		System.out.println("DB Value: " + DBAvgBsktVal);

		String AvgBasketSalesTotal = data.getText("AvgBasketSalesTotal");
		DBAvgBsktTtl = sql.CLRexecuteQuery(getContext(),
				AvgBasketSalesTotal.replace("LYWk", getContext().getStringProperty("LYWk"))
						.replace("WeekEnd", getContext().getStringProperty("SelectFiscalWeek"))
						.replace("WeekStart", getContext().getStringProperty("WeekStart"))
						.replace("LYWeekStrt", getContext().getStringProperty("LYWeekStrt"))
						.replace("Division", getContext().getStringProperty("Division")));
		System.out.println("DB Value: " + DBAvgBsktTtl);

		AvgBsktVal = pageDailyTradingStatementGroupPO.RowWiseResult(objWeeklyTradingStatementStoreObjects,
				objWeeklyTradingStatementBUObjects.Departments, objWeeklyTradingStatementBUObjects.AvgBasketSales,
				objWeeklyTradingStatementBUObjects.DataTable);
		AvgBsktTtl = pageDailyTradingStatementGroupPO.WeekToDateUITotal(objWeeklyTradingStatementBUObjects.TotalTitle,
				objWeeklyTradingStatementBUObjects.AvgBasketSalesTtl);

		pageMonthlyPlanningPO.CompareValueDecimalFields(extentTest, DBAvgBsktVal, AvgBsktVal);
		pageMonthlyPlanningPO.CompareValueDecimalFields(extentTest, DBAvgBsktTtl, AvgBsktTtl);

		// MIX SALES:
		Report_AddStep("testcase", "MIX SALES - WEEK WISE DATA", "", "", "");
		extentTest = report.startTest("Mix Sales");

		String WkyMixSales = data.getText("WkyMixSales");
		DBMixVal = sql.CLRexecuteQuery(getContext(),
				WkyMixSales.replace("LYWk", getContext().getStringProperty("LYWk"))
						.replace("WeekEnd", getContext().getStringProperty("SelectFiscalWeek"))
						.replace("WeekStart", getContext().getStringProperty("WeekStart"))
						.replace("LYWeekStrt", getContext().getStringProperty("LYWeekStrt"))
						.replace("Division", getContext().getStringProperty("Division")));
		System.out.println("DB Value: " + DBMixVal);

		String WkyMixSalesTotal = data.getText("WkyMixSalesTotal");
		DBMixTtl = sql.CLRexecuteQuery(getContext(),
				WkyMixSalesTotal.replace("LYWk", getContext().getStringProperty("LYWk"))
						.replace("WeekEnd", getContext().getStringProperty("SelectFiscalWeek"))
						.replace("WeekStart", getContext().getStringProperty("WeekStart"))
						.replace("LYWeekStrt", getContext().getStringProperty("LYWeekStrt"))
						.replace("Division", getContext().getStringProperty("Division")));
		System.out.println("DB Value: " + DBMixTtl);

		MixVal = pageDailyTradingStatementGroupPO.RowWiseResult(objWeeklyTradingStatementStoreObjects,
				objWeeklyTradingStatementBUObjects.Departments, objWeeklyTradingStatementBUObjects.Mix,
				objWeeklyTradingStatementBUObjects.DataTable);
		MixTtl = pageDailyTradingStatementGroupPO.WeekToDateUITotal(objWeeklyTradingStatementBUObjects.TotalTitle,
				objWeeklyTradingStatementBUObjects.MixTtl);

		pageMonthlyPlanningPO.CompareValueDecimalFields(extentTest, DBMixVal, MixVal);
		pageMonthlyPlanningPO.CompareValueDecimalFields(extentTest, DBMixTtl, MixTtl);
	}

	// Items and Item Price
	@Test(description = "Monthly Trading Statement - BU, Planned Item", priority = 2)
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
				PlannedItems.replace("LYWk", getContext().getStringProperty("LYWk"))
						.replace("WeekEnd", getContext().getStringProperty("SelectFiscalWeek"))
						.replace("WeekStart", getContext().getStringProperty("WeekStart"))
						.replace("LYWeekStrt", getContext().getStringProperty("LYWeekStrt"))
						.replace("Division", getContext().getStringProperty("Division")));
		System.out.println("DB Value: " + DBPlndItmVal);

		String PlannedItemsTotal = data.getText("PlannedItemsTotal");
		DBPlndItmTtl = sql.CLRexecuteQuery(getContext(),
				PlannedItemsTotal.replace("LYWk", getContext().getStringProperty("LYWk"))
						.replace("WeekEnd", getContext().getStringProperty("SelectFiscalWeek"))
						.replace("WeekStart", getContext().getStringProperty("WeekStart"))
						.replace("LYWeekStrt", getContext().getStringProperty("LYWeekStrt"))
						.replace("Division", getContext().getStringProperty("Division")));
		System.out.println("DB Value: " + DBPlndItmTtl);

		PlndItmVal = pageDailyTradingStatementGroupPO.RowWiseResult(objWeeklyTradingStatementStoreObjects,
				objWeeklyTradingStatementBUObjects.Departments, objWeeklyTradingStatementBUObjects.PlannedItem,
				objWeeklyTradingStatementBUObjects.DataTable);
		PlndItmTtl = pageDailyTradingStatementGroupPO.WeekToDateUITotal(objWeeklyTradingStatementBUObjects.TotalTitle,
				objWeeklyTradingStatementBUObjects.PlannedItemTtl);

		pageMonthlyPlanningPO.CompareValueDecimalFields(extentTest, DBPlndItmVal, PlndItmVal);
		pageMonthlyPlanningPO.CompareValueDecimalFields(extentTest, DBPlndItmTtl, PlndItmTtl);

		// ACTUAL ITEMS:
		Report_AddStep("testcase", "ACTUAL ITEMS - WEEK WISE DATA", "", "", "");
		extentTest = report.startTest("Actual Items");

		String ActualItems = data.getText("ActualItems");
		DBActlItmVal = sql.CLRexecuteQuery(getContext(),
				ActualItems.replace("LYWk", getContext().getStringProperty("LYWk"))
						.replace("WeekEnd", getContext().getStringProperty("SelectFiscalWeek"))
						.replace("WeekStart", getContext().getStringProperty("WeekStart"))
						.replace("LYWeekStrt", getContext().getStringProperty("LYWeekStrt"))
						.replace("Division", getContext().getStringProperty("Division")));
		System.out.println("DB Value: " + DBActlItmVal);

		String ActualItemsTotal = data.getText("ActualItemsTotal");
		DBActlItmTtl = sql.CLRexecuteQuery(getContext(),
				ActualItemsTotal.replace("LYWk", getContext().getStringProperty("LYWk"))
						.replace("WeekEnd", getContext().getStringProperty("SelectFiscalWeek"))
						.replace("WeekStart", getContext().getStringProperty("WeekStart"))
						.replace("LYWeekStrt", getContext().getStringProperty("LYWeekStrt"))
						.replace("Division", getContext().getStringProperty("Division")));
		System.out.println("DB Value: " + DBActlItmTtl);

		ActlItmVal = pageDailyTradingStatementGroupPO.RowWiseResult(objWeeklyTradingStatementStoreObjects,
				objWeeklyTradingStatementBUObjects.Departments, objWeeklyTradingStatementBUObjects.ActualItem,
				objWeeklyTradingStatementBUObjects.DataTable);
		ActlItmTtl = pageDailyTradingStatementGroupPO.WeekToDateUITotal(objWeeklyTradingStatementBUObjects.TotalTitle,
				objWeeklyTradingStatementBUObjects.ActualItemTtl);

		pageMonthlyPlanningPO.CompareValueDecimalFields(extentTest, DBActlItmVal, ActlItmVal);
		pageMonthlyPlanningPO.CompareValueDecimalFields(extentTest, DBActlItmTtl, ActlItmTtl);

		// PLANNED ITEM PRICE:
		Report_AddStep("testcase", "PLANNED ITEM PRICE - WEEK WISE DATA", "", "", "");
		extentTest = report.startTest("Planned Item Price");

		String PlannedItemPrice = data.getText("PlannedItemPrice");
		DBPlndItmPriceVal = sql.CLRexecuteQuery(getContext(),
				PlannedItemPrice.replace("LYWk", getContext().getStringProperty("LYWk"))
						.replace("WeekEnd", getContext().getStringProperty("SelectFiscalWeek"))
						.replace("WeekStart", getContext().getStringProperty("WeekStart"))
						.replace("LYWeekStrt", getContext().getStringProperty("LYWeekStrt"))
						.replace("Division", getContext().getStringProperty("Division")));
		System.out.println("DB Value: " + DBPlndItmPriceVal);

		String PlannedItemPriceTotal = data.getText("PlannedItemPriceTotal");
		DBPlndItmPriceTtl = sql.CLRexecuteQuery(getContext(),
				PlannedItemPriceTotal.replace("LYWk", getContext().getStringProperty("LYWk"))
						.replace("WeekEnd", getContext().getStringProperty("SelectFiscalWeek"))
						.replace("WeekStart", getContext().getStringProperty("WeekStart"))
						.replace("LYWeekStrt", getContext().getStringProperty("LYWeekStrt"))
						.replace("Division", getContext().getStringProperty("Division")));
		System.out.println("DB Value: " + DBPlndItmPriceTtl);

		PlndItmPriceVal = pageDailyTradingStatementGroupPO.RowWiseResult(objWeeklyTradingStatementStoreObjects,
				objWeeklyTradingStatementBUObjects.Departments, objWeeklyTradingStatementBUObjects.PlannedItemPrice,
				objWeeklyTradingStatementBUObjects.DataTable);
		PlndItmPriceTtl = pageDailyTradingStatementGroupPO.WeekToDateUITotal(
				objWeeklyTradingStatementBUObjects.TotalTitle, objWeeklyTradingStatementBUObjects.PlannedItemPriceTtl);

		pageMonthlyPlanningPO.CompareValueDecimalFields(extentTest, DBPlndItmPriceVal, PlndItmPriceVal);
		pageMonthlyPlanningPO.CompareValueDecimalFields(extentTest, DBPlndItmPriceTtl, PlndItmPriceTtl);

		// ACTUAL ITEM PRICE:
		Report_AddStep("testcase", "ACTUAL ITEM PRICE - WEEK WISE DATA", "", "", "");
		extentTest = report.startTest("Actual Item Price");

		String ActualItemPrice = data.getText("ActualItemPrice");
		DBActlItmPriceVal = sql.CLRexecuteQuery(getContext(),
				ActualItemPrice.replace("LYWk", getContext().getStringProperty("LYWk"))
						.replace("WeekEnd", getContext().getStringProperty("SelectFiscalWeek"))
						.replace("WeekStart", getContext().getStringProperty("WeekStart"))
						.replace("LYWeekStrt", getContext().getStringProperty("LYWeekStrt"))
						.replace("Division", getContext().getStringProperty("Division")));
		System.out.println("DB Value: " + DBActlItmPriceVal);

		String ActualItemPriceTotal = data.getText("ActualItemPriceTotal");
		DBActlItmPriceTtl = sql.CLRexecuteQuery(getContext(),
				ActualItemPriceTotal.replace("LYWk", getContext().getStringProperty("LYWk"))
						.replace("WeekEnd", getContext().getStringProperty("SelectFiscalWeek"))
						.replace("WeekStart", getContext().getStringProperty("WeekStart"))
						.replace("LYWeekStrt", getContext().getStringProperty("LYWeekStrt"))
						.replace("Division", getContext().getStringProperty("Division")));
		System.out.println("DB Value: " + DBActlItmPriceTtl);

		ActlItmPriceVal = pageDailyTradingStatementGroupPO.RowWiseResult(objWeeklyTradingStatementStoreObjects,
				objWeeklyTradingStatementBUObjects.Departments, objWeeklyTradingStatementBUObjects.ActualItemPrice,
				objWeeklyTradingStatementBUObjects.DataTable);
		ActlItmPriceTtl = pageDailyTradingStatementGroupPO.WeekToDateUITotal(
				objWeeklyTradingStatementBUObjects.TotalTitle, objWeeklyTradingStatementBUObjects.ActualItemPriceTtl);

		pageMonthlyPlanningPO.CompareValueDecimalFields(extentTest, DBActlItmPriceVal, ActlItmPriceVal);
		pageMonthlyPlanningPO.CompareValueDecimalFields(extentTest, DBActlItmPriceTtl, ActlItmPriceTtl);
	}

	// Customers
	@Test(description = "Monthly Trading Statement - BU, This Year Customer", priority = 3)
	public void CustomersColumnValidation() throws Exception {
		pageWeeklyPlanningPO = new WeeklyPlanningStorePage().createPage(getDriver(), getContext(), log, status, data,
				getReport(), getReportLogger(), WeeklyPlanningStorePage.class);
		pageDailyTradingStatementGroupPO = new MonthlyPlanningGroupPage().createPage(getDriver(), getContext(), log,
				status, data, getReport(), getReportLogger(), DailyTradingStatementGroupPage.class);
		TestStatus status = getTestStatus();
		System.out.println("Entering into TestCase - Customer View");

		// THIS YEAR - CUSTOMER:
		Report_AddStep("testcase", "CUSTOMER THIS YEAR VALUE - WEEK WISE DATA", "", "", "");
		extentTest = report.startTest("This Yr - Customers");

		String ThisYrActlCustomers = data.getText("ThisYrActlCustomers");
		DBThisYrCustVal = sql.CLRexecuteQuery(getContext(),
				ThisYrActlCustomers.replace("LYWk", getContext().getStringProperty("LYWk"))
						.replace("WeekEnd", getContext().getStringProperty("SelectFiscalWeek"))
						.replace("WeekStart", getContext().getStringProperty("WeekStart"))
						.replace("LYWeekStrt", getContext().getStringProperty("LYWeekStrt"))
						.replace("Division", getContext().getStringProperty("Division")));
		System.out.println("DB Value: " + DBThisYrCustVal);

		String ThisYrActlCustomersTotal = data.getText("ThisYrActlCustomersTotal");
		DBThisYrCustTtl = sql.CLRexecuteQuery(getContext(),
				ThisYrActlCustomersTotal.replace("LYWk", getContext().getStringProperty("LYWk"))
						.replace("WeekEnd", getContext().getStringProperty("SelectFiscalWeek"))
						.replace("WeekStart", getContext().getStringProperty("WeekStart"))
						.replace("LYWeekStrt", getContext().getStringProperty("LYWeekStrt"))
						.replace("Division", getContext().getStringProperty("Division")));
		System.out.println("DB Value: " + DBThisYrCustTtl);

		ThisYrCustVal = pageDailyTradingStatementGroupPO.RowWiseResult(objWeeklyTradingStatementStoreObjects,
				objWeeklyTradingStatementBUObjects.Departments, objWeeklyTradingStatementBUObjects.ThisYrCustomer,
				objWeeklyTradingStatementBUObjects.DataTable);
		ThisYrCustTtl = pageDailyTradingStatementGroupPO.WeekToDateUITotal(
				objWeeklyTradingStatementBUObjects.TotalTitle, objWeeklyTradingStatementBUObjects.ThisYrCustomerTtl);

		pageMonthlyPlanningPO.CompareValueDecimalFields(extentTest, DBThisYrCustVal, ThisYrCustVal);
		pageMonthlyPlanningPO.CompareValueDecimalFields(extentTest, DBThisYrCustTtl, ThisYrCustTtl);

		// % LAST YEAR CUSTOMER:
		Report_AddStep("testcase", "PERCENTAGE LAST YEAR CUSTOMER - WEEK WISE DATA", "", "", "");
		extentTest = report.startTest("LY Yr - Customers");

		String PctLastYrCustomers = data.getText("PctLastYrCustomers");
		DBLstYrCustVal = sql.CLRexecuteQuery(getContext(),
				PctLastYrCustomers.replace("LYWk", getContext().getStringProperty("LYWk"))
						.replace("WeekEnd", getContext().getStringProperty("SelectFiscalWeek"))
						.replace("WeekStart", getContext().getStringProperty("WeekStart"))
						.replace("LYWeekStrt", getContext().getStringProperty("LYWeekStrt"))
						.replace("Division", getContext().getStringProperty("Division")));
		System.out.println("DB Value: " + DBLstYrCustVal);

		String PctLastYrCustomersTotal = data.getText("PctLastYrCustomersTotal");
		DBLstYrCustTtl = sql.CLRexecuteQuery(getContext(),
				PctLastYrCustomersTotal.replace("LYWk", getContext().getStringProperty("LYWk"))
						.replace("WeekEnd", getContext().getStringProperty("SelectFiscalWeek"))
						.replace("WeekStart", getContext().getStringProperty("WeekStart"))
						.replace("LYWeekStrt", getContext().getStringProperty("LYWeekStrt"))
						.replace("Division", getContext().getStringProperty("Division")));
		System.out.println("DB Value: " + DBLstYrCustTtl);

		LstYrCustVal = pageDailyTradingStatementGroupPO.RowWiseResult(objWeeklyTradingStatementStoreObjects,
				objWeeklyTradingStatementBUObjects.Departments,
				objWeeklyTradingStatementBUObjects.PercentageLastCustomer,
				objWeeklyTradingStatementBUObjects.DataTable);
		LstYrCustTtl = pageDailyTradingStatementGroupPO.WeekToDateUITotal(objWeeklyTradingStatementBUObjects.TotalTitle,
				objWeeklyTradingStatementBUObjects.PercentageLastCustomerTtl);

		pageMonthlyPlanningPO.CompareValueDecimalFields(extentTest, DBLstYrCustVal, LstYrCustVal);
		pageMonthlyPlanningPO.CompareValueDecimalFields(extentTest, DBLstYrCustTtl, LstYrCustTtl);

		// DATA PENETRATION CUSTOMER:
		Report_AddStep("testcase", "DATA PENETRATION CUSTOMER - WEEK WISE DATA", "", "", "");
		extentTest = report.startTest("Data Penetration - Customers");

		String DataPenetrationCustomers = data.getText("DataPenetrationCustomers");
		DBDataPenVal = sql.CLRexecuteQuery(getContext(),
				DataPenetrationCustomers.replace("LYWk", getContext().getStringProperty("LYWk"))
						.replace("WeekEnd", getContext().getStringProperty("SelectFiscalWeek"))
						.replace("WeekStart", getContext().getStringProperty("WeekStart"))
						.replace("LYWeekStrt", getContext().getStringProperty("LYWeekStrt"))
						.replace("Division", getContext().getStringProperty("Division")));
		System.out.println("DB Value: " + DBDataPenVal);

		String DataPenetrationCustomersTotal = data.getText("DataPenetrationCustomersTotal");
		DBDataPenTtl = sql.CLRexecuteQuery(getContext(),
				DataPenetrationCustomersTotal.replace("LYWk", getContext().getStringProperty("LYWk"))
						.replace("WeekEnd", getContext().getStringProperty("SelectFiscalWeek"))
						.replace("WeekStart", getContext().getStringProperty("WeekStart"))
						.replace("LYWeekStrt", getContext().getStringProperty("LYWeekStrt"))
						.replace("Division", getContext().getStringProperty("Division")));
		System.out.println("DB Value: " + DBDataPenTtl);

		DataPenVal = pageDailyTradingStatementGroupPO.RowWiseResult(objWeeklyTradingStatementStoreObjects,
				objWeeklyTradingStatementBUObjects.Departments,
				objWeeklyTradingStatementBUObjects.DeptPenetrationCustomer,
				objWeeklyTradingStatementBUObjects.DataTable);
		DataPenTtl = pageDailyTradingStatementGroupPO.WeekToDateUITotal(objWeeklyTradingStatementBUObjects.TotalTitle,
				objWeeklyTradingStatementBUObjects.DeptPenetrationCustomerTtl);

		pageMonthlyPlanningPO.CompareValueDecimalFields(extentTest, DBDataPenVal, DataPenVal);
		pageMonthlyPlanningPO.CompareValueDecimalFields(extentTest, DBDataPenTtl, DataPenTtl);
	}

	@Test(description = "Monthly Trading Statement - BU, Growth in SMS Hours", priority = 4)
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
				ActualHours.replace("LYWk", getContext().getStringProperty("LYWk"))
						.replace("WeekEnd", getContext().getStringProperty("SelectFiscalWeek"))
						.replace("WeekStart", getContext().getStringProperty("WeekStart"))
						.replace("LYWeekStrt", getContext().getStringProperty("LYWeekStrt"))
						.replace("Division", getContext().getStringProperty("Division")));
		System.out.println("DB Value: " + DBActlHrsVal);

		String ActualHoursTotal = data.getText("ActualHoursTotal");
		DBActlHrsTtl = sql.CLRexecuteQuery(getContext(),
				ActualHoursTotal.replace("LYWk", getContext().getStringProperty("LYWk"))
						.replace("WeekEnd", getContext().getStringProperty("SelectFiscalWeek"))
						.replace("WeekStart", getContext().getStringProperty("WeekStart"))
						.replace("LYWeekStrt", getContext().getStringProperty("LYWeekStrt"))
						.replace("Division", getContext().getStringProperty("Division")));
		System.out.println("DB Value: " + DBActlHrsTtl);

		ActlHrsVal = pageDailyTradingStatementGroupPO.RowWiseResult(objWeeklyTradingStatementStoreObjects,
				objWeeklyTradingStatementBUObjects.Departments, objWeeklyTradingStatementBUObjects.ActualHours,
				objWeeklyTradingStatementBUObjects.DataTable);
		ActlHrsTtl = pageDailyTradingStatementGroupPO.WeekToDateUITotal(objWeeklyTradingStatementBUObjects.TotalTitle,
				objWeeklyTradingStatementBUObjects.ActualHoursTtl);

		pageMonthlyPlanningPO.CompareValueDecimalFields(extentTest, DBActlHrsVal, ActlHrsVal);
		pageMonthlyPlanningPO.CompareValueDecimalFields(extentTest, DBActlHrsTtl, ActlHrsTtl);

		// PLANNED HOURS:
		Report_AddStep("testcase", "PLANNED HOURS: - WEEK WISE DATA", "", "", "");
		extentTest = report.startTest("Planned Hours");

		String PlannedHours = data.getText("PlannedHours");
		DBPlndHrsVal = sql.CLRexecuteQuery(getContext(),
				PlannedHours.replace("LYWk", getContext().getStringProperty("LYWk"))
						.replace("WeekEnd", getContext().getStringProperty("SelectFiscalWeek"))
						.replace("WeekStart", getContext().getStringProperty("WeekStart"))
						.replace("LYWeekStrt", getContext().getStringProperty("LYWeekStrt"))
						.replace("Division", getContext().getStringProperty("Division")));
		System.out.println("DB Value: " + DBPlndHrsVal);

		String PlannedHoursTotal = data.getText("PlannedHoursTotal");
		DBPlndHrsTtl = sql.CLRexecuteQuery(getContext(),
				PlannedHoursTotal.replace("LYWk", getContext().getStringProperty("LYWk"))
						.replace("WeekEnd", getContext().getStringProperty("SelectFiscalWeek"))
						.replace("WeekStart", getContext().getStringProperty("WeekStart"))
						.replace("LYWeekStrt", getContext().getStringProperty("LYWeekStrt"))
						.replace("Division", getContext().getStringProperty("Division")));
		System.out.println("DB Value: " + DBPlndHrsTtl);

		PlndHrsVal = pageDailyTradingStatementGroupPO.RowWiseResult(objWeeklyTradingStatementStoreObjects,
				objWeeklyTradingStatementBUObjects.Departments, objWeeklyTradingStatementBUObjects.PlannedHours,
				objWeeklyTradingStatementBUObjects.DataTable);
		PlndHrsTtl = pageDailyTradingStatementGroupPO.WeekToDateUITotal(objWeeklyTradingStatementBUObjects.TotalTitle,
				objWeeklyTradingStatementBUObjects.PlannedHoursTtl);

		pageMonthlyPlanningPO.CompareValueDecimalFields(extentTest, DBPlndHrsVal, PlndHrsVal);
		pageMonthlyPlanningPO.CompareValueDecimalFields(extentTest, DBPlndHrsTtl, PlndHrsTtl);

		// VARIANCE IN PLANNED HOURS:
		Report_AddStep("testcase", "VARIANCE IN PLANNED HOURS - WEEK WISE DATA", "", "", "");
		extentTest = report.startTest("Variance Planned Hours");

		String VarHours = data.getText("VarHours");
		DBVarPlndHrsVal = sql.CLRexecuteQuery(getContext(),
				VarHours.replace("LYWk", getContext().getStringProperty("LYWk"))
						.replace("WeekEnd", getContext().getStringProperty("SelectFiscalWeek"))
						.replace("WeekStart", getContext().getStringProperty("WeekStart"))
						.replace("LYWeekStrt", getContext().getStringProperty("LYWeekStrt"))
						.replace("Division", getContext().getStringProperty("Division")));
		System.out.println("DB Value: " + DBVarPlndHrsVal);

		String VarHoursTotal = data.getText("VarHoursTotal");
		DBVarPlndHrsTtl = sql.CLRexecuteQuery(getContext(),
				VarHoursTotal.replace("LYWk", getContext().getStringProperty("LYWk"))
						.replace("WeekEnd", getContext().getStringProperty("SelectFiscalWeek"))
						.replace("WeekStart", getContext().getStringProperty("WeekStart"))
						.replace("LYWeekStrt", getContext().getStringProperty("LYWeekStrt"))
						.replace("Division", getContext().getStringProperty("Division")));
		System.out.println("DB Value: " + DBVarPlndHrsTtl);

		VarPlndHrsVal = pageDailyTradingStatementGroupPO.RowWiseResult(objWeeklyTradingStatementStoreObjects,
				objWeeklyTradingStatementBUObjects.Departments, objWeeklyTradingStatementBUObjects.VarPlannedHours,
				objWeeklyTradingStatementBUObjects.DataTable);
		VarPlndHrsTtl = pageDailyTradingStatementGroupPO.WeekToDateUITotal(
				objWeeklyTradingStatementBUObjects.TotalTitle, objWeeklyTradingStatementBUObjects.VarPlannedHoursTtl);

		pageMonthlyPlanningPO.CompareValueDecimalFields(extentTest, DBVarPlndHrsVal, VarPlndHrsVal);
		pageMonthlyPlanningPO.CompareValueDecimalFields(extentTest, DBVarPlndHrsTtl, VarPlndHrsTtl);
	}

	// Wages
	@Test(description = "Monthly Trading Statement - BU, Actual Wages", priority = 5)
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
				ActualWages.replace("LYWk", getContext().getStringProperty("LYWk"))
						.replace("WeekEnd", getContext().getStringProperty("SelectFiscalWeek"))
						.replace("WeekStart", getContext().getStringProperty("WeekStart"))
						.replace("LYWeekStrt", getContext().getStringProperty("LYWeekStrt"))
						.replace("Division", getContext().getStringProperty("Division")));
		System.out.println("DB Value: " + DBActlWgVal);

		String ActualWagesTotal = data.getText("ActualWagesTotal");
		DBActlWgTtl = sql.CLRexecuteQuery(getContext(),
				ActualWagesTotal.replace("LYWk", getContext().getStringProperty("LYWk"))
						.replace("WeekEnd", getContext().getStringProperty("SelectFiscalWeek"))
						.replace("WeekStart", getContext().getStringProperty("WeekStart"))
						.replace("LYWeekStrt", getContext().getStringProperty("LYWeekStrt"))
						.replace("Division", getContext().getStringProperty("Division")));
		System.out.println("DB Value: " + DBActlWgTtl);

		ActlWgVal = pageDailyTradingStatementGroupPO.RowWiseResult(objWeeklyTradingStatementStoreObjects,
				objWeeklyTradingStatementBUObjects.Departments, objWeeklyTradingStatementBUObjects.ActualWages,
				objWeeklyTradingStatementBUObjects.DataTable);
		ActlWgTtl = pageDailyTradingStatementGroupPO.WeekToDateUITotal(objWeeklyTradingStatementBUObjects.TotalTitle,
				objWeeklyTradingStatementBUObjects.ActualWagesTtl);

		pageMonthlyPlanningPO.CompareValueDecimalFields(extentTest, DBActlWgVal, ActlWgVal);
		pageMonthlyPlanningPO.CompareValueDecimalFields(extentTest, DBActlWgTtl, ActlWgTtl);

		// ACTUAL WAGE PERCENTAGE:
		Report_AddStep("testcase", "ACTUAL WAGE PERCENTAGE- WEEK WISE DATA", "", "", "");
		extentTest = report.startTest("Actual Wages Prcnt");

		String ActualWagePercentage = data.getText("ActualWagePercentage");
		DBActlWgPrcntVal = sql.CLRexecuteQuery(getContext(),
				ActualWagePercentage.replace("LYWk", getContext().getStringProperty("LYWk"))
						.replace("WeekEnd", getContext().getStringProperty("SelectFiscalWeek"))
						.replace("WeekStart", getContext().getStringProperty("WeekStart"))
						.replace("LYWeekStrt", getContext().getStringProperty("LYWeekStrt"))
						.replace("Division", getContext().getStringProperty("Division")));
		System.out.println("DB Value: " + DBActlWgPrcntVal);

		String ActualWagePercentageTotal = data.getText("ActualWagePercentageTotal");
		DBActlWgPrcntTtl = sql.CLRexecuteQuery(getContext(),
				ActualWagePercentageTotal.replace("LYWk", getContext().getStringProperty("LYWk"))
						.replace("WeekEnd", getContext().getStringProperty("SelectFiscalWeek"))
						.replace("WeekStart", getContext().getStringProperty("WeekStart"))
						.replace("LYWeekStrt", getContext().getStringProperty("LYWeekStrt"))
						.replace("Division", getContext().getStringProperty("Division")));
		System.out.println("DB Value: " + DBActlWgPrcntTtl);

		ActlWgPrcntVal = pageDailyTradingStatementGroupPO.RowWiseResult(objWeeklyTradingStatementStoreObjects,
				objWeeklyTradingStatementBUObjects.Departments,
				objWeeklyTradingStatementBUObjects.ActualWagesPercentage, objWeeklyTradingStatementBUObjects.DataTable);
		ActlWgPrcntTtl = pageDailyTradingStatementGroupPO.WeekToDateUITotal(
				objWeeklyTradingStatementBUObjects.TotalTitle,
				objWeeklyTradingStatementBUObjects.ActualWagesPercentageTtl);

		pageMonthlyPlanningPO.CompareValueDecimalFields(extentTest, DBActlWgPrcntVal, ActlWgPrcntVal);
		pageMonthlyPlanningPO.CompareValueDecimalFields(extentTest, DBActlWgPrcntTtl, ActlWgPrcntTtl);

		// BUDGET WAGE:
		if (OptionToView.contains("Budget")) {
			Report_AddStep("testcase", "ACTUAL BUDGET WAGE - WEEK WISE DATA", "", "", "");
			extentTest = report.startTest("Budget Wage");

			String BudgetWages = data.getText("BudgetWages");
			DBBdgtWgVal = sql.CLRexecuteQuery(getContext(),
					BudgetWages.replace("LYWk", getContext().getStringProperty("LYWk"))
							.replace("WeekEnd", getContext().getStringProperty("SelectFiscalWeek"))
							.replace("WeekStart", getContext().getStringProperty("WeekStart"))
							.replace("LYWeekStrt", getContext().getStringProperty("LYWeekStrt"))
							.replace("Division", getContext().getStringProperty("Division")));
			System.out.println("DB Value: " + DBBdgtWgVal);

			String BudgetWagesTotal = data.getText("BudgetWagesTotal");
			DBBdgtWgTtl = sql.CLRexecuteQuery(getContext(),
					BudgetWagesTotal.replace("LYWk", getContext().getStringProperty("LYWk"))
							.replace("WeekEnd", getContext().getStringProperty("SelectFiscalWeek"))
							.replace("WeekStart", getContext().getStringProperty("WeekStart"))
							.replace("LYWeekStrt", getContext().getStringProperty("LYWeekStrt"))
							.replace("Division", getContext().getStringProperty("Division")));
			System.out.println("DB Value: " + DBBdgtWgTtl);

			BdgtWgVal = pageDailyTradingStatementGroupPO.RowWiseResult(objWeeklyTradingStatementStoreObjects,
					objWeeklyTradingStatementBUObjects.Departments, objWeeklyTradingStatementBUObjects.BudgetWages,
					objWeeklyTradingStatementBUObjects.DataTable);
			BdgtWgTtl = pageDailyTradingStatementGroupPO.WeekToDateUITotal(
					objWeeklyTradingStatementBUObjects.TotalTitle, objWeeklyTradingStatementBUObjects.BudgetWagesTtl);

			pageMonthlyPlanningPO.CompareValueDecimalFields(extentTest, DBBdgtWgVal, BdgtWgVal);
			pageMonthlyPlanningPO.CompareValueDecimalFields(extentTest, DBBdgtWgTtl, BdgtWgTtl);
		}
		if (OptionToView.contains("Forecast")) {
			Report_AddStep("testcase", "ACTUAL FORECAST WAGE - WEEK WISE DATA", "", "", "");
			extentTest = report.startTest("Forecast Wage");

			String ForecastWages = data.getText("ForecastWages");
			DBBdgtWgVal = sql.CLRexecuteQuery(getContext(),
					ForecastWages.replace("LYWk", getContext().getStringProperty("LYWk"))
							.replace("WeekEnd", getContext().getStringProperty("SelectFiscalWeek"))
							.replace("WeekStart", getContext().getStringProperty("WeekStart"))
							.replace("LYWeekStrt", getContext().getStringProperty("LYWeekStrt"))
							.replace("Division", getContext().getStringProperty("Division")));
			System.out.println("DB Value: " + DBBdgtWgVal);

			String ForecastWagesTotal = data.getText("ForecastWagesTotal");
			DBBdgtWgTtl = sql.CLRexecuteQuery(getContext(),
					ForecastWagesTotal.replace("LYWk", getContext().getStringProperty("LYWk"))
							.replace("WeekEnd", getContext().getStringProperty("SelectFiscalWeek"))
							.replace("WeekStart", getContext().getStringProperty("WeekStart"))
							.replace("LYWeekStrt", getContext().getStringProperty("LYWeekStrt"))
							.replace("Division", getContext().getStringProperty("Division")));
			System.out.println("DB Value: " + DBBdgtWgTtl);

			BdgtWgVal = pageDailyTradingStatementGroupPO.RowWiseResult(objWeeklyTradingStatementStoreObjects,
					objWeeklyTradingStatementBUObjects.Departments, objWeeklyTradingStatementBUObjects.BudgetWages,
					objWeeklyTradingStatementBUObjects.DataTable);
			BdgtWgTtl = pageDailyTradingStatementGroupPO.WeekToDateUITotal(
					objWeeklyTradingStatementBUObjects.TotalTitle, objWeeklyTradingStatementBUObjects.BudgetWagesTtl);

			pageMonthlyPlanningPO.CompareValueDecimalFields(extentTest, DBBdgtWgVal, BdgtWgVal);
			pageMonthlyPlanningPO.CompareValueDecimalFields(extentTest, DBBdgtWgTtl, BdgtWgTtl);
		}

		// BUDGET WAGE PERCENTAGE:
		if (OptionToView.contains("Budget")) {
			Report_AddStep("testcase", "BUDGET WAGE PERCENTAGE- WEEK WISE DATA", "", "", "");
			extentTest = report.startTest("Budget Wage Prcnt");

			String BudgetWagePercentage = data.getText("BudgetWagePercentage");
			DBBdgtWgPrcntVal = sql.CLRexecuteQuery(getContext(),
					BudgetWagePercentage.replace("LYWk", getContext().getStringProperty("LYWk"))
							.replace("WeekEnd", getContext().getStringProperty("SelectFiscalWeek"))
							.replace("WeekStart", getContext().getStringProperty("WeekStart"))
							.replace("LYWeekStrt", getContext().getStringProperty("LYWeekStrt"))
							.replace("Division", getContext().getStringProperty("Division")));
			System.out.println("DB Value: " + DBBdgtWgPrcntVal);

			String BudgetWagePercentageTotal = data.getText("BudgetWagePercentageTotal");
			DBBdgtWgPrcntTtl = sql.CLRexecuteQuery(getContext(),
					BudgetWagePercentageTotal.replace("LYWk", getContext().getStringProperty("LYWk"))
							.replace("WeekEnd", getContext().getStringProperty("SelectFiscalWeek"))
							.replace("WeekStart", getContext().getStringProperty("WeekStart"))
							.replace("LYWeekStrt", getContext().getStringProperty("LYWeekStrt"))
							.replace("Division", getContext().getStringProperty("Division")));
			System.out.println("DB Value: " + DBBdgtWgPrcntTtl);

			BdgtWgPrcntVal = pageDailyTradingStatementGroupPO.RowWiseResult(objWeeklyTradingStatementStoreObjects,
					objWeeklyTradingStatementBUObjects.Departments,
					objWeeklyTradingStatementBUObjects.BudgetWagesPercentage,
					objWeeklyTradingStatementBUObjects.DataTable);
			BdgtWgPrcntTtl = pageDailyTradingStatementGroupPO.WeekToDateUITotal(
					objWeeklyTradingStatementBUObjects.TotalTitle,
					objWeeklyTradingStatementBUObjects.BudgetWagesPercentageTtl);

			pageMonthlyPlanningPO.CompareValueDecimalFields(extentTest, DBBdgtWgPrcntVal, BdgtWgPrcntVal);
			pageMonthlyPlanningPO.CompareValueDecimalFields(extentTest, DBBdgtWgPrcntTtl, BdgtWgPrcntTtl);
		}
		if (OptionToView.contains("Forecast")) {
			Report_AddStep("testcase", "FORECAST WAGE PERCENTAGE- WEEK WISE DATA", "", "", "");
			extentTest = report.startTest("Forecast Wage Prcnt");

			String ForecastWagePercentage = data.getText("ForecastWagePercentage");
			DBBdgtWgPrcntVal = sql.CLRexecuteQuery(getContext(),
					ForecastWagePercentage.replace("LYWk", getContext().getStringProperty("LYWk"))
							.replace("WeekEnd", getContext().getStringProperty("SelectFiscalWeek"))
							.replace("WeekStart", getContext().getStringProperty("WeekStart"))
							.replace("LYWeekStrt", getContext().getStringProperty("LYWeekStrt"))
							.replace("Division", getContext().getStringProperty("Division")));
			System.out.println("DB Value: " + DBBdgtWgPrcntVal);

			String ForecastWagePercentageTotal = data.getText("ForecastWagePercentageTotal");
			DBBdgtWgPrcntTtl = sql.CLRexecuteQuery(getContext(),
					ForecastWagePercentageTotal.replace("LYWk", getContext().getStringProperty("LYWk"))
							.replace("WeekEnd", getContext().getStringProperty("SelectFiscalWeek"))
							.replace("WeekStart", getContext().getStringProperty("WeekStart"))
							.replace("LYWeekStrt", getContext().getStringProperty("LYWeekStrt"))
							.replace("Division", getContext().getStringProperty("Division")));
			System.out.println("DB Value: " + DBBdgtWgPrcntTtl);

			BdgtWgPrcntVal = pageDailyTradingStatementGroupPO.RowWiseResult(objWeeklyTradingStatementStoreObjects,
					objWeeklyTradingStatementBUObjects.Departments,
					objWeeklyTradingStatementBUObjects.BudgetWagesPercentage,
					objWeeklyTradingStatementBUObjects.DataTable);
			BdgtWgPrcntTtl = pageDailyTradingStatementGroupPO.WeekToDateUITotal(
					objWeeklyTradingStatementBUObjects.TotalTitle,
					objWeeklyTradingStatementBUObjects.BudgetWagesPercentageTtl);

			pageMonthlyPlanningPO.CompareValueDecimalFields(extentTest, DBBdgtWgPrcntVal, BdgtWgPrcntVal);
			pageMonthlyPlanningPO.CompareValueDecimalFields(extentTest, DBBdgtWgPrcntTtl, BdgtWgPrcntTtl);
		}

		// PLANNED CPH:
		Report_AddStep("testcase", "PLANNED CPH - WEEK WISE DATA", "", "", "");
		extentTest = report.startTest("Planned CPH");

		String PlannedCPH = data.getText("PlannedCPH");
		DBPlndCPHVal = sql.CLRexecuteQuery(getContext(),
				PlannedCPH.replace("LYWk", getContext().getStringProperty("LYWk"))
						.replace("WeekEnd", getContext().getStringProperty("SelectFiscalWeek"))
						.replace("WeekStart", getContext().getStringProperty("WeekStart"))
						.replace("LYWeekStrt", getContext().getStringProperty("LYWeekStrt"))
						.replace("Division", getContext().getStringProperty("Division")));
		System.out.println("DB Value: " + DBPlndCPHVal);

		String PlannedCPHTotal = data.getText("PlannedCPHTotal");
		DBPlndCPHTtl = sql.CLRexecuteQuery(getContext(),
				PlannedCPHTotal.replace("LYWk", getContext().getStringProperty("LYWk"))
						.replace("WeekEnd", getContext().getStringProperty("SelectFiscalWeek"))
						.replace("WeekStart", getContext().getStringProperty("WeekStart"))
						.replace("LYWeekStrt", getContext().getStringProperty("LYWeekStrt"))
						.replace("Division", getContext().getStringProperty("Division")));
		System.out.println("DB Value: " + DBPlndCPHTtl);

		PlndCPHVal = pageDailyTradingStatementGroupPO.RowWiseResult(objWeeklyTradingStatementStoreObjects,
				objWeeklyTradingStatementBUObjects.Departments, objWeeklyTradingStatementBUObjects.PlannedCPHWages,
				objWeeklyTradingStatementBUObjects.DataTable);
		PlndCPHTtl = pageDailyTradingStatementGroupPO.WeekToDateUITotal(objWeeklyTradingStatementBUObjects.TotalTitle,
				objWeeklyTradingStatementBUObjects.PlannedCPHWagesTtl);

		pageMonthlyPlanningPO.CompareValueDecimalFields(extentTest, DBPlndCPHVal, PlndCPHVal);
		pageMonthlyPlanningPO.CompareValueDecimalFields(extentTest, DBPlndCPHTtl, PlndCPHTtl);

		// PLANNED WAGE:
		Report_AddStep("testcase", "PLANNED WAGE - WEEK WISE DATA", "", "", "");
		extentTest = report.startTest("Planned Wage");

		String PlannedWages = data.getText("PlannedWages");
		DBPlndWgVal = sql.CLRexecuteQuery(getContext(),
				PlannedWages.replace("LYWk", getContext().getStringProperty("LYWk"))
						.replace("WeekEnd", getContext().getStringProperty("SelectFiscalWeek"))
						.replace("WeekStart", getContext().getStringProperty("WeekStart"))
						.replace("LYWeekStrt", getContext().getStringProperty("LYWeekStrt"))
						.replace("Division", getContext().getStringProperty("Division")));
		System.out.println("DB Value: " + DBPlndWgVal);

		String PlannedWagesTotal = data.getText("PlannedWagesTotal");
		DBPlndWgTtl = sql.CLRexecuteQuery(getContext(),
				PlannedWagesTotal.replace("LYWk", getContext().getStringProperty("LYWk"))
						.replace("WeekEnd", getContext().getStringProperty("SelectFiscalWeek"))
						.replace("WeekStart", getContext().getStringProperty("WeekStart"))
						.replace("LYWeekStrt", getContext().getStringProperty("LYWeekStrt"))
						.replace("Division", getContext().getStringProperty("Division")));
		System.out.println("DB Value: " + DBPlndWgTtl);

		PlndWgVal = pageDailyTradingStatementGroupPO.RowWiseResult(objWeeklyTradingStatementStoreObjects,
				objWeeklyTradingStatementBUObjects.Departments, objWeeklyTradingStatementBUObjects.PlannedWages,
				objWeeklyTradingStatementBUObjects.DataTable);
		PlndWgTtl = pageDailyTradingStatementGroupPO.WeekToDateUITotal(objWeeklyTradingStatementBUObjects.TotalTitle,
				objWeeklyTradingStatementBUObjects.PlannedWagesTtl);

		pageMonthlyPlanningPO.CompareValues(extentTest, DBPlndWgVal, PlndWgVal);
		pageMonthlyPlanningPO.CompareValueDecimalFields(extentTest, DBPlndWgTtl, PlndWgTtl);

		// PLANNED WAGE PERCENTAGE:
		Report_AddStep("testcase", "PLANNED WAGE PERCENTAGE - WEEK WISE DATA", "", "", "");
		extentTest = report.startTest("Planned Wage Prcnt");

		String PlannedWagesPercentage = data.getText("PlannedWagesPercentage");
		DBPlndWgPrcntVal = sql.CLRexecuteQuery(getContext(),
				PlannedWagesPercentage.replace("LYWk", getContext().getStringProperty("LYWk"))
						.replace("WeekEnd", getContext().getStringProperty("SelectFiscalWeek"))
						.replace("WeekStart", getContext().getStringProperty("WeekStart"))
						.replace("LYWeekStrt", getContext().getStringProperty("LYWeekStrt"))
						.replace("Division", getContext().getStringProperty("Division")));
		System.out.println("DB Value: " + DBPlndWgPrcntVal);

		String PlannedWagesPercentageTotal = data.getText("PlannedWagesPercentageTotal");
		DBPlndWgPrcntTtl = sql.CLRexecuteQuery(getContext(),
				PlannedWagesPercentageTotal.replace("LYWk", getContext().getStringProperty("LYWk"))
						.replace("WeekEnd", getContext().getStringProperty("SelectFiscalWeek"))
						.replace("WeekStart", getContext().getStringProperty("WeekStart"))
						.replace("LYWeekStrt", getContext().getStringProperty("LYWeekStrt"))
						.replace("Division", getContext().getStringProperty("Division")));
		System.out.println("DB Value: " + DBPlndWgPrcntTtl);

		PlndWgPrcntVal = pageDailyTradingStatementGroupPO.RowWiseResult(objWeeklyTradingStatementStoreObjects,
				objWeeklyTradingStatementBUObjects.Departments,
				objWeeklyTradingStatementBUObjects.PlannedWagesPercentage,
				objWeeklyTradingStatementBUObjects.DataTable);
		PlndWgPrcntTtl = pageDailyTradingStatementGroupPO.WeekToDateUITotal(
				objWeeklyTradingStatementBUObjects.TotalTitle,
				objWeeklyTradingStatementBUObjects.PlannedWagesPercentageTtl);

		pageMonthlyPlanningPO.CompareValueDecimalFields(extentTest, DBPlndWgPrcntVal, PlndWgPrcntVal);
		pageMonthlyPlanningPO.CompareValueDecimalFields(extentTest, DBPlndWgPrcntTtl, PlndWgPrcntTtl);

		// CPH Wages:
		Report_AddStep("testcase", "CPH WAGES - WEEK WISE DATA", "", "", "");
		extentTest = report.startTest("CPH Wages");

		String ActualCPH = data.getText("ActualCPH");
		DBCPHWgVal = sql.CLRexecuteQuery(getContext(),
				ActualCPH.replace("LYWk", getContext().getStringProperty("LYWk"))
						.replace("WeekEnd", getContext().getStringProperty("SelectFiscalWeek"))
						.replace("WeekStart", getContext().getStringProperty("WeekStart"))
						.replace("LYWeekStrt", getContext().getStringProperty("LYWeekStrt"))
						.replace("Division", getContext().getStringProperty("Division")));
		System.out.println("DB Value: " + DBCPHWgVal);

		String ActualCPHTotal = data.getText("ActualCPHTotal");
		DBCPHWgTtl = sql.CLRexecuteQuery(getContext(),
				ActualCPHTotal.replace("LYWk", getContext().getStringProperty("LYWk"))
						.replace("WeekEnd", getContext().getStringProperty("SelectFiscalWeek"))
						.replace("WeekStart", getContext().getStringProperty("WeekStart"))
						.replace("LYWeekStrt", getContext().getStringProperty("LYWeekStrt"))
						.replace("Division", getContext().getStringProperty("Division")));
		System.out.println("DB Value: " + DBCPHWgTtl);

		CPHWgVal = pageDailyTradingStatementGroupPO.RowWiseResult(objWeeklyTradingStatementStoreObjects,
				objWeeklyTradingStatementBUObjects.Departments, objWeeklyTradingStatementBUObjects.CPHWages,
				objWeeklyTradingStatementBUObjects.DataTable);
		CPHWgTtl = pageDailyTradingStatementGroupPO.WeekToDateUITotal(objWeeklyTradingStatementBUObjects.TotalTitle,
				objWeeklyTradingStatementBUObjects.CPHWagesTtl);

		pageMonthlyPlanningPO.CompareValueDecimalFields(extentTest, DBCPHWgVal, CPHWgVal);
		pageMonthlyPlanningPO.CompareValueDecimalFields(extentTest, DBCPHWgTtl, CPHWgTtl);

		// VARIANCE BUDGET WAGES:
		if (OptionToView.contains("Budget")) {
			Report_AddStep("testcase", "VARIANCE BUDGET WAGES - WEEK WISE DATA", "", "", "");
			extentTest = report.startTest("Variance Budget Wages");

			String VarWages = data.getText("VarWages");
			DBVarBdgtWgVal = sql.CLRexecuteQuery(getContext(),
					VarWages.replace("LYWk", getContext().getStringProperty("LYWk"))
							.replace("WeekEnd", getContext().getStringProperty("SelectFiscalWeek"))
							.replace("WeekStart", getContext().getStringProperty("WeekStart"))
							.replace("LYWeekStrt", getContext().getStringProperty("LYWeekStrt"))
							.replace("Division", getContext().getStringProperty("Division")));
			System.out.println("DB Value: " + DBVarBdgtWgVal);

			String VarWagesTotal = data.getText("VarWagesTotal");
			DBVarBdgtWgTtl = sql.CLRexecuteQuery(getContext(),
					VarWagesTotal.replace("LYWk", getContext().getStringProperty("LYWk"))
							.replace("WeekEnd", getContext().getStringProperty("SelectFiscalWeek"))
							.replace("WeekStart", getContext().getStringProperty("WeekStart"))
							.replace("LYWeekStrt", getContext().getStringProperty("LYWeekStrt"))
							.replace("Division", getContext().getStringProperty("Division")));
			System.out.println("DB Value: " + DBVarBdgtWgTtl);

			VarBdgtWgVal = pageDailyTradingStatementGroupPO.RowWiseResult(objWeeklyTradingStatementStoreObjects,
					objWeeklyTradingStatementBUObjects.Departments, objWeeklyTradingStatementBUObjects.VarBudgetWages,
					objWeeklyTradingStatementBUObjects.DataTable);
			VarBdgtWgTtl = pageDailyTradingStatementGroupPO.WeekToDateUITotal(
					objWeeklyTradingStatementBUObjects.TotalTitle,
					objWeeklyTradingStatementBUObjects.VarBudgetWagesTtl);

			pageMonthlyPlanningPO.CompareValueDecimalFields(extentTest, DBVarBdgtWgVal, VarBdgtWgVal);
			pageMonthlyPlanningPO.CompareValueDecimalFields(extentTest, DBVarBdgtWgTtl, VarBdgtWgTtl);
		}
		if (OptionToView.contains("Forecast")) {
			Report_AddStep("testcase", "VARIANCE FORECAST WAGES - WEEK WISE DATA", "", "", "");
			extentTest = report.startTest("Variance Forecast Wages");

			String VarFcstWages = data.getText("VarFcstWages");
			DBVarBdgtWgVal = sql.CLRexecuteQuery(getContext(),
					VarFcstWages.replace("LYWk", getContext().getStringProperty("LYWk"))
							.replace("WeekEnd", getContext().getStringProperty("SelectFiscalWeek"))
							.replace("WeekStart", getContext().getStringProperty("WeekStart"))
							.replace("LYWeekStrt", getContext().getStringProperty("LYWeekStrt"))
							.replace("Division", getContext().getStringProperty("Division")));
			System.out.println("DB Value: " + DBVarBdgtWgVal);

			String VarFcstWagesTotal = data.getText("VarFcstWagesTotal");
			DBVarBdgtWgTtl = sql.CLRexecuteQuery(getContext(),
					VarFcstWagesTotal.replace("LYWk", getContext().getStringProperty("LYWk"))
							.replace("WeekEnd", getContext().getStringProperty("SelectFiscalWeek"))
							.replace("WeekStart", getContext().getStringProperty("WeekStart"))
							.replace("LYWeekStrt", getContext().getStringProperty("LYWeekStrt"))
							.replace("Division", getContext().getStringProperty("Division")));
			System.out.println("DB Value: " + DBVarBdgtWgTtl);

			VarBdgtWgVal = pageDailyTradingStatementGroupPO.RowWiseResult(objWeeklyTradingStatementStoreObjects,
					objWeeklyTradingStatementBUObjects.Departments, objWeeklyTradingStatementBUObjects.VarBudgetWages,
					objWeeklyTradingStatementBUObjects.DataTable);
			VarBdgtWgTtl = pageDailyTradingStatementGroupPO.WeekToDateUITotal(
					objWeeklyTradingStatementBUObjects.TotalTitle,
					objWeeklyTradingStatementBUObjects.VarBudgetWagesTtl);

			pageMonthlyPlanningPO.CompareValueDecimalFields(extentTest, DBVarBdgtWgVal, VarBdgtWgVal);
			pageMonthlyPlanningPO.CompareValueDecimalFields(extentTest, DBVarBdgtWgTtl, VarBdgtWgTtl);
		}
		// Allowed Wages Validation
				if (OptionToView.contains("Budget")) {
					Report_AddStep("testcase", "Allowed Wages Validation", "", "", "");
					extentTest = report.startTest("Allowed Wages");

					String AllowedWages = data.getText("AllowedWages");
					DBAllowedWgVal = sql.CLRexecuteQuery(getContext(),
							AllowedWages.replace("LYWk", getContext().getStringProperty("LYWk"))
									.replace("WeekEnd", getContext().getStringProperty("SelectFiscalWeek"))
									.replace("WeekStart", getContext().getStringProperty("WeekStart"))
									.replace("LYWeekStrt", getContext().getStringProperty("LYWeekStrt"))
									.replace("Division", getContext().getStringProperty("Division")));
					System.out.println("DB Value: " + DBAllowedWgVal);

					String AllowedWagesTotal = data.getText("AllowedWagesTotal");
					DBAllowedWgTtl = sql.CLRexecuteQuery(getContext(),
							AllowedWagesTotal.replace("LYWk", getContext().getStringProperty("LYWk"))
									.replace("WeekEnd", getContext().getStringProperty("SelectFiscalWeek"))
									.replace("WeekStart", getContext().getStringProperty("WeekStart"))
									.replace("LYWeekStrt", getContext().getStringProperty("LYWeekStrt"))
									.replace("Division", getContext().getStringProperty("Division")));
					System.out.println("DB Value: " + DBAllowedWgTtl);

					AllowedWgVal = pageDailyTradingStatementGroupPO.RowWiseResult(objWeeklyTradingStatementStoreObjects,
							objWeeklyTradingStatementBUObjects.Departments, objWeeklyTradingStatementBUObjects.AllowedWages,
							objWeeklyTradingStatementBUObjects.DataTable);
					AllowedWgTtl = pageDailyTradingStatementGroupPO.WeekToDateUITotal(
							objWeeklyTradingStatementBUObjects.TotalTitle, objWeeklyTradingStatementBUObjects.AllowedWagesTtl);

					pageMonthlyPlanningPO.CompareValues(extentTest, DBAllowedWgVal, AllowedWgVal);
					pageMonthlyPlanningPO.CompareValues(extentTest, DBAllowedWgTtl, AllowedWgTtl);
				}
	}

	@Test(priority = 6)
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
		objWeeklyTradingStatementBUObjects = PageFactory.initElements(this.getDriver(),
				WeeklyTradingStatementBusinessUnitObjects.class);
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
			data.loadTestDataXML(System.getProperty("user.dir") + "\\TestData\\WLP\\MonthlyTradingStatementBU.xml");
			customreport.createExtentReport();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

}