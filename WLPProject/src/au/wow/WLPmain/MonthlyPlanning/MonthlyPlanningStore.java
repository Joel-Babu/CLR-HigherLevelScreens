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
import au.wow.WLPmain.objects.MonthlyPlanningStoreObjects;
import au.wow.WLPmain.objects.WeeklyPlanningStoreObjects;
import au.wow.WLPmain.pages.CommonFunctions;
import au.wow.WLPmain.pages.DailyPlanningMainPage;
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

public class MonthlyPlanningStore extends TestBase {

	protected Logger log = LogManager.getLogger(MonthlyPlanningStore.class);
	TestStatus status;
	ExtentReportsScreenshotCode ScreenShotUtil;
	SQLWrapper sql = new SQLWrapper(log);
	WoWLoginPage loginPage;
	CommonRepository CommonFunc;
	WeeklyPlanningStoreObjects objWeeklyPlanningStoreObjects;
	MonthlyPlanningStoreObjects objMonthlyPlanningStoreObjects;
	WeeklyPlanningStorePage pageWeeklyPlanningPO;
	MonthlyPlanningStorePage pageMonthlyPlanningPO;
	BasePage BaseObj;
	CommonFunctions common;
	StoreProperties storeprop;
	List<List<Object>> DBValues, DBPstive, DBNgtive, DBPstiveTtl, DBNgtiveTtl;
	List<List<Object>> DBTotal;
	List<List<Object>> UIValues;
	List<List<Object>> UITotal;
	String OptionToView, Month;

	public MonthlyPlanningStore() {
		super.log = log;
	}

	@Test(priority = 0)
	public void LogintoCLRApplication() throws Exception {
		loginPage = new WoWLoginPage().createPage(getDriver(), getContext(), log, status, data, report, extentTest,
				WoWLoginPage.class);
		pageWeeklyPlanningPO = loginPage.navToWeeklyPlanningStorePg(getContext().getStringProperty("store"));
	}

	@Test(description = "Monthly Planning - Store, Sales history - First Month", priority = 1)
	public void SalesHistoryFirstMonth() throws Exception {
		pageWeeklyPlanningPO = new WeeklyPlanningStorePage().createPage(getDriver(), getContext(), log, status, data,
				getReport(), getReportLogger(), WeeklyPlanningStorePage.class);
		TestStatus status = getTestStatus();
		System.out.println("Entering into TestCase");
		pageWeeklyPlanningPO.SelectWeekFromDropdown(objWeeklyPlanningStoreObjects, extentTest,
				getContext().getStringProperty("SelectFiscalWeek"));

		pageWeeklyPlanningPO.selectPage(extentTest, objWeeklyPlanningStoreObjects.menuBar,
				objWeeklyPlanningStoreObjects.planningScreens, "Planning Screens");
		pageWeeklyPlanningPO.selectPage(extentTest, objWeeklyPlanningStoreObjects.menuBar,
				objWeeklyPlanningStoreObjects.monthlyPlanning, "Monthly Planning");
		pageWeeklyPlanningPO.selectPage(extentTest, objWeeklyPlanningStoreObjects.menuBar,
				objWeeklyPlanningStoreObjects.MPscreenToView, "Store");
		// pageWeeklyPlanningPO.verifyPageName(extentTest,
		// objWeeklyPlanningStoreObjects.DataFrame,
		// objWeeklyPlanningStoreObjects.pageName,
		// getContext().getStringProperty("MonthlyPlanningStore"));

		pageWeeklyPlanningPO.SelectHistoryDropdownToView(objWeeklyPlanningStoreObjects, extentTest, "Sales");

		String Month = pageWeeklyPlanningPO.MonthName(objWeeklyPlanningStoreObjects, extentTest,
				objMonthlyPlanningStoreObjects.FirstMonthNm);
		String SalesHistory = data.getText("SalesHistory");
		DBValues = sql.CLRexecuteQuery(getContext(),
				SalesHistory.replace("store", getContext().getStringProperty("store")).replace("Month", Month).replace("Division", getContext().getStringProperty("Division")));
		System.out.println("DB Value: " + DBValues);

		String SalesHistoryTotal = data.getText("SalesHistoryTotal");
		DBTotal = sql.CLRexecuteQuery(getContext(),
				SalesHistoryTotal.replace("store", getContext().getStringProperty("store")).replace("Month", Month).replace("Division", getContext().getStringProperty("Division")));
		System.out.println("DB Total: " + DBTotal);

		UIValues = pageWeeklyPlanningPO.UIResults(objMonthlyPlanningStoreObjects.MPTable, extentTest,
				objWeeklyPlanningStoreObjects.DepartmentNames, objMonthlyPlanningStoreObjects.FirstMonth);
		pageMonthlyPlanningPO.CompareValues(extentTest, DBValues, UIValues);

		UITotal = pageMonthlyPlanningPO.GetTotal(extentTest, objMonthlyPlanningStoreObjects.TotalRow,
				objMonthlyPlanningStoreObjects.FirstMonthHistory);
		pageMonthlyPlanningPO.CompareValues(extentTest, DBTotal, UITotal);
	}

	@Test(description = "Monthly Planning - Store, Sales history - Second Month", priority = 2)
	public void SalesHistorySecondMonth() throws Exception {
		pageWeeklyPlanningPO = new WeeklyPlanningStorePage().createPage(getDriver(), getContext(), log, status, data,
				getReport(), getReportLogger(), WeeklyPlanningStorePage.class);
		TestStatus status = getTestStatus();
		System.out.println("Entering into TestCase");
		String Month = pageWeeklyPlanningPO.MonthName(objWeeklyPlanningStoreObjects, extentTest,
				objMonthlyPlanningStoreObjects.SecondMonthNm);

		String SalesHistory = data.getText("SalesHistory");
		DBValues = sql.CLRexecuteQuery(getContext(),
				SalesHistory.replace("store", getContext().getStringProperty("store")).replace("Month", Month).replace("Division", getContext().getStringProperty("Division")));
		System.out.println("DB Value: " + DBValues);

		String SalesHistoryTotal = data.getText("SalesHistoryTotal");
		DBTotal = sql.CLRexecuteQuery(getContext(),
				SalesHistoryTotal.replace("store", getContext().getStringProperty("store")).replace("Month", Month).replace("Division", getContext().getStringProperty("Division")));
		System.out.println("DB Total: " + DBTotal);

		UIValues = pageWeeklyPlanningPO.UIResults(objMonthlyPlanningStoreObjects.MPTable, extentTest,
				objWeeklyPlanningStoreObjects.DepartmentNames, objMonthlyPlanningStoreObjects.SecondMonth);
		pageMonthlyPlanningPO.CompareValues(extentTest, DBValues, UIValues);

		UITotal = pageMonthlyPlanningPO.GetTotal(extentTest, objMonthlyPlanningStoreObjects.TotalRow,
				objMonthlyPlanningStoreObjects.SecondMonthHistory);
		pageMonthlyPlanningPO.CompareValues(extentTest, DBTotal, UITotal);
	}

	@Test(description = "Monthly Planning - Store, Sales history - Third Month", priority = 3)
	public void SalesHistoryThirdMonth() throws Exception {
		pageWeeklyPlanningPO = new WeeklyPlanningStorePage().createPage(getDriver(), getContext(), log, status, data,
				getReport(), getReportLogger(), WeeklyPlanningStorePage.class);
		TestStatus status = getTestStatus();
		System.out.println("Entering into TestCase");
		String Month = pageWeeklyPlanningPO.MonthName(objWeeklyPlanningStoreObjects, extentTest,
				objMonthlyPlanningStoreObjects.ThirdMonthNm);

		String SalesHistory = data.getText("SalesHistory");
		DBValues = sql.CLRexecuteQuery(getContext(),
				SalesHistory.replace("store", getContext().getStringProperty("store")).replace("Month", Month).replace("Division", getContext().getStringProperty("Division")));
		System.out.println("DB Value: " + DBValues);

		String SalesHistoryTotal = data.getText("SalesHistoryTotal");
		DBTotal = sql.CLRexecuteQuery(getContext(),
				SalesHistoryTotal.replace("store", getContext().getStringProperty("store")).replace("Month", Month).replace("Division", getContext().getStringProperty("Division")));
		System.out.println("DB Total: " + DBTotal);

		UIValues = pageWeeklyPlanningPO.UIResults(objMonthlyPlanningStoreObjects.MPTable, extentTest,
				objWeeklyPlanningStoreObjects.DepartmentNames, objMonthlyPlanningStoreObjects.ThirdMonth);
		pageMonthlyPlanningPO.CompareValues(extentTest, DBValues, UIValues);

		UITotal = pageMonthlyPlanningPO.GetTotal(extentTest, objMonthlyPlanningStoreObjects.TotalRow,
				objMonthlyPlanningStoreObjects.ThirdMonthHistory);
		pageMonthlyPlanningPO.CompareValues(extentTest, DBTotal, UITotal);
	}

	@Test(description = "Monthly Planning - Store, Sales history - Fourth Month", priority = 4)
	public void SalesHistoryFourthMonth() throws Exception {
		pageWeeklyPlanningPO = new WeeklyPlanningStorePage().createPage(getDriver(), getContext(), log, status, data,
				getReport(), getReportLogger(), WeeklyPlanningStorePage.class);
		TestStatus status = getTestStatus();
		System.out.println("Entering into TestCase");
		String Month = pageWeeklyPlanningPO.MonthName(objWeeklyPlanningStoreObjects, extentTest,
				objMonthlyPlanningStoreObjects.FourthmonthNm);

		String SalesHistory = data.getText("SalesHistory");
		DBValues = sql.CLRexecuteQuery(getContext(),
				SalesHistory.replace("store", getContext().getStringProperty("store")).replace("Month", Month).replace("Division", getContext().getStringProperty("Division")));
		System.out.println("DB Value: " + DBValues);

		String SalesHistoryTotal = data.getText("SalesHistoryTotal");
		DBTotal = sql.CLRexecuteQuery(getContext(),
				SalesHistoryTotal.replace("store", getContext().getStringProperty("store")).replace("Month", Month).replace("Division", getContext().getStringProperty("Division")));
		System.out.println("DB Total: " + DBTotal);

		UIValues = pageWeeklyPlanningPO.UIResults(objMonthlyPlanningStoreObjects.MPTable, extentTest,
				objWeeklyPlanningStoreObjects.DepartmentNames, objMonthlyPlanningStoreObjects.FourMonth);
		pageMonthlyPlanningPO.CompareValues(extentTest, DBValues, UIValues);

		UITotal = pageMonthlyPlanningPO.GetTotal(extentTest, objMonthlyPlanningStoreObjects.TotalRow,
				objMonthlyPlanningStoreObjects.FourthMonthHistory);
		pageMonthlyPlanningPO.CompareValues(extentTest, DBTotal, UITotal);
	}

	// Wages
	@Test(description = "Monthly Planning - Store, Wages history - First Month", priority = 5)
	public void WagesHistoryFirsthMonth() throws Exception {
		pageWeeklyPlanningPO = new WeeklyPlanningStorePage().createPage(getDriver(), getContext(), log, status, data,
				getReport(), getReportLogger(), WeeklyPlanningStorePage.class);
		TestStatus status = getTestStatus();
		System.out.println("Entering into TestCase");
		pageWeeklyPlanningPO.SelectHistoryDropdownToView(objWeeklyPlanningStoreObjects, extentTest, "Wages");
		String Month = pageWeeklyPlanningPO.MonthName(objWeeklyPlanningStoreObjects, extentTest,
				objMonthlyPlanningStoreObjects.FirstMonthNm);

		String WagesHistory = data.getText("WagesHistory");
		DBValues = sql.CLRexecuteQuery(getContext(),
				WagesHistory.replace("store", getContext().getStringProperty("store")).replace("Month", Month).replace("Division", getContext().getStringProperty("Division")));
		System.out.println("DB Value: " + DBValues);

		String WagesHistoryTotal = data.getText("WagesHistoryTotal");
		DBTotal = sql.CLRexecuteQuery(getContext(),
				WagesHistoryTotal.replace("store", getContext().getStringProperty("store")).replace("Month", Month).replace("Division", getContext().getStringProperty("Division")));
		System.out.println("DB Total: " + DBTotal);

		UIValues = pageWeeklyPlanningPO.UIResults(objMonthlyPlanningStoreObjects.MPTable, extentTest,
				objWeeklyPlanningStoreObjects.DepartmentNames, objMonthlyPlanningStoreObjects.FirstMonth);
		pageMonthlyPlanningPO.CompareValues(extentTest, DBValues, UIValues);

		UITotal = pageMonthlyPlanningPO.GetTotal(extentTest, objMonthlyPlanningStoreObjects.TotalRow,
				objMonthlyPlanningStoreObjects.FirstMonthHistory);
		pageMonthlyPlanningPO.CompareValues(extentTest, DBTotal, UITotal);
	}

	@Test(description = "Monthly Planning - Store, Wages history - Second Month", priority = 6)
	public void WagesHistorySecondMonth() throws Exception {
		pageWeeklyPlanningPO = new WeeklyPlanningStorePage().createPage(getDriver(), getContext(), log, status, data,
				getReport(), getReportLogger(), WeeklyPlanningStorePage.class);
		TestStatus status = getTestStatus();
		System.out.println("Welcome " + getContext().getStringProperty("username"));
		System.out.println("Entering into TestCase");
		String Month = pageWeeklyPlanningPO.MonthName(objWeeklyPlanningStoreObjects, extentTest,
				objMonthlyPlanningStoreObjects.SecondMonthNm);

		String WagesHistory = data.getText("WagesHistory");
		DBValues = sql.CLRexecuteQuery(getContext(),
				WagesHistory.replace("store", getContext().getStringProperty("store")).replace("Month", Month).replace("Division", getContext().getStringProperty("Division")));
		System.out.println("DB Value: " + DBValues);

		String WagesHistoryTotal = data.getText("WagesHistoryTotal");
		DBTotal = sql.CLRexecuteQuery(getContext(),
				WagesHistoryTotal.replace("store", getContext().getStringProperty("store")).replace("Month", Month).replace("Division", getContext().getStringProperty("Division")));
		System.out.println("DB Total: " + DBTotal);

		UIValues = pageWeeklyPlanningPO.UIResults(objMonthlyPlanningStoreObjects.MPTable, extentTest,
				objWeeklyPlanningStoreObjects.DepartmentNames, objMonthlyPlanningStoreObjects.SecondMonth);
		pageMonthlyPlanningPO.CompareValues(extentTest, DBValues, UIValues);

		UITotal = pageMonthlyPlanningPO.GetTotal(extentTest, objMonthlyPlanningStoreObjects.TotalRow,
				objMonthlyPlanningStoreObjects.SecondMonthHistory);
		pageMonthlyPlanningPO.CompareValues(extentTest, DBTotal, UITotal);
	}

	@Test(description = "Monthly Planning - Store, Wages history - Third Month", priority = 7)
	public void WagesHistoryThirdMonth() throws Exception {
		pageWeeklyPlanningPO = new WeeklyPlanningStorePage().createPage(getDriver(), getContext(), log, status, data,
				getReport(), getReportLogger(), WeeklyPlanningStorePage.class);
		TestStatus status = getTestStatus();
		System.out.println("Welcome " + getContext().getStringProperty("username"));
		System.out.println("Entering into TestCase");
		String Month = pageWeeklyPlanningPO.MonthName(objWeeklyPlanningStoreObjects, extentTest,
				objMonthlyPlanningStoreObjects.ThirdMonthNm);

		String WagesHistory = data.getText("WagesHistory");
		DBValues = sql.CLRexecuteQuery(getContext(),
				WagesHistory.replace("store", getContext().getStringProperty("store")).replace("Month", Month).replace("Division", getContext().getStringProperty("Division")));
		System.out.println("DB Value: " + DBValues);

		String WagesHistoryTotal = data.getText("WagesHistoryTotal");
		DBTotal = sql.CLRexecuteQuery(getContext(),
				WagesHistoryTotal.replace("store", getContext().getStringProperty("store")).replace("Month", Month).replace("Division", getContext().getStringProperty("Division")));
		System.out.println("DB Total: " + DBTotal);

		UIValues = pageWeeklyPlanningPO.UIResults(objMonthlyPlanningStoreObjects.MPTable, extentTest,
				objWeeklyPlanningStoreObjects.DepartmentNames, objMonthlyPlanningStoreObjects.ThirdMonth);
		pageMonthlyPlanningPO.CompareValues(extentTest, DBValues, UIValues);

		UITotal = pageMonthlyPlanningPO.GetTotal(extentTest, objMonthlyPlanningStoreObjects.TotalRow,
				objMonthlyPlanningStoreObjects.ThirdMonthHistory);
		pageMonthlyPlanningPO.CompareValues(extentTest, DBTotal, UITotal);
	}

	@Test(description = "Monthly Planning - Store, Wages history - Fourth Month", priority = 8)
	public void WagesHistoryFourthMonth() throws Exception {
		pageWeeklyPlanningPO = new WeeklyPlanningStorePage().createPage(getDriver(), getContext(), log, status, data,
				getReport(), getReportLogger(), WeeklyPlanningStorePage.class);
		TestStatus status = getTestStatus();
		System.out.println("Welcome " + getContext().getStringProperty("username"));
		System.out.println("Entering into TestCase");
		String Month = pageWeeklyPlanningPO.MonthName(objWeeklyPlanningStoreObjects, extentTest,
				objMonthlyPlanningStoreObjects.FourthmonthNm);

		String WagesHistory = data.getText("WagesHistory");
		DBValues = sql.CLRexecuteQuery(getContext(),
				WagesHistory.replace("store", getContext().getStringProperty("store")).replace("Month", Month).replace("Division", getContext().getStringProperty("Division")));
		System.out.println("DB Value: " + DBValues);

		String WagesHistoryTotal = data.getText("WagesHistoryTotal");
		DBTotal = sql.CLRexecuteQuery(getContext(),
				WagesHistoryTotal.replace("store", getContext().getStringProperty("store")).replace("Month", Month).replace("Division", getContext().getStringProperty("Division")));
		System.out.println("DB Total: " + DBTotal);

		UIValues = pageWeeklyPlanningPO.UIResults(objMonthlyPlanningStoreObjects.MPTable, extentTest,
				objWeeklyPlanningStoreObjects.DepartmentNames, objMonthlyPlanningStoreObjects.FourMonth);
		pageMonthlyPlanningPO.CompareValues(extentTest, DBValues, UIValues);

		UITotal = pageMonthlyPlanningPO.GetTotal(extentTest, objMonthlyPlanningStoreObjects.TotalRow,
				objMonthlyPlanningStoreObjects.FourthMonthHistory);
		pageMonthlyPlanningPO.CompareValues(extentTest, DBTotal, UITotal);
	}

	// Items
	@Test(description = "Monthly Planning - Store, Items history - First Month", priority = 9)
	public void ItemsHistoryFirsthMonth() throws Exception {
		pageWeeklyPlanningPO = new WeeklyPlanningStorePage().createPage(getDriver(), getContext(), log, status, data,
				getReport(), getReportLogger(), WeeklyPlanningStorePage.class);
		TestStatus status = getTestStatus();
		System.out.println("Entering into TestCase");
		pageWeeklyPlanningPO.SelectHistoryDropdownToView(objWeeklyPlanningStoreObjects, extentTest, "Items");
		String Month = pageWeeklyPlanningPO.MonthName(objWeeklyPlanningStoreObjects, extentTest,
				objMonthlyPlanningStoreObjects.FirstMonthNm);

		String ItemHistory = data.getText("ItemHistory");
		DBValues = sql.CLRexecuteQuery(getContext(),
				ItemHistory.replace("store", getContext().getStringProperty("store")).replace("Month", Month).replace("Division", getContext().getStringProperty("Division")));
		System.out.println("DB Value: " + DBValues);

		String ItemHistoryTotal = data.getText("ItemHistoryTotal");
		DBTotal = sql.CLRexecuteQuery(getContext(),
				ItemHistoryTotal.replace("store", getContext().getStringProperty("store")).replace("Month", Month).replace("Division", getContext().getStringProperty("Division")));
		System.out.println("DB Total: " + DBTotal);

		UIValues = pageWeeklyPlanningPO.UIResults(objMonthlyPlanningStoreObjects.MPTable, extentTest,
				objWeeklyPlanningStoreObjects.DepartmentNames, objMonthlyPlanningStoreObjects.FirstMonth);
		pageMonthlyPlanningPO.CompareValues(extentTest, DBValues, UIValues);

		UITotal = pageMonthlyPlanningPO.GetTotal(extentTest, objMonthlyPlanningStoreObjects.TotalRow,
				objMonthlyPlanningStoreObjects.FirstMonthHistory);
		pageMonthlyPlanningPO.CompareValues(extentTest, DBTotal, UITotal);
	}

	@Test(description = "Monthly Planning - Store, Items history - Second Month", priority = 10)
	public void ItemsHistorySecondMonth() throws Exception {
		pageWeeklyPlanningPO = new WeeklyPlanningStorePage().createPage(getDriver(), getContext(), log, status, data,
				getReport(), getReportLogger(), WeeklyPlanningStorePage.class);
		TestStatus status = getTestStatus();
		System.out.println("Entering into TestCase");
		String Month = pageWeeklyPlanningPO.MonthName(objWeeklyPlanningStoreObjects, extentTest,
				objMonthlyPlanningStoreObjects.SecondMonthNm);

		String ItemHistory = data.getText("ItemHistory");
		DBValues = sql.CLRexecuteQuery(getContext(),
				ItemHistory.replace("store", getContext().getStringProperty("store")).replace("Month", Month).replace("Division", getContext().getStringProperty("Division")));
		System.out.println("DB Value: " + DBValues);

		String ItemHistoryTotal = data.getText("ItemHistoryTotal");
		DBTotal = sql.CLRexecuteQuery(getContext(),
				ItemHistoryTotal.replace("store", getContext().getStringProperty("store")).replace("Month", Month).replace("Division", getContext().getStringProperty("Division")));
		System.out.println("DB Total: " + DBTotal);

		UIValues = pageWeeklyPlanningPO.UIResults(objMonthlyPlanningStoreObjects.MPTable, extentTest,
				objWeeklyPlanningStoreObjects.DepartmentNames, objMonthlyPlanningStoreObjects.SecondMonth);
		pageMonthlyPlanningPO.CompareValues(extentTest, DBValues, UIValues);

		UITotal = pageMonthlyPlanningPO.GetTotal(extentTest, objMonthlyPlanningStoreObjects.TotalRow,
				objMonthlyPlanningStoreObjects.SecondMonthHistory);
		pageMonthlyPlanningPO.CompareValues(extentTest, DBTotal, UITotal);
	}

	@Test(description = "Monthly Planning - Store, Items history - Third Month", priority = 11)
	public void ItemsHistoryThirdMonth() throws Exception {
		pageWeeklyPlanningPO = new WeeklyPlanningStorePage().createPage(getDriver(), getContext(), log, status, data,
				getReport(), getReportLogger(), WeeklyPlanningStorePage.class);
		TestStatus status = getTestStatus();
		System.out.println("Entering into TestCase");
		String Month = pageWeeklyPlanningPO.MonthName(objWeeklyPlanningStoreObjects, extentTest,
				objMonthlyPlanningStoreObjects.ThirdMonthNm);

		String ItemHistory = data.getText("ItemHistory");
		DBValues = sql.CLRexecuteQuery(getContext(),
				ItemHistory.replace("store", getContext().getStringProperty("store")).replace("Month", Month).replace("Division", getContext().getStringProperty("Division")));
		System.out.println("DB Value: " + DBValues);

		String ItemHistoryTotal = data.getText("ItemHistoryTotal");
		DBTotal = sql.CLRexecuteQuery(getContext(),
				ItemHistoryTotal.replace("store", getContext().getStringProperty("store")).replace("Month", Month).replace("Division", getContext().getStringProperty("Division")));
		System.out.println("DB Total: " + DBTotal);

		UIValues = pageWeeklyPlanningPO.UIResults(objMonthlyPlanningStoreObjects.MPTable, extentTest,
				objWeeklyPlanningStoreObjects.DepartmentNames, objMonthlyPlanningStoreObjects.ThirdMonth);
		pageMonthlyPlanningPO.CompareValues(extentTest, DBValues, UIValues);

		UITotal = pageMonthlyPlanningPO.GetTotal(extentTest, objMonthlyPlanningStoreObjects.TotalRow,
				objMonthlyPlanningStoreObjects.ThirdMonthHistory);
		pageMonthlyPlanningPO.CompareValues(extentTest, DBTotal, UITotal);
	}

	@Test(description = "Monthly Planning - Store, Items history - Fourth Month", priority = 12)
	public void ItemsHistoryFourthMonth() throws Exception {
		pageWeeklyPlanningPO = new WeeklyPlanningStorePage().createPage(getDriver(), getContext(), log, status, data,
				getReport(), getReportLogger(), WeeklyPlanningStorePage.class);
		TestStatus status = getTestStatus();
		System.out.println("Entering into TestCase");
		String Month = pageWeeklyPlanningPO.MonthName(objWeeklyPlanningStoreObjects, extentTest,
				objMonthlyPlanningStoreObjects.FourthmonthNm);

		String ItemHistory = data.getText("ItemHistory");
		DBValues = sql.CLRexecuteQuery(getContext(),
				ItemHistory.replace("store", getContext().getStringProperty("store")).replace("Month", Month).replace("Division", getContext().getStringProperty("Division")));
		System.out.println("DB Value: " + DBValues);

		String ItemHistoryTotal = data.getText("ItemHistoryTotal");
		DBTotal = sql.CLRexecuteQuery(getContext(),
				ItemHistoryTotal.replace("store", getContext().getStringProperty("store")).replace("Month", Month).replace("Division", getContext().getStringProperty("Division")));
		System.out.println("DB Total: " + DBTotal);

		UIValues = pageWeeklyPlanningPO.UIResults(objMonthlyPlanningStoreObjects.MPTable, extentTest,
				objWeeklyPlanningStoreObjects.DepartmentNames, objMonthlyPlanningStoreObjects.FourMonth);
		pageMonthlyPlanningPO.CompareValues(extentTest, DBValues, UIValues);

		UITotal = pageMonthlyPlanningPO.GetTotal(extentTest, objMonthlyPlanningStoreObjects.TotalRow,
				objMonthlyPlanningStoreObjects.FourthMonthHistory);
		pageMonthlyPlanningPO.CompareValues(extentTest, DBTotal, UITotal);
	}

	// Customers
	@Test(description = "Monthly Planning - Store, Customers history - First Month", priority = 13)
	public void CustomersHistoryFirsthMonth() throws Exception {
		pageWeeklyPlanningPO = new WeeklyPlanningStorePage().createPage(getDriver(), getContext(), log, status, data,
				getReport(), getReportLogger(), WeeklyPlanningStorePage.class);
		TestStatus status = getTestStatus();
		System.out.println("Entering into TestCase");
		pageWeeklyPlanningPO.SelectHistoryDropdownToView(objWeeklyPlanningStoreObjects, extentTest, "Customers");
		String Month = pageWeeklyPlanningPO.MonthName(objWeeklyPlanningStoreObjects, extentTest,
				objMonthlyPlanningStoreObjects.FirstMonthNm);

		String CustomersHistory = data.getText("CustomersHistory");
		DBValues = sql.CLRexecuteQuery(getContext(),
				CustomersHistory.replace("store", getContext().getStringProperty("store")).replace("Month", Month).replace("Division", getContext().getStringProperty("Division")));
		System.out.println("DB Value: " + DBValues);

		String CustomersHistoryTotal = data.getText("CustomersHistoryTotal");
		DBTotal = sql.CLRexecuteQuery(getContext(), CustomersHistoryTotal
				.replace("store", getContext().getStringProperty("store")).replace("Month", Month).replace("Division", getContext().getStringProperty("Division")));
		System.out.println("DB Total: " + DBTotal);

		UIValues = pageWeeklyPlanningPO.UIResults(objMonthlyPlanningStoreObjects.MPTable, extentTest,
				objWeeklyPlanningStoreObjects.DepartmentNames, objMonthlyPlanningStoreObjects.FirstMonth);
		pageMonthlyPlanningPO.CompareValues(extentTest, DBValues, UIValues);

		UITotal = pageMonthlyPlanningPO.GetTotal(extentTest, objMonthlyPlanningStoreObjects.TotalRow,
				objMonthlyPlanningStoreObjects.FirstMonthHistory);
		pageMonthlyPlanningPO.CompareValues(extentTest, DBTotal, UITotal);
	}

	@Test(description = "Monthly Planning - Store, Customers history - Second Month", priority = 14)
	public void CustomersHistorySecondMonth() throws Exception {
		pageWeeklyPlanningPO = new WeeklyPlanningStorePage().createPage(getDriver(), getContext(), log, status, data,
				getReport(), getReportLogger(), WeeklyPlanningStorePage.class);
		TestStatus status = getTestStatus();
		System.out.println("Entering into TestCase");
		String Month = pageWeeklyPlanningPO.MonthName(objWeeklyPlanningStoreObjects, extentTest,
				objMonthlyPlanningStoreObjects.SecondMonthNm);

		String CustomersHistory = data.getText("CustomersHistory");
		DBValues = sql.CLRexecuteQuery(getContext(),
				CustomersHistory.replace("store", getContext().getStringProperty("store")).replace("Month", Month).replace("Division", getContext().getStringProperty("Division")));
		System.out.println("DB Value: " + DBValues);

		String CustomersHistoryTotal = data.getText("CustomersHistoryTotal");
		DBTotal = sql.CLRexecuteQuery(getContext(), CustomersHistoryTotal
				.replace("store", getContext().getStringProperty("store")).replace("Month", Month).replace("Division", getContext().getStringProperty("Division")));
		System.out.println("DB Total: " + DBTotal);

		UIValues = pageWeeklyPlanningPO.UIResults(objMonthlyPlanningStoreObjects.MPTable, extentTest,
				objWeeklyPlanningStoreObjects.DepartmentNames, objMonthlyPlanningStoreObjects.SecondMonth);
		pageMonthlyPlanningPO.CompareValues(extentTest, DBValues, UIValues);

		UITotal = pageMonthlyPlanningPO.GetTotal(extentTest, objMonthlyPlanningStoreObjects.TotalRow,
				objMonthlyPlanningStoreObjects.SecondMonthHistory);
		pageMonthlyPlanningPO.CompareValues(extentTest, DBTotal, UITotal);
	}

	@Test(description = "Monthly Planning - Store, Customers history - Third Month", priority = 15)
	public void CustomersHistoryThirdMonth() throws Exception {
		pageWeeklyPlanningPO = new WeeklyPlanningStorePage().createPage(getDriver(), getContext(), log, status, data,
				getReport(), getReportLogger(), WeeklyPlanningStorePage.class);
		TestStatus status = getTestStatus();
		System.out.println("Entering into TestCase");
		String Month = pageWeeklyPlanningPO.MonthName(objWeeklyPlanningStoreObjects, extentTest,
				objMonthlyPlanningStoreObjects.ThirdMonthNm);

		String CustomersHistory = data.getText("CustomersHistory");
		DBValues = sql.CLRexecuteQuery(getContext(),
				CustomersHistory.replace("store", getContext().getStringProperty("store")).replace("Month", Month).replace("Division", getContext().getStringProperty("Division")));
		System.out.println("DB Value: " + DBValues);

		String CustomersHistoryTotal = data.getText("CustomersHistoryTotal");
		DBTotal = sql.CLRexecuteQuery(getContext(), CustomersHistoryTotal
				.replace("store", getContext().getStringProperty("store")).replace("Month", Month).replace("Division", getContext().getStringProperty("Division")));
		System.out.println("DB Total: " + DBTotal);

		UIValues = pageWeeklyPlanningPO.UIResults(objMonthlyPlanningStoreObjects.MPTable, extentTest,
				objWeeklyPlanningStoreObjects.DepartmentNames, objMonthlyPlanningStoreObjects.ThirdMonth);
		pageMonthlyPlanningPO.CompareValues(extentTest, DBValues, UIValues);

		UITotal = pageMonthlyPlanningPO.GetTotal(extentTest, objMonthlyPlanningStoreObjects.TotalRow,
				objMonthlyPlanningStoreObjects.ThirdMonthHistory);
		pageMonthlyPlanningPO.CompareValues(extentTest, DBTotal, UITotal);
	}

	@Test(description = "Monthly Planning - Store, Customers history - Fourth Month", priority = 16)
	public void CustomersHistoryFourthMonth() throws Exception {
		pageWeeklyPlanningPO = new WeeklyPlanningStorePage().createPage(getDriver(), getContext(), log, status, data,
				getReport(), getReportLogger(), WeeklyPlanningStorePage.class);
		TestStatus status = getTestStatus();
		System.out.println("Entering into TestCase");
		String Month = pageWeeklyPlanningPO.MonthName(objWeeklyPlanningStoreObjects, extentTest,
				objMonthlyPlanningStoreObjects.FourthmonthNm);

		String CustomersHistory = data.getText("CustomersHistory");
		DBValues = sql.CLRexecuteQuery(getContext(),
				CustomersHistory.replace("store", getContext().getStringProperty("store")).replace("Month", Month).replace("Division", getContext().getStringProperty("Division")));
		System.out.println("DB Value: " + DBValues);

		String CustomersHistoryTotal = data.getText("CustomersHistoryTotal");
		DBTotal = sql.CLRexecuteQuery(getContext(), CustomersHistoryTotal
				.replace("store", getContext().getStringProperty("store")).replace("Month", Month).replace("Division", getContext().getStringProperty("Division")));
		System.out.println("DB Total: " + DBTotal);

		UIValues = pageWeeklyPlanningPO.UIResults(objMonthlyPlanningStoreObjects.MPTable, extentTest,
				objWeeklyPlanningStoreObjects.DepartmentNames, objMonthlyPlanningStoreObjects.FourMonth);
		pageMonthlyPlanningPO.CompareValues(extentTest, DBValues, UIValues);

		UITotal = pageMonthlyPlanningPO.GetTotal(extentTest, objMonthlyPlanningStoreObjects.TotalRow,
				objMonthlyPlanningStoreObjects.FourthMonthHistory);
		pageMonthlyPlanningPO.CompareValues(extentTest, DBTotal, UITotal);
	}

	// Item Price
	@Test(description = "Monthly Planning - Store, Item Price history - First Month", priority = 17)
	public void ItemPriceHistoryFirsthMonth() throws Exception {
		pageWeeklyPlanningPO = new WeeklyPlanningStorePage().createPage(getDriver(), getContext(), log, status, data,
				getReport(), getReportLogger(), WeeklyPlanningStorePage.class);
		TestStatus status = getTestStatus();
		System.out.println("Entering into TestCase");
		pageWeeklyPlanningPO.SelectHistoryDropdownToView(objWeeklyPlanningStoreObjects, extentTest, "Item Price");
		String Month = pageWeeklyPlanningPO.MonthName(objWeeklyPlanningStoreObjects, extentTest,
				objMonthlyPlanningStoreObjects.FirstMonthNm);

		String ItemPriceHistory = data.getText("ItemPriceHistory");
		DBValues = sql.CLRexecuteQuery(getContext(),
				ItemPriceHistory.replace("store", getContext().getStringProperty("store")).replace("Month", Month).replace("Division", getContext().getStringProperty("Division")));
		System.out.println("DB Value: " + DBValues);

		String ItemPriceHistoryTotal = data.getText("ItemPriceHistoryTotal");
		DBTotal = sql.CLRexecuteQuery(getContext(), ItemPriceHistoryTotal
				.replace("store", getContext().getStringProperty("store")).replace("Month", Month).replace("Division", getContext().getStringProperty("Division")));
		System.out.println("DB Total: " + DBTotal);

		UIValues = pageWeeklyPlanningPO.UIResults(objMonthlyPlanningStoreObjects.MPTable, extentTest,
				objWeeklyPlanningStoreObjects.DepartmentNames, objMonthlyPlanningStoreObjects.FirstMonth);
		pageMonthlyPlanningPO.CompareValueDecimalFields(extentTest, DBValues, UIValues);

		UITotal = pageMonthlyPlanningPO.GetTotal(extentTest, objMonthlyPlanningStoreObjects.TotalRow,
				objMonthlyPlanningStoreObjects.FirstMonthHistory);
		pageMonthlyPlanningPO.CompareValueDecimalFields(extentTest, DBTotal, UITotal);
	}

	@Test(description = "Monthly Planning - Store, Item Price history - Second Month", priority = 18)
	public void ItemPriceHistorySecondMonth() throws Exception {
		pageWeeklyPlanningPO = new WeeklyPlanningStorePage().createPage(getDriver(), getContext(), log, status, data,
				getReport(), getReportLogger(), WeeklyPlanningStorePage.class);
		TestStatus status = getTestStatus();
		System.out.println("Entering into TestCase");
		String Month = pageWeeklyPlanningPO.MonthName(objWeeklyPlanningStoreObjects, extentTest,
				objMonthlyPlanningStoreObjects.SecondMonthNm);

		String ItemPriceHistory = data.getText("ItemPriceHistory");
		DBValues = sql.CLRexecuteQuery(getContext(),
				ItemPriceHistory.replace("store", getContext().getStringProperty("store")).replace("Month", Month).replace("Division", getContext().getStringProperty("Division")));
		System.out.println("DB Value: " + DBValues);

		String ItemPriceHistoryTotal = data.getText("ItemPriceHistoryTotal");
		DBTotal = sql.CLRexecuteQuery(getContext(), ItemPriceHistoryTotal
				.replace("store", getContext().getStringProperty("store")).replace("Month", Month).replace("Division", getContext().getStringProperty("Division")));
		System.out.println("DB Total: " + DBTotal);

		UIValues = pageWeeklyPlanningPO.UIResults(objMonthlyPlanningStoreObjects.MPTable, extentTest,
				objWeeklyPlanningStoreObjects.DepartmentNames, objMonthlyPlanningStoreObjects.SecondMonth);
		pageMonthlyPlanningPO.CompareValueDecimalFields(extentTest, DBValues, UIValues);

		UITotal = pageMonthlyPlanningPO.GetTotal(extentTest, objMonthlyPlanningStoreObjects.TotalRow,
				objMonthlyPlanningStoreObjects.SecondMonthHistory);
		pageMonthlyPlanningPO.CompareValueDecimalFields(extentTest, DBTotal, UITotal);
	}

	@Test(description = "Monthly Planning - Store, Item Price history - Third Month", priority = 19)
	public void ItemPriceHistoryThirdMonth() throws Exception {
		pageWeeklyPlanningPO = new WeeklyPlanningStorePage().createPage(getDriver(), getContext(), log, status, data,
				getReport(), getReportLogger(), WeeklyPlanningStorePage.class);
		TestStatus status = getTestStatus();
		System.out.println("Entering into TestCase");
		String Month = pageWeeklyPlanningPO.MonthName(objWeeklyPlanningStoreObjects, extentTest,
				objMonthlyPlanningStoreObjects.ThirdMonthNm);

		String ItemPriceHistory = data.getText("ItemPriceHistory");
		DBValues = sql.CLRexecuteQuery(getContext(),
				ItemPriceHistory.replace("store", getContext().getStringProperty("store")).replace("Month", Month).replace("Division", getContext().getStringProperty("Division")));
		System.out.println("DB Value: " + DBValues);

		String ItemPriceHistoryTotal = data.getText("ItemPriceHistoryTotal");
		DBTotal = sql.CLRexecuteQuery(getContext(), ItemPriceHistoryTotal
				.replace("store", getContext().getStringProperty("store")).replace("Month", Month).replace("Division", getContext().getStringProperty("Division")));
		System.out.println("DB Total: " + DBTotal);

		UIValues = pageWeeklyPlanningPO.UIResults(objMonthlyPlanningStoreObjects.MPTable, extentTest,
				objWeeklyPlanningStoreObjects.DepartmentNames, objMonthlyPlanningStoreObjects.ThirdMonth);
		pageMonthlyPlanningPO.CompareValueDecimalFields(extentTest, DBValues, UIValues);

		UITotal = pageMonthlyPlanningPO.GetTotal(extentTest, objMonthlyPlanningStoreObjects.TotalRow,
				objMonthlyPlanningStoreObjects.ThirdMonthHistory);
		pageMonthlyPlanningPO.CompareValueDecimalFields(extentTest, DBTotal, UITotal);
	}

	@Test(description = "Monthly Planning - Store, Item Price history - Fourth Month", priority = 20)
	public void ItemPriceHistoryFourthMonth() throws Exception {
		pageWeeklyPlanningPO = new WeeklyPlanningStorePage().createPage(getDriver(), getContext(), log, status, data,
				getReport(), getReportLogger(), WeeklyPlanningStorePage.class);
		TestStatus status = getTestStatus();
		System.out.println("Entering into TestCase");
		String Month = pageWeeklyPlanningPO.MonthName(objWeeklyPlanningStoreObjects, extentTest,
				objMonthlyPlanningStoreObjects.FourthmonthNm);

		String ItemPriceHistory = data.getText("ItemPriceHistory");
		DBValues = sql.CLRexecuteQuery(getContext(),
				ItemPriceHistory.replace("store", getContext().getStringProperty("store")).replace("Month", Month).replace("Division", getContext().getStringProperty("Division")));
		System.out.println("DB Value: " + DBValues);

		String ItemPriceHistoryTotal = data.getText("ItemPriceHistoryTotal");
		DBTotal = sql.CLRexecuteQuery(getContext(), ItemPriceHistoryTotal
				.replace("store", getContext().getStringProperty("store")).replace("Month", Month).replace("Division", getContext().getStringProperty("Division")));
		System.out.println("DB Total: " + DBTotal);

		UIValues = pageWeeklyPlanningPO.UIResults(objMonthlyPlanningStoreObjects.MPTable, extentTest,
				objWeeklyPlanningStoreObjects.DepartmentNames, objMonthlyPlanningStoreObjects.FourMonth);
		pageMonthlyPlanningPO.CompareValueDecimalFields(extentTest, DBValues, UIValues);

		UITotal = pageMonthlyPlanningPO.GetTotal(extentTest, objMonthlyPlanningStoreObjects.TotalRow,
				objMonthlyPlanningStoreObjects.FourthMonthHistory);
		pageMonthlyPlanningPO.CompareValueDecimalFields(extentTest, DBTotal, UITotal);
	}

	// Wage Percentage
	@Test(description = "Monthly Planning - Store, WagePercentage history - First Month", priority = 21)
	public void WagePercentageHistoryFirsthMonth() throws Exception {
		pageWeeklyPlanningPO = new WeeklyPlanningStorePage().createPage(getDriver(), getContext(), log, status, data,
				getReport(), getReportLogger(), WeeklyPlanningStorePage.class);
		TestStatus status = getTestStatus();
		System.out.println("Entering into TestCase");
		pageWeeklyPlanningPO.SelectHistoryDropdownToView(objWeeklyPlanningStoreObjects, extentTest, "Wage %");
		String Month = pageWeeklyPlanningPO.MonthName(objWeeklyPlanningStoreObjects, extentTest,
				objMonthlyPlanningStoreObjects.FirstMonthNm);

		String WagePercentageHistory = data.getText("WagePercentageHistory");
		DBValues = sql.CLRexecuteQuery(getContext(), WagePercentageHistory
				.replace("store", getContext().getStringProperty("store")).replace("Month", Month).replace("Division", getContext().getStringProperty("Division")));
		System.out.println("DB Value: " + DBValues);

		String WagePercentageHistoryTotal = data.getText("WagePercentageHistoryTotal");
		DBTotal = sql.CLRexecuteQuery(getContext(), WagePercentageHistoryTotal
				.replace("store", getContext().getStringProperty("store")).replace("Month", Month).replace("Division", getContext().getStringProperty("Division")));
		System.out.println("DB Total: " + DBTotal);

		UIValues = pageWeeklyPlanningPO.UIResults(objMonthlyPlanningStoreObjects.MPTable, extentTest,
				objWeeklyPlanningStoreObjects.DepartmentNames, objMonthlyPlanningStoreObjects.FirstMonth);
		pageMonthlyPlanningPO.CompareValueDecimalFields(extentTest, DBValues, UIValues);

		UITotal = pageMonthlyPlanningPO.GetTotal(extentTest, objMonthlyPlanningStoreObjects.TotalRow,
				objMonthlyPlanningStoreObjects.FirstMonthHistory);
		pageMonthlyPlanningPO.CompareValueDecimalFields(extentTest, DBTotal, UITotal);
	}

	@Test(description = "Monthly Planning - Store, WagePercentage history - Second Month", priority = 22)
	public void WagePercentageHistorySecondMonth() throws Exception {
		pageWeeklyPlanningPO = new WeeklyPlanningStorePage().createPage(getDriver(), getContext(), log, status, data,
				getReport(), getReportLogger(), WeeklyPlanningStorePage.class);
		TestStatus status = getTestStatus();
		System.out.println("Entering into TestCase");
		String Month = pageWeeklyPlanningPO.MonthName(objWeeklyPlanningStoreObjects, extentTest,
				objMonthlyPlanningStoreObjects.SecondMonthNm);

		String WagePercentageHistory = data.getText("WagePercentageHistory");
		DBValues = sql.CLRexecuteQuery(getContext(), WagePercentageHistory
				.replace("store", getContext().getStringProperty("store")).replace("Month", Month).replace("Division", getContext().getStringProperty("Division")));
		System.out.println("DB Value: " + DBValues);

		String WagePercentageHistoryTotal = data.getText("WagePercentageHistoryTotal");
		DBTotal = sql.CLRexecuteQuery(getContext(), WagePercentageHistoryTotal
				.replace("store", getContext().getStringProperty("store")).replace("Month", Month).replace("Division", getContext().getStringProperty("Division")));
		System.out.println("DB Total: " + DBTotal);

		UIValues = pageWeeklyPlanningPO.UIResults(objMonthlyPlanningStoreObjects.MPTable, extentTest,
				objWeeklyPlanningStoreObjects.DepartmentNames, objMonthlyPlanningStoreObjects.SecondMonth);
		pageMonthlyPlanningPO.CompareValueDecimalFields(extentTest, DBValues, UIValues);

		UITotal = pageMonthlyPlanningPO.GetTotal(extentTest, objMonthlyPlanningStoreObjects.TotalRow,
				objMonthlyPlanningStoreObjects.SecondMonthHistory);
		pageMonthlyPlanningPO.CompareValueDecimalFields(extentTest, DBTotal, UITotal);
	}

	@Test(description = "Monthly Planning - Store, WagePercentage history - Third Month", priority = 23)
	public void WagePercentageHistoryThirdMonth() throws Exception {
		pageWeeklyPlanningPO = new WeeklyPlanningStorePage().createPage(getDriver(), getContext(), log, status, data,
				getReport(), getReportLogger(), WeeklyPlanningStorePage.class);
		TestStatus status = getTestStatus();
		System.out.println("Entering into TestCase");
		String Month = pageWeeklyPlanningPO.MonthName(objWeeklyPlanningStoreObjects, extentTest,
				objMonthlyPlanningStoreObjects.ThirdMonthNm);

		String WagePercentageHistory = data.getText("WagePercentageHistory");
		DBValues = sql.CLRexecuteQuery(getContext(), WagePercentageHistory
				.replace("store", getContext().getStringProperty("store")).replace("Month", Month).replace("Division", getContext().getStringProperty("Division")));
		System.out.println("DB Value: " + DBValues);

		String WagePercentageHistoryTotal = data.getText("WagePercentageHistoryTotal");
		DBTotal = sql.CLRexecuteQuery(getContext(), WagePercentageHistoryTotal
				.replace("store", getContext().getStringProperty("store")).replace("Month", Month).replace("Division", getContext().getStringProperty("Division")));
		System.out.println("DB Total: " + DBTotal);

		UIValues = pageWeeklyPlanningPO.UIResults(objMonthlyPlanningStoreObjects.MPTable, extentTest,
				objWeeklyPlanningStoreObjects.DepartmentNames, objMonthlyPlanningStoreObjects.ThirdMonth);
		pageMonthlyPlanningPO.CompareValueDecimalFields(extentTest, DBValues, UIValues);

		UITotal = pageMonthlyPlanningPO.GetTotal(extentTest, objMonthlyPlanningStoreObjects.TotalRow,
				objMonthlyPlanningStoreObjects.ThirdMonthHistory);
		pageMonthlyPlanningPO.CompareValueDecimalFields(extentTest, DBTotal, UITotal);
	}

	@Test(description = "Monthly Planning - Store, WagePercentage history - Fourth Month", priority = 24)
	public void WagePercentageHistoryFourthMonth() throws Exception {
		pageWeeklyPlanningPO = new WeeklyPlanningStorePage().createPage(getDriver(), getContext(), log, status, data,
				getReport(), getReportLogger(), WeeklyPlanningStorePage.class);
		TestStatus status = getTestStatus();
		System.out.println("Entering into TestCase");
		String Month = pageWeeklyPlanningPO.MonthName(objWeeklyPlanningStoreObjects, extentTest,
				objMonthlyPlanningStoreObjects.FourthmonthNm);

		String WagePercentageHistory = data.getText("WagePercentageHistory");
		DBValues = sql.CLRexecuteQuery(getContext(), WagePercentageHistory
				.replace("store", getContext().getStringProperty("store")).replace("Month", Month).replace("Division", getContext().getStringProperty("Division")));
		System.out.println("DB Value: " + DBValues);

		String WagePercentageHistoryTotal = data.getText("WagePercentageHistoryTotal");
		DBTotal = sql.CLRexecuteQuery(getContext(), WagePercentageHistoryTotal
				.replace("store", getContext().getStringProperty("store")).replace("Month", Month).replace("Division", getContext().getStringProperty("Division")));
		System.out.println("DB Total: " + DBTotal);

		UIValues = pageWeeklyPlanningPO.UIResults(objMonthlyPlanningStoreObjects.MPTable, extentTest,
				objWeeklyPlanningStoreObjects.DepartmentNames, objMonthlyPlanningStoreObjects.FourMonth);
		pageMonthlyPlanningPO.CompareValueDecimalFields(extentTest, DBValues, UIValues);

		UITotal = pageMonthlyPlanningPO.GetTotal(extentTest, objMonthlyPlanningStoreObjects.TotalRow,
				objMonthlyPlanningStoreObjects.FourthMonthHistory);
		pageMonthlyPlanningPO.CompareValueDecimalFields(extentTest, DBTotal, UITotal);
	}

	// Paid Hours
	@Test(description = "Monthly Planning - Store, Paid Hours history - First Month", priority = 25)
	public void PaidHoursHistoryFirsthMonth() throws Exception {
		pageWeeklyPlanningPO = new WeeklyPlanningStorePage().createPage(getDriver(), getContext(), log, status, data,
				getReport(), getReportLogger(), WeeklyPlanningStorePage.class);
		TestStatus status = getTestStatus();
		System.out.println("Entering into TestCase");
		pageWeeklyPlanningPO.SelectHistoryDropdownToView(objWeeklyPlanningStoreObjects, extentTest, "Paid Hours");
		String Month = pageWeeklyPlanningPO.MonthName(objWeeklyPlanningStoreObjects, extentTest,
				objMonthlyPlanningStoreObjects.FirstMonthNm);

		String PaidHoursHistory = data.getText("PaidHoursHistory");
		DBValues = sql.CLRexecuteQuery(getContext(),
				PaidHoursHistory.replace("store", getContext().getStringProperty("store")).replace("Month", Month).replace("Division", getContext().getStringProperty("Division")));
		System.out.println("DB Value: " + DBValues);

		String PaidHoursHistoryTotal = data.getText("PaidHoursHistoryTotal");
		DBTotal = sql.CLRexecuteQuery(getContext(), PaidHoursHistoryTotal
				.replace("store", getContext().getStringProperty("store")).replace("Month", Month).replace("Division", getContext().getStringProperty("Division")));
		System.out.println("DB Total: " + DBTotal);

		UIValues = pageWeeklyPlanningPO.UIResults(objMonthlyPlanningStoreObjects.MPTable, extentTest,
				objWeeklyPlanningStoreObjects.DepartmentNames, objMonthlyPlanningStoreObjects.FirstMonth);
		pageMonthlyPlanningPO.CompareValueDecimalFields(extentTest, DBValues, UIValues);

		UITotal = pageMonthlyPlanningPO.GetTotal(extentTest, objMonthlyPlanningStoreObjects.TotalRow,
				objMonthlyPlanningStoreObjects.FirstMonthHistory);
		pageMonthlyPlanningPO.CompareValueDecimalFields(extentTest, DBTotal, UITotal);
	}

	@Test(description = "Monthly Planning - Store, Paid Hours history - Second Month", priority = 26)
	public void PaidHoursHistorySecondMonth() throws Exception {
		pageWeeklyPlanningPO = new WeeklyPlanningStorePage().createPage(getDriver(), getContext(), log, status, data,
				getReport(), getReportLogger(), WeeklyPlanningStorePage.class);
		TestStatus status = getTestStatus();
		System.out.println("Entering into TestCase");
		String Month = pageWeeklyPlanningPO.MonthName(objWeeklyPlanningStoreObjects, extentTest,
				objMonthlyPlanningStoreObjects.SecondMonthNm);

		String PaidHoursHistory = data.getText("PaidHoursHistory");
		DBValues = sql.CLRexecuteQuery(getContext(),
				PaidHoursHistory.replace("store", getContext().getStringProperty("store")).replace("Month", Month).replace("Division", getContext().getStringProperty("Division")));
		System.out.println("DB Value: " + DBValues);

		String PaidHoursHistoryTotal = data.getText("PaidHoursHistoryTotal");
		DBTotal = sql.CLRexecuteQuery(getContext(), PaidHoursHistoryTotal
				.replace("store", getContext().getStringProperty("store")).replace("Month", Month).replace("Division", getContext().getStringProperty("Division")));
		System.out.println("DB Total: " + DBTotal);

		UIValues = pageWeeklyPlanningPO.UIResults(objMonthlyPlanningStoreObjects.MPTable, extentTest,
				objWeeklyPlanningStoreObjects.DepartmentNames, objMonthlyPlanningStoreObjects.SecondMonth);
		pageMonthlyPlanningPO.CompareValueDecimalFields(extentTest, DBValues, UIValues);

		UITotal = pageMonthlyPlanningPO.GetTotal(extentTest, objMonthlyPlanningStoreObjects.TotalRow,
				objMonthlyPlanningStoreObjects.SecondMonthHistory);
		pageMonthlyPlanningPO.CompareValueDecimalFields(extentTest, DBTotal, UITotal);
	}

	@Test(description = "Monthly Planning - Store, Paid Hours history - Third Month", priority = 27)
	public void PaidHoursHistoryThirdMonth() throws Exception {
		pageWeeklyPlanningPO = new WeeklyPlanningStorePage().createPage(getDriver(), getContext(), log, status, data,
				getReport(), getReportLogger(), WeeklyPlanningStorePage.class);
		TestStatus status = getTestStatus();
		System.out.println("Entering into TestCase");
		String Month = pageWeeklyPlanningPO.MonthName(objWeeklyPlanningStoreObjects, extentTest,
				objMonthlyPlanningStoreObjects.ThirdMonthNm);

		String PaidHoursHistory = data.getText("PaidHoursHistory");
		DBValues = sql.CLRexecuteQuery(getContext(),
				PaidHoursHistory.replace("store", getContext().getStringProperty("store")).replace("Month", Month).replace("Division", getContext().getStringProperty("Division")));
		System.out.println("DB Value: " + DBValues);

		String PaidHoursHistoryTotal = data.getText("PaidHoursHistoryTotal");
		DBTotal = sql.CLRexecuteQuery(getContext(), PaidHoursHistoryTotal
				.replace("store", getContext().getStringProperty("store")).replace("Month", Month).replace("Division", getContext().getStringProperty("Division")));
		System.out.println("DB Total: " + DBTotal);

		UIValues = pageWeeklyPlanningPO.UIResults(objMonthlyPlanningStoreObjects.MPTable, extentTest,
				objWeeklyPlanningStoreObjects.DepartmentNames, objMonthlyPlanningStoreObjects.ThirdMonth);
		pageMonthlyPlanningPO.CompareValueDecimalFields(extentTest, DBValues, UIValues);

		UITotal = pageMonthlyPlanningPO.GetTotal(extentTest, objMonthlyPlanningStoreObjects.TotalRow,
				objMonthlyPlanningStoreObjects.ThirdMonthHistory);
		pageMonthlyPlanningPO.CompareValueDecimalFields(extentTest, DBTotal, UITotal);
	}

	@Test(description = "Monthly Planning - Store, Paid Hours history - Fourth Month", priority = 28)
	public void PaidHoursHistoryFourthMonth() throws Exception {
		pageWeeklyPlanningPO = new WeeklyPlanningStorePage().createPage(getDriver(), getContext(), log, status, data,
				getReport(), getReportLogger(), WeeklyPlanningStorePage.class);
		TestStatus status = getTestStatus();
		System.out.println("Entering into TestCase");
		String Month = pageWeeklyPlanningPO.MonthName(objWeeklyPlanningStoreObjects, extentTest,
				objMonthlyPlanningStoreObjects.FourthmonthNm);

		String PaidHoursHistory = data.getText("PaidHoursHistory");
		DBValues = sql.CLRexecuteQuery(getContext(),
				PaidHoursHistory.replace("store", getContext().getStringProperty("store")).replace("Month", Month).replace("Division", getContext().getStringProperty("Division")));
		System.out.println("DB Value: " + DBValues);

		String PaidHoursHistoryTotal = data.getText("PaidHoursHistoryTotal");
		DBTotal = sql.CLRexecuteQuery(getContext(), PaidHoursHistoryTotal
				.replace("store", getContext().getStringProperty("store")).replace("Month", Month).replace("Division", getContext().getStringProperty("Division")));
		System.out.println("DB Total: " + DBTotal);

		UIValues = pageWeeklyPlanningPO.UIResults(objMonthlyPlanningStoreObjects.MPTable, extentTest,
				objWeeklyPlanningStoreObjects.DepartmentNames, objMonthlyPlanningStoreObjects.FourMonth);
		pageMonthlyPlanningPO.CompareValueDecimalFields(extentTest, DBValues, UIValues);

		UITotal = pageMonthlyPlanningPO.GetTotal(extentTest, objMonthlyPlanningStoreObjects.TotalRow,
				objMonthlyPlanningStoreObjects.FourthMonthHistory);
		pageMonthlyPlanningPO.CompareValueDecimalFields(extentTest, DBTotal, UITotal);
	}

	@Test(description = "Monthly Planning - Store, CPH history - First Month", priority = 29)
	public void CPHFirsthMonth() throws Exception {
		pageWeeklyPlanningPO = new WeeklyPlanningStorePage().createPage(getDriver(), getContext(), log, status, data,
				getReport(), getReportLogger(), WeeklyPlanningStorePage.class);
		TestStatus status = getTestStatus();
		System.out.println("Entering into TestCase");
		pageWeeklyPlanningPO.SelectHistoryDropdownToView(objWeeklyPlanningStoreObjects, extentTest, "CPH");
		String Month = pageWeeklyPlanningPO.MonthName(objWeeklyPlanningStoreObjects, extentTest,
				objMonthlyPlanningStoreObjects.FirstMonthNm);

		String CPHHistory = data.getText("CPHHistory");
		DBValues = sql.CLRexecuteQuery(getContext(),
				CPHHistory.replace("store", getContext().getStringProperty("store")).replace("Month", Month).replace("Division", getContext().getStringProperty("Division")));
		System.out.println("DB Value: " + DBValues);

		String CPHHistoryTotal = data.getText("CPHHistoryTotal");
		DBTotal = sql.CLRexecuteQuery(getContext(),
				CPHHistoryTotal.replace("store", getContext().getStringProperty("store")).replace("Month", Month).replace("Division", getContext().getStringProperty("Division")));
		System.out.println("DB Total: " + DBTotal);

		UIValues = pageWeeklyPlanningPO.UIResults(objMonthlyPlanningStoreObjects.MPTable, extentTest,
				objWeeklyPlanningStoreObjects.DepartmentNames, objMonthlyPlanningStoreObjects.FirstMonth);
		pageMonthlyPlanningPO.CompareValueDecimalFields(extentTest, DBValues, UIValues);

		UITotal = pageMonthlyPlanningPO.GetTotal(extentTest, objMonthlyPlanningStoreObjects.TotalRow,
				objMonthlyPlanningStoreObjects.FirstMonthHistory);
		pageMonthlyPlanningPO.CompareValueDecimalFields(extentTest, DBTotal, UITotal);
	}

	@Test(description = "Monthly Planning - Store, CPH history - Second Month", priority = 30)
	public void CPHHistorySecondMonth() throws Exception {
		pageWeeklyPlanningPO = new WeeklyPlanningStorePage().createPage(getDriver(), getContext(), log, status, data,
				getReport(), getReportLogger(), WeeklyPlanningStorePage.class);
		TestStatus status = getTestStatus();
		System.out.println("Entering into TestCase");
		String Month = pageWeeklyPlanningPO.MonthName(objWeeklyPlanningStoreObjects, extentTest,
				objMonthlyPlanningStoreObjects.SecondMonthNm);

		String CPHHistory = data.getText("CPHHistory");
		DBValues = sql.CLRexecuteQuery(getContext(),
				CPHHistory.replace("store", getContext().getStringProperty("store")).replace("Month", Month).replace("Division", getContext().getStringProperty("Division")));
		System.out.println("DB Value: " + DBValues);

		String CPHHistoryTotal = data.getText("CPHHistoryTotal");
		DBTotal = sql.CLRexecuteQuery(getContext(),
				CPHHistoryTotal.replace("store", getContext().getStringProperty("store")).replace("Month", Month).replace("Division", getContext().getStringProperty("Division")));
		System.out.println("DB Total: " + DBTotal);

		UIValues = pageWeeklyPlanningPO.UIResults(objMonthlyPlanningStoreObjects.MPTable, extentTest,
				objWeeklyPlanningStoreObjects.DepartmentNames, objMonthlyPlanningStoreObjects.SecondMonth);
		pageMonthlyPlanningPO.CompareValueDecimalFields(extentTest, DBValues, UIValues);

		UITotal = pageMonthlyPlanningPO.GetTotal(extentTest, objMonthlyPlanningStoreObjects.TotalRow,
				objMonthlyPlanningStoreObjects.SecondMonthHistory);
		pageMonthlyPlanningPO.CompareValueDecimalFields(extentTest, DBTotal, UITotal);
	}

	@Test(description = "Monthly Planning - Store, CPH history - Third Month", priority = 31)
	public void CPHHistoryThirdMonth() throws Exception {
		pageWeeklyPlanningPO = new WeeklyPlanningStorePage().createPage(getDriver(), getContext(), log, status, data,
				getReport(), getReportLogger(), WeeklyPlanningStorePage.class);
		TestStatus status = getTestStatus();
		System.out.println("Entering into TestCase");
		String Month = pageWeeklyPlanningPO.MonthName(objWeeklyPlanningStoreObjects, extentTest,
				objMonthlyPlanningStoreObjects.ThirdMonthNm);

		String CPHHistory = data.getText("CPHHistory");
		DBValues = sql.CLRexecuteQuery(getContext(),
				CPHHistory.replace("store", getContext().getStringProperty("store")).replace("Month", Month).replace("Division", getContext().getStringProperty("Division")));
		System.out.println("DB Value: " + DBValues);

		String CPHHistoryTotal = data.getText("CPHHistoryTotal");
		DBTotal = sql.CLRexecuteQuery(getContext(),
				CPHHistoryTotal.replace("store", getContext().getStringProperty("store")).replace("Month", Month).replace("Division", getContext().getStringProperty("Division")));
		System.out.println("DB Total: " + DBTotal);

		UIValues = pageWeeklyPlanningPO.UIResults(objMonthlyPlanningStoreObjects.MPTable, extentTest,
				objWeeklyPlanningStoreObjects.DepartmentNames, objMonthlyPlanningStoreObjects.ThirdMonth);
		pageMonthlyPlanningPO.CompareValueDecimalFields(extentTest, DBValues, UIValues);

		UITotal = pageMonthlyPlanningPO.GetTotal(extentTest, objMonthlyPlanningStoreObjects.TotalRow,
				objMonthlyPlanningStoreObjects.ThirdMonthHistory);
		pageMonthlyPlanningPO.CompareValueDecimalFields(extentTest, DBTotal, UITotal);
	}

	@Test(description = "Monthly Planning - Store, CPH history - Fourth Month", priority = 32)
	public void CPHHistoryFourthMonth() throws Exception {
		pageWeeklyPlanningPO = new WeeklyPlanningStorePage().createPage(getDriver(), getContext(), log, status, data,
				getReport(), getReportLogger(), WeeklyPlanningStorePage.class);
		TestStatus status = getTestStatus();
		System.out.println("Entering into TestCase");
		String Month = pageWeeklyPlanningPO.MonthName(objWeeklyPlanningStoreObjects, extentTest,
				objMonthlyPlanningStoreObjects.FourthmonthNm);

		String CPHHistory = data.getText("CPHHistory");
		DBValues = sql.CLRexecuteQuery(getContext(),
				CPHHistory.replace("store", getContext().getStringProperty("store")).replace("Month", Month).replace("Division", getContext().getStringProperty("Division")));
		System.out.println("DB Value: " + DBValues);

		String CPHHistoryTotal = data.getText("CPHHistoryTotal");
		DBTotal = sql.CLRexecuteQuery(getContext(),
				CPHHistoryTotal.replace("store", getContext().getStringProperty("store")).replace("Month", Month).replace("Division", getContext().getStringProperty("Division")));
		System.out.println("DB Total: " + DBTotal);

		UIValues = pageWeeklyPlanningPO.UIResults(objMonthlyPlanningStoreObjects.MPTable, extentTest,
				objWeeklyPlanningStoreObjects.DepartmentNames, objMonthlyPlanningStoreObjects.FourMonth);
		pageMonthlyPlanningPO.CompareValueDecimalFields(extentTest, DBValues, UIValues);

		UITotal = pageMonthlyPlanningPO.GetTotal(extentTest, objMonthlyPlanningStoreObjects.TotalRow,
				objMonthlyPlanningStoreObjects.FourthMonthHistory);
		pageMonthlyPlanningPO.CompareValueDecimalFields(extentTest, DBTotal, UITotal);
	}

	////////////////////////// CURRENT MONTH ///////////////////////////////
	@Test(description = "Monthly Planning - Store, Budget Sales - Current Month", priority = 37)
	public void BudgetSales_CurrentMonth() throws Exception {
		pageWeeklyPlanningPO = new WeeklyPlanningStorePage().createPage(getDriver(), getContext(), log, status, data,
				getReport(), getReportLogger(), WeeklyPlanningStorePage.class);
		pageMonthlyPlanningPO = new MonthlyPlanningStorePage().createPage(getDriver(), getContext(), log, status, data,
				getReport(), getReportLogger(), MonthlyPlanningStorePage.class);
		TestStatus status = getTestStatus();
		OptionToView = getContext().getStringProperty("OptionToView");
		Month = pageWeeklyPlanningPO.MonthName(objWeeklyPlanningStoreObjects, extentTest,
				objMonthlyPlanningStoreObjects.CurrentMonth);
		String MonthName = Month.concat(getContext().getStringProperty("Month"));

		if (OptionToView.contains("Budget")) {
			String BudgetSales = data.getText("BudgetSales");
			DBValues = sql.CLRexecuteQuery(getContext(),
					BudgetSales.replace("store", getContext().getStringProperty("store")).replace("Month", MonthName)
					.replace("Division", getContext().getStringProperty("Division")));
			System.out.println("DB Value: " + DBValues);

			String BudgetSalesTotal = data.getText("BudgetSalesTotal");
			DBTotal = sql.CLRexecuteQuery(getContext(), BudgetSalesTotal
					.replace("store", getContext().getStringProperty("store")).replace("Month", MonthName)
					.replace("Division", getContext().getStringProperty("Division")));
			System.out.println("DB Total: " + DBTotal);

			UIValues = pageWeeklyPlanningPO.UIResults(objMonthlyPlanningStoreObjects.MPTable, extentTest,
					objWeeklyPlanningStoreObjects.DepartmentNames, objMonthlyPlanningStoreObjects.BudgetSales);
			pageMonthlyPlanningPO.CompareValues(extentTest, DBValues, UIValues);
			UITotal = pageMonthlyPlanningPO.GetTotal(extentTest, objMonthlyPlanningStoreObjects.TotalRow,
					objMonthlyPlanningStoreObjects.BudgetSalesTotal);
			pageMonthlyPlanningPO.CompareValues(extentTest, DBTotal, UITotal);
		}
		if (OptionToView.contains("Forecast")) {
			String ForecastSales = data.getText("ForecastSales");
			DBValues = sql.CLRexecuteQuery(getContext(), ForecastSales
					.replace("store", getContext().getStringProperty("store")).replace("Month", MonthName)
					.replace("Division", getContext().getStringProperty("Division")));
			System.out.println("DB Value: " + DBValues);

			String ForecastSalesTotal = data.getText("ForecastSalesTotal");
			DBTotal = sql.CLRexecuteQuery(getContext(), ForecastSalesTotal
					.replace("store", getContext().getStringProperty("store")).replace("Month", MonthName)
					.replace("Division", getContext().getStringProperty("Division")));
			System.out.println("DB Total: " + DBTotal);

			UIValues = pageWeeklyPlanningPO.UIResults(objMonthlyPlanningStoreObjects.MPTable, extentTest,
					objWeeklyPlanningStoreObjects.DepartmentNames, objMonthlyPlanningStoreObjects.ForecastSales);
			pageMonthlyPlanningPO.CompareValues(extentTest, DBValues, UIValues);
			UITotal = pageMonthlyPlanningPO.GetTotal(extentTest, objMonthlyPlanningStoreObjects.TotalRow,
					objMonthlyPlanningStoreObjects.ForecastSalesTotal);
			pageMonthlyPlanningPO.CompareValues(extentTest, DBTotal, UITotal);
		}
	}

	@Test(description = "Monthly Planning - Store, Generated Sales- Current Month", priority = 38)
	public void GeneratedSales_CurrentMonth() throws Exception {
		pageWeeklyPlanningPO = new WeeklyPlanningStorePage().createPage(getDriver(), getContext(), log, status, data,
				getReport(), getReportLogger(), WeeklyPlanningStorePage.class);
		pageMonthlyPlanningPO = new MonthlyPlanningStorePage().createPage(getDriver(), getContext(), log, status, data,
				getReport(), getReportLogger(), MonthlyPlanningStorePage.class);
		TestStatus status = getTestStatus();
		OptionToView = getContext().getStringProperty("OptionToView");
		Month = pageWeeklyPlanningPO.MonthName(objWeeklyPlanningStoreObjects, extentTest,
				objMonthlyPlanningStoreObjects.CurrentMonth);
		String MonthName = Month.concat(getContext().getStringProperty("Month"));

		String GeneratedSales = data.getText("GeneratedSales");
		DBValues = sql.CLRexecuteQuery(getContext(),
				GeneratedSales.replace("store", getContext().getStringProperty("store")).replace("Month", MonthName).replace("Division", getContext().getStringProperty("Division")));
		System.out.println("DB Value: " + DBValues);

		String GeneratedSalesTotal = data.getText("GeneratedSalesTotal");
		DBTotal = sql.CLRexecuteQuery(getContext(), GeneratedSalesTotal
				.replace("store", getContext().getStringProperty("store")).replace("Month", MonthName).replace("Division", getContext().getStringProperty("Division")));
		System.out.println("DB Total: " + DBTotal);

		UIValues = pageWeeklyPlanningPO.UIResults(objMonthlyPlanningStoreObjects.MPTable, extentTest,
				objWeeklyPlanningStoreObjects.DepartmentNames, objMonthlyPlanningStoreObjects.GeneratedSales);
		pageMonthlyPlanningPO.CompareValues(extentTest, DBValues, UIValues);

		UITotal = pageMonthlyPlanningPO.GetTotal(extentTest, objMonthlyPlanningStoreObjects.TotalRow,
				objMonthlyPlanningStoreObjects.GeneratedSalesTotal);
		pageMonthlyPlanningPO.CompareValues(extentTest, DBTotal, UITotal);
	}

	@Test(description = "Monthly Planning - Store, Planned Sales- Current Month", priority = 39)
	public void PlannedSales_CurrentMonth() throws Exception {
		pageWeeklyPlanningPO = new WeeklyPlanningStorePage().createPage(getDriver(), getContext(), log, status, data,
				getReport(), getReportLogger(), WeeklyPlanningStorePage.class);
		pageMonthlyPlanningPO = new MonthlyPlanningStorePage().createPage(getDriver(), getContext(), log, status, data,
				getReport(), getReportLogger(), MonthlyPlanningStorePage.class);
		TestStatus status = getTestStatus();
		OptionToView = getContext().getStringProperty("OptionToView");
		Month = pageWeeklyPlanningPO.MonthName(objWeeklyPlanningStoreObjects, extentTest,
				objMonthlyPlanningStoreObjects.CurrentMonth);
		String MonthName = Month.concat(getContext().getStringProperty("Month"));

		String PlannedSales = data.getText("PlannedSales");
		DBValues = sql.CLRexecuteQuery(getContext(),
				PlannedSales.replace("store", getContext().getStringProperty("store")).replace("Month", MonthName).replace("Division", getContext().getStringProperty("Division")));
		System.out.println("DB Value: " + DBValues);

		String PlannedSalesTotal = data.getText("PlannedSalesTotal");
		DBTotal = sql.CLRexecuteQuery(getContext(), PlannedSalesTotal
				.replace("store", getContext().getStringProperty("store")).replace("Month", MonthName).replace("Division", getContext().getStringProperty("Division")));
		System.out.println("DB Total: " + DBTotal);

		UIValues = pageMonthlyPlanningPO.UIData(objMonthlyPlanningStoreObjects.FifthWeek, extentTest,
				objMonthlyPlanningStoreObjects.PlannedSales);
		pageMonthlyPlanningPO.CompareValues(extentTest, DBValues, UIValues);

		UITotal = pageMonthlyPlanningPO.GetTotal(extentTest, objMonthlyPlanningStoreObjects.TotalRow,
				objMonthlyPlanningStoreObjects.PlannedSalesTotal);
		pageMonthlyPlanningPO.CompareValues(extentTest, DBTotal, UITotal);
	}

	@Test(description = "Monthly Planning - Store, Var to BUD Sales- Current Month", priority = 40)
	public void VarToBUDSales_CurrentMonth() throws Exception {
		pageWeeklyPlanningPO = new WeeklyPlanningStorePage().createPage(getDriver(), getContext(), log, status, data,
				getReport(), getReportLogger(), WeeklyPlanningStorePage.class);
		pageMonthlyPlanningPO = new MonthlyPlanningStorePage().createPage(getDriver(), getContext(), log, status, data,
				getReport(), getReportLogger(), MonthlyPlanningStorePage.class);
		TestStatus status = getTestStatus();
		OptionToView = getContext().getStringProperty("OptionToView");
		Month = pageWeeklyPlanningPO.MonthName(objWeeklyPlanningStoreObjects, extentTest,
				objMonthlyPlanningStoreObjects.CurrentMonth);
		String MonthName = Month.concat(getContext().getStringProperty("Month"));

		if (OptionToView.contains("Budget")) {
			String VartoBUD = data.getText("VartoBUD");
			DBValues = sql.CLRexecuteQuery(getContext(),
					VartoBUD.replace("store", getContext().getStringProperty("store")).replace("Month", MonthName).replace("Division", getContext().getStringProperty("Division")));
			System.out.println("DB Value: " + DBValues);

			String VartoBUDTotal = data.getText("VartoBUDTotal");
			DBTotal = sql.CLRexecuteQuery(getContext(), VartoBUDTotal
					.replace("store", getContext().getStringProperty("store")).replace("Month", MonthName).replace("Division", getContext().getStringProperty("Division")));
			System.out.println("DB Total: " + DBTotal);

			UIValues = pageWeeklyPlanningPO.UIResults(objMonthlyPlanningStoreObjects.MPTable, extentTest,
					objWeeklyPlanningStoreObjects.DepartmentNames, objMonthlyPlanningStoreObjects.VarToBudSales);
			pageMonthlyPlanningPO.CompareValues(extentTest, DBValues, UIValues);
			UITotal = pageMonthlyPlanningPO.GetTotal(extentTest, objMonthlyPlanningStoreObjects.TotalRow,
					objMonthlyPlanningStoreObjects.VartoBudSalesTotal);
			pageMonthlyPlanningPO.CompareValues(extentTest, DBTotal, UITotal);
		}
		if (OptionToView.contains("Forecast")) {
			String VartoFCST = data.getText("VartoFCST");
			DBValues = sql.CLRexecuteQuery(getContext(),
					VartoFCST.replace("store", getContext().getStringProperty("store")).replace("Month", MonthName).replace("Division", getContext().getStringProperty("Division")));
			System.out.println("DB Value: " + DBValues);

			String VartoFCSTTotal = data.getText("VartoFCSTTotal");
			DBTotal = sql.CLRexecuteQuery(getContext(), VartoFCSTTotal
					.replace("store", getContext().getStringProperty("store")).replace("Month", MonthName).replace("Division", getContext().getStringProperty("Division")));
			System.out.println("DB Total: " + DBTotal);

			UIValues = pageWeeklyPlanningPO.UIResults(objMonthlyPlanningStoreObjects.MPTable, extentTest,
					objWeeklyPlanningStoreObjects.DepartmentNames, objMonthlyPlanningStoreObjects.VarToForecast);
			pageMonthlyPlanningPO.CompareValues(extentTest, DBValues, UIValues);
			UITotal = pageMonthlyPlanningPO.GetTotal(extentTest, objMonthlyPlanningStoreObjects.TotalRow,
					objMonthlyPlanningStoreObjects.VarToForecastTotal);
			pageMonthlyPlanningPO.CompareValues(extentTest, DBTotal, UITotal);
		}
	}

	@Test(description = "Monthly Planning - Store, LYPercentage Sales- Current Month", priority = 41)
	public void LYPercentageSales_CurrentMonth() throws Exception {
		pageWeeklyPlanningPO = new WeeklyPlanningStorePage().createPage(getDriver(), getContext(), log, status, data,
				getReport(), getReportLogger(), WeeklyPlanningStorePage.class);
		pageMonthlyPlanningPO = new MonthlyPlanningStorePage().createPage(getDriver(), getContext(), log, status, data,
				getReport(), getReportLogger(), MonthlyPlanningStorePage.class);
		TestStatus status = getTestStatus();
		OptionToView = getContext().getStringProperty("OptionToView");
		Month = pageWeeklyPlanningPO.MonthName(objWeeklyPlanningStoreObjects, extentTest,
				objMonthlyPlanningStoreObjects.CurrentMonth);
		String MonthName = Month.concat(getContext().getStringProperty("Month"));
		String LYMonthName = Month.concat(getContext().getStringProperty("LYMonth"));

		String LYPercentageSales = data.getText("LYPercentageSales");
		DBValues = sql.CLRexecuteQuery(getContext(),
				LYPercentageSales.replace("store", getContext().getStringProperty("store")).replace("Month", MonthName)
						.replace("LYMnth", LYMonthName).replace("Division", getContext().getStringProperty("Division")));
		System.out.println("DB Value: " + DBValues);

		String LYPercentageSalesTotal = data.getText("LYPercentageSalesTotal");
		DBTotal = sql.CLRexecuteQuery(getContext(),
				LYPercentageSalesTotal.replace("store", getContext().getStringProperty("store"))
						.replace("Month", MonthName).replace("LYMnth", LYMonthName).replace("Division", getContext().getStringProperty("Division")));
		System.out.println("DB Total: " + DBTotal);

		UIValues = pageWeeklyPlanningPO.UIResults(objMonthlyPlanningStoreObjects.MPTable, extentTest,
				objWeeklyPlanningStoreObjects.DepartmentNames, objMonthlyPlanningStoreObjects.LYPercentageSales);
		pageMonthlyPlanningPO.CompareValueDecimalFields(extentTest, DBValues, UIValues);

		UITotal = pageMonthlyPlanningPO.GetTotal(extentTest, objMonthlyPlanningStoreObjects.TotalRow,
				objMonthlyPlanningStoreObjects.LYPercentageSalesTotal);
		pageMonthlyPlanningPO.CompareValueDecimalFields(extentTest, DBTotal, UITotal);
	}

	@Test(description = "Monthly Planning - Store, BudgetPercentage Sales- Current Month", priority = 42)
	public void BudgetPercentageSales_CurrentMonth() throws Exception {
		pageWeeklyPlanningPO = new WeeklyPlanningStorePage().createPage(getDriver(), getContext(), log, status, data,
				getReport(), getReportLogger(), WeeklyPlanningStorePage.class);
		pageMonthlyPlanningPO = new MonthlyPlanningStorePage().createPage(getDriver(), getContext(), log, status, data,
				getReport(), getReportLogger(), MonthlyPlanningStorePage.class);
		TestStatus status = getTestStatus();
		OptionToView = getContext().getStringProperty("OptionToView");
		Month = pageWeeklyPlanningPO.MonthName(objWeeklyPlanningStoreObjects, extentTest,
				objMonthlyPlanningStoreObjects.CurrentMonth);
		String MonthName = Month.concat(getContext().getStringProperty("Month"));
		String LYMonthName = Month.concat(getContext().getStringProperty("LYMonth"));
		if (OptionToView.contains("Budget")) {
			String BUDPercentageSales = data.getText("BUDPercentageSales");
			DBValues = sql.CLRexecuteQuery(getContext(),
					BUDPercentageSales.replace("store", getContext().getStringProperty("store"))
							.replace("Month", MonthName).replace("LYMnth", LYMonthName).replace("Division", getContext().getStringProperty("Division")));
			System.out.println("DB Value: " + DBValues);

			String BUDPercentageSalesTotal = data.getText("BUDPercentageSalesTotal");
			DBTotal = sql.CLRexecuteQuery(getContext(),
					BUDPercentageSalesTotal.replace("store", getContext().getStringProperty("store"))
							.replace("Month", MonthName).replace("LYMnth", LYMonthName).replace("Division", getContext().getStringProperty("Division")));
			System.out.println("DB Total: " + DBTotal);

			UIValues = pageWeeklyPlanningPO.UIResults(objMonthlyPlanningStoreObjects.MPTable, extentTest,
					objWeeklyPlanningStoreObjects.DepartmentNames,
					objMonthlyPlanningStoreObjects.BudgetPercentageSales);
			pageMonthlyPlanningPO.CompareValueDecimalFields(extentTest, DBValues, UIValues);
			UITotal = pageMonthlyPlanningPO.GetTotal(extentTest, objMonthlyPlanningStoreObjects.TotalRow,
					objMonthlyPlanningStoreObjects.BudgetPercentageSalesTotal);
			pageMonthlyPlanningPO.CompareValueDecimalFields(extentTest, DBTotal, UITotal);
		}
		if (OptionToView.contains("Forecast")) {
			String FCSTPercentageSales = data.getText("FCSTPercentageSales");
			DBValues = sql.CLRexecuteQuery(getContext(),
					FCSTPercentageSales.replace("store", getContext().getStringProperty("store"))
							.replace("Month", MonthName).replace("LYMnth", LYMonthName).replace("Division", getContext().getStringProperty("Division")));
			System.out.println("DB Value: " + DBValues);

			String FCSTPercentageSalesTotal = data.getText("FCSTPercentageSalesTotal");
			DBTotal = sql.CLRexecuteQuery(getContext(),
					FCSTPercentageSalesTotal.replace("store", getContext().getStringProperty("store"))
							.replace("Month", MonthName).replace("LYMnth", LYMonthName).replace("Division", getContext().getStringProperty("Division")));
			System.out.println("DB Total: " + DBTotal);

			UIValues = pageWeeklyPlanningPO.UIResults(objMonthlyPlanningStoreObjects.MPTable, extentTest,
					objWeeklyPlanningStoreObjects.DepartmentNames,
					objMonthlyPlanningStoreObjects.ForecastPercentageSales);
			pageMonthlyPlanningPO.CompareValueDecimalFields(extentTest, DBValues, UIValues);
			UITotal = pageMonthlyPlanningPO.GetTotal(extentTest, objMonthlyPlanningStoreObjects.TotalRow,
					objMonthlyPlanningStoreObjects.ForecastPercentageSalesTotal);
			pageMonthlyPlanningPO.CompareValueDecimalFields(extentTest, DBTotal, UITotal);
		}
	}

	@Test(description = "Monthly Planning - Store, Budget Wages - Current Month", priority = 43)
	public void BudgetWages_CurrentMonth() throws Exception {
		pageWeeklyPlanningPO = new WeeklyPlanningStorePage().createPage(getDriver(), getContext(), log, status, data,
				getReport(), getReportLogger(), WeeklyPlanningStorePage.class);
		pageMonthlyPlanningPO = new MonthlyPlanningStorePage().createPage(getDriver(), getContext(), log, status, data,
				getReport(), getReportLogger(), MonthlyPlanningStorePage.class);
		TestStatus status = getTestStatus();
		OptionToView = getContext().getStringProperty("OptionToView");
		Month = pageWeeklyPlanningPO.MonthName(objWeeklyPlanningStoreObjects, extentTest,
				objMonthlyPlanningStoreObjects.CurrentMonth);
		String MonthName = Month.concat(getContext().getStringProperty("Month"));
		if (OptionToView.contains("Budget")) {
			String BudgetWages = data.getText("BudgetWages");
			DBValues = sql.CLRexecuteQuery(getContext(),
					BudgetWages.replace("store", getContext().getStringProperty("store")).replace("Month", MonthName).replace("Division", getContext().getStringProperty("Division")));
			System.out.println("DB Value: " + DBValues);

			String BudgetWagesTotal = data.getText("BudgetWagesTotal");
			DBTotal = sql.CLRexecuteQuery(getContext(), BudgetWagesTotal
					.replace("store", getContext().getStringProperty("store")).replace("Month", MonthName).replace("Division", getContext().getStringProperty("Division")));
			System.out.println("DB Total: " + DBTotal);

			UIValues = pageWeeklyPlanningPO.UIResults(objMonthlyPlanningStoreObjects.MPTable, extentTest,
					objWeeklyPlanningStoreObjects.DepartmentNames, objMonthlyPlanningStoreObjects.BudgetWages);
			pageMonthlyPlanningPO.CompareValues(extentTest, DBValues, UIValues);
			UITotal = pageMonthlyPlanningPO.GetTotal(extentTest, objMonthlyPlanningStoreObjects.TotalRow,
					objMonthlyPlanningStoreObjects.BudgetWagesTotal);
			pageMonthlyPlanningPO.CompareValues(extentTest, DBTotal, UITotal);
		}

		if (OptionToView.contains("Forecast")) {
			String ForecastWages = data.getText("ForecastWages");
			DBValues = sql.CLRexecuteQuery(getContext(), ForecastWages
					.replace("store", getContext().getStringProperty("store")).replace("Month", MonthName).replace("Division", getContext().getStringProperty("Division")));
			System.out.println("DB Value: " + DBValues);

			String ForecastWagesTotal = data.getText("ForecastWagesTotal");
			DBTotal = sql.CLRexecuteQuery(getContext(), ForecastWagesTotal
					.replace("store", getContext().getStringProperty("store")).replace("Month", MonthName).replace("Division", getContext().getStringProperty("Division")));
			System.out.println("DB Total: " + DBTotal);

			UIValues = pageWeeklyPlanningPO.UIResults(objMonthlyPlanningStoreObjects.MPTable, extentTest,
					objWeeklyPlanningStoreObjects.DepartmentNames, objMonthlyPlanningStoreObjects.ForecastWages);
			pageMonthlyPlanningPO.CompareValues(extentTest, DBValues, UIValues);
			UITotal = pageMonthlyPlanningPO.GetTotal(extentTest, objMonthlyPlanningStoreObjects.TotalRow,
					objMonthlyPlanningStoreObjects.ForecastWagesTotal);
			pageMonthlyPlanningPO.CompareValues(extentTest, DBTotal, UITotal);
		}
	}

	@Test(description = "Monthly Planning - Store, BaseCost Wages - Current Month", priority = 44)
	public void BaseCostWages_CurrentMonth() throws Exception {
		pageWeeklyPlanningPO = new WeeklyPlanningStorePage().createPage(getDriver(), getContext(), log, status, data,
				getReport(), getReportLogger(), WeeklyPlanningStorePage.class);
		pageMonthlyPlanningPO = new MonthlyPlanningStorePage().createPage(getDriver(), getContext(), log, status, data,
				getReport(), getReportLogger(), MonthlyPlanningStorePage.class);
		TestStatus status = getTestStatus();
		OptionToView = getContext().getStringProperty("OptionToView");
		Month = pageWeeklyPlanningPO.MonthName(objWeeklyPlanningStoreObjects, extentTest,
				objMonthlyPlanningStoreObjects.CurrentMonth);
		String MonthName = Month.concat(getContext().getStringProperty("Month"));

		String BaseCostWages = data.getText("BaseCostWages");
		DBValues = sql.CLRexecuteQuery(getContext(),
				BaseCostWages.replace("store", getContext().getStringProperty("store")).replace("Month", MonthName).replace("Division", getContext().getStringProperty("Division")));
		System.out.println("DB Value: " + DBValues);

		String BaseCostWagesTotal = data.getText("BaseCostWagesTotal");
		DBTotal = sql.CLRexecuteQuery(getContext(), BaseCostWagesTotal
				.replace("store", getContext().getStringProperty("store")).replace("Month", MonthName).replace("Division", getContext().getStringProperty("Division")));
		System.out.println("DB Total: " + DBTotal);

		UIValues = pageWeeklyPlanningPO.UIResults(objMonthlyPlanningStoreObjects.MPTable, extentTest,
				objWeeklyPlanningStoreObjects.DepartmentNames, objMonthlyPlanningStoreObjects.BaseCostWages);
		pageMonthlyPlanningPO.CompareValues(extentTest, DBValues, UIValues);

		UITotal = pageMonthlyPlanningPO.GetTotal(extentTest, objMonthlyPlanningStoreObjects.TotalRow,
				objMonthlyPlanningStoreObjects.BaseCostWagesTotal);
		pageMonthlyPlanningPO.CompareValues(extentTest, DBTotal, UITotal);
	}

	@Test(description = "Monthly Planning - Store, Planned Wages - Current Month", priority = 45)
	public void PlannedWages_CurrentMonth() throws Exception {
		pageWeeklyPlanningPO = new WeeklyPlanningStorePage().createPage(getDriver(), getContext(), log, status, data,
				getReport(), getReportLogger(), WeeklyPlanningStorePage.class);
		pageMonthlyPlanningPO = new MonthlyPlanningStorePage().createPage(getDriver(), getContext(), log, status, data,
				getReport(), getReportLogger(), MonthlyPlanningStorePage.class);
		TestStatus status = getTestStatus();
		OptionToView = getContext().getStringProperty("OptionToView");
		Month = pageWeeklyPlanningPO.MonthName(objWeeklyPlanningStoreObjects, extentTest,
				objMonthlyPlanningStoreObjects.CurrentMonth);
		String MonthName = Month.concat(getContext().getStringProperty("Month"));

		String PlannedWages = data.getText("PlannedWages");
		DBValues = sql.CLRexecuteQuery(getContext(),
				PlannedWages.replace("store", getContext().getStringProperty("store")).replace("Month", MonthName).replace("Division", getContext().getStringProperty("Division")));
		System.out.println("DB Value: " + DBValues);

		String PlannedWagesTotal = data.getText("PlannedWagesTotal");
		DBTotal = sql.CLRexecuteQuery(getContext(), PlannedWagesTotal
				.replace("store", getContext().getStringProperty("store")).replace("Month", MonthName).replace("Division", getContext().getStringProperty("Division")));
		System.out.println("DB Total: " + DBTotal);

		UIValues = pageMonthlyPlanningPO.UIData(objMonthlyPlanningStoreObjects.FifthWeek, extentTest,
				objMonthlyPlanningStoreObjects.PlannedWages);
		pageMonthlyPlanningPO.CompareValues(extentTest, DBValues, UIValues);

		UITotal = pageMonthlyPlanningPO.GetTotal(extentTest, objMonthlyPlanningStoreObjects.TotalRow,
				objMonthlyPlanningStoreObjects.PlannedWagesTotal);
		pageMonthlyPlanningPO.CompareValues(extentTest, DBTotal, UITotal);
	}

	@Test(description = "Monthly Planning - Store, LYPercentage Wages - Current Month", priority = 46)
	public void LYPercentageWages_CurrentMonth() throws Exception {
		pageWeeklyPlanningPO = new WeeklyPlanningStorePage().createPage(getDriver(), getContext(), log, status, data,
				getReport(), getReportLogger(), WeeklyPlanningStorePage.class);
		pageMonthlyPlanningPO = new MonthlyPlanningStorePage().createPage(getDriver(), getContext(), log, status, data,
				getReport(), getReportLogger(), MonthlyPlanningStorePage.class);
		TestStatus status = getTestStatus();
		OptionToView = getContext().getStringProperty("OptionToView");
		Month = pageWeeklyPlanningPO.MonthName(objWeeklyPlanningStoreObjects, extentTest,
				objMonthlyPlanningStoreObjects.CurrentMonth);
		String MonthName = Month.concat(getContext().getStringProperty("Month"));
		String LYMonthName = Month.concat(getContext().getStringProperty("LYMonth"));

		String LYPercentageWages = data.getText("LYPercentageWages");
		DBValues = sql.CLRexecuteQuery(getContext(),
				LYPercentageWages.replace("store", getContext().getStringProperty("store")).replace("Month", MonthName)
						.replace("LYMnth", LYMonthName).replace("Division", getContext().getStringProperty("Division")));
		System.out.println("DB Value: " + DBValues);

		String LYPercentageWagesTotal = data.getText("LYPercentageWagesTotal");
		DBTotal = sql.CLRexecuteQuery(getContext(),
				LYPercentageWagesTotal.replace("store", getContext().getStringProperty("store"))
						.replace("Month", MonthName).replace("LYMnth", LYMonthName).replace("Division", getContext().getStringProperty("Division")));
		System.out.println("DB Total: " + DBTotal);

		UIValues = pageWeeklyPlanningPO.UIResults(objMonthlyPlanningStoreObjects.MPTable, extentTest,
				objWeeklyPlanningStoreObjects.DepartmentNames, objMonthlyPlanningStoreObjects.LYPercentageWages);
		pageMonthlyPlanningPO.CompareValueDecimalFields(extentTest, DBValues, UIValues);

		UITotal = pageMonthlyPlanningPO.GetTotal(extentTest, objMonthlyPlanningStoreObjects.TotalRow,
				objMonthlyPlanningStoreObjects.LYPercentageWagesTotal);
		pageMonthlyPlanningPO.CompareValueDecimalFields(extentTest, DBTotal, UITotal);
	}

	@Test(description = "Monthly Planning - Store, Budget WagesPercentage - Current Month", priority = 47)
	public void BudgetWagesPercentage_CurrentMonth() throws Exception {
		pageWeeklyPlanningPO = new WeeklyPlanningStorePage().createPage(getDriver(), getContext(), log, status, data,
				getReport(), getReportLogger(), WeeklyPlanningStorePage.class);
		pageMonthlyPlanningPO = new MonthlyPlanningStorePage().createPage(getDriver(), getContext(), log, status, data,
				getReport(), getReportLogger(), MonthlyPlanningStorePage.class);
		TestStatus status = getTestStatus();
		OptionToView = getContext().getStringProperty("OptionToView");
		Month = pageWeeklyPlanningPO.MonthName(objWeeklyPlanningStoreObjects, extentTest,
				objMonthlyPlanningStoreObjects.CurrentMonth);
		String MonthName = Month.concat(getContext().getStringProperty("Month"));
		if (OptionToView.contains("Budget")) {
			String BudgetWagesPercentage = data.getText("BudgetWagesPercentage");
			DBValues = sql.CLRexecuteQuery(getContext(), BudgetWagesPercentage
					.replace("store", getContext().getStringProperty("store")).replace("Month", MonthName).replace("Division", getContext().getStringProperty("Division")));
			System.out.println("DB Value: " + DBValues);

			String BudgetWagesPercentageTotal = data.getText("BudgetWagesPercentageTotal");
			DBTotal = sql.CLRexecuteQuery(getContext(), BudgetWagesPercentageTotal
					.replace("store", getContext().getStringProperty("store")).replace("Month", MonthName).replace("Division", getContext().getStringProperty("Division")));
			System.out.println("DB Total: " + DBTotal);

			UIValues = pageWeeklyPlanningPO.UIResults(objMonthlyPlanningStoreObjects.MPTable, extentTest,
					objWeeklyPlanningStoreObjects.DepartmentNames,
					objMonthlyPlanningStoreObjects.BudgetWagesPercentage);
			pageMonthlyPlanningPO.CompareValueDecimalFields(extentTest, DBValues, UIValues);
			UITotal = pageMonthlyPlanningPO.GetTotal(extentTest, objMonthlyPlanningStoreObjects.TotalRow,
					objMonthlyPlanningStoreObjects.BudgetWagesPercentageTotal);
			pageMonthlyPlanningPO.CompareValueDecimalFields(extentTest, DBTotal, UITotal);
		}
		if (OptionToView.contains("Forecast")) {
			String ForecastWagesPercentage = data.getText("ForecastWagesPercentage");
			DBValues = sql.CLRexecuteQuery(getContext(), ForecastWagesPercentage
					.replace("store", getContext().getStringProperty("store")).replace("Month", MonthName).replace("Division", getContext().getStringProperty("Division")));
			System.out.println("DB Value: " + DBValues);

			String ForecastWagesPercentageTotal = data.getText("ForecastWagesPercentageTotal");
			DBTotal = sql.CLRexecuteQuery(getContext(), ForecastWagesPercentageTotal
					.replace("store", getContext().getStringProperty("store")).replace("Month", MonthName).replace("Division", getContext().getStringProperty("Division")));
			System.out.println("DB Total: " + DBTotal);

			UIValues = pageWeeklyPlanningPO.UIResults(objMonthlyPlanningStoreObjects.MPTable, extentTest,
					objWeeklyPlanningStoreObjects.DepartmentNames,
					objMonthlyPlanningStoreObjects.ForecastWagesPercentage);
			pageMonthlyPlanningPO.CompareValueDecimalFields(extentTest, DBValues, UIValues);
			UITotal = pageMonthlyPlanningPO.GetTotal(extentTest, objMonthlyPlanningStoreObjects.TotalRow,
					objMonthlyPlanningStoreObjects.ForecastWagesPercentageTotal);
			pageMonthlyPlanningPO.CompareValueDecimalFields(extentTest, DBTotal, UITotal);
		}
	}

	@Test(description = "Monthly Planning - Store, Planned WagesPercentage - Current Month", priority = 48)
	public void PlannedWagesPercentage_CurrentMonth() throws Exception {
		pageWeeklyPlanningPO = new WeeklyPlanningStorePage().createPage(getDriver(), getContext(), log, status, data,
				getReport(), getReportLogger(), WeeklyPlanningStorePage.class);
		pageMonthlyPlanningPO = new MonthlyPlanningStorePage().createPage(getDriver(), getContext(), log, status, data,
				getReport(), getReportLogger(), MonthlyPlanningStorePage.class);
		TestStatus status = getTestStatus();
		OptionToView = getContext().getStringProperty("OptionToView");
		Month = pageWeeklyPlanningPO.MonthName(objWeeklyPlanningStoreObjects, extentTest,
				objMonthlyPlanningStoreObjects.CurrentMonth);
		String MonthName = Month.concat(getContext().getStringProperty("Month"));

		String PlannedWagesPercentage = data.getText("PlannedWagesPercentage");
		DBValues = sql.CLRexecuteQuery(getContext(), PlannedWagesPercentage
				.replace("store", getContext().getStringProperty("store")).replace("Month", MonthName).replace("Division", getContext().getStringProperty("Division")));
		System.out.println("DB Value: " + DBValues);

		String PlannedWagesPercentageTotal = data.getText("PlannedWagesPercentageTotal");
		DBTotal = sql.CLRexecuteQuery(getContext(), PlannedWagesPercentageTotal
				.replace("store", getContext().getStringProperty("store")).replace("Month", MonthName).replace("Division", getContext().getStringProperty("Division")));
		System.out.println("DB Total: " + DBTotal);

		UIValues = pageWeeklyPlanningPO.UIResults(objMonthlyPlanningStoreObjects.MPTable, extentTest,
				objWeeklyPlanningStoreObjects.DepartmentNames, objMonthlyPlanningStoreObjects.PlannedLYWagesPercentage);
		pageMonthlyPlanningPO.CompareValueDecimalFields(extentTest, DBValues, UIValues);

		UITotal = pageMonthlyPlanningPO.GetTotal(extentTest, objMonthlyPlanningStoreObjects.TotalRow,
				objMonthlyPlanningStoreObjects.PlannedLYWagesPercentageTotal);
		pageMonthlyPlanningPO.CompareValueDecimalFields(extentTest, DBTotal, UITotal);
	}

	@Test(description = "Monthly Planning - Store, LY WagesPercentage - Current Month", priority = 49)
	public void LYWagesPercentage_CurrentMonth() throws Exception {
		pageWeeklyPlanningPO = new WeeklyPlanningStorePage().createPage(getDriver(), getContext(), log, status, data,
				getReport(), getReportLogger(), WeeklyPlanningStorePage.class);
		pageMonthlyPlanningPO = new MonthlyPlanningStorePage().createPage(getDriver(), getContext(), log, status, data,
				getReport(), getReportLogger(), MonthlyPlanningStorePage.class);
		TestStatus status = getTestStatus();
		OptionToView = getContext().getStringProperty("OptionToView");
		Month = pageWeeklyPlanningPO.MonthName(objWeeklyPlanningStoreObjects, extentTest,
				objMonthlyPlanningStoreObjects.CurrentMonth);
		String MonthName = Month.concat(getContext().getStringProperty("Month"));
		String LYMonthName = Month.concat(getContext().getStringProperty("LYMonth"));

		String LYWagesPercentage = data.getText("LYWagesPercentage");
		DBValues = sql.CLRexecuteQuery(getContext(),
				LYWagesPercentage.replace("store", getContext().getStringProperty("store")).replace("Month", MonthName)
						.replace("LYMnth", LYMonthName).replace("Division", getContext().getStringProperty("Division")));
		System.out.println("DB Value: " + DBValues);

		String LYWagesPercentageTotal = data.getText("LYWagesPercentageTotal");
		DBTotal = sql.CLRexecuteQuery(getContext(),
				LYWagesPercentageTotal.replace("store", getContext().getStringProperty("store"))
						.replace("LYMnth", LYMonthName).replace("Month", MonthName).replace("Division", getContext().getStringProperty("Division")));
		System.out.println("DB Total: " + DBTotal);

		UIValues = pageWeeklyPlanningPO.UIResults(objMonthlyPlanningStoreObjects.MPTable, extentTest,
				objWeeklyPlanningStoreObjects.DepartmentNames, objMonthlyPlanningStoreObjects.LYWagesPercentage);
		pageMonthlyPlanningPO.CompareValueDecimalFields(extentTest, DBValues, UIValues);

		UITotal = pageMonthlyPlanningPO.GetTotal(extentTest, objMonthlyPlanningStoreObjects.TotalRow,
				objMonthlyPlanningStoreObjects.LYWagesPercentageTotal);
		pageMonthlyPlanningPO.CompareValueDecimalFields(extentTest, DBTotal, UITotal);
	}

	@Test(description = "Monthly Planning - Store, SMS Hours - Current Month", priority = 50)
	public void SMSHours_CurrentMonth() throws Exception {
		pageWeeklyPlanningPO = new WeeklyPlanningStorePage().createPage(getDriver(), getContext(), log, status, data,
				getReport(), getReportLogger(), WeeklyPlanningStorePage.class);
		pageMonthlyPlanningPO = new MonthlyPlanningStorePage().createPage(getDriver(), getContext(), log, status, data,
				getReport(), getReportLogger(), MonthlyPlanningStorePage.class);
		TestStatus status = getTestStatus();
		OptionToView = getContext().getStringProperty("OptionToView");
		Month = pageWeeklyPlanningPO.MonthName(objWeeklyPlanningStoreObjects, extentTest,
				objMonthlyPlanningStoreObjects.CurrentMonth);
		String MonthName = Month.concat(getContext().getStringProperty("Month"));

		String SMSHours = data.getText("SMSHours");
		DBValues = sql.CLRexecuteQuery(getContext(),
				SMSHours.replace("store", getContext().getStringProperty("store")).replace("Month", MonthName).replace("Division", getContext().getStringProperty("Division")));
		System.out.println("DB Value: " + DBValues);

		String SMSHoursTotal = data.getText("SMSHoursTotal");
		DBTotal = sql.CLRexecuteQuery(getContext(),
				SMSHoursTotal.replace("store", getContext().getStringProperty("store")).replace("Month", MonthName).replace("Division", getContext().getStringProperty("Division")));
		System.out.println("DB Total: " + DBTotal);

		UIValues = pageWeeklyPlanningPO.UIResults(objMonthlyPlanningStoreObjects.MPTable, extentTest,
				objWeeklyPlanningStoreObjects.DepartmentNames, objMonthlyPlanningStoreObjects.SMSHours);
		pageMonthlyPlanningPO.CompareValueDecimalFields(extentTest, DBValues, UIValues);

		UITotal = pageMonthlyPlanningPO.GetTotal(extentTest, objMonthlyPlanningStoreObjects.TotalRow,
				objMonthlyPlanningStoreObjects.SMSHoursTotal);
		pageMonthlyPlanningPO.CompareValues(extentTest, DBTotal, UITotal);
	}

	@Test(description = "Monthly Planning - Store, BaseHours - Current Month", priority = 51)
	public void BaseHours_CurrentMonth() throws Exception {
		pageWeeklyPlanningPO = new WeeklyPlanningStorePage().createPage(getDriver(), getContext(), log, status, data,
				getReport(), getReportLogger(), WeeklyPlanningStorePage.class);
		pageMonthlyPlanningPO = new MonthlyPlanningStorePage().createPage(getDriver(), getContext(), log, status, data,
				getReport(), getReportLogger(), MonthlyPlanningStorePage.class);
		TestStatus status = getTestStatus();
		OptionToView = getContext().getStringProperty("OptionToView");
		Month = pageWeeklyPlanningPO.MonthName(objWeeklyPlanningStoreObjects, extentTest,
				objMonthlyPlanningStoreObjects.CurrentMonth);
		String MonthName = Month.concat(getContext().getStringProperty("Month"));

		String BaseHours = data.getText("BaseHours");
		DBValues = sql.CLRexecuteQuery(getContext(),
				BaseHours.replace("store", getContext().getStringProperty("store")).replace("Month", MonthName).replace("Division", getContext().getStringProperty("Division")));
		System.out.println("DB Value: " + DBValues);

		String BaseHoursTotal = data.getText("BaseHoursTotal");
		DBTotal = sql.CLRexecuteQuery(getContext(),
				BaseHoursTotal.replace("store", getContext().getStringProperty("store")).replace("Month", MonthName).replace("Division", getContext().getStringProperty("Division")));
		System.out.println("DB Total: " + DBTotal);

		UIValues = pageWeeklyPlanningPO.UIResults(objMonthlyPlanningStoreObjects.MPTable, extentTest,
				objWeeklyPlanningStoreObjects.DepartmentNames, objMonthlyPlanningStoreObjects.BaseHours);
		pageMonthlyPlanningPO.CompareValues(extentTest, DBValues, UIValues);

		UITotal = pageMonthlyPlanningPO.GetTotal(extentTest, objMonthlyPlanningStoreObjects.TotalRow,
				objMonthlyPlanningStoreObjects.BaseHoursTotal);
		pageMonthlyPlanningPO.CompareValues(extentTest, DBTotal, UITotal);
	}

	@Test(description = "Monthly Planning - Store, PlannedHours - Current Month", priority = 52)
	public void PlannedHours_CurrentMonth() throws Exception {
		pageWeeklyPlanningPO = new WeeklyPlanningStorePage().createPage(getDriver(), getContext(), log, status, data,
				getReport(), getReportLogger(), WeeklyPlanningStorePage.class);
		pageMonthlyPlanningPO = new MonthlyPlanningStorePage().createPage(getDriver(), getContext(), log, status, data,
				getReport(), getReportLogger(), MonthlyPlanningStorePage.class);
		TestStatus status = getTestStatus();
		OptionToView = getContext().getStringProperty("OptionToView");
		Month = pageWeeklyPlanningPO.MonthName(objWeeklyPlanningStoreObjects, extentTest,
				objMonthlyPlanningStoreObjects.CurrentMonth);
		String MonthName = Month.concat(getContext().getStringProperty("Month"));

		String PlannedHours = data.getText("PlannedHours");
		DBValues = sql.CLRexecuteQuery(getContext(),
				PlannedHours.replace("store", getContext().getStringProperty("store")).replace("Month", MonthName).replace("Division", getContext().getStringProperty("Division")));
		System.out.println("DB Value: " + DBValues);

		String PlannedHoursTotal = data.getText("PlannedHoursTotal");
		DBTotal = sql.CLRexecuteQuery(getContext(), PlannedHoursTotal
				.replace("store", getContext().getStringProperty("store")).replace("Month", MonthName).replace("Division", getContext().getStringProperty("Division")));
		System.out.println("DB Total: " + DBTotal);

		UIValues = pageMonthlyPlanningPO.UIData(objMonthlyPlanningStoreObjects.FifthWeek, extentTest,
				objMonthlyPlanningStoreObjects.PlannedHours);
		pageMonthlyPlanningPO.CompareValues(extentTest, DBValues, UIValues);

		UITotal = pageMonthlyPlanningPO.GetTotal(extentTest, objMonthlyPlanningStoreObjects.TotalRow,
				objMonthlyPlanningStoreObjects.PlannedHoursTotal);
		pageMonthlyPlanningPO.CompareValues(extentTest, DBTotal, UITotal);
	}

	@Test(description = "Monthly Planning - Store, LYPercentage Hours - Current Month", priority = 53)
	public void LYPercentageHours_CurrentMonth() throws Exception {
		pageWeeklyPlanningPO = new WeeklyPlanningStorePage().createPage(getDriver(), getContext(), log, status, data,
				getReport(), getReportLogger(), WeeklyPlanningStorePage.class);
		pageMonthlyPlanningPO = new MonthlyPlanningStorePage().createPage(getDriver(), getContext(), log, status, data,
				getReport(), getReportLogger(), MonthlyPlanningStorePage.class);
		TestStatus status = getTestStatus();
		OptionToView = getContext().getStringProperty("OptionToView");
		Month = pageWeeklyPlanningPO.MonthName(objWeeklyPlanningStoreObjects, extentTest,
				objMonthlyPlanningStoreObjects.CurrentMonth);
		String MonthName = Month.concat(getContext().getStringProperty("Month"));
		String LYMonthName = Month.concat(getContext().getStringProperty("LYMonth"));

		String LYHours = data.getText("LYHours");
		DBValues = sql.CLRexecuteQuery(getContext(), LYHours.replace("store", getContext().getStringProperty("store"))
				.replace("Month", MonthName).replace("LYMnth", LYMonthName).replace("Division", getContext().getStringProperty("Division")));
		System.out.println("DB Value: " + DBValues);

		String LYHoursTotal = data.getText("LYHoursTotal");
		DBTotal = sql.CLRexecuteQuery(getContext(),
				LYHoursTotal.replace("store", getContext().getStringProperty("store")).replace("Month", MonthName)
						.replace("LYMnth", LYMonthName).replace("Division", getContext().getStringProperty("Division")));
		System.out.println("DB Total: " + DBTotal);

		UIValues = pageWeeklyPlanningPO.UIResults(objMonthlyPlanningStoreObjects.MPTable, extentTest,
				objWeeklyPlanningStoreObjects.DepartmentNames, objMonthlyPlanningStoreObjects.LYPercentageHours);
		pageMonthlyPlanningPO.CompareValueDecimalFields(extentTest, DBValues, UIValues);

		UITotal = pageMonthlyPlanningPO.GetTotal(extentTest, objMonthlyPlanningStoreObjects.TotalRow,
				objMonthlyPlanningStoreObjects.LYPercentageHoursTotal);
		pageMonthlyPlanningPO.CompareValueDecimalFields(extentTest, DBTotal, UITotal);
	}

	@Test(description = "Monthly Planning - Store, Budget and Forecast OR - Current Month", priority = 54)
	public void BGDT_FCST_OR_CurrentMonth() throws Exception {
		pageWeeklyPlanningPO = new WeeklyPlanningStorePage().createPage(getDriver(), getContext(), log, status, data,
				getReport(), getReportLogger(), WeeklyPlanningStorePage.class);
		pageMonthlyPlanningPO = new MonthlyPlanningStorePage().createPage(getDriver(), getContext(), log, status, data,
				getReport(), getReportLogger(), MonthlyPlanningStorePage.class);
		TestStatus status = getTestStatus();
		OptionToView = getContext().getStringProperty("OptionToView");
		Month = pageWeeklyPlanningPO.MonthName(objWeeklyPlanningStoreObjects, extentTest,
				objMonthlyPlanningStoreObjects.CurrentMonth);
		String MonthName = Month.concat(getContext().getStringProperty("Month"));
		if (OptionToView.contains("Budget")) {
			String BudgetOR = data.getText("BudgetOR");
			DBValues = sql.CLRexecuteQuery(getContext(),
					BudgetOR.replace("store", getContext().getStringProperty("store")).replace("Month", MonthName)
					.replace("Division", getContext().getStringProperty("Division")));
			System.out.println("DB Value: " + DBValues);

			String BudgetORTotal = data.getText("BudgetORTotal");
			DBTotal = sql.CLRexecuteQuery(getContext(), BudgetORTotal
					.replace("store", getContext().getStringProperty("store")).replace("Month", MonthName)
					.replace("Division", getContext().getStringProperty("Division")));
			System.out.println("DB Total: " + DBTotal);

			UIValues = pageWeeklyPlanningPO.UIResults(objMonthlyPlanningStoreObjects.MPTable, extentTest,
					objWeeklyPlanningStoreObjects.DepartmentNames, objMonthlyPlanningStoreObjects.BdgtOR);
			pageMonthlyPlanningPO.CompareValueDecimalFields(extentTest, DBValues, UIValues);

			UITotal = pageMonthlyPlanningPO.GetTotal(extentTest, objMonthlyPlanningStoreObjects.TotalRow,
					objMonthlyPlanningStoreObjects.BdgtORTtl);
			pageMonthlyPlanningPO.CompareValueDecimalFields(extentTest, DBTotal, UITotal);
		}
		if (OptionToView.contains("Forecast")) {
			String ForecastOR = data.getText("ForecastOR");
			DBValues = sql.CLRexecuteQuery(getContext(),
					ForecastOR.replace("store", getContext().getStringProperty("store")).replace("Month", MonthName)
					.replace("Division", getContext().getStringProperty("Division")));
			System.out.println("DB Value: " + DBValues);

			String ForecastORTotal = data.getText("ForecastORTotal");
			DBTotal = sql.CLRexecuteQuery(getContext(), ForecastORTotal
					.replace("store", getContext().getStringProperty("store")).replace("Month", MonthName)
					.replace("Division", getContext().getStringProperty("Division")));
			System.out.println("DB Total: " + DBTotal);

			UIValues = pageWeeklyPlanningPO.UIResults(objMonthlyPlanningStoreObjects.MPTable, extentTest,
					objWeeklyPlanningStoreObjects.DepartmentNames, objMonthlyPlanningStoreObjects.FcstOR);
			pageMonthlyPlanningPO.CompareValueDecimalFields(extentTest, DBValues, UIValues);

			UITotal = pageMonthlyPlanningPO.GetTotal(extentTest, objMonthlyPlanningStoreObjects.TotalRow,
					objMonthlyPlanningStoreObjects.FcstORTtl);
			pageMonthlyPlanningPO.CompareValueDecimalFields(extentTest, DBTotal, UITotal);
		}
	}

	@Test(description = "Monthly Planning - Store, Planned OR - Current Month", priority = 55)
	public void PlannedOR_CurrentMonth() throws Exception {
		pageWeeklyPlanningPO = new WeeklyPlanningStorePage().createPage(getDriver(), getContext(), log, status, data,
				getReport(), getReportLogger(), WeeklyPlanningStorePage.class);
		pageMonthlyPlanningPO = new MonthlyPlanningStorePage().createPage(getDriver(), getContext(), log, status, data,
				getReport(), getReportLogger(), MonthlyPlanningStorePage.class);
		TestStatus status = getTestStatus();
		OptionToView = getContext().getStringProperty("OptionToView");
		Month = pageWeeklyPlanningPO.MonthName(objWeeklyPlanningStoreObjects, extentTest,
				objMonthlyPlanningStoreObjects.CurrentMonth);
		String MonthName = Month.concat(getContext().getStringProperty("Month"));
		String LYMonthName = Month.concat(getContext().getStringProperty("LYMonth"));

		String PlannedOR = data.getText("PlannedOR");
		DBValues = sql.CLRexecuteQuery(getContext(), PlannedOR.replace("store", getContext().getStringProperty("store"))
				.replace("Month", MonthName).replace("LYMnth", LYMonthName).replace("Division", getContext().getStringProperty("Division")));
		System.out.println("DB Value: " + DBValues);

		String PlannedORTotal = data.getText("PlannedORTotal");
		DBTotal = sql.CLRexecuteQuery(getContext(),
				PlannedORTotal.replace("store", getContext().getStringProperty("store")).replace("Month", MonthName)
						.replace("LYMnth", LYMonthName).replace("Division", getContext().getStringProperty("Division")));
		System.out.println("DB Total: " + DBTotal);

		UIValues = pageWeeklyPlanningPO.UIResults(objMonthlyPlanningStoreObjects.MPTable, extentTest,
				objWeeklyPlanningStoreObjects.DepartmentNames, objMonthlyPlanningStoreObjects.PlannedOR);
		pageMonthlyPlanningPO.CompareValuesforOR(extentTest, DBValues, UIValues);

		UITotal = pageMonthlyPlanningPO.GetTotal(extentTest, objMonthlyPlanningStoreObjects.TotalRow,
				objMonthlyPlanningStoreObjects.PlannedORTotal);
		pageMonthlyPlanningPO.CompareValueDecimalFields(extentTest, DBTotal, UITotal);
	}

	@Test(description = "Monthly Planning - Store, Planned CPH - Current Month", priority = 56)
	public void PlannedCPH_CurrentMonth() throws Exception {
		pageWeeklyPlanningPO = new WeeklyPlanningStorePage().createPage(getDriver(), getContext(), log, status, data,
				getReport(), getReportLogger(), WeeklyPlanningStorePage.class);
		pageMonthlyPlanningPO = new MonthlyPlanningStorePage().createPage(getDriver(), getContext(), log, status, data,
				getReport(), getReportLogger(), MonthlyPlanningStorePage.class);
		TestStatus status = getTestStatus();
		OptionToView = getContext().getStringProperty("OptionToView");
		Month = pageWeeklyPlanningPO.MonthName(objWeeklyPlanningStoreObjects, extentTest,
				objMonthlyPlanningStoreObjects.CurrentMonth);
		String MonthName = Month.concat(getContext().getStringProperty("Month"));

		String PlannedCPH = data.getText("PlannedCPH");
		DBValues = sql.CLRexecuteQuery(getContext(),
				PlannedCPH.replace("store", getContext().getStringProperty("store")).replace("Month", MonthName).replace("Division", getContext().getStringProperty("Division")));
		System.out.println("DB Value: " + DBValues);

		String PlannedCPHTotal = data.getText("PlannedCPHTotal");
		DBTotal = sql.CLRexecuteQuery(getContext(),
				PlannedCPHTotal.replace("store", getContext().getStringProperty("store")).replace("Month", MonthName).replace("Division", getContext().getStringProperty("Division")));
		System.out.println("DB Total: " + DBTotal);

		UIValues = pageWeeklyPlanningPO.UIResults(objMonthlyPlanningStoreObjects.MPTable, extentTest,
				objWeeklyPlanningStoreObjects.DepartmentNames, objMonthlyPlanningStoreObjects.PlannedCPH);
		pageMonthlyPlanningPO.CompareValueDecimalFields(extentTest, DBValues, UIValues);

		UITotal = pageMonthlyPlanningPO.GetTotal(extentTest, objMonthlyPlanningStoreObjects.TotalRow,
				objMonthlyPlanningStoreObjects.PlannedCPHTotal);
		pageMonthlyPlanningPO.CompareValueDecimalFields(extentTest, DBTotal, UITotal);
	}

	@Test(description = "Monthly Planning - Store, GrowthLY CPH - Current Month", priority = 57)
	public void GrowthLYCPH_CurrentMonth() throws Exception {
		pageWeeklyPlanningPO = new WeeklyPlanningStorePage().createPage(getDriver(), getContext(), log, status, data,
				getReport(), getReportLogger(), WeeklyPlanningStorePage.class);
		pageMonthlyPlanningPO = new MonthlyPlanningStorePage().createPage(getDriver(), getContext(), log, status, data,
				getReport(), getReportLogger(), MonthlyPlanningStorePage.class);
		TestStatus status = getTestStatus();
		OptionToView = getContext().getStringProperty("OptionToView");
		Month = pageWeeklyPlanningPO.MonthName(objWeeklyPlanningStoreObjects, extentTest,
				objMonthlyPlanningStoreObjects.CurrentMonth);
		String MonthName = Month.concat(getContext().getStringProperty("Month"));
		String LYMonthName = Month.concat(getContext().getStringProperty("LYMonth"));

		String GrowthLYCPH = data.getText("GrowthLYCPH");
		DBValues = sql.CLRexecuteQuery(getContext(),
				GrowthLYCPH.replace("store", getContext().getStringProperty("store")).replace("Month", MonthName)
						.replace("LYMnth", LYMonthName).replace("Division", getContext().getStringProperty("Division")));
		System.out.println("DB Value: " + DBValues);

		String GrowthLYCPHTotal = data.getText("GrowthLYCPHTotal");
		DBTotal = sql.CLRexecuteQuery(getContext(),
				GrowthLYCPHTotal.replace("store", getContext().getStringProperty("store")).replace("Month", MonthName)
						.replace("LYMnth", LYMonthName).replace("Division", getContext().getStringProperty("Division")));
		System.out.println("DB Total: " + DBTotal);

		UIValues = pageWeeklyPlanningPO.UIResults(objMonthlyPlanningStoreObjects.MPTable, extentTest,
				objWeeklyPlanningStoreObjects.DepartmentNames, objMonthlyPlanningStoreObjects.GrowthLYCPH);
		pageMonthlyPlanningPO.CompareValueDecimalFields(extentTest, DBValues, UIValues);

		UITotal = pageMonthlyPlanningPO.GetTotal(extentTest, objMonthlyPlanningStoreObjects.TotalRow,
				objMonthlyPlanningStoreObjects.GrowthLYCPHTotal);
		pageMonthlyPlanningPO.CompareValueDecimalFields(extentTest, DBTotal, UITotal);
	}

	@Test(description = "Monthly Planning - Store, Planned BDGT Rate - Current Month", priority = 58)
	public void PlannedBDGTRate_CurrentMonth() throws Exception {
		pageWeeklyPlanningPO = new WeeklyPlanningStorePage().createPage(getDriver(), getContext(), log, status, data,
				getReport(), getReportLogger(), WeeklyPlanningStorePage.class);
		pageMonthlyPlanningPO = new MonthlyPlanningStorePage().createPage(getDriver(), getContext(), log, status, data,
				getReport(), getReportLogger(), MonthlyPlanningStorePage.class);
		TestStatus status = getTestStatus();
		OptionToView = getContext().getStringProperty("OptionToView");
		Month = pageWeeklyPlanningPO.MonthName(objWeeklyPlanningStoreObjects, extentTest,
				objMonthlyPlanningStoreObjects.CurrentMonth);
		String MonthName = Month.concat(getContext().getStringProperty("Month"));
		String PositiveFactor = data.getText("PositiveFactor");
		DBPstive = sql.CLRexecuteQuery(getContext(),
				PositiveFactor.replace("Region", getContext().getStringProperty("Region")).replace("Month",
						getContext().getStringProperty("MonthToTest")));
		System.out.println("PositveFactor: " + DBPstive);

		String NegativeFactor = data.getText("NegativeFactor");
		DBNgtive = sql.CLRexecuteQuery(getContext(),
				NegativeFactor.replace("Region", getContext().getStringProperty("Region")).replace("Month",
						getContext().getStringProperty("MonthToTest")));
		System.out.println("NegativeFactor: " + DBNgtive);
		String PositiveFactorTtl = data.getText("PositiveFactorTotal");
		DBPstiveTtl = sql.CLRexecuteQuery(getContext(),
				PositiveFactor.replace("Region", getContext().getStringProperty("Region")).replace("Month",
						getContext().getStringProperty("MonthToTest")));
		System.out.println("PositveFactor: " + DBPstiveTtl);

		String NegativeFactorTtl = data.getText("NegativeFactorTotal");
		DBNgtiveTtl = sql.CLRexecuteQuery(getContext(),
				NegativeFactor.replace("Region", getContext().getStringProperty("Region")).replace("Month",
						getContext().getStringProperty("MonthToTest")));
		System.out.println("NegativeFactor: " + DBNgtiveTtl);
		if (OptionToView.contains("Budget")) {
			pageMonthlyPlanningPO.PlannedSalestoBDGTRateforStore("RowWiseData", objMonthlyPlanningStoreObjects,
					extentTest, objMonthlyPlanningStoreObjects.Week, objMonthlyPlanningStoreObjects.BDGTRate,
					objMonthlyPlanningStoreObjects.BudgetWagesPercentage, objMonthlyPlanningStoreObjects.PlannedSales,
					objMonthlyPlanningStoreObjects.BudgetSales, objMonthlyPlanningStoreObjects.PlannedWages,
					objMonthlyPlanningStoreObjects.BudgetWages, DBPstive, DBNgtive, DBPstiveTtl, DBNgtiveTtl);
			pageMonthlyPlanningPO.PlannedSalestoBDGTRateforStore("FinalTotal", objMonthlyPlanningStoreObjects,
					extentTest, objMonthlyPlanningStoreObjects.TotalRowName, objMonthlyPlanningStoreObjects.BDGTRateTtl,
					objMonthlyPlanningStoreObjects.BudgetWagesPercentageTtl,
					objMonthlyPlanningStoreObjects.PlannedSalesTtl, objMonthlyPlanningStoreObjects.BudgetSalesTtl,
					objMonthlyPlanningStoreObjects.PlannedWagesTtl, objMonthlyPlanningStoreObjects.BudgetWagesTtl,
					DBPstive, DBNgtive, DBPstiveTtl, DBNgtiveTtl);
		}
		if (OptionToView.contains("Forecast")) {
			pageMonthlyPlanningPO.PlannedSalestoBDGTRateforStore("RowWiseData", objMonthlyPlanningStoreObjects,
					extentTest, objMonthlyPlanningStoreObjects.Week, objMonthlyPlanningStoreObjects.FCSTRate,
					objMonthlyPlanningStoreObjects.ForecastWagesPercentage, objMonthlyPlanningStoreObjects.PlannedSales,
					objMonthlyPlanningStoreObjects.ForecastSales, objMonthlyPlanningStoreObjects.PlannedWages,
					objMonthlyPlanningStoreObjects.ForecastWages, DBPstive, DBNgtive, DBPstiveTtl, DBNgtiveTtl);
			pageMonthlyPlanningPO.PlannedSalestoBDGTRateforStore("FinalTotal", objMonthlyPlanningStoreObjects,
					extentTest, objMonthlyPlanningStoreObjects.TotalRowName, objMonthlyPlanningStoreObjects.FCSTRateTtl,
					objMonthlyPlanningStoreObjects.ForecastWagesPercentageTtl,
					objMonthlyPlanningStoreObjects.PlannedSalesTtl, objMonthlyPlanningStoreObjects.ForecastSalesTtl,
					objMonthlyPlanningStoreObjects.PlannedWagesTtl, objMonthlyPlanningStoreObjects.ForecastWagesTtl,
					DBPstive, DBNgtive, DBPstiveTtl, DBNgtiveTtl);
		}
	}

	/*
	 * @Test(description =
	 * "Monthly Planning - Store, Budget and Forecast Rate - Current Month",priority
	 * =58) public void PlannedBDGTRateandFCSTRateCurrentMonth() throws Exception {
	 * pageWeeklyPlanningPO = new WeeklyPlanningStorePage().createPage(getDriver(),
	 * getContext(), log, status, data,getReport(), getReportLogger(),
	 * WeeklyPlanningStorePage.class); pageMonthlyPlanningPO = new
	 * MonthlyPlanningStorePage().createPage(getDriver(), getContext(), log, status,
	 * data,getReport(), getReportLogger(), MonthlyPlanningStorePage.class);
	 * TestStatus status = getTestStatus(); OptionToView =
	 * getContext().getStringProperty("OptionToView"); Month=
	 * pageWeeklyPlanningPO.MonthName(objWeeklyPlanningStoreObjects,
	 * extentTest,objMonthlyPlanningStoreObjects.CurrentMonth); String MonthName =
	 * Month.concat(getContext().getStringProperty("Month"));
	 * if(OptionToView.contains("Budget")) { String Budget =
	 * data.getText("PlannedBudgetRate"); DBValues =
	 * sql.CLRexecuteQuery(getContext(), Budget.replace("store",
	 * getContext().getStringProperty("store")).replace("Month",MonthName)
	 * 
	 * .replace("Area",getContext().getStringProperty("Area"))
	 * .replace("Zone",getContext().getStringProperty("Zone"))
	 * .replace("Week",getContext().getStringProperty("SelectFiscalWeek"))
	 * .replace("FinWeek",getContext().getStringProperty("FinWk"))
	 * .replace("Year",getContext().getStringProperty("Year"))
	 * .replace("Region",getContext().getStringProperty("Region"))
	 * .replace("Division",getContext().getStringProperty("Division")));
	 * System.out.println("DB Value: "+DBValues);
	 * 
	 * String BudgetTotal = data.getText("PlannedBudgetRateTotal"); DBTotal =
	 * sql.CLRexecuteQuery(getContext(), BudgetTotal.replace("store",
	 * getContext().getStringProperty("store")).replace("Month",MonthName)
	 * 
	 * .replace("Area",getContext().getStringProperty("Area"))
	 * .replace("Zone",getContext().getStringProperty("Zone"))
	 * .replace("Week",getContext().getStringProperty("SelectFiscalWeek"))
	 * .replace("FinWeek",getContext().getStringProperty("FinWk"))
	 * .replace("Year",getContext().getStringProperty("Year"))
	 * .replace("Region",getContext().getStringProperty("Region"))
	 * .replace("Division",getContext().getStringProperty("Division")));
	 * System.out.println("DB Total: "+DBTotal);
	 * 
	 * UIValues =
	 * pageWeeklyPlanningPO.UIResults(objMonthlyPlanningStoreObjects.MPTable,
	 * extentTest,objWeeklyPlanningStoreObjects.DepartmentNames,
	 * objMonthlyPlanningStoreObjects.BDGTRate);
	 * pageMonthlyPlanningPO.CompareValueDecimalFields(extentTest,DBValues,UIValues)
	 * ;
	 * 
	 * UITotal =
	 * pageMonthlyPlanningPO.GetTotal(extentTest,objMonthlyPlanningStoreObjects.
	 * TotalRow,objMonthlyPlanningStoreObjects.BDGTRateTot);
	 * pageMonthlyPlanningPO.CompareValueDecimalFields(extentTest,DBTotal,UITotal);
	 * } if(OptionToView.contains("Forecast")) { String Forecast =
	 * data.getText("PlannedForecsatRate"); DBValues =
	 * sql.CLRexecuteQuery(getContext(), Forecast.replace("store",
	 * getContext().getStringProperty("store")).replace("Month",MonthName)
	 * .replace("store",getContext().getStringProperty("store"))
	 * .replace("Area",getContext().getStringProperty("Area"))
	 * .replace("Zone",getContext().getStringProperty("Zone"))
	 * .replace("Week",getContext().getStringProperty("SelectFiscalWeek"))
	 * .replace("FinWeek",getContext().getStringProperty("FinWk"))
	 * .replace("Year",getContext().getStringProperty("Year"))
	 * .replace("Region",getContext().getStringProperty("Region"))
	 * .replace("Division",getContext().getStringProperty("Division")));
	 * System.out.println("DB Value: "+DBValues);
	 * 
	 * String ForecastTotal = data.getText("PlannedForecsatRateTotal"); DBTotal =
	 * sql.CLRexecuteQuery(getContext(), ForecastTotal.replace("store",
	 * getContext().getStringProperty("store")).replace("Month",MonthName)
	 * .replace("store",getContext().getStringProperty("store"))
	 * .replace("Area",getContext().getStringProperty("Area"))
	 * .replace("Zone",getContext().getStringProperty("Zone"))
	 * .replace("Week",getContext().getStringProperty("SelectFiscalWeek"))
	 * .replace("FinWeek",getContext().getStringProperty("FinWk"))
	 * .replace("Year",getContext().getStringProperty("Year"))
	 * .replace("Region",getContext().getStringProperty("Region"))
	 * .replace("Division",getContext().getStringProperty("Division")));
	 * System.out.println("DB Total: "+DBTotal);
	 * 
	 * UIValues =
	 * pageWeeklyPlanningPO.UIResults(objMonthlyPlanningStoreObjects.MPTable,
	 * extentTest,objWeeklyPlanningStoreObjects.DepartmentNames,
	 * objMonthlyPlanningStoreObjects.FCSTRate);
	 * pageMonthlyPlanningPO.CompareValueDecimalFields(extentTest,DBValues,UIValues)
	 * ;
	 * 
	 * UITotal =
	 * pageMonthlyPlanningPO.GetTotal(extentTest,objMonthlyPlanningStoreObjects.
	 * TotalRow,objMonthlyPlanningStoreObjects.FCSTRateTot);
	 * pageMonthlyPlanningPO.CompareValueDecimalFields(extentTest,DBTotal,UITotal);
	 * } }
	 */

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
		//initializeTestcontext();

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
		BaseObj = PageFactory.initElements(this.getDriver(), BasePage.class);
		common = PageFactory.initElements(this.getDriver(), CommonFunctions.class);
		storeprop = PageFactory.initElements(this.getDriver(), StoreProperties.class);
		objWeeklyPlanningStoreObjects = PageFactory.initElements(this.getDriver(), WeeklyPlanningStoreObjects.class);
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
			data.loadTestDataXML(System.getProperty("user.dir") + "\\TestData\\WLP\\MonthlyPlanningStore.xml");
			customreport.createExtentReport();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}