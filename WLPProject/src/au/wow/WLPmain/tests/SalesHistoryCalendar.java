package au.wow.WLPmain.tests;

import java.io.File;
import java.io.IOException;
import java.lang.reflect.Method;
import java.sql.SQLException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.support.PageFactory;
import org.testng.ITestContext;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import com.relevantcodes.extentreports.ExtentReports;

import au.wow.WLPmain.objects.DTS_DepartmentObjects;
import au.wow.WLPmain.objects.DailyPlanningStoreObjects;
import au.wow.WLPmain.objects.DailyTradingStatementObjects;
import au.wow.WLPmain.objects.MonthlyPlanningStoreObjects;
import au.wow.WLPmain.objects.PlanVsActualsObjects;
import au.wow.WLPmain.objects.SalesHistoryCalendarObjects;
import au.wow.WLPmain.objects.WeeklyPlanningStoreObjects;
import au.wow.WLPmain.pages.CommonFunctions;
import au.wow.WLPmain.pages.DTS_DepartmentPage;
import au.wow.WLPmain.pages.DailyPlanningPage;
import au.wow.WLPmain.pages.DailyTradingStatementGroupPage;
import au.wow.WLPmain.pages.DailyTradingStatementStorePage;
import au.wow.WLPmain.pages.MonthlyPlanningGroupPage;
import au.wow.WLPmain.pages.MonthlyPlanningStorePage;
import au.wow.WLPmain.pages.WeeklyDepartmentPlanningPage;
import au.wow.WLPmain.pages.WeeklyPlanningStorePage;
import au.wow.WLPmain.pages.WoWLoginPage;
import au.wow.wlp.utils.BasePage;
import au.wow.wlp.utils.CommonRepository;
import au.wow.wlp.utils.CustomExtentReports;
import au.wow.wlp.utils.ExtentReportsScreenshotCode;
import au.wow.wlp.utils.SQLWrapper;
import au.wow.wlp.utils.TestBase;
import au.wow.wlp.utils.TestStatus;
import au.wow.wlp.utils.XMLDataReader;

public class SalesHistoryCalendar extends TestBase {
	protected Logger log = LogManager.getLogger(SalesHistoryCalendar.class);
	TestStatus status;
	ExtentReportsScreenshotCode ScreenShotUtil;
	SQLWrapper sql = new SQLWrapper(log);
	SQLWrapper sql1 = new SQLWrapper(log);
	WoWLoginPage loginPage;
	CommonRepository CommonFunc;
	WeeklyPlanningStoreObjects objWeeklyPlanningStoreObjects;
	DailyTradingStatementObjects objDailyTradingStatementObjects;
	DailyPlanningStoreObjects objDailyPlanningStoreObjects;
	WeeklyPlanningStorePage pageWeeklyPlanningPO;
	MonthlyPlanningStorePage pageMonthlyPlanningPO;
	DailyTradingStatementStorePage pageDailyTradingStatementStorePO;
	DailyTradingStatementGroupPage pageDailyTradingStatementGroupPO;
	WeeklyDepartmentPlanningPage pageWeeklyDepartmentPlanningLLPO;
	MonthlyPlanningGroupPage pageMonthlyPlanningGroupPO;
	MonthlyPlanningStoreObjects objMonthlyPlanningStoreObjects;
	DailyPlanningPage pageDailyPlanningPO;
	DTS_DepartmentPage pageDTSDepartmentPO;
	SalesHistoryCalendarObjects objSalesHistoryCalendarobjects;
	BasePage BaseObj;
	CommonFunctions common;
	StoreProperties storeprop;

	List<List<Object>> UIValues, UITotal;
	List<List<Object>> DBSalesLY, SlsLY, DBSalesForecast, Slsfcst, DBSalesActual, Slsactl, DBWeekdayValue, WkdayVal;

	public SalesHistoryCalendar() {
		super.log = log;
	}

	@Test(priority = 0)
	public void LogintoCLRApplication() throws Exception {
		loginPage = new WoWLoginPage().createPage(getDriver(), getContext(), log, status, data, report, extentTest,
				WoWLoginPage.class);
		pageWeeklyPlanningPO = loginPage.navToWeeklyPlanningStorePg(getContext().getStringProperty("store"));
	}

	////////////// Sales History Calendar ////////////
	@Test(description = "SalesHistoryCalendarValidation", priority = 1)
	public void WeekTypeColumn() throws Exception {
		pageWeeklyDepartmentPlanningLLPO = new WeeklyDepartmentPlanning().createPage(getDriver(), getContext(), log,
				status, data, getReport(), getReportLogger(), WeeklyDepartmentPlanningPage.class);
		pageDTSDepartmentPO = new DTS_DepartmentPage().createPage(getDriver(), getContext(), log, status, data,
				getReport(), getReportLogger(), DTS_DepartmentPage.class);
		pageWeeklyPlanningPO = new WeeklyPlanningStorePage().createPage(getDriver(), getContext(), log, status, data,
				getReport(), getReportLogger(), WeeklyPlanningStorePage.class);
		pageDailyTradingStatementStorePO = new DailyTradingStatementStorePage().createPage(getDriver(), getContext(),
				log, status, data, getReport(), getReportLogger(), DailyTradingStatementStorePage.class);
		pageDailyTradingStatementGroupPO = new DailyTradingStatementGroupPage().createPage(getDriver(), getContext(),
				log, status, data, getReport(), getReportLogger(), DailyTradingStatementGroupPage.class);
		TestStatus status = getTestStatus();
		System.out.println("Welcome " + getContext().getStringProperty("username"));
		System.out.println("Entering into TestCase : Sales History Calendar");
		pageWeeklyPlanningPO.SelectWeekFromDropdown(objWeeklyPlanningStoreObjects, extentTest,
				getContext().getStringProperty("SelectFiscalWeek"));

		pageWeeklyPlanningPO.selectPage(extentTest, objWeeklyPlanningStoreObjects.menuBar,
				objWeeklyPlanningStoreObjects.calenderScreens, "Calender");
		pageWeeklyPlanningPO.selectPage(extentTest, objWeeklyPlanningStoreObjects.menuBar,
				objWeeklyPlanningStoreObjects.screenToViewSalesHisCalendar, "Sales History Calendar");
		/*pageWeeklyPlanningPO.verifyPageName(extentTest, objWeeklyPlanningStoreObjects.DataFrame,
				objWeeklyPlanningStoreObjects.pageName, "Sales History Calendar");*/

		String SalesHistoryCalendarIp = getContext().getStringProperty("DepartmentToView");
		if (SalesHistoryCalendarIp.equalsIgnoreCase("-1"))
			SalesHistoryCalendarIp = "StoreTotal";
		else
			SalesHistoryCalendarIp = getContext().getStringProperty("DepartmentToView");
		pageWeeklyPlanningPO.SelectDepartmentFilter(objSalesHistoryCalendarobjects, extentTest,
				getContext().getStringProperty("DepartmentToView"));

		// Sales LY
		if (SalesHistoryCalendarIp.equalsIgnoreCase("StoreTotal")) {
			Report_AddStep("testcase", "Sales History Calendar- Sales LY", "", "", "");
			extentTest = report.startTest("Sales History Calendar- Sales LY");

			String SalesLY_Storetotal = data.getText("SalesLY_Storetotal");
			DBSalesLY = sql.CLRexecuteQuery(getContext(),
					SalesLY_Storetotal.replace("store", getContext().getStringProperty("store"))
							.replace("Week", getContext().getStringProperty("SelectFiscalWeek"))
							.replace("FinYear", getContext().getStringProperty("FinYr"))
							.replace("LYFinYr", getContext().getStringProperty("LastYr"))
							.replace("Division", getContext().getStringProperty("Division")));
			System.out.println("DB Value: " + DBSalesLY);

			SlsLY = pageDTSDepartmentPO.SlsHistCalUIResults("SalesLY_Storetotal", objSalesHistoryCalendarobjects.WeekId,
					objSalesHistoryCalendarobjects.SalesLY, objSalesHistoryCalendarobjects.table);
			pageMonthlyPlanningPO.CompareValues(extentTest, DBSalesLY, SlsLY);
		} else {
			pageWeeklyDepartmentPlanningLLPO.SelectDepartment(objSalesHistoryCalendarobjects.DataFrame,
					objSalesHistoryCalendarobjects.DepartmentHeader, objSalesHistoryCalendarobjects.DepartmentDropdown,
					extentTest, getContext().getStringProperty("DepartmentToView"));
			Report_AddStep("testcase", "Sales History Calendar- Sales LY", "", "", "");
			extentTest = report.startTest("Sales History Calendar- Sales LY");
			
			String SalesLY_Deptwise = data.getText("SalesLY_Deptwise");
			DBSalesLY = sql.CLRexecuteQuery(getContext(),
					SalesLY_Deptwise.replace("store", getContext().getStringProperty("store"))
							.replace("Week", getContext().getStringProperty("SelectFiscalWeek"))
							.replace("FinYear", getContext().getStringProperty("FinYr"))
							.replace("Dept", getContext().getStringProperty("DepartmentToView"))
							.replace("LYFinYr", getContext().getStringProperty("LastYr"))
							.replace("Division", getContext().getStringProperty("Division")));
			System.out.println("DB Value: " + DBSalesLY);

			SlsLY = pageDTSDepartmentPO.SlsHistCalUIResults("SalesLY", objSalesHistoryCalendarobjects.WeekId,
					objSalesHistoryCalendarobjects.SalesLY, objSalesHistoryCalendarobjects.table);
			pageMonthlyPlanningPO.CompareValues(extentTest, DBSalesLY, SlsLY);
		}

		// Sales Forecast
		if (SalesHistoryCalendarIp.equalsIgnoreCase("StoreTotal")) {
			Report_AddStep("testcase", "Sales History Calendar- Sales Forecast", "", "", "");
			extentTest = report.startTest("Sales History Calendar - Forecast Sales");
			
			String SalesForecast_Storetotal = data.getText("SalesForecast_Storetotal");
			DBSalesLY = sql.CLRexecuteQuery(getContext(),
					SalesForecast_Storetotal.replace("store", getContext().getStringProperty("store"))
							.replace("Week", getContext().getStringProperty("SelectFiscalWeek"))
							.replace("FinYear", getContext().getStringProperty("FinYr"))
							.replace("Division", getContext().getStringProperty("Division")));
			System.out.println("DB Value: " + DBSalesLY);

			Slsfcst = pageDTSDepartmentPO.SlsHistCalUIResults("SalesForecast_Storetotal",
					objSalesHistoryCalendarobjects.WeekId, objSalesHistoryCalendarobjects.SalesFcst,
					objSalesHistoryCalendarobjects.table);
			pageMonthlyPlanningPO.CompareValues(extentTest, DBSalesLY, Slsfcst);
		} else {
			pageWeeklyDepartmentPlanningLLPO.SelectDepartment(objSalesHistoryCalendarobjects.DataFrame,
					objSalesHistoryCalendarobjects.DepartmentHeader, objSalesHistoryCalendarobjects.DepartmentDropdown,
					extentTest, getContext().getStringProperty("DepartmentToView"));
			Report_AddStep("testcase", "Sales History Calendar- Sales Forecast", "", "", "");
			extentTest = report.startTest("Sales History Calendar - Forecast Sales");
			
			String SalesForecast_Deptwise = data.getText("SalesForecast_Deptwise");
			DBSalesLY = sql.CLRexecuteQuery(getContext(),
					SalesForecast_Deptwise.replace("store", getContext().getStringProperty("store"))
							.replace("Week", getContext().getStringProperty("SelectFiscalWeek"))
							.replace("FinYear", getContext().getStringProperty("FinYr"))
							.replace("Dept", getContext().getStringProperty("DepartmentToView"))
							.replace("Division", getContext().getStringProperty("Division")));
			System.out.println("DB Value: " + DBSalesLY);

			Slsfcst = pageDTSDepartmentPO.SlsHistCalUIResults("SalesForecast_Deptwise",
					objSalesHistoryCalendarobjects.WeekId, objSalesHistoryCalendarobjects.SalesFcst,
					objSalesHistoryCalendarobjects.table);
			pageMonthlyPlanningPO.CompareValues(extentTest, DBSalesLY, Slsfcst);
		}

		// Sales Actual
		if (SalesHistoryCalendarIp.equalsIgnoreCase("StoreTotal")) {
			Report_AddStep("testcase", "Sales History Calendar- Sales Actual", "", "", "");
			extentTest = report.startTest("Sales History Calendar - Actual Sales");
			
			String SalesActual_Storetotal = data.getText("SalesActual_Storetotal");
			DBSalesLY = sql.CLRexecuteQuery(getContext(),
					SalesActual_Storetotal.replace("store", getContext().getStringProperty("store"))
							.replace("Week", getContext().getStringProperty("SelectFiscalWeek"))
							.replace("FinYear", getContext().getStringProperty("FinYr"))
							.replace("FinYear", getContext().getStringProperty("FinYr"))
							.replace("Division", getContext().getStringProperty("Division")));
			System.out.println("DB Value: " + DBSalesLY);

			Slsactl = pageDTSDepartmentPO.SlsHistCalUIResults("SalesActual_Storetotal",
					objSalesHistoryCalendarobjects.WeekId, objSalesHistoryCalendarobjects.SalesActual,
					objSalesHistoryCalendarobjects.table);
			pageMonthlyPlanningPO.CompareValues(extentTest, DBSalesLY, Slsactl);
		} else {
			pageWeeklyDepartmentPlanningLLPO.SelectDepartment(objSalesHistoryCalendarobjects.DataFrame,
					objSalesHistoryCalendarobjects.DepartmentHeader, objSalesHistoryCalendarobjects.DepartmentDropdown,
					extentTest, getContext().getStringProperty("DepartmentToView"));
			Report_AddStep("testcase", "Sales History Calendar- Sales Actual", "", "", "");
			extentTest = report.startTest("Sales History Calendar - Actual Sales");
			
			String SalesActual_Deptwise = data.getText("SalesActual_Deptwise");
			DBSalesLY = sql.CLRexecuteQuery(getContext(),
					SalesActual_Deptwise.replace("store", getContext().getStringProperty("store"))
							.replace("Week", getContext().getStringProperty("SelectFiscalWeek"))
							.replace("FinYear", getContext().getStringProperty("FinYr"))
							.replace("Dept", getContext().getStringProperty("DepartmentToView"))
							.replace("Division", getContext().getStringProperty("Division")));
			System.out.println("DB Value: " + DBSalesLY);

			Slsactl = pageDTSDepartmentPO.SlsHistCalUIResults("SalesActual_Deptwise",
					objSalesHistoryCalendarobjects.WeekId, objSalesHistoryCalendarobjects.SalesActual,
					objSalesHistoryCalendarobjects.table);
			pageMonthlyPlanningPO.CompareValues(extentTest, DBSalesLY, Slsactl);
		}

		// Weekday Values
		if (SalesHistoryCalendarIp.equalsIgnoreCase("StoreTotal")) {
			Report_AddStep("testcase", "Sales History Calendar- Week day Value", "", "", "");
			extentTest = report.startTest("Sales History Calendar - Actual Weekday");
			
			String WkDay_SalesActual_Storetotal = data.getText("WkDay_SalesActual_Storetotal");
			DBWeekdayValue = sql.CLRexecuteQuery(getContext(),
					WkDay_SalesActual_Storetotal.replace("store", getContext().getStringProperty("store"))
							.replace("Week", getContext().getStringProperty("SelectFiscalWeek"))
							.replace("FinYear", getContext().getStringProperty("FinYr"))
							.replace("Division", getContext().getStringProperty("Division")));
			System.out.println("DB Value: " + DBWeekdayValue);

			WkdayVal = pageDTSDepartmentPO.WeekdayUIValue(objSalesHistoryCalendarobjects,
					objSalesHistoryCalendarobjects.Weekdaytitle, objSalesHistoryCalendarobjects.Mondayvalue,
					objSalesHistoryCalendarobjects.Tuesdayvalue, objSalesHistoryCalendarobjects.Wednesdayvalue,
					objSalesHistoryCalendarobjects.Thursdayvalue, objSalesHistoryCalendarobjects.Fridayvalue,
					objSalesHistoryCalendarobjects.Saturdayvalue, objSalesHistoryCalendarobjects.Sundayvalue,
					objSalesHistoryCalendarobjects.table, objSalesHistoryCalendarobjects.WeekId);
			System.out.println("UI Value:" + WkdayVal);

		} else {
			pageWeeklyDepartmentPlanningLLPO.SelectDepartment(objSalesHistoryCalendarobjects.DataFrame,
					objSalesHistoryCalendarobjects.DepartmentHeader, objSalesHistoryCalendarobjects.DepartmentDropdown,
					extentTest, getContext().getStringProperty("DepartmentToView"));
			Report_AddStep("testcase", "Sales History Calendar- Week day Value", "", "", "");
			extentTest = report.startTest("Sales History Calendar - Actual Weekday");
			
			String WkDay_SalesActual_Deptwise = data.getText("WkDay_SalesActual_Deptwise");
			DBWeekdayValue = sql.CLRexecuteQuery(getContext(),
					WkDay_SalesActual_Deptwise.replace("store", getContext().getStringProperty("store"))
							.replace("Week", getContext().getStringProperty("SelectFiscalWeek"))
							.replace("FinYear", getContext().getStringProperty("FinYr"))
							.replace("Dept", getContext().getStringProperty("DepartmentToView"))
							.replace("Division", getContext().getStringProperty("Division")));
			System.out.println("DB Value: " + DBWeekdayValue);

			WkdayVal = pageDTSDepartmentPO.WeekdayUIValue(objSalesHistoryCalendarobjects,
					objSalesHistoryCalendarobjects.Weekdaytitle, objSalesHistoryCalendarobjects.Mondayvalue,
					objSalesHistoryCalendarobjects.Tuesdayvalue, objSalesHistoryCalendarobjects.Wednesdayvalue,
					objSalesHistoryCalendarobjects.Thursdayvalue, objSalesHistoryCalendarobjects.Fridayvalue,
					objSalesHistoryCalendarobjects.Saturdayvalue, objSalesHistoryCalendarobjects.Sundayvalue,
					objSalesHistoryCalendarobjects.table, objSalesHistoryCalendarobjects.WeekId);

		}
		pageMonthlyPlanningPO.CompareValues(extentTest, DBWeekdayValue, WkdayVal);

		// Screen validation
		Report_AddStep("testcase", "Sales History Calendar- Screen Validation", "", "", "");
		extentTest = report.startTest("Sales History Calendar - Screen Validation");
		
		pageDTSDepartmentPO.ScreenValidation(objSalesHistoryCalendarobjects.MainHeading,
				objSalesHistoryCalendarobjects.MainHeadingPreviousYearBox,
				objSalesHistoryCalendarobjects.MainHeadingNextYearBox, objSalesHistoryCalendarobjects.TableAreaHeading);
	}

	@Test(priority = 3)
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
		pageWeeklyDepartmentPlanningLLPO = PageFactory.initElements(this.getDriver(),
				WeeklyDepartmentPlanningPage.class);
		pageMonthlyPlanningGroupPO = PageFactory.initElements(this.getDriver(), MonthlyPlanningGroupPage.class);
		pageDTSDepartmentPO = PageFactory.initElements(this.getDriver(), DTS_DepartmentPage.class);
		pageDailyTradingStatementStorePO = PageFactory.initElements(this.getDriver(),
				DailyTradingStatementStorePage.class);
		pageDailyTradingStatementGroupPO = PageFactory.initElements(this.getDriver(),
				DailyTradingStatementGroupPage.class);
		BaseObj = PageFactory.initElements(this.getDriver(), BasePage.class);
		common = PageFactory.initElements(this.getDriver(), CommonFunctions.class);
		storeprop = PageFactory.initElements(this.getDriver(), StoreProperties.class);
		objWeeklyPlanningStoreObjects = PageFactory.initElements(this.getDriver(), WeeklyPlanningStoreObjects.class);
		objDailyTradingStatementObjects = PageFactory.initElements(this.getDriver(),
				DailyTradingStatementObjects.class);
		pageMonthlyPlanningPO = PageFactory.initElements(this.getDriver(), MonthlyPlanningStorePage.class);
		objSalesHistoryCalendarobjects = PageFactory.initElements(this.getDriver(), SalesHistoryCalendarObjects.class);
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
			data.loadTestDataXML(System.getProperty("user.dir") + "\\TestData\\WLP\\SalesHistoryCalendar.xml");
			customreport.createExtentReport();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
}
