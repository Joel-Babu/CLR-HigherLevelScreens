package au.wow.WLPmain.tests;

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
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

public class CopyDataToNewStore extends TestBase {
	protected Logger log = LogManager.getLogger(CopyDataToNewStore.class);
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
	List<List<Object>> DBValues, UIValues;
	String Timestamp1, Username1, dateorder1;
	// String Username1;

	public CopyDataToNewStore() {
		super.log = log;
	}

	@Test(priority = 0)
	public void LogintoCLRApplication() throws Exception {
		loginPage = new WoWLoginPage().createPage(getDriver(), getContext(), log, status, data, report, extentTest,
				WoWLoginPage.class);
		pageWeeklyPlanningPO = loginPage.navToCopyDataToNewStore(getContext().getStringProperty("store"));
	}

	// First
	@Test(description = "VariableWageRateFactor-Admin, VariableWageRateFactorValidation", priority = 1)
	public void VariableWageRateFactor() throws Exception {
		pageWeeklyPlanningPO = new WeeklyPlanningStorePage().createPage(getDriver(), getContext(), log, status, data,
				getReport(), getReportLogger(), WeeklyPlanningStorePage.class);
		pageDailyTradingStatementGroupPO = new MonthlyPlanningGroupPage().createPage(getDriver(), getContext(), log,
				status, data, getReport(), getReportLogger(), DailyTradingStatementGroupPage.class);
		TestStatus status = getTestStatus();
		System.out.println("Entering into TestCase: VariableWageRateFactor");
		pageWeeklyPlanningPO.VerifyUserName(objWeeklyPlanningStoreObjects, extentTest,
				getContext().getStringProperty("username3"));

		pageWeeklyPlanningPO.SelectPage(objWeeklyPlanningStoreObjects,
				getContext().getStringProperty("CopyDataToStore"), extentTest);

		Report_AddStep("testcase", "VariableWageRateFactor - Admin,First Validation", "", "", "");
		extentTest.log(LogStatus.INFO, "VariableWageRateFactor - Admin,First Validation");

		driver1.switchTo().defaultContent();
		driver1.switchTo().frame(driver1.findElement(By.xpath(objWeeklyPlanningStoreObjects.DataFrameTwo)));

		pageWeeklyPlanningPO.verifyDisplayedMessage(objWeeklyPlanningStoreObjects.TitleOfVariableWageRateFactor,
				extentTest, "Variable Wage Rate Factors", "Bench Mark Achieved");

		// ALL Option Selection
		if (getContext().getStringProperty("Alloption").contains("yes")) {
			pageWeeklyPlanningPO.SelectPage(objWeeklyPlanningStoreObjects, getContext().getStringProperty("All"),
					extentTest);
			pageWeeklyPlanningPO.SelectWeekFromDropdown(objWeeklyPlanningStoreObjects, extentTest,
					getContext().getStringProperty("SelectFiscalWeek"));
			pageWeeklyPlanningPO.enterValue(objWeeklyPlanningStoreObjects.PositiveRateFactor, extentTest,
					getContext().getStringProperty("PositiveRateFactor"), "PositiveRateFactorDataEntry");
			pageWeeklyPlanningPO.enterValue(objWeeklyPlanningStoreObjects.NegativeRateFactor, extentTest,
					getContext().getStringProperty("NegativeRateFactor"), "NegativeRateFactorDataEntry");
			pageWeeklyPlanningPO.clickButtonTwo(objWeeklyPlanningStoreObjects.ADDButton, extentTest, "Add");

			String ss = data.getText("VariableWageRateFactorAllQuery");
			DBValues = sql.CLRexecuteQuery(getContext(),
					ss.replace("Week", getContext().getStringProperty("SelectFiscalWeek")));
			System.out.println("DB Value: " + DBValues);

			UIValues = pageDailyTradingStatementGroupPO.UIResults(objWeeklyPlanningStoreObjects,
					objWeeklyPlanningStoreObjects.State, objWeeklyPlanningStoreObjects.FinWeek,
					objWeeklyPlanningStoreObjects.PositiveRateFactorUpdated,
					objWeeklyPlanningStoreObjects.NegativeRateFactorUpdated);

			pageWeeklyPlanningPO.CompareValuesTwo(extentTest, DBValues, UIValues);

		}
		// NSW Option Selection
		if (getContext().getStringProperty("nswoption").contains("yes")) {
			pageWeeklyPlanningPO.SelectPage(objWeeklyPlanningStoreObjects, getContext().getStringProperty("Nsw"),
					extentTest);

			pageWeeklyPlanningPO.SelectWeekFromDropdown(objWeeklyPlanningStoreObjects, extentTest,
					getContext().getStringProperty("SelectFiscalWeek"));
			pageWeeklyPlanningPO.enterValue(objWeeklyPlanningStoreObjects.PositiveRateFactor, extentTest,
					getContext().getStringProperty("PositiveRateFactor"), "PositiveRateFactorDataEntry");
			pageWeeklyPlanningPO.enterValue(objWeeklyPlanningStoreObjects.NegativeRateFactor, extentTest,
					getContext().getStringProperty("NegativeRateFactor"), "NegativeRateFactorDataEntry");
			pageWeeklyPlanningPO.clickButton(objWeeklyPlanningStoreObjects.ADDButton, extentTest, "Add");

			// Comparison Steps
			String ss = data.getText("VariableWageRateFactorQuery");
			DBValues = sql.CLRexecuteQuery(getContext(),
					ss.replace("Week", getContext().getStringProperty("SelectFiscalWeek")));
			System.out.println("DB Value: " + DBValues);

			UIValues = pageDailyTradingStatementGroupPO.UIResults(objWeeklyPlanningStoreObjects,
					objWeeklyPlanningStoreObjects.State, objWeeklyPlanningStoreObjects.FinWeek,
					objWeeklyPlanningStoreObjects.PositiveRateFactorUpdated,
					objWeeklyPlanningStoreObjects.NegativeRateFactorUpdated);

			pageWeeklyPlanningPO.CompareValues(extentTest, DBValues, UIValues);
		}
		// QLD Option Selection
		if (getContext().getStringProperty("qldoption").contains("yes")) {
			pageWeeklyPlanningPO.SelectPage(objWeeklyPlanningStoreObjects, getContext().getStringProperty("Qld"),
					extentTest);

			pageWeeklyPlanningPO.SelectWeekFromDropdown(objWeeklyPlanningStoreObjects, extentTest,
					getContext().getStringProperty("SelectFiscalWeek"));
			pageWeeklyPlanningPO.enterValue(objWeeklyPlanningStoreObjects.PositiveRateFactor, extentTest,
					getContext().getStringProperty("PositiveRateFactor"), "PositiveRateFactorDataEntry");
			pageWeeklyPlanningPO.enterValue(objWeeklyPlanningStoreObjects.NegativeRateFactor, extentTest,
					getContext().getStringProperty("NegativeRateFactor"), "NegativeRateFactorDataEntry");
			pageWeeklyPlanningPO.clickButton(objWeeklyPlanningStoreObjects.ADDButton, extentTest, "Add");

			// Comparison Steps
			String ss = data.getText("VariableWageRateFactorQuery");
			DBValues = sql.CLRexecuteQuery(getContext(),
					ss.replace("Week", getContext().getStringProperty("SelectFiscalWeek")));
			System.out.println("DB Value: " + DBValues);

			UIValues = pageDailyTradingStatementGroupPO.UIResults(objWeeklyPlanningStoreObjects,
					objWeeklyPlanningStoreObjects.State, objWeeklyPlanningStoreObjects.FinWeek,
					objWeeklyPlanningStoreObjects.PositiveRateFactorUpdated,
					objWeeklyPlanningStoreObjects.NegativeRateFactorUpdated);

			pageWeeklyPlanningPO.CompareValues(extentTest, DBValues, UIValues);
		}

		// SA Option Selection
		if (getContext().getStringProperty("saoption").contains("yes")) {
			pageWeeklyPlanningPO.SelectPage(objWeeklyPlanningStoreObjects, getContext().getStringProperty("Sa"),
					extentTest);

			pageWeeklyPlanningPO.SelectWeekFromDropdown(objWeeklyPlanningStoreObjects, extentTest,
					getContext().getStringProperty("SelectFiscalWeek"));
			pageWeeklyPlanningPO.enterValue(objWeeklyPlanningStoreObjects.PositiveRateFactor, extentTest,
					getContext().getStringProperty("PositiveRateFactor"), "PositiveRateFactorDataEntry");
			pageWeeklyPlanningPO.enterValue(objWeeklyPlanningStoreObjects.NegativeRateFactor, extentTest,
					getContext().getStringProperty("NegativeRateFactor"), "NegativeRateFactorDataEntry");
			pageWeeklyPlanningPO.clickButton(objWeeklyPlanningStoreObjects.ADDButton, extentTest, "Add");

			// Comparison Steps
			String ss = data.getText("VariableWageRateFactorQuery");
			DBValues = sql.CLRexecuteQuery(getContext(),
					ss.replace("Week", getContext().getStringProperty("SelectFiscalWeek")));
			System.out.println("DB Value: " + DBValues);

			UIValues = pageDailyTradingStatementGroupPO.UIResults(objWeeklyPlanningStoreObjects,
					objWeeklyPlanningStoreObjects.State, objWeeklyPlanningStoreObjects.FinWeek,
					objWeeklyPlanningStoreObjects.PositiveRateFactorUpdated,
					objWeeklyPlanningStoreObjects.NegativeRateFactorUpdated);

			pageWeeklyPlanningPO.CompareValues(extentTest, DBValues, UIValues);
		}

		// VIC Option Selection
		if (getContext().getStringProperty("vicoption").contains("yes")) {
			pageWeeklyPlanningPO.SelectPage(objWeeklyPlanningStoreObjects, getContext().getStringProperty("Vic"),
					extentTest);

			pageWeeklyPlanningPO.SelectWeekFromDropdown(objWeeklyPlanningStoreObjects, extentTest,
					getContext().getStringProperty("SelectFiscalWeek"));
			pageWeeklyPlanningPO.enterValue(objWeeklyPlanningStoreObjects.PositiveRateFactor, extentTest,
					getContext().getStringProperty("PositiveRateFactor"), "PositiveRateFactorDataEntry");
			pageWeeklyPlanningPO.enterValue(objWeeklyPlanningStoreObjects.NegativeRateFactor, extentTest,
					getContext().getStringProperty("NegativeRateFactor"), "NegativeRateFactorDataEntry");
			pageWeeklyPlanningPO.clickButton(objWeeklyPlanningStoreObjects.ADDButton, extentTest, "Add");

			// Comparison Steps
			String ss = data.getText("VariableWageRateFactorQuery");
			DBValues = sql.CLRexecuteQuery(getContext(),
					ss.replace("Week", getContext().getStringProperty("SelectFiscalWeek")));
			System.out.println("DB Value: " + DBValues);

			UIValues = pageDailyTradingStatementGroupPO.UIResults(objWeeklyPlanningStoreObjects,
					objWeeklyPlanningStoreObjects.State, objWeeklyPlanningStoreObjects.FinWeek,
					objWeeklyPlanningStoreObjects.PositiveRateFactorUpdated,
					objWeeklyPlanningStoreObjects.NegativeRateFactorUpdated);

			pageWeeklyPlanningPO.CompareValues(extentTest, DBValues, UIValues);
		}

		// WA Option Selection
		if (getContext().getStringProperty("waoption").contains("yes")) {
			pageWeeklyPlanningPO.SelectPage(objWeeklyPlanningStoreObjects, getContext().getStringProperty("Wa"),
					extentTest);

			pageWeeklyPlanningPO.SelectWeekFromDropdown(objWeeklyPlanningStoreObjects, extentTest,
					getContext().getStringProperty("SelectFiscalWeek"));
			pageWeeklyPlanningPO.enterValue(objWeeklyPlanningStoreObjects.PositiveRateFactor, extentTest,
					getContext().getStringProperty("PositiveRateFactor"), "PositiveRateFactorDataEntry");
			pageWeeklyPlanningPO.enterValue(objWeeklyPlanningStoreObjects.NegativeRateFactor, extentTest,
					getContext().getStringProperty("NegativeRateFactor"), "NegativeRateFactorDataEntry");
			pageWeeklyPlanningPO.clickButton(objWeeklyPlanningStoreObjects.ADDButton, extentTest, "Add");

			// Comparison Steps
			String ss = data.getText("VariableWageRateFactorQuery");
			DBValues = sql.CLRexecuteQuery(getContext(),
					ss.replace("Week", getContext().getStringProperty("SelectFiscalWeek")));
			System.out.println("DB Value: " + DBValues);

			UIValues = pageDailyTradingStatementGroupPO.UIResults(objWeeklyPlanningStoreObjects,
					objWeeklyPlanningStoreObjects.State, objWeeklyPlanningStoreObjects.FinWeek,
					objWeeklyPlanningStoreObjects.PositiveRateFactorUpdated,
					objWeeklyPlanningStoreObjects.NegativeRateFactorUpdated);

			pageWeeklyPlanningPO.CompareValues(extentTest, DBValues, UIValues);
		}

	}

	/*
	 * @Test(description =
	 * "EditablePlannedItemPage-Admin, EditablePlannedItemPageValidation",priority=
	 * 2) public void EditablePlannedItemPriceToggle() throws Exception {
	 * pageWeeklyPlanningPO = new WeeklyPlanningStorePage().createPage(getDriver(),
	 * getContext(), log, status, data,getReport(), getReportLogger(),
	 * WeeklyPlanningStorePage.class); pageDailyTradingStatementGroupPO = new
	 * MonthlyPlanningGroupPage().createPage(getDriver(), getContext(), log, status,
	 * data,getReport(), getReportLogger(), DailyTradingStatementGroupPage.class);
	 * TestStatus status = getTestStatus();
	 * System.out.println("Entering into TestCase: VariableWageRateFactor");
	 * pageWeeklyPlanningPO.VerifyUserName(objWeeklyPlanningStoreObjects,extentTest,
	 * getContext().getStringProperty("username3"));
	 * 
	 * pageWeeklyPlanningPO.SelectPage(objWeeklyPlanningStoreObjects,getContext().
	 * getStringProperty("CopyDataToStore"),extentTest);
	 * 
	 * Report_AddStep("testcase","EditablePlannedItemPage-Admin,First Validation"
	 * ,"","", ""); extentTest.log(LogStatus.INFO,
	 * "EditablePlannedItemPage-Admin,First Validation");
	 * 
	 * pageWeeklyPlanningPO.verifyDisplayedMessage(objWeeklyPlanningStoreObjects.
	 * TitleOfEditablePlannedItemPrice, extentTest,
	 * "Editable Planned Item Price Toggle", "Title");
	 * pageWeeklyPlanningPO.verifyDisplayedMessage(objWeeklyPlanningStoreObjects.
	 * NSWText, extentTest, "NSW", "NSW Content");
	 * pageWeeklyPlanningPO.verifyDisplayedMessage(objWeeklyPlanningStoreObjects.
	 * QLDText, extentTest, "QLD", "QLD Content");
	 * pageWeeklyPlanningPO.verifyDisplayedMessage(objWeeklyPlanningStoreObjects.
	 * SAText, extentTest, "SA", "SA Content");
	 * pageWeeklyPlanningPO.verifyDisplayedMessage(objWeeklyPlanningStoreObjects.
	 * VICText, extentTest, "VIC", "VIC Content");
	 * pageWeeklyPlanningPO.verifyDisplayedMessage(objWeeklyPlanningStoreObjects.
	 * WAText, extentTest, "WA", "WA Content");
	 * pageWeeklyPlanningPO.verifyDisplayedMessage(objWeeklyPlanningStoreObjects.
	 * SaveButtonText, extentTest, "SAVE", "Save Button");
	 * pageWeeklyPlanningPO.verifyDisplayedMessage(objWeeklyPlanningStoreObjects.
	 * CheckboxoperationInstruction, extentTest,
	 * "Check the box to edit Planned Item Price",
	 * "Instruction for check box operation"); //CheckBox Selection
	 * if(getContext().getStringProperty("CheckBoxSelection").contains("yes")) {
	 * if(getContext().getStringProperty("NSW").contains("yes")) {
	 * pageWeeklyPlanningPO.clickButton(objWeeklyPlanningStoreObjects.NSWCheckBox,
	 * extentTest, "NSWCheckBox"); } else
	 * if(getContext().getStringProperty("QLD").contains("yes")) {
	 * pageWeeklyPlanningPO.clickButton(objWeeklyPlanningStoreObjects.QldCheckBox,
	 * extentTest, "QldCheckBox"); } else
	 * if(getContext().getStringProperty("SA").contains("yes")) {
	 * pageWeeklyPlanningPO.clickButton(objWeeklyPlanningStoreObjects.SACheckBox,
	 * extentTest, "SACheckBox"); } else
	 * if(getContext().getStringProperty("VIC").contains("yes")) {
	 * pageWeeklyPlanningPO.clickButton(objWeeklyPlanningStoreObjects.VICCheckBox,
	 * extentTest, "VICCheckBox"); } else
	 * if(getContext().getStringProperty("WA").contains("yes")) {
	 * pageWeeklyPlanningPO.clickButton(objWeeklyPlanningStoreObjects.WACheckBox,
	 * extentTest, "WACheckBox"); }
	 * pageWeeklyPlanningPO.clickButton(objWeeklyPlanningStoreObjects.SaveButton,
	 * extentTest, "SaveButton");
	 * pageWeeklyPlanningPO.verifyDisplayedMessage(objWeeklyPlanningStoreObjects.
	 * SuccessMessageAfterCheckBoxSelection, extentTest,
	 * "Your changes have been completed successfully.",
	 * "SuccessMessageAfterCheckBoxSelection"); String
	 * name=getContext().getStringProperty("username3"); DateFormat dateFormat = new
	 * SimpleDateFormat("dd/MM/yyyy "); Date date = new Date(); String date1=
	 * dateFormat.format(date); System.out.println(date1); String now = new
	 * SimpleDateFormat("hh:mm:ss aa").format(new java.util.Date().getTime());
	 * System.out.println("time in 12 hour format : " + now); SimpleDateFormat
	 * inFormat = new SimpleDateFormat("hh:mm:ss aa"); SimpleDateFormat outFormat =
	 * new SimpleDateFormat("HH:mm:ss"); String time24 =
	 * outFormat.format(inFormat.parse(now));
	 * System.out.println("time in 24 hour format : " + time24);
	 * pageWeeklyPlanningPO.verifyDisplayedMessageForTimeandDate(
	 * objWeeklyPlanningStoreObjects.SuccessMessageWithDateAndTime, extentTest,
	 * "Updated by" +name+ "at" +time24+ "on" +date1,
	 * "SuccessMessageWithDateAndTime"); } else {
	 * pageWeeklyPlanningPO.clickButton(objWeeklyPlanningStoreObjects.SaveButton,
	 * extentTest, "SaveButton");
	 * pageWeeklyPlanningPO.verifyDisplayedMessage(objWeeklyPlanningStoreObjects.
	 * NoChangesMadeErrorMessage, extentTest, "No changes have been made.",
	 * "NoChangesMadeErrorMessage"); }
	 * 
	 * 
	 * 
	 * }
	 */

	@Test(description = "EditablePlannedItemPage-Admin, EditablePlannedItemPageValidation", priority = 2)
	public void EditablePlannedItemPriceToggle() throws Exception {
		pageWeeklyPlanningPO = new WeeklyPlanningStorePage().createPage(getDriver(), getContext(), log, status, data,
				getReport(), getReportLogger(), WeeklyPlanningStorePage.class);
		pageDailyTradingStatementGroupPO = new MonthlyPlanningGroupPage().createPage(getDriver(), getContext(), log,
				status, data, getReport(), getReportLogger(), DailyTradingStatementGroupPage.class);
		TestStatus status = getTestStatus();
		System.out.println("Entering into TestCase: VariableWageRateFactor");
		pageWeeklyPlanningPO.VerifyUserName(objWeeklyPlanningStoreObjects, extentTest,
				getContext().getStringProperty("username3"));

		pageWeeklyPlanningPO.SelectPage(objWeeklyPlanningStoreObjects,
				getContext().getStringProperty("CopyDataToStore"), extentTest);

		Report_AddStep("testcase", "EditablePlannedItemPage-Admin,First Validation", "", "", "");
		extentTest.log(LogStatus.INFO, "EditablePlannedItemPage-Admin,First Validation");

		driver1.switchTo().defaultContent();
		driver1.switchTo().frame(driver1.findElement(By.xpath(objWeeklyPlanningStoreObjects.DataFrameBottom)));

		pageWeeklyPlanningPO.verifyDisplayedMessage(objWeeklyPlanningStoreObjects.TitleOfEditablePlannedItemPrice,
				extentTest, "Editable Planned Item Price Toggle", "Title");
		pageWeeklyPlanningPO.verifyDisplayedMessage(objWeeklyPlanningStoreObjects.NSWText, extentTest, "NSW",
				"NSW Content");
		pageWeeklyPlanningPO.verifyDisplayedMessage(objWeeklyPlanningStoreObjects.QLDText, extentTest, "QLD",
				"QLD Content");
		pageWeeklyPlanningPO.verifyDisplayedMessage(objWeeklyPlanningStoreObjects.SAText, extentTest, "SA",
				"SA Content");
		pageWeeklyPlanningPO.verifyDisplayedMessage(objWeeklyPlanningStoreObjects.VICText, extentTest, "VIC",
				"VIC Content");
		pageWeeklyPlanningPO.verifyDisplayedMessage(objWeeklyPlanningStoreObjects.WAText, extentTest, "WA",
				"WA Content");
		pageWeeklyPlanningPO.verifyDisplayedMessage(objWeeklyPlanningStoreObjects.SaveButtonText, extentTest, "SAVE",
				"Save Button");
		pageWeeklyPlanningPO.verifyDisplayedMessage(objWeeklyPlanningStoreObjects.CheckboxoperationInstruction,
				extentTest, "Check the box to edit Planned Item Price", "Instruction for check box operation");
		// CheckBox Selection
		if (getContext().getStringProperty("CheckBoxSelection").contains("yes")) {
			if (getContext().getStringProperty("NSW").contains("yes")) {
				pageWeeklyPlanningPO.clickButton(objWeeklyPlanningStoreObjects.NSWCheckBox, extentTest, "NSWCheckBox");
			}
			if (getContext().getStringProperty("QLD").contains("yes")) {
				pageWeeklyPlanningPO.clickButton(objWeeklyPlanningStoreObjects.QldCheckBox, extentTest, "QldCheckBox");
			}
			if (getContext().getStringProperty("SA").contains("yes")) {
				pageWeeklyPlanningPO.clickButton(objWeeklyPlanningStoreObjects.SACheckBox, extentTest, "SACheckBox");
			}
			if (getContext().getStringProperty("VIC").contains("yes")) {
				pageWeeklyPlanningPO.clickButton(objWeeklyPlanningStoreObjects.VICCheckBox, extentTest, "VICCheckBox");
			}
			if (getContext().getStringProperty("WA").contains("yes")) {
				pageWeeklyPlanningPO.clickButton(objWeeklyPlanningStoreObjects.WACheckBox, extentTest, "WACheckBox");
			}
			pageWeeklyPlanningPO.clickButton(objWeeklyPlanningStoreObjects.SaveButton, extentTest, "SaveButton");
			pageWeeklyPlanningPO.verifyDisplayedMessage(
					objWeeklyPlanningStoreObjects.SuccessMessageAfterCheckBoxSelection, extentTest,
					"Your changes have been completed successfully.", "SuccessMessageAfterCheckBoxSelection");
			String name = getContext().getStringProperty("username3");
			DateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy ");
			Date date = new Date();
			String date1 = dateFormat.format(date);
			System.out.println(date1);
			String now = new SimpleDateFormat("hh:mm:ss aa").format(new java.util.Date().getTime());
			System.out.println("time in 12 hour format : " + now);
			SimpleDateFormat inFormat = new SimpleDateFormat("hh:mm:ss aa");
			SimpleDateFormat outFormat = new SimpleDateFormat("HH:mm:ss");
			String time24 = outFormat.format(inFormat.parse(now));
			System.out.println("time in 24 hour format : " + time24);
			/*
			 * String Username = data.getText("PlanItemPriceToggleuserid");
			 * 
			 * Username1 = sql.CLRexecuteQueryw(getContext(), Username);
			 * System.out.println("DB Value: "+Username1);
			 * 
			 * String Timestamp = data.getText("PlanItemPriceToggleTime"); Timestamp1 =
			 * sql.CLRexecuteQueryw(getContext(), Timestamp);
			 * System.out.println("DB Value: "+Timestamp1);
			 * 
			 * String dateorder = data.getText("PlanItemPriceToggledate"); dateorder1 =
			 * sql.CLRexecuteQueryw(getContext(), dateorder);
			 * System.out.println("DB Value: "+dateorder1);
			 * 
			 * pageWeeklyPlanningPO.verifyDisplayedMessageForTimeandDate(
			 * objWeeklyPlanningStoreObjects.SuccessMessageWithDateAndTime, extentTest,
			 * "Updated by" +Username1+ "at" +Timestamp1+ "on" +dateorder1,
			 * "SuccessMessageWithDateAndTime");
			 */

			pageWeeklyPlanningPO.verifyDisplayedMessageForTimeandDate(
					objWeeklyPlanningStoreObjects.SuccessMessageWithDateAndTime, extentTest,
					"Updated by" + " " + name + " " + "at" + " " + time24 + " " + "on" + " " + date1,
					"SuccessMessageWithDateAndTime");
		}

		else {
			pageWeeklyPlanningPO.clickButton(objWeeklyPlanningStoreObjects.SaveButton, extentTest, "SaveButton");
			pageWeeklyPlanningPO.verifyDisplayedMessage(objWeeklyPlanningStoreObjects.NoChangesMadeErrorMessage,
					extentTest, "No changes have been made.", "NoChangesMadeErrorMessage");
		}

	}

	/*
	 * @Test(description =
	 * "EditablePlannedItemPage-Admin, EditablePlannedItemPageValidation",priority=
	 * 2) public void Wee() throws Exception { pageWeeklyPlanningPO = new
	 * WeeklyPlanningStorePage().createPage(getDriver(), getContext(), log, status,
	 * data,getReport(), getReportLogger(), WeeklyPlanningStorePage.class);
	 * pageDailyTradingStatementGroupPO = new
	 * MonthlyPlanningGroupPage().createPage(getDriver(), getContext(), log, status,
	 * data,getReport(), getReportLogger(), DailyTradingStatementGroupPage.class);
	 * TestStatus status = getTestStatus();
	 * System.out.println("Entering into TestCase: VariableWageRateFactor");
	 * pageWeeklyPlanningPO.VerifyUserName(objWeeklyPlanningStoreObjects,extentTest,
	 * getContext().getStringProperty("username3"));
	 * 
	 * pageWeeklyPlanningPO.SelectPage(objWeeklyPlanningStoreObjects,getContext().
	 * getStringProperty("CopyDataToStore"),extentTest);
	 * 
	 * Report_AddStep("testcase","EditablePlannedItemPage-Admin,First Validation"
	 * ,"","", ""); extentTest.log(LogStatus.INFO,
	 * "EditablePlannedItemPage-Admin,First Validation");
	 * 
	 * pageWeeklyPlanningPO.verifyDisplayedMessage(objWeeklyPlanningStoreObjects.
	 * TitleOfEditablePlannedItemPrice, extentTest,
	 * "Editable Planned Item Price Toggle", "Title");
	 * pageWeeklyPlanningPO.verifyDisplayedMessage(objWeeklyPlanningStoreObjects.
	 * NSWText, extentTest, "NSW", "NSW Content");
	 * pageWeeklyPlanningPO.verifyDisplayedMessage(objWeeklyPlanningStoreObjects.
	 * QLDText, extentTest, "QLD", "QLD Content");
	 * pageWeeklyPlanningPO.verifyDisplayedMessage(objWeeklyPlanningStoreObjects.
	 * SAText, extentTest, "SA", "SA Content");
	 * pageWeeklyPlanningPO.verifyDisplayedMessage(objWeeklyPlanningStoreObjects.
	 * VICText, extentTest, "VIC", "VIC Content");
	 * pageWeeklyPlanningPO.verifyDisplayedMessage(objWeeklyPlanningStoreObjects.
	 * WAText, extentTest, "WA", "WA Content");
	 * pageWeeklyPlanningPO.verifyDisplayedMessage(objWeeklyPlanningStoreObjects.
	 * SaveButtonText, extentTest, "SAVE", "Save Button");
	 * pageWeeklyPlanningPO.verifyDisplayedMessage(objWeeklyPlanningStoreObjects.
	 * CheckboxoperationInstruction, extentTest,
	 * "Check the box to edit Planned Item Price",
	 * "Instruction for check box operation"); //CheckBox Selection
	 * if(getContext().getStringProperty("CheckBoxSelection").contains("yes")) {
	 * if(getContext().getStringProperty("NSW").contains("yes")) {
	 * pageWeeklyPlanningPO.clickButton(objWeeklyPlanningStoreObjects.NSWCheckBox,
	 * extentTest, "NSWCheckBox"); } else
	 * if(getContext().getStringProperty("QLD").contains("yes")) {
	 * pageWeeklyPlanningPO.clickButton(objWeeklyPlanningStoreObjects.QldCheckBox,
	 * extentTest, "QldCheckBox"); } else
	 * if(getContext().getStringProperty("SA").contains("yes")) {
	 * pageWeeklyPlanningPO.clickButton(objWeeklyPlanningStoreObjects.SACheckBox,
	 * extentTest, "SACheckBox"); } else
	 * if(getContext().getStringProperty("VIC").contains("yes")) {
	 * pageWeeklyPlanningPO.clickButton(objWeeklyPlanningStoreObjects.VICCheckBox,
	 * extentTest, "VICCheckBox"); } else
	 * if(getContext().getStringProperty("WA").contains("yes")) {
	 * pageWeeklyPlanningPO.clickButton(objWeeklyPlanningStoreObjects.WACheckBox,
	 * extentTest, "WACheckBox"); }
	 * pageWeeklyPlanningPO.clickButton(objWeeklyPlanningStoreObjects.SaveButton,
	 * extentTest, "SaveButton");
	 * pageWeeklyPlanningPO.verifyDisplayedMessage(objWeeklyPlanningStoreObjects.
	 * SuccessMessageAfterCheckBoxSelection, extentTest,
	 * "Your changes have been completed successfully.",
	 * "SuccessMessageAfterCheckBoxSelection"); String
	 * name=getContext().getStringProperty("username3"); DateFormat dateFormat = new
	 * SimpleDateFormat("dd/MM/yyyy "); Date date = new Date(); String date1=
	 * dateFormat.format(date); System.out.println(date1); String now = new
	 * SimpleDateFormat("hh:mm:ss aa").format(new java.util.Date().getTime());
	 * System.out.println("time in 12 hour format : " + now); SimpleDateFormat
	 * inFormat = new SimpleDateFormat("hh:mm:ss aa"); SimpleDateFormat outFormat =
	 * new SimpleDateFormat("HH:mm:ss"); String time24 =
	 * outFormat.format(inFormat.parse(now));
	 * System.out.println("time in 24 hour format : " + time24); String Username =
	 * data.getText("PlanItemPriceToggleuserid"); String Timestamp =
	 * data.getText("PlanItemPriceToggleTime"); String dateorder =
	 * data.getText("PlanItemPriceToggledate");
	 * 
	 * pageWeeklyPlanningPO.verifyDisplayedMessageForTimeandDate(
	 * objWeeklyPlanningStoreObjects.SuccessMessageWithDateAndTime, extentTest,
	 * "Updated by" +Username+ "at" +Timestamp+ "on" +dateorder,
	 * "SuccessMessageWithDateAndTime"); } else {
	 * pageWeeklyPlanningPO.clickButton(objWeeklyPlanningStoreObjects.SaveButton,
	 * extentTest, "SaveButton");
	 * pageWeeklyPlanningPO.verifyDisplayedMessage(objWeeklyPlanningStoreObjects.
	 * NoChangesMadeErrorMessage, extentTest, "No changes have been made.",
	 * "NoChangesMadeErrorMessage"); }
	 * 
	 * 
	 * 
	 * }
	 */
	@Test(priority = 5)
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
			data.loadTestDataXML(System.getProperty("user.dir") + "\\TestData\\WLP\\Admin.xml");
			customreport.createExtentReport();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

}