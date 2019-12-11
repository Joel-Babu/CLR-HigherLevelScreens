package au.wow.wlp.utils;

import java.io.File;
import java.io.IOException;
import java.math.BigDecimal;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.Random;
import java.util.TimeZone;

import org.apache.commons.io.FileUtils;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.NoAlertPresentException;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.UnhandledAlertException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

import au.wow.WLPmain.objects.DailyTradingStatementObjects;
import au.wow.WLPmain.objects.WeeklyPlanningStoreObjects;

import com.google.common.collect.ArrayListMultimap;
import com.google.common.collect.Multimap;
import com.relevantcodes.extentreports.ExtentReports;
import com.relevantcodes.extentreports.ExtentTest;
import com.relevantcodes.extentreports.LogStatus;
import java.util.Collection;
import java.util.Date;
import java.util.HashMap;

public class BasePage extends HTMLReport {
	// WLP
	protected Logger log;
	private TestContext context;
	public WebDriver driver;
	public ExtentReports report;
	public ExtentTest extentTest;
	private TestStatus status;
	private XMLDataReader testdata;
	public static String methodName;

	public static Multimap<String, String> extentList = ArrayListMultimap.create();
	public static ArrayList<String> classList = new ArrayList<String>();
	public static HashMap<String, Integer> tcCt = new HashMap<String, Integer>();
	public static String startTime = "";
	public static String endTime = "";
	public static HashMap<String, Integer> sceCtMap = new HashMap<String, Integer>();
	public static HashMap<String, Integer> extMap = new HashMap<String, Integer>();

	public void setDriver(WebDriver driver) {
		this.driver = driver;
	}

	protected void setContext(TestContext context) {
		this.context = context;
	}

	protected void setLogger(Logger log) {
		this.log = log;
	}

	protected void setTestStatus(TestStatus status) {
		this.status = status;
	}

	protected void setTestData(XMLDataReader testdata) {
		this.testdata = testdata;
	}

	public void setExtentTest(ExtentTest extentTest) {
		this.extentTest = extentTest;
	}

	public void setExtentReports(ExtentReports report) {
		this.report = report;
	}

	public WebDriver getDriver() {
		return driver;
	}

	protected TestContext getContext() {
		return context;
	}

	protected Logger getLogger() {
		return log;
	}

	protected TestStatus getStatus() {
		return status;
	}

	protected XMLDataReader getTestData() {
		return testdata;
	}

	public ExtentReports getReport() {
		return report;
	}

	public ExtentTest getReportLogger() {
		return extentTest;
	}

	/**
	 * Initialize the elements of the specified page class along with the objects of
	 * framework libraries
	 * 
	 * @param driver
	 * @param context
	 * @param log
	 * @param status
	 * @param testdata
	 * @param pageClassToProxy
	 * @return specifiedPage
	 */
	public <T extends BasePage> T createPage(WebDriver driver, TestContext context, Logger log, TestStatus status,
			XMLDataReader testdata, ExtentReports report, ExtentTest extentTest, Class<T> pageClassToProxy) {
		T page = PageFactory.initElements(driver, pageClassToProxy);
		page.setDriver(driver);
		page.setContext(context);
		page.setLogger(log);
		page.setTestStatus(status);
		page.setTestData(testdata);
		page.setExtentReports(report);
		page.setExtentTest(extentTest);
		return page;
	}

	/**
	 * Initialize the elements of the specified page class
	 * 
	 * @param pageClassToProxy
	 * @return specifiedPage
	 */
	public <T extends BasePage> T createPage(Class<T> pageClassToProxy) {
		T page = createPage(getDriver(), getContext(), getLogger(), getStatus(), getTestData(), getReport(),
				getReportLogger(), pageClassToProxy);
		page.setContext(context);
		page.setLogger(log);
		page.setTestStatus(status);
		page.setTestData(testdata);
		page.setExtentReports(report);
		page.setExtentTest(extentTest);
		return page;
	}

	/**
	 * Performs vertical scrolling in the browser/window
	 */
	public void verticalScroll() {
		((JavascriptExecutor) driver).executeScript("window.scrollBy(0,150)");
	}

	public void scrollToElement(WebElement element) {

	}

	/**
	 * Waits for a page to be loaded
	 */
	public void waitForPageToLoad() {
		new WebDriverWait(driver, 30).until(new ExpectedCondition<Boolean>() {
			@Override
			public Boolean apply(WebDriver arg0) {
				return (((JavascriptExecutor) driver).executeScript("return document.readyState").equals("complete"));
			}
		});
	}

	/**
	 * Waits for a specified element to be visible
	 * 
	 * @param element
	 */
	public void waitForElement(WebElement element) {
		// try{
		System.out.println(element);
		WebDriverWait wait = new WebDriverWait(driver, 30);
		wait.until(ExpectedConditions.visibilityOf(element));
		/*
		 * }catch(Exception e){ String elementname = getElementLocator(element); try {
		 * throw new CustomException(e, getLogger(), elementname); } catch
		 * (CustomException e1) { getLogger().
		 * error("TimeOut Exception occurred while waiting for the element: " +
		 * elementname); } }
		 */
	}

	/**
	 * Waits for a specified locator to be invisible
	 * 
	 * @param element
	 */
	public void waitForInvisibilityOfElement(By element) {
		try {
			WebDriverWait wait = new WebDriverWait(driver, 10);
			wait.until(ExpectedConditions.invisibilityOfElementLocated(element));
		} catch (Exception e) {
			// String elementname = getElementLocator(element);
			try {
				throw new CustomException(e, getLogger(), element.toString());
			} catch (CustomException e1) {
				getLogger().error("TimeOut Exception occurred while waiting for the element: " + element.toString());
			}
		}
	}

	/**
	 * Waits for a specified locator to be Cllickable
	 * 
	 * @param lnkReports
	 */
	public void waitForClickOfElement(WebElement lnkReports) {
		try {
			WebDriverWait wait = new WebDriverWait(driver, 10);
			wait.until(ExpectedConditions.elementToBeClickable(lnkReports));
		} catch (Exception e) {
			// String elementname = getElementLocator(element);
			try {
				throw new CustomException(e, getLogger(), lnkReports.toString());
			} catch (CustomException e1) {
				getLogger().error("TimeOut Exception occurred while waiting for the element: " + lnkReports.toString());
			}
		}
	}

	/**
	 * Waits for a specified page to be displayed
	 * 
	 * @param page
	 */
	public void waitForPage(String page) {
		try {
			WebDriverWait wait = new WebDriverWait(driver, 10);
			wait.until(ExpectedConditions.titleContains(page));
		} catch (Exception e) {
			try {
				throw new CustomException(e, getLogger(), page);
			} catch (CustomException e1) {
				getLogger().error("TimeOut Exception occurred while waiting for the page: " + page);
			}
		}
	}

	/**
	 * Waits for a specified number of seconds
	 * 
	 * @param seconds
	 */
	public void waitFor(double seconds) {
		try {
			Thread.sleep((long) seconds * 1000);
		} catch (InterruptedException e) {
			log.error("Interruption occured during waiting", e);
		}
	}

	/*
	 * public String formatDate(String date, String format){ return new
	 * SimpleDateFormat(format).format(date);
	 * 
	 * }
	 */

	/**
	 * Converts a date to a specified format
	 * 
	 * @param date
	 * @param format
	 * @return formattedDate
	 */
	public String formatDate(Date date, String format) {
		return new SimpleDateFormat(format).format(date);

	}

	/**
	 * Converts a string to date
	 * 
	 * @param date
	 * @param format
	 * @return parsedDate
	 */
	public Date parseDate(String date, String format) {
		Date returndate = null;
		// String formattedDate = new SimpleDateFormat(format).format(date);
		try {
			returndate = new SimpleDateFormat(format).parse(date);
		} catch (ParseException e) {
			getStatus().fail("Error in parsing the date: " + date);
		}
		return returndate;
	}

	/**
	 * Convert string to integer
	 * 
	 * @param str
	 * @return integer value of specified string
	 */
	public int toInt(String str) {
		return Integer.parseInt(str);
	}

	/**
	 * Convert string to double
	 * 
	 * @param str
	 * @return double value of specified string
	 */
	public double toDouble(String str) {
		return Double.parseDouble(str);
	}

	/*
	 * public Date parseAbbreviatedMonth(String date){ Date formattedDate =
	 * parseDate(date, "dd MMM yyyy"); Calendar cal = Calendar.getInstance();
	 * cal.setTime(formattedDate); return stringToDate(cal.getTime().toString(),
	 * toformat); }
	 */

	// ================= Common Weblement methods============================

	public int GetIndexFromHTMLTabHead(List<WebElement> objectTable, String ColToSerach) {
		List<WebElement> tr_collection = objectTable;
		int row_Count = objectTable.size();
		int i = 1, j = 0;
		System.out.println("Number Of Rows = " + row_Count);

		for (WebElement trElement : tr_collection) {
			List<WebElement> td_collection = trElement.findElements(By.xpath("th"));
			// System.out.println("NUMBER OF
			// COLUMNS="+td_collection.size()+trElement.getText());

			for (WebElement tdElement : td_collection) {
				if ((tdElement.getText().toString().trim()).equalsIgnoreCase(ColToSerach)) {
					j = i;
					break;
				}
				i = i + 1;
			}

		}
		return j;

	}

	public void GetValFromHTMLTable(List<WebElement> objectTable, int ColSearchIndex, int ColRetrieveIndex,
			String ColSearchval, String searchval) throws IOException {
		List<WebElement> tr_collection = objectTable;
		// int row_Count = objectTable.size();
		// int i = 1 , k = 1, j = 0;
		// System.out.println("Number Of Rows = " + row_Count);
		// System.out.println(ColSearchIndex+"jdjd"+ColRetrieveIndex);

		for (WebElement trElement : tr_collection) {

			List<WebElement> td_collection = trElement.findElements(By.xpath("td"));
			// System.out.println("NUMBER OF COLUMNS="+td_collection.size());
			// System.out.println(td_collection.get(ColSearchIndex-1).getText()+"ssdd");
			if ((td_collection.get(ColSearchIndex - 1).getText().toString().trim()).equalsIgnoreCase(ColSearchval)) {

				if ((td_collection.get(ColRetrieveIndex - 1).getText().toString().trim()).equalsIgnoreCase(searchval)) {
					Report_AddStep("testcase", "Verified for Trading Paramater: " + ColSearchval, searchval, searchval,
							"Pass");
					break;
				} else {
					Report_AddStep("testcase", "Verified for : " + ColSearchval, searchval,
							"Fail to display correct value", "Fail");
				}
			}

		}

	}

	public String getElementLocator(WebElement element) {
		String name = element.toString();
		int index = name.lastIndexOf("->");
		return name.substring(index + 2, (name.length() - 1));
	}

	public String getElementLocatorTwo(WebElement element) {
		String name = element.toString();
		int index = name.lastIndexOf("->");
		return name.substring(index + 2, (name.length() - 1));
	}

	/**
	 * Enters the provided value in the textbox
	 * 
	 * @param textbox
	 *            - WebElement to which value is entered
	 * @param value
	 *            - Value to be entered
	 */
	public void enterText(WebElement textbox, String value) {
		String elementname = getElementLocator(textbox);
		try {

			textbox.clear();
			textbox.sendKeys(value);
			/*
			 * getLogger().info("The textbox: " + elementname + " is set to value: " +
			 * value);
			 */
		} catch (Exception e) {
			try {
				throw new CustomException(e, getLogger(), elementname);
			} catch (CustomException e1) {
				getLogger().error("Error in entering the value: " + value + " in textbox: " + elementname);
			}
		}
	}
	/*
	 * public void enterTextTwo(WebElement textbox, double value){ String
	 * elementname = getElementLocator(textbox); try{ textbox.clear();
	 * textbox.sendKeys(value); getLogger().info("The textbox: " + elementname +
	 * " is set to value: " + value); }catch(Exception e){ try { throw new
	 * CustomException(e, getLogger(), elementname); } catch (CustomException e1) {
	 * getLogger().error("Error in entering the value: " + value + " in textbox: " +
	 * elementname); } } }
	 */

	/**
	 * Gets the value of a html attribute for an element
	 * 
	 * @param element
	 *            - WebElement to be clicked
	 * @return String - value of a html attribute
	 */
	public String getValueAttribute(WebElement element) {
		String elementname = getElementLocator(element);
		String value = null;
		try {
			value = element.getAttribute("value");
			/*
			 * getLogger().info("Value attribute: " + value + " from the element: " +
			 * elementname);
			 */
		} catch (Exception e) {
			try {
				throw new CustomException(e, getLogger(), elementname);
			} catch (CustomException e1) {
				getLogger().error("Error in retrieving the value attribute from the element: " + elementname);
			}
		}
		return value;
	}

	public String getAttribute(WebElement element) {
		String elementname = getElementLocator(element);
		String value = null;
		try {
			value = element.getAttribute("value");
		} catch (Exception e) {
			getLogger().error("Error in retrieving the value attribute from the element: " + elementname);
		}
		return value;
	}

	public String getAttributeWithDalor(WebElement element) {
		String elementname = getElementLocator(element);
		String value = null;
		try {
			value = element.getAttribute("value").trim().replace("$", "");
		} catch (Exception e) {
			getLogger().error("Error in retrieving the value attribute from the element: " + elementname);
		}
		return value;
	}

	/**
	 * Enters the value in a textbox character by character till autocomplete box is
	 * visible
	 * 
	 * @param element
	 *            - WebElement to which the character is entered
	 * @param text
	 *            - character to be entered
	 */
	public void typeTextInAutoComplete(WebElement element, String text) {
		for (int i = 0; i < text.length(); i++) {
			element.sendKeys(String.valueOf(text.charAt(i)));
			waitFor(1);
		}
	}

	/**
	 * Clicks on an Element(button, link)
	 * 
	 * @param element
	 *            - WebElement to be clicked
	 */
	public void click(WebElement element) {
		String elementname = getElementLocator(element);
		try {
			element.click();
			// getLogger().info("The element: " + elementname + " is clicked");
		} catch (Exception e) {
			try {
				throw new CustomException(e, getLogger(), elementname);
			} catch (CustomException e1) {
				getLogger().error("Error in clicking the element: " + elementname);
			}
		}
	}

	public void clear(WebElement element) {
		String elementname = getElementLocator(element);
		try {
			element.clear();
			// getLogger().info("The element: " + elementname + " is clicked");
		} catch (Exception e) {
			try {
				throw new CustomException(e, getLogger(), elementname);
			} catch (CustomException e1) {
				getLogger().error("Error in clicking the element: " + elementname);
			}
		}
	}

	/**
	 * Retrieves the visible text of an element
	 * 
	 * @param element
	 * @return String - visible text of an element
	 */
	public String getText(WebElement element) {
		String elementname = getElementLocator(element);
		String text = null;
		try {
			text = element.getText().trim();
			// getLogger().info("Element's text: " + text);
		} catch (Exception e) {
			try {
				throw new CustomException(e, getLogger(), elementname);
			} catch (CustomException e1) {
				getLogger().error("Error in retrieving the text from the element: " + elementname);
			}
		}
		return text;
	}

	public String getInnerText(String element) {
		final By Field = By.xpath(element);
		WebElement Val = driver.findElement(Field);
		String Page = Val.getAttribute("innerText");
		return Page;

	}

	public String getInnerTextTwo(String element) {
		final By Field = By.xpath(element);
		WebElement Val = driver.findElement(Field);
		String Page = getText(Val);
		return Page;

	}

	/**
	 * GetText By
	 */
	public String getTitle(By title) {
		WebElement titleElement = (WebElement) (new WebDriverWait(driver, 50))
				.until(ExpectedConditions.presenceOfElementLocated(title));
		;
		String titleText = titleElement.getText();
		return titleText;
	}

	/**
	 * Trim
	 */
	public double TrimData(String Value) {
		double Data = 0;
		if (Value.contains("WTD Mix:") && Value.contains("%")) {
			Data = Double.parseDouble(Value.replace("%", "").replace("WTD Mix:", ""));
			System.out.println(Data);
			return Data;
		}
		if (Value.contains("Days Mix") || Value.contains("WTD Mix")) {
			String[] Split = Value.split(":");
			Data = Double.parseDouble(Split[1].replaceAll("%", "").trim());
			System.out.println(Data);
			return Data;
		} else if (Value.contains("%") && Value.contains("*")) {
			Data = Double.parseDouble(Value.replace("%", "").replace("*", ""));
			System.out.println(Data);
			return Data;
		} else if (Value.contains("%")) {
			Data = Double.parseDouble(Value.replaceAll("%", ""));
			System.out.println(Data);
			return Data;
		} else if (Value.trim().isEmpty() || Value.contains(" ") || Value.contains("null")) {
			Data = 0.0;
		} else if (Value.contains("$")) {
			String SplitedValue = Value.replace("$", "");
			Data = Double.parseDouble(SplitedValue.replaceAll(",", ""));
			System.out.println(Data);
			return Data;
		} else if (Value.contains("*") && Value.contains(",")) {
			Data = Double.parseDouble(Value.replace("*", "").replace(",", ""));
			System.out.println(Data);
			return Data;
		} else if (Value.contains("*")) {
			Data = Double.parseDouble(Value.replace("*", ""));
			System.out.println(Data);
			return Data;
		} else if (Value.contains(",")) {
			Data = Double.parseDouble(Value.replaceAll(",", ""));
			System.out.println(Data);
			return Data;
		} else if (Value.contains(".")) {
			Data = Double.parseDouble(Value);
			System.out.println(Data);
			return Data;
		} else {
			Data = Double.parseDouble(Value);
			return Data;
		}
		System.out.println(+Data);
		return Data;
	}

	// Big Decimal
	public BigDecimal ConvertBigDecimal(double Value) {
		BigDecimal FinalUI = new BigDecimal(Value);
		return FinalUI.setScale(2, BigDecimal.ROUND_HALF_DOWN);
	}

	public BigDecimal ConvertBigDecimalSingleDigit(double Value) {
		BigDecimal FinalUI = new BigDecimal(Value);
		return FinalUI.setScale(1, BigDecimal.ROUND_HALF_DOWN);
	}

	// Big Decimal String
	public BigDecimal ConvertBigDecimal(String Value) {
		BigDecimal FinalUI = new BigDecimal(Value);
		return FinalUI.setScale(1, BigDecimal.ROUND_HALF_DOWN);
	}

	// Big Decimal Whole Nbr
	public BigDecimal ConvertBigDecimalNbr(double Value) {
		BigDecimal FinalUI = new BigDecimal(Value);
		return FinalUI.setScale(0, BigDecimal.ROUND_HALF_DOWN);
	}

	public int WeeklyPlanningStoreTablecount() {
		final By TableRowCount = By.xpath("//*[@id='frmDetails:ctrStorePlanningDetailsTable']/tbody/tr");
		int Rows = driver.findElements(TableRowCount).size();
		System.out.println("Row Count: " + Rows);
		return Rows;

	}

	public int TradingStatementTableCount() {
		final By TableRowCount = By.xpath("//*[@id='dispForm:dts']/tbody/tr");
		int Rows = driver.findElements(TableRowCount).size();
		System.out.println("Row Count: " + Rows);
		return Rows;
	}

	public int CopyDataToNewsToTableCount() {
		final By TableRowCount = By.xpath("//*[@id='form2:factorTableId:tb']/tr");
		int Rows = driver.findElements(TableRowCount).size();
		System.out.println("Row Count: " + Rows);
		return Rows;
	}

	public int TradingStatementGroupTableCount() {
		final By TableRowCount = By.xpath("//*[@id='dispForm:dtsArea']/tbody/tr");
		int Rows = driver.findElements(TableRowCount).size();
		System.out.println("Row Count: " + Rows);
		return Rows;
	}

	public int TradingStatementTableCount(String Table) {
		final By TableRowCount = By.xpath(Table);
		int Rows = driver.findElements(TableRowCount).size();
		System.out.println("Row Count: " + Rows);
		return Rows;
	}

	/**
	 * Retrieves element text using JavascriptExecutor
	 * 
	 * @param element
	 * @return String - visible text of an element
	 */
	public String getElementTextUsingJs(WebElement element) {
		return (String) ((JavascriptExecutor) getDriver()).executeScript("return arguments[0].innerText;", element);
	}

	/**
	 * Click using JavascriptExecutor
	 * 
	 * @param element
	 */
	public void clickElmentUsingJs(WebElement element) {
		((JavascriptExecutor) driver).executeScript("arguments[0].click();", element);
	}

	public void enterElmentUsingJs(WebElement element, String longstring) {
		((JavascriptExecutor) driver).executeScript("arguments[0].value = '" + longstring + "'", element);
	}

	/**
	 * Selects an option in dropdown using visibletext
	 * 
	 * @param dropdown
	 * @param text
	 */
	public void selectByVisibleText(WebElement dropdown, String text) {
		String elementname = getElementLocator(dropdown);
		try {
			Select select = new Select(dropdown);
			select.selectByVisibleText(text);
			/*
			 * getLogger().info("The option with text: " + text +
			 * " is selected in the dropdown: " + elementname);
			 */
		} catch (Exception e) {
			try {
				throw new CustomException(e, getLogger(), elementname);
			} catch (CustomException e1) {
				getLogger().error("Error in selecting the text: " + text + " from the dropdown: " + elementname);
			}
		}
	}

	/**
	 * Selects an option in dropdown using index
	 * 
	 * @param dropdown
	 * @param index
	 */
	public void selectByIndex(WebElement dropdown, int index) {
		String elementname = getElementLocator(dropdown);
		try {
			Select select = new Select(dropdown);
			select.selectByIndex(index);
			getLogger().info("The option at index: " + index + " is selected in the dropdown: " + elementname);
		} catch (Exception e) {
			try {
				throw new CustomException(e, getLogger(), elementname);
			} catch (CustomException e1) {
				getLogger().error("Error in selecting the index: " + index + "from the dropdown: " + elementname);
			}
		}
	}

	/**
	 * Selects an option in dropdown using value
	 * 
	 * @param dropdown
	 * @param value
	 */
	public void selectByValue(WebElement dropdown, String value) {
		String elementname = getElementLocator(dropdown);
		try {
			Select select = new Select(dropdown);
			select.selectByValue(value);
			/*
			 * getLogger().info("The option with value: " + value +
			 * " is selected in the dropdown: " + elementname);
			 */
		} catch (Exception e) {
			try {
				throw new CustomException(e, getLogger(), elementname);
			} catch (CustomException e1) {
				getLogger().error("Error in selecting the value: " + value + " from the dropdown: " + elementname);
			}
		}
	}

	/**
	 * Retrieves the List of options selected in a dropdown
	 * 
	 * @param dropdown
	 * @return options
	 */
	public List<WebElement> getSelectedOptions(WebElement dropdown) {
		String elementname = getElementLocator(dropdown);
		List<WebElement> options = new ArrayList<WebElement>();
		try {
			Select select = new Select(dropdown);
			options = select.getAllSelectedOptions();
			if (options.size() > 0) {
				getLogger().info(
						"The following options are selected " + options.toString() + " in dropdown: " + elementname);
			} else
				getLogger().info("No option is selected in the dropdown: " + elementname);

		} catch (Exception e) {
			try {
				throw new CustomException(e, getLogger(), elementname);
			} catch (CustomException e1) {
				getLogger().error("Error in retrieving the selected options from the dropdown: " + elementname);
			}
		}
		return options;
	}

	/**
	 * Retrieves list of options available in a dropdown
	 * 
	 * @param dropdown
	 * @return options
	 */
	public List<WebElement> getAllOptions(WebElement dropdown) {
		String elementname = getElementLocator(dropdown);
		List<WebElement> options = new ArrayList<WebElement>();
		try {
			Select select = new Select(dropdown);
			options = select.getOptions();
			if (options.size() < 0) {
				getLogger().error("No option is listed in " + elementname);
			}

		} catch (Exception e) {
			try {
				throw new CustomException(e, getLogger(), elementname);
			} catch (CustomException e1) {
				getLogger().error("Error in retrieving the selected options from the dropdown: " + elementname);
			}
		}
		return options;
	}

	/**
	 * Retreives the list of values from a dropdown
	 * 
	 * @param dropdown
	 * @return options text
	 */
	public List<String> getOptionsText(WebElement dropdown) {
		List<WebElement> options = getAllOptions(dropdown);
		List<String> text = new ArrayList<String>();
		if (options.size() > 0) {
			Iterator<WebElement> it = options.iterator();
			while (it.hasNext()) {
				text.add(it.next().getText().trim());
			}
		} else {
			getLogger().error("Options list is empty");
		}
		getLogger().info("The following options are present " + text + " in dropdown");
		return text;

	}

	/**
	 * Checks if an element is displayed
	 * 
	 * @param element
	 * @return isElementDisplayed - true/false
	 */
	public boolean isDisplayed(WebElement element) {

		boolean isElementDisplayed = false;
		String elementname = getElementLocator(element);
		try {
			isElementDisplayed = element.isDisplayed();
			if (isElementDisplayed) {
				System.out.println("Element: " + elementname + " is visible");
			} else
				getLogger().warn("Element: " + elementname + " is not visible");
		} catch (Exception e) {
			try {
				throw new CustomException(e, getLogger(), elementname);
			} catch (CustomException e1) {
				getLogger().error("Error in checking the visibility of the element: " + elementname);
			}
		}
		return isElementDisplayed;
	}

	/*
	 * public boolean isDisplayedtwo(String element){
	 * 
	 * boolean isElementDisplayed = false; String elementname =
	 * getElementLocator(element); try { isElementDisplayed = element.isDisplayed();
	 * if(isElementDisplayed) { System.out.println("Element: " + elementname +
	 * " is visible"); } else getLogger().warn("Element: " + elementname +
	 * " is not visible"); } catch(Exception e) { try { throw new CustomException(e,
	 * getLogger(), elementname); } catch (CustomException e1) {
	 * getLogger().error("Error in checking the visibility of the element: " +
	 * elementname); } } return isElementDisplayed; }
	 */

	public boolean isDisplayedTwo(WebElement element, WebElement one) throws Exception {

		boolean isElementDisplayed = false;
		SwitchFrame(one);
		String elementname = getElementLocator(element);
		try {
			isElementDisplayed = element.isDisplayed();
			if (isElementDisplayed) {
				System.out.println("Element: " + elementname + " is visible");
			} else
				getLogger().warn("Element: " + elementname + " is not visible");
		} catch (Exception e) {
			try {
				throw new CustomException(e, getLogger(), elementname);
			} catch (CustomException e1) {
				getLogger().error("Error in checking the visibility of the element: " + elementname);
			}
		}
		return isElementDisplayed;
	}

	/**
	 * Checks if an element is selected
	 * 
	 * @param element
	 * @return isElementSelected - true/false
	 */
	public boolean isSelected(WebElement element) {
		boolean isElementSelected = false;
		String elementname = getElementLocator(element);
		try {
			isElementSelected = element.isSelected();
			if (isElementSelected) {
				getLogger().info("Element: " + elementname + " is selected");
			} else
				getLogger().warn("Element: " + elementname + " is not selected");

		} catch (Exception e) {
			try {
				throw new CustomException(e, getLogger(), elementname);
			} catch (CustomException e1) {
				getLogger().error("Error in checking the selected property of the element: " + elementname);
			}
		}
		return isElementSelected;

	}

	/**
	 * Checks if the element is enabled
	 * 
	 * @param element
	 * @return isElementEnabled - true/false
	 */
	public boolean isEnabled(WebElement element) {
		boolean isElementEnabled = false;
		String elementname = getElementLocator(element);
		try {
			isElementEnabled = element.isEnabled();
			if (isElementEnabled) {
				getLogger().info("Element: " + elementname + " is enabled");
			} else
				getLogger().warn("Element: " + elementname + " is not enabled to perform operation");
		} catch (Exception e) {
			try {
				throw new CustomException(e, getLogger(), elementname);
			} catch (CustomException e1) {
				getLogger().error("Error in checking the enabled property of the element: " + elementname);
			}
		}
		return isElementEnabled;
	}

	/**
	 * Switch To Frame
	 */
	public void SwitchFrame(WebElement element) throws Exception {
		try {
			System.out.println(element);
			driver.switchTo().defaultContent();
			if (isDisplayed(element)) {
				driver.switchTo().frame(element);
			}
		} catch (Exception e) {
			System.out.println("Generic Exception occured");
		}
	}

	/**
	 * Checks if the element is readonly
	 * 
	 * @param element
	 * @return isReadOnly - true/false
	 */
	public boolean isReadOnly(WebElement element) {
		String readonly = null;
		boolean isReadOnly = false;
		String elementname = getElementLocator(element);
		try {
			readonly = element.getAttribute("readonly");
			if (readonly == null) {
				getLogger().info("Element: " + elementname + " has no such property");
			} else if (readonly.equalsIgnoreCase("readonly")) {
				getLogger().info("Element: " + elementname + " is read only");
				isReadOnly = true;
			} else
				getLogger().info("Element: " + elementname + " is not read only");
		} catch (Exception e) {
			try {
				throw new CustomException(e, getLogger(), elementname);
			} catch (CustomException e1) {
				getLogger().error("Error in retrieving the readonly property of the element: " + elementname);
			}
		}
		return isReadOnly;
	}

	/**
	 * Retrieves the tooltiptext of an element
	 * 
	 * @param mouseoverelement
	 *            - element to mouseover
	 * @param tooltip
	 *            - element to get text
	 * @return tooltiptext
	 */
	public String getTooltipText(WebElement mouseoverelement, WebElement tooltip) {
		String tooltiptext = null;
		String elementname = getElementLocator(mouseoverelement);
		try {
			Actions action = new Actions(getDriver());
			action.moveToElement(mouseoverelement).perform();
			waitForElement(tooltip);
			if (isDisplayed(tooltip)) {
				tooltiptext = getText(tooltip);
				getLogger().info("Tooltip text: " + tooltiptext + " is displayed for the element: " + elementname);
			} else {
				getLogger().warn("MouseOver text is not displayed for the element: " + elementname);
			}
		} catch (Exception e) {
			try {
				throw new CustomException(e, getLogger(), elementname);
			} catch (CustomException e1) {
				getLogger().error("Error in retrieving the tooltiptext of the element: " + elementname);
			}
		}
		return tooltiptext;

	}

	/**
	 * Selects an element in a radiogroup based on the text provided
	 * 
	 * @param rb
	 *            - radiogroup
	 * @param textToSelect
	 */
	public void selectRBWithText(List<WebElement> rb, String textToSelect) {
		boolean found = false;
		try {
			for (WebElement element : rb) {
				if (element.getText().contains(textToSelect)) {
					element.click();
					found = true;
					break;
				}
			}
			if (found) {
				getLogger().info(textToSelect + " is selected from the radiogroup");
			} else
				getLogger().warn(textToSelect + " is not present in the radiogroup");
		} catch (Exception e) {
			try {
				throw new CustomException(e, getLogger(), rb.toString());
			} catch (CustomException e1) {
				getLogger().error("Error in clicking on radiobutton");
			}
		}
	}

	/**
	 * Clicks on a checkbox element
	 * 
	 * @param checkbox
	 */
	public void selectCheckBox(WebElement checkbox) {
		String elementname = getElementLocator(checkbox);
		try {
			checkbox.click();
			getLogger().info("Checkbox: " + elementname + " is selected");
		} catch (Exception e) {
			try {
				throw new CustomException(e, getLogger(), elementname);
			} catch (CustomException e1) {
				getLogger().error("Error in selecting the checkbox: " + elementname);
			}
		}
	}

	public List<List<Object>> fnGetValuesFromTable(List<WebElement> objectTable, int RowNo) {
		List<List<Object>> tableValues = new ArrayList<List<Object>>();
		List<Object> row = null;
		List<WebElement> tr_collection = objectTable;
		int row_Count = objectTable.size();
		System.out.println("Number Of Rows = " + row_Count);
		int row_num, col_num;
		row_num = 1;
		for (WebElement trElement : tr_collection) {
			List<WebElement> td_collection = trElement.findElements(By.xpath("td"));
			System.out.println("NUMBER OF COLUMNS=" + td_collection.size());
			col_num = 0;
			row = new ArrayList<>(td_collection.size());
			for (WebElement tdElement : td_collection) {
				// System.out.println("row # "+row_num+", col # "+col_num+
				// "text="+tdElement.getText());
				row.add(tdElement.getText());
				col_num++;
			}

			tableValues.add(row);

			if (RowNo == row_num) {
				break;
			}
			row_num++;
		}
		return tableValues;
	}

	public List<List<Object>> getValuesFromTable(List<WebElement> objectTable) {
		List<List<Object>> tableValues = new ArrayList<List<Object>>();
		List<Object> row = null;
		List<WebElement> tr_collection = objectTable;
		int row_Count = objectTable.size();
		System.out.println("Number Of Rows = " + row_Count);
		int row_num, col_num;
		row_num = 1;
		for (WebElement trElement : tr_collection) {
			List<WebElement> td_collection = trElement.findElements(By.xpath("td"));
			System.out.println("NUMBER OF COLUMNS=" + td_collection.size());
			col_num = 0;
			row = new ArrayList<>(td_collection.size());
			for (WebElement tdElement : td_collection) {
				// System.out.println("row # "+row_num+", col # "+col_num+
				// "text="+tdElement.getText());
				row.add(tdElement.getText());
				col_num++;
			}

			tableValues.add(row);

			row_num++;
		}

		return tableValues;
	}

	public void fnClickFromTable(List<WebElement> objectTable, String Source, int Col) {
		List<List<Object>> tableValues = new ArrayList<List<Object>>();
		List<Object> row = null;
		List<WebElement> tr_collection = objectTable;
		int row_Count = objectTable.size();
		System.out.println("Number Of Rows = " + row_Count);
		int row_num, col_num;
		row_num = 1;
		for (WebElement trElement : tr_collection) {
			List<WebElement> td_collection = trElement.findElements(By.xpath("td"));
			System.out.println("NUMBER OF COLUMNS=" + td_collection.size());
			col_num = 0;
			row = new ArrayList<>(td_collection.size());
			for (WebElement tdElement : td_collection) {
				if (tdElement.getText().toString().equalsIgnoreCase(Source)) {

					// System.out.println("row # "+row_num+", col # "+col_num+
					// "text="+tdElement.getText());
					// row.add(tdElement.getText());
				}
				col_num++;
			}

			tableValues.add(row);

			row_num++;
		}
	}

	public String TimeDiff(String DBTime, String TimeDiff) throws ParseException {
		SimpleDateFormat timeFormat = new SimpleDateFormat("HH:mm");
		timeFormat.setTimeZone(TimeZone.getTimeZone("UTC"));

		Date date1 = timeFormat.parse(DBTime);
		Date date2 = timeFormat.parse(TimeDiff);

		long sum = date1.getTime() - date2.getTime();

		String date3 = timeFormat.format(new Date(sum));
		System.out.println("The sum is " + date3);
		return date3;
	}

	public boolean waitForJSandJQueryToLoad() {

		WebDriverWait wait = new WebDriverWait(getDriver(), 100);

		// wait for jQuery to load
		ExpectedCondition<Boolean> jQueryLoad = new ExpectedCondition<Boolean>() {
			@Override
			public Boolean apply(WebDriver driver) {
				try {
					return ((Long) ((JavascriptExecutor) getDriver()).executeScript("return jQuery.active") == 0);
				} catch (Exception e) {
					// no jQuery present
					return true;
				}
			}
		};

		// wait for Javascript to load
		ExpectedCondition<Boolean> jsLoad = new ExpectedCondition<Boolean>() {
			@Override
			public Boolean apply(WebDriver driver) {
				return ((JavascriptExecutor) getDriver()).executeScript("return document.readyState").toString()
						.equals("complete");
			}
		};

		return wait.until(jQueryLoad) && wait.until(jsLoad);
	}

	public void scrolltoElement(WebElement element) {
		JavascriptExecutor je = (JavascriptExecutor) getDriver();
		String elementname = getElementLocator(element);
		je.executeScript("arguments[0].scrollIntoView();", element);
	}

	protected List<List<Object>> removeIndex(List<List<Object>> listone, String index) {

		for (Iterator<List<Object>> it = listone.iterator(); it.hasNext();) {
			List<Object> elem = it.next();
			if (elem.isEmpty()) {
				it.remove();
			}
			String[] spInd = index.split(",");
			for (int i = 0; i < spInd.length; i++) {
				elem.remove(Integer.parseInt(spInd[i]));
			}

			System.out.println(elem);
		}

		return listone;
	}

	protected void ElementContains(List<List<Object>> one, List<List<Object>> two) throws IOException {

		for (Iterator<List<Object>> it = one.iterator(); it.hasNext();) {
			for (Iterator<List<Object>> it1 = one.iterator(); it1.hasNext();) {
				List<Object> elem = it.next();
				List<Object> elem1 = it1.next();

				if (elem.containsAll(elem1)) {
					Report_AddStep("testcase", "Application list matches with the DB list", elem + "", elem1 + "",
							"Pass");
				} else {
					Report_AddStep("testcase", "Application list NOT matches with the DB list", elem + "", elem1 + "",
							"Fail");
				}
			}
		}
	}

	protected List<List<Object>> removeEmptyArray(List<List<Object>> listone) {

		for (Iterator<List<Object>> it = listone.iterator(); it.hasNext();) {
			List<Object> elem = it.next();
			if (elem.isEmpty()) {
				it.remove();
			}
			elem.remove("");
			System.out.println(elem);
		}

		return listone;
	}

	public int doubleToInt(String value) {
		double d = Double.parseDouble(value);
		int num = (int) d;
		return num;

	}

	public static boolean isDialogPresent(WebDriver driver) {
		try {
			driver.getTitle();
			return true;
		} catch (UnhandledAlertException e) {
			// Modal dialog showed
			return false;
		}
	}

	public List<List<Object>> StringToListCreation(String str1) {
		List<Object> listm = new ArrayList<>();
		String[] lisStrings = str1.split(":");
		for (int i = 0; i < lisStrings.length; i++) {
			listm.add(lisStrings[i]);
		}
		List<List<Object>> list5 = new ArrayList<List<Object>>();
		list5.add(listm);
		return list5;
	}

	public WebElement prepareWebElementWithDynamicXpathWithInt(String xpathValue, int i) {
		WebElement Xpath = driver.findElement(By.xpath(xpathValue.replace("dynamic", "" + i).replace("$", "")));
		return Xpath;
	}

	public WebElement prepareWebElementWithDynamicXpathWithString(String xpathValue, String value) {
		WebElement Xpath = driver.findElement(By.xpath(xpathValue.replace("dynamic", "" + value)));
		return Xpath;
	}

	public WebElement prepareWebElementWithDynamicXpathWithInt(String xpathValue) {
		WebElement Xpath = driver.findElement(By.xpath(xpathValue));
		return Xpath;
	}

	public WebElement prepareWebElementWithDynamicXpathWithIntTry(String xpathValue, int i) {
		WebElement Xpath = driver.findElement(By.xpath(xpathValue.replace("dynamic", "" + i).replace("$", "c")));
		return Xpath;
	}

	public boolean isElementPresent(String xpathValue) {
		boolean isPresent = false;
		try {
			isPresent = driver.findElements(By.xpath(xpathValue)).size() != 0;
		} catch (NoSuchElementException e) {
			isPresent = false;
		}
		return isPresent;
	}

	public WebElement prepareWebElementWithDynamicXpathforDays(
			DailyTradingStatementObjects objDailyTradingStatementObjects, String xpathValue, String Value, int j) {
		String Date0 = null, Date1 = null, Date2 = null, Date3 = null, Date4 = null, Date5 = null, Date6 = null;
		if (xpathValue.equalsIgnoreCase("Date0")) {
			String Element = objDailyTradingStatementObjects.MondayActlSales.replace("dynamic", "" + j);
			String Element1 = objDailyTradingStatementObjects.MondayActlSales.replace("dynamic", "" + j);
			if (Value.equalsIgnoreCase("PlannedSales"))
				Date0 = objDailyTradingStatementObjects.MondayPlndSales;
			if (Value.equalsIgnoreCase("ActualSales"))
				if (isElementPresent(Element1))
					Date0 = objDailyTradingStatementObjects.MondayActlSales;
				else
					Date0 = objDailyTradingStatementObjects.MondayActlSalesYellow;
			if (Value.equalsIgnoreCase("LYSales"))
				Date0 = objDailyTradingStatementObjects.MondayLYSales;
			if (Value.equalsIgnoreCase("PlannedValue"))
				Date0 = objDailyTradingStatementObjects.MondayPlndSales;
			if (Value.equalsIgnoreCase("PlannedValueOR"))
				Date0 = objDailyTradingStatementObjects.MondayPlndSales;
			if (Value.equalsIgnoreCase("ActualValueOR"))
				Date0 = objDailyTradingStatementObjects.MondayActlSales;
			if (Value.equalsIgnoreCase("ActualValue"))
				if (isElementPresent(Element))
					Date0 = objDailyTradingStatementObjects.MondayActlSales;
				else
					Date0 = objDailyTradingStatementObjects.MondayActlSales;
			if (Value.equalsIgnoreCase("LYValue"))
				Date0 = objDailyTradingStatementObjects.MondayLYSales;
			WebElement Xpath = driver.findElement(By.xpath(Date0.replace("dynamic", "" + j)));
			return Xpath;
		}
		if (xpathValue.equalsIgnoreCase("Date1")) {
			String Element = objDailyTradingStatementObjects.TuesdayActlSales.replace("dynamic", "" + j);
			String Element1 = objDailyTradingStatementObjects.TuesdayActlSales.replace("dynamic", "" + j);
			if (Value.equalsIgnoreCase("PlannedSales"))
				Date1 = objDailyTradingStatementObjects.TuesdayPlndSales;
			if (Value.equalsIgnoreCase("ActualSales"))
				if (isElementPresent(Element1))
					Date1 = objDailyTradingStatementObjects.TuesdayActlSales;
				else
					Date1 = objDailyTradingStatementObjects.TuesdayActlSalesYellow;
			if (Value.equalsIgnoreCase("LYSales"))
				Date1 = objDailyTradingStatementObjects.TuesdayLYSales;
			if (Value.equalsIgnoreCase("PlannedValue"))
				Date1 = objDailyTradingStatementObjects.TuesdayPlndSales;
			if (Value.equalsIgnoreCase("PlannedValueOR"))
				Date1 = objDailyTradingStatementObjects.TuesdayPlndSales;
			if (Value.equalsIgnoreCase("ActualValueOR"))
				Date1 = objDailyTradingStatementObjects.TuesdayActlSales;
			if (Value.equalsIgnoreCase("ActualValue"))
				if (isElementPresent(Element))
					Date1 = objDailyTradingStatementObjects.TuesdayActlSales;
				else
					Date1 = objDailyTradingStatementObjects.TuesdayActlSales;
			if (Value.equalsIgnoreCase("LYValue"))
				Date1 = objDailyTradingStatementObjects.TuesdayLYSales;
			WebElement Xpath = driver.findElement(By.xpath(Date1.replace("dynamic", "" + j)));
			return Xpath;
		}
		if (xpathValue.equalsIgnoreCase("Date2")) {
			String Element = objDailyTradingStatementObjects.WednesdayActlSales.replace("dynamic", "" + j);
			String Element1 = objDailyTradingStatementObjects.WednesdayActlSales.replace("dynamic", "" + j);
			if (Value.equalsIgnoreCase("PlannedSales"))
				Date2 = objDailyTradingStatementObjects.WednesdayPlndSales;
			if (Value.equalsIgnoreCase("ActualSales"))
				if (isElementPresent(Element1))
					Date2 = objDailyTradingStatementObjects.WednesdayActlSales;
				else
					Date2 = objDailyTradingStatementObjects.WednesdayActlSalesYellow;
			if (Value.equalsIgnoreCase("LYSales"))
				Date2 = objDailyTradingStatementObjects.WednesdayLYSales;
			if (Value.equalsIgnoreCase("PlannedValue"))
				Date2 = objDailyTradingStatementObjects.WednesdayPlndSales;
			if (Value.equalsIgnoreCase("PlannedValueOR"))
				Date2 = objDailyTradingStatementObjects.WednesdayPlndSales;
			if (Value.equalsIgnoreCase("ActualValueOR"))
				Date2 = objDailyTradingStatementObjects.WednesdayActlSales;
			if (Value.equalsIgnoreCase("ActualValue"))
				if (isElementPresent(Element))
					Date2 = objDailyTradingStatementObjects.WednesdayActlSales;
				else
					Date2 = objDailyTradingStatementObjects.WednesdayActlSales;
			if (Value.equalsIgnoreCase("LYValue"))
				Date2 = objDailyTradingStatementObjects.WednesdayLYSales;
			WebElement Xpath = driver.findElement(By.xpath(Date2.replace("dynamic", "" + j)));
			return Xpath;
		}
		if (xpathValue.equalsIgnoreCase("Date3")) {
			String Element = objDailyTradingStatementObjects.ThursdayActlSales.replace("dynamic", "" + j);
			String Element1 = objDailyTradingStatementObjects.ThursdayPlndSales.replace("dynamic", "" + j);
			String Element2 = objDailyTradingStatementObjects.ThursdayActlSales.replace("dynamic", "" + j);
			if (Value.equalsIgnoreCase("PlannedSales"))
				if (isElementPresent(Element1))
					Date3 = objDailyTradingStatementObjects.ThursdayPlndSales;
				else
					Date3 = objDailyTradingStatementObjects.ThursdayPlndSalesWhite;
			if (Value.equalsIgnoreCase("ActualSales"))
				if (isElementPresent(Element2))
					Date3 = objDailyTradingStatementObjects.ThursdayActlSales;
				else
					Date3 = objDailyTradingStatementObjects.ThursdayActlSalesYellow;
			if (Value.equalsIgnoreCase("LYSales"))
				Date3 = objDailyTradingStatementObjects.ThursdayLYSales;
			if (Value.equalsIgnoreCase("PlannedValue"))
				Date3 = objDailyTradingStatementObjects.ThursdayPlndSales;
			if (Value.equalsIgnoreCase("PlannedValueOR"))
				Date3 = objDailyTradingStatementObjects.ThursdayPlndSales;
			if (Value.equalsIgnoreCase("ActualValueOR"))
				Date3 = objDailyTradingStatementObjects.ThursdayActlSales;
			if (Value.equalsIgnoreCase("ActualValue"))
				if (isElementPresent(Element))
					Date3 = objDailyTradingStatementObjects.ThursdayActlSales;
				else
					Date3 = objDailyTradingStatementObjects.ThursdayActlSales;
			if (Value.equalsIgnoreCase("LYValue"))
				Date3 = objDailyTradingStatementObjects.ThursdayLYSales;
			WebElement Xpath = driver.findElement(By.xpath(Date3.replace("dynamic", "" + j)));
			return Xpath;
		}
		if (xpathValue.equalsIgnoreCase("Date4")) {
			String Element = objDailyTradingStatementObjects.FridayActlSales.replace("dynamic", "" + j);
			String Element1 = objDailyTradingStatementObjects.FridayPlndSales.replace("dynamic", "" + j);
			String Element2 = objDailyTradingStatementObjects.FridayActlSales.replace("dynamic", "" + j);
			if (Value.equalsIgnoreCase("PlannedSales"))
				if (isElementPresent(Element1))
					Date4 = objDailyTradingStatementObjects.FridayPlndSales;
				else
					Date4 = objDailyTradingStatementObjects.FridayPlndSalesWhite;
			if (Value.equalsIgnoreCase("ActualSales"))
				if (isElementPresent(Element2))
					Date4 = objDailyTradingStatementObjects.FridayActlSales;
				else
					Date4 = objDailyTradingStatementObjects.FridayActlSalesYellow;
			if (Value.equalsIgnoreCase("LYSales"))
				Date4 = objDailyTradingStatementObjects.FridayLYSales;
			if (Value.equalsIgnoreCase("PlannedValue"))
				Date4 = objDailyTradingStatementObjects.FridayPlndSales;
			if (Value.equalsIgnoreCase("PlannedValueOR"))
				Date4 = objDailyTradingStatementObjects.FridayPlndSales;
			if (Value.equalsIgnoreCase("ActualValueOR"))
				Date4 = objDailyTradingStatementObjects.FridayActlSales;
			if (Value.equalsIgnoreCase("ActualValue"))
				if (isElementPresent(Element))
					Date4 = objDailyTradingStatementObjects.FridayActlSales;
				else
					Date4 = objDailyTradingStatementObjects.FridayActlSales;
			if (Value.equalsIgnoreCase("LYValue"))
				Date4 = objDailyTradingStatementObjects.FridayLYSales;
			WebElement Xpath = driver.findElement(By.xpath(Date4.replace("dynamic", "" + j)));
			return Xpath;
		}
		if (xpathValue.equalsIgnoreCase("Date5")) {
			String Element = objDailyTradingStatementObjects.SaturdayActlSales.replace("dynamic", "" + j);
			String Element1 = objDailyTradingStatementObjects.SaturdayPlndSales.replace("dynamic", "" + j);
			String Element2 = objDailyTradingStatementObjects.SaturdayActlSales.replace("dynamic", "" + j);
			if (Value.equalsIgnoreCase("PlannedSales"))
				if (isElementPresent(Element1))
					Date5 = objDailyTradingStatementObjects.SaturdayPlndSales;
				else
					Date5 = objDailyTradingStatementObjects.SaturdayPlndSalesWhite;
			if (Value.equalsIgnoreCase("ActualSales"))
				if (isElementPresent(Element2))
					Date5 = objDailyTradingStatementObjects.SaturdayActlSales;
				else
					Date5 = objDailyTradingStatementObjects.SaturdayActlSalesYellow;
			if (Value.equalsIgnoreCase("LYSales"))
				Date5 = objDailyTradingStatementObjects.SaturdayLYSales;
			if (Value.equalsIgnoreCase("PlannedValue"))
				Date5 = objDailyTradingStatementObjects.SaturdayPlndSales;
			if (Value.equalsIgnoreCase("PlannedValueOR"))
				Date5 = objDailyTradingStatementObjects.SaturdayPlndSales;
			if (Value.equalsIgnoreCase("ActualValueOR"))
				Date5 = objDailyTradingStatementObjects.SaturdayActlSales;
			if (Value.equalsIgnoreCase("ActualValue"))
				if (isElementPresent(Element))
					Date5 = objDailyTradingStatementObjects.SaturdayActlSales;
				else
					Date5 = objDailyTradingStatementObjects.SaturdayActlSales;
			if (Value.equalsIgnoreCase("LYValue"))
				Date5 = objDailyTradingStatementObjects.SaturdayLYSales;
			WebElement Xpath = driver.findElement(By.xpath(Date5.replace("dynamic", "" + j)));
			return Xpath;
		}
		if (xpathValue.equalsIgnoreCase("Date6")) {
			String Element = objDailyTradingStatementObjects.SundayActlSales.replace("dynamic", "" + j);
			String Element1 = objDailyTradingStatementObjects.SundayPlndSales.replace("dynamic", "" + j);
			String Element2 = objDailyTradingStatementObjects.SundayActlSales.replace("dynamic", "" + j);
			if (Value.equalsIgnoreCase("PlannedSales"))
				if (isElementPresent(Element1))
					Date6 = objDailyTradingStatementObjects.SundayPlndSales;
				else
					Date6 = objDailyTradingStatementObjects.SundayPlndSalesWhite;
			if (Value.equalsIgnoreCase("ActualSales"))
				if (isElementPresent(Element2))
					Date6 = objDailyTradingStatementObjects.SundayActlSales;
				else
					Date6 = objDailyTradingStatementObjects.SundayActlSalesYellow;
			if (Value.equalsIgnoreCase("LYSales"))
				Date6 = objDailyTradingStatementObjects.SundayLYSales;
			if (Value.equalsIgnoreCase("PlannedValue"))
				Date6 = objDailyTradingStatementObjects.SundayPlndSales;
			if (Value.equalsIgnoreCase("PlannedValueOR"))
				Date6 = objDailyTradingStatementObjects.SundayPlndSales;
			if (Value.equalsIgnoreCase("ActualValueOR"))
				Date6 = objDailyTradingStatementObjects.SundayActlSales;
			if (Value.equalsIgnoreCase("ActualValue"))
				if (isElementPresent(Element))
					Date6 = objDailyTradingStatementObjects.SundayActlSales;
				else
					Date6 = objDailyTradingStatementObjects.SundayActlSales;
			if (Value.equalsIgnoreCase("LYValue"))
				Date6 = objDailyTradingStatementObjects.SundayLYSales;
			WebElement Xpath = driver.findElement(By.xpath(Date6.replace("dynamic", "" + j)));
			return Xpath;
		}
		return null;
	}

	public static String captureScreenshot(WebDriver driver) {

		File scrFile = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);
		String imgName = new SimpleDateFormat("yyyy_MM_dd_HH_mm_ss").format(new Date());
		String ssLoc = TestContext.getStringProperty("reportPath") + "//screenshots//" + imgName + ".png";
		try {

			FileUtils.copyFile(scrFile, new File(ssLoc));

		} catch (IOException e) {

			e.printStackTrace();
		}
		ssLoc = "./" + "Screenshots//" + imgName + ".png";
		return ssLoc;
	}

	// TakeScreenShot
	public String TakeScreenshot(ExtentTest extentTest, String ScreenShotName) {
		try {
			String Screenshot_Path = ExtentReportsScreenshotCode.CaptureScreenshot(driver, ScreenShotName);
			String image = extentTest.addScreenCapture(Screenshot_Path.replace("/Reports", ""));
			return image;

		} catch (Exception e) {
			System.out.println("Exception Occured while taking Screenshot" + e.getMessage());
		}
		return null;
	}

	public void LogOFF(WeeklyPlanningStoreObjects objWeeklyPlanningStoreObjects) throws IOException {
		try {
			if (isDisplayed(objWeeklyPlanningStoreObjects.Logoff)) {
				click(objWeeklyPlanningStoreObjects.Logoff);
				Report_AddStep("testcase", "Logged Out of Application Successfully", "", "", "Pass");
			} else {
				Report_AddStep("testcase", "Failed to Log OFF Application", "", "", "Fail");
			}
		} catch (Exception e) {
			extentTest.log(LogStatus.FAIL, "Failed to Log OFF Application");
			Report_AddStep("testcase", "Failed to Log OFF Application", "", "", "Fail");
		}
	}

	public static void htmlToExtent(String cName, String mName, ExtentTest extLogger, WebDriver driver, String value) {
		String[] valueList = value.split(";");
		String desc = "";
		if (!valueList[1].isEmpty()) {
			desc = valueList[0] + "<br />Db Value: <br />" + valueList[1] + "<br />Application Value: <br />"
					+ valueList[2];
		} else {
			desc = valueList[0];
		}
		System.out.println("Test Value : " + valueList[3]);
		writeExtentReportStep1(valueList[3], desc, methodName, extLogger, cName, mName, driver);

	}

	public static Multimap<String, String> writeExtentReportStep1(String status, String desc, String methodName,
			ExtentTest extLogger, String cName, String mName, WebDriver driver) {
		if (status.equalsIgnoreCase("Pass")) {
			extLogger.log(LogStatus.PASS, desc);
		} else if (status.equalsIgnoreCase("Warning")) {
			extLogger.log(LogStatus.WARNING, desc);
			if (extentList.get(cName).isEmpty()) {
				System.out.println("Arun Class " + cName);
				extentList.put(cName, mName);
			} else if (!checkList(extentList.get(cName), mName)) {
				extentList.put(cName, mName);
			}
		} else if (status.equalsIgnoreCase("Fail")) {
			extLogger.log(LogStatus.FAIL, desc + extLogger.addScreenCapture(captureScreenshot(driver)));
			if (extentList.get(cName).isEmpty()) {
				extentList.put(cName, mName);
			} else if (!checkList(extentList.get(cName), mName)) {
				extentList.put(cName, mName);
			}
		} else if (status.equalsIgnoreCase("INFO")) {
			if (desc.equals("Iteration terminated due to error")) {
			} else {
				extLogger.log(LogStatus.INFO, desc);
			}
		}
		return extentList;
	}

	public static boolean checkList(Collection<String> collection, String searchValue) {
		for (String str : collection) {
			if (str.trim().contains(searchValue))
				return true;
		}
		return false;
	}
}
