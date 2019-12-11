package au.wow.wlp.utils;

import java.awt.Desktop;
import java.io.File;
import java.io.IOException;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.net.MalformedURLException;
import java.net.URL;
import java.sql.SQLException;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.Properties;
import java.util.concurrent.TimeUnit;
import javax.xml.parsers.ParserConfigurationException;
import org.apache.commons.io.FileUtils;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.htmlunit.HtmlUnitDriver;
import org.openqa.selenium.phantomjs.PhantomJSDriver;
import org.openqa.selenium.remote.CapabilityType;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.openqa.selenium.remote.UnreachableBrowserException;
import org.testng.Assert;
import org.testng.ITestContext;
import org.testng.ITestResult;
import org.testng.annotations.AfterClass;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Listeners;
import org.xml.sax.SAXException;
import com.relevantcodes.extentreports.ExtentReports;
import com.relevantcodes.extentreports.ExtentTest;
import com.relevantcodes.extentreports.LogStatus;

@Listeners({ au.wow.wlp.utils.TestBaseListener.class })
public class TestBase extends BasePage {

	// Declare ThreadLocal Driver (ThreadLocalMap) for ThreadSafe Tests
	public static ThreadLocal<WebDriver> driver = new ThreadLocal<>();
	public static DesiredCapabilities capabilities = null;
	static int first = 0;

	// public WebDriver driver;
	// public static RemoteWebDriver driver;
	public static TestContext context;
	protected static Logger log;
	public XMLDataReader data, data1;
	public ExtentReports report;
	public ExtentTest extentTest;
	protected CustomExtentReports customreport;
	public TestStatus status;
	SQLWrapper sql = new SQLWrapper(log);
	private static final Integer MCcount = 0;
	ExtentTest extLogger;
	boolean isExceptionCaptured;
	private static Logger logg;
	public static Properties prop;
	public static String startTime = "";
	public static String endTime = "";
	public static HashMap<String, Integer> sceCtMap = new HashMap<String, Integer>();
	public static HashMap<String, Integer> extMap = new HashMap<String, Integer>();
	public static WebDriver driver1;
	public static String stockTakeArttestdesc;
	public static ArrayList<String> methodList = new ArrayList<String>();
	public static ArrayList<String> classList = new ArrayList<String>();
	public static String cName, mName;

	public WebDriver getDriver() {
		return driver1;
	}

	public ExtentReports getReport() {
		return report;
	}

	public ExtentTest getReportLogger() {
		return extentTest;
	}

	public Logger getLogger() {
		return log;
	}

	public TestStatus getTestStatus() {
		return status;
	}

	public TestContext getContext() {
		return context;
	}

	public static String getStringProperty(String propertyname) {
		String value = null;
		if (prop.containsKey(propertyname)) {
			value = prop.getProperty(propertyname);
		} else
			logg.warn("Property: " + propertyname + " is not present in the config.properties file");
		return value;
	}

	/**
	 * Creates the driver object
	 * 
	 * @param URL
	 * @param browser
	 */
	public WebDriver createDriver(String URL, String browser ,String cName, String mName, ExtentTest extLogger,
			WebDriver driver) 
	{

		switch (browser) {
		case "chrome":
			try {
				if (TestContext.getStringProperty("isDocketRun").equalsIgnoreCase("true")) {
					createDocketDriver(URL);

				} else {
					createWebDriver(URL);
				}

			} catch (Exception e) {
				if (null == driver) {
					String val = "In Local";
					if (getContext().getStringProperty("isDocketRun").equalsIgnoreCase("true")) {
						val = "In Docker";
					}
					BasePage.htmlToExtent(cName, mName, extLogger, driver,
							"Driver launching issue " + val + ";;;Warning");
				}
				e.printStackTrace();
				Assert.fail("Unable to launch the browser\n", e.fillInStackTrace());
			}
			break;

		}
		return getDriver1();
	}

	public static WebDriver getDriver1() {
		if (first == 0) {
			context = new TestContext(log, System.getProperty("user.dir") + "\\TestData\\config.properties");
			first++;
		}
		if (TestContext.getStringProperty("isDocketRun").equalsIgnoreCase("true")) {
			return driver1;
		} else {
			return driver1;
		}
	}

	public void createWebDriver(String URL) {
		capabilities = new DesiredCapabilities();
		System.setProperty("webdriver.chrome.driver",
				System.getProperty("user.dir") + "\\lib\\drivers\\chromedriver.exe");
		capabilities.setCapability(CapabilityType.TAKES_SCREENSHOT, true);
		driver1 = new ChromeDriver(capabilities);
		getDriver1().get(URL);
		waitFor(1);
		getDriver1().manage().window().maximize();
		getDriver1().manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
	}

	public void createDocketDriver(String URL) throws MalformedURLException {
		// Set DesiredCapabilities
		capabilities = new DesiredCapabilities();
		capabilities = DesiredCapabilities.chrome();
		capabilities.setCapability(CapabilityType.TAKES_SCREENSHOT, true);
		capabilities.setJavascriptEnabled(true);
		capabilities.setCapability("platform", "LINUX");
		driver1 = new RemoteWebDriver(new URL("\r\n" + "http://10.57.42.18:32771//wd/hub"), capabilities);
		getDriver1().get(URL);
		getDriver1().manage().window().maximize();
		getDriver1().manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
	}

	/**
	 * Creates the object to read the config.properties file
	 */
	public void initializeTestcontext() {
		context = new TestContext(log, System.getProperty("user.dir") + "\\TestData\\config.properties");
	}

	/**
	 * Initializes the test status object
	 */
	public void initializeTestStatus() {
		status = new TestStatus(customreport);
	}

	/**
	 * Closes all the browser instances
	 */
	public void closeDriver(WebDriver driver) {
		System.out.println("close driver");
		driver1.quit();

	}

	/**
	 * Archives the previous run results
	 */
	public void archiveTestOutput() {
		TestResultsArchiver testarchive = new TestResultsArchiver(log);
		testarchive.copyDir();
	}

	public String getDateAndTime() {
		return (new SimpleDateFormat("yyyy/MM/dd HH:mm:ss").format(new Date()));

	}

	/**
	 * @throws ParseException
	 * @throws IOException
	 * 
	 */
	@AfterSuite
	public void flushReports1() throws IOException, ParseException {

		//closeDriver();

		System.out.println("aftersuite");
		Report_Footer("summary");
		endTime = getDateAndTime();
		try {
			SummaryCreation.create();
		} catch (SAXException | ParserConfigurationException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		File f = new File("C:\\sf sf newly\\sf sf nwly\\successfactor\\SuccessFactors\\Reports");
		File f1 = new File("C:\\sf sf newly\\sf sf nwly\\successfactor\\SuccessFactors\\Reports\\Screenshots");
		for (File file : f.listFiles()) {
			if (!file.isDirectory())
				file.delete();
		}
		for (File file : f1.listFiles()) {
			if (!file.isDirectory())
				file.delete();
		}

		FileUtils.copyDirectory(reportPath,
				new File("C:\\sf sf newly\\sf sf nwly\\successfactor\\SuccessFactors\\Reports"));
	}

	/**
	 * Executes before the test suite is started
	 * 
	 * @throws IOException
	 * @throws SecurityException
	 * @throws NoSuchFieldException
	 * @throws IllegalAccessException
	 * @throws IllegalArgumentException
	 */
	@BeforeSuite(alwaysRun = true)
	public void onStart() throws IOException, NoSuchFieldException, SecurityException, IllegalArgumentException,
			IllegalAccessException {
		int befo = 1;
		waitFor(1);
		System.out.println("testbase - beforesuite");
		startTime = getDateAndTime();
		System.setProperty("java.library.path", System.getProperty("user.dir") + "\\lib\\jdbc\\dblg64");
		Field fieldSysPath = ClassLoader.class.getDeclaredField("sys_paths");
		fieldSysPath.setAccessible(true);
		fieldSysPath.set(null, null);

		initializeTestcontext();

		Date dtSuiteSt = new Date();
		DateFormat dateFormat = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
		stStartSuite = dateFormat.format(dtSuiteSt);
		Date date = new Date();
		SimpleDateFormat sdf = new SimpleDateFormat("MMddyyyy_hmmss");
		String formattedDate = sdf.format(date);
		System.out.println(formattedDate); // 12/01/2011 4:48:16 PM

		String formattedDate1 = dateFormat.format(date);
		System.out.println(formattedDate1); // 12/01/2011 4:48:16 PM
		String dat = formattedDate1;
		dateex = new Date();

		System.out.println(System.getProperty("store"));

		Report_Header("Summary", new File(getContext().getStringProperty("reportPath")), System.getProperty("store"),
				dat);
	}

	public String getResultStatusName(int status) {
		String resultName = null;
		if (status == 1)
			resultName = "Pass";
		if (status == 2)
			resultName = "Fail";
		if (status == 3)
			resultName = "Fail";
		return resultName;
	}

	@AfterClass(alwaysRun = true)
	public void tearDown() {
		try {
			//closeDriver();
			sql.CLRCloseConnection();
			System.out.println("afterclass");
			System.out.println("extentList " + extentList);
			System.out.println("methodList " + methodList);
			System.out.println(methodList.size());
			// New
			sceCtMap.put(cName + "_totalCt", methodList.size());
			sceCtMap.put(cName + "_passCt", methodList.size() - extentList.get(cName).size());
			sceCtMap.put(cName + "_failCt", extentList.get(cName).size());
			tcCt.put(this.getClass().getSimpleName().toString(), MCcount);
			report.endTest(extLogger);
			report.flush();
			methodList.clear();
		} catch (Exception e) {
			System.out.println();

		}

	}

	@AfterMethod(alwaysRun = true)
	protected void flushReports(ITestResult result9, Method m9) throws IOException, ParseException {
		System.out.println("AffterMethod");

		try {

			if (result9.getAttribute("isFail").equals("true") && !isExceptionCaptured) {

				result9.removeAttribute("isFail");
			}
		} catch (Exception e) {
			System.out.println();

		}
	}

}