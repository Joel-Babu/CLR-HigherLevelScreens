package au.wow.WLPmain.objects;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class DTS_DepartmentObjects {

	@FindBy(xpath="//*[@id='dispForm']/table[2]/tbody/tr/td")
	public WebElement DepartmentHeader;
	
	@FindBy(xpath= "//*[@id='form1:secret1']")
	public WebElement Password;
	
	@FindBy(xpath="//*[@id='form1:button1']")
	public WebElement SubmitBtn;
	
	@FindBy(xpath= "//img[@id='form1:clrtextimg']")
	public WebElement ApplicationName;
	
	//WeeklyPlanningStore
	@FindBy(xpath= "/html/frameset/frame[1]")
	public WebElement TitleFrame;
	
	@FindBy(xpath= "//div[@class='clsLabel']/span")
	public WebElement LoggedUserName;
	
	@FindBy(xpath= "//*[@id='form1:label1']")
	public WebElement StoreNbrTitle;
	
	@FindBy(xpath= "//*[@id='dispForm:menu3']")
	public WebElement DepartmentDropdown;
	
	@FindBy(xpath= "//*[@id='form1:label12']")
	public WebElement WeekTitle;
	
	@FindBy(xpath= "//*[@id='form1:menu1']")
	public WebElement WeekSelectionDropDown;	
	
	@FindBy(xpath= "//*[@id='form1:label15']")
	public WebElement PageTitle;
	
	@FindBy(xpath= "//*[@id='form1:menu2']")
	public WebElement PageSelectionDropDown;
	
	@FindBy(xpath= "//*[@id='form1:menu2']/option[i]")
	public WebElement PageDropdownVal;
	
	@FindBy(xpath= "//*[@id='form1:linkGo']")
	public WebElement GoButton;
	
	//HistoryTab	
	@FindBy(xpath= "/html/frameset/frame[2]") 
	public WebElement DataFrame;
	
	@FindBy(xpath= "//*[@id='form1:buttonLogoff']")
	public WebElement Logoff;
	
	
	
	
	

	public static final String Days="//td[contains(@id,'dispForm:tab1:dynamic:') and contains(@id,'j_idt')][1]";
	public static final String Total="//td[contains(@id,'dispForm:tab1:j_idt') and contains(@style,'bold')][1]"; //Total Week Text
	
	//Sales
	public static final String TableRowsCount="//table[@id='dispForm:tab1']/tbody/tr";
	public static final String Weekday="//td[contains(@id,'dispForm:tab1:dynamic:') and contains(@id,'j_idt')][1]";	//Not Required - Duplicate
	public static final String PlannedSales = "//td[contains(@id,'dispForm:tab1:dynamic:') and contains(@id,'j_idt')][2]";
	public static final String PlannedSalesGreen="//td[contains(@id,'dispForm:tab1:dynamic:') and contains(@id,'j_idt')][2]";	//Not Required - Duplicate
	public static final String ActualSales = "//td[contains(@id,'dispForm:tab1:dynamic:') and contains(@id,'j_idt')][3]";
	public static final String VarSales="//td[contains(@id,'dispForm:tab1:dynamic:') and contains(@id,'j_idt')][4]";
	public static final String pertoLYSales="//td[contains(@id,'dispForm:tab1:dynamic:') and contains(@id,'j_idt')][5]";
	public static final String DailyMixSales="//td[contains(@id,'dispForm:tab1:dynamic:') and contains(@id,'j_idt')][6]";
	
	//Planned Hours
	public static final String PlanSMS="//td[contains(@id,'dispForm:tab1:dynamic:') and contains(@id,'j_idt')][7]";
	public static final String KronosScheduledHours="//td[contains(@id,'dispForm:tab1:dynamic:') and contains(@id,'j_idt')][8]";
	public static final String VARHours="//td[contains(@id,'dispForm:tab1:dynamic:') and contains(@id,'j_idt')][9]";
	public static final String OperatingRatioPlannedHours="//td[contains(@id,'dispForm:tab1:dynamic:') and contains(@id,'j_idt')][10]";
	
	//Actual Hours
	public static final String ActualSMS="//td[contains(@id,'dispForm:tab1:dynamic:') and contains(@id,'j_idt')][11]";
	public static final String KronsActualHours="//td[contains(@id,'dispForm:tab1:dynamic:') and contains(@id,'j_idt')][12]";
	public static final String VARActualHours="//td[contains(@id,'dispForm:tab1:dynamic:') and contains(@id,'j_idt')][13]";
	public static final String ORHours="//td[contains(@id,'dispForm:tab1:dynamic:') and contains(@id,'j_idt')][14]";
	
	//Items
	public static final String PlanItems="//td[contains(@id,'dispForm:tab1:dynamic:') and contains(@id,'j_idt')][15]";
	public static final String ActualItems="//td[contains(@id,'dispForm:tab1:dynamic:') and contains(@id,'j_idt')][16]";
	public static final String VARItems="//td[contains(@id,'dispForm:tab1:dynamic:') and contains(@id,'j_idt')][17]";
	
	//Customers
	public static final String PlanCustomers="//td[contains(@id,'dispForm:tab1:dynamic:') and contains(@id,'j_idt')][18]";
	public static final String ActualCustomers="//td[contains(@id,'dispForm:tab1:dynamic:') and contains(@id,'j_idt')][19]";
	public static final String VARCustomers="//td[contains(@id,'dispForm:tab1:dynamic:') and contains(@id,'j_idt')][20]";
	public static final String PlanCustomersEmpty="//td[contains(@id,'dispForm:tab1:dynamic:') and contains(@id,'j_idt')][18]";	//Not Required - Duplicate
	public static final String ActualCustomersEmpty="//td[contains(@id,'dispForm:tab1:dynamic:') and contains(@id,'j_idt')][19]";	//Not Required - Duplicate
	public static final String VARCustomersEmpty="//td[contains(@id,'dispForm:tab1:dynamic:') and contains(@id,'j_idt')][20]";	//Not Required - Duplicate
	
	//Wages
	public static final String PlanWage="//td[contains(@id,'dispForm:tab1:dynamic:') and contains(@id,'j_idt')][21]";
	public static final String ActualWage="//td[contains(@id,'dispForm:tab1:dynamic:') and contains(@id,'j_idt')][22]";
	public static final String VARPlanVsActualWage="//td[contains(@id,'dispForm:tab1:dynamic:') and contains(@id,'j_idt')][23]";
	public static final String PlanpercentWages="//td[contains(@id,'dispForm:tab1:dynamic:') and contains(@id,'j_idt')][24]";
	public static final String ActualpercentWages="//td[contains(@id,'dispForm:tab1:dynamic:') and contains(@id,'j_idt')][25]";
	
	//Sales Total
	public static final String PlannedSalesTot="//td[contains(@id,'dispForm:tab1:j_idt') and contains(@class,'dtsTotal')][1]";
	public static final String ActualSalesTot="//td[contains(@id,'dispForm:tab1:j_idt') and contains(@class,'dtsTotal')][2]";
	public static final String VARSalesTot="//td[contains(@id,'dispForm:tab1:j_idt') and contains(@class,'dtsTotal')][3]";
	public static final String PerToLYSalesTot="//td[contains(@id,'dispForm:tab1:j_idt') and contains(@class,'dtsTotal')][4]";
	public static final String DailyMixTot="//td[contains(@id,'dispForm:tab1:j_idt') and contains(@class,'dtsDailyMix')]";
	
	//Planned Hours Total
	public static final String PlanSMSHoursTot="//td[contains(@id,'dispForm:tab1:j_idt') and contains(@class,'dtsTotal')][5]";
	public static final String KronosSchedHoursTot="//td[contains(@id,'dispForm:tab1:j_idt') and contains(@class,'dtsTotal')][6]";
	public static final String VARHoursTot="//td[contains(@id,'dispForm:tab1:j_idt') and contains(@class,'dtsTotal')][7]";
	public static final String OperatingRatioPlannedHoursTot="//td[contains(@id,'dispForm:tab1:j_idt') and contains(@class,'dtsTotal')][8]";
	
	//Actual Hours Total
	public static final String ActualSMSHoursTot="//td[contains(@id,'dispForm:tab1:j_idt') and contains(@class,'dtsTotal')][9]";
	public static final String KronosActlHoursTot="//td[contains(@id,'dispForm:tab1:j_idt') and contains(@class,'dtsTotal')][10]";
	public static final String VARActlHoursTot="//td[contains(@id,'dispForm:tab1:j_idt') and contains(@class,'dtsTotal')][11]";
	public static final String ORActlHoursTot="//td[contains(@id,'dispForm:tab1:j_idt') and contains(@class,'dtsTotal')][12]";
	
	//Items Total
	public static final String PlanItemsTot="//td[contains(@id,'dispForm:tab1:j_idt') and contains(@class,'dtsTotal')][13]";
	public static final String ActlItemsTot="//td[contains(@id,'dispForm:tab1:j_idt') and contains(@class,'dtsTotal')][14]";
	public static final String VARItemsTot="//td[contains(@id,'dispForm:tab1:j_idt') and contains(@class,'dtsTotal')][15]";
	
	//Customers Total
	public static final String PlanCustTot="//td[contains(@id,'dispForm:tab1:j_idt') and contains(@class,'dtsTotal')][16]";
	public static final String ActlCustTot="//td[contains(@id,'dispForm:tab1:j_idt') and contains(@class,'dtsTotal')][17]";
	public static final String VARCustTot="//td[contains(@id,'dispForm:tab1:j_idt') and contains(@class,'dtsTotal')][18]";
	public static final String PlanCustTotEmpty="//td[contains(@id,'dispForm:tab1:j_idt') and contains(@class,'dtsTotal')][16]";
	public static final String ActlCustTotEmpty="//td[contains(@id,'dispForm:tab1:j_idt') and contains(@class,'dtsTotal')][17]";
	public static final String VARCustTotEmpty="//td[contains(@id,'dispForm:tab1:j_idt') and contains(@class,'dtsTotal')][18]";

	//Wages Total
	public static final String PlanWagesTot="//td[contains(@id,'dispForm:tab1:j_idt') and contains(@class,'dtsTotal')][19]";
	public static final String ActualWagesTot="//td[contains(@id,'dispForm:tab1:j_idt') and contains(@class,'dtsTotal')][20]";
	public static final String VARPlanVsActualWageTot="//td[contains(@id,'dispForm:tab1:j_idt') and contains(@class,'dtsTotal')][21]";
	public static final String PlanPerWagesTot="//td[contains(@id,'dispForm:tab1:j_idt') and contains(@class,'dtsTotal')][22]";
	public static final String ActualPerWagesTot="//td[contains(@id,'dispForm:tab1:j_idt') and contains(@class,'dtsTotal')][23]";
	
	//Budget
	/*public static final String BudSalesTot="//table[@id='dispForm:tab1']//tr[4]/td[contains(@id,'dispForm:tab1:j_')][2]";
	public static final String VarToBudSalesTot="//table[@id='dispForm:tab1']//tr[4]/td[contains(@id,'dispForm:tab1:j_')][3]";
	public static final String PerToBudSalesTot="//table[@id='dispForm:tab1']//tr[4]/td[contains(@id,'dispForm:tab1:j_')][4]";
	public static final String BudWagesTot="//table[@id='dispForm:tab1']//tr[4]/td[contains(@id,'dispForm:tab1:j_')][10]";
	public static final String BudPerWagesTot="//table[@id='dispForm:tab1']//tr[4]/td[contains(@id,'dispForm:tab1:j_')][11]";
	public static final String AllowedBudWagesTot="//table[@id='dispForm:tab1']//tr[4]/td[contains(@id,'dispForm:tab1:j_')][12]"; */
	
	public static final String BudSalesTot="//td[@id='dispForm:tab1:j_idt539']";
	public static final String VarToBudSalesTot="//td[@id='dispForm:tab1:j_idt543']";
	public static final String PerToBudSalesTot="//td[@id='dispForm:tab1:j_idt547']";
	public static final String BudWagesTot="//td[@id='dispForm:tab1:j_idt583']";
	public static final String BudPerWagesTot="//td[@id='dispForm:tab1:j_idt587']";
	public static final String AllowedBudWagesTot="//td[@id='dispForm:tab1:j_idt591']";
	
	//Forecast
	public static final String FcstSalesTot="//td[@id='dispForm:tab1:j_idt551']";	//Not Required - Duplicate
	public static final String VarToFcstSalesTot="//td[@id='dispForm:tab1:j_idt555']";	//Not Required - Duplicate
	public static final String PerToFcstSalesTot="//td[@id='dispForm:tab1:j_idt559']";	//Not Required - Duplicate
	public static final String FcstWagesTot="//td[@id='dispForm:tab1:j_idt595']";	//Not Required - Duplicate
	public static final String FcstPerWagesTot="//td[@id='dispForm:tab1:j_idt599']";	//Not Required - Duplicate
	public static final String AllowedFcstWagesTot="//td[@id='dispForm:tab1:j_idt603']";	//Not Required - Duplicate

	public static final String RowNameTotal="//td[contains(@id,'dispForm:tab1:j_idt') and contains(@style,'bold')][1]"; //Total Week Text //Not Required - Duplicate
	
	/// PST 71 ////	
	public static final String PRTableRowsCount="//*[@id=\"dispForm:tab2:tb\"]/tr";	
	public static final String PRDays="//td[contains(@id,'dispForm:tab2:dynamic:j_id_jsp_274517371_')][1]";	//Week Column
	public static final String PRRowNameTotal="//td[contains(@id,'dispForm:tab2:j_id_jsp_274517371_')][1]";	//Total Week Text
	
	public static final String PRActualSMS="//td[contains(@id,'dispForm:tab2:dynamic:j_id_jsp_274517371_')][2]";
	public static final String PRKronsActualHours="//td[contains(@id,'dispForm:tab2:dynamic:j_id_jsp_274517371_')][3]";
	public static final String PRORHours="//td[contains(@id,'dispForm:tab2:dynamic:j_id_jsp_274517371_')][4]";
	
	public static final String PRActualSMSTot="//td[contains(@id,'dispForm:tab2:j_id_jsp_274517371_')][2]";
	public static final String PRKronsActualHoursTot="//td[contains(@id,'dispForm:tab2:j_id_jsp_274517371_')][3]";
	public static final String PRORHoursTot="//td[contains(@id,'dispForm:tab2:j_id_jsp_274517371_')][4]";
	

}