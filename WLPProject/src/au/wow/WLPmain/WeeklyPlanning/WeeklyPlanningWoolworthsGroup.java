package au.wow.WLPmain.WeeklyPlanning;

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

import com.relevantcodes.extentreports.ExtentReports;
import com.relevantcodes.extentreports.ExtentTest;
import com.relevantcodes.extentreports.LogStatus;

import au.wow.WLPmain.objects.DailyPlanningMainObjects;
import au.wow.WLPmain.objects.MonthlyPlanningGroupObjects;
import au.wow.WLPmain.objects.MonthlyPlanningStoreObjects;
import au.wow.WLPmain.objects.WeeklyPlanningStoreObjects;
import au.wow.WLPmain.pages.CommonFunctions;
import au.wow.WLPmain.pages.DailyPlanningMainPage;
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

public class WeeklyPlanningWoolworthsGroup extends TestBase {

	protected Logger log = LogManager.getLogger(WeeklyPlanningWoolworthsGroup.class);
	TestStatus status;
	ExtentReportsScreenshotCode ScreenShotUtil;
	SQLWrapper sql = new SQLWrapper(log);
	WoWLoginPage loginPage;
	CommonRepository CommonFunc;
	WeeklyPlanningStorePage pageWeeklyPlanningPO;
	WeeklyPlanningStoreObjects objWeeklyPlanningStoreObjects;
	MonthlyPlanningStoreObjects objMonthlyPlanningStoreObjects;
	MonthlyPlanningGroupObjects objMonthlyPlanningGroupObjects;
	MonthlyPlanningStorePage pageMonthlyPlanningPO;
	MonthlyPlanningGroupPage pageMonthlyPlanningGroupPO;
	BasePage BaseObj;
	CommonFunctions common;
	StoreProperties storeprop;
	List<List<Object>> DBValues, DBValues1, DBValues2, DBValues3, DBValues4, DBValues5, DBTotal, DBTotal1, DBTotal2,
			DBTotal3, DBTotal4, DBTotal5, DBPstive, DBNgtive;
	List<List<Object>> UIValues, UIValues1, UIValues2, UIValues3, UIValues4, UIValues5, UITotal, UITotal1, UITotal2,
			UITotal3, UITotal4, UITotal5;
	String Week, Name, Year, YrName, LYYr, OptionToView, Wk, HWk1, HWk2, HWk3, HWk4;
	int LYYear, HistWk;
	String[] WeekName, YearName;

	public WeeklyPlanningWoolworthsGroup() {
		super.log = log;
	}

	/**
	 * AL_1 Verifying Department Hierarchy and Paginations
	 * 
	 * @throws Exception
	 */

	@Test(priority = 0)
	public void LogintoCLRApplication() throws Exception {
		loginPage = new WoWLoginPage().createPage(getDriver(), getContext(), log, status, data, report, extentTest,
				WoWLoginPage.class);
		pageWeeklyPlanningPO = loginPage.navToWOWGroupPage(getContext().getStringProperty("store"));
	}

	// Sales
	@Test(description = "Weekly Planning - WOW Group, Sales history - First Week", priority = 1)
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
				objWeeklyPlanningStoreObjects.screenToView, "Woolworths Group");
	/*	pageWeeklyPlanningPO.verifyPageName(extentTest, objWeeklyPlanningStoreObjects.DataFrame,
				objWeeklyPlanningStoreObjects.pageName, getContext().getStringProperty("WeeklyPlanningWOWGroup"));*/

		pageWeeklyPlanningPO.SelectHistoryDropdownToView(objWeeklyPlanningStoreObjects, extentTest, "Sales");

		Name = pageWeeklyPlanningPO.MonthName(objWeeklyPlanningStoreObjects, extentTest,
				objWeeklyPlanningStoreObjects.FirstWeekofMonth);
		OptionToView = getContext().getStringProperty("OptionToView");
		if (OptionToView.contains("Budget"))
			YrName = pageWeeklyPlanningPO.MonthName(objWeeklyPlanningStoreObjects, extentTest,
					objWeeklyPlanningStoreObjects.CurrentWeekBGDT);
		if (OptionToView.contains("Forecast"))
			YrName = pageWeeklyPlanningPO.MonthName(objWeeklyPlanningStoreObjects, extentTest,
					objWeeklyPlanningStoreObjects.CurrentWeekFCST);
		WeekName = Name.split(" ");
		YearName = YrName.split(" ");
		Week = WeekName[0];
		Year = getContext().getStringProperty("Year");
		String SalesHistoryTotal = data.getText("SalesHistoryTotal");
		DBTotal = sql.CLRexecuteQuery(getContext(),
				SalesHistoryTotal.replace("Region", getContext().getStringProperty("Region"))
						.replace("Area", getContext().getStringProperty("Area"))
						.replace("Zone", getContext().getStringProperty("Zone")).replace("FinWeek", Week)
						.replace("FinYear", Year).replace("Division", getContext().getStringProperty("Division")));
		System.out.println("DB Total: " + DBTotal);

		UIValues = pageWeeklyPlanningPO.UIResults(objMonthlyPlanningStoreObjects.MPTable, extentTest,
				objWeeklyPlanningStoreObjects.DepartmentNames, WeeklyPlanningStoreObjects.FirstWeekofMonth);
		pageMonthlyPlanningPO.CompareValues(extentTest, DBTotal, UIValues);

		UITotal = pageMonthlyPlanningGroupPO.WPGetTotal(extentTest, MonthlyPlanningGroupObjects.RowNameTotal,
				WeeklyPlanningStoreObjects.FirstWeekofMonth);
		pageMonthlyPlanningPO.CompareValues(extentTest, DBTotal, UITotal);
	}

	@Test(description = "Weekly Planning - WOW Group, Sales history - Second Week", priority = 2)
	public void SalesHistorySecondWeek() throws Exception {
		pageWeeklyPlanningPO = new WeeklyPlanningStorePage().createPage(getDriver(), getContext(), log, status, data,
				getReport(), getReportLogger(), WeeklyPlanningStorePage.class);
		pageMonthlyPlanningGroupPO = new MonthlyPlanningGroupPage().createPage(getDriver(), getContext(), log, status,
				data, getReport(), getReportLogger(), MonthlyPlanningGroupPage.class);
		TestStatus status = getTestStatus();
		System.out.println("Entering into TestCase");
		Name = pageWeeklyPlanningPO.MonthName(objWeeklyPlanningStoreObjects, extentTest,
				objWeeklyPlanningStoreObjects.SecondWeekofMonth);
		YrName = pageWeeklyPlanningPO.MonthName(objWeeklyPlanningStoreObjects, extentTest,
				objWeeklyPlanningStoreObjects.CurrentWeekBGDT);
		WeekName = Name.split(" ");
		YearName = YrName.split(" ");
		Week = WeekName[0];
		Year = getContext().getStringProperty("Year");

		String SalesHistoryTotal = data.getText("SalesHistoryTotal");
		DBTotal = sql.CLRexecuteQuery(getContext(),
				SalesHistoryTotal.replace("Region", getContext().getStringProperty("Region"))
						.replace("Area", getContext().getStringProperty("Area"))
						.replace("Zone", getContext().getStringProperty("Zone")).replace("FinWeek", Week)
						.replace("FinYear", Year).replace("Division", getContext().getStringProperty("Division")));
		System.out.println("DB Total: " + DBTotal);

		UIValues = pageWeeklyPlanningPO.UIResults(objMonthlyPlanningStoreObjects.MPTable, extentTest,
				objWeeklyPlanningStoreObjects.DepartmentNames, WeeklyPlanningStoreObjects.SecondWeekofMonth);
		pageMonthlyPlanningPO.CompareValues(extentTest, DBTotal, UIValues);

		UITotal = pageMonthlyPlanningGroupPO.WPGetTotal(extentTest, objMonthlyPlanningGroupObjects.RowNameTotal,
				WeeklyPlanningStoreObjects.SecondWeekofMonth);
		pageMonthlyPlanningPO.CompareValues(extentTest, DBTotal, UITotal);
	}

	@Test(description = "Weekly Planning - WOW Group, Sales history - Third Week", priority = 3)
	public void SalesHistoryThirdWeek() throws Exception {
		pageWeeklyPlanningPO = new WeeklyPlanningStorePage().createPage(getDriver(), getContext(), log, status, data,
				getReport(), getReportLogger(), WeeklyPlanningStorePage.class);
		pageMonthlyPlanningGroupPO = new MonthlyPlanningGroupPage().createPage(getDriver(), getContext(), log, status,
				data, getReport(), getReportLogger(), MonthlyPlanningGroupPage.class);
		TestStatus status = getTestStatus();
		System.out.println("Entering into TestCase");
		Name = pageWeeklyPlanningPO.MonthName(objWeeklyPlanningStoreObjects, extentTest,
				objWeeklyPlanningStoreObjects.ThirdWeekofMonth);
		YrName = pageWeeklyPlanningPO.MonthName(objWeeklyPlanningStoreObjects, extentTest,
				objWeeklyPlanningStoreObjects.CurrentWeekBGDT);
		WeekName = Name.split(" ");
		YearName = YrName.split(" ");
		Week = WeekName[0];
		Year = getContext().getStringProperty("Year");

		String SalesHistoryTotal = data.getText("SalesHistoryTotal");
		DBTotal = sql.CLRexecuteQuery(getContext(),
				SalesHistoryTotal.replace("Region", getContext().getStringProperty("Region"))
						.replace("Area", getContext().getStringProperty("Area"))
						.replace("Zone", getContext().getStringProperty("Zone")).replace("FinWeek", Week)
						.replace("FinYear", Year).replace("Division", getContext().getStringProperty("Division")));
		System.out.println("DB Total: " + DBTotal);

		UIValues = pageWeeklyPlanningPO.UIResults(objMonthlyPlanningStoreObjects.MPTable, extentTest,
				objWeeklyPlanningStoreObjects.DepartmentNames, objWeeklyPlanningStoreObjects.ThirdWeekofMonth);
		pageMonthlyPlanningPO.CompareValues(extentTest, DBTotal, UIValues);

		UITotal = pageMonthlyPlanningGroupPO.WPGetTotal(extentTest, objMonthlyPlanningGroupObjects.RowNameTotal,
				objWeeklyPlanningStoreObjects.ThirdWeekofMonth);
		pageMonthlyPlanningPO.CompareValues(extentTest, DBTotal, UITotal);
	}

	@Test(description = "Weekly Planning - WOW Group, Sales history - Fourth Week", priority = 4)
	public void SalesHistoryFourthWeek() throws Exception {
		pageWeeklyPlanningPO = new WeeklyPlanningStorePage().createPage(getDriver(), getContext(), log, status, data,
				getReport(), getReportLogger(), WeeklyPlanningStorePage.class);
		pageMonthlyPlanningGroupPO = new MonthlyPlanningGroupPage().createPage(getDriver(), getContext(), log, status,
				data, getReport(), getReportLogger(), MonthlyPlanningGroupPage.class);
		TestStatus status = getTestStatus();
		System.out.println("Entering into TestCase");
		Name = pageWeeklyPlanningPO.MonthName(objWeeklyPlanningStoreObjects, extentTest,
				objWeeklyPlanningStoreObjects.FourWeekofMonth);
		YrName = pageWeeklyPlanningPO.MonthName(objWeeklyPlanningStoreObjects, extentTest,
				objWeeklyPlanningStoreObjects.CurrentWeekBGDT);
		WeekName = Name.split(" ");
		YearName = YrName.split(" ");
		Week = WeekName[0];
		Year = getContext().getStringProperty("Year");

		String SalesHistoryTotal = data.getText("SalesHistoryTotal");
		DBTotal = sql.CLRexecuteQuery(getContext(),
				SalesHistoryTotal.replace("Region", getContext().getStringProperty("Region"))
						.replace("Area", getContext().getStringProperty("Area"))
						.replace("Zone", getContext().getStringProperty("Zone")).replace("FinWeek", Week)
						.replace("FinYear", Year).replace("Division", getContext().getStringProperty("Division")));
		System.out.println("DB Total: " + DBTotal);

		UIValues = pageWeeklyPlanningPO.UIResults(objMonthlyPlanningStoreObjects.MPTable, extentTest,
				objWeeklyPlanningStoreObjects.DepartmentNames, objWeeklyPlanningStoreObjects.FourWeekofMonth);
		pageMonthlyPlanningPO.CompareValues(extentTest, DBTotal, UIValues);

		UITotal = pageMonthlyPlanningGroupPO.WPGetTotal(extentTest, objMonthlyPlanningGroupObjects.RowNameTotal,
				objWeeklyPlanningStoreObjects.FourWeekofMonth);
		pageMonthlyPlanningPO.CompareValues(extentTest, DBTotal, UITotal);
	}

	// Wages
	@Test(description = "Weekly Planning - WOW Group, Wages history - First Week", priority = 5)
	public void WagesHistoryFirstWeek() throws Exception {
		pageWeeklyPlanningPO = new WeeklyPlanningStorePage().createPage(getDriver(), getContext(), log, status, data,
				getReport(), getReportLogger(), WeeklyPlanningStorePage.class);
		pageMonthlyPlanningGroupPO = new MonthlyPlanningGroupPage().createPage(getDriver(), getContext(), log, status,
				data, getReport(), getReportLogger(), MonthlyPlanningGroupPage.class);
		TestStatus status = getTestStatus();
		System.out.println("Entering into TestCase");
		pageWeeklyPlanningPO.SelectHistoryDropdownToView(objWeeklyPlanningStoreObjects, extentTest, "Wages");
		Name = pageWeeklyPlanningPO.MonthName(objWeeklyPlanningStoreObjects, extentTest,
				objWeeklyPlanningStoreObjects.FirstWeekofMonth);
		YrName = pageWeeklyPlanningPO.MonthName(objWeeklyPlanningStoreObjects, extentTest,
				objWeeklyPlanningStoreObjects.CurrentWeekBGDT);
		WeekName = Name.split(" ");
		YearName = YrName.split(" ");
		Week = WeekName[0];
		Year = getContext().getStringProperty("Year");

		String WagesHistoryTotal = data.getText("WagesHistoryTotal");
		DBTotal = sql.CLRexecuteQuery(getContext(),
				WagesHistoryTotal.replace("Region", getContext().getStringProperty("Region"))
						.replace("Area", getContext().getStringProperty("Area"))
						.replace("Zone", getContext().getStringProperty("Zone")).replace("FinWeek", Week)
						.replace("FinYear", Year).replace("Division", getContext().getStringProperty("Division")));
		System.out.println("DB Total: " + DBTotal);

		UIValues = pageWeeklyPlanningPO.UIResults(objMonthlyPlanningStoreObjects.MPTable, extentTest,
				objWeeklyPlanningStoreObjects.DepartmentNames, objWeeklyPlanningStoreObjects.FirstWeekofMonth);
		pageMonthlyPlanningPO.CompareValues(extentTest, DBTotal, UIValues);

		UITotal = pageMonthlyPlanningGroupPO.WPGetTotal(extentTest, objMonthlyPlanningGroupObjects.RowNameTotal,
				objWeeklyPlanningStoreObjects.FirstWeekofMonth);
		pageMonthlyPlanningPO.CompareValues(extentTest, DBTotal, UITotal);
	}

	@Test(description = "Weekly Planning - WOW Group, Wages history - Second Week", priority = 6)
	public void WagesHistorySecondWeek() throws Exception {
		pageWeeklyPlanningPO = new WeeklyPlanningStorePage().createPage(getDriver(), getContext(), log, status, data,
				getReport(), getReportLogger(), WeeklyPlanningStorePage.class);
		pageMonthlyPlanningGroupPO = new MonthlyPlanningGroupPage().createPage(getDriver(), getContext(), log, status,
				data, getReport(), getReportLogger(), MonthlyPlanningGroupPage.class);
		TestStatus status = getTestStatus();
		System.out.println("Entering into TestCase");
		Name = pageWeeklyPlanningPO.MonthName(objWeeklyPlanningStoreObjects, extentTest,
				objWeeklyPlanningStoreObjects.SecondWeekofMonth);
		YrName = pageWeeklyPlanningPO.MonthName(objWeeklyPlanningStoreObjects, extentTest,
				objWeeklyPlanningStoreObjects.CurrentWeekBGDT);
		WeekName = Name.split(" ");
		YearName = YrName.split(" ");
		Week = WeekName[0];
		Year = getContext().getStringProperty("Year");

		String WagesHistoryTotal = data.getText("WagesHistoryTotal");
		DBTotal = sql.CLRexecuteQuery(getContext(),
				WagesHistoryTotal.replace("Region", getContext().getStringProperty("Region"))
						.replace("Area", getContext().getStringProperty("Area"))
						.replace("Zone", getContext().getStringProperty("Zone")).replace("FinWeek", Week)
						.replace("FinYear", Year).replace("Division", getContext().getStringProperty("Division")));
		System.out.println("DB Total: " + DBTotal);

		UIValues = pageWeeklyPlanningPO.UIResults(objMonthlyPlanningStoreObjects.MPTable, extentTest,
				objWeeklyPlanningStoreObjects.DepartmentNames, objWeeklyPlanningStoreObjects.SecondWeekofMonth);
		pageMonthlyPlanningPO.CompareValues(extentTest, DBTotal, UIValues);

		UITotal = pageMonthlyPlanningGroupPO.WPGetTotal(extentTest, objMonthlyPlanningGroupObjects.RowNameTotal,
				objWeeklyPlanningStoreObjects.SecondWeekofMonth);
		pageMonthlyPlanningPO.CompareValues(extentTest, DBTotal, UITotal);
	}

	@Test(description = "Weekly Planning - WOW Group, Wages history - Third Week", priority = 7)
	public void WagesHistoryThirdWeek() throws Exception {
		pageWeeklyPlanningPO = new WeeklyPlanningStorePage().createPage(getDriver(), getContext(), log, status, data,
				getReport(), getReportLogger(), WeeklyPlanningStorePage.class);
		pageMonthlyPlanningGroupPO = new MonthlyPlanningGroupPage().createPage(getDriver(), getContext(), log, status,
				data, getReport(), getReportLogger(), MonthlyPlanningGroupPage.class);
		TestStatus status = getTestStatus();
		System.out.println("Entering into TestCase");
		Name = pageWeeklyPlanningPO.MonthName(objWeeklyPlanningStoreObjects, extentTest,
				objWeeklyPlanningStoreObjects.ThirdWeekofMonth);
		YrName = pageWeeklyPlanningPO.MonthName(objWeeklyPlanningStoreObjects, extentTest,
				objWeeklyPlanningStoreObjects.CurrentWeekBGDT);
		WeekName = Name.split(" ");
		YearName = YrName.split(" ");
		Week = WeekName[0];
		Year = getContext().getStringProperty("Year");

		String WagesHistoryTotal = data.getText("WagesHistoryTotal");
		DBTotal = sql.CLRexecuteQuery(getContext(),
				WagesHistoryTotal.replace("Region", getContext().getStringProperty("Region"))
						.replace("Area", getContext().getStringProperty("Area"))
						.replace("Zone", getContext().getStringProperty("Zone")).replace("FinWeek", Week)
						.replace("FinYear", Year).replace("Division", getContext().getStringProperty("Division")));
		System.out.println("DB Total: " + DBTotal);

		UIValues = pageWeeklyPlanningPO.UIResults(objMonthlyPlanningStoreObjects.MPTable, extentTest,
				objWeeklyPlanningStoreObjects.DepartmentNames, objWeeklyPlanningStoreObjects.ThirdWeekofMonth);
		pageMonthlyPlanningPO.CompareValues(extentTest, DBTotal, UIValues);

		UITotal = pageMonthlyPlanningGroupPO.WPGetTotal(extentTest, objMonthlyPlanningGroupObjects.RowNameTotal,
				objWeeklyPlanningStoreObjects.ThirdWeekofMonth);
		pageMonthlyPlanningPO.CompareValues(extentTest, DBTotal, UITotal);
	}

	@Test(description = "Weekly Planning - WOW Group, Wages history - Fourth Week", priority = 8)
	public void WagesHistoryFourthWeek() throws Exception {
		pageWeeklyPlanningPO = new WeeklyPlanningStorePage().createPage(getDriver(), getContext(), log, status, data,
				getReport(), getReportLogger(), WeeklyPlanningStorePage.class);
		pageMonthlyPlanningGroupPO = new MonthlyPlanningGroupPage().createPage(getDriver(), getContext(), log, status,
				data, getReport(), getReportLogger(), MonthlyPlanningGroupPage.class);
		TestStatus status = getTestStatus();
		System.out.println("Entering into TestCase");
		Name = pageWeeklyPlanningPO.MonthName(objWeeklyPlanningStoreObjects, extentTest,
				objWeeklyPlanningStoreObjects.FourWeekofMonth);
		YrName = pageWeeklyPlanningPO.MonthName(objWeeklyPlanningStoreObjects, extentTest,
				objWeeklyPlanningStoreObjects.CurrentWeekBGDT);
		WeekName = Name.split(" ");
		YearName = YrName.split(" ");
		Week = WeekName[0];
		Year = getContext().getStringProperty("Year");

		String WagesHistoryTotal = data.getText("WagesHistoryTotal");
		DBTotal = sql.CLRexecuteQuery(getContext(),
				WagesHistoryTotal.replace("Region", getContext().getStringProperty("Region"))
						.replace("Area", getContext().getStringProperty("Area"))
						.replace("Zone", getContext().getStringProperty("Zone")).replace("FinWeek", Week)
						.replace("FinYear", Year).replace("Division", getContext().getStringProperty("Division")));
		System.out.println("DB Total: " + DBTotal);

		UIValues = pageWeeklyPlanningPO.UIResults(objMonthlyPlanningStoreObjects.MPTable, extentTest,
				objWeeklyPlanningStoreObjects.DepartmentNames, objWeeklyPlanningStoreObjects.FourWeekofMonth);
		pageMonthlyPlanningPO.CompareValues(extentTest, DBTotal, UIValues);

		UITotal = pageMonthlyPlanningGroupPO.WPGetTotal(extentTest, objMonthlyPlanningGroupObjects.RowNameTotal,
				objWeeklyPlanningStoreObjects.FourWeekofMonth);
		pageMonthlyPlanningPO.CompareValues(extentTest, DBTotal, UITotal);
	}

	// Items
	@Test(description = "Weekly Planning - WOW Group, Items history - First Week", priority = 9)
	public void ItemsHistoryFirsthWeek() throws Exception {
		pageWeeklyPlanningPO = new WeeklyPlanningStorePage().createPage(getDriver(), getContext(), log, status, data,
				getReport(), getReportLogger(), WeeklyPlanningStorePage.class);
		pageMonthlyPlanningGroupPO = new MonthlyPlanningGroupPage().createPage(getDriver(), getContext(), log, status,
				data, getReport(), getReportLogger(), MonthlyPlanningGroupPage.class);
		TestStatus status = getTestStatus();
		System.out.println("Entering into TestCase");
		pageWeeklyPlanningPO.SelectHistoryDropdownToView(objWeeklyPlanningStoreObjects, extentTest, "Items");
		Name = pageWeeklyPlanningPO.MonthName(objWeeklyPlanningStoreObjects, extentTest,
				objWeeklyPlanningStoreObjects.FirstWeekofMonth);
		YrName = pageWeeklyPlanningPO.MonthName(objWeeklyPlanningStoreObjects, extentTest,
				objWeeklyPlanningStoreObjects.CurrentWeekBGDT);
		WeekName = Name.split(" ");
		YearName = YrName.split(" ");
		Week = WeekName[0];
		Year = getContext().getStringProperty("Year");

		String ItemsHistoryTotal = data.getText("ItemsHistoryTotal");
		DBTotal = sql.CLRexecuteQuery(getContext(),
				ItemsHistoryTotal.replace("Region", getContext().getStringProperty("Region"))
						.replace("Area", getContext().getStringProperty("Area"))
						.replace("Zone", getContext().getStringProperty("Zone")).replace("FinWeek", Week)
						.replace("FinYear", Year).replace("Division", getContext().getStringProperty("Division")));
		System.out.println("DB Total: " + DBTotal);

		UIValues = pageWeeklyPlanningPO.UIResults(objMonthlyPlanningStoreObjects.MPTable, extentTest,
				objWeeklyPlanningStoreObjects.DepartmentNames, objWeeklyPlanningStoreObjects.FirstWeekofMonth);
		pageMonthlyPlanningPO.CompareValues(extentTest, DBTotal, UIValues);

		UITotal = pageMonthlyPlanningGroupPO.WPGetTotal(extentTest, objMonthlyPlanningGroupObjects.RowNameTotal,
				objWeeklyPlanningStoreObjects.FirstWeekofMonth);
		pageMonthlyPlanningPO.CompareValues(extentTest, DBTotal, UITotal);
	}

	@Test(description = "Weekly Planning - WOW Group, Items history - Second Week", priority = 10)
	public void ItemsHistorySecondWeek() throws Exception {
		pageWeeklyPlanningPO = new WeeklyPlanningStorePage().createPage(getDriver(), getContext(), log, status, data,
				getReport(), getReportLogger(), WeeklyPlanningStorePage.class);
		pageMonthlyPlanningGroupPO = new MonthlyPlanningGroupPage().createPage(getDriver(), getContext(), log, status,
				data, getReport(), getReportLogger(), MonthlyPlanningGroupPage.class);
		TestStatus status = getTestStatus();
		System.out.println("Entering into TestCase");
		Name = pageWeeklyPlanningPO.MonthName(objWeeklyPlanningStoreObjects, extentTest,
				objWeeklyPlanningStoreObjects.SecondWeekofMonth);
		YrName = pageWeeklyPlanningPO.MonthName(objWeeklyPlanningStoreObjects, extentTest,
				objWeeklyPlanningStoreObjects.CurrentWeekBGDT);
		WeekName = Name.split(" ");
		YearName = YrName.split(" ");
		Week = WeekName[0];
		Year = getContext().getStringProperty("Year");

		String ItemsHistoryTotal = data.getText("ItemsHistoryTotal");
		DBTotal = sql.CLRexecuteQuery(getContext(),
				ItemsHistoryTotal.replace("Region", getContext().getStringProperty("Region"))
						.replace("Area", getContext().getStringProperty("Area"))
						.replace("Zone", getContext().getStringProperty("Zone")).replace("FinWeek", Week)
						.replace("FinYear", Year).replace("Division", getContext().getStringProperty("Division")));
		System.out.println("DB Total: " + DBTotal);

		UIValues = pageWeeklyPlanningPO.UIResults(objMonthlyPlanningStoreObjects.MPTable, extentTest,
				objWeeklyPlanningStoreObjects.DepartmentNames, objWeeklyPlanningStoreObjects.SecondWeekofMonth);
		pageMonthlyPlanningPO.CompareValues(extentTest, DBTotal, UIValues);

		UITotal = pageMonthlyPlanningGroupPO.WPGetTotal(extentTest, objMonthlyPlanningGroupObjects.RowNameTotal,
				objWeeklyPlanningStoreObjects.SecondWeekofMonth);
		pageMonthlyPlanningPO.CompareValues(extentTest, DBTotal, UITotal);
	}

	@Test(description = "Weekly Planning - WOW Group, Items history - Third Week", priority = 11)
	public void ItemsHistoryThirdWeek() throws Exception {
		pageWeeklyPlanningPO = new WeeklyPlanningStorePage().createPage(getDriver(), getContext(), log, status, data,
				getReport(), getReportLogger(), WeeklyPlanningStorePage.class);
		pageMonthlyPlanningGroupPO = new MonthlyPlanningGroupPage().createPage(getDriver(), getContext(), log, status,
				data, getReport(), getReportLogger(), MonthlyPlanningGroupPage.class);
		TestStatus status = getTestStatus();
		System.out.println("Entering into TestCase");
		Name = pageWeeklyPlanningPO.MonthName(objWeeklyPlanningStoreObjects, extentTest,
				objWeeklyPlanningStoreObjects.ThirdWeekofMonth);
		YrName = pageWeeklyPlanningPO.MonthName(objWeeklyPlanningStoreObjects, extentTest,
				objWeeklyPlanningStoreObjects.CurrentWeekBGDT);
		WeekName = Name.split(" ");
		YearName = YrName.split(" ");
		Week = WeekName[0];
		Year = getContext().getStringProperty("Year");

		String ItemsHistoryTotal = data.getText("ItemsHistoryTotal");
		DBTotal = sql.CLRexecuteQuery(getContext(),
				ItemsHistoryTotal.replace("Region", getContext().getStringProperty("Region"))
						.replace("Area", getContext().getStringProperty("Area"))
						.replace("Zone", getContext().getStringProperty("Zone")).replace("FinWeek", Week)
						.replace("FinYear", Year).replace("Division", getContext().getStringProperty("Division")));
		System.out.println("DB Total: " + DBTotal);

		UIValues = pageWeeklyPlanningPO.UIResults(objMonthlyPlanningStoreObjects.MPTable, extentTest,
				objWeeklyPlanningStoreObjects.DepartmentNames, objWeeklyPlanningStoreObjects.ThirdWeekofMonth);
		pageMonthlyPlanningPO.CompareValues(extentTest, DBTotal, UIValues);

		UITotal = pageMonthlyPlanningGroupPO.WPGetTotal(extentTest, objMonthlyPlanningGroupObjects.RowNameTotal,
				objWeeklyPlanningStoreObjects.ThirdWeekofMonth);
		pageMonthlyPlanningPO.CompareValues(extentTest, DBTotal, UITotal);
	}

	@Test(description = "Weekly Planning - WOW Group, Items history - Fourth Week", priority = 12)
	public void ItemsHistoryFourthWeek() throws Exception {
		pageWeeklyPlanningPO = new WeeklyPlanningStorePage().createPage(getDriver(), getContext(), log, status, data,
				getReport(), getReportLogger(), WeeklyPlanningStorePage.class);
		pageMonthlyPlanningGroupPO = new MonthlyPlanningGroupPage().createPage(getDriver(), getContext(), log, status,
				data, getReport(), getReportLogger(), MonthlyPlanningGroupPage.class);
		TestStatus status = getTestStatus();
		System.out.println("Entering into TestCase");
		Name = pageWeeklyPlanningPO.MonthName(objWeeklyPlanningStoreObjects, extentTest,
				objWeeklyPlanningStoreObjects.FourWeekofMonth);
		YrName = pageWeeklyPlanningPO.MonthName(objWeeklyPlanningStoreObjects, extentTest,
				objWeeklyPlanningStoreObjects.CurrentWeekBGDT);
		WeekName = Name.split(" ");
		YearName = YrName.split(" ");
		Week = WeekName[0];
		Year = getContext().getStringProperty("Year");

		String ItemsHistoryTotal = data.getText("ItemsHistoryTotal");
		DBTotal = sql.CLRexecuteQuery(getContext(),
				ItemsHistoryTotal.replace("Region", getContext().getStringProperty("Region"))
						.replace("Area", getContext().getStringProperty("Area"))
						.replace("Zone", getContext().getStringProperty("Zone")).replace("FinWeek", Week)
						.replace("FinYear", Year).replace("Division", getContext().getStringProperty("Division")));
		System.out.println("DB Total: " + DBTotal);

		UIValues = pageWeeklyPlanningPO.UIResults(objMonthlyPlanningStoreObjects.MPTable, extentTest,
				objWeeklyPlanningStoreObjects.DepartmentNames, objWeeklyPlanningStoreObjects.FourWeekofMonth);
		pageMonthlyPlanningPO.CompareValues(extentTest, DBTotal, UIValues);

		UITotal = pageMonthlyPlanningGroupPO.WPGetTotal(extentTest, objMonthlyPlanningGroupObjects.RowNameTotal,
				objWeeklyPlanningStoreObjects.FourWeekofMonth);
		pageMonthlyPlanningPO.CompareValues(extentTest, DBTotal, UITotal);
	}

	// Customers
	@Test(description = "Weekly Planning - WOW Group, Customers history - First Week", priority = 13)
	public void CustomersHistoryFirsthWeek() throws Exception {
		pageWeeklyPlanningPO = new WeeklyPlanningStorePage().createPage(getDriver(), getContext(), log, status, data,
				getReport(), getReportLogger(), WeeklyPlanningStorePage.class);
		pageMonthlyPlanningGroupPO = new MonthlyPlanningGroupPage().createPage(getDriver(), getContext(), log, status,
				data, getReport(), getReportLogger(), MonthlyPlanningGroupPage.class);
		TestStatus status = getTestStatus();
		System.out.println("Entering into TestCase");
		pageWeeklyPlanningPO.SelectHistoryDropdownToView(objWeeklyPlanningStoreObjects, extentTest, "Customers");
		Name = pageWeeklyPlanningPO.MonthName(objWeeklyPlanningStoreObjects, extentTest,
				objWeeklyPlanningStoreObjects.FirstWeekofMonth);
		YrName = pageWeeklyPlanningPO.MonthName(objWeeklyPlanningStoreObjects, extentTest,
				objWeeklyPlanningStoreObjects.CurrentWeekBGDT);
		WeekName = Name.split(" ");
		YearName = YrName.split(" ");
		Week = WeekName[0];
		Year = getContext().getStringProperty("Year");

		String CustomerHistoryTotal = data.getText("CustomerHistoryTotal");
		DBTotal = sql.CLRexecuteQuery(getContext(),
				CustomerHistoryTotal.replace("Region", getContext().getStringProperty("Region"))
						.replace("Area", getContext().getStringProperty("Area"))
						.replace("Zone", getContext().getStringProperty("Zone")).replace("FinWeek", Week)
						.replace("FinYear", Year).replace("Division", getContext().getStringProperty("Division")));
		System.out.println("DB Total: " + DBTotal);

		UIValues = pageWeeklyPlanningPO.UIResults(objMonthlyPlanningStoreObjects.MPTable, extentTest,
				objWeeklyPlanningStoreObjects.DepartmentNames, objWeeklyPlanningStoreObjects.FirstWeekofMonth);
		pageMonthlyPlanningPO.CompareValues(extentTest, DBTotal, UIValues);

		UITotal = pageMonthlyPlanningGroupPO.WPGetTotal(extentTest, objMonthlyPlanningGroupObjects.RowNameTotal,
				objWeeklyPlanningStoreObjects.FirstWeekofMonth);
		pageMonthlyPlanningPO.CompareValues(extentTest, DBTotal, UITotal);
	}

	@Test(description = "Weekly Planning - WOW Group, Customers history - Second Week", priority = 14)
	public void CustomersHistorySecondWeek() throws Exception {
		pageWeeklyPlanningPO = new WeeklyPlanningStorePage().createPage(getDriver(), getContext(), log, status, data,
				getReport(), getReportLogger(), WeeklyPlanningStorePage.class);
		pageMonthlyPlanningGroupPO = new MonthlyPlanningGroupPage().createPage(getDriver(), getContext(), log, status,
				data, getReport(), getReportLogger(), MonthlyPlanningGroupPage.class);
		TestStatus status = getTestStatus();
		System.out.println("Entering into TestCase");
		Name = pageWeeklyPlanningPO.MonthName(objWeeklyPlanningStoreObjects, extentTest,
				objWeeklyPlanningStoreObjects.SecondWeekofMonth);
		YrName = pageWeeklyPlanningPO.MonthName(objWeeklyPlanningStoreObjects, extentTest,
				objWeeklyPlanningStoreObjects.CurrentWeekBGDT);
		WeekName = Name.split(" ");
		YearName = YrName.split(" ");
		Week = WeekName[0];
		Year = getContext().getStringProperty("Year");

		String CustomerHistoryTotal = data.getText("CustomerHistoryTotal");
		DBTotal = sql.CLRexecuteQuery(getContext(),
				CustomerHistoryTotal.replace("Region", getContext().getStringProperty("Region"))
						.replace("Area", getContext().getStringProperty("Area"))
						.replace("Zone", getContext().getStringProperty("Zone")).replace("FinWeek", Week)
						.replace("FinYear", Year).replace("Division", getContext().getStringProperty("Division")));
		System.out.println("DB Total: " + DBTotal);

		UIValues = pageWeeklyPlanningPO.UIResults(objMonthlyPlanningStoreObjects.MPTable, extentTest,
				objWeeklyPlanningStoreObjects.DepartmentNames, objWeeklyPlanningStoreObjects.SecondWeekofMonth);
		pageMonthlyPlanningPO.CompareValues(extentTest, DBTotal, UIValues);

		UITotal = pageMonthlyPlanningGroupPO.WPGetTotal(extentTest, objMonthlyPlanningGroupObjects.RowNameTotal,
				objWeeklyPlanningStoreObjects.SecondWeekofMonth);
		pageMonthlyPlanningPO.CompareValues(extentTest, DBTotal, UITotal);
	}

	@Test(description = "Weekly Planning - WOW Group, Customers history - Third Week", priority = 15)
	public void CustomersHistoryThirdWeek() throws Exception {
		pageWeeklyPlanningPO = new WeeklyPlanningStorePage().createPage(getDriver(), getContext(), log, status, data,
				getReport(), getReportLogger(), WeeklyPlanningStorePage.class);
		pageMonthlyPlanningGroupPO = new MonthlyPlanningGroupPage().createPage(getDriver(), getContext(), log, status,
				data, getReport(), getReportLogger(), MonthlyPlanningGroupPage.class);
		TestStatus status = getTestStatus();
		System.out.println("Entering into TestCase");
		Name = pageWeeklyPlanningPO.MonthName(objWeeklyPlanningStoreObjects, extentTest,
				objWeeklyPlanningStoreObjects.ThirdWeekofMonth);
		YrName = pageWeeklyPlanningPO.MonthName(objWeeklyPlanningStoreObjects, extentTest,
				objWeeklyPlanningStoreObjects.CurrentWeekBGDT);
		WeekName = Name.split(" ");
		YearName = YrName.split(" ");
		Week = WeekName[0];
		Year = getContext().getStringProperty("Year");

		String CustomerHistoryTotal = data.getText("CustomerHistoryTotal");
		DBTotal = sql.CLRexecuteQuery(getContext(),
				CustomerHistoryTotal.replace("Region", getContext().getStringProperty("Region"))
						.replace("Area", getContext().getStringProperty("Area"))
						.replace("Zone", getContext().getStringProperty("Zone")).replace("FinWeek", Week)
						.replace("FinYear", Year).replace("Division", getContext().getStringProperty("Division")));
		System.out.println("DB Total: " + DBTotal);

		UIValues = pageWeeklyPlanningPO.UIResults(objMonthlyPlanningStoreObjects.MPTable, extentTest,
				objWeeklyPlanningStoreObjects.DepartmentNames, objWeeklyPlanningStoreObjects.ThirdWeekofMonth);
		pageMonthlyPlanningPO.CompareValues(extentTest, DBTotal, UIValues);

		UITotal = pageMonthlyPlanningGroupPO.WPGetTotal(extentTest, objMonthlyPlanningGroupObjects.RowNameTotal,
				objWeeklyPlanningStoreObjects.ThirdWeekofMonth);
		pageMonthlyPlanningPO.CompareValues(extentTest, DBTotal, UITotal);
	}

	@Test(description = "Weekly Planning - WOW Group, Customers history - Fourth Week", priority = 16)
	public void CustomersHistoryFourthWeek() throws Exception {
		pageWeeklyPlanningPO = new WeeklyPlanningStorePage().createPage(getDriver(), getContext(), log, status, data,
				getReport(), getReportLogger(), WeeklyPlanningStorePage.class);
		pageMonthlyPlanningGroupPO = new MonthlyPlanningGroupPage().createPage(getDriver(), getContext(), log, status,
				data, getReport(), getReportLogger(), MonthlyPlanningGroupPage.class);
		TestStatus status = getTestStatus();
		System.out.println("Entering into TestCase");
		Name = pageWeeklyPlanningPO.MonthName(objWeeklyPlanningStoreObjects, extentTest,
				objWeeklyPlanningStoreObjects.FourWeekofMonth);
		YrName = pageWeeklyPlanningPO.MonthName(objWeeklyPlanningStoreObjects, extentTest,
				objWeeklyPlanningStoreObjects.CurrentWeekBGDT);
		WeekName = Name.split(" ");
		YearName = YrName.split(" ");
		Week = WeekName[0];
		Year = getContext().getStringProperty("Year");

		String CustomerHistoryTotal = data.getText("CustomerHistoryTotal");
		DBTotal = sql.CLRexecuteQuery(getContext(),
				CustomerHistoryTotal.replace("Region", getContext().getStringProperty("Region"))
						.replace("Area", getContext().getStringProperty("Area"))
						.replace("Zone", getContext().getStringProperty("Zone")).replace("FinWeek", Week)
						.replace("FinYear", Year).replace("Division", getContext().getStringProperty("Division")));
		System.out.println("DB Total: " + DBTotal);

		UIValues = pageWeeklyPlanningPO.UIResults(objMonthlyPlanningStoreObjects.MPTable, extentTest,
				objWeeklyPlanningStoreObjects.DepartmentNames, objWeeklyPlanningStoreObjects.FourWeekofMonth);
		pageMonthlyPlanningPO.CompareValues(extentTest, DBTotal, UIValues);

		UITotal = pageMonthlyPlanningGroupPO.WPGetTotal(extentTest, objMonthlyPlanningGroupObjects.RowNameTotal,
				objWeeklyPlanningStoreObjects.FourWeekofMonth);
		pageMonthlyPlanningPO.CompareValues(extentTest, DBTotal, UITotal);
	}

	// Item Price
	@Test(description = "Weekly Planning - WOW Group, Item Price history - First Week", priority = 17)
	public void ItemPriceHistoryFirsthWeek() throws Exception {
		pageWeeklyPlanningPO = new WeeklyPlanningStorePage().createPage(getDriver(), getContext(), log, status, data,
				getReport(), getReportLogger(), WeeklyPlanningStorePage.class);
		pageMonthlyPlanningGroupPO = new MonthlyPlanningGroupPage().createPage(getDriver(), getContext(), log, status,
				data, getReport(), getReportLogger(), MonthlyPlanningGroupPage.class);
		TestStatus status = getTestStatus();
		System.out.println("Entering into TestCase");
		pageWeeklyPlanningPO.SelectHistoryDropdownToView(objWeeklyPlanningStoreObjects, extentTest, "Item Price");
		Name = pageWeeklyPlanningPO.MonthName(objWeeklyPlanningStoreObjects, extentTest,
				objWeeklyPlanningStoreObjects.FirstWeekofMonth);
		YrName = pageWeeklyPlanningPO.MonthName(objWeeklyPlanningStoreObjects, extentTest,
				objWeeklyPlanningStoreObjects.CurrentWeekBGDT);
		WeekName = Name.split(" ");
		YearName = YrName.split(" ");
		Week = WeekName[0];
		Year = getContext().getStringProperty("Year");

		String ItemPriceHistoryTotal = data.getText("ItemPriceHistoryTotal");
		DBTotal = sql.CLRexecuteQuery(getContext(),
				ItemPriceHistoryTotal.replace("Region", getContext().getStringProperty("Region"))
						.replace("Area", getContext().getStringProperty("Area"))
						.replace("Zone", getContext().getStringProperty("Zone")).replace("FinWeek", Week)
						.replace("FinYear", Year).replace("Division", getContext().getStringProperty("Division")));
		System.out.println("DB Total: " + DBTotal);

		UIValues = pageWeeklyPlanningPO.UIResults(objMonthlyPlanningStoreObjects.MPTable, extentTest,
				objWeeklyPlanningStoreObjects.DepartmentNames, objWeeklyPlanningStoreObjects.FirstWeekofMonth);
		pageMonthlyPlanningPO.CompareValueDecimalFields(extentTest, DBTotal, UIValues);

		UITotal = pageMonthlyPlanningGroupPO.WPGetTotal(extentTest, objMonthlyPlanningGroupObjects.RowNameTotal,
				objWeeklyPlanningStoreObjects.FirstWeekofMonth);
		pageMonthlyPlanningPO.CompareValueDecimalFields(extentTest, DBTotal, UITotal);
	}

	@Test(description = "Weekly Planning - WOW Group, Item Price history - Second Week", priority = 18)
	public void ItemPriceHistorySecondWeek() throws Exception {
		pageWeeklyPlanningPO = new WeeklyPlanningStorePage().createPage(getDriver(), getContext(), log, status, data,
				getReport(), getReportLogger(), WeeklyPlanningStorePage.class);
		pageMonthlyPlanningGroupPO = new MonthlyPlanningGroupPage().createPage(getDriver(), getContext(), log, status,
				data, getReport(), getReportLogger(), MonthlyPlanningGroupPage.class);
		TestStatus status = getTestStatus();
		System.out.println("Entering into TestCase");
		Name = pageWeeklyPlanningPO.MonthName(objWeeklyPlanningStoreObjects, extentTest,
				objWeeklyPlanningStoreObjects.SecondWeekofMonth);
		YrName = pageWeeklyPlanningPO.MonthName(objWeeklyPlanningStoreObjects, extentTest,
				objWeeklyPlanningStoreObjects.CurrentWeekBGDT);
		WeekName = Name.split(" ");
		YearName = YrName.split(" ");
		Week = WeekName[0];
		Year = getContext().getStringProperty("Year");

		String ItemPriceHistoryTotal = data.getText("ItemPriceHistoryTotal");
		DBTotal = sql.CLRexecuteQuery(getContext(),
				ItemPriceHistoryTotal.replace("Region", getContext().getStringProperty("Region"))
						.replace("Area", getContext().getStringProperty("Area"))
						.replace("Zone", getContext().getStringProperty("Zone")).replace("FinWeek", Week)
						.replace("FinYear", Year).replace("Division", getContext().getStringProperty("Division")));
		System.out.println("DB Total: " + DBTotal);

		UIValues = pageWeeklyPlanningPO.UIResults(objMonthlyPlanningStoreObjects.MPTable, extentTest,
				objWeeklyPlanningStoreObjects.DepartmentNames, objWeeklyPlanningStoreObjects.SecondWeekofMonth);
		pageMonthlyPlanningPO.CompareValueDecimalFields(extentTest, DBTotal, UIValues);

		UITotal = pageMonthlyPlanningGroupPO.WPGetTotal(extentTest, objMonthlyPlanningGroupObjects.RowNameTotal,
				objWeeklyPlanningStoreObjects.SecondWeekofMonth);
		pageMonthlyPlanningPO.CompareValueDecimalFields(extentTest, DBTotal, UITotal);
	}

	@Test(description = "Weekly Planning - WOW Group, Item Price history - Third Week", priority = 19)
	public void ItemPriceHistoryThirdWeek() throws Exception {
		pageWeeklyPlanningPO = new WeeklyPlanningStorePage().createPage(getDriver(), getContext(), log, status, data,
				getReport(), getReportLogger(), WeeklyPlanningStorePage.class);
		pageMonthlyPlanningGroupPO = new MonthlyPlanningGroupPage().createPage(getDriver(), getContext(), log, status,
				data, getReport(), getReportLogger(), MonthlyPlanningGroupPage.class);
		TestStatus status = getTestStatus();
		System.out.println("Entering into TestCase");
		Name = pageWeeklyPlanningPO.MonthName(objWeeklyPlanningStoreObjects, extentTest,
				objWeeklyPlanningStoreObjects.ThirdWeekofMonth);
		YrName = pageWeeklyPlanningPO.MonthName(objWeeklyPlanningStoreObjects, extentTest,
				objWeeklyPlanningStoreObjects.CurrentWeekBGDT);
		WeekName = Name.split(" ");
		YearName = YrName.split(" ");
		Week = WeekName[0];
		Year = getContext().getStringProperty("Year");

		String ItemPriceHistoryTotal = data.getText("ItemPriceHistoryTotal");
		DBTotal = sql.CLRexecuteQuery(getContext(),
				ItemPriceHistoryTotal.replace("Region", getContext().getStringProperty("Region"))
						.replace("Area", getContext().getStringProperty("Area"))
						.replace("Zone", getContext().getStringProperty("Zone")).replace("FinWeek", Week)
						.replace("FinYear", Year).replace("Division", getContext().getStringProperty("Division")));
		System.out.println("DB Total: " + DBTotal);

		UIValues = pageWeeklyPlanningPO.UIResults(objMonthlyPlanningStoreObjects.MPTable, extentTest,
				objWeeklyPlanningStoreObjects.DepartmentNames, objWeeklyPlanningStoreObjects.ThirdWeekofMonth);
		pageMonthlyPlanningPO.CompareValueDecimalFields(extentTest, DBTotal, UIValues);

		UITotal = pageMonthlyPlanningGroupPO.WPGetTotal(extentTest, objMonthlyPlanningGroupObjects.RowNameTotal,
				objWeeklyPlanningStoreObjects.ThirdWeekofMonth);
		pageMonthlyPlanningPO.CompareValueDecimalFields(extentTest, DBTotal, UITotal);
	}

	@Test(description = "Weekly Planning - WOW Group, Item Price history - Fourth Week", priority = 20)
	public void ItemPriceHistoryFourthWeek() throws Exception {
		pageWeeklyPlanningPO = new WeeklyPlanningStorePage().createPage(getDriver(), getContext(), log, status, data,
				getReport(), getReportLogger(), WeeklyPlanningStorePage.class);
		pageMonthlyPlanningGroupPO = new MonthlyPlanningGroupPage().createPage(getDriver(), getContext(), log, status,
				data, getReport(), getReportLogger(), MonthlyPlanningGroupPage.class);
		TestStatus status = getTestStatus();
		System.out.println("Entering into TestCase");
		Name = pageWeeklyPlanningPO.MonthName(objWeeklyPlanningStoreObjects, extentTest,
				objWeeklyPlanningStoreObjects.FourWeekofMonth);
		YrName = pageWeeklyPlanningPO.MonthName(objWeeklyPlanningStoreObjects, extentTest,
				objWeeklyPlanningStoreObjects.CurrentWeekBGDT);
		WeekName = Name.split(" ");
		YearName = YrName.split(" ");
		Week = WeekName[0];
		Year = getContext().getStringProperty("Year");

		String ItemPriceHistoryTotal = data.getText("ItemPriceHistoryTotal");
		DBTotal = sql.CLRexecuteQuery(getContext(),
				ItemPriceHistoryTotal.replace("Region", getContext().getStringProperty("Region"))
						.replace("Area", getContext().getStringProperty("Area"))
						.replace("Zone", getContext().getStringProperty("Zone")).replace("FinWeek", Week)
						.replace("FinYear", Year).replace("Division", getContext().getStringProperty("Division")));
		System.out.println("DB Total: " + DBTotal);

		UIValues = pageWeeklyPlanningPO.UIResults(objMonthlyPlanningStoreObjects.MPTable, extentTest,
				objWeeklyPlanningStoreObjects.DepartmentNames, objWeeklyPlanningStoreObjects.FourWeekofMonth);
		pageMonthlyPlanningPO.CompareValueDecimalFields(extentTest, DBTotal, UIValues);

		UITotal = pageMonthlyPlanningGroupPO.WPGetTotal(extentTest, objMonthlyPlanningGroupObjects.RowNameTotal,
				objWeeklyPlanningStoreObjects.FourWeekofMonth);
		pageMonthlyPlanningPO.CompareValueDecimalFields(extentTest, DBTotal, UITotal);
	}

	// Wage Percentage
	@Test(description = "Weekly Planning - WOW Group, WagePercentage history - First Week", priority = 21)
	public void WagePercentageHistoryFirsthWeek() throws Exception {
		pageWeeklyPlanningPO = new WeeklyPlanningStorePage().createPage(getDriver(), getContext(), log, status, data,
				getReport(), getReportLogger(), WeeklyPlanningStorePage.class);
		pageMonthlyPlanningGroupPO = new MonthlyPlanningGroupPage().createPage(getDriver(), getContext(), log, status,
				data, getReport(), getReportLogger(), MonthlyPlanningGroupPage.class);
		TestStatus status = getTestStatus();
		System.out.println("Entering into TestCase");
		pageWeeklyPlanningPO.SelectHistoryDropdownToView(objWeeklyPlanningStoreObjects, extentTest, "Wage %");
		Name = pageWeeklyPlanningPO.MonthName(objWeeklyPlanningStoreObjects, extentTest,
				objWeeklyPlanningStoreObjects.FirstWeekofMonth);
		YrName = pageWeeklyPlanningPO.MonthName(objWeeklyPlanningStoreObjects, extentTest,
				objWeeklyPlanningStoreObjects.CurrentWeekBGDT);
		WeekName = Name.split(" ");
		YearName = YrName.split(" ");
		Week = WeekName[0];
		Year = getContext().getStringProperty("Year");

		String WagePercentageHistoryTotal = data.getText("WagePercentageHistoryTotal");
		DBTotal = sql.CLRexecuteQuery(getContext(),
				WagePercentageHistoryTotal.replace("Region", getContext().getStringProperty("Region"))
						.replace("Area", getContext().getStringProperty("Area"))
						.replace("Zone", getContext().getStringProperty("Zone")).replace("FinWeek", Week)
						.replace("FinYear", Year).replace("Division", getContext().getStringProperty("Division")));
		System.out.println("DB Total: " + DBTotal);

		UIValues = pageWeeklyPlanningPO.UIResults(objMonthlyPlanningStoreObjects.MPTable, extentTest,
				objWeeklyPlanningStoreObjects.DepartmentNames, objWeeklyPlanningStoreObjects.FirstWeekofMonth);
		pageMonthlyPlanningPO.CompareValueDecimalFields(extentTest, DBTotal, UIValues);

		UITotal = pageMonthlyPlanningGroupPO.WPGetTotal(extentTest, objMonthlyPlanningGroupObjects.RowNameTotal,
				objWeeklyPlanningStoreObjects.FirstWeekofMonth);
		pageMonthlyPlanningPO.CompareValueDecimalFields(extentTest, DBTotal, UITotal);
	}

	@Test(description = "Weekly Planning - WOW Group, WagePercentage history - Second Week", priority = 22)
	public void WagePercentageHistorySecondWeek() throws Exception {
		pageWeeklyPlanningPO = new WeeklyPlanningStorePage().createPage(getDriver(), getContext(), log, status, data,
				getReport(), getReportLogger(), WeeklyPlanningStorePage.class);
		pageMonthlyPlanningGroupPO = new MonthlyPlanningGroupPage().createPage(getDriver(), getContext(), log, status,
				data, getReport(), getReportLogger(), MonthlyPlanningGroupPage.class);
		TestStatus status = getTestStatus();
		System.out.println("Entering into TestCase");
		Name = pageWeeklyPlanningPO.MonthName(objWeeklyPlanningStoreObjects, extentTest,
				objWeeklyPlanningStoreObjects.SecondWeekofMonth);
		YrName = pageWeeklyPlanningPO.MonthName(objWeeklyPlanningStoreObjects, extentTest,
				objWeeklyPlanningStoreObjects.CurrentWeekBGDT);
		WeekName = Name.split(" ");
		YearName = YrName.split(" ");
		Week = WeekName[0];
		Year = getContext().getStringProperty("Year");

		String WagePercentageHistoryTotal = data.getText("WagePercentageHistoryTotal");
		DBTotal = sql.CLRexecuteQuery(getContext(),
				WagePercentageHistoryTotal.replace("Region", getContext().getStringProperty("Region"))
						.replace("Area", getContext().getStringProperty("Area"))
						.replace("Zone", getContext().getStringProperty("Zone")).replace("FinWeek", Week)
						.replace("FinYear", Year).replace("Division", getContext().getStringProperty("Division")));
		System.out.println("DB Total: " + DBTotal);

		UIValues = pageWeeklyPlanningPO.UIResults(objMonthlyPlanningStoreObjects.MPTable, extentTest,
				objWeeklyPlanningStoreObjects.DepartmentNames, objWeeklyPlanningStoreObjects.SecondWeekofMonth);
		pageMonthlyPlanningPO.CompareValueDecimalFields(extentTest, DBTotal, UIValues);

		UITotal = pageMonthlyPlanningGroupPO.WPGetTotal(extentTest, objMonthlyPlanningGroupObjects.RowNameTotal,
				objWeeklyPlanningStoreObjects.SecondWeekofMonth);
		pageMonthlyPlanningPO.CompareValueDecimalFields(extentTest, DBTotal, UITotal);
	}

	@Test(description = "Weekly Planning - WOW Group, WagePercentage history - Third Week", priority = 23)
	public void WagePercentageHistoryThirdWeek() throws Exception {
		pageWeeklyPlanningPO = new WeeklyPlanningStorePage().createPage(getDriver(), getContext(), log, status, data,
				getReport(), getReportLogger(), WeeklyPlanningStorePage.class);
		pageMonthlyPlanningGroupPO = new MonthlyPlanningGroupPage().createPage(getDriver(), getContext(), log, status,
				data, getReport(), getReportLogger(), MonthlyPlanningGroupPage.class);
		TestStatus status = getTestStatus();
		System.out.println("Entering into TestCase");
		Name = pageWeeklyPlanningPO.MonthName(objWeeklyPlanningStoreObjects, extentTest,
				objWeeklyPlanningStoreObjects.ThirdWeekofMonth);
		YrName = pageWeeklyPlanningPO.MonthName(objWeeklyPlanningStoreObjects, extentTest,
				objWeeklyPlanningStoreObjects.CurrentWeekBGDT);
		WeekName = Name.split(" ");
		YearName = YrName.split(" ");
		Week = WeekName[0];
		Year = getContext().getStringProperty("Year");

		String WagePercentageHistoryTotal = data.getText("WagePercentageHistoryTotal");
		DBTotal = sql.CLRexecuteQuery(getContext(),
				WagePercentageHistoryTotal.replace("Region", getContext().getStringProperty("Region"))
						.replace("Area", getContext().getStringProperty("Area"))
						.replace("Zone", getContext().getStringProperty("Zone")).replace("FinWeek", Week)
						.replace("FinYear", Year).replace("Division", getContext().getStringProperty("Division")));
		System.out.println("DB Total: " + DBTotal);

		UIValues = pageWeeklyPlanningPO.UIResults(objMonthlyPlanningStoreObjects.MPTable, extentTest,
				objWeeklyPlanningStoreObjects.DepartmentNames, objWeeklyPlanningStoreObjects.ThirdWeekofMonth);
		pageMonthlyPlanningPO.CompareValueDecimalFields(extentTest, DBTotal, UIValues);

		UITotal = pageMonthlyPlanningGroupPO.WPGetTotal(extentTest, objMonthlyPlanningGroupObjects.RowNameTotal,
				objWeeklyPlanningStoreObjects.ThirdWeekofMonth);
		pageMonthlyPlanningPO.CompareValueDecimalFields(extentTest, DBTotal, UITotal);
	}

	@Test(description = "Weekly Planning - WOW Group, WagePercentage history - Fourth Week", priority = 24)
	public void WagePercentageHistoryFourthWeek() throws Exception {
		pageWeeklyPlanningPO = new WeeklyPlanningStorePage().createPage(getDriver(), getContext(), log, status, data,
				getReport(), getReportLogger(), WeeklyPlanningStorePage.class);
		pageMonthlyPlanningGroupPO = new MonthlyPlanningGroupPage().createPage(getDriver(), getContext(), log, status,
				data, getReport(), getReportLogger(), MonthlyPlanningGroupPage.class);
		TestStatus status = getTestStatus();
		System.out.println("Entering into TestCase");

		Name = pageWeeklyPlanningPO.MonthName(objWeeklyPlanningStoreObjects, extentTest,
				objWeeklyPlanningStoreObjects.FourWeekofMonth);
		YrName = pageWeeklyPlanningPO.MonthName(objWeeklyPlanningStoreObjects, extentTest,
				objWeeklyPlanningStoreObjects.CurrentWeekBGDT);
		WeekName = Name.split(" ");
		YearName = YrName.split(" ");
		Week = WeekName[0];
		Year = getContext().getStringProperty("Year");

		String WagePercentageHistoryTotal = data.getText("WagePercentageHistoryTotal");
		DBTotal = sql.CLRexecuteQuery(getContext(),
				WagePercentageHistoryTotal.replace("Region", getContext().getStringProperty("Region"))
						.replace("Area", getContext().getStringProperty("Area"))
						.replace("Zone", getContext().getStringProperty("Zone")).replace("FinWeek", Week)
						.replace("FinYear", Year).replace("Division", getContext().getStringProperty("Division")));
		System.out.println("DB Total: " + DBTotal);

		UIValues = pageWeeklyPlanningPO.UIResults(objMonthlyPlanningStoreObjects.MPTable, extentTest,
				objWeeklyPlanningStoreObjects.DepartmentNames, objWeeklyPlanningStoreObjects.FourWeekofMonth);
		pageMonthlyPlanningPO.CompareValueDecimalFields(extentTest, DBTotal, UIValues);

		UITotal = pageMonthlyPlanningGroupPO.WPGetTotal(extentTest, objMonthlyPlanningGroupObjects.RowNameTotal,
				objWeeklyPlanningStoreObjects.FourWeekofMonth);
		pageMonthlyPlanningPO.CompareValueDecimalFields(extentTest, DBTotal, UITotal);
	}

	// Paid Hours
	@Test(description = "Weekly Planning - WOW Group, Paid Hours history - First Week", priority = 25)
	public void PaidHoursHistoryFirsthWeek() throws Exception {
		pageWeeklyPlanningPO = new WeeklyPlanningStorePage().createPage(getDriver(), getContext(), log, status, data,
				getReport(), getReportLogger(), WeeklyPlanningStorePage.class);
		pageMonthlyPlanningGroupPO = new MonthlyPlanningGroupPage().createPage(getDriver(), getContext(), log, status,
				data, getReport(), getReportLogger(), MonthlyPlanningGroupPage.class);
		TestStatus status = getTestStatus();
		System.out.println("Entering into TestCase");
		pageWeeklyPlanningPO.SelectHistoryDropdownToView(objWeeklyPlanningStoreObjects, extentTest, "Paid Hours");
		Name = pageWeeklyPlanningPO.MonthName(objWeeklyPlanningStoreObjects, extentTest,
				objWeeklyPlanningStoreObjects.FirstWeekofMonth);
		YrName = pageWeeklyPlanningPO.MonthName(objWeeklyPlanningStoreObjects, extentTest,
				objWeeklyPlanningStoreObjects.CurrentWeekBGDT);
		WeekName = Name.split(" ");
		YearName = YrName.split(" ");
		Week = WeekName[0];
		Year = getContext().getStringProperty("Year");

		String PaidHoursHistoryTotal = data.getText("PaidHoursHistoryTotal");
		DBTotal = sql.CLRexecuteQuery(getContext(),
				PaidHoursHistoryTotal.replace("Region", getContext().getStringProperty("Region"))
						.replace("Area", getContext().getStringProperty("Area"))
						.replace("Zone", getContext().getStringProperty("Zone")).replace("FinWeek", Week)
						.replace("FinYear", Year).replace("Division", getContext().getStringProperty("Division")));
		System.out.println("DB Total: " + DBTotal);

		UIValues = pageWeeklyPlanningPO.UIResults(objMonthlyPlanningStoreObjects.MPTable, extentTest,
				objWeeklyPlanningStoreObjects.DepartmentNames, objWeeklyPlanningStoreObjects.FirstWeekofMonth);
		pageMonthlyPlanningPO.CompareValues(extentTest, DBTotal, UIValues);

		UITotal = pageMonthlyPlanningGroupPO.WPGetTotal(extentTest, objMonthlyPlanningGroupObjects.RowNameTotal,
				objWeeklyPlanningStoreObjects.FirstWeekofMonth);
		pageMonthlyPlanningPO.CompareValues(extentTest, DBTotal, UITotal);
	}

	@Test(description = "Weekly Planning - WOW Group, Paid Hours history - Second Week", priority = 26)
	public void PaidHoursHistorySecondWeek() throws Exception {
		pageWeeklyPlanningPO = new WeeklyPlanningStorePage().createPage(getDriver(), getContext(), log, status, data,
				getReport(), getReportLogger(), WeeklyPlanningStorePage.class);
		pageMonthlyPlanningGroupPO = new MonthlyPlanningGroupPage().createPage(getDriver(), getContext(), log, status,
				data, getReport(), getReportLogger(), MonthlyPlanningGroupPage.class);
		TestStatus status = getTestStatus();
		System.out.println("Entering into TestCase");
		Name = pageWeeklyPlanningPO.MonthName(objWeeklyPlanningStoreObjects, extentTest,
				objWeeklyPlanningStoreObjects.SecondWeekofMonth);
		YrName = pageWeeklyPlanningPO.MonthName(objWeeklyPlanningStoreObjects, extentTest,
				objWeeklyPlanningStoreObjects.CurrentWeekBGDT);
		WeekName = Name.split(" ");
		YearName = YrName.split(" ");
		Week = WeekName[0];
		Year = getContext().getStringProperty("Year");

		String PaidHoursHistoryTotal = data.getText("PaidHoursHistoryTotal");
		DBTotal = sql.CLRexecuteQuery(getContext(),
				PaidHoursHistoryTotal.replace("Region", getContext().getStringProperty("Region"))
						.replace("Area", getContext().getStringProperty("Area"))
						.replace("Zone", getContext().getStringProperty("Zone")).replace("FinWeek", Week)
						.replace("FinYear", Year).replace("Division", getContext().getStringProperty("Division")));
		System.out.println("DB Total: " + DBTotal);

		UIValues = pageWeeklyPlanningPO.UIResults(objMonthlyPlanningStoreObjects.MPTable, extentTest,
				objWeeklyPlanningStoreObjects.DepartmentNames, objWeeklyPlanningStoreObjects.SecondWeekofMonth);
		pageMonthlyPlanningPO.CompareValues(extentTest, DBTotal, UIValues);

		UITotal = pageMonthlyPlanningGroupPO.WPGetTotal(extentTest, objMonthlyPlanningGroupObjects.RowNameTotal,
				objWeeklyPlanningStoreObjects.SecondWeekofMonth);
		pageMonthlyPlanningPO.CompareValues(extentTest, DBTotal, UITotal);
	}

	@Test(description = "Weekly Planning - WOW Group, Paid Hours history - Third Week", priority = 27)
	public void PaidHoursHistoryThirdWeek() throws Exception {
		pageWeeklyPlanningPO = new WeeklyPlanningStorePage().createPage(getDriver(), getContext(), log, status, data,
				getReport(), getReportLogger(), WeeklyPlanningStorePage.class);
		pageMonthlyPlanningGroupPO = new MonthlyPlanningGroupPage().createPage(getDriver(), getContext(), log, status,
				data, getReport(), getReportLogger(), MonthlyPlanningGroupPage.class);
		TestStatus status = getTestStatus();
		System.out.println("Entering into TestCase");
		Name = pageWeeklyPlanningPO.MonthName(objWeeklyPlanningStoreObjects, extentTest,
				objWeeklyPlanningStoreObjects.ThirdWeekofMonth);
		YrName = pageWeeklyPlanningPO.MonthName(objWeeklyPlanningStoreObjects, extentTest,
				objWeeklyPlanningStoreObjects.CurrentWeekBGDT);
		WeekName = Name.split(" ");
		YearName = YrName.split(" ");
		Week = WeekName[0];
		Year = getContext().getStringProperty("Year");

		String PaidHoursHistoryTotal = data.getText("PaidHoursHistoryTotal");
		DBTotal = sql.CLRexecuteQuery(getContext(),
				PaidHoursHistoryTotal.replace("Region", getContext().getStringProperty("Region"))
						.replace("Area", getContext().getStringProperty("Area"))
						.replace("Zone", getContext().getStringProperty("Zone")).replace("FinWeek", Week)
						.replace("FinYear", Year).replace("Division", getContext().getStringProperty("Division")));
		System.out.println("DB Total: " + DBTotal);

		UIValues = pageWeeklyPlanningPO.UIResults(objMonthlyPlanningStoreObjects.MPTable, extentTest,
				objWeeklyPlanningStoreObjects.DepartmentNames, objWeeklyPlanningStoreObjects.ThirdWeekofMonth);
		pageMonthlyPlanningPO.CompareValues(extentTest, DBTotal, UIValues);

		UITotal = pageMonthlyPlanningGroupPO.WPGetTotal(extentTest, objMonthlyPlanningGroupObjects.RowNameTotal,
				objWeeklyPlanningStoreObjects.ThirdWeekofMonth);
		pageMonthlyPlanningPO.CompareValues(extentTest, DBTotal, UITotal);
	}

	@Test(description = "Weekly Planning - WOW Group, Paid Hours history - Fourth Week", priority = 28)
	public void PaidHoursHistoryFourthWeek() throws Exception {
		pageWeeklyPlanningPO = new WeeklyPlanningStorePage().createPage(getDriver(), getContext(), log, status, data,
				getReport(), getReportLogger(), WeeklyPlanningStorePage.class);
		pageMonthlyPlanningGroupPO = new MonthlyPlanningGroupPage().createPage(getDriver(), getContext(), log, status,
				data, getReport(), getReportLogger(), MonthlyPlanningGroupPage.class);
		TestStatus status = getTestStatus();
		System.out.println("Entering into TestCase");
		Name = pageWeeklyPlanningPO.MonthName(objWeeklyPlanningStoreObjects, extentTest,
				objWeeklyPlanningStoreObjects.FourWeekofMonth);
		YrName = pageWeeklyPlanningPO.MonthName(objWeeklyPlanningStoreObjects, extentTest,
				objWeeklyPlanningStoreObjects.CurrentWeekBGDT);
		WeekName = Name.split(" ");
		YearName = YrName.split(" ");
		Week = WeekName[0];
		Year = getContext().getStringProperty("Year");

		String PaidHoursHistoryTotal = data.getText("PaidHoursHistoryTotal");
		DBTotal = sql.CLRexecuteQuery(getContext(),
				PaidHoursHistoryTotal.replace("Region", getContext().getStringProperty("Region"))
						.replace("Area", getContext().getStringProperty("Area"))
						.replace("Zone", getContext().getStringProperty("Zone")).replace("FinWeek", Week)
						.replace("FinYear", Year).replace("Division", getContext().getStringProperty("Division")));
		System.out.println("DB Total: " + DBTotal);

		UIValues = pageWeeklyPlanningPO.UIResults(objMonthlyPlanningStoreObjects.MPTable, extentTest,
				objWeeklyPlanningStoreObjects.DepartmentNames, objWeeklyPlanningStoreObjects.FourWeekofMonth);
		pageMonthlyPlanningPO.CompareValues(extentTest, DBTotal, UIValues);

		UITotal = pageMonthlyPlanningGroupPO.WPGetTotal(extentTest, objMonthlyPlanningGroupObjects.RowNameTotal,
				objWeeklyPlanningStoreObjects.FourWeekofMonth);
		pageMonthlyPlanningPO.CompareValues(extentTest, DBTotal, UITotal);
	}

	// CPH
	@Test(description = "Weekly Planning - WOW Group, CPH history - First Week", priority = 29)
	public void CPHHistoryFirsthWeek() throws Exception {
		pageWeeklyPlanningPO = new WeeklyPlanningStorePage().createPage(getDriver(), getContext(), log, status, data,
				getReport(), getReportLogger(), WeeklyPlanningStorePage.class);
		pageMonthlyPlanningGroupPO = new MonthlyPlanningGroupPage().createPage(getDriver(), getContext(), log, status,
				data, getReport(), getReportLogger(), MonthlyPlanningGroupPage.class);
		TestStatus status = getTestStatus();
		System.out.println("Entering into TestCase");
		pageWeeklyPlanningPO.SelectHistoryDropdownToView(objWeeklyPlanningStoreObjects, extentTest, "CPH");
		Name = pageWeeklyPlanningPO.MonthName(objWeeklyPlanningStoreObjects, extentTest,
				objWeeklyPlanningStoreObjects.FirstWeekofMonth);
		YrName = pageWeeklyPlanningPO.MonthName(objWeeklyPlanningStoreObjects, extentTest,
				objWeeklyPlanningStoreObjects.CurrentWeekBGDT);
		WeekName = Name.split(" ");
		YearName = YrName.split(" ");
		Week = WeekName[0];
		Year = getContext().getStringProperty("Year");

		String CPHHistoryTotal = data.getText("CPHHistoryTotal");
		DBTotal = sql.CLRexecuteQuery(getContext(),
				CPHHistoryTotal.replace("Region", getContext().getStringProperty("Region"))
						.replace("Area", getContext().getStringProperty("Area"))
						.replace("Zone", getContext().getStringProperty("Zone")).replace("FinWeek", Week)
						.replace("FinYear", Year).replace("Division", getContext().getStringProperty("Division")));
		System.out.println("DB Total: " + DBTotal);

		UIValues = pageWeeklyPlanningPO.UIResults(objMonthlyPlanningStoreObjects.MPTable, extentTest,
				objWeeklyPlanningStoreObjects.DepartmentNames, objWeeklyPlanningStoreObjects.FirstWeekofMonth);
		pageMonthlyPlanningPO.CompareValueDecimalFields(extentTest, DBTotal, UIValues);

		UITotal = pageMonthlyPlanningGroupPO.WPGetTotal(extentTest, objMonthlyPlanningGroupObjects.RowNameTotal,
				objWeeklyPlanningStoreObjects.FirstWeekofMonth);
		pageMonthlyPlanningPO.CompareValueDecimalFields(extentTest, DBTotal, UITotal);
	}

	@Test(description = "Weekly Planning - WOW Group, CPH history - Second Week", priority = 30)
	public void CPHHistorySecondWeek() throws Exception {
		pageWeeklyPlanningPO = new WeeklyPlanningStorePage().createPage(getDriver(), getContext(), log, status, data,
				getReport(), getReportLogger(), WeeklyPlanningStorePage.class);
		pageMonthlyPlanningGroupPO = new MonthlyPlanningGroupPage().createPage(getDriver(), getContext(), log, status,
				data, getReport(), getReportLogger(), MonthlyPlanningGroupPage.class);
		TestStatus status = getTestStatus();
		System.out.println("Entering into TestCase");
		Name = pageWeeklyPlanningPO.MonthName(objWeeklyPlanningStoreObjects, extentTest,
				objWeeklyPlanningStoreObjects.SecondWeekofMonth);
		YrName = pageWeeklyPlanningPO.MonthName(objWeeklyPlanningStoreObjects, extentTest,
				objWeeklyPlanningStoreObjects.CurrentWeekBGDT);
		WeekName = Name.split(" ");
		YearName = YrName.split(" ");
		Week = WeekName[0];
		Year = getContext().getStringProperty("Year");

		String CPHHistoryTotal = data.getText("CPHHistoryTotal");
		DBTotal = sql.CLRexecuteQuery(getContext(),
				CPHHistoryTotal.replace("Region", getContext().getStringProperty("Region"))
						.replace("Area", getContext().getStringProperty("Area"))
						.replace("Zone", getContext().getStringProperty("Zone")).replace("FinWeek", Week)
						.replace("FinYear", Year).replace("Division", getContext().getStringProperty("Division")));
		System.out.println("DB Total: " + DBTotal);

		UIValues = pageWeeklyPlanningPO.UIResults(objMonthlyPlanningStoreObjects.MPTable, extentTest,
				objWeeklyPlanningStoreObjects.DepartmentNames, objWeeklyPlanningStoreObjects.SecondWeekofMonth);
		pageMonthlyPlanningPO.CompareValueDecimalFields(extentTest, DBTotal, UIValues);

		UITotal = pageMonthlyPlanningGroupPO.WPGetTotal(extentTest, objMonthlyPlanningGroupObjects.RowNameTotal,
				objWeeklyPlanningStoreObjects.SecondWeekofMonth);
		pageMonthlyPlanningPO.CompareValueDecimalFields(extentTest, DBTotal, UITotal);
	}

	@Test(description = "Weekly Planning - WOW Group, CPH history - Third Week", priority = 31)
	public void CPHHistoryThirdWeek() throws Exception {
		pageWeeklyPlanningPO = new WeeklyPlanningStorePage().createPage(getDriver(), getContext(), log, status, data,
				getReport(), getReportLogger(), WeeklyPlanningStorePage.class);
		pageMonthlyPlanningGroupPO = new MonthlyPlanningGroupPage().createPage(getDriver(), getContext(), log, status,
				data, getReport(), getReportLogger(), MonthlyPlanningGroupPage.class);
		TestStatus status = getTestStatus();
		System.out.println("Entering into TestCase");
		Name = pageWeeklyPlanningPO.MonthName(objWeeklyPlanningStoreObjects, extentTest,
				objWeeklyPlanningStoreObjects.ThirdWeekofMonth);
		YrName = pageWeeklyPlanningPO.MonthName(objWeeklyPlanningStoreObjects, extentTest,
				objWeeklyPlanningStoreObjects.CurrentWeekBGDT);
		WeekName = Name.split(" ");
		YearName = YrName.split(" ");
		Week = WeekName[0];
		Year = getContext().getStringProperty("Year");

		String CPHHistoryTotal = data.getText("CPHHistoryTotal");
		DBTotal = sql.CLRexecuteQuery(getContext(),
				CPHHistoryTotal.replace("Region", getContext().getStringProperty("Region"))
						.replace("Area", getContext().getStringProperty("Area"))
						.replace("Zone", getContext().getStringProperty("Zone")).replace("FinWeek", Week)
						.replace("FinYear", Year).replace("Division", getContext().getStringProperty("Division")));
		System.out.println("DB Total: " + DBTotal);

		UIValues = pageWeeklyPlanningPO.UIResults(objMonthlyPlanningStoreObjects.MPTable, extentTest,
				objWeeklyPlanningStoreObjects.DepartmentNames, objWeeklyPlanningStoreObjects.ThirdWeekofMonth);
		pageMonthlyPlanningPO.CompareValueDecimalFields(extentTest, DBTotal, UIValues);

		UITotal = pageMonthlyPlanningGroupPO.WPGetTotal(extentTest, objMonthlyPlanningGroupObjects.RowNameTotal,
				objWeeklyPlanningStoreObjects.ThirdWeekofMonth);
		pageMonthlyPlanningPO.CompareValueDecimalFields(extentTest, DBTotal, UITotal);
	}

	@Test(description = "Weekly Planning - WOW Group, CPH history - Fourth Week", priority = 32)
	public void CPHHistoryFourthWeek() throws Exception {
		pageWeeklyPlanningPO = new WeeklyPlanningStorePage().createPage(getDriver(), getContext(), log, status, data,
				getReport(), getReportLogger(), WeeklyPlanningStorePage.class);
		pageMonthlyPlanningGroupPO = new MonthlyPlanningGroupPage().createPage(getDriver(), getContext(), log, status,
				data, getReport(), getReportLogger(), MonthlyPlanningGroupPage.class);
		TestStatus status = getTestStatus();
		System.out.println("Entering into TestCase");
		Name = pageWeeklyPlanningPO.MonthName(objWeeklyPlanningStoreObjects, extentTest,
				objWeeklyPlanningStoreObjects.FourWeekofMonth);
		YrName = pageWeeklyPlanningPO.MonthName(objWeeklyPlanningStoreObjects, extentTest,
				objWeeklyPlanningStoreObjects.CurrentWeekBGDT);
		WeekName = Name.split(" ");
		YearName = YrName.split(" ");
		Week = WeekName[0];
		Year = getContext().getStringProperty("Year");

		String CPHHistoryTotal = data.getText("CPHHistoryTotal");
		DBTotal = sql.CLRexecuteQuery(getContext(),
				CPHHistoryTotal.replace("Region", getContext().getStringProperty("Region"))
						.replace("Area", getContext().getStringProperty("Area"))
						.replace("Zone", getContext().getStringProperty("Zone")).replace("FinWeek", Week)
						.replace("FinYear", Year).replace("Division", getContext().getStringProperty("Division")));
		System.out.println("DB Total: " + DBTotal);

		UIValues = pageWeeklyPlanningPO.UIResults(objMonthlyPlanningStoreObjects.MPTable, extentTest,
				objWeeklyPlanningStoreObjects.DepartmentNames, objWeeklyPlanningStoreObjects.FourWeekofMonth);
		pageMonthlyPlanningPO.CompareValueDecimalFields(extentTest, DBTotal, UIValues);

		UITotal = pageMonthlyPlanningGroupPO.WPGetTotal(extentTest, objMonthlyPlanningGroupObjects.RowNameTotal,
				objWeeklyPlanningStoreObjects.FourWeekofMonth);
		pageMonthlyPlanningPO.CompareValueDecimalFields(extentTest, DBTotal, UITotal);
	}
	
	// Operational Ratio
		/*@Test(description = "Weekly Planning - BU, OR History - First Week", priority = 33)
		public void ORHistoryFirstWeek() throws Exception {
		pageWeeklyPlanningPO = new WeeklyPlanningStorePage().createPage(getDriver(), getContext(), log, status, data,
		getReport(), getReportLogger(), WeeklyPlanningStorePage.class);
		pageMonthlyPlanningGroupPO = new MonthlyPlanningGroupPage().createPage(getDriver(), getContext(), log, status,
		data, getReport(), getReportLogger(), MonthlyPlanningGroupPage.class);
		TestStatus status = getTestStatus();
		System.out.println("Entering into TestCase");
		pageWeeklyPlanningPO.SelectHistoryDropdownToView(objWeeklyPlanningStoreObjects, extentTest,
		"Operating Ratio History");
		Name = pageWeeklyPlanningPO.MonthName(objWeeklyPlanningStoreObjects, extentTest,
		objWeeklyPlanningStoreObjects.FirstWeekofMonth);
		YrName = pageWeeklyPlanningPO.MonthName(objWeeklyPlanningStoreObjects, extentTest,
		objWeeklyPlanningStoreObjects.CurrentWeekBGDT);
		WeekName = Name.split(" ");
		YearName = YrName.split(" ");
		Week = WeekName[0];
		Wk = getContext().getStringProperty("SelectFiscalWeek");
		HistWk = Integer.parseInt(Wk) - 4;
		HWk1 = String.valueOf(HistWk);

		Report_AddStep("testcase", " OR VIEW - FIRST HISTORY WEEK VALIDATION", "", "", "");

		String ORHistory = data.getText("ORHistory");
		DBValues = sql.CLRexecuteQuery(getContext(),
		ORHistory.replace("Region", getContext().getStringProperty("Region"))
		.replace("Area", getContext().getStringProperty("Area"))
		.replace("Zone", getContext().getStringProperty("Zone")).replace("FinWk", Week)
		.replace("FinYear", getContext().getStringProperty("Year")).replace("Week", HWk1));
		System.out.println("DB Value: " + DBValues);

		String ORHistoryTotal = data.getText("ORHistoryTotal");
		DBTotal = sql.CLRexecuteQuery(getContext(),
		ORHistoryTotal.replace("Region", getContext().getStringProperty("Region"))
		.replace("Area", getContext().getStringProperty("Area"))
		.replace("Zone", getContext().getStringProperty("Zone")).replace("FinWk", Week)
		.replace("FinYear", getContext().getStringProperty("Year")).replace("Week", HWk1));
		System.out.println("DB Total: " + DBTotal);

		UIValues = pageWeeklyPlanningPO.UIResults(objMonthlyPlanningStoreObjects.MPTable, extentTest,
		objWeeklyPlanningStoreObjects.DepartmentNames, objWeeklyPlanningStoreObjects.FirstWeekofMonth);
		pageMonthlyPlanningPO.CompareValueDecimalFields(extentTest, DBValues, UIValues);

		UITotal = pageMonthlyPlanningGroupPO.WPGetTotal(extentTest, objMonthlyPlanningGroupObjects.RowNameTotal,
		objWeeklyPlanningStoreObjects.FirstWeekofMonth);
		pageMonthlyPlanningPO.CompareValueDecimalFields(extentTest, DBTotal, UITotal);
		}

		@Test(description = "Weekly Planning - BU, OR History - Second Week", priority = 33)
		public void ORHistorySecondWeek() throws Exception {
		pageWeeklyPlanningPO = new WeeklyPlanningStorePage().createPage(getDriver(), getContext(), log, status, data,
		getReport(), getReportLogger(), WeeklyPlanningStorePage.class);
		pageMonthlyPlanningGroupPO = new MonthlyPlanningGroupPage().createPage(getDriver(), getContext(), log, status,
		data, getReport(), getReportLogger(), MonthlyPlanningGroupPage.class);
		TestStatus status = getTestStatus();
		System.out.println("Entering into TestCase");
		Name = pageWeeklyPlanningPO.MonthName(objWeeklyPlanningStoreObjects, extentTest,
		objWeeklyPlanningStoreObjects.SecondWeekofMonth);
		YrName = pageWeeklyPlanningPO.MonthName(objWeeklyPlanningStoreObjects, extentTest,
		objWeeklyPlanningStoreObjects.CurrentWeekBGDT);
		WeekName = Name.split(" ");
		YearName = YrName.split(" ");
		Week = WeekName[0];
		Wk = getContext().getStringProperty("SelectFiscalWeek");
		HistWk = Integer.parseInt(Wk) - 3;
		HWk2 = String.valueOf(HistWk);

		Report_AddStep("testcase", " OR VIEW - FIRST HISTORY WEEK VALIDATION", "", "", "");

		String ORHistory = data.getText("ORHistory");
		DBValues = sql.CLRexecuteQuery(getContext(),
		ORHistory.replace("Region", getContext().getStringProperty("Region"))
		.replace("Area", getContext().getStringProperty("Area"))
		.replace("Zone", getContext().getStringProperty("Zone")).replace("FinWk", Week)
		.replace("FinYear", getContext().getStringProperty("Year")).replace("Week", HWk2));
		System.out.println("DB Value: " + DBValues);

		String ORHistoryTotal = data.getText("ORHistoryTotal");
		DBTotal = sql.CLRexecuteQuery(getContext(),
		ORHistoryTotal.replace("Region", getContext().getStringProperty("Region"))
		.replace("Area", getContext().getStringProperty("Area"))
		.replace("Zone", getContext().getStringProperty("Zone")).replace("FinWk", Week)
		.replace("FinYear", getContext().getStringProperty("Year")).replace("Week", HWk2));
		System.out.println("DB Total: " + DBTotal);

		UIValues = pageWeeklyPlanningPO.UIResults(objMonthlyPlanningStoreObjects.MPTable, extentTest,
		objWeeklyPlanningStoreObjects.DepartmentNames, objWeeklyPlanningStoreObjects.SecondWeekofMonth);
		pageMonthlyPlanningPO.CompareValueDecimalFields(extentTest, DBValues, UIValues);

		UITotal = pageMonthlyPlanningGroupPO.WPGetTotal(extentTest, objMonthlyPlanningGroupObjects.RowNameTotal,
		objWeeklyPlanningStoreObjects.SecondWeekofMonth);
		pageMonthlyPlanningPO.CompareValueDecimalFields(extentTest, DBTotal, UITotal);
		}

		@Test(description = "Weekly Planning - BU, OR History - Third Week", priority = 33)
		public void ORHistoryThirdWeek() throws Exception {
		pageWeeklyPlanningPO = new WeeklyPlanningStorePage().createPage(getDriver(), getContext(), log, status, data,
		getReport(), getReportLogger(), WeeklyPlanningStorePage.class);
		pageMonthlyPlanningGroupPO = new MonthlyPlanningGroupPage().createPage(getDriver(), getContext(), log, status,
		data, getReport(), getReportLogger(), MonthlyPlanningGroupPage.class);
		TestStatus status = getTestStatus();
		System.out.println("Entering into TestCase");
		Name = pageWeeklyPlanningPO.MonthName(objWeeklyPlanningStoreObjects, extentTest,
		objWeeklyPlanningStoreObjects.ThirdWeekofMonth);
		YrName = pageWeeklyPlanningPO.MonthName(objWeeklyPlanningStoreObjects, extentTest,
		objWeeklyPlanningStoreObjects.CurrentWeekBGDT);
		WeekName = Name.split(" ");
		YearName = YrName.split(" ");
		Week = WeekName[0];
		Wk = getContext().getStringProperty("SelectFiscalWeek");
		HistWk = Integer.parseInt(Wk) - 2;
		HWk3 = String.valueOf(HistWk);

		Report_AddStep("testcase", " OR VIEW - FIRST HISTORY WEEK VALIDATION", "", "", "");

		String ORHistory = data.getText("ORHistory");
		DBValues = sql.CLRexecuteQuery(getContext(),
		ORHistory.replace("Region", getContext().getStringProperty("Region"))
		.replace("Area", getContext().getStringProperty("Area"))
		.replace("Zone", getContext().getStringProperty("Zone")).replace("FinWk", Week)
		.replace("FinYear", getContext().getStringProperty("Year")).replace("Week", HWk3));
		System.out.println("DB Value: " + DBValues);

		String ORHistoryTotal = data.getText("ORHistoryTotal");
		DBTotal = sql.CLRexecuteQuery(getContext(),
		ORHistoryTotal.replace("Region", getContext().getStringProperty("Region"))
		.replace("Area", getContext().getStringProperty("Area"))
		.replace("Zone", getContext().getStringProperty("Zone")).replace("FinWk", Week)
		.replace("FinYear", getContext().getStringProperty("Year")).replace("Week", HWk3));
		System.out.println("DB Total: " + DBTotal);

		UIValues = pageWeeklyPlanningPO.UIResults(objMonthlyPlanningStoreObjects.MPTable, extentTest,
		objWeeklyPlanningStoreObjects.DepartmentNames, objWeeklyPlanningStoreObjects.ThirdWeekofMonth);
		pageMonthlyPlanningPO.CompareValueDecimalFields(extentTest, DBValues, UIValues);

		UITotal = pageMonthlyPlanningGroupPO.WPGetTotal(extentTest, objMonthlyPlanningGroupObjects.RowNameTotal,
		objWeeklyPlanningStoreObjects.ThirdWeekofMonth);
		pageMonthlyPlanningPO.CompareValueDecimalFields(extentTest, DBTotal, UITotal);
		}

		@Test(description = "Weekly Planning - BU, OR History - Fourth Week", priority = 33)
		public void ORHistoryFourthWeek() throws Exception {
		pageWeeklyPlanningPO = new WeeklyPlanningStorePage().createPage(getDriver(), getContext(), log, status, data,
		getReport(), getReportLogger(), WeeklyPlanningStorePage.class);
		pageMonthlyPlanningGroupPO = new MonthlyPlanningGroupPage().createPage(getDriver(), getContext(), log, status,
		data, getReport(), getReportLogger(), MonthlyPlanningGroupPage.class);
		TestStatus status = getTestStatus();
		System.out.println("Entering into TestCase");
		Name = pageWeeklyPlanningPO.MonthName(objWeeklyPlanningStoreObjects, extentTest,
		objWeeklyPlanningStoreObjects.FourWeekofMonth);
		YrName = pageWeeklyPlanningPO.MonthName(objWeeklyPlanningStoreObjects, extentTest,
		objWeeklyPlanningStoreObjects.CurrentWeekBGDT);
		WeekName = Name.split(" ");
		YearName = YrName.split(" ");
		Week = WeekName[0];
		Wk = getContext().getStringProperty("SelectFiscalWeek");
		HistWk = Integer.parseInt(Wk) - 1;
		HWk4 = String.valueOf(HistWk);

		Report_AddStep("testcase", " OR VIEW - FIRST HISTORY WEEK VALIDATION", "", "", "");

		String ORHistory = data.getText("ORHistory");
		DBValues = sql.CLRexecuteQuery(getContext(),
		ORHistory.replace("Region", getContext().getStringProperty("Region"))
		.replace("Area", getContext().getStringProperty("Area"))
		.replace("Zone", getContext().getStringProperty("Zone")).replace("FinWk", Week)
		.replace("FinYear", getContext().getStringProperty("Year")).replace("Week", HWk4));
		System.out.println("DB Value: " + DBValues);

		String ORHistoryTotal = data.getText("ORHistoryTotal");
		DBTotal = sql.CLRexecuteQuery(getContext(),
		ORHistoryTotal.replace("Region", getContext().getStringProperty("Region"))
		.replace("Area", getContext().getStringProperty("Area"))
		.replace("Zone", getContext().getStringProperty("Zone")).replace("FinWk", Week)
		.replace("FinYear", getContext().getStringProperty("Year")).replace("Week", HWk4));
		System.out.println("DB Total: " + DBTotal);

		UIValues = pageWeeklyPlanningPO.UIResults(objMonthlyPlanningStoreObjects.MPTable, extentTest,
		objWeeklyPlanningStoreObjects.DepartmentNames, objWeeklyPlanningStoreObjects.FourWeekofMonth);
		pageMonthlyPlanningPO.CompareValueDecimalFields(extentTest, DBValues, UIValues);

		UITotal = pageMonthlyPlanningGroupPO.WPGetTotal(extentTest, objMonthlyPlanningGroupObjects.RowNameTotal,
		objWeeklyPlanningStoreObjects.FourWeekofMonth);
		pageMonthlyPlanningPO.CompareValueDecimalFields(extentTest, DBTotal, UITotal);
		}*/

	////////////////////////// CURRENT MONTH ///////////////////////////////
	@Test(description = "Weekly Planning - WOWGroup, Budget Sales - Current Week", priority = 33)
	public void BudgetSales_CurrentMonth() throws Exception {
		pageWeeklyPlanningPO = new WeeklyPlanningStorePage().createPage(getDriver(), getContext(), log, status, data,
				getReport(), getReportLogger(), WeeklyPlanningStorePage.class);
		pageMonthlyPlanningGroupPO = new WeeklyPlanningStorePage().createPage(getDriver(), getContext(), log, status,
				data, getReport(), getReportLogger(), MonthlyPlanningGroupPage.class);
		TestStatus status = getTestStatus();
		System.out.println("Entering into TestCase");
		pageWeeklyPlanningPO.SelectHistoryDropdownToView(objWeeklyPlanningStoreObjects, extentTest, "Sales");
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

		if (OptionToView.contains("Budget")) {
			String BudgetSales = data.getText("BudgetSales");
			DBValues = sql.CLRexecuteQuery(getContext(),
					BudgetSales.replace("Region", getContext().getStringProperty("Region"))
							.replace("Area", getContext().getStringProperty("Area"))
							.replace("Zone", getContext().getStringProperty("Zone")).replace("FinWeek", Week)
							.replace("FinYear", getContext().getStringProperty("Year")).replace("Division", getContext().getStringProperty("Division")));
			System.out.println("DB Value: " + DBValues);

			String BudgetSalesTotal = data.getText("BudgetSalesTotal");
			DBTotal = sql.CLRexecuteQuery(getContext(),
					BudgetSalesTotal.replace("Region", getContext().getStringProperty("Region"))
							.replace("Area", getContext().getStringProperty("Area"))
							.replace("Zone", getContext().getStringProperty("Zone")).replace("FinWeek", Week)
							.replace("FinYear", getContext().getStringProperty("Year")).replace("Division", getContext().getStringProperty("Division")));
			System.out.println("DB Total: " + DBTotal);

			UIValues = pageWeeklyPlanningPO.UIResults(objMonthlyPlanningStoreObjects.MPTable, extentTest,
					objWeeklyPlanningStoreObjects.DepartmentNames, objWeeklyPlanningStoreObjects.BudgetSales);
			pageMonthlyPlanningPO.CompareValues(extentTest, DBValues, UIValues);

			UITotal = pageMonthlyPlanningGroupPO.WPGetTotal(extentTest, objMonthlyPlanningGroupObjects.RowNameTotal,
					objWeeklyPlanningStoreObjects.BudgetSales);
			pageMonthlyPlanningPO.CompareValues(extentTest, DBTotal, UITotal);
		}
		if (OptionToView.contains("Forecast")) {
			String ForecastSales = data.getText("ForecastSales");
			DBValues = sql.CLRexecuteQuery(getContext(),
					ForecastSales.replace("Region", getContext().getStringProperty("Region"))
							.replace("Area", getContext().getStringProperty("Area"))
							.replace("Zone", getContext().getStringProperty("Zone")).replace("FinWeek", Week)
							.replace("FinYear", getContext().getStringProperty("Year")).replace("Division", getContext().getStringProperty("Division")));
			System.out.println("DB Value: " + DBValues);

			String ForecastSalesTotal = data.getText("ForecastSalesTotal");
			DBTotal = sql.CLRexecuteQuery(getContext(),
					ForecastSalesTotal.replace("Region", getContext().getStringProperty("Region"))
							.replace("Area", getContext().getStringProperty("Area"))
							.replace("Zone", getContext().getStringProperty("Zone")).replace("FinWeek", Week)
							.replace("FinYear", getContext().getStringProperty("Year")).replace("Division", getContext().getStringProperty("Division")));
			System.out.println("DB Total: " + DBTotal);

			UIValues = pageWeeklyPlanningPO.UIResults(objMonthlyPlanningStoreObjects.MPTable, extentTest,
					objWeeklyPlanningStoreObjects.DepartmentNames, objWeeklyPlanningStoreObjects.ForecastSales);
			pageMonthlyPlanningPO.CompareValues(extentTest, DBValues, UIValues);

			UITotal = pageMonthlyPlanningGroupPO.WPGetTotal(extentTest, objMonthlyPlanningGroupObjects.RowNameTotal,
					objWeeklyPlanningStoreObjects.ForecastSales);
			pageMonthlyPlanningPO.CompareValues(extentTest, DBTotal, UITotal);
		}
	}

	@Test(description = "Weekly Planning - WOWGroup, Generated Sales- Current Week", priority = 34)
	public void GeneratedSales_CurrentMonth() throws Exception {
		pageWeeklyPlanningPO = new WeeklyPlanningStorePage().createPage(getDriver(), getContext(), log, status, data,
				getReport(), getReportLogger(), WeeklyPlanningStorePage.class);
		pageMonthlyPlanningGroupPO = new WeeklyPlanningStorePage().createPage(getDriver(), getContext(), log, status,
				data, getReport(), getReportLogger(), MonthlyPlanningGroupPage.class);
		TestStatus status = getTestStatus();
		System.out.println("Entering into TestCase");
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

		String GeneratedSalesTotal = data.getText("GeneratedSalesTotal");
		DBTotal = sql.CLRexecuteQuery(getContext(),
				GeneratedSalesTotal.replace("Region", getContext().getStringProperty("Region"))
						.replace("Area", getContext().getStringProperty("Area"))
						.replace("Zone", getContext().getStringProperty("Zone")).replace("FinWeek", Week)
						.replace("FinYear", getContext().getStringProperty("Year")).replace("Division", getContext().getStringProperty("Division")));
		System.out.println("DB Total: " + DBTotal);

		UITotal = pageMonthlyPlanningGroupPO.WPGetTotal(extentTest, objMonthlyPlanningGroupObjects.RowNameTotal,
				objWeeklyPlanningStoreObjects.GeneratedSales);
		pageMonthlyPlanningPO.CompareValues(extentTest, DBTotal, UITotal);
	}

	@Test(description = "Weekly Planning - WOWGroup, Planned Sales- Current Week", priority = 35)
	public void PlannedSales_CurrentMonth() throws Exception {
		pageWeeklyPlanningPO = new WeeklyPlanningStorePage().createPage(getDriver(), getContext(), log, status, data,
				getReport(), getReportLogger(), WeeklyPlanningStorePage.class);
		pageMonthlyPlanningGroupPO = new WeeklyPlanningStorePage().createPage(getDriver(), getContext(), log, status,
				data, getReport(), getReportLogger(), MonthlyPlanningGroupPage.class);
		TestStatus status = getTestStatus();
		System.out.println("Entering into TestCase");
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

		String PlannedSalesTotal = data.getText("PlannedSalesTotal");
		DBTotal = sql.CLRexecuteQuery(getContext(),
				PlannedSalesTotal.replace("Region", getContext().getStringProperty("Region"))
						.replace("Area", getContext().getStringProperty("Area"))
						.replace("Zone", getContext().getStringProperty("Zone")).replace("FinWeek", Week)
						.replace("FinYear", getContext().getStringProperty("Year")).replace("Division", getContext().getStringProperty("Division")));
		System.out.println("DB Total: " + DBTotal);

		UITotal = pageMonthlyPlanningGroupPO.WPGetTotal(extentTest, objMonthlyPlanningGroupObjects.RowNameTotal,
				objWeeklyPlanningStoreObjects.PlannedSales);
		pageMonthlyPlanningPO.CompareValues(extentTest, DBTotal, UITotal);
	}

	// Var to BUD
	@Test(description = "Weekly Planning - WOWGroup, Var to Bud - Current Week", priority = 36)
	public void VarToBUDSales_CurrentMonth() throws Exception {
		pageWeeklyPlanningPO = new WeeklyPlanningStorePage().createPage(getDriver(), getContext(), log, status, data,
				getReport(), getReportLogger(), WeeklyPlanningStorePage.class);
		pageMonthlyPlanningGroupPO = new WeeklyPlanningStorePage().createPage(getDriver(), getContext(), log, status,
				data, getReport(), getReportLogger(), MonthlyPlanningGroupPage.class);
		TestStatus status = getTestStatus();
		System.out.println("Entering into TestCase");
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

		if (OptionToView.contains("Budget")) {
			
			String VartoBUDTotal = data.getText("VartoBUDTotal");
			DBTotal = sql.CLRexecuteQuery(getContext(),
					VartoBUDTotal.replace("Region", getContext().getStringProperty("Region"))
							.replace("Area", getContext().getStringProperty("Area"))
							.replace("Zone", getContext().getStringProperty("Zone")).replace("FinWeek", Week)
							.replace("FinYear", getContext().getStringProperty("Year")).replace("Division", getContext().getStringProperty("Division")));
			System.out.println("DB Total: " + DBTotal);

			UITotal = pageMonthlyPlanningGroupPO.WPGetTotal(extentTest, objMonthlyPlanningGroupObjects.RowNameTotal,
					objWeeklyPlanningStoreObjects.VarToBudSales);
			pageMonthlyPlanningPO.CompareValues(extentTest, DBTotal, UITotal);
		}
		if (OptionToView.contains("Forecast")) {
			
			String VartoFCSTTotal = data.getText("VartoFCSTTotal");
			DBTotal = sql.CLRexecuteQuery(getContext(),
					VartoFCSTTotal.replace("Region", getContext().getStringProperty("Region"))
							.replace("Area", getContext().getStringProperty("Area"))
							.replace("Zone", getContext().getStringProperty("Zone")).replace("FinWeek", Week)
							.replace("FinYear", getContext().getStringProperty("Year")).replace("Division", getContext().getStringProperty("Division")));
			System.out.println("DB Total: " + DBTotal);

			UITotal = pageMonthlyPlanningGroupPO.WPGetTotal(extentTest, objMonthlyPlanningGroupObjects.RowNameTotal,
					objWeeklyPlanningStoreObjects.VarToFcstSales);
			pageMonthlyPlanningPO.CompareValues(extentTest, DBTotal, UITotal);
		}
	}

	// +/- LY%
	//Jenkins Run
@Test(description = "Weekly Planning - WOWGroup, +/- LY% - Current Week", priority = 37)
	public void PercentageLYsales_CurrentMonth() throws Exception {
		pageWeeklyPlanningPO = new WeeklyPlanningStorePage().createPage(getDriver(), getContext(), log, status, data,
				getReport(), getReportLogger(), WeeklyPlanningStorePage.class);
		pageMonthlyPlanningGroupPO = new WeeklyPlanningStorePage().createPage(getDriver(), getContext(), log, status,
				data, getReport(), getReportLogger(), MonthlyPlanningGroupPage.class);
		TestStatus status = getTestStatus();
		System.out.println("Entering into TestCase");
		OptionToView = getContext().getStringProperty("OptionToView");
		if (OptionToView.contains("Budget"))
			Name = pageWeeklyPlanningPO.MonthName(objWeeklyPlanningStoreObjects, extentTest,
					objWeeklyPlanningStoreObjects.CurrentWeekBGDT);
		if (OptionToView.contains("Forecast"))
			Name = pageWeeklyPlanningPO.MonthName(objWeeklyPlanningStoreObjects, extentTest,
					objWeeklyPlanningStoreObjects.CurrentWeekFCST);
		WeekName = Name.split(" ");
		Week = WeekName[0];
		Year = getContext().getStringProperty("Year");
		LYYear = Integer.parseInt(Year) - 1;
		LYYr = String.valueOf(LYYear);

		String PercentLYSalesTotal = data.getText("PercentLYSalesTotal");
		DBTotal = sql.CLRexecuteQuery(getContext(),
				PercentLYSalesTotal.replace("Region", getContext().getStringProperty("Region"))
						.replace("Area", getContext().getStringProperty("Area"))
						.replace("Zone", getContext().getStringProperty("Zone")).replace("FinWeek", Week)
						.replace("FinYear", getContext().getStringProperty("Year")).replace("LYFinYr", LYYr).replace("Division", getContext().getStringProperty("Division")));
		System.out.println("DB Total: " + DBTotal);

		UITotal = pageMonthlyPlanningGroupPO.WPGetTotal(extentTest, objMonthlyPlanningGroupObjects.RowNameTotal,
				objWeeklyPlanningStoreObjects.PercentageLYSales);
		pageMonthlyPlanningPO.CompareValueDecimalFields(extentTest, DBTotal, UITotal);
	}

	// +/- Bud%
	@Test(description = "Weekly Planning - WOWGroup, +/- Bud% - Current Week", priority = 38)
	public void BudgetPercentageSales_CurrentMonth() throws Exception {
		pageWeeklyPlanningPO = new WeeklyPlanningStorePage().createPage(getDriver(), getContext(), log, status, data,
				getReport(), getReportLogger(), WeeklyPlanningStorePage.class);
		pageMonthlyPlanningGroupPO = new WeeklyPlanningStorePage().createPage(getDriver(), getContext(), log, status,
				data, getReport(), getReportLogger(), MonthlyPlanningGroupPage.class);
		TestStatus status = getTestStatus();
		System.out.println("Entering into TestCase");
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

		if (OptionToView.contains("Budget")) {
			
			String BudgetPercentageTotal = data.getText("BudgetPercentageTotal");
			DBTotal = sql.CLRexecuteQuery(getContext(),
					BudgetPercentageTotal.replace("Region", getContext().getStringProperty("Region"))
							.replace("Area", getContext().getStringProperty("Area"))
							.replace("Zone", getContext().getStringProperty("Zone")).replace("FinWeek", Week)
							.replace("FinYear", getContext().getStringProperty("Year")).replace("Division", getContext().getStringProperty("Division")));
			System.out.println("DB Total: " + DBTotal);

			UITotal = pageMonthlyPlanningGroupPO.WPGetTotal(extentTest, objMonthlyPlanningGroupObjects.RowNameTotal,
					objWeeklyPlanningStoreObjects.PercentageBudgetSales);
			pageMonthlyPlanningPO.CompareValueDecimalFields(extentTest, DBTotal, UITotal);
		}
		if (OptionToView.contains("Forecast")) {
			
			String ForecastPercentageTotal = data.getText("ForecastPercentageTotal");
			DBTotal = sql.CLRexecuteQuery(getContext(),
					ForecastPercentageTotal.replace("Region", getContext().getStringProperty("Region"))
							.replace("Area", getContext().getStringProperty("Area"))
							.replace("Zone", getContext().getStringProperty("Zone")).replace("FinWeek", Week)
							.replace("FinYear", getContext().getStringProperty("Year")).replace("Division", getContext().getStringProperty("Division")));
			System.out.println("DB Total: " + DBTotal);

			UITotal = pageMonthlyPlanningGroupPO.WPGetTotal(extentTest, objMonthlyPlanningGroupObjects.RowNameTotal,
					objWeeklyPlanningStoreObjects.PercentageForecastSales);
			pageMonthlyPlanningPO.CompareValues(extentTest, DBTotal, UITotal);
		}
	}

	// Planned Item Price
	@Test(description = "Weekly Planning - WOWGroup, Planned Item Price - Current Week", priority = 39)
	public void PlannedItemPrice_CurrentMonth() throws Exception {
		pageWeeklyPlanningPO = new WeeklyPlanningStorePage().createPage(getDriver(), getContext(), log, status, data,
				getReport(), getReportLogger(), WeeklyPlanningStorePage.class);
		pageMonthlyPlanningGroupPO = new WeeklyPlanningStorePage().createPage(getDriver(), getContext(), log, status,
				data, getReport(), getReportLogger(), MonthlyPlanningGroupPage.class);
		TestStatus status = getTestStatus();
		System.out.println("Entering into TestCase");
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

		String PlannedItemPriceTotal = data.getText("PlannedItemPriceTotal");
		DBTotal = sql.CLRexecuteQuery(getContext(),
				PlannedItemPriceTotal.replace("Region", getContext().getStringProperty("Region"))
						.replace("Area", getContext().getStringProperty("Area"))
						.replace("Zone", getContext().getStringProperty("Zone")).replace("FinWeek", Week)
						.replace("FinYear", getContext().getStringProperty("Year")).replace("Division", getContext().getStringProperty("Division")));
		System.out.println("DB Total: " + DBTotal);

		UITotal = pageMonthlyPlanningGroupPO.WPGetTotal(extentTest, objMonthlyPlanningGroupObjects.RowNameTotal,
				objWeeklyPlanningStoreObjects.PlannedItemPrice);
		pageMonthlyPlanningPO.CompareValueDecimalFields(extentTest, DBTotal, UITotal);
	}

	// Planned percent on LY
	@Test(description = "Weekly Planning - WOWGroup, Planned percent on LY - Current Week", priority = 40)
	public void PlannedPercentOnLY_CurrentMonth() throws Exception {
		pageWeeklyPlanningPO = new WeeklyPlanningStorePage().createPage(getDriver(), getContext(), log, status, data,
				getReport(), getReportLogger(), WeeklyPlanningStorePage.class);
		pageMonthlyPlanningGroupPO = new WeeklyPlanningStorePage().createPage(getDriver(), getContext(), log, status,
				data, getReport(), getReportLogger(), MonthlyPlanningGroupPage.class);
		TestStatus status = getTestStatus();
		System.out.println("Entering into TestCase");
		OptionToView = getContext().getStringProperty("OptionToView");
		if (OptionToView.contains("Budget"))
			Name = pageWeeklyPlanningPO.MonthName(objWeeklyPlanningStoreObjects, extentTest,
					objWeeklyPlanningStoreObjects.CurrentWeekBGDT);
		if (OptionToView.contains("Forecast"))
			Name = pageWeeklyPlanningPO.MonthName(objWeeklyPlanningStoreObjects, extentTest,
					objWeeklyPlanningStoreObjects.CurrentWeekFCST);
		WeekName = Name.split(" ");
		Week = WeekName[0];
		Year = getContext().getStringProperty("Year");
		LYYear = Integer.parseInt(Year) - 1;
		LYYr = String.valueOf(LYYear);

		String PlannedpercentonLYTotal = data.getText("PlannedpercentonLYTotal");
		DBTotal = sql.CLRexecuteQuery(getContext(),
				PlannedpercentonLYTotal.replace("Region", getContext().getStringProperty("Region"))
						.replace("Area", getContext().getStringProperty("Area"))
						.replace("Zone", getContext().getStringProperty("Zone")).replace("FinWeek", Week)
						.replace("FinYear", getContext().getStringProperty("Year")).replace("LYFinYr", LYYr).replace("Division", getContext().getStringProperty("Division")));
		System.out.println("DB Total: " + DBTotal);

		UITotal = pageMonthlyPlanningGroupPO.WPGetTotal(extentTest, objMonthlyPlanningGroupObjects.RowNameTotal,
				objWeeklyPlanningStoreObjects.PlannedPercentageonLY);
		pageMonthlyPlanningPO.CompareValueDecimalFields(extentTest, DBTotal, UITotal);
	}

	// Planned Items
	@Test(description = "Weekly Planning - WOWGroup, Planned Items - Current Week", priority = 41)
	public void PlannedItems_CurrentMonth() throws Exception {
		pageWeeklyPlanningPO = new WeeklyPlanningStorePage().createPage(getDriver(), getContext(), log, status, data,
				getReport(), getReportLogger(), WeeklyPlanningStorePage.class);
		pageMonthlyPlanningGroupPO = new WeeklyPlanningStorePage().createPage(getDriver(), getContext(), log, status,
				data, getReport(), getReportLogger(), MonthlyPlanningGroupPage.class);
		TestStatus status = getTestStatus();
		System.out.println("Entering into TestCase");
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

		String PlannedItemsTotal = data.getText("PlannedItemsTotal");
		DBTotal = sql.CLRexecuteQuery(getContext(),
				PlannedItemsTotal.replace("Region", getContext().getStringProperty("Region"))
						.replace("Area", getContext().getStringProperty("Area"))
						.replace("Zone", getContext().getStringProperty("Zone")).replace("FinWeek", Week)
						.replace("FinYear", getContext().getStringProperty("Year")).replace("Division", getContext().getStringProperty("Division")));
		System.out.println("DB Total: " + DBTotal);

		UITotal = pageMonthlyPlanningGroupPO.WPGetTotal(extentTest, objMonthlyPlanningGroupObjects.RowNameTotal,
				objWeeklyPlanningStoreObjects.PlannedItems);
		pageMonthlyPlanningPO.CompareValues(extentTest, DBTotal, UITotal);
	}

	// Planned IPLH
	@Test(description = "Weekly Planning - WOWGroup, Planned IPLH Items - Current Week", priority = 42)
	public void PlannedIPLHitems_CurrentMonth() throws Exception {
		pageWeeklyPlanningPO = new WeeklyPlanningStorePage().createPage(getDriver(), getContext(), log, status, data,
				getReport(), getReportLogger(), WeeklyPlanningStorePage.class);
		pageMonthlyPlanningGroupPO = new WeeklyPlanningStorePage().createPage(getDriver(), getContext(), log, status,
				data, getReport(), getReportLogger(), MonthlyPlanningGroupPage.class);
		TestStatus status = getTestStatus();
		System.out.println("Entering into TestCase");
		OptionToView = getContext().getStringProperty("OptionToView");
		if (OptionToView.contains("Budget"))
			Name = pageWeeklyPlanningPO.MonthName(objWeeklyPlanningStoreObjects, extentTest,
					objWeeklyPlanningStoreObjects.CurrentWeekBGDT);
		if (OptionToView.contains("Forecast"))
			Name = pageWeeklyPlanningPO.MonthName(objWeeklyPlanningStoreObjects, extentTest,
					objWeeklyPlanningStoreObjects.CurrentWeekFCST);
		WeekName = Name.split(" ");
		Week = WeekName[0];
		Year = getContext().getStringProperty("Year");
		LYYear = Integer.parseInt(Year) - 1;
		LYYr = String.valueOf(LYYear);

		String PlannedIPLHitemsTotal = data.getText("PlannedIPLHitemsTotal");
		DBTotal = sql.CLRexecuteQuery(getContext(),
				PlannedIPLHitemsTotal.replace("Region", getContext().getStringProperty("Region"))
						.replace("Area", getContext().getStringProperty("Area"))
						.replace("Zone", getContext().getStringProperty("Zone")).replace("FinWeek", Week)
						.replace("FinYear", getContext().getStringProperty("Year")).replace("LYFinYr", LYYr).replace("Division", getContext().getStringProperty("Division")));
		System.out.println("DB Total: " + DBTotal);

		UITotal = pageMonthlyPlanningGroupPO.WPGetTotal(extentTest, objMonthlyPlanningGroupObjects.RowNameTotal,
				objWeeklyPlanningStoreObjects.PlannedIPLHItems);
		pageMonthlyPlanningPO.CompareValues(extentTest, DBTotal, UITotal);
	}

	// Budget Wages
	@Test(description = "Weekly Planning - WOWGroup, Budget Wages - Current Week", priority = 43)
	public void BudgetWages_CurrentMonth() throws Exception {
		pageWeeklyPlanningPO = new WeeklyPlanningStorePage().createPage(getDriver(), getContext(), log, status, data,
				getReport(), getReportLogger(), WeeklyPlanningStorePage.class);
		pageMonthlyPlanningGroupPO = new WeeklyPlanningStorePage().createPage(getDriver(), getContext(), log, status,
				data, getReport(), getReportLogger(), MonthlyPlanningGroupPage.class);
		TestStatus status = getTestStatus();
		System.out.println("Entering into TestCase");
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

		if (OptionToView.contains("Budget")) {
			
			String BudgetWagesTotal = data.getText("BudgetWagesTotal");
			DBTotal = sql.CLRexecuteQuery(getContext(),
					BudgetWagesTotal.replace("Region", getContext().getStringProperty("Region"))
							.replace("Area", getContext().getStringProperty("Area"))
							.replace("Zone", getContext().getStringProperty("Zone")).replace("FinWeek", Week)
							.replace("FinYear", getContext().getStringProperty("Year")).replace("Division", getContext().getStringProperty("Division")));
			System.out.println("DB Total: " + DBTotal);

			UITotal = pageMonthlyPlanningGroupPO.WPGetTotal(extentTest, objMonthlyPlanningGroupObjects.RowNameTotal,
					objWeeklyPlanningStoreObjects.BudgetWages);
			pageMonthlyPlanningPO.CompareValues(extentTest, DBTotal, UITotal);
		}
		if (OptionToView.contains("Forecast")) {
		
			String ForecastWagesTotal = data.getText("ForecastWagesTotal");
			DBTotal = sql.CLRexecuteQuery(getContext(),
					ForecastWagesTotal.replace("Region", getContext().getStringProperty("Region"))
							.replace("Area", getContext().getStringProperty("Area"))
							.replace("Zone", getContext().getStringProperty("Zone")).replace("FinWeek", Week)
							.replace("FinYear", getContext().getStringProperty("Year")).replace("Division", getContext().getStringProperty("Division")));
			System.out.println("DB Total: " + DBTotal);

			UITotal = pageMonthlyPlanningGroupPO.WPGetTotal(extentTest, objMonthlyPlanningGroupObjects.RowNameTotal,
					objWeeklyPlanningStoreObjects.ForecastWages);
			pageMonthlyPlanningPO.CompareValues(extentTest, DBTotal, UITotal);
		}
	}

	// Planned Wages
	@Test(description = "Weekly Planning - WOWGroup, Planned Wages - Current Week", priority = 44)
	public void PlannedWages_CurrentMonth() throws Exception {
		pageWeeklyPlanningPO = new WeeklyPlanningStorePage().createPage(getDriver(), getContext(), log, status, data,
				getReport(), getReportLogger(), WeeklyPlanningStorePage.class);
		pageMonthlyPlanningGroupPO = new WeeklyPlanningStorePage().createPage(getDriver(), getContext(), log, status,
				data, getReport(), getReportLogger(), MonthlyPlanningGroupPage.class);
		TestStatus status = getTestStatus();
		System.out.println("Entering into TestCase");
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

		String PlannedWagesTotal = data.getText("PlannedWagesTotal");
		DBTotal = sql.CLRexecuteQuery(getContext(),
				PlannedWagesTotal.replace("Region", getContext().getStringProperty("Region"))
						.replace("Area", getContext().getStringProperty("Area"))
						.replace("Zone", getContext().getStringProperty("Zone")).replace("FinWeek", Week)
						.replace("FinYear", getContext().getStringProperty("Year")).replace("Division", getContext().getStringProperty("Division")));
		System.out.println("DB Total: " + DBTotal);

		UITotal = pageMonthlyPlanningGroupPO.WPGetTotal(extentTest, objMonthlyPlanningGroupObjects.RowNameTotal,
				objWeeklyPlanningStoreObjects.PlannedWages);
		pageMonthlyPlanningPO.CompareValues(extentTest, DBTotal, UITotal);
	}

	// Planned % on LY
	@Test(description = "Weekly Planning - WOWGroup, Planned % on LY Wages - Current Week", priority = 45)
	public void PlannedLYWages_CurrentMonth() throws Exception {
		pageWeeklyPlanningPO = new WeeklyPlanningStorePage().createPage(getDriver(), getContext(), log, status, data,
				getReport(), getReportLogger(), WeeklyPlanningStorePage.class);
		pageMonthlyPlanningGroupPO = new WeeklyPlanningStorePage().createPage(getDriver(), getContext(), log, status,
				data, getReport(), getReportLogger(), MonthlyPlanningGroupPage.class);
		TestStatus status = getTestStatus();
		System.out.println("Entering into TestCase");
		OptionToView = getContext().getStringProperty("OptionToView");
		if (OptionToView.contains("Budget"))
			Name = pageWeeklyPlanningPO.MonthName(objWeeklyPlanningStoreObjects, extentTest,
					objWeeklyPlanningStoreObjects.CurrentWeekBGDT);
		if (OptionToView.contains("Forecast"))
			Name = pageWeeklyPlanningPO.MonthName(objWeeklyPlanningStoreObjects, extentTest,
					objWeeklyPlanningStoreObjects.CurrentWeekFCST);
		WeekName = Name.split(" ");
		Week = WeekName[0];
		Year = getContext().getStringProperty("Year");
		LYYear = Integer.parseInt(Year) - 1;
		LYYr = String.valueOf(LYYear);

		String PlannedLYWagesTotal = data.getText("PlannedLYWagesTotal");
		DBTotal = sql.CLRexecuteQuery(getContext(),
				PlannedLYWagesTotal.replace("Region", getContext().getStringProperty("Region"))
						.replace("Area", getContext().getStringProperty("Area"))
						.replace("Zone", getContext().getStringProperty("Zone")).replace("FinWeek", Week)
						.replace("FinYear", getContext().getStringProperty("Year")).replace("LYFinYr", LYYr).replace("Division", getContext().getStringProperty("Division")));
		System.out.println("DB Total: " + DBTotal);

		UITotal = pageMonthlyPlanningGroupPO.WPGetTotal(extentTest, objMonthlyPlanningGroupObjects.RowNameTotal,
				objWeeklyPlanningStoreObjects.PercentLYWages);
		pageMonthlyPlanningPO.CompareValueDecimalFields(extentTest, DBTotal, UITotal);
	}

	// Budget Wage%
	@Test(description = "Weekly Planning - WOWGroup, Budget WagesPercentage - Current Week", priority = 46)
	public void BudgetWagesPercentage_CurrentMonth() throws Exception {
		pageWeeklyPlanningPO = new WeeklyPlanningStorePage().createPage(getDriver(), getContext(), log, status, data,
				getReport(), getReportLogger(), WeeklyPlanningStorePage.class);
		pageMonthlyPlanningGroupPO = new WeeklyPlanningStorePage().createPage(getDriver(), getContext(), log, status,
				data, getReport(), getReportLogger(), MonthlyPlanningGroupPage.class);
		TestStatus status = getTestStatus();
		System.out.println("Entering into TestCase");
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

		if (OptionToView.contains("Budget")) {
			
			String BudgetWagesPercentageTotal = data.getText("BudgetWagesPercentageTotal");
			DBTotal = sql.CLRexecuteQuery(getContext(),
					BudgetWagesPercentageTotal.replace("Region", getContext().getStringProperty("Region"))
							.replace("Area", getContext().getStringProperty("Area"))
							.replace("Zone", getContext().getStringProperty("Zone")).replace("FinWeek", Week)
							.replace("FinYear", getContext().getStringProperty("Year")).replace("Division", getContext().getStringProperty("Division")));
			System.out.println("DB Total: " + DBTotal);

			UITotal = pageMonthlyPlanningGroupPO.WPGetTotal(extentTest, objMonthlyPlanningGroupObjects.RowNameTotal,
					objWeeklyPlanningStoreObjects.BudgetWagesPercent);
			pageMonthlyPlanningPO.CompareValueDecimalFields(extentTest, DBTotal, UITotal);
		}
		if (OptionToView.contains("Forecast")) {
			
			String ForecastWagesPercentageTotal = data.getText("ForecastWagesPercentageTotal");
			DBTotal = sql.CLRexecuteQuery(getContext(),
					ForecastWagesPercentageTotal.replace("Region", getContext().getStringProperty("Region"))
							.replace("Area", getContext().getStringProperty("Area"))
							.replace("Zone", getContext().getStringProperty("Zone")).replace("FinWeek", Week)
							.replace("FinYear", getContext().getStringProperty("Year")).replace("Division", getContext().getStringProperty("Division")));
			System.out.println("DB Total: " + DBTotal);

			UITotal = pageMonthlyPlanningGroupPO.WPGetTotal(extentTest, objMonthlyPlanningGroupObjects.RowNameTotal,
					objWeeklyPlanningStoreObjects.ForecastWagesPercent);
			pageMonthlyPlanningPO.CompareValueDecimalFields(extentTest, DBTotal, UITotal);
		}
	}

	// Planned Wage%
	@Test(description = "Weekly Planning - WOWGroup, Planned WagesPercentage - Current Week", priority = 47)
	public void PlannedWagesPercentage_CurrentMonth() throws Exception {
		pageWeeklyPlanningPO = new WeeklyPlanningStorePage().createPage(getDriver(), getContext(), log, status, data,
				getReport(), getReportLogger(), WeeklyPlanningStorePage.class);
		pageMonthlyPlanningGroupPO = new WeeklyPlanningStorePage().createPage(getDriver(), getContext(), log, status,
				data, getReport(), getReportLogger(), MonthlyPlanningGroupPage.class);
		TestStatus status = getTestStatus();
		System.out.println("Entering into TestCase");
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

		String PlannedWagesPercentageTotal = data.getText("PlannedWagesPercentageTotal");
		DBTotal = sql.CLRexecuteQuery(getContext(),
				PlannedWagesPercentageTotal.replace("Region", getContext().getStringProperty("Region"))
						.replace("Area", getContext().getStringProperty("Area"))
						.replace("Zone", getContext().getStringProperty("Zone")).replace("FinWeek", Week)
						.replace("FinYear", getContext().getStringProperty("Year")).replace("Division", getContext().getStringProperty("Division")));
		System.out.println("DB Total: " + DBTotal);

		UITotal = pageMonthlyPlanningGroupPO.WPGetTotal(extentTest, objMonthlyPlanningGroupObjects.RowNameTotal,
				objWeeklyPlanningStoreObjects.PlannedWagesPercent);
		pageMonthlyPlanningPO.CompareValueDecimalFields(extentTest, DBTotal, UITotal);
	}

	// LY Wage%
	@Test(description = "Weekly Planning - WOWGroup, PlannedLY WagesPercentage - Current Week", priority = 48)
	public void LYWagesPercentage_CurrentMonth() throws Exception {
		pageWeeklyPlanningPO = new WeeklyPlanningStorePage().createPage(getDriver(), getContext(), log, status, data,
				getReport(), getReportLogger(), WeeklyPlanningStorePage.class);
		pageMonthlyPlanningGroupPO = new WeeklyPlanningStorePage().createPage(getDriver(), getContext(), log, status,
				data, getReport(), getReportLogger(), MonthlyPlanningGroupPage.class);
		TestStatus status = getTestStatus();
		System.out.println("Entering into TestCase");
		OptionToView = getContext().getStringProperty("OptionToView");
		if (OptionToView.contains("Budget"))
			Name = pageWeeklyPlanningPO.MonthName(objWeeklyPlanningStoreObjects, extentTest,
					objWeeklyPlanningStoreObjects.CurrentWeekBGDT);
		if (OptionToView.contains("Forecast"))
			Name = pageWeeklyPlanningPO.MonthName(objWeeklyPlanningStoreObjects, extentTest,
					objWeeklyPlanningStoreObjects.CurrentWeekFCST);
		WeekName = Name.split(" ");
		Week = WeekName[0];
		Year = getContext().getStringProperty("Year");
		LYYear = Integer.parseInt(Year) - 1;
		LYYr = String.valueOf(LYYear);

		String LYWagesPercentageTotal = data.getText("LYWagesPercentageTotal");
		DBTotal = sql.CLRexecuteQuery(getContext(),
				LYWagesPercentageTotal.replace("Region", getContext().getStringProperty("Region"))
						.replace("Area", getContext().getStringProperty("Area"))
						.replace("Zone", getContext().getStringProperty("Zone")).replace("FinWeek", Week)
						.replace("FinYear", getContext().getStringProperty("Year")).replace("LYFinYr", LYYr).replace("Division", getContext().getStringProperty("Division")));
		System.out.println("DB Total: " + DBTotal);

		UITotal = pageMonthlyPlanningGroupPO.WPGetTotal(extentTest, objMonthlyPlanningGroupObjects.RowNameTotal,
				objWeeklyPlanningStoreObjects.LYWagePercentage);
		pageMonthlyPlanningPO.CompareValueDecimalFields(extentTest, DBTotal, UITotal);
	}

	// SMS Hours
	@Test(description = "Weekly Planning - WOWGroup, SMS Hours - Current Week", priority = 49)
	public void SMSHours_CurrentMonth() throws Exception {
		pageWeeklyPlanningPO = new WeeklyPlanningStorePage().createPage(getDriver(), getContext(), log, status, data,
				getReport(), getReportLogger(), WeeklyPlanningStorePage.class);
		pageMonthlyPlanningGroupPO = new WeeklyPlanningStorePage().createPage(getDriver(), getContext(), log, status,
				data, getReport(), getReportLogger(), MonthlyPlanningGroupPage.class);
		TestStatus status = getTestStatus();
		System.out.println("Entering into TestCase");
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

		String SMSHoursTotal = data.getText("SMSHoursTotal");
		DBTotal = sql.CLRexecuteQuery(getContext(),
				SMSHoursTotal.replace("Region", getContext().getStringProperty("Region"))
						.replace("Area", getContext().getStringProperty("Area"))
						.replace("Zone", getContext().getStringProperty("Zone")).replace("FinWeek", Week)
						.replace("FinYear", getContext().getStringProperty("Year")).replace("Division", getContext().getStringProperty("Division")));
		System.out.println("DB Total: " + DBTotal);

		UITotal = pageMonthlyPlanningGroupPO.WPGetTotal(extentTest, objMonthlyPlanningGroupObjects.RowNameTotal,
				objWeeklyPlanningStoreObjects.SMSHours);
		pageMonthlyPlanningPO.CompareValueDecimalFields(extentTest, DBTotal, UITotal);
	}

	// Planned Hours
	@Test(description = "Weekly Planning - WOWGroup, PlannedHours - Current Week", priority = 50)
	public void PlannedHours_CurrentMonth() throws Exception {
		pageWeeklyPlanningPO = new WeeklyPlanningStorePage().createPage(getDriver(), getContext(), log, status, data,
				getReport(), getReportLogger(), WeeklyPlanningStorePage.class);
		pageMonthlyPlanningGroupPO = new WeeklyPlanningStorePage().createPage(getDriver(), getContext(), log, status,
				data, getReport(), getReportLogger(), MonthlyPlanningGroupPage.class);
		TestStatus status = getTestStatus();
		System.out.println("Entering into TestCase");
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

		String PlannedHoursTotal = data.getText("PlannedHoursTotal");
		DBTotal = sql.CLRexecuteQuery(getContext(),
				PlannedHoursTotal.replace("Region", getContext().getStringProperty("Region"))
						.replace("Area", getContext().getStringProperty("Area"))
						.replace("Zone", getContext().getStringProperty("Zone")).replace("FinWeek", Week)
						.replace("FinYear", getContext().getStringProperty("Year")).replace("Division", getContext().getStringProperty("Division")));
		System.out.println("DB Total: " + DBTotal);

		UITotal = pageMonthlyPlanningGroupPO.WPGetTotal(extentTest, objMonthlyPlanningGroupObjects.RowNameTotal,
				objWeeklyPlanningStoreObjects.PlannedHours);
		pageMonthlyPlanningPO.CompareValues(extentTest, DBTotal, UITotal);
	}

	// Hours LY
	@Test(description = "Weekly Planning - WOWGroup, HoursLY - Current Week", priority = 51)
	public void HoursLY_CurrentMonth() throws Exception {
		pageWeeklyPlanningPO = new WeeklyPlanningStorePage().createPage(getDriver(), getContext(), log, status, data,
				getReport(), getReportLogger(), WeeklyPlanningStorePage.class);
		pageMonthlyPlanningGroupPO = new WeeklyPlanningStorePage().createPage(getDriver(), getContext(), log, status,
				data, getReport(), getReportLogger(), MonthlyPlanningGroupPage.class);
		TestStatus status = getTestStatus();
		System.out.println("Entering into TestCase");
		OptionToView = getContext().getStringProperty("OptionToView");
		if (OptionToView.contains("Budget"))
			Name = pageWeeklyPlanningPO.MonthName(objWeeklyPlanningStoreObjects, extentTest,
					objWeeklyPlanningStoreObjects.CurrentWeekBGDT);
		if (OptionToView.contains("Forecast"))
			Name = pageWeeklyPlanningPO.MonthName(objWeeklyPlanningStoreObjects, extentTest,
					objWeeklyPlanningStoreObjects.CurrentWeekFCST);
		WeekName = Name.split(" ");
		WeekName = Name.split(" ");
		Week = WeekName[0];
		Year = getContext().getStringProperty("Year");
		LYYear = Integer.parseInt(Year) - 1;
		LYYr = String.valueOf(LYYear);

		String HoursLYTotal = data.getText("HoursLYTotal");
		DBTotal = sql.CLRexecuteQuery(getContext(),
				HoursLYTotal.replace("Region", getContext().getStringProperty("Region"))
						.replace("Area", getContext().getStringProperty("Area"))
						.replace("Zone", getContext().getStringProperty("Zone")).replace("FinWeek", Week)
						.replace("FinYear", getContext().getStringProperty("Year")).replace("LYFinYr", LYYr).replace("Division", getContext().getStringProperty("Division")));
		System.out.println("DB Total: " + DBTotal);

		UITotal = pageMonthlyPlanningGroupPO.WPGetTotal(extentTest, objMonthlyPlanningGroupObjects.RowNameTotal,
				objWeeklyPlanningStoreObjects.LYHours);
		pageMonthlyPlanningPO.CompareValuesForLYHours(extentTest, DBTotal, UITotal);
	}

	// Planned OR
	@Test(description = "Weekly Planning - WOWGroup, Planned OR - Current Week", priority = 52)
	public void PlannedOR_CurrentMonth() throws Exception {
		pageWeeklyPlanningPO = new WeeklyPlanningStorePage().createPage(getDriver(), getContext(), log, status, data,
				getReport(), getReportLogger(), WeeklyPlanningStorePage.class);
		pageMonthlyPlanningGroupPO = new WeeklyPlanningStorePage().createPage(getDriver(), getContext(), log, status,
				data, getReport(), getReportLogger(), MonthlyPlanningGroupPage.class);
		TestStatus status = getTestStatus();
		System.out.println("Entering into TestCase");
		OptionToView = getContext().getStringProperty("OptionToView");
		if (OptionToView.contains("Budget"))
			Name = pageWeeklyPlanningPO.MonthName(objWeeklyPlanningStoreObjects, extentTest,
					objWeeklyPlanningStoreObjects.CurrentWeekBGDT);
		if (OptionToView.contains("Forecast"))
			Name = pageWeeklyPlanningPO.MonthName(objWeeklyPlanningStoreObjects, extentTest,
					objWeeklyPlanningStoreObjects.CurrentWeekFCST);
		WeekName = Name.split(" ");
		Week = WeekName[0];
		Year = getContext().getStringProperty("Year");
		LYYear = Integer.parseInt(Year) - 1;
		LYYr = String.valueOf(LYYear);

		String plannedORTotal = data.getText("plannedORTotal");
		DBTotal = sql.CLRexecuteQuery(getContext(),
				plannedORTotal.replace("Region", getContext().getStringProperty("Region"))
						.replace("Area", getContext().getStringProperty("Area"))
						.replace("Zone", getContext().getStringProperty("Zone")).replace("FinWeek", Week)
						.replace("FinYear", getContext().getStringProperty("Year")).replace("LYFinYr", LYYr).replace("Division", getContext().getStringProperty("Division")));
		System.out.println("DB Total: " + DBTotal);

		UITotal = pageMonthlyPlanningGroupPO.WPGetTotal(extentTest, objMonthlyPlanningGroupObjects.RowNameTotal,
				objWeeklyPlanningStoreObjects.PlannedOR);
		pageMonthlyPlanningPO.CompareValueDecimalFields(extentTest, DBTotal, UITotal);
	}

	// Budget/Target/Forecast - OR
	@Test(description = "Weekly Planning - BU, Budget_Forecast_Target OR - Current Week", priority = 53)
	public void BGDT_FCST_OR_CurrentMonth() throws Exception {
		pageWeeklyPlanningPO = new WeeklyPlanningStorePage().createPage(getDriver(), getContext(), log, status, data,
				getReport(), getReportLogger(), WeeklyPlanningStorePage.class);
		pageMonthlyPlanningGroupPO = new WeeklyPlanningStorePage().createPage(getDriver(), getContext(), log, status,
				data, getReport(), getReportLogger(), MonthlyPlanningGroupPage.class);
		TestStatus status = getTestStatus();
		System.out.println("Entering into TestCase");
		OptionToView = getContext().getStringProperty("OptionToView");
		if (OptionToView.contains("Budget"))
			Name = pageWeeklyPlanningPO.MonthName(objWeeklyPlanningStoreObjects, extentTest,
					objWeeklyPlanningStoreObjects.CurrentWeekBGDT);
		if (OptionToView.contains("Forecast"))
			Name = pageWeeklyPlanningPO.MonthName(objWeeklyPlanningStoreObjects, extentTest,
					objWeeklyPlanningStoreObjects.CurrentWeekFCST);
		WeekName = Name.split(" ");
		Week = WeekName[0];
		Year = getContext().getStringProperty("Year");
		LYYear = Integer.parseInt(Year) - 1;
		LYYr = String.valueOf(LYYear);

		if (OptionToView.contains("Budget")) {
			Report_AddStep("testcase", " OR COLUMN - BUDGET OR VALIDATION", "", "", "");

			String BudgetORTotal = data.getText("BudgetORTotal");
			DBTotal = sql.CLRexecuteQuery(getContext(),
					BudgetORTotal.replace("Region", getContext().getStringProperty("Region"))
							.replace("Area", getContext().getStringProperty("Area"))
							.replace("Zone", getContext().getStringProperty("Zone")).replace("FinWeek", Week)
							.replace("FinYear", getContext().getStringProperty("Year")).replace("LYFinYr", LYYr).replace("Division", getContext().getStringProperty("Division")));
			System.out.println("DB Total: " + DBTotal);

			UITotal = pageMonthlyPlanningGroupPO.WPGetTotal(extentTest, objMonthlyPlanningGroupObjects.RowNameTotal,
					objWeeklyPlanningStoreObjects.BdgtORTtl);
			pageMonthlyPlanningPO.CompareValueDecimalFields(extentTest, DBTotal, UITotal);
		}
		if (OptionToView.contains("Forecast")) {
			Report_AddStep("testcase", " OR COLUMN - FORECAST OR VALIDATION", "", "", "");

			String ForecastORTotal = data.getText("ForecastORTotal");
			DBTotal = sql.CLRexecuteQuery(getContext(),
					ForecastORTotal.replace("Region", getContext().getStringProperty("Region"))
							.replace("Area", getContext().getStringProperty("Area"))
							.replace("Zone", getContext().getStringProperty("Zone")).replace("FinWeek", Week)
							.replace("FinYear", getContext().getStringProperty("Year")).replace("LYFinYr", LYYr).replace("Division", getContext().getStringProperty("Division")));
			System.out.println("DB Total: " + DBTotal);

			UITotal = pageMonthlyPlanningGroupPO.WPGetTotal(extentTest, objMonthlyPlanningGroupObjects.RowNameTotal,
					objWeeklyPlanningStoreObjects.FcstORTtl);
			pageMonthlyPlanningPO.CompareValueDecimalFields(extentTest, DBTotal, UITotal);
		}
	}

	// Planned CPH
	@Test(description = "Weekly Planning - WOWGroup, Planned CPH - Current Week", priority = 54)
	public void PlannedCPH_CurrentMonth() throws Exception {
		pageWeeklyPlanningPO = new WeeklyPlanningStorePage().createPage(getDriver(), getContext(), log, status, data,
				getReport(), getReportLogger(), WeeklyPlanningStorePage.class);
		pageMonthlyPlanningGroupPO = new WeeklyPlanningStorePage().createPage(getDriver(), getContext(), log, status,
				data, getReport(), getReportLogger(), MonthlyPlanningGroupPage.class);
		TestStatus status = getTestStatus();
		System.out.println("Entering into TestCase");
		OptionToView = getContext().getStringProperty("OptionToView");
		if (OptionToView.contains("Budget"))
			Name = pageWeeklyPlanningPO.MonthName(objWeeklyPlanningStoreObjects, extentTest,
					objWeeklyPlanningStoreObjects.CurrentWeekBGDT);
		if (OptionToView.contains("Forecast"))
			Name = pageWeeklyPlanningPO.MonthName(objWeeklyPlanningStoreObjects, extentTest,
					objWeeklyPlanningStoreObjects.CurrentWeekFCST);
		WeekName = Name.split(" ");
		Week = WeekName[0];
		Year = getContext().getStringProperty("Year");
		LYYear = Integer.parseInt(Year) - 1;
		LYYr = String.valueOf(LYYear);

		String PlannedCPHTotal = data.getText("PlannedCPHTotal");
		DBTotal = sql.CLRexecuteQuery(getContext(),
				PlannedCPHTotal.replace("Region", getContext().getStringProperty("Region"))
						.replace("Area", getContext().getStringProperty("Area"))
						.replace("Zone", getContext().getStringProperty("Zone")).replace("FinWeek", Week)
						.replace("FinYear", getContext().getStringProperty("Year")).replace("LYFinYr", LYYr).replace("Division", getContext().getStringProperty("Division")));
		System.out.println("DB Total: " + DBTotal);

		UITotal = pageMonthlyPlanningGroupPO.WPGetTotal(extentTest, objMonthlyPlanningGroupObjects.RowNameTotal,
				objWeeklyPlanningStoreObjects.PlannedCPH);
		pageMonthlyPlanningPO.CompareValueDecimalFields(extentTest, DBTotal, UITotal);
	}

	// Growth LY CPH
	@Test(description = "Weekly Planning - WOWGroup, Growth LY CPH - Current Week", priority = 55)
	public void GrowthLYCPH_CurrentMonth() throws Exception {
		pageWeeklyPlanningPO = new WeeklyPlanningStorePage().createPage(getDriver(), getContext(), log, status, data,
				getReport(), getReportLogger(), WeeklyPlanningStorePage.class);
		pageMonthlyPlanningGroupPO = new WeeklyPlanningStorePage().createPage(getDriver(), getContext(), log, status,
				data, getReport(), getReportLogger(), MonthlyPlanningGroupPage.class);
		TestStatus status = getTestStatus();
		System.out.println("Entering into TestCase");
		OptionToView = getContext().getStringProperty("OptionToView");
		if (OptionToView.contains("Budget"))
			Name = pageWeeklyPlanningPO.MonthName(objWeeklyPlanningStoreObjects, extentTest,
					objWeeklyPlanningStoreObjects.CurrentWeekBGDT);
		if (OptionToView.contains("Forecast"))
			Name = pageWeeklyPlanningPO.MonthName(objWeeklyPlanningStoreObjects, extentTest,
					objWeeklyPlanningStoreObjects.CurrentWeekFCST);
		WeekName = Name.split(" ");
		Week = WeekName[0];
		Year = getContext().getStringProperty("Year");
		LYYear = Integer.parseInt(Year) - 1;
		LYYr = String.valueOf(LYYear);

		String GrowthLYCPHTotal = data.getText("GrowthLYCPHTotal");
		DBTotal = sql.CLRexecuteQuery(getContext(),
				GrowthLYCPHTotal.replace("Region", getContext().getStringProperty("Region"))
						.replace("Area", getContext().getStringProperty("Area"))
						.replace("Zone", getContext().getStringProperty("Zone")).replace("FinWeek", Week)
						.replace("FinYear", getContext().getStringProperty("Year")).replace("LYFinYr", LYYr).replace("Division", getContext().getStringProperty("Division")));
		System.out.println("DB Total: " + DBTotal);

		UITotal = pageMonthlyPlanningGroupPO.WPGetTotal(extentTest, objMonthlyPlanningGroupObjects.RowNameTotal,
				objWeeklyPlanningStoreObjects.GrowthLYCPH);
		pageMonthlyPlanningPO.CompareValueDecimalFields(extentTest, DBTotal, UITotal);
	}
		
	// Planned BDGT Rate
	@Test(description = "Weekly Planning - WOW Group, Planned BDGT Rate - Current Week", priority = 55)
	public void PlannedBDGTRate_CurrentMonth() throws Exception {
		pageWeeklyPlanningPO = new WeeklyPlanningStorePage().createPage(getDriver(), getContext(), log, status, data,
				getReport(), getReportLogger(), WeeklyPlanningStorePage.class);
		pageMonthlyPlanningGroupPO = new WeeklyPlanningStorePage().createPage(getDriver(), getContext(), log, status,
				data, getReport(), getReportLogger(), MonthlyPlanningGroupPage.class);
		TestStatus status = getTestStatus();
		System.out.println("Entering into TestCase");
		OptionToView = getContext().getStringProperty("OptionToView");
		String PositiveFactor = data.getText("PositiveFactor");
		DBPstive = sql.CLRexecuteQuery(getContext(),
				PositiveFactor.replace("Region", getContext().getStringProperty("Region")).replace("Week",
						getContext().getStringProperty("SelectFiscalWeek")));
		System.out.println("PositveFactor: " + DBPstive);

		String NegativeFactor = data.getText("NegativeFactor");
		DBNgtive = sql.CLRexecuteQuery(getContext(),
				NegativeFactor.replace("Region", getContext().getStringProperty("Region")).replace("Week",
						getContext().getStringProperty("SelectFiscalWeek")));
		System.out.println("NegativeFactor: " + DBNgtive);
		if (OptionToView.contains("Budget"))
			pageWeeklyPlanningPO.PlannedSalestoBDGTRate(objWeeklyPlanningStoreObjects, extentTest,
					objWeeklyPlanningStoreObjects.BDGTRate, objWeeklyPlanningStoreObjects.BudgetWagesPercent,
					objWeeklyPlanningStoreObjects.PlannedSales, objWeeklyPlanningStoreObjects.BudgetSales,
					objWeeklyPlanningStoreObjects.PlannedWages, objWeeklyPlanningStoreObjects.BudgetWages, DBPstive,
					DBNgtive);
		if (OptionToView.contains("Forecast"))
			pageWeeklyPlanningPO.PlannedSalestoBDGTRate(objWeeklyPlanningStoreObjects, extentTest,
					objWeeklyPlanningStoreObjects.FCSTRate, objWeeklyPlanningStoreObjects.ForecastWagesPercent,
					objWeeklyPlanningStoreObjects.PlannedSales, objWeeklyPlanningStoreObjects.ForecastSales,
					objWeeklyPlanningStoreObjects.PlannedWages, objWeeklyPlanningStoreObjects.ForecastWages, DBPstive,
					DBNgtive);
	}

	// MonthlyViewMetricsValidation
	@Test(description = "Weekly Planning - WOWGroup, MonthlyViewMetricsValidation", priority = 57)
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

		String Sales = data.getText("Sales");
		DBValues = sql.CLRexecuteQuery(getContext(),
				Sales.replace("Region", getContext().getStringProperty("Region"))
						.replace("Area", getContext().getStringProperty("Area"))
						.replace("Zone", getContext().getStringProperty("Zone"))
						.replace("Month", getContext().getStringProperty("MonthToTest"))
						.replace("Division", getContext().getStringProperty("Division")));
		System.out.println("DB Value: " + DBValues);

		String SalesTotal = data.getText("SalesTotal");
		DBTotal = sql.CLRexecuteQuery(getContext(),
				SalesTotal.replace("Region", getContext().getStringProperty("Region"))
						.replace("Area", getContext().getStringProperty("Area"))
						.replace("Zone", getContext().getStringProperty("Zone"))
						.replace("Month", getContext().getStringProperty("MonthToTest"))
						.replace("Division", getContext().getStringProperty("Division")));
		System.out.println("DB Total: " + DBTotal);

		UIValues = pageWeeklyPlanningPO.WPNewTable_GetUIValue(extentTest, WeeklyPlanningStoreObjects.Sales,
				WeeklyPlanningStoreObjects.Week, WeeklyPlanningStoreObjects.Table);
		pageMonthlyPlanningPO.CompareValues(extentTest, DBValues, UIValues);

		UITotal = pageWeeklyPlanningPO.WPNewTable_GetTotal(extentTest, WeeklyPlanningStoreObjects.WeekTotal,
				WeeklyPlanningStoreObjects.SalesTtl, WeeklyPlanningStoreObjects.Table);
		pageMonthlyPlanningPO.CompareValues(extentTest, DBTotal, UITotal);

		// Wages
		Report_AddStep("testcase", "WAGES COLUMN VALIDATION - MONTHLY DATA", "", "", "");
		extentTest.log(LogStatus.INFO, "WAGES COLUMN VALIDATION - MONTHLY DATA");

		String Wages = data.getText("Wages");
		DBValues1 = sql.CLRexecuteQuery(getContext(),
				Wages.replace("Region", getContext().getStringProperty("Region"))
						.replace("Area", getContext().getStringProperty("Area"))
						.replace("Zone", getContext().getStringProperty("Zone"))
						.replace("Month", getContext().getStringProperty("MonthToTest"))
						.replace("Division", getContext().getStringProperty("Division")));
		System.out.println("DB Value: " + DBValues1);

		String WagesTotal = data.getText("WagesTotal");
		DBTotal1 = sql.CLRexecuteQuery(getContext(),
				WagesTotal.replace("Region", getContext().getStringProperty("Region"))
						.replace("Area", getContext().getStringProperty("Area"))
						.replace("Zone", getContext().getStringProperty("Zone"))
						.replace("Month", getContext().getStringProperty("MonthToTest"))
						.replace("Division", getContext().getStringProperty("Division")));
		System.out.println("DB Total: " + DBTotal1);

		UIValues1 = pageWeeklyPlanningPO.WPNewTable_GetUIValue(extentTest, WeeklyPlanningStoreObjects.Wages,
				WeeklyPlanningStoreObjects.Week, WeeklyPlanningStoreObjects.Table);
		pageMonthlyPlanningPO.CompareValues(extentTest, DBValues1, UIValues1);

		UITotal1 = pageWeeklyPlanningPO.WPNewTable_GetTotal(extentTest, WeeklyPlanningStoreObjects.WeekTotal,
				WeeklyPlanningStoreObjects.WagesTtl, WeeklyPlanningStoreObjects.Table);
		pageMonthlyPlanningPO.CompareValues(extentTest, DBTotal1, UITotal1);

		// Wage %
		Report_AddStep("testcase", "WAGES PERCENTAGE COLUMN VALIDATION - MONTHLY DATA", "", "", "");
		extentTest.log(LogStatus.INFO, "WAGES PERCENTAGE COLUMN VALIDATION - MONTHLY DATA");

		String WagePercentage = data.getText("WagePercentage");
		DBValues2 = sql.CLRexecuteQuery(getContext(),
				WagePercentage.replace("Region", getContext().getStringProperty("Region"))
						.replace("Area", getContext().getStringProperty("Area"))
						.replace("Zone", getContext().getStringProperty("Zone"))
						.replace("Month", getContext().getStringProperty("MonthToTest"))
						.replace("Division", getContext().getStringProperty("Division")));
		System.out.println("DB Value: " + DBValues2);

		String WagePercentageTotal = data.getText("WagePercentageTotal");
		DBTotal2 = sql.CLRexecuteQuery(getContext(),
				WagePercentageTotal.replace("Region", getContext().getStringProperty("Region"))
						.replace("Area", getContext().getStringProperty("Area"))
						.replace("Zone", getContext().getStringProperty("Zone"))
						.replace("Month", getContext().getStringProperty("MonthToTest"))
						.replace("Division", getContext().getStringProperty("Division")));
		System.out.println("DB Total: " + DBTotal2);

		UIValues2 = pageWeeklyPlanningPO.WPNewTable_GetUIValue(extentTest, WeeklyPlanningStoreObjects.WagePer,
				WeeklyPlanningStoreObjects.Week, WeeklyPlanningStoreObjects.Table);
		pageMonthlyPlanningPO.CompareValueDecimalFields(extentTest, DBValues2, UIValues2);

		UITotal2 = pageWeeklyPlanningPO.WPNewTable_GetTotal(extentTest, WeeklyPlanningStoreObjects.WeekTotal,
				WeeklyPlanningStoreObjects.WagePerTtl, WeeklyPlanningStoreObjects.Table);
		pageMonthlyPlanningPO.CompareValueDecimalFields(extentTest, DBTotal2, UITotal2);

		// CPH
		Report_AddStep("testcase", "CPH COLUMN VALIDATION - MONTHLY DATA", "", "", "");
		extentTest.log(LogStatus.INFO, "CPH COLUMN VALIDATION - MONTHLY DATA");

		String CPHValue = data.getText("CPHValue");
		DBValues3 = sql.CLRexecuteQuery(getContext(),
				CPHValue.replace("Region", getContext().getStringProperty("Region"))
						.replace("Area", getContext().getStringProperty("Area"))
						.replace("Zone", getContext().getStringProperty("Zone"))
						.replace("Month", getContext().getStringProperty("MonthToTest"))
						.replace("Division", getContext().getStringProperty("Division")));
		System.out.println("DB Value: " + DBValues3);

		String CPHTotal = data.getText("CPHTotal");
		DBTotal3 = sql.CLRexecuteQuery(getContext(),
				CPHTotal.replace("Region", getContext().getStringProperty("Region"))
						.replace("Area", getContext().getStringProperty("Area"))
						.replace("Zone", getContext().getStringProperty("Zone"))
						.replace("Month", getContext().getStringProperty("MonthToTest"))
						.replace("Division", getContext().getStringProperty("Division")));
		System.out.println("DB Total: " + DBTotal3);

		UIValues3 = pageWeeklyPlanningPO.WPNewTable_GetUIValue(extentTest, WeeklyPlanningStoreObjects.CPH,
				WeeklyPlanningStoreObjects.Week, WeeklyPlanningStoreObjects.Table);
		pageMonthlyPlanningPO.CompareValueDecimalFields(extentTest, DBValues3, UIValues3);

		UITotal3 = pageWeeklyPlanningPO.WPNewTable_GetTotal(extentTest, WeeklyPlanningStoreObjects.WeekTotal,
				WeeklyPlanningStoreObjects.CPHTtL, WeeklyPlanningStoreObjects.Table);
		pageMonthlyPlanningPO.CompareValueDecimalFields(extentTest, DBTotal3, UITotal3);

		// Hours
		Report_AddStep("testcase", "PLAN PAID HOURS COLUMN VALIDATION - MONTHLY DATA", "", "", "");
		extentTest.log(LogStatus.INFO, "PLAN PAID HOURS COLUMN VALIDATION - MONTHLY DATA");

		String PlanPaidHours = data.getText("PlanPaidHours");
		DBValues4 = sql.CLRexecuteQuery(getContext(),
				PlanPaidHours.replace("Region", getContext().getStringProperty("Region"))
						.replace("Area", getContext().getStringProperty("Area"))
						.replace("Zone", getContext().getStringProperty("Zone"))
						.replace("Month", getContext().getStringProperty("MonthToTest"))
						.replace("Division", getContext().getStringProperty("Division")));
		System.out.println("DB Value: " + DBValues4);

		String PlanPaidHoursTotal = data.getText("PlanPaidHoursTotal");
		DBTotal4 = sql.CLRexecuteQuery(getContext(),
				PlanPaidHoursTotal.replace("Region", getContext().getStringProperty("Region"))
						.replace("Area", getContext().getStringProperty("Area"))
						.replace("Zone", getContext().getStringProperty("Zone"))
						.replace("Month", getContext().getStringProperty("MonthToTest"))
						.replace("Division", getContext().getStringProperty("Division")));
		System.out.println("DB Total: " + DBTotal4);

		UIValues4 = pageWeeklyPlanningPO.WPNewTable_GetUIValue(extentTest, WeeklyPlanningStoreObjects.PlannPaidHrs,
				WeeklyPlanningStoreObjects.Week, WeeklyPlanningStoreObjects.Table);
		pageMonthlyPlanningPO.CompareValues(extentTest, DBValues4, UIValues4);

		UITotal4 = pageWeeklyPlanningPO.WPNewTable_GetTotal(extentTest, WeeklyPlanningStoreObjects.WeekTotal,
				WeeklyPlanningStoreObjects.PlannPaidHrsTtl, WeeklyPlanningStoreObjects.Table);
		pageMonthlyPlanningPO.CompareValues(extentTest, DBTotal4, UITotal4);

		// OR
		Report_AddStep("testcase", "OR PERCENTAGE COLUMN VALIDATION - MONTHLY DATA", "", "", "");
		extentTest.log(LogStatus.INFO, "OR PERCENTAGE COLUMN VALIDATION - MONTHLY DATA");

		String ORPercentage = data.getText("ORPercentage");
		DBValues5 = sql.CLRexecuteQuery(getContext(),
				ORPercentage.replace("Region", getContext().getStringProperty("Region"))
						.replace("Area", getContext().getStringProperty("Area"))
						.replace("Zone", getContext().getStringProperty("Zone"))
						.replace("Month", getContext().getStringProperty("MonthToTest"))
						.replace("Division", getContext().getStringProperty("Division")));
		System.out.println("DB Value: " + DBValues5);

		String ORPercentageTotal = data.getText("ORPercentageTotal");
		DBTotal5 = sql.CLRexecuteQuery(getContext(),
				ORPercentageTotal.replace("Region", getContext().getStringProperty("Region"))
						.replace("Area", getContext().getStringProperty("Area"))
						.replace("Zone", getContext().getStringProperty("Zone"))
						.replace("Month", getContext().getStringProperty("MonthToTest"))
						.replace("Division", getContext().getStringProperty("Division")));
		System.out.println("DB Total: " + DBTotal5);

		UIValues5 = pageWeeklyPlanningPO.WPNewTable_GetUIValue(extentTest, WeeklyPlanningStoreObjects.ORPER,
				WeeklyPlanningStoreObjects.Week, WeeklyPlanningStoreObjects.Table);
		pageMonthlyPlanningPO.CompareValueDecimalFields(extentTest, DBValues5, UIValues5);

		UITotal5 = pageWeeklyPlanningPO.WPNewTable_GetTotal(extentTest, WeeklyPlanningStoreObjects.WeekTotal,
				WeeklyPlanningStoreObjects.ORPERTtl, WeeklyPlanningStoreObjects.Table);
		pageMonthlyPlanningPO.CompareValueDecimalFields(extentTest, DBTotal5, UITotal5);
	}

	@Test(priority = 60)
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
		pageMonthlyPlanningPO = PageFactory.initElements(this.getDriver(), MonthlyPlanningStorePage.class);
		pageMonthlyPlanningGroupPO = PageFactory.initElements(this.getDriver(), MonthlyPlanningGroupPage.class);
		BaseObj = PageFactory.initElements(this.getDriver(), BasePage.class);
		common = PageFactory.initElements(this.getDriver(), CommonFunctions.class);
		storeprop = PageFactory.initElements(this.getDriver(), StoreProperties.class);
		objWeeklyPlanningStoreObjects = PageFactory.initElements(this.getDriver(), WeeklyPlanningStoreObjects.class);
		objMonthlyPlanningStoreObjects = PageFactory.initElements(this.getDriver(), MonthlyPlanningStoreObjects.class);
		objMonthlyPlanningGroupObjects = PageFactory.initElements(this.getDriver(), MonthlyPlanningGroupObjects.class);
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
			data.loadTestDataXML(System.getProperty("user.dir") + "\\TestData\\WLP\\WeeklyPlanningWOWGroup.xml");
			customreport.createExtentReport();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

}