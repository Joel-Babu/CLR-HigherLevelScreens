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

public class MP_BusinessUnitToWoolworthsGroupComparison extends TestBase {

	protected Logger log = LogManager.getLogger(MP_BusinessUnitToWoolworthsGroupComparison.class);
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

	public MP_BusinessUnitToWoolworthsGroupComparison() {
		super.log = log;
	}

	@Test(priority = 0)
	public void LogintoCLRApplication() throws Exception {
		loginPage = new WoWLoginPage().createPage(getDriver(), getContext(), log, status, data, report, extentTest,
				WoWLoginPage.class);
		pageWeeklyPlanningPO = loginPage.navToWOWGroupPage(getContext().getStringProperty("store"));
	}

	@Test(description = "Verify BU Total Value and Respective WOWGroup Value in WOWGroup Screen - Sales History", priority = 1)
	public void SalesHistoryFirstMonth() throws Exception {
		pageWeeklyPlanningPO = new WeeklyPlanningStorePage().createPage(getDriver(), getContext(), log, status, data,
				getReport(), getReportLogger(), WeeklyPlanningStorePage.class);
		TestStatus status = getTestStatus();
		pageWeeklyPlanningPO.SelectHistoryDropdownToView(objWeeklyPlanningStoreObjects, extentTest, "Sales");
		String Month = pageWeeklyPlanningPO.MonthName(objWeeklyPlanningStoreObjects, extentTest,
				objMonthlyPlanningStoreObjects.FirstMonthNm);

		String SalesHistoryTotal = data.getText("SalesHistoryTotal");
		List<List<Object>> SalesTotalValue = sql.executeQuery(getContext(),
				SalesHistoryTotal.replace("Region", getContext().getStringProperty("Region"))
						.replace("Area", getContext().getStringProperty("Area"))
						.replace("Zone", getContext().getStringProperty("Zone")).replace("Month", Month));
		System.out.println("DB Value: " + SalesTotalValue);

		String SalesHistoryTotal1 = data1.getText("SalesHistoryTotal");
		List<List<Object>> SalesIndividualValue = sql1.executeQuery(getContext(),
				SalesHistoryTotal1.replace("Region", getContext().getStringProperty("Region"))
						.replace("Zone", getContext().getStringProperty("Zone")).replace("Month", Month));
		System.out.println("DB Total: " + SalesIndividualValue);

		pageDBComparisionPO.CompareValues(extentTest, SalesTotalValue, SalesIndividualValue, "Sales");
	}

	@Test(description = "Verify BU Total Value and Respective WOWGroup Value in WOWGroup Screen - Wages History", priority = 2)
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

		String WagesHistoryTotal1 = data1.getText("WagesHistoryTotal");
		IndividualValue = sql1.executeQuery(getContext(),
				WagesHistoryTotal1.replace("Region", getContext().getStringProperty("Region"))
						.replace("Zone", getContext().getStringProperty("Zone")).replace("Month", Month));
		System.out.println("DB Total: " + IndividualValue);

		pageDBComparisionPO.CompareValues(extentTest, TotalValue, IndividualValue, "Wages");
	}

	@Test(description = "Verify BU Total Value and Respective WOWGroup Value in WOWGroup Screen - Items History", priority = 3)
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

		String ItemsHistoryTotal1 = data1.getText("ItemsHistoryTotal");
		IndividualValue = sql1.executeQuery(getContext(),
				ItemsHistoryTotal1.replace("Region", getContext().getStringProperty("Region"))
						.replace("Zone", getContext().getStringProperty("Zone")).replace("Month", Month));
		System.out.println("DB Total: " + IndividualValue);

		pageDBComparisionPO.CompareValues(extentTest, TotalValue, IndividualValue, "Items");
	}

	@Test(description = "Verify BU Total Value and Respective WOWGroup Value in WOWGroup Screen - Customers History", priority = 4)
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

		String CustomerHistoryTotal1 = data1.getText("CustomerHistoryTotal");
		IndividualValue = sql1.executeQuery(getContext(),
				CustomerHistoryTotal1.replace("Region", getContext().getStringProperty("Region"))
						.replace("Zone", getContext().getStringProperty("Zone")).replace("Month", Month));
		System.out.println("DB Total: " + IndividualValue);

		pageDBComparisionPO.CompareValues(extentTest, TotalValue, IndividualValue, "Customers");
	}

	@Test(description = "Verify BU Total Value and Respective WOWGroup Value in WOWGroup Screen - Paid Hours History", priority = 5)
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

		String PaidHoursHistoryTotal1 = data1.getText("PaidHoursHistoryTotal");
		IndividualValue = sql1.executeQuery(getContext(),
				PaidHoursHistoryTotal1.replace("Region", getContext().getStringProperty("Region"))
						.replace("Zone", getContext().getStringProperty("Zone")).replace("Month", Month));
		System.out.println("DB Total: " + IndividualValue);

		pageDBComparisionPO.CompareValues(extentTest, TotalValue, IndividualValue, "Paid Hours");
	}

	@Test(description = "Verify BU Total Value and Respective WOWGroup Value in WOWGroup Screen - CPH History", priority = 6)
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

		String CPHHistoryTotal1 = data1.getText("CPHHistoryTotal");
		IndividualValue = sql1.executeQuery(getContext(),
				CPHHistoryTotal1.replace("Region", getContext().getStringProperty("Region"))
						.replace("Zone", getContext().getStringProperty("Zone")).replace("Month", Month));
		System.out.println("DB Total: " + IndividualValue);

		pageDBComparisionPO.CompareValues(extentTest, TotalValue, IndividualValue, "CPH");
	}

	@Test(description = "Verify BU Total Value and Respective WOWGroup Value in WOWGroup Screen - Wages % History", priority = 7)
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

		String WagePercentageHistoryTotal1 = data1.getText("WagePercentageHistoryTotal");
		IndividualValue = sql1.executeQuery(getContext(),
				WagePercentageHistoryTotal1.replace("Region", getContext().getStringProperty("Region"))
						.replace("Zone", getContext().getStringProperty("Zone")).replace("Month", Month));
		System.out.println("DB Total: " + IndividualValue);

		pageDBComparisionPO.CompareValues(extentTest, TotalValue, IndividualValue, "Wages %");
	}

	@Test(description = "Verify BU Total Value and Respective WOWGroup Value in WOWGroup Screen - Item Price History", priority = 8)
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

		String ItemPriceHistoryTotal1 = data1.getText("ItemPriceHistoryTotal");
		IndividualValue = sql1.executeQuery(getContext(),
				ItemPriceHistoryTotal1.replace("Region", getContext().getStringProperty("Region"))
						.replace("Zone", getContext().getStringProperty("Zone")).replace("Month", Month));
		System.out.println("DB Total: " + IndividualValue);

		pageDBComparisionPO.CompareValues(extentTest, TotalValue, IndividualValue, "Item Price");
	}

	@Test(description = "Verify BU Total Value and Respective WOWGroup Value in WOWGroup Screen - BudgetSales", priority = 9)
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

			String BudgetSalesTotal1 = data1.getText("BudgetSalesTotal");
			IndividualValue = sql1.executeQuery(getContext(),
					BudgetSalesTotal1.replace("Region", getContext().getStringProperty("Region"))
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

			String ForecastSalesTotal1 = data1.getText("ForecastSalesTotal");
			IndividualValue = sql1.executeQuery(getContext(),
					ForecastSalesTotal1.replace("Region", getContext().getStringProperty("Region"))
							.replace("Zone", getContext().getStringProperty("Zone")).replace("Month", Month));
			System.out.println("DB Total: " + IndividualValue);

			pageDBComparisionPO.CompareValues(extentTest, TotalValue, IndividualValue, "Forecast Sales");
		}
	}

	@Test(description = "Verify BU Total Value and Respective WOWGroup Value in WOWGroup Screen - GeneratedSales", priority = 10)
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

		String GeneratedSalesTotal1 = data1.getText("GeneratedSalesTotal");
		IndividualValue = sql1.executeQuery(getContext(),
				GeneratedSalesTotal1.replace("Region", getContext().getStringProperty("Region"))
						.replace("Zone", getContext().getStringProperty("Zone")).replace("Month", Month));
		System.out.println("DB Total: " + IndividualValue);

		pageDBComparisionPO.CompareValues(extentTest, TotalValue, IndividualValue, "Generated Sales");
	}

	@Test(description = "Verify BU Total Value and Respective WOWGroup Value in WOWGroup Screen - PlannedSales", priority = 11)
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

		String PlannedSalesTotal1 = data1.getText("PlannedSalesTotal");
		IndividualValue = sql1.executeQuery(getContext(),
				PlannedSalesTotal1.replace("Region", getContext().getStringProperty("Region"))
						.replace("Zone", getContext().getStringProperty("Zone")).replace("Month", Month));
		System.out.println("DB Total: " + IndividualValue);

		pageDBComparisionPO.CompareValues(extentTest, TotalValue, IndividualValue, "Planned Sales");
	}

	@Test(description = "Verify BU Total Value and Respective WOWGroup Value in WOWGroup Screen - VarToBUD_FCST Sales", priority = 12)
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

			String VartoBUDTotal1 = data1.getText("VartoBUDTotal");
			IndividualValue = sql1.executeQuery(getContext(),
					VartoBUDTotal1.replace("Region", getContext().getStringProperty("Region"))
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

			String VartoFCSTTotal1 = data1.getText("VartoFCSTTotal");
			IndividualValue = sql1.executeQuery(getContext(),
					VartoFCSTTotal1.replace("Region", getContext().getStringProperty("Region"))
							.replace("Zone", getContext().getStringProperty("Zone")).replace("Month", Month));
			System.out.println("DB Total: " + IndividualValue);

			pageDBComparisionPO.CompareValues(extentTest, TotalValue, IndividualValue, "Var To FCST Sales");
		}
	}

	@Test(description = "Verify BU Total Value and Respective WOWGroup Value in WOWGroup Screen - LYPercentage Sales", priority = 13)
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

		String LYPercentageSalesTotal1 = data1.getText("LYPercentageSalesTotal");
		IndividualValue = sql1.executeQuery(getContext(),
				LYPercentageSalesTotal1.replace("Region", getContext().getStringProperty("Region"))
						.replace("Zone", getContext().getStringProperty("Zone")).replace("Month", Month)
						.replace("LYMnth", LYMonthName));
		System.out.println("DB Total: " + IndividualValue);

		pageDBComparisionPO.CompareValues(extentTest, TotalValue, IndividualValue, "LYPercentage Sales");
	}

	@Test(description = "Verify BU Total Value and Respective WOWGroup Value in WOWGroup Screen - BudgetPercentage Sales", priority = 14)
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

			String BUDPercentageSalesTotal1 = data1.getText("BUDPercentageSalesTotal");
			IndividualValue = sql1.executeQuery(getContext(),
					BUDPercentageSalesTotal1.replace("Region", getContext().getStringProperty("Region"))
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

			String FCSTPercentageSalesTotal1 = data1.getText("FCSTPercentageSalesTotal");
			IndividualValue = sql1.executeQuery(getContext(),
					FCSTPercentageSalesTotal1.replace("Region", getContext().getStringProperty("Region"))
							.replace("Zone", getContext().getStringProperty("Zone")).replace("Month", Month));
			System.out.println("DB Total: " + IndividualValue);

			pageDBComparisionPO.CompareValues(extentTest, TotalValue, IndividualValue, "ForecastPercentage Sales");
		}
	}

	// Wages
	@Test(description = "Verify BU Total Value and Respective WOWGroup Value in WOWGroup Screen - Budget_Forecast Wages", priority = 15)
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

			String BudgetWagesTotal1 = data1.getText("BudgetWagesTotal");
			IndividualValue = sql1.executeQuery(getContext(),
					BudgetWagesTotal1.replace("Region", getContext().getStringProperty("Region"))
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

			String ForecastWagesTotal1 = data1.getText("ForecastWagesTotal");
			IndividualValue = sql1.executeQuery(getContext(),
					ForecastWagesTotal1.replace("Region", getContext().getStringProperty("Region"))
							.replace("Zone", getContext().getStringProperty("Zone")).replace("Month", Month));
			System.out.println("DB Total: " + IndividualValue);

			pageDBComparisionPO.CompareValues(extentTest, TotalValue, IndividualValue, "Forecast Wages");
		}
	}

	@Test(description = "Verify BU Total Value and Respective WOWGroup Value in WOWGroup Screen - BaseCost Wages", priority = 16)
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

		String BaseCostTotal1 = data1.getText("BaseCostTotal");
		IndividualValue = sql1.executeQuery(getContext(),
				BaseCostTotal1.replace("Region", getContext().getStringProperty("Region"))
						.replace("Zone", getContext().getStringProperty("Zone")).replace("Month", Month));
		System.out.println("DB Total: " + IndividualValue);

		pageDBComparisionPO.CompareValues(extentTest, TotalValue, IndividualValue, "BaseCost Wages");
	}

	@Test(description = "Verify BU Total Value and Respective WOWGroup Value in WOWGroup Screen - Planned Wages", priority = 17)
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

		String PlannedWagesTotal1 = data1.getText("PlannedWagesTotal");
		IndividualValue = sql1.executeQuery(getContext(),
				PlannedWagesTotal1.replace("Region", getContext().getStringProperty("Region"))
						.replace("Zone", getContext().getStringProperty("Zone")).replace("Month", Month));
		System.out.println("DB Total: " + IndividualValue);

		pageDBComparisionPO.CompareValues(extentTest, TotalValue, IndividualValue, "Planned Wages");
	}

	@Test(description = "Verify BU Total Value and Respective WOWGroup Value in WOWGroup Screen - LYPercentage Wages", priority = 18)
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

		String LYPercentageWagesTotal1 = data1.getText("LYPercentageWagesTotal");
		IndividualValue = sql1.executeQuery(getContext(),
				LYPercentageWagesTotal1.replace("Region", getContext().getStringProperty("Region"))
						.replace("Zone", getContext().getStringProperty("Zone")).replace("Month", Month)
						.replace("LYMnth", LYMonthName));
		System.out.println("DB Total: " + IndividualValue);

		pageDBComparisionPO.CompareValues(extentTest, TotalValue, IndividualValue, "LYPercentage Wages");
	}

	// Wages %
	@Test(description = "Verify BU Total Value and Respective WOWGroup Value in WOWGroup Screen - Budget_Forecast Wages %", priority = 19)
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

			String BudgetWagesPercentageTotal1 = data1.getText("BudgetWagesPercentageTotal");
			IndividualValue = sql1.executeQuery(getContext(),
					BudgetWagesPercentageTotal1.replace("Region", getContext().getStringProperty("Region"))
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

			String ForecastWagesPercentageTotal1 = data1.getText("ForecastWagesPercentageTotal");
			IndividualValue = sql1.executeQuery(getContext(),
					ForecastWagesPercentageTotal1.replace("Region", getContext().getStringProperty("Region"))
							.replace("Zone", getContext().getStringProperty("Zone")).replace("Month", Month));
			System.out.println("DB Total: " + IndividualValue);

			pageDBComparisionPO.CompareValues(extentTest, TotalValue, IndividualValue, "Forecast Wages%");
		}
	}

	@Test(description = "Verify BU Total Value and Respective WOWGroup Value in WOWGroup Screen - Planned Wages %", priority = 20)
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

		String PlannedWagesPercentageTotal1 = data1.getText("PlannedWagesPercentageTotal");
		IndividualValue = sql1.executeQuery(getContext(),
				PlannedWagesPercentageTotal1.replace("Region", getContext().getStringProperty("Region"))
						.replace("Zone", getContext().getStringProperty("Zone")).replace("Month", Month));
		System.out.println("DB Total: " + IndividualValue);

		pageDBComparisionPO.CompareValues(extentTest, TotalValue, IndividualValue, "Planned Wages %");
	}

	@Test(description = "Verify BU Total Value and Respective WOWGroup Value in WOWGroup Screen - LY Wages%", priority = 21)
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

		String LYWagesPercentageTotal1 = data1.getText("LYWagesPercentageTotal");
		IndividualValue = sql1.executeQuery(getContext(),
				LYWagesPercentageTotal1.replace("Region", getContext().getStringProperty("Region"))
						.replace("Zone", getContext().getStringProperty("Zone")).replace("Month", Month)
						.replace("LYMnth", LYMonthName));
		System.out.println("DB Total: " + IndividualValue);

		pageDBComparisionPO.CompareValues(extentTest, TotalValue, IndividualValue, "LY Wages %");
	}

	// Hours
	@Test(description = "Verify BU Total Value and Respective WOWGroup Value in WOWGroup Screen - SMS Hours", priority = 22)
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

		String SMSHoursTotal1 = data1.getText("SMSHoursTotal");
		IndividualValue = sql1.executeQuery(getContext(),
				SMSHoursTotal1.replace("Region", getContext().getStringProperty("Region"))
						.replace("Zone", getContext().getStringProperty("Zone")).replace("Month", Month));
		System.out.println("DB Total: " + IndividualValue);

		pageDBComparisionPO.CompareValues(extentTest, TotalValue, IndividualValue, "SMS Hours");
	}

	@Test(description = "Verify BU Total Value and Respective WOWGroup Value in WOWGroup Screen - Base Hours", priority = 23)
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

		String BasehoursTotal1 = data1.getText("BasehoursTotal");
		IndividualValue = sql1.executeQuery(getContext(),
				BasehoursTotal1.replace("Region", getContext().getStringProperty("Region"))
						.replace("Zone", getContext().getStringProperty("Zone")).replace("Month", Month));
		System.out.println("DB Total: " + IndividualValue);

		pageDBComparisionPO.CompareValues(extentTest, TotalValue, IndividualValue, "Base Hours");
	}

	@Test(description = "Verify BU Total Value and Respective WOWGroup Value in WOWGroup Screen - Planned Hours", priority = 24)
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

		String PlannedHoursTotal1 = data1.getText("PlannedHoursTotal");
		IndividualValue = sql1.executeQuery(getContext(),
				PlannedHoursTotal1.replace("Region", getContext().getStringProperty("Region"))
						.replace("Zone", getContext().getStringProperty("Zone")).replace("Month", Month));
		System.out.println("DB Total: " + IndividualValue);

		pageDBComparisionPO.CompareValues(extentTest, TotalValue, IndividualValue, "Planned Hours");
	}

	@Test(description = "Verify BU Total Value and Respective WOWGroup Value in WOWGroup Screen - LY% Hours", priority = 25)
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

		String LYHoursTotal1 = data1.getText("LYHoursTotal");
		IndividualValue = sql1.executeQuery(getContext(),
				LYHoursTotal1.replace("Region", getContext().getStringProperty("Region"))
						.replace("Zone", getContext().getStringProperty("Zone")).replace("Month", Month)
						.replace("LYMnth", LYMonthName));
		System.out.println("DB Total: " + IndividualValue);

		pageDBComparisionPO.CompareValues(extentTest, TotalValue, IndividualValue, "LY% Hours");
	}

	// OR
	@Test(description = "Verify BU Total Value and Respective WOWGroup Value in WOWGroup Screen - Planned OR", priority = 26)
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

		String PlannedORTotal1 = data1.getText("PlannedORTotal");
		IndividualValue = sql1.executeQuery(getContext(),
				PlannedORTotal1.replace("Region", getContext().getStringProperty("Region"))
						.replace("Zone", getContext().getStringProperty("Zone")).replace("Month", Month));
		System.out.println("DB Total: " + IndividualValue);

		pageDBComparisionPO.CompareValues(extentTest, TotalValue, IndividualValue, "Planned OR");
	}

	// CPH
	@Test(description = "Verify BU Total Value and Respective WOWGroup Value in WOWGroup Screen - Planned CPH", priority = 27)
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

		String PlannedCPHTotal1 = data1.getText("PlannedCPHTotal");
		IndividualValue = sql1.executeQuery(getContext(),
				PlannedCPHTotal1.replace("Region", getContext().getStringProperty("Region"))
						.replace("Zone", getContext().getStringProperty("Zone")).replace("Month", Month));
		System.out.println("DB Total: " + IndividualValue);

		pageDBComparisionPO.CompareValues(extentTest, TotalValue, IndividualValue, "Planned CPH");
	}

	@Test(description = "Verify BU Total Value and Respective WOWGroup Value in WOWGroup Screen - GrowthLY CPH", priority = 28)
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

		String GrowthLYCPHTotal1 = data1.getText("GrowthLYCPHTotal");
		IndividualValue = sql1.executeQuery(getContext(),
				GrowthLYCPHTotal1.replace("Region", getContext().getStringProperty("Region"))
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
			data.loadTestDataXML(System.getProperty("user.dir") + "\\TestData\\WLP\\MonthlyPlanningBU_WOWGroup.xml");
			data1.loadTestDataXML(System.getProperty("user.dir") + "\\TestData\\WLP\\MonthlyPlanningWOWGroup.xml");
			customreport.createExtentReport();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
}