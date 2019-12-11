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

public class WeeklyTradingStatementStore extends TestBase {
	protected Logger log = LogManager.getLogger(WeeklyTradingStatementStore.class);
	TestStatus status;
	ExtentReportsScreenshotCode ScreenShotUtil;
	SQLWrapper sql = new SQLWrapper(log);
	WoWLoginPage loginPage;
	CommonRepository CommonFunc;
	WeeklyPlanningStoreObjects objWeeklyPlanningStoreObjects;
	WeeklyTradingStatementStoreObjects objWeeklyTradingStatementStoreObjects;
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
			ActlSMSHr, DBActlSMSHrTtl, DBActlSMSHr, DBVarSMSHrHr, DBVarSMSHrTtl, ORHr, ORHrTtl, DBORHr, DBORHrTtl, DBValSD, ActlSD,DBBdgtValLL, BdgtLL,
			DBValLL, ActlLL,DBBdgtValSD, BdgtSD;
	List<List<Object>> DBActlVal, DBActlTtl, DBPlndVal, DBPlndTtl, DBVarPlndVal, DBVarPlndTtl, DBLYSlsVal, DBLYSlsTtl,
			DBVarLYSlsVal, DBVarLYSlsTtl, DBBdgtVal, DBBdgtTtl, DBVarBdgtTtl, DBVarBdgtVal, DBAvgBsktTtl, DBAvgBsktVal,
			DBMixVal, DBMixTtl, DBPlndItmVal, DBPlndItmTtl, DBActlItmVal, DBActlItmTtl, DBPlndItmPriceVal,
			DBPlndItmPriceTtl, DBActlItmPriceVal, DBActlItmPriceTtl, DBThisYrCustVal, DBThisYrCustTtl, DBLstYrCustVal,
			DBLstYrCustTtl, DBDataPenTtl, DBDataPenVal, DBActlHrsVal, DBActlHrsTtl, DBVarSMSTtl, DBVarSMSVal,
			DBPlndHrsTtl, DBPlndHrsVal, DBVarPlndHrsVal, DBVarPlndHrsTtl, DBActlSMSTtl, DBActlSMSVal, DBActlORTtl,
			DBActlORVal, DBActlWgTtl, DBActlWgVal, DBActlWgPrcntTtl, DBActlWgPrcntVal, DBBdgtWgTtl, DBBdgtWgVal,
			DBBdgtWgPrcntTtl, DBBdgtWgPrcntVal, DBPlndCPHTtl, DBPlndCPHVal, DBPlndWgTtl, DBPlndWgVal, DBPlndWgPrcntTtl,
			DBPlndWgPrcntVal, DBCPHWgTtl, DBCPHWgVal, DBVarBdgtWgTtl, DBVarBdgtWgVal,DBAllowedWgVal, AllowedWgVal,DBAllowedWgTtl, AllowedWgTtl;
	
	List<List<Object>> DBActlValLL,DBActlValSD,DBPlndValLL, PlndLL,DBPlndValSD, PlndSD,DBVarPlndValLL, VarPlndLL,DBVarPlndValSD, VarPlndSD,
	DBLYSlsValLL, LYSlsValLL,DBLYSlsValSD, LYSlsValSD,DBVarLYSlsValLL, VarLYSlsLL,DBVarLYSlsValSD, VarLYSlsSD,DBVarBdgtValLL, VarBdgtLL,DBVarBdgtValSD, VarBdgtSD,
	DBAvgBsktValLL, AvgBsktLL,DBAvgBsktValSD, AvgBsktSD,DBMixValLL, MixLL,DBMixValSD, MixSD,DBPlndItmValLL, PlndItmLL,DBPlndItmValSD, PlndItmSD,
	 DBActlItmValLL, ActlItmLL, DBActlItmValSD, ActlItmSD,DBPlndItmPriceValLL, PlndItmPriceLL,DBPlndItmPriceValSD, PlndItmPriceSD,
	 DBActlItmPriceValSD, ActlItmPriceSD, DBActlItmPriceValLL, ActlItmPriceLL,DBDataPenValLL, DataPenLL,DBDataPenValSD, DataPenSD,
	 DBThisYrCustValLL, ThisYrCustLL,DBThisYrCustValSD, ThisYrCustSD,DBLstYrCustValLL, LstYrCustLL,DBLstYrCustValSD, LstYrCustSD,
	 DBVarSMSHrHrLL, VarSMSHrLL,DBVarSMSHrHrSD, VarSMSHrSD,DBActlHrsValLL, ActlHrsLL,DBActlHrsValSD, ActlHrsSD,DBPlndHrsValLL, PlndHrsLL,DBPlndHrsValSD, PlndHrsSD,
	 DBVarPlndHrsValLL, VarPlndHrsLL,DBVarPlndHrsValSD, VarPlndHrsSD,DBActlSMSHrLL, ActlSMSHrLL,DBActlSMSHrSD, ActlSMSHrSD,DBORHrLL, ORHrLL,DBORHrSD, ORHrSD,
	 DBActlWgValLL, ActlWgLL,DBActlWgValSD, ActlWgSD,DBActlWgPrcntValLL, ActlWgPrcntLL,DBActlWgPrcntValSD, ActlWgPrcntSD,DBBdgtWgValLL, BdgtWgLL,DBBdgtWgValSD, BdgtWgSD,
	 DBAllowedWgValLL, AllowedWgLL,DBAllowedWgValSD, AllowedWgSD,DBBdgtWgPrcntValLL, BdgtWgPrcntLL,DBBdgtWgPrcntValSD, BdgtWgPrcntSD,DBPlndCPHValLL, PlndCPHLL,
	 DBPlndCPHValSD, PlndCPHSD,DBPlndWgValLL, PlndWgLL,DBPlndWgValSD, PlndWgSD,DBPlndWgPrcntValLL, PlndWgPrcntLL,DBPlndWgPrcntValSD, PlndWgPrcntSD,
	 DBCPHWgValLL, CPHWgLL,DBCPHWgValSD, CPHWgSD; 
	String OptionToView;

	public WeeklyTradingStatementStore() {
		super.log = log;
	}

	@Test(priority = 0)
	public void LogintoCLRApplication() throws Exception {
		loginPage = new WoWLoginPage().createPage(getDriver(), getContext(), log, status, data, report, extentTest,
				WoWLoginPage.class);
		pageWeeklyPlanningPO = loginPage.navToWeeklyPlanningStorePg(getContext().getStringProperty("store"));
	}

	// Sales
	@Test(description = "Weekly Trading Statement - Store, SalesColumnValidation", priority = 1)
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
				objWeeklyPlanningStoreObjects.screenToViewWeeklyTrading, "Store");
		/*pageWeeklyPlanningPO.verifyPageName(extentTest, objWeeklyPlanningStoreObjects.DataFrame,
				objWeeklyPlanningStoreObjects.pageName, getContext().getStringProperty("WTSStore"));*/

		OptionToView = getContext().getStringProperty("OptionToView");

		// ACTUAL SALES:
		Report_AddStep("testcase", " ACTUAL SALES - WEEK WISE DATA", "", "", "");
		extentTest = report.startTest("Actual Sales");

		String ActualSales = data.getText("ActualSales");
		DBActlVal = sql.CLRexecuteQuery(getContext(),
				ActualSales.replace("store", getContext().getStringProperty("store"))
						.replace("LYWk", getContext().getStringProperty("LYWk"))
						.replace("Week", getContext().getStringProperty("SelectFiscalWeek"))
						.replace("Division", getContext().getStringProperty("Division")));
		System.out.println("DB Value: " + DBActlVal);

		String ActualSalesTotal = data.getText("ActualSalesTotal");
		DBActlTtl = sql.CLRexecuteQuery(getContext(),
				ActualSalesTotal.replace("store", getContext().getStringProperty("store"))
						.replace("LYWk", getContext().getStringProperty("LYWk"))
						.replace("Week", getContext().getStringProperty("SelectFiscalWeek"))
						.replace("Division", getContext().getStringProperty("Division")));
		System.out.println("DB Value: " + DBActlTtl);

		ActlVal = pageDailyTradingStatementGroupPO.GetRowWiseResult(objWeeklyTradingStatementStoreObjects,
				objWeeklyTradingStatementStoreObjects.Departments, objWeeklyTradingStatementStoreObjects.ActualSales,
				objWeeklyTradingStatementStoreObjects.DataTable);
		ActlTtl = pageDailyTradingStatementGroupPO.WeekToDateUITotalWTS(objWeeklyTradingStatementStoreObjects.TotalTitle,
				objWeeklyTradingStatementStoreObjects.ActualSalesTtl,objWeeklyTradingStatementStoreObjects.DataTable);

		pageWeeklyPlanningPO.CompareValues(extentTest, DBActlVal, ActlVal);
		pageMonthlyPlanningPO.CompareValues(extentTest, DBActlTtl, ActlTtl);

		// PLANNED SALES:
		Report_AddStep("testcase", " PLANNED SALES - WEEK WISE DATA", "", "", "");
		extentTest = report.startTest("Planned Sales");

		String PlannedSales = data.getText("PlannedSales");
		DBPlndVal = sql.CLRexecuteQuery(getContext(),
				PlannedSales.replace("store", getContext().getStringProperty("store"))
						.replace("LYWk", getContext().getStringProperty("LYWk"))
						.replace("Week", getContext().getStringProperty("SelectFiscalWeek"))
						.replace("Division", getContext().getStringProperty("Division")));
		System.out.println("DB Value: " + DBPlndVal);

		String PlannedSalesTotal = data.getText("PlannedSalesTotal");
		DBPlndTtl = sql.CLRexecuteQuery(getContext(),
				PlannedSalesTotal.replace("store", getContext().getStringProperty("store"))
						.replace("LYWk", getContext().getStringProperty("LYWk"))
						.replace("Week", getContext().getStringProperty("SelectFiscalWeek"))
						.replace("Division", getContext().getStringProperty("Division")));
		System.out.println("DB Value: " + DBPlndTtl);

		PlndVal = pageDailyTradingStatementGroupPO.GetRowWiseResult(objWeeklyTradingStatementStoreObjects,
				objWeeklyTradingStatementStoreObjects.Departments, objWeeklyTradingStatementStoreObjects.PlannedSales,
				objWeeklyTradingStatementStoreObjects.DataTable);
		PlndTtl = pageDailyTradingStatementGroupPO.WeekToDateUITotalWTS(objWeeklyTradingStatementStoreObjects.TotalTitle,
				objWeeklyTradingStatementStoreObjects.PlannedSalesTtl,objWeeklyTradingStatementStoreObjects.DataTable);

		pageWeeklyPlanningPO.CompareValues(extentTest, DBPlndVal, PlndVal);
		pageMonthlyPlanningPO.CompareValues(extentTest, DBPlndTtl, PlndTtl);

		// VARIANCE IN PLANNED SALES:
		Report_AddStep("testcase", " VARIANCE IN PLANNED SALES - WEEK WISE DATA", "", "", "");
		extentTest = report.startTest("Variance Sales");

		String VarSales = data.getText("VarSales");
		DBVarPlndVal = sql.CLRexecuteQuery(getContext(),
				VarSales.replace("store", getContext().getStringProperty("store"))
						.replace("LYWk", getContext().getStringProperty("LYWk"))
						.replace("Week", getContext().getStringProperty("SelectFiscalWeek"))
						.replace("Division", getContext().getStringProperty("Division")));
		System.out.println("DB Value: " + DBVarPlndVal);

		String VarSalesTotal = data.getText("VarSalesTotal");
		DBVarPlndTtl = sql.CLRexecuteQuery(getContext(),
				VarSalesTotal.replace("store", getContext().getStringProperty("store"))
						.replace("LYWk", getContext().getStringProperty("LYWk"))
						.replace("Week", getContext().getStringProperty("SelectFiscalWeek"))
						.replace("Division", getContext().getStringProperty("Division")));
		System.out.println("DB Value: " + DBVarPlndTtl);

		VarPlndVal = pageDailyTradingStatementGroupPO.GetRowWiseResult(objWeeklyTradingStatementStoreObjects,
				objWeeklyTradingStatementStoreObjects.Departments,
				objWeeklyTradingStatementStoreObjects.PlannedSalesGrwth,
				objWeeklyTradingStatementStoreObjects.DataTable);
		VarPlndTtl = pageDailyTradingStatementGroupPO.WeekToDateUITotalWTS(
				objWeeklyTradingStatementStoreObjects.TotalTitle,
				objWeeklyTradingStatementStoreObjects.PlannedSalesGrwthTtl,objWeeklyTradingStatementStoreObjects.DataTable);

		pageWeeklyPlanningPO.CompareValues(extentTest, DBVarPlndVal, VarPlndVal);
		pageMonthlyPlanningPO.CompareValues(extentTest, DBVarPlndTtl, VarPlndTtl);

		// LAST YEAR SALES:
		Report_AddStep("testcase", " LAST YEAR SALES - WEEK WISE DATA", "", "", "");
		extentTest = report.startTest("LY Sales");

		String LYSales = data.getText("LYSales");
		DBLYSlsVal = sql.CLRexecuteQuery(getContext(),
				LYSales.replace("store", getContext().getStringProperty("store"))
						.replace("LYWk", getContext().getStringProperty("LYWk"))
						.replace("Week", getContext().getStringProperty("SelectFiscalWeek"))
						.replace("Division", getContext().getStringProperty("Division")));
		System.out.println("DB Value: " + DBLYSlsVal);

		String LYSalesTotal = data.getText("LYSalesTotal");
		DBLYSlsTtl = sql.CLRexecuteQuery(getContext(),
				LYSalesTotal.replace("store", getContext().getStringProperty("store"))
						.replace("LYWk", getContext().getStringProperty("LYWk"))
						.replace("Week", getContext().getStringProperty("SelectFiscalWeek"))
						.replace("Division", getContext().getStringProperty("Division")));
		System.out.println("DB Value: " + DBLYSlsTtl);

		LYSlsVal = pageDailyTradingStatementGroupPO.GetRowWiseResult(objWeeklyTradingStatementStoreObjects,
				objWeeklyTradingStatementStoreObjects.Departments, objWeeklyTradingStatementStoreObjects.LYSales,
				objWeeklyTradingStatementStoreObjects.DataTable);
		LYSlsTtl = pageDailyTradingStatementGroupPO.WeekToDateUITotalWTS(objWeeklyTradingStatementStoreObjects.TotalTitle,
				objWeeklyTradingStatementStoreObjects.LYSalesTtl,objWeeklyTradingStatementStoreObjects.DataTable);

		pageWeeklyPlanningPO.CompareValues(extentTest, DBLYSlsVal, LYSlsVal);
		pageMonthlyPlanningPO.CompareValues(extentTest, DBLYSlsTtl, LYSlsTtl);

		// VARIANCE IN LAST YEAR SALES:
		Report_AddStep("testcase", " VARIANCE IN LAST YEAR SALES - WEEK WISE DATA", "", "", "");
		extentTest = report.startTest("Variance LY Sales");

		String LYSalesPct = data.getText("LYSalesPct");
		DBVarLYSlsVal = sql.CLRexecuteQuery(getContext(),
				LYSalesPct.replace("store", getContext().getStringProperty("store"))
						.replace("LYWk", getContext().getStringProperty("LYWk"))
						.replace("Week", getContext().getStringProperty("SelectFiscalWeek"))
						.replace("Division", getContext().getStringProperty("Division")));
		System.out.println("DB Value: " + DBVarLYSlsVal);

		String LYSalesPctTotal = data.getText("LYSalesPctTotal");
		DBVarLYSlsTtl = sql.CLRexecuteQuery(getContext(),
				LYSalesPctTotal.replace("store", getContext().getStringProperty("store"))
						.replace("LYWk", getContext().getStringProperty("LYWk"))
						.replace("Week", getContext().getStringProperty("SelectFiscalWeek"))
						.replace("Division", getContext().getStringProperty("Division")));
		System.out.println("DB Value: " + DBVarLYSlsTtl);

		VarLYSlsVal = pageDailyTradingStatementGroupPO.GetRowWiseResult(objWeeklyTradingStatementStoreObjects,
				objWeeklyTradingStatementStoreObjects.Departments, objWeeklyTradingStatementStoreObjects.LYSalesGrwth,
				objWeeklyTradingStatementStoreObjects.DataTable);
		VarLYSlsTtl = pageDailyTradingStatementGroupPO.WeekToDateUITotalWTS(
				objWeeklyTradingStatementStoreObjects.TotalTitle,
				objWeeklyTradingStatementStoreObjects.LYSalesGrwthTtl,objWeeklyTradingStatementStoreObjects.DataTable);

		pageWeeklyPlanningPO.CompareValues(extentTest, DBVarLYSlsVal, VarLYSlsVal);
		pageMonthlyPlanningPO.CompareValues(extentTest, DBVarLYSlsTtl, VarLYSlsTtl);

		// BUDGET SALES:
		if (OptionToView.contains("Budget")) {
			Report_AddStep("testcase", "BUDGET SALES - WEEK WISE DATA", "", "", "");
			extentTest = report.startTest("Budget Sales");

			String BudgetSales = data.getText("BudgetSales");
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

			BdgtVal = pageDailyTradingStatementGroupPO.GetRowWiseResult(objWeeklyTradingStatementStoreObjects,
					objWeeklyTradingStatementStoreObjects.Departments,
					objWeeklyTradingStatementStoreObjects.BudgetSales, objWeeklyTradingStatementStoreObjects.DataTable);
			BdgtTtl = pageDailyTradingStatementGroupPO.WeekToDateUITotalWTS(
					objWeeklyTradingStatementStoreObjects.TotalTitle,
					objWeeklyTradingStatementStoreObjects.BudgetSalesTtl,objWeeklyTradingStatementStoreObjects.DataTable);

			pageWeeklyPlanningPO.CompareValues(extentTest, DBBdgtVal, BdgtVal);
			pageMonthlyPlanningPO.CompareValues(extentTest, DBBdgtTtl, BdgtTtl);
		}
		if (OptionToView.contains("Forecast")) {
			Report_AddStep("testcase", "FORECAST SALES - WEEK WISE DATA", "", "", "");
			extentTest = report.startTest("Forecast Sales");


			String ForecastSales = data.getText("ForecastSales");
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

			BdgtVal = pageDailyTradingStatementGroupPO.GetRowWiseResult(objWeeklyTradingStatementStoreObjects,
					objWeeklyTradingStatementStoreObjects.Departments,
					objWeeklyTradingStatementStoreObjects.BudgetSales, objWeeklyTradingStatementStoreObjects.DataTable);
			BdgtTtl = pageDailyTradingStatementGroupPO.WeekToDateUITotalWTS(
					objWeeklyTradingStatementStoreObjects.TotalTitle,
					objWeeklyTradingStatementStoreObjects.BudgetSalesTtl,objWeeklyTradingStatementStoreObjects.DataTable);

			pageWeeklyPlanningPO.CompareValues(extentTest, DBBdgtVal, BdgtVal);
			pageMonthlyPlanningPO.CompareValues(extentTest, DBBdgtTtl, BdgtTtl);
		}

		// VARIANCE IN BUDGET SALES:
		if (OptionToView.contains("Budget")) {
			Report_AddStep("testcase", "VARIANCE IN BUDGET SALES - WEEK WISE DATA", "", "", "");
			extentTest = report.startTest("Variance Budget Sales");

			String VarBudgetSales = data.getText("VarBudgetSales");
			DBVarBdgtVal = sql.CLRexecuteQuery(getContext(),
					VarBudgetSales.replace("store", getContext().getStringProperty("store"))
							.replace("LYWk", getContext().getStringProperty("LYWk"))
							.replace("Week", getContext().getStringProperty("SelectFiscalWeek"))
							.replace("Division", getContext().getStringProperty("Division")));
			System.out.println("DB Value: " + DBVarBdgtVal);

			String VarBudgetSalesTotal = data.getText("VarBudgetSalesTotal");
			DBVarBdgtTtl = sql.CLRexecuteQuery(getContext(),
					VarBudgetSalesTotal.replace("store", getContext().getStringProperty("store"))
							.replace("LYWk", getContext().getStringProperty("LYWk"))
							.replace("Week", getContext().getStringProperty("SelectFiscalWeek"))
							.replace("Division", getContext().getStringProperty("Division")));
			System.out.println("DB Value: " + DBVarBdgtTtl);

			VarBdgtVal = pageDailyTradingStatementGroupPO.GetRowWiseResult(objWeeklyTradingStatementStoreObjects,
					objWeeklyTradingStatementStoreObjects.Departments,
					objWeeklyTradingStatementStoreObjects.BudgetSalesGrwth,
					objWeeklyTradingStatementStoreObjects.DataTable);
			VarBdgtTtl = pageDailyTradingStatementGroupPO.WeekToDateUITotalWTS(
					objWeeklyTradingStatementStoreObjects.TotalTitle,
					objWeeklyTradingStatementStoreObjects.BudgetSalesGrwthTtl,objWeeklyTradingStatementStoreObjects.DataTable);

			pageWeeklyPlanningPO.CompareValues(extentTest, DBVarBdgtVal, VarBdgtVal);
			pageMonthlyPlanningPO.CompareValues(extentTest, DBVarBdgtTtl, VarBdgtTtl);
		}
		if (OptionToView.contains("Forecast")) {
			Report_AddStep("testcase", "VARIANCE IN FORECAST SALES - WEEK WISE DATA", "", "", "");
			extentTest = report.startTest("Variance Forecast Sales");

			String VarFcstSales = data.getText("VarFcstSales");
			DBVarBdgtVal = sql.CLRexecuteQuery(getContext(),
					VarFcstSales.replace("store", getContext().getStringProperty("store"))
							.replace("LYWk", getContext().getStringProperty("LYWk"))
							.replace("Week", getContext().getStringProperty("SelectFiscalWeek"))
							.replace("Division", getContext().getStringProperty("Division")));
			System.out.println("DB Value: " + DBVarBdgtVal);

			String VarFcstSalesTotal = data.getText("VarFcstSalesTotal");
			DBVarBdgtTtl = sql.CLRexecuteQuery(getContext(),
					VarFcstSalesTotal.replace("store", getContext().getStringProperty("store"))
							.replace("LYWk", getContext().getStringProperty("LYWk"))
							.replace("Week", getContext().getStringProperty("SelectFiscalWeek"))
							.replace("Division", getContext().getStringProperty("Division")));
			System.out.println("DB Value: " + DBVarBdgtTtl);

			VarBdgtVal = pageDailyTradingStatementGroupPO.GetRowWiseResult(objWeeklyTradingStatementStoreObjects,
					objWeeklyTradingStatementStoreObjects.Departments,
					objWeeklyTradingStatementStoreObjects.BudgetSalesGrwth,
					objWeeklyTradingStatementStoreObjects.DataTable);
			VarBdgtTtl = pageDailyTradingStatementGroupPO.WeekToDateUITotalWTS(
					objWeeklyTradingStatementStoreObjects.TotalTitle,
					objWeeklyTradingStatementStoreObjects.BudgetSalesGrwthTtl,objWeeklyTradingStatementStoreObjects.DataTable);

			pageWeeklyPlanningPO.CompareValues(extentTest, DBVarBdgtVal, VarBdgtVal);
			pageMonthlyPlanningPO.CompareValues(extentTest, DBVarBdgtTtl, VarBdgtTtl);
		}

		// AVERAGE BASKET SALES:
		Report_AddStep("testcase", "AVERAGE BASKET SALES - WEEK WISE DATA", "", "", "");
		extentTest = report.startTest("Average Basket Sales");

		String AvgBasketSales = data.getText("AvgBasketSales");
		DBAvgBsktVal = sql.CLRexecuteQuery(getContext(),
				AvgBasketSales.replace("store", getContext().getStringProperty("store"))
						.replace("LYWk", getContext().getStringProperty("LYWk"))
						.replace("Week", getContext().getStringProperty("SelectFiscalWeek"))
						.replace("Division", getContext().getStringProperty("Division")));
		System.out.println("DB Value: " + DBAvgBsktVal);

		String AvgBasketSalesTotal = data.getText("AvgBasketSalesTotal");
		DBAvgBsktTtl = sql.CLRexecuteQuery(getContext(),
				AvgBasketSalesTotal.replace("store", getContext().getStringProperty("store"))
						.replace("LYWk", getContext().getStringProperty("LYWk"))
						.replace("Week", getContext().getStringProperty("SelectFiscalWeek"))
						.replace("Division", getContext().getStringProperty("Division")));
		System.out.println("DB Value: " + DBAvgBsktTtl);

		AvgBsktVal = pageDailyTradingStatementGroupPO.GetRowWiseResult(objWeeklyTradingStatementStoreObjects,
				objWeeklyTradingStatementStoreObjects.Departments, objWeeklyTradingStatementStoreObjects.AvgBasketSales,
				objWeeklyTradingStatementStoreObjects.DataTable);
		AvgBsktTtl = pageDailyTradingStatementGroupPO.WeekToDateUITotalWTS(
				objWeeklyTradingStatementStoreObjects.TotalTitle,
				objWeeklyTradingStatementStoreObjects.AvgBasketSalesTtl,objWeeklyTradingStatementStoreObjects.DataTable);

		pageWeeklyPlanningPO.CompareValues(extentTest, DBAvgBsktVal, AvgBsktVal);
		pageMonthlyPlanningPO.CompareValues(extentTest, DBAvgBsktTtl, AvgBsktTtl);

		// MIX SALES:
		Report_AddStep("testcase", "MIX SALES - WEEK WISE DATA", "", "", "");
		extentTest = report.startTest("Mix Sales");

		String WkyMixSales = data.getText("WkyMixSales");
		DBMixVal = sql.CLRexecuteQuery(getContext(),
				WkyMixSales.replace("store", getContext().getStringProperty("store"))
						.replace("LYWk", getContext().getStringProperty("LYWk"))
						.replace("Week", getContext().getStringProperty("SelectFiscalWeek"))
						.replace("Division", getContext().getStringProperty("Division")));
		System.out.println("DB Value: " + DBMixVal);

		String WkyMixSalesTotal = data.getText("WkyMixSalesTotal");
		DBMixTtl = sql.CLRexecuteQuery(getContext(),
				WkyMixSalesTotal.replace("store", getContext().getStringProperty("store"))
						.replace("LYWk", getContext().getStringProperty("LYWk"))
						.replace("Week", getContext().getStringProperty("SelectFiscalWeek"))
						.replace("Division", getContext().getStringProperty("Division")));
		System.out.println("DB Value: " + DBMixTtl);

		MixVal = pageDailyTradingStatementGroupPO.GetRowWiseResult(objWeeklyTradingStatementStoreObjects,
				objWeeklyTradingStatementStoreObjects.Departments, objWeeklyTradingStatementStoreObjects.Mix,
				objWeeklyTradingStatementStoreObjects.DataTable);
		MixTtl = pageDailyTradingStatementGroupPO.WeekToDateUITotalWTS(objWeeklyTradingStatementStoreObjects.TotalTitle,
				objWeeklyTradingStatementStoreObjects.MixTtl,objWeeklyTradingStatementStoreObjects.DataTable);

		pageWeeklyPlanningPO.CompareValues(extentTest, DBMixVal, MixVal);
		pageMonthlyPlanningPO.CompareValues(extentTest, DBMixTtl, MixTtl);
	}

	// Items and Item Price
	@Test(description = "Weekly Trading Statement - Store, Planned Item", priority = 2)
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
				PlannedItems.replace("store", getContext().getStringProperty("store"))
						.replace("LYWk", getContext().getStringProperty("LYWk"))
						.replace("Week", getContext().getStringProperty("SelectFiscalWeek"))
						.replace("Division", getContext().getStringProperty("Division")));
		System.out.println("DB Value: " + DBPlndItmVal);

		String PlannedItemsTotal = data.getText("PlannedItemsTotal");
		DBPlndItmTtl = sql.CLRexecuteQuery(getContext(),
				PlannedItemsTotal.replace("store", getContext().getStringProperty("store"))
						.replace("LYWk", getContext().getStringProperty("LYWk"))
						.replace("Week", getContext().getStringProperty("SelectFiscalWeek"))
						.replace("Division", getContext().getStringProperty("Division")));
		System.out.println("DB Value: " + DBPlndItmTtl);

		PlndItmVal = pageDailyTradingStatementGroupPO.GetRowWiseResult(objWeeklyTradingStatementStoreObjects,
				objWeeklyTradingStatementStoreObjects.Departments, objWeeklyTradingStatementStoreObjects.PlannedItem,
				objWeeklyTradingStatementStoreObjects.DataTable);
		PlndItmTtl = pageDailyTradingStatementGroupPO.WeekToDateUITotalWTS(
				objWeeklyTradingStatementStoreObjects.TotalTitle, objWeeklyTradingStatementStoreObjects.PlannedItemTtl,objWeeklyTradingStatementStoreObjects.DataTable);

		pageWeeklyPlanningPO.CompareValues(extentTest, DBPlndItmVal, PlndItmVal);
		pageMonthlyPlanningPO.CompareValues(extentTest, DBPlndItmTtl, PlndItmTtl);

		// ACTUAL ITEMS:
		Report_AddStep("testcase", "ACTUAL ITEMS - WEEK WISE DATA", "", "", "");
		extentTest = report.startTest("Actual Items");

		String ActualItems = data.getText("ActualItems");
		DBActlItmVal = sql.CLRexecuteQuery(getContext(),
				ActualItems.replace("store", getContext().getStringProperty("store"))
						.replace("LYWk", getContext().getStringProperty("LYWk"))
						.replace("Week", getContext().getStringProperty("SelectFiscalWeek"))
						.replace("Division", getContext().getStringProperty("Division")));
		System.out.println("DB Value: " + DBActlItmVal);

		String ActualItemsTotal = data.getText("ActualItemsTotal");
		DBActlItmTtl = sql.CLRexecuteQuery(getContext(),
				ActualItemsTotal.replace("store", getContext().getStringProperty("store"))
						.replace("LYWk", getContext().getStringProperty("LYWk"))
						.replace("Week", getContext().getStringProperty("SelectFiscalWeek"))
						.replace("Division", getContext().getStringProperty("Division")));
		System.out.println("DB Value: " + DBActlItmTtl);

		ActlItmVal = pageDailyTradingStatementGroupPO.GetRowWiseResult(objWeeklyTradingStatementStoreObjects,
				objWeeklyTradingStatementStoreObjects.Departments, objWeeklyTradingStatementStoreObjects.ActualItem,
				objWeeklyTradingStatementStoreObjects.DataTable);
		ActlItmTtl = pageDailyTradingStatementGroupPO.WeekToDateUITotalWTS(
				objWeeklyTradingStatementStoreObjects.TotalTitle, objWeeklyTradingStatementStoreObjects.ActualItemTtl,objWeeklyTradingStatementStoreObjects.DataTable);

		pageWeeklyPlanningPO.CompareValues(extentTest, DBActlItmVal, ActlItmVal);
		pageMonthlyPlanningPO.CompareValues(extentTest, DBActlItmTtl, ActlItmTtl);

		// PLANNED ITEM PRICE:
		Report_AddStep("testcase", "PLANNED ITEM PRICE - WEEK WISE DATA", "", "", "");
		extentTest = report.startTest("Planned Item Price");

		String PlannedItemPrice = data.getText("PlannedItemPrice");
		DBPlndItmPriceVal = sql.CLRexecuteQuery(getContext(),
				PlannedItemPrice.replace("store", getContext().getStringProperty("store"))
						.replace("LYWk", getContext().getStringProperty("LYWk"))
						.replace("Week", getContext().getStringProperty("SelectFiscalWeek"))
						.replace("Division", getContext().getStringProperty("Division")));
		System.out.println("DB Value: " + DBPlndItmPriceVal);

		String PlannedItemPriceTotal = data.getText("PlannedItemPriceTotal");
		DBPlndItmPriceTtl = sql.CLRexecuteQuery(getContext(),
				PlannedItemPriceTotal.replace("store", getContext().getStringProperty("store"))
						.replace("LYWk", getContext().getStringProperty("LYWk"))
						.replace("Week", getContext().getStringProperty("SelectFiscalWeek"))
						.replace("Division", getContext().getStringProperty("Division")));
		System.out.println("DB Value: " + DBPlndItmPriceTtl);

		PlndItmPriceVal = pageDailyTradingStatementGroupPO.GetRowWiseResult(objWeeklyTradingStatementStoreObjects,
				objWeeklyTradingStatementStoreObjects.Departments,
				objWeeklyTradingStatementStoreObjects.PlannedItemPrice,
				objWeeklyTradingStatementStoreObjects.DataTable);
		PlndItmPriceTtl = pageDailyTradingStatementGroupPO.WeekToDateUITotalWTS(
				objWeeklyTradingStatementStoreObjects.TotalTitle,
				objWeeklyTradingStatementStoreObjects.PlannedItemPriceTtl,objWeeklyTradingStatementStoreObjects.DataTable);

		pageWeeklyPlanningPO.CompareValueDecimalFields(extentTest, DBPlndItmPriceVal, PlndItmPriceVal);
		pageMonthlyPlanningPO.CompareValueDecimalFields(extentTest, DBPlndItmPriceTtl, PlndItmPriceTtl);

		// ACTUAL ITEM PRICE:
		Report_AddStep("testcase", "ACTUAL ITEM PRICE - WEEK WISE DATA", "", "", "");
		extentTest = report.startTest(" Actuals Item Price");

		String ActualItemPrice = data.getText("ActualItemPrice");
		DBActlItmPriceVal = sql.CLRexecuteQuery(getContext(),
				ActualItemPrice.replace("store", getContext().getStringProperty("store"))
						.replace("LYWk", getContext().getStringProperty("LYWk"))
						.replace("Week", getContext().getStringProperty("SelectFiscalWeek"))
						.replace("Division", getContext().getStringProperty("Division")));
		System.out.println("DB Value: " + DBActlItmPriceVal);

		String ActualItemPriceTotal = data.getText("ActualItemPriceTotal");
		DBActlItmPriceTtl = sql.CLRexecuteQuery(getContext(),
				ActualItemPriceTotal.replace("store", getContext().getStringProperty("store"))
						.replace("LYWk", getContext().getStringProperty("LYWk"))
						.replace("Week", getContext().getStringProperty("SelectFiscalWeek"))
						.replace("Division", getContext().getStringProperty("Division")));
		System.out.println("DB Value: " + DBActlItmPriceTtl);

		ActlItmPriceVal = pageDailyTradingStatementGroupPO.GetRowWiseResult(objWeeklyTradingStatementStoreObjects,
				objWeeklyTradingStatementStoreObjects.Departments,
				objWeeklyTradingStatementStoreObjects.ActualItemPrice, objWeeklyTradingStatementStoreObjects.DataTable);
		ActlItmPriceTtl = pageDailyTradingStatementGroupPO.WeekToDateUITotalWTS(
				objWeeklyTradingStatementStoreObjects.TotalTitle,
				objWeeklyTradingStatementStoreObjects.ActualItemPriceTtl,objWeeklyTradingStatementStoreObjects.DataTable);

		pageWeeklyPlanningPO.CompareValueDecimalFields(extentTest, DBActlItmPriceVal, ActlItmPriceVal);
		pageMonthlyPlanningPO.CompareValueDecimalFields(extentTest, DBActlItmPriceTtl, ActlItmPriceTtl);
	}

	// Customers
	@Test(description = "Weekly Trading Statement - Store, This Year Customer", priority = 3)
	public void CustomersColumnValidation() throws Exception {
		pageWeeklyPlanningPO = new WeeklyPlanningStorePage().createPage(getDriver(), getContext(), log, status, data,
				getReport(), getReportLogger(), WeeklyPlanningStorePage.class);
		pageDailyTradingStatementGroupPO = new MonthlyPlanningGroupPage().createPage(getDriver(), getContext(), log,
				status, data, getReport(), getReportLogger(), DailyTradingStatementGroupPage.class);
		TestStatus status = getTestStatus();
		System.out.println("Entering into TestCase - Customer View");

		// THIS YEAR - CUSTOMER:
		Report_AddStep("testcase", "CUSTOMER THIS YEAR VALUE - WEEK WISE DATA", "", "", "");
		extentTest = report.startTest("Customer This year");

		String ThisYrActlCustomers = data.getText("ThisYrActlCustomers");
		DBThisYrCustVal = sql.CLRexecuteQuery(getContext(),
				ThisYrActlCustomers.replace("store", getContext().getStringProperty("store"))
						.replace("LYWk", getContext().getStringProperty("LYWk"))
						.replace("Week", getContext().getStringProperty("SelectFiscalWeek"))
						.replace("Division", getContext().getStringProperty("Division")));
		System.out.println("DB Value: " + DBThisYrCustVal);

		String ThisYrActlCustomersTotal = data.getText("ThisYrActlCustomersTotal");
		DBThisYrCustTtl = sql.CLRexecuteQuery(getContext(),
				ThisYrActlCustomersTotal.replace("store", getContext().getStringProperty("store"))
						.replace("LYWk", getContext().getStringProperty("LYWk"))
						.replace("Week", getContext().getStringProperty("SelectFiscalWeek"))
						.replace("Division", getContext().getStringProperty("Division")));
		System.out.println("DB Value: " + DBThisYrCustTtl);

		ThisYrCustVal = pageDailyTradingStatementGroupPO.GetRowWiseResult(objWeeklyTradingStatementStoreObjects,
				objWeeklyTradingStatementStoreObjects.Departments, objWeeklyTradingStatementStoreObjects.ThisYrCustomer,
				objWeeklyTradingStatementStoreObjects.DataTable);
		ThisYrCustTtl = pageDailyTradingStatementGroupPO.WeekToDateUITotalWTS(
				objWeeklyTradingStatementStoreObjects.TotalTitle,
				objWeeklyTradingStatementStoreObjects.ThisYrCustomerTtl,objWeeklyTradingStatementStoreObjects.DataTable);

		pageWeeklyPlanningPO.CompareValues(extentTest, DBThisYrCustVal, ThisYrCustVal);
		pageMonthlyPlanningPO.CompareValues(extentTest, DBThisYrCustTtl, ThisYrCustTtl);

		// % LAST YEAR CUSTOMER:
		Report_AddStep("testcase", "PERCENTAGE LAST YEAR CUSTOMER - WEEK WISE DATA", "", "", "");
		extentTest = report.startTest("Customer LY year");

		String PctLastYrCustomers = data.getText("PctLastYrCustomers");
		DBLstYrCustVal = sql.CLRexecuteQuery(getContext(),
				PctLastYrCustomers.replace("store", getContext().getStringProperty("store"))
						.replace("LYWk", getContext().getStringProperty("LYWk"))
						.replace("Week", getContext().getStringProperty("SelectFiscalWeek"))
						.replace("Division", getContext().getStringProperty("Division")));
		System.out.println("DB Value: " + DBLstYrCustVal);

		String PctLastYrCustomersTotal = data.getText("PctLastYrCustomersTotal");
		DBLstYrCustTtl = sql.CLRexecuteQuery(getContext(),
				PctLastYrCustomersTotal.replace("store", getContext().getStringProperty("store"))
						.replace("LYWk", getContext().getStringProperty("LYWk"))
						.replace("Week", getContext().getStringProperty("SelectFiscalWeek"))
						.replace("Division", getContext().getStringProperty("Division")));
		System.out.println("DB Value: " + DBLstYrCustTtl);

		LstYrCustVal = pageDailyTradingStatementGroupPO.GetRowWiseResult(objWeeklyTradingStatementStoreObjects,
				objWeeklyTradingStatementStoreObjects.Departments,
				objWeeklyTradingStatementStoreObjects.PercentageLastCustomer,
				objWeeklyTradingStatementStoreObjects.DataTable);
		LstYrCustTtl = pageDailyTradingStatementGroupPO.WeekToDateUITotalWTS(
				objWeeklyTradingStatementStoreObjects.TotalTitle,
				objWeeklyTradingStatementStoreObjects.PercentageLastCustomerTtl,objWeeklyTradingStatementStoreObjects.DataTable);

		pageWeeklyPlanningPO.CompareValueDecimalFields(extentTest, DBLstYrCustVal, LstYrCustVal);
		pageMonthlyPlanningPO.CompareValueDecimalFields(extentTest, DBLstYrCustTtl, LstYrCustTtl);

		// DATA PENETRATION CUSTOMER:
		Report_AddStep("testcase", "DATA PENETRATION CUSTOMER - WEEK WISE DATA", "", "", "");
		extentTest = report.startTest("Data Penetration");

		String DataPenetrationCustomers = data.getText("DataPenetrationCustomers");
		DBDataPenVal = sql.CLRexecuteQuery(getContext(),
				DataPenetrationCustomers.replace("store", getContext().getStringProperty("store"))
						.replace("LYWk", getContext().getStringProperty("LYWk"))
						.replace("Week", getContext().getStringProperty("SelectFiscalWeek"))
						.replace("Division", getContext().getStringProperty("Division")));
		System.out.println("DB Value: " + DBDataPenVal);

		String DataPenetrationCustomersTotal = data.getText("DataPenetrationCustomersTotal");
		DBDataPenTtl = sql.CLRexecuteQuery(getContext(),
				DataPenetrationCustomersTotal.replace("store", getContext().getStringProperty("store"))
						.replace("LYWk", getContext().getStringProperty("LYWk"))
						.replace("Week", getContext().getStringProperty("SelectFiscalWeek"))
						.replace("Division", getContext().getStringProperty("Division")));
		System.out.println("DB Value: " + DBDataPenTtl);

		DataPenVal = pageDailyTradingStatementGroupPO.GetRowWiseResult(objWeeklyTradingStatementStoreObjects,
				objWeeklyTradingStatementStoreObjects.Departments,
				objWeeklyTradingStatementStoreObjects.DeptPenetrationCustomer,
				objWeeklyTradingStatementStoreObjects.DataTable);
		DataPenTtl = pageDailyTradingStatementGroupPO.WeekToDateUITotalWTS(
				objWeeklyTradingStatementStoreObjects.TotalTitle,
				objWeeklyTradingStatementStoreObjects.DeptPenetrationCustomerTtl,objWeeklyTradingStatementStoreObjects.DataTable);

		pageWeeklyPlanningPO.CompareValueDecimalFields(extentTest, DBDataPenVal, DataPenVal);
		pageMonthlyPlanningPO.CompareValueDecimalFields(extentTest, DBDataPenTtl, DataPenTtl);
	}

	@Test(description = "Weekly Trading Statement - Store, Growth in SMS Hours", priority = 4)
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
				ActualHours.replace("store", getContext().getStringProperty("store"))
						.replace("LYWk", getContext().getStringProperty("LYWk"))
						.replace("Week", getContext().getStringProperty("SelectFiscalWeek"))
						.replace("Division", getContext().getStringProperty("Division")));
		System.out.println("DB Value: " + DBActlHrsVal);

		String ActualHoursTotal = data.getText("ActualHoursTotal");
		DBActlHrsTtl = sql.CLRexecuteQuery(getContext(),
				ActualHoursTotal.replace("store", getContext().getStringProperty("store"))
						.replace("LYWk", getContext().getStringProperty("LYWk"))
						.replace("Week", getContext().getStringProperty("SelectFiscalWeek"))
						.replace("Division", getContext().getStringProperty("Division")));
		System.out.println("DB Value: " + DBActlHrsTtl);

		ActlHrsVal = pageDailyTradingStatementGroupPO.GetRowWiseResult(objWeeklyTradingStatementStoreObjects,
				objWeeklyTradingStatementStoreObjects.Departments, objWeeklyTradingStatementStoreObjects.ActualHours,
				objWeeklyTradingStatementStoreObjects.DataTable);
		ActlHrsTtl = pageDailyTradingStatementGroupPO.WeekToDateUITotalWTS(
				objWeeklyTradingStatementStoreObjects.TotalTitle, objWeeklyTradingStatementStoreObjects.ActualHoursTtl,objWeeklyTradingStatementStoreObjects.DataTable);

		pageWeeklyPlanningPO.CompareValueDecimalFields(extentTest, DBActlHrsVal, ActlHrsVal);
		pageMonthlyPlanningPO.CompareValueDecimalFields(extentTest, DBActlHrsTtl, ActlHrsTtl);

		// Variance SMS:
		Report_AddStep("testcase", "VARIANCE SMS - WEEK WISE DATA", "", "", "");
		extentTest = report.startTest("Variance SMS");

		String VarianceSMS = data.getText("VarianceSMS");
		DBVarSMSHrHr = sql.CLRexecuteQuery(getContext(),
				VarianceSMS.replace("store", getContext().getStringProperty("store"))
						.replace("FinWk", getContext().getStringProperty("FinWk"))
						.replace("Week", getContext().getStringProperty("SelectFiscalWeek"))
						.replace("Year", getContext().getStringProperty("Year"))
						.replace("Division", getContext().getStringProperty("Division")));
		System.out.println("DB Value: " + DBVarSMSHrHr);

		String VarianceSMSTotal = data.getText("VarianceSMSTotal");
		DBVarSMSHrTtl = sql.CLRexecuteQuery(getContext(),
				VarianceSMSTotal.replace("store", getContext().getStringProperty("store"))
						.replace("FinWk", getContext().getStringProperty("FinWk"))
						.replace("Week", getContext().getStringProperty("SelectFiscalWeek"))
						.replace("Year", getContext().getStringProperty("Year"))
						.replace("Division", getContext().getStringProperty("Division")));
		System.out.println("DB Value: " + DBVarSMSHrTtl);

		VarSMSHr = pageDailyTradingStatementGroupPO.GetRowWiseResult(objWeeklyTradingStatementStoreObjects,
				objWeeklyTradingStatementStoreObjects.Departments, objWeeklyTradingStatementStoreObjects.VarSMSHours,
				objWeeklyTradingStatementStoreObjects.DataTable);
		VarSMSHrTtl = pageDailyTradingStatementGroupPO.WeekToDateUITotalWTS(
				objWeeklyTradingStatementStoreObjects.TotalTitle, objWeeklyTradingStatementStoreObjects.VarSMSHoursTtl,objWeeklyTradingStatementStoreObjects.DataTable);

		pageWeeklyPlanningPO.CompareValueDecimalFields(extentTest, DBVarSMSHrHr, VarSMSHr);
		pageMonthlyPlanningPO.CompareValueDecimalFields(extentTest, DBVarSMSHrTtl, VarSMSHrTtl);

		// PLANNED HOURS:
		Report_AddStep("testcase", "PLANNED HOURS: - WEEK WISE DATA", "", "", "");
		extentTest = report.startTest("Planned Hours");

		String PlannedHours = data.getText("PlannedHours");
		DBPlndHrsVal = sql.CLRexecuteQuery(getContext(),
				PlannedHours.replace("store", getContext().getStringProperty("store"))
						.replace("LYWk", getContext().getStringProperty("LYWk"))
						.replace("Week", getContext().getStringProperty("SelectFiscalWeek"))
						.replace("Division", getContext().getStringProperty("Division")));
		System.out.println("DB Value: " + DBPlndHrsVal);

		String PlannedHoursTotal = data.getText("PlannedHoursTotal");
		DBPlndHrsTtl = sql.CLRexecuteQuery(getContext(),
				PlannedHoursTotal.replace("store", getContext().getStringProperty("store"))
						.replace("LYWk", getContext().getStringProperty("LYWk"))
						.replace("Week", getContext().getStringProperty("SelectFiscalWeek"))
						.replace("Division", getContext().getStringProperty("Division")));
		System.out.println("DB Value: " + DBPlndHrsTtl);

		PlndHrsVal = pageDailyTradingStatementGroupPO.GetRowWiseResult(objWeeklyTradingStatementStoreObjects,
				objWeeklyTradingStatementStoreObjects.Departments, objWeeklyTradingStatementStoreObjects.PlannedHours,
				objWeeklyTradingStatementStoreObjects.DataTable);
		PlndHrsTtl = pageDailyTradingStatementGroupPO.WeekToDateUITotalWTS(
				objWeeklyTradingStatementStoreObjects.TotalTitle,
				objWeeklyTradingStatementStoreObjects.PlannedHoursTtl,objWeeklyTradingStatementStoreObjects.DataTable);

		pageWeeklyPlanningPO.CompareValueDecimalFields(extentTest, DBPlndHrsVal, PlndHrsVal);
		pageMonthlyPlanningPO.CompareValueDecimalFields(extentTest, DBPlndHrsTtl, PlndHrsTtl);

		// VARIANCE IN PLANNED HOURS:
		Report_AddStep("testcase", "VARIANCE IN PLANNED HOURS - WEEK WISE DATA", "", "", "");
		extentTest = report.startTest("Variance Hours");

		String VarHours = data.getText("VarHours");
		DBVarPlndHrsVal = sql.CLRexecuteQuery(getContext(),
				VarHours.replace("store", getContext().getStringProperty("store"))
						.replace("LYWk", getContext().getStringProperty("LYWk"))
						.replace("Week", getContext().getStringProperty("SelectFiscalWeek"))
						.replace("Division", getContext().getStringProperty("Division")));
		System.out.println("DB Value: " + DBVarPlndHrsVal);

		String VarHoursTotal = data.getText("VarHoursTotal");
		DBVarPlndHrsTtl = sql.CLRexecuteQuery(getContext(),
				VarHoursTotal.replace("store", getContext().getStringProperty("store"))
						.replace("LYWk", getContext().getStringProperty("LYWk"))
						.replace("Week", getContext().getStringProperty("SelectFiscalWeek"))
						.replace("Division", getContext().getStringProperty("Division")));
		System.out.println("DB Value: " + DBVarPlndHrsTtl);

		VarPlndHrsVal = pageDailyTradingStatementGroupPO.GetRowWiseResult(objWeeklyTradingStatementStoreObjects,
				objWeeklyTradingStatementStoreObjects.Departments,
				objWeeklyTradingStatementStoreObjects.VarPlannedHours, objWeeklyTradingStatementStoreObjects.DataTable);
		VarPlndHrsTtl = pageDailyTradingStatementGroupPO.WeekToDateUITotalWTS(
				objWeeklyTradingStatementStoreObjects.TotalTitle,
				objWeeklyTradingStatementStoreObjects.VarPlannedHoursTtl,objWeeklyTradingStatementStoreObjects.DataTable);

		pageWeeklyPlanningPO.CompareValueDecimalFields(extentTest, DBVarPlndHrsVal, VarPlndHrsVal);
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
						.replace("Division", getContext().getStringProperty("Division")));
		System.out.println("DB Value: " + DBActlSMSHr);

		String ActualSMSTotal = data.getText("ActualSMSTotal");
		DBActlSMSHrTtl = sql.CLRexecuteQuery(getContext(),
				ActualSMSTotal.replace("store", getContext().getStringProperty("store"))
						.replace("FinWk", getContext().getStringProperty("FinWk"))
						.replace("Week", getContext().getStringProperty("SelectFiscalWeek"))
						.replace("Year", getContext().getStringProperty("Year"))
						.replace("Division", getContext().getStringProperty("Division")));
		System.out.println("DB Value: " + DBActlSMSHrTtl);

		ActlSMSHr = pageDailyTradingStatementGroupPO.GetRowWiseResult(objWeeklyTradingStatementStoreObjects,
				objWeeklyTradingStatementStoreObjects.Departments, objWeeklyTradingStatementStoreObjects.ActualSMSHours,
				objWeeklyTradingStatementStoreObjects.DataTable);
		ActlSMSHrTtl = pageDailyTradingStatementGroupPO.WeekToDateUITotalWTS(
				objWeeklyTradingStatementStoreObjects.TotalTitle,
				objWeeklyTradingStatementStoreObjects.ActualSMSHoursTtl,objWeeklyTradingStatementStoreObjects.DataTable);

		pageWeeklyPlanningPO.CompareValueDecimalFields(extentTest, DBActlSMSHr, ActlSMSHr);
		pageMonthlyPlanningPO.CompareValueDecimalFields(extentTest, DBActlSMSHrTtl, ActlSMSHrTtl);

		// OR % SMS:
		Report_AddStep("testcase", "OPERATIONAL RATIO - WEEK WISE DATA", "", "", "");
		extentTest = report.startTest("OR Percentage");

		String ORPercentage = data.getText("ORPercentage");
		DBORHr = sql.CLRexecuteQuery(getContext(),
				ORPercentage.replace("store", getContext().getStringProperty("store"))
						.replace("FinWk", getContext().getStringProperty("FinWk"))
						.replace("Week", getContext().getStringProperty("SelectFiscalWeek"))
						.replace("Year", getContext().getStringProperty("Year"))
						.replace("Division", getContext().getStringProperty("Division")));
		System.out.println("DB Value: " + DBORHr);

		String ORPercentageTotal = data.getText("ORPercentageTotal");
		DBORHrTtl = sql.CLRexecuteQuery(getContext(),
				ORPercentageTotal.replace("store", getContext().getStringProperty("store"))
						.replace("FinWk", getContext().getStringProperty("FinWk"))
						.replace("Week", getContext().getStringProperty("SelectFiscalWeek"))
						.replace("Year", getContext().getStringProperty("Year"))
						.replace("Division", getContext().getStringProperty("Division")));
		System.out.println("DB Value: " + DBORHrTtl);

		ORHr = pageDailyTradingStatementGroupPO.GetRowWiseResult(objWeeklyTradingStatementStoreObjects,
				objWeeklyTradingStatementStoreObjects.Departments,
				objWeeklyTradingStatementStoreObjects.ORPercentageHours,
				objWeeklyTradingStatementStoreObjects.DataTable);
		ORHrTtl = pageDailyTradingStatementGroupPO.WeekToDateUITotalWTS(objWeeklyTradingStatementStoreObjects.TotalTitle,
				objWeeklyTradingStatementStoreObjects.ORPercentageHoursTtl,objWeeklyTradingStatementStoreObjects.DataTable);

		pageWeeklyPlanningPO.CompareValueDecimalFields(extentTest, DBORHr, ORHr);
		pageMonthlyPlanningPO.CompareValueDecimalFields(extentTest, DBORHrTtl, ORHrTtl);
	}

	// Wages
	@Test(description = "Weekly Trading Statement - Store, Actual Wages", priority = 5)
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
		extentTest = report.startTest(" Actual Wage");

		String ActualWages = data.getText("ActualWages");
		DBActlWgVal = sql.CLRexecuteQuery(getContext(),
				ActualWages.replace("store", getContext().getStringProperty("store"))
						.replace("LYWk", getContext().getStringProperty("LYWk"))
						.replace("Week", getContext().getStringProperty("SelectFiscalWeek"))
						.replace("Division", getContext().getStringProperty("Division")));
		System.out.println("DB Value: " + DBActlWgVal);

		String ActualWagesTotal = data.getText("ActualWagesTotal");
		DBActlWgTtl = sql.CLRexecuteQuery(getContext(),
				ActualWagesTotal.replace("store", getContext().getStringProperty("store"))
						.replace("LYWk", getContext().getStringProperty("LYWk"))
						.replace("Week", getContext().getStringProperty("SelectFiscalWeek"))
						.replace("Division", getContext().getStringProperty("Division")));
		System.out.println("DB Value: " + DBActlWgTtl);

		ActlWgVal = pageDailyTradingStatementGroupPO.GetRowWiseResult(objWeeklyTradingStatementStoreObjects,
				objWeeklyTradingStatementStoreObjects.Departments, objWeeklyTradingStatementStoreObjects.ActualWageswts,
				objWeeklyTradingStatementStoreObjects.DataTable);
		ActlWgTtl = pageDailyTradingStatementGroupPO.WeekToDateUITotalWTS(objWeeklyTradingStatementStoreObjects.TotalTitle,
				objWeeklyTradingStatementStoreObjects.ActualWagesTtlwts,objWeeklyTradingStatementStoreObjects.DataTable);

		pageWeeklyPlanningPO.CompareValues(extentTest, DBActlWgVal, ActlWgVal);
		pageMonthlyPlanningPO.CompareValues(extentTest, DBActlWgTtl, ActlWgTtl);

		// ACTUAL WAGE PERCENTAGE:
		Report_AddStep("testcase", "ACTUAL WAGE PERCENTAGE- WEEK WISE DATA", "", "", "");
		extentTest = report.startTest("Actual Wage Percentage");

		String ActualWagePercentage = data.getText("ActualWagePercentage");
		DBActlWgPrcntVal = sql.CLRexecuteQuery(getContext(),
				ActualWagePercentage.replace("store", getContext().getStringProperty("store"))
						.replace("LYWk", getContext().getStringProperty("LYWk"))
						.replace("Week", getContext().getStringProperty("SelectFiscalWeek"))
						.replace("Division", getContext().getStringProperty("Division")));
		System.out.println("DB Value: " + DBActlWgPrcntVal);

		String ActualWagePercentageTotal = data.getText("ActualWagePercentageTotal");
		DBActlWgPrcntTtl = sql.CLRexecuteQuery(getContext(),
				ActualWagePercentageTotal.replace("store", getContext().getStringProperty("store"))
						.replace("LYWk", getContext().getStringProperty("LYWk"))
						.replace("Week", getContext().getStringProperty("SelectFiscalWeek"))
						.replace("Division", getContext().getStringProperty("Division")));
		System.out.println("DB Value: " + DBActlWgPrcntTtl);

		ActlWgPrcntVal = pageDailyTradingStatementGroupPO.GetRowWiseResult(objWeeklyTradingStatementStoreObjects,
				objWeeklyTradingStatementStoreObjects.Departments,
				objWeeklyTradingStatementStoreObjects.ActualWagesPercentagewts,
				objWeeklyTradingStatementStoreObjects.DataTable);
		ActlWgPrcntTtl = pageDailyTradingStatementGroupPO.WeekToDateUITotalWTS(
				objWeeklyTradingStatementStoreObjects.TotalTitle,
				objWeeklyTradingStatementStoreObjects.ActualWagesPercentageTtlwts,objWeeklyTradingStatementStoreObjects.DataTable);

		pageWeeklyPlanningPO.CompareValueDecimalFields(extentTest, DBActlWgPrcntVal, ActlWgPrcntVal);
		pageMonthlyPlanningPO.CompareValueDecimalFields(extentTest, DBActlWgPrcntTtl, ActlWgPrcntTtl);

		// BUDGET WAGE:
		if (OptionToView.contains("Budget")) {
			Report_AddStep("testcase", "ACTUAL BUDGET WAGE - WEEK WISE DATA", "", "", "");
			extentTest = report.startTest("Budget Wage");

			String BudgetWages = data.getText("BudgetWages");
			DBBdgtWgVal = sql.CLRexecuteQuery(getContext(),
					BudgetWages.replace("store", getContext().getStringProperty("store"))
							.replace("LYWk", getContext().getStringProperty("LYWk"))
							.replace("Week", getContext().getStringProperty("SelectFiscalWeek"))
							.replace("Division", getContext().getStringProperty("Division")));
			System.out.println("DB Value: " + DBBdgtWgVal);

			String BudgetWagesTotal = data.getText("BudgetWagesTotal");
			DBBdgtWgTtl = sql.CLRexecuteQuery(getContext(),
					BudgetWagesTotal.replace("store", getContext().getStringProperty("store"))
							.replace("LYWk", getContext().getStringProperty("LYWk"))
							.replace("Week", getContext().getStringProperty("SelectFiscalWeek"))
							.replace("Division", getContext().getStringProperty("Division")));
			System.out.println("DB Value: " + DBBdgtWgTtl);

			BdgtWgVal = pageDailyTradingStatementGroupPO.GetRowWiseResult(objWeeklyTradingStatementStoreObjects,
					objWeeklyTradingStatementStoreObjects.Departments,
					objWeeklyTradingStatementStoreObjects.BudgetWageswts,
					objWeeklyTradingStatementStoreObjects.DataTable);
			BdgtWgTtl = pageDailyTradingStatementGroupPO.WeekToDateUITotalWTS(
					objWeeklyTradingStatementStoreObjects.TotalTitle,
					objWeeklyTradingStatementStoreObjects.BudgetWagesTtlwts,objWeeklyTradingStatementStoreObjects.DataTable);

			pageWeeklyPlanningPO.CompareValues(extentTest, DBBdgtWgVal, BdgtWgVal);
			pageMonthlyPlanningPO.CompareValues(extentTest, DBBdgtWgTtl, BdgtWgTtl);
		}
		if (OptionToView.contains("Forecast")) {
			Report_AddStep("testcase", "ACTUAL FORECAST WAGE - WEEK WISE DATA", "", "", "");
			extentTest = report.startTest("Forecast Wage");

			String ForecastWages = data.getText("ForecastWages");
			DBBdgtWgVal = sql.CLRexecuteQuery(getContext(),
					ForecastWages.replace("store", getContext().getStringProperty("store"))
							.replace("LYWk", getContext().getStringProperty("LYWk"))
							.replace("Week", getContext().getStringProperty("SelectFiscalWeek"))
							.replace("Division", getContext().getStringProperty("Division")));
			System.out.println("DB Value: " + DBBdgtWgVal);

			String ForecastWagesTotal = data.getText("ForecastWagesTotal");
			DBBdgtWgTtl = sql.CLRexecuteQuery(getContext(),
					ForecastWagesTotal.replace("store", getContext().getStringProperty("store"))
							.replace("LYWk", getContext().getStringProperty("LYWk"))
							.replace("Week", getContext().getStringProperty("SelectFiscalWeek"))
							.replace("Division", getContext().getStringProperty("Division")));
			System.out.println("DB Value: " + DBBdgtWgTtl);

			BdgtWgVal = pageDailyTradingStatementGroupPO.GetRowWiseResult(objWeeklyTradingStatementStoreObjects,
					objWeeklyTradingStatementStoreObjects.Departments,
					objWeeklyTradingStatementStoreObjects.BudgetWageswts,
					objWeeklyTradingStatementStoreObjects.DataTable);
			BdgtWgTtl = pageDailyTradingStatementGroupPO.WeekToDateUITotalWTS(
					objWeeklyTradingStatementStoreObjects.TotalTitle,
					objWeeklyTradingStatementStoreObjects.BudgetWagesTtlwts,objWeeklyTradingStatementStoreObjects.DataTable);

			pageWeeklyPlanningPO.CompareValues(extentTest, DBBdgtWgVal, BdgtWgVal);
			pageMonthlyPlanningPO.CompareValues(extentTest, DBBdgtWgTtl, BdgtWgTtl);
		}

		// BUDGET WAGE PERCENTAGE:
		if (OptionToView.contains("Budget")) {
			Report_AddStep("testcase", "BUDGET WAGE PERCENTAGE- WEEK WISE DATA", "", "", "");
			extentTest = report.startTest("Budget Wage percentage");

			String BudgetWagePercentage = data.getText("BudgetWagePercentage");
			DBBdgtWgPrcntVal = sql.CLRexecuteQuery(getContext(),
					BudgetWagePercentage.replace("store", getContext().getStringProperty("store"))
							.replace("LYWk", getContext().getStringProperty("LYWk"))
							.replace("Week", getContext().getStringProperty("SelectFiscalWeek"))
							.replace("Division", getContext().getStringProperty("Division")));
			System.out.println("DB Value: " + DBBdgtWgPrcntVal);

			String BudgetWagePercentageTotal = data.getText("BudgetWagePercentageTotal");
			DBBdgtWgPrcntTtl = sql.CLRexecuteQuery(getContext(),
					BudgetWagePercentageTotal.replace("store", getContext().getStringProperty("store"))
							.replace("LYWk", getContext().getStringProperty("LYWk"))
							.replace("Week", getContext().getStringProperty("SelectFiscalWeek"))
							.replace("Division", getContext().getStringProperty("Division")));
			System.out.println("DB Value: " + DBBdgtWgPrcntTtl);

			BdgtWgPrcntVal = pageDailyTradingStatementGroupPO.GetRowWiseResult(objWeeklyTradingStatementStoreObjects,
					objWeeklyTradingStatementStoreObjects.Departments,
					objWeeklyTradingStatementStoreObjects.BudgetWagesPercentagewts,
					objWeeklyTradingStatementStoreObjects.DataTable);
			BdgtWgPrcntTtl = pageDailyTradingStatementGroupPO.WeekToDateUITotalWTS(
					objWeeklyTradingStatementStoreObjects.TotalTitle,
					objWeeklyTradingStatementStoreObjects.BudgetWagesPercentageTtlwts,objWeeklyTradingStatementStoreObjects.DataTable);

			pageWeeklyPlanningPO.CompareValueDecimalFields(extentTest, DBBdgtWgPrcntVal, BdgtWgPrcntVal);
			pageMonthlyPlanningPO.CompareValueDecimalFields(extentTest, DBBdgtWgPrcntTtl, BdgtWgPrcntTtl);
		}
		if (OptionToView.contains("Forecast")) {
			Report_AddStep("testcase", "FORECAST WAGE PERCENTAGE- WEEK WISE DATA", "", "", "");
			extentTest = report.startTest("Forecast Wage percentage");

			String ForecastWagePercentage = data.getText("ForecastWagePercentage");
			DBBdgtWgPrcntVal = sql.CLRexecuteQuery(getContext(),
					ForecastWagePercentage.replace("store", getContext().getStringProperty("store"))
							.replace("LYWk", getContext().getStringProperty("LYWk"))
							.replace("Week", getContext().getStringProperty("SelectFiscalWeek"))
							.replace("Division", getContext().getStringProperty("Division")));
			System.out.println("DB Value: " + DBBdgtWgPrcntVal);

			String ForecastWagePercentageTotal = data.getText("ForecastWagePercentageTotal");
			DBBdgtWgPrcntTtl = sql.CLRexecuteQuery(getContext(),
					ForecastWagePercentageTotal.replace("store", getContext().getStringProperty("store"))
							.replace("LYWk", getContext().getStringProperty("LYWk"))
							.replace("Week", getContext().getStringProperty("SelectFiscalWeek"))
							.replace("Division", getContext().getStringProperty("Division")));
			System.out.println("DB Value: " + DBBdgtWgPrcntTtl);

			BdgtWgPrcntVal = pageDailyTradingStatementGroupPO.GetRowWiseResult(objWeeklyTradingStatementStoreObjects,
					objWeeklyTradingStatementStoreObjects.Departments,
					objWeeklyTradingStatementStoreObjects.BudgetWagesPercentagewts,
					objWeeklyTradingStatementStoreObjects.DataTable);
			BdgtWgPrcntTtl = pageDailyTradingStatementGroupPO.WeekToDateUITotalWTS(
					objWeeklyTradingStatementStoreObjects.TotalTitle,
					objWeeklyTradingStatementStoreObjects.BudgetWagesPercentageTtlwts,objWeeklyTradingStatementStoreObjects.DataTable);

			pageWeeklyPlanningPO.CompareValueDecimalFields(extentTest, DBBdgtWgPrcntVal, BdgtWgPrcntVal);
			pageMonthlyPlanningPO.CompareValueDecimalFields(extentTest, DBBdgtWgPrcntTtl, BdgtWgPrcntTtl);
		}

		// PLANNED CPH:
		Report_AddStep("testcase", "PLANNED CPH - WEEK WISE DATA", "", "", "");
		extentTest = report.startTest("Planned CPH");

		String PlannedCPH = data.getText("PlannedCPH");
		DBPlndCPHVal = sql.CLRexecuteQuery(getContext(),
				PlannedCPH.replace("store", getContext().getStringProperty("store"))
						.replace("LYWk", getContext().getStringProperty("LYWk"))
						.replace("Week", getContext().getStringProperty("SelectFiscalWeek"))
						.replace("Division", getContext().getStringProperty("Division")));
		System.out.println("DB Value: " + DBPlndCPHVal);

		String PlannedCPHTotal = data.getText("PlannedCPHTotal");
		DBPlndCPHTtl = sql.CLRexecuteQuery(getContext(),
				PlannedCPHTotal.replace("store", getContext().getStringProperty("store"))
						.replace("LYWk", getContext().getStringProperty("LYWk"))
						.replace("Week", getContext().getStringProperty("SelectFiscalWeek"))
						.replace("Division", getContext().getStringProperty("Division")));
		System.out.println("DB Value: " + DBPlndCPHTtl);

		PlndCPHVal = pageDailyTradingStatementGroupPO.GetRowWiseResult(objWeeklyTradingStatementStoreObjects,
				objWeeklyTradingStatementStoreObjects.Departments,
				objWeeklyTradingStatementStoreObjects.PlannedCPHWageswts,
				objWeeklyTradingStatementStoreObjects.DataTable);
		PlndCPHTtl = pageDailyTradingStatementGroupPO.WeekToDateUITotalWTS(
				objWeeklyTradingStatementStoreObjects.TotalTitle,
				objWeeklyTradingStatementStoreObjects.PlannedCPHWagesTtlwts,objWeeklyTradingStatementStoreObjects.DataTable);

		pageWeeklyPlanningPO.CompareValueDecimalFields(extentTest, DBPlndCPHVal, PlndCPHVal);
		pageMonthlyPlanningPO.CompareValueDecimalFields(extentTest, DBPlndCPHTtl, PlndCPHTtl);

		// PLANNED WAGE:
		Report_AddStep("testcase", "PLANNED WAGE - WEEK WISE DATA", "", "", "");
		extentTest = report.startTest("Planned Wages");

		String PlannedWages = data.getText("PlannedWages");
		DBPlndWgVal = sql.CLRexecuteQuery(getContext(),
				PlannedWages.replace("store", getContext().getStringProperty("store"))
						.replace("LYWk", getContext().getStringProperty("LYWk"))
						.replace("Week", getContext().getStringProperty("SelectFiscalWeek"))
						.replace("Division", getContext().getStringProperty("Division")));
		System.out.println("DB Value: " + DBPlndWgVal);

		String PlannedWagesTotal = data.getText("PlannedWagesTotal");
		DBPlndWgTtl = sql.CLRexecuteQuery(getContext(),
				PlannedWagesTotal.replace("store", getContext().getStringProperty("store"))
						.replace("LYWk", getContext().getStringProperty("LYWk"))
						.replace("Week", getContext().getStringProperty("SelectFiscalWeek"))
						.replace("Division", getContext().getStringProperty("Division")));
		System.out.println("DB Value: " + DBPlndWgTtl);

		PlndWgVal = pageDailyTradingStatementGroupPO.GetRowWiseResult(objWeeklyTradingStatementStoreObjects,
				objWeeklyTradingStatementStoreObjects.Departments,
				objWeeklyTradingStatementStoreObjects.PlannedWageswts, objWeeklyTradingStatementStoreObjects.DataTable);
		PlndWgTtl = pageDailyTradingStatementGroupPO.WeekToDateUITotalWTS(objWeeklyTradingStatementStoreObjects.TotalTitle,
				objWeeklyTradingStatementStoreObjects.PlannedWagesTtlwts,objWeeklyTradingStatementStoreObjects.DataTable);

		pageWeeklyPlanningPO.CompareValues(extentTest, DBPlndWgVal, PlndWgVal);
		pageMonthlyPlanningPO.CompareValues(extentTest, DBPlndWgTtl, PlndWgTtl);

		// PLANNED WAGE PERCENTAGE:
		Report_AddStep("testcase", "PLANNED WAGE PERCENTAGE - WEEK WISE DATA", "", "", "");
		extentTest = report.startTest("Planned Wage Percentage");

		String PlannedWagesPercentage = data.getText("PlannedWagesPercentage");
		DBPlndWgPrcntVal = sql.CLRexecuteQuery(getContext(),
				PlannedWagesPercentage.replace("store", getContext().getStringProperty("store"))
						.replace("LYWk", getContext().getStringProperty("LYWk"))
						.replace("Week", getContext().getStringProperty("SelectFiscalWeek"))
						.replace("Division", getContext().getStringProperty("Division")));
		System.out.println("DB Value: " + DBPlndWgPrcntVal);

		String PlannedWagesPercentageTotal = data.getText("PlannedWagesPercentageTotal");
		DBPlndWgPrcntTtl = sql.CLRexecuteQuery(getContext(),
				PlannedWagesPercentageTotal.replace("store", getContext().getStringProperty("store"))
						.replace("LYWk", getContext().getStringProperty("LYWk"))
						.replace("Week", getContext().getStringProperty("SelectFiscalWeek"))
						.replace("Division", getContext().getStringProperty("Division")));
		System.out.println("DB Value: " + DBPlndWgPrcntTtl);

		PlndWgPrcntVal = pageDailyTradingStatementGroupPO.GetRowWiseResult(objWeeklyTradingStatementStoreObjects,
				objWeeklyTradingStatementStoreObjects.Departments,
				objWeeklyTradingStatementStoreObjects.PlannedWagesPercentagewts,
				objWeeklyTradingStatementStoreObjects.DataTable);
		PlndWgPrcntTtl = pageDailyTradingStatementGroupPO.WeekToDateUITotalWTS(
				objWeeklyTradingStatementStoreObjects.TotalTitle,
				objWeeklyTradingStatementStoreObjects.PlannedWagesPercentageTtlwts,objWeeklyTradingStatementStoreObjects.DataTable);

		pageWeeklyPlanningPO.CompareValueDecimalFields(extentTest, DBPlndWgPrcntVal, PlndWgPrcntVal);
		pageMonthlyPlanningPO.CompareValueDecimalFields(extentTest, DBPlndWgPrcntTtl, PlndWgPrcntTtl);

		// CPH Wages:
		Report_AddStep("testcase", "CPH WAGES - WEEK WISE DATA", "", "", "");
		extentTest = report.startTest("CPH Wage");

		String ActualCPH = data.getText("ActualCPH");
		DBCPHWgVal = sql.CLRexecuteQuery(getContext(),
				ActualCPH.replace("store", getContext().getStringProperty("store"))
						.replace("LYWk", getContext().getStringProperty("LYWk"))
						.replace("Week", getContext().getStringProperty("SelectFiscalWeek"))
						.replace("Division", getContext().getStringProperty("Division")));
		System.out.println("DB Value: " + DBCPHWgVal);

		String ActualCPHTotal = data.getText("ActualCPHTotal");
		DBCPHWgTtl = sql.CLRexecuteQuery(getContext(),
				ActualCPHTotal.replace("store", getContext().getStringProperty("store"))
						.replace("LYWk", getContext().getStringProperty("LYWk"))
						.replace("Week", getContext().getStringProperty("SelectFiscalWeek"))
						.replace("Division", getContext().getStringProperty("Division")));
		System.out.println("DB Value: " + DBCPHWgTtl);

		CPHWgVal = pageDailyTradingStatementGroupPO.GetRowWiseResult(objWeeklyTradingStatementStoreObjects,
				objWeeklyTradingStatementStoreObjects.Departments, objWeeklyTradingStatementStoreObjects.CPHWageswts,
				objWeeklyTradingStatementStoreObjects.DataTable);
		CPHWgTtl = pageDailyTradingStatementGroupPO.WeekToDateUITotalWTS(objWeeklyTradingStatementStoreObjects.TotalTitle,
				objWeeklyTradingStatementStoreObjects.CPHWagesTtlwts,objWeeklyTradingStatementStoreObjects.DataTable);

		pageWeeklyPlanningPO.CompareValueDecimalFields(extentTest, DBCPHWgVal, CPHWgVal);
		pageMonthlyPlanningPO.CompareValueDecimalFields(extentTest, DBCPHWgTtl, CPHWgTtl);
		
		//Allowed Wages Validation
		
				if (OptionToView.contains("Budget")) {
				Report_AddStep("testcase", "Allowed Wages Validation", "", "", "");
				extentTest = report.startTest("Allowed Wage");
				System.out.println("Allowedwages");
				String AllowedWages = data.getText("AllowedWages");
				DBAllowedWgVal = sql.CLRexecuteQuery(getContext(),
						AllowedWages.replace("store", getContext().getStringProperty("store"))
								.replace("LYWk", getContext().getStringProperty("LYWk"))
								.replace("Week", getContext().getStringProperty("SelectFiscalWeek"))
								.replace("Division", getContext().getStringProperty("Division")).replace("Region", getContext().getStringProperty("Region")));
				System.out.println("DB Value: " + DBAllowedWgVal);

				String AllowedWagesTotal = data.getText("AllowedWagesTotal");
				DBAllowedWgTtl = sql.CLRexecuteQuery(getContext(),
						AllowedWagesTotal.replace("store", getContext().getStringProperty("store"))
								.replace("LYWk", getContext().getStringProperty("LYWk"))
								.replace("Week", getContext().getStringProperty("SelectFiscalWeek"))
								.replace("Division", getContext().getStringProperty("Division")).replace("Region", getContext().getStringProperty("Region")));
				System.out.println("DB Value: " + DBAllowedWgTtl);

				AllowedWgVal = pageDailyTradingStatementGroupPO.GetRowWiseResult(objWeeklyTradingStatementStoreObjects,
						objWeeklyTradingStatementStoreObjects.Departments, objWeeklyTradingStatementStoreObjects.AllowedWageswts,
						objWeeklyTradingStatementStoreObjects.DataTable);
				AllowedWgTtl = pageDailyTradingStatementGroupPO.WeekToDateUITotalWTS(objWeeklyTradingStatementStoreObjects.TotalTitle,
						objWeeklyTradingStatementStoreObjects.AllowedWagesTtlwts,objWeeklyTradingStatementStoreObjects.DataTable);

				pageWeeklyPlanningPO.CompareValues(extentTest, DBAllowedWgVal, AllowedWgVal);
				pageMonthlyPlanningPO.CompareValues(extentTest, DBAllowedWgTtl, AllowedWgTtl);
				
			
			}
	}

	//////////////////////////////////////////////// CLR 1
	//////////////////////////////////////////////// /////////////////////////////////////////////////////////
	// Sales
	@Test(description = "Weekly Trading Statement - Store, SalesColumnValidationNewTable", priority = 6)
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
				ActualSalesNew.replace("store", getContext().getStringProperty("store"))
						.replace("Month", getContext().getStringProperty("MonthToTest"))
						.replace("Division", getContext().getStringProperty("Division")));
		System.out.println("DB Value: " + DBActlVal);

		String ActualSalesTotalNew = data.getText("ActualSalesTotal");
		DBActlTtl = sql.CLRexecuteQuery(getContext(),
				ActualSalesTotalNew.replace("store", getContext().getStringProperty("store"))
						.replace("Month", getContext().getStringProperty("MonthToTest"))
						.replace("Division", getContext().getStringProperty("Division")));
		System.out.println("DB Value: " + DBActlTtl);

		ActlVal = pageDailyTradingStatementGroupPO.RowWiseResult(objWeeklyTradingStatementStoreObjects,
				objWeeklyTradingStatementStoreObjects.Weeks, objWeeklyTradingStatementStoreObjects.ActualSalesNew,
				objWeeklyTradingStatementStoreObjects.Table);
		ActlTtl = pageDailyTradingStatementGroupPO.WeekToDateUITotal(objWeeklyTradingStatementStoreObjects.MonthTotal,
				objWeeklyTradingStatementStoreObjects.ActualSalesTtlNew);

		pageMonthlyPlanningPO.CompareValues(extentTest, DBActlVal, ActlVal);
		pageMonthlyPlanningPO.CompareValues(extentTest, DBActlTtl, ActlTtl);

		// WEEKLY PLANNED SALES:
		Report_AddStep("testcase", " WEEKLY PLANNED SALES COMBINED TABLE- WEEK WISE DATA", "", "", "");
		extentTest = report.startTest("Planned Sales - Week Data");

		String WeeklyPlannedSales = data.getText("WeeklyPlannedSales");
		DBPlndVal = sql.CLRexecuteQuery(getContext(),
				WeeklyPlannedSales.replace("store", getContext().getStringProperty("store"))
						.replace("Month", getContext().getStringProperty("MonthToTest"))
						.replace("Division", getContext().getStringProperty("Division")));
		System.out.println("DB Value: " + DBPlndVal);

		String WeeklyPlannedSalesTotal = data.getText("WeeklyPlannedSalesTotal");
		DBPlndTtl = sql.CLRexecuteQuery(getContext(),
				WeeklyPlannedSalesTotal.replace("store", getContext().getStringProperty("store"))
						.replace("Month", getContext().getStringProperty("MonthToTest"))
						.replace("Division", getContext().getStringProperty("Division")));
		System.out.println("DB Value: " + DBPlndTtl);

		PlndVal = pageDailyTradingStatementGroupPO.RowWiseResult(objWeeklyTradingStatementStoreObjects,
				objWeeklyTradingStatementStoreObjects.Weeks, objWeeklyTradingStatementStoreObjects.WeeklyPlannedSales,
				objWeeklyTradingStatementStoreObjects.Table);
		PlndTtl = pageDailyTradingStatementGroupPO.WeekToDateUITotal(objWeeklyTradingStatementStoreObjects.MonthTotal,
				objWeeklyTradingStatementStoreObjects.WeeklyPlannedSalesTtl);

		pageMonthlyPlanningPO.CompareValues(extentTest, DBPlndVal, PlndVal);
		pageMonthlyPlanningPO.CompareValues(extentTest, DBPlndTtl, PlndTtl);

		// MONTHLY PLANNED SALES:
		Report_AddStep("testcase", "MONTHLY PLANNED SALES COMBINED TABLE - WEEK WISE DATA", "", "", "");
		extentTest = report.startTest("Actual Sales - Month Data");

		String MonthlyPlannedSales = data.getText("MonthlyPlannedSales");
		DBPlndVal = sql.CLRexecuteQuery(getContext(),
				MonthlyPlannedSales.replace("store", getContext().getStringProperty("store"))
						.replace("Month", getContext().getStringProperty("MonthToTest"))
						.replace("Division", getContext().getStringProperty("Division")));
		System.out.println("DB Value: " + DBPlndVal);

		String MonthlyPlannedSalesTotal = data.getText("MonthlyPlannedSalesTotal");
		DBPlndTtl = sql.CLRexecuteQuery(getContext(),
				MonthlyPlannedSalesTotal.replace("store", getContext().getStringProperty("store"))
						.replace("Month", getContext().getStringProperty("MonthToTest"))
						.replace("Division", getContext().getStringProperty("Division")));
		System.out.println("DB Value: " + DBPlndTtl);

		PlndVal = pageDailyTradingStatementGroupPO.RowWiseResult(objWeeklyTradingStatementStoreObjects,
				objWeeklyTradingStatementStoreObjects.Weeks, objWeeklyTradingStatementStoreObjects.MonthlyPlannedSales,
				objWeeklyTradingStatementStoreObjects.Table);
		PlndTtl = pageDailyTradingStatementGroupPO.WeekToDateUITotal(objWeeklyTradingStatementStoreObjects.MonthTotal,
				objWeeklyTradingStatementStoreObjects.MonthlyPlannedSalesTtl);

		pageMonthlyPlanningPO.CompareValues(extentTest, DBPlndVal, PlndVal);
		pageMonthlyPlanningPO.CompareValues(extentTest, DBPlndTtl, PlndTtl);

		// BUDGET SALES:
		if (OptionToView.contains("Budget")) {
			Report_AddStep("testcase", "BUDGET SALES COMBINED TABLE- WEEK WISE DATA", "", "", "");
			extentTest = report.startTest("Budget Sales");

			String BudgetSales = data.getText("BudgetSales");
			DBBdgtVal = sql.CLRexecuteQuery(getContext(),
					BudgetSales.replace("store", getContext().getStringProperty("store"))
							.replace("Month", getContext().getStringProperty("MonthToTest"))
							.replace("Division", getContext().getStringProperty("Division")));
			System.out.println("DB Value: " + DBBdgtVal);

			String BudgetSalesTotal = data.getText("BudgetSalesTotal");
			DBBdgtTtl = sql.CLRexecuteQuery(getContext(),
					BudgetSalesTotal.replace("store", getContext().getStringProperty("store"))
							.replace("Month", getContext().getStringProperty("MonthToTest"))
							.replace("Division", getContext().getStringProperty("Division")));
			System.out.println("DB Value: " + DBBdgtTtl);

			BdgtVal = pageDailyTradingStatementGroupPO.RowWiseResult(objWeeklyTradingStatementStoreObjects,
					objWeeklyTradingStatementStoreObjects.Weeks, objWeeklyTradingStatementStoreObjects.BudgetSalesNew,
					objWeeklyTradingStatementStoreObjects.Table);
			BdgtTtl = pageDailyTradingStatementGroupPO.WeekToDateUITotal(
					objWeeklyTradingStatementStoreObjects.MonthTotal,
					objWeeklyTradingStatementStoreObjects.BudgetSalesTtlNew);

			pageMonthlyPlanningPO.CompareValues(extentTest, DBBdgtVal, BdgtVal);
			pageMonthlyPlanningPO.CompareValues(extentTest, DBBdgtTtl, BdgtTtl);
		}
		if (OptionToView.contains("Forecast")) {
			Report_AddStep("testcase", "FORECAST SALES COMBINED TABLE - WEEK WISE DATA", "", "", "");
			extentTest = report.startTest("Forecast Sales");

			String ForecastSales = data.getText("ForecastSales");
			DBBdgtVal = sql.CLRexecuteQuery(getContext(),
					ForecastSales.replace("store", getContext().getStringProperty("store"))
							.replace("Month", getContext().getStringProperty("MonthToTest"))
							.replace("Division", getContext().getStringProperty("Division")));
			System.out.println("DB Value: " + DBBdgtVal);

			String ForecastSalesTotal = data.getText("ForecastSalesTotal");
			DBBdgtTtl = sql.CLRexecuteQuery(getContext(),
					ForecastSalesTotal.replace("store", getContext().getStringProperty("store"))
							.replace("Month", getContext().getStringProperty("MonthToTest"))
							.replace("Division", getContext().getStringProperty("Division")));
			System.out.println("DB Value: " + DBBdgtTtl);

			BdgtVal = pageDailyTradingStatementGroupPO.RowWiseResult(objWeeklyTradingStatementStoreObjects,
					objWeeklyTradingStatementStoreObjects.Weeks, objWeeklyTradingStatementStoreObjects.ForecastSalesNew,
					objWeeklyTradingStatementStoreObjects.Table);
			BdgtTtl = pageDailyTradingStatementGroupPO.WeekToDateUITotal(
					objWeeklyTradingStatementStoreObjects.MonthTotal,
					objWeeklyTradingStatementStoreObjects.ForecastSalesTtlNew);

			pageMonthlyPlanningPO.CompareValues(extentTest, DBBdgtVal, BdgtVal);
			pageMonthlyPlanningPO.CompareValues(extentTest, DBBdgtTtl, BdgtTtl);
		}
	}

	///// WAGES///
	@Test(description = "Weekly Trading Statement - Store, SalesColumnValidationNewTable", priority = 7)
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
		extentTest = report.startTest("Actual Wage");

		String ActualWagesNew = data.getText("ActualWages");
		DBActlVal = sql.CLRexecuteQuery(getContext(),
				ActualWagesNew.replace("store", getContext().getStringProperty("store"))
						.replace("Month", getContext().getStringProperty("MonthToTest"))
						.replace("Division", getContext().getStringProperty("Division")));
		System.out.println("DB Value: " + DBActlVal);

		String ActualWagesTotalNew = data.getText("ActualWagesTotal");
		DBActlTtl = sql.CLRexecuteQuery(getContext(),
				ActualWagesTotalNew.replace("store", getContext().getStringProperty("store"))
						.replace("Month", getContext().getStringProperty("MonthToTest"))
						.replace("Division", getContext().getStringProperty("Division")));
		System.out.println("DB Value: " + DBActlTtl);

		ActlVal = pageDailyTradingStatementGroupPO.RowWiseResult(objWeeklyTradingStatementStoreObjects,
				objWeeklyTradingStatementStoreObjects.Weeks, objWeeklyTradingStatementStoreObjects.ActualWagesNew,
				objWeeklyTradingStatementStoreObjects.Table);
		ActlTtl = pageDailyTradingStatementGroupPO.WeekToDateUITotal(objWeeklyTradingStatementStoreObjects.MonthTotal,
				objWeeklyTradingStatementStoreObjects.ActualWagesTtlNew);

		pageMonthlyPlanningPO.CompareValues(extentTest, DBActlVal, ActlVal);
		pageMonthlyPlanningPO.CompareValues(extentTest, DBActlTtl, ActlTtl);

		// WEEKLY PLANNED WAGES:
		Report_AddStep("testcase", " WEEKLY PLANNED WAGES COMBINED TABLE- WEEK WISE DATA", "", "", "");
		extentTest = report.startTest("Planned Wage - Week Data");

		String WeeklyPlannedWages = data.getText("WeeklyPlannedWages");
		DBPlndVal = sql.CLRexecuteQuery(getContext(),
				WeeklyPlannedWages.replace("store", getContext().getStringProperty("store"))
						.replace("Month", getContext().getStringProperty("MonthToTest"))
						.replace("Division", getContext().getStringProperty("Division")));
		System.out.println("DB Value: " + DBPlndVal);

		String WeeklyPlannedWagesTotal = data.getText("WeeklyPlannedWagesTotal");
		DBPlndTtl = sql.CLRexecuteQuery(getContext(),
				WeeklyPlannedWagesTotal.replace("store", getContext().getStringProperty("store"))
						.replace("Month", getContext().getStringProperty("MonthToTest"))
						.replace("Division", getContext().getStringProperty("Division")));
		System.out.println("DB Value: " + DBPlndTtl);

		PlndVal = pageDailyTradingStatementGroupPO.RowWiseResult(objWeeklyTradingStatementStoreObjects,
				objWeeklyTradingStatementStoreObjects.Weeks, objWeeklyTradingStatementStoreObjects.WeeklyPlannedWages,
				objWeeklyTradingStatementStoreObjects.Table);
		PlndTtl = pageDailyTradingStatementGroupPO.WeekToDateUITotal(objWeeklyTradingStatementStoreObjects.MonthTotal,
				objWeeklyTradingStatementStoreObjects.WeeklyPlannedWagesTtl);

		pageMonthlyPlanningPO.CompareValues(extentTest, DBPlndVal, PlndVal);
		pageMonthlyPlanningPO.CompareValues(extentTest, DBPlndTtl, PlndTtl);

		// MONTHLY PLANNED WAGES:
		Report_AddStep("testcase", "MONTHLY PLANNED WAGES COMBINED TABLE - WEEK WISE DATA", "", "", "");
		extentTest = report.startTest("Planned Wage - Month Data");

		String MonthlyPlannedWages = data.getText("MonthlyPlannedWages");
		DBPlndVal = sql.CLRexecuteQuery(getContext(),
				MonthlyPlannedWages.replace("store", getContext().getStringProperty("store"))
						.replace("Month", getContext().getStringProperty("MonthToTest"))
						.replace("Division", getContext().getStringProperty("Division")));
		System.out.println("DB Value: " + DBPlndVal);

		String MonthlyPlannedWagesTotal = data.getText("MonthlyPlannedWagesTotal");
		DBPlndTtl = sql.CLRexecuteQuery(getContext(),
				MonthlyPlannedWagesTotal.replace("store", getContext().getStringProperty("store"))
						.replace("Month", getContext().getStringProperty("MonthToTest"))
						.replace("Division", getContext().getStringProperty("Division")));
		System.out.println("DB Value: " + DBPlndTtl);

		PlndVal = pageDailyTradingStatementGroupPO.RowWiseResult(objWeeklyTradingStatementStoreObjects,
				objWeeklyTradingStatementStoreObjects.Weeks, objWeeklyTradingStatementStoreObjects.MonthlyPlannedWages,
				objWeeklyTradingStatementStoreObjects.Table);
		PlndTtl = pageDailyTradingStatementGroupPO.WeekToDateUITotal(objWeeklyTradingStatementStoreObjects.MonthTotal,
				objWeeklyTradingStatementStoreObjects.MonthlyPlannedWagesTtl);

		pageMonthlyPlanningPO.CompareValues(extentTest, DBPlndVal, PlndVal);
		pageMonthlyPlanningPO.CompareValues(extentTest, DBPlndTtl, PlndTtl);

		// BUDGET WAGES:
		if (OptionToView.contains("Budget")) {
			Report_AddStep("testcase", "BUDGET WAGES COMBINED TABLE- WEEK WISE DATA", "", "", "");
			extentTest = report.startTest("Budget Wage");

			String BudgetWages = data.getText("BudgetWages");
			DBBdgtVal = sql.CLRexecuteQuery(getContext(),
					BudgetWages.replace("store", getContext().getStringProperty("store"))
							.replace("Month", getContext().getStringProperty("MonthToTest"))
							.replace("Division", getContext().getStringProperty("Division")));
			System.out.println("DB Value: " + DBBdgtVal);

			String BudgetWagesTotal = data.getText("BudgetWagesTotal");
			DBBdgtTtl = sql.CLRexecuteQuery(getContext(),
					BudgetWagesTotal.replace("store", getContext().getStringProperty("store"))
							.replace("Month", getContext().getStringProperty("MonthToTest"))
							.replace("Division", getContext().getStringProperty("Division")));
			System.out.println("DB Value: " + DBBdgtTtl);

			BdgtVal = pageDailyTradingStatementGroupPO.RowWiseResult(objWeeklyTradingStatementStoreObjects,
					objWeeklyTradingStatementStoreObjects.Weeks, objWeeklyTradingStatementStoreObjects.BudgetWagesNew,
					objWeeklyTradingStatementStoreObjects.Table);
			BdgtTtl = pageDailyTradingStatementGroupPO.WeekToDateUITotal(
					objWeeklyTradingStatementStoreObjects.MonthTotal,
					objWeeklyTradingStatementStoreObjects.BudgetWagesTtlNew);

			pageMonthlyPlanningPO.CompareValues(extentTest, DBBdgtVal, BdgtVal);
			pageMonthlyPlanningPO.CompareValues(extentTest, DBBdgtTtl, BdgtTtl);
		}
		if (OptionToView.contains("Forecast")) {
			Report_AddStep("testcase", "FORECAST WAGES COMBINED TABLE - WEEK WISE DATA", "", "", "");
			extentTest = report.startTest("Forecast Wage");

			String ForecastWages = data.getText("ForecastWages");
			DBBdgtVal = sql.CLRexecuteQuery(getContext(),
					ForecastWages.replace("store", getContext().getStringProperty("store"))
							.replace("Month", getContext().getStringProperty("MonthToTest"))
							.replace("Division", getContext().getStringProperty("Division")));
			System.out.println("DB Value: " + DBBdgtVal);

			String ForecastWagesTotal = data.getText("ForecastWagesTotal");
			DBBdgtTtl = sql.CLRexecuteQuery(getContext(),
					ForecastWagesTotal.replace("store", getContext().getStringProperty("store"))
							.replace("Month", getContext().getStringProperty("MonthToTest"))
							.replace("Division", getContext().getStringProperty("Division")));
			System.out.println("DB Value: " + DBBdgtTtl);

			BdgtVal = pageDailyTradingStatementGroupPO.RowWiseResult(objWeeklyTradingStatementStoreObjects,
					objWeeklyTradingStatementStoreObjects.Weeks, objWeeklyTradingStatementStoreObjects.ForecastWagesNew,
					objWeeklyTradingStatementStoreObjects.Table);
			BdgtTtl = pageDailyTradingStatementGroupPO.WeekToDateUITotal(
					objWeeklyTradingStatementStoreObjects.MonthTotal,
					objWeeklyTradingStatementStoreObjects.ForecastWagesTtlNew);

			pageMonthlyPlanningPO.CompareValues(extentTest, DBBdgtVal, BdgtVal);
			pageMonthlyPlanningPO.CompareValues(extentTest, DBBdgtTtl, BdgtTtl);
		}
	}

	///// WAGE%///
	@Test(description = "Weekly Trading Statement - Store, WagePercentageColumnValidationNewTable", priority = 8)
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
		extentTest = report.startTest("Actual Wage percentage");

		String ActualWagePercentageNew = data.getText("ActualWagePercentage");
		DBActlVal = sql.CLRexecuteQuery(getContext(),
				ActualWagePercentageNew.replace("store", getContext().getStringProperty("store"))
						.replace("Month", getContext().getStringProperty("MonthToTest"))
						.replace("Division", getContext().getStringProperty("Division")));
		System.out.println("DB Value: " + DBActlVal);

		String ActualWagePercentageTotal = data.getText("ActualWagePercentageTotal");
		DBActlTtl = sql.CLRexecuteQuery(getContext(),
				ActualWagePercentageTotal.replace("store", getContext().getStringProperty("store"))
						.replace("Month", getContext().getStringProperty("MonthToTest"))
						.replace("Division", getContext().getStringProperty("Division")));
		System.out.println("DB Value: " + DBActlTtl);

		ActlVal = pageDailyTradingStatementGroupPO.RowWiseResult(objWeeklyTradingStatementStoreObjects,
				objWeeklyTradingStatementStoreObjects.Weeks, objWeeklyTradingStatementStoreObjects.ActualWagePerNew,
				objWeeklyTradingStatementStoreObjects.Table);
		ActlTtl = pageDailyTradingStatementGroupPO.WeekToDateUITotal(objWeeklyTradingStatementStoreObjects.MonthTotal,
				objWeeklyTradingStatementStoreObjects.ActualWagePerTtlNew);

		pageMonthlyPlanningPO.CompareValues(extentTest, DBActlVal, ActlVal);
		pageMonthlyPlanningPO.CompareValues(extentTest, DBActlTtl, ActlTtl);

		// WEEKLY PLANNED WAGE%:
		Report_AddStep("testcase", " WEEKLY PLANNED WAGE PERCENTAGE COMBINED TABLE- WEEK WISE DATA", "", "", "");
		extentTest = report.startTest("Planned Wage Percentage - Week Data");

		String WeeklyPlannedWagePercentage = data.getText("WeeklyPlannedWagePercentage");
		DBPlndVal = sql.CLRexecuteQuery(getContext(),
				WeeklyPlannedWagePercentage.replace("store", getContext().getStringProperty("store"))
						.replace("Month", getContext().getStringProperty("MonthToTest"))
						.replace("Division", getContext().getStringProperty("Division")));
		System.out.println("DB Value: " + DBPlndVal);

		String WeeklyPlannedWagePercentageTotal = data.getText("WeeklyPlannedWagePercentageTotal");
		DBPlndTtl = sql.CLRexecuteQuery(getContext(),
				WeeklyPlannedWagePercentageTotal.replace("store", getContext().getStringProperty("store"))
						.replace("Month", getContext().getStringProperty("MonthToTest"))
						.replace("Division", getContext().getStringProperty("Division")));
		System.out.println("DB Value: " + DBPlndTtl);

		PlndVal = pageDailyTradingStatementGroupPO.RowWiseResult(objWeeklyTradingStatementStoreObjects,
				objWeeklyTradingStatementStoreObjects.Weeks, objWeeklyTradingStatementStoreObjects.WeeklyPlannedWagePer,
				objWeeklyTradingStatementStoreObjects.Table);
		PlndTtl = pageDailyTradingStatementGroupPO.WeekToDateUITotal(objWeeklyTradingStatementStoreObjects.MonthTotal,
				objWeeklyTradingStatementStoreObjects.WeeklyPlannedWagePerTtl);

		pageMonthlyPlanningPO.CompareValues(extentTest, DBPlndVal, PlndVal);
		pageMonthlyPlanningPO.CompareValues(extentTest, DBPlndTtl, PlndTtl);

		// MONTHLY PLANNED WAGE%:
		Report_AddStep("testcase", "MONTHLY PLANNED WAGE PERCENTAGE COMBINED TABLE - WEEK WISE DATA", "", "", "");
		extentTest = report.startTest("Planned Wage Percentage - Month Data");

		String MonthlyPlannedWagePercentage = data.getText("MonthlyPlannedWagePercentage");
		DBPlndVal = sql.CLRexecuteQuery(getContext(),
				MonthlyPlannedWagePercentage.replace("store", getContext().getStringProperty("store"))
						.replace("Month", getContext().getStringProperty("MonthToTest"))
						.replace("Division", getContext().getStringProperty("Division")));
		System.out.println("DB Value: " + DBPlndVal);

		String MonthlyPlannedWagePercentageTotal = data.getText("MonthlyPlannedWagePercentageTotal");
		DBPlndTtl = sql.CLRexecuteQuery(getContext(),
				MonthlyPlannedWagePercentageTotal.replace("store", getContext().getStringProperty("store"))
						.replace("Month", getContext().getStringProperty("MonthToTest"))
						.replace("Division", getContext().getStringProperty("Division")));
		System.out.println("DB Value: " + DBPlndTtl);

		PlndVal = pageDailyTradingStatementGroupPO.RowWiseResult(objWeeklyTradingStatementStoreObjects,
				objWeeklyTradingStatementStoreObjects.Weeks,
				objWeeklyTradingStatementStoreObjects.MonthlyPlannedWagePer,
				objWeeklyTradingStatementStoreObjects.Table);
		PlndTtl = pageDailyTradingStatementGroupPO.WeekToDateUITotal(objWeeklyTradingStatementStoreObjects.MonthTotal,
				objWeeklyTradingStatementStoreObjects.MonthlyPlannedWagePerTtl);

		pageMonthlyPlanningPO.CompareValues(extentTest, DBPlndVal, PlndVal);
		pageMonthlyPlanningPO.CompareValues(extentTest, DBPlndTtl, PlndTtl);

		// BUDGET WAGE%:
		if (OptionToView.contains("Budget")) {
			Report_AddStep("testcase", "BUDGET WAGE PERCENTAGE COMBINED TABLE- WEEK WISE DATA", "", "", "");
			extentTest = report.startTest("Budget Wage Percentage");

			String BudgetWagePercentage = data.getText("BudgetWagePercentage");
			DBBdgtVal = sql.CLRexecuteQuery(getContext(),
					BudgetWagePercentage.replace("store", getContext().getStringProperty("store"))
							.replace("Month", getContext().getStringProperty("MonthToTest"))
							.replace("Division", getContext().getStringProperty("Division")));
			System.out.println("DB Value: " + DBBdgtVal);

			String BudgetWagePercentageTotal = data.getText("BudgetWagePercentageTotal");
			DBBdgtTtl = sql.CLRexecuteQuery(getContext(),
					BudgetWagePercentageTotal.replace("store", getContext().getStringProperty("store"))
							.replace("Month", getContext().getStringProperty("MonthToTest"))
							.replace("Division", getContext().getStringProperty("Division")));
			System.out.println("DB Value: " + DBBdgtTtl);

			BdgtVal = pageDailyTradingStatementGroupPO.RowWiseResult(objWeeklyTradingStatementStoreObjects,
					objWeeklyTradingStatementStoreObjects.Weeks, objWeeklyTradingStatementStoreObjects.BudgetWagePerNew,
					objWeeklyTradingStatementStoreObjects.Table);
			BdgtTtl = pageDailyTradingStatementGroupPO.WeekToDateUITotal(
					objWeeklyTradingStatementStoreObjects.MonthTotal,
					objWeeklyTradingStatementStoreObjects.BudgetWagePerTtlNew);

			pageMonthlyPlanningPO.CompareValues(extentTest, DBBdgtVal, BdgtVal);
			pageMonthlyPlanningPO.CompareValues(extentTest, DBBdgtTtl, BdgtTtl);
		}
		if (OptionToView.contains("Forecast")) {
			Report_AddStep("testcase", "FORECAST WAGE PERCENTAGE COMBINED TABLE - WEEK WISE DATA", "", "", "");
			extentTest = report.startTest("Forecast Wage Percentage");

			String ForecastWagePercentage = data.getText("ForecastWagePercentage");
			DBBdgtVal = sql.CLRexecuteQuery(getContext(),
					ForecastWagePercentage.replace("store", getContext().getStringProperty("store"))
							.replace("Month", getContext().getStringProperty("MonthToTest"))
							.replace("Division", getContext().getStringProperty("Division")));
			System.out.println("DB Value: " + DBBdgtVal);

			String ForecastWagePercentageTotal = data.getText("ForecastWagePercentageTotal");
			DBBdgtTtl = sql.CLRexecuteQuery(getContext(),
					ForecastWagePercentageTotal.replace("store", getContext().getStringProperty("store"))
							.replace("Month", getContext().getStringProperty("MonthToTest"))
							.replace("Division", getContext().getStringProperty("Division")));
			System.out.println("DB Value: " + DBBdgtTtl);

			BdgtVal = pageDailyTradingStatementGroupPO.RowWiseResult(objWeeklyTradingStatementStoreObjects,
					objWeeklyTradingStatementStoreObjects.Weeks,
					objWeeklyTradingStatementStoreObjects.ForecastWagePerNew,
					objWeeklyTradingStatementStoreObjects.Table);
			BdgtTtl = pageDailyTradingStatementGroupPO.WeekToDateUITotal(
					objWeeklyTradingStatementStoreObjects.MonthTotal,
					objWeeklyTradingStatementStoreObjects.ForecastWagePerTtlNew);

			pageMonthlyPlanningPO.CompareValues(extentTest, DBBdgtVal, BdgtVal);
			pageMonthlyPlanningPO.CompareValues(extentTest, DBBdgtTtl, BdgtTtl);
		}
	}

	///// OR///
	@Test(description = "Weekly Trading Statement - Store, ORColumnValidationNewTable", priority = 9)
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
				ActualOR.replace("store", getContext().getStringProperty("store"))
						.replace("Month", getContext().getStringProperty("MonthToTest"))
						.replace("Division", getContext().getStringProperty("Division")));
		System.out.println("DB Value: " + DBActlVal);

		String ActualORTotal = data.getText("ActualORTotal");
		DBActlTtl = sql.CLRexecuteQuery(getContext(),
				ActualORTotal.replace("store", getContext().getStringProperty("store"))
						.replace("Month", getContext().getStringProperty("MonthToTest"))
						.replace("Division", getContext().getStringProperty("Division")));
		System.out.println("DB Value: " + DBActlTtl);

		ActlVal = pageDailyTradingStatementGroupPO.RowWiseResult(objWeeklyTradingStatementStoreObjects,
				objWeeklyTradingStatementStoreObjects.Weeks, objWeeklyTradingStatementStoreObjects.ActualORNew,
				objWeeklyTradingStatementStoreObjects.Table);
		ActlTtl = pageDailyTradingStatementGroupPO.WeekToDateUITotal(objWeeklyTradingStatementStoreObjects.MonthTotal,
				objWeeklyTradingStatementStoreObjects.ActualORTtlNew);

		pageMonthlyPlanningPO.CompareValues(extentTest, DBActlVal, ActlVal);
		pageMonthlyPlanningPO.CompareValues(extentTest, DBActlTtl, ActlTtl);

		// WEEKLY PLANNED OR:
		Report_AddStep("testcase", " WEEKLY PLANNED OR  COMBINED TABLE- WEEK WISE DATA", "", "", "");
		extentTest = report.startTest("Planned OR - Week Data");

		String WeeklyPlannedOR = data.getText("WeeklyPlannedOR");
		DBPlndVal = sql.CLRexecuteQuery(getContext(),
				WeeklyPlannedOR.replace("store", getContext().getStringProperty("store"))
						.replace("Month", getContext().getStringProperty("MonthToTest"))
						.replace("Division", getContext().getStringProperty("Division")));
		System.out.println("DB Value: " + DBPlndVal);

		String WeeklyPlannedORTotal = data.getText("WeeklyPlannedORTotal");
		DBPlndTtl = sql.CLRexecuteQuery(getContext(),
				WeeklyPlannedORTotal.replace("store", getContext().getStringProperty("store"))
						.replace("Month", getContext().getStringProperty("MonthToTest"))
						.replace("Division", getContext().getStringProperty("Division")));
		System.out.println("DB Value: " + DBPlndTtl);

		PlndVal = pageDailyTradingStatementGroupPO.RowWiseResult(objWeeklyTradingStatementStoreObjects,
				objWeeklyTradingStatementStoreObjects.Weeks, objWeeklyTradingStatementStoreObjects.WeeklyPlannedOR,
				objWeeklyTradingStatementStoreObjects.Table);
		PlndTtl = pageDailyTradingStatementGroupPO.WeekToDateUITotal(objWeeklyTradingStatementStoreObjects.MonthTotal,
				objWeeklyTradingStatementStoreObjects.WeeklyPlannedORTtl);

		pageMonthlyPlanningPO.CompareValues(extentTest, DBPlndVal, PlndVal);
		pageMonthlyPlanningPO.CompareValues(extentTest, DBPlndTtl, PlndTtl);

		// MONTHLY PLANNED OR:
		Report_AddStep("testcase", "MONTHLY PLANNED OR  COMBINED TABLE - WEEK WISE DATA", "", "", "");
		extentTest = report.startTest("Planned OR - Month Data");

		String MonthlyPlannedOR = data.getText("MonthlyPlannedOR");
		DBPlndVal = sql.CLRexecuteQuery(getContext(),
				MonthlyPlannedOR.replace("store", getContext().getStringProperty("store"))
						.replace("Month", getContext().getStringProperty("MonthToTest"))
						.replace("Division", getContext().getStringProperty("Division")));
		System.out.println("DB Value: " + DBPlndVal);

		String MonthlyPlannedORTotal = data.getText("MonthlyPlannedORTotal");
		DBPlndTtl = sql.CLRexecuteQuery(getContext(),
				MonthlyPlannedORTotal.replace("store", getContext().getStringProperty("store"))
						.replace("Month", getContext().getStringProperty("MonthToTest"))
						.replace("Division", getContext().getStringProperty("Division")));
		System.out.println("DB Value: " + DBPlndTtl);

		PlndVal = pageDailyTradingStatementGroupPO.RowWiseResult(objWeeklyTradingStatementStoreObjects,
				objWeeklyTradingStatementStoreObjects.Weeks, objWeeklyTradingStatementStoreObjects.MonthlyPlannedOR,
				objWeeklyTradingStatementStoreObjects.Table);
		PlndTtl = pageDailyTradingStatementGroupPO.WeekToDateUITotal(objWeeklyTradingStatementStoreObjects.MonthTotal,
				objWeeklyTradingStatementStoreObjects.MonthlyPlannedORTtl);

		pageMonthlyPlanningPO.CompareValues(extentTest, DBPlndVal, PlndVal);
		pageMonthlyPlanningPO.CompareValues(extentTest, DBPlndTtl, PlndTtl);
	}

	///// CPH///
	@Test(description = "Weekly Trading Statement - Store, CPHColumnValidationNewTable", priority = 10)
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
				ActualCPH.replace("store", getContext().getStringProperty("store"))
						.replace("Month", getContext().getStringProperty("MonthToTest"))
						.replace("Division", getContext().getStringProperty("Division")));
		System.out.println("DB Value: " + DBActlVal);

		String ActualCPHTotal = data.getText("ActualCPHTotal");
		DBActlTtl = sql.CLRexecuteQuery(getContext(),
				ActualCPHTotal.replace("store", getContext().getStringProperty("store"))
						.replace("Month", getContext().getStringProperty("MonthToTest"))
						.replace("Division", getContext().getStringProperty("Division")));
		System.out.println("DB Value: " + DBActlTtl);

		ActlVal = pageDailyTradingStatementGroupPO.RowWiseResult(objWeeklyTradingStatementStoreObjects,
				objWeeklyTradingStatementStoreObjects.Weeks, objWeeklyTradingStatementStoreObjects.ActualCPHNew,
				objWeeklyTradingStatementStoreObjects.Table);
		ActlTtl = pageDailyTradingStatementGroupPO.WeekToDateUITotal(objWeeklyTradingStatementStoreObjects.MonthTotal,
				objWeeklyTradingStatementStoreObjects.ActualCPHTtlNew);

		pageMonthlyPlanningPO.CompareValues(extentTest, DBActlVal, ActlVal);
		pageMonthlyPlanningPO.CompareValues(extentTest, DBActlTtl, ActlTtl);

		// WEEKLY PLANNED CPH:
		Report_AddStep("testcase", " WEEKLY PLANNED CPH  COMBINED TABLE- WEEK WISE DATA", "", "", "");
		extentTest = report.startTest("Planned CPH - Week Data");

		String WeeklyPlannedCPH = data.getText("WeeklyPlannedCPH");
		DBPlndVal = sql.CLRexecuteQuery(getContext(),
				WeeklyPlannedCPH.replace("store", getContext().getStringProperty("store"))
						.replace("Month", getContext().getStringProperty("MonthToTest"))
						.replace("Division", getContext().getStringProperty("Division")));
		System.out.println("DB Value: " + DBPlndVal);

		String WeeklyPlannedCPHTotal = data.getText("WeeklyPlannedCPHTotal");
		DBPlndTtl = sql.CLRexecuteQuery(getContext(),
				WeeklyPlannedCPHTotal.replace("store", getContext().getStringProperty("store"))
						.replace("Month", getContext().getStringProperty("MonthToTest"))
						.replace("Division", getContext().getStringProperty("Division")));
		System.out.println("DB Value: " + DBPlndTtl);

		PlndVal = pageDailyTradingStatementGroupPO.RowWiseResult(objWeeklyTradingStatementStoreObjects,
				objWeeklyTradingStatementStoreObjects.Weeks, objWeeklyTradingStatementStoreObjects.WeeklyPlannedCPH,
				objWeeklyTradingStatementStoreObjects.Table);
		PlndTtl = pageDailyTradingStatementGroupPO.WeekToDateUITotal(objWeeklyTradingStatementStoreObjects.MonthTotal,
				objWeeklyTradingStatementStoreObjects.WeeklyPlannedCPHTtl);

		pageMonthlyPlanningPO.CompareValues(extentTest, DBPlndVal, PlndVal);
		pageMonthlyPlanningPO.CompareValues(extentTest, DBPlndTtl, PlndTtl);

		// MONTHLY PLANNED CPH:
		Report_AddStep("testcase", "MONTHLY PLANNED CPH  COMBINED TABLE - WEEK WISE DATA", "", "", "");
		extentTest = report.startTest("Planned CPD - Month Data");

		String MonthlyPlannedCPH = data.getText("MonthlyPlannedCPH");
		DBPlndVal = sql.CLRexecuteQuery(getContext(),
				MonthlyPlannedCPH.replace("store", getContext().getStringProperty("store"))
						.replace("Month", getContext().getStringProperty("MonthToTest"))
						.replace("Division", getContext().getStringProperty("Division")));
		System.out.println("DB Value: " + DBPlndVal);

		String MonthlyPlannedCPHTotal = data.getText("MonthlyPlannedCPHTotal");
		DBPlndTtl = sql.CLRexecuteQuery(getContext(),
				MonthlyPlannedCPHTotal.replace("store", getContext().getStringProperty("store"))
						.replace("Month", getContext().getStringProperty("MonthToTest"))
						.replace("Division", getContext().getStringProperty("Division")));
		System.out.println("DB Value: " + DBPlndTtl);

		PlndVal = pageDailyTradingStatementGroupPO.RowWiseResult(objWeeklyTradingStatementStoreObjects,
				objWeeklyTradingStatementStoreObjects.Weeks, objWeeklyTradingStatementStoreObjects.MonthlyPlannedCPH,
				objWeeklyTradingStatementStoreObjects.Table);
		PlndTtl = pageDailyTradingStatementGroupPO.WeekToDateUITotal(objWeeklyTradingStatementStoreObjects.MonthTotal,
				objWeeklyTradingStatementStoreObjects.MonthlyPlannedCPHTtl);

		pageMonthlyPlanningPO.CompareValues(extentTest, DBPlndVal, PlndVal);
		pageMonthlyPlanningPO.CompareValues(extentTest, DBPlndTtl, PlndTtl);
	}
////////////////////////////////////////////////PST 1073////////////////////////////////////////////////////////////////////////////////
	
	@Test(description = "Weekly Trading Statement - Store, LonglifeseafoodColumnValidation", priority = 11)
	public void LonglifeseafoodSalesColumnValidation() throws Exception {
		
		pageWeeklyPlanningPO = new WeeklyPlanningStorePage().createPage(getDriver(), getContext(), log, status, data,
		getReport(), getReportLogger(), WeeklyPlanningStorePage.class);
		pageDailyTradingStatementGroupPO = new MonthlyPlanningGroupPage().createPage(getDriver(), getContext(), log,
				status, data, getReport(), getReportLogger(), DailyTradingStatementGroupPage.class);
		TestStatus status = getTestStatus();
		System.out.println("Entering into TestCase: Sales Column Metrics Validation");
		OptionToView = getContext().getStringProperty("OptionToView");
	
		Report_AddStep("testcase", " ACTUAL SALES - LONGLIFE E& SEAFOOD & DELI", "", "", "");
		extentTest = report.startTest("Actual Sales");

		String ActualSalesLL = data.getText("ActualSalesTotal");
		DBActlValLL = sql.CLRexecuteQuery(getContext(),
				ActualSalesLL.replace("store", getContext().getStringProperty("store"))
						.replace("LYWk", getContext().getStringProperty("LYWk"))
						.replace("Week", getContext().getStringProperty("SelectFiscalWeek"))
						.replace("Division", getContext().getStringProperty("Division")).replace("Department", getContext().getStringProperty("Longlife")));
		System.out.println("DB Value: " + DBActlValLL);

		String ActualSalesSD = data.getText("ActualSalesTotal");
		DBActlValSD = sql.CLRexecuteQuery(getContext(),
				ActualSalesSD.replace("store", getContext().getStringProperty("store"))
						.replace("LYWk", getContext().getStringProperty("LYWk"))
						.replace("Week", getContext().getStringProperty("SelectFiscalWeek"))
						.replace("Division", getContext().getStringProperty("Division")).replace("Department", getContext().getStringProperty("SeaFoodDeli")));
		System.out.println("DB Value: " + DBActlValSD);

		
		ActlLL = pageDailyTradingStatementGroupPO.WeekToDateUITotal(objWeeklyTradingStatementStoreObjects.LLTitle,
				objWeeklyTradingStatementStoreObjects.ActualSalesLL);
		
		ActlSD = pageDailyTradingStatementGroupPO.WeekToDateUITotal(objWeeklyTradingStatementStoreObjects.SDTitle,
				objWeeklyTradingStatementStoreObjects.ActualSalesSD);

		pageWeeklyPlanningPO.CompareValues(extentTest, DBActlValLL, ActlLL);
		pageMonthlyPlanningPO.CompareValues(extentTest, DBActlValSD, ActlSD);

		// PLANNED SALES:
		Report_AddStep("testcase", " PLANNED SALES - LONGLIFE E& SEAFOOD & DELI", "", "", "");
		extentTest = report.startTest("Planned Sales");

		String PlannedSalesLL = data.getText("PlannedSalesTotal");
		DBPlndValLL = sql.CLRexecuteQuery(getContext(),
				PlannedSalesLL.replace("store", getContext().getStringProperty("store"))
						.replace("LYWk", getContext().getStringProperty("LYWk"))
						.replace("Week", getContext().getStringProperty("SelectFiscalWeek"))
						.replace("Division", getContext().getStringProperty("Division")).replace("Department", getContext().getStringProperty("Longlife")));
		System.out.println("DB Value: " + DBPlndValLL);

		String PlannedSalesSD = data.getText("PlannedSalesTotal");
		DBPlndValSD = sql.CLRexecuteQuery(getContext(),
				PlannedSalesSD.replace("store", getContext().getStringProperty("store"))
						.replace("LYWk", getContext().getStringProperty("LYWk"))
						.replace("Week", getContext().getStringProperty("SelectFiscalWeek"))
						.replace("Division", getContext().getStringProperty("Division")).replace("Department", getContext().getStringProperty("SeaFoodDeli")));
		System.out.println("DB Value: " + DBPlndValSD);

		
		PlndLL = pageDailyTradingStatementGroupPO.WeekToDateUITotal(objWeeklyTradingStatementStoreObjects.LLTitle,
				objWeeklyTradingStatementStoreObjects.PlannedSalesLL);
		PlndSD = pageDailyTradingStatementGroupPO.WeekToDateUITotal(objWeeklyTradingStatementStoreObjects.SDTitle,
				objWeeklyTradingStatementStoreObjects.PlannedSalesSD);

		pageWeeklyPlanningPO.CompareValues(extentTest, DBPlndValLL, PlndLL);
		pageMonthlyPlanningPO.CompareValues(extentTest, DBPlndValSD, PlndSD);

		// VARIANCE IN PLANNED SALES:
		Report_AddStep("testcase", " VARIANCE IN PLANNED SALES - LONGLIFE E& SEAFOOD & DELI", "", "", "");
		extentTest = report.startTest("Variance Sales");

		String VarSalesLL = data.getText("VarSalesTotal");
		DBVarPlndValLL = sql.CLRexecuteQuery(getContext(),
				VarSalesLL.replace("store", getContext().getStringProperty("store"))
						.replace("LYWk", getContext().getStringProperty("LYWk"))
						.replace("Week", getContext().getStringProperty("SelectFiscalWeek"))
						.replace("Division", getContext().getStringProperty("Division")).replace("Department", getContext().getStringProperty("Longlife")));
		System.out.println("DB Value: " + DBVarPlndValLL);

		String VarSalesSD = data.getText("VarSalesTotal");
		DBVarPlndValSD = sql.CLRexecuteQuery(getContext(),
				VarSalesSD.replace("store", getContext().getStringProperty("store"))
						.replace("LYWk", getContext().getStringProperty("LYWk"))
						.replace("Week", getContext().getStringProperty("SelectFiscalWeek"))
						.replace("Division", getContext().getStringProperty("Division")).replace("Department", getContext().getStringProperty("SeaFoodDeli")));
		System.out.println("DB Value: " + DBVarPlndValSD);

		
		VarPlndLL = pageDailyTradingStatementGroupPO.WeekToDateUITotal(
				objWeeklyTradingStatementStoreObjects.LLTitle,
				objWeeklyTradingStatementStoreObjects.PlannedSalesGrwthLL);
		VarPlndSD = pageDailyTradingStatementGroupPO.WeekToDateUITotal(
				objWeeklyTradingStatementStoreObjects.SDTitle,
				objWeeklyTradingStatementStoreObjects.PlannedSalesGrwthSD);

		pageWeeklyPlanningPO.CompareValues(extentTest, DBVarPlndValLL, VarPlndLL);
		pageMonthlyPlanningPO.CompareValues(extentTest, DBVarPlndValSD, VarPlndSD);

		// LAST YEAR SALES:
		Report_AddStep("testcase", " LAST YEAR SALES - WEEK WISE DATA", "", "", "");
		extentTest = report.startTest("LY Sales");

		String LYSalesLL = data.getText("LYSalesTotal");
		DBLYSlsValLL = sql.CLRexecuteQuery(getContext(),
				LYSalesLL.replace("store", getContext().getStringProperty("store"))
						.replace("LYWk", getContext().getStringProperty("LYWk"))
						.replace("Week", getContext().getStringProperty("SelectFiscalWeek"))
						.replace("Division", getContext().getStringProperty("Division")).replace("Department", getContext().getStringProperty("Longlife")));
		System.out.println("DB Value: " + DBLYSlsValLL);

		String LYSalesSD = data.getText("LYSalesTotal");
		DBLYSlsValSD = sql.CLRexecuteQuery(getContext(),
				LYSalesSD.replace("store", getContext().getStringProperty("store"))
						.replace("LYWk", getContext().getStringProperty("LYWk"))
						.replace("Week", getContext().getStringProperty("SelectFiscalWeek"))
						.replace("Division", getContext().getStringProperty("Division")).replace("Department", getContext().getStringProperty("SeaFoodDeli")));
		System.out.println("DB Value: " + DBLYSlsValSD);

		
		LYSlsValLL = pageDailyTradingStatementGroupPO.WeekToDateUITotal(objWeeklyTradingStatementStoreObjects.LLTitle,
				objWeeklyTradingStatementStoreObjects.LYSalesLL);
		LYSlsValSD = pageDailyTradingStatementGroupPO.WeekToDateUITotal(objWeeklyTradingStatementStoreObjects.SDTitle,
				objWeeklyTradingStatementStoreObjects.LYSalesSD);

		pageWeeklyPlanningPO.CompareValues(extentTest, DBLYSlsValLL, LYSlsValLL);
		pageMonthlyPlanningPO.CompareValues(extentTest, DBLYSlsValSD, LYSlsValSD);

		// VARIANCE IN LAST YEAR SALES:
		Report_AddStep("testcase", " VARIANCE IN LAST YEAR SALES - LONGLIFE E& SEAFOOD & DELI", "", "", "");
		extentTest = report.startTest("Variance LY Sales");

		String LYSalesPctLL = data.getText("LYSalesPctTotal");
		DBVarLYSlsValLL = sql.CLRexecuteQuery(getContext(),
				LYSalesPctLL.replace("store", getContext().getStringProperty("store"))
						.replace("LYWk", getContext().getStringProperty("LYWk"))
						.replace("Week", getContext().getStringProperty("SelectFiscalWeek"))
						.replace("Division", getContext().getStringProperty("Division")).replace("Department", getContext().getStringProperty("Longlife")));
		System.out.println("DB Value: " + DBVarLYSlsValLL);

		String LYSalesPctSD = data.getText("LYSalesPctTotal");
		DBVarLYSlsValSD = sql.CLRexecuteQuery(getContext(),
				LYSalesPctSD.replace("store", getContext().getStringProperty("store"))
						.replace("LYWk", getContext().getStringProperty("LYWk"))
						.replace("Week", getContext().getStringProperty("SelectFiscalWeek"))
						.replace("Division", getContext().getStringProperty("Division")).replace("Department", getContext().getStringProperty("SeaFoodDeli")));
		System.out.println("DB Value: " + DBVarLYSlsValSD);

		
		VarLYSlsLL = pageDailyTradingStatementGroupPO.WeekToDateUITotal(
				objWeeklyTradingStatementStoreObjects.LLTitle,
				objWeeklyTradingStatementStoreObjects.LYSalesGrwthLL);
		VarLYSlsSD = pageDailyTradingStatementGroupPO.WeekToDateUITotal(
				objWeeklyTradingStatementStoreObjects.SDTitle,
				objWeeklyTradingStatementStoreObjects.LYSalesGrwthSD);

		pageWeeklyPlanningPO.CompareValues(extentTest, DBVarLYSlsValLL, VarLYSlsLL);
		pageMonthlyPlanningPO.CompareValues(extentTest, DBVarLYSlsValSD, VarLYSlsSD);

		// BUDGET SALES:
		if (OptionToView.contains("Budget")) {
			Report_AddStep("testcase", "BUDGET SALES - LONGLIFE E& SEAFOOD & DELI", "", "", "");
			extentTest = report.startTest("Budget Sales");

			String BudgetSalesLL = data.getText("BudgetSalesTotal");
			DBBdgtValLL = sql.CLRexecuteQuery(getContext(),
					BudgetSalesLL.replace("store", getContext().getStringProperty("store"))
							.replace("LYWk", getContext().getStringProperty("LYWk"))
							.replace("Week", getContext().getStringProperty("SelectFiscalWeek"))
							.replace("Division", getContext().getStringProperty("Division")).replace("Department", getContext().getStringProperty("Longlife")));
			System.out.println("DB Value: " + DBBdgtValLL);

			String BudgetSalesSD = data.getText("BudgetSalesTotal");
			DBBdgtValSD = sql.CLRexecuteQuery(getContext(),
					BudgetSalesSD.replace("store", getContext().getStringProperty("store"))
							.replace("LYWk", getContext().getStringProperty("LYWk"))
							.replace("Week", getContext().getStringProperty("SelectFiscalWeek"))
							.replace("Division", getContext().getStringProperty("Division")).replace("Department", getContext().getStringProperty("SeaFoodDeli")));
			System.out.println("DB Value: " + DBBdgtValSD);

			
			BdgtLL = pageDailyTradingStatementGroupPO.WeekToDateUITotal(
					objWeeklyTradingStatementStoreObjects.LLTitle,
					objWeeklyTradingStatementStoreObjects.BudgetSalesLL);
			
			BdgtSD = pageDailyTradingStatementGroupPO.WeekToDateUITotal(
					objWeeklyTradingStatementStoreObjects.SDTitle,
					objWeeklyTradingStatementStoreObjects.BudgetSalesSD);

			pageWeeklyPlanningPO.CompareValues(extentTest, DBBdgtValLL, BdgtLL);
			pageMonthlyPlanningPO.CompareValues(extentTest, DBBdgtValSD, BdgtSD);
		}
		if (OptionToView.contains("Forecast")) {
			Report_AddStep("testcase", "FORECAST SALES - LONGLIFE E& SEAFOOD & DELI", "", "", "");
			extentTest = report.startTest("Forecast Sales");

			String ForecastSalesLL = data.getText("ForecastSalesTotal");
			DBBdgtValLL = sql.CLRexecuteQuery(getContext(),
					ForecastSalesLL.replace("store", getContext().getStringProperty("store"))
							.replace("LYWk", getContext().getStringProperty("LYWk"))
							.replace("Week", getContext().getStringProperty("SelectFiscalWeek"))
							.replace("Division", getContext().getStringProperty("Division")).replace("Department", getContext().getStringProperty("Longlife")));
			System.out.println("DB Value: " + DBBdgtValLL);

			String ForecastSalesSD = data.getText("ForecastSalesTotal");
			DBBdgtValSD = sql.CLRexecuteQuery(getContext(),
					ForecastSalesSD.replace("store", getContext().getStringProperty("store"))
							.replace("LYWk", getContext().getStringProperty("LYWk"))
							.replace("Week", getContext().getStringProperty("SelectFiscalWeek"))
							.replace("Division", getContext().getStringProperty("Division")).replace("Department", getContext().getStringProperty("SeaFoodDeli")));
			System.out.println("DB Value: " + DBBdgtValSD);

			BdgtLL = pageDailyTradingStatementGroupPO.WeekToDateUITotal(
					objWeeklyTradingStatementStoreObjects.LLTitle,
					objWeeklyTradingStatementStoreObjects.BudgetSalesLL);
			BdgtSD = pageDailyTradingStatementGroupPO.WeekToDateUITotal(
					objWeeklyTradingStatementStoreObjects.SDTitle,
					objWeeklyTradingStatementStoreObjects.BudgetSalesSD);

			pageWeeklyPlanningPO.CompareValues(extentTest, DBBdgtValLL, BdgtLL);
			pageMonthlyPlanningPO.CompareValues(extentTest, DBBdgtValSD, BdgtSD);
		}

		// VARIANCE IN BUDGET SALES:
		
		if (OptionToView.contains("Budget")) {
			
			Report_AddStep("testcase", "VARIANCE BUDGET SALES - LONGLIFE E& SEAFOOD & DELI", "", "", "");
			extentTest = report.startTest("Variance Budget Sales");
			
			String VarBudgetSalesLL = data.getText("VarBudgetSalesTotal");
			DBVarBdgtValLL = sql.CLRexecuteQuery(getContext(),
					VarBudgetSalesLL.replace("store", getContext().getStringProperty("store"))
							.replace("LYWk", getContext().getStringProperty("LYWk"))
							.replace("Week", getContext().getStringProperty("SelectFiscalWeek"))
							.replace("Division", getContext().getStringProperty("Division")).replace("Department", getContext().getStringProperty("Longlife")));
			System.out.println("DB Value: " + DBVarBdgtValLL);

			String VarBudgetSalesSD = data.getText("VarBudgetSalesTotal");
			DBVarBdgtValSD = sql.CLRexecuteQuery(getContext(),
					VarBudgetSalesSD.replace("store", getContext().getStringProperty("store"))
							.replace("LYWk", getContext().getStringProperty("LYWk"))
							.replace("Week", getContext().getStringProperty("SelectFiscalWeek"))
							.replace("Division", getContext().getStringProperty("Division")).replace("Department", getContext().getStringProperty("SeaFoodDeli")));
			System.out.println("DB Value: " + DBVarBdgtValSD);

			
			VarBdgtLL = pageDailyTradingStatementGroupPO.WeekToDateUITotal(
					objWeeklyTradingStatementStoreObjects.LLTitle,
					objWeeklyTradingStatementStoreObjects.BudgetSalesGrwthLL);
			VarBdgtSD = pageDailyTradingStatementGroupPO.WeekToDateUITotal(
					objWeeklyTradingStatementStoreObjects.SDTitle,
					objWeeklyTradingStatementStoreObjects.BudgetSalesGrwthSD);

			pageWeeklyPlanningPO.CompareValues(extentTest, DBVarBdgtValLL, VarBdgtLL);
			pageMonthlyPlanningPO.CompareValues(extentTest, DBVarBdgtValSD, VarBdgtSD);
		}
		
		if (OptionToView.contains("Forecast")) {
			Report_AddStep("testcase", "VARIANCE IN FORECAST SALES - LONGLIFE E& SEAFOOD & DELI", "", "", "");
			extentTest = report.startTest("Variance Forecast Sales");

			String VarFcstSalesLL = data.getText("VarFcstSalesTotal");
			DBVarBdgtValLL = sql.CLRexecuteQuery(getContext(),
					VarFcstSalesLL.replace("store", getContext().getStringProperty("store"))
							.replace("LYWk", getContext().getStringProperty("LYWk"))
							.replace("Week", getContext().getStringProperty("SelectFiscalWeek"))
							.replace("Division", getContext().getStringProperty("Division")).replace("Department", getContext().getStringProperty("Longlife")));
			System.out.println("DB Value: " + DBVarBdgtValLL);

			String VarFcstSalesSD = data.getText("VarFcstSalesTotal");
			DBVarBdgtValSD = sql.CLRexecuteQuery(getContext(),
					VarFcstSalesSD.replace("store", getContext().getStringProperty("store"))
							.replace("LYWk", getContext().getStringProperty("LYWk"))
							.replace("Week", getContext().getStringProperty("SelectFiscalWeek"))
							.replace("Division", getContext().getStringProperty("Division")).replace("Department", getContext().getStringProperty("SeaFoodDeli")));
			System.out.println("DB Value: " + DBVarBdgtValSD);

			
			VarBdgtLL = pageDailyTradingStatementGroupPO.WeekToDateUITotal(
					objWeeklyTradingStatementStoreObjects.LLTitle,
					objWeeklyTradingStatementStoreObjects.BudgetSalesGrwthLL);
			
			VarBdgtSD = pageDailyTradingStatementGroupPO.WeekToDateUITotal(
					objWeeklyTradingStatementStoreObjects.SDTitle,
					objWeeklyTradingStatementStoreObjects.BudgetSalesGrwthSD);

			pageWeeklyPlanningPO.CompareValues(extentTest, DBVarBdgtValLL, VarBdgtLL);
			pageMonthlyPlanningPO.CompareValues(extentTest, DBVarBdgtValSD, VarBdgtSD);
		}

		// AVERAGE BASKET SALES:
		Report_AddStep("testcase", "AVERAGE BASKET SALES - LONGLIFE E& SEAFOOD & DELI", "", "", "");
		extentTest = report.startTest("Average Basket Sales");

		String AvgBasketSalesLL = data.getText("AvgBasketSalesTotal");
		DBAvgBsktValLL = sql.CLRexecuteQuery(getContext(),
				AvgBasketSalesLL.replace("store", getContext().getStringProperty("store"))
						.replace("LYWk", getContext().getStringProperty("LYWk"))
						.replace("Week", getContext().getStringProperty("SelectFiscalWeek"))
						.replace("Division", getContext().getStringProperty("Division")).replace("Department", getContext().getStringProperty("Longlife")));
		System.out.println("DB Value: " + DBAvgBsktValLL);

		String AvgBasketSalesSD = data.getText("AvgBasketSalesTotal");
		DBAvgBsktValSD = sql.CLRexecuteQuery(getContext(),
				AvgBasketSalesSD.replace("store", getContext().getStringProperty("store"))
						.replace("LYWk", getContext().getStringProperty("LYWk"))
						.replace("Week", getContext().getStringProperty("SelectFiscalWeek"))
						.replace("Division", getContext().getStringProperty("Division")).replace("Department", getContext().getStringProperty("SeaFoodDeli")));
		System.out.println("DB Value: " + DBAvgBsktValSD);

	
		AvgBsktLL = pageDailyTradingStatementGroupPO.WeekToDateUITotal(
				objWeeklyTradingStatementStoreObjects.LLTitle,
				objWeeklyTradingStatementStoreObjects.AvgBasketSalesLL);
		
		AvgBsktSD = pageDailyTradingStatementGroupPO.WeekToDateUITotal(
				objWeeklyTradingStatementStoreObjects.SDTitle,
				objWeeklyTradingStatementStoreObjects.AvgBasketSalesSD);

		pageWeeklyPlanningPO.CompareValues(extentTest, DBAvgBsktValLL, AvgBsktLL);
		pageMonthlyPlanningPO.CompareValues(extentTest, DBAvgBsktValSD, AvgBsktSD);

		// MIX SALES:
		Report_AddStep("testcase", "MIX SALES - LONGLIFE E& SEAFOOD & DELI", "", "", "");
		extentTest = report.startTest("Mix Sales");

		String WkyMixSalesLL = data.getText("WkyMixSalesTotal");
		DBMixValLL = sql.CLRexecuteQuery(getContext(),
				WkyMixSalesLL.replace("store", getContext().getStringProperty("store"))
						.replace("LYWk", getContext().getStringProperty("LYWk"))
						.replace("Week", getContext().getStringProperty("SelectFiscalWeek"))
						.replace("Division", getContext().getStringProperty("Division")).replace("Department", getContext().getStringProperty("Longlife")));
		System.out.println("DB Value: " + DBMixValLL);

		String WkyMixSalesSD = data.getText("WkyMixSalesTotal");
		DBMixValSD = sql.CLRexecuteQuery(getContext(),
				WkyMixSalesSD.replace("store", getContext().getStringProperty("store"))
						.replace("LYWk", getContext().getStringProperty("LYWk"))
						.replace("Week", getContext().getStringProperty("SelectFiscalWeek"))
						.replace("Division", getContext().getStringProperty("Division")).replace("Department", getContext().getStringProperty("SeaFoodDeli")));
		System.out.println("DB Value: " + DBMixValSD);

		
		MixLL = pageDailyTradingStatementGroupPO.WeekToDateUITotal(objWeeklyTradingStatementStoreObjects.LLTitle,
				objWeeklyTradingStatementStoreObjects.MixLL);
		
		MixSD = pageDailyTradingStatementGroupPO.WeekToDateUITotal(objWeeklyTradingStatementStoreObjects.SDTitle,
				objWeeklyTradingStatementStoreObjects.MixSD);

		pageWeeklyPlanningPO.CompareValues(extentTest, DBMixValLL, MixLL);
		pageMonthlyPlanningPO.CompareValues(extentTest, DBMixValSD, MixSD);
	}

	// Items and Item Price
	@Test(description = "Weekly Trading Statement - Store, Planned Item", priority = 12)
	public void LonglifeseafoodItemsColumnValidation() throws Exception {
		pageWeeklyPlanningPO = new WeeklyPlanningStorePage().createPage(getDriver(), getContext(), log, status, data,
				getReport(), getReportLogger(), WeeklyPlanningStorePage.class);
		pageDailyTradingStatementGroupPO = new MonthlyPlanningGroupPage().createPage(getDriver(), getContext(), log,
				status, data, getReport(), getReportLogger(), DailyTradingStatementGroupPage.class);
		TestStatus status = getTestStatus();
		System.out.println("Entering into TestCase : Item View");

		// PLANNED ITEMS:
		Report_AddStep("testcase", "PLANNED ITEMS - LONGLIFE E& SEAFOOD & DELI", "", "", "");
		extentTest = report.startTest("Planned Items");

		String PlannedItemsLL = data.getText("PlannedItemsTotal");
		DBPlndItmValLL = sql.CLRexecuteQuery(getContext(),
				PlannedItemsLL.replace("store", getContext().getStringProperty("store"))
						.replace("LYWk", getContext().getStringProperty("LYWk"))
						.replace("Week", getContext().getStringProperty("SelectFiscalWeek"))
						.replace("Division", getContext().getStringProperty("Division")).replace("Department", getContext().getStringProperty("Longlife")));
		System.out.println("DB Value: " + DBPlndItmValLL);

		String PlannedItemsSD = data.getText("PlannedItemsTotal");
		DBPlndItmValSD = sql.CLRexecuteQuery(getContext(),
				PlannedItemsSD.replace("store", getContext().getStringProperty("store"))
						.replace("LYWk", getContext().getStringProperty("LYWk"))
						.replace("Week", getContext().getStringProperty("SelectFiscalWeek"))
						.replace("Division", getContext().getStringProperty("Division")).replace("Department", getContext().getStringProperty("SeaFoodDeli")));
		System.out.println("DB Value: " + DBPlndItmValSD);

		
		PlndItmLL = pageDailyTradingStatementGroupPO.WeekToDateUITotal(
				objWeeklyTradingStatementStoreObjects.LLTitle, objWeeklyTradingStatementStoreObjects.PlannedItemLL);
		PlndItmSD = pageDailyTradingStatementGroupPO.WeekToDateUITotal(
				objWeeklyTradingStatementStoreObjects.SDTitle, objWeeklyTradingStatementStoreObjects.PlannedItemSD);

		pageWeeklyPlanningPO.CompareValues(extentTest, DBPlndItmValLL, PlndItmLL);
		pageMonthlyPlanningPO.CompareValues(extentTest, DBPlndItmValSD, PlndItmSD);

		// ACTUAL ITEMS:
		Report_AddStep("testcase", "ACTUAL ITEMS - LONGLIFE E& SEAFOOD & DELI", "", "", "");
		extentTest = report.startTest("Actual Items");

		String ActualItemsLL = data.getText("ActualItemsTotal");
		DBActlItmValLL = sql.CLRexecuteQuery(getContext(),
				ActualItemsLL.replace("store", getContext().getStringProperty("store"))
						.replace("LYWk", getContext().getStringProperty("LYWk"))
						.replace("Week", getContext().getStringProperty("SelectFiscalWeek"))
						.replace("Division", getContext().getStringProperty("Division")).replace("Department", getContext().getStringProperty("Longlife")));
		System.out.println("DB Value: " + DBActlItmValLL);

		String ActualItemsSD = data.getText("ActualItemsTotal");
		DBActlItmValSD = sql.CLRexecuteQuery(getContext(),
				ActualItemsSD.replace("store", getContext().getStringProperty("store"))
						.replace("LYWk", getContext().getStringProperty("LYWk"))
						.replace("Week", getContext().getStringProperty("SelectFiscalWeek"))
						.replace("Division", getContext().getStringProperty("Division")).replace("Department", getContext().getStringProperty("SeaFoodDeli")));
		System.out.println("DB Value: " + DBActlItmValSD);

		
		ActlItmLL = pageDailyTradingStatementGroupPO.WeekToDateUITotal(
				objWeeklyTradingStatementStoreObjects.LLTitle, objWeeklyTradingStatementStoreObjects.ActualItemLL);
		ActlItmSD = pageDailyTradingStatementGroupPO.WeekToDateUITotal(
				objWeeklyTradingStatementStoreObjects.SDTitle, objWeeklyTradingStatementStoreObjects.ActualItemSD);

		pageWeeklyPlanningPO.CompareValues(extentTest, DBActlItmValLL, ActlItmLL);
		pageMonthlyPlanningPO.CompareValues(extentTest, DBActlItmValSD, ActlItmSD);

		// PLANNED ITEM PRICE:
		Report_AddStep("testcase", "PLANNED ITEM PRICE - LONGLIFE E& SEAFOOD & DELI", "", "", "");
		extentTest = report.startTest("Planned Item Price");

		String PlannedItemPriceLL = data.getText("PlannedItemPriceTotal");
		DBPlndItmPriceValLL = sql.CLRexecuteQuery(getContext(),
				PlannedItemPriceLL.replace("store", getContext().getStringProperty("store"))
						.replace("LYWk", getContext().getStringProperty("LYWk"))
						.replace("Week", getContext().getStringProperty("SelectFiscalWeek"))
						.replace("Division", getContext().getStringProperty("Division")).replace("Department", getContext().getStringProperty("Longlife")));
		System.out.println("DB Value: " + DBPlndItmPriceValLL);

		String PlannedItemPriceSD = data.getText("PlannedItemPriceTotal");
		DBPlndItmPriceValSD = sql.CLRexecuteQuery(getContext(),
				PlannedItemPriceSD.replace("store", getContext().getStringProperty("store"))
						.replace("LYWk", getContext().getStringProperty("LYWk"))
						.replace("Week", getContext().getStringProperty("SelectFiscalWeek"))
						.replace("Division", getContext().getStringProperty("Division")).replace("Department", getContext().getStringProperty("SeaFoodDeli")));
		System.out.println("DB Value: " + DBPlndItmPriceValSD);

		
		PlndItmPriceLL = pageDailyTradingStatementGroupPO.WeekToDateUITotal(
				objWeeklyTradingStatementStoreObjects.LLTitle,
				objWeeklyTradingStatementStoreObjects.PlannedItemPriceLL);
		
		PlndItmPriceSD = pageDailyTradingStatementGroupPO.WeekToDateUITotal(
				objWeeklyTradingStatementStoreObjects.SDTitle,
				objWeeklyTradingStatementStoreObjects.PlannedItemPriceSD);


		pageWeeklyPlanningPO.CompareValueDecimalFields(extentTest, DBPlndItmPriceValLL, PlndItmPriceLL);
		pageMonthlyPlanningPO.CompareValueDecimalFields(extentTest, DBPlndItmPriceValSD, PlndItmPriceSD);

		// ACTUAL ITEM PRICE:
		Report_AddStep("testcase", "ACTUAL ITEM PRICE - LONGLIFE E& SEAFOOD & DELI", "", "", "");
		extentTest = report.startTest(" Actuals Item Price");

		String ActualItemPriceLL = data.getText("ActualItemPriceTotal");
		DBActlItmPriceValLL = sql.CLRexecuteQuery(getContext(),
				ActualItemPriceLL.replace("store", getContext().getStringProperty("store"))
						.replace("LYWk", getContext().getStringProperty("LYWk"))
						.replace("Week", getContext().getStringProperty("SelectFiscalWeek"))
						.replace("Division", getContext().getStringProperty("Division")).replace("Department", getContext().getStringProperty("Longlife")));
		System.out.println("DB Value: " + DBActlItmPriceValLL);

		String ActualItemPriceSD = data.getText("ActualItemPriceTotal");
		DBActlItmPriceValSD = sql.CLRexecuteQuery(getContext(),
				ActualItemPriceSD.replace("store", getContext().getStringProperty("store"))
						.replace("LYWk", getContext().getStringProperty("LYWk"))
						.replace("Week", getContext().getStringProperty("SelectFiscalWeek"))
						.replace("Division", getContext().getStringProperty("Division")).replace("Department", getContext().getStringProperty("SeaFoodDeli")));
		System.out.println("DB Value: " + DBActlItmPriceValSD);

		
		ActlItmPriceLL = pageDailyTradingStatementGroupPO.WeekToDateUITotal(
				objWeeklyTradingStatementStoreObjects.LLTitle,
				objWeeklyTradingStatementStoreObjects.ActualItemPriceLL);
		
		ActlItmPriceSD = pageDailyTradingStatementGroupPO.WeekToDateUITotal(
				objWeeklyTradingStatementStoreObjects.SDTitle,
				objWeeklyTradingStatementStoreObjects.ActualItemPriceSD);

		pageWeeklyPlanningPO.CompareValueDecimalFields(extentTest, DBActlItmPriceValLL, ActlItmPriceLL);
		pageMonthlyPlanningPO.CompareValueDecimalFields(extentTest, DBActlItmPriceValSD, ActlItmPriceSD);
	}

	// Customers
	@Test(description = "Weekly Trading Statement - Store, This Year Customer", priority = 13)
	public void LonglifeseafoodCustomersColumnValidation() throws Exception {
		pageWeeklyPlanningPO = new WeeklyPlanningStorePage().createPage(getDriver(), getContext(), log, status, data,
				getReport(), getReportLogger(), WeeklyPlanningStorePage.class);
		pageDailyTradingStatementGroupPO = new MonthlyPlanningGroupPage().createPage(getDriver(), getContext(), log,
				status, data, getReport(), getReportLogger(), DailyTradingStatementGroupPage.class);
		TestStatus status = getTestStatus();
		System.out.println("Entering into TestCase - Customer View");

		// THIS YEAR - CUSTOMER:
		Report_AddStep("testcase", "CUSTOMER THIS YEAR VALUE - LONGLIFE E& SEAFOOD & DELI", "", "", "");
		extentTest = report.startTest("Customer This year");

		String ThisYrActlCustomersLL = data.getText("ThisYrActlCustomersTotal");
		DBThisYrCustValLL = sql.CLRexecuteQuery(getContext(),
				ThisYrActlCustomersLL.replace("store", getContext().getStringProperty("store"))
						.replace("LYWk", getContext().getStringProperty("LYWk"))
						.replace("Week", getContext().getStringProperty("SelectFiscalWeek"))
						.replace("Division", getContext().getStringProperty("Division")).replace("Department", getContext().getStringProperty("Longlife")));
		System.out.println("DB Value: " + DBThisYrCustValLL);

		String ThisYrActlCustomersSD = data.getText("ThisYrActlCustomersTotal");
		DBThisYrCustValSD = sql.CLRexecuteQuery(getContext(),
				ThisYrActlCustomersSD.replace("store", getContext().getStringProperty("store"))
						.replace("LYWk", getContext().getStringProperty("LYWk"))
						.replace("Week", getContext().getStringProperty("SelectFiscalWeek"))
						.replace("Division", getContext().getStringProperty("Division")).replace("Department", getContext().getStringProperty("SeaFoodDeli")));
		System.out.println("DB Value: " + DBThisYrCustValSD);

		
		ThisYrCustLL = pageDailyTradingStatementGroupPO.WeekToDateUITotal(
				objWeeklyTradingStatementStoreObjects.LLTitle,
				objWeeklyTradingStatementStoreObjects.ThisYrCustomerLL);
		ThisYrCustSD = pageDailyTradingStatementGroupPO.WeekToDateUITotal(
				objWeeklyTradingStatementStoreObjects.SDTitle,
				objWeeklyTradingStatementStoreObjects.ThisYrCustomerSD);

		pageWeeklyPlanningPO.CompareValues(extentTest, DBThisYrCustValLL, ThisYrCustLL);
		pageMonthlyPlanningPO.CompareValues(extentTest, DBThisYrCustValSD, ThisYrCustSD);

		// % LAST YEAR CUSTOMER:
		Report_AddStep("testcase", "PERCENTAGE LAST YEAR CUSTOMER - LONGLIFE E& SEAFOOD & DELI", "", "", "");
		extentTest = report.startTest("Customer LY year");

		String PctLastYrCustomersLL = data.getText("PctLastYrCustomersTotal");
		DBLstYrCustValLL = sql.CLRexecuteQuery(getContext(),
				PctLastYrCustomersLL.replace("store", getContext().getStringProperty("store"))
						.replace("LYWk", getContext().getStringProperty("LYWk"))
						.replace("Week", getContext().getStringProperty("SelectFiscalWeek"))
						.replace("Division", getContext().getStringProperty("Division")).replace("Department", getContext().getStringProperty("Longlife")));
		System.out.println("DB Value: " + DBLstYrCustValLL);

		String PctLastYrCustomersSD = data.getText("PctLastYrCustomersTotal");
		DBLstYrCustValSD = sql.CLRexecuteQuery(getContext(),
				PctLastYrCustomersSD.replace("store", getContext().getStringProperty("store"))
						.replace("LYWk", getContext().getStringProperty("LYWk"))
						.replace("Week", getContext().getStringProperty("SelectFiscalWeek"))
						.replace("Division", getContext().getStringProperty("Division")).replace("Department", getContext().getStringProperty("SeaFoodDeli")));
		System.out.println("DB Value: " + DBLstYrCustValSD);

		
		LstYrCustLL = pageDailyTradingStatementGroupPO.WeekToDateUITotal(
				objWeeklyTradingStatementStoreObjects.LLTitle,
				objWeeklyTradingStatementStoreObjects.PercentageLastCustomerLL);
		LstYrCustSD = pageDailyTradingStatementGroupPO.WeekToDateUITotal(
				objWeeklyTradingStatementStoreObjects.SDTitle,
				objWeeklyTradingStatementStoreObjects.PercentageLastCustomerSD);

		pageWeeklyPlanningPO.CompareValueDecimalFields(extentTest, DBLstYrCustValLL, LstYrCustLL);
		pageMonthlyPlanningPO.CompareValueDecimalFields(extentTest, DBLstYrCustValSD, LstYrCustSD);

		// DATA PENETRATION CUSTOMER:
		Report_AddStep("testcase", "DATA PENETRATION CUSTOMER - LONGLIFE E& SEAFOOD & DELI", "", "", "");
		extentTest = report.startTest("Data Penetration");

		String DataPenetrationCustomersLL = data.getText("DataPenetrationCustomersTotal");
		DBDataPenValLL = sql.CLRexecuteQuery(getContext(),
				DataPenetrationCustomersLL.replace("store", getContext().getStringProperty("store"))
						.replace("LYWk", getContext().getStringProperty("LYWk"))
						.replace("Week", getContext().getStringProperty("SelectFiscalWeek"))
						.replace("Division", getContext().getStringProperty("Division")).replace("Department", getContext().getStringProperty("Longlife")));
		System.out.println("DB Value: " + DBDataPenValLL);

		String DataPenetrationCustomersSD = data.getText("DataPenetrationCustomersTotal");
		DBDataPenValSD = sql.CLRexecuteQuery(getContext(),
				DataPenetrationCustomersSD.replace("store", getContext().getStringProperty("store"))
						.replace("LYWk", getContext().getStringProperty("LYWk"))
						.replace("Week", getContext().getStringProperty("SelectFiscalWeek"))
						.replace("Division", getContext().getStringProperty("Division")).replace("Department", getContext().getStringProperty("SeaFoodDeli")));
		System.out.println("DB Value: " + DBDataPenValSD);

		
		DataPenLL = pageDailyTradingStatementGroupPO.WeekToDateUITotal(
				objWeeklyTradingStatementStoreObjects.LLTitle,
				objWeeklyTradingStatementStoreObjects.DeptPenetrationCustomerLL);
		DataPenSD = pageDailyTradingStatementGroupPO.WeekToDateUITotal(
				objWeeklyTradingStatementStoreObjects.SDTitle,
				objWeeklyTradingStatementStoreObjects.DeptPenetrationCustomerSD);

		pageWeeklyPlanningPO.CompareValueDecimalFields(extentTest, DBDataPenValLL, DataPenLL);
		pageMonthlyPlanningPO.CompareValueDecimalFields(extentTest, DBDataPenValSD, DataPenSD);
	}

	@Test(description = "Weekly Trading Statement - Store, Growth in SMS Hours", priority = 14)
	public void  LonglifeseafoodHoursColumnValidation() throws Exception {
		pageWeeklyPlanningPO = new WeeklyPlanningStorePage().createPage(getDriver(), getContext(), log, status, data,
				getReport(), getReportLogger(), WeeklyPlanningStorePage.class);
		pageDailyTradingStatementGroupPO = new MonthlyPlanningGroupPage().createPage(getDriver(), getContext(), log,
				status, data, getReport(), getReportLogger(), DailyTradingStatementGroupPage.class);
		TestStatus status = getTestStatus();
		System.out.println("Entering into TestCase - Hours View");

		// ACTUAL HOURS:
		Report_AddStep("testcase", "ACTUAL HOURS - LONGLIFE & SEAFOOD & DELI", "", "", "");
		extentTest = report.startTest("Actual Hours");

		String ActualHoursLL = data.getText("ActualHoursTotal");
		DBActlHrsValLL = sql.CLRexecuteQuery(getContext(),
				ActualHoursLL.replace("store", getContext().getStringProperty("store"))
						.replace("LYWk", getContext().getStringProperty("LYWk"))
						.replace("Week", getContext().getStringProperty("SelectFiscalWeek"))
						.replace("Division", getContext().getStringProperty("Division")).replace("Department", getContext().getStringProperty("Longlife")));
		System.out.println("DB Value: " + DBActlHrsValLL);

		String ActualHoursSD = data.getText("ActualHoursTotal");
		DBActlHrsValSD = sql.CLRexecuteQuery(getContext(),
				ActualHoursSD.replace("store", getContext().getStringProperty("store"))
						.replace("LYWk", getContext().getStringProperty("LYWk"))
						.replace("Week", getContext().getStringProperty("SelectFiscalWeek"))
						.replace("Division", getContext().getStringProperty("Division")).replace("Department", getContext().getStringProperty("SeaFoodDeli")));
		System.out.println("DB Value: " + DBActlHrsValLL);

		
		ActlHrsLL = pageDailyTradingStatementGroupPO.WeekToDateUITotal(
				objWeeklyTradingStatementStoreObjects.LLTitle, objWeeklyTradingStatementStoreObjects.ActualHoursLL);
		ActlHrsSD = pageDailyTradingStatementGroupPO.WeekToDateUITotal(
				objWeeklyTradingStatementStoreObjects.SDTitle, objWeeklyTradingStatementStoreObjects.ActualHoursSD);

		pageWeeklyPlanningPO.CompareValueDecimalFields(extentTest, DBActlHrsValLL, ActlHrsLL);
		pageMonthlyPlanningPO.CompareValueDecimalFields(extentTest, DBActlHrsValSD, ActlHrsSD);

		// Variance SMS:
		Report_AddStep("testcase", "VARIANCE SMS - LONGLIFE E& SEAFOOD & DELI", "", "", "");
		extentTest = report.startTest("Variance SMS");

		String VarianceSMSLL = data.getText("VarianceSMSTotal");
		DBVarSMSHrHrLL = sql.CLRexecuteQuery(getContext(),
				VarianceSMSLL.replace("store", getContext().getStringProperty("store"))
						.replace("FinWk", getContext().getStringProperty("FinWk"))
						.replace("Week", getContext().getStringProperty("SelectFiscalWeek"))
						.replace("Year", getContext().getStringProperty("Year"))
						.replace("Division", getContext().getStringProperty("Division")).replace("Department", getContext().getStringProperty("Longlife")));
		System.out.println("DB Value: " + DBVarSMSHrHrLL);

		String VarianceSMSSD = data.getText("VarianceSMSTotal");
		DBVarSMSHrHrSD = sql.CLRexecuteQuery(getContext(),
				VarianceSMSSD.replace("store", getContext().getStringProperty("store"))
						.replace("FinWk", getContext().getStringProperty("FinWk"))
						.replace("Week", getContext().getStringProperty("SelectFiscalWeek"))
						.replace("Year", getContext().getStringProperty("Year"))
						.replace("Division", getContext().getStringProperty("Division")).replace("Department", getContext().getStringProperty("SeaFoodDeli")));
		System.out.println("DB Value: " + DBVarSMSHrHrSD);

		
		VarSMSHrLL = pageDailyTradingStatementGroupPO.WeekToDateUITotal(
				objWeeklyTradingStatementStoreObjects.LLTitle, objWeeklyTradingStatementStoreObjects.VarSMSHoursLL);
		VarSMSHrSD = pageDailyTradingStatementGroupPO.WeekToDateUITotal(
				objWeeklyTradingStatementStoreObjects.SDTitle, objWeeklyTradingStatementStoreObjects.VarSMSHoursSD);

		pageWeeklyPlanningPO.CompareValueDecimalFields(extentTest, DBVarSMSHrHrLL, VarSMSHrLL);
		pageMonthlyPlanningPO.CompareValueDecimalFields(extentTest, DBVarSMSHrHrSD, VarSMSHrSD);

		// PLANNED HOURS:
		Report_AddStep("testcase", "PLANNED HOURS: - LONGLIFE & SEAFOOD & DELI", "", "", "");
		extentTest = report.startTest("Planned Hours");

		String PlannedHoursLL = data.getText("PlannedHoursTotal");
		DBPlndHrsValLL = sql.CLRexecuteQuery(getContext(),
				PlannedHoursLL.replace("store", getContext().getStringProperty("store"))
						.replace("LYWk", getContext().getStringProperty("LYWk"))
						.replace("Week", getContext().getStringProperty("SelectFiscalWeek"))
						.replace("Division", getContext().getStringProperty("Division")).replace("Department", getContext().getStringProperty("Longlife")));
		System.out.println("DB Value: " + DBPlndHrsValLL);

		String PlannedHoursSD = data.getText("PlannedHoursTotal");
		DBPlndHrsValSD = sql.CLRexecuteQuery(getContext(),
				PlannedHoursSD.replace("store", getContext().getStringProperty("store"))
						.replace("LYWk", getContext().getStringProperty("LYWk"))
						.replace("Week", getContext().getStringProperty("SelectFiscalWeek"))
						.replace("Division", getContext().getStringProperty("Division")).replace("Department", getContext().getStringProperty("SeaFoodDeli")));
		System.out.println("DB Value: " + DBPlndHrsValSD);

	
		PlndHrsLL = pageDailyTradingStatementGroupPO.WeekToDateUITotal(
				objWeeklyTradingStatementStoreObjects.LLTitle,
				objWeeklyTradingStatementStoreObjects.PlannedHoursLL);
		PlndHrsSD = pageDailyTradingStatementGroupPO.WeekToDateUITotal(
				objWeeklyTradingStatementStoreObjects.SDTitle,
				objWeeklyTradingStatementStoreObjects.PlannedHoursSD);

		pageWeeklyPlanningPO.CompareValueDecimalFields(extentTest, DBPlndHrsValLL, PlndHrsLL);
		pageMonthlyPlanningPO.CompareValueDecimalFields(extentTest, DBPlndHrsValSD, PlndHrsSD);

		// VARIANCE IN PLANNED HOURS:
		Report_AddStep("testcase", "VARIANCE IN PLANNED HOURS - LONGLIFE & SEAFOOD & DELI", "", "", "");
		extentTest = report.startTest("Variance Hours");

		String VarHoursLL = data.getText("VarHoursTotal");
		DBVarPlndHrsValLL = sql.CLRexecuteQuery(getContext(),
				VarHoursLL.replace("store", getContext().getStringProperty("store"))
						.replace("LYWk", getContext().getStringProperty("LYWk"))
						.replace("Week", getContext().getStringProperty("SelectFiscalWeek"))
						.replace("Division", getContext().getStringProperty("Division")).replace("Department", getContext().getStringProperty("Longlife")));
		System.out.println("DB Value: " + DBVarPlndHrsValLL);

		String VarHoursSD= data.getText("VarHoursTotal");
		DBVarPlndHrsValSD = sql.CLRexecuteQuery(getContext(),
				VarHoursSD.replace("store", getContext().getStringProperty("store"))
						.replace("LYWk", getContext().getStringProperty("LYWk"))
						.replace("Week", getContext().getStringProperty("SelectFiscalWeek"))
						.replace("Division", getContext().getStringProperty("Division")).replace("Department", getContext().getStringProperty("SeaFoodDeli")));
		System.out.println("DB Value: " + DBVarPlndHrsValSD);

		
		VarPlndHrsLL = pageDailyTradingStatementGroupPO.WeekToDateUITotal(
				objWeeklyTradingStatementStoreObjects.LLTitle,
				objWeeklyTradingStatementStoreObjects.VarPlannedHoursLL);
		
		VarPlndHrsSD = pageDailyTradingStatementGroupPO.WeekToDateUITotal(
				objWeeklyTradingStatementStoreObjects.SDTitle,
				objWeeklyTradingStatementStoreObjects.VarPlannedHoursSD);

		pageWeeklyPlanningPO.CompareValueDecimalFields(extentTest, DBVarPlndHrsValLL, VarPlndHrsLL);
		pageMonthlyPlanningPO.CompareValueDecimalFields(extentTest, DBVarPlndHrsValSD, VarPlndHrsSD);

		// Actual SMS:
		Report_AddStep("testcase", "ACTUAL SMS - LONGLIFE & SEAFOOD & DELI", "", "", "");
		extentTest = report.startTest("Actual SMS");

		String ActualSMSLL = data.getText("ActualSMSTotal");
		DBActlSMSHrLL = sql.CLRexecuteQuery(getContext(),
				ActualSMSLL.replace("store", getContext().getStringProperty("store"))
						.replace("FinWk", getContext().getStringProperty("FinWk"))
						.replace("Week", getContext().getStringProperty("SelectFiscalWeek"))
						.replace("Year", getContext().getStringProperty("Year"))
						.replace("Division", getContext().getStringProperty("Division")).replace("Department", getContext().getStringProperty("Longlife")));
		System.out.println("DB Value: " + DBActlSMSHrLL);

		String ActualSMSSD = data.getText("ActualSMSTotal");
		DBActlSMSHrSD = sql.CLRexecuteQuery(getContext(),
				ActualSMSSD.replace("store", getContext().getStringProperty("store"))
						.replace("FinWk", getContext().getStringProperty("FinWk"))
						.replace("Week", getContext().getStringProperty("SelectFiscalWeek"))
						.replace("Year", getContext().getStringProperty("Year"))
						.replace("Division", getContext().getStringProperty("Division")).replace("Department", getContext().getStringProperty("SeaFoodDeli")));
		System.out.println("DB Value: " + DBActlSMSHrSD);

		
		ActlSMSHrLL = pageDailyTradingStatementGroupPO.WeekToDateUITotal(
				objWeeklyTradingStatementStoreObjects.LLTitle,
				objWeeklyTradingStatementStoreObjects.ActualSMSHoursLL);
		
		ActlSMSHrSD = pageDailyTradingStatementGroupPO.WeekToDateUITotal(
				objWeeklyTradingStatementStoreObjects.SDTitle,
				objWeeklyTradingStatementStoreObjects.ActualSMSHoursSD);

		pageWeeklyPlanningPO.CompareValueDecimalFields(extentTest, DBActlSMSHrLL, ActlSMSHrLL);
		pageMonthlyPlanningPO.CompareValueDecimalFields(extentTest, DBActlSMSHrSD, ActlSMSHrSD);

		// OR % SMS:
		Report_AddStep("testcase", "OPERATIONAL RATIO - LONGLIFE & SEAFOOD & DELI", "", "", "");
		extentTest = report.startTest("OR Percentage");

		String ORPercentageLL = data.getText("ORPercentageTotal");
		DBORHrLL = sql.CLRexecuteQuery(getContext(),
				ORPercentageLL.replace("store", getContext().getStringProperty("store"))
						.replace("FinWk", getContext().getStringProperty("FinWk"))
						.replace("Week", getContext().getStringProperty("SelectFiscalWeek"))
						.replace("Year", getContext().getStringProperty("Year"))
						.replace("Division", getContext().getStringProperty("Division")).replace("Department", getContext().getStringProperty("Longlife")));
		System.out.println("DB Value: " + DBORHrLL);

		String ORPercentageSD= data.getText("ORPercentageTotal");
		DBORHrSD = sql.CLRexecuteQuery(getContext(),
				ORPercentageSD.replace("store", getContext().getStringProperty("store"))
						.replace("FinWk", getContext().getStringProperty("FinWk"))
						.replace("Week", getContext().getStringProperty("SelectFiscalWeek"))
						.replace("Year", getContext().getStringProperty("Year"))
						.replace("Division", getContext().getStringProperty("Division")).replace("Department", getContext().getStringProperty("SeaFoodDeli")));
		System.out.println("DB Value: " + DBORHrSD);

		
		ORHrLL = pageDailyTradingStatementGroupPO.WeekToDateUITotal(objWeeklyTradingStatementStoreObjects.LLTitle,
				objWeeklyTradingStatementStoreObjects.ORPercentageHoursLL);
		ORHrSD = pageDailyTradingStatementGroupPO.WeekToDateUITotal(objWeeklyTradingStatementStoreObjects.SDTitle,
				objWeeklyTradingStatementStoreObjects.ORPercentageHoursSD);

		pageWeeklyPlanningPO.CompareValueDecimalFields(extentTest, DBORHrLL, ORHrLL);
		pageMonthlyPlanningPO.CompareValueDecimalFields(extentTest, DBORHrSD, ORHrSD);
	}

	// Wages
	@Test(description = "Weekly Trading Statement - Store, Actual Wages", priority = 15)
	public void LonglifeseafoodWagesColumnValidation() throws Exception {
		pageWeeklyPlanningPO = new WeeklyPlanningStorePage().createPage(getDriver(), getContext(), log, status, data,
				getReport(), getReportLogger(), WeeklyPlanningStorePage.class);
		pageDailyTradingStatementGroupPO = new MonthlyPlanningGroupPage().createPage(getDriver(), getContext(), log,
				status, data, getReport(), getReportLogger(), DailyTradingStatementGroupPage.class);
		TestStatus status = getTestStatus();
		System.out.println("Entering into TestCase - Wages View");
		OptionToView = getContext().getStringProperty("OptionToView");

		// ACTUAL WAGE:
		Report_AddStep("testcase", "ACTUAL WAGE- LONGLIFE & SEAFOOD & DELI", "", "", "");
		extentTest = report.startTest(" Actual Wage");

		String ActualWagesLL = data.getText("ActualWagesTotal");
		DBActlWgValLL = sql.CLRexecuteQuery(getContext(),
				ActualWagesLL.replace("store", getContext().getStringProperty("store"))
						.replace("LYWk", getContext().getStringProperty("LYWk"))
						.replace("Week", getContext().getStringProperty("SelectFiscalWeek"))
						.replace("Division", getContext().getStringProperty("Division")).replace("Department", getContext().getStringProperty("Longlife")));
		System.out.println("DB Value: " + DBActlWgValLL);

		String ActualWagesSD = data.getText("ActualWagesTotal");
		DBActlWgValSD = sql.CLRexecuteQuery(getContext(),
				ActualWagesSD.replace("store", getContext().getStringProperty("store"))
						.replace("LYWk", getContext().getStringProperty("LYWk"))
						.replace("Week", getContext().getStringProperty("SelectFiscalWeek"))
						.replace("Division", getContext().getStringProperty("Division")).replace("Department", getContext().getStringProperty("SeaFoodDeli")));
		System.out.println("DB Value: " + DBActlWgValSD);

		
		ActlWgLL = pageDailyTradingStatementGroupPO.WeekToDateUITotal(objWeeklyTradingStatementStoreObjects.LLTitle,
				objWeeklyTradingStatementStoreObjects.ActualWagesLL);
		ActlWgSD = pageDailyTradingStatementGroupPO.WeekToDateUITotal(objWeeklyTradingStatementStoreObjects.SDTitle,
				objWeeklyTradingStatementStoreObjects.ActualWagesSD);

		pageWeeklyPlanningPO.CompareValues(extentTest, DBActlWgValLL, ActlWgLL);
		pageMonthlyPlanningPO.CompareValues(extentTest, DBActlWgValSD, ActlWgSD);

		// ACTUAL WAGE PERCENTAGE:
		Report_AddStep("testcase", "ACTUAL WAGE PERCENTAGE- LONGLIFE & SEAFOOD & DELI", "", "", "");
		extentTest = report.startTest("Actual Wage Percentage");

		String ActualWagePercentageLL = data.getText("ActualWagePercentageTotal");
		DBActlWgPrcntValLL = sql.CLRexecuteQuery(getContext(),
				ActualWagePercentageLL.replace("store", getContext().getStringProperty("store"))
						.replace("LYWk", getContext().getStringProperty("LYWk"))
						.replace("Week", getContext().getStringProperty("SelectFiscalWeek"))
						.replace("Division", getContext().getStringProperty("Division")).replace("Department", getContext().getStringProperty("Longlife")));
		System.out.println("DB Value: " + DBActlWgPrcntValLL);

		String ActualWagePercentageSD = data.getText("ActualWagePercentageTotal");
		DBActlWgPrcntValSD = sql.CLRexecuteQuery(getContext(),
				ActualWagePercentageSD.replace("store", getContext().getStringProperty("store"))
						.replace("LYWk", getContext().getStringProperty("LYWk"))
						.replace("Week", getContext().getStringProperty("SelectFiscalWeek"))
						.replace("Division", getContext().getStringProperty("Division")).replace("Department", getContext().getStringProperty("SeaFoodDeli")));
		System.out.println("DB Value: " + DBActlWgPrcntValSD);

		
		ActlWgPrcntLL = pageDailyTradingStatementGroupPO.WeekToDateUITotal(
				objWeeklyTradingStatementStoreObjects.LLTitle,
				objWeeklyTradingStatementStoreObjects.ActualWagesPercentageLL);
		ActlWgPrcntSD = pageDailyTradingStatementGroupPO.WeekToDateUITotal(
				objWeeklyTradingStatementStoreObjects.SDTitle,
				objWeeklyTradingStatementStoreObjects.ActualWagesPercentageSD);

		pageWeeklyPlanningPO.CompareValueDecimalFields(extentTest, DBActlWgPrcntValLL, ActlWgPrcntLL);
		pageMonthlyPlanningPO.CompareValueDecimalFields(extentTest, DBActlWgPrcntValSD, ActlWgPrcntSD);

		// BUDGET WAGE:
		if (OptionToView.contains("Budget")) {
			Report_AddStep("testcase", "ACTUAL BUDGET WAGE - LONGLIFE & SEAFOOD & DELI", "", "", "");
			extentTest = report.startTest("Budget Wage");

			String BudgetWagesLL = data.getText("BudgetWagesTotal");
			DBBdgtWgValLL = sql.CLRexecuteQuery(getContext(),
					BudgetWagesLL.replace("store", getContext().getStringProperty("store"))
							.replace("LYWk", getContext().getStringProperty("LYWk"))
							.replace("Week", getContext().getStringProperty("SelectFiscalWeek"))
							.replace("Division", getContext().getStringProperty("Division")).replace("Department", getContext().getStringProperty("Longlife")));
			System.out.println("DB Value: " + DBBdgtWgValLL);

			String BudgetWagesSD = data.getText("BudgetWagesTotal");
			DBBdgtWgValSD = sql.CLRexecuteQuery(getContext(),
					BudgetWagesSD.replace("store", getContext().getStringProperty("store"))
							.replace("LYWk", getContext().getStringProperty("LYWk"))
							.replace("Week", getContext().getStringProperty("SelectFiscalWeek"))
							.replace("Division", getContext().getStringProperty("Division")).replace("Department", getContext().getStringProperty("SeaFoodDeli")));
			System.out.println("DB Value: " + DBBdgtWgValSD);

			
			BdgtWgLL = pageDailyTradingStatementGroupPO.WeekToDateUITotal(
					objWeeklyTradingStatementStoreObjects.LLTitle,
					objWeeklyTradingStatementStoreObjects.BudgetWagesLL);
			BdgtWgSD = pageDailyTradingStatementGroupPO.WeekToDateUITotal(
					objWeeklyTradingStatementStoreObjects.SDTitle,
					objWeeklyTradingStatementStoreObjects.BudgetWagesSD);

			pageWeeklyPlanningPO.CompareValues(extentTest, DBBdgtWgValLL,BdgtWgLL);
			pageMonthlyPlanningPO.CompareValues(extentTest, DBBdgtWgValSD, BdgtWgSD);
		}
		if (OptionToView.contains("Forecast")) {
			Report_AddStep("testcase", "ACTUAL FORECAST WAGE - WEEK WISE DATA", "", "", "");
			extentTest = report.startTest("Forecast Wage");

			String ForecastWagesLL = data.getText("ForecastWagesTotal");
			DBBdgtWgValLL = sql.CLRexecuteQuery(getContext(),
					ForecastWagesLL.replace("store", getContext().getStringProperty("store"))
							.replace("LYWk", getContext().getStringProperty("LYWk"))
							.replace("Week", getContext().getStringProperty("SelectFiscalWeek"))
							.replace("Division", getContext().getStringProperty("Division")).replace("Department", getContext().getStringProperty("Longlife")));
			System.out.println("DB Value: " + DBBdgtWgValLL);

			String ForecastWagesSD = data.getText("ForecastWagesTotal");
			DBBdgtWgValSD = sql.CLRexecuteQuery(getContext(),
					ForecastWagesSD.replace("store", getContext().getStringProperty("store"))
							.replace("LYWk", getContext().getStringProperty("LYWk"))
							.replace("Week", getContext().getStringProperty("SelectFiscalWeek"))
							.replace("Division", getContext().getStringProperty("Division")).replace("Department", getContext().getStringProperty("SeaFoodDeli")));
			System.out.println("DB Value: " + DBBdgtWgValSD);

			
			BdgtWgLL = pageDailyTradingStatementGroupPO.WeekToDateUITotal(
					objWeeklyTradingStatementStoreObjects.LLTitle,
					objWeeklyTradingStatementStoreObjects.BudgetWagesLL);
			BdgtWgSD= pageDailyTradingStatementGroupPO.WeekToDateUITotal(
					objWeeklyTradingStatementStoreObjects.SDTitle,
					objWeeklyTradingStatementStoreObjects.BudgetWagesSD);


			pageWeeklyPlanningPO.CompareValues(extentTest, DBBdgtWgValLL, BdgtWgLL);
			pageMonthlyPlanningPO.CompareValues(extentTest, DBBdgtWgValSD, BdgtWgSD);
		}

		// BUDGET WAGE PERCENTAGE:
		if (OptionToView.contains("Budget")) {
			Report_AddStep("testcase", "BUDGET WAGE PERCENTAGE- LONGLIFE & SEAFOOD & DELI", "", "", "");
			extentTest = report.startTest("Budget Wage percentage");

			String BudgetWagePercentageLL = data.getText("BudgetWagePercentageTotal");
			DBBdgtWgPrcntValLL = sql.CLRexecuteQuery(getContext(),
					BudgetWagePercentageLL.replace("store", getContext().getStringProperty("store"))
							.replace("LYWk", getContext().getStringProperty("LYWk"))
							.replace("Week", getContext().getStringProperty("SelectFiscalWeek"))
							.replace("Division", getContext().getStringProperty("Division")).replace("Department", getContext().getStringProperty("Longlife")));
			System.out.println("DB Value: " + DBBdgtWgPrcntValLL);

			String BudgetWagePercentageSD = data.getText("BudgetWagePercentageTotal");
			DBBdgtWgPrcntValSD = sql.CLRexecuteQuery(getContext(),
					BudgetWagePercentageSD.replace("store", getContext().getStringProperty("store"))
							.replace("LYWk", getContext().getStringProperty("LYWk"))
							.replace("Week", getContext().getStringProperty("SelectFiscalWeek"))
							.replace("Division", getContext().getStringProperty("Division")).replace("Department", getContext().getStringProperty("SeaFoodDeli")));
			System.out.println("DB Value: " + DBBdgtWgPrcntValSD);

			
			BdgtWgPrcntLL = pageDailyTradingStatementGroupPO.WeekToDateUITotal(
					objWeeklyTradingStatementStoreObjects.LLTitle,
					objWeeklyTradingStatementStoreObjects.BudgetWagesPercentageLL);
			BdgtWgPrcntSD = pageDailyTradingStatementGroupPO.WeekToDateUITotal(
					objWeeklyTradingStatementStoreObjects.SDTitle,
					objWeeklyTradingStatementStoreObjects.BudgetWagesPercentageSD);

			pageWeeklyPlanningPO.CompareValueDecimalFields(extentTest, DBBdgtWgPrcntValLL, BdgtWgPrcntLL);
			pageMonthlyPlanningPO.CompareValueDecimalFields(extentTest, DBBdgtWgPrcntValSD, BdgtWgPrcntSD);
		}
		if (OptionToView.contains("Forecast")) {
			Report_AddStep("testcase", "FORECAST WAGE PERCENTAGE-LONGLIFE & SEAFOOD & DELI", "", "", "");
			extentTest = report.startTest("Forecast Wage percentage");

			String ForecastWagePercentageLL = data.getText("ForecastWagePercentageTotal");
			DBBdgtWgPrcntValLL = sql.CLRexecuteQuery(getContext(),
					ForecastWagePercentageLL.replace("store", getContext().getStringProperty("store"))
							.replace("LYWk", getContext().getStringProperty("LYWk"))
							.replace("Week", getContext().getStringProperty("SelectFiscalWeek"))
							.replace("Division", getContext().getStringProperty("Division")).replace("Department", getContext().getStringProperty("Longlife")));
			System.out.println("DB Value: " + DBBdgtWgPrcntValLL);

			String ForecastWagePercentageSD = data.getText("ForecastWagePercentageTotal");
			DBBdgtWgPrcntValSD = sql.CLRexecuteQuery(getContext(),
					ForecastWagePercentageSD.replace("store", getContext().getStringProperty("store"))
							.replace("LYWk", getContext().getStringProperty("LYWk"))
							.replace("Week", getContext().getStringProperty("SelectFiscalWeek"))
							.replace("Division", getContext().getStringProperty("Division")).replace("Department", getContext().getStringProperty("SeaFoodDeli")));
			System.out.println("DB Value: " + DBBdgtWgPrcntValSD);

			
			BdgtWgPrcntLL = pageDailyTradingStatementGroupPO.WeekToDateUITotal(
					objWeeklyTradingStatementStoreObjects.LLTitle,
					objWeeklyTradingStatementStoreObjects.BudgetWagesPercentageLL);
			BdgtWgPrcntSD = pageDailyTradingStatementGroupPO.WeekToDateUITotal(
					objWeeklyTradingStatementStoreObjects.SDTitle,
					objWeeklyTradingStatementStoreObjects.BudgetWagesPercentageSD);

			pageWeeklyPlanningPO.CompareValueDecimalFields(extentTest, DBBdgtWgPrcntValLL, BdgtWgPrcntLL);
			pageMonthlyPlanningPO.CompareValueDecimalFields(extentTest, DBBdgtWgPrcntValSD, BdgtWgPrcntSD);
		}

		// PLANNED CPH:
		Report_AddStep("testcase", "PLANNED CPH - LONGLIFE & SEAFOOD & DELI", "", "", "");
		extentTest = report.startTest("Planned CPH");

		String PlannedCPHLL = data.getText("PlannedCPHTotal");
		DBPlndCPHValLL = sql.CLRexecuteQuery(getContext(),
				PlannedCPHLL.replace("store", getContext().getStringProperty("store"))
						.replace("LYWk", getContext().getStringProperty("LYWk"))
						.replace("Week", getContext().getStringProperty("SelectFiscalWeek"))
						.replace("Division", getContext().getStringProperty("Division")).replace("Department", getContext().getStringProperty("Longlife")));
		System.out.println("DB Value: " + DBPlndCPHValLL);

		String PlannedCPHSD = data.getText("PlannedCPHTotal");
		DBPlndCPHValSD = sql.CLRexecuteQuery(getContext(),
				PlannedCPHSD.replace("store", getContext().getStringProperty("store"))
						.replace("LYWk", getContext().getStringProperty("LYWk"))
						.replace("Week", getContext().getStringProperty("SelectFiscalWeek"))
						.replace("Division", getContext().getStringProperty("Division")).replace("Department", getContext().getStringProperty("SeaFoodDeli")));
		System.out.println("DB Value: " + DBPlndCPHValSD);

		
		PlndCPHLL = pageDailyTradingStatementGroupPO.WeekToDateUITotal(
				objWeeklyTradingStatementStoreObjects.LLTitle,
				objWeeklyTradingStatementStoreObjects.PlannedCPHWagesLL);
		PlndCPHSD = pageDailyTradingStatementGroupPO.WeekToDateUITotal(
				objWeeklyTradingStatementStoreObjects.SDTitle,
				objWeeklyTradingStatementStoreObjects.PlannedCPHWagesSD);


		pageWeeklyPlanningPO.CompareValueDecimalFields(extentTest, DBPlndCPHValLL, PlndCPHLL);
		pageMonthlyPlanningPO.CompareValueDecimalFields(extentTest, DBPlndCPHValSD, PlndCPHSD);

		// PLANNED WAGE:
		Report_AddStep("testcase", "PLANNED WAGE - LONGLIFE & SEAFOOD & DELI", "", "", "");
		extentTest = report.startTest("Planned Wages");

		String PlannedWagesLL = data.getText("PlannedWagesTotal");
		DBPlndWgValLL = sql.CLRexecuteQuery(getContext(),
				PlannedWagesLL.replace("store", getContext().getStringProperty("store"))
						.replace("LYWk", getContext().getStringProperty("LYWk"))
						.replace("Week", getContext().getStringProperty("SelectFiscalWeek"))
						.replace("Division", getContext().getStringProperty("Division")).replace("Department", getContext().getStringProperty("Longlife")));
		System.out.println("DB Value: " + DBPlndWgValLL);

		String PlannedWagesSD = data.getText("PlannedWagesTotal");
		DBPlndWgValSD = sql.CLRexecuteQuery(getContext(),
				PlannedWagesSD.replace("store", getContext().getStringProperty("store"))
						.replace("LYWk", getContext().getStringProperty("LYWk"))
						.replace("Week", getContext().getStringProperty("SelectFiscalWeek"))
						.replace("Division", getContext().getStringProperty("Division")).replace("Department", getContext().getStringProperty("SeaFoodDeli")));
		System.out.println("DB Value: " + DBPlndWgValSD);

		
		PlndWgLL = pageDailyTradingStatementGroupPO.WeekToDateUITotal(objWeeklyTradingStatementStoreObjects.LLTitle,
				objWeeklyTradingStatementStoreObjects.PlannedWagesLL);
		PlndWgSD = pageDailyTradingStatementGroupPO.WeekToDateUITotal(objWeeklyTradingStatementStoreObjects.SDTitle,
				objWeeklyTradingStatementStoreObjects.PlannedWagesSD);

		pageWeeklyPlanningPO.CompareValues(extentTest, DBPlndWgValLL, PlndWgLL);
		pageMonthlyPlanningPO.CompareValues(extentTest, DBPlndWgValSD, PlndWgSD);

		// PLANNED WAGE PERCENTAGE:
		Report_AddStep("testcase", "PLANNED WAGE PERCENTAGE - LONGLIFE & SEAFOOD & DELI", "", "", "");
		extentTest = report.startTest("Planned Wage Percentage");

		String PlannedWagesPercentageLL = data.getText("PlannedWagesPercentageTotal");
		DBPlndWgPrcntValLL = sql.CLRexecuteQuery(getContext(),
				PlannedWagesPercentageLL.replace("store", getContext().getStringProperty("store"))
						.replace("LYWk", getContext().getStringProperty("LYWk"))
						.replace("Week", getContext().getStringProperty("SelectFiscalWeek"))
						.replace("Division", getContext().getStringProperty("Division")).replace("Department", getContext().getStringProperty("Longlife")));
		System.out.println("DB Value: " + DBPlndWgPrcntValLL);

		String PlannedWagesPercentageSD = data.getText("PlannedWagesPercentageTotal");
		DBPlndWgPrcntValSD = sql.CLRexecuteQuery(getContext(),
				PlannedWagesPercentageSD.replace("store", getContext().getStringProperty("store"))
						.replace("LYWk", getContext().getStringProperty("LYWk"))
						.replace("Week", getContext().getStringProperty("SelectFiscalWeek"))
						.replace("Division", getContext().getStringProperty("Division")).replace("Department", getContext().getStringProperty("SeaFoodDeli")));
		System.out.println("DB Value: " + DBPlndWgPrcntValSD);


		PlndWgPrcntLL = pageDailyTradingStatementGroupPO.WeekToDateUITotal(
				objWeeklyTradingStatementStoreObjects.LLTitle,
				objWeeklyTradingStatementStoreObjects.PlannedWagesPercentageLL);
		PlndWgPrcntSD = pageDailyTradingStatementGroupPO.WeekToDateUITotal(
				objWeeklyTradingStatementStoreObjects.SDTitle,
				objWeeklyTradingStatementStoreObjects.PlannedWagesPercentageSD);

		pageWeeklyPlanningPO.CompareValueDecimalFields(extentTest, DBPlndWgPrcntValLL, PlndWgPrcntLL);
		pageMonthlyPlanningPO.CompareValueDecimalFields(extentTest, DBPlndWgPrcntValSD, PlndWgPrcntSD);

		// CPH Wages:
		Report_AddStep("testcase", "CPH WAGES -LONGLIFE & SEAFOOD & DELI", "", "", "");
		extentTest = report.startTest("CPH Wage");

		String ActualCPHLL = data.getText("ActualCPHTotal");
		DBCPHWgValLL = sql.CLRexecuteQuery(getContext(),
				ActualCPHLL.replace("store", getContext().getStringProperty("store"))
						.replace("LYWk", getContext().getStringProperty("LYWk"))
						.replace("Week", getContext().getStringProperty("SelectFiscalWeek"))
						.replace("Division", getContext().getStringProperty("Division")).replace("Department", getContext().getStringProperty("Longlife")));
		System.out.println("DB Value: " + DBCPHWgValLL);

		String ActualCPHSD = data.getText("ActualCPHTotal");
		DBCPHWgValSD = sql.CLRexecuteQuery(getContext(),
				ActualCPHSD.replace("store", getContext().getStringProperty("store"))
						.replace("LYWk", getContext().getStringProperty("LYWk"))
						.replace("Week", getContext().getStringProperty("SelectFiscalWeek"))
						.replace("Division", getContext().getStringProperty("Division")).replace("Department", getContext().getStringProperty("SeaFoodDeli")));
		System.out.println("DB Value: " + DBCPHWgValSD);

		
		CPHWgLL = pageDailyTradingStatementGroupPO.WeekToDateUITotal(objWeeklyTradingStatementStoreObjects.LLTitle,
				objWeeklyTradingStatementStoreObjects.CPHWagesLL);
		CPHWgSD = pageDailyTradingStatementGroupPO.WeekToDateUITotal(objWeeklyTradingStatementStoreObjects.SDTitle,
				objWeeklyTradingStatementStoreObjects.CPHWagesSD);

		pageWeeklyPlanningPO.CompareValueDecimalFields(extentTest, DBCPHWgValLL, CPHWgLL);
		pageMonthlyPlanningPO.CompareValueDecimalFields(extentTest, DBCPHWgValSD, CPHWgSD);
		
		//Allowed Wages Validation
		
				if (OptionToView.contains("Budget")) {
				Report_AddStep("testcase", "Allowed Wages Validation Longlife &Seafood  & Deli", "", "", "");
				extentTest = report.startTest("Allowed Wage");
				System.out.println("Allowedwages");
				String AllowedWagesLL = data.getText("AllowedWagesTotal");
				DBAllowedWgValLL = sql.CLRexecuteQuery(getContext(),
						AllowedWagesLL.replace("store", getContext().getStringProperty("store"))
								.replace("LYWk", getContext().getStringProperty("LYWk"))
								.replace("Week", getContext().getStringProperty("SelectFiscalWeek")).replace("Department", getContext().getStringProperty("Longlife"))
								.replace("Division", getContext().getStringProperty("Division")).replace("Region", getContext().getStringProperty("Region")));
				System.out.println("DB Value: " + DBAllowedWgValLL);

				String AllowedWagesSD = data.getText("AllowedWagesTotal");
				DBAllowedWgValSD = sql.CLRexecuteQuery(getContext(),
						AllowedWagesSD.replace("store", getContext().getStringProperty("store"))
								.replace("LYWk", getContext().getStringProperty("LYWk"))
								.replace("Week", getContext().getStringProperty("SelectFiscalWeek")).replace("Department", getContext().getStringProperty("SeaFoodDeli"))
								.replace("Division", getContext().getStringProperty("Division")).replace("Region", getContext().getStringProperty("Region")));
				System.out.println("DB Value: " + DBAllowedWgValSD);

				
				AllowedWgLL = pageDailyTradingStatementGroupPO.WeekToDateUITotal(objWeeklyTradingStatementStoreObjects.LLTitle,
						objWeeklyTradingStatementStoreObjects.AllowedWagesLL);
				AllowedWgSD = pageDailyTradingStatementGroupPO.WeekToDateUITotal(objWeeklyTradingStatementStoreObjects.SDTitle,
						objWeeklyTradingStatementStoreObjects.AllowedWagesSD);

				pageWeeklyPlanningPO.CompareValues(extentTest, DBAllowedWgValLL, AllowedWgLL);
				pageMonthlyPlanningPO.CompareValues(extentTest, DBAllowedWgValSD, AllowedWgSD);
				
			
			}
	}
	@Test(priority = 16)
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
			data.loadTestDataXML(System.getProperty("user.dir") + "\\TestData\\WLP\\WeeklyTradingStatementStore.xml");
			customreport.createExtentReport();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

}