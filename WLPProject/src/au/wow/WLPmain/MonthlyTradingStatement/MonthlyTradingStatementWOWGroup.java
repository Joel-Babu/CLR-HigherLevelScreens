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
import au.wow.WLPmain.objects.WeeklyTradingStatementWOWGroupObjects;
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

public class MonthlyTradingStatementWOWGroup extends TestBase {
	protected Logger log = LogManager.getLogger(MonthlyTradingStatementWOWGroup.class);
	TestStatus status;
	ExtentReportsScreenshotCode ScreenShotUtil;
	SQLWrapper sql = new SQLWrapper(log);
	WoWLoginPage loginPage;
	CommonRepository CommonFunc;
	WeeklyPlanningStoreObjects objWeeklyPlanningStoreObjects;
	WeeklyTradingStatementStoreObjects objWeeklyTradingStatementStoreObjects;
	WeeklyTradingStatementWOWGroupObjects objWeeklyTradingStatementWOWGroupObjects;
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
			DBPlndWgPrcntVal, DBCPHWgTtl, DBCPHWgVal, DBVarBdgtWgTtl, DBVarBdgtWgVal,DBAllowedWgTtl, AllowedWgVal,AllowedWgTtl;
	String OptionToView;

	public MonthlyTradingStatementWOWGroup() {
		super.log = log;
	}

	@Test(priority = 0)
	public void LogintoCLRApplication() throws Exception {
		loginPage = new WoWLoginPage().createPage(getDriver(), getContext(), log, status, data, report, extentTest,
				WoWLoginPage.class);
		pageWeeklyPlanningPO = loginPage.navToWOWGroupPage(getContext().getStringProperty("store"));
	}

	// Sales
	@Test(description = "Monthly Trading Statement - WOWGroup, SalesColumnValidation", priority = 1)
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
				objWeeklyPlanningStoreObjects.screenToViewMonthlyTrading, "Woolworths Group");
		//pageWeeklyPlanningPO.verifyPageName(extentTest, objWeeklyPlanningStoreObjects.DataFrame,
			//	objWeeklyPlanningStoreObjects.pageName, getContext().getStringProperty("MTSWOWGroup"));

		OptionToView = getContext().getStringProperty("OptionToView");

		// ACTUAL SALES:
		Report_AddStep("testcase", " ACTUAL SALES - WEEK WISE DATA", "", "", "");
		extentTest = report.startTest("Actual Sales");

		String ActualSalesTotal = data.getText("ActualSalesTotal");
		DBActlTtl = sql.CLRexecuteQuery(getContext(),
				ActualSalesTotal.replace("LYWk", getContext().getStringProperty("LYWk"))
						.replace("WeekEnd", getContext().getStringProperty("SelectFiscalWeek"))
						.replace("WeekStart", getContext().getStringProperty("WeekStart"))
						.replace("LYWeekStrt", getContext().getStringProperty("LYWeekStrt"))
						.replace("Division", getContext().getStringProperty("Division")));
		System.out.println("DB Value: " + DBActlTtl);

		ActlVal = pageDailyTradingStatementGroupPO.RowWiseResult(objWeeklyTradingStatementStoreObjects,
				objWeeklyTradingStatementWOWGroupObjects.Departments,
				objWeeklyTradingStatementWOWGroupObjects.ActualSales,
				objWeeklyTradingStatementWOWGroupObjects.DataTable);
		ActlTtl = pageDailyTradingStatementGroupPO.WeekToDateUITotal(
				objWeeklyTradingStatementWOWGroupObjects.TotalTitle,
				objWeeklyTradingStatementWOWGroupObjects.ActualSalesTtl);

		pageMonthlyPlanningPO.CompareValues(extentTest, DBActlTtl, ActlVal);
		pageMonthlyPlanningPO.CompareValues(extentTest, DBActlTtl, ActlTtl);

		// PLANNED SALES:
		Report_AddStep("testcase", " PLANNED SALES - WEEK WISE DATA", "", "", "");
		extentTest = report.startTest("Planned Sales");

		String PlannedSalesTotal = data.getText("PlannedSalesTotal");
		DBPlndTtl = sql.CLRexecuteQuery(getContext(),
				PlannedSalesTotal.replace("LYWk", getContext().getStringProperty("LYWk"))
						.replace("WeekEnd", getContext().getStringProperty("SelectFiscalWeek"))
						.replace("WeekStart", getContext().getStringProperty("WeekStart"))
						.replace("LYWeekStrt", getContext().getStringProperty("LYWeekStrt"))
						.replace("Division", getContext().getStringProperty("Division")));
		System.out.println("DB Value: " + DBPlndTtl);

		PlndVal = pageDailyTradingStatementGroupPO.RowWiseResult(objWeeklyTradingStatementStoreObjects,
				objWeeklyTradingStatementWOWGroupObjects.Departments,
				objWeeklyTradingStatementWOWGroupObjects.PlannedSales,
				objWeeklyTradingStatementWOWGroupObjects.DataTable);
		PlndTtl = pageDailyTradingStatementGroupPO.WeekToDateUITotal(
				objWeeklyTradingStatementWOWGroupObjects.TotalTitle,
				objWeeklyTradingStatementWOWGroupObjects.PlannedSalesTtl);

		pageMonthlyPlanningPO.CompareValues(extentTest, DBPlndTtl, PlndVal);
		pageMonthlyPlanningPO.CompareValues(extentTest, DBPlndTtl, PlndTtl);

		// VARIANCE IN PLANNED SALES:
		Report_AddStep("testcase", " VARIANCE IN PLANNED SALES - WEEK WISE DATA", "", "", "");
		extentTest = report.startTest("Variance Sales");

		String VarSalesTotal = data.getText("VarSalesTotal");
		DBVarPlndTtl = sql.CLRexecuteQuery(getContext(),
				VarSalesTotal.replace("LYWk", getContext().getStringProperty("LYWk"))
						.replace("WeekEnd", getContext().getStringProperty("SelectFiscalWeek"))
						.replace("WeekStart", getContext().getStringProperty("WeekStart"))
						.replace("LYWeekStrt", getContext().getStringProperty("LYWeekStrt"))
						.replace("Division", getContext().getStringProperty("Division")));
		System.out.println("DB Value: " + DBVarPlndTtl);

		VarPlndVal = pageDailyTradingStatementGroupPO.RowWiseResult(objWeeklyTradingStatementStoreObjects,
				objWeeklyTradingStatementWOWGroupObjects.Departments,
				objWeeklyTradingStatementWOWGroupObjects.PlannedSalesGrwth,
				objWeeklyTradingStatementWOWGroupObjects.DataTable);
		VarPlndTtl = pageDailyTradingStatementGroupPO.WeekToDateUITotal(
				objWeeklyTradingStatementWOWGroupObjects.TotalTitle,
				objWeeklyTradingStatementWOWGroupObjects.PlannedSalesGrwthTtl);

		pageMonthlyPlanningPO.CompareValues(extentTest, DBVarPlndTtl, VarPlndVal);
		pageMonthlyPlanningPO.CompareValues(extentTest, DBVarPlndTtl, VarPlndTtl);

		// LAST YEAR SALES:
		Report_AddStep("testcase", " LAST YEAR SALES - WEEK WISE DATA", "", "", "");
		extentTest = report.startTest("LY Sales");

		String LYSalesTotal = data.getText("LYSalesTotal");
		DBLYSlsTtl = sql.CLRexecuteQuery(getContext(),
				LYSalesTotal.replace("LYWk", getContext().getStringProperty("LYWk"))
						.replace("WeekEnd", getContext().getStringProperty("SelectFiscalWeek"))
						.replace("WeekStart", getContext().getStringProperty("WeekStart"))
						.replace("LYWeekStrt", getContext().getStringProperty("LYWeekStrt"))
						.replace("Division", getContext().getStringProperty("Division")));
		System.out.println("DB Value: " + DBLYSlsTtl);

		LYSlsVal = pageDailyTradingStatementGroupPO.RowWiseResult(objWeeklyTradingStatementStoreObjects,
				objWeeklyTradingStatementWOWGroupObjects.Departments, objWeeklyTradingStatementWOWGroupObjects.LYSales,
				objWeeklyTradingStatementWOWGroupObjects.DataTable);
		LYSlsTtl = pageDailyTradingStatementGroupPO.WeekToDateUITotal(
				objWeeklyTradingStatementWOWGroupObjects.TotalTitle,
				objWeeklyTradingStatementWOWGroupObjects.LYSalesTtl);

		pageMonthlyPlanningPO.CompareValues(extentTest, DBLYSlsTtl, LYSlsVal);
		pageMonthlyPlanningPO.CompareValues(extentTest, DBLYSlsTtl, LYSlsTtl);

		// VARIANCE IN LAST YEAR SALES:
		Report_AddStep("testcase", " VARIANCE IN LAST YEAR SALES - WEEK WISE DATA", "", "", "");
		extentTest = report.startTest("Variance LY Sales");

		String LYSalesPctTotal = data.getText("LYSalesPctTotal");
		DBVarLYSlsTtl = sql.CLRexecuteQuery(getContext(),
				LYSalesPctTotal.replace("LYWk", getContext().getStringProperty("LYWk"))
						.replace("WeekEnd", getContext().getStringProperty("SelectFiscalWeek"))
						.replace("WeekStart", getContext().getStringProperty("WeekStart"))
						.replace("LYWeekStrt", getContext().getStringProperty("LYWeekStrt"))
						.replace("Division", getContext().getStringProperty("Division")));
		System.out.println("DB Value: " + DBVarLYSlsTtl);

		VarLYSlsVal = pageDailyTradingStatementGroupPO.RowWiseResult(objWeeklyTradingStatementStoreObjects,
				objWeeklyTradingStatementWOWGroupObjects.Departments,
				objWeeklyTradingStatementWOWGroupObjects.LYSalesGrwth,
				objWeeklyTradingStatementWOWGroupObjects.DataTable);
		VarLYSlsTtl = pageDailyTradingStatementGroupPO.WeekToDateUITotal(
				objWeeklyTradingStatementWOWGroupObjects.TotalTitle,
				objWeeklyTradingStatementWOWGroupObjects.LYSalesGrwthTtl);

		pageMonthlyPlanningPO.CompareValues(extentTest, DBVarLYSlsTtl, VarLYSlsVal);
		pageMonthlyPlanningPO.CompareValues(extentTest, DBVarLYSlsTtl, VarLYSlsTtl);

		// BUDGET SALES:
		if (OptionToView.contains("Budget")) {
			Report_AddStep("testcase", "BUDGET SALES - WEEK WISE DATA", "", "", "");
			extentTest = report.startTest("Budget Sales");

			String BudgetSalesTotal = data.getText("BudgetSalesTotal");
			DBBdgtTtl = sql.CLRexecuteQuery(getContext(),
					BudgetSalesTotal.replace("LYWk", getContext().getStringProperty("LYWk"))
							.replace("WeekEnd", getContext().getStringProperty("SelectFiscalWeek"))
							.replace("WeekStart", getContext().getStringProperty("WeekStart"))
							.replace("LYWeekStrt", getContext().getStringProperty("LYWeekStrt"))
							.replace("Division", getContext().getStringProperty("Division")));
			System.out.println("DB Value: " + DBBdgtTtl);

			BdgtVal = pageDailyTradingStatementGroupPO.RowWiseResult(objWeeklyTradingStatementStoreObjects,
					objWeeklyTradingStatementWOWGroupObjects.Departments,
					objWeeklyTradingStatementWOWGroupObjects.BudgetSales,
					objWeeklyTradingStatementWOWGroupObjects.DataTable);
			BdgtTtl = pageDailyTradingStatementGroupPO.WeekToDateUITotal(
					objWeeklyTradingStatementWOWGroupObjects.TotalTitle,
					objWeeklyTradingStatementWOWGroupObjects.BudgetSalesTtl);

			pageMonthlyPlanningPO.CompareValues(extentTest, DBBdgtTtl, BdgtVal);
			pageMonthlyPlanningPO.CompareValues(extentTest, DBBdgtTtl, BdgtTtl);
		}
		if (OptionToView.contains("Forecast")) {
			Report_AddStep("testcase", "FORECAST SALES - WEEK WISE DATA", "", "", "");
			extentTest = report.startTest("Forecast Sales");

			String ForecastSalesTotal = data.getText("ForecastSalesTotal");
			DBBdgtTtl = sql.CLRexecuteQuery(getContext(),
					ForecastSalesTotal.replace("LYWk", getContext().getStringProperty("LYWk"))
							.replace("WeekEnd", getContext().getStringProperty("SelectFiscalWeek"))
							.replace("WeekStart", getContext().getStringProperty("WeekStart"))
							.replace("LYWeekStrt", getContext().getStringProperty("LYWeekStrt"))
							.replace("Division", getContext().getStringProperty("Division")));
			System.out.println("DB Value: " + DBBdgtTtl);

			BdgtVal = pageDailyTradingStatementGroupPO.RowWiseResult(objWeeklyTradingStatementStoreObjects,
					objWeeklyTradingStatementWOWGroupObjects.Departments,
					objWeeklyTradingStatementWOWGroupObjects.BudgetSales,
					objWeeklyTradingStatementWOWGroupObjects.DataTable);
			BdgtTtl = pageDailyTradingStatementGroupPO.WeekToDateUITotal(
					objWeeklyTradingStatementWOWGroupObjects.TotalTitle,
					objWeeklyTradingStatementWOWGroupObjects.BudgetSalesTtl);

			pageMonthlyPlanningPO.CompareValues(extentTest, DBBdgtTtl, BdgtVal);
			pageMonthlyPlanningPO.CompareValues(extentTest, DBBdgtTtl, BdgtTtl);
		}

		// VARIANCE IN BUDGET SALES:
		if (OptionToView.contains("Budget")) {
			Report_AddStep("testcase", "VARIANCE IN BUDGET SALES - WEEK WISE DATA", "", "", "");
			extentTest = report.startTest("Variance Budget Sales");

			String VarBudgetSalesTotal = data.getText("VarBudgetSalesTotal");
			DBVarBdgtTtl = sql.CLRexecuteQuery(getContext(),
					VarBudgetSalesTotal.replace("LYWk", getContext().getStringProperty("LYWk"))
							.replace("WeekEnd", getContext().getStringProperty("SelectFiscalWeek"))
							.replace("WeekStart", getContext().getStringProperty("WeekStart"))
							.replace("LYWeekStrt", getContext().getStringProperty("LYWeekStrt"))
							.replace("Division", getContext().getStringProperty("Division")));
			System.out.println("DB Value: " + DBVarBdgtTtl);

			VarBdgtVal = pageDailyTradingStatementGroupPO.RowWiseResult(objWeeklyTradingStatementStoreObjects,
					objWeeklyTradingStatementWOWGroupObjects.Departments,
					objWeeklyTradingStatementWOWGroupObjects.BudgetSalesGrwth,
					objWeeklyTradingStatementWOWGroupObjects.DataTable);
			VarBdgtTtl = pageDailyTradingStatementGroupPO.WeekToDateUITotal(
					objWeeklyTradingStatementWOWGroupObjects.TotalTitle,
					objWeeklyTradingStatementWOWGroupObjects.BudgetSalesGrwthTtl);

			pageMonthlyPlanningPO.CompareValues(extentTest, DBVarBdgtTtl, VarBdgtVal);
			pageMonthlyPlanningPO.CompareValues(extentTest, DBVarBdgtTtl, VarBdgtTtl);
		}
		if (OptionToView.contains("Forecast")) {
			Report_AddStep("testcase", "VARIANCE IN FORECAST SALES - WEEK WISE DATA", "", "", "");
			extentTest = report.startTest("Variance Forecast Sales");

			String VarFcstSalesTotal = data.getText("VarFcstSalesTotal");
			DBVarBdgtTtl = sql.CLRexecuteQuery(getContext(),
					VarFcstSalesTotal.replace("LYWk", getContext().getStringProperty("LYWk"))
							.replace("WeekEnd", getContext().getStringProperty("SelectFiscalWeek"))
							.replace("WeekStart", getContext().getStringProperty("WeekStart"))
							.replace("LYWeekStrt", getContext().getStringProperty("LYWeekStrt"))
							.replace("Division", getContext().getStringProperty("Division")));
			System.out.println("DB Value: " + DBVarBdgtTtl);

			VarBdgtVal = pageDailyTradingStatementGroupPO.RowWiseResult(objWeeklyTradingStatementStoreObjects,
					objWeeklyTradingStatementWOWGroupObjects.Departments,
					objWeeklyTradingStatementWOWGroupObjects.BudgetSalesGrwth,
					objWeeklyTradingStatementWOWGroupObjects.DataTable);
			VarBdgtTtl = pageDailyTradingStatementGroupPO.WeekToDateUITotal(
					objWeeklyTradingStatementWOWGroupObjects.TotalTitle,
					objWeeklyTradingStatementWOWGroupObjects.BudgetSalesGrwthTtl);

			pageMonthlyPlanningPO.CompareValues(extentTest, DBVarBdgtTtl, VarBdgtVal);
			pageMonthlyPlanningPO.CompareValues(extentTest, DBVarBdgtTtl, VarBdgtTtl);
		}

		// AVERAGE BASKET SALES:
		Report_AddStep("testcase", "AVERAGE BASKET SALES - WEEK WISE DATA", "", "", "");
		extentTest = report.startTest("Average Basket Sales");

		String AvgBasketSalesTotal = data.getText("AvgBasketSalesTotal");
		DBAvgBsktTtl = sql.CLRexecuteQuery(getContext(),
				AvgBasketSalesTotal.replace("LYWk", getContext().getStringProperty("LYWk"))
						.replace("WeekEnd", getContext().getStringProperty("SelectFiscalWeek"))
						.replace("WeekStart", getContext().getStringProperty("WeekStart"))
						.replace("LYWeekStrt", getContext().getStringProperty("LYWeekStrt"))
						.replace("Division", getContext().getStringProperty("Division")));
		System.out.println("DB Value: " + DBAvgBsktTtl);

		AvgBsktVal = pageDailyTradingStatementGroupPO.RowWiseResult(objWeeklyTradingStatementStoreObjects,
				objWeeklyTradingStatementWOWGroupObjects.Departments,
				objWeeklyTradingStatementWOWGroupObjects.AvgBasketSales,
				objWeeklyTradingStatementWOWGroupObjects.DataTable);
		AvgBsktTtl = pageDailyTradingStatementGroupPO.WeekToDateUITotal(
				objWeeklyTradingStatementWOWGroupObjects.TotalTitle,
				objWeeklyTradingStatementWOWGroupObjects.AvgBasketSalesTtl);

		pageMonthlyPlanningPO.CompareValues(extentTest, DBAvgBsktTtl, AvgBsktVal);
		pageMonthlyPlanningPO.CompareValues(extentTest, DBAvgBsktTtl, AvgBsktTtl);

		// MIX SALES:
		Report_AddStep("testcase", "MIX SALES - WEEK WISE DATA", "", "", "");
		extentTest = report.startTest("Mix Sales");

		String WkyMixSalesTotal = data.getText("WkyMixSalesTotal");
		DBMixTtl = sql.CLRexecuteQuery(getContext(),
				WkyMixSalesTotal.replace("LYWk", getContext().getStringProperty("LYWk"))
						.replace("WeekEnd", getContext().getStringProperty("SelectFiscalWeek"))
						.replace("WeekStart", getContext().getStringProperty("WeekStart"))
						.replace("LYWeekStrt", getContext().getStringProperty("LYWeekStrt"))
						.replace("Division", getContext().getStringProperty("Division")));
		System.out.println("DB Value: " + DBMixTtl);

		MixVal = pageDailyTradingStatementGroupPO.RowWiseResult(objWeeklyTradingStatementStoreObjects,
				objWeeklyTradingStatementWOWGroupObjects.Departments, objWeeklyTradingStatementWOWGroupObjects.Mix,
				objWeeklyTradingStatementWOWGroupObjects.DataTable);
		MixTtl = pageDailyTradingStatementGroupPO.WeekToDateUITotal(objWeeklyTradingStatementWOWGroupObjects.TotalTitle,
				objWeeklyTradingStatementWOWGroupObjects.MixTtl);

		pageMonthlyPlanningPO.CompareValues(extentTest, DBMixTtl, MixVal);
		pageMonthlyPlanningPO.CompareValues(extentTest, DBMixTtl, MixTtl);
	}

	// Items and Item Price
	@Test(description = "Monthly Trading Statement - WOWGroup, Planned Item", priority = 2)
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

		String PlannedItemsTotal = data.getText("PlannedItemsTotal");
		DBPlndItmTtl = sql.CLRexecuteQuery(getContext(),
				PlannedItemsTotal.replace("LYWk", getContext().getStringProperty("LYWk"))
						.replace("WeekEnd", getContext().getStringProperty("SelectFiscalWeek"))
						.replace("WeekStart", getContext().getStringProperty("WeekStart"))
						.replace("LYWeekStrt", getContext().getStringProperty("LYWeekStrt"))
						.replace("Division", getContext().getStringProperty("Division")));
		System.out.println("DB Value: " + DBPlndItmTtl);

		PlndItmVal = pageDailyTradingStatementGroupPO.RowWiseResult(objWeeklyTradingStatementStoreObjects,
				objWeeklyTradingStatementWOWGroupObjects.Departments,
				objWeeklyTradingStatementWOWGroupObjects.PlannedItem,
				objWeeklyTradingStatementWOWGroupObjects.DataTable);
		PlndItmTtl = pageDailyTradingStatementGroupPO.WeekToDateUITotal(
				objWeeklyTradingStatementWOWGroupObjects.TotalTitle,
				objWeeklyTradingStatementWOWGroupObjects.PlannedItemTtl);

		pageMonthlyPlanningPO.CompareValues(extentTest, DBPlndItmTtl, PlndItmVal);
		pageMonthlyPlanningPO.CompareValues(extentTest, DBPlndItmTtl, PlndItmTtl);

		// ACTUAL ITEMS:
		Report_AddStep("testcase", "ACTUAL ITEMS - WEEK WISE DATA", "", "", "");
		extentTest = report.startTest("Actual Items");

		String ActualItemsTotal = data.getText("ActualItemsTotal");
		DBActlItmTtl = sql.CLRexecuteQuery(getContext(),
				ActualItemsTotal.replace("LYWk", getContext().getStringProperty("LYWk"))
						.replace("WeekEnd", getContext().getStringProperty("SelectFiscalWeek"))
						.replace("WeekStart", getContext().getStringProperty("WeekStart"))
						.replace("LYWeekStrt", getContext().getStringProperty("LYWeekStrt"))
						.replace("Division", getContext().getStringProperty("Division")));
		System.out.println("DB Value: " + DBActlItmTtl);

		ActlItmVal = pageDailyTradingStatementGroupPO.RowWiseResult(objWeeklyTradingStatementStoreObjects,
				objWeeklyTradingStatementWOWGroupObjects.Departments,
				objWeeklyTradingStatementWOWGroupObjects.ActualItem,
				objWeeklyTradingStatementWOWGroupObjects.DataTable);
		ActlItmTtl = pageDailyTradingStatementGroupPO.WeekToDateUITotal(
				objWeeklyTradingStatementWOWGroupObjects.TotalTitle,
				objWeeklyTradingStatementWOWGroupObjects.ActualItemTtl);

		pageMonthlyPlanningPO.CompareValues(extentTest, DBActlItmTtl, ActlItmVal);
		pageMonthlyPlanningPO.CompareValues(extentTest, DBActlItmTtl, ActlItmTtl);

		// PLANNED ITEM PRICE:
		Report_AddStep("testcase", "PLANNED ITEM PRICE - WEEK WISE DATA", "", "", "");
		extentTest = report.startTest("Planned Item Price");

		String PlannedItemPriceTotal = data.getText("PlannedItemPriceTotal");
		DBPlndItmPriceTtl = sql.CLRexecuteQuery(getContext(),
				PlannedItemPriceTotal.replace("LYWk", getContext().getStringProperty("LYWk"))
						.replace("WeekEnd", getContext().getStringProperty("SelectFiscalWeek"))
						.replace("WeekStart", getContext().getStringProperty("WeekStart"))
						.replace("LYWeekStrt", getContext().getStringProperty("LYWeekStrt"))
						.replace("Division", getContext().getStringProperty("Division")));
		System.out.println("DB Value: " + DBPlndItmPriceTtl);

		PlndItmPriceVal = pageDailyTradingStatementGroupPO.RowWiseResult(objWeeklyTradingStatementStoreObjects,
				objWeeklyTradingStatementWOWGroupObjects.Departments,
				objWeeklyTradingStatementWOWGroupObjects.PlannedItemPrice,
				objWeeklyTradingStatementWOWGroupObjects.DataTable);
		PlndItmPriceTtl = pageDailyTradingStatementGroupPO.WeekToDateUITotal(
				objWeeklyTradingStatementWOWGroupObjects.TotalTitle,
				objWeeklyTradingStatementWOWGroupObjects.PlannedItemPriceTtl);

		pageMonthlyPlanningPO.CompareValueDecimalFields(extentTest, DBPlndItmPriceTtl, PlndItmPriceVal);
		pageMonthlyPlanningPO.CompareValueDecimalFields(extentTest, DBPlndItmPriceTtl, PlndItmPriceTtl);

		// ACTUAL ITEM PRICE:
		Report_AddStep("testcase", "ACTUAL ITEM PRICE - WEEK WISE DATA", "", "", "");
		extentTest = report.startTest("Actual Item Price");

		String ActualItemPriceTotal = data.getText("ActualItemPriceTotal");
		DBActlItmPriceTtl = sql.CLRexecuteQuery(getContext(),
				ActualItemPriceTotal.replace("LYWk", getContext().getStringProperty("LYWk"))
						.replace("WeekEnd", getContext().getStringProperty("SelectFiscalWeek"))
						.replace("WeekStart", getContext().getStringProperty("WeekStart"))
						.replace("LYWeekStrt", getContext().getStringProperty("LYWeekStrt"))
						.replace("Division", getContext().getStringProperty("Division")));
		System.out.println("DB Value: " + DBActlItmPriceTtl);

		ActlItmPriceVal = pageDailyTradingStatementGroupPO.RowWiseResult(objWeeklyTradingStatementStoreObjects,
				objWeeklyTradingStatementWOWGroupObjects.Departments,
				objWeeklyTradingStatementWOWGroupObjects.ActualItemPrice,
				objWeeklyTradingStatementWOWGroupObjects.DataTable);
		ActlItmPriceTtl = pageDailyTradingStatementGroupPO.WeekToDateUITotal(
				objWeeklyTradingStatementWOWGroupObjects.TotalTitle,
				objWeeklyTradingStatementWOWGroupObjects.ActualItemPriceTtl);

		pageMonthlyPlanningPO.CompareValueDecimalFields(extentTest, DBActlItmPriceTtl, ActlItmPriceVal);
		pageMonthlyPlanningPO.CompareValueDecimalFields(extentTest, DBActlItmPriceTtl, ActlItmPriceTtl);
	}

	// Customers
	@Test(description = "Monthly Trading Statement - WOWGroup, This Year Customer", priority = 3)
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

		String ThisYrActlCustomersTotal = data.getText("ThisYrActlCustomersTotal");
		DBThisYrCustTtl = sql.CLRexecuteQuery(getContext(),
				ThisYrActlCustomersTotal.replace("LYWk", getContext().getStringProperty("LYWk"))
						.replace("WeekEnd", getContext().getStringProperty("SelectFiscalWeek"))
						.replace("WeekStart", getContext().getStringProperty("WeekStart"))
						.replace("LYWeekStrt", getContext().getStringProperty("LYWeekStrt"))
						.replace("Division", getContext().getStringProperty("Division")));
		System.out.println("DB Value: " + DBThisYrCustTtl);

		ThisYrCustVal = pageDailyTradingStatementGroupPO.RowWiseResult(objWeeklyTradingStatementStoreObjects,
				objWeeklyTradingStatementWOWGroupObjects.Departments,
				objWeeklyTradingStatementWOWGroupObjects.ThisYrCustomer,
				objWeeklyTradingStatementWOWGroupObjects.DataTable);
		ThisYrCustTtl = pageDailyTradingStatementGroupPO.WeekToDateUITotal(
				objWeeklyTradingStatementWOWGroupObjects.TotalTitle,
				objWeeklyTradingStatementWOWGroupObjects.ThisYrCustomerTtl);

		pageMonthlyPlanningPO.CompareValues(extentTest, DBThisYrCustTtl, ThisYrCustVal);
		pageMonthlyPlanningPO.CompareValues(extentTest, DBThisYrCustTtl, ThisYrCustTtl);

		// % LAST YEAR CUSTOMER:
		Report_AddStep("testcase", "PERCENTAGE LAST YEAR CUSTOMER - WEEK WISE DATA", "", "", "");
		extentTest = report.startTest("LY Yr - Customers");

		String PctLastYrCustomersTotal = data.getText("PctLastYrCustomersTotal");
		DBLstYrCustTtl = sql.CLRexecuteQuery(getContext(),
				PctLastYrCustomersTotal.replace("LYWk", getContext().getStringProperty("LYWk"))
						.replace("WeekEnd", getContext().getStringProperty("SelectFiscalWeek"))
						.replace("WeekStart", getContext().getStringProperty("WeekStart"))
						.replace("LYWeekStrt", getContext().getStringProperty("LYWeekStrt"))
						.replace("Division", getContext().getStringProperty("Division")));
		System.out.println("DB Value: " + DBLstYrCustTtl);

		LstYrCustVal = pageDailyTradingStatementGroupPO.RowWiseResult(objWeeklyTradingStatementStoreObjects,
				objWeeklyTradingStatementWOWGroupObjects.Departments,
				objWeeklyTradingStatementWOWGroupObjects.PercentageLastCustomer,
				objWeeklyTradingStatementWOWGroupObjects.DataTable);
		LstYrCustTtl = pageDailyTradingStatementGroupPO.WeekToDateUITotal(
				objWeeklyTradingStatementWOWGroupObjects.TotalTitle,
				objWeeklyTradingStatementWOWGroupObjects.PercentageLastCustomerTtl);

		pageMonthlyPlanningPO.CompareValueDecimalFields(extentTest, DBLstYrCustTtl, LstYrCustVal);
		pageMonthlyPlanningPO.CompareValueDecimalFields(extentTest, DBLstYrCustTtl, LstYrCustTtl);

		// DATA PENETRATION CUSTOMER:
		Report_AddStep("testcase", "DATA PENETRATION CUSTOMER - WEEK WISE DATA", "", "", "");
		extentTest = report.startTest("Data Penetration - Customers");

		String DataPenetrationCustomersTotal = data.getText("DataPenetrationCustomersTotal");
		DBDataPenTtl = sql.CLRexecuteQuery(getContext(),
				DataPenetrationCustomersTotal.replace("LYWk", getContext().getStringProperty("LYWk"))
						.replace("WeekEnd", getContext().getStringProperty("SelectFiscalWeek"))
						.replace("WeekStart", getContext().getStringProperty("WeekStart"))
						.replace("LYWeekStrt", getContext().getStringProperty("LYWeekStrt"))
						.replace("Division", getContext().getStringProperty("Division")));
		System.out.println("DB Value: " + DBDataPenTtl);

		DataPenVal = pageDailyTradingStatementGroupPO.RowWiseResult(objWeeklyTradingStatementStoreObjects,
				objWeeklyTradingStatementWOWGroupObjects.Departments,
				objWeeklyTradingStatementWOWGroupObjects.DeptPenetrationCustomer,
				objWeeklyTradingStatementWOWGroupObjects.DataTable);
		DataPenTtl = pageDailyTradingStatementGroupPO.WeekToDateUITotal(
				objWeeklyTradingStatementWOWGroupObjects.TotalTitle,
				objWeeklyTradingStatementWOWGroupObjects.DeptPenetrationCustomerTtl);

		pageMonthlyPlanningPO.CompareValueDecimalFields(extentTest, DBDataPenTtl, DataPenVal);
		pageMonthlyPlanningPO.CompareValueDecimalFields(extentTest, DBDataPenTtl, DataPenTtl);
	}

	@Test(description = "Monthly Trading Statement - WOWGroup, Growth in SMS Hours", priority = 4)
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

		String ActualHoursTotal = data.getText("ActualHoursTotal");
		DBActlHrsTtl = sql.CLRexecuteQuery(getContext(),
				ActualHoursTotal.replace("LYWk", getContext().getStringProperty("LYWk"))
						.replace("WeekEnd", getContext().getStringProperty("SelectFiscalWeek"))
						.replace("WeekStart", getContext().getStringProperty("WeekStart"))
						.replace("LYWeekStrt", getContext().getStringProperty("LYWeekStrt"))
						.replace("Division", getContext().getStringProperty("Division")));
		System.out.println("DB Value: " + DBActlHrsTtl);

		ActlHrsVal = pageDailyTradingStatementGroupPO.RowWiseResult(objWeeklyTradingStatementStoreObjects,
				objWeeklyTradingStatementWOWGroupObjects.Departments,
				objWeeklyTradingStatementWOWGroupObjects.ActualHours,
				objWeeklyTradingStatementWOWGroupObjects.DataTable);
		ActlHrsTtl = pageDailyTradingStatementGroupPO.WeekToDateUITotal(
				objWeeklyTradingStatementWOWGroupObjects.TotalTitle,
				objWeeklyTradingStatementWOWGroupObjects.ActualHoursTtl);

		pageMonthlyPlanningPO.CompareValueDecimalFields(extentTest, DBActlHrsTtl, ActlHrsVal);
		pageMonthlyPlanningPO.CompareValueDecimalFields(extentTest, DBActlHrsTtl, ActlHrsTtl);

		// PLANNED HOURS:
		Report_AddStep("testcase", "PLANNED HOURS: - WEEK WISE DATA", "", "", "");
		extentTest = report.startTest("Planned Hours");

		String PlannedHoursTotal = data.getText("PlannedHoursTotal");
		DBPlndHrsTtl = sql.CLRexecuteQuery(getContext(),
				PlannedHoursTotal.replace("LYWk", getContext().getStringProperty("LYWk"))
						.replace("WeekEnd", getContext().getStringProperty("SelectFiscalWeek"))
						.replace("WeekStart", getContext().getStringProperty("WeekStart"))
						.replace("LYWeekStrt", getContext().getStringProperty("LYWeekStrt"))
						.replace("Division", getContext().getStringProperty("Division")));
		System.out.println("DB Value: " + DBPlndHrsTtl);

		PlndHrsVal = pageDailyTradingStatementGroupPO.RowWiseResult(objWeeklyTradingStatementStoreObjects,
				objWeeklyTradingStatementWOWGroupObjects.Departments,
				objWeeklyTradingStatementWOWGroupObjects.PlannedHours,
				objWeeklyTradingStatementWOWGroupObjects.DataTable);
		PlndHrsTtl = pageDailyTradingStatementGroupPO.WeekToDateUITotal(
				objWeeklyTradingStatementWOWGroupObjects.TotalTitle,
				objWeeklyTradingStatementWOWGroupObjects.PlannedHoursTtl);

		pageMonthlyPlanningPO.CompareValueDecimalFields(extentTest, DBPlndHrsTtl, PlndHrsVal);
		pageMonthlyPlanningPO.CompareValueDecimalFields(extentTest, DBPlndHrsTtl, PlndHrsTtl);

		// VARIANCE IN PLANNED HOURS:
		Report_AddStep("testcase", "VARIANCE IN PLANNED HOURS - WEEK WISE DATA", "", "", "");
		extentTest = report.startTest("Variance Planned Hours");

		String VarHoursTotal = data.getText("VarHoursTotal");
		DBVarPlndHrsTtl = sql.CLRexecuteQuery(getContext(),
				VarHoursTotal.replace("LYWk", getContext().getStringProperty("LYWk"))
						.replace("WeekEnd", getContext().getStringProperty("SelectFiscalWeek"))
						.replace("WeekStart", getContext().getStringProperty("WeekStart"))
						.replace("LYWeekStrt", getContext().getStringProperty("LYWeekStrt"))
						.replace("Division", getContext().getStringProperty("Division")));
		System.out.println("DB Value: " + DBVarPlndHrsTtl);

		VarPlndHrsVal = pageDailyTradingStatementGroupPO.RowWiseResult(objWeeklyTradingStatementStoreObjects,
				objWeeklyTradingStatementWOWGroupObjects.Departments,
				objWeeklyTradingStatementWOWGroupObjects.VarPlannedHours,
				objWeeklyTradingStatementWOWGroupObjects.DataTable);
		VarPlndHrsTtl = pageDailyTradingStatementGroupPO.WeekToDateUITotal(
				objWeeklyTradingStatementWOWGroupObjects.TotalTitle,
				objWeeklyTradingStatementWOWGroupObjects.VarPlannedHoursTtl);

		pageMonthlyPlanningPO.CompareValueDecimalFields(extentTest, DBVarPlndHrsTtl, VarPlndHrsVal);
		pageMonthlyPlanningPO.CompareValueDecimalFields(extentTest, DBVarPlndHrsTtl, VarPlndHrsTtl);
	}

	// Wages
	@Test(description = "Monthly Trading Statement - WOWGroup, Actual Wages", priority = 5)
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

		String ActualWagesTotal = data.getText("ActualWagesTotal");
		DBActlWgTtl = sql.CLRexecuteQuery(getContext(),
				ActualWagesTotal.replace("LYWk", getContext().getStringProperty("LYWk"))
						.replace("WeekEnd", getContext().getStringProperty("SelectFiscalWeek"))
						.replace("WeekStart", getContext().getStringProperty("WeekStart"))
						.replace("LYWeekStrt", getContext().getStringProperty("LYWeekStrt"))
						.replace("Division", getContext().getStringProperty("Division")));
		System.out.println("DB Value: " + DBActlWgTtl);

		ActlWgVal = pageDailyTradingStatementGroupPO.RowWiseResult(objWeeklyTradingStatementStoreObjects,
				objWeeklyTradingStatementWOWGroupObjects.Departments,
				objWeeklyTradingStatementWOWGroupObjects.ActualWages,
				objWeeklyTradingStatementWOWGroupObjects.DataTable);
		ActlWgTtl = pageDailyTradingStatementGroupPO.WeekToDateUITotal(
				objWeeklyTradingStatementWOWGroupObjects.TotalTitle,
				objWeeklyTradingStatementWOWGroupObjects.ActualWagesTtl);

		pageMonthlyPlanningPO.CompareValues(extentTest, DBActlWgTtl, ActlWgVal);
		pageMonthlyPlanningPO.CompareValues(extentTest, DBActlWgTtl, ActlWgTtl);

		// ACTUAL WAGE PERCENTAGE:
		Report_AddStep("testcase", "ACTUAL WAGE PERCENTAGE- WEEK WISE DATA", "", "", "");
		extentTest = report.startTest("Actual Wages Prcnt");

		String ActualWagePercentageTotal = data.getText("ActualWagePercentageTotal");
		DBActlWgPrcntTtl = sql.CLRexecuteQuery(getContext(),
				ActualWagePercentageTotal.replace("LYWk", getContext().getStringProperty("LYWk"))
						.replace("WeekEnd", getContext().getStringProperty("SelectFiscalWeek"))
						.replace("WeekStart", getContext().getStringProperty("WeekStart"))
						.replace("LYWeekStrt", getContext().getStringProperty("LYWeekStrt"))
						.replace("Division", getContext().getStringProperty("Division")));
		System.out.println("DB Value: " + DBActlWgPrcntTtl);

		ActlWgPrcntVal = pageDailyTradingStatementGroupPO.RowWiseResult(objWeeklyTradingStatementStoreObjects,
				objWeeklyTradingStatementWOWGroupObjects.Departments,
				objWeeklyTradingStatementWOWGroupObjects.ActualWagesPercentage,
				objWeeklyTradingStatementWOWGroupObjects.DataTable);
		ActlWgPrcntTtl = pageDailyTradingStatementGroupPO.WeekToDateUITotal(
				objWeeklyTradingStatementWOWGroupObjects.TotalTitle,
				objWeeklyTradingStatementWOWGroupObjects.ActualWagesPercentageTtl);

		pageMonthlyPlanningPO.CompareValueDecimalFields(extentTest, DBActlWgPrcntTtl, ActlWgPrcntVal);
		pageMonthlyPlanningPO.CompareValueDecimalFields(extentTest, DBActlWgPrcntTtl, ActlWgPrcntTtl);

		// BUDGET WAGE:
		if (OptionToView.contains("Budget")) {
			Report_AddStep("testcase", "ACTUAL BUDGET WAGE - WEEK WISE DATA", "", "", "");
			extentTest = report.startTest("Budget Wage");

			String BudgetWagesTotal = data.getText("BudgetWagesTotal");
			DBBdgtWgTtl = sql.CLRexecuteQuery(getContext(),
					BudgetWagesTotal.replace("LYWk", getContext().getStringProperty("LYWk"))
							.replace("WeekEnd", getContext().getStringProperty("SelectFiscalWeek"))
							.replace("WeekStart", getContext().getStringProperty("WeekStart"))
							.replace("LYWeekStrt", getContext().getStringProperty("LYWeekStrt"))
							.replace("Division", getContext().getStringProperty("Division")));
			System.out.println("DB Value: " + DBBdgtWgTtl);

			BdgtWgVal = pageDailyTradingStatementGroupPO.RowWiseResult(objWeeklyTradingStatementStoreObjects,
					objWeeklyTradingStatementWOWGroupObjects.Departments,
					objWeeklyTradingStatementWOWGroupObjects.BudgetWages,
					objWeeklyTradingStatementWOWGroupObjects.DataTable);
			BdgtWgTtl = pageDailyTradingStatementGroupPO.WeekToDateUITotal(
					objWeeklyTradingStatementWOWGroupObjects.TotalTitle,
					objWeeklyTradingStatementWOWGroupObjects.BudgetWagesTtl);

			pageMonthlyPlanningPO.CompareValues(extentTest, DBBdgtWgTtl, BdgtWgVal);
			pageMonthlyPlanningPO.CompareValues(extentTest, DBBdgtWgTtl, BdgtWgTtl);
		}
		if (OptionToView.contains("Forecast")) {
			Report_AddStep("testcase", "ACTUAL FORECAST WAGE - WEEK WISE DATA", "", "", "");
			extentTest = report.startTest("Forecast Wage");

			String ForecastWagesTotal = data.getText("ForecastWagesTotal");
			DBBdgtWgTtl = sql.CLRexecuteQuery(getContext(),
					ForecastWagesTotal.replace("LYWk", getContext().getStringProperty("LYWk"))
							.replace("WeekEnd", getContext().getStringProperty("SelectFiscalWeek"))
							.replace("WeekStart", getContext().getStringProperty("WeekStart"))
							.replace("LYWeekStrt", getContext().getStringProperty("LYWeekStrt"))
							.replace("Division", getContext().getStringProperty("Division")));
			System.out.println("DB Value: " + DBBdgtWgTtl);

			BdgtWgVal = pageDailyTradingStatementGroupPO.RowWiseResult(objWeeklyTradingStatementStoreObjects,
					objWeeklyTradingStatementWOWGroupObjects.Departments,
					objWeeklyTradingStatementWOWGroupObjects.BudgetWages,
					objWeeklyTradingStatementWOWGroupObjects.DataTable);
			BdgtWgTtl = pageDailyTradingStatementGroupPO.WeekToDateUITotal(
					objWeeklyTradingStatementWOWGroupObjects.TotalTitle,
					objWeeklyTradingStatementWOWGroupObjects.BudgetWagesTtl);

			pageMonthlyPlanningPO.CompareValues(extentTest, DBBdgtWgTtl, BdgtWgVal);
			pageMonthlyPlanningPO.CompareValues(extentTest, DBBdgtWgTtl, BdgtWgTtl);
		}

		// BUDGET WAGE PERCENTAGE:
		if (OptionToView.contains("Budget")) {
			Report_AddStep("testcase", "BUDGET WAGE PERCENTAGE- WEEK WISE DATA", "", "", "");
			extentTest = report.startTest("Budget Wage Prcnt");

			String BudgetWagePercentageTotal = data.getText("BudgetWagePercentageTotal");
			DBBdgtWgPrcntTtl = sql.CLRexecuteQuery(getContext(),
					BudgetWagePercentageTotal.replace("LYWk", getContext().getStringProperty("LYWk"))
							.replace("WeekEnd", getContext().getStringProperty("SelectFiscalWeek"))
							.replace("WeekStart", getContext().getStringProperty("WeekStart"))
							.replace("LYWeekStrt", getContext().getStringProperty("LYWeekStrt"))
							.replace("Division", getContext().getStringProperty("Division")));
			System.out.println("DB Value: " + DBBdgtWgPrcntTtl);

			BdgtWgPrcntVal = pageDailyTradingStatementGroupPO.RowWiseResult(objWeeklyTradingStatementStoreObjects,
					objWeeklyTradingStatementWOWGroupObjects.Departments,
					objWeeklyTradingStatementWOWGroupObjects.BudgetWagesPercentage,
					objWeeklyTradingStatementWOWGroupObjects.DataTable);
			BdgtWgPrcntTtl = pageDailyTradingStatementGroupPO.WeekToDateUITotal(
					objWeeklyTradingStatementWOWGroupObjects.TotalTitle,
					objWeeklyTradingStatementWOWGroupObjects.BudgetWagesPercentageTtl);

			pageMonthlyPlanningPO.CompareValueDecimalFields(extentTest, DBBdgtWgPrcntTtl, BdgtWgPrcntVal);
			pageMonthlyPlanningPO.CompareValueDecimalFields(extentTest, DBBdgtWgPrcntTtl, BdgtWgPrcntTtl);
		}
		if (OptionToView.contains("Forecast")) {
			Report_AddStep("testcase", "FORECAST WAGE PERCENTAGE- WEEK WISE DATA", "", "", "");
			extentTest = report.startTest("Forecast Wage Prcnt");

			String ForecastWagePercentageTotal = data.getText("ForecastWagePercentageTotal");
			DBBdgtWgPrcntTtl = sql.CLRexecuteQuery(getContext(),
					ForecastWagePercentageTotal.replace("LYWk", getContext().getStringProperty("LYWk"))
							.replace("WeekEnd", getContext().getStringProperty("SelectFiscalWeek"))
							.replace("WeekStart", getContext().getStringProperty("WeekStart"))
							.replace("LYWeekStrt", getContext().getStringProperty("LYWeekStrt"))
							.replace("Division", getContext().getStringProperty("Division")));
			System.out.println("DB Value: " + DBBdgtWgPrcntTtl);

			BdgtWgPrcntVal = pageDailyTradingStatementGroupPO.RowWiseResult(objWeeklyTradingStatementStoreObjects,
					objWeeklyTradingStatementWOWGroupObjects.Departments,
					objWeeklyTradingStatementWOWGroupObjects.BudgetWagesPercentage,
					objWeeklyTradingStatementWOWGroupObjects.DataTable);
			BdgtWgPrcntTtl = pageDailyTradingStatementGroupPO.WeekToDateUITotal(
					objWeeklyTradingStatementWOWGroupObjects.TotalTitle,
					objWeeklyTradingStatementWOWGroupObjects.BudgetWagesPercentageTtl);

			pageMonthlyPlanningPO.CompareValueDecimalFields(extentTest, DBBdgtWgPrcntTtl, BdgtWgPrcntVal);
			pageMonthlyPlanningPO.CompareValueDecimalFields(extentTest, DBBdgtWgPrcntTtl, BdgtWgPrcntTtl);
		}

		// PLANNED CPH:
		Report_AddStep("testcase", "PLANNED CPH - WEEK WISE DATA", "", "", "");
		extentTest = report.startTest("Planned CPH");

		String PlannedCPHTotal = data.getText("PlannedCPHTotal");
		DBPlndCPHTtl = sql.CLRexecuteQuery(getContext(),
				PlannedCPHTotal.replace("LYWk", getContext().getStringProperty("LYWk"))
						.replace("WeekEnd", getContext().getStringProperty("SelectFiscalWeek"))
						.replace("WeekStart", getContext().getStringProperty("WeekStart"))
						.replace("LYWeekStrt", getContext().getStringProperty("LYWeekStrt"))
						.replace("Division", getContext().getStringProperty("Division")));
		System.out.println("DB Value: " + DBPlndCPHTtl);

		PlndCPHVal = pageDailyTradingStatementGroupPO.RowWiseResult(objWeeklyTradingStatementStoreObjects,
				objWeeklyTradingStatementWOWGroupObjects.Departments,
				objWeeklyTradingStatementWOWGroupObjects.PlannedCPHWages,
				objWeeklyTradingStatementWOWGroupObjects.DataTable);
		PlndCPHTtl = pageDailyTradingStatementGroupPO.WeekToDateUITotal(
				objWeeklyTradingStatementWOWGroupObjects.TotalTitle,
				objWeeklyTradingStatementWOWGroupObjects.PlannedCPHWagesTtl);

		pageMonthlyPlanningPO.CompareValueDecimalFields(extentTest, DBPlndCPHTtl, PlndCPHVal);
		pageMonthlyPlanningPO.CompareValueDecimalFields(extentTest, DBPlndCPHTtl, PlndCPHTtl);

		// PLANNED WAGE:
		Report_AddStep("testcase", "PLANNED WAGE - WEEK WISE DATA", "", "", "");
		extentTest = report.startTest("Planned Wage");

		String PlannedWagesTotal = data.getText("PlannedWagesTotal");
		DBPlndWgTtl = sql.CLRexecuteQuery(getContext(),
				PlannedWagesTotal.replace("LYWk", getContext().getStringProperty("LYWk"))
						.replace("WeekEnd", getContext().getStringProperty("SelectFiscalWeek"))
						.replace("WeekStart", getContext().getStringProperty("WeekStart"))
						.replace("LYWeekStrt", getContext().getStringProperty("LYWeekStrt"))
						.replace("Division", getContext().getStringProperty("Division")));
		System.out.println("DB Value: " + DBPlndWgTtl);

		PlndWgVal = pageDailyTradingStatementGroupPO.RowWiseResult(objWeeklyTradingStatementStoreObjects,
				objWeeklyTradingStatementWOWGroupObjects.Departments,
				objWeeklyTradingStatementWOWGroupObjects.PlannedWages,
				objWeeklyTradingStatementWOWGroupObjects.DataTable);
		PlndWgTtl = pageDailyTradingStatementGroupPO.WeekToDateUITotal(
				objWeeklyTradingStatementWOWGroupObjects.TotalTitle,
				objWeeklyTradingStatementWOWGroupObjects.PlannedWagesTtl);

		pageMonthlyPlanningPO.CompareValues(extentTest, DBPlndWgTtl, PlndWgVal);
		pageMonthlyPlanningPO.CompareValues(extentTest, DBPlndWgTtl, PlndWgTtl);

		// PLANNED WAGE PERCENTAGE:
		Report_AddStep("testcase", "PLANNED WAGE PERCENTAGE - WEEK WISE DATA", "", "", "");
		extentTest = report.startTest("Planned Wage Prcnt");

		String PlannedWagesPercentageTotal = data.getText("PlannedWagesPercentageTotal");
		DBPlndWgPrcntTtl = sql.CLRexecuteQuery(getContext(),
				PlannedWagesPercentageTotal.replace("LYWk", getContext().getStringProperty("LYWk"))
						.replace("WeekEnd", getContext().getStringProperty("SelectFiscalWeek"))
						.replace("WeekStart", getContext().getStringProperty("WeekStart"))
						.replace("LYWeekStrt", getContext().getStringProperty("LYWeekStrt"))
						.replace("Division", getContext().getStringProperty("Division")));
		System.out.println("DB Value: " + DBPlndWgPrcntTtl);

		PlndWgPrcntVal = pageDailyTradingStatementGroupPO.RowWiseResult(objWeeklyTradingStatementStoreObjects,
				objWeeklyTradingStatementWOWGroupObjects.Departments,
				objWeeklyTradingStatementWOWGroupObjects.PlannedWagesPercentage,
				objWeeklyTradingStatementWOWGroupObjects.DataTable);
		PlndWgPrcntTtl = pageDailyTradingStatementGroupPO.WeekToDateUITotal(
				objWeeklyTradingStatementWOWGroupObjects.TotalTitle,
				objWeeklyTradingStatementWOWGroupObjects.PlannedWagesPercentageTtl);

		pageMonthlyPlanningPO.CompareValueDecimalFields(extentTest, DBPlndWgPrcntTtl, PlndWgPrcntVal);
		pageMonthlyPlanningPO.CompareValueDecimalFields(extentTest, DBPlndWgPrcntTtl, PlndWgPrcntTtl);

		// CPH Wages:
		Report_AddStep("testcase", "CPH WAGES - WEEK WISE DATA", "", "", "");
		extentTest = report.startTest("CPH Wages");

		String ActualCPHTotal = data.getText("ActualCPHTotal");
		DBCPHWgTtl = sql.CLRexecuteQuery(getContext(),
				ActualCPHTotal.replace("LYWk", getContext().getStringProperty("LYWk"))
						.replace("WeekEnd", getContext().getStringProperty("SelectFiscalWeek"))
						.replace("WeekStart", getContext().getStringProperty("WeekStart"))
						.replace("LYWeekStrt", getContext().getStringProperty("LYWeekStrt"))
						.replace("Division", getContext().getStringProperty("Division")));
		System.out.println("DB Value: " + DBCPHWgTtl);

		CPHWgVal = pageDailyTradingStatementGroupPO.RowWiseResult(objWeeklyTradingStatementStoreObjects,
				objWeeklyTradingStatementWOWGroupObjects.Departments, objWeeklyTradingStatementWOWGroupObjects.CPHWages,
				objWeeklyTradingStatementWOWGroupObjects.DataTable);
		CPHWgTtl = pageDailyTradingStatementGroupPO.WeekToDateUITotal(
				objWeeklyTradingStatementWOWGroupObjects.TotalTitle,
				objWeeklyTradingStatementWOWGroupObjects.CPHWagesTtl);

		pageMonthlyPlanningPO.CompareValueDecimalFields(extentTest, DBCPHWgTtl, CPHWgVal);
		pageMonthlyPlanningPO.CompareValueDecimalFields(extentTest, DBCPHWgTtl, CPHWgTtl);

		// VARIANCE BUDGET WAGES:
		if (OptionToView.contains("Budget")) {
			Report_AddStep("testcase", "VARIANCE BUDGET WAGES - WEEK WISE DATA", "", "", "");
			extentTest = report.startTest("Variance Budget Wages");

			String VarWagesTotal = data.getText("VarWagesTotal");
			DBVarBdgtWgTtl = sql.CLRexecuteQuery(getContext(),
					VarWagesTotal.replace("LYWk", getContext().getStringProperty("LYWk"))
							.replace("WeekEnd", getContext().getStringProperty("SelectFiscalWeek"))
							.replace("WeekStart", getContext().getStringProperty("WeekStart"))
							.replace("LYWeekStrt", getContext().getStringProperty("LYWeekStrt"))
							.replace("Division", getContext().getStringProperty("Division")));
			System.out.println("DB Value: " + DBVarBdgtWgTtl);

			VarBdgtWgVal = pageDailyTradingStatementGroupPO.RowWiseResult(objWeeklyTradingStatementStoreObjects,
					objWeeklyTradingStatementWOWGroupObjects.Departments,
					objWeeklyTradingStatementWOWGroupObjects.VarBudgetWages,
					objWeeklyTradingStatementWOWGroupObjects.DataTable);
			VarBdgtWgTtl = pageDailyTradingStatementGroupPO.WeekToDateUITotal(
					objWeeklyTradingStatementWOWGroupObjects.TotalTitle,
					objWeeklyTradingStatementWOWGroupObjects.VarBudgetWagesTtl);

			pageMonthlyPlanningPO.CompareValueDecimalFields(extentTest, DBVarBdgtWgTtl, VarBdgtWgVal);
			pageMonthlyPlanningPO.CompareValueDecimalFields(extentTest, DBVarBdgtWgTtl, VarBdgtWgTtl);
		}
		if (OptionToView.contains("Forecast")) {
			Report_AddStep("testcase", "VARIANCE FORECAST WAGES - WEEK WISE DATA", "", "", "");
			extentTest = report.startTest("Variance Forecast Wages");

			String VarFcstWagesTotal = data.getText("VarFcstWagesTotal");
			DBVarBdgtWgTtl = sql.CLRexecuteQuery(getContext(),
					VarFcstWagesTotal.replace("LYWk", getContext().getStringProperty("LYWk"))
							.replace("WeekEnd", getContext().getStringProperty("SelectFiscalWeek"))
							.replace("WeekStart", getContext().getStringProperty("WeekStart"))
							.replace("LYWeekStrt", getContext().getStringProperty("LYWeekStrt"))
							.replace("Division", getContext().getStringProperty("Division")));
			System.out.println("DB Value: " + DBVarBdgtWgTtl);

			VarBdgtWgVal = pageDailyTradingStatementGroupPO.RowWiseResult(objWeeklyTradingStatementStoreObjects,
					objWeeklyTradingStatementWOWGroupObjects.Departments,
					objWeeklyTradingStatementWOWGroupObjects.VarBudgetWages,
					objWeeklyTradingStatementWOWGroupObjects.DataTable);
			VarBdgtWgTtl = pageDailyTradingStatementGroupPO.WeekToDateUITotal(
					objWeeklyTradingStatementWOWGroupObjects.TotalTitle,
					objWeeklyTradingStatementWOWGroupObjects.VarBudgetWagesTtl);

			pageMonthlyPlanningPO.CompareValueDecimalFields(extentTest, DBVarBdgtWgTtl, VarBdgtWgVal);
			pageMonthlyPlanningPO.CompareValueDecimalFields(extentTest, DBVarBdgtWgTtl, VarBdgtWgTtl);
		}
		
		// Allowed Wages Validation
				if (OptionToView.contains("Budget")) {
					Report_AddStep("testcase", "Allowed Wages Validation", "", "", "");
					extentTest = report.startTest("Allowed Wages");

					String AllowedWagesTotal = data.getText("AllowedWagesTotal");
					DBAllowedWgTtl = sql.CLRexecuteQuery(getContext(),
							AllowedWagesTotal.replace("LYWk", getContext().getStringProperty("LYWk"))
									.replace("WeekEnd", getContext().getStringProperty("SelectFiscalWeek"))
									.replace("WeekStart", getContext().getStringProperty("WeekStart"))
									.replace("LYWeekStrt", getContext().getStringProperty("LYWeekStrt"))
									.replace("Division", getContext().getStringProperty("Division")));
					System.out.println("DB Value: " + DBAllowedWgTtl);

					AllowedWgVal = pageDailyTradingStatementGroupPO.RowWiseResult(objWeeklyTradingStatementStoreObjects,
							objWeeklyTradingStatementWOWGroupObjects.Departments,
							objWeeklyTradingStatementWOWGroupObjects.AllowedWages,
							objWeeklyTradingStatementWOWGroupObjects.DataTable);
					AllowedWgTtl = pageDailyTradingStatementGroupPO.WeekToDateUITotal(
							objWeeklyTradingStatementWOWGroupObjects.TotalTitle,
							objWeeklyTradingStatementWOWGroupObjects.AllowedWagesTtl);

					pageMonthlyPlanningPO.CompareValueDecimalFields(extentTest, DBAllowedWgTtl, AllowedWgVal);
					pageMonthlyPlanningPO.CompareValueDecimalFields(extentTest, DBAllowedWgTtl, AllowedWgTtl);
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
		objWeeklyTradingStatementWOWGroupObjects = PageFactory.initElements(this.getDriver(),
				WeeklyTradingStatementWOWGroupObjects.class);
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