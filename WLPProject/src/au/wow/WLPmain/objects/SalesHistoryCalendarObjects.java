package au.wow.WLPmain.objects;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class SalesHistoryCalendarObjects
{
	@FindBy(xpath= "/html/frameset/frame[2]") 
	public WebElement DataFrame;
	
	@FindBy(xpath="//*[@id='j_id_jsp_221359892_1:textDepartment']")
	public WebElement DepartmentHeader;
	
	@FindBy(xpath="//*[@id='j_id_jsp_221359892_1:cbxDepartments']")
	public WebElement DepartmentDropdown;
	
	@FindBy(xpath= "//*[@id='form1:buttonLogoff']")
	public WebElement Logoff;
	
	@FindBy(xpath= "/html/frameset/frame[1]")
	public WebElement TitleFrame;
	
	public static final String table="//*[@id='j_id_jsp_221359892_1:evForecastingCalendar']/tbody/tr";
	
	@FindBy(xpath= "//*[@id='j_id_jsp_221359892_1']/table[1]/tbody/tr/td[1]")
	public WebElement MainHeading;
	
	@FindBy(xpath="//*[@id='lblCalendarTitle']")
	public WebElement TableAreaHeading;
	
	@FindBy(xpath="//*[@id='j_id_jsp_221359892_1']/table[1]/tbody/tr/td[1]/span[1]")
	public WebElement MainHeadingLeftArrow;
	
	@FindBy(xpath="//*[@id='j_id_jsp_221359892_1']/table[1]/tbody/tr/td[1]/span[2]")
	public WebElement MainHeadingRightArrow;
	
	@FindBy(xpath="//*[@id='j_id_jsp_221359892_1:perviousYear']")
	public WebElement MainHeadingPreviousYearBox; 
	
	@FindBy(xpath="//*[@id='j_id_jsp_221359892_1:nextYear']")
	public WebElement MainHeadingNextYearBox;
	
		
	
	public static final String WeekIdtitle="//*[@id='j_id_jsp_221359892_1:evForecastingCalendar:hdrWeekId']";
	public static final String WeekId="//*[@id='j_id_jsp_221359892_1:evForecastingCalendar:dynamic:txtWeekId']";
	public static final String Weektypetitle="//*[@id='j_id_jsp_221359892_1:evForecastingCalendar:hdrWeekType']";
	public static final String Weekdaytitle="//*[@id='j_id_jsp_221359892_1:evForecastingCalendar:hdrWeekDaydynamic']";
	public static final String Mondayvalue="//*[@id='j_id_jsp_221359892_1:evForecastingCalendar:dynamic:txtWeekDay1']";
	public static final String Tuesdayvalue="//*[@id='j_id_jsp_221359892_1:evForecastingCalendar:dynamic:txtWeekDay2']";
	public static final String Wednesdayvalue="//*[@id='j_id_jsp_221359892_1:evForecastingCalendar:dynamic:txtWeekDay3']";
	public static final String Thursdayvalue="//*[@id='j_id_jsp_221359892_1:evForecastingCalendar:dynamic:txtWeekDay4']";
	public static final String Fridayvalue="//*[@id='j_id_jsp_221359892_1:evForecastingCalendar:dynamic:txtWeekDay5']";
	public static final String Saturdayvalue="//*[@id='j_id_jsp_221359892_1:evForecastingCalendar:dynamic:txtWeekDay6']";
	public static final String Sundayvalue="//*[@id='j_id_jsp_221359892_1:evForecastingCalendar:dynamic:txtWeekDay7']";
	public static final String SalesLY="//*[@id='j_id_jsp_221359892_1:evForecastingCalendar:dynamic:txtWeekTypeSalesVales']";
	public static final String SalesFcst="//*[@id='j_id_jsp_221359892_1:evForecastingCalendar:dynamic:txtWeekTypeForecast']";
	public static final String SalesActual="//*[@id='j_id_jsp_221359892_1:evForecastingCalendar:dynamic:txtWeekTypeActual']";
	
}
