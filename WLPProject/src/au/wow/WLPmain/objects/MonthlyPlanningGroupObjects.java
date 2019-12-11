package au.wow.WLPmain.objects;

import java.util.List;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class MonthlyPlanningGroupObjects 
{
	@FindBy(xpath= "//*[@id='frmDetails:ctrStorePlanningDetailsTable']/tbody/tr")
	public WebElement TableRowsCount;
					
	public static final String MPTable="//*[@id='frmDetails:ctrStorePlanningDetailsTable']/tbody/tr";
	public static final String MPWeekTable="//*[@id='frmDetails:ctrStorePlanningDetailsTable']/tbody/tr";
	public static final String FirstMonth="//*[@id='frmDetails:ctrStorePlanningDetailsTable:dynamic:omonth1HistData']";
	public static final String SecondMonth="//*[@id='frmDetails:ctrStorePlanningDetailsTable:dynamic:omonth2HistData']";
	public static final String ThirdMonth="//*[@id='frmDetails:ctrStorePlanningDetailsTable:dynamic:omonth3HistData']";
	public static final String FourMonth="//*[@id='frmDetails:ctrStorePlanningDetailsTable:dynamic:omonth4HistData']";
	public static final String StoreName="//*[@id='frmDetails:ctrStorePlanningDetailsTable:dynamic:colWkDesc']";
	
	public static final String BudgetSales="//*[@id='frmDetails:ctrStorePlanningDetailsTable:dynamic:colBudgetSales']";
	
	public static final String GeneratedSales="//*[@id='frmDetails:ctrStorePlanningDetailsTable:dynamic:colGeneratedSales']";
	public static final String PlannedSales="//*[@id='frmDetails:ctrStorePlanningDetailsTable:dynamic:colPlannedSales']";
	public static final String VarToBudSales="//*[@id='frmDetails:ctrStorePlanningDetailsTable:dynamic:colVariance']";
	public static final String VarToForecast="//*[@id='frmDetails:ctrStorePlanningDetailsTable:dynamic:colVariance1']";
	public static final String LYPercentageSales="//*[@id='frmDetails:ctrStorePlanningDetailsTable:dynamic:colLySales']";
	public static final String BudgetPercentageSales="//*[@id='frmDetails:ctrStorePlanningDetailsTable:dynamic:colBUDPerSales']";
	public static final String ForecastPercentageSales="//*[@id='frmDetails:ctrStorePlanningDetailsTable:dynamic:colFCSTPerSales']";	
	public static final String BudgetWages="//*[@id='frmDetails:ctrStorePlanningDetailsTable:dynamic:colBudgetWageDollar']";
	public static final String BaseCostWages="//*[@id='frmDetails:ctrStorePlanningDetailsTable:dynamic:colPlannedCPH1']";
	public static final String PlannedWages="//*[@id='frmDetails:ctrStorePlanningDetailsTable:dynamic:colPlannedWageCost']";
	public static final String LYPercentageWages="//*[@id='frmDetails:ctrStorePlanningDetailsTable:dynamic:colLyWage']";
	public static final String BudgetWagesPercentage="//*[@id='frmDetails:ctrStorePlanningDetailsTable:dynamic:colBudgetWagePercent']";
	public static final String ForecastWagesPercentage="//*[@id=\"frmDetails:ctrStorePlanningDetailsTable:dynamic:colForecastWagePercent\"]";
	public static final String PlannedLYWagesPercentage="//*[@id='frmDetails:ctrStorePlanningDetailsTable:dynamic:colPlannedWagePercent']";
	public static final String LYWagesPercentage="//*[@id='frmDetails:ctrStorePlanningDetailsTable:dynamic:colLyWagePercent']";
	public static final String SMSHours="//*[@id='frmDetails:ctrStorePlanningDetailsTable:dynamic:colSMSHours']";
	public static final String BaseHours="//*[@id='frmDetails:ctrStorePlanningDetailsTable:dynamic:colSavingOverBudgetRate1']";
	public static final String PlannedHours="//*[@id='frmDetails:ctrStorePlanningDetailsTable:dynamic:colPlannedPaidHours']";
	public static final String LYPercentageHours="//*[@id='frmDetails:ctrStorePlanningDetailsTable:dynamic:colLyHours']";
	public static final String PlannedOR="//*[@id='frmDetails:ctrStorePlanningDetailsTable:dynamic:colPlannedORPer']";
	public static final String PlannedCPH="//*[@id='frmDetails:ctrStorePlanningDetailsTable:dynamic:colPlannedCPH']";
	public static final String GrowthLYCPH="//*[@id='frmDetails:ctrStorePlanningDetailsTable:dynamic:colPlannedCPHPercent']";
	public static final String BDGTRate="//*[@id='frmDetails:ctrStorePlanningDetailsTable:dynamic:colBdgtRate']";
	public static final String FCSTRate="//*[@id=\"frmDetails:ctrStorePlanningDetailsTable:dynamic:colFcstRate\"]";
	public static final String RowNameTotal="//*[@id='frmDetails:ctrStorePlanningDetailsTable:dynamic:colOrgDesc']";
	
	//Weekly Planning-Store - New Table
	public static final String RowNameTotalLL="//*[@id='frmDetails:ctrStorePlanningDetailsTable:16:colOrgDesc']";
	public static final String RowNameTotalSFD="//*[@id='frmDetails:ctrStorePlanningDetailsTable:17:colOrgDesc']";
	public static final String RowNameTtlNT="//*[@id='frmDetails:ctrStorePlanningDetailsTable:dynamic:colOrgDesc']";
	public static final String BDGTRateLL="";
	public static final String BDGTRateSFD="";
	public static final String BudgetWagesPercentageLL="";
	public static final String BudgetWagesPercentageSFD="";
	public static final String PlannedSalesLL="";
	public static final String PlannedSalesSFD="";
	public static final String BudgetSalesLL="";
	public static final String BudgetSalesSFD="";
	public static final String PlannedWagesLL="";
	public static final String PlannedWagesSFD="";
	
	//PST 760 WEEK DATA NEW TABLE
	public static final String FirstMonthWeekTable="//*[@id='frmDetails:ctrStorePlanningDetailsTable:dynamic:omonth1HistData']";
	public static final String SecondMonthWeekTable="//*[@id='frmDetails:ctrStorePlanningDetailsTable:dynamic:omonth2HistData']";
	public static final String ThirdMonthWeekTable="//*[@id='frmDetails:ctrStorePlanningDetailsTable:dynamic:omonth3HistData']";
	public static final String FourMonthWeekTable="//*[@id='frmDetails:ctrStorePlanningDetailsTable:dynamic:omonth4HistData']";
    public static final String StoreNameWeekTable="//*[@id='frmDetails:ctrStorePlanningDetailsTable:dynamic:colWkDesc']";
	
	public static final String BudgetSalesWeekTable="//*[@id='frmDetails:ctrStorePlanningDetailsTable:dynamic:colBudgetSales']";
	public static final String GeneratedSalesWeekTable="//*[@id='frmDetails:ctrStorePlanningDetailsTable:dynamic:colGeneratedSales']";
	public static final String PlannedSalesWeekTable="//*[@id='frmDetails:ctrStorePlanningDetailsTable:dynamic:colPlannedSales']";
	public static final String VarToBudSalesWeekTable="//*[@id='frmDetails:ctrStorePlanningDetailsTable:dynamic:colVariance']";
	public static final String VarToForecastWeekTable="//*[@id='frmDetails:ctrStorePlanningDetailsTable:dynamic:colVariance1']";
	public static final String LYPercentageSalesWeekTable="//*[@id='frmDetails:ctrStorePlanningDetailsTable:dynamic:colLySales']";
	public static final String BudgetPercentageSalesWeekTable="//*[@id='frmDetails:ctrStorePlanningDetailsTable:dynamic:colBUDPerSales']";
	public static final String ForecastPercentageSalesWeekTable="//*[@id='frmDetails:ctrStorePlanningDetailsTable:dynamic:colFCSTPerSales']";	
	public static final String BudgetWagesWeekTable="//*[@id='frmDetails:ctrStorePlanningDetailsTable:dynamic:colBudgetWageDollar']";
	public static final String BaseCostWagesWeekTable="//*[@id='frmDetails:ctrStorePlanningDetailsTable:dynamic:colPlannedCPH1']";
	public static final String PlannedWagesWeekTable="//*[@id='frmDetails:ctrStorePlanningDetailsTable:dynamic:colPlannedWageCost']";
	public static final String LYPercentageWagesWeekTable="//*[@id='frmDetails:ctrStorePlanningDetailsTable:dynamic:colLyWage']";
	public static final String BudgetWagesPercentageWeekTable="//*[@id='frmDetails:ctrStorePlanningDetailsTable:dynamic:colBudgetWagePercent']";
	public static final String ForecastWagesPercentageWeekTable="//*[@id=\"frmDetails:ctrStorePlanningDetailsTable:dynamic:colForecastWagePercent\"]";
	public static final String PlannedLYWagesPercentageWeekTable="//*[@id='frmDetails:ctrStorePlanningDetailsTable:dynamic:colPlannedWagePercent']";
	public static final String LYWagesPercentageWeekTable="//*[@id='frmDetails:ctrStorePlanningDetailsTable:dynamic:colLyWagePercent']";
	public static final String SMSHoursWeekTable="//*[@id='frmDetails:ctrStorePlanningDetailsTable:dynamic:colSMSHours']";
	public static final String BaseHoursWeekTable="//*[@id='frmDetails:ctrStorePlanningDetailsTable:dynamic:colSavingOverBudgetRate1']";
	public static final String PlannedHoursWeekTable="//*[@id='frmDetails:ctrStorePlanningDetailsTable:dynamic:colPlannedPaidHours']";
	public static final String LYPercentageHoursWeekTable="//*[@id='frmDetails:ctrStorePlanningDetailsTable:dynamic:colLyHours']";
	public static final String PlannedORWeekTable="//*[@id='frmDetails:ctrStorePlanningDetailsTable:dynamic:colPlannedORPer']";
	public static final String PlannedCPHWeekTable="//*[@id='frmDetails:ctrStorePlanningDetailsTable:dynamic:colPlannedCPH']";
	public static final String GrowthLYCPHWeekTable="//*[@id='frmDetails:ctrStorePlanningDetailsTable:dynamic:colPlannedCPHPercent']";
	public static final String BDGTRateWeekTable="//*[@id='frmDetails:ctrStorePlanningDetailsTable:dynamic:colBdgtRate']";
	public static final String FCSTRateWeekTable="//*[@id=\"frmDetails:ctrStorePlanningDetailsTable:dynamic:colFcstRate\"]";
	public static final String RowNameTotalWeekTable="//*[@id='frmDetails:ctrStorePlanningDetailsTable:dynamic:colOrgDesc']"; 
	
	@FindBy(xpath= "//*[@id='frmDetails:ctrStorePlanningDetailsTable:11:colOrgDesc']")
	public WebElement TotalRow;
	
	@FindBy(xpath= "//span[@id='frmDetails:lblHistoryType']")
	public WebElement HistoryViewTitle;
	
	@FindBy(xpath= "//*[@id='frmDetails:cboHistoryType']")
	public WebElement HistoryViewDropdown;
	
	@FindBy(xpath= "/html/frameset/frame[1]")
	public WebElement TitleFrame;
	
	@FindBy(xpath= "//*[@id='form1:buttonLogoff']")
	public WebElement Logoff;
	
	@FindBy(xpath= "//*[@id='frmDetails:cboLocation']")
	public WebElement SelectGroup;
	
	@FindBy(xpath= "//*[@id='frmDetails:ctrStorePlanningDetailsTable:11:omonth1HistData']")
	public WebElement FirstMonthHistory;
		
	@FindBy(xpath= "//*[@id='frmDetails:ctrStorePlanningDetailsTable:11:omonth2HistData']")
	public WebElement SecondMonthHistory;
	
	@FindBy(xpath= "//*[@id='frmDetails:ctrStorePlanningDetailsTable:11:omonth3HistData']")
	public WebElement ThirdMonthHistory;
		
	@FindBy(xpath= "//*[@id='frmDetails:ctrStorePlanningDetailsTable:11:omonth4HistData']")
	public WebElement FourthMonthHistory;
	
	@FindBy(xpath= "//*[@id='frmDetails:ctrStorePlanningDetailsTable:11:colBudgetSales']")
	public WebElement BudgetSalesTotal;
	
	@FindBy(xpath= "//*[@id='frmDetails:ctrStorePlanningDetailsTable:11:colGeneratedSales']")
	public WebElement GeneratedSalesTotal;
	
	@FindBy(xpath= "//*[@id='frmDetails:ctrStorePlanningDetailsTable:11:colPlannedSales']")
	public WebElement PlannedSalesTotal;
	
	@FindBy(xpath= "//*[@id='frmDetails:ctrStorePlanningDetailsTable:11:colVariance']")
	public WebElement VartoBudSalesTotal;
	
	@FindBy(xpath= "//*[@id='frmDetails:ctrStorePlanningDetailsTable:11:colLySales']")
	public WebElement LYPercentageSalesTotal;
	
	@FindBy(xpath= "//*[@id='frmDetails:ctrStorePlanningDetailsTable:11:colBUDPerSales']")
	public WebElement BudgetPercentageSalesTotal;
	
	@FindBy(xpath= "//*[@id='frmDetails:ctrStorePlanningDetailsTable:11:colBudgetWageDollar']")
	public WebElement BudgetWagesTotal;
	
	@FindBy(xpath= "//*[@id='frmDetails:ctrStorePlanningDetailsTable:11:colPlannedCPH1']")
	public WebElement BaseCostWagesTotal;
	
	@FindBy(xpath= "//*[@id='frmDetails:ctrStorePlanningDetailsTable:11:colPlannedWageCost']")
	public WebElement PlannedWagesTotal;
	
	@FindBy(xpath= "//*[@id='frmDetails:ctrStorePlanningDetailsTable:11:colLyWage']")
	public WebElement LYPercentageWagesTotal;
	
	@FindBy(xpath= "//*[@id='frmDetails:ctrStorePlanningDetailsTable:11:colBudgetWagePercent']")
	public WebElement BudgetWagesPercentageTotal;
	
	@FindBy(xpath= "//*[@id='frmDetails:ctrStorePlanningDetailsTable:11:colPlannedWagePercent']")
	public WebElement PlannedLYWagesPercentageTotal;
	
	@FindBy(xpath= "//*[@id='frmDetails:ctrStorePlanningDetailsTable:11:colLyWagePercent']")
	public WebElement LYWagesPercentageTotal;
	
	@FindBy(xpath= "//*[@id='frmDetails:ctrStorePlanningDetailsTable:11:colSMSHours']")
	public WebElement SMSHoursTotal;
	
	@FindBy(xpath= "//*[@id='frmDetails:ctrStorePlanningDetailsTable:11:colSavingOverBudgetRate1']")
	public WebElement BaseHoursTotal;
	
	@FindBy(xpath= "//*[@id='frmDetails:ctrStorePlanningDetailsTable:11:colPlannedPaidHours']")
	public WebElement PlannedHoursTotal;
	
	@FindBy(xpath= "//*[@id='frmDetails:ctrStorePlanningDetailsTable:11:colLyHours']")
	public WebElement LYPercentageHoursTotal;
	
	@FindBy(xpath= "//*[@id='frmDetails:ctrStorePlanningDetailsTable:11:colPlannedORPer']")
	public WebElement PlannedORTotal;
	
	@FindBy(xpath= "//*[@id='frmDetails:ctrStorePlanningDetailsTable:11:colPlannedCPH']")
	public WebElement PlannedCPHTotal;
	
	@FindBy(xpath= "//*[@id='frmDetails:ctrStorePlanningDetailsTable:6:colPlannedCPHPercent']")
	public WebElement GrowthLYCPHTotal;
	
	//BDGT Rate total
	public static final String PlannedSalesTtl="//*[@id='frmDetails:ctrStorePlanningDetailsTable:11:colPlannedSales']";
	public static final String BudgetSalesTtl="//*[@id='frmDetails:ctrStorePlanningDetailsTable:11:colBudgetSales']";
	public static final String BDGTRateTtl="//*[@id='frmDetails:ctrStorePlanningDetailsTable:11:colBdgtRate']";
	public static final String BudgetWagesPercentageTtl="//*[@id='frmDetails:ctrStorePlanningDetailsTable:11:colBudgetWagePercent']";
}	

