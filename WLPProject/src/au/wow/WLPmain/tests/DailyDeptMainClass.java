package au.wow.WLPmain.tests;

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
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.Select;
import org.testng.annotations.Test;

import com.relevantcodes.extentreports.ExtentReports;

import au.wow.WLPmain.objects.DailyPlanningMainObjects;
import au.wow.WLPmain.pages.CommonFunctions;
import au.wow.WLPmain.pages.DailyPlanningMainPage;
import au.wow.WLPmain.pages.WoWLoginPage;
import au.wow.wlp.utils.BasePage;
import au.wow.wlp.utils.CustomExtentReports;
import au.wow.wlp.utils.ExtentReportsScreenshotCode;
import au.wow.wlp.utils.SQLWrapper;
import au.wow.wlp.utils.TestBase;
import au.wow.wlp.utils.TestStatus;
import au.wow.wlp.utils.XMLDataReader;

public class DailyDeptMainClass extends TestBase {

	private Logger log = LogManager.getLogger(DailyDeptMainClass.class);
	TestStatus status;
	SQLWrapper sql = new SQLWrapper(log);
	ExtentReportsScreenshotCode ScreenShotUtil;
	WoWLoginPage loginPage;
	DailyPlanningMainPage pageDailyItemPrice;
	DailyPlanningMainPage pageDailyCustomers;
	DailyPlanningMainPage pageDailyItems;
	DailyPlanningMainPage pageDailySales;
	DailyPlanningMainPage pageDailyPaidHours;
	DailyPlanningMainPage pageDailyWages;
	// ArticleDetailsPage pageartDetails;
	DailyPlanningMainPage pageartDetails;
	DailyPlanningMainObjects objDailyItemPrice;
	DailyPlanningMainObjects objDailyCustomers;
	DailyPlanningMainObjects objDailyItems;
	DailyPlanningMainObjects objDailySales;
	DailyPlanningMainObjects objDailyPaidHours;
	DailyPlanningMainObjects objDailyWages;
	BasePage BaseObj;
	CommonFunctions common;
	StoreProperties storeprop;

	public DailyDeptMainClass() {
		super.log = log;
	}

	/**
	 * AL_1 Verifying Department Hierarchy and Paginations
	 * 
	 * @throws Exception
	 */
	@Test(description = "Daily Planning Item Price")
	public void ItemPrice() throws Exception {

		TestStatus status = getTestStatus();
		System.out.println("Testing");

		navToDailyDeptPlanItemPrice(status);
		driver1.switchTo().frame(driver1.findElement(By.xpath("//html//frameset//frame[1]")));
		selectByValue(objDailyItemPrice.dropdonw, "8");
		click(objDailyItemPrice.linkGo);

		waitFor(2);
		driver1.switchTo().defaultContent();
		driver1.switchTo().frame("BOTTOM");

		selectByValue(objDailyItemPrice.dailyplan, "Item Price");
		waitFor(2);
		driver1.switchTo().defaultContent();

		driver1.switchTo().frame(driver1.findElement(By.xpath("//html//frameset//frame[2]")));
		waitFor(2);

		pageDailyItemPrice.fnCheckOutItemPriceTotal(objDailyItemPrice, extentTest);

		driver1.switchTo().defaultContent();
		status.assertAll();
	}

	public void navToDailyDeptPlanItemPrice(TestStatus status) throws Exception {
		log.info("---------------Login into Application--------------------");
		Report_AddStep("testcase", "---------------Login into Application--------------------", "", "", "Pass");
		loginPage = new WoWLoginPage().createPage(getDriver(), getContext(), log, status, data, getReport(),
				getReportLogger(), WoWLoginPage.class);
		Report_AddStep("testcase", "Login with the Store : " + getContext().getStringProperty("store"), "", "", "Pass");
		pageDailyItemPrice = loginPage.navToDailyDept(getContext().getStringProperty("store"));
		// log.info("-------------Navigate to Order Roster Report Page------------");
		// Report_AddStep("testcase", "---------------Navigate to Order Roster Report
		// Page--------------------","","", "Pass");
		// orderReportPage.clickOnOrderRoasterReport(objOrderRoaster);
	}

	@Test(description = "Daily Planning Customers")
	public void Customers() throws Exception {

		TestStatus status = getTestStatus();
		System.out.println("Testing");
		/*
		 * driver1.findElement(By.
		 * xpath("//input[@class = 'clsLabel' and @id ='form1:text1']")).sendKeys(
		 * "1412store"); driver1.findElement(By.
		 * xpath("//input[@class = 'clsLabel' and @id ='form1:secret1']")).sendKeys(
		 * "east1234");
		 */

		navToDailyDeprtmntCustomers(status);
		driver1.switchTo().frame(driver1.findElement(By.xpath("//html//frameset//frame[1]")));
		selectByValue(objDailyCustomers.dropdonw, "8");
		click(objDailyCustomers.linkGo);

		waitFor(2);
		driver1.switchTo().defaultContent();
		driver1.switchTo().frame("BOTTOM");

		selectByValue(objDailyCustomers.dailyplan, "Customers");
		waitFor(2);
		driver1.switchTo().defaultContent();

		driver1.switchTo().frame(driver1.findElement(By.xpath("//html//frameset//frame[2]")));
		waitFor(2);

		pageDailyCustomers.fnlCheckOutCustomersTotal(objDailyCustomers, extentTest);

		driver1.switchTo().defaultContent();
		status.assertAll();
	}

	public void navToDailyDeprtmntCustomers(TestStatus status) throws Exception {
		log.info("---------------Login into Application--------------------");
		Report_AddStep("testcase", "---------------Login into Application--------------------", "", "", "Pass");
		loginPage = new WoWLoginPage().createPage(getDriver(), getContext(), log, status, data, report, extentTest,
				WoWLoginPage.class);
		Report_AddStep("testcase", "Login with the Store : " + getContext().getStringProperty("store"), "", "", "Pass");
		pageDailyCustomers = loginPage.navToDailyDept(getContext().getStringProperty("store"));
		// log.info("-------------Navigate to Order Roster Report Page------------");
		// Report_AddStep("testcase", "---------------Navigate to Order Roster Report
		// Page--------------------","","", "Pass");
		// orderReportPage.clickOnOrderRoasterReport(objOrderRoaster);
	}

	@Test(description = "Daily Planning Items")
	public void Items() throws Exception {

		TestStatus status = getTestStatus();
		System.out.println("Testing");
		/*
		 * driver1.findElement(By.
		 * xpath("//input[@class = 'clsLabel' and @id ='form1:text1']")).sendKeys(
		 * "1412store"); driver1.findElement(By.
		 * xpath("//input[@class = 'clsLabel' and @id ='form1:secret1']")).sendKeys(
		 * "east1234");
		 */

		navToDailyDeprtmntItems(status);
		driver1.switchTo().frame(driver1.findElement(By.xpath("//html//frameset//frame[1]")));
		selectByValue(objDailyItems.dropdonw, "8");
		click(objDailyItems.linkGo);

		waitFor(2);
		driver1.switchTo().defaultContent();
		driver1.switchTo().frame("BOTTOM");

		selectByValue(objDailyItems.dailyplan, "Items");
		waitFor(2);
		driver1.switchTo().defaultContent();

		driver1.switchTo().frame(driver1.findElement(By.xpath("//html//frameset//frame[2]")));
		waitFor(2);

		pageDailyItems.fnlCheckOutItemsTotal(objDailyItems, extentTest);

		driver1.switchTo().defaultContent();
		status.assertAll();
	}

	public void navToDailyDeprtmntItems(TestStatus status) throws Exception {
		log.info("---------------Login into Application--------------------");
		Report_AddStep("testcase", "---------------Login into Application--------------------", "", "", "Pass");
		loginPage = new WoWLoginPage().createPage(getDriver(), getContext(), log, status, data, report, extentTest,
				WoWLoginPage.class);
		Report_AddStep("testcase", "Login with the Store : " + getContext().getStringProperty("store"), "", "", "Pass");
		pageDailyItems = loginPage.navToDailyDept(getContext().getStringProperty("store"));
		// log.info("-------------Navigate to Order Roster Report Page------------");
		// Report_AddStep("testcase", "---------------Navigate to Order Roster Report
		// Page--------------------","","", "Pass");
		// orderReportPage.clickOnOrderRoasterReport(objOrderRoaster);
	}

	@Test(description = "Daily Planning Sales")
	public void Sales() throws Exception {

		TestStatus status = getTestStatus();
		System.out.println("Testing");
		/*
		 * driver1.findElement(By.
		 * xpath("//input[@class = 'clsLabel' and @id ='form1:text1']")).sendKeys(
		 * "1412store"); driver1.findElement(By.
		 * xpath("//input[@class = 'clsLabel' and @id ='form1:secret1']")).sendKeys(
		 * "east1234");
		 */

		navToDailyDeprtmntSales(status);
		driver1.switchTo().frame(driver1.findElement(By.xpath("//html//frameset//frame[1]")));
		selectByValue(objDailySales.dropdonw, "8");
		click(objDailySales.linkGo);

		waitFor(2);
		driver1.switchTo().defaultContent();
		driver1.switchTo().frame("BOTTOM");

		selectByValue(objDailySales.dailyplan, "Sales");
		waitFor(2);
		driver1.switchTo().defaultContent();

		driver1.switchTo().frame(driver1.findElement(By.xpath("//html//frameset//frame[2]")));
		waitFor(2);

		pageDailySales.fnlCheckOutCustomersTotal(objDailySales, extentTest);

		driver1.switchTo().defaultContent();
		status.assertAll();
	}

	public void navToDailyDeprtmntSales(TestStatus status) throws Exception {
		log.info("---------------Login into Application--------------------");
		Report_AddStep("testcase", "---------------Login into Application--------------------", "", "", "Pass");
		loginPage = new WoWLoginPage().createPage(getDriver(), getContext(), log, status, data, report, extentTest,
				WoWLoginPage.class);
		Report_AddStep("testcase", "Login with the Store : " + getContext().getStringProperty("store"), "", "", "Pass");
		pageDailySales = loginPage.navToDailyDept(getContext().getStringProperty("store"));
		// log.info("-------------Navigate to Order Roster Report Page------------");
		// Report_AddStep("testcase", "---------------Navigate to Order Roster Report
		// Page--------------------","","", "Pass");
		// orderReportPage.clickOnOrderRoasterReport(objOrderRoaster);
	}

	@Test(description = "Daily Planning Paid Hours")
	public void PaidHours() throws Exception {

		TestStatus status = getTestStatus();
		System.out.println("Testing");
		/*
		 * driver1.findElement(By.
		 * xpath("//input[@class = 'clsLabel' and @id ='form1:text1']")).sendKeys(
		 * "1412store"); driver1.findElement(By.
		 * xpath("//input[@class = 'clsLabel' and @id ='form1:secret1']")).sendKeys(
		 * "east1234");
		 */

		navToDailyDeprtmntPaidHours(status);
		driver1.switchTo().frame(driver1.findElement(By.xpath("//html//frameset//frame[1]")));
		selectByValue(objDailyPaidHours.dropdonw, "8");
		click(objDailyPaidHours.linkGo);

		waitFor(2);
		driver1.switchTo().defaultContent();
		driver1.switchTo().frame("BOTTOM");

		selectByValue(objDailyPaidHours.dailyplan, "Paid Hours");
		waitFor(2);
		driver1.switchTo().defaultContent();

		driver1.switchTo().frame(driver1.findElement(By.xpath("//html//frameset//frame[2]")));
		waitFor(2);

		pageDailyPaidHours.fnlPaidHoursTotal(objDailyPaidHours, extentTest);

		driver1.switchTo().defaultContent();
		status.assertAll();
	}

	public void navToDailyDeprtmntPaidHours(TestStatus status) throws Exception {
		log.info("---------------Login into Application--------------------");
		Report_AddStep("testcase", "---------------Login into Application--------------------", "", "", "Pass");
		loginPage = new WoWLoginPage().createPage(getDriver(), getContext(), log, status, data, report, extentTest,
				WoWLoginPage.class);
		Report_AddStep("testcase", "Login with the Store : " + getContext().getStringProperty("store"), "", "", "Pass");
		pageDailyPaidHours = loginPage.navToDailyDept(getContext().getStringProperty("store"));

		// log.info("-------------Navigate to Order Roster Report Page------------");
		// Report_AddStep("testcase", "---------------Navigate to Order Roster Report
		// Page--------------------","","", "Pass");
		// orderReportPage.clickOnOrderRoasterReport(objOrderRoaster);
	}

	@Test(description = "Daily Planning Wages")
	public void Wages() throws Exception {

		TestStatus status = getTestStatus();
		System.out.println("Testing");
		/*
		 * driver1.findElement(By.
		 * xpath("//input[@class = 'clsLabel' and @id ='form1:text1']")).sendKeys(
		 * "1412store"); driver1.findElement(By.
		 * xpath("//input[@class = 'clsLabel' and @id ='form1:secret1']")).sendKeys(
		 * "east1234");
		 */

		navToDailyDeprtmntWages(status);
		driver1.switchTo().frame(driver1.findElement(By.xpath("//html//frameset//frame[1]")));
		selectByValue(objDailyWages.dropdonw, "8");
		click(objDailyWages.linkGo);

		waitFor(2);
		driver1.switchTo().defaultContent();
		driver1.switchTo().frame("BOTTOM");

		selectByValue(objDailyWages.dailyplan, "Wages");
		waitFor(2);
		driver1.switchTo().defaultContent();

		driver1.switchTo().frame(driver1.findElement(By.xpath("//html//frameset//frame[2]")));
		waitFor(2);

		pageDailyWages.fnlWagesTotal(objDailyWages, extentTest);

		driver1.switchTo().defaultContent();
		status.assertAll();
	}

	public void navToDailyDeprtmntWages(TestStatus status) throws Exception {
		log.info("---------------Login into Application--------------------");
		Report_AddStep("testcase", "---------------Login into Application--------------------", "", "", "Pass");
		loginPage = new WoWLoginPage().createPage(getDriver(), getContext(), log, status, data, report, extentTest,
				WoWLoginPage.class);

		Report_AddStep("testcase", "Login with the Store : " + getContext().getStringProperty("store"), "", "", "Pass");
		pageDailyWages = loginPage.navToDailyDept(getContext().getStringProperty("store"));
		// log.info("-------------Navigate to Order Roster Report Page------------");
		// Report_AddStep("testcase", "---------------Navigate to Order Roster Report
		// Page--------------------","","", "Pass");
		// orderReportPage.clickOnOrderRoasterReport(objOrderRoaster);
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

		objDailyItemPrice = PageFactory.initElements(this.getDriver(), DailyPlanningMainObjects.class);
		objDailyCustomers = PageFactory.initElements(this.getDriver(), DailyPlanningMainObjects.class);
		objDailyItems = PageFactory.initElements(this.getDriver(), DailyPlanningMainObjects.class);
		objDailySales = PageFactory.initElements(this.getDriver(), DailyPlanningMainObjects.class);
		objDailyPaidHours = PageFactory.initElements(this.getDriver(), DailyPlanningMainObjects.class);
		objDailyWages = PageFactory.initElements(this.getDriver(), DailyPlanningMainObjects.class);
		BaseObj = PageFactory.initElements(this.getDriver(), BasePage.class);
		common = PageFactory.initElements(this.getDriver(), CommonFunctions.class);
		storeprop = PageFactory.initElements(this.getDriver(), StoreProperties.class);
		ScreenShotUtil = PageFactory.initElements(this.getDriver(), ExtentReportsScreenshotCode.class);

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
		cName = this.getClass().getSimpleName();
		reportPath = new File(getContext().getStringProperty("reportPath"));
		report = new ExtentReports(
				getContext().getStringProperty("reportPath") + "\\" + this.getClass().getSimpleName() + ".html");
		classList.add(this.getClass().getSimpleName());
		initializeTestcontext();
		System.out.println("test1 - Beforeclass");
		data = new XMLDataReader(log);
		customreport = new CustomExtentReports();
		createDriver(getContext().getStringProperty("WLPUrl"), getContext().getStringProperty("browser"), cName, mName,
				extentTest, driver1);
		log.info("Loading data for the class: ");
		data.loadTestDataXML(System.getProperty("user.dir") + "\\TestData\\NGBO\\articledetail.xml");
		customreport.createExtentReport();
	}

}
