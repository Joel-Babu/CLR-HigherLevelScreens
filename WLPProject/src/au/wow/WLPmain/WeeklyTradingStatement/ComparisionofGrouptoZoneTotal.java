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
import au.wow.WLPmain.objects.WeeklyTradingStatementStoreObjects;
import au.wow.WLPmain.pages.CommonFunctions;
import au.wow.WLPmain.pages.DBComparision;
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

public class ComparisionofGrouptoZoneTotal extends TestBase {
	protected Logger log = LogManager.getLogger(ComparisionofGrouptoZoneTotal.class);
	TestStatus status;
	ExtentReportsScreenshotCode ScreenShotUtil;
	SQLWrapper sql = new SQLWrapper(log);
	SQLWrapper sql1 = new SQLWrapper(log);
	WoWLoginPage loginPage;
	CommonRepository CommonFunc;
	WeeklyPlanningStoreObjects objWeeklyPlanningStoreObjects;
	WeeklyTradingStatementStoreObjects objWeeklyTradingStatementStoreObjects;
	DailyTradingStatementGroupObjects objDailyTradingStatementGroupObjects;
	DailyTradingStatementStorePage pageDailyTradingStatementStorePO;
	WeeklyPlanningStorePage pageWeeklyPlanningPO;
	DBComparision pageDBComparisionPO;
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
			DBPlndWgPrcntVal, DBCPHWgTtl, DBCPHWgVal, DBVarBdgtWgTtl, DBVarBdgtWgVal;
	String OptionToView;

	public ComparisionofGrouptoZoneTotal() {
		super.log = log;
	}

	@Test(priority = 0)
	public void LogintoCLRApplication() throws Exception {
		loginPage = new WoWLoginPage().createPage(getDriver(), getContext(), log, status, data, report, extentTest,
				WoWLoginPage.class);
		pageWeeklyPlanningPO = loginPage.navToWeeklyPlanningStorePg(getContext().getStringProperty("store"));
	}

	// Sales
	@Test(description = "Weekly Trading Statement - Group, SalesColumnValidation", priority = 1)
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
				objWeeklyPlanningStoreObjects.screenToView, getContext().getStringProperty("WTSGroup"));
		pageWeeklyPlanningPO.verifyPageName(extentTest, objWeeklyPlanningStoreObjects.DataFrame,
				objWeeklyPlanningStoreObjects.pageName, getContext().getStringProperty("WTSGroup"));

		OptionToView = getContext().getStringProperty("OptionToView");

		// ACTUAL SALES:
		Report_AddStep("testcase", " ACTUAL SALES - WEEK WISE DATA", "", "", "");
		extentTest.log(LogStatus.INFO, "ACTUAL SALES - WEEK WISE DATA");

		String ActualSales = data1.getText("ActualSales");
		DBActlVal = sql.CLRexecuteQuery(getContext(),
				ActualSales.replace("Region", getContext().getStringProperty("Region"))
						.replace("Area", getContext().getStringProperty("Area"))
						.replace("Zone", getContext().getStringProperty("Zone"))
						.replace("LYWk", getContext().getStringProperty("LYWk"))
						.replace("Week", getContext().getStringProperty("SelectFiscalWeek"))
						.replace("Division", getContext().getStringProperty("Division")));
		System.out.println("DB Value: " + DBActlVal);

		String ActualSalesTotal = data.getText("ActualSalesTotal");
		DBActlTtl = sql.CLRexecuteQuery(getContext(),
				ActualSalesTotal.replace("Region", getContext().getStringProperty("Region"))
						.replace("Area", getContext().getStringProperty("Area"))
						.replace("Zone", getContext().getStringProperty("Zone"))
						.replace("LYWk", getContext().getStringProperty("LYWk"))
						.replace("store", getContext().getStringProperty("store"))
						.replace("Week", getContext().getStringProperty("SelectFiscalWeek"))
						.replace("Division", getContext().getStringProperty("Division")));
		System.out.println("DB Value: " + DBActlTtl);

		pageDBComparisionPO.CompareValues(extentTest, DBActlVal, DBActlTtl, "Actual Sales");

		// PLANNED SALES:
		Report_AddStep("testcase", " PLANNED SALES - WEEK WISE DATA", "", "", "");
		extentTest.log(LogStatus.INFO, "PLANNED SALES - WEEK WISE DATA");

		String PlannedSales = data1.getText("PlannedSales");
		DBPlndVal = sql.CLRexecuteQuery(getContext(),
				PlannedSales.replace("Region", getContext().getStringProperty("Region"))
						.replace("Area", getContext().getStringProperty("Area"))
						.replace("Zone", getContext().getStringProperty("Zone"))
						.replace("LYWk", getContext().getStringProperty("LYWk"))
						.replace("Week", getContext().getStringProperty("SelectFiscalWeek"))
						.replace("Division", getContext().getStringProperty("Division")));
		System.out.println("DB Value: " + DBPlndVal);

		String PlannedSalesTotal = data.getText("PlannedSalesTotal");
		DBPlndTtl = sql.CLRexecuteQuery(getContext(),
				PlannedSalesTotal.replace("Region", getContext().getStringProperty("Region"))
						.replace("Area", getContext().getStringProperty("Area"))
						.replace("Zone", getContext().getStringProperty("Zone"))
						.replace("LYWk", getContext().getStringProperty("LYWk"))
						.replace("store", getContext().getStringProperty("store"))
						.replace("Week", getContext().getStringProperty("SelectFiscalWeek"))
						.replace("Division", getContext().getStringProperty("Division")));
		System.out.println("DB Value: " + DBPlndTtl);

		pageDBComparisionPO.CompareValues(extentTest, DBPlndVal, DBPlndTtl, "Planned Sales");

		// VARIANCE IN PLANNED SALES:
		Report_AddStep("testcase", " VARIANCE IN PLANNED SALES - WEEK WISE DATA", "", "", "");
		extentTest.log(LogStatus.INFO, " VARIANCE IN PLANNED SALES - WEEK WISE DATA");

		String VarSales = data1.getText("VarSales");
		DBVarPlndVal = sql.CLRexecuteQuery(getContext(),
				VarSales.replace("Region", getContext().getStringProperty("Region"))
						.replace("Area", getContext().getStringProperty("Area"))
						.replace("Zone", getContext().getStringProperty("Zone"))
						.replace("LYWk", getContext().getStringProperty("LYWk"))
						.replace("Week", getContext().getStringProperty("SelectFiscalWeek"))
						.replace("Division", getContext().getStringProperty("Division")));
		System.out.println("DB Value: " + DBVarPlndVal);

		String VarSalesTotal = data.getText("VarSalesTotal");
		DBVarPlndTtl = sql.CLRexecuteQuery(getContext(),
				VarSalesTotal.replace("Region", getContext().getStringProperty("Region"))
						.replace("Area", getContext().getStringProperty("Area"))
						.replace("Zone", getContext().getStringProperty("Zone"))
						.replace("LYWk", getContext().getStringProperty("LYWk"))
						.replace("store", getContext().getStringProperty("store"))
						.replace("Week", getContext().getStringProperty("SelectFiscalWeek"))
						.replace("Division", getContext().getStringProperty("Division")));
		System.out.println("DB Value: " + DBVarPlndTtl);

		pageDBComparisionPO.CompareValues(extentTest, DBVarPlndVal, DBVarPlndTtl, "Variance Sales");

		// LAST YEAR SALES:
		Report_AddStep("testcase", " LAST YEAR SALES - WEEK WISE DATA", "", "", "");
		extentTest.log(LogStatus.INFO, " LAST YEAR SALES - WEEK WISE DATA");

		String LYSales = data1.getText("LYSales");
		DBLYSlsVal = sql.CLRexecuteQuery(getContext(),
				LYSales.replace("Region", getContext().getStringProperty("Region"))
						.replace("Area", getContext().getStringProperty("Area"))
						.replace("Zone", getContext().getStringProperty("Zone"))
						.replace("LYWk", getContext().getStringProperty("LYWk"))
						.replace("Week", getContext().getStringProperty("SelectFiscalWeek"))
						.replace("Division", getContext().getStringProperty("Division")));
		System.out.println("DB Value: " + DBLYSlsVal);

		String LYSalesTotal = data.getText("LYSalesTotal");
		DBLYSlsTtl = sql.CLRexecuteQuery(getContext(),
				LYSalesTotal.replace("Region", getContext().getStringProperty("Region"))
						.replace("Area", getContext().getStringProperty("Area"))
						.replace("Zone", getContext().getStringProperty("Zone"))
						.replace("LYWk", getContext().getStringProperty("LYWk"))
						.replace("store", getContext().getStringProperty("store"))
						.replace("Week", getContext().getStringProperty("SelectFiscalWeek"))
						.replace("Division", getContext().getStringProperty("Division")));
		System.out.println("DB Value: " + DBLYSlsTtl);

		pageDBComparisionPO.CompareValues(extentTest, DBLYSlsVal, DBLYSlsTtl, "Last Year Sales");

		// VARIANCE IN LAST YEAR SALES:
		Report_AddStep("testcase", " VARIANCE IN LAST YEAR SALES - WEEK WISE DATA", "", "", "");
		extentTest.log(LogStatus.INFO, " VARIANCE IN LAST YEAR SALES - WEEK WISE DATA");

		String LYSalesPct = data1.getText("LYSalesPct");
		DBVarLYSlsVal = sql.CLRexecuteQuery(getContext(),
				LYSalesPct.replace("Region", getContext().getStringProperty("Region"))
						.replace("Area", getContext().getStringProperty("Area"))
						.replace("Zone", getContext().getStringProperty("Zone"))
						.replace("LYWk", getContext().getStringProperty("LYWk"))
						.replace("Week", getContext().getStringProperty("SelectFiscalWeek"))
						.replace("Division", getContext().getStringProperty("Division")));
		System.out.println("DB Value: " + DBVarLYSlsVal);

		String LYSalesPctTotal = data.getText("LYSalesPctTotal");
		DBVarLYSlsTtl = sql.CLRexecuteQuery(getContext(),
				LYSalesPctTotal.replace("Region", getContext().getStringProperty("Region"))
						.replace("Area", getContext().getStringProperty("Area"))
						.replace("Zone", getContext().getStringProperty("Zone"))
						.replace("LYWk", getContext().getStringProperty("LYWk"))
						.replace("store", getContext().getStringProperty("store"))
						.replace("Week", getContext().getStringProperty("SelectFiscalWeek"))
						.replace("Division", getContext().getStringProperty("Division")));
		System.out.println("DB Value: " + DBVarLYSlsTtl);

		pageDBComparisionPO.CompareValues(extentTest, DBVarLYSlsVal, DBVarLYSlsTtl, "Variance in LY Sales");

		// BUDGET SALES:
		if (OptionToView.contains("Budget")) {
			Report_AddStep("testcase", "BUDGET SALES - WEEK WISE DATA", "", "", "");
			extentTest.log(LogStatus.INFO, "BUDGET SALES - WEEK WISE DATA");

			String BudgetSales = data1.getText("BudgetSales");
			DBBdgtVal = sql.CLRexecuteQuery(getContext(),
					BudgetSales.replace("Region", getContext().getStringProperty("Region"))
							.replace("Area", getContext().getStringProperty("Area"))
							.replace("Zone", getContext().getStringProperty("Zone"))
							.replace("LYWk", getContext().getStringProperty("LYWk"))
							.replace("Week", getContext().getStringProperty("SelectFiscalWeek"))
							.replace("Division", getContext().getStringProperty("Division")));
			System.out.println("DB Value: " + DBBdgtVal);

			String BudgetSalesTotal = data.getText("BudgetSalesTotal");
			DBBdgtTtl = sql.CLRexecuteQuery(getContext(),
					BudgetSalesTotal.replace("Region", getContext().getStringProperty("Region"))
							.replace("Area", getContext().getStringProperty("Area"))
							.replace("Zone", getContext().getStringProperty("Zone"))
							.replace("LYWk", getContext().getStringProperty("LYWk"))
							.replace("store", getContext().getStringProperty("store"))
							.replace("Week", getContext().getStringProperty("SelectFiscalWeek"))
							.replace("Division", getContext().getStringProperty("Division")));
			System.out.println("DB Value: " + DBBdgtTtl);

			pageDBComparisionPO.CompareValues(extentTest, DBBdgtVal, DBBdgtTtl, "Budget Sales");
		}
		if (OptionToView.contains("Forecast")) {
			Report_AddStep("testcase", "FORECAST SALES - WEEK WISE DATA", "", "", "");
			extentTest.log(LogStatus.INFO, "FORECAST SALES - WEEK WISE DATA");

			String ForecastSales = data1.getText("ForecastSales");
			DBBdgtVal = sql.CLRexecuteQuery(getContext(),
					ForecastSales.replace("Region", getContext().getStringProperty("Region"))
							.replace("Area", getContext().getStringProperty("Area"))
							.replace("Zone", getContext().getStringProperty("Zone"))
							.replace("LYWk", getContext().getStringProperty("LYWk"))
							.replace("Week", getContext().getStringProperty("SelectFiscalWeek"))
							.replace("Division", getContext().getStringProperty("Division")));
			System.out.println("DB Value: " + DBBdgtVal);

			String ForecastSalesTotal = data.getText("ForecastSalesTotal");
			DBBdgtTtl = sql.CLRexecuteQuery(getContext(),
					ForecastSalesTotal.replace("Region", getContext().getStringProperty("Region"))
							.replace("Area", getContext().getStringProperty("Area"))
							.replace("Zone", getContext().getStringProperty("Zone"))
							.replace("LYWk", getContext().getStringProperty("LYWk"))
							.replace("store", getContext().getStringProperty("store"))
							.replace("Week", getContext().getStringProperty("SelectFiscalWeek"))
							.replace("Division", getContext().getStringProperty("Division")));
			System.out.println("DB Value: " + DBBdgtTtl);

			pageDBComparisionPO.CompareValues(extentTest, DBBdgtVal, DBBdgtTtl, "Forecast Sales");
		}

		// VARIANCE IN BUDGET SALES:
		if (OptionToView.contains("Budget")) {
			Report_AddStep("testcase", "VARIANCE IN BUDGET SALES - WEEK WISE DATA", "", "", "");
			extentTest.log(LogStatus.INFO, "VARIANCE IN BUDGET SALES - WEEK WISE DATA");

			String VarBudgetSales = data1.getText("VarBudgetSales");
			DBVarBdgtVal = sql.CLRexecuteQuery(getContext(),
					VarBudgetSales.replace("Region", getContext().getStringProperty("Region"))
							.replace("Area", getContext().getStringProperty("Area"))
							.replace("Zone", getContext().getStringProperty("Zone"))
							.replace("LYWk", getContext().getStringProperty("LYWk"))
							.replace("Week", getContext().getStringProperty("SelectFiscalWeek"))
							.replace("Division", getContext().getStringProperty("Division")));
			System.out.println("DB Value: " + DBVarBdgtVal);

			String VarBudgetSalesTotal = data.getText("VarBudgetSalesTotal");
			DBVarBdgtTtl = sql.CLRexecuteQuery(getContext(),
					VarBudgetSalesTotal.replace("Region", getContext().getStringProperty("Region"))
							.replace("Area", getContext().getStringProperty("Area"))
							.replace("Zone", getContext().getStringProperty("Zone"))
							.replace("LYWk", getContext().getStringProperty("LYWk"))
							.replace("store", getContext().getStringProperty("store"))
							.replace("Week", getContext().getStringProperty("SelectFiscalWeek"))
							.replace("Division", getContext().getStringProperty("Division")));
			System.out.println("DB Value: " + DBVarBdgtTtl);

			pageDBComparisionPO.CompareValues(extentTest, DBVarBdgtVal, DBVarBdgtTtl, "Variance - Budget Sales");
		}
		if (OptionToView.contains("Forecast")) {
			Report_AddStep("testcase", "VARIANCE IN FORECAST SALES - WEEK WISE DATA", "", "", "");
			extentTest.log(LogStatus.INFO, "VARIANCE IN FORECAST SALES - WEEK WISE DATA");

			String VarFcstSales = data1.getText("VarFcstSales");
			DBVarBdgtVal = sql.CLRexecuteQuery(getContext(),
					VarFcstSales.replace("Region", getContext().getStringProperty("Region"))
							.replace("Area", getContext().getStringProperty("Area"))
							.replace("Zone", getContext().getStringProperty("Zone"))
							.replace("LYWk", getContext().getStringProperty("LYWk"))
							.replace("Week", getContext().getStringProperty("SelectFiscalWeek"))
							.replace("Division", getContext().getStringProperty("Division")));
			System.out.println("DB Value: " + DBVarBdgtVal);

			String VarFcstSalesTotal = data.getText("VarFcstSalesTotal");
			DBVarBdgtTtl = sql.CLRexecuteQuery(getContext(),
					VarFcstSalesTotal.replace("Region", getContext().getStringProperty("Region"))
							.replace("Area", getContext().getStringProperty("Area"))
							.replace("Zone", getContext().getStringProperty("Zone"))
							.replace("LYWk", getContext().getStringProperty("LYWk"))
							.replace("store", getContext().getStringProperty("store"))
							.replace("Week", getContext().getStringProperty("SelectFiscalWeek"))
							.replace("Division", getContext().getStringProperty("Division")));
			System.out.println("DB Value: " + DBVarBdgtTtl);

			pageDBComparisionPO.CompareValues(extentTest, DBVarBdgtVal, DBVarBdgtTtl, "Variance - Forecast Sales");
		}

		// AVERAGE BASKET SALES:
		Report_AddStep("testcase", "AVERAGE BASKET SALES - WEEK WISE DATA", "", "", "");
		extentTest.log(LogStatus.INFO, "AVERAGE BASKET SALES - WEEK WISE DATA");

		String AvgBasketSales = data1.getText("AvgBasketSales");
		DBAvgBsktVal = sql.CLRexecuteQuery(getContext(),
				AvgBasketSales.replace("Region", getContext().getStringProperty("Region"))
						.replace("Area", getContext().getStringProperty("Area"))
						.replace("Zone", getContext().getStringProperty("Zone"))
						.replace("LYWk", getContext().getStringProperty("LYWk"))
						.replace("Week", getContext().getStringProperty("SelectFiscalWeek"))
						.replace("Division", getContext().getStringProperty("Division")));
		System.out.println("DB Value: " + DBAvgBsktVal);

		String AvgBasketSalesTotal = data.getText("AvgBasketSalesTotal");
		DBAvgBsktTtl = sql.CLRexecuteQuery(getContext(),
				AvgBasketSalesTotal.replace("Region", getContext().getStringProperty("Region"))
						.replace("Area", getContext().getStringProperty("Area"))
						.replace("Zone", getContext().getStringProperty("Zone"))
						.replace("LYWk", getContext().getStringProperty("LYWk"))
						.replace("store", getContext().getStringProperty("store"))
						.replace("Week", getContext().getStringProperty("SelectFiscalWeek"))
						.replace("Division", getContext().getStringProperty("Division")));
		System.out.println("DB Value: " + DBAvgBsktTtl);

		pageDBComparisionPO.CompareValues(extentTest, DBAvgBsktVal, DBAvgBsktTtl, "Average Basket - Sales");

		// MIX SALES:
		Report_AddStep("testcase", "MIX SALES - WEEK WISE DATA", "", "", "");
		extentTest.log(LogStatus.INFO, "MIX SALES - WEEK WISE DATA");

		String WkyMixSales = data1.getText("WkyMixSales");
		DBMixVal = sql.CLRexecuteQuery(getContext(),
				WkyMixSales.replace("Region", getContext().getStringProperty("Region"))
						.replace("Area", getContext().getStringProperty("Area"))
						.replace("Zone", getContext().getStringProperty("Zone"))
						.replace("LYWk", getContext().getStringProperty("LYWk"))
						.replace("Week", getContext().getStringProperty("SelectFiscalWeek"))
						.replace("Division", getContext().getStringProperty("Division")));
		System.out.println("DB Value: " + DBMixVal);

		String WkyMixSalesTotal = data.getText("WkyMixSalesTotal");
		DBMixTtl = sql.CLRexecuteQuery(getContext(),
				WkyMixSalesTotal.replace("Region", getContext().getStringProperty("Region"))
						.replace("Area", getContext().getStringProperty("Area"))
						.replace("Zone", getContext().getStringProperty("Zone"))
						.replace("LYWk", getContext().getStringProperty("LYWk"))
						.replace("store", getContext().getStringProperty("store"))
						.replace("Week", getContext().getStringProperty("SelectFiscalWeek"))
						.replace("Division", getContext().getStringProperty("Division")));
		System.out.println("DB Value: " + DBMixTtl);

		pageDBComparisionPO.CompareValues(extentTest, DBMixVal, DBMixTtl, "Mix Sales");
	}

	// Items and Item Price
	@Test(description = "Weekly Trading Statement - Group, Planned Item", priority = 2)
	public void ItemsColumnValidation() throws Exception {
		pageWeeklyPlanningPO = new WeeklyPlanningStorePage().createPage(getDriver(), getContext(), log, status, data,
				getReport(), getReportLogger(), WeeklyPlanningStorePage.class);
		pageDailyTradingStatementGroupPO = new MonthlyPlanningGroupPage().createPage(getDriver(), getContext(), log,
				status, data, getReport(), getReportLogger(), DailyTradingStatementGroupPage.class);
		TestStatus status = getTestStatus();
		System.out.println("Entering into TestCase : Item View");

		// PLANNED ITEMS:
		Report_AddStep("testcase", "PLANNED ITEMS - WEEK WISE DATA", "", "", "");
		extentTest.log(LogStatus.INFO, "PLANNED ITEMS - WEEK WISE DATA");

		String PlannedItems = data1.getText("PlannedItems");
		DBPlndItmVal = sql.CLRexecuteQuery(getContext(),
				PlannedItems.replace("Region", getContext().getStringProperty("Region"))
						.replace("Area", getContext().getStringProperty("Area"))
						.replace("Zone", getContext().getStringProperty("Zone"))
						.replace("LYWk", getContext().getStringProperty("LYWk"))
						.replace("Week", getContext().getStringProperty("SelectFiscalWeek"))
						.replace("Division", getContext().getStringProperty("Division")));
		System.out.println("DB Value: " + DBPlndItmVal);

		String PlannedItemsTotal = data.getText("PlannedItemsTotal");
		DBPlndItmTtl = sql.CLRexecuteQuery(getContext(),
				PlannedItemsTotal.replace("Region", getContext().getStringProperty("Region"))
						.replace("Area", getContext().getStringProperty("Area"))
						.replace("Zone", getContext().getStringProperty("Zone"))
						.replace("LYWk", getContext().getStringProperty("LYWk"))
						.replace("store", getContext().getStringProperty("store"))
						.replace("Week", getContext().getStringProperty("SelectFiscalWeek"))
						.replace("Division", getContext().getStringProperty("Division")));
		System.out.println("DB Value: " + DBPlndItmTtl);

		pageDBComparisionPO.CompareValues(extentTest, DBPlndItmVal, DBPlndItmTtl, "Planned Items");

		// ACTUAL ITEMS:
		Report_AddStep("testcase", "ACTUAL ITEMS - WEEK WISE DATA", "", "", "");
		extentTest.log(LogStatus.INFO, "ACTUAL ITEMS - WEEK WISE DATA");

		String ActualItems = data1.getText("ActualItems");
		DBActlItmVal = sql.CLRexecuteQuery(getContext(),
				ActualItems.replace("Region", getContext().getStringProperty("Region"))
						.replace("Area", getContext().getStringProperty("Area"))
						.replace("Zone", getContext().getStringProperty("Zone"))
						.replace("LYWk", getContext().getStringProperty("LYWk"))
						.replace("Week", getContext().getStringProperty("SelectFiscalWeek"))
						.replace("Division", getContext().getStringProperty("Division")));
		System.out.println("DB Value: " + DBActlItmVal);

		String ActualItemsTotal = data.getText("ActualItemsTotal");
		DBActlItmTtl = sql.CLRexecuteQuery(getContext(),
				ActualItemsTotal.replace("Region", getContext().getStringProperty("Region"))
						.replace("Area", getContext().getStringProperty("Area"))
						.replace("Zone", getContext().getStringProperty("Zone"))
						.replace("LYWk", getContext().getStringProperty("LYWk"))
						.replace("store", getContext().getStringProperty("store"))
						.replace("Week", getContext().getStringProperty("SelectFiscalWeek"))
						.replace("Division", getContext().getStringProperty("Division")));
		System.out.println("DB Value: " + DBActlItmTtl);

		pageDBComparisionPO.CompareValues(extentTest, DBActlItmVal, DBActlItmTtl, "Actual Items");

		// PLANNED ITEM PRICE:
		Report_AddStep("testcase", "PLANNED ITEM PRICE - WEEK WISE DATA", "", "", "");
		extentTest.log(LogStatus.INFO, "PLANNED ITEM PRICE - WEEK WISE DATA");

		String PlannedItemPrice = data1.getText("PlannedItemPrice");
		DBPlndItmPriceVal = sql.CLRexecuteQuery(getContext(),
				PlannedItemPrice.replace("Region", getContext().getStringProperty("Region"))
						.replace("Area", getContext().getStringProperty("Area"))
						.replace("Zone", getContext().getStringProperty("Zone"))
						.replace("LYWk", getContext().getStringProperty("LYWk"))
						.replace("Week", getContext().getStringProperty("SelectFiscalWeek"))
						.replace("Division", getContext().getStringProperty("Division")));
		System.out.println("DB Value: " + DBPlndItmPriceVal);

		String PlannedItemPriceTotal = data.getText("PlannedItemPriceTotal");
		DBPlndItmPriceTtl = sql.CLRexecuteQuery(getContext(),
				PlannedItemPriceTotal.replace("Region", getContext().getStringProperty("Region"))
						.replace("Area", getContext().getStringProperty("Area"))
						.replace("Zone", getContext().getStringProperty("Zone"))
						.replace("LYWk", getContext().getStringProperty("LYWk"))
						.replace("store", getContext().getStringProperty("store"))
						.replace("Week", getContext().getStringProperty("SelectFiscalWeek"))
						.replace("Division", getContext().getStringProperty("Division")));
		System.out.println("DB Value: " + DBPlndItmPriceTtl);

		pageDBComparisionPO.CompareValueDecimalFields(extentTest, DBPlndItmPriceVal, DBPlndItmPriceTtl,
				"Planned ItemPrice");

		// ACTUAL ITEM PRICE:
		Report_AddStep("testcase", "ACTUAL ITEM PRICE - WEEK WISE DATA", "", "", "");
		extentTest.log(LogStatus.INFO, "ACTUAL ITEM PRICE - WEEK WISE DATA");

		String ActualItemPrice = data1.getText("ActualItemPrice");
		DBActlItmPriceVal = sql.CLRexecuteQuery(getContext(),
				ActualItemPrice.replace("Region", getContext().getStringProperty("Region"))
						.replace("Area", getContext().getStringProperty("Area"))
						.replace("Zone", getContext().getStringProperty("Zone"))
						.replace("LYWk", getContext().getStringProperty("LYWk"))
						.replace("Week", getContext().getStringProperty("SelectFiscalWeek"))
						.replace("Division", getContext().getStringProperty("Division")));
		System.out.println("DB Value: " + DBActlItmPriceVal);

		String ActualItemPriceTotal = data.getText("ActualItemPriceTotal");
		DBActlItmPriceTtl = sql.CLRexecuteQuery(getContext(),
				ActualItemPriceTotal.replace("Region", getContext().getStringProperty("Region"))
						.replace("Area", getContext().getStringProperty("Area"))
						.replace("Zone", getContext().getStringProperty("Zone"))
						.replace("LYWk", getContext().getStringProperty("LYWk"))
						.replace("store", getContext().getStringProperty("store"))
						.replace("Week", getContext().getStringProperty("SelectFiscalWeek"))
						.replace("Division", getContext().getStringProperty("Division")));
		System.out.println("DB Value: " + DBActlItmPriceTtl);

		pageDBComparisionPO.CompareValueDecimalFields(extentTest, DBActlItmPriceVal, DBActlItmPriceTtl,
				"Actual ItemPrice");
	}

	// Customers
	@Test(description = "Weekly Trading Statement - Group, This Year Customer", priority = 3)
	public void CustomersColumnValidation() throws Exception {
		pageWeeklyPlanningPO = new WeeklyPlanningStorePage().createPage(getDriver(), getContext(), log, status, data,
				getReport(), getReportLogger(), WeeklyPlanningStorePage.class);
		pageDailyTradingStatementGroupPO = new MonthlyPlanningGroupPage().createPage(getDriver(), getContext(), log,
				status, data, getReport(), getReportLogger(), DailyTradingStatementGroupPage.class);
		TestStatus status = getTestStatus();
		System.out.println("Entering into TestCase - Customer View");

		// THIS YEAR - CUSTOMER:
		Report_AddStep("testcase", "CUSTOMER THIS YEAR VALUE - WEEK WISE DATA", "", "", "");
		extentTest.log(LogStatus.INFO, "CUSTOMER THIS YEAR VALUE - WEEK WISE DATA");

		String ThisYrActlCustomers = data1.getText("ThisYrActlCustomers");
		DBThisYrCustVal = sql.CLRexecuteQuery(getContext(),
				ThisYrActlCustomers.replace("Region", getContext().getStringProperty("Region"))
						.replace("Area", getContext().getStringProperty("Area"))
						.replace("Zone", getContext().getStringProperty("Zone"))
						.replace("LYWk", getContext().getStringProperty("LYWk"))
						.replace("Week", getContext().getStringProperty("SelectFiscalWeek"))
						.replace("Division", getContext().getStringProperty("Division")));
		System.out.println("DB Value: " + DBThisYrCustVal);

		String ThisYrActlCustomersTotal = data.getText("ThisYrActlCustomersTotal");
		DBThisYrCustTtl = sql.CLRexecuteQuery(getContext(),
				ThisYrActlCustomersTotal.replace("Region", getContext().getStringProperty("Region"))
						.replace("Area", getContext().getStringProperty("Area"))
						.replace("Zone", getContext().getStringProperty("Zone"))
						.replace("LYWk", getContext().getStringProperty("LYWk"))
						.replace("store", getContext().getStringProperty("store"))
						.replace("Week", getContext().getStringProperty("SelectFiscalWeek"))
						.replace("Division", getContext().getStringProperty("Division")));
		System.out.println("DB Value: " + DBThisYrCustTtl);

		pageDBComparisionPO.CompareValues(extentTest, DBThisYrCustVal, DBThisYrCustTtl, "This Yr Customer");

		// % LAST YEAR CUSTOMER:
		Report_AddStep("testcase", "PERCENTAGE LAST YEAR CUSTOMER - WEEK WISE DATA", "", "", "");
		extentTest.log(LogStatus.INFO, "PERCENTAGE LAST YEAR CUSTOMER - WEEK WISE DATA");

		String PctLastYrCustomers = data1.getText("PctLastYrCustomers");
		DBLstYrCustVal = sql.CLRexecuteQuery(getContext(),
				PctLastYrCustomers.replace("Region", getContext().getStringProperty("Region"))
						.replace("Area", getContext().getStringProperty("Area"))
						.replace("Zone", getContext().getStringProperty("Zone"))
						.replace("LYWk", getContext().getStringProperty("LYWk"))
						.replace("Week", getContext().getStringProperty("SelectFiscalWeek"))
						.replace("Division", getContext().getStringProperty("Division")));
		System.out.println("DB Value: " + DBLstYrCustVal);

		String PctLastYrCustomersTotal = data.getText("PctLastYrCustomersTotal");
		DBLstYrCustTtl = sql.CLRexecuteQuery(getContext(),
				PctLastYrCustomersTotal.replace("Region", getContext().getStringProperty("Region"))
						.replace("Area", getContext().getStringProperty("Area"))
						.replace("Zone", getContext().getStringProperty("Zone"))
						.replace("LYWk", getContext().getStringProperty("LYWk"))
						.replace("store", getContext().getStringProperty("store"))
						.replace("Week", getContext().getStringProperty("SelectFiscalWeek"))
						.replace("Division", getContext().getStringProperty("Division")));
		System.out.println("DB Value: " + DBLstYrCustTtl);

		pageDBComparisionPO.CompareValueDecimalFields(extentTest, DBLstYrCustVal, DBLstYrCustTtl, "LY Customer");

		// DATA PENETRATION CUSTOMER:
		Report_AddStep("testcase", "DATA PENETRATION CUSTOMER - WEEK WISE DATA", "", "", "");
		extentTest.log(LogStatus.INFO, "DATA PENETRATION CUSTOMER - WEEK WISE DATA");

		String DataPenetrationCustomers = data1.getText("DataPenetrationCustomers");
		DBDataPenVal = sql.CLRexecuteQuery(getContext(),
				DataPenetrationCustomers.replace("Region", getContext().getStringProperty("Region"))
						.replace("Area", getContext().getStringProperty("Area"))
						.replace("Zone", getContext().getStringProperty("Zone"))
						.replace("LYWk", getContext().getStringProperty("LYWk"))
						.replace("Week", getContext().getStringProperty("SelectFiscalWeek"))
						.replace("Division", getContext().getStringProperty("Division")));
		System.out.println("DB Value: " + DBDataPenVal);

		String DataPenetrationCustomersTotal = data.getText("DataPenetrationCustomersTotal");
		DBDataPenTtl = sql.CLRexecuteQuery(getContext(),
				DataPenetrationCustomersTotal.replace("Region", getContext().getStringProperty("Region"))
						.replace("Area", getContext().getStringProperty("Area"))
						.replace("Zone", getContext().getStringProperty("Zone"))
						.replace("LYWk", getContext().getStringProperty("LYWk"))
						.replace("store", getContext().getStringProperty("store"))
						.replace("Week", getContext().getStringProperty("SelectFiscalWeek"))
						.replace("Division", getContext().getStringProperty("Division")));
		System.out.println("DB Value: " + DBDataPenTtl);

		pageDBComparisionPO.CompareValueDecimalFields(extentTest, DBDataPenVal, DBDataPenTtl,
				"Data Penetration - Customer");
	}

	@Test(description = "Weekly Trading Statement - Group, Growth in SMS Hours", priority = 4)
	public void HoursColumnValidation() throws Exception {
		pageWeeklyPlanningPO = new WeeklyPlanningStorePage().createPage(getDriver(), getContext(), log, status, data,
				getReport(), getReportLogger(), WeeklyPlanningStorePage.class);
		pageDailyTradingStatementGroupPO = new MonthlyPlanningGroupPage().createPage(getDriver(), getContext(), log,
				status, data, getReport(), getReportLogger(), DailyTradingStatementGroupPage.class);
		TestStatus status = getTestStatus();
		System.out.println("Entering into TestCase - Hours View");

		// ACTUAL HOURS:
		Report_AddStep("testcase", "ACTUAL HOURS - WEEK WISE DATA", "", "", "");
		extentTest.log(LogStatus.INFO, "ACTUAL HOURS - WEEK WISE DATA");

		String ActualHours = data1.getText("ActualHours");
		DBActlHrsVal = sql.CLRexecuteQuery(getContext(),
				ActualHours.replace("Region", getContext().getStringProperty("Region"))
						.replace("Area", getContext().getStringProperty("Area"))
						.replace("Zone", getContext().getStringProperty("Zone"))
						.replace("LYWk", getContext().getStringProperty("LYWk"))
						.replace("Week", getContext().getStringProperty("SelectFiscalWeek"))
						.replace("Division", getContext().getStringProperty("Division")));
		System.out.println("DB Value: " + DBActlHrsVal);

		String ActualHoursTotal = data.getText("ActualHoursTotal");
		DBActlHrsTtl = sql.CLRexecuteQuery(getContext(),
				ActualHoursTotal.replace("Region", getContext().getStringProperty("Region"))
						.replace("Area", getContext().getStringProperty("Area"))
						.replace("Zone", getContext().getStringProperty("Zone"))
						.replace("LYWk", getContext().getStringProperty("LYWk"))
						.replace("store", getContext().getStringProperty("store"))
						.replace("Week", getContext().getStringProperty("SelectFiscalWeek"))
						.replace("Division", getContext().getStringProperty("Division")));
		System.out.println("DB Value: " + DBActlHrsTtl);

		pageDBComparisionPO.CompareValueDecimalFields(extentTest, DBActlHrsVal, DBActlHrsTtl, "Actual Hours");

		// PLANNED HOURS:
		Report_AddStep("testcase", "PLANNED HOURS: - WEEK WISE DATA", "", "", "");
		extentTest.log(LogStatus.INFO, "PLANNED HOURS: - WEEK WISE DATA");

		String PlannedHours = data1.getText("PlannedHours");
		DBPlndHrsVal = sql.CLRexecuteQuery(getContext(),
				PlannedHours.replace("Region", getContext().getStringProperty("Region"))
						.replace("Area", getContext().getStringProperty("Area"))
						.replace("Zone", getContext().getStringProperty("Zone"))
						.replace("LYWk", getContext().getStringProperty("LYWk"))
						.replace("Week", getContext().getStringProperty("SelectFiscalWeek"))
						.replace("Division", getContext().getStringProperty("Division")));
		System.out.println("DB Value: " + DBPlndHrsVal);

		String PlannedHoursTotal = data.getText("PlannedHoursTotal");
		DBPlndHrsTtl = sql.CLRexecuteQuery(getContext(),
				PlannedHoursTotal.replace("Region", getContext().getStringProperty("Region"))
						.replace("Area", getContext().getStringProperty("Area"))
						.replace("Zone", getContext().getStringProperty("Zone"))
						.replace("LYWk", getContext().getStringProperty("LYWk"))
						.replace("store", getContext().getStringProperty("store"))
						.replace("Week", getContext().getStringProperty("SelectFiscalWeek"))
						.replace("Division", getContext().getStringProperty("Division")));
		System.out.println("DB Value: " + DBPlndHrsTtl);

		pageDBComparisionPO.CompareValueDecimalFields(extentTest, DBPlndHrsVal, DBPlndHrsTtl, "Planned Hours");

		// VARIANCE IN PLANNED HOURS:
		Report_AddStep("testcase", "VARIANCE IN PLANNED HOURS - WEEK WISE DATA", "", "", "");
		extentTest.log(LogStatus.INFO, "VARIANCE IN PLANNED HOURS - WEEK WISE DATA");

		String VarHours = data1.getText("VarHours");
		DBVarPlndHrsVal = sql.CLRexecuteQuery(getContext(),
				VarHours.replace("Region", getContext().getStringProperty("Region"))
						.replace("Area", getContext().getStringProperty("Area"))
						.replace("Zone", getContext().getStringProperty("Zone"))
						.replace("LYWk", getContext().getStringProperty("LYWk"))
						.replace("Week", getContext().getStringProperty("SelectFiscalWeek"))
						.replace("Division", getContext().getStringProperty("Division")));
		System.out.println("DB Value: " + DBVarPlndHrsVal);

		String VarHoursTotal = data.getText("VarHoursTotal");
		DBVarPlndHrsTtl = sql.CLRexecuteQuery(getContext(),
				VarHoursTotal.replace("Region", getContext().getStringProperty("Region"))
						.replace("Area", getContext().getStringProperty("Area"))
						.replace("Zone", getContext().getStringProperty("Zone"))
						.replace("LYWk", getContext().getStringProperty("LYWk"))
						.replace("store", getContext().getStringProperty("store"))
						.replace("Week", getContext().getStringProperty("SelectFiscalWeek"))
						.replace("Division", getContext().getStringProperty("Division")));
		System.out.println("DB Value: " + DBVarPlndHrsTtl);

		pageDBComparisionPO.CompareValueDecimalFields(extentTest, DBVarPlndHrsVal, DBVarPlndHrsTtl, "Variance Hours");
	}

	// Wages
	@Test(description = "Weekly Trading Statement - Group, Actual Wages", priority = 5)
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
		extentTest.log(LogStatus.INFO, "ACTUAL WAGE - WEEK WISE DATA");

		String ActualWages = data1.getText("ActualWages");
		DBActlWgVal = sql.CLRexecuteQuery(getContext(),
				ActualWages.replace("Region", getContext().getStringProperty("Region"))
						.replace("Area", getContext().getStringProperty("Area"))
						.replace("Zone", getContext().getStringProperty("Zone"))
						.replace("LYWk", getContext().getStringProperty("LYWk"))
						.replace("Week", getContext().getStringProperty("SelectFiscalWeek"))
						.replace("Division", getContext().getStringProperty("Division")));
		System.out.println("DB Value: " + DBActlWgVal);

		String ActualWagesTotal = data.getText("ActualWagesTotal");
		DBActlWgTtl = sql.CLRexecuteQuery(getContext(),
				ActualWagesTotal.replace("Region", getContext().getStringProperty("Region"))
						.replace("Area", getContext().getStringProperty("Area"))
						.replace("Zone", getContext().getStringProperty("Zone"))
						.replace("LYWk", getContext().getStringProperty("LYWk"))
						.replace("store", getContext().getStringProperty("store"))
						.replace("Week", getContext().getStringProperty("SelectFiscalWeek"))
						.replace("Division", getContext().getStringProperty("Division")));
		System.out.println("DB Value: " + DBActlWgTtl);

		pageDBComparisionPO.CompareValues(extentTest, DBActlWgVal, DBActlWgTtl, "Actual Wages");

		// ACTUAL WAGE PERCENTAGE:
		Report_AddStep("testcase", "ACTUAL WAGE PERCENTAGE- WEEK WISE DATA", "", "", "");
		extentTest.log(LogStatus.INFO, "ACTUAL WAGE PERCENTAGE- WEEK WISE DATA");

		String ActualWagePercentage = data1.getText("ActualWagePercentage");
		DBActlWgPrcntVal = sql.CLRexecuteQuery(getContext(),
				ActualWagePercentage.replace("Region", getContext().getStringProperty("Region"))
						.replace("Area", getContext().getStringProperty("Area"))
						.replace("Zone", getContext().getStringProperty("Zone"))
						.replace("LYWk", getContext().getStringProperty("LYWk"))
						.replace("Week", getContext().getStringProperty("SelectFiscalWeek"))
						.replace("Division", getContext().getStringProperty("Division")));
		System.out.println("DB Value: " + DBActlWgPrcntVal);

		String ActualWagePercentageTotal = data.getText("ActualWagePercentageTotal");
		DBActlWgPrcntTtl = sql.CLRexecuteQuery(getContext(),
				ActualWagePercentageTotal.replace("Region", getContext().getStringProperty("Region"))
						.replace("Area", getContext().getStringProperty("Area"))
						.replace("Zone", getContext().getStringProperty("Zone"))
						.replace("LYWk", getContext().getStringProperty("LYWk"))
						.replace("store", getContext().getStringProperty("store"))
						.replace("Week", getContext().getStringProperty("SelectFiscalWeek"))
						.replace("Division", getContext().getStringProperty("Division")));
		System.out.println("DB Value: " + DBActlWgPrcntTtl);

		pageDBComparisionPO.CompareValueDecimalFields(extentTest, DBActlWgPrcntVal, DBActlWgPrcntTtl,
				"Actual Wages Percentage");

		// BUDGET WAGE:
		if (OptionToView.contains("Budget")) {
			Report_AddStep("testcase", "ACTUAL BUDGET WAGE - WEEK WISE DATA", "", "", "");
			extentTest.log(LogStatus.INFO, "ACTUAL BUDGET WAGE - WEEK WISE DATA");

			String BudgetWages = data1.getText("BudgetWages");
			DBBdgtWgVal = sql.CLRexecuteQuery(getContext(),
					BudgetWages.replace("Region", getContext().getStringProperty("Region"))
							.replace("Area", getContext().getStringProperty("Area"))
							.replace("Zone", getContext().getStringProperty("Zone"))
							.replace("LYWk", getContext().getStringProperty("LYWk"))
							.replace("Week", getContext().getStringProperty("SelectFiscalWeek"))
							.replace("Division", getContext().getStringProperty("Division")));
			System.out.println("DB Value: " + DBBdgtWgVal);

			String BudgetWagesTotal = data.getText("BudgetWagesTotal");
			DBBdgtWgTtl = sql.CLRexecuteQuery(getContext(),
					BudgetWagesTotal.replace("Region", getContext().getStringProperty("Region"))
							.replace("Area", getContext().getStringProperty("Area"))
							.replace("Zone", getContext().getStringProperty("Zone"))
							.replace("LYWk", getContext().getStringProperty("LYWk"))
							.replace("store", getContext().getStringProperty("store"))
							.replace("Week", getContext().getStringProperty("SelectFiscalWeek"))
							.replace("Division", getContext().getStringProperty("Division")));
			System.out.println("DB Value: " + DBBdgtWgTtl);

			pageDBComparisionPO.CompareValues(extentTest, DBBdgtWgVal, DBBdgtWgTtl, "Budget Wages");
		}
		if (OptionToView.contains("Forecast")) {
			Report_AddStep("testcase", "ACTUAL FORECAST WAGE - WEEK WISE DATA", "", "", "");
			extentTest.log(LogStatus.INFO, "ACTUAL FORECAST WAGE - WEEK WISE DATA");

			String ForecastWages = data1.getText("ForecastWages");
			DBBdgtWgVal = sql.CLRexecuteQuery(getContext(),
					ForecastWages.replace("Region", getContext().getStringProperty("Region"))
							.replace("Area", getContext().getStringProperty("Area"))
							.replace("Zone", getContext().getStringProperty("Zone"))
							.replace("LYWk", getContext().getStringProperty("LYWk"))
							.replace("Week", getContext().getStringProperty("SelectFiscalWeek"))
							.replace("Division", getContext().getStringProperty("Division")));
			System.out.println("DB Value: " + DBBdgtWgVal);

			String ForecastWagesTotal = data.getText("ForecastWagesTotal");
			DBBdgtWgTtl = sql.CLRexecuteQuery(getContext(),
					ForecastWagesTotal.replace("Region", getContext().getStringProperty("Region"))
							.replace("Area", getContext().getStringProperty("Area"))
							.replace("Zone", getContext().getStringProperty("Zone"))
							.replace("LYWk", getContext().getStringProperty("LYWk"))
							.replace("store", getContext().getStringProperty("store"))
							.replace("Week", getContext().getStringProperty("SelectFiscalWeek"))
							.replace("Division", getContext().getStringProperty("Division")));
			System.out.println("DB Value: " + DBBdgtWgTtl);

			pageDBComparisionPO.CompareValues(extentTest, DBBdgtWgVal, DBBdgtWgTtl, "Forecast Wages");
		}

		// BUDGET WAGE PERCENTAGE:
		if (OptionToView.contains("Budget")) {
			Report_AddStep("testcase", "BUDGET WAGE PERCENTAGE- WEEK WISE DATA", "", "", "");
			extentTest.log(LogStatus.INFO, "BUDGET WAGE PERCENTAGE- WEEK WISE DATA");

			String BudgetWagePercentage = data1.getText("BudgetWagePercentage");
			DBBdgtWgPrcntVal = sql.CLRexecuteQuery(getContext(),
					BudgetWagePercentage.replace("Region", getContext().getStringProperty("Region"))
							.replace("Area", getContext().getStringProperty("Area"))
							.replace("Zone", getContext().getStringProperty("Zone"))
							.replace("LYWk", getContext().getStringProperty("LYWk"))
							.replace("Week", getContext().getStringProperty("SelectFiscalWeek"))
							.replace("Division", getContext().getStringProperty("Division")));
			System.out.println("DB Value: " + DBBdgtWgPrcntVal);

			String BudgetWagePercentageTotal = data.getText("BudgetWagePercentageTotal");
			DBBdgtWgPrcntTtl = sql.CLRexecuteQuery(getContext(),
					BudgetWagePercentageTotal.replace("Region", getContext().getStringProperty("Region"))
							.replace("Area", getContext().getStringProperty("Area"))
							.replace("Zone", getContext().getStringProperty("Zone"))
							.replace("LYWk", getContext().getStringProperty("LYWk"))
							.replace("store", getContext().getStringProperty("store"))
							.replace("Week", getContext().getStringProperty("SelectFiscalWeek"))
							.replace("Division", getContext().getStringProperty("Division")));
			System.out.println("DB Value: " + DBBdgtWgPrcntTtl);

			pageDBComparisionPO.CompareValueDecimalFields(extentTest, DBBdgtWgPrcntVal, DBBdgtWgPrcntTtl,
					"Budget Wages Percentage");
		}
		if (OptionToView.contains("Forecast")) {
			Report_AddStep("testcase", "FORECAST WAGE PERCENTAGE- WEEK WISE DATA", "", "", "");
			extentTest.log(LogStatus.INFO, "FORECAST WAGE PERCENTAGE- WEEK WISE DATA");

			String ForecastWagePercentage = data1.getText("ForecastWagePercentage");
			DBBdgtWgPrcntVal = sql.CLRexecuteQuery(getContext(),
					ForecastWagePercentage.replace("Region", getContext().getStringProperty("Region"))
							.replace("Area", getContext().getStringProperty("Area"))
							.replace("Zone", getContext().getStringProperty("Zone"))
							.replace("LYWk", getContext().getStringProperty("LYWk"))
							.replace("Week", getContext().getStringProperty("SelectFiscalWeek"))
							.replace("Division", getContext().getStringProperty("Division")));
			System.out.println("DB Value: " + DBBdgtWgPrcntVal);

			String ForecastWagePercentageTotal = data.getText("ForecastWagePercentageTotal");
			DBBdgtWgPrcntTtl = sql.CLRexecuteQuery(getContext(),
					ForecastWagePercentageTotal.replace("Region", getContext().getStringProperty("Region"))
							.replace("Area", getContext().getStringProperty("Area"))
							.replace("Zone", getContext().getStringProperty("Zone"))
							.replace("LYWk", getContext().getStringProperty("LYWk"))
							.replace("store", getContext().getStringProperty("store"))
							.replace("Week", getContext().getStringProperty("SelectFiscalWeek"))
							.replace("Division", getContext().getStringProperty("Division")));
			System.out.println("DB Value: " + DBBdgtWgPrcntTtl);

			pageDBComparisionPO.CompareValueDecimalFields(extentTest, DBBdgtWgPrcntVal, DBBdgtWgPrcntTtl,
					"Forecast Wages Percentage");
		}

		// PLANNED CPH:
		Report_AddStep("testcase", "PLANNED CPH - WEEK WISE DATA", "", "", "");
		extentTest.log(LogStatus.INFO, "PLANNED CPH - WEEK WISE DATA");

		String PlannedCPH = data1.getText("PlannedCPH");
		DBPlndCPHVal = sql.CLRexecuteQuery(getContext(),
				PlannedCPH.replace("Region", getContext().getStringProperty("Region"))
						.replace("Area", getContext().getStringProperty("Area"))
						.replace("Zone", getContext().getStringProperty("Zone"))
						.replace("LYWk", getContext().getStringProperty("LYWk"))
						.replace("Week", getContext().getStringProperty("SelectFiscalWeek"))
						.replace("Division", getContext().getStringProperty("Division")));
		System.out.println("DB Value: " + DBPlndCPHVal);

		String PlannedCPHTotal = data.getText("PlannedCPHTotal");
		DBPlndCPHTtl = sql.CLRexecuteQuery(getContext(),
				PlannedCPHTotal.replace("Region", getContext().getStringProperty("Region"))
						.replace("Area", getContext().getStringProperty("Area"))
						.replace("Zone", getContext().getStringProperty("Zone"))
						.replace("LYWk", getContext().getStringProperty("LYWk"))
						.replace("store", getContext().getStringProperty("store"))
						.replace("Week", getContext().getStringProperty("SelectFiscalWeek"))
						.replace("Division", getContext().getStringProperty("Division")));
		System.out.println("DB Value: " + DBPlndCPHTtl);

		pageDBComparisionPO.CompareValueDecimalFields(extentTest, DBPlndCPHVal, DBPlndCPHTtl, "Planned CPH");

		// PLANNED WAGE:
		Report_AddStep("testcase", "PLANNED WAGE - WEEK WISE DATA", "", "", "");
		extentTest.log(LogStatus.INFO, "PLANNED WAGE - WEEK WISE DATA");

		String PlannedWages = data1.getText("PlannedWages");
		DBPlndWgVal = sql.CLRexecuteQuery(getContext(),
				PlannedWages.replace("Region", getContext().getStringProperty("Region"))
						.replace("Area", getContext().getStringProperty("Area"))
						.replace("Zone", getContext().getStringProperty("Zone"))
						.replace("LYWk", getContext().getStringProperty("LYWk"))
						.replace("Week", getContext().getStringProperty("SelectFiscalWeek"))
						.replace("Division", getContext().getStringProperty("Division")));
		System.out.println("DB Value: " + DBPlndWgVal);

		String PlannedWagesTotal = data.getText("PlannedWagesTotal");
		DBPlndWgTtl = sql.CLRexecuteQuery(getContext(),
				PlannedWagesTotal.replace("Region", getContext().getStringProperty("Region"))
						.replace("Area", getContext().getStringProperty("Area"))
						.replace("Zone", getContext().getStringProperty("Zone"))
						.replace("LYWk", getContext().getStringProperty("LYWk"))
						.replace("store", getContext().getStringProperty("store"))
						.replace("Week", getContext().getStringProperty("SelectFiscalWeek"))
						.replace("Division", getContext().getStringProperty("Division")));
		System.out.println("DB Value: " + DBPlndWgTtl);

		pageDBComparisionPO.CompareValues(extentTest, DBPlndWgVal, DBPlndWgTtl, "Planned Wages");

		// PLANNED WAGE PERCENTAGE:
		Report_AddStep("testcase", "PLANNED WAGE PERCENTAGE - WEEK WISE DATA", "", "", "");
		extentTest.log(LogStatus.INFO, "PLANNED WAGE PERCENTAGE - WEEK WISE DATA");

		String PlannedWagesPercentage = data1.getText("PlannedWagesPercentage");
		DBPlndWgPrcntVal = sql.CLRexecuteQuery(getContext(),
				PlannedWagesPercentage.replace("Region", getContext().getStringProperty("Region"))
						.replace("Area", getContext().getStringProperty("Area"))
						.replace("Zone", getContext().getStringProperty("Zone"))
						.replace("LYWk", getContext().getStringProperty("LYWk"))
						.replace("Week", getContext().getStringProperty("SelectFiscalWeek"))
						.replace("Division", getContext().getStringProperty("Division")));
		System.out.println("DB Value: " + DBPlndWgPrcntVal);

		String PlannedWagesPercentageTotal = data.getText("PlannedWagesPercentageTotal");
		DBPlndWgPrcntTtl = sql.CLRexecuteQuery(getContext(),
				PlannedWagesPercentageTotal.replace("Region", getContext().getStringProperty("Region"))
						.replace("Area", getContext().getStringProperty("Area"))
						.replace("Zone", getContext().getStringProperty("Zone"))
						.replace("LYWk", getContext().getStringProperty("LYWk"))
						.replace("store", getContext().getStringProperty("store"))
						.replace("Week", getContext().getStringProperty("SelectFiscalWeek"))
						.replace("Division", getContext().getStringProperty("Division")));
		System.out.println("DB Value: " + DBPlndWgPrcntTtl);

		pageDBComparisionPO.CompareValueDecimalFields(extentTest, DBPlndWgPrcntVal, DBPlndWgPrcntTtl,
				"Planned Wages Percentage");

		// CPH Wages:
		Report_AddStep("testcase", "CPH WAGES - WEEK WISE DATA", "", "", "");
		extentTest.log(LogStatus.INFO, "CPH WAGES - WEEK WISE DATA");

		String ActualCPH = data1.getText("ActualCPH");
		DBCPHWgVal = sql.CLRexecuteQuery(getContext(),
				ActualCPH.replace("Region", getContext().getStringProperty("Region"))
						.replace("Area", getContext().getStringProperty("Area"))
						.replace("Zone", getContext().getStringProperty("Zone"))
						.replace("LYWk", getContext().getStringProperty("LYWk"))
						.replace("Week", getContext().getStringProperty("SelectFiscalWeek"))
						.replace("Division", getContext().getStringProperty("Division")));
		System.out.println("DB Value: " + DBCPHWgVal);

		String ActualCPHTotal = data.getText("ActualCPHTotal");
		DBCPHWgTtl = sql.CLRexecuteQuery(getContext(),
				ActualCPHTotal.replace("Region", getContext().getStringProperty("Region"))
						.replace("Area", getContext().getStringProperty("Area"))
						.replace("Zone", getContext().getStringProperty("Zone"))
						.replace("LYWk", getContext().getStringProperty("LYWk"))
						.replace("store", getContext().getStringProperty("store"))
						.replace("Week", getContext().getStringProperty("SelectFiscalWeek"))
						.replace("Division", getContext().getStringProperty("Division")));
		System.out.println("DB Value: " + DBCPHWgTtl);

		pageDBComparisionPO.CompareValueDecimalFields(extentTest, DBCPHWgVal, DBCPHWgTtl, "CPH Wages");

		// VARIANCE BUDGET WAGES:
		if (OptionToView.contains("Budget")) {
			Report_AddStep("testcase", "VARIANCE BUDGET WAGES - WEEK WISE DATA", "", "", "");
			extentTest.log(LogStatus.INFO, "VARIANCE BUDGET WAGES - WEEK WISE DATA");

			String VarWages = data1.getText("VarWages");
			DBVarBdgtWgVal = sql.CLRexecuteQuery(getContext(),
					VarWages.replace("Region", getContext().getStringProperty("Region"))
							.replace("Area", getContext().getStringProperty("Area"))
							.replace("Zone", getContext().getStringProperty("Zone"))
							.replace("LYWk", getContext().getStringProperty("LYWk"))
							.replace("Week", getContext().getStringProperty("SelectFiscalWeek"))
							.replace("Division", getContext().getStringProperty("Division")));
			System.out.println("DB Value: " + DBVarBdgtWgVal);

			String VarWagesTotal = data.getText("VarWagesTotal");
			DBVarBdgtWgTtl = sql.CLRexecuteQuery(getContext(),
					VarWagesTotal.replace("Region", getContext().getStringProperty("Region"))
							.replace("Area", getContext().getStringProperty("Area"))
							.replace("Zone", getContext().getStringProperty("Zone"))
							.replace("LYWk", getContext().getStringProperty("LYWk"))
							.replace("store", getContext().getStringProperty("store"))
							.replace("Week", getContext().getStringProperty("SelectFiscalWeek"))
							.replace("Division", getContext().getStringProperty("Division")));
			System.out.println("DB Value: " + DBVarBdgtWgTtl);

			pageDBComparisionPO.CompareValueDecimalFields(extentTest, DBVarBdgtWgVal, DBVarBdgtWgTtl,
					"Variance Budget Wages");
		}
		if (OptionToView.contains("Forecast")) {
			Report_AddStep("testcase", "VARIANCE FORECAST WAGES - WEEK WISE DATA", "", "", "");
			extentTest.log(LogStatus.INFO, "VARIANCE FORECAST WAGES - WEEK WISE DATA");

			String VarFcstWages = data1.getText("VarFcstWages");
			DBVarBdgtWgVal = sql.CLRexecuteQuery(getContext(),
					VarFcstWages.replace("Region", getContext().getStringProperty("Region"))
							.replace("Area", getContext().getStringProperty("Area"))
							.replace("Zone", getContext().getStringProperty("Zone"))
							.replace("LYWk", getContext().getStringProperty("LYWk"))
							.replace("Week", getContext().getStringProperty("SelectFiscalWeek"))
							.replace("Division", getContext().getStringProperty("Division")));
			System.out.println("DB Value: " + DBVarBdgtWgVal);

			String VarFcstWagesTotal = data.getText("VarFcstWagesTotal");
			DBVarBdgtWgTtl = sql.CLRexecuteQuery(getContext(),
					VarFcstWagesTotal.replace("Region", getContext().getStringProperty("Region"))
							.replace("Area", getContext().getStringProperty("Area"))
							.replace("Zone", getContext().getStringProperty("Zone"))
							.replace("LYWk", getContext().getStringProperty("LYWk"))
							.replace("store", getContext().getStringProperty("store"))
							.replace("Week", getContext().getStringProperty("SelectFiscalWeek"))
							.replace("Division", getContext().getStringProperty("Division")));
			System.out.println("DB Value: " + DBVarBdgtWgTtl);

			pageDBComparisionPO.CompareValueDecimalFields(extentTest, DBVarBdgtWgVal, DBVarBdgtWgTtl,
					"Variance Forecast Wages");
		}
	}

	////////////////////////////////////////////////////////////////////// New Table
	////////////////////////////////////////////////////////////////////// Comparison//////////////////////////////////////////////////////////////////

	/// Sales
	@Test(description = "Weekly Trading Statement - Group, SalesColumnValidationNewTable", priority = 6)
	public void SalesColumnValidationNewTable() throws Exception {
		pageWeeklyPlanningPO = new WeeklyPlanningStorePage().createPage(getDriver(), getContext(), log, status, data,
				getReport(), getReportLogger(), WeeklyPlanningStorePage.class);
		pageDailyTradingStatementGroupPO = new MonthlyPlanningGroupPage().createPage(getDriver(), getContext(), log,
				status, data, getReport(), getReportLogger(), DailyTradingStatementGroupPage.class);
		TestStatus status = getTestStatus();
		OptionToView = getContext().getStringProperty("OptionToView");

		// ACTUAL SALES:
		Report_AddStep("testcase", " ACTUAL SALES COMBINED TABLE- WEEK WISE DATA", "", "", "");
		extentTest.log(LogStatus.INFO, "ACTUAL SALES COMBINED TABLE- WEEK WISE DATA");

		String ActualSalesNew = data1.getText("ActualSales");
		DBActlVal = sql.CLRexecuteQuery(getContext(),
				ActualSalesNew.replace("store", getContext().getStringProperty("store"))
						.replace("LYWk", getContext().getStringProperty("LYWk"))
						.replace("Week", getContext().getStringProperty("SelectFiscalWeek"))
						.replace("Division", getContext().getStringProperty("Division")));
		System.out.println("DB Value: " + DBActlVal);

		String ActualSalesTotalNew = data.getText("ActualSalesTotal");
		DBActlTtl = sql.CLRexecuteQuery(getContext(),
				ActualSalesTotalNew.replace("store", getContext().getStringProperty("store"))
						.replace("LYWk", getContext().getStringProperty("LYWk"))
						.replace("Week", getContext().getStringProperty("SelectFiscalWeek"))
						.replace("Division", getContext().getStringProperty("Division")));
		System.out.println("DB Value: " + DBActlTtl);

		pageDBComparisionPO.CompareValues(extentTest, DBActlVal, DBActlTtl, "Actual Sales");

		// WEEKLY PLANNED SALES:
		Report_AddStep("testcase", " WEEKLY PLANNED SALES COMBINED TABLE- WEEK WISE DATA", "", "", "");
		extentTest.log(LogStatus.INFO, "WEEKLY PLANNED SALES COMBINED TABLE- WEEK WISE DATA");

		String WeeklyPlannedSales = data1.getText("WeeklyPlannedSales");
		DBPlndVal = sql.CLRexecuteQuery(getContext(),
				WeeklyPlannedSales.replace("store", getContext().getStringProperty("store"))
						.replace("LYWk", getContext().getStringProperty("LYWk"))
						.replace("Week", getContext().getStringProperty("SelectFiscalWeek"))
						.replace("Division", getContext().getStringProperty("Division")));
		System.out.println("DB Value: " + DBPlndVal);

		String WeeklyPlannedSalesTotal = data.getText("WeeklyPlannedSalesTotal");
		DBPlndTtl = sql.CLRexecuteQuery(getContext(),
				WeeklyPlannedSalesTotal.replace("store", getContext().getStringProperty("store"))
						.replace("LYWk", getContext().getStringProperty("LYWk"))
						.replace("Week", getContext().getStringProperty("SelectFiscalWeek"))
						.replace("Division", getContext().getStringProperty("Division")));
		System.out.println("DB Value: " + DBPlndTtl);

		pageDBComparisionPO.CompareValues(extentTest, DBPlndVal, DBPlndTtl, "Weekly Plannes Sales");

		// MONTHLY PLANNED SALES:
		Report_AddStep("testcase", "MONTHLY PLANNED SALES COMBINED TABLE - WEEK WISE DATA", "", "", "");
		extentTest.log(LogStatus.INFO, "MONTHLY PLANNED SALES COMBINED TABLE- WEEK WISE DATA");

		String MonthlyPlannedSales = data1.getText("MonthlyPlannedSales");
		DBPlndVal = sql.CLRexecuteQuery(getContext(),
				MonthlyPlannedSales.replace("store", getContext().getStringProperty("store"))
						.replace("LYWk", getContext().getStringProperty("LYWk"))
						.replace("Week", getContext().getStringProperty("SelectFiscalWeek"))
						.replace("Division", getContext().getStringProperty("Division")));
		System.out.println("DB Value: " + DBPlndVal);

		String MonthlyPlannedSalesTotal = data.getText("MonthlyPlannedSalesTotal");
		DBPlndTtl = sql.CLRexecuteQuery(getContext(),
				MonthlyPlannedSalesTotal.replace("store", getContext().getStringProperty("store"))
						.replace("LYWk", getContext().getStringProperty("LYWk"))
						.replace("Week", getContext().getStringProperty("SelectFiscalWeek"))
						.replace("Division", getContext().getStringProperty("Division")));
		System.out.println("DB Value: " + DBPlndTtl);

		pageDBComparisionPO.CompareValues(extentTest, DBPlndVal, DBPlndTtl, "Monthly Planned Sales");

		// BUDGET SALES:
		if (OptionToView.contains("Budget")) {
			Report_AddStep("testcase", "BUDGET SALES COMBINED TABLE- WEEK WISE DATA", "", "", "");
			extentTest.log(LogStatus.INFO, "BUDGET SALES COMBINED TABLE- WEEK WISE DATA");

			String BudgetSales = data1.getText("BudgetSales");
			DBBdgtVal = sql.CLRexecuteQuery(getContext(),
					BudgetSales.replace("store", getContext().getStringProperty("store"))
							.replace("LYWk", getContext().getStringProperty("LYWk"))
							.replace("Week", getContext().getStringProperty("SelectFiscalWeek"))
							.replace("Division", getContext().getStringProperty("Division")));
			System.out.println("DB Value: " + DBBdgtVal);

			String BudgetSalesTotal = data.getText("BudgetSalesTotal");
			DBBdgtTtl = sql.CLRexecuteQuery(getContext(),
					BudgetSalesTotal.replace("store", getContext().getStringProperty("store"))
							.replace("LYWk", getContext().getStringProperty("LYWk"))
							.replace("Week", getContext().getStringProperty("SelectFiscalWeek"))
							.replace("Division", getContext().getStringProperty("Division")));
			System.out.println("DB Value: " + DBBdgtTtl);

			pageDBComparisionPO.CompareValues(extentTest, DBBdgtVal, DBBdgtTtl, "Budget Sales");

		}
		if (OptionToView.contains("Forecast")) {
			Report_AddStep("testcase", "FORECAST SALES COMBINED TABLE - WEEK WISE DATA", "", "", "");
			extentTest.log(LogStatus.INFO, "FORECAST SALES COMBINED TABLE- WEEK WISE DATA");

			String ForecastSales = data1.getText("ForecastSales");
			DBBdgtVal = sql.CLRexecuteQuery(getContext(),
					ForecastSales.replace("store", getContext().getStringProperty("store"))
							.replace("LYWk", getContext().getStringProperty("LYWk"))
							.replace("Week", getContext().getStringProperty("SelectFiscalWeek"))
							.replace("Division", getContext().getStringProperty("Division")));
			System.out.println("DB Value: " + DBBdgtVal);

			String ForecastSalesTotal = data.getText("ForecastSalesTotal");
			DBBdgtTtl = sql.CLRexecuteQuery(getContext(),
					ForecastSalesTotal.replace("store", getContext().getStringProperty("store"))
							.replace("LYWk", getContext().getStringProperty("LYWk"))
							.replace("Week", getContext().getStringProperty("SelectFiscalWeek"))
							.replace("Division", getContext().getStringProperty("Division")));
			System.out.println("DB Value: " + DBBdgtTtl);

			pageDBComparisionPO.CompareValues(extentTest, DBBdgtVal, DBBdgtTtl, "Forecast Sales");

		}
	}

	///// WAGES///
	@Test(description = "Weekly Trading Statement - Group, SalesColumnValidationNewTable", priority = 7)
	public void WagesColumnValidationNewTable() throws Exception {
		pageWeeklyPlanningPO = new WeeklyPlanningStorePage().createPage(getDriver(), getContext(), log, status, data,
				getReport(), getReportLogger(), WeeklyPlanningStorePage.class);
		pageDailyTradingStatementGroupPO = new MonthlyPlanningGroupPage().createPage(getDriver(), getContext(), log,
				status, data, getReport(), getReportLogger(), DailyTradingStatementGroupPage.class);
		TestStatus status = getTestStatus();
		OptionToView = getContext().getStringProperty("OptionToView");

		// ACTUAL WAGES:
		Report_AddStep("testcase", " ACTUAL WAGES COMBINED TABLE- WEEK WISE DATA", "", "", "");
		extentTest.log(LogStatus.INFO, "ACTUAL WAGES COMBINED TABLE- WEEK WISE DATA");

		String ActualWagesNew = data1.getText("ActualWages");
		DBActlVal = sql.CLRexecuteQuery(getContext(),
				ActualWagesNew.replace("store", getContext().getStringProperty("store"))
						.replace("LYWk", getContext().getStringProperty("LYWk"))
						.replace("Week", getContext().getStringProperty("SelectFiscalWeek"))
						.replace("Division", getContext().getStringProperty("Division")));
		System.out.println("DB Value: " + DBActlVal);

		String ActualWagesTotalNew = data.getText("ActualWagesTotal");
		DBActlTtl = sql.CLRexecuteQuery(getContext(),
				ActualWagesTotalNew.replace("store", getContext().getStringProperty("store"))
						.replace("LYWk", getContext().getStringProperty("LYWk"))
						.replace("Week", getContext().getStringProperty("SelectFiscalWeek"))
						.replace("Division", getContext().getStringProperty("Division")));
		System.out.println("DB Value: " + DBActlTtl);

		pageDBComparisionPO.CompareValues(extentTest, DBActlVal, DBActlTtl, "Actual Wages");

		// WEEKLY PLANNED WAGES:
		Report_AddStep("testcase", " WEEKLY PLANNED WAGES COMBINED TABLE- WEEK WISE DATA", "", "", "");
		extentTest.log(LogStatus.INFO, "WEEKLY PLANNED WAGES COMBINED TABLE- WEEK WISE DATA");

		String WeeklyPlannedWages = data1.getText("WeeklyPlannedWages");
		DBPlndVal = sql.CLRexecuteQuery(getContext(),
				WeeklyPlannedWages.replace("store", getContext().getStringProperty("store"))
						.replace("LYWk", getContext().getStringProperty("LYWk"))
						.replace("Week", getContext().getStringProperty("SelectFiscalWeek"))
						.replace("Division", getContext().getStringProperty("Division")));
		System.out.println("DB Value: " + DBPlndVal);

		String WeeklyPlannedWagesTotal = data.getText("WeeklyPlannedWagesTotal");
		DBPlndTtl = sql.CLRexecuteQuery(getContext(),
				WeeklyPlannedWagesTotal.replace("store", getContext().getStringProperty("store"))
						.replace("LYWk", getContext().getStringProperty("LYWk"))
						.replace("Week", getContext().getStringProperty("SelectFiscalWeek"))
						.replace("Division", getContext().getStringProperty("Division")));
		System.out.println("DB Value: " + DBPlndTtl);

		pageDBComparisionPO.CompareValues(extentTest, DBPlndVal, DBPlndTtl, "Weekly Planned Sales");

		// MONTHLY PLANNED WAGES:
		Report_AddStep("testcase", "MONTHLY PLANNED WAGES COMBINED TABLE - WEEK WISE DATA", "", "", "");
		extentTest.log(LogStatus.INFO, "MONTHLY PLANNED WAGES COMBINED TABLE- WEEK WISE DATA");

		String MonthlyPlannedWages = data1.getText("MonthlyPlannedWages");
		DBPlndVal = sql.CLRexecuteQuery(getContext(),
				MonthlyPlannedWages.replace("store", getContext().getStringProperty("store"))
						.replace("LYWk", getContext().getStringProperty("LYWk"))
						.replace("Week", getContext().getStringProperty("SelectFiscalWeek"))
						.replace("Division", getContext().getStringProperty("Division")));
		System.out.println("DB Value: " + DBPlndVal);

		String MonthlyPlannedWagesTotal = data.getText("MonthlyPlannedWagesTotal");
		DBPlndTtl = sql.CLRexecuteQuery(getContext(),
				MonthlyPlannedWagesTotal.replace("store", getContext().getStringProperty("store"))
						.replace("LYWk", getContext().getStringProperty("LYWk"))
						.replace("Week", getContext().getStringProperty("SelectFiscalWeek"))
						.replace("Division", getContext().getStringProperty("Division")));
		System.out.println("DB Value: " + DBPlndTtl);

		pageDBComparisionPO.CompareValues(extentTest, DBPlndVal, DBPlndTtl, "Monthly Planned Sales");

		// BUDGET WAGES:
		if (OptionToView.contains("Budget")) {
			Report_AddStep("testcase", "BUDGET WAGES COMBINED TABLE- WEEK WISE DATA", "", "", "");
			extentTest.log(LogStatus.INFO, "BUDGET WAGES COMBINED TABLE- WEEK WISE DATA");

			String BudgetWages = data1.getText("BudgetWages");
			DBBdgtVal = sql.CLRexecuteQuery(getContext(),
					BudgetWages.replace("store", getContext().getStringProperty("store"))
							.replace("LYWk", getContext().getStringProperty("LYWk"))
							.replace("Week", getContext().getStringProperty("SelectFiscalWeek"))
							.replace("Division", getContext().getStringProperty("Division")));
			System.out.println("DB Value: " + DBBdgtVal);

			String BudgetWagesTotal = data.getText("BudgetWagesTotal");
			DBBdgtTtl = sql.CLRexecuteQuery(getContext(),
					BudgetWagesTotal.replace("store", getContext().getStringProperty("store"))
							.replace("LYWk", getContext().getStringProperty("LYWk"))
							.replace("Week", getContext().getStringProperty("SelectFiscalWeek"))
							.replace("Division", getContext().getStringProperty("Division")));
			System.out.println("DB Value: " + DBBdgtTtl);

			pageDBComparisionPO.CompareValues(extentTest, DBBdgtVal, DBBdgtTtl, "Budeget Wages");
		}
		if (OptionToView.contains("Forecast")) {
			Report_AddStep("testcase", "FORECAST WAGES COMBINED TABLE - WEEK WISE DATA", "", "", "");
			extentTest.log(LogStatus.INFO, "FORECAST WAGES COMBINED TABLE- WEEK WISE DATA");

			String ForecastWages = data1.getText("ForecastWages");
			DBBdgtVal = sql.CLRexecuteQuery(getContext(),
					ForecastWages.replace("store", getContext().getStringProperty("store"))
							.replace("LYWk", getContext().getStringProperty("LYWk"))
							.replace("Week", getContext().getStringProperty("SelectFiscalWeek"))
							.replace("Division", getContext().getStringProperty("Division")));
			System.out.println("DB Value: " + DBBdgtVal);

			String ForecastWagesTotal = data.getText("ForecastWagesTotal");
			DBBdgtTtl = sql.CLRexecuteQuery(getContext(),
					ForecastWagesTotal.replace("store", getContext().getStringProperty("store"))
							.replace("LYWk", getContext().getStringProperty("LYWk"))
							.replace("Week", getContext().getStringProperty("SelectFiscalWeek"))
							.replace("Division", getContext().getStringProperty("Division")));
			System.out.println("DB Value: " + DBBdgtTtl);

			pageDBComparisionPO.CompareValues(extentTest, DBBdgtVal, DBBdgtTtl, "Forecast Wages");
		}
	}

	///// WAGE%///
	@Test(description = "Weekly Trading Statement - Group, WagePercentageColumnValidationNewTable", priority = 8)
	public void WagePercentageColumnValidationNewTable() throws Exception {
		pageWeeklyPlanningPO = new WeeklyPlanningStorePage().createPage(getDriver(), getContext(), log, status, data,
				getReport(), getReportLogger(), WeeklyPlanningStorePage.class);
		pageDailyTradingStatementGroupPO = new MonthlyPlanningGroupPage().createPage(getDriver(), getContext(), log,
				status, data, getReport(), getReportLogger(), DailyTradingStatementGroupPage.class);
		TestStatus status = getTestStatus();
		OptionToView = getContext().getStringProperty("OptionToView");

		// ACTUAL WAGE%:
		Report_AddStep("testcase", " ACTUAL WAGE PERCENTAGE COMBINED TABLE- WEEK WISE DATA", "", "", "");
		extentTest.log(LogStatus.INFO, "ACTUAL WAGE PERCENTAGE COMBINED TABLE- WEEK WISE DATA");

		String ActualWagePercentageNew = data1.getText("ActualWagePercentage");
		DBActlVal = sql.CLRexecuteQuery(getContext(),
				ActualWagePercentageNew.replace("store", getContext().getStringProperty("store"))
						.replace("LYWk", getContext().getStringProperty("LYWk"))
						.replace("Week", getContext().getStringProperty("SelectFiscalWeek"))
						.replace("Division", getContext().getStringProperty("Division")));
		System.out.println("DB Value: " + DBActlVal);

		String ActualWagePercentageTotal = data.getText("ActualWagePercentageTotal");
		DBActlTtl = sql.CLRexecuteQuery(getContext(),
				ActualWagePercentageTotal.replace("store", getContext().getStringProperty("store"))
						.replace("LYWk", getContext().getStringProperty("LYWk"))
						.replace("Week", getContext().getStringProperty("SelectFiscalWeek"))
						.replace("Division", getContext().getStringProperty("Division")));
		System.out.println("DB Value: " + DBActlTtl);

		pageDBComparisionPO.CompareValueDecimalFields(extentTest, DBActlVal, DBActlTtl, "Actual Wage Percentage");

		// WEEKLY PLANNED WAGE%:
		Report_AddStep("testcase", " WEEKLY PLANNED WAGE PERCENTAGE COMBINED TABLE- WEEK WISE DATA", "", "", "");
		extentTest.log(LogStatus.INFO, "WEEKLY PLANNED WAGE PERCENTAGE COMBINED TABLE- WEEK WISE DATA");

		String WeeklyPlannedWagePercentage = data1.getText("WeeklyPlannedWagePercentage");
		DBPlndVal = sql.CLRexecuteQuery(getContext(),
				WeeklyPlannedWagePercentage.replace("store", getContext().getStringProperty("store"))
						.replace("LYWk", getContext().getStringProperty("LYWk"))
						.replace("Week", getContext().getStringProperty("SelectFiscalWeek"))
						.replace("Division", getContext().getStringProperty("Division")));
		System.out.println("DB Value: " + DBPlndVal);

		String WeeklyPlannedWagePercentageTotal = data.getText("WeeklyPlannedWagePercentageTotal");
		DBPlndTtl = sql.CLRexecuteQuery(getContext(),
				WeeklyPlannedWagePercentageTotal.replace("store", getContext().getStringProperty("store"))
						.replace("LYWk", getContext().getStringProperty("LYWk"))
						.replace("Week", getContext().getStringProperty("SelectFiscalWeek"))
						.replace("Division", getContext().getStringProperty("Division")));
		System.out.println("DB Value: " + DBPlndTtl);

		pageDBComparisionPO.CompareValueDecimalFields(extentTest, DBPlndVal, DBPlndTtl,
				"Weekly Planned Wage Percentage");

		// MONTHLY PLANNED WAGE%:
		Report_AddStep("testcase", "MONTHLY PLANNED WAGE PERCENTAGE COMBINED TABLE - WEEK WISE DATA", "", "", "");
		extentTest.log(LogStatus.INFO, "MONTHLY PLANNED WAGE PERCENTAGE COMBINED TABLE- WEEK WISE DATA");

		String MonthlyPlannedWagePercentage = data1.getText("MonthlyPlannedWagePercentage");
		DBPlndVal = sql.CLRexecuteQuery(getContext(),
				MonthlyPlannedWagePercentage.replace("store", getContext().getStringProperty("store"))
						.replace("LYWk", getContext().getStringProperty("LYWk"))
						.replace("Week", getContext().getStringProperty("SelectFiscalWeek"))
						.replace("Division", getContext().getStringProperty("Division")));
		System.out.println("DB Value: " + DBPlndVal);

		String MonthlyPlannedWagePercentageTotal = data.getText("MonthlyPlannedWagePercentageTotal");
		DBPlndTtl = sql.CLRexecuteQuery(getContext(),
				MonthlyPlannedWagePercentageTotal.replace("store", getContext().getStringProperty("store"))
						.replace("LYWk", getContext().getStringProperty("LYWk"))
						.replace("Week", getContext().getStringProperty("SelectFiscalWeek"))
						.replace("Division", getContext().getStringProperty("Division")));
		System.out.println("DB Value: " + DBPlndTtl);

		pageDBComparisionPO.CompareValueDecimalFields(extentTest, DBPlndVal, DBPlndTtl,
				"Monthly Planned Wage Percentage");

		// BUDGET WAGE%:
		if (OptionToView.contains("Budget")) {
			Report_AddStep("testcase", "BUDGET WAGE PERCENTAGE COMBINED TABLE- WEEK WISE DATA", "", "", "");
			extentTest.log(LogStatus.INFO, "BUDGET WAGE PERCENTAGE COMBINED TABLE- WEEK WISE DATA");

			String BudgetWagePercentage = data1.getText("BudgetWagePercentage");
			DBBdgtVal = sql.CLRexecuteQuery(getContext(),
					BudgetWagePercentage.replace("store", getContext().getStringProperty("store"))
							.replace("LYWk", getContext().getStringProperty("LYWk"))
							.replace("Week", getContext().getStringProperty("SelectFiscalWeek"))
							.replace("Division", getContext().getStringProperty("Division")));
			System.out.println("DB Value: " + DBBdgtVal);

			String BudgetWagePercentageTotal = data.getText("BudgetWagePercentageTotal");
			DBBdgtTtl = sql.CLRexecuteQuery(getContext(),
					BudgetWagePercentageTotal.replace("store", getContext().getStringProperty("store"))
							.replace("LYWk", getContext().getStringProperty("LYWk"))
							.replace("Week", getContext().getStringProperty("SelectFiscalWeek"))
							.replace("Division", getContext().getStringProperty("Division")));
			System.out.println("DB Value: " + DBBdgtTtl);

			pageDBComparisionPO.CompareValueDecimalFields(extentTest, DBBdgtVal, DBBdgtTtl, "Budget Wage Percentage");
		}
		if (OptionToView.contains("Forecast")) {
			Report_AddStep("testcase", "FORECAST WAGE PERCENTAGE COMBINED TABLE - WEEK WISE DATA", "", "", "");
			extentTest.log(LogStatus.INFO, "FORECAST WAGE PERCENTAGE COMBINED TABLE- WEEK WISE DATA");

			String ForecastWagePercentage = data1.getText("ForecastWagePercentage");
			DBBdgtVal = sql.CLRexecuteQuery(getContext(),
					ForecastWagePercentage.replace("store", getContext().getStringProperty("store"))
							.replace("LYWk", getContext().getStringProperty("LYWk"))
							.replace("Week", getContext().getStringProperty("SelectFiscalWeek"))
							.replace("Division", getContext().getStringProperty("Division")));
			System.out.println("DB Value: " + DBBdgtVal);

			String ForecastWagePercentageTotal = data.getText("ForecastWagePercentageTotal");
			DBBdgtTtl = sql.CLRexecuteQuery(getContext(),
					ForecastWagePercentageTotal.replace("store", getContext().getStringProperty("store"))
							.replace("LYWk", getContext().getStringProperty("LYWk"))
							.replace("Week", getContext().getStringProperty("SelectFiscalWeek"))
							.replace("Division", getContext().getStringProperty("Division")));
			System.out.println("DB Value: " + DBBdgtTtl);

			pageDBComparisionPO.CompareValueDecimalFields(extentTest, DBBdgtVal, DBBdgtTtl, "Forecast Wage Percentage");
		}
	}

	///// OR///
	@Test(description = "Weekly Trading Statement - Group, ORColumnValidationNewTable", priority = 9)
	public void ORColumnValidationNewTable() throws Exception {
		pageWeeklyPlanningPO = new WeeklyPlanningStorePage().createPage(getDriver(), getContext(), log, status, data,
				getReport(), getReportLogger(), WeeklyPlanningStorePage.class);
		pageDailyTradingStatementGroupPO = new MonthlyPlanningGroupPage().createPage(getDriver(), getContext(), log,
				status, data, getReport(), getReportLogger(), DailyTradingStatementGroupPage.class);
		TestStatus status = getTestStatus();
		OptionToView = getContext().getStringProperty("OptionToView");

		// ACTUAL OR:
		Report_AddStep("testcase", " ACTUAL OR COMBINED TABLE- WEEK WISE DATA", "", "", "");
		extentTest.log(LogStatus.INFO, "ACTUAL OR  COMBINED TABLE- WEEK WISE DATA");

		String ActualOR = data1.getText("ActualOR");
		DBActlVal = sql.CLRexecuteQuery(getContext(),
				ActualOR.replace("store", getContext().getStringProperty("store"))
						.replace("LYWk", getContext().getStringProperty("LYWk"))
						.replace("Week", getContext().getStringProperty("SelectFiscalWeek"))
						.replace("Division", getContext().getStringProperty("Division")));
		System.out.println("DB Value: " + DBActlVal);

		String ActualORTotal = data.getText("ActualORTotal");
		DBActlTtl = sql.CLRexecuteQuery(getContext(),
				ActualORTotal.replace("store", getContext().getStringProperty("store"))
						.replace("LYWk", getContext().getStringProperty("LYWk"))
						.replace("Week", getContext().getStringProperty("SelectFiscalWeek"))
						.replace("Division", getContext().getStringProperty("Division")));
		System.out.println("DB Value: " + DBActlTtl);
		pageDBComparisionPO.CompareValueDecimalFields(extentTest, DBActlVal, DBActlTtl, "Actual OR");

		// WEEKLY PLANNED OR:
		Report_AddStep("testcase", " WEEKLY PLANNED OR  COMBINED TABLE- WEEK WISE DATA", "", "", "");
		extentTest.log(LogStatus.INFO, "WEEKLY PLANNED OR COMBINED TABLE- WEEK WISE DATA");

		String WeeklyPlannedOR = data1.getText("WeeklyPlannedOR");
		DBPlndVal = sql.CLRexecuteQuery(getContext(),
				WeeklyPlannedOR.replace("store", getContext().getStringProperty("store"))
						.replace("LYWk", getContext().getStringProperty("LYWk"))
						.replace("Week", getContext().getStringProperty("SelectFiscalWeek"))
						.replace("Division", getContext().getStringProperty("Division")));
		System.out.println("DB Value: " + DBPlndVal);

		String WeeklyPlannedORTotal = data.getText("WeeklyPlannedORTotal");
		DBPlndTtl = sql.CLRexecuteQuery(getContext(),
				WeeklyPlannedORTotal.replace("store", getContext().getStringProperty("store"))
						.replace("LYWk", getContext().getStringProperty("LYWk"))
						.replace("Week", getContext().getStringProperty("SelectFiscalWeek"))
						.replace("Division", getContext().getStringProperty("Division")));
		System.out.println("DB Value: " + DBPlndTtl);

		pageDBComparisionPO.CompareValueDecimalFields(extentTest, DBPlndVal, DBPlndTtl, "Weekly Planned OR");

		// MONTHLY PLANNED OR:
		Report_AddStep("testcase", "MONTHLY PLANNED OR  COMBINED TABLE - WEEK WISE DATA", "", "", "");
		extentTest.log(LogStatus.INFO, "MONTHLY PLANNED OR  COMBINED TABLE- WEEK WISE DATA");

		String MonthlyPlannedOR = data1.getText("MonthlyPlannedOR");
		DBPlndVal = sql.CLRexecuteQuery(getContext(),
				MonthlyPlannedOR.replace("store", getContext().getStringProperty("store"))
						.replace("LYWk", getContext().getStringProperty("LYWk"))
						.replace("Week", getContext().getStringProperty("SelectFiscalWeek"))
						.replace("Division", getContext().getStringProperty("Division")));
		System.out.println("DB Value: " + DBPlndVal);

		String MonthlyPlannedORTotal = data.getText("MonthlyPlannedORTotal");
		DBPlndTtl = sql.CLRexecuteQuery(getContext(),
				MonthlyPlannedORTotal.replace("store", getContext().getStringProperty("store"))
						.replace("LYWk", getContext().getStringProperty("LYWk"))
						.replace("Week", getContext().getStringProperty("SelectFiscalWeek"))
						.replace("Division", getContext().getStringProperty("Division")));
		System.out.println("DB Value: " + DBPlndTtl);

		pageDBComparisionPO.CompareValueDecimalFields(extentTest, DBPlndVal, DBPlndTtl, "Monthly Planned OR");
	}

	///// CPH///
	@Test(description = "Weekly Trading Statement - Group, CPHColumnValidationNewTable", priority = 9)
	public void CPHColumnValidationNewTable() throws Exception {
		pageWeeklyPlanningPO = new WeeklyPlanningStorePage().createPage(getDriver(), getContext(), log, status, data,
				getReport(), getReportLogger(), WeeklyPlanningStorePage.class);
		pageDailyTradingStatementGroupPO = new MonthlyPlanningGroupPage().createPage(getDriver(), getContext(), log,
				status, data, getReport(), getReportLogger(), DailyTradingStatementGroupPage.class);
		TestStatus status = getTestStatus();
		OptionToView = getContext().getStringProperty("OptionToView");

		// ACTUAL CPH:
		Report_AddStep("testcase", " ACTUAL CPH COMBINED TABLE- WEEK WISE DATA", "", "", "");
		extentTest.log(LogStatus.INFO, "ACTUAL CPH  COMBINED TABLE- WEEK WISE DATA");

		String ActualCPH = data1.getText("ActualCPH");
		DBActlVal = sql.CLRexecuteQuery(getContext(),
				ActualCPH.replace("store", getContext().getStringProperty("store"))
						.replace("LYWk", getContext().getStringProperty("LYWk"))
						.replace("Week", getContext().getStringProperty("SelectFiscalWeek"))
						.replace("Division", getContext().getStringProperty("Division")));
		System.out.println("DB Value: " + DBActlVal);

		String ActualCPHTotal = data.getText("ActualCPHTotal");
		DBActlTtl = sql.CLRexecuteQuery(getContext(),
				ActualCPHTotal.replace("store", getContext().getStringProperty("store"))
						.replace("LYWk", getContext().getStringProperty("LYWk"))
						.replace("Week", getContext().getStringProperty("SelectFiscalWeek"))
						.replace("Division", getContext().getStringProperty("Division")));
		System.out.println("DB Value: " + DBActlTtl);
		pageDBComparisionPO.CompareValueDecimalFields(extentTest, DBActlVal, DBActlTtl, "Actual CPH");

		// WEEKLY PLANNED CPH:
		Report_AddStep("testcase", " WEEKLY PLANNED CPH  COMBINED TABLE- WEEK WISE DATA", "", "", "");
		extentTest.log(LogStatus.INFO, "WEEKLY PLANNED CPH COMBINED TABLE- WEEK WISE DATA");

		String WeeklyPlannedCPH = data1.getText("WeeklyPlannedCPH");
		DBPlndVal = sql.CLRexecuteQuery(getContext(),
				WeeklyPlannedCPH.replace("store", getContext().getStringProperty("store"))
						.replace("LYWk", getContext().getStringProperty("LYWk"))
						.replace("Week", getContext().getStringProperty("SelectFiscalWeek"))
						.replace("Division", getContext().getStringProperty("Division")));
		System.out.println("DB Value: " + DBPlndVal);

		String WeeklyPlannedCPHTotal = data.getText("WeeklyPlannedCPHTotal");
		DBPlndTtl = sql.CLRexecuteQuery(getContext(),
				WeeklyPlannedCPHTotal.replace("store", getContext().getStringProperty("store"))
						.replace("LYWk", getContext().getStringProperty("LYWk"))
						.replace("Week", getContext().getStringProperty("SelectFiscalWeek"))
						.replace("Division", getContext().getStringProperty("Division")));
		System.out.println("DB Value: " + DBPlndTtl);
		pageDBComparisionPO.CompareValueDecimalFields(extentTest, DBPlndVal, DBPlndTtl, "Weekly Planned CPH");

		// MONTHLY PLANNED CPH:
		Report_AddStep("testcase", "MONTHLY PLANNED CPH  COMBINED TABLE - WEEK WISE DATA", "", "", "");
		extentTest.log(LogStatus.INFO, "MONTHLY PLANNED CPH  - WEEK WISE DATA");

		String MonthlyPlannedCPH = data1.getText("MonthlyPlannedCPH");
		DBPlndVal = sql.CLRexecuteQuery(getContext(),
				MonthlyPlannedCPH.replace("store", getContext().getStringProperty("store"))
						.replace("LYWk", getContext().getStringProperty("LYWk"))
						.replace("Week", getContext().getStringProperty("SelectFiscalWeek"))
						.replace("Division", getContext().getStringProperty("Division")));
		System.out.println("DB Value: " + DBPlndVal);

		String MonthlyPlannedCPHTotal = data.getText("MonthlyPlannedCPHTotal");
		DBPlndTtl = sql.CLRexecuteQuery(getContext(),
				MonthlyPlannedCPHTotal.replace("store", getContext().getStringProperty("store"))
						.replace("LYWk", getContext().getStringProperty("LYWk"))
						.replace("Week", getContext().getStringProperty("SelectFiscalWeek"))
						.replace("Division", getContext().getStringProperty("Division")));
		System.out.println("DB Value: " + DBPlndTtl);

		pageDBComparisionPO.CompareValueDecimalFields(extentTest, DBPlndVal, DBPlndTtl, "Monthly Planned CPH");
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
		data1.getTCNameFromChildren(m.getName().toString().trim());
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
		pageDBComparisionPO = PageFactory.initElements(this.getDriver(), DBComparision.class);
		BaseObj = PageFactory.initElements(this.getDriver(), BasePage.class);
		common = PageFactory.initElements(this.getDriver(), CommonFunctions.class);
		storeprop = PageFactory.initElements(this.getDriver(), StoreProperties.class);
		objWeeklyPlanningStoreObjects = PageFactory.initElements(this.getDriver(), WeeklyPlanningStoreObjects.class);
		objWeeklyTradingStatementStoreObjects = PageFactory.initElements(this.getDriver(),
				WeeklyTradingStatementStoreObjects.class);
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
			data.loadTestDataXML(System.getProperty("user.dir") + "\\TestData\\WLP\\WeeklyTradingStatementGroup.xml");
			data1.loadTestDataXML(System.getProperty("user.dir") + "\\TestData\\WLP\\WeeklyTradingStatementZone.xml");
			customreport.createExtentReport();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

}