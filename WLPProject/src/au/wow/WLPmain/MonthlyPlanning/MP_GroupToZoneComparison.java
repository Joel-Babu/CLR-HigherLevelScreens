package au.wow.WLPmain.MonthlyPlanning;

import java.awt.AWTException;

import org.testng.ITestContext;
import org.testng.annotations.*;

import java.io.File;
import java.io.IOException;
import java.lang.reflect.Method;
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
import au.wow.WLPmain.objects.MonthlyPlanningGroupObjects;
import au.wow.WLPmain.objects.MonthlyPlanningStoreObjects;
import au.wow.WLPmain.objects.WeeklyPlanningStoreObjects;
import au.wow.WLPmain.objects.WeeklyTradingStatementStoreObjects;
import au.wow.WLPmain.pages.CommonFunctions;
import au.wow.WLPmain.pages.DBComparision;
import au.wow.WLPmain.pages.DailyPlanningMainPage;
import au.wow.WLPmain.pages.DailyTradingStatementGroupPage;
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

public class MP_GroupToZoneComparison extends TestBase {

	protected Logger log = LogManager.getLogger(MP_GroupToZoneComparison.class);
	TestStatus status;
	ExtentReportsScreenshotCode ScreenShotUtil;
	SQLWrapper sql = new SQLWrapper(log);
	SQLWrapper sql1 = new SQLWrapper(log);
	WoWLoginPage loginPage;
	CommonRepository CommonFunc;
	DailyTradingStatementGroupPage pageDailyTradingStatementGroupPO;
	WeeklyTradingStatementStoreObjects objWeeklyTradingStatementStoreObjects;
	WeeklyPlanningStoreObjects objWeeklyPlanningStoreObjects;
	MonthlyPlanningGroupObjects objMonthlyPlanningGroupObjects;
	MonthlyPlanningStoreObjects objMonthlyPlanningStoreObjects;
	MonthlyPlanningGroupPage pageMonthlyPlanningGroupPO;
	DBComparision pageDBComparisionPO;
	WeeklyPlanningStorePage pageWeeklyPlanningPO;
	MonthlyPlanningStorePage pageMonthlyPlanningPO;
	BasePage BaseObj;
	CommonFunctions common;
	StoreProperties storeprop;
	List<List<Object>> TotalValue, IndividualValue;
	String OptionToView, Name;

	public MP_GroupToZoneComparison() {
		super.log = log;
	}

	@Test(priority = 0)
	public void LogintoCLRApplication() throws Exception {
		loginPage = new WoWLoginPage().createPage(getDriver(), getContext(), log, status, data, report, extentTest,
				WoWLoginPage.class);
		pageWeeklyPlanningPO = loginPage.navToWeeklyPlanningStorePg(getContext().getStringProperty("store"));
	}

	@Test(description = "Verify Group Total Value and Respective Group Value in Zone Screen - Sales History", priority = 1)
	public void SalesHistoryFirstMonth() throws Exception {
		pageWeeklyPlanningPO = new WeeklyPlanningStorePage().createPage(getDriver(), getContext(), log, status, data,
				getReport(), getReportLogger(), WeeklyPlanningStorePage.class);
		TestStatus status = getTestStatus();
		System.out.println("Welcome " + getContext().getStringProperty("username"));
		System.out.println("Entering into TestCase");
		pageWeeklyPlanningPO.SelectWeekFromDropdown(objWeeklyPlanningStoreObjects, extentTest,
				getContext().getStringProperty("SelectFiscalWeek"));

		pageWeeklyPlanningPO.SelectHistoryDropdownToView(objWeeklyPlanningStoreObjects, extentTest, "Sales");
		String Month = pageWeeklyPlanningPO.MonthName(objWeeklyPlanningStoreObjects, extentTest,
				objMonthlyPlanningStoreObjects.FirstMonthNm);

		String SalesHistoryTotal = data.getText("SalesHistoryTotal");
		List<List<Object>> SalesTotalValue = sql.executeQuery(getContext(),
				SalesHistoryTotal.replace("Region", getContext().getStringProperty("Region"))
						.replace("Area", getContext().getStringProperty("Area"))
						.replace("Zone", getContext().getStringProperty("Zone")).replace("Month", Month));
		System.out.println("DB Value: " + SalesTotalValue);

		String SalesHistory = data1.getText("SalesHistory");
		List<List<Object>> SalesIndividualValue = sql1.executeQuery(getContext(),
				SalesHistory.replace("Region", getContext().getStringProperty("Region"))
						.replace("Zone", getContext().getStringProperty("Zone")).replace("Month", Month));
		System.out.println("DB Total: " + SalesIndividualValue);

		pageDBComparisionPO.CompareValues(extentTest, SalesTotalValue, SalesIndividualValue, "Sales");
	}

	@Test(description = "Verify Group Total Value and Respective Group Value in Zone Screen - Wages History", priority = 2)
	public void WagesHistoryFirsthMonth() throws Exception {
		pageWeeklyPlanningPO = new WeeklyPlanningStorePage().createPage(getDriver(), getContext(), log, status, data,
				getReport(), getReportLogger(), WeeklyPlanningStorePage.class);
		TestStatus status = getTestStatus();
		pageWeeklyPlanningPO.SelectHistoryDropdownToView(objWeeklyPlanningStoreObjects, extentTest, "Wages");
		String Month = pageWeeklyPlanningPO.MonthName(objWeeklyPlanningStoreObjects, extentTest,
				objMonthlyPlanningStoreObjects.FirstMonthNm);

		String WagesHistoryTotal = data.getText("WagesHistoryTotal");
		TotalValue = sql.executeQuery(getContext(),
				WagesHistoryTotal.replace("Region", getContext().getStringProperty("Region"))
						.replace("Area", getContext().getStringProperty("Area"))
						.replace("Zone", getContext().getStringProperty("Zone")).replace("Month", Month));
		System.out.println("DB Value: " + TotalValue);

		String WagesHistory = data1.getText("WagesHistory");
		IndividualValue = sql1.executeQuery(getContext(),
				WagesHistory.replace("Region", getContext().getStringProperty("Region"))
						.replace("Zone", getContext().getStringProperty("Zone")).replace("Month", Month));
		System.out.println("DB Total: " + IndividualValue);

		pageDBComparisionPO.CompareValues(extentTest, TotalValue, IndividualValue, "Wages");
	}

	@Test(description = "Verify Group Total Value and Respective Group Value in Zone Screen - Items History", priority = 3)
	public void ItemsHistoryFirsthMonth() throws Exception {
		pageWeeklyPlanningPO = new WeeklyPlanningStorePage().createPage(getDriver(), getContext(), log, status, data,
				getReport(), getReportLogger(), WeeklyPlanningStorePage.class);
		TestStatus status = getTestStatus();
		pageWeeklyPlanningPO.SelectHistoryDropdownToView(objWeeklyPlanningStoreObjects, extentTest, "Items");
		String Month = pageWeeklyPlanningPO.MonthName(objWeeklyPlanningStoreObjects, extentTest,
				objMonthlyPlanningStoreObjects.FirstMonthNm);

		String ItemsHistoryTotal = data.getText("ItemsHistoryTotal");
		TotalValue = sql.executeQuery(getContext(),
				ItemsHistoryTotal.replace("Region", getContext().getStringProperty("Region"))
						.replace("Area", getContext().getStringProperty("Area"))
						.replace("Zone", getContext().getStringProperty("Zone")).replace("Month", Month));
		System.out.println("DB Value: " + TotalValue);

		String ItemsHistory = data1.getText("ItemsHistory");
		IndividualValue = sql1.executeQuery(getContext(),
				ItemsHistory.replace("Region", getContext().getStringProperty("Region"))
						.replace("Zone", getContext().getStringProperty("Zone")).replace("Month", Month));
		System.out.println("DB Total: " + IndividualValue);

		pageDBComparisionPO.CompareValues(extentTest, TotalValue, IndividualValue, "Items");
	}

	@Test(description = "Verify Group Total Value and Respective Group Value in Zone Screen - Customers History", priority = 4)
	public void CustomersHistoryFirsthMonth() throws Exception {
		pageWeeklyPlanningPO = new WeeklyPlanningStorePage().createPage(getDriver(), getContext(), log, status, data,
				getReport(), getReportLogger(), WeeklyPlanningStorePage.class);
		TestStatus status = getTestStatus();
		pageWeeklyPlanningPO.SelectHistoryDropdownToView(objWeeklyPlanningStoreObjects, extentTest, "Customers");
		String Month = pageWeeklyPlanningPO.MonthName(objWeeklyPlanningStoreObjects, extentTest,
				objMonthlyPlanningStoreObjects.FirstMonthNm);

		String CustomerHistoryTotal = data.getText("CustomerHistoryTotal");
		TotalValue = sql.executeQuery(getContext(),
				CustomerHistoryTotal.replace("Region", getContext().getStringProperty("Region"))
						.replace("Area", getContext().getStringProperty("Area"))
						.replace("Zone", getContext().getStringProperty("Zone")).replace("Month", Month));
		System.out.println("DB Value: " + TotalValue);

		String CustomerHistory = data1.getText("CustomerHistory");
		IndividualValue = sql1.executeQuery(getContext(),
				CustomerHistory.replace("Region", getContext().getStringProperty("Region"))
						.replace("Zone", getContext().getStringProperty("Zone")).replace("Month", Month));
		System.out.println("DB Total: " + IndividualValue);

		pageDBComparisionPO.CompareValues(extentTest, TotalValue, IndividualValue, "Customers");
	}

	@Test(description = "Verify Group Total Value and Respective Group Value in Zone Screen - Paid Hours History", priority = 5)
	public void PaidHoursHistoryFirsthMonth() throws Exception {
		pageWeeklyPlanningPO = new WeeklyPlanningStorePage().createPage(getDriver(), getContext(), log, status, data,
				getReport(), getReportLogger(), WeeklyPlanningStorePage.class);
		TestStatus status = getTestStatus();
		pageWeeklyPlanningPO.SelectHistoryDropdownToView(objWeeklyPlanningStoreObjects, extentTest, "Paid Hours");
		String Month = pageWeeklyPlanningPO.MonthName(objWeeklyPlanningStoreObjects, extentTest,
				objMonthlyPlanningStoreObjects.FirstMonthNm);

		String PaidHoursHistoryTotal = data.getText("PaidHoursHistoryTotal");
		TotalValue = sql.executeQuery(getContext(),
				PaidHoursHistoryTotal.replace("Region", getContext().getStringProperty("Region"))
						.replace("Area", getContext().getStringProperty("Area"))
						.replace("Zone", getContext().getStringProperty("Zone")).replace("Month", Month));
		System.out.println("DB Value: " + TotalValue);

		String PaidHoursHistory = data1.getText("PaidHoursHistory");
		IndividualValue = sql1.executeQuery(getContext(),
				PaidHoursHistory.replace("Region", getContext().getStringProperty("Region"))
						.replace("Zone", getContext().getStringProperty("Zone")).replace("Month", Month));
		System.out.println("DB Total: " + IndividualValue);

		pageDBComparisionPO.CompareValues(extentTest, TotalValue, IndividualValue, "Paid Hours");
	}

	@Test(description = "Verify Group Total Value and Respective Group Value in Zone Screen - CPH History", priority = 6)
	public void CPHFirsthMonth() throws Exception {
		pageWeeklyPlanningPO = new WeeklyPlanningStorePage().createPage(getDriver(), getContext(), log, status, data,
				getReport(), getReportLogger(), WeeklyPlanningStorePage.class);
		TestStatus status = getTestStatus();
		pageWeeklyPlanningPO.SelectHistoryDropdownToView(objWeeklyPlanningStoreObjects, extentTest, "CPH");
		String Month = pageWeeklyPlanningPO.MonthName(objWeeklyPlanningStoreObjects, extentTest,
				objMonthlyPlanningStoreObjects.FirstMonthNm);

		String CPHHistoryTotal = data.getText("CPHHistoryTotal");
		TotalValue = sql.executeQuery(getContext(),
				CPHHistoryTotal.replace("Region", getContext().getStringProperty("Region"))
						.replace("Area", getContext().getStringProperty("Area"))
						.replace("Zone", getContext().getStringProperty("Zone")).replace("Month", Month));
		System.out.println("DB Value: " + TotalValue);

		String CPHHistory = data1.getText("CPHHistory");
		IndividualValue = sql1.executeQuery(getContext(),
				CPHHistory.replace("Region", getContext().getStringProperty("Region"))
						.replace("Zone", getContext().getStringProperty("Zone")).replace("Month", Month));
		System.out.println("DB Total: " + IndividualValue);

		pageDBComparisionPO.CompareValues(extentTest, TotalValue, IndividualValue, "CPH");
	}

	@Test(description = "Verify Group Total Value and Respective Group Value in Zone Screen - Wages % History", priority = 7)
	public void WagePercentageHistoryFirsthMonth() throws Exception {
		pageWeeklyPlanningPO = new WeeklyPlanningStorePage().createPage(getDriver(), getContext(), log, status, data,
				getReport(), getReportLogger(), WeeklyPlanningStorePage.class);
		TestStatus status = getTestStatus();
		pageWeeklyPlanningPO.SelectHistoryDropdownToView(objWeeklyPlanningStoreObjects, extentTest, "Wage %");
		String Month = pageWeeklyPlanningPO.MonthName(objWeeklyPlanningStoreObjects, extentTest,
				objMonthlyPlanningStoreObjects.FirstMonthNm);

		String WagePercentageHistoryTotal = data.getText("WagePercentageHistoryTotal");
		TotalValue = sql.executeQuery(getContext(),
				WagePercentageHistoryTotal.replace("Region", getContext().getStringProperty("Region"))
						.replace("Area", getContext().getStringProperty("Area"))
						.replace("Zone", getContext().getStringProperty("Zone")).replace("Month", Month));
		System.out.println("DB Value: " + TotalValue);

		String WagePercentageHistory = data1.getText("WagePercentageHistory");
		IndividualValue = sql1.executeQuery(getContext(),
				WagePercentageHistory.replace("Region", getContext().getStringProperty("Region"))
						.replace("Zone", getContext().getStringProperty("Zone")).replace("Month", Month));
		System.out.println("DB Total: " + IndividualValue);

		pageDBComparisionPO.CompareValues(extentTest, TotalValue, IndividualValue, "Wages %");
	}

	@Test(description = "Verify Group Total Value and Respective Group Value in Zone Screen - Item Price History", priority = 8)
	public void ItemPriceHistoryFirsthMonth() throws Exception {
		pageWeeklyPlanningPO = new WeeklyPlanningStorePage().createPage(getDriver(), getContext(), log, status, data,
				getReport(), getReportLogger(), WeeklyPlanningStorePage.class);
		TestStatus status = getTestStatus();
		pageWeeklyPlanningPO.SelectHistoryDropdownToView(objWeeklyPlanningStoreObjects, extentTest, "Item Price");
		String Month = pageWeeklyPlanningPO.MonthName(objWeeklyPlanningStoreObjects, extentTest,
				objMonthlyPlanningStoreObjects.FirstMonthNm);

		String ItemPriceHistoryTotal = data.getText("ItemPriceHistoryTotal");
		TotalValue = sql.executeQuery(getContext(),
				ItemPriceHistoryTotal.replace("Region", getContext().getStringProperty("Region"))
						.replace("Area", getContext().getStringProperty("Area"))
						.replace("Zone", getContext().getStringProperty("Zone")).replace("Month", Month));
		System.out.println("DB Value: " + TotalValue);

		String ItemPriceHistory = data1.getText("ItemPriceHistory");
		IndividualValue = sql1.executeQuery(getContext(),
				ItemPriceHistory.replace("Region", getContext().getStringProperty("Region"))
						.replace("Zone", getContext().getStringProperty("Zone")).replace("Month", Month));
		System.out.println("DB Total: " + IndividualValue);

		pageDBComparisionPO.CompareValues(extentTest, TotalValue, IndividualValue, "Item Price");
	}

	@Test(description = "Verify Group Total Value and Respective Group Value in Zone Screen - BudgetSales", priority = 9)
	public void BudgetSales_CurrentMonth() throws Exception {
		pageWeeklyPlanningPO = new WeeklyPlanningStorePage().createPage(getDriver(), getContext(), log, status, data,
				getReport(), getReportLogger(), WeeklyPlanningStorePage.class);
		TestStatus status = getTestStatus();
		pageWeeklyPlanningPO.SelectHistoryDropdownToView(objWeeklyPlanningStoreObjects, extentTest, "Sales");
		OptionToView = getContext().getStringProperty("OptionToView");
		if (OptionToView.contains("Budget"))
			Name = pageWeeklyPlanningPO.MonthName(objWeeklyPlanningStoreObjects, extentTest,
					objMonthlyPlanningStoreObjects.CurrentMonth);
		if (OptionToView.contains("Forecast"))
			Name = pageWeeklyPlanningPO.MonthName(objWeeklyPlanningStoreObjects, extentTest,
					objMonthlyPlanningStoreObjects.ForecastCurrentMonth);
		String MonthName = Name.replace("Total", "").trim();
		String Month = MonthName.concat(getContext().getStringProperty("Month"));

		if (OptionToView.contains("Budget")) {
			String BudgetSalesTotal = data.getText("BudgetSalesTotal");
			TotalValue = sql.executeQuery(getContext(),
					BudgetSalesTotal.replace("Region", getContext().getStringProperty("Region"))
							.replace("Area", getContext().getStringProperty("Area"))
							.replace("Zone", getContext().getStringProperty("Zone")).replace("Month", Month));
			System.out.println("DB Value: " + TotalValue);

			String BudgetSales = data1.getText("BudgetSales");
			IndividualValue = sql1.executeQuery(getContext(),
					BudgetSales.replace("Region", getContext().getStringProperty("Region"))
							.replace("Zone", getContext().getStringProperty("Zone")).replace("Month", Month));
			System.out.println("DB Total: " + IndividualValue);

			pageDBComparisionPO.CompareValues(extentTest, TotalValue, IndividualValue, "Budget Sales");
		}

		if (OptionToView.contains("Forecast")) {
			String ForecastSalesTotal = data.getText("ForecastSalesTotal");
			TotalValue = sql.executeQuery(getContext(),
					ForecastSalesTotal.replace("Region", getContext().getStringProperty("Region"))
							.replace("Area", getContext().getStringProperty("Area"))
							.replace("Zone", getContext().getStringProperty("Zone")).replace("Month", Month));
			System.out.println("DB Value: " + TotalValue);

			String ForecastSales = data1.getText("ForecastSales");
			IndividualValue = sql1.executeQuery(getContext(),
					ForecastSales.replace("Region", getContext().getStringProperty("Region"))
							.replace("Zone", getContext().getStringProperty("Zone")).replace("Month", Month));
			System.out.println("DB Total: " + IndividualValue);

			pageDBComparisionPO.CompareValues(extentTest, TotalValue, IndividualValue, "Forecast Sales");
		}
	}

	@Test(description = "Verify Group Total Value and Respective Group Value in Zone Screen - GeneratedSales", priority = 10)
	public void GeneratedSales_CurrentMonth() throws Exception {
		pageWeeklyPlanningPO = new WeeklyPlanningStorePage().createPage(getDriver(), getContext(), log, status, data,
				getReport(), getReportLogger(), WeeklyPlanningStorePage.class);
		TestStatus status = getTestStatus();
		OptionToView = getContext().getStringProperty("OptionToView");
		if (OptionToView.contains("Budget"))
			Name = pageWeeklyPlanningPO.MonthName(objWeeklyPlanningStoreObjects, extentTest,
					objMonthlyPlanningStoreObjects.CurrentMonth);
		if (OptionToView.contains("Forecast"))
			Name = pageWeeklyPlanningPO.MonthName(objWeeklyPlanningStoreObjects, extentTest,
					objMonthlyPlanningStoreObjects.ForecastCurrentMonth);
		String MonthName = Name.replace("Total", "").trim();
		String Month = MonthName.concat(getContext().getStringProperty("Month"));

		String GeneratedSalesTotal = data.getText("GeneratedSalesTotal");
		TotalValue = sql.executeQuery(getContext(),
				GeneratedSalesTotal.replace("Region", getContext().getStringProperty("Region"))
						.replace("Area", getContext().getStringProperty("Area"))
						.replace("Zone", getContext().getStringProperty("Zone")).replace("Month", Month));
		System.out.println("DB Value: " + TotalValue);

		String GeneratedSales = data1.getText("GeneratedSales");
		IndividualValue = sql1.executeQuery(getContext(),
				GeneratedSales.replace("Region", getContext().getStringProperty("Region"))
						.replace("Zone", getContext().getStringProperty("Zone")).replace("Month", Month));
		System.out.println("DB Total: " + IndividualValue);

		pageDBComparisionPO.CompareValues(extentTest, TotalValue, IndividualValue, "Generated Sales");
	}

	@Test(description = "Verify Group Total Value and Respective Group Value in Zone Screen - PlannedSales", priority = 11)
	public void PlannedSales_CurrentMonth() throws Exception {
		pageWeeklyPlanningPO = new WeeklyPlanningStorePage().createPage(getDriver(), getContext(), log, status, data,
				getReport(), getReportLogger(), WeeklyPlanningStorePage.class);
		TestStatus status = getTestStatus();
		OptionToView = getContext().getStringProperty("OptionToView");
		if (OptionToView.contains("Budget"))
			Name = pageWeeklyPlanningPO.MonthName(objWeeklyPlanningStoreObjects, extentTest,
					objMonthlyPlanningStoreObjects.CurrentMonth);
		if (OptionToView.contains("Forecast"))
			Name = pageWeeklyPlanningPO.MonthName(objWeeklyPlanningStoreObjects, extentTest,
					objMonthlyPlanningStoreObjects.ForecastCurrentMonth);
		String MonthName = Name.replace("Total", "").trim();
		String Month = MonthName.concat(getContext().getStringProperty("Month"));

		String PlannedSalesTotal = data.getText("PlannedSalesTotal");
		TotalValue = sql.executeQuery(getContext(),
				PlannedSalesTotal.replace("Region", getContext().getStringProperty("Region"))
						.replace("Area", getContext().getStringProperty("Area"))
						.replace("Zone", getContext().getStringProperty("Zone")).replace("Month", Month));
		System.out.println("DB Value: " + TotalValue);

		String PlannedSales = data1.getText("PlannedSales");
		IndividualValue = sql1.executeQuery(getContext(),
				PlannedSales.replace("Region", getContext().getStringProperty("Region"))
						.replace("Zone", getContext().getStringProperty("Zone")).replace("Month", Month));
		System.out.println("DB Total: " + IndividualValue);

		pageDBComparisionPO.CompareValues(extentTest, TotalValue, IndividualValue, "Planned Sales");
	}

	@Test(description = "Verify Group Total Value and Respective Group Value in Zone Screen - VarToBUD_FCST Sales", priority = 12)
	public void VarToBUDSales_CurrentMonth() throws Exception {
		pageWeeklyPlanningPO = new WeeklyPlanningStorePage().createPage(getDriver(), getContext(), log, status, data,
				getReport(), getReportLogger(), WeeklyPlanningStorePage.class);
		TestStatus status = getTestStatus();
		OptionToView = getContext().getStringProperty("OptionToView");
		if (OptionToView.contains("Budget"))
			Name = pageWeeklyPlanningPO.MonthName(objWeeklyPlanningStoreObjects, extentTest,
					objMonthlyPlanningStoreObjects.CurrentMonth);
		if (OptionToView.contains("Forecast"))
			Name = pageWeeklyPlanningPO.MonthName(objWeeklyPlanningStoreObjects, extentTest,
					objMonthlyPlanningStoreObjects.ForecastCurrentMonth);
		String MonthName = Name.replace("Total", "").trim();
		String Month = MonthName.concat(getContext().getStringProperty("Month"));

		if (OptionToView.contains("Budget")) {
			String VartoBUDTotal = data.getText("VartoBUDTotal");
			TotalValue = sql.executeQuery(getContext(),
					VartoBUDTotal.replace("Region", getContext().getStringProperty("Region"))
							.replace("Area", getContext().getStringProperty("Area"))
							.replace("Zone", getContext().getStringProperty("Zone")).replace("Month", Month));
			System.out.println("DB Value: " + TotalValue);

			String VartoBUD = data1.getText("VartoBUD");
			IndividualValue = sql1.executeQuery(getContext(),
					VartoBUD.replace("Region", getContext().getStringProperty("Region"))
							.replace("Zone", getContext().getStringProperty("Zone")).replace("Month", Month));
			System.out.println("DB Total: " + IndividualValue);

			pageDBComparisionPO.CompareValues(extentTest, TotalValue, IndividualValue, "Var To BUD Sales");
		}

		if (OptionToView.contains("Forecast")) {
			String VartoFCSTTotal = data.getText("VartoFCSTTotal");
			TotalValue = sql.executeQuery(getContext(),
					VartoFCSTTotal.replace("Region", getContext().getStringProperty("Region"))
							.replace("Area", getContext().getStringProperty("Area"))
							.replace("Zone", getContext().getStringProperty("Zone")).replace("Month", Month));
			System.out.println("DB Value: " + TotalValue);

			String VartoFCST = data1.getText("VartoFCST");
			IndividualValue = sql1.executeQuery(getContext(),
					VartoFCST.replace("Region", getContext().getStringProperty("Region"))
							.replace("Zone", getContext().getStringProperty("Zone")).replace("Month", Month));
			System.out.println("DB Total: " + IndividualValue);

			pageDBComparisionPO.CompareValues(extentTest, TotalValue, IndividualValue, "Var To FCST Sales");
		}
	}

	@Test(description = "Verify Group Total Value and Respective Group Value in Zone Screen - LYPercentage Sales", priority = 13)
	public void LYPercentageSales_CurrentMonth() throws Exception {
		pageWeeklyPlanningPO = new WeeklyPlanningStorePage().createPage(getDriver(), getContext(), log, status, data,
				getReport(), getReportLogger(), WeeklyPlanningStorePage.class);
		TestStatus status = getTestStatus();
		OptionToView = getContext().getStringProperty("OptionToView");
		if (OptionToView.contains("Budget"))
			Name = pageWeeklyPlanningPO.MonthName(objWeeklyPlanningStoreObjects, extentTest,
					objMonthlyPlanningStoreObjects.CurrentMonth);
		if (OptionToView.contains("Forecast"))
			Name = pageWeeklyPlanningPO.MonthName(objWeeklyPlanningStoreObjects, extentTest,
					objMonthlyPlanningStoreObjects.ForecastCurrentMonth);
		String MonthName = Name.replace("Total", "").trim();
		String Month = MonthName.concat(getContext().getStringProperty("Month"));
		String LYMonthName = MonthName.concat(getContext().getStringProperty("LYMonth"));

		String LYPercentageSalesTotal = data.getText("LYPercentageSalesTotal");
		TotalValue = sql.executeQuery(getContext(),
				LYPercentageSalesTotal.replace("Region", getContext().getStringProperty("Region"))
						.replace("Area", getContext().getStringProperty("Area"))
						.replace("Zone", getContext().getStringProperty("Zone")).replace("Month", Month)
						.replace("LYMnth", LYMonthName));
		System.out.println("DB Value: " + TotalValue);

		String LYPercentageSales = data1.getText("LYPercentageSales");
		IndividualValue = sql1.executeQuery(getContext(),
				LYPercentageSales.replace("Region", getContext().getStringProperty("Region"))
						.replace("Zone", getContext().getStringProperty("Zone")).replace("Month", Month)
						.replace("LYMnth", LYMonthName));
		System.out.println("DB Total: " + IndividualValue);

		pageDBComparisionPO.CompareValues(extentTest, TotalValue, IndividualValue, "LYPercentage Sales");
	}

	@Test(description = "Verify Group Total Value and Respective Group Value in Zone Screen - BudgetPercentage Sales", priority = 14)
	public void BudgetPercentageSales_CurrentMonth() throws Exception {
		pageWeeklyPlanningPO = new WeeklyPlanningStorePage().createPage(getDriver(), getContext(), log, status, data,
				getReport(), getReportLogger(), WeeklyPlanningStorePage.class);
		TestStatus status = getTestStatus();
		OptionToView = getContext().getStringProperty("OptionToView");
		if (OptionToView.contains("Budget"))
			Name = pageWeeklyPlanningPO.MonthName(objWeeklyPlanningStoreObjects, extentTest,
					objMonthlyPlanningStoreObjects.CurrentMonth);
		if (OptionToView.contains("Forecast"))
			Name = pageWeeklyPlanningPO.MonthName(objWeeklyPlanningStoreObjects, extentTest,
					objMonthlyPlanningStoreObjects.ForecastCurrentMonth);
		String MonthName = Name.replace("Total", "").trim();
		String Month = MonthName.concat(getContext().getStringProperty("Month"));

		if (OptionToView.contains("Budget")) {
			String BUDPercentageSalesTotal = data.getText("BUDPercentageSalesTotal");
			TotalValue = sql.executeQuery(getContext(),
					BUDPercentageSalesTotal.replace("Region", getContext().getStringProperty("Region"))
							.replace("Area", getContext().getStringProperty("Area"))
							.replace("Zone", getContext().getStringProperty("Zone")).replace("Month", Month));
			System.out.println("DB Value: " + TotalValue);

			String BUDPercentageSales = data1.getText("BUDPercentageSales");
			IndividualValue = sql1.executeQuery(getContext(),
					BUDPercentageSales.replace("Region", getContext().getStringProperty("Region"))
							.replace("Zone", getContext().getStringProperty("Zone")).replace("Month", Month));
			System.out.println("DB Total: " + IndividualValue);

			pageDBComparisionPO.CompareValues(extentTest, TotalValue, IndividualValue, "BudgetPercentage Sales");
		}

		if (OptionToView.contains("Forecast")) {
			String FCSTPercentageSalesTotal = data.getText("FCSTPercentageSalesTotal");
			TotalValue = sql.executeQuery(getContext(),
					FCSTPercentageSalesTotal.replace("Region", getContext().getStringProperty("Region"))
							.replace("Area", getContext().getStringProperty("Area"))
							.replace("Zone", getContext().getStringProperty("Zone")).replace("Month", Month));
			System.out.println("DB Value: " + TotalValue);

			String FCSTPercentageSales = data1.getText("FCSTPercentageSales");
			IndividualValue = sql1.executeQuery(getContext(),
					FCSTPercentageSales.replace("Region", getContext().getStringProperty("Region"))
							.replace("Zone", getContext().getStringProperty("Zone")).replace("Month", Month));
			System.out.println("DB Total: " + IndividualValue);

			pageDBComparisionPO.CompareValues(extentTest, TotalValue, IndividualValue, "ForecastPercentage Sales");
		}
	}

	// Wages
	@Test(description = "Verify Group Total Value and Respective Group Value in Zone Screen - Budget_Forecast Wages", priority = 15)
	public void BudgetWages_CurrentMonth() throws Exception {
		pageWeeklyPlanningPO = new WeeklyPlanningStorePage().createPage(getDriver(), getContext(), log, status, data,
				getReport(), getReportLogger(), WeeklyPlanningStorePage.class);
		TestStatus status = getTestStatus();
		OptionToView = getContext().getStringProperty("OptionToView");
		if (OptionToView.contains("Budget"))
			Name = pageWeeklyPlanningPO.MonthName(objWeeklyPlanningStoreObjects, extentTest,
					objMonthlyPlanningStoreObjects.CurrentMonth);
		if (OptionToView.contains("Forecast"))
			Name = pageWeeklyPlanningPO.MonthName(objWeeklyPlanningStoreObjects, extentTest,
					objMonthlyPlanningStoreObjects.ForecastCurrentMonth);
		String MonthName = Name.replace("Total", "").trim();
		String Month = MonthName.concat(getContext().getStringProperty("Month"));

		if (OptionToView.contains("Budget")) {
			String BudgetWagesTotal = data.getText("BudgetWagesTotal");
			TotalValue = sql.executeQuery(getContext(),
					BudgetWagesTotal.replace("Region", getContext().getStringProperty("Region"))
							.replace("Area", getContext().getStringProperty("Area"))
							.replace("Zone", getContext().getStringProperty("Zone")).replace("Month", Month));
			System.out.println("DB Value: " + TotalValue);

			String BudgetWages = data1.getText("BudgetWages");
			IndividualValue = sql1.executeQuery(getContext(),
					BudgetWages.replace("Region", getContext().getStringProperty("Region"))
							.replace("Zone", getContext().getStringProperty("Zone")).replace("Month", Month));
			System.out.println("DB Total: " + IndividualValue);

			pageDBComparisionPO.CompareValues(extentTest, TotalValue, IndividualValue, "Budget Wages");
		}

		if (OptionToView.contains("Forecast")) {
			String ForecastWagesTotal = data.getText("ForecastWagesTotal");
			TotalValue = sql.executeQuery(getContext(),
					ForecastWagesTotal.replace("Region", getContext().getStringProperty("Region"))
							.replace("Area", getContext().getStringProperty("Area"))
							.replace("Zone", getContext().getStringProperty("Zone")).replace("Month", Month));
			System.out.println("DB Value: " + TotalValue);

			String ForecastWages = data1.getText("ForecastWages");
			IndividualValue = sql1.executeQuery(getContext(),
					ForecastWages.replace("Region", getContext().getStringProperty("Region"))
							.replace("Zone", getContext().getStringProperty("Zone")).replace("Month", Month));
			System.out.println("DB Total: " + IndividualValue);

			pageDBComparisionPO.CompareValues(extentTest, TotalValue, IndividualValue, "Forecast Wages");
		}
	}

	@Test(description = "Verify Group Total Value and Respective Group Value in Zone Screen - BaseCost Wages", priority = 16)
	public void BaseCostWages_CurrentMonth() throws Exception {
		pageWeeklyPlanningPO = new WeeklyPlanningStorePage().createPage(getDriver(), getContext(), log, status, data,
				getReport(), getReportLogger(), WeeklyPlanningStorePage.class);
		TestStatus status = getTestStatus();
		OptionToView = getContext().getStringProperty("OptionToView");
		if (OptionToView.contains("Budget"))
			Name = pageWeeklyPlanningPO.MonthName(objWeeklyPlanningStoreObjects, extentTest,
					objMonthlyPlanningStoreObjects.CurrentMonth);
		if (OptionToView.contains("Forecast"))
			Name = pageWeeklyPlanningPO.MonthName(objWeeklyPlanningStoreObjects, extentTest,
					objMonthlyPlanningStoreObjects.ForecastCurrentMonth);
		String MonthName = Name.replace("Total", "").trim();
		String Month = MonthName.concat(getContext().getStringProperty("Month"));

		String BaseCostTotal = data.getText("BaseCostTotal");
		TotalValue = sql.executeQuery(getContext(),
				BaseCostTotal.replace("Region", getContext().getStringProperty("Region"))
						.replace("Area", getContext().getStringProperty("Area"))
						.replace("Zone", getContext().getStringProperty("Zone")).replace("Month", Month));
		System.out.println("DB Value: " + TotalValue);

		String BaseCost = data1.getText("BaseCost");
		IndividualValue = sql1.executeQuery(getContext(),
				BaseCost.replace("Region", getContext().getStringProperty("Region"))
						.replace("Zone", getContext().getStringProperty("Zone")).replace("Month", Month));
		System.out.println("DB Total: " + IndividualValue);

		pageDBComparisionPO.CompareValues(extentTest, TotalValue, IndividualValue, "BaseCost Wages");
	}

	@Test(description = "Verify Group Total Value and Respective Group Value in Zone Screen - Planned Wages", priority = 17)
	public void PlannedWages_CurrentMonth() throws Exception {
		pageWeeklyPlanningPO = new WeeklyPlanningStorePage().createPage(getDriver(), getContext(), log, status, data,
				getReport(), getReportLogger(), WeeklyPlanningStorePage.class);
		TestStatus status = getTestStatus();
		OptionToView = getContext().getStringProperty("OptionToView");
		if (OptionToView.contains("Budget"))
			Name = pageWeeklyPlanningPO.MonthName(objWeeklyPlanningStoreObjects, extentTest,
					objMonthlyPlanningStoreObjects.CurrentMonth);
		if (OptionToView.contains("Forecast"))
			Name = pageWeeklyPlanningPO.MonthName(objWeeklyPlanningStoreObjects, extentTest,
					objMonthlyPlanningStoreObjects.ForecastCurrentMonth);
		String MonthName = Name.replace("Total", "").trim();
		String Month = MonthName.concat(getContext().getStringProperty("Month"));

		String PlannedWagesTotal = data.getText("PlannedWagesTotal");
		TotalValue = sql.executeQuery(getContext(),
				PlannedWagesTotal.replace("Region", getContext().getStringProperty("Region"))
						.replace("Area", getContext().getStringProperty("Area"))
						.replace("Zone", getContext().getStringProperty("Zone")).replace("Month", Month));
		System.out.println("DB Value: " + TotalValue);

		String PlannedWages = data1.getText("PlannedWages");
		IndividualValue = sql1.executeQuery(getContext(),
				PlannedWages.replace("Region", getContext().getStringProperty("Region"))
						.replace("Zone", getContext().getStringProperty("Zone")).replace("Month", Month));
		System.out.println("DB Total: " + IndividualValue);

		pageDBComparisionPO.CompareValues(extentTest, TotalValue, IndividualValue, "Planned Wages");
	}

	@Test(description = "Verify Group Total Value and Respective Group Value in Zone Screen - LYPercentage Wages", priority = 18)
	public void LYPercentageWages_CurrentMonth() throws Exception {
		pageWeeklyPlanningPO = new WeeklyPlanningStorePage().createPage(getDriver(), getContext(), log, status, data,
				getReport(), getReportLogger(), WeeklyPlanningStorePage.class);
		TestStatus status = getTestStatus();
		OptionToView = getContext().getStringProperty("OptionToView");
		if (OptionToView.contains("Budget"))
			Name = pageWeeklyPlanningPO.MonthName(objWeeklyPlanningStoreObjects, extentTest,
					objMonthlyPlanningStoreObjects.CurrentMonth);
		if (OptionToView.contains("Forecast"))
			Name = pageWeeklyPlanningPO.MonthName(objWeeklyPlanningStoreObjects, extentTest,
					objMonthlyPlanningStoreObjects.ForecastCurrentMonth);
		String MonthName = Name.replace("Total", "").trim();
		String Month = MonthName.concat(getContext().getStringProperty("Month"));
		String LYMonthName = MonthName.concat(getContext().getStringProperty("LYMonth"));

		String LYPercentageWagesTotal = data.getText("LYPercentageWagesTotal");
		TotalValue = sql.executeQuery(getContext(),
				LYPercentageWagesTotal.replace("Region", getContext().getStringProperty("Region"))
						.replace("Area", getContext().getStringProperty("Area"))
						.replace("Zone", getContext().getStringProperty("Zone")).replace("Month", Month)
						.replace("LYMnth", LYMonthName));
		System.out.println("DB Value: " + TotalValue);

		String LYPercentageWages = data1.getText("LYPercentageWages");
		IndividualValue = sql1.executeQuery(getContext(),
				LYPercentageWages.replace("Region", getContext().getStringProperty("Region"))
						.replace("Zone", getContext().getStringProperty("Zone")).replace("Month", Month)
						.replace("LYMnth", LYMonthName));
		System.out.println("DB Total: " + IndividualValue);

		pageDBComparisionPO.CompareValues(extentTest, TotalValue, IndividualValue, "LYPercentage Wages");
	}

	// Wages %
	@Test(description = "Verify Group Total Value and Respective Group Value in Zone Screen - Budget_Forecast Wages %", priority = 19)
	public void BudgetWagesPercentage_CurrentMonth() throws Exception {
		pageWeeklyPlanningPO = new WeeklyPlanningStorePage().createPage(getDriver(), getContext(), log, status, data,
				getReport(), getReportLogger(), WeeklyPlanningStorePage.class);
		TestStatus status = getTestStatus();
		OptionToView = getContext().getStringProperty("OptionToView");
		if (OptionToView.contains("Budget"))
			Name = pageWeeklyPlanningPO.MonthName(objWeeklyPlanningStoreObjects, extentTest,
					objMonthlyPlanningStoreObjects.CurrentMonth);
		if (OptionToView.contains("Forecast"))
			Name = pageWeeklyPlanningPO.MonthName(objWeeklyPlanningStoreObjects, extentTest,
					objMonthlyPlanningStoreObjects.ForecastCurrentMonth);
		String MonthName = Name.replace("Total", "").trim();
		String Month = MonthName.concat(getContext().getStringProperty("Month"));

		if (OptionToView.contains("Budget")) {
			String BudgetWagesPercentageTotal = data.getText("BudgetWagesPercentageTotal");
			TotalValue = sql.executeQuery(getContext(),
					BudgetWagesPercentageTotal.replace("Region", getContext().getStringProperty("Region"))
							.replace("Area", getContext().getStringProperty("Area"))
							.replace("Zone", getContext().getStringProperty("Zone")).replace("Month", Month));
			System.out.println("DB Value: " + TotalValue);

			String BudgetWagesPercentage = data1.getText("BudgetWagesPercentage");
			IndividualValue = sql1.executeQuery(getContext(),
					BudgetWagesPercentage.replace("Region", getContext().getStringProperty("Region"))
							.replace("Zone", getContext().getStringProperty("Zone")).replace("Month", Month));
			System.out.println("DB Total: " + IndividualValue);

			pageDBComparisionPO.CompareValues(extentTest, TotalValue, IndividualValue, "Budget Wages%");
		}

		if (OptionToView.contains("Forecast")) {
			String ForecastWagesPercentageTotal = data.getText("ForecastWagesPercentageTotal");
			TotalValue = sql.executeQuery(getContext(),
					ForecastWagesPercentageTotal.replace("Region", getContext().getStringProperty("Region"))
							.replace("Area", getContext().getStringProperty("Area"))
							.replace("Zone", getContext().getStringProperty("Zone")).replace("Month", Month));
			System.out.println("DB Value: " + TotalValue);

			String ForecastWagesPercentage = data1.getText("ForecastWagesPercentage");
			IndividualValue = sql1.executeQuery(getContext(),
					ForecastWagesPercentage.replace("Region", getContext().getStringProperty("Region"))
							.replace("Zone", getContext().getStringProperty("Zone")).replace("Month", Month));
			System.out.println("DB Total: " + IndividualValue);

			pageDBComparisionPO.CompareValues(extentTest, TotalValue, IndividualValue, "Forecast Wages%");
		}
	}

	@Test(description = "Verify Group Total Value and Respective Group Value in Zone Screen - Planned Wages %", priority = 20)
	public void PlannedWagesPercentage_CurrentMonth() throws Exception {
		pageWeeklyPlanningPO = new WeeklyPlanningStorePage().createPage(getDriver(), getContext(), log, status, data,
				getReport(), getReportLogger(), WeeklyPlanningStorePage.class);
		TestStatus status = getTestStatus();
		OptionToView = getContext().getStringProperty("OptionToView");
		if (OptionToView.contains("Budget"))
			Name = pageWeeklyPlanningPO.MonthName(objWeeklyPlanningStoreObjects, extentTest,
					objMonthlyPlanningStoreObjects.CurrentMonth);
		if (OptionToView.contains("Forecast"))
			Name = pageWeeklyPlanningPO.MonthName(objWeeklyPlanningStoreObjects, extentTest,
					objMonthlyPlanningStoreObjects.ForecastCurrentMonth);
		String MonthName = Name.replace("Total", "").trim();
		String Month = MonthName.concat(getContext().getStringProperty("Month"));

		String PlannedWagesPercentageTotal = data.getText("PlannedWagesPercentageTotal");
		TotalValue = sql.executeQuery(getContext(),
				PlannedWagesPercentageTotal.replace("Region", getContext().getStringProperty("Region"))
						.replace("Area", getContext().getStringProperty("Area"))
						.replace("Zone", getContext().getStringProperty("Zone")).replace("Month", Month));
		System.out.println("DB Value: " + TotalValue);

		String PlannedWagesPercentage = data1.getText("PlannedWagesPercentage");
		IndividualValue = sql1.executeQuery(getContext(),
				PlannedWagesPercentage.replace("Region", getContext().getStringProperty("Region"))
						.replace("Zone", getContext().getStringProperty("Zone")).replace("Month", Month));
		System.out.println("DB Total: " + IndividualValue);

		pageDBComparisionPO.CompareValues(extentTest, TotalValue, IndividualValue, "Planned Wages %");
	}

	@Test(description = "Verify Group Total Value and Respective Group Value in Zone Screen - LY Wages%", priority = 21)
	public void LYWagesPercentage_CurrentMonth() throws Exception {
		pageWeeklyPlanningPO = new WeeklyPlanningStorePage().createPage(getDriver(), getContext(), log, status, data,
				getReport(), getReportLogger(), WeeklyPlanningStorePage.class);
		TestStatus status = getTestStatus();
		OptionToView = getContext().getStringProperty("OptionToView");
		if (OptionToView.contains("Budget"))
			Name = pageWeeklyPlanningPO.MonthName(objWeeklyPlanningStoreObjects, extentTest,
					objMonthlyPlanningStoreObjects.CurrentMonth);
		if (OptionToView.contains("Forecast"))
			Name = pageWeeklyPlanningPO.MonthName(objWeeklyPlanningStoreObjects, extentTest,
					objMonthlyPlanningStoreObjects.ForecastCurrentMonth);
		String MonthName = Name.replace("Total", "").trim();
		String Month = MonthName.concat(getContext().getStringProperty("Month"));
		String LYMonthName = MonthName.concat(getContext().getStringProperty("LYMonth"));

		String LYWagesPercentageTotal = data.getText("LYWagesPercentageTotal");
		TotalValue = sql.executeQuery(getContext(),
				LYWagesPercentageTotal.replace("Region", getContext().getStringProperty("Region"))
						.replace("Area", getContext().getStringProperty("Area"))
						.replace("Zone", getContext().getStringProperty("Zone")).replace("Month", Month)
						.replace("LYMnth", LYMonthName));
		System.out.println("DB Value: " + TotalValue);

		String LYWagesPercentage = data1.getText("LYWagesPercentage");
		IndividualValue = sql1.executeQuery(getContext(),
				LYWagesPercentage.replace("Region", getContext().getStringProperty("Region"))
						.replace("Zone", getContext().getStringProperty("Zone")).replace("Month", Month)
						.replace("LYMnth", LYMonthName));
		System.out.println("DB Total: " + IndividualValue);

		pageDBComparisionPO.CompareValues(extentTest, TotalValue, IndividualValue, "LY Wages %");
	}

	// Hours
	@Test(description = "Verify Group Total Value and Respective Group Value in Zone Screen - SMS Hours", priority = 22)
	public void SMSHours_CurrentMonth() throws Exception {
		pageWeeklyPlanningPO = new WeeklyPlanningStorePage().createPage(getDriver(), getContext(), log, status, data,
				getReport(), getReportLogger(), WeeklyPlanningStorePage.class);
		TestStatus status = getTestStatus();
		OptionToView = getContext().getStringProperty("OptionToView");
		if (OptionToView.contains("Budget"))
			Name = pageWeeklyPlanningPO.MonthName(objWeeklyPlanningStoreObjects, extentTest,
					objMonthlyPlanningStoreObjects.CurrentMonth);
		if (OptionToView.contains("Forecast"))
			Name = pageWeeklyPlanningPO.MonthName(objWeeklyPlanningStoreObjects, extentTest,
					objMonthlyPlanningStoreObjects.ForecastCurrentMonth);
		String MonthName = Name.replace("Total", "").trim();
		String Month = MonthName.concat(getContext().getStringProperty("Month"));

		String SMSHoursTotal = data.getText("SMSHoursTotal");
		TotalValue = sql.executeQuery(getContext(),
				SMSHoursTotal.replace("Region", getContext().getStringProperty("Region"))
						.replace("Area", getContext().getStringProperty("Area"))
						.replace("Zone", getContext().getStringProperty("Zone")).replace("Month", Month));
		System.out.println("DB Value: " + TotalValue);

		String SMSHours = data1.getText("SMSHours");
		IndividualValue = sql1.executeQuery(getContext(),
				SMSHours.replace("Region", getContext().getStringProperty("Region"))
						.replace("Zone", getContext().getStringProperty("Zone")).replace("Month", Month));
		System.out.println("DB Total: " + IndividualValue);

		pageDBComparisionPO.CompareValues(extentTest, TotalValue, IndividualValue, "SMS Hours");
	}

	@Test(description = "Verify Group Total Value and Respective Group Value in Zone Screen - Base Hours", priority = 23)
	public void BaseHours_CurrentMonth() throws Exception {
		pageWeeklyPlanningPO = new WeeklyPlanningStorePage().createPage(getDriver(), getContext(), log, status, data,
				getReport(), getReportLogger(), WeeklyPlanningStorePage.class);
		TestStatus status = getTestStatus();
		OptionToView = getContext().getStringProperty("OptionToView");
		if (OptionToView.contains("Budget"))
			Name = pageWeeklyPlanningPO.MonthName(objWeeklyPlanningStoreObjects, extentTest,
					objMonthlyPlanningStoreObjects.CurrentMonth);
		if (OptionToView.contains("Forecast"))
			Name = pageWeeklyPlanningPO.MonthName(objWeeklyPlanningStoreObjects, extentTest,
					objMonthlyPlanningStoreObjects.ForecastCurrentMonth);
		String MonthName = Name.replace("Total", "").trim();
		String Month = MonthName.concat(getContext().getStringProperty("Month"));

		String BasehoursTotal = data.getText("BasehoursTotal");
		TotalValue = sql.executeQuery(getContext(),
				BasehoursTotal.replace("Region", getContext().getStringProperty("Region"))
						.replace("Area", getContext().getStringProperty("Area"))
						.replace("Zone", getContext().getStringProperty("Zone")).replace("Month", Month));
		System.out.println("DB Value: " + TotalValue);

		String Basehours = data1.getText("Basehours");
		IndividualValue = sql1.executeQuery(getContext(),
				Basehours.replace("Region", getContext().getStringProperty("Region"))
						.replace("Zone", getContext().getStringProperty("Zone")).replace("Month", Month));
		System.out.println("DB Total: " + IndividualValue);

		pageDBComparisionPO.CompareValues(extentTest, TotalValue, IndividualValue, "Base Hours");
	}

	@Test(description = "Verify Group Total Value and Respective Group Value in Zone Screen - Planned Hours", priority = 24)
	public void PlannedHours_CurrentMonth() throws Exception {
		pageWeeklyPlanningPO = new WeeklyPlanningStorePage().createPage(getDriver(), getContext(), log, status, data,
				getReport(), getReportLogger(), WeeklyPlanningStorePage.class);
		TestStatus status = getTestStatus();
		OptionToView = getContext().getStringProperty("OptionToView");
		if (OptionToView.contains("Budget"))
			Name = pageWeeklyPlanningPO.MonthName(objWeeklyPlanningStoreObjects, extentTest,
					objMonthlyPlanningStoreObjects.CurrentMonth);
		if (OptionToView.contains("Forecast"))
			Name = pageWeeklyPlanningPO.MonthName(objWeeklyPlanningStoreObjects, extentTest,
					objMonthlyPlanningStoreObjects.ForecastCurrentMonth);
		String MonthName = Name.replace("Total", "").trim();
		String Month = MonthName.concat(getContext().getStringProperty("Month"));

		String PlannedHoursTotal = data.getText("PlannedHoursTotal");
		TotalValue = sql.executeQuery(getContext(),
				PlannedHoursTotal.replace("Region", getContext().getStringProperty("Region"))
						.replace("Area", getContext().getStringProperty("Area"))
						.replace("Zone", getContext().getStringProperty("Zone")).replace("Month", Month));
		System.out.println("DB Value: " + TotalValue);

		String PlannedHours = data1.getText("PlannedHours");
		IndividualValue = sql1.executeQuery(getContext(),
				PlannedHours.replace("Region", getContext().getStringProperty("Region"))
						.replace("Zone", getContext().getStringProperty("Zone")).replace("Month", Month));
		System.out.println("DB Total: " + IndividualValue);

		pageDBComparisionPO.CompareValues(extentTest, TotalValue, IndividualValue, "Planned Hours");
	}

	@Test(description = "Verify Group Total Value and Respective Group Value in Zone Screen - LY% Hours", priority = 25)
	public void LYPercentageHours_CurrentMonth() throws Exception {
		pageWeeklyPlanningPO = new WeeklyPlanningStorePage().createPage(getDriver(), getContext(), log, status, data,
				getReport(), getReportLogger(), WeeklyPlanningStorePage.class);
		TestStatus status = getTestStatus();
		OptionToView = getContext().getStringProperty("OptionToView");
		if (OptionToView.contains("Budget"))
			Name = pageWeeklyPlanningPO.MonthName(objWeeklyPlanningStoreObjects, extentTest,
					objMonthlyPlanningStoreObjects.CurrentMonth);
		if (OptionToView.contains("Forecast"))
			Name = pageWeeklyPlanningPO.MonthName(objWeeklyPlanningStoreObjects, extentTest,
					objMonthlyPlanningStoreObjects.ForecastCurrentMonth);
		String MonthName = Name.replace("Total", "").trim();
		String Month = MonthName.concat(getContext().getStringProperty("Month"));
		String LYMonthName = MonthName.concat(getContext().getStringProperty("LYMonth"));

		String LYHoursTotal = data.getText("LYHoursTotal");
		TotalValue = sql.executeQuery(getContext(),
				LYHoursTotal.replace("Region", getContext().getStringProperty("Region"))
						.replace("Area", getContext().getStringProperty("Area"))
						.replace("Zone", getContext().getStringProperty("Zone")).replace("Month", Month)
						.replace("LYMnth", LYMonthName));
		System.out.println("DB Value: " + TotalValue);

		String LYHours = data1.getText("LYHours");
		IndividualValue = sql1.executeQuery(getContext(),
				LYHours.replace("Region", getContext().getStringProperty("Region"))
						.replace("Zone", getContext().getStringProperty("Zone")).replace("Month", Month)
						.replace("LYMnth", LYMonthName));
		System.out.println("DB Total: " + IndividualValue);

		pageDBComparisionPO.CompareValues(extentTest, TotalValue, IndividualValue, "LY% Hours");
	}

	// OR
	@Test(description = "Verify Group Total Value and Respective Group Value in Zone Screen - Planned OR", priority = 26)
	public void PlannedOR_CurrentMonth() throws Exception {
		pageWeeklyPlanningPO = new WeeklyPlanningStorePage().createPage(getDriver(), getContext(), log, status, data,
				getReport(), getReportLogger(), WeeklyPlanningStorePage.class);
		TestStatus status = getTestStatus();
		OptionToView = getContext().getStringProperty("OptionToView");
		if (OptionToView.contains("Budget"))
			Name = pageWeeklyPlanningPO.MonthName(objWeeklyPlanningStoreObjects, extentTest,
					objMonthlyPlanningStoreObjects.CurrentMonth);
		if (OptionToView.contains("Forecast"))
			Name = pageWeeklyPlanningPO.MonthName(objWeeklyPlanningStoreObjects, extentTest,
					objMonthlyPlanningStoreObjects.ForecastCurrentMonth);
		String MonthName = Name.replace("Total", "").trim();
		String Month = MonthName.concat(getContext().getStringProperty("Month"));

		String PlannedORTotal = data.getText("PlannedORTotal");
		TotalValue = sql.executeQuery(getContext(),
				PlannedORTotal.replace("Region", getContext().getStringProperty("Region"))
						.replace("Area", getContext().getStringProperty("Area"))
						.replace("Zone", getContext().getStringProperty("Zone")).replace("Month", Month));
		System.out.println("DB Value: " + TotalValue);

		String PlannedOR = data1.getText("PlannedOR");
		IndividualValue = sql1.executeQuery(getContext(),
				PlannedOR.replace("Region", getContext().getStringProperty("Region"))
						.replace("Zone", getContext().getStringProperty("Zone")).replace("Month", Month));
		System.out.println("DB Total: " + IndividualValue);

		pageDBComparisionPO.CompareValues(extentTest, TotalValue, IndividualValue, "Planned OR");
	}

	// CPH
	@Test(description = "Verify Group Total Value and Respective Group Value in Zone Screen - Planned CPH", priority = 27)
	public void PlannedCPH_CurrentMonth() throws Exception {
		pageWeeklyPlanningPO = new WeeklyPlanningStorePage().createPage(getDriver(), getContext(), log, status, data,
				getReport(), getReportLogger(), WeeklyPlanningStorePage.class);
		TestStatus status = getTestStatus();
		OptionToView = getContext().getStringProperty("OptionToView");
		if (OptionToView.contains("Budget"))
			Name = pageWeeklyPlanningPO.MonthName(objWeeklyPlanningStoreObjects, extentTest,
					objMonthlyPlanningStoreObjects.CurrentMonth);
		if (OptionToView.contains("Forecast"))
			Name = pageWeeklyPlanningPO.MonthName(objWeeklyPlanningStoreObjects, extentTest,
					objMonthlyPlanningStoreObjects.ForecastCurrentMonth);
		String MonthName = Name.replace("Total", "").trim();
		String Month = MonthName.concat(getContext().getStringProperty("Month"));

		String PlannedCPHTotal = data.getText("PlannedCPHTotal");
		TotalValue = sql.executeQuery(getContext(),
				PlannedCPHTotal.replace("Region", getContext().getStringProperty("Region"))
						.replace("Area", getContext().getStringProperty("Area"))
						.replace("Zone", getContext().getStringProperty("Zone")).replace("Month", Month));
		System.out.println("DB Value: " + TotalValue);

		String PlannedCPH = data1.getText("PlannedCPH");
		IndividualValue = sql1.executeQuery(getContext(),
				PlannedCPH.replace("Region", getContext().getStringProperty("Region"))
						.replace("Zone", getContext().getStringProperty("Zone")).replace("Month", Month));
		System.out.println("DB Total: " + IndividualValue);

		pageDBComparisionPO.CompareValues(extentTest, TotalValue, IndividualValue, "Planned CPH");
	}

	@Test(description = "Verify Group Total Value and Respective Group Value in Zone Screen - GrowthLY CPH", priority = 28)
	public void GrowthLYCPH_CurrentMonth() throws Exception {
		pageWeeklyPlanningPO = new WeeklyPlanningStorePage().createPage(getDriver(), getContext(), log, status, data,
				getReport(), getReportLogger(), WeeklyPlanningStorePage.class);
		TestStatus status = getTestStatus();
		OptionToView = getContext().getStringProperty("OptionToView");
		if (OptionToView.contains("Budget"))
			Name = pageWeeklyPlanningPO.MonthName(objWeeklyPlanningStoreObjects, extentTest,
					objMonthlyPlanningStoreObjects.CurrentMonth);
		if (OptionToView.contains("Forecast"))
			Name = pageWeeklyPlanningPO.MonthName(objWeeklyPlanningStoreObjects, extentTest,
					objMonthlyPlanningStoreObjects.ForecastCurrentMonth);
		String MonthName = Name.replace("Total", "").trim();
		String Month = MonthName.concat(getContext().getStringProperty("Month"));
		String LYMonthName = MonthName.concat(getContext().getStringProperty("LYMonth"));

		String GrowthLYCPHTotal = data.getText("GrowthLYCPHTotal");
		TotalValue = sql.executeQuery(getContext(),
				GrowthLYCPHTotal.replace("Region", getContext().getStringProperty("Region"))
						.replace("Area", getContext().getStringProperty("Area"))
						.replace("Zone", getContext().getStringProperty("Zone")).replace("Month", Month)
						.replace("LYMnth", LYMonthName));
		System.out.println("DB Value: " + TotalValue);

		String GrowthLYCPH = data1.getText("GrowthLYCPH");
		IndividualValue = sql1.executeQuery(getContext(),
				GrowthLYCPH.replace("Region", getContext().getStringProperty("Region"))
						.replace("Zone", getContext().getStringProperty("Zone")).replace("Month", Month)
						.replace("LYMnth", LYMonthName));
		System.out.println("DB Total: " + IndividualValue);

		pageDBComparisionPO.CompareValues(extentTest, TotalValue, IndividualValue, "GrowthLY CPH");
	}

	@Test(priority = 29)
	public void LogoutCLRApplication() throws Exception {
		pageWeeklyPlanningPO = new WeeklyPlanningStorePage().createPage(getDriver(), getContext(), log, status, data,
				getReport(), getReportLogger(), WeeklyPlanningStorePage.class);
		pageWeeklyPlanningPO.LogOFF(objWeeklyPlanningStoreObjects);
	}

	@BeforeMethod
	public void setUpItemPrice(Method m, ITestContext testcontext) throws IOException {
		initializeTestcontext();
		System.out.println("test1 - beforemethod");
		log.info("Loading Testdata for the test case: " + m.getName().toString());
		data.getTCNameFromChildren(m.getName().toString().trim());
		data1.getTCNameFromChildren(m.getName().toString().trim());
		// createDriver(getContext().getStringProperty("WLPUrl"),
		// getContext().getStringProperty("browser"));
		testcontext.setAttribute("WebDriver", getDriver());
		testcontext.setAttribute("Customreports", this.customreport);
		customreport.startTest(m.getName().toString());
		initializeTestStatus();
		reportPath = new File(getContext().getStringProperty("reportPath"));
		moduledesc = m.getName().toString().trim();
		dtmoduledesc = m.getAnnotation(Test.class).description();
		testcasesttime = new Date();
		pageWeeklyPlanningPO = PageFactory.initElements(this.getDriver(), WeeklyPlanningStorePage.class);
		pageMonthlyPlanningPO = PageFactory.initElements(this.getDriver(), MonthlyPlanningStorePage.class);
		pageMonthlyPlanningGroupPO = PageFactory.initElements(this.getDriver(), MonthlyPlanningGroupPage.class);
		BaseObj = PageFactory.initElements(this.getDriver(), BasePage.class);
		common = PageFactory.initElements(this.getDriver(), CommonFunctions.class);
		storeprop = PageFactory.initElements(this.getDriver(), StoreProperties.class);
		objWeeklyPlanningStoreObjects = PageFactory.initElements(this.getDriver(), WeeklyPlanningStoreObjects.class);
		objMonthlyPlanningGroupObjects = PageFactory.initElements(this.getDriver(), MonthlyPlanningGroupObjects.class);
		objWeeklyTradingStatementStoreObjects = PageFactory.initElements(this.getDriver(),
				WeeklyTradingStatementStoreObjects.class);
		pageDailyTradingStatementGroupPO = PageFactory.initElements(this.getDriver(),
				DailyTradingStatementGroupPage.class);
		pageDBComparisionPO = PageFactory.initElements(this.getDriver(), DBComparision.class);
		objMonthlyPlanningStoreObjects = PageFactory.initElements(this.getDriver(), MonthlyPlanningStoreObjects.class);
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
			data.loadTestDataXML(System.getProperty("user.dir") + "\\TestData\\WLP\\MonthlyPlanningGroup.xml");
			data1.loadTestDataXML(System.getProperty("user.dir") + "\\TestData\\WLP\\MonthlyPlanningZone.xml");
			customreport.createExtentReport();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
}