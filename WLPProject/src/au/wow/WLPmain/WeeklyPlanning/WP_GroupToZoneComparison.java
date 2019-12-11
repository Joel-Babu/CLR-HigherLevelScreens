package au.wow.WLPmain.WeeklyPlanning;

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

public class WP_GroupToZoneComparison extends TestBase {

	protected Logger log = LogManager.getLogger(WP_GroupToZoneComparison.class);
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
	List<List<Object>> DBValues, DBValues1, DBValues2, DBValues3, DBValues4, DBValues5, DBTotal, DBTotal1, DBTotal2,
			DBTotal3, DBTotal4, DBTotal5;
	String Week, Name, Year, YrName, LYYr, OptionToView, Wk, HWk1, HWk2, HWk3, HWk4, FinYr;
	int LYYear, HistWk, FinYear;
	String[] WeekName, YearName;

	public WP_GroupToZoneComparison() {
		super.log = log;
	}

	@Test(priority = 0)
	public void LogintoCLRApplication() throws Exception {
		loginPage = new WoWLoginPage().createPage(getDriver(), getContext(), log, status, data, report, extentTest,
				WoWLoginPage.class);
		pageWeeklyPlanningPO = loginPage.navToWeeklyPlanningStorePg(getContext().getStringProperty("store"));
	}

	@Test(description = "Verify Group Total Value and Respective Group Value in Zone Screen - Sales History", priority = 1)
	public void SalesHistoryFirstWeek() throws Exception {
		pageWeeklyPlanningPO = new WeeklyPlanningStorePage().createPage(getDriver(), getContext(), log, status, data,
				getReport(), getReportLogger(), WeeklyPlanningStorePage.class);
		pageMonthlyPlanningGroupPO = new MonthlyPlanningGroupPage().createPage(getDriver(), getContext(), log, status,
				data, getReport(), getReportLogger(), MonthlyPlanningGroupPage.class);
		TestStatus status = getTestStatus();
		System.out.println("Entering into TestCase");
		pageWeeklyPlanningPO.SelectWeekFromDropdown(objWeeklyPlanningStoreObjects, extentTest,
				getContext().getStringProperty("SelectFiscalWeek"));

		pageWeeklyPlanningPO.selectPage(extentTest, objWeeklyPlanningStoreObjects.menuBar,
				objWeeklyPlanningStoreObjects.planningScreens, "Planning Screens");
		pageWeeklyPlanningPO.selectPage(extentTest, objWeeklyPlanningStoreObjects.menuBar,
				objWeeklyPlanningStoreObjects.weeklyPlanning, "Weekly Planning");
		pageWeeklyPlanningPO.selectPage(extentTest, objWeeklyPlanningStoreObjects.menuBar,
				objWeeklyPlanningStoreObjects.screenToView, getContext().getStringProperty("WeeklyPlanningGroup"));
		pageWeeklyPlanningPO.verifyPageName(extentTest, objWeeklyPlanningStoreObjects.DataFrame,
				objWeeklyPlanningStoreObjects.pageName, getContext().getStringProperty("WeeklyPlanningGroup"));

		pageWeeklyPlanningPO.SelectHistoryDropdownToView(objWeeklyPlanningStoreObjects, extentTest, "Sales");
		// pageMonthlyPlanningGroupPO.SelectGroupDropdownToView(objMonthlyPlanningGroupObjects,extentTest,getContext().getStringProperty("GroupToView"));
		OptionToView = getContext().getStringProperty("OptionToView");
		if (OptionToView.contains("Budget")) {
			Name = pageWeeklyPlanningPO.MonthName(objWeeklyPlanningStoreObjects, extentTest,
					objWeeklyPlanningStoreObjects.FirstWeekofMonth);
			YrName = pageWeeklyPlanningPO.MonthName(objWeeklyPlanningStoreObjects, extentTest,
					objWeeklyPlanningStoreObjects.CurrentWeekBGDT);
		} else if (OptionToView.contains("Forecast")) {
			Name = pageWeeklyPlanningPO.MonthName(objWeeklyPlanningStoreObjects, extentTest,
					objWeeklyPlanningStoreObjects.FirstWeekofMonth);
			YrName = pageWeeklyPlanningPO.MonthName(objWeeklyPlanningStoreObjects, extentTest,
					objWeeklyPlanningStoreObjects.CurrentWeekFCST);
		}

		WeekName = Name.split(" ");
		YearName = YrName.split(" ");

		Week = WeekName[0];
		// Year = getContext().getStringProperty("Year");
		Year = YearName[4];

		FinYear = Integer.parseInt(Year) + 1;
		FinYr = String.valueOf(FinYear);

		String SalesHistoryTotal = data.getText("SalesHistoryTotal");
		List<List<Object>> SalesTotalValue = sql.executeQuery(getContext(),
				SalesHistoryTotal.replace("Region", getContext().getStringProperty("Region"))
						.replace("Area", getContext().getStringProperty("Area"))
						.replace("store", getContext().getStringProperty("store"))
						.replace("Zone", getContext().getStringProperty("Zone")).replace("FinWeek", Week)
						.replace("FinYear", FinYr).replace("Division", getContext().getStringProperty("Division")));
		System.out.println("DB Value: " + SalesTotalValue);

		String SalesHistory = data1.getText("SalesHistory");
		List<List<Object>> SalesIndividualValue = sql1.executeQuery(getContext(),
				SalesHistory.replace("Region", getContext().getStringProperty("Region"))
						.replace("Area", getContext().getStringProperty("Area"))
						.replace("store", getContext().getStringProperty("store"))
						.replace("Zone", getContext().getStringProperty("Zone")).replace("FinWeek", Week)
						.replace("FinYear", FinYr).replace("Division", getContext().getStringProperty("Division")));
		System.out.println("DB Total: " + SalesIndividualValue);

		pageDBComparisionPO.CompareValues(extentTest, SalesTotalValue, SalesIndividualValue, "Sales");

	}

	@Test(description = "Verify Group Total Value and Respective Group Value in Zone Screen - Wages History", priority = 2)
	public void WagesHistoryFirstWeek() throws Exception {
		pageWeeklyPlanningPO = new WeeklyPlanningStorePage().createPage(getDriver(), getContext(), log, status, data,
				getReport(), getReportLogger(), WeeklyPlanningStorePage.class);
		pageMonthlyPlanningGroupPO = new MonthlyPlanningGroupPage().createPage(getDriver(), getContext(), log, status,
				data, getReport(), getReportLogger(), MonthlyPlanningGroupPage.class);
		TestStatus status = getTestStatus();
		System.out.println("Entering into TestCase");
		pageWeeklyPlanningPO.SelectHistoryDropdownToView(objWeeklyPlanningStoreObjects, extentTest, "Wages");
		pageMonthlyPlanningGroupPO.SelectGroupDropdownToView(objMonthlyPlanningGroupObjects, extentTest,
				getContext().getStringProperty("GroupToView"));
		OptionToView = getContext().getStringProperty("OptionToView");
		if (OptionToView.contains("Budget")) {
			Name = pageWeeklyPlanningPO.MonthName(objWeeklyPlanningStoreObjects, extentTest,
					objWeeklyPlanningStoreObjects.FirstWeekofMonth);
			YrName = pageWeeklyPlanningPO.MonthName(objWeeklyPlanningStoreObjects, extentTest,
					objWeeklyPlanningStoreObjects.CurrentWeekBGDT);
		} else if (OptionToView.contains("Forecast")) {
			Name = pageWeeklyPlanningPO.MonthName(objWeeklyPlanningStoreObjects, extentTest,
					objWeeklyPlanningStoreObjects.FirstWeekofMonth);
			YrName = pageWeeklyPlanningPO.MonthName(objWeeklyPlanningStoreObjects, extentTest,
					objWeeklyPlanningStoreObjects.CurrentWeekFCST);
		}
		WeekName = Name.split(" ");
		YearName = YrName.split(" ");
		Week = WeekName[0];
		Year = YearName[4];
		FinYear = Integer.parseInt(Year) + 1;
		FinYr = String.valueOf(FinYear);

		String WagesHistoryTotal = data.getText("WagesHistoryTotal");
		TotalValue = sql.executeQuery(getContext(),
				WagesHistoryTotal.replace("Region", getContext().getStringProperty("Region"))
						.replace("Area", getContext().getStringProperty("Area"))
						.replace("store", getContext().getStringProperty("store"))
						.replace("Zone", getContext().getStringProperty("Zone")).replace("FinWeek", Week)
						.replace("FinYear", FinYr).replace("Division", getContext().getStringProperty("Division")));
		System.out.println("DB Value: " + TotalValue);

		String WagesHistory = data1.getText("WagesHistory");
		IndividualValue = sql1.executeQuery(getContext(),
				WagesHistory.replace("Region", getContext().getStringProperty("Region"))
						.replace("Area", getContext().getStringProperty("Area"))
						.replace("store", getContext().getStringProperty("store"))
						.replace("Zone", getContext().getStringProperty("Zone")).replace("FinWeek", Week)
						.replace("FinYear", FinYr).replace("Division", getContext().getStringProperty("Division")));
		System.out.println("DB Total: " + IndividualValue);

		pageDBComparisionPO.CompareValues(extentTest, TotalValue, IndividualValue, "Wages");
	}

	@Test(description = "Verify Group Total Value and Respective Group Value in Zone Screen - Items History", priority = 3)
	public void ItemsHistoryFirsthWeek() throws Exception {
		pageWeeklyPlanningPO = new WeeklyPlanningStorePage().createPage(getDriver(), getContext(), log, status, data,
				getReport(), getReportLogger(), WeeklyPlanningStorePage.class);
		pageWeeklyPlanningPO = new WeeklyPlanningStorePage().createPage(getDriver(), getContext(), log, status, data,
				getReport(), getReportLogger(), WeeklyPlanningStorePage.class);
		pageMonthlyPlanningGroupPO = new MonthlyPlanningGroupPage().createPage(getDriver(), getContext(), log, status,
				data, getReport(), getReportLogger(), MonthlyPlanningGroupPage.class);
		TestStatus status = getTestStatus();
		System.out.println("Entering into TestCase");
		pageWeeklyPlanningPO.SelectHistoryDropdownToView(objWeeklyPlanningStoreObjects, extentTest, "Items");
		pageMonthlyPlanningGroupPO.SelectGroupDropdownToView(objMonthlyPlanningGroupObjects, extentTest,
				getContext().getStringProperty("GroupToView"));
		OptionToView = getContext().getStringProperty("OptionToView");
		if (OptionToView.contains("Budget")) {
			Name = pageWeeklyPlanningPO.MonthName(objWeeklyPlanningStoreObjects, extentTest,
					objWeeklyPlanningStoreObjects.FirstWeekofMonth);
			YrName = pageWeeklyPlanningPO.MonthName(objWeeklyPlanningStoreObjects, extentTest,
					objWeeklyPlanningStoreObjects.CurrentWeekBGDT);
		} else if (OptionToView.contains("Forecast")) {
			Name = pageWeeklyPlanningPO.MonthName(objWeeklyPlanningStoreObjects, extentTest,
					objWeeklyPlanningStoreObjects.FirstWeekofMonth);
			YrName = pageWeeklyPlanningPO.MonthName(objWeeklyPlanningStoreObjects, extentTest,
					objWeeklyPlanningStoreObjects.CurrentWeekFCST);
		}
		WeekName = Name.split(" ");
		YearName = YrName.split(" ");
		Week = WeekName[0];
		Year = YearName[4];
		FinYear = Integer.parseInt(Year) + 1;
		FinYr = String.valueOf(FinYear);

		String ItemsHistoryTotal = data.getText("ItemsHistoryTotal");
		TotalValue = sql.executeQuery(getContext(),
				ItemsHistoryTotal.replace("Region", getContext().getStringProperty("Region"))
						.replace("Area", getContext().getStringProperty("Area"))
						.replace("store", getContext().getStringProperty("store"))
						.replace("Zone", getContext().getStringProperty("Zone")).replace("FinWeek", Week)
						.replace("FinYear", FinYr).replace("Division", getContext().getStringProperty("Division")));
		System.out.println("DB Value: " + TotalValue);

		String ItemsHistory = data1.getText("ItemsHistory");
		IndividualValue = sql1.executeQuery(getContext(),
				ItemsHistory.replace("Region", getContext().getStringProperty("Region"))
						.replace("Area", getContext().getStringProperty("Area"))
						.replace("store", getContext().getStringProperty("store"))
						.replace("Zone", getContext().getStringProperty("Zone")).replace("FinWeek", Week)
						.replace("FinYear", FinYr).replace("Division", getContext().getStringProperty("Division")));
		System.out.println("DB Total: " + IndividualValue);

		pageDBComparisionPO.CompareValues(extentTest, TotalValue, IndividualValue, "Items");
	}

	@Test(description = "Verify Group Total Value and Respective Group Value in Zone Screen - Customers History", priority = 4)
	public void CustomersHistoryFirsthWeek() throws Exception {
		pageWeeklyPlanningPO = new WeeklyPlanningStorePage().createPage(getDriver(), getContext(), log, status, data,
				getReport(), getReportLogger(), WeeklyPlanningStorePage.class);
		pageWeeklyPlanningPO = new WeeklyPlanningStorePage().createPage(getDriver(), getContext(), log, status, data,
				getReport(), getReportLogger(), WeeklyPlanningStorePage.class);
		pageMonthlyPlanningGroupPO = new MonthlyPlanningGroupPage().createPage(getDriver(), getContext(), log, status,
				data, getReport(), getReportLogger(), MonthlyPlanningGroupPage.class);
		TestStatus status = getTestStatus();
		System.out.println("Entering into TestCase");
		pageWeeklyPlanningPO.SelectHistoryDropdownToView(objWeeklyPlanningStoreObjects, extentTest, "Customers");
		pageMonthlyPlanningGroupPO.SelectGroupDropdownToView(objMonthlyPlanningGroupObjects, extentTest,
				getContext().getStringProperty("GroupToView"));
		OptionToView = getContext().getStringProperty("OptionToView");
		if (OptionToView.contains("Budget")) {
			Name = pageWeeklyPlanningPO.MonthName(objWeeklyPlanningStoreObjects, extentTest,
					objWeeklyPlanningStoreObjects.FirstWeekofMonth);
			YrName = pageWeeklyPlanningPO.MonthName(objWeeklyPlanningStoreObjects, extentTest,
					objWeeklyPlanningStoreObjects.CurrentWeekBGDT);
		} else if (OptionToView.contains("Forecast")) {
			Name = pageWeeklyPlanningPO.MonthName(objWeeklyPlanningStoreObjects, extentTest,
					objWeeklyPlanningStoreObjects.FirstWeekofMonth);
			YrName = pageWeeklyPlanningPO.MonthName(objWeeklyPlanningStoreObjects, extentTest,
					objWeeklyPlanningStoreObjects.CurrentWeekFCST);
		}
		WeekName = Name.split(" ");
		YearName = YrName.split(" ");
		Week = WeekName[0];
		Year = YearName[4];
		FinYear = Integer.parseInt(Year) + 1;
		FinYr = String.valueOf(FinYear);

		String CustomerHistoryTotal = data.getText("CustomerHistoryTotal");
		TotalValue = sql.executeQuery(getContext(),
				CustomerHistoryTotal.replace("Region", getContext().getStringProperty("Region"))
						.replace("Area", getContext().getStringProperty("Area"))
						.replace("store", getContext().getStringProperty("store"))
						.replace("Zone", getContext().getStringProperty("Zone")).replace("FinWeek", Week)
						.replace("FinYear", FinYr).replace("Division", getContext().getStringProperty("Division")));
		System.out.println("DB Value: " + TotalValue);

		String CustomerHistory = data1.getText("CustomerHistory");
		IndividualValue = sql1.executeQuery(getContext(),
				CustomerHistory.replace("Region", getContext().getStringProperty("Region"))
						.replace("Area", getContext().getStringProperty("Area"))
						.replace("store", getContext().getStringProperty("store"))
						.replace("Zone", getContext().getStringProperty("Zone")).replace("FinWeek", Week)
						.replace("FinYear", FinYr).replace("Division", getContext().getStringProperty("Division")));
		System.out.println("DB Total: " + IndividualValue);

		pageDBComparisionPO.CompareValues(extentTest, TotalValue, IndividualValue, "Customers");
	}

	@Test(description = "Verify Group Total Value and Respective Group Value in Zone Screen - Paid Hours History", priority = 5)
	public void PaidHoursHistoryFirsthWeek() throws Exception {
		pageWeeklyPlanningPO = new WeeklyPlanningStorePage().createPage(getDriver(), getContext(), log, status, data,
				getReport(), getReportLogger(), WeeklyPlanningStorePage.class);
		pageMonthlyPlanningGroupPO = new MonthlyPlanningGroupPage().createPage(getDriver(), getContext(), log, status,
				data, getReport(), getReportLogger(), MonthlyPlanningGroupPage.class);
		TestStatus status = getTestStatus();
		System.out.println("Entering into TestCase");
		pageWeeklyPlanningPO.SelectHistoryDropdownToView(objWeeklyPlanningStoreObjects, extentTest, "Paid Hours");
		pageMonthlyPlanningGroupPO.SelectGroupDropdownToView(objMonthlyPlanningGroupObjects, extentTest,
				getContext().getStringProperty("GroupToView"));
		OptionToView = getContext().getStringProperty("OptionToView");
		if (OptionToView.contains("Budget")) {
			Name = pageWeeklyPlanningPO.MonthName(objWeeklyPlanningStoreObjects, extentTest,
					objWeeklyPlanningStoreObjects.FirstWeekofMonth);
			YrName = pageWeeklyPlanningPO.MonthName(objWeeklyPlanningStoreObjects, extentTest,
					objWeeklyPlanningStoreObjects.CurrentWeekBGDT);
		} else if (OptionToView.contains("Forecast")) {
			Name = pageWeeklyPlanningPO.MonthName(objWeeklyPlanningStoreObjects, extentTest,
					objWeeklyPlanningStoreObjects.FirstWeekofMonth);
			YrName = pageWeeklyPlanningPO.MonthName(objWeeklyPlanningStoreObjects, extentTest,
					objWeeklyPlanningStoreObjects.CurrentWeekFCST);
		}
		WeekName = Name.split(" ");
		YearName = YrName.split(" ");
		Week = WeekName[0];
		Year = YearName[4];
		FinYear = Integer.parseInt(Year) + 1;
		FinYr = String.valueOf(FinYear);

		String PaidHoursHistoryTotal = data.getText("PaidHoursHistoryTotal");
		TotalValue = sql.executeQuery(getContext(),
				PaidHoursHistoryTotal.replace("Region", getContext().getStringProperty("Region"))
						.replace("Area", getContext().getStringProperty("Area"))
						.replace("store", getContext().getStringProperty("store"))
						.replace("Zone", getContext().getStringProperty("Zone")).replace("FinWeek", Week)
						.replace("FinYear", FinYr).replace("Division", getContext().getStringProperty("Division")));
		System.out.println("DB Value: " + TotalValue);

		String PaidHoursHistory = data1.getText("PaidHoursHistory");
		IndividualValue = sql1.executeQuery(getContext(),
				PaidHoursHistory.replace("Region", getContext().getStringProperty("Region"))
						.replace("Area", getContext().getStringProperty("Area"))
						.replace("store", getContext().getStringProperty("store"))
						.replace("Zone", getContext().getStringProperty("Zone")).replace("FinWeek", Week)
						.replace("FinYear", FinYr).replace("Division", getContext().getStringProperty("Division")));
		System.out.println("DB Total: " + IndividualValue);

		pageDBComparisionPO.CompareValueDecimalFields(extentTest, TotalValue, IndividualValue, "Paid Hours");
	}

	@Test(description = "Verify Group Total Value and Respective Group Value in Zone Screen - CPH History", priority = 6)
	public void CPHHistoryFirsthWeek() throws Exception {
		pageWeeklyPlanningPO = new WeeklyPlanningStorePage().createPage(getDriver(), getContext(), log, status, data,
				getReport(), getReportLogger(), WeeklyPlanningStorePage.class);
		pageMonthlyPlanningGroupPO = new MonthlyPlanningGroupPage().createPage(getDriver(), getContext(), log, status,
				data, getReport(), getReportLogger(), MonthlyPlanningGroupPage.class);
		TestStatus status = getTestStatus();
		System.out.println("Entering into TestCase");
		pageWeeklyPlanningPO.SelectHistoryDropdownToView(objWeeklyPlanningStoreObjects, extentTest, "CPH");
		pageMonthlyPlanningGroupPO.SelectGroupDropdownToView(objMonthlyPlanningGroupObjects, extentTest,
				getContext().getStringProperty("GroupToView"));
		OptionToView = getContext().getStringProperty("OptionToView");
		if (OptionToView.contains("Budget")) {
			Name = pageWeeklyPlanningPO.MonthName(objWeeklyPlanningStoreObjects, extentTest,
					objWeeklyPlanningStoreObjects.FirstWeekofMonth);
			YrName = pageWeeklyPlanningPO.MonthName(objWeeklyPlanningStoreObjects, extentTest,
					objWeeklyPlanningStoreObjects.CurrentWeekBGDT);
		} else if (OptionToView.contains("Forecast")) {
			Name = pageWeeklyPlanningPO.MonthName(objWeeklyPlanningStoreObjects, extentTest,
					objWeeklyPlanningStoreObjects.FirstWeekofMonth);
			YrName = pageWeeklyPlanningPO.MonthName(objWeeklyPlanningStoreObjects, extentTest,
					objWeeklyPlanningStoreObjects.CurrentWeekFCST);
		}
		WeekName = Name.split(" ");
		YearName = YrName.split(" ");
		Week = WeekName[0];
		Year = YearName[4];
		FinYear = Integer.parseInt(Year) + 1;
		FinYr = String.valueOf(FinYear);

		String CPHHistoryTotal = data.getText("CPHHistoryTotal");
		TotalValue = sql.executeQuery(getContext(),
				CPHHistoryTotal.replace("Region", getContext().getStringProperty("Region"))
						.replace("Area", getContext().getStringProperty("Area"))
						.replace("store", getContext().getStringProperty("store"))
						.replace("Zone", getContext().getStringProperty("Zone")).replace("FinWeek", Week)
						.replace("FinYear", FinYr).replace("Division", getContext().getStringProperty("Division")));
		System.out.println("DB Value: " + TotalValue);

		String CPHHistory = data1.getText("CPHHistory");
		IndividualValue = sql1.executeQuery(getContext(),
				CPHHistory.replace("Region", getContext().getStringProperty("Region"))
						.replace("Area", getContext().getStringProperty("Area"))
						.replace("store", getContext().getStringProperty("store"))
						.replace("Zone", getContext().getStringProperty("Zone")).replace("FinWeek", Week)
						.replace("FinYear", FinYr).replace("Division", getContext().getStringProperty("Division")));
		System.out.println("DB Total: " + IndividualValue);

		pageDBComparisionPO.CompareValueDecimalFields(extentTest, TotalValue, IndividualValue, "CPH");
	}

	@Test(description = "Verify Group Total Value and Respective Group Value in Zone Screen - Wages % History", priority = 7)
	public void WagePercentageHistoryFirsthWeek() throws Exception {
		pageWeeklyPlanningPO = new WeeklyPlanningStorePage().createPage(getDriver(), getContext(), log, status, data,
				getReport(), getReportLogger(), WeeklyPlanningStorePage.class);
		pageMonthlyPlanningGroupPO = new MonthlyPlanningGroupPage().createPage(getDriver(), getContext(), log, status,
				data, getReport(), getReportLogger(), MonthlyPlanningGroupPage.class);
		TestStatus status = getTestStatus();
		System.out.println("Entering into TestCase");
		pageWeeklyPlanningPO.SelectHistoryDropdownToView(objWeeklyPlanningStoreObjects, extentTest, "Wage %");
		pageMonthlyPlanningGroupPO.SelectGroupDropdownToView(objMonthlyPlanningGroupObjects, extentTest,
				getContext().getStringProperty("GroupToView"));
		OptionToView = getContext().getStringProperty("OptionToView");
		if (OptionToView.contains("Budget")) {
			Name = pageWeeklyPlanningPO.MonthName(objWeeklyPlanningStoreObjects, extentTest,
					objWeeklyPlanningStoreObjects.FirstWeekofMonth);
			YrName = pageWeeklyPlanningPO.MonthName(objWeeklyPlanningStoreObjects, extentTest,
					objWeeklyPlanningStoreObjects.CurrentWeekBGDT);
		} else if (OptionToView.contains("Forecast")) {
			Name = pageWeeklyPlanningPO.MonthName(objWeeklyPlanningStoreObjects, extentTest,
					objWeeklyPlanningStoreObjects.FirstWeekofMonth);
			YrName = pageWeeklyPlanningPO.MonthName(objWeeklyPlanningStoreObjects, extentTest,
					objWeeklyPlanningStoreObjects.CurrentWeekFCST);
		}
		WeekName = Name.split(" ");
		YearName = YrName.split(" ");
		Week = WeekName[0];
		Year = YearName[4];
		FinYear = Integer.parseInt(Year) + 1;
		FinYr = String.valueOf(FinYear);

		String WagePercentageHistoryTotal = data.getText("WagePercentageHistoryTotal");
		TotalValue = sql.executeQuery(getContext(),
				WagePercentageHistoryTotal.replace("Region", getContext().getStringProperty("Region"))
						.replace("Area", getContext().getStringProperty("Area"))
						.replace("store", getContext().getStringProperty("store"))
						.replace("Zone", getContext().getStringProperty("Zone")).replace("FinWeek", Week)
						.replace("FinYear", FinYr).replace("Division", getContext().getStringProperty("Division")));
		System.out.println("DB Value: " + TotalValue);

		String WagePercentageHistory = data1.getText("WagePercentageHistory");
		IndividualValue = sql1.executeQuery(getContext(),
				WagePercentageHistory.replace("Region", getContext().getStringProperty("Region"))
						.replace("Area", getContext().getStringProperty("Area"))
						.replace("store", getContext().getStringProperty("store"))
						.replace("Zone", getContext().getStringProperty("Zone")).replace("FinWeek", Week)
						.replace("FinYear", FinYr).replace("Division", getContext().getStringProperty("Division")));
		System.out.println("DB Total: " + IndividualValue);

		pageDBComparisionPO.CompareValueDecimalFields(extentTest, TotalValue, IndividualValue, "Wages %");
	}

	@Test(description = "Verify Group Total Value and Respective Group Value in Zone Screen - Item Price History", priority = 8)
	public void ItemPriceHistoryFirsthWeek() throws Exception {
		pageWeeklyPlanningPO = new WeeklyPlanningStorePage().createPage(getDriver(), getContext(), log, status, data,
				getReport(), getReportLogger(), WeeklyPlanningStorePage.class);
		pageMonthlyPlanningGroupPO = new MonthlyPlanningGroupPage().createPage(getDriver(), getContext(), log, status,
				data, getReport(), getReportLogger(), MonthlyPlanningGroupPage.class);
		TestStatus status = getTestStatus();
		System.out.println("Entering into TestCase");
		pageWeeklyPlanningPO.SelectHistoryDropdownToView(objWeeklyPlanningStoreObjects, extentTest, "Item Price");
		pageMonthlyPlanningGroupPO.SelectGroupDropdownToView(objMonthlyPlanningGroupObjects, extentTest,
				getContext().getStringProperty("GroupToView"));
		OptionToView = getContext().getStringProperty("OptionToView");
		if (OptionToView.contains("Budget")) {
			Name = pageWeeklyPlanningPO.MonthName(objWeeklyPlanningStoreObjects, extentTest,
					objWeeklyPlanningStoreObjects.FirstWeekofMonth);
			YrName = pageWeeklyPlanningPO.MonthName(objWeeklyPlanningStoreObjects, extentTest,
					objWeeklyPlanningStoreObjects.CurrentWeekBGDT);
		} else if (OptionToView.contains("Forecast")) {
			Name = pageWeeklyPlanningPO.MonthName(objWeeklyPlanningStoreObjects, extentTest,
					objWeeklyPlanningStoreObjects.FirstWeekofMonth);
			YrName = pageWeeklyPlanningPO.MonthName(objWeeklyPlanningStoreObjects, extentTest,
					objWeeklyPlanningStoreObjects.CurrentWeekFCST);
		}
		WeekName = Name.split(" ");
		YearName = YrName.split(" ");
		Week = WeekName[0];
		Year = YearName[4];
		FinYear = Integer.parseInt(Year) + 1;
		FinYr = String.valueOf(FinYear);

		String ItemPriceHistoryTotal = data.getText("ItemPriceHistoryTotal");
		TotalValue = sql.executeQuery(getContext(),
				ItemPriceHistoryTotal.replace("Region", getContext().getStringProperty("Region"))
						.replace("Area", getContext().getStringProperty("Area"))
						.replace("store", getContext().getStringProperty("store"))
						.replace("Zone", getContext().getStringProperty("Zone")).replace("FinWeek", Week)
						.replace("FinYear", FinYr).replace("Division", getContext().getStringProperty("Division")));
		System.out.println("DB Value: " + TotalValue);

		String ItemPriceHistory = data1.getText("ItemPriceHistory");
		IndividualValue = sql1.executeQuery(getContext(),
				ItemPriceHistory.replace("Region", getContext().getStringProperty("Region"))
						.replace("Area", getContext().getStringProperty("Area"))
						.replace("store", getContext().getStringProperty("store"))
						.replace("Zone", getContext().getStringProperty("Zone")).replace("FinWeek", Week)
						.replace("FinYear", FinYr).replace("Division", getContext().getStringProperty("Division")));
		System.out.println("DB Total: " + IndividualValue);

		pageDBComparisionPO.CompareValueDecimalFields(extentTest, TotalValue, IndividualValue, "Item Price");
	}

	@Test(description = "Verify Group Total Value and Respective Group Value in Zone Screen - OR History", priority = 8)
	public void ORHistoryFirstWeek() throws Exception {
		pageWeeklyPlanningPO = new WeeklyPlanningStorePage().createPage(getDriver(), getContext(), log, status, data,
				getReport(), getReportLogger(), WeeklyPlanningStorePage.class);
		pageMonthlyPlanningGroupPO = new MonthlyPlanningGroupPage().createPage(getDriver(), getContext(), log, status,
				data, getReport(), getReportLogger(), MonthlyPlanningGroupPage.class);
		TestStatus status = getTestStatus();
		System.out.println("Entering into TestCase");
		pageWeeklyPlanningPO.VerifyUserName(objWeeklyPlanningStoreObjects, extentTest,
				getContext().getStringProperty("username"));
		pageWeeklyPlanningPO.SelectWeekFromDropdown(objWeeklyPlanningStoreObjects, extentTest,
				getContext().getStringProperty("SelectFiscalWeek"));
		pageWeeklyPlanningPO.SelectPage(objWeeklyPlanningStoreObjects,
				getContext().getStringProperty("WeeklyPlanningGroup"), extentTest);
		// pageWeeklyPlanningPO.SelectHistoryDropdownToView(objWeeklyPlanningStoreObjects,extentTest,"Sales");
		pageWeeklyPlanningPO.SelectHistoryDropdownToView(objWeeklyPlanningStoreObjects, extentTest,
				"Operating Ratio History");
		OptionToView = getContext().getStringProperty("OptionToView");
		if (OptionToView.contains("Budget")) {
			Name = pageWeeklyPlanningPO.MonthName(objWeeklyPlanningStoreObjects, extentTest,
					objWeeklyPlanningStoreObjects.FirstWeekofMonth);
			YrName = pageWeeklyPlanningPO.MonthName(objWeeklyPlanningStoreObjects, extentTest,
					objWeeklyPlanningStoreObjects.CurrentWeekBGDT);
		} else if (OptionToView.contains("Forecast")) {
			Name = pageWeeklyPlanningPO.MonthName(objWeeklyPlanningStoreObjects, extentTest,
					objWeeklyPlanningStoreObjects.FirstWeekofMonth);
			YrName = pageWeeklyPlanningPO.MonthName(objWeeklyPlanningStoreObjects, extentTest,
					objWeeklyPlanningStoreObjects.CurrentWeekFCST);
		}
		WeekName = Name.split(" ");
		YearName = YrName.split(" ");
		Week = WeekName[0];
		Wk = getContext().getStringProperty("SelectFiscalWeek");
		HistWk = Integer.parseInt(Wk) - 4;
		HWk1 = String.valueOf(HistWk);

		String ORHistoryTotal = data.getText("ORHistoryTotal");
		TotalValue = sql.executeQuery(getContext(),
				ORHistoryTotal.replace("Region", getContext().getStringProperty("Region"))
						.replace("Area", getContext().getStringProperty("Area"))
						.replace("Zone", getContext().getStringProperty("Zone")).replace("FinWk", Week)
						.replace("FinYear", getContext().getStringProperty("FinYr")).replace("Week", HWk1)
						.replace("Division", getContext().getStringProperty("Division")));
		System.out.println("DB Value: " + TotalValue);

		String ORHistory = data1.getText("ORHistory");
		IndividualValue = sql1.executeQuery(getContext(),
				ORHistory.replace("Region", getContext().getStringProperty("Region"))
						.replace("Area", getContext().getStringProperty("Area"))
						.replace("Zone", getContext().getStringProperty("Zone")).replace("FinWk", Week)
						.replace("FinYear", getContext().getStringProperty("FinYr")).replace("Week", HWk1)
						.replace("Division", getContext().getStringProperty("Division")));
		System.out.println("DB Total: " + IndividualValue);

		pageDBComparisionPO.CompareValueDecimalFields(extentTest, TotalValue, IndividualValue, "Item Price");
	}

	@Test(description = "Verify Group Total Value and Respective Group Value in Zone Screen - BudgetSales", priority = 9)
	public void BudgetSales_CurrentMonth() throws Exception {
		pageWeeklyPlanningPO = new WeeklyPlanningStorePage().createPage(getDriver(), getContext(), log, status, data,
				getReport(), getReportLogger(), WeeklyPlanningStorePage.class);
		pageMonthlyPlanningGroupPO = new WeeklyPlanningStorePage().createPage(getDriver(), getContext(), log, status,
				data, getReport(), getReportLogger(), MonthlyPlanningGroupPage.class);
		TestStatus status = getTestStatus();
		System.out.println("Entering into TestCase");
		pageWeeklyPlanningPO.VerifyUserName(objWeeklyPlanningStoreObjects, extentTest,
				getContext().getStringProperty("username"));
		pageWeeklyPlanningPO.SelectWeekFromDropdown(objWeeklyPlanningStoreObjects, extentTest,
				getContext().getStringProperty("SelectFiscalWeek"));
		pageWeeklyPlanningPO.SelectPage(objWeeklyPlanningStoreObjects,
				getContext().getStringProperty("WeeklyPlanningGroup"), extentTest);
		pageWeeklyPlanningPO.SelectHistoryDropdownToView(objWeeklyPlanningStoreObjects, extentTest, "Sales");
		pageMonthlyPlanningGroupPO.SelectGroupDropdownToView(objMonthlyPlanningGroupObjects, extentTest,
				getContext().getStringProperty("GroupToView"));
		OptionToView = getContext().getStringProperty("OptionToView");
		if (OptionToView.contains("Budget"))
			Name = pageWeeklyPlanningPO.MonthName(objWeeklyPlanningStoreObjects, extentTest,
					objWeeklyPlanningStoreObjects.CurrentWeekBGDT);
		if (OptionToView.contains("Forecast"))
			Name = pageWeeklyPlanningPO.MonthName(objWeeklyPlanningStoreObjects, extentTest,
					objWeeklyPlanningStoreObjects.CurrentWeekFCST);
		WeekName = Name.split(" "); // YearName = YrName.split(" ");
		Week = WeekName[0];
		Year = WeekName[4];
		// FinYear = Integer.parseInt(Year) +1;
		// FinYr = String.valueOf(FinYear);

		LYYear = Integer.parseInt(Year) + 1;
		LYYr = String.valueOf(LYYear);

		if (OptionToView.contains("Budget")) {
			String BudgetSalesTotal = data.getText("BudgetSalesTotal");
			TotalValue = sql.executeQuery(getContext(),
					BudgetSalesTotal.replace("Region", getContext().getStringProperty("Region"))
							.replace("Area", getContext().getStringProperty("Area"))
							.replace("store", getContext().getStringProperty("store"))
							.replace("Zone", getContext().getStringProperty("Zone")).replace("FinWeek", Week)
							.replace("FinYear", LYYr).replace("LYFinYr", LYYr)
							.replace("Division", getContext().getStringProperty("Division")));
			System.out.println("DB Value: " + TotalValue);

			String BudgetSales = data1.getText("BudgetSales");
			IndividualValue = sql1.executeQuery(getContext(),
					BudgetSales.replace("Region", getContext().getStringProperty("Region"))
							.replace("Area", getContext().getStringProperty("Area"))
							.replace("store", getContext().getStringProperty("store"))
							.replace("Zone", getContext().getStringProperty("Zone")).replace("FinWeek", Week)
							.replace("FinYear", LYYr).replace("LYFinYr", LYYr)
							.replace("Division", getContext().getStringProperty("Division")));
			System.out.println("DB Total: " + IndividualValue);

			pageDBComparisionPO.CompareValues(extentTest, TotalValue, IndividualValue, "Budget Sales");
		}

		if (OptionToView.contains("Forecast")) {
			String ForecastSalesTotal = data.getText("ForecastSalesTotal");
			TotalValue = sql.executeQuery(getContext(),
					ForecastSalesTotal.replace("Region", getContext().getStringProperty("Region"))
							.replace("Area", getContext().getStringProperty("Area"))
							.replace("store", getContext().getStringProperty("store"))
							.replace("Zone", getContext().getStringProperty("Zone")).replace("FinWeek", Week)
							.replace("FinYear", Year).replace("LYFinYr", LYYr)
							.replace("Division", getContext().getStringProperty("Division")));
			System.out.println("DB Value: " + TotalValue);

			String ForecastSales = data1.getText("ForecastSales");
			IndividualValue = sql1.executeQuery(getContext(),
					ForecastSales.replace("Region", getContext().getStringProperty("Region"))
							.replace("Area", getContext().getStringProperty("Area"))
							.replace("store", getContext().getStringProperty("store"))
							.replace("Zone", getContext().getStringProperty("Zone")).replace("FinWeek", Week)
							.replace("FinYear", Year).replace("LYFinYr", LYYr)
							.replace("Division", getContext().getStringProperty("Division")));
			System.out.println("DB Total: " + IndividualValue);

			pageDBComparisionPO.CompareValues(extentTest, TotalValue, IndividualValue, "Forecast Sales");
		}
	}

	@Test(description = "Verify Group Total Value and Respective Group Value in Zone Screen - GeneratedSales", priority = 10)
	public void GeneratedSales_CurrentMonth() throws Exception {
		pageWeeklyPlanningPO = new WeeklyPlanningStorePage().createPage(getDriver(), getContext(), log, status, data,
				getReport(), getReportLogger(), WeeklyPlanningStorePage.class);
		pageMonthlyPlanningGroupPO = new WeeklyPlanningStorePage().createPage(getDriver(), getContext(), log, status,
				data, getReport(), getReportLogger(), MonthlyPlanningGroupPage.class);
		TestStatus status = getTestStatus();
		System.out.println("Entering into TestCase");
		pageMonthlyPlanningGroupPO.SelectGroupDropdownToView(objMonthlyPlanningGroupObjects, extentTest,
				getContext().getStringProperty("GroupToView"));
		OptionToView = getContext().getStringProperty("OptionToView");
		if (OptionToView.contains("Budget"))
			Name = pageWeeklyPlanningPO.MonthName(objWeeklyPlanningStoreObjects, extentTest,
					objWeeklyPlanningStoreObjects.CurrentWeekBGDT);
		if (OptionToView.contains("Forecast"))
			Name = pageWeeklyPlanningPO.MonthName(objWeeklyPlanningStoreObjects, extentTest,
					objWeeklyPlanningStoreObjects.CurrentWeekFCST);
		WeekName = Name.split(" ");
		Week = WeekName[0];
		Year = WeekName[4];
		LYYear = Integer.parseInt(Year) + 1;
		LYYr = String.valueOf(LYYear);

		String GeneratedSalesTotal = data.getText("GeneratedSalesTotal");
		TotalValue = sql.executeQuery(getContext(),
				GeneratedSalesTotal.replace("Region", getContext().getStringProperty("Region"))
						.replace("Area", getContext().getStringProperty("Area"))
						.replace("store", getContext().getStringProperty("store"))
						.replace("Zone", getContext().getStringProperty("Zone")).replace("FinWeek", Week)
						.replace("FinYear", LYYr).replace("LYFinYr", LYYr)
						.replace("Division", getContext().getStringProperty("Division")));
		System.out.println("DB Value: " + TotalValue);

		String GeneratedSales = data1.getText("GeneratedSales");
		IndividualValue = sql1.executeQuery(getContext(),
				GeneratedSales.replace("Region", getContext().getStringProperty("Region"))
						.replace("Area", getContext().getStringProperty("Area"))
						.replace("store", getContext().getStringProperty("store"))
						.replace("Zone", getContext().getStringProperty("Zone")).replace("FinWeek", Week)
						.replace("FinYear", LYYr).replace("LYFinYr", LYYr)
						.replace("Division", getContext().getStringProperty("Division")));
		System.out.println("DB Total: " + IndividualValue);

		pageDBComparisionPO.CompareValues(extentTest, TotalValue, IndividualValue, "Generated Sales");
	}

	@Test(description = "Verify Group Total Value and Respective Group Value in Zone Screen - PlannedSales", priority = 11)
	public void PlannedSales_CurrentMonth() throws Exception {
		pageWeeklyPlanningPO = new WeeklyPlanningStorePage().createPage(getDriver(), getContext(), log, status, data,
				getReport(), getReportLogger(), WeeklyPlanningStorePage.class);
		pageMonthlyPlanningGroupPO = new WeeklyPlanningStorePage().createPage(getDriver(), getContext(), log, status,
				data, getReport(), getReportLogger(), MonthlyPlanningGroupPage.class);
		TestStatus status = getTestStatus();
		System.out.println("Entering into TestCase");
		pageWeeklyPlanningPO.VerifyUserName(objWeeklyPlanningStoreObjects, extentTest,
				getContext().getStringProperty("username"));
		pageWeeklyPlanningPO.SelectWeekFromDropdown(objWeeklyPlanningStoreObjects, extentTest,
				getContext().getStringProperty("SelectFiscalWeek"));
		pageWeeklyPlanningPO.SelectPage(objWeeklyPlanningStoreObjects,
				getContext().getStringProperty("WeeklyPlanningGroup"), extentTest);
		pageWeeklyPlanningPO.SelectHistoryDropdownToView(objWeeklyPlanningStoreObjects, extentTest, "Sales");
		pageMonthlyPlanningGroupPO.SelectGroupDropdownToView(objMonthlyPlanningGroupObjects, extentTest,
				getContext().getStringProperty("GroupToView"));
		OptionToView = getContext().getStringProperty("OptionToView");
		if (OptionToView.contains("Budget"))
			Name = pageWeeklyPlanningPO.MonthName(objWeeklyPlanningStoreObjects, extentTest,
					objWeeklyPlanningStoreObjects.CurrentWeekBGDT);
		if (OptionToView.contains("Forecast"))
			Name = pageWeeklyPlanningPO.MonthName(objWeeklyPlanningStoreObjects, extentTest,
					objWeeklyPlanningStoreObjects.CurrentWeekFCST);
		WeekName = Name.split(" ");
		Week = WeekName[0];
		Year = WeekName[4];
		LYYear = Integer.parseInt(Year) + 1;
		LYYr = String.valueOf(LYYear);

		String PlannedSalesTotal = data.getText("PlannedSalesTotal");
		TotalValue = sql.executeQuery(getContext(),
				PlannedSalesTotal.replace("Region", getContext().getStringProperty("Region"))
						.replace("Area", getContext().getStringProperty("Area"))
						.replace("store", getContext().getStringProperty("store"))
						.replace("Zone", getContext().getStringProperty("Zone")).replace("FinWeek", Week)
						.replace("FinYear", LYYr).replace("LYFinYr", LYYr)
						.replace("Division", getContext().getStringProperty("Division")));
		System.out.println("DB Value: " + TotalValue);

		String PlannedSales = data1.getText("PlannedSales");
		IndividualValue = sql1.executeQuery(getContext(),
				PlannedSales.replace("Region", getContext().getStringProperty("Region"))
						.replace("Area", getContext().getStringProperty("Area"))
						.replace("store", getContext().getStringProperty("store"))
						.replace("Zone", getContext().getStringProperty("Zone")).replace("FinWeek", Week)
						.replace("FinYear", LYYr).replace("LYFinYr", LYYr)
						.replace("Division", getContext().getStringProperty("Division")));
		System.out.println("DB Total: " + IndividualValue);

		pageDBComparisionPO.CompareValues(extentTest, TotalValue, IndividualValue, "Planned Sales");
	}

	@Test(description = "Verify Group Total Value and Respective Group Value in Zone Screen - VarToBUD_FCST Sales", priority = 12)
	public void VarToBUDSales_CurrentMonth() throws Exception {
		pageWeeklyPlanningPO = new WeeklyPlanningStorePage().createPage(getDriver(), getContext(), log, status, data,
				getReport(), getReportLogger(), WeeklyPlanningStorePage.class);
		pageMonthlyPlanningGroupPO = new WeeklyPlanningStorePage().createPage(getDriver(), getContext(), log, status,
				data, getReport(), getReportLogger(), MonthlyPlanningGroupPage.class);
		TestStatus status = getTestStatus();
		System.out.println("Entering into TestCase");
		pageMonthlyPlanningGroupPO.SelectGroupDropdownToView(objMonthlyPlanningGroupObjects, extentTest,
				getContext().getStringProperty("GroupToView"));
		OptionToView = getContext().getStringProperty("OptionToView");
		if (OptionToView.contains("Budget"))
			Name = pageWeeklyPlanningPO.MonthName(objWeeklyPlanningStoreObjects, extentTest,
					objWeeklyPlanningStoreObjects.CurrentWeekBGDT);
		if (OptionToView.contains("Forecast"))
			Name = pageWeeklyPlanningPO.MonthName(objWeeklyPlanningStoreObjects, extentTest,
					objWeeklyPlanningStoreObjects.CurrentWeekFCST);
		WeekName = Name.split(" ");
		Week = WeekName[0];
		Year = WeekName[4];
		LYYear = Integer.parseInt(Year) + 1;
		LYYr = String.valueOf(LYYear);

		if (OptionToView.contains("Budget")) {
			String VartoBUDTotal = data.getText("VartoBUDTotal");
			TotalValue = sql.executeQuery(getContext(),
					VartoBUDTotal.replace("Region", getContext().getStringProperty("Region"))
							.replace("Area", getContext().getStringProperty("Area"))
							.replace("store", getContext().getStringProperty("store"))
							.replace("Zone", getContext().getStringProperty("Zone")).replace("FinWeek", Week)
							.replace("FinYear", LYYr).replace("LYFinYr", LYYr)
							.replace("Division", getContext().getStringProperty("Division")));
			System.out.println("DB Value: " + TotalValue);

			String VartoBUD = data1.getText("VartoBUD");
			IndividualValue = sql1.executeQuery(getContext(),
					VartoBUD.replace("Region", getContext().getStringProperty("Region"))
							.replace("Area", getContext().getStringProperty("Area"))
							.replace("store", getContext().getStringProperty("store"))
							.replace("Zone", getContext().getStringProperty("Zone")).replace("FinWeek", Week)
							.replace("FinYear", LYYr).replace("LYFinYr", LYYr)
							.replace("Division", getContext().getStringProperty("Division")));
			System.out.println("DB Total: " + IndividualValue);

			pageDBComparisionPO.CompareValues(extentTest, TotalValue, IndividualValue, "Var To BUD Sales");
		}

		if (OptionToView.contains("Forecast")) {
			String VartoFCSTTotal = data.getText("VartoFCSTTotal");
			TotalValue = sql.executeQuery(getContext(),
					VartoFCSTTotal.replace("Region", getContext().getStringProperty("Region"))
							.replace("Area", getContext().getStringProperty("Area"))
							.replace("store", getContext().getStringProperty("store"))
							.replace("Zone", getContext().getStringProperty("Zone")).replace("FinWeek", Week)
							.replace("FinYear", LYYr).replace("LYFinYr", LYYr)
							.replace("Division", getContext().getStringProperty("Division")));
			System.out.println("DB Value: " + TotalValue);

			String VartoFCST = data1.getText("VartoFCST");
			IndividualValue = sql1.executeQuery(getContext(),
					VartoFCST.replace("Region", getContext().getStringProperty("Region"))
							.replace("Area", getContext().getStringProperty("Area"))
							.replace("store", getContext().getStringProperty("store"))
							.replace("Zone", getContext().getStringProperty("Zone")).replace("FinWeek", Week)
							.replace("FinYear", LYYr).replace("LYFinYr", LYYr)
							.replace("Division", getContext().getStringProperty("Division")));
			System.out.println("DB Total: " + IndividualValue);

			pageDBComparisionPO.CompareValues(extentTest, TotalValue, IndividualValue, "Var To FCST Sales");
		}
	}

	@Test(description = "Verify Group Total Value and Respective Group Value in Zone Screen - LYPercentage Sales", priority = 13)
	public void PercentageLYsales_CurrentMonth() throws Exception {
		pageWeeklyPlanningPO = new WeeklyPlanningStorePage().createPage(getDriver(), getContext(), log, status, data,
				getReport(), getReportLogger(), WeeklyPlanningStorePage.class);
		pageMonthlyPlanningGroupPO = new WeeklyPlanningStorePage().createPage(getDriver(), getContext(), log, status,
				data, getReport(), getReportLogger(), MonthlyPlanningGroupPage.class);
		TestStatus status = getTestStatus();
		System.out.println("Entering into TestCase");
		pageWeeklyPlanningPO.VerifyUserName(objWeeklyPlanningStoreObjects, extentTest,
				getContext().getStringProperty("username"));
		pageWeeklyPlanningPO.SelectWeekFromDropdown(objWeeklyPlanningStoreObjects, extentTest,
				getContext().getStringProperty("SelectFiscalWeek"));
		pageWeeklyPlanningPO.SelectPage(objWeeklyPlanningStoreObjects,
				getContext().getStringProperty("WeeklyPlanningGroup"), extentTest);
		pageWeeklyPlanningPO.SelectHistoryDropdownToView(objWeeklyPlanningStoreObjects, extentTest, "Sales");
		pageMonthlyPlanningGroupPO.SelectGroupDropdownToView(objMonthlyPlanningGroupObjects, extentTest,
				getContext().getStringProperty("GroupToView"));
		OptionToView = getContext().getStringProperty("OptionToView");
		if (OptionToView.contains("Budget"))
			Name = pageWeeklyPlanningPO.MonthName(objWeeklyPlanningStoreObjects, extentTest,
					objWeeklyPlanningStoreObjects.CurrentWeekBGDT);
		if (OptionToView.contains("Forecast"))
			Name = pageWeeklyPlanningPO.MonthName(objWeeklyPlanningStoreObjects, extentTest,
					objWeeklyPlanningStoreObjects.CurrentWeekFCST);
		WeekName = Name.split(" ");
		Week = WeekName[0];
		Year = WeekName[4];
		LYYear = Integer.parseInt(Year) + 1;
		LYYr = String.valueOf(LYYear);

		String PercentLYSalesTotal = data.getText("PercentLYSalesTotal");
		TotalValue = sql.executeQuery(getContext(),
				PercentLYSalesTotal.replace("Region", getContext().getStringProperty("Region"))
						.replace("Area", getContext().getStringProperty("Area"))
						.replace("store", getContext().getStringProperty("store"))
						.replace("Zone", getContext().getStringProperty("Zone")).replace("FinWeek", Week)
						.replace("FinYear", LYYr).replace("LYFinYr", LYYr)
						.replace("Division", getContext().getStringProperty("Division")));
		System.out.println("DB Value: " + TotalValue);

		String PercentLYSales = data1.getText("PercentLYSales");
		IndividualValue = sql1.executeQuery(getContext(),
				PercentLYSales.replace("Region", getContext().getStringProperty("Region"))
						.replace("Area", getContext().getStringProperty("Area"))
						.replace("store", getContext().getStringProperty("store"))
						.replace("Zone", getContext().getStringProperty("Zone")).replace("FinWeek", Week)
						.replace("FinYear", LYYr).replace("LYFinYr", LYYr)
						.replace("Division", getContext().getStringProperty("Division")));
		System.out.println("DB Total: " + IndividualValue);

		pageDBComparisionPO.CompareValueDecimalFields(extentTest, TotalValue, IndividualValue, "LYPercentage Sales");
	}

	@Test(description = "Verify Group Total Value and Respective Group Value in Zone Screen - BudgetPercentage Sales", priority = 14)
	public void BudgetPercentageSales_CurrentMonth() throws Exception {
		pageWeeklyPlanningPO = new WeeklyPlanningStorePage().createPage(getDriver(), getContext(), log, status, data,
				getReport(), getReportLogger(), WeeklyPlanningStorePage.class);
		pageMonthlyPlanningGroupPO = new WeeklyPlanningStorePage().createPage(getDriver(), getContext(), log, status,
				data, getReport(), getReportLogger(), MonthlyPlanningGroupPage.class);
		TestStatus status = getTestStatus();
		System.out.println("Entering into TestCase");
		pageMonthlyPlanningGroupPO.SelectGroupDropdownToView(objMonthlyPlanningGroupObjects, extentTest,
				getContext().getStringProperty("GroupToView"));
		OptionToView = getContext().getStringProperty("OptionToView");
		if (OptionToView.contains("Budget"))
			Name = pageWeeklyPlanningPO.MonthName(objWeeklyPlanningStoreObjects, extentTest,
					objWeeklyPlanningStoreObjects.CurrentWeekBGDT);
		if (OptionToView.contains("Forecast"))
			Name = pageWeeklyPlanningPO.MonthName(objWeeklyPlanningStoreObjects, extentTest,
					objWeeklyPlanningStoreObjects.CurrentWeekFCST);
		WeekName = Name.split(" ");
		Week = WeekName[0];
		Year = WeekName[4];
		LYYear = Integer.parseInt(Year) + 1;
		LYYr = String.valueOf(LYYear);

		if (OptionToView.contains("Budget")) {
			String BudgetPercentageTotal = data.getText("BudgetPercentageTotal");
			TotalValue = sql.executeQuery(getContext(),
					BudgetPercentageTotal.replace("Region", getContext().getStringProperty("Region"))
							.replace("Area", getContext().getStringProperty("Area"))
							.replace("store", getContext().getStringProperty("store"))
							.replace("Zone", getContext().getStringProperty("Zone")).replace("FinWeek", Week)
							.replace("FinYear", LYYr).replace("LYFinYr", LYYr)
							.replace("Division", getContext().getStringProperty("Division")));
			System.out.println("DB Value: " + TotalValue);

			String BudgetPercentage = data1.getText("BudgetPercentage");
			IndividualValue = sql1.executeQuery(getContext(),
					BudgetPercentage.replace("Region", getContext().getStringProperty("Region"))
							.replace("Area", getContext().getStringProperty("Area"))
							.replace("store", getContext().getStringProperty("store"))
							.replace("Zone", getContext().getStringProperty("Zone")).replace("FinWeek", Week)
							.replace("FinYear", LYYr).replace("LYFinYr", LYYr)
							.replace("Division", getContext().getStringProperty("Division")));
			System.out.println("DB Total: " + IndividualValue);

			pageDBComparisionPO.CompareValueDecimalFields(extentTest, TotalValue, IndividualValue,
					"BudgetPercentage Sales");
		}

		if (OptionToView.contains("Forecast")) {
			String ForecastPercentageTotal = data.getText("ForecastPercentageTotal");
			TotalValue = sql.executeQuery(getContext(),
					ForecastPercentageTotal.replace("Region", getContext().getStringProperty("Region"))
							.replace("Area", getContext().getStringProperty("Area"))
							.replace("store", getContext().getStringProperty("store"))
							.replace("Zone", getContext().getStringProperty("Zone")).replace("FinWeek", Week)
							.replace("FinYear", LYYr).replace("LYFinYr", LYYr)
							.replace("Division", getContext().getStringProperty("Division")));
			System.out.println("DB Value: " + TotalValue);

			String ForecastPercentage = data1.getText("ForecastPercentage");
			IndividualValue = sql1.executeQuery(getContext(),
					ForecastPercentage.replace("Region", getContext().getStringProperty("Region"))
							.replace("Area", getContext().getStringProperty("Area"))
							.replace("store", getContext().getStringProperty("store"))
							.replace("Zone", getContext().getStringProperty("Zone")).replace("FinWeek", Week)
							.replace("FinYear", LYYr).replace("LYFinYr", LYYr)
							.replace("Division", getContext().getStringProperty("Division")));
			System.out.println("DB Total: " + IndividualValue);

			pageDBComparisionPO.CompareValueDecimalFields(extentTest, TotalValue, IndividualValue,
					"ForecastPercentage Sales");
		}
	}

	// Wages
	@Test(description = "Verify Group Total Value and Respective Group Value in Zone Screen - Budget_Forecast Wages", priority = 15)
	public void BudgetWages_CurrentMonth() throws Exception {
		pageWeeklyPlanningPO = new WeeklyPlanningStorePage().createPage(getDriver(), getContext(), log, status, data,
				getReport(), getReportLogger(), WeeklyPlanningStorePage.class);
		pageMonthlyPlanningGroupPO = new WeeklyPlanningStorePage().createPage(getDriver(), getContext(), log, status,
				data, getReport(), getReportLogger(), MonthlyPlanningGroupPage.class);
		TestStatus status = getTestStatus();
		System.out.println("Entering into TestCase");
		pageWeeklyPlanningPO.VerifyUserName(objWeeklyPlanningStoreObjects, extentTest,
				getContext().getStringProperty("username"));
		pageWeeklyPlanningPO.SelectWeekFromDropdown(objWeeklyPlanningStoreObjects, extentTest,
				getContext().getStringProperty("SelectFiscalWeek"));
		pageWeeklyPlanningPO.SelectPage(objWeeklyPlanningStoreObjects,
				getContext().getStringProperty("WeeklyPlanningGroup"), extentTest);
		pageWeeklyPlanningPO.SelectHistoryDropdownToView(objWeeklyPlanningStoreObjects, extentTest, "Sales");
		pageMonthlyPlanningGroupPO.SelectGroupDropdownToView(objMonthlyPlanningGroupObjects, extentTest,
				getContext().getStringProperty("GroupToView"));
		OptionToView = getContext().getStringProperty("OptionToView");
		if (OptionToView.contains("Budget"))
			Name = pageWeeklyPlanningPO.MonthName(objWeeklyPlanningStoreObjects, extentTest,
					objWeeklyPlanningStoreObjects.CurrentWeekBGDT);
		if (OptionToView.contains("Forecast"))
			Name = pageWeeklyPlanningPO.MonthName(objWeeklyPlanningStoreObjects, extentTest,
					objWeeklyPlanningStoreObjects.CurrentWeekFCST);
		WeekName = Name.split(" ");
		Week = WeekName[0];
		Year = WeekName[4];
		LYYear = Integer.parseInt(Year) + 1;
		LYYr = String.valueOf(LYYear);

		if (OptionToView.contains("Budget")) {
			String BudgetWagesTotal = data.getText("BudgetWagesTotal");
			TotalValue = sql.executeQuery(getContext(),
					BudgetWagesTotal.replace("Region", getContext().getStringProperty("Region"))
							.replace("Area", getContext().getStringProperty("Area"))
							.replace("store", getContext().getStringProperty("store"))
							.replace("Zone", getContext().getStringProperty("Zone")).replace("FinWeek", Week)
							.replace("FinYear", LYYr).replace("LYFinYr", LYYr)
							.replace("Division", getContext().getStringProperty("Division")));
			System.out.println("DB Value: " + TotalValue);

			String BudgetWages = data1.getText("BudgetWages");
			IndividualValue = sql1.executeQuery(getContext(),
					BudgetWages.replace("Region", getContext().getStringProperty("Region"))
							.replace("Area", getContext().getStringProperty("Area"))
							.replace("store", getContext().getStringProperty("store"))
							.replace("Zone", getContext().getStringProperty("Zone")).replace("FinWeek", Week)
							.replace("FinYear", LYYr).replace("LYFinYr", LYYr)
							.replace("Division", getContext().getStringProperty("Division")));
			System.out.println("DB Total: " + IndividualValue);

			pageDBComparisionPO.CompareValues(extentTest, TotalValue, IndividualValue, "Budget Wages");
		}

		if (OptionToView.contains("Forecast")) {
			String ForecastWagesTotal = data.getText("ForecastWagesTotal");
			TotalValue = sql.executeQuery(getContext(),
					ForecastWagesTotal.replace("Region", getContext().getStringProperty("Region"))
							.replace("Area", getContext().getStringProperty("Area"))
							.replace("store", getContext().getStringProperty("store"))
							.replace("Zone", getContext().getStringProperty("Zone")).replace("FinWeek", Week)
							.replace("FinYear", LYYr).replace("LYFinYr", LYYr)
							.replace("Division", getContext().getStringProperty("Division")));
			System.out.println("DB Value: " + TotalValue);

			String ForecastWages = data1.getText("ForecastWages");
			IndividualValue = sql1.executeQuery(getContext(),
					ForecastWages.replace("Region", getContext().getStringProperty("Region"))
							.replace("Area", getContext().getStringProperty("Area"))
							.replace("store", getContext().getStringProperty("store"))
							.replace("Zone", getContext().getStringProperty("Zone")).replace("FinWeek", Week)
							.replace("FinYear", LYYr).replace("LYFinYr", LYYr)
							.replace("Division", getContext().getStringProperty("Division")));
			System.out.println("DB Total: " + IndividualValue);

			pageDBComparisionPO.CompareValues(extentTest, TotalValue, IndividualValue, "Forecast Wages");
		}
	}

	@Test(description = "Verify Group Total Value and Respective Group Value in Zone Screen - Planned Wages", priority = 16)
	public void PlannedWages_CurrentMonth() throws Exception {
		pageWeeklyPlanningPO = new WeeklyPlanningStorePage().createPage(getDriver(), getContext(), log, status, data,
				getReport(), getReportLogger(), WeeklyPlanningStorePage.class);
		pageMonthlyPlanningGroupPO = new WeeklyPlanningStorePage().createPage(getDriver(), getContext(), log, status,
				data, getReport(), getReportLogger(), MonthlyPlanningGroupPage.class);
		TestStatus status = getTestStatus();
		System.out.println("Entering into TestCase");
		pageMonthlyPlanningGroupPO.SelectGroupDropdownToView(objMonthlyPlanningGroupObjects, extentTest,
				getContext().getStringProperty("GroupToView"));
		OptionToView = getContext().getStringProperty("OptionToView");
		if (OptionToView.contains("Budget"))
			Name = pageWeeklyPlanningPO.MonthName(objWeeklyPlanningStoreObjects, extentTest,
					objWeeklyPlanningStoreObjects.CurrentWeekBGDT);
		if (OptionToView.contains("Forecast"))
			Name = pageWeeklyPlanningPO.MonthName(objWeeklyPlanningStoreObjects, extentTest,
					objWeeklyPlanningStoreObjects.CurrentWeekFCST);
		WeekName = Name.split(" ");
		Week = WeekName[0];
		Year = WeekName[4];
		LYYear = Integer.parseInt(Year) + 1;
		LYYr = String.valueOf(LYYear);

		String PlannedWagesTotal = data.getText("PlannedWagesTotal");
		TotalValue = sql.executeQuery(getContext(),
				PlannedWagesTotal.replace("Region", getContext().getStringProperty("Region"))
						.replace("Area", getContext().getStringProperty("Area"))
						.replace("store", getContext().getStringProperty("store"))
						.replace("Zone", getContext().getStringProperty("Zone")).replace("FinWeek", Week)
						.replace("FinYear", LYYr).replace("LYFinYr", LYYr)
						.replace("Division", getContext().getStringProperty("Division")));
		System.out.println("DB Value: " + TotalValue);

		String PlannedWages = data1.getText("PlannedWages");
		IndividualValue = sql1.executeQuery(getContext(),
				PlannedWages.replace("Region", getContext().getStringProperty("Region"))
						.replace("Area", getContext().getStringProperty("Area"))
						.replace("store", getContext().getStringProperty("store"))
						.replace("Zone", getContext().getStringProperty("Zone")).replace("FinWeek", Week)
						.replace("FinYear", LYYr).replace("LYFinYr", LYYr)
						.replace("Division", getContext().getStringProperty("Division")));
		System.out.println("DB Total: " + IndividualValue);

		pageDBComparisionPO.CompareValues(extentTest, TotalValue, IndividualValue, "Planned Wages");
	}

	@Test(description = "Verify Group Total Value and Respective Group Value in Zone Screen - LYPercentage Wages", priority = 17)
	public void PlannedLYWages_CurrentMonth() throws Exception {
		pageWeeklyPlanningPO = new WeeklyPlanningStorePage().createPage(getDriver(), getContext(), log, status, data,
				getReport(), getReportLogger(), WeeklyPlanningStorePage.class);
		pageMonthlyPlanningGroupPO = new WeeklyPlanningStorePage().createPage(getDriver(), getContext(), log, status,
				data, getReport(), getReportLogger(), MonthlyPlanningGroupPage.class);
		TestStatus status = getTestStatus();
		System.out.println("Entering into TestCase");
		pageMonthlyPlanningGroupPO.SelectGroupDropdownToView(objMonthlyPlanningGroupObjects, extentTest,
				getContext().getStringProperty("GroupToView"));
		OptionToView = getContext().getStringProperty("OptionToView");
		if (OptionToView.contains("Budget"))
			Name = pageWeeklyPlanningPO.MonthName(objWeeklyPlanningStoreObjects, extentTest,
					objWeeklyPlanningStoreObjects.CurrentWeekBGDT);
		if (OptionToView.contains("Forecast"))
			Name = pageWeeklyPlanningPO.MonthName(objWeeklyPlanningStoreObjects, extentTest,
					objWeeklyPlanningStoreObjects.CurrentWeekFCST);
		WeekName = Name.split(" ");
		Week = WeekName[0];
		Year = WeekName[4];
		LYYear = Integer.parseInt(Year) + 1;
		LYYr = String.valueOf(LYYear);

		String PlannedLYWagesTotal = data.getText("PlannedLYWagesTotal");
		TotalValue = sql.executeQuery(getContext(),
				PlannedLYWagesTotal.replace("Region", getContext().getStringProperty("Region"))
						.replace("Area", getContext().getStringProperty("Area"))
						.replace("store", getContext().getStringProperty("store"))
						.replace("Zone", getContext().getStringProperty("Zone")).replace("FinWeek", Week)
						.replace("FinYear", LYYr).replace("LYFinYr", LYYr)
						.replace("Division", getContext().getStringProperty("Division")));
		System.out.println("DB Value: " + TotalValue);

		String PlannedLYWages = data1.getText("PlannedLYWages");
		IndividualValue = sql1.executeQuery(getContext(),
				PlannedLYWages.replace("Region", getContext().getStringProperty("Region"))
						.replace("Area", getContext().getStringProperty("Area"))
						.replace("store", getContext().getStringProperty("store"))
						.replace("Zone", getContext().getStringProperty("Zone")).replace("FinWeek", Week)
						.replace("FinYear", LYYr).replace("LYFinYr", LYYr)
						.replace("Division", getContext().getStringProperty("Division")));
		System.out.println("DB Total: " + IndividualValue);

		pageDBComparisionPO.CompareValueDecimalFields(extentTest, TotalValue, IndividualValue, "LYPercentage Wages");
	}

	// Wages %
	@Test(description = "Verify Group Total Value and Respective Group Value in Zone Screen - Budget_Forecast Wages %", priority = 18)
	public void BudgetWagesPercentage_CurrentMonth() throws Exception {
		pageWeeklyPlanningPO = new WeeklyPlanningStorePage().createPage(getDriver(), getContext(), log, status, data,
				getReport(), getReportLogger(), WeeklyPlanningStorePage.class);
		pageMonthlyPlanningGroupPO = new WeeklyPlanningStorePage().createPage(getDriver(), getContext(), log, status,
				data, getReport(), getReportLogger(), MonthlyPlanningGroupPage.class);
		TestStatus status = getTestStatus();
		System.out.println("Entering into TestCase");
		pageWeeklyPlanningPO.VerifyUserName(objWeeklyPlanningStoreObjects, extentTest,
				getContext().getStringProperty("username"));
		pageWeeklyPlanningPO.SelectWeekFromDropdown(objWeeklyPlanningStoreObjects, extentTest,
				getContext().getStringProperty("SelectFiscalWeek"));
		pageWeeklyPlanningPO.SelectPage(objWeeklyPlanningStoreObjects,
				getContext().getStringProperty("WeeklyPlanningGroup"), extentTest);
		pageWeeklyPlanningPO.SelectHistoryDropdownToView(objWeeklyPlanningStoreObjects, extentTest, "Sales");
		pageMonthlyPlanningGroupPO.SelectGroupDropdownToView(objMonthlyPlanningGroupObjects, extentTest,
				getContext().getStringProperty("GroupToView"));
		OptionToView = getContext().getStringProperty("OptionToView");
		if (OptionToView.contains("Budget"))
			Name = pageWeeklyPlanningPO.MonthName(objWeeklyPlanningStoreObjects, extentTest,
					objWeeklyPlanningStoreObjects.CurrentWeekBGDT);
		if (OptionToView.contains("Forecast"))
			Name = pageWeeklyPlanningPO.MonthName(objWeeklyPlanningStoreObjects, extentTest,
					objWeeklyPlanningStoreObjects.CurrentWeekFCST);
		WeekName = Name.split(" ");
		Week = WeekName[0];
		Year = WeekName[4];
		LYYear = Integer.parseInt(Year) + 1;
		LYYr = String.valueOf(LYYear);

		if (OptionToView.contains("Budget")) {
			String BudgetWagesPercentageTotal = data.getText("BudgetWagesPercentageTotal");
			TotalValue = sql.executeQuery(getContext(),
					BudgetWagesPercentageTotal.replace("Region", getContext().getStringProperty("Region"))
							.replace("Area", getContext().getStringProperty("Area"))
							.replace("store", getContext().getStringProperty("store"))
							.replace("Zone", getContext().getStringProperty("Zone")).replace("FinWeek", Week)
							.replace("FinYear", LYYr).replace("LYFinYr", LYYr)
							.replace("Division", getContext().getStringProperty("Division")));
			System.out.println("DB Value: " + TotalValue);

			String BudgetWagesPercentage = data1.getText("BudgetWagesPercentage");
			IndividualValue = sql1.executeQuery(getContext(),
					BudgetWagesPercentage.replace("Region", getContext().getStringProperty("Region"))
							.replace("Area", getContext().getStringProperty("Area"))
							.replace("store", getContext().getStringProperty("store"))
							.replace("Zone", getContext().getStringProperty("Zone")).replace("FinWeek", Week)
							.replace("FinYear", LYYr).replace("LYFinYr", LYYr)
							.replace("Division", getContext().getStringProperty("Division")));
			System.out.println("DB Total: " + IndividualValue);

			pageDBComparisionPO.CompareValueDecimalFields(extentTest, TotalValue, IndividualValue, "Budget Wages%");
		}

		if (OptionToView.contains("Forecast")) {
			String ForecastWagesPercentageTotal = data.getText("ForecastWagesPercentageTotal");
			TotalValue = sql.executeQuery(getContext(),
					ForecastWagesPercentageTotal.replace("Region", getContext().getStringProperty("Region"))
							.replace("Area", getContext().getStringProperty("Area"))
							.replace("store", getContext().getStringProperty("store"))
							.replace("Zone", getContext().getStringProperty("Zone")).replace("FinWeek", Week)
							.replace("FinYear", LYYr).replace("LYFinYr", LYYr)
							.replace("Division", getContext().getStringProperty("Division")));
			System.out.println("DB Value: " + TotalValue);

			String ForecastWagesPercentage = data1.getText("ForecastWagesPercentage");
			IndividualValue = sql1.executeQuery(getContext(),
					ForecastWagesPercentage.replace("Region", getContext().getStringProperty("Region"))
							.replace("Area", getContext().getStringProperty("Area"))
							.replace("store", getContext().getStringProperty("store"))
							.replace("Zone", getContext().getStringProperty("Zone")).replace("FinWeek", Week)
							.replace("FinYear", LYYr).replace("LYFinYr", LYYr)
							.replace("Division", getContext().getStringProperty("Division")));
			System.out.println("DB Total: " + IndividualValue);

			pageDBComparisionPO.CompareValueDecimalFields(extentTest, TotalValue, IndividualValue, "Forecast Wages%");
		}
	}

	@Test(description = "Verify Group Total Value and Respective Group Value in Zone Screen - Planned Wages %", priority = 19)
	public void PlannedWagesPercentage_CurrentMonth() throws Exception {
		pageWeeklyPlanningPO = new WeeklyPlanningStorePage().createPage(getDriver(), getContext(), log, status, data,
				getReport(), getReportLogger(), WeeklyPlanningStorePage.class);
		pageMonthlyPlanningGroupPO = new WeeklyPlanningStorePage().createPage(getDriver(), getContext(), log, status,
				data, getReport(), getReportLogger(), MonthlyPlanningGroupPage.class);
		TestStatus status = getTestStatus();
		System.out.println("Entering into TestCase");
		pageMonthlyPlanningGroupPO.SelectGroupDropdownToView(objMonthlyPlanningGroupObjects, extentTest,
				getContext().getStringProperty("GroupToView"));
		OptionToView = getContext().getStringProperty("OptionToView");
		if (OptionToView.contains("Budget"))
			Name = pageWeeklyPlanningPO.MonthName(objWeeklyPlanningStoreObjects, extentTest,
					objWeeklyPlanningStoreObjects.CurrentWeekBGDT);
		if (OptionToView.contains("Forecast"))
			Name = pageWeeklyPlanningPO.MonthName(objWeeklyPlanningStoreObjects, extentTest,
					objWeeklyPlanningStoreObjects.CurrentWeekFCST);
		WeekName = Name.split(" ");
		Week = WeekName[0];
		Year = WeekName[4];
		LYYear = Integer.parseInt(Year) + 1;
		LYYr = String.valueOf(LYYear);

		String PlannedWagesPercentageTotal = data.getText("PlannedWagesPercentageTotal");
		TotalValue = sql.executeQuery(getContext(),
				PlannedWagesPercentageTotal.replace("Region", getContext().getStringProperty("Region"))
						.replace("Area", getContext().getStringProperty("Area"))
						.replace("store", getContext().getStringProperty("store"))
						.replace("Zone", getContext().getStringProperty("Zone")).replace("FinWeek", Week)
						.replace("FinYear", LYYr).replace("LYFinYr", LYYr)
						.replace("Division", getContext().getStringProperty("Division")));
		System.out.println("DB Value: " + TotalValue);

		String PlannedWagesPercentage = data1.getText("PlannedWagesPercentage");
		IndividualValue = sql1.executeQuery(getContext(),
				PlannedWagesPercentage.replace("Region", getContext().getStringProperty("Region"))
						.replace("Area", getContext().getStringProperty("Area"))
						.replace("store", getContext().getStringProperty("store"))
						.replace("Zone", getContext().getStringProperty("Zone")).replace("FinWeek", Week)
						.replace("FinYear", LYYr).replace("LYFinYr", LYYr)
						.replace("Division", getContext().getStringProperty("Division")));
		System.out.println("DB Total: " + IndividualValue);

		pageDBComparisionPO.CompareValueDecimalFields(extentTest, TotalValue, IndividualValue, "Planned Wages %");

	}

	@Test(description = "Verify Group Total Value and Respective Group Value in Zone Screen - LY Wages%", priority = 20)
	public void LYWagesPercentage_CurrentMonth() throws Exception {
		pageWeeklyPlanningPO = new WeeklyPlanningStorePage().createPage(getDriver(), getContext(), log, status, data,
				getReport(), getReportLogger(), WeeklyPlanningStorePage.class);
		pageMonthlyPlanningGroupPO = new WeeklyPlanningStorePage().createPage(getDriver(), getContext(), log, status,
				data, getReport(), getReportLogger(), MonthlyPlanningGroupPage.class);
		TestStatus status = getTestStatus();
		System.out.println("Entering into TestCase");
		pageMonthlyPlanningGroupPO.SelectGroupDropdownToView(objMonthlyPlanningGroupObjects, extentTest,
				getContext().getStringProperty("GroupToView"));
		OptionToView = getContext().getStringProperty("OptionToView");
		if (OptionToView.contains("Budget"))
			Name = pageWeeklyPlanningPO.MonthName(objWeeklyPlanningStoreObjects, extentTest,
					objWeeklyPlanningStoreObjects.CurrentWeekBGDT);
		if (OptionToView.contains("Forecast"))
			Name = pageWeeklyPlanningPO.MonthName(objWeeklyPlanningStoreObjects, extentTest,
					objWeeklyPlanningStoreObjects.CurrentWeekFCST);
		WeekName = Name.split(" ");
		Week = WeekName[0];
		Year = WeekName[4];
		LYYear = Integer.parseInt(Year) + 1;
		LYYr = String.valueOf(LYYear);

		String LYWagesPercentageTotal = data.getText("LYWagesPercentageTotal");
		TotalValue = sql.executeQuery(getContext(),
				LYWagesPercentageTotal.replace("Region", getContext().getStringProperty("Region"))
						.replace("Area", getContext().getStringProperty("Area"))
						.replace("store", getContext().getStringProperty("store"))
						.replace("Zone", getContext().getStringProperty("Zone")).replace("FinWeek", Week)
						.replace("FinYear", LYYr).replace("LYFinYr", LYYr)
						.replace("Division", getContext().getStringProperty("Division")));
		System.out.println("DB Value: " + TotalValue);

		String LYWagesPercentage = data1.getText("LYWagesPercentage");
		IndividualValue = sql1.executeQuery(getContext(),
				LYWagesPercentage.replace("Region", getContext().getStringProperty("Region"))
						.replace("Area", getContext().getStringProperty("Area"))
						.replace("store", getContext().getStringProperty("store"))
						.replace("Zone", getContext().getStringProperty("Zone")).replace("FinWeek", Week)
						.replace("FinYear", LYYr).replace("LYFinYr", LYYr)
						.replace("Division", getContext().getStringProperty("Division")));
		System.out.println("DB Total: " + IndividualValue);

		pageDBComparisionPO.CompareValueDecimalFields(extentTest, TotalValue, IndividualValue, "LY Wages %");

	}

	// Hours
	@Test(description = "Verify Group Total Value and Respective Group Value in Zone Screen - SMS Hours", priority = 21)
	public void SMSHours_CurrentMonth() throws Exception {
		pageWeeklyPlanningPO = new WeeklyPlanningStorePage().createPage(getDriver(), getContext(), log, status, data,
				getReport(), getReportLogger(), WeeklyPlanningStorePage.class);
		pageMonthlyPlanningGroupPO = new WeeklyPlanningStorePage().createPage(getDriver(), getContext(), log, status,
				data, getReport(), getReportLogger(), MonthlyPlanningGroupPage.class);
		TestStatus status = getTestStatus();
		System.out.println("Entering into TestCase");
		pageMonthlyPlanningGroupPO.SelectGroupDropdownToView(objMonthlyPlanningGroupObjects, extentTest,
				getContext().getStringProperty("GroupToView"));
		OptionToView = getContext().getStringProperty("OptionToView");
		if (OptionToView.contains("Budget"))
			Name = pageWeeklyPlanningPO.MonthName(objWeeklyPlanningStoreObjects, extentTest,
					objWeeklyPlanningStoreObjects.CurrentWeekBGDT);
		if (OptionToView.contains("Forecast"))
			Name = pageWeeklyPlanningPO.MonthName(objWeeklyPlanningStoreObjects, extentTest,
					objWeeklyPlanningStoreObjects.CurrentWeekFCST);
		WeekName = Name.split(" ");
		Week = WeekName[0];
		Year = WeekName[4];
		LYYear = Integer.parseInt(Year) + 1;
		LYYr = String.valueOf(LYYear);

		String SMSHoursTotal = data.getText("SMSHoursTotal");
		TotalValue = sql.executeQuery(getContext(),
				SMSHoursTotal.replace("Region", getContext().getStringProperty("Region"))
						.replace("Area", getContext().getStringProperty("Area"))
						.replace("store", getContext().getStringProperty("store"))
						.replace("Zone", getContext().getStringProperty("Zone")).replace("FinWeek", Week)
						.replace("FinYear", LYYr).replace("LYFinYr", LYYr)
						.replace("Division", getContext().getStringProperty("Division")));
		System.out.println("DB Value: " + TotalValue);

		String SMSHours = data1.getText("SMSHours");
		IndividualValue = sql1.executeQuery(getContext(),
				SMSHours.replace("Region", getContext().getStringProperty("Region"))
						.replace("Area", getContext().getStringProperty("Area"))
						.replace("store", getContext().getStringProperty("store"))
						.replace("Zone", getContext().getStringProperty("Zone")).replace("FinWeek", Week)
						.replace("FinYear", LYYr).replace("LYFinYr", LYYr)
						.replace("Division", getContext().getStringProperty("Division")));
		System.out.println("DB Total: " + IndividualValue);

		pageDBComparisionPO.CompareValues(extentTest, TotalValue, IndividualValue, "SMS Hours");

	}

	@Test(description = "Verify Group Total Value and Respective Group Value in Zone Screen - Base Hours", priority = 22)
	public void PlannedHours_CurrentMonth() throws Exception {
		pageWeeklyPlanningPO = new WeeklyPlanningStorePage().createPage(getDriver(), getContext(), log, status, data,
				getReport(), getReportLogger(), WeeklyPlanningStorePage.class);
		pageMonthlyPlanningGroupPO = new WeeklyPlanningStorePage().createPage(getDriver(), getContext(), log, status,
				data, getReport(), getReportLogger(), MonthlyPlanningGroupPage.class);
		TestStatus status = getTestStatus();
		System.out.println("Entering into TestCase");
		pageMonthlyPlanningGroupPO.SelectGroupDropdownToView(objMonthlyPlanningGroupObjects, extentTest,
				getContext().getStringProperty("GroupToView"));
		OptionToView = getContext().getStringProperty("OptionToView");
		if (OptionToView.contains("Budget"))
			Name = pageWeeklyPlanningPO.MonthName(objWeeklyPlanningStoreObjects, extentTest,
					objWeeklyPlanningStoreObjects.CurrentWeekBGDT);
		if (OptionToView.contains("Forecast"))
			Name = pageWeeklyPlanningPO.MonthName(objWeeklyPlanningStoreObjects, extentTest,
					objWeeklyPlanningStoreObjects.CurrentWeekFCST);
		WeekName = Name.split(" ");
		Week = WeekName[0];
		Year = WeekName[4];
		LYYear = Integer.parseInt(Year) + 1;
		LYYr = String.valueOf(LYYear);

		String PlannedHoursTotal = data.getText("PlannedHoursTotal");
		TotalValue = sql.executeQuery(getContext(),
				PlannedHoursTotal.replace("Region", getContext().getStringProperty("Region"))
						.replace("Area", getContext().getStringProperty("Area"))
						.replace("store", getContext().getStringProperty("store"))
						.replace("Zone", getContext().getStringProperty("Zone")).replace("FinWeek", Week)
						.replace("FinYear", LYYr).replace("LYFinYr", LYYr)
						.replace("Division", getContext().getStringProperty("Division")));
		System.out.println("DB Value: " + TotalValue);

		String PlannedHours = data1.getText("PlannedHours");
		IndividualValue = sql1.executeQuery(getContext(),
				PlannedHours.replace("Region", getContext().getStringProperty("Region"))
						.replace("Area", getContext().getStringProperty("Area"))
						.replace("store", getContext().getStringProperty("store"))
						.replace("Zone", getContext().getStringProperty("Zone")).replace("FinWeek", Week)
						.replace("FinYear", LYYr).replace("LYFinYr", LYYr)
						.replace("Division", getContext().getStringProperty("Division")));
		System.out.println("DB Total: " + IndividualValue);

		pageDBComparisionPO.CompareValues(extentTest, TotalValue, IndividualValue, "Base Hours");

	}

	@Test(description = "Verify Group Total Value and Respective Group Value in Zone Screen - Planned Hours", priority = 23)
	public void HoursLY_CurrentMonth() throws Exception {
		pageWeeklyPlanningPO = new WeeklyPlanningStorePage().createPage(getDriver(), getContext(), log, status, data,
				getReport(), getReportLogger(), WeeklyPlanningStorePage.class);
		pageMonthlyPlanningGroupPO = new WeeklyPlanningStorePage().createPage(getDriver(), getContext(), log, status,
				data, getReport(), getReportLogger(), MonthlyPlanningGroupPage.class);
		TestStatus status = getTestStatus();
		System.out.println("Entering into TestCase");
		pageMonthlyPlanningGroupPO.SelectGroupDropdownToView(objMonthlyPlanningGroupObjects, extentTest,
				getContext().getStringProperty("GroupToView"));
		OptionToView = getContext().getStringProperty("OptionToView");
		if (OptionToView.contains("Budget"))
			Name = pageWeeklyPlanningPO.MonthName(objWeeklyPlanningStoreObjects, extentTest,
					objWeeklyPlanningStoreObjects.CurrentWeekBGDT);
		if (OptionToView.contains("Forecast"))
			Name = pageWeeklyPlanningPO.MonthName(objWeeklyPlanningStoreObjects, extentTest,
					objWeeklyPlanningStoreObjects.CurrentWeekFCST);
		WeekName = Name.split(" ");
		Week = WeekName[0];
		Year = WeekName[4];
		LYYear = Integer.parseInt(Year) + 1;
		LYYr = String.valueOf(LYYear);

		String HoursLYTotal = data.getText("HoursLYTotal");
		TotalValue = sql.executeQuery(getContext(),
				HoursLYTotal.replace("Region", getContext().getStringProperty("Region"))
						.replace("Area", getContext().getStringProperty("Area"))
						.replace("store", getContext().getStringProperty("store"))
						.replace("Zone", getContext().getStringProperty("Zone")).replace("FinWeek", Week)
						.replace("FinYear", LYYr).replace("LYFinYr", LYYr)
						.replace("Division", getContext().getStringProperty("Division")));
		System.out.println("DB Value: " + TotalValue);

		String HoursLY = data1.getText("HoursLY");
		IndividualValue = sql1.executeQuery(getContext(),
				HoursLY.replace("Region", getContext().getStringProperty("Region"))
						.replace("Area", getContext().getStringProperty("Area"))
						.replace("store", getContext().getStringProperty("store"))
						.replace("Zone", getContext().getStringProperty("Zone")).replace("FinWeek", Week)
						.replace("FinYear", LYYr).replace("LYFinYr", LYYr)
						.replace("Division", getContext().getStringProperty("Division")));
		System.out.println("DB Total: " + IndividualValue);

		pageDBComparisionPO.CompareValues(extentTest, TotalValue, IndividualValue, "Planned Hours");
	}

	// OR
	@Test(description = "Verify Group Total Value and Respective Group Value in Zone Screen - Planned OR", priority = 24)
	public void PlannedOR_CurrentMonth() throws Exception {
		pageWeeklyPlanningPO = new WeeklyPlanningStorePage().createPage(getDriver(), getContext(), log, status, data,
				getReport(), getReportLogger(), WeeklyPlanningStorePage.class);
		pageMonthlyPlanningGroupPO = new WeeklyPlanningStorePage().createPage(getDriver(), getContext(), log, status,
				data, getReport(), getReportLogger(), MonthlyPlanningGroupPage.class);
		TestStatus status = getTestStatus();
		System.out.println("Entering into TestCase");
		pageMonthlyPlanningGroupPO.SelectGroupDropdownToView(objMonthlyPlanningGroupObjects, extentTest,
				getContext().getStringProperty("GroupToView"));
		OptionToView = getContext().getStringProperty("OptionToView");
		if (OptionToView.contains("Budget"))
			Name = pageWeeklyPlanningPO.MonthName(objWeeklyPlanningStoreObjects, extentTest,
					objWeeklyPlanningStoreObjects.CurrentWeekBGDT);
		if (OptionToView.contains("Forecast"))
			Name = pageWeeklyPlanningPO.MonthName(objWeeklyPlanningStoreObjects, extentTest,
					objWeeklyPlanningStoreObjects.CurrentWeekFCST);
		WeekName = Name.split(" ");
		Week = WeekName[0];
		Year = WeekName[4];
		LYYear = Integer.parseInt(Year) + 1;
		LYYr = String.valueOf(LYYear);

		String plannedORTotal = data.getText("plannedORTotal");
		TotalValue = sql.executeQuery(getContext(),
				plannedORTotal.replace("Region", getContext().getStringProperty("Region"))
						.replace("Area", getContext().getStringProperty("Area"))
						.replace("store", getContext().getStringProperty("store"))
						.replace("Zone", getContext().getStringProperty("Zone")).replace("FinWeek", Week)
						.replace("FinYear", LYYr).replace("LYFinYr", LYYr)
						.replace("Division", getContext().getStringProperty("Division")));
		System.out.println("DB Value: " + TotalValue);

		String plannedOR = data1.getText("plannedOR");
		IndividualValue = sql1.executeQuery(getContext(),
				plannedOR.replace("Region", getContext().getStringProperty("Region"))
						.replace("Area", getContext().getStringProperty("Area"))
						.replace("store", getContext().getStringProperty("store"))
						.replace("Zone", getContext().getStringProperty("Zone")).replace("FinWeek", Week)
						.replace("FinYear", LYYr).replace("LYFinYr", LYYr)
						.replace("Division", getContext().getStringProperty("Division")));
		System.out.println("DB Total: " + IndividualValue);

		pageDBComparisionPO.CompareValueDecimalFields(extentTest, TotalValue, IndividualValue, "Planned OR");

	}

	// CPH
	@Test(description = "Verify Group Total Value and Respective Group Value in Zone Screen - Planned CPH", priority = 25)
	public void PlannedCPH_CurrentMonth() throws Exception {
		pageWeeklyPlanningPO = new WeeklyPlanningStorePage().createPage(getDriver(), getContext(), log, status, data,
				getReport(), getReportLogger(), WeeklyPlanningStorePage.class);
		pageMonthlyPlanningGroupPO = new WeeklyPlanningStorePage().createPage(getDriver(), getContext(), log, status,
				data, getReport(), getReportLogger(), MonthlyPlanningGroupPage.class);
		TestStatus status = getTestStatus();
		System.out.println("Entering into TestCase");
		pageMonthlyPlanningGroupPO.SelectGroupDropdownToView(objMonthlyPlanningGroupObjects, extentTest,
				getContext().getStringProperty("GroupToView"));
		OptionToView = getContext().getStringProperty("OptionToView");
		if (OptionToView.contains("Budget"))
			Name = pageWeeklyPlanningPO.MonthName(objWeeklyPlanningStoreObjects, extentTest,
					objWeeklyPlanningStoreObjects.CurrentWeekBGDT);
		if (OptionToView.contains("Forecast"))
			Name = pageWeeklyPlanningPO.MonthName(objWeeklyPlanningStoreObjects, extentTest,
					objWeeklyPlanningStoreObjects.CurrentWeekFCST);
		WeekName = Name.split(" ");
		Week = WeekName[0];
		Year = WeekName[4];
		LYYear = Integer.parseInt(Year) + 1;
		LYYr = String.valueOf(LYYear);

		String PlannedCPHTotal = data.getText("PlannedCPHTotal");
		TotalValue = sql.executeQuery(getContext(),
				PlannedCPHTotal.replace("Region", getContext().getStringProperty("Region"))
						.replace("Area", getContext().getStringProperty("Area"))
						.replace("store", getContext().getStringProperty("store"))
						.replace("Zone", getContext().getStringProperty("Zone")).replace("FinWeek", Week)
						.replace("FinYear", LYYr).replace("LYFinYr", LYYr)
						.replace("Division", getContext().getStringProperty("Division")));
		System.out.println("DB Value: " + TotalValue);

		String PlannedCPH = data1.getText("PlannedCPH");
		IndividualValue = sql1.executeQuery(getContext(),
				PlannedCPH.replace("Region", getContext().getStringProperty("Region"))
						.replace("Area", getContext().getStringProperty("Area"))
						.replace("store", getContext().getStringProperty("store"))
						.replace("Zone", getContext().getStringProperty("Zone")).replace("FinWeek", Week)
						.replace("FinYear", LYYr).replace("LYFinYr", LYYr)
						.replace("Division", getContext().getStringProperty("Division")));
		System.out.println("DB Total: " + IndividualValue);

		pageDBComparisionPO.CompareValueDecimalFields(extentTest, TotalValue, IndividualValue, "Planned CPH");
	}

	@Test(description = "Verify Group Total Value and Respective Group Value in Zone Screen - GrowthLY CPH", priority = 26)
	public void GrowthLYCPH_CurrentMonth() throws Exception {
		pageWeeklyPlanningPO = new WeeklyPlanningStorePage().createPage(getDriver(), getContext(), log, status, data,
				getReport(), getReportLogger(), WeeklyPlanningStorePage.class);
		pageMonthlyPlanningGroupPO = new WeeklyPlanningStorePage().createPage(getDriver(), getContext(), log, status,
				data, getReport(), getReportLogger(), MonthlyPlanningGroupPage.class);
		TestStatus status = getTestStatus();
		System.out.println("Entering into TestCase");
		pageMonthlyPlanningGroupPO.SelectGroupDropdownToView(objMonthlyPlanningGroupObjects, extentTest,
				getContext().getStringProperty("GroupToView"));
		OptionToView = getContext().getStringProperty("OptionToView");
		if (OptionToView.contains("Budget"))
			Name = pageWeeklyPlanningPO.MonthName(objWeeklyPlanningStoreObjects, extentTest,
					objWeeklyPlanningStoreObjects.CurrentWeekBGDT);
		if (OptionToView.contains("Forecast"))
			Name = pageWeeklyPlanningPO.MonthName(objWeeklyPlanningStoreObjects, extentTest,
					objWeeklyPlanningStoreObjects.CurrentWeekFCST);
		WeekName = Name.split(" ");
		Week = WeekName[0];
		Year = WeekName[4];
		LYYear = Integer.parseInt(Year) + 1;
		LYYr = String.valueOf(LYYear);

		String GrowthLYCPHTotal = data.getText("GrowthLYCPHTotal");
		TotalValue = sql.executeQuery(getContext(),
				GrowthLYCPHTotal.replace("Region", getContext().getStringProperty("Region"))
						.replace("Area", getContext().getStringProperty("Area"))
						.replace("store", getContext().getStringProperty("store"))
						.replace("Zone", getContext().getStringProperty("Zone")).replace("FinWeek", Week)
						.replace("FinYear", LYYr).replace("LYFinYr", LYYr)
						.replace("Division", getContext().getStringProperty("Division")));
		System.out.println("DB Value: " + TotalValue);

		String GrowthLYCPH = data1.getText("GrowthLYCPH");
		IndividualValue = sql1.executeQuery(getContext(),
				GrowthLYCPH.replace("Region", getContext().getStringProperty("Region"))
						.replace("Area", getContext().getStringProperty("Area"))
						.replace("store", getContext().getStringProperty("store"))
						.replace("Zone", getContext().getStringProperty("Zone")).replace("FinWeek", Week)
						.replace("FinYear", LYYr).replace("LYFinYr", LYYr)
						.replace("Division", getContext().getStringProperty("Division")));
		System.out.println("DB Total: " + IndividualValue);

		pageDBComparisionPO.CompareValueDecimalFields(extentTest, TotalValue, IndividualValue, "GrowthLY CPH");
	}

	@Test(description = "Verify Group Total Value and Respective Group Value in Zone Screen - Planned Items", priority = 27)
	public void PlannedItems_CurrentMonth() throws Exception {
		pageWeeklyPlanningPO = new WeeklyPlanningStorePage().createPage(getDriver(), getContext(), log, status, data,
				getReport(), getReportLogger(), WeeklyPlanningStorePage.class);
		pageMonthlyPlanningGroupPO = new WeeklyPlanningStorePage().createPage(getDriver(), getContext(), log, status,
				data, getReport(), getReportLogger(), MonthlyPlanningGroupPage.class);
		TestStatus status = getTestStatus();
		System.out.println("Entering into TestCase");
		pageMonthlyPlanningGroupPO.SelectGroupDropdownToView(objMonthlyPlanningGroupObjects, extentTest,
				getContext().getStringProperty("GroupToView"));
		OptionToView = getContext().getStringProperty("OptionToView");
		if (OptionToView.contains("Budget"))
			Name = pageWeeklyPlanningPO.MonthName(objWeeklyPlanningStoreObjects, extentTest,
					objWeeklyPlanningStoreObjects.CurrentWeekBGDT);
		if (OptionToView.contains("Forecast"))
			Name = pageWeeklyPlanningPO.MonthName(objWeeklyPlanningStoreObjects, extentTest,
					objWeeklyPlanningStoreObjects.CurrentWeekFCST);
		WeekName = Name.split(" ");
		Week = WeekName[0];
		Year = WeekName[4];
		LYYear = Integer.parseInt(Year) + 1;
		LYYr = String.valueOf(LYYear);

		String PlannedItemsTotal = data.getText("PlannedItemsTotal");
		TotalValue = sql.executeQuery(getContext(),
				PlannedItemsTotal.replace("Region", getContext().getStringProperty("Region"))
						.replace("Area", getContext().getStringProperty("Area"))
						.replace("store", getContext().getStringProperty("store"))
						.replace("Zone", getContext().getStringProperty("Zone")).replace("FinWeek", Week)
						.replace("FinYear", LYYr).replace("LYFinYr", LYYr)
						.replace("Division", getContext().getStringProperty("Division")));
		System.out.println("DB Value: " + TotalValue);

		String PlannedItems = data1.getText("PlannedItems");
		IndividualValue = sql1.executeQuery(getContext(),
				PlannedItems.replace("Region", getContext().getStringProperty("Region"))
						.replace("Area", getContext().getStringProperty("Area"))
						.replace("store", getContext().getStringProperty("store"))
						.replace("Zone", getContext().getStringProperty("Zone")).replace("FinWeek", Week)
						.replace("FinYear", LYYr).replace("LYFinYr", LYYr)
						.replace("Division", getContext().getStringProperty("Division")));
		System.out.println("DB Total: " + IndividualValue);

		pageDBComparisionPO.CompareValues(extentTest, TotalValue, IndividualValue, "GrowthLY CPH");
	}

	@Test(description = "Verify Group Total Value and Respective Group Value in Zone Screen - PlannedIPLHitems", priority = 28)
	public void PlannedIPLHitems_CurrentMonth() throws Exception {
		pageWeeklyPlanningPO = new WeeklyPlanningStorePage().createPage(getDriver(), getContext(), log, status, data,
				getReport(), getReportLogger(), WeeklyPlanningStorePage.class);
		pageMonthlyPlanningGroupPO = new WeeklyPlanningStorePage().createPage(getDriver(), getContext(), log, status,
				data, getReport(), getReportLogger(), MonthlyPlanningGroupPage.class);
		TestStatus status = getTestStatus();
		System.out.println("Entering into TestCase");
		pageMonthlyPlanningGroupPO.SelectGroupDropdownToView(objMonthlyPlanningGroupObjects, extentTest,
				getContext().getStringProperty("GroupToView"));
		OptionToView = getContext().getStringProperty("OptionToView");
		if (OptionToView.contains("Budget"))
			Name = pageWeeklyPlanningPO.MonthName(objWeeklyPlanningStoreObjects, extentTest,
					objWeeklyPlanningStoreObjects.CurrentWeekBGDT);
		if (OptionToView.contains("Forecast"))
			Name = pageWeeklyPlanningPO.MonthName(objWeeklyPlanningStoreObjects, extentTest,
					objWeeklyPlanningStoreObjects.CurrentWeekFCST);
		WeekName = Name.split(" ");
		Week = WeekName[0];
		Year = WeekName[4];
		LYYear = Integer.parseInt(Year) + 1;
		LYYr = String.valueOf(LYYear);

		String PlannedIPLHitemsTotal = data.getText("PlannedIPLHitemsTotal");
		TotalValue = sql.executeQuery(getContext(),
				PlannedIPLHitemsTotal.replace("Region", getContext().getStringProperty("Region"))
						.replace("Area", getContext().getStringProperty("Area"))
						.replace("store", getContext().getStringProperty("store"))
						.replace("Zone", getContext().getStringProperty("Zone")).replace("FinWeek", Week)
						.replace("FinYear", LYYr).replace("LYFinYr", LYYr)
						.replace("Division", getContext().getStringProperty("Division")));
		System.out.println("DB Value: " + TotalValue);

		String PlannedIPLHitems = data1.getText("PlannedIPLHitems");
		IndividualValue = sql1.executeQuery(getContext(),
				PlannedIPLHitems.replace("Region", getContext().getStringProperty("Region"))
						.replace("Area", getContext().getStringProperty("Area"))
						.replace("store", getContext().getStringProperty("store"))
						.replace("Zone", getContext().getStringProperty("Zone")).replace("FinWeek", Week)
						.replace("FinYear", LYYr).replace("LYFinYr", LYYr)
						.replace("Division", getContext().getStringProperty("Division")));
		System.out.println("DB Total: " + IndividualValue);

		pageDBComparisionPO.CompareValues(extentTest, TotalValue, IndividualValue, "GrowthLY CPH");
	}

	@Test(description = "Verify Group Total Value and Respective Group Value in Zone Screen - Planned ItemPrice", priority = 29)
	public void PlannedItemPrice_CurrentMonth() throws Exception {
		pageWeeklyPlanningPO = new WeeklyPlanningStorePage().createPage(getDriver(), getContext(), log, status, data,
				getReport(), getReportLogger(), WeeklyPlanningStorePage.class);
		pageMonthlyPlanningGroupPO = new WeeklyPlanningStorePage().createPage(getDriver(), getContext(), log, status,
				data, getReport(), getReportLogger(), MonthlyPlanningGroupPage.class);
		TestStatus status = getTestStatus();
		System.out.println("Entering into TestCase");
		pageMonthlyPlanningGroupPO.SelectGroupDropdownToView(objMonthlyPlanningGroupObjects, extentTest,
				getContext().getStringProperty("GroupToView"));
		OptionToView = getContext().getStringProperty("OptionToView");
		if (OptionToView.contains("Budget"))
			Name = pageWeeklyPlanningPO.MonthName(objWeeklyPlanningStoreObjects, extentTest,
					objWeeklyPlanningStoreObjects.CurrentWeekBGDT);
		if (OptionToView.contains("Forecast"))
			Name = pageWeeklyPlanningPO.MonthName(objWeeklyPlanningStoreObjects, extentTest,
					objWeeklyPlanningStoreObjects.CurrentWeekFCST);
		WeekName = Name.split(" ");
		Week = WeekName[0];
		Year = WeekName[4];
		LYYear = Integer.parseInt(Year) + 1;
		LYYr = String.valueOf(LYYear);

		String PlannedItemPriceTotal = data.getText("PlannedItemPriceTotal");
		TotalValue = sql.executeQuery(getContext(),
				PlannedItemPriceTotal.replace("Region", getContext().getStringProperty("Region"))
						.replace("Area", getContext().getStringProperty("Area"))
						.replace("store", getContext().getStringProperty("store"))
						.replace("Zone", getContext().getStringProperty("Zone")).replace("FinWeek", Week)
						.replace("FinYear", LYYr).replace("LYFinYr", LYYr)
						.replace("Division", getContext().getStringProperty("Division")));
		System.out.println("DB Value: " + TotalValue);

		String PlannedItemPrice = data1.getText("PlannedItemPrice");
		IndividualValue = sql1.executeQuery(getContext(),
				PlannedItemPrice.replace("Region", getContext().getStringProperty("Region"))
						.replace("Area", getContext().getStringProperty("Area"))
						.replace("store", getContext().getStringProperty("store"))
						.replace("Zone", getContext().getStringProperty("Zone")).replace("FinWeek", Week)
						.replace("FinYear", LYYr).replace("LYFinYr", LYYr)
						.replace("Division", getContext().getStringProperty("Division")));
		System.out.println("DB Total: " + IndividualValue);

		pageDBComparisionPO.CompareValueDecimalFields(extentTest, TotalValue, IndividualValue, "GrowthLY CPH");

	}

	@Test(description = "Verify Group Total Value and Respective Group Value in Zone Screen - PlannedPercentOnLY", priority = 30)
	public void PlannedPercentOnLY_CurrentMonth() throws Exception {
		pageWeeklyPlanningPO = new WeeklyPlanningStorePage().createPage(getDriver(), getContext(), log, status, data,
				getReport(), getReportLogger(), WeeklyPlanningStorePage.class);
		pageMonthlyPlanningGroupPO = new WeeklyPlanningStorePage().createPage(getDriver(), getContext(), log, status,
				data, getReport(), getReportLogger(), MonthlyPlanningGroupPage.class);
		TestStatus status = getTestStatus();
		System.out.println("Entering into TestCase");
		pageMonthlyPlanningGroupPO.SelectGroupDropdownToView(objMonthlyPlanningGroupObjects, extentTest,
				getContext().getStringProperty("GroupToView"));
		OptionToView = getContext().getStringProperty("OptionToView");
		if (OptionToView.contains("Budget"))
			Name = pageWeeklyPlanningPO.MonthName(objWeeklyPlanningStoreObjects, extentTest,
					objWeeklyPlanningStoreObjects.CurrentWeekBGDT);
		if (OptionToView.contains("Forecast"))
			Name = pageWeeklyPlanningPO.MonthName(objWeeklyPlanningStoreObjects, extentTest,
					objWeeklyPlanningStoreObjects.CurrentWeekFCST);
		WeekName = Name.split(" ");
		Week = WeekName[0];
		Year = WeekName[4];
		LYYear = Integer.parseInt(Year) + 1;
		LYYr = String.valueOf(LYYear);

		String PlannedpercentonLYTotal = data.getText("PlannedpercentonLYTotal");
		TotalValue = sql.executeQuery(getContext(),
				PlannedpercentonLYTotal.replace("Region", getContext().getStringProperty("Region"))
						.replace("Area", getContext().getStringProperty("Area"))
						.replace("store", getContext().getStringProperty("store"))
						.replace("Zone", getContext().getStringProperty("Zone")).replace("FinWeek", Week)
						.replace("FinYear", LYYr).replace("LYFinYr", LYYr)
						.replace("Division", getContext().getStringProperty("Division")));
		System.out.println("DB Value: " + TotalValue);

		String PlannedpercentonLY = data1.getText("PlannedpercentonLY");
		IndividualValue = sql1.executeQuery(getContext(),
				PlannedpercentonLY.replace("Region", getContext().getStringProperty("Region"))
						.replace("Area", getContext().getStringProperty("Area"))
						.replace("store", getContext().getStringProperty("store"))
						.replace("Zone", getContext().getStringProperty("Zone")).replace("FinWeek", Week)
						.replace("FinYear", LYYr).replace("LYFinYr", LYYr)
						.replace("Division", getContext().getStringProperty("Division")));
		System.out.println("DB Total: " + IndividualValue);

		pageDBComparisionPO.CompareValueDecimalFields(extentTest, TotalValue, IndividualValue, "GrowthLY CPH");
	}

	// New Table
	// MonthlyViewMetricsValidation
	@Test(description = "Verify Group Total Value and Respective Group Value in Zone Screen, MonthlyViewMetricsValidation", priority = 31)
	public void MonthlyViewMetricsValidation() throws Exception {
		pageWeeklyPlanningPO = new WeeklyPlanningStorePage().createPage(getDriver(), getContext(), log, status, data,
				getReport(), getReportLogger(), WeeklyPlanningStorePage.class);
		pageMonthlyPlanningGroupPO = new MonthlyPlanningGroupPage().createPage(getDriver(), getContext(), log, status,
				data, getReport(), getReportLogger(), MonthlyPlanningGroupPage.class);
		TestStatus status = getTestStatus();
		System.out.println("Entering into TestCase");

		// Sales
		Report_AddStep("testcase", "SALES COLUMN VALIDATION - MONTHLY DATA", "", "", "");
		extentTest.log(LogStatus.INFO, "SALES COLUMN VALIDATION - MONTHLY DATA");

		String Sales = data1.getText("Sales");
		DBValues = sql.CLRexecuteQuery(getContext(),
				Sales.replace("store", getContext().getStringProperty("store"))
						.replace("Month", getContext().getStringProperty("MonthToTest"))
						.replace("Division", getContext().getStringProperty("Division")));
		System.out.println("DB Value: " + DBValues);

		String SalesTotal = data.getText("SalesTotal");
		DBTotal = sql.CLRexecuteQuery(getContext(),
				SalesTotal.replace("store", getContext().getStringProperty("store"))
						.replace("Month", getContext().getStringProperty("MonthToTest"))
						.replace("Division", getContext().getStringProperty("Division")));
		System.out.println("DB Total: " + DBTotal);

		pageDBComparisionPO.CompareValues(extentTest, DBValues, DBTotal, "Sales");

		// Wages
		Report_AddStep("testcase", "WAGES COLUMN VALIDATION - MONTHLY DATA", "", "", "");
		extentTest.log(LogStatus.INFO, "WAGES COLUMN VALIDATION - MONTHLY DATA");

		String Wages = data1.getText("Wages");
		DBValues1 = sql.CLRexecuteQuery(getContext(),
				Wages.replace("store", getContext().getStringProperty("store"))
						.replace("Month", getContext().getStringProperty("MonthToTest"))
						.replace("Division", getContext().getStringProperty("Division")));
		System.out.println("DB Value: " + DBValues1);

		String WagesTotal = data.getText("WagesTotal");
		DBTotal1 = sql.CLRexecuteQuery(getContext(),
				WagesTotal.replace("store", getContext().getStringProperty("store"))
						.replace("Month", getContext().getStringProperty("MonthToTest"))
						.replace("Division", getContext().getStringProperty("Division")));
		System.out.println("DB Total: " + DBTotal1);

		pageDBComparisionPO.CompareValues(extentTest, DBValues1, DBTotal1, "Wages");

		// Wage %
		Report_AddStep("testcase", "WAGES PERCENTAGE COLUMN VALIDATION - MONTHLY DATA", "", "", "");
		extentTest.log(LogStatus.INFO, "WAGES PERCENTAGE COLUMN VALIDATION - MONTHLY DATA");

		String WagePercentage = data1.getText("WagePercentage");
		DBValues2 = sql.CLRexecuteQuery(getContext(),
				WagePercentage.replace("store", getContext().getStringProperty("store"))
						.replace("Month", getContext().getStringProperty("MonthToTest"))
						.replace("Division", getContext().getStringProperty("Division")));
		System.out.println("DB Value: " + DBValues2);

		String WagePercentageTotal = data.getText("WagePercentageTotal");
		DBTotal2 = sql.CLRexecuteQuery(getContext(),
				WagePercentageTotal.replace("store", getContext().getStringProperty("store"))
						.replace("Month", getContext().getStringProperty("MonthToTest"))
						.replace("Division", getContext().getStringProperty("Division")));
		System.out.println("DB Total: " + DBTotal2);

		pageDBComparisionPO.CompareValueDecimalFields(extentTest, TotalValue, IndividualValue, "Wage Percentage");

		// CPH
		Report_AddStep("testcase", "CPH COLUMN VALIDATION - MONTHLY DATA", "", "", "");
		extentTest.log(LogStatus.INFO, "CPH COLUMN VALIDATION - MONTHLY DATA");

		String CPHValue = data1.getText("CPHValue");
		DBValues3 = sql.CLRexecuteQuery(getContext(),
				CPHValue.replace("store", getContext().getStringProperty("store"))
						.replace("Month", getContext().getStringProperty("MonthToTest"))
						.replace("Division", getContext().getStringProperty("Division")));
		System.out.println("DB Value: " + DBValues3);

		String CPHTotal = data.getText("CPHTotal");
		DBTotal3 = sql.CLRexecuteQuery(getContext(),
				CPHTotal.replace("store", getContext().getStringProperty("store"))
						.replace("Month", getContext().getStringProperty("MonthToTest"))
						.replace("Division", getContext().getStringProperty("Division")));
		System.out.println("DB Total: " + DBTotal3);

		pageDBComparisionPO.CompareValueDecimalFields(extentTest, DBValues3, DBTotal3, "CPH");

		// Hours
		Report_AddStep("testcase", "PLAN PAID HOURS COLUMN VALIDATION - MONTHLY DATA", "", "", "");
		extentTest.log(LogStatus.INFO, "PLAN PAID HOURS COLUMN VALIDATION - MONTHLY DATA");

		String PlanPaidHours = data1.getText("PlanPaidHours");
		DBValues4 = sql.CLRexecuteQuery(getContext(),
				PlanPaidHours.replace("store", getContext().getStringProperty("store"))
						.replace("Month", getContext().getStringProperty("MonthToTest"))
						.replace("Division", getContext().getStringProperty("Division")));
		System.out.println("DB Value: " + DBValues4);

		String PlanPaidHoursTotal = data.getText("PlanPaidHoursTotal");
		DBTotal4 = sql.CLRexecuteQuery(getContext(),
				PlanPaidHoursTotal.replace("store", getContext().getStringProperty("store"))
						.replace("Month", getContext().getStringProperty("MonthToTest"))
						.replace("Division", getContext().getStringProperty("Division")));
		System.out.println("DB Total: " + DBTotal4);

		pageDBComparisionPO.CompareValueDecimalFields(extentTest, DBValues4, DBTotal4, "Hours");

		// OR
		Report_AddStep("testcase", "OR PERCENTAGE COLUMN VALIDATION - MONTHLY DATA", "", "", "");
		extentTest.log(LogStatus.INFO, "OR PERCENTAGE COLUMN VALIDATION - MONTHLY DATA");

		String ORPercentage = data1.getText("ORPercentage");
		DBValues5 = sql.CLRexecuteQuery(getContext(),
				ORPercentage.replace("store", getContext().getStringProperty("store"))
						.replace("Month", getContext().getStringProperty("MonthToTest"))
						.replace("Division", getContext().getStringProperty("Division")));
		System.out.println("DB Value: " + DBValues5);

		String ORPercentageTotal = data.getText("ORPercentageTotal");
		DBTotal5 = sql.CLRexecuteQuery(getContext(),
				ORPercentageTotal.replace("store", getContext().getStringProperty("store"))
						.replace("Month", getContext().getStringProperty("MonthToTest"))
						.replace("Division", getContext().getStringProperty("Division")));
		System.out.println("DB Total: " + DBTotal5);

		pageDBComparisionPO.CompareValueDecimalFields(extentTest, DBValues5, DBTotal5, "OR Percentage");
	}

	@Test(priority = 31)
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
			data.loadTestDataXML(System.getProperty("user.dir") + "\\TestData\\WLP\\WeeklyPlanningGroup.xml");
			data1.loadTestDataXML(System.getProperty("user.dir") + "\\TestData\\WLP\\WeeklyPlanningZone.xml");
			customreport.createExtentReport();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
}