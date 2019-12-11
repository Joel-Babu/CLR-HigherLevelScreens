package au.wow.WLPmain.objects;

import java.util.List;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class WeeklyPlanningStoreObjects {
	
	//Loing Page
	@FindBy(xpath="//*[@id='form1:text1']")
	public WebElement UserName;
	
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
	
	@FindBy(xpath= "//*[@id='form1:text1']")
	public WebElement StoreNbrTxtBox;
	
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
	
	@FindBy(xpath= "//*[@id='frmDetails:ctrStorePlanningDetailsTable:lblHistoryType']")
	public WebElement HistoryViewTitle;
	
	@FindBy(xpath= "//*[@id='frmDetails:ctrStorePlanningDetailsTable:cboHistoryType']")
	public WebElement HistoryViewDropdown;
	

	@FindBy(xpath= "//*[@id='frmDetails:ctrStorePlanningDetailsTable:cboLocation']")
	public WebElement RegionViewDropdown;
	
	@FindBy(xpath= "//*[@id='frmDetails:lblHistoryType']")
	public WebElement MPHistoryViewTitle;
	
	@FindBy(xpath= "//*[@id='frmDetails:cboHistoryType']")
	public WebElement MPHistoryViewDropdown;


	@FindBy(xpath= "//*[@id='frmDetails:ctrStorePlanningDetailsTable:12:oOrgDescinLink']")
	public WebElement CheckOutDeptElement;
					
	@FindBy(xpath= "//*[@id='frmDetails:ctrStorePlanningDetailsTable:12:oWeek1HistData11']")
	public WebElement CheckOutDeptWeek1;
	
	@FindBy(xpath= "//*[@id='frmDetails:ctrStorePlanningDetailsTable:12:oWeek2HistData22']")
	public WebElement CheckOutDeptWeek2;
	
	@FindBy(xpath= "//*[@id='frmDetails:ctrStorePlanningDetailsTable:12:oWeek3HistData33']")
	public WebElement CheckOutDeptWeek3;
	
	@FindBy(xpath= "//*[@id='frmDetails:ctrStorePlanningDetailsTable:12:oWeek4HistData44']")
	public WebElement CheckOutDeptWeek4;
					
	@FindBy(xpath= "//*[@id='frmDetails:ctrStorePlanningDetailsTable:14:oWeek1HistData11']")
	public WebElement StrTtlDeptWeek1;
	
	@FindBy(xpath= "//*[@id='frmDetails:ctrStorePlanningDetailsTable:14:oWeek2HistData22']")
	public WebElement StrTtlDeptWeek2;
	
	@FindBy(xpath= "//*[@id='frmDetails:ctrStorePlanningDetailsTable:14:oWeek3HistData33']")
	public WebElement StrTtlDeptWeek3;
	
	@FindBy(xpath= "//*[@id='frmDetails:ctrStorePlanningDetailsTable:14:oWeek4HistData44']")
	public WebElement StrTtlDeptWeek4;
	
	@FindBy(xpath= "//*[@id='form1:buttonLogoff']")
	public WebElement Logoff;
	
	//Buttons
	@FindBy(xpath= "//*[@id='frmDetails:btnStoreDailyPlanning']")
	public WebElement DailyPlanningBtn;
	
	@FindBy(xpath= "//*[@id='frmDetails:btnStoreMonthlyPlanning']")
	public WebElement MonthlyPlanningBtn;
	
	@FindBy(xpath="//*[@id='dailyForm:btnnavigateStoreWeeklyPlanning']")
	public WebElement WeeklyPlanningBtn;
	
	@FindBy(xpath="//*[@id='frmDetails:btnStoreWeeklyPlanning']")
	public WebElement WeeklyPlanningbtn_WPG;
	
	@FindBy(xpath="//*[@id='frmDetails:btnStoreDailyPlanning']")
	public WebElement DailyPlanningButton;
	
	@FindBy(xpath="//*[@id='dailyForm:btnnavigateStoreWeeklyPlanning']")
	public WebElement WeeklyPlanningButton;
	
	@FindBy(xpath="//*[@id='frmDetails:btnStoreMonthlyPlanning']")
	public WebElement DailyTradingStatementButton;
	
	public static final String MonthlyPlanningButton="//*[@id='frmDetails:btnStoreMonthlyPlanning']";	
//	public static final String DailyPlanningButton="//*[@id='frmDetails:btnStoreDailyPlanning']";
	public static final String ScreenName="//*[@id='dailyForm']/table[1]/tbody/tr/td[1]/span[1]";
	// Current Month
	@FindBy(xpath= "//*[@id='frmDetails:ctrStorePlanningDetailsTable']/tbody/tr")
	public WebElement TableRowsCount;
	public static final String DataFrameBottom="/html/frameset/frame[2]";
	
	public static final String CurrentWeekBGDT="//*[contains(@id,'frmDetails:ctrStorePlanningDetailsTable:0:oPlannedSalesDate')]";
	public static final String CurrentWeekFCST="//*[@id='frmDetails:ctrStorePlanningDetailsTable:0:oPlannedSalesDate1']";
	public static final String FirstWeekofMonth="//*[@id='frmDetails:ctrStorePlanningDetailsTable:dynamic:oWeek1HistData11']";
	public static final String SecondWeekofMonth="//*[@id='frmDetails:ctrStorePlanningDetailsTable:dynamic:oWeek2HistData22']";
	public static final String ThirdWeekofMonth="//*[@id='frmDetails:ctrStorePlanningDetailsTable:dynamic:oWeek3HistData33']";
	public static final String FourWeekofMonth="//*[@id='frmDetails:ctrStorePlanningDetailsTable:dynamic:oWeek4HistData44']";
	
	//Individual
	public static final String ForecastSales="//*[@id='frmDetails:ctrStorePlanningDetailsTable:dynamic:colForecastSales']";
	public static final String BudgetSales="//*[@id='frmDetails:ctrStorePlanningDetailsTable:dynamic:colBudgetSales']";
	public static final String GeneratedSales="//*[@id='frmDetails:ctrStorePlanningDetailsTable:dynamic:colGeneratedSales']";
	public static final String PlannedSales="//*[@id='frmDetails:ctrStorePlanningDetailsTable:dynamic:colPlannedSales']";
	public static final String VarToBudSales="//*[@id='frmDetails:ctrStorePlanningDetailsTable:dynamic:colVariance']";
	public static final String VarToFcstSales="//*[@id=\"frmDetails:ctrStorePlanningDetailsTable:dynamic:colfcstVariance\"]";
	public static final String PercentageLYSales="//*[@id='frmDetails:ctrStorePlanningDetailsTable:dynamic:colLySales']";
	public static final String PercentageBudgetSales="//*[@id='frmDetails:ctrStorePlanningDetailsTable:dynamic:colBUDPerSales']";
	public static final String PercentageForecastSales="//*[@id=\"frmDetails:ctrStorePlanningDetailsTable:dynamic:colFCSTPerSales\"]";
	public static final String PlannedItemSales="//*[@id='frmDetails:ctrStorePlanningDetailsTable:dynamic:colPlannedItemPrice']";
	public static final String PlannedItemSalesGrowth="//*[@id='frmDetails:ctrStorePlanningDetailsTable:dynamic:colPlannedIPLH']";
	
	public static final String BudgetWages="//*[@id='frmDetails:ctrStorePlanningDetailsTable:dynamic:colBudgetWageDollar']";
	public static final String ForecastWages="//*[@id=\"frmDetails:ctrStorePlanningDetailsTable:dynamic:colForecastWageDollar\"]";
	public static final String PlannedWages="//*[@id='frmDetails:ctrStorePlanningDetailsTable:dynamic:colPlannedWageCost']";
	public static final String PercentLYWages="//*[@id='frmDetails:ctrStorePlanningDetailsTable:dynamic:colLyWage']";
	public static final String LYWagePercentage="//*[@id='frmDetails:ctrStorePlanningDetailsTable:dynamic:colLyWagePercent']";
	public static final String BudgetWagesPercent="//*[@id='frmDetails:ctrStorePlanningDetailsTable:dynamic:colBudgetWagePercent']";
	public static final String ForecastWagesPercent="//*[@id='frmDetails:ctrStorePlanningDetailsTable:dynamic:colForecastWagePercent']";
	
	public static final String PlannedWagesPercent="//*[@id='frmDetails:ctrStorePlanningDetailsTable:dynamic:colPlannedWagePercent']";
	public static final String PlannedPercentageonLY="//*[@id='frmDetails:ctrStorePlanningDetailsTable:dynamic:colPlannedItemPriceGrowth']";

	public static final String SMSHours="//*[@id='frmDetails:ctrStorePlanningDetailsTable:dynamic:colSMSHours']";
	public static final String PlannedHours="//*[@id='frmDetails:ctrStorePlanningDetailsTable:dynamic:colPlannedPaidHours']";
	public static final String LYHours="//*[@id='frmDetails:ctrStorePlanningDetailsTable:dynamic:colLyHours']";
	public static final String DepartmentNames="//*[@id='frmDetails:ctrStorePlanningDetailsTable:dynamic:colOrgDesc']";
	public static final String DepartmentNamesMPWeekTable="//*[@id='frmDetails:ctrStorePlanningDetailsTable:dynamic:colOrgDesc']";
	public static final String RowNameTotal="//*[@id='frmDetails:ctrStorePlanningDetailsTable:14:colOrgDesc']";
	
	public static final String BdgtOR="//*[@id=\"frmDetails:ctrStorePlanningDetailsTable:dynamic:colPlannedbORPer\"]";
	public static final String BdgtORWeekTable="//*[@id=\"frmDetails:ctrStorePlanningDetailsTable:dynamic:colPlannedbORPer\"]";
	public static final String BdgtORTtl="//*[@id=\"frmDetails:ctrStorePlanningDetailsTable:dynamic:colPlannedbORPer\"]";
	public static final String FcstOR="//*[@id=\"frmDetails:ctrStorePlanningDetailsTable:dynamic:colPlannedfORPer\"]";
	public static final String FcstORWeekTable="//*[@id=\"frmDetails:ctrStorePlanningDetailsTable:dynamic:colPlannedfORPer\"]";
	public static final String FcstORTtl="//*[@id=\"frmDetails:ctrStorePlanningDetailsTable:dynamic:colPlannedfORPer\"]";
	public static final String PlannedOR="//*[@id='frmDetails:ctrStorePlanningDetailsTable:dynamic:colPlannedORPer']";
	public static final String BDGTRate="//*[@id='frmDetails:ctrStorePlanningDetailsTable:dynamic:colBdgtRate']";
	public static final String FCSTRate="//*[@id='frmDetails:ctrStorePlanningDetailsTable:dynamic:colFcstRate']";
	@FindBy(xpath= "//*[@id='frmDetails:ctrStorePlanningDetailsTable:12:colForecastSales']")
	public WebElement ForecastSalesCheckoutVal;
		
	@FindBy(xpath= "//*[@id='frmDetails:ctrStorePlanningDetailsTable:14:colForecastSales']")
	public WebElement ForecastSalesStrTtl;
	
	@FindBy(xpath= "//*[@id='frmDetails:ctrStorePlanningDetailsTable:12:colBudgetSales']")
	public WebElement BudgetSalesCheckoutVal;
		
	@FindBy(xpath= "//*[@id='frmDetails:ctrStorePlanningDetailsTable:14:colBudgetSales']")
	public WebElement BudgetSalesStrTtl;
	
	@FindBy(xpath= "//*[@id='frmDetails:ctrStorePlanningDetailsTable:12:colGeneratedSales']")
	public WebElement GeneratedSalesCheckoutVal;
	
	@FindBy(xpath= "//*[@id='frmDetails:ctrStorePlanningDetailsTable:14:colGeneratedSales']")
	public WebElement GeneratedSalesStrTtl;
	
	@FindBy(xpath= "//*[@id='frmDetails:ctrStorePlanningDetailsTable:12:colPlannedSales']")
	public WebElement PlannedSalesCheckoutVal;
	
	@FindBy(xpath= "//*[@id='frmDetails:ctrStorePlanningDetailsTable:14:colPlannedSales']")
	public WebElement PlannedSalesStrTtl;
	
	@FindBy(xpath= "//*[@id='frmDetails:ctrStorePlanningDetailsTable:12:colVariance']")
	public WebElement VartoBudSalesCheckoutVal;
	
	@FindBy(xpath= "//*[@id='frmDetails:ctrStorePlanningDetailsTable:14:colVariance']")
	public WebElement VartoBudSalesStrTtl;
	
	@FindBy(xpath= "//*[@id='frmDetails:ctrStorePlanningDetailsTable:12:colLySales']")
	public WebElement PercentLYSalesCheckoutVal;
	
	@FindBy(xpath= "//*[@id='frmDetails:ctrStorePlanningDetailsTable:14:colLySales']")
	public WebElement PercentLYSalesStrTtl;
	
	@FindBy(xpath= "//*[@id='frmDetails:ctrStorePlanningDetailsTable:12:colBUDPerSales']")
	public WebElement PercentBudgetSalesCheckoutVal;
	//
	@FindBy(xpath= "//*[@id='frmDetails:ctrStorePlanningDetailsTable:14:colBUDPerSales']")
	public WebElement PercentBudgetSalesStrTtl;
	
	@FindBy(xpath= "//*[@id='frmDetails:ctrStorePlanningDetailsTable:12:colPlannedItemPrice']")
	public WebElement PlndItemPriceSalesCheckoutVal;
	
	@FindBy(xpath= "//*[@id='frmDetails:ctrStorePlanningDetailsTable:14:colPlannedItemPrice']")
	public WebElement PlndItemPriceSalesStrTtl;
	
	@FindBy(xpath= "//*[@id='frmDetails:ctrStorePlanningDetailsTable:12:colPlannedIPLH']")
	public WebElement PlndItemPriceSalesGrwthCheckoutVal;
	
	@FindBy(xpath= "//*[@id='frmDetails:ctrStorePlanningDetailsTable:14:colPlannedIPLH']")
	public WebElement PlndItemPriceSalesGrwthStrTtl;
	
	@FindBy(xpath= "//*[@id='frmDetails:ctrStorePlanningDetailsTable:14:colBudgetWageDollar']")
	public WebElement BudgetWagesStrTtl;
	
	@FindBy(xpath= "//*[@id='frmDetails:ctrStorePlanningDetailsTable:14:colPlannedWageCost']")
	public WebElement PlannedWagesStrTtl;
	
	@FindBy(xpath= "//*[@id='frmDetails:ctrStorePlanningDetailsTable:14:colSMSHours']")
	public WebElement SMSHoursStrTtl;
	
	@FindBy(xpath= "//*[@id='frmDetails:ctrStorePlanningDetailsTable:14:colPlannedPaidHours']")
	public WebElement PlannedHoursStrTtl;
	
	public static final String PlannedItemPrice="//*[@id='frmDetails:ctrStorePlanningDetailsTable:dynamic:colPlannedItemPrice']";
	public static final String PlannedPercentageonLYItemPrice="//*[@id='frmDetails:ctrStorePlanningDetailsTable:dynamic:colPlannedItemPriceGrowth']";
	public static final String PlannedItems="//*[@id='frmDetails:ctrStorePlanningDetailsTable:dynamic:colPlannedItem']";
	public static final String PlannedIPLHItems="//*[@id='frmDetails:ctrStorePlanningDetailsTable:dynamic:colPlannedIPLH']";
	public static final String PlannedPercentonLYWages="//*[@id='frmDetails:ctrStorePlanningDetailsTable:dynamic:colLyWage']";
	public static final String PlannedCPH="//*[@id='frmDetails:ctrStorePlanningDetailsTable:dynamic:colPlannedCPH']";
	public static final String GrowthLYCPH="//*[@id='frmDetails:ctrStorePlanningDetailsTable:dynamic:colPlannedCPHPercent']";
	
//Sorting
	//Planning xpath:
	public static final String RegionDropdown="//*[@id='frmDetails:cboLocation']/option";
	public static final String RegionDropdownValue="//*[@id='frmDetails:cboLocation']/option[dynamic]";

	//Trading xpath:
	public static final String TSRegionDropdown="//*[@id='dispForm']/table[3]/tbody/tr/td[1]/select/option";
	public static final String TSRegionDropdownValue="//*[@id='dispForm']/table[3]/tbody/tr/td[1]/select/option[dynamic]";
	
	
	
	//Extra Table
	  public static final String FirstWeekofMonthColumnName="//*[@id='frmDetails:ctrStorePlanningDetailsTable:dynamic:oWeek1HistData11']";
	  public static final String SecondWeekofMonthColumnName="//*[@id='frmDetails:ctrStorePlanningDetailsTable:dynamic:oWeek2HistData22']";
		public static final String ThirdWeekofMonthColumnName="//*[@id='frmDetails:ctrStorePlanningDetailsTable:dynamic:oWeek3HistData33']";
		public static final String FourWeekofMonthColumnName="//*[@id='frmDetails:ctrStorePlanningDetailsTable:dynamic:oWeek4HistData44']";
	  public static final String CurrentWeekBDGTNT="//*[@id='frmDetails:ctrStorePlanningDetailsTable:dynamic:oPlannedSalesDate']";
		public static final String CurrentWeekFCSTNT="//*[@id='frmDetails:ctrStorePlanningDetailsTable:dynamic:oPlannedSalesDate1']";
	
	
	//Longlife
		@FindBy(xpath= "//*[@id='frmDetails:ctrStorePlanningDetailsTable']/tbody/tr")
		public WebElement TableRowsCountLL;
		
		public static final String CurrentWeekBGDTLL="//*[@id='frmDetails:ctrStorePlanningDetailsTable:0:oPlannedSalesDate']";
		public static final String CurrentWeekFCSTLL="//*[@id='frmDetails:ctrStorePlanningDetailsTable:0:oPlannedSalesDate1']";
		public static final String FirstWeekofMonthLL="//*[@id='frmDetails:ctrStorePlanningDetailsTable:16:oWeek1HistData11']";
		public static final String SecondWeekofMonthLL="//*[@id='frmDetails:ctrStorePlanningDetailsTable:16:oWeek2HistData22']";
		public static final String ThirdWeekofMonthLL="//*[@id='frmDetails:ctrStorePlanningDetailsTable:16:oWeek3HistData33']";
		public static final String FourWeekofMonthLL="//*[@id='frmDetails:ctrStorePlanningDetailsTable:16:oWeek4HistData44']";
		
		public static final String ForecastSalesLL="//*[@id='frmDetails:ctrStorePlanningDetailsTable:16:colForecastSales']";
		public static final String BudgetSalesLL="//*[@id='frmDetails:ctrStorePlanningDetailsTable:16:colBudgetSales']";
		public static final String GeneratedSalesLL="//*[@id='frmDetails:ctrStorePlanningDetailsTable:16:colGeneratedSales']";
		public static final String PlannedSalesLL="//*[@id='frmDetails:ctrStorePlanningDetailsTable:16:colPlannedSales']";
		public static final String VarToBudSalesLL="//*[@id='frmDetails:ctrStorePlanningDetailsTable:16:colVariance']";
		public static final String VarToFcstSalesLL="//*[@id=\"frmDetails:ctrStorePlanningDetailsTable:16:colfcstVariance\"]";
		public static final String PercentageLYSalesLL="//*[@id='frmDetails:ctrStorePlanningDetailsTable:16:colLySales']";
		public static final String PercentageBudgetSalesLL="//*[@id='frmDetails:ctrStorePlanningDetailsTable:16:colBUDPerSales']";
		public static final String PercentageFcstSalesLL="//*[@id=\"frmDetails:ctrStorePlanningDetailsTable:16:colFCSTPerSales\"]";
		public static final String PlannedItemSalesLL="//*[@id='frmDetails:ctrStorePlanningDetailsTable:16:colPlannedItemPrice']";
		public static final String PlannedItemSalesGrowthLL="//*[@id='frmDetails:ctrStorePlanningDetailsTable:16:colPlannedIPLH']";
		
		public static final String BudgetWagesLL="//*[@id='frmDetails:ctrStorePlanningDetailsTable:16:colBudgetWageDollar']";
		public static final String ForecastWagesLL="//*[@id=\"frmDetails:ctrStorePlanningDetailsTable:16:colForecastWageDollar\"]";
		public static final String PlannedWagesLL="//*[@id='frmDetails:ctrStorePlanningDetailsTable:16:colPlannedWageCost']";
		public static final String PercentLYWagesLL="//*[@id='frmDetails:ctrStorePlanningDetailsTable:16:colLyWage']";
		public static final String LYWagePercentageLL="//*[@id='frmDetails:ctrStorePlanningDetailsTable:16:colLyWagePercent']";
		public static final String BudgetWagesPercentLL="//*[@id='frmDetails:ctrStorePlanningDetailsTable:16:colBudgetWagePercent']";
		public static final String ForecastWagesPercentLL="//*[@id=\"frmDetails:ctrStorePlanningDetailsTable:16:colForecastWagePercent\"]";
		
		public static final String PlannedWagesPercentLL="//*[@id='frmDetails:ctrStorePlanningDetailsTable:16:colPlannedWagePercent']";
		public static final String PlannedPercentageonLYLL="//*[@id='frmDetails:ctrStorePlanningDetailsTable:16:colPlannedItemPriceGrowth']";

		public static final String SMSHoursLL="//*[@id='frmDetails:ctrStorePlanningDetailsTable:16:colSMSHours']";
		public static final String PlannedHoursLL="//*[@id='frmDetails:ctrStorePlanningDetailsTable:16:colPlannedPaidHours']";
		public static final String LYHoursLL="//*[@id='frmDetails:ctrStorePlanningDetailsTable:16:colLyHours']";
		public static final String DepartmentNamesLL="//*[@id='frmDetails:ctrStorePlanningDetailsTable:16:colOrgDesc']";
		
		public static final String FCSTORLL="//*[@id='frmDetails:ctrStorePlanningDetailsTable:16:colPlannedfORPer']";
		public static final String PlannedORLL="//*[@id='frmDetails:ctrStorePlanningDetailsTable:16:colPlannedORPer']";
		public static final String BDGTRateLL="//*[@id='frmDetails:ctrStorePlanningDetailsTable:16:colBdgtRate']";
		public static final String FCSTRateLL="//*[@id='frmDetails:ctrStorePlanningDetailsTable:16:colFcstRate']";
		public static final String BUDGETORLL="//*[@id='frmDetails:ctrStorePlanningDetailsTable:16:colPlannedbORPer']";
			
		
		//*[@id="frmDetails:ctrStorePlanningDetailsTable:16:colPlannedORPer"]
		public static final String PlannedItemPriceLL="//*[@id='frmDetails:ctrStorePlanningDetailsTable:16:colPlannedItemPrice']";
		public static final String PlannedPercentageonLYItemPriceLL="//*[@id='frmDetails:ctrStorePlanningDetailsTable:16:colPlannedItemPriceGrowth']";
		public static final String PlannedItemsLL="//*[@id='frmDetails:ctrStorePlanningDetailsTable:16:colPlannedItem']";
		public static final String PlannedIPLHItemsLL="//*[@id='frmDetails:ctrStorePlanningDetailsTable:16:colPlannedIPLH']";
		public static final String PlannedPercentonLYWagesLL="//*[@id='frmDetails:ctrStorePlanningDetailsTable:16:colLyWage']";
		public static final String PlannedCPHLL="//*[@id='frmDetails:ctrStorePlanningDetailsTable:16:colPlannedCPH']";
		public static final String GrowthLYCPHLL="//*[@id='frmDetails:ctrStorePlanningDetailsTable:16:colPlannedCPHPercent']";
	
	
	//Seafood and Deli
		@FindBy(xpath= "//*[@id='frmDetails:ctrStorePlanningDetailsTable']/tbody/tr")
		public WebElement TableRowsCountSFD;
		
		public static final String CurrentWeekBGDTSFD="//*[@id='frmDetails:ctrStorePlanningDetailsTable:0:oPlannedSalesDate']";
		public static final String CurrentWeekFCSTSFD="//*[@id='frmDetails:ctrStorePlanningDetailsTable:0:oPlannedSalesDate1']";
		public static final String FirstWeekofMonthSFD="//*[@id='frmDetails:ctrStorePlanningDetailsTable:17:oWeek1HistData11']";
		public static final String SecondWeekofMonthSFD="//*[@id='frmDetails:ctrStorePlanningDetailsTable:17:oWeek2HistData22']";
		public static final String ThirdWeekofMonthSFD="//*[@id='frmDetails:ctrStorePlanningDetailsTable:17:oWeek3HistData33']";
		public static final String FourWeekofMonthSFD="//*[@id='frmDetails:ctrStorePlanningDetailsTable:17:oWeek4HistData44']";
		
		public static final String ForecastSalesSFD="//*[@id='frmDetails:ctrStorePlanningDetailsTable:17:colForecastSales']";
		public static final String BudgetSalesSFD="//*[@id='frmDetails:ctrStorePlanningDetailsTable:17:colBudgetSales']";
		public static final String GeneratedSalesSFD="//*[@id='frmDetails:ctrStorePlanningDetailsTable:17:colGeneratedSales']";
		public static final String PlannedSalesSFD="//*[@id='frmDetails:ctrStorePlanningDetailsTable:17:colPlannedSales']";
		public static final String VarToBudSalesSFD="//*[@id='frmDetails:ctrStorePlanningDetailsTable:17:colVariance']";
		public static final String VarToFcstSalesSFD="//*[@id=\"frmDetails:ctrStorePlanningDetailsTable:17:colfcstVariance\"]";
		public static final String PercentageLYSalesSFD="//*[@id='frmDetails:ctrStorePlanningDetailsTable:17:colLySales']";
		public static final String PercentageBudgetSalesSFD="//*[@id='frmDetails:ctrStorePlanningDetailsTable:17:colBUDPerSales']";
		public static final String PercentageFcstSalesSFD="//*[@id=\"frmDetails:ctrStorePlanningDetailsTable:17:colFCSTPerSales\"]";
		public static final String PlannedItemSalesSFD="//*[@id='frmDetails:ctrStorePlanningDetailsTable:17:colPlannedItemPrice']";
		public static final String PlannedItemSalesGrowthSFD="//*[@id='frmDetails:ctrStorePlanningDetailsTable:17:colPlannedIPLH']";
		
		public static final String BudgetWagesSFD="//*[@id='frmDetails:ctrStorePlanningDetailsTable:17:colBudgetWageDollar']";
		public static final String ForecastWagesSFD="//*[@id=\"frmDetails:ctrStorePlanningDetailsTable:17:colForecastWageDollar\"]";
		public static final String PlannedWagesSFD="//*[@id='frmDetails:ctrStorePlanningDetailsTable:17:colPlannedWageCost']";
		public static final String PercentLYWagesSFD="//*[@id='frmDetails:ctrStorePlanningDetailsTable:17:colLyWage']";
		public static final String LYWagePercentageSFD="//*[@id='frmDetails:ctrStorePlanningDetailsTable:17:colLyWagePercent']";
		public static final String BudgetWagesPercentSFD="//*[@id='frmDetails:ctrStorePlanningDetailsTable:17:colBudgetWagePercent']";
		public static final String ForecastWagesPercentSFD="//*[@id=\"frmDetails:ctrStorePlanningDetailsTable:17:colForecastWagePercent\"]";
		
		public static final String PlannedWagesPercentSFD="//*[@id='frmDetails:ctrStorePlanningDetailsTable:17:colPlannedWagePercent']";
		public static final String PlannedPercentageonLYSFD="//*[@id='frmDetails:ctrStorePlanningDetailsTable:17:colPlannedItemPriceGrowth']";

		public static final String SMSHoursSFD="//*[@id='frmDetails:ctrStorePlanningDetailsTable:17:colSMSHours']";
		public static final String PlannedHoursSFD="//*[@id='frmDetails:ctrStorePlanningDetailsTable:17:colPlannedPaidHours']";
		public static final String LYHoursSFD="//*[@id='frmDetails:ctrStorePlanningDetailsTable:17:colLyHours']";
		public static final String DepartmentNamesSFD="//*[@id='frmDetails:ctrStorePlanningDetailsTable:17:colOrgDesc']";
		
		public static final String FCSTORSFD="//*[@id='frmDetails:ctrStorePlanningDetailsTable:17:colPlannedfORPer']";
		public static final String PlannedORSFD="//*[@id='frmDetails:ctrStorePlanningDetailsTable:17:colPlannedORPer']";
		public static final String BudgetORSFD="//*[@id='frmDetails:ctrStorePlanningDetailsTable:17:colPlannedbORPer']";
		public static final String BDGTRateSFD="//*[@id='frmDetails:ctrStorePlanningDetailsTable:17:colBdgtRate']";
		public static final String FCSTRateSFD="//*[@id='frmDetails:ctrStorePlanningDetailsTable:17:colFcstRate']";
		
		public static final String PlannedItemPriceSFD="//*[@id='frmDetails:ctrStorePlanningDetailsTable:17:colPlannedItemPrice']";
		public static final String PlannedPercentageonLYItemPriceSFD="//*[@id='frmDetails:ctrStorePlanningDetailsTable:17:colPlannedItemPriceGrowth']";
		public static final String PlannedItemsSFD="//*[@id='frmDetails:ctrStorePlanningDetailsTable:17:colPlannedItem']";
		public static final String PlannedIPLHItemsSFD="//*[@id='frmDetails:ctrStorePlanningDetailsTable:17:colPlannedIPLH']";
		public static final String PlannedPercentonLYWagesSFD="//*[@id='frmDetails:ctrStorePlanningDetailsTable:17:colLyWage']";
		public static final String PlannedCPHSFD="//*[@id='frmDetails:ctrStorePlanningDetailsTable:17:colPlannedCPH']";
		public static final String GrowthLYCPHSFD="//*[@id='frmDetails:ctrStorePlanningDetailsTable:17:colPlannedCPHPercent']";
		
	///////New Table ////////
		public static final String Table="//*[@id='frmDetails:ctrStorePlanningDetailsTable1']/tbody/tr";
		public static final String Week="//*[@id='frmDetails:ctrStorePlanningDetailsTable1:dynamic:colOrgDesc1']";
		public static final String WeekTotal="//*[@id='frmDetails:ctrStorePlanningDetailsTable1:dynamic:colOrgDesc1']";
		
		//public static final String WeekTotal="//*[@id='frmDetails:ctrStorePlanningDetailsTable1:4:oOrgDescinLink2']";
		public static final String Sales="//*[@id='frmDetails:ctrStorePlanningDetailsTable1:dynamic:colPlannedSales2']";
		
		public static final String SalesTtl="//*[@id='frmDetails:ctrStorePlanningDetailsTable1:dynamic:oPlannedSales2']";
		public static final String Wages="//*[@id='frmDetails:ctrStorePlanningDetailsTable1:dynamic:colPlannedWageCost2']";
		public static final String WagesTtl="//*[@id='frmDetails:ctrStorePlanningDetailsTable1:dynamic:colPlannedWageCost2']";
		public static final String WagePer="//*[@id='frmDetails:ctrStorePlanningDetailsTable1:dynamic:colPlannedWagePercent2']";
		public static final String WagePerTtl="//*[@id='frmDetails:ctrStorePlanningDetailsTable1:dynamic:colPlannedWagePercent2']";
		public static final String CPH="//*[@id='frmDetails:ctrStorePlanningDetailsTable1:dynamic:colPlannedCPH2']";
		public static final String CPHTtL="//*[@id='frmDetails:ctrStorePlanningDetailsTable1:dynamic:colPlannedCPH2']";
		public static final String PlannPaidHrs="//*[@id='frmDetails:ctrStorePlanningDetailsTable1:dynamic:colPlannedPaidHours2']";
		public static final String PlannPaidHrsTtl="//*[@id='frmDetails:ctrStorePlanningDetailsTable1:dynamic:colPlannedPaidHours2']";
		public static final String ORPER="//*[@id='frmDetails:ctrStorePlanningDetailsTable1:dynamic:colPlannedORPer2']";
		public static final String ORPERTtl="//*[@id='frmDetails:ctrStorePlanningDetailsTable1:dynamic:colPlannedORPer2']";
		
		
	//CopyDataToNewStore
		@FindBy(xpath= "//*[@id='form2:stateMenu']")
		public WebElement StateSelectionDropDown;
		
		@FindBy(xpath= "//*[@id='form2']/table[5]/tbody/tr[1]/td[1]/label")
		public WebElement StateTitle;
		
		@FindBy(xpath= "//*[@id='form2:weekMenu']")
		public WebElement FinancialWeekDropDown;
		
		@FindBy(xpath= "//*[@id='form2']/table[5]/tbody/tr[1]/td[3]/label")
		public WebElement FinWeekTitle;
		
		@FindBy(xpath= "//*[@id='form1:menu2']")
		public WebElement VerifyFrame;
		
		//*[@id='form2:factorTableId']
		
		
		public static final String AllOption="//*[@id='form2:stateMenu']/option[1]";
		public static final String NswOption="//*[@id='form2:stateMenu']/option[2]";
		public static final String QLDOption="//*[@id='form2:stateMenu']/option[3]";
		public static final String SAOption="//*[@id='form2:stateMenu']/option[4]";
		public static final String VICOption="//*[@id='form2:stateMenu']/option[5]";
		public static final String WAOption="//*[@id='form2:stateMenu']/option[6]";
		
		public static final String DataFrameTwo="/html/frameset/frame[2]";
		public static final String TitleOfVariableWageRateFactor="//*[@id='form2']/table[4]/tbody/tr/td";
		public static final String PositiveRateFactor="//*[@id='form2:posRate']";
		public static final String NegativeRateFactor="//*[@id='form2:negRate']";
		public static final String ADDButton="//input[@type='submit' and @value='Add']";
		public static final String State="//*[@id='form2:factorTableId:dynamic:j_id_jsp_72320285_87']";
		public static final String FinWeek="//*[@id='form2:factorTableId:dynamic:j_id_jsp_72320285_89']";
		public static final String PositiveRateFactorUpdated="//*[@id='form2:factorTableId:dynamic:posDynId']";
		public static final String NegativeRateFactorUpdated="//*[@id='form2:factorTableId:dynamic:j_id_jsp_72320285_95']/input";
		
		public static final String ChangesSavedSuccessMessage="//*[@id='form2:wageFactorTrail']";
		//Editable Planned Item Price Toggle
		public static final String TitleOfEditablePlannedItemPrice="//*[@id='form2']/table[7]/tbody/tr[1]/td";
		public static final String NSWText="//*[@id='form2:checkState']/tbody/tr/td[1]/label";
		public static final String QLDText="//*[@id='form2:checkState']/tbody/tr/td[2]/label";
		public static final String SAText="//*[@id='form2:checkState']/tbody/tr/td[3]/label";
		public static final String VICText="//*[@id='form2:checkState']/tbody/tr/td[4]/label";
		public static final String WAText="//*[@id='form2:checkState']/tbody/tr/td[5]/label";
		public static final String SaveButtonText="//*[@id='form2:saveState']";
		public static final String CheckboxoperationInstruction="//*[@id='form2:messageBox']";
		public static final String NSWCheckBox="//*[@id='form2:checkState:0']";
		public static final String QldCheckBox="//*[@id='form2:checkState:1']";
		public static final String SACheckBox="//*[@id='form2:checkState:2']";
		public static final String VICCheckBox="//*[@id='form2:checkState:3']";
		public static final String WACheckBox="//*[@id='form2:checkState:4']";
		public static final String SaveButton="//*[@id='form2:saveState']";
		public static final String NoChangesMadeErrorMessage="//*[@id='frmDetails:ctrStorePlanningDetailsTable1']/tbody/tr";
		public static final String SuccessMessageAfterCheckBoxSelection="//*[@id='form2:stateAddTrial']";
		public static final String SuccessMessageWithDateAndTime="//*[@id='form2:stateUpdateTrial']";
		
		// New Navigation View
		@FindBy(xpath= "//*[@id='main-menu']")
		public WebElement menuBar;
		
		@FindBy(xpath= "//span[@id='frmDetails:text11']")
		public WebElement pageName;
		
		public final String planningScreens="//a[contains(text(),'Planning Screens')]";
		public final String planvsactualscreen="//a[contains(text(),'Plan Vs Actual')]";
		
		public final String PlanvActualDailySummary="//a[contains(text(),'Plan v Actual Daily Summary')]";
		public final String tradingScreens="//a[contains(text(),'Trading Screens')]";
		public final String calenderScreens="//a[contains(text(),'Calendar')]";
		public final String adminScreen="//a[contains(text(),'Admin')]";
		public final String otherScreens="//a[contains(text(),'Others')]";
		public final String homeIcon="//span[@class='glyphicon glyphicon-home']";
		public final String dailyPlanning="//a[contains(text(),'Daily Planning/Actual')]";
		public final String weeklyPlanning="//a[contains(text(),'Weekly Planning')]";
		public final String monthlyPlanning="//a[contains(text(),'Monthly Planning')]";
		public final String departmentPlanning="//a[contains(text(),'Department Planning')]";
		//public final String screenToView="//a[contains(text(),'dynamic')]";
		public final String screenToView="//li[a[contains(text(),'Weekly Planning')]]/ul/li[a[contains(text(),'dynamic')]]";
		public final String MPscreenToView="//li[a[contains(text(),'Monthly Planning')]]/ul/li[a[contains(text(),'dynamic')]]";
		public final String AllocationscreenToView="//li[a[contains(text(),'Others')]]/ul/li/a[contains(text(),'Allocation of Fixed Hours')]";
		public final String DeptPlnscreenToView="//li[a[contains(text(),'Department Planning')]]/ul/li[a[contains(text(),'dynamic')]]";
		public final String DPscreenToView="//li[a[contains(text(),'Daily Planning/Actual')]]/ul/li[a[contains(text(),'dynamic')]]";
		public final String screenToViewWeeklyPlanning="//li[a[contains(text(),'Weekly Planning')]]/ul/li[a[contains(text(),'dynamic')]]";
		public final String screenToViewDailyTrading="//li[a[contains(text(),'Daily Trading')]]/ul/li[a[contains(text(),'dynamic')]]";
		public final String screenToViewWeeklyTrading="//li[a[contains(text(),'Weekly Trading')]]/ul/li[a[contains(text(),'dynamic')]]";
		public final String screenToViewMonthlyTrading="//li[a[contains(text(),'Monthly Trading')]]/ul/li[a[contains(text(),'dynamic')]]";
		public final String screenToViewDailyPlanning="//li[a[contains(text(),'Daily Planning/Actual')]]/ul/li[a[contains(text(),'dynamic')]]";
		public final String screenToViewAllocationoffixdhrs="//a[contains(text(),'dynamic')]";
		public final String screenToViewPlanvsactual="//a[contains(text(),'dynamic')]";
		public final String dailyTrading="//a[contains(text(),'Daily Trading')]";
		public final String weeklyTrading="//a[contains(text(),'Weekly Trading')]";
		public final String monthlyTrading="//a[contains(text(),'Monthly Trading')]";
		public final String yearlyTrading="//a[contains(text(),'Yearly Trading')]";
		public final String screenToViewSalesHisCalendar="//a[contains(text(),'Sales History Calendar')]";
		public final String screenToViewForecastCalendar="//a[contains(text(),'Forecast Calendar')]";
}