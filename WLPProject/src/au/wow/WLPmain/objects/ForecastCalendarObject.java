package au.wow.WLPmain.objects;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class ForecastCalendarObject
{
	@FindBy(xpath= "/html/frameset/frame[2]") 
	public WebElement DataFrame;
	
	@FindBy(xpath="//*[@id='foreCastForm:textDepartment']")
	public WebElement DepartmentHeader;
	
	@FindBy(xpath="//*[@id='foreCastForm:cbxDepartments']")
	public WebElement DepartmentDropdown;
	
	@FindBy(xpath= "//*[@id='form1:buttonLogoff']")
	public WebElement Logoff;
	
	@FindBy(xpath= "/html/frameset/frame[1]")
	public WebElement TitleFrame;
	
	public static final String table="//*[@id='foreCastForm:evForecastingCalendar:tb']/tr";
	
	@FindBy(xpath= "//*[@id='foreCastForm']/table/tbody/tr/td[1]")
	public WebElement MainHeading;
	
	@FindBy(xpath="//*[@id='foreCastForm']/div/div/span")
	public WebElement TableAreaHeading;
	
	@FindBy(xpath="//*[@id='j_id_jsp_221359892_1']/table[1]/tbody/tr/td[1]/span[1]")
	public WebElement MainHeadingLeftArrow;
	
	@FindBy(xpath="//*[@id='j_id_jsp_221359892_1']/table[1]/tbody/tr/td[1]/span[2]")
	public WebElement MainHeadingRightArrow;
	
	@FindBy(xpath="//*[@id='foreCastForm:perviousYear']")
	public WebElement MainHeadingPreviousYearBox; 
	
	@FindBy(xpath="//*[@id='foreCastForm:nextYear']")
	public WebElement MainHeadingNextYearBox;
	
	public static final String PastYear="//*[@id='foreCastForm:perviousYear']";
	public static final String FutureYear="//*[@id='foreCastForm:nextYear']";	
	public static final String FutureYear1="//*[@id='foreCastForm:nextYear']";
	public static final String WeekIdtitle="//*[@id='j_id_jsp_221359892_1:evForecastingCalendar:hdrWeekId']";
	public static final String WeekId="//*[@id='foreCastForm:evForecastingCalendar:dynamic:txtWeekId']";
	public static final String Weektypetitle="//*[@id='j_id_jsp_221359892_1:evForecastingCalendar:hdrWeekType']";
	public static final String Weekdaytitle="//*[@id='foreCastForm:evForecastingCalendar:colWeekDaydynamicheader:sortDiv']";
	public static final String Mondayvalue="//*[@id='foreCastForm:evForecastingCalendar:dynamic:colWeekDay1']";
	public static final String Tuesdayvalue="//*[@id='foreCastForm:evForecastingCalendar:dynamic:colWeekDay2']";
	public static final String Wednesdayvalue="//*[@id='foreCastForm:evForecastingCalendar:dynamic:colWeekDay3']";
	public static final String Thursdayvalue="//*[@id='foreCastForm:evForecastingCalendar:dynamic:colWeekDay4']";
	public static final String Fridayvalue="//*[@id='foreCastForm:evForecastingCalendar:dynamic:colWeekDay5']";
	public static final String Saturdayvalue="//*[@id='foreCastForm:evForecastingCalendar:dynamic:colWeekDay6']";
	public static final String Sundayvalue="//*[@id='foreCastForm:evForecastingCalendar:dynamic:colWeekDay7']";
	public static final String SalesLY="//*[@id='foreCastForm:evForecastingCalendar:dynamic:dTblWeekType:0:txtWeekTypeSalesVales']";
	public static final String SalesFcst="//*[@id='foreCastForm:evForecastingCalendar:dynamic:dTblWeekType:0:txtWeekTypeForecast']";
	public static final String SalesActual="//*[@id='foreCastForm:evForecastingCalendar:dynamic:dTblWeekType:0:txtWeekTypeActual']";
	
	
}
