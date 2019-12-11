package au.wow.WLPmain.objects;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class PlanVsActualsObjects 
{
	
	@FindBy(xpath= "/html/frameset/frame[2]") 
	public WebElement DataFrame;
	
	@FindBy(xpath= "//*[@id='form1:buttonLogoff']")
	public WebElement Logoff;
	
	@FindBy(xpath= "/html/frameset/frame[1]")
	public WebElement TitleFrame;
	
	public static final String Table="//*[@id='PlanVsActualForm:t1']/tbody/tr";
	
	//Plan Vs Actual Daily Summary screen - Unworked Holidays  /text()[1]
		public static final String WagesRow="//*[@id='printDiv']/table[3]/tbody/tr/td[2]/table/tbody/tr[1]/td[1]";
		public static final String HoursRow="//*[@id='printDiv']/table[3]/tbody/tr/td[2]/table/tbody/tr[2]/td[1]";
		public static final String Wages="//*[@id='PlanVsActualForm:dlyUnwkPlnWages']";
		public static final String Hours="//*[@id='PlanVsActualForm:dlyUnwkPlnHours']";
		
	//Other objects
		public static final String Departments="//td[contains(@id,'PlanVsActualForm:t1:dynamic')][1]";	//Deparment Column
		public static final String StoreTotal="//td[contains(@id,'PlanVsActualForm:t1:13')][1]";	//Store Total Text
		public static final String PlannedWagesandHours="//td[contains(@id,'PlanVsActualForm:t1:dynamic')][3]";
		public static final String PlannedWagesandHoursTtl="//td[contains(@id,'PlanVsActualForm:t1:13')][3]";
		
		public static final String MondayWages="//td[contains(@id,'PlanVsActualForm:t1:dynamic')][4]";
		public static final String TuesdayWages="//td[contains(@id,'PlanVsActualForm:t1:dynamic')][5]";
		public static final String WednesdayWages="//td[contains(@id,'PlanVsActualForm:t1:dynamic')][6]";
		public static final String ThursdayWages="//td[contains(@id,'PlanVsActualForm:t1:dynamic')][7]";
		public static final String FridayWages="//td[contains(@id,'PlanVsActualForm:t1:dynamic')][8]";
		public static final String SaturdayWages="//td[contains(@id,'PlanVsActualForm:t1:dynamic')][9]";
		public static final String SundayWages="//td[contains(@id,'PlanVsActualForm:t1:dynamic')][10]";
		
		public static final String MondayWagesTtl="//td[contains(@id,'PlanVsActualForm:t1:13')][4]";
		public static final String TuesdayWagesTtl="//td[contains(@id,'PlanVsActualForm:t1:13')][5]";
		public static final String WednesdayWagesTtl="//td[contains(@id,'PlanVsActualForm:t1:13')][6]";
		public static final String ThursdayWagesTtl="//td[contains(@id,'PlanVsActualForm:t1:13')][7]";
		public static final String FridayWagesTtl="//td[contains(@id,'PlanVsActualForm:t1:13')][8]";
		public static final String SaturdayWagesTtl="//td[contains(@id,'PlanVsActualForm:t1:13')][9]";
		public static final String SundayWagesTtl="//td[contains(@id,'PlanVsActualForm:t1:13')][10]";
		
		public static final String MondayHours="//td[contains(@id,'PlanVsActualForm:t1:dynamic')][4]";	//Not Required - Duplicate
		public static final String TuesdayHours="//td[contains(@id,'PlanVsActualForm:t1:dynamic')][5]";	//Not Required - Duplicate
		public static final String WednesdayHours="//td[contains(@id,'PlanVsActualForm:t1:dynamic')][6]";	//Not Required - Duplicate
		public static final String ThursdayHours="//td[contains(@id,'PlanVsActualForm:t1:dynamic')][7]";	//Not Required - Duplicate
		public static final String FridayHours="//td[contains(@id,'PlanVsActualForm:t1:dynamic')][8]";	//Not Required - Duplicate
		public static final String SaturdayHours="//td[contains(@id,'PlanVsActualForm:t1:dynamic')][9]";	//Not Required - Duplicate
		public static final String SundayHours="//td[contains(@id,'PlanVsActualForm:t1:dynamic')][10]";	//Not Required - Duplicate
		
		public static final String MondayHoursTtl="//td[contains(@id,'PlanVsActualForm:t1:13')][4]";	//Not Required - Duplicate
		public static final String TuesdayHoursTtl="//td[contains(@id,'PlanVsActualForm:t1:13')][5]";	//Not Required - Duplicate
		public static final String WednesdayHoursTtl="//td[contains(@id,'PlanVsActualForm:t1:13')][6]";	//Not Required - Duplicate
		public static final String ThursdayHoursTtl="//td[contains(@id,'PlanVsActualForm:t1:13')][7]";	//Not Required - Duplicate
		public static final String FridayHoursTtl="//td[contains(@id,'PlanVsActualForm:t1:13')][8]";	//Not Required - Duplicate
		public static final String SaturdayHoursTtl="//td[contains(@id,'PlanVsActualForm:t1:13')][9]";	//Not Required - Duplicate
		public static final String SundayHoursTtl="//td[contains(@id,'PlanVsActualForm:t1:13')][10]";	//Not Required - Duplicate
		
		public static final String WkToDtWagesandHours="//td[contains(@id,'PlanVsActualForm:t1:dynamic')][11]";
		public static final String WkToDtWagesandHoursTtl="//td[contains(@id,'PlanVsActualForm:t1:13')][11]";
		
		//Longlife and seafood and deli
		public static final String RowNameTotalLL="//td[contains(@id,'PlanVsActualForm:t1:15')][1]";	
		public static final String RowNameTotalSFD="//td[contains(@id,'PlanVsActualForm:t1:16')][1]";	
		public static final String PlannedWagesandHoursTtlLL="//td[contains(@id,'PlanVsActualForm:t1:15')][3]";
		public static final String PlannedWagesandHoursTtlSFD="//td[contains(@id,'PlanVsActualForm:t1:16')][3]";
		
		public static final String MondayWagesLL="//td[contains(@id,'PlanVsActualForm:t1:15')][4]";
		public static final String MondayWagesSFD="//td[contains(@id,'PlanVsActualForm:t1:16')][4]";
		public static final String TuesdayWagesLL="//td[contains(@id,'PlanVsActualForm:t1:15')][5]";
		public static final String TuesdayWagesSFD="//td[contains(@id,'PlanVsActualForm:t1:16')][5]";
		public static final String WednesdayWagesLL="//td[contains(@id,'PlanVsActualForm:t1:15')][6]";
		public static final String WednesdayWagesSFD="//td[contains(@id,'PlanVsActualForm:t1:16')][6]";
		public static final String ThursdayWagesLL="//td[contains(@id,'PlanVsActualForm:t1:15')][7]";
		public static final String ThursdayWagesSFD="//td[contains(@id,'PlanVsActualForm:t1:16')][7]";
		public static final String FridayWagesLL="//td[contains(@id,'PlanVsActualForm:t1:15')][8]";
		public static final String FridayWagesSFD="//td[contains(@id,'PlanVsActualForm:t1:16')][8]";
		public static final String SaturdayWagesLL="//td[contains(@id,'PlanVsActualForm:t1:15')][9]";
		public static final String SaturdayWagesSFD="//td[contains(@id,'PlanVsActualForm:t1:16')][9]";
		public static final String SundayWagesLL="//td[contains(@id,'PlanVsActualForm:t1:15')][10]";
		public static final String SundayWagesSFD="//td[contains(@id,'PlanVsActualForm:t1:16')][10]";
		public static final String WkToDtWagesandHoursLL="//td[contains(@id,'PlanVsActualForm:t1:15')][11]";
		public static final String WkToDtWagesandHoursSFD="//td[contains(@id,'PlanVsActualForm:t1:16')][11]";
		
}
