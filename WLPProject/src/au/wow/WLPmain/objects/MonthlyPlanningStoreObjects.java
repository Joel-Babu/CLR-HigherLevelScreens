package au.wow.WLPmain.objects;

import java.util.List;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class MonthlyPlanningStoreObjects 
{
	@FindBy(xpath= "//*[@id='frmDetails:ctrStorePlanningDetailsTable']/tbody/tr")
	public WebElement TableRowsCount;
	
	@FindBy(xpath= "//*[@id='form1:label15']")
	public WebElement PageTitle;
	
	public static final String PageDropdown="//*[@id='form1:menu2']/option";
	public static final String PageDropdownValue="//*[@id='form1:menu2']/option[dynamic]";
	public static final String MPTable="//*[@id='frmDetails:ctrStorePlanningDetailsTable']/tbody/tr";
	public static final String FirstMonth="//*[@id='frmDetails:ctrStorePlanningDetailsTable:dynamic:omonth1HistData']";
	public static final String SecondMonth="//*[@id='frmDetails:ctrStorePlanningDetailsTable:dynamic:omonth2HistData']";
	public static final String ThirdMonth="//*[@id='frmDetails:ctrStorePlanningDetailsTable:dynamic:omonth3HistData']";
	public static final String FourMonth="//*[@id='frmDetails:ctrStorePlanningDetailsTable:dynamic:omonth4HistData']";
	public static final String CurrentMonth="//*[@id='frmDetails:ctrStorePlanningDetailsTable:dynamic:oPlannedSalesDate']";
	public static final String FirstMonthNm="//*[@id='frmDetails:ctrStorePlanningDetailsTable:0:omonth1HistData']";
	public static final String SecondMonthNm="//*[@id='frmDetails:ctrStorePlanningDetailsTable:0:omonth2HistData']";
	public static final String ThirdMonthNm="//*[@id='frmDetails:ctrStorePlanningDetailsTable:0:omonth3HistData']";
	public static final String FourthmonthNm="//*[@id='frmDetails:ctrStorePlanningDetailsTable:0:omonth4HistData']";
	public static final String ForecastCurrentMonth="//*[@id='frmDetails:ctrStorePlanningDetailsTable:dynamic:oPlannedSalesDate1']";
	public static final String WeekName="//*[@id='frmDetails:ctrStorePlanningDetailsTable:dynamic:oOrgDesc']";	
	public static final String Week="//*[@id='frmDetails:ctrStorePlanningDetailsTable:dynamic:colWkDesc']";
	public static final String Events="//*[@id='frmDetails:ctrStorePlanningDetailsTable:dynamic:colEvent']";
	public static final String BudgetSales="//*[@id='frmDetails:ctrStorePlanningDetailsTable:dynamic:colBudgetSales']";
	public static final String BudgetSalesWeekTotal="//*[@id='frmDetails:ctrStorePlanningDetailsTable:dynamic:colBudgetSales']";
	public static final String GeneratedSales="//*[@id='frmDetails:ctrStorePlanningDetailsTable:dynamic:colGeneratedSales']";
	public static final String GeneratedSalesWeekTotal="//*[@id='frmDetails:ctrStorePlanningDetailsTable:dynamic:colGeneratedSales']";
	public static final String PlannedSales="//*[@id='frmDetails:ctrStorePlanningDetailsTable:dynamic:oPlannedSales2']";
	public static final String FCSTPlannedSales="//*[@id='frmDetails:ctrStorePlanningDetailsTable:dynamic:colPlannedSales']";
	public static final String VarToBudSales="//*[@id='frmDetails:ctrStorePlanningDetailsTable:dynamic:colVariance']";
	public static final String LYPercentageSales="//*[@id='frmDetails:ctrStorePlanningDetailsTable:dynamic:colLySales']";
	public static final String BudgetPercentageSales="//*[@id='frmDetails:ctrStorePlanningDetailsTable:dynamic:colBUDPerSales']";
	public static final String BudgetWages="//*[@id='frmDetails:ctrStorePlanningDetailsTable:dynamic:colBudgetWageDollar']";
	public static final String BaseCostWages="//*[@id='frmDetails:ctrStorePlanningDetailsTable:dynamic:colPlannedCPH1']";
	public static final String PlannedWages="//*[@id='frmDetails:ctrStorePlanningDetailsTable:dynamic:oPlannedWageCost2']";
	public static final String LYPercentageWages="//*[@id='frmDetails:ctrStorePlanningDetailsTable:dynamic:colLyWage']";
	public static final String BudgetWagesPercentage="//*[@id='frmDetails:ctrStorePlanningDetailsTable:dynamic:colBudgetWagePercent']";
	public static final String PlannedLYWagesPercentage="//*[@id='frmDetails:ctrStorePlanningDetailsTable:dynamic:colPlannedWagePercent']";
	public static final String LYWagesPercentage="//*[@id='frmDetails:ctrStorePlanningDetailsTable:dynamic:colLyWagePercent']";
	public static final String SMSHours="//*[@id='frmDetails:ctrStorePlanningDetailsTable:dynamic:colSMSHours']";
	public static final String BaseHours="//*[@id='frmDetails:ctrStorePlanningDetailsTable:dynamic:colSavingOverBudgetRate1']";
	public static final String PlannedHours="//*[@id='frmDetails:ctrStorePlanningDetailsTable:dynamic:oPlannedPaidHours2']";
	public static final String FCSTPlannedHours="//*[@id='frmDetails:ctrStorePlanningDetailsTable:dynamic:oPlannedPaidHours']";
	public static final String LYPercentageHours="//*[@id='frmDetails:ctrStorePlanningDetailsTable:dynamic:colLyHours']";
	public static final String PlannedOR="//*[@id='frmDetails:ctrStorePlanningDetailsTable:dynamic:colPlannedORPer']";
	public static final String PlannedCPH="//*[@id='frmDetails:ctrStorePlanningDetailsTable:dynamic:colPlannedCPH']";
	public static final String GrowthLYCPH="//*[@id='frmDetails:ctrStorePlanningDetailsTable:dynamic:colPlannedCPHPercent']";
	public static final String BDGTRate="//*[@id='frmDetails:ctrStorePlanningDetailsTable:dynamic:colBdgtRate']";
	public static final String BDGTRateWeekTable="//*[@id='frmDetails:ctrStorePlanningDetailsTable:dynamic:colBdgtRate']";
	public static final String BdgtOR="//*[@id=\"frmDetails:ctrStorePlanningDetailsTable:dynamic:colPlannedbORPer\"]";
	public static final String FcstOR="//*[@id=\"frmDetails:ctrStorePlanningDetailsTable:dynamic:colPlannedfORPer\"]";
	
	//Forecast
	public static final String ForecastSales="//*[@id='frmDetails:ctrStorePlanningDetailsTable:dynamic:colForecastSales']";
	public static final String ForecastSalesWeekTotal="//*[@id='frmDetails:ctrStorePlanningDetailsTable:dynamic:colForecastSales']";
	public static final String VarToForecast="//*[@id='frmDetails:ctrStorePlanningDetailsTable:dynamic:colVariance1']";
	public static final String ForecastPercentageSales="//*[@id='frmDetails:ctrStorePlanningDetailsTable:dynamic:colFCSTPerSales']";
	public static final String ForecastWages="//*[@id='frmDetails:ctrStorePlanningDetailsTable:dynamic:colForecastWageDollar']";
	public static final String ForecastWagesWeekTable="//*[@id='frmDetails:ctrStorePlanningDetailsTable:dynamic:colForecastWageDollar']";
	public static final String ForecastWagesPercentage="//*[@id='frmDetails:ctrStorePlanningDetailsTable:dynamic:colForecastWagePercent']";
	public static final String ForecastWagesPercentageWeekTable="//*[@id='frmDetails:ctrStorePlanningDetailsTable:dynamic:colForecastWagePercent']";
	public static final String FCSTRate="//*[@id='frmDetails:ctrStorePlanningDetailsTable:dynamic:colFcstRate']";
	public static final String FCSTRateWeekTable="//*[@id='frmDetails:ctrStorePlanningDetailsTable:dynamic:colFcstRate']";
	
	//Forecast
	@FindBy(xpath= "//*[@id='frmDetails:ctrStorePlanningDetailsTable:6:colForecastSales']")
	public WebElement ForecastSalesTotal;
	
	@FindBy(xpath= "//*[@id='frmDetails:ctrStorePlanningDetailsTable:6:colVariance1']")
	public WebElement VarToForecastTotal;
	
	@FindBy(xpath= "//*[@id='frmDetails:ctrStorePlanningDetailsTable:6:colFCSTPerSales']")
	public WebElement ForecastPercentageSalesTotal;
	
	@FindBy(xpath= "//*[@id='frmDetails:ctrStorePlanningDetailsTable:6:colForecastWageDollar']")
	public WebElement ForecastWagesTotal;
	
	@FindBy(xpath= "//*[@id='frmDetails:ctrStorePlanningDetailsTable:6:colForecastWagePercent']")
	public WebElement ForecastWagesPercentageTotal;
	
	@FindBy(xpath= "//*[@id='frmDetails:ctrStorePlanningDetailsTable:6:colFcstRate']")
	public WebElement FCSTRateTotal;
	
	
	@FindBy(xpath= "//*[@id='frmDetails:ctrStorePlanningDetailsTable:6:colOrgDesc']")
	public WebElement TotalRow;
	
	@FindBy(xpath= "//*[@id='frmDetails:ctrStorePlanningDetailsTable:5:colWkDesc']")
	public WebElement FifthWeek;
	
	@FindBy(xpath= "//*[@id='frmDetails:ctrStorePlanningDetailsTable:6:omonth1HistData']")
	public WebElement FirstMonthHistory;
		
	@FindBy(xpath= "//*[@id='frmDetails:ctrStorePlanningDetailsTable:6:omonth2HistData']")
	public WebElement SecondMonthHistory;
	
	@FindBy(xpath= "//*[@id='frmDetails:ctrStorePlanningDetailsTable:6:omonth3HistData']")
	public WebElement ThirdMonthHistory;
		
	@FindBy(xpath= "//*[@id='frmDetails:ctrStorePlanningDetailsTable:6:omonth4HistData']")
	public WebElement FourthMonthHistory;
	
	@FindBy(xpath= "//*[@id='frmDetails:ctrStorePlanningDetailsTable:6:colBudgetSales']")
	public WebElement BudgetSalesTotal;
	
	@FindBy(xpath= "//*[@id='frmDetails:ctrStorePlanningDetailsTable:6:colGeneratedSales']")
	public WebElement GeneratedSalesTotal;
	
	@FindBy(xpath= "//*[@id='frmDetails:ctrStorePlanningDetailsTable:6:oPlannedSales']")
	public WebElement PlannedSalesTotal;
	
	@FindBy(xpath= "//*[@id='frmDetails:ctrStorePlanningDetailsTable:6:colVariance']")
	public WebElement VartoBudSalesTotal;
	
	@FindBy(xpath= "//*[@id='frmDetails:ctrStorePlanningDetailsTable:6:colLySales']")
	public WebElement LYPercentageSalesTotal;
	
	@FindBy(xpath= "//*[@id='frmDetails:ctrStorePlanningDetailsTable:6:colBUDPerSales']")
	public WebElement BudgetPercentageSalesTotal;
	
	@FindBy(xpath= "//*[@id='frmDetails:ctrStorePlanningDetailsTable:6:colBudgetWageDollar']")
	public WebElement BudgetWagesTotal;
	
	@FindBy(xpath= "//*[@id='frmDetails:ctrStorePlanningDetailsTable:6:colPlannedCPH1']")
	public WebElement BaseCostWagesTotal;
	
	@FindBy(xpath= "//*[@id='frmDetails:ctrStorePlanningDetailsTable:6:colPlannedWageCost']")
	public WebElement PlannedWagesTotal;
	
	@FindBy(xpath= "//*[@id='frmDetails:ctrStorePlanningDetailsTable:6:colLyWage']")
	public WebElement LYPercentageWagesTotal;
	
	@FindBy(xpath= "//*[@id='frmDetails:ctrStorePlanningDetailsTable:6:colBudgetWagePercent']")
	public WebElement BudgetWagesPercentageTotal;
	
	@FindBy(xpath= "//*[@id=\"frmDetails:ctrStorePlanningDetailsTable:6:colPlannedbORPer\"]")
	public WebElement BdgtORTtl;
	
	@FindBy(xpath= "//*[@id=\"frmDetails:ctrStorePlanningDetailsTable:6:colPlannedfORPer\"]")
	public WebElement FcstORTtl;
	
	@FindBy(xpath= "//*[@id='frmDetails:ctrStorePlanningDetailsTable:6:colPlannedWagePercent']")
	public WebElement PlannedLYWagesPercentageTotal;
	
	@FindBy(xpath= "//*[@id='frmDetails:ctrStorePlanningDetailsTable:6:colLyWagePercent']")
	public WebElement LYWagesPercentageTotal;
	
	@FindBy(xpath= "//*[@id='frmDetails:ctrStorePlanningDetailsTable:6:colSMSHours']")
	public WebElement SMSHoursTotal;
	
	@FindBy(xpath= "//*[@id='frmDetails:ctrStorePlanningDetailsTable:6:colSavingOverBudgetRate1']")
	public WebElement BaseHoursTotal;
	
	@FindBy(xpath= "//*[@id='frmDetails:ctrStorePlanningDetailsTable:6:colPlannedPaidHours']")
	public WebElement PlannedHoursTotal;
	
	@FindBy(xpath= "//*[@id='frmDetails:ctrStorePlanningDetailsTable:6:colLyHours']")
	public WebElement LYPercentageHoursTotal;
	
	@FindBy(xpath= "//*[@id='frmDetails:ctrStorePlanningDetailsTable:6:colPlannedORPer']")
	public WebElement PlannedORTotal;
	
	@FindBy(xpath= "//*[@id='frmDetails:ctrStorePlanningDetailsTable:6:colPlannedCPH']")
	public WebElement PlannedCPHTotal;
	
	@FindBy(xpath= "//*[@id='frmDetails:ctrStorePlanningDetailsTable:6:colPlannedCPHPercent']")
	public WebElement GrowthLYCPHTotal;
	
	@FindBy(xpath= "//*[@id='frmDetails:ctrStorePlanningDetailsTable:dynamic:colBdgtRate']")
	public WebElement BDGTRateTotal;

	@FindBy(xpath= "//*[@id='frmDetails:ctrStorePlanningDetailsTable:6:colBdgtRate']")
	public WebElement BDGTRateTot;
	
	@FindBy(xpath= "//*[@id='frmDetails:ctrStorePlanningDetailsTable:6:colFcstRate']")
	public WebElement FCSTRateTot;
	
	//BGDT Rate
	public static final String PlannedSalesTtl="//*[@id='frmDetails:ctrStorePlanningDetailsTable:6:colPlannedSales']";
	public static final String BudgetSalesTtl="//*[@id='frmDetails:ctrStorePlanningDetailsTable:6:colBudgetSales']";
	public static final String BDGTRateTtl="//*[@id='frmDetails:ctrStorePlanningDetailsTable:6:colBdgtRate']";
	public static final String BudgetWagesPercentageTtl="//*[@id='frmDetails:ctrStorePlanningDetailsTable:6:colBudgetWagePercent']";
	public static final String TotalRowName="//*[@id='frmDetails:ctrStorePlanningDetailsTable:6:colOrgDesc']";
	public static final String PlannedWagesTtl="//*[@id='frmDetails:ctrStorePlanningDetailsTable:6:oPlannedWageCost']";
	public static final String BudgetWagesTtl="//*[@id='frmDetails:ctrStorePlanningDetailsTable:6:colBudgetWageDollar']";
	
	//FCST Rate
	public static final String ForecastSalesTtl="//*[@id='frmDetails:ctrStorePlanningDetailsTable:6:colForecastSales']";
	public static final String FCSTRateTtl="//*[@id='frmDetails:ctrStorePlanningDetailsTable:6:colFcstRate']";
	public static final String ForecastWagesPercentageTtl="//*[@id='frmDetails:ctrStorePlanningDetailsTable:6:colForecastWagePercent']";
	public static final String ForecastWagesTtl="//*[@id='frmDetails:ctrStorePlanningDetailsTable:6:colForecastWageDollar']";
	
	//PlannedOR
	public static final String PlannedORTtl= "//*[@id='frmDetails:ctrStorePlanningDetailsTable:6:colPlannedORPer']";
	public static final String SMSHoursTtl= "//*[@id='frmDetails:ctrStorePlanningDetailsTable:6:colSMSHours']";
	public static final String PlannedHoursTtl= "//*[@id='frmDetails:ctrStorePlanningDetailsTable:6:colPlannedPaidHours']";
}	

