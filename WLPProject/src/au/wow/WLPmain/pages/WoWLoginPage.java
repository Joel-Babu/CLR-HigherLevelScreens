/**
 * This class represents the loginpage of stores central application
 */

package au.wow.WLPmain.pages;

import java.io.IOException;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.testng.SkipException;

import au.wow.WLPmain.objects.WeeklyPlanningStoreObjects;
import au.wow.WLPmain.tests.DailyDeptMainClass;
import au.wow.wlp.utils.BasePage;


public class WoWLoginPage extends BasePage{
		
	@FindBy(xpath = "//input[@class = 'clsLabel' and @id ='form1:text1']")
	private WebElement txtUserName;
	
	@FindBy(xpath = "//input[@class = 'clsLabel' and @id ='form1:secret1']")
	private WebElement txtPassword;
	
	@FindBy(xpath = "//input[@id ='form1:button1']")
	private WebElement btnLogin;
	
//	textbox verifyNm
	@FindBy(xpath = "//input[contains(@class,'verifyNm')]")
	private WebElement txtStore;
	
	@FindBy(xpath = "//label[contains(@class,'verify')]")
	private WebElement btnVerify;
	
	@FindBy(id = "yes")
	private WebElement btnYes;
	
	@FindBy(xpath = "//a[contains(@class,'globalLinkAccountName')]")
	private WebElement gblMenuItem;
	
	@FindBy(linkText = "Logout")
	private WebElement lnkLogout;
	
	@FindBy(id = "dialog-passwordReminder")
	private WebElement notificationPopup;
	
	@FindBy(xpath = "//label[text()='No']")
	private WebElement btnNo;
	
	@FindBy(xpath= "//*[@id='form1:buttonLogoff']")
	public WebElement Logoff;
	
	WeeklyPlanningStoreObjects objWeeklyPlanningStoreObjects;
	WeeklyPlanningStorePage pageWeeklyPlanningPO;
	
	/**
	 * Enters the username and password 
	 * @throws Exception 
	 */
	public void enterUserCredentials() throws Exception
	{		

			getLogger().info("Username: " + getContext().getStringProperty("username3"));
			enterText(txtUserName, getContext().getStringProperty("username3"));
			enterText(txtPassword, getContext().getStringProperty("password3"));
			click(btnLogin);
			waitFor(1);		
	}
	
	public void enterUserCredentials_GroupManager() throws Exception
	{		

			getLogger().info("Username: " + getContext().getStringProperty("username3"));
			enterText(txtUserName, getContext().getStringProperty("username3"));
			enterText(txtPassword, getContext().getStringProperty("password3"));
			click(btnLogin);
			waitFor(1);		
	}
	
	public void enterUserCredential_StateManagerLogin() throws Exception
	{		

			getLogger().info("Username: " + getContext().getStringProperty("username1"));
			enterText(txtUserName, getContext().getStringProperty("username1"));
			enterText(txtPassword, getContext().getStringProperty("password1"));
			click(btnLogin);
			waitFor(1);		
	}
	
	public void enterUserCredential_WOWGroupLogin() throws Exception
	{		

			getLogger().info("Username: " + getContext().getStringProperty("username2"));
			enterText(txtUserName, getContext().getStringProperty("username2"));
			enterText(txtPassword, getContext().getStringProperty("password2"));
			click(btnLogin);
			waitFor(1);		
	}
	public void enterUserCredential_CopyDataToNewStore() throws Exception
	{		

			getLogger().info("Username: " + getContext().getStringProperty("username4"));
			enterText(txtUserName, getContext().getStringProperty("username4"));
			enterText(txtPassword, getContext().getStringProperty("password4"));
			click(btnLogin);
			waitFor(1);		
	}
	
	/**
	 * Inputs the store number
	 * @param storenum
	 */
	public void enterStoreNo(String storenum){
		
		//new Actions(driver).moveToElement(txtStore).perform();
		//waitFor(1);
		//waitFor(2);
		try{
			waitForElement(txtStore);
			
			enterElmentUsingJs(txtStore, storenum);
		}catch(Exception ex)
		{
			System.out.println("Exception : "+ex);
			enterElmentUsingJs(txtStore, storenum);
		}
		//txtStore.sendKeys(storenum);
		waitForElement(btnVerify);
		btnVerify.click();
		waitForElement(btnYes);
		btnYes.click();
	}
	
	
	
	/**
	 * Login into application and navigate to promotion search page	
	 * @param storenum
	 * @return PromotionSearchPage
	 * @throws Exception 
	 */
	public DailyPlanningMainPage navToDailyDept(String storenum) throws Exception{
		
		if(isDisplayed(Logoff))
		{
			LogOFF(objWeeklyPlanningStoreObjects);
		}
		else
		{
			enterUserCredentials();
			waitForPageToLoad();
			waitFor(1);
		}
			
		return createPage(DailyPlanningMainPage.class);
	}
	
	public WeeklyPlanningStorePage navToWeeklyPlanningStorePg(String storenum) throws Exception
	{
		enterUserCredentials();
		waitForPageToLoad();
		waitFor(1);	
		return createPage(WeeklyPlanningStorePage.class);
	}
	
	//Group 
	public WeeklyPlanningStorePage navToWeeklyPlanningGroupPg(String storenum) throws Exception
	{
		enterUserCredentials_GroupManager();
		waitForPageToLoad();
		waitFor(1);	
		return createPage(WeeklyPlanningStorePage.class);
	}
	
	// State Manager
	public WeeklyPlanningStorePage navToStatePage(String storenum) throws Exception
	{
		enterUserCredential_StateManagerLogin();
		waitForPageToLoad();
		waitFor(1);	
		return createPage(WeeklyPlanningStorePage.class);
	}
	
	// WOW Group Manager
	public WeeklyPlanningStorePage navToWOWGroupPage(String storenum) throws Exception
	{
		enterUserCredential_WOWGroupLogin();
		waitForPageToLoad();
		waitFor(1);	
		return createPage(WeeklyPlanningStorePage.class);
	}
	//
	
	// Copy Data To New Store Credentials/Admin Credentials
	public WeeklyPlanningStorePage navToCopyDataToNewStore(String storenum) throws Exception
	{
		enterUserCredential_CopyDataToNewStore();
		waitForPageToLoad();
		waitFor(1);	
		return createPage(WeeklyPlanningStorePage.class);
	}
	/**
	 * Validates the login page of the application
	 */
	public void validatePage(){
		waitForPageToLoad();
		waitForPage("Login to Store Central");
		getLogger().info("Title of the page: " + getDriver().getTitle());
		if(getDriver().getTitle().trim().contentEquals("Login to Store Central")){
			getLogger().info("Application is launched successfully");
			
		}else
			throw new SkipException("Error in launching the application");
	}
	
	/**
	 * Logout from the application
	 * @throws IOException 
	 */
	public void logoutFromAppl() throws IOException{
		scrolltoElement(gblMenuItem);
		((JavascriptExecutor)getDriver()).executeScript("window.scrollBy(0,-150)");
		Actions action = new Actions(getDriver());
		action.moveToElement(gblMenuItem).click().perform();
		waitForElement(lnkLogout);
		click(lnkLogout);
		Report_AddStep("testcase", "---------------Logged out of Application successfully--------------------","","", "Pass");
		
	}

	

	public WeeklyPlanningStorePage navToWeeklyPlanningPage(String stringProperty) throws Exception
	
	{
		enterUserCredentials();
		waitForJSandJQueryToLoad();
		enterStoreNo(getContext().getStringProperty(store));
		waitForJSandJQueryToLoad();
		if (notificationPopup.isDisplayed()){
			btnNo.click();
		}	
		return createPage(WeeklyPlanningStorePage.class);
	}

	

}
