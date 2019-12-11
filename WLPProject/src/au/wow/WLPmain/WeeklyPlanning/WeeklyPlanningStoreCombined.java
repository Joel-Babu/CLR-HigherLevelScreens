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
import org.testng.annotations.Test;

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

public class WeeklyPlanningStoreCombined extends TestBase {

	protected Logger log = LogManager.getLogger(WeeklyPlanningStoreCombined.class);
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
	List<List<Object>> DBValues, DBTotalSFD;
	List<List<Object>> DBTotal;
	List<List<Object>> UIValues;
	List<List<Object>> UITotal, UITotalSFD;
	String Week, Name, Year, YrName, LYYr, OptionToView, Wk, HWk1, HWk2, HWk3, HWk4;
	int LYYear, HistWk;
	String[] WeekName, YearName;

	public WeeklyPlanningStoreCombined() {
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
		pageWeeklyPlanningPO = loginPage.navToWeeklyPlanningStorePg(getContext().getStringProperty("store"));
	}

	// Sales
	@Test(description = "Weekly Planning - Store, Sales history - First Week", priority = 1)
	public void SalesHistoryFirstWeek() throws Exception {
		pageWeeklyPlanningPO = new WeeklyPlanningStorePage().createPage(getDriver(), getContext(), log, status, data,
				getReport(), getReportLogger(), WeeklyPlanningStorePage.class);
		pageMonthlyPlanningGroupPO = new MonthlyPlanningGroupPage().createPage(getDriver(), getContext(), log, status,
				data, getReport(), getReportLogger(), MonthlyPlanningGroupPage.class);
		TestStatus status = getTestStatus();
		System.out.println("Entering into TestCase");
		OptionToView = getContext().getStringProperty("OptionToView");
		pageWeeklyPlanningPO.SelectWeekFromDropdown(objWeeklyPlanningStoreObjects, extentTest,
				getContext().getStringProperty("SelectFiscalWeek"));

		pageWeeklyPlanningPO.selectPage(extentTest, objWeeklyPlanningStoreObjects.menuBar,
				objWeeklyPlanningStoreObjects.planningScreens, "Planning Screens");
		pageWeeklyPlanningPO.selectPage(extentTest, objWeeklyPlanningStoreObjects.menuBar,
				objWeeklyPlanningStoreObjects.weeklyPlanning, "Weekly Planning");
		pageWeeklyPlanningPO.selectPage(extentTest, objWeeklyPlanningStoreObjects.menuBar,
				objWeeklyPlanningStoreObjects.screenToView, "Store");
		/*pageWeeklyPlanningPO.verifyPageName(extentTest, objWeeklyPlanningStoreObjects.DataFrame,
				objWeeklyPlanningStoreObjects.pageName, getContext().getStringProperty("WeeklyPlanningStore"));*/

		pageWeeklyPlanningPO.SelectHistoryDropdownToView(objWeeklyPlanningStoreObjects, extentTest, "Sales");

		Name = pageWeeklyPlanningPO.MonthName(objWeeklyPlanningStoreObjects, extentTest,
				objWeeklyPlanningStoreObjects.FirstWeekofMonthColumnName);
		if (OptionToView.contains("Budget"))
			YrName = pageWeeklyPlanningPO.MonthName(objWeeklyPlanningStoreObjects, extentTest,
					objWeeklyPlanningStoreObjects.CurrentWeekBDGTNT);
		if (OptionToView.contains("Forecast"))
			YrName = pageWeeklyPlanningPO.MonthName(objWeeklyPlanningStoreObjects, extentTest,
					objWeeklyPlanningStoreObjects.CurrentWeekFCSTNT);
		WeekName = Name.split(" ");
		YearName = YrName.split(" ");
		Week = WeekName[0];
		Year = getContext().getStringProperty("FinYr");

		Report_AddStep("testcase", " SALES HISTORY - FIRST WEEK NEW TABLE", "", "", "");
		extentTest.log(LogStatus.INFO, "SALES HISTORY - FIRST WEEK NEW TABLE");

		String SalesHistoryNT = data.getText("SalesHistoryNT");
		DBTotal = sql.CLRexecuteQuery(getContext(),
				SalesHistoryNT.replace("store", getContext().getStringProperty("store"))
						.replace("Area", getContext().getStringProperty("Area"))
						.replace("Dept", getContext().getStringProperty("Longlife"))
						.replace("Zone", getContext().getStringProperty("Zone")).replace("FinWk", Week)
						.replace("FinYr", getContext().getStringProperty("FinYr")).replace("Division", getContext().getStringProperty("Division")));
		System.out.println("DB Total: " + DBTotal);

		UITotal = pageMonthlyPlanningGroupPO.GetTotal(extentTest, MonthlyPlanningGroupObjects.RowNameTotalLL,
				WeeklyPlanningStoreObjects.FirstWeekofMonthLL);
		pageMonthlyPlanningPO.CompareValues(extentTest, DBTotal, UITotal);

		DBTotalSFD = sql.CLRexecuteQuery(getContext(),
				SalesHistoryNT.replace("store", getContext().getStringProperty("store"))
						.replace("Area", getContext().getStringProperty("Area"))
						.replace("Dept", getContext().getStringProperty("SeaFoodDeli"))
						.replace("Zone", getContext().getStringProperty("Zone")).replace("FinWk", Week)
						.replace("FinYr", getContext().getStringProperty("FinYr")).replace("Division", getContext().getStringProperty("Division")));
		System.out.println("DB Total: " + DBTotalSFD);

		UITotalSFD = pageMonthlyPlanningGroupPO.GetTotal(extentTest, MonthlyPlanningGroupObjects.RowNameTotalSFD,
				WeeklyPlanningStoreObjects.FirstWeekofMonthSFD);
		pageMonthlyPlanningPO.CompareValues(extentTest, DBTotalSFD, UITotalSFD);
	}

	@Test(description = "Weekly Planning - Store, Sales history - Second Week", priority = 2)
	public void SalesHistorySecondWeek() throws Exception {
		pageWeeklyPlanningPO = new WeeklyPlanningStorePage().createPage(getDriver(), getContext(), log, status, data,
				getReport(), getReportLogger(), WeeklyPlanningStorePage.class);
		pageMonthlyPlanningGroupPO = new MonthlyPlanningGroupPage().createPage(getDriver(), getContext(), log, status,
				data, getReport(), getReportLogger(), MonthlyPlanningGroupPage.class);
		TestStatus status = getTestStatus();
		System.out.println("Entering into TestCase");
		pageWeeklyPlanningPO.SelectHistoryDropdownToView(objWeeklyPlanningStoreObjects, extentTest, "Sales");
		Name = pageWeeklyPlanningPO.MonthName(objWeeklyPlanningStoreObjects, extentTest,
				objWeeklyPlanningStoreObjects.SecondWeekofMonthColumnName);
		YrName = pageWeeklyPlanningPO.MonthName(objWeeklyPlanningStoreObjects, extentTest,
				objWeeklyPlanningStoreObjects.CurrentWeekBDGTNT);
		WeekName = Name.split(" ");
		YearName = YrName.split(" ");
		Week = WeekName[0];
		Year = getContext().getStringProperty("FinYr");

		Report_AddStep("testcase", " SALES HISTORY - SECOND WEEK NEW TABLE", "", "", "");
		extentTest.log(LogStatus.INFO, "SALES HISTORY - SECOND WEEK NEW TABLE");

		String SalesHistoryNT = data.getText("SalesHistoryNT");
		DBTotal = sql.CLRexecuteQuery(getContext(),
				SalesHistoryNT.replace("store", getContext().getStringProperty("store"))
						.replace("Area", getContext().getStringProperty("Area"))
						.replace("Dept", getContext().getStringProperty("Longlife"))
						.replace("Zone", getContext().getStringProperty("Zone")).replace("FinWk", Week)
						.replace("FinYr", getContext().getStringProperty("FinYr")).replace("Division", getContext().getStringProperty("Division")));
		System.out.println("DB Total: " + DBTotal);

		UITotal = pageMonthlyPlanningGroupPO.GetTotal(extentTest, MonthlyPlanningGroupObjects.RowNameTotalLL,
				WeeklyPlanningStoreObjects.SecondWeekofMonthLL);
		pageMonthlyPlanningPO.CompareValues(extentTest, DBTotal, UITotal);

		DBTotalSFD = sql.CLRexecuteQuery(getContext(),
				SalesHistoryNT.replace("store", getContext().getStringProperty("store"))
						.replace("Area", getContext().getStringProperty("Area"))
						.replace("Dept", getContext().getStringProperty("SeaFoodDeli"))
						.replace("Zone", getContext().getStringProperty("Zone")).replace("FinWk", Week)
						.replace("FinYr", getContext().getStringProperty("FinYr")).replace("Division", getContext().getStringProperty("Division")));
		System.out.println("DB Total: " + DBTotalSFD);
		UITotalSFD = pageMonthlyPlanningGroupPO.GetTotal(extentTest, MonthlyPlanningGroupObjects.RowNameTotalSFD,
				WeeklyPlanningStoreObjects.SecondWeekofMonthSFD);
		pageMonthlyPlanningPO.CompareValues(extentTest, DBTotalSFD, UITotalSFD);
	}

	@Test(description = "Weekly Planning - Store, Sales history - Third Week", priority = 3)
	public void SalesHistoryThirdWeek() throws Exception {
		pageWeeklyPlanningPO = new WeeklyPlanningStorePage().createPage(getDriver(), getContext(), log, status, data,
				getReport(), getReportLogger(), WeeklyPlanningStorePage.class);
		pageMonthlyPlanningGroupPO = new MonthlyPlanningGroupPage().createPage(getDriver(), getContext(), log, status,
				data, getReport(), getReportLogger(), MonthlyPlanningGroupPage.class);
		TestStatus status = getTestStatus();
		System.out.println("Entering into TestCase");
		pageWeeklyPlanningPO.SelectHistoryDropdownToView(objWeeklyPlanningStoreObjects, extentTest, "Sales");
		Name = pageWeeklyPlanningPO.MonthName(objWeeklyPlanningStoreObjects, extentTest,
				objWeeklyPlanningStoreObjects.ThirdWeekofMonthColumnName);
		YrName = pageWeeklyPlanningPO.MonthName(objWeeklyPlanningStoreObjects, extentTest,
				objWeeklyPlanningStoreObjects.CurrentWeekBDGTNT);
		WeekName = Name.split(" ");
		YearName = YrName.split(" ");
		Week = WeekName[0];
		Year = getContext().getStringProperty("FinYr");

		Report_AddStep("testcase", " SALES HISTORY - THIRD WEEK NEW TABLE", "", "", "");
		extentTest.log(LogStatus.INFO, "SALES HISTORY - THIRD WEEK NEW TABLE");

		String SalesHistoryNT = data.getText("SalesHistoryNT");
		DBTotal = sql.CLRexecuteQuery(getContext(),
				SalesHistoryNT.replace("store", getContext().getStringProperty("store"))
						.replace("Area", getContext().getStringProperty("Area"))
						.replace("Dept", getContext().getStringProperty("Longlife"))
						.replace("Zone", getContext().getStringProperty("Zone")).replace("FinWk", Week)
						.replace("FinYr", getContext().getStringProperty("FinYr")).replace("Division", getContext().getStringProperty("Division")));
		System.out.println("DB Total: " + DBTotal);

		UITotal = pageMonthlyPlanningGroupPO.GetTotal(extentTest, MonthlyPlanningGroupObjects.RowNameTotalLL,
				WeeklyPlanningStoreObjects.ThirdWeekofMonthLL);
		pageMonthlyPlanningPO.CompareValues(extentTest, DBTotal, UITotal);

		DBTotalSFD = sql.CLRexecuteQuery(getContext(),
				SalesHistoryNT.replace("store", getContext().getStringProperty("store"))
						.replace("Area", getContext().getStringProperty("Area"))
						.replace("Dept", getContext().getStringProperty("SeaFoodDeli"))
						.replace("Zone", getContext().getStringProperty("Zone")).replace("FinWk", Week)
						.replace("FinYr", getContext().getStringProperty("FinYr")).replace("Division", getContext().getStringProperty("Division")));
		System.out.println("DB Total: " + DBTotalSFD);
		UITotalSFD = pageMonthlyPlanningGroupPO.GetTotal(extentTest, MonthlyPlanningGroupObjects.RowNameTotalSFD,
				WeeklyPlanningStoreObjects.ThirdWeekofMonthSFD);
		pageMonthlyPlanningPO.CompareValues(extentTest, DBTotalSFD, UITotalSFD);
	}

	@Test(description = "Weekly Planning - Store, Sales history - Fourth Week", priority = 4)
	public void SalesHistoryFourthWeek() throws Exception {
		pageWeeklyPlanningPO = new WeeklyPlanningStorePage().createPage(getDriver(), getContext(), log, status, data,
				getReport(), getReportLogger(), WeeklyPlanningStorePage.class);
		pageMonthlyPlanningGroupPO = new MonthlyPlanningGroupPage().createPage(getDriver(), getContext(), log, status,
				data, getReport(), getReportLogger(), MonthlyPlanningGroupPage.class);
		TestStatus status = getTestStatus();
		System.out.println("Entering into TestCase");
		pageWeeklyPlanningPO.SelectHistoryDropdownToView(objWeeklyPlanningStoreObjects, extentTest, "Sales");
		Name = pageWeeklyPlanningPO.MonthName(objWeeklyPlanningStoreObjects, extentTest,
				objWeeklyPlanningStoreObjects.FourWeekofMonthColumnName);
		YrName = pageWeeklyPlanningPO.MonthName(objWeeklyPlanningStoreObjects, extentTest,
				objWeeklyPlanningStoreObjects.CurrentWeekBDGTNT);
		WeekName = Name.split(" ");
		YearName = YrName.split(" ");
		Week = WeekName[0];
		Year = getContext().getStringProperty("FinYr");

		Report_AddStep("testcase", " SALES HISTORY - FOURTH WEEK NEW TABLE", "", "", "");
		extentTest.log(LogStatus.INFO, "SALES HISTORY - FOURTH WEEK NEW TABLE");

		String SalesHistoryNT = data.getText("SalesHistoryNT");
		DBTotal = sql.CLRexecuteQuery(getContext(),
				SalesHistoryNT.replace("store", getContext().getStringProperty("store"))
						.replace("Area", getContext().getStringProperty("Area"))
						.replace("Dept", getContext().getStringProperty("Longlife"))
						.replace("Zone", getContext().getStringProperty("Zone")).replace("FinWk", Week)
						.replace("FinYr", getContext().getStringProperty("FinYr")).replace("Division", getContext().getStringProperty("Division")));
		System.out.println("DB Total: " + DBTotal);

		UITotal = pageMonthlyPlanningGroupPO.GetTotal(extentTest, MonthlyPlanningGroupObjects.RowNameTotalLL,
				WeeklyPlanningStoreObjects.FourWeekofMonthLL);
		pageMonthlyPlanningPO.CompareValues(extentTest, DBTotal, UITotal);

		DBTotalSFD = sql.CLRexecuteQuery(getContext(),
				SalesHistoryNT.replace("store", getContext().getStringProperty("store"))
						.replace("Area", getContext().getStringProperty("Area"))
						.replace("Dept", getContext().getStringProperty("SeaFoodDeli"))
						.replace("Zone", getContext().getStringProperty("Zone")).replace("FinWk", Week)
						.replace("FinYr", getContext().getStringProperty("FinYr")).replace("Division", getContext().getStringProperty("Division")));
		System.out.println("DB Total: " + DBTotalSFD);
		UITotalSFD = pageMonthlyPlanningGroupPO.GetTotal(extentTest, MonthlyPlanningGroupObjects.RowNameTotalSFD,
				WeeklyPlanningStoreObjects.FourWeekofMonthSFD);
		pageMonthlyPlanningPO.CompareValues(extentTest, DBTotalSFD, UITotalSFD);
	}

	// Wages

	@Test(description = "Weekly Planning - Store, Wages history - First Week", priority = 5)
	public void WagesHistoryFirstWeek() throws Exception {
		pageWeeklyPlanningPO = new WeeklyPlanningStorePage().createPage(getDriver(), getContext(), log, status, data,
				getReport(), getReportLogger(), WeeklyPlanningStorePage.class);
		pageMonthlyPlanningGroupPO = new MonthlyPlanningGroupPage().createPage(getDriver(), getContext(), log, status,
				data, getReport(), getReportLogger(), MonthlyPlanningGroupPage.class);
		TestStatus status = getTestStatus();
		System.out.println("Entering into TestCase");
		pageWeeklyPlanningPO.SelectHistoryDropdownToView(objWeeklyPlanningStoreObjects, extentTest, "Wages");
		Name = pageWeeklyPlanningPO.MonthName(objWeeklyPlanningStoreObjects, extentTest,
				objWeeklyPlanningStoreObjects.FirstWeekofMonthColumnName);
		YrName = pageWeeklyPlanningPO.MonthName(objWeeklyPlanningStoreObjects, extentTest,
				objWeeklyPlanningStoreObjects.CurrentWeekBDGTNT);
		WeekName = Name.split(" ");
		YearName = YrName.split(" ");
		Week = WeekName[0];
		Year = getContext().getStringProperty("FinYr");

		String WagesHistoryNT = data.getText("WagesHistoryNT");
		DBTotal = sql.executeQuery(getContext(),
				WagesHistoryNT.replace("store", getContext().getStringProperty("store"))
						.replace("Area", getContext().getStringProperty("Area"))
						.replace("Dept", getContext().getStringProperty("Longlife"))
						.replace("Zone", getContext().getStringProperty("Zone")).replace("FinWk", Week)
						.replace("FinYr", getContext().getStringProperty("FinYr")).replace("Division", getContext().getStringProperty("Division")));
		System.out.println("DB Total: " + DBTotal);

		UITotal = pageMonthlyPlanningGroupPO.GetTotal(extentTest, MonthlyPlanningGroupObjects.RowNameTotalLL,
				WeeklyPlanningStoreObjects.FirstWeekofMonthLL);
		pageMonthlyPlanningPO.CompareValues(extentTest, DBTotal, UITotal);

		DBTotalSFD = sql.executeQuery(getContext(),
				WagesHistoryNT.replace("store", getContext().getStringProperty("store"))
						.replace("Area", getContext().getStringProperty("Area"))
						.replace("Dept", getContext().getStringProperty("SeaFoodDeli"))
						.replace("Zone", getContext().getStringProperty("Zone")).replace("FinWk", Week)
						.replace("FinYr", getContext().getStringProperty("FinYr")).replace("Division", getContext().getStringProperty("Division")));
		System.out.println("DB Total: " + DBTotalSFD);

		UITotalSFD = pageMonthlyPlanningGroupPO.GetTotal(extentTest, MonthlyPlanningGroupObjects.RowNameTotalSFD,
				WeeklyPlanningStoreObjects.FirstWeekofMonthSFD);
		pageMonthlyPlanningPO.CompareValues(extentTest, DBTotalSFD, UITotalSFD);
	}

	@Test(description = "Weekly Planning - Store, Wages history - Second Week", priority = 6)
	public void WagesHistorySecondWeek() throws Exception {
		pageWeeklyPlanningPO = new WeeklyPlanningStorePage().createPage(getDriver(), getContext(), log, status, data,
				getReport(), getReportLogger(), WeeklyPlanningStorePage.class);
		pageMonthlyPlanningGroupPO = new MonthlyPlanningGroupPage().createPage(getDriver(), getContext(), log, status,
				data, getReport(), getReportLogger(), MonthlyPlanningGroupPage.class);
		TestStatus status = getTestStatus();
		System.out.println("Entering into TestCase");
		pageWeeklyPlanningPO.SelectHistoryDropdownToView(objWeeklyPlanningStoreObjects, extentTest, "Wages");
		Name = pageWeeklyPlanningPO.MonthName(objWeeklyPlanningStoreObjects, extentTest,
				objWeeklyPlanningStoreObjects.SecondWeekofMonthColumnName);
		YrName = pageWeeklyPlanningPO.MonthName(objWeeklyPlanningStoreObjects, extentTest,
				objWeeklyPlanningStoreObjects.CurrentWeekBDGTNT);
		WeekName = Name.split(" ");
		YearName = YrName.split(" ");
		Week = WeekName[0];
		Year = getContext().getStringProperty("FinYr");

		String WagesHistoryNT = data.getText("WagesHistoryNT");
		DBTotal = sql.executeQuery(getContext(),
				WagesHistoryNT.replace("store", getContext().getStringProperty("store"))
						.replace("Area", getContext().getStringProperty("Area"))
						.replace("Dept", getContext().getStringProperty("Longlife"))
						.replace("Zone", getContext().getStringProperty("Zone")).replace("FinWk", Week)
						.replace("FinYr", getContext().getStringProperty("FinYr")).replace("Division", getContext().getStringProperty("Division")));
		System.out.println("DB Total: " + DBTotal);

		UITotal = pageMonthlyPlanningGroupPO.GetTotal(extentTest, MonthlyPlanningGroupObjects.RowNameTotalLL,
				WeeklyPlanningStoreObjects.SecondWeekofMonthLL);
		pageMonthlyPlanningPO.CompareValues(extentTest, DBTotal, UITotal);

		DBTotalSFD = sql.executeQuery(getContext(),
				WagesHistoryNT.replace("store", getContext().getStringProperty("store"))
						.replace("Area", getContext().getStringProperty("Area"))
						.replace("Dept", getContext().getStringProperty("SeaFoodDeli"))
						.replace("Zone", getContext().getStringProperty("Zone")).replace("FinWk", Week)
						.replace("FinYr", getContext().getStringProperty("FinYr")).replace("Division", getContext().getStringProperty("Division")));
		System.out.println("DB Total: " + DBTotalSFD);

		UITotalSFD = pageMonthlyPlanningGroupPO.GetTotal(extentTest, MonthlyPlanningGroupObjects.RowNameTotalSFD,
				WeeklyPlanningStoreObjects.SecondWeekofMonthSFD);
		pageMonthlyPlanningPO.CompareValues(extentTest, DBTotalSFD, UITotalSFD);
	}

	@Test(description = "Weekly Planning - Store, Wages history - Third Week", priority = 7)
	public void WagesHistoryThirdWeek() throws Exception {
		pageWeeklyPlanningPO = new WeeklyPlanningStorePage().createPage(getDriver(), getContext(), log, status, data,
				getReport(), getReportLogger(), WeeklyPlanningStorePage.class);
		pageMonthlyPlanningGroupPO = new MonthlyPlanningGroupPage().createPage(getDriver(), getContext(), log, status,
				data, getReport(), getReportLogger(), MonthlyPlanningGroupPage.class);
		TestStatus status = getTestStatus();
		System.out.println("Entering into TestCase");
		pageWeeklyPlanningPO.SelectHistoryDropdownToView(objWeeklyPlanningStoreObjects, extentTest, "Wages");
		Name = pageWeeklyPlanningPO.MonthName(objWeeklyPlanningStoreObjects, extentTest,
				objWeeklyPlanningStoreObjects.ThirdWeekofMonthColumnName);
		YrName = pageWeeklyPlanningPO.MonthName(objWeeklyPlanningStoreObjects, extentTest,
				objWeeklyPlanningStoreObjects.CurrentWeekBDGTNT);
		WeekName = Name.split(" ");
		YearName = YrName.split(" ");
		Week = WeekName[0];
		Year = getContext().getStringProperty("FinYr");

		String WagesHistoryNT = data.getText("WagesHistoryNT");
		DBTotal = sql.executeQuery(getContext(),
				WagesHistoryNT.replace("store", getContext().getStringProperty("store"))
						.replace("Area", getContext().getStringProperty("Area"))
						.replace("Dept", getContext().getStringProperty("Longlife"))
						.replace("Zone", getContext().getStringProperty("Zone")).replace("FinWk", Week)
						.replace("FinYr", getContext().getStringProperty("FinYr")).replace("Division", getContext().getStringProperty("Division")));
		System.out.println("DB Total: " + DBTotal);

		UITotal = pageMonthlyPlanningGroupPO.GetTotal(extentTest, MonthlyPlanningGroupObjects.RowNameTotalLL,
				WeeklyPlanningStoreObjects.ThirdWeekofMonthLL);
		pageMonthlyPlanningPO.CompareValues(extentTest, DBTotal, UITotal);

		DBTotalSFD = sql.executeQuery(getContext(),
				WagesHistoryNT.replace("store", getContext().getStringProperty("store"))
						.replace("Area", getContext().getStringProperty("Area"))
						.replace("Dept", getContext().getStringProperty("SeaFoodDeli"))
						.replace("Zone", getContext().getStringProperty("Zone")).replace("FinWk", Week)
						.replace("FinYr", getContext().getStringProperty("FinYr")).replace("Division", getContext().getStringProperty("Division")));
		System.out.println("DB Total: " + DBTotal);

		UITotalSFD = pageMonthlyPlanningGroupPO.GetTotal(extentTest, MonthlyPlanningGroupObjects.RowNameTotalSFD,
				WeeklyPlanningStoreObjects.ThirdWeekofMonthSFD);
		pageMonthlyPlanningPO.CompareValues(extentTest, DBTotalSFD, UITotalSFD);
	}

	@Test(description = "Weekly Planning - Store, Wages history - Fourth Week", priority = 8)
	public void WagesHistoryFourthWeek() throws Exception {
		pageWeeklyPlanningPO = new WeeklyPlanningStorePage().createPage(getDriver(), getContext(), log, status, data,
				getReport(), getReportLogger(), WeeklyPlanningStorePage.class);
		pageMonthlyPlanningGroupPO = new MonthlyPlanningGroupPage().createPage(getDriver(), getContext(), log, status,
				data, getReport(), getReportLogger(), MonthlyPlanningGroupPage.class);
		TestStatus status = getTestStatus();
		System.out.println("Entering into TestCase");
		pageWeeklyPlanningPO.SelectHistoryDropdownToView(objWeeklyPlanningStoreObjects, extentTest, "Wages");
		Name = pageWeeklyPlanningPO.MonthName(objWeeklyPlanningStoreObjects, extentTest,
				objWeeklyPlanningStoreObjects.FourWeekofMonthColumnName);
		YrName = pageWeeklyPlanningPO.MonthName(objWeeklyPlanningStoreObjects, extentTest,
				objWeeklyPlanningStoreObjects.CurrentWeekBDGTNT);
		WeekName = Name.split(" ");
		YearName = YrName.split(" ");
		Week = WeekName[0];
		Year = getContext().getStringProperty("FinYr");

		String WagesHistoryNT = data.getText("WagesHistoryNT");
		DBTotal = sql.executeQuery(getContext(),
				WagesHistoryNT.replace("store", getContext().getStringProperty("store"))
						.replace("Area", getContext().getStringProperty("Area"))
						.replace("Dept", getContext().getStringProperty("Longlife"))
						.replace("Zone", getContext().getStringProperty("Zone")).replace("FinWk", Week)
						.replace("FinYr", getContext().getStringProperty("FinYr")).replace("Division", getContext().getStringProperty("Division")));
		System.out.println("DB Total: " + DBTotal);

		UITotal = pageMonthlyPlanningGroupPO.GetTotal(extentTest, MonthlyPlanningGroupObjects.RowNameTotalLL,
				WeeklyPlanningStoreObjects.FourWeekofMonthLL);
		pageMonthlyPlanningPO.CompareValues(extentTest, DBTotal, UITotal);

		DBTotalSFD = sql.executeQuery(getContext(),
				WagesHistoryNT.replace("store", getContext().getStringProperty("store"))
						.replace("Area", getContext().getStringProperty("Area"))
						.replace("Dept", getContext().getStringProperty("SeaFoodDeli"))
						.replace("Zone", getContext().getStringProperty("Zone")).replace("FinWk", Week)
						.replace("FinYr", getContext().getStringProperty("FinYr")).replace("Division", getContext().getStringProperty("Division")));
		System.out.println("DB Total: " + DBTotalSFD);

		UITotalSFD = pageMonthlyPlanningGroupPO.GetTotal(extentTest, MonthlyPlanningGroupObjects.RowNameTotalSFD,
				WeeklyPlanningStoreObjects.FourWeekofMonthSFD);
		pageMonthlyPlanningPO.CompareValues(extentTest, DBTotalSFD, UITotalSFD);
	}

	// Items

	@Test(description = "Weekly Planning - Store, Items history - First Week", priority = 9)
	public void ItemsHistoryFirsthWeek() throws Exception {
		pageWeeklyPlanningPO = new WeeklyPlanningStorePage().createPage(getDriver(), getContext(), log, status, data,
				getReport(), getReportLogger(), WeeklyPlanningStorePage.class);
		pageMonthlyPlanningGroupPO = new MonthlyPlanningGroupPage().createPage(getDriver(), getContext(), log, status,
				data, getReport(), getReportLogger(), MonthlyPlanningGroupPage.class);
		TestStatus status = getTestStatus();
		System.out.println("Entering into TestCase");
		pageWeeklyPlanningPO.SelectHistoryDropdownToView(objWeeklyPlanningStoreObjects, extentTest, "Items");
		Name = pageWeeklyPlanningPO.MonthName(objWeeklyPlanningStoreObjects, extentTest,
				objWeeklyPlanningStoreObjects.FirstWeekofMonthColumnName);
		YrName = pageWeeklyPlanningPO.MonthName(objWeeklyPlanningStoreObjects, extentTest,
				objWeeklyPlanningStoreObjects.CurrentWeekBDGTNT);
		WeekName = Name.split(" ");
		YearName = YrName.split(" ");
		Week = WeekName[0];
		Year = getContext().getStringProperty("FinYr");

		String ItemsHistoryNT = data.getText("ItemsHistoryNT");
		DBTotal = sql.executeQuery(getContext(),
				ItemsHistoryNT.replace("store", getContext().getStringProperty("store"))
						.replace("Area", getContext().getStringProperty("Area"))
						.replace("Dept", getContext().getStringProperty("Longlife"))
						.replace("Zone", getContext().getStringProperty("Zone")).replace("FinWk", Week)
						.replace("FinYr", getContext().getStringProperty("FinYr")).replace("Division", getContext().getStringProperty("Division")));
		System.out.println("DB Total: " + DBTotal);

		UITotal = pageMonthlyPlanningGroupPO.GetTotal(extentTest, objMonthlyPlanningGroupObjects.RowNameTotalLL,
				objWeeklyPlanningStoreObjects.FirstWeekofMonthLL);
		pageMonthlyPlanningPO.CompareValues(extentTest, DBTotal, UITotal);

		DBTotalSFD = sql.executeQuery(getContext(),
				ItemsHistoryNT.replace("store", getContext().getStringProperty("store"))
						.replace("Area", getContext().getStringProperty("Area"))
						.replace("Dept", getContext().getStringProperty("SeaFoodDeli"))
						.replace("Zone", getContext().getStringProperty("Zone")).replace("FinWk", Week)
						.replace("FinYr", getContext().getStringProperty("FinYr")).replace("Division", getContext().getStringProperty("Division")));
		System.out.println("DB Total: " + DBTotal);

		UITotalSFD = pageMonthlyPlanningGroupPO.GetTotal(extentTest, objMonthlyPlanningGroupObjects.RowNameTotalSFD,
				objWeeklyPlanningStoreObjects.FirstWeekofMonthSFD);
		pageMonthlyPlanningPO.CompareValues(extentTest, DBTotalSFD, UITotalSFD);
	}

	@Test(description = "Weekly Planning - Store, Items history - Second Week", priority = 10)
	public void ItemsHistorySecondWeek() throws Exception {
		pageWeeklyPlanningPO = new WeeklyPlanningStorePage().createPage(getDriver(), getContext(), log, status, data,
				getReport(), getReportLogger(), WeeklyPlanningStorePage.class);
		pageMonthlyPlanningGroupPO = new MonthlyPlanningGroupPage().createPage(getDriver(), getContext(), log, status,
				data, getReport(), getReportLogger(), MonthlyPlanningGroupPage.class);
		TestStatus status = getTestStatus();
		System.out.println("Entering into TestCase");
		pageWeeklyPlanningPO.SelectHistoryDropdownToView(objWeeklyPlanningStoreObjects, extentTest, "Items");
		pageWeeklyPlanningPO.SelectHistoryDropdownToView(objWeeklyPlanningStoreObjects, extentTest, "Items");
		Name = pageWeeklyPlanningPO.MonthName(objWeeklyPlanningStoreObjects, extentTest,
				objWeeklyPlanningStoreObjects.SecondWeekofMonthColumnName);
		YrName = pageWeeklyPlanningPO.MonthName(objWeeklyPlanningStoreObjects, extentTest,
				objWeeklyPlanningStoreObjects.CurrentWeekBDGTNT);
		WeekName = Name.split(" ");
		YearName = YrName.split(" ");
		Week = WeekName[0];
		Year = getContext().getStringProperty("FinYr");

		String ItemsHistoryNT = data.getText("ItemsHistoryNT");
		DBTotal = sql.executeQuery(getContext(),
				ItemsHistoryNT.replace("store", getContext().getStringProperty("store"))
						.replace("Area", getContext().getStringProperty("Area"))
						.replace("Dept", getContext().getStringProperty("Longlife"))
						.replace("Zone", getContext().getStringProperty("Zone")).replace("FinWk", Week)
						.replace("FinYr", getContext().getStringProperty("FinYr")).replace("Division", getContext().getStringProperty("Division")));
		System.out.println("DB Total: " + DBTotal);

		UITotal = pageMonthlyPlanningGroupPO.GetTotal(extentTest, objMonthlyPlanningGroupObjects.RowNameTotalLL,
				objWeeklyPlanningStoreObjects.SecondWeekofMonthLL);
		pageMonthlyPlanningPO.CompareValues(extentTest, DBTotal, UITotal);

		DBTotalSFD = sql.executeQuery(getContext(),
				ItemsHistoryNT.replace("store", getContext().getStringProperty("store"))
						.replace("Area", getContext().getStringProperty("Area"))
						.replace("Dept", getContext().getStringProperty("SeaFoodDeli"))
						.replace("Zone", getContext().getStringProperty("Zone")).replace("FinWk", Week)
						.replace("FinYr", getContext().getStringProperty("FinYr")).replace("Division", getContext().getStringProperty("Division")));
		System.out.println("DB Total: " + DBTotalSFD);

		UITotalSFD = pageMonthlyPlanningGroupPO.GetTotal(extentTest, objMonthlyPlanningGroupObjects.RowNameTotalSFD,
				objWeeklyPlanningStoreObjects.SecondWeekofMonthSFD);
		pageMonthlyPlanningPO.CompareValues(extentTest, DBTotalSFD, UITotalSFD);
	}

	@Test(description = "Weekly Planning - Store, Items history - Third Week", priority = 11)
	public void ItemsHistoryThirdWeek() throws Exception {
		pageWeeklyPlanningPO = new WeeklyPlanningStorePage().createPage(getDriver(), getContext(), log, status, data,
				getReport(), getReportLogger(), WeeklyPlanningStorePage.class);
		pageMonthlyPlanningGroupPO = new MonthlyPlanningGroupPage().createPage(getDriver(), getContext(), log, status,
				data, getReport(), getReportLogger(), MonthlyPlanningGroupPage.class);
		TestStatus status = getTestStatus();
		System.out.println("Entering into TestCase");
		pageWeeklyPlanningPO.SelectHistoryDropdownToView(objWeeklyPlanningStoreObjects, extentTest, "Items");
		pageWeeklyPlanningPO.SelectHistoryDropdownToView(objWeeklyPlanningStoreObjects, extentTest, "Items");
		Name = pageWeeklyPlanningPO.MonthName(objWeeklyPlanningStoreObjects, extentTest,
				objWeeklyPlanningStoreObjects.ThirdWeekofMonthColumnName);
		YrName = pageWeeklyPlanningPO.MonthName(objWeeklyPlanningStoreObjects, extentTest,
				objWeeklyPlanningStoreObjects.CurrentWeekBDGTNT);
		WeekName = Name.split(" ");
		YearName = YrName.split(" ");
		Week = WeekName[0];
		Year = getContext().getStringProperty("FinYr");

		String ItemsHistoryNT = data.getText("ItemsHistoryNT");
		DBTotal = sql.executeQuery(getContext(),
				ItemsHistoryNT.replace("store", getContext().getStringProperty("store"))
						.replace("Area", getContext().getStringProperty("Area"))
						.replace("Dept", getContext().getStringProperty("Longlife"))
						.replace("Zone", getContext().getStringProperty("Zone")).replace("FinWk", Week)
						.replace("FinYr", getContext().getStringProperty("FinYr")).replace("Division", getContext().getStringProperty("Division")));
		System.out.println("DB Total: " + DBTotal);

		UITotal = pageMonthlyPlanningGroupPO.GetTotal(extentTest, objMonthlyPlanningGroupObjects.RowNameTotalLL,
				objWeeklyPlanningStoreObjects.ThirdWeekofMonthLL);
		pageMonthlyPlanningPO.CompareValues(extentTest, DBTotal, UITotal);

		DBTotalSFD = sql.executeQuery(getContext(),
				ItemsHistoryNT.replace("store", getContext().getStringProperty("store"))
						.replace("Area", getContext().getStringProperty("Area"))
						.replace("Dept", getContext().getStringProperty("SeaFoodDeli"))
						.replace("Zone", getContext().getStringProperty("Zone")).replace("FinWk", Week)
						.replace("FinYr", getContext().getStringProperty("FinYr")).replace("Division", getContext().getStringProperty("Division")));
		System.out.println("DB Total: " + DBTotal);

		UITotalSFD = pageMonthlyPlanningGroupPO.GetTotal(extentTest, objMonthlyPlanningGroupObjects.RowNameTotalSFD,
				objWeeklyPlanningStoreObjects.ThirdWeekofMonthSFD);
		pageMonthlyPlanningPO.CompareValues(extentTest, DBTotalSFD, UITotalSFD);
	}

	@Test(description = "Weekly Planning - Store, Items history - Fourth Week", priority = 12)
	public void ItemsHistoryFourthWeek() throws Exception {
		pageWeeklyPlanningPO = new WeeklyPlanningStorePage().createPage(getDriver(), getContext(), log, status, data,
				getReport(), getReportLogger(), WeeklyPlanningStorePage.class);
		pageMonthlyPlanningGroupPO = new MonthlyPlanningGroupPage().createPage(getDriver(), getContext(), log, status,
				data, getReport(), getReportLogger(), MonthlyPlanningGroupPage.class);
		TestStatus status = getTestStatus();
		System.out.println("Entering into TestCase");
		pageWeeklyPlanningPO.SelectHistoryDropdownToView(objWeeklyPlanningStoreObjects, extentTest, "Items");
		pageWeeklyPlanningPO.SelectHistoryDropdownToView(objWeeklyPlanningStoreObjects, extentTest, "Items");
		Name = pageWeeklyPlanningPO.MonthName(objWeeklyPlanningStoreObjects, extentTest,
				objWeeklyPlanningStoreObjects.FourWeekofMonthColumnName);
		YrName = pageWeeklyPlanningPO.MonthName(objWeeklyPlanningStoreObjects, extentTest,
				objWeeklyPlanningStoreObjects.CurrentWeekBDGTNT);
		WeekName = Name.split(" ");
		YearName = YrName.split(" ");
		Week = WeekName[0];
		Year = getContext().getStringProperty("FinYr");

		String ItemsHistoryNT = data.getText("ItemsHistoryNT");
		DBTotal = sql.executeQuery(getContext(),
				ItemsHistoryNT.replace("store", getContext().getStringProperty("store"))
						.replace("Area", getContext().getStringProperty("Area"))
						.replace("Dept", getContext().getStringProperty("Longlife"))
						.replace("Zone", getContext().getStringProperty("Zone")).replace("FinWk", Week)
						.replace("FinYr", getContext().getStringProperty("FinYr")).replace("Division", getContext().getStringProperty("Division")));
		System.out.println("DB Total: " + DBTotal);

		UITotal = pageMonthlyPlanningGroupPO.GetTotal(extentTest, objMonthlyPlanningGroupObjects.RowNameTotalLL,
				objWeeklyPlanningStoreObjects.FourWeekofMonthLL);
		pageMonthlyPlanningPO.CompareValues(extentTest, DBTotal, UITotal);

		DBTotalSFD = sql.executeQuery(getContext(),
				ItemsHistoryNT.replace("store", getContext().getStringProperty("store"))
						.replace("Area", getContext().getStringProperty("Area"))
						.replace("Dept", getContext().getStringProperty("SeaFoodDeli"))
						.replace("Zone", getContext().getStringProperty("Zone")).replace("FinWk", Week)
						.replace("FinYr", getContext().getStringProperty("FinYr")).replace("Division", getContext().getStringProperty("Division")));
		System.out.println("DB Total: " + DBTotal);

		UITotalSFD = pageMonthlyPlanningGroupPO.GetTotal(extentTest, objMonthlyPlanningGroupObjects.RowNameTotalSFD,
				objWeeklyPlanningStoreObjects.FourWeekofMonthSFD);
		pageMonthlyPlanningPO.CompareValues(extentTest, DBTotalSFD, UITotalSFD);
	}

	// Customers

	@Test(description = "Weekly Planning - Store, Customers history - First Week", priority = 13)
	public void CustomersHistoryFirsthWeek() throws Exception {
		pageWeeklyPlanningPO = new WeeklyPlanningStorePage().createPage(getDriver(), getContext(), log, status, data,
				getReport(), getReportLogger(), WeeklyPlanningStorePage.class);
		pageMonthlyPlanningGroupPO = new MonthlyPlanningGroupPage().createPage(getDriver(), getContext(), log, status,
				data, getReport(), getReportLogger(), MonthlyPlanningGroupPage.class);
		TestStatus status = getTestStatus();
		System.out.println("Entering into TestCase");
		pageWeeklyPlanningPO.SelectHistoryDropdownToView(objWeeklyPlanningStoreObjects, extentTest, "Customers");
		Name = pageWeeklyPlanningPO.MonthName(objWeeklyPlanningStoreObjects, extentTest,
				objWeeklyPlanningStoreObjects.FirstWeekofMonthColumnName);
		YrName = pageWeeklyPlanningPO.MonthName(objWeeklyPlanningStoreObjects, extentTest,
				objWeeklyPlanningStoreObjects.CurrentWeekBDGTNT);
		WeekName = Name.split(" ");
		YearName = YrName.split(" ");
		Week = WeekName[0];
		Year = getContext().getStringProperty("FinYr");

		String CustomerHistoryNT = data.getText("CustomerHistoryNT");
		DBTotal = sql.executeQuery(getContext(),
				CustomerHistoryNT.replace("store", getContext().getStringProperty("store"))
						.replace("Area", getContext().getStringProperty("Area"))
						.replace("Dept", getContext().getStringProperty("Longlife"))
						.replace("Zone", getContext().getStringProperty("Zone")).replace("FinWk", Week)
						.replace("FinYr", getContext().getStringProperty("FinYr")).replace("Division", getContext().getStringProperty("Division")));
		System.out.println("DB Total: " + DBTotal);

		UITotal = pageMonthlyPlanningGroupPO.GetTotal(extentTest, objMonthlyPlanningGroupObjects.RowNameTotalLL,
				objWeeklyPlanningStoreObjects.FirstWeekofMonthLL);
		pageMonthlyPlanningPO.CompareValues(extentTest, DBTotal, UITotal);

		DBTotalSFD = sql.executeQuery(getContext(),
				CustomerHistoryNT.replace("store", getContext().getStringProperty("store"))
						.replace("Area", getContext().getStringProperty("Area"))
						.replace("Dept", getContext().getStringProperty("SeaFoodDeli"))
						.replace("Zone", getContext().getStringProperty("Zone")).replace("FinWk", Week)
						.replace("FinYr", getContext().getStringProperty("FinYr")).replace("Division", getContext().getStringProperty("Division")));
		System.out.println("DB Total: " + DBTotal);

		UITotalSFD = pageMonthlyPlanningGroupPO.GetTotal(extentTest, objMonthlyPlanningGroupObjects.RowNameTotalSFD,
				objWeeklyPlanningStoreObjects.FirstWeekofMonthSFD);
		pageMonthlyPlanningPO.CompareValues(extentTest, DBTotalSFD, UITotalSFD);
	}

	@Test(description = "Weekly Planning - Store, Customers history - Second Week", priority = 14)
	public void CustomersHistorySecondWeek() throws Exception {
		pageWeeklyPlanningPO = new WeeklyPlanningStorePage().createPage(getDriver(), getContext(), log, status, data,
				getReport(), getReportLogger(), WeeklyPlanningStorePage.class);
		pageMonthlyPlanningGroupPO = new MonthlyPlanningGroupPage().createPage(getDriver(), getContext(), log, status,
				data, getReport(), getReportLogger(), MonthlyPlanningGroupPage.class);
		TestStatus status = getTestStatus();
		System.out.println("Entering into TestCase");
		pageWeeklyPlanningPO.SelectHistoryDropdownToView(objWeeklyPlanningStoreObjects, extentTest, "Customers");
		Name = pageWeeklyPlanningPO.MonthName(objWeeklyPlanningStoreObjects, extentTest,
				objWeeklyPlanningStoreObjects.SecondWeekofMonthColumnName);
		YrName = pageWeeklyPlanningPO.MonthName(objWeeklyPlanningStoreObjects, extentTest,
				objWeeklyPlanningStoreObjects.CurrentWeekBDGTNT);
		WeekName = Name.split(" ");
		YearName = YrName.split(" ");
		Week = WeekName[0];
		Year = getContext().getStringProperty("FinYr");

		String CustomerHistoryNT = data.getText("CustomerHistoryNT");
		DBTotal = sql.executeQuery(getContext(),
				CustomerHistoryNT.replace("store", getContext().getStringProperty("store"))
						.replace("Area", getContext().getStringProperty("Area"))
						.replace("Dept", getContext().getStringProperty("Longlife"))
						.replace("Zone", getContext().getStringProperty("Zone")).replace("FinWk", Week)
						.replace("FinYr", getContext().getStringProperty("FinYr")).replace("Division", getContext().getStringProperty("Division")));
		System.out.println("DB Total: " + DBTotal);

		UITotal = pageMonthlyPlanningGroupPO.GetTotal(extentTest, objMonthlyPlanningGroupObjects.RowNameTotalLL,
				objWeeklyPlanningStoreObjects.SecondWeekofMonthLL);
		pageMonthlyPlanningPO.CompareValues(extentTest, DBTotal, UITotal);

		DBTotalSFD = sql.executeQuery(getContext(),
				CustomerHistoryNT.replace("store", getContext().getStringProperty("store"))
						.replace("Area", getContext().getStringProperty("Area"))
						.replace("Dept", getContext().getStringProperty("SeaFoodDeli"))
						.replace("Zone", getContext().getStringProperty("Zone")).replace("FinWk", Week)
						.replace("FinYr", getContext().getStringProperty("FinYr")).replace("Division", getContext().getStringProperty("Division")));
		System.out.println("DB Total: " + DBTotalSFD);

		UITotalSFD = pageMonthlyPlanningGroupPO.GetTotal(extentTest, objMonthlyPlanningGroupObjects.RowNameTotalSFD,
				objWeeklyPlanningStoreObjects.SecondWeekofMonthSFD);
		pageMonthlyPlanningPO.CompareValues(extentTest, DBTotalSFD, UITotalSFD);
	}

	@Test(description = "Weekly Planning - Store, Customers history - Third Week", priority = 15)
	public void CustomersHistoryThirdWeek() throws Exception {
		pageWeeklyPlanningPO = new WeeklyPlanningStorePage().createPage(getDriver(), getContext(), log, status, data,
				getReport(), getReportLogger(), WeeklyPlanningStorePage.class);
		pageMonthlyPlanningGroupPO = new MonthlyPlanningGroupPage().createPage(getDriver(), getContext(), log, status,
				data, getReport(), getReportLogger(), MonthlyPlanningGroupPage.class);
		TestStatus status = getTestStatus();
		System.out.println("Entering into TestCase");
		pageWeeklyPlanningPO.SelectHistoryDropdownToView(objWeeklyPlanningStoreObjects, extentTest, "Customers");
		Name = pageWeeklyPlanningPO.MonthName(objWeeklyPlanningStoreObjects, extentTest,
				objWeeklyPlanningStoreObjects.ThirdWeekofMonthColumnName);
		YrName = pageWeeklyPlanningPO.MonthName(objWeeklyPlanningStoreObjects, extentTest,
				objWeeklyPlanningStoreObjects.CurrentWeekBDGTNT);
		WeekName = Name.split(" ");
		YearName = YrName.split(" ");
		Week = WeekName[0];
		Year = getContext().getStringProperty("FinYr");

		String CustomerHistoryNT = data.getText("CustomerHistoryNT");
		DBTotal = sql.executeQuery(getContext(),
				CustomerHistoryNT.replace("store", getContext().getStringProperty("store"))
						.replace("Area", getContext().getStringProperty("Area"))
						.replace("Dept", getContext().getStringProperty("Longlife"))
						.replace("Zone", getContext().getStringProperty("Zone")).replace("FinWk", Week)
						.replace("FinYr", getContext().getStringProperty("FinYr")).replace("Division", getContext().getStringProperty("Division")));
		System.out.println("DB Total: " + DBTotal);

		UITotal = pageMonthlyPlanningGroupPO.GetTotal(extentTest, objMonthlyPlanningGroupObjects.RowNameTotalLL,
				objWeeklyPlanningStoreObjects.ThirdWeekofMonthLL);
		pageMonthlyPlanningPO.CompareValues(extentTest, DBTotal, UITotal);

		DBTotalSFD = sql.executeQuery(getContext(),
				CustomerHistoryNT.replace("store", getContext().getStringProperty("store"))
						.replace("Area", getContext().getStringProperty("Area"))
						.replace("Dept", getContext().getStringProperty("SeaFoodDeli"))
						.replace("Zone", getContext().getStringProperty("Zone")).replace("FinWk", Week)
						.replace("FinYr", getContext().getStringProperty("FinYr")).replace("Division", getContext().getStringProperty("Division")));
		System.out.println("DB Total: " + DBTotalSFD);

		UITotalSFD = pageMonthlyPlanningGroupPO.GetTotal(extentTest, objMonthlyPlanningGroupObjects.RowNameTotalSFD,
				objWeeklyPlanningStoreObjects.ThirdWeekofMonthSFD);
		pageMonthlyPlanningPO.CompareValues(extentTest, DBTotalSFD, UITotalSFD);
	}

	@Test(description = "Weekly Planning - Store, Customers history - Fourth Week", priority = 16)
	public void CustomersHistoryFourthWeek() throws Exception {
		pageWeeklyPlanningPO = new WeeklyPlanningStorePage().createPage(getDriver(), getContext(), log, status, data,
				getReport(), getReportLogger(), WeeklyPlanningStorePage.class);
		pageMonthlyPlanningGroupPO = new MonthlyPlanningGroupPage().createPage(getDriver(), getContext(), log, status,
				data, getReport(), getReportLogger(), MonthlyPlanningGroupPage.class);
		TestStatus status = getTestStatus();
		System.out.println("Entering into TestCase");
		pageWeeklyPlanningPO.SelectHistoryDropdownToView(objWeeklyPlanningStoreObjects, extentTest, "Customers");
		Name = pageWeeklyPlanningPO.MonthName(objWeeklyPlanningStoreObjects, extentTest,
				objWeeklyPlanningStoreObjects.FourWeekofMonthColumnName);
		YrName = pageWeeklyPlanningPO.MonthName(objWeeklyPlanningStoreObjects, extentTest,
				objWeeklyPlanningStoreObjects.CurrentWeekBDGTNT);
		WeekName = Name.split(" ");
		YearName = YrName.split(" ");
		Week = WeekName[0];
		Year = getContext().getStringProperty("FinYr");

		String CustomerHistoryNT = data.getText("CustomerHistoryNT");
		DBTotal = sql.executeQuery(getContext(),
				CustomerHistoryNT.replace("store", getContext().getStringProperty("store"))
						.replace("Area", getContext().getStringProperty("Area"))
						.replace("Dept", getContext().getStringProperty("Longlife"))
						.replace("Zone", getContext().getStringProperty("Zone")).replace("FinWk", Week)
						.replace("FinYr", getContext().getStringProperty("FinYr")).replace("Division", getContext().getStringProperty("Division")));
		System.out.println("DB Total: " + DBTotal);

		UITotal = pageMonthlyPlanningGroupPO.GetTotal(extentTest, objMonthlyPlanningGroupObjects.RowNameTotalLL,
				objWeeklyPlanningStoreObjects.FourWeekofMonthLL);
		pageMonthlyPlanningPO.CompareValues(extentTest, DBTotal, UITotal);

		DBTotalSFD = sql.executeQuery(getContext(),
				CustomerHistoryNT.replace("store", getContext().getStringProperty("store"))
						.replace("Area", getContext().getStringProperty("Area"))
						.replace("Dept", getContext().getStringProperty("SeaFoodDeli"))
						.replace("Zone", getContext().getStringProperty("Zone")).replace("FinWk", Week)
						.replace("FinYr", getContext().getStringProperty("FinYr")).replace("Division", getContext().getStringProperty("Division")));
		System.out.println("DB Total: " + DBTotalSFD);

		UITotalSFD = pageMonthlyPlanningGroupPO.GetTotal(extentTest, objMonthlyPlanningGroupObjects.RowNameTotalSFD,
				objWeeklyPlanningStoreObjects.FourWeekofMonthSFD);
		pageMonthlyPlanningPO.CompareValues(extentTest, DBTotalSFD, UITotalSFD);
	}

	// Item Price

	@Test(description = "Weekly Planning - Store, Item Price history - First Week", priority = 17)
	public void ItemPriceHistoryFirsthWeek() throws Exception {
		pageWeeklyPlanningPO = new WeeklyPlanningStorePage().createPage(getDriver(), getContext(), log, status, data,
				getReport(), getReportLogger(), WeeklyPlanningStorePage.class);
		pageMonthlyPlanningGroupPO = new MonthlyPlanningGroupPage().createPage(getDriver(), getContext(), log, status,
				data, getReport(), getReportLogger(), MonthlyPlanningGroupPage.class);
		TestStatus status = getTestStatus();
		System.out.println("Entering into TestCase");
		pageWeeklyPlanningPO.SelectHistoryDropdownToView(objWeeklyPlanningStoreObjects, extentTest, "Item Price");
		Name = pageWeeklyPlanningPO.MonthName(objWeeklyPlanningStoreObjects, extentTest,
				objWeeklyPlanningStoreObjects.FirstWeekofMonthColumnName);
		YrName = pageWeeklyPlanningPO.MonthName(objWeeklyPlanningStoreObjects, extentTest,
				objWeeklyPlanningStoreObjects.CurrentWeekBDGTNT);
		WeekName = Name.split(" ");
		YearName = YrName.split(" ");
		Week = WeekName[0];
		Year = getContext().getStringProperty("FinYr");

		String ItemPriceHistoryNT = data.getText("ItemPriceHistoryNT");
		DBTotal = sql.executeQuery(getContext(),
				ItemPriceHistoryNT.replace("store", getContext().getStringProperty("store"))
						.replace("Area", getContext().getStringProperty("Area"))
						.replace("Dept", getContext().getStringProperty("Longlife"))
						.replace("Zone", getContext().getStringProperty("Zone")).replace("FinWk", Week)
						.replace("FinYr", getContext().getStringProperty("FinYr")).replace("Division", getContext().getStringProperty("Division")));
		System.out.println("DB Total: " + DBTotal);

		UITotal = pageMonthlyPlanningGroupPO.GetTotal(extentTest, objMonthlyPlanningGroupObjects.RowNameTotalLL,
				objWeeklyPlanningStoreObjects.FirstWeekofMonthLL);
		pageMonthlyPlanningPO.CompareValues(extentTest, DBTotal, UITotal);

		DBTotalSFD = sql.executeQuery(getContext(),
				ItemPriceHistoryNT.replace("store", getContext().getStringProperty("store"))
						.replace("Area", getContext().getStringProperty("Area"))
						.replace("Dept", getContext().getStringProperty("SeaFoodDeli"))
						.replace("Zone", getContext().getStringProperty("Zone")).replace("FinWk", Week)
						.replace("FinYr", getContext().getStringProperty("FinYr")).replace("Division", getContext().getStringProperty("Division")));
		System.out.println("DB Total: " + DBTotalSFD);

		UITotalSFD = pageMonthlyPlanningGroupPO.GetTotal(extentTest, objMonthlyPlanningGroupObjects.RowNameTotalSFD,
				objWeeklyPlanningStoreObjects.FirstWeekofMonthSFD);
		pageMonthlyPlanningPO.CompareValues(extentTest, DBTotalSFD, UITotalSFD);
	}

	@Test(description = "Weekly Planning - Store, Item Price history - Second Week", priority = 18)
	public void ItemPriceHistorySecondWeek() throws Exception {
		pageWeeklyPlanningPO = new WeeklyPlanningStorePage().createPage(getDriver(), getContext(), log, status, data,
				getReport(), getReportLogger(), WeeklyPlanningStorePage.class);
		pageMonthlyPlanningGroupPO = new MonthlyPlanningGroupPage().createPage(getDriver(), getContext(), log, status,
				data, getReport(), getReportLogger(), MonthlyPlanningGroupPage.class);
		TestStatus status = getTestStatus();
		System.out.println("Entering into TestCase");
		pageWeeklyPlanningPO.SelectHistoryDropdownToView(objWeeklyPlanningStoreObjects, extentTest, "Item Price");
		Name = pageWeeklyPlanningPO.MonthName(objWeeklyPlanningStoreObjects, extentTest,
				objWeeklyPlanningStoreObjects.SecondWeekofMonthColumnName);
		YrName = pageWeeklyPlanningPO.MonthName(objWeeklyPlanningStoreObjects, extentTest,
				objWeeklyPlanningStoreObjects.CurrentWeekBDGTNT);
		WeekName = Name.split(" ");
		YearName = YrName.split(" ");
		Week = WeekName[0];
		Year = getContext().getStringProperty("FinYr");

		String ItemPriceHistoryNT = data.getText("ItemPriceHistoryNT");
		DBTotal = sql.executeQuery(getContext(),
				ItemPriceHistoryNT.replace("store", getContext().getStringProperty("store"))
						.replace("Area", getContext().getStringProperty("Area"))
						.replace("Dept", getContext().getStringProperty("Longlife"))
						.replace("Zone", getContext().getStringProperty("Zone")).replace("FinWk", Week)
						.replace("FinYr", getContext().getStringProperty("FinYr")).replace("Division", getContext().getStringProperty("Division")));
		System.out.println("DB Total: " + DBTotal);

		UITotal = pageMonthlyPlanningGroupPO.GetTotal(extentTest, objMonthlyPlanningGroupObjects.RowNameTotalLL,
				objWeeklyPlanningStoreObjects.SecondWeekofMonthLL);
		pageMonthlyPlanningPO.CompareValues(extentTest, DBTotal, UITotal);

		DBTotalSFD = sql.executeQuery(getContext(),
				ItemPriceHistoryNT.replace("store", getContext().getStringProperty("store"))
						.replace("Area", getContext().getStringProperty("Area"))
						.replace("Dept", getContext().getStringProperty("SeaFoodDeli"))
						.replace("Zone", getContext().getStringProperty("Zone")).replace("FinWk", Week)
						.replace("FinYr", getContext().getStringProperty("FinYr")).replace("Division", getContext().getStringProperty("Division")));
		System.out.println("DB Total: " + DBTotalSFD);

		UITotalSFD = pageMonthlyPlanningGroupPO.GetTotal(extentTest, objMonthlyPlanningGroupObjects.RowNameTotalSFD,
				objWeeklyPlanningStoreObjects.SecondWeekofMonthSFD);
		pageMonthlyPlanningPO.CompareValues(extentTest, DBTotalSFD, UITotalSFD);
	}

	@Test(description = "Weekly Planning - Store, Item Price history - Third Week", priority = 19)
	public void ItemPriceHistoryThirdWeek() throws Exception {
		pageWeeklyPlanningPO = new WeeklyPlanningStorePage().createPage(getDriver(), getContext(), log, status, data,
				getReport(), getReportLogger(), WeeklyPlanningStorePage.class);
		pageMonthlyPlanningGroupPO = new MonthlyPlanningGroupPage().createPage(getDriver(), getContext(), log, status,
				data, getReport(), getReportLogger(), MonthlyPlanningGroupPage.class);
		TestStatus status = getTestStatus();
		System.out.println("Entering into TestCase");
		pageWeeklyPlanningPO.SelectHistoryDropdownToView(objWeeklyPlanningStoreObjects, extentTest, "Item Price");
		Name = pageWeeklyPlanningPO.MonthName(objWeeklyPlanningStoreObjects, extentTest,
				objWeeklyPlanningStoreObjects.ThirdWeekofMonthColumnName);
		YrName = pageWeeklyPlanningPO.MonthName(objWeeklyPlanningStoreObjects, extentTest,
				objWeeklyPlanningStoreObjects.CurrentWeekBDGTNT);
		WeekName = Name.split(" ");
		YearName = YrName.split(" ");
		Week = WeekName[0];
		Year = getContext().getStringProperty("FinYr");

		String ItemPriceHistoryNT = data.getText("ItemPriceHistoryNT");
		DBTotal = sql.executeQuery(getContext(),
				ItemPriceHistoryNT.replace("store", getContext().getStringProperty("store"))
						.replace("Area", getContext().getStringProperty("Area"))
						.replace("Dept", getContext().getStringProperty("Longlife"))
						.replace("Zone", getContext().getStringProperty("Zone")).replace("FinWk", Week)
						.replace("FinYr", getContext().getStringProperty("FinYr")).replace("Division", getContext().getStringProperty("Division")));
		System.out.println("DB Total: " + DBTotal);

		UITotal = pageMonthlyPlanningGroupPO.GetTotal(extentTest, objMonthlyPlanningGroupObjects.RowNameTotalLL,
				objWeeklyPlanningStoreObjects.ThirdWeekofMonthLL);
		pageMonthlyPlanningPO.CompareValues(extentTest, DBTotal, UITotal);

		DBTotalSFD = sql.executeQuery(getContext(),
				ItemPriceHistoryNT.replace("store", getContext().getStringProperty("store"))
						.replace("Area", getContext().getStringProperty("Area"))
						.replace("Dept", getContext().getStringProperty("SeaFoodDeli"))
						.replace("Zone", getContext().getStringProperty("Zone")).replace("FinWk", Week)
						.replace("FinYr", getContext().getStringProperty("FinYr")).replace("Division", getContext().getStringProperty("Division")));
		System.out.println("DB Total: " + DBTotalSFD);

		UITotalSFD = pageMonthlyPlanningGroupPO.GetTotal(extentTest, objMonthlyPlanningGroupObjects.RowNameTotalSFD,
				objWeeklyPlanningStoreObjects.ThirdWeekofMonthSFD);
		pageMonthlyPlanningPO.CompareValues(extentTest, DBTotalSFD, UITotalSFD);
	}

	@Test(description = "Weekly Planning - Store, Item Price history - Fourth Week", priority = 20)
	public void ItemPriceHistoryFourthWeek() throws Exception {
		pageWeeklyPlanningPO = new WeeklyPlanningStorePage().createPage(getDriver(), getContext(), log, status, data,
				getReport(), getReportLogger(), WeeklyPlanningStorePage.class);
		pageMonthlyPlanningGroupPO = new MonthlyPlanningGroupPage().createPage(getDriver(), getContext(), log, status,
				data, getReport(), getReportLogger(), MonthlyPlanningGroupPage.class);
		TestStatus status = getTestStatus();
		System.out.println("Entering into TestCase");
		pageWeeklyPlanningPO.SelectHistoryDropdownToView(objWeeklyPlanningStoreObjects, extentTest, "Item Price");
		Name = pageWeeklyPlanningPO.MonthName(objWeeklyPlanningStoreObjects, extentTest,
				objWeeklyPlanningStoreObjects.FourWeekofMonthColumnName);
		YrName = pageWeeklyPlanningPO.MonthName(objWeeklyPlanningStoreObjects, extentTest,
				objWeeklyPlanningStoreObjects.CurrentWeekBDGTNT);
		WeekName = Name.split(" ");
		YearName = YrName.split(" ");
		Week = WeekName[0];
		Year = getContext().getStringProperty("FinYr");

		String ItemPriceHistoryNT = data.getText("ItemPriceHistoryNT");
		DBTotal = sql.executeQuery(getContext(),
				ItemPriceHistoryNT.replace("store", getContext().getStringProperty("store"))
						.replace("Area", getContext().getStringProperty("Area"))
						.replace("Dept", getContext().getStringProperty("Longlife"))
						.replace("Zone", getContext().getStringProperty("Zone")).replace("FinWk", Week)
						.replace("FinYr", getContext().getStringProperty("FinYr")).replace("Division", getContext().getStringProperty("Division")));
		System.out.println("DB Total: " + DBTotal);

		UITotal = pageMonthlyPlanningGroupPO.GetTotal(extentTest, objMonthlyPlanningGroupObjects.RowNameTotalLL,
				objWeeklyPlanningStoreObjects.FourWeekofMonthLL);
		pageMonthlyPlanningPO.CompareValues(extentTest, DBTotal, UITotal);

		DBTotalSFD = sql.executeQuery(getContext(),
				ItemPriceHistoryNT.replace("store", getContext().getStringProperty("store"))
						.replace("Area", getContext().getStringProperty("Area"))
						.replace("Dept", getContext().getStringProperty("SeaFoodDeli"))
						.replace("Zone", getContext().getStringProperty("Zone")).replace("FinWk", Week)
						.replace("FinYr", getContext().getStringProperty("FinYr")).replace("Division", getContext().getStringProperty("Division")));
		System.out.println("DB Total: " + DBTotalSFD);

		UITotalSFD = pageMonthlyPlanningGroupPO.GetTotal(extentTest, objMonthlyPlanningGroupObjects.RowNameTotalSFD,
				objWeeklyPlanningStoreObjects.FourWeekofMonthSFD);
		pageMonthlyPlanningPO.CompareValues(extentTest, DBTotalSFD, UITotalSFD);
	}

	// Wage Percentage

	@Test(description = "Weekly Planning - Store, WagePercentage history - First Week", priority = 21)
	public void WagePercentageHistoryFirsthWeek() throws Exception {
		pageWeeklyPlanningPO = new WeeklyPlanningStorePage().createPage(getDriver(), getContext(), log, status, data,
				getReport(), getReportLogger(), WeeklyPlanningStorePage.class);
		pageMonthlyPlanningGroupPO = new MonthlyPlanningGroupPage().createPage(getDriver(), getContext(), log, status,
				data, getReport(), getReportLogger(), MonthlyPlanningGroupPage.class);
		TestStatus status = getTestStatus();
		System.out.println("Entering into TestCase");
		pageWeeklyPlanningPO.SelectHistoryDropdownToView(objWeeklyPlanningStoreObjects, extentTest, "Wage %");
		Name = pageWeeklyPlanningPO.MonthName(objWeeklyPlanningStoreObjects, extentTest,
				objWeeklyPlanningStoreObjects.FirstWeekofMonthColumnName);
		YrName = pageWeeklyPlanningPO.MonthName(objWeeklyPlanningStoreObjects, extentTest,
				objWeeklyPlanningStoreObjects.CurrentWeekBDGTNT);
		WeekName = Name.split(" ");
		YearName = YrName.split(" ");
		Week = WeekName[0];
		Year = getContext().getStringProperty("FinYr");

		String WagePercentageHistoryNT = data.getText("WagePercentageHistoryNT");
		DBTotal = sql.executeQuery(getContext(),
				WagePercentageHistoryNT.replace("store", getContext().getStringProperty("store"))
						.replace("Area", getContext().getStringProperty("Area"))
						.replace("Dept", getContext().getStringProperty("Longlife"))
						.replace("Zone", getContext().getStringProperty("Zone")).replace("FinWk", Week)
						.replace("FinYr", getContext().getStringProperty("FinYr")).replace("Division", getContext().getStringProperty("Division")));
		System.out.println("DB Total: " + DBTotal);

		UITotal = pageMonthlyPlanningGroupPO.GetTotal(extentTest, objMonthlyPlanningGroupObjects.RowNameTotalLL,
				objWeeklyPlanningStoreObjects.FirstWeekofMonthLL);
		pageMonthlyPlanningPO.CompareValues(extentTest, DBTotal, UITotal);

		DBTotalSFD = sql.executeQuery(getContext(),
				WagePercentageHistoryNT.replace("store", getContext().getStringProperty("store"))
						.replace("Area", getContext().getStringProperty("Area"))
						.replace("Dept", getContext().getStringProperty("SeaFoodDeli"))
						.replace("Zone", getContext().getStringProperty("Zone")).replace("FinWk", Week)
						.replace("FinYr", getContext().getStringProperty("FinYr")).replace("Division", getContext().getStringProperty("Division")));
		System.out.println("DB Total: " + DBTotal);

		UITotalSFD = pageMonthlyPlanningGroupPO.GetTotal(extentTest, objMonthlyPlanningGroupObjects.RowNameTotalSFD,
				objWeeklyPlanningStoreObjects.FirstWeekofMonthSFD);
		pageMonthlyPlanningPO.CompareValues(extentTest, DBTotalSFD, UITotalSFD);
	}

	@Test(description = "Weekly Planning - Store, WagePercentage history - Second Week", priority = 22)
	public void WagePercentageHistorySecondWeek() throws Exception {
		pageWeeklyPlanningPO = new WeeklyPlanningStorePage().createPage(getDriver(), getContext(), log, status, data,
				getReport(), getReportLogger(), WeeklyPlanningStorePage.class);
		pageMonthlyPlanningGroupPO = new MonthlyPlanningGroupPage().createPage(getDriver(), getContext(), log, status,
				data, getReport(), getReportLogger(), MonthlyPlanningGroupPage.class);
		TestStatus status = getTestStatus();
		System.out.println("Entering into TestCase");
		pageWeeklyPlanningPO.SelectHistoryDropdownToView(objWeeklyPlanningStoreObjects, extentTest, "Wage %");
		Name = pageWeeklyPlanningPO.MonthName(objWeeklyPlanningStoreObjects, extentTest,
				objWeeklyPlanningStoreObjects.SecondWeekofMonthColumnName);
		YrName = pageWeeklyPlanningPO.MonthName(objWeeklyPlanningStoreObjects, extentTest,
				objWeeklyPlanningStoreObjects.CurrentWeekBDGTNT);
		WeekName = Name.split(" ");
		YearName = YrName.split(" ");
		Week = WeekName[0];
		Year = getContext().getStringProperty("FinYr");

		String WagePercentageHistoryNT = data.getText("WagePercentageHistoryNT");
		DBTotal = sql.executeQuery(getContext(),
				WagePercentageHistoryNT.replace("store", getContext().getStringProperty("store"))
						.replace("Area", getContext().getStringProperty("Area"))
						.replace("Dept", getContext().getStringProperty("Longlife"))
						.replace("Zone", getContext().getStringProperty("Zone")).replace("FinWk", Week)
						.replace("FinYr", getContext().getStringProperty("FinYr")).replace("Division", getContext().getStringProperty("Division")));
		System.out.println("DB Total: " + DBTotal);

		UITotal = pageMonthlyPlanningGroupPO.GetTotal(extentTest, objMonthlyPlanningGroupObjects.RowNameTotalLL,
				objWeeklyPlanningStoreObjects.SecondWeekofMonthLL);
		pageMonthlyPlanningPO.CompareValues(extentTest, DBTotal, UITotal);

		DBTotalSFD = sql.executeQuery(getContext(),
				WagePercentageHistoryNT.replace("store", getContext().getStringProperty("store"))
						.replace("Area", getContext().getStringProperty("Area"))
						.replace("Dept", getContext().getStringProperty("SeaFoodDeli"))
						.replace("Zone", getContext().getStringProperty("Zone")).replace("FinWk", Week)
						.replace("FinYr", getContext().getStringProperty("FinYr")).replace("Division", getContext().getStringProperty("Division")));
		System.out.println("DB Total: " + DBTotalSFD);

		UITotalSFD = pageMonthlyPlanningGroupPO.GetTotal(extentTest, objMonthlyPlanningGroupObjects.RowNameTotalSFD,
				objWeeklyPlanningStoreObjects.SecondWeekofMonthSFD);
		pageMonthlyPlanningPO.CompareValues(extentTest, DBTotalSFD, UITotalSFD);
	}

	@Test(description = "Weekly Planning - Store, WagePercentage history - Third Week", priority = 23)
	public void WagePercentageHistoryThirdWeek() throws Exception {
		pageWeeklyPlanningPO = new WeeklyPlanningStorePage().createPage(getDriver(), getContext(), log, status, data,
				getReport(), getReportLogger(), WeeklyPlanningStorePage.class);
		pageMonthlyPlanningGroupPO = new MonthlyPlanningGroupPage().createPage(getDriver(), getContext(), log, status,
				data, getReport(), getReportLogger(), MonthlyPlanningGroupPage.class);
		TestStatus status = getTestStatus();
		System.out.println("Entering into TestCase");
		pageWeeklyPlanningPO.SelectHistoryDropdownToView(objWeeklyPlanningStoreObjects, extentTest, "Wage %");
		Name = pageWeeklyPlanningPO.MonthName(objWeeklyPlanningStoreObjects, extentTest,
				objWeeklyPlanningStoreObjects.ThirdWeekofMonthColumnName);
		YrName = pageWeeklyPlanningPO.MonthName(objWeeklyPlanningStoreObjects, extentTest,
				objWeeklyPlanningStoreObjects.CurrentWeekBDGTNT);
		WeekName = Name.split(" ");
		YearName = YrName.split(" ");
		Week = WeekName[0];
		Year = getContext().getStringProperty("FinYr");

		String WagePercentageHistoryNT = data.getText("WagePercentageHistoryNT");
		DBTotal = sql.executeQuery(getContext(),
				WagePercentageHistoryNT.replace("store", getContext().getStringProperty("store"))
						.replace("Area", getContext().getStringProperty("Area"))
						.replace("Dept", getContext().getStringProperty("Longlife"))
						.replace("Zone", getContext().getStringProperty("Zone")).replace("FinWk", Week)
						.replace("FinYr", getContext().getStringProperty("FinYr")).replace("Division", getContext().getStringProperty("Division")));
		System.out.println("DB Total: " + DBTotal);

		UITotal = pageMonthlyPlanningGroupPO.GetTotal(extentTest, objMonthlyPlanningGroupObjects.RowNameTotalLL,
				objWeeklyPlanningStoreObjects.SecondWeekofMonthLL);
		pageMonthlyPlanningPO.CompareValues(extentTest, DBTotal, UITotal);

		DBTotalSFD = sql.executeQuery(getContext(),
				WagePercentageHistoryNT.replace("store", getContext().getStringProperty("store"))
						.replace("Area", getContext().getStringProperty("Area"))
						.replace("Dept", getContext().getStringProperty("SeaFoodDeli"))
						.replace("Zone", getContext().getStringProperty("Zone")).replace("FinWk", Week)
						.replace("FinYr", getContext().getStringProperty("FinYr")).replace("Division", getContext().getStringProperty("Division")));
		System.out.println("DB Total: " + DBTotalSFD);

		UITotalSFD = pageMonthlyPlanningGroupPO.GetTotal(extentTest, objMonthlyPlanningGroupObjects.RowNameTotalSFD,
				objWeeklyPlanningStoreObjects.SecondWeekofMonthSFD);
		pageMonthlyPlanningPO.CompareValues(extentTest, DBTotalSFD, UITotalSFD);
	}

	@Test(description = "Weekly Planning - Store, WagePercentage history - Fourth Week", priority = 24)
	public void WagePercentageHistoryFourthWeek() throws Exception {
		pageWeeklyPlanningPO = new WeeklyPlanningStorePage().createPage(getDriver(), getContext(), log, status, data,
				getReport(), getReportLogger(), WeeklyPlanningStorePage.class);
		pageMonthlyPlanningGroupPO = new MonthlyPlanningGroupPage().createPage(getDriver(), getContext(), log, status,
				data, getReport(), getReportLogger(), MonthlyPlanningGroupPage.class);
		TestStatus status = getTestStatus();
		System.out.println("Entering into TestCase");
		pageWeeklyPlanningPO.SelectHistoryDropdownToView(objWeeklyPlanningStoreObjects, extentTest, "Wage %");
		Name = pageWeeklyPlanningPO.MonthName(objWeeklyPlanningStoreObjects, extentTest,
				objWeeklyPlanningStoreObjects.FourWeekofMonthColumnName);
		YrName = pageWeeklyPlanningPO.MonthName(objWeeklyPlanningStoreObjects, extentTest,
				objWeeklyPlanningStoreObjects.CurrentWeekBDGTNT);
		WeekName = Name.split(" ");
		YearName = YrName.split(" ");
		Week = WeekName[0];
		Year = getContext().getStringProperty("FinYr");

		String WagePercentageHistoryNT = data.getText("WagePercentageHistoryNT");
		DBTotal = sql.executeQuery(getContext(),
				WagePercentageHistoryNT.replace("store", getContext().getStringProperty("store"))
						.replace("Area", getContext().getStringProperty("Area"))
						.replace("Dept", getContext().getStringProperty("Longlife"))
						.replace("Zone", getContext().getStringProperty("Zone")).replace("FinWk", Week)
						.replace("FinYr", getContext().getStringProperty("FinYr")).replace("Division", getContext().getStringProperty("Division")));
		System.out.println("DB Total: " + DBTotal);

		UITotal = pageMonthlyPlanningGroupPO.GetTotal(extentTest, objMonthlyPlanningGroupObjects.RowNameTotalLL,
				objWeeklyPlanningStoreObjects.FourWeekofMonthLL);
		pageMonthlyPlanningPO.CompareValues(extentTest, DBTotal, UITotal);

		DBTotalSFD = sql.executeQuery(getContext(),
				WagePercentageHistoryNT.replace("store", getContext().getStringProperty("store"))
						.replace("Area", getContext().getStringProperty("Area"))
						.replace("Dept", getContext().getStringProperty("SeaFoodDeli"))
						.replace("Zone", getContext().getStringProperty("Zone")).replace("FinWk", Week)
						.replace("FinYr", getContext().getStringProperty("FinYr")).replace("Division", getContext().getStringProperty("Division")));
		System.out.println("DB Total: " + DBTotalSFD);

		UITotalSFD = pageMonthlyPlanningGroupPO.GetTotal(extentTest, objMonthlyPlanningGroupObjects.RowNameTotalSFD,
				objWeeklyPlanningStoreObjects.FourWeekofMonthSFD);
		pageMonthlyPlanningPO.CompareValues(extentTest, DBTotalSFD, UITotalSFD);
	}

	// Paid Hours

	@Test(description = "Weekly Planning - Store, Paid Hours history - First Week", priority = 25)
	public void PaidHoursHistoryFirsthWeek() throws Exception {
		pageWeeklyPlanningPO = new WeeklyPlanningStorePage().createPage(getDriver(), getContext(), log, status, data,
				getReport(), getReportLogger(), WeeklyPlanningStorePage.class);
		pageMonthlyPlanningGroupPO = new MonthlyPlanningGroupPage().createPage(getDriver(), getContext(), log, status,
				data, getReport(), getReportLogger(), MonthlyPlanningGroupPage.class);
		TestStatus status = getTestStatus();
		System.out.println("Entering into TestCase");
		pageWeeklyPlanningPO.SelectHistoryDropdownToView(objWeeklyPlanningStoreObjects, extentTest, "Paid Hours");
		Name = pageWeeklyPlanningPO.MonthName(objWeeklyPlanningStoreObjects, extentTest,
				objWeeklyPlanningStoreObjects.FirstWeekofMonthColumnName);
		YrName = pageWeeklyPlanningPO.MonthName(objWeeklyPlanningStoreObjects, extentTest,
				objWeeklyPlanningStoreObjects.CurrentWeekBDGTNT);
		WeekName = Name.split(" ");
		YearName = YrName.split(" ");
		Week = WeekName[0];
		Year = getContext().getStringProperty("FinYr");

		String PaidHoursHistoryNT = data.getText("PaidHoursHistoryNT");
		DBTotal = sql.executeQuery(getContext(),
				PaidHoursHistoryNT.replace("store", getContext().getStringProperty("store"))
						.replace("Area", getContext().getStringProperty("Area"))
						.replace("Dept", getContext().getStringProperty("Longlife"))
						.replace("Zone", getContext().getStringProperty("Zone")).replace("FinWk", Week)
						.replace("FinYr", getContext().getStringProperty("FinYr")).replace("Division", getContext().getStringProperty("Division")));
		System.out.println("DB Total: " + DBTotal);

		UITotal = pageMonthlyPlanningGroupPO.GetTotal(extentTest, objMonthlyPlanningGroupObjects.RowNameTotalLL,
				objWeeklyPlanningStoreObjects.FirstWeekofMonthLL);
		pageMonthlyPlanningPO.CompareValues(extentTest, DBTotal, UITotal);

		DBTotalSFD = sql.executeQuery(getContext(),
				PaidHoursHistoryNT.replace("store", getContext().getStringProperty("store"))
						.replace("Area", getContext().getStringProperty("Area"))
						.replace("Dept", getContext().getStringProperty("SeaFoodDeli"))
						.replace("Zone", getContext().getStringProperty("Zone")).replace("FinWk", Week)
						.replace("FinYr", getContext().getStringProperty("FinYr")).replace("Division", getContext().getStringProperty("Division")));
		System.out.println("DB Total: " + DBTotalSFD);

		UITotalSFD = pageMonthlyPlanningGroupPO.GetTotal(extentTest, objMonthlyPlanningGroupObjects.RowNameTotalSFD,
				objWeeklyPlanningStoreObjects.FirstWeekofMonthSFD);
		pageMonthlyPlanningPO.CompareValues(extentTest, DBTotalSFD, UITotalSFD);
	}

	@Test(description = "Weekly Planning - Store, Paid Hours history - Second Week", priority = 26)
	public void PaidHoursHistorySecondWeek() throws Exception {
		pageWeeklyPlanningPO = new WeeklyPlanningStorePage().createPage(getDriver(), getContext(), log, status, data,
				getReport(), getReportLogger(), WeeklyPlanningStorePage.class);
		pageMonthlyPlanningGroupPO = new MonthlyPlanningGroupPage().createPage(getDriver(), getContext(), log, status,
				data, getReport(), getReportLogger(), MonthlyPlanningGroupPage.class);
		TestStatus status = getTestStatus();
		System.out.println("Entering into TestCase");
		pageWeeklyPlanningPO.SelectHistoryDropdownToView(objWeeklyPlanningStoreObjects, extentTest, "Paid Hours");
		Name = pageWeeklyPlanningPO.MonthName(objWeeklyPlanningStoreObjects, extentTest,
				objWeeklyPlanningStoreObjects.SecondWeekofMonthColumnName);
		YrName = pageWeeklyPlanningPO.MonthName(objWeeklyPlanningStoreObjects, extentTest,
				objWeeklyPlanningStoreObjects.CurrentWeekBDGTNT);
		WeekName = Name.split(" ");
		YearName = YrName.split(" ");
		Week = WeekName[0];
		Year = getContext().getStringProperty("FinYr");

		String PaidHoursHistoryNT = data.getText("PaidHoursHistoryNT");
		DBTotal = sql.executeQuery(getContext(),
				PaidHoursHistoryNT.replace("store", getContext().getStringProperty("store"))
						.replace("Area", getContext().getStringProperty("Area"))
						.replace("Dept", getContext().getStringProperty("Longlife"))
						.replace("Zone", getContext().getStringProperty("Zone")).replace("FinWk", Week)
						.replace("FinYr", getContext().getStringProperty("FinYr")).replace("Division", getContext().getStringProperty("Division")));
		System.out.println("DB Total: " + DBTotal);

		UITotal = pageMonthlyPlanningGroupPO.GetTotal(extentTest, objMonthlyPlanningGroupObjects.RowNameTotalLL,
				objWeeklyPlanningStoreObjects.SecondWeekofMonthLL);
		pageMonthlyPlanningPO.CompareValues(extentTest, DBTotal, UITotal);

		DBTotalSFD = sql.executeQuery(getContext(),
				PaidHoursHistoryNT.replace("store", getContext().getStringProperty("store"))
						.replace("Area", getContext().getStringProperty("Area"))
						.replace("Dept", getContext().getStringProperty("SeaFoodDeli"))
						.replace("Zone", getContext().getStringProperty("Zone")).replace("FinWk", Week)
						.replace("FinYr", getContext().getStringProperty("FinYr")).replace("Division", getContext().getStringProperty("Division")));
		System.out.println("DB Total: " + DBTotalSFD);

		UITotalSFD = pageMonthlyPlanningGroupPO.GetTotal(extentTest, objMonthlyPlanningGroupObjects.RowNameTotalSFD,
				objWeeklyPlanningStoreObjects.SecondWeekofMonthSFD);
		pageMonthlyPlanningPO.CompareValues(extentTest, DBTotalSFD, UITotalSFD);
	}

	@Test(description = "Weekly Planning - Store, Paid Hours history - Third Week", priority = 27)
	public void PaidHoursHistoryThirdWeek() throws Exception {
		pageWeeklyPlanningPO = new WeeklyPlanningStorePage().createPage(getDriver(), getContext(), log, status, data,
				getReport(), getReportLogger(), WeeklyPlanningStorePage.class);
		pageMonthlyPlanningGroupPO = new MonthlyPlanningGroupPage().createPage(getDriver(), getContext(), log, status,
				data, getReport(), getReportLogger(), MonthlyPlanningGroupPage.class);
		TestStatus status = getTestStatus();
		System.out.println("Entering into TestCase");
		pageWeeklyPlanningPO.SelectHistoryDropdownToView(objWeeklyPlanningStoreObjects, extentTest, "Paid Hours");
		Name = pageWeeklyPlanningPO.MonthName(objWeeklyPlanningStoreObjects, extentTest,
				objWeeklyPlanningStoreObjects.ThirdWeekofMonthColumnName);
		YrName = pageWeeklyPlanningPO.MonthName(objWeeklyPlanningStoreObjects, extentTest,
				objWeeklyPlanningStoreObjects.CurrentWeekBDGTNT);
		WeekName = Name.split(" ");
		YearName = YrName.split(" ");
		Week = WeekName[0];
		Year = getContext().getStringProperty("FinYr");

		String PaidHoursHistoryNT = data.getText("PaidHoursHistoryNT");
		DBTotal = sql.executeQuery(getContext(),
				PaidHoursHistoryNT.replace("store", getContext().getStringProperty("store"))
						.replace("Area", getContext().getStringProperty("Area"))
						.replace("Dept", getContext().getStringProperty("Longlife"))
						.replace("Zone", getContext().getStringProperty("Zone")).replace("FinWk", Week)
						.replace("FinYr", getContext().getStringProperty("FinYr")).replace("Division", getContext().getStringProperty("Division")));
		System.out.println("DB Total: " + DBTotal);

		UITotal = pageMonthlyPlanningGroupPO.GetTotal(extentTest, objMonthlyPlanningGroupObjects.RowNameTotalLL,
				objWeeklyPlanningStoreObjects.ThirdWeekofMonthLL);
		pageMonthlyPlanningPO.CompareValues(extentTest, DBTotal, UITotal);

		DBTotalSFD = sql.executeQuery(getContext(),
				PaidHoursHistoryNT.replace("store", getContext().getStringProperty("store"))
						.replace("Area", getContext().getStringProperty("Area"))
						.replace("Dept", getContext().getStringProperty("SeaFoodDeli"))
						.replace("Zone", getContext().getStringProperty("Zone")).replace("FinWk", Week)
						.replace("FinYr", getContext().getStringProperty("FinYr")).replace("Division", getContext().getStringProperty("Division")));
		System.out.println("DB Total: " + DBTotalSFD);

		UITotalSFD = pageMonthlyPlanningGroupPO.GetTotal(extentTest, objMonthlyPlanningGroupObjects.RowNameTotalSFD,
				objWeeklyPlanningStoreObjects.ThirdWeekofMonthSFD);
		pageMonthlyPlanningPO.CompareValues(extentTest, DBTotalSFD, UITotalSFD);
	}

	@Test(description = "Weekly Planning - Store, Paid Hours history - Fourth Week", priority = 28)
	public void PaidHoursHistoryFourthWeek() throws Exception {
		pageWeeklyPlanningPO = new WeeklyPlanningStorePage().createPage(getDriver(), getContext(), log, status, data,
				getReport(), getReportLogger(), WeeklyPlanningStorePage.class);
		pageMonthlyPlanningGroupPO = new MonthlyPlanningGroupPage().createPage(getDriver(), getContext(), log, status,
				data, getReport(), getReportLogger(), MonthlyPlanningGroupPage.class);
		TestStatus status = getTestStatus();
		System.out.println("Entering into TestCase");
		pageWeeklyPlanningPO.SelectHistoryDropdownToView(objWeeklyPlanningStoreObjects, extentTest, "Paid Hours");
		Name = pageWeeklyPlanningPO.MonthName(objWeeklyPlanningStoreObjects, extentTest,
				objWeeklyPlanningStoreObjects.FourWeekofMonthColumnName);
		YrName = pageWeeklyPlanningPO.MonthName(objWeeklyPlanningStoreObjects, extentTest,
				objWeeklyPlanningStoreObjects.CurrentWeekBDGTNT);
		WeekName = Name.split(" ");
		YearName = YrName.split(" ");
		Week = WeekName[0];
		Year = getContext().getStringProperty("FinYr");

		String PaidHoursHistoryNT = data.getText("PaidHoursHistoryNT");
		DBTotal = sql.executeQuery(getContext(),
				PaidHoursHistoryNT.replace("store", getContext().getStringProperty("store"))
						.replace("Area", getContext().getStringProperty("Area"))
						.replace("Dept", getContext().getStringProperty("Longlife"))
						.replace("Zone", getContext().getStringProperty("Zone")).replace("FinWk", Week)
						.replace("FinYr", getContext().getStringProperty("FinYr")).replace("Division", getContext().getStringProperty("Division")));
		System.out.println("DB Total: " + DBTotal);

		UITotal = pageMonthlyPlanningGroupPO.GetTotal(extentTest, objMonthlyPlanningGroupObjects.RowNameTotalLL,
				objWeeklyPlanningStoreObjects.FourWeekofMonthLL);
		pageMonthlyPlanningPO.CompareValues(extentTest, DBTotal, UITotal);

		DBTotalSFD = sql.executeQuery(getContext(),
				PaidHoursHistoryNT.replace("store", getContext().getStringProperty("store"))
						.replace("Area", getContext().getStringProperty("Area"))
						.replace("Dept", getContext().getStringProperty("SeaFoodDeli"))
						.replace("Zone", getContext().getStringProperty("Zone")).replace("FinWk", Week)
						.replace("FinYr", getContext().getStringProperty("FinYr")).replace("Division", getContext().getStringProperty("Division")));
		System.out.println("DB Total: " + DBTotalSFD);

		UITotalSFD = pageMonthlyPlanningGroupPO.GetTotal(extentTest, objMonthlyPlanningGroupObjects.RowNameTotalSFD,
				objWeeklyPlanningStoreObjects.FourWeekofMonthSFD);
		pageMonthlyPlanningPO.CompareValues(extentTest, DBTotalSFD, UITotalSFD);
	}

	// CPH

	@Test(description = "Weekly Planning - Store, CPH history - First Week", priority = 29)
	public void CPHHistoryFirsthWeek() throws Exception {
		pageWeeklyPlanningPO = new WeeklyPlanningStorePage().createPage(getDriver(), getContext(), log, status, data,
				getReport(), getReportLogger(), WeeklyPlanningStorePage.class);
		pageMonthlyPlanningGroupPO = new MonthlyPlanningGroupPage().createPage(getDriver(), getContext(), log, status,
				data, getReport(), getReportLogger(), MonthlyPlanningGroupPage.class);
		TestStatus status = getTestStatus();
		System.out.println("Entering into TestCase");
		pageWeeklyPlanningPO.SelectHistoryDropdownToView(objWeeklyPlanningStoreObjects, extentTest, "CPH");
		Name = pageWeeklyPlanningPO.MonthName(objWeeklyPlanningStoreObjects, extentTest,
				objWeeklyPlanningStoreObjects.FirstWeekofMonthColumnName);
		YrName = pageWeeklyPlanningPO.MonthName(objWeeklyPlanningStoreObjects, extentTest,
				objWeeklyPlanningStoreObjects.CurrentWeekBDGTNT);
		WeekName = Name.split(" ");
		YearName = YrName.split(" ");
		Week = WeekName[0];
		Year = getContext().getStringProperty("FinYr");

		String CPHNT = data.getText("CPHHistoryNT");
		DBTotal = sql.executeQuery(getContext(),
				CPHNT.replace("store", getContext().getStringProperty("store"))
						.replace("Area", getContext().getStringProperty("Area"))
						.replace("Dept", getContext().getStringProperty("Longlife"))
						.replace("Zone", getContext().getStringProperty("Zone")).replace("FinWk", Week)
						.replace("FinYr", getContext().getStringProperty("FinYr")).replace("Division", getContext().getStringProperty("Division")));
		System.out.println("DB Total: " + DBTotal);

		UITotal = pageMonthlyPlanningGroupPO.GetTotal(extentTest, objMonthlyPlanningGroupObjects.RowNameTotalLL,
				objWeeklyPlanningStoreObjects.FirstWeekofMonthLL);
		pageMonthlyPlanningPO.CompareValues(extentTest, DBTotal, UITotal);

		DBTotalSFD = sql.executeQuery(getContext(),
				CPHNT.replace("store", getContext().getStringProperty("store"))
						.replace("Area", getContext().getStringProperty("Area"))
						.replace("Dept", getContext().getStringProperty("SeaFoodDeli"))
						.replace("Zone", getContext().getStringProperty("Zone")).replace("FinWk", Week)
						.replace("FinYr", getContext().getStringProperty("FinYr")).replace("Division", getContext().getStringProperty("Division")));
		System.out.println("DB Total: " + DBTotalSFD);

		UITotalSFD = pageMonthlyPlanningGroupPO.GetTotal(extentTest, objMonthlyPlanningGroupObjects.RowNameTotalSFD,
				objWeeklyPlanningStoreObjects.FirstWeekofMonthSFD);
		pageMonthlyPlanningPO.CompareValues(extentTest, DBTotalSFD, UITotalSFD);
	}

	@Test(description = "Weekly Planning - Store, CPH history - Second Week", priority = 30)
	public void CPHHistorySecondWeek() throws Exception {
		pageWeeklyPlanningPO = new WeeklyPlanningStorePage().createPage(getDriver(), getContext(), log, status, data,
				getReport(), getReportLogger(), WeeklyPlanningStorePage.class);
		pageMonthlyPlanningGroupPO = new MonthlyPlanningGroupPage().createPage(getDriver(), getContext(), log, status,
				data, getReport(), getReportLogger(), MonthlyPlanningGroupPage.class);
		TestStatus status = getTestStatus();
		System.out.println("Entering into TestCase");
		pageWeeklyPlanningPO.SelectHistoryDropdownToView(objWeeklyPlanningStoreObjects, extentTest, "CPH");
		Name = pageWeeklyPlanningPO.MonthName(objWeeklyPlanningStoreObjects, extentTest,
				objWeeklyPlanningStoreObjects.SecondWeekofMonthColumnName);
		YrName = pageWeeklyPlanningPO.MonthName(objWeeklyPlanningStoreObjects, extentTest,
				objWeeklyPlanningStoreObjects.CurrentWeekBDGTNT);
		WeekName = Name.split(" ");
		YearName = YrName.split(" ");
		Week = WeekName[0];
		Year = getContext().getStringProperty("FinYr");

		String CPHNT = data.getText("CPHHistoryNT");
		DBTotal = sql.executeQuery(getContext(),
				CPHNT.replace("store", getContext().getStringProperty("store"))
						.replace("Area", getContext().getStringProperty("Area"))
						.replace("Dept", getContext().getStringProperty("Longlife"))
						.replace("Zone", getContext().getStringProperty("Zone")).replace("FinWk", Week)
						.replace("FinYr", getContext().getStringProperty("FinYr")).replace("Division", getContext().getStringProperty("Division")));
		System.out.println("DB Total: " + DBTotal);

		UITotal = pageMonthlyPlanningGroupPO.GetTotal(extentTest, objMonthlyPlanningGroupObjects.RowNameTotalLL,
				objWeeklyPlanningStoreObjects.SecondWeekofMonthLL);
		pageMonthlyPlanningPO.CompareValues(extentTest, DBTotal, UITotal);

		DBTotalSFD = sql.executeQuery(getContext(),
				CPHNT.replace("store", getContext().getStringProperty("store"))
						.replace("Area", getContext().getStringProperty("Area"))
						.replace("Dept", getContext().getStringProperty("SeaFoodDeli"))
						.replace("Zone", getContext().getStringProperty("Zone")).replace("FinWk", Week)
						.replace("FinYr", getContext().getStringProperty("FinYr")).replace("Division", getContext().getStringProperty("Division")));
		System.out.println("DB Total: " + DBTotalSFD);

		UITotalSFD = pageMonthlyPlanningGroupPO.GetTotal(extentTest, objMonthlyPlanningGroupObjects.RowNameTotalSFD,
				objWeeklyPlanningStoreObjects.SecondWeekofMonthSFD);
		pageMonthlyPlanningPO.CompareValues(extentTest, DBTotalSFD, UITotalSFD);
	}

	@Test(description = "Weekly Planning - Store, CPH history - Third Week", priority = 31)
	public void CPHHistoryThirdWeek() throws Exception {
		pageWeeklyPlanningPO = new WeeklyPlanningStorePage().createPage(getDriver(), getContext(), log, status, data,
				getReport(), getReportLogger(), WeeklyPlanningStorePage.class);
		pageMonthlyPlanningGroupPO = new MonthlyPlanningGroupPage().createPage(getDriver(), getContext(), log, status,
				data, getReport(), getReportLogger(), MonthlyPlanningGroupPage.class);
		TestStatus status = getTestStatus();
		System.out.println("Entering into TestCase");
		pageWeeklyPlanningPO.SelectHistoryDropdownToView(objWeeklyPlanningStoreObjects, extentTest, "CPH");
		Name = pageWeeklyPlanningPO.MonthName(objWeeklyPlanningStoreObjects, extentTest,
				objWeeklyPlanningStoreObjects.ThirdWeekofMonthColumnName);
		YrName = pageWeeklyPlanningPO.MonthName(objWeeklyPlanningStoreObjects, extentTest,
				objWeeklyPlanningStoreObjects.CurrentWeekBDGTNT);
		WeekName = Name.split(" ");
		YearName = YrName.split(" ");
		Week = WeekName[0];
		Year = getContext().getStringProperty("FinYr");

		String CPHNT = data.getText("CPHHistoryNT");
		DBTotal = sql.executeQuery(getContext(),
				CPHNT.replace("store", getContext().getStringProperty("store"))
						.replace("Area", getContext().getStringProperty("Area"))
						.replace("Dept", getContext().getStringProperty("Longlife"))
						.replace("Zone", getContext().getStringProperty("Zone")).replace("FinWk", Week)
						.replace("FinYr", getContext().getStringProperty("FinYr")).replace("Division", getContext().getStringProperty("Division")));
		System.out.println("DB Total: " + DBTotal);

		UITotal = pageMonthlyPlanningGroupPO.GetTotal(extentTest, objMonthlyPlanningGroupObjects.RowNameTotalLL,
				objWeeklyPlanningStoreObjects.ThirdWeekofMonthLL);
		pageMonthlyPlanningPO.CompareValues(extentTest, DBTotal, UITotal);

		DBTotalSFD = sql.executeQuery(getContext(),
				CPHNT.replace("store", getContext().getStringProperty("store"))
						.replace("Area", getContext().getStringProperty("Area"))
						.replace("Dept", getContext().getStringProperty("SeaFoodDeli"))
						.replace("Zone", getContext().getStringProperty("Zone")).replace("FinWk", Week)
						.replace("FinYr", getContext().getStringProperty("FinYr")).replace("Division", getContext().getStringProperty("Division")));
		System.out.println("DB Total: " + DBTotalSFD);

		UITotalSFD = pageMonthlyPlanningGroupPO.GetTotal(extentTest, objMonthlyPlanningGroupObjects.RowNameTotalSFD,
				objWeeklyPlanningStoreObjects.ThirdWeekofMonthSFD);
		pageMonthlyPlanningPO.CompareValues(extentTest, DBTotalSFD, UITotalSFD);
	}

	@Test(description = "Weekly Planning - Store, CPH history - Fourth Week", priority = 32)
	public void CPHHistoryFourthWeek() throws Exception {
		pageWeeklyPlanningPO = new WeeklyPlanningStorePage().createPage(getDriver(), getContext(), log, status, data,
				getReport(), getReportLogger(), WeeklyPlanningStorePage.class);
		pageMonthlyPlanningGroupPO = new MonthlyPlanningGroupPage().createPage(getDriver(), getContext(), log, status,
				data, getReport(), getReportLogger(), MonthlyPlanningGroupPage.class);
		TestStatus status = getTestStatus();
		System.out.println("Entering into TestCase");
		pageWeeklyPlanningPO.SelectHistoryDropdownToView(objWeeklyPlanningStoreObjects, extentTest, "CPH");
		Name = pageWeeklyPlanningPO.MonthName(objWeeklyPlanningStoreObjects, extentTest,
				objWeeklyPlanningStoreObjects.FourWeekofMonthColumnName);
		YrName = pageWeeklyPlanningPO.MonthName(objWeeklyPlanningStoreObjects, extentTest,
				objWeeklyPlanningStoreObjects.CurrentWeekBDGTNT);
		WeekName = Name.split(" ");
		YearName = YrName.split(" ");
		Week = WeekName[0];
		Year = getContext().getStringProperty("FinYr");

		String CPHNT = data.getText("CPHHistoryNT");
		DBTotal = sql.executeQuery(getContext(),
				CPHNT.replace("store", getContext().getStringProperty("store"))
						.replace("Area", getContext().getStringProperty("Area"))
						.replace("Dept", getContext().getStringProperty("Longlife"))
						.replace("Zone", getContext().getStringProperty("Zone")).replace("FinWk", Week)
						.replace("FinYr", getContext().getStringProperty("FinYr")).replace("Division", getContext().getStringProperty("Division")));
		System.out.println("DB Total: " + DBTotal);

		UITotal = pageMonthlyPlanningGroupPO.GetTotal(extentTest, objMonthlyPlanningGroupObjects.RowNameTotalLL,
				objWeeklyPlanningStoreObjects.FourWeekofMonthLL);
		pageMonthlyPlanningPO.CompareValues(extentTest, DBTotal, UITotal);

		DBTotalSFD = sql.executeQuery(getContext(),
				CPHNT.replace("store", getContext().getStringProperty("store"))
						.replace("Area", getContext().getStringProperty("Area"))
						.replace("Dept", getContext().getStringProperty("SeaFoodDeli"))
						.replace("Zone", getContext().getStringProperty("Zone")).replace("FinWk", Week)
						.replace("FinYr", getContext().getStringProperty("FinYr")).replace("Division", getContext().getStringProperty("Division")));
		System.out.println("DB Total: " + DBTotalSFD);

		UITotalSFD = pageMonthlyPlanningGroupPO.GetTotal(extentTest, objMonthlyPlanningGroupObjects.RowNameTotalSFD,
				objWeeklyPlanningStoreObjects.FourWeekofMonthSFD);
		pageMonthlyPlanningPO.CompareValues(extentTest, DBTotalSFD, UITotalSFD);
	}

	// Operational Ratio
	/*
	 * @Test(description =
	 * "Weekly Planning - BU, OR History - First Week",priority=33) public void
	 * ORHistoryFirstWeek() throws Exception { pageWeeklyPlanningPO = new
	 * WeeklyPlanningStorePage().createPage(getDriver(), getContext(), log, status,
	 * data,getReport(), getReportLogger(), WeeklyPlanningStorePage.class);
	 * pageMonthlyPlanningGroupPO = new
	 * MonthlyPlanningGroupPage().createPage(getDriver(), getContext(), log, status,
	 * data,getReport(), getReportLogger(), MonthlyPlanningGroupPage.class);
	 * TestStatus status = getTestStatus();
	 * System.out.println("Entering into TestCase");
	 * pageWeeklyPlanningPO.SelectHistoryDropdownToView(
	 * objWeeklyPlanningStoreObjects,extentTest,"Operating Ratio History"); Name=
	 * pageWeeklyPlanningPO.MonthName(objWeeklyPlanningStoreObjects,
	 * extentTest,objWeeklyPlanningStoreObjects.FirstWeekofMonthColumnName); YrName=
	 * pageWeeklyPlanningPO.MonthName(objWeeklyPlanningStoreObjects,
	 * extentTest,objWeeklyPlanningStoreObjects.CurrentWeekBDGTNT); WeekName =
	 * Name.split(" "); YearName = YrName.split(" "); Week = WeekName[0]; Wk =
	 * getContext().getStringProperty("SelectFiscalWeek"); HistWk =
	 * Integer.parseInt(Wk) -4; HWk1 = String.valueOf(HistWk);
	 * 
	 * Report_AddStep(
	 * "testcase"," OR VIEW - FIRST HISTORY WEEK VALIDATION NEW TABLE" ,"","", "");
	 * 
	 * String ORHistoryNT = data.getText("ORHistoryNT"); DBTotal =
	 * sql.executeQuery(getContext(),
	 * ORHistoryNT.replace("store",getContext().getStringProperty("store"))
	 * .replace("Area",getContext().getStringProperty("Area")).replace("Dept",
	 * getContext().getStringProperty("Longlife"))
	 * .replace("Week",getContext().getStringProperty("SelectFiscalWeek1"))
	 * .replace("Zone",getContext().getStringProperty("Zone")).replace("FinWk",Week)
	 * .replace("FinYr",getContext().getStringProperty("FinYr")).replace("Division", getContext().getStringProperty("Division")));
	 * System.out.println("DB Total: "+DBTotal);
	 * 
	 * UITotal =
	 * pageMonthlyPlanningGroupPO.GetTotal(extentTest,objMonthlyPlanningGroupObjects
	 * .RowNameTotalLL,objWeeklyPlanningStoreObjects.FirstWeekofMonthLL);
	 * pageMonthlyPlanningPO.CompareValues(extentTest,DBTotal,UITotal);
	 * 
	 * 
	 * DBTotalSFD = sql.executeQuery(getContext(),
	 * ORHistoryNT.replace("store",getContext().getStringProperty("store"))
	 * .replace("Area",getContext().getStringProperty("Area")).replace("Dept",
	 * getContext().getStringProperty("SeaFoodDeli"))
	 * .replace("Week",getContext().getStringProperty("SelectFiscalWeek1"))
	 * .replace("Zone",getContext().getStringProperty("Zone")).replace("FinWk",Week)
	 * .replace("FinYr",getContext().getStringProperty("FinYr")).replace("Division", getContext().getStringProperty("Division")));
	 * System.out.println("DB Total: "+DBTotalSFD);
	 * 
	 * UITotalSFD =
	 * pageMonthlyPlanningGroupPO.GetTotal(extentTest,objMonthlyPlanningGroupObjects
	 * .RowNameTotalSFD,objWeeklyPlanningStoreObjects.FirstWeekofMonthSFD);
	 * pageMonthlyPlanningPO.CompareValues(extentTest,DBTotalSFD,UITotalSFD); }
	 * 
	 * @Test(description =
	 * "Weekly Planning - BU, OR History - Second Week",priority=34) public void
	 * ORHistorySecondWeek() throws Exception { pageWeeklyPlanningPO = new
	 * WeeklyPlanningStorePage().createPage(getDriver(), getContext(), log, status,
	 * data,getReport(), getReportLogger(), WeeklyPlanningStorePage.class);
	 * pageMonthlyPlanningGroupPO = new
	 * MonthlyPlanningGroupPage().createPage(getDriver(), getContext(), log, status,
	 * data,getReport(), getReportLogger(), MonthlyPlanningGroupPage.class);
	 * TestStatus status = getTestStatus();
	 * System.out.println("Entering into TestCase");
	 * pageWeeklyPlanningPO.SelectHistoryDropdownToView(
	 * objWeeklyPlanningStoreObjects,extentTest,"Operating Ratio History"); Name=
	 * pageWeeklyPlanningPO.MonthName(objWeeklyPlanningStoreObjects,
	 * extentTest,objWeeklyPlanningStoreObjects.SecondWeekofMonth); YrName=
	 * pageWeeklyPlanningPO.MonthName(objWeeklyPlanningStoreObjects,
	 * extentTest,objWeeklyPlanningStoreObjects.CurrentWeekBDGTNT); WeekName =
	 * Name.split(" "); YearName = YrName.split(" "); Week = WeekName[0]; Wk =
	 * getContext().getStringProperty("SelectFiscalWeek"); HistWk =
	 * Integer.parseInt(Wk) -3; HWk2 = String.valueOf(HistWk);
	 * 
	 * Report_AddStep(
	 * "testcase"," OR VIEW - FIRST HISTORY WEEK VALIDATION NEW TABLE" ,"","", "");
	 * 
	 * String ORHistoryNT = data.getText("ORHistoryNT"); DBTotal =
	 * sql.executeQuery(getContext(),
	 * ORHistoryNT.replace("store",getContext().getStringProperty("store"))
	 * .replace("Area",getContext().getStringProperty("Area")).replace("Dept",
	 * getContext().getStringProperty("Longlife"))
	 * .replace("Week",getContext().getStringProperty("SelectFiscalWeek2"))
	 * .replace("Zone",getContext().getStringProperty("Zone")).replace("FinWk",Week)
	 * .replace("FinYr",getContext().getStringProperty("FinYr")).replace("Division", getContext().getStringProperty("Division")));
	 * System.out.println("DB Total: "+DBTotal);
	 * 
	 * UITotal =
	 * pageMonthlyPlanningGroupPO.GetTotal(extentTest,objMonthlyPlanningGroupObjects
	 * .RowNameTotalLL,objWeeklyPlanningStoreObjects.SecondWeekofMonthLL);
	 * pageMonthlyPlanningPO.CompareValues(extentTest,DBTotal,UITotal);
	 * 
	 * 
	 * DBTotalSFD = sql.executeQuery(getContext(),
	 * ORHistoryNT.replace("store",getContext().getStringProperty("store"))
	 * .replace("Area",getContext().getStringProperty("Area")).replace("Dept",
	 * getContext().getStringProperty("SeaFoodDeli"))
	 * .replace("Week",getContext().getStringProperty("SelectFiscalWeek2"))
	 * .replace("Zone",getContext().getStringProperty("Zone")).replace("FinWk",Week)
	 * .replace("FinYr",getContext().getStringProperty("FinYr")).replace("Division", getContext().getStringProperty("Division")));
	 * System.out.println("DB Total: "+DBTotalSFD);
	 * 
	 * UITotalSFD =
	 * pageMonthlyPlanningGroupPO.GetTotal(extentTest,objMonthlyPlanningGroupObjects
	 * .RowNameTotalSFD,objWeeklyPlanningStoreObjects.SecondWeekofMonthSFD);
	 * pageMonthlyPlanningPO.CompareValues(extentTest,DBTotalSFD,UITotalSFD); }
	 * 
	 * @Test(description =
	 * "Weekly Planning - BU, OR History - Third Week",priority=35) public void
	 * ORHistoryThirdWeek() throws Exception { pageWeeklyPlanningPO = new
	 * WeeklyPlanningStorePage().createPage(getDriver(), getContext(), log, status,
	 * data,getReport(), getReportLogger(), WeeklyPlanningStorePage.class);
	 * pageMonthlyPlanningGroupPO = new
	 * MonthlyPlanningGroupPage().createPage(getDriver(), getContext(), log, status,
	 * data,getReport(), getReportLogger(), MonthlyPlanningGroupPage.class);
	 * TestStatus status = getTestStatus();
	 * System.out.println("Entering into TestCase");
	 * pageWeeklyPlanningPO.SelectHistoryDropdownToView(
	 * objWeeklyPlanningStoreObjects,extentTest,"Operating Ratio History"); Name=
	 * pageWeeklyPlanningPO.MonthName(objWeeklyPlanningStoreObjects,
	 * extentTest,objWeeklyPlanningStoreObjects.ThirdWeekofMonth); YrName=
	 * pageWeeklyPlanningPO.MonthName(objWeeklyPlanningStoreObjects,
	 * extentTest,objWeeklyPlanningStoreObjects.CurrentWeekBDGTNT); WeekName =
	 * Name.split(" "); YearName = YrName.split(" "); Week = WeekName[0]; Wk =
	 * getContext().getStringProperty("SelectFiscalWeek"); HistWk =
	 * Integer.parseInt(Wk) -2; HWk3 = String.valueOf(HistWk);
	 * 
	 * Report_AddStep("testcase"," OR VIEW - FIRST HISTORY WEEK VALIDATION" ,"","",
	 * "");
	 * 
	 * String ORHistoryNT = data.getText("ORHistoryNT"); DBTotal =
	 * sql.executeQuery(getContext(),
	 * ORHistoryNT.replace("store",getContext().getStringProperty("store"))
	 * .replace("Area",getContext().getStringProperty("Area")).replace("Dept",
	 * getContext().getStringProperty("Longlife"))
	 * .replace("Week",getContext().getStringProperty("SelectFiscalWeek3"))
	 * .replace("Zone",getContext().getStringProperty("Zone")).replace("FinWk",Week)
	 * .replace("FinYr",getContext().getStringProperty("FinYr")).replace("Division", getContext().getStringProperty("Division")));
	 * System.out.println("DB Total: "+DBTotal);
	 * 
	 * UITotal =
	 * pageMonthlyPlanningGroupPO.GetTotal(extentTest,objMonthlyPlanningGroupObjects
	 * .RowNameTotalLL,objWeeklyPlanningStoreObjects.ThirdWeekofMonthLL);
	 * pageMonthlyPlanningPO.CompareValues(extentTest,DBTotal,UITotal);
	 * 
	 * 
	 * DBTotalSFD = sql.executeQuery(getContext(),
	 * ORHistoryNT.replace("store",getContext().getStringProperty("store"))
	 * .replace("Area",getContext().getStringProperty("Area")).replace("Dept",
	 * getContext().getStringProperty("SeaFoodDeli"))
	 * .replace("Week",getContext().getStringProperty("SelectFiscalWeek3"))
	 * .replace("Zone",getContext().getStringProperty("Zone")).replace("FinWk",Week)
	 * .replace("FinYr",getContext().getStringProperty("FinYr")).replace("Division", getContext().getStringProperty("Division")));
	 * System.out.println("DB Total: "+DBTotalSFD);
	 * 
	 * UITotalSFD =
	 * pageMonthlyPlanningGroupPO.GetTotal(extentTest,objMonthlyPlanningGroupObjects
	 * .RowNameTotalSFD,objWeeklyPlanningStoreObjects.ThirdWeekofMonthSFD);
	 * pageMonthlyPlanningPO.CompareValues(extentTest,DBTotal,UITotalSFD); }
	 * 
	 * @Test(description =
	 * "Weekly Planning - BU, OR History - Fourth Week",priority=36) public void
	 * ORHistoryFourthWeek() throws Exception { pageWeeklyPlanningPO = new
	 * WeeklyPlanningStorePage().createPage(getDriver(), getContext(), log, status,
	 * data,getReport(), getReportLogger(), WeeklyPlanningStorePage.class);
	 * pageMonthlyPlanningGroupPO = new
	 * MonthlyPlanningGroupPage().createPage(getDriver(), getContext(), log, status,
	 * data,getReport(), getReportLogger(), MonthlyPlanningGroupPage.class);
	 * TestStatus status = getTestStatus();
	 * System.out.println("Entering into TestCase");
	 * pageWeeklyPlanningPO.SelectHistoryDropdownToView(
	 * objWeeklyPlanningStoreObjects,extentTest,"Operating Ratio History"); Name=
	 * pageWeeklyPlanningPO.MonthName(objWeeklyPlanningStoreObjects,
	 * extentTest,objWeeklyPlanningStoreObjects.FourWeekofMonth); YrName=
	 * pageWeeklyPlanningPO.MonthName(objWeeklyPlanningStoreObjects,
	 * extentTest,objWeeklyPlanningStoreObjects.CurrentWeekBDGTNT); WeekName =
	 * Name.split(" "); YearName = YrName.split(" "); Week = WeekName[0]; Wk =
	 * getContext().getStringProperty("SelectFiscalWeek"); HistWk =
	 * Integer.parseInt(Wk) -1; HWk4 = String.valueOf(HistWk);
	 * 
	 * Report_AddStep("testcase"," OR VIEW - FIRST HISTORY WEEK VALIDATION" ,"","",
	 * "");
	 * 
	 * String ORHistoryNT = data.getText("ORHistoryNT"); DBTotal =
	 * sql.executeQuery(getContext(),
	 * ORHistoryNT.replace("store",getContext().getStringProperty("store"))
	 * .replace("Area",getContext().getStringProperty("Area")).replace("Dept",
	 * getContext().getStringProperty("Longlife"))
	 * .replace("Week",getContext().getStringProperty("SelectFiscalWeek4"))
	 * .replace("Zone",getContext().getStringProperty("Zone")).replace("FinWk",Week)
	 * .replace("FinYr",getContext().getStringProperty("FinYr")).replace("Division", getContext().getStringProperty("Division")));
	 * System.out.println("DB Total: "+DBTotal);
	 * 
	 * UITotal =
	 * pageMonthlyPlanningGroupPO.GetTotal(extentTest,objMonthlyPlanningGroupObjects
	 * .RowNameTotalLL,objWeeklyPlanningStoreObjects.FourWeekofMonthLL);
	 * pageMonthlyPlanningPO.CompareValues(extentTest,DBTotal,UITotal);
	 * 
	 * 
	 * DBTotalSFD = sql.executeQuery(getContext(),
	 * ORHistoryNT.replace("store",getContext().getStringProperty("store"))
	 * .replace("Area",getContext().getStringProperty("Area")).replace("Dept",
	 * getContext().getStringProperty("SeaFoodDeli"))
	 * .replace("Week",getContext().getStringProperty("SelectFiscalWeek4"))
	 * .replace("Zone",getContext().getStringProperty("Zone")).replace("FinWk",Week)
	 * .replace("FinYr",getContext().getStringProperty("FinYr")).replace("Division", getContext().getStringProperty("Division")));
	 * System.out.println("DB Total: "+DBTotalSFD);
	 * 
	 * UITotalSFD =
	 * pageMonthlyPlanningGroupPO.GetTotal(extentTest,objMonthlyPlanningGroupObjects
	 * .RowNameTotalSFD,objWeeklyPlanningStoreObjects.FourWeekofMonthSFD);
	 * pageMonthlyPlanningPO.CompareValues(extentTest,DBTotalSFD,UITotalSFD); }
	 */

	////////////////////////// CURRENT MONTH ///////////////////////////////
	/*
	 * @Test(description =
	 * "Weekly Planning - Store, Budget Sales - Current Week",priority=37) public
	 * void BudgetSales_CurrentMonth() throws Exception { pageWeeklyPlanningPO = new
	 * WeeklyPlanningStorePage().createPage(getDriver(), getContext(), log, status,
	 * data,getReport(), getReportLogger(), WeeklyPlanningStorePage.class);
	 * pageMonthlyPlanningGroupPO = new
	 * WeeklyPlanningStorePage().createPage(getDriver(), getContext(), log, status,
	 * data,getReport(), getReportLogger(), MonthlyPlanningGroupPage.class);
	 * TestStatus status = getTestStatus();
	 * System.out.println("Entering into TestCase"); OptionToView =
	 * getContext().getStringProperty("OptionToView");
	 * if(OptionToView.contains("Budget")) Name=
	 * pageWeeklyPlanningPO.MonthName(objWeeklyPlanningStoreObjects,
	 * extentTest,objWeeklyPlanningStoreObjects.CurrentWeekBDGTNT);
	 * if(OptionToView.contains("Forecast")) Name=
	 * pageWeeklyPlanningPO.MonthName(objWeeklyPlanningStoreObjects,
	 * extentTest,objWeeklyPlanningStoreObjects.CurrentWeekFCSTNT); WeekName =
	 * Name.split(" "); Week = WeekName[0]; Year =
	 * getContext().getStringProperty("FinYr");
	 * 
	 * if(OptionToView.contains("Budget")) {
	 * 
	 * String BudgetSalesNT = data.getText("BudgetSalesNT"); DBTotal =
	 * sql.executeQuery(getContext(),
	 * BudgetSalesNT.replace("store",getContext().getStringProperty("store"))
	 * .replace("Area",getContext().getStringProperty("Area")).replace("Dept",
	 * getContext().getStringProperty("Longlife"))
	 * .replace("Zone",getContext().getStringProperty("Zone")).replace("FinWk",Week)
	 * .replace("FinYr",getContext().getStringProperty("FinYr")).replace("Division", getContext().getStringProperty("Division")));
	 * System.out.println("DB Total: "+DBTotal);
	 * 
	 * UITotal =
	 * pageMonthlyPlanningGroupPO.GetTotal(extentTest,objMonthlyPlanningGroupObjects
	 * .RowNameTotalLL,objWeeklyPlanningStoreObjects.BudgetSalesLL);
	 * pageMonthlyPlanningPO.CompareValues(extentTest,DBTotal,UITotal);
	 * 
	 * 
	 * DBTotalSFD = sql.executeQuery(getContext(),
	 * BudgetSalesNT.replace("store",getContext().getStringProperty("store"))
	 * .replace("Area",getContext().getStringProperty("Area")).replace("Dept",
	 * getContext().getStringProperty("SeaFoodDeli"))
	 * .replace("Zone",getContext().getStringProperty("Zone")).replace("FinWk",Week)
	 * .replace("FinYr",getContext().getStringProperty("FinYr")).replace("Division", getContext().getStringProperty("Division")));
	 * System.out.println("DB Total: "+DBTotalSFD);
	 * 
	 * UITotalSFD =
	 * pageMonthlyPlanningGroupPO.GetTotal(extentTest,objMonthlyPlanningGroupObjects
	 * .RowNameTotalSFD,objWeeklyPlanningStoreObjects.BudgetSalesSFD);
	 * pageMonthlyPlanningPO.CompareValues(extentTest,DBTotalSFD,UITotalSFD); }
	 * if(OptionToView.contains("Forecast")) {
	 * 
	 * String ForecastSalesNT = data.getText("ForecastSalesNT"); DBTotal =
	 * sql.executeQuery(getContext(),
	 * ForecastSalesNT.replace("store",getContext().getStringProperty("store"))
	 * .replace("Area",getContext().getStringProperty("Area")).replace("Dept",
	 * getContext().getStringProperty("Longlife"))
	 * .replace("Zone",getContext().getStringProperty("Zone")).replace("FinWk",Week)
	 * .replace("FinYr",getContext().getStringProperty("FinYr")).replace("Division", getContext().getStringProperty("Division")));
	 * System.out.println("DB Total: "+DBTotal);
	 * 
	 * UITotal =
	 * pageMonthlyPlanningGroupPO.GetTotal(extentTest,objMonthlyPlanningGroupObjects
	 * .RowNameTotalLL,objWeeklyPlanningStoreObjects.ForecastSalesLL);
	 * pageMonthlyPlanningPO.CompareValues(extentTest,DBTotal,UITotal);
	 * 
	 * 
	 * DBTotalSFD = sql.executeQuery(getContext(),
	 * ForecastSalesNT.replace("store",getContext().getStringProperty("store"))
	 * .replace("Area",getContext().getStringProperty("Area")).replace("Dept",
	 * getContext().getStringProperty("SeaFoodDeli"))
	 * .replace("Zone",getContext().getStringProperty("Zone")).replace("FinWk",Week)
	 * .replace("FinYr",getContext().getStringProperty("FinYr")).replace("Division", getContext().getStringProperty("Division")));
	 * System.out.println("DB Total: "+DBTotalSFD);
	 * 
	 * UITotalSFD =
	 * pageMonthlyPlanningGroupPO.GetTotal(extentTest,objMonthlyPlanningGroupObjects
	 * .RowNameTotalSFD,objWeeklyPlanningStoreObjects.ForecastSalesSFD);
	 * pageMonthlyPlanningPO.CompareValues(extentTest,DBTotalSFD,UITotalSFD); } }
	 * 
	 * @Test(description =
	 * "Weekly Planning - Store, Generated Sales- Current Week",priority=38) public
	 * void GeneratedSales_CurrentMonth() throws Exception { pageWeeklyPlanningPO =
	 * new WeeklyPlanningStorePage().createPage(getDriver(), getContext(), log,
	 * status, data,getReport(), getReportLogger(), WeeklyPlanningStorePage.class);
	 * pageMonthlyPlanningGroupPO = new
	 * WeeklyPlanningStorePage().createPage(getDriver(), getContext(), log, status,
	 * data,getReport(), getReportLogger(), MonthlyPlanningGroupPage.class);
	 * TestStatus status = getTestStatus();
	 * System.out.println("Entering into TestCase"); OptionToView =
	 * getContext().getStringProperty("OptionToView");
	 * if(OptionToView.contains("Budget")) Name=
	 * pageWeeklyPlanningPO.MonthName(objWeeklyPlanningStoreObjects,
	 * extentTest,objWeeklyPlanningStoreObjects.CurrentWeekBDGTNT);
	 * if(OptionToView.contains("Forecast")) Name=
	 * pageWeeklyPlanningPO.MonthName(objWeeklyPlanningStoreObjects,
	 * extentTest,objWeeklyPlanningStoreObjects.CurrentWeekFCSTNT); WeekName =
	 * Name.split(" "); Week = WeekName[0]; Year =
	 * getContext().getStringProperty("FinYr");
	 * 
	 * String GeneratedSalesNT = data.getText("GeneratedSalesNT"); DBTotal =
	 * sql.executeQuery(getContext(),
	 * GeneratedSalesNT.replace("store",getContext().getStringProperty("store"))
	 * .replace("Area",getContext().getStringProperty("Area")).replace("Dept",
	 * getContext().getStringProperty("Longlife"))
	 * .replace("Zone",getContext().getStringProperty("Zone")).replace("FinWk",Week)
	 * .replace("FinYr",getContext().getStringProperty("FinYr")).replace("Division", getContext().getStringProperty("Division")));
	 * System.out.println("DB Total: "+DBTotal);
	 * 
	 * UITotal =
	 * pageMonthlyPlanningGroupPO.GetTotal(extentTest,objMonthlyPlanningGroupObjects
	 * .RowNameTotalLL,objWeeklyPlanningStoreObjects.GeneratedSalesLL);
	 * pageMonthlyPlanningPO.CompareValues(extentTest,DBTotal,UITotal);
	 * 
	 * 
	 * DBTotalSFD = sql.executeQuery(getContext(),
	 * GeneratedSalesNT.replace("store",getContext().getStringProperty("store"))
	 * .replace("Area",getContext().getStringProperty("Area")).replace("Dept",
	 * getContext().getStringProperty("SeaFoodDeli"))
	 * .replace("Zone",getContext().getStringProperty("Zone")).replace("FinWk",Week)
	 * .replace("FinYr",getContext().getStringProperty("FinYr")).replace("Division", getContext().getStringProperty("Division")));
	 * System.out.println("DB Total: "+DBTotalSFD);
	 * 
	 * UITotalSFD =
	 * pageMonthlyPlanningGroupPO.GetTotal(extentTest,objMonthlyPlanningGroupObjects
	 * .RowNameTotalSFD,objWeeklyPlanningStoreObjects.GeneratedSalesSFD);
	 * pageMonthlyPlanningPO.CompareValues(extentTest,DBTotalSFD,UITotalSFD); }
	 * 
	 * @Test(description =
	 * "Weekly Planning - Store, Planned Sales- Current Week",priority=39) public
	 * void PlannedSales_CurrentMonth() throws Exception { pageWeeklyPlanningPO =
	 * new WeeklyPlanningStorePage().createPage(getDriver(), getContext(), log,
	 * status, data,getReport(), getReportLogger(), WeeklyPlanningStorePage.class);
	 * pageMonthlyPlanningGroupPO = new
	 * WeeklyPlanningStorePage().createPage(getDriver(), getContext(), log, status,
	 * data,getReport(), getReportLogger(), MonthlyPlanningGroupPage.class);
	 * TestStatus status = getTestStatus(); OptionToView =
	 * getContext().getStringProperty("OptionToView");
	 * if(OptionToView.contains("Budget")) Name=
	 * pageWeeklyPlanningPO.MonthName(objWeeklyPlanningStoreObjects,
	 * extentTest,objWeeklyPlanningStoreObjects.CurrentWeekBDGTNT);
	 * if(OptionToView.contains("Forecast")) Name=
	 * pageWeeklyPlanningPO.MonthName(objWeeklyPlanningStoreObjects,
	 * extentTest,objWeeklyPlanningStoreObjects.CurrentWeekFCSTNT); WeekName =
	 * Name.split(" "); Week = WeekName[0]; Year =
	 * getContext().getStringProperty("FinYr");
	 * 
	 * String PlannedSalesNT = data.getText("PlannedSalesNT"); DBTotal =
	 * sql.executeQuery(getContext(),
	 * PlannedSalesNT.replace("store",getContext().getStringProperty("store"))
	 * .replace("Area",getContext().getStringProperty("Area")).replace("Dept",
	 * getContext().getStringProperty("Longlife"))
	 * .replace("Zone",getContext().getStringProperty("Zone")).replace("FinWk",Week)
	 * .replace("FinYr",getContext().getStringProperty("FinYr")).replace("Division", getContext().getStringProperty("Division")));
	 * System.out.println("DB Total: "+DBTotal);
	 * 
	 * UITotal =
	 * pageMonthlyPlanningGroupPO.GetTotal(extentTest,objMonthlyPlanningGroupObjects
	 * .RowNameTotalLL,objWeeklyPlanningStoreObjects.PlannedSalesLL);
	 * pageMonthlyPlanningPO.CompareValues(extentTest,DBTotal,UITotal);
	 * 
	 * 
	 * DBTotalSFD = sql.executeQuery(getContext(),
	 * PlannedSalesNT.replace("store",getContext().getStringProperty("store"))
	 * .replace("Area",getContext().getStringProperty("Area")).replace("Dept",
	 * getContext().getStringProperty("SeaFoodDeli"))
	 * .replace("Zone",getContext().getStringProperty("Zone")).replace("FinWk",Week)
	 * .replace("FinYr",getContext().getStringProperty("FinYr")).replace("Division", getContext().getStringProperty("Division")));
	 * System.out.println("DB Total: "+DBTotalSFD);
	 * 
	 * UITotalSFD =
	 * pageMonthlyPlanningGroupPO.GetTotal(extentTest,objMonthlyPlanningGroupObjects
	 * .RowNameTotalSFD,objWeeklyPlanningStoreObjects.PlannedSalesSFD);
	 * pageMonthlyPlanningPO.CompareValues(extentTest,DBTotalSFD,UITotalSFD); }
	 * 
	 * //Var to BUD
	 * 
	 * @Test(description =
	 * "Weekly Planning - Store, Var to Bud - Current Week",priority=40) public void
	 * VarToBUDSales_CurrentMonth() throws Exception { pageWeeklyPlanningPO = new
	 * WeeklyPlanningStorePage().createPage(getDriver(), getContext(), log, status,
	 * data,getReport(), getReportLogger(), WeeklyPlanningStorePage.class);
	 * pageMonthlyPlanningGroupPO = new
	 * WeeklyPlanningStorePage().createPage(getDriver(), getContext(), log, status,
	 * data,getReport(), getReportLogger(), MonthlyPlanningGroupPage.class);
	 * TestStatus status = getTestStatus();
	 * System.out.println("Entering into TestCase"); OptionToView =
	 * getContext().getStringProperty("OptionToView");
	 * if(OptionToView.contains("Budget")) Name=
	 * pageWeeklyPlanningPO.MonthName(objWeeklyPlanningStoreObjects,
	 * extentTest,objWeeklyPlanningStoreObjects.CurrentWeekBDGTNT);
	 * if(OptionToView.contains("Forecast")) Name=
	 * pageWeeklyPlanningPO.MonthName(objWeeklyPlanningStoreObjects,
	 * extentTest,objWeeklyPlanningStoreObjects.CurrentWeekFCSTNT); WeekName =
	 * Name.split(" "); Week = WeekName[0]; Year =
	 * getContext().getStringProperty("FinYr");
	 * 
	 * if(OptionToView.contains("Budget")) {
	 * 
	 * String VartoBUDNT = data.getText("VartoBUDNT"); DBTotal =
	 * sql.executeQuery(getContext(),
	 * VartoBUDNT.replace("store",getContext().getStringProperty("store"))
	 * .replace("Area",getContext().getStringProperty("Area")).replace("Dept",
	 * getContext().getStringProperty("Longlife"))
	 * .replace("Zone",getContext().getStringProperty("Zone")).replace("FinWk",Week)
	 * .replace("FinYr",getContext().getStringProperty("FinYr")).replace("Division", getContext().getStringProperty("Division")));
	 * System.out.println("DB Total: "+DBTotal);
	 * 
	 * UITotal =
	 * pageMonthlyPlanningGroupPO.GetTotal(extentTest,objMonthlyPlanningGroupObjects
	 * .RowNameTotalLL,objWeeklyPlanningStoreObjects.VarToBudSalesLL);
	 * pageMonthlyPlanningPO.CompareValues(extentTest,DBTotal,UITotal);
	 * 
	 * 
	 * DBTotalSFD = sql.executeQuery(getContext(),
	 * VartoBUDNT.replace("store",getContext().getStringProperty("store"))
	 * .replace("Area",getContext().getStringProperty("Area")).replace("Dept",
	 * getContext().getStringProperty("SeaFoodDeli"))
	 * .replace("Zone",getContext().getStringProperty("Zone")).replace("FinWk",Week)
	 * .replace("FinYr",getContext().getStringProperty("FinYr")).replace("Division", getContext().getStringProperty("Division")));
	 * System.out.println("DB Total: "+DBTotalSFD);
	 * 
	 * UITotalSFD =
	 * pageMonthlyPlanningGroupPO.GetTotal(extentTest,objMonthlyPlanningGroupObjects
	 * .RowNameTotalSFD,objWeeklyPlanningStoreObjects.VarToBudSalesSFD);
	 * pageMonthlyPlanningPO.CompareValues(extentTest,DBTotalSFD,UITotalSFD); }
	 * if(OptionToView.contains("Forecast")) {
	 * 
	 * String VartoFCSTNT = data.getText("VartoFCSTNT"); DBTotal =
	 * sql.executeQuery(getContext(),
	 * VartoFCSTNT.replace("store",getContext().getStringProperty("store"))
	 * .replace("Area",getContext().getStringProperty("Area")).replace("Dept",
	 * getContext().getStringProperty("Longlife"))
	 * .replace("Zone",getContext().getStringProperty("Zone")).replace("FinWk",Week)
	 * .replace("FinYr",getContext().getStringProperty("FinYr")).replace("Division", getContext().getStringProperty("Division")));
	 * System.out.println("DB Total: "+DBTotal);
	 * 
	 * UITotal =
	 * pageMonthlyPlanningGroupPO.GetTotal(extentTest,objMonthlyPlanningGroupObjects
	 * .RowNameTotalLL,objWeeklyPlanningStoreObjects.VarToFcstSalesLL);
	 * pageMonthlyPlanningPO.CompareValues(extentTest,DBTotal,UITotal);
	 * 
	 * 
	 * DBTotal = sql.executeQuery(getContext(),
	 * VartoFCSTNT.replace("store",getContext().getStringProperty("store"))
	 * .replace("Area",getContext().getStringProperty("Area")).replace("Dept",
	 * getContext().getStringProperty("SeaFoodDeli"))
	 * .replace("Zone",getContext().getStringProperty("Zone")).replace("FinWk",Week)
	 * .replace("FinYr",getContext().getStringProperty("FinYr")).replace("Division", getContext().getStringProperty("Division")));
	 * System.out.println("DB Total: "+DBTotal);
	 * 
	 * UITotal =
	 * pageMonthlyPlanningGroupPO.GetTotal(extentTest,objMonthlyPlanningGroupObjects
	 * .RowNameTotalSFD,objWeeklyPlanningStoreObjects.VarToFcstSalesSFD);
	 * pageMonthlyPlanningPO.CompareValues(extentTest,DBTotal,UITotal); } }
	 * 
	 * // +/- LY%
	 * 
	 * @Test(description =
	 * "Weekly Planning - Store, +/- LY% - Current Week",priority=41) public void
	 * PercentageLYsales_CurrentMonth() throws Exception { pageWeeklyPlanningPO =
	 * new WeeklyPlanningStorePage().createPage(getDriver(), getContext(), log,
	 * status, data,getReport(), getReportLogger(), WeeklyPlanningStorePage.class);
	 * pageMonthlyPlanningGroupPO = new
	 * WeeklyPlanningStorePage().createPage(getDriver(), getContext(), log, status,
	 * data,getReport(), getReportLogger(), MonthlyPlanningGroupPage.class);
	 * TestStatus status = getTestStatus();
	 * System.out.println("Entering into TestCase"); OptionToView =
	 * getContext().getStringProperty("OptionToView");
	 * if(OptionToView.contains("Budget")) Name=
	 * pageWeeklyPlanningPO.MonthName(objWeeklyPlanningStoreObjects,
	 * extentTest,objWeeklyPlanningStoreObjects.CurrentWeekBDGTNT);
	 * if(OptionToView.contains("Forecast")) Name=
	 * pageWeeklyPlanningPO.MonthName(objWeeklyPlanningStoreObjects,
	 * extentTest,objWeeklyPlanningStoreObjects.CurrentWeekFCSTNT); WeekName =
	 * Name.split(" "); Week = WeekName[0]; Year =
	 * getContext().getStringProperty("FinYr"); LYYear = Integer.parseInt(Year) -1;
	 * LYYr = String.valueOf(LYYear);
	 * 
	 * String PercentLYSalesNT = data.getText("PercentLYSalesNT"); DBTotal =
	 * sql.executeQuery(getContext(),
	 * PercentLYSalesNT.replace("store",getContext().getStringProperty("store"))
	 * .replace("Area",getContext().getStringProperty("Area")).replace("Dept",
	 * getContext().getStringProperty("Longlife"))
	 * .replace("Zone",getContext().getStringProperty("Zone")).replace("FinWk",
	 * getContext().getStringProperty("FinWk")).replace("FinYr",getContext().
	 * getStringProperty("FinYr")).replace("LYYr",LYYr).replace("Division",
	 * getContext().getStringProperty("Division")));
	 * System.out.println("DB Total: "+DBTotal);
	 * 
	 * UITotal =
	 * pageMonthlyPlanningGroupPO.GetTotal(extentTest,objMonthlyPlanningGroupObjects
	 * .RowNameTotalLL,objWeeklyPlanningStoreObjects.PercentageLYSalesLL);
	 * pageMonthlyPlanningPO.CompareValueDecimalFields(extentTest,DBTotal,UITotal);
	 * 
	 * 
	 * DBTotalSFD = sql.executeQuery(getContext(),
	 * PercentLYSalesNT.replace("store",getContext().getStringProperty("store"))
	 * .replace("Area",getContext().getStringProperty("Area")).replace("Dept",
	 * getContext().getStringProperty("SeaFoodDeli"))
	 * .replace("Zone",getContext().getStringProperty("Zone")).replace("FinWk",
	 * getContext().getStringProperty("FinWk")).replace("FinYr",getContext().
	 * getStringProperty("FinYr")).replace("LYYr",LYYr).replace("Division",
	 * getContext().getStringProperty("Division")));
	 * System.out.println("DB Total: "+DBTotalSFD);
	 * 
	 * UITotalSFD =
	 * pageMonthlyPlanningGroupPO.GetTotal(extentTest,objMonthlyPlanningGroupObjects
	 * .RowNameTotalSFD,objWeeklyPlanningStoreObjects.PercentageLYSalesSFD);
	 * pageMonthlyPlanningPO.CompareValueDecimalFields(extentTest,DBTotalSFD,
	 * UITotalSFD); }
	 * 
	 * // +/- Bud%
	 * 
	 * @Test(description =
	 * "Weekly Planning - Store, +/- Bud% - Current Week",priority=42) public void
	 * BudgetPercentageSales_CurrentMonth() throws Exception { pageWeeklyPlanningPO
	 * = new WeeklyPlanningStorePage().createPage(getDriver(), getContext(), log,
	 * status, data,getReport(), getReportLogger(), WeeklyPlanningStorePage.class);
	 * pageMonthlyPlanningGroupPO = new
	 * WeeklyPlanningStorePage().createPage(getDriver(), getContext(), log, status,
	 * data,getReport(), getReportLogger(), MonthlyPlanningGroupPage.class);
	 * TestStatus status = getTestStatus();
	 * System.out.println("Entering into TestCase"); OptionToView =
	 * getContext().getStringProperty("OptionToView");
	 * if(OptionToView.contains("Budget")) Name=
	 * pageWeeklyPlanningPO.MonthName(objWeeklyPlanningStoreObjects,
	 * extentTest,objWeeklyPlanningStoreObjects.CurrentWeekBDGTNT);
	 * if(OptionToView.contains("Forecast")) Name=
	 * pageWeeklyPlanningPO.MonthName(objWeeklyPlanningStoreObjects,
	 * extentTest,objWeeklyPlanningStoreObjects.CurrentWeekFCSTNT); WeekName =
	 * Name.split(" "); Week = WeekName[0]; Year =
	 * getContext().getStringProperty("FinYr");
	 * 
	 * if(OptionToView.contains("Budget")) {
	 * 
	 * String BudgetPercentageNT = data.getText("BudgetPercentageNT"); DBTotal =
	 * sql.executeQuery(getContext(),
	 * BudgetPercentageNT.replace("store",getContext().getStringProperty("store"))
	 * .replace("Area",getContext().getStringProperty("Area")).replace("Dept",
	 * getContext().getStringProperty("Longlife"))
	 * .replace("Zone",getContext().getStringProperty("Zone")).replace("FinWk",Week)
	 * .replace("FinYr",getContext().getStringProperty("FinYr")).replace("Division", getContext().getStringProperty("Division")));
	 * System.out.println("DB Total: "+DBTotal);
	 * 
	 * UITotal =
	 * pageMonthlyPlanningGroupPO.GetTotal(extentTest,objMonthlyPlanningGroupObjects
	 * .RowNameTotalLL,objWeeklyPlanningStoreObjects.PercentageBudgetSalesLL);
	 * pageMonthlyPlanningPO.CompareValueDecimalFields(extentTest,DBTotal,UITotal);
	 * 
	 * 
	 * DBTotalSFD = sql.executeQuery(getContext(),
	 * BudgetPercentageNT.replace("store",getContext().getStringProperty("store"))
	 * .replace("Area",getContext().getStringProperty("Area")).replace("Dept",
	 * getContext().getStringProperty("SeaFoodDeli"))
	 * .replace("Zone",getContext().getStringProperty("Zone")).replace("FinWk",Week)
	 * .replace("FinYr",getContext().getStringProperty("FinYr")).replace("Division", getContext().getStringProperty("Division")));
	 * System.out.println("DB Total: "+DBTotalSFD);
	 * 
	 * UITotalSFD =
	 * pageMonthlyPlanningGroupPO.GetTotal(extentTest,objMonthlyPlanningGroupObjects
	 * .RowNameTotalSFD,objWeeklyPlanningStoreObjects.PercentageBudgetSalesSFD);
	 * pageMonthlyPlanningPO.CompareValueDecimalFields(extentTest,DBTotalSFD,
	 * UITotalSFD); } if(OptionToView.contains("Forecast")) {
	 * 
	 * String ForecastPercentageNT = data.getText("ForecastPercentageSalesNT");
	 * DBTotal = sql.executeQuery(getContext(),
	 * ForecastPercentageNT.replace("store",getContext().getStringProperty("store"))
	 * .replace("Area",getContext().getStringProperty("Area")).replace("Dept",
	 * getContext().getStringProperty("Longlife"))
	 * .replace("Zone",getContext().getStringProperty("Zone")).replace("FinWk",Week)
	 * .replace("FinYr",getContext().getStringProperty("FinYr")).replace("Division", getContext().getStringProperty("Division")));
	 * System.out.println("DB Total: "+DBTotal);
	 * 
	 * UITotal =
	 * pageMonthlyPlanningGroupPO.GetTotal(extentTest,objMonthlyPlanningGroupObjects
	 * .RowNameTotalLL,objWeeklyPlanningStoreObjects.PercentageFcstSalesLL);
	 * pageMonthlyPlanningPO.CompareValueDecimalFields(extentTest,DBTotal,UITotal);
	 * 
	 * 
	 * DBTotalSFD = sql.executeQuery(getContext(),
	 * ForecastPercentageNT.replace("store",getContext().getStringProperty("store"))
	 * .replace("Area",getContext().getStringProperty("Area")).replace("Dept",
	 * getContext().getStringProperty("SeaFoodDeli"))
	 * .replace("Zone",getContext().getStringProperty("Zone")).replace("FinWk",Week)
	 * .replace("FinYr",getContext().getStringProperty("FinYr")).replace("Division", getContext().getStringProperty("Division")));
	 * System.out.println("DB Total: "+DBTotalSFD);
	 * 
	 * UITotalSFD =
	 * pageMonthlyPlanningGroupPO.GetTotal(extentTest,objMonthlyPlanningGroupObjects
	 * .RowNameTotalSFD,objWeeklyPlanningStoreObjects.PercentageFcstSalesSFD);
	 * pageMonthlyPlanningPO.CompareValueDecimalFields(extentTest,DBTotalSFD,
	 * UITotalSFD); } }
	 * 
	 * // Planned Item Price
	 * 
	 * @Test(description =
	 * "Weekly Planning - Store, Planned Item Price - Current Week",priority=43)
	 * public void PlannedItemPrice_CurrentMonth() throws Exception {
	 * pageWeeklyPlanningPO = new WeeklyPlanningStorePage().createPage(getDriver(),
	 * getContext(), log, status, data,getReport(), getReportLogger(),
	 * WeeklyPlanningStorePage.class); pageMonthlyPlanningGroupPO = new
	 * WeeklyPlanningStorePage().createPage(getDriver(), getContext(), log, status,
	 * data,getReport(), getReportLogger(), MonthlyPlanningGroupPage.class);
	 * TestStatus status = getTestStatus();
	 * System.out.println("Entering into TestCase"); OptionToView =
	 * getContext().getStringProperty("OptionToView");
	 * if(OptionToView.contains("Budget")) Name=
	 * pageWeeklyPlanningPO.MonthName(objWeeklyPlanningStoreObjects,
	 * extentTest,objWeeklyPlanningStoreObjects.CurrentWeekBDGTNT);
	 * if(OptionToView.contains("Forecast")) Name=
	 * pageWeeklyPlanningPO.MonthName(objWeeklyPlanningStoreObjects,
	 * extentTest,objWeeklyPlanningStoreObjects.CurrentWeekFCSTNT); WeekName =
	 * Name.split(" "); Week = WeekName[0]; Year =
	 * getContext().getStringProperty("FinYr");
	 * 
	 * String PlannedItemPriceNT = data.getText("PlannedItemPriceNT"); DBTotal =
	 * sql.executeQuery(getContext(),
	 * PlannedItemPriceNT.replace("store",getContext().getStringProperty("store"))
	 * .replace("Area",getContext().getStringProperty("Area")).replace("Dept",
	 * getContext().getStringProperty("Longlife"))
	 * .replace("Zone",getContext().getStringProperty("Zone")).replace("FinWk",Week)
	 * .replace("FinYr",getContext().getStringProperty("FinYr")).replace("Division", getContext().getStringProperty("Division")));
	 * System.out.println("DB Total: "+DBTotal);
	 * 
	 * UITotal =
	 * pageMonthlyPlanningGroupPO.GetTotal(extentTest,objMonthlyPlanningGroupObjects
	 * .RowNameTotalLL,objWeeklyPlanningStoreObjects.PlannedItemPriceLL);
	 * pageMonthlyPlanningPO.CompareValueDecimalFields(extentTest,DBTotal,UITotal);
	 * 
	 * 
	 * DBTotalSFD = sql.executeQuery(getContext(),
	 * PlannedItemPriceNT.replace("store",getContext().getStringProperty("store"))
	 * .replace("Area",getContext().getStringProperty("Area")).replace("Dept",
	 * getContext().getStringProperty("SeaFoodDeli"))
	 * .replace("Zone",getContext().getStringProperty("Zone")).replace("FinWk",Week)
	 * .replace("FinYr",getContext().getStringProperty("FinYr")).replace("Division", getContext().getStringProperty("Division")));
	 * System.out.println("DB Total: "+DBTotalSFD);
	 * 
	 * UITotalSFD =
	 * pageMonthlyPlanningGroupPO.GetTotal(extentTest,objMonthlyPlanningGroupObjects
	 * .RowNameTotalSFD,objWeeklyPlanningStoreObjects.PlannedItemPriceSFD);
	 * pageMonthlyPlanningPO.CompareValueDecimalFields(extentTest,DBTotalSFD,
	 * UITotalSFD); }
	 * 
	 * //Planned percent on LY
	 * 
	 * @Test(description =
	 * "Weekly Planning - Store, Planned percent on LY - Current Week",priority=44)
	 * public void PlannedPercentOnLY_CurrentMonth() throws Exception {
	 * pageWeeklyPlanningPO = new WeeklyPlanningStorePage().createPage(getDriver(),
	 * getContext(), log, status, data,getReport(), getReportLogger(),
	 * WeeklyPlanningStorePage.class); pageMonthlyPlanningGroupPO = new
	 * WeeklyPlanningStorePage().createPage(getDriver(), getContext(), log, status,
	 * data,getReport(), getReportLogger(), MonthlyPlanningGroupPage.class);
	 * TestStatus status = getTestStatus();
	 * System.out.println("Entering into TestCase"); OptionToView =
	 * getContext().getStringProperty("OptionToView");
	 * if(OptionToView.contains("Budget")) Name=
	 * pageWeeklyPlanningPO.MonthName(objWeeklyPlanningStoreObjects,
	 * extentTest,objWeeklyPlanningStoreObjects.CurrentWeekBDGTNT);
	 * if(OptionToView.contains("Forecast")) Name=
	 * pageWeeklyPlanningPO.MonthName(objWeeklyPlanningStoreObjects,
	 * extentTest,objWeeklyPlanningStoreObjects.CurrentWeekFCSTNT); WeekName =
	 * Name.split(" "); Week = WeekName[0]; Year =
	 * getContext().getStringProperty("FinYr"); LYYear = Integer.parseInt(Year) -1;
	 * LYYr = String.valueOf(LYYear);
	 * 
	 * String PlannedpercentonLYNT = data.getText("PlannedpercentonLYNT"); DBTotal =
	 * sql.executeQuery(getContext(),
	 * PlannedpercentonLYNT.replace("store",getContext().getStringProperty("store"))
	 * .replace("Area",getContext().getStringProperty("Area")).replace("Dept",
	 * getContext().getStringProperty("Longlife"))
	 * .replace("Zone",getContext().getStringProperty("Zone")).replace("FinWk",Week)
	 * .replace("FinYr",getContext().getStringProperty("FinYr")).replace("LYYr",LYYr
	 * )); System.out.println("DB Total: "+DBTotal);
	 * 
	 * UITotal =
	 * pageMonthlyPlanningGroupPO.GetTotal(extentTest,objMonthlyPlanningGroupObjects
	 * .RowNameTotalLL,objWeeklyPlanningStoreObjects.PlannedPercentageonLYLL);
	 * pageMonthlyPlanningPO.CompareValueDecimalFields(extentTest,DBTotal,UITotal);
	 * 
	 * 
	 * DBTotalSFD = sql.executeQuery(getContext(),
	 * PlannedpercentonLYNT.replace("store",getContext().getStringProperty("store"))
	 * .replace("Area",getContext().getStringProperty("Area")).replace("Dept",
	 * getContext().getStringProperty("SeaFoodDeli"))
	 * .replace("Zone",getContext().getStringProperty("Zone")).replace("FinWk",Week)
	 * .replace("FinYr",getContext().getStringProperty("FinYr")).replace("LYYr",LYYr
	 * )); System.out.println("DB Total: "+DBTotalSFD);
	 * 
	 * UITotalSFD =
	 * pageMonthlyPlanningGroupPO.GetTotal(extentTest,objMonthlyPlanningGroupObjects
	 * .RowNameTotalSFD,objWeeklyPlanningStoreObjects.PlannedPercentageonLYSFD);
	 * pageMonthlyPlanningPO.CompareValueDecimalFields(extentTest,DBTotalSFD,
	 * UITotalSFD); }
	 * 
	 * //Planned Items
	 * 
	 * @Test(description =
	 * "Weekly Planning - Store, Planned Items - Current Week",priority=45) public
	 * void PlannedItems_CurrentMonth() throws Exception { pageWeeklyPlanningPO =
	 * new WeeklyPlanningStorePage().createPage(getDriver(), getContext(), log,
	 * status, data,getReport(), getReportLogger(), WeeklyPlanningStorePage.class);
	 * pageMonthlyPlanningGroupPO = new
	 * WeeklyPlanningStorePage().createPage(getDriver(), getContext(), log, status,
	 * data,getReport(), getReportLogger(), MonthlyPlanningGroupPage.class);
	 * TestStatus status = getTestStatus();
	 * System.out.println("Entering into TestCase"); OptionToView =
	 * getContext().getStringProperty("OptionToView");
	 * if(OptionToView.contains("Budget")) Name=
	 * pageWeeklyPlanningPO.MonthName(objWeeklyPlanningStoreObjects,
	 * extentTest,objWeeklyPlanningStoreObjects.CurrentWeekBDGTNT);
	 * if(OptionToView.contains("Forecast")) Name=
	 * pageWeeklyPlanningPO.MonthName(objWeeklyPlanningStoreObjects,
	 * extentTest,objWeeklyPlanningStoreObjects.CurrentWeekFCSTNT); WeekName =
	 * Name.split(" "); Week = WeekName[0]; Year =
	 * getContext().getStringProperty("FinYr");
	 * 
	 * String PlannedItemsNT = data.getText("PlannedItemsNT"); DBTotal =
	 * sql.executeQuery(getContext(),
	 * PlannedItemsNT.replace("store",getContext().getStringProperty("store"))
	 * .replace("Area",getContext().getStringProperty("Area")).replace("Dept",
	 * getContext().getStringProperty("Longlife"))
	 * .replace("Zone",getContext().getStringProperty("Zone")).replace("FinWk",Week)
	 * .replace("FinYr",getContext().getStringProperty("FinYr")).replace("Division", getContext().getStringProperty("Division")));
	 * System.out.println("DB Total: "+DBTotal);
	 * 
	 * UITotal =
	 * pageMonthlyPlanningGroupPO.GetTotal(extentTest,objMonthlyPlanningGroupObjects
	 * .RowNameTotalLL,objWeeklyPlanningStoreObjects.PlannedItemsLL);
	 * pageMonthlyPlanningPO.CompareValues(extentTest,DBTotal,UITotal);
	 * 
	 * 
	 * DBTotalSFD = sql.executeQuery(getContext(),
	 * PlannedItemsNT.replace("store",getContext().getStringProperty("store"))
	 * .replace("Area",getContext().getStringProperty("Area")).replace("Dept",
	 * getContext().getStringProperty("SeaFoodDeli"))
	 * .replace("Zone",getContext().getStringProperty("Zone")).replace("FinWk",Week)
	 * .replace("FinYr",getContext().getStringProperty("FinYr")).replace("Division", getContext().getStringProperty("Division")));
	 * System.out.println("DB Total: "+DBTotalSFD);
	 * 
	 * UITotalSFD =
	 * pageMonthlyPlanningGroupPO.GetTotal(extentTest,objMonthlyPlanningGroupObjects
	 * .RowNameTotalSFD,objWeeklyPlanningStoreObjects.PlannedItemsSFD);
	 * pageMonthlyPlanningPO.CompareValues(extentTest,DBTotalSFD,UITotalSFD); }
	 * 
	 * //Planned IPLH
	 * 
	 * @Test(description =
	 * "Weekly Planning - Store, Planned IPLH Items - Current Week",priority=46)
	 * public void PlannedIPLHitems_CurrentMonth() throws Exception {
	 * pageWeeklyPlanningPO = new WeeklyPlanningStorePage().createPage(getDriver(),
	 * getContext(), log, status, data,getReport(), getReportLogger(),
	 * WeeklyPlanningStorePage.class); pageMonthlyPlanningGroupPO = new
	 * WeeklyPlanningStorePage().createPage(getDriver(), getContext(), log, status,
	 * data,getReport(), getReportLogger(), MonthlyPlanningGroupPage.class);
	 * TestStatus status = getTestStatus();
	 * System.out.println("Entering into TestCase"); OptionToView =
	 * getContext().getStringProperty("OptionToView");
	 * if(OptionToView.contains("Budget")) Name=
	 * pageWeeklyPlanningPO.MonthName(objWeeklyPlanningStoreObjects,
	 * extentTest,objWeeklyPlanningStoreObjects.CurrentWeekBDGTNT);
	 * if(OptionToView.contains("Forecast")) Name=
	 * pageWeeklyPlanningPO.MonthName(objWeeklyPlanningStoreObjects,
	 * extentTest,objWeeklyPlanningStoreObjects.CurrentWeekFCSTNT); WeekName =
	 * Name.split(" "); Week = WeekName[0]; Year =
	 * getContext().getStringProperty("FinYr"); LYYear = Integer.parseInt(Year) -1;
	 * LYYr = String.valueOf(LYYear);
	 * 
	 * String PlannedIPLHitemsNT = data.getText("PlannedIPLHitemsNT"); DBTotal =
	 * sql.executeQuery(getContext(),
	 * PlannedIPLHitemsNT.replace("store",getContext().getStringProperty("store"))
	 * .replace("Area",getContext().getStringProperty("Area")).replace("Dept",
	 * getContext().getStringProperty("Longlife"))
	 * .replace("Zone",getContext().getStringProperty("Zone")).replace("FinWk",Week)
	 * .replace("FinYr",getContext().getStringProperty("FinYr")).replace("LYFinYr",
	 * LYYr).replace("Division", getContext().getStringProperty("Division")));
	 * System.out.println("DB Total: "+DBTotal);
	 * 
	 * UITotal =
	 * pageMonthlyPlanningGroupPO.GetTotal(extentTest,objMonthlyPlanningGroupObjects
	 * .RowNameTotalLL,objWeeklyPlanningStoreObjects.PlannedIPLHItemsLL);
	 * pageMonthlyPlanningPO.CompareValues(extentTest,DBTotal,UITotal);
	 * 
	 * 
	 * DBTotalSFD = sql.executeQuery(getContext(),
	 * PlannedIPLHitemsNT.replace("store",getContext().getStringProperty("store"))
	 * .replace("Area",getContext().getStringProperty("Area")).replace("Dept",
	 * getContext().getStringProperty("SeaFoodDeli"))
	 * .replace("Zone",getContext().getStringProperty("Zone")).replace("FinWk",Week)
	 * .replace("FinYr",getContext().getStringProperty("FinYr")).replace("LYFinYr",
	 * LYYr).replace("Division", getContext().getStringProperty("Division")));
	 * System.out.println("DB Total: "+DBTotalSFD);
	 * 
	 * UITotalSFD =
	 * pageMonthlyPlanningGroupPO.GetTotal(extentTest,objMonthlyPlanningGroupObjects
	 * .RowNameTotalSFD,objWeeklyPlanningStoreObjects.PlannedIPLHItemsSFD);
	 * pageMonthlyPlanningPO.CompareValues(extentTest,DBTotalSFD,UITotalSFD); }
	 * 
	 * @SuppressWarnings("static-access") // Budget Wages
	 * 
	 * @Test(description =
	 * "Weekly Planning - Store, Budget Wages - Current Week",priority=47) public
	 * void BudgetWages_CurrentMonth() throws Exception { pageWeeklyPlanningPO = new
	 * WeeklyPlanningStorePage().createPage(getDriver(), getContext(), log, status,
	 * data,getReport(), getReportLogger(), WeeklyPlanningStorePage.class);
	 * pageMonthlyPlanningGroupPO = new
	 * WeeklyPlanningStorePage().createPage(getDriver(), getContext(), log, status,
	 * data,getReport(), getReportLogger(), MonthlyPlanningGroupPage.class);
	 * TestStatus status = getTestStatus();
	 * System.out.println("Entering into TestCase"); OptionToView =
	 * getContext().getStringProperty("OptionToView");
	 * if(OptionToView.contains("Budget")) Name=
	 * pageWeeklyPlanningPO.MonthName(objWeeklyPlanningStoreObjects,
	 * extentTest,objWeeklyPlanningStoreObjects.CurrentWeekBDGTNT);
	 * if(OptionToView.contains("Forecast")) Name=
	 * pageWeeklyPlanningPO.MonthName(objWeeklyPlanningStoreObjects,
	 * extentTest,objWeeklyPlanningStoreObjects.CurrentWeekFCSTNT); WeekName =
	 * Name.split(" "); Week = WeekName[0]; Year =
	 * getContext().getStringProperty("FinYr");
	 * 
	 * if(OptionToView.contains("Budget")) {
	 * 
	 * String BudgetWagesNT = data.getText("BudgetWagesNT"); DBTotal =
	 * sql.executeQuery(getContext(),
	 * BudgetWagesNT.replace("store",getContext().getStringProperty("store"))
	 * .replace("Area",getContext().getStringProperty("Area")).replace("Dept",
	 * getContext().getStringProperty("Longlife"))
	 * .replace("Zone",getContext().getStringProperty("Zone")).replace("FinWk",Week)
	 * .replace("FinYr",getContext().getStringProperty("FinYr")).replace("Division", getContext().getStringProperty("Division")));
	 * System.out.println("DB Total: "+DBTotal);
	 * 
	 * UITotal =
	 * pageMonthlyPlanningGroupPO.GetTotal(extentTest,objMonthlyPlanningGroupObjects
	 * .RowNameTotalLL,objWeeklyPlanningStoreObjects.BudgetWagesLL);
	 * pageMonthlyPlanningPO.CompareValues(extentTest,DBTotal,UITotal);
	 * 
	 * 
	 * DBTotalSFD = sql.executeQuery(getContext(),
	 * BudgetWagesNT.replace("store",getContext().getStringProperty("store"))
	 * .replace("Area",getContext().getStringProperty("Area")).replace("Dept",
	 * getContext().getStringProperty("SeaFoodDeli"))
	 * .replace("Zone",getContext().getStringProperty("Zone")).replace("FinWk",Week)
	 * .replace("FinYr",getContext().getStringProperty("FinYr")).replace("Division", getContext().getStringProperty("Division")));
	 * System.out.println("DB Total: "+DBTotalSFD);
	 * 
	 * UITotalSFD =
	 * pageMonthlyPlanningGroupPO.GetTotal(extentTest,objMonthlyPlanningGroupObjects
	 * .RowNameTotalSFD,objWeeklyPlanningStoreObjects.BudgetWagesSFD);
	 * pageMonthlyPlanningPO.CompareValues(extentTest,DBTotalSFD,UITotalSFD); }
	 * if(OptionToView.contains("Forecast")) {
	 * 
	 * String ForecastWagesNT = data.getText("ForecastWagesNT"); DBTotal =
	 * sql.executeQuery(getContext(),
	 * ForecastWagesNT.replace("store",getContext().getStringProperty("store"))
	 * .replace("Area",getContext().getStringProperty("Area")).replace("Dept",
	 * getContext().getStringProperty("Longlife"))
	 * .replace("Zone",getContext().getStringProperty("Zone")).replace("FinWk",Week)
	 * .replace("FinYr",getContext().getStringProperty("FinYr")).replace("Division", getContext().getStringProperty("Division")));
	 * System.out.println("DB Total: "+DBTotal);
	 * 
	 * UITotal =
	 * pageMonthlyPlanningGroupPO.GetTotal(extentTest,objMonthlyPlanningGroupObjects
	 * .RowNameTotalLL,objWeeklyPlanningStoreObjects.ForecastWagesLL);
	 * pageMonthlyPlanningPO.CompareValues(extentTest,DBTotal,UITotal);
	 * 
	 * 
	 * DBTotalSFD = sql.executeQuery(getContext(),
	 * ForecastWagesNT.replace("store",getContext().getStringProperty("store"))
	 * .replace("Area",getContext().getStringProperty("Area")).replace("Dept",
	 * getContext().getStringProperty("SeaFoodDeli"))
	 * .replace("Zone",getContext().getStringProperty("Zone")).replace("FinWk",Week)
	 * .replace("FinYr",getContext().getStringProperty("FinYr")).replace("Division", getContext().getStringProperty("Division")));
	 * System.out.println("DB Total: "+DBTotal);
	 * 
	 * UITotalSFD =
	 * pageMonthlyPlanningGroupPO.GetTotal(extentTest,objMonthlyPlanningGroupObjects
	 * .RowNameTotalSFD,objWeeklyPlanningStoreObjects.ForecastWagesSFD);
	 * pageMonthlyPlanningPO.CompareValues(extentTest,DBTotalSFD,UITotalSFD); } }
	 * 
	 * //Planned Wages
	 * 
	 * @Test(description =
	 * "Weekly Planning - Store, Planned Wages - Current Week",priority=48) public
	 * void PlannedWages_CurrentMonth() throws Exception { pageWeeklyPlanningPO =
	 * new WeeklyPlanningStorePage().createPage(getDriver(), getContext(), log,
	 * status, data,getReport(), getReportLogger(), WeeklyPlanningStorePage.class);
	 * pageMonthlyPlanningGroupPO = new
	 * WeeklyPlanningStorePage().createPage(getDriver(), getContext(), log, status,
	 * data,getReport(), getReportLogger(), MonthlyPlanningGroupPage.class);
	 * TestStatus status = getTestStatus();
	 * System.out.println("Entering into TestCase"); OptionToView =
	 * getContext().getStringProperty("OptionToView");
	 * if(OptionToView.contains("Budget")) Name=
	 * pageWeeklyPlanningPO.MonthName(objWeeklyPlanningStoreObjects,
	 * extentTest,objWeeklyPlanningStoreObjects.CurrentWeekBDGTNT);
	 * if(OptionToView.contains("Forecast")) Name=
	 * pageWeeklyPlanningPO.MonthName(objWeeklyPlanningStoreObjects,
	 * extentTest,objWeeklyPlanningStoreObjects.CurrentWeekFCSTNT); WeekName =
	 * Name.split(" "); Week = WeekName[0]; Year =
	 * getContext().getStringProperty("FinYr");
	 * 
	 * String PlannedWagesNT = data.getText("PlannedWagesNT"); DBTotal =
	 * sql.executeQuery(getContext(),
	 * PlannedWagesNT.replace("store",getContext().getStringProperty("store"))
	 * .replace("Area",getContext().getStringProperty("Area")).replace("Dept",
	 * getContext().getStringProperty("Longlife"))
	 * .replace("Zone",getContext().getStringProperty("Zone")).replace("FinWk",Week)
	 * .replace("FinYr",getContext().getStringProperty("FinYr")).replace("Division", getContext().getStringProperty("Division")));
	 * System.out.println("DB Total: "+DBTotal);
	 * 
	 * UITotal =
	 * pageMonthlyPlanningGroupPO.GetTotal(extentTest,objMonthlyPlanningGroupObjects
	 * .RowNameTotalLL,objWeeklyPlanningStoreObjects.PlannedWagesLL);
	 * pageMonthlyPlanningPO.CompareValues(extentTest,DBTotal,UITotal);
	 * 
	 * 
	 * DBTotalSFD = sql.executeQuery(getContext(),
	 * PlannedWagesNT.replace("store",getContext().getStringProperty("store"))
	 * .replace("Area",getContext().getStringProperty("Area")).replace("Dept",
	 * getContext().getStringProperty("SeaFoodDeli"))
	 * .replace("Zone",getContext().getStringProperty("Zone")).replace("FinWk",Week)
	 * .replace("FinYr",getContext().getStringProperty("FinYr")).replace("Division", getContext().getStringProperty("Division")));
	 * System.out.println("DB Total: "+DBTotal);
	 * 
	 * UITotalSFD =
	 * pageMonthlyPlanningGroupPO.GetTotal(extentTest,objMonthlyPlanningGroupObjects
	 * .RowNameTotalSFD,objWeeklyPlanningStoreObjects.PlannedWagesSFD);
	 * pageMonthlyPlanningPO.CompareValues(extentTest,DBTotalSFD,UITotalSFD); }
	 * 
	 * //Planned % on LY
	 * 
	 * @Test(description =
	 * "Weekly Planning - Store, Planned % on LY Wages - Current Week",priority=49)
	 * public void PlannedLYWages_CurrentMonth() throws Exception {
	 * pageWeeklyPlanningPO = new WeeklyPlanningStorePage().createPage(getDriver(),
	 * getContext(), log, status, data,getReport(), getReportLogger(),
	 * WeeklyPlanningStorePage.class); pageMonthlyPlanningGroupPO = new
	 * WeeklyPlanningStorePage().createPage(getDriver(), getContext(), log, status,
	 * data,getReport(), getReportLogger(), MonthlyPlanningGroupPage.class);
	 * TestStatus status = getTestStatus();
	 * System.out.println("Entering into TestCase"); OptionToView =
	 * getContext().getStringProperty("OptionToView");
	 * if(OptionToView.contains("Budget")) Name=
	 * pageWeeklyPlanningPO.MonthName(objWeeklyPlanningStoreObjects,
	 * extentTest,objWeeklyPlanningStoreObjects.CurrentWeekBDGTNT);
	 * if(OptionToView.contains("Forecast")) Name=
	 * pageWeeklyPlanningPO.MonthName(objWeeklyPlanningStoreObjects,
	 * extentTest,objWeeklyPlanningStoreObjects.CurrentWeekFCSTNT); WeekName =
	 * Name.split(" "); Week = WeekName[0]; Year =
	 * getContext().getStringProperty("FinYr"); LYYear = Integer.parseInt(Year) -1;
	 * LYYr = String.valueOf(LYYear);
	 * 
	 * String PlannedLYWagesNT = data.getText("PlannedLYWagesNT"); DBTotal =
	 * sql.executeQuery(getContext(),
	 * PlannedLYWagesNT.replace("store",getContext().getStringProperty("store"))
	 * .replace("Area",getContext().getStringProperty("Area")).replace("Dept",
	 * getContext().getStringProperty("Longlife"))
	 * .replace("Zone",getContext().getStringProperty("Zone")).replace("FinWk",Week)
	 * .replace("FinYr",getContext().getStringProperty("FinYr")).replace("LYYr",LYYr
	 * )); System.out.println("DB Total: "+DBTotal);
	 * 
	 * UITotal =
	 * pageMonthlyPlanningGroupPO.GetTotal(extentTest,objMonthlyPlanningGroupObjects
	 * .RowNameTotalLL,objWeeklyPlanningStoreObjects.PlannedPercentonLYWagesLL);
	 * pageMonthlyPlanningPO.CompareValueDecimalFields(extentTest,DBTotal,UITotal);
	 * 
	 * 
	 * DBTotalSFD = sql.executeQuery(getContext(),
	 * PlannedLYWagesNT.replace("store",getContext().getStringProperty("store"))
	 * .replace("Area",getContext().getStringProperty("Area")).replace("Dept",
	 * getContext().getStringProperty("SeaFoodDeli"))
	 * .replace("Zone",getContext().getStringProperty("Zone")).replace("FinWk",Week)
	 * .replace("FinYr",getContext().getStringProperty("FinYr")).replace("LYYr",LYYr
	 * )); System.out.println("DB Total: "+DBTotalSFD);
	 * 
	 * UITotalSFD =
	 * pageMonthlyPlanningGroupPO.GetTotal(extentTest,objMonthlyPlanningGroupObjects
	 * .RowNameTotalSFD,objWeeklyPlanningStoreObjects.PlannedPercentonLYWagesSFD);
	 * pageMonthlyPlanningPO.CompareValueDecimalFields(extentTest,DBTotalSFD,
	 * UITotalSFD); }
	 * 
	 * //Budget Wage%
	 * 
	 * @Test(description =
	 * "Weekly Planning - Store, Budget WagesPercentage - Current Week",priority=50)
	 * public void BudgetWagesPercentage_CurrentMonth() throws Exception {
	 * pageWeeklyPlanningPO = new WeeklyPlanningStorePage().createPage(getDriver(),
	 * getContext(), log, status, data,getReport(), getReportLogger(),
	 * WeeklyPlanningStorePage.class); pageMonthlyPlanningGroupPO = new
	 * WeeklyPlanningStorePage().createPage(getDriver(), getContext(), log, status,
	 * data,getReport(), getReportLogger(), MonthlyPlanningGroupPage.class);
	 * TestStatus status = getTestStatus();
	 * System.out.println("Entering into TestCase"); OptionToView =
	 * getContext().getStringProperty("OptionToView");
	 * if(OptionToView.contains("Budget")) Name=
	 * pageWeeklyPlanningPO.MonthName(objWeeklyPlanningStoreObjects,
	 * extentTest,objWeeklyPlanningStoreObjects.CurrentWeekBDGTNT);
	 * if(OptionToView.contains("Forecast")) Name=
	 * pageWeeklyPlanningPO.MonthName(objWeeklyPlanningStoreObjects,
	 * extentTest,objWeeklyPlanningStoreObjects.CurrentWeekFCSTNT); WeekName =
	 * Name.split(" "); Week = WeekName[0]; Year =
	 * getContext().getStringProperty("FinYr");
	 * 
	 * if(OptionToView.contains("Budget")) {
	 * 
	 * String BudgetWagesPercentageNT = data.getText("BudgetWagesPercentageNT");
	 * DBTotal = sql.executeQuery(getContext(),
	 * BudgetWagesPercentageNT.replace("store",getContext().getStringProperty(
	 * "store"))
	 * .replace("Area",getContext().getStringProperty("Area")).replace("Dept",
	 * getContext().getStringProperty("Longlife"))
	 * .replace("Zone",getContext().getStringProperty("Zone")).replace("FinWk",Week)
	 * .replace("FinYr",getContext().getStringProperty("FinYr")).replace("Division", getContext().getStringProperty("Division")));
	 * System.out.println("DB Total: "+DBTotal);
	 * 
	 * UITotal =
	 * pageMonthlyPlanningGroupPO.GetTotal(extentTest,objMonthlyPlanningGroupObjects
	 * .RowNameTotalLL,objWeeklyPlanningStoreObjects.BudgetWagesPercentLL);
	 * pageMonthlyPlanningPO.CompareValueDecimalFields(extentTest,DBTotal,UITotal);
	 * 
	 * 
	 * DBTotalSFD = sql.executeQuery(getContext(),
	 * BudgetWagesPercentageNT.replace("store",getContext().getStringProperty(
	 * "store"))
	 * .replace("Area",getContext().getStringProperty("Area")).replace("Dept",
	 * getContext().getStringProperty("SeaFoodDeli"))
	 * .replace("Zone",getContext().getStringProperty("Zone")).replace("FinWk",Week)
	 * .replace("FinYr",getContext().getStringProperty("FinYr")).replace("Division", getContext().getStringProperty("Division")));
	 * System.out.println("DB Total: "+DBTotalSFD);
	 * 
	 * UITotalSFD =
	 * pageMonthlyPlanningGroupPO.GetTotal(extentTest,objMonthlyPlanningGroupObjects
	 * .RowNameTotalSFD,objWeeklyPlanningStoreObjects.BudgetWagesPercentSFD);
	 * pageMonthlyPlanningPO.CompareValueDecimalFields(extentTest,DBTotalSFD,
	 * UITotalSFD); } if(OptionToView.contains("Forecast")) {
	 * 
	 * String ForecastWagesPercentageNT = data.getText("ForecastWagesPercentageNT");
	 * DBTotal = sql.executeQuery(getContext(),
	 * ForecastWagesPercentageNT.replace("store",getContext().getStringProperty(
	 * "store"))
	 * .replace("Area",getContext().getStringProperty("Area")).replace("Dept",
	 * getContext().getStringProperty("Longlife"))
	 * .replace("Zone",getContext().getStringProperty("Zone")).replace("FinWk",Week)
	 * .replace("FinYr",getContext().getStringProperty("FinYr")).replace("Division", getContext().getStringProperty("Division")));
	 * System.out.println("DB Total: "+DBTotal);
	 * 
	 * UITotal =
	 * pageMonthlyPlanningGroupPO.GetTotal(extentTest,objMonthlyPlanningGroupObjects
	 * .RowNameTotalLL,objWeeklyPlanningStoreObjects.ForecastWagesPercentLL);
	 * pageMonthlyPlanningPO.CompareValueDecimalFields(extentTest,DBTotal,UITotal);
	 * 
	 * 
	 * DBTotalSFD = sql.executeQuery(getContext(),
	 * ForecastWagesPercentageNT.replace("store",getContext().getStringProperty(
	 * "store"))
	 * .replace("Area",getContext().getStringProperty("Area")).replace("Dept",
	 * getContext().getStringProperty("SeaFoodDeli"))
	 * .replace("Zone",getContext().getStringProperty("Zone")).replace("FinWk",Week)
	 * .replace("FinYr",getContext().getStringProperty("FinYr")).replace("Division", getContext().getStringProperty("Division")));
	 * System.out.println("DB Total: "+DBTotalSFD);
	 * 
	 * UITotalSFD =
	 * pageMonthlyPlanningGroupPO.GetTotal(extentTest,objMonthlyPlanningGroupObjects
	 * .RowNameTotalSFD,objWeeklyPlanningStoreObjects.ForecastWagesPercentSFD);
	 * pageMonthlyPlanningPO.CompareValueDecimalFields(extentTest,DBTotalSFD,
	 * UITotalSFD); } }
	 * 
	 * //Planned Wage%
	 * 
	 * @Test(description =
	 * "Weekly Planning - Store, Planned WagesPercentage - Current Week",priority=
	 * 51) public void PlannedWagesPercentage_CurrentMonth() throws Exception {
	 * pageWeeklyPlanningPO = new WeeklyPlanningStorePage().createPage(getDriver(),
	 * getContext(), log, status, data,getReport(), getReportLogger(),
	 * WeeklyPlanningStorePage.class); pageMonthlyPlanningGroupPO = new
	 * WeeklyPlanningStorePage().createPage(getDriver(), getContext(), log, status,
	 * data,getReport(), getReportLogger(), MonthlyPlanningGroupPage.class);
	 * TestStatus status = getTestStatus();
	 * System.out.println("Entering into TestCase"); OptionToView =
	 * getContext().getStringProperty("OptionToView");
	 * if(OptionToView.contains("Budget")) Name=
	 * pageWeeklyPlanningPO.MonthName(objWeeklyPlanningStoreObjects,
	 * extentTest,objWeeklyPlanningStoreObjects.CurrentWeekBDGTNT);
	 * if(OptionToView.contains("Forecast")) Name=
	 * pageWeeklyPlanningPO.MonthName(objWeeklyPlanningStoreObjects,
	 * extentTest,objWeeklyPlanningStoreObjects.CurrentWeekFCSTNT); WeekName =
	 * Name.split(" "); Week = WeekName[0]; Year =
	 * getContext().getStringProperty("FinYr");
	 * 
	 * String PlannedWagesPercentageNT = data.getText("PlannedWagesPercentageNT");
	 * DBTotal = sql.executeQuery(getContext(),
	 * PlannedWagesPercentageNT.replace("store",getContext().getStringProperty(
	 * "store"))
	 * .replace("Area",getContext().getStringProperty("Area")).replace("Dept",
	 * getContext().getStringProperty("Longlife"))
	 * .replace("Zone",getContext().getStringProperty("Zone")).replace("FinWk",Week)
	 * .replace("FinYr",getContext().getStringProperty("FinYr")).replace("Division", getContext().getStringProperty("Division")));
	 * System.out.println("DB Total: "+DBTotal);
	 * 
	 * UITotal =
	 * pageMonthlyPlanningGroupPO.GetTotal(extentTest,objMonthlyPlanningGroupObjects
	 * .RowNameTotalLL,objWeeklyPlanningStoreObjects.PlannedWagesPercentLL);
	 * pageMonthlyPlanningPO.CompareValueDecimalFields(extentTest,DBTotal,UITotal);
	 * 
	 * 
	 * DBTotalSFD = sql.executeQuery(getContext(),
	 * PlannedWagesPercentageNT.replace("store",getContext().getStringProperty(
	 * "store"))
	 * .replace("Area",getContext().getStringProperty("Area")).replace("Dept",
	 * getContext().getStringProperty("SeaFoodDeli"))
	 * .replace("Zone",getContext().getStringProperty("Zone")).replace("FinWk",Week)
	 * .replace("FinYr",getContext().getStringProperty("FinYr")).replace("Division", getContext().getStringProperty("Division")));
	 * System.out.println("DB Total: "+DBTotalSFD);
	 * 
	 * UITotalSFD =
	 * pageMonthlyPlanningGroupPO.GetTotal(extentTest,objMonthlyPlanningGroupObjects
	 * .RowNameTotalSFD,objWeeklyPlanningStoreObjects.PlannedWagesPercentSFD);
	 * pageMonthlyPlanningPO.CompareValueDecimalFields(extentTest,DBTotalSFD,
	 * UITotalSFD); }
	 * 
	 * //LY Wage%
	 * 
	 * @Test(description =
	 * "Weekly Planning - Store, PlannedLY WagesPercentage - Current Week",priority=
	 * 52) public void LYWagesPercentage_CurrentMonth() throws Exception {
	 * pageWeeklyPlanningPO = new WeeklyPlanningStorePage().createPage(getDriver(),
	 * getContext(), log, status, data,getReport(), getReportLogger(),
	 * WeeklyPlanningStorePage.class); pageMonthlyPlanningGroupPO = new
	 * WeeklyPlanningStorePage().createPage(getDriver(), getContext(), log, status,
	 * data,getReport(), getReportLogger(), MonthlyPlanningGroupPage.class);
	 * TestStatus status = getTestStatus();
	 * System.out.println("Entering into TestCase"); OptionToView =
	 * getContext().getStringProperty("OptionToView");
	 * if(OptionToView.contains("Budget")) Name=
	 * pageWeeklyPlanningPO.MonthName(objWeeklyPlanningStoreObjects,
	 * extentTest,objWeeklyPlanningStoreObjects.CurrentWeekBDGTNT);
	 * if(OptionToView.contains("Forecast")) Name=
	 * pageWeeklyPlanningPO.MonthName(objWeeklyPlanningStoreObjects,
	 * extentTest,objWeeklyPlanningStoreObjects.CurrentWeekFCSTNT); WeekName =
	 * Name.split(" "); Week = WeekName[0]; Year =
	 * getContext().getStringProperty("FinYr"); LYYear = Integer.parseInt(Year) -1;
	 * LYYr = String.valueOf(LYYear);
	 * 
	 * String LYWagesPercentageNT = data.getText("LYWagesPercentageNT"); DBTotal =
	 * sql.executeQuery(getContext(),
	 * LYWagesPercentageNT.replace("store",getContext().getStringProperty("store"))
	 * .replace("Area",getContext().getStringProperty("Area")).replace("Dept",
	 * getContext().getStringProperty("Longlife"))
	 * .replace("Zone",getContext().getStringProperty("Zone")).replace("FinWk",Week)
	 * .replace("FinYr",getContext().getStringProperty("FinYr")).replace("LYYr",LYYr
	 * )); System.out.println("DB Total: "+DBTotal);
	 * 
	 * UITotal =
	 * pageMonthlyPlanningGroupPO.GetTotal(extentTest,objMonthlyPlanningGroupObjects
	 * .RowNameTotalLL,objWeeklyPlanningStoreObjects.LYWagePercentageLL);
	 * pageMonthlyPlanningPO.CompareValueDecimalFields(extentTest,DBTotal,UITotal);
	 * 
	 * 
	 * DBTotalSFD = sql.executeQuery(getContext(),
	 * LYWagesPercentageNT.replace("store",getContext().getStringProperty("store"))
	 * .replace("Area",getContext().getStringProperty("Area")).replace("Dept",
	 * getContext().getStringProperty("SeaFoodDeli"))
	 * .replace("Zone",getContext().getStringProperty("Zone")).replace("FinWk",Week)
	 * .replace("FinYr",getContext().getStringProperty("FinYr")).replace("LYYr",LYYr
	 * )); System.out.println("DB Total: "+DBTotalSFD);
	 * 
	 * UITotalSFD =
	 * pageMonthlyPlanningGroupPO.GetTotal(extentTest,objMonthlyPlanningGroupObjects
	 * .RowNameTotalSFD,objWeeklyPlanningStoreObjects.LYWagePercentageSFD);
	 * pageMonthlyPlanningPO.CompareValueDecimalFields(extentTest,DBTotalSFD,
	 * UITotalSFD); }
	 */
	// SMS Hours
	@Test(description = "Weekly Planning - Store, SMS Hours - Current Week", priority = 53)
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
					objWeeklyPlanningStoreObjects.CurrentWeekBDGTNT);
		if (OptionToView.contains("Forecast"))
			Name = pageWeeklyPlanningPO.MonthName(objWeeklyPlanningStoreObjects, extentTest,
					objWeeklyPlanningStoreObjects.CurrentWeekFCSTNT);
		WeekName = Name.split(" ");
		Week = WeekName[0];
		Year = getContext().getStringProperty("FinYr");

		String SMSHoursNT = data.getText("SMSHoursNT");
		DBTotal = sql.executeQuery(getContext(),
				SMSHoursNT.replace("store", getContext().getStringProperty("store"))
						.replace("Area", getContext().getStringProperty("Area"))
						.replace("Dept", getContext().getStringProperty("Longlife"))
						.replace("Zone", getContext().getStringProperty("Zone")).replace("FinWk", Week)
						.replace("FinYr", getContext().getStringProperty("FinYr")).replace("Division", getContext().getStringProperty("Division")));
		System.out.println("DB Total: " + DBTotal);

		UITotal = pageMonthlyPlanningGroupPO.GetTotal(extentTest, objMonthlyPlanningGroupObjects.RowNameTotalLL,
				objWeeklyPlanningStoreObjects.SMSHoursLL);
		pageMonthlyPlanningPO.CompareValues(extentTest, DBTotal, UITotal);

		DBTotalSFD = sql.executeQuery(getContext(),
				SMSHoursNT.replace("store", getContext().getStringProperty("store"))
						.replace("Area", getContext().getStringProperty("Area"))
						.replace("Dept", getContext().getStringProperty("SeaFoodDeli"))
						.replace("Zone", getContext().getStringProperty("Zone")).replace("FinWk", Week)
						.replace("FinYr", getContext().getStringProperty("FinYr")).replace("Division", getContext().getStringProperty("Division")));
		System.out.println("DB Total: " + DBTotalSFD);

		UITotalSFD = pageMonthlyPlanningGroupPO.GetTotal(extentTest, objMonthlyPlanningGroupObjects.RowNameTotalSFD,
				objWeeklyPlanningStoreObjects.SMSHoursSFD);
		pageMonthlyPlanningPO.CompareValues(extentTest, DBTotalSFD, UITotalSFD);
	}

	// Planned Hours
	@Test(description = "Weekly Planning - Store, PlannedHours - Current Week", priority = 54)
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
					objWeeklyPlanningStoreObjects.CurrentWeekBDGTNT);
		if (OptionToView.contains("Forecast"))
			Name = pageWeeklyPlanningPO.MonthName(objWeeklyPlanningStoreObjects, extentTest,
					objWeeklyPlanningStoreObjects.CurrentWeekFCSTNT);
		WeekName = Name.split(" ");
		Week = WeekName[0];
		Year = getContext().getStringProperty("FinYr");

		String PlannedHoursNT = data.getText("PlannedHoursNT");
		DBTotal = sql.executeQuery(getContext(),
				PlannedHoursNT.replace("store", getContext().getStringProperty("store"))
						.replace("Area", getContext().getStringProperty("Area"))
						.replace("Dept", getContext().getStringProperty("Longlife"))
						.replace("Zone", getContext().getStringProperty("Zone")).replace("FinWk", Week)
						.replace("FinYr", getContext().getStringProperty("FinYr")).replace("Division", getContext().getStringProperty("Division")));
		System.out.println("DB Total: " + DBTotal);

		UITotal = pageMonthlyPlanningGroupPO.GetTotal(extentTest, objMonthlyPlanningGroupObjects.RowNameTotalLL,
				objWeeklyPlanningStoreObjects.PlannedHoursLL);
		pageMonthlyPlanningPO.CompareValues(extentTest, DBTotal, UITotal);

		DBTotalSFD = sql.executeQuery(getContext(),
				PlannedHoursNT.replace("store", getContext().getStringProperty("store"))
						.replace("Area", getContext().getStringProperty("Area"))
						.replace("Dept", getContext().getStringProperty("SeaFoodDeli"))
						.replace("Zone", getContext().getStringProperty("Zone")).replace("FinWk", Week)
						.replace("FinYr", getContext().getStringProperty("FinYr")).replace("Division", getContext().getStringProperty("Division")));
		System.out.println("DB Total: " + DBTotalSFD);

		UITotalSFD = pageMonthlyPlanningGroupPO.GetTotal(extentTest, objMonthlyPlanningGroupObjects.RowNameTotalSFD,
				objWeeklyPlanningStoreObjects.PlannedHoursSFD);
		pageMonthlyPlanningPO.CompareValues(extentTest, DBTotalSFD, UITotalSFD);
	}

	// Hours LY

	@Test(description = "Weekly Planning - Store, HoursLY - Current Week", priority = 55)
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
					objWeeklyPlanningStoreObjects.CurrentWeekBDGTNT);
		if (OptionToView.contains("Forecast"))
			Name = pageWeeklyPlanningPO.MonthName(objWeeklyPlanningStoreObjects, extentTest,
					objWeeklyPlanningStoreObjects.CurrentWeekFCSTNT);
		WeekName = Name.split(" ");
		WeekName = Name.split(" ");
		Week = WeekName[0];
		Year = getContext().getStringProperty("FinYr");
		LYYear = Integer.parseInt(Year) - 1;
		LYYr = String.valueOf(LYYear);

		String HoursLYNT = data.getText("HoursLYNT");

		DBTotal = sql.executeQuery(getContext(),
				HoursLYNT.replace("store", getContext().getStringProperty("store"))
						.replace("Area", getContext().getStringProperty("Area"))
						.replace("Dept", getContext().getStringProperty("Longlife"))
						.replace("Zone", getContext().getStringProperty("Zone")).replace("FinWk", Week)
						.replace("FinYr", getContext().getStringProperty("FinYr")).replace("LYYr", LYYr).replace("Division", getContext().getStringProperty("Division")));
		System.out.println("DB Total: " + DBTotal);

		UITotal = pageMonthlyPlanningGroupPO.GetTotal(extentTest, objMonthlyPlanningGroupObjects.RowNameTotalLL,
				objWeeklyPlanningStoreObjects.LYHoursLL);
		pageMonthlyPlanningPO.CompareValues(extentTest, DBTotal, UITotal);

		DBTotalSFD = sql.executeQuery(getContext(),
				HoursLYNT.replace("store", getContext().getStringProperty("store"))
						.replace("Area", getContext().getStringProperty("Area"))
						.replace("Dept", getContext().getStringProperty("SeaFoodDeli"))
						.replace("Zone", getContext().getStringProperty("Zone")).replace("FinWk", Week)
						.replace("FinYr", getContext().getStringProperty("FinYr")).replace("LYYr", LYYr).replace("Division", getContext().getStringProperty("Division")));
		System.out.println("DB Total: " + DBTotalSFD);

		UITotalSFD = pageMonthlyPlanningGroupPO.GetTotal(extentTest, objMonthlyPlanningGroupObjects.RowNameTotalSFD,
				objWeeklyPlanningStoreObjects.LYHoursSFD);
		pageMonthlyPlanningPO.CompareValues(extentTest, DBTotalSFD, UITotalSFD);
	}

	// Planned OR
	@Test(description = "Weekly Planning - Store, Planned OR - Current Week", priority = 56)
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
					objWeeklyPlanningStoreObjects.CurrentWeekBDGTNT);
		if (OptionToView.contains("Forecast"))
			Name = pageWeeklyPlanningPO.MonthName(objWeeklyPlanningStoreObjects, extentTest,
					objWeeklyPlanningStoreObjects.CurrentWeekFCSTNT);
		WeekName = Name.split(" ");
		Week = WeekName[0];
		Year = getContext().getStringProperty("FinYr");
		LYYear = Integer.parseInt(Year) - 1;
		LYYr = String.valueOf(LYYear);

		String plannedORNT = data.getText("plannedORNT");
		DBTotal = sql.executeQuery(getContext(),
				plannedORNT.replace("store", getContext().getStringProperty("store"))
						.replace("Area", getContext().getStringProperty("Area"))
						.replace("Dept", getContext().getStringProperty("Longlife"))
						.replace("Zone", getContext().getStringProperty("Zone")).replace("FinWk", Week)
						.replace("FinYr", getContext().getStringProperty("FinYr")).replace("LYYr", LYYr)
						.replace("Division", getContext().getStringProperty("Division")));
		System.out.println("DB Total: " + DBTotal);

		UITotal = pageMonthlyPlanningGroupPO.GetTotal(extentTest, objMonthlyPlanningGroupObjects.RowNameTotalLL,
				objWeeklyPlanningStoreObjects.PlannedORLL);
		pageMonthlyPlanningPO.CompareValueDecimalFields(extentTest, DBTotal, UITotal);

		DBTotalSFD = sql.executeQuery(getContext(),
				plannedORNT.replace("store", getContext().getStringProperty("store"))
						.replace("Area", getContext().getStringProperty("Area"))
						.replace("Dept", getContext().getStringProperty("SeaFoodDeli"))
						.replace("Zone", getContext().getStringProperty("Zone")).replace("FinWk", Week)
						.replace("FinYr", getContext().getStringProperty("FinYr")).replace("LYYr", LYYr)
						.replace("Division", getContext().getStringProperty("Division")));
		System.out.println("DB Total: " + DBTotalSFD);

		UITotalSFD = pageMonthlyPlanningGroupPO.GetTotal(extentTest, objMonthlyPlanningGroupObjects.RowNameTotalSFD,
				objWeeklyPlanningStoreObjects.PlannedORSFD);
		pageMonthlyPlanningPO.CompareValueDecimalFields(extentTest, DBTotalSFD, UITotalSFD);
	}

	// Budget/Target/Forecast - OR
	@Test(description = "Weekly Planning - Store, Budget_Forecast_Target OR - Current Week", priority = 57)
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

			String BudgetORLL = data.getText("BudgetOR");

			DBValues = sql.executeQuery(getContext(),
					BudgetORLL.replace("store", getContext().getStringProperty("store"))
							.replace("Area", getContext().getStringProperty("Area"))
							.replace("Dept", getContext().getStringProperty("Longlife"))
							.replace("Zone", getContext().getStringProperty("Zone")).replace("FinWeek", Week)
							.replace("FinYear", getContext().getStringProperty("FinYr")).replace("LYFinYr", LYYr)
							.replace("Division", getContext().getStringProperty("Division")));
			System.out.println("DB Value: " + DBValues);

			String BudgetORSF = data.getText("BudgetOR");
			DBTotal = sql.executeQuery(getContext(),
					BudgetORSF.replace("store", getContext().getStringProperty("store"))
							.replace("Area", getContext().getStringProperty("Area"))
							.replace("Dept", getContext().getStringProperty("SeaFoodDeli"))
							.replace("Zone", getContext().getStringProperty("Zone")).replace("FinWeek", Week)
							.replace("FinYear", getContext().getStringProperty("FinYr")).replace("LYFinYr", LYYr)
							.replace("Division", getContext().getStringProperty("Division")));
			System.out.println("DB Total: " + DBTotal);

			UIValues = pageMonthlyPlanningGroupPO.GetTotal(extentTest, objMonthlyPlanningGroupObjects.RowNameTotalLL,
					objWeeklyPlanningStoreObjects.BUDGETORLL);
			pageMonthlyPlanningPO.CompareValueDecimalFields(extentTest, DBValues, UIValues);

			UITotalSFD = pageMonthlyPlanningGroupPO.GetTotal(extentTest, objMonthlyPlanningGroupObjects.RowNameTotalSFD,
					objWeeklyPlanningStoreObjects.BudgetORSFD);
			pageMonthlyPlanningPO.CompareValueDecimalFields(extentTest, DBTotal, UITotalSFD);
		}
		if (OptionToView.contains("Forecast")) {
			Report_AddStep("testcase", " OR COLUMN - FORECAST OR VALIDATION", "", "", "");

			String ForecastORLL = data.getText("ForecastOR");
			DBValues = sql.executeQuery(getContext(),
					ForecastORLL.replace("store", getContext().getStringProperty("store"))
							.replace("Area", getContext().getStringProperty("Area"))
							.replace("Dept", getContext().getStringProperty("Longlife"))
							.replace("Zone", getContext().getStringProperty("Zone")).replace("FinWeek", Week)
							.replace("FinYear", getContext().getStringProperty("FinYr")).replace("LYFinYr", LYYr)
							.replace("Division", getContext().getStringProperty("Division")));
			System.out.println("DB Value: " + DBValues);

			String ForecastORSF = data.getText("ForecastOR");
			DBTotal = sql.executeQuery(getContext(),
					ForecastORSF.replace("store", getContext().getStringProperty("store"))
							.replace("Area", getContext().getStringProperty("Area"))
							.replace("Dept", getContext().getStringProperty("SeaFoodDeli"))
							.replace("Zone", getContext().getStringProperty("Zone")).replace("FinWeek", Week)
							.replace("FinYear", getContext().getStringProperty("FinYr")).replace("LYFinYr", LYYr)
							.replace("Division", getContext().getStringProperty("Division")));
			System.out.println("DB Total: " + DBTotal);

			UIValues = pageMonthlyPlanningGroupPO.GetTotal(extentTest, objMonthlyPlanningGroupObjects.RowNameTotalLL,
					objWeeklyPlanningStoreObjects.FCSTORLL);
			pageMonthlyPlanningPO.CompareValueDecimalFields(extentTest, DBValues, UIValues);

			UITotalSFD = pageMonthlyPlanningGroupPO.GetTotal(extentTest, objMonthlyPlanningGroupObjects.RowNameTotalSFD,
					objWeeklyPlanningStoreObjects.FCSTORSFD);
			pageMonthlyPlanningPO.CompareValueDecimalFields(extentTest, DBTotal, UITotalSFD);
		}
	}

	// Planned CPH
	@Test(description = "Weekly Planning - Store, Planned CPH - Current Week", priority = 58)
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
					objWeeklyPlanningStoreObjects.CurrentWeekBDGTNT);
		if (OptionToView.contains("Forecast"))
			Name = pageWeeklyPlanningPO.MonthName(objWeeklyPlanningStoreObjects, extentTest,
					objWeeklyPlanningStoreObjects.CurrentWeekFCSTNT);
		WeekName = Name.split(" ");
		Week = WeekName[0];
		Year = getContext().getStringProperty("FinYr");
		LYYear = Integer.parseInt(Year) - 1;
		LYYr = String.valueOf(LYYear);

		String PlannedCPHNT = data.getText("PlannedCPHNT");
		DBTotal = sql.executeQuery(getContext(),
				PlannedCPHNT.replace("store", getContext().getStringProperty("store"))
						.replace("Area", getContext().getStringProperty("Area"))
						.replace("Dept", getContext().getStringProperty("Longlife"))
						.replace("Zone", getContext().getStringProperty("Zone")).replace("FinWk", Week)
						.replace("FinYr", getContext().getStringProperty("FinYr")).replace("LYFinYr", LYYr)
						.replace("Division", getContext().getStringProperty("Division")));
		System.out.println("DB Total: " + DBTotal);

		UITotal = pageMonthlyPlanningGroupPO.GetTotal(extentTest, objMonthlyPlanningGroupObjects.RowNameTotalLL,
				objWeeklyPlanningStoreObjects.PlannedCPHLL);
		pageMonthlyPlanningPO.CompareValueDecimalFields(extentTest, DBTotal, UITotal);

		DBTotalSFD = sql.executeQuery(getContext(),
				PlannedCPHNT.replace("store", getContext().getStringProperty("store"))
						.replace("Area", getContext().getStringProperty("Area"))
						.replace("Dept", getContext().getStringProperty("SeaFoodDeli"))
						.replace("Zone", getContext().getStringProperty("Zone")).replace("FinWk", Week)
						.replace("FinYr", getContext().getStringProperty("FinYr")).replace("LYFinYr", LYYr)
						.replace("Division", getContext().getStringProperty("Division")));
		System.out.println("DB Total: " + DBTotalSFD);

		UITotalSFD = pageMonthlyPlanningGroupPO.GetTotal(extentTest, objMonthlyPlanningGroupObjects.RowNameTotalSFD,
				objWeeklyPlanningStoreObjects.PlannedCPHSFD);
		pageMonthlyPlanningPO.CompareValueDecimalFields(extentTest, DBTotalSFD, UITotalSFD);
	}

	// Growth LY CPH
	@Test(description = "Weekly Planning - Store, Growth LY CPH - Current Week", priority = 59)
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
					objWeeklyPlanningStoreObjects.CurrentWeekBDGTNT);
		if (OptionToView.contains("Forecast"))
			Name = pageWeeklyPlanningPO.MonthName(objWeeklyPlanningStoreObjects, extentTest,
					objWeeklyPlanningStoreObjects.CurrentWeekFCSTNT);
		WeekName = Name.split(" ");
		Week = WeekName[0];
		Year = getContext().getStringProperty("FinYr");
		LYYear = Integer.parseInt(Year) - 1;
		LYYr = String.valueOf(LYYear);

		String GrowthLYCPHTotal = data.getText("GrowthLYCPHNT");
		DBTotal = sql.executeQuery(getContext(),
				GrowthLYCPHTotal.replace("store", getContext().getStringProperty("store"))
						.replace("Area", getContext().getStringProperty("Area"))
						.replace("Dept", getContext().getStringProperty("Longlife"))
						.replace("Zone", getContext().getStringProperty("Zone")).replace("FinWk", Week)
						.replace("FinYr", getContext().getStringProperty("FinYr")).replace("LYYr", LYYr)
						.replace("Division", getContext().getStringProperty("Division")));
		System.out.println("DB Total: " + DBTotal);

		UITotal = pageMonthlyPlanningGroupPO.GetTotal(extentTest, objMonthlyPlanningGroupObjects.RowNameTotalLL,
				objWeeklyPlanningStoreObjects.GrowthLYCPHLL);
		pageMonthlyPlanningPO.CompareValueDecimalFields(extentTest, DBTotal, UITotal);

		DBTotalSFD = sql.executeQuery(getContext(),
				GrowthLYCPHTotal.replace("store", getContext().getStringProperty("store"))
						.replace("Area", getContext().getStringProperty("Area"))
						.replace("Dept", getContext().getStringProperty("SeaFoodDeli"))
						.replace("Zone", getContext().getStringProperty("Zone")).replace("FinWk", Week)
						.replace("FinYr", getContext().getStringProperty("FinYr")).replace("LYYr", LYYr)
						.replace("Division", getContext().getStringProperty("Division")));
		System.out.println("DB Total: " + DBTotalSFD);

		UITotalSFD = pageMonthlyPlanningGroupPO.GetTotal(extentTest, objMonthlyPlanningGroupObjects.RowNameTotalSFD,
				objWeeklyPlanningStoreObjects.GrowthLYCPHSFD);
		pageMonthlyPlanningPO.CompareValueDecimalFields(extentTest, DBTotalSFD, UITotalSFD);
	}

	// Planned BDGT Rate
	//Jenkins Run
	/*@Test(description = "Weekly Planning - Store, LLandSFDPlannedBudgetAndForecastRate - Current Week", priority = 61)
	public void LLandSFDPlannedBudgetAndForecastRate() throws Exception {
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
			Report_AddStep("testcase", " LL and SFD COLUMN - BUDGET  VALIDATION", "", "", "");

			String BudgetLL = data.getText("LLPlannedBudgetRate");

			DBValues = sql.executeQuery(getContext(),
					BudgetLL.replace("store", getContext().getStringProperty("store"))
							.replace("Area", getContext().getStringProperty("Area"))
							.replace("Zone", getContext().getStringProperty("Zone"))
							.replace("FinWeek", getContext().getStringProperty("FinWk"))
							.replace("Year", getContext().getStringProperty("Year"))
							.replace("Division", getContext().getStringProperty("Division"))
							.replace("Region", getContext().getStringProperty("Region"))
							.replace("Week", getContext().getStringProperty("SelectFiscalWeek"))
							.replace("LYFinYr", LYYr).replace("Division", getContext().getStringProperty("Division")));
			System.out.println("DB Value: " + DBValues);

			String BudgetSF = data.getText("SFDPlannedBudgetRate");
			DBTotal = sql.executeQuery(getContext(),
					BudgetSF.replace("store", getContext().getStringProperty("store"))
							.replace("Area", getContext().getStringProperty("Area"))
							.replace("Zone", getContext().getStringProperty("Zone"))
							.replace("FinWeek", getContext().getStringProperty("FinWk"))
							.replace("Year", getContext().getStringProperty("Year"))
							.replace("Division", getContext().getStringProperty("Division"))
							.replace("Region", getContext().getStringProperty("Region"))
							.replace("Week", getContext().getStringProperty("SelectFiscalWeek"))
							.replace("LYFinYr", LYYr).replace("Division", getContext().getStringProperty("Division")));
			System.out.println("DB Total: " + DBTotal);

			UIValues = pageMonthlyPlanningGroupPO.GetTotal(extentTest, objMonthlyPlanningGroupObjects.RowNameTotalLL,
					objWeeklyPlanningStoreObjects.BDGTRateLL);
			pageMonthlyPlanningPO.CompareValueDecimalFields(extentTest, DBValues, UIValues);

			UITotal = pageMonthlyPlanningGroupPO.GetTotal(extentTest, objMonthlyPlanningGroupObjects.RowNameTotalSFD,
					objWeeklyPlanningStoreObjects.BDGTRateSFD);
			pageMonthlyPlanningPO.CompareValueDecimalFields(extentTest, DBTotal, UITotal);
		}
		if (OptionToView.contains("Forecast")) {
			Report_AddStep("testcase", " LL and SFD COLUMN  - FORECAST  VALIDATION", "", "", "");

			String ForecastLL = data.getText("LLPlannedForecastRate");
			DBValues = sql.executeQuery(getContext(),
					ForecastLL.replace("store", getContext().getStringProperty("store"))
							.replace("Area", getContext().getStringProperty("Area"))
							.replace("Zone", getContext().getStringProperty("Zone"))
							.replace("FinWeek", getContext().getStringProperty("FinWk"))
							.replace("Year", getContext().getStringProperty("Year"))
							.replace("Division", getContext().getStringProperty("Division"))
							.replace("Region", getContext().getStringProperty("Region"))
							.replace("Week", getContext().getStringProperty("SelectFiscalWeek"))
							.replace("LYFinYr", LYYr).replace("Division", getContext().getStringProperty("Division")));
			System.out.println("DB Value: " + DBValues);

			String ForecastSF = data.getText("SFDPlannedForecastRate");
			DBTotal = sql.executeQuery(getContext(),
					ForecastSF.replace("store", getContext().getStringProperty("store"))
							.replace("Area", getContext().getStringProperty("Area"))
							.replace("Zone", getContext().getStringProperty("Zone"))
							.replace("FinWeek", getContext().getStringProperty("FinWk"))
							.replace("Year", getContext().getStringProperty("Year"))
							.replace("Division", getContext().getStringProperty("Division"))
							.replace("Region", getContext().getStringProperty("Region"))
							.replace("Week", getContext().getStringProperty("SelectFiscalWeek"))
							.replace("LYFinYr", LYYr).replace("Division", getContext().getStringProperty("Division")));
			System.out.println("DB Total: " + DBTotal);

			UIValues = pageMonthlyPlanningGroupPO.GetTotal(extentTest, objMonthlyPlanningGroupObjects.RowNameTotalLL,
					objWeeklyPlanningStoreObjects.FCSTRateLL);
			pageMonthlyPlanningPO.CompareValueDecimalFields(extentTest, DBValues, UIValues);

			UITotal = pageMonthlyPlanningGroupPO.GetTotal(extentTest, objMonthlyPlanningGroupObjects.RowNameTotalSFD,
					objWeeklyPlanningStoreObjects.FCSTRateSFD);
			pageMonthlyPlanningPO.CompareValueDecimalFields(extentTest, DBTotal, UITotal);
		}
	}*/

	@Test(priority = 61)
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
			data.loadTestDataXML(System.getProperty("user.dir") + "\\TestData\\WLP\\WeeklyPlanningStoreCombined.xml");
			customreport.createExtentReport();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

}