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
import com.relevantcodes.extentreports.LogStatus;

import au.wow.WLPmain.objects.AllocationofFixHoursObjects;
import au.wow.WLPmain.objects.DTS_DepartmentObjects;
import au.wow.WLPmain.objects.DailyTradingStatementObjects;
import au.wow.WLPmain.objects.WeeklyDepartmentPlanningObjects;
import au.wow.WLPmain.objects.WeeklyPlanningStoreObjects;
import au.wow.WLPmain.pages.AlocationofFixHoursPage;
import au.wow.WLPmain.pages.CommonFunctions;
import au.wow.WLPmain.pages.DTS_DepartmentPage;
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

public class AllocationofFixHours extends TestBase {

	protected Logger log = LogManager.getLogger(AllocationofFixHours.class);
	TestStatus status;
	ExtentReportsScreenshotCode ScreenShotUtil;
	SQLWrapper sql = new SQLWrapper(log);
	WoWLoginPage loginPage;
	CommonRepository CommonFunc;
	WeeklyPlanningStoreObjects objWeeklyPlanningStoreObjects;
	DailyTradingStatementObjects objDailyTradingStatementObjects;
	AllocationofFixHoursObjects objAllocationofFixHoursObjects;
	WeeklyPlanningStorePage pageWeeklyPlanningPO;
	MonthlyPlanningStorePage pageMonthlyPlanningPO;
	DailyTradingStatementStorePage pageDailyTradingStatementStorePO;
	AlocationofFixHoursPage pageAlocationofFixHoursPO;
	MonthlyPlanningGroupPage pageMonthlyPlanningGroupPO;
	DTS_DepartmentPage pageDTSDepartmentPO;
	DTS_DepartmentObjects objDTSDepartmentObjects;
	WeeklyDepartmentPlanningPage pageWeeklyDepartmentPlanningLLPO;
	WeeklyDepartmentPlanningObjects objWeeklyDepartmentPlanningObjects;

	BasePage BaseObj;
	CommonFunctions common;
	StoreProperties storeprop;

	List<List<Object>> DBFixPrcnt, DBFixPrcntTtl, DBFixHrs, DBFixHrsTtl, DBVarHrs, DBVarHrsTtl, DBTtl, DBTtlColTtl,
			DBTaskTyp, DBVarStd, DBVarTyp, DBPrior;
	List<List<Object>> FixPrcnt, FixPrcntTtl, FixHrs, FixHrsTtl, VarHrs, VarHrsTtl, Ttl, TtlColTtl, TaskTyp, VarStnd,
			VarTyp, Prior;
	String Depts;

	public AllocationofFixHours() {
		super.log = log;
	}

	///////////////////// SUMMARY INFO /////////////////////////
	@Test(description = "Allocation of Fixed Hours", priority = 0)
	public void AllocationofFixHours() throws Exception {
		loginPage = new WoWLoginPage().createPage(getDriver(), getContext(), log, status, data, report, extentTest,
				WoWLoginPage.class);
		pageWeeklyDepartmentPlanningLLPO = new AllocationofFixHours().createPage(getDriver(), getContext(), log, status,
				data, getReport(), getReportLogger(), WeeklyDepartmentPlanningPage.class);
		pageAlocationofFixHoursPO = new AllocationofFixHours().createPage(getDriver(), getContext(), log, status, data,
				getReport(), getReportLogger(), AlocationofFixHoursPage.class);
		pageMonthlyPlanningGroupPO = new MonthlyPlanningGroupPage().createPage(getDriver(), getContext(), log, status,
				data, getReport(), getReportLogger(), MonthlyPlanningGroupPage.class);
		pageWeeklyPlanningPO = loginPage.navToWeeklyPlanningStorePg(getContext().getStringProperty("store"));
		TestStatus status = getTestStatus();
		System.out.println("Welcome " + getContext().getStringProperty("username"));
		System.out.println("Entering into TestCase : Weekly Planning Department");
		pageWeeklyPlanningPO.SelectWeekFromDropdown(objWeeklyPlanningStoreObjects, extentTest,
				getContext().getStringProperty("SelectFiscalWeek"));

		pageWeeklyPlanningPO.selectPage(extentTest, objWeeklyPlanningStoreObjects.menuBar,
				objWeeklyPlanningStoreObjects.otherScreens, "Others");
		pageWeeklyPlanningPO.selectPage(extentTest, objWeeklyPlanningStoreObjects.menuBar,
				objWeeklyPlanningStoreObjects.AllocationscreenToView,
				getContext().getStringProperty("AllocatedofFixHrs"));
		//pageWeeklyPlanningPO.verifyPageName(extentTest, objWeeklyPlanningStoreObjects.DataFrame,
			//	objWeeklyPlanningStoreObjects.pageName, getContext().getStringProperty("AllocatedofFixHrs"));

		//pageWeeklyPlanningPO.SwitchToFrame(objAllocationofFixHoursObjects.DataFrame);
		pageWeeklyDepartmentPlanningLLPO.SelectDepartment(objWeeklyDepartmentPlanningObjects.DataFrame,
				objAllocationofFixHoursObjects.DepartmentHeader, objAllocationofFixHoursObjects.DepartmentDropdown,
				extentTest, getContext().getStringProperty("DepartmentToView"));
		Depts = getContext().getStringProperty("DepartmentToView");
		waitFor(4);

		// Task Details Validation	
		String TaskCode = data.getText("TaskCode");
		DBTaskTyp = sql.CLRexecuteQuery(getContext(),
				TaskCode.replace("store", getContext().getStringProperty("store"))
						.replace("FinYr", getContext().getStringProperty("FinYr"))
						.replace("FinWk", getContext().getStringProperty("FinWk"))
						.replace("Department", getContext().getStringProperty("DepartmentToView"))
						.replace("Week", getContext().getStringProperty("SelectFiscalWeek")));
		System.out.println("DB Value: " + DBTaskTyp);

		String VarStd = data.getText("VarStd");
		DBVarStd = sql.CLRexecuteQuery(getContext(),
				VarStd.replace("store", getContext().getStringProperty("store"))
						.replace("FinYr", getContext().getStringProperty("FinYr"))
						.replace("FinWk", getContext().getStringProperty("FinWk"))
						.replace("Department", getContext().getStringProperty("DepartmentToView"))
						.replace("Week", getContext().getStringProperty("SelectFiscalWeek")));
		System.out.println("DB Value: " + DBVarStd);

		String VarType = data.getText("VarType");
		DBVarTyp = sql.CLRexecuteQuery(getContext(),
				VarType.replace("store", getContext().getStringProperty("store"))
						.replace("FinYr", getContext().getStringProperty("FinYr"))
						.replace("FinWk", getContext().getStringProperty("FinWk"))
						.replace("Department", getContext().getStringProperty("DepartmentToView"))
						.replace("Week", getContext().getStringProperty("SelectFiscalWeek")));
		System.out.println("DB Value: " + DBVarTyp);

		String Priority = data.getText("Priority");
		DBPrior = sql.CLRexecuteQuery(getContext(),
				Priority.replace("store", getContext().getStringProperty("store"))
						.replace("FinYr", getContext().getStringProperty("FinYr"))
						.replace("FinWk", getContext().getStringProperty("FinWk"))
						.replace("Department", getContext().getStringProperty("DepartmentToView"))
						.replace("Week", getContext().getStringProperty("SelectFiscalWeek")));
		System.out.println("DB Value: " + DBPrior);

		TaskTyp = pageAlocationofFixHoursPO.GetTotal(extentTest, objAllocationofFixHoursObjects.SMSJobs,
				objAllocationofFixHoursObjects.Taskcode, objAllocationofFixHoursObjects.Table, "Task Type");
		VarStnd = pageAlocationofFixHoursPO.GetTotal(extentTest, objAllocationofFixHoursObjects.SMSJobs,
				objAllocationofFixHoursObjects.VarStd, objAllocationofFixHoursObjects.Table, "Variable Standard");
		VarTyp = pageAlocationofFixHoursPO.GetTotal(extentTest, objAllocationofFixHoursObjects.SMSJobs,
				objAllocationofFixHoursObjects.VarTyp, objAllocationofFixHoursObjects.Table, "Variable Type");
		Prior = pageAlocationofFixHoursPO.GetTotal(extentTest, objAllocationofFixHoursObjects.SMSJobs,
				objAllocationofFixHoursObjects.Priority, objAllocationofFixHoursObjects.Table, "Priority");

		Report_AddStep("testcase", "TASK CODE VALIDATION", "", "", "");
		extentTest = report.startTest("Task Code Validation");
	
		pageAlocationofFixHoursPO.CompareValues(extentTest, DBTaskTyp, TaskTyp);

		Report_AddStep("testcase", "VARIABLE STANDARD VALIDATION", "", "", "");
		extentTest = report.startTest("Variable Standard Validation");

		pageMonthlyPlanningPO.CompareValueDecimalFields(extentTest, DBVarStd, VarStnd);

		Report_AddStep("testcase", "VARIABLE TYPE VALIDATION", "", "", "");
		extentTest = report.startTest("Variable Type Validation");

		pageAlocationofFixHoursPO.CompareValues(extentTest, DBVarTyp, VarTyp);

		Report_AddStep("testcase", "PRIORITY VALIDATION", "", "", "");
		extentTest = report.startTest("Priority Validation");

		pageMonthlyPlanningPO.CompareValues(extentTest, DBPrior, Prior);

		// Fixed Percentage
		Report_AddStep("testcase", "FIXED HOURS PERCENTAGE", "", "", "");
		extentTest = report.startTest("Fixed Hours Percentage");

		String FixedHoursPercentage = data.getText("FixedHoursPercentage");
		DBFixPrcnt = sql.CLRexecuteQuery(getContext(),
				FixedHoursPercentage.replace("store", getContext().getStringProperty("store"))
						.replace("FinYr", getContext().getStringProperty("FinYr"))
						.replace("FinWk", getContext().getStringProperty("FinWk"))
						.replace("Department", getContext().getStringProperty("DepartmentToView"))
						.replace("Week", getContext().getStringProperty("SelectFiscalWeek")));
		System.out.println("DB Value: " + DBFixPrcnt);

		String FixedHoursPercentageTotal = data.getText("FixedHoursPercentageTotal");
		DBFixPrcntTtl = sql.CLRexecuteQuery(getContext(),
				FixedHoursPercentageTotal.replace("store", getContext().getStringProperty("store"))
						.replace("FinYr", getContext().getStringProperty("FinYr"))
						.replace("FinWk", getContext().getStringProperty("FinWk"))
						.replace("Department", getContext().getStringProperty("DepartmentToView"))
						.replace("Week", getContext().getStringProperty("SelectFiscalWeek")));
		System.out.println("DB Value: " + DBFixPrcntTtl);

		FixPrcnt = pageAlocationofFixHoursPO.UIResults(objAllocationofFixHoursObjects.Table, "Fixed Percentage",
				objAllocationofFixHoursObjects.FxdHrsPrcntMon, objAllocationofFixHoursObjects.FxdHrsPrcntTue,
				objAllocationofFixHoursObjects.FxdHrsPrcntWed, objAllocationofFixHoursObjects.FxdHrsPrcntThu,
				objAllocationofFixHoursObjects.FxdHrsPrcntFri, objAllocationofFixHoursObjects.FxdHrsPrcntSat,
				objAllocationofFixHoursObjects.FxdHrsPrcntSun, objAllocationofFixHoursObjects.SMSJobs);
		FixPrcntTtl = pageAlocationofFixHoursPO.GetTotal(extentTest, objAllocationofFixHoursObjects.SMSJobs,
				objAllocationofFixHoursObjects.FxdHrsPrcntTtl, objAllocationofFixHoursObjects.Table,
				"Fixed Percentage");

		pageDailyTradingStatementStorePO.CompareValueDecimalFields(extentTest, DBFixPrcnt, FixPrcnt);
		pageMonthlyPlanningPO.CompareValueDecimalFields(extentTest, DBFixPrcntTtl, FixPrcntTtl);

		// Fixed Hours
		Report_AddStep("testcase", "FIXED HOURS", "", "", "");
		extentTest = report.startTest("Fixed Hours");

		String FixedHours = data.getText("FixedHours");
		DBFixHrs = sql.CLRexecuteQuery(getContext(),
				FixedHours.replace("store", getContext().getStringProperty("store"))
						.replace("FinYr", getContext().getStringProperty("FinYr"))
						.replace("Week", getContext().getStringProperty("SelectFiscalWeek"))
						.replace("Department", getContext().getStringProperty("DepartmentToView"))
						.replace("Week", getContext().getStringProperty("SelectFiscalWeek")));
		System.out.println("DB Value: " + DBFixHrs);

		String FixedHoursTotal = data.getText("FixedHoursTotal");
		DBFixHrsTtl = sql.CLRexecuteQuery(getContext(),
				FixedHoursTotal.replace("store", getContext().getStringProperty("store"))
						.replace("FinYr", getContext().getStringProperty("FinYr"))
						.replace("Week", getContext().getStringProperty("SelectFiscalWeek"))
						.replace("Department", getContext().getStringProperty("DepartmentToView"))
						.replace("Week", getContext().getStringProperty("SelectFiscalWeek")));
		System.out.println("DB Value: " + DBFixHrsTtl);

		FixHrs = pageAlocationofFixHoursPO.UIResults(objAllocationofFixHoursObjects.Table, "Fixed Hours",
				objAllocationofFixHoursObjects.FxdHrsMon, objAllocationofFixHoursObjects.FxdHrsTue,
				objAllocationofFixHoursObjects.FxdHrsWed, objAllocationofFixHoursObjects.FxdHrsThu,
				objAllocationofFixHoursObjects.FxdHrsFri, objAllocationofFixHoursObjects.FxdHrsSat,
				objAllocationofFixHoursObjects.FxdHrsSun, objAllocationofFixHoursObjects.SMSJobs);
		FixHrsTtl = pageAlocationofFixHoursPO.GetTotal(extentTest, objAllocationofFixHoursObjects.SMSJobs,
				objAllocationofFixHoursObjects.FxdHrsTtl, objAllocationofFixHoursObjects.Table, "Fixed Hours");

		pageDailyTradingStatementStorePO.CompareValueDecimalFields(extentTest, DBFixHrs, FixHrs);
		pageMonthlyPlanningPO.CompareValueDecimalFields(extentTest, DBFixHrsTtl, FixHrsTtl);

		// Variable Hours
		Report_AddStep("testcase", "VARIABLE HOURS", "", "", "");
		extentTest = report.startTest("Variable Hours");

		if (Depts.equals("5") || Depts.equals("10") || Depts.equals("15")) {
			String VarHoursLonglife = data.getText("VarHoursLonglife");
			DBVarHrs = sql.CLRexecuteQuery(getContext(),
					VarHoursLonglife.replace("store", getContext().getStringProperty("store"))
							.replace("FinYr", getContext().getStringProperty("FinYr"))
							.replace("Week", getContext().getStringProperty("SelectFiscalWeek"))
							.replace("Department", getContext().getStringProperty("DepartmentToView"))
							.replace("Week", getContext().getStringProperty("SelectFiscalWeek")));
			System.out.println("DB Value: " + DBVarHrs);

			String VarHoursLonglifeTotal = data.getText("VarHoursLonglifeTotal");
			DBVarHrsTtl = sql.CLRexecuteQuery(getContext(),
					VarHoursLonglifeTotal.replace("store", getContext().getStringProperty("store"))
							.replace("FinYr", getContext().getStringProperty("FinYr"))
							.replace("Week", getContext().getStringProperty("SelectFiscalWeek"))
							.replace("Department", getContext().getStringProperty("DepartmentToView"))
							.replace("Week", getContext().getStringProperty("SelectFiscalWeek")));
			System.out.println("DB Value: " + DBVarHrsTtl);
		} else {
			String VarHoursNonLonglife = data.getText("VarHoursNonLonglife");
			DBVarHrs = sql.CLRexecuteQuery(getContext(),
					VarHoursNonLonglife.replace("store", getContext().getStringProperty("store"))
							.replace("FinYr", getContext().getStringProperty("FinYr"))
							.replace("Week", getContext().getStringProperty("SelectFiscalWeek"))
							.replace("Department", getContext().getStringProperty("DepartmentToView"))
							.replace("Week", getContext().getStringProperty("SelectFiscalWeek")));
			System.out.println("DB Value: " + DBVarHrs);

			String VarHoursNonLonglifeTotal = data.getText("VarHoursNonLonglifeTotal");
			DBVarHrsTtl = sql.CLRexecuteQuery(getContext(),
					VarHoursNonLonglifeTotal.replace("store", getContext().getStringProperty("store"))
							.replace("FinYr", getContext().getStringProperty("FinYr"))
							.replace("Week", getContext().getStringProperty("SelectFiscalWeek"))
							.replace("Department", getContext().getStringProperty("DepartmentToView"))
							.replace("Week", getContext().getStringProperty("SelectFiscalWeek")));
			System.out.println("DB Value: " + DBVarHrsTtl);
		}

		VarHrs = pageAlocationofFixHoursPO.UIResults(objAllocationofFixHoursObjects.Table, "Variable Hours",
				objAllocationofFixHoursObjects.VarHrsMon, objAllocationofFixHoursObjects.VarHrsTue,
				objAllocationofFixHoursObjects.VarHrsWed, objAllocationofFixHoursObjects.VarHrsThu,
				objAllocationofFixHoursObjects.VarHrsFri, objAllocationofFixHoursObjects.VarHrsSat,
				objAllocationofFixHoursObjects.VarHrsSun, objAllocationofFixHoursObjects.SMSJobs);
		VarHrsTtl = pageAlocationofFixHoursPO.GetTotal(extentTest, objAllocationofFixHoursObjects.SMSJobs,
				objAllocationofFixHoursObjects.VarHrsTtl, objAllocationofFixHoursObjects.Table, "Variable Hours");

		pageDailyTradingStatementStorePO.CompareValueDecimalFields(extentTest, DBVarHrs, VarHrs);
		pageMonthlyPlanningPO.CompareValueDecimalFields(extentTest, DBVarHrsTtl, VarHrsTtl);

		// Total Hours
		Report_AddStep("testcase", "TOTAL HOURS", "", "", "");
		extentTest = report.startTest("Total Hours");

		if (Depts.equals("5") || Depts.equals("10") || Depts.equals("15")) {
			String TotalHoursLonglife = data.getText("TotalHoursLonglife");
			DBTtl = sql.CLRexecuteQuery(getContext(),
					TotalHoursLonglife.replace("store", getContext().getStringProperty("store"))
							.replace("FinYr", getContext().getStringProperty("FinYr"))
							.replace("Week", getContext().getStringProperty("SelectFiscalWeek"))
							.replace("Department", getContext().getStringProperty("DepartmentToView"))
							.replace("Week", getContext().getStringProperty("SelectFiscalWeek")));
			System.out.println("DB Value: " + DBTtl);

			String TotalHoursLonglifeTtl = data.getText("TotalHoursLonglifeTtl");
			DBTtlColTtl = sql.CLRexecuteQuery(getContext(),
					TotalHoursLonglifeTtl.replace("store", getContext().getStringProperty("store"))
							.replace("FinYr", getContext().getStringProperty("FinYr"))
							.replace("Week", getContext().getStringProperty("SelectFiscalWeek"))
							.replace("Department", getContext().getStringProperty("DepartmentToView"))
							.replace("Week", getContext().getStringProperty("SelectFiscalWeek")));
			System.out.println("DB Value: " + DBTtlColTtl);
		} else {
			String TotalHoursNonLonglife = data.getText("TotalHoursNonLonglife");
			DBTtl = sql.CLRexecuteQuery(getContext(),
					TotalHoursNonLonglife.replace("store", getContext().getStringProperty("store"))
							.replace("FinYr", getContext().getStringProperty("FinYr"))
							.replace("Week", getContext().getStringProperty("SelectFiscalWeek"))
							.replace("Department", getContext().getStringProperty("DepartmentToView"))
							.replace("Week", getContext().getStringProperty("SelectFiscalWeek")));
			System.out.println("DB Value: " + DBTtl);

			String TotalHoursNonLonglifeTtl = data.getText("TotalHoursNonLonglifeTtl");
			DBTtlColTtl = sql.CLRexecuteQuery(getContext(),
					TotalHoursNonLonglifeTtl.replace("store", getContext().getStringProperty("store"))
							.replace("FinYr", getContext().getStringProperty("FinYr"))
							.replace("Week", getContext().getStringProperty("SelectFiscalWeek"))
							.replace("Department", getContext().getStringProperty("DepartmentToView"))
							.replace("Week", getContext().getStringProperty("SelectFiscalWeek")));
			System.out.println("DB Value: " + DBTtlColTtl);
		}

		Ttl = pageAlocationofFixHoursPO.UIResults(objAllocationofFixHoursObjects.Table, "Total Hours",
				objAllocationofFixHoursObjects.TotalMon, objAllocationofFixHoursObjects.TotalTue,
				objAllocationofFixHoursObjects.TotalWed, objAllocationofFixHoursObjects.TotalThu,
				objAllocationofFixHoursObjects.TotalFri, objAllocationofFixHoursObjects.TotalSat,
				objAllocationofFixHoursObjects.TotalSun, objAllocationofFixHoursObjects.SMSJobs);
		TtlColTtl = pageAlocationofFixHoursPO.GetTotal(extentTest, objAllocationofFixHoursObjects.SMSJobs,
				objAllocationofFixHoursObjects.TotalTtl, objAllocationofFixHoursObjects.Table, "Total Hours");

		pageDailyTradingStatementStorePO.CompareValueDecimalFields(extentTest, DBTtl, Ttl);
		pageMonthlyPlanningPO.CompareValueDecimalFields(extentTest, DBTtlColTtl, TtlColTtl);

		// Total Daily Hours
		Report_AddStep("testcase", "TOTAL DAILY HOURS", "", "", "");
		extentTest = report.startTest("Total Daily Hours");

		if (Depts.equals("5") || Depts.equals("10") || Depts.equals("15")) {
			String TotalDailyHoursLonglife = data.getText("TotalDailyHoursLonglife");
			DBTtl = sql.CLRexecuteQuery(getContext(),
					TotalDailyHoursLonglife.replace("store", getContext().getStringProperty("store"))
							.replace("FinYr", getContext().getStringProperty("FinYr"))
							.replace("FinWk", getContext().getStringProperty("FinWk"))
							.replace("Department", getContext().getStringProperty("DepartmentToView"))
							.replace("Week", getContext().getStringProperty("SelectFiscalWeek")));
			System.out.println("DB Value: " + DBTtl);

			String TotalDailyHoursLonglifeTotal = data.getText("TotalDailyHoursLonglifeTotal");
			DBTtlColTtl = sql.CLRexecuteQuery(getContext(),
					TotalDailyHoursLonglifeTotal.replace("store", getContext().getStringProperty("store"))
							.replace("FinYr", getContext().getStringProperty("FinYr"))
							.replace("FinWk", getContext().getStringProperty("FinWk"))
							.replace("Department", getContext().getStringProperty("DepartmentToView"))
							.replace("Week", getContext().getStringProperty("SelectFiscalWeek")));
			System.out.println("DB Value: " + DBTtlColTtl);
		} else {
			String TotalDailyHoursNonLonglife = data.getText("TotalDailyHoursNonLonglife");
			DBTtl = sql.CLRexecuteQuery(getContext(),
					TotalDailyHoursNonLonglife.replace("store", getContext().getStringProperty("store"))
							.replace("FinYr", getContext().getStringProperty("FinYr"))
							.replace("FinWk", getContext().getStringProperty("FinWk"))
							.replace("Department", getContext().getStringProperty("DepartmentToView"))
							.replace("Week", getContext().getStringProperty("SelectFiscalWeek")));
			System.out.println("DB Value: " + DBTtl);

			String TotalDailyHoursNonLonglifeTotal = data.getText("TotalDailyHoursNonLonglifeTotal");
			DBTtlColTtl = sql.CLRexecuteQuery(getContext(),
					TotalDailyHoursNonLonglifeTotal.replace("store", getContext().getStringProperty("store"))
							.replace("FinYr", getContext().getStringProperty("FinYr"))
							.replace("FinWk", getContext().getStringProperty("FinWk"))
							.replace("Department", getContext().getStringProperty("DepartmentToView"))
							.replace("Week", getContext().getStringProperty("SelectFiscalWeek")));
			System.out.println("DB Value: " + DBTtlColTtl);
		}

		Ttl = pageAlocationofFixHoursPO.UIResults(objAllocationofFixHoursObjects.Table, "Total Daily Hours",
				objAllocationofFixHoursObjects.TtlDlyHrsMon, objAllocationofFixHoursObjects.TtlDlyHrsTue,
				objAllocationofFixHoursObjects.TtlDlyHrsWed, objAllocationofFixHoursObjects.TtlDlyHrsThu,
				objAllocationofFixHoursObjects.TtlDlyHrsFri, objAllocationofFixHoursObjects.TtlDlyHrsSat,
				objAllocationofFixHoursObjects.TtlDlyHrsSun, objAllocationofFixHoursObjects.TtlDlyHrs);
		TtlColTtl = pageAlocationofFixHoursPO.GetTotal(extentTest, objAllocationofFixHoursObjects.TtlDlyHrs,
				objAllocationofFixHoursObjects.TtlDlyHrsTotal, objAllocationofFixHoursObjects.Table,
				"Total Daily Hours");

		pageDailyTradingStatementStorePO.CompareValueDecimalFields(extentTest, DBTtl, Ttl);
		pageMonthlyPlanningPO.CompareValueDecimalFields(extentTest, DBTtlColTtl, TtlColTtl);

	}

	@BeforeTest
	public void InitiateExtentReport() throws Exception {
		report = new ExtentReports(System.getProperty("user.dir") + "\\Reports\\AllocationofFixHours.html");
	}

	@AfterTest
	public void CloseExtentReport() throws Exception {
		report.endTest(extentTest);
		report.flush();
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
		pageMonthlyPlanningGroupPO = PageFactory.initElements(this.getDriver(), MonthlyPlanningGroupPage.class);
		pageWeeklyDepartmentPlanningLLPO = PageFactory.initElements(this.getDriver(),
				WeeklyDepartmentPlanningPage.class);
		objWeeklyDepartmentPlanningObjects = PageFactory.initElements(this.getDriver(),
				WeeklyDepartmentPlanningObjects.class);
		objAllocationofFixHoursObjects = PageFactory.initElements(this.getDriver(), AllocationofFixHoursObjects.class);
		pageDTSDepartmentPO = PageFactory.initElements(this.getDriver(), DTS_DepartmentPage.class);
		pageDailyTradingStatementStorePO = PageFactory.initElements(this.getDriver(),
				DailyTradingStatementStorePage.class);
		pageAlocationofFixHoursPO = PageFactory.initElements(this.getDriver(), AlocationofFixHoursPage.class);
		BaseObj = PageFactory.initElements(this.getDriver(), BasePage.class);
		common = PageFactory.initElements(this.getDriver(), CommonFunctions.class);
		storeprop = PageFactory.initElements(this.getDriver(), StoreProperties.class);
		objWeeklyPlanningStoreObjects = PageFactory.initElements(this.getDriver(), WeeklyPlanningStoreObjects.class);
		objDailyTradingStatementObjects = PageFactory.initElements(this.getDriver(),
				DailyTradingStatementObjects.class);
		pageMonthlyPlanningPO = PageFactory.initElements(this.getDriver(), MonthlyPlanningStorePage.class);
		objDTSDepartmentObjects = PageFactory.initElements(this.getDriver(), DTS_DepartmentObjects.class);
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
			data.loadTestDataXML(System.getProperty("user.dir") + "\\TestData\\WLP\\AllocationofFixHours.xml");
			customreport.createExtentReport();

		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

}
