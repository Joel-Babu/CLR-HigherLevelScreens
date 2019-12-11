package au.wow.WLPmain.objects;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class DailyPlanningStoreObjects 
{
	@FindBy(xpath= "//*[@id='dailyForm:dd1Dailyplan']")
	public WebElement PageDropdown;
	
	@FindBy(xpath= "/html/frameset/frame[2]") 
	public WebElement DataFrame;
	
	@FindBy(xpath= "//*[@id='dailyForm']/table[2]/tbody/tr/td[1]/table/tbody/tr/td[3]/span") 
	public WebElement View;
	
	@FindBy(xpath= "//*[@id='form1:buttonLogoff']")
	public WebElement Logoff;
	
	@FindBy(xpath= "/html/frameset/frame[1]")
	public WebElement TitleFrame;
	
	@FindBy(xpath="//span[contains(text(),'Departments')]")
	public WebElement DepartmentHeader;
	
	@FindBy(xpath= "//*[@id='dailyForm:dd1Dailyplan1']")
	public WebElement DepartmentDropdown;
	
	//Radio Button
	public static final String ActualRadio="//*[@id=\"dailyForm:paflag:1\"]";
	public static final String PlannedRadio="//*[@id=\"dailyForm:paflag:0\"]";
	@FindBy(xpath="//*[@id=\"dailyForm:paflag:0\"]")
	public WebElement ClickPlannedBtn;
	@FindBy(xpath="//*[@id=\"dailyForm:paflag:1\"]")
	public WebElement ClickActualBtn;
	
	/*@FindBy(xpath="//*[@id=\"dailyForm:paflag:1\"]")
	public WebElement PlannedTable;
	
	
	@FindBy(xpath="//*[@id=\"dailyForm:paflag:1\"]")
	public WebElement ActualTable;
	
	
	@FindBy(xpath="//*[@id=\"dailyForm:paflag:1\"]")
	public WebElement VarianceTable;
	
	
	@FindBy(xpath="//*[@id=\"dailyForm:paflag:1\"]")
	public WebElement PlannedHRTable;
	
	
	@FindBy(xpath="//*[@id=\"dailyForm:paflag:1\"]")
	public WebElement ActualHRTable;
	
	
	@FindBy(xpath="//*[@id=\"dailyForm:paflag:1\"]")
	public WebElement VarianceHRTable;
	*/
	
	public static final String Table="//*[@id='dailyForm:dailyReport']/tbody/tr";
	/////NEW
	public static final String PlannedTable="//*[contains(@id,'dailyForm:j') and contains(@id,':dailyReport') and not(contains(@id,'Actual'))]/tbody/tr";
	public static final String ActualTable="//*[contains(@id,'dailyForm:j') and contains(@id,':dailyReportActual')]/tbody/tr";
	public static final String VarianceTable="//*[contains(@id,'dailyForm:j') and contains(@id,':newvaraincedailyReportsales')]/tbody/tr";
	//public static final String Table="//*[@id='dailyForm:dailyReport']/tbody/tr";
	
	
	public static final String HrTable="//*[@id='dailyForm:dailyReportHours:tb']"; //*[@id="dailyForm:dailyReportHoursActual:tb"]
	/////NEW
	public static final String PlannedHRTable="//*[contains(@id,'dailyForm:j') and contains(@id,':dailyReportHours') and not(contains(@id,'Actual'))]/tbody/tr";
	public static final String ActualHRTable="//*[contains(@id,'dailyForm:j') and contains(@id,':dailyReportHoursActual')]/tbody/tr";
	public static final String VarianceHRTable="//*[contains(@id,'dailyForm:j') and contains(@id,':dailyReporthoursVariance')]/tbody/tr";
	
	public static final String ORTable="//*[@id='dailyForm:dailyReportOR']";//*[@id="dailyForm:dailyReportORPlan"]
	///NEW
	public static final String PlannedORTable="//*[contains(@id,'dailyForm:j') and contains(@id,':dailyReportORPlan')]/tbody/tr";
	public static final String ActualORTable="//*[contains(@id,'dailyForm:j') and contains(@id,':dailyReportORAct')]/tbody/tr";
	public static final String VarianceORTable="//*[contains(@id,'dailyForm:j') and contains(@id,':dailyReportOR') and not(contains(@id,'Act') or contains(@id,'Plan'))]/tbody/tr";
	
	
	
	
	public static final String ORTableActl="//*[@id=\"dailyForm:dailyReportORAct:tb\"]/tr";
	
	public static final String ScreenName="//*[@id='dailyForm']/table[1]/tbody/tr/td[1]/span[1]";	//ScreenName ReadOnly
	
	//VIEW - Sales, Wages, Wage%, Items, Item Price, Customers, CPH
	public static final String Departments="//td[contains(@id,'dailyForm:j') and contains(@id,':dailyReport:dynamic:j')][1]";	//Department Column 
	public static final String Monday="//td[contains(@id,'dailyForm:j') and contains(@id,':dailyReport:dynamic:j')][2]";		//Monday Column 
	public static final String Tuesday="//td[contains(@id,'dailyForm:j') and contains(@id,':dailyReport:dynamic:j')][3]";	//Tuesday Column 
	public static final String Wednesday="//td[contains(@id,'dailyForm:j') and contains(@id,':dailyReport:dynamic:j')][4]";	//Wednesday Column 
	public static final String Thursday="//td[contains(@id,'dailyForm:j') and contains(@id,':dailyReport:dynamic:j')][5]";	//Thursday Column 
	public static final String Friday="//td[contains(@id,'dailyForm:j') and contains(@id,':dailyReport:dynamic:j')][6]";		//Friday Column 
	public static final String Saturday="//td[contains(@id,'dailyForm:j') and contains(@id,':dailyReport:dynamic:j')][7]";	//Saturday Column 
	public static final String Sunday="//td[contains(@id,'dailyForm:j') and contains(@id,':dailyReport:dynamic:j')][8]";		//Sunday Column 
	public static final String WeekToDate="//td[contains(@id,'dailyForm:j') and contains(@id,':dailyReport:dynamic:j')][9]";		//Week Total Column 
	
	public static final String Total="//td[contains(@id,'dailyForm:j') and contains(@id,':dailyReport:j')][1]";	//StoreTotal ReadOnly
	public static final String MondayTtl="//td[contains(@id,'dailyForm:j') and contains(@id,':dailyReport:j')][2]";	//Monday Total 	
	public static final String TuesdayTtl="//td[contains(@id,'dailyForm:j') and contains(@id,':dailyReport:j')][3]";	//Tuesday Total 	
	public static final String WednesdayTtl="//td[contains(@id,'dailyForm:j') and contains(@id,':dailyReport:j')][4]";	//Wednesday Total 	
	public static final String ThursdayTtl="//td[contains(@id,'dailyForm:j') and contains(@id,':dailyReport:j')][5]";	//Thursday Total 	
	public static final String FridayTtl="//td[contains(@id,'dailyForm:j') and contains(@id,':dailyReport:j')][6]";		//Friday Total 	
	public static final String SaturdayTtl="//td[contains(@id,'dailyForm:j') and contains(@id,':dailyReport:j')][7]";	//Saturday Total 	
	public static final String SundayTtl="//td[contains(@id,'dailyForm:j') and contains(@id,':dailyReport:j')][8]";		//Sunday Total 	
	public static final String WeekToDateTtl="//td[contains(@id,'dailyForm:j') and contains(@id,':dailyReport:j')][9]";	//Week Total 	
	
	
	//////New Actual
	public static final String ACTDepartments="//td[contains(@id,'dailyForm:j') and contains(@id,':dailyReportActual:dynamic:j')][1]";	//Department Column 
	public static final String ACTMonday="//td[contains(@id,'dailyForm:j') and contains(@id,':dailyReportActual:dynamic:j')][2]";		//Monday Column 
	public static final String ACTTuesday="//td[contains(@id,'dailyForm:j') and contains(@id,':dailyReportActual:dynamic:j')][3]";	//Tuesday Column 
	public static final String ACTWednesday="//td[contains(@id,'dailyForm:j') and contains(@id,':dailyReportActual:dynamic:j')][4]";	//Wednesday Column 
	public static final String ACTThursday="//td[contains(@id,'dailyForm:j') and contains(@id,':dailyReportActual:dynamic:j')][5]";	//Thursday Column 
	public static final String ACTFriday="//td[contains(@id,'dailyForm:j') and contains(@id,':dailyReportActual:dynamic:j')][6]";		//Friday Column 
	public static final String ACTSaturday="//td[contains(@id,'dailyForm:j') and contains(@id,':dailyReportActual:dynamic:j')][7]";	//Saturday Column 
	public static final String ACTSunday="//td[contains(@id,'dailyForm:j') and contains(@id,':dailyReportActual:dynamic:j')][8]";		//Sunday Column 
	public static final String ACTWeekToDate="//td[contains(@id,'dailyForm:j') and contains(@id,':dailyReportActual:dynamic:j')][9]";		//Week Total Column 
	
	public static final String ACTTotal="//td[contains(@id,'dailyForm:j') and contains(@id,':dailyReportActual:j')][1]";	//StoreTotal ReadOnly
	public static final String ACTMondayTtl="//td[contains(@id,'dailyForm:j') and contains(@id,':dailyReportActual:j')][2]";	//Monday Total 	
	public static final String ACTTuesdayTtl="//td[contains(@id,'dailyForm:j') and contains(@id,':dailyReportActual:j')][3]";	//Tuesday Total 	
	public static final String ACTWednesdayTtl="//td[contains(@id,'dailyForm:j') and contains(@id,':dailyReportActual:j')][4]";	//Wednesday Total 	
	public static final String ACTThursdayTtl="//td[contains(@id,'dailyForm:j') and contains(@id,':dailyReportActual:j')][5]";	//Thursday Total 	
	public static final String ACTFridayTtl="//td[contains(@id,'dailyForm:j') and contains(@id,':dailyReportActual:j')][6]";		//Friday Total 	
	public static final String ACTSaturdayTtl="//td[contains(@id,'dailyForm:j') and contains(@id,':dailyReportActual:j')][7]";	//Saturday Total 	
	public static final String ACTSundayTtl="//td[contains(@id,'dailyForm:j') and contains(@id,':dailyReportActual:j')][8]";		//Sunday Total 	
	public static final String ACTWeekToDateTtl="//td[contains(@id,'dailyForm:j') and contains(@id,':dailyReportActual:j')][9]";	//Week Total 	
	
	
	
	
	
	/////New Variance
	public static final String VARDepartments="//td[contains(@id,'dailyForm:j') and contains(@id,':newvaraincedailyReportsales:dynamic:j')][1]";	//Department Column 
	public static final String VARMonday="//td[contains(@id,'dailyForm:j') and contains(@id,':newvaraincedailyReportsales:dynamic:j')][2]";		//Monday Column 
	public static final String VARTuesday="//td[contains(@id,'dailyForm:j') and contains(@id,':newvaraincedailyReportsales:dynamic:j')][3]";	//Tuesday Column 
	public static final String VARWednesday="//td[contains(@id,'dailyForm:j') and contains(@id,':newvaraincedailyReportsales:dynamic:j')][4]";	//Wednesday Column 
	public static final String VARThursday="//td[contains(@id,'dailyForm:j') and contains(@id,':newvaraincedailyReportsales:dynamic:j')][5]";	//Thursday Column 
	public static final String VARFriday="//td[contains(@id,'dailyForm:j') and contains(@id,':newvaraincedailyReportsales:dynamic:j')][6]";		//Friday Column
	public static final String VARSaturday="//td[contains(@id,'dailyForm:j') and contains(@id,':newvaraincedailyReportsales:dynamic:j')][7]";	//Saturday Column 
	public static final String VARSunday="//td[contains(@id,'dailyForm:j') and contains(@id,':newvaraincedailyReportsales:dynamic:j')][8]";		//Sunday Column 
	public static final String VARWeekToDate="//td[contains(@id,'dailyForm:j') and contains(@id,':newvaraincedailyReportsales:dynamic:j')][9]";		//Week Total Column 
	
	public static final String VARTotal="//td[contains(@id,'dailyForm:j') and contains(@id,':newvaraincedailyReportsales:j')][1]";	//StoreTotal ReadOnly
	public static final String VARMondayTtl="//td[contains(@id,'dailyForm:j') and contains(@id,':newvaraincedailyReportsales:j')][2]";	//Monday Total 	
	public static final String VARTuesdayTtl="//td[contains(@id,'dailyForm:j') and contains(@id,':newvaraincedailyReportsales:j')][3]";	//Tuesday Total 	
	public static final String VARWednesdayTtl="//td[contains(@id,'dailyForm:j') and contains(@id,':newvaraincedailyReportsales:j')][4]";	//Wednesday Total 	
	public static final String VARThursdayTtl="//td[contains(@id,'dailyForm:j') and contains(@id,':newvaraincedailyReportsales:j')][5]";	//Thursday Total 	
	public static final String VARFridayTtl="//td[contains(@id,'dailyForm:j') and contains(@id,':newvaraincedailyReportsales:j')][6]";		//Friday Total 	
	public static final String VARSaturdayTtl="//td[contains(@id,'dailyForm:j') and contains(@id,':newvaraincedailyReportsales:j')][7]";	//Saturday Total 	
	public static final String VARSundayTtl="//td[contains(@id,'dailyForm:j') and contains(@id,':newvaraincedailyReportsales:j')][8]";		//Sunday Total 	
	public static final String VARWeekToDateTtl="//td[contains(@id,'dailyForm:j') and contains(@id,':newvaraincedailyReportsales:j')][9]";	//Week Total 	
	
	
	
	//VIEW - HOURS - PLAN
	public static final String HrDepartments="//td[contains(@id,'dailyForm:j') and contains(@id,':dailyReportHours:dynamic:j')][1]";	//Department Column 
	
	//Rostered Hours
	public static final String HrMonday="//td[contains(@id,'dailyForm:j') and contains(@id,':dailyReportHours:dynamic:j')][2]";		//Monday Rostered Hours Column
	public static final String HrTuesday="//td[contains(@id,'dailyForm:j') and contains(@id,':dailyReportHours:dynamic:j')][4]";	//Tuesday Rostered Hours Column
	public static final String HrWednesday="//td[contains(@id,'dailyForm:j') and contains(@id,':dailyReportHours:dynamic:j')][6]";	//Wednesday Rostered Hours Column
	public static final String HrThursday="//td[contains(@id,'dailyForm:j') and contains(@id,':dailyReportHours:dynamic:j')][8]";	//Thursday Rostered Hours Column
	public static final String HrFriday="//td[contains(@id,'dailyForm:j') and contains(@id,':dailyReportHours:dynamic:j')][10]";	//Friday Rostered Hours Column
	public static final String HrSaturday="//td[contains(@id,'dailyForm:j') and contains(@id,':dailyReportHours:dynamic:j')][12]";	//Saturday Rostered Hours Column
	public static final String HrSunday="//td[contains(@id,'dailyForm:j') and contains(@id,':dailyReportHours:dynamic:j')][14]";	//Sunday Rostered Hours Column
	public static final String HrWeekToDate="//td[contains(@id,'dailyForm:j') and contains(@id,':dailyReportHours:dynamic:j')][16]";	//Week Total Rostered Hours Column
	
	public static final String HrTotal="//td[contains(@id,'dailyForm:j') and contains(@id,':dailyReportHours:j')][1]";		//StoreTotal ReadOnly
	public static final String HrMondayTtl="//td[contains(@id,'dailyForm:j') and contains(@id,':dailyReportHours:j')][2]";	//Monday Rostered Hours Total
	public static final String HrTuesdayTtl="//td[contains(@id,'dailyForm:j') and contains(@id,':dailyReportHours:j')][4]";	//Tuesday Rostered Hours Total
	public static final String HrWednesdayTtl="//td[contains(@id,'dailyForm:j') and contains(@id,':dailyReportHours:j')][6]";	//Wednesday Rostered Hours Total
	public static final String HrThursdayTtl="//td[contains(@id,'dailyForm:j') and contains(@id,':dailyReportHours:j')][8]";	//Thursday Rostered Hours Total
	public static final String HrFridayTtl="//td[contains(@id,'dailyForm:j') and contains(@id,':dailyReportHours:j')][10]";		//Friday Rostered Hours Total
	public static final String HrSaturdayTtl="//td[contains(@id,'dailyForm:j') and contains(@id,':dailyReportHours:j')][12]";	//Saturday Rostered Hours Total
	public static final String HrSundayTtl="//td[contains(@id,'dailyForm:j') and contains(@id,':dailyReportHours:j')][14]";		//Sunday Rostered Hours Total
	public static final String HrWeekToDateTtl="//td[contains(@id,'dailyForm:j') and contains(@id,':dailyReportHours:j')][16]";	//WeekTotal Rostered Hours Total
		
	//PayPlan Hours
	public static final String PayHrMonday="//td[contains(@id,'dailyForm:j') and contains(@id,':dailyReportHours:dynamic:j')][3]";	//Monday PayPlan Hours Column
	public static final String PayHrTuesday="//td[contains(@id,'dailyForm:j') and contains(@id,':dailyReportHours:dynamic:j')][5]";	//Tuesday PayPlan Hours Column
	public static final String PayHrWednesday="//td[contains(@id,'dailyForm:j') and contains(@id,':dailyReportHours:dynamic:j')][7]";	//Wednesday PayPlan Hours Column
	public static final String PayHrThursday="//td[contains(@id,'dailyForm:j') and contains(@id,':dailyReportHours:dynamic:j')][9]";	//Thursday PayPlan Hours Column
	public static final String PayHrFriday="//td[contains(@id,'dailyForm:j') and contains(@id,':dailyReportHours:dynamic:j')][11]";	//Friday PayPlan Hours Column
	public static final String PayHrSaturday="//td[contains(@id,'dailyForm:j') and contains(@id,':dailyReportHours:dynamic:j')][13]";	//Saturday PayPlan Hours Column
	public static final String PayHrSunday="//td[contains(@id,'dailyForm:j') and contains(@id,':dailyReportHours:dynamic:j')][15]";	//Sunday PayPlan Hours Column
	public static final String PayHrWeekToDate="//td[contains(@id,'dailyForm:j') and contains(@id,':dailyReportHours:dynamic:j')][17]";	//Week Total PayPlan Hours Column

	public static final String PayHrMondayTtl="//td[contains(@id,'dailyForm:j') and contains(@id,':dailyReportHours:j')][3]";	//Monday PayPlan Hours Total
	public static final String PayHrTuesdayTtl="//td[contains(@id,'dailyForm:j') and contains(@id,':dailyReportHours:j')][5]";	//Tuesday PayPlan Hours Total
	public static final String PayHrWednesdayTtl="//td[contains(@id,'dailyForm:j') and contains(@id,':dailyReportHours:j')][7]";	//Wednesday PayPlan Hours Total
	public static final String PayHrThursdayTtl="//td[contains(@id,'dailyForm:j') and contains(@id,':dailyReportHours:j')][9]";	//Thursday PayPlan Hours Total
	public static final String PayHrFridayTtl="//td[contains(@id,'dailyForm:j') and contains(@id,':dailyReportHours:j')][11]";	//Friday PayPlan Hours Total
	public static final String PayHrSaturdayTtl="//td[contains(@id,'dailyForm:j') and contains(@id,':dailyReportHours:j')][13]";	//Saturday PayPlan Hours Total
	public static final String PayHrSundayTtl="//td[contains(@id,'dailyForm:j') and contains(@id,':dailyReportHours:j')][15]";	//Sunday PayPlan Hours Total
	public static final String PayHrWeekToDateTtl="//td[contains(@id,'dailyForm:j') and contains(@id,':dailyReportHours:j')][17]";	//WeekTotal PayPlan Hours Total
		
	//VIEW - HOURS - ACTUAL
	public static final String ActualHrDepartments="//td[contains(@id,'dailyForm:j') and contains(@id,':dailyReportHoursActual:dynamic:j')][1]";
		
	//Worked Hours
	public static final String ActualHrMonday="//td[contains(@id,'dailyForm:j') and contains(@id,':dailyReportHoursActual:dynamic:j')][2]";	//Monday Worked Hours Column
	public static final String ActualHrTuesday="//td[contains(@id,'dailyForm:j') and contains(@id,':dailyReportHoursActual:dynamic:j')][4]";	//Tuesday Worked Hours Column
	public static final String ActualHrWednesday="//td[contains(@id,'dailyForm:j') and contains(@id,':dailyReportHoursActual:dynamic:j')][6]";	//Wednesday Worked Hours Column
	public static final String ActualHrThursday="//td[contains(@id,'dailyForm:j') and contains(@id,':dailyReportHoursActual:dynamic:j')][8]";	//Thursday Worked Hours Column
	public static final String ActualHrFriday="//td[contains(@id,'dailyForm:j') and contains(@id,':dailyReportHoursActual:dynamic:j')][10]";	//Friday Worked Hours Column
	public static final String ActualHrSaturday="//td[contains(@id,'dailyForm:j') and contains(@id,':dailyReportHoursActual:dynamic:j')][12]";	//Saturday Worked Hours Column
	public static final String ActualHrSunday="//td[contains(@id,'dailyForm:j') and contains(@id,':dailyReportHoursActual:dynamic:j')][14]";	//Sunday Worked Hours Column
	public static final String ActualHrWeekToDate="//td[contains(@id,'dailyForm:j') and contains(@id,':dailyReportHoursActual:dynamic:j')][16]";	//WeekTotal Worked Hours Column
			
	public static final String ActualHrTotal="//td[contains(@id,'dailyForm:j') and contains(@id,':dailyReportHoursActual:j')][1]";		//StoreTotal ReadOnly
	public static final String ActualHrMondayTtl="//td[contains(@id,'dailyForm:j') and contains(@id,':dailyReportHoursActual:j')][2]";	//Monday Worked Hours Total
	public static final String ActualHrTuesdayTtl="//td[contains(@id,'dailyForm:j') and contains(@id,':dailyReportHoursActual:j')][4]";	//Tuesday Worked Hours Total
	public static final String ActualHrWednesdayTtl="//td[contains(@id,'dailyForm:j') and contains(@id,':dailyReportHoursActual:j')][6]";	//Wednesday Worked Hours Total
	public static final String ActualHrThursdayTtl="//td[contains(@id,'dailyForm:j') and contains(@id,':dailyReportHoursActual:j')][8]";	//Thursday Worked Hours Total
	public static final String ActualHrFridayTtl="//td[contains(@id,'dailyForm:j') and contains(@id,':dailyReportHoursActual:j')][10]";	//Friday Worked Hours Total	
	public static final String ActualHrSaturdayTtl="//td[contains(@id,'dailyForm:j') and contains(@id,':dailyReportHoursActual:j')][12]";	//Saturday Worked Hours Total
	public static final String ActualHrSundayTtl="//td[contains(@id,'dailyForm:j') and contains(@id,':dailyReportHoursActual:j')][14]";	//Sunday Worked Hours Total
	public static final String ActualHrWeekToDateTtl="//td[contains(@id,'dailyForm:j') and contains(@id,':dailyReportHoursActual:j')][16]";	//WeekTotal Worked Hours Total
			
	//Paid Hours
	public static final String ActualPayHrMonday="//td[contains(@id,'dailyForm:j') and contains(@id,':dailyReportHoursActual:dynamic:j')][3]";	//Monday Paid Hours Column
	public static final String ActualPayHrTuesday="//td[contains(@id,'dailyForm:j') and contains(@id,':dailyReportHoursActual:dynamic:j')][5]";	//Tuesday Paid Hours Column
	public static final String ActualPayHrWednesday="//td[contains(@id,'dailyForm:j') and contains(@id,':dailyReportHoursActual:dynamic:j')][7]";	//Wed Paid Hours Column
	public static final String ActualPayHrThursday="//td[contains(@id,'dailyForm:j') and contains(@id,':dailyReportHoursActual:dynamic:j')][9]";	//Thur Paid Hours Column
	public static final String ActualPayHrFriday="//td[contains(@id,'dailyForm:j') and contains(@id,':dailyReportHoursActual:dynamic:j')][11]";	//Fri Paid Hours Column
	public static final String ActualPayHrSaturday="//td[contains(@id,'dailyForm:j') and contains(@id,':dailyReportHoursActual:dynamic:j')][13]";	//Sat Paid Hours Column
	public static final String ActualPayHrSunday="//td[contains(@id,'dailyForm:j') and contains(@id,':dailyReportHoursActual:dynamic:j')][15]";	//Sun Paid Hours Column
	public static final String ActualPayHrWeekToDate="//td[contains(@id,'dailyForm:j') and contains(@id,':dailyReportHoursActual:dynamic:j')][17]";	//WeekTotal Paid Hours Column

	public static final String ActualPayHrMondayTtl="//td[contains(@id,'dailyForm:j') and contains(@id,':dailyReportHoursActual:j')][3]";	//Monday Paid Hours Total
	public static final String ActualPayHrTuesdayTtl="//td[contains(@id,'dailyForm:j') and contains(@id,':dailyReportHoursActual:j')][5]";	//Tue Paid Hours Total
	public static final String ActualPayHrWednesdayTtl="//td[contains(@id,'dailyForm:j') and contains(@id,':dailyReportHoursActual:j')][7]";	//Wed Paid Hours Total
	public static final String ActualPayHrThursdayTtl="//td[contains(@id,'dailyForm:j') and contains(@id,':dailyReportHoursActual:j')][9]";	//Thur Paid Hours Total
	public static final String ActualPayHrFridayTtl="//td[contains(@id,'dailyForm:j') and contains(@id,':dailyReportHoursActual:j')][11]";	//Fri Paid Hours Total
	public static final String ActualPayHrSaturdayTtl="//td[contains(@id,'dailyForm:j') and contains(@id,':dailyReportHoursActual:j')][13]";	//Sat Paid Hours Total
	public static final String ActualPayHrSundayTtl="//td[contains(@id,'dailyForm:j') and contains(@id,':dailyReportHoursActual:j')][15]";	//Sun Paid Hours Total
	public static final String ActualPayHrWeekToDateTtl="//td[contains(@id,'dailyForm:j') and contains(@id,':dailyReportHoursActual:j')][17]";	//WeekTotal Paid Hours Total

	//UPDATED (VIEW - HOURS - VARIANCE)
		public static final String VarHrDepartments="//td[contains(@id,'dailyForm:j') and contains(@id,':dailyReporthoursVariance:dynamic:j')][1]";
			
		//Worked Hours
		public static final String VarHrMonday="//td[contains(@id,'dailyForm:j') and contains(@id,':dailyReporthoursVariance:dynamic:j')][2]";	//Monday Worked Hours Column
		public static final String VarHrTuesday="//td[contains(@id,'dailyForm:j') and contains(@id,':dailyReporthoursVariance:dynamic:j')][4]";	//Tuesday Worked Hours Column
		public static final String VarHrWednesday="//td[contains(@id,'dailyForm:j') and contains(@id,':dailyReporthoursVariance:dynamic:j')][6]";	//Wednesday Worked Hours Column
		public static final String VarHrThursday="//td[contains(@id,'dailyForm:j') and contains(@id,':dailyReporthoursVariance:dynamic:j')][8]";	//Thursday Worked Hours Column
		public static final String VarHrFriday="//td[contains(@id,'dailyForm:j') and contains(@id,':dailyReporthoursVariance:dynamic:j')][10]";	//Friday Worked Hours Column
		public static final String VarHrSaturday="//td[contains(@id,'dailyForm:j') and contains(@id,':dailyReporthoursVariance:dynamic:j')][12]";	//Saturday Worked Hours Column
		public static final String VarHrSunday="//td[contains(@id,'dailyForm:j') and contains(@id,':dailyReporthoursVariance:dynamic:j')][14]";	//Sunday Worked Hours Column
		public static final String VarHrWeekToDate="//td[contains(@id,'dailyForm:j') and contains(@id,':dailyReporthoursVariance:dynamic:j')][16]";	//WeekTotal Worked Hours Column
				
		public static final String VarHrTotal="//td[contains(@id,'dailyForm:j') and contains(@id,':dailyReporthoursVariance:j')][1]";		//StoreTotal ReadOnly
		public static final String VarHrMondayTtl="//td[contains(@id,'dailyForm:j') and contains(@id,':dailyReporthoursVariance:j')][2]";	//Monday Worked Hours Total
		public static final String VarHrTuesdayTtl="//td[contains(@id,'dailyForm:j') and contains(@id,':dailyReporthoursVariance:j')][4]";	//Tuesday Worked Hours Total
		public static final String VarHrWednesdayTtl="//td[contains(@id,'dailyForm:j') and contains(@id,':dailyReporthoursVariance:j')][6]";	//Wednesday Worked Hours Total
		public static final String VarHrThursdayTtl="//td[contains(@id,'dailyForm:j') and contains(@id,':dailyReporthoursVariance:j')][8]";	//Thursday Worked Hours Total
		public static final String VarHrFridayTtl="//td[contains(@id,'dailyForm:j') and contains(@id,':dailyReporthoursVariance:j')][10]";	//Friday Worked Hours Total	
		public static final String VarHrSaturdayTtl="//td[contains(@id,'dailyForm:j') and contains(@id,':dailyReporthoursVariance:j')][12]";	//Saturday Worked Hours Total
		public static final String VarHrSundayTtl="//td[contains(@id,'dailyForm:j') and contains(@id,':dailyReporthoursVariance:j')][14]";	//Sunday Worked Hours Total
		public static final String VarHrWeekToDateTtl="//td[contains(@id,'dailyForm:j') and contains(@id,':dailyReporthoursVariance:j')][16]";	//WeekTotal Worked Hours Total
				
		//Paid Hours
		public static final String VarPayHrMonday="//td[contains(@id,'dailyForm:j') and contains(@id,':dailyReporthoursVariance:dynamic:j')][3]";	//Monday Paid Hours Column
		public static final String VarPayHrTuesday="//td[contains(@id,'dailyForm:j') and contains(@id,':dailyReporthoursVariance:dynamic:j')][5]";	//Tuesday Paid Hours Column
		public static final String VarPayHrWednesday="//td[contains(@id,'dailyForm:j') and contains(@id,':dailyReporthoursVariance:dynamic:j')][7]";	//Wed Paid Hours Column
		public static final String VarPayHrThursday="//td[contains(@id,'dailyForm:j') and contains(@id,':dailyReporthoursVariance:dynamic:j')][9]";	//Thur Paid Hours Column
		public static final String VarPayHrFriday="//td[contains(@id,'dailyForm:j') and contains(@id,':dailyReporthoursVariance:dynamic:j')][11]";	//Fri Paid Hours Column
		public static final String VarPayHrSaturday="//td[contains(@id,'dailyForm:j') and contains(@id,':dailyReporthoursVariance:dynamic:j')][13]";	//Sat Paid Hours Column
		public static final String VarPayHrSunday="//td[contains(@id,'dailyForm:j') and contains(@id,':dailyReporthoursVariance:dynamic:j')][15]";	//Sun Paid Hours Column
		public static final String VarPayHrWeekToDate="//td[contains(@id,'dailyForm:j') and contains(@id,':dailyReporthoursVariance:dynamic:j')][17]";	//WeekTotal Paid Hours Column

		public static final String VarPayHrMondayTtl="//td[contains(@id,'dailyForm:j') and contains(@id,':dailyReporthoursVariance:j')][3]";	//Monday Paid Hours Total
		public static final String VarPayHrTuesdayTtl="//td[contains(@id,'dailyForm:j') and contains(@id,':dailyReporthoursVariance:j')][5]";	//Tue Paid Hours Total
		public static final String VarPayHrWednesdayTtl="//td[contains(@id,'dailyForm:j') and contains(@id,':dailyReporthoursVariance:j')][7]";	//Wed Paid Hours Total
		public static final String VarPayHrThursdayTtl="//td[contains(@id,'dailyForm:j') and contains(@id,':dailyReporthoursVariance:j')][9]";	//Thur Paid Hours Total
		public static final String VarPayHrFridayTtl="//td[contains(@id,'dailyForm:j') and contains(@id,':dailyReporthoursVariance:j')][11]";	//Fri Paid Hours Total
		public static final String VarPayHrSaturdayTtl="//td[contains(@id,'dailyForm:j') and contains(@id,':dailyReporthoursVariance:j')][13]";	//Sat Paid Hours Total
		public static final String VarPayHrSundayTtl="//td[contains(@id,'dailyForm:j') and contains(@id,':dailyReporthoursVariance:j')][15]";	//Sun Paid Hours Total
		public static final String VarPayHrWeekToDateTtl="//td[contains(@id,'dailyForm:j') and contains(@id,':dailyReporthoursVariance:j')][17]";	//WeekTotal Paid Hours Total

		
	//VIEW - OPERATING RATIO - PLAN
	public static final String ORDepartments="//td[contains(@id,'dailyForm:j') and contains(@id,':dailyReportORPlan:dynamic:j')][1]";
	
	//OR% Work Plan
	public static final String ORMonday="//td[contains(@id,'dailyForm:j') and contains(@id,':dailyReportORPlan:dynamic:j')][2]";
	public static final String ORTuesday="//td[contains(@id,'dailyForm:j') and contains(@id,':dailyReportORPlan:dynamic:j')][4]";
	public static final String ORWednesday="//td[contains(@id,'dailyForm:j') and contains(@id,':dailyReportORPlan:dynamic:j')][6]";
	public static final String ORThursday="//td[contains(@id,'dailyForm:j') and contains(@id,':dailyReportORPlan:dynamic:j')][8]";
	public static final String ORFriday="//td[contains(@id,'dailyForm:j') and contains(@id,':dailyReportORPlan:dynamic:j')][10]";
	public static final String ORSaturday="//td[contains(@id,'dailyForm:j') and contains(@id,':dailyReportORPlan:dynamic:j')][12]";
	public static final String ORSunday="//td[contains(@id,'dailyForm:j') and contains(@id,':dailyReportORPlan:dynamic:j')][14]";
	public static final String ORWeekToDate="//td[contains(@id,'dailyForm:j') and contains(@id,':dailyReportORPlan:dynamic:j')][16]";
	
	public static final String ORTotal="//td[contains(@id,'dailyForm:j') and contains(@id,':dailyReportORPlan:j')][1]";
	public static final String ORMondayTtl="//td[contains(@id,'dailyForm:j') and contains(@id,':dailyReportORPlan:j')][2]";
	public static final String ORTuesdayTtl="//td[contains(@id,'dailyForm:j') and contains(@id,':dailyReportORPlan:j')][4]";
	public static final String ORWednesdayTtl="//td[contains(@id,'dailyForm:j') and contains(@id,':dailyReportORPlan:j')][6]";
	public static final String ORThursdayTtl="//td[contains(@id,'dailyForm:j') and contains(@id,':dailyReportORPlan:j')][8]";
	public static final String ORFridayTtl="//td[contains(@id,'dailyForm:j') and contains(@id,':dailyReportORPlan:j')][10]";
	public static final String ORSaturdayTtl="//td[contains(@id,'dailyForm:j') and contains(@id,':dailyReportORPlan:j')][12]";
	public static final String ORSundayTtl="//td[contains(@id,'dailyForm:j') and contains(@id,':dailyReportORPlan:j')][14]";
	public static final String ORWeekToDateTtl="//td[contains(@id,'dailyForm:j') and contains(@id,':dailyReportORPlan:j')][16]";
	
	//OR% PayPlan
	public static final String PayORMonday="//td[contains(@id,'dailyForm:j') and contains(@id,':dailyReportORPlan:dynamic:j')][3]";
	public static final String PayORTuesday="//td[contains(@id,'dailyForm:j') and contains(@id,':dailyReportORPlan:dynamic:j')][5]";
	public static final String PayORWednesday="//td[contains(@id,'dailyForm:j') and contains(@id,':dailyReportORPlan:dynamic:j')][7]";
	public static final String PayORThursday="//td[contains(@id,'dailyForm:j') and contains(@id,':dailyReportORPlan:dynamic:j')][9]";
	public static final String PayORFriday="//td[contains(@id,'dailyForm:j') and contains(@id,':dailyReportORPlan:dynamic:j')][11]";
	public static final String PayORSaturday="//td[contains(@id,'dailyForm:j') and contains(@id,':dailyReportORPlan:dynamic:j')][13]";
	public static final String PayORSunday="//td[contains(@id,'dailyForm:j') and contains(@id,':dailyReportORPlan:dynamic:j')][15]";
	public static final String PayORWeekToDate="//td[contains(@id,'dailyForm:j') and contains(@id,':dailyReportORPlan:dynamic:j')][17]";

	public static final String PayORMondayTtl="//td[contains(@id,'dailyForm:j') and contains(@id,':dailyReportORPlan:j')][3]";
	public static final String PayORTuesdayTtl="//td[contains(@id,'dailyForm:j') and contains(@id,':dailyReportORPlan:j')][5]";
	public static final String PayORWednesdayTtl="//td[contains(@id,'dailyForm:j') and contains(@id,':dailyReportORPlan:j')][7]";
	public static final String PayORThursdayTtl="//td[contains(@id,'dailyForm:j') and contains(@id,':dailyReportORPlan:j')][9]";
	public static final String PayORFridayTtl="//td[contains(@id,'dailyForm:j') and contains(@id,':dailyReportORPlan:j')][11]";
	public static final String PayORSaturdayTtl="//td[contains(@id,'dailyForm:j') and contains(@id,':dailyReportORPlan:j')][13]";
	public static final String PayORSundayTtl="//td[contains(@id,'dailyForm:j') and contains(@id,':dailyReportORPlan:j')][15]";
	public static final String PayORWeekToDateTtl="//td[contains(@id,'dailyForm:j') and contains(@id,':dailyReportORPlan:j')][17]";
	
	
	//VIEW - OPERATING RATIO - ACTUAL
	public static final String ORDepartmentsActl="//td[contains(@id,'dailyForm:j') and contains(@id,':dailyReportORAct:dynamic:j')][1]";
	
	//OR% Worked
	public static final String ORMondayActl="//td[contains(@id,'dailyForm:j') and contains(@id,':dailyReportORAct:dynamic:j')][2]";
	public static final String ORTuesdayActl="//td[contains(@id,'dailyForm:j') and contains(@id,':dailyReportORAct:dynamic:j')][4]";
	public static final String ORWednesdayActl="//td[contains(@id,'dailyForm:j') and contains(@id,':dailyReportORAct:dynamic:j')][6]";
	public static final String ORThursdayActl="//td[contains(@id,'dailyForm:j') and contains(@id,':dailyReportORAct:dynamic:j')][8]";
	public static final String ORFridayActl="//td[contains(@id,'dailyForm:j') and contains(@id,':dailyReportORAct:dynamic:j')][10]";
	public static final String ORSaturdayActl="//td[contains(@id,'dailyForm:j') and contains(@id,':dailyReportORAct:dynamic:j')][12]";
	public static final String ORSundayActl="//td[contains(@id,'dailyForm:j') and contains(@id,':dailyReportORAct:dynamic:j')][14]";
	public static final String ORWeekToDateActl="//td[contains(@id,'dailyForm:j') and contains(@id,':dailyReportORAct:dynamic:j')][16]";
	
	public static final String ORTotalActl="//td[contains(@id,'dailyForm:j') and contains(@id,':dailyReportORAct:j')][1]";
	public static final String ORMondayTtlActl="//td[contains(@id,'dailyForm:j') and contains(@id,':dailyReportORAct:j')][2]";
	public static final String ORTuesdayTtlActl="//td[contains(@id,'dailyForm:j') and contains(@id,':dailyReportORAct:j')][4]";
	public static final String ORWednesdayTtlActl="//td[contains(@id,'dailyForm:j') and contains(@id,':dailyReportORAct:j')][6]";
	public static final String ORThursdayTtlActl="//td[contains(@id,'dailyForm:j') and contains(@id,':dailyReportORAct:j')][8]";
	public static final String ORFridayTtlActl="//td[contains(@id,'dailyForm:j') and contains(@id,':dailyReportORAct:j')][10]";
	public static final String ORSaturdayTtlActl="//td[contains(@id,'dailyForm:j') and contains(@id,':dailyReportORAct:j')][12]";
	public static final String ORSundayTtlActl="//td[contains(@id,'dailyForm:j') and contains(@id,':dailyReportORAct:j')][14]";
	public static final String ORWeekToDateTtlActl="//td[contains(@id,'dailyForm:j') and contains(@id,':dailyReportORAct:j')][16]";
	
	//OR% Paid
	public static final String PayORMondayActl="//td[contains(@id,'dailyForm:j') and contains(@id,':dailyReportORAct:dynamic:j')][3]";
	public static final String PayORTuesdayActl="//td[contains(@id,'dailyForm:j') and contains(@id,':dailyReportORAct:dynamic:j')][5]";
	public static final String PayORWednesdayActl="//td[contains(@id,'dailyForm:j') and contains(@id,':dailyReportORAct:dynamic:j')][7]";
	public static final String PayORThursdayActl="//td[contains(@id,'dailyForm:j') and contains(@id,':dailyReportORAct:dynamic:j')][9]";
	public static final String PayORFridayActl="//td[contains(@id,'dailyForm:j') and contains(@id,':dailyReportORAct:dynamic:j')][11]";
	public static final String PayORSaturdayActl="//td[contains(@id,'dailyForm:j') and contains(@id,':dailyReportORAct:dynamic:j')][13]";
	public static final String PayORSundayActl="//td[contains(@id,'dailyForm:j') and contains(@id,':dailyReportORAct:dynamic:j')][15]";
	public static final String PayORWeekToDateActl="//td[contains(@id,'dailyForm:j') and contains(@id,':dailyReportORAct:dynamic:j')][17]";

	public static final String PayORMondayTtlActl="//td[contains(@id,'dailyForm:j') and contains(@id,':dailyReportORAct:j')][3]";
	public static final String PayORTuesdayTtlActl="//td[contains(@id,'dailyForm:j') and contains(@id,':dailyReportORAct:j')][5]";
	public static final String PayORWednesdayTtlActl="//td[contains(@id,'dailyForm:j') and contains(@id,':dailyReportORAct:j')][7]";
	public static final String PayORThursdayTtlActl="//td[contains(@id,'dailyForm:j') and contains(@id,':dailyReportORAct:j')][9]";
	public static final String PayORFridayTtlActl="//td[contains(@id,'dailyForm:j') and contains(@id,':dailyReportORAct:j')][11]";
	public static final String PayORSaturdayTtlActl="//td[contains(@id,'dailyForm:j') and contains(@id,':dailyReportORAct:j')][13]";
	public static final String PayORSundayTtlActl="//td[contains(@id,'dailyForm:j') and contains(@id,':dailyReportORAct:j')][15]";
	public static final String PayORWeekToDateTtlActl="//td[contains(@id,'dailyForm:j') and contains(@id,':dailyReportORAct:j')][17]";
	
	
	//UPDATED (VIEW - OPERATING RATIO - VARIANCE)
		public static final String VARORDepartments="//td[contains(@id,'dailyForm:j') and contains(@id,':dailyReportOR:dynamic:j')][1]";
		
		//OR% Worked
		public static final String VARORMonday="//td[contains(@id,'dailyForm:j') and contains(@id,':dailyReportOR:dynamic:j')][2]";
		public static final String VARORTuesday="//td[contains(@id,'dailyForm:j') and contains(@id,':dailyReportOR:dynamic:j')][4]";
		public static final String VARORWednesday="//td[contains(@id,'dailyForm:j') and contains(@id,':dailyReportOR:dynamic:j')][6]";
		public static final String VARORThursday="//td[contains(@id,'dailyForm:j') and contains(@id,':dailyReportOR:dynamic:j')][8]";
		public static final String VARORFriday="//td[contains(@id,'dailyForm:j') and contains(@id,':dailyReportOR:dynamic:j')][10]";
		public static final String VARORSaturday="//td[contains(@id,'dailyForm:j') and contains(@id,':dailyReportOR:dynamic:j')][12]";
		public static final String VARORSunday="//td[contains(@id,'dailyForm:j') and contains(@id,':dailyReportOR:dynamic:j')][14]";
		public static final String VARORWeekToDate="//td[contains(@id,'dailyForm:j') and contains(@id,':dailyReportOR:dynamic:j')][16]";
		
		public static final String VARORTotalActl="//td[contains(@id,'dailyForm:j') and contains(@id,':dailyReportOR:j')][1]";
		public static final String VARORMondayTtl="//td[contains(@id,'dailyForm:j') and contains(@id,':dailyReportOR:j')][2]";
		public static final String VARORTuesdayTtl="//td[contains(@id,'dailyForm:j') and contains(@id,':dailyReportOR:j')][4]";
		public static final String VARORWednesdayTtl="//td[contains(@id,'dailyForm:j') and contains(@id,':dailyReportOR:j')][6]";
		public static final String VARORThursdayTtl="//td[contains(@id,'dailyForm:j') and contains(@id,':dailyReportOR:j')][8]";
		public static final String VARORFridayTtl="//td[contains(@id,'dailyForm:j') and contains(@id,':dailyReportOR:j')][10]";
		public static final String VARORSaturdayTtl="//td[contains(@id,'dailyForm:j') and contains(@id,':dailyReportOR:j')][12]";
		public static final String VARORSundayTtl="//td[contains(@id,'dailyForm:j') and contains(@id,':dailyReportOR:j')][14]";
		public static final String VARORWeekToDateTtl="//td[contains(@id,'dailyForm:j') and contains(@id,':dailyReportOR:j')][16]";
		
		//OR% Paid
		public static final String VARPayORMonday="//td[contains(@id,'dailyForm:j') and contains(@id,':dailyReportOR:dynamic:j')][3]";
		public static final String VARPayORTuesday="//td[contains(@id,'dailyForm:j') and contains(@id,':dailyReportOR:dynamic:j')][5]";
		public static final String VARPayORWednesday="//td[contains(@id,'dailyForm:j') and contains(@id,':dailyReportOR:dynamic:j')][7]";
		public static final String VARPayORThursday="//td[contains(@id,'dailyForm:j') and contains(@id,':dailyReportOR:dynamic:j')][9]";
		public static final String VARPayORFriday="//td[contains(@id,'dailyForm:j') and contains(@id,':dailyReportOR:dynamic:j')][11]";
		public static final String VARPayORSaturday="//td[contains(@id,'dailyForm:j') and contains(@id,':dailyReportOR:dynamic:j')][13]";
		public static final String VARPayORSunday="//td[contains(@id,'dailyForm:j') and contains(@id,':dailyReportOR:dynamic:j')][15]";
		public static final String VARPayORWeekToDate="//td[contains(@id,'dailyForm:j') and contains(@id,':dailyReportOR:dynamic:j')][17]";

		public static final String VARPayORMondayTtl="//td[contains(@id,'dailyForm:j') and contains(@id,':dailyReportOR:j')][3]";
		public static final String VARPayORTuesdayTtl="//td[contains(@id,'dailyForm:j') and contains(@id,':dailyReportOR:j')][5]";
		public static final String VARPayORWednesdayTtl="//td[contains(@id,'dailyForm:j') and contains(@id,':dailyReportOR:j')][7]";
		public static final String VARPayORThursdayTtl="//td[contains(@id,'dailyForm:j') and contains(@id,':dailyReportOR:j')][9]";
		public static final String VARPayORFridayTtl="//td[contains(@id,'dailyForm:j') and contains(@id,':dailyReportOR:j')][11]";
		public static final String VARPayORSaturdayTtl="//td[contains(@id,'dailyForm:j') and contains(@id,':dailyReportOR:j')][13]";
		public static final String VARPayORSundayTtl="//td[contains(@id,'dailyForm:j') and contains(@id,':dailyReportOR:j')][15]";
		public static final String VARPayORWeekToDateTtl="//td[contains(@id,'dailyForm:j') and contains(@id,':dailyReportOR:j')][17]";
}
