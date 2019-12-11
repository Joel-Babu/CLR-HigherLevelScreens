package au.wow.WLPmain.objects;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class WeeklyDepartmentPlanningObjects 
{
	@FindBy(xpath= "/html/frameset/frame[2]") 
	public WebElement DataFrame;
	
	@FindBy(xpath="//*[@id='form2:text50']")
	public WebElement DepartmentHeader;
	
	@FindBy(xpath="//*[@id='form2:ddlDepartment']")
	public WebElement DepartmentDropdown;
	public static final String DataFrameTwo="/html/frameset/frame[2]";
	public static final String SummaryInfocolumn="//*[@id='form2:dgWageSummary:1:text5']";
	public static final String SummaryInfoBudgetcolumn="//*[@id='form2:dgWageSummary:2:text5']";
	public static final String SummaryInfoLastyearcolumn="//*[@id='form2:dgWageSummary:0:text5']";
	public static final String Tablename="//*[@id='form2:dgWageSummary']/tbody";
	public static final String SummaryInfoSales="//*[@id='form2:dgWageSummary:1:text6']";
	public static final String SummaryInfoBudgetSales="//*[@id='form2:dgWageSummary:2:text6']";
	public static final String SummaryInfoWage="//*[@id='form2:dgWageSummary:1:text8']";
	public static final String SummaryInfoBudgetWage="//*[@id='form2:dgWageSummary:2:text8']";
	public static final String SummaryInfoWagepercent="//*[@id='form2:dgWageSummary:1:text7']";
	public static final String SummaryInfoBudgetWagepercent="//*[@id='form2:dgWageSummary:2:text7']";
	public static final String SummaryInfoPlanPaidHours="//*[@id='form2:dgWageSummary:1:text53']";
	public static final String SummaryInfoBudgetPaidHours="//*[@id='form2:dgWageSummary:2:text53']";
	public static final String SummaryInfoORpercent="//*[@id='form2:dgWageSummary:1:text54']";
	public static final String SummaryInfoBudgetORpercent="//*[@id='form2:dgWageSummary:2:text54']";
	public static final String SummaryInfoLYSales="//*[@id='form2:dgWageSummary:0:text6']";
	public static final String SummaryInfoLYWages="//*[@id='form2:dgWageSummary:0:text8']";
	public static final String SummaryInfoLYWageper ="//*[@id='form2:dgWageSummary:0:text7']";
	public static final String SummaryInfoLYHours ="//*[@id='form2:dgWageSummary:0:text53']";
	public static final String SummaryInfoORPer ="//*[@id='form2:dgWageSummary:0:text54']";
	
	
	//Sales History
	@FindBy(xpath="//*[@id='form2:dgSalesHistory:0:text20']")
	public WebElement CPHFirstWk;
	@FindBy(xpath="//*[@id='form2:dgSalesHistory:1:text20']")
	public WebElement CPHSecondWk;
	@FindBy(xpath="//*[@id='form2:dgSalesHistory:2:text20']")
	public WebElement CPHThirdWk;
	@FindBy(xpath="//*[@id='form2:dgSalesHistory:3:text20']")
	public WebElement CPHFourthWk;
	
	public static final String SalesHistoryTable="//*[@id='form2:dgSalesHistory']";
	public static final String SalesWeekday="//*[@id='form2:dgSalesHistory:text15']";
	
	@FindBy(xpath="//*[@id='form2:dgSalesHistory:0:text19']")
	public WebElement FirstWeekCaption;
	@FindBy(xpath="//*[@id='form2:dgSalesHistory:0:text21']")
	public WebElement FirstWeekValTotal;
	
	public static final String FirstWeekMonval="//*[@id='form2:dgSalesHistory:0:text22']";
	public static final String FirstWeekTueval="//*[@id='form2:dgSalesHistory:0:text23']";
	public static final String FirstWeekWedval="//*[@id='form2:dgSalesHistory:0:text24']";
	public static final String FirstWeekThuval="//*[@id='form2:dgSalesHistory:0:text25']";
	public static final String FirstWeekFrival="//*[@id='form2:dgSalesHistory:0:text26']";
	public static final String FirstWeekSatval="//*[@id='form2:dgSalesHistory:0:text27']";
	public static final String FirstWeekSunval="//*[@id='form2:dgSalesHistory:0:text28']";
	
	@FindBy(xpath="//*[@id='form2:dgSalesHistory:1:text19']")
	public WebElement SecondWeekCaption;
	@FindBy(xpath="//*[@id='form2:dgSalesHistory:1:text21']")
	public WebElement SecondWeekValTotal;
	
	public static final String SecondWeekMonval="//*[@id='form2:dgSalesHistory:1:text22']";
	public static final String SecondWeekTueval="//*[@id='form2:dgSalesHistory:1:text23']";
	public static final String SecondWeekWedval="//*[@id='form2:dgSalesHistory:1:text24']";
	public static final String SecondWeekThuval="//*[@id='form2:dgSalesHistory:1:text25']";
	public static final String SecondWeekFrival="//*[@id='form2:dgSalesHistory:1:text26']";
	public static final String SecondWeekSatval="//*[@id='form2:dgSalesHistory:1:text27']";
	public static final String SecondWeekSunval="//*[@id='form2:dgSalesHistory:1:text28']";
	
	@FindBy(xpath="//*[@id='form2:dgSalesHistory:2:text19']")
	public WebElement ThirdWeekCaption;
	@FindBy(xpath="//*[@id='form2:dgSalesHistory:2:text21']")
	public WebElement ThirdWeekValTotal;
	
	public static final String ThirdWeekMonval="//*[@id='form2:dgSalesHistory:2:text22']";
	public static final String ThirdWeekTueval="//*[@id='form2:dgSalesHistory:2:text23']";
	public static final String ThirdWeekWedval="//*[@id='form2:dgSalesHistory:2:text24']";
	public static final String ThirdWeekThuval="//*[@id='form2:dgSalesHistory:2:text25']";
	public static final String ThirdWeekFrival="//*[@id='form2:dgSalesHistory:2:text26']";
	public static final String ThirdWeekSatval="//*[@id='form2:dgSalesHistory:2:text27']";
	public static final String ThirdWeekSunval="//*[@id='form2:dgSalesHistory:2:text28']";
	
	@FindBy(xpath="//*[@id='form2:dgSalesHistory:3:text19']")
	public WebElement FourthWeekCaption;
	@FindBy(xpath="//*[@id='form2:dgSalesHistory:3:text21']")
	public WebElement FourthWeekValTotal;
	
	public static final String FourthWeekMonval="//*[@id='form2:dgSalesHistory:3:text22']";
	public static final String FourthWeekTueval="//*[@id='form2:dgSalesHistory:3:text23']";
	public static final String FourthWeekWedval="//*[@id='form2:dgSalesHistory:3:text24']";
	public static final String FourthWeekThuval="//*[@id='form2:dgSalesHistory:3:text25']";
	public static final String FourthWeekFrival="//*[@id='form2:dgSalesHistory:3:text26']";
	public static final String FourthWeekSatval="//*[@id='form2:dgSalesHistory:3:text27']";
	public static final String FourthWeekSunval="//*[@id='form2:dgSalesHistory:3:text28']";
	
	@FindBy(xpath="//*[@id='form2:dgSalesHistory:4:text19']")
	public WebElement AvgDailyMixCaption;

	public static final String AvgMixMonval="//*[@id='form2:dgSalesHistory:4:text22']";
	public static final String AvgMixTueval="//*[@id='form2:dgSalesHistory:4:text23']";
	public static final String AvgMixWedval="//*[@id='form2:dgSalesHistory:4:text24']";
	public static final String AvgMixThuval="//*[@id='form2:dgSalesHistory:4:text25']";
	public static final String AvgMixFrival="//*[@id='form2:dgSalesHistory:4:text26']";
	public static final String AvgMixSatval="//*[@id='form2:dgSalesHistory:4:text27']";
	public static final String AvgMixSunval="//*[@id='form2:dgSalesHistory:4:text28']";
	
	@FindBy(xpath="//*[@id='form2:dgSalesHistory:5:text19']")
	public WebElement AvgGrowthCaption;

	public static final String AvgGrowthMonval="//*[@id='form2:dgSalesHistory:5:text22']";
	public static final String AvgGrowthTueval="//*[@id='form2:dgSalesHistory:5:text23']";
	public static final String AvgGrowthWedval="//*[@id='form2:dgSalesHistory:5:text24']";
	public static final String AvgGrowthThuval="//*[@id='form2:dgSalesHistory:5:text25']";
	public static final String AvgGrowthFrival="//*[@id='form2:dgSalesHistory:5:text26']";
	public static final String AvgGrowthSatval="//*[@id='form2:dgSalesHistory:5:text27']";
	public static final String AvgGrowthSunval="//*[@id='form2:dgSalesHistory:5:text28']";
	
	//Planning for the Week
	public static final String TableName="//*[@id='form2:tblPlanningGrid']/tbody";
	
	@FindBy(xpath="//*[@id='form2:tblPlanningGrid:lblplannweek']")
	public WebElement ColumnName;
	
	public static final String WeekDay="//*[@id='form2:tblPlanningGrid']/thead/tr/th[dynamic]";
	
	@FindBy(xpath="//*[@id='form2:tblPlanningGrid:0:text29']")
	public WebElement LastYearSalesCaption;	
	@FindBy(xpath="//*[@id='form2:tblPlanningGrid:0:toolTip']")
	public WebElement LastYearSalesTotal;
	
	public static final String LastYearSalesMonVal="//*[@id='form2:tblPlanningGrid:0:text43']";
	public static final String LastYearSalesTueVal="//*[@id='form2:tblPlanningGrid:0:text44']";
	public static final String LastYearSalesWedVal="//*[@id='form2:tblPlanningGrid:0:text45']";
	public static final String LastYearSalesThuVal="//*[@id='form2:tblPlanningGrid:0:text46']";
	public static final String LastYearSalesFriVal="//*[@id='form2:tblPlanningGrid:0:text47']";
	public static final String LastYearSalesSatVal="//*[@id='form2:tblPlanningGrid:0:text48']";
	public static final String LastYearSalesSunVal="//*[@id='form2:tblPlanningGrid:0:text49']";
	
	@FindBy(xpath="//*[@id='form2:tblPlanningGrid:1:text29']")
	public WebElement GeneratedSalesCaption;	
	@FindBy(xpath="//*[@id='form2:tblPlanningGrid:1:text42']")
	public WebElement GeneratedSalesTotal;
	
	public static final String GeneratedSalesMonVal="//*[@id='form2:tblPlanningGrid:1:text43']";
	public static final String GeneratedSalesTueVal="//*[@id='form2:tblPlanningGrid:1:text44']";
	public static final String GeneratedSalesWedVal="//*[@id='form2:tblPlanningGrid:1:text45']";
	public static final String GeneratedSalesThuVal="//*[@id='form2:tblPlanningGrid:1:text46']";
	public static final String GeneratedSalesFriVal="//*[@id='form2:tblPlanningGrid:1:text47']";
	public static final String GeneratedSalesSatVal="//*[@id='form2:tblPlanningGrid:1:text48']";
	public static final String GeneratedSalesSunVal="//*[@id='form2:tblPlanningGrid:1:text49']";
	
	@FindBy(xpath="//*[@id='form2:tblPlanningGrid:2:text51']")
	public WebElement PlannedSalesCaption;	
	@FindBy(xpath="//*[@id='form2:tblPlanningGrid:2:total']")
	public WebElement PlannedSalesTotal;
	
	public static final String PlannedSalesMonVal="//*[@id='form2:tblPlanningGrid:2:day1']";
	public static final String PlannedSalesTueVal="//*[@id='form2:tblPlanningGrid:2:day2']";
	public static final String PlannedSalesWedVal="//*[@id='form2:tblPlanningGrid:2:day3']";
	public static final String PlannedSalesThuVal="//*[@id='form2:tblPlanningGrid:2:day4']";
	public static final String PlannedSalesFriVal="//*[@id='form2:tblPlanningGrid:2:day5']";
	public static final String PlannedSalesSatVal="//*[@id='form2:tblPlanningGrid:2:day6']";
	public static final String PlannedSalesSunVal="//*[@id='form2:tblPlanningGrid:2:day7']";
	
	@FindBy(xpath="//*[@id='form2:tblPlanningGrid:3:text29']")
	public WebElement PlannedSalesVSLYCaption;
		
	public static final String PlannedSalesVSLYMonVal="//*[@id='form2:tblPlanningGrid:3:text43']";
	public static final String PlannedSalesVSLYTueVal="//*[@id='form2:tblPlanningGrid:3:text44']";
	public static final String PlannedSalesVSLYWedVal="//*[@id='form2:tblPlanningGrid:3:text45']";
	public static final String PlannedSalesVSLYThuVal="//*[@id='form2:tblPlanningGrid:3:text46']";
	public static final String PlannedSalesVSLYFriVal="//*[@id='form2:tblPlanningGrid:3:text47']";
	public static final String PlannedSalesVSLYSatVal="//*[@id='form2:tblPlanningGrid:3:text48']";
	public static final String PlannedSalesVSLYSunVal="//*[@id='form2:tblPlanningGrid:3:text49']";

	
	@FindBy(xpath="//*[@id='form2:tblPlanningGrid:4:text29']")
	public WebElement PlannedSalesdailymixCaption;
		
	public static final String PlannedSalesdailymixMonVal="//*[@id='form2:tblPlanningGrid:4:text43']";
	public static final String PlannedSalesdailymixTueVal="//*[@id='form2:tblPlanningGrid:4:text44']";
	public static final String PlannedSalesdailymixWedVal="//*[@id='form2:tblPlanningGrid:4:text45']";
	public static final String PlannedSalesdailymixThuVal="//*[@id='form2:tblPlanningGrid:4:text46']";
	public static final String PlannedSalesdailymixFriVal="//*[@id='form2:tblPlanningGrid:4:text47']";
	public static final String PlannedSalesdailymixSatVal="//*[@id='form2:tblPlanningGrid:4:text48']";
	public static final String PlannedSalesdailymixSunVal="//*[@id='form2:tblPlanningGrid:4:text49']";
	
	
	@FindBy(xpath="//*[@id='form2:tblPlanningGrid:5:text29']")
	public WebElement GeneratedItemPriceCaption;
	@FindBy(xpath="//*[@id='form2:tblPlanningGrid:5:toolTip']")
	public WebElement GeneratedItemPriceTotal;
	
	public static final String GnrtItmPriceMonVal="//*[@id='form2:tblPlanningGrid:5:text43']";
	public static final String GnrtItmPriceTueVal="//*[@id='form2:tblPlanningGrid:5:text44']";
	public static final String GnrtItmPriceWedVal="//*[@id='form2:tblPlanningGrid:5:text45']";
	public static final String GnrtItmPriceThuVal="//*[@id='form2:tblPlanningGrid:5:text46']";
	public static final String GnrtItmPriceFriVal="//*[@id='form2:tblPlanningGrid:5:text47']";
	public static final String GnrtItmPriceSatVal="//*[@id='form2:tblPlanningGrid:5:text48']";
	public static final String GnrtItmPriceSunVal="//*[@id='form2:tblPlanningGrid:5:text49']";
	
	@FindBy(xpath="//*[@id='form2:tblPlanningGrid:6:text51']")
	public WebElement PlannedItemPriceCaption;
	@FindBy(xpath="//*[@id='form2:tblPlanningGrid:6:totalDouble']")
	public WebElement PlannedItemPriceTotal;
	
	@FindBy(xpath="//*[@id='form2:tblPlanningGrid:6:day1Double']")
	public WebElement PlndItmPriceMonValueget;
	@FindBy(xpath="//*[@id='form2:tblPlanningGrid:6:day2Double']")
	public WebElement PlndItmPriceTueValueget;
	@FindBy(xpath="//*[@id='form2:tblPlanningGrid:6:day3Double']")
	public WebElement PlndItmPriceWedValueget;
	@FindBy(xpath="//*[@id='form2:tblPlanningGrid:6:day4Double']")
	public WebElement PlndItmPriceThuValueget;
	@FindBy(xpath="//*[@id='form2:tblPlanningGrid:6:day5Double']")
	public WebElement PlndItmPriceFriValueget;
	@FindBy(xpath="//*[@id='form2:tblPlanningGrid:6:day6Double']")
	public WebElement PlndItmPriceSatValueget;
	@FindBy(xpath="//*[@id='form2:tblPlanningGrid:6:day7Double']")
	public WebElement PlndItmPriceSunValueget;
	
	public static final String PlndItmPriceMonValue="//*[@id='form2:tblPlanningGrid:6:day1Double']";
	public static final String PlndItmPriceTueValue="//*[@id='form2:tblPlanningGrid:6:day2Double']";
	public static final String PlndItmPriceWedValue="//*[@id='form2:tblPlanningGrid:6:day3Double']";
	public static final String PlndItmPriceThuValue="//*[@id='form2:tblPlanningGrid:6:day4Double']";
	public static final String PlndItmPriceFriValue="//*[@id='form2:tblPlanningGrid:6:day5Double']";
	public static final String PlndItmPriceSatValue="//*[@id='form2:tblPlanningGrid:6:day6Double']";
	public static final String PlndItmPriceSunValue="//*[@id='form2:tblPlanningGrid:6:day7Double']";
	
	@FindBy(xpath="//*[@id='form2:tblPlanningGrid:7:text29']")
	public WebElement GeneratedItemCaption;
	@FindBy(xpath="//*[@id='form2:tblPlanningGrid:7:toolTip']")
	public WebElement GeneratedItemTotal;

	public static final String GnrtItmMonVal="//*[@id='form2:tblPlanningGrid:7:text43']";
	public static final String GnrtItmTueVal="//*[@id='form2:tblPlanningGrid:7:text44']";
	public static final String GnrtItmWedVal="//*[@id='form2:tblPlanningGrid:7:text45']";
	public static final String GnrtItmThuVal="//*[@id='form2:tblPlanningGrid:7:text46']";
	public static final String GnrtItmFriVal="//*[@id='form2:tblPlanningGrid:7:text47']";
	public static final String GnrtItmSatVal="//*[@id='form2:tblPlanningGrid:7:text48']";
	public static final String GnrtItmSunVal="//*[@id='form2:tblPlanningGrid:7:text49']";
	
	@FindBy(xpath="//*[@id='form2:tblPlanningGrid:8:text29']")
	public WebElement PlannedItemsCaption;
	@FindBy(xpath="//*[@id='form2:tblPlanningGrid:8:toolTip']")
	public WebElement PlannedItemsTotal;
	
	public static final String PlndItmMonVal="//*[@id='form2:tblPlanningGrid:8:text43']";
	public static final String PlndItmTueVal="//*[@id='form2:tblPlanningGrid:8:text44']";
	public static final String PlndItmWedVal="//*[@id='form2:tblPlanningGrid:8:text45']";
	public static final String PlndItmThuVal="//*[@id='form2:tblPlanningGrid:8:text46']";
	public static final String PlndItmFriVal="//*[@id='form2:tblPlanningGrid:8:text47']";
	public static final String PlndItmSatVal="//*[@id='form2:tblPlanningGrid:8:text48']";
	public static final String PlndItmSunVal="//*[@id='form2:tblPlanningGrid:8:text49']";
	
	@FindBy(xpath="//*[@id='form2:tblPlanningGrid:9:text29']")
	public WebElement PlannedCustCaption;
	@FindBy(xpath="//*[@id='form2:tblPlanningGrid:9:toolTip']")
	public WebElement PlannedCustTotal;
	
	public static final String PlndCustMonVal="//*[@id='form2:tblPlanningGrid:9:text43']";
	public static final String PlndCustTueVal="//*[@id='form2:tblPlanningGrid:9:text44']";
	public static final String PlndCustWedVal="//*[@id='form2:tblPlanningGrid:9:text45']";
	public static final String PlndCustThuVal="//*[@id='form2:tblPlanningGrid:9:text46']";
	public static final String PlndCustFriVal="//*[@id='form2:tblPlanningGrid:9:text47']";
	public static final String PlndCustSatVal="//*[@id='form2:tblPlanningGrid:9:text48']";
	public static final String PlndCustSunVal="//*[@id='form2:tblPlanningGrid:9:text49']";
	
	
	@FindBy(xpath="//*[@id='form2:tblPlanningGrid:10:text29']")
	public WebElement WeekTypeCaption;
	@FindBy(xpath="//*[@id='form2:tblPlanningGrid:10:toolTip']")
	public WebElement WeekTypeOption;
	
	@FindBy(xpath="//*[@id='form2:tblPlanningGrid:11:text29']")
	public WebElement SMSHoursCaption;
	@FindBy(xpath="//*[@id='form2:tblPlanningGrid:11:toolTip']")
	public WebElement SMSHoursTotal;

	public static final String SMSHrsMonVal="//*[@id='form2:tblPlanningGrid:11:text43']";
	public static final String SMSHrsTueVal="//*[@id='form2:tblPlanningGrid:11:text44']";
	public static final String SMSHrsWedVal="//*[@id='form2:tblPlanningGrid:11:text45']";
	public static final String SMSHrsThuVal="//*[@id='form2:tblPlanningGrid:11:text46']";
	public static final String SMSHrsFriVal="//*[@id='form2:tblPlanningGrid:11:text47']";
	public static final String SMSHrsSatVal="//*[@id='form2:tblPlanningGrid:11:text48']";
	public static final String SMSHrsSunVal="//*[@id='form2:tblPlanningGrid:11:text49']";
	
	@FindBy(xpath="//*[@id='form2:tblPlanningGrid:12:text29']")
	public WebElement PlndHrsCaption;
	@FindBy(xpath="//*[@id='form2:tblPlanningGrid:12:toolTip']")
	public WebElement PlndHrsTotal;
	
	public static final String PlndHrsMonVal="//*[@id='form2:tblPlanningGrid:12:text43']";
	public static final String PlndHrsTueVal="//*[@id='form2:tblPlanningGrid:12:text44']";
	public static final String PlndHrsWedVal="//*[@id='form2:tblPlanningGrid:12:text45']";
	public static final String PlndHrsThuVal="//*[@id='form2:tblPlanningGrid:12:text46']";
	public static final String PlndHrsFriVal="//*[@id='form2:tblPlanningGrid:12:text47']";
	public static final String PlndHrsSatVal="//*[@id='form2:tblPlanningGrid:12:text48']";
	public static final String PlndHrsSunVal="//*[@id='form2:tblPlanningGrid:12:text49']";
	
	@FindBy(xpath="//*[@id='form2:tblPlanningGrid:13:text29']")
	public WebElement PlndVarCaption;
	@FindBy(xpath="//*[@id='form2:tblPlanningGrid:13:toolTip']")
	public WebElement PlndVarTotal;
	
	public static final String PlndVarMonVal="//*[@id='form2:tblPlanningGrid:13:text43']";
	public static final String PlndVarTueVal="//*[@id='form2:tblPlanningGrid:13:text44']";
	public static final String PlndVarWedVal="//*[@id='form2:tblPlanningGrid:13:text45']";
	public static final String PlndVarThuVal="//*[@id='form2:tblPlanningGrid:13:text46']";
	public static final String PlndVarFriVal="//*[@id='form2:tblPlanningGrid:13:text47']";
	public static final String PlndVarSatVal="//*[@id='form2:tblPlanningGrid:13:text48']";
	public static final String PlndVarSunVal="//*[@id='form2:tblPlanningGrid:13:text49']";
	
	@FindBy(xpath="//*[@id='form2:tblPlanningGrid:14:text29']")
	public WebElement PlndORCaption;
	@FindBy(xpath="//*[@id='form2:tblPlanningGrid']/tbody/tr[15]/td[3]/span") 
	public WebElement PlndORTotal;

	public static final String PlndORMonVal="//*[@id='form2:tblPlanningGrid']/tbody/tr[15]/td[4]/span";
	public static final String PlndORTueVal="//*[@id='form2:tblPlanningGrid']/tbody/tr[15]/td[5]/span";
	public static final String PlndORWedVal="//*[@id='form2:tblPlanningGrid']/tbody/tr[15]/td[6]/span";
	public static final String PlndORThuVal="//*[@id='form2:tblPlanningGrid']/tbody/tr[15]/td[7]/span";
	public static final String PlndORFriVal="//*[@id='form2:tblPlanningGrid']/tbody/tr[15]/td[8]/span";
	public static final String PlndORSatVal="//*[@id='form2:tblPlanningGrid']/tbody/tr[15]/td[9]/span";
	public static final String PlndORSunVal="//*[@id='form2:tblPlanningGrid']/tbody/tr[15]/td[10]/span";

	@FindBy(xpath="//*[@id='form2:tblPlanningGrid:15:text29']")
	public WebElement WgCostCaption;
	@FindBy(xpath="//*[@id='form2:tblPlanningGrid:15:toolTip']")
	public WebElement WgCostTotal;

	public static final String WgCostMonVal="//*[@id='form2:tblPlanningGrid:15:text43']";
	public static final String WgCostTueVal="//*[@id='form2:tblPlanningGrid:15:text44']";
	public static final String WgCostWedVal="//*[@id='form2:tblPlanningGrid:15:text45']";
	public static final String WgCostThuVal="//*[@id='form2:tblPlanningGrid:15:text46']";
	public static final String WgCostFriVal="//*[@id='form2:tblPlanningGrid:15:text47']";
	public static final String WgCostSatVal="//*[@id='form2:tblPlanningGrid:15:text48']";
	public static final String WgCostSunVal="//*[@id='form2:tblPlanningGrid:15:text49']";

	
	//////////////////////////////////////////////////////////////////////////////////////////////////////////////////

	@FindBy(xpath="//*[@id='form2:tblPlanningGrid:9:text29']")
	public WebElement NonCHeckoutWeekTypeCaption;
	@FindBy(xpath="//*[@id='form2:tblPlanningGrid:9:toolTip']")
	public WebElement NonCHeckoutWeekTypeOption;
	
	@FindBy(xpath="//*[@id='form2:tblPlanningGrid:10:text29']")
	public WebElement NonCHeckoutSMSHoursCaption;
	@FindBy(xpath="//*[@id='form2:tblPlanningGrid:10:toolTip']")
	public WebElement NonCHeckoutSMSHoursTotal;

	public static final String NonCHeckoutSMSHrsMonVal="//*[@id='form2:tblPlanningGrid:10:text43']";
	public static final String NonCHeckoutSMSHrsTueVal="//*[@id='form2:tblPlanningGrid:10:text44']";
	public static final String NonCHeckoutSMSHrsWedVal="//*[@id='form2:tblPlanningGrid:10:text45']";
	public static final String NonCHeckoutSMSHrsThuVal="//*[@id='form2:tblPlanningGrid:10:text46']";
	public static final String NonCHeckoutSMSHrsFriVal="//*[@id='form2:tblPlanningGrid:10:text47']";
	public static final String NonCHeckoutSMSHrsSatVal="//*[@id='form2:tblPlanningGrid:10:text48']";
	public static final String NonCHeckoutSMSHrsSunVal="//*[@id='form2:tblPlanningGrid:10:text49']";
	
	@FindBy(xpath="//*[@id='form2:tblPlanningGrid:11:text29']")
	public WebElement NonCHeckoutPlndHrsCaption;
	@FindBy(xpath="//*[@id='form2:tblPlanningGrid:11:toolTip']")
	public WebElement NonCHeckoutPlndHrsTotal;
	
	public static final String NonCHeckoutPlndHrsMonVal="//*[@id='form2:tblPlanningGrid:11:text43']";
	public static final String NonCHeckoutPlndHrsTueVal="//*[@id='form2:tblPlanningGrid:11:text44']";
	public static final String NonCHeckoutPlndHrsWedVal="//*[@id='form2:tblPlanningGrid:11:text45']";
	public static final String NonCHeckoutPlndHrsThuVal="//*[@id='form2:tblPlanningGrid:11:text46']";
	public static final String NonCHeckoutPlndHrsFriVal="//*[@id='form2:tblPlanningGrid:11:text47']";
	public static final String NonCHeckoutPlndHrsSatVal="//*[@id='form2:tblPlanningGrid:11:text48']";
	public static final String NonCHeckoutPlndHrsSunVal="//*[@id='form2:tblPlanningGrid:11:text49']";
	
	@FindBy(xpath="//*[@id='form2:tblPlanningGrid:12:text29']")
	public WebElement NonCHeckoutPlndVarCaption;
	@FindBy(xpath="//*[@id='form2:tblPlanningGrid:12:toolTip']")
	public WebElement NonCHeckoutPlndVarTotal;
	
	public static final String NonCHeckoutPlndVarMonVal="//*[@id='form2:tblPlanningGrid:12:text43']";
	public static final String NonCHeckoutPlndVarTueVal="//*[@id='form2:tblPlanningGrid:12:text44']";
	public static final String NonCHeckoutPlndVarWedVal="//*[@id='form2:tblPlanningGrid:12:text45']";
	public static final String NonCHeckoutPlndVarThuVal="//*[@id='form2:tblPlanningGrid:12:text46']";
	public static final String NonCHeckoutPlndVarFriVal="//*[@id='form2:tblPlanningGrid:12:text47']";
	public static final String NonCHeckoutPlndVarSatVal="//*[@id='form2:tblPlanningGrid:12:text48']";
	public static final String NonCHeckoutPlndVarSunVal="//*[@id='form2:tblPlanningGrid:12:text49']";
	
	@FindBy(xpath="//*[@id='form2:tblPlanningGrid:13:text29']")
	public WebElement NonCHeckoutPlndORCaption;
	@FindBy(xpath="//*[@id='form2:tblPlanningGrid']/tbody/tr[14]/td[3]/span") 
	public WebElement NonCHeckoutPlndORTotal;

	public static final String NonCHeckoutPlndORMonVal="//*[@id='form2:tblPlanningGrid']/tbody/tr[14]/td[4]/span";
	public static final String NonCHeckoutPlndORTueVal="//*[@id='form2:tblPlanningGrid']/tbody/tr[14]/td[5]/span";
	public static final String NonCHeckoutPlndORWedVal="//*[@id='form2:tblPlanningGrid']/tbody/tr[14]/td[6]/span";
	public static final String NonCHeckoutPlndORThuVal="//*[@id='form2:tblPlanningGrid']/tbody/tr[14]/td[7]/span";
	public static final String NonCHeckoutPlndORFriVal="//*[@id='form2:tblPlanningGrid']/tbody/tr[14]/td[8]/span";
	public static final String NonCHeckoutPlndORSatVal="//*[@id='form2:tblPlanningGrid']/tbody/tr[14]/td[9]/span";
	public static final String NonCHeckoutPlndORSunVal="//*[@id='form2:tblPlanningGrid']/tbody/tr[14]/td[10]/span";

	@FindBy(xpath="//*[@id='form2:tblPlanningGrid:14:text29']")
	public WebElement NonCHeckoutWgCostCaption;
	@FindBy(xpath="//*[@id='form2:tblPlanningGrid:14:toolTip']")
	public WebElement NonCHeckoutWgCostTotal;

	public static final String NonCHeckoutWgCostMonVal="//*[@id='form2:tblPlanningGrid:14:text43']";
	public static final String NonCHeckoutWgCostTueVal="//*[@id='form2:tblPlanningGrid:14:text44']";
	public static final String NonCHeckoutWgCostWedVal="//*[@id='form2:tblPlanningGrid:14:text45']";
	public static final String NonCHeckoutWgCostThuVal="//*[@id='form2:tblPlanningGrid:14:text46']";
	public static final String NonCHeckoutWgCostFriVal="//*[@id='form2:tblPlanningGrid:14:text47']";
	public static final String NonCHeckoutWgCostSatVal="//*[@id='form2:tblPlanningGrid:14:text48']";
	public static final String NonCHeckoutWgCostSunVal="//*[@id='form2:tblPlanningGrid:14:text49']";
		
	@FindBy(xpath="//*[@id='form2:tblPlanningGrid:11:text29']")
	public WebElement InventoryCaption;
	@FindBy(xpath="//*[@id='form2:tblPlanningGrid:11:toolTip']")
	public WebElement InventoryTotal;
	
	public static final String InventoryMonValue="//*[@id='form2:tblPlanningGrid:11:textdynamic']";
	
	@FindBy(xpath="//*[@id='form2:tblPlanningGrid:12:text29']")
	public WebElement MerchandisingCaption;
	@FindBy(xpath="//*[@id='form2:tblPlanningGrid:12:toolTip']")
	public WebElement MerchandisingTotal;
	public static final String MerchandisingMonValue="//*[@id='form2:tblPlanningGrid:12:textdynamic']";
	
	@FindBy(xpath="//*[@id='form2:tblPlanningGrid:13:text29']")
	public WebElement ReplenishmentCaption;
	@FindBy(xpath="//*[@id='form2:tblPlanningGrid:13:toolTip']")
	public WebElement ReplenishmentTotal;
	public static final String ReplenishmentMonValue="//*[@id='form2:tblPlanningGrid:13:textdynamic']";
	
	@FindBy(xpath="//*[@id='form2:tblPlanningGrid:14:text29']")
	public WebElement KronosScheduledHoursCaption;
	@FindBy(xpath="//*[@id='form2:tblPlanningGrid:14:toolTip']")
	public WebElement KronosScheduledHoursTotal;
	public static final String KronosScheduledHoursMonValue="//*[@id='form2:tblPlanningGrid:14:textdynamic']";
	
	@FindBy(xpath="//*[@id='form2:tblPlanningGrid:15:text29']")
	public WebElement PlanVarianceCaption;
	@FindBy(xpath="//*[@id='form2:tblPlanningGrid:15:toolTip']")
	public WebElement PlanVarianceTotal;
	public static final String PlanVarianceMonValue="//*[@id='form2:tblPlanningGrid:15:textdynamic']";
	
	@FindBy(xpath="//*[@id='form2:tblPlanningGrid:16:text29']")
	public WebElement OperatingRatioCaption;
	@FindBy(xpath="//*[@id='form2:tblPlanningGrid']/tbody/tr[17]/td[3]/span")
	public WebElement OperatingRatioTotal;
	public static final String OperatingRatioMonValue="//*[@id='form2:tblPlanningGrid']/tbody/tr[17]/td[dynamic]/span";
	
	@FindBy(xpath="//*[@id='form2:tblPlanningGrid:17:text29']")
	public WebElement WageCostCaption;
	@FindBy(xpath="//*[@id='form2:tblPlanningGrid:17:toolTip']")
	public WebElement WageCostTotal;
	public static final String WageCostMonValue="//*[@id='form2:tblPlanningGrid:17:textdynamic']";
	
	//Replenishment
	public static final String Replenishbtn="//*[@id='form2:button6']";
	public static final String Inventorybtn="//*[@id='form2:button4']";
	public static final String Merchandisingbtn="//*[@id='form2:button3']";
	
	
	//Pst537	

	@FindBy(xpath="//*[ @style='background: green;' ]")
	public WebElement PlanCompleteSelectedCheckBoxget;
	@FindBy(xpath="//*[ @style='background: red;' ]")
	public WebElement PlanCompleteUnSelectedCheckBoxget;
	@FindBy(xpath="//*[@id='form2:button1' and @value='Save']")
	public WebElement SaveButtoninPlanCompleteWeb;
	public static final String PlanCompleteUnSelectedCheckBox="//*[ @style='background: red;' ]";
	public static final String PlanCompleteSelectedCheckBox="//*[ @style='background: green;' ]";
	public static final String SaveButtoninPlanComplete="//*[@id='form2:button1' and @value='Save']";
}


