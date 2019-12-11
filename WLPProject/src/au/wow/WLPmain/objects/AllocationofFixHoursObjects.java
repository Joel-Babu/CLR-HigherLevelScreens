package au.wow.WLPmain.objects;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class AllocationofFixHoursObjects 
{
	@FindBy(xpath= "/html/frameset/frame[2]") 
	public WebElement DataFrame;
	
	@FindBy(xpath= "//*[@id='form1:buttonLogoff']")
	public WebElement Logoff;
	
	@FindBy(xpath= "/html/frameset/frame[1]")
	public WebElement TitleFrame;
	
	@FindBy(xpath="//*[@id='frmDetails:lblDepartment']")
	public WebElement DepartmentHeader;
	
	@FindBy(xpath= "//*[@id='frmDetails:cbxDepartment']")
	public WebElement DepartmentDropdown;
	
	@FindBy(xpath= "//*[@id='frmDetails:ctrJobDailySplitTable:5:oSMSJobsTotal']")
	public WebElement TtlDlyHrsTitle;
	
	public static final String Table="//*[@id='frmDetails:ctrJobDailySplitTable']/tbody/tr";
	
	public static final String SMSJobs="//*[@id='frmDetails:ctrJobDailySplitTable:dynamic:oSMSJobs']";	
	public static final String Taskcode="//*[@id='frmDetails:ctrJobDailySplitTable:dynamic:oTaskMnc']";
	public static final String VarStd="//*[@id='frmDetails:ctrJobDailySplitTable:dynamic:oVarPer']";
	public static final String VarTyp="//*[@id='frmDetails:ctrJobDailySplitTable:dynamic:oVarType']";
	public static final String Priority="//*[@id='frmDetails:ctrJobDailySplitTable:dynamic:oPriority']";
	public static final String TtlDlyHrs="//*[@id='frmDetails:ctrJobDailySplitTable:dynamic:oSMSJobsTotal']";
	
	
	public static final String FxdHrsPrcntMon="//*[@id='frmDetails:ctrJobDailySplitTable:dynamic:iMonFixedHrPct']";
	public static final String FxdHrsPrcntTue="//*[@id='frmDetails:ctrJobDailySplitTable:dynamic:iTueFixedHrPct']";
	public static final String FxdHrsPrcntWed="//*[@id='frmDetails:ctrJobDailySplitTable:dynamic:iWedFixedHrPct']";
	public static final String FxdHrsPrcntThu="//*[@id='frmDetails:ctrJobDailySplitTable:dynamic:iThuFixedHrPct']";
	public static final String FxdHrsPrcntFri="//*[@id='frmDetails:ctrJobDailySplitTable:dynamic:iFriFixedHrPct']";
	public static final String FxdHrsPrcntSat="//*[@id='frmDetails:ctrJobDailySplitTable:dynamic:iSatFixedHrPct']";
	public static final String FxdHrsPrcntSun="//*[@id='frmDetails:ctrJobDailySplitTable:dynamic:iSunFixedHrPct']";
	public static final String FxdHrsPrcntTtl="//*[@id='frmDetails:ctrJobDailySplitTable:dynamic:iTotalHrsFixedHrPct']";
	
	public static final String FxdHrsMon="//*[@id='frmDetails:ctrJobDailySplitTable:dynamic:oMonFixHr']";
	public static final String FxdHrsTue="//*[@id='frmDetails:ctrJobDailySplitTable:dynamic:oTueFixHr']";
	public static final String FxdHrsWed="//*[@id='frmDetails:ctrJobDailySplitTable:dynamic:oWedFixHr']";
	public static final String FxdHrsThu="//*[@id='frmDetails:ctrJobDailySplitTable:dynamic:oThuFixHr']";
	public static final String FxdHrsFri="//*[@id='frmDetails:ctrJobDailySplitTable:dynamic:oFriFixHr']";
	public static final String FxdHrsSat="//*[@id='frmDetails:ctrJobDailySplitTable:dynamic:oSatFixHr']";
	public static final String FxdHrsSun="//*[@id='frmDetails:ctrJobDailySplitTable:dynamic:oSunFixHr']";
	public static final String FxdHrsTtl="//*[@id='frmDetails:ctrJobDailySplitTable:dynamic:oTotalHrsFixHr']";
	
	public static final String VarHrsMon="//*[@id='frmDetails:ctrJobDailySplitTable:dynamic:oMonVarHr']";
	public static final String VarHrsTue="//*[@id='frmDetails:ctrJobDailySplitTable:dynamic:oTueVarHr']";
	public static final String VarHrsWed="//*[@id='frmDetails:ctrJobDailySplitTable:dynamic:oWedVarHr']";
	public static final String VarHrsThu="//*[@id='frmDetails:ctrJobDailySplitTable:dynamic:oThuVarHr']";
	public static final String VarHrsFri="//*[@id='frmDetails:ctrJobDailySplitTable:dynamic:oFriVarHr']";
	public static final String VarHrsSat="//*[@id='frmDetails:ctrJobDailySplitTable:dynamic:oSatVarHr']";
	public static final String VarHrsSun="//*[@id='frmDetails:ctrJobDailySplitTable:dynamic:oSunVarHr']";
	public static final String VarHrsTtl="//*[@id='frmDetails:ctrJobDailySplitTable:dynamic:oTotalHrsVarHr']";
	
	public static final String TotalMon="//*[@id='frmDetails:ctrJobDailySplitTable:dynamic:oMonTotalHr']";
	public static final String TotalTue="//*[@id='frmDetails:ctrJobDailySplitTable:dynamic:oTueTotalHr']";
	public static final String TotalWed="//*[@id='frmDetails:ctrJobDailySplitTable:dynamic:oWedTotalHr']";
	public static final String TotalThu="//*[@id='frmDetails:ctrJobDailySplitTable:dynamic:oThuTotalHr']";
	public static final String TotalFri="//*[@id='frmDetails:ctrJobDailySplitTable:dynamic:oFriTotalHr']";
	public static final String TotalSat="//*[@id='frmDetails:ctrJobDailySplitTable:dynamic:oSatTotalHr']";
	public static final String TotalSun="//*[@id='frmDetails:ctrJobDailySplitTable:dynamic:oSunTotalHr']";
	public static final String TotalTtl="//*[@id='frmDetails:ctrJobDailySplitTable:dynamic:oTotalHrsTotalHr']";
	
	public static final String TtlDlyHrsMon="//*[@id='frmDetails:ctrJobDailySplitTable:dynamic:oMonTotalHr']";
	public static final String TtlDlyHrsTue="//*[@id='frmDetails:ctrJobDailySplitTable:dynamic:oTueTotalHr']";
	public static final String TtlDlyHrsWed="//*[@id='frmDetails:ctrJobDailySplitTable:dynamic:oWedTotalHr']";
	public static final String TtlDlyHrsThu="//*[@id='frmDetails:ctrJobDailySplitTable:dynamic:oThuTotalHr']";
	public static final String TtlDlyHrsFri="//*[@id='frmDetails:ctrJobDailySplitTable:dynamic:oFriTotalHr']";
	public static final String TtlDlyHrsSat="//*[@id='frmDetails:ctrJobDailySplitTable:dynamic:oSatTotalHr']";
	public static final String TtlDlyHrsSun="//*[@id='frmDetails:ctrJobDailySplitTable:dynamic:oSunTotalHr']";
	public static final String TtlDlyHrsTotal="//*[@id='frmDetails:ctrJobDailySplitTable:dynamic:oTotalHrsTotalHr']";
}
